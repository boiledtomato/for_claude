# Zscaler Help — ZPA — Private Access (part 4)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-08-17 01:14 UTC
Articles in this file: 26

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-private-cloud-controllers-dashboard","lastmod":"2026-06-29T10:10Z","nid":"1506341"} -->
## Viewing the Private Cloud Controllers Dashboard

- Source: https://help.zscaler.com/zpa/viewing-private-cloud-controllers-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Private Cloud Controller Monitoring > Viewing the Private Cloud Controllers Dashboard
- Last modified: 2026-06-29T10:10Z
- Summary: Information on the Private Cloud Controllers dashboard and widgets accessible within the Zscaler Admin Console.

The Private Cloud Controllers dashboard provides information about the Private Cloud Controllers for your organization. To view the dashboard in the Zscaler Admin Console, go to Analytics > Switch to Existing Reports > Private Applications > Private Cloud Controllers.

See image.

## Dashboard Tools

The Private Cloud Controllers dashboard displays the following information and functionality:

- **Time Range Filter**: View user data over a period between **30 Mins** to **14 Days**, or you can select **Custom Range** to specify a custom start and end date. If you use **Custom Range**, the start date can be within the last 14 days. This filter applies to all widgets on the dashboard. By default, the dashboard displays information for events that occurred in the past 30 minutes.

Due to the way data is aggregated for different time period filters, the same point in time in an Activity Monitor widget might show slightly different values depending on the time chosen. For example, the data with the **30 Mins** time period filter at 3:00 PM might not match the data with the **14 Days** time period filter for the same date at 3:00 PM.

- **Refresh Icon**: Refresh the dashboard to reflect the most current information. The page displays the most recent information from the last 5 minutes.
- **Chart Selection**: Select the charts you want to display or hide on the dashboard. A minimum of 3 charts and a maximum of 6 charts can be selected.

See image.

## Top Private Cloud Controller Widgets

The Top Private Cloud Controller widgets provide an overview of the peak or top metrics for the relevant Private Cloud Controllers in the selected time range.

Four widgets are selected automatically when you access the dashboard. At least 4 widgets must be selected for the widgets to display, and no more than 8 widgets are available to view at one time.

- View the widgets.

