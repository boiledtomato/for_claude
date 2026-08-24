# Zscaler Zenith Community — ZPA — Private Access (part 1)

Source: https://community.zscaler.com
Generated: 2026-08-24 02:21 UTC
Posts in this file: 230

> これはユーザー投稿のコミュニティフォーラムの内容であり、Zscaler の公式ドキュメントではない。

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmU7CAI/video-aws-cloudformation-deployment","lastmod":"2023-05-31T09:08:53.000Z","id":"0D54u00009evmU7CAI"} -->
## [VIDEO] AWS CloudFormation Deployment

- Source: https://community.zscaler.com/s/question/0D54u00009evmU7CAI/video-aws-cloudformation-deployment
- Type: Q&A
- Posted: 2022-08-10T14:28:01.000Z
- Last activity: 2023-05-31T09:08:53.000Z
- Answers: 0
- Likes: 4
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Cloud Connector is a virtual appliance within AWS used to forward cloud workload traffic to the Zero Trust Exchange. It can be deployed within an AWS environment using both Terraform and, as a more native scripting option, CloudFormation. Zscaler CloudFormation scripts assume a customer already has an existing cloud deployment that they wish to integrate Zero Trust Network Access principles within. As such, VPCs, NAT Gateways, Internet Gateways, Subnets, and Route Tables should already be configured prior to running these scripts.

- The Pre-deployment Template ensures certain prerequisites are met prior to running any additional CloudFormation scripts. This script should be run first before running the Starter Deployment Template.
- The Starter Deployment Template will install a single Cloud Connector appliance within the Subnet chosen and is a requirement for all other scripts. In fact, we recommend that you run this script multiple times to install multiple Cloud Connector VMs within various Availability Zones to satisfy High Availability requirements.
- The Add-on Template for ZPA will add AWS Route 53 functionality. You can learn more about how ZPA interacts with Cloud Connector via the other videos on this Communities page. In a nutshell, Route 53 allows an administrator to influence cloud workload DNS requests to cross over the Cloud Connector appliance - allowing the appliance to then proxy that traffic by responding to the DNS Request with a synthetic IP address.
- The Add-on Template for ZPA and High-Availability will add support for both ZPA and AWS Lambda functionality. Please note that AWS Lambda functionality exists to provide backward compatibility for customers who have not yet migrated to Gateway Load Balancer. Zscaler recommends running the Add-on Template with Gateway Load Balancer script instead if a customer is seeking High Availability.
- The Add-on Template with Gateway Load Balancer (GWLB), as the name would imply, installs a Gateway Load Balancer as well as all the necessary GWLB endpoints and Target Group necessary for High Availability.

In this video, we’ll explore:

[0:00 to 0:44] Pre-requisites and overview of CloudFormation
 [0:44 to 1:48] How are CloudFormation scripts obtained, and what does each do?
 [1:48 to 2:50] Using the Starter Deployment Template to install a pair of Cloud Connectors
 [2:50 to 3:38] Installing Gateway Load Balancer
 [3:38 to 4:00] Implementing Zscaler Private Access (Route 53)
 [4:00 to 4:35] Key takeaways

"Transcript
Hello, my name is Aaron and I’m one of the Principal Technical Product Specialists for Zscaler Cloud Workload Protection.

In this video, we’ll explore how Zscaler Cloud Connector can be provisioned within AWS using Cloud Formation Templates. Before you get started, make sure to check out the AWS Pre-Requisites video linked in the description as there are some items that need to be set up prior to running these scripts.

Though CloudFormation scripts can be used in greenfield situations, their value shines when a customer is seeking brownfield integration, since many of the aforementioned prerequisites are generally already satisfied if a customer has an existing AWS buildout. CloudFormation scripts are written in YAML and can be downloaded from the Cloud Connector portal:

- The Starter Deployment Template will instantiate a single Cloud Connector appliance and associate it with the Subnet and Route Table specified in the CloudFormation workflow. This script is a requirement in order to run any of the other CloudFormation scripts.
- The Add-on Template with ZPA script will instantiate Route 53 resources for outbound DNS resolution and redirection to the ZPA service for use-cases where Zscaler Zscaler Private Access is the requirement. For more information on ZPA, DNS redirection, and its interaction with Cloud Connector, please check out the AWS DNS Setup for ZPA video linked in the description.
- The Add-on Template with High-Availability script will instantiate AWS Lambda functionality for high availability. This script assumes that a pair of Cloud Connector instances already exist (with associated Subnets, Route Tables, and Availability Zones) and that a High Availability port was selected during their instantiation. It should be noted that, as of this recording, AWS Gateway Load Balancer is now also supported and will become the new recommendation for High Availability instead of Lambda. AWS Gateway Load Balancer CloudFormation scripts will be available for download in the Cloud Connector portal as well and will be used as the basis for this demonstration.

Start by navigating to your AWS console and searching for CloudFormation. Click the Create Stack button and choose 'With new resources. Upload the Starter Deployment Template script. Provide a stack name, then select the resources that fit your deployment. In this case, we’ll choose a pre-configured VPC, Subnet, Availability Zone, and Keypair. For testing purposes, Zscaler recommends the T3.medium Instance Type. For production deployments, choose C5.large or M5.large. Provide the name of your Secrets Manager object as well as an HTTP port. Though the HTTP port is optional, Zscaler highly recommends a port be entered here so that high availability can be configured. This port identifies a heartbeat service that the appliance uses to report its current health to the AWS Gateway Load Balancer or Lambda function.

Click the Next button, followed by Next again… acknowledge the changes and click the Create Stack button. The script begins to execute and deploy the Cloud Connector resources.

It is highly recommended that you run this script a second time against an adjacent Availability Zone to provide fault tolerance for your implementation.

Once the appliances have been deployed and have registered with the Cloud Connector portal, you can proceed with installing the Gateway Load Balancer service. In the same way that the Cloud Connector appliances were installed, upload the GWLB macro script first. This script pre-configures the environment for GWLB. Once this script is executed, create a new CloudFormation stack with the GWLB script. Select the instances you created previously and identify the HTTP port they were instantiated with.

Choose whether to enable cross-zone load-balancing. GWLB will, by default, attempt to maintain Availability Zone affinity. In the event of an appliance failure, cross-zone load-balancing can be turned on using this dropdown - allowing GWLB to ignore Availability Zone affinity and forward traffic to any available appliance. Be aware that this may incur additional costs from AWS.

Lastly, if ZPA integration is desired, create a CloudFormation stack to enable Route53 functionality. Here, input your Cloud Connector and ZPA cloud name. Application Segment FQDNs defined in ZPA can then be configured in the Domain Name fields. This script will instruct Route 53 to redirect DNS traffic for these domains through the Cloud Connector appliance - allowing the appliance to proxy application traffic via synthetic IP Addresses.

Though useful in Greenfield situations, CloudFormation scripts shine when implemented in a Brownfield environment with existing infrastructure.

- You can download CloudFormation scripts from the Cloud Connector portal via the Administration > Deployment Templates menu.
- Make sure you have met the pre-requisites prior to running a CloudFormation script, then upload and execute them from your AWS console.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmdwCAA/zpa-manual-selection","lastmod":"2023-05-31T08:43:15.000Z","id":"0D54u00009evmdwCAA"} -->
## ZPA Manual Selection

- Source: https://community.zscaler.com/s/question/0D54u00009evmdwCAA/zpa-manual-selection
- Type: Q&A
- Posted: 2023-02-28T07:41:51.000Z
- Last activity: 2023-05-31T08:43:15.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi there.

Wondering if there will ever be an option / plan to allow customer to manually select / exclude certain ZPA broker (by country), similar to the PAC files. Usage scenario e.g. users in the Philippines would have a better connectivity (ISP) to SG than to Taipei / HK.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmdzCAA/not-getting-any-logs-for-configured-app-on-zpa","lastmod":"2023-05-31T08:12:40.000Z","id":"0D54u00009evmdzCAA"} -->
## Not getting any logs for configured app on ZPA

- Source: https://community.zscaler.com/s/question/0D54u00009evmdzCAA/not-getting-any-logs-for-configured-app-on-zpa
- Type: Q&A
- Posted: 2023-02-25T19:03:51.000Z
- Last activity: 2023-05-31T08:12:40.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Team,

We have configured a printer on ZPA portal to be accessed from remote location, but when user is trying to print files on printer through ZPA he’s not able to print, we tried checking in logs if there’s any policy issue ir port no issue, but we are not getting any loga for that printer traffic, we tried ping and telnet command from app connector to the printer everything is okay but still user cant access printer and we are also not getting any logs for that traffic please help if anyone has solution for this.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evme0CAA/zscaler-virtual-service-edge-connection-to-zscaler-cloud","lastmod":"2023-05-31T08:12:40.000Z","id":"0D54u00009evme0CAA"} -->
## ZScaler Virtual Service Edge connection to ZScaler Cloud

