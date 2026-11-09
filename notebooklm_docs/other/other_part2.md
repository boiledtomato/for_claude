# Zscaler Help — Other (part 2)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-11-09 10:16 UTC
Articles in this file: 53

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/nss-feed-output-format-dns-logs","lastmod":"2026-11-01T23:53Z","nid":"1420841"} -->
## NSS Feed Output Format: DNS Logs

- Source: https://help.zscaler.com/zero-trust-cloud/nss-feed-output-format-dns-logs
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Nanolog Streaming Service > NSS Feeds > Formatting NSS Feeds > NSS Feed Output Format: DNS Logs
- Last modified: 2026-11-01T23:53Z
- Summary: Information about DNS log fields that the Nanolog Streaming Service (NSS) sends to security information and event management (SIEM) systems.

The DNS Nanolog Streaming Service (NSS) feed specifies the data from the DNS logs that the NSS sends to the security information and event management (SIEM) system. You can configure an NSS feed by including one or more fields. The fields and their values display in the NSS feed output.

The following tables display information about the DNS fields and possible values for those fields.

## Date/Time

| Field | Description | Example |
| --- | --- | --- |
| %s{eventtime} | The time and date the transaction happened. This excludes the time zone. | Mon Oct 28 22:55:48 2024 |
| %s{time} | The date and time the event was logged. | Mon Oct 28 22:55:53 2024 |
| %s{tz} | The time zone. This is the same as the time zone you specified when you configured the NSS feed. | GMT |
| %02d{ss} | Second of timestamp (0–59) | 48 |
| %02d{mm} | Minute of timestamp (0–59) | 55 |
| %02d{hh} | Hour of timestamp (0–23) | 22 |
| %02d{dd} | Day of timestamp (1–31) | 28 |
| %02d{mth} | Month of timestamp (1-12) | 10 |
| %04d{yyyy} | Year of timestamp | 2024 |
| %s{mon} | The name of the month | Oct |
| %s{day} | The day of the week | Mon |

## Transaction Action

| Field | Description | Example |
| --- | --- | --- |
| %s{req_rulename} | Name of the rule that was applied to the DNS request | Private Access Resolver |
| %s{req_action} | Name of the action that was applied to the DNS request | REQ_ALLOW REQ_BLOCK |
| %s{res_rulename} | Name of the rule that was applied to the DNS response | Default Connector DNS Rule |
| %s{res_action} | Name of the action that was applied to the DNS response | Allow |

## Transaction Information

| Field | Description | Example |
| --- | --- | --- |
| %d{inbytes} | Number of bytes sent from the server to the client. | 10,000 |
| %d{outbytes} | Number of bytes sent from the client to the server. | 10,000 |
| %s{clt_ip} | The IP address of the user. This can be the internal IP address if it is visible—for example, traffic sent through a GRE tunnel or an internal IP address indicated using XFF. Otherwise, it's the client internet (NAT Public) IP address. | 192.0.2.135 |
| %ld{req_duration} | Duration of the DNS request in milliseconds | 65 |
| %s{resolver} | Server IP address of the request | 203.0.113.200 |
| %ld{recordid} | Unique record identifier for each log | sys_cloud_branch_id |
| %s{location} | Gateway location or sublocation of the source | Headquarters |
| %s{dom_str} | Fully Qualified Domain Name (FQDN) in the DNS request | mail.safemarch.com |
| %s{odom_str} | Obfuscated FQDN in the DNS request |  |
| %s{res_type} | Response type |  |
| %s{resp_val_str} | Response value | 51.xx.192.49 blob.xxz21prdxxxx7a.store.core.windows.net |
| %{oresp_val_str} | Obfuscated response value |  |
| %s{req_type} | DNS record being requested | A record |
| %d{srv_port} | Server port of the request | 80 |
| %d{num_resp} | Number of DNS responses | 52 |
| %s{dns_err} | DNS error, if any | DNS server not available; NXDOMAIN; SERVFAIL |
| %s{protocol} | Protocol used | TCP; UDP; DOH |
| %s{dnsgw_flags} | Flags indicating the DNS Gateway status | PRIMARY_SERVER_RESPONSE_PASS; FO_DEST_ERR |
| %s{dnsgw_slot} | DNS Gateway rule |  |

## Cloud Connector or Branch Connector Information

| Field | Description | Example |
| --- | --- | --- |
| %s{name} | Cloud Connector or Branch Connector name | CC_304 |
| %s{vm_name} | Cloud Connector or Branch Connector virtual machine (VM) name | zs-cc-vpc-xxxx |
| %s{group_name} | Cloud Connector or Branch Connector group name | zs-cc-vpc-xxxx-us-west-2x |
| %s{region_name} | Cloud Connector or Branch Connector region name | (US) West US 2 |
| %s{platform_name} | Cloud Connector or Branch Connector platform name | Azure |
| %s{platformgeo_name} | Cloud Connector or Branch Connector platform geolocation name (availability zone) | San Jose, United States |
| %s{account_id} | AWS account ID, or Azure subscription ID, or GCP project ID | 506xxx032xxx (AWS); f60abxxx-bb7d-43xx-84xx-4axxxx7xxxxx (Azure); gcs-xxx-cc-gcp-project (GCP) |

## Base64 Fields

A SIEM can have parsing issues whenever a string field has nonprintable or delimiter characters. For that reason, the Zscaler service has URL encoding for fields such as `dom_str`. Several other fields have the same parsing issue, but URL encoding is not suitable. Such fields are encoded using Base64.

Turning on Base64 encoding for all supported fields may result in approximately a 20% drop in performance.

The following fields have been added as Base64 fields:

- b64dom_str
- b64resp_val_str

## Hex-Encoded Fields

When the Zscaler service sends logs to the NSS, it hex encodes all nonprintable ASCII characters that are in URLs. Any URL character that is less than or equal to 0x20, or greater than or equal to 0x7F, is encoded as `%HH`. This ensures that your SIEM parses the URLs that contain control characters. For example, a `\n` character in a URL is encoded as `0A`, and a space is encoded as `%20`.

edom_str has been added as a hex-encoded field.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/nss-feed-output-format-event-logs","lastmod":"2026-11-01T23:53Z","nid":"1459131"} -->
## NSS Feed Output Format: Event Logs

- Source: https://help.zscaler.com/zero-trust-cloud/nss-feed-output-format-event-logs
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Nanolog Streaming Service > NSS Feeds > Formatting NSS Feeds > NSS Feed Output Format: Event Logs
- Last modified: 2026-11-01T23:53Z
- Summary: Information about Event log fields that the Nanolog Streaming Service (NSS) sends to security information and event management (SIEM) systems.

The Event NSS feed specifies the data from the Event logs that the Nanolog Streaming Service (NSS) sends to the security information and event management (SIEM) system. You can configure an NSS feed by including one or more fields. The fields and their values display in the NSS feed output.

Zscaler Cloud Connector or Branch Connector generates the Event logs when a status change occurs for a Cloud Connector or Branch Connector health check, Zscaler data center reachability, a data plane health check, a software upgrade, a control plane health check, or a tunnel to Zscaler data centers.

## Event Log Attributes

Each Event log has date and time, metadata, and event-specific attributes in it. The event-specific attributes depend on the type and severity of the event. Therefore, the values of the attributes change depending on the event type and severity.

There are 9 event-specific attributes in each Event log:

- Attribute 1 Type: The data type for the first event attribute.
- Attribute 1 Value: The value for the first event attribute.
- Attribute 2 Type: The data type for the second event attribute.
- Attribute 2 Value: The value for the second event attribute.
- Attribute 3 Type: The data type for the third event attribute.
- Attribute 3 Value: The value for the third event attribute.
- Attribute 4 Type: The data type for the fourth event attribute.
- Attribute 4 Value: The value for the fourth event attribute.
- Error Code: The error code associated with an event if an error code is available.

The following list shows all the possible Event Type/Severity combinations that can occur:

- ZIA_TUNNEL/Info
- ZIA_TUNNEL/Error
- ZIA_GATEWAY/Warn
- ZIA_GATEWAY/Warn
- ZIA_GATEWAY/Error
- ZIA_GATEWAY/Info
- SVC_HEALTH_CHECK/Info
- SVC_HEALTH_CHECK/Error
- INST_HEALTH_CHECK/Warn
- INST_HEALTH_CHECK/Info
- CONTROL_PLANE/Info
- CONTROL_PLANE/Error
- DATA_PLANE/Info
- DATA_PLANE/Error
- UPGRADE_START/Info
- UPGRADE_END/Info
- UPGRADE_END/Error

- Event Name: ZIA_TUNNEL
- Severity: Info
- Attribute 1 Type: Previous State
- Attribute 1 Value: Inactive
- Attribute 2 Type: Current State
- Attribute 2 Value: Active
- Attribute 3 Type: Gateway Name
- Attribute 3 Value: <gw_name>
- Attribute 4 Type: Tunnel Destination IP Address
- Attribute 4 Value: <Tunnel Destination IP Address>
- Error Code: N/A

- Event Name: ZIA_TUNNEL
- Severity: Error
- Attribute 1 Type: Previous State
- Attribute 1 Value: Active
- Attribute 2 Type: Current State
- Attribute 2 Value: Inactive
- Attribute 3 Type: Gateway Name
- Attribute 3 Value: <gw_name>
- Attribute 4 Type: Tunnel Destination IP Address
- Attribute 4 Value: <Tunnel Destination IP Address>
- Error Code: <error_code>

- Event Name: ZIA_GATEWAY
- Severity: Warn
- Attribute 1 Type: Previous State
- Attribute 1 Value: Secondary
- Attribute 2 Type: Current State
- Attribute 2 Value: Primary
- Attribute 3 Type: Gateway Name
- Attribute 3 Value: <gw_name>
- Attribute 4 Type: N/A
- Attribute 4 Value: N/A
- Error Code: N/A

- Event Name: ZIA_GATEWAY
- Severity: Warn
- Attribute 1 Type: Previous State
- Attribute 1 Value: Primary
- Attribute 2 Type: Current State
- Attribute 2 Value: Secondary
- Attribute 3 Type: Gateway Name
- Attribute 3 Value: <gw_name>
- Attribute 4 Type: N/A
- Attribute 4 Value: N/A
- Error Code: N/A

- Event Name: ZIA_GATEWAY
- Severity: Error
- Attribute 1 Type: Previous State
- Attribute 1 Value: Active
- Attribute 2 Type: Current State
- Attribute 2 Value: Inactive
- Attribute 3 Type: Gateway Name
- Attribute 3 Value: <gw_name>
- Attribute 4 Type: N/A
- Attribute 4 Value: N/A
- Error Code: N/A

- Event Name: ZIA_GATEWAY
- Severity: Info
- Attribute 1 Type: Previous State
- Attribute 1 Value: Inactive
- Attribute 2 Type: Current State
- Attribute 2 Value: Active
- Attribute 3 Type: Gateway Name
- Attribute 3 Value: <gw_name>
- Attribute 4 Type: N/A
- Attribute 4 Value: N/A
- Error Code: N/A

- Event Name: SVC_HEALTH_CHECK
- Severity: Info
- Attribute 1 Type: Previous State
- Attribute 1 Value: Inactive
- Attribute 2 Type: Current State
- Attribute 2 Value: Active
- Attribute 3 Type: N/A
- Attribute 3 Value: N/A
- Attribute 4 Type: N/A
- Attribute 4 Value: N/A
- Error Code: N/A

- Event Name: SVC_HEALTH_CHECK
- Severity: Error
- Attribute 1 Type: Previous State
- Attribute 1 Value: Active
- Attribute 2 Type: Current State
- Attribute 2 Value: Inactive
- Attribute 3 Type: N/A
- Attribute 3 Value: N/A
- Attribute 4 Type: N/A
- Attribute 4 Value: N/A
- Error Code: N/A

- Event Name: INST_HEALTH_CHECK
- Severity: Warn
- Attribute 1 Type: Previous State
- Attribute 1 Value: Active
- Attribute 2 Type: Current State
- Attribute 2 Value: Degraded
- Attribute 3 Type: Number of active instances
- Attribute 3 Value: <integer>
- Attribute 4 Type: Total instances
- Attribute 4 Value: <integer>
- Error Code: N/A

- Event Name: INST_HEALTH_CHECK
- Severity: Info
- Attribute 1 Type: Previous State
- Attribute 1 Value: Degraded
- Attribute 2 Type: Current State
- Attribute 2 Value: Active
- Attribute 3 Type: Number of active instances
- Attribute 3 Value: <integer>
- Attribute 4 Type: Total instances
- Attribute 4 Value: <integer>
- Error Code: N/A

- Event Name: CONTROL_PLANE
- Severity: Info
- Attribute 1 Type: Previous State
- Attribute 1 Value: Inactive
- Attribute 2 Type: Current State
- Attribute 2 Value: Active
- Attribute 3 Type: N/A
- Attribute 3 Value: N/A
- Attribute 4 Type: N/A
- Attribute 4 Value: N/A
- Error Code: <error_code>

- Event Name: CONTROL_PLANE
- Severity: Error
- Attribute 1 Type: Previous State
- Attribute 1 Value: Active
- Attribute 2 Type: Current State
- Attribute 2 Value: Inactive
- Attribute 3 Type: N/A
- Attribute 3 Value: N/A
- Attribute 4 Type: N/A
- Attribute 4 Value: N/A
- Error Code: N/A

- Event Name: DATA_PLANE
- Severity: Info
- Attribute 1 Type: Previous State
- Attribute 1 Value: Inactive
- Attribute 2 Type: Current State
- Attribute 2 Value: Active
- Attribute 3 Type: N/A
- Attribute 3 Value: N/A
- Attribute 4 Type: N/A
- Attribute 4 Value: N/A
- Error Code: N/A

- Event Name: DATA_PLANE
- Severity: Error
- Attribute 1 Type: Previous State
- Attribute 1 Value: Active
- Attribute 2 Type: Current State
- Attribute 2 Value: Inactive
- Attribute 3 Type: N/A
- Attribute 3 Value: N/A
- Attribute 4 Type: N/A
- Attribute 4 Value: N/A
- Error Code: N/A

- Event Name: UPGRADE_START
- Severity: Info
- Attribute 1 Type: Current Version
- Attribute 1 Value: <version>
- Attribute 2 Type: New Version
- Attribute 2 Value: <version_1>
- Attribute 3 Type: Package Name
- Attribute 3 Value: <janus>
- Attribute 4 Type: N/A
- Attribute 4 Value: N/A
- Error Code: N/A

- Event Name: UPGRADE_END
- Severity: Info
- Attribute 1 Type: Current Version
- Attribute 1 Value: <version>
- Attribute 2 Type: Upgrade Status
- Attribute 2 Value: Success for Info, Fail for Error
- Attribute 3 Type: Package Name
- Attribute 3 Value: <janus/edgeconnector>
- Attribute 4 Type: N/A
- Attribute 4 Value: N/A
- Error Code: N/A

- Event Name: UPGRADE_END
- Severity: Error
- Attribute 1 Type: Current Version
- Attribute 1 Value: <version>
- Attribute 2 Type: Upgrade Status
- Attribute 2 Value: Success for Info, Fail for Error
- Attribute 3 Type: Package Name
- Attribute 3 Value: <janus/edgeconnector>
- Attribute 4 Type: N/A
- Attribute 4 Value: N/A
- Error Code: N/A

The following tables display information about the Event fields and possible values for those fields.

## Date/Time

| **Field** | **Description** | **Example** |
| --- | --- | --- |
| %d{inteventtime} | Event time in epoch format | 1687170925 |
| %d{abstime} | Event time if logs are delayed | 0 |
| %s{time} | Time at which events are generated | Mon Oct 14 03:35:25 2024 |
| %s{tz} | Time zone specified when configuring the NSS feed | GMT |
| %02d{ss} | Second of timestamp (0–59) | 25 |
| %02d{mm} | Minute of timestamp (0–59) | 35 |
| %02d{hh} | Hour of timestamp (0-23) | 03 |
| %02d{dd} | Day of timestamp (1–31) | 14 |
| %02d{mth} | Month of timestamp (1-12) | 10 |
| %04d{yyyy} | Year of timestamp | 2024 |
| %s{day} | Name of the day | Mon |
| %s{mon} | Name of the month | Oct |
| %s{rtime} | Log time at which NSS receives the logs | Mon Oct 14 03:35:28 PDT 2024 |
| %s{rmon} | Name of the month | Oct |

## Metadata

| **Field** | **Description** | **Example** |
| --- | --- | --- |
| %s{company} | Company name | Test2 |
| %s{location} | Location name | TempLoc |
| %s{name} | Cloud Connector or Branch Connector name | Instance 5 |
| %s{vm_name} | Cloud Connector or Branch Connector virtual machine (VM) name | zs-cc-vps-xxxx |
| %s{group_name} | Cloud Connector or Branch Connector group name | zs-cc-vpc-xxxx-us-east-2-x |
| %s{platform_name} | Cloud Connector or Branch Connector platform name | AWS |
| %s{platformgeo_name} | Cloud Connector or Branch Connector platform geolocation name | Columbus, United States |
| %s{region_name} | Region name | vk_location-us-east-2-x |
| %s{connector_type} | The type of connector | CC |
| %s{event_name} | Event type | VM_HEALTH; ZIA_TUNNEL; ZIA_GW; SVC_HEALTH_CHK; INST_HEALTH_CHK; CTL_PLANE; DATA_PLANE; UPGRADE_ST; UPGRADE_END |
| %s{category} | Category | TRAFF_FWD; CONN_VM; CONN_INST |
| %s{severity} | Severity | ERR; INFO; WARN |

## Events

| **Field** | **Description** | **Example** |
| --- | --- | --- |
| %s{attr1_name}, %s{attr1_val} | Attribute 1 name, Attribute 1 string value | prev_state, Inactive |
| %s{attr2_name},%s{attr2_val} | Attribute 2 name, Attribute 2 string value | curr_state, Active |
| %s{attr3_name}, %s{attr3_val} | Attribute 3 name, Attribute 3 string value | gid, 613x53 |
| %s{attr4_name}, %s{attr4_val} | Attribute 4 name, Attribute 4 string value | gwip, 192.0.2.1 |
| %s{error_code} | Error code (if any) | None |
| %s{account_id} | Account ID (alphanumeric string) | subscription_id2 |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/nss-feed-output-format-metrics-logs","lastmod":"2026-11-01T23:53Z","nid":"1459136"} -->
## NSS Feed Output Format: Metrics Logs

- Source: https://help.zscaler.com/zero-trust-cloud/nss-feed-output-format-metrics-logs
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Nanolog Streaming Service > NSS Feeds > Formatting NSS Feeds > NSS Feed Output Format: Metrics Logs
- Last modified: 2026-11-01T23:53Z
- Summary: Information about Metrics log fields that the Nanolog Streaming Service (NSS) sends to security information and event management (SIEM) systems.

The Metrics Nanolog Streaming Service (NSS) feed specifies the data from the Metrics logs that the NSS sends to the security information and event management (SIEM) system. You can configure an NSS feed by including one or more fields. The fields and their values display in the NSS feed output.

There are three types of Metrics logs that each Zscaler Cloud Connector or Branch Connector generates:

- VM: Metrics that are applicable at the virtual machine (VM) level.
- Instance: Metrics that are applicable at the level of an individual data-processing instance. There are multiple data-processing instances in a single Cloud Connector or Branch Connector.
- Resource: Metrics that are applicable at an individual resource level. There are physical and logical resources in a Cloud Connector or Branch Connector that all data-processing instances use.

The following tables display information about the Metrics fields and possible values for those fields.

## VM

VM Metrics logs are applicable to an entire Cloud Connector or Branch Connector VM. These metrics are collected at one-minute intervals. There is one log line per minute for each Cloud Connector or Branch Connector.

### Date/Time

| **Field** | **Description** | **Example** |
| --- | --- | --- |
| %s{tz} | The time zone. This is the time zone you specified when you configured the NSS feed. | GMT |
| %s{time} | Time at which events are generated | Thu Nov 14 11:39:59 2024 |
| %s{eventtime} | The time and date of the transaction. This excludes the time zone. | Thu Nov 14 11:39:59 2024 |
| %d{inteventtime} | Event time in epoch format | 1691667599 |
| %02d{ss} | Second of timestamp (0–59) | 59 |
| %02d{mm} | Minute of timestamp (0–59) | 39 |
| %02d{hh} | Hour of timestamp (0–23) | 11 |
| %02d{dd} | Day of timestamp (1–31) | 14 |
| %02d{mth} | Month of timestamp (1-12) | 11 |
| %04d{yyyy} | Year of timestamp | 2024 |
| %s{day} | Name of the day | Thu |
| %s{mon} | Name of the month | Nov |
| %s{rtime} | Log time at which NSS receives logs | Thu Aug 14 11:40:33 2024 |
| %02d{rss} | Second of timestamp (0–59) | 33 |
| %02d{rmm} | Minute of timestamp (0–59) | 40 |
| %02d{rhh} | Hour of timestamp (0–23) | 11 |
| %02d{rdd} | Day of timestamp (1–31) | 14 |
| %02d{rmth} | Month of stamp (1-12) | 11 |
| %04d{ryyyy} | Year of timestamp | 2024 |
| %s{rday} | Name of the day | Thu |
| %s{rmon} | Name of the month | Nov |

### Metadata

| **Field** | **Description** | **Example** |
| --- | --- | --- |
| %s{recordid} | Unique record identifier for each log | sys_cloud_branch_id |
| %s{location} | Location name | bc-location-0 |
| %s{group_name} | Group name | bc-group-0 |
| %s{vm_name} | VM name | bc-group-0-VM-yVIbR |
| %s{platform_name} | Platform name | VMware ESXi |
| %s{platformgeo_name} | Platform geolocation name (Cloud Connector only) | N/A |
| %s{region_name} | Region name (Cloud Connector only) | N/A |
| %s{connector_type} | Connector type | Branch Connector |
| %d{resourceno} | Resource number |  |

### Metrics

| **Field** | **Description** | **Example** |
| --- | --- | --- |
| %s{syscpu} | VM system CPU | 0.09% |
| %s{cpu} | VM overall CPU | 0.21% |
| %s{sendbytes} | Total sent bytes | 11366860611 |
| %s{rcvdbytes} | Total received bytes | 9373237430 |
| %s{totalmem} | Total memory in bytes | 29290921984 |
| %s{memutil} | System memory utilized | 2746220544 |
| %s{totthroughput} | Total throughput in bytes per second | 8978 |
| %s{mgmtcpu} | Control Plane CPU | 0.04% |
| %s{mgmtmem} | Control Plane Memory | 2929092 |
| %s{nodata} | Data not available | True/False |

VM NSS feeds consist of all metrics at the VM level, such as CPU and memory. You can use the VM-level Metrics logs to monitor resource usage at the VM level. For example, you can configure alerts on the SIEM side to monitor the CPU or memory utilization when they cross a defined threshold.

## Instance

Each Cloud Connector or Branch Connector has between one and three data-processing instances, depending on the form factor. This feed is used for metrics specific to a data-processing instance. These metrics are collected at one-minute intervals. There is one log line per minute for each data-processing instance.

### Date/Time

| **Field** | **Description** | **Example** |
| --- | --- | --- |
| %s{tz} | The time zone. This is the time zone you specified when you configured the NSS feed. | GMT |
| %s{time} | Time at which events are generated | Thu Nov 14 11:39:59 2024 |
| %s{eventtime} | The time and date of the transaction. This excludes the time zone. | Thu Nov 14 11:39:59 2024 |
| %d{inteventtime} | Event time in epoch format | 1691667599 |
| %02d{ss} | Second of timestamp (0–59) | 59 |
| %02d{mm} | Minute of timestamp (0–59) | 39 |
| %02d{hh} | Hour of timestamp (0–23) | 11 |
| %02d{dd} | Day of timestamp (1 –31) | 14 |
| %02d{mth} | Month of timestamp (1-12) | 11 |
| %04d{yyyy} | Year of timestamp | 2024 |
| %s{day} | Name of the day | Thu |
| %s{mon} | Name of the month | Nov |
| %s{rtime} | Log time at which NSS receives logs | Thu Nov 14 11:40:33 2024 |
| %02d{rss} | Second of timestamp (0–59) | 33 |
| %02d{rmm} | Minute of timestamp (0–59) | 40 |
| %02d{rhh} | Hour of timestamp (0–23) | 11 |
| %02d{rdd} | Day of timestamp (1–31) | 14 |
| %02d{rmth} | Month of timestamp (1-12) | 11 |
| %04d{ryyyy} | Year of timestamp | 2024 |
| %s{rday} | Name of the day | Thu |
| %s{rmon} | Name of the month | Nov |

### Metadata

| **Field** | **Description** | **Example** |
| --- | --- | --- |
| %s{recordid} | Unique record identifier for each log | sys_cloud_branch_id |
| %s{location} | Location name | bc-location-0 |
| %s{group_name} | Group name | bc-group-0 |
| %s{vm_name} | VM name | bc-group-0-VM-yVIbR |
| %s{platform_name} | Platform name | VMware ESXi |
| %s{platformgeo_name} | Platform geolocation name | N/A |
| %s{region_name} | Region name | N/A |
| %s{connector_type} | Connector type | Branch Connector |
| %s{instance_name} | Instance name | bc-group-0-VM-y; VIbR_INSTANCE_1_3jHxn |

### Metrics

| **Field** | **Description** | **Example** |
| --- | --- | --- |
| %s{icpu} | CPU utilization for the data-processing instance | 0.12 |
| %s{imem} | Memory utilization for the data-processing instance | 2746220544 |
| %s{ithroughput_tx} | Throughput in bytes per second for the data transferred out from the instance | 2685 |
| %s{ithroughput_rx} | Throughput in bytes per second for the data that the instance received | 3434 |
| %s{ziathroughput_tx} | Throughput in bytes per second for the data sent to Internet & SaaS from the instance | 1542 |
| %s{ziathroughput_rx} | Throughput in bytes per second for the data that the instance received from Internet & SaaS | 2658 |
| %s{nodata} | Data not available | False |

Instance NSS feeds contain metrics at the instance level. Users can monitor the instance-level resource usage, such as the CPU, memory, and throughput. There is also a `%s{nodata}` field, which is set to `True` when no data is available. This value indicates an issue on the connector instance service in which the metrics samples were not collected.

## Resource

Each Cloud Connector or Branch Connector has physical or logical resources, such as disk partitions and Network Interface Cards (NICs). Resources are used for metrics specific to a resource. These metrics are collected at one-minute intervals. There is one log line per minute for each resource.

The log line for each resource type has a set of applicable metrics. Metrics that are not applicable to that resource are set to zero or absent from the log line. The applicable set of metrics depends on the resource type.

### Date/Time

| **Field** | **Description** | **Example** |
| --- | --- | --- |
| %s{tz} | The time zone. This is the time zone you specified when you configured the NSS feed. | GMT |
| %s{time} | Time at which events are generated | Thu Nov 14 11:39:59 2024 |
| %s{eventtime} | The time and date of the transaction. This excludes the time zone. | Thu Nov 14 11:40:01 2024 |
| %d{inteventtime} | Event time in epoch format | 1691667599 |
| %02d{ss} | Second of timestamp (0–59) | 59 |
| %02d{mm} | Minute of timestamp (0–59) | 39 |
| %02d{hh} | Hour of timestamp (0–23) | 11 |
| %02d{dd} | Day of timestamp (1–31) | 14 |
| %02d{mth} | Month of timestamp (1-12) | 11 |
| %04d{yyyy} | Year of timestamp | 2024 |
| %s{day} | Name of the day | Thu |
| %s{mon} | Name of the month | Nov |
| %s{rtime} | Log time at which NSS receives logs | Thu Nov 14 11:40:33 2024 |
| %02d{rss} | Second of timestamp (0–59) | 33 |
| %02d{rmm} | Minute of timestamp (0–59) | 40 |
| %02d{rhh} | Hour of timestamp (0–23) | 11 |
| %02d{rdd} | Day of timestamp (1–31) | 14 |
| %02d{rmth} | Month of timestamp (1-12) | 11 |
| %04d{ryyyy} | Year of timestamp | 2024 |
| %s{rday} | Name of the day | Thu |
| %s{rmon} | Name of the month | Nov |

### Metadata

| **Field** | **Description** | **Example** |
| --- | --- | --- |
| %d{recordid} | Unique record identifier for each log | sys_cloud_branch_id |
| %s{location} | Location name | bc-location-0 |
| %s{group_name} | Group name | bc-group-0 |
| %s{vm_name} | VM name | bc-group-0-VM-yVIbR |
| %s{platform_name} | Platform name | VMware ESXi |
| %s{platformgeo_name} | Platform geolocation name (Cloud Connector only) | N/A |
| %s{region_name} | Region name (Cloud Connector only) | N/A |
| %s{connector_type} | Connector type | Branch Connector |
| %s{resourcename} | Resource name | FS_ufs_/sc_/dev/vtbd1p1 |

### Metrics

| **Field** | **Description** | **Example** |
| --- | --- | --- |
| %s{rdiskutil} | Resource disk utilized | 30.37% |
| %s{rdiskused} | Disk space used in bytes | 87193907200 |
| %s{rdiskfree} | Disk space free in bytes | 224806400000 |

Resource NSS feeds consist of metrics at the resource level. For example, a resource can be a disk partition. You can set up your SIEM to monitor the usage of disk partitions and send notifications when necessary. Resource Metrics are present in the feed if that counter is applicable to the resource type to which the metric is published. For example, metrics related to disk usage are available for the disk resource type.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/nss-feed-output-format-session-logs","lastmod":"2026-11-01T23:53Z","nid":"1420836"} -->
## NSS Feed Output Format: Session Logs

- Source: https://help.zscaler.com/zero-trust-cloud/nss-feed-output-format-session-logs
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Nanolog Streaming Service > NSS Feeds > Formatting NSS Feeds > NSS Feed Output Format: Session Logs
- Last modified: 2026-11-01T23:53Z
- Summary: Information about the Session fields that the Nanolog Streaming Service (NSS) sends to security information and event management (SIEM) systems.

The Session Nanolog Streaming Service (NSS) feed specifies the data from the Session logs that the NSS sends to the security information and event management (SIEM) system. You can configure an NSS feed by including one or more fields. The fields and their values display in the NSS feed output.

The following tables display information about the Session fields and possible values for those fields.

## Date/Time

| Field | Description | Example |
| --- | --- | --- |
| %s{time} | The log time (i.e., when the NSS logs a transaction) | Tue Nov 12 22:55:48 2024 |
| %s{eventtime} | The time and date of the transaction. This excludes the time zone. | Tue Nov 12 22:55:48 2024 |
| %s{tz} | The time zone. This is the same as the time zone you specified when you configured the NSS feed. | GMT |
| %02d{ss} | Second of timestamp (0–59) | 48 |
| %02d{mm} | Minute of timestamp (0–59) | 55 |
| %02d{hh} | Hour of timestamp (0–23) | 22 |
| %02d{dd} | Day of timestamp (1–31) | 12 |
| %02d{mth} | Month of timestamp (1-12) | 11 |
| %04d{yyyy} | Year | 2024 |
| %s{mon} | The name of the month | Nov |
| %s{day} | The day of the week | Tue |

## Client Information

| Field in Logs | Description | Example |
| --- | --- | --- |
| %s{clt_dst_name} | Client destination host name | Hostname |
| %s{oclt_dest_name} | Obfuscated client destination host name |  |
| %s{clt_src_ip} | Client source IPv4 address. For aggregated sessions, this is the client source IP address of the last session in the aggregate. | 192.0.2.6 |
| %d{clt_src_port} | Client source port. For aggregated sessions, this is the client source port of the last session in the aggregate. | 22 |
| %s{clt_dst_ip} | Client destination IPv4 address. For aggregated sessions, this is the client destination IP address of the last session in the aggregate. | 192.0.2.184 |
| %d{clt_dst_port} | Client destination port. For aggregated sessions, this is the client destination port of the last session in the aggregate. | 80 |
| %s{fwdtypename} | Traffic forwarding method used to send the traffic to the firewall | L2 tunnel |
| %s{zapptunname} | Zscaler Client Connector tunnel name used to send the traffic to the firewall |  |

## Server Information

| Field in Logs | Description | Example |
| --- | --- | --- |
| %d{srv_dst_port} | Server destination port. For aggregated sessions, this is the server destination port of the last session in the aggregate. | 443 |
| %d{gw_dst_port} | Gateway destination port | 443 |
| %d{zia_src_port} | Internet & SaaS source port | 443 |
| %s{srv_dst_ip} | Server destination IPv4 address. For aggregated sessions, this is the server destination IP address of the last session in the aggregate. | 203.0.113.199 |
| %s{gw_name} | Gateway name | FWD_1 |
| %s{ogw_name} | Obfuscated gateway name |  |
| %s{gw_dst_ip} | Gateway destination IPv4 address | 203.0.113.1 |
| %s{zia_src_ip} | Internet & SaaS source IPv4 address | 198.51.100.48 |
| %s{srv_src_ip} | Server source IPv4 address. For aggregated sessions, this is the server source IP address of the last session in the aggregate. | 203.0.113.64 |
| %d{srv_src_port} | Server source port. For aggregated sessions, this is the server source port of the last session in the aggregate. | 22 |
| %s{srv_ip_country} | Abbreviated code of the country of the server IP address | USA |

## Session Information

| Field | Description | Example |
| --- | --- | --- |
| %ld{session_duration} | Session duration in milliseconds | 600,000 |
| %d{num_sess} | Number of sessions | 36 |
| %d{num_aggr_sess} | Number of sessions that were aggregated | 5 |
| %s{aggrname} | Type of aggregation |  |

## Forwarding Control

| Field | Description | Example |
| --- | --- | --- |
| %s{rdrrulename} | Name of redirect rule that was applied to the transaction | FWD_Rule_1 |
| %s{ordrrulename} | Obfuscated redirect rule name |  |
| %s{self_rulename} | Cloud Connector or Branch Connector self-rule name that was applied to the transaction | CONNECTOR_SELF |
| %s{oself_rulename} | Obfuscated Cloud Connector or Branch Connector self rule name | AVG |

## Transaction Information

| Field | Description | Example |
| --- | --- | --- |
| %ld{clt_tx_bytes} | Number of bytes sent from the server to the client | 10,000 |
| %ld{clt_rx_bytes} | Number of bytes sent from the client to the server | 10,000 |
| %s{nw_service} | The network service that was used | HTTP |
| %s{traffic_type} | Traffic type | INTERNAL |
| %s{clt_nw_protocol} | Client network protocol | TCP |
| %s{srv_nw_protocol} | Server network protocol | TCP |
| %s{zia_gw_protocol} | Internet & SaaS gateway protocol |  |
| %s{zpa_appseg} | Private Access application segment | Any |

## Cloud & Branch Connector Information

| Field | Description | Example |
| --- | --- | --- |
| %s{name} | Cloud Connector or Branch Connector name | CC_304 |
| %s{vm_name} | Cloud Connector or Branch Connector virtual machine (VM) name | zs-cc-vpc-xxxx |
| %s{group_name} | Cloud Connector or Branch Connector group name | zs-cc-vpc-xxxx-us-west-2x |
| %s{region_name} | Cloud Connector or Branch Connector region name | (US) West US 2 |
| %s{location} | Cloud Connector or Branch Connector location | Headquarters |
| %s{platform_name} | Cloud Connector or Branch Connector platform name | Azure |
| %s{platformgeo_name} | Cloud Connector or Branch Connector platform geolocation name (availability zone) | San Jose, United States |
| %s{account_id} | Amazon Web Services (AWS) account ID, Microsoft Azure subscription ID, or Google Cloud Platform (GCP) project ID | 506xxx032xxx (AWS); f60abxxx-bb7d-43xx-84xx-4axxxx7xxxxx (Azure); gcs-xxx-cc-gcp-project (GCP) |

## Base64 Fields

A SIEM can have parsing issues whenever a string field has nonprintable or delimiter characters. For that reason, the Zscaler service has URL encoding for fields such as `clt_dst_name`. There are several other fields that have the same parsing issue, but URL encoding is not suitable. Such fields are encoded using Base64.

Turning on Base64 encoding for all supported fields may result in approximately a 20% drop in performance.

The following fields have been added as Base64 fields:

- b64clt_dst_name
- b64gw_name
- b64rdrrulename
- b64self_rulename

## Hex-Encoded Fields

When the Zscaler service sends logs to the NSS, it hex encodes all nonprintable ASCII characters that are in URLs. Any URL character that is less than or equal to 0x20, or greater than or equal to 0x7F, is encoded as `%HH`. This ensures that your SIEM can parse the URLs that contain control characters. For example, a `\n` character in a URL is encoded as `%0A`, and a space is encoded as `%20`.

- eclt_dst_name
- egw_name
- erdrrulename
- eself_rulename
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/registering-endpoint-amazon-web-services","lastmod":"2026-11-01T23:53Z","nid":"1519146"} -->
## Registering an Endpoint in Amazon Web Services

- Source: https://help.zscaler.com/zero-trust-cloud/registering-endpoint-amazon-web-services
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Zero Trust Gateway Management > AWS Zero Trust Gateway Management > Registering an Endpoint in Amazon Web Services
- Last modified: 2026-11-01T23:53Z
- Summary: Information on how to register an endpoint in Amazon Web Services (AWS).

Zero Trust Gateways allow you to register an endpoint in Amazon Web Services (AWS). Registering your endpoint with the Zscaler service allows you to route and secure your traffic to the Zero Trust Exchange (ZTE).

