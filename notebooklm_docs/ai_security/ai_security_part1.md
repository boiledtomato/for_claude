# Zscaler Help — AI Security (part 1)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-08-31 03:58 UTC
Articles in this file: 77

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/about-ai-guard-dashboard","lastmod":"2026-08-03T13:50Z","nid":"1541822"} -->
## About AI Guard Dashboard

- Source: https://help.zscaler.com/secure-ai-apps-infra/about-ai-guard-dashboard
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Monitoring > About AI Guard Dashboard
- Last modified: 2026-08-03T13:50Z
- Summary: Learn how to view information on the AI Guard dashboard.

The AI Guard dashboard provides a high-level overview of all AI applications AI Guard manages. This includes information such as the number of apps, number of policy detections, and number of AI bot transactions.

The AI Guard **Dashboard** page provides the following benefits and enables you to:

- View general statistics of your apps, LLMs, and policy detections.
- View information on individual policy transactions.
- Search by specific policy names or filters.

## About the AI Guard Dashboard Page

On the AI Guard **Dashboard** page (AI Guard > Dashboard), you can do the following:

1. Select the **AI Applications** tab.
2. Select the date range (up to 90 days) for your data.
3. Search by a specific policy name.
4. Filter your data based on criteria such as **Policy Name**, **Severity**, and **Prompt Detections**.
5. Refresh will update your dashboard with the most current data.
6. Export a PDF copy of your dashboard for the selected date range.
7. View the following information:
  1. **Number of Apps**: The total number of applications.
  2. **Number of LLMs**: The total number of LLMs.
  3. **Number of Detections**: The total number of policy detections.
  4. **Number of Transactions**: The total number of transactions.
8. View dashboard entries as individual prompt transactions, or view multi-prompt AI interactions as a single, connected conversation thread. This functionality is exclusive to DAS/API mode and will not appear if using Proxy mode.
  - With **Conversations** selected, you will see a message icon next to a transaction's date, indicating the number of transactions in that conversation thread. Expand the entry to view details of the individual transactions. See image.
9. View a list of all transactions. For each transaction, you can see the following:
  - **Date and Time**: The date and time of the transaction.
  - **App**: The name of the app.
  - **Policy Name**: The name of the policy associated with this transaction.
  - **Severity**: The level of severity of the transaction. The severity can be **Info**, **Low**, **Medium**, **High**, and **Critical**.
  - **Prompt Detections**: The specific prompt detectors that the policy triggered.
  - **Response Detections**: The specific response detectors that the policy triggered.
  - **LLM**: The LLM used for this transaction.
  - **Prompt Action**: Displays whether the AI Guard policy transaction resulted in the prompt being **Allowed** or **Blocked.**
  - **Response Action**: Displays whether the AI Guard policy transaction resulted in the response being **Allowed** or **Blocked.**
  - **Details**: Clicking the **Details** icon opens a window showing detailed information in the following sections: **Overview**, **Detection Summary**, **Performance & Network Stats**, **Custom Request Headers**, and **Prompt Details**. See image.

[Image: AI Guard Dashboard Details window showing Overview, Detection Summary, Performance & Network Stats, Custom Request Headers, and Prompt Details.]

[Image: AI Guard Dashboard page with annotations highlighting each section]

[Image: AI Guard Dashboard, in DAS/API Mode, with  Conversation mode selected showing an open conversation transaction]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/about-ai-guard-detection-summary","lastmod":"2026-07-24T11:11Z","nid":"1542028"} -->
## About AI Guard Detection Summary

- Source: https://help.zscaler.com/secure-ai-apps-infra/about-ai-guard-detection-summary
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Monitoring > About AI Guard Detection Summary
- Last modified: 2026-07-24T11:11Z
- Summary: Learn how to access and view information on the AI Guard detection summary.

The AI Guard dashboard provides a high-level overview of all the AI applications AI Guard manages, but if you want to view additional details on specific transactions, you need to view the detection summary found in the transaction details window.

To access a transaction detection summary, go to AI Security Admin Portal > AI Guard > Dashboard. In the dashboard, find the transaction you want to view, go to the **Details** column, and click **View Details**. The **Transaction Details** window opens.

On the Transaction Details window, you can view the following information:

1. View or copy the Transaction ID.
2. The Overview section contains the following information:
  1. **Date**: Date and time of the transaction.
  2. **App**: The app that the prompt was submitted through.
  3. **Severity**: Strength of the prompt's triggering of the policy detector: Info > Low > Medium > High > Critical.
  4. **Policy**: Name of the policy that was triggered.
  5. **LLM**: Name of the LLM used for analyzing the prompt or sending the response.
  6. **LLM Model**: Specific LLM model used for this transaction (such as: claude-sonnet-4-6).
  7. **User Agent**: Information about the user's agent where the prompt was written or the response was sent to.
3. The Detection Summary section contains the following information:
  1. **Prompt Detections**: Shows which detectors were triggered by the prompt.
    1. **Details**: Clicking for more details will show additional information about the detectors that were triggered.
  2. **Prompt Action**: Shows whether the prompt was blocked, allowed, or just detected.
  3. **Prompt Tags**: Shows an tags associated with the prompt.
  4. **Response Detections**: Shows which detectors were triggered by the response.
    1. **Details**: Clicking for more details will show additional information about the detectors that were triggered.
  5. **Response Action**:Shows whether the response was blocked, allowed, or just detected.
  6. **Response Tags**: Shows an tags associated with the response.
4. The Performance & Network Stats section contains the following information:
  1. **Prompt Size**: File size of the prompt.
  2. **Response Size**: File size of the response.
  3. **Total Time Taken by LLM**: Length of time the LLM took to process the prompt and send a response
  4. **LLM Provider Status Code**: An HTTP status code that corresponds to the status of the request. For example, 200 means Acceptable Response.
  5. **Detection Time On Request**: Time it took to analyze the prompt.
  6. **Detection Time On Response**: Time it took to analyze the response.
  7. **Region**: Region of the app's server.
  8. **Protocol**: Protocol used by the transaction.
5. **Custom Request Headers**: This section will show any custom headers that were set up in the AI Guard tenant settings page.
6. **Prompt and Response Details**: In this section you can read the specific prompt sent by the user and the response sent by the GenAI app.
  1. **Add to Allowlist**: If you have the proper permissions, when you reveal a prompt or response that was blocked, you can click this option to add the prompt or response to your allowlist.
7. **Was this detection helpful?**: Leave feedback for Zscaler Support.

[Image: AI Guard Detection Summary window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/about-ai-guard-insights","lastmod":"2026-07-24T11:13Z","nid":"1541823"} -->
## About AI Guard Insights

- Source: https://help.zscaler.com/secure-ai-apps-infra/about-ai-guard-insights
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Monitoring > About AI Guard Insights
- Last modified: 2026-07-24T11:13Z
- Summary: Learn how to use and view the AI Guard Insights page.

The AI Guard **Insights** page provides an executive overview of all the AI applications that AI Guard manages. This includes information such as the number of apps, number of policy detections, number of AI bot transactions, and category of prompts.

The AI Guard **Insights** page provides the following benefits and enables you to:

- View general app statistics for AI Guard, such as the number of blocked prompts and responses.
- View data graphs of AI Guard statistics.
- View an infographic of the most common prompt categories.

## About the AI Guard Insights Page

On the AI Guard **Insights** page (AI Guard > Insights), you can do the following:

1. Select the **AI Applications** tab.
2. Select the date range to view the corresponding data. Click **Reset** to set the date range back to default.
3. Refresh your Insights data.
4. Export your Insights page to a PDF file.
5. View data about AI Guard activities in the following categories:
  - **Total Prompts**: Total number of prompts submitted.
  - **Blocked Prompts**: Total number of blocked prompts.
  - **Prompt Tokens**: Approximate number of input tokens consumed by AI applications.
  - **Average Prompt Tokens**: Average number of tokens per prompt.
  - **Average Prompt Detection Latency**: Average time for prompt detection in milliseconds.
  - **Total Responses**: Total number of LLM responses.
  - **Blocked Responses**: Total number of LLM responses that AI Guard blocked.
  - **Response Tokens**: Approximate number of output tokens consumed by AI applications.
  - **Average Response Token**: Average number of tokens per response.
  - **Average Response Detection**: Average time for response detection in milliseconds.
  - **Active Apps**: Total number of active AI applications.
  - **Active LLMs**: Total number of LLMs.
6. View data presented in the following graphs:
  - **Trend Over Time**: Daily transaction volume showing **Blocked** and **Total** counts. Use this to spot spikes in blocked traffic or unusual activity patterns.
  - **Security Posture**: Overview of transaction disposition which shows the proportion of **Allowed**, **Blocked**, and **Passthrough** transactions across all AI traffic.
  - **Transactions by LLM**: Displays the active LLMs and breaks down by color how many of them are **Low Risk**, **High Risk**, or **Critical Risk**.
  - **Top Detectors**: Displays the detectors that have been triggered the most.
  - **Transactions by Application**: Displays the total number and percentage of transactions. Hover your mouse over the circle chart to see the number of transactions per application.
  - **Top Applications**: Top AI applications ranked by transaction volume. Identifies the most active applications in your environment.
  - **PII Detections by Application**: Total number of personally identifiable information (PII) detections per application. Clicking on an application bar will open a window with all transactions that contributed to that bar in the chart.
  - **PII Categories**: Types of PII detected in AI traffic such as credit cards, emails, SSNs, and phone numbers. Clicking on a category bar will open a window with all transactions that contributed to that bar in the chart.
7. View the most common prompt categories during the selected date range and the number of transactions for each category. [Image: AI Guard Insights page with numbered annotations around each UI element and section]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/about-ai-guard-usage","lastmod":"2026-07-24T11:05Z","nid":"1541824"} -->
## About AI Guard Token Usage

- Source: https://help.zscaler.com/secure-ai-apps-infra/about-ai-guard-usage
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Monitoring > About AI Guard Token Usage
- Last modified: 2026-07-24T11:05Z
- Summary: Learn about the AI Guard Usage dashboard.

The AI Guard **Token Usage** dashboard provides an overview of the data usage by AI applications.

The AI Guard **Token Usage** page provides the following benefits and enables you to:

- View general statistics about AI Guard token usage by AI applications.
- View usage based on specific time intervals.

## About the AI Guard Token Usage Page

On the AI Guard **Token Usage** page (AI Guard > Token Usage), you can do the following:

1. Select the **AI Applications** tab.
2. Select the date range for your data.
3. Refresh updates your dashboard with the most current data.
4. Select to view token usage data across the entire tenant, or a single application.
5. The Overview section shows the following information:
  1. **Total Tokens**: All prompt and response tokens used by every user in your tenant during this period.
  2. **Top App**: The app that used the most tokens.
  3. **Busiest Day**: The day with the most token usage.
  4. **Avg. per App**: Average tokens used by each app on the leaderboard. A high "Top App" share with a low average means usage is concentrated in a few apps.
6. The Insights section presents the following data: [Image: AI Guard Usage page with highlights around the Users and AI Applications tabs, date range, refresh, Input/Output info, and Input/Output graph]
  1. **Prompts vs. Responses**: Total number of prompts and transactions as well as their ratio.
  2. **Activity by day & hour**: Requires Hourly or 5-min granularity to see hour-of-day patterns.
  3. **Token Consumption**: A graph of token consumption over the selected time period.
  4. **LLM Provider Distribution**: See LLM distribution information based on the level of granularity you have selected (Daily, Hourly, 5-min).
  5. **Top usage by app**: The 10 applications that used the most tokens. The label at the end of each bar shows when usage peaked.
  6. **Raw Data**: A quick total summary of prompts and responses by date. Data can be exported.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/about-ai-red-teaming-overview","lastmod":"2026-06-22T21:06Z","nid":"1540179"} -->
## About AI Red Teaming Overview

- Source: https://help.zscaler.com/secure-ai-apps-infra/about-ai-red-teaming-overview
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > About AI Red Teaming Overview
- Last modified: 2026-06-22T21:06Z
- Summary: Information about the AI Red Teaming Overview page in the AI Security Admin Portal.

The AI Red Teaming Overview page gives customers a single, central place to configure, run, and review adversarial testing across their AI assets. It helps security and AI teams quickly move from discovering weaknesses to remediation by making test coverage, results, and asset status easy to find and act on.

The AI Red Teaming Overview page provides the following benefits and enables you to:

- Give customers one place to configure, run, and review adversarial testing.
- Help teams move from discovery of weaknesses to remediation.

## About the AI Red Teaming Overview Page

On the AI Red Teaming Overview Page (AI Infrastructure > AI Red Teaming > Overview), you can do the following:

1. Filter information by specific **Business Units**.
2. Click [**+ Connect Asset**](https://help.zscaler.com/secure-ai-apps-infra/connecting-asset) to onboard an AI app.
3. Click [**+ New Test Result**](https://help.zscaler.com/secure-ai-apps-infra/creating-new-test-run)to add a new test run.
4. **Usage Insights**: Displays information about the total number of red teaming test runs executed in the last seven days. It also displays the ratio of the AI assets that are red teamed to the total number of AI assets that are onboarded.
5. **Tested AI Assets by Health Score**: Displays the distribution of the AI assets by their health score, (i.e, **Critical**, **Low**, **Medium**, or **High**).
6. **AI Assets by Availability**: Displays the distribution of your AI assets, grouped by whether they are **Customer-Facing** or **Internal**.
7. View detailed information in the following tabs:
  - Tested AI Assets
  - Untested AI Assets

View the list of tested assets. For each asset, you can:

- Search for a specific AI asset in the overview table.
- Show or hide columns.
- Apply [filters](https://help.zscaler.com/ai-asset-mgmt/using-filters) to view specific information.
- View the list of AI Apps. For each AI app, you can see:
  - **Asset Name & Business Unit**: Name and business unit of the tested AI Asset. Click the name of the AI asset to view details of the same.
  - **Asset Type**: Type of the AI asset.
  - **Latest Test Run**: Timestamp of when the last test run was executed.
  - **Health Score**: Score that indicates the asset’s current security posture based on test results (higher usually indicates better posture).
  - **Environment**: Where the asset is deployed (for example, production, staging, development, or a specific environment label).
  - **Availability**: The current availability state of the asset (i.e., internal or customer-facing).
  - **Supported Modes**: Indicates which input modalities the AI asset can process for system prompt attacks.
  - **Hardened Prompt**: Indicates if the AI asset is hardened or not.

View the list of untested assets. For each asset, you can:

- Search for a specific AI asset in the overview table.
- Show or hide columns.
- Apply [filters](https://help.zscaler.com/ai-asset-mgmt/using-filters) to view specific information.
- View the list of AI Apps. For each AI app, you can see:
  - **Asset Name & Business Unit**: Name and business unit of the tested AI Asset. Click the name of the AI asset to view details of the same.
  - **Asset Type**: Type of the AI asset.
  - **Environment**: Where the asset is deployed (for example, production, staging, development, or a specific environment label).
  - **Availability**: The current availability state of the asset (i.e., internal or customer facing).
  - **Supported Modes**: Indicates which input modalities the AI asset can process for system prompt attacks.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/about-red-teaming-brokers","lastmod":"2026-07-27T05:53Z","nid":"1541764"} -->
## About AI Red Teaming Brokers

- Source: https://help.zscaler.com/secure-ai-apps-infra/about-red-teaming-brokers
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Brokers > About AI Red Teaming Brokers
- Last modified: 2026-07-27T05:53Z
- Summary: Information about the Brokers page in the AI Security Admin Portal.

The AI Red Teaming Brokers enable Zscaler's AI Red Teaming service to securely reach private AI applications hosted inside a customer's network without requiring any changes to the customer's firewall or network configuration.

The AI Red Teaming Brokers page provides the following benefits and enables you to:

- View all registered broker instances in one place with key details like status, version, connected AI apps, and last seen timestamp at a glance.
- Instantly confirm whether a broker is online or offline without inspecting container logs or running diagnostic commands.
- Attach brokers to AI targets directly from the AI Security Admin Portal. After it is attached, all test traffic is automatically routed through the broker without the need for manual URL configuration.
- Register brokers and download pre-filled run manifests for Docker, Docker Compose, or Kubernetes without any Zscaler assistance.
- Configure an optional source-IP allow-list using CIDR notation to control which IP addresses can connect through the broker.
- Track all broker-related events, including who made changes and when, ensuring accountability across multi-user environments.

## About the AI Red Brokers Page

On the Brokers Page (Administration > AI Red Teaming > Brokers), you can do the following:

1. Filter information by specific **Business Units**.
2. Click [**+Register Broker**](https://help.zscaler.com/secure-ai-apps-infra/registering-broker) to register an AI app.
3. Search by broker name.
4. Enable the **Show Revoked**toggle to view the revoked brokers.
5. Show or hide column names.
6. View a list of brokers. For each broker, you can view:
  - **Broker Name:** The unique name assigned to the broker instance at the time of registration. Click the broker name to view additional details.
  - **Description:** A brief summary of the broker's purpose or the environment it is deployed in.
  - **Business Unit:** The organizational unit or team that owns and manages this broker instance.
  - **Connected AI Apps:** The number of private AI applications currently linked to this broker.
  - **Status:** Indicates whether the broker is currently active and reachable. Possible values include Online (the broker has an active WebSocket Secure (WSS) connection to the gateway) and Offline (the connection has been lost or the broker has stopped).
  - **Last Seen:** The date and time when the broker last successfully communicated with the Broker Gateway.
  - **Version:** The version of the broker agent currently running.
  - **Source IP:** The public IP address from which the broker's outbound connection originates.
7. **Actions:** You can perform the following actions on the selected broker:
  - **Reinstate Broker:** Restore a previously revoked broker and re-enable its connection to the platform.
  - **Revoke Broker:** Revoke an active broker to temporarily disable its connection to the platform.
  - **Delete Broker:** Permanently remove a broker and all its associated configuration from the platform.

[Image: The AI Red Teaming Brokers Page in AI security provides a list of all the brokers.]

The **Details** page shows the following widgets and tabs:

- 1. General information
- [2. Configuration](https://help.zscaler.com/secure-ai-apps-infra/configuring-broker-target)
- 3. Actions
- 4. Connection Summary
- 5. Audit Log
- 6. Assigned AI Apps
- 7. Commands/Instruction

[Image: Brokers Details page provides information about the broker ID, version, source IP, etc.]

This section displays the core identity and configuration details of a broker instance. It also includes information such as Broker ID, version, source IP, created by, created on, etc.

This tab displays time-stamped record of all significant activities and changes made to broker instances. In the Audit Log section, you can do the following:

- Filter data by time range, event type, and actor.
- The Audit log table lists the following information:
  - **Last Seen timestamp:** Displays the date and time of the most recent activity associated with the broker, helping administrators identify instances that may have gone silent or fallen out of sync.
  - **Event:** Displays the specific action that triggered the log entry such as a broker being registered, updated, or reconnected, making it easy to trace changes in the broker's state or configuration.
  - **Actor:** Identifies the source of the event, either a broker (an automated action triggered by the broker agent itself) or a user (a platform administrator who made a manual change).
  - **Description:** Displays additional context about the event, capturing key details such as configuration changes or error conditions.

This widget shows whether the broker is Online or Offline, confirming that the outbound WSS tunnel to the Zscaler platform is active and that the broker is ready to route test traffic to your private AI application.

You can perform the following actions on the selected broker:

- **Reinstate Broker:** Restore a previously revoked broker and re-enable its connection to the platform.
- **Revoke Broker:** Revoke an active broker to temporarily disable its connection to the platform.
- **Delete Broker:** Permanently remove a broker and all its associated configuration from the platform.

This tab displays the following information:

- **AI App Name:** The name of the private AI application that the broker is configured to route test traffic to.
- **Connection Type:** The protocol or integration method used to connect to the AI application. Supported connection types include REST API, OpenAI-Compatible API, Proxy SDK, and Agentforce.

This tab provides a ready-to-use run manifest for Docker, Docker Compose, or Kubernetes, with all required variables pre-filled. You only need to supply your Authentication Service OAuth credentials to complete the configuration.

The following environment variables are required:

| Variable | Source | Purpose |
| --- | --- | --- |
| `GATEWAY_URL` | AI Security Admin Portal | WSS gateway endpoint |
| `BROKER_ID` | AI Security Admin Portal | Broker identifier issued at registration |
| `OAUTH_TOKEN_URL` | AI Security Admin Portal | Authentication Service OAuth token endpoint |
| `OAUTH_CLIENT_ID` | Customer | Authentication Service client id |
| `OAUTH_CLIENT_SECRET` | Customer | Authentication Service client secret |

- Docker: Execute the following command within the Docker container: `docker run``-d --name``airt-broker``--restart``unless-stopped \` `-e``GATEWAY_URL=``"wss://airt-gw.us2.zscaler.net:443/ws/broker"``\` `-e``BROKER_ID=``"6b9247f9-7c00-4622-b4e4-ce052b724ae6"``\` `-e``OAUTH_TOKEN_URL="``https://<zidentity-host>/oauth2/v1/token``" \` `-e``OAUTH_CLIENT_ID=``"<client-id>"``\` `-e``OAUTH_CLIENT_SECRET=``"<client-secret>"``\` `-e``SSL_VERIFY=``"true"``-e``LOG_LEVEL=``"INFO"``-e``HEALTHZ_PORT=``"8080"``\` `nexus.corp.zscaler.com:9016/airt/airt-broker:<tag>`
- Docker Compose:
- Kubernetes:
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/about-test-runs","lastmod":"2026-06-18T12:01Z","nid":"1540210"} -->
## About Test Runs

- Source: https://help.zscaler.com/secure-ai-apps-infra/about-test-runs
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Test Run > About Test Runs
- Last modified: 2026-06-18T12:01Z
- Summary: Information about the Test Run views in the AI Security Admin Portal

The Test Run View page displays details for a triggered test run and scheduled test runs.

## About Test Runs Page

On the Test Runs page (AI Infrastructure > AI Red Teaming > Test Runs), you can do the following:

- 1. Filter data by **Business Units**.
- 2. View Test Run History.
- 3. View Scheduled Test Runs.

The **Test Run History** tab provides a comprehensive list of all test runs associated with a single AI app, including those currently in progress. By default, test runs are sorted chronologically, with the most recent run appearing at the top.

- **Search**: Search by test run name.
- **Status**: Filter by test run status (i.e., **Running**, **Error**, **Finished**, or **Canceled**).
- View the list of test runs. For each test run, you can see:
  - **Asset Name & Business Unit**: The name of the asset and business unit. Click the asset name to view asset details. To learn more, see View AI App Details.
  - **Name**: Name of the test run. To view Test Run details, click the name of the test run. To learn more, see [Viewing Test Runs](https://help.zscaler.com/secure-ai-apps-infra/viewing-test-run-results).
  - **Launched By**: Name of the user who launched the test run.
  - **Timestamp**: Test run's execution date and time.
  - **Status**: Current status of the test run.
  - **Probes**: Probes included in the test run.
  - **Result**: Shows the total number of passed, failed and error test cases across all probes

The **Test Run Scheduled** tab provides a comprehensive list of all scheduled test runs.

- **Search**: Search by scheduled test run name.
- **Status**: Filter by test run status (i.e., **Running**, **Error**, **Finished**, or **Canceled**).
- View the list of scheduled test runs. For each scheduled test run, you can see:
  - **Asset Name & Business Unit**: The name of the asset and business unit. Click the asset name to view asset details. To learn more, see View AI App Details.
  - **Name**: Name of the test run.
  - **Last Run**: Name of the user who launched the test run.
  - **Next Run**: Test run's execution date and time.
  - **Status**: Current status of the test run.
  - **Probes**: Probes included in the test run.
  - **Actions**: Select **Edit** to edit a test run or **Delete** to delete the test run entirely.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/about-user-groups-llm-provider-topology","lastmod":"2026-07-24T11:54Z","nid":"1541717"} -->
## About AI Apps & LLM Provider Topology

- Source: https://help.zscaler.com/secure-ai-apps-infra/about-user-groups-llm-provider-topology
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Monitoring > About AI Apps & LLM Provider Topology
- Last modified: 2026-07-24T11:54Z
- Summary: Learn how to use and view the AI Guard AI apps, user groups & LLM provider Topology page.

The AI Guard for Apps Topology page provides a system overview of the data flow between all AI apps and LLM providers managed by AI Guard in Proxy mode.

The AI Guard **Topology** page provides the following benefits and enables you to:

- View data flow between all AI apps and LLM providers managed by AI Guard.
- Hover over user groups or LLM providers to view additional information.
- Click on individual user group and LLM providers to highlight their direct data flow connections.

## About the AI Guard User Groups & LLM Provider Topology Page

On the AI Guard**User Groups & LLM Provider Topology** Page (AI Security Admin Portal > AI Guard > Topology > Users tab), you can do the following:

1. Select the**AI Applications** tab.
2. Search by a specific AI app or LLM provider node name.
3. Select whether you want to search by AI App, LLM Providers, or All Types.
4. Make the following view adjustments:
  - Zoom In.
  - Zoom Out.
  - Fit to View.
  - Reset Zoom.
5. Reset the topology view back to default.
6. View the overall topology of AI apps and LLM providers. Hover your cursor over an AI App or LLM provider to view additional information about the node. Click on a node to highlight the direct connections to and from it.
  - AI Apps: Formatted as blue circles.
  - LLM Providers: Formatted as purple hexagons.
  - Data Flow: Represented as lines connecting the user groups and LLM providers.

[Image: AI Guard User Groups & LLM Topology page with highlights around the interactable elements of the page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/adding-and-managing-ai-applications-ai-guard","lastmod":"2026-07-27T11:06Z","nid":"1541885"} -->
## Adding and Managing AI Applications for AI Guard in Proxy Mode

- Source: https://help.zscaler.com/secure-ai-apps-infra/adding-and-managing-ai-applications-ai-guard
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Configuration > Proxy Mode > Adding and Managing AI Applications for AI Guard in Proxy Mode
- Last modified: 2026-07-27T11:06Z
- Summary: Learn to add and manage AI applications for AI Guard in Proxy Mode.

Adding an AI application (such as a chatbot) needs to be done while configuring AI Guard. Once added, an AI Guard policy can be configured and applied to it.

This article will focus on managing AI Applications in Proxy mode. With the Proxy mode, AI Guard is placed between the AI application and the Large Language Model (LLM) provider.

## Adding an AI Application

When adding an AI application to AI Guard in Proxy mode, you will need to have already added an LLM provider and credentials. To learn more, see [Managing LLM Providers for AI Guard](https://help.zscaler.com/secure-ai-apps-infra/managing-llm-provider-credentials-ai-guard).

To add an AI Application:

1. In the AI Security Admin Portal, go to **AI Guard** > **AI Applications**. The **AI Applications** page appears. See image.
2. Click **Add More** to open the **Create Application** window. See image.
3. Enter the following information:
  - **Name**: Enter a name for the application.
  - **Owner Email**: Enter the owner email address for the application.
  - **Store Events Content**: Select whether you want to store events content.
  - **Encrypt Events Content**: Select whether you want the events content to be encrypted.
4. Click **Create** to return to the **AI Application** page.
5. Go to the **Application Identity Brokers** tab. See image.
6. Click **Add More** to open the **Add Application Identity Broker** window. See image.
7. Enter the following information:
  - **Name**: Enter a name for the app credentials you are entering.
  - **Application**: Select the AI application you previously created from the drop-down menu.
  - **Expires At**: (Optional) Enter an expiration date for the credentials.
  - **LLM Provider**: Select your desired LLM provider you have added to AI Guard.
  - **LLM Provider Credentials**: Select the corresponding credentials for the LLM provider you previously selected.
8. Click **Create** and the **Save your key** window appears. See image.
9. Save your API key. For security reasons, you will not be able to view it again after closing the window. After copying and saving it, click **Done**.

## Managing an AI Application

To make changes to an AI application:

1. In the left-side navigation, under **Private AI Apps**,click **Applications**. The **AI Applications** page appears. See image.
2. If you want to edit your AI application, in the **Action** column, click the **Edit** button to open the **Edit Application** window.
3. Click **Update** when you finish making changes. See image.

## Adding and Managing Application Groups

To group your AI applications together:

1. In the left-side navigation, under **Private AI Apps**, click **Application Groups**. The **Application Groups** page appears. See image.
2. To add a group, click **Add Group**. The **Add AI Application Group** window opens. See image.
3. Enter the following information:
  - **Group Name**: Enter a name for the group.
  - **Description**: (Optional) Enter a description for the group.
  - **Applications**: From the drop-down menu, select the applications you want to group together.
4. Click **Submit**. You return to the **Application Groups** page.

To delete a group:

1. From the **Application Groups** page, in the **Action** column, click the **Delete** icon. The **Delete AI Application** window opens.
2. Click **OK** to delete the group.

## Managing AI Application Identity Brokers

To make changes to a Proxy mode AI application's identity broker:

1. If you want to delete an AI application, click the **Delete** button and then click **OK** on the window that appears. See image.
2. If you want to edit your AI application, click the **Edit** button to open **Edit App Credentials** window. See image.
3. Click **Update** when you finish making changes.

[Image: AI Guard AI Applications page showing App Name, Last Updated, Owner Email, Store Contents, Encrypt Contents, and Action]

[Image: Edit AI Application window]

[Image: Delete AI Application Credential window]

[Image: Add App Credentials window]

[Image: AI Guard AI Applications page showing App Name, Last Updated, Owner Email, Store Contents, Encrypt Contents, and Action]

[Image: Create Application window]

[Image: AI Application Identity Broker page]

[Image: Add Application Identity Broker window]

[Image: Save your key window, you need to copy the key before closing the window]

[Image: AI Guard Application Groups page showing Name & Description Number of Applications, Last Updated, and Action columns]

[Image: AI Guard Add AI Application Group window showing Group Name, Description, and Applications fields]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/adding-and-managing-ai-applications-ai-guard-dasapi-mode","lastmod":"2026-07-27T11:13Z","nid":"1542056"} -->
## Adding and Managing AI Applications for AI Guard in DAS/API Mode

- Source: https://help.zscaler.com/secure-ai-apps-infra/adding-and-managing-ai-applications-ai-guard-dasapi-mode
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Configuration > DAS/API Mode > Adding and Managing AI Applications for AI Guard in DAS/API Mode
- Last modified: 2026-07-27T11:13Z
- Summary: Learn to add and manage AI applications for AI Guard in DAS/API Mode.

Adding an AI application (such as a chatbot) needs to be done while configuring AI Guard. Once added, an AI Guard policy can be configured and applied to it.

This article will focus on managing AI Applications in DAS/API mode. With the DAS mode, AI Guard acts as an API endpoint the enterprise AI application or chatbot can access. The AI application must make an API call to AI Guard for every prompt that you make and for every response you receive in the AI application.

## Adding an AI Application

To add an AI application and credentials:

1. In the AI Security Admin Portal, go to **AI Guard** > **AI Applications**. The **AI Applications** page appears. See image.
2. Click **Add More** to open the**Create Application** window. See image.
3. Enter the following information:
  - **Name**: Enter a name for the application.
  - **Owner Email**: Enter the owner email address for the application.
  - **Store Events Content**: Select whether you want to store events content on the Zscaler AI Guard platform for viewing later in the dashboard.
  - **Encrypt Events Content**: Select whether you want the events content to be encrypted.
4. Click **Create** to return to the **AI Application** page.
5. In the left-side navigation, under **Private AI Apps**, click **App API Keys**. The **App API Keys** page opens. See image.
6. Click **Add More** and enter the following information:
  - **Name**: Enter a name for the identity broker.
  - **Application**: Select the application from the drop-down menu.
  - **Expires At**: (Optional) Select a date that you want the identity broker to expire.
7. Click **Create** and the **Save your key** window appears. See image.
8. Save your API key. For security reasons, you will not be able to view it again after closing the window. After copying and saving it, click **Done**.

## Managing an AI Application

To make changes to an AI application:

1. In the left-side navigation, under **Private AI Apps**,click **Applications**. The **AI Applications** page appears. See image.
2. If you want to edit your AI application, in the **Action** column, click the **Edit** button to open the **Edit Application** window.
3. Click **Update** when you finish making changes. See image.

## Adding and Managing Application Groups

To group your AI applications together:

1. In the left-side navigation, under **Private AI Apps**, click **Application Groups**. The **Application Groups** page appears. See image.
2. To add a group, click **Add Group**. The **Add AI Application Group** window opens. See image.
3. Enter the following information:
  - **Group Name**: Enter a name for the group.
  - **Description**: (Optional) Enter a description for the group.
  - **Applications**: From the drop-down menu, select the applications you want to group together.
4. Click **Submit**. You return to the **Application Groups** page.

To delete a group:

1. From the **Application Groups** page, in the **Action** column, click the **Delete** icon. The **Delete AI Application** window opens.
2. Click **OK** to delete the group.

## Managing AI Application API Key

To add or make changes to a DAS mode AI application's API key:

1. If you want to delete an AI application API key, click the **Delete** button and then click **OK** on the window that appears. See image.
2. If you want to edit your AI application API key, click the **Edit** button to open **Edit App Credentials** window.
3. Click **Update** when you finish making changes.

[Image: AI Guard AI Applications page showing App Name, Last Updated, Owner Email, Store Contents, Encrypt Contents, and Action]

[Image: Edit AI Application window]

[Image: AI Application Credentials page with the Edit and Delete buttons highlighted]

[Image: Delete AI Application Credential window]

[Image: AI Guard AI Applications page showing App Name, Last Updated, Owner Email, Store Contents, Encrypt Contents, and Action]

[Image: Create Application window]

[Image: Save your key window, you will need to copy the key from this window before closing]

[Image: AI Guard Application Groups page showing Name & Description Number of Applications, Last Updated, and Action columns]

[Image: AI Guard Add AI Application Group window showing Group Name, Description, and Applications fields]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/adding-and-managing-ai-guard-policy-configurations","lastmod":"2026-08-06T15:09Z","nid":"1541884"} -->
## Adding and Managing AI Guard Policy Configurations

- Source: https://help.zscaler.com/secure-ai-apps-infra/adding-and-managing-ai-guard-policy-configurations
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Configuration > General > Adding and Managing AI Guard Policy Configurations
- Last modified: 2026-08-06T15:09Z
- Summary: Adding and Managing policy configurations for AI Guard.

AI Guard works by enforcing enterprise policies on prompts and responses between users and public AI apps, such as ChatGPT, Perplexity, Claude, etc., and between private AI apps and foundational Large Language Models (LLMs), such as OpenAI, Anthropic, etc. You set a policy by enabling one or more included detectors on prompts and responses. These detectors are activated on prompts and responses based on the policies you define in the portal. Every policy is a guardrail, and you can set up multiple policies, define and apply a policy per app, apply multiple policies to one app, or apply a policy to multiple apps.

## Adding Policy Configurations

To add a new policy to AI Guard:

1. In the AI Security Admin Portal left-side navigation, go to: **AI Guard** > **Policy >** **Configurations**. See image.
2. Click **Add More** to open the **Add New Configuration** page.
3. Under **Basic Information**, enter: See image.
  1. **Policy Name**
  2. (Optional) **Description**
4. Click **Continue to Detectors**. The **Prompt Detectors** tab opens.
5. On the **Prompt Detectors** tab, click on any of the prompt detectors you want to include with your policy. The **Configure** window appears.
6. Most prompt detectors share the following configuration options: See image.
  - **Show Details**: Gives a description and examples of the detector.
  - **Enabled**: Enables the prompt detector and is on by default. Click the checkbox to disable the prompt detector.
  - **Severity**: Corresponds to the severity icons in the **Prompt Detectors** column on the **Policies** page.
  - **Threshold**: The lower the threshold setting, the more strict AI Guard is with activating the policy (i.e., allowing, blocking, or detecting the prompt). Some prompt detectors such as **Text** and **Off Topic** don't include this option because those detectors activate with specific keywords, phrases, or topics.
  - **Action**: Select what you want the policy to do when activated for specific prompt detectors. **Allow** allows the prompt to proceed, **Block** blocks the prompt, and **Detect** flags the prompt as detected. Some detectors include a **Disabled** option to ensure what is disabled is not covered under that category's detections. In the **PII** detector, the **Person's name** category is defaulted to **Disabled**.
7. The following prompt detectors include additional configuration options: See image.
  - **Code**: Includes a list of **Programming Languages** where you select for your policy.
  - **Text**: Includes a **Regex Pattern** category where you enter a **Name** and the regular expression **Pattern** you want to add.
  - **Competition**: Includes a **Competitors**category where you enter your specific competitor names. Only 10 can be set at a time for a single policy.
  - **Language**: Includes a list of **Languages** where you select for your policy.
  - **Secrets**: Includes a list of secret types such as **API Keys** or **Tokens**.
  - **Off Topic**: Includes a field for adding whatever topic you want to be considered off topic in your policy, such as sports or music.
  - **PII**: Includes a list of personally identifiable information types such as **Person's name** and **Location**.
  - **Topic**: Includes the ability to add multiple custom topics to add to your policy. Enter a **Name** and **Topic Definition** for the topic you want to add. Only 10 can be set at a time for a single policy.
  - **Prompt Tags**: Includes specific categories to add to your policy, such as **News and Media**, **Shopping and Retail**, and **Technology**.
  - **Intellectual Property**: Includes a **Sensitive Context** field to enter what you want to avoid leaking, such as a project launch date.
8. After configuring the prompt detectors, click **Save Changes** to close the window and click **Next** on the **Prompt Detectors** tab. The **Response Detectors** tab opens.
9. Configuring response detectors follows similar steps as configuring prompt detectors, so refer back to the earlier steps for more information.
10. After configuring your response detectors, click **Next**. The **Review** tab opens. See image.
11. The **Review** tab shows you a summary of the policy configuration you created. Click **Submit Policy** if everything looks correct.

After creating a policy, the next step is to create a policy match. For more information on policy matching, see [Managing AI Guard Policy Control](https://help.zscaler.com/secure-ai-users/managing-ai-guard-policy-control).

## Configuration Actions

To view details of a policy configuration, under **Action**, click the **View Details** button. The **View Configuration Details** window appears and shows you the following information:

- **ID**
- **Name**
- **Description**
- **Controls**
- **Prompt Detectors**
- **Response Detectors**

To edit an AI Guard policy configuration:

1. Under the **Action** column, click the **Edit Configuration** icon. The **Edit Policy** page opens.
2. Make any desired changes to the policy. On the **Review** tab, click **Submit Policy** when finished. See image.

To copy a policy configuration:

1. Under the **Action** column, click the **Copy Configuration** icon. The **Copy Configuration** page opens.
2. Make any desired changes to the policy. On the **Review** tab, click **Submit Policy** when finished.

To delete a policy configuration:

1. Under the **Action** column, click the **Delete Configuration** icon. The **Delete Configuration** window appears. See image.
2. Type `Delete` into the text field to confirm the deletion of the policy, and then click the **Delete** button.

[Image: AI Guard Policies page with an example policy visible]

[Image: Basic policy information fields which includes Policy Name and Description]

[Image: Configuring detector window showing the common options available.]

[Image: Configure policy window for the Competition detector showing its unique configuration options.]

[Image: Review tab for adding a policy showing the information entered previously in the process]

[Image: Review page for adding a policy showing the information entered previously in the process]

[Image: AI Guard Policy Configuration Delete menu showing the name of the policy you want to delete, a field to type "Delete" into to confirm, and a Delete button to complete.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/adding-llm-provider-credentials-ai-guard","lastmod":"2026-08-06T14:37Z","nid":"1541886"} -->
## Adding LLM Providers and Credentials in Proxy Mode

- Source: https://help.zscaler.com/secure-ai-apps-infra/adding-llm-provider-credentials-ai-guard
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Configuration > Proxy Mode > Adding LLM Providers and Credentials in Proxy Mode
- Last modified: 2026-08-06T14:37Z
- Summary: How to manage Large Language Model (LLM) provider credentials for AI Guard in Proxy mode.

In AI Guard's Proxy mode, AI Guard acts as a reverse proxy for the LLM provider. As such, you must add the respective credentials to authenticate with your LLM providers.

## Prerequisites

Before managing LLM provider credentials, access your LLM provider and copy the keys or IAM roles from your LLM provider dashboard.

## Adding LLM Providers

Register the AI services your apps use: OpenAI, Anthropic, Azure OpenAI, and more.

1. Go to **AI Security Admin Portal** > **AI Guard** > **LLMs** > **Providers**. See image.
2. Click **Add More**. The **Add LLM Provider** window opens.
3. Enter the following: See image.
  1. **Provider Name**: Enter a name for your LLM provider.
  2. **Providers Type**: Select the LLM provider you are using.
  3. **Deployment**: Select **Public** or **Private**.
4. Click **Submit**.

## Adding LLM Provider Credentials

To add the credentials for an LLM provider:

1. In the AI Security Admin Portal left-side navigation, go to **AI Guard** > **LLMs.** Click the **Credentials** tab. See image.
2. On the **Credentials** page, click **Add More**. The **Add LLM Credentials**window appears.
3. In the **Add LLM Credentials** window: See image.
  - **Name**: Enter the name for the credential.
  - **LLM Provider**: From the drop-down menu, select the LLM provider to associate with this credential.
  - **Expires At**:(Optional) Select the date when the credential expires.
  - **API Key**: Enter the LLM provider credentials that you copied from your LLM provider dashboard.
4. Click **Submit**.

## Editing LLM Provider Credentials

To edit the credentials for an LLM provider:

1. In the AI Security Admin Portal left-side navigation, go to **AI Guard** > **LLMs.** Click the **Credentials** tab. See image.
2. On the **Credentials** page, in the **Action** column next to a credential, click the **Edit** icon. The **Add LLM Credentials**window appears.
3. In the **Add LLM Credentials** window, modify any of the credential information. See image.
4. Click **Submit**.

[Image: LLM providers page]

[Image: Add LLM Provider window]

[Image: LLM Provider Credentials page]

[Image: LLM Provider Credentials page]

[Image: Add LLM Provider window with Provider Name, Type, and Public/Private entered.]

[Image: Add LLM Provider window with Provider Name, Type, and Public/Private entered.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/agentforce","lastmod":"2026-07-21T23:06Z","nid":"1541838"} -->
## Agentforce

- Source: https://help.zscaler.com/secure-ai-apps-infra/agentforce
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Connect an Asset > Connections > Agentforce
- Last modified: 2026-07-21T23:06Z
- Summary: Integration Setup information for Agentforce is provided in this article.

After you select your [connection type](https://help.zscaler.com/secure-ai-apps-infra/connecting-ai-app), the **Configure your connection** page appears in the next step and prompts you to enter the required connection details as follows:

- **Organization Domain:**The domain of your Salesforce org instance that the application or client authenticates against and to which it sends API requests.
- **Client ID:** The public value that indicates which application is requesting access.
- **Client Secret:** The secret key associated with the Client ID, used to prove the application’s identity during OAuth authentication (must be kept confidential).
- **Agent ID:** The unique identifier of the specific Agentforce agent (resource) you want to call, used to route requests to that exact agent/configuration.
- **Variables**(optional): A JSON object of context and custom variables to pass to the agent when starting a session. These variables provide additional context to the agent during conversations. You can define variables in Agentforce Builder and enable them for API access.

## How to Obtain Required Fields

To obtain the required fields, refer to the [Salesforce documentation](https://developer.salesforce.com/docs/ai/agentforce/guide/agent-api-get-started.html).

- **Register Salesforce App:** To register a Salesforce App, follow instructions from [Salesforce](https://developer.salesforce.com/docs/ai/agentforce/guide/agent-api-get-started.html#create-a-salesforce-app) documentation.
- **Client Secret and Client ID:** To mint Consumer Key (Client ID) and Consumer Secret (Client Secret), follow the [Obtain Credentials step](https://developer.salesforce.com/docs/ai/agentforce/guide/agent-api-get-started.html#obtain-credentials) from the Salesforce documentation.
- **Organization Domain:**To get the Organization Domain, from the **Setup** menu in the top right corner of the Agentforce app, search for **My Domain** and copy the value shown in the **Current My Domain URL** field.
- **Agent ID:** To obtain an Agent ID, follow instructions from [Get the Agent ID for an Agent](https://developer.salesforce.com/docs/ai/agentforce/guide/agent-api-agent-id.html) section.
- **Variables:**To obtain the variables, see the [Salesforce Variables documentation](https://developer.salesforce.com/docs/ai/agentforce/guide/agent-api-variables.html).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/ai-guard-audit-log","lastmod":"2026-07-23T12:08Z","nid":"1541875"} -->
## AI Guard Audit Log

- Source: https://help.zscaler.com/secure-ai-apps-infra/ai-guard-audit-log
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Monitoring > AI Guard Audit Log
- Last modified: 2026-07-23T12:08Z
- Summary: Learn how to view the AI Guard Audit Log.

The Audit Log page tracks any changes users made inside AI Guard. This can range from anything from updating a policy control to signing in.

To view the AI Guard Audit Log page:

1. Go to **AI Security Admin Portal** > **AI Guard** > **Audit Log**. See image.
2. The Audit Log page allows you to view the following information:
  1. **Timestamp**: The date and time that an action was logged.
  2. **Initiator**: The email address of the user that triggered the action.
  3. **Action**: The action performed, such as: Update, View, or Create.
  4. **Entity Type**: More granular detail about the action. For example, an Action of Update may have an Entity Type of Policy Control, meaning that the user made an update to a policy control.
3. For more information about the logged event, in the **Details** column, click the **View Request Details** button. See image.
4. The **Actions Details** window shows the following information:
  1. **Initiator**, **Action**, and **Entity Type**: The same information from the main Audit Log page.
  2. **Entity ID**: The ID number associated with the action.
  3. **Payload**: The code payload of the action.

[Image: AI Guard Audit Log page]

[Image: AI Guard Audit Log Action Details window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/ai-guard-latency","lastmod":"2026-07-24T11:46Z","nid":"1542031"} -->
## AI Guard Latency

- Source: https://help.zscaler.com/secure-ai-apps-infra/ai-guard-latency
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Troubleshooting > AI Guard Latency
- Last modified: 2026-07-24T11:46Z
- Summary: Learn how to review the latency between prompts, responses, and detections in AI Guard.

AI Guard is a service that provides run-time protection for your AI applications by enforcing enterprise policies that prevent prompt injections, block jailbreak attempts, and stop personal information leakage with guardrails around LLM interactions.

This process involves a user sending a request that is first analyzed by AI Guard to see if it triggers any detectors. If the prompt isn't blocked, the LLM provider then processes the prompt and returns a response to AI Guard for analysis. If the response isn't blocked, it is then sent to the user. Depending on the complexity of the prompt, speed of analysis, and internet speed, this can cause latency in the AI application interaction for the user. AI Guard tracks this information and includes it in the details of every logged transaction.

To access latency information:

1. Go to **AI Security Admin Portal** > **AI Guard** > **Dashboard**. The **Dashboard** page opens.
2. Find the transaction you want to see the latency information for and in its **Details** column, click the **View Details** button. The **Transaction Details** window appears. See image.
3. In the **Performance & Network Stats** section, you will find the following latency-related information about the transaction: See image.
  1. **Total Time Taken By LLM**: Time it took for the LLM provider to read the prompt and return a response.
  2. **Detection Time on Request**: Time it took for the AI Guard policy detectors to analyze the prompt.
  3. **Detection Time On Response**: Time it took for the AI Guard policy detectors to analyze the response.

[Image: AI Guard Dashboard with annotations around the View Details button]

[Image: Latency information for a specific transaction in AI Guard with annotations around the time based information]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/ai-guard-multilingual-support","lastmod":"2026-07-24T09:41Z","nid":"1541624"} -->
## AI Guard Multilingual Support

- Source: https://help.zscaler.com/secure-ai-apps-infra/ai-guard-multilingual-support
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Getting Started > AI Guard Multilingual Support
- Last modified: 2026-07-24T09:41Z
- Summary: This article contains a list of languages supported by the AI Guard interface.

AI Guard currently supports the following languages with more being added in the future:

- English
- French
- German
- Japanese
- Portuguese
- Spanish

As more prompt and response detector categories are added, they may not support all languages at this time. You can see at-a-glance which detector categories support all languages by finding the **Multilingual** badge on their tile. Hovering your mouse cursor over it will show which languages are supported by the specific detector.

See image.

To learn more about prompt and response detectors, see [Adding and Managing AI Guard Policy Configurations](https://help.zscaler.com/secure-ai-apps-infra/adding-and-managing-ai-guard-policy-configurations).

[Image: Prompt Detector Configuration page showing the Multilingual support badge and pop-up list of languages]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/ai-guard-users-policy-testing","lastmod":"2026-07-23T12:15Z","nid":"1541819"} -->
## AI Guard Policy Testing

- Source: https://help.zscaler.com/secure-ai-apps-infra/ai-guard-users-policy-testing
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Troubleshooting > AI Guard Policy Testing
- Last modified: 2026-07-23T12:15Z
- Summary: Learn how to test a policy in AI Guard for Users.

After adding a large language model (LLM) provider, LLM provider credentials, and a policy to AI Guard, you can test your policy to make sure it is working to your satisfaction. To learn more, see [Adding and Managing AI Guard Policies](https://help.zscaler.com/secure-ai-users/adding-and-managing-ai-guard-policy-configurations) and [Managing AI Guard Policy Control](https://help.zscaler.com/secure-ai-users/managing-ai-guard-policy-control).

To test an AI Guard policy:

1. In the AI Guard left-side navigation, click **Policy Testing**. The **Policy Testing** page appears. See image.
2. Enter the following information:
  - **Provider Credential**: From the drop-down menu, select an LLM credential.
  - **Policy**: From the drop-down menu, select a policy you want to test.
  - **LLM Model**: From the drop-down menu, select the LLM model you want to use.
  - **Prompt**: Enter a prompt that you want to test.
3. Click **Send** to test the policy. Results appear below the prompt. As an example, you could test a policy that blocks Spanish in both the prompts and the responses. If a user enters, `How do I say "what is the weather" in Spanish?`, the prompt is allowed because the prompt is in English, but the policy blocks the response because the answer is in Spanish. In addition to the policy test showing you what is allowed or blocked, it also shows you the prompt and response without guardrails. See image.

[Image: AI Guard Policy Testing page with an example prompt entered.]

[Image: AI Guard Policy Testing page showing the results of an example prompt]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/amazon-bedrock-agentcore","lastmod":"2026-08-16T19:33Z","nid":"1542865"} -->
## Amazon Bedrock AgentCore

- Source: https://help.zscaler.com/secure-ai-apps-infra/amazon-bedrock-agentcore
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Connect an Asset > Connections > Amazon Bedrock AgentCore
- Last modified: 2026-08-16T19:33Z
- Summary: Integration information for Amazon Bedrock AgentCore is provided in this article

After you select your [connection type](https://help.zscaler.com/secure-ai-apps-infra/connecting-ai-app), the **Configure your connection** page appears in the next step and prompts you to enter the required connection details.

[Image: bedrock_agentcore]

IMAGE PLACEHOLDER: Figure 1: Amazon Bedrock AgentCore Integration Example

- **Agent Runtime ARN** - ARN (Amazon Resource Name) of the Bedrock AgentCore runtime you want to invoke.
- **AWS Region** - AWS region where that runtime is deployed (for example: "us-east-1").
- **AWS Access Key Id** - IAM (Identity and Access Management) access key used to sign Bedrock AgentCore API calls.
- **AWS Secret Access Key** - IAM secret access key paired with the Access Key Id. Note that AWS **only shows secret keys at creation time**, so store it securely.
- **Qualifier** - Optional runtime qualifier (for example a version or alias) used to route the invocation to a specific runtime revision. Leave it empty to use the runtime's default.
- **Payload Template** - JSON payload sent to the AgentCore runtime on each invocation. Use placeholders to let the platform inject dynamic values:
  - **{message}** - the current probe/test message.
  - **{session_id}** - unique identifier for the conversation session (useful for multi-step tests).
- **Response Path** - The JSON path pointing to the text response in the response.
- **Image Response Path** - The JSON path pointing to generated **images** in the response (for example an array of base64 strings or URLs). Leave empty if your runtime does not return images.
- **Audios Response Path** - The JSON path pointing to generated **audio** in the response. Leave empty if not applicable.
- **Documents Response Path** - The JSON path pointing to generated **documents/files** in the response. Leave empty if not applicable.

## Obtaining the Required Fields

- Agent Runtime ARN
- AWS Region
- AWS Access Key Id/AWS Secret Access Keys
- Qualifier
- Payload Template
- Response Path
- Image Response Path
- Audios Response Path
- Documents Response Path

To obtain the Agent Runtime ARN:

1. Find the runtime in the AWS console where you manage your AgentCore runtime.
2. Copy the resource **ARN** from the runtime details page.
3. If you provisioned it via IaC (CloudFormation/Terraform/CDK), you can also use the output variable that contains the ARN.

To obtain the AWS Region:

1. Use the same region where the runtime lives.
2. You can read it from the AWS console region selector or from the ARN itself.

To obtain the AWS Access Key Id and AWS Secret Access Key:

1. Create an access key for an IAM principal that has permission to invoke the runtime.
2. IAM console path: **IAM** → **Users** → select user → **Security credentials** → **Access keys**.
3. AWS docs: [https://docs.aws.amazon.com/IAM/latest/UserGuide/id_credentials_access-keys.html](https://docs.aws.amazon.com/IAM/latest/UserGuide/id_credentials_access-keys.html)

To obtain the Qualifier:

1. Only needed if your runtime supports routing by version or alias.
2. Use the exact qualifier value your runtime expects. Otherwise leave it blank.

To obtain the Payload Template:

1. Use the request payload schema your runtime expects.
2. Example: `{ "prompt": "{message}", "session": "{session_id}", "media": {"type": "image", "data": "{image_base64}"} }`
3. Easiest way: run one test invocation from your app or SDK, then copy the JSON body and replace the user message with `**{message}**`.
4. Add `**{session_id}**` if your runtime supports multi-turn sessions.

| Placeholder | Description |
| --- | --- |
| {message} | Main text message |
| {session_id} | Runtime session ID |
| {image_url} | Image URL |
| {image_base64} | Image as base64 data URL |
| {audio_url} | Audio URL |
| {audio_base64} | Audio as base64 data URL |
| {document_url} | Document URL |
| {document_base64} | Document as base64 data URL |

To obtain the Response Path:

1. Invoke the runtime once and inspect the raw JSON response.
2. Set this to the JSON path pointing to the **text** content you want evaluated.
3. If you're unsure about the JSON path format, see the definition used in the [REST API connection](https://help.zscaler.com/secure-ai-apps-infra/connecting-ai-app).

To obtain the Image Response Path:

- JSON path to images in the response (array of base64 strings or URLs), if your runtime returns images.

To obtain the Audios Response Path:

- JSON path to audio in the response, if your runtime returns audio.

To obtain the Documents Response Path:

- JSON path to documents or files in the response, if your runtime returns documents.

**Warning:** If you can't locate a field in the AWS console, it is likely that it's in **your runtime's request/response contract** (payload template and response paths) rather than AWS metadata.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/amazon-bedrock-agents","lastmod":"2026-08-17T20:46Z","nid":"1542890"} -->
## Amazon Bedrock Agents

- Source: https://help.zscaler.com/secure-ai-apps-infra/amazon-bedrock-agents
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Connect an Asset > Connections > Amazon Bedrock Agents
- Last modified: 2026-08-17T20:46Z
- Summary: Integration Setup information for Amazon Bedrock Agents is provided in this article

After you select your [connection type](https://help.zscaler.com/secure-ai-apps-infra/connecting-ai-app), the **Configure your connection** page appears in the next step and prompts you to enter the required connection details.

IMAGE PLACEHOLDER: Figure 1: Amazon Bedrock Agents

- **Agent Id** - Unique identifier of the Bedrock Agent you want to invoke.
- **Agent Alias Id** - Identifier of the **agent alias** to invoke. Aliases route traffic to a specific agent version.
- **AWS Region** - AWS region where the agent is created (for example: "us-east-1").
- **AWS Access Key Id** - IAM (Identity and Access Management) access key used to sign Bedrock Agent Runtime requests.
- **AWS Secret Access Key** - IAM secret access key paired with the Access Key Id. AWS **only shows secret keys at creation time**, so store it securely.

**Possible Internal Server Error When Rate Limit Is Exceeded (Code Interpreter Notice)**

If your Bedrock Agent has **Code Interpreter enabled**, AWS enforces a limit on concurrent active sessions per account/region (minimum 25). When running scans with high concurrency, **this limit can be reached quickly, causing requests to fail.**

To avoid this, we recommend adjusting the following in Target Configuration:

- **Rate Limit** to a lower value (e.g. **20 or lower**) to control the number of messages per minute.
- **Disable Parallel Requests** or keep concurrency low.

## Obtaining the Required Fields

- Agent Id
- Agent Alias Id
- AWS Region
- AWS Access Key Id
- AWS Secret Access Key

To obtain the Agent Id:

1. Open the AWS console and go to **Amazon Bedrock** → **Agents**.
2. Select the agent you want to test.
3. Copy the **Agent ID** from the agent details (or extract it from the agent ARN).

To obtain the Agent Alias Id:

1. Open the same agent in the AWS console.
2. Go to **Aliases** and pick the alias you want to invoke (for example `prod` or `staging`).
3. Copy the **Alias ID** (or extract it from the alias ARN).
4. Ensure the alias points to the correct agent version you want to test.

To obtain the AWS Region:

1. Use the same region where you created the agent.
2. Read it from the AWS console region selector (top bar).

To obtain the AWS Access Key Id:

1. Create an access key for an IAM principal that can invoke the Bedrock Agent Runtime.
2. IAM console path: **IAM** → **Users** → select user → **Security credentials** → **Access keys**.
3. AWS docs: [https://docs.aws.amazon.com/IAM/latest/UserGuide/id_credentials_access-keys.html](https://docs.aws.amazon.com/IAM/latest/UserGuide/id_credentials_access-keys.html)

To obtain the AWS Secret Access Key:

1. Generated together with the Access Key Id during access key creation.
2. AWS shows the secret value only once. If you lost it, create a new access key.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/architecture-ai-guard-apps-proxy-das-api-mode","lastmod":"2026-08-12T10:00Z","nid":"1542640"} -->
## Architecture of AI Guard for Apps in Proxy and DAS/API Modes

- Source: https://help.zscaler.com/secure-ai-apps-infra/architecture-ai-guard-apps-proxy-das-api-mode
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Getting Started > Architecture of AI Guard for Apps in Proxy and DAS/API Modes
- Last modified: 2026-08-12T10:00Z
- Summary: Learn about the general architecture of AI Guard for Apps in Proxy and DAS/API mode and how it works with LLMs and generative AI (GenAI) applications.

Zscaler AI Guard for Apps provides comprehensive runtime protection for AI applications by enforcing enterprise policies on prompts and responses between end users and Large Language Models (LLMs). It is designed to mitigate risks such as prompt injection, data leakage, and toxic content.

The platform supports two primary deployment modes: Proxy Mode and Detection-as-a-Service (DAS/API) Mode.

## Proxy Mode (Inline Reverse Proxy)

In Proxy Mode, AI Guard acts as an inline reverse proxy situated between the AI application and the LLM provider.

- How it Works
- Best Use Cases
- Step-by-Step Diagram

## Detection-as-a-Service (DAS/API) Mode

DAS Mode, also referred to as API or DAS/API Mode, operates out-of-band relative to the primary network path between the application and the LLM.

- How it Works
- Best Use Cases
- Step-by-Step Diagram

## Technical Comparison

| Feature | Proxy Mode | DAS/API Mode |
| --- | --- | --- |
| Enforcement Type | Inline/Synchronous | Out-of-band |
| Endpoint URL | `https://proxy.zseclipse.net` | `https://api.zseclipse.net` |
| Credential Handling | AI Guard manages LLM credentials | App keeps direct LLM credentials |
| Connectivity | Only support public LLM providers | Supports any provider (public/private) |
| Integration Style | URL/Header override | API integration |

## Multi-Mode Support

Organizations do not have to choose a single mode for their entire environment. An AI Guard tenant can support multiple applications simultaneously, with some utilizing Proxy mode and others utilizing DAS mode based on their specific technical requirements.

- **Endpoint Redirect**: The application developer modifies the application configuration to point to the Zscaler proxy URL (`https://proxy.zseclipse.net`) instead of the native LLM provider’s API endpoint. The following are endpoint redirect examples for Claude (Anthropic):
  | Type | Endpoint URL |
  | --- | --- |
  | Native Anthropic API | `https://api.anthropic.com/v1/messages` |
  | AI Guard Proxy API | `https://proxy.zseclipse.net/v1/messages` |
- **Credential Management**: The application uses a Zscaler-specific API key to authenticate with AI Guard. AI Guard then uses the stored upstream LLM provider credentials to forward the request to the actual model.
- **Synchronous Inspection**: All traffic flows through AI Guard in real-time. Prompts and responses are inspected, and based on configured policies, traffic can be allowed, blocked, or redacted.

- **Public LLMs**: Ideal for providers like OpenAI, Anthropic, or Google Gemini that are reachable via the internet.
- **Minimal Code Changes**: Best when the application framework or SDK allows for simple overrides of the base URL and headers.
- **Fast Rollout**: Simplifies integration by following a common proxy pattern.

The following is a visual representation and a step-by-step explanation of an example Proxy mode setup for AI Guard:

[Image: AI Guard for users architecture diagram]

1. **App Prompt**: A prompt is sent in the application. This can be public or private.
2. **Backend Connection**: The application's front-end connects to the backend server.
3. **Proxy Invocation**: The message route triggers code to send the prompt to AI Guard, acting as a proxy rather than connecting directly to the LLM.
4. **Prompt Inspection**: AI Guard evaluates the prompt. If allowed, it forwards the request to the public AI endpoint, such as OpenAI (https://api.openai.com).
5. **LLM Processing**: The public GenAI/LLM analyzes the prompt and returns the output to AI Guard.
6. **Response Inspection**: AI Guard inspects the GenAI/LLM output. If allowed, it forwards the response to the backend server.
7. **Server Routing**: The backend server receives the processed message and returns it to the AI application front-end.
8. **Final Delivery**: The front-end delivers the final response back to the user, client, browser, or workload.

- **Direct LLM Connection**: The application maintains its direct connection and native credentials with the LLM provider.
- **Sidecar API Calls**: The application code is updated to make explicit, separate API calls to AI Guard (`https://api.zseclipse.net`) for every interaction.
- **Policy Evaluation**: The application sends both prompt and response content to AI Guard, which responds with a "block" or "allow" message. The application then enforces this decision before proceeding with the LLM call or returning the response to the user.

- **Private LLMs**: Required for models that are not internet-reachable or are hosted on-premises where a proxy cannot be easily inserted.
- **Complex Routing**: Suitable for advanced agentic workflows where the application needs to maintain full control over the model connection.
- **Universal Compatibility**: Works in almost all cases, regardless of whether the provider is public or private.

The following is a visual representation and a step-by-step explanation of an example DAS/API mode setup for AI Guard:

[Image: Diagram with each step of the DASS/API mode]

1. **Request Initiation**: A prompt is made in an AI application. This can be public or private.
2. **System Connectivity**: The frontend interface establishes a connection with the backend API server, where core logic and routing are managed.
3. **Inbound Security Inspection**: The chat route triggers a validation process, forwarding the input prompt to the AI Guard Detection API for authorization.
  1. **Inbound Policy Enforcement**: AI Guard evaluates the prompt. Unauthorized content is blocked immediately, preventing the request from reaching the LLM or agent.
  2. **Prompt Processing**: Upon authorization, the backend API forwards the approved prompt to the private LLM for analysis.
4. **Knowledge Retrieval**: The LLM analyzes the prompt, utilizing Retrieval-Augmented Generation (RAG) to query the internal knowledge base.
5. **Data Integration**: The knowledge base returns the relevant data to the agent or LLM for response formulation.
6. **Response Generation**: The agent or LLM transmits the generated output to the backend API server.
7. **Outbound Security Inspection**: The backend API sends the generated response to the AI Guard Detection API for a final safety validation.
  1. **Outbound Policy Enforcement**: AI Guard evaluates the response. If blocked, the system prevents the transmission of the response to the user.
8. **Transmission to Frontend**: The backend API server relays the authorized response to the frontend application.
9. **Final Delivery**: The frontend interface completes the process by delivering the response to the user.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/best-practices-runbook-competitor-detector","lastmod":"2026-07-24T11:50Z","nid":"1541785"} -->
## Best Practices Runbook: Competition Detector

- Source: https://help.zscaler.com/secure-ai-apps-infra/best-practices-runbook-competitor-detector
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Configuration > Best Practices > Best Practices Runbook: Competition Detector
- Last modified: 2026-07-24T11:50Z
- Summary: Learn the best practices for configuring the Competition prompt/response detector for AI Guard as well as explanations on how it works, example customer scenarios, and troubleshooting.

AI Guard works by enforcing enterprise policies on prompts and responses between users and public AI apps, such as ChatGPT, Perplexity, Claude, etc., and between private AI apps and foundational Large Language Models (LLMs), such as OpenAI, Anthropic, etc. You set a policy by enabling one or more included detectors on prompts and responses. These detectors are activated on prompts and responses based on the policies you define in the portal.

The Competition Detector flags user prompts that reference monitored competitors, with built-in tolerance for name misspellings, ticker symbol usage, and multilingual input.

This best practices runbook contains the following information on the Competition detector:

- How the Competition detector works.
- How to configure the detector.
- Choosing the correct threshold.
- Troubleshooting and FAQ.

The Competition detector watches every prompt a user sends to an LLM and flags the prompt when it mentions a competitor. The user provides a list of competitor names; AI Guard does the rest.

The Competition detector catches the following:

- **Plain mentions**: "How does Veltroxmax Motors's autopilot compare to our system?" triggers on competitor Veltroxmax.
- **Ticker symbols**: "VTRX earnings looked strong this quarter" triggers on competitor Veltroxmax (ticker VTRX).
- **Typos and OCR artifacts**: "Veltroxmax Motors's new model is fast" still triggers on Veltroxmax when there is business context.
- **Other languages**: "die bleep bloop netwerke firewall is uitstekend" (Afrikaans) triggers on Bleep Bloop Networks.
- **Phonetic spellings**: "the veltrohmax is fast" triggers on Veltroxmax.

The Competition detector does not trigger on the following:

- **Common-word false positives**: "I walked around the city block" does not trigger competitor Block.
- **Fictional or unrelated references**: "Peter Pan is a beloved character" does not trigger competitor Pan.
- **Idioms and wordplay**: "I've had writer's block all week" does not trigger Block.
- **Landmarks**: "Times Square was crowded with tourists" does not trigger Square.
- **Generic vocabulary**: "We are out of stock for this item" does not trigger Stock.

AI Guard makes the distinction because every potential match must have evidence that it refers to a company, not just a common word.

No evidence means there is no trigger, regardless of length or spelling.

To configure the Competition detector in an AI Guard policy:

1. In the AI Security Admin Portal left-side navigation, go to: **AI Guard** > **Policy >** **Configurations**.
2. Click **Add More** to open the **Add New Configuration** page. To edit an existing policy, in the **Action** column, click **Edit Configuration** for a policy.
3. Under **Basic Information**, enter: See image.
  1. **Policy Name**
  2. (Optional) **Description**
4. Click **Continue to Detectors**. The **Prompt Detectors** tab opens.
5. On the **Prompt Detectors** tab, click on the **Competition** tile. The **Configure** window appears.
6. The following configuration options are the intitial recommendations for the Competition detector: See image.
  - **Enabled**: Enables the prompt detector and is on by default. Leave **Enabled**.
  - **Severity**: Corresponds to the severity icons in the **Prompt Detectors** column on the **Policies** page. The recommended setting is LOW for initial deployments. Raise to MEDIUM/HIGH only after a user confirms the false-positive rate is acceptable.
  - **Threshold**: The lower the threshold setting, the more strict AI Guard is with activating the policy (i.e., allowing, blocking, or detecting the prompt). Keep the default (**0.7**) for production. For more information, refer to the next section.
  - **Default State**: What happens by default when the detector triggers: **Detect** (log only), **Allow** (pass through), or **Block** (stop the prompt).
  - **Competitors**: The list of up to 10 names to watch for. Each entry has its own **Action** override (**Allow** / **Block** / **Detect**). Start with three to five high-value competitors. The more names you enter, the more noise and errors may appear. Fewer competitors is usually better.
    - **Competitor naming tips**: Use the common short form of the name: "**Veltroxmax**," not "**Veltroxmax Motors**" or "**Veltroxmax Corp.**" The detector already knows about corporate suffixes (Inc, Corp, Ltd). For multi-word names like **Bleep Bloop Networks**, use the full name. The detector handles partial matches automatically.
  - **Action (per Competitor)**: Select what you want the policy to do when activated for specific prompt detectors.
7. After configuring the prompt detectors, click **Save Changes** to close the window and click **Next** on the **Prompt Detectors** tab. The **Response Detectors** tab opens.
8. Configuring response detectors follows the same steps as configuring prompt detectors, so refer back to the earlier steps for more information.
9. After configuring your response detectors, click **Next**. The **Review** tab opens. See image.
10. The **Review** tab shows you a summary of the policy configuration you created. Click **Submit Policy** if everything looks correct.
11. In the AI Security Admin Portal left-side navigation, go to: **AI Guard** > **Policy Testing**.
12. Validate in **Policy Testing** with five to ten sample prompts to confirm your setup is working as expected. To learn more about policy testing, see [AI Guard Policy Testing](https://help.zscaler.com/secure-ai-apps-infra/ai-guard-users-policy-testing).

After creating a policy and confirming it works as expected, the next step is to create a policy match. For more information on policy matching, see [Managing AI Guard Policy Control](https://help.zscaler.com/secure-ai-apps-infra/managing-ai-guard-policy-control).

The **threshold** is a number between 0.0 and 1.0 that controls how confident the model must be before it calls a prompt a match. Think of it as a volume knob: turn it up and only very obvious mentions trigger. Turn it down and more borderline cases trigger too.

The default is **0.7**. This was chosen based on Zscaler's internal validation set, a benchmark spanning 28 languages and roughly 9,000 labeled prompts covering both real competitor mentions and tricky false-positive cases (city block, writer's block, out of stock, Times Square, Peter Pan, etc.). At 0.7 the detector hits the sweet spot between catching real mentions and avoiding false alarms.

Zscaler strongly recommends that you leave the threshold at **0.7**. We built and validated the detector around this default.

Refer to the following table when considering changing the threshold:

| Range | What it means | When to use it |
| --- | --- | --- |
| 0.85 to 1.00 | Only unambiguous mentions trigger. The competitor's name is written clearly, in business context, and the model is highly confident. | If you are very sensitive to false positives. They would rather miss borderline cases than pay someone to review noise. Common for initial Block-mode roll-outs. |
| 0.70 to 0.84 | Default sweet spot. Catches real mentions including typos, tickers, and other languages. Rejects common-word false positives. | Production deployments. Start here unless you need tighter or looser behavior. |
| 0.50 to 0.69 | Also fires on semantically adjacent cases: prompts that mention the competitor only indirectly or use ambiguous wording. | Investigation/audit mode where you want to see anything the model thinks is even plausibly related. Not recommended for Block mode, the false-positive rate is meaningfully higher. |
| Below 0.50 | Very permissive. Loose or tangential mentions trigger. | Not recommended for production. Useful only for internal debugging when you want to see the full score distribution. |

### Troubleshooting

If the Competition detector isn't triggering correctly, run through the following checklist:

- **Is the detector actually Enabled?** Toggle in the UI, not just added competitors.
- **Is the competitor name configured correctly?** Check spelling and spacing.
- **Is the prompt lowercase with no business context?** "I like oracle" (no business words, no capital) will not trigger. This is intentional, otherwise every mention of the word oracle in mythology or fiction would trigger the detector.
- **Is the threshold unusually high?** Check that the threshold is 0.7, not 0.9.
- **Is the policy test tool accurate?** When using Policy Testing, confirm the test prompt is being run through the same policy you've has deployed. A different policy won't use the same competitor list.
- **Is the prompt very short?** Prompts under ~10 characters don't give the model enough context to classify reliably.
- **Is the word being used as a proper noun in a business context?** If the prompt literally says "Oracle's Q3 earnings beat estimates", this is a correct detection.
- **Is the competitor's name also a common English word?** Common English words are all prone to being used as ordinary words and as company names. The detector uses business context to tell them apart, but rare context mismatches happen.
- **Is the score close to threshold (0.70 – 0.75)?** Borderline scores are expected to have both true and false positives. Raising the threshold to 0.80 will cut most of these but also drop real matches.
- **File a ticket with Zscaler Support for persistent false positives.** Include the following information in your ticket:
  - Exact prompt text.
  - Configured competitor name.
  - Confidence score from scoreByCompetitor.
  - Tenant ID.
  - Whether the prompt was in English or another language.

### FAQs

- **How many competitors can you configure?** Up to 10 per policy. Beyond that, noise and latency increase. If you needs more than 10, they probably want the Topic detector instead — it handles conceptual categories better.
- **Can you configure the same competitor list across policies?** Yes. Each policy has its own Competition configuration. Copy-paste the competitor list when duplicating a policy.
- **How does the detector handle tickers I haven't told it about?** The detector ships with a built-in ticker map covering the largest tech and finance tickers. If you have a competitor with a ticker that isn't in the map, it'll still try fuzzy matching, but matches are less reliable. File a ticket to add new tickers.
- **Does it work on non-English prompts?** Yes. 28 languages are supported out of the box. Business-context keywords are built in for major Germanic, Romance, Nordic, Slavic, Greek, Turkish, and Southeast Asian languages. Capitalization evidence (i.e., the name of the competitor capitalized) is English-only; in other languages the detector relies on business context alone.
- **Can the detector handle hyphens, apostrophes, and possessives?** Yes. All of these are supported.
- **What if you wants to allowlist certain prompts?** Not supported at the Competition-detector level. Use policy-level exceptions (exclude a user group, restrict to certain apps) or a sentence-level allow rule if you have those. To learn more, see [Manage Prompt Allowlist](https://help.zscaler.com/secure-ai-apps-infra/managing-prompt-allowlist).
- **Can I export the detection log for analysis?** Yes. In the AI Security Admin Portal, go to **AI Guard** > **Log Exports**. The Competition detector's triggers are tagged with the per-competitor scoreByCompetitor dict, so you can pivot on individual competitors in a BI tool or spreadsheet.
- **How often is the underlying model updated?** Retrained periodically as new false-positive patterns surface. Updates are rolled out transparently, no action needed. Check release notes for version updates.

[Image: Competitor detector configuration page]

[Image: Basic policy information fields which includes Policy Name and Description]

[Image: Review tab for adding a policy showing the information entered previously in the process]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/best-practices-runbook-topicoff-topic-detectors","lastmod":"2026-08-25T13:56Z","nid":"1541798"} -->
## Best Practices Runbook: Topic/Off Topic Detectors

- Source: https://help.zscaler.com/secure-ai-apps-infra/best-practices-runbook-topicoff-topic-detectors
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Configuration > Best Practices > Best Practices Runbook: Topic/Off Topic Detectors
- Last modified: 2026-08-25T13:56Z
- Summary: Learn the best practices for configuring the Topic and Off Topic prompt/response detectors for AI Guard as well as explanations on how it works, example customer scenarios, and troubleshooting.

AI Guard works by enforcing enterprise policies on prompts and responses between users and public AI apps, such as ChatGPT, Perplexity, Claude, etc., and between private AI apps and foundational Large Language Models (LLMs), such as OpenAI, Anthropic, etc. You set a policy by enabling one or more included detectors on prompts and responses. These detectors are activated on prompts and responses based on the policies you define in the portal.

The **Topic** detector triggers when a user's prompt matches a topic you care about: salary discussions, medical questions, weapons, legal matters, etc., even when phrased indirectly or in another language. The Off-Topic detector does the inverse: it triggers when the prompt does not match any configured topic.

This best practices runbook contains the following information on the Topic/Off Topic detectors:

- How the Topic/Off Topic detectors works.
- How to configure the detector.
- Choosing the correct threshold.
- Example customer scenarios.
- Troubleshooting and FAQ.

The Topic detector (also called “On-Topic”) monitors every prompt a user sends to the LLM and flags the prompt when it's semantically related to any configured topics. The user provides a topic name and a topic definition; the detector does the rest.

The Off-Topic detector monitors the same prompt stream but triggers when the prompt does not match any configured topic. If a user defines “Customer Support” as a topic, any prompt that relates to sports, cooking, or politics will be flagged.

The Topic/Off Topic detectors catch the following:

- **Direct matches**: “What are the side effects of insulin?” triggers a Medical topic defined as “diabetes, thyroid and cancer.”
- **Indirect / semantic matches**: “How much does a senior engineer make at Google?” triggers a Salary topic even though the word “salary” isn’t in the prompt.
- **Keyword lists**: “The NBA finals were incredible this year” triggers a Leisure topic defined as “sports, religion, politics, gaming.” Each keyword is evaluated independently.
- **Multilingual prompts**: Prompts in any of 28 supported languages are handled natively. No extra configuration needed.
- **Paraphrased or indirect phrasing**: “Can you compare compensation packages between Amazon and Meta?” triggers a Salary topic even though it uses “compensation” not “salary.”

The Topic/Off Topic detectors does not do the following:

- It does not perform keyword matching by exact substring. It uses semantic understanding, so “block” in “writer’s block” won’t trigger a topic about blockchain.
- It does not chunk long prompts. Mentions buried deep in very long prompts may be missed.
- It does not replace content filtering (toxicity, PII, jailbreak). Topic detection is about subject matter, not safety.

#### When to use which detector

Both detectors use the same underlying model and the same topic definitions. The only difference is when they are triggered:

|  | Topic (On Topic) | Off Topic |
| --- | --- | --- |
| Triggers when... | Prompt is about a configured topic. | Prompt is not about any configured topic. |
| Use case | Block or log sensitive subjects (salary, medical, weapons, legal). | Keep users on task (only allow topics you’ve defined). |
| Typical action | Block or Detect on match. | Block or Detect when off topic. |
| Example | **Topic**: Salary **Prompt**: “How much do nurses earn?” **Result**: Triggered | **Topic**: Salary **Prompt**: “How much do nurses earn?” **Result**: Triggered |
| Aggregation | **OR**: Triggers if any topic matches | **AND**: Triggers only if no topic matches |

If you only want user to talk about X, Y, and Z, then you should use the Off-Topic detector. If you only want to block users from talking about A or B, then you should use the Topic detector.

### Topic Name and Definition

Each topic is a combination of a **Topic Name** and a **Topic Definition**. Both contribute to what the detector matches; the name provides additional semantic context to the model, so the name should be related to the definition.

- **Topic Name**: Should clearly describe the category being detected. Examples: “Medical”, “Salary”, “Weapons”, “Legal Compliance”.
- **Topic Definition**: choose one of the following two formats:
  - **Natural Language**: Describe what you want to detect in plain language. Example: “We want to avoid discussions related to diabetes, thyroid and cancer.” Natural language descriptions work best for broad semantic matching.
  - **Keyword List**: Enter comma-separated keywords related to the topic name. Example: “diabetes, thyroid, cancer.” Each keyword is evaluated independently. Keyword lists work best for precise, specific term matching.
- **Definition Tips**:
  - Use multi-word, descriptive definitions rather than short abstract phrases.
  - For common or broad terms, prefer natural language format over single keywords. Keyword lists are sensitive to ambiguous single-word terms.
  - Avoid parentheses or regex patterns in definitions.
  - Topic name and definition should be related; the name provides additional semantic context to the model.
  - A single-word definition like “Manager” is too vague, it will match almost anything conversational. Use “Questions about management practices, team leadership, and managerial responsibilities” instead.

### Configuration

To configure the Topic/Off Topic detectors in an AI Guard policy:

1. In the AI Security Admin Portal left-side navigation, go to: **AI Guard** > **Policy >** **Configurations**.
2. Click **Add More** to open the **Add New Configuration** page. To edit an existing policy, in the **Action** column, click **Edit Configuration** for a policy.
3. Under **Basic Information**, enter: See image.
  1. **Policy Name**
  2. (Optional) **Description**
4. Click **Continue to Detectors**. The **Prompt Detectors** tab opens.
5. On the **Prompt Detectors** tab, click on the **Topic**or**Off Topic** tile. The **Configure** window appears.
6. The following configuration options are the initial recommendations for the Topic/Off Topic detectors: See image.
  - **Enabled**: Enables the prompt detector and is on by default. Leave **Enabled**.
  - **Severity**: Corresponds to the severity icons in the **Prompt Detectors** column on the **Policies** page. The recommended setting is LOW for initial deployments. Raise to MEDIUM/HIGH only after a user confirms the false-positive rate is acceptable.
  - **Threshold**: The lower the threshold setting, the more strict AI Guard is with activating the policy (i.e., allowing, blocking, or detecting the prompt). Keep the default for production. For more information, refer to the next section.
  - **Default State**: What happens by default when the detector triggers: **Detect** (log only), **Allow** (pass through), or **Block** (stop the prompt).
  - **Custom Topic (Topic) or Topic Definition (Off Topic)**: Up to 10 topics. Each has a Name and a Definition. Refer to the previous Topic Name and Definition section for more information.
  - **Action**: Select what you want the policy to do when activated for specific prompt detectors. **Allow** allows the prompt to proceed, **Block** blocks the prompt, and **Detect** flags the prompt as detected.
7. After configuring the prompt detectors, click **Save Changes** to close the window and click **Next** on the **Prompt Detectors** tab. The **Response Detectors** tab opens.
8. Configuring response detectors follows the same steps as configuring prompt detectors, so refer back to the earlier steps for more information.
9. After configuring your response detectors, click **Next**. The **Review** tab opens. See image.
10. The **Review** tab shows you a summary of the policy configuration you created. Click **Submit Policy** if everything looks correct.
11. In the AI Security Admin Portal left-side navigation, go to: **AI Guard** > **Policy Testing**.
12. Validate in **Policy Testing** with five to ten sample prompts to confirm your setup is working as expected. To learn more about policy testing, see [AI Guard Policy Testing](https://help.zscaler.com/secure-ai-apps-infra/ai-guard-users-policy-testing).

After creating a policy and confirming it works as expected, the next step is to create a policy match. For more information on policy matching, see [Managing AI Guard Policy Control](https://help.zscaler.com/secure-ai-apps-infra/managing-ai-guard-policy-control).

The **threshold** is a number between 0.0 and 1.0 that controls how confident the model must be before it calls a prompt a match. Similar to a volume knob; turning it up will trigger only very obvious mentions. Turn it down and more borderline cases trigger.

The default is **0.7**. This was chosen based on Zscaler's internal validation set, a benchmark spanning 28 languages and roughly 9,000 labelled prompts covering both real competitor mentions and tricky false-positive cases (city block, writer's block, out of stock, Times Square, Peter Pan, etc.).

Zscaler strongly recommends that you leave the threshold at **0.7**. We built and validated the detector around this default.

Refer to the following table when considering changing the threshold:

| Range | What it means | When to use it |
| --- | --- | --- |
| 0.80 to 1.00 | Only strong, direct matches trigger. The content explicitly discusses the subject using closely related terminology. | If you are very sensitive to false positives. You would rather miss borderline cases than pay someone to review noise. Common for initial Block-mode roll-outs. |
| 0.5 to 0.7 | Semantically related content triggers, even with different phrasing or indirect discussion. Adjacent concepts within the same domain match. | Recommended range for most production deployments. Start at 0.7. |
| Below 0.50 | Loose or tangential connections trigger. Content may mention the subject in passing or share vocabulary from the same domain without being truly about it. | Not recommended for production. Useful only for internal debugging when you want to see the full score distribution. |

### What Threshold Doesn't Fix

The following are situations that the threshold setting does not fix:

- **Too many false positives.** Before raising the threshold, look at the actual false positives. If the topic definition is a single vague word (e.g., “Manager”), improve the definition first.
- **It doesn’t catch indirect mentions.**Lowering the threshold can help slightly, but the real fix is to write a better topic definition using natural language.
- **Want to block mentions of salary but not pay grade.**Threshold won’t help. Refine the topic definition to be more specific about what constitutes a salary discussion.

### Scenario A: Enterprise Blocking Salary Discussions

#### Situation

A large enterprise wants to prevent employees from using their internal LLM chatbot to discuss salary or compensation information.

#### Configuration (Topic Detector)

Use the following policy configurations for the Topic detector:

- **Enabled**: ON
- **Severity**: LOW
- **Threshold**: Keep Default
- **Default State**: Block
- **Topic**:
  - **Name**: “Salary”
  - **Definition**: “A salary is a fixed, regular payment made by an employer to an employee, typically expressed as an annual sum and paid monthly or biweekly, in exchange for work performed, regardless of hours worked.”
  - **Action**: Block

This works because the natural language definition gives the model rich semantic context. Prompts like “How much does a senior engineer make at Google?”, “Compare compensation packages between Amazon and Meta”, and “What is the minimum wage in California?” all trigger correctly. Meanwhile, unrelated prompts like “Good morning”, “What is 2+2?”, and “Generate a French poem” do not trigger.

### Scenario B: Healthcare Chatbot Stays On Topic

#### Situation

A hospital’s patient-facing chatbot should only answer questions about healthcare. Any off-topic prompts (sports, politics, cooking) should be blocked.

#### Configuration (Off Topic Detector)

Use the following policy configurations for the Off Topic detector:

- **Enabled**: ON
- **Severity**: LOW
- **Threshold**: Keep Default
- **Default State**: Block
- **Topic 1**:
  - **Name**: “Healthcare”
  - **Definition**: “We want to allow discussions related to medical conditions, treatments, medications, symptoms, diagnoses, and healthcare procedures.”
- **Topic 2**:
  - **Name**: “Administrative”
  - **Definition**: “Appointment scheduling, insurance, billing, and patient intake.”

The Off Topic detector fires when the prompt does not match any configured topic. A question about diabetes matches Healthcare, so there is no trigger (allowed). A question about the NBA finals matches neither topic, so it is triggered (blocked).

### Scenario C: Block Some Topics, Detect Others (Mixed Policy)

#### Situation

A financial services firm wants to block salary discussions entirely but only log (detect) when users ask about management practices.

#### Configuration (Topic Detector)

Use the following policy configurations for the Topic/Off Topic detectors:

- **Enabled**: ON
- **Severity**: LOW
- **Threshold**: Keep Default
- **Default State**: Detect
- **Topic 1**:
  - **Name**: “Salary”
  - **Definition**: “Salary, compensation, pay, wages, bonuses, and total rewards”,
  - **Action**: Block
- **Topic 2**:
  - **Name**: “Management”
  - **Definition**: “Management practices, team leadership, managerial responsibilities, and people management”
  - **Action**: Detect

Each topic can have its own action override, so you can mix Block and Detect within the same policy.

### Scenario D: Multilingual Deployment

#### Situation

A global company has offices in Germany, Japan, and Brazil. They want to detect medical topics across all languages.

#### Configuration

Same as any other deployment, no extra language configuration needed. Define the topic in English; the detector handles 28 languages natively. A German prompt “Was sind die Nebenwirkungen von Insulin?” (What are the side effects of insulin?) triggers the Medical topic correctly.

### Troubleshooting

If the Topic/Off Topic detectors aren't triggering correctly, run through the following checklist:

- **Is the detector actually Enabled?** Check the toggle in the UI.
- **Is the topic definition too vague?**A definition of just “Manager” matches everything loosely. Use a full natural language description.
- **Is the topic definition too narrow?** If you defined “diabetes” and the user asks about “insulin resistance,” the model should still match. If it doesn’t, broaden the definition: “diabetes, insulin, blood sugar management.”
- **Is the threshold too high?** Check that it’s 0.7, not 0.9.
- **Is the prompt very short?**Prompts under ~10 characters don’t give the model enough context.
- **Is the policy test tool running against the correct policy?**A different policy won’t use the same topic list.

If the Topic/Off Topic detectors are triggering when they shouldn't, run through the following checklist:

- **Is the topic definition a single common word?** “Manager,” “Sports,” or “Block” as a standalone definition will match too broadly. Improve the definition.
- **Is the content actually related to the topic?** “Pay ranges for a product manager role” legitimately matches both Salary and Manager topics. That may be correct.
- **Is the confidence score borderline (0.70–0.75)?** Raising the threshold to 0.80 will cut most borderline matches but also drop some real ones.
  - Gather 5–10 example false-positive prompts and file a ticket with the ML team.

Common issues:

- **Single-word definitions**: “Manager” as a topic definition is too vague. The model treats any conversational prompt as potentially about a manager. Always use multi-word, descriptive definitions.
- **Conflating Topic and Off Topic**: If you wants to block salary discussions, use the Topic detector (triggers on match). If they want to force users to stay on topic, use Off Topic (triggers when no match).
- **Too many topics**: More than 10 topics per policy increases noise and latency. If you need conceptual categories, use fewer broader topics rather than many narrow ones.

### FAQs

- **How many topics can you configure?** Up to 10 per policy. Beyond that, noise and latency increase. If more are needed, use fewer broader topics.
- **Can the same topics be used across policies?** Yes. Each policy has its own Topic / Off-Topic configuration. Copy-paste topic names and definitions when duplicating.
- **Does it work on non-English prompts?** Yes. 28 languages are supported out of the box, including Arabic, Chinese, French, German, Hindi, Japanese, Korean, Portuguese, Russian, Spanish, Thai, Turkish, Vietnamese, and more. No extra configuration needed.
- **What happens if a prompt is very long?** Very long prompts are not chunked. If critical content is in the tail, recommend shorter prompts.
- **Can I use both Topic and Off Topic on the same policy?** Yes, and this is a common pattern. For example: use Topic to block salary discussions, and Off Topic to block anything that isn’t about customer support. They evaluate independently.
- **What’s the difference between Topic Name and Topic Definition?** Both contribute to matching. The name gives the model semantic context (“Medical” tells the model we’re in the healthcare domain), and the definition tells it exactly what to look for (“diabetes, thyroid, cancer”). Always make them related.
- **Can I use regex or patterns in the definition?** No. Avoid parentheses, regex syntax, or special characters. Use plain natural language or comma-separated keywords.
- **How do natural language definitions differ from keyword lists?** Natural language (“We want to avoid discussions related to diabetes, thyroid and cancer”) works best for broad semantic matching. Keyword lists (“diabetes, thyroid, cancer”) work best for precise, specific term matching. Each keyword in a list is evaluated independently.
- **Can I see what the model sees?** The detection log contains the full response JSON, including confidence scores per topic. That’s the same data the policy engine uses.
- **How often is the model updated?** Updated periodically as new patterns surface. Updates are rolled out transparently, no action needed.
- **What if you want to allowlist certain prompts?** Not supported at the detector level. Use policy-level exceptions (exclude a user group, restrict to certain apps).

[Image: Off topic detector configuration page]

[Image: Basic policy information fields which includes Policy Name and Description]

[Image: Review tab for adding a policy showing the information entered previously in the process]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/configuring-application","lastmod":"2026-05-29T21:06Z","nid":"1540108"} -->
## Configuring the Application

- Source: https://help.zscaler.com/secure-ai-apps-infra/configuring-application
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Connect an Asset > Configuring the Application
- Last modified: 2026-05-29T21:06Z
- Summary: Information about how to configure an AI app in the AI Security Admin Portal.

After you have [configured the connection](https://help.zscaler.com/secure-ai-apps-infra/configuring-connection), the final step before enabling the probes is providing the details and capabilities of your AI app in the **Configure your application** tab.

To configure your application, do the following:

- **AI App Name**: Enter a name for the AI App that is required to be displayed within the AI Security.
- **Business Unit**: Select the business unit from the drop-down menu.
- **Application Description**: Provide a brief description of your AI application’s purpose and use case.
- **AI App Stage**: Select the stage of the AI App. This field helps track the various stages of your AI app, i.e., Development, Production, and Staging.
- **Availability**: Select the availability of the AI app from the drop-down menu, i.e. Internal or Customer-Facing.
- **RAG Supported**: Select if the app is RAG supported or not.
- **Language**: Select the language from the drop-down menu. Use this to test multilingual attack inputs against your AI app. Red teaming attacks are generated in English by default, but for better coverage, test variations are also run, and the attacks are translated into the other languages selected here.
- **Rate Limit**: Select the maximum number of requests your application can process per minute.
- **Parallel Requests**: Enable the toggle to send requests in parallel. Toggle off to have AI Security send requests to your AI app one at a time.
- **Multi-Step Attacks**: Enable the toggle to allow multi-messge attack prompts in the conversation. Probes can effectively simulate and test multi-step (multi-message) conversations to evaluate an AI system’s ability to handle context retention, nuanced understanding, and adaptive decision-making across prolonged exchanges.
- **System Prompt Tools**: List the tools and functions mentioned in your system prompt.
- **System Prompt Confidential**: Provide the confidential part of your system prompt.
- **System Prompt Non-Confidential**: Provide the non-confidential part of your system prompt.
- **Supported Modes**: Select the input mode of your AI app can process. This helps you understand which testing scenarios apply to the AI app and ensures you select compatible adversarial tests.
  - **Text**: The asset accepts and responds to text-based prompts.
  - **Image**: The asset can analyze or generate outputs based on image inputs.
  - **Document**: The asset can process document inputs (for example, PDFs or other file-based content).
  - **Voice**: The asset can process audio inputs (for example, speech or sound files).
- **Predefined Responses**: Add predefined responses that you expect to be returned by the AI app in specific situations, for example, when an adversarial input is sent and the system activates a Guardrail.
  - If the system’s actual response matches a predefined response during an adversarial testing scenario (e.g., the system replies with Message is blocked), the probe test case is marked as passed.
  - Predefined responses can be defined in two ways:
    - **Text**: A direct 1:1 match. The system response must be exactly identical to the predefined message.
    - **Regex**: A pattern-based match, defined using standard regular expression rules, allowing for flexible response validation
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/configuring-broker","lastmod":"2026-07-27T05:55Z","nid":"1541768"} -->
## Configuring a Broker

- Source: https://help.zscaler.com/secure-ai-apps-infra/configuring-broker
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Brokers > Configuring a Broker
- Last modified: 2026-07-27T05:55Z
- Summary: Information about how to configure a broker target in AI Security Admin Portal.

AI Red Teaming workers run in the Zscaler cloud and must reach your private AI application to perform Red Teaming tests. Configuring a target and attaching it to a broker allows the platform to automatically route all test traffic through the broker's outbound WSS tunnel with no firewall changes, no inbound ports to open, and no need to point the URL at the gateway manually.

Configuration involves defining the target's connection details and associating it with the broker. Attaching the broker to the target establishes the communication link between the two, enabling the broker to route traffic and facilitate secure connectivity to the target resource.

To configure a broker target, do the following:

1. Go to **Administration > AI Red Teaming > Brokers**.
2. Select the broker for which you would like to configure a target.
3. On the details page, click **Configuration**.
4. On the **Configuration** page, enter the following details:
  - **Name:** The name of the broker.
  - **Business Units:** Select the business unit from the drop-down menu.
  - **Description:** Add information such as what the broker connects to, where it runs, and who runs it.
  - **Allowed Source CIDR:** Add the CIDR that the broker may connect from. The broker may only connect from these ranges. An empty list means no restriction.
  - **Allowed Targets:** The broker may only reach these targets. An empty list means no restriction.
5. Click **Save Changes**. The Broker details are updated successfully.

[Image: The Configuration page allows you to add the allowed source CIDR and allowed targets.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/configuring-connection","lastmod":"2026-08-29T13:06Z","nid":"1540061"} -->
## Configuring a Connection

- Source: https://help.zscaler.com/secure-ai-apps-infra/configuring-connection
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Connect an Asset > Configuring a Connection
- Last modified: 2026-08-29T13:06Z
- Summary: Information about selecting connection types for connecting an AI application to AI Security Admin Portal.

After you select the appropriate connection type on the [**Connect AI App**](https://help.zscaler.com/secure-ai-apps-infra/connecting-ai-app) page, enter the required details in the **Configure your connection**tab. This tab is specific to the selected connection type.

## Connection Types

The following integration methods are available between the AI Security and the AI application:

- API
- Platform
- LLM

REST API connection between your AI app and the AI Security.

- [REST API](https://help.zscaler.com/secure-ai-apps-infra/rest-api)
- [Proxy SDK](https://help.zscaler.com/secure-ai-apps-infra/proxy-sdk)
- [OpenAI REST API](https://help.zscaler.com/secure-ai-apps-infra/openai-rest-api)

Test runs are executed on chatbots that are accessible through external platforms (e.g., Slack, WhatsApp, Glean). Probe uses the platform’s APIs to interact with the chatbots.

- [Copilot Studio](https://help.zscaler.com/secure-ai-apps-infra/copilot-studio)
- [Glean](https://help.zscaler.com/secure-ai-apps-infra/glean)
- [Microsoft Teams](https://help.zscaler.com/secure-ai-apps-infra/microsoft-teams)
- Slack
- WhatsApp
- [Agentforce](https://help.zscaler.com/secure-ai-apps-infra/agentforce)
- [Amazon Bedrock AgentCore](https://help.zscaler.com/secure-ai-apps-infra/amazon-bedrock-agentcore)
- [Amazon Bedrock Agents](https://help.zscaler.com/secure-ai-apps-infra/amazon-bedrock-agents)

Tests are executed directly on the Large Language Model.

- Azure OpenAI
- Azure ML
- Anthropic
- Databricks
- Hugging Face
- OpenAI
- OpenAI Assistant
- Mistral
- Gemini
- Bedrock

## Testing the Connection

After you enter all the required information in the **Configure your connection** page, click **Continue**, a background connection test runs between the AI Security and the AI app. The test result appears in a dialog box.

- If the connection test succeeds, you can continue to [Configure your application](https://help.zscaler.com/secure-ai-apps-infra/configuring-application).
- If the connection test fails:
  - The error message indicates the possible reason of the failure.
  - Ensure that your AI App is accessible through the internet and not only on the private intranet.
  - Ensure that the AI Security's IP address is whitelisted.
  - Check if all necessary tags `({message}`, `{session_id})` are provided in the POST request payload.
  - Check that the response path format is correct.
  - Check that you entered the correct authentication information.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/configuring-custom-probe","lastmod":"2026-05-29T21:06Z","nid":"1540136"} -->
## Configuring a Custom Probe

- Source: https://help.zscaler.com/secure-ai-apps-infra/configuring-custom-probe
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Probe Settings > Configuring a Custom Probe
- Last modified: 2026-05-29T21:06Z
- Summary: Information about the probe and probe settings in the AI Security Admin Portal

Probe is the adversarial testing module in AI Security. It allows you to proactively evaluate your AI systems against real-world, domain-specific attack patterns. With Probe, you can configure, execute, and analyze simulated adversarial runs to discover vulnerabilities.

After connecting the AI app to the AI Security and selecting and configuring at least one of the available probes, you can start your first test run. These probes help identify potential vulnerabilities in the AI app. To learn more, see [Understanding Probes](https://help.zscaler.com/secure-ai-apps-infra/understanding-probes).

## Configuring a Custom Probe

To configure a probe, do the following:

1. Go to **AI infrastructure** > **AI Assets** > **Apps**.
2. Select any AI App from the table.
3. Click **Settings** > **Probe Settings**.
4. Click **+Create Probe**.
5. On the **Create Probe** page, select the probe type and configure the probe.
6. Click **Submit**. The custom probe is listed in the **Custom** page.

In the **Select Probe Type** section, update the following:

1. **Probe Name**: Enter a name for the probe.
2. **Probe Type**: Select from one of the following options:
  - Custom Probe
  - Custom Dataset
  - Q&A Probe

In the **Custom Probe** section, do the following:

1. **Risk Priority**: Select from **Low**, **Medium**, **High**, or **Critical**.
2. **Coverage**: Select from **Basic**, **Medium**, or **Extensive**.
3. **Probe Description**: Describe in detail what your custom probe should test for in your application.
4. **List of Allowed Behaviors**: Provide a list describing what your chatbot is allowed to do. Use short, affirmative statements (do not include “may do” in each bullet). For example, provide general product information or guide users through support steps without requesting sensitive credentials.
5. **List of Banned Behaviors**: Provide a list describing disallowed chatbot behaviors. List the behaviors directly (no need to add negations like “must not” or “do not” in each bullet). For example, request passwords or tokens. Provide abuse instructions.”, “Promise actions that require authorization.

In the **Custom Dataset**section, do the following:

1. **Upload Custom Dataset**: Upload a custom dataset in a CSV file of up to 5 MB. An example CSV file is available under the **Upload your custom dataset** field.
2. Click **Continue**.
3. **Risk Priority**: The weight assigned to each probe. It reflects the probe’s severity and likelihood of exploitation. Select from **Low**, **Medium**, **High**, or **Critical**.
4. **Probe Intent**: Describe the goal of the attacks for this probe and the purpose of the dataset (e.g., Test whether users can extract internal policies or configuration or coerce the bot into disallowed actions.
5. **Attack Specifications**: Select from one of the following options:
  - **On-Domain**: If the dataset is an on-domain (legitimate domain questions and edge cases).
  - **Adversarial**: If the dataset is adversarial, (jailbreaks, prompt injection, social engineering, policy evasion), and what behaviors you are targeting.
6. **Languages**: Select the languages in which the Platform should generate or execute variants to verify consistent behavior across locales.
7. **Attack Variations**: Define which transformations to apply. You can learn more, see the
8. **Attack Strategies**: Choose the adversarial strategies to apply. The available options are:
  - **One Shot with Retry**: For each prompt, a new conversation is created with no context from previous interactions.
  - **Multi-Shot**: Each prompt creates a new conversation within the test case, but Probe components have access to the attack history to improve future attacks.
  - **Delayed Attack**: One attack occurs within a single conversation. This strategy combines regular and adversarial prompts. Regular prompts prepare the target context, and adversarial prompts are executed later in the conversation.
  - **Tree of Attack**: This strategy uses a thought arrangement for red teamers, allowing them to discover weaknesses in their attacks and improve subsequent prompts.
9. **Depth**: Set how deep the probe goes in a conversation (i.e., number of turns or steps). Higher depth simulates more persistent and realistic attacks.
10. **Attack Multiplier**: Set how many attack variants to generate per dataset entry. Higher values increase coverage at the cost of more requests.
11. **Total Number of Attacks**:

In the **Q&A Probe**section, do the following:

1. **Risk Priority**: Select from **Low**, **Medium**, **High**, or **Critical**. Risk priority is the weight assigned to each probe. It reflects the probe’s severity and likelihood of exploitation.
2. **Upload Custom Dataset**: Upload your Q&A dataset in a CSV file of up to 5 MB. An example CSV file is available under the **Upload your custom dataset** field.
3. **Company Name**: Enter the name of the company that owns the AI application.

## Enabling a Probe

You can enable a predefined or a custom probe and configure it to tailor the probe to your application’s needs. This helps make the simulated attacks more domain-specific, improving their relevance and realism.

To enable a probe, do the following:

1. Go to **AI infrastructure** > **AI Assets** > **Apps**.
2. Select any AI App from the table.
3. Click **Settings** > **Probe Settings**.
4. Enable the toggle against the probe name. A **Predefined Probe Configuration**window appears.
5. Retain or modify the values as needed in the available fields.
6. Click the **Save and Enable Probe** button.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/connect-an-asset","lastmod":"2026-07-27T00:59Z","nid":"1540060"} -->
## Connect an Asset

- Source: https://help.zscaler.com/secure-ai-apps-infra/connect-an-asset
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Connect an Asset > Connect an Asset
- Last modified: 2026-07-27T00:59Z
- Summary: Information about how to connect and configure an AI asset for Red Teaming to the AI Security Admin Portal.

Connecting an asset is the first step in onboarding your AI Application. The target of this testing is the conversational generative AI application (for example, an internal chatbot or a public-facing customer engagement assistant). Automated testing helps identify potential vulnerabilities in the application. Testing includes a range of AI-generated attack scenarios that evaluate the application’s resilience and security. A selected set of probes runs these scenarios and performs specific security assessments.

The first step in onboarding an AI asset to AI Security is setting up the connection between them. With AI Security, you can observe how your asset performs across various layers, from the LLM to the platform level, which simulates real user interactions.

To connect an AI asset to AI Security, do the following:

1. Go to **AI Infrastructure** > **AI Assets.**
2. Click the **+Connect Asset** button. The **Connect Asset** window appears.
3. Select the **AI App for Red Teaming** option and click **Continue**. The **Connect AI App** page opens.
4. Connect to your application's endpoint to run the security tests. Connecting with an AI App consists of three steps:
  1. Select your connection type.
  2. [Configure your connection](https://help.zscaler.com/secure-ai-apps-infra/configuring-connection).
  3. [Configure your application](https://help.zscaler.com/secure-ai-apps-infra/configuring-application).
5. Click **Complete**to save your changes.

Enter the following details in the AI App Details & Environments:

- **AI App Name:** Enter a unique name to identify the AI application.
- **Business Unit:** Select the Business Unit that this AI application belongs to.
- **Network Type:** Select the network accessibility of the AI application:
  - Public
  - Private
- **Connection Type:** The following integration methods are available between the AI app and AI Security: The connection types supported for private network access types are: **API Connection:** [REST API](https://help.zscaler.com/secure-ai-apps-infra/rest-api), [OpenAI RestAPI](https://help.zscaler.com/secure-ai-apps-infra/openai-rest-api), and [Proxy SDK](https://help.zscaler.com/secure-ai-apps-infra/proxy-sdk); **Platform:** [Agentforce](https://help.zscaler.com/secure-ai-apps-infra/agentforce)
  - API
  - Platform
  - LLM

The AI application is isolated from the public internet with no ingress networking enabled.

**Private Network Access Type:**Select the method by which the Zscaler AI Red Teaming workers connect to your private AI application to perform Red Teaming tests:

- **Broker:** Select broker as the network access type to route traffic through a deployed broker to establish secure connectivity to the private AI application.
  - Select the broker to associate with this AI application from the drop-down menu. The broker facilitates secure communication between the AI Security Admin Portal and the private AI application. See image.
- **IP Whitelisting:** Restricts access to the AI application by allowing only specific, trusted IP addresses.
  - Add the listed IP addresses to the allowlist to enable connectivity. See image.

Public AI Apps are accessible over the public internet.

[Image: Select Public as the Network Type.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/copilot-studio","lastmod":"2026-08-14T18:13Z","nid":"1541104"} -->
## Copilot Studio

- Source: https://help.zscaler.com/secure-ai-apps-infra/copilot-studio
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Connect an Asset > Connections > Copilot Studio
- Last modified: 2026-08-14T18:13Z
- Summary: Integration Setup information for Copilot Studio is provided in this article.

After you select your [connection type](https://help.zscaler.com/secure-ai-apps-infra/connecting-ai-app), the **Configure your connection** page appears in the next step and prompts you to enter the required connection details.

The Copilot Studio integration supports two modes:

- **Non-Auth Mode:** A lightweight, easy-to-set-up option that doesn’t use tokens. It’s well-suited for straightforward agents that only need access to public information and resources. However, it can’t enable user-specific features such as reading email or accessing other protected data. This mode emphasizes simplicity.
- **Auth Mode:** Offers expanded, identity-based functionality for scenarios that require secure user sign-in via OAuth or SSO. It manages access and refresh tokens automatically, allowing the agent to interact securely and seamlessly with Microsoft APIs on the user’s behalf.

To configure Copilot Studio, do the following in the **Send Message** window:

1. Enter the required details, based on the authentication option you select:
  - Agent Secret
  - User
2. Click **Authorize**.

The non-auth mode is a simpler configuration flow, where no refresh token or authentication steps are needed. Enter the details as follows:

- **Agent Secret:** Enter a secure, unique key to authenticate the agent without requiring OAuth. Provide this value during agent setup to securely store it o prevent unauthorized access.
- **Direct Line Region:** The identifier for the geographic region where your Direct Line API is hosted. This ensures requests are routed to the appropriate Microsoft data center for processing. Select the region from the drop-down menu.

In the auth mode, enter the connection details as follows:

- **Client ID:** A unique identifier assigned to your Microsoft Azure application.
- **Tenant ID:** The identifier for your Microsoft Azure directory (tenant).
- **Environment ID:** Identifies the Power Platform environment where your agent lives.
- **Schema Name:** A unique identifier for an agent within a Dataverse environment.

## Obtaining Values

You can obtain the values for the following modes of operation for the MS Copilot Studio integration:

- Non-Auth Mode
- Auth Mode

The following are the steps to obtain values in the non-auth mode:

- 1. Disable Authentication
- 2. Obtain Agent Secret
- 3. Publish the Agent
- 4. Choose the correct Direct Line Region

1. On the **Copilot Studio** page, click on the Agent you want to test.
2. Go to **Settings > Security > Web channel security,** and set the **Require secured access** toggle to**On**. This ensures no unwanted third parties can invoke your Agent.
3. To turn off authentication, on the Agent page, click **Settings > Security > Authentication**.
4. In the **Authentication** options, choose **No authentication** and click **Save > Save**.

1. Go to **Settings > Security > Web channel security**.
2. Toggle on **Require secured access** to restrict invocations to authorized callers.
3. Copy one of the two Direct Line Secrets and paste it into the AI Security Admin Portal.

1. On the agent page, click **Publish > Publish**.
2. Wait a few minutes for the changes to take effect on the server side.

1. On the Agent page in **Copilot Studio**, identify the Environment name in the top right corner of the page.
2. Go to [Power Platform](https://admin.powerplatform.microsoft.com).
3. On the left-side navigation bar, click **Manage > Environments**.
4. Locate the row in the table which corresponds to the environment from the Copilot Studio page.
5. Look for the **Region** column
  - If it is Europe, set Direct Line Region in the AI Security Admin Portal to Europe.
  - If it is India, set Direct Line Region in the AI Security Admin Portal to India.
  - Otherwise, set Direct Line Region in the AI Security Admin Portal to Global.

The following are the steps to obtain values in the auth mode:

- 1. Create an Application Registration in Entra ID
- 2. Set up and Publish the Copilot Studio agent

1. Open the [Azure Portal](https://portal.azure.com) and go to **App registrations**.
2. Register an application
3. Click **New registration.**
4. Provide a name for the app.
5. Under **Supported account types**, choose **Accounts in this organization directory only**.
6. Under**Redirect URL**, choose **Single-page application (SPA)** as the platform. Set the URL to be: `https://copilot-studio-auth.splx.ai/integrations/copilot-studio-redirect` Origin is the URL which you see in the browser for the AI Security Admin Portal.
7. Open your newly created application.
  1. Search for your application under **App registrations > All applications**.
8. On the **Overview**page, copy and paste the following information into the AI Security Admin Portal:
  1. Application (client) ID as the Client ID,
  2. Directory (tenant) ID as the Tenant ID.
9. In the sidebar, click **Manage > API permissions**.
10. Click **Add a permission**.
11. Click on the tab **APIs my organization uses** and search for **Power Platform API.**
  1. If you do not see the **Power Platform API**, you must first enable it in your organization.
  2. To enable Power Platform API, first click on the **Cloud Shell** icon in the top right corner.
  3. Run the following command: `az ad sp create --id 8578e004-a5c6-46e7-913e-12f58912df43`
12. Choose **Delegated permissions** and search for the **CopilotStudio** section.
13. Select the checkbox next to **CopilotStudio.Copilots.Invoke** and click **Add permissions**.
14. On the API permissions page, under **Configured permissions**, click **Grant admin consent for {your org},**
15. When prompted to confirm, click **Yes**.

1. On the **Copilot Studio** page, click on the Agent you want to test
2. Set up Authentication
  1. On the Agent page, select **Settings > Security > Authentication**.
  2. In the **Authentication** options, choose **Authenticate with Microsoft** and click **Save**.
3. Copy and paste Agent metadata into the AI Security Admin Portal.
  1. On the Agent page, click **Settings > Advanced > Metadata**.
  2. Copy and paste the following information into the AI Security Admin Portal:
    1. Environment ID as the Environment ID.
    2. Schema name as the Schema Name.
4. Publish the Agent.
  1. On the Agent page, click on **Publish > Publish**.
  2. Wait a couple of minutes for the changes to take effect.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/creating-new-test-run","lastmod":"2026-05-29T21:06Z","nid":"1540186"} -->
## Creating a New Test Run

- Source: https://help.zscaler.com/secure-ai-apps-infra/creating-new-test-run
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Test Run > Creating a New Test Run
- Last modified: 2026-05-29T21:06Z
- Summary: Information about creating a new test run in the AI Red Teaming section of AI Security Admin Portal.

A Test Run is a group of executed probes performed at a specific point in time against your AI app in the Zscaler AI Security. In each test run, you can choose which vulnerabilities to test by selecting one or more pre-configured probes. To learn more, see [Understanding Probes](https://help.zscaler.com/secure-ai-apps-infra/understanding-probes).

## Prerequisites

Ensure the following prerequisites are met:

- Your AI app is [connected](https://help.zscaler.com/secure-ai-apps-infra/connecting-asset) to Zscaler AI Security.
- You have [configured probes](https://help.zscaler.com/secure-ai-apps-infra/configuring-probe).

## Creating a New Test Run

To create a new test run, do the following:

1. Go to **AI Infrastructure** > **AI Red Teaming** > **Overview**.
2. Click **+New Test Run**. A **New Test Run** window appears. The header shows the **Current Units**, the **Test Run Cost**, and the **Remaining Units.**Units are your organization’s consumption credits for running AI Security evaluations (eg, red teaming, test runs). When you start a test run, the platform deducts the required units from your available balance. If you don't have sufficient units remaining, contact your Zscaler Account team.
3. In the **New Test Run** window, do the following:
  - **Select Business Unit**: Select the Business Unit from the drop-down menu.
  - **Select AI App**: Select the AI app on which you want to perform the test run from the drop-down menu.
  - **Test Run Name**: Enter a name for your test run.
  - **Probe Selection**: Select probes from the available list of probes.
  - **Receive email notification**: Enable this option to receive email notifications. After the test run completes, the user who initiated it receives an email with the test run details and the generated PDF report attached.
  - **Schedule for Later**: Select to schedule a test run for later. To learn more, see [Scheduling Test Runs](https://help.zscaler.com/secure-ai-apps-infra/scheduling-test-run).
  - **Start Test Run**: Select to start the test run.

You can also create a new test run from **AI Infrastructure** > **AI Assets > Apps > Select any app > +New Test Run**. To learn more, see [Viewing AI App Details](https://help.zscaler.com/secure-ai-apps-infra/viewing-ai-app-details).

To view the test run results and perform actions on an already triggered Test Run, go to the [AI Red Teaming Overview](https://help.zscaler.com/secure-ai-apps-infra/about-ai-red-teaming-overview) page.

## Deleting a Test Run

To delete a test run, do the following:

1. Go to **AI Infrastructure** > **AI Red Teaming** > **Overview > Tested AI Assets**.
2. Click the name of the test run in the **Tested AI Assets** or **Untested AI Assets** tabs.
3. Select **Delete**from the Actions drop-down menu. The test run is deleted.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/glean","lastmod":"2026-08-10T22:59Z","nid":"1541107"} -->
## Glean

- Source: https://help.zscaler.com/secure-ai-apps-infra/glean
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Connect an Asset > Connections > Platform > Glean
- Last modified: 2026-08-10T22:59Z
- Summary: Integration Setup information for Glean is provided in this article.

After you select your [connection type](https://help.zscaler.com/secure-ai-apps-infra/connecting-ai-app), the **Configure your connection** page appears in the next step and prompts you to enter the required connection details.

Enter the required details based on the **Glean Type** you select in the **Send Message** window:

- Chat
- Agent

## Obtaining the Required Fields

Obtain the following fields:

- Instance
- Glean Client API Token
- Agent ID
- Application ID

To create an nstance:

1. Sign in to the [Glean Admin console](https://app.glean.com/admin/about-glean).
2. Go to **Admin > Settings**.
3. Locate the **Server instance (QE**).
4. Take the part before -be.glean.com (e.g., https://{your-glean-instance}-be.glean.com/...)

To create a Glean Client API Token:

1. Sign in to the [Glean Admin console](https://app.glean.com/admin/about-glean).
2. Go to **Platform** > **API Tokens**.
3. Select the **Client Tokens** tab.
4. Click **Add token**.
5. Choose the required scopes and set an expiry date and time.
6. Create the token, then copy and securely store the token secret.

To get an Agent ID:

1. Sign in to the [Glean Admin console](https://app.glean.com/admin/about-glean).
2. Click **Developers > Agents**.
3. Open your specific agent, and from the **Configuration** settings copy the Agent ID.

To get an Application ID:

1. Sign in to the [Glean Admin console](https://app.glean.com/admin/about-glean).
2. Click **Developers > Agents**.
3. Open your specific agent, and in the **Configuration** settings locate and copy the **Application ID** (if your org uses multiple apps).

This is the default chat type that connects users to the organization-wide Glean assistant. It provides generalized support for all users, leveraging Glean’s centralized resources. This type is ideal for scenarios where no specific agent or scoped application is required. Only the API Token and Instance Slug are necessary for configuration.

- **API Token:** Client API token with chat scope.
- **Instance:** The Glean instance slug (e.g., acme-prod from https://acme-prod-be.glean.com).
- **Application ID (optional):** Scope the chat to a specific application.

This chat type enables communication with a specific Glean Agent within your organization. It is suitable for scenarios where a targeted agent, and optionally a scoped application, is required to address a more specific use case. To configure this type, enter the following information:

- **API Token:** Client API token with chat scope.
- **Instance:** The Glean instance slug (e.g., acme-prod from https://acme-prod-be.glean.com).
- **Agent ID:** The agent you want to target.
- **Application ID (optional):** To restrict the interaction to a particular application.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/managing-ai-guard-log-exports","lastmod":"2026-08-11T10:04Z","nid":"1541825"} -->
## Managing AI Guard Log Exports

- Source: https://help.zscaler.com/secure-ai-apps-infra/managing-ai-guard-log-exports
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Configuration > General > Managing AI Guard Log Exports
- Last modified: 2026-08-11T10:04Z
- Summary: Learn to manage and configure third-party integrations to export incident data from AI Guard.

The AI Guard **Log Exports** page allows you to manage and configure third-party integrations to export incident data. You can do this through either Amazon Web Services (AWS), CrowdStrike (CRWD), AWS S3, or Splunk event exporting.

## Azure Data Explorer (ADX) Event Export

To add an ADX event export instance:

1. Under **Azure ADX Event Export**, click **Add Instance**. The **Add Integration** window appears. See image.
2. In the **Add Integration** window:
  - **Name**: Enter a name for your event export instance.
  - **Enabled**: Select this option to activate the event report functionality.
  - **Meta Event Hub Connection String**: Enter the SAS connection string for the meta Event Hub (e.g. `aiguard-meta`). Obtain via: `az eventhubs eventhub authorization-rule keys list`.
  - **Content Event Hub Connection String**: (Optional) Enter the SAS connection string for the content Event Hub (e.g. `aiguard-content`). Obtain via: `az eventhubs eventhub authorization-rule keys list`.
  - **Export Allowed/Detected Prompts**: Select to export allowed and detected prompts.
  - **Export Blocked Prompts**: Select to export blocked prompts.
  - **Export Tools Field**: Enabled by default. Disable if you want to remove the **Tools** field from your event log metadata export.
3. Click **Validate Connection** to check whether the information you entered is accurate and working.
4. Click **Save Integration**. The **Azure ADX Event Export Integrations** page opens. Your integration appears on this page.

## CRWD Event Export

Use the CRWD integration to export event metadata to CrowdStrike HTTP Event Collector (HEC) and event contents to AWS S3.

To add a CRWD event export instance:

1. Under **CRWD Event Export**, click **Add Instance**. The **Add Integration** window appears. See image.
2. In the **Add Integration** window:
  - **Name**: Enter a name for your event export instance.
  - **Enabled**: Select this option to activate the event report functionality.
  - **AWS S3 Bucket**: Enter the AWS S3 bucket location where the tenant's event content files will be stored.
  - **Bucket Key Prefix**: (Optional) Enter the key prefix for objects created in the S3 bucket.
  - **Tags**: (Optional) Click **Add Tag** to enter the tag key and value to apply to the object put in the S3 bucket. To use this feature, the IAM role requires permissions for the "s3:PutObjectTagging" action.
  - **Region of Bucket**: Enter the region of the bucket.
  - **IAM Cross-Account Role ARN**: Enter the Amazon Resource Name (ARN) of the IAM cross-account role created in the tenant's AWS account.
  - **IAM Cross-Account Role External ID**: The external ID of the IAM cross-account role which is created in the tenant's AWS account. It should be filled in automatically.
  - **CrowdStrike HEC Bearer Token**: Enter the bearer token to authenticate the CrowdStrike HEC.
  - **CrowdStrike HEC URL**: Enter the URL of the CrowdStrike HEC (raw endpoint) where tenant's events (metadata) will be posted.
  - **Export Allowed/Detected Prompts**: Select to export allowed and detected prompts.
  - **Export Blocked Prompts**: Select to export blocked prompts.
  - **Export Tools Field**: Enabled by default. Disable if you want to remove the **Tools** field from your event log metadata export.
3. Click **Validate Connection** to check whether the information you entered is accurate and working.
4. Click **Save Integration**. The **CRWD Event Export Integrations** page opens. Your integration appears on this page.

## S3 Event Export

Use the S3 integration to export event metadata and contents to AWS S3. A cloud formation template to set the right roles and policies can be found on the Zscaler AI Guard portal.

To add an S3 event export instance:

1. Under **S3 Event Export**, click **Add Instance**. The **Add Integration** window appears. See image.
2. In the **Add Integration** window:
  - **Name**: Enter a name for your event export instance.
  - **Enabled**: Select this option to activate the event report functionality.
  - **AWS S3 Metadata Bucket**: Enter the AWS S3 bucket location where the tenant's event metadata files will be stored.
  - **AWS S3 Content Bucket**: Enter the AWS S3 bucket where the tenant's event content files will be stored.
  - **Bucket Key Prefix**: (Optional) Enter the key prefix for objects created in the S3 bucket.
  - **Tags**: (Optional) Click **Add Tag** to enter the tag key and value to apply to the object put in the S3 bucket. To use this feature, the IAM role requires permissions for the "s3:PutObjectTagging" action.
  - **Region of Bucket**: Enter the region of the bucket.
  - **IAM Cross-Account Role ARN**: Enter the ARN of the IAM cross-account role created in the tenant's AWS account.
  - **IAM Cross-Account Role External ID**: The external ID of the IAM cross-account role created in the tenant's AWS account. This field auto-populates.
  - **Export Allowed/Detected Prompts**: Select to export allowed and detected prompts.
  - **Export Blocked Prompts**: Select to export blocked prompts.
  - **Export Tools Field**: Enabled by default. Disable if you want to remove the **Tools** field from your event log metadata export.
3. Click **Validate Connection** to check whether the information you entered is accurate and working.
4. Click **Save Integration**. The **S3 Event Export Integrations** page opens. Your integration appears on this page.

## Splunk Event Export

1. Under **Splunk Event Export**, click **Add Instance**. The **Add Integration** window appears. See image.
2. In the **Add Integration** window:
  1. **Name**: Enter a name for your event export instance.
  2. **Enabled**: Select this option to activate the event report functionality.
  3. **Splunk Metadata HEC Bearer Token**: Enter the bearer token used to authenticate Splunk HEC for the tenant's events (metadata).
  4. **Splunk Metadata HEC URL**: Enter the URL of the Splunk HEC (raw endpoint) where the tenant's events (metadata) will be posted.
  5. **Splunk Content HEC Bearer Token**: (Optional) Enter the bearer token used to authenticate to the Splunk HEC for tenant's events (content).
  6. **Splunk Content HEC URL**: (Optional) Enter the URL of the Splunk HEC (raw endpoint) where tenant's events (content) will be posted.
  7. **Export Allowed/Detected Prompts**: Select to export allowed and detected prompts.
  8. **Export Blocked Prompts**: Select to export blocked prompts.
  9. **Export Tools Field**: Enabled by default. Disable if you want to remove the **Tools** field from your event log metadata export.
3. Click **Validate Connection** to check whether the information you entered is accurate and working.
4. Click **Save Integration**. The **Splunk Export Integrations** page opens. Your integration appears on this page.

[Image: AI Guard Add Integration window for CRWD]

[Image: AI Guard Add Integration window for S3]

[Image: AI Guard Add Integration window for Splunk]

[Image: AI Guard Add Integration window for Azure ADX]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/managing-ai-guard-policy-control","lastmod":"2026-08-06T15:10Z","nid":"1542026"} -->
## Managing AI Guard Policy Control

- Source: https://help.zscaler.com/secure-ai-apps-infra/managing-ai-guard-policy-control
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Configuration > General > Managing AI Guard Policy Control
- Last modified: 2026-08-06T15:10Z
- Summary: Learn to create and manage AI Guard policy control.

After you create an AI Guard policy configuration, you can then attach it to an application by adding policy control. To learn more, see [Adding and Managing AI Guard Policies](https://help.zscaler.com/secure-ai-apps-infra/adding-and-managing-ai-guard-policy-configurations).

## Adding User Policy Control

To create user policy control:

1. In the left-side navigation, go to **AI Guard** > **Policy** > **Controls**. The **Policy Control** page appears. See image.
2. Click the **AI Applications** tab in Proxy mode. For DAS mode, skip to the next step.
3. Click **Add More** to open the **Create Policy Control** window. See image.
4. Enter the following required information:
  - **Rule Name**: Enter a name for the policy match.
  - **Policy Configuration**: From the drop-down menu, select a policy you previously created.
  - **Rule Order**: Enter a number for the order in which you want the policy match applied.
  - **Rule Status**: Select whether you want the rule to be enabled or disabled.
  - **Match Criteria**: At least one match criteria must be defined:
    - **LLM Provider & Models (Proxy Only)**: Match against specific LLM providers and models.
    - **Applications & Credentials**: Match against specific AI Applications and their credentials.
    - **Application Groups**: Match against specific AI Application Groups.
    - **Custom Request Headers**: Match against custom request headers and their values.
    - **Source IPs**: Match against specific source IPv4 addresses (e.g., `192.168.1.1`).
5. Click **Submit** to return to the **Policy Control** page. The new policy control appears at the **Rule Order** number you entered.

## Editing a Policy Control

To edit a policy control:

1. Click the **Users** tab.
2. In the **Action** column, click the **Edit** icon. The **Edit Policy Control** window opens. See image.
3. In the **Edit Policy Match** window, edit any policy match fields and click **Submit** when done. See image.

## Deleting a Policy Control

To delete a policy control:

1. Click the **Users** tab.
2. In the **Action** column, click the **Delete** icon. The **Delete Policy Control** window opens and asks if you would like to delete the policy match. See image.
3. In the **Delete Policy Control** window, click **OK**.

[Image: AI Guard Create Policy Control window]

[Image: AI Guard Policy Match page with an example policy match and Add More button visible.]

[Image: The Action column on the AI Guard main page with the edit icon highlighted]

[Image: AI Guard Edit Policy Match window with Policy and Rule Name blurred out]

[Image: The Action column on the AI Guard Main Page with Delete icon highlighted]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/managing-prompt-allowlist","lastmod":"2026-08-06T15:10Z","nid":"1541821"} -->
## Managing Prompt Allowlist

- Source: https://help.zscaler.com/secure-ai-apps-infra/managing-prompt-allowlist
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Configuration > General > Managing Prompt Allowlist
- Last modified: 2026-08-06T15:10Z
- Summary: Learn how to add blocked prompts to the AI Guard allowlist and manage the allowlist.

The prompt allowlist for AI Guard allows you to track and manage any prompts that you wish to allow that may otherwise be blocked by an existing AI Guard policy. Prompts sent to the allowlist endpoint are fully encrypted, so the prompt content remains protected end-to-end.

The prompt allowlist functionality is only available in Proxy mode.

## Configure AI Guard to Enable Allowlist

The following instructions explain what tenant settings you need to enable in AI Guard so that the prompt allowlist functionality is enabled.

1. From the AI Security Admin Portal left-side navigation, go to **AI Guard** > **Tenant Settings**.
2. On the **Tenant Settings** page, go to the **Security** tab.
3. Under **Security Settings**, enable **Store Prompts/Responses** and **Enable Data Sharing for AI Training**. See image.

Using the allowlist functionality requires the System Administrator role with all-write access. Individual actions are governed by the Default Access permission. The following steps will explain how to add those permissions with role-based acces control (RBAC).

1. From the AI Security Admin Portal left-side navigation, go to **AI Guard** > **RBAC Management**.
2. Click **Add Role** to create a new role with allowlist access, or click **Edit** to add access to an existing role. See image.
3. In the **Add** or **Edit Role** window, enable the following permissions as needed: See image.
  1. View allowlisted prompts: **Default Access: View**
  2. Add to allowlist: **Default Access: Create**
  3. Delete prompt from allowlist: **Default Access: Delete**
4. Click **Create** or **Save** when done.

To learn more about the Tenant Settings and RBAC Management pages, see [Managing Tenant Settings](https://help.zscaler.com/secure-ai-users/managing-tenant-settings) and [Managing Role-Based Access Control in AI Guard](https://help.zscaler.com/secure-ai-users/managing-role-based-access-control-ai-guard).

## Add Prompt to Allowlist

The following instructions explain how to add a blocked prompt to the allowlist.

1. From the AI Security Admin Portal left-side navigation, go to **AI Guard** > **Dashboard**.
2. Find the blocked prompt and click **Details**. The **Transaction Details** window opens. See image.
3. At the bottom of the Transaction Details window under Prompt Details, click **Reveal Prompt**.
4. If the blocked prompt is the one you want to add to the allowlist, click **Add to Allowlist**. See image.
5. In the **Add to Allowlist** window, confirm the prompt and click **OK**. See image.
6. From the AI Security Admin Portal left-side navigation, go to **AI Guard** > **Prompt Allowlist**.
7. On the **Prompt Allowlist** page, you can find the prompts that you have added. See image.

## Delete Prompt from Allowlist

The following instructions explain how to delete a prompt from the allowlist.

1. From the AI Security Admin Portal left-side navigation, go to **AI Guard** > **Prompt Allowlist**.
2. On the **Prompt Allowlist** page, find the prompt you want to delete and in the **Action** column, click **Delete**. See image.
3. In the **Remove from Allowlist** window, click **OK**. See image.

[Image: Prompt Details section of the Transaction Details page with annotations around Add to Allowlist]

[Image: AI Guard Dashboard page with annotations around Details.]

[Image: Add to Allowlist window]

[Image: Prompt Allowlist page]

[Image: Prompt Allowlist page with annotations around Delete.]

[Image: Remove from Allowlist window]

[Image: Tenant Settings page on Security tab with annotations around Store Prompts/Responses and Enable Data Sharing for AI Training]

[Image: RBAC page with annotations around Add Role and Edit]

[Image: RBAC Add Role window with annotations around the Default Access permissions View, Create, and Delete.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/managing-role-based-access-control-ai-guard","lastmod":"2026-08-06T15:08Z","nid":"1541721"} -->
## Managing Role-Based Access Control in AI Guard

- Source: https://help.zscaler.com/secure-ai-apps-infra/managing-role-based-access-control-ai-guard
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Configuration > General > Managing Role-Based Access Control in AI Guard
- Last modified: 2026-08-06T15:08Z
- Summary: Learn to use AI Guard's Role-based access control (RBAC). This includes the ability to start with a predefined role template, or creating custom roles with granular levels of permissions.

Role-Based Access Controls (RBAC) for AI Guard system users enables organizations to provide more granular control of administrative functions, such as the ability to create custom permissions to assign to system users.

RBAC applies to:

- System users provisioned/managed through Zscaler Authentication (Authentication Service).
- Local hosted system users in AI Guard.

Administrators managed through Authentication Service can leverage the new RBAC within AI Guard to assign these roles to other system users in Authentication Service.

## Role Permissions

AI Guard roles are created using a combination of permissions and scopes across the various sections of the admin console and object configurations. Please refer to the following table for a list of permissions and scopes for modules:

- List of Role Permissions

A majority of the permissions allow a role to be configured with a combination of one or multiple scopes per permission. For example:

- Customer Service Role:
  - Dashboard: **View**
  - Detections Policy: **View**
  - Events (View Prompts): **None** (unchecked)
  - Insights: **View**
  - All other permissions: **None** (unchecked)
- Auditor:
  - Audit Log: **View**
  - Event (View Prompts): **None** (unchecked)
  - All other permissions: **None** (unchecked)

## Creating a Role

This section describes how to create roles for managing system user permissions.

1. In the AI Security Admin Portal left-side navigation, go to **AI Guard** > **RBAC Management**.
2. Click **Add Role**. The **Add Role** window appears. See image.
3. In the **Add Role** window, fill out the following sections: See image.
  1. **Role Name**: Provide a role name, such as `Auditor`.
  2. **Start from a template**: (Optional) Click the drop-down menu to select from the following role templates:
    1. **Viewer**: Read-only access to all modules.
    2. **Editor**: Read, create, and update access. No delete access.
    3. **Administrator**: Full access to all modules.
  3. **Permissions**: Select the permissions for the role.
    - Selecting a module checkbox enables all permissions for that module.
    - Selecting an individual permission checkbox will enable that specific permission for that module.
  4. Click **Save**.
4. To edit a role, click the **Edit** button under the **Action** column of the role. In the **Edit Role** window, make any necessary changes and click **Save**.
5. To delete a role, click the **Delete** button under the **Action** column of the role. In the **Delete Role** window, click **Delete**.

## Assign a Role to a System User

This section will go into detail on how to assign a role to a system user in AI Guard.

1. In the AI Security Admin Portal left-side navigation, go to **AI Guard** > **System User Management**.
2. Select the **Edit** button under the **Action** column to add or edit a role for an existing system user, or you can click **Add More** to create a new system user. See image.
3. In the **Edit System User** or **Add System User** window, click the **Role** drop-down menu and select the role you want to assign.
4. Click **Update** or **Submit**.

To learn more about AI Guard System Users, see [Viewing AI Guard System Users](https://help.zscaler.com/secure-ai-users/viewing-ai-guard-system-users).

| Modules | Scope (Multiselect) |
| --- | --- |
| Default Access (applies permissions to all modules) | None \| View \| Create \| Edit \| Delete |
| **Overview** |  |
| Dashboard | None \| View |
| Insights | None \| View |
| Topology | None \| View |
| **Security** |  |
| Policies | None \| View \| Create \| Edit \| Delete |
| LLMs | None \| View \| Create \| Edit \| Delete |
| Private AI Apps | None \| View \| Create \| Edit \| Delete |
| **Operations** |  |
| Policy Testing | None \| View \| Create |
| Log Exports | None \| View \| Create \| Edit \| Delete |
| **Admin** |  |
| User Management | None \| View \| Create \| Edit \| Delete |
| RBAC Management | None \| View \| Create \| Edit \| Delete |
| Audit Log | None \| View |
| Tenant Settings | None \| View \| Edit |
| **Other** |  |
| API Clients | None \| View \| Delete |

[Image: The AI Guard Role-Based Access Control page showing example role entries]

[Image: The Add Role window showing Name, Template, Permissions, and Other settings to configure]

[Image: The AI Guard System Users page with annotations around the Add More button and the Edit button.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/managing-settings","lastmod":"2026-06-08T00:32Z","nid":"1540144"} -->
## Managing Settings

- Source: https://help.zscaler.com/secure-ai-apps-infra/managing-settings
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Managing Settings
- Last modified: 2026-06-08T00:32Z
- Summary: Information about managing AI App settings and connection settings in Red Teaming section of the AI Security Admin Portal.

You can modify or delete AI App Settings or Connection Settings from the **Settings** tab.

To modify an existing AI App or connection setting, do the following:

1. Go to **AI Infrastructure** > **AI Red Teaming** > **Overview**.
2. Select the AI App from the **Name & Business Unit** column.
3. On the detailed information page, select **Settings ()**icon.
4. Select any of the following options:
  - **AI App Settings**: To modify the AI App settings.
  - **Connection Settings**: To modify the connection settings.
  - **Probe Settings**: To modify probe settings. You can also create a new probe from this page. To learn more, see [Configuring Probes](https://help.zscaler.com/secure-ai-apps-infra/configuring-probe).
5. Edit the required fields.
6. Click **Update**.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/managing-tenant-settings","lastmod":"2026-08-11T10:34Z","nid":"1541820"} -->
## Managing Tenant Settings

- Source: https://help.zscaler.com/secure-ai-apps-infra/managing-tenant-settings
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Configuration > General > Managing Tenant Settings
- Last modified: 2026-08-11T10:34Z
- Summary: Learn how to manage the following AI Guard tenant settings: Network Access Control Policy, Custom Request Headers, Security Settings, and Syncing ZIA End Users and Groups.

From the AI Guard Tenant Settings page, you can view information and make additional customizations to your AI Guard tenant. In addition to basic tenant information, you can also configure your network access control policy to allow IPv4 CIDR ranges, add custom request headers, and make changes to your security and encryption settings.

On the AI Guard **Tenant Settings** page (AI Guard > Tenant Settings), you can view the following basic information:

- **Name**: Name of the tenant.
- **Mode**: Lists whether AI Guard is in **Proxy** or **DaaS** mode.
- **UUID**: Universally unique identifier for your tenant.
- **Zscaler AWS Account ID**: Displays Zscaler's Account ID. This is used for optional AWS integrations such as log exports to AWS S3 buckets or optional AWS customer-managed keys for encryption.

See image.

## Managing Network Access Control Policy

To manage your network access control policy:

1. In the left-side navigation, click **Tenant Settings**. The **Tenant Settings** page appears.
2. Under the **Network Access Control Policy**, enter up to 10 IPv4 CIDR ranges. After entering one, press `Enter`, `Comma`, `Space`, or paste a list to add more. See image.
3. Click **Submit**.
4. To delete an IPv4 CIDR range, click the **Delete** icon next to it. See image.

## Managing Custom Request Headers

Custom request headers must follow the following guidelines:

- A maximum of 5 custom request headers is allowed.
- Header names must not exceed 64 characters.
- You can use the following characters: a-z A-Z 0-9 !#$%&'*+-.^_`|~
- Header names are case insensitive and must be unique.
- A conversation ID header is optional.

To manage your custom request headers:

1. In the left-side navigation, click **Tenant Settings**. The **Tenant Settings** page appears.
2. Under **Custom Request Headers**, enter a **Header Name** and click **Add Header**. See image.
3. (Optional) Under **Conversation ID Header**, you can select a header from the drop-down menu to use as the conversation ID header. This header will be used to track related requests.
4. Click **Submit** and in the **Update Custom Request Headers** window, click **Yes**.
5. In addition to adding headers you can also: See image.
  - **Mark as sensitive**: This allows for extra protection of headers that may contain sensitive information. Select the **Mark as sensitive** checkbox. If you selected a custom header as a **Conversation ID Header**, then you will not be able to mark it as sensitive until you remove it.
  - **Edit**: To make changes to a custom header, click the **Edit** icon next to the header, make the required changes, and click the **Save** icon.
  - **Delete**: To delete a custom header, click the **Delete** icon and in the **Delete this Header?** window click **Yes**.
6. After you make all changes, click **Submit**. In the **Update Custom Request Headers** window, click **Yes**.

## Managing Security Settings

In the **Security Settings** section, you have the following functionality:

- **Store Prompts/Responses**: Keep a history of all prompts and responses for the last 90 days for auditing.
- **Enable Event Detection Feedback**: Enable feedback submission on detection events. The feedback might be used for local model training. It will be securely stored with encryption.
- **Enable Custom Header Encryption**: Encrypt the contents of sensitive custom headers.
- **Enable Content Encryption**: Use customer-managed keys for additional security.

See image.

If you enable any encryption, you must provide a customer-managed key. Under **Encryption - Configure Customer Managed Key**:

- **KMS Provider Key**: **AWS** is currently the only supported provider type.
- **Key ARN**: Enter your Amazon Resource Name (ARN) encryption key.

See image.

After entering your encryption key, click **Save Changes**. Click **Reset** to revert any changes to the customer key ID and disable both content and custom header encryption settings.

## Custom Block Message for Consumer GenAI Apps

When someone is using AI Guard, you can include a custom message that appears when their prompt or response is blocked. This custom block message can be used to provide a link to the user to notify them about AI usage policy of your organization.

See image.

- **Custom Prompt Block Message**: Enter a message that users will see when their prompt is blocked.
- **Custom Response Block Message**: Enter a message that users will see when their LLM response is blocked.
- **Delete Conversation on Response Block**: Select if you want to automatically delete chat history of response blocking cases for providers.

After entering the block message, click **Save**.

## Organization Settings

AI Guard's tenancy restriction feature allows you to restrict user traffic access either to personal accounts, enterprise accounts, or both, and apply different access-control rules to each. This feature is currently available for Anthropic and OpenAI.

User traffic is automatically tagged at runtime. Requests matching a registered ID are tagged as "Enterprise," while unmatched requests are classified as "Personal."

To enable this functionality:

1. In the left-side navigation, click **Tenant Settings**. The **Tenant Settings** page appears.
2. Go to the **Security** tab and find the **Organisation Restrictions** section. See image.
3. Choose to enable the toggle for **Anthropic** or **OpenAI**.
4. Under the LLM providers you enabled, enter your organization's specific Anthropic Org IDs or OpenAI Workspace IDs.
5. Click **Save Restrictions**.

After enabling this functionality, within Policy Controls, administrators can select an "Account Type" (Personal, Enterprise, or Both) as a match criterion. This allows for specific policies (such as stricter data loss prevention or topic detection rules) to be applied to unmanaged personal traffic, while maintaining different standards for company-sanctioned workspaces.

To learn more, see [Managing AI Guard Policy Control](https://help.zscaler.com/secure-ai-apps-infra/managing-ai-guard-policy-control).

## Red Teaming

To enable AI Red Teaming after integrating it with AI Guard:

1. In the left-side navigation, click **Tenant Settings**. The **Tenant Settings** page appears.
2. Go to the **Integrations** tab and find the **Red Teaming** section. See image.
3. Enable the toggle next to **Enable Red Teaming Integration**.

## Anthropic Webhook

AI Guard can integrate with Anthropic Claude's inference hooks to inspect and evaluate prompts against a tenant's detection policy in real time. AI Guard responds with an "allow" or "deny" verdict, which determines whether Claude proceeds to generate a response or the prompt is blocked.

To set up an inference hook between AI Guard and Claude:

1. In the **Claude UI**, go to **Organization Settings** > **Data and Privacy** > **Inference Hooks**.
2. Under **Inference hooks**, enable **Allow for your organization**. See image.
3. In the **Inference hooks** section, do the following: See image.
  1. **Enforce verdicts**: Enable.
  2. **Inference hooks endpoint**: Set to `https://api.zseclipse.net/v1/webhook/execute`
  3. **Prompt verdict timeout (ms)**: 1000ms
  4. **Signing secret**: Copy this secret for use in AI Guard later. A signing secret is generated automatically on the first save and can be rotated at any time.
  5. **(Optional) Custom blocked prompt message**: Enter a custom block message to show users when a prompt is blocked.
4. Leave Claude and go to the **AI Security Admin Portal** > **AI Guard** > **Tenant Settings** > **Integrations** tab.
5. In the **Anthropic Webhook** section, click **Add Secret**. See image.
6. In the **Add Webhook Signing Secret** window, do the following: See image.
  1. **Anthropic Org UUID**: Enter your organization’s Anthropic UUID.
  2. **Signing Secret**: Enter the signing secret you copied earlier.
  3. Click **Add Secret**.

With the webhook integrated, create a policy to detect webhook traffic and create a policy control to set the match criteria so the policy applies to the traffic you intend. To learn more, see [Adding and Managing AI Guard Policy Configurations](https://help.zscaler.com/secure-ai-apps-infra/adding-and-managing-ai-guard-policy-configurations) and [Managing AI Guard Policy Control](https://help.zscaler.com/secure-ai-apps-infra/managing-ai-guard-policy-control).

[Image: Anthropic Claude UI showing Inference hooks section]

[Image: Anthropic Claude UI showing Inference hooks section][Image: Anthropic Claude UI showing Inference hooks section]

[Image: AI Guard, Tenant Settings, Integration Tab, Anthropic Webhook section]

[Image: Add Webhook Signing Secret window]

[Image: Tenant Settings, Security tab, Organisation Settings with example entries added]

[Image: AI Guard Red Teaming Integration setting]

[Image: AI Guard Tenant Settings Basic Information which includes Name, Mode, UUID, and ZIdentity Enabled.]

[Image: AI Guard Tenant Settings Network Access Control Policy with example CIDR value entered]

[Image: AI Guard Allowed IPv4 CIDR Ranges field with the Delete icon highlighted]

[Image: AI Guard Tenant Settings Custom Request Headers section with example headers entered]

[Image: AI Guard Custom Header settings showing Name and Actions columns including mark as sensitive, Edit, and Delete buttons]

[Image: AI Guard Tenant Settings Security Settings]

[Image: AI Guard Tenant Settings showing the Encryption - Configure Customer Managed Key section]

[Image: AI Guard Tenant Settings page with the Custom Block Message section displayed]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/microsoft-teams","lastmod":"2026-08-16T00:13Z","nid":"1542864"} -->
## Microsoft Teams

- Source: https://help.zscaler.com/secure-ai-apps-infra/microsoft-teams
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Connect an Asset > Connections > Microsoft Teams
- Last modified: 2026-08-16T00:13Z
- Summary: Integration Setup information for Microsoft Teams is provided in this article

After you select your [connection type](https://help.zscaler.com/secure-ai-apps-infra/connecting-ai-app), the **Configure your connection** page appears in the next step and prompts you to enter the required connection details.

Microsoft Teams chatbots are Azure bots connected to Microsoft Teams. Testing is performed directly on the Azure bot, as it contains all the functionalities of the bot within Microsoft Teams. Azure Bot integration uses the [Direct Line API](https://learn.microsoft.com/en-us/azure/bot-service/rest-api/bot-framework-rest-direct-line-3-0-concepts?view=azure-bot-service-4.0).

To create the integration, enter the **Bot ID** and **Bot Secret** from the Bot Framework. To retrieve these, go to your bot in the [Microsoft Bot Framework](https://dev.botframework.com/bots).

IMAGE PLACEHOLDER: Figure 1 &ndash; Microsoft Teams Connection Example

- Bot ID
- Bot Secret

To obtain the Bot ID:

1. Go to **My bots** in the [Microsoft Bot Framework](https://dev.botframework.com/bots) and select your bot. [Image: This is the My bots page]
2. Click **Settings**. [Image: microsoft_bot_page]
3. Copy the **Bot handle** value and enter it in the **Bot ID** field. [Image: microsoft_bot_handle]

IMAGE PLACEHOLDER: Figure 4 &ndash; Microsoft Bot Handle

To obtain the Bot Secret:

1. On the bot page, click **Edit** in the **Direct Line** row. [Image: microsoft_bot_edit]
2. Copy the **Secret key** and enter it in the **Bot Secret** field. [Image: microsoft_bot_secret_key]

After you enter both fields, click **Continue** to test your connection and proceed.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/openai-rest-api","lastmod":"2026-08-10T19:44Z","nid":"1540734"} -->
## OpenAI REST API

- Source: https://help.zscaler.com/secure-ai-apps-infra/openai-rest-api
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Connect an Asset > Connections > API > OpenAI REST API
- Last modified: 2026-08-10T19:44Z
- Summary: Integration Setup information for OpenAI Rest API is provided in this article.

After you select your [connection type](https://help.zscaler.com/secure-ai-apps-infra/connecting-ai-app), the **Configure your connection** page appears in the next step and prompts you to enter the required connection details, as follows:

- **API Type**:
  - **Chat Completions API**: The traditional industry-standard protocol (eg, v1, chat, completions). It operates on a message-based array system and requires manual context management. While robust and widely used, it is now considered a legacy path and may not receive the latest agentic features or reasoning optimizations.
  - **Responses API**: The modern, unified protocol designed to supersede Chat Completions. It offers better performance with reasoning models (like GPT-5), native agentic loops for multi-tool calling, lower costs through improved cache utilization, and built-in stateful context management.
- **URL**: The AI app's endpoint to which the attack messages are sent.
- **API Key**: The authentication key for your AI app (if applicable).
- **System Prompt**: Your application’s system prompt. It sets the initial instructions or context for the AI model, defines the behavior, tone, and specific guidelines the AI should follow while interacting. For best practices, refer to the [OpenAI documentation on prompt engineering](https://platform.openai.com/docs/guides/prompt-engineering).
- **Model**: The exact model name you want to use. As this connector is not tied to a single model provider, you should look up the correct identifier on your chosen provider’s website.
- **Authentication Header**: Select **+ Add Header** button for HTTP header customization. Custom HTTP headers are optional but may be required by the AI App for additional security, tracking, or configuration purposes. Enter the key–value pairs required for API requests to the AI App.
- **Extra LLM Config**: Any request-body LLM configuration parameters the UI does not expose explicitly, such as temperature, top_p, or max_tokens. Leave the field blank to accept provider defaults. You can check all the optional fields in the [OpenAI cookbook](https://cookbook.openai.com/examples/how_to_format_inputs_to_chatgpt_models?utm_source=chatgpt.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/proxy-sdk","lastmod":"2026-08-10T19:43Z","nid":"1540722"} -->
## Proxy SDK

- Source: https://help.zscaler.com/secure-ai-apps-infra/proxy-sdk
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Connect an Asset > Connections > API > Proxy SDK
- Last modified: 2026-08-10T19:43Z
- Summary: Integration Setup information for PROXY SDK is provided in this article.

After you select your [connection type](https://help.zscaler.com/secure-ai-apps-infra/connecting-ai-app), the **Configure your connection** page appears in the next step and prompts you to enter the required connection details, as follows:

- **URL**: This is your target endpoint to which the attack messages will be sent.
- **API Key**: The API Key for your application, used to ensure successful authentication with the Proxy SDK.
- **Additional Parameters Payload**: This section allows you to define custom payload data for POST requests sent by the Proxy SDK to the AI app. The payload is described as a JSON object.
- **Authentication Header**: Select **+ Add Header** button for HTTP header customization. Custom HTTP headers are optional but may be required by the target for additional security, tracking, or configuration purposes.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/quick-start-guide-dasapi-mode","lastmod":"2026-08-12T09:56Z","nid":"1542215"} -->
## Quick Start Guide for DAS/API Mode

- Source: https://help.zscaler.com/secure-ai-apps-infra/quick-start-guide-dasapi-mode
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Getting Started > Quick Start Guide for DAS/API Mode
- Last modified: 2026-08-12T09:56Z
- Summary: This guide takes you through the configuration steps you need to set up AI Guard in DAS/API mode, add an AI application and credentials, and set up the policies necessary to provide run-time protection for your AI applications.

This guide takes you through the configuration steps you need to set up AI Guard in DAS/API mode, add your AI applications, generate API keys, and set up the policies necessary to provide run-time protection for your AI applications. To aid with explaining the configuration, this guide also assumes a fictitious internal application (Travel App).

## Prerequisites

Before you can configure AI Guard, ensure that you

- Have an AI Guard subscription.
- Have the ability to modify application code.

In addition, Zscaler recommends reading the following articles:

- [What Is AI Guard?](https://help.zscaler.com/secure-ai-apps-infra/what-ai-guard)
- [About the AI Guard Dashboard](https://help.zscaler.com/secure-ai-apps-infra/about-ai-guard-dashboard)
- [About AI Guard Insights](https://help.zscaler.com/secure-ai-apps-infra/about-ai-guard-insights)
- [About AI Guard Usage](https://help.zscaler.com/secure-ai-apps-infra/about-ai-guard-usage)

## Configuring AI Guard in DAS/API Mode

To configure AI Guard in DAS/API mode, complete the following steps:

- Step 1: Configuring AI Guard
- Step 2: Configuring AI Application
- Step 3: Configure Policy
- Step 4: Testing and Validating

This section will cover the baseline for configuring AI Guard for DAS/API mode.

1. In the AI Security Admin Portal left navigation menu, go to **AI Guard** > **Tenant Settings** > **Security**.
2. In the top right of the page, set the **Mode** to **DAS**.
3. In the **Security** tab, enable **Store Prompts/Responses** to be able to view the data in AI Guard, otherwise all Prompt/Response fields will show No Data. See image.
4. After enabling **Store Prompts/Responses**, the **Setting Store Prompts/Responses configuration** window appears. Read the information in the window and click **Yes**. See image.

To add an AI Application to AI Guard for DAS/API mode:

1. In the AI Security Admin Portal, go to **AI Guard** > **AI Applications**. The **AI Applications** page appears. See image.
2. Click **Add More** to open the **Create Application** window. See image.
3. Enter the following information:
  1. **Name**: `Travel App`
  2. **(Optional) Owner Email**: Enter the owner email address for the application.
  3. **Store Events Content**: Enabled.
  4. **Encrypt Events Content**: Leave disabled.
4. Click **Create** to return to the **AI Application** page.
5. Go to the **Add API Keys** tab. See image.
6. Click **Add More**.
7. Enter the following information:
  1. **Name**: `Travel App`
  2. **Application**: Select the AI application you previously created from the drop-down menu.
  3. **Expires At**: (Optional) Enter an expiration date for the credentials.
8. Click **Create** and the **Save your key** window appears. See image.
9. Save your API key which can be shared with your application developers. For security reasons, you will not be able to view it again after closing the window. After copying and saving it, click **Done**.

### Create Policy

To add a new policy for your AI app in AI Guard:

1. In the AI Security Admin Portal left-side navigation, go to: **AI Guard** > **Policy >** **Configurations**. See image.
2. Click **Add More** to open the **Add New Configuration** page.
3. Under **Basic Information**, enter: See image.
  1. **Policy Name**: `Travel App Policy Configuration`
  2. (Optional) **Description**
4. Click **Continue to Detectors**. The **Prompt Detectors** tab opens.
5. On the **Prompt Detectors** tab, click on the **Toxicity** detector, set the **Action** to **Block**, and then click on **Save Changes**. See image.
6. Click Next. Skip the configuration of response detectors. Click **Next** again. The **Review** tab opens.
7. The **Review** tab shows you a summary of the policy configuration you created. Click **Submit Policy** if everything looks correct.
8. Note down the **Policy ID** after creating the policy.

### Testing DAS/API Mode

The following sample Python code demonstrates how to make the API call to AI Guard for prompts. Note that it does not send the response from the LLM provider to AI Guard for evaluation. It assumes two things:

1. The httpx package has been installed.
2. The API key created under AI Applications is stored under an environment variable called `AIG_DAS_API_KEY`.

In the following, note the `direction` key-value pair. The value of `IN` refers to prompt and the value of `OUT` refers to response. Keep in mind that in DAS mode, AI Guard can only make recommendations to the application, but it cannot perform enforcement actions. Your application must check for the key-value pair of `action: ALLOW` or `action: BLOCK` in the response from AI Guard.

```
import httpx
import json
import os

das_api_key = os.getenv('AIG_DAS_API_KEY')

aig_das_url = "https://api.zseclipse.net/v1/detection/execute-policy"

policy_id = <policy_id> # Retrieve policy ID from the UI e.g. 3712

prompt_string = "which airlines fly to San Diego"

das_header = {
    "Authorization" : f"Bearer {das_api_key}”
}

das_payload = {
    "direction" : "IN",
    "policyId" : policy_id,
    "content" : prompt_string
}

resp = httpx.request("POST", aig_das_url, headers=das_header, 
                         json=das_payload)

print(json.dumps(resp.json(), indent=2))
```

#### Allowed Response

```
{
  "transactionId": "3bfb70cd-3947-4937-9da9-8eff39d33385",
  "statusCode": 200,
  "detectorErrorCount": 0,
  "action": "ALLOW",
  "direction": "IN",
  "detectorResponses": {
    "toxicity": {
      "statusCode": 200,
      "triggered": false,
      "action": "ALLOW",
      "latency": 43,
      "deviceType": "cuda",
      "details": {
        "topLabel": null,
        "topScore": 0.0,
        "topScoreByLabel": {
          "LABEL_1": 0.0
        }
      },
      "contentHash": {
        "hashType": "md5",
        "hashValue": "b68a77dbab9544978609bf3f2f13199c"
      },
      "severity": "LOW"
    }
  },
  "maskedContent": "which airlines fly to San Diego",
  "sendToApplication": false
}
```

#### Blocked Response

```
{
  "transactionId": "15eaf15a-fea2-43c0-af49-cfa9cc9e40d9",
  "statusCode": 200,
  "detectorErrorCount": 0,
  "action": "BLOCK",
  "severity": "LOW",
  "direction": "IN",
  "detectorResponses": {
    "toxicity": {
      "statusCode": 200,
      "triggered": true,
      "action": "BLOCK",
      "latency": 43,
      "deviceType": "cuda",
      "details": {
        "topLabel": "LABEL_1",
        "topScore": 1.0,
        "topScoreByLabel": {
          "LABEL_1": 1.0
        }
      },
      "contentHash": {
        "hashType": "md5",
        "hashValue": "45b1719591fe117a5bebad17f8db3cc8"
      },
      "severity": "LOW"
    }
  },
  "maskedContent": "how to create a hoax for an airline",
  "sendToApplication": false
}
```

### Validating Activity

From the AI Guard Dashboard page (AI Guard > Dashboard), you can validate your AI application activity.

After your application has performed several transactions after being configured with AI Guard, these transactions will now appear on the Dashboard page:

1. Select your desired date range and filters. See image.
2. Under the date range and filters, you can view the number of Apps, Detections, and Transactions AI Guard managed in that time.
3. Select **Transactions** or **Conversation**s to view dashboard entries as individual prompt transactions, or view multi-prompt AI interactions as a single, connected conversation thread. See image.
4. The dashboard table contains information on each transaction for you to view.
5. Click the **Details** icon to open a window showing more detailed information about that specific transaction. See image.

[Image: Setting Store Prompts/Responses configuration window]

[Image: Dashboard date and information]

[Image: Dashboard Transactions Conversations setting]

[Image: Transactions Details page after clicking the Details icon]

[Image: AI Guard Policies page with an example policy visible]

[Image: Basic policy information fields which includes Policy Name and Description]

[Image: Configuring detector window showing the common options available.]

[Image: AI Guard Tenant Settings, Security Settings]

[Image: AI Guard AI Applications page showing App Name, Last Updated, Owner Email, Store Contents, Encrypt Contents, and Action]

[Image: Create Application window]

[Image: AI Application Identity Broker page]

[Image: Save your key window, you need to copy the key before closing the window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/quick-start-guide-proxy-mode","lastmod":"2026-08-12T09:57Z","nid":"1542208"} -->
## Quick Start Guide for Proxy Mode

- Source: https://help.zscaler.com/secure-ai-apps-infra/quick-start-guide-proxy-mode
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Getting Started > Quick Start Guide for Proxy Mode
- Last modified: 2026-08-12T09:57Z
- Summary: This guide takes you through the configuration steps you need to set up AI Guard in Proxy mode, add an AI application and credentials, and set up the policies necessary to provide run-time protection for your AI applications.

This guide takes you through the configuration steps you need to set up AI Guard in Proxy mode, add your AI applications, generate API keys, and set up the policies necessary to provide run-time protection for your AI applications. This guide is geared towards Claude using their public API endpoint as an example, but can be adapted for other LLM providers. To aid with explaining the configuration, this guide also assumes a fictitious internal application (Travel App) that makes API calls to Claude.

## Prerequisites

Before you can configure AI Guard, ensure that you

- Have an AI Guard subscription.
- Have the ability to modify application code.
- Have the API key from your LLM provider.

In addition, Zscaler recommends reading the following articles:

- [What Is AI Guard?](https://help.zscaler.com/secure-ai-apps-infra/what-ai-guard)
- [About the AI Guard Dashboard](https://help.zscaler.com/secure-ai-apps-infra/about-ai-guard-dashboard)
- [About AI Guard Insights](https://help.zscaler.com/secure-ai-apps-infra/about-ai-guard-insights)
- [About AI Guard Usage](https://help.zscaler.com/secure-ai-apps-infra/about-ai-guard-usage)

## Configuring AI Guard in Proxy Mode

To configure AI Guard in Proxy mode, complete the following steps:

- Step 1: Configuring AI Guard
- Step 2: Configuring LLM Credentials
- Step 3: Configuring AI Application and Identity Broker
- Step 4: Configure Policy and Policy Control
- Step 5: Testing and Validating

This section will cover the baseline for configuring AI Guard for Proxy mode.

1. In the AI Security Admin Portal left navigation menu, go to **AI Guard** > **Tenant Settings** > **Security**.
2. In the top right of the page, set the **Mode** to **Proxy**.
3. In the **Security** tab, enable **Store Prompts/Responses** to be able to view the data in AI Guard, otherwise all Prompt/Response fields will show No Data. See image.
4. After enabling **Store Prompts/Responses**, the **Setting Store Prompts/Responses configuration** window appears. Read the information in the window and click **Yes**. See image.
5. Leave all other settings disabled under **Security Settings**.

1. Go to **AI Security Admin Portal** > **AI Guard** > **LLMs** > **Credentials**. See image.
2. Click **Add More**. The **Add LLM Credentials** window opens.
3. Enter the following: See image.
  1. **Name**: `Anthropic credentials`
  2. **LLM Provider**: Default Anthropic Provider
  3. **(Optional) Expires At**: You can add an expiration date to your LLM provider credentials.
  4. **API Key**: Enter the API key for your LLM provider.
4. Click **Submit**.

### Add AI Application

To add an AI Application to AI Guard for Proxy mode:

1. In the AI Security Admin Portal, go to **AI Guard** > **AI Applications**. The **AI Applications** page appears. See image.
2. Click **Add More** to open the **Create Application** window. See image.
3. Enter the following information:
  1. **Name**: `Travel App`
  2. **(Optional) Owner Email**: Enter the owner email address for the application.
  3. **Store Events Content**: Enable.
  4. **Encrypt Events Content**: Leave disabled.
4. Click **Create** to return to the **AI Application** page.

### Add Application Identity Broker

Link your app to your LLM provider by creating an identity broker.

1. Go to **AI Security Admin Portal** > **AI Guard** > **AI Applications** > **Application Identity Brokers**. See image.
2. Click **Add More** to open the **Add Application Identity Broker** window. See image.
3. Enter the following information:
  1. **Name**: `Travel App-Anthropic`
  2. **Application**: `Travel App`
  3. **Expires At**: (Optional) Enter an expiration date for the credentials.
  4. **LLM Provider**: `Default Anthropic Provider`
  5. **LLM Provider Credentials**: `Anthropic credentials`
4. Click **Create** and the **Save your key** window appears. See image.
5. Save your API key which can be shared with your application developers. For security reasons, you will not be able to view it again after closing the window. After copying and saving it, click **Done**.

### Create Policy

To add a new policy for your AI app in AI Guard:

1. In the AI Security Admin Portal left-side navigation, go to: **AI Guard** > **Policy >** **Configurations**. See image.
2. Click **Add More** to open the **Add New Configuration** page.
3. Under **Basic Information**, enter: See image.
  1. **Policy Name**: `Travel App Policy Configuration`
  2. (Optional) **Description**
4. Click **Continue to Detectors**. The **Prompt Detectors** tab opens.
5. On the **Prompt Detectors** tab, click on the **Toxicity** detector, set the **Action** to **Block**, and then click on **Save Changes**. See image.
6. Click Next. Skip the configuration of response detectors. Click **Next** again. The **Review** tab opens.
7. The **Review** tab shows you a summary of the policy configuration you created. Click **Submit Policy** if everything looks correct.

### Create Policy Control

After you create an AI Guard policy configuration, you can then apply it to your AI app by adding policy control. To create an AI application policy control:

1. In the AI Security Admin Portal left-side navigation, go to **AI Guard** > **Policy** > **Controls**. The **Policy Control** page appears. See image.
2. Click on the **AI Applications** tab.
3. Click **Add More** to open the **Create Policy Match** window. See image.
4. Enter the following required information:
  - **Rule Name**: `Travel App Policy Control`
  - **Policy Configuration**: `Travel App Policy Configuration`
  - **Rule Order**: Do not modify.
  - **Rule Status**: Enabled.
  - **Match Criteria**: Click **Add More**:
    - **Applications & Credentials**: Select `Travel App`.
5. Click **Submit** to return to the **Policy Control** page.

### Testing Proxy Mode

The following sample Python code demonstrates how to make the API call to AI Guard. It assumes two things:

1. The httpx package has been installed.
2. The API key created under Application Identity Broker is stored under an environment variable called `AIG_PROXY_API_KEY`.

Two key changes of note: the first is the URL. Instead of connecting directly to Claude, the application now makes the API calls to AI Guard. Second, authentication is done via the `X-ApiKey` header. The payload itself remains the same.

The first test sends a prompt that should not trigger the Toxicity detector. To test the Toxicity detector, change the prompt string to: `how do I create a hoax for an airline`.

Code block:

```
import httpx
import os

proxy_api_key = os.getenv('AIG_PROXY_API_KEY')

# claude_native_url = "https://api.anthropic.com/v1/messages"
claude_proxy_url = "https://proxy.zseclipse.net/v1/messages" 

prompt_string = "which airlines fly to San Diego"

claude_proxy_header = {
    "X-ApiKey" : proxy_api_key,
    "anthropic-version" : "2023-06-01"
}

claude_payload = {
    "model" : "claude-opus-4-5-20251101",
    "messages": [{"role" : "user",
                 "content": prompt_string}
                 ],
    "max_tokens" : 1024
}

resp = httpx.request("POST", claude_proxy_url, headers=claude_proxy_header, 
                         json=claude_payload)

print(resp.json())
```

### Validating Activity

From the AI Guard Dashboard page (**AI Guard** > **Dashboard**), you can validate your AI application activity.

After your application has performed several transactions after being configured with AI Guard, these transactions will now appear on the Dashboard page:

1. Select your desired date range and filters. See image.
2. Under the date range and filters, you can view the number of Apps, Detections, and Transactions AI Guard managed in that time.
3. The dashboard table contains information on each transaction for you to view.
4. Click the **Details** icon to open a window showing more detailed information about that specific transaction. See image.

[Image: Setting Store Prompts/Responses configuration window]

[Image: AI Guard Dashboard for Proxy]

[Image: Transaction Details menu]

[Image: LLM Provider Credentials page with Add More button]

[Image: Add LLM Credentials window]

[Image: AI Guard Policies page with an example policy visible]

[Image: Basic policy information fields which includes Policy Name and Description]

[Image: Configuring detector window showing the common options available.]

[Image: AI Guard Create Policy Control window]

[Image: AI Guard Policy Match page with an example policy match and Add More button visible.]

[Image: AI Guard Tenant Settings, Security Settings]

[Image: AI Guard AI Applications page showing App Name, Last Updated, Owner Email, Store Contents, Encrypt Contents, and Action]

[Image: Create Application window]

[Image: AI Application Identity Broker page]

[Image: Add Application Identity Broker window]

[Image: Save your key window, you need to copy the key before closing the window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/registering-broker","lastmod":"2026-07-27T05:31Z","nid":"1541765"} -->
## Registering a Broker

- Source: https://help.zscaler.com/secure-ai-apps-infra/registering-broker
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Brokers > Registering a Broker
- Last modified: 2026-07-27T05:31Z
- Summary: Information about how to register a broker in the AI Security Admin Portal.

Before you can route AI Red Teaming traffic through the AI Security Admin Portal, you must register a broker. Registering a broker is the first step in setting up your AI Red Teaming environment. It establishes a secure connection between your infrastructure and the AI Security Admin Portal platform, enabling the AI Red Teaming worker to connect to the private application.

To register a Red Teaming broker, do the following:

1. Go to **Administration > AI Red Teaming > Brokers**.
2. Click **+Register Broker**.
3. On the **Register** page, do the following: See image.
  - **Name:** The name of the broker.
  - **Business Units:** Select the business unit from the drop-down menu.
  - **Description:** Add information such as what the broker connects to, where it runs, and who runs it.
4. Click **Create Broker**. Your broker is created and visible in the **Brokers** Page.

[Image: The brokers you created appear in the Brokers page.]

[Image: Enter details in the register a broker page.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/release-upgrade-summary-2026","lastmod":"2026-08-28T10:21Z","nid":"1539124"} -->
## Release Upgrade Summary (2026)

- Source: https://help.zscaler.com/secure-ai-apps-infra/release-upgrade-summary-2026
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > Release Notes > Release Upgrade Summary (2026)
- Last modified: 2026-08-28T10:21Z
- Summary: Secure AI Apps & Infrastructure Release Upgrade Summary for service updates deployed in 2026.

This article provides a summary of all new features and enhancements for Secure AI Apps & Infrastructure.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/rest-api","lastmod":"2026-08-10T19:42Z","nid":"1540063"} -->
## REST API

- Source: https://help.zscaler.com/secure-ai-apps-infra/rest-api
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Connect an Asset > Connections > API > REST API
- Last modified: 2026-08-10T19:42Z
- Summary: Integration Setup information for Rest API is provided in this article.

After you select your [connection type](https://help.zscaler.com/secure-ai-apps-infra/connecting-ai-app), the **Configure your connection** page appears in the next step and prompts you to enter the required connection details, as follows:

- Send Message
- Obtaining Values
- Session Management
- OAuth REST API
- Authentication Header

- **URL**: The asset's endpoint to which the attack message is sent.
- **Request Payload Sample**: Provide the payload (body of the HTTP request). After you provide the payload, ensure it includes the following placeholders that you must insert:
  - **{message}**: This placeholder represents where the probe inserts attack messages, simulating input from a user interacting with your application.
  - **{session_id}**: This placeholder marks the location where a unique string, identifying the current conversation session is placed. This ensures that the request is tied to a specific session for multi-step testing.
  - The payload can contain additional fixed arguments if needed.
- **Response Path**: The JSON path pointing to the message within your chatbot's API response to the given request.

To obtain values, interact with your application and inspect the network requests using developer tools (e.g., in your browser or API testing tool like Postman).

- **URL**: Locate the endpoint URL where your chatbot sends requests. This is typically found in the network tab of developer tools or in your API documentation.
- **HTTP Headers**: Review the headers in the network request to identify any required key-value pairs, such as authorization tokens or content types.
- **Request Payload**: Copy the body of the POST request, then replace the user message with the {message} placeholder and the session identifier with the {session_id} placeholder.
- **Response Path**: Inspect the chatbot's API response and identify the JSON path to the specific part of the response where the chatbot's message is returned.

Some applications use separate endpoints to manage conversation sessions (separate from the endpoints used to send messages). For example:

- Start a session by sending a request to an Open session endpoint.
- End the session by sending a request to a Close session endpoint.

If your application works this way, enable and configure the Open Session and Close Session options in the integration settings. This lets you define the requests used to start and end sessions, so the Probe can simulate and test multi-step conversations correctly.

OAuth support for REST API connections allows third-party authentication without sharing user credentials (such as a username and password).

To enable OAuth authorization, provide the following:

- **URL**: The OAuth server endpoint used to request an access token (typically the token endpoint). Example: [https://auth.example.com/oauth/token](https://auth.example.com/oauth/token)
- **Client ID**: The unique identifier for your client application (from the OAuth server during app registration).
- **Client Secre**t: A confidential key used with the Client ID to authenticate the client application.
- **Scope**: A space-separated list of permissions that the application requests. These values are required to authenticate and authorize access via OAuth. Get the correct values from your OAuth provider and configure them in the connection settings.

These parameters are required to successfully authenticate and authorize access via OAuth. Make sure to obtain the correct values from your OAuth provider and configure them accordingly in the connection settings.

Enter the key-value pairs necessary for your API Requests. Authorization headers must be included for non-public APIs (alternatively, OAuth can also be used).

Select **+ Add Header** button for HTTP header customization after the Key and Value textboxes are filled
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/scheduling-test-run","lastmod":"2026-05-29T21:06Z","nid":"1540187"} -->
## Scheduling a Test Run

- Source: https://help.zscaler.com/secure-ai-apps-infra/scheduling-test-run
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Test Run > Scheduling a Test Run
- Last modified: 2026-05-29T21:06Z
- Summary: Information about creating a scheduled test run for AI Red Teaming in AI Security Admin Portal

The Test Run Scheduler enables you to plan and automate test runs at predefined times and frequencies, helping your team streamline evaluation workflows and reduce operational overhead. Instead of manually setting up tests every time you want to validate model behavior, scheduler allows you to set up recurring or single-run executions that run in the background.

The scheduler supports a wide range of use cases, from scheduled daily Context Leakage probe in production to monthly full security assessments—enabling consistent, repeatable testing practices aligned with your organization's policies and release cycles.

## Scheduling a Test Run

To schedule a test run, do the following:

1. Go to **AI Infrastructure** > **AI Red Teaming** > **Overview**.
2. Click **+New Test Run**. A **New Test Run** window appears.
3. Configure the test name and select probes.
4. Click **Schedule for Later**.
5. Choose a **Schedule Date**, **Schedule Time**, and **Frequency** (i.e., **Single Run**, **Daily**, **Weekly**, or **Monthly**) for execution.
6. Click **Schedule Test Run**to confirm. The scheduled test run gets listed in the **AI Infrastructure** > **AI Red Teaming - Test Runs** > **Scheduled** tab.

## Editing a Scheduled Test Run

To edit a scheduled test run, do the following:

1. Go to **AI Infrastructure** > **AI Red Teaming - Test Runs** > **Scheduled**tab.
2. Select the test run you would like to edit in the test runs table.
3. In the **Actions** column, click () icon and select **Edit**. The **Edit Scheduled Test Run** window appears.
4. Modify the parameters as required.
5. Click **Update Scheduled Test Run**. The scheduled test run is modified.

## Deleting a Scheduled Test Run

To delete a scheduled test run, do the following:

1. Go to **AI Infrastructure** > **AI Red Teaming - Test Runs** > **Scheduled**tab.
2. Select the test run you would like to edit in the test runs table.
3. In the **Actions** column, click () icon and select **Delete**. The **Delete** **Scheduled Test Run** window appears.
4. Type CONFIRM to delete.
5. Click **Delete** The scheduled test run is deleted.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/test-llm-providers-ai-guard-dasapi-mode","lastmod":"2026-07-27T10:45Z","nid":"1541883"} -->
## Test LLM Providers in AI Guard DAS/API Mode

- Source: https://help.zscaler.com/secure-ai-apps-infra/test-llm-providers-ai-guard-dasapi-mode
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Configuration > DAS/API Mode > Test LLM Providers in AI Guard DAS/API Mode
- Last modified: 2026-07-27T10:45Z
- Summary: This article describes how to integrate an application with Zscaler AI Guard using DAS/API mode.

In DAS/API mode, AI Guard is not inline from a network-routing perspective. The application still talks to the Large Language Model (LLM) directly, but the application code should be updated so that it calls AI Guard before sending the prompt to the model, and then calls AI Guard again on the model response before returning that response to the end user.

When configuring a proxy for Zscaler, use the following base URL endpoint: `https://proxy.zseclipse.net`

In other words, DAS/API mode depends on application logic:

- Application sends prompt content to AI Guard.
- If allowed, the application sends the prompt to the LLM.
- The application receives the LLM response.
- The application sends the response content to AI Guard.
- If allowed, the application returns the response to the user.

This is different from proxy mode:

- Proxy mode routes traffic through AI Guard inline.
- DAS/API mode adds explicit API calls in the application code.

The approach is agnostic across LLMs. The application still uses its normal model or provider, but the prompt and response are inspected by AI Guard using API calls.

## App Flow Example

The following diagram shows that applications configured with AI Guard in DAS/API Mode will continue to have the prompt and responses sent from the application to the LLM directly, but the application code logic will include steps to send the prompts and responses to the AI Guard cloud service API endpoints:

[Image: AI Guard DAS/API mode app flow example]

## DAS/API Endpoints

There are two primary endpoints for DAS/API mode:

- Option 1: Execute a specific policy
- Option 2: Resolve and execute policy

## Direction Handling

The application should inspect both sides of the interaction:

- The prompt before it is sent to the model.
- The response before it is shown back to the user.

For example:

```
def inspect_with_ai_guard(content: str, direction: str):
    headers = {
        "Authorization": f"Bearer {self.zag_key}",
        "Content-Type": "application/json",
    }
    payload = {
        "direction": direction,
        "content": content or "",
    }
    return requests.post(
        "https://api.zseclipse.net/v1/detection/resolve-and-execute-policy",
        json=payload,
        headers=headers,
        timeout=30,
    )
```

Common usage pattern:

- Call once with prompt content and outbound direction.
- Call again with model output and inbound direction.

## Suggested Integration Pattern

At a high level, the application code should look like this:

```
prompt_result = inspect_prompt_with_ai_guard(user_prompt)
if prompt_result_is_blocked(prompt_result):
    return blocked_message()

llm_response = call_llm(user_prompt)

response_result = inspect_response_with_ai_guard(llm_response)
if response_result_is_blocked(response_result):
    return blocked_message()

return llm_response
```

For explicit policy binding:

```
prompt_result = execute_specific_policy(user_prompt, "request",
ZAG_POLICY_ID
)
...
response_result = execute_specific_policy(llm_response, "response",
ZAG_POLICY_ID
)
```

For policy resolution:

```
prompt_result = resolve_and_execute_policy(user_prompt, "request")
...
response_result = resolve_and_execute_policy(llm_response, "response")
```

## When to Use Each Endpoint

Use `execute-policy` when:

- You want deterministic use of one exact policy.
- The application owner knows which policy should always apply.
- The policy routing should be controlled in the application code.

Use `resolve-and-execute-policy` when:

- The policy selection is handled by AI Guard.
- Multiple DAS/API policies may apply.
- The application should stay light and avoid hard-coded policy IDs.

## Configuration Requirements

For DAS/API mode, customers should expect to configure:

- AI Guard API key.
- DAS/API policy or policies.
- Fixed policy ID if using `execute-policy`.

The application team should be prepared to add:

- AI Guard API call before the prompt is sent to the model.
- AI Guard API call before the response is returned to the user.
- Error handling for failed inspections.
- Return message verbiage for blocked inspections.

## Implementation Considerations

- Use a short request timeout for AI Guard inspection.
- Handle AI Guard failures explicitly.
- Log blocked events safely without exposing sensitive content broadly.
- Ensure both prompt and response paths are instrumented.
- Do not bypass the response-side call if output enforcement is required.

Use this when:

- You want to explicitly reference a specific AI Guard policy.
- The application should always be checked against one known policy ID.

Endpoint:

```
https://api.zseclipse.net/v1/detection/execute-policy
```

Request shape:

```
self.zag_key = "
<insert-api-key-generated-in-aiguard>
"
ZAG_POLICY_ID =
<insert-policy-id-number>
headers = {
    "Authorization": f"Bearer {self.zag_key}",
    "Content-Type": "application/json",
}

payload = {
    "policyId": ZAG_POLICY_ID,
    "direction": direction,
    "content": content or "",
}
```

Notes:

- `policyId` is required.
- `direction` should indicate whether this is the `outbound` prompt or `inbound` response.
- `content` is the text being evaluated.

Use this when:

- You want AI Guard to resolve which policy should match.
- The application should not hard-code a single policy ID.

Requirements:

- DAS/API policies must exist in AI Guard.
- AI Guard evaluates those policies and determines the matching policy.

Endpoint:

```
https://api.zseclipse.net/v1/detection/resolve-and-execute-policy
```

Request shape:

```
self.zag_key = "
<insert-api-key-generated-in-aiguard>
"

headers = {
    "Authorization": f"Bearer {self.zag_key}",
    "Content-Type": "application/json",
}

payload = {
    "direction": direction,
    "content": content or "",
}
```

Notes:

- `policyId` is not sent.
- AI Guard resolves and applies the appropriate policy.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/test-llm-providers-ai-guard-proxy-mode","lastmod":"2026-08-06T14:49Z","nid":"1541882"} -->
## Test LLM Provider Endpoints in AI Guard Proxy Mode

- Source: https://help.zscaler.com/secure-ai-apps-infra/test-llm-providers-ai-guard-proxy-mode
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Configuration > Proxy Mode > Test LLM Provider Endpoints in AI Guard Proxy Mode
- Last modified: 2026-08-06T14:49Z
- Summary: The purpose of this article is to show, for each validated provider, the exact proxy URL path, required headers, and basic request body structure needed to successfully send a test prompt through the common Zscaler AI Guard proxy endpoint.

This document provides a reference for testing supported Large Language Model (LLM) providers through AI Guard in proxy mode. Its purpose is to show, for each validated provider, the exact proxy URL path, required headers, and basic request body structure needed to successfully send a test prompt through the common AI Guard proxy endpoint. This is intended as a practical reference guide for administrators and engineers who need to configure or verify provider-specific proxy routing in AI Guard for services such as Anthropic, Azure Foundry, AWS Bedrock, Google Gemini, and Google Vertex, OpenAI, etc.

When configuring a proxy for Zscaler, use the following base URL endpoint: `https://proxy.zseclipse.net`

In proxy mode, the application is updated so it no longer sends requests directly to each individual LLM provider endpoint. Instead, the application sends its model requests to Zscaler AI Guard as the single destination, and AI Guard then proxies that traffic to the appropriate upstream LLM provider on the application’s behalf. This allows AI Guard to sit inline for routing, inspection, and policy enforcement while simplifying the application-side integration to a common proxy pattern.

## App Flow Example

The following diagram is a simple visualization to show applications configured with AI Guard in proxy mode will have the prompts and responses flow from the application server to the AI Guard cloud service, which will then send the traffic to the destination LLM:

[Image: A simple visualization to show applications configured with AI Guard in proxy mode will have the prompts and responses flow from the application server to the AI Guard cloud service, which will then send the traffic to the destination LLM]

## Providers List

The following table provides a list of all supported AI providers as well as hostname and proxy path information.

| Provider | Hostname | Proxy Path |
| --- | --- | --- |
| Anthropic | Provider-specific upstream in AI Guard | `/v1/messages` |
| AWS Bedrock Agent | Provider-specific upstream in AI Guard | `/agents/``<AGENT_ID>``/agentAliases/``<AGENT_ALIAS_ID>``/sessions/``<SESSION_ID>``/text` |
| AWS Bedrock Anthropic | Provider-specific upstream in AI Guard | `/model/anthropic.``<MODEL>``/invoke` |
| AWS Bedrock Invoke | Provider-specific upstream in AI Guard | `/model/``<MODEL>``/converse` |
| Azure Foundry | `<your-resource>``.openai.azure.com` | `/openai/v1/chat/completions` |
| Google Gemini | `generativelanguage.googleapis.com` | `/v1beta/models/``<MODEL>``:generateContent` |
| Google Vertex | `aiplatform.googleapis.com` | `/v1/projects/``<PROJECT_ID>``/locations/``<REGION>``/publishers/google/models/``<MODEL>``:generateContent` |
| OpenAI | Provider-specific upstream in AI Guard | `/v1/chat/completions` |

## Provider Testing Information

- Anthropic
- AWS Bedrock Agent
- AWS Bedrock Anthropic
- AWS Bedrock Invoke
- Azure Foundry
- Google Gemini
- Google Vertex
- OpenAI

**Endpoint**: `POST https://proxy.zseclipse.net/v1/chat/completions`

**Headers**:

```
X-ApiKey:
<ZSCALER_AI_GUARD_KEY>
Content-Type: application/json
```

**Sample Body**:

```
{
  "model": "
<MODEL>
",
  "messages": [
    {
      "role": "user",
      "content": "Reply with the single word OK."
    }
  ],
  "max_tokens": 64
}
```

### Fields explained

- **X-ApiKey**: The AI Guard key for OpenAI.
- **Content-Type**: Specifies the request payload format (always JSON).
- **Model**: The model to query, such as `gpt-4o-mini`.
- **Messages**: List of conversations containing:
  - **Role**: The sender’s role. Options include: `"user"`, `"assistant"`, and `"system"`.
  - **Content**: The actual content or query to be processed.

**AI Guard hostname/FQDN**: `<your-resource>``.openai.azure.com`

**Endpoint**: `POST https://proxy.zseclipse.net/openai/v1/chat/completions`

**Headers**:

```
X-ApiKey:
<ZSCALER_AI_GUARD_KEY>
Content-Type: application/json
```

**Sample Body**:

```
{
  "model": "
<MODEL>
",
  "messages": [
    {
      "role": "user",
      "content": "Reply with the single word OK."
    }
  ],
  "max_tokens": 64
}
```

### Fields explained

- **Your-resource**: The prefix to your Azure Foundry OpenAI service.
- **X-ApiKey**: The AI Guard key for OpenAI.
- **Content-Type**: Specifies the request payload format (always JSON).
- **Model**: The model to query, such as `"claude-sonnet-4-2025051"`.
- **Messages**: List of conversations containing:
  - **Role**: The sender’s role. Options include: `"user"`, `"assistant"`, and `"system"`.
  - **Content**: The actual content or query to be processed.

**AI Guard hostname/FQDN**: `generativelanguage.googleapis.com`

**Endpoint**: `POST https://proxy.zseclipse.net/v1beta/models/``<MODEL>``:generateContent`

**Headers**:

```
X-ApiKey:
<ZSCALER_AI_GUARD_KEY>
Content-Type: application/json
```

**Minimal sample body aligned with Google Gemini**:

```
{
  "contents": [
    {
      "parts": [
        {
          "text": "Reply with the single word OK."
        }
      ]
    }
  ]
}
```

**Script-compatible sample body:**

```
{
  "contents": [
    {
      "role": "user",
      "parts": [
        {
          "text": "Reply with the single word OK."
        }
      ]
    }
  ],
  "generationConfig": {
    "maxOutputTokens": 64
  }
}
```

### Fields explained

- **Model**: The model being called, such as `gemini-2.5-flash`.
- **X-ApiKey**: The AI Guard key for Gemini.
- **Content-Type**: Specifies the request payload format (always JSON).

Upstream provider credential in AI Guard is a Gemini API key from Google AI Studio.

**AI Guard hostname/FQDN**: `aiplatform.googleapis.com`

**Endpoint**: `POST https://proxy.zseclipse.net/v1/projects/``<PROJECT_ID>``/locations/``<REGION>``/publishers/google/models/``<MODEL>``:generateContent`

**Headers**:

```
X-ApiKey:
<ZSCALER_AI_GUARD_KEY>
Content-Type: application/json
Authorization: Bearer
<GOOGLE_OAUTH_ACCESS_TOKEN>
X-Goog-User-Project:
<PROJECT_ID>
```

**Minimal sample body aligned with Google Gemini**:

```
{
  "contents": [
    {
      "parts": [
        {
          "text": "Reply with the single word OK."
        }
      ]
    }
  ]
}
```

**Script-compatible sample body:**

```
{
  "contents": [
    {
      "role": "user",
      "parts": [
        {
          "text": "Reply with the single word OK."
        }
      ]
    }
  ],
  "generationConfig": {
    "maxOutputTokens": 64
  }
}
```

### Fields explained

- **Project_ID**: The GCP project ID for Vertex.
- **Region**: The GCP region for Vertex, such as `us-central1`.
- **Model**: The model being called, such as `gemini-2.5-flash`.
- **X-ApiKey**: The AI Guard key for Vertex.
- **Content-Type**: Specifies the request payload format (always JSON).
- **Google_OAuth_Access_Token**: This is an access token obtained from the Google authorization server.
- **Contents**: List of conversations containing:
  - **Role**: The sender’s role. Options include: `"user"`, `"assistant"`, and `"system"`.
  - **Content**: The actual content or query to be processed.

Vertex does not use a Gemini API key in the request.
It also requires a short-lived Google OAuth bearer token in the original request. AI Guard forwards the bearer token upstream.

**Endpoint**: `POST https://proxy.zseclipse.net/v1/messages`

**Headers**:

```
X-ApiKey:
<ZSCALER_AI_GUARD_KEY>
Content-Type: application/json
anthropic-version:
<VERSION>
```

**Sample Body**:

```
{
  "model": "
<MODEL>
",
  "max_tokens": 64,
  "messages": [
    {
      "role": "user",
      "content": "Reply with the single word OK."
    }
  ]
}
```

### Fields explained

- **X-ApiKey**: The AI Guard key for Anthropic.
- **Content-Type**: Specifies the request payload format (always JSON).
- **Anthropic-version**: This is the version of Anthropic you're using, i.e. `2023-06-01`.
- **Model**: The model to query, such as `"claude-sonnet-4-2025051"`.
- **Messages**: List of conversations containing:
  - **Role**: The sender’s role. Options include: `"user"`, `"assistant"`, and `"system"`.
  - **Content**: The actual content or query to be processed.

**Endpoint**: `POST https://proxy.zseclipse.net/model/``<MODEL>``/invoke`

**Headers**:

```
X-ApiKey:
<ZSCALER_AI_GUARD_KEY>
Content-Type: application/json
accept: application/json
```

**Sample Body**:

```
{
  "anthropic_version": "
<MODEL>
",
  "max_tokens": 64,
  "messages": [
    {
      "role": "user",
      "content": [
        {
          "type": "text",
          "text": "Reply with the single word OK."
        }
      ]
    }
  ]
}
```

### Fields explained

- **Model**: The model to query, such as `anthropic.claude-3-haiku-20240307-v1:0`.
- **X-ApiKey**: The AI Guard key for Bedrock.
- **Content-Type**: Specifies the request payload format (always JSON).
- **Anthropic-version**: This is the version of Anthropic you're using, i.e. `2023-06-01`.
- **Messages**: List of conversations containing:
  - **Role**: The sender’s role. Options include: `"user"`, `"assistant"`, and `"system"`.
  - **Content**: The actual content or query to be processed.

**Endpoint**: `POST https://proxy.zseclipse.net/model/``<MODEL>``/converse`

**Headers**:

```
X-ApiKey:
<ZSCALER_AI_GUARD_KEY>
Content-Type: application/json
accept: application/json
```

**Sample Body**:

```
{
  "messages": [
    {
      "role": "user",
      "content": [
        {
          "text": "Reply with the single word OK."
        }
      ]
    }
  ],
  "inferenceConfig": {
    "maxTokens": 64,
    "temperature": 0
  }
}
```

### Fields explained

- **Model**: The model to query, such as `"amazon.nova-lite-v1:0"`.
- **X-ApiKey**: The AI Guard key for Bedrock.
- **Content-Type**: Specifies the request payload format (always JSON).
- **Messages**: List of conversations containing:
  - **Role**: The sender’s role. Options include: `"user"`, `"assistant"`, and `"system"`.
  - **Content**: The actual content or query to be processed.

**Endpoint**: `POST https://proxy.zseclipse.net/agents/``<AGENT_ID>``/agentAliases/``<AGENT_ALIAS_ID>``/sessions/``<SESSION_ID>``/text`

**Headers**:

```
X-ApiKey:
<ZSCALER_AI_GUARD_KEY>
Content-Type: application/json
accept: application/json
```

**Sample Body**:

```
{
  "inputText": "Reply with the single word OK.",
  "enableTrace": false
}
```

### Fields explained

- **Agent ID**: The Agent ID obtained from AWS Bedrock Agent
- **Agent Alias ID**: The Agent Alias ID obtained from your AWS Bedrock Agent.
- **Session ID**: Does not need to be pre-created in AWS. It is a client-supplied conversation ID. For example: `lab-test-001`
- **X-ApiKey**: The AI Guard key for Bedrock.
- **Content-Type**: Specifies the request payload format (always JSON).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/troubleshooting-ai-red-teaming-broker-issues","lastmod":"2026-08-10T19:19Z","nid":"1541957"} -->
## Troubleshooting AI Red Teaming Broker Issues

- Source: https://help.zscaler.com/secure-ai-apps-infra/troubleshooting-ai-red-teaming-broker-issues
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Brokers > Troubleshooting AI Red Teaming Broker Issues
- Last modified: 2026-08-10T19:19Z
- Summary: This Zscaler runbook outlines troubleshooting steps for AI Red Teaming Brokers.

The AI Security Red Teaming Broker is a lightweight agent that you deploy inside your network to enable Red Teaming to reach private AI applications without opening inbound firewall ports. Use this article to diagnose and resolve common broker connectivity, authentication, and routing issues.

### Running the Preflight Check

For any broker issue, your first step is always to run the built-in diagnostic tool `airt-broker diagnose`. It checks connectivity, certificates, OAuth credentials, and DNS without disrupting a live broker.

Run the following command within the container:

`docker exec <broker-container> python``-m``airt_broker diagnose`

An example of healthy output is shown here:

[Image: An example of a healthy output is shown here.]

### Quick Reference

The following table lists the symptoms, the likely causes, and resolution:

| Symptom | Likely Cause | Resolution |
| --- | --- | --- |
| Broker stays **Pending**or **Offline** Logs repeat `opening wss` with `no wss connected` | Cannot reach or authenticate to gateway | Check egress to `GATEWAY_URL:443` Run `diagnose` tool |
| **Auth**token returns (`401 invalid_client`) | Incorrect `OAUTH_CLIENT_ID / OAUTH_CLIENT_SECRET` | Verify that the Client ID and Client Secret match the registered broker |
| WSS rejected **4403**(`gateway_cidr_rejected`) | Broker’s egress IP is not in the source-IP allow-list | Add the broker’s public egress IP to the allow-list in the platform |
| WSS rejected **4401**(`gateway_broker_unregistered / _revoked`) | BROKER_ID mismatch, or broker revoked/deleted | Confirm `BROKER_ID` Ensure the broker is not revoked (reinstate if needed) |
| TLS errors (`certificate verify failed, unable to get local issuer`) | Corporate TLS inspection/private CA | Set `EXTRA_CA_CERTS` (add the corporate CA) Disable SSL inspection for the broker traffic Set `HTTPS_PROXY` if required |
| Broker **Online**but tests return **403** | Target base URL not in the broker’s target allow-list | Add the target’s base URL to the broker’s allow-list, or correct `X-Target-Url`. |
| Tests **time out (504)** or fail **502** | Target slow or unreachable from the broker’s network | Verify the broker can reach the target Raise `TARGET_REQUEST_TIMEOUT` for slow applications |
| Broker disconnects periodically while idle (`close_code:null`) | An intermediary (proxy/ NAT/firewall) idle or max-connection timeout | Benign - the broker reconnects automatically Raise the intermediary’s idle timeout if frequent. |

### Diagnosing and Resolving Common Issues

This section describes common Broker issues and the steps to resolve them:

- Broker Stays Pending or Offline
- TLS Certificate Errors
- Broker is Online but Tests Return 403
- Tests Time Out (504) or Fail with 502
- Broker Disconnects Periodically While Idle

**Symptom:** The broker status does not change from **Pending**, or it repeatedly switches to **Offline**. The logs show repeated `opening wss` entries with no `wss connected` line.

The following are the likely causes:

- Cannot Reach the Gateway
- Invalid OAuth Credentials (`diagnose` shows `401 invalid_client`)
- Egress IP Not in the Allow-List (`4403 gateway_cidr_rejected`)
- Broker ID Mismatch or Broker is Revoked (`4401 gateway_broker_unregistered or _revoked`)

**Symptom:** Broker startup logs show certificate verify failed or unable to get local issuer certificate.

**Cause:** A corporate TLS inspection proxy or a private Certificate Authority (CA) is intercepting the broker's outbound connection.

To resolve this issue:

1. Obtain the corporate CA certificate in PEM format.
2. Set the `EXTRA_CA_CERTS` environment variable to the path of the PEM file and mount it into the container: `-e EXTRA_CA_CERTS="/etc/airt-broker/corp-intermediates.pem" \ -v "$PWD/certs:/etc/airt-broker:ro"`
3. If an outbound proxy is required, also set: `-e HTTPS_PROXY="http://proxy.internal:8080"`Do not set `SSL_VERIFY` to false. Disabling certificate validation removes a critical security control and is not supported in production environments.

**Symptom:** Requests hang and return `504 Gateway Timeout` or `502 Bad Gateway`.

**Cause:**The target application is slow to respond or is unreachable from the broker host.

To resolve this issue:

1. From the broker host, verify that the broker can reach the target URL directly (for example, using `curl <target-url>`).
2. If the target is reachable but responds slowly, increase the request timeout: `-e TARGET_REQUEST_TIMEOUT=120`
3. Check that no firewall rule is blocking traffic from the broker host to the target application.

**Symptom:** Logs show `wss disconnected` with `close_code: null` and `abnormal: true` after a period of inactivity, followed by automatic reconnection (opening wss).

**Cause:** An intermediary (proxy, NAT gateway, or firewall) is closing idle connections due to a configured timeout.

This behavior is benign. The broker reconnects automatically. If reconnections are frequent and affecting test reliability, work with your network team to increase the idle connection timeout on the intermediary.

**Symptom:**The broker shows **Online**in AIRT, but test requests return a `403`error.

**Cause:** The target's base URL is not in the broker's target allow-list.

To resolve this issue:

1. Go to **Administration > Red Teaming > Brokers** and open the broker's detail page.
2. Select the **Allow-lists** tab, then select **Target URLs**.
3. Add the target's base URL, or verify that the X-Target-Url header in your request is correct.

### Collecting Logs for Support

If you cannot resolve the issue, collect the following information before contacting Zscaler Support:

Run these commands to export a redacted diagnostic report and recent broker logs:

```
docker
exec <broker-container> python -m airt_broker diagnose
--json
> diag.json
docker
logs
--tail 500
<broker-container> > broker.log
```

Ensure you include the following information when you contact support:

- `diag.json`
- `broker.log`
- Your `BROKER_ID`
- The approximate date and time of the incident The `diag.json` output is automatically redacted and does not contain your `OAUTH_CLIENT_SECRET`. Do not share your client secret directly with support or include it in log files.

1. Confirm that outbound TCP access to the `GATEWAY_URL` host on port **443**is open.
2. Run the preflight check and review the DNS, TCP, and TLS rows.

1. Go to **Administration > Red Teaming > Brokers** and open the broker's detail page.
2. Compare the `OAUTH_CLIENT_ID` and`OAUTH_CLIENT_SECRET` values in your deployment against the Authentication Service credentials associated with this broker registration.
3. If they do not match, re-copy the credentials from the Authentication Service API client and redeploy the broker container.

1. Identify the broker container's public egress IP address.
2. Go to **Administration > Red Teaming > Brokers** and open the broker's detail page.
3. Under **Source-IP Allow-list**, add the broker's public egress IP. Allow-list changes apply immediately and no broker restart is required.

1. Confirm that the `BROKER_ID` environment variable in your deployment matches the ID shown on the broker's detail page in AIRT.
2. If the broker status shows **Revoked**, go to **Administration > Red Teaming > Brokers**, open the broker, and select **Reinstate**.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/understanding-ai-security-broker","lastmod":"2026-07-26T23:36Z","nid":"1541762"} -->
## Understanding the AI Security Broker

- Source: https://help.zscaler.com/secure-ai-apps-infra/understanding-ai-security-broker
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Brokers > Understanding the AI Security Broker
- Last modified: 2026-07-26T23:36Z
- Summary: This article describes how the broker works in the AI Security Admin Portal

The AI Red Teaming Broker enables Zscaler's AI Red Teaming (AIRT) workers, which run in the Zscaler cloud, to securely reach private AI applications hosted inside a customer's network without requiring any firewall changes to allow inbound connections that originate from the Internet.

In most enterprise environments, AI applications sit behind a firewall with no inbound ports open to the internet. The AI Red Teaming Broker eliminates this barrier by having the customer deploy a lightweight broker agent inside their network. The agent opens a single outbound connection to the Zscaler cloud, allowing AI Red Teaming to reach the private application through that connection.

## How It Works

The broker opens one outbound WebSocket Secure (WSS) connection to the cloud gateway and keeps it open. All test traffic travels over this tunnel to the customer’s application and back.

1. The Broker Agent initializes an outbound WSS connection towards the Broker Gateway that is running in the Zscaler infrastructure.
2. After a connection is established, the AI Red Teaming Worker sends the commands to the Broker Agent via the Broker Gateway.
3. The commands are carried over the bidirectional WSS connection.
4. The Broker Agent forwards the command to the Customer AI App.
5. The Customer AI App sends its response back to the Broker Agent.
6. The Broker Agent sends the response to the Broker Gateway.
7. The Broker Gateway forwards the response to the AI Red Teaming Worker.

No service is exposed to the internet in the customer’s network. The broker makes only outbound connections on port 443.

## Provisioning a Broker

Users can provision a broker by performing the following steps:

- 1. Register a broker.
- 2. Obtain the OAuth credentials.
- 3. Download the generated run manifest.
- 4. Verify the broker status.
- 5. Configure the target AI application and attach the broker.

Create a new broker entry in the AI Security Admin Portal to generate the credentials and configuration required for deployment. To learn more, see [Registering a Broker](https://help.zscaler.com/secure-ai-apps-infra/registering-broker).

Ensure you do the following before you run the broker:

1. Create an API Client. To learn more, see [Adding an API Client](https://help.zscaler.com/authentication-service/adding-api-client).
2. Assign **AI Red Teaming Viewer** scope under AI Security.
3. Set the **Validation Type** to **Secret**.
4. Copy the **Client ID** and Client Secret to a password manager. This is used when configuring the broker's environment. You can reuse the same API Client across multiple brokers.

The broker authenticates to the platform with an OAuth API Client you create in the Authentication Service Admin Portal.

Download the run manifest file that contains the broker configuration. Use this file to deploy and run the broker within your private network environment. To learn more, see [About Red Teaming Brokers](https://help.zscaler.com/secure-ai-apps-infra/about-red-teaming-brokers).

Confirm that the broker shows **Online**in the AI Security Admin Portal. An **Online**status indicates that the broker has successfully established an outbound WebSocket connection to the Zscaler cloud gateway.

Add your private AI application as a target in the platform and associate it with the provisioned broker. This enables Zscaler AI Red Teaming workers to route test traffic through the broker to reach the application. To learn more, see [Connect an Asset](https://help.zscaler.com/secure-ai-apps-infra/connect-an-asset).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/understanding-probes","lastmod":"2026-05-15T00:20Z","nid":"1540145"} -->
## Understanding Probes

- Source: https://help.zscaler.com/secure-ai-apps-infra/understanding-probes
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Red Teaming > Probe Settings > Understanding Probes
- Last modified: 2026-05-15T00:20Z
- Summary: Information about probes in the AI Security Admin Portal

Probe is the adversarial testing module in Zscaler AI Security. It allows you to proactively evaluate your AI systems against real-world, domain-specific attack patterns. With Probe, you can configure, execute, and analyze simulated adversarial runs to discover vulnerabilities.

They help you understand how your AI app performs under pressure, identify weaknesses before they become problems, and ensure the system remains safe, reliable, and aligned with your organization’s standards.

After connecting the AI app to AI Security, and selecting and configuring at least one of the available probes, you can start your first test run. These probes help identify potential vulnerabilities in the AI app.

## Probe Categories

All probes are designed to provoke and detect specific vulnerabilities. These probe descriptions outline what each probe tests, what to expect during evaluation, and why it matters, giving you a complete picture of how your AI app is assessed across security, compliance, accuracy, and user experience. Each probe simulates real-world scenarios across the following key categories:

- Security
- Safety
- Hallucination & Trustworthiness
- Business Alignment
- Custom

## Risk Priority

Each probe also includes a Risk Priority (Low, Medium, High, Critical), which reflects the potential risk based on the severity and likelihood of exploitation. A higher Risk Priority means that any vulnerabilities identified by the probe contribute more to the AI app’s Overall Risk Score shown on the Overview page - so findings with a higher risk level will increase the score more than lower-risk findings. Default values are set according to the AI app type, and this metric is used to calculate the overall performance of your application.

Additionally, the assigned Coverage Level (Basic, Medium, Extended) defines the depth of weakness testing. Higher coverage levels typically include a broader set of checks and more thorough probing, which can increase test duration. Choose the level that best balances testing depth with time and resource constraints for your environment.

Security probes test how well a target application resists adversarial inputs. They focus on confidentiality, integrity, and access controls. They simulate attack-like conversations (e.g., prompt injection, phishing, XSS). They mark results as pass/fail based on target behavior. They focus on model behavior and policy enforcement. They do not scan infrastructure.

Security probes help you assess the following:

- Resistance to prompt injection and jailbreak attempts.
- Protection of sensitive data (secrets, credentials, internal info).
- Enforcement of authorization and policy boundaries.
- Safe handling of untrusted input and malicious instructions.

Safety probes test whether a target avoids harmful, abusive, or dangerous content. They simulate high-risk prompts and mark results as pass/fail based on target behavior. They focus on safety policy adherence in real conversations.

The safety probes help you assess:

- Safe handling of illegal or harmful requests.
- Avoidance of toxicity, harassment, hate, and discriminatory content.
- Protection of user privacy and sensitive personal data.
- Appropriateness and de-escalation in high-risk conversations.

Hallucination & Trustworthiness probes test whether a target produces accurate, grounded, and verifiable outputs. They use datasets and RAG context where applicable. They mark results as pass/fail based on factuality, grounding, and refusal behavior.

The Hallucination & Trustworthiness probes help you assess:

- Hallucinated facts, links, or citations.
- Grounding to provided context (e.g., RAG file) and avoidance of unsupported claims.
- Consistency of answers across similar prompts.

Business Alignment probes test whether a target follows your organization’s business rules and brand guardrails. They simulate prompts that pressure the target into out-of-scope or high-stakes behavior. They mark results as pass/fail based on adherence to internal policies and intended use cases.

The business alignment probes help you assess:

- Adherence to company policies, disclaimers, and approved messaging.
- Brand voice, tone, and style consistency.
- Handling of out-of-scope requests and escalation paths.
- Prevention of unauthorized commitments (e.g., refunds, contracts, unlisted services).

Custom probes let you define your own testing rules and datasets. They help you validate target behavior that built-in probes may not cover. They generate prompts and variations from your inputs. They mark results as pass/fail based on your expected behavior.

These probes help you assess:

- Adherence to allowed and banned behaviors (policy-style rules)
- Regression detection using internal adversarial datasets
- Accuracy and consistency for custom Q&A and critical facts

The types of custom probes are as follows:

- Custom probe
- Custom Dataset
- Custom Q&A

The custom probe tests your AI app against rules you define. The Platform generates and sends relevant prompts (including variations) to verify that the chatbot consistently follows the allowed behaviors and avoids the banned behaviors, flagging responses that violate your expectations.

Custom probes allow you to validate safety and security requirements that may not be covered by built-in probes. It helps uncover policy gaps, unintended capabilities (e.g., over-permissive actions, sensitive data exposure, unauthorized commitments), and reduces the risk of incidents, compliance violations, and reputational harm.

This custom dataset probe uses your uploaded CSV dataset as the source of attacks or test cases. For each entry, the Platform can generate additional variants (languages, paraphrases, strategies), run them against the target, and evaluate responses using either regex-based detection or an LLM-based evaluator.

The custom dataset probe is ideal when you already have internal test suites or domain-specific scenarios (e.g., proprietary jailbreak attempts, compliance checks, organization-specific abuse patterns). It scales your testing with broader coverage, more realistic automated adversarial pressure, and faster regression detection.

This probe tests whether your application answers the questions you define correctly. The Platform will reformulate your questions, send them to the target, and evaluate whether the response content matches the correct answers you provided in the CSV.

Custom Q&A probe helps validate accuracy and consistency for critical business information (e.g., FAQ, policies, product facts). It is useful for tracking regressions after model, prompt, or knowledge-base changes and for measuring answer quality over time.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-apps-infra/what-ai-guard","lastmod":"2026-07-27T10:38Z","nid":"1541745"} -->
## What Is AI Guard for Apps?

- Source: https://help.zscaler.com/secure-ai-apps-infra/what-ai-guard
- Product: Secure AI Apps & Infrastructure
- Path: Secure AI Apps & Infrastructure Help > AI Guard for Apps > Getting Started > What Is AI Guard for Apps?
- Last modified: 2026-07-27T10:38Z
- Summary: A summary of AI Guard for Apps and its functionality.

The rise of generative large language models (LLMs) has accelerated the pace of application innovation and increased productivity, with organizations customizing LLMs to their specific tasks. However, the proliferation of LLMs in apps and agents is creating new security challenges, including prompt injection, toxicity, and code leakage. This increasing adoption of LLMs necessitates the implementation of AI security in applications, in addition to cybersecurity and data security.

AI Guard for Apps is a service that provides run-time protection for your AI applications by enforcing enterprise policies that prevent prompt injections, block jailbreak attempts, and stop personal information leakage with guardrails around LLM interactions.

It secures these interactions by filtering harmful or inappropriate content from the prompts that users enter and the responses they receive in the AI application. AI Guard enforces intent-based detectors on both prompts and responses.

See image.

## Key Capabilities of AI Guard

The following functionality and guardrails are some of the options available to you with AI Guard:

- **Visibility & Access Control**: You can monitor which applications are accessing private AI systems, with granular controls over access permissions. Visibility tools ensure complete oversight of private AI interactions across the enterprise.
- **Prompt Injection & Jailbreak Protection**: AI models can be manipulated with adversarial prompts to bypass restrictions or produce unintended outputs. The AI Guard guardrails detect and neutralize such attacks, ensuring that AI responses stay within defined safety parameters.
- **Toxicity**: AI-generated content can potentially be emotionally harmful for workers. AI Guard's guardrails apply real-time monitoring and filtering to prevent toxic language.
- **Sensitive Data Protection**: AI models can inadvertently expose or process confidential, regulated, or proprietary information. AI Guard enables inline inspection and classification to detect and block sensitive data before it is sent to AI APIs.
- **Off-Topic Response Detection**: AI Guard detects and prevents off-topic responses, ensuring that AI stays focused on its intended use cases.
- **Malicious URL Detection & Blocking**: AI-generated responses can sometimes include harmful or phishing links. AI Guard's solution actively scans links and blocks malicious URLs to prevent security threats and safeguard users from cyber risks.
- **Language Detection & Enforcement**: Organizations can enforce AI interactions in specific approved languages, preventing unauthorized language use that can lead to miscommunication or security vulnerabilities.
- **Code Injection & Execution Detection**: AI Guard detects and prevents unauthorized code snippets embedded in AI interactions, protecting against potential security risks from malicious scripts or unauthorized automation attempts.
- **Gibberish & Low-Quality Prompt/Response Filtering**: AI models can sometimes produce nonsensical or low-value responses. Our system detects gibberish, meaningless text, and irrelevant output, ensuring that AI responses remain coherent and valuable.
- **Refusal Detection & Intervention**: When AI refuses to respond to valid queries due to overly restrictive filters or misunderstanding, our system flags and intervenes to alert the security team because it might be a denial-of-service attack.
- **Finance Advice**: AI Guard can block actionable financial guidance (investing, trading, tax, product choices); allows neutral finance facts, history, and definitions.
- **Prompt Tagging-based Access Control**: AI Guard has prompt tagging model that automatically classifies prompts into different categories, enabling organizations to enforce AI governance, security, and compliance while optimizing AI interactions.
- **Competitor Discussion Detection**: You can identify and block prompts that reference competitors, rival products, pricing comparisons, or market strategies.
- **URL Reachability Detection**: AI-provided links can sometimes be outdated or unreachable. This guardrail verifies whether URLs in prompts are accessible, safe, or broken to prevent hallucinations.
- **Legal Advice**: Block prompts seeking legal advice, interpretation, or compliance guidance. This allows for neutral legal facts, definitions, and non-legal work inquiries.

[Image: Diagram of the AI Guard workflow]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/about-ai-application-topology","lastmod":"2026-07-21T12:09Z","nid":"1541718"} -->
## About User Groups & LLM Provider Topology

- Source: https://help.zscaler.com/secure-ai-users/about-ai-application-topology
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Monitoring > About User Groups & LLM Provider Topology
- Last modified: 2026-07-21T12:09Z
- Summary: Learn how to use and view the AI Guard AI Application Topology page.

The AI Guard for Users Topology page provides a system overview of the data flow between all user groups and LLM providers managed by AI Guard in Proxy mode.

The AI Guard **Topology** page provides the following benefits and enables you to:

- View data flow between all user groups and LLM providers managed by AI Guard.
- Hover over user groups or LLM providers to view additional information.
- Click on individual user group and LLM provider nodes to highlight their direct data flow connections.

## About the AI Guard AI Applications Topology Page

On the AI Guard**AI Applications Topology** Page (AI Security Admin Portal > AI Guard > Topology > Users tab), you can do the following:

1. Select the Users tab.
2. Search by a specific User Group or LLM Provider.
3. Select whether you want to search by Users, LLM Providers, or All Types.
4. Make the following view adjustments:
  - Zoom In.
  - Zoom Out.
  - Fit to View.
  - Reset Zoom.
5. Reset the topology view back to default.
6. View the overall topology of User Groups and LLM providers. Hover your cursor over a user or LLM provider to view additional information about the node. Click on a node to highlight the direct connections to and from it.
  - Users or User Groups: Formatted as green circles.
  - LLM Providers: Formatted as purple hexagons.
  - Data Flow: Represented as lines connecting the user groups and LLM providers.

[Image: AI Guard User Groups & LLM Topology page with highlights around the interactable elements of the page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/about-ai-guard-dashboard","lastmod":"2026-07-21T11:29Z","nid":"1540886"} -->
## About AI Guard Dashboard

- Source: https://help.zscaler.com/secure-ai-users/about-ai-guard-dashboard
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Monitoring > About AI Guard Dashboard
- Last modified: 2026-07-21T11:29Z
- Summary: Learn how to view information on the AI Guard dashboard.

The AI Guard dashboard provides a high-level overview of all the users and AI applications AI Guard manages. This includes information such as the number of users or apps, number of policy detections, and number of AI bot transactions.

The AI Guard **Dashboard** page provides the following benefits and enables you to:

- View general statistics of your users, LLMs, and policy detections.
- View information on individual policy transactions.
- Search by specific policy names or filters.

## About the AI Guard Dashboard Page

On the AI Guard **Dashboard** page (AI Security Admin Portal > AI Guard > Dashboard), you can do the following:

1. Select the **Users** tab.
2. Select the date range (up to 90 days) for your data.
3. Search by a specific policy name.
4. Filter your data based on criteria such as **Policy Name**, **Severity**, and **Prompt Detections**.
5. Refresh will update your dashboard with the most current data.
6. Export a PDF copy of your dashboard for the selected date range.
7. View the following information:
  1. **Number of Users**: The total number of users.
  2. **Number of LLMs**: The total number of LLMs.
  3. **Number of Detections**: The total number of policy detections.
  4. **Number of Transactions**: The total number of transactions.
8. View a list of all transactions. For each transaction, you can see the following:
  - **Date and Time**: The date and time of the transaction.
  - **User**: The name of the user.
  - **Policy Name**: The name of the policy associated with this transaction.
  - **Severity**: The level of severity of the transaction. The severity can be **Info**, **Low**, **Medium**, **High**, and **Critical**.
  - **Prompt Detections**: The specific prompt detectors that the policy triggered.
  - **Response Detections**: The specific response detectors that the policy triggered.
  - **LLM**: The LLM used for this transaction.
  - **Prompt Action**: Displays whether the AI Guard policy transaction resulted in the prompt being **Allowed** or **Blocked.**
  - **Response Action**: Displays whether the AI Guard policy transaction resulted in the response being **Allowed** or **Blocked.**
  - **Details**: Clicking the **Details** icon opens a window showing detailed information in the following sections: **Overview**, **Detection Summary**, **Performance & Network Stats**, **Custom Request Headers**, and **Prompt Details**. See image.

[Image: AI Guard Dashboard Details window showing Overview, Detection Summary, Performance & Network Stats, Custom Request Headers, and Prompt Details.]

[Image: AI Guard Dashboard page with annotations highlighting each section]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/about-ai-guard-detection-summary","lastmod":"2026-07-21T17:19Z","nid":"1541851"} -->
## About AI Guard Detection Summary

- Source: https://help.zscaler.com/secure-ai-users/about-ai-guard-detection-summary
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Monitoring > About AI Guard Detection Summary
- Last modified: 2026-07-21T17:19Z
- Summary: Learn how to access and view information on the AI Guard detection summary.

The AI Guard dashboard provides a high-level overview of all the users AI Guard manages, but if you want to view additional details on specific transactions, you need to view the detection summary found in the transaction details window.

To access a transaction detection summary, go to AI Security Admin Portal > AI Guard > Dashboard. In the dashboard, find the transaction you want to view, go to the **Details** column, and click **View Details**. The **Transaction Details** window opens.

On the Transaction Details window, you can view the following information:

1. View or copy the Transaction ID.
2. The Overview section contains the following information:
  1. **Date**: Date and time of the transaction.
  2. **User**: The user who submitted the prompt or who received the response.
  3. **Severity**: Strength of the prompt's triggering of the policy detector: Info > Low > Medium > High > Critical.
  4. **Policy**: Name of the policy that was triggered.
  5. **LLM**: Name of the LLM used for analyzing the prompt or sending the response.
  6. **LLM Model**: Specific LLM model used for this transaction (such as: claude-sonnet-4-6).
  7. **User Agent**: Information about the user's agent where the prompt was written or the response was sent to.
3. The Detection Summary section contains the following information:
  1. **Prompt Detections**: Shows which detectors were triggered by the prompt.
    1. **Details**: Clicking for more details will show additional information about the detectors that were triggered.
  2. **Prompt Action**: Shows whether the prompt was blocked, allowed, or just detected.
  3. **Prompt Tags**: Shows an tags associated with the prompt.
  4. **Response Detections**: Shows which detectors were triggered by the response.
    1. **Details**: Clicking for more details will show additional information about the detectors that were triggered.
  5. **Response Action**:Shows whether the response was blocked, allowed, or just detected.
  6. **Response Tags**: Shows an tags associated with the response.
4. The Performance & Network Stats section contains the following information:
  1. **Prompt Size**: File size of the prompt.
  2. **Response Size**: File size of the response.
  3. **Total Time Taken by LLM**: Length of time the LLM took to process the prompt and send a response
  4. **LLM Provider Status Code**: An HTTP status code that corresponds to the status of the request. For example, 200 means Acceptable Response.
  5. **Detection Time On Request**: Time it took to analyze the prompt.
  6. **Detection Time On Response**: Time it took to analyze the response.
  7. **Region**: Region of the user's server.
  8. **Protocol**: Protocol used by the transaction.
5. **Custom Request Headers**: This section will show any custom headers that were set up in the AI Guard tenant settings page.
6. **Prompt and Response Details**: In this section you can read the specific prompt sent by the user and the response sent by the GenAI app.
  1. Add to Allowlist: If you have the proper permissions, when you reveal a prompt or response that was blocked, you can click this option to add the prompt or response to your allowlist.
7. **Was this detection helpful?**: Leave feedback for Zscaler Support.

[Image: AI Guard Detection Summary window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/about-ai-guard-insights","lastmod":"2026-07-22T09:01Z","nid":"1540887"} -->
## About AI Guard Insights

- Source: https://help.zscaler.com/secure-ai-users/about-ai-guard-insights
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Monitoring > About AI Guard Insights
- Last modified: 2026-07-22T09:01Z
- Summary: Learn how to use and view the AI Guard Insights page.

The AI Guard **Insights** page provides an executive overview of all the users that AI Guard manages. This includes information such as the number of users, number of policy detections, number of AI bot transactions, and category of prompts.

The AI Guard **Insights** page provides the following benefits and enables you to:

- View general user statistics for AI Guard, such as the number of blocked prompts and responses.
- View data graphs of AI Guard statistics.
- View an infographic of the most common prompt categories.

## About the AI Guard Insights Page

On the AI Guard **Insights** page (AI Guard > Insights), you can do the following:

1. Select the **Users** tab.
2. Select the date range to view the corresponding data. Click **Reset** to set the date range back to default.
3. Refresh your Insights data.
4. Export your Insights page to a PDF file.
5. View data about AI Guard activities in the following categories:
  - **Total Prompts**: Total number of prompts users have submitted.
  - **Blocked Prompts**: Total number of blocked prompts users tried to submit.
  - **Prompt Tokens**: Approximate number of input tokens consumed by users.
  - **Average Prompt Tokens**: Average number of tokens per prompt.
  - **Average Prompt Detection Latency**: Average time for prompt detection in milliseconds.
  - **Total Responses**: Total number of LLM responses.
  - **Blocked Responses**: Total number of LLM responses that AI Guard blocked.
  - **Response Tokens**: Approximate number of output tokens consumed by users.
  - **Average Response Token**: Average number of tokens per response.
  - **Average Response Detection**: Average time for response detection in milliseconds.
  - **Active Users**: Total number of active users.
  - **Active LLMs**: Total number of LLMs.
6. View data presented in the following graphs:
  - **Trend Over Time**: Daily transaction volume showing **Blocked** and **Total** counts. Use this to spot spikes in blocked traffic or unusual activity patterns.
  - **Security Posture**: Overview of transaction disposition which shows the proportion of **Allowed**, **Blocked**, and **Passthrough** transactions across all AI traffic.
  - **Transactions by LLM**: Displays the active LLMs and breaks down by color how many of them are **Low Risk**, **High Risk**, or **Critical Risk**.
  - **Top Detectors**: Displays the detectors that have been triggered the most.
  - **Transactions by Users**: Displays the total number and percentage of transactions. Hover your mouse over the circle chart to see the number of transactions per user.
  - **PII Detections by Users / Groups**: Total number of personally identifiable information (PII) detections per user or user group. Clicking on a user bar will open a window with all transactions that contributed to that bar in the chart.
  - **PII Categories**: Types of PII detected in AI traffic such as credit cards, emails, SSNs, and phone numbers. Clicking on a category bar will open a window with all transactions that contributed to that bar in the chart.
7. View the most common prompt categories during the selected date range and the number of transactions for each category. [Image: AI Guard Insights page with numbered annotations around each UI element and section]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/about-ai-guard-usage","lastmod":"2026-07-21T13:02Z","nid":"1540888"} -->
## About AI Guard Token Usage

- Source: https://help.zscaler.com/secure-ai-users/about-ai-guard-usage
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Monitoring > About AI Guard Token Usage
- Last modified: 2026-07-21T13:02Z
- Summary: Learn about the AI Guard Token Usage dashboard.

The AI Guard **Token Usage** dashboard provides an overview of the data usage by users.

The AI Guard **Token Usage** page provides the following benefits and enables you to:

- View general statistics about AI Guard token usage by users.
- View usage based on specific time intervals.

## About the AI Guard Token Usage Page

On the AI Guard **Token Usage** page (AI Guard > Token Usage), you can do the following:

1. Select the **Users** tab.
2. Select the date range for your data.
3. Refresh updates your dashboard with the most current data.
4. Select to view token usage data across the entire tenant, or a single user
5. The Overview section shows the following information:
  1. **Total Tokens**: All prompt and response tokens used by every user in your tenant during this period.
  2. **Top User**: The user that used the most tokens.
  3. **Busiest Day**: The day with the most token usage.
  4. **Avg. per User**: Average tokens used by each user on the leaderboard. A high "Top User" share with a low average means usage is concentrated in a few users.
6. The Insights section presents the following data: [Image: AI Guard Usage page with highlights around the Users and AI Applications tabs, date range, refresh, Input/Output info, and Input/Output graph]
  1. **Prompts vs. Responses**: Total number of prompts and transactions as well as their ratio.
  2. **Activity by day & hour**: Requires Hourly or 5-min granularity to see hour-of-day patterns.
  3. **Token Consumption**: A graph of token consumption over the selected time period.
  4. **LLM Provider Distribution**: See LLM distribution information based on the level of granularity you have selected (Daily, Hourly, 5-min).
  5. **Top users by token usage**: The 10 users that used the most tokens. Click a bar to filter the page to just that user.
  6. **Raw Data**: A quick total summary of prompts and responses by date. Data can be exported.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/adding-and-managing-ai-guard-policy-configurations","lastmod":"2026-07-19T20:58Z","nid":"1540882"} -->
## Adding and Managing AI Guard Policy Configurations

- Source: https://help.zscaler.com/secure-ai-users/adding-and-managing-ai-guard-policy-configurations
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Configuration > Adding and Managing AI Guard Policy Configurations
- Last modified: 2026-07-19T20:58Z
- Summary: Adding and Managing policy configurations for AI Guard.

AI Guard works by enforcing enterprise policies on prompts and responses between users and public AI apps, such as ChatGPT, Perplexity, Claude, etc., and between private AI apps and foundational Large Language Models (LLMs), such as OpenAI, Anthropic, etc. You set a policy by enabling one or more included detectors on prompts and responses. These detectors are activated on prompts and responses based on the policies you define in the portal. Every policy is a guardrail, and you can set up multiple policies, define and apply a policy per app, apply multiple policies to one app, or apply a policy to multiple apps.

## Adding Policy Configurations

To add a new policy to AI Guard:

1. In the AI Security Admin Portal left-side navigation, go to: **AI Guard** > **Policy >** **Configurations**. See image.
2. Click **Add More** to open the **Add New Configuration** page.
3. Under **Basic Information**, enter: See image.
  1. **Policy Name**
  2. (Optional) **Description**
4. Click **Continue to Detectors**. The **Prompt Detectors** tab opens.
5. On the **Prompt Detectors** tab, click on any of the prompt detectors you want to include with your policy. The **Configure** window appears.
6. Most prompt detectors share the following configuration options: See image.
  - **Show Details**: Gives a description and examples of the detector.
  - **Enabled**: Enables the prompt detector and is on by default. Click the checkbox to disable the prompt detector.
  - **Severity**: Corresponds to the severity icons in the **Prompt Detectors** column on the **Policies** page.
  - **Threshold**: The lower the threshold setting, the more strict AI Guard is with activating the policy (i.e., allowing, blocking, or detecting the prompt). Some prompt detectors such as **Text** and **Off Topic** don't include this option because those detectors activate with specific keywords, phrases, or topics.
  - **Action**: Select what you want the policy to do when activated for specific prompt detectors. **Allow** allows the prompt to proceed, **Block** blocks the prompt, and **Detect** flags the prompt as detected. Some detectors include a **Disabled** option to ensure what is disabled is not covered under that category's detections. In the **PII** detector, the **Person's name** category is defaulted to **Disabled**.
7. The following prompt detectors include additional configuration options: See image.
  - **Code**: Includes a list of **Programming Languages** where you select for your policy.
  - **Text**: Includes a **Regex Pattern** category where you enter a **Name** and the regular expression **Pattern** you want to add.
  - **Competition**: Includes a **Competitors**category where you enter your specific competitor names. Only 10 can be set at a time for a single policy.
  - **Language**: Includes a list of **Languages** where you select for your policy.
  - **Secrets**: Includes a list of secret types such as **API Keys** or **Tokens**.
  - **Off Topic**: Includes a field for adding whatever topic you want to be considered off topic in your policy, such as sports or music.
  - **PII**: Includes a list of personally identifiable information types such as **Person's name** and **Location**.
  - **Topic**: Includes the ability to add multiple custom topics to add to your policy. Enter a **Name** and **Topic Definition** for the topic you want to add. Only 10 can be set at a time for a single policy.
  - **Prompt Tags**: Includes specific categories to add to your policy, such as **News and Media**, **Shopping and Retail**, and **Technology**.
  - **Intellectual Property**: Includes a **Sensitive Context** field to enter what you want to avoid leaking, such as a project launch date.
8. After configuring the prompt detectors, click **Save Changes** to close the window and click **Next** on the **Prompt Detectors** tab. The **Response Detectors** tab opens.
9. Configuring response detectors follows similar steps as configuring prompt detectors, so refer back to the earlier steps for more information.
10. After configuring your response detectors, click **Next**. The **Review** tab opens. See image.
11. The **Review** tab shows you a summary of the policy configuration you created. Click **Submit Policy** if everything looks correct.

After creating a policy, the next step is to create a policy match. For more information on policy matching, see [Managing AI Guard Policy Control](https://help.zscaler.com/secure-ai-users/managing-ai-guard-policy-control).

## Configuration Actions

To view details of a policy configuration, under **Action**, click the **View Details** button. The **View Configuration Details** window appears and shows you the following information:

- **ID**
- **Name**
- **Description**
- **Controls**
- **Prompt Detectors**
- **Response Detectors**

To edit an AI Guard policy configuration:

1. Under the **Action** column, click the **Edit Configuration** icon. The **Edit Policy** page opens.
2. Make any desired changes to the policy. On the **Review** tab, click **Submit Policy** when finished. See image.

To copy a policy configuration:

1. Under the **Action** column, click the **Copy Configuration** icon. The **Copy Configuration** page opens.
2. Make any desired changes to the policy. On the **Review** tab, click **Submit Policy** when finished.

To delete a policy configuration:

1. Under the **Action** column, click the **Delete Configuration** icon. The **Delete Configuration** window appears. See image.
2. Type `Delete` into the text field to confirm the deletion of the policy, and then click the **Delete** button.

[Image: AI Guard Policies page with an example policy visible]

[Image: Basic policy information fields which includes Policy Name and Description]

[Image: Configuring detector window showing the common options available.]

[Image: Configure policy window for the Competition detector showing its unique configuration options.]

[Image: Review tab for adding a policy showing the information entered previously in the process]

[Image: Review page for adding a policy showing the information entered previously in the process]

[Image: AI Guard Policy Configuration Delete menu showing the name of the policy you want to delete, a field to type "Delete" into to confirm, and a Delete button to complete.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/ai-guard-audit-log","lastmod":"2026-07-21T13:16Z","nid":"1541874"} -->
## AI Guard Audit Log

- Source: https://help.zscaler.com/secure-ai-users/ai-guard-audit-log
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Monitoring > AI Guard Audit Log
- Last modified: 2026-07-21T13:16Z
- Summary: Learn how to view the AI Guard Audit Log.

The Audit Log page tracks any changes users made inside AI Guard. This can range from anything from updating a policy control to signing in.

To view the AI Guard Audit Log page:

1. Go to **AI Security Admin Portal** > **AI Guard** > **Audit Log**. See image.
2. The Audit Log page allows you to view the following information:
  1. **Timestamp**: The date and time that an action was logged.
  2. **Initiator**: The email address of the user that triggered the action.
    1. Click the **Search** button in the header to search for a specific initiator.
  3. **Action**: The action performed, such as: Update, View, or Create.
    1. Click the **Filter** button in the header to select which actions you want to see.
  4. **Entity Type**: More granular detail about the action. For example, an Action of Update may have an Entity Type of Policy Control, meaning that the user made an update to a policy control.
    1. Click the **Filter** button in the header to select which entity types you want to see.
3. For more information about the logged event, in the **Details** column, click the **View Request Details** button. See image.
4. The **Actions Details** window shows the following information:
  1. **Initiator**, **Action**, and **Entity Type**: The same information from the main Audit Log page.
  2. **Entity ID**: The ID number associated with the action.
  3. **Payload**: The code payload of the action.

[Image: AI Guard Audit Log page]

[Image: AI Guard Audit Log Action Details window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/ai-guard-latency","lastmod":"2026-07-24T11:45Z","nid":"1541854"} -->
## AI Guard Latency

- Source: https://help.zscaler.com/secure-ai-users/ai-guard-latency
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Troubleshooting > AI Guard Latency
- Last modified: 2026-07-24T11:45Z
- Summary: Learn how to review the latency between prompts, responses, and detections in AI Guard.

AI Guard is a service that provides run-time protection for your AI applications by enforcing enterprise policies that prevent prompt injections, block jailbreak attempts, and stop personal information leakage with guardrails around LLM interactions.

This process involves a user sending a request that is first analyzed by AI Guard to see if it triggers any detectors. If the prompt isn't blocked, the LLM provider then processes the prompt and returns a response to AI Guard for analysis. If the response isn't blocked, it is then sent to the user. Depending on the complexity of the prompt, speed of analysis, and internet speed, this can cause latency in the AI application interaction for the user. AI Guard tracks this information and includes it in the details of every logged transaction.

To access latency information:

1. Go to **AI Security Admin Portal** > **AI Guard** > **Dashboard**. The **Dashboard** page opens.
2. Find the transaction you want to see the latency information for and in its **Details** column, click the **View Details** button. The **Transaction Details** window appears. See image.
3. In the **Performance & Network Stats** section, you will find the following latency-related information about the transaction: See image.
  1. **Total Time Taken By LLM**: Time it took for the LLM provider to read the prompt and return a response.
  2. **Detection Time on Request**: Time it took for the AI Guard policy detectors to analyze the prompt.
  3. **Detection Time On Response**: Time it took for the AI Guard policy detectors to analyze the response.

[Image: AI Guard Dashboard with annotations around the View Details button]

[Image: Latency information for a specific transaction in AI Guard with annotations around the time based information]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/ai-guard-multilingual-support","lastmod":"2026-07-21T12:45Z","nid":"1541873"} -->
## AI Guard Multilingual Support

- Source: https://help.zscaler.com/secure-ai-users/ai-guard-multilingual-support
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Getting Started > AI Guard Multilingual Support
- Last modified: 2026-07-21T12:45Z
- Summary: This article contains a list of languages supported by the AI Guard interface.

AI Guard currently supports the following languages with more being added in the future:

- English
- French
- German
- Japanese
- Portuguese
- Spanish

As more prompt and response detector categories are added, they may not support all languages at this time. You can see at-a-glance which detector categories support all languages by finding the **Multilingual** badge on their tile. Hovering your mouse cursor over it will show which languages are supported by the specific detector.

See image.

To learn more about prompt and response detectors, see [Adding and Managing AI Guard Policy Configurations](https://help.zscaler.com/secure-ai-users/adding-and-managing-ai-guard-policy-configurations).

[Image: Prompt Detector Configuration page showing the Multilingual support badge and pop-up list of languages]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/ai-guard-policy-testing","lastmod":"2026-07-19T17:45Z","nid":"1540884"} -->
## AI Guard Policy Testing

- Source: https://help.zscaler.com/secure-ai-users/ai-guard-policy-testing
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Troubleshooting > AI Guard Policy Testing
- Last modified: 2026-07-19T17:45Z
- Summary: Learn how to test a policy in AI Guard.

After adding a large language model (LLM) provider, LLM provider credentials, and a policy to AI Guard, you can test your policy to make sure it is working to your satisfaction. To learn more, see [Adding and Managing AI Guard Policies](https://help.zscaler.com/secure-ai-users/adding-and-managing-ai-guard-policy-configurations) and [Managing AI Guard Policy Control](https://help.zscaler.com/secure-ai-users/managing-ai-guard-policy-control).

To test an AI Guard policy:

1. In the AI Guard left-side navigation, click **Policy Testing**. The **Policy Testing** page appears. See image.
2. Enter the following information:
  - **Provider Credential**: From the drop-down menu, select an LLM credential.
  - **Policy**: From the drop-down menu, select a policy you want to test.
  - **LLM Model**: From the drop-down menu, select the LLM model you want to use.
  - **Prompt**: Enter a prompt that you want to test.
3. Click **Send** to test the policy. Results appear below the prompt. As an example, you could test a policy that blocks Spanish in both the prompts and the responses. If a user enters, `How do I say "what is the weather" in Spanish?`, the prompt is allowed because the prompt is in English, but the policy blocks the response because the answer is in Spanish. In addition to the policy test showing you what is allowed or blocked, it also shows you the prompt and response without guardrails. See image.

[Image: AI Guard Policy Testing page with an example prompt entered.]

[Image: AI Guard Policy Testing page showing the results of an example prompt]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/best-practices-runbook-competition-detector-0","lastmod":"2026-07-24T11:42Z","nid":"1541880"} -->
## Best Practices Runbook: Competition Detector

- Source: https://help.zscaler.com/secure-ai-users/best-practices-runbook-competition-detector-0
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Configuration > Best Practices > Best Practices Runbook: Competition Detector
- Last modified: 2026-07-24T11:42Z
- Summary: Learn the best practices for configuring the Competition prompt/response detector for AI Guard as well as explanations on how it works, example customer scenarios, and troubleshooting.

AI Guard works by enforcing enterprise policies on prompts and responses between users and public AI apps, such as ChatGPT, Perplexity, Claude, etc., and between private AI apps and foundational Large Language Models (LLMs), such as OpenAI, Anthropic, etc. You set a policy by enabling one or more included detectors on prompts and responses. These detectors are activated on prompts and responses based on the policies you define in the portal.

The Competition Detector flags user prompts that reference monitored competitors, with built-in tolerance for name misspellings, ticker symbol usage, and multilingual input.

This best practices runbook contains the following information on the Competition detector:

- How the Competition detector works.
- How to configure the detector.
- Choosing the correct threshold.
- Troubleshooting and FAQ.

The Competition detector watches every prompt a user sends to an LLM and flags the prompt when it mentions a competitor. The user provides a list of competitor names; AI Guard does the rest.

The Competition detector catches the following:

- **Plain mentions**: "How does Veltroxmax Motors's autopilot compare to our system?" triggers on competitor Veltroxmax.
- **Ticker symbols**: "VTRX earnings looked strong this quarter" triggers on competitor Veltroxmax (ticker VTRX).
- **Typos and OCR artifacts**: "Veltroxmax Motors's new model is fast" still triggers on Veltroxmax when there is business context.
- **Other languages**: "die bleep bloop netwerke firewall is uitstekend" (Afrikaans) triggers on Bleep Bloop Networks.
- **Phonetic spellings**: "the veltrohmax is fast" triggers on Veltroxmax.

The Competition detector does not trigger on the following:

- **Common-word false positives**: "I walked around the city block" does not trigger competitor Block.
- **Fictional or unrelated references**: "Peter Pan is a beloved character" does not trigger competitor Pan.
- **Idioms and wordplay**: "I've had writer's block all week" does not trigger Block.
- **Landmarks**: "Times Square was crowded with tourists" does not trigger Square.
- **Generic vocabulary**: "We are out of stock for this item" does not trigger Stock.

AI Guard makes the distinction because every potential match must have evidence that it refers to a company, not just a common word.

No evidence means there is no trigger, regardless of length or spelling.

To configure the Competition detector in an AI Guard policy:

1. In the AI Security Admin Portal left-side navigation, go to: **AI Guard** > **Policy >** **Configurations**.
2. Click **Add More** to open the **Add New Configuration** page. To edit an existing policy, in the **Action** column, click **Edit Configuration** for a policy.
3. Under **Basic Information**, enter: See image.
  1. **Policy Name**
  2. (Optional) **Description**
4. Click **Continue to Detectors**. The **Prompt Detectors** tab opens.
5. On the **Prompt Detectors** tab, click on the **Competition** tile. The **Configure** window appears.
6. The following configuration options are the intitial recommendations for the Competition detector: See image.
  - **Enabled**: Enables the prompt detector and is on by default. Leave **Enabled**.
  - **Severity**: Corresponds to the severity icons in the **Prompt Detectors** column on the **Policies** page. The recommended setting is LOW for initial deployments. Raise to MEDIUM/HIGH only after a user confirms the false-positive rate is acceptable.
  - **Threshold**: The lower the threshold setting, the more strict AI Guard is with activating the policy (i.e., allowing, blocking, or detecting the prompt). Keep the default (**0.7**) for production. For more information, refer to the next section.
  - **Default State**: What happens by default when the detector triggers: **Detect** (log only), **Allow** (pass through), or **Block** (stop the prompt).
  - **Competitors**: The list of up to 10 names to watch for. Each entry has its own **Action** override (**Allow** / **Block** / **Detect**). Start with three to five high-value competitors. The more names you enter, the more noise and errors may appear. Fewer competitors is usually better.
    - **Competitor naming tips**: Use the common short form of the name: "**Veltroxmax**," not "**Veltroxmax Motors**" or "**Veltroxmax Corp.**" The detector already knows about corporate suffixes (Inc, Corp, Ltd). For multi-word names like **Bleep Bloop Networks**, use the full name. The detector handles partial matches automatically.
  - **Action (per Competitor)**: Select what you want the policy to do when activated for specific prompt detectors.
7. After configuring the prompt detectors, click **Save Changes** to close the window and click **Next** on the **Prompt Detectors** tab. The **Response Detectors** tab opens.
8. Configuring response detectors follows the same steps as configuring prompt detectors, so refer back to the earlier steps for more information.
9. After configuring your response detectors, click **Next**. The **Review** tab opens. See image.
10. The **Review** tab shows you a summary of the policy configuration you created. Click **Submit Policy** if everything looks correct.
11. In the AI Security Admin Portal left-side navigation, go to: **AI Guard** > **Policy Testing**.
12. Validate in **Policy Testing** with five to ten sample prompts to confirm your setup is working as expected. To learn more about policy testing, see [AI Guard Policy Testing](https://help.zscaler.com/secure-ai-users/ai-guard-policy-testing).

After creating a policy and confirming it works as expected, the next step is to create a policy match. For more information on policy matching, see [Managing AI Guard Policy Control](https://help.zscaler.com/secure-ai-users/managing-ai-guard-policy-control).

The **threshold** is a number between 0.0 and 1.0 that controls how confident the model must be before it calls a prompt a match. Think of it as a volume knob: turn it up and only very obvious mentions trigger. Turn it down and more borderline cases trigger too.

The default is **0.7**. This was chosen based on Zscaler's internal validation set, a benchmark spanning 28 languages and roughly 9,000 labeled prompts covering both real competitor mentions and tricky false-positive cases (city block, writer's block, out of stock, Times Square, Peter Pan, etc.). At 0.7 the detector hits the sweet spot between catching real mentions and avoiding false alarms.

Zscaler strongly recommends that you leave the threshold at **0.7**. We built and validated the detector around this default.

Refer to the following table when considering changing the threshold:

| Range | What it means | When to use it |
| --- | --- | --- |
| 0.85 to 1.00 | Only unambiguous mentions trigger. The competitor's name is written clearly, in business context, and the model is highly confident. | If you are very sensitive to false positives. They would rather miss borderline cases than pay someone to review noise. Common for initial Block-mode roll-outs. |
| 0.70 to 0.84 | Default sweet spot. Catches real mentions including typos, tickers, and other languages. Rejects common-word false positives. | Production deployments. Start here unless you need tighter or looser behavior. |
| 0.50 to 0.69 | Also fires on semantically adjacent cases: prompts that mention the competitor only indirectly or use ambiguous wording. | Investigation/audit mode where you want to see anything the model thinks is even plausibly related. Not recommended for Block mode, the false-positive rate is meaningfully higher. |
| Below 0.50 | Very permissive. Loose or tangential mentions trigger. | Not recommended for production. Useful only for internal debugging when you want to see the full score distribution. |

### Troubleshooting

If the Competition detector isn't triggering correctly, run through the following checklist:

- **Is the detector actually Enabled?** Toggle in the UI, not just added competitors.
- **Is the competitor name configured correctly?** Check spelling and spacing.
- **Is the prompt lowercase with no business context?** "I like oracle" (no business words, no capital) will not trigger. This is intentional, otherwise every mention of the word oracle in mythology or fiction would trigger the detector.
- **Is the threshold unusually high?** Check that the threshold is 0.7, not 0.9.
- **Is the policy test tool accurate?** When using Policy Testing, confirm the test prompt is being run through the same policy you've has deployed. A different policy won't use the same competitor list.
- **Is the prompt very short?** Prompts under ~10 characters don't give the model enough context to classify reliably.
- **Is the word being used as a proper noun in a business context?** If the prompt literally says "Oracle's Q3 earnings beat estimates", this is a correct detection.
- **Is the competitor's name also a common English word?** Common English words are all prone to being used as ordinary words and as company names. The detector uses business context to tell them apart, but rare context mismatches happen.
- **Is the score close to threshold (0.70 – 0.75)?** Borderline scores are expected to have both true and false positives. Raising the threshold to 0.80 will cut most of these but also drop real matches.
- **File a ticket with Zscaler Support for persistent false positives.** Include the following information in your ticket:
  - Exact prompt text.
  - Configured competitor name.
  - Confidence score from scoreByCompetitor.
  - Tenant ID.
  - Whether the prompt was in English or another language.

### FAQs

- **How many competitors can you configure?** Up to 10 per policy. Beyond that, noise and latency increase. If you needs more than 10, they probably want the Topic detector instead — it handles conceptual categories better.
- **Can you configure the same competitor list across policies?** Yes. Each policy has its own Competition configuration. Copy-paste the competitor list when duplicating a policy.
- **How does the detector handle tickers I haven't told it about?** The detector ships with a built-in ticker map covering the largest tech and finance tickers. If you have a competitor with a ticker that isn't in the map, it'll still try fuzzy matching, but matches are less reliable. File a ticket to add new tickers.
- **Does it work on non-English prompts?** Yes. 28 languages are supported out of the box. Business-context keywords are built in for major Germanic, Romance, Nordic, Slavic, Greek, Turkish, and Southeast Asian languages. Capitalization evidence (i.e., the name of the competitor capitalized) is English-only; in other languages the detector relies on business context alone.
- **Can the detector handle hyphens, apostrophes, and possessives?** Yes. All of these are supported.
- **What if you wants to allowlist certain prompts?** Not supported at the Competition-detector level. Use policy-level exceptions (exclude a user group, restrict to certain apps) or a sentence-level allow rule if you have those. To learn more, see [Manage Prompt Allowlist](https://help.zscaler.com/secure-ai-users/managing-prompt-allowlist).
- **Can I export the detection log for analysis?** Yes. In the AI Security Admin Portal, go to **AI Guard** > **Log Exports**. The Competition detector's triggers are tagged with the per-competitor scoreByCompetitor dict, so you can pivot on individual competitors in a BI tool or spreadsheet.
- **How often is the underlying model updated?** Retrained periodically as new false-positive patterns surface. Updates are rolled out transparently, no action needed. Check release notes for version updates.

[Image: Competitor detector configuration page]

[Image: Basic policy information fields which includes Policy Name and Description]

[Image: Review tab for adding a policy showing the information entered previously in the process]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/best-practices-runbook-topicoff-topic-detectors-1","lastmod":"2026-08-24T08:57Z","nid":"1541879"} -->
## Best Practices Runbook: Topic/Off Topic Detectors

- Source: https://help.zscaler.com/secure-ai-users/best-practices-runbook-topicoff-topic-detectors-1
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Configuration > Best Practices > Best Practices Runbook: Topic/Off Topic Detectors
- Last modified: 2026-08-24T08:57Z
- Summary: Learn the best practices for configuring the Topic and Off Topic prompt/response detectors for AI Guard as well as explanations on how it works, example customer scenarios, and troubleshooting.

AI Guard works by enforcing enterprise policies on prompts and responses between users and public AI apps, such as ChatGPT, Perplexity, Claude, etc., and between private AI apps and foundational Large Language Models (LLMs), such as OpenAI, Anthropic, etc. You set a policy by enabling one or more included detectors on prompts and responses. These detectors are activated on prompts and responses based on the policies you define in the portal.

The **Topic** detector triggers when a user's prompt matches a topic you care about: salary discussions, medical questions, weapons, legal matters, etc., even when phrased indirectly or in another language. The Off-Topic detector does the inverse: it triggers when the prompt does not match any configured topic.

This best practices runbook contains the following information on the Topic/Off Topic detectors:

- How the Topic/Off Topic detectors works.
- How to configure the detector.
- Choosing the correct threshold.
- Example customer scenarios.
- Troubleshooting and FAQ.

The Topic detector (also called “On-Topic”) monitors every prompt a user sends to the LLM and flags the prompt when it's semantically related to any configured topics. The user provides a topic name and a topic definition; the detector does the rest.

The Off-Topic detector monitors the same prompt stream but triggers when the prompt does not match any configured topic. If a user defines “Customer Support” as a topic, any prompt that relates to sports, cooking, or politics will be flagged.

The Topic/Off Topic detectors catch the following:

- **Direct matches**: “What are the side effects of insulin?” triggers a Medical topic defined as “diabetes, thyroid and cancer.”
- **Indirect / semantic matches**: “How much does a senior engineer make at Google?” triggers a Salary topic even though the word “salary” isn’t in the prompt.
- **Keyword lists**: “The NBA finals were incredible this year” triggers a Leisure topic defined as “sports, religion, politics, gaming.” Each keyword is evaluated independently.
- **Multilingual prompts**: Prompts in any of 28 supported languages are handled natively. No extra configuration needed.
- **Paraphrased or indirect phrasing**: “Can you compare compensation packages between Amazon and Meta?” triggers a Salary topic even though it uses “compensation” not “salary.”

The Topic/Off Topic detectors does not do the following:

- It does not perform keyword matching by exact substring. It uses semantic understanding, so “block” in “writer’s block” won’t trigger a topic about blockchain.
- It does not chunk long prompts. Mentions buried deep in very long prompts may be missed.
- It does not replace content filtering (toxicity, PII, jailbreak). Topic detection is about subject matter, not safety.

#### When to use which detector

Both detectors use the same underlying model and the same topic definitions. The only difference is when they are triggered:

|  | Topic (On Topic) | Off Topic |
| --- | --- | --- |
| Triggers when... | Prompt is about a configured topic. | Prompt is not about any configured topic. |
| Use case | Block or log sensitive subjects (salary, medical, weapons, legal). | Keep users on task (only allow topics you’ve defined). |
| Typical action | Block or Detect on match. | Block or Detect when off topic. |
| Example | **Topic**: Salary **Prompt**: “How much do nurses earn?” **Result**: Triggered | **Topic**: Salary **Prompt**: “How much do nurses earn?” **Result**: Triggered |
| Aggregation | **OR**: Triggers if any topic matches | **AND**: Triggers only if no topic matches |

If you only want user to talk about X, Y, and Z, then you should use the Off-Topic detector. If you only want to block users from talking about A or B, then you should use the Topic detector.

### Topic Name and Definition

Each topic is a combination of a **Topic Name** and a **Topic Definition**. Both contribute to what the detector matches; the name provides additional semantic context to the model, so the name should be related to the definition.

- **Topic Name**: Should clearly describe the category being detected. Examples: “Medical”, “Salary”, “Weapons”, “Legal Compliance”.
- **Topic Definition**: Choose one of the following two formats:
  - **Natural Language**: Describe what you want to detect in plain language. Example: “We want to avoid discussions related to diabetes, thyroid and cancer.” Natural language descriptions work best for broad semantic matching.
  - **Keyword List**: Enter comma-separated keywords related to the topic name. Example: “diabetes, thyroid, cancer.” Each keyword is evaluated independently. Keyword lists work best for precise, specific term matching.
- **Definition Tips**:
  - Use multi-word, descriptive definitions rather than short abstract phrases.
  - A single-word definition like “Manager” is too vague, it will match almost anything conversational. Use “Questions about management practices, team leadership, and managerial responsibilities” instead.
  - For common or broad terms, prefer natural language format over single keywords. Keyword lists are sensitive to ambiguous single-word terms.
  - Avoid parentheses or regex patterns in definitions.
  - Topic name and definition should be related; the name provides additional semantic context to the model.

### Configuration

To configure the Topic/Off Topic detectors in an AI Guard policy:

1. In the AI Security Admin Portal left-side navigation, go to: **AI Guard** > **Policy >** **Configurations**.
2. Click **Add More** to open the **Add New Configuration** page. To edit an existing policy, in the **Action** column, click **Edit Configuration** for a policy.
3. Under **Basic Information**, enter: See image.
  1. **Policy Name**
  2. (Optional) **Description**
4. Click **Continue to Detectors**. The **Prompt Detectors** tab opens.
5. On the **Prompt Detectors** tab, click on the **Topic**or**Off Topic** tile. The **Configure** window appears.
6. The following configuration options are the initial recommendations for the Topic/Off Topic detectors: See image.
  - **Enabled**: Enables the prompt detector and is on by default. Leave **Enabled**.
  - **Severity**: Corresponds to the severity icons in the **Prompt Detectors** column on the **Policies** page. The recommended setting is LOW for initial deployments. Raise to MEDIUM/HIGH only after a user confirms the false-positive rate is acceptable.
  - **Threshold**: The lower the threshold setting, the more strict AI Guard is with activating the policy (i.e., allowing, blocking, or detecting the prompt). Keep the default for production. For more information, refer to the next section.
  - **Default State**: What happens by default when the detector triggers: **Detect** (log only), **Allow** (pass through), or **Block** (stop the prompt).
  - **Custom Topic (Topic) or Topic Definition (Off Topic)**: Up to 10 topics. Each has a Name and a Definition. Refer to the previous Topic Name and Definition section for more information.
  - **Action**: Select what you want the policy to do when activated for specific prompt detectors. **Allow** allows the prompt to proceed, **Block** blocks the prompt, and **Detect** flags the prompt as detected.
7. After configuring the prompt detectors, click **Save Changes** to close the window and click **Next** on the **Prompt Detectors** tab. The **Response Detectors** tab opens.
8. Configuring response detectors follows the same steps as configuring prompt detectors, so refer back to the earlier steps for more information.
9. After configuring your response detectors, click **Next**. The **Review** tab opens. See image.
10. The **Review** tab shows you a summary of the policy configuration you created. Click **Submit Policy** if everything looks correct.
11. In the AI Security Admin Portal left-side navigation, go to: **AI Guard** > **Policy Testing**.
12. Validate in **Policy Testing** with five to ten sample prompts to confirm your setup is working as expected. To learn more about policy testing, see [AI Guard Policy Testing](https://help.zscaler.com/secure-ai-users/ai-guard-policy-testing).

After creating a policy and confirming it works as expected, the next step is to create a policy match. For more information on policy matching, see [Managing AI Guard Policy Control](https://help.zscaler.com/secure-ai-users/managing-ai-guard-policy-control).

The **threshold** is a number between 0.0 and 1.0 that controls how confident the model must be before it calls a prompt a match. Similar to a volume knob; turning it up will trigger only very obvious mentions. Turn it down and more borderline cases trigger.

The default is **0.7**. This was chosen based on Zscaler's internal validation set, a benchmark spanning 28 languages and roughly 9,000 labeled prompts covering both real competitor mentions and tricky false-positive cases (city block, writer's block, out of stock, Times Square, Peter Pan, etc.).

Zscaler strongly recommends that you leave the threshold at **0.7**. We built and validated the detector around this default.

Refer to the following table when considering changing the threshold:

| Range | What it means | When to use it |
| --- | --- | --- |
| 0.80 to 1.00 | Only strong, direct matches trigger. The content explicitly discusses the subject using closely related terminology. | If you are very sensitive to false positives. You would rather miss borderline cases than pay someone to review noise. Common for initial Block-mode roll-outs. |
| 0.5 to 0.7 | Semantically related content triggers, even with different phrasing or indirect discussion. Adjacent concepts within the same domain match. | Recommended range for most production deployments. Start at 0.7. |
| Below 0.50 | Loose or tangential connections trigger. Content may mention the subject in passing or share vocabulary from the same domain without being truly about it. | Not recommended for production. Useful only for internal debugging when you want to see the full score distribution. |

### What Threshold Doesn't Fix

The following are situations that the threshold setting does not fix:

- **Too many false positives.** Before raising the threshold, look at the actual false positives. If the topic definition is a single vague word (e.g., “Manager”), improve the definition first.
- **It doesn’t catch indirect mentions.**Lowering the threshold can help slightly, but the real fix is to write a better topic definition using natural language.
- **Want to block mentions of salary but not pay grade.**Threshold won’t help. Refine the topic definition to be more specific about what constitutes a salary discussion.

### Scenario A: Enterprise Blocking Salary Discussions

#### Situation

A large enterprise wants to prevent employees from using their internal LLM chatbot to discuss salary or compensation information.

#### Configuration (Topic Detector)

Use the following policy configurations for the Topic detector:

- **Enabled**: ON
- **Severity**: LOW
- **Threshold**: Keep Default
- **Default State**: Block
- **Topic**:
  - **Name**: “Salary”
  - **Definition**: “A salary is a fixed, regular payment made by an employer to an employee, typically expressed as an annual sum and paid monthly or biweekly, in exchange for work performed, regardless of hours worked.”
  - **Action**: Block

This works because the natural language definition gives the model rich semantic context. Prompts like “How much does a senior engineer make at Google?”, “Compare compensation packages between Amazon and Meta”, and “What is the minimum wage in California?” all trigger correctly. Meanwhile, unrelated prompts like “Good morning”, “What is 2+2?”, and “Generate a French poem” do not trigger.

### Scenario B: Healthcare Chatbot Stays On Topic

#### Situation

A hospital’s patient-facing chatbot should only answer questions about healthcare. Any off-topic prompts (sports, politics, cooking) should be blocked.

#### Configuration (Off Topic Detector)

Use the following policy configurations for the Off Topic detector:

- **Enabled**: ON
- **Severity**: LOW
- **Threshold**: Keep Default
- **Default State**: Block
- **Topic 1**:
  - **Name**: “Healthcare”
  - **Definition**: “We want to allow discussions related to medical conditions, treatments, medications, symptoms, diagnoses, and healthcare procedures.”
- **Topic 2**:
  - **Name**: “Administrative”
  - **Definition**: “Appointment scheduling, insurance, billing, and patient intake.”

The Off Topic detector fires when the prompt does not match any configured topic. A question about diabetes matches Healthcare, so there is no trigger (allowed). A question about the NBA finals matches neither topic, so it is triggered (blocked).

### Scenario C: Block Some Topics, Detect Others (Mixed Policy)

#### Situation

A financial services firm wants to block salary discussions entirely but only log (detect) when users ask about management practices.

#### Configuration (Topic Detector)

Use the following policy configurations for the Topic/Off Topic detectors:

- **Enabled**: ON
- **Severity**: LOW
- **Threshold**: Keep Default
- **Default State**: Detect
- **Topic 1**:
  - **Name**: “Salary”
  - **Definition**: “Salary, compensation, pay, wages, bonuses, and total rewards”,
  - **Action**: Block
- **Topic 2**:
  - **Name**: “Management”
  - **Definition**: “Management practices, team leadership, managerial responsibilities, and people management”
  - **Action**: Detect

Each topic can have its own action override, so you can mix Block and Detect within the same policy.

### Scenario D: Multilingual Deployment

#### Situation

A global company has offices in Germany, Japan, and Brazil. They want to detect medical topics across all languages.

#### Configuration

Same as any other deployment, no extra language configuration needed. Define the topic in English; the detector handles 28 languages natively. A German prompt “Was sind die Nebenwirkungen von Insulin?” (What are the side effects of insulin?) triggers the Medical topic correctly.

### Troubleshooting

If the Topic/Off Topic detectors aren't triggering correctly, run through the following checklist:

- **Is the detector actually Enabled?** Check the toggle in the UI.
- **Is the topic definition too short or vague?**A definition of just “Manager” matches everything loosely. Use a full natural language description.
- **Is the topic definition too narrow?** If you defined “diabetes” and the user asks about “insulin resistance,” the model should still match. If it doesn’t, broaden the definition: “diabetes, insulin, blood sugar management.”
- **Is the threshold too high?** Check that it’s 0.7, not 0.9.
- **Is the prompt very short?**Prompts under ~10 characters don’t give the model enough context.
- **Is the policy test tool running against the correct policy?**A different policy won’t use the same topic list.

If the Topic/Off Topic detectors are triggering when they shouldn't, run through the following checklist:

- **Is the topic definition a single common word?** “Manager,” “Sports,” or “Block” as a standalone definition will match too broadly. An example of a better definition for "Sports" would be "athletes, sports teams, sports scores, and tickets."
- **Is the content actually related to the topic?** “Pay ranges for a product manager role” legitimately matches both Salary and Manager topics. That may be correct.
- **Is the confidence score borderline (0.70–0.75)?** Raising the threshold to 0.80 will cut most borderline matches but also drop some real ones.
  - Gather 5–10 example false-positive prompts and file a ticket with the ML team.

Common issues:

- **Single-word definitions**: “Manager” as a topic definition is too vague. The model treats any conversational prompt as potentially about a manager. Always use multi-word, descriptive definitions.
- **Conflating Topic and Off Topic**: If you want to block salary discussions, use the Topic detector (triggers on match). If they want to force users to stay on topic, use Off Topic (triggers when no match).
- **Too many topics**: More than 10 topics per policy increases noise and latency. If you need conceptual categories, use fewer broader topics rather than many narrow ones.

### FAQs

- **How many topics can you configure?** Up to 10 per policy. Beyond that, noise and latency increase. If more are needed, use fewer broader topics.
- **Can the same topics be used across policies?** Yes. Each policy has its own Topic / Off-Topic configuration. Copy-paste topic names and definitions when duplicating.
- **Does it work on non-English prompts?** Yes. 28 languages are supported out of the box, including Arabic, Chinese, French, German, Hindi, Japanese, Korean, Portuguese, Russian, Spanish, Thai, Turkish, Vietnamese, and more. No extra configuration needed.
- **What happens if a prompt is very long?** Very long prompts are not chunked. If critical content is in the tail, recommend shorter prompts.
- **Can I use both Topic and Off Topic on the same policy?** Yes, and this is a common pattern. For example: use Topic to block salary discussions, and Off Topic to block anything that isn’t about customer support. They evaluate independently.
- **What’s the difference between Topic Name and Topic Definition?** Both contribute to matching. The name gives the model semantic context (“Medical” tells the model we’re in the healthcare domain), and the definition tells it exactly what to look for (“diabetes, thyroid, cancer”). Always make them related.
- **Can I use regex or patterns in the definition?** No. Avoid parentheses, regex syntax, or special characters. Use plain natural language or comma-separated keywords.
- **How do natural language definitions differ from keyword lists?** Natural language (“We want to avoid discussions related to diabetes, thyroid and cancer”) works best for broad semantic matching. Keyword lists (“diabetes, thyroid, cancer”) work best for precise, specific term matching. Each keyword in a list is evaluated independently.
- **Can I see what the model sees?** The detection log contains the full response JSON, including confidence scores per topic. That’s the same data the policy engine uses.
- **How often is the model updated?** Updated periodically as new patterns surface. Updates are rolled out transparently, no action needed.
- **What if you want to allowlist certain prompts?** Not supported at the detector level. Use policy-level exceptions (exclude a user group, restrict to certain apps).

[Image: Off topic detector configuration page]

[Image: Basic policy information fields which includes Policy Name and Description]

[Image: Review tab for adding a policy showing the information entered previously in the process]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/configuring-custom-block-messages","lastmod":"2026-07-21T12:51Z","nid":"1541915"} -->
## Configuring Custom Block Messages

- Source: https://help.zscaler.com/secure-ai-users/configuring-custom-block-messages
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Configuration > Configuring Custom Block Messages
- Last modified: 2026-07-21T12:51Z
- Summary: Learn how to create custom block messages for prompts and responses in AI Guard.

Whenever a prompt or response is blocked by an AI Guard policy, a custom message can be shown in its place. This custom block message can be used to provide a link to the user to notify them about AI usage policy of your organization.

To create a custom block message:

1. Go to **AI Guard** > **Tenant Settings**.
2. Go to the **Security** tab.
3. In the **Custom Block Message** section you will find the following: When configuring a custom block message, the message will be sent to the LLM asking it to return the block message to the user. Certain LLMs may misinterpret the instructions as malicious and either refuse to answer or provide an unexpected response. See image.
  - **Custom Prompt Block Message**: Enter a message that users will see when their prompt is blocked.
  - **Custom Response Block Message**: Enter a message that users will see when their LLM response is blocked.
  - **Delete Conversation on Response Block**: Select if you want to automatically delete chat history of response blocking cases for providers.
4. Click **Save**.

[Image: AI Guard Tenant Settings page with the Custom Block Message section displayed]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/integrating-zia-ai-guard","lastmod":"2026-08-27T07:55Z","nid":"1540870"} -->
## Integrating ZIA with AI Guard

- Source: https://help.zscaler.com/secure-ai-users/integrating-zia-ai-guard
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Getting Started > Integrating ZIA with AI Guard
- Last modified: 2026-08-27T07:55Z
- Summary: Learn how to forward traffic from ZIA to AI Guard for processing AI traffic for AI users.

Thanks to the integration of AI Guard with Internet & SaaS (ZIA), you can configure ZIA to invoke AI Guard for processing user's AI traffic.

## Prerequisites

Before you can integrate ZIA with AI Guard, ensure that you:

- Have an AI Guard subscription.
- Have linked ZIA and AI Guard. Contact Zscaler Support to ensure they are linked.
  - AI Guard can only be mapped to a single tenant and a single domain. For example, if `acme.com` exists in both the QA and Production tenant, AI Guard can map to either the QA or Production tenant, but not both.
- Have Experience Center enabled for your tenant. Instructions for ZIA-related configuration assume that it will be performed via Experience Center.
- Have the CA certificate of the AI Guard endpoint. Contact Zscaler Support if you need help getting it.
- Are using a supported generative AI application. The following table lists the supported AI applications (last updated: July 02, 2026); use the page controls at the bottom-right of the table to view all providers:
  | Application | Client Types | Domains Required |
  | --- | --- | --- |
  | Anthropic (Claude) | Web Browsers; Claude Desktop; Claude CLI; Claude Code for VS Code; Claude MS Add-in (Excel/PPT) | `*.claude.ai` `*.api.anthropic.com` |
  | Bedrock-Anthropic | Web Browsers; CLI | Enter the domain for your region: `bedrock-runtime.ap-northeast-1.amazonaws.com` `bedrock-runtime.ap-northeast-2.amazonaws.com` `bedrock-runtime.ap-south-1.amazonaws.com` `bedrock-runtime.ap-southeast-1.amazonaws.com` `bedrock-runtime.ap-southeast-2.amazonaws.com` `bedrock-runtime.ca-central-1.amazonaws.com` `bedrock-runtime.eu-central-1.amazonaws.com` `bedrock-runtime.eu-west-1.amazonaws.com` `bedrock-runtime.eu-west-2.amazonaws.com` `bedrock-runtime.eu-west-3.amazonaws.com` `bedrock-runtime.sa-east-1.amazonaws.com` `bedrock-runtime.us-east-1.amazonaws.com` `bedrock-runtime.us-east-2.amazonaws.com` `bedrock-runtime.us-gov-east-1.amazonaws.com` `bedrock-runtime.us-west-2.amazonaws.com` `bedrock-runtime.ap-northeast-3.amazonaws.com` |
  | Bolt.new | Web Browsers | `*.bolt.new` |
  | Builder.io | Web Browsers | `*.api.builder.io` `*.cdn.builder.io` `*.cdn.builder.codes` |
  | DeepAI | Web Browsers | `*.api.deepai.org` |
  | Gamma | Web Browsers | `*.api.gamma.app` `*.ai.api.gamma.app` |
  | ElevenLabs | Web Browsers: Text-to-speech supported | `api.us.elevenlabs.io` |
  | GitHub Copilot | Web Browsers | `api.individual.githubcopilot.com` |
  | GitHub Copilot Enterprise | Web Browsers | `api.business.githubcopilot.com` `api.enterprise.githubcopilot.com` `api.individual.githubcopilot.com` |
  | Glean | Web Browser Chat | `*.glean.com` |
  | Google Gemini | Web Browsers | `*.gemini.google.com` |
  | Google Gemini Code | Web Browsers | `*.gemini.google.com` `cloudcode-pa.googleapis.com` |
  | Google Gemini Enterprise | Web Browsers | `*.biz-discoveryengine.googleapis.com` `*.content-us-discoveryengine.googleapis.com` `*.discoveryengine.clients6.google.com` `*.us-discoveryengine.clients6.google.com` `*.eu-discoveryengine.clients6.google.com` `*.global-discoveryengine.clients6.google.com` `*.us-discoveryengine.googleapis.com` `*.eu-discoveryengine.googleapis.com` |
  | Google Gemini Workspaces | Google Suite (Docs, Slides, Sheets): "Ask Gemini" and "Generate document" are supported.; "Help me write," "Refine," and "Refine the selected text" functionality is not supported. | `appsgenaiserver-pa.clients6.google.com` |
  | Grok (xAI) | Web Browsers | `*.grok.com` |
  | Lovable | Web Browsers | `api.lovable.dev` |
  | MaxAI | Web Browsers | `*.api.maxai.me` |
  | Microsoft 365 Copilot | M365 Web Experience (Web Browser); M365 Desktop App (Windows and Mac); Copilot.com (Web Browser); Copilot in Word (Desktop App and Web Browser); Copilot in Excel; Copilot in Outlook (Web Browser) | `substrate.office.com` `substrate.svc.cloud.microsoft` `copilot.microsoft.com` `api.business.githubcopilot.com` `api.enterprise.githubcopilot.com` `api.individual.githubcopilot.com` `augloop.office.com` www.`copilot.com` |
  | Mistral AI | Mistral Vibe (formerly Le Chat) backend | `*.mistral.ai` |
  | Napkin AI | Web Browsers | `*.app.napkin.ai` |
  | NotebookLM | Web Browsers | `*.notebooklm.google.com` |
  | OpenAI (ChatGPT, Codex) | Web Browsers; Desktop App (Windows and Mac) | `*.chatgpt.com` `*.chat.openai.com` |
  | OpenCode | App; CLI | `*.opencode.ai` |
  | Perplexity | Web Browsers; Perplexity Desktop App; Comet Browser | `*.perplexity.ai` |
  | Vertex AI | Web Browsers | `*.cloudconsole-pa.clients6.google.com` |
  | Windsurf | Desktop App (Proxy Mode Only).Custom Block Messages are not currently supported with this provider. | `server.self-serve.windsurf.com` `server.codeium.com` |

## Configuring ZIA

- Uploading your AI Guard CA certificate to ZIA
- Configuring the ZIA Proxy
- Creating a rule for ZIA to drop QUIC traffic
- Creating a wildcard destination group and forwarding rule

## Configuring AI Guard

AI users defined in Authentication Service show up automatically in AI Guard after you have configured ZIA successfully. Groups are not yet automatically populated. You can define policies in **Policy Match** for both individual users and groups you've created. The default group is **AllUsersGroup**.

See image.

To upload your AI Guard CA certificate to ZIA:

1. In the Zscaler Admin Console, go to **Infrastructure** > **Internet & SaaS** > **Network Policies** > **Root Certificates**.
2. Click **Add Root Certificates**. The **Add Root Certificate** window appears. See image.
3. In the **Add Root Certificate** window:
  1. **Name**: Enter a name for your certificate.
  2. **Type**: From the drop-down menu, select **Proxy Chaining**.
  3. **File**: Browse and select the AI Guard CA certificate (`.pem` file) from your system.
4. Click **Save**.

To configure your ZIA proxy:

1. In the Zscaler Admin Console, go to **Infrastructure** > **Internet & SaaS** > **Network Policies** > **Proxies and Gateways**.
2. In the **Proxies** tab, click **Add Proxy**. The **Add Proxy** window appears. See image.
3. In the **Add Proxy** window:
  - **Proxy Name**: Enter a user-friendly name for the third-party proxy that you are defining.
  - **IP Address/FQDN**: `forward.zseclipse.net`.
  - **Port**: `9443`.
  - **Proxy's Root Certificate**: Select the root certificate you previously created.
  - **Insert X-Authenticated-User**: Enable this setting.
  - **Enable Base64 Encoding for X-Authenticated-User value**: Disable this setting.
  - **Description (Optional)**: Enter additional notes or information. The description cannot exceed 256 characters.
4. Click **Save**.
5. In the **Proxy Gateways** tab, click **Add Gateway for Proxies**. The **Add Gateway for Proxies** window appears. See image.
6. In the **Add Gateway and Proxies** window:
  - **Name**: Enter a name for your proxy gateway.
  - **Fail Close**: Leave selected.
  - **Primary Proxy**: Select the proxy you previously created.
  - **Secondary Proxy**: Leave blank.
7. Click **Save**.

To create a rule for ZIA to drop QUIC traffic:

1. In the Zscaler Admin Console, go to **Policies** > **Access Control** > **Firewall** > **Firewall Filtering Policy**. See image.
2. On the **Firewall Filtering Policy** page, click **Add Rule**. The **Add Rule** window appears.
3. In the **Add Rule** window: If you have ZIA Tunnel 1.0, disable the QUIC protocol from your browser. Refer to your specific browser's instructions to disable QUIC.
  - **Criteria**: Select **Network Services** from the drop-down menu.
    - **Network Services**: Select **QUIC** from the dropdown menu.
  - **Network Services**: In the **Services** tab, click the drop-down menu and select **QUIC**.
  - **Network Traffic**: From the drop-down menu, select **Block/ICMP**.
  - **Rule Name**: Enter a name for the rule. See image.
4. Click **Save**.

To create a wildcard destination group and forwarding rule:

1. In the Zscaler Admin Console, go to **Policies** > **Access Control** > **Firewall** > **IP &FQDN Groups**. See image.
2. On the **Destination IPv4 Groups** tab, click **Add Destination IPv4 Group**. The **Add Destination IPv4 Group** window opens.
3. In the **Add Destination IPv4 Group** window: See image.
  - **Name**: Enter a name such as `AI Guard`.
  - **Type**: Select **Wildcard FQDN**.
  - **Wildcard FQDN**: Add in the required domains for your desired AI providers and click **Add Items**. The AI domains are found in the Prerequisites section.
  - **Description (Optional)**: Enter additional information not exceeding 10240 characters.
4. Click **Save**.
5. In the Zscaler Admin Console, go to **Infrastructure** > **Internet & SaaS** > **Network Policies** > **Forwarding Control Policy**. See image.
6. On the **Forwarding Control** page, click **Add Rule**. The **Add Forwarding Rule** window appears.
7. In the **Add Forwarding Rule** window:
  - **Criteria**: Select **Destination IPv4 Groups**.
    - **Destination IPv4 Groups**: Select the wildcard FQDN destination group you previously created.
  - **Rule Name**: Enter a name for the forwarding rule.
  - **Forwarding Method**: Select **Proxy Chaining**.
  - **Forward to Proxy Gateway**: Select the proxy gateway you previously created. See image.

For traffic forwarding to work, users must have signed in to ZIA through mechanisms such as Zscaler Client Connector, and they must be authenticated by your IdP. You can check the logs in ZIA to ensure that traffic forwarding is enabled. To learn more, see [About Insights Logs](https://help.zscaler.com/zia/about-insights-logs).

[Image: AI Guard Users page with usernames and domains blurred]

[Image: ZIA Add Root Certificate window with Name, Type, and File fields displayed.]

[Image: ZIA Add Proxy window showing the Proxy Name, IP/FQDN, Port, Proxy's Root Certificate, and Insert X-Authenticated-User]

[Image: ZIA Add Gateway for Proxies window showing Gateway Name, Fail Close, Primary and Secondary Proxy]

[Image: ZIA Add Firewall Filtering Rule window]

[Image: ZIA Add Firewall Filtering Rule window]

[Image: Zscaler Forwarding Control page]

[Image: Zscaler Add Forwarding Rule window with Criteria set to Destination]

[Image: Zscaler IP & FQDN Groups page on the Destination IPv4 Groups tab]

[Image: Zscaler Add Destination IPv4 window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/managing-ai-guard-log-exports","lastmod":"2026-08-11T10:02Z","nid":"1540889"} -->
## Managing AI Guard Log Exports

- Source: https://help.zscaler.com/secure-ai-users/managing-ai-guard-log-exports
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Configuration > Managing AI Guard Log Exports
- Last modified: 2026-08-11T10:02Z
- Summary: Learn to manage and configure third-party integrations to export incident data from AI Guard.

The AI Guard **Log Exports** page allows you to manage and configure third-party integrations to export incident data. You can do this through either Amazon Web Services (AWS), CrowdStrike (CRWD), Splunk, or AWS S3 event exporting.

## Azure Data Explorer (ADX) Event Export

To add an ADX event export instance:

1. Under **Azure ADX Event Export**, click **Add Instance**. The **Add Integration** window appears. See image.
2. In the **Add Integration** window:
  - **Name**: Enter a name for your event export instance.
  - **Enabled**: Select this option to activate the event report functionality.
  - **Meta Event Hub Connection String**: Enter the SAS connection string for the meta Event Hub (e.g. `aiguard-meta`). Obtain via: `az eventhubs eventhub authorization-rule keys list`.
  - **Content Event Hub Connection String**: (Optional) Enter the SAS connection string for the content Event Hub (e.g. `aiguard-content`). Obtain via: `az eventhubs eventhub authorization-rule keys list`.
  - **Export Allowed/Detected Prompts**: Select to export allowed and detected prompts.
  - **Export Blocked Prompts**: Select to export blocked prompts.
  - **Export Tools Field**: Enabled by default. Disable if you want to remove the **Tools** field from your event log metadata export.
3. Click **Validate Connection** to check whether the information you entered is accurate and working.
4. Click **Save Integration**. The **Azure ADX Event Export Integrations** page opens. Your integration appears on this page.

## CRWD Event Export

Use the CRWD integration to export event metadata to CrowdStrike HTTP Event Collector (HEC) and event contents to AWS S3.

To add a CRWD event export instance:

1. Under **CRWD Event Export**, click **Add Instance**. The **Add Integration** window appears. See image.
2. In the **Add Integration** window:
  - **Name**: Enter a name for your event export instance.
  - **Enabled**: Select this option to activate the event report functionality.
  - **AWS S3 Bucket**: Enter the AWS S3 bucket location where the tenant's event content files will be stored.
  - **Bucket Key Prefix**: (Optional) Enter the key prefix for objects created in the S3 bucket.
  - **Tags**: (Optional) Click **Add Tag** to enter the tag key and value to apply to the object put in the S3 bucket. To use this feature, the IAM role requires permissions for the "s3:PutObjectTagging" action.
  - **Region of Bucket**: Enter the region of the bucket.
  - **IAM Cross-Account Role ARN**: Enter the Amazon Resource Name (ARN) of the IAM cross-account role created in the tenant's AWS account.
  - **IAM Cross-Account Role External ID**: The external ID of the IAM cross-account role which is created in the tenant's AWS account. It should be filled in automatically.
  - **CrowdStrike HEC Bearer Token**: Enter the bearer token to authenticate the CrowdStrike HEC.
  - **CrowdStrike HEC URL**: Enter the URL of the CrowdStrike HEC (raw endpoint) where tenant's events (metadata) will be posted.
  - **Export Allowed/Detected Prompts**: Select to export allowed and detected prompts.
  - **Export Blocked Prompts**: Select to export blocked prompts.
  - **Export Tools Field**: Enabled by default. Disable if you want to remove the **Tools** field from your event log metadata export.
3. Click **Validate Connection** to check whether the information you entered is accurate and working.
4. Click **Save Integration**. The **CRWD Event Export Integrations** page opens. Your integration appears on this page.

## S3 Event Export

Use the S3 integration to export event metadata and contents to AWS S3.

To add an S3 event export instance:

1. Under **S3 Event Export**, click **Add Instance**. The **Add Integration** window appears. See image.
2. In the **Add Integration** window:
  - **Name**: Enter a name for your event export instance.
  - **Enabled**: Select this option to activate the event report functionality.
  - **AWS S3 Metadata Bucket**: Enter the AWS S3 bucket location where the tenant's event metadata files will be stored.
  - **AWS S3 Content Bucket**: Enter the AWS S3 bucket where the tenant's event content files will be stored.
  - **Bucket Key Prefix**: (Optional) Enter the key prefix for objects created in the S3 bucket.
  - **Tags**: (Optional) Click **Add Tag** to enter the tag key and value to apply to the object put in the S3 bucket. To use this feature, the IAM role requires permissions for the "s3:PutObjectTagging" action.
  - **Region of Bucket**: Enter the region of the bucket.
  - **IAM Cross-Account Role ARN**: Enter the ARN of the IAM cross-account role created in the tenant's AWS account.
  - **IAM Cross-Account Role External ID**: The external ID of the IAM cross-account role created in the tenant's AWS account. This field auto-populates.
  - **Export Allowed/Detected Prompts**: Select to export allowed and detected prompts.
  - **Export Blocked Prompts**: Select to export blocked prompts.
  - **Export Tools Field**: Enabled by default. Disable if you want to remove the **Tools** field from your event log metadata export.
3. Click **Validate Connection** to check whether the information you entered is accurate and working.
4. Click **Save Integration**. The **S3 Event Export Integrations** page opens. Your integration appears on this page.

## Splunk Event Export

To add a Splunk event export instance:

1. Under **Splunk Event Export**, click **Add Instance**. The **Add Integration** window appears. See image.
2. In the **Add Integration** window:
  1. **Name**: Enter a name for your event export instance.
  2. **Enabled**: Select this option to activate the event report functionality.
  3. **Splunk Metadata HEC Bearer Token**: Enter the bearer token used to authenticate Splunk HEC for the tenant's events (metadata).
  4. **Splunk Metadata HEC URL**: Enter the URL of the Splunk HEC (raw endpoint) where the tenant's events (metadata) will be posted.
  5. **Splunk Content HEC Bearer Token**: (Optional) Enter the bearer token used to authenticate to the Splunk HEC for tenant's events (content).
  6. **Splunk Content HEC URL**: (Optional) Enter the URL of the Splunk HEC (raw endpoint) where tenant's events (content) will be posted.
  7. **Export Allowed/Detected Prompts**: Select to export allowed and detected prompts.
  8. **Export Blocked Prompts**: Select to export blocked prompts.
  9. **Export Tools Field**: Enabled by default. Disable if you want to remove the **Tools** field from your event log metadata export.
3. Click **Validate Connection** to check whether the information you entered is accurate and working.
4. Click **Save Integration**. The **Splunk Export Integrations** page opens. Your integration appears on this page.

[Image: AI Guard Add Integration window for CRWD]

[Image: AI Guard Add Integration window for S3]

[Image: AI Guard Add Integration window for Splunk]

[Image: AI Guard Add Integration window for Azure ADX]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/managing-ai-guard-policy-control","lastmod":"2026-08-17T11:24Z","nid":"1540883"} -->
## Managing AI Guard Policy Control

- Source: https://help.zscaler.com/secure-ai-users/managing-ai-guard-policy-control
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Configuration > Managing AI Guard Policy Control
- Last modified: 2026-08-17T11:24Z
- Summary: Learn to create and manage AI Guard policy control.

After you create an AI Guard policy configuration, you can then attach it to an LLM provider, user, or user group by adding policy control. Policies are evaluated from top-to-bottom and the evaluation will stop after matching the first policy. To learn more, see [Adding and Managing AI Guard Policies](https://help.zscaler.com/secure-ai-users/adding-and-managing-ai-guard-policy-configurations).

## Adding User Policy Control

To create user policy control:

1. In the left-side navigation, go to **AI Guard** > **Policy** > **Controls**. The **Policy Control** page appears. See image.
2. Click the **Users** tab.
3. Click **Add More** to open the **Create Policy Control** window. See image.
4. Enter the following required information:
  - **Rule Name**: Enter a name for the policy match.
  - **Policy Configuration**: From the drop-down menu, select a policy you previously created.
  - **Rule Order**: Enter a number for the order in which you want the policy match applied.
  - **Rule Status**: Select whether you want the rule to be enabled or disabled.
  - **Match Criteria**: At least one match criteria must be defined:
    - **LLM Provider & Models**: Click **Add More**.
      - **LLM**: Select an LLM provider.
      - **Models**: Select an AI model.
      - **Account Type**: If Organization Restrictions are enabled, select Enterprise, Personal, or Both. To learn more about Organization Restrictions, see [Managing Tenant Settings](https://help.zscaler.com/secure-ai-users/managing-tenant-settings).
    - **Users**: Click the drop-down menu and select the users you want to include with the match criteria.
    - **User Groups**: Click the drop-down to select the groups you want to include in the match criteria.
5. Click **Submit** to return to the **Policy Control** page. The new policy control appears at the **Rule Order** number you entered.

## Editing a Policy Control

To edit a policy control:

1. Click the **Users** tab.
2. In the **Action** column, click the **Edit** icon. The **Edit Policy Control** window opens. See image.
3. In the **Edit Policy Match** window, edit any policy match fields and click **Submit** when done. See image.

## Deleting a Policy Control

To delete a policy control:

1. Click the **Users** tab.
2. In the **Action** column, click the **Delete** icon. The **Delete Policy Control** window opens and asks if you would like to delete the policy match. See image.
3. In the **Delete Policy Control** window, click **OK**.

[Image: AI Guard Create Policy Control window]

[Image: AI Guard Policy Match page with an example policy match and Add More button visible.]

[Image: The Action column on the AI Guard main page with the edit icon highlighted]

[Image: AI Guard Edit Policy Match window with Policy and Rule Name blurred out]

[Image: The Action column on the AI Guard Main Page with Delete icon highlighted]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/managing-prompt-allowlist","lastmod":"2026-07-19T17:47Z","nid":"1541537"} -->
## Managing Prompt Allowlist

- Source: https://help.zscaler.com/secure-ai-users/managing-prompt-allowlist
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Configuration > Managing Prompt Allowlist
- Last modified: 2026-07-19T17:47Z
- Summary: Learn how to add blocked prompts to the AI Guard allowlist and manage the allowlist.

The prompt allowlist for AI Guard allows you to track and manage any prompts that you wish to allow that may otherwise be blocked by an existing AI Guard policy. Prompts sent to the allowlist endpoint are fully encrypted, so the prompt content remains protected end-to-end.

The prompt allowlist functionality is only available in Proxy mode.

## Configure AI Guard to Enable Allowlist

The following instructions explain what tenant settings you need to enable in AI Guard so that the prompt allowlist functionality is enabled.

1. From the AI Security Admin Portal left-side navigation, go to **AI Guard** > **Tenant Settings**.
2. On the **Tenant Settings** page, go to the **Security** tab.
3. Under **Security Settings**, enable **Store Prompts/Responses** and **Enable Data Sharing for AI Training**. See image.

Using the allowlist functionality requires the System Administrator role with all-write access. Individual actions are governed by the Default Access permission. The following steps will explain how to add those permissions with role-based acces control (RBAC).

1. From the AI Security Admin Portal left-side navigation, go to **AI Guard** > **RBAC Management**.
2. Click **Add Role** to create a new role with allowlist access, or click **Edit** to add access to an existing role. See image.
3. In the **Add** or **Edit Role** window, enable the following permissions as needed: See image.
  1. View allowlisted prompts: **Default Access: View**
  2. Add to allowlist: **Default Access: Create**
  3. Delete prompt from allowlist: **Default Access: Delete**
4. Click **Create** or **Save** when done.

To learn more about the Tenant Settings and RBAC Management pages, see [Managing Tenant Settings](https://help.zscaler.com/secure-ai-users/managing-tenant-settings) and [Managing Role-Based Access Control in AI Guard](https://help.zscaler.com/secure-ai-users/managing-role-based-access-control-ai-guard).

## Add Prompt to Allowlist

The following instructions explain how to add a blocked prompt to the allowlist.

1. From the AI Security Admin Portal left-side navigation, go to **AI Guard** > **Dashboard**.
2. Find the blocked prompt and click **Details**. The **Transaction Details** window opens. See image.
3. At the bottom of the Transaction Details window under Prompt Details, click **Reveal Prompt**.
4. If the blocked prompt is the one you want to add to the allowlist, click **Add to Allowlist**. See image.
5. In the **Add to Allowlist** window, confirm the prompt and click **OK**. See image.
6. From the AI Security Admin Portal left-side navigation, go to **AI Guard** > **Prompt Allowlist**.
7. On the **Prompt Allowlist** page, you can find the prompts that you have added. See image.

## Delete Prompt from Allowlist

The following instructions explain how to delete a prompt from the allowlist.

1. From the AI Security Admin Portal left-side navigation, go to **AI Guard** > **Prompt Allowlist**.
2. On the **Prompt Allowlist** page, find the prompt you want to delete and in the **Action** column, click **Delete**. See image.
3. In the **Remove from Allowlist** window, click **OK**. See image.

[Image: Prompt Details section of the Transaction Details page with annotations around Add to Allowlist]

[Image: AI Guard Dashboard page with annotations around Details.]

[Image: Add to Allowlist window]

[Image: Prompt Allowlist page]

[Image: Prompt Allowlist page with annotations around Delete.]

[Image: Remove from Allowlist window]

[Image: Tenant Settings page on Security tab with annotations around Store Prompts/Responses and Enable Data Sharing for AI Training]

[Image: RBAC page with annotations around Add Role and Edit]

[Image: RBAC Add Role window with annotations around the Default Access permissions View, Create, and Delete.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/managing-role-based-access-control-ai-guard","lastmod":"2026-07-21T10:56Z","nid":"1540877"} -->
## Managing Role-Based Access Control in AI Guard

- Source: https://help.zscaler.com/secure-ai-users/managing-role-based-access-control-ai-guard
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Configuration > Managing Role-Based Access Control in AI Guard
- Last modified: 2026-07-21T10:56Z
- Summary: Learn to use AI Guard's Role-based access control (RBAC). This includes the ability to start with a predefined role template, or creating custom roles with granular levels of permissions.

Role-Based Access Controls (RBAC) for AI Guard system users enables organizations to provide more granular control of administrative functions, such as the ability to create custom permissions to assign to system users.

RBAC applies to:

- System users provisioned/managed through Zscaler Authentication (Authentication Service).
- Local hosted system users in AI Guard.

Administrators managed through Authentication Service can leverage the new RBAC within AI Guard to assign these roles to other system users in Authentication Service.

## Role Permissions

AI Guard roles are created using a combination of permissions and scopes across the various sections of the admin console and object configurations. Please refer to the following table for a list of permissions and scopes for modules:

- List of Role Permissions

A majority of the permissions allow a role to be configured with a combination of one or multiple scopes per permission. For example:

- Customer Service Role:
  - Dashboard: **View**
  - Detections Policy: **View**
  - Events (View Prompts): **None** (unchecked)
  - Insights: **View**
  - All other permissions: **None** (unchecked)
- Auditor:
  - Audit Log: **View**
  - Event (View Prompts): **None** (unchecked)
  - All other permissions: **None** (unchecked)

## Creating a Role

This section describes how to create roles for managing system user permissions.

1. In the AI Security Admin Portal left-side navigation, go to **AI Guard** > **RBAC Management**.
2. Click **Add Role**. The **Add Role** window appears. See image.
3. In the **Add Role** window, fill out the following sections: See image.
  1. **Role Name**: Provide a role name, such as `Auditor`.
  2. **Start from a template**: (Optional) Click the drop-down menu to select from the following role templates:
    1. **Viewer**: Read-only access to all modules.
    2. **Editor**: Read, create, and update access. No delete access.
    3. **Administrator**: Full access to all modules.
  3. **Permissions**: Select the permissions for the role.
    - Selecting a module checkbox enables all permissions for that module.
    - Selecting an individual permission checkbox will enable that specific permission for that module.
  4. Click **Save**.
4. To edit a role, click the **Edit** button under the **Action** column of the role. In the **Edit Role** window, make any necessary changes and click **Save**.
5. To delete a role, click the **Delete** button under the **Action** column of the role. In the **Delete Role** window, click **Delete**.

## Assign a Role to a System User

This section will go into detail on how to assign a role to a system user in AI Guard.

1. In the AI Security Admin Portal left-side navigation, go to **AI Guard** > **System User Management**.
2. Select the **Edit** button under the **Action** column to add or edit a role for an existing system user, or you can click **Add More** to create a new system user. See image.
3. In the **Edit System User** or **Add System User** window, click the **Role** drop-down menu and select the role you want to assign.
4. Click **Update** or **Submit**.

To learn more about AI Guard System Users, see [Viewing AI Guard System Users](https://help.zscaler.com/secure-ai-users/viewing-ai-guard-system-users).

| Modules | Scope (Multiselect) |
| --- | --- |
| Default Access (applies permissions to all modules) | None \| View \| Create \| Edit \| Delete |
| **Overview** |  |
| Dashboard | None \| View |
| Insights | None \| View |
| Topology | None \| View |
| **Security** |  |
| Policies | None \| View \| Create \| Edit \| Delete |
| LLMs | None \| View \| Create \| Edit \| Delete |
| Private AI Apps | None \| View \| Create \| Edit \| Delete |
| **Operations** |  |
| Policy Testing | None \| View \| Create |
| Log Exports | None \| View \| Create \| Edit \| Delete |
| **Admin** |  |
| User Management | None \| View \| Create \| Edit \| Delete |
| RBAC Management | None \| View \| Create \| Edit \| Delete |
| Audit Log | None \| View |
| Tenant Settings | None \| View \| Edit |
| **Other** |  |
| API Clients | None \| View \| Delete |

[Image: The AI Guard Role-Based Access Control page showing example role entries]

[Image: The Add Role window showing Name, Template, Permissions, and Other settings to configure]

[Image: The AI Guard System Users page with annotations around the Add More button and the Edit button.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/managing-tenant-settings","lastmod":"2026-08-17T11:17Z","nid":"1540885"} -->
## Managing Tenant Settings

- Source: https://help.zscaler.com/secure-ai-users/managing-tenant-settings
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Configuration > Managing Tenant Settings
- Last modified: 2026-08-17T11:17Z
- Summary: Learn how to manage the following AI Guard tenant settings: Network Access Control Policy, Custom Request Headers, Security Settings, and Syncing ZIA End Users and Groups.

From the AI Guard **Tenant Settings** page, you can view information and make additional customizations to your AI Guard tenant. In addition to basic tenant information, you can also make changes to your security and encryption settings, and sync your Zscaler Internet Access (ZIA) end users, groups, and domains.

On the AI Guard **Tenant Settings** page (AI Guard > Tenant Settings), you can view the following basic information:

- **Name**: Name of the tenant.
- **Mode**: Lists whether AI Guard is in **Proxy** or **DaaS** mode.
- **UUID**: Universally unique identifier for your tenant.
- **Zscaler AWS Account ID**: Displays Zscaler’s AWS Account ID. This is used for optional AWS integrations such as log exports to AWS S3 buckets or optional AWS customer-managed keys for encryption.

See image.

## Managing Security Settings

In the **Security Settings** section, you have the following functionality:

- **Store Prompts/Responses**: Keep a history of all prompts and responses for the last 90 days for auditing.
- **Enable Event Detection Feedback**: Enable feedback submission on detection events. The feedback might be used for local model training. It will be securely stored with encryption.
- **Enable Custom Header Encryption**: Encrypt the contents of sensitive custom headers.
- **Enable Content Encryption**: Use customer-managed keys for additional security.

See image.

If you enable any encryption, you must provide a customer-managed key. Under **Encryption - Configure Customer Managed Key**:

- **KMS Provider Key**: **AWS** is currently the only supported provider type.
- **Key ARN**: Enter your Amazon Resource Name (ARN) encryption key.

See image.

After entering your encryption key, click **Save Changes**. Click **Reset** to revert any changes to the customer key ID and disable both content and custom header encryption settings.

## Syncing ZIA End Users and Groups with Multi-domain Support

To sync your ZIA end users and groups with AI Guard, you must first provide your Zscaler Internet Access (ZIA) information on the **Tenant Settings** page:

1. Under **ZIA Information**, click the **Enable ZIA User and Group Sync** toggle to sync users and groups from ZIA to AI Guard. The cloud name and organization ID will be displayed below once sync is enabled and information is available. See image.
2. Click the **Enable ZIA Domain Sync** toggle to sync domains from ZIA to AI Guard. This ensures all relevant domains are synchronized for policy evaluation.
3. Next to **Download Zscaler AI Guard Proxy Chain Certificate**, click **Download** to download the root certificate from the portal which you can upload into ZIA to create the trust between ZIA and AI Guard.

To enable an immediate ZIA data sync outside the scheduled batch window, under **Advanced Actions**, click **Start Sync**.

## Custom Block Message for Consumer GenAI Apps

When someone is using AI Guard, you can include a custom message that appears when their prompt or response is blocked. This custom block message can be used to provide a link to the user to notify them about AI usage policy of your organization.

See image.

- **Custom Prompt Block Message**: Enter a message that users will see when their prompt is blocked.
- **Custom Response Block Message**: Enter a message that users will see when their LLM response is blocked.
- **Delete Conversation on Response Block**: Select if you want to automatically delete chat history of response blocking cases for providers.

After entering the block message, click **Save**.

## Organization Restrictions

AI Guard's tenancy restriction feature allows you to restrict user traffic access either to personal accounts, enterprise accounts, or both, and apply different access-control rules to each. This feature is currently available for Anthropic and OpenAI.

User traffic is automatically tagged at runtime. Requests matching a registered ID are tagged as "Enterprise," while unmatched requests are classified as "Personal."

To enable this functionality:

1. In the left-side navigation, click **Tenant Settings**. The **Tenant Settings** page appears.
2. Go to the **Security** tab and find the **Organization Restrictions** section. See image.
3. Choose to enable the toggle for **Anthropic** or **OpenAI**. It may take up to five minutes for the changes to take effect.
  1. When enabled, AI Guard will permit the listed organizations and deny all others.
  2. When disabled, AI Guard will allow all traffic through and treat the list of organizations as Enterprise accounts. For everything else, it will assume those are Personal accounts.
4. Under the LLM providers you enabled, enter your organization's specific Anthropic Org IDs or OpenAI Workspace IDs.
5. Click **Save Restrictions**.

After enabling this functionality, within Policy Controls, administrators can select an "Account Type" (Personal, Enterprise, or Both) as a match criterion. This allows for specific policies (such as stricter data loss prevention or topic detection rules) to be applied to unmanaged personal traffic, while maintaining different standards for company-sanctioned workspaces.

To learn more, see [Managing AI Guard Policy Control](https://help.zscaler.com/secure-ai-users/managing-ai-guard-policy-control).

## Anthropic Webhook

AI Guard can integrate with Anthropic Claude's inference hooks to inspect and evaluate prompts against a tenant's detection policy in real time. AI Guard responds with an "allow" or "deny" verdict, which determines whether Claude proceeds to generate a response or the prompt is blocked.

To set up an inference hook between AI Guard and Claude:

1. In the **Claude UI**, go to **Organization Settings** > **Data and Privacy** > **Inference Hooks**.
2. Under **Inference hooks**, enable **Allow for your organization**. See image.
3. In the **Inference hooks** section, do the following: See image.
  1. **Enforce verdicts**: Enable.
  2. **Inference hooks endpoint**: Set to `https://api.zseclipse.net/v1/webhook/execute`
  3. **Prompt verdict timeout (ms)**: 1000ms
  4. **Signing secret**: Copy this secret for use in AI Guard later. A signing secret is generated automatically on the first save and can be rotated at any time.
  5. **(Optional) Custom blocked prompt message**: Enter a custom block message to show users when a prompt is blocked.
4. Leave Claude and go to the **AI Security Admin Portal** > **AI Guard** > **Tenant Settings** > **Integrations** tab.
5. In the **Anthropic Webhook** section, click **Add Secret**. See image.
6. In the **Add Webhook Signing Secret** window, do the following: See image.
  1. **Anthropic Org UUID**: Enter your organization’s Anthropic UUID.
  2. **Signing Secret**: Enter the signing secret you copied earlier.
  3. Click **Add Secret**.

With the webhook integrated, create a policy to detect webhook traffic and create a policy control to set the match criteria so the policy applies to the traffic you intend. To learn more, see [Adding and Managing AI Guard Policy Configurations](https://help.zscaler.com/secure-ai-apps-infra/adding-and-managing-ai-guard-policy-configurations) and [Managing AI Guard Policy Control](https://help.zscaler.com/secure-ai-apps-infra/managing-ai-guard-policy-control).

[Image: Anthropic Claude UI showing Inference hooks section]

[Image: Anthropic Claude UI showing Inference hooks section][Image: Anthropic Claude UI showing Inference hooks section]

[Image: AI Guard, Tenant Settings, Integration Tab, Anthropic Webhook section]

[Image: Add Webhook Signing Secret window]

[Image: Tenant Settings, Security tab, Organisation Settings with example entries added]

[Image: AI Guard Tenant Settings ZIA Information section showing Cloud Name and Organization ID fields]

[Image: AI Guard Tenant Settings Basic Information which includes Name, Mode, UUID, and ZIdentity Enabled.]

[Image: AI Guard Tenant Settings Security Settings]

[Image: AI Guard Tenant Settings showing the Encryption - Configure Customer Managed Key section]

[Image: AI Guard Tenant Settings page with the Custom Block Message section displayed]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/quick-start-guide-chatgpt","lastmod":"2026-07-22T09:41Z","nid":"1541877"} -->
## Quick Start Guide for ChatGPT

- Source: https://help.zscaler.com/secure-ai-users/quick-start-guide-chatgpt
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Getting Started > Quick Start Guide for ChatGPT
- Last modified: 2026-07-22T09:41Z
- Summary: This guide takes you through the configuration steps you need to set up AI Guard, integrate with ZIA, and set up the policies necessary to provide run-time protection for your ChatGPT applications.

This guide takes you through the configuration steps you need to set up AI Guard, integrate with Zscaler Internet Access (ZIA), and set up the policies necessary to provide run-time protection for your ChatGPT applications.

Before you begin configuring AI Guard, Zscaler recommends reading the following articles:

- [What Is AI Guard?](https://help.zscaler.com/secure-ai-users/what-ai-guard)
- [About the AI Guard Dashboard](https://help.zscaler.com/secure-ai-users/about-ai-guard-dashboard)
- [About AI Guard Insights](https://help.zscaler.com/secure-ai-users/about-ai-guard-insights)
- [About AI Guard Usage](https://help.zscaler.com/secure-ai-users/about-ai-guard-usage)

## Configuring AI Guard

To configure AI Guard, complete the following steps:

- Step 1: Configuring AI Guard
- Step 2: Configuring ZIA
- Step 3: Configure Policies

This section will cover the baseline for configuring AI Guard.

1. In the AI Security Admin Portal left navigation menu, go to **AI Guard** > **Tenant Settings** > **Security**.
2. Under **Security Settings**, enable **Store Prompts/Responses** to be able to view the data in AI Guard, otherwise all Prompt/Response fields will show No Data. See image.
3. Go to **Tenant Settings** > **Integrations**.
4. Enable the following: See image.
  1. **Enable ZIA User and Group Sync**
  2. **Enable ZIA Domain Sync**
5. Download the **Zscaler AI Guard Proxy Chain Certificate** (CA certificate) for use later.

The following steps are to ensure the required controls are in place for inspection and forwarding the supported ChatGPT apps from ZIA to AI Guard.

- Uploading your AI Guard CA certificate to ZIA
- Configuring the ZIA Proxy
- Creating a rule for ZIA to drop QUIC traffic
- Creating a wildcard destination group and forwarding rule

To upload your AI Guard CA certificate to ZIA:

1. In the Zscaler Admin Console, go to **Infrastructure** > **Internet & SaaS** > **Network Policies** > **Root Certificates**.
2. Click **Add Root Certificates**. The **Add Root Certificate** window appears. See image.
3. In the **Add Root Certificate** window:
  1. **Name**: Enter a name for your certificate.
  2. **Type**: From the drop-down menu, select **Proxy Chaining**.
  3. **File**: Browse and select the AI Guard CA certificate (`.pem` file) from your system.
4. Click **Save**.

To configure your ZIA proxy:

1. In the Zscaler Admin Console, go to **Infrastructure** > **Internet & SaaS** > **Network Policies** > **Proxies and Gateways**.
2. In the **Proxies** tab, click **Add Proxy**. The **Add Proxy** window appears. See image.
3. In the **Add Proxy** window:
  - **Proxy Name**: Enter a user-friendly name for the third-party proxy that you are defining.
  - **IP Address/FQDN**: Enter `forward.zseclipse.net`.
  - **Port**: Enter `9443`.
  - **Proxy's Root Certificate**: Select the root certificate you previously created.
  - **Insert X-Authenticated-User**: Enable this setting.
  - **Enable Base64 Encoding for X-Authenticated-User value**: Disable this setting.
  - **Description (Optional)**: Enter additional notes or information. The description cannot exceed 256 characters.
4. Click **Save**.
5. In the **Proxy Gateways** tab, click **Add Gateway for Proxies**. The **Add Gateway for Proxies** window appears. See image.
6. In the **Add Gateway and Proxies** window:
  - **Name**: Enter a name for your proxy gateway.
  - **Fail Close**: Leave selected.
  - **Primary Proxy**: Select the proxy you previously created.
  - **Secondary Proxy**: Leave blank.
7. Click **Save**.

To create a rule for ZIA to drop QUIC traffic:

1. In the Zscaler Admin Console, go to **Policies** > **Access Control** > **Firewall** > **Firewall Filtering Policy**. See image.
2. On the **Firewall Filtering Policy** page, click **Add Rule**. The **Add Rule** window appears.
3. In the **Add Rule** window: If you have ZIA Tunnel 1.0, disable the QUIC protocol from your browser. Refer to your specific browser's instructions to disable QUIC.
  - **Criteria**: Select **Network Services** from the drop-down menu.
    - **Network Services**: Select **QUIC** from the dropdown menu.
  - **Network Services**: In the **Services** tab, click the drop-down menu and select **QUIC**.
  - **Network Traffic**: From the drop-down menu, select **Block/ICMP**.
  - **Rule Name**: Enter a name for the rule.. See image.
4. Click **Save**.

To create a wildcard destination group and forwarding rule:

1. In the Zscaler Admin Console, go to **Policies** > **Access Control** > **Firewall** > **IP &FQDN Groups**. See image.
2. On the **Destination IPv4 Groups** tab, click **Add Destination IPv4 Group**. The **Add Destination IPv4 Group** window opens.
3. In the **Add Destination IPv4 Group** window: See image.
  - **Name**: Enter a name such as `AI Guard`.
  - **Type**: Select **Wildcard FQDN**.
  - **Wildcard FQDN**: Enter:
    - `.chatgpt.com`
    - `.chat.openai.com`
  - **Description (Optional)**: Enter additional information not exceeding 10240 characters.
4. Click **Save**.
5. In the Zscaler Admin Console, go to **Infrastructure** > **Internet & SaaS** > **Network Policies** > **Forwarding Control Policy**. See image.
6. On the **Forwarding Control** page, click **Add Rule**. The **Add Forwarding Rule** window appears.
7. In the **Add Forwarding Rule** window:
  - **Criteria**: Select **Destination IPv4 Groups**.
    - **Destination IPv4 Groups**: Select the wildcard FQDN destination group you previously created.
  - **Rule Name**: Enter a name for the forwarding rule.
  - **Forwarding Method**: Select **Proxy Chaining**.
  - **Forward to Proxy Gateway**: Select the proxy gateway you previously created. See image.

For traffic forwarding to work, users must have signed in to ZIA through mechanisms such as Zscaler Client Connector, and they must be authenticated by your IdP. You can check the logs in ZIA to ensure that traffic forwarding is enabled. To learn more, see [About Insights Logs](https://help.zscaler.com/zia/about-insights-logs).

## Adding Policy Configurations

To add a new policy for your ChatGPT app in AI Guard:

1. In the AI Security Admin Portal left-side navigation, go to: **AI Guard** > **Policy >** **Configurations**. See image.
2. Click **Add More** to open the **Add New Configuration** page.
3. Under **Basic Information**, enter: See image.
  1. **Policy Name**
  2. (Optional) **Description**
4. Click **Continue to Detectors**. The **Prompt Detectors** tab opens.
5. On the **Prompt Detectors** tab, click on the **Toxicity** detector and then click on **Save Changes**. See image.
6. Click Next. Skip the configuration of response detectors. Click **Next** again. The **Review** tab opens. See image.
7. The **Review** tab shows you a summary of the policy configuration you created. Click **Submit Policy** if everything looks correct.

After creating a policy, the next step is to create a policy match. For more information on policy matching, see [Managing AI Guard Policy Control](https://help.zscaler.com/secure-ai-users/managing-ai-guard-policy-control).

## Adding Policy Control

After you create an AI Guard policy configuration, you can then apply it to your ChatGPT app by adding policy control.

To create a ChatGPT application policy control:

1. In the AI Guard left-side navigation under **Policies**, click **Controls**. The **Policy Control** page appears. See image.
2. Click on the **Users** tab.
3. Click **Add More** to open the **Create Policy Match** window. See image.
4. Enter the following required information:
  - **Policy**: From the drop-down menu, select a policy you previously created.
  - **Rule Name**: Enter a name for the policy match.
  - **Rule Order**: Enter a number for the order in which you want the policy match applied.
  - **Rule Status**: Select whether you want the rule to be enabled or disabled.
  - **Match Criteria**: At least one match criteria must be defined. Click **Add More** under any of the following drop-down menus to add more.
    - **Users & User Groups**: From the drop-down menus, select your ChatGPT users or user groups.
5. Click **Submit** to return to the **Policy Control** page.

[Image: AI Guard Policies page with an example policy visible]

[Image: Basic policy information fields which includes Policy Name and Description]

[Image: Configuring detector window showing the common options available.]

[Image: Review tab for adding a policy showing the information entered previously in the process]

[Image: ZIA Add Root Certificate window with Name, Type, and File fields displayed.]

[Image: ZIA Add Proxy window showing the Proxy Name, IP/FQDN, Port, Proxy's Root Certificate, and Insert X-Authenticated-User]

[Image: ZIA Add Gateway for Proxies window showing Gateway Name, Fail Close, Primary and Secondary Proxy]

[Image: ZIA Add Firewall Filtering Rule window]

[Image: ZIA Add Firewall Filtering Rule window]

[Image: Zscaler Forwarding Control page]

[Image: Zscaler Add Forwarding Rule window with Criteria set to Destination]

[Image: AI Guard Create Policy Control window]

[Image: AI Guard Policy Match page with an example policy match and Add More button visible.]

[Image: AI Guard Tenant Settings, Security Settings]

[Image: AI Guard Tenant Settings, ZIA Information]

[Image: Zscaler IP & FQDN Groups page on the Destination IPv4 Groups tab]

[Image: Zscaler Add Destination IPv4 window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/quick-start-guide-microsoft-365-copilot","lastmod":"2026-07-22T09:44Z","nid":"1541876"} -->
## Quick Start Guide for Microsoft 365 Copilot

- Source: https://help.zscaler.com/secure-ai-users/quick-start-guide-microsoft-365-copilot
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Getting Started > Quick Start Guide for Microsoft 365 Copilot
- Last modified: 2026-07-22T09:44Z
- Summary: This guide takes you through the configuration steps you need to set up AI Guard, integrate with ZIA, and set up the policies necessary to provide run-time protection for your Microsoft 365 Copilot applications.

This guide takes you through the configuration steps you need to set up AI Guard, integrate with Zscaler Internet Access (ZIA), and set up the policies necessary to provide run-time protection for your Microsoft 365 Copilot applications.

Before you begin configuring AI Guard, Zscaler recommends reading the following articles:

- [What Is AI Guard?](https://help.zscaler.com/secure-ai-users/what-ai-guard)
- [About the AI Guard Dashboard](https://help.zscaler.com/secure-ai-users/about-ai-guard-dashboard)
- [About AI Guard Insights](https://help.zscaler.com/secure-ai-users/about-ai-guard-insights)
- [About AI Guard Usage](https://help.zscaler.com/secure-ai-users/about-ai-guard-usage)

## Configuring AI Guard

To configure AI Guard, complete the following steps:

- Step 1: Configuring AI Guard
- Step 2: Configuring ZIA
- Step 3: Configure Policies

This section will cover the baseline for configuring AI Guard.

1. In the AI Security Admin Portal left navigation menu, go to **AI Guard** > **Tenant Settings** > **Security**.
2. Under **Security Settings**, enable **Store Prompts/Responses** to be able to view the data in AI Guard, otherwise all Prompt/Response fields will show No Data. See image.
3. Go to **Tenant Settings** > **Integrations**.
4. Enable the following: See image.
  1. **Enable ZIA User and Group Sync**
  2. **Enable ZIA Domain Sync**
5. Download the **Zscaler AI Guard Proxy Chain Certificate** (CA certificate) for use later.

The following steps are to ensure the required controls are in place for inspection and forwarding the supported Microsoft 365 Copilot apps from ZIA to AI Guard.

- Uploading your AI Guard CA certificate to ZIA
- Configuring the ZIA Proxy
- Creating a rule for ZIA to drop QUIC traffic
- Creating a wildcard destination group and forwarding rule

To upload your AI Guard CA certificate to ZIA:

1. In the Zscaler Admin Console, go to **Infrastructure** > **Internet & SaaS** > **Network Policies** > **Root Certificates**.
2. Click **Add Root Certificates**. The **Add Root Certificate** window appears. See image.
3. In the **Add Root Certificate** window:
  1. **Name**: Enter a name for your certificate.
  2. **Type**: From the drop-down menu, select **Proxy Chaining**.
  3. **File**: Browse and select the AI Guard CA certificate (`.pem` file) from your system.
4. Click **Save**.

To configure your ZIA proxy:

1. In the Zscaler Admin Console, go to **Infrastructure** > **Internet & SaaS** > **Network Policies** > **Proxies and Gateways**.
2. In the **Proxies** tab, click **Add Proxy**. The **Add Proxy** window appears. See image.
3. In the **Add Proxy** window:
  - **Proxy Name**: Enter a user-friendly name for the third-party proxy that you are defining.
  - **IP Address/FQDN**: Enter `forward.zseclipse.net`.
  - **Port**: Enter `9443`.
  - **Proxy's Root Certificate**: Select the root certificate you previously created.
  - **Insert X-Authenticated-User**: Enable this setting.
  - **Enable Base64 Encoding for X-Authenticated-User value**: Disable this setting.
  - **Description (Optional)**: Enter additional notes or information. The description cannot exceed 256 characters.
4. Click **Save**.
5. In the **Proxy Gateways** tab, click **Add Gateway for Proxies**. The **Add Gateway for Proxies** window appears. See image.
6. In the **Add Gateway and Proxies** window:
  - **Name**: Enter a name for your proxy gateway.
  - **Fail Close**: Leave selected.
  - **Primary Proxy**: Select the proxy you previously created.
  - **Secondary Proxy**: Leave blank.
7. Click **Save**.

To create a rule for ZIA to drop QUIC traffic:

1. In the Zscaler Admin Console, go to **Policies** > **Access Control** > **Firewall** > **Firewall Filtering Policy**. See image.
2. On the **Firewall Filtering Policy** page, click **Add Rule**. The **Add Rule** window appears.
3. In the **Add Rule** window: If you have ZIA Tunnel 1.0, disable the QUIC protocol from your browser. Refer to your specific browser's instructions to disable QUIC.
  - **Criteria**: Select **Network Services** from the drop-down menu.
    - **Network Services**: Select **QUIC** from the dropdown menu.
  - **Network Services**: In the **Services** tab, click the drop-down menu and select **QUIC**.
  - **Network Traffic**: From the drop-down menu, select **Block/ICMP**.
  - **Rule Name**: Enter a name for the rule.. See image.
4. Click **Save**.

To create a wildcard destination group and forwarding rule:

1. In the Zscaler Admin Console, go to **Policies** > **Access Control** > **Firewall** > **IP &FQDN Groups**. See image.
2. On the **Destination IPv4 Groups** tab, click **Add Destination IPv4 Group**. The **Add Destination IPv4 Group** window opens.
3. In the **Add Destination IPv4 Group** window: See image.
  - **Name**: Enter a name such as `AI Guard`.
  - **Type**: Select **Wildcard FQDN**.
  - **Wildcard FQDN**: Enter:
    - `.substrate.office.com`
    - `.substrate.svc.cloud.microsoft`
    - `.copilot.microsoft.com`
    - `.api.business.githubcopilot.com`
    - `.api.enterprise.githubcopilot.com`
    - `.api.individual.githubcopilot.com`
  - **Description (Optional)**: Enter additional information not exceeding 10240 characters.
4. Click **Save**.
5. In the Zscaler Admin Console, go to **Infrastructure** > **Internet & SaaS** > **Network Policies** > **Forwarding Control Policy**. See image.
6. On the **Forwarding Control** page, click **Add Rule**. The **Add Forwarding Rule** window appears.
7. In the **Add Forwarding Rule** window:
  - **Criteria**: Select **Destination IPv4 Groups**.
    - **Destination IPv4 Groups**: Select the wildcard FQDN destination group you previously created.
  - **Rule Name**: Enter a name for the forwarding rule.
  - **Forwarding Method**: Select **Proxy Chaining**.
  - **Forward to Proxy Gateway**: Select the proxy gateway you previously created. See image.

For traffic forwarding to work, users must have signed in to ZIA through mechanisms such as Zscaler Client Connector, and they must be authenticated by your IdP. You can check the logs in ZIA to ensure that traffic forwarding is enabled. To learn more, see [About Insights Logs](https://help.zscaler.com/zia/about-insights-logs).

## Adding Policy Configurations

To add a new policy for your Microsoft 365 Copilot app in AI Guard:

1. In the AI Security Admin Portal left-side navigation, go to: **AI Guard** > **Policy >** **Configurations**. See image.
2. Click **Add More** to open the **Add New Configuration** page.
3. Under **Basic Information**, enter: See image.
  1. **Policy Name**
  2. (Optional) **Description**
4. Click **Continue to Detectors**. The **Prompt Detectors** tab opens.
5. On the **Prompt Detectors** tab, click on the **Toxicity** detector and then click on **Save Changes**. See image.
6. Click Next. Skip the configuration of response detectors. Click **Next** again. The **Review** tab opens. See image.
7. The **Review** tab shows you a summary of the policy configuration you created. Click **Submit Policy** if everything looks correct.

After creating a policy, the next step is to create a policy match. For more information on policy matching, see [Managing AI Guard Policy Control](https://help.zscaler.com/secure-ai-users/managing-ai-guard-policy-control).

## Adding Policy Control

After you create an AI Guard policy configuration, you can then apply it to your Microsoft 365 Copilot app by adding policy control.

To create a Microsoft 365 Copilot application policy control:

1. In the AI Guard left-side navigation under **Policies**, click **Controls**. The **Policy Control** page appears. See image.
2. Click on the **Users** tab.
3. Click **Add More** to open the **Create Policy Match** window. See image.
4. Enter the following required information:
  - **Policy**: From the drop-down menu, select a policy you previously created.
  - **Rule Name**: Enter a name for the policy match.
  - **Rule Order**: Enter a number for the order in which you want the policy match applied.
  - **Rule Status**: Select whether you want the rule to be enabled or disabled.
  - **Match Criteria**: At least one match criteria must be defined. Click **Add More** under any of the following drop-down menus to add more.
    - **Users & User Groups**: From the drop-down menus, select your Microsoft 365 Copilot users or user groups.
5. Click **Submit** to return to the **Policy Control** page.

[Image: AI Guard Policies page with an example policy visible]

[Image: Basic policy information fields which includes Policy Name and Description]

[Image: Configuring detector window showing the common options available.]

[Image: Review tab for adding a policy showing the information entered previously in the process]

[Image: ZIA Add Root Certificate window with Name, Type, and File fields displayed.]

[Image: ZIA Add Proxy window showing the Proxy Name, IP/FQDN, Port, Proxy's Root Certificate, and Insert X-Authenticated-User]

[Image: ZIA Add Gateway for Proxies window showing Gateway Name, Fail Close, Primary and Secondary Proxy]

[Image: ZIA Add Firewall Filtering Rule window]

[Image: ZIA Add Firewall Filtering Rule window]

[Image: Zscaler Forwarding Control page]

[Image: Zscaler Add Forwarding Rule window with Criteria set to Destination]

[Image: AI Guard Create Policy Control window]

[Image: AI Guard Policy Match page with an example policy match and Add More button visible.]

[Image: AI Guard Tenant Settings, Security Settings]

[Image: AI Guard Tenant Settings, ZIA Information]

[Image: Zscaler IP & FQDN Groups page on the Destination IPv4 Groups tab]

[Image: Zscaler Add Destination IPv4 window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/release-upgrade-summary-2026","lastmod":"2026-08-28T11:01Z","nid":"1539123"} -->
## Release Upgrade Summary (2026)

- Source: https://help.zscaler.com/secure-ai-users/release-upgrade-summary-2026
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > Release Notes > Release Upgrade Summary (2026)
- Last modified: 2026-08-28T11:01Z
- Summary: Secure Access to AI Apps Release Upgrade Summary for service updates deployed in 2026.

This article provides a summary of all new features and enhancements for Secure Access to AI Apps.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/understanding-ai-guard-users-architecture","lastmod":"2026-07-21T12:15Z","nid":"1541847"} -->
## Understanding AI Guard for Users Architecture

- Source: https://help.zscaler.com/secure-ai-users/understanding-ai-guard-users-architecture
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Getting Started > Understanding AI Guard for Users Architecture
- Last modified: 2026-07-21T12:15Z
- Summary: Learn about the general architecture of AI Guard for Users to public generative AI (GenAI) applications.

AI Guard for Users is a service that provides run-time protection for your generative AI (GenAI) applications on a per-user or user group basis by enforcing enterprise policies that prevent prompt injections, block jailbreak attempts, and stop personal information leakage with guardrails around LLM interactions.

It secures these interactions by filtering harmful or inappropriate content from the prompts that users enter and the responses they receive in the AI application. AI Guard enforces intent-based detectors on both prompts and responses.

The AI Guard for Users architecture includes the following key components:

[Image: AI Guard for users architecture diagram]

1. **Signing in to ZIA**: Zscaler Client Connector authenticated users connect to ZIA in order to send traffic to it.
2. **User prompt is analyzed by AI Guard**: ZIA forwards traffic to the public GenAI apps using proxy chaining to the AI Guard infrastructure within Zscaler cloud (traditional Cloud App Controls, Web DLP, Threat Protection still occur here). If an AI Guard policy blocks the prompt, the traffic will not go to the GenAI apps.
3. **Prompts that aren't blocked are sent to GenAI apps**: If the prompt isn't blocked, AI Guard initiates (proxies) the traffic to the Gen AI apps.
4. **GenAI apps response sent to AI Guard**: The public GenAI apps return their response to AI Guard, where it is analyzed with policy response detectors if configured. If blocked, the user will instead receive a blocked message from AI Guard.
5. **Response sent through ZIA**: The response is sent back to ZIA.
6. **User receives response**: ZIA returns the response to the user.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/users-and-user-groups","lastmod":"2026-07-21T13:06Z","nid":"1541911"} -->
## Users and User Groups

- Source: https://help.zscaler.com/secure-ai-users/users-and-user-groups
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Monitoring > Users and User Groups
- Last modified: 2026-07-21T13:06Z
- Summary: Learn how to integrate Zscaler Internet Access (ZIA) Users and User Groups in AI Guard.

If you want to create AI Guard policies that are configured on a user or user group basis, you will need to enable synchronization with ZIA to import users, groups, and domains for policy evaluation.

Your ZIA tenant should already be linked with your AI Guard tenant. You should see your ZIA tenant information under the **Tenant Settings** page:

1. Under **ZIA Information**, click the **Enable ZIA User and Group Sync** toggle to sync users and groups from ZIA to AI Guard. The cloud name and organization ID will be displayed below once sync is enabled and information is available. See image.
2. Click the **Enable ZIA Domain Sync** toggle to sync domains from ZIA to AI Guard. This ensures all relevant domains are synchronized for policy evaluation.

To enable an immediate ZIA data sync outside the scheduled batch window, under **Advanced Actions**, click **Start Sync**.

After the sync is complete, go to the **AI Users** page. You will see your **Users** and **User Groups** in their associated tabs. To learn how to set a policy to apply to specific users or user groups, see [Managing AI Guard Policy Control](https://help.zscaler.com/secure-ai-users/managing-ai-guard-policy-control).

See image.

[Image: AI Guard Tenant Settings ZIA Information section showing Cloud Name and Organization ID fields]

[Image: AI Guard Users page][Image: AI Guard User Groups page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/viewing-ai-guard-system-users","lastmod":"2026-07-19T17:36Z","nid":"1540872"} -->
## Managing AI Guard System Users

- Source: https://help.zscaler.com/secure-ai-users/viewing-ai-guard-system-users
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Configuration > Managing AI Guard System Users
- Last modified: 2026-07-19T17:36Z
- Summary: How to view system users in AI Guard.

System users are managed through the Authentication Service Admin Portal, but you can view them within AI Guard. For more information about managing system users with Authentication Service, see [About Users for Authentication Service](https://help.zscaler.com/zidentity/about-users) and [Adding Users for Authentication Service](https://help.zscaler.com/zidentity/adding-users).

## Viewing System Users

To view system users:

1. In the AI Guard left-side navigation, go to **System User Management**. The **System Users** page appears.
2. On the **System Users** page, you can view the following information: See image.
  1. **Name**: Name of the system user.
  2. **Email**: Email address of the system user.
  3. **Login Type**: Displays the method of user login.
  4. **Role**: Role of the system user such as Administrator or Editor.
  5. **Created At**: Date that the system user was created.
  6. **Last Updated At**: Date that the system user's information was last updated.
  7. **Action**: All actions you are able to perform will be listed here.

## Adding System Users

To add a system user:

1. In the AI Guard left-side navigation, go to **System User Management**. The **System Users** page appears.
2. Click Add More. The **Add System User** window appears.
3. Enter the **Name**, **Email**, and **Role** for the system user and click **Submit**.

## Editing System Users

1. In the AI Guard left-side navigation, go to **System User Management**. The **System Users** page appears.
2. Under the **Action** column, click **Edit**. The **Edit System User** window appears.
3. Edit the **Name**, **Email**, or **Role** for the system user and click **Update**.

[Image: System Users page with Name, Email, Role, Created At, and Last Updated At categories visible.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/secure-ai-users/what-ai-guard","lastmod":"2026-07-21T10:50Z","nid":"1540836"} -->
## What Is AI Guard for Users?

- Source: https://help.zscaler.com/secure-ai-users/what-ai-guard
- Product: Secure Access to AI Apps
- Path: Secure Access to AI Apps Help > AI Guard for Users > Getting Started > What Is AI Guard for Users?
- Last modified: 2026-07-21T10:50Z
- Summary: A summary of AI Guard for Users and its functionality.

The rise of generative large language models (LLMs) has accelerated the pace of application innovation and increased productivity, with organizations customizing LLMs to their specific tasks. However, the proliferation of LLMs in apps and agents is creating new security challenges, including prompt injection, toxicity, and code leakage. This increasing adoption of LLMs necessitates the implementation of AI security in applications, in addition to cybersecurity and data security.

AI Guard is a service that provides run-time protection for your AI applications by enforcing enterprise policies that prevent prompt injections, block jailbreak attempts, and stop personal information leakage with guardrails around LLM interactions.

It secures these interactions by filtering harmful or inappropriate content from the prompts that users enter and the responses they receive in the AI application. AI Guard enforces intent-based detectors on both prompts and responses.

See image.

## Key Capabilities of AI Guard

The following functionality and guardrails are some of the options available to you with AI Guard:

- **Visibility & Access Control**: You can monitor which applications are accessing private AI systems, with granular controls over access permissions. Visibility tools ensure complete oversight of private AI interactions across the enterprise.
- **Prompt Injection & Jailbreak Protection**: AI models can be manipulated with adversarial prompts to bypass restrictions or produce unintended outputs. The AI Guard guardrails detect and neutralize such attacks, ensuring that AI responses stay within defined safety parameters.
- **Toxicity**: AI-generated content can potentially be emotionally harmful for workers. AI Guard's guardrails apply real-time monitoring and filtering to prevent toxic language.
- **Sensitive Data Protection**: AI models can inadvertently expose or process confidential, regulated, or proprietary information. AI Guard enables inline inspection and classification to detect and block sensitive data before it is sent to AI APIs.
- **Off-Topic Response Detection**: AI Guard detects and prevents off-topic responses, ensuring that AI stays focused on its intended use cases.
- **Malicious URL Detection & Blocking**: AI-generated responses can sometimes include harmful or phishing links. AI Guard's solution actively scans links and blocks malicious URLs to prevent security threats and safeguard users from cyber risks.
- **Language Detection & Enforcement**: Organizations can enforce AI interactions in specific approved languages, preventing unauthorized language use that can lead to miscommunication or security vulnerabilities.
- **Code Injection & Execution Detection**: AI Guard detects and prevents unauthorized code snippets embedded in AI interactions, protecting against potential security risks from malicious scripts or unauthorized automation attempts.
- **Gibberish & Low-Quality Prompt/Response Filtering**: AI models can sometimes produce nonsensical or low-value responses. Our system detects gibberish, meaningless text, and irrelevant output, ensuring that AI responses remain coherent and valuable.
- **Refusal Detection & Intervention**: When AI refuses to respond to valid queries due to overly restrictive filters or misunderstanding, our system flags and intervenes to alert the security team because it might be a denial-of-service attack.
- **Finance Advice**: AI Guard can block actionable financial guidance (investing, trading, tax, product choices); allows neutral finance facts, history, and definitions.
- **Prompt Tagging-based Access Control**: AI Guard has prompt tagging model that automatically classifies prompts into different categories, enabling organizations to enforce AI governance, security, and compliance while optimizing AI interactions.
- **Competitor Discussion Detection**: You can identify and block prompts that reference competitors, rival products, pricing comparisons, or market strategies.
- **URL Reachability Detection**: AI-provided links can sometimes be outdated or unreachable. This guardrail verifies whether URLs in prompts are accessible, safe, or broken to prevent hallucinations.
- **Legal Advice**: Block prompts seeking legal advice, interpretation, or compliance guidance. This allows for neutral legal facts, definitions, and non-legal work inquiries.

[Image: Diagram of the AI Guard workflow]
<!-- /ZS-ARTICLE -->
