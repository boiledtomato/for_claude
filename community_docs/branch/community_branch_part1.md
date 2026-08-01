# Zscaler Zenith Community — Branch / Cloud Connector / SD-WAN (part 1)

Source: https://community.zscaler.com
Generated: 2026-08-01 13:55 UTC
Posts in this file: 36

> これはユーザー投稿のコミュニティフォーラムの内容であり、Zscaler の公式ドキュメントではない。

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmewCAA/how-to-find-current-cloud-connector-version","lastmod":"2023-07-07T11:22:11.000Z","id":"0D54u00009evmewCAA"} -->
## How to find current Cloud Connector version?

- Source: https://community.zscaler.com/s/question/0D54u00009evmewCAA/how-to-find-current-cloud-connector-version
- Type: Q&A
- Posted: 2023-01-18T16:58:29.000Z
- Last activity: 2023-07-07T11:22:11.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Zscaler for Workloads (Cloud Connector) utilizes an IaaS VM deployment from the respective public cloud marketplaces. [What is Cloud Connector?](https://community.zscaler.com/t/what-is-cloud-connector/17838)

As with many cloud offerings, Cloud Connector follows a Shared Responsibility Model where Zscaler provides the VM image and maintains all software/application and OS patching. The customer simply needs to deploy the actual compute resources to run the appliance in their account/tenant. Generally, software updates are pushed incrementally per Zscaler release schedules. Periodically, we will release new versions of the VM image to the AWS/Azure marketplaces though. This could be due to a variety of reasons, such as a new feature release, OS/Kernel update that would be too disruptive or difficult to update in-place, or other performance/boot optimizations. When this happens, the following things will occur:

1. Zscaler will update Cloud Connector [Release Notes](https://help.zscaler.com/cloud-connector/release-upgrade-summary-2022#:~:text=Accelerated%20Networking%20in%20the%20Azure%20Marketplace). (Example linked when Azure Accelerating Networking support was introduced).
2. Cloud Connector Portal Banner update. This may include additional information or just link to the release notes.

#### [#what-action-is-required-for-existing-cloud-connector-customers-1](#what-action-is-required-for-existing-cloud-connector-customers-1)**What action is required for existing Cloud Connector customers?**

Per the release notes, depending on the nature of the update you may want to upgrade existing Cloud Connector clusters by deploying new/updated appliances to replace the old images. (Suggested options of accomplishing this will be documented in a future article). In general though, Zscaler’s **recommendation is to always be running the latest AMI/VM**. If there is a specific new feature requirement/bug impacting an existing customer deployment, that may also be a driver to update.

#### [#how-do-i-know-if-i-am-running-the-latest-cloud-connector-vm-version-2](#how-do-i-know-if-i-am-running-the-latest-cloud-connector-vm-version-2)**How do I know if I am running the latest Cloud Connector VM version?**

Zscaler provides automation templates CFT/Terraform (AWS) and Terraform/managed app (Azure) that, when run, will always pick up the Latest VM version so there is no customer action needed when deploying new VMs as long as they are deploying the latest automation templates. Locating your current virtual machine version can be accomplished a few different ways and also varies between AWS and Azure.

##### [#aws-3](#aws-3)AWS:

You can locate all current and historical marketplace OS/VM versions and corresponding AMIs per region in the AWS marketplace [here](https://aws.amazon.com/marketplace/server/configuration?productId=2bc022b8-5d8c-4e13-aadb-ef3483e607fe). From this example screenshot, you can see at the time of this article the latest AWS image is software version: ZS6.1.24.3 and corresponding AMI in region US West 2 is ami-08811594b4563ee8e.

[[image] image603×529 31.2 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O5EnAAK)

You can then verify what version you have deployed in the AWS Console for any running Cloud Connector EC2 instances.

[[image] image1066×660 58.7 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O788AAC)

##### [#azure-4](#azure-4)Azure:

Azure does not make viewing a Virtual Machine image version from the Azure Portal very easy. From any VM Overview, they only display Publisher, Offer, and Plan details.
 [image]

To locate the currently deployed version for this Offer, the easiest option is to query via AZ CLI. This can be done from a local system with AZ CLI installed and able to successfully authenticate with “az login?. Then, run the following commands:

**az vm image show --urn zscaler1579058425289:zia_cloud_connector:zs_ser_gen1_cc_01:latest** <<< will provide all information about the latest VM offering version. You can then search for the latest Version in the “id? attribute. The output below from the command shows the latest version is “24.2.7?.

[[image] image1897×820 27.9 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O6hhAAC)

**az vm list -g “resource group name? | grep -A 9 imageReference** <<< check that “exactVersion? for the currently deployed Cloud Connector appliances matches the previous latest version.

[[image] image800×225 6.89 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O6W9AAK)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmj1CAA/using-sipa-source-ip-anchoring-with-cloud-connectors","lastmod":"2025-07-14T16:43:56.000Z","id":"0D54u00009evmj1CAA"} -->
## Using SIPA (source ip anchoring) with Cloud Connectors

- Source: https://community.zscaler.com/s/question/0D54u00009evmj1CAA/using-sipa-source-ip-anchoring-with-cloud-connectors
- Type: Q&A
- Posted: 2022-09-07T15:18:16.000Z
- Last activity: 2025-07-14T16:43:56.000Z
- Answers: 5
- Likes: 5
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

You might be using SIPA for your existing Users on the Zscaler platform. This same capability is possible for Workloads that are forwarding to the Zscaler Zero Trust Exchange via Cloud Connectors. Please find the attach guide with step-by-step instructions and a demo video to walk you through the configuration.

[Configure SIPA with Cloud Connectors - SEP2022.pdf](https://community.zscaler.com/uploads/short-url/bA4mx3ClG32zkp9q3MiBxfcTQQ6.pdf) (1.1 MB)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjXCAQ/video-call-home","lastmod":"2023-05-31T09:08:51.000Z","id":"0D54u00009evmjXCAQ"} -->
## [VIDEO] Call Home

- Source: https://community.zscaler.com/s/question/0D54u00009evmjXCAQ/video-call-home
- Type: Q&A
- Posted: 2022-08-10T15:06:24.000Z
- Last activity: 2023-05-31T09:08:51.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

When issues arise, as they often do, how can we guarantee that we capture enough information to not only remediate the issue but also identify what caused it and prevent it from happening again? This is the job of the Call Home functionality within Cloud Connector. As Cloud Connector appliances process cloud workload traffic, the Call Home process periodically captures log files and reports this information to Zscaler engineering. In the event your appliance experiences a problem, Zscaler engineering then immediately has access to the information necessary to both remediate the issue and perform a root-cause analysis.

The feature works by establishing a limited two-way communication channel between the customer’s AWS account and Zscaler’s AWS account. The Cloud Connector appliance, using its configured and attached IAM role, then assumes the `callhome-delegation-role` configured within Zscaler’s AWS account. If authorized, temporary credentials will then be passed back to the Cloud Connector appliance. These credentials are then used to authenticate to the Zscaler S3 bucket where the Cloud Connector will post its log files. Zscaler engineering can then collect the posted files from the S3 bucket and begin diagnosis.

In this video, we’ll explore:
 [0:00 to 0:41] What is Call Home?
 [0:41 to 1:28] How does Call Home work?
 [1:28 to 3:13] How do you configure Call Home?
 [3:13 to 4:10] What are the key takeaways?

Transcript
Hello, my name is Aaron and I’m one of the Principal Technical Product Specialists for Zscaler Cloud Workload Protection.
 In this video, we’ll discuss how you can set up the Cloud Connector appliance to utilize Call Home functionality within AWS.

Call Home is built-in functionality to export certain data and statistics to Zscaler in the occurrence of a fatal event in the Cloud Connector VM - such as instance crashes, failure to boot, etc. These statistics are used by Zscaler engineering to diagnose and rectify any issues encountered, or to perform root-cause analysis. It is highly recommended that Call Home functionality be enabled when building new Cloud Connector appliances in order to assist with any future troubleshooting efforts.

The feature works by establishing limited 2-way communication between the customer’s AWS account and Zscaler’s AWS account. In the event of a failure or issue, the Call Home functionality is invoked. The Cloud Connector appliance, using its configured and attached IAM role, then attempts to assume the `callhome-delegation-role` configured within Zscaler’s AWS account. If authorized, temporary credentials will then be passed back to the Cloud Connector appliance. These credentials are then used to authenticate to the Zscaler S3 bucket where the Cloud Connector will post its log files. Zscaler engineering can then collect the posted files from the S3 bucket and begin diagnosis.

There are two steps to configure Call Home. First, the necessary policy needs to be added to the IAM role attached to the Cloud Connector appliance and second, the ARN of that role needs to be provided to Zscaler support. This will ensure a mutual trust exists between the two entities.

To begin, navigate to the EC2 Instances dashboard of your AWS account. Find the EC2 instance for the Cloud Connector appliance. Scroll down to the Instance IAM Role details and click on the role shown. In the permissions tab, click on the Add permissions button, followed by Create inline policy. Choose the JSON tab. In the blank field that appears, enter the following info shown on your screen. A copy of this information will be placed in the description of this video as well:

```
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "AllowDelegationForCallhome",
            "Effect": "Allow",
            "Action": "sts:AssumeRole",
            "Resource": "arn:aws:iam::223544365242:role/callhome-delegation-role"
        }
    ]
}
```

Please note that Terraform and CloudFormation scripts will eventually be adjusted to perform this step for you automatically. Hence, depending on when you’re watching this video, you may not need to adjust your IAM Role. As a matter of best practice, however, you should review the configured roles and permissions to both ensure they align with organization security policy and for the presence of the displayed permission for Call Home.

As a final step in establishing 2-way trust, copy the ARN of the updated IAM role attached to your appliance. This ARN must be supplied to Zscaler engineering to be added to our policy. Please open a support ticket and provide the ARN for each of your Cloud Connector IAM roles in use. Each Cloud Connector maintains its own IAM role and, hence, ARN… so ensure you include all ARNs in the ticket.

That’s it! The Cloud Connector will now use the Call Home functionality to report failures directly to Zscaler engineering. To test this functionality, you can do so from the command line of the appliance itself. Once logged in, enter the root shell and execute the following command:
 `januscli callhome backup \--instance-dir /sc/instances/edgeconnector0 \--paths /sc/instances/edgeconnector0/core/`

…by default, the command will pack up the displayed paths and export them to Zscaler:

```
/etc/janus/
/var/run/janus*
/sc/instances/edgeconnector0/conf/
/sc/instances/edgeconnector0/logs/
```

Should the Call Home fail, ensure that the trust relationship exists and there are no 400 errors - such as a 403 Unauthorized - and that space exists on the appliance itself to generate the archives to be posted. If in doubt, please contact Zscaler support for more information.

– Cloud Connector appliances can automatically export logs and statistical information to Zscaler engineering for troubleshooting and root-cause analysis via Call Home
 – Call Home works by establishing mutual trust between the Cloud Connector appliance IAM Role and Zscaler. In the event of issues, this trust can be leveraged to deposit log archives into Zscaler’s S3 bucket
 – Terraform and CloudFormation will automatically add the necessary IAM permissions, but you should review them as a matter of best practice. You may need to manually add the IAM policy, depending on when you’re watching this video
 – Open a Zscaler support ticket to have the Cloud Connector IAM Role ARNs added to the Zscaler Trust Relationship. Remember, each Cloud Connector has a separate IAM Role and ARN, so ensure all of them are provided
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjYCAQ/video-session-logging","lastmod":"2023-05-31T09:08:53.000Z","id":"0D54u00009evmjYCAQ"} -->
## [VIDEO] Session Logging

- Source: https://community.zscaler.com/s/question/0D54u00009evmjYCAQ/video-session-logging
- Type: Q&A
- Posted: 2022-08-10T15:04:04.000Z
- Last activity: 2023-05-31T09:08:53.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

One of the unique attributes of Zscaler Cloud Connector, given that the services it connects to (ZIA and ZPA) are cloud-based, is its ability to provide logging of cloud workload traffic from within the cloud itself. By now, if you’re familiar with ZIA or ZPA, you know that much of the organization’s traffic is logged from a security standpoint as it passes through the Zero Trust Exchange. Put simply, the Cloud Connector appliance and portal provide another perspective on this traffic by offering visibility into cloud workload traffic as it leaves the cloud. This, obviously, has merit in Troubleshooting - but also provides value in the fact that even cloud-native traffic that bypasses the Zero Trust Exchange can be logged. For many organizations, having this additional data point is invaluable when reviewing network traffic heuristics.

In this video, we’ll explore:
 [0:00 to 1:13] What is Cloud Connector logging? And how does it differ from normal logging?
 [1:13 to 1:14] What types of logs are available and how are they accessed?
 [1:14 to 4:06] Demonstration of log collection
 [4:06 to 4:41] What are the key takeaways?

Transcript
Hello, my name is Aaron and I’m one of the Principal Technical Product Specialists for Zscaler Cloud Workload Protection.

In this video, we’ll discuss how to leverage the logging functionality of the Zscaler Cloud Connector portal.

One of the unique attributes of Zscaler Cloud Connector, given that the services it connects to (ZIA and ZPA) are cloud-based, is its ability to provide logging of cloud workload traffic from within the cloud itself. By now, if you’re familiar with ZIA or ZPA, you know that much of the organization’s traffic is logged from a security standpoint as it passes through the Zero Trust Exchange. Put simply, the Cloud Connector portal provides another perspective on this traffic by offering visibility into cloud workload traffic as it leaves the cloud. This, obviously, has merit in Troubleshooting - but also provides value in the fact that even cloud-native traffic that bypasses the Zero Trust Exchange can be logged. For many organizations, having this additional data point is invaluable when reviewing network traffic heuristics.

Furthermore, this logging also adds an additional layer of visibility over traditional connectivity options like IPsec and GRE.

Be sure to check out the video on Nanolog Streaming Service integration with Cloud Connector as well. NSS gives us the ability to export the Cloud Connector logs to a SIEM or other event manager for further external processing.

You can access logging information from the Cloud Connector portal Analytics tab.

The tab is broken into three sections: Session Insights, DNS Insights, and Tunnel Insights.

Session Insights, as the name would suggest, provides logging on data path sessions that cross over the Cloud Connector appliances: such as the source and destination of traffic, originating VPC or VNet, protocol, port, and what the disposition of the traffic was.

DNS Insights provides visibility into DNS traffic that crosses the appliance. This is particularly useful in ZPA use-cases where the appliance is proxying traffic using synthetic IP addresses, but it also provides a bit of visibility into the domains being queried by cloud workloads that are outside the organization. You’ll find information on the DNS request itself, the resolved IP, and the disposition of the traffic.

Tunnel Insights provides a glimpse into the data tunnels that are created from the appliance towards the Zero Trust Exchange. Here, you can view the source VPC or VNet the Cloud Connector sits within, its public IP as well as the Zscaler IP address used to terminate the far end of the data tunnel.

All of these log outputs can be filtered as well, allowing administrators to zero in on specific hosts, timeframes, appliances, or a range of other criteria. Here, we will narrow down our Session Insights log to the previous 60 minutes and focus in on one of our cloud workloads at 10.2.1.81. Note the other criteria available for matching as well. Depending on your output, you may also choose to resort to any of the columns to bring more relevant data to the top.

…

Let’s generate a bit of traffic from one of our test hosts and follow it through the network. Logging in to our host at 10.2.1.81, let’s open a web browser and navigate to a few websites. First and foremost, some general connectivity checks to [ip.zscaler.com](http://ip.zscaler.com) and perhaps some random websites like Disney and ESPN. Of course, a staple in any engineering toolbag, let’s check our IP address with [ipinfo.io](http://ipinfo.io). And maybe again through ipaddress.my. Interestingly, did you note how the IP address was different? One showed as an AWS address, the other showed as a ZIA address. Let’s check the logs to see what happened.

From the Session Insights tab, let’s narrow our logs down to our 10.2.1.81 host again. Here, we see our traffic to ESPN, Disney, [ip.zscaler.com](http://ip.zscaler.com) and [ipinfo.io](http://ipinfo.io). Note how all of these use ZIA as the Forwarding Method. Our request to ipaddress.my, however, went Direct. This is because of a Forwarding Rule we have configured for that specific website. You can learn more about Traffic Forwarding rules in the Forwarding Policy video.

Moving into the ZIA dashboard, from the Web Insights log, we can review this same traffic as it was seen entering the Zero Trust Exchange.

– Cloud Connector appliances log control and data traffic within the Cloud Connector portal

– These logs provide a separate vantage point for engineers seeking to review network traffic as it passes out of the cloud

– Logs are split into three categories: Session, DNS, and Tunnel Insights. Each of these logs provides unique statistics on the traffic that passes through the Cloud Connector as well as the traffic originated by the Cloud Connector

– You can use Nanolog Streaming Service (NSS) to export these logs to an external event collector for further processing
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjbCAA/video-forwarding-policy","lastmod":"2023-05-31T09:08:51.000Z","id":"0D54u00009evmjbCAA"} -->
## [VIDEO] Forwarding Policy

- Source: https://community.zscaler.com/s/question/0D54u00009evmjbCAA/video-forwarding-policy
- Type: Q&A
- Posted: 2022-08-10T14:57:29.000Z
- Last activity: 2023-05-31T09:08:51.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Once a Cloud Connector is up and operational, traffic will be directed to it via cloud routing. This is generally done through a simple default route. Regardless, after traffic has reached the Cloud Connector, there are three Forwarding policy types available to direct traffic out of the cloud: Traffic Forwarding, Log and Control, and DNS Policies.

- Log and Control Policies allow an administrator to identify control-plane traffic from specific cloud locations and redirect this traffic to a specified Zscaler Logging Gateway. Generally speaking, Zscaler recommends that this option be adjusted only under the supervision of Zscaler support, however, since the appliance will automatically select a Log and Control Gateway that compliments the Data Plane PoP chosen.
- DNS Policies find their usefulness with regards to ZPA use-cases. As discussed in other videos, the Cloud Connector must see DNS traffic from workload machines in order to proxy their traffic within ZPA. The traffic is proxied via synthetic IP addressing hosted within the Cloud Connector. Administrators can use DNS Policies to allow, block, and forward DNS requests for ZPA-bound traffic. Furthermore, when forwarding to ZPA, DNS Policies also allow the administrator to specify the synthetic IP ranges used.
- Traffic Forwarding Policies are by far the most common policy adjustment that a customer may wish to entertain. These policies allow administrators to influence how data plane traffic is directed through the Cloud Connector.

In this video, we’ll explore:
 [0:00 to 3:25] Overview of Forwarding Policies and policy types
 [3:25 to 7:01] Configuring Traffic Forwarding rules
 [7:01 to 7:55] Key Takeaways

Transcript
Hello, my name is Aaron and I’m one of the Principal Technical Product Specialists for Zscaler Cloud Workload Protection.

In this video, we’ll discuss how Forwarding Policy can be used to influence how the Cloud Connector treats incoming workload traffic.

Once a Cloud Connector is up and operational, traffic will be directed to it via cloud routing. This is generally done through a simple default route. Regardless, after traffic has reached the Cloud Connector, there are three Forwarding policy types available to direct traffic out of the cloud: Traffic Forwarding, Log and Control, and DNS Policies.

Log and Control Policies allow an administrator to identify control-plane traffic from specific cloud locations and redirect this traffic to a specified Zscaler Logging Gateway. Generally speaking, Zscaler recommends that this option be adjusted only under the supervision of Zscaler support, however, since the appliance will automatically select a Log and Control Gateway that compliments the Data Plane PoP chosen.

DNS Policies find their usefulness with regards to ZPA use-cases. As discussed in DNS Setup for AWS and Azure videos, the Cloud Connector must see DNS traffic from workload machines in order to proxy their traffic within ZPA. The traffic is proxied via synthetic IP addressing hosted within the Cloud Connector. Administrators can use DNS Policies to allow, block, and forward DNS requests for ZPA-bound traffic. Furthermore, when forwarding to ZPA, DNS Policies also allow the administrator to specify the synthetic IP ranges used.

Traffic Forwarding Policies are by far the most common policy adjustment that a customer may wish to entertain. These policies allow administrators to influence how data plane traffic is directed through the Cloud Connector. There are three options available within the Traffic Forwarding Policies workflow:

- The Direct forwarding option allows traffic matching the criteria defined to bypass ZIA/ZPA and hair-pin back out of the appliance, where it will follow underlay cloud Route Tables towards the destination. This type of forwarding rule is useful for allowing workloads to reach cloud-native services without having to “boomerang? through ZIA or ZPA. For instance, when accessing an AWS S3 Bucket, a Direct Forwarding Rule can be created to allow AWS workloads to access the resource directly, without being inspected by ZIA. Furthermore, Direct Forwarding Rules also find usefulness in IP whitelisting, since traffic can be forwarded straight from the cloud, where a predictable IP will be used (in this case, the NAT Gateway). Keep in mind, however, that this traffic will be Source NAT’d to the Service Interface IP Address first, then NAT’d again when egressing the cloud.
- Second, the Zscaler Internet Access (ZIA) option, as implied, will allow traffic matching the criteria defined to be forwarded to the Zscaler Internet Access cloud for inspection. By default, for ZIA customers a rule will be automatically created for you to send all traffic to ZIA. This may be acceptable, or, a customer may wish to modify this behavior by creating more specific rules to define which traffic should be delivered to ZIA for inspection.
- And finally, using the Zscaler Private Access (ZPA) option, traffic matching the criteria defined will be forwarded to the Zscaler Private Access cloud. The Cloud Connector automatically downloads ZPA Application Segments from your ZPA portal. Hence, any traffic it receives that is destined to these segments will be proxied, assuming it is permitted within the ZPA Access Policy and Client Forwarding Policy. Similar to ZIA, for ZPA customers, a default rule will be added automatically to ensure ZPA-bound traffic is automatically forwarded to the ZPA Broker.

Whether using DNS Policies, Log and Control, or Traffic Forwarding, each of the three options permits the administrator to define a range of match criteria. This demonstration will focus on DNS Policies and Traffic Forwarding Policies, but the workflow remains roughly the same regardless of the policy chosen.

The forwarding policy is located in the Policy Management section of the Cloud Connector portal, under the Forwarding menu. Rule creation and assessment models ZIA and ZPA workflows. More specific rules should be ordered near the top, while more broad rules ordered towards the bottom. For our first example, let’s assume that we’d like to send traffic destined for Office365 directly out of Azure, instead of inspecting with ZIA. Since this is data plane traffic, we’ll use the Traffic Forwarding Policy type with a Direct Forwarding Rule to accomplish this.

Click the Add Traffic Forwarding Rule button

Set the Rule Order appropriately, so as not to conflict with other rules, provide a name and set the Forwarding Method to Direct. In the Criteria section, notice the options available to define how traffic that should adhere to this rule is matched. Within the General tab, Locations identify the various VPCs or VNets from which your workloads send traffic. As Cloud Connector appliances are brought online, the VPC or VNet they are installed within will automatically populate this menu. Location Groups can be created to organize various cloud VPCs and VNets - such as a “Dev VPCs? Location Group, “Prod VPCs? Location Group, etc. If there are many locations and associated sub-locations within your organization, consider using Location Groups. Branch and Cloud Connector Groups allow you to match traffic transiting specific Cloud Connector appliances.

In the Services menu, you can choose the protocol type that defines the traffic via the Network Service menu. For traffic profiles with multiple protocol types, Network Service Groups can be created to group the traffic for easier policy creation.

Within the Source tab, source IP Addresses or groups of IP Addresses can be used to define the source of incoming traffic that should adhere to this rule. IP addresses can be written as an individual host, in CIDR notation, or as a range of IP addresses using a hyphen.

In the Destination tab, you can enter the IP addresses and/or fully qualified domain names (FQDNs) that this traffic is destined for. Similarly, you can group together Destination IP Addresses and FQDNs that you want to control in a Forwarding Policy rule by Destination IP/FQDN Groups. And finally, as the name suggests, Destination Country allows this match criteria to specify the destination country of the remote machine.

Please note that wildcard domain identifiers (“*?) are not currently supported and that Destination criteria is not supported when Zscaler Private Access is selected as the Forwarding Method.

For our example here, we’ll select our Azure Cloud Connector, then set a Destination FQDN of [sharepoint.com](http://sharepoint.com) and [sharepointonline.com](http://sharepointonline.com). Once we activate our change, we can be assured that Sharepoint traffic to these FQDNs will now egress Azure locally, instead of being inspected by ZIA.

Similarly, similar match criteria can be set up to direct traffic to ZIA and ZPA as well. Remember, ZIA and ZPA have default policies out of the box that will forward traffic to their respective service.

As mentioned previously, the Cloud Connector proxies traffic for ZPA using synthetic IP addressing. This pool, by default, is 10.254.0.0/19, as shown on your screen within the IP & FQDN Group menu. Let’s assume that we want to update this pool to use the 10.253.0.0/16 address space for our AWS clouds, so as not to overlap with existing workload subnets in those regions. Click the Add IP Pool button. Provide a name, and description, and enter the IP address range.

Ne
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjcCAA/video-administrator-rbac-management","lastmod":"2023-05-31T09:08:51.000Z","id":"0D54u00009evmjcCAA"} -->
## [VIDEO] Administrator / RBAC Management

- Source: https://community.zscaler.com/s/question/0D54u00009evmjcCAA/video-administrator-rbac-management
- Type: Q&A
- Posted: 2022-08-10T14:55:21.000Z
- Last activity: 2023-05-31T09:08:51.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

When Branch or Cloud Connectors are booted, they automatically locate the geographically nearest and best performing Zscaler PoPs to connect to for both primary and secondary data tunnels. In some situations, however, a customer may wish to have more control over this automation. For instance, in some regulatory or compliance use-cases, a requirement exists that the ZIA proxy exists within the same country as where the traffic originates. Or, when private Virtual Zscaler Enforcement Nodes are deployed, a customer may wish to manually steer traffic towards these appliances instead of public gateways. Likewise, for troubleshooting purposes, allowing one to flexibly control where their Cloud Connector appliances terminate is advantageous. Furthermore, the ability to export logs off of the appliance itself can be granted when configuring a Log and Control Gateway.

In this video, we’ll explore:
 [0:00 to 1:39] Overview of Role-Based Access Control within Cloud Connector portal
 [1:39 to 3:38] Configuring and RBAC
 [3:38 to 4:20] Key Takeaways

Transcript
Hello, my name is Aaron and I’m one of the Principal Technical Product Specialists for Zscaler Cloud Workload Protection.

In this video, we’re going to be discussing Administrators and Role Management within the Cloud Connector portal.

Zscaler’s role-based administration enables you to control what different admins can do in the Zscaler Cloud Connector Portal. You can delegate responsibilities among admins and granularly control their level of access to the Zscaler Cloud Connector Portal to ensure they do not create conflicting policies and settings.

To facilitate role-based administration, each admin account comprises a role and scope:

Using an admin role or partner admin role, you can specify which features admins can access in the Zscaler Cloud Connector Portal

Using an admin scope, you can specify which areas of the organization (for example, which departments or which locations) admins can configure policies or settings for in the Zscaler Cloud Connector Portal

A great example of where role-based administration within the Cloud Connector portal is when using service accounts. Service accounts are discussed in greater detail in the pre-requisite videos for AWS and Azure, but are used to authenticate and provision the Cloud Connector appliance. These types of accounts are not designed to be used for portal administration - only to authenticate and authorize a connecting appliance. Hence, we can use roles to limit the functionality and scope of these accounts, should they ever become compromised.

Zscaler provides a default admin account that has full access to the Zscaler Cloud Connector Portal and scope over the entire organization. This account cannot be edited or deleted. With role-based administration, you can add as many additional admins as necessary to meet the specific needs of the organization. You can also edit and delete admins as necessary at any time. Remember, Cloud Connector portal also supports SAML v2.0, so authentication for these accounts can be passed from the IdP.

To get started, navigate to the Role Management section within the Administration menu of the Cloud Connector portal. Note that a Super Admin role already exists with new deployments to provide unlimited access to the default administrator account…

Click Add Cloud Connector Role…

Provide a name for your new role. Here, we’ll assume that we are creating a service account role for our Cloud Connector appliances:

In our case, the account should never require dashboard access, so we can remove this functionality.

Likewise, our service account will never need to create or edit Templates, so we can remove this function as well.

Since our Cloud Connector appliances create new locations when they register, we’ll leave Location Management at full.

There’s no reason to provide API Key management access to this service account, so we’ll disable this function.

Obviously, this account is used for the purpose of provisioning new appliances, so we’ll set Cloud Connector Provisioning to full.

We won’t be creating any new administrators with this account, so we’ll disable Administrator Management.

Further, this account will not be used to adjust the traffic Forwarding policy, so we’ll set that to none.

Remote Assistance isn’t necessary either, so this will be set to View Only.

NSS Logging management should also be set to None.

Click the Save button…

Head over to the Administrator Management section of the Administration menu…

Click Add Cloud Connector Admin…

Provide a login ID. If using SAML, ensure this ID aligns with the SAML username that will be passed to the Cloud Connector portal upon successful authentication.

Provide an e-mail and friendly name for this account.

In the Role dropdown, select the role just created.

In the scope dropdown, choose whether this account is authorized to make changes at an Organization level (to all Cloud Connector locations), or on a specific location only. This can be useful for organizations that have geographically dispersed IT departments. Here, we’ll leave it at Organization.

Set a password for the account and click the save button. That’s it! Your new account is ready to use.

You may choose to do a test login of the account before providing the credentials to the end-user or device. In our case, we disabled dashboard access, so our message here is normal.

– Cloud Connector portal provides administrator management and role-based access control.

– A default super-admin account is provided when the portal is initially provisioned. This account cannot be removed or edited.

– Portal account privileges are controlled through roles and scopes. Roles define the individual permissions of an account while scope defines the areas of the organization in which this account is allowed to exercise its roles

– You can configure new roles in the Role Management section of the Cloud Connector portal. These roles and a subsequent scope can then be tied to the account through the Administrator Management section of the portal.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjeCAA/video-scheduled-upgrades","lastmod":"2023-05-31T09:08:51.000Z","id":"0D54u00009evmjeCAA"} -->
## [VIDEO] Scheduled Upgrades

- Source: https://community.zscaler.com/s/question/0D54u00009evmjeCAA/video-scheduled-upgrades
- Type: Q&A
- Posted: 2022-08-10T14:46:14.000Z
- Last activity: 2023-05-31T09:08:51.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

When Branch or Cloud Connectors are deployed, their software is upgraded to the latest version automatically. Although the appliance exists within a customer’s cloud service provider subscription, Zscaler engineering is responsible for maintaining the appliance software. This can include OS upgrades, hotfixes, and Zscaler software upgrades.

Branch and Cloud Connectors check for new software versions every day between 12 and 2 am. If an update is available, they will upgrade themselves automatically. Upgrades for connectors within a Branch or Cloud Connector Group are then staggered to prevent service impact. If deployed in High Availability, as is best practice, the Gateway Load Balancer or Microsoft Standard Load Balancer will ensure the data path outage is minimized. After a connector is successfully upgraded, another eligible connector in the Branch or Cloud Connector group is upgraded.

In this video, we’ll explore:
 [0:00 to 1:20] Overview of Scheduled Upgrades
 [1:20 to 2:01] Modifying upgrade windows
 [2:01 to 2:37] Key Takeaways

Transcript
Hello, my name is Aaron and I’m one of the Principal Technical Product Specialists for Zscaler Cloud Workload Protection.

In this video, we’ll explore how Zscaler Cloud Connector can automatically be upgraded using the Scheduled Upgrade functionality of the Cloud Connector portal.

When Branch or Cloud Connectors are deployed, their software is upgraded to the latest version automatically. Although the appliance exists within a customer’s cloud service provider subscription, Zscaler engineering is responsible for maintaining the appliance software. This can include OS upgrades, hotfixes, and Zscaler software upgrades.

Branch and Cloud Connectors check for new software versions every day… and if one is available, they will upgrade themselves automatically. By default, the upgrade window starts at midnight (from the perspective of the deployed appliance) and all connectors in the Branch and Cloud Connector Group are updated within a 2-hour window. When deployed, the appliance references metadata from the Cloud Service Provider to identify where it is deployed, geographically speaking, in an effort to ascertain its local timezone. Then, it uses NTP to synchronize its clock. Upgrades for connectors within a Branch or Cloud Connector Group are then staggered to prevent service impact. If deployed in High Availability, as is best practice, the Gateway Load Balancer or Microsoft Standard Load Balancer will ensure the data path outage is minimized. After a connector is successfully upgraded, another eligible connector in the Branch or Cloud Connector group is upgraded.

For those customers wishing to modify a scheduled upgrade window, navigate to the Cloud Connector portal > Administration > Connector Groups…

Notice that each Cloud or Branch Connector exists within a group. You may also notice that this group corresponds to an individual VPC and Availability Zone. As mentioned previously, if two or more appliances exist in the same group, their upgrade will be staggered to prevent service impact. You can quickly identify, using the Upgrade Window and Upgrade Status columns, which appliances are scheduled to be upgraded as well as which ones were successfully upgraded.

Click the Edit icon on the right of the group or appliance you wish to modify…

At the top of the pop-up window, adjust the time window to that of your preference and click the Save button.

That’s it! Your Cloud Connector appliances will now honor the new time range when attempting to upgrade.

– Cloud Connector software is maintained by Zscaler engineering automatically

– Cloud Connector appliances will automatically check for upgrades every day and use cloud metadata along with NTP to establish their geographical timezone and correct time

– By default, upgrades are staggered within Availability Zones (or, Cloud Connector Groups) and happen from midnight to 2 am local, appliance time

– You can customize this time range using the Edit function within the Cloud Connector Groups page under the Administration menu
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjiCAA/video-azure-managed-app-deployment","lastmod":"2023-05-31T09:08:51.000Z","id":"0D54u00009evmjiCAA"} -->
## [VIDEO] Azure Managed App Deployment

- Source: https://community.zscaler.com/s/question/0D54u00009evmjiCAA/video-azure-managed-app-deployment
- Type: Q&A
- Posted: 2022-08-10T14:38:14.000Z
- Last activity: 2023-05-31T09:08:51.000Z
- Answers: 0
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Cloud Connector is a virtual appliance within Microsoft Azure used to forward cloud workload traffic to the Zero Trust Exchange. It can be deployed within an Azure environment using both Terraform and, as a more native option, Azure Marketplace. The Azure Marketplace makes it easy to deploy Cloud Connector in a new or existing environment with just a few clicks. Simply browse to the Azure Marketplace and run the Zscaler Cloud Connector Application. The guided workflow then walks the user through Resource Group, Load Balancer, VNet, and Subnet creation, where necessary, before installing the appliances. A Managed Identity and KeyVault are required, however, before running the Marketplace App, so ensure these prerequisites are met prior to beginning.

In this video, we’ll explore:
 [0:00 to 0:38] Pre-requisites and overview of the Azure Marketplace App
 [0:38 to 2:32] Deploying Cloud Connector using the Azure Marketplace
 [2:32 to 3:05] Key takeaways

Transcript
Hello, my name is Aaron and I’m one of the Principal Technical Product Specialists for Zscaler Cloud Workload Protection.
 In this video, we’ll explore how Zscaler Cloud Connector can be provisioned within Microsoft Azure using the Marketplace Application. Before you get started, make sure to check out the Azure Pre-Requisites video and Terraform Overview video linked in the description as there are some items that need to be understood and set up prior to running this application.

For customers seeking a more native automation option for deploying Cloud Connector, Zscaler offers Azure Resource Manager Templates through the Marketplace. The Azure Marketplace makes it easy to deploy Cloud Connector in a new or existing environment with just a few clicks.

From the Azure portal, navigate to the Marketplace and search for Zscaler Cloud Connector Application.

Click the create option to proceed with the deployment.

Select your Subscription and choose a Resource Group that will house the Cloud Connector components. Be aware, however, that the Resource Group MUST be empty. Alternatively, you may create a new Resource Group in this workflow.

Select your Region and click the Next button to proceed.

Enter your Provisioning URL… select your SSH Keys, and identify the Key Vault you wish to leverage for Cloud Connector authentication.

In the User Assigned Managed Identity section, select the Managed Identity you pre-created that has Get and List access to the Key Vault as well as Read access to the appliance’s interfaces. Again, if you’re unsure about how this Managed Identity should be configured, please check out the Azure Pre-requisites video linked in the description.

Click the Next button to proceed.

Here, choose whether or not to create and configure a Load Balancer, or select an existing one. For the purposes of this demo, we’ll create a new one. For redundancy purposes, we’ll update the workflow to create 2 Cloud Connector appliances using HTTP probe port 50000 for health checks. This port identifies a heartbeat service that the appliance uses to report its current health to the Azure Standard Load Balancer.

Next, choose whether you wish to leverage Availability Sets or Availability Zones for physical or geographical redundancy. For this demonstration, we’ll choose Availability Zones to provide physical data center fault isolation. Clicking the Next button then allows us to select the Availability Zones we wish to use.

Here, we’ll select Availability Zones 1 and 2.

We’re now given the option to either select an existing VNet or create a new one. Depending on the architecture of your Azure cloud environment, choose the correct option. Each Cloud Connector will require its own unique subnet. Choose whether to create a new one or use an existing Subnet from the dropdown list.

Click the Next button to proceed. If you have any tags you wish to assign to these resources, add them here. Click the Next button to proceed.

In this final screen, review the changes that will be made and click the Create button.

The creation process will take approximately 5 to 7 minutes. Once complete, you can verify the workflow’s changes by reviewing your All Resources output.

– The Azure Managed App can easily deploy Cloud Connector appliances and, optionally, a Load Balancer, VNets, Subnets, Route Table, and NAT Gateway

– It requires an empty Resource Group to deploy to but can leverage some of your existing Azure resources, so it can lend itself to both Brownfield and Greenfield integration.

– Ensure you have met pre-requisites in Azure prior to running the ARM Marketplace Application so that the workflow will deploy to a successful state.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjuCAA/zscaler-cloud-connector-throughput-and-horizontal-scaling","lastmod":"2023-07-06T11:43:51.000Z","id":"0D54u00009evmjuCAA"} -->
## ZScaler Cloud Connector - throughput and horizontal scaling

- Source: https://community.zscaler.com/s/question/0D54u00009evmjuCAA/zscaler-cloud-connector-throughput-and-horizontal-scaling
- Type: Q&A
- Posted: 2022-08-08T08:52:58.000Z
- Last activity: 2023-07-06T11:43:51.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello All,

I am looking through the Deployment Architecture for CC [here](https://help.zscaler.com/cloud-connector/zero-trust-security-aws-workloads-zscaler-cloud-connector) and there are a couple of questions I am struggling to answer - so looking at a wider community for some assistance

- CloudConnector’s performance is limited to 400Mb, as mentioned by ZScaler: does it mean 400Gbps in total or only for inspected traffic. Will bypassed traffic also bound by this 400Mbps ceiling?
- Is there a ZScaler-approved (=reference architecture) way to scale solution beyond that number (auto-scaling with GLB, etc)? I could not find any - document mentions that one can ‘scale with load-balancing’ without detailing it. Does CC support GENEVE protocol to be used with GWLB? If not, what is the exact supported scheme for horizontal scaling?

All in all, I am concerned about CC being a bottleneck for non-inspected traffic (and inspected also) in a large multi-account/multi-VPC deployment with a requirement to provide egress inspection for all internet-bound web-traffic AND with a requirement to bypass certain traffic classes from inspection.

Thank you!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjvCAA/reference-architecture-documentation","lastmod":"2023-05-31T09:08:51.000Z","id":"0D54u00009evmjvCAA"} -->
## Reference Architecture Documentation

- Source: https://community.zscaler.com/s/question/0D54u00009evmjvCAA/reference-architecture-documentation
- Type: Q&A
- Posted: 2022-08-08T07:00:00.000Z
- Last activity: 2023-05-31T09:08:51.000Z
- Answers: 0
- Likes: 6
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

If you’re keen to get much more technical and “in the weeds? on not just Cloud Connector but all the CSP (Azure and AWS) resources, terminology, and dependencies that Cloud Connector leverages, check out our Reference Architectures below. They provide a 30,000 foot view of public cloud concepts and how they relate to Zscaler Cloud Connector.

**AWS Reference Architecture:
 [Zscaler Cloud Connector - AWS Reference Architecture - Google Docs](https://docs.google.com/document/d/1RPkYk9MtDb1ao8QaO_4s-9HL1_3BulrFWwumsA3-UEY/edit?usp=sharing)**

**Azure Reference Architecture:
 [Zscaler Cloud Connector - Azure Reference Architecture - Google Docs](https://docs.google.com/document/d/1M_uxuNCB3CPg8AyU-D6SM4XL-HheTEOUKQ6V0g5QA4c/edit?usp=sharing)**
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjxCAA/what-is-cloud-connector","lastmod":"2023-05-31T09:08:51.000Z","id":"0D54u00009evmjxCAA"} -->
## What is Cloud Connector?

- Source: https://community.zscaler.com/s/question/0D54u00009evmjxCAA/what-is-cloud-connector
- Type: Q&A
- Posted: 2022-08-07T04:00:00.000Z
- Last activity: 2023-05-31T09:08:51.000Z
- Answers: 1
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

#### [#cloud-infrastructure-protection-using-cloud-connector-1](#cloud-infrastructure-protection-using-cloud-connector-1)Cloud Infrastructure Protection using Cloud Connector

Enabled by the Zero Trust Exchange, Workload Communications is deployed as Cloud Connector virtual machines and extends the capabilities of Zscaler Internet Access (ZIA) and Zscaler Private Access (ZPA) to cloud-native workloads. This allows enterprises to secure cloud workload communications over any network. The Zscaler Zero Trust exchange will enable workloads to communicate with each other and have a granular security policy applied. The communication may be from private workloads (IaaS/Physical DC) to public workloads (SaaS/Internet), or between private workloads (IaaS to IaaS, or Physical DC to IaaS). Generally, where a private workload is communicating to a public workload through the ZTE, this will use the Zscaler Internet Access platform. Where a private workload is communicating with another private workload through the ZTE, this will use the Zscaler Private Access platform.

##### [#key-features-and-benefits-2](#key-features-and-benefits-2)Key Features and Benefits

- Security - Secures all inbound and outbound traffic to the internet. The security capabilities that are available through the [Zscaler Internet Access](https://www.zscaler.com/products/zscaler-internet-access) platform for server internet access are Secure Sockets Layer (SSL), Intrusion Prevention System (IPS), Firewall, Data Loss Prevention (DLP), etc.
- Connectivity - Provides seamless connectivity from Private/Public cloud applications to the internet.
- Performance - Ensures better end-user experience and application performance by peering into relationships with SaaS providers (e.g., Microsoft Office 365, Amazon Web Services, and Microsoft Azure).
- Reduces Cost - Consolidates multiple products (e.g., Squid proxies, firewalls, 3rd party NAT appliances, URL filtering, etc.) into a single solution. Additionally, the same policy applied to user traffic can be applied across the cloud infrastructure.
- Highly Scalable - Ease of implementation across 1K service accounts in public clouds and single solution scales to connect 10K+ server environments in public clouds (e.g., AWS, Azure, etc.).
- Ease of Deployment - Fully orchestrated deployment for AWS using Terraform and CloudFormation templates.
- Real-Time Visibility - Dashboards and Insights provide unparalleled visibility into your users and applications and the health of your organization’s applications and servers.

Oftentimes, in the realm of networking, we find ourselves asking how we can secure traffic to the cloud. Rarely do we evaluate how traffic is secured within the cloud, as it leaves the cloud, or while in transit between clouds. This attack surface is growing as more and more workloads are being migrated and customers begin operating across multiple cloud service providers. Zscaler Workload Communications can help enable the following use-cases:

Applications to Internet Communications: Applications may need to access any Internet or SaaS destination, such as 3rd party APIs, software updates, etc. with a scalable, reliable security solution that inspects all transactions, applying advanced threat prevention and data loss protection controls.

Application to Application Communication to other public clouds and corporate data centers for multi/hybrid cloud connectivity, delivered with better security and a dramatically simplified operational model as compared with traditional solutions like proxies, virtual firewalls and IDS/IPS.

Application to Application Communications within a VPC by securing process-to-process communications to achieve micro-segmentation with no changes to the application or the network.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmoDCAQ/forwarding-profile-in-sdwan-visibility-or-authentication","lastmod":"2023-05-31T08:46:16.000Z","id":"0D54u00009evmoDCAQ"} -->
## Forwarding Profile in SDWAN : visibility or authentication?

- Source: https://community.zscaler.com/s/question/0D54u00009evmoDCAQ/forwarding-profile-in-sdwan-visibility-or-authentication
- Type: Q&A
- Posted: 2022-01-31T16:45:20.000Z
- Last activity: 2023-05-31T08:46:16.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello ZSC users and members;

I come here today to seek advice as we are heading to implement SDWAN as our new WAN standard. And we face a decision that is not easy to make. Let me explain.

**Today : traditionnal internet access**
 **RW**, environnement is untrust, and we want full cybersecurity controll ==> ZCC running on all laptop + ZT2.0 to catch all internet trafic and manage it in our cloud.
 **For onsite users**, all standard users have ZCC on their computer (laptop and desktop), and we’re using Tunnel with local proxy forwarding mode and hosted pac file. But there is also some servers and shared station that doesn’t. So we setup IPSec tunnel to catch all internet traffic.
 This work quite fine.

**Tomorrow : sdwan**
 **RW** : no change
 **onsite** : here comes the big question.
 Should we deactivate our ZCC when ontrusted network to offer the sdwan device full visibility of the url in order to allow classification, and app recognition (we keep the IPSec “catch all? tunnel from branch to Zscaler ) ? It seems at first try to also create auth issue, as the ZCC doesn’t provide the user ID.
 Should we keep tunnel mode + IPSec for better security and accept that for the sdwan device, all our internet access is seen as “flow to zscaler? ?

I cannot put my hand on some documentation covering this case. It’s either “here is how the ZCC works? or “zscaler integrates within sdwan thanks to API tunnel creation?.

Hope I’ve made myself clear enough (sorry french guy here)

Cheers
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmsDCAQ/zscaler-cloud-connector-public-preview-availability","lastmod":"2023-07-06T11:45:36.000Z","id":"0D54u00009evmsDCAQ"} -->
## Zscaler Cloud Connector public preview availability

- Source: https://community.zscaler.com/s/question/0D54u00009evmsDCAQ/zscaler-cloud-connector-public-preview-availability
- Type: Q&A
- Posted: 2021-04-23T11:55:32.000Z
- Last activity: 2023-07-06T11:45:36.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi all,

the help page now includes [documentation for ZCC](https://help.zscaler.com/cloud-connector), and I noticed this message too:

[[image] image1024×648 36.5 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O6oSAAS)

Can anyone share what this public preview is all about?

How does one access the public preview?

Many thanks,

Luca
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2JCAQ/zscaler-sdwan-integrations","lastmod":"2023-05-31T08:52:38.000Z","id":"0D54u00009evn2JCAQ"} -->
## ZScaler SD-WAN integrations

- Source: https://community.zscaler.com/s/question/0D54u00009evn2JCAQ/zscaler-sdwan-integrations
- Type: Q&A
- Posted: 2019-05-25T18:13:02.000Z
- Last activity: 2023-05-31T08:52:38.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Does ZScaler have any plans to integrate with [https://www.aryaka.com/](https://www.aryaka.com/) SD-WAN vendor?
 Thanks,
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4LCAQ/local-internet-breakout","lastmod":"2023-05-31T08:54:46.000Z","id":"0D54u00009evn4LCAQ"} -->
## Local Internet breakout

- Source: https://community.zscaler.com/s/question/0D54u00009evn4LCAQ/local-internet-breakout
- Type: Q&A
- Posted: 2018-08-09T10:10:47.000Z
- Last activity: 2023-05-31T08:54:46.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

My scenario is as below.

1. I have 1 SD-WAN device. Behind SD-WAN, there are 3 different network.
2. All the 3 different network belongs to 3 different companies & they don’t communicate to each other.
3. Each company has different Zscaler account.
4. I am planning to form 3 different GRE tunnel to Zscaler for 3 different networks.

**Question:**

1. Is is possible to integrate 3 different tunnel to 3 different Zscaler account ?
2. Can single SD-WAN device be able to connect to 3 different Zscaler account ?

Is this practical design.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykQ0CAI/awsazure-traffic-forwarding-options-to-cloud-connectors","lastmod":"2024-06-24T12:26:30.000Z","id":"0D54u00009eykQ0CAI"} -->
## AWS/Azure Traffic Forwarding Options to Cloud Connectors

- Source: https://community.zscaler.com/s/question/0D54u00009eykQ0CAI/awsazure-traffic-forwarding-options-to-cloud-connectors
- Type: Q&A
- Posted: 2023-04-06T20:21:54.000Z
- Last activity: 2024-06-24T12:26:30.000Z
- Answers: 1
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello everyone, please find the attached solution guide describing the various ways to forward/route traffic from workloads to Cloud Connectors in an AWS and Azure environment. This guide covers:

- ZIA forwarding with default routes for all traffic
- ZIA forwarding with default routes for specific protocols
- ZIA Explicit Proxy (PAC) Forwarding using Global Service Edges
- ZPA Forwarding

[Cloud Connector Workload Forwarding Options (Default Route & Explicit Proxy Configurations).pdf](https://community.zscaler.com/uploads/short-url/9QnH7KmWJ64nvfYSn6z6mZ28a5A.pdf) (2.1 MB)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A0eLLBCQ2/using-cloud-connector-to-replace-nat-gateway-and-reduce-cost-part-1","lastmod":"2023-10-09T08:47:26.000Z","id":"0D54u0000A0eLLBCQ2"} -->
## Using Cloud Connector to replace NAT Gateway (and reduce cost) (part 1)

- Source: https://community.zscaler.com/s/question/0D54u0000A0eLLBCQ2/using-cloud-connector-to-replace-nat-gateway-and-reduce-cost-part-1
- Type: Q&A
- Posted: 2023-09-29T12:54:26.000Z
- Last activity: 2023-10-09T08:47:26.000Z
- Answers: 0
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><strong style="font-size: 11pt; font-family: Arial, sans-serif;">Using Cloud Connectors without NAT Gateway</strong></p><p><span style="font-size: 11pt; font-family: Arial, sans-serif;">The Zscaler Cloud Connector is a VM-based solution built to forward traffic from cloud-based workloads to public and/or private destinations using the Zscaler Cloud services. As such it needs to be able to initiate traffic to Zscaler Service Edges which requires public IP addresses (more detailed&nbsp;information on Cloud Connector communication can be found </span><a href="https://help.zscaler.com/cloud-branch-connector/networking-flows-cloud-connector" rel="noopener noreferrer" target="_blank" style="font-size: 11pt; font-family: Arial, sans-serif;">in our help-portal</a><span style="font-size: 10.5pt; font-family: Roboto, sans-serif;">).</span><span style="font-size: 11pt; font-family: Arial, sans-serif;">&nbsp;</span></p><p><br></p><p><span style="font-size: 11pt; font-family: Arial, sans-serif;">In general, Zscaler recommends setting up Cloud Connector with a NAT Gateway as it solves a number of required functions:</span></p><ul><li><span style="font-size: 11pt;">It assigns public IP addresses to all interfaces for outbound traffic</span></li><li><span style="font-size: 11pt;">It prevents unsolicited inbound connections (from the Internet)</span></li><li><span style="font-size: 11pt;">It allows for the use of private IP space within the Cloud, making for an easier local routing setup</span></li></ul><p style="text-align: center;"><em style="font-size: 11pt; font-family: Arial, sans-serif;"><img src="/sfc/servlet.shepherd/version/download/0684u00000dWeeK?asPdf=false&amp;operationContext=CHATTER">Diagram: recommended Cloud Connector setup with NAT-GW</em></p><p><br></p><p><span style="font-size: 11pt; font-family: Arial, sans-serif;">However, NAT Gateways can introduce significant additional costs, especially when combined with high data throughput. At the same time, Cloud Connectors are designed to be exposed to the Internet </span><em style="font-size: 11pt; font-family: Arial, sans-serif;">and</em><span style="font-size: 11pt; font-family: Arial, sans-serif;"> only require outbound Internet access which makes them even less of a target. Moreover, since Cloud Connectors act as the default forwarding function this also means that NAT Gateway isn’t necessary for other non-public workloads either. </span></p><p><br></p><p><span style="font-size: 11pt; font-family: Arial, sans-serif;">This article describes a Cloud Connector setup that replaces the NAT Gateway functionality where it makes sense, while still maintaining the same security considerations.</span></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000dWel5?asPdf=false&amp;operationContext=CHATTER"></p><p><span style="font-size: 11pt; font-family: Arial, sans-serif;"> </span><em style="font-size: 11pt; font-family: Arial, sans-serif;">Diagram: alternative Cloud Connector setup without NAT-GW</em></p><p><br></p><p><span style="font-size: 11pt; font-family: Arial, sans-serif;">Note that the Community site limits the article length (for now), so the next section can be found </span><a href="https://community.zscaler.com/zenith/s/question/0D54u0000A0eTJbCQM/using-cloud-connector-to-replace-nat-gateway-and-reduce-cost-part-2" rel="noopener noreferrer" target="_blank" style="font-size: 11pt; font-family: Arial, sans-serif;">here</a></p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A0eTJbCQM/using-cloud-connector-to-replace-nat-gateway-and-reduce-cost-part-2","lastmod":"2023-10-16T09:48:37.000Z","id":"0D54u0000A0eTJbCQM"} -->
## Using Cloud Connector to replace NAT Gateway (and reduce cost) (part 2)

- Source: https://community.zscaler.com/s/question/0D54u0000A0eTJbCQM/using-cloud-connector-to-replace-nat-gateway-and-reduce-cost-part-2
- Type: Q&A
- Posted: 2023-09-29T13:14:58.000Z
- Last activity: 2023-10-16T09:48:37.000Z
- Answers: 0
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Note: the previous section of this article can be found <a href="https://community.zscaler.com/s/question/0D54u0000A0eLLBCQ2/using-cloud-connector-to-replace-nat-gateway-and-reduce-cost-part-1" rel="noopener noreferrer" target="_blank">here</a></p><p><br></p><p><strong style="font-size: 11pt; font-family: Arial, sans-serif;">Setting up public IP addresses to the Cloud Connector interfaces</strong></p><p><br></p><p><span style="font-size: 11pt; font-family: Arial, sans-serif;">The first thing to do is to actually assign public IP addresses to these interfaces. Note that (as the NAT Gateway already implied) this doesn’t have to be a fixed address, as long as it’s consistent during the Cloud Connector’s uptime.&nbsp;</span></p><p><br></p><p><strong style="font-size: 11pt; font-family: Arial, sans-serif;">In Azure</strong><span style="font-size: 11pt; font-family: Arial, sans-serif;"> you link public IP addresses to the CloudConnector interfaces. First ensure there is no NAT Gateway associated with the subnet (or remove it when there is). Then go into the Cloud Connector VM, select the Network Interface, select IP Configuration and toggle the Public IP address settings to “Associate”. Do this for all interfaces.</span></p><p><br></p><p><strong style="font-size: 11pt; font-family: Arial, sans-serif;">In AWS </strong><span style="font-size: 11pt; font-family: Arial, sans-serif;">you need to place the Cloud Connector in a public subnet which will assign one public IP address to it, and assign Elastic IP addresses to all other interfaces.&nbsp;&nbsp;</span></p><p><br></p><p><span style="font-size: 11pt; font-family: Arial, sans-serif;">When using Terraform this can be achieved by first creating an </span><em style="font-size: 11pt; font-family: Arial, sans-serif;">aws_eip resource</em><span style="font-size: 11pt; font-family: Arial, sans-serif;">, and then associate it to the Cloud Connector interface-ids through </span><em style="font-size: 11pt; font-family: Arial, sans-serif;">aws_eip_association</em></p><p><br></p><p><span style="font-size: 11pt; font-family: Arial, sans-serif;">When using CloudFormation you must assign a public subnet when creating the stack. This will automatically assign one Public IP address to the instance and, as such, to one of the Interfaces. Allocate an Elastic IP address and, once the Cloud Connector EC2 instance is created, associate it with another interface. Repeat until all Cloud Connector interfaces have a public IP association.&nbsp;</span></p><p><br></p><p><strong style="font-size: 11pt; font-family: Arial, sans-serif;">NB:</strong><span style="font-size: 11pt; font-family: Arial, sans-serif;"> By default, AWS only allows a limited number of E-IPs per Region.&nbsp;For additional addresses the customer has to create a support ticket with AWS. </span><em style="font-size: 11pt; font-family: Arial, sans-serif;">See </em><a href="https://docs.aws.amazon.com/vpc/latest/userguide/amazon-vpc-limits.html" rel="noopener noreferrer" target="_blank" style="font-size: 11pt; font-family: Arial, sans-serif;"><em><u>https://docs.aws.amazon.com/vpc/latest/userguide/amazon-vpc-limits.html</u></em></a><em style="font-size: 11pt; font-family: Arial, sans-serif;"> for more details</em></p><p><br></p><p><span style="font-size: 11pt; font-family: Arial, sans-serif;">Since requesting additional E-IPs can be a cumbersome process </span><em style="font-size: 11pt; font-family: Arial, sans-serif;">and</em><span style="font-size: 11pt; font-family: Arial, sans-serif;"> since the majority of cost for NAT-GW comes with the throughput used, it can interesting to not have E-IPs assigned to all interfaces, but to the service interfaces only and still use NAT Gateway for the management interface:</span></p><p><br></p><p style="text-align: center;"><img src="/zenith/sfc/servlet.shepherd/version/download/0684u00000dWeuW?asPdf=false&amp;operationContext=CHATTER"></p><p style="text-align: center;"><em style="font-size: 11pt; font-family: Arial, sans-serif;">Diagram: alternative Cloud Connector setup with partial NAT-GW</em></p><p><br></p><p><span style="font-size: 11pt; font-family: Arial, sans-serif;">The </span><a href="https://community.zscaler.com/zenith/s/question/0D54u0000A0eUfyCQE/using-cloud-connector-to-replace-nat-gateway-and-reduce-cost-part-3" rel="noopener noreferrer" target="_blank" style="font-size: 11pt; font-family: Arial, sans-serif;">next section of this article</a><span style="font-size: 11pt; font-family: Arial, sans-serif;"> describes the additional measures to protect against external attacks</span></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A0eUfyCQE/using-cloud-connector-to-replace-nat-gateway-and-reduce-cost-part-3","lastmod":"2023-10-16T12:22:06.000Z","id":"0D54u0000A0eUfyCQE"} -->
## Using Cloud Connector to replace NAT Gateway (and reduce cost) (part 3)

- Source: https://community.zscaler.com/s/question/0D54u0000A0eUfyCQE/using-cloud-connector-to-replace-nat-gateway-and-reduce-cost-part-3
- Type: Q&A
- Posted: 2023-09-29T13:36:18.000Z
- Last activity: 2023-10-16T12:22:06.000Z
- Answers: 4
- Likes: 4
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>The previous part of this article can be found <a href="https://community.zscaler.com/zenith/s/question/0D54u0000A0eTJbCQM/using-cloud-connector-to-replace-nat-gateway-and-reduce-cost-part-2" rel="noopener noreferrer" target="_blank">here</a> </p><p><br></p><p><strong style="font-family: Arial, sans-serif; font-size: 11pt;">Protecting against Internet-sourced attacks</strong></p><p><br></p><p><span style="font-family: Arial, sans-serif; font-size: 11pt;">Setting up Cloud Connectors without NAT Gateway requires that they are placed in a public subnet, which makes them addressable from the Internet. The attack-surface of a Cloud Connector itself is limited; it is hardened and only allows limited direct access. Still, the management interface allows inbound SSH access which </span><em style="font-family: Arial, sans-serif; font-size: 11pt;">can</em><span style="font-family: Arial, sans-serif; font-size: 11pt;"> be a target for both compromise and denial-of-service and should be protected.</span></p><p><br></p><p><span style="font-family: Arial, sans-serif; font-size: 11pt;">More fundamentally, the CC </span><em style="font-family: Arial, sans-serif; font-size: 11pt;">service</em><span style="font-family: Arial, sans-serif; font-size: 11pt;"> interface must accept traffic coming from the internal Cloud workloads but should never accept unsolicited traffic from the Internet. However, if an attacker is able to mimic/spoof Workload traffic, CC will pick it up and process it as normal. This opens up attack-vectors towards ZIA and ZPA resources, which need to be mitigated.&nbsp;</span></p><p><br></p><p><span style="font-family: Arial, sans-serif; font-size: 11pt;">Fortunately, some attacks are infeasible due to regular routing, and Azure and AWS have a few useful options that allow for a ruleset that doesn’t need continuous updating after adding new workloads:</span></p><ul><li><span style="font-size: 11pt;">Transparent access from the Internet through Cloud Connectors to ZIA or ZPA resources will be prevented by regular Internet routing (the traffic will never end up at the CC in the first place)</span></li><li><span style="font-size: 11pt;">AWS and Azure have Anti-spoofing measures to block inbound traffic using cloud-local IP space</span></li><li><span style="font-size: 11pt;">Azure has default labeling for local Cloud resources. This means you don’t have to change the Security Groups each time you add a new subnet</span></li></ul><p><span style="font-family: Arial, sans-serif; font-size: 11pt;">Unfortunately, although AWS and Azure do provide protection against spoofing (Cloud-) local addresses, (obviously) it can’t protect against spoofed Internet address space. And since the CC service interface must respond to DNS requests, it could be used as a target by itself </span><em style="font-family: Arial, sans-serif; font-size: 11pt;">and</em><span style="font-family: Arial, sans-serif; font-size: 11pt;"> as facilitator to (D)DoS public and private services (and, incidentally, it could also lead to Zscaler counting these spoofed addresses towards the ZIA and ZPA Workload licenses). Combined, this leads to the following attacks and mitigation measures:&nbsp;</span></p><p><img src="/zenith/sfc/servlet.shepherd/version/download/0684u00000dWf6c?asPdf=false&amp;operationContext=CHATTER"></p><p><span style="font-family: Arial, sans-serif; font-size: 11pt;">So we need a number of Security Group rules to mitigate these risks by making sure that only local resources can use the CCs.&nbsp;</span></p><p><br></p><p><strong style="font-family: Arial, sans-serif; font-size: 11pt;">In Azure</strong><span style="font-family: Arial, sans-serif; font-size: 11pt;"> this is straightforward (in fact: our ARM and Terraform provisioning scripts create the correct Security Group rules by using Azure defined network TAGs). For the management interface only sources on “VirtualNetwork” should be allowed access to listening services, like SSH. Of course, if you have a specific subnet to manage workloads from (containing management systems and/or jump hosts) then further limit SSH access only to those systems. In addition, the management interface needs public outbound access towards DNS (UDP/TCP 53),&nbsp; (D)TLS (UDP/TCP 443) and NTP (UDP 123). </span></p><p><br></p><p><span style="font-family: Arial, sans-serif; font-size: 11pt;">For the service interface this means only sources on “VirtualNetwork” are allowed full TCP/UDP access to ANY destination behind the Cloud Connector. Note that </span><em style="font-family: Arial, sans-serif; font-size: 11pt;">if</em><span style="font-family: Arial, sans-serif; font-size: 11pt;"> you have additional networks connected (through Direct Access, virtual WAN or VPN) that also want to use Cloud Connector to protect their traffic going out, you’ll need to manually add policy rules for them as well.</span></p><p><br></p><p><strong style="font-family: Arial, sans-serif; font-size: 11pt;">In&nbsp;AWS </strong><span style="font-family: Arial, sans-serif; font-size: 11pt;">this configuration is slightly less convenient; you’ll have to define these ACLs using your local IP subnets manually. Again, the management interface should only allow inbound SSH from a management subnet or from specific bastion/jump-hosts. The management also needs public outbound access towards DNS (UDP/TCP 53),&nbsp; (D)TLS (UDP/TCP 443) and NTP (UDP 123).</span></p><p><br></p><p><span style="font-family: Arial, sans-serif; font-size: 11pt;">For the service interface this means only your locally defined subnets (and IP ranges from other connected networks, if they need to use Cloud Connector to protect their traffic going out) should be allowed full TCP/UDP access to ANY destination behind the Cloud Connector. Note that since AWS will protect against traffic with (spoofed) private (RFC1918) IP addresses, allowing inbound connections only from RFC1918 sources will protect against attacks from external sources.</span></p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AA1Qb3CQF/azure-virtual-wan-route-configuration-for-cloud-connector-secure-egress","lastmod":"2024-01-25T20:06:12.000Z","id":"0D54u0000AA1Qb3CQF"} -->
## Azure Virtual WAN route configuration for Cloud Connector secure egress

- Source: https://community.zscaler.com/s/question/0D54u0000AA1Qb3CQF/azure-virtual-wan-route-configuration-for-cloud-connector-secure-egress
- Type: Q&A
- Posted: 2023-11-21T16:29:40.000Z
- Last activity: 2024-01-25T20:06:12.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><a href="https://community.zscaler.com/zenith/s/question/0D54u0000AHNzY7CQL/azure-vwan-secured-virtual-hub-reference-architecture" rel="noopener noreferrer" target="_blank">UPDATED DOCUMENT HERE</a></p><p><br></p><p>This article aims to provide an overview of how the Workloads team at Zscaler typically configures Azure VHub route tables to support a hub-and-spoke VNet model using Virtual WAN. More information on this Azure network topology can be found <a href="https://learn.microsoft.com/en-us/azure/architecture/networking/hub-spoke-vwan-architecture" rel="noopener noreferrer" target="_blank">here</a>.</p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000ecg9f?asPdf=false&amp;operationContext=CHATTER" title="UPDATED DOCUMENT HERE"></p><p>Configuration Overview</p><ol><li>Create a Virtual WAN and then a associated Virtual WAN Hub</li><li>Create two empty route tables on the VHub configuration screen named: SpokesToZscaler, ZscalerToSpokes</li><li>Attach all relevant VNets to the Virtual WAN. Use the following configuration options:<ol><li>For a spoke VNet, associate the SpokesToZscaler route table and propagate routes to the ZscalerToSpokes route table</li><li>For the Zscaler Cloud Connector VNet, associate the ZscalerToSpokes route table and propagate routes to the SpokesToZscaler route table</li><li>Note: If you wish to enable traffic routing from spoke VNet 1 to spoke VNet 2, for example, ensure that in the configuration for spoke VNet 2 the route table SpokesToZscaler is selected for route propagation which will enable the spoke VNet(s) to route to spoke VNet 2. This design can be applied to all spoke VNets if you wish for full mesh connectivity.</li></ol></li><li>Edit the SpokesToZscaler route table adding this static route:<ol><li>Name: Internet via Zscaler</li><li>Destination type: CIDR</li><li>Destination prefix: 0.0.0.0/0</li><li>Next hop: Select the Zscaler Cloud Connector VNet to VWAN connection name</li><li>Select Configure and use these settings:<ol><li>Next hop IP: Enter the Zscaler Cloud Connector load balancer frontend IP</li></ol></li></ol></li><li>Confirm and Create to finalize the route table configuration</li><li>Note: Routes will be propagated to connected VNets and their subnet route tables automatically. If your environment requires additional routing configuration, make sure to check a subnet's or a VM's effective routes before creating any additional static routes.</li></ol><p><br></p><p><br></p><p>Step 4:</p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000ecgBq?asPdf=false&amp;operationContext=CHATTER" title="UPDATED DOCUMENT HERE"></p><p><br></p><p><br></p><p>Step 4e:</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000ecgCA?asPdf=false&amp;operationContext=CHATTER" title="UPDATED DOCUMENT HERE"></p><p><br></p><p><br></p><p>Here is the resulting SpokesToZscaler effective route table:</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000ecgCe?asPdf=false&amp;operationContext=CHATTER" title="UPDATED DOCUMENT HERE"></p><p><br></p><p><br></p><p>Here is an example resulting effective route table for a VM that resides in a spoke VNet:</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000ecgDX?asPdf=false&amp;operationContext=CHATTER" title="UPDATED DOCUMENT HERE"></p><p><br></p><p><br></p><p>Caveats and other notes</p><ul><li>The VNet subnet where the Zscaler Cloud Connectors reside should have an assigned NAT gateway and no other network services that would prevent this NAT gateway from being the 0.0.0.0/0 default route.</li><li>Some VWAN services are not compatible with user defined VHub route tables. Refer to the VWAN documentation for more information (<a href="https://learn.microsoft.com/en-us/azure/virtual-wan/how-to-routing-policies" rel="noopener noreferrer" target="_blank">example 1</a>).</li><li>Services and components shown in the above example:<ul><li>VHub private address space: 10.0.0.0/24</li><li>Spoke1 AKA VNet1: 10.1.0.0/24</li><li>Spoke2 AKA VNet2: 10.2.0.0/24</li><li>Spoke3 AKA VNet3: 10.3.0.0/24</li><li>Zscaler AKA VNet4: 10.4.0.0/24</li><li>Cloud Connector LB frontend IP: 10.4.3.4</li></ul></li></ul>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AJWSGoCQP/frequent-session-disconnections-with-sap-servers-after-sdwan-implementation","lastmod":"2024-03-08T17:23:03.000Z","id":"0D54u0000AJWSGoCQP"} -->
## Frequent Session Disconnections with SAP Servers after SDWAN Implementation

- Source: https://community.zscaler.com/s/question/0D54u0000AJWSGoCQP/frequent-session-disconnections-with-sap-servers-after-sdwan-implementation
- Type: Q&A
- Posted: 2024-02-18T18:36:19.000Z
- Last activity: 2024-03-08T17:23:03.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi All,</p><p>In my organization there has been implementations of SDWAN, after which some branch locations where it was implemented started facing internet slowness and frequent disconnections issues.</p><p>They were not able to use SAP application with private access on in office network but they use to access it properly while turning it off.</p><p>There were no error logs found over ZPA, what can be the issue?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AKiw25CQB/cloud-connector-vs-cloud-connector-application","lastmod":"2024-07-18T16:42:07.000Z","id":"0D54u0000AKiw25CQB"} -->
## Cloud Connector vs Cloud Connector Application

- Source: https://community.zscaler.com/s/question/0D54u0000AKiw25CQB/cloud-connector-vs-cloud-connector-application
- Type: Q&A
- Posted: 2024-02-24T05:18:29.000Z
- Last activity: 2024-07-18T16:42:07.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Experts,</p><p>We're planning to install Cloud connectors in Azure and in market place observed that there are two options relating to this: Zscaler Cloud Connector and Zscaler Cloud Connector Application.</p><p>Just trying to figure out which one to install and what is the difference of the two?</p><p>Thank you.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AXTKKUCQ5/cloud-connector-installation-azure","lastmod":"2025-05-28T16:29:41.000Z","id":"0D54u0000AXTKKUCQ5"} -->
## Cloud Connector Installation- Azure

- Source: https://community.zscaler.com/s/question/0D54u0000AXTKKUCQ5/cloud-connector-installation-azure
- Type: Q&A
- Posted: 2024-07-15T15:41:28.000Z
- Last activity: 2025-05-28T16:29:41.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi There,</p><p><br></p><p>We're planning to install Cloud connector in Azure and while going through the below link it seems that Azure key vault needs to be allowed from All public networks. If my understanding is correct, Zscaler Cloud connector VM will try to reach out to key vault for creds and try ti connect with the Cloud connector portal for registration via management interface which is a private IP.</p><p><br></p><p>So, in this case, we can restrict the Azure key vault networking settings specific to this Vnet/Internal IP range and UDR of the management subnet can be configured to reach the Key vault via service tags (MS backbone network).</p><p><br></p><p>Can someone please advise why "Allow access from: Select All networks' (Public) should be selected as per Zscaler recommendation?</p><p><br></p><p>Thank you.</p><p><br></p><p><a href="https://help.zscaler.com/cloud-branch-connector/deploying-zscaler-cloud-connector-microsoft-azure" rel="noopener noreferrer" target="_blank">Deploying Zscaler Cloud Connector with Microsoft Azure | Zscaler</a></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Ab5y20CQA/cloud-connector-inactive-status","lastmod":"2024-09-04T08:37:04.000Z","id":"0D54u0000Ab5y20CQA"} -->
## Cloud Connector - Inactive status

- Source: https://community.zscaler.com/s/question/0D54u0000Ab5y20CQA/cloud-connector-inactive-status
- Type: Q&A
- Posted: 2024-09-03T14:32:43.000Z
- Last activity: 2024-09-04T08:37:04.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi There,</p><p><br></p><p>We've deployed Cloud connectors which in Azure and in the cloud connector portal it's showing as 'Inactive status'. Everything seems to be in place as per the Zscaler's recommendation (<a href="https://help.zscaler.com/cloud-branch-connector/troubleshooting-cloud-connector-microsoft-azure" rel="noopener noreferrer" target="_blank">Troubleshooting Cloud Connector with Microsoft Azure | Zscaler</a>)</p><p><br></p><p>While checking the bootup logs, noticed below message.</p><p>{nimbus.system.azure.update_system_config} Retrying in 60 seconds</p><p><br></p><p>In the runtime logs, noticed below message.</p><p>{janus.init.runtime.wait_systemconfig_load} System Config not yet initialized, waiting</p><p><br></p><p>Can you please advise on the same. Thank you.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Acbm3lCQA/dns-updates-on-branch-connector","lastmod":"2024-10-07T05:57:36.000Z","id":"0D54u0000Acbm3lCQA"} -->
## DNS Updates on Branch Connector

- Source: https://community.zscaler.com/s/question/0D54u0000Acbm3lCQA/dns-updates-on-branch-connector
- Type: Q&A
- Posted: 2024-10-01T19:20:04.000Z
- Last activity: 2024-10-07T05:57:36.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We are running into a problem having clients register dynamic DNS updates with AD integrated DNS. Through logs and packet captures, we verify that the DNS dynamic update packet makes it from the branch location to the DNS server. When the zone is set to only accept dynamic updates from secure sources, the DNS server refuses the updates. It works when the zone is set to accept nonsecure sources, but that is not a viable solution.</p><p><br></p><p>I have seen some posts about adding DHCP servers to the DNSUpdateProxy group, but that seems to be for domain machines. Is there a way to add our Zscaler app connectors to this group?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000RKi3x0AD/cloud-connector","lastmod":"2025-01-23T11:06:17.000Z","id":"0D5PJ00000RKi3x0AD"} -->
## cloud connector

- Source: https://community.zscaler.com/s/question/0D5PJ00000RKi3x0AD/cloud-connector
- Type: Q&A
- Posted: 2025-01-22T07:52:53.000Z
- Last activity: 2025-01-23T11:06:17.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi team,</p><p><br></p><p>We are currently in the process of deploying the Cloud Connector. As part of this deployment, it is necessary to create the service account?</p><p><br></p><p>Could you please provide information on the role of the service account in Cloud Connector deployment?</p><p><br></p><p>Regards.</p><p>Sairam</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000YCWn30AH/cloud-connector-traffic-forwarding","lastmod":"2025-05-27T16:41:02.000Z","id":"0D5PJ00000YCWn30AH"} -->
## Cloud Connector- Traffic Forwarding

- Source: https://community.zscaler.com/s/question/0D5PJ00000YCWn30AH/cloud-connector-traffic-forwarding
- Type: Q&A
- Posted: 2025-05-23T06:31:18.000Z
- Last activity: 2025-05-27T16:41:02.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Everyone,</p><p>We have already deployed Cloud Connectors (CC) in Azure across several regions, and at present, Multi-session AVD users are experiencing difficulties in resolving Wildcard FQDN URLs.</p><p>The suggested solution is to install the VDI agent and direct DNS requests through the Cloud Connectors by utilizing the DNS Gateway policy.</p><p><br></p><ol><li>As I mentioned, given that we have multiple regions across continents provisioned with the CC, how can I configure the DNS servers for each location within the DNS gateway policy when I only see the primary and secondary DNS server IP addresses? The issue with this setup is that I want the DNS requests to be handled locally rather than routing all requests to a single DNS server.</li><li>Furthermore, since Azure employs the concept of Service tags and service endpoints, according to Zscaler's recommendation (link provided below), it is advised to exclude the Azure ranges from being forwarded to CC in the VDI forwarding profile. As these IP ranges are updated weekly by Microsoft, this necessitates manual updates to prevent service disruptions. Is there a workaround available to eliminate the need for manual intervention?</li></ol><p><a href="https://help.zscaler.com/cloud-branch-connector/configuring-vdi-forwarding-profiles" rel="noopener noreferrer" target="_blank">Configuring VDI Forwarding Profiles | Zscaler</a></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000bnjAb0AI/checkpoint-and-zscaler-in-aws","lastmod":"2025-07-28T16:45:05.000Z","id":"0D5PJ00000bnjAb0AI"} -->
## Checkpoint and ZScaler in AWS

- Source: https://community.zscaler.com/s/question/0D5PJ00000bnjAb0AI/checkpoint-and-zscaler-in-aws
- Type: Q&A
- Posted: 2025-07-24T20:36:31.000Z
- Last activity: 2025-07-28T16:45:05.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello ZScaler Community, I would like to request your inputs on the following use-case in AWS. I am new to ZScaler so I might be lacking some fundamental knowledge around the offerings.</p><p><br></p><p>My team wants to use both Checkpoint Firewall and ZScaler Inspection for internet egress traffic. I have the following questions:</p><p><br></p><ol><li>Is this a common architecture, to have both Checkpoint and ZScaler in the internet egress path on AWS? Initial inspection by Checkpoint and then forwarded to ZScaler? In the past I had come across usecases where either of these products were used for egress in AWS, but <strong>not both</strong></li><li>I read about ZScaler Cloud Connector offering for AWS. How is this different from the tunnels that my team has setup (to ZScaler service edge) in the on-premise setup, through the edge WAN router.</li><li>Will there be tunnels established between Checkpoint security gateways in AWS and ZScaler Cloud Connectors (in AWS), or is there a better way to deal with this?</li><li>If ZScaler Cloud Connectors are not used, how can the traffic be forwarded from Checkpoint to ZScaler?</li><li>Are there some reference architecture around such a setup?</li></ol><p><br></p><p>Thanks!</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ezPXk0AM/routing-to-cloud-connector","lastmod":"2025-09-22T07:36:20.000Z","id":"0D5PJ00000ezPXk0AM"} -->
## Routing to Cloud Connector

- Source: https://community.zscaler.com/s/question/0D5PJ00000ezPXk0AM/routing-to-cloud-connector
- Type: Q&A
- Posted: 2025-09-18T08:05:54.000Z
- Last activity: 2025-09-22T07:36:20.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have a customer implementing Cloud Connector in AWS.</p><p>They want traffic from a server in AWS to go via Cloud Connector to reach a ZPA-Published App, but all other traffic to go via the default route. I don't want to set the CC as the Default Route, as then it would become part of the critical inline path to the internet for all traffic. How can I set a route to send just traffic destined for the ZPA App via the CC (as the destination IP isn't visible)?</p><p>I'm thinking a DNS entry that resolves a FQDN to a specific IP that is routed via the CC??</p><p><br></p><p>Thanks!</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000fXqoD0AS/unable-to-onboard-azure-vmss-cloud-connector","lastmod":"2025-10-26T12:56:09.000Z","id":"0D5PJ00000fXqoD0AS"} -->
## Unable to onboard Azure VMSS Cloud Connector

- Source: https://community.zscaler.com/s/question/0D5PJ00000fXqoD0AS/unable-to-onboard-azure-vmss-cloud-connector
- Type: Q&A
- Posted: 2025-09-28T08:49:32.000Z
- Last activity: 2025-10-26T12:56:09.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi There,</p><p><br></p><p>We are currently trying to deploy Zscaler Cloud Connectors VMSS in Azure via Terraform. VMs are deployed and able to reach Zscaler teanant with API calls (successful API login in Audit logs) however the Cloud connectors don't appear on the Cloud Connector portal. I've seen on some documetations, there is an option for enabling auto-scaling in the provisioning template. However I don't see that option on the GUI.</p><p>Appreciate feedback from anyone who deployed this before.</p><p><br></p><p>Namal</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000oWTAC0A4/aws-cloud-connector-fails-to-provision-python-39311-package-mismatch-in-ami","lastmod":"2026-02-03T04:11:46.000Z","id":"0D5PJ00000oWTAC0A4"} -->
## AWS Cloud Connector fails to provision - Python 3.9/3.11 package mismatch in AMI

- Source: https://community.zscaler.com/s/question/0D5PJ00000oWTAC0A4/aws-cloud-connector-fails-to-provision-python-39311-package-mismatch-in-ami
- Type: Q&A
- Posted: 2026-01-31T05:47:52.000Z
- Last activity: 2026-02-03T04:11:46.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>AWS Environment - AMI: ami-0a7dfac79f3f24414 / CC_AMI_PROD-05_06_2025 - Product Code: 2l8tfysndbav4tv2nfjwak3cu -</p><p><br></p><p>Deployment: Terraform + ASG, AWS Cloud Connector us-east-1/us-west-2 Issue</p><p><br></p><p>Cloud Connector fails to provision. zagent crashes on startup with: File "/sc/zagent/bin/zutil.py", line 19, in import requests</p><p><br></p><p>ModuleNotFoundError:</p><p><br></p><p>No module named 'requests' Root Cause Python symlink mismatch: $ readlink -f /usr/local/bin/python3 /usr/local/bin/python3.9&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><p><br></p><p># zagent uses this - NO requests $ /usr/local/bin/python3.11 -c "import requests; print(requests.__version__)" 2.32.3&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><p><br></p><p># requests IS installed here Site-packages comparison: - /usr/local/lib/python3.9/site-packages/ → empty (only README.txt) - /usr/local/lib/python3.11/site-packages/ → has requests 2.32.3</p><p><br></p><p>Proof Running zutil.py with Python 3.11 works (gets past imports): $ /usr/local/bin/python3.11 /sc/zagent/bin/zutil.py IndexError: list index out of range&nbsp;#</p><p><br></p><p>Expected - needs args, but imports succeeded</p><p><br></p><p>Fix Required ln -sf /usr/local/bin/python3.11 /usr/local/bin/python3 Request 1. Provide sudo password to apply fix, OR 2. Publish corrected AMI</p><p><br></p><p>Example testing: Below</p><p><br></p><p><span style="font-size: 14px; font-family: &quot;Cascadia Mono&quot;, Consolas, ui-monospace, Menlo, Monaco, monospace;">$&nbsp;/usr/local/bin/python3.11&nbsp;-c&nbsp;"import&nbsp;requests;&nbsp;print('OK')"</span></p><p><span style="font-size: 14px; font-family: &quot;Cascadia Mono&quot;, Consolas, ui-monospace, Menlo, Monaco, monospace;">OK</span></p><p><br></p><p><br></p><p><span style="font-size: 14px; font-family: &quot;Cascadia Mono&quot;, Consolas, ui-monospace, Menlo, Monaco, monospace;">$&nbsp;/usr/local/bin/python3&nbsp;-c&nbsp;"import&nbsp;requests;&nbsp;print('OK')"</span></p><p><span style="font-size: 14px; font-family: &quot;Cascadia Mono&quot;, Consolas, ui-monospace, Menlo, Monaco, monospace;">Traceback&nbsp;(most&nbsp;recent&nbsp;call&nbsp;last):</span></p><p><span style="font-size: 14px; font-family: &quot;Cascadia Mono&quot;, Consolas, ui-monospace, Menlo, Monaco, monospace;"> &nbsp;File&nbsp;"",&nbsp;line&nbsp;1,&nbsp;in&nbsp;</span></p><p><span style="font-size: 14px; font-family: &quot;Cascadia Mono&quot;, Consolas, ui-monospace, Menlo, Monaco, monospace;">ModuleNotFoundError:&nbsp;No&nbsp;module&nbsp;named&nbsp;'requests'</span></p><p><br></p><p><br></p><p><span style="font-size: 14px; font-family: &quot;Cascadia Mono&quot;, Consolas, ui-monospace, Menlo, Monaco, monospace;">$&nbsp;/usr/local/bin/python3.11&nbsp;/sc/zagent/bin/zutil.py</span></p><p><span style="font-size: 14px; font-family: &quot;Cascadia Mono&quot;, Consolas, ui-monospace, Menlo, Monaco, monospace;">Traceback&nbsp;(most&nbsp;recent&nbsp;call&nbsp;last):</span></p><p><span style="font-size: 14px; font-family: &quot;Cascadia Mono&quot;, Consolas, ui-monospace, Menlo, Monaco, monospace;"> &nbsp;File&nbsp;"/sc/zagent/bin/zutil.py",&nbsp;line&nbsp;783,&nbsp;in&nbsp;</span></p><p><span style="font-size: 14px; font-family: &quot;Cascadia Mono&quot;, Consolas, ui-monospace, Menlo, Monaco, monospace;"> &nbsp;&nbsp;&nbsp;cmd&nbsp;=&nbsp;sys.argv[1]</span></p><p><span style="font-size: 14px; font-family: &quot;Cascadia Mono&quot;, Consolas, ui-monospace, Menlo, Monaco, monospace;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;~~~~~~~~^^^</span></p><p><span style="font-size: 14px; font-family: &quot;Cascadia Mono&quot;, Consolas, ui-monospace, Menlo, Monaco, monospace;">IndexError:&nbsp;list&nbsp;index&nbsp;out&nbsp;of&nbsp;range</span></p><p><span style="font-size: 14px; font-family: &quot;Cascadia Mono&quot;, Consolas, ui-monospace, Menlo, Monaco, monospace;">$</span></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000pQS8S0AW/branch-connectors-inactive","lastmod":"2026-02-16T15:11:10.000Z","id":"0D5PJ00000pQS8S0AW"} -->
## Branch connectors inactive

- Source: https://community.zscaler.com/s/question/0D5PJ00000pQS8S0AW/branch-connectors-inactive
- Type: Q&A
- Posted: 2026-02-15T15:53:28.000Z
- Last activity: 2026-02-16T15:11:10.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Is anyone else have issues provisioning new branch connectors? I can get them to deploy but they are stuck on installed SMEDGE. </p><p><br></p><p>The same symptoms a few weeks back and Zscaler posted a global incident. </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000pSbir0AC/how-to-resolve-wildcard-fqdn-in-cloud-connectors","lastmod":"2026-02-16T15:14:59.000Z","id":"0D5PJ00000pSbir0AC"} -->
## How to resolve Wildcard FQDN  in Cloud Connectors

- Source: https://community.zscaler.com/s/question/0D5PJ00000pSbir0AC/how-to-resolve-wildcard-fqdn-in-cloud-connectors
- Type: Q&A
- Posted: 2026-02-16T14:28:09.000Z
- Last activity: 2026-02-16T15:14:59.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi There,</p><p><br></p><p>We have several workloads (AVD) provisioned in Azure that are accessing the Internet via the Cloud Connectors (CC) installed in Azure.</p><p><br></p><p>We are encountering an issue where these workloads are experiencing frequent disconnections when accessing wildcard FQDN that are random in nature. The client has subscribed to both ZIA and ZPA.</p><p><br></p><p>Do you have any recommendations on how to resolve this problem?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000qis5T0AQ/branch-connector","lastmod":"2026-03-19T15:07:04.000Z","id":"0D5PJ00000qis5T0AQ"} -->
## Branch Connector

- Source: https://community.zscaler.com/s/question/0D5PJ00000qis5T0AQ/branch-connector
- Type: Q&A
- Posted: 2026-03-11T07:17:48.000Z
- Last activity: 2026-03-19T15:07:04.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Branch Connector deployment scenario , traffic forwarding methods and routed tunnel purpose in BRANCH CONNECTOR </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000qixuf0AA/branch-connector","lastmod":"2026-03-11T07:17:46.000Z","id":"0D5PJ00000qixuf0AA"} -->
## Branch Connector

- Source: https://community.zscaler.com/s/question/0D5PJ00000qixuf0AA/branch-connector
- Type: Q&A
- Posted: 2026-03-11T07:17:46.000Z
- Last activity: 2026-03-11T07:17:46.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Branch Connector deployment scenario , traffic forwarding methods and routed tunnel purpose in BRANCH CONNECTOR </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000z7NO90AM/cloud-connector-has-two-service-nics-primary-and-secondary","lastmod":"2026-07-28T17:05:07.000Z","id":"0D5PJ00000z7NO90AM"} -->
## Cloud Connector has two service NICs: primary and secondary

- Source: https://community.zscaler.com/s/question/0D5PJ00000z7NO90AM/cloud-connector-has-two-service-nics-primary-and-secondary
- Type: Q&A
- Posted: 2026-07-24T07:04:18.000Z
- Last activity: 2026-07-28T17:05:07.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>If anyone knows about this, I would appreciate your help.</p><p>I deployed Zscaler Cloud Connector via the Azure Marketplace. As a result, the VM had three NICs by default: a primary service NIC, a secondary service NIC, and a management NIC.</p><p>However, the Cloud Connector deployment guide did not mention that multiple service NICs would be created.</p><p>- Is it correct to consider the secondary service NIC as a standby NIC?</p><p>- Is there any documentation that explains how the primary and secondary service NICs are used differently?</p>
<!-- /ZS-POST -->
