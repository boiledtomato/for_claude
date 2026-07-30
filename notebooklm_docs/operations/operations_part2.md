# Zscaler Help — Deployment / Operations / Terms (part 2)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-07-30 10:13 UTC
Articles in this file: 60

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/managing-incidents","lastmod":"2026-07-17T07:06Z","nid":"1420341"} -->
## Managing Incidents

- Source: https://help.zscaler.com/workflow-automation/managing-incidents
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Incident Management > Managing Incidents
- Last modified: 2026-07-17T07:06Z
- Summary: Information about the Incidents page in the Zscaler Admin Console.

The Incidents page in Workflow Automation captures and displays a list of the transactions that have violated the Data Protection policies (Inline DLP, Endpoint DLP, Email DLP, and SaaS Security DLP) that your organization has configured in the Zscaler Admin Console. Each such recorded transaction is known as an incident. This page enables you to review and remediate Data Loss Prevention (DLP) incidents.

You can view high-level visibility and insight into your organization's [Data Loss Prevention (DLP) incidents](https://help.zscaler.com/zia/about-incidents) using the Incident Analytics dashboard. This dashboard provides a variety of information about the incidents over a specified time frame, such as the time taken to triage and resolve incidents, previous and current incident counts, and the cumulative number of new and resolved incidents. To learn more, see [About the Incident Analytics Dashboard](https://help.zscaler.com/unified/about-incident-analytics-dashboard).

The following is a primary process flow for incident resolution using the Zscaler Admin Console:

1. Workflow Automation assigns the transaction that violated the DLP policies of an organization (the incident) to an admin who has edit access to that incident group. If the violated rules belong to an incident group that is not mapped to an admin, incidents are not auto-assigned to an admin. For those incidents, super admins can log in to the Incidents page and assign admins manually.
2. The admin accesses the Incidents page in the Zscaler Admin Console to review new incidents.
3. The admin then starts investigating the new incident and determines whether to notify the end user (the next step) or escalate to an approver or the end user's manager (the step after that), depending upon the severity of the incident.
4. An email notification requests justification from the end user. After receiving a justification, the admin either closes the incident or escalates it to an approver or the end user's manager (the next step).
5. The approver or the end user's manager receives an email notification requesting advice on how to proceed. After the approver responds, the admin proceeds with the incident or closes it, depending upon the response.

While investigating an incident, the admin can also change the priority of the incident and assign a new DLP admin.

A restricted admin can only assign an incident to a super admin.

## Viewing Incidents

On the Incidents page (Administration > Workflow Automation > Data Protection > Incidents), you can do the following:

1. Export incidents to a CSV file.
2. Refresh the page to display the latest information.
3. [Select a date range for the incidents displayed on the Incidents page.](https://help.zscaler.com/workflow-automation/managing-incidents#date-range-filter)
4. Filter the incident information that is displayed on the page by priority. To display only information about critical and high-priority incidents, select **High Only**.
5. Filter the incident information that is displayed on the page by severity. To display only information about high-severity incidents, select **High Only**.
6. [Use and manage the incident filters](https://help.zscaler.com/workflow-automation/using-incident-filters-workflow-automation).
7. Reset all the applied filters.
8. View the following widgets: You can click a widget to view only the applicable incidents for the selected widget. For example, if you click the **Open** widget, only the incidents with the status **New**, **Investigating**, or **Received** **Justification Response**are displayed in the incidents table, and the widget is highlighted with a blue border. To reset the selection, click the widget again.
  - **All**: Displays the total number of incidents that have occurred in your organization (i.e., **Open**, **Unassigned**, **Resolved**, **Waiting Feedback**, and **Escalated**incidents).
  - **Open**: Displays the number of open incidents (i.e., incidents with the status **New**, **Investigating**, or **Received** **Justification Response**).
  - **Unassigned**: Displays the number of incidents that are not assigned to a DLP admin.
  - **Resolved**: Displays the number of closed incidents (i.e., incidents with the status **Resolved**).
  - **Waiting Feedback**: Displays the number of incidents awaiting justification from the end user or awaiting feedback from the approver after escalation (i.e., incidents with the status **Validating with User** or **Escalated**).
  - **Escalated**: Displays the number of incidents escalated to the managers or approvers for further review (i.e., incidents with the status **Escalated**).
  - **Response Available**: Displays the number of incidents where a user has provided a response from a user notification, or where a manager or approver has provided a response from an escalation notification (i.e., incidents with the status **Received Justification Response**).
9. Perform actions against one or more incidents available on a single page or bulk actions against all incidents available across different pages.
10. Search for an incident by **All** or **Transaction ID**. The search only shows results that match the search string.
  - **All**: This option allows you to enter free-form text that can match multiple incidents. This search is not case sensitive. You can search for incidents associated with a URL by entering a complete URL (e.g., https://www.jumpshare.com/https-post) or the complete host name (e.g., www.jumpshare.com) for a URL. You can also enter multiple strings within a single text string. If you enter multiple strings within a text string, each string is treated with an AND operation. For example, if you enter `Microsoft Office` as the text string, all incidents containing both Microsoft and Office are returned with this search, such as Microsoft Office, Microsoft Office 365, and Office 365 Microsoft. This search does not return incidents containing only Microsoft or Office. If you choose user attributes for obfuscation, you cannot search for incidents using these obfuscated user attributes. In addition, there might be times when you perform a free-form text search for incidents and some of the incidents might not display in the search results due to the obfuscation settings. A notification message appears at the top of the incidents table to inform you of this situation. See image. To learn more about obfuscation settings, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Transaction ID**: This option allows you to search for one or more incidents by their transaction IDs. To search for multiple incidents, enter the transaction IDs separated by a comma. You can search for up to 10 incidents. You can also search for an incident using its duplicate transaction ID. The search result provides the actual transaction ID of the incident. To learn more about duplicated incidents, see [Viewing & Managing Incident Details](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details#Duplicate-Incidents).
11. View a list of incidents that have occurred in your organization. For each incident, you can view:
  - **Transaction ID**: The transaction ID for the incident. If duplicate incidents exist for an incident, this column also displays the total count of the duplicate incidents next to the transaction ID. To learn more, see [Understanding Duplicate Incidents in Workflow Automation](https://help.zscaler.com/workflow-automation/understanding-duplicate-incidents-workflow-automation).
  - **System Creation Date**: The date and time when the incident was created in the system. Workflow Automation searches the incidents based on the System Creation Date, and by default it sorts the incidents based on the Incident Date.
  - **Last Change Date**: The date and time when the incident was last modified. The date and time are displayed in the local time zone of the admin.
  - **Priority**: The priority of the incident. Priorities are **Critical**, **High**, **Medium**, and **Low**.
  - **Severity**: The severity of the incident. Severities are **High**, **Medium**, and **Low**.
  - **DLP Admin**: The DLP admin who is responsible for the incident.
  - **Source DLP Type:** The type of DLP policy that the incident violated. Source DLP types are **Inline**, **Email**, **Endpoint**, and **SaaS Security**.
  - **DLP Type**: The type of DLP incident. This value is retrieved from the incident itself.
  - **Labels**: The labels assigned to the incident.
  - **Status**: The status of the incident. Statuses are:
    - **New**
    - **Investigating**
    - **Validating with User**
    - **Justification Response Received**
    - **Escalated**
    - **Resolved**
  - **Engine(s)**: The DLP engines associated with the incident.
  - **Dictionary Match Count(s)**: The DLP dictionaries associated with the incident. The number of times the incident matched a specific dictionary is displayed in brackets (e.g., Financial Statements [10]).
  - **Rule(s)**: The DLP rules associated with the incident.
  - **Action**: The action associated with the incident.
  - **Destination**: The destination of the incident.
  - **Last Change**: The latest state of the incident. It indicates the most recent change to the incident.
  - **Incident Date**: The date and time when the incident was generated due to a policy violation. The date and time are displayed in the local time zone of the end user.
  - **Incident Groups:**The incident groups associated with the incident.
  - **Justification Reason**: The reason for the incident submitted by the end user, the end user's manager, or another approver.
  - **Justification Note**: The justification type of the incident.
  - **Username**: The name of the end user responsible for the incident. When you click the name link, you are redirected to the Incidents page, which displays only the incidents created by that end user. The user filter is automatically applied in the Filters section. If you applied other filters before clicking the name link, those filters remain applied, as well. If you choose the User Name attribute for obfuscation, multiple asterisks appear for this field. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Client IP**: The client IP address of the end user. If you choose the Client IP attribute for obfuscation, multiple asterisks appear for this field. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **File Name**: The name of the file associated with the incident. If an evidence file for a DLP incident exceeds 100 MB in size, the Zscaler service replaces the original file with a placeholder evidence file. The placeholder file retains the original name with ".txt" appended (e.g., attachment.pdf.txt and samplefile.xlsx.txt). When the placeholder is open or downloaded, the placeholder provides a message indicating that the file is too large to send.
  - **File Type**: The type or extension of the file.
  - **File MD5**: The 32-character MD5 hash of the file.
  - **Application Name**: The name of the application.
  - **Application Category**: The category of the application.
  - **Home Location**: The home location of the end user.
  - **Work Location**: The work location of the end user.
  - **Department**: The department of the end user.
  - **Referrer URL**: The referrer URL of the application.
  - **File Source Location**: The source location of the file.
  - **File Size**: The size of the file in bytes.
  - **File Modification Time**: The date and time that the file was last modified.
  - **Document Type**: The type of document.
  - **Resolution Date**: The date and time when the incident was resolved (i.e., closed).
  - **Integration**: The name of the DLP application integration in Workflow Automation where the incident occurred. To learn more, see [Configuring the DLP Application Integration Using Amazon Web Services](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-amazon-web-services), [Configuring the DLP Application Integration Using Azure](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-azure), and [Configuring the DLP Application Integration Using Google Cloud Platform](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-google-cloud-platform).
  - **Channel**: The type of channel (e.g., Network Drive Transfer or Remote Drive Transfer) that the user used to cause the incident. This field appears only for incidents of Source DLP type Endpoint.
  - **External Collaborators Groups**: The collaborator groups outside your organization for the incident. This field appears only for incidents of Source DLP type SaaS Security.
  - **File Link Expiry**: The date and time when the file link expires. This field appears only for incidents of Source DLP type SaaS Security.
  - **File Modified By**: The email address of the user who last modified the file. This field appears only for incidents of Source DLP type SaaS Security.
  - **File Shared By**: The email address of the user who shared the file. This field appears only for incidents of Source DLP type SaaS Security.
  - **File Shared At**: The date and time when the file was shared. This field appears only for incidents of Source DLP type SaaS Security.
  - **Triggered Recipients**: The recipients who took actions that triggered rules on which some action was taken, such as allow and block. This field appears only for incidents of Source DLP type Email. If you choose the Recipient Email attribute for obfuscation, multiple asterisks appear for each recipient email in this field. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Internal Recipients**: The recipients within your organization who received the email that caused the incident. This field appears only for incidents of Source DLP type SaaS Security. If you choose the Recipient Email attribute for obfuscation, multiple asterisks appear for each recipient email in this field. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **External Recipients**: The recipients outside your organization who received the email that caused the incident. This field appears only for incidents of Source DLP type SaaS Security. If you choose the Recipient Email attribute for obfuscation, multiple asterisks appear for each recipient email in this field. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Protocol**: The protocol (e.g., FTP, HTTP, or HTTPS) used for the incident. This field appears only for incidents of Source DLP type Inline.
  - **User Groups**: The groups that the end user belongs to in your organization.
  - **Destination Type**: The destination type (e.g., Removable Storage Device) for the incident.
  - **Component**: The specific subresource within a SaaS tenant where the violation occurred. This field appears only for incidents of Source DLP type SaaS Security.
  - **Content Location**: The category of the collaboration channel for the event. This field appears only for incidents of Source DLP type SaaS Security.
  - **Workspace**: The name or identifier of the collaboration workspace. This field appears only for incidents of Source DLP type SaaS Security.
  - **Domains**: The domains associated with the transaction context. This field appears only for incidents of Source DLP type SaaS Security.
  - **Attachments:** The names of the files that were sent to the recipients of the email incident. This field appears only for incidents of Source DLP type Email.
12. [Modify the table and its columns](https://help.zscaler.com/unified/using-tables).
13. [View detailed information about each incident on the Incident Details page.](https://help.zscaler.com/workflow-automation/managing-incidents#viewing-incident-details)
14. [View the number of rows of incidents displayed on the page](https://help.zscaler.com/workflow-automation/managing-incidents#rows-per-page). You can modify the number of rows using the **Rows per page** drop-down menu.

See image.

## Managing Incidents

The Incidents page allows you to perform certain actions to manage the incidents assigned to you.

- Apply Time Range Filter
- Export Incidents
- Perform Actions
- Perform Bulk Actions
- View Incident Details
- Modify Rows Per Page

You can filter the incidents displayed on the Incidents page by the date and time of occurrence. By default, this page displays information about the incidents that occurred in the current calendar week (Sunday through Saturday). The time range filter applies to all widgets. Time ranges are:

- Hours
  - Current Hour
  - Last Hour
  - Last 2 Hours
  - Last 6 Hours
  - Last 12 Hours
- Days
  - Current Day
  - Last Day
- Weeks
  - Current Week
  - Last Week
- Months
  - Current Month
  - Last Month
- Custom Date Range To view the incidents that occurred in a specific date range, you can use the **Custom Date Range** option and specify the start and end dates and times. To specify a custom date range: The **Custom Date Range** option only supports incidents that are up to 6 months old, and you can only search for a maximum of a three-month window.
  1. Select the **Custom Date Range** option. A calendar appears with the current date selected. To the right of the calendar, three columns appear, displaying the hours, minutes, and seconds for a day. See image.
  2. Select the start date on the calendar, and then in the columns to the right, select the start time (i.e., hour, minute, and second). As you select the date and time, they appear in the **Start date** field under the calendar. See image.
  3. Click **OK**. The date and time you selected appear in the **Start date** field, and the **End date** field is selected.
  4. Select the end date in the calendar, and then in the columns to the right, select the end time (i.e., hour, minute, and second). See image.
  5. Click **OK**.

See image.

You can export all incidents, or you can use the filter criteria or sort the incidents to modify the incidents that are displayed, and then export those incidents. After you perform the export action, and after your incident file export is successfully completed, Workflow Automation emails you a notification stating that your incident file export is successfully completed. The incident file is available on the [Downloads](https://help.zscaler.com/workflow-automation/managing-downloads) page, where you can download the incidents to a CSV file. The CSV file lists the incidents in the order in which they appeared in the Incidents table when you exported them. For incidents that have duplicate incidents, the CSV file lists the main incident ID along with each of the duplicate incident IDs in the Transaction ID field. The incident IDs are separated by a comma.

Exporting numerous incidents takes time to download. Only a maximum of three bulk activities (download incidents and bulk actions) can be in progress concurrently.

To manage incidents, you can perform various actions against them. You can also perform these same actions against a single incident on the [Incident Details](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details) page.

To perform actions:

1. On the**Incidents** page, select the checkbox next to one or more **Transaction ID**s on a single page to select the incidents.
2. From the **Actions** drop-down menu, select one of the following actions that you want to perform:
  - Assign DLP Admin
  - Assign to Me
  - Assign Priority
  - Close Incident
  - Notify User
  - Investigating
  - Escalate
  - Label
  - Update Incident Group

See image.

To assign a DLP admin for incidents:

1. Select **Assign DLP Admin**. The **Assign DLP Admin** window appears.
2. In the **Assign DLP Admin** window, you can:
  - **DLP Admin**: Select a DLP admin to assign to the incident. The drop-down menu displays only the DLP admins who have edit access to the incident groups. If you have selected two or more incidents, the menu displays only DLP admins who have edit access to at least one incident group of the selected incidents. An empty menu indicates that no DLP admin has edit access to the incident groups. Only DLP admins with full access to Workflow Automation can assign incidents to DLP admins with restricted access.
  - **Notes**: (Optional) Enter additional notes for the action.
3. Click **Assign**.

See image.

To assign incidents to yourself, select **Assign to Me**. The selected incidents are assigned to you.

To close incidents:

1. Select **Close Incident**. The **Close Incident** window appears.
2. In the **Close Incident**window, you can:
  - **Notes**: (Optional) Enter additional notes for the action.
  - **Resolution Label**: Select a resolution label and values associated with the label.
  - **False Positive**: If the incident is a false positive, select the **False Positive** checkbox.
3. Click **Close Incidents**.

See image.

After an incident is closed (status is **Resolved**), you can still perform all the other actions against the incident except for the **Investigating** and **Escalate** actions, but the incident status remains at **Resolved**.

To investigate incidents:

1. Select **Investigating**. The **Investigating**window appears.
2. (Optional) In the **Investigating**window, enter additional notes for the action.
3. Click **Submit**.

See image.

To assign or modify priority for incidents:

1. Select **Assign Priority**. The **Assign Priority** window appears.
2. In the **Assign Priority** window, you can:
  - **Priority**: From the drop-down menu, select the priority for the incidents.
  - **Notes**: (Optional) Enter additional notes for the action.
3. Click **Assign**.

See image.

To notify the current user about the incident:

1. Select **Notify User**. The **Notify User** window appears.
2. In the **Notify User** window, you can:
  - **Channel Type**: Select the channel type through which you want to send the incident notification to the end user. The current user for the incident is displayed in the **Current State Details** section of the [Incident Details](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details) page.
  - **Note to user**: (Optional) Enter additional notes for the action.
3. Click **Submit**.

See image.

To notify the user's manager or another approver about the incident:

1. Select **Escalate**. The **Escalate** window appears.
2. In the **Escalate** window, you can:
  - **User Type**: Select the type of user (**Manager** or **Approver)** to whom you want to escalate the incident. If you select the **Approver** user type, the **Approver** field appears, where you can select the approver of your choice for the incident.
  - **Channel Type**: Select the channel type through which you want to send the escalations to the user's manager or approver for further review.
  - **Note to user**: (Optional) Enter additional notes for the action.
3. Click **Submit**.

See image.

To add or remove labels for an incident:

1. Select **Label**. The **Add or Remove Label** window appears.
2. In the **Add or Remove Label** window, you can add or remove labels for an incident:
  - To add a label:
    1. Select **Add**.
    2. In the **Label** field, from the drop-down menu, select a label for the incident.
    3. In the **Value**field, from the drop-down menu, select the label value if values are associated with that label.
    4. Click the **Add** icon to input more labels and values to add to the incident.
    5. Click **Submit**.
  - To remove a label:
    1. Select **Remove**.
    2. In the **Label** field, from the drop-down menu, select a label for the incident.
    3. In the **Value**field, from the drop-down menu, select the label value if values are associated with that label.
    4. Click the **Add** icon to input more labels and values to be removed from the incident.
    5. Click **Submit**.

See image.

Click the **Delete** icon to remove a label-value pair for the incident.

You can use the Update Incident Group action to do the following:

- Add additional incident groups to multiple incidents.
- Delete one or more of the incident groups that are currently assigned to multiple incidents. Deleting incident groups might result in unassigned incidents and the removal of the admin assigned to the incident.
- Update the incident group that is used for assigning the admin to multiple incidents. When making this update, you can select one of the newly added incident groups, or you can select another one of the incident groups that was previously assigned to the incidents.
- Assign a default incident group to those unassigned incidents that might occur as a result of deleting an existing assigned incident group.

To update incident groups assigned to multiple incidents:

1. Select **Update Incident Group**. The **Update Incident Group** window appears, displaying the following information: See image.
  - In the **Available**section, all the incident groups that have been assigned to at least one admin appear in alphabetical order. The number of available incident groups appears in parentheses next to the heading. To learn more, see [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - In the **Assigned** section, the incident groups that are currently assigned to the incidents appear. The number of assigned incident groups appears in parentheses next to the heading.
2. (Optional) Add incident groups:
  1. (Optional) At the top of the window, use the search field to locate an incident group in the **Available** section. The search for incident groups spans across the **Available**, **Assigned**, and **Newly added** sections.
  2. In the **Available** section, click the **Add** icon next to each incident group that you want to add to the incidents. The incident group is moved from the **Available** section to the **Newly added** section. The number of newly added incident groups appears in parentheses next to the heading. In the **Newly added** section, you can also delete a newly added incident group by clicking the **Delete** icon next to an incident group. After you delete an incident group, that incident group is displayed in the **Available** section. You can also click **Reset** to reset the window to the original incident group settings. See image.
3. (Optional) Delete assigned incident groups. You can delete assigned incident groups even if you have not added new incident groups. In the **Assigned** section, click the **Delete** icon next to each incident group you want to delete. After you delete an incident group, that incident group is displayed in the **Available** section. See image. If you delete an assigned incident group in the **Assigned** section, and you have not added any new incident groups in the **Newly added**section, the **Assign Default Group** checkbox becomes available and is selected. Deleting an assigned incident group might result in unassigned incidents and the removal of the admin assignments for those incidents. Assigning a default group is only required if you delete an assigned incident group, and you have not added any new incident groups. You must have at least one incident group assigned to the incidents. If you delete all the assigned and newly added incident groups, a message appears, stating that at least one group must be assigned. You can also click **Reset** to reset the window to the original incident group settings.
4. If you deleted an assigned incident group in the **Assigned** section, and you did not add any new incident groups in the **Newly added**section, assign the default incident group to be used for those incidents that might become unassigned as a result of the assigned incident group deletion. The system automatically selects the **Assign Default Group** checkbox. From the **Incident Group** drop-down menu, select the default incident group to be used for assigning admins to those unassigned incidents. The menu lists all the incident groups that have been assigned to at least one admin, and they are displayed in alphabetical order. See image.
5. (Optional) Update the admin assignment for the incidents: You can update the admin assignment for the incidents to use one of the newly added incident groups or to use another one of the previously existing assigned incident groups.
  1. Select the**Update Admin Assignment** checkbox. The **Incident Group** field appears.
  2. From the **Incident Group** drop-down menu, select the incident group to be used for assigning the admin to the incidents. This menu lists all the incident groups that are displayed in the **Assigned** and **Newly added** sections of the window. See image.
6. (Optional) In the **Notes** field, enter additional notes for updating the incident groups.
7. Click **Update**. The **Incident**page appears. To see the updates, refresh the page. After refreshing the page, you can see the following updates:
  - The **Incident Groups**field for each incident displays the updated incident groups. Some of the incidents might display the default incident group if you assigned a default incident group.
  - The**Priority** field might change on the incidents based on the final list of incident groups that you assigned to the incidents. The priority for the incidents is derived from the incident groups assigned to the incidents. If the incident groups have different priorities, the highest priority is used.
  - If you updated the admin assignment to use a different incident group, the **DLP Admin** field for the incidents displays the name of the admin derived from that incident group.
  - If you assigned a default incident group to support unassigned incidents, the **DLP Admin** field for those incidents displays the name of the admin derived from the default incident group.
  - The **Last Change** field for each incident displays the latest state change that occurred for the incident group updates.

To perform bulk actions:

1. On the**Incidents** page, select the checkbox of the **Transaction ID** header on the incident table.
2. Click **Select All Incidents**. This action selects all incidents with or without filter criteria available across different pages on the incidents table.
3. From the **Actions** drop-down menu, select one of the following actions that you want to perform: See image.
  - Assign DLP Admin
  - Assign to Me
  - Close Incident
  - Label
  - Update Incident Group

Performing bulk action on numerous incidents takes time to complete. You can check the status of your bulk action on the [Bulk Actions](https://help.zscaler.com/workflow-automation/managing-bulk-actions) page. After you confirm the bulk action, and it is complete, Workflow Automation emails you a notification stating that your bulk action is successfully completed.

Only a maximum of three bulk activities (download incidents and bulk actions) can be in progress concurrently.

To assign a DLP admin for incidents:

1. Select **Assign DLP Admin**. The **Assign DLP Admin** window appears.
2. In the **Assign DLP Admin** window, you can:
  - **DLP Admin**: Select a DLP admin to assign to the incidents. The drop-down menu displays only the DLP admins who have edit access to the incident groups. An empty menu indicates that no DLP admin has edit access to the incident groups. Only DLP admins with full access to Workflow Automation can assign incidents to DLP admins with restricted access.
  - **Notes**: (Optional) Enter additional notes for the action.
3. Click **Assign**.

See image.

To close incidents:

1. Select **Close Incident**. The **Confirm Bulk Action**window appears.
2. In the **Confirm Bulk Action**window, you can:
  - **Notes**: (Optional) Enter additional notes for the action.
  - **Resolution Label**: Select a resolution label and values associated with the label.
  - **False Positive**: If the incident is a false positive, select the **False Positive** checkbox.
3. Click **Close Incidents**.

See image.

To add or remove labels for incidents:

1. Select **Label**. The **Add or Remove Label** window appears.
2. In the **Add or Remove Label** window, you can perform an add or remove labels bulk action for incidents:
  - To add a label:
    1. Select **Add**.
    2. In the **Label** field, from the drop-down menu, select a label for the incident.
    3. In the **Value**field, from the drop-down menu, select the label value if values are associated with that label.
    4. Click the **Add** icon to input more labels and values to add to the incident.
    5. Click **Submit**.
  - To remove a label:
    1. Select **Remove**.
    2. In the **Label** field, from the drop-down menu, select a label for the incident.
    3. In the **Value**field, from the drop-down menu, select the label value if values are associated with that label.
    4. Click the **Add** icon to input more labels and values to be removed from the incident.
    5. Click **Submit**.

See image.

Click the **Delete** icon to remove a label-value pair for the incident.

To assign incidents to yourself:

1. Select **Assign to Me**. The **Assign to Me**window appears.
2. (Optional) In the **Assign to Me**window, enter additional notes for the action.
3. Click **Assign to Me**.

See image.

You can use the Update Incident Group action to:

- Add additional incident groups to multiple incidents.
- Delete one or more of the incident groups that are currently assigned to multiple incidents. Deleting incident groups might result in unassigned incidents and the removal of the admin assigned to the incident.
- Update the incident group that is used for assigning the admin to multiple incidents. When making this update, you can select one of the newly added incident groups, or you can select another one of the incident groups that was previously assigned to the incidents.
- Assign a default incident group to those unassigned incidents that might occur as a result of deleting an existing assigned incident group.

To update incident groups assigned to multiple incidents:

1. Select **Update Incident Group**. The **Update Incident Group** window appears, displaying the following information: See image.
  - In the **Available**section, all the incident groups that have been assigned to at least one admin appear in alphabetical order. The number of available incident groups appears in parentheses next to the heading. To learn more, see [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - In the **Assigned** section, the incident groups that are currently assigned to the incidents appear. The number of assigned incident groups appears in parentheses next to the heading.
2. (Optional) Add incident groups: See image.
  1. (Optional) At the top of the window, use the search field to locate an incident group in the **Available** section. The search for incident groups spans across the **Available**, **Assigned**, and **Newly added** sections.
  2. In the **Available** section, click the **Add** icon next to each incident group that you want to add to the incidents. The incident group is moved from the **Available** section to the **Newly added** section. The number of newly added incident groups appears in parentheses next to the heading. In the **Newly added** section, you can also delete a newly added incident group by clicking the **Delete** icon next to an incident group. After you delete an incident group, that incident group is displayed in the **Available** section. You can also click **Reset** to reset the window back to the original incident group settings.
3. (Optional) Delete assigned incident groups. You can delete assigned incident groups even if you have not added new incident groups. In the **Assigned** section, click the **Delete** icon next to each incident group you want to delete. After you delete an incident group, that incident group is displayed in the **Available** section. See image. If you delete an assigned incident group in the **Assigned** section, and you have not added any new incident groups in the **Newly added**section, the **Assign Default Group** checkbox becomes available and is selected. Deleting an assigned incident group might result in unassigned incidents and the removal of the admin assignments for those incidents. Assigning a default group is only required if you delete an assigned incident group, and you have not added any new incident groups. You must have at least one incident group assigned to the incidents. If you delete all the assigned and newly added incident groups, a message appears, stating that at least one group must be assigned. You can also click **Reset** to reset the window back to the original incident group settings.
4. If you deleted an assigned incident group in the **Assigned** section, and you did not add any new incident groups in the **Newly added**section, assign the default incident group to be used for those incidents that might become unassigned as a result of the assigned incident group deletion. The system automatically selects the **Assign Default Group** checkbox. From the **Incident Group** drop-down menu, select the default incident group to be used for assigning admins to those unassigned incidents. The menu lists all the incident groups that have been assigned to at least one admin, and they are displayed in alphabetical order. See image.
5. (Optional) Update the admin assignment for the incidents: You can update the admin assignment for the incidents to use one of the new incident groups that you added or to use another one of the existing assigned incident groups.
  1. Select the**Update Admin Assignment** checkbox. The **Incident Group** field appears.
  2. From the **Incident Group** drop-down menu, select the incident group to be used for assigning the admin to the incidents. This menu lists all the incident groups that are displayed in the **Assigned** and **Newly added** sections of the window. See image.
6. (Optional) In the **Notes** field, enter additional notes for updating the incident groups.
7. Click **Update**. The **Incident**page appears, displaying the incident group updates for the incidents. To see the updates, refresh the page. After refreshing the page, you can see the following updates:
  - The **Incident Groups**field for each incident displays the updated incident groups. Some of the incidents might display the default incident group if you assigned a default incident group.
  - The**Priority** field might change on the incidents based on the final list of incident groups that you assigned to the incidents. The priority for the incidents is derived from the incident groups assigned to the incidents. If the incident groups have different priorities, the highest priority is used.
  - If you updated the admin assignment to use a different incident group, the **DLP Admin** field for the incidents displays the name of the admin derived from that incident group.
  - If you assigned a default incident group to support unassigned incidents, the **DLP Admin** field for those incidents displays the name of the admin derived from the default incident group.
  - The **Last Change** field for each incident displays the latest state change that occurred for the incident group updates.

To go to the [Incident Details](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details) page, click the **Transaction ID** of an incident on the **Incidents** page. You can view detailed information about each incident, such as incident ID, violation details, state changes, priority, and severity, and manage the incident.

See image.

To view the **Incident Details** page for an incident in a new tab of the same browser window, right-click the **Transaction ID** of an incident on the **Incidents** page, and select **Open in New Tab**. In the new tab, the **Incident Details** page appears, displaying the detailed information for the incident. You can perform actions on the incident on this page, and you can click the **Refresh** button at the top of the page to display the latest information for the incident. To learn more about the actions, see [Viewing & Managing Incident Details](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details).

See image.

To modify the number of incidents displayed per page, click the **Rows per page** drop-down menu and select the number of rows. Options are **10 rows**, **20 rows**, **25 rows**, **50 rows**, and **100 rows**. The default display is **10 rows**.

You can also configure the rows that are displayed on the page using the **Table Options** dialog window.

See image.

[Image: The Rows per page menu displaying the options available to set the number of incidents that the Incidents page displays]

[Image: Selecting the time range to apply for the incidents displayed on the Incidents page.]

[Image: Viewing the calendar and time columns that appear after selecting the Custom Date Range option in the Time Range drop-down menu on the Incidents page. The current date and time columns for hours, minutes, and seconds are highlighted.]

[Image: Viewing the calendar and time columns after a date and time have been selected for the start date. The start date (January 1) is highlighted on the calendar, the time (6:30 AM) is highlighted in the time options, and the start date field (2026-01-01 06:30:00) under the calendar is highlighted to show the date and time selected.]

[Image: Viewing the calendar and time columns after a date and time have been selected for the end date. The end date (January 15) is highlighted in the calendar, the time (6:30 AM) is highlighted in the time options, and the end date field (2026-01-15 06:30:00) is highlighted to show the date and time selected.]

[Image: Selecting a new DLP Admin for the selected incidents in the Assign DLP Admin window]

[Image: Assigning new priority for the selected incidents in the Assign Priority window]

[Image: Assigning Resolution Label and notes for resolving the incidents in the Close Incident window]

[Image: Selecting the User Type and Channel Type for escalating the incident to the manager or approver]

[Image: Selecting the Channel Type to send notifications about the incident to the end user]

[Image: Adding optional notes or information to start investigating the incident in the Investigating window]

[Image: Adding a Label and Value to the incidents in the Add or Remove Label window]

[Image: Bulk Actions - Selecting new DLP Admin for the all incidents in the Assign DLP Admin window]

[Image: Bulk Actions - Assigning incidents to yourself in the Assign To Me window]

[Image: Bulk Actions - Adding Resolution Labels and Values for closing incidents in the Confirm Bulk Action window]

[Image: Bulk Actions - Selecting Label and Value for incidents in the Add or Remove Label window]

[Image: Viewing the Update Incident Group window before incident groups are updated. The window contains an Available incident group section, an Assigned incident group section, an assign default group section, an update admin assignment section, and a Notes field.]

[Image: Video showing how to add and delete new incident groups in the Update Incident Group window]

[Image: Video showing how to delete assigned incident groups when new incident groups have been added in the Update Incident Group window]

[Image: Video showing how to assign the default incident group for incidents after deleting an assigned incident group when no new incident groups have been added in the Update Incident Group window]

[Image: Video showing how to update the admin assignment incident group for the incidents in the Update Incident Group window]

[Image: Viewing the Update Incident Group window before incident groups are updated. The window contains an Available incident group section, an Assigned incident group section, an assign default group section, an update admin assignment section, and a Notes field.]

[Image: Video showing how to add and delete new incident groups in the Update Incident Group window]

[Image: Video showing how to delete assigned incident groups when new incident groups have been added in the Update Incident Group window]

[Image: Video showing how to assign the default incident group for incidents after deleting an assigned incident group when no new incident groups have been added in the Update Incident Group window]

[Image: Video showing how to update the admin assignment incident group for the incidents in the Update Incident Group window]

[Image: Selecting Transaction ID to navigate from the Incidents page to the Incident Details page]

[Image: Viewing the Incidents page with the Open in New Tab option selected for an incident]

[Image: Viewing the Actions menu on the Incidents page when performing actions in bulk. The Actions menu has the following options: Assign DLP Admin, Assign To Me, Close Incident, Label, and Update Incident Group.]

[Image: Viewing the Actions menu on the Incidents page. The Actions menu has the following options: Assign DLP Admin, Assign To Me, Assign Priority, Close Incident, Notify User, Investigating, Escalate, Label, and Update Incident Group.]

[Image: Viewing the Incidents Page in the Zscaler Admin Console.]

[Image: Viewing the obfuscation message displayed on the Incidents page. Below the Actions drop-down menu and above the Incidents table a message reads "Some incidents may not display in search results due to the obfuscation settings".]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/managing-integration-users","lastmod":"2026-04-10T09:46Z","nid":"1457436"} -->
## Managing Integration Users

- Source: https://help.zscaler.com/workflow-automation/managing-integration-users
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Admin, User, & Role Management > Managing Integration Users
- Last modified: 2026-04-10T09:46Z
- Summary: How to manage integration users in Workflow Automation.

Adding integration users on the Integration Users page is required if you are integrating Workflow Automation with a ticketing integration application (e.g., ServiceNow or Jira Software). During the remediation of an incident on the Incident Details page in Workflow Automation, admins can perform a ticket action against an incident. This action creates and assigns a ticket in the ticketing integration application to a user and associates that ticket with the incident. When performing this ticket action, the admin must select a user who already exists on the Integration Users page. After the ticket is created in the ticketing integration application, the ticket information displays in the Ticket section on the Incident Details page. The Ticket section contains an incident link that enables the admins to access the ticketing integration application for that specific ticket if they have the appropriate credentials for the application.

The user assigned to the ticket can manage that ticket within the ticketing integration application itself.

## Adding Integration Users

To add an integration user:

1. Go to **Administration** > **Identity** > **Workflow Automation** > **Integration Users**. The **Integration Users** page appears, listing all the integration users who were added for your organization.
2. On the **Integration Users** page, click **Add More**. The **Add User** window appears.
3. In the **Add User** window: See image.
  1. **Integration**: From the drop-down menu, select the tenant ID associated with the ticketing integration application (e.g., ServiceNow or Jira Software).
  2. **Email**: Enter the email address for the user whom you want to associate with the ticketing integration application. You can only add users who are available in the ticketing integration application selected. After you enter the email address, the **Name** and **Username** fields appear in the window, displaying the information associated with that email address.
4. Click **Add User**.

## Viewing Integration Users

To view integration users:

1. Go to **Administration** > **Identity** > **Workflow Automation** > **Integration Users**. The **Integration Users** page appears, listing all the integration users who were added for your organization.
2. For each integration user, you can view the following information:
  - **Name**: The name of the user associated with the ticketing integration application.
  - **Email ID**: The email ID for the user.
  - **Integration Type**: The type of integration associated with the user. Integration types are **ServiceNow**and**JiraCloud**.
  - **Integration**: The tenant ID associated with the ticketing integration application (e.g., ServiceNow or Jira Software).

See image.

[Image: Integration Users Page - Add User window]

[Image: Integration Users Page - View All Existing Users]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/managing-labels","lastmod":"2026-07-14T11:56Z","nid":"1450111"} -->
## Managing Labels

- Source: https://help.zscaler.com/workflow-automation/managing-labels
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Incident Management > Managing Labels
- Last modified: 2026-07-14T11:56Z
- Summary: How to manage labels in Workflow Automation.

Adding labels is optional when configuring Workflow Automation. Admins with access to Workflow Automation can manage custom labels and the values associated with those labels. After you add labels, you can assign those labels to the different incidents that have occurred in your organization on the Incidents page and the Incident Details page. To learn more, see [Managing Incidents](https://help.zscaler.com/workflow-automation/managing-incidents) and [Viewing & Managing Incident Details](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details).

## Adding Labels

To add a label:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Labels**. The **Labels** page appears, listing all the labels for your organization. See image.
2. On the **Labels** page, click **Add More**.The **Add Label** window appears. See image.
3. In the **Add Label** window: See image.
  1. **Label Name**: Enter the name for the label.
  2. (Optional) **Label** **Values**: Click **Add New** and enter a value for the label. Then press `Tab` or press `Enter`. The value is added to the label.
  3. (Optional) Click **Add More**to add another value to the label.
4. Click **Add.**

## Editing Labels

To edit the label:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Labels**. The **Labels** page appears, listing all the labels for your organization.
2. (Optional) On the **Labels** page, use the **Search** field to locate the label for which you want to edit.
3. Click the **Edit** icon next to the label. The **Edit Label** window appears.
4. Change the **Label Name** field for the label or change the values for the label by doing one of these options:
  - To edit a label value, double-click an existing label value, edit the value, and then press `Tab` or press `Enter`.
  - To add another value to the label, click **Add More** in the **Label Values** field for the label, enter the new value, and then press `Tab` or press `Enter`.
  - To delete a value associated with the label, click the **Delete** icon next to a value that is displayed for the label.

See image.

1. Click **Update.**

To delete the label along with its associated values, click the **Delete** icon next to the label on the **Labels** page.

If you delete the entire label, there might still be incidents in Workflow Automation that were previously assigned to that label on the **Incidents** page or the **Incident Details** page. You need to manually find those incidents and remove the label that was deleted. To learn more, see [Managing Incidents](https://help.zscaler.com/workflow-automation/managing-incidents) and [Viewing & Managing Incident Details](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details).

## Viewing Labels

To view labels, go to **Administration** > **Workflow Automation** > **Data Protection** > **Labels**. The **Labels** page appears, listing all the labels for your organization. For labels, you can view the following information:

- **Label Name**: The name of the label.
- **Label Value**: The values associated with the label.

See image.

[Image: Viewing existing labels on the Labels page]

[Image: Viewing the Add Label window]

[Image: Viewing label values for a label in the Add Label window]

[Image: Viewing the Edit Label window]

[Image: Viewing existing labels on the Labels page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/managing-notification-templates","lastmod":"2026-04-10T13:27Z","nid":"1419961"} -->
## Managing Notification Templates

- Source: https://help.zscaler.com/workflow-automation/managing-notification-templates
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Notifications > Managing Notification Templates
- Last modified: 2026-04-10T13:27Z
- Summary: How to manage notification templates in Workflow Automation.

Adding notification templates in Workflow Automation is one of the tasks in configuring Workflow Automation. Admins with access to Workflow Automation must add and map the notification templates. Notification templates provide:

- The format for the notifications that Workflow Automation generates and sends when admins are investigating an incident. Notifications are sent when the admin asks the end user to justify actions that led to an incident and when the admin escalates the incident to an approver from the Incidents page or the Incident Details page.
- The format for the digest notifications that Workflow Automation sends to the users and the Data Loss Prevention (DLP) admins.
- The format for the notifications that are generated and sent from the various workflows defined in Workflow Automation.

To learn more, see [Managing Incident and Digest Template Mappings](https://help.zscaler.com/zia/managing-incident-and-digest-template-mappings), [Managing Incidents](https://help.zscaler.com/workflow-automation/managing-incidents), [Viewing & Managing Incident Details](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details), [Managing Workflow Templates](https://help.zscaler.com/workflow-automation/managing-workflow-templates), [Managing Workflows](https://help.zscaler.com/workflow-automation/managing-workflows), and [Managing Workflow Mappings](https://help.zscaler.com/workflow-automation/managing-workflow-mappings).

Workflow Automation provides the following system default notification template families and templates:

- Email Families and Templates
  | Notification Template Family | Notification Template |
  | --- | --- |
  | End-user Justification - Email Template | End-user Justification - Email Template |
  | Escalation - Email Template | Escalation - Email Template |
  | Digest - Email Template | Digest - Email Template |
  | DLP Admin Digest - Email Template | DLP Admin Digest - Email Template |
- Slack Families and Templates These families and templates are only available if you have integrated Workflow Automation with Slack.
  | Notification Template Family | Notification Template |
  | --- | --- |
  | End-user Justification - Slack Template | End-user Justification - Slack Template |
  | Escalation - Slack Template | Escalation - Slack Template |
  | Digest - Slack Template | Digest - Slack Template |
  | DLP Admin Digest - Slack Template | DLP Admin Digest - Slack Template |
- Microsoft Teams Families and Templates These families and templates are only available if you have integrated Workflow Automation with Microsoft Teams.
  | Notification Template Family | Notification Template |
  | --- | --- |
  | End-user Justification - Teams Template | End-user Justification - Teams Template |
  | Escalation - Teams Template | Escalation - Teams Template |
  | Digest - Teams Template | Digest - Teams Template |
  | DLP Admin Digest - Teams Template | DLP Admin Digest - Teams Template |

For each template family, you can create only one template in each language. You cannot create two templates of the same language for a single template family.

Admins cannot edit the system default notification templates. They can only view and clone the default notification templates.

On the Notification Templates page in the Zscaler Admin Console, admins can:

- View Notification Templates
- Add Notification Templates
- Preview Notification Templates
- Edit Notification Templates
- Translate Notification Templates
- Clone Notification Templates

To view notification templates:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Notification Templates**. The **Notification Templates**page appears.
2. (Optional) Filter the templates by status, type, template family, or language. You can also search for specific templates that you want to view.
3. (Optional) Reset all the applied filters.
4. View a list of notification templates configured for your organization. For each notification template, you can see: See image.
  - **Template Name**: The name of the template.
  - **Template Family**: The family of the template.
  - **Type**: The type of template. Template types are **Email**, **Slack**,and**Teams**. The Slack and Microsoft Teams templates are only available if you have integrated Workflow Automation with Slack and Microsoft Teams, respectively.
  - **Language**: The language of the template.
  - **Last Modified**: The date and time the template was last modified.
  - **Status**: The status of the template. Statuses are **Draft**, **Published**,and **System Default**.

[Image: Notification Templates Page - View All Notification Templates]

To add a notification template:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Notification Templates**. The **Notification Templates** page appears.
2. Click **Add More**. The **Notification Template** page appears. See image.
3. On the **Notification Template**page, you can create a custom notification template for the following types of notifications:
  - [Adding an Email Notification Template](https://help.zscaler.com/workflow-automation/adding-email-notification-templates)
  - [Adding a Slack Notification Template](https://help.zscaler.com/workflow-automation/adding-slack-notification-templates)
  - [Adding a Microsoft Teams Notification Template](https://help.zscaler.com/workflow-automation/adding-microsoft-teams-notification-templates)

[Image: Notification Templates Page - Add More Button]

To preview a notification template:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Notification Templates**. The **Notification Templates**page appears.
2. In the **Action** column next to a template, click the **View Template** icon. The **Notification Template** page appears, displaying the notification template. The following images show examples of an email notification template, a Slack notification template, and a Microsoft Teams notification template. See image.
3. (Optional) Click **Edit Template**. You are redirected to a page where you can modify and publish the notification template. To learn more, see Add Notification Templates. See image.
4. Click **Close**.

[Image: Notification Template Page - Previewing Email Notification Template]

[Image: Notification Template Page - Preview Slack Notification Template]

[Image: Notification Template Page - Preview Teams Notification Template]

[Image: Preview Notification Template Page - Edit Template]

You can only edit draft and published notification templates. You cannot edit or delete system default notification templates.

- Editing a Draft Notification Template
- Editing a Published Notification Template

To edit a draft notification template:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Notification Templates**. The **Notification Templates** page appears.
2. In the **Action** column next to a draft template, click the**Edit Template** icon. The **Notification Template** page appears, displaying the notification template. See image.
3. Edit the template details or the notification template design using the tools or format options provided for the template. To learn more, see Add Notification Templates.
4. (Optional) Click **Save as Draft**. The edited template appears on the **Notification Templates** page with a draft status. You can come back later and continue to work on the template design.
5. Click **Publish Template**. The template is published. Workflow Automation only uses published templates for its notifications.

To delete a draft notification template, click the **Delete Template** icon in the **Action** column next to the template.

[Image: Notification Template Page - Editing a Draft Notification Template]

To edit a published notification template:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Notification Templates**. The **Notification Templates** page appears.
2. In the **Action** column next to a published template, click the**Edit Template** icon. The **Notification Template** page appears, displaying the notification template. See image.
3. Edit the template details or the notification template design using the tools or format options provided for the template. To learn more, see Add Notification Templates.
4. Click **Update Template**. The published template is updated. The template still appears on the **Notification Templates** page with a published status.

To delete a published notification template, click the **Delete Template** icon in the **Action** column next to the template.

[Image: Notification Template Page - Editing a Published Notification Template]

To translate a notification template into a different language:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Notification Templates**. The **Notification Templates** page appears.
2. In the **Action** column next to a template, click the **Translate Template** icon. The **Notification Template** page appears, with the **Translate**dialogwindow. See image.
  1. In the **Translate** dialog window, from the **Translate To** drop-down menu, select a language to which you want to translate the template.
  2. To view the translated template, click **Preview**. The template displays in the selected language. See image.
  3. (Optional) To revert the template to the original language and also to revert any changes made after translation, click **Reset**. See image.
3. (Optional) Click **Save as Draft**. You can come back later and continue to work on the template.
4. Click **Publish Template**. The template is published. Workflow Automation only uses published templates for its notifications.

[Image: Notification Templates Page - Translate Icons]

[Image: Translate Dialog Window]

[Image: Notification Template Page - Revert Translations]

To clone a notification template:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Notification Templates**. The **Notification Templates** page appears.
2. In the **Action** column next to a template, click the **Clone Template** icon. The **Notification Template**page appears. In the **Template Name** field, "Clone of" appears in front of the name of the notification template that you cloned. See image.
3. (Optional) In the **Template Name** field, change the template name.
4. Edit the template details or the notification template design using the tools or format options provided for the template. To learn more, see Add Notification Templates.
5. (Optional) Click **Save as Draft**. The cloned template appears on the **Notification Templates** page with a draft status. You can come back later and continue to work on the template design. See image.
6. (Optional) Click **Translate** to translate the newly created template to a different language. The **Translate**dialogwindow appears.
  1. In the **Translate** dialog window, from the **Translate To** drop-down menu, select a language to which you want to translate the template.
  2. Click **Preview** to view the translated template. The template displays in the selected language.
  3. (Optional) To revert the template to the original language and also to revert any changes made after translation, click **Reset**.
7. Click **Publish Template**. The template is published. Workflow Automation only uses published templates for its notifications.

You can only update the [template mappings](https://help.zscaler.com/workflow-automation/managing-incident-and-digest-template-mappings) with templates that are in a published status. You also receive notifications from Workflow Automation to update the template mappings to use this template.

[Image: Notification Template Page - Cloning a Template]

[Image: Notification Templates Page - Cloned Notification Template]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/managing-priorities","lastmod":"2026-07-23T21:06Z","nid":"1418036"} -->
## Managing Priorities

- Source: https://help.zscaler.com/workflow-automation/managing-priorities
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Incident Management > Managing Priorities
- Last modified: 2026-07-23T21:06Z
- Summary: How to manage priorities in Workflow Automation.

Adding priorities to incident groups in Workflow Automation is one of the tasks for configuring Workflow Automation. Admins with access to Workflow Automation can add a priority to all the different incident groups for their organization. They can assign a priority of critical, high, medium, or low to an incident group.

After priorities have been assigned to incident groups, admins can then filter the incidents they are responsible for by priority on the Incidents page of the Zscaler Admin Console. To learn more, see [Managing Incidents](https://help.zscaler.com/workflow-automation/managing-incidents).

## Prerequisites

Before managing priorities for incident groups, add incident groups on the Incident Group page of the Zscaler Admin Console. To learn more, see [Managing Incident Groups](https://help.zscaler.com/zia/managing-incident-groups).

## Adding Incident Group Priorities

To add an incident group priority:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Priorities**. The **Priorities** page appears, listing all the incident groups and their assigned priorities for your organization.
2. On the **Priorities** page, click **Add More**. A new row appears after the last incident group that is displayed. See image.
3. In the new row:
  1. **Incident Group**: From the drop-down menu, select an incident group.
  2. **Priority**: Select a **Critical**, **High**, **Medium**, or **Low** priority for the incident group.
4. Click **Save**.

## Editing Incident Group Priorities

To edit an incident group priority:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Priorities**. The **Priorities** page appears, listing all the incident groups and their assigned priorities for your organization.
2. (Optional) On the **Priorities** page, use the **Search** field to locate the incident group for which you want to edit the priority.
3. Next to the incident group, select a different **Priority**. You can also use the **Incident Group** drop-down menu to change the incident group. If you change the incident group, the priority is removed from the previous incident group and added to the new incident group.
4. Click **Save**.

To delete an incident group priority configuration, click the **Delete** icon next to a priority configuration.

## Viewing Incident Group Priorities

To view incident group priorities:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Priorities**. The **Priorities** page appears, listing all the incident groups and their assigned priorities for your organization.
2. For each incident group, you can view: See image.
  - **Incident Group**: The name of the incident group.
  - **Priority**: The priority assigned to the incident group. Priorities are **Critical**, **High**, **Medium**, or **Low**.

[Image: Adding a priority to an incident group on the Priorities page in the Zscaler Admin Console]

[Image: Viewing incident groups and their assigned priorities on the Priorities Page in the Zscaler Admin Console]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/managing-roles-and-permissions","lastmod":"2026-04-10T09:22Z","nid":"1471471"} -->
## Managing Roles and Permissions

- Source: https://help.zscaler.com/workflow-automation/managing-roles-and-permissions
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Admin, User, & Role Management > Managing Roles and Permissions
- Last modified: 2026-04-10T09:22Z
- Summary: Information on how to configure Roles and Permissions for DLP admins in Workflow Automation.

Workflow Automation enables you to create roles and assign permissions for the admins to access the various features of the Workflow Automation application. Doing so provides you with the flexibility to control admin permissions based on roles. Permissions allow you to control an admin's access to the major features of Workflow Automation in the Zscaler Admin Console.

Configuring roles and permissions is one of the tasks you must complete before configuring admin assignments in the Zscaler Admin Console. During an admin assignment, only admins with full access to Workflow Automation can configure roles and assign them to admins who have restricted workflow access. You can assign multiple roles to an admin. To learn more, see [Managing Admins](https://help.zscaler.com/workflow-automation/managing-admins).

For each role, you can configure the following permissions for the different features of Workflow Automation:

- **Delete**: This role allows admins to delete the feature. This permission is only available for the Incidents feature.
- **Edit**: This role allows admins to edit the feature.
- **View**: This role only allows admins to view the feature. Admins cannot edit the data.
- **None**: This role provides admins with no access to the feature.

The Roles page provides the following default roles for the admins. You cannot edit or delete these roles. Admins with full access to Workflow Automation can create additional roles.

- **DLP Super Admin**: This role provides users with full access (i.e., edit access) to all features related to Data Loss Prevention (DLP) incidents. It can also delete incidents.
- **DLP Admin**: This role provides users with full access to all DLP incident-related features except Audit Logs, API Management, and Integrations.
- **DLP Incident Manager**: This role provides users with full access to incident-management features, such as viewing incident details and notifying users, as well as adding approvers.

If you are subscribed to Authentication Service, you can assign the configured roles to a user only from Authentication Service. To learn more, see [About Administrative Entitlements](https://help.zscaler.com/zidentity/about-administrative-entitlements).

On the Roles page, you can perform the following actions:

- Adding Roles and Permissions
- Editing Roles and Permissions
- Viewing Roles and Permissions

To add a role and configure permissions:

1. Go to **Administration** > **Admin Management** > **Role Based Access Control** > **Workflow Automation**. The **Roles** page appears, listing all the roles configured for your organization, including the default roles.
2. On the **Roles** page, click **Add More**. The **Add Role** window appears.
3. In the **Add Role** window:
  1. **Role**: Enter a name for the role.
  2. **Product**: The product is **DLP** by default, and you cannot change it.
  3. Select permissions for the following categories. For each category, you can assign **Delete**, **Edit**, **View**, or **None** access permissions: The delete access permission is only available for the Incidents category. The delete access permission is the highest permission and includes full access to the feature (i.e., permission to both edit and delete). See image.
    - **Analytics**: Enables you to manage the **Incidents Analytics** page, which allows you to monitor and analyze the incident data from a single location at an organizational level.
    - **Incidents**: Enables you to manage the **Incidents** and **Incident Details** pages, which capture the transactions that have violated the Data Protection policies.
    - **Workflows and Mappings**: Enables you to add workflows to enable remediation actions against an incident without manual user intervention. A workflow mapping specifies the incidents that are associated with the workflow.
    - **Workflow Templates**: Enables you to manage workflow templates, which you can use to create workflows for DLP incidents.
    - **Incident Group and Mappings**: Enables you to group individual incidents so that you can manage them from a single location. You can also map incident groups to one or more of the incident attributes available in an incident transaction.
    - **Priorities**: Enables you to assign a priority of critical, high, medium, or low to all the different incident groups for the organization.
    - **Notification Templates**: Enables you to add and map the notification templates to manage all incidents. Notifications are sent when an admin notifies the end user of an incident and escalates an incident to an approver.
    - **Survey Templates**: Enables you to configure the survey template format for the survey that a user or approver must complete when responding to an incident notification.
    - **Template Mappings**: Enables you to configure template mappings for incident and digest templates. The mappings determine which templates should be used for incident notifications and for the digest notifications.
    - **API Management**: Enables you to manage the **API Keys** page, which provides a repository where an organization can store created API keys.
    - **Audit Logs**: Enables you to filter and view records of all the admin actions that occurred in Workflow Automation and APIs.
    - **Labels**: Enables you to add and assign labels to the incidents that have occurred in your organization.
    - **Integrations**: Enables you to add integration users for integrating Workflow Automation with a ticketing application to create and assign tickets to a user.
    - **Account Settings**: Enables you to manage user digest notifications, DLP admin digest notifications, and globally unique identifiers (GUIDs) for your organization.
    - **Notification Center**: Enables you to access the **Notification Center**page, which displays alerts that affect the operation of Workflow Automation.
    - **Approvers**: Enables you to configure the approvers. During the investigation of an incident, you can escalate the incident to an approver for their review.
4. Click **Save.**

When managing roles, you can view roles and the details for a role.

- Viewing Roles
- Viewing Role Details

To view a role, go to **Administration** > **Admin Management** > **Role Based Access Control** > **Workflow Automation**. The **Roles** page appears, listing all the roles configured for your organization, including the default roles. For each role, you can view the following information:

- **Role**: The name of the role.
- **Edit Access**: The features for which edit access is enabled.
- **View Only Access**: The features for which view-only access is enabled.

See image.

To view role details:

1. Go to **Administration** > **Admin Management** > **Role Based Access Control** > **Workflow Automation**. The **Roles** page appears, listing all the roles configured for your organization.
2. In the **Action** column next to the role you want to view, click the **View** icon. The **View Permissions**window appears, displaying the details for the role. See image.

To edit a role:

1. Go to **Administration** > **Admin Management** > **Role Based Access Control** > **Workflow Automation**. The **Roles** page appears, listing all the roles configured for your organization.
2. In the **Action** column next to the role you want to edit, click the **Edit** icon. The **Edit Permissions** window appears. See image.
3. In the **Edit Permissions**window, modify permissions for any category.
4. Click **Save**.

To delete a role, click the **Delete** icon in the **Action** column next to the role and then click **Yes** in the dialog window that appears.

[Image: Add Role Window]

[Image: Roles page]

[Image: View Permissions Window]

[Image: Roles Page - Edit icon]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/managing-survey-templates","lastmod":"2026-04-30T11:43Z","nid":"1420016"} -->
## Managing Survey Templates

- Source: https://help.zscaler.com/workflow-automation/managing-survey-templates
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Notifications > Managing Survey Templates
- Last modified: 2026-04-30T11:43Z
- Summary: How to manage survey templates in Workflow Automation.

Adding survey templates is one of the tasks in configuring Workflow Automation. Admins with access to Workflow Automation can add and map survey templates. A survey template provides the format for the survey that a user or approver must complete when responding to an incident notification from Workflow Automation. The survey includes questions that, when answered, provide the justification for the incident.

When an admin configures a notification template, they can choose to format a link in the notification template that enables the user or approver to view the incident details and the survey template where they can enter the response to the notification. Then, when a user or approver receives a notification that is using that notification template, they can click that link, which opens the Incidents page and the survey that they must complete for the incident. The template settings on the Template Mappings page determine the notification template and the survey templates that Workflow Automation uses for the different source Data Loss Prevention (DLP) types and notification types. To learn more, see [Managing Incident and Digest Template Mappings](https://help.zscaler.com/workflow-automation/managing-incident-and-digest-template-mappings).

Workflow Automation provides the following system default survey template families:

- End-user Justification - Questionnaire Template
- Escalation - Questionnaire Template

For each template family, you can create only one template in each language. You cannot create two templates of the same language for a single template family.

Admins cannot edit the default survey templates. They can only view and clone the system default survey templates.

On the Survey Templates page in the Zscaler Admin Console, admins can:

- View Survey Templates
- Add Survey Templates
- Preview Survey Templates
- Edit Survey Templates
- Translate Survey Templates
- Clone Survey Templates

To view existing survey templates:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Survey Builder**. The **Survey Templates** page appears.
2. (Optional) Filter the templates by status, type, template family, or language. You can also search for specific templates that you want to view.
3. (Optional) Click the **Reset** icon to reset all the applied filters.
4. View a list of survey templates configured for your organization. For each survey template, you can see the following: See image.
  - **Template Name**: The name of the template.
  - **Template Family**: The family of the template.
  - **Type**: The type of template. The type of template is always **Questionnaire**.
  - **Language**: The language of the template.
  - **Last Modified**: The date and time the template was last modified.
  - **Status**: The status of the template. Statuses are **Draft**, **Published**, and **System Default**.

To add a survey template:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Survey Builder**. The **Survey Templates** page appears.
2. On the **Survey Templates** page, click **Add More**. The **Survey Template** page appears. See image.
3. On the **Survey Template** page: After you select the template type, the survey creator section appears below the row. The survey creator section contains a **Designer** tab and a **Preview** tab and contains a placeholder drop-down type question titled Reason. Workflow Automation uses the Survey Creator component from SurveyJS. Using this drag-and-drop survey builder, you can create a survey template, change the content, include pictures, and link to files. In addition, you can change the template's structure and style. See image.
  - **Template Name**: Enter a name for the template.
  - **Template Family**: From the drop-down menu, select a family for the template or add a new template family.
  - **Type**: From the drop-down menu, select **Questionnaire**.
  - **Source Language**: From the drop-down menu, select the language for the template.
4. Click the **Designer** tab.
5. On the**Designer**tab of the survey creator, edit the choices for the placeholder question with values for your organization.
6. Configure additional items for the survey template using one or more of the question and panel types in the toolbox: The survey template must contain at least one drop-down panel type question with the name of Reason.
  - **Radio Button Group**: Adds a radio button group to the template design. Use this type for questions that can have multiple options but accept only one answer.
  - **Rating Scale**: Adds a rating selection to the template design. Use this type if you want respondents to enter a rating.
  - **Checkboxes**: Adds checkboxes to the template design. Use this type for questions that accept multiple answers.
  - **Dropdown**: Adds a drop-down menu to the template design. Use this type for questions that can have multiple options but accept only one answer. With this type, you can display more options while occupying less screen space.
  - **Multi-Select Dropdown:**Adds a drop-down menu to the template design. Use this type for questions that can have multiple options but can accept more than one answer from the drop-down list.
  - **Yes/No (Boolean)**: Adds a Boolean editor to the template design. Use this type to have respondents switch the Boolean editor to **Yes** or **No**.
  - **File Upload**: Adds a file upload area to the template design. Use this type to allow respondents to upload files.
  - **Image Picker**: Adds an image or video to the template design. This type is used for presentation only.
  - **Ranking**: Adds a ranking list to the template design. Use this type for questions in which respondents must set the order of items.
  - **Single-Line Input**: Adds a single-line text editor to the template design. Use this type for open-ended questions that require short answers.
  - **Long Text**: Adds a multi-line text editor to the template design. Use this type for open-ended questions that accept multiple line answers.
  - **Multiple Textboxes**: Adds multiple single-line text editors to the template design. Use this type for open-ended questions that require more than one short answer.
  - **Panel**: Adds a panel container for other questions and panels to the template design. Use this type to group several questions or panels and control them all together.
  - **Dynamic Panel**: Adds a dynamic panel that can contain multiple questions to the template design. Respondents can add and remove panels based on the template.
  - **Single-Select Matrix**: Adds a single-choice matrix to the template design. Use this type to display radio buttons in rows and columns. Respondents can select only one radio button in each row.
  - **Multi-Select Matrix**: Adds a multiple-choice matrix to the template design. Use this type to display rows and columns. At their intersections, the matrix can display the following editors: Respondents use these editors to select a desired value in each cell.
    - Dropdown
    - Checkboxes
    - Radio Button Group
    - Single-Line Input
    - Long Text
    - Yes/No (Boolean)
    - Expression
    - Rating Scale
  - **Dynamic Matrix**: Adds a dynamic matrix to the template design. This type is similar to the Multi-Select Matrix, but respondents can add and remove matrix rows.
  - **HTML**: Adds HTML to the template design. Use this type to format text as needed, include links, and insert media or other custom elements. This type is used for presentation only.
  - **Expression (read-only)**: Adds an expression to the template design. Use this type to calculate values and present them to respondents.
  - **Image**: Adds an image or video to the template design. This type is used for presentation only.
  - **Signature**: Adds a signature area to the template design. Use this type to obtain the respondent's signature or any hand-drawn input.
7. (Optional) Click **Save as Draft**. The template is saved with the status as **Draft**. You can come back later and continue to work on the template design.
8. (Optional) Click **Translate** to translate the newly created template to a different language. The **Translate**window appears.
  1. In the **Translate** window, from the **Translate To** drop-down menu, select a language to which you want to translate the template.
  2. Click **Preview** to view the translated template. The template displays in the selected language.
  3. (Optional) To revert the template to the original language and also to revert any changes made after translation, click **Reset**.
9. Click **Publish Template**. The template is published, and the status is **Published**. Workflow Automation only uses published templates for its surveys.

To preview a survey template:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Survey Builder**. The **Survey Templates** page appears.
2. In the **Action** column next to an existing template, click the **View Template** icon. The **Survey Template** page appears, displaying the survey template on the **Designer** tab. See image.
3. On the **Survey Template** page, click the **Preview** tab to view the survey template in its rendered format. See image.
4. (Optional) Click **Edit Template**. You are redirected to the **Designer** tab of the **Survey Template** page, where you can modify and publish the survey template.
5. Click **Close**.

To edit a survey template:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Survey Builder**. The **Survey Templates** page appears.
2. In the**Action** column next to an existing published or draft template, click the **Edit Template** icon. You cannot edit a system default template. The **Survey Template** page appears, displaying the survey template on the **Designer** tab. See image.
3. Edit the survey template details or the survey template design using one or more of the question and panel types in the toolbox.
4. (Optional) Click **Save as Draft**. You can come back later and continue to work on the template design. This button is not available for published templates.
5. Click **Publish Template**. The template is published. Workflow Automation only uses published templates for its surveys.

To delete a survey template, in the **Action** column next to an existing template, click the **Delete Template** icon. You cannot delete a system default template.

You can translate a published, system default, or draft template.

To translate a survey template into a different language:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Survey Builder**. The **Survey Templates** page appears.
2. In the**Action** column next to a template, click the **Translate Template** icon. The **Survey Template** page appears, with the **Translate**window. See image.
  1. In the **Translate** window, from the **Translate To** drop-down menu, select a language to which you want to translate the template.
  2. To view the translated template, click **Preview**. The template displays in the selected language. See image.
  3. (Optional) To revert the template to the original language and also to revert any changes made after translation, click **Reset**.
3. (Optional) Click **Save as Draft**. You can come back later and continue to work on the template.
4. Click **Publish Template**. The template is published. Workflow Automation only uses published templates for its surveys.

You can clone a published, system default, or draft template.

To clone a survey template:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Survey Builder**. The **Survey** **Templates** page appears.
2. In the **Action** column next to a template, click the **Clone Template** icon. The **Survey Template** page appears. In the **Template Name** field, "Clone of" appears in front of the name of the survey template that you cloned. See image.
3. (Optional) In the **Template Name** field, change the template name.
4. Edit the template details or the survey template design using one or more of the question and panel types in the toolbox.
5. (Optional) Click **Save as Draft**. You can come back later and continue to work on the template design. See image.
6. (Optional) Click **Translate** to translate the newly created template to a different language. The **Translate**window appears.
  1. In the **Translate** window, from the **Translate To** drop-down menu, select a language to which you want to translate the template.
  2. Click **Preview** to view the translated template. The template displays in the selected language.
  3. (Optional) To revert the template to the original language and also to revert any changes made after translation, click **Reset**.
7. Click **Publish Template**. The template is published. Workflow Automation only uses published templates for its surveys.

You can only update the [template mappings](https://help.zscaler.com/workflow-automation/managing-incident-and-digest-template-mappings) with templates that are in a published status. You also receive notifications from Workflow Automation to update the template mappings to use this template.

[Image: Survey Templates Page - Viewing Templates]

[Image: Survey Templates Page - Add More Button]

[Image: Survey Template Page - Adding a Template]

[Image: Survey Template Page - Viewing a Template]

[Image: Survey Template Page - Previewing a Template]

[Image: Survey Template Page - Editing a Template]

[Image: Survey Template Page - Cloned Template]

[Image: Survey Templates Page - Viewing a Survey Template with a Draft Status]

[Image: Viewing the Translate Template Icons on the Survey Templates Page]

[Image: Viewing the Language Drop-Down Menu and Preview Button in the Translate Window for Survey Templates]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/managing-user-attributes","lastmod":"2026-04-10T09:44Z","nid":"1503486"} -->
## Managing User Attributes

- Source: https://help.zscaler.com/workflow-automation/managing-user-attributes
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Admin, User, & Role Management > Managing User Attributes
- Last modified: 2026-04-10T09:44Z
- Summary: Information on how to use and manage the User Attributes page in Workflow Automation.

Workflow Automation fetches the end user information (i.e., user attributes) that is displayed in the Zscaler Admin Console, such as the employee ID, email address, and manager's name, from the following two data sources:

- **SCIM**: The System for Cross-domain Identity Management is a standard protocol that you can use to manage user attributes with the Zscaler service. You can use SCIM for automatically updating user information on the Workflow Automation database to reflect changes in your user directory. To learn more, see [Understanding SCIM](https://help.zscaler.com/zia/understanding-scim).
- **CSV**: Comma Separated Value (CSV) is used to manually import user attributes as a `.csv` file to be displayed in the Zscaler Admin Console.

On the User Attributes page, you can import the end user attributes as CSV files. Workflow Automation provides access to predefined CSV template versions and instructions on how to construct your CSV file with your organization's end user information. You can download and modify any one of the available predefined CSV template versions, based on your requirements, and import it into the Zscaler Admin Console.

Only a valid CSV file that is constructed based on one of the CSV template versions and the CSV template instructions can be imported into Workflow Automation.

To view the user attributes imported through a CSV file in the Zscaler Admin Console, you must select **CSV** as the primary data source and select a unique identifier on the [Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) page. Otherwise, SCIM protocol is selected as the default primary data source. After you select **CSV** as the primary user data source, Workflow Automation displays only the user attributes available in the CSV file. If a user attribute is missing from the CSV file, Workflow Automation does not populate that data.

The unique identifiers (email address and employee ID) are used to identify a specific user in the CSV file. If the selected unique identifier is not available in the CSV file, Workflow Automation uses the other (unselected) unique identifier to locate the user in the CSV file. When neither unique identifier is available in the imported CSV file, Workflow Automation fetches that user information from the SCIM protocol. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings).

## Managing User Attributes CSV Files

- Import CSV Files
- View Imported CSV Files
- Restart CSV File Import
- Download a CSV File

Importing a CSV file with numerous user attributes takes time to complete. You receive an email notification when your CSV file import into Workflow Automation is completed, partially completed, or has failed.

To import the user attributes as a CSV file:

1. Go to **Administration** > **Identity** > **Workflow Automation** > **User Attributes**.
2. Click **Import CSV**.
3. On the**Import CSV** page, complete the following sections: See image.
  1. **Prerequisite**: Complete the prerequisites to import a CSV file.
    - If you are importing a CSV file for the first time, you must set the primary user data source and a unique identifier for your users. To update the settings: To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings).
      1. Click **Account Settings**. The **Account Settings** page appears.
      2. Select **CSV** as the **Primary User Data Source** and select a **Unique Identifier** for the users.
    - If the Primary User Data Source and the Unique Identifier are already available, then the selected settings are displayed in this section. To modify the settings:
      1. Click **Account Settings**. The **Account Settings** page appears.
      2. Modify the **Primary User Data Source**. After you import your first CSV file to the **User Attributes** page, you cannot change the selected Unique Identifier. To update your unique identifier settings, contact Zscaler Support. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings).
  2. **How does this work?**: This section displays the high-level process of importing the CSV file of user attributes into Workflow Automation.
  3. **Resource**: This section provides the **CSV Template Instruction** drop-down from where you can download the instructions on how to construct the user attributes CSV as a `.pdf` file. This file provides the actual user attributes, the format of the attributes, the maximum character limitations (e.g., User Name (50 maximum characters) and User Email (320 maximum characters)), and example values for the CSV file.
  4. **Recommendations for the CSV file**: This section displays the limitations and general recommendations for the user attributes CSV file. You can download any one of the CSV template versions and modify it with your organization's user information.
  5. Drag and drop or select the CSV file from your local folder. Select the version type of the file and then click **Validate**. Workflow Automation validates the format of the file against the selected version. After the validation is complete, click **Import** to import the file. If the file is empty or has incorrect data, the CSV file fails validation. You receive a link to download the list of the errors in your file. Fix the errors and import the file again. See image.

After the CSV file import is complete, you can view the file on the **User Attributes**page.

For CSV file imports that are partially successful or failed, you can restart the import action.

To restart importing a CSV file:

1. Go to **Administration** > **Identity** > **Workflow Automation** > **User Attributes**. The **User Attributes** page appears, listing all the CSV files uploaded.
2. For a partially complete CSV file import, in the **Actions** column, click the **Resume** icon to restart the action. The import action restarts, and the progress is displayed in the **Status** column. See image.
3. For a failed CSV file import, in the **Actions** column, click the **Retry** icon to restart the action. The import action restarts, and the progress is displayed in the **Status** column. See image.

You can only restart an import action a maximum of three times. If you reach the maximum number of attempts, import the CSV file again on the **User Attributes** page.

See image.

To download a previously uploaded CSV file:

1. Go to **Administration** > **Identity** > **Workflow Automation** > **User Attributes**. The **User Attributes** page appears, listing all the CSV files uploaded.
2. For the CSV file you want to download, in the **Actions** column, click the**Download** icon. The CSV file is downloaded onto your local system.

See image.

To view the user attributes CSV files, go to **Administration** > **Identity** > **Workflow Automation** > **User Attributes**. The **User Attributes** page appears.

On the **User Attributes** page, you can do the following:

1. Import user attributes as a CSV file.
2. Search for an imported user attributes CSV file.
3. View a list of imported CSV files. For each CSV file, you can view the following information:
  - **File Name**: The name of the imported CSV file.
  - **Imported By**: The username who imported the CSV file.
  - **Initiated On**: The date and time when the CSV file import was initiated.
  - **Completed On**: The date and time when the CSV file import was completed.
  - **No. of New Users**: The number of users added through the CSV file.
  - **No. of Updated Users**: The number of users updated through the CSV file.
  - **Size**: The size of the CSV file in bytes.
  - **Status**: The import status of the CSV file. The statuses are complete, partially complete, and failed. Hover over the**Information** icon next to the status to view the details of each CSV file import. See image.
4. Download a previously uploaded CSV file.
5. Resume importing a partially complete CSV file.
6. Retry importing a failed CSV file.
7. Delete a previously uploaded CSV file.

[Image: Screenshot of the main User Attributes page]

[Image: Viewing the Download icon on the User Attributes page.]

[Image: Viewing the Resume icon on the User Attributes page]

[Image: Viewing the Retry icon on the User Attributes page]

[Image: Information icon on the User Attributes page]

[Image: Message - maximum number of attempts to restart action]

[Image: Viewing the error message with the download error list link on the Import CSV page.]

[Image: Viewing the Import CSV page. The different sections (Prerequisite, Recommendations for the CSV file, How does this work?, file upload, and Resource) in the page are highlighted.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/managing-workflow-automation-api-keys","lastmod":"2026-05-01T06:17Z","nid":"1452006"} -->
## Managing Workflow Automation API Keys

- Source: https://help.zscaler.com/workflow-automation/managing-workflow-automation-api-keys
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > API Management > Managing Workflow Automation API Keys
- Last modified: 2026-05-01T06:17Z
- Summary: Information on how to manage API keys in the Zscaler Admin Console.

If you have a subscription to Workflow Automation, API Management is automatically accessible to an admin role. An admin can create and display the API key required for authenticating with the Workflow Automation API to make API calls.

The API Keys page allows you to perform the following actions:

- Create a New API Key
- Disable an API Key
- Regenerate an API Key
- Delete an API Key

To learn more, see [About API Keys](https://help.zscaler.com/workflow-automation/about-api-keys).

The **Create API Key** action allows you to create API keys for an admin. An API key must be created for authentication to make API calls.

To create a new API key:

1. Go to **Administration**>**API Configuration**>**Legacy API**>**Workflow Automation API**.The **API Keys** page appears, listing the API keys.
2. Click **Add More**. The **Create API Key** window appears.
3. In the **Create API Key** window:

- **Name**: Enter the name for the API key.
- **Expiration Time**: (Optional) Enable to set an expiration time for the API key.
- **Lifetime (days)**: If you have enabled **Expiration Time**, enter the number of days that you want the key to be valid.

If the **Expiration Time** option is disabled, the API key has no expiration time.

See image.

1. Click **Submit**. The **API Key** window appears.
2. In the **API Key** window, click the **Copy** icon to copy the **Key Secret**and the **Key ID**. You need both for authentication.

The key secret is only available to copy when creating an API key. Copy and save the key secret for authentication.

See image.

1. Click **Done** to close the window.

When created, the API key is enabled by default. You can use the blue toggle corresponding to a specific API key to disable that key.

To disable an API key:

1. Go to **Administration**>**API Configuration**>**Legacy API**>**Workflow Automation API**. The **API Keys** page appears, listing the API keys.
2. On the **API Keys** page, click the blue toggle in the **Action** column to disable a specific API key.

See image.

The API key is disabled and cannot be used for authentication. You can use the same action to enable the API key when needed.

You can generate a new key secret for the same admin and the key ID.

To regenerate an API key:

1. Go to **Administration**>**API Configuration**>**Legacy API**>**Workflow Automation API**. The **API Keys** page appears, listing the API keys.
2. On the **API Keys** page, click the **Regenerate** icon corresponding to a specific API key.
3. Click **Yes** in the pop-up window that appears.

See image.

A new key secret for the API key is generated.

The **Delete** action allows you to delete the specified API key.

To delete an API key:

1. Go to **Administration**>**API Configuration**>**Legacy API**>**Workflow Automation API**. The **API Keys** page appears, listing the API keys.
2. On the **API Keys** page, click the **Delete** icon for a specific API key.
3. Click **Yes**in the pop-up window that appears.

See image.

The API key is deleted permanently.

[Image: Create API Key Window]

[Image: API Key Window]

[Image: API Keys Page - Enable or Disable Toggles in Action Column]

[Image: API Keys Page - Regenerate Icon]

[Image: API Keys Page - Delete Icon]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/managing-workflow-automation-integration-jira-software","lastmod":"2026-07-21T13:52Z","nid":"1461951"} -->
## Managing Workflow Automation Integration with Jira Software

- Source: https://help.zscaler.com/workflow-automation/managing-workflow-automation-integration-jira-software
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Incident Management > Integrations > Managing Workflow Automation Integration with Jira Software
- Last modified: 2026-07-21T13:52Z
- Summary: Information about Workflow Automation integration with Jira Software.

Workflow Automation can integrate with Jira Software, a sanctioned Software as a Service (SaaS) application for Zscaler. During the remediation process for a data protection incident in Workflow Automation, admins can create and assign a Jira ticket to an incident on the Incident Details page. When the ticket action is initiated on the Incident Details page for an incident, the admin selects the user to assign the ticket to in Jira Software and specifies the Jira project where the ticket is created in Jira Software. The user they select must exist in Jira Software and must have already been added to the Integration Users page in Workflow Automation. To learn more, see [Managing Integration Users](https://help.zscaler.com/workflow-automation/managing-integration-users) and [Viewing & Managing Incident Details](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details).

When configuring the Jira Software integration in Workflow Automation, you can select whether you want to sync the project list and the ticket status between Jira Software and Workflow Automation. If you choose to sync the ticket status, you can also select whether to close the incident in Workflow Automation when the ticket is completed in Jira Software. When configuring the Jira Software integration in Workflow Automation, you can specify the Jira complete status to use for the ticket closure. After Workflow Automation receives the specified complete status through the sync process, it automatically closes the incident.

The workflow functionality in Workflow Automation also has an Auto Create Tickets template that you can use to create tickets in Jira Software. To learn more, see [Managing Workflow Templates](https://help.zscaler.com/workflow-automation/managing-workflow-templates).

## Integrating Workflow Automation with Jira Software

Before you can integrate Workflow Automation with Jira Software, you must:

- Configure the Data Loss Prevention (DLP) application integration for your organization using Amazon Web Services, Azure, or Google Cloud Platform. Ensure that you add a DLP application integration in Workflow Automation during this process. To learn more, see [Configuring the DLP Application Integration Using Amazon Web Services](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-amazon-web-services), [Configuring the DLP Application Integration Using Azure](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-azure), and [Configuring the DLP Application Integration Using Google Cloud Platform](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-google-cloud-platform).
- Obtain and configure Jira Software for your organization.
  - Ensure that you define the different users for your organization who can use Jira Software.
  - Ensure that you have admin credentials for Jira Software. These credentials are required to integrate Workflow Automation with Jira Software.

To learn more, refer to the [Jira Software documentation](https://confluence.atlassian.com/jirasoftware/jira-software-documentation-774242447.html).

To integrate Workflow Automation with Jira Software:

1. In the Zscaler Admin Console, go to **Policies** > **Common Configuration** > **Out-of-Band CASB** > **SaaS Application Tenants**.
2. Click **Add SaaS Application Tenant**. The **Add SaaS Application Tenant** page appears.
3. On the **Add SaaS Application Tenant** page, configure and add Jira Software as a SaaS application tenant using the Jira Software admin credentials. When adding the tenant, ensure that you select the **Workflow Automation** checkbox under the **Onboard SaaS Application for** section. This process authorizes Jira Software with Internet & SaaS (ZIA) and integrates Jira Software with Workflow Automation. On the **Integrations** dashboard in Workflow Automation, the Jira Cloud tile appears in the **Connected Apps** section. To learn more, see [Adding SaaS Application Tenants](https://help.zscaler.com/zia/adding-saas-application-tenants).

See image.

## Managing Jira Software Integrations in Workflow Automation

On the Integrations dashboard in the Zscaler Admin Console, admins can:

- Configure Jira Software Integrations
- Edit Jira Software Integrations
- Disable or Enable Jira Software Integrations
- View Jira Software Integrations

To configure a Jira Software integration in Workflow Automation:

- 1. Configure project settings for a Jira Software integration.
- 2. Configure integration settings for a Jira Software integration.

Before you can create a Jira Software ticket on the Incident Details page in Workflow Automation, you must configure the project settings for the Jira projects in Workflow Automation. This process includes defining the field mappings for the project and specifying a complete status for the project. To learn more, see [Viewing & Managing Incident Details](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details).

To configure project settings for a Jira Software integration in Workflow Automation:

1. Go to **Administration**>**Workflow Automation**>**Data Protection** > **Integrations**. The **Integrations** dashboard appears, displaying a **Jira Cloud** application tile in the**Connected Apps** section. The **Tile View** icon is selected by default.

See image.

1. Perform one of the following steps:
  - In the tile view, in the **Connected Apps**section, click anywhere on the **Jira Cloud** tile.
  - In the list view, in the **Connected Apps** section, click **View Details**next to the Jira Cloud integration.

The **Jira Integration** details page appears. In the **Connected Accounts** section, all the Jira Cloud integrations appear along with their statuses (Enabled and Disabled). To display the deleted application integrations, click **Show Deleted Accounts** at the top right of the **Connected Accounts** section.

See image.

1. On the **Jira Integration** details page, click the **Expand** icon for a connected account. The Jira projects display along with their metadata associated with the Jira Cloud integration. When you enable the Jira Cloud integration for the first time, all the projects and their metadata for the Jira Cloud integration are synced with Workflow Automation and are displayed on the page.

See image.

1. (Optional) At the top right of the project details section, click **Sync Project List**to manually sync the Jira Cloud integration project list with Workflow Automation.
2. In the project details section, configure the complete status settings for each project associated with the Jira Cloud account. From the **Complete Status** drop-down menu, select a status for when the Jira tickets are considered complete in the project in the Jira Software application. If a status has not been selected, **Click to change status** appears for the **Complete Status** field. All the completed statuses for the project in Jira Software are available for selection—for example, **In Progress**, **To Do**, **In Review**, **Done**, and **Closed**.

See image.

1. Enter the field mapping for the Jira tickets in the project between the Jira Cloud integration and Workflow Automation.
  1. Click the **Add Field Mapping** icon next to a project. The **Required Fields Mappings** page appears.

See image.

1. On the **Required Fields Mappings** page, select an issue type from the **Issue Type** drop-down menu. The menu displays the issue types available for that project in the Jira Cloud account (e.g., **Task**, **Bug**, and **Epic**). After you select an issue type, all the required Jira fields for that project and issue type are listed on the page and cannot be deleted. The system automatically maps the Issue Type Jira field for you. For the **Issue Type** field, the **Zscaler Fields**value is set to **None** and the **Default Value** is set to the same value as the issue type that you selected. You cannot edit or delete the Issue Type Jira field mapping.
2. Enter the field mapping for all the other required Jira fields:
  - Next to the required field:
    - If applicable, from the **Zscaler Fields** drop-down menu, select the Zscaler field that you want to map to that Jira field. The drop-down menu displays all Zscaler fields that are available on the Incident Details page.
    - If applicable, set a default value that you want to map to that Jira field. Enter the **Default Value** field or select the default value from the **Default Value** drop-down menu. Some fields that have predefined values have a drop-down menu that you can select from, and other fields allow you to enter a value.
  - Click **Save**. The field mapping is configured. After you map the last required field and save it, the **Add More** button becomes available. You can only edit a required field mapping. You cannot delete required field mappings.

See image.

1. (Optional) Add optional Jira field mappings for the ticket:
  1. Click the **Add More** button. An additional row appears at the bottom of the table.
  2. In the new row:
    - From the **Jira Fields** drop-down menu, select the optional Jira field.
    - If applicable, from the **Zscaler Fields** drop-down menu, select the Zscaler field to map to this Jira field. All potential Zscaler fields that match this Jira field are available in the drop-down menu.
    - If applicable, enter the default value that you want to map to that Jira field in the **Default Value** field or select the default value from the **Default Value** drop-down menu. Some fields that have predefined values have a drop-down menu that you can select from, and other fields allow you to enter a value.
  3. Click **Save**at the end of the row. The field mapping is configured.

See image.

You can edit or delete optional field mappings.

To configure integration settings for a Jira Software integration in Workflow Automation:

1. Go to **Administration**>**Workflow Automation**>**Data Protection** > **Integrations**. The **Integrations** dashboard appears, displaying a **Jira Cloud** application tile in the**Connected Apps** section. The **Tile View** icon is selected by default.

See image.

1. Perform one of the following steps:
  - In the tile view, in the **Connected Apps**section, click anywhere on the **Jira Cloud** tile.
  - In the list view, in the **Connected Apps** section, click **View Details**next to the Jira Cloud integration.

The **Jira Integration** details page appears. In the **Connected Accounts** section, all the Jira Cloud integrations appear along with their statuses (Enabled and Disabled). To display the deleted application integrations, click **Show Deleted Accounts** at the top right of the **Connected Accounts** section.

See image.

1. On the **Jira Integration** details page, click the **Edit** icon next to the connected account you want to configure. The **Jira Integration** editing page appears.
2. On the **Jira Integration** editing page:
  1. (Optional) **Integration Name**: Edit the integration name.
  2. (Optional) **Sync project list**: Select this checkbox if you want to automatically sync the project list between the Jira Cloud integration and Workflow Automation. The sync process runs daily. To manually sync the project list, on the **Jira Integration** details page, click the **Sync Project List** button at the top right of the project details section.
  3. (Optional) **Sync ticket status**: Select this checkbox if you want to sync the ticket status between the Jira Cloud integration and Workflow Automation. The sync process runs daily. On the **Incident Details** page in Workflow Automation, only the complete status that you specified for a project appears in the Ticket section for an incident. This status appears when a ticket matches that complete status for the project in the Jira Cloud integration. None of the other Jira Cloud integration ticket statuses appear on the **Incident Details** page. After you select this checkbox, the **Close incident when ticket is closed** checkbox appears on the page.
  4. (Optional) **Close incident when ticket is closed**: Select this checkbox if you want Workflow Automation to automatically close the incident in Workflow Automation when the Jira ticket matches the complete status you defined for the project on the **Jira Integration** details page. If you do not select this checkbox, Workflow Automation does not automatically close the ticket. See image.
  5. Click **Save Changes**.

You can edit both project settings and integration settings for a Jira Software integration in Workflow Automation.

- Editing Project Settings for a Jira Software Integration.
- Editing Integration Settings for a Jira Software Integration.

To edit project settings for a Jira Software integration in Workflow Automation:

1. Go to **Administration**>**Workflow Automation**>**Data Protection** > **Integrations**. The **Integrations** dashboard appears, displaying a **Jira Cloud** application tile in the**Connected Apps** section. The **Tile View** icon is selected by default.

See image.

1. Perform one of the following steps:
  - In the tile view, in the **Connected Apps**section,click anywhere on the **Jira Cloud** tile.
  - In the list view, in the **Connected Apps** section, click **View Details**next to the Jira Cloud integration.

The **Jira Integration** details page appears. In the **Connected Accounts** section, all the Jira Cloud integration accounts appear along with the status of each account (Enabled and Disabled), the date each account was last modified, the name of the individual who performed the modification, the total number of projects for the account, and the total number of mapped projects for the account. To display the deleted application integrations, click **Show Deleted Accounts** at the top right of the **Connected Accounts** section.

See image.

1. On the **Jira Integration** details page, click the **Expand** icon for a connected account. The Jira projects display along with their metadata associated with the Jira Cloud integration. When you enable the Jira Cloud integration for the first time, all the projects and their metadata for the Jira Cloud integration sync with Workflow Automation and display on the page.

See image.

1. (Optional) In the top right of the project details section, click **Sync Project List**to manually sync the Jira Cloud integration project list with Workflow Automation.
2. (Optional) In the project details section, modify the complete status settings for each project associated with the Jira Cloud account. Select a different status from the **Complete Status** drop-down menu for each project.
3. Edit the field mapping for the Jira tickets in the project between the Jira Cloud integration and Workflow Automation.
  1. Click the **View Field Mapping** icon next to a project. The **Required Fields Mappings** page appears, listing all the current field mappings.
  2. On the **Required Fields Mappings** page:
    - Edit the existing values or enter new field mapping values for each required field for the Jira issue type. In the **Action** column, click **Save**. You cannot delete a required field value mapping.
    - Edit the field mapping values for each existing optional field for the Jira issue type and click **Save**in the**Action**column. Alternatively, click **Add More** and configure a new optional field mapping and click **Save**in the **Action**column.

You can delete optional field mappings. To delete an optional field mapping, click **Delete** in the **Action** column.

To edit integration settings for a Jira Software integration in Workflow Automation:

1. Go to **Administration**>**Workflow Automation**>**Data Protection** > **Integrations**. The **Integrations**dashboard appears, displaying a **Jira Cloud** application tile in the**Connected Apps** section. The **Tile View** icon is selected by default.

See image.

1. Perform one of the following steps:
  - In the tile view, in the **Connected Apps** section,click anywhere on the **Jira Cloud** tile.
  - In the list view, in the **Connected Apps** section, click **View Details**next to the Jira Cloud integration.

The **Jira Integration** details page appears. In the **Connected Accounts** section, all the Jira Cloud integration accounts appear, along with the status of each account (Enabled and Disabled), the date each account was last modified, the name of the individual who performed the modification, the total number of projects for the account, and the total number of mapped projects for the account. To display the deleted application integrations, click **Show Deleted Accounts** at the top right of the **Connected Accounts** section.

See image.

1. On the **Jira Integration**details page, click the **Edit** icon next to the connected account you want to edit. The **Jira Cloud Integration** editing page appears.

See image.

1. On the **Jira Integration** editing page, edit any of the fields that are displayed.
2. Click **Save Changes**.

To disable or enable a Jira Software integration in Workflow Automation:

1. Go to **Administration**>**Workflow Automation**>**Data Protection** > **Integrations**. The **Integrations**dashboard appears, displaying a **Jira Cloud** application tile in the**Connected Apps** section. The **Tile View** icon is selected by default.

See image.

1. Perform one of the following steps:
  - In the tile view, in the **Connected Apps**section,click anywhere on the **Jira Cloud** tile.
  - In the list view, in the **Connected Apps** section, click **View Details**next to the Jira Cloud integration.

The **Jira Integration** details page appears. In the **Connected Accounts** section, all the Jira Cloud integration accounts appear, along with the status of each account (Enabled and Disabled), the date each account was last modified, the name of the individual who performed the modification, the total number of projects for the account, and the total number of mapped projects for the account.

See image.

1. On the **Jira Integration** details page, click the **Edit** icon next to a connected account with an Enabled status that you want to disable or an account with a Disabled status that you want to enable. The **Jira Integration** editing page appears.
2. On the **Jira Integration** editing page, click **Disable Integration**or**Enable Integration.**The status of the integration changes to Disabled or Enabled. You cannot enable or disable Jira Cloud integrations that have been deleted.

To view Jira Software integrations in Workflow Automation:

1. Go to **Administration**>**Workflow Automation**>**Data Protection** > **Integrations**. The **Integrations**dashboard appears.
2. On the **Integrations**dashboard:

- Filter the integrations that are displayed on the dashboard. Select **All** to display all the integrations or select **Connected** to display only those integrations that are connected. By default, all integrations are displayed.

See image.

- Change the layout of the dashboard. Select the **Tile View** icon to view the integrations in a tile format or select the **List View** icon to view the integrations in a list format. By default, the dashboard displays in the tile view.

See image.

1. View the list of all Jira Cloud integrations that have been added to your organization:

In the tile view, in the **Connected Apps** section, you can view the following information:

1. **App**: The application that is associated with the integration (e.g., **Jira Cloud**).
2. **Account Details**: The integration name for each account associated with the integration.
3. **Status**: The status of the accounts for the integration. An oval at the top of the tile lists the number of accounts that are connected and the status of **Connected**. This number does not include accounts with a Deleted status.

See image.

In the list view, in the **Connected Apps** section, you can view the following information:

- **App Integration:** The application that is associated with the integration.
- **Account Details**: The integration name for each account associated with the integration.
- **Status**: The status of the accounts for the integration. An oval in this field lists the number of accounts that are connected and the status of **Connected.**This number does not include accounts with a Deleted status.
- **Account Connected**: The number of accounts that are connected.

See image.

1. View additional configuration details for the Jira Cloud integrations:
  1. On the **Integrations** dashboard, perform one of the following steps:

- In the tile view, in the **Connected Apps**section,click anywhere in the **Jira Cloud** tile.
- In the list view, in the **Connected Apps** section, click **View Details** next to the Jira Cloud application integration.

The **Jira Integration** details page appears, displaying a **Configuration Steps** tab and a **Configuration** tab. The **Configuration Steps** tab provides a link to the instructions on how to manage the Workflow Automation integration with Jira Software. On the **Configuration** tab, you can view the list of Jira Cloud integration accounts along with the status of each account (Enabled and Disabled), the date when each account was last modified, the name of the individual who performed the modification, the total number of projects for the account, and the total number of mapped projects for the account. To display the deleted application integrations, click **Show Deleted Accounts** at the top right of the **Connected Accounts** section.

1. On the **Configuration** tab, click the **Edit** icon next to a Jira Cloud integration account to view the configuration details for that integration.

1. View project settings for the Jira Cloud integrations:
  1. On the **Integrations** dashboard, perform one of the following steps:
    - In the tile view, in the **Connected Apps** section,click anywhere in the **Jira Cloud** tile.
    - In the list view, in the **Connected Apps** section, click **View Details** next to the Jira Cloud application integration.

The **Jira Integration** details page appears, displaying a **Configuration Steps** tab and a **Configuration** tab.

1. On the **Jira Integration** details page, click the **Expand** icon for a connected account. The project details section appears, listing all the project configurations for that account. To view all projects (mapped and unmapped) for the integration, select **All**at the top of the section. To view projects that have been mapped, select **Only Mapped** at the top of the section. In the project details section, you can see:

- **Project**: The name of the project.
- **Modified By**: The name of the user who modified the project settings.
- **Complete Status**: The complete status specified for the project. If a status has not been selected, **Click to change status** appears for the field.
- **Auto Sync**: Indicates whether auto sync is **Enabled** or **Disabled** for the project. To view the reason the auto sync is disabled, click the **Information** icon next to the **Disabled** status. You can disable auto sync for the following reasons:
  - **Mapping is Empty**: The field mapping has not been added for the project using this section of the page. Click the **Add Field Mapping** icon next to a project to add the field mapping for the project.
  - **Labels Not Supported**: The Labels field is not supported for the project in the Jira Software application. Workflow Automation uses labels to search for and sync the tickets in Jira Software with the tickets on the Incident Details page in Workflow Automation. To correct this issue, you must add the **Labels** field to the project in Jira Software.
- **Field Mappings**: Displays either the **Add Field Mapping** icon or the **View Field Mapping** icon. The **Add Field Mapping** icon appears if no field mapping has been added for the project. The **View Field Mapping** icon appears if field mapping has been added for the project.

See image.

1. To view the existing field mapping for the project, click the **View Field Mapping** icon next to a project. The **Required Fields Mappings** page appears, displaying the existing field mappings.

See image.

[Image: Adding Jira Software as a SaaS Application Tenant in the Zscaler Admin Console]

[Image: Viewing the Jira Cloud tile on the Integrations dashboard in the Zscaler Admin Console]

[Image: Viewing the Jira Integration details page for an integration in the Integrations dashboard]

[Image: Viewing the Jira Integration details page with an account row expanded in the Integrations dashboard]

[Image: Selecting the complete status for a project on the Jira Cloud Integration Details page in the Integrations Dashboard]

[Image: Viewing the Required Fields Mapping page in the Integrations dashboard]

[Image: Viewing the Required Fields Mapping page with all the required fields entered in the Integrations dashboard]

[Image: Required Fields Mapping page with optional fields entered in the Integrations dashboard]

[Image: Viewing the Jira Cloud tile on the Integrations dashboard in the Zscaler Admin Console]

[Image: Viewing the Jira Integration details page with an account enabled in the Integrations dashboard]

[Image: Configuring Jira Cloud configuration settings on the Jira Integration editing page in the Integrations dashboard]

[Image: Viewing the Jira Cloud tile on the Integrations dashboard in the Zscaler Admin Console]

[Image: Viewing the Jira Integration details page with an account that has been edited in the Integrations dashboard]

[Image: Viewing the Jira Integration details page with an account row expanded in the Integrations dashboard]

[Image: Viewing the Jira Cloud tile on the Integrations dashboard in the Zscaler Admin Console]

[Image: Viewing the Jira Integration details page with an account that has been edited in the Integrations dashboard]

[Image: Viewing the Jira Integration details page with an account that has been edited in the Integrations dashboard]

[Image: Viewing the Jira Cloud tile on the Integrations dashboard in the Zscaler Admin Console]

[Image: Viewing the Jira Integration details page with an account that has been edited in the Integrations dashboard]

[Image: Viewing the All and Connected icons on the Integrations dashboard in the Zscaler Admin Console]

[Image: Viewing the Tile and List View icons on the Integrations dashboard in the Zscaler Admin Console]

[Image: Viewing the Jira Cloud Integration tile in the Integrations dashboard in the Zscaler Admin Console]

[Image: Viewing the Jira Cloud list entry in the list view on the Integrations dashboard in the Zscaler Admin Console]

[Image: Viewing the Jira Integration details page with an account row expanded in the Integrations dashboard]

[Image: Viewing all the existing field mappings on the Required Fields Mapping page in the Zscaler Admin Console]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/managing-workflow-automation-integration-microsoft-teams","lastmod":"2026-07-21T13:53Z","nid":"1461676"} -->
## Managing Workflow Automation Integration with Microsoft Teams

- Source: https://help.zscaler.com/workflow-automation/managing-workflow-automation-integration-microsoft-teams
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Incident Management > Integrations > Managing Workflow Automation Integration with Microsoft Teams
- Last modified: 2026-07-21T13:53Z
- Summary: Information about Workflow Automation integration with Microsoft Teams.

Workflow Automation can integrate with Microsoft Teams, a sanctioned Software as a Service (SaaS) application for Zscaler. During the remediation process for a data protection incident in Workflow Automation, admins or the application can initiate different notifications (user, escalation, and digest) to remediate the incident. When Workflow Automation is integrated with the Microsoft Teams application, these notifications can be delivered through Teams messages to the appropriate users or admins associated with the incident. To learn more, see [Managing Incidents](https://help.zscaler.com/workflow-automation/managing-incidents), [Viewing & Managing Incident Details](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details), [Managing Notification Templates](https://help.zscaler.com/workflow-automation/managing-notification-templates), [Managing Incident and Digest Template Mappings](https://help.zscaler.com/workflow-automation/managing-incident-and-digest-template-mappings), [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments), and [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings).

The workflow functionality can also use Microsoft Teams messages instead of emails when notifications are initiated. When defining a workflow definition, you can select Teams messages for the different actions contained within it. To learn more, see [Understanding Workflows in Workflow Automation](https://help.zscaler.com/workflow-automation/understanding-workflows-workflow-automation).

## Integrating Workflow Automation with Microsoft Teams

Before you can integrate Workflow Automation with Microsoft Teams, you must:

- Configure the Data Loss Prevention (DLP) application integration for your organization using Amazon Web Services, Azure, or Google Cloud Platform. Ensure that you add a DLP application integration in Workflow Automation during this process. To learn more, see [Configuring the DLP Application Integration Using Amazon Web Services](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-amazon-web-services), [Configuring the DLP Application Integration Using Azure](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-azure), and [Configuring the DLP Application Integration Using Google Cloud Platform](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-google-cloud-platform).
- Obtain and configure the Microsoft Teams application for your organization.
  - Ensure that you define the different users for your organization who can use the Microsoft Teams application.
  - Ensure that you have admin credentials for the Microsoft Teams application. These credentials are required to integrate Workflow Automation with Microsoft Teams.

To learn more, refer to the [Microsoft Teams admin documentation](https://learn.microsoft.com/en-us/microsoftteams/).

To integrate Workflow Automation with Microsoft Teams:

1. In the Zscaler Admin Console, go to **Policies** > **Common Configuration** > **Out-of-Band CASB** > **SaaS Application Tenants**.
2. Click **Add SaaS Application Tenant**. The **Add SaaS Application Tenant** page appears.
3. On the **Add SaaS Application Tenant** page, add the Microsoft Teams application as a SaaS application tenant using the Microsoft Teams admin credentials. When adding the tenant, ensure that you select the **Workflow Automation** checkbox under the **Onboard SaaS Application for** section. This process authorizes the Microsoft Teams application with Internet & SaaS (ZIA) and integrates the Microsoft Teams application with Workflow Automation. You can now select the Teams channel for the notifications initiated within Workflow Automation. To learn more, see [Adding SaaS Application Tenants](https://help.zscaler.com/zia/adding-saas-application-tenants).

See image.

[Image: Adding Microsoft Teams as a SaaS Application Tenant in the Add SaaS Application Tenant Page in the Zscaler Admin Console]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/managing-workflow-automation-integration-servicenow","lastmod":"2026-07-17T10:23Z","nid":"1457511"} -->
## Managing Workflow Automation Integration with ServiceNow

- Source: https://help.zscaler.com/workflow-automation/managing-workflow-automation-integration-servicenow
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Incident Management > Integrations > Managing Workflow Automation Integration with ServiceNow
- Last modified: 2026-07-17T10:23Z
- Summary: Information about Workflow Automation integration with ServiceNow.

Workflow Automation can integrate with ServiceNow, a sanctioned Software as a Service (SaaS) application for Zscaler. During the remediation process for a data protection incident in Workflow Automation, admins can create and assign a ServiceNow ticket to an incident on the Incident Details page. When the ticket action is initiated on the Incident Details page for an incident, the admin selects the user to assign to the ticket in ServiceNow. The user they select must exist in the ServiceNow application and must have already been added on the Integration Users page in Workflow Automation. To learn more, see [Managing Integration Users](https://help.zscaler.com/workflow-automation/managing-integration-users) and [Viewing & Managing Incident Details](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details). test

When configuring the ServiceNow integration in Workflow Automation, you can select whether you want to sync the ticket status between ServiceNow and Workflow Automation. If you choose to sync the ticket status, you can also select whether to close the incident in Workflow Automation when the ticket is closed in ServiceNow. If you enable this option, after Workflow Automation receives the Closed status through the sync process, it automatically closes the incident.

The workflow functionality in Workflow Automation also has an Auto Create Tickets template that you can use to create tickets in ServiceNow. To learn more, see [Managing Workflow Templates](https://help.zscaler.com/workflow-automation/managing-workflow-templates).

## Integrating Workflow Automation with ServiceNow

Before you can integrate Workflow Automation with ServiceNow, you must:

- Configure the Data Loss Prevention (DLP) application integration for your organization using Amazon Web Services, Azure, or Google Cloud Platform. Ensure that you add a DLP application integration in Workflow Automation during this process. To learn more, see [Configuring the DLP Application Integration Using Amazon Web Services](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-amazon-web-services), [Configuring the DLP Application Integration Using Azure](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-azure), and [Configuring the DLP Application Integration Using Google Cloud Platform](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-google-cloud-platform).
- Obtain and configure the ServiceNow application for your organization.
  - Ensure that you define the different users for your organization who can use the ServiceNow application.
  - Ensure that you have admin credentials for the ServiceNow application. These credentials are required to integrate Workflow Automation with ServiceNow.

To learn more, refer to the [ServiceNow documentation](https://docs.servicenow.com/en-US/).

To integrate Workflow Automation with ServiceNow:

1. In the Zscaler Admin Console, go to **Policies** > **Common Configuration** > **Out-of-Band CASB** > **SaaS Application Tenants**.
2. Click **Add SaaS Application Tenant**. The **Add SaaS Application Tenant** page appears.
3. On the **Add SaaS Application Tenant** page, configure and add the ServiceNow application as a SaaS application tenant using the ServiceNow admin credentials. When adding the tenant, ensure that you select the **Workflow Automation** checkbox in the **Onboard SaaS Application for** section. This process authorizes the ServiceNow application with Internet & SaaS (ZIA) and integrates the ServiceNow application with Workflow Automation. On the **Integrations** dashboard in Workflow Automation, the ServiceNow application appears as a tile in the **Connected Apps** section. To learn more, see [Adding SaaS Application Tenants](https://help.zscaler.com/zia/adding-saas-application-tenants). See image.

## Managing ServiceNow Integrations in Workflow Automation

On the Integrations dashboard in the Zscaler Admin Console, admins can:

- Configure ServiceNow Integrations
- Edit ServiceNow Integrations
- Disable or Enable ServiceNow Integrations
- View ServiceNow Integrations

To configure a ServiceNow integration in Workflow Automation:

1. Go to **Administration**>**Workflow Automation**>**Data Protection** > **Integrations**. The **Integrations** dashboard appears, displaying a **ServiceNow** application tile in the**Connected Apps** section. The **Tile View** icon is selected by default. See image.
2. Perform one of the following steps:
  - In the tile view, in the **Connected Apps**section,click anywhere on the **ServiceNow** tile.
  - In the list view, in the **Connected Apps** section, click **View Details**next to the ServiceNow integration.

The **ServiceNow Integration** details page appears. In the **Connected Accounts** section, all the ServiceNow integrations appear along with their statuses (Enabled and Disabled). To display the deleted application integrations, click **Show Deleted Accounts** at the top right of the **Connected Accounts** section.

See image.

1. On the **ServiceNow Integration** details page, click the **Edit** icon next to the connected account you want to configure. The **ServiceNow Integration** editing page appears.
2. On the **ServiceNow Integration** editing page:
  1. (Optional) **Sync ticket status**: Select this checkbox if you want to sync the ticket status between ServiceNow and Workflow Automation. The sync process runs daily. When the ticket is closed in ServiceNow, only the **Closed** status for a ticket appears on the **Incident Details** page in Workflow Automation. None of the other ServiceNow ticket statuses appear on the **Incident Details** page. After you select this checkbox, the **Close incident when ticket is closed** checkbox appears on the page.
  2. (Optional) **Close incident when ticket is closed**: Select this checkbox if you want Workflow Automation to automatically close the incident in Workflow Automation when the ticket is closed in ServiceNow.

See image.

1. Click **Save Changes**.

To edit a ServiceNow integration in Workflow Automation:

1. Go to **Administration**>**Workflow Automation**>**Data Protection** > **Integrations**. The **Integrations**dashboard appears, displaying a **ServiceNow** application tile in the**Connected Apps** section. The **Tile View** icon is selected by default.
2. Perform one of the following steps:
  - In the tile view, in the **Connected Apps**section,click anywhere on the **ServiceNow** tile.
  - In the list view, in the **Connected Apps** section, click **View Details**next to the ServiceNow integration.

The **ServiceNow Integration** details page appears. In the **Connected Accounts** section, all the ServiceNow integrations appear, along with the status of each integration (Enabled and Disabled), the date each integration was last modified, and the name of the individual who performed the modification. To display the deleted application integrations, click **Show Deleted Accounts** at the top right of the **Connected Accounts** section.

See image.

1. On the **ServiceNow Integration** details page, click the **Edit** icon next to the connected account you want to edit. The **ServiceNow Integration** editing page appears.
2. On the **ServiceNow Integration** editing page, edit any of the fields that are displayed.
3. Click **Save Changes**.

To disable or enable a ServiceNow integration in Workflow Automation:

1. Go to **Administration**>**Workflow Automation**>**Data Protection** > **Integrations**. The **Integrations**dashboard appears, displaying a **ServiceNow** application tile in the**Connected Apps** section. The **Tile View** icon is selected by default.
2. Perform one of the following steps:
  - In the tile view, in the **Connected Apps** section, click anywhere on the **ServiceNow** tile.
  - In the list view, in the **Connected Apps** section, click **View Details**next to the ServiceNow integration.

The **ServiceNow Integration** details page appears. In the **Connected Accounts** section, all the ServiceNow integrations appear, along with their statuses (Enabled and Disabled), the date each integration was last modified, and the name of the individual who performed the modification.

See image.

1. On the **ServiceNow Integration** details page, click the **Edit** icon next to a connected account with an Enabled status that you want to disable or an account with a Disabled status that you want to enable. The **ServiceNow Integration** editing page appears.
2. On the **ServiceNow Integration** editing page, click **Disable Integration**or**Enable Integration.**The status of the integration changes to Disabled or Enabled. You cannot enable or disable ServiceNow integrations that have been deleted.

To view ServiceNow integrations in Workflow Automation:

1. Go to **Administration**>**Workflow Automation**>**Data Protection** > **Integrations**. The **Integrations**dashboard appears.
2. On the **Integrations**dashboard:

- Filter the integrations that are displayed on the dashboard. Select **All** to display all the integrations or select **Connected** to display only those integrations that are connected. By default, all integrations display. See image.
- Change the layout of the dashboard. Select the **Tile View** icon to view the integrations in a tile format or select the **List View** icon to view the integrations in a list format. By default, the dashboard displays in the tile view. See image.

1. View the list of all ServiceNow integrations that have been added to your organization.

In the tile view, in the **Connected Apps** section, you can view the following information:

1. **App**: The application that is associated with the integration—for example, ServiceNow.
2. **Account Details**: The integration name for each account associated with the integration.
3. **Status**: The status of the accounts for the integration. An oval at the top of the tile lists the number of accounts that are connected and the status of **Connected**. This number does not include accounts with a Deleted status.

See image.

In the list view, in the **Connected Apps** section, you can view the following information:

- **App Integration:** The application that is associated with the integration.
- **Account Details**: The integration name for each account associated with the integration.
- **Status**: The status of the accounts for the integration. An oval in this field lists the number of accounts that are connected and the status of **Connected.**This number does not include accounts with a Deleted status.
- **Account Connected**: The number of accounts that are connected.

See image.

1. View additional details for the ServiceNow integrations by performing one of the following steps:

- In the tile view, in the **Connected Apps** section, click anywhere in the **DLP** tile.
- In the list view, in the **Connected Apps** section, click **View Details** next to the DLP application integration.

The **ServiceNow Integration** details page appears, displaying a **Configuration Steps** tab and a **Configuration** tab. The **Configuration Steps** tab provides a link to the instructions on how to manage the Workflow Automation integration with ServiceNow. On the **Configuration** tab, you can view the list of ServiceNow integration accounts along with the status of each account, the date when each account was last modified, and the individual who performed the modification. To display the deleted application integrations, click **Show Deleted Accounts** at the top right of the **Connected Accounts** section.

1. On the **Configuration** tab, click the **Edit** icon next to a ServiceNow integration account to view the specific details for that integration.

[Image: Adding ServiceNow as a SaaS Application Tenant on the Add SaaS Application Tenant page in the Zscaler Admin Console]

[Image: Viewing the ServiceNow connected application tile on the Integrations Dashboard in the Zscaler Admin Console]

[Image: Viewing the ServiceNow Integration details page with an enabled ServiceNow integration in the Zscaler Admin Console]

[Image: Configuring the ServiceNow Integration on the ServiceNow Integration editing page in the Zscaler Admin Console]

[Image: Viewing all the ServiceNow integrations on the ServiceNow Integration details page in the Zscaler Admin Console]

[Image: Viewing the ServiceNow Integration details page after editing a ServiceNow integration]

[Image: Viewing the All and Connected icons on the Integrations Dashboard in the Zscaler Admin Console]

[Image: Viewing the Tile and List View icons on the Integrations Dashboard in the Zscaler Admin Console]

[Image: Viewing the ServiceNow Integration tile on the Integrations Dashboard in the Zscaler Admin Console]

[Image: Viewing the ServiceNow list entry on the Integrations Dashboard in the Zscaler Admin Console]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/managing-workflow-automation-integration-slack","lastmod":"2026-07-21T13:53Z","nid":"1457291"} -->
## Managing Workflow Automation Integration with Slack

- Source: https://help.zscaler.com/workflow-automation/managing-workflow-automation-integration-slack
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Incident Management > Integrations > Managing Workflow Automation Integration with Slack
- Last modified: 2026-07-21T13:53Z
- Summary: Information about Workflow Automation integration with Slack.

Workflow Automation can integrate with Slack, a sanctioned Software as a Service (SaaS) application for Zscaler. During the remediation process for a data protection incident in Workflow Automation, admins or the application can initiate different notifications (user, escalation, and digest) to remediate the incident. When Workflow Automation is integrated with the Slack application, these notifications can be delivered through Slack messages to the appropriate users or admins associated with the incident. To learn more, see [Managing Incidents](https://help.zscaler.com/workflow-automation/managing-incidents), [Viewing & Managing Incident Details](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details), [Managing Notification Templates](https://help.zscaler.com/workflow-automation/managing-notification-templates), [Managing Incident and Digest Template Mappings](https://help.zscaler.com/workflow-automation/managing-incident-and-digest-template-mappings), [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments), and [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings).

The workflow functionality can also use Slack messages instead of emails when notifications are initiated. When defining a workflow definition, you can select Slack messages for the different actions contained within it. To learn more, see [Understanding Workflows in Workflow Automation](https://help.zscaler.com/workflow-automation/understanding-workflows-workflow-automation).

## Integrating Workflow Automation with Slack

Before you can integrate Workflow Automation with Slack, you must:

- Configure the Data Loss Prevention (DLP) application integration for your organization using Amazon Web Services, Azure, or Google Cloud Platform. Ensure that you add a DLP application integration in Workflow Automation during this process. To learn more, see [Configuring the DLP Application Integration Using Amazon Web Services](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-amazon-web-services), [Configuring the DLP Application Integration Using Azure](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-azure), and [Configuring the DLP Application Integration Using Google Cloud Platform](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-google-cloud-platform).
- Obtain and configure the Slack application for your organization.
  - Ensure that you define the different users for your organization who can use the Slack application.
  - Ensure that you have admin credentials for the Slack application. These credentials are required to integrate Workflow Automation with Slack.

To learn more, refer to the [Slack help center](https://slack.com/help).

To integrate Workflow Automation with Slack:

1. In the Zscaler Admin Console, go to **Policies** > **Common Configuration** > **Out-of-Band CASB** > **SaaS Application Tenants**.
2. Click **Add SaaS Application Tenant**. The **Add SaaS Application Tenant** page appears.
3. On the **Add SaaS Application Tenant** page, add the Slack application as a SaaS application tenant using the Slack admin credentials. When adding the tenant, ensure that you select the **Workflow Automation** checkbox under the **Onboard SaaS Application for** section. This process authorizes the Slack application with Internet & SaaS (ZIA) and integrates the Slack application with Workflow Automation. You can now select the Slack channel for the notifications initiated within Workflow Automation. To learn more, see [Adding SaaS Application Tenants](https://help.zscaler.com/zia/adding-saas-application-tenants).

See image.

[Image: Adding Slack as a SaaS Application Tenant in the Add SaaS Application Tenant Page in the Zscaler Admin Console]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/managing-workflow-mappings","lastmod":"2026-06-29T07:06Z","nid":"1455966"} -->
## Managing Workflow Mappings

- Source: https://help.zscaler.com/workflow-automation/managing-workflow-mappings
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Workflows > Managing Workflow Mappings
- Last modified: 2026-06-29T07:06Z
- Summary: How to manage workflow mappings in Workflow Automation.

A workflow mapping specifies the incidents that are associated with the workflow. Only admins with full access to Workflow Automation can map the workflows. Incidents are mapped to workflows, which are based on one or more of the attributes available in an incident transaction. These mappings can be simple or more complex to meet your requirements. Then, when an incident occurs in your organization that contains those attributes, the workflow automatically triggers and performs the actions that the workflow specifies.

The mapping statements are evaluated in the order in which you configure them. Workflow Automation uses the first statement that matches with an incident. If no statements match an incident, then a workflow is not automatically triggered for the incident.

On the Workflow Mappings page in the Zscaler Admin Console, admins can:

- Add Workflow Mappings
- Edit Workflow Mappings
- View Workflow Mappings
- Delete Workflow Mappings
- Arrange Workflow Mapping Rules

## Prerequisites

In the Zscaler Admin Console, ensure that workflows have been added on the Workflows page. To learn more, see [Managing Workflows](https://help.zscaler.com/workflow-automation/managing-workflows).

## Adding Workflow Mappings

To add a workflow mapping:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Workflow Mappings**. The **Workflow** **Mappings** page appears, listing all the workflows that have been mapped.
2. On the **Workflow Mappings** page, at the top left of the page, click **Add Statement**. A new expanded row appears after the last workflow mapping. The statement section appears within that row. See image. You can also access the **Workflow Mappings** page from the **Workflows** page. In the table, click the **Add Workflow Mapping** icon in the **Mapping** column for the workflow you want to map. See image.
3. In the new row, from the **Workflow Name**drop-down menu, select the name of the workflow that you want to map. See image.
4. Configure a basic or advanced incident property mapping for the workflow, as required.
  - Basic Workflow Mapping
  - Advanced Workflow Mapping

To configure a basic workflow mapping:

1. In the statement section, from the drop-down menu, select the **Source** **DLP Type**. Source DLP types are **Any**, **Email**, **Endpoint**, **Inline**,and **SaaS Security**. **Any**appears by default.
2. Add a predicate for the first condition: You can add up to 100 predicates for a statement. See image.
  1. **Property**: From the drop-down menu, select the property. All the attributes in an incident transaction are available as properties. The properties available for selection vary depending on the DLP type you select. A property can be a number, a string, a date, or a Boolean field (True or False). If you choose user attributes for obfuscation, you cannot map a workflow to these obfuscated attributes (properties). In addition, if a user with permissions to workflow mappings has obfuscation enabled and a workflow was previously mapped using an obfuscation field, then the user cannot edit those existing workflow mappings. To learn more about obfuscation settings, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
    - Property List
  2. **Operation**: From the drop-down menu, select the operation. The operations vary depending on the property you choose.
    - Operations Table
  3. **Property value**: Enter or select the value for the property. Some of the properties display values for your organization filtered by the source DLP type that you can select (e.g., Severity, Source Actions, and Matching Policies.Rules[*].name). For others, you must enter a value for the property.
  4. Select the function for the condition. If required, select **NOT**. You can only select **OR** or **AND** as the function when you add another predicate.
3. (Optional) Add another predicate:
  1. Click **Add Predicate**.Another predicate row appears under the first predicate row, and the **AND** function is automatically selected for the condition. See image.
  2. In the new predicate row:
    1. **Property**: From the drop-down menu, select the property.
    2. **Operation**: From the drop-down menu, select the operation.
    3. **Property value**: Enter or select the property value for the property.
  3. If required, select the function for the condition. Functions are **NOT**, **OR**, and **AND**. See image.
4. (Optional) Add another condition to the statement:
  1. Above the predicates that have been defined, click the **Add** icon. Another condition box appears. See image.
  2. Enter the predicates for the condition. Add a predicate for the first condition and optionally add another predicate.
  3. Click **Save**.

The following is a list of the workflow mapping properties:

- **Application Info** The Application Info properties are available only for Source DLP types of Inline, SaaS Security, and Email.
  - Inline Source DLP Type
  - SaaS Security Source DLP Type
  - Email Source DLP Type
- **Content Info**
  - Inline and Any Source DLP Types
  - SaaS Security Source DLP Type
  - Endpoint Source DLP Type
  - Email Source DLP Type
- **Endpoint Info** The Endpoint Info properties are available only for Source DLP type Endpoint.
  - **Activity Type**
  - **Confirm Action**
  - **Confirm Justification**
- **Incident Group IDs**
- **Integration Name**
- **Integration Type**
- **Matching Policies**
  - Inline Source DLP Type
  - SaaS Security Source DLP Type
  - Endpoint Source DLP Type
  - Email Source DLP Type
  - Any Source DLP Type
- **Protocol** (only available for Source DLP type Inline)
- **Severity** (not available for Source DLP type Email)
- **Source Actions**
- **Source ID**
- **Source SubType**
- **Source Type**
- **User Info**
  - **Addresses**
    - **Home**
      - **Country**
      - **PostCode**
      - **Region**
    - **Other**
      - **Country**
      - **PostCode**
      - **Region**
    - **Work**
      - **Country**
      - **PostCode**
      - **Region**
  - **Client IP**(only available for Source DLP type Inline)
  - **Cost Center**
  - **Department**
  - **Device Name**(only available for Source DLP type Endpoint)
  - **Device OS**(only available for Source DLP type Endpoint)
  - **Device Trust Level** (only available for Source DLP type Endpoint)
  - **Division**
  - **Email**
  - **Employ Number**
  - **First Name**
  - **Groups**
  - **Home Country**(only available if you select **CSV** as the primary user data source on the [Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) page)
  - **Job Title**(only available if you select **CSV** as the primary user data source on the [Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) page)
  - **Last Name**
  - **Location**(only available if you select **CSV** as the primary user data source on the [Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) page)
  - **Manager**
    - **Department**
    - **Email**
    - **Groups**
    - **ID**
    - **Name**
    - **Organization**
  - **Name**
  - **Organization**
  - **Organization Hierarchy**(only available if you select **CSV**as the primary user data source on the [Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) page)
  - **Phone Number**(only available if you select **CSV**as the primary user data source on the [Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) page)
  - **Project IDs**(only available if you select **CSV** as the primary user data source on the [Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) page)
  - **Skip Level Managers** The Skip Level Managers properties are only available if you select **CSV** as your primary user data source on the [Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) page.
    - **Department**
    - **Email**
    - **ID**
    - **Name**
  - **Status**
  - **Termination Date**(only available if you select **CSV** as the primary user data source on the [Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) page)
  - **User ID**
  - **User Role**(only available if you select **CSV** as the primary user data source on the [Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) page)
  - **Worker Type**(only available if you select **CSV** as the primary user data source on the [Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) page)

- **Category**
- **Hostname Or Application**
- **Name**
- **Referrer URL**
- **Url**

- **Additional Info**
  - **SaaS Tenant Name**
- **Category**
- **Component**
- **Content Location**
- **Current Tag Name**
- **Domains**
- **Hostname Or Application**
- **Is Copilot Accessible**
- **Name**
- **Url**
- **Workspace**

- **Additional Info**
  - **Tenant**
- **Category**
- **Hostname Or Application**
- **Name**
- **Url**

- **Additional Info**
  - **DLP MD5**
- **File Name**
- **File Type**

- **Additional Info**
  - **Attachment Name**
  - **Bucket Name**
  - **Bucket Owner**
  - **Channel Name**
  - **Code Repository**
  - **Collaboration Scope**
    - **Values**
  - **DLP MD5**
  - **Email Sender**
  - **External Collaborators**
  - **External Collaborators Groups**
  - **External Email Recipients**
  - **File ID**
  - **File Owner**
  - **Internal Collaborators**
  - **Internal Collaborators Groups**
  - **Internal Email Recipients**
  - **Message ID**
  - **Object ID**
  - **Object Name**
- **Attachments**
  - **Document Sub Type**
  - **File Category**
  - **File Name**
  - **File Size**
  - **File Type**
  - **MD5**
- **Content Location**
- **File Category**
- **File Link Expiry**
- **File Modified By**
- **File Name**
- **File Shared At**
- **File Shared By**
- **File Size**
- **File Type**

- **Additional Info**
  - **Additional Info**
  - **Channel**
  - **Destination Type**
  - **DLP MD5**
  - **Expected Action**
  - **File Destination Location**
  - **File Size**
  - **File Source Location**
  - **Item Destination Name**
  - **Item Source Name**
  - **Item Type**
  - **Source Type**
  - **ZDP Mode**
- **Evidence Url**
- **File Name**
- **File Type**

- **Additional Info**
  - **Message ID**
  - **Other Email Recipients**
  - **Subject**

- **Dictionaries**
  - **Match Count**
  - **Name**
  - **Name Match Count**
- **Engines**
  - **Name**
  - **Rule**
- **Other Rules**
  - **Other Rules**
    - **Rule Name**
  - **Total Other Rules**
- **Rules**
  - **Name**

- **Dictionaries**
  - **Match Count**
  - **Name**
  - **Name Match Count**
- **Engines**
  - **Name**
  - **Rule**
- **Rules**
  - **Name**

- **Dictionaries**
  - **Assigned To Hit Rule**
  - **Match Count**
  - **Name**
  - **Name Match Count**
- **Engines**
  - **Assigned To Hit Rule**
  - **Name**
  - **Rule Expr**
- **Other Rules**
  - **Other Rules**
    - **Rule ID**
    - **Rule Name**
  - **Total Other Rules**
- **Rules**
  - **Name**

- **Dictionaries**
  - **Assigned To Hit Rule**
  - **Match Count**
  - **Name**
  - **Name Match Count**
- **Engines**
  - **Assigned To Hit Rule**
  - **Name**
  - **Rule**
- **Rules**
  - **Action**
  - **Files Info**
    - **Content Location**
    - **File Category**
    - **File Name**
    - **File Size**
    - **File Type**
    - **MD5**
  - **Name**
  - **Other Matched Rules**
  - **Recipient**
  - **Severity**

- **Dictionaries**
  - **Match Count**
  - **Name**
  - **Name Match Count**
- **Engines**
  - **Name**
- **Rules**
  - **Name**

The following table lists the operations and their descriptions:

| Operation | Description |
| --- | --- |
| **AFTER** It can be used for a Date type property field. | This operation tests whether the property selected for these types of incidents is after the value that you entered in the property value field (e.g., userInfo.Termination Date). |
| **BEFORE** It can be used for a Date type property field. | This operation tests whether the property selected for these types of incidents is before the value that you entered in the property value field (e.g., userInfo.Termination Date). |
| **CONTAINS_EXACT** It can be used for the following types of property fields: Array of strings; Number; Boolean | This operation tests whether the property selected for these types of incidents contains the exact value that you entered in the property value field. You must enter the full value for the property because no partial comparisons are performed. The property field value is not case sensitive. You can use this operation for properties that might include multiple values (e.g., matchingPolicies.rules[*].name, matchingPolicies.engines[*].name, and matchingPolicies.dictionaries[*].name). For example, let's say you want to map incidents that have violated a specific Data Loss Prevention (DLP) rule (Block-HIPAA-SSN) to a workflow (Social Security Numbers). But incidents are occurring in your organization that violate multiple DLP rules (Block-HIPAA-MIN, Block-PCI-CC, and Block-HIPAA-SSN) at the same time. Using the CONTAINS operation, you can ensure that those incidents with multiple rule violations that include the Block-HIPAA-SSN rule are mapped to the Social Security Numbers workflow. In the future, to ensure that this type of incident maps to the Social Security Numbers workflow, create the following workflow mapping predicate: Workflow Name = Social Security Numbers; Property = matchingPolicies.rules[*].name; Operation = CONTAINS_EXACT; Property Value = Block-HIPAA-SSN (must contain the full name of the DLP rule)See image. |
| **NOT_CONTAINS_EXACT** It can be used for the following types of property fields: Array of strings; Number; Boolean | This operation tests whether the property selected for these types of incidents does not contain the exact value that you entered in the property value field. You must enter the full value for the property because no partial comparisons are performed. The property field value is not case sensitive. You can use this operation for properties that might include multiple values (e.g., matchingPolicies.rules[*].name, matchingPolicies.engines[*].name, and matchingPolicies.dictionaries[*].name). |
| **EQUALS** It can be used for the following types of property fields: String; Number; Date | This operation tests whether the property selected for these types of incidents equals the value that you entered in the property value field. The property field value is not case sensitive. |
| **NOT_EQUALS** It can be used for the following types of property fields: String; Number; Date | This operation tests whether the property selected for these types of incidents does not equal the value that you entered in the property value field. The property field value is not case sensitive. |
| **IN_IPv4_SUBNET** It can be used for an IP Address type property field. | This operation tests whether the property selected for these types of incidents is in the IPv4 subnet value that you entered in the property value field. You can use this operation for IP address properties. |
| **IN_IPv6_SUBNET** It can be used for an IP Address type property field. | This operation tests whether the property selected for these types of incidents is in the IPv6 subnet value that you entered in the property value field. You can use this operation for IP address properties. |
| **LIKE** It can be used for a String type property field. | This operation tests whether the property selected for these types of incidents is like the value that you entered in the property value field. This operation does a partial comparison of the substring. For example, if you select **userInfo.name** as the property and enter `John` for the property value, the **LIKE** operation matches the following user names: John Brown; David John Smith; Susan John; John |
| **EXISTS** It can be used for all types of property fields. | This operation tests whether the property selected exists for these types of incidents. For this type of operation, you do not enter a property value. |
| **LESS_THAN** It can be used for a Number type property field. | This operation tests whether the property selected for these types of incidents is less than the value that you entered in the property value field. You can use this operation for numeric properties (e.g., userInfo.userId). |
| **GREATER_THAN** It can be used for a Number type property field. | This operation tests whether the property selected for these types of incidents is greater than the value that you entered in the property value field. You can use this operation for numeric properties (e.g., userInfo.userId). |

To configure an advanced workflow mapping:

1. In the statement section, from the drop-down menu, select the **Source** **DLP Type**. Source DLP types are **Any**, **Email**, **Endpoint**, **Inline**,and **SaaS Security**. **Any**appears by default.
2. Click **Advanced**. The statement section reappears, displaying multiple nested conditions. See image.
3. Configure the predicates as required for each condition in the statement. To add another predicate to a condition, click **Add Predicate**. To add another condition to a level in the statement section, click the **Add** icon at that level. To learn how to add a predicate and a condition to a statement, see Basic Workflow Mapping. You can add up to 100 predicates for a statement.
4. Click **Save**.

To edit a workflow mapping:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Workflow Mappings**. The **Workflow** **Mappings** page appears, listing all the workflows that have been mapped.
2. (Optional) On the **Workflow Mappings** page, use the **Search** field to locate the workflow you want to edit the mappings for.
3. At the end of the row next to the workflow you want to edit, click the **Expand** icon. The row expands to display the mappings in the statement section for the workflow. See image.
4. In the statement section, edit any of the existing predicates and conditions for the statement. You can edit the properties, operations for the properties, and property values within the existing predicates and the function for the condition.
5. (Optional) Add additional predicates or conditions to the statement. To learn more, see Adding Workflow Mappings. You can add up to 100 predicates for a statement.
6. Click **Save**.

To delete a predicate or condition within a statement, click the **Delete** icon next to the predicate or condition.

To view workflow mappings:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Workflow Mappings**. The**Workflow** **Mappings** page appears, listing all the workflows that have been mapped. See image.
2. On the **Workflow Mappings** page, at the end of the row next to a workflow, click the **Expand** icon. The row expands to display the mappings in the statement section for that workflow. See image.

To delete a workflow mapping:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Workflow Mappings**. The **Workflow** **Mappings** page appears, listing all the workflows that have been mapped.
2. On the **Workflow Mappings** page, click the **Delete** icon next to a workflow. A message appears asking whether you are sure that you want to delete this statement. See image.
3. Click **OK**.

Rules equate to statements in Workflow Automation.

To arrange workflow mapping rules:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Workflow Mappings**. The **Workflow** **Mappings** page appears, listing all the workflows that have been mapped.
2. On the **Workflow Mappings** page, click the down arrow or up arrow next to a workflow to arrange the order in which the rules are processed. Workflow Automation stops processing an incident after it finds its first rule match for the incident. See image.
3. Click **Save**.

[Image: Viewing the Workflow Mappings Page with the Add Statement Button Highlighted]

[Image: Viewing the Workflows Page with the Add Workflow Mapping Icons highlighted on the Table]

[Image: Viewing the Workflow Mappings Page with the Workflow Name Selected in the Statement Row]

[Image: Example of a Workflow Mapping with a Contains_Exact Operation on the Workflow Mappings Page]

[Image: Viewing the Workflow Mappings Page with a Basic Mapping Added]

[Image: Adding Another Predicate to a Basic Mapping on the Workflow Mappings Page. The New Row Under the First Row is highlighted for the Statement.]

[Image: Viewing a Basic Mapping with Two Predicates Configured. The Second Predicate Row is Highlighted and the Function Options are Highlighted.]

[Image: Adding Another Condition to a Basic Mapping on the Workflow Mappings Page. The New Condition Row is Highlighted.]

[Image: Adding an Advanced Mapping on the Workflow Mappings Page.]

[Image: Editing a Mapping on the Workflow Mappings Page]

[Image: Viewing All Workflow Mappings on the Workflow Mappings Page]

[Image: Viewing the Details for a Specific Workflow Mapping on the Workflow Mappings Page]

[Image: Deleting a Workflow Mapping on the Workflow Mappings Page. The Delete Icon is Highlighted Next to a Workflow Mapping.]

[Image: Arranging Workflow Mapping Rules on the Workflow Mappings Page. The Up and Down Arrow Icons are Highlighted Next to Each Mapping.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/managing-workflow-templates","lastmod":"2026-04-08T13:17Z","nid":"1455806"} -->
## Managing Workflow Templates

- Source: https://help.zscaler.com/workflow-automation/managing-workflow-templates
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Workflows > Managing Workflow Templates
- Last modified: 2026-04-08T13:17Z
- Summary: How to manage workflow templates in Workflow Automation.

Workflow templates are used as the basis for the workflows you add in Workflow Automation. Workflow Automation provides several different templates that address different scenarios. To add a workflow for your organization, you must select one of the templates provided. Admins use the workflows that are added and mapped to assist them with remediating the incidents that occur in your organization.

Workflow Automation provides the following workflow templates:

- Auto Close Data Protection Incident With Resolution Label
- Auto Close Data Protection Incident
- Auto Create Tickets
- Auto Escalate
- Auto Notify
- Auto Notify User and Close Incident
- Auto Notify User and Concurrently Escalate
- Auto Notify User and Escalate
- Auto Notify User and Escalate to Manager

To learn more, see [Understanding Workflows in Workflow Automation](https://help.zscaler.com/workflow-automation/understanding-workflows-workflow-automation).

On the Workflow Templates page in the Zscaler Admin Console, admins can:

- View Workflow Templates
- View Workflow Template Definitions
- Add Workflows

To view workflow templates:

Go to **Administration** > **Workflow Automation** > **Data Protection** > **Workflow Templates**. The **Workflow Templates** page appears, listing all the workflow templates that Workflow Automation provides. For workflow templates, you can view:

- **Template Name**: The name of the template.
- **Template Description**: The description for the template.
- **Counts**: The number of workflow definitions that have been created using this workflow template.

See image.

To view workflow template definitions:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Workflow Templates**. The **Workflow Templates** page appears, listing all the workflow templates that Workflow Automation provides.
2. On the **Workflow Templates** page, click the **View Workflow Template** icon in the **Action** column next to the template for which you want to view the definition. The **Workflow Settings** page appears, displaying the workflow definition. On the left side of the page, a graphic representation of the workflow definition is displayed. On the right side of the page, the workflow definition fields that are required for this workflow definition are displayed. The graphic and workflow definition fields vary depending on the workflow template that you select. The **Notification Channel** fields are only available if you have integrated Workflow Automation with Slack or Microsoft Teams. If the **Notification Channel** fields are not available, the notifications are by email. The following image is an example of the Auto Notify User and Escalate template when Workflow Automation has been integrated with Slack or Microsoft Teams.

See image.

1. Click **Cancel** to return to the **Workflow Templates** page.

To add a workflow:

You can add workflows from the **Workflow Templates** page or the **Workflows** page.

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Workflow Templates**. The **Workflow Templates** page appears, listing all the workflow templates that Workflow Automation provides.
2. On the **Workflow Templates** page, click the **Add New Workflow Definition** icon in the **Action** column next to the template on which you want to base the workflow. The **Workflow Settings** page appears, displaying the workflow definition for that template. On the left side of the page, a graphic representation of the workflow definition is displayed.
3. On the **Workflow Settings** page, on the right side of the page, enter values in the workflow definition fields required for that particular type of workflow.
4. Click **Save**. The workflow is added. The **Workflows** page appears, listing the workflow.

To learn more about adding workflows, see [Managing Workflows](https://help.zscaler.com/workflow-automation/managing-workflows) and [Managing Workflow Mappings](https://help.zscaler.com/workflow-automation/managing-workflow-mappings).

[Image: Workflow Templates Page]

[Image: Workflow Settings Page - Viewing workflow template definition]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/managing-workflows","lastmod":"2026-07-27T07:06Z","nid":"1455941"} -->
## Managing Workflows

- Source: https://help.zscaler.com/workflow-automation/managing-workflows
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Workflows > Managing Workflows
- Last modified: 2026-07-27T07:06Z
- Summary: How to manage predefined and custom workflows in Workflow Automation.

Workflows enable remediation actions to be performed against an incident that occurs in your organization without manual user intervention. You can add a predefined workflow in Workflow Automation based on a workflow template that specifies one or more actions and the order in which those actions are to be performed against an incident, or you can add a custom workflow where you choose and configure the different steps and actions required for the workflow without using a template. In either case, you must then map the workflow to one or more of the attributes available on an incident transaction. Then, when an incident occurs in your organization that contains those attributes, the workflow automatically triggers those actions specified in the workflow.

To learn more, see [Understanding Workflows in Workflow Automation](https://help.zscaler.com/workflow-automation/understanding-workflows-workflow-automation), [Managing Workflow Templates](https://help.zscaler.com/workflow-automation/managing-workflow-templates), [Managing Workflow Mappings](https://help.zscaler.com/workflow-automation/managing-workflow-mappings), [Managing Incidents](https://help.zscaler.com/workflow-automation/managing-incidents), and [Viewing & Managing Incident Details](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details).

On the Workflows page in the Zscaler Admin Console, admins can:

- Add Workflows
- View Workflows
- Edit Workflows
- Clone Workflows
- Add Workflow Mappings
- View Workflow Mappings

You can add predefined workflows based on a template, or you can add custom workflows that are not based on a template.

- Adding Predefined Workflows
- Adding Custom Workflows

You can add predefined workflows from the **Workflows** page or the **Workflow Templates** page. To learn more, see [Managing Workflow Templates](https://help.zscaler.com/workflow-automation/managing-workflow-templates).

To add a predefined workflow:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Workflows**. The **Workflows** page appears, listing all the workflows.
2. On the **Workflows** page, click **Add Pre-defined Workflow**. The **Workflow Templates** page appears, listing all the available workflow templates.
3. On the **Workflow Templates** page, in the **Action** column, click the **Add New Workflow Definition** icon next to the template on which you want to base the workflow. The **Workflow Settings** page appears, displaying the workflow definition for that template. The left side of the page displays a graphic representation of the workflow definition.
4. On the **Workflow Settings** page, on the right side of the page, enter values in the workflow definition fields required for that particular type of workflow.
  - Entering Information for an Auto Close Data Protection Incident
  - Entering Information for an Auto Close Data Loss Protection Incident With Resolution Label Type Workflow
  - Entering Information for an Auto Create Tickets Type Workflow
  - Entering Information for an Auto Escalate Template Type Workflow
  - Entering Information for an Auto Notify Template Type Workflow
  - Entering Information for an Auto Notify User and Close Incident Template Type Workflow
  - Entering Information for an Auto Notify User and Concurrently Escalate Type Workflow
  - Entering Information for an Auto Notify User and Escalate Type Workflow
  - Entering Information for an Auto Notify User and Escalate to Manager Type Workflow
5. Click **Save**. The workflow is added. The **Workflows** page appears, listing the workflow.

To enter information for an Auto Notify template type workflow:

1. In the **Workflow** section:
  - **Workflow Template**: Leave the default value of **Auto Notify**.
  - **Workflow Name**: Enter a name for the workflow.
  - **Description**: (Optional) Enter a description for the workflow.
2. In the **Notify User** section:
  - **Notification Channel**: From the drop-down menu, select the notification channel by which the user receives the user notification. Notification channels are **Email**, **Slack**, and**Teams**. The **Notification Channel** field is only available for the workflow if you have integrated Workflow Automation with Slack or Microsoft Teams. If the **Notification Channel**field is not available for the workflow, the notification is by email.
  - **Language**: From the drop-down menu, select the language in which the notification message is displayed to the user. The languages that appear in the drop-down menu depend on the mapped templates. If you select a language here that is not available in a mapped template set, then Workflow Automation defaults the notifications to English. You can edit this field only after selecting the **Notification Channel** field.

See image.

To enter information for an Auto Notify User and Close Incident template type workflow:

1. In the **Workflow** section:
  - **Workflow Template**: Leave the default value of **Auto Notify User and Close Incident**.
  - **Workflow Name:** Enter a name for the workflow.
  - **Description**: (Optional) Enter a description for the workflow.
2. In the **Notify User** section:
  - **Notification Channel**: From the drop-down menu, select the notification channel by which the user receives the user notification. Notification channels are **Email**, **Slack**, and**Teams**. The **Notification Channel** field is only available for the workflow if you have integrated Workflow Automation with Slack or Microsoft Teams. If the **Notification Channel**field is not available for the workflow, the notification is by email.
  - **Language**: From the drop-down menu, select the language in which the notification message is displayed to the user. The languages that appear in the drop-down menu depend on the mapped templates. If you select a language here that is not available in a mapped template set, then Workflow Automation defaults the notifications to English. You can edit this field only after selecting the **Notification Channel** field.
3. In the **Wait For User Response** section, in the **Time to wait for user response in seconds** field, enter the number of seconds the workflow waits to receive a response from the user before it closes the incident.
4. In the **Close Incident** section:
  - **Notes**: Enter notes to associate with the incident when the workflow closes the incident.
  - **Resolution Label Name**: From the drop-down menu, select a resolution label name to apply to the incident. After you select a resolution label name, if the label has configured values, the **Resolution** **Label Value**field becomes available. If the label does not have configured values, you cannot enter a label value.
  - **Resolution Label Value**: From the drop-down menu, select a label value to apply to the incident. You can only associate one resolution label value with a resolution label name when closing an incident.
  - **False Positive**: If the incident is a false positive, select this checkbox.

See image.

To enter information for an Auto Escalate template type workflow:

1. In the **Workflow** section:
  - **Workflow Template**: Leave the default value of **Auto Escalate**.
  - **Workflow Name**: Enter a name for the workflow.
  - **Description**: (Optional) Enter a description for the workflow.
2. In the **Escalate To Manager** section:
  - **Notification Channel**: From the drop-down menu, select the notification channel by which the manager receives the escalation notification. Notification channels are **Email**, **Slack**, and**Teams**.
  - **Language**: From the drop-down menu, select the language in which the escalation message is displayed to the manager. The languages that appear in the drop-down menu depend on the mapped templates. If you select a language here that is not available in a mapped template set, then Workflow Automation defaults the notifications to English. You can edit this field only after selecting the **Notification Channel** field.
3. In the **Escalate To Approver** section:
  - **Approver Name**:From the drop-down menu, select the name of the approver. The approver must exist on the [Approvers](https://help.zscaler.com/workflow-automation/managing-approvers) page.
  - **Notification Channel**: From the drop-down menu, select the notification channel by which the approver receives the escalation notification. Notification channels are **Email**, **Slack**, and**Teams**.
  - **Language**: From the drop-down menu, select the language in which the escalation message is displayed to the approver. The languages that appear in the drop-down menu depend on the mapped templates. If you select a language here that is not available in a mapped template set, then Workflow Automation defaults the notifications to English. You can edit this field only after selecting the **Notification Channel** field.

See image.

The **Notification Channel** fields are only available for the workflow if you have integrated Workflow Automation with Slack or Microsoft Teams. If the **Notification Channel**fields are not available for the workflow, the notifications are by email.

To enter information for an Auto Notify User and Escalate type workflow:

1. In the **Workflow** section:
  - **Workflow Template**: Leave the default value of **Auto** **Notify User and Escalate**.
  - **Workflow Name**: Enter a name for the workflow.
  - **Description**: (Optional) Enter a description for the workflow.
2. In the **Notify User** section:
  - **Notification Channel**: From the drop-down menu, select the notification channel by which the user receives the user notification. Notification channels are **Email**, **Slack**, and**Teams**.
  - **Language**: From the drop-down menu, select the language in which the notification message is displayed to the user. The languages that appear in the drop-down menu depend on the mapped templates. If you select a language here that is not available in a mapped template set, then Workflow Automation defaults the notifications to English. You can edit this field only after selecting the **Notification Channel** field.
3. In the **Wait For User Response** section, in the **Time to wait for user response in seconds** field, enter the number of seconds the workflow waits to receive a response from the user before it escalates the incident to the manager and approver.
4. In the **Escalate To Manager** section:
  - **Notification Channel**: From the drop-down menu, select the notification channel by which the manager receives the escalation notification. Notification channels are **Email**, **Slack**, and**Teams**.
  - **Language**: From the drop-down menu, select the language in which the escalation message is displayed to the manager. The languages that appear in the drop-down menu depend on the mapped templates. If you select a language here that is not available in a mapped template set, then Workflow Automation defaults the notifications to English. You can edit this field only after selecting the **Notification Channel** field.
5. In the **Escalate To Approver** section:
  - **Approver Name**:From the drop-down menu, select the name of the approver. The approver must exist on the [Approvers](https://help.zscaler.com/workflow-automation/managing-approvers) page.
  - **Notification Channel**: From the drop-down menu, select the notification channel by which the approver receives the escalation notification. Notification channels are **Email**, **Slack**, and**Teams**.
  - **Language**: From the drop-down menu, select the language in which the escalation message is displayed to the approver. The languages that appear in the drop-down menu depend on the mapped templates. If you select a language here that is not available in a mapped template set, then Workflow Automation defaults the notifications to English. You can edit this field only after selecting the **Notification Channel** field.

See image.

The **Notification Channel** fields are only available for the workflow if you have integrated Workflow Automation with Slack or Microsoft Teams. If the **Notification Channel**fields are not available for the workflow, the notifications are by email.

To enter information for an Auto Notify User and Concurrently Escalate type workflow:

1. In the **Workflow** section:
  - **Workflow Template**: Leave the default value of **Auto** **Notify User and Concurrently Escalate**.
  - **Workflow Name**: Enter a name for the workflow.
  - **Description**: (Optional) Enter a description for the workflow.
2. In the **Notify User** section:
  - **Notification Channel**: From the drop-down menu, select the notification channel by which the user receives the user notification. Notification channels are **Email**, **Slack**, and**Teams**.
  - **Language**: From the drop-down menu, select the language in which the notification message is displayed to the user. The languages that appear in the drop-down menu depend on the mapped templates. If you select a language here that is not available in a mapped template set, then Workflow Automation defaults the notifications to English. You can edit this field only after selecting the **Notification Channel** field.
3. In the **Escalate To Manager** section:
  - **Notification Channel**: From the drop-down menu, select the notification channel by which the manager receives the escalation notification. Notification channels are **Email**, **Slack**, and**Teams**.
  - **Language**: From the drop-down menu, select the language in which the escalation message is displayed to the manager. The languages that appear in the drop-down menu depend on the mapped templates. If you select a language here that is not available in a mapped template set, then Workflow Automation defaults the notifications to English. You can edit this field only after selecting the **Notification Channel** field.
4. In the **Escalate To Approver** section:
  - **Approver Name**:From the drop-down menu, select the name of the approver. The approver must exist on the [Approvers](https://help.zscaler.com/workflow-automation/managing-approvers) page.
  - **Notification Channel**: From the drop-down menu, select the notification channel by which the approver receives the escalation notification. Notification channels are **Email**,**Slack**, and**Teams**.
  - **Language**: From the drop-down menu, select the language in which the escalation message is displayed to the approver. The languages that appear in the drop-down menu depend on the mapped templates. If you select a language here that is not available in a mapped template set, then Workflow Automation defaults the notifications to English. You can edit this field only after selecting the **Notification Channel** field.

See image.

The **Notification Channel** fields are only available for the workflow if you have integrated Workflow Automation with Slack or Microsoft Teams. If the **Notification Channel**fields are not available for the workflow, the notifications are by email.

To enter information for an Auto Notify User and Escalate to Manager type workflow:

1. In the **Workflow** section:
  - **Workflow Template**: Leave the default value of **Auto** **Notify User and Escalate to Manager**.
  - **Workflow Name:** Enter a name for the workflow.
  - **Description**: (Optional) Enter a description for the workflow.
2. In the **Notify User** section:
  - **Notification Channel**: From the drop-down menu, select the notification channel by which the user receives the user notification. Notification channels are **Email**, **Slack**, and**Teams**.
  - **Language**: From the drop-down menu, select the language in which the notification message is displayed to the user. The languages that appear in the drop-down menu depend on the mapped templates. If you select a language here that is not available in a mapped template set, then Workflow Automation defaults the notifications to English. You can edit this field only after selecting the **Notification Channel** field.
3. In the **Wait For User Response** section, in the **Time to wait for user response in seconds** field, enter the number of seconds the workflow waits to receive a response from the user before it escalates the incident to the manager.
4. In the **Escalate To Manager** section:
  - **Notification Channel**: From the drop-down menu, select the notification channel by which the manager receives the escalation notification. Notification channels are **Email**, **Slack**, and**Teams**.
  - **Language**: From the drop-down menu, select the language in which the escalation message is displayed to the manager. The languages that appear in the drop-down menu depend on the mapped templates. If you select a language here that is not available in a mapped template set, then Workflow Automation defaults the notifications to English. You can edit this field only after selecting the **Notification Channel** field.

See image.

The **Notification Channel** fields are only available for the workflow if you have integrated Workflow Automation with Slack or Microsoft Teams. If the **Notification Channel**fields are not available for the workflow, the notifications are by email.

To enter information for an Auto Close Data Protection Incident type workflow:

1. In the **Workflow** section:
  - **Workflow Template**: Leave the default value of **Auto Close Data Protection Incident**.
  - **Workflow Name**: Enter a name for the workflow.
  - **Description**: (Optional) Enter a description for the workflow.
2. In the **Close Incident** section:
  - **Notes**:Enter notes to associate with the incident when the workflow closes the incident.
  - **False Positive**:If the incident is a false positive, select this checkbox.

See image.

To enter information for an Auto Close Data Loss Protection Incident With Resolution Label type workflow:

1. In the **Workflow** section:
  - **Workflow Template**: Leave the default value of **Auto Close Data Loss Protection Incident With Resolution Label**.
  - **Workflow Name:** Enter a name for the workflow.
  - **Description**: (Optional) Enter a description for the workflow.
2. In the **Close Incident** section:
  - **Notes**: Enter notes to associate with the incident when the workflow closes the incident.
  - **Resolution Label Name**: From the drop-down menu, select a resolution label name to apply to the incident. After you select a resolution label name, if the label has configured values, the **Resolution** **Label** **Value** field becomes available. If the label does not have configured values, you cannot enter a resolution label value.
  - **Resolution Label Value**: From the drop-down menu, select a resolution label value to apply to the incident. You can only associate one resolution label value with a resolution label name when closing an incident.
  - **False Positive**: If the incident is a false positive, select this checkbox.

See image.

To enter information for an Auto Create Tickets type workflow:

1. In the **Workflow** section:
  - **Workflow Template**: Leave the default value of **Auto Create Tickets**.
  - **Workflow Name**: Enter a name for the workflow.
  - **Description**: (Optional) Enter a description for the workflow.
2. In the **Ticketing Configuration** section:
  - **Ticketing Service**: From the drop-down menu, select the ticketing service that creates the ticket. Ticketing services are **JiraCloud** and **ServiceNow**. If you select JiraCloud as the service, the **Jira Project** field appears.
  - **Default Ticketing System**: From the drop-down menu, select the tenant ID associated with the ticketing service.
  - **Jira Project**: From the drop-down menu, select the Jira project for the ticket. Workflow Automation fetches all the projects from the Jira Software integration as part of the Jira Software application onboarding. To learn more, see [Managing Workflow Automation Integration with Jira Software](https://help.zscaler.com/workflow-automation/managing-workflow-automation-integration-jira-software).
  - **Default Ticket Assignee Email**: From the drop-down menu, select the email address for the user who will be assigned to the ticket that the workflow creates. The user must be added to the [Integration Users](https://help.zscaler.com/workflow-automation/managing-integration-users).

See image.

The custom workflow functionality in Workflow Automation is currently a preview version, as indicated by the Beta label on the Add Custom Workflow button.

To add a custom workflow:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Workflows**. The **Workflows** page appears, listing all the workflows.
2. On the **Workflows** page, click **Add Custom Workflow**. The custom workflow builder page appears, displaying the following: See image.
  1. The custom workflow name field at the top left of the page. Use this field to name the custom workflow.
  2. The **Description** button at the top right of the page. Click this button to add a description for the custom workflow.
  3. The **Tile** pane, which contains the **Notify** tile, the **Close Incident** tile, and the **Policy Exception** tile. Use these tiles to configure the custom workflow. A custom workflow can be simple or more complex depending on the requirements for the workflow.
  4. The custom workflow design area, which is blank except for the **Start** tile. Use this area to create and configure the custom workflow.
3. Click in the custom workflow name field and enter a name for the custom workflow.
4. (Optional) Enter a description for the custom workflow. To enter a description:
  1. Click **Description**. The **Description** dialog window appears.
  2. Enter a description in the text box.
  3. Click **OK**.
5. In the custom workflow design area, configure the custom workflow using one or more of the tiles. You can add as many tiles as required for the custom workflow. A custom workflow must start with the **Start** tile, and then you can add one or more **Notify** tiles, **Close Incident** tiles, and **Policy Exception** tiles. In a custom workflow configuration, you cannot connect a Close Incident tile immediately to another Close Incident tile, have two Close Incident tiles in the same branch of the workflow, or have an escalation to manager Notify tile after a Close Incident tile.
  - Adding Notify Tiles for a User Notification
  - Adding Notify Tiles for an Escalation Notification to a Manager
  - Adding Notify Tiles for an Escalation Notification to an Approver
  - Adding Notify Tiles for an Escalation Notification to Someone Other than a Manager or an Approver.
  - Adding Close Incident Tiles
  - Adding Policy Exception Tiles
  - Duplicating Tiles
  - Deleting Tiles and Edge Connectors
6. (Optional) Click **Validate**to have the systemvalidate the overall structure and configuration details of the workflow. If there are any validation errors in the workflow, the tiles where there are errors are highlighted in red. Click the**Information** icon on a tile to read an explanation of the error. See image.
7. (Optional) Click **Save as** **Draft**. The **Workflows** page appears, listing the workflow. You can come back later and continue to work on the custom workflow configuration. The status of the custom workflow is **Draft**.
8. Click **Publish Workflow**. The custom workflow is published, and a message dialog window appears asking whether you want to create a workflow mapping now. Do one of the following: Workflow Automation only uses published custom workflows for incident remediation.
  - If you do not want to immediately create a workflow mapping, click **Cancel**. The **Workflows** page appears, listing the custom workflow. The status of the workflow is **Published**, and the **Add Workflow Mapping** icon appears in the **Mapping** column for the workflow. When you click this icon, the **Workflow Mappings** page appears, where you can map the workflow. To learn more, see [Managing Workflow Mappings](https://help.zscaler.com/workflow-automation/managing-workflow-mappings).
  - If you want to create a workflow mapping, click **OK**. The **Workflow Mappings** page appears, where you can map the custom workflow. After the workflow is mapped, when you return to the **Workflows** page, the status of the workflow is **Published**,andthe **Show Workflow Mapping** icon appears in the **Mapping** column for the workflow. To learn more, see [Managing Workflow Mappings](https://help.zscaler.com/workflow-automation/managing-workflow-mappings).

To add a Notify tile for a user notification:

1. In the **Tile** pane, click the **Notify** tile, then drag and drop it to the custom workflow design area. The **Configure Notify Node** window appears, displaying the first tile configuration question that you must answer to configure the tile. This window is conversation based and assists you with configuring the tile. To configure the tile directly in the **Notify** Rich Text Editor without using the **Configure Notify Node** window, close the **Configure Notify Node** window and click the **Notify** tile in the custom workflow design area. After you click the tile, the **Notify** Rich Text Editor appears on the right side of the page where you can configure the options for the tile.
2. In the **Configure Notify Node** window, select the tile configuration settings: See image.
  1. For the first question that appears, click **None**. Question: I can help you configure this Notify node. Which policy context(s) would you like to configure?
  2. For the next question that appears, click **Notify**. Question: Would you like to notify the end user or escalate?
  3. For the next question that appears, click one of the buttons to select the channel (**Email**, **Slack**, or **Teams**) by which the user receives the notification. Question: How do you want to notify the user?
  4. (Optional) For the next question that appears, enter the number of days, hours, and minutes that the workflow waits to receive a response from the user. If you don't enter a wait time, the workflow immediately performs the action without waiting. Question: Would you like to set a wait time for a response? (Optional)
  5. Click **Continue**. A summary of the configuration settings you selected appears in the window along with the next question.
  6. For the next question that appears, click **Confirm.** Question: Would you like to confirm this configuration?
  7. For the last statement that appears on the window, click **Apply Configuration**. The **Notify** Rich Text Editor appears on the right side of the page. The configuration settings you selected appear in the Rich Text Editor. Statement: Your configuration is ready. Click "Apply Configuration" to save it.
3. In the **Notify** Rich Text Editor, configure the remaining configuration settings: See image.
  - **Action Context**: (Optional) From the drop-down menu, if you want the user to be able to approve or reject a policy exception from the user notification, select **Policy Exception**. The **Policy Exception** action context is not allowed for a user notification tile unless it is preceded by a Policy Exception tile. To learn more, see Adding Policy Exception Tiles.
  - **Notification Template**: (Optional) From the drop-down menu, select the notification template for the user notification. After you select the notification template, the **Notification** **Language** and **Time Zone** fields appear. If you do not select a notification template, the system uses the system default notification template for the notification channel you selected.
  - **Notification Language**: From the drop-down menu, select the language in which the user receives the user notification.
  - **Time Zone:**(Optional) If the notification template you selected is configured with one or more time-related merge tags, you can select the time zone that appears for those merge tags in the user notification. The **Time** merge tag represents the time when the incident occurred. From the drop-down menu, select the time zone that appears for those merge tags in the notification. In Workflow Automation, you can configure the time zone for the time-related merge tags for a notification on a few different pages. Because of this, the time zones the system uses for these merge tags when it creates a notification from a workflow is based on the following priority. If you select the time zone when you configure a notify tile for a workflow on the custom workflow builder page, then the system uses that time zone. If you did not select the time zone, then the system uses the time zone configured for the notification template on the Notification Template page. If you did not configure the time zone for the notification template, then the system uses the time zone assigned to the admin on the Admin Assignment page. If you did not assign the time zone to the admin, then the system uses the UTC+00:00 time zone, by default. To learn more, see [Managing Notification Templates](https://help.zscaler.com/workflow-automation/managing-notification-templates) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Survey Template**: (Optional) From the drop-down menu, select the survey template for the user notification. After you select the survey template, the **Survey Language** field appears. If you don't require a user response, do not select a survey template. Even if you do not select a survey template, the user notification still contains the content for the system default survey template. If the user wants, they can provide a justification for the incident, but the workflow doesn't depend on it.
  - **Survey Language**: From the drop-down menu, select the language in which the user receives the survey.
4. Click **OK**. The **Notify**tile appears in the custom workflow design area.
5. In the custom workflow design area, depending on the workflow, add one or more edge connectors (i.e., connection lines) between this notify tile and the other existing tiles in the custom workflow design area. The edge connectors link the tiles in the appropriate order for the workflow. To add an edge connector:
  1. Hover over the small circles on the tile to reveal the connection pointer.
  2. Drag the connection pointer between the points on two tiles. Depending on the tiles you are connecting and how they were configured, either a connection line appears immediately between the two tiles or a **Justification Condition** dialog window appears that you must configure before the connection line appears.
  3. If required, enter a justification condition for when the connecting tile action is executed in the workflow. To enter a justification condition, in the **Justification Condition** dialog window, from the **Justification Conditions** drop-down menu, select one of the justification types, or select **Any Response** or **No Response**. The justification types that appear in this field are based on the survey template that you selected when configuring the tile you are connecting from. If the from tile was not configured with templates, then the **Justification Conditions** dialog window does not appear.
  4. Click **OK**. The connection line appears with the justification condition you selected displayed in a black box on the connection line. See image.

To add a Notify tile for an escalation notification to a manager:

1. In the **Tile** pane, click the **Notify** tile, then drag and drop it to the custom workflow design area. The **Configure Notify Node** window appears, displaying the first tile configuration question that you must answer to configure the tile. This window is conversation based and assists you with configuring the tile. To configure the tile directly in the **Notify** Rich Text Editor without using the **Configure Notify Node** window, close the **Configure Notify Node** window and click the **Notify** tile in the custom workflow design area. After you click the tile, the **Notify** Rich Text Editor appears on the right side of the page where you can configure the options for the tile.
2. In the **Configure Notify Node** window, select the tile configuration settings: See image.
  1. For the first question that appears, click **None.** Question: I can help you configure this Notify node. Which policy context(s) would you like to configure?
  2. For the next question that appears, click **Escalate**. Question: Would you like to notify the end user or escalate?
  3. For the next question that appears, click **Manager**. Question: Who are we escalating to?
  4. For the next question that appears, click **Yes** or **No**. If the manager doesn't exist, and you want to send the escalation notification to another approver, click **Yes**. If the manager doesn't exist, and you do not want to send the escalation notification to another approver, click **No**. Question: If the manager doesn't exist, would you like to send the approval to an approver instead?
  5. If you selected **Yes** to the previous question, from the drop-down menu select the approver's email address for the statement that appears. If you selected **No** to the previous question, this statement is not displayed. Statement: Please select the approver email address to use as the fallback.
  6. Click **Continue.**
  7. For the next question that appears, enter the number of days, hours, and minutes that the workflow waits to receive a response from the manager or approver before timing out. Question: How long should we wait for a response before timing out?
  8. Click **Continue**.
  9. For the next question that appears, click one of the buttons to select the channel (**Email**, **Slack**, or **Teams**) by which the manager or approver receives the notification. After you click a channel button, a summary of the configuration settings you selected appears in the window along with the next question. Question: Which channel should we use for escalation?
  10. For the next question that appears, click **Confirm**. Question: Would you like to confirm this configuration?
  11. For the last statement that appears on the window, click **Apply Configuration**. The **Notify** Rich Text Editor appears on the right side of the page. The configuration settings you selected appear in the Rich Text Editor. Statement: Your configuration is ready. Click "Apply Configuration" to save it.
3. In the **Notify** Rich Text Editor, configure the remaining configuration settings: See image.
  - **Action Context**: (Optional) From the drop-down menu, if you want the manager or approver to be able to approve or reject a policy exception from the escalation notification, select **Policy Exception**. To learn more, see Adding Policy Exception Tiles.
  - **Notification Template**: (Optional) From the drop-down menu, select the notification template for the escalation notification. After you select the notification template, the **Notification** **Language** and **Time Zone** fields appear. If you do not select a notification template, the system uses the system default notification template for the notification channel you selected.
  - **Notification Language**: From the drop-down menu, select the language in which the manager or approver receives the escalation notification.
  - **Time Zone:**(Optional) If the notification template you selected is configured with one or more time-related merge tags, you can select the time zone that appears for those merge tags in the escalation notification. The **Time** merge tag represents the time when the incident occurred. From the drop-down menu, select the time zone that appears for those merge tags in the notification. In Workflow Automation, you can configure the time zone for the time-related merge tags for a notification on a few different pages. Because of this, the time zones the system uses for these merge tags when it creates a notification from a workflow is based on the following priority. If you select the time zone when you configure a notify tile for a workflow on the custom workflow builder page, then the system uses that time zone. If you did not select the time zone, then the system uses the time zone configured for the notification template on the Notification Template page. If you did not configure the time zone for the notification template, then the system uses the time zone assigned to the admin on the Admin Assignment page. If you did not assign the time zone to the admin, then the system uses the UTC+00:00 time zone, by default. To learn more, see [Managing Notification Templates](https://help.zscaler.com/workflow-automation/managing-notification-templates) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Survey Template**: (Optional) From the drop-down menu, select the survey template that the manager or approver receives to respond to the escalation notification. After you select the survey template, the **Survey Language** field appears. If you do not select a survey template, the system uses the system default survey template.
  - **Survey Language**: From the drop-down menu, select the language in which the manager or approver receives the survey.
4. Click **OK**. The **Notify**tile appears in the custom workflow design area.
5. In the custom workflow design area, depending on the workflow, add one or more edge connectors (i.e., connection lines) between this tile and the other existing tiles in the custom workflow design area. The edge connectors link the tiles in the appropriate order for the workflow. To add an edge connector:
  1. Hover over the small circles on the tile to reveal the connection pointer.
  2. Drag the connection pointer between the points on two tiles. Depending on the tiles you are connecting and how they were configured, either a connection line appears immediately between the two tiles or a **Justification Condition** dialog window appears that you must configure before the connection line appears.
  3. If required, enter a justification condition for when the connecting tile action is executed in the workflow. To enter a justification condition, in the **Justification Condition** dialog window, from the **Justification Conditions** drop-down menu, select either one of the justification types, or select **Any Response** or **No Response**. The justification types that appear in this field are based on the survey template that you selected when configuring the tile you are connecting from. If the from tile was not configured with templates, then the **Justification Conditions** dialog window does not appear.
  4. Click **OK**. The connection line appears with the justification condition you selected displayed in a black box on the connection line. See image.

To add a Notify tile for an escalation notification to an approver:

1. In the **Tile** pane, click the **Notify** tile, then drag and drop it to the custom workflow design area. The **Configure Notify Node** window appears, displaying the first tile configuration question that you must answer to configure the tile. This window is conversation based and assists you with configuring the tile. To configure the tile directly in the **Notify** Rich Text Editor without using the **Configure Notify Node** window, close the **Configure Notify Node** window and click the **Notify** tile in the custom workflow design area. After you click the tile, the **Notify** Rich Text Editor appears on the right side of the page where you can configure the options for the tile.
2. In the **Configure Notify Node** window, select the tile configuration settings: See image.
  1. For the first question that appears, click **None**. Question: I can help you configure this Notify node. Which policy context(s) would you like to configure?
  2. For the next question that appears, click **Escalate**. Question: Would you like to notify the end user or escalate?
  3. For the next question that appears, click **Approver**. Question: Who are we escalating to?
  4. For the statement that appears, from the drop-down menu, select the approver's email address. Statement: Please select the approver email.
  5. Click **Continue**.
  6. For the next question that appears, enter the number of days, hours, and minutes that the workflow waits to receive a response from the manager or approver before timing out. Question: How long should we wait for a response before timing out?
  7. Click **Continue**.
  8. For the next question that appears, click one of the buttons to select the channel (**Email**, **Slack**, or **Teams**) by which the manager or approver receives the notification. After you click a button, a summary of the configuration settings you selected appears in the window along with the next question. Question: Which channel should we use for escalation?
  9. For the next question that appears, click **Confirm**. Question: Would you like to confirm this configuration?
  10. For the last statement that appears on the window, click **Apply Configuration**. The **Notify** Rich Text Editor appears on the right side of the page. The configuration settings you selected appear in the Rich Text Editor. Statement: Your configuration is ready. Click "Apply Configuration" to save it.
3. In the **Notify** Rich Text Editor, configure the remaining configuration settings: See image.
  - **Action Context**: (Optional) From the drop-down menu, if you want the approver to be able to approve or reject a policy exception from the escalation notification, select **Policy Exception**. To learn more, see Adding Policy Exception Tiles.
  - **Notification Template**: (Optional) From the drop-down menu, select the notification template for the escalation notification. After you select the notification template, the **Notification** **Language**and**Time Zone** fields appear. If you do not select a notification template, the system uses the system default notification template for the notification channel you selected.
  - **Notification Language**: From the drop-down menu, select the language in which the manager receives the escalation notification.
  - **Time Zone:**(Optional) If the notification template you selected is configured with one or more time-related merge tags, you can select the time zone that appears for those merge tags in the escalation notification. The **Time** merge tag represents the time when the incident occurred. From the drop-down menu, select the time zone that appears for those merge tags in the notification. In Workflow Automation, you can configure the time zone for the time-related merge tags for a notification on a few different pages. Because of this, the time zones the system uses for these merge tags when it creates a notification from a workflow is based on the following priority. If you select the time zone when you configure a notify tile for a workflow on the custom workflow builder page, then the system uses that time zone. If you did not select the time zone, then the system uses the time zone configured for the notification template on the Notification Template page. If you did not configure the time zone for the notification template, then the system uses the time zone assigned to the admin on the Admin Assignment page. If you did not assign the time zone to the admin, then the system uses the UTC+00:00 time zone, by default. To learn more, see [Managing Notification Templates](https://help.zscaler.com/workflow-automation/managing-notification-templates) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Survey Template**: (Optional) From the drop-down menu, select the survey template that the approver receives to respond to the escalation notification. After you select the survey template, the **Survey Language** field appears. If you do not select a survey template, the system uses the system default survey template.
  - **Survey Language**: From the drop-down menu, select the language in which this person receives the survey.
4. Click **OK**. The **Notify**tile appears in the custom workflow design area.
5. In the custom workflow design area, depending on the workflow, add one or more edge connectors (i.e., connection lines) between this tile and the other existing tiles in the custom workflow design area. The edge connectors link the tiles in the appropriate order for the workflow. To add an edge connector:
  1. Hover over the small circles on the tile to reveal the connection pointer.
  2. Drag the connection pointer between the points on two tiles. Depending on the tiles you are connecting and how they were configured, either a connection line appears immediately between the two tiles or a **Justification Condition** dialog window appears that you must configure before the connection line appears.
  3. If required, enter a justification condition for when the connecting tile action is executed in the workflow. To enter a justification condition, in the **Justification Condition** dialog window, from the **Justification Conditions** drop-down menu, select either one of the justification types, or select **Any Response** or **No Response**. The justification types that appear in this field are based on the survey template that you selected when configuring the tile you are connecting from. If the from tile was not configured with templates, then the **Justification Conditions** dialog window does not appear.
  4. Click **OK**. The connection line appears with the justification condition you selected displayed in a black box on the connection line. See image.

To add a Notify tile for an escalation notification to someone other than a manager or approver:

1. In the **Tile** pane, click the **Notify** tile, then drag and drop it to the custom workflow design area. The **Configure Notify Node** window appears, displaying the first tile configuration question that you must answer to configure the tile. This window is conversation based and assists you with configuring the tile. To configure the tile directly in the **Notify** Rich Text Editor without using the **Configure Notify Node** window, close the **Configure Notify Node** window and click the **Notify** tile in the custom workflow design area. After you click the tile, the **Notify** Rich Text Editor appears on the right side of the page where you can configure the options for the tile.
2. In the **Configure Notify Node** window, select the tile configuration settings: See image.
  1. For the first question that appears, click **None**. Question: I can help you configure this Notify node. Which policy context(s) would you like to configure?
  2. For the next question that appears, click **Escalate**. Question: Would you like to notify the end user or escalate?
  3. For the next question that appears, click **Other**. Question: Who are we escalating to?
  4. For the statement that appears, from the drop-down menu, enter the email address of the person to receive the escalation notification. Statement: Please enter the email address to escalate to.
  5. Click **Continue.**
  6. For the next question that appears, enter the number of days, hours, and minutes that the workflow waits to receive a response from the person before timing out. Question: How long should we wait for a response before timing out?
  7. Click **Continue**.
  8. For the next question that appears, click one of the buttons to select the channel (**Email**, **Slack**, or **Teams**) by which the person receives the notification. After you click a button, a summary of the configuration settings you selected appears in the window along with the next question. Question: Which channel should we use for escalation?
  9. For the next question that appears, click **Confirm.** Question: Would you like to confirm this configuration?
  10. For the last statement that appears on the window, click **Apply Configuration**. The **Notify** Rich Text Editor appears on the right side of the page. The configuration settings you selected appear in the Rich Text Editor. Statement: Your configuration is ready. Click "Apply Configuration" to save it.
3. In the **Notify** Rich Text Editor, configure the remaining configuration settings: See image.
  - **Action Context**: (Optional) From the drop-down menu, if you want the other person to be able to approve or reject a policy exception from the escalation notification, select **Policy Exception**. To learn more, see Adding Policy Exception Tiles.
  - **Notification Template**: (Optional) From the drop-down menu, select the notification template for the escalation notification. After you select the notification template, the **Notification** **Language**and **Time Zone** fields appears. If you do not select a notification template, the system uses the system default notification template for the notification channel you selected.
  - **Notification Language**: From the drop-down menu, select the language in which this person receives the escalation notification.
  - **Time Zone:**(Optional) If the notification template you selected is configured with one or more time-related merge tags, you can select the time zone that appears for those merge tags in the escalation notification. The **Time** merge tag represents the time when the incident occurred. From the drop-down menu, select the time zone that appears for those merge tags in the notification. In Workflow Automation, you can configure the time zone for the time-related merge tags for a notification on a few different pages. Because of this, the time zones the system uses for these merge tags when it creates a notification from a workflow is based on the following priority. If you select the time zone when you configure a notify tile for a workflow on the custom workflow builder page, then the system uses that time zone. If you did not select the time zone, then the system uses the time zone configured for the notification template on the Notification Template page. If you did not configure the time zone for the notification template, then the system uses the time zone assigned to the admin on the Admin Assignment page. If you did not assign the time zone to the admin, then the system uses the UTC+00:00 time zone, by default. To learn more, see [Managing Notification Templates](https://help.zscaler.com/workflow-automation/managing-notification-templates) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Survey Template**: (Optional) From the drop-down menu, select the survey template that the person receives to respond to the escalation notification. After you select the survey template, the **Survey Language** field appears. If you do not select a survey template, the system uses the system default survey template.
  - **Survey Language**: From the drop-down menu, select the language in which this person receives the survey.
4. Click **OK**. The **Notify**tile appears in the custom workflow design area.
5. In the custom workflow design area, depending on the workflow, add one or more edge connectors (i.e., connection lines) between this tile and the other existing tiles in the custom workflow design area. The edge connectors link the tiles in the appropriate order for the workflow. To add an edge connector:
  1. Hover over the small circles on the tile to reveal the connection pointer.
  2. Drag the connection pointer between the points on two tiles. Depending on the tiles you are connecting and how they were configured, either a connection line appears immediately between the two tiles or a **Justification Condition** dialog window appears that you must configure before the connection line appears.
  3. If required, enter a justification condition for when the connecting tile action is executed in the workflow. To enter a justification condition, in the **Justification Condition** dialog window, from the **Justification Conditions** drop-down menu, select either one of the justification types, or select **Any Response** or **No Response**. The justification types that appear in this field are based on the survey template that you selected when configuring the tile you are connecting from. If the from tile was not configured with templates, then the **Justification Conditions** dialog window does not appear.
  4. Click **OK**. The connection line appears with the justification condition you selected displayed in a black box on the connection line. See image.

To add a Close Incident tile:

1. In the **Tile** pane, click the **Close Incident** tile, then drag and drop it in the custom workflow design area. The **Close Incident** Rich Text Editor appears on the right side of the page.
2. In the **Close Incident** Rich Text Editor, configure the Close Incident tile:
  - **Notes**: Enter notes to associate with the incident when the workflow closes the incident.
  - **Resolution Label Name**: From the drop-down menu, select a resolution label name to apply to the incident.
  - **Resolution Label Value**: From the drop-down menu, select a resolution label value to apply to the incident. You can only associate one resolution label value with a resolution label name when closing an incident.
  - **False Positive**: Select this checkbox to indicate that the incident is a false positive.
3. Click **OK**.
4. In the custom workflow design area, depending on the workflow, add one or more edge connectors (i.e., connection lines) between this tile and the other existing tiles in the custom workflow design area. The edge connectors link the tiles in the appropriate order for the workflow. To add an edge connector:
  1. Hover over the small circles on the tile to reveal the connection pointer.
  2. Drag the connection pointer between the points on two tiles. Depending on the tiles you are connecting and how they were configured, either a connection line appears immediately between the two tiles or a **Justification Condition** dialog window appears that you must configure before the connection line appears.
  3. If required, enter a justification condition for when the connecting tile action is executed in the workflow. To enter a justification condition, in the **Justification Condition** dialog window, from the **Justification Conditions** drop-down menu, select either one of the justification types, or select **Any Response** or **No Response**. The justification types that appear in this field are based on the survey template that you selected when configuring the tile you are connecting from. If the from tile was not configured with templates, then the **Justification Conditions** dialog window does not appear.
  4. Click **OK**. The connection line appears with the justification condition you selected displayed in a black box on the line.

See image.

You can add Policy Exception tiles to a workflow using two different methods. You can drag and drop a Policy Exception tile from the tile pane to the custom workflow design area, or you can configure a Notify tile with an Action Context of Policy Exception. In this case, the Policy Exception tile is automatically added for you in the custom workflow design area.

Workflow Automation supports custom workflows with Policy Exceptions tiles only for incidents of Source DLP type Inline and Email.

- Adding a Policy Exception Tile by Dragging and Dropping the Tile
- Adding a Policy Exception Tile by Adding a Notify Tile with a Policy Exception Action Context

To add a Policy Exception tile by dragging and dropping the Policy Exception tile from the Tile pane:

1. In the **Tile** pane, click the **Policy Exception** tile, then drag and drop it in the custom workflow design area. The **Policy Exception** Rich Text Editor appears on the right side of the page.
2. In the **Policy Exception** Rich Text Editor, enter the number of days, hours, and minutes for the default duration for the policy exception.
3. Click **OK**.
4. In the custom workflow design area, depending on the workflow, add one or more edge connectors (i.e., connection lines) between this Policy Exception tile and the other existing tiles in the custom workflow design area. The edge connectors link the tiles in the appropriate order for the workflow. You only connect **Policy Exception** tiles to **Notify** tiles that have been configured with an **Action** **Context** of **Policy Exception**. To add an edge connector:
  1. Hover over the small circles on the tile to reveal the connection pointer.
  2. Drag the connection pointer between the points on two tiles. Depending on the tiles you are connecting and how they were configured, either a connection line appears immediately between the two tiles or a **Justification Condition** dialog window appears that you must configure before the connection line appears.
  3. If required, enter a justification condition for when the connecting tile action is executed in the workflow. To enter a justification condition, in the **Justification Condition** dialog window, from the **Justification Conditions** drop-down menu, select one of the justification types or policy exception types, or select **Any Response** or **No Response**. The justification types that appear in this menu are based on the survey template that you selected when configuring the tile you are connecting from. If the from tile was not configured with a survey template, then the **Justification Conditions** dialog window displays only policy exception types (**Policy Exception Allowed** or **Policy Exception Denied)** and **Any Response** or **No Response**.
  4. Click **OK**. The connection line appears with the justification condition you selected displayed in a black box on the connection line.

See image.

To add a Policy Exception tile from a Notify tile with a policy exception action context:

1. In the **Tile** pane, click the **Notify** tile, then drag and drop it in the custom workflow design area. The **Configure Notify Node** window appears, displaying the first tile configuration question that you must answer to configure the tile. This window is conversation based and assists you with configuring the tile.
2. In the **Configure Notify Node** window, configure a **Notify** tile for either a user notification or an escalation notification. Instead of clicking **None** for the first question that appears on the window, click **Policy Exception**. To learn more, see Adding Notify Tiles for User Notification, Adding Notify Tiles for an Escalation Notification to a Manager, Adding Notify Tiles for an Escalation Notification to an Approver, and Adding Notify Tiles for an Escalation Notification to Someone Other than a Manager or an Approver. See image.
3. Click **OK**. The **Notify** tile appears along with the**Policy Exception** tile in the custom workflow design area. The **Notify** tile is already connected to the **Policy Exception** tile, and the **Justification Condition** dialog window appears.
4. In the **Justification Condition** dialog window, from the **Justification Conditions** drop-down menu, select one of the justification types or policy exception types, or select **Any Response** or **No Response**for when the **Policy Exception** tileis executed. The justification types that appear in this menu are based on the survey template that you selected when configuring the Notify tile.
5. Click **OK**. See image.
6. Configure the default policy exception duration:
  1. In the custom workflow design area, click the **Policy Exception** tile. The **Policy Exception** Rich Text Editor appears.
  2. Enter the number of days, hours, and minutes for the default policy exception duration. See image.
  3. Click **OK**.
7. In the custom workflow design area, depending on the workflow, add one or more edge connectors (i.e., connection lines) between this Notify tile and the other existing tiles in the custom workflow design area. The edge connectors link the tiles in the appropriate order for the workflow. To add an edge connector:
  1. Hover over the small circles on the tile to reveal the connection pointer.
  2. Drag the connection pointer between the points on two tiles. Depending on the tiles you are connecting and how they were configured, either a connection line appears immediately between the two tiles or a **Justification Condition** dialog window appears that you must configure before the connection line appears.
  3. If required, enter a justification condition for when the connecting tile action is executed in the workflow. To enter a justification condition, in the **Justification Condition** dialog window, from the **Justification Conditions** drop-down menu, select either one of the justification types, or select **Any Response** or **No Response**. The justification types that appear in this field are based on the survey template that you selected when configuring the tile you are connecting from. If the from tile was not configured with templates, then the **Justification Conditions** dialog window does not appear.
  4. Click **OK**. The connection line appears with the justification condition you selected displayed in a black box on the connection line. See image.

You can duplicate any tile in the custom workflow design area, except for the Start tile.

To duplicate a tile:

1. In the custom workflow design area, hover over a tile that you want to duplicate, and right-click. The **Duplicate** and **Delete** options appear. See image.
2. Select **Duplicate**. A new tile appears in the custom workflow design area. The new tile is identical to the tile it was duplicated from.

You can delete tiles and edge connectors in the custom workflow design area.

- Deleting Tiles
- Deleting Edge Connectors

To delete a tile:

1. In the custom workflow design area, hover over a tile that you want to delete, and right-click. The **Duplicate** and **Delete** options appear. See image.
2. Select **Delete**. The tile is deleted from the custom workflow design area.

You can also delete a tile by clicking on the tile and then pressing `Backspace`.

To delete an edge connector, click the edge connector (i.e., connection line) that you want to delete in the custom workflow design area, and press `Backspace`.

Go to **Administration** > **Workflow Automation**> **Data Protection** > **Workflows**. The **Workflows** page appears, listing all the workflows. For each workflow, you can view:

- **Workflow Name**: The name of the workflow.
- **Workflow Description**: The description of the workflow.
- **Template Name**: The template associated with the workflow. For custom workflows, **Custom Workflow** appears in this field.
- **Status**: The status of the workflow. Statuses are **Draft** and **Published**. Only custom workflows can have a draft status.

See image.

You can edit a predefined workflow that was configured using a template, and you can edit a custom workflow that was created without using a template.

- Editing a Predefined Workflow
- Editing a Draft Custom Workflow
- Editing a Published Custom Workflow

To edit a predefined workflow:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Workflows**. The **Workflows** page appears, listing all the workflows.
2. (Optional) On the **Workflows** page, apply filtersto locate the workflow you want to edit. To apply filters:
  1. Click **Filters**. The **Filters** window appears, displaying all the available filters on the left side of the window.
  2. Select a filter and then select the filter values on the right side of the window. As you select the filter values, the number of values selected appears next to the filter. You can select multiple filters and their values. You can include or exclude all the filter values by selecting the **Select All** checkbox. To remove all the selected filters, click **Reset**.
  3. Click **Apply**.
3. (Optional) On the **Workflows** page, use the **Search** field to locate the workflow you want to edit.
4. Click the **Edit**icon in the **Action** column next to a workflow. The **Workflow Settings** page appears, displaying the workflow. See image.
5. On the **Workflow Settings** page, change any of the workflow detail fields except the **Workflow Template** field.
6. Click **Save**.

The changes apply to future incidents mapped to the workflow. Current workflow executions are not affected by the changes.

To delete a workflow, click the **Delete**icon in the **Action** column next to a workflow on the **Workflows** page.

To edit a draft custom workflow:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Workflows**. The **Workflows** page appears, listing all the workflows.
2. (Optional) On the **Workflows** page, apply filtersto locate the workflow you want to edit. To apply filters:
  1. Click **Filters**. The **Filters** window appears, displaying all the available filters on the left side of the window.
  2. Select a filter and then select the filter values on the right side of the window. As you select the filter values, the number of values selected appears next to the filter. You can select multiple filters and their values. You can include or exclude all the filter values by selecting the **Select All** checkbox. To remove all the selected filters, click **Reset**.
  3. Click **Apply**.
3. (Optional) On the **Workflows** page, use the **Search** field to locate the workflow you want to edit.
4. Click the **Edit**icon in the **Action** column next to a draft custom workflow. The custom workflow builder page appears, displaying the custom workflow. See image.
5. On the custom workflow page, you can change the workflow description and any of the workflow detail fields in the existing tiles, configure additional tiles for the workflow, or delete existing tiles in the workflow. You can also change the justification condition on an edge connector. You cannot change the name of the custom workflow. To change the justification condition on an edge connector: To learn more, see Adding Custom Workflows and Deleting Tiles.
  1. Click an edge connector. The **Update Justification Condition** Rich Text Editor appears on the right side of the page. See image.
  2. In the **Justification Conditions** field, delete the existing justification condition and then select another condition from the drop-down menu.
  3. Click **Ok**.
6. (Optional) Click **Validate**to have the systemvalidate the overall structure and configuration details of the workflow. If there are any validation errors in the workflow, the tiles where there are errors are highlighted in red. Click the Information icon on a tile to get an explanation of the error.
7. (Optional) Click **Save as** **Draft**. You can come back later and continue to work on the workflow configuration. The status of the workflow is **Draft**.
8. Click **Publish Workflow**. The custom workflow is published, and a message dialog window appears asking whether you want to create a workflow mapping now. Do one of the following:
  - If you do not want to immediately create a workflow mapping, click **Cancel**. The **Workflows** page appears, listing the custom workflow. The status of the workflow is **Published**, and the **Add Workflow Mapping** icon appears in the **Mapping** column for the workflow. When you click this icon, the **Workflow Mappings** page appears, where you can map the workflow. To learn more, see [Managing Workflow Mappings](https://help.zscaler.com/workflow-automation/managing-workflow-mappings).
  - If you want to create a workflow mapping, click **OK**. The **Workflow Mappings** page appears, where you can map the custom workflow. After the workflow is mapped, when you return to the **Workflows** page, the status of the workflow is **Published**,andthe **Show Workflow Mapping** icon appears in the **Mapping** column for the workflow. To learn more, see [Managing Workflow Mappings](https://help.zscaler.com/workflow-automation/managing-workflow-mappings).

To delete a draft custom workflow, click the **Delete**icon in the **Action** column next to a draft custom workflow on the **Workflows** page.

To edit a published custom workflow:

1. Go to **Administration** > **Workflow Automation**> **Data Protection** > **Workflows**. The **Workflows** page appears, listing all the workflows.
2. (Optional) On the **Workflows** page, apply filtersto locate the workflow you want to edit. To apply filters:
  1. Click **Filters**. The **Filters** window appears, displaying all the available filters on the left side of the window.
  2. Select a filter and then select the filter values on the right side of the window. As you select the filter values, the number of values selected appears next to the filter. You can select multiple filters and their values. You can include or exclude all the filter values by selecting the **Select All** checkbox. To remove all the selected filters, click **Reset**.
  3. Click **Apply**.
3. (Optional) On the **Workflows** page, use the **Search** field to locate the workflow you want to edit.
4. Click the **Edit**icon in the **Action** column next to a published custom workflow. The custom workflow page appears, displaying the custom workflow. See image.
5. On the custom workflow page, you can change the workflow description and any of the workflow detail fields in the existing tiles, configure additional tiles for the workflow, or delete existing tiles in the workflow. You can also change the justification condition on an edge connector. You cannot change the name of the custom workflow. To change the justification condition on an edge connector: To learn more, see Adding Custom Workflows and Deleting Tiles.
  1. Click an edge connector. The **Update Justification Condition** Rich Text Editor appears on the right side of the page. See image.
  2. In the **Justification Conditions** field, delete the existing justification condition and then select another condition from the drop-down menu.
  3. Click **Ok**.
6. (Optional) Click **Validate**to have the systemvalidate the overall structure and configuration details of the workflow. If there are any validation errors in the workflow, the tiles where there are errors are highlighted in red.
7. Click **Update Workflow**. The workflow is updated, and its status remains **Published**.

The changes apply to future incidents mapped to the custom workflow. The changes do not affect current custom workflow executions.

To delete a published custom workflow, click the **Delete**icon in the **Action** column next to a published custom workflow on the **Workflows** page.

You can clone draft custom workflows and published custom workflows.

You can only clone custom workflows.

- Cloning Draft Custom Workflows
- Cloning Published Custom Workflows

To clone a draft custom workflow:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Workflows**. The **Workflows** page appears, listing all the workflows.
2. (Optional) On the **Workflows** page, apply filters or use the **Search** fieldto locate the custom workflow you want to clone.
3. In the **Action** column next to a custom workflow, click the **Clone**icon. The custom workflow builder page appears. In the **Workflow Name** field, "Clone of" appears in front of the name of the custom workflow that you cloned. See image.
4. (Optional) In the **Workflow Name** field, change the workflow name.
5. Edit any of the workflow details in the existing tiles, configure additional tiles for the workflow, or delete existing tiles in the workflow. You can also change the justification condition on an edge connector. To learn more, see Editing a Draft Custom Workflow, Adding Custom Workflows, and Deleting Tiles.
6. (Optional) Click **Validate**to have the systemvalidate the overall structure and configuration details of the workflow. If there are any validation errors in the workflow, the tiles where there are errors are highlighted in red.
7. (Optional) Click **Save as** **Draft**. The **Workflows** page appears listing the workflow. You can come back later and continue to work on the custom workflow configuration. The status of the custom workflow is **Draft**.
8. Click **Publish Workflow**. The custom workflow is published, and a message dialog window appears, asking whether you want to create a workflow mapping now. Do one of the following: Workflow Automation only uses published custom workflows for incident remediation.
  - If you do not want to immediately create a workflow mapping, click **Cancel**. The **Workflows** page appears, listing the custom workflow. The status of the workflow is **Published**, and the **Add Workflow Mapping** icon appears in the **Mapping** column for the workflow. When you click this icon, the **Workflow Mappings** page appears, where you can map the workflow. To learn more, see [Managing Workflow Mappings](https://help.zscaler.com/workflow-automation/managing-workflow-mappings).
  - If you want to create a workflow mapping, click **OK**. The **Workflow Mappings** page appears, where you can map the custom workflow. After the workflow is mapped, when you return to the **Workflows** page, the status of the workflow is **Published**,andthe **Show Workflow Mapping** icon appears in the **Mapping** column for the workflow. To learn more, see [Managing Workflow Mappings](https://help.zscaler.com/workflow-automation/managing-workflow-mappings).

To clone a published custom workflow:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Workflows**. The **Workflows** page appears, listing all the workflows.
2. (Optional) On the **Workflows** page, apply filters or use the **Search** fieldto locate the custom workflow you want to clone.
3. Click the **Clone**icon in the **Action** column next to a custom workflow. The custom workflow builder page appears. In the **Workflow Name** field, "Clone of" appears in front of the name of the custom workflow that you cloned. See image.
4. (Optional) In the **Workflow Name** field, change the workflow name.
5. Edit any of the workflow details in the existing tiles, configure additional tiles for the workflow, or delete existing tiles in the workflow. You can also change the justification condition on an edge connector. To learn more, see Editing a Published Custom Workflow, Adding Custom Workflows, and Deleting Tiles.
6. (Optional) Click **Validate**to have the systemvalidate the overall structure and configuration details of the workflow. If there are any validation errors in the workflow, the tiles where there are errors are highlighted in red.
7. Click **Publish Workflow**. The custom workflow is published, and a message dialog window appears asking whether you want to create a workflow mapping now. Do one of the following: Workflow Automation only uses published custom workflows for incident remediation.
  - If you do not want to immediately create a workflow mapping, click **Cancel**. The **Workflows** page appears, listing the custom workflow. The status of the workflow is **Published**, and the **Add Workflow Mapping** icon appears in the **Mapping** column for the workflow. When you click this icon, the **Workflow Mappings** page appears, where you can map the workflow. To learn more, see [Managing Workflow Mappings](https://help.zscaler.com/workflow-automation/managing-workflow-mappings).
  - If you want to create a workflow mapping, click **OK**. The **Workflow Mappings** page appears, where you can map the custom workflow. After the workflow is mapped, when you return to the **Workflows** page, the status of the workflow is **Published**,andthe **Show Workflow Mapping** icon appears in the **Mapping** column for the workflow. To learn more, see [Managing Workflow Mappings](https://help.zscaler.com/workflow-automation/managing-workflow-mappings).

After you add a predefined workflow or a custom workflow, you must then map the workflow.

A custom workflow must be in a Published state before you can map it.

To add a workflow mapping:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Workflows**. The **Workflows** page appears, listing all the workflows.
2. On the **Workflows** page, click the **Add**icon in the **Mapping**column next to a workflow. The **Workflow Mappings** page appears, displaying an expanded row for the workflow. See image.
3. Add the workflow mapping for the workflow. To learn more, see [Managing Workflow Mappings](https://help.zscaler.com/workflow-automation/managing-workflow-mappings#add-workflow-mappings).

Only admins with full access to Workflow Automation can map a workflow. You can add workflow mappings to a workflow from the **Workflows** page and the **Workflow Mappings** page.

To view workflow mappings:

1. Go to **Administration** > **Workflow Automation** > **Data Protection** > **Workflows**. The **Workflows** page appears, listing all the workflows.
2. On the **Workflows** page, in the **Mapping**column, click the **View** icon next to a workflow. See image. The **Workflow Mappings** page appears, displaying the mapping for the workflow.

[Image: Workflow Settings Page - Add Auto Notify Workflow]

[[Image: Workflow Settings Page - Auto Notify User and Close Incident Workflow]](https://help.zscaler.com/downloads/zia/workflow-automation/workflows/managing-workflows/ZIA-WA-Workflow-Settings-Page-Add-AutoNotifyCloseIncident.png)

[Image: Workflow Settings Page - Adding Auto Escalate Workflow]

[Image: Workflow Settings Page - Adding Auto Notify User and Escalate to Manager or Approver Workflow]

[Image: Workflow Settings Page - Adding Auto Notify User and Concurrently Escalate Workflow]

[Image: Workflow Settings Page - Adding Auto Notify User and Escalate to Manager Workflow]

[[Image: Workflow Settings Page - Adding Auto Close Data Loss Protection Incident with Resolution Label Workflow]](https://help.zscaler.com/downloads/zia/workflow-automation/workflows/managing-workflows/ZIA-WA-Workflow-Settings-PG-AutoCloseIncidentResolutionLabel.png)

[Image: Workflow Settings Page - Add Auto Close Data Protection Incident Workflow]

[Image: Workflow Settings Page - Adding Auto Create Tickets Workflow]

[Image: Viewing the custom workflow builder page. The page is highlighted to indicate the different areas of the page.]

[Image: Configure Notify Node window with the values populated for a user notification]

[Image: Notify Rich Text Editor with all the fields populated for a user notification. The optional fields are highlighted.]

[Image: Custom workflow design area with a user notification tile configured. The edge connector line is highlighted along with the connection points.]

[Image: Configure Notify Node window with the values populated for a manager escalation.]

[Image: Notify Rich Text Editor with all the fields populated for a manager escalation notification. The optional fields are highlighted.]

[Image: Custom workflow design area with an escalate to manager escalation tile configured. The edge connector line is highlighted along with the connection points.]

[Image: Configure Notify Node window with the values populated for an approver escalation notification]

[Image: Notify Rich Text Editor with all the fields populated for an approver escalation notification. The optional fields are highlighted.]

[Image: Custom workflow design area with an escalate to approver escalation tile configured. The edge connector line is highlighted along with the connection points.]

[Image: Configure Notify Node window with the values populated for an other person escalation notification]

[Image: Notify Rich Text Editor with all the fields populated for an other person escalation notification. The optional fields are highlighted.]

[Image: Custom workflow design area with an escalate to other escalation tile configured. The edge connector line is highlighted along with the connection points.]

[Image: Video showing how to add a Close Incident tile on the custom workflow builder page.]

[Image: Video showing how to add a Policy Exception tile on the custom workflow builder page.]

[Image: Configure Notify Node window with the Policy Exception button highlighted]

[Image: Custom workflow design area with a Policy Exception tile configured. The edge connector line is highlighted for the policy exception tile.]

[Image: Policy Exception Rich Text Editor. This Rich Text Editor contains a Default exception duration field.]

[Image: Custom workflow design area with an escalate to manager Notify tile and a Policy Exception tile that are both configured. The edge connector line is highlighted between the escalate to manager Notify tile and another notify user Notify tile.]

[Image: Viewing a custom workflow where the tiles have errors on the custom workflow builder page. The page shows a Close Incident tile connected to an Escalate to Manager Notify tile, and then that tile is connected to an Escalate to Approver Notify tile.]

[Image: Viewing the available tile options (Duplicate and Delete) for a tile in the custom workflow design area.]

[Image: Viewing the available tile options (Duplicate and Delete) for a tile in the custom workflow design area.]

[Image: Viewing the custom workflow builder page when editing a draft custom workflow.]

[Image: Viewing the custom workflow builder page when updating the justification condition for an edge connector between two tiles in a draft custom workflow. One of the edge connectors is highlighted in the workflow and the Update Justification Condition Real Text Editor is displayed.]

[Image: Viewing the custom workflow builder page when editing a published custom workflow.]

[Image: Viewing the custom workflow builder page when updating the justification condition for an edge connector between two tiles in a published custom workflow. One of the edge connectors is highlighted in the workflow and the Update Justification Condition Real Text Editor is displayed.]

[Image: Workflows Page - Viewing a List of Workflows]

[Image: Workflow Settings Page - Editing a Workflow]

[Image: Workflow Mappings Page - Adding a Mapping]

[Image: Workflows Page - Icons for Viewing Workflow Mappings]

[Image: Viewing the Workflows page with the Clone icon for a couple of draft custom workflows highlighted.]

[Image: Viewing the Workflows page with the Clone icon for a published custom workflow highlighted.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/release-upgrade-summary-2023","lastmod":"2023-11-27T23:59Z","nid":"1470966"} -->
## Release Upgrade Summary (2023)

- Source: https://help.zscaler.com/workflow-automation/release-upgrade-summary-2023
- Product: Workflow Automation
- Path: Workflow Automation Help > Release Notes > Release Upgrade Summary (2023)
- Last modified: 2023-11-27T23:59Z
- Summary: Workflow Automation Release Upgrade Summary for service updates deployed in 2023.

This article provides a summary of all new features and enhancements for Workflow Automation. To view all the older release notes of Workflow Automation, go to [ZIA Release Upgrade Summary (2023)](https://help.zscaler.com/zia/release-upgrade-summary-2023).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/release-upgrade-summary-2024","lastmod":"2025-01-02T16:49Z","nid":"1473541"} -->
## Release Upgrade Summary (2024)

- Source: https://help.zscaler.com/workflow-automation/release-upgrade-summary-2024
- Product: Workflow Automation
- Path: Workflow Automation Help > Release Notes > Release Upgrade Summary (2024)
- Last modified: 2025-01-02T16:49Z
- Summary: Workflow Automation Release Upgrade Summary for service updates deployed in 2024.

This article provides a summary of all new features and enhancements for Workflow Automation.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/release-upgrade-summary-2025","lastmod":"2026-01-03T00:40Z","nid":"1516031"} -->
## Release Upgrade Summary (2025)

- Source: https://help.zscaler.com/workflow-automation/release-upgrade-summary-2025
- Product: Workflow Automation
- Path: Workflow Automation Help > Release Notes > Release Upgrade Summary (2025)
- Last modified: 2026-01-03T00:40Z
- Summary: Workflow Automation Release Upgrade Summary for service updates deployed in 2025.

This article provides a summary of all new features and enhancements for Workflow Automation.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/release-upgrade-summary-2026","lastmod":"2026-07-27T10:50Z","nid":"1534330"} -->
## Release Upgrade Summary (2026)

- Source: https://help.zscaler.com/workflow-automation/release-upgrade-summary-2026
- Product: Workflow Automation
- Path: Workflow Automation Help > Release Notes > Release Upgrade Summary (2026)
- Last modified: 2026-07-27T10:50Z
- Summary: Workflow Automation Release Upgrade Summary for service updates deployed in 2026.

This article provides a summary of all new features and enhancements for Workflow Automation.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/responding-dlp-admin-digest-notification","lastmod":"2026-07-23T21:06Z","nid":"1451801"} -->
## Responding to a DLP Admin Digest Notification

- Source: https://help.zscaler.com/workflow-automation/responding-dlp-admin-digest-notification
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Incident Management > Responding to a DLP Admin Digest Notification
- Last modified: 2026-07-23T21:06Z
- Summary: Information on how to review and respond to a digest notification from the Zscaler Admin Console.

The format of the notification might not be the same as illustrated in this article. It depends upon the notification template configured by your organization in the Zscaler Admin Console.

Workflow Automation can send DLP admin digest notifications to admins if they have assigned incidents using a DLP admin digest notification template. Before a DLP admin digest notification can be sent to an admin, the DLP Admin Digest Notification Flags field must be enabled on the Account Settings page, and the admin's DLP admin digest settings (digest frequency, channel, and priority) must be configured for the admin on the Admins page. An admin's DLP admin digest settings specify the frequency and channel for their DLP admin digest notifications, as well as the incident priorities to include in their DLP admin digest notifications.

Workflow Automation provides default DLP admin digest notification templates (DLP Admin Digest - Email Template, DLP Admin Digest - Slack Template, and DLP Admin Digest - Teams Template) that you can clone and use for these notifications. These default digest notifications list the number of all new incidents for the admin, the number of all open incidents for the admin, and the number of state changes, and provide a link to the Incident page where the admin can view and manage these incidents. The DLP admin digest notifications contain the same information, but the format of the message is different and where the notification is delivered is different. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings), [Managing Notification Templates](https://help.zscaler.com/workflow-automation/managing-notification-templates), [Managing Incident and Digest Template Mappings](https://help.zscaler.com/workflow-automation/managing-incident-and-digest-template-mappings), and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).

## Viewing the DLP Admin Digest Notification

If you have assigned incidents, a DLP admin digest notification (e.g., email, Slack message, or Microsoft Teams message) is sent to you at the frequency specified for you on the Admins page. The digest notification contains the following information:

- The number of new incidents that require your attention.
- The number of open incidents that require your attention.
- The number of state changes associated with those incidents.
- The incidents link, where you can view and manage those incidents. This link is only valid for 24 hours.

The incident count provided in the message is accurate as of the date and time the notification is generated. However, the count is subject to change as new incidents are created, reassigned, or resolved.

The following images are examples of a DLP admin digest notification email, Slack message, and Microsoft Teams message. The DLP admin digest notifications contain the same information, but the format of the message is different.

See image.

The digest notification emails can be tagged as spam and sent to your spam folder based on your email settings. Change your settings to receive the digest notification emails directly in your inbox.

## Viewing the Incidents

To view the incidents, click the **View all incidents assigned to me**link provided in the notification. You are redirected to the **Incident** page. The **Incident** page appears, listing all the incidents that require your attention. For each incident, you can view:

- **Last Change Date**: The date and time the incident was last changed.
- **Transaction ID**: The transaction ID for the incident.
- **Status**: The status of the incident. Statuses are:
  - **New**
  - **Investigating**
  - **Validating with User**
  - **Justification Response Received**
  - **Escalated**

See image.

## Managing the Incidents

To manage incidents listed on the **Incident** page in the DLP admin digest notification, click the **Login**button on the **Incident** page. You are redirected to the **Incidents** page in the Zscaler Admin Console, where you can view the details of each incident and manage the incidents. To learn more, see [Managing Incidents](https://help.zscaler.com/workflow-automation/managing-incidents) and [Viewing & Managing Incident Details](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details).

See image.

[Image: Viewing an example of an email DLP admin digest notification. The DLP admin digest notification contains the number of new and open incidents that require your attention, the number of state changes associated with those incidents, and an incidents link, where you can view and manage those incidents.]

[Image: Viewing an example of a Slack DLP admin digest notification. The DLP admin digest notification contains the number of new and open incidents that require your attention, the number of state changes associated with those incidents, and an incidents link, where you can view and manage those incidents.]

[Image: Viewing an example of a Teams DLP admin digest notification. The DLP admin digest notification contains the number of new and open incidents that require your attention, the number of state changes associated with those incidents, and an incidents link, where you can view and manage those incidents.]

[Image: Viewing the list of incidents for the DLP Admin Digest Notification generated from Workflow Automation]

[Image: Viewing the incidents for the DLP Admin Digest Notification in the Incidents page in the Zscaler Admin Console]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/responding-end-user-notification","lastmod":"2026-07-09T14:28Z","nid":"1421056"} -->
## Responding to an End User Notification

- Source: https://help.zscaler.com/workflow-automation/responding-end-user-notification
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Incident Management > Responding to an End User Notification
- Last modified: 2026-07-09T14:28Z
- Summary: Information on how to review and respond to an end user notification from the Zscaler Admin Console.

The format of the notification and survey might not be the same as illustrated in this article. It depends upon the notification and the survey template that your organization configured in Workflow Automation.

As an end user, when you make a transaction that violates the Data Loss Prevention (DLP) policies of your organization, a DLP admin can notify you about the incident through an alert notification (e.g., email, Slack message, or Microsoft Teams message). This notification comes from the Zscaler Admin Console, where the organization's admins record and evaluate all incidents. The notification contains an Incident Detail Link associated with the survey that you must complete to justify that incident.

Depending on the severity of that incident, the admin can directly escalate it to your manager or another approver.

## Viewing the Notification

When an incident occurs, you receive a notification (e.g., email, Slack message, or Microsoft Teams message) requesting justification. The notification contains the following information:

- The date and time when the incident occurred.
- The Incident Detail Link, where you can view the incident details and also take a survey to justify the incident. This link is only valid for 24 hours.
- The incident ID.
- The hostname or the application of the incident.
- The priority of the incident.
- The note to the user from the admin who initiated the notification. This note appears only if the admin enters a note.

The following images are examples of a user notification sent by email, Slack message, and Microsoft Teams message. The notifications contain the same information, but the format of the message is different.

See image.

Depending on your email settings, notification emails might be tagged as spam and sent to your spam folder. Change your settings to receive the notification emails directly in your inbox.

## Viewing the Incident Details

To view the details of the incident, click the **Incident Detail Link** provided in the user notification. You are redirected to the **Incident** page, where you can view the following details:

- Overview
- Violation Details
- Generate Presigned Link
- View Trigger Data
- Policy

### Releasing Quarantined Emails for Email Incidents

After reviewing the incident details for a quarantined email incident, you can choose to release the email to one or more of its recipients by selecting one of the options that appears in the **Release Email to Recipients** section. This section is only available for incidents with a Source DLP type of Emailthat have a quarantine action, and if you have enabled the Enable Email Quarantine Release for End Users option on the [Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) page.

See image.

To release the quarantined email to its recipients, use one of the following methods:

- To release all the email recipients, select **Release to all recipients**.
- To release one or more of the email recipients:
  1. Select **Release to select recipients**. An **Email** table appears, listing the email addresses of the recipients who were sent the quarantined email.
  2. In the **Email** table, select the checkbox next to one or more of the recipient email addresses you want to release the email to. See image.

If you do not want to release the email to any recipients, select **Do not release**. This option is selected by default.

### Creating a Policy Exception for an Incident

After reviewing the details of an incident, you can choose to approve or reject a policy exception for the incident. If you approve the policy exception, you can then enter the duration for the policy exception. This section only appears for the end user notifications that are generated for an incident through a custom workflow where a policy exception tile has been configured. To learn more, see [Managing Workflows](https://help.zscaler.com/workflow-automation/managing-workflows).

This section is only available for incidents of Source DLP type Inline.

See image.

To create a policy exception for the incident:

1. Select **Approve**. The **Duration in Minutes** field appears below the **Approve** option.
2. In the **Duration in Minutes** field, enter the duration for the policy exception in minutes. After you submit the survey for the notification, the system creates a policy exception. Workflow Automation creates a subrule for the rule associated with the incident in the Zscaler Admin Console. The subrule applies only to the user associated with the incident. You can view this subrule on the **Data Loss Prevention** page in the Zscaler Admin Console. To learn more, see [About Data Loss Prevention](https://help.zscaler.com/zia/about-data-loss-prevention). See image.

If you do not want to create a policy exception, select **Reject**.

### Completing the Survey

After reviewing the incident details, you might be required to complete a survey at the end of the Incident page to justify your action.

To provide a justification, complete the following survey and then click **Submit**:

- **Justification Type**: Select a justification type for the incident. Justification types are **False Positive**, **Manager Approved**, and **Others**.
- **Justification Reason**: Enter a justification reason in detail for the incident.

See image.

Your justification response is sent for review to the organization's admin, who is investigating the incident.

In the Overviewsection, you can see:

- **Incident ID**: The ID of the incident.
- **Incident Date**: The date on which the incident occurred.
- **Severity**: The severity of the incident. This field is not available for incidents with a Source DLP type of **Email**.
- **Resolution Date**: The date and time when the incident was resolved (i.e., closed). This field only appears for resolved incidents.

See image.

In the Violation Details section, you can see the various attributes for an incident.

The attributes that appear under the Originating User subsection can vary, depending on how and what information was imported to Workflow Automation through the primary user data source of System for Cross-domain Identity Management (SCIM) or a CSV file. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings), [Managing User Attributes](https://help.zscaler.com/workflow-automation/managing-user-attributes), and [SAML & SCIM Configuration Guide for Microsoft Entra ID](https://help.zscaler.com/zia/saml-scim-configuration-guide-microsoft-entra-id).

In the Violation Details section, you can see:

- Incidents of Source DLP Type Inline
- Incidents of Source DLP Type SaaS Security
- Incidents of Source DLP Type Endpoint
- Incidents of Source DLP Type Email

- Originating User: To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Name**: The name of the end user responsible for the incident. If you choose the User Name attribute for obfuscation, multiple asterisks appear in this field.
  - **Client IP**: The client IP address of the end user. If you choose the Client IP attribute for obfuscation, multiple asterisks appear in this field.
  - **Manager Name**: The name of the user's manager. If you choose the Manager Name attribute for obfuscation, multiple asterisks appear in this field.
  - **Manager Email**: The email address of the manager. If you choose the Manager Email attribute for obfuscation, multiple asterisks appear in this field.
  - **Department**: The department of the end user.
  - **Work Location**: The work location of the end user that is assigned by the organization.
  - **Additional Information**: Displays the user attributes associated with the end user responsible for the incident. To view the additional user attributes:
    1. Click the link provided in this field. The **Additional Information** window appears.
    2. In the **Additional Information** window, you can view data associated with the incident, such as end user attributes, manager attributes, and addresses. If you choose user attributes for obfuscation, these obfuscated attributes appear with multiple asterisks in this window. To learn more about obfuscation settings, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments). The system fetches the additional information from the primary user data source (i.e., CSV or SCIM) you selected when it generates the incident. For example, if you select **CSV** as the primary user data source during the incident generation, the **Additional Information** window displays the user attributes fetched from the imported CSV file. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings). The additional information displayed for an incident does not change if you alter the primary user data source. The change of the primary user data source settings impacts only new incidents. See image.
- Policy:
  - **Rules**: The DLP rules that the end user violated (e.g., Block-HIPAA).
  - **Engines**: The DLP engines associated with the incident.
  - **Dictionaries with Match Count**:The DLP dictionaries associated with the incident. The number of times the end user's traffic violated a specific dictionary is displayed in brackets (e.g., Medical Information[2]).
  - **Other Matched Rules**: Click this field to display the rules that the incident violated, in addition to the primary rules that caused the incident. This field is only available for incidents of Source DLP type **Inline** and **Endpoint**.

The policy fields are only available if the **Hide Policy Details - End User**field is not selected on the **Zscaler DLP Integration** page, the **Zscaler DLP Azure Integration** page, or the **Zscaler DLP GCP Integration** page in the Zscaler Admin Console. To learn more, see [Configuring the DLP Application Integration Using Amazon Web Services](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-amazon-web-services), [Configuring the DLP Application Integration Using Azure](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-azure), and [Configuring the DLP Application Integration Using Google Cloud Platform](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-google-cloud-platform).

- Content:
  - **File Name**: The name of the file.
  - **File Type**: The type or extension of the file.
  - **File MD5**: The 32-character MD5 hash of the file.
- Application:
  - **URL**: The URL of the application.
  - **Name**: The name of the application.
  - **Category**: The category of the application

The following image is an example of the Violation Details section for an Inline incident. The information that is displayed in the Violation Details section varies depending on the type of incident.

See image.

- Originating User: To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Name**: The name of the end user responsible for the incident. If you choose the User Name attribute for obfuscation, multiple asterisks appear in this field.
  - **Email**: The email address of the end user responsible for the incident.
  - **Manager Name**: The name of the user's manager. If you choose the Manager Name attribute for obfuscation, multiple asterisks appear in this field.
  - **Manager Email**: The email address of the manager. If you choose the Manager Email attribute for obfuscation, multiple asterisks appear in this field.
  - **Employee Number**: The employee number of the end user.
  - **Department**: The department of the end user.
  - **Work Location**: The work location of the end user that is assigned by the organization.
  - **Additional Information**: Displays the user attributes associated with the end user responsible for the incident. To view the additional user attributes:
    1. Click the link provided in this field. The **Additional Information** window appears.
    2. In the **Additional Information** window, you can view data associated with the incident, such as end user attributes, manager attributes, and addresses. If you choose user attributes for obfuscation, these obfuscated attributes appear with multiple asterisks in this window. To learn more about obfuscation settings, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments). The system fetches the additional information from the primary user data source (i.e., CSV or SCIM) you selected when it generates the incident. For example, if you select **CSV** as the primary user data source during the incident generation, the **Additional Information** window displays the user attributes fetched from the imported CSV file. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings). The additional information displayed for an incident does not change if you alter the primary user data source. The change of the primary user data source settings impacts only new incidents. See image.
- Policy:
  - **Rules**: The DLP rules that the end user violated (e.g., Block-HIPAA).
  - **Engines**: The DLP engines associated with the incident.
  - **Dictionaries with Match Count**:The DLP dictionaries associated with the incident. The number of times the end user's traffic violated a specific dictionary is displayed in brackets (e.g., Medical Information[2]).

The policy fields are only available if the **Hide Policy Details - End User**field is not selected on the **Zscaler DLP Integration** page, the **Zscaler DLP Azure Integration** page, or the **Zscaler DLP GCP Integration** page in the Zscaler Admin Console. To learn more, see [Configuring the DLP Application Integration Using Amazon Web Services](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-amazon-web-services), [Configuring the DLP Application Integration Using Azure](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-azure), and [Configuring the DLP Application Integration Using Google Cloud Platform](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-google-cloud-platform).

- Content:
  - **File Name**: The name of the file.
  - **File Type**: The type or extension of the file.
  - **File MD5**: The 32-character MD5 hash of the file.
  - **File Source Location**: The source location of the file.
  - **File Size**: The size of the file.
  - **Document Type**: The type of document.
  - **File Shared By**: The email address of the user who shared the file.
  - **File Shared At**: The date and time the file was shared.
  - **File Modified By**: The email address of the user who modified the file.
  - **File Link Expiry**: The date and time the file link expires.
- Application:
  - **Name**: The name of the application.
  - **Category**: The category of the application. Categories are **File** and **Email**.
  - **Current Tag Name**: The name of the tag currently assigned to the application.
  - **Is Copilot Accessible**: Indicates whether Copilot can access the application. Values are **yes** or **no**.

The following image is an example of the Violation Details section for a SaaS Security incident. The information that is displayed in the Violation Details section varies depending on the type of incident.

See image.

- Originating User: To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Name**: The name of the end user responsible for the incident. If you choose the User Name attribute for obfuscation, multiple asterisks appear in this field.
  - **Manager Name**: The name of the user's manager. If you choose the Manager Name attribute for obfuscation, multiple asterisks appear in this field.
  - **Manager Email**: The email address of the manager. If you choose the Manager Email attribute for obfuscation, multiple asterisks appear in this field.
  - **Department**: The department of the end user.
  - **Device Name**: The name of the end user's device.
  - **Device OS**: The operating system of the end user's device.
  - **Device Trust Level**: The trust level of the end user's device.
  - **Additional Information**: Displays the user attributes associated with the end user responsible for the incident. To view the additional user attributes:
    1. Click the link provided in this field. The **Additional Information** window appears.
    2. In the **Additional Information** window, you can view data associated with the incident, such as end user attributes, manager attributes, and addresses. If you choose user attributes for obfuscation, these obfuscated attributes appear with multiple asterisks in this window. To learn more about obfuscation settings, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments). The system fetches the additional information from the primary user data source (i.e., CSV or SCIM) you selected when it generates the incident. For example, if you select **CSV** as the primary user data source during the incident generation, the **Additional Information** window displays the user attributes fetched from the imported CSV file. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings). The additional information displayed for an incident does not change if you alter the primary user data source. The change of the primary user data source settings impacts only new incidents. See image.
- Policy:
  - **Rules**: The DLP rules that the end user violated (e.g., Block-HIPAA).
  - **Engines**: The DLP engines associated with the incident.
  - **Dictionaries with Match Count**:The DLP dictionaries associated with the incident. The number of times the end user's traffic violated a specific dictionary is displayed in brackets (e.g., Medical Information[2]).
  - **Other Matched Rules**: Click this field to display the rules that the incident violated, in addition to the primary rules that caused the incident. This field is only available for incidents of Source DLP type **Inline** and **Endpoint**.

The policy fields are only available if the **Hide Policy Details - End User**field is not selected on the **Zscaler DLP Integration** page, the **Zscaler DLP Azure Integration** page, or the **Zscaler DLP GCP Integration** page in the Zscaler Admin Console. To learn more, see [Configuring the DLP Application Integration Using Amazon Web Services](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-amazon-web-services), [Configuring the DLP Application Integration Using Azure](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-azure), and [Configuring the DLP Application Integration Using Google Cloud Platform](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-google-cloud-platform).

- Content:
  - **File Name**: The name of the file.
  - **File Type**: The type or extension of the file.
  - **File MD5**: The 32-character MD5 hash of the file.
  - **File Size**: The size of the file in bytes.
- User Activity:
  - **Activity Type**: The type of activity the user performed that caused the incident.
  - **Channel**: The type of channel (e.g., Network Drive Transfer or Remote Drive Transfer) that the user used to cause the incident.
  - **Source**: The source of the incident.
  - **Destination**: The destination of the incident.
  - **Source Type**: The source type of the incident.
  - **Destination Type**: The destination type (e.g., Removable Storage Device) for the incident.
  - **Source Location**: The source location of the incident.
  - **Destination Location**: The destination location for the incident.
  - **ZDP Mode**: The Zscaler Data Protection (ZDP) mode for the incident. ZDP modes can be **Block** or **Exemption**.
  - **Expected Action**: The expected action by the ZDP mode for the incident.
  - **Confirm Action**: The action that the user took when prompted with a confirmation dialog box for the incident creation.
  - **Confirm Justification**: The justification that the user provided during incident creation.
  - **Additional Information**: Additional information or notes about the incident.

The following image is an example of the Violation Details section for an Endpoint incident. The information that is displayed in the Violation Details section varies depending on the type of incident.

See image.

Originating User:

- **Name**: The name of the end user responsible for the incident. If you choose the User Name attribute for obfuscation, multiple asterisks appear in this field. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
- **Email Subject**: The subject of the email.
- **Application Name**: The name of the application used to create the email.
- **Work Location**: The work location of the end user that is assigned by the organization.
- **Additional Information**: Displays the user attributes associated with the end user responsible for the incident. To view the additional user attributes:
  1. Click the link provided in this field. The **Additional Information** window appears.
  2. In the **Additional Information** window, you can view data associated with the incident, such as end user attributes, manager attributes, and addresses. If you choose user attributes for obfuscation, these obfuscated attributes appear with multiple asterisks in this window. To learn more about obfuscation settings, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments). The system fetches the additional information from the primary user data source (i.e., CSV or SCIM) you selected when it generates the incident. For example, if you select **CSV** as the primary user data source during the incident generation, the **Additional Information** window displays the user attributes fetched from the imported CSV file. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings). The additional information displayed for an incident does not change if you alter the primary user data source. The change of the primary user data source settings impacts only new incidents. See image.

The following image is an example of the Violation Details section for an Email incident. The information that is displayed in the Violation Details section varies depending on the type of incident.

See image.

This field displays the presigned link of the incident and also the expiration date and time of the presigned link. It allows you to:

- Click the presigned link to download the actual data that triggered the incident before the expiration time.
- Click the **Copy** icon to copy the link for reference.

See image.

This field enables you to automatically download the actual data for the incident only if the **Hide** **Evidence Data - End User**field is not selected on the **Zscaler AWS DLP Integration** page, the **Zscaler DLP Azure Integration**page, or the **Zscaler DLP GCP Integration**page in the Zscaler Admin Console.

This field displays the data that triggered the incident. It displays the prefix and suffix for the trigger data, along with the trigger data itself. The actual trigger data portion is highlighted. You can view the DLP dictionaries, DLP rules, and DLP engines associated with the incident and the exact data that violated the DLP policies.

See image.

This field is available only if the **Hide** **Trigger Data - End User** field is not selected on the **Zscaler AWS DLP Integration** page, the **Zscaler DLP Azure Integration**page, or **Zscaler DLP GCP Integration**page in the Zscaler Admin Console.

This section is only available for incidents of Source DLP type **Email**. For the other types of incidents, the policy information for the incident appears in the Violation Details section.

In the Policy section, you can see:

- **Recipients Email**: The email address of the recipient who received the incident. If you choose the Recipient Email attribute for obfuscation, multiple asterisks appear in this field. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
- **Rule**: The DLP rule that the end user violated.
- **Other Matched Rules**: The other rules that the incident violated in addition to the primary DLP rule that caused the incident.

See image

[Image: Viewing the end user's notification email]

[Image: Viewing the end user's notification message in Slack]

[Image: Viewing the end user's notification message in Microsoft Teams]

[Image: Responding to an end user notification using the survey]

[Image: Viewing the Violation Details section for an Inline incident on the Incident page]

[Image: Viewing the Violation Details section for a SaaS Security incident on the Incident page]

[Image: Viewing the Violation Details section for an Endpoint incident on the Incident page]

[Image: Viewing the Violation Details section for an Email incident on the Incident page]

[Image: Viewing the Policy section for an Email incident on the Incident page]

[Image: Viewing the Overview section on the Incident page]

[Image: Viewing the Generate Presigned Link section on the Incident page]

[Image: Viewing the View Trigger Data section on the Incident page]

[Image: Viewing the Release Email to Recipients section on the Incident page. This section contains options to release to all recipients, release to select recipients, and to not release.]

[Image: Viewing the Release Email to Recipients section on the Incident page. The Release to select recipients is highlighted, and the Email table is shown with a couple of the recipient email addresses selected.]

[Image: Viewing the Policy Exception section on the Incident page. This section contains options to approve or reject a policy exception for an incident.]

[Image: Viewing the Policy Exception section on the Incident page. The Approve option is selected and the Duration in Minutes is entered.]

[Image: Viewing additional information for an Email incident in the Additional Information window]

[Image: Viewing additional information for an Endpoint incident in the Additional Information window]

[Image: Viewing additional information for a SaaS Security incident in the Additional Information window]

[Image: Viewing additional information for an Inline incident in the Additional Information window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/responding-escalation-notification","lastmod":"2026-07-10T10:42Z","nid":"1433821"} -->
## Responding to an Escalation Notification

- Source: https://help.zscaler.com/workflow-automation/responding-escalation-notification
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Incident Management > Responding to an Escalation Notification
- Last modified: 2026-07-10T10:42Z
- Summary: Information on how to review and respond to an escalation notification from the Zscaler Admin Console.

The format of the notification and survey might not be the same as illustrated in this article. It depends upon the notification and the survey template that your organization configured in Workflow Automation.

If you are a manager or an approver, the Zscaler Admin Console sends you an alert notification (e.g., email, Slack message, or Microsoft Teams message) about transactions (incidents) that violate Data Loss Prevention (DLP) policies. The notification contains an Incident Detail Link associated with the survey that you must complete to approve or suggest the next steps for the incident.

An incident can be escalated when the justification submitted by the end user is not acceptable or when the incident is of high severity and priority. If the organization accepts the justification that the end user submits, then you are not notified about that incident.

## Viewing the Notification

When an incident occurs, you receive a notification (e.g., email, Slack message, or Microsoft Teams message) requesting justification. The notification contains the following information:

- The date and time when the incident occurred.
- The Incident Detail Link, where you can view the incident details and also take a survey to justify the incident. This link is only valid for 24 hours.
- The incident ID.
- The hostname or the application of the incident.
- The priority of the incident.
- The note to the user from the admin who initiated the notification. This note appears only if the admin enters a note.

The following images are examples of a user notification sent by email, Slack message, and Microsoft Teams message. The notifications contain the same information, but the format of the message is different.

See image.

Depending on your email settings, notification emails might be tagged as spam and sent to your spam folder. Change your settings to receive the notification emails directly in your inbox.

## Viewing the Incident Details

To view the details of the incident, click the **Incident Detail Link** provided in the user notification. You are redirected to the **Incident** page, where you can view the following details:

- Overview
- Violation Details
- Generate Presigned Link
- View Trigger Data
- Policy
- Notification Response History

### Releasing Quarantined Emails for Email Incidents

After reviewing the incident details for a quarantined email incident, you can choose to release the email to one or more of its recipients by selecting one of the options that appears in the **Release Email to Recipients** section. This section is only available for incidents with a Source DLP type of Emailthat have a quarantine action, and if you have enabled the Enable Email Quarantine Release for End Users option on the [Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) page.

See image.

To release the quarantined email to its recipients, use one of the following methods:

- To release all the email recipients, select **Release to all recipients**.
- To release one or more of the email recipients:
  1. Select **Release to select recipients**. An **Email** table appears, listing the email addresses of the recipients who were sent the quarantined email.
  2. In the **Email** table, select the checkbox next to one or more of the recipient email addresses you want to release the email to. See image.

If you do not want to release the email to any recipients, select **Do not release**. This option is selected by default.

### Creating a Policy Exception for an Incident

After reviewing the details of an incident, you can choose to approve or reject a policy exception for the incident. If you approve the policy exception, you can then enter the duration for the policy exception. This section only appears for the escalation notifications that are generated for an incident through a custom workflow where a policy exception tile has been configured. To learn more, see [Managing Workflows](https://help.zscaler.com/workflow-automation/managing-workflows).

This section is only available for incidents of Source DLP types Inline.

See image.

To create a policy exception for the incident:

1. Select **Approve**. The **Duration in Minutes** field appears below the **Approve** option.
2. In the **Duration in Minutes** field, enter the duration for the policy exception in minutes. After you submit the survey for the notification, the system creates a policy exception. Workflow Automation creates a subrule for the rule associated with the incident in the Zscaler Admin Console. The subrule applies only to the user associated with the incident. You can view this subrule on the **Data Loss Prevention** page in the Zscaler Admin Console. To learn more, see [About Data Loss Prevention](https://help.zscaler.com/zia/about-data-loss-prevention). See image.

If you do not want to create a policy exception, select **Reject**.

### Completing the Survey

After reviewing the incident details, you might be required to complete a survey at the end of the Incident page to justify your action.

To provide a justification, complete the following survey and then click **Submit**:

- **Justification Type**: Select a justification type for the incident. Justification types are **False Positive**, **Manager Approved**, and **Others**.
- **Justification Reason**: Enter a justification reason in detail for the incident.

See image.

Your justification response is sent for review to the organization's admin, who is investigating the incident.

In the Overviewsection, you can see:

- **Incident ID**: The ID of the incident.
- **Incident Date**: The date on which the incident occurred.
- **Severity**: The severity of the incident. This field is not available for incidents with a Source DLP type of **Email**.
- **Resolution Date**: The date and time when the incident was resolved (i.e., closed). This field only appears for resolved incidents.

See image.

In the Violation Details section, you can see the various attributes for an incident.

The attributes that appear under the Originating User subsection can vary, depending on how and what information was imported to Workflow Automation through the primary user data source of System for Cross-domain Identity Management (SCIM) or a CSV file. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings), [Managing User Attributes](https://help.zscaler.com/workflow-automation/managing-user-attributes), and [SAML & SCIM Configuration Guide for Microsoft Entra ID](https://help.zscaler.com/zia/saml-scim-configuration-guide-microsoft-entra-id).

In the Violation Details section, you can see:

- Incidents of Source DLP Type Inline
- Incidents of Source DLP Type SaaS Security
- Incidents of Source DLP Type Endpoint
- Incidents of Source DLP Type Email

- Originating User: To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Name**: The name of the end user responsible for the incident. If you choose the User Name attribute for obfuscation, multiple asterisks appear in this field.
  - **Client IP**: The client IP address of the end user. If you choose the Client IP attribute for obfuscation, multiple asterisks appear in this field.
  - **Manager Name**: The name of the user's manager. If you choose the Manager Name attribute for obfuscation, multiple asterisks appear in this field.
  - **Manager Email**: The email address of the manager. If you choose the Manager Email attribute for obfuscation, multiple asterisks appear in this field.
  - **Department**: The department of the end user.
  - **Work Location**: The work location of the end user that is assigned by the organization.
  - **Additional Information**: Displays the user attributes associated with the end user responsible for the incident. To view the additional user attributes:
    1. Click the link provided in this field. The **Additional Information** window appears.
    2. In the **Additional Information** window, you can view data associated with the incident, such as end user attributes, manager attributes, and addresses. If you choose user attributes for obfuscation, these obfuscated attributes appear with multiple asterisks in this window. To learn more about obfuscation settings, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments). The system fetches the additional information from the primary user data source (i.e., CSV or SCIM) you selected when it generates the incident. For example, if you select **CSV** as the primary user data source during the incident generation, the **Additional Information** window displays the user attributes fetched from the imported CSV file. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings). The additional information displayed for an incident does not change if you alter the primary user data source. The change of the primary user data source settings impacts only new incidents. See image.
- Policy:
  - **Rules**: The DLP rules that the end user violated (e.g., Block-HIPAA).
  - **Engines**: The DLP engines associated with the incident.
  - **Dictionaries with Match Count**:The DLP dictionaries associated with the incident. The number of times the end user's traffic violated a specific dictionary is displayed in brackets (e.g., Medical Information[2]).
  - **Other Matched Rules**: Click this field to display the rules that the incident violated, in addition to the primary rules that caused the incident. This field is only available for incidents of Source DLP type **Inline** and **Endpoint**.

The policy fields are only available if the **Hide Policy Details - End User**field is not selected on the **Zscaler DLP Integration** page, the **Zscaler DLP Azure Integration** page, or the **Zscaler DLP GCP Integration** page in the Zscaler Admin Console. To learn more, see [Configuring the DLP Application Integration Using Amazon Web Services](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-amazon-web-services), [Configuring the DLP Application Integration Using Azure](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-azure), and [Configuring the DLP Application Integration Using Google Cloud Platform](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-google-cloud-platform).

- Content:
  - **File Name**: The name of the file.
  - **File Type**: The type or extension of the file.
  - **File MD5**: The 32-character MD5 hash of the file.
- Application:
  - **URL**: The URL of the application.
  - **Name**: The name of the application.
  - **Category**: The category of the application

The following image is an example of the Violation Details section for an Inline incident. The information that is displayed in the Violation Details section varies depending on the type of incident.

See image.

- Originating User: To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Name**: The name of the end user responsible for the incident. If you choose the User Name attribute for obfuscation, multiple asterisks appear in this field.
  - **Email**: The email address of the end user responsible for the incident.
  - **Manager Name**: The name of the user's manager. If you choose the Manager Name attribute for obfuscation, multiple asterisks appear in this field.
  - **Manager Email**: The email address of the manager. If you choose the Manager Email attribute for obfuscation, multiple asterisks appear in this field.
  - **Employee Number**: The employee number of the end user.
  - **Department**: The department of the end user.
  - **Work Location**: The work location of the end user that is assigned by the organization.
  - **Additional Information**: Displays the user attributes associated with the end user responsible for the incident. To view the additional user attributes:
    1. Click the link provided in this field. The **Additional Information** window appears.
    2. In the **Additional Information** window, you can view data associated with the incident, such as end user attributes, manager attributes, and addresses. If you choose user attributes for obfuscation, these obfuscated attributes appear with multiple asterisks in this window. To learn more about obfuscation settings, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments). The system fetches the additional information from the primary user data source (i.e., CSV or SCIM) you selected when it generates the incident. For example, if you select **CSV** as the primary user data source during the incident generation, the **Additional Information** window displays the user attributes fetched from the imported CSV file. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings). The additional information displayed for an incident does not change if you alter the primary user data source. The change of the primary user data source settings impacts only new incidents. See image.
- Policy:
  - **Rules**: The DLP rules that the end user violated (e.g., Block-HIPAA).
  - **Engines**: The DLP engines associated with the incident.
  - **Dictionaries with Match Count**:The DLP dictionaries associated with the incident. The number of times the end user's traffic violated a specific dictionary is displayed in brackets (e.g., Medical Information[2]).

The policy fields are only available if the **Hide Policy Details - End User**field is not selected on the **Zscaler DLP Integration** page, the **Zscaler DLP Azure Integration** page, or the **Zscaler DLP GCP Integration** page in the Zscaler Admin Console. To learn more, see [Configuring the DLP Application Integration Using Amazon Web Services](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-amazon-web-services), [Configuring the DLP Application Integration Using Azure](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-azure), and [Configuring the DLP Application Integration Using Google Cloud Platform](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-google-cloud-platform).

- Content:
  - **File Name**: The name of the file.
  - **File Type**: The type or extension of the file.
  - **File MD5**: The 32-character MD5 hash of the file.
  - **File Source Location**: The source location of the file.
  - **File Size**: The size of the file.
  - **Document Type**: The type of document.
  - **File Shared By**: The email address of the user who shared the file.
  - **File Shared At**: The date and time the file was shared.
  - **File Modified By**: The email address of the user who modified the file.
  - **File Link Expiry**: The date and time the file link expires.
- Application:
  - **Name**: The name of the application.
  - **Category**: The category of the application. Categories are **File** and **Email**.
  - **Current Tag Name**: The name of the tag currently assigned to the application.
  - **Is Copilot Accessible**: Indicates whether Copilot can access the application. Values are **yes** or **no**.

The following image is an example of the Violation Details section for a SaaS Security incident. The information that is displayed in the Violation Details section varies depending on the type of incident.

See image.

- Originating User: To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Name**: The name of the end user responsible for the incident. If you choose the User Name attribute for obfuscation, multiple asterisks appear in this field.
  - **Manager Name**: The name of the user's manager. If you choose the Manager Name attribute for obfuscation, multiple asterisks appear in this field.
  - **Manager Email**: The email address of the manager. If you choose the Manager Email attribute for obfuscation, multiple asterisks appear in this field.
  - **Department**: The department of the end user.
  - **Device Name**: The name of the end user's device.
  - **Device OS**: The operating system of the end user's device.
  - **Device Trust Level**: The trust level of the end user's device.
  - **Additional Information**: Displays the user attributes associated with the end user responsible for the incident. To view the additional user attributes:
    1. Click the link provided in this field. The **Additional Information** window appears.
    2. In the **Additional Information** window, you can view data associated with the incident, such as end user attributes, manager attributes, and addresses. If you choose user attributes for obfuscation, these obfuscated attributes appear with multiple asterisks in this window. To learn more about obfuscation settings, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments). The system fetches the additional information from the primary user data source (i.e., CSV or SCIM) you selected when it generates the incident. For example, if you select **CSV** as the primary user data source during the incident generation, the **Additional Information** window displays the user attributes fetched from the imported CSV file. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings). The additional information displayed for an incident does not change if you alter the primary user data source. The change of the primary user data source settings impacts only new incidents. See image.
- Policy:
  - **Rules**: The DLP rules that the end user violated (e.g., Block-HIPAA).
  - **Engines**: The DLP engines associated with the incident.
  - **Dictionaries with Match Count**:The DLP dictionaries associated with the incident. The number of times the end user's traffic violated a specific dictionary is displayed in brackets (e.g., Medical Information[2]).
  - **Other Matched Rules**: Click this field to display the rules that the incident violated, in addition to the primary rules that caused the incident. This field is only available for incidents of Source DLP type **Inline** and **Endpoint**.

The policy fields are only available if the **Hide Policy Details - End User**field is not selected on the **Zscaler DLP Integration** page, the **Zscaler DLP Azure Integration** page, or the **Zscaler DLP GCP Integration** page in the Zscaler Admin Console. To learn more, see [Configuring the DLP Application Integration Using Amazon Web Services](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-amazon-web-services), [Configuring the DLP Application Integration Using Azure](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-azure), and [Configuring the DLP Application Integration Using Google Cloud Platform](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-google-cloud-platform).

- Content:
  - **File Name**: The name of the file.
  - **File Type**: The type or extension of the file.
  - **File MD5**: The 32-character MD5 hash of the file.
  - **File Size**: The size of the file in bytes.
- User Activity:
  - **Activity Type**: The type of activity the user performed that caused the incident.
  - **Channel**: The type of channel (e.g., Network Drive Transfer or Remote Drive Transfer) that the user used to cause the incident.
  - **Source**: The source of the incident.
  - **Destination**: The destination of the incident.
  - **Source Type**: The source type of the incident.
  - **Destination Type**: The destination type (e.g., Removable Storage Device) for the incident.
  - **Source Location**: The source location of the incident.
  - **Destination Location**: The destination location for the incident.
  - **ZDP Mode**: The Zscaler Data Protection (ZDP) mode for the incident. ZDP modes can be **Block** or **Exemption**.
  - **Expected Action**: The expected action by the ZDP mode for the incident.
  - **Confirm Action**: The action that the user took when prompted with a confirmation dialog box for the incident creation.
  - **Confirm Justification**: The justification that the user provided during incident creation.
  - **Additional Information**: Additional information or notes about the incident.

The following image is an example of the Violation Details section for an Endpoint incident. The information that is displayed in the Violation Details section varies depending on the type of incident.

See image.

Originating User:

- **Name**: The name of the end user responsible for the incident. If you choose the User Name attribute for obfuscation, multiple asterisks appear in this field. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
- **Email Subject**: The subject of the email.
- **Application Name**: The name of the application used to create the email.
- **Work Location**: The work location of the end user that is assigned by the organization.
- **Additional Information**: Displays the user attributes associated with the end user responsible for the incident. To view the additional user attributes:
  1. Click the link provided in this field. The **Additional Information** window appears.
  2. In the **Additional Information** window, you can view data associated with the incident, such as end user attributes, manager attributes, and addresses. If you choose user attributes for obfuscation, these obfuscated attributes appear with multiple asterisks in this window. To learn more about obfuscation settings, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments). The system fetches the additional information from the primary user data source (i.e., CSV or SCIM) you selected when it generates the incident. For example, if you select **CSV** as the primary user data source during the incident generation, the **Additional Information** window displays the user attributes fetched from the imported CSV file. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings). The additional information displayed for an incident does not change if you alter the primary user data source. The change of the primary user data source settings impacts only new incidents. See image.

The following image is an example of the Violation Details section for an Email incident. The information that is displayed in the Violation Details section varies depending on the type of incident.

See image.

This field displays the presigned link of the incident and also the expiration date and time of the presigned link. It allows you to:

- Click the presigned link to download the actual data that triggered the incident before the expiration time.
- Click the **Copy** icon to copy the link for reference.

See image.

This field enables you to automatically download the actual data for the incident only if the **Hide** **Evidence Data - End User**field is not selected on the **Zscaler AWS DLP Integration** page, the **Zscaler DLP Azure Integration**page, or the **Zscaler DLP GCP Integration**page in the Zscaler Admin Console.

This field displays the data that triggered the incident. It displays the prefix and suffix for the trigger data, along with the trigger data itself. The actual trigger data portion is highlighted. You can view the DLP dictionaries, DLP rules, and DLP engines associated with the incident and the exact data that violated the DLP policies.

See image.

This field is available only if the **Hide** **Trigger Data - End User** field is not selected on the **Zscaler AWS DLP Integration** page, the **Zscaler DLP Azure Integration**page, or **Zscaler DLP GCP Integration**page in the Zscaler Admin Console.

This section is only available for incidents of Source DLP type **Email**. For the other types of incidents, the policy information for the incident appears in the Violation Details section.

In the Policy section, you can see:

- **Recipients Email**: The email address of the recipient who received the incident. If you choose the Recipient Email attribute for obfuscation, multiple asterisks appear in this field. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
- **Rule**: The DLP rule that the end user violated.
- **Other Matched Rules**: The other rules that the incident violated in addition to the primary DLP rule that caused the incident.

See image

The Notification Response History table displays a detailed log of the incident's end user notifications and escalations sent to the originating user and the user's manager or approver, respectively.

The table displays the following information:

- **User**: The person to whom the admin sends the details of the incident. If you choose the User Email attribute or the Manager Email attribute for obfuscation, multiple asterisks appear in this field for the manager or user. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
- **Role**: The role of the user. The roles are **Originating User**, **Manager**, or **Approver**.
- **Initial Notification Date**: The date and time when the first notification was sent to a user.
- **Response Date**: The date and time when the user responded to the notification.
- **User Response**: The actual response received from the user.

See image.

[Image: Viewing the Notification Response History section on the Incident page]

[Image: Viewing an example of an email escalation notification. The escalation notification contains the email address of the end user responsible for the incident, the date and time the incident occurred, the incident ID, the hostname or the application of the incident, the priority of the incident, and a note to the approver from the admin who initiated the escalation. In addition, it contains an Incident Detail Link, where you can view the details of the incident and complete a survey to respond to the incident.]

[Image: Viewing an example of a Slack escalation notification. The escalation notification contains the email address of the end user responsible for the incident, the date and time the incident occurred, the incident ID, the hostname or the application of the incident, the priority of the incident, and a note to the approver from the admin who initiated the escalation. In addition, it contains an Incident Detail Link, where you can view the details of the incident and complete a survey to respond to the incident.]

[Image: Viewing an example of a Teams escalation notification. The escalation notification contains the email address of the end user responsible for the incident, the date and time the incident occurred, the incident ID, the hostname or the application of the incident, the priority of the incident, and a note to the approver from the admin who initiated the escalation. In addition, it contains an Incident Detail Link, where you can view the details of the incident and complete a survey to respond to the incident.]

[Image: Viewing the Survey section on the Incident page]

[Image: Viewing the Additional Information window displaying multiple attributes (i.e., Organization, Division, Phone Number, Project IDs, Termination Date, Worker Type, Organization Hierarchy, Location, User Role, and Manager Information) for the incident attributes that were imported to Workflow Automation via SCIM or CSV]

[Image: Viewing the Violation Details section for an Inline incident on the Incident page]

[Image: Viewing the Additional Information window displaying multiple attributes (i.e., Organization, Division, Phone Number, Project IDs, Termination Date, Worker Type, Organization Hierarchy, Location, User Role, and Manager Information) for the incident attributes that were imported to Workflow Automation via SCIM or CSV]

[Image: Viewing the Violation Details section for a SaaS Security incident on the Incident page]

[Image: Viewing the Additional Information window displaying multiple attributes (i.e., Organization, Division, Phone Number, Project IDs, Termination Date, Worker Type, Organization Hierarchy, Location, User Role, and Manager Information) for the incident attributes that were imported to Workflow Automation via SCIM or CSV]

[Image: Viewing the Violation Details section for an Endpoint incident on the Incident page]

[Image: Viewing the Additional Information window displaying multiple attributes (i.e., Organization, Division, Phone Number, Project IDs, Termination Date, Worker Type, Organization Hierarchy, Location, User Role, and Manager Information) for the incident attributes that were imported to Workflow Automation via SCIM or CSV]

[Image: Viewing the Violation Details section for an Email incident on the Incident page]

[Image: Viewing the Policy section for an Email incident on the Incident page]

[Image: Viewing the Overview section on the Incident page]

[Image: Viewing the Generate Presigned Link section on the Incidents page]

[Image: Viewing the View Trigger Data section on the Incident page]

[Image: Viewing the Release Email to Recipients section on the Incident page. This section contains options to release to all recipients, release to select recipients, and to not release.]

[Image: Viewing the Release Email to Recipients section on the Incident page. The Release to select recipients is highlighted and the Email table is shown with an couple of the recipient email addresses selected.]

[Image: Viewing the Policy Exception section on the Incident page. This section contains options to approve or reject a policy exception for an incident.]

[Image: Viewing the Policy Exception section on the Incident page. The Approve option is selected and the Duration in Minutes is entered.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/responding-user-digest-notification","lastmod":"2026-07-23T21:06Z","nid":"1447086"} -->
## Responding to a User Digest Notification

- Source: https://help.zscaler.com/workflow-automation/responding-user-digest-notification
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Incident Management > Responding to a User Digest Notification
- Last modified: 2026-07-23T21:06Z
- Summary: Information on how to review and respond to a user digest notification from Workflow Automation.

The format of the notification might not be the same as illustrated in this article. It depends upon the digest notification template that your organization configured in Workflow Automation.

If a channel (e.g., Email, Slack, or Teams) is enabled for the User Digest notification flags on the Account Settings page, Workflow Automation uses a digest notification template to generate and send a user digest notification daily via that channel to all users who have incidents assigned to them. Workflow Automation provides default user digest notification templates (Digest - Email Template, Digest - Slack Template, and Digest - Teams Template) that you can clone and use for these notifications. These default digest notifications list the number of all open incidents and the number of open incidents that have a high priority. They also provide a link to the Incidents page, where the user can manage these incidents. The digest notifications contain the same information, but the format of the message and where it is delivered varies depending on the channel. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings), [Managing Notification Templates](https://help.zscaler.com/workflow-automation/managing-notification-templates), and [Managing Incident and Digest Template Mappings](https://help.zscaler.com/workflow-automation/managing-incident-and-digest-template-mappings).

## Viewing the User Digest Notification

If you have incidents that are open or in progress, a digest notification (e.g., email, Slack message, or Microsoft Teams message) is sent to you. The digest notification contains the following information:

- The number of open and in-progress incidents that require your attention.
- The number of high-priority incidents that require your attention.
- A link to the Incidents page, where you can view a list of your assigned incidents and also take a survey to justify or approve the incidents. This link is only valid for 24 hours.

The incident count provided in the message is accurate as of the date and time the notification is generated. However, the count is subject to change as new incidents are created, reassigned, or resolved.

The following images are examples of a digest notification email, Slack message, and Microsoft Teams message. The digest notifications contain the same information, but the format of the message is different.

See image.

Your email settings might tag the digest notification emails as spam and send them to your spam folder. Change your settings to receive the digest notification emails directly in your inbox.

## Viewing the Incidents

To view your assigned incidents, click the **View all incidents assigned to me**link provided in the use digest notification. The **Incidents** page appears, listing all the open or in-progress incidents that require your attention. For each incident, you can view the following information:

- **Last Change Date**: The date and time the incident was last changed.
- **Transaction ID**: The transaction ID for the incident.
- **Status**: The status of the incident. Statuses are:
  - **New**
  - **Investigating**
  - **Validating with User**
  - **Justification Response Received**
  - **Escalated**
- **Action**: The action required for the incident. Actions are:
  - **Justify**: When you click this action, you are redirected to the **Justify** page, where you can view the incident details and complete the justification survey for the incident.
  - **Approve**: When you click this action, you are redirected to the **Approve** page, where you can view the incident details and complete the approval survey for the incident.

See image.

To view only the incidents where you are the owner, select the **My Incidents** checkbox at the top-left of the page.

## Viewing the Incident Details

To view the details of an incident, on the **Incidents** page, click the **Justify**or **Approve** action next to the incident. You are redirected to either the **Justify**or**Approve** page, where you can view the following details:

- Overview
- Violation Details
- Policy
- Violation Content

### Completing the Survey

After reviewing the incident details, you might be required to complete the survey at the end of the JustifyorApprove page to justify, approve, or suggest the next steps for the incident.

To respond, complete the following survey and then click **Submit**:

- **Justification Type**: Select a justification type for the incident. Justification types are **False Positive**, **Manager Approved**, and **Others**.
- **Justification Reason**: Enter a response to proceed with the incident.

See image.

Your response is sent to the organization's admin investigating the incident for further review.

In the Overviewsection, you can see:

- **Incident ID**: The ID of the incident.
- **Incident Date**: The date on which the incident occurred.
- **Severity**: The severity of the incident. This field is not available for incidents with a Source DLP type of **Email**.
- **Resolution Date**: The date and time when the incident was resolved (i.e., closed). This field only appears for resolved incidents.

See image.

In the Violation Details section, you can see:

The attributes that appear under the Originating User subsection can vary, depending on what information was imported to Workflow Automation and whether it was imported via the primary user data source of System for Cross-domain Identity Management (SCIM) or a CSV file. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings), [Managing User Attributes](https://help.zscaler.com/workflow-automation/managing-user-attributes), and [SAML & SCIM Configuration Guide for Microsoft Entra ID](https://help.zscaler.com/zia/saml-scim-configuration-guide-microsoft-entra-id).

- Incidents of Source DLP Type Inline
- Incidents of Source DLP Type SaaS Security
- Incidents of Source DLP Type Endpoint
- Incidents of Source DLP Type Email

- Originating User: To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Name**: The name of the end user responsible for the incident. If you choose the User Name attribute for obfuscation, multiple asterisks appear in this field.
  - **Client IP**: The client IP address of the end user. If you choose the Client IP attribute for obfuscation, multiple asterisks appear in this field.
  - **Manager Name**: The name of the user's manager. If you choose the Manager Name attribute for obfuscation, multiple asterisks appear in this field.
  - **Manager Email**: The email address of the manager. If you choose the Manager Email attribute for obfuscation, multiple asterisks appear in this field.
  - **Employee Number**: The employee number of the end user.
  - **Department**: The department of the end user.
  - **Home Location**: The home location of the end user.
  - **Work Location**: The work location of the end user.
  - **Additional Information**: Displays the user attributes associated with the end user responsible for the incident. To view the additional user attributes:
    1. Click the link provided in this field. The **Additional Information** window appears.
    2. In the **Additional Information** window, you can view data associated with the incident, such as end user attributes, manager attributes, and addresses. If you choose user attributes for obfuscation, these obfuscated attributes appear with multiple asterisks in this window. The additional information is fetched from the primary user data source (i.e., CSV or SCIM) you selected during the incident generation. For example, if you select **CSV** as the primary user data source during the incident generation, the **Additional Information** window displays the user attributes fetched from the imported CSV file. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings). The additional information displayed for an incident does not change if you alter the primary user data source. Changing the primary user data source settings impacts only new incidents. See image.
- Policy:
  - **Rules**: The DLP rules that the end user violated (e.g., Block-HIPAA).
  - **Engines**: The DLP engines associated with the incident.
  - **Dictionaries with Match Count**:The DLP dictionaries associated with the incident. The number of times the end user's traffic violated a specific dictionary is displayed in brackets (e.g., Medical Information[2]).
  - **Other Matched Rules**: Click this field to display the rules that the incident violated, in addition to the primary rules that caused the incident. This field is only available for incidents of Source DLP type **Inline** and **Endpoint**.

The policy fields are only available if the **Hide Policy Details - End User**field is not selected on the **Zscaler DLP Integration** page, the **Zscaler DLP Azure Integration**page, or the **Zscaler DLP GCP Integration**page in the Zscaler Admin Console. To learn more, see [Configuring the DLP Application Integration Using Amazon Web Services](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-amazon-web-services), [Configuring the DLP Application Integration Using Azure](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-azure), and [Configuring the DLP Application Integration Using Google Cloud Platform](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-google-cloud-platform).

- Content:
  - **File Name**: The name of the file.
  - **File Type**: The type or extension of the file.
  - **File MD5**: The 32-character MD5 hash of the file.
- Application:
  - **URL**: The URL of the application.
  - **Name**: The name of the application.
  - **Category**: The category of the application.

The following image is an example of the Violation Details section for an Inline incident. The information that is displayed in the Violation Details section varies depending on the type of incident.

See image.

- Originating User: To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Name**: The name of the end user responsible for the incident. If you choose the User Name attribute for obfuscation, multiple asterisks appear in this field.
  - **Email**: The email address of the end user responsible for the incident.
  - **Manager Name**: The name of the user's manager. If you choose the Manager Name attribute for obfuscation, multiple asterisks appear in this field.
  - **Manager Email**: The email address of the manager. If you choose the Manager Email attribute for obfuscation, multiple asterisks appear in this field.
  - **Employee Number**: The employee number of the end user.
  - **Department**: The department of the end user.
  - **Work Location**: The work location of the end user.
  - **Additional Information**: Displays the user attributes associated with the end user responsible for the incident. To view the additional user attributes:
    - Click the link provided in this field. The **Additional Information** window appears.
    - In the **Additional Information** window, you can view data associated with the incident, such as end user attributes, manager attributes, and addresses. If you choose user attributes for obfuscation, these obfuscated attributes appear with multiple asterisks in this window. The additional information is fetched from the primary user data source (i.e., CSV or SCIM) you selected during the incident generation. For example, if you select **CSV** as the primary user data source during the incident generation, the **Additional Information** window displays the user attributes fetched from the imported CSV file. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings). The additional information displayed for an incident does not change if you alter the primary user data source. Changing the primary user data source settings impacts only new incidents. See image.
- Policy:
  - **Rules**: The DLP rules that the end user violated (e.g., Block-HIPAA).
  - **Engines**: The DLP engines associated with the incident.
  - **Dictionaries with Match Count**:The DLP dictionaries associated with the incident. The number of times the end user's traffic violated a specific dictionary is displayed in brackets (e.g., Medical Information[2]).

The policy fields are only available if the **Hide Policy Details** **- End User**field is not selected on the **Zscaler DLP Integration** page, the **Zscaler DLP Azure Integration**page, or the **Zscaler DLP GCP Integration**page in the Zscaler Admin Console. To learn more, see [Configuring the DLP Application Integration Using Amazon Web Services](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-amazon-web-services), [Configuring the DLP Application Integration Using Azure](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-azure), and [Configuring the DLP Application Integration Using Google Cloud Platform](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-google-cloud-platform).

- Content:
  - **File Name**: The name of the file.
  - **File Type**: The type or extension of the file.
  - **File MD5**: The 32-character MD5 hash of the file.
  - **File Source Location**: The source location of the file.
  - **File Size**: The size of the file.
  - **Document Type**: The type of document.
  - **File Shared By**: The email address of the user who shared the file.
  - **File Shared At**: The date and time the file was shared.
  - **File Modified By**: The email address of the user who modified the file.
  - **File Link Expiry**: The date and time the file link expires.
- Application:
  - **Name**: The name of the application.
  - **Category**: The category of the application. Categories are **File** and **Email**.
  - **Current Tag Name**: The name of the tag currently assigned to the application.
  - **Is Copilot Accessible**: Indicates whether Copilot can access the application. Values are **yes** or **no**.

The following image is an example of the Violation Details section for a SaaS Security incident. The information that is displayed in the Violation Details section varies depending on the type of incident.

See image.

- Originating User: To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Name**: The name of the end user responsible for the incident. If you choose the User Name attribute for obfuscation, multiple asterisks appear in this field.
  - **Manager Name**: The name of the user's manager. If you choose the Manager Name attribute for obfuscation, multiple asterisks appear in this field.
  - **Manager Email**: The email address of the manager. If you choose the Manager Email attribute for obfuscation, multiple asterisks appear in this field.
  - **Department**: The department of the end user.
  - **Device Name**: The name of the end user's device.
  - **Device OS**: The operating system of the end user's device.
  - **Device Trust Level**: The trust level of the end user's device.
  - **Additional Information**: Displays the user attributes associated with the end user responsible for the incident. To view the additional user attributes:
    - Click the link provided in this field. The **Additional Information** window appears.
    - In the **Additional Information** window, you can view data associated with the incident, such as end user attributes, manager attributes, and addresses. If you choose user attributes for obfuscation, these obfuscated attributes appear with multiple asterisks in this window. The additional information is fetched from the primary user data source (i.e., CSV or SCIM) you selected during the incident generation. For example, if you select **CSV** as the primary user data source during the incident generation, the **Additional Information** window displays the user attributes fetched from the imported CSV file. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings). The additional information displayed for an incident does not change if you alter the primary user data source. Changing the primary user data source settings impacts only new incidents. See image.
- Policy:
  - **Rules**: The DLP rules that the end user violated (e.g., Block-HIPAA).
  - **Engines**: The DLP engines associated with the incident.
  - **Dictionaries with Match Count**:The DLP dictionaries associated with the incident. The number of times the end user's traffic violated a specific dictionary is displayed in brackets (e.g., Medical Information[2]).
  - **Other Matched Rules**: Click this field to display the rules that the incident violated, in addition to the primary rules that caused the incident. This field is only available for incidents of Source DLP type **Inline** and **Endpoint**.

The policy fields are only available if the **Hide Policy Details - End User**field is not selected on the **Zscaler DLP Integration** page, the **Zscaler DLP Azure Integration**page, or the **Zscaler DLP GCP Integration**page in the Zscaler Admin Console. To learn more, see [Configuring the DLP Application Integration Using Amazon Web Services](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-amazon-web-services), [Configuring the DLP Application Integration Using Azure](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-azure), and [Configuring the DLP Application Integration Using Google Cloud Platform](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-google-cloud-platform).

- Content:
  - **File Name**: The name of the file.
  - **File Type**: The type or extension of the file.
  - **File MD5**: The 32-character MD5 hash of the file.
  - **File Size**: The size of the file in bytes.
- User Activity:
  - **Activity Type**: The type of activity that the user performed that caused the incident.
  - **Channel**: The type of channel (e.g., Network Drive Transfer or Remote Drive Transfer) that the user used to cause the incident.
  - **Source**: The source of the incident.
  - **Destination**: The destination of the incident.
  - **Source Type**: The source type of the incident.
  - **Destination Type**: The destination type (e.g., Removable Storage Device) for the incident.
  - **Source Location**: The source location of the incident.
  - **Destination Location**: The destination location for the incident.
  - **ZDP Mode**: The Zscaler Data Protection (ZDP) mode for the incident. ZDP modes can be **Block** or **Exemption**.
  - **Expected Action**: The expected action by the ZDP mode for the incident.
  - **Confirm Action**: The action that the user took when prompted with a confirmation dialog box for the incident creation.
  - **Confirm Justification**: The justification that the user provided during incident creation.
  - **Additional Information**: Additional information or notes about the incident.

The following image is an example of the Violation Details section for an Endpoint incident. The information that is displayed in the Violation Details section varies depending on the type of incident.

See image.

Originating User:

- **Name**: The name of the end user responsible for the incident. If you choose the User Name attribute for obfuscation, multiple asterisks appear in this field. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
- **Email Subject**: The subject of the email.
- **Application Name**: The name of the application used to create the email.
- **Additional Information**: Displays the user attributes associated with the end user responsible for the incident. To view the additional user attributes:
  - Click the link provided in this field. The **Additional Information** window appears.
  - In the **Additional Information** window, you can view data associated with the incident, such as end user attributes, manager attributes, and addresses. If you choose user attributes for obfuscation, these obfuscated attributes appear with multiple asterisks in this window. The additional information is fetched from the primary user data source (i.e., CSV or SCIM) you selected during the incident generation. For example, if you select **CSV** as the primary user data source during the incident generation, the **Additional Information** window displays the user attributes fetched from the imported CSV file. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings). The additional information displayed for an incident does not change if you alter the primary user data source. Changing the primary user data source settings impacts only new incidents. See image.

The following image is an example of the Violation Details section for an Email incident. The information that is displayed in the Violation Details section varies depending on the type of incident.

See image.

This section is only available for incidents of Source DLP type **Email**. For other types of incidents, the policy information for the incident appears in the Violation Details section.

In the Policy section, you can see:

- **Recipients Email**: The email address of the user who received the incident. If you choose the Recipient Email attribute for obfuscation, multiple asterisks appear in this field. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
- **Rule**: The DLP rule that the end user violated.
- **Other Matched Rules**: The other rules that the incident violated in addition to the primary DLP rule that caused the incident.

See image.

The **Generate Presigned Link** field is not available for incidents of Source DLP type **Email**.

In the Violation Content section, you can see:

- Generate Presigned Link
- View Trigger Data

This field displays the presigned link of the incident and also the link's expiration date and time. It allows you to:

- Before the expiration time, click the presigned link to download the actual data that triggered the incident.
- Click the **Copy** icon to copy the link for reference. See image.

When justifying an incident, this field enables users to automatically download the actual data that triggered an incident only if the **Hide Evidence Data - End User** field is not selected for the DLP integration. When approving an incident, this field enables approvers to automatically download the actual data that triggered an incident only if the **Hide Evidence Data - Manager/Approver** field is not selected for the DLP integration. To learn more, see [Configuring the DLP Application Integration Using Amazon Web Services](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-amazon-web-services), [Configuring the DLP Application Integration Using Azure](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-azure), and [Configuring the DLP Application Integration Using Google Cloud Platform](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-google-cloud-platform).

When justifying an incident, this field is available only if the **Hide Trigger Data - End User** field is not selected for the DLP integration. When approving an incident, this field is available only if the **Hide Evidence Data - Manager/Approver** field is not selected for the DLP integration. To learn more, see [Configuring the DLP Application Integration Using Amazon Web Services](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-amazon-web-services), [Configuring the DLP Application Integration Using Azure](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-azure), and [Configuring the DLP Application Integration Using Google Cloud Platform](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-google-cloud-platform).

This field displays the data that triggered the incident. The prefix and suffix for the trigger data are displayed along with the trigger data itself. The actual trigger data portion is highlighted. You can view the DLP dictionaries, DLP rules, and DLP engines associated with the incident and the exact data that violated the DLP policies.

See image.

[Image: Viewing an example of an email user digest notification. The user digest notification contains the number of open and in-progress incidents that require your attention and the number of high-priority incidents that require your attention. It also contains a link to the Incidents page, where you can view a list of your assigned incidents and also take a survey to justify or approve the incidents.]

[Image: Viewing an example of a Slack user digest notification. The user digest notification contains the number of open and in-progress incidents that require your attention and the number of high-priority incidents that require your attention. It also contains a link to the Incidents page, where you can view a list of your assigned incidents and also take a survey to justify or approve the incidents.]

[Image: Viewing an example of a Teams user digest notification. The user digest notification contains the number of open and in-progress incidents that require your attention and the number of high-priority incidents that require your attention. It also contains a link to the Incidents page, where you can view a list of your assigned incidents and also take a survey to justify or approve the incidents.]

[Image: Viewing the list of incidents for the User Digest Notification generated from Workflow Automation]

[Image: Viewing the Overview section on the Justify page or Approve page for the user digest notification]

[Image: Viewing the Violation Details section for an Inline incident when responding to a user digest notification]

[Image: Viewing the Additional Information window displaying multiple attributes (i.e., Organization, Division, Phone Number, Project IDs, Termination Date, Worker Type, Organization Hierarchy, Location, User Role, and Manager Information) for the incident that were imported to Workflow Automation via SCIM or CSV]

[Image: Viewing the Violation Details section for a SaaS Security incident when responding to a user digest notification]

[Image: Viewing the Additional Information window displaying multiple attributes (i.e., Organization, Division, Phone Number, Project IDs, Termination Date, Worker Type, Organization Hierarchy, Location, User Role, and Manager Information) for the incident that were imported to Workflow Automation via SCIM or CSV]

[Image: Viewing the Violation Details section for an Endpoint incident when responding to a user digest notification]

[Image: Viewing the Additional Information window displaying multiple attributes (i.e., Organization, Division, Phone Number, Project IDs, Termination Date, Worker Type, Organization Hierarchy, Location, User Role, and Manager Information) for the incident that were imported to Workflow Automation via SCIM or CSV]

[Image: Viewing the Violation Details section for an Email incident when responding to a user digest notification]

[Image: Viewing the Additional Information window displaying multiple attributes (i.e., Organization, Division, Phone Number, Project IDs, Termination Date, Worker Type, Organization Hierarchy, Location, User Role, and Manager Information) for the incident that were imported to Workflow Automation via SCIM or CSV]

[Image: Viewing the Policy Section for an Email incident when responding to a user digest notification]

[Image: Viewing the Generate Presigned Link when responding to a user digest notification]

[Image: Viewing the View Trigger Data section when responding to a user digest notification]

[Image: Viewing the Survey section on the Justify page or Approve page for the user digest notification]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/step-step-configuration-guide-workflow-automation-data-protection","lastmod":"2026-05-04T12:13Z","nid":"1417806"} -->
## Step-by-Step Configuration Guide for Workflow Automation for Data Protection

- Source: https://help.zscaler.com/workflow-automation/step-step-configuration-guide-workflow-automation-data-protection
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Step-by-Step Configuration Guide for Workflow Automation for Data Protection
- Last modified: 2026-05-04T12:13Z
- Summary: Step-by-Step Configuration Guide for Workflow Automation for Data Protection.

This guide takes you through the configuration steps you must complete to begin using Workflow Automation for your organization.

Before you begin configuring Workflow Automation, Zscaler recommends reading the following articles:

- [What Is Workflow Automation?](https://help.zscaler.com/workflow-automation/what-workflow-automation)
- [Managing Incidents](https://help.zscaler.com/workflow-automation/managing-incidents)
- [Viewing & Managing Incident Details](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details)
- [Managing Incident Summaries](https://help.zscaler.com/workflow-automation/managing-incident-summaries)

## Prerequisites

Because Workflow Automation integrates with Internet & SaaS (ZIA), you must set up and configure Internet & SaaS and configure the Data Loss Prevention (DLP) policies before you configure Workflow Automation. To learn more, see the [Step-By-Step Configuration Guide for Internet & SaaS](https://help.zscaler.com/zia/step-step-configuration-guide-zia).

## Configuring Workflow Automation

To configure Workflow Automation, complete the following steps:

- Step 1: Provision Your Admins in Internet & SaaS to Access Workflow Automation
- Step 2: Configure the DLP Application Integration for Your Organization
- Step 3: (Optional) Integrate Workflow Automation with Slack
- Step 4: (Optional) Integrate Workflow Automation with Microsoft Teams
- Step 5: (Optional) Integrate Workflow Automation with a Ticketing Integration Application
- Step 6: Configure Roles and Permissions for the Admins in Workflow Automation
- Step 7: Configure Incident Groups in Workflow Automation
- Step 8: Configure Incident Group Permissions for Restricted Workflow Admins in Workflow Automation
- Step 9: Configure Incident Group Priorities in Workflow Automation
- Step 10: Configure Incident Approvers in Workflow Automation
- Step 11: Configure Account Settings in Workflow Automation
- Step 12: Configure User Attributes in Workflow Automation
- Step 13: Configure and Map Notification and Survey Templates in Workflow Automation
- Step 14: (Optional) Configure Labels in Workflow Automation
- Step 15: (Optional) Configure Workflows in Workflow Automation
- Step 16: (Optional) Configure Custom Email Domains in Workflow Automation

To manage incidents in Workflow Automation, admins must be provisioned in Internet & SaaS with a role that has the **Workflow Access** permission configured. You can configure admins with full or restricted workflow access to Workflow Automation.

To provision administrators as applicable, see:

- [About Administrators](https://help.zscaler.com/zia/about-administrators)
- [Adding Admins](https://help.zscaler.com/zia/adding-zia-admins)
- [About Role Management](https://help.zscaler.com/zia/about-role-management)
- [Adding Admin Roles](https://help.zscaler.com/zia/adding-admin-roles)

After the admins have been provisioned with Workflow Access permission, proceed with the next step to configure the DLP application integration in Amazon Web Services (AWS), Google Cloud Platform (GCP), or Microsoft Azure.

To enable your organization's DLP incident transactions to appear as incidents in Workflow Automation, you must configure a DLP application integration for your organization. You can configure a DLP application integration using AWS, GCP, or Microsoft Azure.

To configure a DLP application integration, see:

- [Configuring the DLP Application Integration Using Amazon Web Services](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-amazon-web-services)
- [Configuring the DLP Application Integration Using Azure](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-azure)
- [Configuring the DLP Application Integration Using Google Cloud Platform](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-google-cloud-platform)

To use Slack messages for the notifications (user, escalation, and digest) that admins or the application initiate during the incident remediation process, you must integrate Workflow Automation with Slack.

To integrate Workflow Automation with Slack, see [Managing Workflow Automation Integration with Slack](https://help.zscaler.com/workflow-automation/managing-workflow-automation-integration-slack).

To use Microsoft Teams messages for the notifications (user, escalation, and digest) that admins or the application initiate during the incident remediation process, you must integrate Workflow Automation with Microsoft Teams.

To integrate Workflow Automation with Microsoft Teams, see [Managing Workflow Automation Integration with Microsoft Teams](https://help.zscaler.com/workflow-automation/managing-workflow-automation-integration-microsoft-teams).

During the remediation process for a data protection incident in Workflow Automation, admins can create and associate a ticket from ServiceNow or Jira Software with the incident. To be able to create and associate a ticket with an incident, you must integrate Workflow Automation with ServiceNow or Jira Software and add the users for those integrations on the Integration Users page in Workflow Automation.

To integrate Workflow Automation with a ticketing integration application (ServiceNow or Jira Software), see:

- [Managing Workflow Automation Integration with ServiceNow](https://help.zscaler.com/workflow-automation/managing-workflow-automation-integration-servicenow)
- [Managing Workflow Automation Integration with Jira Software](https://help.zscaler.com/workflow-automation/managing-workflow-automation-integration-jira-software)
- [Managing Integration Users](https://help.zscaler.com/workflow-automation/managing-integration-users)

To assist with the management of incidents that appear on the Incidents page, admins can add and map incident groups. Admins with full workflow access to Workflow Automation can map incident groups to the different properties associated with an incident transaction.

To configure incident groups, see:

- [Managing Incident Groups](https://help.zscaler.com/workflow-automation/managing-incident-groups)
- [Managing Incident Group Mappings](https://help.zscaler.com/workflow-automation/managing-incident-group-mappings)

Only admins with full access to Workflow Automation can configure roles and assign them to restricted workflow access admins during an admin assignment. Configure roles to complete the admin assignments for DLP admins. For each role, you can assign Edit, View, or None permissions to access and manage different Workflow Automation features.

To configure roles and permissions for DLP admins, see [Managing Roles and Permissions](https://help.zscaler.com/workflow-automation/managing-roles-and-permissions).

Admins who have been provisioned with the restricted workflow access permission in Internet & SaaS must have their incident group permissions configured in Workflow Automation. An admin with full access to Workflow Automation configures their incident group permissions.

To configure incident group permissions for restricted workflow access admins in Workflow Automation, see [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).

Admins can prioritize the DLP incidents that appear on the Incidents page in Workflow Automation by assigning a priority to the different incident groups.

To configure incident group priorities, see [Managing Priorities](https://help.zscaler.com/workflow-automation/managing-priorities).

On the Incidents page or the Incident Details page, admins can escalate an incident to an approver who is defined in Workflow Automation. An approver can be the manager of the end user responsible for the incident or any other individual.

To configure approvers, see [Managing Approvers](https://help.zscaler.com/workflow-automation/managing-approvers).

On the Account Settings page, admins can:

- Enter the expiration period for the justification links on user and escalation notifications.
- Enable or disable the generation of user digest notifications by channel for their organization, and enter the expiration period for the justification links on the user digest notifications.
- Enable or disable the generation of DLP admin digest notifications by channel for their organization, and enter the expiration period for the justification links on the DLP admin digest notifications.
- Enable or disable the generation of notification reminders.
- Specify trusted email domains for approver email addresses.
- Copy the customer Globally Unique Identifier (GUID) for their organization.
- Choose the primary data source for user information.
- Choose the user's unique identifier.
- Choose the privacy and security user data-obfuscation settings for their organization.
- Choose the primary data source for retrieving the user's email address.
- Choose settings for the release quarantine feature for incidents of Source DLP type Email with a Quarantine Action.

To configure account settings, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings).

On the User Attributes page, admins can import the end user attributes as CSV files, from which Workflow Automation fetches the user attributes that display in Workflow Automation. To view the user attributes imported through a CSV file in Workflow Automation, you must select CSV as the primary data source and select a unique identifier on the [Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) page.

To configure user attributes, see [Managing User Attributes](https://help.zscaler.com/workflow-automation/managing-user-attributes).

While investigating an incident, admins can notify the end user who caused the incident and ask for justification for the incident. They can also escalate the incident to an approver, if required. When an admin performs either of these actions for an incident, an email is sent to the end user or approver using a notification template and a survey template that was created in Workflow Automation. Workflow Automation provides default notification templates and survey templates, but admins can configure custom notification or survey templates, if they choose. After admins define these new custom templates, they must also map them to a DLP Type, Notification Type, and Source Action.

In addition, a notification template is also used for the email digest that Workflow Automation can send.

To configure and map notification and survey templates, see:

- [Managing Notification Templates](https://help.zscaler.com/workflow-automation/managing-notification-templates)
- [Managing Survey Templates](https://help.zscaler.com/workflow-automation/managing-survey-templates)
- [Managing Incident and Digest Template Mappings](https://help.zscaler.com/workflow-automation/managing-incident-and-digest-template-mappings)

Admins can label the DLP incidents that appear on the Incidents page or the Incident Details page in Workflow Automation by assigning a label to the incident.

To configure labels, see [Managing Labels.](https://help.zscaler.com/workflow-automation/managing-labels)

Admins can configure predefined and custom workflows to assist them with remediating incidents that occur in their organization. Workflows are automatically triggered for those incidents that match the mapped criteria for the workflows. Workflows can contain one or more actions to be performed against an incident and do not require user intervention.

To configure and map workflows, see:

- [Understanding Workflows in Workflow Automation](https://help.zscaler.com/workflow-automation/understanding-workflows-workflow-automation)
- [Managing Workflow Templates](https://help.zscaler.com/workflow-automation/managing-workflow-templates)
- [Managing Workflows](https://help.zscaler.com/workflow-automation/managing-workflows)
- [Managing Workflow Mappings](https://help.zscaler.com/workflow-automation/managing-workflow-mappings)

Admins can configure custom email domains for their organization. Workflow Automation generates and sends email notifications for the various actions that you can perform, such as notifying the user of an incident, escalating an incident to an approver or manager, and performing bulk actions. After you configure a custom email domain, Workflow Automation uses that custom email domain as the sender for these email notifications.

To configure custom email domains, see [Managing Custom Email Domains](https://help.zscaler.com/workflow-automation/managing-custom-email-domains).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/understanding-duplicate-incidents-workflow-automation","lastmod":"2026-06-30T21:06Z","nid":"1486281"} -->
## Understanding Duplicate Incidents in Workflow Automation

- Source: https://help.zscaler.com/workflow-automation/understanding-duplicate-incidents-workflow-automation
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Incident Management > Understanding Duplicate Incidents in Workflow Automation
- Last modified: 2026-06-30T21:06Z
- Summary: Information on duplicate incidents in Workflow Automation.

Duplicate incidents can occur in Workflow Automation when a user violates the same Data Loss Prevention (DLP) rule multiple times during a two-hour time frame. Duplicate incidents are only applicable to Inline source DLP type incidents. Workflow Automation does not track duplicate incidents for SaaS Security, Email, or Endpoint source DLP type incidents.

To determine whether an incident is a duplicate of another incident, Workflow Automation looks at the following attributes. All of these attributes must be present in the incident:

- Host name from URL
- End user email
- MD5 hash of the content
- File name

For incidents resulting from DLP rules without content inspection (i.e., the Engines attribute value is External DLP), the MD5 value does not need to be present for Workflow Automation to determine duplicate incidents.

It is possible that sites such as Gmail can indefinitely send duplicate incidents until the page is refreshed or the tab is closed. Because Workflow Automation cannot look back indefinitely in time, it only looks back two hours. In this case, Workflow Automation creates a duplicate incident every two hours.

On the Incidents page in the Zscaler Admin Console, you can see the total count of duplicate incidents that have occurred for an incident, and you can also filter the incidents by duplicate incidents. On the Incident Details page, you can use the Duplicate Incidents dialog window to view all the duplicate incidents for a specific incident. To learn more, see [Managing Incidents](https://help.zscaler.com/workflow-automation/managing-incidents) and [Viewing & Managing Incident Details](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/understanding-notification-reminders","lastmod":"2026-07-14T08:26Z","nid":"1535308"} -->
## Understanding Notification Reminders

- Source: https://help.zscaler.com/workflow-automation/understanding-notification-reminders
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Incident Management > Understanding Notification Reminders
- Last modified: 2026-07-14T08:26Z
- Summary: Information about notification reminders in Workflow Automation.

Workflow Automation can send out notification reminders to those end users, managers, and approvers that have not responded to end user or escalation notifications that were previously sent to them. You must configure these notification reminders for your organization on the Account Settings page before they are sent.

On the Account Settings page, you can:

- Enable or disable notification reminders for end users, managers, and approvers.
- Configure the frequency at which Workflow Automation sends these notification reminders.
- Configure the maximum number of notification reminders that the system sends.

See image.

After you have enabled notification reminders, the system automatically sends these notification reminders at the frequency you configured. These notification reminders are identical to the notifications the end users, managers, and approvers previously received but have not yet responded to. Plus, the system updates the information (i.e., Number of Attempts) that appears for the incident in the User Notifications section on the Incident Details page, and it logs the reminder activity for the incident in the State Changes section for the incident on the Incident Details page.

See image.

The system stops sending notification reminders after the end users, managers, or approvers respond to the original notification or a reminder notification, or after the maximum number of reminder notifications that you configured have been sent.

To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings), [Viewing & Managing Incident Details](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details), and [Managing Incidents](https://help.zscaler.com/workflow-automation/managing-incidents).

[Image: Viewing the Notification Reminder tab on the Account Settings page]

[Image: Viewing the Incident Details page with the notification reminder information highlighted]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/understanding-workflows-workflow-automation","lastmod":"2026-04-08T11:46Z","nid":"1455976"} -->
## Understanding Workflows in Workflow Automation

- Source: https://help.zscaler.com/workflow-automation/understanding-workflows-workflow-automation
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Workflows > Understanding Workflows in Workflow Automation
- Last modified: 2026-04-08T11:46Z
- Summary: Information about workflows in Workflow Automation.

Workflow Automation enables admins to view and remediate data protection incidents that have occurred in their organization. To remediate incidents, admins can:

- Manually perform different actions (e.g., investigate, notify user, escalate, create ticket, and close incident) for one or more incidents using the Incidents page and Incident Details page. To learn more, see [Managing Incidents](https://help.zscaler.com/workflow-automation/managing-incidents) and [Viewing & Managing Incident Details.](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details)
- Configure predefined and custom workflows that automatically perform the different actions (e.g., notify user, escalate, create ticket, and close incident) for one or more incidents. To learn more, see [Managing Workflow Templates](https://help.zscaler.com/workflow-automation/managing-workflow-templates), [Managing Workflows](https://help.zscaler.com/workflow-automation/managing-workflows), and [Managing Workflow Mappings](https://help.zscaler.com/workflow-automation/managing-workflow-mappings).

Admins can use one or both of these methods to remediate the incidents. Depending on the number and type of incidents that occur in your organization, one method might be preferred over the other.

The users, managers, and approvers respond to the actions that a workflow generates (e.g., notify user and escalate) in the same way as if an admin manually performed those actions on the Incidents page or Incident Details page.

## Workflow Configuration

You can configure predefined workflows and custom workflows in Workflow Automation. When you configure a predefined workflow, you must select a workflow template on which to base the workflow and also enter the details for the workflow steps within that particular workflow template. Depending on the workflow template, there might be one or more steps and different details required for each step, such as notification channel and time to wait for user response. Workflow Automation provides the following workflow templates that you can use:

- **Auto Close Data Protection Incident With Resolution Label**: This template automatically sets the status of the incident as Resolved and adds a resolution label for the closure.
- **Auto Close Data Protection Incident**: This template automatically sets the status of the incident as Resolved.
- **Auto Create Tickets**: This template automatically creates a ticket in the ticketing integration application (e.g., ServiceNow or Jira Software).
- **Auto Escalate**: This template automatically escalates the incident to the user's manager or to an approver if the manager is not found in the system.
- **Auto Notify**: This template automatically notifies the user who generated the incident, through the configured channel (i.e., email, Slack, or Microsoft Teams).
- **Auto Notify User and Close Incident**: This template automatically notifies the user who generated the incident, through the configured channel (i.e., email, Slack, or Microsoft Teams), and closes the incident if a response is not received from the user after a configurable time period in seconds.
- **Auto Notify User and Concurrently Escalate**: This template automatically notifies the user who generated the incident, through the configured channel (i.e., email, Slack, or Microsoft Teams), as well as automatically escalating the incident to the manager or approver without waiting for a response from the user.
- **Auto Notify User and Escalate**: This template automatically notifies the user who generated the incident, through the configured channel (i.e., email, Slack, or Microsoft Teams), and escalates to their manager or approver if the user doesn't respond after a configurable time period in seconds.
- **Auto Notify User and Escalate to Manager**: This template automatically notifies the user who generated the incident, through the configured channel (i.e., email, Slack, or Microsoft Teams), and optionally escalates to their manager if the user doesn't respond after a configurable time period in seconds.

When you configure a custom workflow, you choose and configure the different steps and actions required for the workflow without using one of the templates.

To learn more, see [Managing Workflow Templates](https://help.zscaler.com/workflow-automation/managing-workflow-templates) and [Managing Workflows](https://help.zscaler.com/workflow-automation/managing-workflows).

After you configure a predefined or custom workflow, you must specify the incidents that use this workflow by mapping the workflow to one or more of the attributes available in an incident transaction. Then, when an incident occurs in your organization that contains those attributes, the workflow automatically triggers and performs those actions specified in the workflow. To learn more, see [Managing Workflow Mappings](https://help.zscaler.com/workflow-automation/managing-workflow-mappings).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/using-incident-filters-workflow-automation","lastmod":"2026-07-16T06:13Z","nid":"1468511"} -->
## Using Incident Filters in Workflow Automation

- Source: https://help.zscaler.com/workflow-automation/using-incident-filters-workflow-automation
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Incident Management > Using Incident Filters in Workflow Automation
- Last modified: 2026-07-16T06:13Z
- Summary: How to use and manage incident filters in Workflow Automation.

In the Zscaler Admin Console, the incidents that occur in your organization are displayed on the Incidents page. You can use filters to modify the incidents that are displayed on the page. Some of the filters have predefined values that you select, and some of the filters require you to enter a text string value. The following filters have predefined values:

- **Action**
- **Attachments**
- **Channel**
- **Department**
- **Destination Type**
- **Dictionary**
- **DLP Admin**
- **DLP Type**
- **Document Type**
- **Duplicated Incidents**
- **Engine**
- **External Collaborators Groups**
- **File Type**
- **Home Location**
- **Incident Group**
- **Integration**
- **Label**
- **Other Rule**
- **Priority**
- **Rule**
- **Severity**
- **Source DLP Type**
- **Status**
- **Work Location**

To use these predefined filters, select to include or exclude one or more of the predefined values that appear for these filters.

You must enter a text string for the following filters:

- **Application Category**
- **Application Name**
- **Client IP**
- **Component**
- **Content Location**
- **Domains**
- **External Recipients**
- **File Modified By**
- **File Name**
- **File Shared By**
- **File Source Location**
- **Hostname or Application**
- **Internal Recipients**
- **Originating User Email**
- **Originating User Name**
- **Protocol**
- **Referrer URL**
- **Remediation Owner Email**
- **Triggered Recipients**
- **URL**
- **User**
- **User Groups**
- **Workspace**

The text string values are not case sensitive. In addition, you can:

- Enter multiple values for these filters by entering a comma between the text string values.
- Enter multiple strings within a single text string value. If you enter multiple strings within a text string value, each string is treated with an AND operation. For example, if you select the **Application Name** filter and enter `Microsoft Office` as the text string value, all incidents with an application name containing Microsoft and Office are returned with this filter, such as Microsoft Office, Microsoft Office 365, and Office 365 Microsoft. This filter does not return incidents with an application name containing only Microsoft or Office.
- For the Referrer URL filter and the URL filter, enter a complete URL (e.g., https://www.jumpshare.com/https-post) or enter a complete host name (e.g., www.jumpshare.com) for the URL.

You can:

- Apply Filters
- Save Filters
- Apply Saved Filters
- Delete Saved Filters

To apply filters:

1. On the **Incidents** page, click **Filters**. The **Filters** window appears, displaying all the available filters on the left side of the window. If you choose the **User Name** attribute, the **Client IP** attribute, or the **Recipient Email** attribute for obfuscation, you cannot filter incidents using these obfuscated user attributes. Obfuscating the **Recipient Email** attribute disables the **External Recipients** filter, the **Internal Recipients**filter, and the **Triggered Recipients** filter. To learn more about obfuscation settings, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments). See image.
2. Select a filter and then select or enter the filter values to include or exclude on the right side of the window. As you select or enter the filter values, the number of values selected appears next to the filter. You can select multiple filters and their values. For filters with predefined values (e.g., **Department** and **Dictionary**), you can select to include or exclude all the filter values by selecting the **Select All**checkbox. To remove all the selected filters, click **Reset**. See image.
3. Click **Apply**. The **Incidents** page reappears, displaying the incidents that match the filter criteria.

To save filters:

1. On the **Incidents** page, click **Filters**. The **Filters** window appears, displaying all the available filters on the left side of the window. See image.
2. Select a filter and then select or enter the filter values to include or exclude on the right side of the window. You can select multiple filters and their values. For filters with predefined values (e.g., **Department** and **Dictionary**), you can select to include or exclude all the filter values by selecting the **Select All**checkbox. See image.
3. Click **Save As.**The **My Filters** dialog window appears.
4. In the **My Filters** dialog window, enter a name for the filter in the **Filter Name** field. See image.
5. Click **Save**. The filter is saved and is available for selection under the **My Filters** drop-down menu in the **Filters** window. See image.

To apply saved filters:

1. On the **Incidents** page, click **Filters**. The **Filters** window appears, displaying all the available filters on the left side of the window. See image.
2. Select a saved filter from the **My Filters** drop-down menu. The **Filters** window reappears, displaying the filters associated with that saved filter. See image.
3. Click **Apply**. The **Incidents** page reappears, displaying the incidents that match the filter criteria.

To delete saved filters:

1. On the **Incidents** page, click **Filters**. The **Filters** window appears, displaying all the available filters on the left side of the window. See image.
2. From the **My Filters** drop-down menu, click the **Delete** icon next to the saved filter that you want to delete. A message appears asking whether you are sure that you want to delete the filter. See image.
3. Click **Yes**. The saved filter is deleted.

[Image: Viewing the Filters window on the Incidents page in the Zscaler Admin Console]

[Image: Viewing the Filters window with a few of the filter options selected to include and exclude for the Department filter. Accounting, Engineering, and Sales department filter options are included. Service Admin and Support department filter options are excluded.]

[Image: Viewing the Filters window on the Incidents page with all the available filters displayed]

[Image: Viewing the Filters window with the Department filter and Dictionary filter selected]

[Image: Entering a filter name on the My Filters dialog window in the Filters window]

[Image: Viewing the saved filters in the Filters window in the Zscaler Admin Console. The My Filters drop-down menu shows options for Credit Card Information, Resolved Incidents, Social Security Number Incidents - Critical, Social Security Number Incidents - Inline - Critical, and Social Security Number Incidents - Accounting Department.]

[Image: Viewing the Filters window on the Incidents page with all the available filters displayed]

[Image: Selecting a saved filter in the My Filters drop-down menu on the Filters window in the Zscaler Admin Console]

[Image: Viewing the Filters window on the Incidents page with all the available filters displayed]

[Image: Filters window displaying the following message after you click the Delete icon next to a saved filter: Are you sure you want to delete? Along with No and Yes buttons for the response.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/viewing-alert-notifications","lastmod":"2026-04-10T11:28Z","nid":"1452701"} -->
## Viewing Alert Notifications

- Source: https://help.zscaler.com/workflow-automation/viewing-alert-notifications
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Alerts and Settings > Viewing Alert Notifications
- Last modified: 2026-04-10T11:28Z
- Summary: How to view alert notifications in Workflow Automation.

The Notification Center page in Workflow Automation displays alerts that affect the operation of Workflow Automation. The following are the alert notifications that you might view on the Notification Center page:

| Module | Alert Summary | Severity |
| --- | --- | --- |
| Filewatcher | The Filewatcher is experiencing issues for Host IP: {`hostIP` } SystemId: {`systemId`} with integration: {`integrationName`} | High |
| Incident Group | Default incident group does not have an admin | High |
| Incident Receiver | The IR is experiencing issues for SystemId: `{systemId}` with integration: {`integrationName}` | High |
| Integration | `{integrationName}` integration is not working | High |
| Notification | No active channel for user digest notification | High |
| Notification | No active channel for admin digest notification | High |

To view alert notifications:

Go to **Administration** > **Workflow Automation** > **Data Protection** > **Alerts**. The **Notification Center** page appears, listing all the alert notifications. For each alert notification, you can view:

- **ID**: The ID associated with the alert notification. It is a unique generated sequential ID.
- **Module**: The feature area of Workflow Automation that is related to this alert notification. For example, Filewatcher, Integration, Notification, Incident Receiver, or Incident Group.
- **Severity**: The severity of the alert notification.
- **Summary**: The summary description of the alert notification.
- **First Detected**: The date and time the alert issue was first detected.
- **Last Detected**: The date and time the alert issue was last detected.

See image.

[Image: Viewing Alert Notifications on the Notification Center page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/viewing-managing-incident-details","lastmod":"2026-07-10T12:32Z","nid":"1420336"} -->
## Viewing & Managing Incident Details

- Source: https://help.zscaler.com/workflow-automation/viewing-managing-incident-details
- Product: Workflow Automation
- Path: Workflow Automation Help > Workflow Automation for Data Protection > Incident Management > Viewing & Managing Incident Details
- Last modified: 2026-07-10T12:32Z
- Summary: Information about the Incidents Details page and how to manage incidents in the Zscaler Admin Console.

Workflow Automation provides access to the Incident Details page, which displays detailed information about an incident, such as an overview of the incident, violation details, and the current status of the incident. This page also allows you to manage and take action on an incident.

You can access theIncident Details page from the [Incidents](https://help.zscaler.com/workflow-automation/managing-incidents) page by clicking the **Transaction ID** of an incident. On the Incident Details page, you can use the **Next Incident** and **Previous Incident**icons at the top of the page to navigate through the list of incidents, and you can click the **Refresh** button at the top of the page to display the latest information for the incident.

## Viewing Incident Details

You can view the following details about the incident:

- Duplicate Incidents
- Overview
- Violation Details
- Current State Details
- Ticket
- Notes
- Violation Content
- Files
- Recipients
- Collaborators
- Attachments
- User Notifications
- State Changes

Sometimes a rule can retrigger duplicate incidents for a user. In this case, the first incident appears on the Incidents page, and you can view all the duplicate messages on that incident's detail page.

If duplicate incidents exist for an incident, at the top of its detail page, a message appears stating that fact and providing a link to view the duplicate incidents. If there are no duplicate incidents, this message does not appear on the page.

See image.

To view the duplicate incidents, click the link at the top of the page. The **Duplicate Incidents** dialog window appears, displaying all the duplicate incidents for the incident. In the **Duplicate Incidents** dialog window, you can see:

- **Transaction ID**: The transaction ID of the duplicate incident.
- **Transaction Time**: The date and time of the transaction. The date and time display in the local time zone of the user.

See image.

To learn more, see [Understanding Duplicate Incidents in Workflow Automation](https://help.zscaler.com/workflow-automation/understanding-duplicate-incidents-workflow-automation).

In the Overview section, you can see:

- **Incident ID**: The ID of the incident.
- **System Creation Date**: The date and time when the incident was created in the system.
- **Incident Date**: The date and time when the incident was generated due to a policy violation. The date and time display in the local time zone of the user.
- **Severity**: The severity of the incident. Severities can be **Critical**, **High**, **Low**, **Medium**, and **Info**.
- **Priority**: The priority of the incident. Priorities are **Critical**, **High**, **Medium**, and **Low**.
- **Action**: The action associated with the incident. This field is not available for incidents with a Source DLP type of **Email**.
- **DLP Admin**: The Data Loss Prevention (DLP) admin who is responsible for validating the incident.
- **Source DLP Type**: The source DLP type of the incident. Source DLP types are **Inline**, **Email**,**SaaS Security**, and **Endpoint**.
- **Incident Groups**: The incident groups mapped to the incident.
- **Labels**: The labels assigned to the incident.
- **Action Recipient Count**: The actions taken against the recipients of the incident. The number of times the action was taken against the recipients displays next to the action (e.g., Block: 3, Allow: 1, and Quarantine: 2). This field is only available for incidents with a Source DLP type of **Email**.
- **Resolution Date**: The date and time when the incident was resolved (i.e., closed). This field only appears for resolved incidents.
- **Integration**: The name of the DLP application integration in Workflow Automation where the incident occurred. To learn more, see [Configuring the DLP Application Integration Using Amazon Web Services](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-amazon-web-services), [Configuring the DLP Application Integration Using Azure](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-azure), and [Configuring the DLP Application Integration Using Google Cloud Platform](https://help.zscaler.com/workflow-automation/configuring-dlp-application-integration-using-google-cloud-platform).
- **Quarantined Email Status**: The status of the quarantined email release process for the recipients of the quarantined email. This field is only available for incidents with a Source DLP type of **Email**. To view the quarantined email status:
  1. Click the **View Status** link provided in this field. The **Quarantined Email Status**page appears.
  2. On the **Quarantined Email Status**page, you can view: See image.
    - **Status Summary**:
      - **Quarantined**: The number of recipients that are quarantined.
      - **Quarantine Release In Progress**: The number of recipients for whom the release quarantine process is still in progress.
      - **Quarantine Release Failed**: The number of recipients for whom the release quarantine process failed.
      - **Quarantine Release Success**: The number of recipients for whom the release quarantine process was successful.
    - **Status Details**:
      - **Email**: The email address of the recipient.
      - **Status**: The status of the release email quarantine process for this recipient. Statuses are **Quarantined**, **Quarantine Release Failed**, **Quarantine Release In Progress**, and **Quarantine Release Success**.
      - **Message**: The reason why the release email quarantine process failed for the recipient.
        - Error Message List

See image.

The following table lists the error messages that can display, the reason why the error messages occurred, and some troubleshooting tips to help you resolve the error messages:

| Error Message | Why It Happens | Troubleshooting Tips |
| --- | --- | --- |
| General error in message processing | Unexpected system error during processing | Review the PowerShell error logs for details; Check the system resources (e.g., memory and disk space); Contact Zscaler Support with full error details |
| No recipients matched for release | The specified recipient is not in the message's recipient list | Verify the recipient's email address spelling; Check the original message headers for actual recipients; Ensure that you are using the correct recipient address (not an alias) |
| Error releasing message to specific recipient | Cannot release the message to the specified recipient | Verify that the recipient mailbox exists and is active; Check that the recipient has a sufficient mailbox quota; Review transport rules that might block delivery |
| Error releasing message to all recipients | Cannot release the message to all original recipients | Verify that the app has permission to release messages; Check whether DLP policies block the release; Ensure that the message type supports release |
| No messages matched the MessageId | MessageId doesn't match any quarantined message | Verify that the exact MessageId format matches quarantine records; Check that you are searching in the correct tenant |
| MessageId is null or whitespace | Missing the required MessageId input parameter | Ensure that the MessageId parameter is being provided; Check the integration/API layer sending the request |
| Error getting quarantined messages | Permission issues or Exchange Online service problems | Verify that the app has Exchange.ManageAsApp permission; Check Exchange Online service health; Review API throttling limits |
| No quarantine messages found | Message doesn't exist in the quarantine system | Verify that the MessageId exists in quarantine; Check whether the message was already released or deleted; Confirm that the MessageId format is correct |
| Something went wrong | Error while processing the action (i.e., generic error) | Try again |
| Exchange Online connection failure | Authentication or connectivity problems with Microsoft 365 services | Verify that the certificate file exists and the password is correct; Check that AppId and TenantId are valid; Ensure that the app has the required permissions (Exchange.ManageAsApp, Directory.Read.All); Confirm network connectivity to Microsoft 365 |

The attributes that appear under the Originating User subsection can vary, depending on how and what information was imported to Workflow Automation through the primary user data source of System for Cross-domain Identity Management (SCIM) or a CSV file. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings), [Managing User Attributes](https://help.zscaler.com/workflow-automation/managing-user-attributes), and [SAML & SCIM Configuration Guide for Microsoft Entra ID](https://help.zscaler.com/zia/saml-scim-configuration-guide-microsoft-entra-id).

In the Violation Details section, you can see:

- Originating User:
  - **Name**: The name of the end user responsible for the incident. When you click the name link, you are redirected to the Incidents page, which displays only the incidents created by the same end user. The user filter is automatically applied in the Filters section. If you applied other filters before clicking the name link, those filters remain applied, as well. If you choose the User Name attribute for obfuscation, multiple asterisks appear for this field, and the name link is not available. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments). See image.
  - **Email**: The email address of the end user responsible for the incident. If you choose the User Email attribute for obfuscation, multiple asterisks appear for this field. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments). You can override the email address that appears for the end user with the email address for that user from your primary user data source—i.e., SCIM or CSV attributes. To override the email address, on the **Account Settings** page, in the **Incident** **Management** section, enable the **Retrieve User Email from Primary Data Source** option. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings).
  - **Client IP**: The client IP address of the end user. This field is only available for incidents with a Source DLP type of **Inline**. If you choose the Client IP attribute for obfuscation, multiple asterisks appear for this field. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Device Name**: The name of the user's device. This field is only available for incidents with a Source DLP type of **Endpoint**. If you choose the Device Name attribute for obfuscation, multiple asterisks appear for this field. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Device OS**: The operating system of the user's device. This field is only available for incidents with a Source DLP type of **Endpoint**.
  - **Device Trust Level**: The trust level of the user's device. This field is only available for incidents with a Source DLP type of **Endpoint**.
  - **Manager Name**: The name of the user's manager. If you choose the Manager Name attribute for obfuscation, multiple asterisks appear for this field. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Manager Email**: The email address of the user's manager. If you choose the Manager Email attribute for obfuscation, multiple asterisks appear for this field. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Employee Number**: The employee number of the end user.
  - **Job Title**: The job title of the end user.
  - **Department**: The department of the end user.
  - **Home Location**: The home location of the end user.
  - **Work Location**: The work location of the end user.
  - **Additional Information**: Displays the user attributes associated with the end user responsible for the incident. To view the additional user attributes:
    1. Click the link provided in this field. The **Additional Information** window appears.
    2. In the **Additional Information** window, you can view data associated with the incident such as end user attributes, manager attributes, and addresses. If you choose user attributes for obfuscation, these obfuscated attributes appear with multiple asterisks in this window. To learn more about obfuscation settings, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments). The additional information is fetched from the primary user data source (i.e, CSV or SCIM) you selected during the incident generation. For example, if you select CSV as the primary user data source during the incident generation, the **Additional Information** window displays the user attributes fetched from the imported CSV file. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings). The additional information displayed for an incident does not change if you alter the primary user data source. The change of the primary user data source settings impacts only new incidents. See image.
- Policy:
  - **Rules**: The DLP rules that the end user violated (e.g., Block-HIPAA). This field is not available for incidents with a Source DLP type of **Email**.
  - **Triggered Engines and Dictionaries**. Expand this heading to view the following fields:
    - **Engines**: The DLP engines that are assigned to the DLP rules that caused the incident.
    - **Dictionaries with Match Count:**The DLP dictionaries that are assigned to the DLP rules that caused the incident. The number of times the end user's traffic violated a specific dictionary is displayed in brackets (e.g., Medical Information[2]).
  - **Non-Triggered Engines and Dictionaries**: Expand this heading to view the following fields:
    - **Engines**: The DLP engines that are not assigned to the DLP rules that caused the incident.
    - **Dictionaries with Match Count:**The DLP dictionaries that are not assigned to the DLP rules that caused the incident. The number of times the end user's traffic violated a specific dictionary is displayed in brackets (e.g., Medical Information[2]).
  - **Other Matched Rules**: Click this field to display the rules that the incident violated, in addition to the primary DLP rules that caused the incident. This field is only available for incidents of Source DLP type **Inline** and **Endpoint**.
- Content: If an evidence file for a DLP incident exceeds 100 MB in size, the Zscaler service replaces the original file with a placeholder evidence file. The placeholder file retains the original name with ".txt" appended (e.g., attachment.pdf.txt and samplefile.xlsx.txt). When the placeholder is open or downloaded, the placeholder provides a message indicating that the file is too large to send.
  - For incidents of Source DLP type **Inline**and **Endpoint**:
    - **File Name**: The name of the file.
    - **File Type**: The type or extension of the file.
    - **File MD5**: The 32-character MD5 hash of the file.
    - **File Size**: The size of the file in bytes.
  - For incidents of Source DLP type **SaaS Security**:
    - **File Name**: The name of the file.
    - **File Type**: The type or extension of the file.
    - **File MD5**: The 32-character MD5 hash of the file.
    - **File Source Location**: The source location of the file.
    - **File Size**: The size of the file in bytes.
    - **Document Type**: The type of document.
    - **File Shared By**: The email address of the user who shared the file.
    - **File Shared At**: The date and time the file was shared.
    - **File Modified By**: The email address of the user who modified the file.
    - **File Link Expiry**: The date and time the file link expires.
  - For incidents of Source DLP type **Email**:
    - **File Name**:The name of the file.
    - **Message ID**: The message ID of the incident.
    - **Email Subject**: The subject of the email.
- Application: This section is only available for incidents of Source DLP type **Inline**, **SaaS Security**, and **Email**.
  - For incidents of Source DLP type **Inline**:
    - **URL**: The URL of the application.
    - **Referrer URL**: The referrer URL of the application.
    - **Name**: The name of the application.
    - **Category**: The category of the application.
  - For incidents of Source DLP type **SaaS Security**:
    - **Name**: The name of the application.
    - **Category**: The category of the application. Categories are **File** and **Email**.
    - **Current Tag Name**: The name of the tag currently assigned to the application.
    - **Is Copilot Accessible**: Indicates whether Copilot can access the application. Values are **yes** or **no**.
  - For incidents of Source DLP type **Email**:
    - **Name**: The name of the application used to create the email.
    - **Tenant**: The tenant ID of the end user.
- User Activity: This section is only available for incidents of Source DLP type **Endpoint**.
  - **Activity Type**: The type of activity that the user performed that caused the incident.
  - **Channel**: The type of channel (e.g., Network Drive Transfer or Remote Drive Transfer) that the user used to cause the incident.
  - **Source**: The source of the incident.
  - **Destination**: The destination of the incident.
  - **Source Type**: The source type of the incident.
  - **Destination Type**: The destination type (e.g., Removable Storage Device) for the incident.
  - **Source Location**:The source location of the incident.
  - **Destination Location**: The destination location of the incident.
  - **ZDP Mode**: The Zscaler Data Protection (ZDP) mode for the incident. ZDP modes can be **Block** or **Exemption**.
  - **Expected Action**: The expected action by the ZDP mode for the incident.
  - **Confirm Action**: The action that the user took when prompted with a confirmation dialog box for the incident creation.
  - **Confirm Justification**: The justification that the user provided during incident creation.
  - **Justification Text**: The optional text that the user provided for the incident. This field is only available if the user adds a justification text.
  - **Additional Information**: Additional information or notes about the incident.

The following images are examples of the Violation Details section for a SaaS Security incident, an Inline incident, an Endpoint incident, and an Email incident. The information that displays in the Violation Details section depends on the type of incident.

See image.

In the Current State Details section, you can see:

- **Status**: The current status of the incident.
- **User**: The person from whom you need a response to move forward with the incident. The person can be the end user, another user, the end user's manager, or another approver. If you choose the User Name attribute or Manager Name attribute for obfuscation, multiple asterisks appear for this field depending on the state. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).

See image.

The Ticket section appears on the page only after you have integrated Workflow Automation with a ticketing integration application (e.g., ServiceNow or Jira Software) and you have executed the Ticket action for the data protection incident on the Incident Details page.

In the Ticket section, you can see:

- **Ticketing Integration**: The tenant ID associated with the ticketing integration application (e.g., ServiceNow or Jira Software).
- **Ticket ID**: The ticket ID associated with the incident in the ticketing integration application. Click the ticket ID link to go to the ticketing integration application (e.g., ServiceNow or Jira Software). After you log in to the ticketing integration application, this particular ticket displays in the application. You can manage the ticket using the ticketing integration application.
- **Project**: The project associated with the ticket in Jira Software. This field only displays for tickets created by the Jira Software application.
- **Ticket Status**: The status of **Closed** displays in this field. This field does not appear on this page until after the ticket is closed in the ticketing integration application (e.g., ServiceNow or Jira Software) and synced with Workflow Automation. Only the **Closed** status displays for these types of tickets. None of the other ticketing integration application statuses are displayed.

The following images are examples of the Ticket section after a ticket is created in ServiceNow and after a ticket is created in Jira Software.

See image.

In the Notessection, you can enter additional notes or information about the progress of the incident, which is logged in the State Changes table.

See image.

The Generate Presigned Link field is not available for incidents of Source DLP type **Email**.

In the Violation Content section, you can see:

- Generate Presigned Link
- View Trigger Data

This field displays the presigned link of the incident and also the link's expiration date and time. It allows you to:

- Before the expiration time, click the presigned link to download the actual data that triggered the incident.
- Click the **Copy** icon to copy the link for reference. See image.

This field enables admins to automatically download the actual data that triggered an incident only if the **Hide Evidence Data - Admin** field is not selected for the DLP integration, and the **Evidence Data Privacy** field is not enabled for the admin on the **Admin Assignment** page in the Zscaler Admin Console. Otherwise, to view the actual data, the admin can copy the evidence link and then access and log in to either Amazon Web Services (AWS) or Azure and paste the link.

This field is available only if the **Hide Trigger Data - Admin** field is not selected for the DLP integration, and the **Trigger Data Privacy** field is not enabled on the Admin Assignmentpage for the admin in the Zscaler Admin Console.

This field displays the data that triggered the incident. The prefix and suffix for the trigger data are displayed along with the trigger data itself. The actual trigger data portion is highlighted. You can view the DLP dictionaries, DLP rules, and DLP engines associated with the incident and the exact data that violated the DLP policies.

See image.

This section is only available for incidents of Source DLP type **Email**.

The Files section displays the files sent to the recipients of the incident and the policies violated by those files. At the top of the section, the total number of files sent appears in parentheses.

In the Files section, you can see:

- **File Name**: The name of the file. If an evidence file for a DLP incident exceeds 100 MB in size, the Zscaler service replaces the original file with a placeholder evidence file. The placeholder file retains the original name with ".txt" appended (e.g., attachment.pdf.txt and samplefile.xlsx.txt). When the placeholder is open or downloaded, the placeholder provides a message indicating that the file is too large to send.
- **File Type**: The type of file (e.g., PDF, XLSX, TXT, or DOCX).
- **File Size**: The size of the file.
- **Document Type**: The type of document (e.g., Invoice).
- **MD5**: The 32-character MD5 hash of the file.
- **Policy**: Displays the DLP policies that the file violated. To view the policies: See image.
  1. Click the **View** icon in this field. The **Policy** window appears, displaying a **Triggered Engines and Dictionaries** heading and a **Non-Triggered Engines and Dictionaries** heading.
  2. In the **Policy** window, expand the **Triggered Engines and Dictionaries** heading to view the following fields:
    - **Engines**: The DLP engines that are assigned to the files that caused the incident.
    - **Dictionaries with Match Count**: The DLP dictionaries assigned to the files that caused the incident. The number of times the end user's traffic violated a specific dictionary is displayed in brackets (e.g., Medical Information[2]).
  3. Expand the **Non-Triggered Engines and Dictionaries** heading to view the following fields:
    - **Engines**: The DLP engines that are not assigned to the files that caused the incident.
    - **Dictionaries with Match Count**: The DLP dictionaries that are not assigned to the files that caused the incident. The number of times the end user's traffic violated a specific dictionary is displayed in brackets (e.g., Medical Information[2]).

See image.

This section is only available for incidents of Source DLP type **Email**and **SaaS Security**.

- Email Source DLP Type
- SaaS Security Source DLP Type

The Recipientssection displays the recipients of the incident and the action taken against each recipient. At the top of the section, the total number of recipients appears in parentheses.

In the Recipients section, you can see:

- **Recipients Email**: The email address of the recipient. If you choose the Recipient Email attribute for obfuscation, multiple asterisks appear for this field. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
- **Rule**: The DLP rule that was violated for each recipient (e.g., Block-HIPAA).
- **Severity**: The severity by recipient and rule that was violated. Severity can be **Critical**, **High**, **Low**, **Medium**, or **Info**.
- **Content Location**: The location of the content on the email. Content locations are **Email Attachments** and **Email Body**.
- **Other Matched Rules**: Any other rules that the incident violated in addition to the primary email DLP rule that caused the incident.
- **Action Taken**: The action taken against each recipient. Actions are **Allow, Block**, **Quarantine**,or**Custom Header Insertion**.

See image.

The Recipients section displays the internal and external recipients for the incident. This section only appears when the application category for the incident is **Email**.

In the Recipients section, you can see:

- **Internal Recipients**: The recipients within your organization that received the incident. At the top of the section, the total number of recipients appears in parentheses.
- **External Recipients**: The recipients outside your organization that received the incident. At the top of the section, the total number of recipients appears in parentheses.

See image.

This section is only available for incidents of Source DLP type **SaaS Security**.

The Collaborators section displays the internal and external collaborators for the incident and the collaborator scope.

In the Collaborators section, you can see:

- **Internal Collaborators**: The collaborators inside your organization for the incident.
- **External Collaborators**: The collaborators outside your organization for the incident.
- **Collaborator Scope**: The scope for the collaborators.
- **Internal Collaborators Groups**: The collaborator groups inside your organization for the incident.
- **External Collaborators Groups**: The collaborator groups outside your organization for the incident.

See image.

This section is only available for incidents of Source DLP type **SaaS Security**with an application category of **Email**.

The Attachments section displays the files sent to the recipients of the incident. At the top of the section, the total number of attachments sent appears in parentheses.

In the Attachments section, you can see:

- **File Name**: The name of the file.
- **File Type**: The type of file (e.g., PDF, XLSX, TXT, or DOCX).
- **File Size**: The size of the file.
- **Document Type**: The type of document (e.g., Invoice).
- **MD5**: The 32-character MD5 hash of the file.
- **Document Sub Type**: The document subtype for the file.

See image.

The User Notifications section provides a table that records and displays a detailed log of the incident's notifications sent to the originating user. This section includes the incident's escalations that have been sent to the user's manager or approver. It provides details such as the timeline of the initial notification, the number of times that the user was notified, the communication channels of the notifications, etc. It also displays whether the user responded, the timing of the response, and the actual response itself.

The table provides the following information:

- **User**: The person to whom the admin sends the details of the incident. If you choose the User Name attribute or Manager Name attribute for obfuscation, multiple asterisks appear for this field depending on the type of notification. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
- **Role**: The role of the user. The roles are **Originating User**, **Manager**, or **Approver**.
- **Channel**: The communication medium used for notification.
- **Status**: The status of the notification.
- **No. of Attempts**: The number of times the notification was sent to the user.
- **Initial Notification Date**: The date and time when the first notification was sent to a user. The User Notification table is sorted by the initial notification date.
- **Response Date**: The date and time when the user responded to the notification.
- **User Response**: The actual response received from the user.

See image.

The State Changes section acts as an audit log for the incident. The State Changes table records and displays all the changes to the incident.

The table displays the following information:

- **State**: The state of the incident. The latest state is displayed at the top.
- **Date**: The date and time when the incident's state changed. The date and time display in the local time zone of the user who caused the incident.
- **Changed By**: The name of the user or service that updated the incident.
- **Comment**: The system-generated comments or comments that the user added about the incident. If you choose the User Name attribute or Manager Name attribute for obfuscation, multiple asterisks appear for those fields in the comments related to a user or manager. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).

See image.

## Managing the Incident

The Incident Details page allows you to perform certain actions to manage the incidents assigned to you. All the actions you perform, except for the Delete action, are logged in the State Changes table. On the Incident Details page, you can click the **Refresh** button at the top of the page to display the latest information for the incident.

See image.

When managing an incident, you are not required to follow the order of the actions as they appear in the **Actions** drop-down menu. You can perform actions based on the requirements of the incident. For example, you can directly escalate the incident to an approver without notifying the user, depending upon the severity of that incident.

At the top-right of the page, the **Actions** drop-down menu contains the following actions:

- Assign DLP Admin
- Assign Priority
- Assign to Me
- Close Incident
- Create Policy Exception
- Delete
- Escalate
- Investigating
- Label
- Notify User
- Release Email Quarantine
- Reopen
- Ticket
- Update Incident Group

Depending on your investigation, you can assign or change the assigned priority of the incident. You can also assign or change the priority of multiple incidents through the [Incidents](https://help.zscaler.com/workflow-automation/managing-incidents) page.

To assign or change the priority of the incident:

1. From the **Actions** drop-down menu, select **Assign Priority**. The **Assign Priority** window appears.
2. In the **Assign Priority** window: See image.
  - **Priority**: From the drop-down menu, select a priority for the incident. Priorities are **Critical**, **High**, **Medium**, or **Low**.
  - **Notes**: Enter additional notes or information about the incident.
3. Click **Assign**.

You can assign an incident to yourself to investigate and resolve it.

To assign an incident to yourself, from the **Actions** drop-down menu, select **Assign to Me**. The incident is assigned to you.

You can assign a new DLP admin to the incident. The newly assigned DLP admin is responsible for managing the incident. You can also assign new DLP admins to multiple incidents through the [Incidents](https://help.zscaler.com/workflow-automation/managing-incidents) page.

To assign a new DLP admin to the incident:

1. From the **Actions** drop-down menu, select **Assign DLP Admin**. The **Assign DLP Admin** window appears.
2. In the **Assign DLP Admin** window: See image.
  - **DLP Admin**: From the drop-down menu, select a new DLP admin for the incident. The drop-down menu displays only the DLP admins who have edit access to the incident group.
  - **Notes**: Enter additional notes or information about the incident.
3. Click **Assign**​​​​​.

Only DLP admins with full access to Workflow Automation can assign incidents to DLP admins with restricted access to Workflow Automation.

You can assign or change the labels for the incident. Labels can assist you with managing incidents. You can also assign labels to multiple incidents through the [Incidents](https://help.zscaler.com/workflow-automation/managing-incidents) page.

To assign a label to the incident:

1. From the **Actions** drop-down menu, select **Label**. The **Label** window appears.
2. In the **Label** window: To add another label to the incident, click the **Add** icon at the end of the row and select another label and label value. See image.
  - **Label**: From the drop-down menu, select a label that you want to assign to the incident.
  - **Value**: From the drop-down menu, select a value for the label. You only need to select a value if the label has associated values.
3. Click **Submit**​​​​​.

To change the label for the incident:

1. From the **Actions** drop-down menu, select **Label**. The **Label** window appears, displaying the assigned labels for the incident.
2. In the **Label** window, change the label or the value associated with an existing label. To delete a label, click the **Delete** icon at the end of a row.
3. Click **Submit**.

You can start investigating the incident assigned to you and view the DLP rules violated, the severity, the priority, etc. You can investigate multiple incidents through the [Incidents](https://help.zscaler.com/workflow-automation/managing-incidents) page.

To investigate an incident:

1. From the **Actions** drop-down menu, select **Investigating**. The **Investigating** window appears.
2. In the **Investigating**window, under **Notes**,enter additional notes or information about the incident. See image.
3. Click **Submit**.

The status of the incident changes to Investigating.

You can create a policy exception only for incidents of Source DLP type Inline and Email.

To create a policy exception for an incident:

1. From the **Actions** drop-down menu, select **Create Policy Exception**. The **Create Policy Exception** window appears.
2. In the **Create Policy Exception** window: See image.
  - **Policy Exception duration:**Enter the number of days, hours, and minutes for the duration of the policy exception.
  - **Notify User**: Select this checkbox if you want to notify the user who triggered the incident when the policy exception is created.
3. Click **Create**. The system creates a policy exception. Workflow Automation creates a subrule for the rule associated with the incident in the Zscaler Admin Console. The subrule applies only to the user associated with the incident. You can view this subrule on the **Data Loss Prevention** page in the Zscaler Admin Console. To learn more, see [About Data Loss Prevention](https://help.zscaler.com/zia/about-data-loss-prevention). If you chose to notify the user, an email notification is sent to the user informing them that the policy exception has been approved. This notification contains the following information: The following image is an example of a policy exception notification email. See image.
  - The Incident ID
  - The active period for the policy exception
  - The date the policy exception was created (i.e., approved)
  - The recommended action for the policy exception
  - A note to the user asking them to ensure that all data transfers and file sharing comply with company policies to protect sensitive information

You can notify the end user or another user about the DLP violation that created the incident through email, a Slack message if you have integrated Workflow Automation with Slack, or a Microsoft Teams message if you have integrated Workflow Automation with Microsoft Teams. You can also notify the users of multiple incidents through the [Incidents](https://help.zscaler.com/workflow-automation/managing-incidents) page.

To notify the user:

1. From the **Actions** drop-down menu, select **Notify User**. The **Notify User** window appears.
2. In the **Notify User** window: See image.
  - **Channel Type**:Select the type of channel to use for the user notification. Channel types are **Email**, **Slack**, and**Teams**. This field is only available in the window if you have integrated Workflow Automation with Slack or Microsoft Teams. If the **Channel Type** field is not available in the window, the user notification is by email.
  - **Language**: From the drop-down menu, select the language for the notification message that the user receives.
  - **User**: Enter the email address of the user. By default, this field displays the user who is associated with the incident, but you can enter a different user. If you have integrated Workflow Automation with Slack and you select the **Slack** channel type, the email address that you enter must be associated with a user for your organization in Slack. If you have integrated Workflow Automation with Microsoft Teams and you select the **Teams** channel type, the email address that you enter must be associated with a user for your organization in Microsoft Teams. In addition, if you choose the User Email attribute for obfuscation, multiple asterisks appear for this field. To learn more about user data obfuscation, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Time Zone**: (Optional) If the notification template you are using for user notifications is configured with one or more time-related merge tags, you can select the time zone that appears for those merge tags in the user notification. The **Time** merge tag represents the time when the incident occurred. From the drop-down menu, select the time zone that appears for those merge tags in the notification. In Workflow Automation, you can configure the time zone for the time-related merge tags for a notification on a few different pages. Because of this, the time zones the system uses for these merge tags when you manually create a user or escalation notification is based on the following priority. If you select the time zone when you manually create a user notification or escalation notification on the Incident Details page, then the system uses that time zone. If you did not select the time zone, then the system uses the time zone configured for the notification template on the Notification Template page. If you did not configure the time zone for the notification template, then the system uses the time zone assigned to the admin on the Admin Assignment page. If you did not assign the time zone to the admin, then the system uses the UTC+00:00 time zone, by default. To learn more, see [Managing Notification Templates](https://help.zscaler.com/workflow-automation/managing-notification-templates) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Note to user**: Enter additional notes or information about the incident.
3. Click **Submit**. A user notification (i.e., email, Slack message, or Microsoft Teams message) is sent to the user requesting justification for the incident.

The status of the incident changes to Validating with User. After the user reviews and responds to the user notification, the status of the incident changes to Received Justification Response.

After the status of the incident changes to Received Justification Response, you can evaluate the justification that the user submitted. If you are not satisfied with the response, you can escalate the incident to the user's manager or another approver. You can escalate multiple incidents to a manager or approver through the [Incidents](https://help.zscaler.com/workflow-automation/managing-incidents) page.

To escalate the incident:

1. From the **Actions** drop-down menu, select **Escalate**. The **Escalate**window appears.
2. In the **Escalate** window: See image.
  - **User Type:**Select the type of user to whom you want to escalate the incident. The **User Type** field, where you can select the type of user (i.e., Approver or Manager), is only available if you map the additional Workflow Automation user attributes when configuring [SCIM](https://help.zscaler.com/zia/saml-scim-configuration-guide-microsoft-entra-id) provisioning in Azure. Otherwise, the **User Type** field is not available, and you can only escalate the incident to an **Approver**. See image.
    - To escalate the incident to the user's manager, select **Manager**. The **Manager** option is visible only when a manager is available to the end user. In this case, the incident is escalated to the manager associated with the incident.
    - To escalate the incident to an approver, select **Approver**. After you select the **Approver** user type, the **Approver** field appears, where you can select the approver of your choice for the incident.
  - **Channel Type**: Select the type of channel to use for the escalation notification. Channel types are **Email**, **Slack**, and**Teams**. This field is only available in the window if you have integrated Workflow Automation with Slack or Microsoft Teams. If the **Channel Type** field is not available in the window, the escalation notification is by email.
  - **Approver**: Select or manually enter an approver to whom you want to escalate the issue. The approver must exist on the [Approvers](https://help.zscaler.com/workflow-automation/managing-approvers) page. This field is not available if you select **Manager** as the user type. If you have integrated Workflow Automation with Slack and you select the **Slack** channel type, the email address that you enter for the approver must be associated with a user for your organization in Slack. If you have integrated Workflow Automation with Microsoft Teams and you select the **Teams** channel type, the email address that you enter must be associated with a user for your organization in Microsoft Teams.
  - **Language**: From the drop-down menu, select the language for the escalation message to the manager or approver.
  - **Time Zone**: (Optional) If the notification template you are using for escalations is configured with one or more time-related merge tags, you can select the time zone that appears for those merge tags in the escalation notification. The **Time** merge tag represents the time when the incident occurred. From the drop-down menu, select the time zone that appears for those merge tags in the notification. In Workflow Automation, you can configure the time zone for the time-related merge tags for a notification on a few different pages. Because of this, the time zones the system uses for these merge tags when you manually create a user or escalation notification is based on the following priority. If you select the time zone when you manually create a user notification or escalation notification on the Incident Details page, then the system uses that time zone. If you did not select the time zone, then the system uses the time zone configured for the notification template on the Notification Template page. If you did not configure the time zone for the notification template, then the system uses the time zone assigned to the admin on the Admin Assignment page. If you did not assign the time zone to the admin, then the system uses the UTC+00:00 time zone, by default. To learn more, see [Managing Notification Templates](https://help.zscaler.com/workflow-automation/managing-notification-templates) and [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - **Note to user**: Enter additional notes or information about the incident.
3. Click **Submit**. An escalation notification (i.e., email, Slack message, or Microsoft Teams message) is sent to the approver or manager requesting the next steps.

The status of the incident changes to Escalated*.* After the manager or approver reviews and responds to the escalation notification, the status of the incident changes to Received Justification Response.

You can also directly close the incident if you are satisfied with the justification response that the user submitted.

You can close the incident when the justification response from the user is acceptable or the approver or manager, after reviewing the escalation email, decides that the incident can be closed. You can close multiple incidents at the same time through the [Incidents](https://help.zscaler.com/zia/about-incidents) page.

To close the incident:

1. From the **Actions** drop-down menu, select **Close Incident**. The **Close Incident** window appears.
2. In the **Close Incident** window: See image.
  - **Notes**: (Optional) Enter additional notes or information about the incident.
  - **Resolution Label**: From the drop-down menu, select the reason for the resolution. If values are associated with the label, select the label value. You can only associate one label value with a resolution label.
  - If the incident is a false positive, select the **False Positive**checkbox.When you mark an incident as a false positive, after the incident is closed, Workflow Automation categorizes the incident to be shown in the False Positive Incidents By Rule widget and the False Positive Incidents By Dictionary widget on the Incident Analytics dashboard. Workflow Automation reports the number of false positives for each incident on the Incident Analytics dashboard by rule and by dictionary. To learn more, see [About the Incident Analytics Dashboard](https://help.zscaler.com/unified/about-incident-analytics-dashboard).
3. Click **Yes**.

The status changes to Resolved.

After an incident is closed (status is Resolved), you can still perform all the other actions against the incident except for the Investigating and Escalate actions, but the incident status remains at Resolved.

You can create a ticket for a ticketing integration application (e.g., ServiceNow or Jira Software) and associate it with the incident. You cannot associate multiple incidents with a ticketing integration application ticket through the [Incidents](https://help.zscaler.com/zia/about-incidents) page. The Ticket action is only available if you have integrated Workflow Automation with a ticketing integration application (e.g., ServiceNow or Jira Software).

To create and associate a ticket with the incident:

1. From the **Actions** drop-down menu, select **Ticket**. The **Ticket**window appears.
2. In the **Ticket** window: See image.
  - **Ticketing Integration**: From the drop-down menu, select the tenant ID associated with the ticketing integration application (e.g., ServiceNow or Jira Software).
  - **Project**: (Jira tickets only) Select the project in the ticketing integration application where you want the ticket to be created. This field is only available after you select a Jira Software tenant ID in the **Ticketing Integration** field.
  - **User**: From the drop-down menu, select the user to assign to the ticket. Only users who appear on the **Integration Users** page for that ticketing integration application are available for selection. To learn more, see [Managing Integration Users](https://help.zscaler.com/workflow-automation/managing-integration-users).
  - **Notes**: Enter additional information about the incident. These notes appear as a comment in the ticket in the ticketing integration application.
3. Click **Submit**. A ticket is created in the ticketing integration application (e.g., ServiceNow or Jira Software), and that ticket information appears in the Ticket section of the **Incident Details** page. Adding a ticket to the incident does not change the status of the incident.

After an incident is closed (Status is Resolved), the **Reopen**action appears in the **Actions** drop-down menu on the page for that incident. You can reopen an incident only from the Incident Details page.

See image.

To reopen an incident, from the **Actions** drop-down menu, select **Reopen**. The incident is reopened, and the status of the incident changes from Resolved to Investigating.

You can delete an incident only from the Incident Details page, and you can delete an incident in any status. The delete action is only available for admins who are assigned to a role that has delete access permission for the Incidents category. To learn more, see [Managing Roles and Permissions.](https://help.zscaler.com/workflow-automation/managing-roles-and-permissions)

To delete an incident:

1. From the **Actions** drop-down menu, select **Delete**. The **Delete Incidents**dialog window appears, displaying a message asking whether you are sure that you want to delete the incident. See image.
2. Click **Yes**. The incident is deleted. This action permanently deletes the incident, and you can't recover a deleted incident later.

On the Incident Details page, you can use the Update Incident Group action to:

- Add additional incident groups to the incident.
- Delete one or more of the incident groups that are currently assigned to the incident.
- Update the incident group that is used for assigning the admin to the incident. When making this update, you can select one of the newly added incident groups, or you can select another one of the incident groups that was previously assigned to the incident.

You can also update the incident groups for multiple incidents through the [Incidents](https://help.zscaler.com/workflow-automation/managing-incidents) page.

To update incident groups:

1. From the **Actions** drop-down menu, select **Update Incident Group**. The **Update Incident Group** window appears, displaying the following information: See image.
  - In the **Available**section, all the incident groups that have been assigned to at least one admin appear in alphabetical order. The number of available incident groups appears in parentheses next to the heading. To learn more, see [Managing Admin Assignments](https://help.zscaler.com/workflow-automation/managing-admin-assignments).
  - In the **Assigned** section, the incident groups that are currently assigned to the incident appear, and an information icon appears next to the incident group that is currently being used to assign the admin. The number of assigned incident groups appears in parentheses next to the heading.
2. (Optional) Add incident groups:
  1. (Optional) At the top of the window, use the search field to locate an incident group in the **Available** section. The search for incident groups spans across the **Available**, **Assigned**, and **Newly added** sections.
  2. In the **Available** section, click the **Add** icon next to each incident group that you want to add to the incident. The incident group is moved from the **Available** section to the **Newly added** section. The number of newly added incident groups appears in parentheses next to the heading. After you add an incident group, the **Update Admin Assignment** checkbox becomes available. In the **Newly added** section, you can also delete a newly added incident group by clicking the **Delete** icon next to an incident group. After you delete an incident group, that incident group is displayed in the **Available** section. You can also click **Reset** to reset the window to the original incident group settings. See image.
3. (Optional) Delete assigned incident groups. You can delete assigned incident groups even if you have not added new incident groups. In the **Assigned** section, click the **Delete** icon next to each incident group you want to delete. After you delete an incident group, that incident group is displayed in the **Available** section. If you delete an incident group that is being used for admin assignment, a warning message appears stating that the current assigned admin will be removed and instructing you to update the admin assignment to select a new incident group for admin mapping. If you have already added new incidents groups, the **Update Admin Assignment** checkbox is already available. If you have not added new incidents groups, the **Update Admin Assignment** checkbox becomes available at this time. You must have at least one incident group assigned to the incident. If you delete all the assigned and newly added incident groups, a message appears stating that at least one group must be assigned. You can also click **Reset** to reset the window to the original incident group settings. See image.
4. (Optional) Update the admin assignment: You can update the admin assignment for the incident to use one of the newly added incident groups or to use another one of the previously existing assigned incident groups that is not used for admin assignment. If you delete the existing assigned incident group that is used for admin assignment, to ensure an admin gets assigned to the incident, you must select another incident group to be used for admin assignment.
  1. Select the**Update Admin Assignment** checkbox. The **Incident Group** field appears.
  2. From the **Incident Group** drop-down menu, select the incident group to be used for assigning the admin to the incident. This menu lists all the incident groups that are displayed in the **Assigned** and **Newly added** sections of the window. See image.
5. (Optional) In the **Notes** field, enter additional notes for updating the incident groups.
6. Click **Update**. The **Incident Details** page appears. To see the updates, refresh the page. After refreshing the page, you can see the following updates: See image.
  - The **Incident Groups**field displays the updated incident groups.
  - The **Priority** field might change based on the final list of incident groups that you assigned to the incident. The priority of an incident is derived from the incident groups assigned to the incident. If the incident groups have different priorities, the highest priority is used.
  - If you updated the admin assignment to use a different incident group, the **DLP Admin** field displays the name of the admin derived from that incident group.
  - The **State Changes** section displays all the state changes that were made to the incident when you updated the incident groups for the incident.

On the Incident Details page, for an incident with a Source DLP type of Email in which the email is quarantined, you can use the Release Email Quarantine action to release the email to one or more of its recipients. The Release Email Quarantine action is only available for incidents with a Source DLP type of Email with a quarantine action.

You also have the option to allow the end user or approver/manager of a quarantined email incident to release the email to one or more of its recipients from an end user notification or an escalation notification. To enable this feature for an end user notification, select **Enable Email Quarantine Release for End Users** in the **Incident Management** section on the **Account Settings** page. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings#incident-management-quarantine-settings), [Responding to an End User Notification](https://help.zscaler.com/workflow-automation/responding-end-user-notification), and [Responding to an Escalation Notification](https://help.zscaler.com/workflow-automation/responding-escalation-notification).

To release the email to its recipients:

1. From the **Actions** drop-down menu, select **Release Email Quarantine**. The **Release Email** window appears, displaying the following information at the top of the window: See image.
  - **Sender**: The email address of the user who sent the email that was quarantined.
  - **Recipients**: The number of recipients that are quarantined for the email. Initially, this field lists all the recipients that are quarantined for the email. But as you release the email to its recipients, this field adjusts to reflect the number of recipients that are still quarantined for the email.
  - **Quarantine Reason**: The reason why the email was quarantined.
2. Release the quarantine for the recipients using one of the following methods:
  - Release the Quarantine for All Recipients
  - Release the Quarantine for One or More Recipients

1. In the **Release email to recipients inboxes** section, select **Release to all recipients**.
2. (Optional) If the quarantined email incident is a false positive, select the **False Positive** checkbox. On the **Account Settings** page, in the **Incident Management**section, you can specify the default setting for this **False Positive** checkbox in this window. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings#incident-management-quarantine-settings).
3. (Optional) In the **Justification** field, enter a reason in detail for the release of the quarantine from the recipients. The reason can contain a maximum of 250 characters. See image.
4. Click **Release.** The quarantine is released for the recipients and the email is sent to all the email recipients. If you selected the **False Positive** checkbox, a **False Positive** label is added to the incident.

1. In the **Release email to recipients inboxes** section, select **Release to one or more recipients**. On the **Account Settings** page, if you have chosen to obfuscate the **Recipient Email** attribute in the **Privacy and Security** section, this option is not available. An **Email** table appears, listing the email addresses of the recipients sent the quarantined email. See image.
2. (Optional) Use the **Search** field to search for the email address of a specific recipient you want to release from quarantine. You can search for an email address by entering the full email address or a portion of it.
3. In the **Email** table, select the checkbox next to one or more of the recipient email addresses you want to release the email to. To select all the recipient email addresses, select the checkbox in the header of the table.
4. (Optional) If the quarantined email incident is a false positive, select the **False Positive** checkbox. On the **Account Settings** page, you can specify the default setting for this **False Positive** checkbox on this window. To learn more, see [Managing Account Settings](https://help.zscaler.com/workflow-automation/managing-account-settings#incident-management-quarantine-settings).
5. (Optional) In the **Justification** field, enter a reason in detail for the release of the quarantine for the recipients. The reason can contain a maximum of 250 characters. See image.
6. Click **Release.** The quarantine is released for the selected recipients and the email is sent to them. If you selected the **False Positive** checkbox, a **False Positive** label is added to the incident.

[Image: Viewing the duplicate incident message on the Incident Details page]

[Image: Viewing the duplicate incidents for an incident in the Duplicate Incidents dialog window]

[Image: Viewing the Overview section on the Incident Details page]

[Image: Viewing the Quarantined Email Status page where the release process failed for a couple of the recipients.]

[Image: Viewing the originating user prefiltered name link on the Incident Details page]

[Image: Viewing the Violation Details section for a SaaS Security incident on the Incident Details page]

[Image: Viewing the Violation Details section for an Inline incident on the Incident Details page]

[Image: Viewing the Violation Details section for an Endpoint incident on the Incident Details page]

[Image: Viewing the Violation Details section for an Email incident on the Incident Details page]

[Image: Viewing the Generate Presigned Link section for an incident on the Incident Details page]

[Image: Viewing the Trigger Data section for an incident on the Incident Details page]

[Image: Viewing the Current State Details section on the Incident Details page]

[Image: Viewing the Ticket section on the Incident Details page after the ServiceNow ticket is created]

[Image: Viewing the Ticket section on the Incident Details page after a Jira Software ticket is created]

[Image: Viewing the Notes section on the Incident Details page]

[Image: Viewing the policies for a file in the Policy window]

[Image: Viewing the Files section for an Email incident]

[Image: Viewing the Recipients section for an Email incident on the Incident Details page]

[Image: Viewing the Recipients section for a SaaS Security incident on the Incident Details page]

[Image: Viewing the Collaborators section for a SaaS Security incident on the Incident Details page]

[Image: Viewing the Attachments section on the Incident Details page]

[Image: Viewing the User Notifications section on the Incident Details page]

[Image: Viewing the State Changes section on the Incident Details page]

[Image: Viewing the Incident Details page showing the Actions menu. The menu shows the following options: Assign DLP Admin, Assign Priority, Assign to Me, Close Incident, Create Policy Exception, Delete, Escalate, Investigating, Label, Notify User, Release Email Quarantine, Ticket, and Update Incident Group.]

[Image: Viewing the Investigating window on the Incident Details page]

[Image: Viewing the Assign Priority window on the Incident Details page]

[Image: Viewing the Assign DLP Admin window on the Incident Details page]

[Image: Viewing the Label window on the Incident Details page]

[Image: Viewing the Notify User window on the Incident Details page]

[Image: Viewing the Escalate window on the Incident Details page when escalating an incident to a manager]

[Image: Viewing the Escalate window on the Incident Details page when escalating an incident to an approver]

[Image: Viewing the Close Incident window on the Incident Details page]

[Image: Viewing the Ticket window on the Incident Details page]

[Image: Viewing the Reopen action on the Incident Details page]

[Image: Viewing the Delete Incidents dialog window on the Incident Details page]

[Image: Escalate window when no user attributes are mapped in SCIM]

[Image: Viewing the Additional Information window on the Incident Details page]

[Image: Viewing the Update Incident Group window before incident groups are updated. The window contains an Available incident group section, an Assigned incident group section, an update admin assignment section, and a Notes field.]

[Image: Video showing how to add and delete new incident groups on the Update Incident Group window]

[Image: Video showing how to delete assigned incident groups on the Update Incident Group window]

[Image: Video showing how to update the incident group for admin assignment on the Update Incident Group window]

[Image: Viewing the Incident Details page after doing incident group updates. The fields that are affected by an incident group update are highlighted in the window. Those fields are Incident Groups, DLP Admin, and Priority. In addition, the State Changes section is highlighted, showing the entries for the incident group updates.]

[Image: Viewing the Release Email window on the Incident Details page with no fields selected or entered]

[Image: Viewing the Release Email window on the Incident Details page. The Release to all recipients option is selected, the False Positive checkbox is selected, and a justification reason is entered.]

[Image: Viewing the Release Email window on the Incident Details page. The Release to one or more recipients option is the only field selected.]

[Image: Viewing the Release Email window on the Incident Details page. The Release one or more recipients option is selected, two email addresses are selected, the False Positive checkbox is selected, and a justification reason is entered.]

[Image: Viewing the Create Policy Exception window on the Incident Details page.]

[Image: Viewing a user email notification for a policy exception.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/workflow-automation/what-workflow-automation","lastmod":"2026-04-21T06:45Z","nid":"1417816"} -->
## What Is Workflow Automation?

- Source: https://help.zscaler.com/workflow-automation/what-workflow-automation
- Product: Workflow Automation
- Path: Workflow Automation Help > What Is Workflow Automation?
- Last modified: 2026-04-21T06:45Z
- Summary: Information on Workflow Automation.

Workflow Automation is an application that enables governance admins to automate the management and resolution of Data Loss Prevention (DLP) incidents that occur in their organization. Workflow Automation integrates with Internet & SaaS (ZIA) to capture the Data Protection incidents generated from the DLP policies defined in Internet & SaaS.

Workflow Automation provides a closed-loop Incidents page where admins can review and remediate the Data Protection incidents that have occurred in their organization all in one location. The Incidents page lists all the Data Protection incidents along with the details for each of those incidents. The incident details include the metadata for the incident and the data that triggered the incident.

Workflow Automation provides the capability to group individual incidents into incident groups and assign priorities to those incident groups. These incident groups can then be assigned to different admins.

To assist with reviewing and remediating incidents on the Incidents page, Workflow Automation offers workflow management features that admins can use to:

- Notify the end user involved in an incident and request justification for the incident.
- Escalate the incident to an end user's manager or other approver and request justification.

Workflow Automation also enables you to configure workflows that automatically perform different actions to manage and remediate DLP incidents.

By using all the DLP features that Workflow Automation provides, admins can dramatically reduce resolution time and obtain insights into where their security is at risk in their organization. These insights can assist them with security challenges such as deployment, compliance, and upgrades.

To learn more about configuring Workflow Automation for DLP Incidents, see [Step-by-Step Configuration Guide for Workflow Automation for Data Protection](https://help.zscaler.com/workflow-automation/step-step-configuration-guide-workflow-automation).

## Key Benefits

The key benefits of using Workflow Automation are:

- Protection of your security posture by proactively monitoring every DLP incident that occurs in your organization.
- Reduction in resolution time by centralized management of all incidents through automated workflows in one location.
- Establishment of clear lines of responsibility for managing various incidents by priority.
- Assignment of the tickets to the dedicated teams to review in-depth details of incidents, and take necessary action.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/access-policy-deployment-and-operations-guide","lastmod":"2025-04-18T10:07Z","nid":"1417751"} -->
## Access Policy Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/access-policy-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZPA Deployments & Operations > Access Policy Deployment and Operations Guide
- Last modified: 2025-04-18T10:07Z
- Summary: Describes the benefits of and the steps necessary to enable Zscaler Private Access (ZPA) Access Policies.

This guide describes the benefits of using Access Policies and the steps necessary for configuring Zscaler Private Access (ZPA) to add Access Policies to your security posture.

ZPA Access Policy rules enable you to implement role-based access control. To configure an Access Policy rule, you must first define the users and then define which applications or segment groups they can access. For example, you would specify the users first (i.e., SalesStaff), then specify which application segments or segment groups they can access (i.e., Sales App and Intranet Group). For a complete list of ranges and limitations for Access Policy rules, see [Ranges & Limitations](https://help.zscaler.com/zpa/ranges-limitations#Policies).

To learn more, see [Access Policy Guide](https://help.zscaler.com/zpa/about-access-policy).

## Value of Deploying Access Policy

Using Access Policy provides the following benefits:

- Secures access to internally hosted applications.
- Ensures only authorized and compliant users have access to only the applications they need, using a least-privilege Zero Trust model.
- Secures access to ZIA-hosted applications via [Source IP Anchoring](https://help.zscaler.com/zia/configuring-source-ip-anchoring).

## Deployment Phase

The deployment phase initially sets up and integrates ZPA solutions into an existing network infrastructure. During the deployment phase, you configure ZPA Access Policies to meet the needs of your infrastructure. The following sections discuss steps to deploy ZPA Access Policies.

### Prerequisites

For Access Policy deployment, verify and complete the following prerequisites:

- One of the following Zscaler subscriptions is required:
  - ZPA core software
  - Source IP Anchoring (SIPA) module (optional)
- Defined and deployed [App Connector](https://help.zscaler.com/zpa/about-connectors)s.
- Defined applications.
- Defined access criteria.
- (Optional) [Security Assertion Markup Language (SAML)](https://help.zscaler.com/zpa/about-saml-attributes) or [System for Cross-domain Identity Management (SCIM)](https://help.zscaler.com/zpa/about-scim) attributes.

### Deployment Steps

The following steps explain how to configure a ZPA Access Policy:

1. Define whether all App Connectors can communicate with the applications, or only specific App Connector groups, or Server groups can communicate with the applications.
2. Define whether the policy is an **Allow**or **Block**policy.

The default policy behavior is to block access.

1. [Configure the desired Access Policy](https://help.zscaler.com/zpa/configuring-access-policies) based on the required criteria:
  - [Applications](https://help.zscaler.com/zpa/configuring-access-policies#apps)
  - [Client Connector Posture Profiles](https://help.zscaler.com/zpa/configuring-access-policies#postureprofile)
  - [Client Connector Trusted Networks](https://help.zscaler.com/zpa/configuring-access-policies#networks)
  - [Client Types](https://help.zscaler.com/zpa/configuring-access-policies#client)
  - [Cloud Connector Groups](https://help.zscaler.com/zpa/configuring-access-policies#CloudConnectorGroups)
  - [Machine Groups](https://help.zscaler.com/zpa/configuring-access-policies#machinegrps)
  - [SAML and SCIM Attributes](https://help.zscaler.com/zpa/configuring-access-policies#samlscimattribute)

Zscaler always displays the Boolean logic between criteria. For example, when a user requests access to an application, the policy rule is evaluated to check if an application segment *or* its segment group are present *and*whether any of the SAML attributes apply to the user requesting before it grants or denies access. You can always view the **Rule Action** and **Criteria**and the applied Boolean logic on the [Access Policy page](https://help.zscaler.com/zpa/about-accesspolicy).

1. To learn more, see [Configuring Access Policies](https://help.zscaler.com/zpa/configuring-access-policies).

### Considerations

Review the following considerations:

- If applying any Posture Checking rules for users connected to Zscaler Client Connector, remember to configure the [Posture Checking Profiles](https://help.zscaler.com/zscaler-client-connector/about-device-posture-profiles) in the ZPA Admin Portal and ensure the endpoints can pass the check (i.e., a certificate check requires a certificate is deployed to the endpoint).
- ZPA Access Policies process on a specific and top-down methodology. For example, if rule 1 blocks *.web.com but rule 2 allows specific.web.com, rule 2 allows access to specific.web.com for a user. Any other subdomain of web.com is blocked. When both FQDNs are equal, ZPA performs a top-down ranking approach. So, if rule 1 is *.specific.web.com and rule 2 is specific.web.com, then rule 1 would apply, because it’s processed first.
- If you use SCIM as the provisioning method, ensure that the initial SCIM cycle is complete before using any SCIM attributes in Access Policies. After you enable SCIM, Zscaler checks if a user is present in the SCIM database. Based on this information, Zscaler decides if the user is allowed or blocked access to ZPA. Ensure the SCIM user sync is complete before enabling SCIM policies for these users. If not, the ZPA service evaluates policies on the users it does not recognize.
- After SCIM sync is enabled, Zscaler recommends waiting for a minimum of 48 hours (sometimes up to a week) before enabling SCIM policies. It can take several days for the IdP to sync all user information to ZPA completely. Zscaler recommends enabling SCIM sync in advance before enabling SCIM attributes for policy.

## Operations Phase

This section describes standard practices used to operate Zscaler solutions when integrated with your environment. You can monitor and tune ZPA Access Policies during operations to meet your infrastructure needs.

### Prerequisites

For Access Policy operation, complete the following prerequisites:

- Zscaler recommends regularly reviewing ZPA Access Policies (at least annually) to ensure they still meet organizational objectives. It is also best practice to ensure Access Policies are still in use, relevant, and applied to the correct users.
- Document the process to create a new Access Policy and whether all App Connectors or specific App Connector groups or Server groups have access to the applications.

### Common Troubleshooting Items

The following list describes common issues related to Access Policy operation:

- Application is not accessible: Check whether an Access Policy exists and whether the default behavior is an implicit block.
- Desktop notification ZPA Blocked Access: A configured Access Policy blocks access, and the user cannot meet the Access Policy criteria. Check the Access Policies for validation.
- Application is not accessible due to failed Posture Check:
  - Check the ZPA diagnostic logs to determine the reason for the failed posture check.
  - Ensure the endpoint has the configuration to pass the posture check (i.e., the endpoint has the correct certificate for Certificate Posture Check type).
- Application is not accessible due to No App Connector Available:
  - Check that the Access Policies define the correct App Connectors that can access the application.
  - Check general network connectivity.
  - Check that the App Connector is connected to the ZPA Public Service Edge or Private Service Edges.

## Deployment Checklist

Zscaler recommends downloading the [Access Policy Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zpa-deployments-operations/access-policy-deployment-and-operations-guide/Access-Policy-Deployment-Operations-Checklist.pdf) to help plan and implement ZPA Access Policy: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zpa-deployments-operations/access-policy-deployment-and-operations-guide/Access-Policy-Deployment-Operations-Checklist.pdf)

## Additional Information

For more Access Policy information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and [Zscaler Zenith Community](https://community.zscaler.com/).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/advanced-sandbox-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1417931"} -->
## Advanced Sandbox Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/advanced-sandbox-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZIA Deployments & Operations > Advanced Sandbox Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits of and the steps necessary to enable Zscaler Internet Access (ZIA) Advanced Sandbox.

This guide describes the benefits of using Advanced Sandbox and the steps necessary for configuring Zscaler Internet Access (ZIA) to add Advanced Sandbox to your security posture.

Advanced Sandbox provides an additional layer of security by analyzing suspected malware files in a virtual environment to detect malicious behavior. It distributes a hash of malicious files to all ZIA Public Service Edges, effectively maintaining a real-time denylist. The analysis based on the denylist prevents users (anywhere) from downloading malicious files.

To learn more, see [About Sandbox](https://help.zscaler.com/zia/about-sandbox).

## Value of Deploying Advanced Sandbox

Using Advanced Sandbox provides more capabilities and more security options than Basic Sandbox:

- Scans more file types than .exe and .dll, including .jar, archives, scripts in MS files, MS macros, .pdf, .swf, and .apk.
- Scans files sized higher than 2 MB.
- Quarantines files via configuration rules.

## Deployment Phase

The deployment phase initially sets up and integrates ZIA solutions into an existing network infrastructure. During the deployment phase, you configure ZIA Advanced Sandbox to meet the needs of your infrastructure. The following sections discuss the steps to deploy ZIA Advanced Sandbox.

### Prerequisites

One of the following Zscaler subscriptions is required:

- ZIA Transformation Edition and later.
- Advanced Sandbox Add-On.

### Deployment Steps

The following steps explain how to deploy ZIA Advanced Sandbox:

1. Make sure to [enable the inspection on inbound and outbound traffic](https://help.zscaler.com/zia/configuring-default-sandbox-rule#enable-inbound-outbound-traffic-inspection).
2. (Optional) [Review and implement the recommended policies for Sandbox](https://help.zscaler.com/zia/what-recommended-sandbox-policy).
3. (Optional) [Edit the default Sandbox rule](https://help.zscaler.com/zia/configuring-default-sandbox-rule#edit-default-sandbox-rule) if necessary.
4. [Add policies to Sandbox](https://help.zscaler.com/zia/configuring-sandbox-policy#add-sandbox-rule) that match your organization’s needs.
5. (Optional) [Configuring the Patient 0 Alert](https://help.zscaler.com/zia/configuring-patient-0-alert) and other [Alert Definitions](https://help.zscaler.com/zia/about-alerts).
6. (Optional) [Configure Sandbox end user](https://help.zscaler.com/zia/about-sandbox-end-user-notifications) notifications.

### Considerations

Review the following considerations:

- Rules in the Rule Order List are applied first to last. The last rule checked is the default rule.
- Any rule that applies to unauthenticated traffic must apply to all groups and departments.
- The Zscaler service does an initial static analysis for unknown PDF or Microsoft Office files to check for active content:
  - If the Zscaler service detects active content, it sends the files to the Sandbox for behavioral analysis.
  - If the Zscaler service does not detect active content, the files are classified as benign and allowed to download.
- If you choose Allow as the action for subsequent downloads, and a user attempts to download a malicious Sandbox classified file, the service allows the download. Zscaler recommends disallowing subsequent downloads of flagged malicious content (unless it is for testing).

## Operations Phase

This section describes common practices used to operate Zscaler solutions when integrated with your environment. You can monitor and tune ZIA Advanced Sandbox during operations to meet your infrastructure needs.

### Prerequisites

For Advanced Sandbox operation, complete the following prerequisites:

- The operations team should familiarize themselves with the following reports and tools. Support can view a variety of Sandbox data and reports under Dashboard and Analytics:
  - [Security Dashboard](https://help.zscaler.com/zia/viewing-sandbox-reports-data#security-dashboard)
  - [Web Insights](https://help.zscaler.com/zia/viewing-sandbox-reports-data#web-insights)
  - [Sandbox Detail Report](https://help.zscaler.com/zia/viewing-sandbox-reports-data#about-sandbox-detail-report)
- Examine Sandbox activity in the [Sandbox Activity Report](https://help.zscaler.com/zia/about-sandbox-activity-report):
  - Review the Sandbox [Files Found Malicious](https://help.zscaler.com/zia/about-sandbox-files-found-malicious-report) report for information on what files are quarantined.
  - Check the Endpoint Hit Reports for endpoints exposed to malicious files (if integrated with [CrowdStrike](https://help.zscaler.com/zia/configuring-crowdstrike-integration), [VMware Carbon Black](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-vmware-carbon-black-deployment-guide), or [Microsoft Defender for Endpoint](https://help.zscaler.com/zia/integrating-microsoft-defender-endpoint)).
  - Use the [Sandbox Scanning Portal](http://filecheck.zscaler.com/) to submit suspicious files for behavioral analysis.

## Deployment Checklist

Zscaler recommends downloading the [Advanced Sandbox Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/advanced-cloud-sandbox-deployment-and-operations-guide/Advanced-Sandbox-Deployment-Operations-Checklist.pdf) to help plan and implement ZIA Advanced Sandbox: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/advanced-cloud-sandbox-deployment-and-operations-guide/Advanced-Sandbox-Deployment-Operations-Checklist.pdf)

## Additional Information

For more SaaS Security information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com/).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/app-connector-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1417951"} -->
## App Connector Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/app-connector-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZPA Deployments & Operations > App Connector Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits of and the steps necessary to enable App Connectors in Zscaler Private Access (ZPA).

This guide describes the benefits of using App Connectors and the steps necessary for configuring Zscaler Private Access (ZPA) to add App Connectors to your security posture.

App Connectors provide a secure authenticated interface between a customer’s servers and the Zscaler cloud. App Connectors can be deployed in several forms. Zscaler distributes a standard virtual machine (VM) image for deployment in enterprise data centers, local private cloud environments such as VMware, or public cloud environments such as Amazon Web Services (AWS) EC2. Additionally, Zscaler provides packages that can be installed on supported Linux distributions.

To learn more, see [About App Connectors](https://help.zscaler.com/zpa/about-connectors).

## Value of Deploying App Connectors

Using App Connectors provides the following benefits:

- Provides a cleaner and faster infrastructure by selecting the closest App Connector given the location of the user and App Connector-to-application latency.
- Facilitates an always active, redundant configuration.

## Deployment Phase

The deployment phase initially sets up and integrates ZPA solutions into an existing network infrastructure. During the deployment phase, you configure App Connectors to meet the needs of your infrastructure. The following sections discuss steps to deploy App Connectors.

### Prerequisites

For App Connector deployment, verify and complete the following prerequisites:

- One of the following Zscaler subscriptions is required:
  - ZPA Professional Edition
  - ZPA Business Edition
  - ZPA Transformation Edition
- [App Connector Deployment Prerequisites](https://help.zscaler.com/zpa/connector-deployment-prerequisites)

### Deployment Steps

For information on how to configure an App Connector on the respective platforms, see [App Connector Deployment Guides for Supported Platforms](https://help.zscaler.com/zpa/app-connector-management/app-connector-deployment-guides-supported-platforms).

### Considerations

Review the following considerations:

- [List of platforms supported by ZPA](https://help.zscaler.com/zpa/about-connectors#platforms).
- By design, certificate verification is not configurable to maintain the service's integrity. Ensure that *.prod.zpath.net is in your SSL bypass list for traffic originating from the App Connector, which is necessary for App Connectors to resolve and reach ZPA Public Service Edges or ZPA Private Service Edges.
- For ZPA integration with Zscaler Digital Experience (ZDX), App Connector firewall requirements must align with the respective ZDX configuration and require the configured report protocols to egress the App Connector (i.e., UDP, ICMP, or UDP). The traffic must egress the App Connector towards the configured application port and to the Zscaler Public Service Edge on port 443.
- The customer is responsible for maintaining the host on which the App Connector is running. Zscaler does not maintain the underlying operating system, only the App Connector application. To learn more about updating the App Connector system software, see [Update App Connector System Software](https://help.zscaler.com/zpa/managing-deployed-connectors#Updating).

## Operations Phase

This section describes standard practices used to operate Zscaler solutions when integrated with your environment. You can monitor and tune App Connectors during operations to meet your infrastructure needs.

### Prerequisites

For App Connector operation, complete the following prerequisites:

- Check the [App Connector health status](https://help.zscaler.com/zpa/managing-deployed-connectors#Status) by accessing the App Connectors page and using the Health dashboard within the ZPA Admin Portal.
- Verify the [App Connector Sizing Specifications](https://help.zscaler.com/zpa/managing-deployed-connectors#VerifySizing).

### Common Troubleshooting Items

The following list describes common issues related to App Connector operations:

- App Connector is not connecting to the Zscaler cloud: If the App Connector was previously working and now shows an error about not being connected to the cloud, see [Troubleshooting App Connectors](https://help.zscaler.com/unified/troubleshooting-app-connectors).
  1. In the command prompt, enter the following command to stop the zpa-connector service: [admin@zpa-connector ~]$ sudo systemctl stop zpa-connector
  2. Enter the following command to delete the App Connector: [admin@zpa-connector ~]$ rm -f /opt/zscaler/var/*
  3. Enter the following command to restart services: [admin@zpa-connector ~]$ sudo systemctl restart zpa-connector
- Collect the App Connector logs to see App Connector log information. Collect the App Connector logs by running the `journalctl` sudo command as an admin.
  - Enter the following command to collect the logs: [admin@zpa-connector ~]$ sudo journalctl -u zpa-connector -f
  - Enter the following command to collect the logs with a maximum of 1000 lines (you don't need to use the root level for this command): [root@ip-10-0-0-228 admin]# journalctl -n1000 | grep zpa-connector
  - Enter the following command to collect the logs and store in a file called journalctl.log: sudo journalctl -u zpa-connector > journalctl.log

For more troubleshooting information, see [Troubleshooting App Connectors](https://help.zscaler.com/zpa/troubleshooting-app-connectors).

## Deployment Checklist

Zscaler recommends downloading the [App Connector Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zpa-deployments-operations/app-connector-deployment-and-operations-guide/App-Connector-Deployment-Operations-Checklist.pdf) to help plan and implement App Connector: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zpa-deployments-operations/app-connector-deployment-and-operations-guide/App-Connector-Deployment-Operations-Checklist.pdf)

## Additional Information

For more App Connector information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](https://community.zscaler.com/).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/application-discovery-deployment-and-operations-guide","lastmod":"2026-02-25T09:16Z","nid":"1420091"} -->
## Application Discovery Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/application-discovery-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZPA Deployments & Operations > Application Discovery Deployment and Operations Guide
- Last modified: 2026-02-25T09:16Z
- Summary: Describes the benefits of and the steps necessary to enable Application Discovery in Zscaler Private Access (ZPA).

This guide describes the benefits of using Application Discovery and the steps necessary for configuring Zscaler Private Access (ZPA) to add Application Discovery to your security posture.

Two ways to access applications are through an explicit application definition or application discovery. When users request applications, Application Discovery lets ZPA discover them using specific domain names and IP subnets.

Configuring an application segment to allow for application discovery is useful when you want to learn about the applications used in your organization and the users accessing them. Application discovery works efficiently when the TCP and UDP port range specified for the domain or IP subnet is wide, and when the application policy allows access to a broad set of users. After discovering an application, you can define granular policies for your discovered applications to control access.

To learn more, see [Understanding Application Discovery](https://help.zscaler.com/zpa/understanding-application-discovery).

## Value of Deploying Application Discovery

Enabling Application Discovery for ZPA provides the following benefits:

- Helps to scope out the network and frequently accessed applications to implement least-privilege access policies early in the ZPA deployment stage.
- Ascertains if there are hidden applications so that you can restrict access if necessary.

## Deployment Phase

The deployment phase initially sets up and integrates ZPA solutions into an existing network infrastructure. During the deployment phase, you configure Application Discovery to meet the needs of your infrastructure. The following sections discuss steps to deploy Application Discovery.

### Prerequisites

For Application Discovery deployment, ZPA discovers an application only when a user requests access to it.

### Deployment Steps

The following steps explain how to deploy Application Discovery:

1. [Create an application segment](https://help.zscaler.com/zpa/configuring-application-segments) with a wider domain or subnet:
  - Fully Qualified Domain Names (FQDNs) or Domain Names: Enter FQDNs and domain names in wildcard format (e.g., `*.``<FQDN or Domain Name>` or `.``<FQDN or Domain Name>`).
  - IP Subnets: Enter IP subnets using the Classless Inter-Domain Routing (CIDR) notation (e.g., 10.0.0.0/24).
2. Define wider TCP/UDP port ranges for applications excluding TCP and UDP 53 (DNS).
3. (Optional) [Add DNS search domains](https://help.zscaler.com/zpa/about-applications/dnsDomains).
4. [Configure access policies](https://help.zscaler.com/zpa/configuring-access-policies) that reference applications as a set rather than configuring policies for each application.
5. Define the discovered applications from the [Discovered Application widget in the Applications dashboard](https://help.zscaler.com/zpa/defining-dynamically-discovered-application).
6. Use the [bypass settings](https://help.zscaler.com/zpa/configuring-bypass-settings) to bypass selected applications from ZPA.
7. After applications are discovered, define granular application segments and access policies to restrict access on a least-privilege access principle.

### Considerations

Review the following considerations:

- Applications defined with only a wildcard, i.e., an asterisk (*), are not available for application discovery.
- On Access health reporting is available for only discovered applications.
- App Connector reports the application's health as soon as a user accesses it and for up to 30 minutes after the user has completed using it.
- ZPA does not apply the ports specified in the app segments that contain IP subnets or wildcards towards app segments that contain more specific IPs or FQDNs. You must specify the ports in the app segment that contain the IPs or FQDNs.
- When defining a new application segment, Zscaler recommends taking note of the following interaction between a wildcard domain and a specific host domain, where wildcard no longer means wildcard:
  - You have an app segment defined by a wildcard (*.exapp.company.com).
  - You add the app segment to an access policy.
  - You create a new app segment (file.exapp.company.com).

The access policy does not cover the app segment in the third bullet. By defining the app segment (file.exapp.company.com) separately, you must add a new access policy because the application matches the specific app segment. ZPA always matches an application to the most specific app segment, even if that application could potentially match a wildcard app segment. For policy, ZPA evaluates the policy with the most specific app segment.

- For applications that users access using only the hostname; e.g., distributed file system (DFS), ensure that you configure [DNS search domains](https://help.zscaler.com/zpa/about-applications/dnsDomains) so that ZPA automatically adds the search domain to the hostname. This ensures that ZPA users accessing applications as non-FQDNs (i.e., host short names) have the domain suffixes appended, which causes an FQDN to be sent through for application discovery.
- If your identity provider (IdP) is defined as an application within an application segment, the [Authentication Timeout](https://help.zscaler.com/zpa/about-timeout-policy) for the IdP application must be set to Never. If an IdP domain overlaps with a domain configured for application discovery, you must bypass the IdP domain in ZPA to avoid user reauthentication failure.
- After 14 days, discovered application data is no longer listed in the [User Activity Diagnostics](https://help.zscaler.com/zpa/about-diagnostics/txnUsersDiagnostics).

## Operations Phase

This section describes standard practices used to operate Zscaler solutions when integrated with your environment. You can monitor and tune Application Discovery in ZPA during operations to meet your infrastructure needs.

### Prerequisites

For Application Discovery operation, complete the following prerequisites:

- Prepare an up-to-date list of domains, IP subnets, and ports currently enabled for application discovery.
- Define a deadline after which application discovery is turned off to enforce the least-privilege access principle for all discovered applications. Request new applications through an established internal process.

### Common Troubleshooting Items

The following list describes common issues related to Application Discovery operation:

- Application is not being discovered or accessed:
  - If not discovered, check if the following preconditions are met:
    - The application was accessed within the last 14 days.
    - The application discovery segment covers the ports used by the application in question.
    - The specified application discovery criteria cover the destination URL or IP address.
  - If discovered but not accessed, check for the following:
    - Is there a more specific application segment that overrides policies set for the application discovery segment.
    - Is there an [access policy](https://help.zscaler.com/zpa/configuring-access-policies) for the application segment that prevents the user from accessing the application.
    - Does the [User Activity Diagnostics](https://help.zscaler.com/zpa/about-user-activity-diagnostics) and the [bypass settings](https://help.zscaler.com/zpa/configuring-bypass-settings) of the application segment show the application is forwarded to ZPA.
- Zscaler Client Connector requests reauthentication, but the reauthentication fails: If your identity provider (IdP) is defined as an application within an application segment, the Authentication Timeout for the IdP application must be set to Never. If an IdP domain overlaps with a domain configured for application discovery, you must bypass the IdP domain in ZPA to avoid user reauthentication failure.

## Deployment Checklist

Zscaler recommends downloading the [Application Discovery Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zpa-deployments-operations/application-discovery-deployment-and-operations-guide/Application-Discovery-Deployment-Operations-Checklist.pdf) to help plan and implement Application Discovery: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zpa-deployments-operations/application-discovery-deployment-and-operations-guide/Application-Discovery-Deployment-Operations-Checklist.pdf)

## Additional Information

For more application discovery information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com/).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/authentication-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1420096"} -->
## Authentication Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/authentication-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZIA Deployments & Operations > Authentication Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits of and the steps necessary to enforce authentication in Zscaler Internet Access (ZIA).

This guide describes the benefits of enforcing authentication and the steps necessary for configuring Zscaler Internet Access (ZIA) to add authentication to your security posture.

ZIA enforces web and firewall policies by location, department, group, and user. It also tracks internet usage by location, department, and user.

You must provision and authenticate users to enforce granular authentication policies and reporting capabilities for ZIA. Provisioning involves uploading users, groups, and departments to the service database. Enabling authentication allows ZIA to:

- Identify received traffic.
- Enforce the location, department, group, and user policies.
- Provide user and department logging and reporting.

To learn more, see [About Provisioning and Authentication Users](https://help.zscaler.com/zia/about-provisioning-authenticating-users).

## Value of Enforcing Authentication

Enforcing authentication provides the following benefits:

- Enforces policies based on user criteria.
- Maps transactions to individual users and increases the reporting detail.

## Deployment Phase

The deployment phase initially sets up and integrates ZIA solutions into an existing network infrastructure. During the deployment phase, you configure authentication in ZIA to meet the needs of your infrastructure. The following sections discuss steps to enable authentication.

### Prerequisites

For authentication deployment, verify and complete the following prerequisites:

- Provision the authentication domain needed to identify the organization (by raising a [Zscaler Support ticket](https://help.zscaler.com/submit-ticket-links)).
- Identify the users and groups that use ZIA.
- Identify what identity provider (IdP) certificate, login URLs, and metadata must be available.

### Deployment Steps

The following steps explain how to enable authentication:

1. Configure a provisioning method based on the instructions and information provided in [Choosing Provisioning and Authentication Methods](https://help.zscaler.com/zia/choosing-provisioning-authentication-methods). Zscaler recommends [SCIM-based provisioning](https://help.zscaler.com/zia/configuring-scim) to allow for real-time synchronization.
2. Configure an authentication method based on the instructions and information provided in [Choosing Provisioning and Authentication Methods](https://help.zscaler.com/zia/choosing-provisioning-authentication-methods). Zscaler recommends using an [Identity Federation using SAML](https://help.zscaler.com/zia/understanding-saml).
3. If using a [Zscaler Authentication Bridge (ZAB)](https://help.zscaler.com/zia/about-zscaler-authentication-bridge), [deploy the ZAB](https://help.zscaler.com/zia/deploying-zscaler-authentication-bridge).
4. Add [authentication bypasses](https://help.zscaler.com/zia/exempting-urls-cloud-apps-authentication) for the IdP authentication URLs to authenticate end users. You might need to exempt IdP URLs from being forwarded to ZIA for remote users and forward them via a direct path instead.
5. (Optional) If you use Zscaler Client Connector for authentication, [deploy Zscaler Client Connector](https://help.zscaler.com/zia/policies/mobile-security/zscaler-client-connector-deployment) to end users’ devices.
6. Enable [Enforce Authentication](https://help.zscaler.com/zia/configuring-locations) on locations where it is needed.
7. If using Kerberos, enable [Kerberos Authentication](https://help.zscaler.com/zia/configuring-locations) on locations where Kerberos authentication is needed.
8. (Optional) To map users to device IP addresses, enable [IP Surrogate](https://help.zscaler.com/zia/about-surrogate-ip). This is not required for Zscaler Client Connector.
9. (Optional) If you enabled IP Surrogate and you want to skip authentication for a specific time duration, enable [Surrogate IP for Known Browsers](https://help.zscaler.com/zia/configuring-locations).

### Considerations

Review the following considerations:

- If you don’t use Zscaler Client Connector, Zscaler uses cookie-based authentication for most traffic. To learn more, see [About Zscaler Cookies](https://help.zscaler.com/zia/about-zscaler-cookies).
- If you use cookie-based authentication, you must enable SSL inspection for TLS-encrypted destinations. If SSL inspection isn’t used, use [IP Surrogacy](https://help.zscaler.com/zia/about-surrogate-ip) to map transactions to the previously authenticated user.
- Applications that cannot perform cookie-based authentication either hit Zscaler as unauthenticated traffic or, if [IP Surrogacy is enabled](https://help.zscaler.com/zia/about-surrogate-ip), map to the last authenticated user associated with this IP.
- IP Surrogacy maps the private IP address to a new user if a different user logs in to the Zscaler service from the same private IP address. If the mapping changes more than three times in a minute (e.g., three different users log in and surf the internet from the same private IP address within a minute), the service stops mapping users to the private IP address for 5 minutes and applies the location policies to the transactions that do not support authentication during these 5 minutes. Zscaler discourages the use of IP Surrogacy on shared hosts.
- Users who try to authenticate from a known location are automatically forwarded to the associated IdP. If the user is not coming from a known location, they must provide their user ID to get forwarded to the correct IdP.
- URL Filtering policies assume an implicit Allow All rule as a last resort choice if no other URL filtering rule applies to the traffic. This means that unauthenticated traffic is allowed by default. To restrict unauthenticated traffic, you must add specific URL filtering rules. To learn more, see [Configuring Policies for Unauthenticated Traffic](https://help.zscaler.com/zia/configuring-policies-for-unauthenticated-traffic).
- The refresh time for the revalidation of IP Surrogacy must be shorter than the DHCP lease time. Otherwise, ZIA might apply the wrong user policies. Zscaler recommends setting the Refresh Time for re-validation of Surrogacy to a period shorter than what you specified for Idle Time to Disassociation. To learn more, see [Configuring Locations](https://help.zscaler.com/zia/configuring-locations) and [Configuring Sub-Locations](https://help.zscaler.com/zia/about-sub-locations#addsubloc).

## Operations Phase

This section describes common practices used to operate ZIA solutions when integrated with your environment. You can monitor and tune authentication during the operations phase to meet your infrastructure needs.

### Prerequisites

For authentication operation, complete the following prerequisites:

- Store relevant IdP certificates and login URLs in a secure place, and make them available to relevant stakeholders for troubleshooting and backup purposes.
- Store SCIM Bearer Tokens in a secure place.
- Formulate an Incident Response Plan for situations where your IdP is unavailable.

### Common Troubleshooting Items

The following list describes common issues related to authentication operation:

- User and group synchronization is not happening with SAML auto provisioning: SAML auto provisioning doesn’t support real-time synchronization, so you must manually synchronize new groups if the users or their group mapping recently changed. Users must reauthenticate to make updated information available.
- User and group synchronization not happening with SCIM provisioning:
  - Check the IdP event logs to see if the IdP triggered a SCIM sync.
  - Check the ZIA Admin Portal event logs to see if Zscaler successfully validated the SCIM sync.
  - Check if the users and groups that needed synchronization to Zscaler are within the scope of entities synced on the IdP.
- Users are not receiving an authentication prompt, or traffic is not being authenticated:
  - Check if SSL inspection is enabled for the destination.
  - Check if authentication is enforced on the location from which the users are accessing the destination.
  - Check if the browser or application is a supported agent and supports cookie-based authentication.
- Users are not correctly mapped in [Insights](https://help.zscaler.com/zia/about-insights) and [Interactive Reports](https://help.zscaler.com/zia/about-interactive-reports)): Check if [IP Surrogacy](https://help.zscaler.com/zia/about-surrogate-ip) is enabled and if multiple users try to authenticate using the same private IP.

## Deployment and Operations Checklist

Zscaler recommends downloading the [Authentication Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/authentication-deployment-and-operations-guide/Authentication-Deployment-Operations-Checklist.pdf) to help plan and enable authentication in ZIA: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/authentication-deployment-and-operations-guide/Authentication-Deployment-Operations-Checklist.pdf)

## Additional Information

For more SaaS Security information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com/).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/bandwidth-control-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1417461"} -->
## Bandwidth Control Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/bandwidth-control-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZIA Deployments & Operations > Bandwidth Control Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits of and the steps necessary to enable Zscaler Internet Access (ZIA) Bandwidth Control.

This guide describes the benefits of using Bandwidth Control and the steps necessary for configuring Zscaler Internet Access (ZIA) to add Bandwidth Control to your security posture.

Bandwidth Control allows you to preserve access to your business-critical applications regardless of your internet pipe consumption by adding more restrictive rules around activities such as social media and streaming media.

To learn more, see [About Bandwidth Control](https://help.zscaler.com/zia/about-bandwidth-control).

## Value of Deploying Bandwidth Control

Using Bandwidth Control provides the following benefits:

- Limits the impact of streaming media, file sharing, and social media on business apps.
- Identifies bandwidth constraints before they impede the user experience.
- Aligns policies for all users to business needs, with granular rules by application class, location, and time.

## Deployment Phase

The deployment phase includes initially setting up and integrating Zscaler solutions into an existing network infrastructure. During the deployment phase, configure ZIA Bandwidth Control to meet the needs of your infrastructure. The following sections discuss steps to deploy ZIA Bandwidth Control.

### Prerequisites

For Bandwidth Control deployment, verify and complete the following prerequisites:

1. One of the following ZIA subscriptions is required:
  - ZIA Business Edition and later
  - Bandwidth Control Add-On
2. Determine the available bandwidth for each location where you plan to add Bandwidth Control. For example, for an organization deploying Bandwidth Control at a head office and two branch offices:
  - Headquarters: 1 Gbps
  - Bangalore office: 250 Mbps
  - Mumbai office: 150 Mbps
3. Determine the maximum bandwidth requirement for any [sub-locations](https://help.zscaler.com/zia/about-sub-locations) at each [location](https://help.zscaler.com/zia/about-locations). Using the previous example:
  - HR team (configured as a sub-location called HR in the ZIA Admin Portal) at the headquarters: 250 Mbps
  - Support team (configured as a sub-location called Support in the ZIA Admin Portal) at the headquarters: 500 Mbps
  - Guest Wi-Fi (configured as a sub-location called Guest Wifi in the ZIA Admin Portal) at the Bangalore office: 40 Mbps
  - Marketing team (configured as a sub-location called Marketing in the ZIA Admin Portal) at the Mumbai office: 150 Mbps
4. Determine a list of productivity apps or categories (e.g., Outlook, OneDrive, SuccessFactors, etc.) that need an allocated minimum guaranteed bandwidth.
5. Determine a list of non-productivity apps or categories (e.g., streaming, social networking, new sites, etc.) that have a defined bandwidth cap.
6. (Optional) Consider whether you need to enforce bandwidth rules 24 hours per day, during peak working hours, or for some other specific period using [Time Intervals](https://help.zscaler.com/zia/about-time-intervals).
7. For an example, see [Bandwidth Control Policy Example](https://help.zscaler.com/zia/bandwidth-control-policy-example).

### Considerations

Review the following considerations:

- About five to seven percent of TCP traffic is overhead (e.g., packet headers). The Zscaler service does not include overhead in its bandwidth calculations. It only includes the application traffic that is proxied to Zscaler. Therefore, the best practice for computing a location's bandwidth is as follows:
  - Actual bandwidth should be set at 10 to 15 percent less than the measured traffic to account for the protocol overhead when setting the upload and download bandwidth in the ZIA Admin Portal.
  - For example, if the actual bandwidth of a location is 100 Mbps, set the location bandwidth in ZIA Admin Portal to 85 Mbps (100 minus 15 percent).
- Determine the times of day to apply the Bandwidth Control policies. Define the time settings using the [Time Intervals](https://help.zscaler.com/zia/defining-time-intervals) option.
  - For example, you can apply a Bandwidth Control policy limitation for streaming media apps during business hours (9:00 AM to 5:00 PM) and leave the bandwidth for these apps unrestricted at all other times.
- While creating [bandwidth classes](https://help.zscaler.com/zia/about-bandwidth-classes), consider using [cloud applications](https://help.zscaler.com/zia/about-cloud-app-categories) (if available).
- On a sub-location where [Enforce Bandwidth Control is disabled](https://help.zscaler.com/zia/configuring-bandwidth-control-policy), it uses the shared bandwidth at any given time.
- On a sub-location with [Enforce Bandwidth Control set to Override](https://help.zscaler.com/zia/configuring-bandwidth-control-policy), the defined bandwidth is dedicated to that sub-location. It can't be used by any other sub-location, regardless if there is any traffic or not on the sub-location.

For example, given the following configuration:

| Location Name in ZIA | Bandwidth Control Configuration | Actual Throughput | Configured Throughput (Actual - 15%) |
| --- | --- | --- | --- |
| HQ | Enabled | 100 Mbps Up/Down | 85 Mbps Up/Down |
| Sub-location_1 | Override | 35 Mbps Up/Down | N/A |
| Sub-location_2 | Disabled | N/A | N/A |
| Other | Use Location Bandwidth | N/A | N/A |

- At any given time, HQ only uses up to the max shared bandwidth (i.e., 85 minus 35 equals 50 Mbps of total shared bandwidth).
- Since the Bandwidth Control option on Other is set to Use Location Bandwidth, the behavior is the same (i.e., Other only uses up to the maximum shared bandwidth of 50 Mbps).
- With Bandwidth Control set to Disabled, no Bandwidth Control policies and functionalities are applied. Bandwidth Control policies are applied if Bandwidth Control is set to Use Location Bandwidth.
- Sub-location_1 has a dedicated 35 Mbps of bandwidth. That bandwidth can’t be used by any other sub-location regardless if the sub-location has traffic.

### Deployment Steps

The following steps explain how to deploy Bandwidth Control:

1. Enable Bandwidth Control on the [location](https://help.zscaler.com/zia/configuring-bandwidth-control-policy#A) and [sub-location](https://help.zscaler.com/zia/configuring-bandwidth-control-policy#B).
2. Configure [bandwidth classes](https://help.zscaler.com/zia/about-bandwidth-classes).
3. Configure [Bandwidth Control policy](https://help.zscaler.com/zia/adding-rules-bandwidth-control-policy).
4. [Bandwidth Control Policy Example](https://help.zscaler.com/zia/bandwidth-control-policy-example) shows a Bandwidth Control configuration example.

## Operations Phase

This section describes common practices used to operate Zscaler solutions when integrated with your environment. You can monitor and tune ZIA Bandwidth Control during the operations phase to meet your infrastructure needs.

### Prerequisites

For Bandwidth Control operation, complete the following prerequisites:

- When your organization introduces a new productivity or business-related app, and a Bandwidth Control policy is necessary for this app:
  - Evaluate the criticality of this new application to your business.
  - [Create bandwidth classes](https://help.zscaler.com/zia/about-bandwidth-classes) for this app and configure the minimum bandwidth policy. That way, during bandwidth contention, the app is guaranteed its needed bandwidth.
- During a Zscaler Quarterly Operations Review (or internal audit), if a non-productivity app is overusing the office bandwidth:
  - Evaluate how much bandwidth this app uses.
  - [Create a bandwidth class](https://help.zscaler.com/zia/about-bandwidth-classes) for the app and then configure a maximum bandwidth policy. This restricts the bandwidth available to the app.
  - Regularly review the [Bandwidth Control dashboard](https://help.zscaler.com/zia/about-dashboards#bandwidth-control). If the bandwidth usage of any app swings, adjust the Bandwidth Control policies accordingly.
- (Optional) Create a [custom dashboard](https://help.zscaler.com/zia/creating-copying-report) for bandwidth usage as per your organization's requirements.

### Common Troubleshooting Items

The following list describes common issues related to Bandwidth Control operation:

- Bandwidth Control is not applied per expectations:
  - Check where the user is (in the office or remote) as Bandwidth Control policies aren’t enforced for remote users.
  - Check if [Enforce Bandwidth Control](https://help.zscaler.com/zia/configuring-bandwidth-control-policy) is enabled for that location or sub-location.
  - Check the [Web Insight Logs](https://help.zscaler.com/zia/about-insights-logs) for that transaction, and check if the correct Bandwidth Control policy is getting enforced:
    - If not, check the URL against that transaction, and revisit the matching Bandwidth Control rule.
    - Add the URL in the correct [bandwidth class](https://help.zscaler.com/zia/about-bandwidth-classes).
    - Open a ticket with Zscaler Support for further analysis.
- A productivity app, already bandwidth-controlled, is having performance issues during peak production hours:
  - Visit the [Bandwidth Control dashboard](https://help.zscaler.com/zia/about-dashboards#bandwidth-control) and check for the top bandwidth classes. If the impacted bandwidth class is the top bandwidth user, and there is a performance issue, consider [increasing the policy’s minimum bandwidth percentage](https://help.zscaler.com/zia/adding-rules-bandwidth-control-policy). Consider increasing the minimum bandwidth five percent and test for performance. If there is no change, increase minimum bandwidth another five percent and test again. Repeat this process until performance is acceptable.
  - If your impacted bandwidth class is not the top bandwidth user, then consider [reducing the minimum bandwidth percentage](https://help.zscaler.com/zia/adding-rules-bandwidth-control-policy) of the top bandwidth user to balance it with the impacted Bandwidth Control policy.
  - Increasing or decreasing the values too much has implications on other apps used by your organization. Therefore, test thoroughly before changing the percentage.
- A non-productivity app, already bandwidth-controlled, is still using the highest bandwidth.
  - Consider [reducing the maximum bandwidth percentage](https://help.zscaler.com/zia/adding-rules-bandwidth-control-policy) for this Bandwidth Control policy.
  - Consider blocking this app during business hours by implementing Zscaler’s [Cloud App Control](https://help.zscaler.com/zia/about-cloud-app-control) or [URL Filtering](https://help.zscaler.com/zia/about-url-filtering) policies.

## Deployment Checklist

Zscaler recommends downloading the [Bandwidth Control Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/bandwidth-control-deployment-and-operations-guide/Bandwidth-Control-Deployment-Operations-Checklist.pdf) to help plan and implement ZIA Bandwidth Control: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/bandwidth-control-deployment-and-operations-guide/Bandwidth-Control-Deployment-Operations-Checklist.pdf)

## Additional Information

For more SaaS Security information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com/).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/browser-access-deployment-and-operations-guide","lastmod":"2026-05-13T07:06Z","nid":"1420976"} -->
## Browser Access Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/browser-access-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZPA Deployments & Operations > Browser Access Deployment and Operations Guide
- Last modified: 2026-05-13T07:06Z
- Summary: Describes the benefits of and the steps necessary to enable Browser Access on Zscaler Private Access (ZPA).

This guide describes the benefits of using Browser Access and the steps necessary for configuring Zscaler Private Access (ZPA) to add Browser Access to your security posture. Browser Access leverages a web browser for user authentication and application access over ZPA without Zscaler Client Connector.

To learn more, see [About Browser Access](https://help.zscaler.com/zpa/about-browser-access).

## Value of Deploying Browser Access

Enabling Browser Access for ZPA provides the following benefits:

- Allows you to include devices without Zscaler Client Connector installed. For example, you might want to:
  - Control user access to applications on devices with operating systems currently unsupported by Zscaler Client Connector.
  - Provide third-party access to applications on devices that aren’t owned or managed by your company (e.g., a contractor or partner).
- Makes applications accessible for users from any web browser without Zscaler Client Connector or browser plugins and configurations.
- Uses your existing identity provider (IdP) to provide access to your current users, contractors, and other third-party users without managing an internet footprint.

## Deployment Phase

The deployment phase includes initially setting up and integrating Zscaler solutions into an existing network infrastructure. During the deployment phase, you configure Browser Control to meet the needs of your infrastructure. The following sections discuss steps to deploy Browser Control.

### Prerequisites

For Browser Access deployment, observe the following prerequisites:

- Browser Access might require an additional license for your organization. Check with your Zscaler Account team to verify the necessary licensing requirements.
- Ensure that application servers support Transport Layer Security (TLS) 1.2 encryption.
- Any user, contractor, or third party using Browser Access must be [provisioned in the ZPA IdP](https://help.zscaler.com/zpa/configuring-idp-single-sign).

### Deployment Steps

The following steps explain how to deploy Browser Access:

1. ZPA uses web server certificates to provide access to Browser Access. You get a certificate by:
  1. Uploading an [existing web server certificate](https://help.zscaler.com/zpa/about-uploadingBrowserAccessCertificate#UploadNotPending).
  2. [Creating a certificate signing request (CSR)](https://help.zscaler.com/zpa/about-creatingBrowserAccessCsr) for a web server certificate and [uploading the signed certificate](https://help.zscaler.com/zpa/uploading-web-server-certificates#UploadPending).
2. Define a Browser Access application by creating an application segment or [editing an existing application segment](https://help.zscaler.com/zpa/defining-browser-access-application-within-existing-application-segment).
3. (Optional) Define a Browser Access application with [multiple ports on the same domain](https://help.zscaler.com/zpa/defining-browser-access-application-multiple-ports-same-domain).
4. (Optional) Define a Browser Access application with [different external and internal domains](https://help.zscaler.com/zpa/defining-browser-access-application-different-external-vs-internal-domains).
5. (Optional) Enable [cross-origin resource sharing (CORS) requests](https://help.zscaler.com/zpa/configuring-authentication-settings).
6. (Optional) Enable [SameSite Cookie Attribution](https://help.zscaler.com/zpa/configuring-authentication-settings).

### Considerations

Review the following considerations:

- If you intend to use a different external and internal hostname for an application that has browser access enabled within an application segment:
  - Internal hostnames are not exposed, so there is no record of internal hostnames on the public DNS.
  - Backend Secure Sockets Layer (SSL) cannot be verified, so a web server certificate error is displayed to your end users because the hostname of the application doesn't match the certificate's hostname.
- If you intend to use the same external and internal hostname for an application that has browser access enabled within an application segment:
  - Internal hostnames are exposed on the public DNS.
  - Backend SSL can be verified, so end users don’t receive a web server certificate error.
- Browser Access cookies are session-based and are cleared when a web browser's session terminates. Users must authenticate before accessing applications via Browser Access. In addition, users are asked to reauthenticate periodically based on the Authentication Timeout setting of the ZPA timeout policy rule. To learn more, see [Configuring Timeout Policies](https://help.zscaler.com/zpa/configuring-timeout-policies).
- To learn if specific browsers support CORS requests, see [About Browser Access](https://help.zscaler.com/zpa/about-browser-access).
- Browser Access only accesses web-based applications. You can leverage [Privileged Remote Access](https://help.zscaler.com/zpa/about-privileged-portals) to reach applications on protocols other than HTTP/HTTPS.
- For all Browser Access applications with CORS, access is always allowed if the following conditions exist:
  - Browser setting that allows third-party cookies is **On**.
  - The JavaScript from the server has:
    - xhr.withCredentials=true;
    - CORS for the GET request are sent first, since Exporter doesn't support CORS for POST.
    - Cookies are sent in response to CORS GET.
    - Requests to CORS POST occur with cookies and access **Allowed**.

## Operations Phase

This section describes standard practices used to operate Zscaler solutions when integrated with your environment. You can monitor and tune Browser Access during operations to meet your infrastructure needs.

### Prerequisites

Document which applications correlate to which application segments for Browser Access operations.

### Common Troubleshooting Items

You might receive a `403 Forbidden` error if the application fails to load a domain with CORS headers enabled. To support CORS and same-site requests from the applications configured for Browser Access, follow the instructions in [Configuring Authentication Settings](https://help.zscaler.com/zpa/configuring-authentication-settings).

To learn more, see [Understanding Browser Access Error Codes](https://help.zscaler.com/zpa/understanding-browser-access-error-codes).

## Deployment Checklist

Zscaler recommends downloading the [Browser Access Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zpa-deployments-operations/browser-access-deployment-and-operations-guide/Browser-Access-Deployment-Operations-Checklist.pdf) to help plan and implement Browser Access on ZPA: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zpa-deployments-operations/browser-access-deployment-and-operations-guide/Browser-Access-Deployment-Operations-Checklist.pdf)

## Additional Information

For more information and troubleshooting instructions regarding Browser Access, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/client-forwarding-policy-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1417936"} -->
## Client Forwarding Policy Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/client-forwarding-policy-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZPA Deployments & Operations > Client Forwarding Policy Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits of and the steps necessary to enable Zscaler Private Access (ZPA) Client Forwarding Policy.

This guide describes the benefits of using Client Forwarding Policy and the steps necessary for configuring Zscaler Private Access (ZPA) to add Client Forwarding policies to your security posture.

ZPA evaluates Client Forwarding Policy rules using the most specific application segment and a top-down, first-match principle. The process occurs before ZPA evaluates Access Policy rules. When ZPA finds a match in the Client Forwarding Policy, ZPA disregards other Client Forwarding Policy rules, including conflicts.

To learn more, see [About Client Forwarding Policy](https://help.zscaler.com/zpa/about-client-forwarding-policy).

## Value of Deploying Client Forwarding Policy

Using Client Forwarding Policy provides more granular traffic control processing to meet your organization’s needs.

## Deployment Phase

The deployment phase initially sets up and integrates ZPA solutions into an existing network infrastructure. During the deployment phase, you configure ZPA Client Forwarding Policy to meet the needs of your infrastructure. The following sections discuss steps to deploy ZPA Client Forwarding Policy.

### Prerequisites

To use this feature, you must set the Application Segment bypass behavior to Client Forwarding Policy.

### Deployment Steps

The following steps explain how to deploy ZPA Client Forwarding Policy:

1. Configure a [Client Forwarding Policy](https://help.zscaler.com/zpa/configuring-client-forwarding-policies).
2. Set the [Application Segment bypass behavior](https://help.zscaler.com/zpa/configuring-bypass-settings) to Use Client Forwarding Policy.
3. When creating the Client Forwarding Policy, choose a rule action:
  - Bypass ZPA: If selected, applications are marked and bypassed for ZPA, and Zscaler Client Connector does not send application requests to ZPA. This setting is the equivalent of selecting Always for the Bypass setting within an application segment. To learn more, see [Configuring Application Segments](https://help.zscaler.com/zpa/configuring-application-segments).
  - Only Forward Allowed Applications: If selected, Zscaler Client Connector learns about and forwards traffic for applications that a user is allowed to access. The Access Policy determines the list of applications to which the user has access. When Zscaler Client Connector sets up a connection to ZPA, the service determines the list of applications that a user can access and downloads this list to Zscaler Client Connector for forwarding traffic to ZPA. Zscaler Client Connector forwards requests for applications not on the list to the local network. Requests for these applications are not logged in ZPA.
  - Forward to ZPA: If selected, Zscaler Client Connector forwards application requests to ZPA. This includes requests for applications that the user is not authorized to access. By default, these requests are logged in the Diagnostic logs as Policy not configured for access.
4. Select a criteria option:
  - [Applications](https://help.zscaler.com/zpa/configuring-client-forwarding-policies#apps)
  - [Client Connector Posture Profiles](https://help.zscaler.com/zpa/configuring-client-forwarding-policies#postureprofile)
  - [Client Connect Trusted Networks](https://help.zscaler.com/zpa/configuring-client-forwarding-policies#networks)
  - [Client Types](https://help.zscaler.com/zpa/configuring-client-forwarding-policies#clients)
  - [Machine Groups](https://help.zscaler.com/zpa/configuring-client-forwarding-policies#machinegrps)
  - [Platforms](https://help.zscaler.com/zpa/configuring-client-forwarding-policies)
  - [SAML and SCIM Attribute](https://help.zscaler.com/zpa/configuring-client-forwarding-policies#samlscimattribute)

### Considerations

Review the following considerations:

- A Client Forwarding Policy might not be required for some applications. For example, you can configure an application to bypass ZPA when users are on the trusted network directly in the [Application Segment settings](https://help.zscaler.com/zpa/configuring-application-segments) (without a Client Forwarding Policy). This granularity is helpful for mergers and acquisitions where application access should be granted based on trusted network criteria.
- Select Only Forward Allowed Applications if you are setting up a Client Forwarding Policy to use as part of Source IP Anchoring in Zscaler Internet Access (ZIA).
- If you have multiple Client Forwarding Policy rules, apply Bypass ZPA and Forward to ZPA rule actions to the same application segment, the Forward to ZPA rule action takes precedence.
- For application segments, the bypass setting takes precedence over any new Client Forwarding Policy rule.

## Operations Phase

This section describes common practices used to operate Zscaler solutions when integrated with your environment. You can monitor and tune ZPA Client Forwarding Policy during operations to meet your infrastructure needs.

### Prerequisites

Ensure that you thoroughly test the Client Forwarding Policy using different forwarding methods (especially when you have defined complex criteria sets).

### Common Troubleshooting Items

The following list describes common issues related to Client Forwarding Policy operation:

- The Client Forwarding Policy is not successfully applied to the application: This occurs if Only Forward Allowed Applications is selected and an Access Policy allowing access was not created. In this case, the application the user is trying to reach is not present in the list of applications downloaded by Zscaler Client Connector.
- Another reason could be the configured behavior action does not match the criteria. For example, trusted network detection or posture checks might not match the defined criteria. Zscaler Client Connector re-evaluates posture checks at startup and every 15 minutes thereafter. For Trusted Network, verify that the user's network settings are configured to one of the defined settings in the trusted network criteria.
- The criteria are not being met: In this instance, check if the criteria definitions are using either an AND or OR operator. The operator choice could explain why the Client Forwarding Policy criteria are not being met and enforced.

## Deployment Checklist

Zscaler recommends downloading the [Client Forwarding Policy Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zpa-deployments-operations/client-forwarding-policy-deployment-and-operations-guide/Client-Forwarding-Policy-Deployment-Operations-Checklist.pdf) to help plan and implement ZPA Client Forwarding Policy: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zpa-deployments-operations/client-forwarding-policy-deployment-and-operations-guide/Client-Forwarding-Policy-Deployment-Operations-Checklist.pdf)

## Additional Information

For more Client Forwarding Policy information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/cloud-app-control-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1417301"} -->
## Cloud App Control Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/cloud-app-control-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZIA Deployments & Operations > Cloud App Control Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits of and the steps necessary to enable Zscaler Internet Access (ZIA) Cloud App Control.

This guide describes the benefits of using Cloud App Control and the steps necessary for configuring Zscaler Internet Access (ZIA) to add Cloud App Control to your security posture.

Cloud App Control policies provide granular control over popular websites and applications. They are organized by function into categories for easy reference and to facilitate defining rules for similar apps.

To learn more, see [About Cloud App Control](https://help.zscaler.com/zia/about-cloud-app-control).

## Value of Deploying Cloud App Control

Using Cloud App Control provides the following benefits:

- Controls users’ access to specific cloud applications.
- Controls access using a predefined set of [cloud application policy rules](https://help.zscaler.com/zia/adding-rules-cloud-app-control-policy).
- Defines daily access limits via criteria such as bandwidth or time.
- Uses the [Cloud Application Risk Profile](https://help.zscaler.com/zia/about-cloud-application-risk-profile).

## Deployment Phase

The deployment phase includes initially setting up and integrating ZIA solutions into an existing network infrastructure. During the deployment phase, you configure ZIA Cloud App Control to meet the needs of your infrastructure. The following sections discuss steps to deploy ZIA Cloud App Control.

### Prerequisites

One of the following Zscaler subscriptions is required:

- ZIA Business Edition and later.
- Cloud Apps Control subscription.

### Deployment Steps

The following steps explain how to deploy ZIA Cloud App Control:

1. Determine whether to apply URL Filtering Policies even after a Cloud App Control policy explicitly allows a transaction. To learn more, see [Advanced Web App Control Options](https://help.zscaler.com/zia/about-advanced-settings#web-app-control).

Refer to [About Policy Enforcement](https://help.zscaler.com/zia/about-policy-enforcement) for an in-depth understanding of policy enforcement and the policy evaluation workflow.

1. Collect the list of the cloud categories and applications for which you would like to control access. For the list of supported cloud applications, see [Understanding Cloud App Categories](https://help.zscaler.com/zia/understanding-cloud-app-categories).
2. Configure the required rules:
  - [AI & ML Applications](https://help.zscaler.com/zia/adding-ai-ml-applications-rule-cloud-app-control)
  - [Collaboration & Online Meetings](https://help.zscaler.com/zia/adding-collaboration-online-meetings-rule-for-cloud-app-control)
  - [Consumer](https://help.zscaler.com/zia/adding-consumer-rule-cloud-app-control)
  - [Custom Applications](https://help.zscaler.com/zia/adding-custom-applications-rule-cloud-app-control)
  - [DNS Over HTTPS Services](https://help.zscaler.com/zia/adding-dns-over-https-services-rule-cloud-app-control)
  - [File Sharing](https://help.zscaler.com/zia/adding-file-sharing-rule-cloud-app-control)
  - [Finance](https://help.zscaler.com/zia/adding-finance-rule-cloud-app-control)
  - [Health Care](https://help.zscaler.com/zia/adding-health-care-rule-cloud-app-control)
  - [Hosting Providers](https://help.zscaler.com/zia/adding-hosting-providers-rule-cloud-app-control)
  - [Human Resources](https://help.zscaler.com/zia/adding-human-resources-rule-cloud-app-control)
  - [Instant Messaging](https://help.zscaler.com/zia/adding-instant-messaging-rule-cloud-app-control)
  - [IT Services](https://help.zscaler.com/zia/adding-it-services-rule-cloud-app-control)
  - [Legal](https://help.zscaler.com/zia/adding-legal-rule-cloud-app-control)
  - [Productivity & CRM Tools](https://help.zscaler.com/zia/adding-productivity-crm-tools-rule-cloud-app-control)
  - [Sales & Marketing](https://help.zscaler.com/zia/adding-sales-marketing-rule-cloud-app-control)
  - [Social Networking](https://help.zscaler.com/zia/adding-social-networking-rule-cloud-app-control)
  - [Streaming Media](https://help.zscaler.com/zia/adding-streaming-media-rule-cloud-app-control)
  - [System & Development](https://help.zscaler.com/zia/adding-system-development-rule-cloud-app-control)
  - [Webmail](https://help.zscaler.com/zia/adding-webmail-rule-cloud-app-control)

You can select the [Cloud Application Risk Profile](https://help.zscaler.com/zia/about-cloud-application-risk-profile) or the Cloud Applications field when defining rules for the Cloud App Category.

### Considerations

Review the following considerations:

- The default policy behavior is Allow All.
- Rules evaluation proceeds in order from top to bottom.
- If a user requests a cloud app that you explicitly allow with a Cloud App Control policy rule, the service only applies the Cloud App Control policy and does not apply a URL Filtering policy. You can change this by enabling [Allow Cascading to URL Filtering](https://help.zscaler.com/zia/about-advanced-settings#web-app-control).
- Cloud App Control policies do not support custom End User Notifications (EUNs).
- The Cloud App Control policy rule applies to all specified cloud applications if you select the [cloud application risk profile criterion](https://help.zscaler.com/zia/about-cloud-application-risk-profile).
- You can select the Cloud Application Risk Profile or the Cloud Applications field for the rule.

## Operations Phase

This section describes standard practices used to operate Zscaler solutions when integrated with your environment. You can monitor and tune ZIA Cloud App Control during the operations phase to meet your infrastructure needs.

### Prerequisites

For Cloud App Control operation, complete the following prerequisites:

- Document the [Allow Cascading to URL Filtering](https://help.zscaler.com/zia/about-advanced-settings#web-app-control) settings to understand the flow of the evaluated rules to help internal personnel and Zscaler Support troubleshoot issues.
- Generally, allow Cloud App Control policies over URL filtering rules to use the predefined Cloud Applications.

### Common Troubleshooting Items

The following list describes common issues related to Cloud App Control operation:

- Zscaler is blocking an allowed cloud app: Verify that you enabled [Allow Cascading to URL Filtering](https://help.zscaler.com/zia/about-advanced-settings#web-app-control). If so, verify that you have a corresponding URL filtering rule that allows this traffic.
- Users can still access a blocked cloud app:
  - Verify if transactions from the affected users are visible in Zscaler Web/Firewall Insights. If not, it’s possible the traffic goes direct and bypasses the rules. The [PAC file / Proxy settings](https://help.zscaler.com/zia/writing-pac-file) can block the traffic.
  - Verify if the protocol used is QUIC in Zscaler Firewall Insights. If yes, disable QUIC in Internet Browser settings.

## Deployment and Operations Checklist

Zscaler recommends downloading the [Cloud App Control Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/cloud-app-control-deployment-and-operations-guide/Cloud-App-Control-Deployment-Operations-Checklist.pdf) to help plan and implement ZIA Cloud App Control: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/cloud-app-control-deployment-and-operations-guide/Cloud-App-Control-Deployment-Operations-Checklist.pdf)

## Additional Information

For more Cloud App Control information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and [Zscaler Zenith Community](http://community.zscaler.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/cloud-browser-isolation-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1417406"} -->
## Isolation Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/cloud-browser-isolation-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZIA Deployments & Operations > Isolation Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits and steps necessary to enable Zscaler Internet Access (ZIA)  Isolation.

This guide describes the benefits of using Isolation and the steps necessary for configuring Zscaler Internet Access (ZIA) to add Isolation to your security posture.

Isolation isolates users from potentially harmful content on the internet by loading the accessed web page on a remote browser in a Zscaler data center and sending the rendered content as a stream of pixels to the user’s native browser.

This ensures that the HTML files, CSS files, JavaScript, and any other active content served by the accessed web page never reach the end user’s machine or the corporate network.

Isolation provides the following benefits:

- Zero-risk access to any destination by isolating the end user from potentially malicious content.
- Meets regulatory compliance requirements to isolate browser traffic.

To learn more, see [What Is Isolation](https://help.zscaler.com/isolation/what-is-isolation)?

## Deployment Phase

The deployment phase initially sets up and integrates Zscaler solutions into an existing network infrastructure. During the deployment phase, you configure ZIA Isolation to meet the needs of your infrastructure. The following sections discuss steps to deploy ZIA Isolation.

### Prerequisites

This feature requires:

A ZIA subscription and the Isolation add-on.

- Creating a URL Filtering Policy with the action Allow.
- Create a File Type Control Policy with the action Allow for Upload/Download.
- In addition, Isolation requires configuring the service URLs listed in the following table:

| Source | Destination URL | Port / Protocol | ZIA Configuration | Remarks |
| --- | --- | --- | --- | --- |
| User Machine | .files.rbi.zscaler.com | 443/HTTPS | URL Filtering policy. Bypassed from isolation. Configure the appropriate file type control. | This wildcard domain shares the files between isolation and the user machine (upload and download). |
| User Machine | .isolation.zscaler.com | 443/HTTPS | SSL inspection is bypassed by default (Zscaler recommends this policy). | This wildcard domain connects to the isolation service. |

### Deployment Steps

The following steps explain how to deploy ZIA Isolation:

1. Create a [ZIA Isolation profile](https://help.zscaler.com/isolation/configuring-zia-isolation).
2. Identify [criteria for enabling Isolation](https://help.zscaler.com/isolation/configuring-zia-isolation) (based on a risk or specific use case).
3. Create [URL filtering rules](https://help.zscaler.com/zia/configuring-url-filtering-policy) for respective destinations with web traffic action *Isolate*.

### Considerations

Review the following considerations:

- Latency is less than 100 ms for the optimal end user experience. Check through the [isolation browser menu](https://help.zscaler.com/isolation/accessing-network-diagnostics-isolation).
- Isolation only works with HTTP and HTTPS protocols in the web browser.
- The web traffic action *Isolate*is only available for known user agents. In a URL filtering rule, you cannot choose *Other*as the user agent criteria.
- SSL inspection for respective destinations is mandatory for redirecting HTTPS traffic to Isolation.

## Operations Phase

This section describes common practices used to operate Zscaler solutions when integrated with your environment. You can monitor and tune ZIA Isolation during the Operations phase to meet your infrastructure needs.

### Prerequisites

For Isolation operations, complete the following prerequisites:

- Create a standard operating procedure (SOP) for adding URL destinations to Isolation.
- Create an SOP for adding URL destinations to an isolation bypass.
- Create an SOP for collecting information to log a support case.

### Common Troubleshooting Items

The following list describes common issues related to the Isolation operation:

- The HTTPS website fails to redirect to Isolation: Check if SSL inspection is enabled for the destination.
- The website fails to load or is only partially loading content: Check logs to see if any ZIA policy blocks content.

## Deployment and Operations Checklist

Zscaler recommends downloading the [Isolation Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/cloud-browser-isolation-deployment-and-operations-guide/Isolation-Deployment-Operations-Checklist.pdf) to help plan and implement ZIA Isolation: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/cloud-browser-isolation-deployment-and-operations-guide/Isolation-Deployment-Operations-Checklist.pdf)

## Additional Information

For more Isolation information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/cloud-firewall-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1417316"} -->
## Firewall Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/cloud-firewall-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZIA Deployments & Operations > Firewall Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits of and the steps necessary to enable Zscaler Internet Access (ZIA) Firewall.

This guide describes the benefits of using Firewall and the steps necessary for configuring Zscaler Internet Access (ZIA) to add Firewall to your security posture.

Firewall provides the capabilities of traditional firewalls by monitoring and controlling an organization’s outbound traffic. These features include traditional network service control, dynamic application identification with deep packet inspection (DPI), and identification of web traffic on non-default ports. To learn more, see [Understanding Firewall Capabilities](https://help.zscaler.com/zia/understanding-firewall-capabilities).

## Value of Deploying Firewall

Using Firewall provides the following benefits:

- Full protection for work-from-anywhere users, on-premises or remote.
- Catch evasive attacks on non-standard ports.
- Secure local internet breakout for internet and SaaS applications.

## Deployment Phase

The deployment phase initially sets up and integrates ZIA solutions into an existing network infrastructure. During the deployment phase, you configure Firewall in ZIA to meet the needs of your infrastructure. The following sections discuss steps to deploy Firewall.

### Prerequisites

For Firewall deployment, verify and complete the following prerequisites:

1. Validate and review one of the needed subscriptions (i.e., Standard Firewall or Advanced Firewall). For information about subscriptions, see [Understanding Firewall Capabilities](https://help.zscaler.com/zia/understanding-firewall-capabilities).
2. Define a global rule set that identifies all needed criteria objects such as source/destination IPs, location groups, departments, etc.).
3. Validate that you have mapped the identified objects in the first two prerequisites to [Nanolog Streaming Service (NSS) firewall criteria](https://help.zscaler.com/zia/configuring-firewall-filtering-policy).
4. Validate that all parameters are within the [ZIA product limits](https://help.zscaler.com/zia/ranges-limitations).

### Deployment Steps

The following steps explain how to deploy Firewall:

1. Create the objects used as rule criteria (i.e., [Locations](https://help.zscaler.com/zia/configuring-locations), [Network Services](https://help.zscaler.com/zia/adding-network-service), [Destination Groups](https://help.zscaler.com/cloud-branch-connector/configuring-destination-groups), etc.).
2. [Create the Firewall rules](https://help.zscaler.com/zia/configuring-firewall-filtering-policy) in a disabled state.
3. Using NSS, deploy [Zscaler's Nanolog Streaming Service (NSS)](https://help.zscaler.com/zia/about-nanolog-streaming-service) firewall and [configure the NSS firewall feeds](https://help.zscaler.com/zia/adding-nss-feeds-firewall-logs).
4. Gradually [enable firewall rules and firewall features](https://help.zscaler.com/zia/enabling-firewall-locations) on locations.
5. Perform a controlled test per location before enabling all rules on all locations.
6. Enable a firewall for Z-Tunnel 1.0 and PAC remote users via the [advanced settings](https://help.zscaler.com/zia/about-advanced-settings).
7. Ensure traffic is forwarded to the ZIA Firewall from Zscaler Client Connector [using Z-Tunnel 2.0](https://help.zscaler.com/z-app/best-practices-deploying-z-tunnel-2.0).

### Considerations

Review the following considerations:

- Understand and respect the default [firewall control policy recommendations](https://help.zscaler.com/zia/recommended-firewall-control-policy).
- Build a rule set with specific rules first and less specific rules last. The order ensures that more specific policies are matched first in case of overlap.
- Be familiar with [predefined firewall filtering rules](https://help.zscaler.com/zia/about-predefined-firewall-filtering-rules).
- Understand the [difference between Network Service and Network Application](https://help.zscaler.com/zia/about-network-applications), and if possible, combine both on the same firewall rule to restrict DPI processes to the specific port.
- Be familiar with the [log fields in Zscaler Insights](https://help.zscaler.com/zia/firewall-insights-logs-columns).

## Operations Phase

This section describes common practices used to operate ZIA solutions when integrated with your environment. You can monitor and tune Firewall during the operations phase to meet your infrastructure needs.

### Prerequisites

For Firewall operation, complete the following prerequisites:

- Comment all firewall rules so the operations team can understand their purpose. Include other related objects (IP groups, URL categories, etc.) and Reference Change IDs/numbers (if you track these in internal tools).
- If not all locations use Firewall, document the reasoning for disabling firewalls for the specific locations.
- Validate that the team is familiar with all the essential concepts (i.e., Network Application vs. Network Service, NSS logs, etc.).

### Common Troubleshooting Items

Transactions marked as Allow due to insufficient app data: If transactions are logged as Allow due to insufficient app data on Firewall Insights, it might relate to [a match from our DPI mechanism and specific rule](https://help.zscaler.com/zia/about-network-applications).

## Deployment and Operations Checklist

Zscaler recommends downloading the [Firewall Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/cloud-firewall-deployment-and-operations-guide/Firewall-Deployment-Operations-Checklist.pdf) to help plan and implement Firewall: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/cloud-firewall-deployment-and-operations-guide/Firewall-Deployment-Operations-Checklist.pdf)

## Additional Information

For more SaaS Security information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com/).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/data-loss-prevention-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1417451"} -->
## DLP Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/data-loss-prevention-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZIA Deployments & Operations > DLP Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits of and the steps necessary to enable Zscaler Internet Access (ZIA) Data Loss Prevention (DLP).

This guide describes the benefits of using Data Loss Prevention (DLP) and the steps necessary for configuring Zscaler Internet Access (ZIA) to add DLP to your security posture. DLP detects potential data breaches and data ex-filtration transmissions and prevents them by monitoring, detecting, and blocking sensitive data while in use and motion.

To learn more, see [About Data Loss Prevention](https://help.zscaler.com/zia/about-data-loss-prevention).

## Value of Deploying DLP

Using DLP provides the following benefits:

- Protects your organization from data loss by monitoring or blocking content according to configured policies.
- Forwards information about transactions that trigger third-party DLP policies.
- Maintains compliance and data privacy.

## Deployment Phase

The deployment phase includes initially setting up and integrating Zscaler solutions into an existing network infrastructure. During the deployment phase, you configure ZIA DLP to meet the needs of your infrastructure. The following sections discuss the steps to deploy ZIA DLP.

### Prerequisites

One of the following Zscaler subscriptions is required:

- ZIA ELA Edition.
- Data Loss Prevention subscription.

### Deployment Steps

The following steps explain how to deploy ZIA DLP.

1. Identify the dataset and data categories that need protection from ex-filtration. Refer to your organization’s security and compliance policy for this information. For example, if an organization is Payment Card Industry Data Security Standard (PCI-DSS) compliant, you might need to consider monitoring all traffic related to the payment card industry (e.g., credit card, debit card, etc.).
2. Decide which DLP policy rules to configure. Your policy can simultaneously use all of the following options:
  - [Monitor or block data using Zscaler DLP engines](https://help.zscaler.com/zia/about-data-loss-prevention#option1).
  - [Monitor or block data using Zscaler DLP engines, then forward information to a third-party DLP solution](https://help.zscaler.com/zia/about-data-loss-prevention#opt2).
  - [Monitor or block data based on specific criteria, then forward information to a third-party DLP solution](https://help.zscaler.com/zia/about-data-loss-prevention#opt3).
3. Configure a DLP policy rule with [content inspection](https://help.zscaler.com/zia/configuring-policy-using-zscaler-dlp-engines) or [without content inspection](https://help.zscaler.com/zia/how-do-i-configure-policy-using-external-dlp-engines).
4. (Optional) Configure an End User Notification (EUN) that alerts users that the data they sent is not allowed to leave the organization. Zscaler recommends you add hyperlinks to company security policies in the EUN for better user education.
5. Start by configuring DLP policies that alert users to potential policy violations across a small subset of users and applications. This allows you to analyze false positives before taking action on policy notifications.

### Considerations

Review the following considerations:

- You can decide to use Zscaler DLP with or without content inspection.
- You can configure Zscaler DLP policies not to use Zscaler DLP engines. Traffic is forwarded to third-party DLP solutions based on matched criteria.
- Decide if a policy’s initial action should be Block or Allow. As a best practice, new policies are only monitored first, so you can fine-tune them to reduce false positives. When the policy shows low false positives, change the action to Block.
- Configure a custom [End User Notification](https://help.zscaler.com/zia/configuring-end-user-notifications).

## Operations Phase

This section describes common practices used to operate Zscaler solutions when integrated with your environment. During the operations phase, you can monitor and tune ZIA DLP to meet your infrastructure needs.

### Prerequisites

For DLP operation, complete the following prerequisites:

- Determine the false positive percentage of a DLP policy and let operations and support teams know what to expect while triaging incidents.
- Consider end user education for top actors to learn how to handle the sensitive data they use.

### Common Troubleshooting Items

If you see the message `Error: unable to update SSH public key`, review [Configuring the Zscaler Incident Receiver](https://help.zscaler.com/zia/configuring-zscaler-incident-receiver).

## Deployment Checklist

Zscaler recommends downloading the [DLP Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/data-loss-prevention-deployment-and-operations-guide/DLP-Deployment-Operations-Checklist.pdf) to help plan and implement ZIA DLP: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/data-loss-prevention-deployment-and-operations-guide/DLP-Deployment-Operations-Checklist.pdf)

## Additional Information

For more SaaS Security information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com/).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/dns-control-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1417401"} -->
## DNS Control Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/dns-control-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZIA Deployments & Operations > DNS Control Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits of and the steps necessary to enable DNS Control in Zscaler Internet Access (ZIA).

This guide describes the benefits of using DNS Control and the steps necessary for configuring Zscaler Internet Access (ZIA) to add DNS Control to your security posture.

DNS Control mitigates the risk of malware transmission, identifies infected endpoints using DNS tunnels, and restricts domains visited to comply with organizational standards and acceptable use.

The Zscaler service also detects and controls DNS tunneling occurring in your networks. The service operates as a DNS proxy. You can use this proxy as a firewall for DNS traffic. The service logs all traffic that goes through this proxy.

To learn more, see [About DNS Control](https://help.zscaler.com/zia/about-dns-control).

## Value of Deploying DNS

- Implements DNS security.
- Detects and controls DNS tunneling.
- Redirects requests to your organization’s trusted public DNS resolver.
- Improves DNS performance.
- Gives visibility into every DNS request and response.
- Controls DNS traffic regardless of the DNS resolver selected by the end user.
- Protections from evasive behavior even for TLS-encrypted traffic.
- Logs every transaction in a forensically complete and enriched format.

## Deployment Phase

The deployment phase includes initially setting up and integrating Zscaler solutions into an existing network infrastructure. During the deployment phase, you configure DNS Control to meet the needs of your infrastructure. The following sections discuss steps to deploy DNS Control.

### Prerequisites

DNS Control might require an additional license for your organization. Check with your Zscaler Account team to verify the necessary licensing requirements.

### Deployment Steps

The following steps explain how to deploy DNS Control:

1. Choose between the following scenarios:
  - Your organization wants to [send all traffic to Zscaler (office and remote users)](https://help.zscaler.com/zia/about-dns-control#client_traffic).
  - Your organization wants to [forward DNS traffic to the internal DNS server and then redirect it to Zscaler](https://help.zscaler.com/zia/about-dns-control#dns_server_traffic).
2. Modify the [Firewall Filtering policies](https://help.zscaler.com/zia/configuring-firewall-filtering-policy) so that DNS traffic passes through the cloud firewall.
3. Configure the [DNS Control policy](https://help.zscaler.com/zia/configuring-dns-control-policy).
4. (Optional) Configure [DNS tunneling detection](https://help.zscaler.com/zia/about-dns-tunnel-detection).
5. After adding rules to the DNS Control policy, you might also need to do the following before [enabling the firewall](https://help.zscaler.com/zia/enabling-firewall-locations) for your locations.
  1. Modify the rules for the [NAT Control policy](https://help.zscaler.com/zia/about-nat-control) and [Firewall Filtering policy](https://help.zscaler.com/zia/configuring-firewall-filtering-policy) to allow traffic to pass.
  2. Configure [custom ports](https://help.zscaler.com/zia/configuring-custom-ports) as applicable.
6. (Optional) [Define application groups](https://help.zscaler.com/zia/about-dns-application-groups).

### Considerations

- The DNS proxy model of DNS Control allows the DNS to be intercepted and resolved as soon as the request reaches the ZIA Public Service Edge. The DNS is configurable by endpoint and many other conditions in the destination NAT policy and essentially amounts to two modes:
  - Transit Option: This mode passes DNS requests through the proxy. A proxy safely hides the customer's IP address from third parties, including the external resolver (DNS security policy is still applied). Hiding the IP address is good for iterative requests that Zscaler can’t resolve, but DNS Control can secure.
  - Resolver Option: This option sees and intercepts the DNS request at the Public Service Edge and resolves the request pending DNS Control policy. This option provides all the security and performance benefits of quickly resolving DNS requests with a geographic context where users get the closest Microsoft or Amazon Web Services (AWS) point of presence.
- Zscaler recommends using the Resolver option. You can enable traffic to reach Zscaler's Trusted DNS Resolver in two ways:
  - Enable a [NAT control rule](https://help.zscaler.com/zia/configuring-nat-control-policy).
  - Use a [Z-Tunnel 2.0 with specified DNS exclusions and inclusions](https://community.zscaler.com/t/configuring-client-connector-for-dns-control-and-cloud-firewall/19101).

## Operations Phase

This section describes common practices used to operate Zscaler solutions when integrated with your environment. You can monitor and tune DNS Control during operations to meet your infrastructure needs.

### Prerequisites

For DNS Control operation, verify the following prerequisites:

- Document the methodology for deploying DNS Control (either the Transit or Resolver option).
- Consider [destination NAT for selective queries](https://help.zscaler.com/zia/about-nat-control) and its performance impact.
- Give the support team access to the DNS dashboard. This provides visibility into the most blocked domain categories and users, providing insights into incidents raised due to malicious domains getting blocked.

### Common Troubleshooting Items

The following list describes common issues related to DNS Control operation:

- Internal domains are blocked or not accessible: Check if the internal domains are part of DNS exclusions.
- Iterative queries are not getting resolved: Iterative DNS requests require additional policy configurations to transit the traffic through the Zscaler service (depending on your methodology). Disable the default rule in NAT Control or create a higher precedence rule to support iterative queries transiting the ZIA service. To learn more, see [About NAT Control](https://help.zscaler.com/zia/about-nat-control).
- New DNS domains are getting blocked: Domains that fall under malicious domains might get blocked per your policy. Verify whether these domains are getting blocked under blocked domains in the [DNS Overview dashboard](https://help.zscaler.com/zia/about-dashboards#DNS-overview).

## Deployment Checklist

Zscaler recommends downloading the [DNS Control Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/dns-control-deployment-and-operations-guide/DNS-Control-Deployment-Operations-Checklist.pdf) to help plan and implement DNS Control: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/dns-control-deployment-and-operations-guide/DNS-Control-Deployment-Operations-Checklist.pdf)

## Additional Information

For more SaaS Security information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com/).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/ips-control-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1417456"} -->
## IPS Control Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/ips-control-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZIA Deployments & Operations > IPS Control Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits of and the steps necessary to enable Intrusion Prevention System (IPS) Control for Zscaler Internet Access (ZIA).

This guide describes the benefits of using Intrusion Prevention System (IPS) Control and the steps necessary for configuring Zscaler Internet Access (ZIA) to add IPS Control to your security posture.

IPS Control uses signature-based detection to control and protect your traffic from intrusion over all ports and protocols. The Zscaler service uses custom signatures built and updated by Zscaler's security research team and signatures from industry-leading vendors. Using these signatures, the Zscaler service monitors your traffic in real time. When the IPS finds a pattern match in your traffic, it enforces your policies inline.

To learn more, see [About IPS Control](https://help.zscaler.com/zia/about-ips-control).

## Value of Deploying IPS Control

Using IPS Control provides the following benefits:

- Increases company security posture.
- Protects against threats from both web traffic and non-web traffic such as HTTP, HTTPS, FTP, DNS, TCP, UDP, and IP-based ports and protocols.
- Centralizes granular policy enforcement.

## Deployment Phase

The deployment phase includes initially setting up and integrating Zscaler solutions into an existing network infrastructure. During the deployment phase, you configure IPS Control to meet the needs of your infrastructure. The following sections discuss steps to deploy IPS Control.

### Prerequisites

One of the following Zscaler subscriptions is required:

- ZIA Transformation Edition and later.
- Advanced Firewall subscription.

### Deployment Steps

The following steps explain how to deploy IPS Control:

1. [Review Zscaler’s recommended IPS Control policy](https://help.zscaler.com/zia/recommended-ips-control-policy).
2. [Configure the IPS Control policy for your locations](https://help.zscaler.com/zia/configuring-ips-control-policy).
3. [Enable Firewall Control for your locations](https://help.zscaler.com/zia/enabling-firewall-locations). To learn more, see [About Firewall](https://help.zscaler.com/zia/about-firewall).
4. [Enable IPS Control for your locations](https://help.zscaler.com/zia/release-upgrade-summary-2019?applicable_category=zscaler.net&deployment_date=2019-09-20&id=1352466).

### Considerations

Review the following considerations:

- Detected threats for both web and non-web traffic display in [Firewall Insights > Logs](https://help.zscaler.com/zia/firewall-insights-logs-filters). Threats detected from web-only traffic also appear in the [Security Dashboard](https://help.zscaler.com/zia/about-dashboards#security).
- Firewall Control is enabled by default for Zscaler Client Connector Z-Tunnel 2.0 remote users.
- You can enable Firewall Control for [Zscaler Client Connector Z-Tunnel 1.0 and PAC remote users](https://help.zscaler.com/zia/about-advanced-settings#firewall-remote-users).
- IPS Control is enabled by default for Zscaler Client Connector Z-Tunnel 2.0 remote users.
- You can enable IPS Control by enabling [Firewall Control for Zscaler Client Connector Z-Tunnel 1.0 and PAC remote users](https://help.zscaler.com/zia/about-advanced-settings#firewall-remote-users).

## Operations Phase

This section describes common practices used to operate Zscaler solutions when integrated with your environment. You can monitor and tune IPS Control during the operations phase to meet your infrastructure needs.

### Common Troubleshooting Items

The following list describes common issues related to IPS Control operation:

- IPS Control blocks a commonly used website and labels it as phishing/botnet callback/malware/etc.: This might be a false positive detection. Submit a ticket with [Zscaler Support](https://help.zscaler.com/submit-ticket-links) so that the Security Research team can investigate and analyze this behavior.
- I can see in the logs that my SSH tunnel traffic is blocked by IPS Control, and I have configured an IPS Control rule to allow the traffic, but the traffic is still showing as blocked: [SSH tunneling is blocked, or allowed, for your entire tenant](https://help.zscaler.com/zia/configuring-advanced-threat-protection-policy#Unauthorized). It can’t be granularly allowed for specific users or groups.

## Deployment and Operations Checklist

Zscaler recommends downloading the [IPS Control Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/ips-control-deployment-and-operations-guide/IPS-Control-Deployment-Operations-Checklist.pdf) to help plan and implement IPS Control in ZIA: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/ips-control-deployment-and-operations-guide/IPS-Control-Deployment-Operations-Checklist.pdf)

## Additional Information

For more SaaS Security information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com/).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/local-breakouts-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1414406"} -->
## Local Breakouts Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/local-breakouts-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZIA Deployments & Operations > Local Breakouts Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits of and the steps necessary to integrate Zscaler Internet Access (ZIA) using Software-Defined Wide Area Networking (SD-WAN) local breakouts.

This guide describes the benefits of using Software-Defined Wide Area Networking (SD-WAN) local breakouts and the steps necessary for integrating Zscaler Internet Access (ZIA) into your local breakout security posture.

Backhauling over Multi-Protocol Label Switching (MPLS) to a centralized internet gateway via a hub-and-spoke architecture is inadequate when deploying cloud applications. Delivering a fast user experience and supporting cloud applications and services requires locally-routed internet traffic.

SD-WAN simplifies branch traffic routing in the branch, and makes it easy to establish local internet breakouts. Software-defined policies select the best path to route traffic connecting the branch to the internet, cloud applications, and the data center.

Deploying Zscaler with your new local breakouts secures and simplifies your local branch infrastructure by protecting user and application traffic, and reducing unneeded security hardware.

## Value of Deploying ZIA with SD-WAN Local Breakouts

Integrating ZIA with SD-WAN local breakout deployments provides the following benefits:

- Fast and secured user experience.
- Reduced costs.
- Simplified branch IT operations.
- Protected users, wherever they connect.

## Deployment Phase

The deployment phase includes the process to initially set up and integrate Zscaler solutions into an existing network infrastructure. During the deployment phase, you configure ZIA to work with your local breakout SD-WAN infrastructure. The following sections discuss steps to deploy ZIA with local breakouts using SD-WAN.

### Prerequisites

For local breakout deployment, verify and complete the following prerequisites:

1. Endpoint evaluation:
  1. Identify hardware (such as routers and firewalls) at the Public Service Edge.
  2. Identify SD-WAN capabilities.
2. Estimate traffic and consumption.
3. [Evaluate suitable traffic forwarding methods and validate the respective deployment prerequisites](https://help.zscaler.com/zia/choosing-traffic-forwarding-methods).
4. Evaluate security requirements.
5. Identify egress IPs.
6. (Optional) Identify clients that need different treatment than the regular users at the endpoint.
7. [Identify suitable data centers](https://config.zscaler.us/).

### Deployment Steps

The following steps explain how to integrate ZIA with local breakout SD-WAN:

1. [Provision static egress IPs](https://help.zscaler.com/zia/self-provisioning-static-ip-addresses) or review how to [configure a location without a static egress IP](https://help.zscaler.com/zia/configuring-location-without-static-public-ip-address).
2. [Create individual locations per local breakout](https://help.zscaler.com/zia/configuring-locations).
3. (Optional) [Create sub-locations](https://help.zscaler.com/zia/configuring-sub-locations).
4. Choose and implement the most [suitable traffic forwarding method](https://help.zscaler.com/zia/choosing-traffic-forwarding-methods) for your organization, [GRE](https://help.zscaler.com/zia/traffic-forwarding/gre), or [IPSec](https://help.zscaler.com/zia/traffic-forwarding/ipsec) tunneling.

### Considerations

Review the following considerations:

- Verify the cloud enforcement node status before choosing a suitable data center ([Zscaler Cloud Configuration Requirements](https://config.zscaler.us/)):
  - Some data centers have a regional surcharge applied to them. Contact your Zscaler Account team to confirm your organization can use surcharged data centers.
  - Data centers that have a regional surcharge or are not marked with Auto Geo Proximity Enabled won’t be chosen as the preferred data centers. Contact your Zscaler Account team for instructions on how to include all possible Zscaler data centers in your infrastructure.
- Check round-trip times between the egress and Zscaler’s Public Service Edges to identify the Zscaler data center with the least latency. Reach out to Zscaler Support for assistance.

## Operations Phase

This section describes common practices used to operate Zscaler solutions when integrated with your environment. During the operations phase, you can monitor and tune ZIA to meet your SD-WAN local breakout infrastructure needs.

### Prerequisites

Make sure to document all ZIA policies and traffic forwarding methods that are defined for entire branch locations to use as a reference to help your engineers or Zscaler contacts with troubleshooting processes.

### Common Troubleshooting Tips

The following list describes common issues related to deploying ZIA in SD-WAN local breakouts:

- Performance Issues: When faced with performance issues, you can analyze infrastructure performance using the [Zscaler Cloud Performance Test Tool](https://help.zscaler.com/zia/using-zscaler-cloud-performance-test-tool).
- Tunnel Flaps: If your tunnels start flapping, review the [GRE](https://help.zscaler.com/zia/configuring-gre-tunnels#step1)and [IPSec](https://help.zscaler.com/zia/configuring-ipsec-vpn-tunnel)configuration guidelines and ensure that you've properly provisioned the tunnel for your organization. Check [Zscaler Trust](https://trust.zscaler.us/zscalergov.net) to see if there is an associated outage with respect to the detected flapping behavior.
- Tunnel Failover Issues: Review [tunnel configuration guidelines](https://help.zscaler.com/zia/configuring-gre-tunnels#step1) to verify that everything is correctly configured.

## Deployment and Operations Checklist

Zscaler recommends downloading the [Local Breakouts Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/local-breakouts-deployment-and-operations-guide/Local-Breakouts-Deployment-Operations-Checklist.pdf) to help plan and implement your SD-WAN local breakout deployment for ZIA: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/local-breakouts-deployment-and-operations-guide/Local-Breakouts-Deployment-Operations-Checklist.pdf)

## Additional Information

For more SaaS Security information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com/).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/saas-security-api-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1433721"} -->
## SaaS Security Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/saas-security-api-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZIA Deployments & Operations > SaaS Security Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits of and the steps necessary to enable SaaS Security for Zscaler Internet Access (ZIA).

This guide describes the benefits of using Saas Security and the steps necessary for configuring Zscaler Internet Access (ZIA) to add SaaS Security to your security posture.

A cloud access security broker (CASB) is a visibility and control point that sits between cloud app users and cloud services. CASB secures cloud applications by monitoring traffic and user activity, automatically blocking threats and risky sharing, and enforcing security policies (such as authentication and alerting).

Some CASB benefits include:

- Data protection
- Threat protection services
- Data Loss Prevention (DLP)
- Malware and other threat prevention
- Shadow IT discovery and control
- Regulatory compliance

Zscaler offers both inline and out-of-band CASB capabilities. The inline (proxy-based) solution is in place through ZIA using [Cloud App Control](https://help.zscaler.com/zscaler-deployments-operations/cloud-app-control-deployment-and-operations-guide), [Threat Protection](https://help.zscaler.com/zscaler-deployments-operations/threat-protection-deployment-and-operations-guide), [Sandboxing](https://help.zscaler.com/zscaler-deployments-operations/advanced-sandbox-deployment-and-operations-guide), [DLP](https://help.zscaler.com/zscaler-deployments-operations/data-loss-prevention-deployment-and-operations-guide), and [URL Filtering](https://help.zscaler.com/zscaler-deployments-operations/url-filtering-deployment-and-operations-guide).

Zscaler’s Software as a Service (SaaS) Security API is an out-of-band CASB solution that prevents data exposure and ensures SaaS app compliance. It also scans data repositories and retains historical data for cloud applications.

To learn more, see [SaaS Security](https://help.zscaler.com/zia/policies/saas-security).

## Value of Deploying SaaS Security

SaaS Security provides the following benefits:

- Discovers your organization’s cloud app usage, creates reports on cloud spend, and performs risk assessments to help decide whether to block an app.
- Identifies your industry’s greatest risk factors so you can set stringent data protection policies to achieve and maintain compliance across your organization.
- Applies policies to provide [shadow IT control](https://help.zscaler.com/zia/about-saas-security-api-scan-configuration), [cloud DLP](https://help.zscaler.com/zia/about-saas-security-api-dlp), [Posture Control](https://help.zscaler.com/zia/configuring-saas-security-posture-control-policy), and [Malware Detection](https://help.zscaler.com/zia/configuring-saas-security-api-malware-detection-policy).
- Protects data at rest (i.e., out-of-band).

## Deployment Phase

The deployment phase includes initially setting up and integrating ZIA solutions into an existing network infrastructure. During the deployment phase, you configure SaaS Security to meet the needs of your infrastructure. The following sections discuss the steps to deploy SaaS Security in ZIA.

### Prerequisites

SaaS Security might require an additional license for your organization. Check with your Zscaler Account team to see if you have the necessary licensing requirements.

### Deployment Steps

The following sections cover only the out-of-band deployment instructions:

1. Identify applications that your organization wants to monitor.
2. [Add a Software as a Service (SaaS) Application Tenant](https://help.zscaler.com/zia/adding-saas-application-tenants).
3. (Optional) [Configure a SaaS Security DLP policy](https://help.zscaler.com/zia/configuring-saas-security-api-dlp-policy) to discover and protect sensitive data at rest in sanctioned SaaS applications:
  1. You must configure DLP first. To learn more, see [DLP Deployment and Operations Guide](https://help.zscaler.com/zscaler-deployments-operations/data-loss-prevention-deployment-and-operations-guide).
  2. (Optional) [Configure Zscaler Incident Receivers](https://help.zscaler.com/zia/configuring-zscaler-incident-receiver) to forward the transactions captured by this policy rule to an on-premises DLP incident receiver.
4. (Optional) [Configure a SaaS Security Malware Detection policy](https://help.zscaler.com/zia/configuring-saas-security-api-malware-detection-policy) to discover and prevent threats to data at rest in sanctioned SaaS applications.
5. [Configure SaaS Security scan schedules](https://help.zscaler.com/zia/configuring-saas-security-api-scan-schedules).
6. (Optional) [Modify default SaaS security activity alerts](https://help.zscaler.com/zia/editing-default-saas-security-activity-alerts) and [create custom alerts](https://help.zscaler.com/zia/adding-alerts).

### Considerations

Review the following considerations:

- You can configure [out-of-band CASB scanning exceptions](https://help.zscaler.com/zia/configuring-saas-security-api-scanning-exceptions) for sensitive locations or other reasons as needed.
- For DLP or Malware Detection rules, decide whether to only report incidents or to take immediate action (e.g., removing shareable links or quarantining malware) when a rule violation is detected.
- You can view additional info in the SaaS Security [Report](https://help.zscaler.com/zia/saas-security-report), [Insights](https://help.zscaler.com/zia/saas-security-insights), and [Logs](https://help.zscaler.com/zia/saas-security-insights-logs).
- To fully leverage SaaS Security capabilities, you might need to use an admin account with sufficient administrative privileges. To learn more about the requirements for individual SaaS applications, see [Adding SaaS Application Tenants](https://help.zscaler.com/zia/adding-saas-application-tenants).
- To learn more about adding additional object types for ServiceNow tenants, see [Adding Object Types for ServiceNow Tenants](https://help.zscaler.com/zia/adding-object-types-servicenow-tenants).

## Operations Phase

This section describes common practices used to operate Zscaler solutions when integrated with your environment. During the operations phase, you can monitor and tune SaaS Security to meet your infrastructure needs.

### Common Troubleshooting Items

If [Security Exceptions](https://help.zscaler.com/zia/configuring-saas-security-api-dlp-policy-exceptions) are not working as expected (e.g., excluded folders still appear in the logs), it’s possible that the username assigned in ZIA does not match the username of the scanned folder in the SaaS tenant. For example, the ZIA policy exception uses joe.blogs@xyz.com, while the SaaS tenant uses joe.blogs@abc.com. Usernames must be consistent across the policies and tenants for the exceptions to work.

To learn more, see [SaaS Application Validation Error Codes](https://help.zscaler.com/zia/saas-application-validation-error-codes).

## Deployment Checklist

Zscaler recommends downloading the [SaaS Security Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/saas-security-api-deployment-and-operations-guide/SaaS-Security-API-Deployment-Operations-Checklist.pdf) to help plan and implement SaaS Security: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/saas-security-api-deployment-and-operations-guide/SaaS-Security-API-Deployment-Operations-Checklist.pdf)

## Additional Information

For more SaaS Security information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com/).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/siem-zia-integration-deployment-and-operations-guide","lastmod":"2026-06-24T02:16Z","nid":"1417481"} -->
## SIEM and ZIA Integration Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/siem-zia-integration-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZIA Deployments & Operations > SIEM and ZIA Integration Deployment and Operations Guide
- Last modified: 2026-06-24T02:16Z
- Summary: Describes the benefits of and the steps necessary to integrate Zscaler Internet Access (ZIA) and a security information and event management (SIEM) system.

This guide describes the benefits of integrating your security information and event management (SIEM) into Zscaler, and the steps necessary for configuring Zscaler Internet Access (ZIA) to work with your SIEM. With this integration, Zscaler can send logs related to web, firewall, DNS, and tunnel traffic to the SIEM. To learn more, see [About Nanolog Streaming Service (NSS)](https://help.zscaler.com/zia/about-nanolog-streaming-service).

## Value of SIEM Integration with ZIA

Integrating a SIEM with ZIA provides the following benefits:

- Comprehensive real-time logging and visibility for all users and locations.
- Facilitates compliance and event correlation.

## Deployment Phase

The deployment phase includes initially setting up and integrating Zscaler solutions into an existing network infrastructure. During the deployment phase, you configure ZIA to integrate with your SIEM. The following sections discuss the steps to deploy ZIA and SIEM integration.

There are primarily two ways Zscaler can send logs to the SIEM tool:

1. Using Zscaler’s NSS. Zscaler uses [NSS](https://help.zscaler.com/zia/about-nanolog-streaming-service) to send the logs to the SIEM. The NSS is a virtual device that sits between the [Zscaler Cloud Nanolog cluster](https://help.zscaler.com/zia/about-zscaler-cloud-architecture) and the SIEM. NSS receives encrypted, compressed, and tokenized logs from the cloud and decrypts, uncompresses, and de-tokenizes them. Then NSS converts the logs into a format that the SIEM can process and sends it to the SIEM. [Image: Using Zscaler NSS]
2. Using cloud-to-cloud log streaming. For customers with a SIEM deployed in the cloud (e.g., Splunk Cloud), Zscaler can send the logs to the cloud provider via HTTPS API. [Image: Using Zscaler Cloud NSS]

### Prerequisites

NSS appliances are included with the ZIA Essentials Edition or later. You can optionally add the following:

- For cloud NSS:
  - ZIA Transformational Edition and later.
  - Cloud NSS subscription.
- For one-hour log recovery capability:
  - ZIA Transformational Edition and later.
  - NSS Log Recovery subscription.

Separate NSS appliances are required for web and firewall logging.

### Deployment Steps

The following steps explain how to integrate ZIA and a SIEM system:

1. Understand the SIEM infrastructure. Cloud-to-cloud logging is supported only if the SIEM infrastructure is in the cloud. Zscaler supports cloud-to-cloud logging only if the SIEM vendor supports API-based feeds.
2. Only applicable to NSS deployments:
  1. Identify the correct sizing for the NSS. Depending on the size of the organization and the volume of traffic received by Zscaler, the size of the virtual appliance can vary. Ensure that the virtual machine (VM) is built based on the traffic volume:
    - [Sizing guide for AWS](https://help.zscaler.com/zia/nss-deployment-guide-amazon-web-services#3).
    - [Sizing guide for Azure](https://help.zscaler.com/zia/nss-deployment-guide-microsoft-azure).
    - [Sizing guide for VMware](https://help.zscaler.com/zia/nss-deployment-guide-vmware-vsphere).
  2. Deploy the NSS. To learn more, see [Deploying NSS Virtual Appliances](https://help.zscaler.com/zia/deploying-nss-virtual-appliances).
  3. Configure the NSS feeds. To learn more, see [About NSS Feeds](https://help.zscaler.com/zia/about-nss-feeds).
  4. Configure firewalls to allow NSS traffic to pass through using the information on the Zscaler Cloud Configuration Requirements for your Zscaler Cloud (e.g., `https://config.zscaler.com/``<cloudname>``/nss`). To learn more, see [What is my cloud name for ZIA](https://help.zscaler.com/zia/what-my-cloud-name-zia)?
3. [Configure cloud NSS feeds](https://help.zscaler.com/zia/about-cloud-nss-feeds) (only applicable to cloud NSS deployments).

### Considerations

Review the following considerations:

- You cannot deploy NSS VMs in active-active mode, which means only one NSS server can connect to the cloud at a time. If you want fault-tolerant NSS deployment, deploy NSS in active-standby mode. In active-standby mode, the standby VM remains disconnected from the cloud and becomes active only when the other NSS is completely disconnected from the cloud. Two VMs using the same certificate to connect to the cloud can result in an inconsistent logging experience.
- For a fault-tolerant active-active solution, Zscaler recommends subscribing to two NSS services for web and firewall, and running one VM for web and one VM for firewall logs.
- Zscaler NSS buffers logs for up to one hour. If there is an interruption between the NSS and SIEM (e.g., if the SIEM is unavailable due to maintenance), NSS can buffer the logs for one hour. VM sizing is important to ensure accurate data.
- Each NSS server can support a maximum of 8 configured NSS feeds in different formats to different SIEM IP addresses on different ports.

## Operations Phase

This section describes common practices used to operate Zscaler solutions when integrated with your environment. You can monitor and tune ZIA and SIEM integration during the operations phase to meet your infrastructure needs.

### Prerequisites

For ZIA and SIEM operations, complete the following prerequisites:

- Add [NSS feeds for alerts](https://help.zscaler.com/zia/adding-tcp-nss-feeds-alerts) to ensure that the operations team is alerted when there is an issue on NSS.
- Ensure that the operations team can access the NSS server in case they need to troubleshoot issues.
- Ensure that the NSS IP addresses are included in every relevant standard operating procedure (SOP) document.

### Common Troubleshooting Items

The following list describes common issues related to ZIA and SIEM operation:

- [Troubleshooting commands for NSS servers](https://help.zscaler.com/zia/troubleshooting-deployed-nss-servers).
- [How to troubleshoot forbidden errors on Zscaler NSS](https://zscaler-support.force.com/customers/s/article/How-to-troubleshoot-issue-with-NSS-which-has-stopped-sending-logs-to-MCAS) that stop it from sending logs to Microsoft Cloud App Security (MCAS).
- [How to troubleshoot NSS cloud logs not getting received on Splunk Cloud](https://zscaler-support.force.com/customers/s/article/How-to-troubleshoot-the-issue-NSS-Cloud-logs-are-not-getting-received-on-Splunk-Cloud).

## Deployment and Operations Checklist

Zscaler recommends downloading the [ZIA SIEM Integration Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/siem-zia-integration-deployment-and-operations-guide/ZIA-SIEM-Integration-Deployment-Operations-Checklist.pdf) to help plan and implement ZIA and SIEM integration: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/siem-zia-integration-deployment-and-operations-guide/ZIA-SIEM-Integration-Deployment-Operations-Checklist.pdf)

## Additional Information

For more SIEM integration information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/siem-zpa-integration-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1420081"} -->
## SIEM and ZPA Integration Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/siem-zpa-integration-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZPA Deployments & Operations > SIEM and ZPA Integration Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits of and the steps necessary to integrate Zscaler Private Access (ZPA) and a security information and event management (SIEM) system.

This guide describes the benefits of integrating security information and event management (SIEM) with Zscaler Private Access (ZPA) and the steps necessary for configuring ZPA to add SIEM integration to your security posture.

By integrating a SIEM with ZPA, organizations can efficiently collect and analyze log data from ZPA in one place. The Zscaler Log Streaming Service (LSS) sends various data logs to a log receiver. By default, Zscaler retains User Activity, User Status, and App Connector log information for up to 14 days and audit log information for up to 6 months. Both are accessible via the ZPA Admin Portal.

You can access logs beyond these periods by using LSS integrated with a SIEM. To learn more, see [About the Log Streaming Service](https://help.zscaler.com/zpa/about-log-streaming-service). To learn how to integrate your SIEM with Zscaler Internet Access (ZIA), see [SIEM and ZIA Integration Deployment and Operations Guide](https://help.zscaler.com/zscaler-deployments-operations/siem-zia-integration-deployment-and-operations-guide).

## Value of SIEM Integration

Integrating a SIEM with ZPA provides the following benefits:

- Retains logged data for longer than 14 days.
- Retains audit logs for longer than 6 months.
- Uses SIEM analysis capabilities.

## Deployment Phase

The deployment phase initially sets up and integrates Zscaler solutions into an existing network infrastructure. During the deployment phase, you configure ZPA to integrate with your SIEM. The following sections discuss the steps to deploy ZPA and SIEM integration.

### Prerequisites

For SIEM integration with ZPA, review the following prerequisites:

- Set up App Connectors for your LSS configuration:
  - Using a single Connector Group for both user traffic and log streaming is only supported in proof of concept scenarios and should not be used for any production deployments. Zscaler recommends creating dedicated App Connectors for LSS log types.
  - You can use the standard App Connector image. To learn more, see [App Connector Deployment Prerequisites](https://help.zscaler.com/zpa/connector-deployment-prerequisites).
- (Optional) Enable Transport Layer Security (TLS) encryption between the log receiver and the App Connector when [configuring a log receiver](https://help.zscaler.com/zpa/configuring-log-receiver). You should use TLS encryption when the App Connector sends logs across an untrusted network (e.g., when the log receiver is in a public cloud environment):
  - It requires a client certificate for mutual TLS encryption that uses a public root Certificate Authority (CA).
  - Validate the chain of trust to the App Connector’s enrollment certificate. One way is to add the App Connector’s enrollment certificate to the log receiver’s certificate trust store.

### Deployment Steps

The following steps explain how to integrate ZPA and a SIEM system:

1. [Create App Connectors](https://help.zscaler.com/zpa/connector-deployment-prerequisites) dedicated to log streaming.
2. [Add a log receiver](https://help.zscaler.com/zpa/configuring-log-receiver) and assign the [App Connector Group](https://help.zscaler.com/zpa/about-connector-groups).
3. (Optional) [Configure mutual TLS encryption](https://help.zscaler.com/zpa/configuring-log-receiver) between the log receiver and the App Connector.
4. Select which [log type](https://help.zscaler.com/zpa/configuring-log-receiver#Step2) to forward.
5. Add a new log receiver configuration for each log you want to forward.
6. [Verify the configuration](https://help.zscaler.com/zpa/configuring-log-receiver#Step3Review) and check if logs are received.

### Considerations

Review the following considerations:

- In production scenarios, dedicate different App Connector Groups for log streaming and user traffic.
- The LSS won’t transmit any log data generated during a connection loss between ZPA and the App Connectors. When the connection is restored, the LSS can retransmit the last 15 minutes of the log data, but delivery isn’t guaranteed. The LSS does not transmit any log data generated during a loss of connection between the App Connector and the SIEM, except for [Audit Log data](https://help.zscaler.com/zpa/about-audit-logs).
- Zscaler recommends deploying App Connectors in pairs to ensure continuous availability during software upgrades.

## Operations Phase

This section describes common practices used to operate Zscaler solutions when integrated with your environment. You can monitor and tune the ZPA SIEM integration during the operations phase to meet your infrastructure needs.

### Common Troubleshooting Items

The following list describes common issues related to ZPA and SIEM operation:

- App Connectors health status check: You can use the [App Connectors page and health dashboard](https://help.zscaler.com/zpa/monitoring-connector-performance) to verify the health status because the log collector acts as an App Connector.
- If the SIEM server is not receiving logs from LSS:
  - Verify the configuration in the ZPA Admin Portal based on the steps in this guide.
  - Verify if the LSS is sending the logs by checking the [diagnostic logs](https://help.zscaler.com/zpa/dashboard-diagnostics).
  - Check if [LSS encryption in the log receiver settings](https://help.zscaler.com/zpa/configuring-log-receiver) uses TLS encryption on traffic between the log streaming service components. The receiving component might not trust the certificate presented for mutual TLS encryption. Disable TLS encryption temporarily and test whether you receive logs in SIEM to validate.
  - Follow Zscaler's general [App Connector Troubleshooting](https://help.zscaler.com/zpa/troubleshooting-app-connectors) guidelines.

## Deployment Checklist

Zscaler recommends downloading the [ZPA SIEM Integration Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zpa-deployments-operations/siem-zpa-integration-deployment-and-operations-guide/ZPA-SIEM-Integration-Deployment-Operations-Checklist.pdf) to help plan and implement ZPA and SIEM integration: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zpa-deployments-operations/siem-zpa-integration-deployment-and-operations-guide/ZPA-SIEM-Integration-Deployment-Operations-Checklist.pdf)

## Additional Information

For more SIEM integration information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com/).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/ssl-inspection-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1417371"} -->
## SSL Inspection Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/ssl-inspection-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZIA Deployments & Operations > SSL Inspection Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits of and the steps necessary to enable Zscaler Internet Access (ZIA) SSL Inspection.

This guide describes the benefits of using SSL inspection and the steps necessary for configuring Zscaler Internet Access (ZIA) to add SSL inspection to your security posture.

Much of the data traveling via the internet is confidential and sensitive. To protect this data in transport across the internet, browsers and cloud apps use encryption.

SSL encryption protects this data from unauthorized access while in transit between locations over the internet. However, malicious traffic can also hide in SSL encryption. SSL inspection decrypts SSL-encrypted data in transit and checks it for malicious traffic.

Zscaler ThreatLabZ observed a more than 400 percent increase in phishing attacks delivered over the SSL channel in 2018 compared to 2017. Decrypting SSL traffic is an important aspect of an organization's security, and most companies should inspect as much of their SSL traffic as possible.

To learn more, see [About SSL Inspection](https://help.zscaler.com/zia/about-ssl-inspection).

## Value of Deploying SSL Inspection

Using SSL Inspection provides the following benefits:

- Visibility into traffic that isn’t scannable by dedicated engines.
- Prevents data breaches by finding hidden malware and stopping hackers from sneaking past defenses.
- Identifies what data employees are sending outside the organization, intentionally or accidentally, and responds accordingly.
- Meets regulatory compliance requirements by ensuring employees aren’t putting confidential data at risk.
- Supports a multilayered defense strategy that keeps the entire organization secure.

## Deployment Phase

The deployment phase includes initially setting up and integrating Zscaler solutions into an existing network infrastructure. During the deployment phase, configure ZIA SSL Inspection to meet the needs of your infrastructure. The following sections discuss steps to deploy ZIA SSL Inspection.

### Deployment Steps

The following steps explain how to deploy ZIA SSL Inspection:

1. [Decide which certificate to use](https://help.zscaler.com/zia/choosing-ca-certificate-ssl-inspection). If you use the default Zscaler intermediate certificate, ensure all clients have installed the Zscaler root certificate. Failure to install the Zscaler root certificate results in warnings from browsers and applications or the inability to inspect the traffic properly.
2. If using Firefox, make sure to update its root certificate store.
3. Follow the [SSL deployment best practices](https://help.zscaler.com/zia/deploying-ssl-inspection) and start small. Begin SSL Inspection in a test lab or a small office for a subset of users and applications.
4. When enlarging the user base, issue the proper communications as dictated by the local laws concerning privacy. You can never inspect some traffic due to regulations (e.g., personal medical data and financial services). However, depending on the industry and the risks, you might decide to apply inspection to commonly skipped categories.
5. Remember that mobile applications often use [certificate pinning](https://help.zscaler.com/zia/deploying-ssl-inspection), which prevents SSL Inspection from working correctly. Instead of inspecting data in transit, you might consider leveraging SaaS Security functionality to inspect data at rest.
6. [Be aware of the different scenarios](https://help.zscaler.com/zia/deployment-scenarios-ssl-inspection) related to the different types of traffic forwarding. Are you going to apply SSL Inspection to known locations, remote users, or both?
7. Remote users might leverage different types of traffic forwarding, although the Zscaler Client Connector is recommended.
8. [Test a small list of websites and applications](https://help.zscaler.com/zia/best-practices-testing-and-rolling-out-ssl-inspection), and then proceed with the entire URL categories you want to inspect.

### Considerations

Review the following considerations:

- Make sure you’re not breaching any laws or contractual agreements concerning users' privacy.
- Consider leveraging SaaS Security-type control for data-at-rest instead of (or combined with) SSL Inspection.
- Prepare a list of critical business applications and add them to your [SSL bypass list](https://help.zscaler.com/zia/configuring-ssl-inspection-policy) before deploying SSL Inspection.
- Consider building a Non_SSL_inspection location for the server sub-location.

## Operations Phase

This section describes common practices used to operate Zscaler solutions when integrated with your environment. You can monitor and tune ZIA SSL Inspection during the operations phase to meet your infrastructure needs.

### Prerequisites

For SSL Inspection operation, complete the following prerequisites:

- Make sure all clients have the proper root certificates in the correct stores and remember that web applications and their desktop counterparts might behave differently (the latter are more prone to certificate pinning). The same is valid for mobile apps.
- Depending on the type of deployment of ZIA services, you might have to configure SSL Inspection for Zscaler Client Connector.
- Test corner cases as much as possible, including:
  - Untrusted server certificates.
  - Undecryptable traffic.
- Make sure your business-critical applications are not reliant on the cases mentioned earlier.

### Common Troubleshooting Items

The following list describes common issues related to deploying SSL Inspection:

- Non-browser applications are getting SSL errors: Check if the Zscaler Root certificate is installed in the proper repository for these applications.
- The app or website behaves differently in Android than iOS and desktop OS: Each OS can behave differently, especially when dealing with SSL certificates. Inside each OS, different browsers can exhibit different behaviors. Ensure as much consistency as possible among different platforms: If necessary, disable SSL Inspection for a particular site and move to SaaS Security-type protection.
- The app is not working properly. Check if the app uses certificate pinning: [Many well-known applications use pinning](https://help.zscaler.com/zia/certificate-pinning-and-ssl-inspection). If certificate pinning is in place, exempt the applications from [SSL Inspection](https://help.zscaler.com/zia/about-ssl-inspection) by removing the cloud application from SSL Inspection or the individual domains from SSL Inspection, using the [custom URL categories](https://help.zscaler.com/zia/adding-custom-url-categories).
- Unable to configure SSL Inspection on the ZIA Admin Portal: Make sure you have the proper license for SSL inspection and that your [admin account has the correct permissions and access](https://help.zscaler.com/zia/configuring-role-based-administration).
- A legitimate site is blocked: Make sure you don't configure policies that are too restrictive regarding the [minimum Transport Layer Security (TLS) version](https://help.zscaler.com/zia/configuring-ssl-inspection-policy).
- My legal department is concerned about Zscaler handling its data: Web transaction content inspection takes place in memory and is never written to disk. Refer to your Zscaler Account team for further details.
- I’m not willing to upload my own intermediate certificate to Zscaler nodes: Zscaler supports private keys in cloud hardware security modules (HSM).

## Deployment and Operations Checklist

Zscaler recommends downloading the [SSL Inspection Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/ssl-inspection-deployment-and-operations-guide/SSL-Inspection-Deployment-Operations-Checklist.pdf) to help plan and implement ZIA SSL Inspection: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/ssl-inspection-deployment-and-operations-guide/SSL-Inspection-Deployment-Operations-Checklist.pdf)

## Additional Information

For more SSL Inspection information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/threat-protection-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1420101"} -->
## Threat Protection Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/threat-protection-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZIA Deployments & Operations > Threat Protection Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits of and the steps necessary to enable Threat Protection for Zscaler Internet Access (ZIA).

This guide describes the benefits of using Threat Protection and the steps necessary for configuring Zscaler Internet Access (ZIA) to add Threat Protection to your security posture.

Today, web pages don't just contain plain text nested inside HTML tags. Instead, they use Java applets, Adobe Flash videos, ActiveX, and other objects designed to run programs. Hackers routinely embed malicious scripts and applications on illegitimate or hacked websites.

The Zscaler service uses an industry-leading antivirus (AV) vendor for signature-based detection and protection to provide comprehensive web security. ZIA identifies various objects and scripts, and prevents the end user browser from downloading them.

To learn more, see [Advanced Threat Protection (ATP)](https://help.zscaler.com/zia/policies/advanced-threat-protection), [Malware Protection](https://help.zscaler.com/zia/policies/malware-protection), and [Mobile Security](https://help.zscaler.com/zia/policies/mobile-security).

## Value of Deploying Threat Protection

Using Threat Protection provides the following benefits:

- Improves your organization's security posture by protecting against zero-day threats and unknown malware.
- Analyzes network traffic to monitor your network for security and operational anomalies.
- Leverages threat data from the world’s largest security cloud and shares threat protection cloud-wide in real time.

## Deployment Phase

The deployment phase initially sets up and integrates ZIA solutions into an existing network infrastructure. During the deployment phase, configure Threat Protection in ZIA to meet the needs of your infrastructure. The following sections discuss steps to deploy Threat Protection.

### Prerequisites

For Threat Protection deployment, verify and complete the following prerequisites:

- User traffic must traverse the Zscaler infrastructure to be evaluated by Threat Protection policies. To learn more, see the [Zscaler Client Connector Deployment and Operations Guide](https://help.zscaler.com/zscaler-deployments-operations/zscaler-client-connector-deployment-and-operations-guide) and the [Local Breakouts Deployment and Operations Guide](https://help.zscaler.com/zscaler-deployments-operations/local-breakouts-deployment-and-operations-guide).
- Identify your organization’s risk tolerances, and use them to guide how you configure Threat Protection.

### Deployment Steps

The following steps explain how to deploy Threat Protection:

1. [Configure Malware Protection policies](https://help.zscaler.com/zia/configuring-malware-protection-policy) in the ZIA Admin Portal, using the Zscaler [recommended Malware Protection policy](https://help.zscaler.com/zia/recommended-malware-protection-policy) as a guide.
2. [Configure ATP policies](https://help.zscaler.com/zia/configuring-advanced-threat-protection-policy) in the ZIA Admin Portal, using the Zscaler [recommended ATP policy](https://help.zscaler.com/zia/recommended-advanced-threat-protection-policy) as a guide.
3. [Configure Mobile Malware Protection policies](https://help.zscaler.com/zia/about-mobile-malware-protection) in the ZIA Admin Portal, using the Zscaler [recommended Mobile Malware Protection policy](https://help.zscaler.com/zia/recommended-mobile-malware-protection-policy) as a guide.
4. [Configure Mobile App Store Control policies](https://help.zscaler.com/zia/about-mobile-app-store-control) if you want to restrict access to sites from which users can download apps for their mobile devices.
5. (Optional) Configure the security exceptions in the ZIA Admin Portal, which is done separately for [Malware Protection](https://help.zscaler.com/zia/configuring-security-exceptions-malware-protection-policy) and [ATP](https://help.zscaler.com/zia/configuring-security-exceptions-advanced-threat-protection-policy).

### Considerations

Review the following considerations:

- Malware Protection, ATP, and Mobile Malware Protection work separately from the URL and Cloud App policies, and are handled by separate Zscaler features. To learn more, see [About Policy Enforcement](https://help.zscaler.com/zia/about-policy-enforcement).
- Zscaler recommends [raising a support ticket with Zscaler](https://help.zscaler.com/submit-ticket-links) if you think that Zscaler is detecting a false-positive. Reduce security exceptions to an absolute minimum.
- ATP security exceptions also apply to Malware Protection, but not vice versa. ATP exceptions also cover Sandbox policies.
- To allow a specific destination across all Zscaler, you must create exceptions in ATP, URL Filtering, and File Type Control policies.
- When troubleshooting destination accessibility issues, remember that threat protection policies are global policies configured for all users.

## Operations Phase

This section describes common practices used to operate ZIA solutions when integrated with your environment. You can monitor and tune Threat Protection during operations to meet your infrastructure needs.

### Prerequisites

For Threat Protection operation, keep a list of known security exceptions (such as partner websites and currently running or scheduled phishing campaigns) available to security administrators.

### Common Troubleshooting Items

If users cannot download a file or load a website due to threat protection, check the Web and Firewall [Insights logs](https://help.zscaler.com/zia/about-insights-logs) to determine which threat protection prohibits users from accessing the desired content. Carefully consider whether placing the file or content on the allowlist aligns with your security compliance.

## Deployment and Operations Checklist

Zscaler recommends downloading the [Threat Protection Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/threat-protection-deployment-and-operations-guide/Threat-Protection-Deployment-Operations-Checklist.pdf) to help plan and implement Threat Protection in ZIA: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/threat-protection-deployment-and-operations-guide/Threat-Protection-Deployment-Operations-Checklist.pdf)

## Additional Information

For more Threat Protection information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/url-filtering-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1414586"} -->
## URL Filtering Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/url-filtering-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZIA Deployments & Operations > URL Filtering Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits of and the steps necessary to enable Zscaler Internet Access (ZIA) URL filtering.

This guide describes the benefits of using URL filtering and the steps necessary for configuring Zscaler Internet Access (ZIA) to add URL filtering to your security posture. To learn more, see [About URL Filtering](https://help.zscaler.com/zia/about-url-filtering).

You can define a filtering policy that restricts or prevents access to websites based on criteria such as URL categories, users, groups, departments, locations, and time intervals.

## Value of Deploying URL Filtering

Using URL Filtering provides the following benefits:

- Limits exposure to certain types of web content.
- Controls access privileges for your users.
- Defines access to custom allowlists and denylists.

## Deployment Phase

The deployment phase includes the process of initially setting up and integrating Zscaler solutions into an existing network infrastructure. During the deployment phase, you configure ZIA URL filtering to meet the needs of your infrastructure. The following sections discuss steps to deploy ZIA URL filtering.

### Prerequisites

All ZIA editions include URL filtering features. To learn more, see [Zscaler Pricing & Plans](https://www.zscaler.com/pricing-and-plans).

### Deployment Steps

The following steps explain how to deploy URL Filtering:

1. Determine if ZIA should [cascade to URL filtering policies](https://help.zscaler.com/zia/about-advanced-settings#web-app-control) after it has already processed your [Cloud App Control policies](https://help.zscaler.com/zia/documentation-knowledgebase/policies/cloud-apps/cloud-app-control-policies).
2. Decide which destinations (i.e., single websites or whole URL categories) should receive restrictions through URL filtering policies.
3. Decide on a filtering concept:
  - Should the last default rule in your rule set block or allow access to web content? The Zscaler default policy allows access. To learn more about the default Zscaler policy and policy execution, see [Configuring the URL Filtering Policy](https://help.zscaler.com/zia/configuring-url-filtering-policy).
  - Which admins, and their [admin ranks](https://help.zscaler.com/zia/configuring-url-filtering-policy), are responsible for policy creation?
  - What is the [rule order](https://help.zscaler.com/zia/configuring-url-filtering-policy)?
  - What criteria limits access to web content? You can define individual sets of criterion per rule.
  - Are [custom URL categories](https://help.zscaler.com/zia/about-url-categories) needed for specific destinations (e.g., partner sites).
4. Determine which [advanced policy settings](https://help.zscaler.com/zia/configuring-advanced-url-policy-settings) to enable and disable.
5. [Configure your URL filtering policies](https://help.zscaler.com/zia/configuring-url-filtering-policy).

### Considerations

Review the following considerations:

- By default, the Cloud App Control policy takes precedence over the URL filtering policy. To learn more, see [Configuring the URL Filtering Policy](https://help.zscaler.com/zia/configuring-url-filtering-policy).
- Cloud App Control policies are better suited to control access to specific web applications (e.g., Meta, Google Drive, GitHub) compared to URL filtering rules.
- When routing Microsoft 365 traffic through the Zscaler cloud, consider enabling the [Microsoft One Click Options](https://help.zscaler.com/zia/about-microsoft-one-click-options) to apply policies to traffic that meets Microsoft’s recommendations automatically.
- ZIA might not enforce rules based on certain criteria if you don’t enable SSL inspection or use the Zscaler Client Connector.
- If you only want safe-for-work content served on supported search engines, enable [Enforce SafeSearch](https://help.zscaler.com/zia/configuring-advanced-url-policy-settings#Safe).

You must have SSL Inspection enabled in order to enable Enforce SafeSearch.

- URL filtering rules are applied on a first-match basis. To learn more, see [URL Filtering Policy](https://help.zscaler.com/zia/configuring-url-filtering-policy) and [Advanced URL Policy Settings](https://help.zscaler.com/zia/configuring-advanced-url-policy-settings).
- Consider how to treat miscellaneous URL categories. Blocking access to URLs in the miscellaneous category might impact your users' experience if they can’t access these sites until the URL is [categorized](https://sitereview.zscaler.com/).
- Consider enabling [AI/ML-based content categorization](https://help.zscaler.com/zia/configuring-advanced-url-policy-settings#Dynamic). AI/ML content categorization dynamically vets unknown websites by analyzing their content.
- Consider creating additional security policies (e.g., [Sandbox](https://help.zscaler.com/zia/configuring-sandbox-policy), [File Type Control](https://help.zscaler.com/zia/configuring-file-type-control-policy), [Firewall](https://help.zscaler.com/zia/configuring-firewall-policies)) even after limiting access through URL filtering rules.
- More specific [custom category](https://help.zscaler.com/zia/about-url-categories) entries always take precedence. URL filtering rules aren’t applied to a category with a wildcard if the destination is defined more specifically in another category.

For example, the first rule CUSTOM_C1 contains .example.com and the second rule CUSTOM_C2 contains www.example.com. When accessing www.example.com, the CUSTOM_C1 policy isn’t applied because CUSTOM_C2 is more specific (even though CUSTOM_C1 is higher in the rule order).

## Operations Phase

This section describes common practices used to operate Zscaler solutions when integrated with your environment. During the operations phase, you can monitor and tune ZIA URL filtering to meet your infrastructure needs.

### Prerequisites

For URL Filtering operation, complete the following prerequisites:

- Document if the[Allow Cascading to URL Filtering](https://help.zscaler.com/zia/about-advanced-settings) option is enabled to understand the rule evaluation flow better. This option is located under [Advanced Web App Control Options](https://help.zscaler.com/zia/about-advanced-settings#web-app-control).
- Make sure the operations team has access to the authorization concept.
- Add informative descriptions to your URL filtering rules.
- Agree upon standard operating procedures (SOPs) to add/delete/edit URL filtering rules.
- Agree upon SOPs to add/delete/edit custom URL categories used in your URL policies.

### Common Troubleshooting Tips

The following list describes common issues related to URL filtering operation:

- Rules with Request Method or Protocol criteria are not processed as expected: Check if SSL inspection is applied to these transactions. Zscaler might not determine certain criteria if sites are uninspected.
- Rule processing for a site is unexpected: The category check shows it as MISCELLANEOUS_OR_UNKNOWN, but other rules apply. Check if [AI/ML categorization](https://help.zscaler.com/zia/configuring-advanced-url-policy-settings) is enabled. This feature automatically assigns certain categories to uncategorized sites.
- Allowlist category rule is not applied to certain sites: Check [custom categories](https://help.zscaler.com/zia/adding-custom-url-categories) for more specific entries.
- Website only partially loads, so certain site elements are missing even though access should be allowed: Background elements can be loaded from other sites that could be blocked by your policies. Check the [headers](https://help.zscaler.com/zia/capturing-http-headers-google-chrome) for blocked content.
- Applications show TLS error messages: Try [turning off SSL inspection](https://help.zscaler.com/zia/deploying-ssl-inspection) for URLs used by this application, since certain applications use certificate pinning and do not function properly if they are not served the original certificate.
- Wrong category is shown on the blocked page: Check logs to see which category is logged for the transaction. Is the URL in question added to a custom category? If the site is added in the Retaining Parent category, check the [rule order](https://help.zscaler.com/zia/configuring-url-filtering-policy) to make sure that another rule does not block access to this URL first.

## Deployment and Operations Checklist

Zscaler recommends downloading the [URL Filtering Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/url-filtering-deployment-and-operations-guide/URL-Filtering-Deployment-Operations-Checklist.pdf) to help plan and implement ZIA URL filtering: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/url-filtering-deployment-and-operations-guide/URL-Filtering-Deployment-Operations-Checklist.pdf)

## Additional Information

For more URL Filtering information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/user-provisioning-and-authenticating-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1417946"} -->
## User Provisioning and Authentication Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/user-provisioning-and-authenticating-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZPA Deployments & Operations > User Provisioning and Authentication Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits of and the steps necessary to enable User Provisioning and Authentication in Zscaler Private Access (ZPA).

This guide describes the benefits of using User Provisioning and Authentication and the steps necessary for configuring Zscaler Private Access (ZPA) to add User Provisioning and Authentication to your security posture.

ZPA uses Zero Trust network access, which allows users to connect to only the specific applications they need. End users are never actually placed onto the destination network, removing any possibility of lateral movement of malicious software or actors on the network to attack or infect other resources.

This guide focuses on ZPA user management best practices. The ZPA Admin Portal can provision users directly from your identity provider (IdP) using a System for Cross-domain Identity Management (SCIM) or Security Assertion Markup Language (SAML).

To learn more, see [Authentication](https://help.zscaler.com/zpa/authentication).

## Value of Deploying User Provisioning and Authentication

User Provisioning and Authentication provides the following benefits:

- Granular user- and group-based Zero Trust network access policy control.
- The ability to automatically manage application access via ZPA from your IdP.

## Deployment Phase

The deployment phase initially sets up and integrates ZPA solutions into an existing network infrastructure. During the deployment phase, you configure User Provisioning and Authentication to meet the needs of your infrastructure. The following sections discuss steps to deploy User Provisioning and Authentication.

### Prerequisites

For User Provisioning and Authentication deployment, complete the following prerequisites:

- Closely coordinate with the ZPA Admin Portal and IdP management team when deploying User Provisioning and Authentication.
- Configure the authentication domains to allow users to log in.
- Configure the service provider information in the IdP using the service provider (SP) certificates, SP URL, and SP metadata.
- Gather the IdP certificates to authenticate users.
- You can use SAML auto-provisioning to provision users and associated attributes into ZPA every time a new user authenticates into Zscaler Client Connector.
- You can use SCIM to directly push the users and attributes from the IdP to the ZPA user database.
- Zscaler supports only SCIM version 2.0 and SAML version 2.0 and later.

### Deployment Steps

The following steps explain how to deploy User Provisioning and Authentication:

1. Contact Zscaler Support to add authentication domains by submitting a provisioning ticket with [Zscaler Support](https://help.zscaler.com/submit-ticket-links).
2. Zscaler can check for authentication domains and other settings from the ZPA Admin Portal on the [Settings](https://help.zscaler.com/zpa/about-authSettings) page.
3. Single Sign-On (SSO) deployment using SAML:
  - ZPA supports SSO via SAML so that your remote users can access enterprise applications without having to log in to ZPA separately. To learn more about configuring SSO for ZPA, see [About IdP Configuration](https://help.zscaler.com/zpa/about-idps).
  - Configure the [IdP in the ZPA Admin Portal](https://help.zscaler.com/zpa/about-idp/new) and download the SP information.

To learn more, see [Authentication](https://help.zscaler.com/zpa/authentication).

1. SCIM deployment:
  - Setting up SCIM requires an IdP partnered with Zscaler and [ZPA Admin Portal configuration](https://help.zscaler.com/zpa/enabling-scim-identity-management).
  - The following IdP partners work with Zscaler:
    - [SCIM Configuration Guide for Microsoft Azure AD](https://help.zscaler.com/zpa/scim-configuration-guide-microsoft-azure-ad)
    - [SCIM Configuration Guide for Okta](https://help.zscaler.com/zpa/scim-configuration-guide-okta)
    - [PingFederate integration with ZPA](https://docs.pingidentity.com/bundle/integrations/page/exl1587060854790.html)
2. Verify the configuration. When deployed, [check the user status](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector) in the Zscaler Client Connector to see if the authentication was successful.

### Considerations

Review the following considerations:

- Review the [IdP Configuration Best Practices](https://help.zscaler.com/zpa/idp-configuration-best-practices) before deployment.
- Review the [Ranges & Limitations](https://help.zscaler.com/zpa/ranges-limitations) for Authentication configuration.
- Before choosing between SAML or SCIM, review the application information and best practices. To learn more, see [About SCIM](https://help.zscaler.com/zpa/about-scim) and [Understanding SAML](https://help.zscaler.com/zia/understanding-saml).
- Take care if you decide to switch from SAML to SCIM. When you enable SCIM for the first time (or toggle the SCIM provisioning option), the SCIM database is synchronized. Database synchronization might impact authentication until all the users are synced from the customer IdP to the Zscaler database.

## Operations Phase

This section describes standard practices used to operate Zscaler solutions when integrated with your environment. You can monitor and tune User Provisioning and Authentication in ZPA during operations to meet your infrastructure needs.

To configure your SAML attributes, see the following articles:

- [About SAML Attributes](https://help.zscaler.com/zpa/about-samlattributes).
- [Manually Adding SAML attributes](https://help.zscaler.com/zpa/about-samlattribute/new).
- [Importing SAML Attributes](https://help.zscaler.com/zpa/about-importSamlAttributes). To learn more, see [SAML Attributes](https://help.zscaler.com/zpa/documentation-knowledgebase/authentication/saml-attributes).
- Zscaler supports SCIM APIs and provides [SCIM API Examples](https://help.zscaler.com/zpa/scim-api-examples).
- To learn more about the list of users and groups synced using SCIM, see [About SCIM Users and Groups](https://help.zscaler.com/zpa/about-scim-users-and-groups).

### Common Troubleshooting Items

The following list describes common issues related to User Provisioning and Authentication operation:

- Authentication fails: Check the user status logs for any failures in authentication from the [diagnostics](https://help.zscaler.com/zpa/about-user-authentication-diagnostics).
- Authentication errors on Zscaler Client Connector: Review the list of [Zscaler Client Connector error codes](https://help.zscaler.com/client-connector/zscaler-client-connector-zpa-authentication-errors) with reasons for the error and resolution details.
- Users might encounter a Zscaler Client Connector connection error when enabling SCIM sync with Okta. Okta does not sync users to ZPA in the Okta IdP before you enable SCIM. As a result, users do not initially appear in the SCIM user database when SCIM is enabled in ZPA. To learn more, see [How to avoid ZPA Connector Error when enabling SCIM sync with Okta](https://community.zscaler.com/s/question/0D54u00009evlQxCAI/how-to-avoid-zpa-connection-error-when-enabling-scim-sync-with-okta).
- If SCIM sync fails, review the information [About SCIM Sync Logs](https://help.zscaler.com/zpa/about-scim-sync-logs) in the ZPA Admin Portal. If you do not see the logs, look for errors in the SCIM provisioning logs from the configured IdP.

## Deployment Checklist

Zscaler recommends downloading the [User Provisioning and Authentication Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zpa-deployments-operations/user-provisioning-and-authenticating-deployment-and-operations-guide/User-Provisioning-Authentication-Deployment-Operations-Checklist.pdf) to help plan and implement User Provisioning and Authentication: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zpa-deployments-operations/user-provisioning-and-authenticating-deployment-and-operations-guide/User-Provisioning-Authentication-Deployment-Operations-Checklist.pdf)

## Additional Information

For more User Provisioning and Authentication information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](https://community.zscaler.com/).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/zia-disaster-recovery-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1451331"} -->
## Disaster Recovery Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/zia-disaster-recovery-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZIA Deployments & Operations > Disaster Recovery Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits of and the steps necessary to enable Zscaler Internet Access (ZIA) disaster recovery.

This deployment and operations guide describes the benefits of using disaster recovery and the steps necessary for configuring Zscaler Internet Access (ZIA) to add disaster recovery to your security posture.

ZIA Disaster Recovery ensures business continuity when an event impacts the global Zscaler cloud infrastructure. Disaster recovery provides an organization's users access to critical applications by ensuring access even if the Zscaler cloud isn’t accessible.

To learn more, see [About Disaster Recovery](https://help.zscaler.com/zia/about-disaster-recovery) and [Zscaler Resilience](https://www.zscaler.com/zscaler-resilience).

## Value of Deploying Disaster Recovery

ZIA disaster recovery provides the following benefits:

- Business continuity with uninterrupted security in case of:
  - Brownout
  - Blackout
  - Catastrophic failure
- Avoids costly business interruptions or loss of productivity due to lack of access to critical apps.

## Deployment Phase

The deployment phase includes initially setting up and integrating Zscaler solutions into an existing network infrastructure. During the deployment phase, you configure disaster recovery to meet the needs of your infrastructure. The deployment phase includes preparation steps needed to enable disaster recovery. The following sections discuss steps to deploy disaster recovery in ZIA.

### Prerequisites

ZIA disaster recovery might require an additional license for your organization. Check with your Zscaler Account team to verify the necessary licensing requirements.

The following prerequisites are required for ZIA disaster recovery on the applicable devices:

- ZIA disaster recovery is available for certain [Zscaler Client Connector versions](https://help.zscaler.com/zscaler-client-connector/what-is-zscaler-client-connector):
  - Zscaler Client Connector version 4.0 or later for Windows
  - Zscaler Client Connector version 3.71.38 or later for macOS
- Disaster recovery must be enabled for your organization. Check with your Zscaler Account team to enable disaster recovery.
- Disaster recovery requires a modifiable, customer-owned public DNS record.

### Deployment Steps

The following sections cover deployment instructions for ZIA disaster recovery:

1. [Create a DNS TXT Record](https://help.zscaler.com/zpa/creating-dns-txt-records). Modify the record format values according to your organization’s needs. To learn more, see [About the Zscaler DNS Record Generator](https://help.zscaler.com/zpa/about-zscaler-dns-record-generator).
2. Enable [Disaster Recovery](https://help.zscaler.com/zia/about-disaster-recovery) on an [App Profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles):
  1. Specify the **Activation Domain Name** used for the DNS text (TXT) record.
  2. (Optional) [Upload a public key](https://help.zscaler.com/zia/about-disaster-recovery) in order to create a signed DNS record.
  3. Specify a traffic forwarding action in case of disaster recovery:
    1. **Send Traffic Direct**: All traffic bypasses Zscaler Client Connector, giving the user access to all applications through direct internet access.
    2. **Disable Internet Access**: All traffic is dropped at the endpoint and users do not have access.
    3. **Pre-selected Destinations**: The admin selects to block or allow access to specific URLs using a custom PAC file:
      - (Optional) Enable **Use Zscaler Pre-selected Destinations**.
      - (Optional) Enable **Use Custom Destinations**.
  4. (Optional) Enable **Part of ZIA Disaster Recovery Test Group** if this profile is for testing purposes. To learn more, see [Configuring Disaster Recovery Test Mode](https://help.zscaler.com/zpa/configuring-disaster-recovery#:~:text=Configuring%20Disaster%20Recovery%20Test%20Mode).

### Considerations

Review the following considerations:

- You can find Zscaler pre-selected destinations [here](https://dll7xpq8c5ev0.cloudfront.net/drdb.txt).
- You can define a combination of Zscaler pre-selected destinations and custom destinations for access in a PAC file. This is not a mutually exclusive choice.
- Custom destination URLs take precedence over Zscaler pre-selected destinations. You can block certain Zscaler pre-selected destinations by placing them in the custom destinations PAC file and forwarding the traffic to destination Block.
- An example of the custom PAC file syntax is shown in [About Disaster Recovery](https://help.zscaler.com/zia/about-disaster-recovery).
- You cannot use ZIA Virtual Service Edges or Private Service Edges in conjunction with disaster recovery.
- You cannot use any other traffic forwarding decisions than allowing direct access or blocking access to destinations in conjunction with disaster recovery.
- Zscaler recommends testing disaster recovery through the disaster recovery test mode with a handful of users prior to deployment in production environments.
- End users receive an HTTP 403 error if they try to open any blocked page when disaster recovery is on.
- Zscaler Client Connector shows Service Status `Safe Mode` when disaster recovery mode is on.
- When disaster recovery is triggered for a client, that client can only access destinations that are specified as accessible during disaster recovery. Limited access is enforced until disaster recovery is disabled for the client. Even when ZIA services are restored, client machines do not automatically reconnect to the Zscaler cloud until disaster recovery is disabled.

## Operations Phase

This section describes common practices used to operate Zscaler solutions when integrated with your environment. You can enable, monitor, and tune ZIA disaster recovery to meet your infrastructure needs.

To enable disaster recovery, customers must adjust the DNS TXT record. At minimum:

- Indicate the DNS record version with `v=1`.
- Enable disaster recovery with`b=on`.
- Enable disaster recovery for ZIA with `k=zia` (and potentially ZPA with`k=all`).

To learn more, see [About the Zscaler DNS Record Generator](https://help.zscaler.com/zpa/about-zscaler-dns-record-generator).

Choose the lowest time to live (TTL) possible in the DNS TXT record for swift DNS update propagation downstream (Zscaler recommends 30 seconds).

For testing, after completing the deployment steps described previously, adjust the DNS TXT record as follows:

- v=1
- b=test
- k=zia (for ZIA) or k=all (for ZIA and ZPA)

For more information see [Configuring Disaster Recovery Test Mode](https://help.zscaler.com/zpa/configuring-disaster-recovery#:~:text=Configuring%20Disaster%20Recovery%20Test%20Mode).

### Common Troubleshooting Tips

- During a catastrophic failure, assume that the ZIA Admin Portal is inaccessible and configuration changes or client enrollments are not possible.
- Zscaler Client Connector checks the DNS TXT record every 200 seconds.
- Take DNS propagation times into account when waiting for Safe Mode to trigger in the Zscaler Client Connector after updating the DNS TXT record.

## Deployment and Operations Checklist

Zscaler recommends downloading the [ZIA Disaster Recovery Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/zia-disaster-recovery-deployment-and-operations-guide/ZIA-Disaster-Recovery-Deployment-Operations-Checklist.pdf) to help plan and implement ZIA disaster recovery: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/zia-disaster-recovery-deployment-and-operations-guide/ZIA-Disaster-Recovery-Deployment-Operations-Checklist.pdf)

## Additional Information

For more SaaS Security information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com/).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/zia-policy-leading-practices-guide","lastmod":"2025-08-06T23:32Z","nid":"1456801"} -->
## ZIA Policy Leading Practices Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/zia-policy-leading-practices-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZIA Deployments & Operations > ZIA Policy Leading Practices Guide
- Last modified: 2025-08-06T23:32Z
- Summary: The ZIA Policies Leading Practices Guide provides a set of best practices for configuring and deploying Zscaler Internet Access (ZIA) policies in an organization's environment.

Zscaler Internet Access (ZIA) is a security and traffic forwarding service running inline to your organization’s traffic. ZIA controls your traffic using simple-to-configure granular policies.

This document explores the policy configuration areas available to administrators. Understanding how to configure ZIA policies is crucial to securing your environment, maintaining user experience, and supporting productivity.

URL Filtering and Cloud App Control limit exposure to certain types of web content by allowing or restricting user access. A well-configured policy restricts or permits web content access without disrupting the end users or their experience.

To learn more, see [Understanding Policy Enforcement](https://help.zscaler.com/zia/about-policy-enforcement).

## Overview

The following sections provide an overview of the important concepts in configuring Zscaler security policies.

### Zscaler Service Modules

ZIA uses full-featured inline proxies called [ZIA Public Service Edges](https://help.zscaler.com/zia/about-zscaler-enforcement-nodes) to inspect and enforce policies on traffic entering or leaving your organization. ZIA Public Service Edges feature Single-Scan Multi-Action (SSMA) technology that handles the traffic inspection and policy execution in the ZIA Public Service Edge's web and firewall modules.

SSMA allows inspection engines to scan all content in a single pass. Packets are placed in shared memory in highly optimized custom servers. All CPUs on a ZIA Public Service Edge access those packets at the same time. With dedicated CPUs for each function, all engines can inspect the packets simultaneously instead of using a chained model of physical or virtual appliances (where each security service processes packets in turn, adding incremental latency). SSMA technology applies the Zscaler service policies based on a variety of security engines with minimal latency.

After the SSMA inspection process is complete, the ZIA Public Service Edge executes policies using a specific precedence order. Each ZIA Public Service Edge has two main modules for applying policies: a web module and a firewall module.

At a high level, this is how traffic flows through the modules:

- Outbound web traffic. The ZIA Public Service Edge sends outbound internet web traffic from your organization to the firewall module for policy evaluation. If the traffic violates a firewall policy, it blocks the transaction. If the traffic does not violate any firewall policies, it sends the traffic to the web module for policy evaluation. In the web module, if the traffic violates a web policy, it blocks the transaction. If the traffic does not violate any web policies, the transaction is allowed to the internet.
- Outbound non-web traffic. The ZIA Public Service Edge sends outbound non-web traffic bound for ports other than 80/443 (or other HTTP/HTTPS ports) directly to the firewall module for policy evaluation. If the traffic violates a firewall policy, it blocks the transaction. If the traffic does not violate any firewall policies, it allows the traffic to the internet.
- Inbound web traffic. The ZIA Public Service Edge sends inbound web traffic (HTTP/HTTPS traffic for ports 80/443) from the internet in response to HTTP GET/POST requests to its web module for policy evaluation. If the traffic violates a web policy, it blocks the transaction. If the traffic does not violate any web policies, it allows the traffic into your organization.

When the web traffic violates a firewall policy, both the Firewall Insights logs and the Web Insights logs indicate that the traffic is blocked. However, if the traffic passes through the firewall policy but is blocked due to a web policy violation later, the Firewall Insights logs still indicate that the traffic is allowed, whereas the Web Insights logs indicate it as blocked.

### Order of Operations

Web traffic is first evaluated in the firewall module and, if not blocked, is sent to the web module. If your organization's web policy allows a transaction but a firewall policy blocks it, the Zscaler service applies the firewall policy (i.e., the traffic is blocked). For example, if a web [Cloud App Control](https://help.zscaler.com/zia/about-cloud-app-control) policy allows the application Box.net, but the firewall policy blocks it, the Zscaler service blocks the transaction.

If the firewall module does not block the user's HTTP or HTTPS transaction, the ZIA Public Service Edge sends the traffic to the web module for policy enforcement.

When the ZIA Public Service Edge receives web traffic, the web module inspects the traffic and applies your organization's web policies. The web module applies policies in a specific order based on the web traffic types, whether the traffic is encrypted or not, and whether SSL inspection is enabled or disabled. If the service finds a policy violation, it immediately blocks the transaction and does not apply other web policies.

Web module policies are enforced in the following order of operations:

- Firewall
- SSL Inspection
- Advanced Threat Protection (ATP)
- Malware/Antivirus (AV)
- Cloud App Control
- URL Control
- File Type Control
- Data Loss Prevention (DLP)
- Cloud Sandbox

See image.

### Policy Enforcement Examples

Understanding how the Zscaler service applies policies in different scenarios helps you determine why certain policies are or are not triggered on users' traffic. It also ensures that your organization's traffic is secured as expected.

The following examples illustrate how policies are applied to traffic.

#### Example 1

Organization X has the following policies:

- The firewall policy allows the cloud application Box.net.
- The web policy blocks the same application.

When a user from the organization opens a browser and requests the application Box.net, the user is blocked. Even though the firewall module allows the traffic, the web module inspects and enforces the web policy (to block Box.net).

#### Example 2

Organization Y has the following policies:

- The File Type Control policy blocks users from sending PDFs from the corporate network.
- The DLP policy blocks documents containing US Social Security numbers (SSNs), and specifies that the Zscaler service sends notifications to auditors when it detects any attempt to send SSNs.

When a user from the organization attempts to send a PDF that contains SSNs, the service blocks the transaction because of the File Type Control policy preventing PDFs. The DLP policy is not triggered, and the service doesn’t notify the auditor that a user has attempted to send SSNs from the organization.

## Deployment Guide

Build a policy strategy that meets your needs as easily as possible. Most organizations use URL Filtering and Cloud App Control features simultaneously.

### Gather a List of the Known Knowns

Before building a policy:

- Review any existing filter policies on legacy systems.
- Take stock of what is known about the environment.
- Look at the available predefined [URL Categories](https://help.zscaler.com/zia/about-url-categories) and [Cloud App Categories](https://help.zscaler.com/zia/about-cloud-app-categories).

Use this review to create a list of destinations (or types of destinations) that absolutely must be allowed, blocked, or restricted.

Commonly blocked destination types include:

- Legal Liability (adult material, gambling, illegal or questionable content, etc.).
- Privacy Risk (security, other encrypted content, spyware/adware).
- Potential Data Loss destinations (file host, webmail, peer-to-peer, etc.), except those specifically approved by the organization.
- Social Media or Streaming Media services.

Commonly allowed destinations include:

- Applications approved and used by the organization.
- Software and operating system update services.
- Vendors and partners specific to the organization.

### Determine the Best Tool to Use

Understanding what traffic should be controlled greatly assists in selecting what policy type to use. Work through the list of known knowns and classify the proper policy type to meet your needs most effectively. Finding the best tool for the task makes policy building and management significantly easier.

Zscaler recommends employing policies as follows:

- Cloud App Control. Use Cloud App Control when you must explicitly allow, block, or restrict user access to a known list of applications or control aspects of user access (e.g., permitting view access but not upload access, receiving mail but not sending mail, etc.)
- URL Filtering. URL Filter is the default access control space. Use URL Filtering to create policies that prevent users from reaching URL categories, or allow access to certain destinations (prebuilt or custom-defined categories). URL Filtering allows the use of granular criteria to fine-tune access details.
- File Type Control. File Type Control is best for creating policies that limit user file type access on permitted destinations and web browsing. File Type Control restricts what files can be shared in one direction or another. File Type Control lets users enjoy web content while maintaining organizational security controls.
- Firewall. A firewall policy is best for controlling outbound connectivity for all non-web traffic; e.g., DNS, SSH, RDP, various United Communications as a Service (UCaaS) services, etc. With the Advanced Firewall subscription, you can use Firewall to control traffic by specific network service (port plus protocol) or by DPI application identification (network application). The Advanced Firewall subscription can also identify traffic that initially begins as web but is some other traffic type and apply the relevant firewall rules.

Furthermore, you can define rules about traffic on web destinations (such as blocking an IP address or allowing traffic to an FQDN or wildcard FQDN destination).

#### Examples

To block users from reaching adult content, use a URL Filtering policy to block the necessary URL categories.

To allow view access to GitHub while blocking uploads, use a Cloud App policy under the System Development category.

To permit users to browse entertainment sites while blocking the upload or download of PDFs and Microsoft documents, use a File Type Control policy for the Entertainment URL Category and select the file types to restrict.

### Determine the Granularity

After identifying which policy type to use, decide if you need special criteria to narrow the traffic matching the rule. If the rule does not apply to all users connecting from anywhere, how should the policy properly identify the traffic? Each of the policy types has unique criteria.

Zscaler recommends the following guidance on selecting criteria:

- Specific Destinations (app or category). Use the appropriate policy type to narrow your selected destinations.
- Specific Users. Use Groups or Department for criteria, as this places control within the user directory space. Avoid using specific users unless testing or access is limited to a small (less than 4) set of users.
- Source Locations. While building policies for individual locations is acceptable, it is easier to manage [Location Groups](https://help.zscaler.com/zia/about-location-groups). You can add new locations to a Location Group and the locations are instantly included in all applicable policies.
- Time. Set access based on the data center’s time. For example, use the time to permit specific files during lunch or ensure certain functions are unavailable after hours or on weekends.
- Specific Device Groups. Using Zscaler Client Connector on user devices, you can configure policies to target device operating systems specifically. Alternatively, you can set a policy to filter traffic sourced by means other than Zscaler Client Connector.
- Device Trust Levels. Zscaler Client Connector continuously evaluates the posture of a device and returns the status of the device. Device Trust levels in ZIA are High, Medium, and Low and are associated with specific device posture profiles that you can configure.
- Mobile Admin. You can create explicit rules to allow and block based on these trust levels.

### Configure Advanced Policy Settings

There are several Advanced Policy Settings in the ZIA Admin Portal. Go to Policy > URL & Cloud App Control > Advanced Policy Settings. The settings affect all traffic.

The following explains the settings:

- Children's Internet Protection Act (CIPA) Compliance. Set to Disable (unless in K-12 education). This feature allows K-12 education-based customers to configure a CIPA-compliant environment.
- Suspicious New Domains Lookup. Set to Enable. This feature provides advanced protection to users against newly registered and observed domains identified within hours of going live, along with newly revived domains that are inactive. New or idle domains are often considered unsafe and potentially malicious. Blocking them (within the URL policy) improves the overall security posture.
- AI/ML-based Content Categorization. Set to Enable. This feature enables AI/ML for the service to categorize traffic of uncategorized websites based on the content. This helps to identify whether the traffic fits within commonly blocked URL super categories and applies the appropriate policy.
- Embedded Sites Categorization. Set to Enable. This feature applies a URL filtering policy for sites embedded within others. It is commonly employed to stop users who attempt to bypass filtering by using translation services.
- SafeSearch. Set to Enable. This feature applies safe search enforcement on many top search sites. This feature requires you to enable SSL inspection of the traffic.
- Identity-based Block Override. Set to Optional. This feature prompts users to authenticate to access otherwise blocked destinations (as configured in a URL policy). It is not commonly enabled.
- Microsoft-Recommended Click-to-Run Microsoft 365 Configuration. Set to Enable. This feature automatically enables an SSL Inspection exemption and a Cloud App and Firewall policy for Microsoft 365 traffic. To customize the policy exemption, review SSL inspection, Cloud App Control, and Firewall policies to build a higher-order policy, or leave this option disabled and manage Microsoft 365 traffic differently. To learn more, see [About Microsoft One-Click Options](https://help.zscaler.com/zia/about-microsoft-one-click-options).
- UCaaS services. Enable per service if used in the environment. This feature manages SSL inspection and access control policy for the respective service to operate.

### Configure Advanced Settings

Go to Administration > Advanced Settings to reveal additional global configuration options. The following section reviews a selection of options:

- Cascading to URL Filtering. Set to Disable. This feature lets traffic allowed by Cloud App Control to flow to URL Filtering for policy evaluation before any action is taken. This setting isn’t commonly used and often causes more issues than it resolves. This is a global option, so consider your policy structure before enabling the feature. If the feature is necessary, use Firewall Control to meet your needs first.
- Policy for Unauthenticated Traffic. Set to Enable. For policies that specify users and departments in the criteria, you can specify which rules the service applies to unauthenticated traffic. Enabling this feature logs a specific username based on why the traffic was not authenticated when the location enforced authentication. To learn more, see [Configuring Policies for Unauthenticated Traffic](https://help.zscaler.com/zia/configuring-policies-for-unauthenticated-traffic).
- Block Domain Fronting. Set to Enable. Domain fronting allows a client to conceal the true intended destination of an HTTPS request from censors and network security filters by *fronting* the request with a TLS connection to a different domain than the one set in the request’s host header, if both are hosted on the same Content Delivery Network (CDN) service. Using domain fronting, an attacker hides an HTTPS request to a bad site inside a TLS connection to a good site. Using this feature and performing SSL inspection allows Zscaler to evaluate traffic for domain fronting and block any detections. To learn more, see [Analysis of Domain Fronting Technique: Abuse and Hiding via CDNs](https://www.zscaler.com/blogs/security-research/analysis-domain-fronting-technique-abuse-and-hiding-cdns).
- Auto Proxy Forwarding for Non-Defined Ports. Set to Enable All. Advanced Firewall’s Deep Packet Inspection (DPI) capability identifies traffic (including web and other traffic) that bypasses the Secure Web Gateway (SWG) proxy and other specialized engines. With the Auto Proxy Forwarding for Non-Defined Ports toggles enabled, any non-standard traffic (by destination port) not currently redirected to the SWG or other specialized engines is subsequently redirected on the next session that targets that IP, port, and protocol destination. For example, if HTTPS is discovered on destination IP:2.2.2.2 and port:32556, subsequent sessions are directed to the SWG proxy for full policy inspection. You must create a Cloud Firewall rule that permits the traffic initially and an Allow rule with HTTPS defined as the network application to discover non-standard HTTPS.

See image.

### Build a Policy

To stage policy, begin with security concerns and have future expansion and management in mind. The following sections review the baseline recommendations for URL Filtering, Cloud App Control, File Type Control, Firewall, and DNS Control.

#### URL Filtering Policy Baseline

URL Filtering policies are usually the most straightforward of any policies configured. The categories regarding whether to allow or block are generally obvious, and the policy logic makes sense. The best approach for URL Filtering is to set a policy for Global > Specific > Global. In other words:

1. Start with global rules. Create a rule that allows normally blocked destinations and blocks normally allowed destinations.
2. Configure rules (allow or block) specific to source users, groups, locations, and destinations that apply to specific users and user groups.
3. Create rules that apply to everyone and block unwanted traffic before the default (unwritten) allow-all rule.

The following table lays out a good starting policy strategy checklist.

| Rule # | Purpose | Criteria (minimum listed) | Action |
| --- | --- | --- | --- |
| 1 | Global Specific Permit. Do not migrate your existing Allow list in full. Test Zscaler’s engines before determining what to migrate. | Category: Custom Exceptions (maintained to allow specific FQDN destinations, otherwise blocked by policy). | Allow |
| 2 | Global Specific Block. Do not migrate your existing Block list in full. Test Zscaler’s engines before determining what to migrate. | Category: Custom Blocks (maintained for destinations that would otherwise be allowed by policy). | Block |
| 3 | Global Security-based Block. | Category: Newly Registered and Observed Domains, Peer-to-Peer Site, Anonymizer, Computer Hacking, Copyright Infringement, Custom Encrypted Content, Newly Revived Domains, Other Security, Spyware/Adware. | Block |
| 4+ | Policies specific to User Groups. While not necessarily security related, it is good to stage policies in a disabled state to build a starting structure. | Category: Optional. User Group: As necessary. | Allow or Block |
| 5+ | Policy specific to Location Groups. Building a policy using user attributes is preferred. Policy follows the user anywhere. Policy by location could be necessary and should have a structure. | Category: Optional. Location: As necessary. Location Groups: As necessary. | Allow or Block |
| 6 | Global Category Blocks. Select the specific URL Categories that should not be accessible to any groups not permitted earlier. | Category: As desired. | Block |
| Default | Default Allow-all. This rule is unwritten and is the default action for URL Filtering Policy. Anything not matching a prior policy is allowed and is evaluated by the next policy module. | Unwritten. No criteria. Not configurable. | Allow |

The following is an example of a policy rule set.

See image.

Every customer requires a set of policies that make sense for their specific environment and security concerns. The policy design example here creates the following conditions:

- Specific allows (overrides): Create rules that override Zscaler's blocked categories.
- Specific blocks: Create categories that MUST NOT be allowed under any circumstance.
- Specific security blocks: Create block rules that prevent user access due to security team requirements.
- Granular control: Create allow and block rules for granular company-specific access control requirements.
- Global blocks: Create block rules for categories not covered prior (or all categories if customer wishes to overwrite the implicit allow).

For help creating a set of policies that address your needs, contact your [Zscaler Account team](https://help.zscaler.com/contact-support).

#### Cloud App Control Policy Baseline

The recommended approach for Cloud App Control is to be strategic and selective wherever possible. Configure rules matching the applications and criteria for explicitly known needs and leave the unknown to URL Filtering policies. If it must be allowed, blocked, isolated, or controlled in some form, build a Cloud App Control rule. Otherwise, control access elsewhere.

Rules are applied top-down, so entries move from granular to less specific. If one team needs access to a specific tool and other teams should be blocked, build an access rule specific to the approved use group and another rule to block other groups.

If uncertain, rely on the URL Filtering policy.

##### General Guidance

Use the [Risk Profile](https://help.zscaler.com/zia/about-cloud-application-risk-profile) feature to control access to applications without needing to research and specify them individually. Creating a risk profile for applications with a Risk Index of 4 or 5 in an Unsanctioned status allows you to apply the profile to various Cloud Application categories and block or restrict access to risky applications.

To learn more, see [SaaS Security Insights](https://help.zscaler.com/zia/saas-security-insights).

The following is an example Risk Profile:

1. Select **Any**for **Cloud Applications**.
2. Select a setting for **Cloud Application Risk Profile**.
3. Select **Users**or **Locations**criteria.

See image.

1. Select **Block**or **Isolate**for **Action**.

Example Policy:

See image.

##### Recommendations by Cloud App Category

The following are recommendations for the different Cloud App categories.

- Collaboration and Online Meetings: Common inclusions are Microsoft, Google, Zoom, and Webex services.
- DNS Over HTTPS Services: Common DNS over HTTPS, whether used by default in certain browsers or configured by end users. If you use Zscaler’s DNS Control features within the environment, Zscaler recommends blocking these services (either here or with more security granularity within DNS Control) to ensure DNS requests are captured and processed correctly.
  - File Sharing: Contains many common and obscure file-sharing cloud applications. A common recommendation is to allow access to the organization’s selected/approved file-sharing service within Cloud App Control and then block the other services through a URL Filtering policy with the File Host category.
  - Rules created within File Sharing give additional control to allow or block uploading traffic if viewing is allowed. This assists in permitting users access to view (download) files from third-party sites while preventing the upload of files to the same. SSL inspection is necessary for the additional feature to work.
- IT Services: Common space for development, design, SaaS hosts, identity providers (IdPs), security information and event management systems (SIEMs), OS updates, and other services, along with login services for Google, Microsoft, and Webex. You can configure this category to use [Tenant Profiles](https://help.zscaler.com/zia/adding-tenant-profiles) for related services.
- Productivity and CRM Tools: Contains the productivity tools used within the organization. Common examples are Microsoft, Google, Salesforce, and ServiceNow. You can configure this category to use [Tenant Profiles](https://help.zscaler.com/zia/adding-tenant-profiles) for related services.
- Social Networking: Contains popular social networking and media applications (Facebook, Discord, Reddit, TikTok, etc.). This category can granularly control access to the services and the user's ability to post to the service. SSL inspection is necessary to control posting.
- Streaming Media: Contains the common streaming platforms. Controlling access to these applications reduces bandwidth consumption and productivity loss. A common policy permits YouTube (+ option for [Tenant Profiles](https://help.zscaler.com/zia/adding-tenant-profiles)) and other approved media services for training purposes before further restricting the Music & Audio Streaming and Video Streaming categories by URL Filtering policy.
- System Development: Contains common development repositories, tools, and training sites related to development. You can configure rules to control the upload permissions to restrict users further if they can browse the content versus share and store content. You can find services not listed here under the IT Services category.
  - Webmail: Zscaler’s recommendation is to explicitly allow webmail services selected by the organization and fully restrict sending mail or sending mail with attachments to any non-approved webmail services for users and devices within the corporate environment. SSL inspection is highly recommended for any permitted webmail services and is necessary to restrict sending.

#### File Type Control Policy Baseline

It is necessary to properly implement File Type Control to maintain organizational security by preventing uploading and downloading unwanted files while still allowing users access to internet services.

Zscaler recommends setting policies that control sending and receiving files to URL categories that don’t contain the expected file types.

##### Examples

- Prohibit Microsoft 365 documents from uploading or downloading to and from Entertainment, Gambling, and Productivity Loss sites.
- Permit executable downloads (i.e., .exe, .cab, etc.) from trusted vendors whose applications automatically update end user machines.
- Caution or Block downloads of executable files from all sites except those approved.
- Block the upload of files (i.e., .zip, .cad, .dwg, .stl, undetectable, etc.) from design teams to unsanctioned destinations.

Do not block files commonly used for websites unless you want to restrict access tightly and don’t mind severely impacting the user experience.

The following table outlines an example of a set of File Type Control policies.

| Rule # | Purpose | User/Groups/Departments | File Type | URL Category | Upload/ Download | Action |
| --- | --- | --- | --- | --- | --- | --- |
| 1+ | Business exceptions | Specific Groups | Only file types required by the exception (e.g., PDF, archives, or Microsoft 365 documents from business partners). Applications that auto-update (OS, browsers, etc.). | Custom category for specific trusted sites. | Download and/or Upload | Allow |
| 2+ | Permit EXE download for IT Admins | IT Admins and Helpdesk | Archives (.zip, .7z, .bzip, .cab, .gzip, .rar, .tgz, etc.). Executables (.sh, .msi, .exe, .dll, .ps1, etc.). Other documents (.crt, .pcap, etc.). | Specifically accepted categories (business, education, info tech). | Download | Allow |
| 3+ | Permit uploads for developers | Developers | Source code, web content, video, other documents, executables, etc. | Custom category for specific trusted development sites. | Upload/ Download | Allow |
| 4+ | Permit download of Microsoft 365 docs for business | Everyone | Microsoft 365, select archives, specific other documents (.cad, .csv, .txt), etc., as necessary. | Specific business-appropriate categories. | Download | Allow |
| 5+ | Block upload and download of unnecessary files | Everyone | Everything except .gzip, audio, image, select video and web content. | All categories. | Upload/ Download | Block or Caution |

#### Cloud Firewall Control Policy Baseline

Zscaler’s Firewall service consists of three main capabilities: the core Firewall, DNS Control, and Cloud IPS Control.

The core Firewall is available as a Standard Firewall and Advanced Firewall. The Standard Firewall is available in all tiers of service that includes the SWG proxy and provides Layer 3, Layer 4, and FQDN rules. For example, you can define a rule to block a source or destination IP address plus FQDN (using an AND condition) plus network service (port and protocol, also using an AND in this case). Zscaler’s Standard Firewall provides aggregate logging for all flows and detailed logging where a block action is selected.

Zscaler’s Advanced Firewall adds per-user conditions (per-user, group, department, etc.), application identification through Deep Packet Inspection (DPI), an ability to detect and redirect non-standard web traffic to the SWG and other modules, DNS controls, and IPS controls for non-web traffic. The Advanced Firewall also adds detailed logging for each session and flow, regardless of whether they are allowed by policy or blocked.

The following Zscaler recommendations often assume that Advanced Firewall is used, and many suggestions blend one or more of these functions.

##### Enable Cloud Firewall (and IPS) per Location

Firewall rules apply only to locations where Firewall is enabled. It always applies to remote users. Ensure that Cloud Firewall and IPS are enabled at each location where Firewall, DNS, and IPS controls are needed (Admin > Location Management).

See image.

##### Determining the Firewall Strategy: Default Firewall Filtering Rule

The first Firewall rule to consider is the last. The setting here determines the strategic approach for defining all firewall rules.

Today, all Zscaler tenants ship with the default Firewall Filtering Rule set for the Block. This means all traffic by default is blocked unless it is allowed in a higher-ranked rule. This strategy is called *default block* (i.e., the traffic is placed on the *allowlist*) and requires explicitly defining any traffic and applications that can access the internet in one or more rules. You must tactfully add new applications or newly discovered applications.

See image.

This default block is very secure and adopts Zero Trust tenants using a least-privilege approach. This approach, however, can mean disruption to business functions as applications unknown to network ops, security ops, or purchased or implemented outside the normal purview of these teams become non-functional.

A staged rollout is a common way to mitigate disruption, where traffic is initially examined, and all flows are attributed to an application that meets the business goals. This way, you can identify applications in advance and incrementally add Allow rules until few or no sessions are unattributable to the desired set of applications.

Historically, ZIA Firewall shipped with a default Allow rule setting, and many customers have maintained this setting.

The default Allow strategy is that all undesirable or malicious applications using the network must be explicitly defined in firewall rules. This is called a *block list* strategy and requires NetOps/SecOps validation to get a secure footing. The default Allow rule is often regarded as a low disruption approach—at least until a business suffers a breach due to the overly permissive policy.

##### Essential Firewall Best Practices

The following best practices assume a default block strategy is in place:

1. Highest priority to main system-defined rules: Ensure top rules are the rules set to allow. This includes allowing Zscaler proxy traffic, Microsoft 365, and any other Click-to-Run rules to permit UCaaS communication. Putting any other rule higher than the Zscaler proxy traffic rule causes the proxy not to function. Any rule above the Click-to-Run rule is possible but not advised.

See image.

1. High priority to Recommended Firewall rule: The Recommended Firewall rule is also a system-defined rule that allows certain known traffic to pass the Layer 3 and Layer 4 firewall and be examined by higher-level proxy functions. These higher-level proxy functions include SWG for web (and other) traffic and DNS Control for standard DNS. This rule uses network services (port plus protocol) and specifically targets standard web (TCP:80 as HTTP and TCP:443 as HTTPS) and DNS (TCP:53 or UDP:53).

Because there is a deeper inspection of the web and DNS, enabling this rule doesn’t mean that this traffic passes uninspected and insecurely, but that you must specify policy for this traffic in those higher-level proxy engines. Zscaler doesn’t recommend putting any other traffic-type conditions in this rule. Reserve this rule for just the specialized ZIA functions. You don’t need to specify DNS over HTTPS (DoH) because it falls under the Web, but the DNS Control engine inspects it after it is determined to be a DoH flow.

1. Blocking QUIC as a network service: QUIC is an alternative method to transmit web content that doesn't use TCP and forms the transport basis of the HTTP/3 protocol (which is not yet managed by SWG). Zscaler recommends blocking QUIC and forcing communications into HTTP/2 or HTTPS, which SWG fully manages. Most QUIC is UDP:443, also the current definition of the network service QUIC. Since QUIC appears on UDP:80, you might consider modifying the default definition of the network service QUIC (Admin > Network Services). The selection of the ICMP block drops the UDP packet and sends the client a Type 3 error message (`Destination Unreachable`) and Code 13 (`Communication Administratively Prohibited`) via ICMP. This allows the client a potentially faster switchover to another web transport method, resulting in a better user experience with little lag as the client learns quickly that QUIC is not working. This results in a better user experience regardless if you use a *default block* rule strategy.

See image.

1. Considered use of Network Applications in rules: Since DPI was incorporated into firewalls in the mid-2000s with next-generation firewalls, independent identification of applications is done regardless of the port used by the application. The strength of DPI is the certainty of the detection. If DPI identifies the traffic as application x, then it is certainly application x. The challenge with any DPI firewall is that application detection does not usually happen on the first packet. This means that several packets might be allowed before the application identification concludes and, therefore, an action (Allow, Block). This leakage means using network applications as the best practice should be applied to as few flows as possible. This limits the number of rule conditions that must stay open since DPI identifies the flow. The best way to do this is to blend network applications with at least one other condition in a rule, such as source or destination IP address, user, or network service. Alternatively, you can lower the priority of a rule (place it further down the rule list) with network applications so first-packet rules using conditions like network services, IP addresses, and FQDNs can trigger on their relevant flows immediately before DPI inspects the remainder flows. If you don’t follow this practice, you could get a lot of flow log entries for flows that were only short bursts and ended independent of firewall policy (before the network application was identified) as `Allowed due to insufficient app data`.

##### Deploying Without Disruption (Standard Firewall):

1. Ensure your default action is set to **Allow**for all traffic if not blocked in a prior rule.
2. Review your existing legacy firewall for any explicit **Block**policies.
3. Configure **Allow**and **Block**policies in ZIA to match as necessary:
  1. Only build Network Services policy.
  2. Enable Firewall at the sublocation level.
4. Forward non-web traffic from test locations and users to Zscaler:
  1. Convert select tunnels from 80/443 to all ports and protocols.
  2. Implement Z-Tunnel 2.0 for targeting remote users with Zscaler Client Connector.
5. Improve your policy:
  1. Use Firewall NSS feed to your SIEM to identify blocked traffic during troubleshooting.
  2. Continue building **Allow**policies to match needs, allowing traffic from specific sublocations to the necessary destinations or services.
  3. **Block**what you know or, as necessary, to work with your policy.
  4. Set Default policy to **Block**.

##### Deploying Without Disruption (Advanced Firewall):

1. Ensure your default action is set to **Allow**or all traffic if not blocked by a prior rule.
2. Review your existing legacy firewall for any explicit **Block**policies.
3. Configure known-necessary **Block**policies in ZIA.
4. Enable Firewall at the sublocation level for all locations.
5. Forward your additional non-web traffic to Zscaler:
  1. Convert tunnels and policy-based routing (PBR) from 80/443 to all ports and protocols.
  2. Implement Z-Tunnel 2.0 for remote users with Zscaler Client Connector.
6. Review logs regularly to:
  1. Determine which traffic is triggering the Default policy.
  2. Identify if this traffic should be allowed or blocked.
  3. Build policy to match, ensuring it is higher in the list than the default policy.
  4. Repeat until Default policy matches are minimal enough to set the action to Block safely.

##### Policy Configuration Examples

The following sections provide configuration examples.

###### Restricting SSH to Trusted Destinations

In this use case, outbound connections via SSH are only available to specific trusted destinations and blocked otherwise. The purpose of the rule is that permitting SSH to any destination exposes a potential data exfiltration pathway.

Building the rules:

1. Configure a Trusted SSH. To learn more, see [IP or FQDN Destination Groups](https://help.zscaler.com/zia/configuring-destination-ip-groups).

See image.

1. Build a rule to permit select services: **SSH**.

See image.

1. Select **Destination Groups**: **Trusted SSH.**

See image.

1. Set Action: **Allow**.
2. Click **Save**.
3. Build a rule to **Block**.
4. Select Services: **SSH.**
5. Set **Action**: **Block/Reset**.
6. Click **Save**.

See image.

##### Restricting RDP to Specific Users

In this use case, outbound connections via RDP is only permitted by specific users and blocked for all others.

The purpose of this rule is that users initiating RDP to external machines can bypass filtering and data exfiltration.

Building the Rules:

1. Build [Custom Network Service](https://help.zscaler.com/zia/adding-network-service).
2. **TCP Destination Port**: **3389**.

See image.

1. Build a rule to **Permit**.
2. Select the desired user groups.
3. Select the **Custom Service**.
4. Select the **Application**: **Remote Desktop**.

See image.

1. Set **Action**: **Allow**.
2. Click **Save**.
3. Build a rule to Block.
4. Select **Network Applications**: **RDP**.

See image.

1. Set **Action**: **Block/Rese**t.
2. Click **Save**.

Selecting Applications requires an Advanced Firewall subscription. Standard licensed users can select Network Services.

See image.

#### DNS Control Policy Baseline

DNS Control examines DNS requests and responses and secures these against undesirable, risky, or malicious domains and IP address categories. DNS Control offers many other rule conditions that can be combined with or used independently from categorizations like DNS protocol type, DNS record type, resolved country IP, etc.

Additionally, DNS Control is critical to preventing undesirable communications from leaving your environment. Visibility into the request, and control of the response, means you can steer users and machines away from many of the dangerous services before issuing the connection request.

##### Forwarding DNS Traffic to Zscaler

You must direct DNS traffic to ZIA for DNS Control to work. You can direct traffic to ZIA in different ways, but the main options are:

1. Including DNS into the existing GRE or IPSec forwarding tunnels. This is usually done for HQ and branch locations.
2. Enabling Z-Tunnel 2.0 on deployed Zscaler Client Connector clients and including DNS traffic (excluding any domains or IPs that should not be sent to Zscaler, particularly private solutions).
3. Forwarding recursive DNS requests directly from a DNS server. This requires creating a location with the IP address of the DNS server sending traffic to ZIA. This method loses all end user context but is often done as an initial step for DNS security.

##### Recommendations

- **Protect users** from reaching malicious domains by blocking destination IP categories for the riskiest destinations as the first line of defense. Examples: Anonymizer, Botnet Callback, Malicious Content, Phishing.

See image.

- **Block or Redirect DNS Over HTTPS**requests by Protocol, sending to a trusted public server to gain visibility into requests. This requires SSL Inspection to perform.

See image.

- **Block DNS Tunneling** traffic to unwanted (Commonly Blocked DNS Tunnels) applications. Additionally, review the Unknown DNS Tunnels section for additional pieces to block.

See image.

- **Permit DNS**requests to trusted servers (defined within an IP Destination Group). Then block or redirect any other DNS requests as the final policy.

See image.

- Create policy rules as their properties are shown in the rule order list.

See image.

### Deployment Checklist

The following checklists provide a guide to setting ZIA policies.

#### URL Filtering Recommendations by URL Super Category

Zscaler recommends downloading the [ZIA Filtering Recommendations by URL Super Categories Leading Practices Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/zia-policy-leading-practices/ZIA-Filtering-URL-Super-Category-Leading-Practices-Checklist.pdf) to help plan and implement ZIA policies: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/zia-policy-leading-practices/ZIA-Filtering-URL-Super-Category-Leading-Practices-Checklist.pdf).

To learn more, see [About URL Categories](https://help.zscaler.com/zia/about-url-categories).

#### URL Filtering Advanced Policy Settings

Zscaler recommends downloading the [ZIA URL Filtering Advanced Policy Settings Leading Practices Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/zia-policy-leading-practices/ZIA-URL-Filtering-Advanced-Policy-Settings-Leading-Practices-Checklist.pdf) to help plan and implement ZIA policies: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/zia-policy-leading-practices/ZIA-URL-Filtering-Advanced-Policy-Settings-Leading-Practices-Checklist.pdf).

To learn more, see [Configuring Advanced URL Policy Settings](https://help.zscaler.com/zia/configuring-advanced-url-policy-settings).

### Considerations

The following considerations are important when creating ZIA policies.

#### Policy for Non-Web Traffic Without Firewall Enabled

If the ZIA Public Service Edge receives outbound, non-web traffic going to ports other than 80/443, and the organization has a firewall policy enabled on the user's location. The ZIA Public Service Edge inspects the traffic and applies the policy using only the firewall module if the organization has not enabled firewall policy for the location. The ZIA Public Service Edge neither scans nor applies any policy to the traffic.

#### Policy for Unauthenticated Traffic

Properly configured ZIA policies should authenticate traffic associated with a user, but it might not be possible in every environment. Deploying and using Zscaler Client Connector even while on Trusted Networks greatly increases the authentication rate and user attribution to traffic.

Alternatively, Zscaler encourages you to enable the policy for Unauthenticated Traffic feature by going to Administration > Advanced Settings. This feature provides additional departments specific to various unauthenticated traffic reasons for selection when building policy. By enabling the feature, policies are enforced for traffic that isn’t authenticated even though authentication is enabled for the location. To learn more, see [Configuring Policies for Unauthenticated Traffic](https://help.zscaler.com/zia/configuring-policies-for-unauthenticated-traffic).

[Image: ZIA Services Edge]

[Image: Advanced Settings]

[Image: Rule Order Example]

[Image: Productivity and CRM Tools]

[Image: Default Rule]

[Image: Highest Priority Policy]

[Image: Edit Firewall Filtering]

[Image: Network Services Rules]

[Image: Rule Order Example]

[Image: Edit Cloud Application Risk Profile]

[Image: Add Destination Group]

[Image: SSH]

[Image: Trusted SSH]

[Image: Rule 7 and 8]

[Image: Add Network Service]

[Image: Remote Desktop]

[Image: Applications]

[Image: DNS Application]

[Image: DNS Application Action]

[Image: DNS Application Commonly Blocked DNS Tunnels]

[Image: DNS Resloved]

[Image: Gateway Options]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/zia-ssl-inspection-leading-practices-guide","lastmod":"2026-06-12T17:32Z","nid":"1456351"} -->
## Internet & SaaS TLS and SSL Inspection Leading Practices Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/zia-ssl-inspection-leading-practices-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZIA Deployments & Operations > Internet & SaaS TLS and SSL Inspection Leading Practices Guide
- Last modified: 2026-06-12T17:32Z
- Summary: The Internet & SaaS SSL Inspection Leading Practices Guide provides a set of best practices for configuring and deploying Internet & SaaS Secure Sockets Layer (SSL) and Transport Layer Security (TSL) inspection in an organization's environment.

The Internet & SaaS SSL and TLS Inspection Leading Practices Guide provides a set of best practices for configuring and deploying Internet & SaaS Secure Sockets Layer (SSL), and its successor Transport Layer Security (TLS) inspection in an organization's network environment.

Encrypting communications helps maintain the privacy and security of information passed between sender and receiver communications. SSL and its successor TLS are protocols designed for the privacy and security of internet services. While these protocols do a great job of keeping information private from prying eyes, these security tools also conceal threats to the user, device, and organization. This is where the inspection of SSL and TLS encrypted traffic becomes a necessity.

The modern term is typically TLS, while the function in the Zscaler UI generally uses SSL to encompass all encrypted traffic. This document uses TLS unless referring to a specific section of the Zscaler UI.

Inspecting encrypted traffic is done by Zscaler’s Zero Trust Exchange (ZTE) at scale, allowing organizations to control risk and enforce policy. Enabling TLS inspection is a required first step towards:

- Controlling risk
- Inspect Traffic (malware, data loss)
- Adaptive control
- Enforcing policy
- Per session policy decision and enforcement
- Allowing, blocking, and restricting tenants

See image.

## Traffic Inspection Strategy

A good zero trust strategy is to inspect as much traffic as possible, and understand how to properly handle non-inspected traffic. First, implement a baseline traffic inspection policy, and then expand inspection to additional URL categories.

Zscaler recommends inspecting as much traffic as possible, but 100% traffic inspection is typically not feasible due to legal liability, regulatory concerns, Sarbanes-Oxley Act (SOX) requirements, personal identifying information (PII) issues, and other limiting factors. Zscaler often sees companies achieve a 60–70 percent TLS inspection rate. The key to a good strategy for any organization is understanding why something is or is not inspected. Internet & SaaS makes it easy to understand TLS transactions and make policies around TLS inspection.

### Why Inspect?

With more than 95% of global traffic encrypted, most threats are passed within encryption and are invisible without implementing inline TLS inspection.

See image.

ThreatLabz, Zscaler’s in-house security research team, created the [ThreatLabz 2024 Encrypted Attacks Report](https://www.zscaler.com/campaign/threatlabz-encrypted-attacks-report), a whitepaper reviewing security threat trends for 2024.

### What Can’t Be Inspected

TLS inspection requires that the endpoints trust the certificate presented by the inspecting service. You must install a certificate onto each endpoint to build this trust. As such, inspection is not possible on devices that cannot reasonably install the certificate (e.g., IoT/OT, BYOD, guest networks, etc.).

Some vendors and developers have implemented coding techniques commonly referred to as certificate pinning (see Certificate Pinning or Hard-Coded Certificates) that work to prevent Man-in-the-Middle (MiTM) inspection. As such, Zscaler cannot inspect TLS traffic from sites or applications that use certificate pinning (e.g., Apple, many Microsoft 365 apps, Adobe, Cisco WebEx, Dropbox app, etc.).

Finally, Zscaler cannot inspect sites and applications using client-authentication certificates mutual Transport Layer Security (mTLS), or use unsupported ciphers.

### What Commonly Isn’t Inspected

Certain services currently posing little to no security risk are commonly not inspected. Examples include conferencing and communication systems (Teams, Zoom, etc.). Additionally, TLS inspection is often configured to exempt URL categories having PII concerns (such as Health Care, Finance, or Banking). The most common are Health and Finance sites. Discuss sites that raise legal or regulatory concerns with HR, councils, or legal experts for local privacy laws.

### Tools Dependent On TLS Inspection

Many of Zscaler’s ZTE security features require TLS inspection to work on encrypted transactions. Anything which inspects the payload, or requires policy on HTTP Headers, or redirects the user with injected content, would require TLS inspection. Some of the features that depend on TLS inspection are:

- Anti-Virus/Malware
- Cloud Sandbox
- Advanced Threat Prevention
- Isolation
- Data Loss Prevention (DLP)
- File Type Control
- Inline CASB
- Intrusion Prevention System (IPS)

See image.

Zscaler must see the contents of transactions to effectively block malware, manage data loss, and protect users. While Zscaler can still protect traffic with zero trust strategies without TLS inspection, organizations miss out on the full benefit of many Zscaler security features if they don’t employ TLS inspection.

## Deploying TLS Inspection

The following sections describe considerations when deploying TLS inspection.

### Stakeholders

Work with internal stakeholders (i.e., executives, legal teams, human resources, etc.) to align TLS inspection requirements and to establish consensus on the need for change. Stakeholders must understand TLS inspection benefits and the risks of not implementing TLS inspection. Without TLS inspection, the organization can’t ensure that encrypted traffic is free from the threat of malicious inbound content, or verify the legitimacy of sensitive outbound data. Consult and inform your stakeholders during all phases of TLS inspection rollout because they are crucial for successfully implementing TLS inspection.

To learn more, see [Encryption, Privacy, & Data Protection: A Balancing Act](https://www.zscaler.com/resources/white-papers/encryption-privacy-data-protection.pdf).

### Compliance Documentation and Communication

Implementing TLS inspection is a technical initiative, but it also requires preparing the organization for the change. Typically, organizations must update computer usage policies, acceptable use agreements, and other internal documentation related to IT systems. In some regions and countries, TLS inspection requires the involvement of workers’ councils and unions.

### Root Certificate

You must decide between using the Zscaler Central Authority (CA) or your own existing CA with Zscaler as an intermediate CA. Using your existing CA requires an additional Zscaler subscription.

Either way, you must deploy the certificate to all necessary devices and handle adding the custom certificate to an Application-Specific Trust Store. Adding the customer certificate is required, since some applications do not leverage the system’s built-in certificate store. This is often found with software development tools and custom applications.

To learn more, see [Adding Custom Certificate to an Application-Specific Trust Store](https://help.zscaler.com/zia/adding-custom-certificate-application-specific-trusted-store) and [Choosing the CA Certificate for SSL/TLS Inspection](https://help.zscaler.com/zia/choosing-ca-certificate-ssltls-inspection).

## Deployment Guide

You must ensure that your organization continues to operate without interruption while you roll out TLS inspection. Zscaler recommends starting your inspection rollout with a limited group of users and policies from various departments or business units and expanding as your policy becomes more robust. This method allows you to learn as you deploy with minimal disruption. Follow the general guidance provided to maximize your chances of success.

1. Complete all prerequisites.
2. Deploy the selected Root CA Certificate (follow phasing if necessary).
3. Stage inspection policy for the pilot.

### Building Policy

The following provides a general template for policy building:

1. Build using a granular, rule-based engine that includes:
  - User, Group, and Department
  - URL Category and Cloud Apps
  - Location and Location Group
2. Avoid breaking cert-pinned apps, including client OS, user agents, etc.
3. Enforce secure TLS usage:
  - Minimum TLS versions
  - Certificate validation/revocation
4. Inspect OneDrive, SharePoint, etc., but exclude Click-to-Run from Microsoft 365. See image.

### Initial Recommended Policy

Read this entire section before taking any action, as it contains general guidance on the initial policy set as well as basic pilot phasing. Implementing these rules in a production environment is likely to cause impact, and thus should not be done until all preparations have been made.

1. Enable the **Microsoft 365 One Click** policy. The **Microsoft 365 One Click** policy is a set of rules made in consultation with Microsoft to provide the best experience for Microsoft 365. Additionally, the Microsoft 365 Click-to-Run policies are automatically updated if Microsoft makes a change. To learn more, see [Best Practices for Implementing Access to Microsoft 365 with Zscaler](https://www.zscaler.com/resources/white-papers/best-practices-for-microsoft365-and-zscaler.pdf).
2. Go to **Policies**> **Access Control** > **Internet & SaaS** > **Advanced Settings**
3. Select **Enable Microsoft-Recommended One Click Office 365 Configuration**.
4. Click **Save**.
5. Go to **Policies**> **Common Configuration** > **SSL/TLS Inspection**> **SSL/TLS Inspection Policy**.
6. Build rules with the intention to eventually align with the following leading practices examples. To learn more, see [Configuring SSL/TLS Inspection Policy](https://help.zscaler.com/zia/configuring-ssltls-inspection-policy).
  | Rule | Name/Purpose | Criteria | Actions |
  | --- | --- | --- | --- |
  | 1 | Smart Isolation One Click Rule (if licensed) | URL Category: Suspicious Domains | Inspect: Untrusted Server Certificates: Block OCSP Revocation Check: Enabled; Block No Server Name Indication (SNI): Disabled; Block Undecryptable Traffic: Enabled; Minimum Client TLS Version: TLS 1.0; Minimum Server TLS Version: TLS 1.0; Enable HTTP/2: Enabled |
  | 2 | Zscaler Recommended Exemption Rule | N/A | Do Not Inspect: Evaluate Other Polcies: Show End User Notifications: Disabled; Untrusted Server Certificates: Block; OCSP Revocation Check: Enabled; Minimum TLS Version: TLS 1.0 |
  | 3 | Exclude Endpoint Protection Services | Cloud Applications: Microsoft Defender Advanced Threat Protection, CrowdStrike, etc. | Do Not Inspect: Bypass Other Policies Block No Server Name Indication (SNI): Disabled |
  | 4+ | Global Source-Based Exemptions (Repeat as necessary for Locations, Groups, Devices, etc.) | Specific mixture of source criteria needing to be exempted from inspection. Locations and Location Groups; Groups and Departments; Device GroupsExample: Location Groups: Locations_NoSSL; Device Groups: No Client Connector | Do Not Inspect: Evaluate Other Policies Show End User Notifications: Disabled; Untrusted Server Certificates: Allow; OCSP Revocation Check: Enabled; Minimum TLS Version: TLS 1.2 |
  | 5 | Inspect Rule for OneDrive, SharePoint, and Microsoft Login Services | Cloud Applications: OneDrive, SharePoint, Microsoft Login Services Device Groups: Windows, macOS (Disabled to start, or set criteria to include an empty group or sublocation to avoid impacting traffic immediately—refining criteria later as necessary) | Inspect Untrusted Server Certificates: Block; OCSP Revocation Check: Enabled; Block No Server Name Indication (SNI): Enabled; Block Undecryptable Traffic: Enabled; Minimum Client TLS Version: TLS 1.2; Minimum Server TLS Version: TLS 1.2; Enable HTTP/2: Enabled |
  | 6 | Office 365 One Click (Auto-generated earlier in Step 3). | N/A | Do Not Inspect: Evaluate Other Policies Show End User Notifications: Enabled; Untrusted Server Certificates: Allow; OCSP Revocation Check: Enabled; Block No Server Name Indication (SNI): Disabled; Minimum TLS Version: TLS 1.2 |
  | 7+ | Specific Exemption Rules (Granular by source and destination as individual rules) | Criteria defined to ensure specific source and destination exemption needs are met without being overly risky. Example: Device Groups: iOS; URL Category: Custom_iOS_Bypass | Do Not Inspect: Evaluate Other Policies Show End User Notifications: Disabled; Untrusted Server Certificates: Block; OCSP Revocation Check: Enabled; Block No Server Name Indication (SNI): Enabled; Minimum TLS Version: TLS 1.2 |
  | 8+ | Global Source-Based Exemptions (Repeat as necessary for Locations, Groups, Devices, etc.) | Specific mixture of source criteria needing to be exempted from inspection. Locations and Location Groups; Groups and Departments; Device GroupsExample: Location Groups: Locations_NoSSL; Device Groups: No Client Connector | Do Not Inspect: Evaluate Other Policies Show End User Notifications: Disabled; Untrusted Server Certificates: Block; OCSP Revocation Check: Enabled; Minimum TLS Version: TLS 1.2 |
  | 9+ | Global Destination-Based Exemptions (Repeat as necessary, for different use case tracking) | Specific destinations (URL Category, Cloud Application, Destination Group, etc.) needing exemption from inspection. Example: URL Category: Finance, Health, Custom_No_Inspect-Certificate_Pinning | Do Not Inspect: Evaluate Other Policies Show End User Notifications: Disabled; Untrusted Server Certificates: Block; OCSP Revocation Check: Enabled; Block No Server Name Indication (SNI): Enabled; Minimum TLS Version: TLS 1.2 |
  | 10+ | Action Exceptions (Create as necessary to handle specific use cases) | Rules defined to address specific sources or destinations where settings must be changed within the Actions list. These can be for Inspect or Do Not Inspect type rules. Common reasons are for Minimum TLS Version downgrades; accepting Undecryptable traffic, or those with No SNI; Untrusted certificate acceptance; etc. Use Custom URL Categories and individual rules for managing the different exceptions by type and following practice guidance within the organization. | (As Needed) |
  | 11 | Inspect All | Criteria: None (Inspecting everything not exempted previously) | Inspect: Untrusted Server Certificates: Block; OCSP Revocation Check: Enabled; Block Undecryptable Traffic: Enabled; Minimum Client TLS Version: TLS 1.2; Minimum Server TLS Version: TLS 1.2 |
  | Default | Default Rule | N/A | Do Not Inspect: Evaluate other policies. |
7. Consider the following when building policy:
  - **User Attributes (Group/Department)**:
    - This works only if the traffic forwarded to the Zscaler service is routed by the Zscaler Client Connector (preferred) or if [Enforce Surrogate IP](https://help.zscaler.com/zia/about-surrogate-ip) is enabled for the locations and traffic is flowing through a GRE/IPSec tunnel to the Zscaler service.
    - Reason: Without Zscaler Client Connector, or **Enforce Authentication + Surrogate IP**, the service cannot identify the user (before inspection) to properly match and apply user-based policies.
  - **Device Groups (OS Type)**: The Device OS Type is only identified if traffic is forwarded to the Zscaler service via the Zscaler Client Connector; otherwise it is flagged as **No Client Connector**.
  - **Location Groups**:
    - Use location groups whenever possible to more easily manage and maintain groupings of locations for various purposes. For example, a list of locations to be inspected (e.g., Locations_SSL) and not inspected (e.g., Locations_NoSSL).
    - Use the location group as criteria in policy, instead of individually identifying locations, where location-based logic is necessary.
    - When defining new locations to the system, ensure they are added to the proper location group to automatically apply all relevant policies, instead of needing to update all relevant rules individually.
8. Communicate with pilot users.
  1. Plan the user pilot and make adjustments.
  2. Modify policy to meet the needs of new pilot testers during each phase.
    - As testers continue their work, collect their feedback and adjust policy as necessary to account for any situations where critical applications or services are broken by inspection.
    - Use an FQDN or Cloud Application where possible to avoid exempting traffic based upon IP address.
    - Properly investigate, understand, and document the reasons for inspection bypass of each application or service.
      - This documentation assists in ongoing operations and maintenance of policies.
      - To learn more, see Handling Exceptions.
    - Repeat pilot steps as necessary to cover all initially planned criteria and phases, and to include any new use cases discovered during the pilot.
9. Expand deployment:
  1. Expand your policy configuration to include all the leading practice policy configurations needed to cover your successfully tested use cases.
  2. Enable inspection for more locations, user groups, destinations, and devices as appropriate.
  3. Continue to Investigate any reported issues, and document exceptions to keep the business moving forward.
10. Measure and report. To learn more, see Reporting.
11. Continue to identify additional use cases and work through necessary testing to attain the organization’s goals. Inspect as much of the TLS-encrypted traffic as possible.
12. Leverage web insights with the filter Client SSL Handshake Failure Reason to identify transactions that failed SSL inspection and investigate for the proper action to take. See image.

### Deployment Checklist

Zscaler recommends downloading the [Internet & SaaS SSL Inspection Leading Practices Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/zia-ssl-inspection-leading-practices/ZIA-SSL-Inspection-Leading-Practices-Checklist.pdf) to help plan and implement Internet & SaaS SSL Inspection: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zia-deployments-operations/zia-ssl-inspection-leading-practices/ZIA-SSL-Inspection-Leading-Practices-Checklist.pdf).

#### Considerations

Review the following considerations when deploying TLS inspection.

##### Policy Guidelines

Zscaler recommends an SSL/TLS inspection policy set which makes granular exemptions only as necessary, and has a default Inspect action on any remaining traffic. Consider the following when configuring policy:

- Build with the intention of Opt-Out in mind. Define a final rule to inspect everything not explicitly exempted by an earlier rule. Why: Any new groups, applications, categories, or locations are automatically inspected until configured otherwise.
- Reference User Groups instead of individual users.
  - When you must set a policy for specific users, it is commonly easier to use Groups.
  - Why: Groups help make policies easier to manage by keeping the identity provider (IdP) as the source of truth on membership and therefore policy matching.
- Rule order matters.
  - Rules are evaluated in a top-down, first-match style.
  - If something needs to be inspected and is otherwise exempted, insert an inspection rule before the exemption rule.
  - For example, if you must inspect all devices actively running Zscaler Client Connector, even if they are coming from locations which should not be inspected, build one rule to inspect the Device Groups running Zscaler Client Connector and then another rule to exempt the locations or location group as necessary.
- Simple is generally better.
  - Configure with a logical structure in mind.
  - If granular rules are necessary, insert them appropriately within the structure, and keep the quantity of rules minimal (unless the environment specifically calls for more complexity).
  - Why: A straightforward and structured policy configuration is easier for admins to understand and maintain.
- Avoid using **Bypass Other Policies** for any custom exemption rules. Why: In addition to not inspecting the SSL/TLS connection, any traffic matching the criteria in a **Bypass Other Policies** rule ignores Cloud App Control and URL Filtering policies and is **Allowed**.
  - Test the Pilot and Production phases with appropriate granularity.
  - TLS inspection can be tested and deployed by more than just user groups and locations.
  - Targeting specific URL categories is usually less disruptive to production environments when already fully deployed to the Zero Trust Exchange (ZTE).
  - One approach is to test inspection for categories which are less business-critical, before moving to the more critical ones, to help validate that inspection works while reducing the risk of production issues.

To learn more, see [Best Practices: Testing and Rolling Out SSL Inspection](https://help.zscaler.com/zia/best-practices-testing-and-rolling-out-ssl-inspection).

##### Handling Exceptions

In rare cases, TLS inspection might cause applications not to function as expected or at all. The most common reasons for decryption failures and inability to inspect are:

- The device is missing the proper Root CA certificate.
- Expired or incorrect certificate for the site or application.
- Certificate pinning by an application. To learn more, see Certificate Pinning or Hard-Coded Certificates.

Traffic using a protocol that the Public Service Edge for Internet & SaaS doesn’t understand and/or cannot decrypt.

Zscaler recommends that organizations perform a thorough review of each site, system, and service that is not compatible with inspection. If the organization legitimately needs the traffic, and the risk of not inspecting this traffic is accepted, then specific exemptions should be made to tightly restrict access to the necessary domains from only the required sources whenever possible.

###### Expired or Incorrect Certificates

When a certificate is expired or incorrect, allowing users to proceed and access the application risks the user visiting a malicious site. Legitimate sites, barring unforeseen issues, keep their certificates up-to-date. While users might be inconvenienced by an inaccessible site or application, prioritize their safety and that of the organization.

A certificate’s components must be correct and match the server information. If a failure occurs, you can choose to Allow, Caution, or Block the connection. Zscaler recommends blocking access until the certificate issue is addressed on the server side.

Zscaler also supports Online Certificate Status Protocol (OCSP) revocation checks. When enabled, the Public Service Edge for Internet & SaaS checks the revocation status of the certificate. It makes this check even if the certificate is correct in all other aspects. If the issuing CA revokes the certificate, the Public Service Edge for Internet & SaaS treats the certificate as an untrusted certificate. The same action you choose for failed trust certificates applies to an OCSP failure.

Zscaler generally recommends dropping all traffic associated with bad certificates and enabling OCSP for all inspection rules.

###### Certificate Pinning or Hard-Coded Certificates

Certificate pinning, or hard-coded certificates, is another type of exemption. These are based on the client application, not the server. Certificate pinning is different from HTTP Public Key Pinning (HPKP).

In certificate pinning, the application is hard-coded with a server certificate and treats any other certificate as invalid. This prevents MiTM attacks, but it also prevents trusted MiTM from being possible.

This method is common with iOS and Android applications, which can make it more difficult to manage those environments.

The industry is deprecating certificate pinning due to certificate issues causing a loss of access. Application vendors, as with public CAs, are moving to shorter lifetimes for their intermediate CAs. Those developers who persist with certificate pinning are raising the cost of certificate maintenance and risking users losing connection with their service.

If possible, look to replace the application if you encounter certificate pinning. Bypass inspection on an application's traffic only if the application is of such high value to the organization that it is worth the risks associated with not inspecting the traffic. Otherwise, deny the traffic.

Additionally, it might be worthwhile to reach out to the application vendor and inquire as to why they have adopted certificate pinning. Ask them to discontinue the practice for the sake of customer security.

Many trusted teams are advising against certificate pinning. To learn more, refer to this [Sectigo blog post](https://sectigo.com/resource-library/what-is-certificate-pinning).

###### Undecryptable Traffic

The Public Service Edge for Internet & SaaS can decrypt most publicly documented encryption methods. Be suspicious of any traffic which cannot be decrypted. While it’s possible that the Zscaler service is unable to decrypt a particular protocol, it is more likely that the protocol is broken, not secure, and should not be used. For more information, see [Supported Cipher Suites in SSL Inspection](https://help.zscaler.com/zia/zscaler-ssl-tls-support).

Sites also sometimes use custom encryption protocols. Most often, this happens with nation-state actors using custom encryption due to classified communications. Those protocols are considered state secrets and are not available to the Zscaler service. It is rare to see custom encryption schemes if you are not a government employee or contractor. Block this traffic in most cases. If you use one of these protocols in your organization, you must bypass that traffic from inspection.

Zscaler recommends blocking undecryptable traffic unless your organization has a specific use case for communication.

###### Bypassing Inspection

Zscaler strongly recommends that you inspect all traffic and place an inspection exemption only in controlled and understood exceptions. Bypass inspection of traffic in specific circumstances. Choose to bypass traffic only after consulting your legal counsel for the impacted region. In general, bypasses are applicable only for specific functions such as:

- Banking and finance destinations.
- Healthcare destinations.
- Business functions requiring the use of undecryptable traffic.
- Business functions requiring the use of certificate-pinned sites or applications.
- Applications, such as certain parts of Microsoft 365, that have issues when inspected.

When creating inspection bypasses, try to keep the following guidance in mind:

- Zscaler recommends creating at least one URL category and/or IP and FQDN Destination Group specific to managing TLS exceptions.
- Avoid creating bypasses to resolve access issues without proper investigation and understanding. If an inspection bypass is used as a troubleshooting step, it should only remain in place if there is no other way to clear the issue AND the destination is sufficiently trusted.
- Avoid adding major domains to the list (Alexa top 100). Common exemptions to avoid are:
  - (.s3).amazonaws.com
  - (.blob.core).windows.net
  - .cloudfront.net
  - Similar major content delivery networks (CDNs) These are destinations where any subscriber can get their own file storage subdomain. For example, exempting these domains from inspection means no inspection occurs for any AWS S3 or Azure Blob storage account, and thus they should not be exempted without serious consideration.
- Avoid adding IP addresses in favor of FQDNs or domains.
- Avoid trying to manage domains if a matching Cloud Application exists.
- While adding domains, try bypassing the most specific domains instead of wildcards (i.e., add `corp.safemarch.com` and `eng.safemarch.com` instead of `.safemarch.com`).
- Do not add file paths or anything other than the FQDN (i.e., `safemarch.com/games/`), as anything beyond the domain is not visible without first inspecting the communication.
- Document your bypass entries and reasoning within your documentation system for future reference during the audit and review.
- Except for compliance and privacy reasons, ensure you understand why a bypass is necessary (open a ticket with your vendor or partner).
- In very rare cases when SIPA is combined with an application that uses XFF headers to understand source IP, you can use an inspection bypass to avoid Zscaler XFF header injection. You should only use this if the destination is sufficiently trusted.

General guidance is that all bypass entries should be reviewed at least annually to ensure they are still required and approved.

##### QUIC Protocol

Google developed the Quick UDP Internet Connections (QUIC) protocol to speed up internet connectivity on its browsers and devices. This is accomplished by skipping the TCP handshake and using UDP instead. However, Zscaler’s TLS/SSL inspection relies on TCP session information, so Zscaler recommends blocking Google QUIC. When blocked, the browser or device falls back on TCP connections.

The block rule should use a Block/ICMP action, instead of Block/Drop or Block/Reset, as the ICMP response more quickly triggers the application to fall back to TCP. Zscaler’s firewall and Zscaler Client Connector both support blocking QUIC connections. You can also block this protocol using conventional branch office firewalls if needed. To learn more, see [Managing the QUIC Protocol](https://help.zscaler.com/zia/managing-quic-protocol).

##### Developer Environments

Developers often need rapidly changing access, and use tools which might not integrate well with TLS Inspection. However, developers often have access to core resources and production data, meaning they present a risk to an organization for data exfiltration and cyber security. It's important to get the balance correct for the company to be secure, and for developers to have agile working practices. Developers often need direct internet access to their customer-facing applications, especially if they still use certificate pinning. Developers must ensure their applications function as expected, the certificates are correct, etc. Development environments might also have local development resources that include custom DNS and mail servers. One of the most common barriers to deploying TLS inspection for developers is that many developer tools use custom certificate stores which are addressed in the next section.

Work with your development teams to understand their workflow. Development teams are ideal candidates for their own department or group label. You can exempt their workloads from inspection (or even from Zscaler forwarding all together), allowing for development and testing. However, it should be clear that this is a risk to the organization. Have a plan to limit devices in a developer test lab from accessing or being accessed by the rest of the organization.

Create a set of URLs that support their development. If you allow your developer teams to deploy resources, consider using wildcard certificates to bypass specified sites not only from inspection but from any bandwidth constraints.

For initial deployment, engage with the developer community early to ensure they understand the journey and give them an opportunity to contribute to the plan. Deploying TLS inspection where there was previously none causes friction in their world. The most expedient way to deploy inspection is to engage with the development teams up front, include them in the deployment like the rest of the user community, and have a rapid response team to update configuration (such as exemptions, or bypasses) for unforeseen issues. Some organizations setup automation so that a development team member can open a service ticket to submit a destination for bypass, which is then automatically pushed to the Zscaler configuration to allow immediate access. This ticket then also triggers review by the security team to ensure that the configuration is an acceptable risk.

This path can cause too much friction within some organizations. Another option would be to use group attributes to identify the developer community and slow-role TLS inspection to those users. This path can take longer to achieve maximum coverage, but can reduce impact on developers and support staff during initial deployment.

For all other traffic, your developers should have the same policies as others in the organization. Order the development-specific rules higher in the list than the general employee rules to ensure that the rules for general users don’t interfere with permissions for developers.

To learn more about specific strategies in managing inspection within developer environments, see the [Software Developer Solutions Guide](https://www.zscaler.com/resources/white-papers/software-developer-tls-guide.pdf).

##### Custom Trust Stores

Every OS has a default system root CA certificate store. Some applications host their own certificate trust store and might also need to be updated. Micro-services, containerized apps, developer environments, and security tools are common examples. For TLS inspection to work, the certificate chain must be pre-deployed, which means some of these environments need direct intervention to be compatible.

Zscaler recommends handling the applications with custom trust stores by installing the Zscaler root cert (or custom intermediate cert) into the application’s cert store. This is done manually or automatically via a tool like Microsoft System Center Configuration Manager (SCCM).

You can also disable cert validation, which Zscaler recommends only for short terms and testing. Alternatively, you can exempt the traffic from developer users (which is less desirable) from TLS inspection. You can also exempt the destination domains to which these apps connect. To learn more, see Developer Environments.

Additionally, ChromeOS devices use Custom Trust Stores. ChromeOS doesn’t trust any managed or user-installed certificates for OS-level communications. As such, you can only inspect user traffic, and you must exempt the traffic between the device and Google’s ChromeOS services. Put the list of required exemptions by domain into a custom URL category and then bypass the custom URL category from inspection. For instructions on deploying a custom certificate to ChromeOS devices, refer to the [Google Help Center](https://support.google.com/chrome/a/answer/3505249).

To learn more, see [Adding Custom Certificates to an Application-Specific Trust Store](https://help.zscaler.com/zia/adding-custom-certificate-application-specific-trusted-store).

##### Mobile Devices (iOS/Android/Android on ChromeOS)

Installing the certificate on mobile devices requires Mobile Device Management (MDM) and is also commonly the best way to deploy the Zscaler Client Connector application.

Inspection for all traffic is not always possible. To learn more, see Certificate Pinning or Hard-coded Certificates and Custom Trust Stores.

##### Handling Servers and other Workloads

Enforcing TLS inspection for servers can be challenging, depending on the environment, management schema, and services used. Due to the potential complexity, server networks are typically excluded from the initial TLS inspection projects and are deployed when production user inspection is mostly completed. Inspection of workload traffic is a critical component when securing an enterprise.

Regarding servers, the primary challenge is getting the root CA certificate installed on the host machines, as well as any containers and applications which make use of Custom Trust Stores. To learn more, see Custom Trust Stores. Adding the requisite certificate to the trusted CA store should be handled in a similar process to other infrastructure or DevOps changes in an environment.

Zscaler recommends restricting server access to explicitly permitted destinations and blocking all other access, as this can greatly assist in reducing risk from non-inspection until or after inspection is enabled.

##### BYOD and Guest Networks

BYOD and guest network traffic are generally impossible to inspect because the Root CA Certificate cannot be installed on these devices. Ensure any BYOD and guest networks are completely segmented away from your organization’s network with no chance of accessing anything other than the internet.

##### IoT Devices

Internet of Things (IoT) devices are in a similar state to BYOD and guest networks. While you might be able to manage and configure them, most of them don’t accept modifications to the certificate trust store. If your devices are compatible, then inspection might still be possible, though it is likely that a custom policy is required along with the creation of one or more sublocations specific to these nodes. Other components of the Zero Trust Exchange should be used to secure these traffic flows (restrict destinations & applications that these devices can communicate with, etc.).

##### Minimum Protocol Versions (TLS Version Enforcement)

Internet & SaaS supports many protocols and ciphers when inspecting TLS/SSL. As you use inspection, the Internet & SaaS service reports on which protocols and ciphers are used by your clients and infrastructure. To learn more, see [Supported Cipher Suites in SSL Inspection](https://help.zscaler.com/zia/supported-cipher-suites-ssl-inspection).

Zscaler recommends initially allowing all supported protocols and ciphers to minimize user disruption. During this time, you can discover what is in use on your network and which services or platforms still use legacy encryption. Using the provided dashboards, you can discover which applications use legacy security and move to strengthen your requirements for connections.

The Zscaler Admin Console supports a display of the versions of TLS and SSL used in your organization. Using this information, you can upgrade or disable services still tied to legacy encryption suites. Zscaler recommends blocking all TLS versions earlier than 1.2, including any legacy SSL services, noting that some services might require special exception rules until they can be upgraded.

### Planning Guidance: Pilot to Production

A comprehensive pilot makes expansion fairly straightforward. If the pilot covers enough business units and use cases, then expanding to more users is generally not too challenging. It all comes down to planning, communication, and execution. The next sections cover some of the necessary decisions and provide baseline recommendations to get you successfully through your pilot phases and into production.

#### Pilot Planning

A project as significant as TLS inspection must begin with careful planning and multiple rounds of piloting before an organization can confidently push out to the entire environment. For piloting, Zscaler recommends starting with identifying the use cases you want to cover, then selecting who is included in each use case, for what traffic inspection is enabled, and planning the stages, timing, and success criteria for each.

##### Identifying Use Cases

Use case identification is done by considering the environment. The best way to assemble this list is by thinking about where your users and traffic live. Consider these pieces:

- Are all users working remotely, or are they in the office?
- Is the Zscaler Client Connector installed on your workstations?
- Is there a mobile device fleet (Android and iOS) to be protected?
- Are there critical services that must be tightly controlled?
- Who has access to the most sensitive services or information?
- What kind of compliance requirements must be met?
- What are the servers allowed to communicate with externally?

Let the answers to these questions guide you toward the sources (users, servers, devices, etc.) that need protection, as well as how their traffic communicates with the Zscaler service.

##### User Selection

Understanding the use cases in your environment leads you to align users (both pilot and production) to each use case. For each of your use cases, select users to act as a pilot group. Which users make good pilot testers?

The first users to onboard in the pilot group must understand that they might experience some issues and disruptions. Select a group of users that can handle interruptions. You don’t want to include users at a critical time of year in either their product development or regulatory calendars. While you can rapidly roll back changes to policy, try to avoid that scenario. Select a minimally vulnerable group and ensure that you have that group’s full cooperation before starting the pilot program.

Don't include IT or development staff in the pilot group because IT and development users often try to work around issues before reporting them. Additionally, their work often entails operating in an environment that does not match that of the average user. Ensure the pilot group consists of a diverse set of average users in an office location, business unit, or small region. The more job functions this group performs, the more applications will be tested and the more notifications you’ll receive. You also want feedback on your notification copy to see if it’s clearly conveying the required information and the escalation process for resolving issues.

Ensure that your pilot group knows how the escalation process works. Ideally, have a representative of your security or IT team offer a presentation about inspection and escalation and perhaps be available for real-time communication should an issue arise. Make sure that you have someone in place to make real-time changes or implement a quick rollback of policy. Zscaler makes it simple to roll back policy quickly, and direct communication with your security team is vital for the successful support of the pilot.

The pilot group must be identified by the policy engine so that the appropriate rules are applied. There are several options for you to select from depending on your needs, including user and location options.

When you start your policy selections, you will likely expand the policy to apply to more users. You might rewrite the policy as you learn more about your users and application needs. When you are building out policies for long-term use:

- If you have a policy that applies to many of your users no matter where they are, focus on the user selection criteria via group.
- If the policy is only relevant based on location, such as required by a government mandate, choose the location selection criteria. You might also have criteria that apply only when users are in a particular location.

After your pilot group is onboard and operational, you can start to roll out an inspection to the rest of your organization. Plan for this rollout to move quickly, region by region, until all your users are inspected.

##### Traffic Selection

After designating users for your use cases, the next step is identifying what traffic to inspect. This is based on a selection of one or more source-based criteria as well as the selection of destination-based criteria. Doing this helps to define inspected traffic versus not-inspected traffic, either during the pilot phase or throughout the entire process.

For source-based criteria, consider what you know of the use case along with how you can build policy to explicitly target the sources:

- By user group or department (e.g., Operations, Sales, HR, Finance, etc.).
- By location or network (e.g., sublocation, remote workers, HQ, branch sites, building floors).
- By device type or purpose:
  - Windows, macOS, mobile OS, servers, kiosks, or IoT
  - Running Zscaler Client Connector or not

Consider if you need to separate employees, contractors, servers, kiosks, executives, developers, etc., into different policy sets or phases.

With the source in mind, consider which destinations to select for inspection. Will you:

- Inspect all destination URL categories and Cloud applications? What about categories discussed with your stakeholders regarding a sensitive and private nature (e.g., PII or health) or if categories might need to be exempted for legal reasons (e.g., finance, government)?
- Take a phased approach to select groups of URL categories per phase?
  - Start with destinations least likely to impact your business.
  - Start with those most likely to be risky.
  - Start with the largest (or lowest) by volume.

After understanding the source and destination criteria, consider which traffic should not be inspected. How should you construct a policy to ensure that this traffic is excluded for each of your use cases without overlapping other scenarios where the traffic should be inspected?

Perhaps the most common approach is to target all users running Zscaler Client Connector while in a remote worker (i.e., not on your trusted networks) state and then expand to cover Zscaler Client Connector users from any location. Eventually, this covers systems without Zscaler Client Connector that do have the certificate. Begin by inspecting URL categories less likely to impact business, and steadily inspect more categories as you receive feedback and gain confidence. To learn more, see [Deploying SSL Inspection](https://help.zscaler.com/zia/deploying-ssl-inspection).

###### Example Scenario

In the following scenario, the setup inspects all traffic from only Zscaler Client Connector users from location HQ (except for the Finance and Health categories), Microsoft 365 traffic (but does inspect OneDrive and SharePoint), and Zoom Cloud application traffic.

The following policy rules are built in the simplest way while permitting for more scenarios to be added later:

- Rule 1: Inspect if Cloud App is OneDrive or SharePoint, Device Group is Android, iOS, Windows, or macOS, and the Location is HQ
- Rule 2: Exempt Microsoft 365 traffic via Click-to-Run
- Rule 3: Exempt URL categories of Finance and Health, plus Cloud Application of Zoom
- Rule 4: Exempt if Device Group is No Client Connector for Location HQ
- Rule 5: Inspect all traffic from location HQ

You now have a policy that inspects only the target traffic and are prepared to build on this policy as you expand the deployment. To make things even easier to manage, you could use location groups to apply a similar policy:

- Build a Location Group that is only inspected when users run Zscaler Client Connector (e.g., `Locations_SSL_CltConEnforce` and add HQ and any other locations to this group).
- Modify the rules where you have referenced Location equals HQ and instead select the `Locations_SSL_CltConEnforce` for your Location Group criteria.
- Any new locations that follow the same schema need only be added to the proper Location Group, and policy is applied as appropriate.

You can also build a Location Group where all traffic is inspected (not just Zscaler Client Connector) and name it `Locations_SSL`, and a Location Group for any of your locations not yet in scope or never to be inspected, called `Locations_NoSSL`. Build a policy using these Location Groups as necessary, and the new location policies become a matter of assigning the location to the proper group.

##### Timing and Success Criteria

It’s a challenge to plan the timing of the project and its phases. Ideally, you want to move quickly to ensure your environment is protected from threats immediately. However, you must also give adequate time for testing, feedback, and learning at each stage.

Organizations that prioritize and understand the importance of TLS inspection can reasonably expect successful deployment of TLS inspection to most of their use cases within 45–90 days (although this timing depends on the environment’s size, complexity, and distance along their zero trust journey).

The timing of the project should consider the phases and use cases. Some of the most common considerations when building project timing are:

- Deploy to remote users before on-premises?
- Complete workstations before mobile devices?
- Target one operating system at a time?
- Deploy for Zscaler Client Connector devices first?
- Do the mission-critical devices first or last?
- When to address compliance requirements?
- When to do servers, kiosks, and/or IoT?

Set realistic schedules to permit adequate time for testing, feedback, and learning at each stage. Do not be afraid to move to expanded deployment in any use cases you have successfully tested, even if other use cases are still being explored. Expand where and when you are comfortable, making certain not to overlap into spaces not yet ready for production. As you expand into production, if significantly impacting issues are reported, be prepared to roll back a bit and consider the new scenarios to investigate and test.

Establish success criteria in the planning phase for pilot stages and production state.

Pilot criteria might be something like:

- Tested with 10 users for 5 business days.
- Addressed reported issues within two hours.
- Received fewer than three issue reports in the last two days of testing.

The idea is to keep it simple. Test some percentage of the use case base (i.e., 1–5 percent) for a set amount of time to give you confidence across most applications and services. Keep track of how many issues are reported for the test duration, along with how long it takes the team to investigate and resolve them. Monitor if the number of reported issues reduces dramatically (or stops) within the last small portion of the test window. If users are functional, reported issues are addressed, and things look good, then you can reasonably expect a production expansion in this use case to be similar.

Project success criteria are different. Base them on your discovered use cases, the expected percentage of traffic represented by these use cases, and what traffic is not covered under the project.

Project success criteria might look like this:

- 90%+ of the user base has traffic inspection enabled in some form.
- Reported issues below one per 100 users per day.
- 65%+ of all encrypted traffic is being inspected.

An expectation of a majority of the user base being inspected, reported issues being relatively low, and having a target for overall inspection is a reasonable way to judge success. You could expand upon this and report the additional threats detected with the added visibility of TLS inspection to the environment.

Remember, the goal is to inspect as much encrypted web traffic as possible. There must be a baseline of what is acceptable to the organization as a percentage of total traffic inspected–knowing that some traffic can’t be inspected.

Example:

If an environment has 15% traffic sent to exempted destinations (certificate pinned, much of it Microsoft 365, other desired or necessary exemptions), along with 20% of traffic being from sources to not be inspected (guest network, IoT, anything not in scope); then it can be expected that nearly 35% of traffic is not going to be inspected. As such, 65% is the absolute maximum inspection rate possible when considering all traffic from the environment.

##### Communication

Communication is key. You must let your users know changes are coming, typically with your updated Acceptable Use Policy, and give the users guidance on how to report any issues they might experience.

Review and evaluate feedback, lessons learned, and results at each stage. Share updates and reports with the key stakeholders to keep them informed and focused on the need and benefits of the project.

##### Risk Tolerance and Acceptance Plans

If you can’t inspect traffic, what criteria does the organization set to determine if the risk is acceptable? Will the traffic be permitted or blocked? Negotiate this guidance with stakeholders during the planning stage, as it will become necessary to act quickly on established plans when investigating and resolving reported issues.

Important considerations:

- The Zscaler service cannot see inside uninspected traffic, meaning any embedded threats (inbound or outbound) are not detected.
- Determine under what circumstances traffic is exempted from inspection and communications permitted:
  - Destinations exempted by privacy and legal requirements.
  - Business critical services (and what determines business-critical status).
  - Connections to partners or vendors.
  - Low-risk destinations (and what determines low risk).
- How to handle undecryptable traffic.
- How to handle revoked certificates.
- How to handle untrusted server certificates.
- Is there a minimum TLS version, and what should be done with exceptions?
- Is policy set globally or for some grouping (user, location, network, device, etc.)?

Establish your guidance early, and continue to update as you learn more about your environment and the services that require exception handling.

##### Root Certificates

Certificates play a critical role in securing web applications by confirming the validity of the applications. A user’s device trusts the certificate because of a chain of trust link showing the server certificate’s relationship to a trusted Certificate Authority (CA). The information contained in the certificate allows for the setup of secure connections with TLS/SSL using public-key encryption.

In Internet & SaaS, the Central Authority is a private CA, acting as both root and intermediate CA for the Internet & SaaS service or as an intermediate to your private CA. The Central Authority authorizes the Public Service Edge for Internet & SaaS to act as intermediate CAs, issuing certificates to end users for their requested destinations.

Generally, preventing interception is what you want. Having a third party be able to impersonate a legitimate service with a valid certificate allows the impersonator to see into the transactions. When a legitimate TLS/SSL inspection occurs, impersonating all the destinations a user wants to visit is exactly what happens. The Internet & SaaS proxy must sit in the traffic flow and must present itself as the legitimate service the user is attempting to achieve.

To do this, the Public Service Edge for Internet & SaaS acts as a short-lived intermediate CA. As a user requests a connection, the Public Service Edge for Internet & SaaS issues certificates on demand for the application. From the browser’s point of view, the Public Service Edge for Internet & SaaS certificate is valid for the destination. This is because the user or administrator imported the Public Service Edge for Internet & SaaS trust chain into the device’s certificate store.

Zscaler provides three options for root CA certificates: using your own certificate authority, using your own CA with Zscaler Cloud Hardware Security Model (HSM), or leveraging Zscaler’s CA. From a functional standpoint, the two options are equivalent, but there are differences in the deployment of the root CA. The option you choose depends on your current infrastructure. If you already have your own CA infrastructure in place, you might prefer to use your own certificates. Your client machines are already preloaded with your root CA certificate, and they can begin to use the Internet & SaaS service immediately without additional certificate installations in the client certificate store. To learn more, see [Safeguarding SSL Keys](https://help.zscaler.com/zia/safeguarding-ssl-keys-and-data-collected-during-ssl-inspection).

If you don’t have a robust CA practice in place already, or don’t want to make Zscaler an intermediate CA to your organization, Zscaler's CA is the best choice. Zscaler CA is a private CA. You must install Zscaler's root CA in your machines’ certificate stores for the devices to trust the Zscaler Root CA certificate.

Using your existing CA is possible with licensing.

##### Reporting

Implementing TLS inspection for an environment is typically done in phases and over an extended time. During these phases, and onward into operations, the ability to report on various progress metrics is important.

Details to find commonly desired views are as follows.

###### Security Policy Audit Report

View an overall summary of TLS traffic inspection:

1. Go to **Analytics** > **Internet & SaaS** > **Security Policy Audit Report**.
2. Select the SSL/TLS Inspection row to expand the details. See image.

###### Interactive Reports

This reporting section includes predefined and custom reports:

1. Go to Analytics, and enable **Switch to Existing Reports.**
2. Go to **Internet & SaaS** > **Analytics** > **Interactive Reports**.
  1. Traffic Distribution by Protocol, which includes: See image.
    1. Traffic by protocol
    2. Threats by protocol
    3. Sandbox threats
    4. Top applications by protocol
  2. SSL Traffic Overview, which includes: See image.
    1. Top ciphers
    2. SSL/TLS versions

###### Web Insights

The Web Insights section is helpful in providing aggregated data and in further narrowing results before drilling into additional insights and/or logs. Web Insights is reached by going to **Logs** > **Web Insights**.

TLS inspection efforts can be enhanced with the filtering of various data sections by protocol being either HTTPS or SSL; indicating whether traffic was TLS inspected or not inspected, respectively.

Example data types to check:

- Threat Category
- Download/Upload File Type
- Cloud Applications
- URL Categories See image.

##### Building an Inspection Policy

Understanding the process of creating an TLS Inspection policy is crucial for proper implementation and ongoing operations of an inspection program. Fortunately, policy creation is fairly straightforward and similar to policy creation in other areas of Internet & SaaS.

1. Add an SSL Inspection Rule by going to **Policies**> **Common Configuration** > **SSL/TLS Inspection** >**SSL/TLS Inspection Policy**.
2. Click **Add Rule**. See image.
3. Configure the rule as needed. To learn more, see [Configuring SSL/TLS Inspection Policy](https://help.zscaler.com/zia/configuring-ssltls-inspection-policy).
4. **Save**and Review Rule. Rule order matters. See image.

To learn more, see [Configuring SSL Inspection Policy](https://help.zscaler.com/zia/configuring-ssl-inspection-policy).

[Image: How to add an SSL rule]

[Image: Why Use SSL?]

[Image: Why Inspect?]

[Image: Tools dependent on SSL inspection]

[Image: Building policies]

[Image: Select filters to leverage web insights]

[Image: Policy audit reporting]

[Image: Traffic distribution by protocol]

[Image: SSL traffic overview]

[Image: Create an SSL inspection rule]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/zpa-disaster-recovery-deployment-and-operations-guide","lastmod":"2025-08-06T23:32Z","nid":"1451411"} -->
## Disaster Recovery Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/zpa-disaster-recovery-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZPA Deployments & Operations > Disaster Recovery Deployment and Operations Guide
- Last modified: 2025-08-06T23:32Z
- Summary: Describes the benefits of and the steps necessary to enable Zscaler Private Access (ZPA) disaster recovery.

This guide describes the benefits of using disaster recovery and the steps necessary for configuring Zscaler Private Access (ZPA) to add disaster recovery to your security posture.

ZPA disaster recovery ensures business continuity if an event impacts the global Zscaler cloud infrastructure. Disaster recovery provides an organization's users access to critical applications by ensuring access even if the Zscaler cloud isn’t accessible.

To learn more, see [Understanding Disaster Recovery](https://help.zscaler.com/zpa/understanding-disaster-recovery) or [Zscaler Resilience](https://www.zscaler.com/zscaler-resilience).

## Value of Deploying ZPA Disaster Recovery

ZPA disaster recovery provides the following benefits when Zscaler cloud infrastructure is completely unavailable:

- Business continuity with uninterrupted access to critical business applications in case of a global ZPA cloud infrastructure outage.
- Avoid costly business interruptions or loss of productivity due to a lack of access to critical business applications.

## Deployment Phase

The deployment phase includes initially setting up and integrating Zscaler solutions into an existing network infrastructure. During the deployment phase, you configure disaster recovery to meet the needs of your infrastructure.

The following sections discuss steps to deploy disaster recovery in ZPA.

### Prerequisites

ZPA disaster recovery might require additional licenses. Check with your Zscaler Account team to see if you have the necessary licensing requirements.

For ZPA disaster recovery deployment, verify and complete the following prerequisites:

- Enable the disaster recovery feature. Check with your Zscaler Account team to enable it.
- Identify which [critical application segments](https://help.zscaler.com/zpa/configuring-application-segments#define-generalinfo), [App Connector Groups](https://help.zscaler.com/zpa/configuring-connectors#createconnectorgroup), and [ZPA Private Service Edge Groups](https://help.zscaler.com/zpa/configuring-service-edges#addgroup) to designate for disaster recovery use.
- Deploy or identify which [App Connector](https://help.zscaler.com/zpa/app-connector-management/app-connector-deployment-guides-supported-platforms) disaster recovery uses.
- Deploy or identify which [ZPA Private Service Edges](https://help.zscaler.com/zpa/private-service-edge-management/private-service-edge-deployment-guides-supported-platforms) disaster recovery uses. Check with your Zscaler Account team if you are eligible to use ZPA Private Service Edges.
- Ensure the end user’s machines run [Zscaler Client Connector](https://help.zscaler.com/client-connector/downloading-zscaler-client-connector) version 4.0 or later for Windows.
- Ensure the end user’s machines run [Zscaler Client Connector](https://help.zscaler.com/client-connector/downloading-zscaler-client-connector) version 3.7.1.38 or later for macOS.
- Create a public DNS record that is customer-owned and modifiable to enable and disable disaster recovery.
- Download the [Zscaler DNS Record Generator](https://help.zscaler.com/zpa/about-zscaler-dns-record-generator#install) (Windows only).
- Create a separate domain name in your DNS for disaster recovery. Then enter the public IP addresses of the ZPA Private Service Edge designated for disaster recovery into the DNS A record.

### Deployment Steps

The following sections cover deployment instructions for ZPA disaster recovery:

1. Ensure that you’ve met the prerequisites.
2. [Configure disaster recovery for an application segment](https://help.zscaler.com/zpa/configuring-application-segments#define-generalinfo).
3. Configure disaster recovery for an [App Connector group](https://help.zscaler.com/zpa/configuring-connectors#createconnectorgroup).
4. [Configure disaster recovery for a ZPA Private Service Edge group](https://help.zscaler.com/zpa/configuring-service-edges#addgroup).
5. Configure the [disaster recovery settings](https://help.zscaler.com/zpa/about-disaster-recovery-settings).
6. Capture all the public IP addresses of the ZPA Private Service Edges designated for disaster recovery and add them to the record for the disaster recovery domain name. You can find public IP of the ZPA Private Service Edge in [About ZPA Private Service Edges](https://help.zscaler.com/zpa/about-zpa-private-service-edges).
7. Configure the disaster recovery settings for an App Profile in Zscaler Client Connector. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).

In Disaster Recovery Test Mode, App Connector in an App Connector group restart their zpa-connector services and Private Service Edges restart their zpa-service-edge service. When the services are restarted, user connections to the test App Connector and Private Service Edges are dropped. Unless part of the test group, new connections are not established with the Test Mode App Connectors and Private Service Edges. To learn more about how to enable disaster recovery test mode, see [Configuring Disaster Recovery Test Mode](https://help.zscaler.com/zpa/configuring-disaster-recovery#:~:text=Configuring%20Disaster%20Recovery%20Test%20Mode).

### Considerations

Review the following considerations:

- Activate disaster recovery via the DNS records with caution, as it disrupts existing Zscaler Client Connector connections. The services for both App Connector (zpa-connector) and ZPA Private Service Edges (zpa-service-edge) that are enabled for disaster recovery restart after disaster recovery is activated via DNS.
- An application segment with disaster recovery enabled must be associated with a server group. The server group must be associated with at least one App Connector Group designated for disaster recovery.
- Ensure you choose the lowest possible TTL (Zscaler recommends 30 seconds) for swift DNS update propagation.
- When disaster recovery is triggered, App Connectors and Private Service Edges designated for disaster recovery usage cut connections to the Zscaler cloud and only provide access to the applications that have been designated for disaster recovery usage.
- Clients that have disaster recovery triggered only access applications designated for disaster recovery usage as long as disaster recovery is enabled. Even after ZPA services are restored, the client machines do not automatically reconnect to the Zscaler cloud unless disaster recovery is disabled.
- Disaster recovery supports the following functions and features:
  - Access is allowed only through the Zscaler Client Connector.
  - Application segments designated for disaster recovery are accessible when Disaster Recovery Mode is activated.
  - Existing and disaster-recovery-generated App Connectors, ZPA Private Service Edges, and Zscaler Client Connectors with valid and unexpired certificates.
  - Users enrolled in the ZPA Cloud prior to enabling disaster recovery.
- Disaster recovery doesn’t support the following functions and features:
  - Authentication and reauthentication. (Reauthentication behavior is modified during disaster recovery. By default, the Security Assertion Markup Language (SAML) assertion validity is extended by 14 days from the date the SAML assertion was issued. The SAML assertion is issued when a user reauthenticates. You can customize the value.)
  - [Browser Access](https://help.zscaler.com/zpa/about-browser-access).
  - [Client-Based Remote Assistance](https://help.zscaler.com/zpa/about-client-based-remote-assistance).
  - [Cloud Connectors](https://help.zscaler.com/zpa/about-cloud-connectors).
  - [Dashboards and Diagnostics](https://help.zscaler.com/zpa/dashboard-diagnostics).
  - Enrollment of new [App Connectors](https://help.zscaler.com/zpa/about-connectors), [ZPA Private Service Edges](https://help.zscaler.com/zpa/about-zpa-private-service-edges), or [Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/what-is-zscaler-client-connector).
  - [AppProtection for Private Application Traffic](https://help.zscaler.com/zpa/appprotection-private-application-traffic-formerly-inspection).
  - [Log Streaming Service](https://help.zscaler.com/zpa/log-streaming-service).
  - [Machine Tunnels](https://help.zscaler.com/zpa/deploying-machine-tunnels-pre-windows-login).
  - [Policies](https://help.zscaler.com/zpa/policies)or configuration updates.
  - [Source IP Anchoring](https://help.zscaler.com/zia/about-source-ip-anchoring).
  - [SCIM Configuration](https://help.zscaler.com/zpa/about-scim).
  - User enrollments.
  - [Zscaler Deception](https://help.zscaler.com/deception/what-zscaler-deception).

## Operations Phase

This section describes common practices used to operate Zscaler solutions when integrated with your environment. You can enable, monitor, and tune ZPA disaster recovery to meet your infrastructure needs.

After performing the deployment steps, customers must adjust the DNS TXT record according to the format guidelines described in [About the DNS Record Generator](https://help.zscaler.com/zpa/about-zscaler-dns-record-generator) to enable disaster recovery. At a minimum, ensure the following tags are set:

- v=1 to indicate the DNS record version.
- b=on to enable disaster recovery.
- k=zpa or k=all (for ZPA and ZIA).

Choose the lowest TTL for the DNS TXT record (Zscaler recommends 30 seconds) for swift DNS update propagation downstream.

For testing, make the following DNS TXT adjustments:

- v=1
- b=test
- k=zpa or k=all (for ZPA and ZIA)

In Disaster Recovery Test Mode, all App Connectors in an App Connector group that are designated for disaster recovery restart their zpa-connector services, and all ZPA Private Service Edges in a ZPA Private Service Edge group that are designated for disaster recovery restart their zpa-service-edge services. When the services restart, existing user connections to the test App Connectors and ZPA Private Service Edges that are designated for disaster recovery are dropped. New connections for regular users that are not part of the disaster recovery test group are routed through either ZPA Public Service Edges or regular ZPA Private Service Edges and App Connectors that are not designated for disaster recovery. Users participating in disaster recovery testing that are part of the disaster recovery test group also form new connections to the ZPA Private Service Edges and App Connectors that are part of the test mode. To learn more about how to enable disaster recovery test mode, see [Configuring Disaster Recovery Test Mode](https://help.zscaler.com/zpa/configuring-disaster-recovery#:~:text=Configuring%20Disaster%20Recovery%20Test%20Mode).

### Common Troubleshooting Items

- During ZPA global outage, customers should assume that the ZPA Admin Portal is unavailable and configuration changes, client enrollments, or new user authentication are not possible.
- Zscaler Client Connector checks the DNS TXT record every 200 seconds.
- Consider DNS propagation times when updating the DNS TXT record and waiting for Safe Mode to trigger in the Zscaler Client Connector.
- You can run the journaltctl -f command on the ZPA Public Service Edge. Look for the message to confirm that disaster recovery is successfully triggered: `DR mode is activated!`
- To learn more about App Connector troubleshooting information and guidelines, see [Troubleshooting App Connectors](https://help.zscaler.com/zpa/troubleshooting-app-connectors).
- To learn more about ZPA Private Service Edge troubleshooting information and guidelines, see [Troubleshooting ZPA Private Service Edges](https://help.zscaler.com/zpa/troubleshooting-zpa-private-service-edges).

## Deployment and Operations Checklist

Zscaler recommends downloading the [ZPA Disaster Recovery Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zpa-deployments-operations/zpa-disaster-recovery-deployment-and-operations-guide/ZPA-Disaster-Recovery-Deployment-Operations-Checklist.pdf) to help plan and implement ZPA disaster recovery: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zpa-deployments-operations/zpa-disaster-recovery-deployment-and-operations-guide/ZPA-Disaster-Recovery-Deployment-Operations-Checklist.pdf)

## Additional Information

For more disaster recovery information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](http://community.zscaler.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/zpa-leading-practices-guide","lastmod":"2026-06-05T15:51Z","nid":"1452301"} -->
## ZPA Leading Practices Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/zpa-leading-practices-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > ZPA Deployments & Operations > ZPA Leading Practices Guide
- Last modified: 2026-06-05T15:51Z
- Summary: The ZPA Leading Practices Guide provides a set of best practices for configuring and deploying Zscaler Private Access (ZPA) in an organization's environment.

The Zscaler Private Access (ZPA) Leading Practices Guide provides a set of best practices for configuring and deploying ZPA in an organization's network environment.

ZPA is a cloud service that provides seamless Zero Trust access to private applications running on a public cloud or within the data center. Zero Trust has become a popular model for secure user access to applications and resources using an approach that focuses on granular user-to-app segmentation.

With ZPA, applications are never exposed to the internet, making them completely invisible to unauthorized users. The service enables applications to connect to users via inside-out connectivity versus extending the network to them.

To learn more about ZPA, see [What is Zscaler Private Access?](https://help.zscaler.com/zpa/what-zscaler-private-access)

This document shares ZPA leading practice guidelines for a successful Zero Trust implementation.

## Deploying ZPA App Connectors

App Connectors provide a secure authenticated interface between a customer’s servers and the Zscaler cloud. The following sections provide best practices for deploying and managing App Connectors.

### High Availability and Scalability

When planning high availability and scalability:

- To guarantee high availability, administrators must ensure that App Connector groups are scaled to provide N+1 redundancy. A single App Connector can handle up to 500 Mbps (total inbound and outbound) throughput. You must add more App Connectors if you need more throughput.

For example, if you require 1 Gbps throughput, configure at least three App Connectors. Using the correct number of App Connectors to meet throughput needs ensures good user quality of service.

- Zscaler frequently automatically updates App Connectors during the life cycle of the ZPA service. Customers should select off-hour time slots for upgrade operations to minimize the impact on the service. ZPA cloud sequentially upgrades members of an App Connectors group to reduce service interruptions during the upgrade window.

To help maximize user experience:

- Zscaler leverages Machine Learning (ML) to properly optimize user-to-application connectivity. The ZPA cloud automatically balances all available connectors by taking into account many factors, such as proximity to users, availability, scale, etc. To ensure correct calculations and user-app assignments for App Connector groups, the administrator configures the following groupings when setting up ZPA:
  - Connectors in a connector group must share the same geographic location (latitude and longitude). This ensures that path selection works as intended. Zscaler recommends using location-based connector groups all the time. Not using location-based connector groups adversely impacts location-sensitive apps. That is, Active Directory (AD) domain controllers used for distributed file servers (DFSs) and other AD services where the user is associated to a local instance.
  - To ensure users are connected to a location-appropriate app, place all connectors at a location in the same connector group. For example, all connectors at site A are in connector group A, and all connectors at site B are in connector group B, etc.
- Zscaler recommends defining a connector group for each logical domain of applications. For example, define a separate connector group for a single AWS virtual private cloud (VPC), a single data center, and a data center segment that is isolated within a security boundary, etc.
- Connectors must resolve Domain Name Service (DNS) for applications that users access by hostname and must reach those applications on the appropriate TCP/UDP ports to deliver user traffic. Zscaler recommends verifying that App Connectors use network conditions that assure quick DNS resolution for internal applications. Non-optimal DNS resolutions by App Connectors often contribute to delays and degraded user experience.
- Connectors must have Internet Control Message Protocol (ICMP) access to servers. This provides an optimal path selection for UDP-based apps and is mandatory. Though not mandatory, Zscaler also recommends ICMP access for TCP-based apps (to help with troubleshooting).
- In general, it is not necessary to dedicate App Connector groups to each application server. However, Zscaler recommends dedicating an App Connector group to an application server used for highly sensitive and business-critical applications.
- Due to multichannel communication and encryption, Zscaler does not recommend forwarding the App Connector's outbound traffic via a ZIA Public Service Edge or Private Service Edge.

You can measure user experience using Zscaler Digital Experience (ZDX). To learn more, see [What is Zscaler Digital Experience](https://help.zscaler.com/zdx/what-is-zscaler-digital-experience).

### Segmentation

Generally, organizations can take one of two broad approaches to segmentation rollout. Most organizations find the second of the two approaches a better fit.

1. Day 0 Trust: The fastest way to achieve Zero Trust is to define every known Fully Qualified Domain Name (FQDN), IP address in application segments, and user group access policies mappings, and then to create access to each application segment in advance of rollout.

This approach requires innate knowledge of all organizational applications and risks blocking business activity for any applications or users missed as a part of the initial rollout configuration. Such blocks create negative employee sentiment around Zero Trust. This approach is waterfall-based rather than Agile-based and requires a large effort before realizing any benefits. Zscaler recommends an organization only use the Day 0 Trust approach if it is confident that it can enumerate all applications and corresponding user access patterns.

1. Discovery-Based Zero Trust: A slower but less disruptive way to roll out Zero Trust is to allow all authenticated users access to any private application (very similar to a traditional client-based VPN). The organization defines a pattern-based application segment with a private app space and a permissive allow policy that provides authenticated users access (i.e., allowing authenticated users to access *.companyprivateapps.com). Organizations using discovery-based Zero Trust can replace their VPN from day one without creating user experience or productivity friction.

ZPA then discovers and surfaces applications that users access, which allows further application segment and access policy creation over time that are split from the catch-all pattern-based application segment.

|  | Day 0 Zero Trust | Discovery-Based Zero Trust |
| --- | --- | --- |
| Pros | Minimizes security risk. | Minimizes organizational impact. |
| Cons | Long time to value. | Long time to 100% completion. |

#### Pre-Segmentation Checklist for Discovery-Based Zero Trust

The following provides guidance on how to effectively implement discovery-based Zero Trust.

Discovery-based segmentation success requires good visibility into application traffic data. Zscaler recommends that the following prerequisites are met before you proceed on segmentation:

- App Connectors are deployed for the majority of your applications.
- Zscaler Client Connector is deployed to most managed endpoints.
- A substantial portion (more than half) of your user base actively leverages ZPA to access private applications.

Application Discovery is a good first-feature when adopting ZPA. Configuring an application segment to employ application discovery using wildcards helps uncover the applications in your organization that users access.

To learn more, see [Configuring Defined Application Segments](https://help.zscaler.com/zpa/configuring-application-segments).

For Application Discovery, Zscaler recommends:

- Using dynamic server discovery in server groups whenever possible. By default, administrators should always use dynamic server discovery (though there might be some cases where it makes sense to configure servers manually).
- Verifying that the initial ZPA deployment (within the first 60 days in production) includes 60% of entitled users.

In this mode, ZPA allows all users access to any DNS-based service or server as long as their authentication is in order (similar to client-based VPN access). Application Discovery allows the administrator to follow user key trends and access habits, which is used to build user segmentation and application segmentation.

Administrators should define an application discovery deadline. After the deadline, application discovery is turned off. Define discovered applications within appropriate Application Segments based on the discovery phase findings, and remove wildcards while building granular policies for the specifically named applications. Turning off application discovery enforces least-privilege access for all discovered applications and any newly requested applications.

Zscaler recommends managing access via the discovered applications rule for a limited period (i.e., 60 or 90 days) or until a percentage of users are deployed (i.e, 60%) for each ZPA user group during the deployment phase. To learn more, see [Understanding Application Discovery](https://help.zscaler.com/zpa/understanding-application-discovery).

#### Business-Critical Applications

Zscaler recommends that you build clear policies listing which user groups can access business-critical applications (any application that is essential for business continuity) immediately. Do not rely on access rules that match the applications discovered via wildcard matches. Before deployment, define which departments and user groups can access critical applications, and make sure the user segmentation is clearly visible to the identity provider (IdP) and shared with the ZPA infrastructure. That is, via System for Cross-domain Identity Management (SCIM).

After defining the App Segment relative to these business-critical applications, Zscaler recommends specific access policies similar to:

| Policy Name | Segment Group/ App Segment | IdP Attribute | Action |
| --- | --- | --- | --- |
| Allowing Business-Critical Apps for Engineering | business-critical-app1 | memberOf = engineering | Allow |
| Blocking Business-Critical Apps | business-critical-app1 | Any | Block |

You can limit business-critical application access to specific departments or business units during the initial ZPA deployment. After you take care of Engineering business-critical apps, move on to Sales, Marketing, etc.

For example, if only UK payroll employees should access a critical application, administrators must ensure that these users have the appropriate group memberships on the IdP and that those groups are pushed towards ZPA. Then they must add a policy similar to the following:

| Policy Name | Segment Group/ App Segment | IdP Attribute | Action |
| --- | --- | --- | --- |
| Allowing Payroll UK | critical-app-payroll-UK | memberOf = Payroll AND memberOf = UK | Allow |
| Blocking Payroll UK | critical-app-payroll-UK | Any | Block |

Remember that rule order matters. ZPA evaluates policy rules using the most specific application segment and a top-down, first-match principle.

For business-critical applications:

- Add posture checks very early in the process to ensure that access to these apps is allowed from only compliant corporate-managed devices.
- Add Client Forwarding rules to ensure access to these apps goes through ZPA, even if the user is on the local corporate network. This provides administrators full visibility while implementing the key Zero Trust principles. Only approved users from approved devices get access to the app. Leverage a Private Service Edge for local access to specific apps through ZPA. Review the Zscaler Private Service Edge section later in this document.
- The default Timeout Policy rule specifies that all users have to authenticate every 7 days for any application. You can change the Authentication Timeout for critical apps (i.e., one hour or one day) to make sure that frequent authentication requests add an additional layer of protection (to protect against a user leaving their laptop unlocked and unattended, for example).

To learn more, see [About Access Policy](https://help.zscaler.com/zpa/about-access-policy).

#### Regular Applications (Non-Critical)

You can manage non-critical business applications during the initial deployment phase by matching the wildcard application segment or application discovery rule. Zscaler recommends a regular analysis (on a weekly basis) of the discovered applications to meet the deadline for only allowing defined applications. Leverage ZPA ML suggestions using the [Recommended Application Segments](https://help.zscaler.com/zpa/about-recommended-application-segments) options.

To improve security posture considerations for all users and all applications:

- Implement more specific and granular access rules, since controlling who can access what is a key principle of Zero Trust.
- Zscaler recommends implementing regular (weekly) policies for all discovered applications that look at who has access to what. Use a mix of the following two approaches:
  - Who can access what. Identify the targeted user community. That is, third parties, mergers and acquisitions (M&A) users, etc., and determine the apps to which they need access.
  - What is accessed by whom. Identify sensitive apps and who needs access.
- App discovery and granular policy can co-exist. Administrators can create a generic allow policy for app discovery, and then gradually layer more specific allow policies to restrict access for specific users or apps.
- The following is an example of granular policies layered over generic app discovery that locks down a user community to specific apps:

| Policy Name | Segment Group/ App Segment | IdP Attribute | Action |
| --- | --- | --- | --- |
| Allowing contractors apps for contractors | contractor-app | memberOf = contractor | Allow |
| Blocking anything else for contractors | Any | memberOf = contractor | Block |
| Allowing anything else | Any | Any | Allow |

These policies allow contractors access to the contractor-app group of application segments. When a contractor attempts to access another app outside of that group, the explicit block rule displays a notification informing the user that the connection cannot go through.

After the applications are discovered, the ZPA interface allows administrators to easily add the discovered applications, define their respective servers, and update the App Segment information. Administrators can leverage Zscaler's ML features, when available, to use the provided insights relative to the discovered applications.

To learn more, see [About Access Policy](https://help.zscaler.com/zpa/about-access-policy).

#### Policy Definition Proposal for Initial Deployment

The following is an example of how Access Policy rules can work during initial deployment, one month after deployment, and two months after deployment. This example follows the internally set timeline to stop relying on the autodiscovery rules.

##### Day 1:

| Policy Name | Segment Group/ App Segment | IdP Attribute | Action |
| --- | --- | --- | --- |
| Allowing specific business-critical apps for Engineering | business-critical-app1 | memberOf = engineering | Allow |
| Blocking specific business-critical apps | business-critical-app1 | Any | Block |
| Allowing specific business-critical apps for Sales | business-critical-app2 | memberOf = sales | Allow |
| Blocking specific business-critical apps | business-critical-app2 | Any | Block |
| […] |  |  |  |
| Allowing anything else | Any | Any | Allow |

Dedicated rules allow authorized users access to critical apps, and a wildcard rule handles the rest of the traffic and discovers other applications.

##### Day 30:

| Policy Name | Segment Group/ App Segment | IdP Attribute | Action |
| --- | --- | --- | --- |
| Allowing specific business-critical apps for Engineering | business-critical-app1 | memberOf = engineering | Allow |
| Blocking specific business-critical apps | business-critical-app1 | Any | Block |
| Allowing specific business-critical apps for Sales | business-critical-app2 | memberOf = sales | Allow |
| Blocking specific business-critical apps | business-critical-app2 | Any | Block |
| […] |  |  |  |
| Allowing contractors apps for contractors | contractor-app | memberOf = contractor | Allow |
| Blocking anything else for contractors | Any | memberOf = contractor | Block |
| […] |  |  |  |
| Allowing anything else | Any | Any | Allow |

At 30 days, the schedule adds granular policies for non-business-critical apps to cover specific use cases. The last wildcard rule that handles the remaining traffic is still in effect.

##### Day 60:

Remove the last wildcard rule that handles remaining traffic. All applications are discovered and granular policies implemented.

Request new applications through an established internal process.

#### Unmanaged Laptops or Contractor, Vendor, or Third-Party Users

To improve the security posture for unmanaged laptops or contractor/vendor/third-party users, Zscaler recommends using browser-based access to a limited number of applications. Browser-based access lets you deploy ZPA on devices without installing the Zscaler Client Connector. Zscaler recommends limiting access to certain specific applications only.

It is possible to use Browser Access with Multiple IdP support (i.e., employees are authenticated via the default IdP, and contractors are authenticated via a dedicated IdP). Critical apps accessed by unmanaged devices should use Isolation access for an additional security layer.

To help administrators with ease of management, many customers map one policy to one app segment, which creates more access policies than necessary.

#### App Segment Groups

App segment groups apply policies to multiple applications. For example, if you have 100 app segments needing the same policy treatment, you can create a policy with a single target rather than 100.

To improve the user experience:

- Do not send DNS traffic through ZPA, because DNS traffic creates DNS resolution problems. Administrators should avoid using TCP port 53 in any app segments.
- To determine if any App Segment uses TCP port 53, go to Diagnostics and filter for `Application: Server Port = 53`.
- Zscaler recommends that admins review any app segments with a health check set to None or Continuous and confirm the reasons for the setting. App segments set to None are reachable by any App Connector servicing an associated server group. App segments set to Continuous can create high loads while continuously monitoring infrequently, since there is a limit of 6k targets (a combination of the IP address and port) per Connector Group.
- Zscaler strongly discourages defining app segments with large IP ranges (i.e., 10.0.0.0/8). Administrators should use wildcard domains such as *.internal.corp.com or *.corp.com. These wildcards offer broad connectivity options. If admins have no choice for a wildcard domain range, Zscaler recommends setting a time limit on the IP range. After the time limit is exceeded, rely on discovered applications and recommended application segments to properly define the used applications and then remove this wildcard IP range.

#### Zscaler Private Service Edge

You can deploy a ZPA Private Service Edge to facilitate ZPA connectivity for:

- Users who connect to the local corporate network and access applications on the internal network.
- Remote users who access regional internal applications.
- Implementing a business continuity plan.

Admins should turn on ZPA for local users using the Private Service Edge in the office or branch using the Forwarding Profile on the Zscaler Client Connector.

When administrators add the Private Service Edge to the ZPA Admin Portal, assign the Trusted Networks criteria to the Private Service Edge group so that users connected to the trusted network are redirected to the Private Service Edge instead of a Public Service Edge.

When a Private Service Edge is configured for both types of users rather than an IP address, the admin should use an FQDN that resolves from the internet to the Private Service Edge's public IP address, and from the internal network to the Private Service Edge’s private IP address.

To learn more, see [About ZPA Private Service Edges](https://help.zscaler.com/zpa/about-zpa-private-service-edges).

#### SIEM/SOC Integration

Considerations for administrator ease-of-management:

- Zscaler recommends using dedicated connector groups configured for log streaming, separate from connector groups serving user traffic. This allows user traffic bursts to avoid disrupting log streaming (since ZPA traffic has a higher priority than log streaming).
- Zscaler recommends that admins configure the Log Receiver Log Stream Policy Session setting to reauthBlock. This setting excludes log entries for authentication timeouts.

Any large system with a frequent reauthentication policy generates tens or hundreds of log entries as users refresh their SAML assertions. This data is temporarily interesting and can be viewed in the web UI, but is generally not useful for long-term research, trending, and forensics. The value is usually outweighed by the cost of sending and storing all the log entries.

To learn more, see [About the Log Streaming Service](https://help.zscaler.com/zpa/about-log-streaming-service).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-deployments-operations/zscaler-client-connector-deployment-and-operations-guide","lastmod":"2026-07-23T16:03Z","nid":"1420036"} -->
## Zscaler Client Connector Deployment and Operations Guide

- Source: https://help.zscaler.com/zscaler-deployments-operations/zscaler-client-connector-deployment-and-operations-guide
- Product: Zscaler Deployments & Operations
- Path: Zscaler Deployments & Operations > Zscaler Client Connector Deployments & Operations > Zscaler Client Connector Deployment and Operations Guide
- Last modified: 2026-07-23T16:03Z
- Summary: Describes the benefits of and the steps necessary to enable and configure the Zscaler Client Connector.

This guide describes how to use Zscaler Client Connector and the steps necessary for configuring Zscaler Client Connector. Zscaler Client Connector is a lightweight application that runs on a user’s endpoint device. Zscaler Client Connector automatically forwards all user traffic to the closest Zscaler Public Service Edge and enforces security and access policies across all devices, locations, and applications.

With Zscaler Private Access (ZPA) enabled, users can securely access your organization's internal resources from any location. Using Zscaler Digital Experience (ZDX), Zscaler Client Connector synthetically probes Software as a Service (SaaS) applications or internet-based services (e.g., OneDrive, Gmail, etc.) to triage and pinpoint the source of performance issues.

To learn more, see [What is Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/what-is-zscaler-client-connector)[?](https://help.zscaler.com/zscaler-client-connector/what-zscaler-app)

## Value of Deploying User Provisioning and Authentication

Using Zscaler Client Connector provides the following benefits:

- Zero Trust policies follow users regardless of devices, locations, or applications accessed.
- Enhances the user experience and streamlines application access.
- Centralizes control and policy management.
- Tracks and monitors user and device activities for IT teams.
- Supports popular operating systems and device types (e.g., laptops, smartphones, tablets, etc.).
- Strictly enforces internet access criteria for users not enrolled in Zscaler Client Connector.

## Deployment Phase

The deployment phase initially sets up and integrates Zscaler Client Connector. During the deployment phase, you configure Zscaler Client Connector to meet the needs of your infrastructure. The following sections discuss the steps to deploy Zscaler Client Connector.

### Prerequisites

For Zscaler Client Connector deployment, observe the following prerequisites:

- Verify the [system requirements](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#step-1).
- Zscaler Client Connector does not require an additional license or subscription. Licensing for ZIA and ZPA includes Zscaler Client Connector.

### Deployment Steps

The following steps explain how to deploy Zscaler Client Connector:

1. Complete [system requirements and prerequisite tasks](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#step-1).
2. Allowlist [Zscaler Client Connector processes](https://help.zscaler.com/zscaler-client-connector/zscaler-app-processes-whitelist) on client firewall and antivirus (AV).
3. Allow [Zscaler Client Connector communication](https://config.zscaler.com/zscaler.net/zscaler-app) to the Zscaler cloud through your organization's firewall.
4. [Download Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/downloading-zscaler-client-connector) from an app store.
5. (Optional) Customize [Zscaler Client Connector with installer options](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi).
6. Use your organization’s device management system to [deploy Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-app-active-directory-windows).

### Considerations

Review the following considerations:

- If your users are running Zscaler Client Connector in conjunction with virtual private network (VPN) clients or VPN-like applications (e.g., Microsoft DirectAccess), check that users aren’t experiencing interoperability issues.
- For a complete list of recommended steps, see [Best Practices for Zscaler Client Connector and VPN Client Interoperability](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-app-and-vpn-client-interoperability).
- Ensure all authentication traffic goes directly to the identity provider (IdP) destination URL. Users who are off the trusted network and forwarding traffic via Zscaler Client Connector should not experience issues. However, check that other authentication traffic (e.g., PAC files, GRE tunnels, and IPSec tunnels) goes directly to the IdP.
- Make sure traffic destined for the IdP that goes through Zscaler is not intercepted for inspection by Zscaler:
  - Adjust [SSL Inspection exemptions](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-app#exempting-URLs-SSL).
  - Adjust [Authentication exemptions](https://help.zscaler.com/zia/exempting-urls-cloud-apps-authentication).
- If you are using Microsoft Windows Autopilot, see the [Zscaler and Microsoft Windows Autopilot Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-microsoft-windows-autopilot-deployment-guide).

## Operations Phase

This section describes standard practices used to operate Zscaler solutions when integrated with your environment. You can monitor and tune Zscaler Client Connector during operations to meet your infrastructure needs.

### Prerequisites

For Zscaler Client Connector operations, observe the following prerequisites:

- Create a standard operating procedure (SOP) for adding domain bypasses in the PAC file used by Zscaler Client Connector.
- Define a process to test configuration changes, such as diverting traffic to a different data center or bypassing a PAC file.
- Test any changes with separate app profiles or PAC files and apply the policy to test users to avoid organization-wide impact.
- Implement a process to clean up test profiles and PAC files.

## Deployment Checklist

Zscaler recommends downloading the [Zscaler Client Connector Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zscaler-client-connector-deployments-operations/zscaler-client-connector-deployment-and-operations-guide/Zscaler-Client-Connector-Deployment-Operations-Checklist.pdf) to help plan and implement Zscaler Client Connector: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zscaler-client-connector-deployments-operations/zscaler-client-connector-deployment-and-operations-guide/Zscaler-Client-Connector-Deployment-Operations-Checklist.pdf)

## Additional Information

For more Zscaler Client Connector information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](https://community.zscaler.com/).
<!-- /ZS-ARTICLE -->