- Source: https://community.zscaler.com/s/question/0D54u00009evme0CAA/zscaler-virtual-service-edge-connection-to-zscaler-cloud
- Type: Q&A
- Posted: 2023-02-25T17:54:56.000Z
- Last activity: 2023-05-31T08:12:40.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,
 is there any notification to a customer if ZScaler Virtual Service Edge [About Virtual Service Edges | Zscaler](https://help.zscaler.com/zia/about-virtual-service-edges) looses connectivity or registration to ZScaler Cloud?
 Thanks,
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmeiCAA/dns-resolution-for-split-vpn-traffic","lastmod":"2023-05-31T08:12:44.000Z","id":"0D54u00009evmeiCAA"} -->
## DNS Resolution for Split VPN traffic

- Source: https://community.zscaler.com/s/question/0D54u00009evmeiCAA/dns-resolution-for-split-vpn-traffic
- Type: Q&A
- Posted: 2023-01-27T22:07:27.000Z
- Last activity: 2023-05-31T08:12:44.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are currently using GlobalProtect VPN alongside ZPA to route voice traffic to our datacenter. To date, we have used only IP addresses for destinations that get routed over GlobalProtect which has worked fine. We now have a use case for directing traffic over GlobalProtect using a DNS name that can only be resolved by our internal DNS servers. We currently do not allow DNS traffic to traverse ZPA, per Zscaler’s recommendations. So our dilemma is, how do our clients resolve a DNS name to an internal IP if we don’t allow DNS traffic. Has anyone seen anything like this or have any suggestions? We have considered populating the local Windows host file with the DNS/IP, but would prefer not to go down that route.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmesCAA/about-the-browser-access-category","lastmod":"2023-05-31T08:12:44.000Z","id":"0D54u00009evmesCAA"} -->
## About the Browser Access category

- Source: https://community.zscaler.com/s/question/0D54u00009evmesCAA/about-the-browser-access-category
- Type: Q&A
- Posted: 2023-01-20T19:55:10.000Z
- Last activity: 2023-05-31T08:12:44.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

(Replace this first paragraph with a brief description of your new category. This guidance will appear in the category selection area, so try to keep it below 200 characters.)

Use the following paragraphs for a longer description, or to establish category guidelines or rules:

- Why should people use this category? What is it for?
- How exactly is this different than the other categories we already have?
- What should topics in this category generally contain?
- Do we need this category? Can we merge with another category, or subcategory?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmf0CAA/about-the-zpa-category","lastmod":"2023-05-31T08:12:44.000Z","id":"0D54u00009evmf0CAA"} -->
## About the ZPA category

- Source: https://community.zscaler.com/s/question/0D54u00009evmf0CAA/about-the-zpa-category
- Type: Q&A
- Posted: 2023-01-13T16:32:36.000Z
- Last activity: 2023-05-31T08:12:44.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

(Replace this first paragraph with a brief description of your new category. This guidance will appear in the category selection area, so try to keep it below 200 characters.)

Use the following paragraphs for a longer description, or to establish category guidelines or rules:

- Why should people use this category? What is it for?
- How exactly is this different than the other categories we already have?
- What should topics in this category generally contain?
- Do we need this category? Can we merge with another category, or subcategory?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmffCAA/zpa-application-access-logs-in-splunk","lastmod":"2023-05-31T08:43:37.000Z","id":"0D54u00009evmffCAA"} -->
## ZPA Application Access Logs in Splunk

- Source: https://community.zscaler.com/s/question/0D54u00009evmffCAA/zpa-application-access-logs-in-splunk
- Type: Q&A
- Posted: 2022-12-27T17:07:40.000Z
- Last activity: 2023-05-31T08:43:37.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

In the ZPA Application Access Logs that flow in to Splunk under sourcetype=“zscalerlss-zpa-app?, I can capture the Username but how to capture the User’s hostname. I don’t see any field that captures the User’s hostname.

Thank you

Kumar
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmfkCAA/force-road-warriors-to-specific-zia-service-edge","lastmod":"2023-07-06T12:52:42.000Z","id":"0D54u00009evmfkCAA"} -->
## Force road warriors to specific ZIA service edge

- Source: https://community.zscaler.com/s/question/0D54u00009evmfkCAA/force-road-warriors-to-specific-zia-service-edge
- Type: Q&A
- Posted: 2022-12-20T15:17:33.000Z
- Last activity: 2023-07-06T12:52:42.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi all,

I would like to force a set of road warriors to go to a specific ZIA Service Edge (ZSE) irrespective of their egress IP address. For example, some users in Indonesia are surfacing in Hong Kong (due to the ISP route) and use the Tokyo ZSE and I want to force them to go to either Hong Kong or Singapore.

We are using Tunnel 2.0 and I originally used the COUNTRY macro but the maxmind db is not reliable and the results were terrible.

Now, I have used the following script (snippet) in the App Profile PAC file where the IP addresses are the egress IP adresses the users surface from:

var egressip = “${SRCIP}?;

if (shExpMatch(egressip,“x.x.x.50?) ||
 shExpMatch(egressip,“x.x.x.170?) ||
 shExpMatch(egressip,"x.x.x.210 "))
 {
 return “PROXY [hkg3.sme.zscaler.net:80](http://hkg3.sme.zscaler.net:80); PROXY [sin4.sme.zscaler.net:80](http://sin4.sme.zscaler.net:80); DIRECT?;
 }

This is also failing. Any suggestions on the scripting?

Much appreciated,
 Rallis

PS: I have not done any mods on the forwarding profile PAC file.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmfvCAA/zpa-app-connector","lastmod":"2023-05-31T08:43:49.000Z","id":"0D54u00009evmfvCAA"} -->
## ZPA App Connector

- Source: https://community.zscaler.com/s/question/0D54u00009evmfvCAA/zpa-app-connector
- Type: Q&A
- Posted: 2022-12-15T07:02:32.000Z
- Last activity: 2023-05-31T08:43:49.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

I want to deploy another App connector and I want to paste the provisioning key to the nano editor and not working with that

I tried to use putty but the connection was not complete
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmfwCAA/about-the-zpa-category","lastmod":"2023-05-31T08:12:48.000Z","id":"0D54u00009evmfwCAA"} -->
## About the ZPA category

- Source: https://community.zscaler.com/s/question/0D54u00009evmfwCAA/about-the-zpa-category
- Type: Q&A
- Posted: 2022-12-14T17:17:22.000Z
- Last activity: 2023-05-31T08:12:48.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

(Replace this first paragraph with a brief description of your new category. This guidance will appear in the category selection area, so try to keep it below 200 characters.)

Use the following paragraphs for a longer description, or to establish category guidelines or rules:

- Why should people use this category? What is it for?
- How exactly is this different than the other categories we already have?
- What should topics in this category generally contain?
- Do we need this category? Can we merge with another category, or subcategory?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmgGCAQ/zpa-zsatray-credential-prompt-on-reauthentication","lastmod":"2023-07-07T11:22:26.000Z","id":"0D54u00009evmgGCAQ"} -->
## ZPA: ZSATray credential prompt on reauthentication

- Source: https://community.zscaler.com/s/question/0D54u00009evmgGCAQ/zpa-zsatray-credential-prompt-on-reauthentication
- Type: Q&A
- Posted: 2022-12-12T08:53:58.000Z
- Last activity: 2023-07-07T11:22:26.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

I am having an issue which is driving me nuts, and I cannot really explain it, I am hoping someone can provide some insight on why this is happening.

Background:

- IDP is ADFS.
- I am forwarding auth requests to internal “leg? of the ADFS by having a no timeout for the ADFS, so it can always be reached via the tunnel.
- We have username, password and OTP auth via a OpenOTP
- When timeout happens, instead of reaching the usual ADFS username and password the screen below appears.

[[image] image922×688 89.8 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O5e8AAC)

I am quite sure before I was getting the ADFS prompt for OpenOTP token, but now, something has changed (perhaps I changed something on Zscaler side).

Can you please help me on this strange issue?

Thanks,
 Mario
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmgZCAQ/removal-pending-device-state","lastmod":"2023-05-31T08:43:52.000Z","id":"0D54u00009evmgZCAQ"} -->
## Removal Pending Device State

- Source: https://community.zscaler.com/s/question/0D54u00009evmgZCAQ/removal-pending-device-state
- Type: Q&A
- Posted: 2022-12-08T16:54:37.000Z
- Last activity: 2023-05-31T08:43:52.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

I have about 4% of devices in our environment under this state.

Few questions:

- Having a device here on this state it does not mean that they are consuming a license, correct? As this is the device and not the user.
- Is it safe to remove this number of devices on this state? What would be benefit of doing so? Just to clean up the database and remove these PCs?
- Clearing these PCs on this state, would it release any ZPA licenses? Only if the user had one PC and PC is under this state of removal pending, correct?
- Is it safe to say that a PC on this state it is not using ZIA and ZPA, correct?

Thank you
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmgzCAA/zpa-logs-status-code-se-connection-closed-by-service-edge","lastmod":"2023-07-06T12:53:16.000Z","id":"0D54u00009evmgzCAA"} -->
## ZPA logs Status code (SE: Connection closed by Service Edge)

- Source: https://community.zscaler.com/s/question/0D54u00009evmgzCAA/zpa-logs-status-code-se-connection-closed-by-service-edge
- Type: Q&A
- Posted: 2022-11-24T11:12:49.000Z
- Last activity: 2023-07-06T12:53:16.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Team,

One for your location users are not able to access a server via ZPA. while in ZPA diagnosis logs I can see this status “SE: Connection closed by Service Edge? and action is allowed.
 But user cant connect with the application.

I am not able to find info about this status…
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmh3CAA/nonweb-application-mechanism","lastmod":"2023-05-31T08:43:58.000Z","id":"0D54u00009evmh3CAA"} -->
## Non-web application mechanism

- Source: https://community.zscaler.com/s/question/0D54u00009evmh3CAA/nonweb-application-mechanism
- Type: Q&A
- Posted: 2022-11-21T17:08:42.000Z
- Last activity: 2023-05-31T08:43:58.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

How ZIA handle non-web application (non-port 80 & non-port 443) traffic?

Is it send to Zscaler Service Edge via TLS-Tunnel?
 If I insert a physical firewall between Client Connector and Zscaler Service Edge, what application & port traffic will be shown?
 What is the source IP and destination IP of the traffic packet?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhCCAQ/need-to-configure-duo-for-our-zia-and-zpa-admin-portal","lastmod":"2023-07-06T12:53:25.000Z","id":"0D54u00009evmhCCAQ"} -->
## Need to configure DUO for our ZIA and ZPA admin portal

- Source: https://community.zscaler.com/s/question/0D54u00009evmhCCAQ/need-to-configure-duo-for-our-zia-and-zpa-admin-portal
- Type: Q&A
- Posted: 2022-11-18T14:06:23.000Z
- Last activity: 2023-07-06T12:53:25.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Team ,

Can someone help me with the doc for integrating the DUO to login into our Admin portals .

Regards,
 Durga Chitturi.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmi9CAA/detection-when-switching-between-vpn-trusted-network-and-off-trusted-network","lastmod":"2023-07-06T12:54:23.000Z","id":"0D54u00009evmi9CAA"} -->
## Detection when switching between VPN Trusted network and Off Trusted Network

- Source: https://community.zscaler.com/s/question/0D54u00009evmi9CAA/detection-when-switching-between-vpn-trusted-network-and-off-trusted-network
- Type: Q&A
- Posted: 2022-10-10T19:53:23.000Z
- Last activity: 2023-07-06T12:54:23.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

In my current setup we are in a situation, where users might need to switch between our traditional VPN and ZPA.

Both clients coexist, and ZPA behaves correctly and swtches to VPN trusted when the VPN is launched.

The problem appears on the opposite direction, when a user disconnects from the VPN, the ZCC client (3.8.0.102) appears to be way more time than expected on “VPN Trusted Network? status, until a point where it automatically switches to “Off Trusted Network?. This time seems to vary between 30 seconds and something above 1’10". What bothers me is in fact this lack of predictability and this appears to be for way more time than expected.

Hence my questions:

- What is triggering the detection between Off Trusted and VPN Trusted?
- Can we change the speed of this detection process?
- What could we do to increase the detection speed?

Thanks,
 Mario
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmiDCAQ/virtual-service-edge-adding-multiple-route-to-em5","lastmod":"2023-05-31T08:13:01.000Z","id":"0D54u00009evmiDCAQ"} -->
## Virtual Service Edge - Adding multiple route to em5

- Source: https://community.zscaler.com/s/question/0D54u00009evmiDCAQ/virtual-service-edge-adding-multiple-route-to-em5
- Type: Q&A
- Posted: 2022-10-07T08:10:58.000Z
- Last activity: 2023-05-31T08:13:01.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

I have problem for adding multiple route to em5 interface on virtual service edge. Is somebody know syntax on /etc/rc.conf in order to have multiple route

When i add multiple route, only the last line have been added to interface routing table.
 route_em5_internal=“-net 10.110.1.0/24 192.168.1.1?
 route_em5_internal=“-net 10.192.0.0/22 192.168.1.1?

Thank you
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmiGCAQ/necessity-to-upload-a-certificate-to-zpa-admin-portal","lastmod":"2023-05-31T08:44:25.000Z","id":"0D54u00009evmiGCAQ"} -->
## Necessity to upload a certificate to ZPA Admin Portal

- Source: https://community.zscaler.com/s/question/0D54u00009evmiGCAQ/necessity-to-upload-a-certificate-to-zpa-admin-portal
- Type: Q&A
- Posted: 2022-10-04T09:37:02.000Z
- Last activity: 2023-05-31T08:44:25.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Dear Team,

Is it necessary to upload the certificate used to access the remote desktop server to the ZPA Admin Portal?
 End user goes through ZPA to access to the remote desktop server.

Thanks,

P.S. I have corrected because the subject was wrong.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmihCAA/user-is-facing-dns-resolution-issue-when-connected-to-zpa","lastmod":"2023-05-31T08:44:34.000Z","id":"0D54u00009evmihCAA"} -->
## User is facing DNS resolution issue when connected to ZPA

- Source: https://community.zscaler.com/s/question/0D54u00009evmihCAA/user-is-facing-dns-resolution-issue-when-connected-to-zpa
- Type: Q&A
- Posted: 2022-09-15T16:59:46.000Z
- Last activity: 2023-05-31T08:44:34.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We have one user , when he is trying to access the server by using the hostname it is not getting connected but when user is able to access with the IP address . and did work around by configuring the host entry on the user name .

So we have checked on our admin portal and could see that hostname is configured on our app. segment with disabled DNS port means we have alowed only 8080 and 443 ports .

Can someone explain why it is not working with the hostname?
 is there any thing needs to be done from my end to fix this DNS issue .
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmilCAA/zpa-cloud-connector-in-3rd-party-iaassaas","lastmod":"2023-05-31T09:28:59.000Z","id":"0D54u00009evmilCAA"} -->
## ZPA cloud connector in 3rd party IaaS/SaaS

- Source: https://community.zscaler.com/s/question/0D54u00009evmilCAA/zpa-cloud-connector-in-3rd-party-iaassaas
- Type: Q&A
- Posted: 2022-09-12T08:09:52.000Z
- Last activity: 2023-05-31T09:28:59.000Z
- Answers: 2
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi, we are provider of cloud application, so far we have been connecting clients with our cloud infra using site 2 site VPN.

Now, one of new clients is using ZScaler and asking if we could establish for them ZPA app connector in our cloud to enable them access to their application tenatn. The goal is to avoid using VPN connectivity and leverage existing client’s ZScaler estate to enable secure network.

Could you please point me to any materials regarding such a setup?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjRCAQ/hardware-requirements-for-vmware-esx-to-run-virtual-service-edge-vms","lastmod":"2025-10-31T00:44:48.000Z","id":"0D54u00009evmjRCAQ"} -->
## Hardware requirements for VmWare ESX to run Virtual Service Edge VM's

- Source: https://community.zscaler.com/s/question/0D54u00009evmjRCAQ/hardware-requirements-for-vmware-esx-to-run-virtual-service-edge-vms
- Type: Q&A
- Posted: 2022-08-14T22:22:42.000Z
- Last activity: 2025-10-31T00:44:48.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are looking to run Virtual Service Edge Appliances on VmWare ESX hypervisor. As we learn more about the VSEs, it’s becoming obvious that it’s better to allocate dedicated ESX servers as required promiscuous mode on vSwitch could chew a lot of memory resources.
 Could someone recommend the hardware specs for physical servers?
 There is another option with Physical Service Edge, but it is prohibitively expensive.

Thanks,
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjZCAQ/video-zpa-workflow","lastmod":"2024-01-04T20:58:31.000Z","id":"0D54u00009evmjZCAQ"} -->
## [VIDEO] ZPA Workflow

- Source: https://community.zscaler.com/s/question/0D54u00009evmjZCAQ/video-zpa-workflow
- Type: Q&A
- Posted: 2022-08-10T15:01:37.000Z
- Last activity: 2024-01-04T20:58:31.000Z
- Answers: 0
- Likes: 6
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Assuming that Cloud Connector has been instantiated and traffic directed through it, we can now add support for Zscaler Private Access. This use case is growing in popularity as organizations seek to depart from legacy VPN technologies to interconnect cloud and on-premise workloads. Cloud Connector integrates with ZPA by proxying workload traffic through synthetic IP addresses and forged DNS responses to workload queries. As traffic is directed through the Cloud Connector, it is proxied through the nearest ZPA Broker which, in turn, sends this traffic to the destination App Connector that will service this traffic.

In this video, we’ll explore:

[0:00 to 1:20] Overview of ZPA with Cloud Connector
 [1:20 to 3:24] Prerequisites and Cloud Connector interaction with DNS
 [3:24 to 5:13] Configuring ZPA Access Policy and Client Forwarding Policy
 [5:13 to 6:21] Key Takeaways

Transcript
Hello, my name is Aaron and I’m one of the Principal Technical Product Specialists for Zscaler Cloud Workload Protection.

In this video, we’ll discuss how Zscaler Private Access can be leveraged to secure cloud workloads.

Assuming that Cloud Connector has been instantiated and you’ve adjusted routing to direct traffic through it, we can now add support for Zscaler Private Access. Be sure to check out the AWS and Azure provisioning videos for help in provisioning Cloud Connector, if necessary. This use case is growing in popularity as organizations seek to depart from legacy VPN technologies to interconnect cloud and on-premise workloads. An important consideration with Cloud Connector is that it is designed to facilitate outbound workload traffic towards a remote destination. When the destination is in a customer-controlled location (such as another Region of the same Cloud Service Provider, an entirely different Cloud Service Provider, or even an on-premise data center), we must consider how this traffic ingresses into the remote facility. We do this using the Zscaler App Connector appliance. More specifically, App Connector VMs will sit adjacent to the remote workloads they provide access to. In many cases, this means that Cloud Connector and App Connector will sit side-by-side to provide both inbound and outbound connectivity to the cloud.

In the interest of time, this video will not focus on the deployment of App Connector and will assume that these appliances have already been deployed. You can verify this in your ZPA Dashboard by browsing Administration > App Connectors and ensuring that your appliance is deployed and connected.

Likewise, when Cloud Connector is deployed, it is automatically added to your ZPA portal as well. You can verify this by navigating to Administration > Cloud Connector. Any deployed Cloud Connectors should appear in this output, along with their associated Cloud Connector Group.

As a next step, review the App Segments configured within your ZPA portal. You can do this from Administration > App Segments. This list of App Segments, and their associated criteria, is what Cloud Connector will download and use to identify outbound cloud traffic that requires redirection to the ZPA service. Creating new App Segments is not the focus of this video, so please check out the Zscaler Help Portal for more information, should this step not be completed already.

Finally, we need to ensure that Cloud Connector is properly set up to forward ZPA traffic. Remember, Cloud Connector focuses on outbound traffic from the cloud. To do this for ZPA traffic, Cloud Connector intercepts and proxies DNS requests. In essence, cloud workloads send their DNS request for ZPA-based App Segments to their configured DNS server, which presumably, crosses over the Cloud Connector. This DNS request is then validated against the downloaded list of App Segments that the Cloud Connector downloaded. If a match is found, the Cloud Connector responds back to the requesting workload with a synthetic IP address. The cloud workload then begins to send its traffic to this IP address, which is hosted on the Cloud Connector, and on into the ZPA fabric. You will need to ensure that the synthetic IP space used for this proxied traffic is routed to the Cloud Connector. As such, you may need to add a route in your AWS or Azure infrastructure if there is no Default Route that accomplishes this automatically. For more information on DNS interception, DNS policies, synthetic IP addressing, and the like, check out the accompanying videos on Gateway Configuration, Forwarding Policy, and AWS and Azure DNS Setup for ZPA.

It should also be noted here that IP-based App Segments obviously do not require DNS redirection, so these segments rely purely on routing through the Cloud Connector.

As mentioned in previous videos, for ZPA customers, Cloud Connector automatically builds Forwarding Policy for ZPA. You can review this pre-built policy from the Forwarding > Traffic Forwarding menu of the Cloud Connector portal.

Now that we’ve verified the basics, the final step is to return to the ZPA portal to review our Access Policies and Client Forwarding Policies.

Put simply, our Access Policies (if present) need to be adjusted to ensure that cloud workloads are allowed to send traffic into the ZPA fabric for certain App Segments. Navigating to Administration > Access Policy, you can review the existing policies. If existing Access Policies exist to define user access, they can be adjusted to incorporate cloud workloads. However, we recommend that new Access Policies be created to define cloud workload access to App Segments. This is because cloud workloads generally don’t have user attributes tied to their traffic and, as such, attributes like SCIM and SAML cannot be used to restrict access. Instead, we can use Client Type and Cloud Connector Group as our match criteria to permit or deny traffic towards the App Segment.

Likewise, Client Forwarding Policies can be manipulated to determine whether or not the Cloud Connector should even forward traffic into the ZPA fabric. By default, all traffic from all clients (whether cloud or user) that matches a ZPA App Segment and is permitted by Access Policy is forwarded into the ZPA fabric. Depending on the organizational policy, this may or may not be desired. For instance, in use-cases that invoke Source IP Anchoring, the Client Forwarding Policy may need to be adjusted to allow certain traffic types to bypass ZPA initially. Similarly, for hybrid-cloud environments where infrastructure like Active Directory spans across multiple cloud and on-premise destinations, you may decide to allow this traffic to bypass ZPA and be forwarded natively into the cloud. In most cases, the default Client Forwarding Policy will suffice for Cloud Connector deployments. But, it’s good to know that you can manipulate the logic here that Cloud Connector will use to either forward traffic into ZPA or allow the traffic to bypass.

– Cloud Connector appliances automatically register their cloud Locations (VPC and VNet) that they serve within the Cloud Connector and ZPA dashboards

– To facilitate ZPA traffic, Cloud Connector intercepts and proxies cloud workload traffic. This is primarily accomplished by manipulating DNS queries in the cloud

– Cloud Connector appliances automatically download ZPA App Segments and, by default, will have a pre-built policy to begin forwarding traffic towards these segments

– ZPA Access Policy can be used to permit or deny access to specific App Segments. Since cloud workloads generally don’t have user attributes attached to their traffic, Client Type and Cloud Connector Group is used as the match criteria for the policy

– Client Forwarding Policy can be used to influence whether or not the Cloud Connector allows cloud workload traffic to bypass ZPA, even if there’s an App Segment match. This is particularly useful in use-cases that require Source IP Anchoring (SIPA), where traffic bypasses ZPA initially
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjoCAA/iphone-users-unable-to-authenticate-to-zscaler-when-ondemand-vpn-is-configured","lastmod":"2023-05-31T08:44:36.000Z","id":"0D54u00009evmjoCAA"} -->
## iPhone Users Unable to Authenticate to Zscaler When On-Demand VPN is Configured

- Source: https://community.zscaler.com/s/question/0D54u00009evmjoCAA/iphone-users-unable-to-authenticate-to-zscaler-when-ondemand-vpn-is-configured
- Type: Q&A
- Posted: 2022-08-10T12:07:15.000Z
- Last activity: 2023-05-31T08:44:36.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

First let me provide a background for easy understanding.

I am deploying Zcaler to our work iPhones, the configuration and deployment have been done to a test group and this seem to be working. The idea is to make sure that users are unable to access the internet without being singed in to Zscaler, this we have attempted to do by using **Strict Enforcement** in our configuration in our VPN profile in Microsoft Endpoint Manager. But we have however realized that a user can go to the phone setting and switch off VPN which then enable him to access the internet even without signing in to Zscaler.

In an attempt to prevent this from happening, we set the automatic VPN setting in our configuration in Microsoft Endpoint Manager to **On-Demand VPN**. This now automatically turn the device VPN back on when the user attempt to access the internet (if the device VPN was off).

The challenge I am now having is that despite adding [login.microsoftonline.com](http://login.microsoftonline.com) and
 [authsp.prod.zpath.net](http://authsp.prod.zpath.net) to exclusion so the user can be able to authenticate and sign in to Zscaler, the login process is no longer going on successfully, it seem it is being blocked. I do not know if there is another url i need to exclude that is involved in the login process or if there is something else i need to do to ensure users are able to login successfully to zscaler.

Please I need help.

Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjsCAA/zpa-reauthenticate-when-user-network-connection-changes","lastmod":"2023-05-31T08:45:01.000Z","id":"0D54u00009evmjsCAA"} -->
## ZPA reauthenticate when user network connection changes

- Source: https://community.zscaler.com/s/question/0D54u00009evmjsCAA/zpa-reauthenticate-when-user-network-connection-changes
- Type: Q&A
- Posted: 2022-08-09T04:34:55.000Z
- Last activity: 2023-05-31T08:45:01.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are busy implementing ZPA for our internal network to replace our existing VPN connection technology.

We are using AzureAD for SAML SSO and also have a conditional access policy to require MFA when a user is not accessing from a trusted location.

What we’ve found is if a user is in the office, opens the client connector and signs in, when they then switch to a hot spot and disconnect from the internal network their existing token is retained and they are automatically granted access in ZPA.

What we are hoping to implement is that when the user transitions from the trusted network to an un-trusted network that the application re-authenticates the user. Then it should hit the conditional access policy and the user should get an MFA prompt.

We are trying to not have users get prompted by MFA multiple times a day as users do have the flexibility to work from home (I imagine a user getting an MFA prompt every 2 hours when working from home would get very frustrating).

Is it possible to configure this on the Zscaler side? Or am I missing something in the AzureAD SAML configuration?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjwCAA/selectively-enable-private-access-only-for-users","lastmod":"2023-05-31T08:45:01.000Z","id":"0D54u00009evmjwCAA"} -->
## Selectively enable Private Access only for users

- Source: https://community.zscaler.com/s/question/0D54u00009evmjwCAA/selectively-enable-private-access-only-for-users
- Type: Q&A
- Posted: 2022-08-07T23:17:06.000Z
- Last activity: 2023-05-31T08:45:01.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Guys,

We are working on deploying client connectors to some of our 3rd party vendors and one of the challenge I see is I’m unable hide or bypass authentication for ZIA since I only need private access for them.

Has anyone comeacross this issue and manage to resolve it?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmk4CAA/what-is-global-public-service-edges-and-how-to-monitor-them","lastmod":"2023-05-31T08:45:04.000Z","id":"0D54u00009evmk4CAA"} -->
## What is Global Public Service Edges and how to monitor them?

- Source: https://community.zscaler.com/s/question/0D54u00009evmk4CAA/what-is-global-public-service-edges-and-how-to-monitor-them
- Type: Q&A
- Posted: 2022-08-02T20:20:26.000Z
- Last activity: 2023-05-31T08:45:04.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I am referring to this article: [About Global Public Service Edges | Zscaler](https://help.zscaler.com/zia/about-global-zscaler-enforcement-nodes#:~:text=You%20can%20use%20the%20following,185.46).

My questions are:

1. what is the global public ip(185.46.212.88) used for? I understand that it’s used in “no default? network environments but the documentation says that the public ip doesn’t listen to traffic. So as the traffic gets routed into the zscaler environment, I assume the destination ip is 185.46.212.88. What happens to the packet as it goes towards the 185.x.x.x network within the zscaler environment?
2. how do you monitor these ip addresses? I cannot ping them from my laptop nor can I traceroute to it. Is there a public status page that shows whether these ip addresses are routable?
3. When the user is in his corporate network, why can’t he still navigate to the ZEN cloud enforcement ranges (165.x.x.x for example) and disregard the 185.x.x.x network altogether?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmk8CAA/aws-access-issues-via-zpa-windows","lastmod":"2023-07-06T11:43:52.000Z","id":"0D54u00009evmk8CAA"} -->
## AWS Access Issues via ZPA (Windows)

- Source: https://community.zscaler.com/s/question/0D54u00009evmk8CAA/aws-access-issues-via-zpa-windows
- Type: Q&A
- Posted: 2022-08-01T14:18:58.000Z
- Last activity: 2023-07-06T11:43:52.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

This is our ZPA configuration

1. We have installed ZPA Connector on AWS
2. ACL the ZPA Connector IP on your AWS account
3. Due to AWS ACL setting, access is not possible unless through ZPA

No problem on Mac OS
 It only happens on Windows
 (Mac OS and Windows use the same Forwarding Profile)
 (Access Policy is the same)

It doesn’t seem to go through the ZPA Connector when accessing AWS.
 No access to AWS and no logs in ZPA Live Log

Has anyone seen this problem?

[[aws_dashboard] aws_dashboard1146×824 142 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O6jwAAC)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmlGCAQ/script-error-while-reauthenticate-in-zscaler-private-access-on-windows-machines","lastmod":"2023-05-31T08:45:28.000Z","id":"0D54u00009evmlGCAQ"} -->
## Script Error while reauthenticate in Zscaler Private Access on Windows Machines

- Source: https://community.zscaler.com/s/question/0D54u00009evmlGCAQ/script-error-while-reauthenticate-in-zscaler-private-access-on-windows-machines
- Type: Q&A
- Posted: 2022-06-16T12:51:45.000Z
- Last activity: 2023-05-31T08:45:28.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello All,

We have recently deployed Zscaler private access in our environment. We have integrated Microsoft ADFS and integrated MFA as well. We have kept authentication timeout policy.
 When authentication expires and tries to reauthenticate it throws script error everytime. This is very annoying.

Does anyone have any information on how to fix this issue. Error Snapshot attached herewith.

[MicrosoftTeams-image (1)]
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmlpCAA/netscaler-vpn-coexisting-with-client-agent-for-zpa-only","lastmod":"2023-07-06T12:58:25.000Z","id":"0D54u00009evmlpCAA"} -->
## Netscaler VPN co-existing with Client Agent for ZPA ONLY

- Source: https://community.zscaler.com/s/question/0D54u00009evmlpCAA/netscaler-vpn-coexisting-with-client-agent-for-zpa-only
- Type: Q&A
- Posted: 2022-06-02T20:00:58.000Z
- Last activity: 2023-07-06T12:58:25.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I have a customer testing ZPA in a POV and they also use Netscaler which when launched grabs all traffic (default route) and sends it back through the VPN tunnel. Anyone have experience on how to configure the Zscaler client agent for ZPA only to work with Netscaler full VPN config? Can I just exclude 100.64.0.0/16 as well as all Zscaler ZPA data center IP’s from Netscaler’s config? Is that the right way to configure this? Any info would be greatly appreciated.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmluCAA/zpa-authentication-timeout","lastmod":"2023-07-06T12:58:36.000Z","id":"0D54u00009evmluCAA"} -->
## ZPA Authentication Timeout

- Source: https://community.zscaler.com/s/question/0D54u00009evmluCAA/zpa-authentication-timeout
- Type: Q&A
- Posted: 2022-05-27T15:55:13.000Z
- Last activity: 2023-07-06T12:58:36.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Currently working on a pilot of ZPA and would love to get some feedback from the community.

First, what authentication timeout value are you using? The default is 7 days and there are pros and cons of leaving that or shortening it. Curious what other organizations are doing.

The second thing is more an issue that I’m wondering if anyone has run into. We use Azure AD for SSO and with the SAML App excluded from the MFA policy, re-authentication does not prompt for credentials, as it uses IWA. On macOS, the pilot users are prompted for creds for each re-auth attempt, which is what we want.

Zscaler’s suggestion to force this on Windows was to enforce MFA for the app or disable IWA (laughed at this suggestion!). I worked with the IAM team to add Zscaler to the MFA conditional access policy for VPN, which should trigger an MFA prompt every hour. However, once this was enabled and my ZPA access timed out, I started receiving a TLS error when the client tried to hit [login.microsoftonline.com](http://login.microsoftonline.com).

I validated the traffic going out to the SAML URLs and Microsoft are bypassed from SSL inspection, so I’m not sure what else could be causing this problem. Again, it’s only when the conditional access policy hits the ZPA SAML app. When it’s removed from that, the error goes away post reboot. Any insight/assistance would be appreciated!!

We really need to ensure there is some level of authentication on ZPA, otherwise a compromised laptop will have access with the click of a button and no auth challenge.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmmrCAA/zscaler-ziazpa-and-a-vpn-agent-and-tunnel-with-local-proxy-bypassing-ipzscalercom","lastmod":"2023-05-31T08:13:24.000Z","id":"0D54u00009evmmrCAA"} -->
## Zscaler ZIA/ZPA and a VPN Agent and Tunnel with Local Proxy bypassing ip.zscaler.com

- Source: https://community.zscaler.com/s/question/0D54u00009evmmrCAA/zscaler-ziazpa-and-a-vpn-agent-and-tunnel-with-local-proxy-bypassing-ipzscalercom
- Type: Q&A
- Posted: 2022-04-22T14:27:36.000Z
- Last activity: 2023-05-31T08:13:24.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I decided to share with the community that the Guide [Best Practices for Zscaler Client Connector and VPN Client Interoperability | Zscaler](https://help.zscaler.com/z-app/best-practices-zscaler-app-and-vpn-client-interoperability) is a little old on tunnel 2.0 seems to work good with a VPN agent as it detects it and the ip addresses/fqdn of the VPN gateways can also be excluded from the tunnel.

What is interesting is that when I used an example pac file for a forwarding profile for “Tunnel with local proxy? mode ip.zscaler did not work and it was because in the PAC file there was *.zscaler.com to be send directly so be carefull as I took the example file from here:

help.zscaler.com
##### [Best Practices for Using PAC Files with Zscaler Client Connector | Zscaler](https://help.zscaler.com/z-app/best-practices-using-pac-files-zscaler-app#twlp-forwarding-profile)

Best practices for using PAC files with Zscaler Client Connector.

Just play arround and see which is the best way to use Zscaler and a VPN agent together. Also better as Zscaler to confirm which version of the Zscaler connector was tested with which version of the VPN agent for example Palo Alto Globalprotect etc.

If you are having Windows and Mac devices then test to see which option is best for Windows or MAC as some options may not work on MAC.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmo9CAA/question-on-client-lookup-of-service-edge","lastmod":"2023-05-31T08:46:21.000Z","id":"0D54u00009evmo9CAA"} -->
## Question on client lookup of Service Edge

- Source: https://community.zscaler.com/s/question/0D54u00009evmo9CAA/question-on-client-lookup-of-service-edge
- Type: Q&A
- Posted: 2022-02-04T10:16:25.000Z
- Last activity: 2023-05-31T08:46:21.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi there,

When using ZCC with ZPA the client will ‘[any.broker.prod.zpath.net](http://any.broker.prod.zpath.net)’ to find the local ZPA service edge.
 This routes the ZPA traffic to the the local service edge.

My question is this, when accessing ZIA with ZCC, what is the FQDN that it looks up to select the ZIA service edge ?

For example, if i use a PAC file, I can determine the primary and secondary nodes using the ‘GATEWAY’ variables. But when using the client, with tunnel 2.0 mode operation (no PAC files), how does the client determine what is the primary and secondary service edges to use.

The documents refer to ‘[mobile.zscaler.net](http://mobile.zscaler.net)’ and ‘[login.zscaler.net](http://login.zscaler.net)’ - if I ping test these two FQDN’s I will get completely IPs from the 104.129.192.0/20 range, yet when I look up ‘[ip.zscaler.com](http://ip.zscaler.com)’ on a client enabled PC, it will indicate for example that my service edge is in the ‘165.225.0.0/17’ range.

This is purely for a process documentation purpose - I’m looking to write a procedure to verify if the service is accessible (from a ping response, as an example) or whether traffic to the FQDN is being filtered. I know to check ‘[any.broker.prod.zpath.net](http://any.broker.prod.zpath.net)’ for ZPA, I’d like to have something similar for ZIA.

Thanks in advance
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmosCAA/zpa-authentication-timers","lastmod":"2023-07-06T13:01:54.000Z","id":"0D54u00009evmosCAA"} -->
## ZPA Authentication timers

- Source: https://community.zscaler.com/s/question/0D54u00009evmosCAA/zpa-authentication-timers
- Type: Q&A
- Posted: 2021-12-18T04:06:32.000Z
- Last activity: 2023-07-06T13:01:54.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello community,
 What is the default behavior regard ZPA Authentication? Timeout Policy defaults to 7 days. Does it mean that users will be prompted to authenticate every 7 days no matter what? Even if for example they turn off and back on their computers every day? Or every time users turn off/on computers ZCC has to re-authenticate again?
 Also what is the effect of having *Automatic ZPA reauthentication* switch turned on?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmpFCAQ/zscaler-logstash","lastmod":"2023-05-31T09:27:13.000Z","id":"0D54u00009evmpFCAQ"} -->
## Zscaler - Logstash

- Source: https://community.zscaler.com/s/question/0D54u00009evmpFCAQ/zscaler-logstash
- Type: Q&A
- Posted: 2021-11-23T09:06:42.000Z
- Last activity: 2023-05-31T09:27:13.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello everybody,

We would like to send logs from our ZPA to Logstash using TLS.

For every products we have already did it, we just needed to upload to the Logstash certificate into the configuration panel of the product to validate the trust.

But for Zscaler it seems to be different due to the “***mutual authentification***?.

- Which certificates we should export from Zscaler to install in our Logstash ?
- And which certificates we should put on the Logstash side ?

We have read this documentation but it doesn’t explain very well how to do it : *[https://help.zscaler.com/zpa/about-log-streaming-service](https://help.zscaler.com/zpa/about-log-streaming-service)*

Somebody has already implement this or not ?

Thanks everybody in advance for your help,
 Lucas A.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmpcCAA/zpa-issues-zscaler-and-azure-sync","lastmod":"2023-05-31T08:46:50.000Z","id":"0D54u00009evmpcCAA"} -->
## ZPA issues - Zscaler and Azure sync

- Source: https://community.zscaler.com/s/question/0D54u00009evmpcCAA/zpa-issues-zscaler-and-azure-sync
- Type: Q&A
- Posted: 2021-11-07T21:16:02.000Z
- Last activity: 2023-05-31T08:46:50.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,
 I am trying to understand how sycn between Zscaler and Azure works. The issue I have sometimes is that access to app segments are restricted using SAML and SCIM Attributes

e.g.
 SAML and SCIM Attributes
 IDP: User SSO
 SAML Attributes
 memberOf = ZScaler-Archive

What happens is that after a new user is added to a existing group/role, in the example above Zscaler-Archive it seems to take up to two days for it to take effect so new users can only connect to the application remotely after 2 days.

Any ideas? Is there a way to force a sync?

Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmq4CAA/better-notification-that-zpa-reauthentication-is-required","lastmod":"2023-07-06T13:03:21.000Z","id":"0D54u00009evmq4CAA"} -->
## Better notification that ZPA re-authentication is required

- Source: https://community.zscaler.com/s/question/0D54u00009evmq4CAA/better-notification-that-zpa-reauthentication-is-required
- Type: Q&A
- Posted: 2021-10-14T16:36:03.000Z
- Last activity: 2023-07-06T13:03:21.000Z
- Answers: 3
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is it just me, or does anyone else get a lot of support requests from our employees that they lose access to internal resources? Then we have to remind them that they have to re-authenticate?

I would like to see the option to have a more aggressive message to tell users that their authentication has expired and that they need to login. A message that pops up in the foreground that forces users to acknowledge the fact they will need to re-authenticate.

Changing the frequency of authentication does not seem to help because our users still forget that they have to re-authenticate. So we end up with countless support requests saying they have lost access to the fileservers…only to discover that they need to re-authenticate.

There is a tiny, short lived, system notification bubble that pops up in taskbar, but even I do not see it and then have a mini panic attack when I try to open one of my firewalls and it does not load and I think the network is down…but then I realize…

Anyone know a better way that I can do this?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqTCAQ/deploying-zpa-for-branch-office","lastmod":"2023-07-06T13:03:46.000Z","id":"0D54u00009evmqTCAQ"} -->
## Deploying ZPA for Branch Office

- Source: https://community.zscaler.com/s/question/0D54u00009evmqTCAQ/deploying-zpa-for-branch-office
- Type: Q&A
- Posted: 2021-09-23T10:44:58.000Z
- Last activity: 2023-07-06T13:03:46.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I’m currently planning the deploying Zscaler ZPA for our branch office. But there is a challenge for network devices on which we can’t install ZPA client connectors (Printers, Wireless Access Point, facilities equipment…). How do we segment, classify or continue to grant proper network access to these types of devices after ZPA is fully deployed?
 Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmrlCAA/dallas-service-edge-issues","lastmod":"2025-02-25T16:59:16.000Z","id":"0D54u00009evmrlCAA"} -->
## Dallas Service Edge Issues

- Source: https://community.zscaler.com/s/question/0D54u00009evmrlCAA/dallas-service-edge-issues
- Type: Q&A
- Posted: 2021-05-20T03:15:54.000Z
- Last activity: 2025-02-25T16:59:16.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Anyone having problems with Dallas ZIA and ZPA?
 We are seeing huge latency for both our ZCC and GRE clients.
 Failing over to Atlanta resolves these immediately. Zscaler TAC cannot see any issues with Dallas.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmrqCAA/new-to-zscaler-zscaler-internet-access-zia-and-zscaler-private-access-zpa","lastmod":"2023-05-31T08:47:52.000Z","id":"0D54u00009evmrqCAA"} -->
## New to Zscaler: Zscaler Internet Access (“ZIA?) and Zscaler Private Access (ZPA)

- Source: https://community.zscaler.com/s/question/0D54u00009evmrqCAA/new-to-zscaler-zscaler-internet-access-zia-and-zscaler-private-access-zpa
- Type: Q&A
- Posted: 2021-05-14T03:46:59.000Z
- Last activity: 2023-05-31T08:47:52.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

**Requirement**: Supplier will provide a cloud based internet security gateway service to address the requirement of cloud proxy functionality. Supplier will deliver Zscaler Internet Access (“ZIA?) and Zscaler Private Access (ZPA)

What all the things i need to consider to start? Like pre checks and any documentation that will be helpful.
 Thank you
 Jay
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmrvCAA/how-does-public-service-edge-distinguish-traffic-from-multiple-customers","lastmod":"2023-05-31T08:47:55.000Z","id":"0D54u00009evmrvCAA"} -->
## How does public service edge distinguish traffic from multiple customers?

- Source: https://community.zscaler.com/s/question/0D54u00009evmrvCAA/how-does-public-service-edge-distinguish-traffic-from-multiple-customers
- Type: Q&A
- Posted: 2021-05-07T01:11:38.000Z
- Last activity: 2023-05-31T08:47:55.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Zscaler public service edge is a machine cluster shared among all customers.

How does the edge distinguish the traffic from the customer-A and customer-B?

Because customer-A and B have different policies, it is required to know where the traffic is coming from, I guess.

Best regards
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtECAQ/zscaler-20-pac-files-with-zpa-use-case","lastmod":"2023-05-31T08:13:54.000Z","id":"0D54u00009evmtECAQ"} -->
## Zscaler 2.0 pac files with ZPA use case

- Source: https://community.zscaler.com/s/question/0D54u00009evmtECAQ/zscaler-20-pac-files-with-zpa-use-case
- Type: Q&A
- Posted: 2021-03-05T23:43:57.000Z
- Last activity: 2023-05-31T08:13:54.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I’m building up a pac file for Zscaler 2.0, of course if the tunnel falls back to 1.0 I’m testing the same arguments. shExpMatch(), LocalhostOrDomainIs(), dnsDomainIs().

I have a use case where we divert an external website ([example.com](http://example.com)) over ZPA for whichever reasons.
 If that websites gets placed in a forwarding pac file bypass, then ZPA cant capture the traffic and we get occasional errors.

when I use dnsDomainIs() for both app and forwarding pac files, Ztunnel 2.0 works. When I remove the website from forwarding pac ([example.com](http://example.com)) to get around the layer 3/4 bypass the app profile argument does not bypass traffic.(what is the right one?)

I have tried shExpMatch(), LocalhostorDomainis(), seperating them into seperate lines (no grouped arguments).

The documentation is mixed when it comes to moving to Ztunnel 2.0 as well.

[https://help.zscaler.com/z-app/migrating-z-tunnel-1.0-z-tunnel-2.0](https://help.zscaler.com/z-app/migrating-z-tunnel-1.0-z-tunnel-2.0)

[https://help.zscaler.com/z-app/best-practices-adding-bypasses-z-tunnel-2.0](https://help.zscaler.com/z-app/best-practices-adding-bypasses-z-tunnel-2.0)

What arguments does Ztunnel 2.0 process? What ones does 1.0 process?

Is there anyway I can keep my [example.com](http://example.com) external site out of the forward pac file, if someone turns off ZPA, let the app profile make the traffic go DIRECT?

I’m coming from point of view that whatever I do with Ztunnel 2.0 to get this working, 1.0 should work as well, and I want ZPA to be able to capture any traffic not in forwarding pac file, but if ZPA is turned Off then the app profile pac should go DIRECT;
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtFCAQ/avoiding-zpa-connection-error-when-enabling-scim-sync-with-okta","lastmod":"2023-07-06T11:45:42.000Z","id":"0D54u00009evmtFCAQ"} -->
## Avoiding ZPA "Connection Error" when enabling SCIM sync with Okta

- Source: https://community.zscaler.com/s/question/0D54u00009evmtFCAQ/avoiding-zpa-connection-error-when-enabling-scim-sync-with-okta
- Type: Q&A
- Posted: 2021-03-05T22:53:07.000Z
- Last activity: 2023-07-06T11:45:42.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Greetings!

We’ve had a few reports of ZPA “Connection Error? in the Zscaler Client Connector when enabling SCIM sync with Okta as your IdP. Here’s a post detailing why that happens - and, more importantly, how to avoid it!

How to avoid ZPA "Connection Error" when enabling SCIM sync with Okta
Private Access
> If you’re using Okta as your IdP for ZPA, here’s a quick heads-up on a corner case that may result in users getting “Connection Error? on ZPA in the Zscaler Client Connector when you enable SCIM sync. Problem If you have Okta as your SAML IdP in your ZPA tenant, and you configure and enable SCIM sync, some - but not all! - users may encounter this error in the Zscaler Client Connector:
> [zpa-connection-error]
> Root Cause Okta does not sync users that were assigned to the Zscaler Private Acce…

Regards,
 Lisa
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtGCAQ/zpa-compatibility-with-kvm-hypervisor","lastmod":"2023-05-31T08:48:26.000Z","id":"0D54u00009evmtGCAQ"} -->
## ZPA compatibility with KVM hypervisor

- Source: https://community.zscaler.com/s/question/0D54u00009evmtGCAQ/zpa-compatibility-with-kvm-hypervisor
- Type: Q&A
- Posted: 2021-03-04T18:55:15.000Z
- Last activity: 2023-05-31T08:48:26.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

ZPA supports the KVM hypervisor?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtLCAQ/zpa-on-arm-devices","lastmod":"2023-05-31T08:47:41.000Z","id":"0D54u00009evmtLCAQ"} -->
## ZPA on ARM devices

- Source: https://community.zscaler.com/s/question/0D54u00009evmtLCAQ/zpa-on-arm-devices
- Type: Q&A
- Posted: 2021-03-01T14:50:34.000Z
- Last activity: 2023-05-31T08:47:41.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Zscaler team,

i saw very little information about Client Connector being compiled to work on ARM machines. do you have any plans to make it working or abandon that direction?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtMCAQ/per-app-vpn-on-mobile-dns-question","lastmod":"2024-02-23T02:08:14.000Z","id":"0D54u00009evmtMCAQ"} -->
## Per App VPN on Mobile - DNS Question

- Source: https://community.zscaler.com/s/question/0D54u00009evmtMCAQ/per-app-vpn-on-mobile-dns-question
- Type: Q&A
- Posted: 2021-02-26T20:43:15.000Z
- Last activity: 2024-02-23T02:08:14.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Does per-app VPN ZPA on mobile constantly intercept DNS with ZPA (always on) or does the DNS interception only start occurring when a VPN application is launched?

The VPN profile would always be “on? in this scenario, but the VPN applications would not always been running.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtaCAA/local-routes-when-private-access-is-enabled","lastmod":"2023-07-06T11:45:47.000Z","id":"0D54u00009evmtaCAA"} -->
## Local routes when Private Access is enabled

- Source: https://community.zscaler.com/s/question/0D54u00009evmtaCAA/local-routes-when-private-access-is-enabled
- Type: Q&A
- Posted: 2021-02-12T14:42:36.000Z
- Last activity: 2023-07-06T11:45:47.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Community,

I am an embedded software developer and like many people currently I work from home. My company resently switched from a VPN based solution to ZScaler Private Access. When Private Access is enabled I am having trouble accessing my development network. Here’s my configuration:

- I have my private network with internet access (192.168.1.0/24)
- I have a local development network (172.16.0.0/24)
- I use a tunnel to my company via Zscaler private access.

I can access my private network without any issues, even when private access is enabled. But I can’t access my local development network. I had similar issues with the previous VPN solution, but I was able to solve the issue with a static route. Unfortunately this solution doesn’t work for ZScaler.

My company’s IT support knows even worse than I do

Do you have an idea how to fix the issue? Currently I have to enable and disable ZScaler Private Access multiple times per hour.

Regards,
 Andy
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtuCAA/zscaler-zia-and-zpa-with-netskope-casb-and-dlp-services","lastmod":"2023-05-31T09:25:23.000Z","id":"0D54u00009evmtuCAA"} -->
## Zscaler ZIA and ZPA with NetSkope CASB and DLP services

- Source: https://community.zscaler.com/s/question/0D54u00009evmtuCAA/zscaler-zia-and-zpa-with-netskope-casb-and-dlp-services
- Type: Q&A
- Posted: 2021-01-31T20:35:02.000Z
- Last activity: 2023-05-31T09:25:23.000Z
- Answers: 6
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Everyone

I have a customer considering Zscaler ZIA and ZPA with NetSkope CASB and DLP services. We will implement Zscaler ZIA with the Zscaler Client Connector. I’m wondering if there are likely to be any compatibility issues when running the two solutions side-by-side, and would value anyone’s thoughts on this. Unfortunately I know on a little about NetSkope and the CASB services are being sold to the customer by another provider, so I’m not able to lab it up in time.

Any replies are greatly appreciated.

Many Thanks

Jon
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmuXCAQ/configuring-virtual-service-edge-in-an-esx-nsxt-environment","lastmod":"2023-05-31T09:08:22.000Z","id":"0D54u00009evmuXCAQ"} -->
## Configuring Virtual Service Edge in an ESX NSX-T environment

- Source: https://community.zscaler.com/s/question/0D54u00009evmuXCAQ/configuring-virtual-service-edge-in-an-esx-nsxt-environment
- Type: Q&A
- Posted: 2021-01-08T14:35:43.000Z
- Last activity: 2023-05-31T09:08:22.000Z
- Answers: 6
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

**Note:This has not been validated and qualified by engineering team.**

ZIA Virtual Service Edge Cluster with vmware NSX-T

Virtual Service Edge (VSE) utilizes the CARP protocol, which is the public domain version of the VRRP protocol, for high availability across multiple virtual service edges. In a nutshell, each VSE has a unique management IP, a proxy IP, a load balancer IP, and a shared cluster IP. The CARP protocol is responsible for making the cluster IP representative of the VSE that will proxy traffic at that time.

Changes to ESX must be configured to support CARP, as outlined in our guide for [Configuring Virtual Service Edge Clusters](https://help.zscaler.com/zia/configuring-virtual-service-edge-clusters). The referenced terms that are specific to ESX changes when the NSX-T overlay is deployed.

ESX/VCenter: Port Group := NSX: Segment

ESX/VCenter: Promiscuous mode := Unknown Unicast Flooding

ESX/VCenter: MAC Address Changes := MAC Change

ESX/VCenter: Forged Transmits := MAC Learning

In order to get the Virtual Service Edge CARP protocol working with ESX with NSX-T:

1. [Create a MAC Discovery Profile Segment](https://docs.vmware.com/en/VMware-NSX-T-Data-Center/2.4/administration/GUID-8CC828C0-323F-4050-A3A8-7C87987A4204.html)
2. [Configure MAC Learning](https://docs.vmware.com/en/VMware-Integrated-OpenStack/7.0/com.vmware.openstack.admin.doc/GUID-339CDE06-3345-4C2D-9CDB-5382C8F8E8AE.html)
3. Assign the MAC Discovery Profile to the NSX Segment
4. Provision the Virtual Service Edge machines to that segment
5. Continue with the guide for [Configuring Virtual Service Edge Clusters](https://help.zscaler.com/zia/configuring-virtual-service-edge-clusters) omitting the ESX specific configuration tasks.

**Note:This has not been validated and qualified by engineering team.**
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmunCAA/zpa-on-arm-devices","lastmod":"2023-05-31T08:13:58.000Z","id":"0D54u00009evmunCAA"} -->
## ZPA on ARM devices

- Source: https://community.zscaler.com/s/question/0D54u00009evmunCAA/zpa-on-arm-devices
- Type: Q&A
- Posted: 2020-12-14T14:18:31.000Z
- Last activity: 2023-05-31T08:13:58.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Team
 just wondering if anyone successfully deployed ZConnector in ARM devices (like Surface Pro) .

Seems like compatibility issues with Network Adapter installation (TAP driver). I’ve not seen many threads for that hence asking.
 ZIA seems working fine, but ZPA deffo not

tomek
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwvCAA/zpa-connector-connecting-to-a-choice-of-service-edge","lastmod":"2023-05-31T09:08:19.000Z","id":"0D54u00009evmwvCAA"} -->
## ZPA Connector connecting to a choice of Service Edge

- Source: https://community.zscaler.com/s/question/0D54u00009evmwvCAA/zpa-connector-connecting-to-a-choice-of-service-edge
- Type: Q&A
- Posted: 2020-08-12T03:22:50.000Z
- Last activity: 2023-05-31T09:08:19.000Z
- Answers: 3
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi guys, i was wondering since ZPA connectors automatically looks for the nearest Public Service Edge to connect to is it possible for it to pre-set which Service Node for it to connect to ? E.g. For some reasons its a policy for the companies to use local country Cloud instead of it flowing into other countries.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmx1CAA/do-we-need-sso-mandatory-to-implement-zpa","lastmod":"2023-05-31T08:49:55.000Z","id":"0D54u00009evmx1CAA"} -->
## Do we need SSO mandatory to implement ZPA

- Source: https://community.zscaler.com/s/question/0D54u00009evmx1CAA/do-we-need-sso-mandatory-to-implement-zpa
- Type: Q&A
- Posted: 2020-08-07T14:40:14.000Z
- Last activity: 2023-05-31T08:49:55.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

Wanted to know that is it mandatory to have SSO authentication for ZPA implementation ?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmy7CAA/service-edge-vs-private-zen","lastmod":"2023-05-31T08:50:15.000Z","id":"0D54u00009evmy7CAA"} -->
## Service Edge Vs Private Zen

- Source: https://community.zscaler.com/s/question/0D54u00009evmy7CAA/service-edge-vs-private-zen
- Type: Q&A
- Posted: 2020-06-11T00:55:59.000Z
- Last activity: 2023-05-31T08:50:15.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Can anyone tell me the difference between Service Edge and Private ZEN. The Use case for both seems to be same in Zscaler documents. Both are extension of ZEN that are deployed On-Premise. Only difference noticeable is Service Edge comes with cluster of 3 or 5 ZEN instance, whereas Private ZEN comes with 1 HW with LB.

Regards
 Ganesh Krishnan
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmyjCAA/device-posture-tool-integration-with-zpa","lastmod":"2023-05-31T08:50:27.000Z","id":"0D54u00009evmyjCAA"} -->
## Device Posture Tool Integration With ZPA

- Source: https://community.zscaler.com/s/question/0D54u00009evmyjCAA/device-posture-tool-integration-with-zpa
- Type: Q&A
- Posted: 2020-04-27T23:36:07.000Z
- Last activity: 2023-05-31T08:50:27.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I am working on a project to incorporate a more robust device posture tool to evaluate running processes, patch levels and existing executables on an endpoint and I would like to integrate it into Zscaler ZPA as part of the posture check. Has anyone been able to do this? What tools did you use and did you develop a special integration or did you leverage the existing ZPA device posture checks to look for a flag (e.g. registry key or file) in order to make this work?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmz8CAA/zia-on-only-with-zpa-on","lastmod":"2023-05-31T08:50:56.000Z","id":"0D54u00009evmz8CAA"} -->
## ZIA on only with ZPA on

- Source: https://community.zscaler.com/s/question/0D54u00009evmz8CAA/zia-on-only-with-zpa-on
- Type: Q&A
- Posted: 2020-03-08T16:31:06.000Z
- Last activity: 2023-05-31T08:50:56.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We have the need for one specific scenario in Zapp

Enable ZIA *only* when ZPA is connected

Any ideas if there is any configuration supporting this ?
 thx
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmzyCAA/capability-to-export-activity-logs","lastmod":"2023-05-31T08:51:10.000Z","id":"0D54u00009evmzyCAA"} -->
## Capability to export activity logs

- Source: https://community.zscaler.com/s/question/0D54u00009evmzyCAA/capability-to-export-activity-logs
- Type: Q&A
- Posted: 2020-01-24T17:09:45.000Z
- Last activity: 2023-05-31T08:51:10.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

In ZPA, sometimes the Diagnostic Logs reveal a lot of data, and my focus is on several of them at once. I’d like to export them in bulk, as I can in ZIA, but there doesn’t appear to be a way to do this. In other words, I have to do all my investigation within the ZPA console, which has constraints (column order, column width resets with each new page, etc).
 Can the diagnostic lots be exported into csv format? If not, can this feature be added?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3rCAA/separate-idp-for-zia-and-zpa","lastmod":"2023-07-07T11:32:32.000Z","id":"0D54u00009evn3rCAA"} -->
## Separate IDP for ZIA and ZPA

- Source: https://community.zscaler.com/s/question/0D54u00009evn3rCAA/separate-idp-for-zia-and-zpa
- Type: Q&A
- Posted: 2018-09-27T06:12:09.000Z
- Last activity: 2023-07-07T11:32:32.000Z
- Answers: 4
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Team,
 We have ADFS for ZIA and for ZPA we are planning to configure Azure AD.
 Can I configure in that way to keep ADFS for ZIA and configure Azure AD for ZPA alone.
 Here ZAPP is already deployed for ZIA.

Regards / Ramesh M
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnD0CAI/zpa-mutiple-tenant-support","lastmod":"2025-09-03T12:05:10.000Z","id":"0D54u00009evnD0CAI"} -->
## ZPA mutiple tenant support?

- Source: https://community.zscaler.com/s/question/0D54u00009evnD0CAI/zpa-mutiple-tenant-support
- Type: Q&A
- Posted: 2021-08-20T19:55:08.000Z
- Last activity: 2025-09-03T12:05:10.000Z
- Answers: 10
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

As ZPA is adopted by more of our customers they are also using ZPA. This is making it challenging for our remote work force having to logout of our ZCC and then login to ZCC again to access customer resources and then log back into our ZCC/ZPA.

Also I will add when they login to the customers ZPA they don’t necessarily have ZIA protections etc. So I think there may be a better way to accomplish this.

Are there any plans to support multiple zpa tenants so users don’t have to completely logout?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnD3CAI/azure-private-dns-and-azure-zscaler-connector","lastmod":"2023-07-06T13:04:07.000Z","id":"0D54u00009evnD3CAI"} -->
## Azure private DNS and azure zscaler connector

- Source: https://community.zscaler.com/s/question/0D54u00009evnD3CAI/azure-private-dns-and-azure-zscaler-connector
- Type: Q&A
- Posted: 2021-08-20T10:51:05.000Z
- Last activity: 2023-07-06T13:04:07.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi all Has anyone used Azure private DNS for the azure zscaler connector , is this feasible and what design options are there?

This scenario is to access vms ( rdp and ssh) in a vnet in azure via ZPA with no other connectivity to the subscription to our network.

We would like to use DNS to add the application segment . Can you use IP ranges instead?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnDOCAY/zpa-app-sso-problem","lastmod":"2023-07-06T13:04:35.000Z","id":"0D54u00009evnDOCAY"} -->
## ZPA App SSO Problem

- Source: https://community.zscaler.com/s/question/0D54u00009evnDOCAY/zpa-app-sso-problem
- Type: Q&A
- Posted: 2021-07-27T19:00:39.000Z
- Last activity: 2023-07-06T13:04:35.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I am having some trouble with AzureAD joined machines and SSO on the ZPA Client Connector App.

We have consultants that I have given access with user accounts on our domain @ourdomain.com. Their computers are AzureAD joined machines with their domain @consultant.com. When they log into the Client Connector App, they put in the user account @ourdomain.com and select the correct cloud. Normally this should send them to a Microsoft Sign on page where they can enter their account (@ourdomain.com) and password. However, since their machines are AzureAD Joined the computer is automatically passing their @consultant.com address to Microsoft login. This ends up erroring out since of course that account does not have rights nor is in my Microsoft tenant. How can I prevent the application from automatically sending @consultant.com and making it prompt for username/passsword?

I have found this ([How to prevent browser SSO for AAD joined machines?](https://social.msdn.microsoft.com/Forums/sqlserver/en-US/f08b550b-07f0-460b-8e1c-11d995444ca1/how-to-prevent-browser-sso-for-aad-joined-machines?forum=WindowsAzureAD)) which describes the same situation, but I have not been able to find a solution.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnDzCAI/zpa-authentication-timeout-policy-enforce-use-of-login-credentials","lastmod":"2023-08-15T09:51:41.000Z","id":"0D54u00009evnDzCAI"} -->
## ZPA Authentication Timeout Policy - Enforce Use of Login Credentials

- Source: https://community.zscaler.com/s/question/0D54u00009evnDzCAI/zpa-authentication-timeout-policy-enforce-use-of-login-credentials
- Type: Q&A
- Posted: 2021-07-08T05:05:56.000Z
- Last activity: 2023-08-15T09:51:41.000Z
- Answers: 10
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We have many customers asking why, when the ZPA timeout policy occurs, that users are not prompted to login using their credentials with enforced MFA. I’ve been informed by Zscaler TAC that this is because the ZCC uses a persistent SAML session token stored in the browser cache to re-authenticate the user automatically and is related to Integrated Windows Authentication (IWA).

This has raised some security concerns and we’d like a solution to ensure that users are required to enter their credentials manually when the timeout policy is triggered.

I understand the default lifetime for Microsoft SAML session tokens is 90 days, so we’ve tried using Azure conditional access policies to disable persistent browser sessions and enforce session sign-in frequencies, but this has not resolved the problem. We use Azure AD as the IdP for ZPA.

Has anyone found a working solution for this problem?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnE7CAI/onelogin-scim-with-zpa","lastmod":"2023-07-06T13:04:01.000Z","id":"0D54u00009evnE7CAI"} -->
## OneLogin SCIM with ZPA

- Source: https://community.zscaler.com/s/question/0D54u00009evnE7CAI/onelogin-scim-with-zpa
- Type: Q&A
- Posted: 2021-07-02T21:09:53.000Z
- Last activity: 2023-07-06T13:04:01.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Has anyone setup OneLogin SCIM with the Zscaler Private Access app?
 I am able to setup the SAML configuration but there is no section in the OneLogin Zscaler ZPA application to add the SCIM service provide endpoint and bearer token.
 I have already turned on SCIM sync and SCIM attributes for policy in the idP in ZPA admin portal.
 Does anyone have a suggestions?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnEHCAY/how-to-deauthenticate-a-session-in-zpa","lastmod":"2025-09-03T12:06:07.000Z","id":"0D54u00009evnEHCAY"} -->
## How to deauthenticate a session in ZPA

- Source: https://community.zscaler.com/s/question/0D54u00009evnEHCAY/how-to-deauthenticate-a-session-in-zpa
- Type: Q&A
- Posted: 2020-04-02T09:42:20.000Z
- Last activity: 2025-09-03T12:06:07.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I have set-up a VPN connection using my credentials (two factor through Memority app) but now I want to logout/de-authenticate.
 There is no button in the app to do this, also when I go to [ip.zscaler.com](http://ip.zscaler.com) I can logout there, but this does not de-authenticate my zscaler session, switch off->on, and reboot, same result, still connected using my authentication, so Now I need to wait for the time-out to kick in, this can be 1 week…
 How do I de-authenticate?

[[Zscaler] Zscaler756×557 34.1 KB](https://global.discourse-cdn.com/zscaler/original/2X/f/fe3784cf0529ed40cad1db2eb517805693606af3.jpeg)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnEMCAY/directing-microsoft-office-365-o365-login-traffic","lastmod":"2023-07-07T11:21:26.000Z","id":"0D54u00009evnEMCAY"} -->
## Directing Microsoft Office 365 (O365) Login Traffic

- Source: https://community.zscaler.com/s/question/0D54u00009evnEMCAY/directing-microsoft-office-365-o365-login-traffic
- Type: Q&A
- Posted: 2020-03-31T14:40:20.000Z
- Last activity: 2023-07-07T11:21:26.000Z
- Answers: 12
- Likes: 8
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

DRAFT IN PROGRESS!

**Background:**
 I’ve been working with a current customer to roll out Z-App, and migrate away from a PAC file using a Dedicated Proxy Port (DPP). In order to do this, we started with a clean default PAC file.

Their previous PAC file had many (but not all) of the O365 URLs completely bypassing Zscaler. They did this so that they bypass multi-factor authentication when users logged into O365 from a corporate location. Unfortunately, this also meant that they lost visibility to this traffic and they didn’t benefit from Zscaler’s technical relationship with Microsoft.

The customer was also having some authentication challenges, and the customer has now decided to leave Z-App enabled for on-premises and off-premises users.

**First Use-Case:**
 Since the authentication process is the only time Microsoft applies conditional access policies related to source IP address, you don’t need to bypass Zscaler for all of the traffic. You only need to carve off the login traffic from Zscaler.

To do this, we added the below lines to the default PAC file, and applied it to an Z-App Profile.

> if (localHostOrDomainIs(host, “ANY_IDP_URL??)) ||
 (localHostOrDomainIs(host, “[login.microsoft.com](http://login.microsoft.com)??")) ||
 (localHostOrDomainIs(host, “[login.microsoftonline.com](http://login.microsoftonline.com)??)) ||
 (localHostOrDomainIs(host, “[login.windows.net](http://login.windows.net)??)) ||
 (localHostOrDomainIs(host, “[login.office365.com](http://login.office365.com)??))
 return “DIRECT??;
>

Doing the above will send only this traffic direct.

- If the user is on-premises, the traffic will egress with the customer’s IP address. This IP address is whitelisted within the conditional access, and the user will not be prompted for MFA.
- If the user is off-premises, the traffic will egress with the end user’s IP address… prompting for multi-factor authentication (MFA).

Another use case is if the customer also has ZPA, and they want to lock down their Office365 traffic to their corporate egress IP addresses.

The same concept above applies here. Except this time, we use ZPA to direct just the login traffic to one more more ZPA Connector Group.

To accomplish this, create an App Segment for the login URLs.

[[Screen Shot 2020-03-31 at 11.56.38 AM] Screen Shot 2020-03-31 at 11.56.38 AM2126×462 45.1 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O5PHAA0)

This will force just the login traffic over ZPA, and to reach the appropriate URLs from the customer’s egress IP addresses that have been whitelisted with Microsoft’s conditional access.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnEhCAI/daily-zpa-technical-call","lastmod":"2023-05-31T09:08:08.000Z","id":"0D54u00009evnEhCAI"} -->
## Daily ZPA technical call

- Source: https://community.zscaler.com/s/question/0D54u00009evnEhCAI/daily-zpa-technical-call
- Type: Q&A
- Posted: 2020-03-19T16:48:04.000Z
- Last activity: 2023-05-31T09:08:08.000Z
- Answers: 2
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Learn how Zscaler can help with your business continuity & work from home requirements in a secure and scalable manner on an open call with Zscaler SEs.

**Time:** Every weekday at 10am PST

Join from a PC, Mac, iPad, iPhone or Android device:
 Please click this URL to join. [https://zscaler.zoom.us/s/108184677](https://zscaler.zoom.us/s/108184677)

Or join by phone:

- **US:** +1 312 626 6799 or +1 646 876 9923 or +1 408 638 0968 or +1 669 900 6833 or +1 253 215 8782 or +1 301 715 8592 or +1 346 248 7799
- **United Kingdom:** +44 203 481 5240 or +44 131 460 1196 or +44 203 051 2874 or +44 203 481 5237
- **France:** +33 1 7037 2246 or +33 1 7037 9729 or +33 7 5678 4048
- **Germany:** +49 30 5679 5800 or +49 695 050 2596 or +49 69 7104 9922
- **Australia:** +61 2 8015 6011 or +61 3 7018 2005 or +61 8 7150 1149
- **India:** +91 22 48 798 004
- **Japan:** +81 524 564 439 or +81 3 4578 1488 Webinar ID: 108 184 677 International numbers available: [https://zscaler.zoom.us/u/acJYZl3Ykf](https://zscaler.zoom.us/u/acJYZl3Ykf)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnEiCAI/zscaler-not-showing-private-access-pane","lastmod":"2023-05-31T08:50:51.000Z","id":"0D54u00009evnEiCAI"} -->
## Zscaler not showing Private Access Pane

- Source: https://community.zscaler.com/s/question/0D54u00009evnEiCAI/zscaler-not-showing-private-access-pane
- Type: Q&A
- Posted: 2020-03-19T02:20:18.000Z
- Last activity: 2023-05-31T08:50:51.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

I have a problem, my zscaler app on windows is not showing private access only internet access is showing. ZIA is not using okta, ZPA is using okta to sign in. Does this matter, do advise on how can I solve this.

Thanks.

Regards,
 Matthews Loke
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykLUCAY/china-zpa-connecting-issues","lastmod":"2023-06-06T11:54:53.000Z","id":"0D54u00009eykLUCAY"} -->
## China ZPA connecting issues

- Source: https://community.zscaler.com/s/question/0D54u00009eykLUCAY/china-zpa-connecting-issues
- Type: Q&A
- Posted: 2023-05-22T23:33:23.000Z
- Last activity: 2023-06-06T11:54:53.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

has anyone been facing issues when connecting to ZPA public brokers in China recently?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykLkCAI/third-party-users","lastmod":"2025-08-22T07:27:48.000Z","id":"0D54u00009eykLkCAI"} -->
## Third Party Users

- Source: https://community.zscaler.com/s/question/0D54u00009eykLkCAI/third-party-users
- Type: Q&A
- Posted: 2023-05-16T19:05:06.000Z
- Last activity: 2025-08-22T07:27:48.000Z
- Answers: 7
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is anyone doing Browser Access for 3rd party users? We’ve had the Arbitrary Domains feature enabled for our tenant but still getting an error during the authentication/redirect process.

ERR_EMPTY_RESPONSE

We can access BA just fine with corporate authentication domains, just not those that are not defined within ZPA.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykP3CAI/connection-is-disconnected-twice-or-few-times-a-day-around-in-12hours","lastmod":"2023-09-12T00:46:32.000Z","id":"0D54u00009eykP3CAI"} -->
## Connection is disconnected twice or few times a day (around in 12hours)

- Source: https://community.zscaler.com/s/question/0D54u00009eykP3CAI/connection-is-disconnected-twice-or-few-times-a-day-around-in-12hours
- Type: Q&A
- Posted: 2023-04-18T05:16:43.000Z
- Last activity: 2023-09-12T00:46:32.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,
 I experience that Zscaler ZPA connection is discinnected-reconnected automatically twice or few times a day when I use the work PC from WFH. Other PCs without Zscaler never experience disconnection. The network in my apartment is built like hotel network without auth. The information outlet is provided to my room using vlan and the DHCP is managed by the apartment. Both using wifi or wired, this disconnection happens. How can I fix this problem? Windows 11, lenovo. All updates are applied. This probroblem started at the time when I started to use this PC with Zscaler/ZPA. I never experienced without disconnection whole a day.
 Thank you very much.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykg0CAA/websockets-on-zpa-browser-access","lastmod":"2024-12-06T17:49:11.000Z","id":"0D54u00009eykg0CAA"} -->
## Websockets on ZPA browser access

- Source: https://community.zscaler.com/s/question/0D54u00009eykg0CAA/websockets-on-zpa-browser-access
- Type: Q&A
- Posted: 2023-05-11T17:28:42.000Z
- Last activity: 2024-12-06T17:49:11.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Does ZPA Browser access have Websocket support. We are trying to publish an application with browser access
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eyl4sCAA/what-does-a-private-service-edge-do-with-my-web-traffic","lastmod":"2023-06-02T17:16:02.000Z","id":"0D54u00009eyl4sCAA"} -->
## What does a private service edge do with my web traffic?

- Source: https://community.zscaler.com/s/question/0D54u00009eyl4sCAA/what-does-a-private-service-edge-do-with-my-web-traffic
- Type: Q&A
- Posted: 2023-04-28T14:56:31.000Z
- Last activity: 2023-06-02T17:16:02.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I have virtual private service edge (a.k.a. VZEN) on prem. I also have a PAC file that will specify what URLs must be forwarded to it.

1. Does that traffic get forwarded on to the Zscaler cloud, or does it just exit the same private service edge and go out to the internet?
2. Is there a way for the customer to control what the private service edge does with the traffic? Thanks.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eyl61CAA/how-does-zpa-authentication-work-with-aad-sso-and-iwa","lastmod":"2025-05-27T19:50:54.000Z","id":"0D54u00009eyl61CAA"} -->
## How does ZPA authentication work with AAD SSO and IWA

- Source: https://community.zscaler.com/s/question/0D54u00009eyl61CAA/how-does-zpa-authentication-work-with-aad-sso-and-iwa
- Type: Q&A
- Posted: 2023-05-02T23:31:09.000Z
- Last activity: 2025-05-27T19:50:54.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi, we have ZPA authentication integrated with AAD and on an AAD joined device, users are enjoying seamless authentication experience. Everything works fine, but can anyone explain in detail (step by step) how things are working in the back ground to make this happened?

To my understanding, when user logged into AAD joined device. A Primary Refresh Token (PRT) is issued to the user, which can be used to request further app access token and refresh token. Each type of token has its own expiry date. So, how exactly does ZCC interact and take advantage of IWA, PRT, SAML to provide the SSO experience?

Also, how does ZCC keep track of authentication timeout? I have heard that a session cookie is kept in ZCC’s memory which will be compared to the Timeout Policy for re-authentication, and the cookie will be clear when the user logs out of ZCC, is that true? Obviously, on an AAD joined device, SSO will handle all the authentication and re-authentication transparently (with auto re-authentication configured for ZCC). All relevant tokens (and the cookie) will keep on refreshing as long as the user continue to use the device.

How about on non-AAD joined devices? Users are given the Keep Me Sign In (KMSI) option during the manual sign in. Again, how do things work in the background? And how does KMSI impact the timeout policy?

Thanks.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009fth26CAA/zpa-dns-ttl-not-honoured","lastmod":"2023-07-12T12:33:45.000Z","id":"0D54u00009fth26CAA"} -->
## ZPA DNS TTL not honoured

- Source: https://community.zscaler.com/s/question/0D54u00009fth26CAA/zpa-dns-ttl-not-honoured
- Type: Q&A
- Posted: 2023-06-06T15:57:24.000Z
- Last activity: 2023-07-12T12:33:45.000Z
- Answers: 1
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We've found an issue with ZPA as it does not honour the original DNS TTL's. ZPA changes all original DNS TTL's to 180 seconds across the board. This is causing us delays with DNS invoked fail overs where the original TTL has been set shorter. Wondering if anyone else has found this issue and if you found any resolution. We're working with support and TAM to see if we can get a fix in place.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009fvoTbCAI/zpa-basic-query","lastmod":"2023-06-08T15:11:47.000Z","id":"0D54u00009fvoTbCAI"} -->
## ZPA Basic Query

- Source: https://community.zscaler.com/s/question/0D54u00009fvoTbCAI/zpa-basic-query
- Type: Q&A
- Posted: 2023-06-08T14:20:12.000Z
- Last activity: 2023-06-08T15:11:47.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Where do i post technical queries relating to your product please?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009gh0p9CAA/zpa-for-vpn-network-diagram","lastmod":"2023-06-09T12:40:26.000Z","id":"0D54u00009gh0p9CAA"} -->
## ZPA for VPN Network Diagram

- Source: https://community.zscaler.com/s/question/0D54u00009gh0p9CAA/zpa-for-vpn-network-diagram
- Type: Q&A
- Posted: 2023-06-09T12:18:07.000Z
- Last activity: 2023-06-09T12:40:26.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Is there a network diagram that details the traffic flow from a user PC to an internal application when ZPA is used as the VPN solution? </p><p><br></p><p>Thank for any help on this.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009giWoMCAU/zpa-terraform-private-service-edge-setup-errors","lastmod":"2023-08-23T01:26:50.000Z","id":"0D54u00009giWoMCAU"} -->
## ZPA Terraform - Private Service Edge setup errors

- Source: https://community.zscaler.com/s/question/0D54u00009giWoMCAU/zpa-terraform-private-service-edge-setup-errors
- Type: Q&A
- Posted: 2023-06-12T12:57:24.000Z
- Last activity: 2023-08-23T01:26:50.000Z
- Answers: 2
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi </p><p>When configuring a Private Service Edge via the Terraform module we are encountering a couple of problems.</p><p><br></p><p>Firstly - assigning a trusted network to a Service Edge Group fails as per error below:</p><p><br></p><p>Error: Null value found in list</p><p>with module.pse.module.zpa_service_edge_group[0].zpa_service_edge_group.service_edge_group,</p><p>on ../../modules/terraform-zpa-service-edge-group/main.tf line 5, in resource "zpa_service_edge_group" "service_edge_group":</p><p> 5: resource "zpa_service_edge_group" "service_edge_group" {</p><p>Null values are not allowed for this attribute value.</p><p><br></p><p>This appears to work on initial creation but subsequently running a 'plan' results in the error above.</p><p><br></p><p><br></p><p>The second issue we have is that in order to connect a remote user running ZCC to a PSE we must set the Publish IP to match the Public IP. Since we are building in AWS, this is not known until the component is built and the only way around this we can see is to add a for loop in the AWS user code, waiting for the public IP and then assigning the Publish IP via the API. Is this possible to assign automatically via the Terraform code?</p><p><br></p><p>Thanks</p><p><br></p><p>Jamie</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009gil9GCAQ/zpa-sipa","lastmod":"2023-06-12T16:17:43.000Z","id":"0D54u00009gil9GCAQ"} -->
## ZPA SIPA

- Source: https://community.zscaler.com/s/question/0D54u00009gil9GCAQ/zpa-sipa
- Type: Q&A
- Posted: 2023-06-12T16:17:43.000Z
- Last activity: 2023-06-12T16:17:43.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Does ZScaler have any customer training for SIPA feature?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009hk847CAA/zscaler-devops-and-automation-playlist-new-links","lastmod":"2024-09-18T10:35:41.000Z","id":"0D54u00009hk847CAA"} -->
## Zscaler DevOps and Automation Playlist (New Links)

- Source: https://community.zscaler.com/s/question/0D54u00009hk847CAA/zscaler-devops-and-automation-playlist-new-links
- Type: Q&A
- Posted: 2023-06-16T05:03:31.000Z
- Last activity: 2024-09-18T10:35:41.000Z
- Answers: 0
- Likes: 4
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><span style="font-size: 14px;">Hi All,</span></p><p><span style="font-size: 14px;">For those who were wondering where to find all my previous videos related to Zscaler's Terraform providers and other automation topics. Here is the playlist with all new links for your reference.</span></p><p><span style="font-size: 14px;">Let me know if you have any questions.</span></p><p><br></p><p><strong style="font-size: 18px;">Zscaler Private Access - Terraform Provider Series (Demo Videos)</strong></p><ul><li><a style="font-size: 14px;" rel="noopener noreferrer" href="https://community.zscaler.com/zenith/s/question/0D54u00009evlEpCAI/video-zpa-terraform-provider-video-series-ep1">[VIDEO] ZPA Terraform provider Video Series Ep1</a></li><li><a style="font-size: 14px;" rel="noopener noreferrer" href="https://community.zscaler.com/zenith/s/question/0D54u00009evlEoCAI/video-zpa-terraform-provider-video-series-ep2-connector-groups">[VIDEO] ZPA Terraform provider Video Series Ep2 - Connector Groups</a></li><li><a style="font-size: 14px;" rel="noopener noreferrer" href="https://community.zscaler.com/zenith/s/question/0D54u00009evlEnCAI/video-zpa-terraform-provider-video-series-ep3-provisioning-keys">[VIDEO] ZPA Terraform provider Video Series Ep3 - Provisioning Keys</a></li><li><a style="font-size: 14px;" rel="noopener noreferrer" href="https://community.zscaler.com/zenith/s/question/0D54u00009evlEmCAI/video-zpa-terraform-provider-video-series-ep4-server-groups">[VIDEO] ZPA Terraform provider Video Series Ep4 - Server Groups</a></li><li><a style="font-size: 14px;" rel="noopener noreferrer" href="https://community.zscaler.com/zenith/s/question/0D54u00009evlEgCAI/video-terraform-provider-video-series-ep5-zpa-application-server">[VIDEO] Terraform provider Video Series Ep.5 - ZPA Application Server</a></li><li><a style="font-size: 14px;" rel="noopener noreferrer" href="https://community.zscaler.com/zenith/s/question/0D54u00009evlEfCAI/video-zpa-terraform-provider-video-series-ep6-zpa-segment-group">[VIDEO] ZPA Terraform provider Video Series Ep.6 - ZPA Segment Group</a></li><li><a style="font-size: 14px;" rel="noopener noreferrer" href="https://community.zscaler.com/zenith/s/question/0D54u00009evlEXCAY/video-zpa-terraform-provider-video-series-ep7-zpa-application-segment">[VIDEO] - ZPA Terraform Provider Video Series Ep.7 - ZPA Application Segment</a></li><li><a style="font-size: 14px;" rel="noopener noreferrer" href="https://community.zscaler.com/zenith/s/question/0D54u00009evlEGCAY/zpa-terraform-provider-video-series-ep8-zpa-browser-access-application-segment">ZPA Terraform Provider Video Series Ep.8 - ZPA Browser Access Application Segment</a></li></ul><p><span style="font-size: 18px;">Zscaler Terraform Modules</span></p><ul><li><a style="font-size: 14px;" rel="noopener noreferrer" href="https://community.zscaler.com/zenith/s/question/0D54u00009evlEWCAY/video-zpaaws-automated-terraform-deployment">[VIDEO] - ZPA-AWS Automated Terraform Deployment</a></li><li><a style="font-size: 14px;" rel="noopener noreferrer" href="https://community.zscaler.com/zenith/s/question/0D54u00009evlDECAY/zscaler-private-service-edge-aws-terraform-module-now-available">Zscaler Private Service Edge AWS Terraform Module&nbsp;- Now Available</a></li></ul><p><strong style="font-size: 18px;">ZPA Terraform Provider Advanced Topics</strong></p><ul><li><a style="font-size: 14px;" rel="noopener noreferrer" href="https://community.zscaler.com/zenith/s/question/0D54u00009evlDrCAI/video-zscaler-terraformer-migration-tool-launch">[VIDEO] Zscaler Terraformer Migration Tool - Launch</a></li><li><a style="font-size: 14px;" rel="noopener noreferrer" href="https://community.zscaler.com/zenith/s/question/0D54u00009evlNoCAI/how-to-backup-your-zpa-configuration-via-zpa-terraform-provider-to-aws-s3-backend-part-1">[VIDEO] How to Backup Your ZPA Configuration via ZPA Terraform Provider to AWS S3 Backend (Part 1)</a></li><li><a style="font-size: 14px;" rel="noopener noreferrer" href="https://community.zscaler.com/zenith/s/question/0D54u00009evlNnCAI/how-to-backup-your-zpa-configuration-via-zpa-terraform-provider-to-aws-s3-backend-part-2">[VIDEO] How to Backup Your ZPA Configuration via ZPA Terraform Provider to AWS S3 Backend (Part 2)</a></li></ul><p><span style="font-size: 18px;">Data Protection Topics</span></p><ul><li><a style="font-size: 14px;" rel="noopener noreferrer" href="https://community.zscaler.com/zenith/s/question/0D54u00009evmf5CAA/video-zscaler-apicasb-dlp-and-servicenow">[VIDEO] Zscaler API/CASB DLP and ServiceNow</a></li><li><a style="font-size: 14px;" rel="noopener noreferrer" href="https://community.zscaler.com/zenith/s/question/0D54u00009evmf6CAA/video-zia-identity-proxy-and-servicenow">[VIDEO] ZIA Identity Proxy and ServiceNow</a></li></ul><p><span style="font-size: 18px;">Zscaler Posture Control Topics</span></p><ul><li><a style="font-size: 14px;" rel="noopener noreferrer" href="https://community.zscaler.com/zenith/s/question/0D54u00009evlUqCAI/zscaler-posture-control-and-jira-itsm-integration">[VIDEO] Zscaler Posture Control and Jira ITSM Integration</a></li><li><a style="font-size: 14px;" rel="noopener noreferrer" href="https://community.zscaler.com/zenith/s/question/0D54u00009evlV3CAI/zscaler-posture-control-and-service-now-integration">[VIDEO] Zscaler Posture Control and Service Now Integration</a></li></ul><p><span style="font-size: 18px;">Miscellaneous Topics</span></p><ul><li><a style="font-size: 14px;" rel="noopener noreferrer" href="https://community.zscaler.com/zenith/s/question/0D54u00009eylLECAY/video-zscaler-nss-deployment-with-azurerm-template">[Video] Zscaler NSS Deployment with Azurerm Template</a></li></ul>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009hkC2PCAU/about-reducing-zia-and-zpa-login-with-azure-ad","lastmod":"2023-07-10T07:56:47.000Z","id":"0D54u00009hkC2PCAU"} -->
## About reducing ZIA and ZPA login with Azure AD

- Source: https://community.zscaler.com/s/question/0D54u00009hkC2PCAU/about-reducing-zia-and-zpa-login-with-azure-ad
- Type: Q&A
- Posted: 2023-06-16T07:38:16.000Z
- Last activity: 2023-07-10T07:56:47.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Zscaler team,

Any idea on how I can reduce the Azure AD sign in with Client Connector when I have both ZIA and ZPA account?

On Windows seems to need logging in twice. (Found solution turn off this will work [Configuring Automatic Username Population for IdP Authentication | Zscaler](https://help.zscaler.com/z-app/configuring-automatic-username-population-idp-authentication))

Tommy Ho
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009hkWBGCA2/zpa-bypass-behavior-can-i-bypass-port-443-in-zpa-while-keeping-port-3389-of-same-fqdn","lastmod":"2023-10-01T23:45:55.000Z","id":"0D54u00009hkWBGCA2"} -->
## ZPA Bypass Behavior: Can I bypass port 443 in ZPA while keeping port 3389 of same FQDN

- Source: https://community.zscaler.com/s/question/0D54u00009hkWBGCA2/zpa-bypass-behavior-can-i-bypass-port-443-in-zpa-while-keeping-port-3389-of-same-fqdn
- Type: Q&A
- Posted: 2023-06-16T14:31:28.000Z
- Last activity: 2023-10-01T23:45:55.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p><br></p><p>I have requirement of bypassing 443 port of abc.com in zpa. Also I need to keep port 3389 of abc.com in ZPA itself. Whether is it possible?</p><p>I have configured abc.com bypass for port 443 and allowed 3389 for the same FQDN. But i can not connect 3389. 443 is working fine via ZIA since bypassed. when i used IP for 3389 it worked. Am i missing something. is it possible to achieve my goal?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009kWrg0CAC/how-to-check-in-zpa-subscriptions","lastmod":"2023-12-21T11:25:12.000Z","id":"0D54u00009kWrg0CAC"} -->
## How to check in ZPA Subscriptions

- Source: https://community.zscaler.com/s/question/0D54u00009kWrg0CAC/how-to-check-in-zpa-subscriptions
- Type: Q&A
- Posted: 2023-07-04T06:20:08.000Z
- Last activity: 2023-12-21T11:25:12.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p><br></p><p>Can someone tell us if we have ZPA services, why we are not seeing Subscriptions in the ZPA.</p><p><br></p><p>Can some one pls help me.</p><p><br></p><p>Regards,</p><p>Ricky Kocharekar</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009lJ5qWCAS/dynamic-latencybased-service-edge-assignment-zia","lastmod":"2024-10-01T19:21:39.000Z","id":"0D54u00009lJ5qWCAS"} -->
## Dynamic Latency-Based Service Edge Assignment (ZIA)

- Source: https://community.zscaler.com/s/question/0D54u00009lJ5qWCAS/dynamic-latencybased-service-edge-assignment-zia
- Type: Q&A
- Posted: 2023-07-06T14:31:53.000Z
- Last activity: 2024-10-01T19:21:39.000Z
- Answers: 8
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><span style="font-size: 12pt; font-family: &quot;Times New Roman&quot;, serif;">In this article, we will help you to test the latency-based service edge assignment (ZIA) feature. Before we start with the steps it is important to understand the problem that this feature is resolving. Improving the end users experience is a crucial goal that Zscaler has been committing to providing. After many companies started adapting work from anywhere and hybrid work environments (i.e., home and office), and because users have different network environments at home, it became essential to implement an intelligence method into the Client Connector to allow choosing the best service edge with the lowest latency. This enhancement can substantially improve the end user experience and reduce the administrator’s workload by minimizing the number of support tickets.&nbsp;</span></p><p><span style="font-size: 12pt; font-family: &quot;Times New Roman&quot;, serif;">Client Connector connects users to the service edge that is configured in the PAC file. Administrators can manually add the ZIA public service edge to the PAC file, or use $ {GATEWAY} and $ {SECONDARY_GATEWAY} Zscaler specific variables to connect users based on geo-proximity.&nbsp;Prior to Zscaler Client Connector (ZCC) 4.2, ZCC would failover to the secondary service edge, if and only if the primary service edge becomes unreachable. In other words, if the tunnel to the primary is up and the user experiences a latency issue with that DC, the ZCC won’t failover to the secondary DC that can offer a better performance. To overcome this limitation, a new feature was added into Client Connector 4.2 where a constant HTTP-based probing to the primary and the secondary service edges is conducted. ZCC utilizes Time to first byte (TTFB) to compare the latency between both service edges and then based on the following parameters: Probe Interval Threshold, and Probe Sample Size the failover may occur. Zscaler supports this feature with all traffic forwarding methods; Tunnel with Local proxy, Tunnel 1.0, and Tunnel 2.0. In tunnel 1.0, Client Connector utilizes HTTP CONNECT to the public service edge for 407 (Proxy Authentication) response to calculate the latency. In Tunnel 2.0, Client Connector utilizes HTTP GET to http://gateway.[cloud].net /generate_204 response to calculate the latency (As shown in the below pic)</span></p><p style="text-align: center;"><br></p><p style="text-align: center;"><img src="/sfc/servlet.shepherd/version/download/0684u00000b6Oyk?asPdf=false&amp;operationContext=CHATTER"></p><p style="text-align: center;"><br></p><p><span style="font-size: 12pt; font-family: &quot;Times New Roman&quot;, serif;">To test the feature:</span></p><ol><li><span style="font-size: 12pt; font-family: &quot;Times New Roman&quot;, serif;">To start testing, you need a tool that can be used to simulate high latency and packet loss. In this article we will use Clumsy tool to add extra overhead.&nbsp;</span></li><li><span style="font-size: 12pt; font-family: &quot;Times New Roman&quot;, serif;">After downloading Clumsy, run the .exe file&nbsp;</span><img src="/sfc/servlet.shepherd/version/download/0684u00000b6P1j?asPdf=false&amp;operationContext=CHATTER"></li><li><span style="font-family: &quot;Times New Roman&quot;, serif; font-size: 12pt;">Enable Dynamic ZIA service edge assignment under forwarding profile and decrease the values to simulate a faster failover.&nbsp;</span><img src="/sfc/servlet.shepherd/version/download/0684u00000b6P1y?asPdf=false&amp;operationContext=CHATTER"></li><li><span style="font-family: &quot;Times New Roman&quot;, serif; font-size: 12pt;">Enable and set a high values for: Lag, Drop and Throttle checkboxes. For ip.DstAddr, add the primary&nbsp;DC VIP (You can get this value from ZCC GUI)&nbsp;</span><img src="/sfc/servlet.shepherd/version/download/0684u00000b6P28?asPdf=false&amp;operationContext=CHATTER"><img src="/sfc/servlet.shepherd/version/download/0684u00000b6P2c?asPdf=false&amp;operationContext=CHATTER"></li><li><span style="font-family: &quot;Times New Roman&quot;, serif; font-size: 12pt;">Once the criteria are met, the failover will occur. As you can seen in the below screenshot, the Server IP was changed from 104.129.194.38 to 104.129.192.254.</span><img src="/sfc/servlet.shepherd/version/download/0684u00000b6P2m?asPdf=false&amp;operationContext=CHATTER"></li></ol><p style="text-align: center;"><br></p><p><span style="font-size: 12pt; font-family: &quot;Times New Roman&quot;, serif;">End user will be notified.&nbsp;</span><img src="/sfc/servlet.shepherd/version/download/0684u00000b6P3L?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p><span style="font-size: 12pt; font-family: &quot;Times New Roman&quot;, serif;">To investigate issues related to dynamic service edge latency-based assignment, you can use ZSATunnel.log files to get more insight (keywords: Changing active T2 instance based on latency, LBZ).&nbsp;&nbsp;</span></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000b6P3Q?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p><br></p><p style="text-align: center;"><br></p><p style="text-align: center;"><br></p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009lKgpJCAS/vmware-horizon-vdi-disconnect-issue","lastmod":"2023-07-10T20:02:35.000Z","id":"0D54u00009lKgpJCAS"} -->
## VMWare Horizon VDI Disconnect Issue

- Source: https://community.zscaler.com/s/question/0D54u00009lKgpJCAS/vmware-horizon-vdi-disconnect-issue
- Type: Q&A
- Posted: 2023-07-08T16:46:30.000Z
- Last activity: 2023-07-10T20:02:35.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello all,</p><p>We are using Horizon over ZPA, 2 App connectors and 2 VDI connection servers.</p><p>First, we migrated from VPN to ZPA so default connection was PCoIP and it seems unstable because I see errors regarding to UDP on ZPA console. We have changed connection to BLAST and initial connection seems got fine. However, users are reporting VDI disconnect intermittently. I opened a case with Zscaler and they say connection server is sending RST packet that probably causes connection to be terminated. That never happens on VPN or LAN. Is anyone uses Horizon View with ZPA stably? if so what is the configuration? Currently we are on BLAST Secure gateway enabled on connection server and trying to disable so that the connection will be peer to peer.</p><p>Any advise is appreciated.</p><p><br></p><p>Thank you,</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009mSquZCAS/how-to-enable-zpa-service-which-is-was-manually-turned-off","lastmod":"2023-07-28T11:27:04.000Z","id":"0D54u00009mSquZCAS"} -->
## How to enable ZPA service which is / was manually turned off

- Source: https://community.zscaler.com/s/question/0D54u00009mSquZCAS/how-to-enable-zpa-service-which-is-was-manually-turned-off
- Type: Q&A
- Posted: 2023-07-12T12:51:02.000Z
- Last activity: 2023-07-28T11:27:04.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi All,</p><p>During deployment of ZPA for pilot users we decided to allow them to turn off ZPA manually on their own ( simply no password was set on). At this moment we are looking if there is any option to remotely enable this service i.e. through registry , ZPA portal, reinstalling ZCC , ...... but so far there is no luck to find the way to enable it without contacting users directly and asking them to turn on the service (We already implement password for disabling ZPA so anyone who enable will not be able to disable on their own anymore). Has anyone having same issue / problem? Have anyone found any solution for that case?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009nNtCmCAK/zpa-partner-login","lastmod":"2023-07-23T22:00:49.000Z","id":"0D54u00009nNtCmCAK"} -->
## ZPA partner login

- Source: https://community.zscaler.com/s/question/0D54u00009nNtCmCAK/zpa-partner-login
- Type: Q&A
- Posted: 2023-07-20T22:25:56.000Z
- Last activity: 2023-07-23T22:00:49.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>My organization is not a ZPA customer, however one of our client is, and they are asking us to enable ZPA partner login in our tenant, the expectation is that doing so will enable our ZCC users to be able to access the 3rd party origanizations apps published via ZPA.</p><p>I am getting conflicting information on whether this will work or not from Zscaler, could someone pls let me know what exactly enabling ZPA partner login does, the documentation does not have a lot of info on this, and whether this would provide the access to another Zscaler customer apps that does have ZPA</p><p><br></p><p>Thanks</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009oUxpHCAS/configure-zpa-lss-log-receiver-for-audit-logs-to-sentinel","lastmod":"2023-07-31T09:48:07.000Z","id":"0D54u00009oUxpHCAS"} -->
## Configure ZPA LSS log receiver for 'Audit logs' to Sentinel

- Source: https://community.zscaler.com/s/question/0D54u00009oUxpHCAS/configure-zpa-lss-log-receiver-for-audit-logs-to-sentinel
- Type: Q&A
- Posted: 2023-07-24T15:35:46.000Z
- Last activity: 2023-07-31T09:48:07.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Community,</p><p>We've configured our ZPA Log Receivers for all categories from user activity, user status , App Conn status, etc for Sentinel as our SIEM. That all works fine except 'Audit logs'.</p><p><br></p><p>Can anyone kindly share a CEF sample to how you've configured your Log Receiver for Audit logs ?</p><p><br></p><p>We started with:</p><p><166>%s{LogTimestamp:mon} %02d{LogTimestamp:dd} %04d{LogTimestamp:yyy} %02d{LogTimestamp:hh}:%02d{LogTimestamp:mm}:%02d{LogTimestamp:ss} Audit-zpa-lss CEF:0|Zscaler|ZPA-AuditLogs|1.0|%s{modifiedTime:iso8601}|%s{creationTime:iso8601}|%d{modifiedBy}|%s{requestId}|%s{sessionId}|%s{auditOldValue}|%s{auditNewValue}|%s{auditOperationType}|%s{objectType}|%s{objectName}|%d{objectId}|%d{customerId}|%s{modifiedByUser}|%d{isClientAudit}\n</p><p><br></p><p>But Zscaler Support corrected me that LogTimestamp is not supported for Auditlogs.</p><p><br></p><p>We dont have rights to the SIEM so I cant do any form of format validation also this is very new to me.</p><p><br></p><p>Thank you</p><p>Regards</p><p>G</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009qaMpHCAU/two-application-one-internal-external-sapintraabccom-and-sapextabccom-in-zpa","lastmod":"2023-08-03T11:34:53.000Z","id":"0D54u00009qaMpHCAU"} -->
## Two Application one Internal -> External sap.intra.abc.com and sap.ext.abc.com in ZPA

- Source: https://community.zscaler.com/s/question/0D54u00009qaMpHCAU/two-application-one-internal-external-sapintraabccom-and-sapextabccom-in-zpa
- Type: Q&A
- Posted: 2023-08-03T05:52:00.000Z
- Last activity: 2023-08-03T11:34:53.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p><br></p><p>Need some help and advise,</p><p><br></p><p>My plan is create first to create;</p><p><br></p><p><span style="font-family: Arial, sans-serif; font-size: 10pt;">Step 1:-&nbsp;We need to configure two&nbsp;</span>sap.intra.abc.com and sap.ext.abc.com in the Application segment.</p><p><br></p><p>the .ext one must have browser access and certificate mapped. Then the CNAME for .ext must be hosted.</p><p><br></p><p>Now there are some challenge i am facing;</p><p><br></p><p>1) In the Application Segment -> which i have create a internal application we have a wildcard domain abc.com. So do i need to create a separate one for this? which I think is a best practice. but think i need to keep in mind.</p><p><br></p><p>2) In that case I’m wondering how the traffic will be destined towards the original server where FQDN “sap.intra.abc.com” is hosted as FQDN “sap.ext.abc.com” is no where hosted on any end server?</p><p><br></p><p>Also, we need to have new CA signed certificate for new fqdn, and modifications at DNS level also needed, right?</p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009rdZmLCAU/citrix-workspace-via-browser-access","lastmod":"2026-05-19T02:07:10.000Z","id":"0D54u00009rdZmLCAU"} -->
## Citrix Workspace via Browser Access

- Source: https://community.zscaler.com/s/question/0D54u00009rdZmLCAU/citrix-workspace-via-browser-access
- Type: Q&A
- Posted: 2023-08-11T20:10:01.000Z
- Last activity: 2026-05-19T02:07:10.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Has anyone successfully deployed Citrix Workspace via Browser Access? We have everything in place, and can access the initial login, but when we get logged into the Citrix Gateway there seems to be an issue with the handoff to Workspace and it wants us to log into Workspace even though that is not the behavior it exhibits when on network or when connecting via ZCC, Gateway seems to basically be a Proxy and SSO type deal and takes you straight into Workspace. Upon trying to authenticate to Workspace when prompted, we get an error stating that "Your logon has expired. Please log on again to continue." And that is an endless loop. </p><p><br></p><p>Just wanted to ask around before we started getting other groups and vendors involved. Thanks for any insight or advice!</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009y4w6QCAQ/export-zpa-user-activity-logs-filtered-results-from-diagnostics","lastmod":"2025-06-24T03:01:05.000Z","id":"0D54u00009y4w6QCAQ"} -->
## Export ZPA User activity logs - Filtered results from Diagnostics

- Source: https://community.zscaler.com/s/question/0D54u00009y4w6QCAQ/export-zpa-user-activity-logs-filtered-results-from-diagnostics
- Type: Q&A
- Posted: 2023-09-15T13:35:51.000Z
- Last activity: 2025-06-24T03:01:05.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello, Do we have any update on this question ?</p><p>https://community.zscaler.com/zenith/s/question/0D54u00009evlMwCAI/export-zpa-filter-logs</p><p><br></p><p>Thank you.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009zAwCXCA0/browser-access","lastmod":"2023-10-09T02:16:40.000Z","id":"0D54u00009zAwCXCA0"} -->
## Browser Access

- Source: https://community.zscaler.com/s/question/0D54u00009zAwCXCA0/browser-access
- Type: Q&A
- Posted: 2023-09-18T07:11:12.000Z
- Last activity: 2023-10-09T02:16:40.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We trying to implement an application that will be accessible over ZPA as well as Browser without ZPA.</p><p>After enabling Browser Access, we have seen that those who are connected to ZPA traffic is also going via Browser Access as a result user is not able to access the application.</p><p><br></p><p>We reached Zscaler but for 2 months there is no resolution yet.</p><p><br></p><p>ZCC version 4.2 and 4.1</p><p> Is anyone having any idea ?</p><p>If ZPA ia enabled application should always go via ZPA only, if not then only go via ZPA browser.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009zdiqiCAA/zpa-terraform-provider-v300beta-september-19-2023","lastmod":"2023-09-19T21:21:25.000Z","id":"0D54u00009zdiqiCAA"} -->
## ZPA Terraform Provider - v3.0.0-beta (September, 19 2023)

- Source: https://community.zscaler.com/s/question/0D54u00009zdiqiCAA/zpa-terraform-provider-v300beta-september-19-2023
- Type: Q&A
- Posted: 2023-09-19T21:21:25.000Z
- Last activity: 2023-09-19T21:21:25.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><strong>ZPA Terraform Provider - v3.0.0-beta (September, 19 2023)</strong></p><p><br></p><p><strong>Enhancements</strong></p><p><a href="https://github.com/zscaler/terraform-provider-zpa/pull/355" rel="noopener noreferrer" target="_blank">PR #355</a> - Introduced the new resource and datasource zpa_microtenant_controller</p><p><a href="https://github.com/zscaler/terraform-provider-zpa/pull/355" rel="noopener noreferrer" target="_blank">PR #355</a> - Added support to the new Microtenant Controller feature to the following resources:</p><ul><li>zpa_app_connector_controller,, zpa_app_connector_group, zpa_application_segment, zpa_application_segment_browser_access, zpa_application_segment_inspection, zpa_application_segment_pra, zpa_application_server, zpa_policy_type, zpa_policy_access_rule, zpa_policy_access_forwarding_rule, zpa_policy_access_timeout_rule, zpa_policy_access_inspection_rule, zpa_policy_access_isolation_rule, zpa_segment_group, zpa_server_group, zpa_provisioning_key, zpa_machine_group, zpa_service_edge_group, zpa_service_edge_controller</li></ul><p><br></p><p><strong>Important: </strong>The new attribute microtenant_id is optional. The provider also supports the microtenant ID configuration via the environment variable <strong>ZPA_MICROTENANT_ID</strong> which is the recommended method.</p><p><strong>Important: </strong>This feature is in limited availability and requires additional license. To learn more, contact your local account team.</p><p><br></p><p><a href="https://github.com/zscaler/terraform-provider-zpa/pull/356" rel="noopener noreferrer" target="_blank">PR #356</a> - Added support to the following new ZPA Cloud Browser Isolation resources and datasources:</p><p>Resources</p><ul><li>zpa_cloud_browser_isolation_banner - Cloud Browser Isolation Banner Controller</li><li>zpa_cloud_browser_isolation_certificate - Cloud Browser Isolation Certificate Controller</li><li>zpa_cloud_browser_isolation_external_profile - Cloud Browser Isolation External Profile Controller</li><li>Data Sources</li><li>zpa_cloud_browser_isolation_banner - Cloud Browser Isolation Banner Controller</li><li>zpa_cloud_browser_isolation_certificate - Cloud Browser Isolation Certificate Controller</li><li>zpa_cloud_browser_isolation_external_profile - Cloud Browser Isolation External Profile Controller</li><li>zpa_cloud_browser_isolation_region - Cloud Browser Isolation Regions</li><li>zpa_cloud_browser_isolation_zpa_profile - Cloud Browser Isolation ZPA Profile</li></ul><p><strong>Important:</strong> Cloud Browser Isolation (CBI) is a licensed feature flag. Please contact your local account team for details.</p><p><br></p><p><a href="https://github.com/zscaler/terraform-provider-zpa/pull/363" rel="noopener noreferrer" target="_blank">PR #363</a> - Added support for COUNTRY_CODE object type within the zpa_policy_access_rule resource. The provider validates the use of proper 2 letter country codes <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2" rel="noopener noreferrer" target="_blank">ISO3166 By Alpha2Code</a> - <a href="https://github.com/zscaler/terraform-provider-zpa/issues/361" rel="noopener noreferrer" target="_blank">Issue #361</a></p><p><a href="https://github.com/zscaler/terraform-provider-zpa/pull/366" rel="noopener noreferrer" target="_blank">PR #366</a> - Added <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2" rel="noopener noreferrer" target="_blank">ISO3166 By Alpha2Code</a> for country_code validation on zpa_app_connector_groups and zpa_service_edge_group resources</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009ze48mCAA/zpa-pse-terraform-azure","lastmod":"2023-11-06T07:25:52.000Z","id":"0D54u00009ze48mCAA"} -->
## ZPA PSE Terraform Azure

- Source: https://community.zscaler.com/s/question/0D54u00009ze48mCAA/zpa-pse-terraform-azure
- Type: Q&A
- Posted: 2023-09-20T11:26:13.000Z
- Last activity: 2023-11-06T07:25:52.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Dear Community, </p><p><br></p><p>I am trying to deploy PSEs to Azure via Terraform, I have compared the Terraform code between AWS and Azure. As a requirement, we should have an inbound rule to the PSEs from the ZCCs to allow the connectivity. I see such an ingress rule in the SG module in the AWS code but for the Azure NSG, I don't see the incoming 443 rule. </p><p>Am I missing something?</p><p>Regards</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009zhJPyCAM/comparing-between-the-zpa-ztna-and-fortigate-ztna","lastmod":"2023-09-25T08:59:15.000Z","id":"0D54u00009zhJPyCAM"} -->
## Comparing Between the ZPA ZTNA and FortiGate ZTNA

- Source: https://community.zscaler.com/s/question/0D54u00009zhJPyCAM/comparing-between-the-zpa-ztna-and-fortigate-ztna
- Type: Q&A
- Posted: 2023-09-24T12:40:28.000Z
- Last activity: 2023-09-25T08:59:15.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Dears,</p><p><br></p><p>What is the difference between the ZPA ZTNA and FortiGate ZTNA?</p><p>What is the strength and weak points for each?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A0eZY5CQM/multiple-profile-selection","lastmod":"2023-10-04T09:34:35.000Z","id":"0D54u0000A0eZY5CQM"} -->
## Multiple profile selection

- Source: https://community.zscaler.com/s/question/0D54u0000A0eZY5CQM/multiple-profile-selection
- Type: Q&A
- Posted: 2023-09-29T14:57:06.000Z
- Last activity: 2023-10-04T09:34:35.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I have users that have two different devices and I need them to be in different profiles depending on the device. At first I thought device posture but that won't do it from what I can tell. Now I'm thinking a single profile that does different things so I tried somehow twisting the Trusted vs Untrusted network criteria but I can't get that to work either because I need that criteria for ZPA. Does anyone have any ideas? Is it doable?</p><p><br></p><p>Laptop type 1</p><p>ZIA Tunnel (on-prem and off-prem)</p><p>ZPA Enabled off-prem/Disabled on-prem</p><p><br></p><p>Laptop type 2</p><p>ZIA Enforce Proxy (on-prem and off-prem)</p><p>ZPA Enabled off-prem/Disabled on-prem</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A2nKHnCQM/app-connector-centos-eol","lastmod":"2025-08-22T07:27:05.000Z","id":"0D54u0000A2nKHnCQM"} -->
## App Connector / CentOS EOL

- Source: https://community.zscaler.com/s/question/0D54u0000A2nKHnCQM/app-connector-centos-eol
- Type: Q&A
- Posted: 2023-10-09T16:40:57.000Z
- Last activity: 2025-08-22T07:27:05.000Z
- Answers: 5
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Does anyone know what the future/migration plans are for ZPA app connectors that are running CentOS? As they are running 7.9.2009 and EOL is approaching quickly in June 2024. Wondering what the path will be to a different OS when customers have a large number of app connectors.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A2onaRCQQ/multi-tenant-integration-with-zpa","lastmod":"2023-10-11T19:10:09.000Z","id":"0D54u0000A2onaRCQQ"} -->
## Multi tenant integration with ZPA

- Source: https://community.zscaler.com/s/question/0D54u0000A2onaRCQQ/multi-tenant-integration-with-zpa
- Type: Q&A
- Posted: 2023-10-11T00:42:04.000Z
- Last activity: 2023-10-11T19:10:09.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Can we have 2 ZIA clouds attached to single ZPA tenant ? If yes, What is the process of adding them to the tenant.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A7uJahCQE/zpa-lss-feed-status","lastmod":"2023-11-09T16:13:28.000Z","id":"0D54u0000A7uJahCQE"} -->
## ZPA - LSS Feed Status

- Source: https://community.zscaler.com/s/question/0D54u0000A7uJahCQE/zpa-lss-feed-status
- Type: Q&A
- Posted: 2023-11-03T18:17:56.000Z
- Last activity: 2023-11-09T16:13:28.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p><br></p><p>Where within the Admin portal can I check that the LSS feeds I created are working and sending logs please.</p><p><br></p><p>Cheers,</p><p>Rallis</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A8kOjVCQU/terraform-zpa-access-policy-issue","lastmod":"2023-11-17T20:45:07.000Z","id":"0D54u0000A8kOjVCQU"} -->
## Terraform ZPA Access Policy issue

- Source: https://community.zscaler.com/s/question/0D54u0000A8kOjVCQU/terraform-zpa-access-policy-issue
- Type: Q&A
- Posted: 2023-11-15T07:09:04.000Z
- Last activity: 2023-11-17T20:45:07.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p><br></p><p>we have recently using terraform to build our access policy.</p><p><br></p><p>it gets build normally but i cannot access resources. assigned to that policy.</p><p><br></p><p>but when i go to the console and select the same scim group and save the policy it starts working.</p><p><br></p><p>has anyone seen this issue before with policy deployment using TF.</p><p><br></p><p>thanks,</p><p>Sudhir Mirajkar </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AA1wfTCQR/application-segments-regex","lastmod":"2024-05-06T21:45:31.000Z","id":"0D54u0000AA1wfTCQR"} -->
## Application Segments Regex

- Source: https://community.zscaler.com/s/question/0D54u0000AA1wfTCQR/application-segments-regex
- Type: Q&A
- Posted: 2023-11-22T13:45:02.000Z
- Last activity: 2024-05-06T21:45:31.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>During ZenithLive earlier this year, it was announced that support for pattern matching within application segments was going to be introduced. </p><p>Has there been any update on this?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ACB9xhCQD/app-connector-in-azure-vm-with-hub-and-spoke-architecture","lastmod":"2024-02-07T20:33:05.000Z","id":"0D54u0000ACB9xhCQD"} -->
## App Connector in Azure VM with Hub and spoke architecture

- Source: https://community.zscaler.com/s/question/0D54u0000ACB9xhCQD/app-connector-in-azure-vm-with-hub-and-spoke-architecture
- Type: Q&A
- Posted: 2023-12-06T22:19:00.000Z
- Last activity: 2024-02-07T20:33:05.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p>We are planning on setting up ZScaler ZPA connector in Azure. I have some questions with regards to best practice as I'm fairly new with this product.</p><p><br></p><p>1) Do you create a dedicated subnet for the VMs? If yes, any required subnet size</p><p><span style="background-color: var(--slds-g-color-neutral-base-100, var(--lwc-colorBackgroundInput,rgb(255, 255, 255))); color: var(--slds-g-color-neutral-base-30, var(--lwc-colorTextWeak,#696969));">2) Do you leave the default NSGs and leave it up to the connector to control inbound and outbound. </span></p><p>3) Any concerns implementing the app connector in the hub subnet with a hub and spoke architecture?</p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ACBH7WCQX/zscaler-zpa-pra-after-login-to-rdp-server-getting-the-error-message","lastmod":"2023-12-07T13:49:43.000Z","id":"0D54u0000ACBH7WCQX"} -->
## Zscaler ZPA PRA - After login to RDP server getting the error message

- Source: https://community.zscaler.com/s/question/0D54u0000ACBH7WCQX/zscaler-zpa-pra-after-login-to-rdp-server-getting-the-error-message
- Type: Q&A
- Posted: 2023-12-07T06:46:25.000Z
- Last activity: 2023-12-07T13:49:43.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Can someone help me on the below error message which we are getting for Zscaler ZPA PRA (RDP) sesssion</p><p><br></p><p>Could not set up connection to server: EXPTR_MT_TLS_SETUP_FAIL_PEER,error:000005C6:lib(0):func(0):reason: 1478</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ACDCZiCQP/how-to-access-zpa-api-for-sse-logs-and-admin-portal","lastmod":"2023-12-19T07:41:25.000Z","id":"0D54u0000ACDCZiCQP"} -->
## How to access ZPA API for SSE logs and admin portal?

- Source: https://community.zscaler.com/s/question/0D54u0000ACDCZiCQP/how-to-access-zpa-api-for-sse-logs-and-admin-portal
- Type: Q&A
- Posted: 2023-12-12T07:46:44.000Z
- Last activity: 2023-12-19T07:41:25.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p><br></p><p>I’m working on an integration for which I have the following queries, I would really appreciate if someone could help me to answer these queries:</p><ol><li>I am looking for&nbsp;Security Service Edge&nbsp;(SSE) details on the Zscaler ZPA API website,&nbsp;<a href="https://help.zscaler.com/zpa/understanding-zpa-api#ServiceEdges" rel="noopener noreferrer" target="_blank">https://help.zscaler.com/zpa/understanding-zpa-api#ServiceEdges</a>, but I could not locate them anywhere. Could someone confirm if ZPA capture SSE logs? If yes, then is there any web API, using which we can fetch these details, also it would be nice if anyone can guide us on how to setup this API.</li><li>As well as, wanted to create a profile on ZPA admin portal. <span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">Although we're not presently using the ZPA product, we're open to purchasing it if it aligns with our requirements, and we aim to do so as soon as possible. </span></li></ol><p><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">Before proceeding, we'd like to inquire about the availability of test credentials for ZPA API access or the possibility of obtaining a product evaluation license.</span></p><p><br></p><p><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">Your assistance in resolving this matter would be highly appreciated. Could someone please provide insights, guidance, or support to help me successfully access the API? Your prompt response and assistance are invaluable. </span></p><p><br></p><p><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;"><span class="ql-cursor">﻿</span>Thank you in advance for your help.</span></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ADFrCsCQL/secure-dns-preventing-users-from-working-with-zpa","lastmod":"2024-01-11T13:41:08.000Z","id":"0D54u0000ADFrCsCQL"} -->
## Secure DNS preventing users from working with ZPA

- Source: https://community.zscaler.com/s/question/0D54u0000ADFrCsCQL/secure-dns-preventing-users-from-working-with-zpa
- Type: Q&A
- Posted: 2023-12-14T17:11:20.000Z
- Last activity: 2024-01-11T13:41:08.000Z
- Answers: 3
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>HI. We are new to Zscaler ZPA and just started using it recently. We ran into an issue that is concerning with Secure DNS. If a user winds up with a DNS server that support secure DNS, such as Googles 8.8.8.8 and 8.8.4.4, the browsers are now defaulting to use Secure DNS. This basically breaks Zscaler ZPA from intercepting the URL's for our internal servers and the traffic does not come through the ZPA connectors. The workaround is to disable Secure DNS in the browser, but this does not seem to be a good long term solution. Is anyone else running into this, and does anyone know if Zscaler is working towards a better solution to handle clients using Secure DNS other than needing to have each user disable Secure DNS in their browsers? </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AFRKLvCQP/zpa-app-connector-support-for-rhel-v9","lastmod":"2024-01-22T00:37:34.000Z","id":"0D54u0000AFRKLvCQP"} -->
## ZPA APP connector support for RHEL v9

- Source: https://community.zscaler.com/s/question/0D54u0000AFRKLvCQP/zpa-app-connector-support-for-rhel-v9
- Type: Q&A
- Posted: 2024-01-10T17:15:40.000Z
- Last activity: 2024-01-22T00:37:34.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Do we know when ZPA APP Connector will support for RHEL v9 ?</p><p><br></p><p>I can't see anything on the community and the documentation only mentions v7 and v8</p><p><br></p><p>Regards</p><p><br></p><p>DABk</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AGOwrpCQD/changing-of-the-nameid-but-same-login-credential","lastmod":"2025-10-31T21:09:48.000Z","id":"0D54u0000AGOwrpCQD"} -->
## Changing of the NameID but same login credential

- Source: https://community.zscaler.com/s/question/0D54u0000AGOwrpCQD/changing-of-the-nameid-but-same-login-credential
- Type: Q&A
- Posted: 2024-01-20T01:44:59.000Z
- Last activity: 2025-10-31T21:09:48.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We are doing an exercise of changing user UPN name ( nameid) but all users and the login crednetial remain the same. I would like to know the behavior of the users that are already login with the device tag to old UPN name in ZIA. </p><p>Will the new upn get auto update on ZCC portal? Or when ZPA re-authenticate, it will update for ZPA and ZIA new upn?</p><p><br></p><p>Will there be disruption when the UPN name changed?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AJTk2ICQT/app-connectors-in-aws-scaling-group-api-key-use","lastmod":"2024-02-16T07:49:32.000Z","id":"0D54u0000AJTk2ICQT"} -->
## App Connectors in AWS Scaling Group - API Key use

- Source: https://community.zscaler.com/s/question/0D54u0000AJTk2ICQT/app-connectors-in-aws-scaling-group-api-key-use
- Type: Q&A
- Posted: 2024-02-13T06:57:11.000Z
- Last activity: 2024-02-16T07:49:32.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi, I want to set up an ASG for my App Connectors. I have a highly time dependant work load, and so, i want to shut machines off outside of work hours.. I need at least 6 conectors during work hours and just 1 out of work hours. </p><p><br></p><p>The API key that i get from the console has a maximum number of uses, so, after a few scaling events, i'm rapidally going to hit that limit. </p><p><br></p><p>Is there a way to set an 'unlimited' use of the API key?</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AJUS2ICQX/hybrid-autopilot-dj-with-zpa-windows-prelogon","lastmod":"2024-02-15T21:08:38.000Z","id":"0D54u0000AJUS2ICQX"} -->
## Hybrid Autopilot DJ with ZPA  windows pre-logon

- Source: https://community.zscaler.com/s/question/0D54u0000AJUS2ICQX/hybrid-autopilot-dj-with-zpa-windows-prelogon
- Type: Q&A
- Posted: 2024-02-13T16:23:39.000Z
- Last activity: 2024-02-15T21:08:38.000Z
- Answers: 9
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello guys, I m setting up a hybrid domain join autopilot, this ia 90% done, is working the objects are created in both AAD and AD. The only thing is missing is the Zscaler prelogon screen.</p><p>we are using zscaler with device tunel in our environment, but I do need this to be activated before the first user log on. I see the zscaler installed during the ESP but I cannot see the “Zscaler Diagnostics” after the device setup is finished. We need this so the user not be obliged to be in the office in order to be able to logon the the PC. Does zscaler support this feature? Basically I need that the ZPA to activate and connect the to domain network before the first very logon. Thank you.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AJUUdSCQX/zia-and-zpa-performance-over-starlink","lastmod":"2024-02-29T07:35:12.000Z","id":"0D54u0000AJUUdSCQX"} -->
## ZIA and ZPA performance over Starlink

- Source: https://community.zscaler.com/s/question/0D54u0000AJUUdSCQX/zia-and-zpa-performance-over-starlink
- Type: Q&A
- Posted: 2024-02-13T17:25:48.000Z
- Last activity: 2024-02-29T07:35:12.000Z
- Answers: 3
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Are there any customers out there using Starlink? Wanted to know if there were any connectivity or performance issues with ZIA and/or ZPA. Was curious which Service Edges / Brokers the users would connect to.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AJWJN9CQP/reducing-internet-egress-costs-in-aws-with-app-connectors","lastmod":"2024-03-27T22:05:49.000Z","id":"0D54u0000AJWJN9CQP"} -->
## Reducing Internet Egress costs in AWS with App Connectors.

- Source: https://community.zscaler.com/s/question/0D54u0000AJWJN9CQP/reducing-internet-egress-costs-in-aws-with-app-connectors
- Type: Q&A
- Posted: 2024-02-17T08:17:59.000Z
- Last activity: 2024-03-27T22:05:49.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello, </p><p><br></p><p>I'm looking for ideas on how best to reduce egress costs that a ZPA app connectors use. </p><p><br></p><p>Are there any options for the app connectors to use Private Connectivity, to connect to the 'Zero Trust Exchange'? </p><p><br></p><p>Im not pushing things through Nat gateways, ( the cost of EIP's is less than Nat gateways ).. </p><p><br></p><p><br></p><p>I have >100 sites which are connected over an Internet based SDWAN, and 10G connectivity between that sdwan and AWS ( direct connect ).. Dropping traffic out to the internet and back is costing $$</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AJWONHCQ5/zpa-private-service-edges-do-clients-connect-on-private-ip-addresss","lastmod":"2024-03-16T20:00:56.000Z","id":"0D54u0000AJWONHCQ5"} -->
## ZPA Private Service Edges - Do clients connect on Private IP address.s

- Source: https://community.zscaler.com/s/question/0D54u0000AJWONHCQ5/zpa-private-service-edges-do-clients-connect-on-private-ip-addresss
- Type: Q&A
- Posted: 2024-02-17T23:59:41.000Z
- Last activity: 2024-03-16T20:00:56.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>If i deploy Private Service Edges inside my network, will clients connect to these private Edges on The private IP's that they have?</p><p><br></p><p>If my clients are 'on prem' somewhere, I want them to use the Private Service Edges, and not a public one. If the clients are 'off prem' ( for example working from home, or mobile ), they can connect to a public edge. </p><p><br></p><p>How does ZPA, know if the client is on the 'on prem' network? It cant' just be ip address'sing.. My on prem network is in the 10/8 range, but so are everyones home networks etc etc. </p><p><br></p><p><br></p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AKhhSCCQZ/user-traffic-dynamically-connects-public-service-edge-instead-of-using-private-service-edge","lastmod":"2024-02-29T16:21:31.000Z","id":"0D54u0000AKhhSCCQZ"} -->
## user traffic dynamically connects public service edge instead of using private service edge

- Source: https://community.zscaler.com/s/question/0D54u0000AKhhSCCQZ/user-traffic-dynamically-connects-public-service-edge-instead-of-using-private-service-edge
- Type: Q&A
- Posted: 2024-02-21T18:49:56.000Z
- Last activity: 2024-02-29T16:21:31.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>we are using private service edge in our organizations sometimes when accessing the internal applications it uses the public edge even though the user resides has private service edge deployed in same location. </p><p><br></p><p>i would like know if there is anyway the users can user Private service edge within the same location instead of going to public edge.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AKihLqCQJ/app-connector-in-azure-vs-cloud-connector","lastmod":"2024-03-01T16:49:08.000Z","id":"0D54u0000AKihLqCQJ"} -->
## App connector in Azure vs Cloud Connector

- Source: https://community.zscaler.com/s/question/0D54u0000AKihLqCQJ/app-connector-in-azure-vs-cloud-connector
- Type: Q&A
- Posted: 2024-02-23T14:48:40.000Z
- Last activity: 2024-03-01T16:49:08.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi guys, trying to understand the difference between using an app connector in Azure vs Azure cloud connector?</p><p><br></p><p>Thanks.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AM2te1CQB/zpa-private-service-edges-in-scaling-group-what-happens-to-traffic-when-a-member-of-the-the-group-scales-in","lastmod":"2024-03-04T15:41:46.000Z","id":"0D54u0000AM2te1CQB"} -->
## ZPA Private Service Edges in Scaling Group.  What happens to traffic when a Member of the the group scales in..

- Source: https://community.zscaler.com/s/question/0D54u0000AM2te1CQB/zpa-private-service-edges-in-scaling-group-what-happens-to-traffic-when-a-member-of-the-the-group-scales-in
- Type: Q&A
- Posted: 2024-03-02T22:15:38.000Z
- Last activity: 2024-03-04T15:41:46.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I am planning on deploying Private Service Edges, which will be part of a scaling group, so we can have an appropriate amount of resources running to match demand, which changes widely over the day. </p><p><br></p><p>If one of my private edge servers is shutdown, what happens to traffic flows that are using that edge? Do they just fail, and the client re-establishes connectivitity to another of the service edges? Does that result in a noticable impact to the end users? </p><p><br></p><p>Is there a way of signalling to Zscaler, that this node is marked for shutdown so that all traffic can be gracefully drained away, before shutdown?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ANByXFCQ1/zpa-new-application-setup","lastmod":"2024-03-11T09:11:34.000Z","id":"0D54u0000ANByXFCQ1"} -->
## ZPA New Application Setup

- Source: https://community.zscaler.com/s/question/0D54u0000ANByXFCQ1/zpa-new-application-setup
- Type: Q&A
- Posted: 2024-03-11T06:44:22.000Z
- Last activity: 2024-03-11T09:11:34.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Scenario 2 - A new office is being brought online, which will use the existing location like A, Connectors &amp; PSEs. What would you check to ensure that ZPA has been setup correctly for the new site?&nbsp;</p><p><br></p><p>Scenario 2 – A user is trying to access an application and is not working. Assuming it is an issue with ZPA, what troubleshooting steps would you run through ?</p><p><br></p><p><br></p><p>Scenario 3 – New application is onboarded into location A, what is required and how would you set this application up in Zscaler ?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AODuJOCQ1/screen-sharing-not-working-with-jabber-on-zpa","lastmod":"2024-03-22T19:03:28.000Z","id":"0D54u0000AODuJOCQ1"} -->
## Screen Sharing Not Working with Jabber on ZPA

- Source: https://community.zscaler.com/s/question/0D54u0000AODuJOCQ1/screen-sharing-not-working-with-jabber-on-zpa
- Type: Q&A
- Posted: 2024-03-21T19:37:05.000Z
- Last activity: 2024-03-22T19:03:28.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have been in the process of rolling out ZPA across our company and running into more people complaining about not being able to use the screen grab or screen share features while on ZPA.</p><p><br></p><p>During our deployment, all of the voice stuff was advised to be bypassed. But wondering if anyone else ran into this and would have some tips on which servers from Jabber need to be added to the bypass, and which need their own app segment with specific ports to allow this feature.</p><p><br></p><p>Thanks,</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000APgd3gCQB/trusted-network-detection","lastmod":"2024-04-15T07:11:11.000Z","id":"0D54u0000APgd3gCQB"} -->
## Trusted Network Detection

- Source: https://community.zscaler.com/s/question/0D54u0000APgd3gCQB/trusted-network-detection
- Type: Q&A
- Posted: 2024-04-10T23:32:03.000Z
- Last activity: 2024-04-15T07:11:11.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi!</p><p><br></p><p>Has anyone here tested Zscaler's Trusted Network Detection to disable Private Access when connected to a third-party VPN like Cisco or Palo Alto Networks GlobalProtect (PanGP)? While working with TAC, we reached a point where a VPN with a split tunnel and traffic with those specified on aPanGP VPN worked, and the rest was directed to ZPA. However, we couldn't completely turn off the Private Access to say 'Disable, on a trusted network' or something similar. I wonder if anyone here has tested this and achieved the goal.</p><p><br></p><p>I simply want Zscaler Private Access to be turned off when connected to PanGP VPN.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AQSFIsCQP/zia-zpa-certification","lastmod":"2024-05-13T20:01:43.000Z","id":"0D54u0000AQSFIsCQP"} -->
## ZIA & ZPA Certification

- Source: https://community.zscaler.com/s/question/0D54u0000AQSFIsCQP/zia-zpa-certification
- Type: Q&A
- Posted: 2024-04-15T04:14:12.000Z
- Last activity: 2024-05-13T20:01:43.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Team,</p><p><br/></p><p>I recently completed ZTCA training and certification. I would like to proceed further with certification path. I would like to learn about ZIA &amp; ZPA, but there are no official certification and learnt those have been removed or changed. Please advise on what training &amp; certification I have to take up to be at professional level in Zscaler products.</p><p><br></p><p>Thanks,</p><p>Sridhar Prasad</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AQTl4ICQT/zscaler-academy-trainings-and-cpes","lastmod":"2024-04-17T11:52:53.000Z","id":"0D54u0000AQTl4ICQT"} -->
## Zscaler Academy trainings and CPEs

- Source: https://community.zscaler.com/s/question/0D54u0000AQTl4ICQT/zscaler-academy-trainings-and-cpes
- Type: Q&A
- Posted: 2024-04-17T11:52:53.000Z
- Last activity: 2024-04-17T11:52:53.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi all, and especially the Zscaler Academy training team,</p><p><br></p><p>I have embarked in a number of training courses over the last couple of months, which allowed me to refresh my knowledge of ZIA and ZPA, by reaching the Zscaler Certified Delivery Specialist (ZCDS) status which I earned just now!</p><p><br></p><p>I had to also go thru the new EDU-200 and EDU-202 plus the ZIA and ZPA Delivery labs and exams, and spent quite some time perfecting my skills, something which is very useful to me in my delivery specialist role.</p><p><br></p><p>My question is on the CPEs, as I am not clear how to show the fact that I have effectively perform these activities to allow ISC2 to grant me the CPEs for my CISSP renewal.</p><p><br></p><p>Here is what I mean. If I take a look at the ZPA Delivery Specialist Practical exam I have 8 CPEs and a certificate which proves the achievement:</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000hiZVD?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p>I presume that simply sending the certificate as a proof for my CPEs would be enough.</p><p><br></p><p>For what concerns ZIA Delivery Specialist Practical exam, which is listed at 9 CPEs, I do not see that link to download a certificate:</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000hiYls?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p>Is this an error on the Academy website?</p><p><br></p><p>Moreover, for what concerns EDU-200 and EDU-202 labs and exams, from the Academy website I see that they should provide 30 CPEs, which is a very high number, so I am prone to believe that this is an error:</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000hiZWa?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p>Can anyone from the Zscaler Academy team clarify the matter?</p><p><br></p><p>Hopefully this can also be useful to other Zscaler technicians who can benefit from the CPEs accrued during the Zscaler Academy trainings.</p><p><br></p><p>Many thanks,</p><p><br></p><p>Luca Bertagnolio</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ARPI2bCQH/zpa-dns-timeouts-and-other-questionable-behavior","lastmod":"2025-07-01T19:36:48.000Z","id":"0D54u0000ARPI2bCQH"} -->
## ZPA DNS Timeouts and other questionable behavior

- Source: https://community.zscaler.com/s/question/0D54u0000ARPI2bCQH/zpa-dns-timeouts-and-other-questionable-behavior
- Type: Q&A
- Posted: 2024-04-23T21:34:22.000Z
- Last activity: 2025-07-01T19:36:48.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>My company just started using Zscaler and we're having DNS issues that seem bizarre to me. I have performed significant analysis using packet captures, including the one built into ZCC itself, but I don't fully understand what I'm seeing as things just aren't adding up how I would expect.</p><p><br></p><p>Let me start by saying I found another old post that pretty much sums up exact issue, which is regarding timeouts during DNS queries. That can be found here: <a rel="noopener noreferrer" href="https://community.zscaler.com/Zenith/s/question/0D54u00009evlEeCAI/problem-with-zscaler-dns-hijacking-advanced">https://community.zscaler.com/Zenith/s/question/0D54u00009evlEeCAI/problem-with-zscaler-dns-hijacking-advanced</a></p><p><br></p><p>Short summary of the above post: DNS lookups result in timeouts when using NSLookup and not adding a trailing dot at the end of the fqdn. Sometimes multiple timeouts but ultimately resolving the query.</p><p><br></p><p>I understand NSLookup treats anything without the trailing dot as unqualified, so the pc then goes about tacking on its dns suffixes as part of the query. What I don't understand is the behavior of where those queries are sent to be resolved.</p><p><br></p><p>Say <strong>my domain dns suffix is example.net </strong>and <strong>Zscaler is configured to route *.example.net across the internal tunnel.&nbsp;</strong></p><p><br></p><p>When I submit a query for '<strong>host1.example.net' <em>(no trailing dot)</em></strong>, the packet captures show the request goes out as '<strong>host1.example.net.example.net.'.</strong> That part I expect. What I don't expect is that query is attempted to be resolved by external DNS resolvers. Note: this domain does exist externally as we're using split-brain dns.</p><p><br></p><p>Eventually the query for the intended hostname resolves internally as expected. Additionally submitting<strong> 'host1.example.net.' <em>(with the trailing dot)</em></strong>, it resolves instantly and all traffic remains internal.</p><p><br></p><p>In short: Why would '<strong>host1.example.net.example.net.</strong>' attempt to resolve externally, at all? While the host is malformed due to dns suffix addition, it is ultimately still on the example.net domain, which should remain internal. I would expect all queries for this domain to remain internal while ZCC is connected.</p><p><br></p><p>Secondly, I've noticed resolving simple netbios names fail entirely the first time (not timeout), but subsequent retries fine. I'm sure it's cached somewhere from that point forward but I don't understand the first time failure.</p><p><br></p><p>I'm hoping someone with advanced knowledge here of ZPA can help me out.</p><p><br></p><p>Note: I'm not sure what community 'topic' this would fall under. I see nothing for DNS specifically.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ASPEXfCQP/zpa-frequently-disconnecteds","lastmod":"2024-05-14T04:11:40.000Z","id":"0D54u0000ASPEXfCQP"} -->
## ZPA frequently Disconnecteds

- Source: https://community.zscaler.com/s/question/0D54u0000ASPEXfCQP/zpa-frequently-disconnecteds
- Type: Q&A
- Posted: 2024-05-06T10:23:36.000Z
- Last activity: 2024-05-14T04:11:40.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><img src="/sfc/servlet.shepherd/version/download/0684u00000gCrBn?asPdf=false&amp;operationContext=CHATTER">Hi Guys we facing issue frequent ZPA disconnects that causing shared drive connection drops after every 15 to 20 min if anybody help here with their suggestion highly appreciate attaching snap . </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ASPVrECQX/selective-ziazpa-forwarding-based-on-internally-resolved-ip-address","lastmod":"2024-05-24T15:25:43.000Z","id":"0D54u0000ASPVrECQX"} -->
## Selective ZIA/ZPA forwarding based on internally resolved IP Address

- Source: https://community.zscaler.com/s/question/0D54u0000ASPVrECQX/selective-ziazpa-forwarding-based-on-internally-resolved-ip-address
- Type: Q&A
- Posted: 2024-05-06T16:32:51.000Z
- Last activity: 2024-05-24T15:25:43.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have Azure Private Endpoints on domains such as *.windows.net that resolve to private IPs within our data center, but Microsoft also has public endpoints on that domain for general use. Would there be any way to selectively route traffic to hosts on the .windows.net domain to ZPA ONLY if the host resolves to a private IP within our data center? To date we have been adding the FQDN of the Private Endpoint hosts to Application Segments in ZPA, but with the volume of new Private Endpoints being created this is becoming difficult. So we are looking for any solution that would allow us to send traffic to *.windows.net over ZPA, but ONLY if that resolves to a private IP within our data center.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ASS4atCQD/cisco-jabber-traffic-not-working-via-zpa","lastmod":"2024-05-12T23:42:02.000Z","id":"0D54u0000ASS4atCQD"} -->
## Cisco Jabber traffic not working via ZPA

- Source: https://community.zscaler.com/s/question/0D54u0000ASS4atCQD/cisco-jabber-traffic-not-working-via-zpa
- Type: Q&A
- Posted: 2024-05-12T23:42:02.000Z
- Last activity: 2024-05-12T23:42:02.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>HI Team,</p><p><br></p><p>We are trying to enable cisco jabber traffic via ZPA, we pointed the below SRV records to internal DNS server.</p><p>_cisco-uds._tcp.example.com -live-cucmq.internaldomain,ad</p><p>_cublogin._tcp.example.com-live-cimp.internaldomain.ad</p><p>while testing message is delivering successfully, but audio and video is not working</p><p>kindly help to solve the same.</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ASS4smCQD/cisco-jabber-via-zpaaudio-and-video-not-working","lastmod":"2024-05-13T08:07:57.000Z","id":"0D54u0000ASS4smCQD"} -->
## Cisco Jabber via ZPA-Audio and video not working

- Source: https://community.zscaler.com/s/question/0D54u0000ASS4smCQD/cisco-jabber-via-zpaaudio-and-video-not-working
- Type: Q&A
- Posted: 2024-05-12T23:40:19.000Z
- Last activity: 2024-05-13T08:07:57.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>HI Team,</p><p><br></p><p>We are trying to enable cisco jabber traffic via ZPA, we pointed the below SRV records to internal DNS server.</p><p>_cisco-uds._tcp.example.com -live-cucmq.internaldomain,ad</p><p>_cublogin._tcp.example.com-live-cimp.internaldomain.ad</p><p>while testing message is delivering successfully, but audio and video is not working </p><p>kindly help to solve the same.</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ATFiu7CQD/trusted-network-selection-criteria","lastmod":"2024-06-04T13:48:52.000Z","id":"0D54u0000ATFiu7CQD"} -->
## Trusted Network selection criteria

- Source: https://community.zscaler.com/s/question/0D54u0000ATFiu7CQD/trusted-network-selection-criteria
- Type: Q&A
- Posted: 2024-05-20T09:47:57.000Z
- Last activity: 2024-06-04T13:48:52.000Z
- Answers: 7
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi</p><p>I'd like to know if there's a selection criteria to choose a trusted netwok before another when a user matches conditions for several networks.</p><p><br></p><p>I mean, considering that I have defined 3 trusted networks:</p><p>TN A -> condition= DNS server A. Only suitable for users on site A</p><p>TN B -> condition= DNS server B. On ly suitable for users on site B</p><p>TN C -> condition= DNS search domain. For users on any other site (but users on site A and B also match this condition)</p><p><br></p><p>And I also have deployed Private Service Edges for ZPA on site A and B, and associated the Trusted Network A with the Private Service Edge A, and the Trusted Network B with the Private Service Edge B</p><p><br></p><p><br></p><p>For me it seems clear that users from a site diferent than A or B will match the Trusted Network C due to their DNS Search Domain and set the ZPA broker to any of the Private Service Edges based on geolocation, but what happens with users on sites A and B?</p><p><br></p><p>I want that users on site A set their ZPA broker to Private Service Edge on site A as they match the Trusted Network A, but they also match the Trusted Network C condition. How can I know what trusted network will these users consider being when they match conditions from more than one?</p><p><br></p><p>Thanks. Regards</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AUjkWqCQJ/zpa-app-connector-prebuilt-images","lastmod":"2025-08-22T07:27:53.000Z","id":"0D54u0000AUjkWqCQJ"} -->
## ZPA App Connector Pre-built images

- Source: https://community.zscaler.com/s/question/0D54u0000AUjkWqCQJ/zpa-app-connector-prebuilt-images
- Type: Q&A
- Posted: 2024-06-04T20:36:07.000Z
- Last activity: 2025-08-22T07:27:53.000Z
- Answers: 2
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Do we know when the OVA's for RHEL9 App Connectors will be available.</p><p>Do we also know when the RHEL9 Azure/AWS Marketplace images will also be available?</p><p><br></p><p>Would like to start planning/testing deployments for these.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AUkRIECQ3/zscaler-browser-access-upload-certificate","lastmod":"2024-06-06T03:07:59.000Z","id":"0D54u0000AUkRIECQ3"} -->
## Zscaler browser access - Upload certificate

- Source: https://community.zscaler.com/s/question/0D54u0000AUkRIECQ3/zscaler-browser-access-upload-certificate
- Type: Q&A
- Posted: 2024-06-06T03:07:07.000Z
- Last activity: 2024-06-06T03:07:59.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi All,</p><p>I tried to upload a web cert + key to Zscaler to use for browser access but it didn't work. We converted to base 64 encoded ASCII .pem file.</p><p>We were able to upload a cert in the past, but it was using RSA and this new cert uses ECC.</p><p><br></p><p>Does anyone know if Zscaler supports uploading cert that uses the ECC algorithm?</p><p><br></p><p>Thanks</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AVLKZQCQ5/complete-lost-internet-while-zpa-turn-on","lastmod":"2024-06-13T11:17:46.000Z","id":"0D54u0000AVLKZQCQ5"} -->
## complete lost internet while ZPA turn on.

- Source: https://community.zscaler.com/s/question/0D54u0000AVLKZQCQ5/complete-lost-internet-while-zpa-turn-on
- Type: Q&A
- Posted: 2024-06-12T07:38:16.000Z
- Last activity: 2024-06-13T11:17:46.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello Zscaler Team</p><p><br></p><p>Good day to you.</p><p>For some reason, I trying to help a Zscaler private access issue but I don't have right to open support case.</p><p>Background:</p><p>ZPA working fine at anywhere for company S. Company F have Zscaler by different tenant with S. I believe compnay F have PZEN.</p><p>issue: while S user go to one of F network. </p><p>If turn on company S ZPA, PC completely lost internet and intranet access. it can ping 8.8.8.8. but can not access any website and outlook offline.</p><p>If turn off company S ZPA, PC can access internet properly. Of course, it can not access company S internal resource because ZPA off.</p><p><br></p><p>May you please guide me how to investigate this issue?</p><p>Thank you.</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AVLVgACQX/zpa-powershell-module","lastmod":"2024-06-13T11:19:50.000Z","id":"0D54u0000AVLVgACQX"} -->
## ZPA Powershell Module

- Source: https://community.zscaler.com/s/question/0D54u0000AVLVgACQX/zpa-powershell-module
- Type: Q&A
- Posted: 2024-06-12T10:38:29.000Z
- Last activity: 2024-06-13T11:19:50.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I'm trying to automate user registration for ZCC so that we can have users sign in to ZCC and then cache their network credentials in Windows 11. ZCC/ZPA doesnt allow our users to remotely sign into our domain without first registering on ZCC app. Currently this means we have to preconfigure our laptops for users before shipping them to them otherwise our users cant log on as they cant contact the domain. I've followed the community article to set up a local user and then let the user log into that, register ZCC by signing into that and then running a script to cache network credentials but this is not easy for our users to do. My plan is to have a user that logs in locally, runs a script as Shell, prompts for email address, username and password and then uses that to register ZCC, cache the network credentials, delete the current logged in user and then restart. Then all our user has to do is sign in as themselves once the laptop has rebooted, also means the local user account is removed as well to maintain device security.</p><p>I am trying to use this PS command: </p><p><br></p><p>Connect-ZpaService -Username $email -Password $password -Gateway "zpa-gateway.example.com"</p><p><br></p><p>However the powershell module ZpaService doesnt seem to exist. Does anyone know if this is an actual PS module and if so where i can get it from? Doesnt seem to be in the PS Gallery</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AWSg1BCQT/zpa-reauthentication-prompt","lastmod":"2024-06-28T00:50:55.000Z","id":"0D54u0000AWSg1BCQT"} -->
## ZPA Reauthentication Prompt

- Source: https://community.zscaler.com/s/question/0D54u0000AWSg1BCQT/zpa-reauthentication-prompt
- Type: Q&A
- Posted: 2024-06-26T15:25:39.000Z
- Last activity: 2024-06-28T00:50:55.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We are in the initial deployment of ZPA to our employees and I am struggling with the ZPA Reauthentication enforcement. The Private Access Expired window appears as expected but it is being missed, and is showing up behind user windows. For example, if a users private access expires while their laptop is sleeping, they open the screen and resume working but the ZPA reauthentication window is behind their working windows and is inadvertently being missed.</p><p><br></p><p>How is everyone getting around this issue? The users are getting frustrated (as is our help desk) because they are thinking systems are down but in fact all they need to do is authenticate with Zscaler.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AXPXDoCQP/remote-tanium-pxe-boot-server-with-zpa-allowing-local-ports-to-pass","lastmod":"2024-07-09T18:39:45.000Z","id":"0D54u0000AXPXDoCQP"} -->
## Remote Tanium PXE Boot Server with ZPA allowing local ports to pass.

- Source: https://community.zscaler.com/s/question/0D54u0000AXPXDoCQP/remote-tanium-pxe-boot-server-with-zpa-allowing-local-ports-to-pass
- Type: Q&A
- Posted: 2024-07-05T21:21:47.000Z
- Last activity: 2024-07-09T18:39:45.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We are currently trying to architect a remote solution for our organization to have a 3rd party vendor image new devices for us using Tanium PXE boot. We have installed ZPA on this server and have created a forwarding bypass rule for the PXE port 66-69 and 4011. When they initiate the process it fails. When ZPA is off its works up until the point it needs to grab the offline domain join file (ODJ). Which is understandable. Anyone ever successfully accomplish this before? </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AYRoAuCQL/zpa-device-posture-failure-crowdstrike-falcon-zero-trust-risk-score","lastmod":"2025-09-03T12:06:16.000Z","id":"0D54u0000AYRoAuCQL"} -->
## ZPA Device Posture failure - CrowdStrike Falcon Zero Trust Risk Score

- Source: https://community.zscaler.com/s/question/0D54u0000AYRoAuCQL/zpa-device-posture-failure-crowdstrike-falcon-zero-trust-risk-score
- Type: Q&A
- Posted: 2024-07-18T15:04:54.000Z
- Last activity: 2025-09-03T12:06:16.000Z
- Answers: 9
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have recently seen an issue with Windows ZPA Device Posture failure for CrowdStrike Falcon Zero Trust Risk Score. When checking the Falcon console the risk score is there and acceptable. The fix we have found is on the endpoint to reset the network adapter. </p><p><br></p><p>This is under Settings > Network &amp; Internet</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000iFnhu?asPdf=false&amp;operationContext=CHATTER"></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AYSFWLCQ5/connecting-to-azure-sql-via-zpa-an-error-occurred-during-the-prelogin-handshake","lastmod":"2024-07-20T04:15:18.000Z","id":"0D54u0000AYSFWLCQ5"} -->
## Connecting to Azure SQL via ZPA - an error occurred during the pre-login handshake.

- Source: https://community.zscaler.com/s/question/0D54u0000AYSFWLCQ5/connecting-to-azure-sql-via-zpa-an-error-occurred-during-the-prelogin-handshake
- Type: Q&A
- Posted: 2024-07-19T11:14:46.000Z
- Last activity: 2024-07-20T04:15:18.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p><br></p><p>We had setup Azure SQL access via ZPA. In Azure SQL firewall rules, we would add app connectors egress/public IP address and then have app segment with DNS of the Azure SQL server, i.e. myserver.database.windows.net and port TCP 1433. And a policy for this.</p><p><br></p><p>It was working for a year, but this month, without us changing anything in ZPA or Azure SQL, connection started to fail with error message:</p><p><br></p><p><span style="font-family: courier;">A connection was successfully established with the server, but then an error occurred during the pre-login handshake. (provider: TCP Provider, error: 0 - The specified network name is no longer available.)</span></p><p><br></p><p>If I white list my IP on Azure SQL and turn off ZPA, then there is a Pre-login authentication going on with a cert from MS. It seems this Pre-Login handshake does not work now via ZPA. </p><p><br></p><p>Anyone seen this and have some advice?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AZExkKCQT/howto-import-zpa-app-connector-vmware-ova-into-proxmox","lastmod":"2024-07-29T05:01:11.000Z","id":"0D54u0000AZExkKCQT"} -->
## Howto import ZPA App Connector VMware OVA into Proxmox

- Source: https://community.zscaler.com/s/question/0D54u0000AZExkKCQT/howto-import-zpa-app-connector-vmware-ova-into-proxmox
- Type: Q&A
- Posted: 2024-07-25T08:13:27.000Z
- Last activity: 2024-07-29T05:01:11.000Z
- Answers: 0
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Zscaler recently released updated ZPA App Connector images in OVA format. Which are great if your running VMWare. But I'm running Proxmox in a lab environment. How do you get these OVA images imported into Promox? Let me show you in 5 steps.</p><p><br></p><p><strong>1. Import OVA into Proxmox</strong></p><p>First step is to place a copy of the 'zpa-connector-el9-2024.06.05.ova' file onto your Proxmox host using SCP. Once there, you will need to unpackage the OVA file(which is simply a TAR file) with the following command: "tar -xvf zpa-connector-el9-2024.06.05.ova". This will unpackage the OVA file and provide the following 3 files:</p><p>zpa-connector-el9-2024.06.05-disk1.vmdk</p><p>zpa-connector-el9-2024.06.05.mf</p><p>zpa-connector-el9-2024.06.05.ovf</p><p><br></p><p>From here, we need to ask Proxmox to read the OVF file and import the disk and host settings. We can do this with the 'qm' command and the following syntax:</p><p>qm importovf zpa-connector-el9-2024.06.05.ovf</p><p><br></p><p>An example of what this looks like in my lab is this:</p><p># qm importovf 104 zpa-connector-el9-2024.06.05.ovf bay3-lvm</p><p>Logical volume "vm-104-disk-0" created.</p><p>transferred 0.0 B of 64.0 GiB (0.00%)</p><p>transferred 655.4 MiB of 64.0 GiB (1.00%)</p><p>...</p><p>transferred 64.0 GiB of 64.0 GiB (100.00%)</p><p><br></p><p><strong>2. Assigning Network interface</strong></p><p><br></p><p>Unfortunately, Proxmox can't convert the OVF file perfectly and we need to tell it about the new network. interface we want to use. We can do that with the following command (change the command to suit your host ID and network bridge):</p><p><br></p><p># qm set 104 --net0 virtio,bridge=vmbr0</p><p>update VM 104: -net0 virtio,bridge=vmbr0</p><p><br></p><p><strong>3. Assigning SCSI card</strong></p><p><br></p><p>We also need to tell Proxmox about the SCSI hardware that this VM is using. I'm using a vmware emulated pvscsi card that seems to work well for this host:</p><p><br></p><p># qm set 104 --scsihw pvscsi</p><p>update VM 104: -scsihw pvscsi</p><p><br></p><p><strong>4. Assign CPU Architecture</strong></p><p><br></p><p>Next, I need to tell Proxmox about my CPU architecture, x86-64. You can do that with this command:</p><p><br></p><p># qm set 104 --cpu cputype=x86-64-v2-AES</p><p>update VM 104: -cpu cputype=x86-64-v2-AES</p><p><br></p><p><strong>5. And now you should be able to start the VM we just imported and log in</strong></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000iwGSv?asPdf=false&amp;operationContext=CHATTER"></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AZG3AgCQL/browser-access-to-public-website","lastmod":"2024-08-10T09:09:45.000Z","id":"0D54u0000AZG3AgCQL"} -->
## Browser Access to Public Website

- Source: https://community.zscaler.com/s/question/0D54u0000AZG3AgCQL/browser-access-to-public-website
- Type: Q&A
- Posted: 2024-07-27T12:43:17.000Z
- Last activity: 2024-08-10T09:09:45.000Z
- Answers: 3
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi, </p><p><br></p><p>There are few public website restricted to our office public IP.</p><p><br></p><p>For ZCC users, they are fine by using SIPA function. However, there are many unmanaged devices and company policy does not allowed to install zcc. </p><p><br></p><p>We are thinking of the possibility to leverage Browser Access to reach those public restricted to our office public IP.</p><p><br></p><p>Anyone did the same approach? If yes, please teach me how.</p><p><br></p><p>I have done several test, but the challenge is that i could not added CNAME for those public website as they dont belonged to my company.</p><p><br></p><p>I have tested to added dummy domain on ZPA App segment, and add cname record in company DNS to map public website to dummy domain. Network-wise, it works but the destination website could not returned any content, the browser display black content.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Aaj7eECQQ/problem-to-access-network-drive-via-zpa","lastmod":"2024-08-21T08:12:38.000Z","id":"0D54u0000Aaj7eECQQ"} -->
## Problem to access network drive via ZPA

- Source: https://community.zscaler.com/s/question/0D54u0000Aaj7eECQQ/problem-to-access-network-drive-via-zpa
- Type: Q&A
- Posted: 2024-08-21T00:13:25.000Z
- Last activity: 2024-08-21T08:12:38.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Team,</p><p><br></p><p>we have enabled the shared drive access via ZPA. the shared drives are mapped via group policy, the issue is the shared drives are required to be re-mapped every morning since using Zscaler. </p><p>is there any way to troubleshoot and solve this issue.</p><p><br></p><p>Kindly help on the same.</p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AakN4xCQE/zpa-device-authentication","lastmod":"2024-08-27T16:54:30.000Z","id":"0D54u0000AakN4xCQE"} -->
## ZPA device authentication

- Source: https://community.zscaler.com/s/question/0D54u0000AakN4xCQE/zpa-device-authentication
- Type: Q&A
- Posted: 2024-08-22T20:03:04.000Z
- Last activity: 2024-08-27T16:54:30.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p>Could someone provide a use case for zpa machine authentication?</p><p>https://help.zscaler.us/zpa/authentication/machine-authentication</p><p>Thanks,</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AcZmhFCQS/zscaler-zpa-access-functionality","lastmod":"2024-10-04T13:40:06.000Z","id":"0D54u0000AcZmhFCQS"} -->
## Zscaler ZPA Access Functionality

- Source: https://community.zscaler.com/s/question/0D54u0000AcZmhFCQS/zscaler-zpa-access-functionality
- Type: Q&A
- Posted: 2024-09-27T02:18:54.000Z
- Last activity: 2024-10-04T13:40:06.000Z
- Answers: 3
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Please clarify the following for me regarding Zscaler ZPA access and functionality for Cisco SDWAN with Zscaler security umbrella?</p><p><br></p><ol><li>For any access (from any sites) which need to go to datacenters (for retrieval of any data from servers hosted in those datacenters), will the Zscaler Edge Node just “route” that flow to the firewall positioned in that datacenter?</li><li>Or will any security processing be done in the Zscaler cloud for that specific flow? I would assume none, as it would entail double processing for security on that flow increasing transmission delay!!!</li><li>Will there be any case(s) where flows will just be “routed” by the Zscaler Cloud to the datacenter FW’s (and not security processed)?</li><li>Also, for user-to-server traffic and server-to-server traffic how would this traffic routing be any different?</li></ol><p>Would highly appreciate if someone could clarify this access functionality (or provide reference to a document that clarifies this issue).</p><p><br></p><p>Thanks very much.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Acs4IACQY/how-can-browser-access-allow-for-3rd-party-to-access-internal-web-site","lastmod":"2024-10-14T04:19:09.000Z","id":"0D54u0000Acs4IACQY"} -->
## How can browser access allow for 3rd party to access internal web site?

- Source: https://community.zscaler.com/s/question/0D54u0000Acs4IACQY/how-can-browser-access-allow-for-3rd-party-to-access-internal-web-site
- Type: Q&A
- Posted: 2024-10-10T01:31:43.000Z
- Last activity: 2024-10-14T04:19:09.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p><br></p><p>I'm trying to make use of ZPA user portal for contractor to access our local resources through unmanaged device. It is fine to create user portal, withc public FQDN and CNAME. </p><p><br></p><p>However, there's problem on the portal link to access the local resources, with local domain, says .local. It's fine to access with ZCC. But, it can't on unmanaged device through browser local. "DNS_PROBE_FINISHED_NXDOMAIN" is shown instead. I wanna how the local web site can be accessed via BA. BA with public DNS can't resolve local domain, right? May I seek your advice?</p><p><br></p><p>Fred</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AdAcLKCQ0/zpa-enter-button-bug-redirect","lastmod":"2024-10-15T06:02:31.000Z","id":"0D54u0000AdAcLKCQ0"} -->
## ZPA "enter" button bug redirect

- Source: https://community.zscaler.com/s/question/0D54u0000AdAcLKCQ0/zpa-enter-button-bug-redirect
- Type: Q&A
- Posted: 2024-10-14T22:38:55.000Z
- Last activity: 2024-10-15T06:02:31.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>When a user login the Zscaler app on their computer, selects the passcode method, and after writing the code clicks enter redirects to the passcode method selection again, but if clicking the submit button works correctly, the "enter" button only fails in the last part of the login when the user writer the passcode.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AdAtRRCQ0/zpa-erremptyresponse-2-users-same-same-wifi","lastmod":"2024-10-19T05:07:50.000Z","id":"0D54u0000AdAtRRCQ0"} -->
## ZPA ERR_EMPTY_RESPONSE - 2 users same same Wi-Fi

- Source: https://community.zscaler.com/s/question/0D54u0000AdAtRRCQ0/zpa-erremptyresponse-2-users-same-same-wifi
- Type: Q&A
- Posted: 2024-10-15T12:01:39.000Z
- Last activity: 2024-10-19T05:07:50.000Z
- Answers: 9
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Experts,</p><p><br></p><p>Maybe you are my last resort, as been going back and forth with that, I am not an expert or anything but I have tired with that, at our company we are using ZPA 4.3.0.190 and the issue is that together me and my wife work for the same company and we get constant issue while using browser either in intranet or internet with error ERR_EMPTY_RESPONSE, my internet provided was also not helpful, claiming it works on our end and that's it... I used the analyzer and see as below, any ideas, comments, how to approach the issue? As both company IT and network provider are not helpful at all. </p><p>Thanks for any input</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000jVb5q?asPdf=false&amp;operationContext=CHATTER"></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Add6mQCQQ/zpa-user-activity-logs","lastmod":"2024-11-05T07:53:05.000Z","id":"0D54u0000Add6mQCQQ"} -->
## ZPA User Activity Logs

- Source: https://community.zscaler.com/s/question/0D54u0000Add6mQCQQ/zpa-user-activity-logs
- Type: Q&A
- Posted: 2024-10-30T17:45:17.000Z
- Last activity: 2024-11-05T07:53:05.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p>We’ve recently started transferring Zscaler ZPA User Activity logs to Datadog, and while reviewing the log schema, it mainly includes connection status between service edges and app connectors, total bytes transferred, and connection states (open/close/active). We are interested in understanding:</p><ol><li>How to identify whether a connection is blocked or allowed by the access policy in these logs, as we couldn’t find a field that directly indicates this.</li><li>How we can leverage these logs to identify potential threats and their overall usefulness.</li><li>Whether these logs are generated only for authorized connections or if they also cover unauthorized attempts.</li></ol>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AdsDPpCQM/zpa-block-notifications","lastmod":"2024-12-05T18:47:33.000Z","id":"0D54u0000AdsDPpCQM"} -->
## ZPA Block Notifications

- Source: https://community.zscaler.com/s/question/0D54u0000AdsDPpCQM/zpa-block-notifications
- Type: Q&A
- Posted: 2024-11-06T12:28:39.000Z
- Last activity: 2024-12-05T18:47:33.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Having difficulties wrapping up Zscaler Project with basic but irritating problem. ZPA User Block Notifications. We started by creating Allow All Policy at the bottom covering our Domains and /8 IP range. Then through Discovery covered all the Users/Technology (Admin) Access above. After covering 99% of our Systems we're wondering how to cover services like (Windows Update Optimization, BranchCache, NetBIOS protocols, etc.) all this being either broadcast or shared between machines and generating thousands of sessions every hour. After removing Allow All rule and leaving Explicit Block policy at the bottom all of the mentioned services are hitting Explicit Block and therefore spamming users with Block Notification. How can we cover these issues?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AeGRfZCQW/multiple-zpa-tenant","lastmod":"2024-11-18T18:21:58.000Z","id":"0D54u0000AeGRfZCQW"} -->
## Multiple ZPA tenant

- Source: https://community.zscaler.com/s/question/0D54u0000AeGRfZCQW/multiple-zpa-tenant
- Type: Q&A
- Posted: 2024-11-13T21:52:21.000Z
- Last activity: 2024-11-18T18:21:58.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Can we leverage two ZPA tenants for a single azure domain/tenant for IDP. I know that this is possible for ZIA clouds but not quite sure if this separation can be done on the same domain with a different enterprise app registration in azure to keep it separate ?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AeYCt5CQG/zpa-new-app-connector-rehl-integration-issuetcp-coonection-timed-out-to-zpaupdatesprodzpathnet","lastmod":"2024-11-28T05:48:40.000Z","id":"0D54u0000AeYCt5CQG"} -->
## ZPA New APP connector (REHL) Integration issue(TCP coonection timed out to zpa-updates.prod.zpath.net)

- Source: https://community.zscaler.com/s/question/0D54u0000AeYCt5CQG/zpa-new-app-connector-rehl-integration-issuetcp-coonection-timed-out-to-zpaupdatesprodzpathnet
- Type: Q&A
- Posted: 2024-11-26T10:22:00.000Z
- Last activity: 2024-11-28T05:48:40.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Coud anyone please help me we are encountering the new app connector integration issue with (TCP coonection timed out to zpa-updates.prod.zpath.net)</p><p><br></p><p>We have given full access to app connector IP in firewall still observed same issue</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AeZIsoCQG/zpa-loses-authentication-in-450344","lastmod":"2024-12-06T18:46:58.000Z","id":"0D54u0000AeZIsoCQG"} -->
## ZPA  loses authentication in  4.5.0.344

- Source: https://community.zscaler.com/s/question/0D54u0000AeZIsoCQG/zpa-loses-authentication-in-450344
- Type: Q&A
- Posted: 2024-11-28T12:37:33.000Z
- Last activity: 2024-12-06T18:46:58.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Guys,</p><p><br></p><p>we deployed ZCC Version 4.5.0.344 on Friday.</p><p>Since Monday alot of Users complain, that they need to authenticate everyday.</p><p>Our Timeout Policy is set to 7 Days and worked before normally.</p><p><br></p><p>Is there a known bug with 4.5.0.344 that explains those disconnects?</p><p><br></p><p>BR</p><p>Desync</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Af02joCQA/zpa-client-stuck-on-connecting","lastmod":"2025-08-22T07:27:59.000Z","id":"0D54u0000Af02joCQA"} -->
## ZPA client stuck on connecting

- Source: https://community.zscaler.com/s/question/0D54u0000Af02joCQA/zpa-client-stuck-on-connecting
- Type: Q&A
- Posted: 2024-12-06T11:00:27.000Z
- Last activity: 2025-08-22T07:27:59.000Z
- Answers: 21
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>12/12--> this morning, the issued gone fixed by itself for all the users impacted. No modification on our side. as of today 17/12 I am still wating for an official statement from zscaler support as something must have been changed on their infrastructure, there is no other explanations.</p><p><br></p><p>10/12--> I have installed the ZCC on my windows 10 home private device. Exactly the same issue and behavior, so it is not definetly something related to the company device posture. What do we have then in common to verify? ZIA ? pac files? what else?</p><p><br></p><p>Hi, starting monday the 2nd of december we are experiencing a strange issue. We have 1200 users, more or less 3-400 connecting every with zpa from home. We have 50 users more or less that are not able to connect to ZPA from home. The connection stay stuck on "connecting" without even going to the authentication phase.</p><p>The strange thing is changing from wifi home to hotspot works for almost the user, sometimes user need to try 3 or 4 different mobile hotstpot before it is working. Once authenticated successfully you can then switch back to home wifi without any issue. Then you reboot the device and the isssue is still there.</p><p>Already contacted the support , they insist is something on device side maybe inspecting the traffic but I tried to remove antivirus, to power off the windows defender firewall an nothing helped.</p><p>The other strange thing is , you can logout from ZCC , sign in again and you will be then able to authenticate but then the service is stuck on connecting and does not go on "ON".</p><p>Any idea?!?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Af3pBKCQY/can-we-use-single-app-connector-for-different-application-on-different-subnets","lastmod":"2024-12-09T13:25:26.000Z","id":"0D54u0000Af3pBKCQY"} -->
## Can we use single App Connector for Different Application on different Subnets?

- Source: https://community.zscaler.com/s/question/0D54u0000Af3pBKCQY/can-we-use-single-app-connector-for-different-application-on-different-subnets
- Type: Q&A
- Posted: 2024-12-09T05:30:49.000Z
- Last activity: 2024-12-09T13:25:26.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello!</p><p><br></p><p>I want to know whether we can use the same App connector for the multiple application residing in different Subnet?</p><p>OR we need to use different app connector for all the application residing on different subnets?</p><p><br></p><p>Also it is necessary to place app connector in the same subnet where the application is resided?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Af4qhGCQQ/how-many-additional-vm-servers-are-required-for-fully-deploying-zia-zpa-zdx-on-client-environment-with-all-the-modules-enabled","lastmod":"2025-04-05T03:00:57.000Z","id":"0D54u0000Af4qhGCQQ"} -->
## How many additional VM servers are required for fully deploying ZIA, ZPA, ZDX on client environment with all the modules enabled?

- Source: https://community.zscaler.com/s/question/0D54u0000Af4qhGCQQ/how-many-additional-vm-servers-are-required-for-fully-deploying-zia-zpa-zdx-on-client-environment-with-all-the-modules-enabled
- Type: Q&A
- Posted: 2024-12-09T11:23:38.000Z
- Last activity: 2025-04-05T03:00:57.000Z
- Answers: 5
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>How many additional VM servers are required for fully deploying ZIA, ZPA, ZDX on client environment with all the modules enabled?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AfAg8HCQS/sipa-for-remote-access","lastmod":"2024-12-17T02:22:21.000Z","id":"0D54u0000AfAg8HCQS"} -->
## SIPA for remote access

- Source: https://community.zscaler.com/s/question/0D54u0000AfAg8HCQS/sipa-for-remote-access
- Type: Q&A
- Posted: 2024-12-11T16:05:59.000Z
- Last activity: 2024-12-17T02:22:21.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have a server that we need to provide remote access to and usually, we'd use ZPA.&nbsp;However, could we use SIPA instead to provide access to that server?&nbsp;After all ZIA forwarding policies will send traffic to that server down the tunnel, do some security policy on it, and send it to an app connector which will resolve the DNS and send to the server.&nbsp;I appreciate that ZPA and SIPA are essentially the same but the licensing is different.&nbsp;We'd not get the two tunnels (both TLS and DTLS) and just have one tunnel.</p><p><br></p><p>Is there some limitation or other reason why SIPA cannot be used for remote access via ZIA?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AfsIETCQ2/ziazpa-test-tenant","lastmod":"2025-01-07T13:59:27.000Z","id":"0D54u0000AfsIETCQ2"} -->
## ZIA/ZPA Test Tenant

- Source: https://community.zscaler.com/s/question/0D54u0000AfsIETCQ2/ziazpa-test-tenant
- Type: Q&A
- Posted: 2025-01-02T01:58:38.000Z
- Last activity: 2025-01-07T13:59:27.000Z
- Answers: 3
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi guys, </p><p><br></p><p>Is there way to get test or trail tenant to understand and get hands on the ZIA or ZPA configuration?</p><p><br></p><p>How to apply to get trail tenant? </p><p><br></p><p>Thanks </p><p>Nav</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000RGObX0AX/experience-with-app-connector-on-containers","lastmod":"2026-07-10T09:57:33.000Z","id":"0D5PJ00000RGObX0AX"} -->
## Experience with App Connector on containers

- Source: https://community.zscaler.com/s/question/0D5PJ00000RGObX0AX/experience-with-app-connector-on-containers
- Type: Q&A
- Posted: 2025-01-21T07:29:24.000Z
- Last activity: 2026-07-10T09:57:33.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi all,</p><p><br></p><p>Wondering if any of you have experience with running app connectors on docker containers as we are considering adoption. Specifically the following topics:</p><ol><li>Are the app connectors as stable on docker containers as on VMs?</li><li>Any quirks/bugs/known issues to be aware of?</li></ol><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ROKRd0AP/show-ip-mapping-of-client-public-and-private-ip-and-server-public-and-private-ip-in-logs","lastmod":"2025-02-09T16:14:36.000Z","id":"0D5PJ00000ROKRd0AP"} -->
## Show IP mapping of client public and private IP and server public and private IP in logs

- Source: https://community.zscaler.com/s/question/0D5PJ00000ROKRd0AP/show-ip-mapping-of-client-public-and-private-ip-and-server-public-and-private-ip-in-logs
- Type: Q&A
- Posted: 2025-01-23T04:25:02.000Z
- Last activity: 2025-02-09T16:14:36.000Z
- Answers: 2
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>From a client, when a server is pinged by FQDN, the client does not see the real IP address of the server. Some 100.x.x.x IP or such is seen. When client with a private iP of, say, 192.168.5.5 and public IP of, say, 3.4.5.6 access - using client connector - a server whose private IP is, say, 10.21.21.10, it is very hard for us to do any forensics on the server side because all the logs on the server shows the IP of app connector.</p><p><br></p><p>When we go to ZScaler, there isn't enough information to definitively tell that client with private IP of 192.168.5.5 and public IP of 3.4.5.6 connected to 10.21.21.10. The app connector must know all of this information.</p><p><br></p><p><strong>Can you make logs available that will tell the public and private IP of a client and the public and private IP of the server it is connecting to and when?</strong></p><p><br></p><p>If we had a situation where a client was doing nefarious activities, we want to correlate logs. We want such logs to flow into our SIEM product to generate alerts. Right now, we are running blind. We did a penetration test and attempted to follow logs from different systems and ZPA appears to be the bottleneck in tracing down activities of a pen tester. All logs on servers just point to the app connector and after that it becomes so hard to find which client was making responsible for making the request.</p><p><br></p><p><strong>When we ping a server, can you just not show the IP of the server's FQDN instead of a fake IP?</strong></p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ROdif0AD/show-urls-a-user-is-accessing-through-zpa","lastmod":"2025-01-28T19:39:13.000Z","id":"0D5PJ00000ROdif0AD"} -->
## Show URLs a user is accessing through ZPA

- Source: https://community.zscaler.com/s/question/0D5PJ00000ROdif0AD/show-urls-a-user-is-accessing-through-zpa
- Type: Q&A
- Posted: 2025-01-23T04:15:17.000Z
- Last activity: 2025-01-28T19:39:13.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We had a use case where a server CPU spiked. The server could only tell me that HTTP requests were coming from ZPA app connectors. So, I went to ZPA app connector to find out who was sending massive amount of requests and I couldn't find that. We asked support and they asked us to put a feature reqeust in.</p><p><br></p><p><strong>Can you please expand your logging to show HTTP/HTTPS requests each client is making?</strong></p><p><br></p><p>Client connector must know what resources each client is attempt to access, using which the app connector creates a microtunnel with the right server. It'd be great if you can log that and allow customers to download the log to debug issues. Such logging can be used for forensics as well in the case that a client is compromised and is sending malicious HTTP requests.</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000RPQxm0AH/how-to-force-off-trusted-user-to-connect-private-zpa-pse-and-never-to-connect-to-public-zpa","lastmod":"2025-01-24T05:04:11.000Z","id":"0D5PJ00000RPQxm0AH"} -->
## How to force off trusted user to connect private ZPA PSE and never to connect to public ZPA

- Source: https://community.zscaler.com/s/question/0D5PJ00000RPQxm0AH/how-to-force-off-trusted-user-to-connect-private-zpa-pse-and-never-to-connect-to-public-zpa
- Type: Q&A
- Posted: 2025-01-23T12:18:10.000Z
- Last activity: 2025-01-24T05:04:11.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Team,</p><p><br></p><p>what is that best option we can force off trusted network user to connect to private PSE and never connect to public ZPA other then the proximity option.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000RiOdW0AV/zpa-scim-attribute","lastmod":"2025-02-10T07:13:26.000Z","id":"0D5PJ00000RiOdW0AV"} -->
## ZPA SCIM Attribute

- Source: https://community.zscaler.com/s/question/0D5PJ00000RiOdW0AV/zpa-scim-attribute
- Type: Q&A
- Posted: 2025-01-28T18:45:04.000Z
- Last activity: 2025-02-10T07:13:26.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p><br></p><p>I wanted to understand why Group attribute is not required in Azure AD when we are integrating Azure AD with Zscaler. </p><p>In below guide also Zscaler team only suggested to use Group attribute in ZPA but not in ZIA.</p><p><a href="https://help.zscaler.com/zscaler-technology-partners/zscaler-and-azure-identity-deployment-guide" rel="noopener noreferrer" target="_blank">Zscaler and Azure Identity Deployment Guide | Zscaler</a></p><p><br></p><p>Can anyone please help me to understand the logic behind this??</p><p><br></p><p>Thanks </p><p>Amandeep </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000RkW0p0AF/user-not-connecting-to-private-zpa-pse-they-are-getting-connect-to-public-zpa","lastmod":"2025-02-13T09:27:29.000Z","id":"0D5PJ00000RkW0p0AF"} -->
## User not connecting to Private ZPA PSE, they are getting connect to public ZPA

- Source: https://community.zscaler.com/s/question/0D5PJ00000RkW0p0AF/user-not-connecting-to-private-zpa-pse-they-are-getting-connect-to-public-zpa
- Type: Q&A
- Posted: 2025-01-29T08:27:56.000Z
- Last activity: 2025-02-13T09:27:29.000Z
- Answers: 10
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Team,</p><p><br></p><p>We have Private ZPA PSE instal and it show online on ZPA portal and APP coonector are also showing online. But end user are not getting connect to Prvate ZPA PSE, it is taking only Public ZPA.Can you help with some tshoot step to check why user are not getting coonect to Private ZPA</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000RuRYG0A3/browser-access-wildcard-certificate","lastmod":"2025-08-22T07:28:01.000Z","id":"0D5PJ00000RuRYG0A3"} -->
## Browser Access - wildcard certificate?

- Source: https://community.zscaler.com/s/question/0D5PJ00000RuRYG0A3/browser-access-wildcard-certificate
- Type: Q&A
- Posted: 2025-01-31T11:29:21.000Z
- Last activity: 2025-08-22T07:28:01.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>For folks who are leveraging a lot of Browser Access applications, are you all leveraging a wildcard ssl certificates to support multiple applications?</p><p><br></p><p>We are discussing a larger deployment of Browser Access applications, and I'm trying to determine the best approach to managing 30+ applications without dealing with some sort of certificate nightmare.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000SBMoK0AX/app-connector-deploy-in-dr-should-be-in-same-app-coonector-group-of-main-dc-or-separate","lastmod":"2025-02-13T09:25:16.000Z","id":"0D5PJ00000SBMoK0AX"} -->
## App connector deploy in DR should be in same app coonector group of main DC or separate

- Source: https://community.zscaler.com/s/question/0D5PJ00000SBMoK0AX/app-connector-deploy-in-dr-should-be-in-same-app-coonector-group-of-main-dc-or-separate
- Type: Q&A
- Posted: 2025-02-05T08:23:42.000Z
- Last activity: 2025-02-13T09:25:16.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi ,</p><p><br></p><p>We have client who has one app coonector in DC and wants another app coonector in DR site.So what should be the best practise to deplay this app coonector?</p><p> Can this app coonector in two diffrent location in same city can be group in same appcoongroup or has to deploy in diffrent appgroup ? Can both this ap coonector in diffrention laction work in cluster ?</p><p><br></p><p>Or Customer should have 2 xAPpcoon in one DC in cluster and one in DR ?</p><p><br></p><p>Or customer can have 1xApp in DC and 1X DR to work in cluster in same appConnector group ?</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000SIHWB0A5/3rd-party-business-partner-recently-deployed-zia-and-zpa","lastmod":"2025-02-10T15:07:29.000Z","id":"0D5PJ00000SIHWB0A5"} -->
## 3rd Party Business Partner Recently Deployed ZIA and ZPA

- Source: https://community.zscaler.com/s/question/0D5PJ00000SIHWB0A5/3rd-party-business-partner-recently-deployed-zia-and-zpa
- Type: Q&A
- Posted: 2025-02-06T16:56:50.000Z
- Last activity: 2025-02-10T15:07:29.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have a 3rd party vendor that accesses our environment through an SSL-VPN (Layer7) and they recently deployed ZIA and ZPA to their workstations changing the way they connect to our environments.</p><p><br></p><p>For some reason the 3rd party vendor blamed the connectivity issue on our side and I had to connect an troubleshoot and noticed that they were sending out Internal Network requests to Zscaler Service Edge, failing, and then attempting to identify our Internal Resources on their Internal Network.</p><p><br></p><p>I have shared with them the configurations they need to put in place and showed them how to analyze the issue and see that it is due to how they are handling traffic, but I am getting little to no response from their team and my request for escalation to their IT/Zscaler Admins.</p><p><br></p><p>I have also raised a request to our Zscaler Customer Success Team to see if they could reach out to the 3rd parties Customer Success Team but have yet to receive a response. No hit to my Success Team, they are just currently in other meetings.</p><p><br></p><p>Can someone reach out to me directly and help get a bridge request to the Vendors Customer Success Team for faster resolution?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000SUpuC0AT/regex-zpa-application-segments","lastmod":"2025-02-13T08:44:08.000Z","id":"0D5PJ00000SUpuC0AT"} -->
## Regex ZPA Application Segments

- Source: https://community.zscaler.com/s/question/0D5PJ00000SUpuC0AT/regex-zpa-application-segments
- Type: Q&A
- Posted: 2025-02-10T18:21:38.000Z
- Last activity: 2025-02-13T08:44:08.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>A few Zenith Lives ago there was an announcement that regex were going to be allowed to define Application Segments. </p><p><br></p><p>Has there been any update on this?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000Sg8Jb0AJ/app-connector-ova-file-having-error-while-installtion","lastmod":"2025-08-22T07:28:02.000Z","id":"0D5PJ00000Sg8Jb0AJ"} -->
## APP Connector OVA file having error while installtion

- Source: https://community.zscaler.com/s/question/0D5PJ00000Sg8Jb0AJ/app-connector-ova-file-having-error-while-installtion
- Type: Q&A
- Posted: 2025-02-13T09:50:21.000Z
- Last activity: 2025-08-22T07:28:02.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi team,</p><p><br></p><p>When using ovf package to deploy app connector I am getting below error</p><p><br></p><p>Cause: </p><p>a specific parameter was not correct</p><p>the checksum from the provided manifest file do not match the content of files</p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000SsBhh0AF/zscaler-zia-and-zpa-idp-migration","lastmod":"2025-02-18T16:55:20.000Z","id":"0D5PJ00000SsBhh0AF"} -->
## Zscaler ZIA and ZPA IdP migration

- Source: https://community.zscaler.com/s/question/0D5PJ00000SsBhh0AF/zscaler-zia-and-zpa-idp-migration
- Type: Q&A
- Posted: 2025-02-17T15:41:04.000Z
- Last activity: 2025-02-18T16:55:20.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hey Guys,</p><p>We are planning to migrate existing Google authentication IdP to Azure AD. Currently we haven't deployed SCIM Provisioning. However we want to deploy SCIM provisioning with new Azure IdP. I am expecting a proper guidance on how to migrate IdP successfully. </p><p>Thank you</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000T0D5N0AV/bypassforwarding-optionspolicies-on-zscaler","lastmod":"2025-03-01T07:39:31.000Z","id":"0D5PJ00000T0D5N0AV"} -->
## Bypass/Forwarding Options/Policies on Zscaler

- Source: https://community.zscaler.com/s/question/0D5PJ00000T0D5N0AV/bypassforwarding-optionspolicies-on-zscaler
- Type: Q&A
- Posted: 2025-02-19T15:23:10.000Z
- Last activity: 2025-03-01T07:39:31.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>In our enviroment, ZPA (Packet Filter Based Mode) runs on our clients in order to connect to the global corporate network. To clarify it, we do not manage ourselves the Zscaler.&nbsp;</p><p>So, I am not familiar with Zscaler Mgmt GUI. It is managed by other independent Team.</p><p>But I would like to ask which possibilities could offer Zscaler in our use case.</p><p><br></p><p>From our side we want to reach some destinations/servers which are located in isolated enviroment within the global corporate network, not over Zscaler but over other VPN Solution.</p><p>Let's say a traditional VPN Remote Access Solution which builds a virtual adapter on the client and assigns an IP from the VPN IP Pool.&nbsp;</p><p>So, we are talking about Split-Tunneling case where probably the intended traffic to the desired server Y.Y.Y.Y has to be bypassed on Zscaler so that it can be forwarded to the other VPN remote access client.</p><p><br></p><p>I am aware that generally it is possible to bypass traffic to specific Server from Zscaler tunnel, but we do not want to have a global impact to all clients/users.</p><p>It's should be more user-based.</p><p><br></p><p>https://help.zscaler.com/zpa/configuring-bypass-settings</p><p><br></p><p>Based on the documentation above the key elements are: Client Forwarding Policies, application segment etc.</p><p>As I known, for User Identification/Auth Zscaler works with Azure Entra-ID.&nbsp;</p><p>Is it possible to build a bypass policy/policies for a single user or for a group of users, so that they could reach the server Y.Y.Y.Y over an other way and not via Zscaler?</p><p><br></p><p>It should be like this:</p><p><br></p><p>Source: User-ID or Group of User&nbsp;=> Destination: Y.Y.Y.Y&nbsp; Action: Bypass</p><p><br></p><p>It would be ideal if the dest_port/service could be also filtered in the above theoretical policy.</p><p><br></p><p>Does it sound possible impelementation on Zscaler? Does anyone have any similar experience?</p><p><br></p><p>Thank you in advance.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000TskXA0AZ/cannot-connect-to-mapped-drive-with-zscaler-turned-on","lastmod":"2025-05-07T13:55:38.000Z","id":"0D5PJ00000TskXA0AZ"} -->
## Cannot Connect to Mapped drive with ZScaler turned on

- Source: https://community.zscaler.com/s/question/0D5PJ00000TskXA0AZ/cannot-connect-to-mapped-drive-with-zscaler-turned-on
- Type: Q&A
- Posted: 2025-03-06T14:55:52.000Z
- Last activity: 2025-05-07T13:55:38.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>So this is a strange issue facing users at work. A few users have a mapped network drive on their laptop, the drive is a Synology NAS on the network (no Firewall to it at present). So when ZScaler Private Access is turned on and the user enters the credentials to log on to the mapped drive they get a message saying 'The specified network password is incorrect'.</p><p><br></p><p>With ZPA turned off it works. It seems like the username is recognised but not the password. Anyone else come across this or similar issue?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000UCFV90AP/should-we-need-to-add-synthetic-ip-range-in-the-destination-exclusion-list-when-we-use-zpa-and-zia-and-both-are-in-tunnel-mode-","lastmod":"2025-03-13T03:39:35.000Z","id":"0D5PJ00000UCFV90AP"} -->
## Should we need to add synthetic ip range in the destination exclusion list when we use ZPA and ZIA and both are in tunnel mode ?

- Source: https://community.zscaler.com/s/question/0D5PJ00000UCFV90AP/should-we-need-to-add-synthetic-ip-range-in-the-destination-exclusion-list-when-we-use-zpa-and-zia-and-both-are-in-tunnel-mode-
- Type: Q&A
- Posted: 2025-03-12T11:11:36.000Z
- Last activity: 2025-03-13T03:39:35.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I would like to understand if we need to add synthetic IP range in the destination exclusion list used for tunnel 2.0 when we use both ZPA and ZIA. </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000UJTTJ0A5/international-zpa-access-to-domestic-hosted-apps","lastmod":"2025-03-19T13:20:23.000Z","id":"0D5PJ00000UJTTJ0A5"} -->
## International ZPA access to domestic hosted apps

- Source: https://community.zscaler.com/s/question/0D5PJ00000UJTTJ0A5/international-zpa-access-to-domestic-hosted-apps
- Type: Q&A
- Posted: 2025-03-13T21:54:27.000Z
- Last activity: 2025-03-19T13:20:23.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Would like to hear about what methods are used with regard to use of ZPA internationally. Yes, there are company policies. However, are there other access policy considerations, entitlements, machine tunnel exceptions or even policies to exclude access from certain country codes that being applied for a good practice.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000VBA7A0AX/app-connector-os-upgrade","lastmod":"2025-08-22T07:28:08.000Z","id":"0D5PJ00000VBA7A0AX"} -->
## App Connector OS Upgrade

- Source: https://community.zscaler.com/s/question/0D5PJ00000VBA7A0AX/app-connector-os-upgrade
- Type: Q&A
- Posted: 2025-03-30T12:04:57.000Z
- Last activity: 2025-08-22T07:28:08.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Do we need to update the RHEL OS and libraries of app connector post installation?</p><p>Is there any set of libraries that needs to be updated</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000VIJfP0AX/dns-search-domains-appended-to-fqdn","lastmod":"2025-04-04T15:20:58.000Z","id":"0D5PJ00000VIJfP0AX"} -->
## DNS Search Domains Appended to FQDN

- Source: https://community.zscaler.com/s/question/0D5PJ00000VIJfP0AX/dns-search-domains-appended-to-fqdn
- Type: Q&A
- Posted: 2025-04-01T10:34:09.000Z
- Last activity: 2025-04-04T15:20:58.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Dear Community, </p><p><br></p><p>we have several search domains in our ZPA Tenant configured, and as far as I read in the documentation from Zscaler these search domains should be appended to the shortnames in the client side. For example if user do the following; </p><p>shortname: server1</p><p>search domains in ZPA portal: xyz.com, abc.com </p><p>The results should be something like this : server1. xyz.com and server1.abc.com</p><p><br></p><p>But what I see in the client connector logs, that when the application I have tries to connect FQDN directly (e.g. server1. xyz.com ), I am still seeing that the it tries to add other domain to the FQDN and gives me something like this: server1. xyz.com.abc.com</p><p><br></p><p>Does anyone have any ideas</p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000VOmCN0A1/mr","lastmod":"2025-04-07T13:58:32.000Z","id":"0D5PJ00000VOmCN0A1"} -->
## Mr

- Source: https://community.zscaler.com/s/question/0D5PJ00000VOmCN0A1/mr
- Type: Q&A
- Posted: 2025-04-02T21:45:57.000Z
- Last activity: 2025-04-07T13:58:32.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I have this weird scenario and it's driving me crazy. Maybe there is someone here who can help. I have a customer testing ZPA but his ZPA traffic keeps going to a Zscaler broker which is in Mumbai and the latency is very high but cannot be forwarded to a PoP in Johannesburg closest to the application, its literally 20km away. We tried ZPA Private Service Edge, but the situation is the same.</p><p><br></p><p>I can appreciate any guidance here. </p><p><br></p><p>Thanks in advance for your help</p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000VQ0OB0A1/zscaler-client-forwarding-logs","lastmod":"2025-05-16T00:57:45.000Z","id":"0D5PJ00000VQ0OB0A1"} -->
## Zscaler client Forwarding logs

- Source: https://community.zscaler.com/s/question/0D5PJ00000VQ0OB0A1/zscaler-client-forwarding-logs
- Type: Q&A
- Posted: 2025-04-03T07:12:38.000Z
- Last activity: 2025-05-16T00:57:45.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have a ZPA setup. I have been trying to find ZPA client forwarding logs to figure out the client forwarding policy my traffic is matching</p><p><br></p><p>Can someone please help find this info</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000VryD50AJ/zpa-deployment-issue","lastmod":"2025-04-15T07:25:43.000Z","id":"0D5PJ00000VryD50AJ"} -->
## ZPA Deployment Issue

- Source: https://community.zscaler.com/s/question/0D5PJ00000VryD50AJ/zpa-deployment-issue
- Type: Q&A
- Posted: 2025-04-10T21:24:57.000Z
- Last activity: 2025-04-15T07:25:43.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We currently deploying ZPA but have an issue with Windows clients (macOS and iOS work fine). It seems routes are not being injected (100.64) and not sure where to look. No Windows firewall issues and A/V exclusions are configured. Not sure where to go from here?</p><p><br></p><p>Any help is appreciated.</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000W7WYq0AN/zpa-se-session-closed-by-service-edge","lastmod":"2025-04-17T07:06:33.000Z","id":"0D5PJ00000W7WYq0AN"} -->
## ZPA SE: Session closed by Service Edge

- Source: https://community.zscaler.com/s/question/0D5PJ00000W7WYq0AN/zpa-se-session-closed-by-service-edge
- Type: Q&A
- Posted: 2025-04-16T00:56:16.000Z
- Last activity: 2025-04-17T07:06:33.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Based on the diagnostics, the Action is Allow, but in status code SE: Session closed by Service Edge.<u> What does it mean?</u></p><p><br></p><p>Also the website is inaccessible by that time but it is now working out of nowhere.</p><p><br></p><p>Can anyone explain? </p><p><br></p><p>It is a website. The TCP Keep alive in the app segment is already enabled.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000WnHKj0AN/packet-loss-to-default-gateway-when-using-ziazpa-on-wifi-on-mac","lastmod":"2025-05-21T21:16:18.000Z","id":"0D5PJ00000WnHKj0AN"} -->
## Packet Loss to Default Gateway When Using ZIA+ZPA on Wi-Fi on MAC

- Source: https://community.zscaler.com/s/question/0D5PJ00000WnHKj0AN/packet-loss-to-default-gateway-when-using-ziazpa-on-wifi-on-mac
- Type: Q&A
- Posted: 2025-04-29T09:06:01.000Z
- Last activity: 2025-05-21T21:16:18.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I'm experiencing packet loss to the default gateway when connected to Wi-Fi while using Zscaler ZIA+ZPA. The issue does <strong>not</strong> occur when using a wired connection. Disabling the Zscaler Client Connector resolves the problem on Wi-Fi.</p><p>Has anyone encountered that?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000WrSwr0AF/zpa-redirection-policy-not-redirecting-all-clients","lastmod":"2025-05-02T12:24:07.000Z","id":"0D5PJ00000WrSwr0AF"} -->
## ZPA Redirection Policy, not redirecting all clients

- Source: https://community.zscaler.com/s/question/0D5PJ00000WrSwr0AF/zpa-redirection-policy-not-redirecting-all-clients
- Type: Q&A
- Posted: 2025-04-30T08:06:09.000Z
- Last activity: 2025-05-02T12:24:07.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I have set up some ZPA private Service edge services as containers, running on an Arm64 server. It appears to run very well, performance wise and with our particular network, it allows us to engineer the traffic flows for both performance and cost. </p><p><br></p><p>I have configured a redirection policy, so all our traffic should be using the Private Service Edges, unless they are unavaible. However i'm finding that some of the clietns are making connections to the Public Service Edges, even when the private ones are avaiable. </p><p><br></p><p>What could be causing this behavior?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000WrU2Q0AV/zia-private-service-edge-deployment-options","lastmod":"2025-07-10T10:32:06.000Z","id":"0D5PJ00000WrU2Q0AV"} -->
## ZIA Private Service Edge Deployment Options.

- Source: https://community.zscaler.com/s/question/0D5PJ00000WrU2Q0AV/zia-private-service-edge-deployment-options
- Type: Q&A
- Posted: 2025-04-30T08:12:34.000Z
- Last activity: 2025-07-10T10:32:06.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi, I want to be able to deploy ZIA Private Service Edge on a couple of servers, so that i can engineer my traffic flows better for both cost and performance. I have been been able to do this for ZPA, and had excellent results. With ZPA i was able to deploy the servers as docker containers on ARM64. They run exceptionally well. </p><p><br></p><p>I had assumed ( badly its seems ) that would be able to get a Arm64 Docker container image for ZPA. but i cant' find one. As an alternative I cna deploy EC2 but it has to be on Arm64 processors. </p><p><br></p><p>It seems that the only thing i can find right now, is EC2 on X86.. If the worst occurs, i'll ahve to order a x86 server to run it on..</p><p><br></p><p><br></p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000X641f0AB/wifi-issue-with-zpa","lastmod":"2025-07-09T14:05:34.000Z","id":"0D5PJ00000X641f0AB"} -->
## wifi issue with zpa

- Source: https://community.zscaler.com/s/question/0D5PJ00000X641f0AB/wifi-issue-with-zpa
- Type: Q&A
- Posted: 2025-05-05T06:17:24.000Z
- Last activity: 2025-07-09T14:05:34.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>when ZPA is enabled, not able to switch wi-fi network , but able to connect mobile network&nbsp;</p><p>once ZPA disabled, able to switch to another home wi-fi network.&nbsp;</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000XY3fE0AT/azure-private-endpoints","lastmod":"2025-05-18T19:19:38.000Z","id":"0D5PJ00000XY3fE0AT"} -->
## Azure Private Endpoints

- Source: https://community.zscaler.com/s/question/0D5PJ00000XY3fE0AT/azure-private-endpoints
- Type: Q&A
- Posted: 2025-05-12T23:02:55.000Z
- Last activity: 2025-05-18T19:19:38.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>How is everyone handling Azure Private Endpoints with ZPA Forwarding? For example, we are creating Application Segments for *.blob.core.windows.net to perform lookup to determine if privatelink.blob.core.windows.net exists. Of course, this is sending both private and public endpoint traffic across our ZPA infrastructure.</p><p><br></p><p><a href="https://learn.microsoft.com/en-us/azure/private-link/private-endpoint-dns-integration" rel="noopener noreferrer" target="_blank">Azure Private Endpoint DNS integration | Microsoft Learn</a></p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000Xy7tQ0AR/fedex-system-for-odbc-connection","lastmod":"2025-05-22T22:53:24.000Z","id":"0D5PJ00000Xy7tQ0AR"} -->
## FedEx System for ODBC Connection

- Source: https://community.zscaler.com/s/question/0D5PJ00000Xy7tQ0AR/fedex-system-for-odbc-connection
- Type: Q&A
- Posted: 2025-05-19T21:20:47.000Z
- Last activity: 2025-05-22T22:53:24.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We created an app segment and a test access policy for the ODBC connection, and it worked with the FedEx system and software. But whenever we applied it globally, like to all app connectors, it didn't work. </p><p><br></p><p>No logs have been generated when the issue occurs from ZPA and ZIA related to FedEx.</p><p><br></p><p>The error message from the end user says, "Error: FedEx Ship Manager has failed to connect to the FedEx systems. FedEx services have been suspended until the software can communicate properly."</p><p><br></p><p>Do you think I need to create an app segment for FedEx, or do I just need to adjust the ODBC app segment and add necessary ports or FQDNs related to FedEx? </p><ul><li>If so, would anyone provide the required details? I can't find an article that is helpful for this.</li></ul><p><br></p><p>Thanks.</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000Zomi70AB/could-not-connect-to-distprivatezscalercom-via-zpaupdatesprodzpathnet","lastmod":"2025-06-20T10:58:20.000Z","id":"0D5PJ00000Zomi70AB"} -->
## Could not connect to dist.private.zscaler.com via zpa-updates.prod.zpath.net

- Source: https://community.zscaler.com/s/question/0D5PJ00000Zomi70AB/could-not-connect-to-distprivatezscalercom-via-zpaupdatesprodzpathnet
- Type: Q&A
- Posted: 2025-06-20T06:53:23.000Z
- Last activity: 2025-06-20T10:58:20.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I am getting below error on Zscaler app connector </p><p><br></p><p><strong>Could not connect to dist.private.zscaler.com via zpa-updates.prod.zpath.net</strong></p><p><br></p><p><strong>Tried yum update but that's all not working. </strong></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000a18La0AI/zpa-apptoapp-access-instead-of-usertoapp","lastmod":"2025-06-30T09:52:10.000Z","id":"0D5PJ00000a18La0AI"} -->
## ZPA  app-to-app access instead of user-to-app

- Source: https://community.zscaler.com/s/question/0D5PJ00000a18La0AI/zpa-apptoapp-access-instead-of-usertoapp
- Type: Q&A
- Posted: 2025-06-24T16:39:08.000Z
- Last activity: 2025-06-30T09:52:10.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi all !</p><p><br></p><p>Is it possible to restrict access to a private app by allowing only a certain app running on the client device access to the private resource ? </p><p><br></p><p>So instead of just relying on user identity/authentication and device profile/posture, only a certain app running on this users device is allowed to access specific private resources, whether it's another app, database, sql server, etc. ? </p><p><br></p><p>Thanks in advance!</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000a4jMx0AI/zpa-with-active-directory","lastmod":"2025-07-08T17:22:17.000Z","id":"0D5PJ00000a4jMx0AI"} -->
## ZPA with Active Directory

- Source: https://community.zscaler.com/s/question/0D5PJ00000a4jMx0AI/zpa-with-active-directory
- Type: Q&A
- Posted: 2025-06-25T14:31:14.000Z
- Last activity: 2025-07-08T17:22:17.000Z
- Answers: 5
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi I recently deployed ZPA for our IT team and *.local-domain.biz and full access so it's working very nicely</p><p><br></p><p>Next I'm deploying to our external developers with domain joined windows laptops. What's the best practice here for the laptop <> domain controller comms ? Do we need to allow some services ? Stuff like password change, Kerberos auth etc.. </p><p><br></p><p><a href="https://community.zscaler.com/s/question/0D54u00009jZp4qCAC/zscaler-private-access-active-directory-enumeration" rel="noopener noreferrer" target="_blank">https://community.zscaler.com/s/question/0D54u00009jZp4qCAC/zscaler-private-access-active-directory-enumeration</a></p><p><br></p><p>Please advise </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000aZUOf0AO/unexpected-popup-application-access-is-blocked-by-private-access-policy","lastmod":"2025-07-04T04:47:32.000Z","id":"0D5PJ00000aZUOf0AO"} -->
## Unexpected pop-up: "Application access is blocked by private access policy"

- Source: https://community.zscaler.com/s/question/0D5PJ00000aZUOf0AO/unexpected-popup-application-access-is-blocked-by-private-access-policy
- Type: Q&A
- Posted: 2025-07-03T15:31:06.000Z
- Last activity: 2025-07-04T04:47:32.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We are experiencing an issue where a pop-up message is displayed with the following text:</p><p><strong>"Application access is blocked by private access policy"</strong></p><p>I understand that this message indicates that some traffic is being blocked by the private access policy. However, my policy is configured as follows:</p><p><strong>Block all access > Pop-Up Message to User > Disable</strong></p><p>In the ZCC panel, I also checked under <strong>Client Connector Notification</strong>, and all fields are set to <strong>Disable</strong>.</p><p>I would like to confirm if there is any other point I may have forgotten to disable. My client requests that this pop-up <strong>not be displayed</strong>, but I am having trouble preventing it from appearing.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000aqJef0AE/kerberos-preauthentication-failed-w-zpa","lastmod":"2025-11-04T23:53:26.000Z","id":"0D5PJ00000aqJef0AE"} -->
## Kerberos pre-authentication failed w/ ZPA

- Source: https://community.zscaler.com/s/question/0D5PJ00000aqJef0AE/kerberos-preauthentication-failed-w-zpa
- Type: Q&A
- Posted: 2025-07-09T04:29:51.000Z
- Last activity: 2025-11-04T23:53:26.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>In a recent ZPA deployment I have encountered an issue whereby Kerberos authentication requests via ZPA App Connector are getting the following error from Domain Controller. Client's are able to authenticate without issue with ZPA is not in play.</p><p><br></p><h1>4771(F): Kerberos pre-authentication failed.</h1><p>https://learn.microsoft.com/en-us/previous-versions/windows/it-pro/windows-10/security/threat-protection/auditing/event-4771#security-monitoring-recommendations.</p><p><br></p><p>Examining the PCAP from the AC we can see this Kerberos error being reflected with the..</p><p>KRB5KDC_ERR_PREAUTH_REQUIRED</p><p>KRB5KDC_ERR_PADATA_TYPE_NOSUPP</p><p>... errors (see below).</p><p><br></p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/068PJ00000N8z5t?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p>All Domain Controller App Segments have been configured directly in line with Mark Ryan' brilliant articles on this subject (<a href="https://community.zscaler.com/s/question/0D54u00009evlUWCAY/zscaler-private-access-active-directory" rel="noopener noreferrer" target="_blank">here</a>).</p><p><br></p><p>Has anyone encounter any similar issues when doing Kerberos auth through ZPA AC's?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000auhP30AI/allowing-users-to-addremove-fqdns-for-access-via-a-specific-zpa-app-segment","lastmod":"2025-07-14T02:43:05.000Z","id":"0D5PJ00000auhP30AI"} -->
## Allowing users to add/remove FQDNs for access via a specific ZPA App Segment

- Source: https://community.zscaler.com/s/question/0D5PJ00000auhP30AI/allowing-users-to-addremove-fqdns-for-access-via-a-specific-zpa-app-segment
- Type: Q&A
- Posted: 2025-07-10T04:28:59.000Z
- Last activity: 2025-07-14T02:43:05.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I got an AppSegment allowing access to specific FQDNs' for a specific group of users, the users accessing it are developers so they need to frequently add and remove FQDNs. Currently they have to request Zscaler support to make those changes which has certain lead times and overhead for support team, so we are exploring an option to allow these users to make changes themselves, e.g. on a CSV file or XML/JSON that can be then sync'd with AppSegment using automation. </p><p><br></p><p>Please share your experience if anyone has implemented such a solution or got any ideas worth testing.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000avEBS0A2/need-help-with-zscaler-private-service-edge","lastmod":"2025-07-15T13:58:41.000Z","id":"0D5PJ00000avEBS0A2"} -->
## Need Help with Zscaler Private Service Edge

- Source: https://community.zscaler.com/s/question/0D5PJ00000avEBS0A2/need-help-with-zscaler-private-service-edge
- Type: Q&A
- Posted: 2025-07-10T10:19:22.000Z
- Last activity: 2025-07-15T13:58:41.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Greeting Community,</p><p><br></p><p>Need your help regarding ZPA PSE,</p><p><br></p><p>I have deployed Zscaler PSE in my lab via DOCKER and when I define the trusted network criteria I am not able to get private IP of the broker in my ZCC in my win 11 Client Machine</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000bVzKH0A0/zscaler-learning-path-for-zscaler-private-access","lastmod":"2025-07-28T05:30:21.000Z","id":"0D5PJ00000bVzKH0A0"} -->
## Zscaler Learning Path for Zscaler Private Access

- Source: https://community.zscaler.com/s/question/0D5PJ00000bVzKH0A0/zscaler-learning-path-for-zscaler-private-access
- Type: Q&A
- Posted: 2025-07-21T07:29:35.000Z
- Last activity: 2025-07-28T05:30:21.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Dear All, </p><p>I am new to Zscaler. kindly suggest me a Learning path to Zscaler Private Access - Cloud Administrator </p><p><br></p><p>Thanks in Advance </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000boSkB0AU/a-single-server-group-vs-two-or-more-server-groups","lastmod":"2025-07-30T10:32:09.000Z","id":"0D5PJ00000boSkB0AU"} -->
## A single server group vs two or more server groups

- Source: https://community.zscaler.com/s/question/0D5PJ00000boSkB0AU/a-single-server-group-vs-two-or-more-server-groups
- Type: Q&A
- Posted: 2025-07-25T00:13:49.000Z
- Last activity: 2025-07-30T10:32:09.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>For example, we have 4 app connectors to an app segment.</p><p>Scenario 1</p><p>we assign two app connectors to a server group and assign remaining two to another server group and add these two server groups to the app segment. Now the app segment contains two server groups. How ZPA will work in this condition when client machine or ZPA user wants to access the app segment.</p><p>&nbsp;</p><p>Scenario 2</p><p>We assign all 4 app connectors to a single server group and assign the server group to the app segment. now the app segment has a single server group. How ZPA work in this condition when client machine or ZPA wants to access the app segment.</p><p>&nbsp;</p><p>Which one is the best for active directory related app segments and why ?</p><p>Which one is the best for normal applications related app segment and why ?</p><p>&nbsp;</p><p>Kindly help me on this.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000bxKwf0AE/zpa-onoff-trusted-network","lastmod":"2025-07-28T16:42:36.000Z","id":"0D5PJ00000bxKwf0AE"} -->
## ZPA  on/off trusted network

- Source: https://community.zscaler.com/s/question/0D5PJ00000bxKwf0AE/zpa-onoff-trusted-network
- Type: Q&A
- Posted: 2025-07-28T11:36:33.000Z
- Last activity: 2025-07-28T16:42:36.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi recently deployed ZPA for our users and all are good. All ZPA users are treated a off trusted network and no b/w issues yet. I have deployed 2 app connectors per site. What's the best way to tell ZCC if the users are in the office bypass ZPA and directly go to our on-pram app/service etc.. May be ping our local G/W and if yes treat as on trusted network ? We have windows and macs and users need to reboot the devices ? Also I have licences to 2 x PSE will this help ? Looking for some advise . Cheers</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000c62BE0AY/difference-between-sipa-and-zpa","lastmod":"2026-04-07T11:55:21.000Z","id":"0D5PJ00000c62BE0AY"} -->
## Difference between SIPA and ZPA

- Source: https://community.zscaler.com/s/question/0D5PJ00000c62BE0AY/difference-between-sipa-and-zpa
- Type: Q&A
- Posted: 2025-07-30T08:47:22.000Z
- Last activity: 2026-04-07T11:55:21.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><span style="font-size: 14px; font-family: Roboto, Arial, sans-serif;">Hi,</span></p><p><br></p><p><strong>Can someone please clarify — if we dont use SIPA and route traffic to internet-hosted applications via ZPA App Connector, will the traffic still egress with the App Connector's fixed public IP? I’m trying to understand whether SIPA is truly necessary in scenarios where ZPA is already deployed on the user system.</strong></p><p><strong>In our current setup, traffic flows from ZCC to ZPA, then through the App Connector to the destination internet server</strong></p><p><br></p><p><span style="font-size: 14px; font-family: Roboto, Arial, sans-serif;">Thanks</span></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000cCE9P0AW/ztna-app-connector-is-spawning-connections-so-the-maria-db","lastmod":"2025-08-07T06:41:43.000Z","id":"0D5PJ00000cCE9P0AW"} -->
## ZTNA app connector is spawning connections so the Maria DB

- Source: https://community.zscaler.com/s/question/0D5PJ00000cCE9P0AW/ztna-app-connector-is-spawning-connections-so-the-maria-db
- Type: Q&A
- Posted: 2025-07-31T15:39:14.000Z
- Last activity: 2025-08-07T06:41:43.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi I have a legacy client server app on ZPA for users. I have to include the FQDN &amp; IP on the app segment (With all ports opened except DNS) for this to work. The server side it uses Maria DB. ZTNA app connector is spawning connections so the Maria DB complains saying too many connections and lock the IP of the app connector so I have to manually run mysqladmin flush-hosts. Any ides how can we precent this please ? Before ZPA I was using the traditional VPN and had no issues. </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000cOWbA0AW/zpa-rhel9-app-connectors-on-hyperv","lastmod":"2026-02-05T13:18:13.000Z","id":"0D5PJ00000cOWbA0AW"} -->
## ZPA RHEL9 App Connectors on Hyper-V

- Source: https://community.zscaler.com/s/question/0D5PJ00000cOWbA0AW/zpa-rhel9-app-connectors-on-hyperv
- Type: Q&A
- Posted: 2025-08-04T19:38:38.000Z
- Last activity: 2026-02-05T13:18:13.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi all,</p><p><br></p><p>Due to the changes with ESXi, we have customers exploring migrations to Hyper-V</p><p><br></p><p>What are the supported configurations for Hyper-V using RHEL9 App connectors, if any.</p><p>What suggested alternatives could be used without deploying to Azure/AWS (customer servers are mostly on-prem).</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000cY1yS0AS/username-mismatched-please-try-again-error-zpa-partner-tenant","lastmod":"2025-08-14T17:33:11.000Z","id":"0D5PJ00000cY1yS0AS"} -->
## 'Username mismatched. Please try again' Error | ZPA Partner Tenant

- Source: https://community.zscaler.com/s/question/0D5PJ00000cY1yS0AS/username-mismatched-please-try-again-error-zpa-partner-tenant
- Type: Q&A
- Posted: 2025-08-06T18:19:07.000Z
- Last activity: 2025-08-14T17:33:11.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have a third service provider who has a Zscaler Tenant. My organization has another zscaler tenant. The third service provider has to access my Organization Internal resources. This the service provider can do by adding my organization tenant as partner tenant. Our organization has allowed access to connect from remote tenant on Zscaler Client Connector portal. Similarly third service provider has provided access to connect to any remote tenant. They are able to connect to a tenant of other organization.</p><p>However, we are facing issue when third service provider uses my organization id in partner tenant. We are receiving error '<strong>Username mismatched. Please try again</strong>'. </p><p>The user is able to login with same id if logged in as main tenant on same non-domain machine. On domain machine too with same user ID and password we are able to login as main id.</p><p><br></p><p>Let me know what we can do to resolve it?</p><p><br></p><p><strong>Any input would be appreciated</strong> as i am not able to find anything for above issue.</p><p>We have tried logging out other partner tenant ids too for the service provider user but still our tenant id could not be added.</p><p>We logged out the user from his main ID and logged in back to remove any cache kind of error, but still our organization id could not be used as tenant.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000dAx520AC/zpa-pra-question","lastmod":"2025-08-19T07:41:23.000Z","id":"0D5PJ00000dAx520AC"} -->
## ZPA PRA question

- Source: https://community.zscaler.com/s/question/0D5PJ00000dAx520AC/zpa-pra-question
- Type: Q&A
- Posted: 2025-08-18T14:14:34.000Z
- Last activity: 2025-08-19T07:41:23.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi I have deployed ZPA for our staff and all are good. Now I have a handful of 3rd parties / contractors needs RDP access to our VM(s)&nbsp;</p><p>I have 10 user basic PRA licences. This VM(s) will be accessed (RDP) by our dev staff as well as few 3rd parties</p><p><br></p><p>How can I do this ?</p><p><br></p><p>Normal RDP access to our dev team&nbsp;</p><p>at the same time secure bowser based RDP access to 3rd parties to the same VM(s)&nbsp;</p><p><br></p><p>I'm nor sure how to do this in the app segment(s) &amp; policies etc...&nbsp;</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000dCOhq0AG/need-zpa-tenant-in-beta-cloud-for-internal-testing","lastmod":"2025-08-25T14:18:04.000Z","id":"0D5PJ00000dCOhq0AG"} -->
## Need ZPA Tenant in Beta cloud for Internal testing

- Source: https://community.zscaler.com/s/question/0D5PJ00000dCOhq0AG/need-zpa-tenant-in-beta-cloud-for-internal-testing
- Type: Q&A
- Posted: 2025-08-18T17:02:42.000Z
- Last activity: 2025-08-25T14:18:04.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Team, </p><p><br></p><p>Need ZPA Tenant in Beta cloud for Internal testing with below domains to be added.</p><p>newbetatest1.com</p><p>newbetatest2.com</p><p>newbetatest3.com</p><p><br></p><p>Cheers,</p><p>Chenna.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000dF09N0AS/zpa-your-access-to-private-application-have-expired","lastmod":"2025-08-19T12:18:27.000Z","id":"0D5PJ00000dF09N0AS"} -->
## ZPA your access to private application have expired

- Source: https://community.zscaler.com/s/question/0D5PJ00000dF09N0AS/zpa-your-access-to-private-application-have-expired
- Type: Q&A
- Posted: 2025-08-19T07:46:17.000Z
- Last activity: 2025-08-19T12:18:27.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi I do get this with users and most are just needs to re-authenticate but few users needs to log off from ZCC (Via the desktop support staff) and log back on. Any idea why this happens ? May be a caching issues with the browser ? We uses Entra ID as iDP. This is not a major issues</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ddKVF0A2/short-name-on-zpa-on-macs-dont-work","lastmod":"2025-09-01T09:17:26.000Z","id":"0D5PJ00000ddKVF0A2"} -->
## Short name on ZPA on Macs don't work

- Source: https://community.zscaler.com/s/question/0D5PJ00000ddKVF0A2/short-name-on-zpa-on-macs-dont-work
- Type: Q&A
- Posted: 2025-08-26T09:00:27.000Z
- Last activity: 2025-09-01T09:17:26.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi I recentely moved our clients from FortiClient to ZPA and it's huge success. For all Windows clints we can access all internal resources without FQDN but for Mac clients they need to use the FQDN while on ZPA/WFH. Not a major issue but nice to fix this issue - Any idea how can we do this ? (When the Mac clints are in the office &amp; ZPA will auto disable &amp; no need to use FQDN) </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000donkF0AQ/keyboard-for-zpa-pra","lastmod":"2025-09-01T09:20:58.000Z","id":"0D5PJ00000donkF0AQ"} -->
## Keyboard for ZPA PRA

- Source: https://community.zscaler.com/s/question/0D5PJ00000donkF0AQ/keyboard-for-zpa-pra
- Type: Q&A
- Posted: 2025-08-28T17:03:11.000Z
- Last activity: 2025-09-01T09:20:58.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><span style="font-size: 14px; font-family: Inter;">I'm deploying PRA for our 3rd party clients &amp; some of the symbol keys are adrift - for example I type \ and get # on screen, pipe (|) generates ~, # generates £ etc etc. I can't find a key that generates a backslash (\) at all</span></p><p><span style="font-size: 14px; font-family: Inter;">Can you help pls</span></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000eB1hA0AS/windows-autopilot-with-ziazpa","lastmod":"2025-09-15T03:55:20.000Z","id":"0D5PJ00000eB1hA0AS"} -->
## Windows Autopilot with ZIA/ZPA

- Source: https://community.zscaler.com/s/question/0D5PJ00000eB1hA0AS/windows-autopilot-with-ziazpa
- Type: Q&A
- Posted: 2025-09-04T14:34:32.000Z
- Last activity: 2025-09-15T03:55:20.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi we are planning to test Windows Auto Pilot deployment anything I need to know or do ? Appreciate any feedback </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000eTwRr0AK/split-horizon-dns","lastmod":"2025-09-15T05:15:46.000Z","id":"0D5PJ00000eTwRr0AK"} -->
## Split Horizon DNS

- Source: https://community.zscaler.com/s/question/0D5PJ00000eTwRr0AK/split-horizon-dns
- Type: Q&A
- Posted: 2025-09-09T23:20:37.000Z
- Last activity: 2025-09-15T05:15:46.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have *.example.com in ZPA as an app segment to forward all traffic for the entire domain (including subdomains *) to ZPA as we think everything for it is all private but we have many in that, for example, support.example.com, dev.example.com, uat.example.com and many more which has internal as well as public facing resolution and its too hard to find all that individually. Even if we find all individuals, how can we make sure which dns resolution users are requesting, private or public?? And how can we efficiently route that traffic?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000eutjL0AQ/app-connector-connection-issue-to-pse","lastmod":"2025-09-23T03:06:11.000Z","id":"0D5PJ00000eutjL0AQ"} -->
## App connector connection issue to PSE

- Source: https://community.zscaler.com/s/question/0D5PJ00000eutjL0AQ/app-connector-connection-issue-to-pse
- Type: Q&A
- Posted: 2025-09-17T08:20:47.000Z
- Last activity: 2025-09-23T03:06:11.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi everyone,</p><p><br></p><p>We face the issue of connectivity from the App connector&nbsp;to Private Service Edge frequently when there is no high utilization on both the network and the VMs, and it causes high latency.</p><p>During the issue, the following log is generated at the App connector:</p><p>Kick flow control to connection [PSE_local_IP]:37480;216199427017146580.pb.private.zscaler.com:[PSE_public_IP]:443;0, as it is blocked for more than 1 second.</p><p>After restarting the zpa-connector service, it goes back to normal.</p><p>Has anyone faced the same issue? Any solution for this?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000fhTPx0AM/telnet-success-but-access-application-using-browser-not-working","lastmod":"2025-10-08T04:59:47.000Z","id":"0D5PJ00000fhTPx0AM"} -->
## Telnet Success but Access Application using browser not working

- Source: https://community.zscaler.com/s/question/0D5PJ00000fhTPx0AM/telnet-success-but-access-application-using-browser-not-working
- Type: Q&A
- Posted: 2025-10-01T05:12:55.000Z
- Last activity: 2025-10-08T04:59:47.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>ZPA configured to access internal application.</p><p>Few Application which is working over https(443) using IP address, as it's internal application no certificate installed. Hence when accessing certificate error will come.</p><p><br></p><p>Scenario: ZPA Is Connected , able telnet to the IP using port 443, but when accessing over web browser it not working. Error "check DNS or Porxy"</p><p>In logs it shows App connector does not find any application or application not reachable.</p><p><br></p><p>My question how telnet is successfull when application not reachable via App connector in the connection logs.</p><p>What could be the possible solution ?</p><p>Do we have any ZPA ssl inspection bypass ?</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000hAZgl0AG/app-connector-disconnected-after-a-failed-sarge-update","lastmod":"2025-10-21T06:01:51.000Z","id":"0D5PJ00000hAZgl0AG"} -->
## App Connector disconnected after a failed SARGE Update

- Source: https://community.zscaler.com/s/question/0D5PJ00000hAZgl0AG/app-connector-disconnected-after-a-failed-sarge-update
- Type: Q&A
- Posted: 2025-10-20T02:28:30.000Z
- Last activity: 2025-10-21T06:01:51.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I've always encounter this issue with AppConnector disconnected after a failed SARGE update. </p><p>What is the easy way to fix this? And prevent it from happening in the future? </p><p><br></p><p> "currentSargeVersion":"25.45.1",</p><p> "expectedSargeVersion":"25.48.2",</p><p> "message":"App Connector running outdated sarge version"</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000hn24k0AA/unable-to-connect-with-private-links-with-zpa-while-connected-with-office-wifi","lastmod":"2025-10-28T19:16:38.000Z","id":"0D5PJ00000hn24k0AA"} -->
## Unable to connect with Private links with ZPA while connected with office wi-fi

- Source: https://community.zscaler.com/s/question/0D5PJ00000hn24k0AA/unable-to-connect-with-private-links-with-zpa-while-connected-with-office-wifi
- Type: Q&A
- Posted: 2025-10-27T07:51:55.000Z
- Last activity: 2025-10-28T19:16:38.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We've some sites that are accessible only when you're connected with vpn.It doesn't work as it's suppose to be on office wi-fi.I got in touch with ISP there and this is what they said "As discussed during our meeting with you and your team, please coordinate with Zscaler and arrange a meeting to address the issue.</p><p>During my observation, when connecting to Zscaler, I noticed packet drops when pinging the Zscaler IP, while there was no packet loss when pinging the global DNS. This indicates a potential issue on Zscaler's end.</p><p>Kindly log a complaint with Zscaler and ensure the issue is resolved at the earliest." Any ideas would be great.Thanks.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000hzZrc0AE/unable-to-configure-zpa-pse-behind-f5-vip","lastmod":"2025-11-07T00:17:28.000Z","id":"0D5PJ00000hzZrc0AE"} -->
## Unable to configure ZPA PSE behind F5 VIP

- Source: https://community.zscaler.com/s/question/0D5PJ00000hzZrc0AE/unable-to-configure-zpa-pse-behind-f5-vip
- Type: Q&A
- Posted: 2025-10-28T20:56:53.000Z
- Last activity: 2025-11-07T00:17:28.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>My Organization is trying to setup a Zscaler PSE and DR. </p><p><br></p><p>Broadly speaking it appears to be working - when attempting to connect directly over the LAN app connectors and workstations are able to connect and route traffic as expected. </p><p><br></p><p>However, when we try to put the traffic behind a F5 Local Traffic Manager VIP connections are breaking. We can see the traffic reaching the VIP but nothing is able to actually establish a connection. When taking PCAPs we see that traffic reaches the VIP. There is a client hello, 3 seconds of delay, and then a RST sent back. </p><p><br></p><p>The current configuration is the following </p><p><br></p><p>"<strong>Standard: </strong>Specifies a virtual server that directs client traffic to a load balancing pool and is the most basic type of virtual server. When you first create the virtual server, you assign an existing default pool to it. From then on, the virtual server automatically directs traffic to that default pool."</p><p><br></p><p>So, the connection is coming into a virtual server zspse.organization.com and then is forwarding without SSL offloading the TCP 443 traffic to the server in our DMZ. </p><p><br></p><p>If anyone has had experience setting this up we would appreciate any guidance as we are currently scratching our heads. </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ifiSW0AY/app-connector-and-pse-manager-version-can-this-be-automated","lastmod":"2025-11-07T03:16:44.000Z","id":"0D5PJ00000ifiSW0AY"} -->
## App Connector and PSE manager version - can this be automated?

- Source: https://community.zscaler.com/s/question/0D5PJ00000ifiSW0AY/app-connector-and-pse-manager-version-can-this-be-automated
- Type: Q&A
- Posted: 2025-11-05T03:12:08.000Z
- Last activity: 2025-11-07T03:16:44.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Whenever a new release of manager version in this example, version 25.45.1 and new version 25.46.2 we need to manually upgrade it. Is there a way from the admin portal to upgrage it automatically? </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000iflao0AA/time-zone-setting-mismatch-with-location-on-app-connectors-and-pse","lastmod":"2025-11-06T07:03:01.000Z","id":"0D5PJ00000iflao0AA"} -->
## Time zone setting mismatch with location on App  Connectors and PSE

- Source: https://community.zscaler.com/s/question/0D5PJ00000iflao0AA/time-zone-setting-mismatch-with-location-on-app-connectors-and-pse
- Type: Q&A
- Posted: 2025-11-05T03:16:53.000Z
- Last activity: 2025-11-06T07:03:01.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have observed a few ZPA and PSE servers with time zone mismatch, i.e., India server with EDT and or Singapore server using HKT instead of SGT. Or a US server on GMT instead of EST. Will this affect the servers if the time zone is updated? </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000iov0F0AQ/app-connector-groups-displaying-incorrect-number-of-app-connector","lastmod":"2025-11-07T03:19:37.000Z","id":"0D5PJ00000iov0F0AQ"} -->
## App Connector Groups Displaying Incorrect Number of App Connector

- Source: https://community.zscaler.com/s/question/0D5PJ00000iov0F0AQ/app-connector-groups-displaying-incorrect-number-of-app-connector
- Type: Q&A
- Posted: 2025-11-06T07:16:34.000Z
- Last activity: 2025-11-07T03:19:37.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>In my ZPA admin portal, I have 50 connected App Connectors and is being provisioned in one App Connector group. However, the group only display a count of 45 of 1000 used instead of 50 of 1000 used. Since all 50 App Conn are successfully provisioned. </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000iz5L10AI/ssh-log-entries-did-not-receive-identification-string-app-connectors","lastmod":"2025-11-26T13:47:01.000Z","id":"0D5PJ00000iz5L10AI"} -->
## SSH log entries "Did not receive identification string" App Connectors

- Source: https://community.zscaler.com/s/question/0D5PJ00000iz5L10AI/ssh-log-entries-did-not-receive-identification-string-app-connectors
- Type: Q&A
- Posted: 2025-11-07T02:36:08.000Z
- Last activity: 2025-11-26T13:47:01.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have been receiving multiple SSH logs entries "<strong>Did not receive identification string</strong>" from our app connector.</p><p><br></p><p>Can anyone explain if this is normal behaviour? I did not find any blocked access entries within 14 days, except there are few errors with an error reason <strong>AST_MT_SETUP_TIMEOUT_CANNOT_CONN_TO_BROKER</strong> which this is indicates communication timeouts between the connectors and Service edge, right?</p><p><br></p><p>I hope some will help me with this.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000k925I0AQ/zpa-app-connector-reassignment-from-one-appconnector-group-to-another","lastmod":"2025-11-27T13:02:13.000Z","id":"0D5PJ00000k925I0AQ"} -->
## ZPA app connector  reassignment from one app-connector group to another

- Source: https://community.zscaler.com/s/question/0D5PJ00000k925I0AQ/zpa-app-connector-reassignment-from-one-appconnector-group-to-another
- Type: Q&A
- Posted: 2025-11-17T19:18:55.000Z
- Last activity: 2025-11-27T13:02:13.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p>Is there a way to <span style="font-family: &quot;Salesforce Sans&quot;, Arial, sans-serif;">re-assign ZPA app connector from one app-connector group to another w/o removing app-connector registration?</span></p><p><span style="font-family: &quot;Salesforce Sans&quot;, Arial, sans-serif;">Thanks,</span></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000l70bQ0AQ/zscaler-zpa-app-connectors-deployment-and-issues","lastmod":"2025-12-10T10:18:14.000Z","id":"0D5PJ00000l70bQ0AQ"} -->
## ZScaler ZPA App Connectors deployment and issues

- Source: https://community.zscaler.com/s/question/0D5PJ00000l70bQ0AQ/zscaler-zpa-app-connectors-deployment-and-issues
- Type: Q&A
- Posted: 2025-11-28T13:43:37.000Z
- Last activity: 2025-12-10T10:18:14.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I have very little experience with ZScaler, but here is my environment:</p><p><br></p><p>- HQ where all the users are located,</p><p>- Fully remote users,</p><p>- Data Center X,</p><p>- Azure environment,</p><p>- Internet access is coming out of Data Center X,</p><p><br></p><p>We deploy four App Connectors, two in Azure and two in Data Center X.&nbsp;The two&nbsp;</p><p>App Connectors have their own Internet access in Azure while the other two&nbsp;</p><p>App Connectors Data Center X use Internet in Data Center X.&nbsp;We have a VPN tunnel</p><p>between Data Center X and customer A Data Center Y.&nbsp;The way the network is setup is&nbsp;</p><p>that there is a 10G connections between HQ and Data Center X, and express route&nbsp;</p><p>between Data Center X and Azure.&nbsp;There is a 5ms latency between HQ and Data Center&nbsp;</p><p>X, and 11ms between HQ and Azure (in order to get to Azure from HQ, traffic must&nbsp;</p><p>goes through Data Center X).&nbsp;So far so good, right?&nbsp;&nbsp;</p><p><br></p><p>Here are the problems:&nbsp;&nbsp;</p><p><br></p><p>#1:&nbsp;When a user in HQ needs to access an application locates in customer A Data&nbsp;</p><p>Center Y, I notice that it uses Azure App Connectors instead of Data Center X&nbsp;</p><p>App Connectors.&nbsp;WHY?</p><p><br></p><p>#2:&nbsp;In our Azure App Connectors, I see one App Connector has five times more usage</p><p>(application tunnel) than the other App Connector.&nbsp;WHY?&nbsp;How do I go about load</p><p>balancing between these two Azure App Connectors?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000lDZMq0AO/export-policies-and-reports-as-a-zpa-readonly-administrator","lastmod":"2025-12-03T16:51:13.000Z","id":"0D5PJ00000lDZMq0AO"} -->
## Export policies and reports as a ZPA read-only administrator

- Source: https://community.zscaler.com/s/question/0D5PJ00000lDZMq0AO/export-policies-and-reports-as-a-zpa-readonly-administrator
- Type: Q&A
- Posted: 2025-12-01T15:30:43.000Z
- Last activity: 2025-12-03T16:51:13.000Z
- Answers: 3
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We're using ZPA as a managed service. Of course the supplier does not want to have us permissions to perform any changes, so we have read-only access to the console. Now, I would like to be able to export application segments, segment groups and access policies to CSV, and to access reports. But both is not possible with my current role.</p><p><br></p><p>Now, the supplier claims that there is <strong>no possibility whatsoever</strong> to grant us access to these functions (CSV export and reports) without giving us <strong>full </strong>admin rights (that would include the possibility to <strong>modify </strong>everything).</p><p><br></p><p>Is this true? Is it really not possible to have a read-only role that can run the CSV export?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ljIac0AE/zpa-use-of-machine-provisioning-key-for-machineauthentication","lastmod":"2026-04-12T08:03:31.000Z","id":"0D5PJ00000ljIac0AE"} -->
## ZPA use of machine provisioning key for machine-authentication

- Source: https://community.zscaler.com/s/question/0D5PJ00000ljIac0AE/zpa-use-of-machine-provisioning-key-for-machineauthentication
- Type: Q&A
- Posted: 2025-12-10T19:28:59.000Z
- Last activity: 2026-04-12T08:03:31.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi All,</p><p>Trying to follow the documentation for machine-authentication with ZPA. There is a reference to create machine provisioning key. The question we have is what is the intended use of machine provisioning key? Does it have to be included with ZCC install or for some other use?</p><p>Please advice.</p><p>Thanks,</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000lwlEv0AI/best-tool-to-capture-monthly-zpa-server-performance-for-capacity-review","lastmod":"2025-12-15T07:08:21.000Z","id":"0D5PJ00000lwlEv0AI"} -->
## Best tool to capture monthly ZPA server performance for Capacity review.

- Source: https://community.zscaler.com/s/question/0D5PJ00000lwlEv0AI/best-tool-to-capture-monthly-zpa-server-performance-for-capacity-review
- Type: Q&A
- Posted: 2025-12-15T07:08:21.000Z
- Last activity: 2025-12-15T07:08:21.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have around 600+ ZPA AppConn and PSE servers running RHEL8 in our environment. What is/are the best tools to use for performance metrics in terms of CPU usage, memory utilization and bandwidth consumption for a monthly review? </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000m0jbD0AQ/zpa-connector","lastmod":"2025-12-16T08:35:15.000Z","id":"0D5PJ00000m0jbD0AQ"} -->
## Zpa Connector

- Source: https://community.zscaler.com/s/question/0D5PJ00000m0jbD0AQ/zpa-connector
- Type: Q&A
- Posted: 2025-12-16T05:45:10.000Z
- Last activity: 2025-12-16T08:35:15.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>How to deploy ZPA App connector on AWS </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000m6QrW0AU/licensing-model-for-saas-app-access-for-3rd-parties","lastmod":"2025-12-17T15:47:37.000Z","id":"0D5PJ00000m6QrW0AU"} -->
## Licensing Model for SaaS App Access for 3rd parties

- Source: https://community.zscaler.com/s/question/0D5PJ00000m6QrW0AU/licensing-model-for-saas-app-access-for-3rd-parties
- Type: Q&A
- Posted: 2025-12-17T15:47:37.000Z
- Last activity: 2025-12-17T15:47:37.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi, Team,</p><p><br/></p><p>I need your advise/guidance regarding this use case I have with a customer who is interested in Zscaler following a successful POC.</p><p><br/></p><p>Scenario:</p><p>The customer has Microsoft Dynamics application which is hosted by Microsoft, publicly accessible over the internet. They want to give access to managed employees as well as 3rd parties i.e their customers, sponsors, etc.. the number of 3rd parties is dynamic currently 100 but for managed users its just 50. </p><p><br/></p><p>During the POC we restricted direct access through Entra contitional access policy allowing only traffic from Zscaler Egress IPs. Used browser Isolation in ZPA and published the App on user portal for 3rd parties but still controlled access to managed users through ZCC.</p><p><br/></p><p> Now, I need to understand how this Scenario can be licensed. My understanding was that with user portal 2.0, the customer does not necessarily have to buy ZPA but just Browser Isolation, I could be wrong with the changes in licensing model now the platform approach.</p><p><br/></p><p>Can you kindly correct or guide me on the best possible way to quote this Scenario without the customer breaking the bank. </p><p><br/></p><p>Thanks for your assistance in advance.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000mNT5h0AG/zpa-entra-auth-fails","lastmod":"2026-01-07T02:56:01.000Z","id":"0D5PJ00000mNT5h0AG"} -->
## ZPA entra Auth fails

- Source: https://community.zscaler.com/s/question/0D5PJ00000mNT5h0AG/zpa-entra-auth-fails
- Type: Q&A
- Posted: 2025-12-22T21:36:50.000Z
- Last activity: 2026-01-07T02:56:01.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello , i have an issue related to Entra and ZPA integration , Customer of mine has only purchased ZPA . SO we tried to integrate ZPA and Entra and it didnot work . I opened a ZS case and they said for entra integration , we also need to integrate ZIA app in Enterprise applications in Entra . We did that . ZIA tenant is in zscaler.net and ZPA in zscalertwo.net . So I asked Zscaler to link these two as they are in different clouds . They linked both of them . Now on ZCC , when a use tries to login , it redirects to Entra . User put the username and it asks for MFA . Even MFA is Cisco Duo . it checks the cisco duo and it works fine at this stage . but then when it tries to download zscaler service it immediately throws an error - 'Unable to verify the SAML response from the IDP </p><p><br></p><p>Zscaler team checked Entra config and also IDP config on both ZIA and ZPA .it looks fine . I checked the timezones are also OK ..</p><p><br></p><p>Even the customer also tried to reinstall ZCC . Can anyone suggest if they faced any similar issue ?</p><p><img src="/sfc/servlet.shepherd/version/download/068PJ00000VP79K?asPdf=false&amp;operationContext=CHATTER"></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000mPuZk0AK/zidentity-as-an-idp-for-zpa","lastmod":"2025-12-23T16:05:03.000Z","id":"0D5PJ00000mPuZk0AK"} -->
## Zidentity as an iDP for ZPA

- Source: https://community.zscaler.com/s/question/0D5PJ00000mPuZk0AK/zidentity-as-an-idp-for-zpa
- Type: Q&A
- Posted: 2025-12-23T16:05:03.000Z
- Last activity: 2025-12-23T16:05:03.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p>Could Zidentity be used as an iDP for users in ZPA?</p><p>Thanks,</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000nYFRp0AO/zpa-app-connector-on-rhel-10","lastmod":"2026-01-27T06:01:33.000Z","id":"0D5PJ00000nYFRp0AO"} -->
## ZPA app connector on RHEL 10

- Source: https://community.zscaler.com/s/question/0D5PJ00000nYFRp0AO/zpa-app-connector-on-rhel-10
- Type: Q&A
- Posted: 2026-01-15T02:53:19.000Z
- Last activity: 2026-01-27T06:01:33.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>ZPA app connector, any timeline when it will support on RHEL 10?</p><p>or is it support now?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000oJ8Va0AK/zpa-authentication-freeze-at-signing-in-to-zscaler-private-access","lastmod":"2026-02-03T14:25:50.000Z","id":"0D5PJ00000oJ8Va0AK"} -->
## ZPA authentication freeze at "Signing in to Zscaler Private Access"

- Source: https://community.zscaler.com/s/question/0D5PJ00000oJ8Va0AK/zpa-authentication-freeze-at-signing-in-to-zscaler-private-access
- Type: Q&A
- Posted: 2026-01-28T13:55:27.000Z
- Last activity: 2026-02-03T14:25:50.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I'm encountering a problem on all macOS devices when trying to authenticate with ZPA.</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/068PJ00000Wr121?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p>The IdP is Okta, and authentication is successful when using Browser Based Authentication. The problem is that if we use the browser built into ZCC, it freezes on the "Signing in to Zscaler Private Access" screen and doesn't go any further.</p><p><br></p><p>It's not the ZCC version, as we've tested several versions and all of them have the same problem.</p><p><br></p><p>Has anyone else experienced this?</p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000oxmYY0AY/zpa-subscriptions-page","lastmod":"2026-02-16T15:19:07.000Z","id":"0D5PJ00000oxmYY0AY"} -->
## ZPA subscriptions page

- Source: https://community.zscaler.com/s/question/0D5PJ00000oxmYY0AY/zpa-subscriptions-page
- Type: Q&A
- Posted: 2026-02-07T17:09:48.000Z
- Last activity: 2026-02-16T15:19:07.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p>Is there a way to see ZPA account subscriptions information? It appears ZIA does have the page for that. Screenshot attached.</p><p><img src="/sfc/servlet.shepherd/version/download/068PJ00000XMXdR?asPdf=false&amp;operationContext=CHATTER"></p><p>Thanks,</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000p52ZJ0AY/okta-dashboard-as-zpa-user-portal","lastmod":"2026-02-11T15:43:43.000Z","id":"0D5PJ00000p52ZJ0AY"} -->
## Okta dashboard as ZPA user portal

- Source: https://community.zscaler.com/s/question/0D5PJ00000p52ZJ0AY/okta-dashboard-as-zpa-user-portal
- Type: Q&A
- Posted: 2026-02-10T14:22:45.000Z
- Last activity: 2026-02-11T15:43:43.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p>Could Okta Dashboard be used as ZPA user portal?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000pBHgI0AW/zpa-dns-suffix-and-zia-url-issue","lastmod":"2026-03-19T15:08:09.000Z","id":"0D5PJ00000pBHgI0AW"} -->
## ZPA, DNS Suffix and ZIA URL Issue

- Source: https://community.zscaler.com/s/question/0D5PJ00000pBHgI0AW/zpa-dns-suffix-and-zia-url-issue
- Type: Q&A
- Posted: 2026-02-11T18:25:39.000Z
- Last activity: 2026-03-19T15:08:09.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Evening,</p><p><br></p><p>We forward ZPA traffic through ZIA for inspection. End users dont add the full FQDN for internal apps, so we added a DNS suffix which appears correctly on the client side.</p><p>Traffic flows through and when it hits ZIA, we got the internal APP URL classification as miscellaneous with browser isolation.</p><p><br></p><p>Created a global whitelist, added the internal suffix and expected that to bypass the URL filter,. This works fine if the user adds the full FQDN, but if they dont, it gets matched by ZPA based on the suffix / FQDN, but in ZIA it doesnt match the global whitelist for URL.</p><p><br></p><p>Example:</p><p>Site = testsite.internal.test.net</p><p>ZPA matches a wildcard currently on anything *.internal.test.net</p><p>Suffix added is internal.test.net</p><p>Globalwhitelist includes .internal.test.net</p><p>End user puts in testsite in their browser</p><p>ZPA matches the wildcard, forwards to ZIA, but it doesnt match the whitelist</p><p><br></p><p>If user enters testsite.internal.test.net it matches all the way through</p><p><br></p><p>Any ideas? We cant put individual exceptions in or match keywords as there is a huge amount of apps with non standard naming</p><p><br></p><p>/EDIT With Answer/</p><p>This is expected behaviour. The suffix is not inserted into the browser, so ZIA does not see the FQDN. We ended up modifying the misc category to not use browser isolation. </p><p>It would be quite nice if Zscaler had the option to not do URL filtering for ZPA segments though</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000q12K20AI/zpa-issues-after-upgrading-to-macos-263","lastmod":"2026-02-26T16:59:24.000Z","id":"0D5PJ00000q12K20AI"} -->
## ZPA Issues after Upgrading to MacOS 26.3

- Source: https://community.zscaler.com/s/question/0D5PJ00000q12K20AI/zpa-issues-after-upgrading-to-macos-263
- Type: Q&A
- Posted: 2026-02-25T21:01:51.000Z
- Last activity: 2026-02-26T16:59:24.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Has anyone else seen ZPA issues after upgrading to MacOS 26.3 while running ZCC? Speed and performance seem to ramp down until a service restart is necessary when accessing private web resources. Only began to observe this after the upgrade from earlier versions. </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000rwnFP0AY/chrome-enterprise-device-trust-integration-with-zscaler","lastmod":"2026-04-29T20:45:27.000Z","id":"0D5PJ00000rwnFP0AY"} -->
## Chrome Enterprise Device Trust integration with Zscaler

- Source: https://community.zscaler.com/s/question/0D5PJ00000rwnFP0AY/chrome-enterprise-device-trust-integration-with-zscaler
- Type: Q&A
- Posted: 2026-03-31T14:54:35.000Z
- Last activity: 2026-04-29T20:45:27.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We are attempting to setup Chrome Enterprise Device Trust integration with Zscaler as per guide available at https://support.google.com/chrome/a/answer/13570263?hl=en</p><p>It appears we don't have "Chrome Enterprise Browser" under "Configuration &amp; Control" at ZPA Administration menu.</p><p>According to the guide this feature is available for all licensed editions of Zscaler Private Access.</p><p><br></p><p>Any feedback is appriciated.</p><p>Thanks,</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000sNmhg0AC/zpa-not-authenticated-state-causes-system-slowness-on-windows-11-pro25h2","lastmod":"2026-05-13T12:47:25.000Z","id":"0D5PJ00000sNmhg0AC"} -->
## ZPA Not Authenticated State Causes System Slowness on Windows 11 Pro,25H2

- Source: https://community.zscaler.com/s/question/0D5PJ00000sNmhg0AC/zpa-not-authenticated-state-causes-system-slowness-on-windows-11-pro25h2
- Type: Q&A
- Posted: 2026-04-08T09:20:58.000Z
- Last activity: 2026-05-13T12:47:25.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>An intermittent issue is observed on an HP EliteBook Ultra G1i 14‑inch Notebook (Next Gen AI PC – IDS Base Model) running Windows 11, with Zscaler Client Connector version 4.8.0.156, where the system becomes slow or unresponsive only when Zscaler is installed and running in a “ZPA Not Authenticated” state with the Network Type marked as Off Trusted Network. There is no issue when ZPA is connected or when Zscaler is fully disabled. The issue occurs on public/open Wi‑Fi when ZPA authentication does not initiate, affecting Outlook desktop, Microsoft 365 apps, and Windows UI elements (Taskbar, Search). Disabling and re‑enabling Wi‑Fi immediately restores apps. The system works normally on corporate LAN/Wi‑Fi with ZPA disabled.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000tPZku0AG/app-segments-populating-in-edge-appliance","lastmod":"2026-07-27T16:19:33.000Z","id":"0D5PJ00000tPZku0AG"} -->
## App Segments populating in Edge Appliance

- Source: https://community.zscaler.com/s/question/0D5PJ00000tPZku0AG/app-segments-populating-in-edge-appliance
- Type: Q&A
- Posted: 2026-04-23T17:00:51.000Z
- Last activity: 2026-07-27T16:19:33.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Question about ZTB and specifically app segments that are sent to an Edge Appliance.</p><p><br></p><p>ZPA portal will send down a list of app segments to an Edge appliance. You can see the list with the command line.</p><p><br></p><p><span style="font-family: courier;">gw-01:~$cat /etc/airgap/zcc/fqdn.txt</span></p><p><span style="font-family: courier;">.domain.com</span></p><p><span style="font-family: courier;">.domain.com</span></p><p><span style="font-family: courier;">etc...</span></p><p><span style="font-family: courier;">etc...</span></p><p><br></p><p>When a regular user logs into their client connector, ZPA will send a list of FQDN, but will filter out based on ZPA's Client Forwarding Policy and regular Policies.</p><p><br></p><p>How about for the Edges? Is there some filtering process going on before the list is sent to the Edges?</p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000uMT0A0AW/ziazpa-sandbox","lastmod":"2026-05-13T14:02:16.000Z","id":"0D5PJ00000uMT0A0AW"} -->
## ZIA/ZPA sandbox

- Source: https://community.zscaler.com/s/question/0D5PJ00000uMT0A0AW/ziazpa-sandbox
- Type: Q&A
- Posted: 2026-05-10T13:03:04.000Z
- Last activity: 2026-05-13T14:02:16.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>looking to set up cloud sandbox for zpa and zia. can anyone et me know steps</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000uVpDq0AK/zpa-route-audit-logs","lastmod":"2026-05-12T19:46:11.000Z","id":"0D5PJ00000uVpDq0AK"} -->
## ZPA route audit logs

- Source: https://community.zscaler.com/s/question/0D5PJ00000uVpDq0AK/zpa-route-audit-logs
- Type: Q&A
- Posted: 2026-05-12T17:49:24.000Z
- Last activity: 2026-05-12T19:46:11.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Team, I am trying to find the audit logs for app connector routes that were changed a week ago. We lost all the routes and I am looking for the changes made. </p><p>Where can i find this?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000xALmQ0AW/does-zpa-support-bidirectional-udp-for-a-vdi-display-protocol-pcoip-udp4172","lastmod":"2026-06-27T11:45:59.000Z","id":"0D5PJ00000xALmQ0AW"} -->
## Does ZPA support bidirectional UDP for a VDI display protocol (PCoIP UDP/4172)?

- Source: https://community.zscaler.com/s/question/0D5PJ00000xALmQ0AW/does-zpa-support-bidirectional-udp-for-a-vdi-display-protocol-pcoip-udp4172
- Type: Q&A
- Posted: 2026-06-24T12:43:29.000Z
- Last activity: 2026-06-27T11:45:59.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p>We are planning to migrate the remote-access path for a Horizon-based VDI environment from a traditional VPN to ZTNA (Zscaler ZPA), and I'd like to confirm whether PCoIP can work over ZPA.</p><p><br></p><p><strong>Architecture (simplified / anonymized):</strong></p><ul><li>Remote end-user device → VDI gateway → virtual desktops.</li><li>The gateway is an F5 BIG-IP APM acting as a PCoIP proxy / secure gateway. The client connects to a single gateway VIP.</li><li>Connection brokering to the Horizon Connection Servers is HTTPS/TCP 443 only (PCoIP does not traverse the Connection Servers).</li><li>PCoIP display traffic uses UDP/4172 and is relayed by the gateway between client and desktop. (Blast Extreme UDP/8443 is also provisioned but not currently used.)</li><li>The client always initiates the session. Once established, display data flows from the server side back to the client over UDP/4172, so traffic on 4172 is bidirectional.</li></ul><p>My understanding is that ZTNA/ZPA is primarily designed around client-initiated TCP connections, and UDP is connectionless (no handshake to anchor a "client-initiated" session).</p><p><br></p><p><strong>Questions:</strong></p><ol><li>Does ZPA support UDP application traffic such as PCoIP UDP/4172 end-to-end, including the server→client return traffic within a client-initiated session?</li><li>Has anyone successfully run Horizon PCoIP through ZPA? Any required configuration, limitations, or MTU/performance considerations?</li><li>If PCoIP UDP is not fully supported, would Blast Extreme over TCP (443/8443) be the recommended, more ZTNA-friendly alternative?</li></ol><p>Thanks in advance.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000yL0Ml0AK/zpa-usage-report-for-60-days-and-90-days","lastmod":"2026-08-17T17:03:07.000Z","id":"0D5PJ00000yL0Ml0AK"} -->
## ZPA Usage report for 60 days and 90 days

- Source: https://community.zscaler.com/s/question/0D5PJ00000yL0Ml0AK/zpa-usage-report-for-60-days-and-90-days
- Type: Q&A
- Posted: 2026-07-13T11:50:37.000Z
- Last activity: 2026-08-17T17:03:07.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Currently we can have a report of only 30days of usage of appseg and access policy.</p><p>If we can increase this range for 60days and 90 days, it will be beneficial for organization to maintain polices.</p>
<!-- /ZS-POST -->