Click on any of the Private Cloud Controllers in the widgets to view the [User Activity Diagnostic logs](https://help.zscaler.com/zpa/accessing-user-activity-diagnostics) page filtered for this Private Cloud Controller.

- **Minimum Available Disk Space**: Displays up to the top 10 Private Cloud Controllers that have the least disk space available in the selected time range. This is not the average disk space used over the time range. This widget displays automatically when first accessing the dashboard.
- **Peak CPU Utilization**: Displays up to the top 10 Private Cloud Controllers using the most CPU in the selected time range. This is not the average CPU used over the time range. This widget displays automatically when first accessing the dashboard.
- **Peak File Descriptor Utilization**: Displays up to the top 10 Private Cloud Controllers using the most file descriptors in relation to their maximum number of file descriptors.
- **Peak Memory Utilization**: Displays up to the top 10 Private Cloud Controllers using the most memory in the selected time range. This is not the average memory used over the time range. This widget displays automatically when first accessing the dashboard.
- **Peak TCP IPv6 Port Utilization**: Displays up to the top 10 Private Cloud Controllers using the most TCP ports for IPv6 in the selected time range. This is not the average number of TCP ports used over the time range, and it does not show Private Cloud Controllers using TCP ports for IPv4.
- **Peak TCP Port Utilization**: Displays up to the top 10 Private Cloud Controllers using the most TCP ports for IPv4 in the selected time range. This is not the average number of TCP ports used over the time range, and it does not show Private Cloud Controllers using TCP ports for IPv6.
- **Peak UDP IPv6 Port Utilization**: Displays up to the top 10 Private Cloud Controllers using the most UDP ports for IPv6 in the selected time range. This is not the average number of UDP ports used over the time range, and it does not show Private Cloud Controllers using UDP ports for IPv4.
- **Peak UDP Port Utilization**: Displays up to the top 10 Private Cloud Controllers using the most UDP ports for IPv4 in the selected time range. This is not the average number of UDP ports used over the time range, and it does not show Private Cloud Controllers using UDP ports for IPv6.
- **Total Bytes Received from Public Service Edges**: Displays up to the top 10 Private Cloud Controllers and the number of bytes received by each from the Public Service Edges for Private Access (ZPA).
- **Total Bytes Transmitted to Public Service Edges**: Displays up to the top 10 Private Cloud Controllers and the number of bytes transmitted by each to the Public Service Edges.
- **Total App Connectors Connected to the Private Cloud Controller**: Displays up to the top 10 Private Cloud Controllers and the total number of App Connectors connected to each.
- **Total Private Service Edges Connected to the Private Cloud Controller**: Displays up to the top 10 Private Cloud Controllers and the total number of Private Service Edges for Private Access connected to each.
- **Peak Zscaler Client Connector Redirections to Private Service Edges**: Displays up to the top 10 Zscaler Client Connector redirects to the Private Service Edges.
- **Logs Successfully Transmitted to SIEM**: The total number of successful logs transmitted to the SIEM. This is a cumulative count of all diagnostic log types (i.e., User Status, User Activity, App Connector Status, Private Service Edge Status, etc.).
- **Logs Failed to be Transmitted to SIEM**: The total number of logs that failed to be transmitted to the SIEM. This is a cumulative count of all diagnostic log types (i.e., User Status, User Activity, App Connector Status, Private Service Edge Status, etc.).

## Activity Monitor Widgets

The Activity Monitor widgets provide trend information about selected Private Cloud Controllers in the selected time range. If no Private Cloud Controllers are selected, the top Private Cloud Controllers from the Peak Memory Utilization widget are selected by default.

Three widgets are selected automatically when you access the dashboard. At least three widgets must be selected for the widgets to display, and no more than 6 widgets are available to view at one time.

- View the widgets.

Widgets show solid lines that represent actual data for the time period. You can select a point on the lines in a widget to see the exact date, time, and relevant numbers for the Private Cloud Controllers as related to the particular widget. You can also click **Show All Names**to show or hide the particular labels for the Private Cloud Controllers in the widget.

See image.

Select a part of the chart to zoom in for greater detail. A blue box shows the chosen portion of the widget, and the widget shows this selected smaller time period.

See image.

Click **Zoom Out** to view the original widget.

See image.

For each widget, you can deselect the listed Private Cloud Controllers to change what items are tracked within the widget. You can also search within the widget to reduce the listed Private Cloud Controllers that appear in the widget. To search, enter part or all of a Private Cloud Controller name, or use the following search query options with >, <, or = operators:

- Name: The name of the Private Cloud Controller (e.g., name = MyPrivateCloudController).
- Value: A numerical value specific to the widget (e.g., value < 40).

See image.

- **Available Disk Space**: Displays the amount of disk space that is available to a Private Cloud Controller at different points during the selected time range. This widget displays automatically when first accessing the dashboard.
- **CPU Utilization**: Displays the amount of CPU used by a Private Cloud Controller at different points during the selected time range. This widget displays automatically when first accessing the dashboard.
- **File Descriptor Utilization**: Displays the number of file descriptors used by a Private Cloud Controller at different points during the selected time range.
- **Memory Utilization**: Displays the amount of memory used by a Private Cloud Controller at different points during the selected time range. This widget displays automatically when first accessing the dashboard.
- **TCP IPv6 Port Utilization**: Displays the number of TCP ports for IPv6 used by a Private Cloud Controller at different points during the selected time range. It does not show Private Cloud Controllers using TCP ports for IPv4.
- **TCP Port Utilization**: Displays the number of TCP ports for IPv4 used by a Private Cloud Controller at different points during the selected time range. It does not show Private Cloud Controllers using TCP ports for IPv6.
- **UDP IPv6 Port Utilization**: Displays the number of UDP ports for IPv6 used by a Private Cloud Controller at different points during the selected time range. It does not show Private Cloud Controllers using UDP ports for IPv4.
- **UDP Port Utilization**: Displays the number of UDP ports for IPv4 used by a Private Cloud Controller at different points during the selected time range. It does not show Private Cloud Controllers using UDP ports for IPv6.
- **Bytes Received from Public Service Edges**: Displays the number of bytes received by the Private Cloud Controller from the Public Service Edges.
- **Bytes Transmitted to Public Service Edges**: Displays the number of bytes transmitted by the Private Cloud Controller from the Public Service Edges.
- **App Connectors Connected to the Private Cloud Controller**: Displays the number of App Connectors connected to the Private Cloud Controller.
- **Private Service Edges Connected to the Private Cloud Controller**: Displays the number of Private Service Edges connected to the Private Cloud Controller.
- **Zscaler Client Connector Redirections to Private Service Edges**: Displays the number of Zscaler Client Connector redirects to the Private Service Edges.
- **Logs Successfully Transmitted to SIEM**: Displays the number of successful logs that are transmitted to the SIEM.
- **Logs Failed to be Transmitted to SIEM**: Displays the number of logs that failed to be transmitted to the SIEM.

### Filtering Private Cloud Controllers

You can filter the Activity Monitor charts and Private Cloud Controller Details table by selecting the Private Cloud Controllers you want to review. The filters available are **Private Cloud Controllers** and **Private Cloud Controller Groups**.

See image.

When filtering by Private Cloud Controllers and Private Cloud Controller groups, the selected Private Cloud Controllers and Private Cloud Controllers within the selected Private Cloud Controller groups are shown.

The filters between Private Cloud Controllers and Private Cloud Controller groups use the OR operator, instead of AND, to help compare Private Cloud Controllers in Private Cloud Controller groups.

There is a limit of 25 Private Cloud Controllers you can select at one time. If you haven't selected any Private Cloud Controllers and have selected a Private Cloud Controller group that contains more than 25 Private Cloud Controllers, then the first 25 Private Cloud Controllers for the selected Private Cloud Controller group are used in the filter. If you select Private Cloud Controllers and then select Private Cloud Controller groups in a way that exceeds the limit, an error message appears and you must adjust your selection.

Within the **Private Cloud Controller Groups** filter, you can see the number of Private Cloud Controllers associated with the Private Cloud Controller group.

See image.

If no Private Cloud Controllers are selected, the top Private Cloud Controllers from the **Peak Memory Utilization chart** are selected by default.

## Private Cloud Controller Details

The Private Cloud Controller Details table provides information about the Private Cloud Controller selected in the Activity Monitor section. If no Private Cloud Controllers are selected, the top Private Cloud Controllers from the **Peak Memory Utilization** widget are selected by default.

The table covers:

- **Private Cloud Controllers**: The name of the Private Cloud Controllers.
- **Private Cloud Controller Group**: The name of the group the Private Cloud Controller is included in.
- **Location**: The city and country that the Private Cloud Controller is connecting from.
- **Actions**: The following actions are available:
  - **View**: View the Private Cloud Controller Details and Private Cloud Controller Latency pages.
  - **Edit**: Edit the Private Cloud Controller.
  - **Logs**: View the [User Activity Diagnostic logs](https://help.zscaler.com/zpa/accessing-user-activity-diagnostics) page filtered for this Private Cloud Controller.

See image.

### Evaluating Individual Private Cloud Controller Details and Latency

For each Private Cloud Controller, you can click the **View**icon for a Private Cloud Controller in the Private Cloud Controller Details table. For each Private Cloud Controller, you can view:

- Details
- Latency

The **Details** tab is automatically selected.

See image.

- General Information
- Private Cloud Controller Information
- Activity Monitor Widgets

The general information available about the Private Cloud Controller:

- **Private Cloud Controller Group**: The name of the group the Private Cloud Controller is included in.
- **Location**: The city and country that the Private Cloud Controller is connecting from.
- **Enabled**: Identifies if the Private Cloud Controller is enabled or disabled.
- **Session Status**: The status of the Private Cloud Controller session during the time range. The potential session statuses are:
  - **Authenticated**: The Private Cloud Controller successfully authenticated.
  - **Authentication Failed**: The Private Cloud Controller was unable to authenticate to the Zscaler cloud.
  - **Disconnected**: The Private Cloud Controller successfully disconnected.
- **Periodic Software Update On**: The date and time of the next periodic software update for the Private Cloud Controller.
- **Last Software Update On**: The date and time of the last software update for the Private Cloud Controller.
- **Scheduled Software Version**: The next Private Cloud Controller software version that the Private Cloud Controller upgrades to.
- **Current** **Software** **Version**: The current Private Cloud Controller software version during the time range.
- **Connection Status**: The connection status of the Private Cloud Controller during the time range. The potential session statuses are:
  - **Connected**: The Private Cloud Controller is up during the time range.
  - **Disconnected**: The Private Cloud Controller is down during the time range.

[Image: General information for a Private Cloud Controller on the Private Cloud Controllers dashboard]

The values and percentages for each item in this section are initially based on the most recent data available as noted by the point in time listed on the right side of this section.

[Image: Time Selection for Individual Private Cloud Controller Details]

The information available about the Private Cloud Controller includes:

- **Active Connections to App Connectors**: The number of active connections the Private Cloud Controller had to App Connectors for the point in time listed.
- **Active Connections to Public Service Edges**: The active connection the Private Cloud Controller had to Public Service Edges for the point in time listed.
- **Available Disk Space**: The size of available disk space for the Private Cloud Controller at the point in time listed.
- **Bytes Received from the Public Service Edge**: The number of received bytes from Public Service Edges to the Private Cloud Controller in the selected time range.
- **Bytes Transmitted to the Public Service Edge**: The number of transmitted bytes to Public Service Edges from the Private Cloud Controller in the selected time range.
- **CPU Utilization**: The highest CPU used by the Public Service Edge for the time range selected.
- **Logs Failed to be Transmitted to SIEM**: The total number of logs that failed to be transmitted to the SIEM.
- **File Descriptor Utilization**: The file descriptors used by the Public Service Edge for the point in time listed.
- **Memory Utilization**: The highest memory used by the Private Cloud Controller for the selected time range.
- **Logs Successfully Transmitted to SIEM**: The total number of logs successfully transmitted to the SIEM.
- **TCP IPv6 Port Utilization:**The number of TCP ports for IPv6 used by the Private Cloud Controller for the point in time listed. It does not show Private Cloud Controllers using TCP ports for IPv4.
- **TCP Port Utilization**: The number of TCP ports for IPv4 used by the Private Cloud Controller for the point in time listed. It does not show Private Cloud Controllers using TCP ports for IPv6.
- **UDP IPv6 Port Utilization:**The number of UDP ports for IPv6 used by the Private Cloud Controller for the point in time listed. It does not show Private Cloud Controllers using TCP ports for IPv4.
- **UDP Port Utilization**: The number of UDP ports for IPv4 used by the Private Cloud Controllers for the point in time listed. It does not show Private Cloud Controllers using UDP ports for IPv6.
- **Zscaler Client Connector Redirections to Private Service Edges**: The number of Zscaler Client Connector redirects to the Private Service Edges.

The Activity Monitor section displays the same widgets with the same functionality as seen in the Activity Monitor section. Three widgets are selected automatically:

- Available Disk Space
- CPU Utilization
- Memory Utilization

The latency monitor widgets on the Latency tab show the latency trend from a Public Service Edge to a Private Cloud Controller in the selected time range. The time range for the Latency tab is different from the rest of the Private Cloud Controllers dashboard. It is limited to data over a period between **30 Mins** to **24 Hours**, or you can select **Custom Range** to specify a custom start and end date. If you use **Custom Range**, the start date can be within the last 24 hours. This filter applies to all widgets in the Latency tab, and the default time is 30 minutes.

The widgets available are:

- Private Cloud Controller to Default Gateway TCP Latency
- Private Cloud Controller to Public Service Edge Latency

Each widget shows the Public Service Edges that interact with the selected Private Cloud Controller during the time range. The widgets have the same functionality as seen in the Activity Monitor section.

For each widget, you can search by entering part or all of a Private Cloud Controller name, or by using the following search query options with >, <, or = operators:

- Location: The location of the Private Cloud Controller (e.g., location = San Jose).
- Name: The name of the Private Cloud Controller (e.g., name = sj_serviceedge).
- Value: A numerical value specific to the widget (e.g., value < 40).

[Image: Latency Tab on the Private Cloud Controllers Dashboard]

[Image: Viewing the labels for each data point within the Activity Monitor widgets]

[Image: Select a part of a chart]

[Image: Zoom Out of a chart on the Private Cloud Controllers Dashboard]

[Image: Search and Deselect Private Cloud Controllers in Widgets]

[Image: Filter Private Cloud Controllers on the Private Cloud Controllers Dashboard in the Zscaler Admin Console]

[Image: Number of Private Cloud Controller Groups in the Private Cloud Controller Group filter]

[Image: Actions for the Private Cloud Controller Details table]

[Image: Details and Latency Tabs for Private Cloud Controllers in the Private Cloud Controllers Dashboard]

[Image: Private Cloud Controller Dashboard]

[Image: Private Cloud Controllers Dashboard Tools]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-private-service-edges-dashboard","lastmod":"2026-07-30T14:22Z","nid":"1485151"} -->
## Viewing the Private Service Edges Dashboard

- Source: https://help.zscaler.com/zpa/viewing-private-service-edges-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Private Service Edge Monitoring > Viewing the Private Service Edges Dashboard
- Last modified: 2026-07-30T14:22Z
- Summary: Information on the Private Service Edges dashboard and widgets accessible within the Zscaler Admin Console.

The Private Service Edges dashboard provides information about the Private Service Edges for Private Access for your organization. To view the dashboard in the Zscaler Admin Console, go to Analytics > Switch to Existing Reports > Private Applications > Private Service Edges.

See image.

## Dashboard Tools

The Private Service Edges dashboard displays the following information and functionality:

- **Time Range Filter**: View user data over a period between **30 Mins** to **14 Days**, or select **Custom Range** to specify a custom start and end date. If you use **Custom Range**, the start date can be within the last 14 days. This filter applies to all widgets on the dashboard. By default, the dashboard displays information for events that occurred in the past 30 minutes.

Due to the way data is aggregated for different time period filters, the same point in time in an Activity Monitor widget might show slightly different values depending on the time chosen. For example, the data with the **30 Mins** time period filter at 3:00 PM might not match the data with the **14 Days** time period filter for the same date at 3:00 PM.

- **Refresh Icon**: Refresh the dashboard to reflect the most current information. The page displays the most recent information from the last 5 minutes.
- **Chart Selection**: Select the charts you want to display or hide on the dashboard. A minimum of 4 charts and a maximum of 8 charts can be selected.

See image.

## Top Private Service Edges Widgets

The Top Private Service Edges widgets provide an overview of the peak or top metrics for the relevant Private Service Edges in the selected time range.

Four widgets are selected automatically when you access the dashboard. At least 4 widgets must be selected for the widgets to display, and no more than 8 widgets are available to view at one time.

- View the widgets.

Click any of the Private Service Edges in the widgets to view the [User Activity Diagnostic logs](https://help.zscaler.com/zpa/accessing-user-activity-diagnostics) page filtered for this Private Service Edge.

- **Top Errors**: Displays up to the top 100 Private Service Edges that had the most errors in the selected time range. This widget displays automatically when first accessing the dashboard.
- **Minimum Available Disk Space**: Displays up to the top 10 Private Service Edges that have the least disk space available in the selected time range. This is not the average disk space used over the time range. This widget displays automatically when first accessing the dashboard.
- **Peak CPU Utilization**: Displays up to the top 10 Private Service Edges using the most CPU in the selected time range. This is not the average CPU used over the time range. This widget displays automatically when first accessing the dashboard.
- **Peak File Descriptor Utilization**: Displays up to the top 10 Private Service Edges using the most file descriptors in relation to their maximum number of file descriptors.
- **Peak Memory Utilization**: Displays up to the top 10 Private Service Edges using the most memory in the selected time range. This is not the average memory used over the time range. This widget displays automatically when first accessing the dashboard.
- **Peak System File Descriptor Utilization**: Displays up to the top 10 Private Service Edges in the entire system using the most file descriptors in the selected time range.
- **Peak TCP IPv6 Port Utilization**: Displays up to the top 10 Private Service Edges using the most TCP ports for IPv6 in the selected time range. This is not the average number of TCP ports used over the time range, and it does not show Private Service Edges using TCP ports for IPv4.
- **Peak TCP Port Utilization**: Displays up to the top 10 Private Service Edges using the most TCP ports for IPv4 in the selected time range. This is not the average number of TCP ports used over the time range, and it does not show Private Service Edges using TCP ports for IPv6.
- **Peak UDP IPv6 Port Utilization**: Displays up to the top 10 Private Service Edges using the most UDP ports for IPv6 in the selected time range. This is not the average number of UDP ports used over the time range, and it does not show Private Service Edges using UDP ports for IPv4.
- **Peak UDP Port Utilization**: Displays up to the top 10 Private Service Edges using the most UDP ports for IPv4 in the selected time range. This is not the average number of UDP ports used over the time range, and it does not show Private Service Edges using UDP ports for IPv6.

## Activity Monitor Widgets

The Activity Monitor widgets provide trend information about selected Private Service Edges in the selected time range. If no Private Service Edges are selected, the top Private Service Edges from the Peak Memory Utilization widget are selected by default.

Three widgets are selected automatically when you access the dashboard. At least three widgets must be selected for the widgets to display, and no more than 6 widgets are available to view at one time.

- View the widgets.

Widgets can show solid lines or dashed lines. Solid lines represent actual data for the time period. Dashed lines indicate the expected trajectory of the data, but it isn't actual data. You can select a point on the lines in a widget to see the exact date, time, and relevant numbers for the Private Service Edges as related to the particular widget. You can also click **View Logs** to see the [User Activity Diagnostic logs](https://help.zscaler.com/zpa/accessing-user-activity-diagnostics) page filtered for the Private Service Edges in the widget.

See image.

Select a part of the chart to zoom in for greater detail. A blue box shows the chosen portion of the widget, and the widget shows this selected smaller time period.

See image.

Click **Zoom Out** to view the original widget.

See image.

For each widget, you can deselect the listed Private Service Edges to change what items are tracked within the widget. You can also search within the widget to reduce the listed Private Service Edges that appear in the widget. To search, enter part or all of a Private Service Edge name, or use the following search query options with >, <, or = operators:

- name: The name of the Private Service Edge (e.g., name = MyPrivateServiceEdge).
- value: A numerical value specific to the widget (e.g., value < 40).

See image.

- **Available Disk Space**: Displays the amount of disk space that is available to a Private Service Edge at different points during the selected time range. This widget displays automatically when first accessing the dashboard.
- **CPU Utilization**: Displays the amount of CPU used by a Private Service Edge at different points during the selected time range. This widget displays automatically when first accessing the dashboard.
- **File Descriptor Utilization**: Displays the number of file descriptors used by a Private Service Edge at different points during the selected time range.
- **Memory Utilization**: Displays the amount of memory used by a Private Service Edge at different points during the selected time range. This widget displays automatically when first accessing the dashboard.
- **System File Descriptor Utilization**: Displays the number of file descriptors used by the entire system running the Private Service Edges in the selected time range.
- **TCP IPv6 Port Utilization**: Displays the number of TCP ports for IPv6 used by a Private Service Edge at different points during the selected time range. It does not show Private Service Edges using TCP ports for IPv4.
- **TCP Port Utilization**: Displays the number of TCP ports for IPv4 used by a Private Service Edge at different points during the selected time range. It does not show Private Service Edges using TCP ports for IPv6.
- **UDP IPv6 Port Utilization**: Displays the number of UDP ports for IPv6 used by a Private Service Edge at different points during the selected time range. It does not show Private Service Edges using UDP ports for IPv4.
- **UDP Port Utilization**: Displays the number of UDP ports for IPv4 used by a Private Service Edge at different points during the selected time range. It does not show Private Service Edges using UDP ports for IPv6.

### Filtering Private Service Edges

You can filter the Activity Monitor charts and Private Service Edge Details table by selecting the Private Service Edges you want to review. The filters available are Private Service Edge and Private Service Edge Groups.

See image.

When filtering by Private Service Edges and Private Service Edge groups, the selected Private Service Edges and Private Service Edges within the selected Private Service Edge groups are shown.

The filters between Private Service Edges and Private Service Edge groups use the OR operator, instead of AND, to help compare Private Service Edges in Private Service Edge groups.

There is a limit of 25 Private Service Edges you can select at one time. If you haven't selected any Private Service Edges and have selected a Private Service Edge group that contains more than 25 Private Service Edges, then the first 25 Private Service Edges for the selected Private Service Edge group are used in the filter. If you select Private Service Edges and then select Private Service Edge groups in a way that exceeds the limit, an error message appears and you must adjust your selection.

Within the Private Service Edge group filter, you can see the number of Private Service Edges associated with the Private Service Edge group.

See image.

If no Private Service Edges are selected, the top Private Service Edges from the Peak Memory Utilization chart are selected by default.

## Private Service Edge Details

The Private Service Edge Details table provides information about the Private Service Edge selected in the Activity Monitor section. If no Private Service Edges are selected, the top Private Service Edges from the Peak Memory Utilization widget are selected by default.

The table covers:

- **Private Service Edges**: The name of the Private Service Edges.
- **Private Service Edge Group**: The name of the group the Private Service Edge is included in.
- **Location**: The city and country that the Private Service Edge is connecting from.
- **Actions**:
  - **View**: View the Private Service Edge Details and Private Service Edge Latency pages.
  - **Edit**: Edit the Private Service Edge.
  - **Logs**: View the [User Activity Diagnostic logs](https://help.zscaler.com/zpa/accessing-user-activity-diagnostics) page filtered for this Private Service Edge.

See image.

### Evaluating Individual Private Service Edge Details and Latency

For each Private Service Edge, you can click the **View**icon for a Private Service Edge in the Private Service Edge Details table. For each Private Service Edge, you can view:

- Details
- Latency

The **Details** tab is automatically selected.

See image.

- General Information
- Private Service Edge Information
- Activity Monitor Widgets

The general information available about the Private Service Edge:

- **Private Service Edge Group**: The name of the group the Private Service Edge is included in.
- **Location**: The city and country that the Private Service Edge is connecting from.
- **Enabled**: Identifies if the Private Service Edge is enabled or disabled.
- **Session Status**: The status of the Private Service Edge session during the time range. The potential session statuses are:
  - **Authenticated**: The Private Service Edge successfully authenticated.
  - **Authentication Failed**: The Private Service Edge was unable to authenticate to the Zscaler cloud.
  - **Disconnected**: The Private Service Edge successfully disconnected.
- **Periodic Software Update On**: The date and time of the next periodic software update for the Private Service Edge.
- **Last Software Update On**: The date and time of the last software update for the Private Service Edge.
- **Scheduled Software Version**: The next Private Service Edge software version that the Private Service Edge upgrades to.
- **Current** **Software** **Version**: The current Private Service Edge software version during the time range.
- **Connection Status**: The connection status of the Private Service Edge during the time range. The potential session statuses are:
  - **Connected**: The Private Service Edge is up during the time range.
  - **Disconnected**: The Private Service Edge is down during the time range.

[Image: General information for a Private Service Edge on the Private Service Edge dashboard]

The values and percentages for each item in this section are initially based on the most recent data available as noted by the point in time listed on the right side of this section.

[Image: Time Selection for Individual Private Service Edge Details]

The information available about the Private Service Edge is:

- **Active Application Tunnel Count**: The number of active connections the Private Service Edge had to applications for the point in time listed.
- **Active Connections to App Connectors**: The number of active connections the Private Service Edge had to App Connectors for the point in time listed.
- **Active Connections to Public Service Edges**: The active connection the Private Service Edge had to Public Service Edges for Private Access for the point in time listed.
- **Available Disk Space**: The number of available disk space for the Private Service Edge at the point in time listed.
- **Total Bytes Received**: The number of received bytes from App Connectors to the Private Service Edge in the selected time range.
- **Total Bytes Transferred**: The number of transfered bytes to App Connectors from the Public Service Edges in the selected time range.
- **Configured Application Count**: The number of applications configured for access via the Private Service Edge at different points during the selected time range.
- **CPU Utilization**: The highest CPU used by the Public Service Edge for the time range selected.
- **Disconnected Connections to App Connectors**: The number of disconnected connections to App Connectors in the selected time range for a Private Service Edge.
- **Disconnected Connections to Public Service Edges**: The number of disconnected connections to Public Service Edges in the selected time range for a Private Service Edge.
- **File Descriptor Maximum Limit**: The maximum limit of file descriptors used by a Private Service Edge at different points during the selected time range.
- **File Descriptor Utilization**: The file descriptors used by the Public Service Edge for the point in time listed.
- **Number of Usable Ports**: The number of usable ports for a Private Service Edge at different points during the selected time range.
- **Memory Utilization**: The highest memory used by the Private Service Edge for the selected time range.
- **Rate of Bits Received from Public Service Edge**: The number of bits received per second by the Private Service Edge from the Public Service Edge for the point in time listed.
- **Rate of Bits Transmitted to Public Service Edges**: The number of bits transmitted per second by the Private Service Edge to Public Service Edges for the point in time listed.
- **TCP IPv6 Port Utilization:**The number of TCP ports for IPv6 used by the Private Service Edge for the point in time listed. It does not show Private Service Edges using TCP ports for IPv4.
- **TCP Port Utilization**: The number of TCP ports for IPv4 used by the Private Service Edge for the point in time listed. It does not show Private Service Edges using TCP ports for IPv6.
- **Total Transactions Completed**: The total number of completed connections to applications on a Private Service Edge.
- **UDP IPv6 Port Utilization:**The number of UDP ports for IPv6 used by the Private Service Edge for the point in time listed. It does not show Private Service Edges using TCP ports for IPv4.
- **UDP Port Utilization**: The number of UDP ports for IPv4 used by the Private Service Edges for the point in time listed. It does not show Private Service Edges using UDP ports for IPv6.

The Activity Monitor section displays the same widgets with the same functionality as seen in the Activity Monitor section. Three widgets are selected automatically. They are:

- Active Application Tunnel Count
- CPU Utilization
- Memory Utilization

The latency monitor widgets on the Latency tab show the latency trend from a Public Service Edge to a Private Service Edge in the selected time range. The time range for the Latency tab is different from the rest of the App Connector Dashboard. It is limited to data over a period between **30 Mins** to **24 Hours**, or you can select **Custom Range** to specify a custom start and end date. If you use **Custom Range**, the start date can be within the last 24 hours. This filter applies to all widgets in the Latency tab, and the default time is 30 minutes.

The widgets available are:

- Private Service Edge to Default Gateway TCP Latency
- Private Service Edge to Public Service Edge Latency

Each widget shows the Public Service Edges that interact with the selected Private Service Edge during the time range. The widgets have the same functionality as seen in the Activity Monitor section.

For each widget, you can search by entering part or all of a Private Service Edge name, or by using the following search query options with >, <, or = operators:

- location: The location of the Private Service Edge (e.g., location = San Jose).
- name: The name of the Private Service Edge (e.g., name = sj_serviceedge).
- value: A numerical value specific to the widget (e.g., value < 40).

[Image: Latency Tab on the Private Service Edge Dashboard]

[Image: Select a point in time or view the logs]

[Image: Select a part of the chart]

[Image: Zoom Out of a chart on the Private Service Edge Dashboard]

[Image: Search and Deselect Private Service Edges in Widgets]

[Image: Filter Private Service Edges on the Private Service Edge Dashboard in the Zscaler Admin Console]

[Image: Number of Private Service Edge Groups in the Private Service Edge Group filter]

[Image: Actions for the Private Service Edge Details table]

[Image: Details and Latency Tabs for Private Service Edges in the Private Service Edge Dashboard]

[Image: Viewing the Private Service Edge dashboard tools]

[Image: Private Service Edges dashboard tools]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-protocol-discovery-dashboard","lastmod":"2026-06-29T10:14Z","nid":"1486011"} -->
## Viewing the Protocol Discovery Dashboard

- Source: https://help.zscaler.com/zpa/viewing-protocol-discovery-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > AppProtection and Browser Protection Monitoring > Viewing the Protocol Discovery Dashboard
- Last modified: 2026-06-29T10:14Z
- Summary: Information on the Protocol Discovery dashboard accessible within the Zscaler Admin Console.

The Protocol Discovery dashboard displays protocols (KRB, LDAP, SMB, HTTP, and TLS) detected for domains with application segments that have AppProtection disabled. [You can enable AppProtection for the application segment](https://help.zscaler.com/zpa/configuring-defined-application-segments#ADProtection) when you click the application segment, the listed domains, and the ports and protocols mapped to it. The Protocol Discovery dashboard displays up to 100 of a domain's most recent transactions and 6,000 application segments for the time range selected. To view the dashboard in the Zscaler Admin Console, go to Analytics > Switch to Existing Reports > Private Applications > Security > Protocol Discovery.

## Dashboard Tools

The AppProtection dashboard displays the following information and functionality:

- **Time Range Filter**: View Protocol Discovery data over a period between 1 hour to 7 days, or you can select **Custom Range**. If you use **Custom Range**, the start date must be within the last 7 days. The end date automatically sets to the system's current time. By default, the dashboard displays information for events that occurred in the last hour.
- **Refresh Icon**: Refresh the dashboard to reflect the most current information.
- **AppProtection Dashboard**: View the [AppProtection page](https://help.zscaler.com/zpa/about-appprotection-dashboard) for information about AppProtection policy activity in your organization.
- **Browser Protection Dashboard**: View the [Browser Protection](https://help.zscaler.com/zpa/about-browser-protection-dashboard) page for information about browser sessions in your organization.

## Protocol Discovery Widget

You can select the application segment that you want displayed from the drop-down menu. You can enter a keyword to search by domain within the displayed application segment.

[Image: Selecting an application segment for the Protocol Discovery widget]

### Enabling AppProtection for an Application Segment

If you haven't [enabled AppProtection](https://help.zscaler.com/zpa/configuring-defined-application-segments#ADProtection)for the displayed application segment and want to inspect it, follow these steps:

1. Click the displayed application segment or any of its related items (domain, port, or protocol).
2. When you click one of the displayed items, the **Enable AppProtection** window appears. If the application segment doesn't include domains that have AppProtection enabled, and an AppProtection profile and policy don't already exist, you can adjust the AppProtection Profile Type and select the **Auto create an AppProtection policy** checkbox.

See image.

1. When you are finished, click **Save**. A confirmation message appears notifying you that you have successfully updated the application segment.

Make sure you also [generate the AppProtection enrollment (CA) certificate](https://help.zscaler.com/zpa/generating-zscaler-issued-enrollment-ca-certificates) to use the AppProtection feature. If you haven't already generated the certificate, then another pop-up window appears to generate the certificate.

You can view the adjusted application segments on the[Defined Application Segments page](https://help.zscaler.com/zpa/about-applications).

### Inspect Application Segments

To inspect at the application segment level:

1. Click the displayed application segment.
2. After you have clicked on the application segment, a pop-up message appears to confirm if you want to enable inspection for the application segment.
  - If you want to continue with inspection of the entire application segment, click **Yes**. The application segment is then inspected. A confirmation message appears notifying you that you have successfully updated the application segment.
  - If you don't want to inspect all of the application segments, click **Cancel**. You can go to the Defined Application page and [edit the application segment](https://help.zscaler.com/zpa/editing-application-segments) to adjust the domain and related ports.

See image.

### Inspect Domains and Ports

To inspect at the domain or port level:

1. Click one of the displayed domains or ports.
2. After you have clicked a domain or port, a pop-up message appears to confirm if you want to enable inspection for all of the UDP and TCP ports assigned to the application segment associated with the selected domain.
  - If you want to continue with inspection of all the ports, click**Yes**. The domain and its related ports for the related application segment are then inspected. A confirmation message appears notifying you that you have successfully updated the application segment.
  - If you don't want to inspect all of the current ports for the application segment that the domain is associated with, click **Cancel**. You can go to the Defined Application page and [edit the application segment](https://help.zscaler.com/zpa/editing-application-segments) to adjust the domain and related ports. Protocol Discovery allows AppProtection to be enabled for a domain with a single port or for all of the TCP or UDP ports configured with the application segment. If you have successfully enabled inspection for only one domain, when you refresh the page, if you click the same domain but with a different port than the one that you inspected, you are prompted to enable inspection on all the ports for that domain.

See image.

### Inspect Protocols

To inspect at the protocol level:

1. Click one of the listed protocols.
2. When you have clicked a protocol, a pop-up message appears to confirm if you want to enable inspection for that specific protocol of the application segment displayed. If you want to continue, click **Yes**. A confirmation message appears notifying you that you have successfully updated the application segment.

See image.

1. After inspection has been enabled, the items that have been selected for inspection are removed from the widget.

[Image: The Enable AppProtection window on the Protocol Discovery Dashboard page]

[Image: Info window to inspect all the domains in the application segment selected]

[Image: Info window to inspect the domain selected]

[Image: Info window to inspect all the domains in the protocol selected]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-quarterly-business-review-reports","lastmod":"2026-04-21T16:15Z","nid":"1519176"} -->
## Viewing Quarterly Business Review Reports

- Source: https://help.zscaler.com/zpa/viewing-quarterly-business-review-reports
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Applications & Users Monitoring > Applications & Users Insights > Viewing Quarterly Business Review Reports
- Last modified: 2026-04-21T16:15Z
- Summary: Descriptions of individual sections within a Private Access (ZPA) Quarterly Business Review report.

Quarterly Business Review (QBR) reports are available for download to all users. The reports provide extensive insight into emerging traffic trends based on private application usage and the types of threats Zscaler blocks to protect your network.

A new QBR report is generated on the first weekend of every month. However, if the first weekend falls on the first or second day of the month, then the report is generated on the following weekend. The QBR reports are securely stored as a PowerPoint file in the Zscaler cloud. The QBR reports are displayed for the last 3 years, and up to 12 reports are stored.

Go to **Logs** > **Insights** > **Usage** > **Quarterly Business Reports** to view a list of downloadable quarterly business reports based on your organization's private application usage.

Each report provides the following information:

- **Highlights of Zscaler in Your Environment**: An architectural overview of your internet, SaaS, and private app traffic. Additionally, it provides an overview of your end users' digital experience data.
- **Global Traffic Distribution**: A geospatial distribution of your users around the world shows the number of users and the traffic percentage originating from each location.
- **Quarterly Snapshot**: The growth in your organization's applications (FQDNs), configured app segments, app segments for browser-related access, and transaction count between the last and current quarter.
- **ZPA Secures User Access to Private Applications**: Displays the quarterly overview of the most used application segments across users, the number of application segments with traffic usage, and the percentage growth.
- **Most Used Application Segments**: Displays the quarterly overview of the top 10 application segments and data transfer (in TBytes) across your organization.
- **Where are my apps hosted?**: A geospatial distribution of your users around the world by showing the number of users, number of App Connectors, and traffic percentage originating from each location. If hosting provider details are not determined, **Unknown** appears next to the name of the private applications in the Bar and Map views. For example, if the provider and region can't be found, but the app's country is Australia, you see **Unknown, Unknown, AU**. For accurate location tracking regarding data center environments, edit the App Connector group, set Hosting Detection to Manual, and then select or enter host data center information. To learn more, see [Configuring App Connectors](https://help.zscaler.com/zpa/configuring-connectors) and [Editing App Connector Groups](https://help.zscaler.com/zpa/editing-connector-groups).
- **Reduce Lateral Threat Movement with User-to-App-Segmentation**: The growth of your private applications, configured application segments, and configured access policies to build granular privileged access.

## Downloading QBR Reports

To download and view the reports:

1. Go to **Logs** > **Insights** > **Usage** > **Quarterly Business Reports**.
2. Click the **Download** icon to download a specific report.

[Image: Download QBR]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-restore-activities-and-restore-reports","lastmod":"2026-06-08T15:43Z","nid":"1485871"} -->
## Viewing Restore Activities and Restore Reports

- Source: https://help.zscaler.com/zpa/viewing-restore-activities-and-restore-reports
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Administration > Backup & Restore > Viewing Restore Activities and Restore Reports
- Last modified: 2026-06-08T15:43Z
- Summary: How to view restore activities and restore reports for the backup and restore feature in the Zscaler Admin Console.

After a [backup](https://help.zscaler.com/zpa/about-backup-and-restore) is created [manually](https://help.zscaler.com/zpa/adding-backups-manually) or [automatically](https://help.zscaler.com/zpa/creating-scheduled-backup-configurations), you can view a list of restore activities, or you can view the post-restore reports for the particular backup.

## Accessing a List of Restore Activities for a Backup

To access a list of restore activities for a particular backup:

1. Go to **Administration**> **Backup & Restore** > **Private Applications**.
2. In the table, locate the backup you want to view the restore activities and restore reports for, and click on the individual backup name in the **Backup Name** column. The **Restore Activities**drawer appears. See image.
3. In the **Restore Activities** drawer, you can:
  - Click the **Restore**icon () to open the **View Report and Restore** drawer. In the **View Report and Restore** drawer, you can [view the inconsistency reports before restoring a backup](https://help.zscaler.com/zpa/restoring-policies-and-configurations-backup).
  - View the list of restore activities. Filter the data in the table with your selections, and then click **Apply**. For each restore activity, you can see:
    - **Admin ID**: The admin ID of the admin who restored the backup.
    - **Initiated At**: The time when the backup is restored.
    - **Status**: The status of the restore (**Completed**, **Failure**, **In Progress**).
  - Click the **View Restore Report** icon () to view the post-restore reports of the selected backup. The **Restore Report** drawer appears.

[Image: Viewing the Restore Activities drawer]

## Viewing the Post-Restore Reports for a Backup

In the **Restore Report** drawer, you can view the post-restore report view of the backup if the configurations were modified. You can click **Collapse All** to collapse all configurations, click on an individual configuration to see more information, or click the **Download**icon to download the full report as a CSV file. In the **Restore Report** table, you can see:

- **Configuration**: The list of configurations for the backup. Any unchanged configurations are not listed in the table.
- **Status**: The status of the configurations after the backup is restored.

See image.

[Image: Viewing the Restore Reports drawer]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-source-ip-anchoring-dashboard","lastmod":"2026-06-29T10:04Z","nid":"1485891"} -->
## Viewing the Source IP Anchoring Dashboard

- Source: https://help.zscaler.com/zpa/viewing-source-ip-anchoring-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Applications & Users Monitoring > Viewing the Source IP Anchoring Dashboard
- Last modified: 2026-06-29T10:04Z
- Summary: Information on the Source IP Anchoring dashboard and widgets accessible within the Zscaler Admin Console.

The Source IP Anchoring dashboard provides information about your organization's Source IP Anchoring connections. To view the dashboard in the Zscaler Admin Console, go to Analytics > Switch to Existing Reports > Private Applications > Source IP Anchoring.

See image.

[Image: Source IP Anchoring dashboard within the Zscaler Admin Console]

## Dashboard Tools

The Source IP Anchoring dashboard displays the following information and functionality:

- **Time Range Filter**: View Source IP Anchoring data over a period between **7 Days** to **6 Months**, or you can select **Custom Range**. This filter applies to all widgets on the dashboard. By default, the dashboard displays information for events that occurred in the last 7 days.
- **Refresh icon**: Refresh the dashboard to reflect the most current information.
- **Average Number of Users**: View real-time information about the number of users per day or month for the selected time period.
- **Average Total Bandwidth Consumption**: View real-time information about the average bandwidth consumed per day or month for the selected time period.

[Image: Source IP Anchoring dashboard tools]

## Widgets

The Source IP Anchoring dashboard provides the following widgets:

- Bandwidth Consumption
- Active Users
- Top Application Segments by Bandwidth

The widget displays total and average per user bandwidth consumption for the selected time interval.

[Image: Bandwidth Consumption widget]

- Hover over a date to view:
  - **Total**: The total bandwidth consumed for Source IP Anchoring applications.
  - **Average per user**: The average bandwidth consumed per user.
- Click the **Download**icon () to export a file (.csv) containing information on the total bandwidth consumed, and average bandwidth consumed per user for the time interval.

The widget displays the total unique Source IP Anchoring users for the selected time interval.

[Image: Active Users widget]

- Hover over a date to view the number of active Source IP Anchoring users by date.
- Click the **Download**icon () to export a file (.csv) containing information on the number of active Source IP Anchoring users for the time interval.

The widget displays the top application segments by bandwidth for the selected time interval.

[Image: Top Application Segments by Bandwidth widget]

- Hover over a section of the pie or horizontal bar chart to see the applications. Click **View Applications** to view the top five applications for the selected application segment.
- Click the **Pie**or **Bar**icon () to display data in pie or bar format.
- Click the **Download**icon () to export a file (.csv) containing information on top application segments by bandwidth.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-users-dashboard","lastmod":"2026-07-20T10:12Z","nid":"1483761"} -->
## Viewing the Users Dashboard

- Source: https://help.zscaler.com/zpa/viewing-users-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Applications & Users Monitoring > Viewing the Users Dashboard
- Last modified: 2026-07-20T10:12Z
- Summary: Information on the Users dashboard and widgets accessible within the Zscaler Admin Console.

The Users dashboard provides information about user activity in your organization. To view the dashboard in the Zscaler Admin Console, go to Analytics > Switch to Existing Reports > Private Applications > Users.

See image.

[Image: Users Dashboard within the Zscaler Admin Console]

## Dashboard Tools

The Users dashboard displays the following information and functionality:

- **Time Range Filter**: View user data over a period between **1 Hour** to **14 Days**, or you can select **Custom Range**. If you use a **Custom Range**, the start and end date must be within the last 14 days. The end time can be configured to the selected time in hours and minutes. By default, the dashboard displays information for events that occurred in the last hour. This filter applies to all widgets on the dashboard except **Current Connected Users**, which displays users for up to the last 30 minutes.

Log information in the dashboard is limited to 14 days. For longer access to the logs, use the [Log Streaming Service (LSS)](https://help.zscaler.com/zpa/about-log-streaming-service).

- **Federated By**: Filter data in the dashboard by users that are federated by your tenant (i.e., **My Tenant**), or by users that are federated by your partner (i.e., **Partner**). If the **Federated By** filter is set to **Partner**, then the **Partner**filter and a valid value must be applied in addition to the **Federated By** filter. To learn more, see [Understanding Business-to-Business (B2B) Federation](https://help.zscaler.com/zpa/understanding-business-business-b2b-federation).
- **Partner**: Filter data in the dashboard by the federated partner. The **Partner** filter must be applied with the**Federated By** filter, and is only visible when the **Federated By** filter is set to **Partner**. To learn more, see [About Federated Partners](https://help.zscaler.com/zpa/about-federated-partners).
- **Refresh Icon**: Refresh the dashboard to reflect the most current information.
- **Recent Users**: View the total number for this category at the top of the page, and then view its details in the widget below. This number is based on the users that accessed applications, and it includes all successful and unsuccessful transactions.
- **Current Connected**: View the total number for this category at the top of the page, and then view its details in the widget below. This number is based on the users who have logged into the Zscaler Client Connector and who are authenticated and connected to Private Access.
- **Top Policy Blocks**: View the total numbers for this category at the top of the page, and then view its details in the widget below.
- **Chart Selection**: Select the charts you want to display or hide on the dashboard. A minimum of 3 charts and a maximum of 6 charts can be selected.

See image.

[Image: Accessing the Time Period filter, Refresh icon, and totals for Recent Users, Connected Users, and Policy Blocks within the Zscaler Admin Console]

## Widgets

The Users dashboard provides the following widgets:

- Recent Users
- Current Connected Users
- Users Blocked by Policies
- Top Users by Applications
- Top Users by Bandwidth
- Top Policies by Blocked Users
- Top Users by Tunnel Count

If you are using the [Log Streaming Service](https://help.zscaler.com/zpa/about-log-streaming-service), the Users dashboard includes information for a ZPA LSS Client user. This user represents the LSS service, not an actual user. Also, each log receiver is displayed as an application, to reflect the data coming in from the service. To learn more, including how to stop the LSS service from streaming ZPA LSS Client logs, see [Configuring a Log Receiver](https://help.zscaler.com/zpa/configuring-log-receiver#Step2).

This widget displays real-time information about the total number of users that requested applications and successful machine tunnel connections over the selected time frame. The widget always uses the current time for its end time even if a custom time range with a different end time is selected.

For users accessing applications using Browser Access through an unauthenticated HTTP preflight OPTIONS request, the username will appear as **ZPA BA Unauthenticated**. To learn more, see [Configuring Application Segments](https://help.zscaler.com/zpa/configuring-defined-application-segments#BrowserAccess_NewAppSeg).

[Image: Users Dashboard with Recent Users widget]

- Click the navigation tabs to view a list of users or machine tunnels.
- Click on a user or machine tunnel to view more details in **Diagnostics**.
- Click the **Download** icon ([Image: Download icon within the ZPA Admin Portal]) to export a file (.csv) containing information on the users that accessed applications or successful machine tunnel connections with Private Access for the selected time frame (i.e., timestamp (in UTC) and username).

This widget displays real-time information of users and machine tunnels currently connected to Private Access.

[Image: Users Dashboard with Current Connected Users widget]

- Click on the navigation tabs to view a list of users or machine tunnels.
- Hover over a user or machine tunnel to view the following:
  - **Logged In Time**:The time the user logged in.
  - **IP Address**:The IP address of the user's device.
  - **User Location**:The location the user is connecting from.
- Click on a user or machine tunnel to view more details in **Diagnostics**.
- Click the **Download** icon ([Image: Download icon within the ZPA Admin Portal]) to export a file (.csv) containing information on the users or machine tunnels that are currently accessing applications with Private Access for up to the last 30 minutes.

This widget displays users that have been blocked by your organization's configured policies in the selected time frame.

[Image: Users Blocked by Policies widget]

- Hover over a user to view the following:
  - **Name**: The username. This is the NameID in the SAML assertion from the IdP and not the username entered in Zscaler Client Connector.
  - **# of Policy Blocks**: The number of times the user was blocked by a policy in the selected time frame and the percentage that number represents of the total number of times users were blocked by policies, as shown in the widget.
- Click on a user to view more details in **Diagnostics**.

This widget displays the top 10 users of applications in the selected time frame. The percentage of total transactions for the top users by applications appears in the top-right corner.

[Image: Top Users by Application widget]

- Hover over a user to view the following:
  - **Name**: The username. This is the NameID in the SAML assertion from the IdP and not the username entered in Zscaler Client Connector.
  - **# of App(s)**: The number of applications accessed by the user in the selected time frame and the percentage that amount represents of the total number of applications the top 10 users accessed, as shown in the widget.
- Click on a user to view more details in **Diagnostics**.

This widget displays the top 10 consumers of bandwidth in the selected time frame. The percentage of total transactions for the top users by bandwidth appears in the top-right corner.

[Image: Top Users by Bandwidth widget]

- Hover over a user to view the following:
  - **Name**: The username. This is the NameID in the SAML assertion from the IdP and not the username entered in Zscaler Client Connector.
  - **Bandwidth**: The amount of bandwidth consumed by the user in the selected time frame and the percentage that number represents of the total bandwidth consumed by the ten users shown in the widget.
- Click on a user to view more details in **Diagnostics**.

This widget displays the top 10 policies that blocked users in the selected time frame.

[Image: Top Policies By Blocked Users widget]

- Hover over a policy to view the following:
  - **Name**: The policy name.
  - **# of Policy Blocks**: The number of users the policy blocked in the selected time frame and the percentage that number represents of the total number of users blocked by the ten policies shown in the widget.
- Click on a policy to view more details in **Diagnostics**.

This widget displays the top users by tunnel count in the selected time frame. The percentage of total transactions for the top users by tunnel count appears in the top-right corner.

[Image: Top Users by Tunnel Count widget on the Users dashboard]

- Hover over a policy to view the following:
  - **Name**: The username. This is the NameID in the SAML assertion from the IdP, and not the username entered in Zscaler Client Connector.
  - **Number of MTunnel(s)**: The number of tunnel counts accessed by the user in the selected time frame, and the percentage of that amount that represents the total number of tunnel counts accessed by the users shown in the widget.
- Click a policy to view more details in **Diagnostics**.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-vpn-service-edges-dashboard","lastmod":"2026-08-11T13:33Z","nid":"1542720"} -->
## Viewing the VPN Service Edges Dashboard

- Source: https://help.zscaler.com/zpa/viewing-vpn-service-edges-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > VPN (for Legacy Apps) > Dashboard & Diagnostics > Viewing the VPN Service Edges Dashboard
- Last modified: 2026-08-11T13:33Z
- Summary: Information about the VPN Service Edge dashboard and widgets available in the Zscaler Admin Console.

The VPN Service Edges Dashboard provides information about the VPN Service Edges for your organization. To view the dashboard in the Zscaler Admin Console, go to Infrastructure > Private Access > Component > VPN Service Edges Dashboard.

The dashboard displays a series of interactive timeseries charts. These charts show how data changes over a selected period and help you identify trends, patterns, and anomalies in performance and usage. You can hover over any point in a chart to see the specific value at that time.

[Image: Learning about the widgets on the VPN service edges dashboard]

## Dashboard Tools

The VPN Service Edges Dashboard displays the following information and functionality:

- **VPN Service Edge selection**: Select the VPN Service Edge that you want to view data for from the drop-down menu.
- **Time Period Filter**: View VPN Service Edge data over a period between 1 hour to 14 days, or you can select **Custom**. If you use a custom range, the start and end dates must be within the last 14 days. You can adjust the time in hours and minutes for the start and end dates. This filter applies to all widgets on the dashboard. By default, the dashboard displays information about events that occurred in the last hour. Due to how data is aggregated for different time period filters, the same point in time in an Activity Monitor widget might show slightly different values depending on the selected time. For example, the data with the 1 Hour time period filter at 3:00 PM might not match the data with the 14 Days time period filter for the same date at 3:00 PM.
- **Refresh icon**: Refresh the dashboard to adjust the time period filter to reflect the most current information.

## Client Connectors Widgets

The Client Connectors widgets provide a real-time overview of user device connectivity and data traffic.

The following widgets are available:

- **Total Number of Client Connectors**: Displays the total count of individual Zscaler Client Connectors that are currently connected to the VPN Service Edge.
- **Bytes Received from Client Connectors**: Displays the volume of data traffic received from Zscaler Client Connectors to monitor inbound traffic from users.
- **Bytes Sent to Client Connectors**: Displays the volume of data traffic sent to Zscaler Client Connectors to monitor outbound traffic to users.

## Network Connectors Widgets

The Network Connectors widgets show traffic from your private network locations. You can use these widgets to monitor traffic loads and plan ahead for potential network issues. This information helps you decide when to add Network Connectors for proper scaling and ensures service reliability.

The following widgets are available:

- **Total Number of Network Connectors**: Displays the total count of Network Connectors currently connected to the VPN Service Edge.
- **Bytes Received from Network Connectors**: Displays the throughput of data received from Network Connectors, showing traffic from your internal networks.
- **Bytes Sent to Network Connectors**: Displays the throughput of data sent to Network Connectors, showing traffic directed towards your internal networks.

## VPN Service Edges Widgets

The VPN Service Edges widgets provide key performance indicators and overall health metrics for VPN Service Edges.

The following widgets are available:

- **Total Number of VPN Service Edges**: Displays the total count of VPN Service Edges deployed within your organization.
- **Bytes Received from VPN Service Edges**: Displays the total volume of inbound data processed by the VPN Service Edges from all sources.
- **Bytes Sent to VPN Service Edges**: Displays the total volume of outbound data sent from the VPN Service Edges to all destinations.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-where-your-apps-are-served","lastmod":"2026-04-21T16:21Z","nid":"1529636"} -->
## Viewing Where Your Apps Are Served

- Source: https://help.zscaler.com/zpa/viewing-where-your-apps-are-served
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Applications & Users Monitoring > Applications & Users Insights > Viewing Where Your Apps Are Served
- Last modified: 2026-04-21T16:21Z
- Summary: How to view the Where are my apps being served from? insights.

The Where are my apps being served from? page provides a view of hosting providers and analytical details of the App Connectors that have been serving your applications for the last quarter. You can view and understand where your applications are hosted because App Connectors act as a good proxy for your private applications in certain cases. While observing and understanding trends, you can filter and sort to customize your view to focus on details based on your selection.

The report is generated on the same schedule as the [quarterly business reports](https://help.zscaler.com/zpa/viewing-quarterly-business-review-reports).

If hosting provider details are not determined, **Unknown** appears next to the name of the private applications in the Bar and Map views. For example, if the provider and region can't be found, but the app's country is Australia, you see **Unknown, Unknown, AU**.
For accurate location tracking regarding data center environments, edit the App Connector group, set Hosting Detection to Manual, and then select or enter host data center information. To learn more, see [Configuring App Connectors](https://help.zscaler.com/zpa/configuring-connectors) and [Editing App Connector Groups](https://help.zscaler.com/zpa/editing-connector-groups).

## Bar View

The Bar view provides a visual and analytical representation of the number of FQDNs, application segments, users, and transactions using horizontal bar graphs.

[Image: Bar View displays bar graphs of private applications]

In the Bar view, you can:

- Sort by **FQDNs**, **App Segments**, **Users**, or **Transactions** in ascending or descending order to view private applications. See image.
- Filter private applications based upon selection (**AWS**, **Azure**, **Google Cloud**, **Data Center**). At least one private application must be selected to display data. See image.
- Switch between the Map and Bar view.
- Hover over a bar to view the number of FQDNs, App Segments, Users, Transactions, and Location details. See image.

If hosting provider details are not determined, **Unknown** appears next to the name of the private applications in Bar view. For accurate location tracking regarding data center environments, edit the App Connector group, set **Hosting Detection** to **Manual**, and then select or enter host data center information. To learn more, see [Configuring App Connectors](https://help.zscaler.com/zpa/configuring-connectors) and [Editing App Connector Groups](https://help.zscaler.com/zpa/editing-connector-groups).

## Map View

The Map view displays the geographic locations of all cloud providers based upon your selection. Zscaler uses the user device's location service to determine the general vicinity of the private application's location.

[Image: View insights on the Where are my apps being served from? page]

In the Map view, you can:

- Sort by **FQDNs**, **App Segments**, **Users**, or **Transactions** in ascending or descending order to view a list of private applications. Click **Prev** or **Next** to navigate between items. The map automatically adjusts its location based on the items selected. See image.
- Filter private applications based upon selection (**AWS**, **Azure**, **Google Cloud**, **Data Center**). See image.
- Switch between the Map and Bar view.
- Zoom in, zoom out, center your position, or reset your view to review areas of interest. You can zoom in and out with your mouse's scroll wheel. See image.
- Hover over a marked location to view the number of FQDNs, application segments, users, transactions, and location details. See image.

[Image: Sort the list of private applications in ascending or descending order or navigate through the list.]

[Image: Filter based on private applications]

[Image: Use the Zoom menu to interact with map]

[Image: Hover over a location to view more details]

[Image: Use Sort By to select items in ascending or descending order]

[Image: Select filters to specify private applications to view]

[Image: Hover over a bar to view details of the private application]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/vpn-redundancy-deployment-guide","lastmod":"2026-08-07T07:38Z","nid":"1535291"} -->
## VPN Redundancy Deployment Guide

- Source: https://help.zscaler.com/zpa/vpn-redundancy-deployment-guide
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > VPN (for Legacy Apps) > VPN Redundancy Deployment Guide
- Last modified: 2026-08-07T07:38Z
- Summary: Information on how to enable VPN redundancy for your Network Connectors.

This guide explains how to configure VPN redundancy for your organization's traffic using Network Connectors that support redundancy within a Network Connector group. Network Connectors that support redundancy solve the following failure scenarios:

- A Network Connector link failure
- An internal link failure towards a customer router
- An external link failure towards the VPN Service Edge
- A complete Network Connector node failure
- A data center failure with traffic being rerouted towards another data center

With the help of Border Gateway Protocol (BGP), you can scale your network with Equal-Cost Multi-Path (ECMP) to load balance traffic to the Network Connectors from the VPN Service Edges, and the traffic from the routers to the Network Connectors.

If you want to upgrade your existing VPN (for Legacy Apps) configuration with redundancy capability, see [Replacing or Migrating Existing Network Connectors with Network Connectors that Support Redundancy](https://help.zscaler.com/zpa/replacing-or-migrating-network-connectors-support-redundancy).

With a BGP router, you can configure redundancy using the following steps:

- Step 1: Add VPN Service Edges
- Step 2: Configure BGP for VPN Service Edges
- Step 3: Add External Routers
- Step 4: Add Network Connectors
- Step 5: Deploy Network Connectors by Platform
- Step 6: Configure BGP for Network Connectors
- Step 7: Add Network Segments
- Step 8: View the BGP Dashboard
- Step 9: Add Access Policies

To add a VPN Service Edge, see the following articles:

- [About VPN Service Edges](https://help.zscaler.com/zpa/about-vpn-service-edges)
- [Configuring VPN Service Edges](https://help.zscaler.com/zpa/configuring-vpn-service-edges)

To configure BGP for VPN Service Edges, see [Configuring Border Gateway Protocols for VPN Service Edges](https://help.zscaler.com/zpa/configuring-border-gateway-protocol-vpn-service-edges).

To add external routers, see the following articles:

- [About External Routers](https://help.zscaler.com/zpa/about-external-routers)
- [Configuring External Routers](https://help.zscaler.com/zpa/configuring-external-routers)

To add Network Connectors, see the following articles:

- [About Network Connectors](https://help.zscaler.com/zpa/about-network-connectors)
- [Configuring Network Connectors](https://help.zscaler.com/zpa/configuring-network-connectors)

To create Network Connectors using a provisioning key, see [About Network Connector Provisioning Keys](https://help.zscaler.com/zpa/about-network-connector-provisioning-keys).

Ensure the following settings for the Network Connector group when configuring Network Connectors:

- **Advertise LAN Segments**: Select **Disabled** to use BGP.
- **External Routers**: Select the routers you want to associate with the Network Connector group.
- **Keepalive**: Select the frequency and wait time for the keepalive message (in seconds) for BGP before a session times out.
- **Enable Multi**-**Hop**: Enable this option to allow BGP sessions to be established between Network Connectors and the external routers that are not directly connected on the same network subnet.
- **Interface**: Enter the interface name that has connectivity to the BGP router.

Ensure that the Manager software version for the new Network Connectors is 25.46.2 or later.

To deploy Network Connectors based on your platform, see [Network Connector Software by Platform](https://help.zscaler.com/zpa/network-connector-software-platform).

Ensure the following based on the platform:

- VMware
- AWS
- Azure

If you are installing Network Connectors without using the prebuilt virtual image from Zscaler, make sure you configure the Zscaler repository so the Network Connector can download and install the FRR package, which is essential for BGP to work. To learn more, see [Network Connector Deployment Guide for Linux](https://help.zscaler.com/zpa/network-connector-deployment-guide-linux).

- Assign a static private IP address.
- Use only one network interface.
- Use version VMware ESXi 7.0 Update 2 or later.

For the deployed Network Connector instance, ensure that the **Source / destination checking** option is disabled (**Actions**> **Networking**>**Change source / destination check**).

For the deployed Network Connector VMs, ensure that the **IP forwarding** option is enabled (**Settings > IP configurations**).

To configure BGP for Network Connectors, see [Configuring Border Gateway Protocols for Network Connectors](https://help.zscaler.com/zpa/configuring-border-gateway-protocol-network-connectors).

To configure network segments, see the following articles:

- [About Network Segments](https://help.zscaler.com/zpa/about-network-segments)
- [Configuring Network Segments](https://help.zscaler.com/zpa/configuring-network-segments)

The routes advertised by the BGP routers must match the IP subnets defined in the network segments for the BGP to work.

You can check the BGP states and statuses on the BGP Peers Dashboard. To learn more, see [Viewing the BGP Peers Dashboard](https://help.zscaler.com/zpa/viewing-bgp-peers-dashboard).

To add access polices, see the following articles:

- [About VPN Access Policy](https://help.zscaler.com/zpa/about-vpn-access-policy)
- [Configuring VPN Access Policies](https://help.zscaler.com/zpa/configuring-vpn-access-policies)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/what-is-microsegmentation","lastmod":"2026-08-05T13:33Z","nid":"1531937"} -->
## What Is Microsegmentation?

- Source: https://help.zscaler.com/zpa/what-is-microsegmentation
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Microsegmentation > What Is Microsegmentation?
- Last modified: 2026-08-05T13:33Z
- Summary: Information about Microsegmentation in the Zscaler Admin Console.

Zscaler Microsegmentation is a platform within the [Zscaler Admin Console](https://help.zscaler.com/unified/signing-zscaler-admin-console) that provides the capability to visualize traffic flows within private applications and segment them on a fractional level, reducing the attack surface and preventing lateral movement of any threats on your network. It is a multi-tenant software-as-a-service solution optimized for security, reliability, and scale, using a system divided into the Zscaler cloud and deployed agents. The Zscaler cloud and agents work together to collect and analyze application flow and telemetry data and also monitor the health of all managed systems.

The deployed agents sit on Windows or Linux hosts in your environment and collect information about application activity. Agents are deployed to your servers, whether they are virtual or physical, cloud based, or in your data center. The agents are responsible for downloading the latest access policies from the Zscaler cloud and translating them to rules that are specific to local OS enforcement points (e.g., Windows Filtering Platform and Linux nftables). You can create [agent groups](https://help.zscaler.com/zpa/configuring-agent-groups) and [AppZones](https://help.zscaler.com/zpa/configuring-appzones) that group together specific machines and their applications that you want to monitor for certain data flows.

You can enable Microsegmentation for organizations that have [Private Access (ZPA)](https://help.zscaler.com/zpa/what-zscaler-private-access). The backend framework is hosted and managed by the Zscaler cloud and is available across the US region. The data collection is localized to the region of choice of the administrator, and the data retention cycle is a 14-day rolling period. You can deploy the managed resources to any other region. The installed agents operate in continuous mode.

If you want Microsegmentation provisioned for your organization, contact your Zscaler Account team.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/what-zscaler-private-access","lastmod":"2026-06-01T08:36Z","nid":"1483511"} -->
## What Is Zscaler Private Access?

- Source: https://help.zscaler.com/zpa/what-zscaler-private-access
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > What Is Zscaler Private Access?
- Last modified: 2026-06-01T08:36Z
- Summary: Introductory information about Private Access and its cloud-based infrastructure.

The Private Access (ZPA) service enables organizations to provide access to internal applications and services while ensuring the security of their networks. Private Access is an easier-to-deploy, more cost-effective, and more secure alternative to VPNs. Unlike VPNs, which require users to connect to your network to access your enterprise applications, Private Access allows you to give users policy-based secure access only to the internal apps they need to get their work done. With Private Access, application access does not require network access.

While Private Access is for connecting users to an enterprise's internal applications, Internet & SaaS (ZIA) is for connecting users to public applications on the internet. To learn more about Internet & SaaS architecture, see [Understanding the Zscaler Cloud Architecture for Internet & SaaS](https://help.zscaler.com/zia/understanding-zscaler-cloud-architecture).

Additionally, Private Access decouples applications from the physical network so you can provide seamless connectivity to private internal applications and assets, whether they are in the cloud, the data center, or both. It also adjusts dynamically to network changes, so you can move your resources without impacting user access.

You can configure settings and policies in a central [Zscaler Admin Console](https://help.zscaler.com/unified/signing-zscaler-admin-console), which also features dashboards where you can see your [users](https://help.zscaler.com/zpa/about-users-dashboard) and the [apps](https://help.zscaler.com/zpa/viewing-applications-dashboard) they access, and [monitor the health of your servers and resources](https://help.zscaler.com/zpa/understanding-health-reporting). You can configure Private Access to automatically discover servers and applications when users request them, or you can configure them manually as well. You then define [policies](https://help.zscaler.com/zpa/policies) that specify which apps users or groups can use, and Private Access allows them to connect to those apps only. Private Access renders your applications invisible to all but the authorized users and unroutable to anyone.

Like all Zscaler offerings, the Private Access service is based on the Zero Trust Exchange (ZTE), so there is no requirement for additional hardware or upgrades to existing hardware.

To learn more, see the [Private Access Overview](https://www.zscaler.com/products/zscaler-private-access).

## Key Features and Benefits

Private Access offers the following key features and benefits:

- Seamless User Experience: Policy-driven connectivity that dynamically adjusts to network changes.
- Enhanced Security: Application-specific connectivity without ever bringing users on-net.
- Ease of Deployment: Does not require hardware or hardware upgrades.
- Instant Deployment and Discovery: Can automatically discover applications so you can easily build policies around them.
- Single Sign-On (SSO): Private Access is tied directly to your existing authentication infrastructure, leveraging SSO to further reduce complexity.
- Real-Time Visibility: Dashboards provide unparalleled visibility into your users and applications, and the health of your organization's applications and servers

## How Private Access Works

Private Access uses Zscaler's cloud-based, elastically scalable infrastructure to deliver seamless connectivity to your private internal applications and assets. Its key components are as follows:

- Zscaler Client Connector: Installed on your users' devices, Zscaler Client Connector connects to the Private Access cloud to enable granular, policy-based access to your organization's internal resource.

Zscaler Client Connector can also forward your users' traffic to the ZTE to secure their internet traffic as well. To learn more, see [What Is Zscaler Client Connector?](https://help.zscaler.com/zscaler-client-connector/what-is-zscaler-client-connector)

- App Connectors: Lightweight virtual machines (VM) that are installed in the data centers that host your servers and applications. They connect to Public Service Edges or Private Service Edges only to provide users access to applications in your data center, and do not accept inbound connections. To learn more, see [About App Connectors](https://help.zscaler.com/zpa/about-connectors).
- Global Zscaler cloud: Stitches all components together. The Central Authority (CA) provides a central location for software updates as well as policy and configuration settings. The Public Service Edges or Private Service Edges enforce user policies and provide secure transport to the App Connectors.

Users install Zscaler Client Connector on their devices and can then log into an application using SAML 2.0-based SSO credentials. When users request access to an internal application, Zscaler Client Connector uses geolocation technology to locate the Public Service Edge or Private Service Edge closest to them. Zscaler Client Connector presents its certificate to the Public Service Edge or Private Service Edge to confirm its identity, and then establishes a secure tunnel to the Public Service Edge or Private Service Edge. The Public Service Edge or Private Service Edge then retrieves the user's policies from the CA, and depending on the internal application requested by the user, the Public Service Edge or Private Service Edge contacts the appropriate App Connector. The App Connector presents its certificate to the Public Service Edge or Private Service Edge, and when the Public Service Edge or Private Service Edge confirms the App Connector's identity, it allows the App Connector to connect to it.

When the connection is established between the user's device and the application, the traffic traversing the solution remains completely isolated.

Because Private Access is built on the premise of Zero Trust for your private applications, the traffic is isolated from Zscaler as well.

Private Access admins can view [dashboards](https://help.zscaler.com/zpa/dashboard-diagnostics) and [diagnostics](https://help.zscaler.com/zpa/dashboard-diagnostics) to filter for detailed information about users and applications, as well as monitor the health of their organization's applications, servers, and App Connectors.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zero-trust-access-private-apps-aws-zscaler-private-access","lastmod":"2023-09-22T13:04Z","nid":"1485076"} -->
## Zero Trust Access to Private Apps in AWS with Zscaler Private Access

- Source: https://help.zscaler.com/zpa/zero-trust-access-private-apps-aws-zscaler-private-access
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Reference Architecture > Zero Trust Access to Private Apps in AWS with Zscaler Private Access
- Last modified: 2023-09-22T13:04Z
- Summary: The Zero Trust Access to Private Apps in Amazon Web Services (AWS) with Zscaler Private Access (ZPA) reference architecture guide that steers you through the architecture process, and provides technical deep dives into specific platform functionality and integrations.

The Zscaler Reference Architecture series delivers best practices based on real-world deployments. The recommendations in this series were developed by Zscaler's transformation experts from across the company. This guide will steer you through the architecture process and provide technical deep dives into specific platform functionality and integrations. The Zscaler Reference Architecture series is designed to be modular, so this guide will show you how to configure a different aspect of the platform in order to allow you meet your specific policy goals.

Zscaler Private Access (ZPA) is a part of the Zscaler Zero Trust Exchange platform. ZPA provides secure access, based on a zero-trust framework, to private applications—including remote access to internal applications running on Amazon Web Services (AWS). With ZPA, applications are never exposed to the internet, making them completely invisible to unauthorized users.

[Download English PDF](https://help.zscaler.com/downloads/zpa/reference-architecture/zero-trust-access-private-apps-aws-zscaler-private-access/zero-trust-access-private-apps-aws-zpa.pdf)

[Download Japanese PDF](https://help.zscaler.com/downloads/zpa/reference-architecture/zero-trust-access-private-apps-aws-zscaler-private-access/zscaler-architecture-guide-ZPA-R9-15dec21_ja-JP.pdf)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zero-trust-access-private-apps-microsoft-azure-zscaler-private-access","lastmod":"2022-11-04T13:48Z","nid":"1485486"} -->
## Zero Trust Access to Private Apps in Microsoft Azure with Zscaler Private Access

- Source: https://help.zscaler.com/zpa/zero-trust-access-private-apps-microsoft-azure-zscaler-private-access
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Reference Architecture > Zero Trust Access to Private Apps in Microsoft Azure with Zscaler Private Access
- Last modified: 2022-11-04T13:48Z
- Summary: The Zero Trust Access to Private Apps in Microsoft Azure with Zscaler Private Access (ZPA) reference architecture guide that steers you through the architecture process, and provides technical deep dives into specific platform functionality and integrations.

The Zscaler Reference Architecture series delivers best practices based on real-world deployments. The recommendations in this series were developed by Zscaler's transformation experts from across the company. This guide will steer you through the architecture process and provide technical deep dives into specific platform functionality and integrations. The Zscaler Reference Architecture series is designed to be modular, so this guide will show you how to configure a different aspect of the platform in order to allow you meet your specific policy goals.

Zscaler Private Access (ZPA) is a part of the Zscaler Zero Trust Exchange platform. ZPA provides secure access, based on a zero-trust framework, to private applications—including remote access to internal applications running on Microsoft Azure. With ZPA, applications are never exposed to the internet, making them completely invisible to unauthorized users.

[Zero Trust Access to Private Apps in Microsoft Azure with Zscaler Private Access: Reference Architecture Guide](https://help.zscaler.com/downloads/zpa/reference-architecture/zero-trust-access-private-apps-microsoft-azure-zscaler-private-access/Zero-Trust-Access-to-Private-Apps-in-Azure-with-Zscaler-Private-Access.pdf) [Download PDF](https://help.zscaler.com/downloads/zpa/reference-architecture/zero-trust-access-private-apps-microsoft-azure-zscaler-private-access/Zero-Trust-Access-to-Private-Apps-in-Azure-with-Zscaler-Private-Access.pdf)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zero-trust-user-to-app-segmentation-zpa","lastmod":"2025-11-06T09:14Z","nid":"1485376"} -->
## Zero Trust User-to-App Segmentation with ZPA

- Source: https://help.zscaler.com/zpa/zero-trust-user-to-app-segmentation-zpa
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Reference Architecture > Zero Trust User-to-App Segmentation with ZPA
- Last modified: 2025-11-06T09:14Z
- Summary: The Zero Trust User-to-App Segmentation with Zscaler Private Access (ZPA) reference architecture guide that steers you through the architecture process, and provides technical deep dives into specific platform functionality and integrations.

The Zscaler Reference Architecture series delivers best practices based on real-world deployments. The recommendations in this series were developed by Zscaler's transformation experts from across the company. This guide will steer you through the architecture process and provide technical deep dives into specific platform functionality and integrations. The Zscaler Reference Architecture series is designed to be modular, so this guide will show you how to configure a different aspect of the platform in order to allow you meet your specific policy goals.

Zero trust has become a popular model for secure user access to applications and resources. Moving away from the traditional VPN style of network access, zero trust is an approach that focuses on granular user-to-app segmentation. Zscaler Private Access (ZPA) gives users access to applications without requiring users to share a network context with the applications. Where VPNs assign a user an IP address and place the user on the network, ZPA enables users to connect only to allowed applications, with no access to adjacent applications or systems. The user can be anywhere, and the application can be hosted in any location. Granular context-based policy can control application visibility and access by end users.

[Download English PDF](https://help.zscaler.com/downloads/zpa/reference-architecture/zero-trust-user-to-app-segmentation-zpa/zero-trust-user-to-app-segmentation-with-zpa.pdf)

[Download Japanese PDF](https://help.zscaler.com/downloads/zpa/reference-architecture/zero-trust-user-to-app-segmentation-zpa/zero-trust-user-to-app-segmentation-zpa_ja-JP.pdf)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-app-connector-software-by-platform","lastmod":"2026-08-06T07:23Z","nid":"1485956"} -->
## App Connector Software by Platform

- Source: https://help.zscaler.com/zpa/zpa-app-connector-software-by-platform
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > App Connector Management > App Connector Deployment Guides for Supported Platforms > App Connector Software by Platform
- Last modified: 2026-08-06T07:23Z
- Summary: The current App Connector software downloads by platform.

App Connectors are supported on [many different platforms](https://help.zscaler.com/zpa/app-connector-management/app-connector-deployment-guides-supported-platforms). Each supported platform has an App Connector image you can use to deploy App Connectors on that platform. To learn more, see [App Connector Deployment Guides for Supported Platforms](https://help.zscaler.com/zpa/app-connector-management/app-connector-deployment-guides-supported-platforms) for detailed deployment instructions.

The following platforms support App Connector software packages. Where applicable, see the download links for images, public keys, and checksums.

| Platform | Software |
| --- | --- |
| Colocation / Data Centers |  |
| [Nutanix AHV](https://help.zscaler.com/zpa/app-connector-deployment-guide-nutanix-ahv#deploy) | [STIG OVA](https://dist.private.zscaler.com/vms/VMware/2026.07/zpa-connector-el9-2026.07.ova) [STIG OVA checksum](https://dist.private.zscaler.com/vms/VMware/2026.07/zpa-connector-el9-2026.07.ova.sha256sum) |
| [VMware](https://help.zscaler.com/zpa/connector-deployment-vmware-appliance-vmware-vcenter) | [STIG OVA](https://dist.private.zscaler.com/vms/VMware/2026.07/zpa-connector-el9-2026.07.ova) [STIG OVA checksum](https://dist.private.zscaler.com/vms/VMware/2026.07/zpa-connector-el9-2026.07.ova.sha256sum) |
| Clouds |  |
| [Amazon Web Services (AWS)](https://help.zscaler.com/zpa/connector-deployment-guide-amazon-web-services#deploy) | [Private Access - AWS Marketplace (STIG Image)](https://aws.amazon.com/marketplace/pp/prodview-cvvqe5hxw2bku?sr=0-1&ref_=beagle&applicationId=AWSMPContessa) |
| [Google Cloud Platform (GCP)](https://help.zscaler.com/zpa/app-connector-deployment-guide-google-cloud-platform) | [Private Access - GCP Marketplace (STIG Image)](https://console.cloud.google.com/marketplace/product/zpa-gcp-marketplace/zscaler-private-access-connector) |
| [Microsoft Azure](https://help.zscaler.com/zpa/connector-deployment-guide-microsoft-azure#Deployment) | [Private Access - Azure Marketplace (STIG Image)](https://azuremarketplace.microsoft.com/en-us/marketplace/apps/zscaler.zscaler-private-access?tab=overview) |
| Containers |  |
| [Docker](https://help.zscaler.com/zpa/app-connector-deployment-guide-docker) | [Docker Hub](https://hub.docker.com/r/zscaler/zpa-connector/tags) |
| [Kubernetes](https://help.zscaler.com/zpa/app-connector-deployment-guide-kubernetes) | To learn more, see the [App Connector Deployment Guide for Kubernetes](https://help.zscaler.com/zpa/app-connector-deployment-guide-kubernetes). |
| [OpenShift](https://help.zscaler.com/zpa/app-connector-deployment-guide-openshift#deploy) | A Helm Chart must be installed in order to deploy an App Connector on OpenShift. To learn more, see the [App Connector Deployment Guide for OpenShift](https://help.zscaler.com/zpa/app-connector-deployment-guide-openshift#get-helm-chart). |
| Linux Operating Systems |  |
| [App Connector Deployment Guide for Linux](https://help.zscaler.com/zpa/connector-deployment-guide-centos-oracle-and-redhat#Deployment) | The following RPM packages are supported for RHEL App Connector deployments: RHEL 8-based: [RPM package](https://yum.private.zscaler.com/yum/el8/zpa-connector-26.55.1-1.el8.x86_64.rpm); [GPG public key](https://yum.private.zscaler.com/yum/el8/gpg)RHEL 9-based: [RPM package](https://yum.private.zscaler.com/yum/el9/zpa-connector-26.55.1-1.el9.x86_64.rpm); [GPG public key](https://yum.private.zscaler.com/yum/el9/gpg)You must have the RHEL operating system deployed and running. |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-appliance-physical-port-mapping","lastmod":"2026-06-29T07:06Z","nid":"1540962"} -->
## ZPA Appliance Physical Port Mapping

- Source: https://help.zscaler.com/zpa/zpa-appliance-physical-port-mapping
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > ZPA Appliance > ZPA Appliance Physical Port Mapping
- Last modified: 2026-06-29T07:06Z
- Summary: A description of the physical ports on the ZPA hardware appliance, and its interface, port types, and roles.

This article depicts the physical ports on the ZPA hardware appliance and identifies its interface names, port types, and roles. You can configure the ports using `kubectl` commands from the command-line interface.

The following image and table description describe the physical port mapping on the ZS-ZPA-APPL-8010 and ZS-ZPA-PSE-8010 appliances.

[Image: ZS-ZPA-APPL-8010 Appliance physical port diagram that identifies the console port and 18 interface ports]

| Port | Interface | Port Type | Speed | Role |
| --- | --- | --- | --- | --- |
| C | Console | RJ45 | 1GbE | Serial console |
| 1 | GE1/eno1 | RJ45 | 1GbE | Management |
| 2 | GE2/enp1s0 | RJ45 | 1GbE | LAN |
| 3 | GE3/ens84f3 | RJ45 | 1GbE | LAN |
| 4 | GE4/ens84f2 | RJ45 | 1GbE | LAN |
| 5 | GE5/ens84f1 | RJ45 | 1GbE | LAN |
| 6 | GE6/ens84f0 | RJ45 | 1GbE | LAN |
| 7 | XE7/eno4 | SFP+ | 10G | LAN (default traffic port) |
| 8 | XE8/eno5 | SFP+ | 10G | LAN |
| 9 | XE9/eno2 | SFP+ | 10G | LAN |
| 10 | XE10/eno3 | SFP+ | 10G | LAN |
| 11 | GE11/ens83f0 | RJ45 | 1GbE | LAN |
| 12 | GE12/ens83f1 | RJ45 | 1GbE | LAN |
| 13 | GE13/ens83f2 | RJ45 | 1GbE | LAN |
| 14 | GE14/ens83f3 | RJ45 | 1GbE | LAN |
| 15 | XE15/ens5f3 | Optional SFP+ | 10G | LAN |
| 16 | XE16/ens5f2 | Optional SFP+ | 10G | LAN |
| 17 | XE17/ens5f1 | Optional SFP+ | 10G | LAN |
| 18 | XE18/ens5f0 | Optional SFP+ | 10G | LAN |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-appliance-wall-and-rack-mount-instruction-manual","lastmod":"2026-06-29T07:06Z","nid":"1540961"} -->
## ZPA Appliance Wall and Rack Mount Instruction Manual

- Source: https://help.zscaler.com/zpa/zpa-appliance-wall-and-rack-mount-instruction-manual
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > ZPA Appliance > ZPA Appliance Wall and Rack Mount Instruction Manual
- Last modified: 2026-06-29T07:06Z
- Summary: Instructions for rack mounting a ZPA hardware appliance.

This article includes instructions on mounting your ZPA hardware appliance.

## Rack Mount

Mounting your ZPA appliance on a rack or wall optimizes your workspace and makes the appliance more accessible for installation and deployment. It also protects the appliance from any external damage and helps streamline the organization of cables and power adapters. Additionally, rack or wall mounting helps ensure proper airflow, preventing overheating and dust accumulation caused by restricted ventilation. Proper airflow also maintains a cleaner environment, prolonging the lifespan of the appliance.

## ZPA Appliance

The ZPA appliance ships in two form factors: one hosting the App Connector and another hosting the Private Service Edge for Private Access (ZPA).

- ZS-ZPA-APPL-8010-APPC - App Connector appliance
- ZS-ZPA-APPL-8010-PSE - Private Service Edge appliance

## Instruction Manual

The ZPA appliance is a 1U, 19-inch rack-mountable form factor. This article serves as a comprehensive hardware installation manual for the ZS-ZPA-APPL-8010-APPC / ZS-ZPA-APPL-8010-PSE. These instructions are structured to assist IT personnel with the physical mounting, cabling, and initial power on of the appliance. This guide solely focuses on the hardware installation process and doesn’t cover software configuration. For software configuration, see [Deploying App Connector and Private Service Edge ZPA Appliances](https://help.zscaler.com/zpa/deploying-app-connector-private-service-edge-zpa-appliances).

- ZS-ZPA-APPL-8010 Specifications
- Package Contents
- Rack Mount the Appliance

The ZPA appliance has a weight of 18.96lbs (8.6kg), and its total packaged weight is 33lbs (15kg).

The following table provides environmental and power specifications for ZS-ZPA-APPL-8010:

| ZS-ZPA-APPL-8010 Specifications |  |
| --- | --- |
| **Environmental Specifications** |  |
| Operating Temperature | 32 to 104°F (0 to 40°C) |
| Non-Operating Temperature | -4 to 150°F (-20 to 65°C) |
| Relative Humidity | 5 to 90% (operating); 5 to 95% (non-operating) |
| Altitude | 5,000 feet (1,524 m) operating; 50,000 feet (15,240 m) non-operating |
| **Power Specifications** |  |
| Power Supply | Internal AC |
| Redundant Power Supply | 1 + 1 |
| Typical Power Consumption | Idle Mode: 107.8W Full Load Mode: 225.5W |
| Maximum Power Consumption | Redundant PSU: 300W Single PSU: 350W |
| Power Cord Rating | C14 10A |
| Input Range | Redundant PSU: AC 100–240V @ 50–60 Hz Single PSU: AC 100–240V @ 47–63Hz |
| Input Current | 5A (max) |
| Output Rating | 11.4 to 12.6V |

Before beginning the installation, verify that all necessary components are present in the packaging. The package includes the following items:

- 1x ZS-ZPA-APPL-8010-XX(APPC/PSE) unit
- 2x PSUs
- 2x RJ45 cable
- 1x USB serial console cable
- 4 x SFP+ FTLX8574D3BCL
- 6x A screws for attaching the brackets to the appliance See image.
- 6x B screws for mounting the appliance to the rack See image.
- 2x ear brackets See image.

To rack mount the appliance:

1. Align one of the ear brackets to screw holes on the side of the chassis, and secure the brackets using 3x A screws. See image.
2. Secure the other ear bracket to the other side of the appliance.
3. Insert the appliance into the rack with the ear brackets fixed on the posts using the B screws. See image. The method and parts required for mounting depend on whether a shelf or rail kit is used.
4. Connect the AC power cords to the appliance power supply unit. The appliance supports redundant power supply units (PSUs). Connect both cords to their respective power sources. A continuous beep indicates that the second power source is unplugged or faulty. To silence the beep, press the round button located near or between the power supplies, or connect the second power source.
5. Connect the WAN cable to the designated port of the appliance. The appliance has several ports, including 10x 1GbE RJ45 ports and 8x 10G SFP+ ports. To learn more, see [ZPA Appliance Physical Port Mapping](https://help.zscaler.com/zpa/zpa-appliance-physical-port-mapping). You can deploy 2 or more appliances in a network location and pair them into groups for load balancing. See image.

[Image: Set of A screws for ZS-ZPA-APPL-8010]

[Image: Set of B screws for ZS-ZPA-APPL-8010]

[Image: Ear brackets for ZS-ZPA-APPL-8010]

[Image: Ear bracket mounted to ZS-ZPA-APPL-8010]

[Image: ZS-ZPA-APPL-8010 mounted to a rack]

[Image: Illustration of ports in ZS-ZPA-APPL-8010]

## Troubleshooting Installation

This section helps to identify and resolve common installation issues.

- No Power
- Loud Beep
- No Link on Port

If the ZPA appliance does not boot:

1. Verify that the power outlet is active and functioning. A power LED light should be on. If not, it indicates a lack of power to the unit.
2. Check that the power supply is securely plugged into the unit and the power source.
3. Verify that the power LED light is on.

A continuous beeping sound indicates that only one power supply is active or that the power supply is defective. To stop the beeping, press the round button located near or between the power supplies. If the power supply is faulty, contact Zscaler Support to request a Return Merchandise Authorization (RMA).

If a network cable is connected, but no link light appears, there could be an issue with the cable, the port on the ZPA appliance, or the port on the connected switch.

1. Replace the cable with a new one to see if that resolves the issue.
2. If the problem persists, try connecting to a different port on the ZPA appliance to determine if the port is faulty.
3. Power cycle the device and confirm if the LED returns to a normal state.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-private-service-edge-red-hat-enterprise-linux-9-migration","lastmod":"2026-06-29T07:06Z","nid":"1487746"} -->
## Red Hat Enterprise Linux 9 Migration for Private Service Edges

- Source: https://help.zscaler.com/zpa/zpa-private-service-edge-red-hat-enterprise-linux-9-migration
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Private Service Edge Management > Private Service Edge Deployment Guides for Supported Platforms > Red Hat Enterprise Linux 9 Migration for Private Service Edges
- Last modified: 2026-06-29T07:06Z
- Summary: Private Service Edge for Private Access (ZPA) migration steps for Red Hat Enterprise Linux 9.x.

This article provides migration instructions to replace CentOS 7 instances with Red Hat Enterprise Linux 9.x (RHEL 9.x). The enrollment and provisioning of new Private Service Edges for Private Access (ZPA) can be automated in a few steps using Terraform (IaC) or Container Orchestration to further simplify deployment.

To learn more about support for CentOS 7.x, see [End-of-Support for CentOS 7.x, RHEL 7.x, and Oracle Linux 7.x](https://help.zscaler.com/eos-eol/end-support-centos-7.x-rhel-7.x-and-oracle-linux-7.x).

Note the following requirements for successfully migrating from CentOS 7 to RHEL 9.x:

- Use a fresh install for all deployments.
- The EL9 repository must be used with RHEL 9 base OS. Older platform binaries (EL7/EL8) are not supported.
- Ensure that the`/opt/zscaler/var` folder is empty before install.
- Yum upgrades from EL7/EL8 to RHEL9 are not supported.
- Requires ESXi version 7.0 Update 2 or newer, including ESXi 8.x. To learn more about ESXi support, see [End-of-Support for VMware vSphere Hypervisor (ESXi) Version 5.5](https://help.zscaler.com/eos-eol/end-support-vmware-vsphere-hypervisor-esxi-version-5.5).

Use the following steps to migrate from CentOS 7 to RHEL 9.x:

1. Use a current [provisioning key](https://help.zscaler.com/zpa/about-zpa-service-edge-provisioning-keys) or create a new [Private Service Edge group](https://help.zscaler.com/zpa/about-zpa-private-service-edge-groups) with a provisioning key for each location.
2. Verify that the [version profile](https://help.zscaler.com/zpa/configuring-version-profile) is set to **Default**, **Previous Default**, or **New Release**. See image.

1. Follow the [step-by-step guide](https://help.zscaler.com/zpa/service-edge-deployment-guide-vmware) to deploy new VMs using the RHEL 9.x images and newly created provisioning keys. Ensure the yum repository is pointing to the new RHEL 9 link: `https://yum.zpagov.net/yum/el9`

Only RHEL 9.x repositories and RPMs are supported on RHEL 9.x.

1. Add trusted networks and enable **Publicly Accessible** (if applicable) on the new Private Service Edge groups. [Image: Edit Private Service Edges group]
2. (Optional) [Disable the Private Service Edge groups](https://help.zscaler.com/zpa/editing-service-edge-groups) 15 minutes prior to the regional off-hours maintenance window to allow connections to gradually drain down.
3. During regional off hours, remove trusted networks and disable public access (if applicable) on CentOS 7 Private Service Edge groups. [Image: Edit Private Service Edges group]

[Image: Set version profile]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-private-service-edge-release-summary-2020","lastmod":"2020-10-23T14:03Z","nid":"1484716"} -->
## ZPA Private Service Edge Release Summary (2020)

- Source: https://help.zscaler.com/zpa/zpa-private-service-edge-release-summary-2020
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Release Notes > ZPA Private Service Edge Release Notes > ZPA Private Service Edge Release Summary (2020)
- Last modified: 2020-10-23T14:03Z
- Summary: Zscaler Private Access (ZPA) Private Service Edge release summary for updates deployed, per version, in 2020.

This article provides a summary of all new features and enhancements released per Zscaler Private Access (ZPA) Private Service Edge version.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-private-service-edge-release-summary-2021","lastmod":"2022-03-24T16:30Z","nid":"1484741"} -->
## ZPA Private Service Edge Release Summary (2021)

- Source: https://help.zscaler.com/zpa/zpa-private-service-edge-release-summary-2021
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Release Notes > ZPA Private Service Edge Release Notes > ZPA Private Service Edge Release Summary (2021)
- Last modified: 2022-03-24T16:30Z
- Summary: Zscaler Private Access (ZPA) Private Service Edge release summary for updates deployed, per version, in 2021.

This article provides a summary of all new features and enhancements released per Zscaler Private Access (ZPA) Private Service Edge version.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-private-service-edge-release-summary-2022","lastmod":"2022-02-27T06:00Z","nid":"1485091"} -->
## ZPA Private Service Edge Release Summary (2022)

- Source: https://help.zscaler.com/zpa/zpa-private-service-edge-release-summary-2022
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Release Notes > ZPA Private Service Edge Release Notes > ZPA Private Service Edge Release Summary (2022)
- Last modified: 2022-02-27T06:00Z
- Summary: Zscaler Private Access (ZPA) Private Service Edge release summary for updates deployed, per version, in 2022.

This article provides a summary of all new features and enhancements released per Zscaler Private Access (ZPA) Private Service Edge version.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-private-service-edge-release-summary-2023","lastmod":"2022-12-19T18:47Z","nid":"1485501"} -->
## ZPA Private Service Edge Release Summary (2023)

- Source: https://help.zscaler.com/zpa/zpa-private-service-edge-release-summary-2023
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Release Notes > ZPA Private Service Edge Release Notes > ZPA Private Service Edge Release Summary (2023)
- Last modified: 2022-12-19T18:47Z
- Summary: Zscaler Private Access (ZPA) Private Service Edge release summary for updates deployed, per version, in 2023.

This article provides a summary of all new features and enhancements released per Zscaler Private Access (ZPA) Private Service Edge version.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-private-service-edge-release-summary-2024","lastmod":"2024-03-03T06:06Z","nid":"1485906"} -->
## ZPA Private Service Edge Release Summary (2024)

- Source: https://help.zscaler.com/zpa/zpa-private-service-edge-release-summary-2024
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Release Notes > ZPA Private Service Edge Release Notes > ZPA Private Service Edge Release Summary (2024)
- Last modified: 2024-03-03T06:06Z
- Summary: Zscaler Private Access (ZPA) Private Service Edge release summary for updates deployed, per version, in 2024.

This article provides a summary of all new features and enhancements released per Zscaler Private Access (ZPA) Private Service Edge version.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-private-service-edge-release-summary-2025","lastmod":"2026-07-22T14:18Z","nid":"1516231"} -->
## ZPA Private Service Edge Release Summary (2025)

- Source: https://help.zscaler.com/zpa/zpa-private-service-edge-release-summary-2025
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Release Notes > ZPA Private Service Edge Release Notes > ZPA Private Service Edge Release Summary (2025)
- Last modified: 2026-07-22T14:18Z
- Summary: Zscaler Private Access (ZPA) Private Service Edge release summary for updates deployed, per version, in 2025.

This article provides a summary of all new features and enhancements released per Zscaler Private Access (ZPA) Private Service Edge version.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-private-service-edge-release-summary-2026","lastmod":"2026-08-06T08:30Z","nid":"1534307"} -->
## ZPA Private Service Edge Release Summary (2026)

- Source: https://help.zscaler.com/zpa/zpa-private-service-edge-release-summary-2026
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Release Notes > ZPA Private Service Edge Release Notes > ZPA Private Service Edge Release Summary (2026)
- Last modified: 2026-08-06T08:30Z
- Summary: Zscaler Private Access (ZPA) Private Service Edge release summary for updates deployed, per version, in 2026.

This article provides a summary of all new features and enhancements released per Zscaler Private Access (ZPA) Private Service Edge version.
<!-- /ZS-ARTICLE -->