After you [add an AWS Zero Trust Gateway](https://help.zscaler.com/cloud-branch-connector/adding-amazon-web-services-zero-trust-gateway), you can use the service address to register the endpoint.

- 1. Copy and save the endpoint service name.
- 2. Register an endpoint.
- 3. Configure the route table.

To copy the endpoint service name:

1. In the Zscaler Admin Console, from the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Zero Trust Gateway** > **AWS**.
2. On the **AWS** Zero Trust Gateway page, select your gateway by clicking its name. See image.
3. On the **Dashboard** tab, copy and save the **Endpoint Service Name**. You use it when creating an endpoint in AWS. See image.

To register an endpoint:

1. Sign in to the AWS Management Console.
2. Click **VPC**. The **VPC dashboard** appears. Endpoints are deployed in the virtual private cloud (VPC). If you want to use centralized deployment, use the transit gateway. To learn more, refer to the [AWS documentation](https://aws.amazon.com/blogs/networking-and-content-delivery/centralized-inspection-architecture-with-aws-gateway-load-balancer-and-aws-transit-gateway/).
3. On the **VPC dashboard**, from the left-side navigation, under **PrivateLink and Lattice**, select **Endpoints**. See image.
4. On the **Endpoints** page, click **Create endpoint**. See image.
5. On the **Create endpoint** page, configure the following:
  1. In the **Endpoint settings** section:
    - **Name tag**: Enter a name for your endpoint.
    - **Type**: Select **Endpoint services that use NLBs and GWLBs**. See image.
  2. In the **Services** section:
    - **Service name**: Paste the **Endpoint Service Name** from the Zscaler Admin Console and click **Verify service**.
    - **Service Region**: Leave this setting unchanged. See image.
  3. In the **Network settings** section, under **VPC**, from the **Select a VPC** drop-down menu, select the VPC where you want to deploy the endpoint. See image.
  4. In the **Subnets** section, select the **Availability Zone** where you want to deploy the endpoint by selecting the checkbox next to the name of the **Availability Zone**. See image.
  5. In the **Tags** section, leave the settings unchanged.
  6. Click **Create endpoint**.

The endpoint is deployed.

After deploying the endpoint, you must route traffic through it. Traffic passes through the Zero Trust Gateway. To configure the route table, navigate to the **Route tables** page and click **Edit routes** to configure the following:

- **Destination**: (Optional) Enter an IP address.
- **Target**: From the drop-down menu, select **Gateway Load Balancer Endpoint**. Under the **Gateway Load Balancer Endpoint** field, in the drop-down menu, select your endpoint.

Click **Save changes**.

See image.

[Image: Selecting Endpoints under PrivateLink and Lattice on the VPC dashboard page of the AWS Management Console]

[Image: Selecting Create endpoint on the Endpoints page of the AWS Management Console. On this page, there is a table with columns for Name, VPC endpoint ID, Endpoint type, Status, Service name, and Service network. In the upper-right corner, there is a drop-down menu labeled "Actions" and an orange button labeled "Create endpoint."]

[Image: Configuring settings in the Endpoint settings section of the Create endpoint page in the AWS Management Console]

[Image: Pasting the Service name in the Service settings section of the Create endpoint page in the AWS Management Console]

[Image: Selecting your VPC from the Network settings section of the Create endpoint page in the AWS Management Console]

[Image: A table in the AWS Management Console with checkboxes and columns labeled Availability Zone, Subnet ID, Designate IP addresses, IPv4 address, and IPv6 address. The checkbox for the first line of the table is selected.]

[Image: The Edit routes page with columns labeled Destination, Target, Status, and Propagated in the AWS Management Console.]

[Image: Selecting your Zero Trust Gateway from the Zero Trust Gateway page in the Zscaler Admin Console]

[Image: Copying and saving the Endpoint Service Name on the Dashboard tab of your gateway in the Zscaler Admin Console]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/registering-virtual-private-cloud-google-cloud-platform-zero-trust-gateway","lastmod":"2026-11-01T23:53Z","nid":"1542803"} -->
## Registering a Virtual Private Cloud with the Google Cloud Platform Zero Trust Gateway

- Source: https://help.zscaler.com/zero-trust-cloud/registering-virtual-private-cloud-google-cloud-platform-zero-trust-gateway
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Zero Trust Gateway Management > GCP Zero Trust Gateway Management > Registering a Virtual Private Cloud with the Google Cloud Platform Zero Trust Gateway
- Last modified: 2026-11-01T23:53Z
- Summary: Information on how to register your virtual private cloud (VPC) with the Google Cloud Platform (GCP) Zero Trust Gateway.

This article instructs you on how to route traffic from your Google Cloud virtual private cloud (VPC) through the Zscaler Google Cloud Platform (GCP) Zero Trust Gateway using Network Security Integration (NSI) in-band integration in the GCP console.

## Prerequisites

Before you begin configuring your VPC to route traffic through GCP gateways, ensure that you have:

- Your consumer project details, such as where the endpoint group is deployed.
- Project information which is used to create the security profile. If the security profile is created at the organization level, organization details are required.
- An existing VPC network to protect. NSI does not create a VPC network.
- Ingress source and egress destination CIDR ranges to inspect.
- IAM roles to manage compute firewall policies and Network Security resources. The Security Profile Admin role is needed for security profiles and groups. If you are using organization scope, ensure that you have organization-level roles.
- The Compute Engine and Network Security APIs enabled.
- (Optional) Trusted source ranges that bypass inspection.

Zscaler provides:

- The intercept deployment group.
- Any location guidance (i.e., NSI consumer resources use global) and recommended traffic match ranges.

## Registering a VPC

To register a VPC with the GCP gateway:

- Step 1: Create the Intercept Endpoint Group
- Step 2: Create the Custom Intercept Security Profile
- Step 3: Create the Security Profile Group
- Step 4: Create the Firewall Policy and Attach It to Your VPC
- Step 5: Verify the Registration

1. Log in to the GCP console.
2. In the **Search (/) for resources, docs, products, and more** field, enter `Endpoint groups`.
3. Select **Endpoint groups**. The **Endpoint groups** page appears.
4. On the **Endpoints groups** page, click **Create endpoint group**. The **Create an endpoint group** page appears.
5. On the **Create an endpoint group** page:
  1. On the **Basic configuration** tab, configure the following: See image.
    - **Name**: Enter a name (e.g. `customer-nsi-intercept-endpoint-group`).
    - **Description**: Enter a description.
    - **Purpose**: Select **NSI In-Band**.
    - **Type**: By default, this field is set to **Intercept**.
    - **Deployment Group**: Select one of the following options to determine how to point to the Zscaler-provided group:
      - **Select project**: Select the project that hosts the group.
      - **Select current project**: If the deployment group is in this project, select its name.
      - **Enter manually**: Enter the project ID and name where the group is located in a different project. This is the most common case for a Zscaler producer project.
  2. Click **Continue**.
  3. On the **Associations** tab, under **Associations**, click **Add endpoint group association**. The **New endpoint group association** section appears.
  4. In the **New endpoint group association** section: See image.
    - **Project**: Select the project to protect.
    - **Network**: From the drop-down menu, select the VPC network to protect.
  5. Click **Done**.
  6. Click **Create**.

The endpoint group is created.

1. In the GCP console, in the **Search (/) for resources, docs, products, and more** field, enter `Security profiles`.
2. Select **Security profiles**. The **Security profiles** page appears.
3. On the **Security profiles** page, on the **Security profiles** tab, click **Create profile**. The **Create security profile** page appears.
4. On the **Create security profile** page: See image.
  - **Name**: Enter a name (e.g. `customer-nsi-custom-intercept-profile`).
  - **Description**: Enter a description.
  - **Purpose**: Select **NSI In-Band**.
  - **Type**: By default, this field is set to **Custom**.
  - **Traffic directed to**: Select the deployment group you selected in Step 1.
5. Click **Create**.

1. In the GCP console, in the **Search (/) for resources, docs, products, and more** field, enter `Security profiles`.
2. Select **Security profiles**. The **Security profiles** page appears.
3. On the **Security profiles** page, on the **Security profile groups** tab, click **Create profile group**. The **Create security profile group** page appears.
4. On the **Create security profile group** page: See image.
  1. **Name**: Enter a name (e.g. `customer-nsi-security-profile-group`).
  2. **Description**: Enter a description.
  3. **Purpose**: Select **NSI In-Band**.
  4. **Custom intercept profile**: Select the profile you created in Step 2.
5. Click **Create**.

The security profile group is created. The firewall rules reference this group, not the profile directly.

1. In the GCP console, in the **Search (/) for resources, docs, products, and more** field, enter `Firewall policies`.
2. Select **Firewall policies**. The **Firewall policies** page appears.
3. On the **Firewall policies** page, click **Create firewall policy**. The **Create a network firewall policy** page appears.
4. On the **Create a network firewall policy** page:
  1. In the **Configure policy**section: See image.
    - **Policy name**: Enter a name (e.g., `customer-nsi-consumer-policy`).
    - **Description**: Enter a description.
    - **Policy type**: From the drop-down menu, select **VPC policy**.
    - **Deployment scope**: Select **Global**.
  2. Click **Continue**.
  3. In the **Add rules** section:
    1. Click **Create firewall rule**. The Create a firewall rule window appears.
    2. In the **Create a firewall rule** window: See image.
      - **Priority**: Enter `90`. Lower priority numbers are evaluated first.
      - **Description**: Enter a description for the rule. This is the allow rule for trusted sources to bypass inspection.
      - **Target type**: Select **Instances**.
      - **Target**: Select **Apply to all**, **Service accounts**, or **Secure tags** depending on your requirements.
      - **Direction of traffic**: Select **Ingress**.
      - **Action on match**: Select **Allow**.
      - **Logs**: Select **Enabled**.
      - **IP type**: From the drop-down menu, select **IPv4**.
      - **IP ranges**: Enter your trusted CIDRs.
      - Configure the **Source network context**, **Secure tags**, **FQDNs**, **Geolocations**, **Address group**, **Google Cloud Threat Intelligence**, **IP type**, **Protocols and ports**, and **Enforcement** fields per your requirements.
    3. Click **Create**. You are returned to the **Add rules** section.
    4. Click **Create firewall rule**. The Create a firewall rule window appears.
    5. In the **Create a firewall rule** window: See image.
      - **Priority**: Enter `100`.
      - **Description**: Enter a description for the rule. This is the ingress intercept rule.
      - **Target type**: Select **Instances**.
      - **Target**: Select **Apply to all**, **Service accounts**, or **Secure tags** depending on your requirements.
      - **Direction of traffic**: Select **Ingress**.
      - **Action on match**: Select **Apply security group profile**.
        - **Purpose**: Select **NSI In-Band**.
        - **Security group profile**: Select the security group profile you created in Step 3.
      - **Logs**: Select **Enabled**.
      - **IP type**: From the drop-down menu, select **IPv4**.
      - **IP ranges**: Enter the inbound CIDRs to inspect (e.g., `10.1.0.0/16`)
      - **Protocols and ports**: Select **All**.
      - Configure the **Source network context**, **Secure tags**, **FQDNs**, **Geolocations**, **Address group**, **Google Cloud Threat Intelligence**, **IP type**, and **Enforcement** fields per your requirements.
    6. Click **Create**. You are returned to the **Add rules** section.
    7. Click **Create firewall rule**. The **Create a firewall rule** window appears.
    8. In the **Create a firewall rule** window: See image.
      - **Priority**: Enter `101`.
      - **Description**: Enter a description for the rule. This is the egress intercept rule.
      - **Target type**: Select **Instances**.
      - **Target**: Select **Apply to all**, **Service accounts**, or **Secure tags** depending on your requirements.
      - **Direction of traffic**:
      - **Action on match**: Select **Apply security group profile**.
        - **Purpose**: Select **NSI In-Band**.
        - **Security group profile**: Select the security group profile you created in Step 3.
      - **Logs**: Select **Enabled**.
      - **IP type**: From the drop-down menu, select IPv4.
      - **IP ranges**: Enter the outbound CIDRs to inspect (e.g., `0.0.0.0/0` is all egress).
      - **Protocols and ports**: Select **All**.
      - Configure the **Source network context**, **Secure tags**, **FQDNs**, **Geolocations**, **Address group**, **Google Cloud Threat Intelligence**, **IP type**, and **Enforcement** fields per your requirements.
  4. Click **Continue**.
  5. In the **Add mirroring rules** section, click **Continue**.
  6. In the **Associate policy with networks (optional)** section, click **Associate**. The **Associate policy with VPC networks** window appears.
  7. In the **Associate policy with VPC networks** window, select your consumer VPC. Then, click **Associate**. See image.
  8. Click **Continue**.
  9. Click **Create**.

The firewall policy is created.

To verify that the registration was successful, ensure the following:

- On the **Endpoint groups** page:
  - In the **Status** column, the group is **Active**.
  - In the **Associations** column, the VPC association is listed.
- On the **Security profile groups** page, in the **Purpose** column, the group references your custom intercept profile.
- On the **Firewall policies** page, select the **Name** of your policy and confirm that the ingress/egress rules show the correct **Action on match** and match ranges.

Generate traffic that matches your ranges and confirm that it appears in the Zscaler inspection logs.

## Troubleshooting

| **Issue Encountered** | **Potential Cause** | **Troubleshooting Action** |
| --- | --- | --- |
| You cannot select the deployment group. | It is the wrong project, or it is in the Zscaler producer project. | In the **Enter manually** field, enter the Zscaler project ID and name. |
| "Permission denied" appears on the security profile. | The Network Security IAM is missing at the chosen scope. | Grant the Security Profile Admin IAM role (organization-level if you are using the organization scope). |
| The rule does not offer the profile group. | The profile group is not created yet, or has the wrong purpose. | Create the profile group with NSI in-band. |
| The traffic is not inspected. | The match ranges are wrong, or an allow rule shadows the intercept rule. | Check the rules ranges and confirm that the allow rule's priority is intentional. |

[Image: The Basic configuration tab on the Create an endpoint group page in the GCP console]

[Image: The New endpoint group association section displaying the Project and Network fields accessed from the Create an endpoint group page in the GCP console]

[Image: The Create security profile page accessed from the Security profiles page in the GCP console]

[Image: The Create security profile group page accessed from the Security profiles page in the GCP console]

[Image: The Configure policy section of the Create a network firewall policy page accessed from the Firewall policies page in the GCP console]

[Image: The Create a firewall rule section of the Create a network firewall policy page accessed from the Firewall policies page in the GCP console]

[Image: The Create a firewall rule section of the Create a network firewall policy page accessed from the Firewall policies page in the GCP console]

[Image: The Create a firewall rule section of the Create a network firewall policy page accessed from the Firewall policies page in the GCP console]

[Image: The Associate policy with VPC networks section of the Create a network firewall policy page accessed from the Firewall policies page in the GCP console]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/release-upgrade-summary-2021","lastmod":"2026-11-01T23:52Z","nid":"1420641"} -->
## Release Upgrade Summary (2021)

- Source: https://help.zscaler.com/zero-trust-cloud/release-upgrade-summary-2021
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Release Notes > Zero Trust Cloud Service Release Notes > Release Upgrade Summary (2021)
- Last modified: 2026-11-01T23:52Z
- Summary: Zscaler Cloud Connector Release Upgrade Summary for service updates deployed in 2021.

This article provides a summary of all new features and enhancements for Zscaler Cloud Connector.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/release-upgrade-summary-2022","lastmod":"2026-11-01T23:53Z","nid":"1420816"} -->
## Release Upgrade Summary (2022)

- Source: https://help.zscaler.com/zero-trust-cloud/release-upgrade-summary-2022
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Release Notes > Zero Trust Cloud Service Release Notes > Release Upgrade Summary (2022)
- Last modified: 2026-11-01T23:53Z
- Summary: Zscaler Cloud Connector Release Upgrade Summary for service updates deployed in 2022.

This article provides a summary of all new features and enhancements for Zscaler Cloud Connector.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/release-upgrade-summary-2023","lastmod":"2026-11-01T23:53Z","nid":"1440736"} -->
## Release Upgrade Summary (2023)

- Source: https://help.zscaler.com/zero-trust-cloud/release-upgrade-summary-2023
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Release Notes > Zero Trust Cloud Service Release Notes > Release Upgrade Summary (2023)
- Last modified: 2026-11-01T23:53Z
- Summary: Zscaler Cloud & Branch Connector Release Upgrade Summary for service updates deployed in 2023.

This article provides a summary of all new features and enhancements for Zscaler Cloud & Branch Connector.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/release-upgrade-summary-2024","lastmod":"2026-11-01T23:53Z","nid":"1473301"} -->
## Release Upgrade Summary (2024)

- Source: https://help.zscaler.com/zero-trust-cloud/release-upgrade-summary-2024
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Release Notes > Zero Trust Cloud Service Release Notes > Release Upgrade Summary (2024)
- Last modified: 2026-11-01T23:53Z
- Summary: Zscaler Cloud & Branch Connector Release Upgrade Summary for service updates deployed in 2024.

This article provides a summary of all new features and enhancements for Zscaler Cloud & Branch Connector. To see scheduled maintenance updates for your cloud, visit the [Trust Portal](https://trust.zscaler.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/release-upgrade-summary-2025","lastmod":"2026-11-01T23:53Z","nid":"1515711"} -->
## Release Upgrade Summary (2025)

- Source: https://help.zscaler.com/zero-trust-cloud/release-upgrade-summary-2025
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Release Notes > Zero Trust Cloud Service Release Notes > Release Upgrade Summary (2025)
- Last modified: 2026-11-01T23:53Z
- Summary: Zscaler Cloud & Branch Connector Release Upgrade Summary for service updates deployed in 2025.

This article provides a summary of all new features and enhancements for Zscaler Cloud & Branch Connector. To see scheduled maintenance updates for your cloud, visit the [Trust Portal](https://trust.zscaler.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/release-upgrade-summary-2026","lastmod":"2026-11-06T11:43Z","nid":"1534301"} -->
## Release Upgrade Summary (2026)

- Source: https://help.zscaler.com/zero-trust-cloud/release-upgrade-summary-2026
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Release Notes > Zero Trust Cloud Service Release Notes > Release Upgrade Summary (2026)
- Last modified: 2026-11-06T11:43Z
- Summary: Zero Trust Cloud (formerly Cloud & Branch Connector) Release Upgrade Summary for service updates deployed in 2026.

This article provides a summary of all new features and enhancements for Zero Trust Cloud (formerly Cloud & Branch Connector). To see scheduled maintenance updates for your cloud, visit the [Trust Portal](https://trust.zscaler.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/rotating-zscaler-service-account-passwords","lastmod":"2026-11-01T23:53Z","nid":"1532225"} -->
## Rotating Zscaler Service Account Passwords

- Source: https://help.zscaler.com/zero-trust-cloud/rotating-zscaler-service-account-passwords
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Deployment Management for Virtual Devices > Rotating Zscaler Service Account Passwords
- Last modified: 2026-11-01T23:53Z
- Summary: Information about rotating passwords on Cloud & Branch Connector.

Zscaler Cloud & Branch Connector virtual devices contain a Zscaler Service Account called `zsroot`. This account is a privileged user that enables secure administrative access and management of the virtual device. In order to safeguard your organization, Zscaler strongly recommends implementing routine password rotation as a best practice.

## Zscaler Cloud Connector

Zscaler Cloud Connectors are typically accessed with the SSH key that your cloud provider provides when you create the connector. Although your `zsroot` password is not required for logging in, Zscaler recommends periodically changing the password.

1. Log in to the VM as user `zsroot`.
2. Change the password:
  1. Enter the following command:

```
passwd zsroot
```

1. Follow the prompts to enter the current password and then a new password.

## Zscaler Virtual Branch Connector

1. Log in to the device through the console/management interface as user `zsroot`.
2. Change the password:
  1. Enter the following commands:

```
ssh zsroot@169.254.2.2
```

```
passwd zsroot
```

1. Follow the prompts to enter the current password and then a new password.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/session-data-types-filters","lastmod":"2026-11-01T23:52Z","nid":"1420701"} -->
## Session Data Types and Filters

- Source: https://help.zscaler.com/zero-trust-cloud/session-data-types-filters
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Analytics & Monitoring > Insights > Session Data Types and Filters
- Last modified: 2026-11-01T23:52Z
- Summary: Information on Session data types to define Session traffic information in a dashboard and charts in Session Insights.

There are two ways to view Session data types and filters to define Session traffic information. You can view them on a dashboard or when analyzing charts on an Insights page. To learn more about how to analyze your Insights traffic, see [Analyzing Traffic Using Insights](https://help.zscaler.com/cloud-branch-connector/analyzing-traffic-using-insights).

From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Data Explorer** > **Zero Trust Cloud**> **Session Insights** and select a data type to view from the menu above the chart and apply filters that you choose from the **Add Filter** menu on the left-side navigation.

## Data Types and Filters

You can view the Session Insights based on the following data types:

- Overall Traffic
- Traffic Direction
- TS Direction (traffic stream direction)
- Traffic Type
- Connector Instance
- Connector Group
- Azure Subscription ID
- Platform
- Forwarding Rule
- Forwarding Type
- Location

Using the following filters, you can further drill down into the Session Insights data that you want to view for any selected data type:

- **AWS Region**: Limits the data to traffic associated with the location of your AWS cloud account.
- **AWS Availability Zone**:Limits the data to traffic associated with the availability zone where Cloud Connector for AWS is deployed.
- **Azure Region**: Limits the data to traffic associated with the location of your Azure cloud account.
- **Azure Availability Zone**:Limits the data to traffic associated with the availability zone where Cloud Connector for Azure is deployed.
- **Azure Subscription ID**: Limits the data to traffic associated with the Azure Subscription ID.
- **CC VM**: Limits the data to traffic associated with the Cloud Connector virtual machine (VM) from which you want to view the session insights.
- **Cloud Connector Group**: Limits the data to traffic associated with the Cloud or Branch Connector group.
- **Cloud Connector Instance**: Limits the data to traffic associated with the specific Zscaler software instance.
- **Forwarding Rule**: Limits the data to traffic associated with the type of forwarding rule used.
- **Forwarding Type**: Limits the data to traffic associated with the type of forwarding method used.
- **GCP Availability Zone**: Limits the data to traffic associated with the availability zone where Cloud Connector for GCP is deployed.
- **GCP Region**: Limits the data to traffic associated with the location of your GCP cloud account.
- **Location**: Limits the data to a location's traffic.
- **Platform:**Limits the data to traffic associated with the hypervisor or the cloud platform from which you want to see the session insights.
- **TS Direction**: Limits the data to either transmitted (TX) or received (RX) traffic.
- **Traffic Direction**: Limits the data to traffic associated with
- **Traffic Type**: Limits the data to traffic associated with the type of traffic: ZIA, ZPA, or direct.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/session-insights-logs-columns","lastmod":"2026-11-01T23:52Z","nid":"1420691"} -->
## Session Insights Logs: Columns

- Source: https://help.zscaler.com/zero-trust-cloud/session-insights-logs-columns
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Analytics & Monitoring > Insights > Logs > Session Insights Logs: Columns
- Last modified: 2026-11-01T23:52Z
- Summary: Information on the different columns in the Session Insights Logs page in the Zscaler Admin Console.

You can customize your Session logs by using column fields. To learn more about logs, see [About Insights Logs](https://help.zscaler.com/cloud-branch-connector/about-insights-logs).

From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Data Explorer** > **Zero Trust Cloud** > **Session Insights**. Choose the **Logs** tab, located in the top left corner of the page. You can view the following columns:

- **Application Segment**: The transactions associated with a specific application segment.
- **AWS Availability Zone**: The availability zone where Cloud Connector for Amazon Web Services (AWS) is deployed.
- **AWS Region**: The location of your AWS cloud account.
- **Azure Availability Zone**: The availability zone where Cloud Connector for Azure is deployed.
- **Azure Region**: The location of your Azure cloud account.
- **Azure Subscription ID**: The Azure subscription ID associated with your Cloud Connector.
- **CC Instance**: The specific Zscaler software instance from which you want to view the session logs.
- **CC VM**: The Cloud Connector virtual machine.
- **Client Destination IP**: The client destination IP address. For aggregated sessions, this is the client destination IP address of the last session in the aggregate.
- **Client Destination Name**: The client destination FQDN for advanced firewall. For aggregated sessions, this is the client destination FQDN of the last session in the aggregate.
- **Client Destination Port**: The client-side destination IP address. For aggregated sessions, this is the client's destination port of the last session in the aggregate.
- **Client Received Bytes**: The number of bytes sent from the server to the client.
- **Client Sent Bytes**: The number of bytes sent from the client to the server.
- **Client Source IP**: The client source IP address. For aggregated sessions, this is the client source IP address of the last session in the aggregate.
- **Client Source Port**: The client-side source port. For aggregated sessions, this is the client source port of the last session in the aggregate.
- **Cloud Connector Group**: The name of the Cloud Connector group.
- **Cloud Connector Source IP**: The Cloud Connector source IP address.
- **Cloud Connector Source Port**: The source port of the Cloud Connector that is used to connect to the forwarding gateway.
- **Country**: The country that corresponds to the server IP address.
- **Endpoint / Interface**: The virtual private cloud (VPC) endpoint ID for AWS that connects to the Gateway Load Balancer.
- **Event Time**: The date and time of the transaction.
- **Forwarding Rule**: The forwarding policy list. If the client destination IP address entered is the service IP address of the Cloud or Branch Connector instance, the forwarding rule is logged as 0.
- **Forwarding Type**: The type of traffic forwarding mechanism for this session.
- **GCP Availability Zone**: The availability zone where Cloud Connector for GCP is deployed.
- **GCP Project ID**: The GCP project ID associated with your Cloud Connector.
- **GCP Region**: The location of your GCP cloud account.
- **Gateway Destination IP**: The gateway destination IP address used for the Cloud or Branch Connector.
- **Gateway Destination Port**: The destination port of the forwarding gateway.
- **Gateway Name**: The name of the gateway.
- **Location**: The name of the location from which the session was initiated.
- **Network Service**: The network service associated with the session or aggregated sessions.
- **Platform**: The hypervisor or the cloud platform associated with the session insights.
- **Server Destination IP**: The server destination IP address. For aggregated sessions, this is the server destination IP address of the last session in the aggregate.
- **Server Destination Port**: The server-side destination port. For aggregated sessions, this is the server destination port of the last session in the aggregate.
- **Server Received Bytes**: The number of bytes received by the server.
- **Server Sent Bytes**: The number of bytes sent by the server.
- **Server Source IP**: The server source IP address. For aggregated sessions, this is the server source IP address of the last session in the aggregate.
- **Server Source Port**: The server-side source port. For aggregated sessions, this is the server source port of the last session in the aggregate.
- **Session Duration**: The duration of the session in milliseconds. For aggregated sessions, this indicates the sum of individual session durations.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/session-insights-logs-filters","lastmod":"2026-11-01T23:52Z","nid":"1420696"} -->
## Session Insights Logs: Filters

- Source: https://help.zscaler.com/zero-trust-cloud/session-insights-logs-filters
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Analytics & Monitoring > Insights > Logs > Session Insights Logs: Filters
- Last modified: 2026-11-01T23:52Z
- Summary: Information on the different filters in the Session Insights Logs page in the Zscaler Cloud & Branch Connector Admin Portal.

Filters define the traffic information that you view in your Session Insight logs. To learn more about logs, see [About Insights Logs](https://help.zscaler.com/cloud-branch-connector/about-insights-logs).

From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Data Explorer** > **Zero Trust Cloud** > **Session Insights**. Choose the **Logs** tab, located in the top left corner of the page. You can select the following Session Insights Logs filters:

- **Application Segment**: Use this filter to view transactions associated with a specific application segment.
- **AWS Availability Zone**: Use this filter to limit the data to traffic associated with the availability zone where Cloud Connector for Amazon Web Services (AWS) is deployed.
- **AWS Region**: Use this filter to limit the data to traffic associated with the location of your AWS cloud account.
- **Azure Availability Zone**: Use this filter to limit the data to traffic associated with the availability zone where Cloud Connector for Azure is deployed.
- **Azure Region**: Use this filter to limit the data to traffic associated with the location of your Azure cloud account.
- **Azure Subscription ID**: Use this filter to limit the data associated with your Azure subscription ID.
- **CC VM**: Use this filter to limit the data to traffic associated with the Cloud Connector virtual machine (VM) from which you want to view the session insights.
- **Client Destination IP**: Use this filter to limit the data about traffic associated with a specific client destination IP address.
- **Client Destination Name**: For Advanced Firewall, use this filter to limit the data to traffic associated with a specific destination FQDN.
- **Client Destination Port**: Use this filter to limit the data to traffic associated with a specific client destination port.
- **Client Source IP**: Use this filter to limit the data about traffic associated with a specific client source IP address.
- **Client Source Port**: Use this filter to limit the data to traffic associated with a specific client source port.
- **Cloud Connector Group**: Use this filter to limit the data to traffic associated with the Cloud Connector group.
- **Cloud Connector Instance**: Use this filter to limit the data to traffic associated with the specific Cloud Connector instance.
- **Cloud Connector Name**: Use this filter to limit the data to traffic associated with a specific Cloud Connector name.
- **Cloud Connector Source IP**: Use this filter to limit the data to traffic associated with the Cloud Connector source IP.
- **Cloud Connector Source Port**: Use this filter to limit the data to traffic associated with the source port of the Cloud Connector that is used to connect to the forwarding gateway.
- **Country**: Use this filter to limit the data to traffic associated with a specific country.
- **Endpoint / Interface**: Use this filter to limit the data to traffic associated with an endpoint.
- **Forwarding Rule**: Use this filter to limit the data to traffic associated with the type of forwarding rule used.
- **Forwarding Type**: Use this filter to limit the data to traffic associated with the type of forwarding method used.
- **Gateway Destination IP**: Use this filter to limit the data to traffic associated with the gateway destination IP used.
- **Gateway Destination Port**: Use this filter to limit the data to traffic associated with the gateway destination port used.
- **Gateway Name**: Use this filter to limit the data to traffic associated with the gateway name used.
- **GCP Availability Zone**: Use this filter to limit the data to traffic associated with the availability zone where Cloud Connector for Google Cloud Platform (GCP) is deployed.
- **GCP Region**: Use this filter to limit the data to traffic associated with the location of your GCP cloud account.
- **Location**: Use this filter to limit the data to a location's traffic.
- **Network Service**: The network service associated with the session or aggregated sessions.
- **Platform**: Use this filter to limit the data to traffic associated with the hypervisor or the cloud platform from which you want to see the session insights.
- **Server Destination IP**: Use this filter to limit the data to traffic associated with a specific server destination IP address.
- **Server Destination Port**: Use this filter to limit the data to traffic associated with a specific server destination IP port.
- **Server Source IP**: Use this filter to limit the data to traffic associated with a specific server source IP address.
- **Server Source Port**: Use this filter to limit the data to traffic associated with a specific server source port.
- **Traffic Type**: The type of traffic mechanism for this session.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/siem-integration-nss","lastmod":"2026-11-01T23:53Z","nid":"1455426"} -->
## SIEM Integration for NSS

- Source: https://help.zscaler.com/zero-trust-cloud/siem-integration-nss
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Nanolog Streaming Service > SIEM Integration for NSS
- Last modified: 2026-11-01T23:53Z
- Summary: Understanding SIEM Integration for NSS.

Cloud NSS allows you to instantly stream logs from the Zscaler Admin Console directly into a compatible cloud-based security information and event management (SIEM) system. If you subscribe to Cloud NSS, you can enable direct cloud-to-cloud log streaming.

There are two reliable log-delivery mechanisms in NSS:

- NSS to SIEM: NSS buffers the logs in the VM memory to increase its resiliency to transient network issues between SIEM and NSS. If the connection drops, NSS replays logs from the buffer, according to the Duplicate Logs setting. For a list of SIEMs that integrate with the VM-based NSS, see[Integrating VM-Based NSS with SIEMs](https://help.zscaler.com/zia/integrating-vm-based-nss-siems).
- Cloud NSS to SIEM: If the connectivity between the cloud and NSS is interrupted, NSS misses logs that arrived at the Nanolog cluster during the interruption, and they are not delivered to the SIEM. After the connection is restored, the NSS one-hour recovery allows the Nanolog to replay logs up to one hour prior. For a list of SIEMs that integrate with Cloud NSS, see [Integrating Cloud NSS with Cloud-Based SIEMs](https://help.zscaler.com/zia/integrating-cloud-nss-cloud-based-siems).

For Cloud & Branch Connector, consider only the NSS for Firewall configurations in the integration guides and ignore the NSS for Web configurations because they are not applicable.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/step-step-configuration-guide-zscaler-branch-connector","lastmod":"2026-11-01T23:53Z","nid":"1420916"} -->
## Step-by-Step Configuration Guide for Zscaler Branch Connector

- Source: https://help.zscaler.com/zero-trust-cloud/step-step-configuration-guide-zscaler-branch-connector
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Zscaler Branch Connector Management > Step-by-Step Configuration Guide for Zscaler Branch Connector
- Last modified: 2026-11-01T23:53Z
- Summary: This guide takes you step-by-step through the configuration tasks you must complete for Zscaler Branch Connector.

This guide takes you through the configuration steps you need to complete before using Zscaler Branch Connector for your organization.

Before you begin configuring Branch Connector, Zscaler recommends reading the following articles:

- [What is Zscaler Branch Connector?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-branch-connector)
- [Zscaler Admin Console Access & Navigation](https://help.zscaler.com/unified/getting-started-experience-center/zscaler-admin-console-access-navigation)

## Configuring Branch Connector

To configure Branch Connector, complete the following steps:

- Step 1: Update Administrator and Role Management Information
- Step 2: Configure your Locations
- Step 3: Configure your Provisioning Template
- Step 4: Configure your Firewall Filtering
- Step 5: Configure your Gateways
- Step 6: Configure your Forwarding Methods
- Step 7: Configure your Analytics

To update and configure administrators and roles as applicable, see:

- [Accessing Administrator Management](https://help.zscaler.com/cloud-branch-connector/accessing-administrator-management)
- [About Administrators](https://help.zscaler.com/cloud-branch-connector/about-administrators)
- [About Role Management](https://help.zscaler.com/cloud-branch-connector/about-role-management)
- [Adding Admin Roles](https://help.zscaler.com/cloud-branch-connector/adding-admin-roles)

Locations identify the various networks from which your organization sends its internet traffic. To learn more, see:

- [About Locations](https://help.zscaler.com/cloud-branch-connector/about-locations)
- [About Location Templates](https://help.zscaler.com/cloud-branch-connector/about-location-templates)
- [Configuring a Location Template](https://help.zscaler.com/cloud-branch-connector/configuring-location-template)

Provisioning templates create provisioning URLs to deploy your Branch Connector as a virtual machine (VM). To learn more, see:

- [About Branch Provisioning Templates](https://help.zscaler.com/cloud-branch-connector/about-branch-provisioning-template)
- [Configuring a Branch Provisioning Template](https://help.zscaler.com/cloud-branch-connector/configuring-branch-provisioning-template)

Firewall filtering policies are comprised of source IP groups, destination IP groups, IP pools, and network services. To learn more, see:

- [About Source IP Groups](https://help.zscaler.com/cloud-branch-connector/about-source-ip-groups)
- [Configuring Source IP Groups](https://help.zscaler.com/cloud-branch-connector/configuring-source-ip-groups)
- [About Destination IP Groups](https://help.zscaler.com/cloud-branch-connector/about-destination-ip-groups)
- [Configuring Destination IP Groups](https://help.zscaler.com/cloud-branch-connector/configuring-destination-ip-groups)
- [About IP Pool](https://help.zscaler.com/cloud-branch-connector/about-ip-pool)
- [Configuring IP Pool](https://help.zscaler.com/cloud-branch-connector/configuring-ip-pool)
- [About Network Services](https://help.zscaler.com/cloud-branch-connector/about-network-services)
- [Configuring Network Services](https://help.zscaler.com/cloud-branch-connector/configuring-network-services)
- [About Network Service Groups](https://help.zscaler.com/cloud-branch-connector/about-network-service-groups)
- [Configuring Network Service Groups](https://help.zscaler.com/cloud-branch-connector/configuring-network-service-groups)

Gateway forwarding methods allow you to control and forward traffic. To learn more, see:

- [About Zscaler Internet Access Gateways](https://help.zscaler.com/cloud-branch-connector/about-zia-gateways)
- [Configuring a ZIA Gateway](https://help.zscaler.com/cloud-branch-connector/configuring-zia-gateway)
- [About Log and Control Gateways](https://help.zscaler.com/cloud-branch-connector/about-log-and-control-gateways)
- [Configuring a Log and Control Gateway](https://help.zscaler.com/cloud-branch-connector/configuring-log-and-control-gateway)

Forwarding allows you to set up traffic forwarding, log and control forwarding, and DNS control rules. To learn more, see:

- [About Traffic Forwarding](https://help.zscaler.com/cloud-branch-connector/about-traffic-forwarding)
- [Configuring Traffic Forwarding Rules](https://help.zscaler.com/cloud-branch-connector/configuring-traffic-forwarding-rule)
- [About Log and Control Forwarding](https://help.zscaler.com/cloud-branch-connector/about-log-and-control-forwarding)
- [Configuring Log and Control Forwarding Rule](https://help.zscaler.com/cloud-branch-connector/configuring-log-and-control-forwarding-rule)
- [About DNS Policies](https://help.zscaler.com/cloud-branch-connector/about-dns-policies)
- [Configuring the DNS Filtering Rule](https://help.zscaler.com/cloud-branch-connector/configuring-dns-filtering-rule)

The Zscaler service provides real-time log consolidation across the globe, so you can view every transaction performed by your users regardless of where they are in the world. It also presents the deployment status, activity status, and geographical view of your deployed Branch Connector. To learn more, see:

- [Accessing Cloud & Branch Connector Monitoring](https://help.zscaler.com/cloud-branch-connector/accessing-cloud-branch-connector-monitoring)
- [About Insights](https://help.zscaler.com/cloud-branch-connector/about-insights)
- [About Insights Logs](https://help.zscaler.com/cloud-branch-connector/about-insights-logs)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/step-step-configuration-guide-zscaler-client-connector-vdi","lastmod":"2026-11-01T23:53Z","nid":"1472246"} -->
## Step-by-Step Configuration Guide for Zscaler Client Connector for VDI

- Source: https://help.zscaler.com/zero-trust-cloud/step-step-configuration-guide-zscaler-client-connector-vdi
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Zscaler Client Connector for VDI Management > Step-by-Step Configuration Guide for Zscaler Client Connector for VDI
- Last modified: 2026-11-01T23:53Z
- Summary: Information on the configuration tasks an organization must complete to begin using Zscaler Client Connector for VDI.

This guide takes you step-by-step through the configuration tasks you must complete to begin using Zscaler Client Connector for Virtual Desktop Infrastructure (VDI) for your organization.

To enable this feature, contact Zscaler Support.

Before you begin configuring Zscaler Client Connector for VDI, Zscaler recommends reading the following articles:

- [What Is Zscaler Client Connector for VDI?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-vdi-agent)
- [Understanding Zscaler Cloud Names](https://help.zscaler.com/unified/understanding-zscaler-cloud-names)
- [What Is Zscaler Cloud Connector?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-cloud-connector)
- [What Is Zscaler Branch Connector?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-branch-connector)
- [Understanding the Zscaler Cloud Architecture for Internet & SaaS](https://help.zscaler.com/zia/understanding-zscaler-cloud-architecture)
- [Understanding the Private Access Architecture](https://help.zscaler.com/zpa/understanding-private-access-architecture)

## Configuring Zscaler Client Connector for VDI

To configure Zscaler Client Connector for VDI, complete the following steps:

- Step 1: Review the System Requirements
- Step 2: Review the Zscaler Firewall Requirements
- Step 3: Deploy Zscaler Cloud Connector or Zscaler Branch Connector
- Step 4: Configure the VDI Templates
- Step 5: Configure the VDI Forwarding Profile
- Step 6: Configure Dynamic VDI Groups
- Step 7: Customize Zscaler Client Connector for VDI with Installer Options
- Step 8: Configure DNS for Private Access
- Step 9: (Optional) Review Best Practices for FSLogix, Citrix, and AVD

Zscaler Client Connector for VDI currently supports only modern Windows operating systems. You must be running Windows 10 or Windows Server 2016 or later. Additionally, only Azure Virtual Desktop and Citrix, on-premises and cloud-hosted, have been tested. Other vendors might work, but have not been tested.

Before you begin configuring Zscaler Client Connector for VDI, you must deploy Zscaler Cloud Connector to a cloud provider or Zscaler Branch Connector to a hypervisor host.

For Cloud Connector deployment, see the following articles:

- [Deployment Templates for Zscaler Cloud Connector](https://help.zscaler.com/cloud-branch-connector/deployment-templates-zscaler-cloud-connector)
- [Deploying Zscaler Cloud Connector with Amazon Web Services](https://help.zscaler.com/cloud-branch-connector/deploying-zscaler-cloud-connector-amazon-web-services)
- [Deploying Zscaler Cloud Connector with Microsoft Azure](https://help.zscaler.com/cloud-branch-connector/deploying-zscaler-cloud-connector-microsoft-azure)
- [Deploying Zscaler Cloud Connector on the Google Cloud Platform](https://help.zscaler.com/cloud-branch-connector/deploying-zscaler-cloud-connector-google-cloud-platform)

Amazon Web Services (AWS) and Google Cloud Platform (GCP) are not supported by Zscaler Client Connector for VDI.

For Branch Connector deployment, see the following articles:

- [Downloading Branch Connector Images](https://help.zscaler.com/cloud-branch-connector/downloading-branch-connector-images)
- [Deployment Templates for Zscaler Branch Connector & App Connector](https://help.zscaler.com/cloud-branch-connector/deployment-templates-zscaler-branch-connector-app-connector)
- [Deploying Branch Connector on VMware Platforms](https://help.zscaler.com/cloud-branch-connector/deploying-branch-connector-vmware-platforms)
- [Deploying Branch Connector with Linux KVM](https://help.zscaler.com/cloud-branch-connector/deploying-branch-connector-linux-kvm)

If you leverage load balancers to distribute traffic to Cloud Connector or Branch Connector, Zscaler recommends using source IP address affinity (2-tuple) hashing.

The Cloud Connector and Branch Connector instances require only outbound connections to the Zscaler cloud. They do not require any inbound connections to your network from the Zscaler cloud. For user authentication, Zscaler Client Connector for VDI communicates with the Zscaler cloud using TCP/443. It also requires UDP/7443 to be open between the application software and the Cloud Connector or Branch Connector. This is how Zscaler Client Connector for VDI tunnels traffic to the Cloud Connector or Branch Connector. UDP/7443 to the Cloud Connector or Branch Connector and TCP/443 to the Zscaler cloud are required. Additionally, Zscaler Client Connector for VDI connects on TCP 9090 to the Cloud Connector service endpoint for policy updates. To view the outbound access requirements for your specific account, go to the following URL: https://config.zscaler.com/<Zscaler Cloud Name>/cloud-branch-connector.

You can find the <Zscaler Cloud Name> in the URL you use to log in to the Zscaler Admin Console. For example, if you log in to connector.zscaler.net, then go to https://config.zscaler.com/zscaler.net/cloud-branch-connector. To learn more, see [Understanding Zscaler Cloud Names](https://help.zscaler.com/unified/understanding-zscaler-cloud-names).

The VDI templates allow you to create a set of required configurations, including the provisioning URL, authentication token, and end user authentication method. These configurations are then applied to Zscaler Client Connector for VDI.

To configure VDI templates with Internet & SaaS, assign an identity provider (IdP) and System User to the template. Zscaler Client Connector for VDI uses the IdP configured in the Internet & SaaS tenant.

To configure VDI templates with Private Access, ensure that SCIM and [SCIM Attributes for Policy](https://help.zscaler.com/zpa/enabling-scim-identity-management) are enabled for Private Access user attribution to function. Zscaler Client Connector for VDI uses the IdP configured in the Internet & SaaS tenant for both Internet & SaaS and Private Access. For Private Access, the Internet & SaaS IdP provides the authentication and passes a token to Private Access, authenticating the user to the Private Access platform. For Private Access and wildcard Fully Qualified Domain Name (FQDN) forwarding to work, send all DNS requests through Cloud Connectors.

To learn more, see the following articles:

- [About VDI Templates](https://help.zscaler.com/cloud-branch-connector/about-vdi-agent-templates)
- [Configuring VDI Templates](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-templates)

The VDI forwarding profiles allow you to create forwarding rules to include or exclude destinations from being encapsulated in the tunnels that Zscaler Client Connector for VDI creates.

To configure VDI forwarding profiles with Internet & SaaS, Zscaler recommends configuring a single 0.0.0.0/0 inclusion rule. The application automatically excludes RFC 1918 traffic (i.e., 10.0.0.0/8, 172.16.0.0/12, 192.168.0.0/24, and 224.0.0.0/4). You can adjust the exclusion list to add additional private ranges. For example, Microsoft Azure users might choose to include the Automatic Private IP Addressing (APIPA) address range of 169.254.0.0/16 and Microsoft's dedicated 168.63.129.16/32 IP. If you are implementing Conditional Access policies in Azure, consider the URL-based bypasses that Microsoft recommends for Microsoft 365 and Azure Virtual Desktop to function correctly. Execute the bypasses in the Zscaler Admin Console. To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/microsoft-365/enterprise/urls-and-ip-address-ranges?view=o365-worldwide) and [Azure documentation](https://learn.microsoft.com/en-us/azure/virtual-desktop/required-fqdn-endpoint?tabs=azure).

To configure VDI forwarding profiles with Private Access, you must make additional changes to the synthetic IP pool used for Private Access. This synthetic IP pool is configured in the Zscaler Admin Console. Zscaler recommends adding your synthetic IP ranges, set to 10.254.0.0/19 by default, to the inclusion list of your Zscaler VDI Agent forwarding profile to ensure that the traffic is properly tunneled to the Cloud Connector or Branch Connector. If your VDI forwarding profile excludes Private Access synthetic IP pools, then some Private Access functionalities might be unavailable.

To learn more, see the following articles:

- [About VDI Forwarding Profiles](https://help.zscaler.com/cloud-branch-connector/about-vdi-agent-forwarding-profiles)
- [Configuring VDI Forwarding Profiles](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-agent-forwarding-profiles)

Zscaler Client Connector for VDI does not support Private Access features such as server-to-client connectivity for remote access, client forwarding policy for client types as Zscaler Client Connector for VDI, and double encryption. However, if the [client forwarding policy](https://help.zscaler.com/zpa/about-client-forwarding-policy) is required, the client type of the Cloud Connector or Branch Connector is supported, and you can use it in the same way.

VDI groups allow administrators to dynamically apply Maximum Transmission Unit (MTU) and forwarding profile values to VDI applications as they register VDI devices and groups.

To learn more, see the following articles:

- [About VDI Devices](https://help.zscaler.com/cloud-branch-connector/about-vdi-devices)
- [About VDI Groups](https://help.zscaler.com/cloud-branch-connector/about-vdi-groups)
- [Configuring VDI Groups](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-groups)

You can configure the Zscaler Client Connector for VDI installer file with installation options that allow you to remove steps from the user-enrollment process and enable or disable specific features of Zscaler Client Connector for VDI. To learn more, see[Customizing Zscaler Client Connector for VDI with Install Options for MSI](https://help.zscaler.com/cloud-branch-connector/customizing-zscaler-vdi-agent-install-options-msi).

To enable Zscaler Client Connector for VDI to connect to Private Access access, route DNS queries through Cloud Connector. In Azure, Zscaler Client Connector for VDI uses the Azure DNS server with the IP address `168.63.129.16` by default. If this IP address is included in the [VDI forwarding profile](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-forwarding-profiles) Include list, DNS queries are handled by Cloud Connector. If it is in the [VDI forwarding profile](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-forwarding-profiles) Exclude list, ensure that the DNS request is sent toward Cloud Connector.

- If `168.63.129.16` is the primary DNS server for the Azure virtual network (VNet), you can use the private Azure outbound DNS resolver to route specific domains, such as Private Access applications, to the Cloud Connector for resolution.
- If the Azure VNet uses a custom DNS server, you can add the DNS server to the [VDI forwarding profile](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-forwarding-profiles) Include list, and DNS requests are forwarded to Cloud Connector by default. If you add a custom DNS server to the [VDI forwarding profile](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-forwarding-profiles) Exclude list, ensure that a route toward the Cloud Connector is configured on the Azure Virtual Desktop (AVD) subnet. If the AVD subnet is not configured to send the default route to the Cloud Connector, ensure that DNS traffic is directed toward the Cloud Connector.
- A default rule is configured to send all traffic to Internet & SaaS. If you do not want the DNS traffic to be sent to Internet & SaaS, add a direct traffic forwarding rule to the Cloud Connector cluster so that this traffic bypasses Internet & SaaS.

For Private Access use cases, include the Private Access synthetic IP pool in the [VDI forwarding profile](https://help.zscaler.com/cloud-branch-connector/about-vdi-forwarding-profiles).

Zscaler recommends the following best practices when configuring FSLogix, Citrix, or AVD for Zscaler Client Connector for VDI:

- FSLogix
- Citrix
- AVD

The following best practices apply to FSLogix:

- Storage Account Bypass: You can host the Azure storage account on a public IP address or on an Azure Private Link. Zscaler recommends using an Azure Private Link. Bypass the Azure storage account IP address in the VDI forwarding profile so the traffic can go directly from the Windows session host to the FSLogix storage account.
- Azure Storage Account Firewall Policies: Azure storage accounts are protected by firewall policies, and everything from outside the Azure VNet is dropped by default. Depending on the designated traffic flow, you can decide whether the firewall policy allows the Cloud Connector service interface IP address, the NAT gateway public IP address, or the Zscaler Public Service Edge IP address.
- Azure resources (Microsoft Learn): Azure has a unique, global IP address, `168.63.129.16`. It is the default DNS server for all Azure instances and the gateway to other Azure resources such as the Azure Windows VM Agent. Traffic to the IP address must be bypassed latest on the Cloud Connector traffic forwarding profile for the following ports: Microsoft owns the IP address `168.63.129.16`. If Microsoft changes this IP address, update it to match.
  - UDP/53
  - TCP/53
  - TCP/80
  - TCP/443
  - TCP/32526
- DNS Resolution: To prepare for enrollment, bypass the DNS server IP address, `168.63.129.16`, in Cloud Connector traffic forwarding rules, and not in Zscaler Client Connector for VDI itself.
  - When the FSLogix storage account is a default account, the FQDN of the account is always resolved as a public IP address.
  - When the FSLogix storage account is using a private link connection, DNS requests for the FQDN from Azure networks are resolved into the private IP address assigned to the private link. External requests, such as requests from the Zscaler Public Service Edge, are resolved with the storage accounts' public IP address.
- Azure Instance Metadata Service: Azure instances communicate with the hypervisor host through `169.254.0.0/19`, which should be bypassed in Zscaler Client Connector for VDI.
- Cloud Connector Network Security Group: The default network security group deployed within the Azure Marketplace only allows TCP/443, UDP/443, and TCP/80. For storage accounts, enable the account IP address with TCP/443, UDP/443, TCP/80, UDP/53, TCP/53, TCP/445, and UDP/445.

The following best practices apply to Citrix:

- To prevent bootup during MCS creation, ensure that the Zscaler service and ZCCVDIService are not running.
- If you are using XenApp, Zscaler recommends always using an embedded browser and SSO.
- Bypass Citrix Profile Management and Citrix Rendezvous protocols for them to function correctly. To learn more about bypasses, see [Configuring VDI Forwarding Profiles](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-forwarding-profiles#CitrixIPFQDN).
- When installing Zscaler on a Citrix image VM, ensure that you are using the onboarding parameter 0. To learn more about command line arguments when creating a MCS golden image, see [Customizing Zscaler Client Connector for VDI with Install Options for MSI](https://help.zscaler.com/cloud-branch-connector/customizing-zscaler-client-connector-vdi-install-options-msi).

To learn more, refer to the [Citrix documentation](https://docs.citrix.com/en-us/citrix-cloud/overview/requirements/internet-connectivity-requirements.html).

The following best practices apply to AVD:

- When using Microsoft Intune, ensure that all the command parameters are updated and formatted correctly. If you are using AVDs, search for `WindowsVirtualDesktop` in the JSON file for your region to receive the IP addresses to bypass.
- If you are configuring network security groups (NSGs), Zscaler recommends allowing traffic from Zscaler Client Connector for VDI. NSGs should not have any other proxies, third-party tools, virtual private networks (VPNs), or software that can hinder the system network.
- To learn more about Microsoft DNS, refer to [Step 8: Configure DNS for Private Access](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi).
- To learn about best practices for Zscaler Client Connector for VDI, see [Downloading Zscaler Client Connector for VDI](https://help.zscaler.com/cloud-branch-connector/downloading-zscaler-client-connector-vdi) and [Customizing Zscaler Client Connector for VDI with Install Options for MSI](https://help.zscaler.com/cloud-branch-connector/customizing-zscaler-client-connector-vdi-install-options-msi).

To learn more about bypasses, see [Configuring VDI Forwarding Profiles](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-forwarding-profiles#AzureIPFQDN).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/step-step-configuration-guide-zscaler-cloud-connector","lastmod":"2026-11-01T23:52Z","nid":"1420756"} -->
## Step-by-Step Configuration Guide for Zscaler Cloud Connector

- Source: https://help.zscaler.com/zero-trust-cloud/step-step-configuration-guide-zscaler-cloud-connector
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Step-by-Step Configuration Guide for Zscaler Cloud Connector
- Last modified: 2026-11-01T23:52Z
- Summary: This guide takes you step-by-step through the configuration tasks you must complete for Zscaler Cloud Connector.

This guide takes you through the configuration steps you need to complete before using Zscaler Cloud Connector for your organization.

Before you begin configuring Cloud Connector, Zscaler recommends reading the following articles:

- [What Is Zscaler Cloud Connector?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-cloud-connector)
- [Zscaler Admin Console Access & Navigation](https://help.zscaler.com/unified/getting-started-experience-center/zscaler-admin-console-access-navigation)

## Configuring Cloud Connector

To configure Cloud Connector, complete the following steps:

- Step 1: Update Administrator and Role Management Information
- Step 2: Configure Your Provisioning Template
- Step 3: Configure Your Locations
- Step 4: Deploy Your Cloud Connector Virtual Machine (VM)
- Step 5: Configure Your Firewall Filtering
- Step 6: Configure Your Gateways
- Step 7: Configure Your Forwarding Methods
- Step 8: Configure Your Analytics

To update and configure administrators and roles as applicable, see:

- [About Role Management](https://help.zscaler.com/cloud-branch-connector/about-role-management)
- [Adding Admin Roles](https://help.zscaler.com/cloud-branch-connector/adding-admin-roles)

Locations identify the various networks from which your organization sends its internet traffic. To learn more, see:

- [About Locations](https://help.zscaler.com/cloud-branch-connector/about-locations)
- [About Location Templates](https://help.zscaler.com/cloud-branch-connector/about-location-templates)
- [Configuring a Location Template](https://help.zscaler.com/cloud-branch-connector/configuring-location-template)

Provisioning templates create provisioning URLs to deploy your Cloud Connector as a virtual machine (VM). To learn more, see:

- [About Cloud Provisioning Templates](https://help.zscaler.com/cloud-branch-connector/about-cloud-provisioning-templates)
- [Configuring a Cloud Provisioning Template](https://help.zscaler.com/cloud-branch-connector/configuring-cloud-provisioning-template)

To deploy your Cloud Connector VM, see:

- [Deployment Templates for Zscaler Cloud Connector](https://help.zscaler.com/cloud-branch-connector/deployment-templates-zscaler-cloud-connector)
- [Deploying Zscaler Cloud Connector with Amazon Web Services](https://help.zscaler.com/cloud-branch-connector/deploying-zscaler-cloud-connector-amazon-web-services)
- [Deploying Zscaler Cloud Connector with Microsoft Azure](https://help.zscaler.com/cloud-branch-connector/deploying-zscaler-cloud-connector-microsoft-azure)
- [Deploying Zscaler Cloud Connector on the Google Cloud Platform](https://help.zscaler.com/cloud-branch-connector/deploying-zscaler-cloud-connector-google-cloud-platform)

Firewall filtering policies are comprised of source IP groups, destination IP groups, IP pools, and network services. To learn more, see:

- [About Source IP Groups](https://help.zscaler.com/cloud-branch-connector/about-source-ip-groups)
- [Configuring Source IP Groups](https://help.zscaler.com/cloud-branch-connector/configuring-source-ip-groups)
- [About Destination IP Groups](https://help.zscaler.com/cloud-branch-connector/about-destination-ip-groups)
- [Configuring Destination IP Groups](https://help.zscaler.com/cloud-branch-connector/configuring-destination-ip-groups)
- [About IP Pool](https://help.zscaler.com/cloud-branch-connector/about-ip-pool)
- [Configuring IP Pool](https://help.zscaler.com/cloud-branch-connector/configuring-ip-pool)
- [About Network Services](https://help.zscaler.com/cloud-branch-connector/about-network-services)
- [Configuring Network Services](https://help.zscaler.com/cloud-branch-connector/configuring-network-services)
- [About Network Service Groups](https://help.zscaler.com/cloud-branch-connector/about-network-service-groups)
- [Configuring Network Service Groups](https://help.zscaler.com/cloud-branch-connector/configuring-network-service-groups)

Gateway forwarding methods allow you to control and forward traffic. To learn more, see:

- [About Zscaler Internet Access Gateways](https://help.zscaler.com/cloud-branch-connector/about-zia-gateways)
- [Configuring a ZIA Gateway](https://help.zscaler.com/cloud-branch-connector/configuring-zia-gateway)
- [About Log and Control Gateways](https://help.zscaler.com/cloud-branch-connector/about-log-and-control-gateways)
- [Configuring a Log and Control Gateway](https://help.zscaler.com/cloud-branch-connector/configuring-log-and-control-gateway)

Forwarding allows you to set up traffic forwarding, log and control forwarding, and DNS control rules. To learn more, see:

- [About Traffic Forwarding](https://help.zscaler.com/cloud-branch-connector/about-traffic-forwarding)
- [Configuring Traffic Forwarding Rules](https://help.zscaler.com/cloud-branch-connector/configuring-traffic-forwarding-rule)
- [About Log and Control Forwarding](https://help.zscaler.com/cloud-branch-connector/about-log-and-control-forwarding)
- [Configuring Log and Control Forwarding Rule](https://help.zscaler.com/cloud-branch-connector/configuring-log-and-control-forwarding-rule)
- [About DNS Policies](https://help.zscaler.com/cloud-branch-connector/about-dns-policies)
- [Configuring the DNS Filtering Rule](https://help.zscaler.com/cloud-branch-connector/configuring-dns-filtering-rule)

The Zscaler service provides real-time log consolidation across the globe, so you can view every transaction performed by your users regardless of where they are in the world. It also presents the deployment status, activity status, and geographical view of your deployed Cloud Connector. To learn more, see:

- [Accessing Cloud & Branch Connector Monitoring](https://help.zscaler.com/cloud-branch-connector/accessing-cloud-branch-connector-monitoring)
- [About Insights](https://help.zscaler.com/cloud-branch-connector/about-insights)
- [About Insights Logs](https://help.zscaler.com/cloud-branch-connector/about-insights-logs)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/storing-your-secret-credentials-hashicorp-vault","lastmod":"2026-11-01T23:53Z","nid":"1519691"} -->
## Storing Your Secret Credentials in HashiCorp Vault for Google Cloud Platform-Based Cloud Connectors

- Source: https://help.zscaler.com/zero-trust-cloud/storing-your-secret-credentials-hashicorp-vault
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Deployment Management for Virtual Devices > Cloud Connector Deployment Management > Cloud Connector Deployment Management for GCP > Storing Your Secret Credentials in HashiCorp Vault for Google Cloud Platform-Based Cloud Connectors
- Last modified: 2026-11-01T23:53Z
- Summary: Information about setting up HashiCorp Vault and Google Cloud to store your secret credentials.

HashiCorp Vault is a cloud-agnostic method for storing and maintaining secret credentials. This article describes how to use HashiCorp IAM authentication to integrate a HashiCorp Vault with a new Cloud Connector based on the Google Cloud Platform (GCP).

You cannot migrate an existing Cloud Connector from GCP Secret Manager to HashiCorp Vault. You must deploy a new Cloud Connector using the Terrarform wizard.

## Prerequisites

Make sure the following prerequisites are met:

- A HashiCorp Vault and Vault cluster have already been created.
- The Vault CLI is downloaded to your local machine.

For information, refer to the [HashiCorp Vault documentation](https://developer.hashicorp.com/vault).

## Integration

Perform the following procedures to integrate a new GCP-based Cloud Connector with a dedicated public HashiCorp Vault.

This article describes how to configure a dedicated public HashiCorp Vault. The procedures may differ for self-managed Vault deployments. For example, for self-managed deployments, instead of logging in to the HashiCorp Cloud Portal, log in to your self-managed Vault FQDN or IP address.

- 1. Set up the HashiCorp Vault and secrets.
- 2. Enable Vault to authenticate with GCP.
- 3. Configure HashiCorp to enable GCP to authenticate with Vault.
- 4. Gather information for the Terraform deployment wizard.

To set up the Vault and secrets, perform the following procedure in the [HashiCorp Cloud Portal](https://portal.cloud.hashicorp.com):

1. Log in to the [HashiCorp Cloud Portal](https://portal.cloud.hashicorp.com).
2. Navigate to your Vault (**Vault Dedicated** > <Vault Cluster Name> > **Launch web ui**).
3. At the bottom of the left-side navigation, set **admin**as the current namespace. See image.
4. From the left-side navigation, select **Secrets Engines**.
5. On the **Secrets Engines** page, click **Enable new engine**. The **Enable a Secrets Engine** page appears.
6. In the **Generic**section, click **KV**. See image.
7. Another **Enable a Secrets Engine** page appears. In the **Path**field, enter the path (for example, `secretKV`).
8. Click **Enable engine**. The secrets enginepage appears (in this example, the **secretKV**page). See image.
9. Click **Create secret**. The **Create Secret** page appears. In the **Path for this secret** field, enter your path (for example, `secretKV`).
10. In the **Secret data** section fields: See image.
  1. Enter `username`. For the corresponding value, enter your dedicated Cloud Connector deployment username. Click **Add**.
  2. Enter `password`. For the corresponding value, enter your dedicated Cloud Connector deployment password. Click **Add**.
  3. Enter `api_key`. For the corresponding value, enter the value you copied from the [API Key Management](https://help.zscaler.com/cloud-branch-connector/about-api-key-management) page in the Zscaler Admin Console.
11. Click **Save**.

To enable Vault to authenticate with GCP, perform the following procedures in the [GCP Console](https://console.cloud.google.com/):

- a. Create a service account for Vault to authenticate with GCP.
- b. Create a service account key credential file.

Instead of using the predefined role as described in this procedure, you can create a custom role and assign the following 5 required permissions: **compute.instanceGroups.list**,**compute.instances.get**,**iam.serviceAccountKeys.get**,**iam.serviceAccounts.get**, and **iamService.signJwt**. (The predefined role has additional roles assigned to it.)

1. Log in to the [GCP Console](https://console.cloud.google.com/).
2. From the left-side navigation, select **IAM & Admin** > **Service Accounts**.
3. Click **Create Service Account**.
4. In the **Service account details** section:
  - **Service account name**: Enter a unique display name for the service account. GCP generates a service account ID based on this name.
  - **Service account ID**: If necessary, change the ID. You cannot change the ID after the service account is created.
  - **Service account description**: (Optional) Enter a description of the service account's purpose. See image.
5. Click **Create and Continue**.
6. On the **Grant this service account access to project** page, from the **Role**drop-down menu, select **Service Account Key Admin**. See image.
7. Click **Done** to create the service account.

1. Navigate to **IAM & Admin** > **Service Accounts**.
2. Select the Vault service account and then click **Keys**.
3. From the **Keys**page, click **Add Key** > **Create new key**.
4. Select **JSON**as the **Key type**, then click **Create**. The private key downloads to your computer. See image.
5. Copy the JSON file to a secure location. You will need this file in the next section.

To enable GCP to authenticate with Vault, perform the following procedures in the [HashiCorp Cloud Portal](https://portal.cloud.hashicorp.com/):

- a. Enable the GCP secrets engine.
- b. Configure the GCP authentication method to use the Vault service account credentials.
- c. Create a policy file and policy.
- d. Create an access role for an IAM service account.

1. Log in to the [HashiCorp Cloud portal](https://portal.cloud.hashicorp.com/) and navigate to your Vault (**Vault Dedicated** > <Vault Cluster Name> > **Launch web UI**).
2. From the left-side navigation, select **Secrets Engines**.
3. On the **Secrets Engines** page, click **Enable new engine**. The **Enable a Secrets Engine** page appears.
4. In the Cloud section, click **Google Cloud**. See image.
5. Another **Enable a Secrets Engine** page appears. In the **Path** field, enter the path (for example, `gcp`).
6. Click **Enable engine**.

1. From the left-side navigation, select **Access** > **Authentication methods**.
2. Select **gcp**. The gcp page opens.
3. On the **Configuration**tab, click **Configure**. The **Configure Google Cloud** page appears.
4. Under **Credentials**, click **Choose File**. See image.
5. Navigate to and select the **VaultServiceAccountKey.json** file you created in the previous section.

You must create a policy that grants read and list capabilities to access your secrets path.

1. From the left-side navigation, select **Policies**> **ACL Policies**.
2. Click **Create ACL policy**. In the **Create ACL Policy** dialog box:
  1. **Name**. Enter a name for the new policy (for example, `gcp-policy`).
  2. **Policy**: Enter the ACL policy rules as shown below: `# Read permission on the k/v secrets path "/secretKV/*" capabilities = ["read", "list"] }`where `path` is the **Path for this secret** value you provided in Set up the HashiCorp Vault and secrets (in this example, `secretKV`), and the `capabilities` are `read` and `list`.
  3. Click **Create policy**.

You must use the Vault CLI to create this role.

1. Open the Vault cluster overview page (<Vault Cluster Name> > **Overview**).
2. Under **Quick actions**, select **How to access via**> **Command Line (CLI)**.
3. Enter the following: `vault write auth/gcp/role/"<Name of Role>" \ type="iam" \ policies="<Name of Policy You Created>" \ bound_service_accounts="<GCP Service Email for Cloud Connector Service Account>" max_jwt_exp="60m"`where `bound_service_accounts` is the email address associated with the [VM service account](https://help.zscaler.com/cloud-branch-connector/deploying-zscaler-cloud-connector-google-cloud-platform#VMServiceAcct) you created previously. For example: `vault write Auth/gcp/role/cc-vm-service-account \ type="iam" \ policies="gcp_policy" \ bound_service_accounts="cc-vm-service-account@cc-abc-123456.iam.gserviceaccount.com" \ max_jwt_exp="60m"`

Gather the following information to respond to Terraform deployment wizard prompts.

- From the [HashiCorp Cloud Portal](https://portal.cloud.hashicorp.com/):
  - Cluster Public URL. Navigate to your Vault cluster page, and in the Cluster URLs section, click **Public**. See image.
  - Full API path for the secret. Navigate to your secret page. The **API path** is displayed in the Paths section. See image.
  - Name of the access role you created for an IAM service account.
- From the [Google Cloud Portal](https://console.cloud.google.com/) (if you answered that you already have a service account when prompted by the Terraform wizard):
  - [Service Account email from Vault service account details](https://help.zscaler.com/cloud-branch-connector/deploying-zscaler-cloud-connector-google-cloud-platform#VMServiceAcct). You must have access to the service account to see this information.

[Image: Selecting admin as the namespace]

[Image: Creating secrets for KV Secrets Engine]

[Image: Creating secrets for KV Secrets Engine]

[Image: Creating a secret for KV Secrets Engine]

[Image: Creating service account for Vault to authenticate with GCP]

[Image: Selecting Service Account Key Admin role]

[Image: Create private key in Google Cloud Platform]

[Image: Enabling the Google Cloud secrets engine]

[Image: Selecting JSON file for Vault to communicate with Google Cloud]

[Image: Copying the cluster URL]

[Image: Finding the API path]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/supported-regions-zero-trust-gateways","lastmod":"2026-11-01T23:53Z","nid":"1538721"} -->
## Supported Regions for Zero Trust Gateways

- Source: https://help.zscaler.com/zero-trust-cloud/supported-regions-zero-trust-gateways
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Zero Trust Gateway Management > Supported Regions for Zero Trust Gateways
- Last modified: 2026-11-01T23:53Z
- Summary: Information about the supported regions for Zscaler Zero Trust Gateways in the Zscaler Admin Console.

Zscaler Zero Trust Gateways are supported in the following regions:

## Amazon Web Services

| **Region Code** | **Display Name** | **Location** |
| --- | --- | --- |
| us-east-1 | US East (N. Virginia) | Virginia, USA |
| us-east-2 | US East (Ohio) | Ohio, USA |
| us-west-1 | US West (N. California) | California, USA |
| us-west-2 | US West (Oregon) | Oregon, USA |
| eu-north-1 | Europe (Stockholm) | Stockholm, Sweden |
| eu-central-1 | Europe (Frankfurt) | Frankfurt, Germany |
| eu-south-2 | Europe (Spain) | Madrid, Spain |
| eu-west-1 | Europe (Ireland) | Dublin, Ireland |
| eu-west-2 | Europe (London) | London, UK |
| eu-west-3 | Europe (Paris) | Paris, France |
| ap-southeast-1 | Asia Pacific (Singapore) | Singapore |
| ap-south-1 | Asia Pacific (Mumbai) | Mumbai, India |
| ap-south-2 | Asia Pacific (Hyderabad) | Hyderabad, India |
| ap-southeast-2 | Asia Pacific (Sydney) | Sydney, Australia |
| ap-southeast-4 | Asia Pacific (Melbourne) | Melbourne, Australia |
| ca-central-1 | Canada (Central) | Montreal, Canada |
| sa-east-1 | South America (São Paulo) | São Paulo, Brazil |
| me-south-1 | Middle East (Bahrain) | Bahrain |

## Google Cloud Platform

| **Region Code** | **Location** |
| --- | --- |
| us-west1 | Oregon, USA |
| us-west2 | Los Angeles, California, USA |
| us-west3 | Salt Lake City, Utah, USA |
| us-west4 | Las Vegas, Nevada, USA |
| us-east1 | South Carolina, USA |
| us-east4 | N. Virginia, USA |
| us-east5 | Columbus, Ohio, USA |
| us-central1 | Iowa, USA |
| us-south1 | Dallas, Texas, USA |
| europe-west1 | Belgium |
| europe-west3 | Frankfurt, Germany |
| europe-west4 | Netherlands |
| australia-southeast1 | Sydney, Australia |
| asia-south1 | Mumbai, India |
| asia-south2 | Delhi, India |
| asia-southeast1 | Singapore |

If you are interested in support for a region that is currently unavailable, contact Zscaler Support.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/troubleshooting-cloud-connector-amazon-web-services","lastmod":"2026-11-01T23:52Z","nid":"1420646"} -->
## Troubleshooting Cloud Connector with Amazon Web Services

- Source: https://help.zscaler.com/zero-trust-cloud/troubleshooting-cloud-connector-amazon-web-services
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Troubleshooting > Troubleshooting Cloud Connector with Amazon Web Services
- Last modified: 2026-11-01T23:52Z
- Summary: Information on various issue that may occur in the Zscaler Cloud Connector and their solutions.

This article provides troubleshooting information and guidelines for [Cloud Connector deployment in Amazon Web Services (AWS)](https://help.zscaler.com/cloud-branch-connector/deploying-cloud-connector-amazon-web-services). You can review issues and corresponding solutions for Zscaler Cloud Connector.

- Deployed Cloud Connector is not displayed in the Zscaler Admin Console
- Unable to log in to the Zscaler Admin Console
- Workloads unable to access internet applications
- Workloads unable to access private applications
- Traffic is not restored after a Cloud Connector failure
- Cloud Connector experiencing connectivity disruptions

The deployed Cloud Connector might not be visible in the Zscaler Admin Console for various reasons. The following tables list provisioning and deployment issues and their corresponding troubleshooting actions.

## Provisioning Issues

| Potential Causes | Troubleshooting Action |
| --- | --- |
| The Cloud Connector instance state is not running after 20 minutes, or the status check is not **2/2 checks passed** after 10 minutes. | In the AWS Management Console: Select the Cloud Connector instance and click**Actions**.; Navigate to the **Monitor and troubleshoot**submenu and select **Get system log**.; Copy the **Get system log** information into your desired text editor.; From the **Monitor and troubleshoot** submenu, select **Get instance screenshot**.; Download the selected get instance screenshot.; Reboot the instance by clicking **Instance state**, followed by **Reboot instance**.; If the appliance does not proceed after rebooting and indicate two of two checks have passed, delete and re-create the appliance. |
| Cloud Connector does not appear in the Zscaler Admin Console after provisioning. | Confirm the following: The [cloud provisioning template](https://help.zscaler.com/cloud-branch-connector/about-cloud-provisioning-templates) in the Zscaler Admin Console is for AWS.; The CloudFormation or Terraform [cloud automation script](https://help.zscaler.com/cloud-branch-connector/deployment-templates-zscaler-cloud-connector) process used the correct provisioning URL. If it used an incorrect provisioning URL, remove the instance and create a new instance.; AWS Secrets Manager has the correct secret credentials (api_key, Zscaler Admin Console username, and Zscaler Admin Console password).; The role assigned to the Cloud & Branch Connector username has the **Cloud Connector Provisioning** and **Location Management** permissions assigned.; Network connectivity is configured correctly. Verify route tables, NAT gateway, and the internet gateway. To test, from the Cloud Connector appliance console, attempt to ping or cURL a destination on the internet.; Security configuration within AWS is accurate. Inbound and outbound access is verified by security groups and network access control lists (ACLs).; The workload traffic that transits the Cloud Connector is included in the security group and network ACL rule sets.; The bootstrap file (userdata.cfg) was properly imported into the Cloud Connector. You can verify this by logging into the appliance via SSH and issuing the following command: cat /etc/cloud/cloud.cfg.d/userdata.cfg. Confirm that Terraform and CloudFormation variables are present in this file. If the variables are not present, re-create the appliance.; The Identity Access Management (IAM) role attached to the Cloud Connector Amazon Machine Image (AMI) instance in AWS has the SecretsManagerRead policy attached to it. If it does not, attach the correct IAM role and re-create a new Cloud Connector instance in the AWS Management Console. To avoid access issues with the Cloud Connector instance, make sure to replicate the AWS Secrets Manager from a working region to new regions.; The instance types are supported in the AWS Marketplace. Zscaler recommends deploying with m6i.large or m6i.4xlarge instance types. |
| Cloud Connector does not automatically update and cannot reach the package repository. | Perform the following reachability checks from the Cloud Connector console: Verify that pkg-repo.zscaler.com is reachable on TCP port 443 by running either telnet pkg-repo.zscaler.com 443 or curl pkg-repo.zscaler.com 443.; Check whether Cloud Connector received a 500 response from the server indicating that connectivity has succeeded. If the request times out, verify the AWS network configuration (NAT gateway, route table, and internet gateway).; Run the pkg info \| grep certificates command to verify that the certificate bundle is present.; Run the sudo pkg update command to verify the software update. |
| CloudFormation built-in rules and constraints are not satisfied due to user input error. | Verify the following: There is no mismatch with the CloudFormation stack parameters: virtual private cloud (VPC), subnet, and availability zone.; The provisioning URL field is not empty and is in the correct format. |
| Security admin IAM user permissions are not set correctly for Cloud Connector deployment. | Verify that the security admin IAM user has the following access: Full access to the AWS Management Console; Full access to the AWS Secrets Manager; Full access to Amazon CloudWatch Events; Full access to AWS CloudFormation; Full access to AWS Lamda ServiceCreate the following policies and roles: { “Version”: “2012-10-17", “Statement”: [ { “Sid”: “VisualEditor0”, “Effect”: “Allow”, “Action”: [ “cloudformation:CreateUploadBucket”, “aws-marketplace:ViewSubscriptions”, “cloudformation:ListStacks”, “aws-marketplace:ListBuilds”, “sns:ListTopics”, “s3:CreateBucket”, “aws-marketplace:Subscribe”, “s3:PutObject”, “aws-marketplace:StartBuild”, “ssm:DescribeParameters”, “route53:ListTrafficPolicies”, “route53:GetHostedZoneCount”, “secretsmanager:ListSecrets” ], “Resource”: “*” }, { “Sid”: “VisualEditor1”, “Effect”: “Allow”, “Action”: [ “logs:ListTagsLogGroup”, “iam:CreateInstanceProfile”, “route53resolver:GetResolverRuleAssociation”, “secretsmanager:DescribeSecret”, “secretsmanager:PutSecretValue”, “route53resolver:ListTagsForResource”, “secretsmanager:CreateSecret”, “iam:RemoveRoleFromInstanceProfile”, “iam:DeletePolicy”, “iam:CreateRole”, “iam:AttachRolePolicy”, “iam:PutRolePolicy”, “ssm:GetParameter”, “iam:AddRoleToInstanceProfile”, “route53resolver:DisassociateResolverRule”, “route53resolver:CreateResolverEndpoint”, “iam:ListInstanceProfilesForRole”, “ssm:DeleteParameter”, “iam:PassRole”, “iam:DetachRolePolicy”, “secretsmanager:GetSecretValue”, “iam:ListAttachedRolePolicies”, “iam:DeleteRolePolicy”, “route53resolver:GetResolverRule”, “route53resolver:DeleteResolverEndpoint”, “iam:DeleteInstanceProfile”, “iam:GetRole”, “iam:GetInstanceProfile”, “logs:DeleteLogGroup”, “route53resolver:DeleteResolverRule”, “route53resolver:AssociateResolverRule”, “iam:DeleteRole”, “ssm:GetParameters”, “route53resolver:GetResolverEndpoint”, “logs:CreateLogGroup”, “ssm:DeleteParameters”, “ssm:PutParameter”, “iam:CreatePolicy”, “route53resolver:ListResolverEndpointIpAddresses”, “iam:ListPolicyVersions”, “ssm:ListTagsForResource”, “iam:GetRolePolicy”, “route53resolver:CreateResolverRule” ], “Resource”: [ “arn:aws:ssm:*:awsaccountnumber:parameter/*“, “arn:aws:logs:*:awsaccountnumber:log-group:*“, “arn:aws:iam::awsaccountnumber:instance-profile/*“, “arn:aws:iam::awsaccountnumber:policy/*“, “arn:aws:iam::awsaccountnumber:role/*“, “arn:aws:route53resolver:*:awsaccountnumber:resolver-endpoint/*“, “arn:aws:route53resolver:*:awsaccountnumber:resolver-rule/*“, “arn:aws:secretsmanager:*:awsaccountnumber:secret:*” ] } ] } |
| CloudFormation stack fails to deploy. Resources and the stack are deleted. | Investigate the event log for errors.; Confirm that the user has the correct permissions.; Verify that Supported Images in the AWS Marketplace terms and conditions were accepted before deploying to the AWS account for the first time. Navigate to the Cloud Connector offering in the [AWS Marketplace](http://aws.amazon.com/marketplace/pp/prodview-cvzx4oiv7oljm)and accept the terms and conditions. |
| Terraform script fails to deploy resources. | Verify Terraform credentials (i.e., Access Key/Secret Key).; Verify that the user has permissions to create VPC, NAT Gateway, GWLB, IAM, EC2, Elastic IP, Route Tables, Subnets, Security Groups, etc. |

## Deployment Issues

| Error | Troubleshooting Action |
| --- | --- |
| AWS Cloud Connector is in the wrong geolocation in the Zscaler Admin Console. | Ensure that the Cloud Connector Service Interface is associated with the correct NAT gateway and that the NAT gateway has the correct elastic IP address assigned. If you are not using a NAT gateway, ensure that the public or elastic IP associated with the elastic network interface (ENI) of Cloud Connector is correct. |
| AWS Cloud Connector is in an inactive state. | Verify the following: The registration and policy fetch are correct. To check, contact Zscaler Support.; The Cloud Connector instance is up and running within the AWS Management Console.; The Zscaler Admin Console can be reached from your VPC/Subnet.; AWS Secrets Manager has the correct secret credentials in case of a secrets rotation.; The network is connected. Verify by checking the route tables, NAT gateway, and internet gateway configurations.; The security group or network ACL is not blocking the Cloud Connector TCP/UDP 443. |
| The Internet & SaaS gateway IP address in the Zscaler Admin Console is not populated, and the tunnel is not established. | Verify the following: The service security group is not blocking access to the management interface (TCP/UDP 443) and the network ACL. You can find a list of Zscaler IP addresses here: [https://config.zscaler.com](http://config.zscaler.com); In the Zscaler Admin Console, check the Internet & SaaS gateway configuration. Ensure that **Gateway configuration** is set to **Auto** or **Manual** (with the proper Data Center set), or in the case of Override, the proper IP Address is input.; The PAC file for pac.<cloudname>.net is reachable from the customer's VPC or subnet. |
| Cloud Connector could not enroll in Private Access. | If the user deploys more than 100 Cloud Connectors within a group and within the same VPC or availability zone, then the Private Access enrollment fails. Contact Zscaler Support. |
| AWS Cloud Connector does not have an assigned Private Access Private Service Edge or Private Access Public Service Edge IP address, resulting in a DNS or reachability issue. | Verify the following: The DNS to ec2br.prod.zpath.net is resolvable from the Cloud Connector.; The connection to TCP/UDP port 443 is allowed in the network ACL. You can find a list of Zscaler IP addresses here: [https://config.zscaler.com](http://config.zscaler.com) |

| Potential Causes | Troubleshooting Action |
| --- | --- |
| Account not created. | Contact Zscaler Support. |
| Cloud Connector SKUs not enabled. | Contact Zscaler Support. |
| Authentication failed. | Verify that the username and all credentials are valid. In the Zscaler Admin Console, confirm the admin. |
| User could not access single sign-on (SSO) from the Zscaler Admin Console. | Contact Zscaler Support. |

The traffic from the Cloud Connector instance might not be able to reach internet applications.

Check the following areas to determine what is preventing the traffic connection:

| **Potential Causes** | **Troubleshooting Action** |
| --- | --- |
| The firewall policy to the Cloud Connector. | Verify the following: The firewall policy (network security group, security group, and network ACL) to the Cloud Connector does not prevent connection.; The Internet & SaaS firewall policy allows traffic through the Cloud Connector. |
| The connectivity from Cloud Connector to Internet & SaaS. | Verify the following: In the Zscaler Admin Console, the [Internet Access Gateway](https://help.zscaler.com/cloud-branch-connector/about-zia-gateways) is populated.; Session logs display the Cloud Connector self-traffic.; Your tunnel insights are displayed from the Internet & SaaS tunnel insights.; You can view your assigned Internet & SaaS cloud. If not, verify network connectivity.; The security group is not blocking outbound TCP/UDP 443 access to Internet & SaaS cloud.; The network ACL does not block outbound TCP/UDP 443 access to the Internet & SaaS cloud. |
| The ingress routing to the Cloud Connector. | Verify the following: The routing of the workload subnet does not interfere with the connection. Configure a default route and associate the route to the ENI of the Cloud Connector Service Interface.; The network ACL on the Cloud Connector subnet does not restrict access from the workload subnet.; The security group on the Cloud Connector service ENI does not restrict access from the workload. |
| The egress path from the Cloud Connector. | Verify the following: The gateway configuration does not interfere with the connection.; In the Zscaler Admin Console, the forwarding policy is set up correctly.; The network ACL and other services on egress are functional. |
| The status of the Cloud Connector. | Verify the following: The Amazon Machine Image (AMI) status of the Cloud Connector in AWS and the Zscaler Admin Console.; The service status of the Cloud Connector in AWS. |
| Incorrect or incompatible AMI and deployment templates. | Verify the following: You are deploying the [latest templates](https://help.zscaler.com/cloud-branch-connector/deployment-templates-zscaler-cloud-connector) and [AMI](https://help.zscaler.com/cloud-branch-connector/identifying-zscaler-cloud-connector-version).; For non-auto scaling deployments that were deployed before AWS AMI version ZS6.1.25.0, the service interface is device index 1. Cloud Connector AMIs released after October 19, 2023 have the service interface as device index 0. |
| Gateway load balancer target group is showing target Cloud Connector instance as unhealthy. | Verify the following: The target IP is correct. For non-auto scaling deployments, this is the service interface device index 0. For auto scaling deployments, this is the EC2 instance ID.; The status of the instance is healthy. From the Zscaler Admin Console dashboard, check the health status reported and any error codes or messages. |
| Connectivity issues between Cloud Connectors within a group or cluster. | Verify the following: All Cloud Connectors communicate with each other within a given connector group or cluster via their service interface(s) to share information such as fully qualified domain name (FQDN)/wildcard FQDN synchronization.; No firewall or security groups are blocking network communications between Cloud Connectors within a given VPC/VNet and their availability zones. |

To enable Auto Scaling, contact Zscaler Support.

If your workloads are unable to access private applications, verify the following:

- The parameters in the CloudFormation template that pertain to the Private Access endpoints are accurate.
- The application name matches the route53 configuration.
- The application is available from the Zscaler Admin Console.

If your traffic is not restored after Cloud Connector's connection failure, verify the following:

- The Cloud Connector backup instance is in good health with the high-availability model.
- The Cloud Connector instance is healthy in the Gateway Load Balancer console of AWS.

To avoid connectivity disruptions and/or unreachable destinations, source/destination checks must be disabled in the AWS Management Console.

To verify that the source/destination check setting is disabled:

1. Log in to the AWS Management Console.
2. In the **Search** field, enter `EC2`. Select **EC2**. The **EC2 Dashboard** appears.
3. On the **EC2 Dashboard**, from the left-side navigation, under **Instances**, select **Instances**.
4. On the **Instances** page, select your NAT instance.
5. On the **Instance summary** page, select **Actions**. See image.
6. From the **Actions** drop-down menu, select **Networking** > **Change source/destination check**. The **Change Source / destination check** window appears. See image.
7. In the **Change source/destination check** window, select the **Stop** checkbox. See image.
8. Click **Save**.

Occasionally, you might need to access the Cloud Connector appliance’s CLI for troubleshooting or monitoring purposes. To access the CLI:

- Bastion Host
- Management Interface Elastic IP Address

A Bastion host is a lightweight EC2 instance installed within the same subnet as the Cloud Connector Service Interface or Management Interface. This option leverages SSH as the connection protocol. Therefore, you must have access to the SSH key pair used when instantiating the appliances. Configure SSH keys exclusively using Terraform during deployment.

When using Terraform to configure new appliances, a Bastion host is automatically created for management and troubleshooting purposes.

To access the appliance management interface remotely via Bastion host:

1. Log in to the AWS Management Console.
2. In the **Search** field, enter `EC2`. Select **EC2**. The **EC2 Dashboard** appears. See image.
3. On the **EC2 Dashboard** page, under **Resources**, select **Elastic IPs**. The **Elastic IPs** page appears. See image.
4. On the **Elastic IPs** page, click **Allocate Elastic IP address**. See image.
5. In the **Allocate Elastic IP address** window, configure the **Elastic IP address settings** based on your preferences, and then click **Allocate**. See image.
6. In the **Elastic IP address allocated successfully** window, click **Associate this Elastic IP address**. Alternatively, select the checkbox of the Elastic IP address you created. Then, click **Actions** and from the drop-down menu, select **Associate Elastic IP address**. The **Associate Elastic IP address** page appears. See image.
7. On the **Associate Elastic IP address** page, configure the following settings: See image.
  - **Resource type**: Select **Network interface**.
  - **Network interface**: Choose the Elastic Network Interface (ENI) of the Bastion host.
  - **Private IP address**: Choose a private IP address to associate with the Elastic IP address.
  - **Reassociation**: To enable the Elastic IP address to be reassociated with a different resource when it is already associated with a resource, select the checkbox. When complete, click **Associate**.
8. In the **Search** field, enter `VPC`. Select **VPC**. The **VPC Dashboard** appears. See image.
9. On the **VPC Dashboard** page, in the left-side navigation, select **Route tables**. The **Route tables** page appears. See image. Before creating or configuring your route table, identify your [public IP Address](https://ip.zscaler.com). This is the address that your SSH management traffic comes from.
10. On the **Route tables** page, select the checkbox of the route table. Then, from the **Actions** drop-down menu, select **Edit routes**. The **Edit routes** page appears. See image.
11. On the **Edit routes** page: See image.
  1. In the **Destination** field, enter your public IP address.
  2. In the **Target field**, enter the internet gateway (IGW) of your Amazon VPC.
  3. Click **Save changes**.
12. In the **Search** field, enter `EC2`. Select **EC2**. The **EC2 Dashboard** appears. See image.
13. On the **EC2 Dashboard**, under **Resources**, select **Security groups**. See image.
14. On the **Security Groups** page, select the checkbox of the security group. Then, click **Actions** and from the drop-down menu, select **Edit inbound rules**. See image.
15. On the **Edit inbound rules** page, update the Network ACL and/or Security Group ACLs to allow inbound SSH traffic from your public IP address to the Bastion host. Zscaler highly recommends that your inbound rules are restricted to your known management IP address sources.
16. From your management host, use your preferred SSH client to Secure Socket Shell (SSH) to the Cloud Connector. The administrative login for the appliance is `zsroot`. For Linux terminal SSH clients, enter the command `ssh -i mySshKey.pem zsroot@100.64.0.1`.

Cloud Connector appliances have a dedicated management interface installed, by default, in the same subnet as the Service Interface. This option leverages SSH as the connection protocol. Hence, you must have access to the SSH key pair used when instantiating the appliances.

To access the appliance management interface remotely:

1. Log in to the AWS Management Console.
2. In the **Search** field, enter `EC2`. Select **EC2**. The **EC2 Dashboard** appears. See image.
3. On the **EC2 Dashboard**, under **Resources**, select **Elastic IPs**. The **Elastic IPs** page appears. See image.
4. On the **Elastic IPs** page, select **Allocate Elastic IP address**. See image.
5. In the **Allocate Elastic IP address** window, configure the **Elastic IP address settings** based on your preferences, and then click **Allocate**. See image.
6. In the **Elastic IP address allocated successfully** window, click **Associate this Elastic IP address**. Alternatively, select the checkbox of the Elastic IP address you created. Then, click **Actions** and from the drop-down menu, select **Associate Elastic IP address**. The **Associate Elastic IP address** page appears. See image.
7. On the **Associate Elastic IP address** page, configure the following settings:
  - **Resource type**: Select **Network interface**.
  - **Network interface**: Choose the Elastic Network Interface (ENI) of the Management and Service Interface Subnet.
  - **Private IP address**: Choose a private IP address to associate with the Elastic IP address.
  - **Reassociation**: To enable the Elastic IP address to be reassociated with a different resource when it is already associated with a resource, select the checkbox. When complete, click **Associate**. See image.
8. In the **Search** field, enter `VPC`. Select **VPC**. The **VPC Dashboard** appears. See image.
9. On the **VPC Dashboard** page, in the left-side navigation, select **Route tables**. The **Route tables** page appears. See image. Before creating or configuring your route table, identify your [public IP Address](https://ip.zscaler.com). This is the address that your SSH management traffic is sourced from.
10. On the **Route tables** page, select the checkbox of the route table. Then, from the **Actions** drop-down menu, select **Edit routes**. The **Edit routes** page appears. See image.
11. On the **Edit routes** page:
  - In the **Destination** field, enter your public IP address.
  - In the **Target field**, enter the internet gateway (IGW) of your Amazon VPC.
  - Click **Save changes**. See image.
12. In the **Search** field, enter `EC2`. Select **EC2**. The **EC2 Dashboard** appears. See image.
13. On the **EC2 Dashboard**, under **Resources**, select **Security groups**. See image.
14. On the **Security Groups** page, select the checkbox of the security group. Then, click **Actions** and from the drop-down menu, click **Edit inbound rules**. See image.
15. On the **Edit inbound rules** page, update the Network ACL and/or Security Group ACLs to allow inbound SSH traffic from your public IP address to the Bastion host. Zscaler highly recommends that your inbound rules are restricted to your known management IP address sources.
16. From your management host, use your preferred SSH client to SSH to the Cloud Connector. The administrative login for the appliance is `zsroot`. For Linux terminal SSH clients, the command is `ssh -i mySshKey.pem zsroot@100.64.0.1`.

[Image: Searching EC2 in the AWS Management Console]

[Image: Selecting Elastic IPs under Resources in the AWS Management Console]

[Image: Selecting Allocate Elastic IP address in the AWS Management Console]

[Image: Configuring Elastic IP address settings in the Allocate Elastic IP address window of the AWS Management Console]

[Image: Clicking the Associate this Elastic IP address button in the AWS Management Console]

[Image: Configuring settings in the Associate Elastic IP address window of the AWS Management Console]

[Image: Selecting VPC under the Search field in the AWS Management Console]

[Image: Selecting Route tables from the left-side navigation of the VPC Dashboard in the AWS Management Console]

[Image: Selecting Edit routes on the Route tables page of the AWS Management Console]

[Image: Configuring settings on the Edit routes page of the AWS Management Console]

[Image: Searching EC2 in the AWS Management Console]

[Image: Selecting Security groups under Resources on the EC2 Dashboard of the AWS Management Console]

[Image: Selecting Edit inbound rules on the Security Groups page of the AWS Management Console]

[Image: Searching EC2 in the AWS Management Console]

[Image: Selecting Elastic IPs under Resources in the AWS Management Console]

[Image: Selecting Allocate Elastic IP address in the AWS Management Console]

[Image: Configuring Elastic IP address settings in the Allocate Elastic IP address window of the AWS Management Console]

[Image: Clicking the Associate this Elastic IP address button in the AWS Management Console]

[Image: Configuring settings in the Associate Elastic IP address window of the AWS Management Console]

[Image: Selecting VPC under the Search field in the AWS Management Console]

[Image: Selecting Route tables from the left-side navigation of the VPC Dashboard in the AWS Management Console]

[Image: Selecting Edit routes on the Route tables page of the AWS Management Console]

[Image: Configuring settings on the Edit routes page of the AWS Management Console]

[Image: Searching EC2 in the AWS Management Console]

[Image: Selecting Security groups under Resources in the EC2 Dashboard of the AWS Management Console]

[Image: Selecting Edit inbound rules on the Security Groups page of the AWS Management Console]

[Image: Selecting Actions from the upper-right corner of the NAT Instance summary page of the AWS Management Console]

[Image: Navigating to the Change source/destination check window in the AWS Management Console]

[Image: Selecting the Stop checkbox in the Change Source / destination check window of the AWS Management Console]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/troubleshooting-cloud-connector-google-cloud-platform","lastmod":"2026-11-01T23:53Z","nid":"1462141"} -->
## Troubleshooting Cloud Connector with Google Cloud Platform

- Source: https://help.zscaler.com/zero-trust-cloud/troubleshooting-cloud-connector-google-cloud-platform
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Troubleshooting > Troubleshooting Cloud Connector with Google Cloud Platform
- Last modified: 2026-11-01T23:53Z
- Summary: Troubleshooting information and guidelines for deploying Zscaler Cloud Connector with Google Cloud Platform.

This article provides troubleshooting information and guidelines about [Cloud Connector deployment in Google Cloud Platform (GCP)](https://help.zscaler.com/cloud-branch-connector/deploying-zscaler-cloud-connector-google-cloud-platform). You can learn about different issues, potential causes, and corresponding solutions for the deployed Zscaler Cloud Connector.

- Deployed Cloud Connector is not displayed in the Zscaler Admin Console
- Unable to log in to the Zscaler Admin Console
- Workloads unable to access internet applications
- Workloads unable to access private applications
- Unable to monitor Internet & SaaS traffic in the Zscaler Admin Console
- Unable to monitor Private Access traffic in the Zscaler Admin Console
- Traffic not restored on Cloud Connector failure
- Cloud Connector experiencing connectivity disruptions

The following tables list provisioning and deployment issues and their corresponding troubleshooting actions.

## Provisioning Issues

| Potential Causes | Troubleshooting Actions |
| --- | --- |
| Deployed Cloud Connector is not displayed in the Zscaler Admin Console. | Check whether user data is missing or is provided in an incorrect format. You can verify user data by reviewing the GCP Secret Manager, or by reviewing the User Data file located on the appliance here: /etc/cloud/cloud.cfg.d/userdata.cfgVerify the API Key (api-key).; Verify login credentials (username, password).; Verify that the provisioning URL is in the correct format and there are no spaces.; From the Zscaler Admin Console, verify that the Provisioning Template type is correct.; Check whether the Cloud Connector has sufficient privileges to the GCP Secret Manager. Ensure that the GCP Secret Manager API is enabled and that the Cloud Connector can access it. Also, ensure that the service account that the Cloud Connector is using has the **Secret Manager Secret Accessor** role assigned to it.; Verify the firewall rules to ensure that Cloud Connectors can reach the Zscaler cloud.; Verify that Cloud Connector can reach the package repository when using certificate authority. Verify that you can do a package update to confirm that the package repository is reachable and certificates are valid. Run the `sudo pkg update` command.; Verify DNS resolution. |
| Terraform script fails to create a virtual private cloud (VPC) in a greenfield deployment. | Check whether the user has surpassed the VPC creation limit. Also, verify that the service principal that Terraform is using has permissions to create new VPCs. |

## Deployment Issues

| Potential Causes | Troubleshooting Actions |
| --- | --- |
| Cloud Connector is in the wrong geolocation in the Zscaler Admin Console. | Verify that the outbound NAT and routing take the appropriate egress path. In the Zscaler Admin Console, confirm the public IP address. If the public IP address is correct, contact Zscaler Support. |
| Cloud Connector is in an inactive state. | Verify the following: The registration and policy fetch are correct. To check, contact Zscaler Support.; The Cloud Connector instance is up and running within the GCP Management Console.; The Zscaler Admin Console can be reached from the Cloud Connector VPC/subnet.; GCP Secret Manager has the correct secret credentials in case of a secrets rotation.; The network is connected. Verify the route tables, Cloud NAT, and DNS.; The firewall rule is not blocking the Cloud Connector TCP/UDP 443 traffic. |
| In the Zscaler Admin Console, the Internet & SaaS gateway IP address is not populated. | From the Zscaler Admin Console, check the Internet & SaaS gateway configuration. If the gateway configuration is not set correctly, contact Zscaler Support. |

| Potential Causes | Troubleshooting Action |
| --- | --- |
| Account not created. | Contact Zscaler Support. |
| Cloud Connector SKUs not enabled. | Contact Zscaler Support. |
| Authentication failed. | Verify that the username and all credentials are valid. In the Zscaler Admin Console, confirm that the admin exists. |
| User could not access single sign-on (SSO) from the Zscaler Admin Console. | Contact Zscaler Support. |

The traffic from the deployed Cloud Connector might not be able to reach internet applications.

Check the following areas to isolate and identify what is preventing the traffic connection:

| **Potential Causes** | **Troubleshooting Action** |
| --- | --- |
| The firewall policy attached to the Cloud Connector | Verify that the Internet & SaaS firewall policy is not blocking any outbound traffic. |
| The connectivity from the Cloud Connector to Internet & SaaS | Verify the following: In the Zscaler Admin Console, the [Internet Access Gateway](https://help.zscaler.com/cloud-branch-connector/about-internet-access-gateways) is populated; Session logs display the Cloud Connector self-traffic.; In the Zscaler Admin Console, your tunnel insights are displayed. |
| The ingress routing to the Cloud Connector | Verify the following: The routing of the workloads. Depending on the architecture chosen, review network tags to ensure that workloads are using the correct default route.; The firewall rule on the workload. |
| The egress path from the Cloud Connector | Verify the following: The gateway configuration.; The forwarding policy in the Zscaler Admin Console.; The firewall rules and other services on egress.; The routing of the Cloud Connector. Depending on the architecture chosen, review network tags to ensure that the Cloud Connectors are using the correct default route. |
| The status of the Cloud Connector | Verify the following: The VM status of the Cloud Connector; The service status of the Cloud Connector |
| Connectivity issues between Cloud Connectors within a group or cluster | Verify the following: All Cloud Connectors communicate with each other within a given connector group or cluster via their service interface(s) to share information such as FQDN/wildcard FQDN synchronization.; No firewall or security groups are blocking network communications between Cloud Connectors within a given VPC/VNet and their availability zones. |

If your workloads are unable to access private applications, verify the following:

- The parameters in the Terraform template that pertain to the Private Access endpoints are accurate (specifically, the GCP Cloud DNS forwarding rules).
- The application name matches the GCP Cloud DNS forwarding configuration.
- The application is available from the Zscaler Admin Console.
- If FQDN-based Private Access App Segments are in use, verify that DNS is passing through the Cloud Connector.

If you are unable to monitor Internet & SaaS traffic in the Zscaler Admin Console, verify the following:

- The Cloud Connector location created in the Zscaler Admin Console is registered in the Zscaler Admin Console.
- The tunnel logs in the Zscaler Admin Console are showing traffic to Internet & SaaS.
- When filtering traffic in the Zscaler Admin Console, you use the Internet & SaaS filter.
- In the Zscaler Admin Console traffic forwarding rules drop-down menu, the [traffic forwarding method is Internet & SaaS](https://help.zscaler.com/cloud-branch-connector/configuring-traffic-forwarding-rule#zia).

If you are unable to monitor Private Access traffic in the Zscaler Admin Console, verify the following:

- The Cloud Connector is registered in the Zscaler Admin Console.
- In the Zscaler Admin Console, the forwarding filter is Private Access.
- In the Zscaler Admin Console, policy is configured correctly. Ensure that access policies and client forwarding policies are working.

If your traffic is not restored after Cloud Connector connection failure, verify the following:

- The Cloud Connector backup instance is in good health with the high-availability model.
- In the Load Balancing console of GCP, the Cloud Connector instance is healthy.

To avoid connectivity disruptions and/or unreachable destinations, IP forwarding must be enabled on the Cloud Connector service interface. The IP Forwarding setting is enabled by default on the service NIC of the Cloud Connector with the Zscaler Terraform deployment script. You can verify the setting from the GCP console. If the setting has been disabled, refer to the [GCP documentation](https://cloud.google.com/compute/docs/instances/update-instance-properties#updatable-properties) to re-enable the setting.

Configure SSH keys exclusively using Terraform during deployment.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/troubleshooting-cloud-connector-hashicorp-vault-google-cloud-platform","lastmod":"2026-11-01T23:53Z","nid":"1515891"} -->
## Troubleshooting Cloud Connector with HashiCorp Vault for Google Cloud Platform

- Source: https://help.zscaler.com/zero-trust-cloud/troubleshooting-cloud-connector-hashicorp-vault-google-cloud-platform
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Troubleshooting > Troubleshooting Cloud Connector with HashiCorp Vault for Google Cloud Platform
- Last modified: 2026-11-01T23:53Z
- Summary: Troubleshooting Cloud Connector with HashiCorp Vault for Google Cloud Platform.

This article provides troubleshooting information and guidelines about Cloud Connector deployment in Google Cloud Platform (GCP) with HashiCorp Vault, described in [Storing Your Secret Credentials in HashiCorp Vault for Google Cloud Platform-Based Cloud Connectors](https://help.zscaler.com/cloud-branch-connector/storing-your-secret-credentials-hashicorp-vault) and [Deploying Zscaler Cloud Connector on the Google Cloud Platform](https://help.zscaler.com/cloud-branch-connector/deploying-zscaler-cloud-connector-google-cloud-platform). You can learn about different issues, potential causes, and corresponding solutions for the deployed Zscaler Cloud Connectors.

## Prerequisites

To troubleshoot your Cloud Connector, ensure that you have the following:

- In GCP:
  - Access to the GCP console: The ability to view and modify settings and IAM service accounts and roles in GCP.
  - Vault/GCP sync service account: A service account with the secrets JSON file exported from GCP and uploaded to HashiCorp with the **Service Account Key Admin** GCP role.
  - Cloud Connector virtual machine (VM) service account: A service account assigned to the VM at deployment and referenced in HashiCorp as `bound_service_accounts` with the **Service Account Token Creator** GCP role.
- In HashiCorp:
  - Access to the Vault CLI: The ability to view and modify settings in the Vault CLI.
  - Vault address: The URL of your Vault instance.
  - Secret path: The path within the Vault where secrets are stored.
  - GCP IAM authentication: Ensure that the GCP auth method is enabled.
  - GCP IAM role: The GCP IAM type authentication role.

## Troubleshooting

The following is a step-by-step troubleshooting guide for Cloud Connector deployment in GCP with HashiCorp:

- Verify the VM service account configuration
- Verify the VM configuration
- Verify the Vault configuration
- Verify your VM network connectivity

The following table displays common issues and resolutions:

| **Potential Causes** | **Troubleshooting Action** |
| --- | --- |
| Permission is denied or authentication fails. | Verify the following: The service account assigned to the Cloud Connector VMs has the **Service Account Token Creator** role.; The service account is included in the Vault's `bound_service_accounts`.; `max_jwt_exp` is correctly set in the Vault.; Check your GCP `jwt`: `https://jwt.ms/` |
| The Vault is unreachable from the VM, causing network connectivity issues. | Verify the following: Firewall rules allow outbound traffic to the Vault on port 8200.; The Vault address and DNS resolution are correct from the Cloud Connector.; There are no network outages or issues with connectivity or routing. |
| Your access is denied to the secret path after authentication succeeds. | Verify the following: The policies attached to the IAM authentication role include "read and list" permissions for the secret path.; The secret exists at the specified path.; There are no incorrectly spelled words or incorrect paths. |

To verify your service account configuration:

- Check the service account attached to the VM:
  1. Sign in to the [GCP console](https://console.cloud.google.com/).
  2. In the top-left corner of the console, select **Navigation menu (.)**.
  3. From the left-side navigation, select **Compute Engine** > **VM instances**.
  4. On the **VM instances** page, select your VM instance.
  5. On the **Details** tab, under **API and identity management**, verify that the correct **Service account** is displayed.
- Verify the service account permissions:
  1. Sign in to the [GCP console](https://console.cloud.google.com/).
  2. In the top-left corner of the console, select **Navigation menu (.)**.
  3. From the left-side navigation, select **IAM & Admin** > **Service Accounts**
  4. On the **Service accounts** page, select your service account.
  5. On the service account page, click the **Permissions** tab.
  6. On the **Permissions** tab, ensure that the **Service Account Token Creator** role is correctly assigned.
- Confirm the service account in the HashiCorp Vault's `bound_service_accounts` by contacting your Vault administrator to verify that the service account email is listed in the `bound_service_accounts` for the IAM authentication role.

To verify your VM configuration, check your VM metadata:

1. Sign in to the [GCP console](https://console.cloud.google.com/).
2. In the top-left corner of the console, select **Navigation menu (.)**.
3. From the left-side navigation, select **Compute Engine** > **VM instances**.
4. On the **VM instances** page, select your VM instance.
5. On the **Details** tab, locate the **Custom metadata** section.
6. Verify that the metadata keys for **Vault Address**, **Secret Path**, and **IAM Auth Role** are correctly set per your Vault configuration. `ZSCALER hcp_vault_addr: "https://vault-cluster-public-vault-example.z1.hashicorp.cloud:8200" hcp_vault_secret_path: "admin/secret/data/example-zscaler-secret" hcp_vault_role_name: "example-iam-auth-role"`

To verify your Vault configuration:

- Confirm your Vault address and accessibility:
  - Ensure that the Vault address, protocol, and port number (i.e., 8200) are correct.
  - Ensure that the Vault UI or API is reachable from your machine by testing the accessibility.
- Verify your GCP IAM authentication role in the Vault by confirming the following with your administrator:
  - The GCP IAM authentication role exists and is named correctly.
  - The type is set to `iam`.
  - The appropriate policies are attached.
  - The `max_jwt_exp` is set to `"60m`".
  - The `bound_service_accounts` include your VM service account.
- Verify your Vault GCP auth credentials:
  - Configure the GCP auth method to use the `VaultServiceAccountKey.json` credentials. Ensure that you successfully created a service account with the **Service Account Key Admin** permissions and that the service account credentials are exported and used for `auth/gcp/config` (i.e., `VaultServiceAccountKey.json`).
  - HashiCorp CLI: `vault write auth/gcp/config \` `credentials=@VaultServiceAccountKey.json`

To test your network connectivity:

1. Verify that your network tags and firewall rules are correctly set:
  1. Sign in to the [GCP console](https://console.cloud.google.com/).
  2. In the top-left corner of the console, select **Navigation menu (.)**.
  3. From the left-side navigation, select **Compute Engine** > **VM instances**.
  4. On the **VM instances** page, select your VM instance.
  5. On the **Details** tab, in the **Network tags** section, note the tags assigned to your VM.
  6. In the top-left corner of the console, select **Navigation menu (.)**.
  7. From the left-side navigation, select **VPC Network** > **Firewall**.
  8. On the **Firewall policies** page, ensure that your outbound traffic to the Vault address on port 8200 is permitted.
2. Secure Socket Shell (SSH) into your VM instance.
3. Replace `<vault-address>` with your Vault's address. Omit `https://` or `http://`. `sudo bash nc -zv vault-cluster-public-vault-abc.xyz.z1.hashicorp.cloud 8200 Connection to vault-cluster-public-vault-abc.xyz.z1.hashicorp.cloud 8200 port [tcp/*] succeeded!`
4. Test the connection using `curl`. `curl -v <vault-address>:8200/v1/sys/health A JSON response indicating Vault's health should be returned.`
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/troubleshooting-cloud-connector-microsoft-azure","lastmod":"2026-11-01T23:53Z","nid":"1420786"} -->
## Troubleshooting Cloud Connector with Microsoft Azure

- Source: https://help.zscaler.com/zero-trust-cloud/troubleshooting-cloud-connector-microsoft-azure
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Troubleshooting > Troubleshooting Cloud Connector with Microsoft Azure
- Last modified: 2026-11-01T23:53Z
- Summary: Troubleshooting information and guidelines for deploying Cloud Connector with Microsoft Azure.

This article provides troubleshooting information and guidelines about Cloud Connector deployment in Microsoft Azure. You can learn about different issues, potential causes, and corresponding solutions for the deployed Cloud Connector.

- Deployed Cloud Connector is not displayed in the Zscaler Admin Console
- Unable to log in to the Zscaler Admin Console
- Workloads unable to access internet applications
- Workloads unable to access private applications
- Unable to monitor the traffic in the Zscaler Admin Console
- Unable to monitor the traffic in the Zscaler Admin Console
- Traffic not restored after Cloud Connector failure
- Cloud Connector experiencing connectivity disruptions

The following tables list provisioning and deployment issues and their corresponding troubleshooting actions.

## Provisioning Issues

| Potential Causes | Troubleshooting Actions |
| --- | --- |
| Deployed Cloud Connector is not displayed in the Zscaler Admin Console | Check whether user data is missing or is provided in an incorrect format. You can verify this by reviewing the Azure Key Vault entries, or by reviewing the User Data file located on the appliance here: /etc/cloud/cloud.cfg.d/userdata.cfgVerify the API Key (api-key).; Verify login credentials (username, password).; Verify that the provisioning URL is in the correct format and there are no spaces.; From the Zscaler Admin Console, verify that the Provisioning Template type is correct.; Check whether Cloud Connector has sufficient privileges to the Azure Key Vault. Ensure that the Get and List roles are assigned to that the User Managed Identity that the Cloud Connector is using.; Check whether Cloud Connector has sufficient privileges to enumerate network interfaces. Ensure that the Network Contributor role or Microsoft.Network/networkInterfaces/read permission is assigned to the User Managed Identity that the Cloud Connector is using. Also ensure that the Managed Identity has a sufficient scope that includes the Cloud Connector appliance.; Verify network security groups to ensure that Cloud Connectors can reach the Zscaler cloud.; Check whether the Cloud Connector could reach the package repository when using certificate authority. Verify that you can do a package update to confirm that the package repository is reachable and certificates are valid. Run the `sudo pkg update` command.; Verify DNS resolution. |
| Terraform script fails to create a VNet in a greenfield deployment. | Check whether the user has surpassed the VNet creation limit for that region. |

## Deployment Issues

| Potential Causes | Troubleshooting Actions |
| --- | --- |
| Cloud Connector is in the wrong geolocation in the Zscaler Admin Console. | Verify that the outbound NAT and routing take the appropriate egress path. In the Zscaler Admin Console, confirm the Public IP address.; If the Public IP address is correct, contact Zscaler Support. |
| Cloud Connector is in an inactive state. | Verify the following: The registration and policy fetch are correct. To check, contact Zscaler Support.; Cloud Connector instance is up and running within the Azure Management Console.; The Zscaler Admin Console can be reached from your VNet/subnet.; Azure Key Vault has the correct secret credentials in case of a secrets rotation.; The network is connected. Verify the route tables, NAT gateway, and DNS.; The network security group is not blocking the Cloud Connector TCP/UDP 443 traffic.You cannot validate these conditions from the Command Line Interface (CLI) because the CLI uses the management interface for the deployment operation. |
| In the Zscaler Admin Console, the Internet & SaaS (ZIA) gateway IP address is not populated. | From the Zscaler Admin Console, check the Internet & SaaS gateway configuration. If the gateway configuration is not set correctly, contact Zscaler Support. |

| Potential Causes | Troubleshooting Action |
| --- | --- |
| Account not created. | Contact Zscaler Support. |
| Cloud Connector SKUs not enabled. | Contact Zscaler Support. |
| Authentication failed. | Verify that the username and all credentials are valid. Confirm that the admin exists in the Zscaler Admin Console. |
| User could not access single sign-on (SSO) from the Zscaler Admin Console. | Contact Zscaler Support. |

The traffic from the deployed Cloud Connector might not be able to reach internet applications.

Check the following areas to isolate and identify what is preventing the traffic connection:

| **Potential Causes** | **Troubleshooting Action** |
| --- | --- |
| The firewall policy attached to the Cloud Connector. | Verify that the Internet & SaaS firewall policy is not blocking any outbound traffic. |
| The connectivity from the Cloud Connector to Internet & SaaS. | Verify the following: In the Zscaler Admin Console, the [Internet Access Gateway](https://help.zscaler.com/cloud-branch-connector/about-zia-gateways) is populated.; Session logs display the Cloud Connector self-traffic.; The Zscaler Admin Console displays your tunnel insights. |
| The ingress routing to the Cloud Connector. | Verify the following: The routing of the workload subnet.; The network security group on the workload subnet.; The security group for workloads. |
| The egress path from the Cloud Connector. | Verify the following: The gateway configuration.; The forwarding policy in the Zscaler Admin Console.; The network security group and other services on egress. |
| The status of the Cloud Connector. | Verify the following: The virtual machine (VM) status of the Cloud Connector.; The service status of the Cloud Connector. |
| Connectivity issues between Cloud Connectors within a group or cluster. | Verify the following: Within a given connector group or cluster, all Cloud Connectors communicate with each other via their service interface(s) to share information such as FQDN or wildcard FQDN synchronization.; No firewall or security groups are blocking network communications between Cloud Connectors within a given VPC/VNet and their availability zones. |

If your workloads are unable to access private applications, verify the following:

- The destination can be reached via Zscaler Client Connector.
- Cloud Connector can resolve Private Access (ZPA) destinations by sending a DNS request for a Private Access destination from a workload directly to the IP address of a Cloud Connector service interface. The response should be an IP address from the IP pool defined in the Cloud Connector DNS policy. If that fails, check Private Access access and forwarding policies.
- The workload can resolve Private Access destinations by sending a regular DNS request for a Private Access destination. If that fails, check the Azure or workload DNS settings.
- The IP addresses and ranges that Private Access uses are routed through the Cloud Connector service interfaces.
- The Cloud Connector DNS and forwarding logs are operational.
- The Private Access events are operational.
- If FQDN-based Private Access App Segments are in use, Cloud Connector is able to resolve Private Access destinations by sending a DNS request for a Private Access destination from a workload directly to the IP address of a Cloud Connector service interface.

If you are unable to monitor traffic in the Zscaler Admin Console, verify the following:

- The Cloud Connector location created in the Zscaler Admin Console is registered in the Zscaler Admin Console.
- In the Zscaler Admin Console, the tunnel logs are showing traffic to Internet & SaaS.
- When you filter traffic in the Zscaler Admin Console, you use the Internet & SaaS filter.
- On the Zscaler Admin Console's Traffic Forwarding Rules page, the [traffic forwarding method is Internet & SaaS](https://help.zscaler.com/cloud-branch-connector/configuring-traffic-forwarding-rule).

If you are unable to monitor traffic in the Zscaler Admin Console, verify the following:

- Cloud Connector is registered in the Zscaler Admin Console.
- In the Zscaler Admin Console, the forwarding filter is Private Access.
- In the Zscaler Admin Console, policy configuration is correct. Ensure that access policies and client forwarding policies are working.

If your traffic is not restored after Cloud Connector connection failure, verify the following:

- The Cloud Connector backup instance is in good health with the high-availability model.
- In the Azure Load Balancer, the Cloud Connector instance is healthy.

To avoid connectivity disruptions and/or unreachable destinations, IP forwarding must be enabled on the Cloud Connector service interface. This setting is enabled by default, but it could be inadvertently disabled manually or via automation.

To verify the IP forwarding setting:

1. Log in to the [Azure Portal](https://portal.azure.com).
2. Click **Network interfaces**.
3. Select the interface and then select **Settings** > **IP configurations**.
4. In the **IP Settings** section, select **Enable IP forwarding**. See image.

Occasionally, you might need to access the Cloud Connector appliance's command line interface (CLI) for troubleshooting or monitoring purposes.

Network operations, such as ping, use the management interface. The CLI only provides limited insights to the service interface and no options to directly test its connectivity.

You can access the CLI via the following methods:

- Bastion Host
- Management Interface Public IP Address
- Bastion Subnet

A bastion host is a lightweight VM instance installed within the same subnet as the Cloud Connector service interface. This option leverages Secure Socket Shell (SSH) as the connection protocol. Hence, you must have access to the SSH key pair used when instantiating the appliances. Configure SSH keys exclusively using Terraform during deployment.

To access the appliance management interface remotely:

1. Log in to the Microsoft Azure portal.
2. On the Azure dashboard, navigate to the **Search resources, service, and docs (G+/)** field. See image.
3. In the **Search resources, service, and docs (G+/)**field, enter `Public IP addresses`. Then, under **Services**, select **Public IP addresses**. The **Public IP addresses** page appears. See image.
4. On the **Public IP addresses** page, click **Create**. The **Create public IP address** page appears. See image.
5. On the **Create public IP address** page, on the **Basics** tab, under **Project details**, configure the following settings:
  - **Subscription**: Select your desired subscription.
  - **Resource group**: Select your desired resource group to apply policies to the public IP address.
6. Under **Instance details**, select a region for the public IP address.
7. Under **Configuration details**, configure the following settings: See image.
  - **Name**: Enter a name for the public IP address.
  - **IP Version**: Select **IPv4**.
  - **SKU**: Select **Standard** or **Basic**.
  - **Availability zone**: Select your desired availability zone.
  - **Tier**: Select **Regional** or **Global**.
  - **IP address assignment**: Select **Static**.
  - **Routing preference**: Select **Microsoft network** or **Internet**, depending on your preference.
  - **Idle timeout (minutes)**: The default idle timeout is set to 4 minutes.
  - **DNS name label**: (Optional) Choose a DNS name label.
8. Click **Next : Tags >**.
9. On the **Tags** tab, assign your desired name/value pairs, and then click **Review + create**.
10. On the **Review + create** tab, click **Create**. See image.
11. On the **Public IP addresses** page, navigate to the public IP address you created, and then click the name.
12. On your public IP address's **Overview** page, click **Associate**. The **Associate public IP address** window appears. See image.
13. In the **Associate public IP address** window, configure the following settings:
  1. **Resource type**: Select **Network interface**.
  2. **Network interface**: Select the network interface of your bastion host.
14. Click **OK** to confirm the association. See image.
15. In the **Search resources, service, and docs (G+/)**field, enter `Route tables`. Then, under **Services**, select **Route tables**. The **Route tables** page appears. See image. Before creating or configuring your route table, identify your [public IP Address](https://ip.zscaler.com). This is the address that your SSH management traffic comes from.
16. On the **Route tables** page, select the name of the route table associated with your bastion host.
17. On your route table's **Overview** page, in the left-side navigation, under **Settings**, select **Routes**. See image.
18. On the **Routes** page, click **Add**.
19. In the **Add route** window, configure the following settings:
  - **Route name**: Enter a name for your route.
  - **Destination type**: Select **IP Addresses**.
  - **Destination IP addresses/CIDR ranges**: Enter your public IP address.
  - **Next hop type**: Select **Internet**.
  - **Next hop address**: Leave as default.
20. Click **Add** to confirm the route. See image.
21. In the **Search resources, service, and docs (G+/)**field, enter `Network security groups`. Then, under **Services**, select **Network security groups**. The **Network security groups** page appears. See image.
22. On the **Network security groups** page, select the name of the network security group associated with your bastion host.
23. On your network security group's **Overview** page, in the left-side navigation, under **Settings**, select **Inbound security rules**.
24. On the **Inbound security rules** page, click **Add**.
25. In the **Add inbound security rule** window, configure the following settings:
  - **Source**: Select **IP Addresses**.
  - **Source IP addresses/CIDR ranges**: Enter your source IP addresses/CIDR ranges.
  - **Source port ranges**: Enter your source port ranges.
  - **Destination**: Select **IP Addresses**.
  - **Service**: Select **SSH**.
  - **Destination port ranges**: This field is automatically set to **22**.
  - **Protocol**: This field is automatically set to **TCP**.
  - **Action**: Select **Allow**.
  - **Priority**: Enter a number to designate the priority of this rule.
  - **Name**: Enter a name for this rule.
  - **Description**: (Optional) Enter a description of the rule.
26. Click **Add** to confirm the inbound security rule configuration. See image.
27. Using your preferred SSH client, SSH from the management host to the Cloud Connector. The administrative login for the appliance is `zsroot`. The administrative login for the bastion host varies depending on the operating system (OS) selected. For Linux terminal SSH clients using a Linux (Ubuntu) bastion host, the command is `ssh -i mySshKey.pem zsroot@10.0.50.106 -o "proxycommand ssh -W %h:%p -i mySshKey.pem ubuntu@100.64.0.1`.

Cloud Connector appliances have a dedicated management interface installed, by default, in the same subnet as the Service Interface. This option leverages SSH as the connection protocol. Hence, you must have access to the SSH key pair used when instantiating the appliances.

To access the appliance management interface remotely:

1. Log in to the Microsoft Azure portal.
2. On the Azure dashboard, navigate to the **Search resources, service, and docs (G+/)** field. See image.
3. In the **Search resources, service, and docs (G+/)**field, enter `Public IP addresses`. Then, under **Services**, select **Public IP addresses**. The **Public IP addresses** page appears. See image.
4. On the **Public IP addresses** page, click **Create**. The **Create public IP address** page appears. See image.
5. On the **Create public IP address** page, on the **Basics** tab, under **Project details**, configure the following settings:
  - **Subscription**: Select your desired subscription.
  - **Resource group**: Select your desired resource group to apply policies to the public IP address.
6. Under **Instance details**, select a region for the public IP address.
7. Under **Configuration details**, configure the following settings: See image.
  - **Name**: Enter a name for the public IP address.
  - **IP Version**: Select **IPv4**.
  - **SKU**: Select **Standard** or **Basic**.
  - **Availability zone**: Select your desired availability zone.
  - **Tier**: Select **Regional** or **Global**.
  - **IP address assignment**: Select **Static**.
  - **Routing preference**: Select **Microsoft network** or **Internet**, depending on your preference.
  - **Idle timeout (minutes)**: The default idle timeout is set to 4 minutes.
  - **DNS name label**: Choose a DNS name label.
8. Click **Next : Tags >**.
9. On the **Tags** tab, assign your desired name/value pairs, and then click **Review + create**.
10. On the **Review + create** tab, click **Create**. See image.
11. On the **Public IP addresses** page, navigate to the public IP address you created, and then click the name.
12. On your public IP address's **Overview** page, click **Associate**. The **Associate public IP address** window appears. See image.
13. In the **Associate public IP address** window, configure the following settings:
  - **Resource type**: Select **Network interface**.
  - **Network interface**: Select the network interface of your management interface.
14. Click **OK** to confirm the association. See image.
15. In the **Search resources, service, and docs (G+/)**field, enter `Route tables`. Then, under **Services**, select **Route tables**. The **Route tables** page appears. See image. Before creating or configuring your route table, identify your [public IP address](https://ip.zscaler.com). This is the address that your SSH management traffic comes from.
16. On the **Route tables** page, select the name of the route table associated with your management interface.
17. On your route table's **Overview** page, in the left-side navigation, under **Settings**, select **Routes**. See image.
18. On the **Routes** page, click **Add**.
19. In the **Add route** window, configure the following settings:
  - **Route name**: Enter a name for your route.
  - **Destination type**: Select **IP Addresses**.
  - **Destination IP addresses/CIDR ranges**: Enter your public IP address.
  - **Next hop type**: Select **Internet**.
  - **Next hop address**: Leave as default.
20. Click **Add** to confirm the route. See image.
21. In the **Search resources, service, and docs (G+/)**field, enter `Network security groups`. Then, under **Services**, select **Network security groups (classic)**. The **Network security groups** page appears. See image.
22. On the **Network security groups** page, select the name of the network security group associated with your management interface.
23. On your network security group's **Overview** page, in the left-side navigation, under **Settings**, select **Inbound security rules**.
24. On the **Inbound security rules** page, click **Add**.
25. In the **Add inbound security rule** window, configure the following settings:
  - **Source**: Select **IP Addresses**.
  - **Source IP addresses/CIDR ranges**: Enter your source IP addresses/CIDR ranges.
  - **Source port ranges**: Enter your source port ranges.
  - **Destination**: Select **IP Addresses**.
  - **Service**: Select **SSH**.
  - **Destination port ranges**: This field is automatically set to **22**.
  - **Protocol**: This field is automatically set to **TCP**.
  - **Action**: Select **Allow**.
  - **Priority**: Enter a number to designate the priority of this rule.
  - **Name**: Enter a name for this rule.
  - **Description**: (Optional) Enter a description of the rule.
26. Click **Add** to confirm the inbound security rule configuration. See image.
27. Using your preferred SSH client, SSH from the management host to the Cloud Connector. The administrative login for the appliance is `zsroot`. For Linux terminal SSH clients, the command is `ssh -i mySshKey.pem zsroot@100.64.0.1`.

Microsoft Azure offers a feature known as a bastion subnet. This feature allocates a small percentage of available IP addresses in a subnet to the bastion service and provides an entry point into your cloud network for management purposes.

To access the CLI via the bastion subnet:

1. Log in to the Microsoft Azure Portal.
2. On the Azure dashboard, navigate to the **Search resources, service, and docs (G+/)** field. See image.
3. In the **Search resources, service, and docs (G+/)**field, enter `Virtual machines`. Then, under **Services**, select **Virtual machines**. The **Virtual machines** page appears. See image.
4. On the **Virtual machines** page, select your desired Cloud Connector appliance.
5. On the Cloud Connector appliance **Overview** page, click **Connect**. See image.
6. Select **Bastion**, then click **Use Bastion**. See image. Ensure that the service is created within the VNet and Management subnet of your appliance so the appropriate connectivity exists.
7. Under **Create Bastion**, click **Deploy Bastion**. See image.
8. After your bastion has deployed, return to your Cloud Connector appliance **Overview** page.
9. On the **Overview** page, under **Operations**, select **Bastion**. See image.
10. In the bastion window, configure the following settings:
  - **Username**: Enter `zsroot`.
  - **Authentication Type**: Select **SSH Private Key from Local File**.
  - **Local File**: Navigate to and select your SSH Key.
11. Click **Connect**. See image.

[Image: Navigating to the Search field in the Microsoft Azure Portal]

[Image: Navigating to the Public IP addresses page in the Microsoft Azure Portal]

[Image: Creating a public IP address in the Microsoft Azure Portal]

[Image: Adding the public IP address details in Microsoft Azure]

[Image: Reviewing and creating a public IP address in Microsoft Azure]

[Image: Clicking the Associate button for your public IP address in Microsoft Azure]

[Image: Associating the public IP address in Microsoft Azure]

[Image: Navigating to the Route tables page in the Microsoft Azure Portal]

[Image: Selecting Routes on the Overview page of your route table in Microsoft Azure]

[Image: Confirming changes in the Add route window of Microsoft Azure]

[Image: Navigating to the Network security groups page in Microsoft Azure]

[Image: Adding and confirming changes in the Add inbound security rule window of Microsoft Azure]

[Image: Navigating to the Search field in Microsoft Azure]

[Image: Navigating to the Public IP addresses page in Microsoft Azure.]

[Image: Clicking the Create button on the Public IP addresses page in Microsoft Azure.]

[Image: The Create public IP address page in Microsoft Azure.]

[Image: Reviewing and creating a public IP address in Microsoft Azure.]

[Image: Clicking the Associate button for your public IP address in Microsoft Azure.]

[Image: Associating the public IP address in Microsoft Azure.]

[Image: Navigating to the Route tables page in Microsoft Azure.]

[Image: Selecting Routes on the Overview page of your route table in Microsoft Azure.]

[Image: Confirming changes and adding a route in the Route window of Microsoft Azure.]

[Image: Navigating to the Network security groups page in Microsoft Azure.]

[Image: Configuring settings to add an inbound security rule in Microsoft Azure.]

[Image: Navigating to the Search field in Microsoft Azure.]

[Image: Navigating to the Virtual machines page in Microsoft Azure.]

[Image: Clicking Connect on the Overview page of the Cloud Connector appliance in Microsoft Azure.]

[Image: Clicking Use Bastion under Bastion in Microsoft Azure.]

[Image: Clicking Deploy Bastion under Create Bastion in Microsoft Azure.]

[Image: Selecting Bastion under Operations in Microsoft Azure.]

[Image: Configuring your Bastion settings and clicking Connect in Microsoft Azure.]

[Image: Selecting the Enable IP forwarding checkbox in the IP Settings section of the Azure Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/troubleshooting-zscaler-client-connector-vdi","lastmod":"2026-11-01T23:53Z","nid":"1494866"} -->
## Troubleshooting Zscaler Client Connector for VDI

- Source: https://help.zscaler.com/zero-trust-cloud/troubleshooting-zscaler-client-connector-vdi
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Zscaler Client Connector for VDI Management > Troubleshooting Zscaler Client Connector for VDI
- Last modified: 2026-11-01T23:53Z
- Summary: Information on various issue that may occur in Zscaler Client Connector for VDI and their solutions.

This article provides troubleshooting information and guidelines for Zscaler Client Connector for Virtual Desktop Infrastructure (VDI) deployment. You can review issues and corresponding solutions for Zscaler Client Connector for VDI. If you cannot reach a solution, contact Zscaler Support. Additionally, ensure that you bypass services you want to keep local to Microsoft Azure or Citrix. To learn more about bypasses, see [Configuring VDI Forwarding Profiles](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-forwarding-profiles).

- Registration
- Authentication
- Data Path

The following registration troubleshooting flow chart leads you through various Zscaler Client Connector for VDI situations and potential solutions.

See image.

The following table lists Zscaler Client Connector for VDI registration issues and their corresponding troubleshooting actions.

| **Potential Causes** | **Troubleshooting Action** |
| --- | --- |
| Zscaler Client Connector for VDI is experiencing location fetch errors. | Location fetch errors can occur for multiple reasons. Collect Zscaler Client Connector for VDI's logs and contact Zscaler Support. |
| The Zscaler Cloud & Branch Connector service interface is unreachable or Cloud & Branch Connector is displayed inactive. | Test whether the Cloud & Branch Connector service interface is reachable with and without Zscaler Client Connector for VDI. To learn more, see the [Cloud Connector troubleshooting documentation](https://help.zscaler.com/cloud-branch-connector/troubleshooting). |
| The Zscaler Client Connector for VDI onboard parameter is not set. | Ensure that the onboarding behavior is set to either `1` or `0`. The default is `1`, which allows Zscaler Client Connector for VDI to onboard itself to the Workload Communications (WLC) tenant. Setting the behavior to `0` disables this automatic behavior. To learn more, see [Customizing Zscaler Client Connector for VDI with Install Options for MSI](https://help.zscaler.com/cloud-branch-connector/customizing-zscaler-client-connector-vdi-install-options-msi). |
| Routing is not configured for `185.46.212.80`. | By default, Zscaler Client Connector for VDI connects to `185.46.212.80`. Ensure that a route is configured for this destination pointing toward the Cloud Connector, Branch Connector, or a load balancer. |

The following authentication troubleshooting flow chart guides you through various Zscaler Client Connector for VDI situations and potential solutions.

See image.

The following table lists Zscaler Client Connector for VDI authentication issues and their corresponding troubleshooting actions.

| **Potential Causes** | **Troubleshooting Action** |
| --- | --- |
| User authentication is failing. | User authentication can fail for multiple reasons: When the VDI environment completes a backup and restoration of the roaming profile, Zscaler Client Connector for VDI stores and reads the user configuraton file in the roaming profile. This restoration process can take time. Configure the `CONFIGREADTIMEOUT` parameter so that Zscaler Client Connector for VDI waits a minimum of 0 and a maximum of 300 seconds for profiles to restore.; If the virtual machine (VM) loads more slowly than Zscaler Client Connector for VDI, a timeout issue might occur. Configure the timeout parameter to wait for the window to load completely before showing the authentication page. To learn more, see [Customizing Zscaler Client Connector for VDI with Install Options for MSI](https://help.zscaler.com/cloud-branch-connector/customizing-zscaler-client-connector-vdi-install-options-msi). |
| Integrated Windows Authentication (IWA) is failing. | IWA relies on browser and/or Internet settings, so review your configured settings and refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/entra/identity/hybrid/connect/how-to-connect-sso-quick-start). |
| Okta ADSSO authentication is failing. | Refer to the [Okta documentation](https://help.okta.com/en-us/content/topics/directory/configuring_agentless_sso.htm). |
| Profile backup and restore is not working. | Ensure that the backup and restore IP addresses are bypassed. |
| FSLogix is not able to restore the user profile. | Ensure that all FSLogix IP addresses are bypassed. |
| Zscaler Client Connector for VDI is not able to reach the identity provider (IdP). | Ensure that there is a bypass from the Zscaler Admin Console and FQDN bypasses from the Cloud Connector. |
| Zscaler Client Connector for VDI is not able to reach Zscaler authentication domains. | Ensure that all Zscaler authentication domains are bypassed. |
| You do not see the correct user when navigating to `ip.zscaler.com`. | Ensure that all bypasses and authentications are functioning. Collect the packet captures and ensure that traffic is tunneled properly. |

- Internet & SaaS
- Private Access

The following Internet & SaaS data path troubleshooting flow chart guides you through various Zscaler Client Connector for VDI situations and potential solutions.

See image.

To learn more about routing traffic through `185.46.212.80`, see [What Is Zscaler Client Connector for VDI?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-client-connector-vdi)

The following table lists Zscaler Client Connector for VDI Internet & SaaS data path issues and their corresponding troubleshooting actions.

| **Potential Causes** | **Troubleshooting Action** |
| --- | --- |
| The incorrect tunnel mode is configured for Zscaler Client Connector for VDI. | You can configure Point-to-Point (P2P) or Any-to-Any (A2A) tunneling mode for Zscaler Client Connector for VDI. To determine which tunneling mode is appropriate, see [What Is Zscaler Client Connector for VDI?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-client-connector-vdi) A2A tunneling mode is not recommended and is being deprecated. |
| Traffic is not reaching Internet & SaaS. | Review the Cloud Connector session insights to verify whether the traffic is forwarded to Internet & SaaS. To learn more, see the [Cloud Connector troubleshooting documentation](https://help.zscaler.com/cloud-branch-connector/troubleshooting). |
| Traffic is not reaching Cloud Connector or Branch Connector. | Ensure that workload routes are properly configured to send the traffic to Cloud Connector and the traffic forwarding rule is configured to send traffic to Internet & SaaS. |
| The incorrect policy is applied in Internet & SaaS. | In the Zscaler Admin Console, check the Internet & SaaS insights logs and match them with the rule preceding the correct rule. |

The following Private Access data path troubleshooting flow chart guides you through various Zscaler Client Connector for VDI situations and potential solutions.

See image.

To learn more about P2P and A2A tunneling modes, see [What Is Zscaler Client Connector for VDI?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-client-connector-vdi)

The following table lists Zscaler Client Connector for VDI Private Access data path issues and their corresponding troubleshooting actions.

| **Potential Causes** | **Troubleshooting Action** |
| --- | --- |
| Server Message Block (SMB), Kerberos, Windows network, DNS, ICMP, or network file system (NFS) traffic are not able to reach the destination application. | SMB, Kerberos, Windows network, DNS, ICMP, and NFS traffic are treated as system user traffic by Zscaler Client Connector for VDI. Ensure that Private Access has a correct access policy configured for the system user. This is a Windows limitation, not a Zscaler limitation. |
| Traffic is not reaching Cloud Connector or Branch Connector. | Ensure that workload routes are properly configured to send the traffic to Cloud Connector and the traffic forwarding rule is configured to send traffic to Private Access. |
| The incorrect access policy is applied in Private Access. | Ensure that the access policy is configured with the client type as Zscaler Client Connector for VDI. If the access policy contains user attributes, enable System for Cross-domain Identity Management (SCIM). SAML is not supported. |
| The incorrect application segment is selected in Private Access. | Check the application segment configuration in Private Access to verify that it is correct. |
| Synthetic IP pool is not included in the VDI forwarding profile. | Ensure that the VDI forwarding policy includes synthetic IP pools in the Include list. |
| DNS requests are not being received. | Ensure that Cloud Connector is seeing DNS requests and able to match them to the Private Access application or FQDN. To learn more, see [Step-by-Step Configuration Guide for Zscaler Client Connector for VDI](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi). |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/tunnel-data-types-filters","lastmod":"2026-11-01T23:52Z","nid":"1420661"} -->
## Tunnel Data Types and Filters

- Source: https://help.zscaler.com/zero-trust-cloud/tunnel-data-types-filters
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Analytics & Monitoring > Insights > Tunnel Data Types and Filters
- Last modified: 2026-11-01T23:52Z
- Summary: Information on tunnel, location, and VPN credential data types to define traffic information in a dashboard or when analyzing charts in Tunnel Insights.

You can use tunnel data types and filters to define the tunnel traffic information that you want to view when analyzing charts on an Insights page. To learn more about how to analyze your traffic insights, see [Analyzing Traffic Using Insights](https://help.zscaler.com/cloud-branch-connector/analyzing-traffic-using-insights).

From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Data Explorer** > **Zero Trust Cloud**> **Tunnel Insights** and select a data type to view from the menu above the chart and apply filters that you choose from the **Add Filter** menu in the left-side navigation.
See image.

[Image: Tunnel Insights filters page in the Zscaler Admin Console]

The **Location** filter lists 200 results at a time. It provides search fields where you can enter characters and narrow the results to match your input.

## Data Types and Filters

The following are the tunnel data types and their filters:

- **Location**: Use this filter to view metrics associated with a specific location. The default option for this filter is **All**. You can search for specific locations.
- **Tunnel Destination IP**: Use this filter to view metrics associated with a destination IP address. If you want to filter by multiple IP addresses, you can enter them individually, separated by commas (e.g., 192.0.2.0, 192.0.2.1).
- **Tunnel Source IP**: Use this filter to view metrics associated with a source IP address. If you want to filter by multiple IP addresses, you can enter them individually, separated by commas (e.g., 192.0.2.0, 192.0.2.1).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/tunnel-insights-logs-columns","lastmod":"2026-11-01T23:52Z","nid":"1420681"} -->
## Tunnel Insights Logs: Columns

- Source: https://help.zscaler.com/zero-trust-cloud/tunnel-insights-logs-columns
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Analytics & Monitoring > Insights > Logs > Tunnel Insights Logs: Columns
- Last modified: 2026-11-01T23:52Z
- Summary: Information on the different columns in the Tunnel Insights Logs page in the Zscaler Zscaler Admin Console.

You can customize your tunnel logs using the columns. To learn more about logs, see [About Insights Logs](https://help.zscaler.com/cloud-branch-connector/about-insights-logs).

From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Data Explorer** > **Zero Trust Cloud** > **Tunnel Insights**. Choose the **Logs**tab, located in the top left corner of the page. You can view the following Tunnel Logs columns:

- **Authentication Algorithm**: The authentication algorithm used for IPSec Phase 1 and Phase 2.
- **Authentication Type**: The method IPSec peers use to authenticate each other.
- **Bytes Received**: The total bytes of data Zscaler received through the tunnel from the organization in the one-minute sample interval.
- **Bytes Sent**: The total bytes of data Zscaler sent through the tunnel to the organization in the one-minute sample interval.
- **Cipher**: The ciphers used for the control and data channel of the tunnel.
- **Cipher Protocol**:The protocol that is used by the tunnel (i.e., TLS or DTLS).
- **Cloud Connector Instance**: The specific Zscaler software instance from which you want to view the DNS logs.
- **Data Center**: The data center associated with the tunnel.
- **Encryption Algorithm**: The encryption algorithm used to encrypt handshakes (IPSec Phase 1) and data (IPSec Phase 2).
- **Event Reason**: The reason that the status change occurred for a tunnel.
- **Event Time**: The date and time the event occurred.
- **IKE Phase 2 SPI**: The Security Parameter Index (SPI) for the IKE SA established in Phase 2.
- **Initiator Cookie**: The initiator's cookie value used during the IPSec IKE Phase 1 message exchange.
- **IPSec Protocol**: Protocols used in establishing the IPSec VPN tunnel.
- **Keep Alive Packets**: The Keep-Alive packet ensures that the SSL VPN connection remains open even when the device is inactive.
- **Life Bytes**: The total number of life bytes that is transmitted over an IPSec SA before it expires.
- **Location**: The location associated with the tunnel.
- **Log Time**: The date and time the event was logged.
- **Log Type**: The type of tunnel logs.
- **No.**: The item number.
- **P2 Policy Dest IP - End**: The end destination IP address specified in the IPSec Phase 2 policy proposal.
- **P2 Policy Dest IP - Start**: The start destination IP address specified in the IPSec Phase 2 policy proposal.
- **P2 Policy Dest Port - End**: The end destination port number specified in the IPSec Phase 2 policy proposal.
- **P2 Policy Protocol**: The protocol specified in the IPSec Phase 2 policy proposal.
- **P2 Policy Src IP - End**: The end source IP address specified in the IPSec Phase 2 policy proposal.
- **P2 Policy Src IP - Start**: The start source IP address specified in the IPSec Phase 2 policy proposal.
- **P2 Policy Src Port - Star**t: The start source port number specified in the IPSec Phase 2 policy proposal.
- **Packets Received**: The total number of packets Zscaler received through the tunnel from the organization in the one-minute sample interval.
- **Packets Sent**: The total number of packets Zscaler sent through the tunnel to the organization in the one-minute sample interval.
- **Policy Direction**: The direction in which the IPSec Phase 2 policies (allowed subnets/ports/protocols) are proposed. The following directions appear under this column:
  - Inbound: From the organization's IPSec gateway to Zscaler.
  - Outbound: From Zscaler to the organization's IPSec gateway.
- **Responder Cookie**: The responder's cookie value used during the IPSec IKE Phase 1 message exchange.
- **Session ID**: A cryptographically generated random number that is unique per tunnel.
- **Source Port**: The port number of the tunnel from which the tunnel is initiated.
- **Tunnel Destination IP**: The destination IP address that the tunnel is destined to.
- **Tunnel Lifetime**: The time after an IKE expires for the IPSec VPN tunnel.
- **Tunnel Source IP**: The source IP address from which the tunnel is initiated.
- **Tunnel Status**: The status of the tunnel.
- **Tunnel Type**: The type of networking tunnel.
- **Vendor ID**: The vendor associated with the tunnel.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/tunnel-insights-logs-filters","lastmod":"2026-11-01T23:52Z","nid":"1420686"} -->
## Tunnel Insights Logs: Filters

- Source: https://help.zscaler.com/zero-trust-cloud/tunnel-insights-logs-filters
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Analytics & Monitoring > Insights > Logs > Tunnel Insights Logs: Filters
- Last modified: 2026-11-01T23:52Z
- Summary: Information on the different filters in the Tunnel Insights logs page in the Zscaler Cloud & Branch Connector Admin Portal.

Filters define the traffic information that you view in your Tunnel Insights logs. To learn more about logs, see [About Insights Logs](https://help.zscaler.com/cloud-branch-connector/about-insights-logs).

From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Data Explorer** > **Zero Trust Cloud** > **Tunnel Insights**. Choose the **Logs**tab, located in the top left corner of the page. You can select the following Tunnel Insights Logs filters:

- **Data Center**: Use this filter to view metrics associated with a specific data center.
- **Location**: Use this filter to view metrics associated with a specific location. The default option for this filter is All. You can search for specific locations.
- **Tunnel Destination IP**: Use this filter to view metrics associated with a destination IP address. If you want to filter by multiple IP addresses, you can enter:
  - An individual IP addresses separated by commas (e.g., `192.0.2.0, 192.0.2.1`).
  - A range of IP addresses (e.g., `192.0.2.0-192.0.2.100`).
  - A subnet mask (e.g., `192.0.2.0/24`).
- **Tunnel Source IP**: Use this filter to view metrics associated with a source IP address. If you want to filter by multiple IP addresses, you can enter:
  - An individual IP addresses separated by commas (e.g., `192.0.2.0, 192.0.2.1`).
  - A range of IP addresses (e.g., `192.0.2.0-192.0.2.100`).
  - A subnet mask (e.g., `192.0.2.0/24`).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/understanding-cloud-connector-deployments-amazon-web-services-auto-scaling-groups","lastmod":"2026-11-01T23:53Z","nid":"1529197"} -->
## Understanding Cloud Connector Deployments with Amazon Web Services Autoscaling Groups

- Source: https://help.zscaler.com/zero-trust-cloud/understanding-cloud-connector-deployments-amazon-web-services-auto-scaling-groups
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Deployment Management for Virtual Devices > Cloud Connector Deployment Management > Cloud Connector Deployment Management for AWS > Understanding Cloud Connector Deployments with Amazon Web Services Autoscaling Groups
- Last modified: 2026-11-01T23:53Z
- Summary: Information about Autoscaling Groups with Zscaler Cloud Connector.

An AWS Elastic Compute Cloud (EC2) Autoscaling deployment dynamically adds Cloud Connector virtual machines (VMs), which are EC2 instances, to an Autoscaling group to meet the current load, and removes Cloud Connector VMs from the group when the load decreases. You define the desired capacity and the minimum and maximum number of VMs in the group.

Autoscaling also constantly monitors the health of each VM in the group. It removes unhealthy VMs from the group and replaces them with healthy ones. If someone manually terminates a VM that is part of an autoscaling group from the Amazon EC2 Console, the VM is likewise replaced.

When deploying a Cloud Connector, only deploy an autoscaling group (ASG) with an ASG template or a non-ASG with a non-ASG template. Additionally, stopping or rebooting a VM that is part of an ASG from the Amazon E2 Console could cause the VM to be terminated.

AWS autoscaling provides the following benefits:

- Dynamically scales the number of VMs in the autoscaling group to match demand.
- Automatically removes unhealthy VMs and replaces them with healthy ones.
- Deploys VMs across availability zones for high availability. A Gateway Load Balancer (GWLB) distributes traffic among the VMs.

This article describes AWS autoscaling and how it works in a Cloud Connector deployment. The deployment template prompts you to configure certain autoscaling settings mentioned in this article. After deployment, you can modify the settings in the [Amazon EC2 Console](https://console.aws.amazon.com/ec2/). For information about the deployment template and the deployment steps, see [Deployment Templates for Zscaler Cloud Connector](https://help.zscaler.com/cloud-branch-connector/deployment-templates-zscaler-cloud-connector) and [Deploying Zscaler Cloud Connector with Amazon Web Services](https://help.zscaler.com/cloud-branch-connector/deploying-zscaler-cloud-connector-amazon-web-services). For comprehensive autoscaling information, refer to the [AWS product documentation](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/concepts.html).

## Topology

The following sections provide a diagram depicting the topology of an autoscale deployment and a description of its components and flow.

- Topology diagram
- Topology details

[Image: Diagram showing a Cloud Connector Auto Scaling deployment in AWS]

- The workload servers and the autoscaling group are deployed in separate subnets in the VPC. They are deployed into a single regional availability zone to simplify the management of resources. Alternatively, you can deploy multiple autoscaling groups in multiple availability zones to provide additional reliability and redundancy.
- A GWLB is deployed in front of the Cloud Connectors. It is the entry point for traffic from the endpoints to the security stack. After the security stack is deployed, in the route tables in the workload subnets, you define a route that steers traffic to the GWLB. To learn more, see [Deploying Zscaler Cloud Connector with Amazon Web Services](https://help.zscaler.com/cloud-branch-connector/deploying-zscaler-cloud-connector-amazon-web-services).
- A NAT gateway used for outbound traffic from the Cloud Connectors is deployed in a public subnet in the availability zone. The NAT gateway has a dedicated IP address associated with it.
- The following services run in the AWS namespace associated with your account. Zscaler Lambda; CloudWatch; EventBridge
- After a termination event, Lambda initiates a REST API call to the Zscaler Admin Console so the terminated VM can be removed from the Cloud Connector group.

The Zscaler Lambda function:

- Evaluates custom health metrics published by each VM. If the VM does not publish health metrics, Lambda treats missing data points as unhealthy by default. If you want Lambda to ignore missing data points, change the value of the `MISSING_DATAPOINTS_UNHEALTHY` environment variable in the Lambda console (Functions > <Auto Scaling group function> > Configuration > Environment variables).
- Cleans up the Zscaler cloud resources associated with the VM when the VM is terminated. This includes resources allocated to Internet & SaaS (ZIA) and Private Access (ZPA).
- Ensures smooth termination of autoscaling lifecycle events after cleaning up the cloud resources.
- Manages the termination of VMs in a warm pool.

The following conditions trigger Lambda via AWS EventBridge:

- **Periodic execution**: Triggered every minute to check VM health.
- **EC2 instance termination events**: Triggered asynchronously after a VM termination event completes.
- **EC2 lifecycle termination events**: Triggered asynchronously after a lifecycle termination event completes.

After any of these events triggers the Zscaler Lamba function, it runs for a maximum of three minutes and then terminates automatically.

Amazon CloudWatch monitors your AWS resources and security stack in real time. CloudWatch collects and stores logs, displays data on the CloudWatch dashboard, and triggers alarms based on specific metrics identified in the logs.

For additional information about CloudWatch, refer to the [AWS product documentation](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/concepts.html).

- Metrics
- Alarms
- Logs

A metric is a time-ordered set of data points that is published to CloudWatch. The metric is the entity being monitored, and the data points are evaluation periods during which metric values change over time. Amazon EC2 autoscaling metrics that collect information about autoscaling groups are in the `AWS/AutoScaling` namespace. Amazon EC2 instance metrics that collect CPU and other usage data from VMs in autoscaling groups are in the `Zscaler/Cloud Connectors` namespace.

CloudWatch enables you to create the following types of alarms:

- **Metric Alarm**: Monitors a single CloudWatch metric or the result of a math expression based on CloudWatch metrics. The alarm performs one or more actions based on a metric and target value during a number of data points. By default, the number of data points is 10, so if the metric value reaches the threshold for 10 consecutive minutes, the alarm is triggered. An alarm's possible actions include performing an Amazon EC2 autoscaling action, performing an Amazon EC2 action, and sending a notification to EventBridge. The Target Tracking Policy automatically configures the CloudWatch alarms for scale-out and scale-in events. A high alarm triggers a scale-out event, and a low alarm triggers a scale-in event.
- **Composite Alarm**: Includes a rule expression that considers the status of other alarms you created. The composite alarm goes into an Alarm state if all the rule conditions are met. The rule expression can include metric alarms or other composite alarms.

The AWS CloudWatch Logs service allows you to monitor, store, and access logs from Amazon EC2 instances and other systems, applications, and AWS services in a centralized way. The CloudWatch Logs service presents all your logs from any source as a single and consistent flow of events sorted by date and time. This makes it easy to view logs, search logs for specific error codes or patterns, filter logs based on specific fields, and archive logs securely for future analysis.

You can also query your logs with a powerful query language, audit and mask sensitive information in logs, and use filters or an embedded log format to generate metrics from logs. For more information, refer to the [AWS product documentation](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/concepts.html).

This EC2 service generates events based on state changes, such as the termination of a VM. Amazon EventBridge listens for the events and routes them from sources to targets based on predefined rules. For example, the EC2 Lifecycle Termination rule sends a termination event to the Lambda function that handles lifecycle termination.

## Scale-Out and Scale-In

Each Cloud Connector independently reports custom CPU utilization metrics to Amazon CloudWatch at one-minute intervals to advertise the load it is handling. AWS autoscaling uses the aggregate CPU utilization of all VMs in the autoscaling group to determine when to trigger scale-out and scale-in events and how aggressively to do so.

Autoscaling uses custom CPU metrics instead of VM-level metrics because custom metrics provide more detailed and precise information about CPU usage.

- Scaling Policy
- Cooldown Period and Tolerance
- Capacity and Sizing
- Examples

A Target Tracking Scaling Policy, which is a type of Dynamic Scaling Policy, manages scale-out and scale-in events. The policy defines when to increase or decrease the *desired capacity* of the group based on the aggregate custom CPU utilization metric for all VMs in the group and a target value defined in the policy. The policy defines what action to take when an Alarm-level CloudWatch alarm is triggered. You can view the policy on the details page for the autoscaling group in the EC2 Portal.

The Target Tracking Policy has a default target value of 80%. The policy automatically configures the CloudWatch alarms for scale-out and scale-in events:

- **Scale-Out**: A scale-out event is triggered automatically if the aggregate CPU utilization is more than 80% for 180 seconds (three minutes).
- **Scale-In**: A scale-in event is triggered automatically if the aggregate CPU utilization is less than 80% for 900 seconds (15 minutes).

In logs and reports, the CPU utilization metric is displayed as `smedge_cpu_utilization`.

Target Tracking provides the following safeguards:

- **Cooldown Period:** When a scale-out or scale-in event occurs, autoscaling automatically applies a cooldown period, during which it ignores new metric changes to give the system time to stabilize. By default, the cooldown period is 300 seconds (5 minutes).
- **Tolerance Level**: Autoscaling adds *error bands* around the target level. This margin prevents small fluctuations from triggering scale-out or scale-in actions. For example, with the 80% target level, autoscaling does not act immediately when the metric reaches 80.1% or 79.9%.

During deployment, you configure the maximum size, minimum size, and desired capacity for an autoscaling group. When a scaling event happens, autoscaling automatically determines the number of VMs to add or remove to bring the metric closer to the target value. The autoscaling group adjusts the desired capacity based on that target level.

The desired capacity cannot exceed the maximum size or fall below the minimum size defined for the group. For example, suppose the desired capacity is two VMs, the minimum size is one, and the maximum size is 10. Eight scale-out events bring the desired capacity to the maximum size of 10 VMs. Scale-out actions stop. If another spike occurs, traffic responses could be delayed or random traffic could be dropped.

The following examples show how an autoscaling group with a Target Tracking Policy can change the desired capacity of an autoscaling group:

- **Initial State (3 VMs)**
  1. The VMs report CPU utilizations of 80%, 90%, and 85%, so the aggregate CPU utilization is 85%.
  2. Because the aggregate is slightly above the 80% target, the system waits through the cooldown period to see whether the condition persists.
  3. The condition persists for a few minutes after the cooldown period ends, so a scale-out action is triggered, increasing the desired capacity to 4 VMs.
- **State After Scale-Out (4 VMs)**
  1. The VMs report CPU utilizations of 50%, 40%, 25%, and 35%, so the aggregate CPU utilization is 37.5%.
  2. The aggregate is well below the 80% target, but autoscaling does not immediately scale in. Instead, it waits through the cooldown period and checks whether the low utilization remains consistent and significantly below target.
  3. If the low utilization is confirmed, a scale-in event reduces the desired capacity back to 3 VMs.
- **Stable State (2 VMs)**
  1. The VMs report CPU utilizations of 65% and 55%, so the aggregate CPU utilization is 60%.
  2. Although the aggregate is below the 80% target, it is close enough that autoscaling considers the system stable.
  3. No scale-out or scale-in action is triggered, because this level of utilization efficiently uses the current capacity.

## Health Monitoring

Autoscaling uses health checks to determine the status of a VM in the InService state.

- Health Checks
- Grace Period

- Amazon EC2 Status Checks evaluate whether the VM is running and look for underlying hardware or software issues that might compromise the VM. Autoscaling performs these status checks at one-minute intervals. If all checks pass, the overall status of the VM is **OK**. If one or more checks fail, the overall status is **Impaired**.
- Custom Health Checks expand the scope of health monitoring.Each Cloud Connector posts an individual custom health metric at one-minute intervals to the `Zscaler/Cloud Connectors` custom namespace in CloudWatch. The custom metric value is 0 for an unhealthy VM or 100 for a healthy VM. The Lambda function evaluates recent health data using sliding window logic with the following configurable environment variables: If either the 5- or 7-minute threshold is crossed, the VM is determined to be unhealthy.
  - The size of the sliding window. The default value is 10 consecutive minutes.
  - The amount of time the VM was in an unhealthy state. The default value for defining a VM as unhealthy is 5 consecutive minutes.
  - The tolerance for flapping values in the sliding window. The default value is 7 unhealthy counts in 10 consecutive minutes.

In logs and reports, the custom health metric is displayed as `cloud_connector_aggr_health`.

A new Cloud Connector has a specific amount of time after it is in service before AWS autoscaling checks its health status. This gives the VM time to boot up and become healthy before autoscaling can determine it to be unhealthy and terminate it. This amount of time is called a grace period. By default, the grace period is 900 seconds (15 minutes). If the VM is healthy after the grace period ends, it remains in service. Otherwise, autoscaling makes an API call to remove the VM from the Zscaler Admin Console and it is then terminated.

## Lifecycle Management

- Warm Pool
- Lifecycle Hooks

A warm pool contains pre-initialized VMs that are ready to start handling traffic. This reduces latency caused by situations such as long boot times. When a scale-out event happens, the autoscaling group draws from its warm pool to meet the new desired capacity. VMs that leave the warm pool count toward the desired capacity of the group. This process is called a warm start.

The VMs in the warm pool are in either the **Stopped**or **Running**state, based on your selection during deployment. Keeping the VMs in a **Stopped**state saves on cost, because you only pay for VMs that are running. In the Zscaler Admin Console, stopped VMs are shown as inactive (red).

- Warm Pool Sizing Options
- Warm Pool Reuse Policy

The following warm pool sizing options are available:

- By default, the size of a warm pool is dynamic, being calculated as the difference between the autoscaling group's maximum capacity and its desired capacity. For example, if the desired capacity of your autoscaling group is 5 and the maximum capacity is 8, the initial size of the warm pool is 3.
- During deployment, you can use the `MaxGroupPreparedCapacity` option to specify a custom value that is greater than the current capacity for the group. In this case, the warm pool size is the difference between the custom value and the desired capacity of the group. For example, if the desired capacity of your autoscaling group is 6, the maximum capacity is 18, and the custom value is 8, the size of the warm pool is 2. This option is useful for managing the cost-effectiveness of warm pools for large autoscaling groups.
- To set the warm pool size to a static value, set a warm pool minimum size during deployment. This ensures that a certain number of warmed VMs are always available to handle traffic. No minimum size is set by default.

By default, when a scale-in event happens, AWS autoscaling terminates a VM and then launches a new VM into the warm pool to replace it. You can instead enable the reuse policy during deployment. The reuse policy returns VMs that are already configured and able to handle traffic to the warm pool.

An autoscaling group takes custom actions to keep a VM in a wait state due to a lifecycle hook. There are two types of lifecycle hooks available for VMs in autoscaling groups:

- **Launch Lifecycle Hook**:Triggered when a new VM in an autoscaling group is about to be launched. The custom actions perform any necessary initialization or configuration steps before the VM goes into service.
- **Termination Lifecycle Hook**: Triggered when a VM in an autoscaling group is about to be terminated. The custom actions perform any necessary cleanup or data backup steps before the VM is terminated.

The lifecycle depends on whether the VM is active or in a warm pool.

- Active VM Lifecycle
- Warm VM Lifecycle

The following sections describe the lifecycle of an active VM during its launch and termination.

- Launch Lifecycle
- Termination Lifecycle

Autoscaling launches a VM into an autoscaling group in response to any of the following events:

- An increase in demand triggers a scale-out action.
- An unhealthy VM is terminated and needs replacing.
- You manually increase the size of the group.

The launch lifecycle proceeds as follows:

1. The autoscaling group starts to launch the VM and puts it into the **Pending**state.
2. The launch lifecycle hook puts the VM in the **Pending:Wait** state and performs the custom actions associated with the hook, including registering the VM and reporting that the VM is healthy. The VM remains in that state for 30 minutes (the Zscaler default value). If it completes the custom actions before 30 minutes elapse, the autoscaling group continues the launch process.
3. The VM enters the **Pending:Proceed** state.
4. The autoscaling group moves the VM into the **InService**state.

Autoscaling terminates a VM in response to any of the following events:

- A decrease in demand triggers a scale-in action.
- Lambda determines that a VM is unhealthy.
- You manually decrease the size of the group.

The termination lifecycle proceeds as follows:

1. The autoscaling group starts to terminate the VM and puts it into the **Terminating**state.
2. The termination lifecycle hook puts the VM into the **Terminating:Wait** state and performs the custom actions associated with the hook. These actions include making an API call to remove the VM from the Cloud Connector Group in the Zscaler Admin Console and notifying the autoscaling group that the VM is ready to be terminated. The VM remains in that state for 15 minutes (the Zscaler default value). If the custom actions complete before 15 minutes elapse, the autoscaling group continues the termination process.
3. When the autoscaling group receives the notification from the lifecycle hook that the custom actions are complete, it moves the VM into the **Terminating:Proceed** state.
4. The autoscaling group moves the VM into the **Terminated** state.

You can use lifecycle hooks with warm pools. Reasons for using them include ensuring there is enough time for VMs entering the warm pool to initialize and for VMs leaving the warm pool to become ready to handle traffic.

The lifecycle of a VM in a warm pool is as follows:

This description applies to warm pools where VMs are configured to be in a **Stopped**state. If VMs are configured to be in a **Running**state, the state is **Warmed:Running** instead of **Warmed:Stopped**.

1. The new VM in the warm pool starts up and enters the **Warmed:Pending** state.
2. After the VM enters the **Warmed:Pending:Wait** state, it pauses to perform a custom action.
3. The VM waits for the custom action to complete before the VM stops and enters the **Warmed:Stopped** state.
4. A launch event such as a scale-out action occurs.
5. After the VM reaches the **Pending:Wait** state, it pauses to perform a custom action.
6. When the custom action completes, the VM restarts and enters the **InService** state.

If traffic is high enough to deplete the warm pool, the autoscaling group can launch a VM directly into itself, if the group is not at its maximum capacity. If the autoscaling group launches a VM directly, the VM pauses only to perform a custom action when it enters the **Pending:Wait** state.

## Viewing Metrics and Logs

- Autoscaling group details
- Activity History
- CloudWatch Metrics
- CloudWatch Logs
- Zscaler Admin Console

The details page for an autoscaling group contains comprehensive information about the group, such as the capacity overview, configuration settings, scaling policy, instance management, activity history, metrics, and so on.

To view autoscaling group details:

1. Open the [Amazon EC2 console](https://console.aws.amazon.com/ec2/).
2. In the navigation pane, click **Auto Scaling Groups**.
3. Select the autoscaling group for which you want to view activity.

See image.

[Image: Capacity overview section in the Amazon EC2 Console showing sections for Desired capacity, Scaling limits, Desired capacity type, and Status. Below this section, the Details, Integrations, Automatic scaling, Instance management, Instance refresh, Activity, and Monitoring tabs provide additional details.]

You can see the current status of a scaling action that is in progress and whether it was successful.

To view activity history:

1. Open the [Amazon EC2 console](https://console.aws.amazon.com/ec2/).
2. In the navigation pane, click **Auto Scaling Groups**.
3. Select the autoscaling group for which you want to view activity.
4. On the details page for the autoscaling group, click the **Activity**tab.
5. Scroll to the **Activity history** section.

You can view autoscaling group metrics in the Amazon EC2 Console and the CloudWatch console.

To view the metrics from the details page of an autoscaling group:

1. Open the [Amazon EC2 console](https://console.aws.amazon.com/ec2/).
2. In the navigation pane, click **Auto Scaling Groups**.
3. Select the autoscaling group for which you want to view metrics.
4. On the autoscaling group details page, click the **Monitoring**tab.
5. (Optional) If you want to see monitoring graphs of aggregated VM metrics for the group, click the **EC2**tab.

See image.

[Image: Viewing Auto Scaling group metrics from the Amazon EC2 Console]

To view metrics on the CloudWatch dashboard:

1. Open the [CloudWatch console](https://console.aws.amazon.com/cloudwatch/).
2. Do one of the following:
  - Go to **Dashboards**> **Custom dashboards** > <Your Dashboard>.
  - Go to **Dashboards**> **Automatic dashboards** > **AutoScaling**.

See image.

[Image: Viewing Auto Scaling metrics from the CloudWatch Dashboard]

Lambda writes logs to Amazon CloudWatch in a designated log group that contains log streams for each invocation of the Lambda function.

To view the logs:

1. In the Lambda console, open the **Functions**page.
2. Locate and then select the Lambda function.
3. Click the **Monitor**tab and scroll to the **CloudWatch Logs** section.

See image.

[Image: Tables in the Lambda console listing invocations, with columns for #, Timestamp, Request ID, Log Stream, Billed duration, and Memory.]

By default, CloudWatch retains the logs for three days. You can change the retention period via the **Actions**menu on the Log groups page in the CloudWatch console.

The autoscaling group name and instance IDs in the Amazon EC2 Console map to the Cloud Connector Group name and VM names in the Zscaler Admin Console. You can view the status of each VM in the Zscaler Admin Console.

To view the status:

1. Go to **Zero Trust Cloud** > **Cloud Connector** > **Cloud Connector Groups.** The value of the **Auto Scaling** column is **true**if the Cloud Connector Group maps to an AWS autoscaling group.
2. Go to **Zero Trust Cloud** > **Cloud Connector** > **Cloud Connector Overview**and scroll to the **Geo View** section. Active VMs in the group are displayed in green. Inactive VMs in the group are displayed in orange. You can watch the status of the VMs change as they are added to or removed from an autoscaling group. Click a VM to see its details. See image. [Image: The Geo View section of the Monitoring page in the Admin Portal, where inactive VMs are red and active VMs are green. This gives a visual display of VMs in an Auto Scaling group as their status changes due to Auto Scaling events. A pop-up window shows details about a VM whose image is hovered over.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/understanding-cloud-connector-deployments-azure-virtual-machine-scale-sets","lastmod":"2026-11-01T23:53Z","nid":"1528441"} -->
## Understanding Cloud Connector Deployments with Azure Virtual Machine Scale Sets

- Source: https://help.zscaler.com/zero-trust-cloud/understanding-cloud-connector-deployments-azure-virtual-machine-scale-sets
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Deployment Management for Virtual Devices > Cloud Connector Deployment Management > Cloud Connector Deployment Management for Azure > Understanding Cloud Connector Deployments with Azure Virtual Machine Scale Sets
- Last modified: 2026-11-01T23:53Z
- Summary: Information about Azure Virtual Machine Scale Sets (VMSS) deployment with Zscaler Cloud Connector

An Azure Virtual Machine Scale Sets (VMSS) deployment dynamically adds Cloud Connector virtual machines (VMs) to a scale set to meet the current load when it increases, and it removes Cloud Connector VMs from the scale set when the load decreases. For example, consider an Azure Virtual Desktop deployment, where users log in to their own virtual workstations at the beginning of the work day and log out at the end of the day. This causes fluctuations in the number of users and the amount of traffic flow during these periods.

VMSS also constantly monitors the health of each VM in the scale set. It removes unhealthy VMs from the scale set and replaces them with healthy ones. If someone manually terminates a VM that is part of a scale set from the Azure portal, the VMSS likewise replaces the VM.

Stopping or rebooting a VM that is part of a scale set from the Azure portal could cause the VM to be terminated.

VMSS provides the following benefits:

- Dynamically scales the number of VMs in the scale set to match demand.
- Automatically removes unhealthy VMs and replaces them with healthy ones.
- Deploys VMs across availability zones for high availability. The Internal Load Balancer (ILB) distributes traffic among the VMs.

This article describes VMSS and how it works in a Cloud Connector deployment. The deployment template prompts you to configure certain VMSS settings mentioned in this article. For information about the deployment template and the deployment steps, see [Deployment Templates for Zscaler Cloud Connector](https://help.zscaler.com/cloud-branch-connector/deployment-templates-zscaler-cloud-connector#azure-terraform) and [Deploying Zscaler Cloud Connector with Microsoft Azure](https://help.zscaler.com/cloud-branch-connector/deploying-zscaler-cloud-connector-microsoft-azure). For comprehensive VMSS information, refer to the [Azure product documentation](https://learn.microsoft.com/en-us/azure/virtual-machine-scale-sets/).

## Topology

The following sections provide a diagram depicting the topology of a VMSS deployment and a description of its components and flow.

- Topology diagram
- Topology details

[Image: Diagram showing a Cloud Connector Virtual Machine Scale Sets (VMSS) deployment in Azure]

- The security stack is deployed into its own Security Resource Group to simplify the management of resources. Zscaler recommends that you deploy the security stack into its own Security VNet and that you peer Workload VNets with it. After the security stack is deployed, route tables in the Workload VNets should have a user-defined route steering traffic to the ILB in front of the Cloud Connectors.
- An Internal Load Balancer (ILB) is deployed in front of the Cloud Connectors and is the entry point for the security stack.
- A VMSS is created in each configured zone to provide high availability across zones.
- A NAT Gateway (NATGW) used for outbound traffic from the Cloud Connectors is deployed in each configured zone and has a dedicated IP address associated with it.
- There are two Azure functions: The Azure Functions communicate with the Cloud Connector API server via REST APIs. Azure Functions do not communicate directly with Cloud Connectors. The Azure Functions app contains Azure Functions and runs a ZIP file to start them. It finds the ZIP file in a new storage account that is created at runtime or from an existing storage account that you specify during deployment.
  - **Health Monitoring**: Uses the custom metrics published by each Cloud Connector to determine whether there are any unhealthy Cloud Connectors that need to be replaced. The function terminates an unhealthy instance and immediately replaces it with a new one. This function runs at one-minute intervals.
  - **Resource Sync**: Ensures that the VMs in a Cloud Connector Group on the Zscaler Admin Console match the VMs in your VMSS. If the function finds a Cloud Connector in the Cloud Connector Group that is not in the VMSS, it cleans up that instance in the Cloud Connector Group to ensure that the two entities are in sync. This function runs at 30-minute intervals.

## Scale-Out and Scale-In

Each Cloud Connector independently reports custom CPU utilization metrics to the Azure Monitor Service at one-minute intervals to advertise the load it is handling. VMSS uses the aggregate CPU utilization of all VMs in the scale set to determine when to trigger scale-out and scale-in events and how aggressively to do so.

Custom CPU metrics provide more detailed information about CPU usage, so Cloud Connector publishes them instead of VM-level metrics.

- Scaling Rules
- Scheduled Scaling

Scaling rules define the parameters for triggering scale-out and scale-in events. Zscaler recommends the following default thresholds and values:

| Parameter | Description | Scale-Out Rule | Scale-In Rule |
| --- | --- | --- | --- |
| CPU utilization threshold | The aggregate percentage of CPU utilization for the VMs in the scale set. | 70% | 50% |
| Duration | The number of consecutive minutes after the threshold is crossed before a scale-out or scale-in event is triggered. | 10 | 10 |
| Cooldown | The number of minutes to wait before triggering another scale-out or scale-in event. Metrics continue to be monitored and reported during the cooldown period. This gives the VMSS time to stabilize and determine how the updated number of VMs impacts the CPU utilization. | 15 | 15 |
| Instance count | The number of VMs to remove or add for a single scale-out or scale-in event. | 1 | 1 |

Examples:

- There are 4 VMs in a scale set. The VMs report CPU utilization of 80%, 60%, 75%, and 90%, so the aggregated CPU utilization for the scale set is 76.25%. The CPU utilization remains higher than the scale-out threshold for 10 consecutive minutes, so a scale-out event adds one VM to the scale set, bringing the number of VMs to 5.
- There are three VMs in a scale set. The VMs report CPU utilization of 50%, 40%, and 35%, so the aggregated CPU utilization for the scale set is 41.7%. The aggregate CPU utilization remains lower than the scale-in threshold for 10 consecutive minutes, so a scale-in event removes one VM from the scale set, bringing the number to two. During the cooldown period, a traffic spike brings the aggregated CPU utilization to 83%, so when the cooldown period ends, a scale-out event adds one VM to the scale set, bringing the number of VMs back to three.
- There are two VMs in a scale set. The VMs report CPU utilization of 65% and 55%, so the aggregated CPU utilization for the VMSS is 60%. No scale-out or scale-in event is triggered because the aggregated CPU utilization remains within the scale-out and scale-in thresholds for 10 consecutive minutes.

In logs and reports, the CPU utilization metric is displayed as `smedge_cpu_utilization`.

If you have predictable load patterns, you can define a schedule for scale-out and scale-in events. This ensures that enough VMs are provisioned before the predicted spike. For example, if a batch job runs every Saturday at 6:00 PM, you could preemptively schedule a scale-out event for 5:45 PM. You can define the following parameters during deployment:

- **Minimum instances**: The minimum number of VMs in the scaling set during the scheduled scaling event.
- **Days of week**: The days of the week when the schedule takes effect.
- **Start time**: The time in hours and minutes to start the scheduled scaling event.
- **End time**: The time in hours and minutes to end the scheduled scaling event.

## Cloud Connector Health Monitoring

Health monitoring includes the following entities:

- **Custom Metric Publishing**: Each Cloud Connector publishes a VM-level custom health metric at one-minute intervals. This metric value is 0 for an unhealthy VM or 100 for a healthy VM.
- **Health Monitoring**: The Health Monitoring function consumes the health metric at one-minute intervals and initiates the termination of a VM that it determines is unhealthy.

In logs and reports, the health metric is displayed as `cloud_connector_aggr_health`.

- Grace Period
- Terminating Unhealthy VMs

A grace period allows a Cloud Connector to boot up before its health is evaluated, and it is potentially terminated. The grace period lasts until one of the following events occurs:

- The VM has been alive for more than 30 minutes.
- The VM reports at least one healthy metric.

After the grace period ends, a Cloud Connector is considered unhealthy if the custom health metric is reported as either:

- **None**or **0** for 7 out of the last 10 samples, starting with the first healthy sample
- **None**or **0** for 5 consecutive samples, starting with the first healthy sample

The Health Monitoring function determines whether a VM is unhealthy. This function terminates an unhealthy VM after the grace period ends and the unhealthy criteria are met. The VM is replaced immediately.

Unhealthy instances are terminated in iterations. In a single iteration, a VMSS can terminate 20% of the instances, or one instance, whichever is greater. The instance to terminate is based on which instance has the most healthy statuses over a 10-sample stretch. If multiple instances have the same number of unhealthy statuses, the instances to terminate are chosen randomly.

## Viewing Metrics and Logs

You can view the following metrics and logs in the [Azure portal](https://portal.azure.us/).

- Metrics
- Functions App Logs

- Cloud Connector Metrics
- VMSS Metrics

- Recent Invocations
- Log Streaming in Real Time
- Application Insights

Cloud Connectors publish health metrics at one-minute intervals and are managed by Application Insights.

To display the metrics:

1. Navigate to **Resource Groups** > <Resource Group> > <VMSS> > <VM> > **Monitoring**> **Metrics**.
2. On the **Metrics**page, click **Add metric**.
3. Select the following parameters:
  - Scope: <VM name>
  - Metric Namespace: **zscaler/cloudconnectors**
  - Metric: **cloud_connector_aggr_health**
  - Aggregation: **Avg** (average)

[Image: Graph showing query for Cloud Connector metrics where you use the Scope, Metric Namespace, Metric, and Aggregation drop-down menus to specify query parameters]

Cloud Connectors in a scale set publish scaling metrics to the Health Monitoring function at one-minute intervals. VMSS consumes metrics from Azure Monitor for scaling decisions. The scaling metrics include `smedge_cpu_utilization`, `smedge_mem_utilization`, `smedge_bytes_in`, and `smedge_bytes_out`. The scaling rules compare the `smedge_cpu_utilization` value with the defined threshold.

To display the VMSS metrics:

1. Navigate to **Resource groups** > <Resource Group> > <VMSS> > <VM> > **Monitoring**> **Metrics**.
2. On the **Metrics**page, click **Add metric**.
3. Select the following parameters:
  - **Scope**: VMSS name
  - **Metric Namespace**: **zscaler/cloudconnectors**
  - **Metric**: **smedge_metrics**
  - **Aggregation**: **Avg** (average)
4. Click **Add filter**.
5. Select the following parameters:
  - **Property**: **metric_name**
  - **Operator**:**=**
  - **Values**: **smedge_cpu_utilization**

[Image: Graph showing query for VMSS metrics where you use the Scope, Metric Namespace, Metric, and Aggregation drop-down menus to specify query parameters]

An Azure invocation is logged each time an Azure function is executed.

To view recent invocations:

1. Go to **Resource groups** > <Resource Group>. The **Resource group** page appears.
2. In the **Name**column on the **Resource**tab, find the Functions App and click it. The **Functions App** page appears.
3. On the **Functions**tab, click the function in the **Name**column. The details page for the Functions app appears.
4. Click the **Invocations**tab and then click the invocation in the **Date**column.

[Image: Viewing recent App Function Invocations in Azure Portal]

You can view logs in real time for functions that are executing.

To view real-time logs:

1. Go to **Resource groups** > <Resource Group>. The **Resource group** page appears.
2. In the **Name**column on the **Resources**tab, find the Functions App and click it in the **Name**column. The **Functions App** page appears.
3. On the **Functions**tab, click the function in the **Name**column. The details page for the Functions app appears.
4. Click the **Logs**tab.

[Image: Viewing real-time App Function logs in the Azure Portal]

The Application Insights feature allows you to perform queries to view specific log messages, executions, time frames, and so on. For example, you can view Health Monitoring function logs that report that the VMSS found no instances to terminate. A specific message is defined when querying the logs, which allows you to refine your search instead of manually going through each invocation or continuously watching the real-time streaming of logs.

To view logs through Application Insights:

1. Go to **Resource groups** > <Resource Group>.
2. On the **Application Insights** page, click **Logs**.
3. Enter a query and then click **Run**. For example, the following query would show you when there are no instances to terminate. `union traces | union exceptions | where timestamp > ago(1d) | where customDimensions['Category'] == 'Function.healthMonitor.User' or customDimensions['Category'] == 'Function.healthMonitor' | where message contains "No instances to terminate on this iteration." | order by timestamp asc | project timestamp, message = iff(message != '', message, iff(innermostMessage != '', innermostMessage, customDimensions.['prop__{OriginalFormat}']))`

[Image: Viewing Health Monitor function logs through Application Insights]

## Access to Azure Resources

Managed identities provide granular access control for Azure resources, which eliminates the need to explicitly store secret credentials within the Azure environment.

Azure Key Vault securely manages credentials for external services such as the Zscaler Admin Console.

Two user-assigned managed identities are required to perform Azure operations:

- Cloud Connector
- Functions App

This managed identity allows Cloud Connectors to perform Azure operations such as network interface discovery and metric publishing. It needs the following roles:

- Network Contributor
- Monitoring Metrics Publisher
- Storage Queue Data Contributor

This managed identity allows the Azure Functions App to make API calls to perform operations such as instance termination, instance replacement, and metric reading. It needs the following roles:

- Network Contributor
- Virtual Machine Contributor
- Monitoring Contributor
- Managed Identity Operator
- Storage Blob Data Reader

User-assigned managed identities allow access to different entities in a VMSS deployment. Ensure that Cloud Connector is not assigned an Azure System-Assigned Managed Identity, because that identity overrides the deployment requirements.

For information about creating managed identities and assigning roles, see [Deploying Zscaler Cloud Connector with Microsoft Azure](https://help.zscaler.com/cloud-branch-connector/deploying-zscaler-cloud-connector-microsoft-azure).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/understanding-cloud-connector-deployments-google-cloud-platform-managed-instance-groups-autoscaling","lastmod":"2026-11-01T23:53Z","nid":"1535452"} -->
## Understanding Cloud Connector Deployments with Google Cloud Platform Managed Instance Groups with Autoscaling

- Source: https://help.zscaler.com/zero-trust-cloud/understanding-cloud-connector-deployments-google-cloud-platform-managed-instance-groups-autoscaling
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Deployment Management for Virtual Devices > Cloud Connector Deployment Management > Cloud Connector Deployment Management for GCP > Understanding Cloud Connector Deployments with Google Cloud Platform Managed Instance Groups with Autoscaling
- Last modified: 2026-11-01T23:53Z
- Summary: Information about Google Cloud Platform autoscaling managed instance group (MIG) deployment with Zscaler Cloud Connector

A Google Cloud Platform (GCP) Managed Instance Group (MIG) with autoscaling deployment dynamically adds Cloud Connector virtual machines (VMs) to an instance group to meet the current load when it increases, and it removes Cloud Connector VMs from the instance group when the load decreases. For example, consider a virtual Windows workstation deployment where users log in to their own virtual workstations at the beginning of the work day and log out at the end of the day. This causes fluctuations in the number of users and the amount of traffic flow during these periods.

Instance groups also constantly monitor the health of each Cloud Connector VM in the instance group. They remove unhealthy VMs from the instance group and replace them with healthy ones. If someone manually terminates a VM that is part of an instance group from the Google Cloud console, the instance group replaces the VM.

When deploying a Cloud Connector, only deploy an autoscaling group (ASG) with an ASG template or a non-ASG with a non-ASG template. Additionally, stopping or rebooting a VM that is part of an instance group from the Google Cloud console could cause the VM to be terminated.

Instance groups with autoscaling provide the following benefits:

- Dynamically scale the number of VMs in the instance group to match demand.
- Automatically remove unhealthy VMs and replaces them with healthy ones.
- Deploy VMs across availability zones for high availability. Load balancing distributes traffic among the VMs.

This article describes instance groups and how they work in a Cloud Connector deployment. The deployment template prompts you to configure certain instance group settings mentioned in this article. For information about the deployment template and the deployment steps, see [Deployment Templates for Zscaler Cloud Connector](https://help.zscaler.com/cloud-branch-connector/deployment-templates-zscaler-cloud-connector#azure-terraform) and [Deploying Zscaler Cloud Connector on the Google Cloud Platform](https://help.zscaler.com/cloud-branch-connector/deploying-zscaler-cloud-connector-google-cloud-platform). For comprehensive autoscaling information, refer to the [GCP product documentation](https://docs.cloud.google.com/compute/docs/autoscaler).

## Topology

The following sections provide a diagram depicting the topology of an autoscaling deployment and a description of its components and flow.

- Topology diagram
- Topology details

[Image: Diagram showing a Cloud Connector autoscaling deployment on the Google Cloud Platorm]

- The security stack is deployed in either a dedicated GCP project or within the same project as the workloads, based on your organizational architecture. GCP has an architectural limitation where instances with multiple network interfaces require separate virtual private cloud (VPC) networks for each interface. Cloud Connector deployments therefore require the creation of two distinct VPCs: one for management and one for service and security operations. After the security stack is in place, you must configure custom routes in the workload VPCs to direct appropriate traffic to the load balancer in front of the Cloud Connectors. This load balancer serves as the central entry point for the security stack.
- Zonal MIGs with autoscaling are deployed in each configured region or zone, ensuring high availability and automatic failover across zones.
- A Cloud NAT gateway is deployed in each region for the Cloud Connectors. This arrangement provides managed, scalable outbound internet access and allocates dedicated external IPs for each gateway.
- Two Google Cloud Run functions are deployed: The Resource Sync Cloud Run function uses RESTful APIs to communicate with the Cloud Connector API endpoint securely over HTTPS. The functions do not directly interact with individual Cloud Connector VMs; instead, they orchestrate via the Admin API.
  - **Health Monitoring**: This function uses custom metrics exported from each Cloud Connector VM (for example, via Cloud Monitoring/Operations) to determine the health status of the VM. The instance group automatically terminates unhealthy Cloud Connector VMs and replaces them with new ones. The Health Monitoring function is triggered every minute by default using Cloud Scheduler.
  - **Resource Sync**: This function ensures that the VMs registered in the Zscaler Admin Console match the membership of each Cloud Connector instance group. If the function discovers a Cloud Connector in the Zscaler Admin Console that is not part of any instance group, it issues an API call to the Zscaler Admin Console to delete the VM. This function runs every 10 minutes by default.
- Each Cloud Function is packaged in a [ZIP file](https://zscaler-cc-functions-artifacts.s3.amazonaws.com/zscaler-cc-functions/latest/cloud-functions-latest.zip) and deployed via Google Cloud Storage. The ZIP file is uploaded either to a newly created storage bucket during deployment or to an existing bucket specified in the deployment configuration. The Cloud Functions retrieve their code from this bucket during initialization.

## Scale-Out and Scale-In

Each Cloud Connector independently posts data plane CPU utilization metrics at one-minute intervals to GCP Cloud Monitoring as a user-defined metric. The MIG autoscaling policy uses the aggregate CPU utilization value across all VMs in the instance group to determine whether a scale-in or scale-out event should happen based on a target percentage value.

Auto Scaling uses custom CPU utilization metrics instead of VM-level metrics because custom metrics provide more detailed and precise information about CPU usage. In logs and reports, the CPU utilization metric is displayed as `smedge_cpu_utilization`.

- Scaling Policy
- Cooldown Period
- Examples

The scaling policy manages scale-out and scale-in events using the following variables:

- **Target value**: By default, the target value is 80% aggregate CPU utilization across all VMs in the instance group over a period of two to three minutes. Autoscaling maintains CPU utilization as close to the target value as possible without exceeding it.
- **Instance group size**: When a scaling event happens, autoscaling automatically determines the number of VMs to add or remove to bring the aggregate CPU utilization metric closer to the target value. By default, the maximum size of an instance group is 10 VMs and the minimum size is one VM. (The maximum cannot exceed the [Cloud Connector group limit of 16 VMs per group](https://help.zscaler.com/unified/ranges-limitations).)

You can view the policy in the instance group details in the Google Cloud console.

While your VMs are initializing, the utilization metric might not reflect normal conditions. As a safeguard, autoscaling provides a cooldown (or initialization) period, which is the number of seconds between scaling actions.

- For scale-in events, autoscaling takes into account utilization data from all VMs, even those that are still in the cooldown period.
- For scale-out events, autoscaling ignores utilization data from VMs that are still in the cooldown period.

By default, the initialization period is 900 seconds (15 minutes).

The following examples demonstrate how scaling events affect the size of an instance group with a minimum size of two, a maximum size of 5, and the default target value (80%):

**Scale-Out**: Because the minimum instance group size is two, at least two VMs must always be running.

- No scaling event happens in normal conditions with two VMs running at 60% aggregate CPU utilization.
- If the aggregate CPU utilization exceeds 80%, a scale-out event happens, bringing the number of VMs in the instance group to three.
- After the cooldown period, if the aggregated CPU utilization still exceeds 80%, another scale-out event adds one more VM to the instance group, bringing the number of VMs to 4. This continues after the cooldown period ends until the average is less than 80%.

**Scale-In**: GCP autoscaling uses its own logic to determine what to do when the aggregate CPU utilization is less than 80%:

- Wait because the value is too close to 80% or has not been under 80% long enough.
- Trigger a scale-in event because, based on the time sample and the individual CPU utilization of each VM, removing one VM will meet the following criteria:
  - The number of VMs will be at or above the minimum target group size.
  - The average fluctuation for the remaining VM will likely remain below the target CPU utilization value.

## Cloud Connector Health Monitoring

Health monitoring includes the following entities:

- **Custom Metric Publishing**: Each Cloud Connector publishes a VM-level custom metric at one-minute intervals. This metric value is either 0 for an unhealthy VM or 100 for a healthy VM. Custom metrics also include dataplane CPU utilization metrics published at one-minute intervals, described in the previous section.
- **Health Monitoring**: The Health Monitor function consumes the health metric at one-minute intervals and initiates the termination of a VM that it determines is unhealthy. It uses two criteria to determine whether to terminate the VM: Recent Health Data; Missing Metrics Unhealthy VMs are terminated in iterations. By default, in a single iteration, 16 VMs can be terminated. After a VM is terminated, it is replaced immediately.

The Health Monitor function evaluates recent health data using sliding window logic with the following configurable environment variables:

- The size of the sliding window. The default is 10 consecutive minutes.
- The amount of time the VM was in an unhealthy state. The default value for defining a VM as unhealthy is 5 consecutive minutes.
- The tolerance for flapping values in the sliding window. The default value is 7 unhealthy counts in 10 consecutive minutes.

The Health Monitor function uses the following configurable environment variables to handle scenarios in which a running VM does not post metrics:

- If the VM does not report metrics for two minutes, the function generates a warning log.
- If the VM does not report metrics for 5 minutes, the function generates a critical log.
- If the VM does not report metrics for 10 minutes, the function generates a termination log and starts the process of deleting the VM.

In logs and reports, the health metric is displayed as `cloud_connector_aggr_health`.

## Viewing Metrics and Logs

- Instance Group Details
- Cloud Connector VM Details

The details page for an instance group contains comprehensive information about the group, such as the capacity overview, configuration settings, scaling policy, instance management, activity history, metrics, and so on.

To view the instance group details:

1. Open the Google Cloud console.
2. In the navigation pane, click **Instance Groups**.
3. Select the instance group for which you want to view details. The **Overview**tab opens. See image.
4. Click the **Details**tab to review the instance status, template, configuration, location, group size, and autoscaling policy. See image.
5. Click the **Monitoring** tab to review instance group metrics over a period of time. See image.

To view details about a VM in the instance group:

1. Open the Google Cloud console.
2. In the navigation pane, click **Instance Groups**.
3. Select the instance group for which you want to view details.
4. On the **Overview**tab, in the **VM instances** section, click an instance in the **Name** column.
5. Click **Observability** to monitor VM health, behavior, and performance. See image.

## Access to GCP Resources

Service accounts provide secure access to Google Cloud resources. Three service accounts are required in a GCP autoscaling deployment:

- Deployment Service Account
- Cloud Connector VM Service Account
- Cloud Function Service Account

This service account allows Terraform to run during deployment. It needs the following roles:

- Compute Instance Admin (v1)
- Compute Network Admin
- Compute Security Admin
- Service Account Admin
- Service Account User
- Secret Manager Admin
- Project IAM Admin
- DNS Administrator (optional)
- Cloud Functions Admin (Autoscaling only)
- Cloud Scheduler Admin (Autoscaling only)
- Storage Admin (Autoscaling only)

This service account also needs the following APIs enabled:

- Compute Engine API
- Cloud DNS API
- Cloud Resource Manager API
- Identity and Access Management (IAM) API
- Secret Manager API
- Autoscaling deployments only:
  - Cloud Functions API
  - Cloud Scheduler API
  - Cloud Monitoring API
  - Cloud Logging API
  - Cloud Build API

This service account allows Cloud Connectors to access secret credentials. It needs the following roles:

- Monitoring Metric Writer
- One of the following roles, depending on the method you use to manage and store your secret credentials:
  - Secret Manager Secret Accessor (for GCP Secret Manager)
  - Service Account Token Creator (for HashiCorp Vault) with the following permissions assigned to it:
    - compute.instanceGroups.list
    - compute.instances.get
    - iam.serviceAccounts.get
    - iam.serviceAccountKeys.get,
    - iam.serviceAccounts.signJwt

This service account allows the Cloud Run Functions (Health Monitor and Resource Sync) to make API calls to perform operations such as reading metrics, terminating VMs, and replacing VMs. It needs the following roles:

- Compute Instance Admin (v1)
- Monitoring Viewer
- Logs Writer
- Cloud Run Invoker
- One of the following roles, depending on the method you use to manage and store your secret credentials:
  - Secret Manager Secret Accessor (for GCP Secret Manager)
  - Service Account Token Creator (for HashiCorp Vault) with the following permissions assigned to it:
    - compute.instanceGroups.list
    - compute.instances.get
    - iam.serviceAccounts.get
    - iam.serviceAccountKays.get
    - iam.serviceAccounts.sign.Jwt

For information about creating service accounts and assigning roles, see [Deploying Zscaler Cloud Connector on the Google Cloud Platform](https://help.zscaler.com/tech-pubs-drafts/deploying-zscaler-cloud-connector-google-cloud-platform-draft-doc-57264).

[Image: Cloud Connector instance group Overview tab, showing status and other details, and the VM instances in the group]

[Image: Cloud Connector instance group Details tab, where you can view status, the instance template, configuration, and location, the group size, and autoscaling parameters]

[Image: Cloud Connector instance group Monitoring tab, where you can view the group size and CPU utilization over a period of time]

[Image: Cloud Connector instance Observability tab, where you can view the CPU utilization, memory utilization, network traffic, disk space utilization, new connections, and disk throughputDetails tab, where you can view status, the instance template, configuration, and location, the group size, and autoscaling parameters]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/understanding-cloud-connector-repository-certificate-expiry-and-renewal","lastmod":"2026-11-01T23:53Z","nid":"1546120"} -->
## Understanding Cloud Connector Repository Certificate Expiry and Renewal

- Source: https://help.zscaler.com/zero-trust-cloud/understanding-cloud-connector-repository-certificate-expiry-and-renewal
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Troubleshooting > Understanding Cloud Connector Repository Certificate Expiry and Renewal
- Last modified: 2026-11-01T23:53Z
- Summary: Information on Cloud Connector repository certificate expiry and renewal.

This article provides troubleshooting information and guidelines about the TLS server certificate for Cloud Connector expiration of the software update repository (`pkg-repo.zscaler.com`). When this certificate is renewed, the repository uses a new DigiCert G2 certificate chain. To learn more, see [End-of-Support for Cloud Connector Repository Certificate](https://help.zscaler.com/product-usage-terms/end-support-cloud-connector-repository-certificate).

When updating your virtual machines (VMs) to use the latest marketplace images, consider the following:

- Required Action to Take
- Users Who Are Impacted
- Cloud Connectors Are Not Automatically Updating
- Redeploying Existing Cloud Connectors
- Changes to ASG Deployments
- Verifying that the AWS ASG Launch Template Is Set to Use the Latest Cloud Connector Image
- Verifying that the Azure VMSS Is Set to Use the Latest Cloud Connector Image
- Verifying that the GCP ASGs Are Set to Use the Latest Cloud Connector Image

Existing, active Cloud Connectors update automatically through regularly scheduled upgrades. The Zscaler service monitors upgrade activity across all clouds and users, and regularly generates internal reports to identify any users whose connectors do not appear to be upgrading.

For new Cloud Connector deployments, Zscaler-provided Infrastructure as Code (IaC) templates are designed to use the latest available image version by default. Unless you hard-code older image versions in custom IaC templates, the main requirement is to rerun or refresh your templates so that subsequent deployments use the latest images.

- Amazon Web Services (AWS): Use image version ZS42.1.0 or later (Deployed AMI Name: starts with zs-cc-ga-03092026).
- Microsoft Azure: Use image version 42.1.0 or later.
- Google Cloud Platform (GCP): Use image version zs-cc-ga-03092026 or later.

For autoscaling group (ASG) deployments, you must update your launch/instance template to reference the new marketplace image. If you do not update your launch/instance template, new instances cannot connect to the software update repository.

To determine whether you are impacted:

1. Check your Cloud Connector software version:
  1. In the Zscaler Admin Console, from the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console), go to **Zero Trust Cloud** > **Cloud Connector** > **Cloud Connector Groups**. The **Cloud Connector Groups** page appears.
  2. On the **Cloud Connector Groups** page, expand a Cloud Connector group and click the **Edit** icon. The **Edit Cloud Connector** window appears.
  3. In the **Edit Cloud Connector** window, click the **Upgrade Status** tab.
  4. On the **Upgrade Status** tab, in the **Upgrade Information** section, locate the **Current Version**. Cloud Connectors running version 1.146.5 or later have received the automatic CA bundle update and are not affected. To learn more, see [About Cloud Connector Groups](https://help.zscaler.com/cloud-branch-connector/about-cloud-connector-groups).
2. Check your deployment image version. If you are deploying new Cloud Connectors or using ASGs, verify the marketplace image version in your launch configuration. If the image version is older than the fixed versions, you are using an affected image and must update it. To learn more, see [Identifying the Zscaler Cloud Connector Version](https://help.zscaler.com/cloud-branch-connector/identifying-zscaler-cloud-connector-version).

Cloud or Branch Connectors might not update automatically for reasons including:

- Firewall and/or SSL inspection connectivity issues. Users can resolve this issue in their own environment.
- Modifying the scheduled upgrade windows to circumvent automatic updates. Users can resolve this issue by stopping modifying configurations.
- Zscaler-controlled block filtering (per user request and/or Zscaler debugging necessities). To disable filtering, contact Zscaler Support.
- Cloud Connector appliance issues might prevent updates from proceeding (i.e., process errors, crashes, or disk partition space issues). To troubleshoot this issue, contact Zscaler Support.

Existing Cloud Connectors do not need to be redeployed, as long as they remain current by running the latest software through regular, automatic scheduled upgrades.

Some improvements (i.e., performance enhancements, operating system security fixes, and other platform updates) might only be available through newer image releases. Zscaler recommends that all new deployments use the latest marketplace images and that existing instance fleets be refreshed periodically.

Refreshing existing fleets typically involves updating CI/CD deployment pipelines based on the original IaC method used, such as Terraform or CloudFormation, to ensure that future deployments are not unintentionally pinned to older image versions. This is important for autoscaling deployments, where cloud providers might rely on constructs such as launch templates or Virtual Machine Scale Set (VMSS) profiles.

If you use ASGs (AWS ASG, Azure VMSS, or GCP Managed Instance Group (MIG)), you must update your launch/instance template to reference the new marketplace image.

- AWS: Update the AMI ID in your launch template and create a new launch template version. Update your ASG to use the new launch template version.
- Azure: Update the image reference in your VMSS model and trigger a model update.
- GCP: Create a new instance template with the updated image and update your MIG to use the new template.

If you do not update ASG configurations, newly scaled-out instances use the old image, which is unable to connect to the software update repository after expiration.

The Terraform module templates utilize the following `aws_ami` data source. Without modification, this code asks to find the newest (`most_recent`) AMI ID available for the Cloud Connector image product code. The value is outputted to an attribute: `data.aws_ami.cloudconnector.id`.

```
################################################################################
# Locate Latest CC AMI by product code
################################################################################
data "aws_ami" "cloudconnector" {
most_recent = true
filter {
name = "product-code"
values = var.aws_region == "cn-north-1" || var.aws_region == "cn-northwest-1" ? ["axnpwhsb4facossmbm1h9yad6"] : ["2l8tfysndbav4tv2nfjwak3cu"]
#change product code value for China marketplace
}
owners = ["aws-marketplace"]
}
```

The data source attribute is then fed into the `cc_asg module` as the input variable: `ami_id`.

```
# Create the specified CC VMs via Launch Template and Autoscaling Group
module "cc_asg" {
source = "../../modules/terraform-zscc-asg-aws"
ami_id = contains(var.ami_id, "") ? [data.aws_ami.cloudconnector.id] : var.ami_id
```

You can verify the resulting deployment configuration in AWS within the ASG details.

See image.

By rerunning Terraform utilizing the original deployment state details, the AWS launch template where the `image_id` is changed from `ami-0b11365e64b30005e` (old ZS6.1.26.2) to new `ami-07c8a4afe978a3087` (new ZS42.1.0) is shown. AWS is also bumping the launch template version from two to three. Enter `yes` and press enter to apply the change. Applying this change has no effect on running Cloud Connector appliances on the ASGs that are using this launch template.

See image.

If you check the ASG details again, the launch template is still set to use the latest version, and the AMI ID is changed to `ami-07c8a4afe978a3087`.

See image.

Future scale-out or health-based replacement instances are automatically deployed from this new AMI. If you want to proactively refresh your instance, you can mandate instance replacements with this new AMI, either manually or with cloud native tools (i.e., Instance Refresh), which is recommended as a best practice to test and verify.

Updating the launch template does not automatically replace existing EC2 instances. To roll out the new AMI to your running environment, use the following best practices:

- Use the recommended Instance Refresh feature to replace instances in your ASG automatically. This performs a rolling update, ensuring availability is maintained.
- In the AWS Console, navigate to your ASG's Instance Refresh page and click **Start instance refresh**.
- In Terraform, ensure that your `aws_autoscaling_group` resource includes an `instance_refresh` block to automate this process upon launch template updates.
- Manually terminate instances one by one. The ASG automatically launches replacement instances using the new launch template version and thus the new AMI.

To verify that your Azure VMSS uses the latest Cloud Connector image:

1. Deploy a VMSS deployment with Terraform version 24.3.5. See image.
2. In the Azure portal, go to your VMSS deployment.
3. In your VMSS deployment, from the left-side navigation, click **Operating system**.
4. On the **Operating system** page, click **Change image reference**. See image. The **Change image reference** page appears.
5. On the **Change image reference** page, configure the following:
  - **Select image source**: Select **Platform image**.
  - **Image**: Select the latest marketplace image for Zscaler Cloud Connector.
  - **VM architecture**: Select **x64**. See image.
6. Click **Apply** to apply the changes.
7. On the **Scaling** page, in the **Instance limits** section, configure the following:
  - **Minimum**: Enter `3`.
  - **Maximum**: Enter `5`.
  - **Default**: Enter `3`. See image.
8. Save the profile.

On the **Operating system** page, the newly scaled-out instance has the latest marketplace image, and the **Image Reference** shows the version as `latest`.

See image.

To verify that your GCP ASG uses the latest Cloud Connector image:

1. Deploy a GCP ASG deployment with `production-image-cc-gcp-zs42-202601211704`. See image.
2. In the GCP console, on the **Instance templates** page, create a new instance template.
3. On the **Instance templates** page, search for and select your template.
4. On the instance template page, click **Create similar**. See image.
5. On the **Create an instance template** page, in the **Boot disk** section, click **Change**. The **Boot disk** window appears.
6. In the **Boot disk** window, click the **Custom images** tab.
7. From the **Image** drop-down menu, select the marketplace Zscaler GCP image. See image.
8. Save your changes and create the instance template according to your preferences.
9. Navigate to the **Instance Groups** page and select your GCP ASG instance group.
10. On the instance group page, click **Edit**.
11. In the **Instance template & overrides** section, from the **Instance template** drop-down menu, select the new instance template. See image.
12. In the **Group size & autoscaling section**, reconfirm the minimum, maximum, and the metrics values.
13. Click **Save** to update the new template to the GCP ASG deployment.

Scale out a new instance by changing the minimum and maximum values in an instance group. The newly scaled-out instance appears with a newly selected instance template.

See image.

[Image: Verifying the resulting deployment in AWS]

[Image: Configuration changes]

[Image: The launch template using the latest version in AWS]

[Image: VMSS deployment with Terraform version 24.3.5 in Azure]

[Image: The Change image reference page in Azure]

[Image: Instance limits on the Scaling page in Azure]

[Image: Instance deployed with the latest version in Azure]

[Image: A GCP ASG deployment with production-image-cc-gcp-zs42-202601211704.]

[Image: Clicking Create similar on the Instance template page in Azure]

[Image: Selecting the marketplace Zscaler GCP image from the drop-down menu in GCP]

[Image: Selecting the new instance template from from the Instance template drop-down menu in the Instance template & overrides section in Azure]

[Image: The newly scaled-out instance appearing with a newly selected instance template in GCP]

[Image: Selecting Change image reference on the Operating system page in Azure]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/understanding-disaster-recovery","lastmod":"2026-11-05T11:54Z","nid":"1539697"} -->
## Understanding Disaster Recovery

- Source: https://help.zscaler.com/zero-trust-cloud/understanding-disaster-recovery
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Administration > Global Profiles > Understanding Disaster Recovery
- Last modified: 2026-11-05T11:54Z
- Summary: Overview information on Disaster Recovery (DR) Gateways with respect to BCC and Global Profiles in Zscaler Admin Console.

The Disaster Recovery (DR) solution is built around two primary, independent operational pillars designed to address different disaster scenarios:

## Automated Failover (Data Plane Outages)

The following list describes the approach for automated failover.

- **Operational focus:** This automated mechanism handles situations where Zscaler's data plane nodes (the Public Service Edges carrying and inspecting traffic) become unreachable, while the control plane remains operational.
- **How it works:** The Cloud Connector constantly monitors the traffic path's health. If the primary path is declared dead, it transparently redirects the egress traffic to a preconfigured, isolated regional Business Continuity Plan (BCP) subcloud.

The following diagrams illustrate three scenarios:

- Traffic is behaving as normal. See image.
- The data plane is unreachable, so traffic falls back to the BCP Subcloud (dedicated fallback destination, available with the BCC SKU). See image.
- The BCP Subcloud is unreachable. See image.

## DNS-Triggered Disaster Recovery (Control-Plane & Regional Outages)

The following list describes the approach for recovering from control plane and regional outages.

- **Operational focus:** This administrator-controlled mechanism addresses control plane outages or macro-level disaster scenarios where the Zscaler Admin Console is unavailable, making policy-level configuration changes impossible.
- **How it works:** Rather than relying on Zscaler's cloud-side systems, the Cloud Connector continuously polls a customer-owned DNS zone. By publishing a standard DNS TXT record containing specialized key-value instructions (e.g., `v=1, k=zia, b=on`), administrators can force DR bypass or failover mode without needing access to the Zscaler Admin Console. For more information, see [About Global Profiles](https://help.zscaler.com/cloud-branch-connector/about-global-profiles).

[Image: Normal traffic behavior diagram]

[Image: BCP sublcoud fallback diagram]

[Image: BCP subcloud unreachable diagram]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/understanding-high-availability-and-failover","lastmod":"2026-11-01T23:53Z","nid":"1455436"} -->
## Understanding High Availability and Failover

- Source: https://help.zscaler.com/zero-trust-cloud/understanding-high-availability-and-failover
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Deployment Management for Virtual Devices > Understanding High Availability and Failover
- Last modified: 2026-11-01T23:53Z
- Summary: Information on High Availability and Failover for Zscaler Cloud Connector.

High Availability (HA) is critical to ensure continued secure access to applications for workloads routing through Zscaler Cloud Connector. The various areas to consider when deploying are, but not limited to:

- Using Cloud Connectors to the Zero Trust Exchange (ZTE) HA and failover
- Using cloud native load balancers to support horizontal scaling and failover of Cloud Connectors
- Using cloud provider best practices to ensure HA across availability zones within regions

See image.

[Image: High availability and failover flows for Cloud Connector.]

Blue arrows indicate primary, or active, Internet & SaaS tunnels. Green lines indicate secondary Internet & SaaS tunnels.

## Load Balancing

The Zscaler service integrates with the native load-balancing services offered by respective cloud providers. The load balancers conduct HTTP health probes on a defined port selected during deployment to determine the health of each Cloud Connector. Cloud Connector listens to the configured port for HTTP health probes on the `?cchealth` path from the load balancers. A healthy Cloud Connector has a 200 HTTP response code, and an unhealthy Cloud Connector has a 503 HTTP response code or no response. Health probes ensure that the Cloud Connector VM is functional and can successfully forward workload traffic to Internet & SaaS (ZIA) Public Service Edges or Private Service Edges and Private Access (ZPA) Public Service Edges or Private Service Edges.

Cloud Connectors scale horizontally and are all active, enabling organizations to add more Cloud Connectors into a group to support higher throughput per egress location. New sessions should be sent to healthy Cloud Connectors. In some cases, such as existing connections flowing through a Cloud Connector that becomes unhealthy, the sessions might temporarily fail until the flow ages or times out and the load balancer begins to send the session through a healthy Cloud Connector.

- Health Probe Intervals

Zscaler deployment templates use a recommended default configuration for health check intervals. The Microsoft Azure Load Balancer defaults to 15 seconds, and Amazon Web Services (AWS) Gateway Load Balancer defaults to 30 seconds.

The default settings are optimized to take unhealthy Cloud Connectors out of the rotation efficiently, avoiding network "bumps."

While the default deployment templates are configurable, contact Zscaler Support before changing any settings to ensure an optimal configuration is made.

## Data Plane

The data plane is used to process and forward workload traffic to Internet & SaaS Public Service Edges or Private Service Edges and Private Access Public Service Edges or Private Service Edges. The data plane is composed of outbound connections from the service interface of each Cloud Connector.

- Internet Egress (Internet & SaaS)
- Private Apps (Private Access)

By default, each Cloud Connector tenant comes with a default rule to forward internet-bound traffic to Internet & SaaS using automatically selected gateways. Similar to the control plane, the Internet & SaaS Public Service Edges or Private Service Edges and Private Access Public Service Edges or Private Service Edges are selected using geolocation. It is possible to configure traffic forwarding rules to utilize specific Public Service Edges, Virtual Service Edges, or sub-clouds for various Cloud Connector groups and locations.

Workload traffic that is processed by traffic forwarding rules configured with the Internet & SaaS forwarding method uses the primary gateway as the active tunnel to the Internet & SaaS Public Service Edges or Private Service Edges and Private Access Public Service Edges or Private Service Edges. The default gateway configuration will fail-close, meaning that internet-bound traffic from workloads is dropped if none of the Cloud Connectors in the same group are able to establish connectivity to any of the Internet & SaaS Service Edges. Customers can change this configuration to fail-open, allowing workloads that are accessing the internet to continue doing so. The fail-open option means the egressing traffic is flowing through Zscaler for inspection and policy control.

In the event of a failed connection, Cloud Connector marks the secondary gateway as the active tunnel and forwards workload traffic via the secondary gateway to the Internet & SaaS Public Service Edges or Private Service Edges and Private Access Public Service Edges or Private Service Edges. When the primary gateway is healthy again, it is marked as active and Cloud Connector forwards new sessions to the Internet & SaaS Public Service Edges or Private Service Edges and Private Access Public Service Edges or Private Service Edges via the primary gateway tunnel.

Cloud Connector monitors the gateway connections to ensure the data path exists so that traffic can be inspected at the Internet & SaaS Public Service Edges or Private Service Edges and Private Access Public Service Edges or Private Service Edges. If the active tunnel forwarding workload traffic to the Internet & SaaS Public Service Edges or Private Service Edges and Private Access Public Service Edges or Private Service Edges fails, the Cloud Connector will fail over to the secondary tunnel in approximately 30 seconds.

By default, Cloud Connector automatically tries to connect to a tertiary Internet & SaaS Public Service Edge or Private Service Edge and Private Access Public Service Edge or Private Service Edge if the primary and secondary have failed. This is important to note because Cloud Connector is not limited to just the two Internet & SaaS Public Service Edges or Private Service Edges and Private Access Public Service Edges or Private Service Edges that are selected for tunneling.

To learn more about the Internet & SaaS Gateways, see [About Zscaler Internet Access Gateways](https://help.zscaler.com/cloud-branch-connector/about-zia-gateways).

Cloud Connectors that are enrolled with Private Access automatically establish a secure connection to an optimal Private Access Public Service Edge or Private Service Edge in the ZTE. This secure connection allows for the workloads accessing private applications to be securely tunneled to the Private Access Public Service Edge or Private Service Edge and allows the data path to be connected through App Connectors configured for the accessed application.

Cloud Connector, similar to Zscaler Client Connector, attempts to resolve the most optimal or nearest Private Access Public Service Edge or Private Service Edge for private application access.

## Zero Trust Exchange (ZTE)

Cloud Connector is a Zscaler purpose-built Zero Trust gateway to forward traffic to the ZTE. With over 150 global data centers, the Internet & SaaS and Private Access Public Service Edges enable deployments in almost any region with optimal connectivity.

Organizations can forward users, workload, and Internet of Things (IoT) devices to the ZTE using:

- Zscaler Client Connector
- Cloud Connector
- Branch Connector
- PAC Files (Internet & SaaS only)
- GRE Tunnels (Internet & SaaS only)
- IPSec Tunnels (Internet & SaaS only)

The ZTE HA is identical to the aforementioned forwarding methods.

## Cloud Providers

Zscaler uses the best practices for HA with cloud providers such as AWS and Azure. Regions and availability zones are HA aspects to take into consideration when deploying Cloud Connector.

- Regions
- Availability Zones

In most cases, Cloud Connector is deployed into groups or locations that serve the same internet egress. Whether centralized, using AWS Transit Gateway or Azure Virtual WAN Hub, or decentralized, where each Virtual Private Cloud (VPC) or Azure Virtual Network (VNet) has direct internet access, each region serves a number of different egress points for Cloud Connector and does not affect other regions even if there is a cloud provider outage.

Zscaler recommends deploying at least two Cloud Connectors per availability zone and across at least two availability zones. This takes into account HA without service interruption intra or inter availability zone per egress location.

Zscaler also recommends enabling AWS Gateway Load Balancer (GWLB) cross-zone load balancing for all production deployments. This setting ensures that GWLB VPC endpoints deployed across multiple availability zones can use Cloud Connector in all availability zones instead of only its own.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/understanding-namespaces","lastmod":"2026-11-01T23:53Z","nid":"1508906"} -->
## Understanding Namespaces

- Source: https://help.zscaler.com/zero-trust-cloud/understanding-namespaces
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Administration > Cloud Connector Partner Integrations > Understanding Namespaces
- Last modified: 2026-11-01T23:53Z
- Summary: Understanding Namespaces for Amazon Web Services and Microsoft Azure Accounts

User-defined tags and cloud-provider-defined attributes in security policies enable you to apply policies based on workload identities in a dynamic and granular fashion. The Zscaler service creates a mapping between the user-defined tags or cloud-provider-generated attributes and the workload IP address. This mapping is decentralized at the Zscaler Cloud Connector level. With no overlapping Classless Inter-Domain Routing (CIDR) blocks, Cloud Connector maps the IP address to a set of tags. When there are overlapping CIDR blocks, divide the set of maps into a subset. In a subset, every CIDR block is unique to the namespace. Mapping is simple in a deployment that has no overlapping IP addresses and with all of the virtual private clouds (VPCs) in the same account. It is challenging when VPCs are spread across multiple accounts and have overlapping IP addresses.

A namespace is a set of VPC endpoints in Amazon Web Services (AWS) or a set of virtual networks (VNets) in Microsoft Azure. The VPCs or VNets in a namespace should not have overlapping IP addresses. A namespace is used as an additional data point to differentiate between identical source IP addresses when the egress traffic reaches Cloud Connector.

## Namespaces in AWS Accounts

Namespaces allow you to:

- Provide deterministic mapping between tags and source IP addresses when there are overlapping IP addresses.
- Enable the decentralized deployment and inspection in overlapping IP address environments.
- Help apply policies based on user-defined tags in an environment with overlapping IP addresses.

In decentralized deployments, you can have overlapping CIDR blocks that all route to the same Cloud Connector where you assign the IP addresses to the Cloud Connector. Namespaces provide a way to associate the tag with the IP address using a VPC endpoint.

By default, if the user-defined namespace is not detected, all workloads are part of the default namespace. If you want to group VPCs and/or accounts in a namespace, you must assign the same namespace VPC tag for each VPC. The namespace tag key is named `zs:namespace` and has a value of `<namespace_value>`, where `<namespace_value>` is a string you choose.

Namespaces influence AWS accounts by:

- Grouping accounts and/or VPCs that do not have overlapping IP addresses and can communicate to the same set of Cloud Connectors.
- Creating mapping between workload tags and IP addresses even when duplicate IP addresses are detected between accounts and/or VPCs.
- Applying security policies based on tags even when the tags are associated with overlapping IP addresses.

See image.

In the diagram, there are three AWS accounts (Acct_1, Acct_2, and Acct_3). The VPC in Acct_1 has an overlapping IP address range with the VPC in Acct_2. The VPC in Acct_3 is the security VPC that has the Cloud Connectors deployed. You want to use the user-defined tags on workloads in Acct_1 and Acct_2 in Zscaler policies. They tag the Acct_1 VPC with `zs:namespace=project-green`. The AWS admin tags the Acct_2 VPC with `zs:namespace=project-blue`. The Zscaler discovery service reads the accounts and VPC tags to create the following mapping:

| **Endpoint ID** | **Namespace** |
| --- | --- |
| vpce-111 | project-blue |
| vpce-222 | project-blue |
| vpce-333 | project-green |
| vpce-444 | project-green |

The Zscaler discovery service also fetches the IP addresses and the associated tags to create the following mapping for the same IP address present in both VPCs:

| **IP Address** | **VPC Endpoint** | **Tag Index** | **Tag List** |
| --- | --- | --- | --- |
| 172.31.0.1 | vpce-111 | 172.31.0.1+project-green | Tag-A, Tag-B |
| 172.31.0.1 | vpce-444 | 172.31.0.1+project-blue | Tag-C, Tag-D |

## Namespaces in Azure Accounts

In Azure, the discovery service does not know which VNet peers to which Cloud Connector. For example, you have an IP address that sends a list of tags to Cloud Connector. If the discovery service discovers overlapping CIDR blocks within the IP addresses, a namespace must be created. The Cloud Connector requests that specific namespace to receive tags. The Zscaler service needs to maintain separate namespaces for the same subscription, so Cloud Connectors in the same subscription have different namespaces.

Although the location of the Cloud Connector is not an issue for the policy and logs, it is a problem for assigning tags. You can use the namespace assigned to each VNet to route the message from the publish-subscribe mechanism to the correct Cloud Connector. In the diagram below, you can assign `zs:namespace` to Yellow, which applies to the three VNets on the left, and assign `zs:namespace` to Green, which applies to the three VNets on the right. If the VNets are overlapping in different subscriptions, you can create a subscription group. Each subscription group must have a different credential (app registration).

See image.

When deploying applications in Azure, you can reuse the same CIDR block in a deployment. When using VPC peering, you cannot use endpoints to separate traffic. You must duplicate the Cloud Connector group stack.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/understanding-zero-trust-sd-wan-devices","lastmod":"2026-11-01T23:53Z","nid":"1468276"} -->
## Understanding Zero Trust SD-WAN Devices

- Source: https://help.zscaler.com/zero-trust-cloud/understanding-zero-trust-sd-wan-devices
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Zscaler Branch Connector Management > Deployment Management for Physical Devices > Understanding Zero Trust SD-WAN Devices
- Last modified: 2026-11-01T23:53Z
- Summary: Information on Zero Trust SD-WAN Devices for Zscaler Branch Connector.

Enabled by the Zscaler Zero Trust Exchange (ZTE), Zero Trust Software-Defined Wide Area Network (SD-WAN) Devices are hardware devices that use Zero Trust Branch Connectivity to simplify traffic forwarding to Zscaler services. The Zero Trust SD-WAN Device is deployed as a [Zscaler Branch Connector](https://help.zscaler.com/cloud-branch-connector/what-zscaler-branch-connector) virtual machine (VM). It supplies branches and data centers with fast and reliable access to the internet and private applications with a direct-to-cloud architecture.

Branch Connector eliminates the network attack surface by establishing direct branch-to-internet and branch-to-private-app connections using a full proxy architecture. It also simplifies branch communications by eliminating complex routing, virtual private networks (VPNs), and firewalls while allowing for flexible forwarding and simple policy management by using the proven Internet & SaaS (ZIA) and Private Access (ZPA) policy framework.

The Branch Connector forwards all branch communications directly to the ZTE, where you can apply [Internet & SaaS](https://help.zscaler.com/zia/policies) or [Private Access](https://help.zscaler.com/zpa/policies) policies for full security inspection and you can access identity-based control of branch and data center communications. The communications are then forwarded from the ZTE to any destination (the internet, private applications in a public cloud, on-premises data center, etc.).

You can deploy Zero Trust SD-WAN Devices in one of two modes: gateway or non-gateway (one-arm).

In gateway mode, the Zero Trust SD-WAN Device enables direct, secure access from your private network to other geographically distributed parts of your private network, cloud applications, and the internet over one or more internet service provider (ISP) connections. It can also dynamically determine the best quality link, forward specific traffic toward that link, and function as a local router. Local devices can communicate without an external router. You can also deploy the hardware device in gateway mode inside your local area network (LAN) while an existing device connects you to the internet through the wide area network (WAN).

In non-gateway (one-arm) mode, the Zero Trust SD-WAN Device does not connect directly to the internet service provider (ISP). Instead, the Zero Trust SD-WAN Device deploys in the internal network of the organization and provides access from your private network to other geographically distributed parts of your private network, cloud applications, and the internet. Non-gateway (one-arm) mode requires an external router.

## Zero Touch Provisioning

Zero Trust SD-WAN Devices are installed in your organization's on-premises locations and are loaded with the required deployment configurations using [Branch Configuration Templates](https://help.zscaler.com/cloud-branch-connector/about-branch-provisioning-template). You can stage the device configuration in the Zscaler Admin Console before a device is powered on and connected to the on-premises location. When your organization is ready for the ZTE to adopt the device, you’ll change the template status from Staged to Ready to Deploy. After an on-site technician powers up the device and provides it with network connectivity, the device software connects to the Zscaler cloud and authenticates itself. After the authentication is successful, the device is provided with its deployment configuration. This simplified method for loading the deployment configuration on on-premises devices is referred to as Zero Touch Provisioning.

To learn more, see[Deploying Zero Trust SD-WAN Devices](https://help.zscaler.com/cloud-branch-connector/deploying-zero-trust-sd-wan-devices)and [Installing Zero Trust SD-WAN Devices](https://help.zscaler.com/cloud-branch-connector/installing-zero-trust-sd-wan-devices).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/using-sublocation-scopes-group-cloud-connector-workloads-amazon-web","lastmod":"2026-11-01T23:53Z","nid":"1532858"} -->
## Using Sublocation Scopes to Group Cloud Connector Workloads in Amazon Web Services

- Source: https://help.zscaler.com/zero-trust-cloud/using-sublocation-scopes-group-cloud-connector-workloads-amazon-web
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Deployment Management for Virtual Devices > Cloud Connector Deployment Management > Cloud Connector Deployment Management for AWS > Using Sublocation Scopes to Group Cloud Connector Workloads in Amazon Web Services
- Last modified: 2026-11-01T23:53Z
- Summary: Information about how sublocation scopes can group workload traffic in Amazon Web Services (AWS).

This feature is supported in Cloud Connector deployments for locations that are automatically created when Cloud Connectors are deployed with Amazon Web Services (AWS). These locations have the Workload traffic location type.

In a Cloud Connector deployment with Amazon Web Services (AWS), the [location](https://help.zscaler.com/cloud-branch-connector/about-locations) is associated with the Virtual Private Cloud (VPC) containing the Cloud Connectors. [Sublocations](https://help.zscaler.com/zia/understanding-sublocations) are subsets of workloads within a location group, based on criteria such as scope. Sublocation scope types include VPC Endpoint, VPC, Account, and Namespace. You configure sublocations in the Zscaler Admin Console.

All scope types except VPC Endpoint require integration with [workload discovery](https://help.zscaler.com/cloud-branch-connector/configuring-workload-discovery-workloads-amazon-web-services).

Sublocation scopes allow the following:

- You can apply different Cloud Connector and Internet & SaaS (ZIA) policies to workload traffic based on VPC endpoint, VPC, account, or namespace.
- Policy lookup can distinguish between multiple workloads with the same source IP address space, even without using [namespace tags](https://help.zscaler.com/cloud-branch-connector/understanding-namespaces-amazon-web-services-and-microsoft-azure-accounts) or [workload discovery](https://help.zscaler.com/cloud-branch-connector/configuring-workload-discovery-workloads-amazon-web-services).
- Logs can indicate the correct workload when there are overlapping IP address spaces.

Authentication is not supported for the sublocations described in this article. For more information, see the Gateway Options information in [Configuring Sublocations](https://help.zscaler.com/zia/configuring-sublocations).

## Scope Types

All sublocations within a location must be configured with the same criteria: scope only, combination of scope and IP address range, or IP address range only. Combining a scope and IP address range allows you to create more granular sublocations. You can add a scope to existing sublocations that already have a defined IP address range.

- VPC Endpoint
- VPC
- Account
- Namespace

## Sublocation Traffic Mapping

- Deployment Models
- Scope Topology Diagrams
- Traffic Mapping Order

The Zscaler service first attempts to map workload traffic to a sublocation that has a defined scope. If there is no match, the Zscaler service attempts to map workload traffic to a sublocation that has only a defined IP address range. If there is still no match, workload traffic is sent to the default **other**sublocation, which the Zscaler service automatically creates when a location is added. For information about the **other**sublocation, see [Understanding Sublocations](https://help.zscaler.com/zia/understanding-sublocations).

There are three VPC endpoint deployment models:

- Distributed
- Centralized
- Combined

In a distributed VPC endpoint deployment, there is one VPC endpoint or a set of VPC endpoints for each subnet or VPC. In this model, workload traffic from each VPC or subnet is routed to Cloud Connectors through a unique VPC endpoint. Duplicate or overlapping IP address ranges within the same namespace are supported if the subnets are in separate sublocations. For examples of the distributed model, see the VPC Endpoint Scope, VPC Scope, Account Scope, and Namespace Scope topology diagrams in the next section.

In a centralized VPC endpoint deployment, all workload traffic from subnets in spoke VPCs is routed to Cloud Connectors through a shared set of VPC endpoints in a hub VPC. You can create a sublocation based on the hub VPC, but not the spoke VPCs. However, you can use both the hub VPC and a unique IP address range to create more granular sublocations. For an example of the centralized model, see the Scope and IP Address Range topology diagram in the next section.

In a combined VPC endpoint deployment, there are sets of distributed VPCs and sets of centralized VPCs. Duplicate or overlapping IP address ranges are supported across centralized and distributed VPCs and within distributed VPCs. The combined model is the most common model, especially in large deployments.

The following topology diagrams illustrate how scope criteria can group workload traffic.

- VPC Endpoint Scope
- VPC Scope
- Account Scope
- Namespace Scope
- Scope and IP Address Range

In this example, workload traffic in VPC-A is grouped into two sublocations at the VPC endpoint level. You can apply different policies to workload traffic behind VPCE 1 and VPCE 2.

The workload traffic originating from Private Subnet-1 is routed to VPCE 1. The workload traffic originating from Private Subnet-2 is routed to VPCE 2. VCPE 1 and VCPE 2 are connected via AWS Private Link to the Gateway Load Balancer (GWLB), which passes traffic to and from the Cloud Connectors in the security VPC.

[Image: Topology diagram showing how workload traffic is grouped into two sublocations at the VPC endpoint level]

In this example, workload traffic is grouped into two sublocations at the VPC level. This allows duplicate IP address spaces (172.16.1.0/24 and 172.16.2.0/24) across VPCs because the VPCs are in different sublocations. You can apply different policies to workload traffic behind VPC-A and VPC-B.

The workload traffic originating from Private Subnet-1 and Private Subnet-2 in Availability Zone A is routed to VPCE 1. The workload traffic originating from Private Subnet-1 and Private Subnet-3 in Availability Zone B is routed to VPCE 2. VCPE 1 and VCPE 2 are connected via AWS Private Link to the GWLB, which passes traffic to and from the Cloud Connectors in the security VPC.

[Image: Topology diagram showing how workload traffic is grouped into two sublocations at the VPC level]

In this example, workload traffic is grouped into two sublocations at the AWS account level. This allows duplicate IP address spaces (172.16.1.0/24, 172.16.2.0/24, 172.16.3.0/24, and 172.16.4.0/24) across accounts because the accounts are in different sublocations. You can apply different policies to workload traffic behind VPC-A and VPC-B (Account 1) and workload traffic behind VPC-C and VPC-D (Account 2).

The workload traffic originating from Private Subnet-1 and Private Subnet-2 in Availability Zone A is routed to VPCE 1. The workload traffic originating from Private Subnet-1 and Private Subnet-3 in Availability Zone B is routed to VPCE 2. VCPE 1 and VCPE 2 are connected via AWS Private Link to the GWLB, which passes traffic to and from the Cloud Connectors in the security VPC.

[Image: Topology diagram showing how workload traffic is grouped into two sublocations at the AWS account level]

You can apply a [namespace tag](https://help.zscaler.com/cloud-branch-connector/understanding-namespaces-amazon-web-services-and-microsoft-azure-accounts) to one or more VPCs. The workload discovery service discovers the namespace tag and assigns all the workloads in those VPCs to the namespace. Use the Namespace scope to map all workload traffic behind the VPC endpoints in one or more namespaces to a sublocation.

- Namespace Scope Localized to Account Boundaries
- Namespace Scope Across Account Boundaries

In this example, workload traffic is grouped into two sublocations at the namespace level, where two namespaces are under a single account. This allows a duplicate IP address space (172.16.1.0/24) across Account 1 and Account 2, but not within an account. You can apply different policies to the workload traffic behind VPC-A, VPC-B, and VPC-C (Namespace Blue and Namespace Red) and the workload traffic behind VPC-D and VPC-E (Namespace Green).

The workload traffic originating from each private subnet is routed to a unique VPC endpoint. The VPC endpoints are connected via AWS Private Link to the GWLB, which passes traffic to and from the Cloud Connectors in the security VPC.

[Image: Topology diagram showing how workload traffic is grouped into two sublocations at the namespace level, where two namespaces are under a single account]

In this example, workload traffic is grouped into two sublocations at the namespace level, where one namespace spans two accounts. This allows duplicate IP address spaces (172.16.2.0/24) within an account, because VPC-B and VPC-C are in different namespaces. You can apply different policies to the workload traffic behind VPC-A and VPC-B, and the workload traffic behind VPC-C, VPC-D, and VPC-E. The same namespace tag is assigned to the VPCs in each namespace.

The workload traffic originating from each private subnet is routed to a unique VPC endpoint. The VPC endpoints are connected via AWS Private Link to the GWLB, which passes traffic to and from the Cloud Connectors in the security VPC.

[Image: Topology diagram showing how workload traffic is grouped into two sublocations at the namespace level, where one namespace spans two accounts]

You can create a sublocation using both a scope and IP address range for greater granularity. This is particularly useful in a centralized deployment model, where you cannot create a sublocation based on spoke VPCs because they do not have VPC IDs or endpoints.

In this example, Sublocation 1a has the hub VPC and 172.16.1.0/16 as criteria. Sublocation 1b has the hub VPC and 172.16.2.0/16 as criteria. Sublocation 1c has the hub VPC and 172.16.3.0/16 as criteria. You can apply different policies to workload traffic originating from each of these sublocations.

The workload traffic originating from each spoke VPC is routed to the AWS Transit Gateway, which passes traffic to and from the endpoint subnet in the hub VPC. The VPC endpoint is connected via AWS Private Link to the GWLB, which passes traffic to and from the Cloud Connectors in the security VPC.

[Image: Topology diagram showing how workload traffic grouped by VPC in one sublocation is subdivided into other sublocations based on IP address range]

Use the VPC Endpoint scope to map workloads from one or more VPC endpoints to a sublocation. This is the only scope that does not use the workload discovery service, so the **VPC Endpoint** field is not dynamically populated. You must copy and paste each VPC endpoint ID into the field. In this example from the Zscaler Admin Console, one VPC endpoint is mapped to the sublocation. Logs show the workload traffic originating from this VPC endpoint.

[Image: Internet & SaaS configuration of VPC Endpoint scope where one VPC endpoint is mapped to a sublocation" title]

Use the VPC scope to map workloads in one or more VPCs to a sublocation. This scope uses the [workload discovery service](https://help.zscaler.com/cloud-branch-connector/configuring-workload-discovery-workloads-amazon-web-services), which discovers the workloads in the selected accounts and dynamically populates the **VPC**drop-down menu. In this example from the Zscaler Admin Console, two VPCs are mapped to the sublocation. Logs show the workload traffic originating from these VPCs.

The **Account** drop-down menu is a filter for the **VPC**list, not a scope.

[Image: Internet & SaaS configuration of VPC scope where two VPCs are mapped to a sublocation]

Use the Account scope to map workloads in one or more AWS accounts to a sublocation. The **Account**drop-down menu lists the accounts you can select. This scope uses the workload discovery service, which discovers the workloads in the selected account or accounts. In this example from the Zscaler Admin Console, all VPC endpoints in this account are mapped to the sublocation. Logs shows the workload traffic originating from this account.

[Image: Internet & SaaS configuration of Account scope where one account is mapped to a sublocation]

Use the Namespace scope to map workloads in one or more [namespaces](https://help.zscaler.com/cloud-branch-connector/understanding-namespaces-amazon-web-services-and-microsoft-azure-accounts) to a sublocation. This scope uses the workload discovery service, which discovers the workloads in the selected accounts and dynamically populates the **Namespace**drop-down menu. In this example from the Zscaler Admin Console, all VPC endpoints in the selected namespace are mapped to the sublocation. Logs show the workload traffic originating from this namespace.

The **Account** drop-down menu is a filter for the **Namespace**list, not a scope.

[Image: Internet & SaaS configuration of Namespace scope where a namespace with two accounts mapped to a sublocation]

In the [Amazon VPC console](https://console.aws.amazon.com/vpc/), you must assign the same namespace tag to each VPC in a namespace.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/what-zero-trust-gateways","lastmod":"2026-11-01T23:53Z","nid":"1517756"} -->
## What Are Zero Trust Gateways?

- Source: https://help.zscaler.com/zero-trust-cloud/what-zero-trust-gateways
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Zero Trust Gateway Management > What Are Zero Trust Gateways?
- Last modified: 2026-11-01T23:53Z
- Summary: Introductory information, key features, and benefits of Zero Trust Gateways accessible in the Zscaler Admin Console.

The Zscaler Zero Trust Gateway service transforms how you can secure your workloads and workload traffic deployed in public clouds. Built on the Zscaler Zero Trust Exchange (ZTE), the Zero Trust Gateway service simplifies cloud workload security for enterprises.

## Key Features and Benefits

The Zero Trust Gateway service shares [Zscaler Cloud Connector features and benefits](https://help.zscaler.com/cloud-branch-connector/what-zscaler-cloud-connector). Additionally, the following are key Zero Trust Gateway features and benefits:

- Simplifies operations to install, configure, and manage workload security as a SaaS service in Amazon Web Services (AWS) and Google Cloud Platform (GCP).
- Offers consistent and comprehensive threat and data protection with common security policies and cloud-scale TLS inspection.
- Reduces the attack surface by connecting applications instead of networks and applying least-privilege access.
- Supports cloud-to-cloud, cloud-to-data-center, and region-to-region implementations, which reduces operational costs and complexity.
- Secures workload connections to the internet or to other workloads.
- Reduces cost by allowing traffic forwarded to the Zero Trust Gateway to egress to the internet and/or Zscaler cloud from the Zscaler AWS or GCP account.
- Eliminates lateral threat movement.
- Provides Layer 4 security controls for east-west traffic, including subnet-to-subnet and virtual private cloud (VPC)-to-VPC communication in the AWS cloud. Layer 4 controls are also used to secure ingress traffic for public applications hosted in AWS, offering a unified view for flow-level visibility and control and the added flexibility of leveraging tag-based policies for Layer 4 controls.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/what-zscaler-branch-connector","lastmod":"2026-11-01T23:53Z","nid":"1420881"} -->
## What Is Zscaler Branch Connector?

- Source: https://help.zscaler.com/zero-trust-cloud/what-zscaler-branch-connector
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Zscaler Branch Connector Management > What Is Zscaler Branch Connector?
- Last modified: 2026-11-01T23:53Z
- Summary: Introductory information about Zscaler Branch Connector.

Enabled by the Zscaler Zero Trust Exchange (ZTE), Zscaler Branch Connector is a virtual machine (VM) that simplifies traffic forwarding to Zscaler services. The Zero Trust Software-Defined Wide Area Network (SD-WAN) is deployed as a Branch Connector VM. This supplies branches and data centers with fast and reliable access to the internet and private applications with a direct-to-cloud architecture. Branch Connector works similarly to [Zscaler Cloud Connector](https://help.zscaler.com/cloud-branch-connector/what-zscaler-cloud-connector).

Branch Connector eliminates the network attack surface by establishing direct branch-to-internet and branch-to-private app connections using a full proxy architecture. It also simplifies branch communications by eliminating complex routing, virtual private networks (VPNs), and firewalls while allowing for flexible forwarding and simple policy management by using the proven Internet & SaaS (ZIA) and Private Access (ZPA) policy framework.

All branch communications forward directly to the ZTE, where [Internet & SaaS](https://help.zscaler.com/zia/policies) or [Private Access](https://help.zscaler.com/zpa/policies) policies can be applied for full security inspection and access identity-based control of branch and data center communications. The communications then forward from the ZTE to any destination (i.e., internet, private applications in a public cloud, on-premises data center, etc.).

## Key Features and Benefits

The following are some key Branch Connector features and benefits:

- Enables Zero Trust Everywhere: All users, devices, servers, Internet of Things (IoT), and Operational Technology (OT) have explicit access based on continuous identity and context validation.
- Secure Connection: Every office, data center, multi-cloud, and SaaS is secured by building a foundation for connectivity that enables east-west segmentation, preventing lateral threat movement.
- Eliminates Attack Surfaces: Branches and data centers connect directly to each other through the ZTE, independent of their underlying corporate network, VPN, or WAN.
- Purpose-Built, Multi-tenant Proxy Architecture: Holds, inspects, and enforces policy.
- Delivers High-Performance Inspection: Uses a single scan multi-action (SSMA) architecture for an optimal inspection.
- Enforcement: Finely grained forwarding policies for internet and non-internet traffic using Internet & SaaS or Private Access.
- Standardized Policy: Policy across branches, data centers, and multi-cloud locations is uniform. This includes policy management, traffic monitoring, and log tracking.
- Device Classification: Allows deeper visibility into behavior for better access control policies.
- Predefined Template: Allows zero touch provisioning and automated deployment.
- Granular Forwarding Policy: Applies to internet and private application traffic to Internet & SaaS, Private Access, or Direct (bypassing Zscaler services).
- Service Continuity: Ensures high availability automatic failover with N+1 redundancy.
- Centralized Visibility and Granular Logging: Provides device health and traffic monitoring information.
- Identity and Application-Based Communication: Users move from network-based VPN connectivity to zero trust security.
- Elimination of Legacy Products: Eliminates legacy castle-and-moat architecture without compromising security, so there is no need for legacy products (i.e., Squid proxies, NAT gateways, IPSs, etc.).
- Scalable Connectivity: Accessible where necessary with centralized, automated policy management to simplify branch and data center communications.

## Zero Trust SD-WAN and Branch Connector Use Cases

The Zero Trust SD-WAN and Branch Connector capabilities are as follows:

- Direct Internet Access Enablement for Branches: As organizations migrate apps to the cloud, on-premises networking and security model effectiveness decreases. Zero Trust SD-WAN is a purpose-built solution for branch transformation, where branches communicate with any destination independent from their underlying network.
- Site-to-Site VPN Replacement: Connect your branches directly to private applications without extending your WAN or relying on VPNs. Applications are hidden behind the branches and access is restricted via the ZTE to a set of named entities. Identity, context, and policy adherence are verified before access is allowed, eliminating the risk of lateral movement.
- Shadow IoT and OT Visibility: Undiscoverable devices create blind spots when they connect to office networks, causing vulnerability and a broader attack surface. Zero Trust SD-WAN identifies and classifies devices to give IT teams visibility into behavior for better access control policies.
- Zero Trust for Server, IoT, and OT Connectivity: To maximize production uptime and avoid disruptions, IoT and OT assets must be regularly accessed by employees and third-party vendors. Zero Trust SD-WAN for IoT provides remote desktop access to internal Remote Desktop Protocol (RDP) and Secure Shell (SSH) target systems without installing a client on a device.
- Mergers and Acquisitions: With Zero Trust SD-WAN, networks can remain separate and branch locations can connect to private applications without disruption.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/what-zscaler-client-connector-vdi","lastmod":"2026-11-01T23:53Z","nid":"1472116"} -->
## What Is Zscaler Client Connector for VDI?

- Source: https://help.zscaler.com/zero-trust-cloud/what-zscaler-client-connector-vdi
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Zscaler Client Connector for VDI Management > What Is Zscaler Client Connector for VDI?
- Last modified: 2026-11-01T23:53Z
- Summary: Information on Zscaler Client Connector for VDI, its key features, and how it works.

Zscaler Client Connector for Virtual Desktop Infrastructure (VDI) is a lightweight Windows application that runs in the user space of the VDI session host to authenticate multiple concurrent users, forward traffic to Zscaler Cloud Connector or Zscaler Branch Connector, and exchange user context within the Cloud Connector or Branch Connector. Using Zscaler Client Connector for VDI, users can get all the benefits of the Zscaler service through granular, policy-based access to internet resources from a single point.

To enable this feature, contact Zscaler Support.

Zscaler Client Connector for VDI allows you to inspect all ports and protocols for multisession, nonpersistent VDI deployments in the public cloud and on-premises data centers. Organizations can apply granular threat- and data-protection policies per individual user session, enabling those organizations to maintain common security policies across all environments.

[Image: The Zscaler Client Connector for VDI window with Connectivity, Diagnostics, and More on the left-side navigation and a table showing the Username, Service Status, Authentication Status, Server IP, Client IP, Total Bytes Sent, and Total Bytes Received.]

## Key Features and Benefits

The following are some key features and benefits of Zscaler Client Connector for VDI:

- **Authentication**: The application supports all authentication mechanisms that the Zscaler service supports.
- **Remote access**: Users can connect to their VDI from any location.
- **Reduced cost**: With processing established on the server, hardware requirements for end devices are significantly lower.
- **Security**: In a VDI environment, data lives on the server rather than on your end device.
- **Centralized management**: The centralized format of Zscaler Client Connector for VDI allows for easy updates or configurations for all the virtual desktops within a system.

## How Does Zscaler Client Connector for VDI Work?

The Zscaler Client Connector for VDI tunnels traffic through UDP encapsulation to the local Cloud Connector or Branch Connector. These tunnels carry both user session data in the payload and user context information in the UDP header. These tunnels are stateless, which ensures that they can fail over to other active appliances if a Cloud Connector or Branch Connector fails.

Enabled by the Zero Trust Exchange (ZTE), Cloud Connector and Branch Connector are virtual machines (VMs) that act as a gateway to the Zscaler cloud to simplify traffic forwarding to Zscaler services. Cloud & Branch Connectors automatically establish geographically proximate tunnels to Internet & SaaS. Cloud Connectors operate within cloud environments such as Amazon Web Services (AWS), Microsoft Azure, and the Google Cloud Platform (GCP). Branch Connectors operate within on-premises environments such as VMware ESXi or Linux KVM. The Cloud & Branch Connectors aggregate tunnels from Zscaler Client Connector for VDI in addition to encrypting and forwarding traffic to the ZTE. Zscaler Client Connector for VDI connects on TCP 443 for the control channel, UDP 7443 for the data channel to Cloud Connector, and TCP 9090 on the Cloud Connector service endpoint for policy updates.

Common services handle operations such as the Active Directory service, DHCP, DNS resolution, Network Location Awareness (NLASVC), Server Message Block (SMB) file download, Windows DNS client API, Windows Ping, Winlogon service, and traffic from a Windows service. For example, DNS queries are resolved using the DNS client service. Similarly, SMB traffic is handled by the LanmanWorkstation service, where services run under the `NT AUTHORITY\SYSTEM` user account. When the Zscaler driver intercepts the connections that these services initiate, Windows gives the Zscaler driver `NT AUTHORITY\SYSTEM` as the user ID for these services.

[Image: How Zscaler Client Connector for VDI works]

Zscaler Client Connector for VDI has multiple ways in which to establish tunnels for routing and forwarding to the Cloud Connector or Branch Connector. Its key components are:

- Point-to-Point (P2P) Tunneling Mode: The P2P Tunneling Mode is the default mode of operation for Zscaler Client Connector for VDI and Cloud & Branch Connector tunnels. With P2P, the tunnel is created with a fixed destination IP address and destination port. The source port is copied from the inner header. Use this mode if you want to have fixed IP addresses for outer header tunnels and destination IP addresses must be known. As part of the Zscaler Client Connector for VDI deployment, a Zscaler anycast Global VIP address (185.46.212.80) is used as the destination IP address for the outer header. You must configure routing so that the next hop for the Global VIP points to the Cloud Connector or Branch Connector load balancer. Additionally, when Zscaler Client Connector for VDI is enabled, enter the client IP address as the session persistence setting for the Cloud Connector load balancer in Azure. If you deploy Zscaler Client Connector for VDI with Private Access, Zscaler recommends using P2P and 2-tuple tunneling. To learn more, see [Step-by-Step Configuration Guide for Zscaler Client Connector for VDI](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-vdi-agent).
- Any-to-Any (A2A) Tunneling Mode: The A2A Tunneling Mode copies the inner header of the packet to the outer encapsulated header. This mode provides better load balancing because the source IP address, destination IP address, and source port are preserved. This results in better traffic distribution across multiple Cloud & Branch Connector instances. A2A Tunneling Mode requires a default route pointing to the Cloud Connector or Branch Connector because the destination IP addresses are dynamic. A2A Tunneling Mode is not recommended and is being deprecated.
- Maximum Transmission Unit (MTU): By default, Zscaler Client Connector for VDI uses an MTU of 1,400 bytes. If any additional tunneling or encapsulation is present within your network, you might need to adjust the MTU from within the [VDI Template](https://help.zscaler.com/cloud-branch-connector/about-vdi-agent-templates).
- SSL Inspection: If you want to provide SSL Inspection, ensure that Zscaler Client Connector for VDI has automatically installed the Zscaler[Root CA certificate](https://help.zscaler.com/zia/about-root-certificates) or your organization’s Root CA certificate.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/what-zscaler-cloud-connector","lastmod":"2026-11-05T20:40Z","nid":"1420636"} -->
## What Is Zscaler Cloud Connector?

- Source: https://help.zscaler.com/zero-trust-cloud/what-zscaler-cloud-connector
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > What Is Zscaler Cloud Connector?
- Last modified: 2026-11-05T20:40Z
- Summary: Introductory information about Zscaler Cloud Connector.

Enabled by the Zero Trust Exchange (ZTE), Zscaler Cloud Connector is a virtual machine (VM) that simplifies traffic forwarding to Zscaler services. It extends the capabilities of Internet & SaaS (ZIA) and Private Access (ZPA) to cloud-native workloads, which allows enterprises to secure cloud workload communications over any network.

The ZTE enables workloads to communicate with each other and have a granular security policy applied. The communication might be from private workloads (e.g., Infrastructure as a Service (IaaS), physical data center) to public workloads (e.g., SaaS/internet), or between private workloads (e.g., IaaS to IaaS, IaaS to physical data center).

Internet & SaaS is used when a private workload is communicating to a public workload through the ZTE. Private Access is used when two private workloads are communicating with each other through the ZTE. To learn more, see the [Step-by-Step Configuration Guide for Zscaler Cloud Connector](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-cloud-connector).

## Key Features and Benefits

The following are some key Cloud Connector features and benefits:

- **Security**: Secures all inbound and outbound traffic to the internet. The security capabilities that are available through the Internet & SaaS platform for server internet access are Secure Sockets Layer (SSL), Intrusion Prevention System (IPS), Firewall, Data Loss Prevention (DLP), etc.
- **Connectivity**: Provides seamless connectivity from private or public cloud applications to the internet.
- Performance: Ensures better end-user experience and application performance by peering into relationships with SaaS providers (e.g., Microsoft Office 365, Amazon Web Services (AWS), and Microsoft Azure).
- **Reduces Cost**: Consolidates multiple products (e.g., Squid proxies, firewalls, third-party NAT appliances, URL filtering, etc.) into a single solution. Additionally, the same policy applied to user traffic can be applied across the cloud infrastructure.
- **Highly Scalable**: Eases implementation across 1,000 service accounts in public clouds and a single solution scales to connect 10K+ server environments in public clouds (AWS, Azure, etc.).
- **Ease of Deployment**: Fully orchestrated deployment for AWS and Azure using Terraform, CloudFormation, and Azure Resource Manager.
- **Real-Time Visibility**: Dashboards and Insights provide unparalleled visibility into your users and applications, and the health of your organization’s applications and servers.
- **Mergers and Acquisitions**: Securely integrates public cloud accounts/networks with the same consistent security controls and enables connectivity between merged/acquired organizations' cloud networks without having to directly connect them or worry about overlapping IP space.
- **Layer 4 Security Controls**: Cloud Connector provides Layer 4 security controls for east-west traffic, including subnet-to-subnet and virtual private cloud (VPC)-to-VPC communication across cloud providers such as AWS, Azure, and Google Cloud Platform (GCP). Layer 4 controls are also used to secure ingress traffic for public applications hosted in AWS, Azure, and GCP. This arrangement offers a unified view for flow-level visibility and control and the added flexibility of leveraging tag-based policies for Layer 4 controls.

In the realm of networking, organizations ask how to secure traffic entering the cloud, but not traffic that is within, exiting, or transitioning between clouds. As users operate between multiple service providers, this attack surface grows. Cloud Connector enables the following use cases:

- **Workloads-to-Internet Communication**: Applications might need to access an internet or SaaS destination (third-party APIs, software updates, etc.) with a scalable, reliable security solution that inspects all transactions, applying advanced threat prevention and DLP controls.
- **Workload-to-Workload Communication**: Public clouds and corporate data centers for multi-cloud or hybrid cloud connectivity deliver better security and a simplified operational model compared to traditional solutions like proxies, virtual firewalls, Intrusion Detection System (IDS), and IPS.

The following image demonstrates how Cloud Connector functions with AWS, Azure, and GCP.

[Image: The functionality of Cloud Connector with AWS, Azure, and GCP]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/what-zscaler-zero-trust-sd-wan","lastmod":"2026-11-03T15:52Z","nid":"1478621"} -->
## What Is Zscaler Zero Trust SD-WAN?

- Source: https://help.zscaler.com/zero-trust-cloud/what-zscaler-zero-trust-sd-wan
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Zscaler Branch Connector Management > Deployment Management for Physical Devices > What Is Zscaler Zero Trust SD-WAN?
- Last modified: 2026-11-03T15:52Z
- Summary: Information about Zscaler Zero Trust SD-WAN

Zscaler Zero Trust SD-WAN (software-defined wide area network) provides branches, on-premises data centers, and public clouds with fast and reliable access to the internet and private applications with a direct-to-cloud architecture that features strong security and operational simplicity. The solution eliminates lateral threat movement by connecting users and IoT/OT devices to applications through the Zscaler Zero Trust Exchange (ZTE).

Zscaler Zero Trust Cloud traffic is securely forwarded directly to the ZTE, where you can apply Internet & SaaS (ZIA) or Private Access (ZPA) policies depending on traffic destination for full security inspection and access identity-based control of cloud, branch, and data center communications.

By combining lightweight virtual machines or plug-and-play devices with the ZTE, Zero Trust SD-WAN provides secure inbound and outbound zero trust networking for sites and clouds, without overlay routing, additional firewall appliances, or policy inconsistencies. Fully integrated with the ZTE, Zero Trust SD-WAN enables robust security and simplifies branch network management.

To learn more, see [What Is Zscaler Branch Connector?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-branch-connector), [What Is Zscaler Cloud Connector?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-cloud-connector), [What Is Zscaler Private Access?](https://help.zscaler.com/zpa/what-zscaler-private-access), [Understanding the ZIA Cloud Architecture](https://help.zscaler.com/zia/understanding-zscaler-cloud-architecture), and [Understanding Zero Trust Branch Devices](https://help.zscaler.com/cloud-branch-connector/understanding-zero-trust-branch-devices).

## Key Features and Benefits

The following are some key Zero Trust SD-WAN features and benefits:

- Enables zero trust everywhere for all users, devices, servers, and IoT/OT, regardless of location or cloud.
- Improves application performance by replacing complex site-to-site VPNs with a simple direct-to-cloud architecture that improves application performance and increases productivity.
- Minimizes the internet attack surface by placing private applications behind the ZTE, where they can’t be discovered or attacked from the internet.
- Prevents lateral threat movement by connecting directly to applications, not the network.
- Enables organizations to discover and classify shadow IoT devices with automatic device classification based on traffic profiles.
- Simplifies secure access to OT resources with clientless browser-based access to SSH/RDP/VNC ports on OT assets.
- Enforces finely grained forwarding policies for internet and non-internet traffic using Internet & SaaS or Private Access.
- Introduces plug-and-play deployment with zero touch provisioning, which simplifies deployment and reduces time to integration.

## Zero Trust SD-WAN Use Cases

The following are some key Zero Trust SD-WAN use cases:

- Site-to-site VPN replacement: Connect branches directly to private applications in data centers or public clouds without extending your WAN or relying on VPNs, both of which increase a network’s attack surface. Applications are hidden from discovery behind the branches, and access is restricted via the ZTE to a set of named entities. Identity, context, and policy adherence of the specified participants are all verified before access is allowed, prohibiting lateral movement elsewhere in the network.
- Mergers and acquisitions: Merging multiple networks can be challenging and time-consuming. Problems range from overlapping IP addresses and routing issues to increased security risk from an enlarged network attack surface. With Zero Trust SD-WAN, networks can remain separate, and branch locations or clouds in one environment can quickly connect to private applications in another, without disruption.
- Direct internet access enablement for branches: On-premises networking and security models become less effective as organizations migrate their apps to the public cloud and build cloud-native apps. Zero Trust SD-WAN is a purpose-built solution for branch transformation, ushering in a new model that enables branches to communicate with any destination securely and independently from the underlying network.
- Zero Trust for server, IoT/OT connectivity: Employees and third-party vendors must regularly access IoT/OT assets to maximize production uptime and avoid disruptions from equipment and process failures. Zero Trust SD-WAN for IoT/OT and Privileged Remote Access (PRA) provide fully isolated, clientless remote desktop access to Remote Desktop Protocol (RDP) and Secure Shell (SSH) Protocol target systems—without having to install a client on devices using jump hosts and legacy VPNs.
- Shadow IoT/OT discovery and visibility: IT teams face blind spots when unsanctioned, undiscoverable devices connect to branch office networks, increasing device vulnerability, and creating a broader attack surface. Zscaler identifies and classifies devices to give IT teams deeper visibility into behavior for better access control policies.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/zero-trust-branch-connectivity-zscaler-branch-connector","lastmod":"2026-11-01T23:53Z","nid":"1514256"} -->
## Zero Trust Branch Connectivity with Zscaler Branch Connector

- Source: https://help.zscaler.com/zero-trust-cloud/zero-trust-branch-connectivity-zscaler-branch-connector
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Zscaler Branch Connector Management > Zero Trust Branch Connectivity with Zscaler Branch Connector
- Last modified: 2026-11-01T23:53Z
- Summary: The Zero Trust Branch Connectivity with Zscaler Branch Connector reference architecture guide that steers you through the architecture process, and provides technical deep dives into specific platform functionality and integrations.

The Zscaler Reference Architecture series delivers best practices based on real-world deployments. The recommendations in this series were developed by Zscaler's transformation experts from across the company. This guide will steer you through the architecture process and provide technical deep dives into specific platform functionality and integrations. The Zscaler Reference Architecture series is designed to be modular, so this guide will show you how to configure a different aspect of the platform in order to allow you meet your specific policy goals.

Zscaler Branch Connector takes a different approach to the SD-WAN model. It starts with a Zero Trust approach to the branch office, using the Secure Access Service Edge (SASE) model. Leveraging both Zscaler Internet Access (ZIA) and Zscaler Private Access (ZPA), users gain access to applications, not networks. Designed to be deployed anywhere, from small branches to large campuses and data centers, Zscaler Branch Connector simplifies connecting your devices with the Zscaler cloud, the Zero Trust Exchange (ZTE).

[Zero Trust Branch Connectivity with Zscaler Branch Connector](https://help.zscaler.com/downloads/cloud-branch-connector/reference-architecture/zero-trust-branch-connectivity-zscaler-branch-connector/Zero-Trust-Branch-Connectivity-with-Zscaler-Branch-Connector.pdf) [Download PDF](https://help.zscaler.com/downloads/cloud-branch-connector/reference-architecture/zero-trust-branch-connectivity-zscaler-branch-connector/Zero-Trust-Branch-Connectivity-with-Zscaler-Branch-Connector.pdf)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/zero-trust-sd-wan-devices-wall-and-rack-mount-instruction-manual","lastmod":"2026-11-01T23:53Z","nid":"1478656"} -->
## Zero Trust SD-WAN Devices Wall and Rack Mount Instruction Manual

- Source: https://help.zscaler.com/zero-trust-cloud/zero-trust-sd-wan-devices-wall-and-rack-mount-instruction-manual
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Zscaler Branch Connector Management > Deployment Management for Physical Devices > Zero Trust SD-WAN Devices Wall and Rack Mount Instruction Manual
- Last modified: 2026-11-01T23:53Z
- Summary: Instructions for wall and rack mounting the Zero Trust SD-WAN Devices.

After you receive the Zscaler Branch Connector hardware, you can mount the [Zero Trust SD-WAN Device](https://help.zscaler.com/cloud-branch-connector/understanding-zero-trust-sd-wan-devices) in the following locations:

- The Zero Trust SD-WAN Device 400: Rack posts using the rack mount kit.
- The Zero Trust SD-WAN Device 600: Rack posts using the rack mount kit.
- The Zero Trust SD-WAN Device 800:
  - Rack posts using the rack mount kit.
  - The wall, using the wall mount kit. Only available upon request.

Mounting on a rack or wall optimizes your workspace and makes your Zero Trust SD-WAN Device more accessible for[installation](https://help.zscaler.com/cloud-branch-connector/installing-zero-trust-sd-wan-devices)and[deployment](https://help.zscaler.com/cloud-branch-connector/deploying-zero-trust-sd-wan-devices). It protects your device from any external damage and helps streamline the organization of your cables and power adapters. Additionally, rack or wall mounting helps ensure proper airflow, preventing overheating and dust accumulation caused by restricted ventilation. Proper airflow also maintains a cleaner environment, prolonging the lifespan of the device.

## Instruction Manuals

- Zero Trust SD-WAN Device 400 Instruction Manual
- Zero Trust SD-WAN Device 600 Instruction Manual
- Zero Trust SD-WAN Device 800 Instruction Manual

- Package Contents
- Rack Mount the Device

The rack mount kit contains the following items:

- 1 rack mount bracket
- 5 cable ties
- 3 A screws for attaching the bracket to the device
- 4 B screws for mounting the device on the rack

To rack mount your Zero Trust SD-WAN Device 400:

1. Place the device in the middle of the rack mounting bracket with the device's rear panel facing away from the bracket.

[Image: Placing the device in the rack mounting bracket]

1. On the back of the mount, secure the device to the rack mounting bracket with three screws.

[Image: Securing the device to the rack mount bracket using three screws]

1. Turn over the device and rack mounting bracket and place the power adapter next to the device, in the adapter holder section.
2. Secure the power adapter with two zip ties. Secure the cables with one zip tie.

[Image: Securing power adapter and cables with zip ties]

1. Attach the power adapter connector to the device's rear panel.

[Image: Attaching the power adapter connector to the device rear panel]

1. Hold the device and lift carefully to insert the device into the rack. Zscaler recommends installing a shelf in the rack to support the device. Attach the brackets to the rail rack using rack mounting screws.

[Image: Inserting the device in the rack]

- Package Contents
- Rack Mount the Device

The rack mount kit contains the following items:

- 2 rack mount brackets
- 6 A screws for attaching the brackets to the device
- 4 B screws for mounting the device on the rack

To rack mount the Zero Trust SD-WAN Device 600:

1. Align one ear bracket to the screw holes on the side panel of the device and attach the bracket using three A screws.

[Image: Attaching ear bracket to side panel of device]

1. Secure the other ear bracket to the other side of the device.

[Image: Attaching second ear bracket to side panel of device]

1. Place the adapter in the adapter holder. Then position the adapter bracket so that the holes align with the two B screws.

[Image: Placing the adapter in the adapter holder and positioning the adapter bracket]

1. Attach the adapter holder to the left side of the device and secure it with two A screws.

[Image: Attaching adapter holder to left side of device]

1. Secure the adapter's cable onto the adapter holder.

[Image: Securing the adapter cable to the adapter holder]

1. Zscaler recommends installing a shelf in the rack to support the device. Attach the brackets to the rail rack using rack mounting screws. Hold the device and lift carefully to insert the device into the rack.

[Image: Installing the device to the rack]

- Package Contents
- Wall Mount the Device
- Rack Mount the Device

The wall mount kit contains the following items:

- 2 wall mount brackets
- 4 white wall plugs
- 4 A screws for attaching the brackets to the device
- 4 B screws for mounting the device on the rack

The wall mount kit is available only by request.

The rack mount kit contains the following items:

- 2 rack mount brackets
- 6 A screws for attaching the brackets to the device
- 4 B screws for mounting the device on the rack

To wall mount the Zero Trust SD-WAN Device 800:

1. Turn the device over and attach the wall brackets onto the bottom side of the device. Secure with the four provided screws.

[Image: Attaching wall brackets to the device.]

1. On the wall, measure the exact place where you want to mount the device. Drill four holes that align with the four mounting holes on the brackets.

[Image: Drilling four holes aligned with the four mounting brackets]

1. Insert the four white wall plugs into the holes you drilled into the wall.
2. Insert the long screws into the wall plugs.

[Image: Inserting long screws into wall plugs]

1. Align the four screw holes on the device's wall brackets with the four long screws installed on the wall.

[Image: Aligning four screw holes with four long screws on wall]

1. Secure the four screws in the bracket holes and push the device downward to lock the screws into position.

[Image: Locking four bracket screws into position]

To rack mount the Zero Trust SD-WAN Device 800:

1. Align one ear bracket to the screw holes on the side panel of the device and secure the bracket using three A screws.

[Image: Securing ear bracket to the side panel of the device]

1. Secure the other ear bracket to the other side of the device.

[Image: Securing second ear bracket to the other side of device]

1. Place the adapter in the adapter holder. Then position the adapter bracket so that the holes align with the two B screws.

[Image: Positioning adapter and adapter holder]

1. Attach the adapter holder to the side of the device and secure it with two A screws.

[Image: Attaching adapter holder to device]

1. Secure the adapter's cable onto the adapter holder.

[Image: Securing adapter cable to adapter holder]

1. Hold the device and lift carefully to insert the device into the rack. Zscaler recommends installing a shelf in the rack to support the device. Attach the brackets to the rail rack using rack mounting screws.

[Image: Installing the device on the rack]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/zero-trust-security-aws-workloads-zscaler-cloud-connector","lastmod":"2026-11-01T23:53Z","nid":"1420871"} -->
## Zero Trust Security for AWS Workloads with Zscaler Cloud Connector

- Source: https://help.zscaler.com/zero-trust-cloud/zero-trust-security-aws-workloads-zscaler-cloud-connector
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Reference Architecture > Zero Trust Security for AWS Workloads with Zscaler Cloud Connector
- Last modified: 2026-11-01T23:53Z
- Summary: The Zero Trust Security for Amazon Web Services (AWS) Workloads with Zscaler Cloud Connector reference architecture guide that steers you through the architecture process, and provides technical deep dives into specific platform functionality and integrations.

The Zscaler Reference Architecture series delivers best practices based on real-world deployments. The recommendations in this series were developed by Zscaler's transformation experts from across the company. This guide will steer you through the architecture process and provide technical deep dives into specific platform functionality and integrations. The Zscaler Reference Architecture series is designed to be modular, so this guide will show you how to configure a different aspect of the platform in order to allow you meet your specific policy goals.

Zscaler Cloud Connector ensures that cloud workloads adhere to organizational security policy when accessing both public and private endpoints. This is achieved by intelligently forwarding traffic to the Zscaler Internet Access (ZIA) and Zscaler Private Access (ZPA) platforms. Cloud Connector also enables multi-cloud connectivity and enforces a security policy for cloud-to-cloud traffic. Cloud Connector is delivered in several form factors. It is available as a virtual appliance in both Amazon Web Services (AWS) and Microsoft Azure, as well as VMs for on-premises deployment. This guide walks you through the AWS deployment. To learn more about the Azure deployment, see [Zero Trust Security for Azure Workloads with Zscaler Cloud Connector](https://help.zscaler.com/cloud-branch-connector/zero-trust-security-azure-workloads-zscaler-cloud-connector).

[Download English PDF](https://help.zscaler.com/downloads/cloud-branch-connector/reference-architecture/zero-trust-security-aws-workloads-zscaler-cloud-connector/zero-trust-security-for-aws-workloads-with-zscaler-cloud-connector.pdf)

[Download Japanese PDF](https://help.zscaler.com/downloads/cloud-branch-connector/reference-architecture/zero-trust-security-aws-workloads-zscaler-cloud-connector/Zero-Trust-Security-AWS-Zscaler-v20230724_ja-JP.pdf)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/zero-trust-security-azure-workloads-zscaler-cloud-connector","lastmod":"2026-11-01T23:53Z","nid":"1420866"} -->
## Zero Trust Security for Azure Workloads with Zscaler Cloud Connector

- Source: https://help.zscaler.com/zero-trust-cloud/zero-trust-security-azure-workloads-zscaler-cloud-connector
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Reference Architecture > Zero Trust Security for Azure Workloads with Zscaler Cloud Connector
- Last modified: 2026-11-01T23:53Z
- Summary: The Zero Trust Security for Azure Workloads with Zscaler Cloud Connector reference architecture guide that steers you through the architecture process, and provides technical deep dives into specific platform functionality and integrations.

The Zscaler Reference Architecture series delivers best practices based on real-world deployments. The recommendations in this series were developed by Zscaler's transformation experts from across the company. This guide will steer you through the architecture process and provide technical deep dives into specific platform functionality and integrations. The Zscaler Reference Architecture series is designed to be modular, so this guide will show you how to configure a different aspect of the platform in order to allow you meet your specific policy goals.

Zscaler Cloud Connector ensures that cloud workloads adhere to organizational security policy when accessing both public and private endpoints. This is achieved by intelligently forwarding traffic to the Zscaler Internet Access (ZIA) and Zscaler Private Access (ZPA) platforms. Cloud Connector also enables multi-cloud connectivity and enforces a security policy for cloud-to-cloud traffic. Cloud Connector is delivered in several form factors. It is available as a virtual appliance in both Amazon Web Services (AWS) and Microsoft Azure, as well as VMs for on-premises deployment. This guide walks you through the Azure deployment. To learn more about the AWS deployment, see [Zero Trust Security for AWS Workloads with Zscaler Cloud Connector](https://help.zscaler.com/cloud-branch-connector/zero-trust-security-aws-workloads-zscaler-cloud-connector).

[Download English PDF](https://help.zscaler.com/downloads/cloud-branch-connector/reference-architecture/zero-trust-security-azure-workloads-zscaler-cloud-connector/Zero-Trust-Security-for-Azure-Workloads-with-Zscaler-Cloud-Connector.pdf)

[Download Japanese PDF](https://help.zscaler.com/downloads/cloud-branch-connector/reference-architecture/zero-trust-security-azure-workloads-zscaler-cloud-connector/Zero-Trust-Security-Azure-Zscaler-v20230715_ja-JP.pdf)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/zero-trust-security-gcp-workloads-zscaler-cloud-connector","lastmod":"2026-11-01T23:53Z","nid":"1474436"} -->
## Zero Trust Security for GCP Workloads with Zscaler Cloud Connector

- Source: https://help.zscaler.com/zero-trust-cloud/zero-trust-security-gcp-workloads-zscaler-cloud-connector
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Reference Architecture > Zero Trust Security for GCP Workloads with Zscaler Cloud Connector
- Last modified: 2026-11-01T23:53Z
- Summary: The Zero Trust Security for Google Cloud Platform (GCP) Workloads with Zscaler Cloud Connector reference architecture guide that steers you through the architecture process, and provides technical deep dives into specific platform functionality and integrations.

The Zscaler Reference Architecture series delivers best practices based on real-world deployments. The recommendations in this series were developed by Zscaler's transformation experts from across the company. This guide will steer you through the architecture process and provide technical deep dives into specific platform functionality and integrations. The Zscaler Reference Architecture series is designed to be modular, so this guide will show you how to configure a different aspect of the platform in order to allow you meet your specific policy goals.

Zscaler Cloud Connector ensures that cloud workloads adhere to organizational security policy when accessing both public and private endpoints. This is achieved by intelligently forwarding traffic to the Zscaler Internet Access (ZIA) and Zscaler Private Access (ZPA) platforms. Cloud Connector also enables multi-cloud connectivity and enforces a security policy for cloud-to-cloud traffic. This guide walks you through the Google Cloud Platform (GCP) deployment.

[Zero Trust Security for GCP Workloads with Zscaler Cloud Connector](https://help.zscaler.com/downloads/cloud-branch-connector/reference-architecture/zero-trust-security-gcp-workloads-zscaler-cloud-connector/Zero-Trust-Security-for-GCP-Workloads-with-Zscaler-Cloud-Connector.pdf) [Download PDF](https://help.zscaler.com/downloads/cloud-branch-connector/reference-architecture/zero-trust-security-gcp-workloads-zscaler-cloud-connector/Zero-Trust-Security-for-GCP-Workloads-with-Zscaler-Cloud-Connector.pdf)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/zscaler-client-connector-vdi-processes-allowlist","lastmod":"2026-11-01T23:53Z","nid":"1516321"} -->
## Zscaler Client Connector for VDI Processes to Allowlist

- Source: https://help.zscaler.com/zero-trust-cloud/zscaler-client-connector-vdi-processes-allowlist
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Zscaler Client Connector for VDI Management > Zscaler Client Connector for VDI Processes to Allowlist
- Last modified: 2026-11-01T23:53Z
- Summary: Information on Zscaler Client Connector for VDI binaries and processes that the users' devices should allowlist.

Zscaler recommends that you allowlist Zscaler Client Connector for VDI processes that permit Virtual Desktop Infrastructure (VDI) binaries and processes. You can only allowlist in Windows. To learn more about Zscaler Client Connector for VDI, see [What Is Zscaler Client Connector for VDI?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-client-connector-vdi)

## Allowlist Processes

The file paths to allowlist for Zscaler Client Connector for VDI are:

- `%ProgramFiles%\ZCCVDI\ZCCVDIHelper.exe`
- `%ProgramFiles%\ZCCVDI\ZCCVDIService.exe`
- `%ProgramFiles%\ZCCVDI\ZCCVDIUI\ZCCVDIUI.exe`
- `%ProgramFiles%\ZCCVDI\ThirdParty\WebView2\MicrosoftEdgeWebview2Setup.exe`
- `%ProgramData%\ZCCVDI`

## Bypasses for Firewall

If you have a firewall managed by group policy object (GPO), you can configure firewall rules on your endpoint protection product for `ZCCVDI.exe` processes for all ports, protocols, network interfaces, and network addresses (e.g., `0.0.0.0/0`).

- `ZCCVDIUI.exe: Outbound`
- `ZCCVDIService.exe: Outbound`

## Processes Usage

The following list describes what each process is used for:

- **ZCCVDIHelper**: An internal process used by other Zscaler Client Connector for VDI processes.
- **ZCCVDIUI**: The user interface of the application.
- **ZCCVDIService**: The main service that manages all other internal services.
- **ProgramData\ZCCVDI**: The directory that stores logs and configuration for Zscaler Client Connector for VDI.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/zscaler-client-connector-vdi-release-summary-2024","lastmod":"2026-11-01T23:53Z","nid":"1478781"} -->
## Zscaler Client Connector for VDI Release Summary (2024)

- Source: https://help.zscaler.com/zero-trust-cloud/zscaler-client-connector-vdi-release-summary-2024
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Release Notes > Zero Trust Cloud VDI Release Notes (per OS) > Zscaler Client Connector for VDI Release Summary (2024)
- Last modified: 2026-11-01T23:53Z
- Summary: Zscaler Client Connector for VDI release summary for updates deployed, per OS and version, in 2024 on Zscaler Cloud & Branch Connector.

This article provides a summary of all new features and enhancements released per operating system (OS) for the Zscaler Client Connector for VDI on Zscaler Cloud & Branch Connector.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/zscaler-client-connector-vdi-release-summary-2025","lastmod":"2026-11-01T23:53Z","nid":"1516236"} -->
## Zscaler Client Connector for VDI Release Summary (2025)

- Source: https://help.zscaler.com/zero-trust-cloud/zscaler-client-connector-vdi-release-summary-2025
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Release Notes > Zero Trust Cloud VDI Release Notes (per OS) > Zscaler Client Connector for VDI Release Summary (2025)
- Last modified: 2026-11-01T23:53Z
- Summary: Zscaler Client Connector for VDI release summary for updates deployed, per OS and version, in 2025 on Zscaler Cloud & Branch Connector.

This article provides a summary of all new features and enhancements released per operating system (OS) for the Zscaler Client Connector for VDI on Zscaler Cloud & Branch Connector.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-cloud/zscaler-client-connector-vdi-release-summary-2026","lastmod":"2026-11-04T10:15Z","nid":"1534300"} -->
## Zscaler Client Connector for VDI Release Summary (2026)

- Source: https://help.zscaler.com/zero-trust-cloud/zscaler-client-connector-vdi-release-summary-2026
- Product: Zero Trust Cloud
- Path: Zero Trust Cloud Help > Release Notes > Zscaler Client Connector for VDI Release Notes > Zscaler Client Connector for VDI Release Summary (2026)
- Last modified: 2026-11-04T10:15Z
- Summary: Zscaler Client Connector for VDI release summary for updates deployed, per OS and version, in 2026 on Zscaler Cloud & Branch Connector.

This article provides a summary of all new features and enhancements released per operating system (OS) for the Zscaler Client Connector for VDI on Zscaler Cloud & Branch Connector.
<!-- /ZS-ARTICLE -->
