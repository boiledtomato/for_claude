# Zscaler Zenith Community — API / 自動化 (part 1)

Source: https://community.zscaler.com
Generated: 2026-08-01 13:55 UTC
Posts in this file: 39

> これはユーザー投稿のコミュニティフォーラムの内容であり、Zscaler の公式ドキュメントではない。

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evlBRCAY/logout-and-log-back-in-users-remotely-with-apiautomation","lastmod":"2023-06-02T17:09:36.000Z","id":"0D54u00009evlBRCAY"} -->
## Logout and Log Back in Users Remotely with API/Automation

- Source: https://community.zscaler.com/s/question/0D54u00009evlBRCAY/logout-and-log-back-in-users-remotely-with-apiautomation
- Type: Q&A
- Posted: 2022-10-13T20:38:30.000Z
- Last activity: 2023-06-02T17:09:36.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We have been receiving multiple Incident from users every day where the user reports an issue with the client connector stuck in ‘Connecting’ state or stuck with authentication phase. Zscaler TAM recommends to try rebooting the device or logging out the user and log back in again. Working with each user individually has been a task, we are looking to automate this process where a user can get the OTP by themselves and log out and re log in again. Has anyone tried this before or have any input on how to automate this through script or APIs?

Thanks,
 Monica Singh
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmU5CAI/video-azure-terraform-deployment","lastmod":"2023-05-31T09:08:51.000Z","id":"0D54u00009evmU5CAI"} -->
## [VIDEO] Azure Terraform Deployment

- Source: https://community.zscaler.com/s/question/0D54u00009evmU5CAI/video-azure-terraform-deployment
- Type: Q&A
- Posted: 2022-08-10T14:36:01.000Z
- Last activity: 2023-05-31T09:08:51.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Cloud Connector is a virtual appliance within Microsoft Azure used to forward cloud workload traffic to the Zero Trust Exchange. It can be deployed within an Azure environment using both Terraform and, as a more native option, Azure Marketplace. Zscaler Terraform scripts represent the “easy? button when deploying Cloud Connector appliances. Their value shines in the fact that they automate nearly every aspect of appliance deployment, from VNet creation to Subnet, Route Table, and NAT Gateway. Where required, Terraform scripts can even deploy test and management workstations. Be aware, however, that this often means that a customer wishing to integrate into a brownfield environment may have to adjust these scripts to fit their scenario.

Terraform scripts are wrapped up within an easy-to-use Bash script as well, for those customers who are unfamiliar with Hashicorp Configuration Language. This Bash script (labeled zsec in the archive downloaded from the Cloud Connector UI) provides an interactive prompting mechanism that administrators can use to instantiate their deployments. Simply run ./zsec up from your Bash interpreter and answer the on-screen prompts. The script will handle the rest! For those more familiar with Terraform, the archive contains all the .tf files necessary to customize the deployment to suit the environment.

- The Starter Deployment Template will instantiate a single or multiple Cloud Connector appliances with associated Subnets, a workload Subnet, their respective Route Tables and routes, and a NAT Gateway.
- The Starter Deployment Template with Load Balancer script will instantiate Azure Standard Load Balancer for High Availability.

In this video, we’ll explore:
 [0:00 to 1:08] Pre-requisites and overview of Terraform
 [1:08 to 1:30] How are Terraform scripts obtained, and what does each do?
 [1:30 to 2:45] Understanding Terraform deployment types
 [2:45 to 3:15] Executing Terraform via the zsec wrapper
 [3:15 to 3:45] Key takeaways

Transcript
Hello, my name is Aaron and I’m one of the Principal Technical Product Specialists for Zscaler Cloud Workload Protection.

In this video, we’ll explore how Zscaler Cloud Connector can be provisioned within Microsoft Azure using Terraform scripts. Before you get started, make sure to check out the Azure Pre-Requisites video and Terraform Overview video linked in the description as there are some items that need to be understood and set up prior to running these scripts.

Zscaler Cloud Connector Terraform scripts are the easy button for integrating Zscaler Workload Protection into a cloud environment. Their value shines in the fact that they automate nearly every aspect of appliance deployment, from VNet creation to Subnet, Route Table, and NAT Gateway creation. Where required, Terraform scripts can even deploy test and management workstations. Be aware, however, that this often means that a customer wishing to integrate into a brownfield environment may have to adjust these scripts to fit their scenario.

Though Terraform scripts are natively written in Hashicorp Configuration Language, or HCL, Zscaler encapsulates these scripts within a Bash script to make it easier to deploy. Keep this in mind if you choose to deploy with this option as you will need a Bash interpreter - such as Azure CloudShell, a Linux machine, or a Windows machine running Subsystem for Linux. Navigate to the Administration menu, followed by Deployment Templates, then the Azure tab.

- The Starter Deployment Template will instantiate a single Cloud Connector appliance with an associated Subnet, a workload Subnet, their respective Route Tables and routes, and a NAT Gateway.
- The Starter Deployment Template with Load Balancer script will instantiate Azure Standard Load Balancer for high availability.

Download the script applicable to your scenario and unzip it. The directory contains several files: a ChangeLog, a ReadMe, a Variables file, the Terraform script directory, and the Bash script wrapper. Open the variables file - terraform.tfvars - with your favorite text editor. For all deployments, variables 1, 2, and 3 must be uncommented and filled in. Here, we will paste in our Provisioning URL, our Azure Key Vault URL, and uncomment the HTTP Probe Port. Though the HTTP port is optional, Zscaler highly recommends a port be entered here so that high availability can be configured. This port identifies a heartbeat service that the appliance uses to report its current health to the Azure Standard Load Balancer.

Notice that the file contains additional variables when scrolling down. This allows the user to customize the deployment for Brownfield environments. Though it won’t be the focus of this demonstration, these variables can also be uncommented and adjusted to suit your environment.

Run the zsec Bash script in your terminal by executing the command ./zsec up. Notice how the Bash script then prompts for the type of deployment you wish to execute. The base will install a VNet, workload Subnet, Route Table, NAT Gateway, and a test workload. Base CC will install a single Cloud Connector into the mix by adding additional Subnets and Route Tables. Base CC LB will add a second Cloud Connector appliance in a second availability zone, along with Microsoft Azure Standard Load Balancer. The remaining ‘custom’ options should be chosen if you modified any variables in the terraform.tfvars file outside of the first three, as discussed previously.

The Bash script then automatically installs Terraform, fetches the necessary providers, and executes the deployment. If this is the first time you’ve run Terraform, you may be prompted for your Azure Client ID, Client Secret, Tenant ID, Object ID, and Region. Once complete, notice that the script provides login information for your newly instantiated hosts. You can also log in to your Azure console to review Terraform’s changes.

- Terraform is a highly customizable and easy option for deploying Cloud Connector. By default, it is well suited for Greenfield installation, but can be tailored for Brownfield with minimal effort.
- You can download the latest Terraform scripts from the Cloud Connector portal via the Administration > Deployment Templates menu.
- Make sure you have met the pre-requisites prior to running Terraform, then execute the zsec Bash script.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmU6CAI/video-aws-terraform-deployment","lastmod":"2023-05-31T09:09:00.000Z","id":"0D54u00009evmU6CAI"} -->
## [VIDEO] AWS Terraform Deployment

- Source: https://community.zscaler.com/s/question/0D54u00009evmU6CAI/video-aws-terraform-deployment
- Type: Q&A
- Posted: 2022-08-10T14:32:09.000Z
- Last activity: 2023-05-31T09:09:00.000Z
- Answers: 0
- Likes: 4
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Cloud Connector is a virtual appliance within AWS used to forward cloud workload traffic to the Zero Trust Exchange. It can be deployed within an AWS environment using both Terraform and, as a more native scripting option, CloudFormation. Zscaler Terraform scripts represent the “easy? button when deploying Cloud Connector appliances. Their value shines in the fact that they automate nearly every aspect of appliance deployment, from VPC creation to Subnet, Route Table, NAT Gateway, and IGW creation. Where required, Terraform scripts can even deploy test and management workstations. Be aware, however, that this often means that a customer wishing to integrate into a brownfield environment may have to adjust these scripts to fit their scenario.

Terraform scripts are wrapped up within an easy-to-use Bash script as well, for those customers who are unfamiliar with Hashicorp Configuration Language. This Bash script (labeled zsec in the archive downloaded from the Cloud Connector UI) provides an interactive prompting mechanism that administrators can use to instantiate their deployments. Simply run ./zsec up from your Bash interpreter and answer the on-screen prompts. The script will handle the rest! For those more familiar with Terraform, the archive contains all the .tf files necessary to customize the deployment to suit the environment.

- The Starter Deployment Template will instantiate a single or multiple Cloud Connector appliances, private, workload, and public Subnets, their respective Route Tables and routes, a NAT Gateway and an IGW.
- The Starter Deployment Template with ZPA adds the ability to instantiate Route 53 resources for outbound DNS resolution and redirection to the ZPA service for use-cases where Zscaler Zscaler Private Access is the requirement.
- The Starter Deployment Template with a High-Availability script will instantiate AWS Lambda for high availability. Please note that AWS Lambda functionality exists to provide backward compatibility for customers who have not yet migrated to Gateway Load Balancer. Zscaler recommends running the Starter Deployment Template with Gateway Load Balancer script instead if a customer is seeking High Availability.
- The Starter Deployment Template with ZPA and High-Availability will naturally aggregate all of the aforementioned functionality into a single deployment script.
- The Starter Deployment Template with Gateway Load Balancer (GWLB), as the name would imply, installs a Gateway Load Balancer as well as all the necessary GWLB endpoints and Target Group necessary for High Availability.

In this video, we’ll explore:

[0:00 to 1:08] Pre-requisites and overview of Terraform
 [1:08 to 1:57] How are Terraform scripts obtained, and what does each do?
 [1:57 to 3:40] Understanding Terraform deployment types
 [3:40 to 4:07] Understanding Cross-Zone Load Balancing
 [4:07 to 4:32] Executing Terraform via the zsec wrapper
 [4:32 to 5:03] Key takeaways

Transcript
Hello, my name is Aaron and I’m one of the Principal Technical Product Specialists for Zscaler Cloud Workload Protection.

In this video, we’ll explore how Zscaler Cloud Connector can be provisioned within AWS using Terraform scripts. Before you get started, make sure to check out the AWS Pre-Requisites video and Terraform Overview video linked in the description as there are some items that need to be understood and set up prior to running these scripts.

Zscaler Cloud Connector Terraform scripts are the easy button for integrating Zscaler Workload Protection into a cloud environment. Their value shines in the fact that they automate nearly every aspect of appliance deployment, from VPC creation to Subnet, Route Table, NAT Gateway, and IGW creation. Where required, Terraform scripts can even deploy test and management workstations. Be aware, however, that this often means that a customer wishing to integrate into a brownfield environment may have to adjust these scripts to fit their scenario.

Though Terraform scripts are natively written in Hashicorp Configuration Language, or HCL, Zscaler encapsulates these scripts within a Bash script to make it easier to deploy. Keep this in mind if you choose to deploy with this option as you will need a Bash interpreter - such as AWS CloudShell, a Linux machine, or a Windows machine running Subsystem for Linux. Navigate to the Administration menu, followed by Deployment Templates.

- The Starter Deployment Template will instantiate a single Cloud Connector appliance, a private, workload, and public Subnet, their respective Route Tables and routes, a NAT Gateway and an IGW.
- The Starter Deployment Template with ZPA adds the ability to instantiate Route 53 resources for outbound DNS resolution and redirection to the ZPA service for use-cases where Zscaler Zscaler Private Access is the requirement. For more information on ZPA, DNS redirection and its interaction with Cloud Connector, please check out the AWS DNS Setup for ZPA video linked in the description.
- The Starter Deployment Template with a High-Availability script will instantiate AWS Lambda or GWLB functionality for high availability.
- The Starter Deployment Template with ZPA and High-Availability will naturally aggregate all of the aforementioned functionality into a single deployment script.

Download the script applicable to your scenario and unzip it. The directory has several files: a ChangeLog, a ReadMe, a Variables file, the Terraform script directory, and the Bash script wrapper. Open the variables file - terraform.tfvars - with your favorite text editor. For all deployments, variables 1, 2, and 3 must be uncommented and filled in. Here, we will paste in our Provisioning URL, our AWS Secrets Manager name, and the HTTP Probe Port. Though the HTTP port is optional, Zscaler highly recommends a port be entered here so that high availability can be configured. This port identifies a heartbeat service that the appliance uses to report its current health to the AWS Gateway Load Balancer or Lambda function.

Notice that the file contains additional variables when scrolling down. This allows the user to customize the deployment for Brownfield environments. Though it won’t be the focus of this demonstration, these variables can also be uncommented and adjusted to suit your environment.

Run the zsec Bash script in your terminal by executing the command ./zsec up. Notice how the Bash script then prompts for the type of deployment you wish to execute. The base will install a VPC, workload Subnet, Route Table, IGW, and a test workload. Base 1cc will install a single Cloud Connector into the mix by adding a NAT Gateway, additional private Subnets, and Route Tables. Base 1cc ZPA will add Route 53 support for DNS forwarding of ZPA Application Segments. Similarly, Base 2cc and Base 2cc ZPA will add a second Cloud Connector appliance in a second availability zone, along with AWS Lambda. Base CC GWLB and Base CC GWLB ZPA will likewise provide High Availability and ZPA functionality, but will replace AWS Lambda with AWS Gateway Load Balancer. The remaining ‘custom’ options should be chosen if you modified any variables in the terraform.tfvars file outside of the first three, as discussed previously.

Should you choose GWLB as your High Availability option, note that cross-zone load-balancing will be disabled by default… GWLB attempts to maintain Availability Zone affinity. In the event of an appliance failure, this functionality can be turned on if you wish - allowing GWLB to ignore Availability Zone affinity and forward traffic to any available appliance. This may incur additional costs from AWS, however, so if necessary, you can disable this function by uncommenting the cross_zone_lb_enabled variable and updating it to false.

The Bash script then automatically installs Terraform, fetches the necessary providers, and executes the deployment. If this is the first time you’ve run Terraform, you may be prompted for your AWS Access Key, Secret Key, and Region. Once complete, notice that the script provides login information for your newly instantiated hosts. You can also log in to your AWS console to review Terraform’s changes.

- Terraform is a highly customizable and easy option for deploying Cloud Connector. By default, it is well suited for Greenfield installation, but can be tailored for Brownfield with minimal effort.
- You can download the latest Terraform scripts from the Cloud Connector portal via the Administration > Deployment Templates menu.
- Make sure you have met the pre-requisites prior to running Terraform, then execute the zsec Bash script.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmfpCAA/cloud-connector-terraform-modules-now-available-in-the-terraform-registry","lastmod":"2023-05-31T09:09:00.000Z","id":"0D54u00009evmfpCAA"} -->
## Cloud Connector Terraform Modules - Now Available in the Terraform Registry

- Source: https://community.zscaler.com/s/question/0D54u00009evmfpCAA/cloud-connector-terraform-modules-now-available-in-the-terraform-registry
- Type: Q&A
- Posted: 2022-12-16T21:51:58.000Z
- Last activity: 2023-05-31T09:09:00.000Z
- Answers: 0
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

**[Cloud Connector Terraform Modules - Now Available in the Terraform Registry](https://registry.terraform.io/namespaces/zscaler)**
 The Zscaler’s Cloud Connector Terraform Modules for both AWS and Azure are now publicly available in the HashiCorp Terraform Registry.
 The Terraform Registry enables the distribution of Terraform modules, which are reusable configurations. The registry acts as a centralized repository for module sharing, making modules easier to discover and reuse.
 Historically, these modules were only available for download via the Cloud Connector admin portal. Having these modules in the registry will allow for easier maintenance and utilization tracking via the statistics provided by Hashicorp.

**Resources:**

- **[Zscaler Cloud Connector AWS Module - Terraform Registry](https://registry.terraform.io/modules/zscaler/cloud-connector-modules/aws/latest)**
- **[Zscaler Cloud Connector AWS Module - GitHub Repository](https://github.com/zscaler/terraform-aws-cloud-connector-modules)**
- **[Zscaler Cloud Connector Azurerm Module - Terraform Registry](https://registry.terraform.io/modules/zscaler/cloud-connector-modules/azurerm/latest)**
- **[Zscaler Cloud Connector Azurerm Module - GitHub Repository](https://github.com/zscaler/terraform-azurerm-cloud-connector-modules)**

If you have any questions or feedback please let us know.

**Also:**

- The Help Docs [Deployment Templates](https://help.zscaler.com/cloud-connector/deployment-templates-zscaler-cloud-connector) page has been updated to reflect the updates templates and links

[Release Notes](https://help.zscaler.com/cloud-connector/release-upgrade-summary-2022)

- The current download links on the Cloud Connector Portal will remain for a short time. Next week, we’ll have a login banner regarding this change. The CC Portal downloads page will also be updated steering people towards the new Github pages.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmggCAA/rest-api-version-of-httpsipzscalercom-to-test-connections-status-or-command-line-connection-test","lastmod":"2023-12-20T15:16:16.000Z","id":"0D54u00009evmggCAA"} -->
## REST API version of https://ip.zscaler.com/ to test connections status (or command line connection test?)

- Source: https://community.zscaler.com/s/question/0D54u00009evmggCAA/rest-api-version-of-httpsipzscalercom-to-test-connections-status-or-command-line-connection-test
- Type: Q&A
- Posted: 2022-12-06T13:03:53.000Z
- Last activity: 2023-12-20T15:16:16.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is there a programmatic way to verify that the client is connected to Zscaler?

Rather than hack together a script that parses strings from this, is there a purpose-built API version?

```
Invoke-RestMethod https://ip.zscaler.com/
```
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhbCAA/how-to-send-logs-from-zscaler-to-apidatabase","lastmod":"2023-07-06T12:53:54.000Z","id":"0D54u00009evmhbCAA"} -->
## How to send logs from Zscaler to API/Database?

- Source: https://community.zscaler.com/s/question/0D54u00009evmhbCAA/how-to-send-logs-from-zscaler-to-apidatabase
- Type: Q&A
- Posted: 2022-11-07T10:43:31.000Z
- Last activity: 2023-07-06T12:53:54.000Z
- Answers: 9
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,
 we have a requirement to store connection details logs in our database.
 Is there any way to send logs to our API endpoint or even directly to MySQL database?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmi1CAA/directory-sync-via-api","lastmod":"2025-10-29T00:26:43.000Z","id":"0D54u00009evmi1CAA"} -->
## Directory Sync via API?

- Source: https://community.zscaler.com/s/question/0D54u00009evmi1CAA/directory-sync-via-api
- Type: Q&A
- Posted: 2022-10-14T13:49:37.000Z
- Last activity: 2025-10-29T00:26:43.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi there -

Wondered if there is a mechanism to trigger the Administration → Authentication Profile → Directory Synchronization process via the API?

The sync currently occurs once a day automatically. It would be great to be able to trigger this on an event via automation. Any ideas if this is possible.

Thanks,

Tom
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjTCAQ/wireshark-and-ssl-connections","lastmod":"2023-07-06T12:56:11.000Z","id":"0D54u00009evmjTCAQ"} -->
## Wireshark and SSL connections

- Source: https://community.zscaler.com/s/question/0D54u00009evmjTCAQ/wireshark-and-ssl-connections
- Type: Q&A
- Posted: 2022-08-12T15:54:00.000Z
- Last activity: 2023-07-06T12:56:11.000Z
- Answers: 2
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,
 sometimes it is kind of annoying to troubleshoot SSL connections in Wireshark, since the payload is encrypted.
 However, I just learned and tested that there is a possibility.
 In Windows 10 (and most likely also other OS) you can set an environment variable
 SSLKEYLOGFILE to a certain file like C:\User<username>keylog.txt

This convinces the SSL engine to log CLIENT RANDOM, CLIENT_HANDSHAKE_TRAFFIC_SECRET, etc.

So what I did:

- Set the SSLKEYLOGFILE environment variable
- Open the keylog.txt in notepad++
- Open Chrome in private mode and access my banking page / prepare a python script for Zscaler API (just to test)
- Deleted content of keylog.txt in notepad++, since the file grows fast
- Started Wireshark capture
- logged in to my home banking via chrome / started the python script to execute two put requests
- stopped capture and copy/paste keylog.txt entry to a newkeyfile.txt
- saved pcap file
- In Wireshark go to configuration/settings/protocols and chose TLS (sorry my Wireshark is German so don’t know the correct GUI wording in English)
- Configure newkeyfile.txt as "(Pre-Master-Secret log filename) and click OK and on “reload file?. If everything has worked well you can now see http in clear text ( i had to try it several times) - but finally for my banking test I had a closer look on all POST requests… and indeed I was able to see my username and password in “cleartext? - for the Python request I saw the json structures.

For obvious reasons I removed the environment variable after my tests.

Best regards
 Andreas
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmkICAQ/user-connecting-internet-with-apipa-ip","lastmod":"2023-05-31T09:08:49.000Z","id":"0D54u00009evmkICAQ"} -->
## User connecting internet with APIPA IP

- Source: https://community.zscaler.com/s/question/0D54u00009evmkICAQ/user-connecting-internet-with-apipa-ip
- Type: Q&A
- Posted: 2022-07-25T06:38:03.000Z
- Last activity: 2023-05-31T09:08:49.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I see logs shows Users are connecting internet via Zscaler using APIPA IP. Issue is very much intermediate and inconsistent. We notice users were connected from 3min to 30min to internet with APIPA IP address. We couldn’t reach the user during the active logs.

Most of them are offline or standby. So the machine might maintain keepalive session its application. However as per ZIA logs, machine can connect internet with APIPA IP address.

Issue happens only in GRE or Tun2.0 setup

i tried to replicate the scenario by bringing my home DHCP down. At this time, my internet is broken as expected since there is no GATEWAY IP assigned to my machine.

Do other see connection with APIPA IP in your office?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmmbCAA/saas-api-security-control","lastmod":"2023-07-06T11:44:30.000Z","id":"0D54u00009evmmbCAA"} -->
## SaaS API Security Control

- Source: https://community.zscaler.com/s/question/0D54u00009evmmbCAA/saas-api-security-control
- Type: Q&A
- Posted: 2022-04-25T12:45:34.000Z
- Last activity: 2023-07-06T11:44:30.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Team,

I am able to create a SaaS API tenant for Google Workspaces. But the same tenant is not visible under any of the SaaS Application types such as File Sharing, CRM, Collaboration etc.

Could you please help us with the same.

[[Screen Shot 2022-04-13 at 1.13.15 PM] Screen Shot 2022-04-13 at 1.13.15 PM2330×150 25 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O3vVAAS)

[[Screen Shot 2022-04-13 at 1.14.24 PM] Screen Shot 2022-04-13 at 1.14.24 PM1596×1304 162 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O6SrAAK)

Thank you.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmnHCAQ/powershell-connecting-to-cloudservices","lastmod":"2023-05-31T08:13:24.000Z","id":"0D54u00009evmnHCAQ"} -->
## PowerShell connecting to cloudservices

- Source: https://community.zscaler.com/s/question/0D54u00009evmnHCAQ/powershell-connecting-to-cloudservices
- Type: Q&A
- Posted: 2022-04-04T16:02:46.000Z
- Last activity: 2023-05-31T08:13:24.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Anyone knows how to get powershell to connect to O365, after Zscaler is installed on the corporate machine?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmnzCAA/programmatically-obtain-user-visited-domains-via-cloud-service-api","lastmod":"2023-05-31T08:13:28.000Z","id":"0D54u00009evmnzCAA"} -->
## Programmatically obtain user visited domains via Cloud Service API

- Source: https://community.zscaler.com/s/question/0D54u00009evmnzCAA/programmatically-obtain-user-visited-domains-via-cloud-service-api
- Type: Q&A
- Posted: 2022-02-16T16:10:41.000Z
- Last activity: 2023-05-31T08:13:28.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hey there, I was browsing the [Cloud Service API reference](https://help.zscaler.com/zia/api) and I didn’t find any endpoint which provides what I’m looking for natively.

I’m looking to find which domains are being visited by my users (for example [google.com](http://google.com)).
 Is there any way of doing so? I’d love to get some help if possible!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmuCCAQ/zscaler-security-alerts-via-emailapi","lastmod":"2023-05-31T08:46:56.000Z","id":"0D54u00009evmuCCAQ"} -->
## Zscaler Security Alerts via Email/API

- Source: https://community.zscaler.com/s/question/0D54u00009evmuCCAQ/zscaler-security-alerts-via-emailapi
- Type: Q&A
- Posted: 2021-01-25T19:59:40.000Z
- Last activity: 2023-05-31T08:46:56.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

1. Is it possible to query Zscaler API for new Security alerts e.g. Comms to bad domain blocked, malware detected, IPS matched, etc? We want to create security incidents but not ingest raw logs.
2. Zscaler security alerts via email are not helpful as they dont contain any information about the User ID, location, department, type of issue, bad destination, malware name etc. which can be helpful in creating tickets with details already filled in.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmyuCAA/accessing-internet-services-via-global-zen-node-using-powershell-or-cli","lastmod":"2023-05-31T08:50:41.000Z","id":"0D54u00009evmyuCAA"} -->
## Accessing Internet services via global zen node using powershell or cli

- Source: https://community.zscaler.com/s/question/0D54u00009evmyuCAA/accessing-internet-services-via-global-zen-node-using-powershell-or-cli
- Type: Q&A
- Posted: 2020-04-14T16:28:37.000Z
- Last activity: 2023-05-31T08:50:41.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,
 As we know,we can use global zen node in internet browser then authenticate then we access the internet.
 But if the user machine uses global zen node “without zapp or GRE/IPSEC Tunnel? and the user wants to join MIcrosoft Azure AD"for example" via powershell ,the zen node blocks this traffic,so how to solve this issue and allow user access internet services behind global zen node via powershell “for example?.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmzCCAQ/powershell-script-to-replace-the-zwebload-tab-of-the-analyzer-app","lastmod":"2023-05-31T09:08:08.000Z","id":"0D54u00009evmzCCAQ"} -->
## Powershell script to replace the Z-WebLoad tab of the analyzer app

- Source: https://community.zscaler.com/s/question/0D54u00009evmzCCAQ/powershell-script-to-replace-the-zwebload-tab-of-the-analyzer-app
- Type: Q&A
- Posted: 2020-03-04T13:51:31.000Z
- Last activity: 2023-05-31T09:08:08.000Z
- Answers: 1
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We do not have java widely deployed in our environment. With the z-analyzer app dependent on java it made thing a bit difficult. Wrote a PowerShell script that does everything the WebLoad tab does. Figured someone else out there may find it useful.

```
<#
.SYNOPSIS
Measurs how long it takes to load a web site or sites and detects if the client is behind a Zscaler cloud security gateway.
.DESCRIPTION
Creates two functions (Get-ZscalerGWInfo aka get Zscaler Gateway Information and get-websiteloadtimes).
Get-ZscalerGWInfo parses the site http://ip.zscaler.com for the nodename, datacenter, and clientip information.
Get-WebsiteLoadTimes measures how long the invoke-WebRequest function takes to load a web site. 
.LINK
Thanks to port135 for the code that was used to create the Get-WebsiteLoadTimes function.
https://port135.com/2019/09/11/how-to-measure-website-speed-in-your-internal-network/
.PARAMETER Site
Input must be in the form of an IP address or FQDN. Should be compatible with most TLDs. If not specified the defaults will be used.
.PARAMETER PageLoadAttempts
Specifies how may times to try and load the page. Default is 5.
.PARAMETER OutputToFile
Default is to display on screen. Will write out a filename if one is given in this parameter.
.INPUTS
System.String, System.Int32
.OUTPUTS
PSObjects containing the results. Option to lso saves a file to the working directory the script is run from.
.EXAMPLE
PS C:\> Get-PageLoadTimes.ps1 
Detects if the client is behind a Zscalers secure gateway and if it is displays the info about it.
Tests loading Wikipedia, Facebook, Amazon, Instagram, and Intuit 5 times and displays the results.
.EXAMPLE
PS C:\> Get-PageLoadTimes -s amazon.com -a 10 -o amazon.txt
Detects if the client is behind a Zscalers secure gateway and if it is displays the info about it.
Tests loading Amazon's web site 10 times and outputs the result to the file named amazon.txt.
#>

#Requires -version 4
[CmdletBinding()]
  param(
    [Parameter(ValueFromPipeline)]
    [Alias("s")]
    [Array]$Site = ('http://en.wikipedia.org/', 'https://facebook.com',  'https://amazon.com', 'https://instagram.com', 'http://intuit.com'),

    [Parameter(ValueFromPipeline)]
    [Alias("a")]
    [ValidateRange(1,1000)]
    [int]$PageLoadAttempts = 5, #Default to 5 attempts with a min of 1 and max of 1000
    
    [Parameter(ValueFromPipeline)]
    [Alias("o")]
    [string]$OutputToFile = "" #If specified will right the output to a file of the given name in the working directory the script was run from.
)

# Establish global variables.
$Global:WebsiteLoadTimeResults = New-Object Collections.Generic.List[String]
$Global:ZSResults = New-Object Collections.Generic.List[String]

Function script:Get-ZscalerGWInfo {
    $ZSIP = Invoke-WebRequest -Uri http://ip.zscaler.com
    $ZSIP = $ZSIP.InputFields.value -split ":"
    $ZSIP = $ZSIP -split ","
    $ZSIP = $ZSIP.trim('"')
    $ZSDatacenter = $ZSIP[9]
    $ZSNodeName = $ZSIP[5]
    $ZSClientIP = $ZSIP[13]
    $ZSClientIP = $ZSClientIP -replace ('"',"")
    $ZSClientIP = $ZSClientIP -replace ("}","")
    $Global:ZSResults.Add("Client is using the $ZSDatacenter Zscaler datacenter.")
    $Global:ZSResults.Add("Client is using $ZSNodeName as their Zscaler node.")
    $Global:ZSResults.Add("Zscaler is seeing the clients IP address as $ZSClientIP.")
} #End Get-ZscalerGWInfo

Function script:Get-WebsiteLoadTimes {
    ForEach ($url in $Site) {
        $totalSeconds=0
        $individualRequests = ""
 
        For ($i=0; $i -lt $PageLoadAttempts; $i++)  {
 
            $timeTaken = Measure-Command -Expression {
                $site = Invoke-WebRequest -Uri $url
            }
 
            $roundedSecond = [Math]::Round($timeTaken.TotalSeconds, 4)
            $totalSeconds += $roundedSecond
            $individualRequests += $roundedSecond.toString() +  ", "
 
            Start-Sleep -m 200
        }
 
        $averageSeconds = $totalSeconds / $PageLoadAttempts
        $Global:WebsiteLoadTimeResults.Add("For Site $url")
        $Global:WebsiteLoadTimeResults.Add("Average of $totalRequest requests is $averageSeconds")
        $Global:WebsiteLoadTimeResults.Add("Individual requests: $individualRequests")
        $Global:WebsiteLoadTimeResults.Add("")
    }
} #End Get-WebsiteLoadTimes

Get-WebsiteLoadTimes
Get-ZscalerGWInfo

If ($OutputToFile -eq "") {
    Write-Output $Global:ZSResults
    Write-Output ""
    Write-Output $Global:WebsiteLoadTimeResults
}
Else {
    $Global:ZSResults | Out-File -FilePath .\$OutputToFile 
    "" | Out-File -FilePath .\bob.txt -Append
    $Global:WebsiteLoadTimeResults | Out-File -FilePath .\$OutputToFile -Append
}
```
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn0vCAA/api-for-filecheck","lastmod":"2023-05-31T08:51:35.000Z","id":"0D54u00009evn0vCAA"} -->
## API for Filecheck

- Source: https://community.zscaler.com/s/question/0D54u00009evn0vCAA/api-for-filecheck
- Type: Q&A
- Posted: 2019-10-31T23:37:28.000Z
- Last activity: 2023-05-31T08:51:35.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Looking for a documentation or API developer guide for [filecheck.zscaler.com](http://filecheck.zscaler.com). Are there any? We are licensed Zscaler customer. The API key in the portal can be used only against ZIA
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2RCAQ/inspect-json-and-api-content-for-deep-analysis-of-non-sanctioned-cloud-storage","lastmod":"2023-07-06T13:16:13.000Z","id":"0D54u00009evn2RCAQ"} -->
## Inspect JSON and API content for deep analysis of non sanctioned cloud storage

- Source: https://community.zscaler.com/s/question/0D54u00009evn2RCAQ/inspect-json-and-api-content-for-deep-analysis-of-non-sanctioned-cloud-storage
- Type: Q&A
- Posted: 2019-05-20T01:52:33.000Z
- Last activity: 2023-07-06T13:16:13.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Guys,

I am looking for a solution which can provide me greater transparency of my users uploading files to non enterprise owned cloud storage tenants. 99% of the time this would be OK, however, there are instances where we may need to monitor or retrospectively review cloud storage. The tenant level analysis is key to my requirement.

eg. an accountant uploads a tax return to their own private google drive storage which is not part of the corporate network (We have a corporate DMS and are Office365 centric). This document does not contain any DLP tags or other identifiers to make it a known “type?. I simply want to be able to know that a user uploaded a file called random.pdf to a non firm cloud storage provider.

I are currently using MS 365 E5, with a strong focus on Defender, the MS ATP stack and incrementally building integration into the Sentinel engine. Our current forward proxy logs are shipped to MS CASB providing us with high level information.

Is this possible now or in the near future, any feedback would be appreciated.

Thanks
 Ben
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3RCAQ/api-access-logging","lastmod":"2023-05-31T08:53:49.000Z","id":"0D54u00009evn3RCAQ"} -->
## API access logging

- Source: https://community.zscaler.com/s/question/0D54u00009evn3RCAQ/api-access-logging
- Type: Q&A
- Posted: 2018-11-14T11:48:07.000Z
- Last activity: 2023-05-31T08:53:49.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is there a syslog event generated when API token is used/accessed for ZIA?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3gCAA/zscaler-iocs-feed-via-api","lastmod":"2023-05-31T08:54:09.000Z","id":"0D54u00009evn3gCAA"} -->
## ZScaler IOC’s feed via API

- Source: https://community.zscaler.com/s/question/0D54u00009evn3gCAA/zscaler-iocs-feed-via-api
- Type: Q&A
- Posted: 2018-10-18T21:22:56.000Z
- Last activity: 2023-05-31T08:54:09.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are trying to feed a list of IOC’s into ZScaler via API by using Workato. Would ZScaler consider creating Workato connector on Workato market place?
 Thanks,
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4rCAA/undefined-user-activity","lastmod":"2023-05-31T08:55:15.000Z","id":"0D54u00009evn4rCAA"} -->
## Undefined User Activity

- Source: https://community.zscaler.com/s/question/0D54u00009evn4rCAA/undefined-user-activity
- Type: Q&A
- Posted: 2018-05-28T02:36:32.000Z
- Last activity: 2023-05-31T08:55:15.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Good morning!

I have a question related to identity management and non-browser web activity.

User —> Browser —> [PAC] —> Web —> Zscaler ([identity@company.com](mailto:identity@company.com))

User —> Non-Browser —> [PAC] —> Web —> Zscaler (Enterprise Location Only)

For example, a user received an identity when they visit any website through IE, Chrome, etc., however, if they run a PowerShell script that makes an inbound/outbound connection, I can only see their client ip address, which makes it difficult to correlate back to an individual user due to DHCP leases constantly being updated.

Also, any third party resources loaded in the browser (css, js, etc) do not receive an identity as well, just the location.

I’m wondering if it’s possible to associate a user identity ([identity@company.com](mailto:identity@company.com)) to all outbound/inbound web traffic, regardless if the user used their browser or not.

Thanks for reading!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009iYQv6CAG/zscaler-terraformer-migration-tool-v030-june-21-2023","lastmod":"2024-07-08T07:09:44.000Z","id":"0D54u00009iYQv6CAG"} -->
## Zscaler Terraformer Migration Tool - v0.3.0 (June, 21 2023)

- Source: https://community.zscaler.com/s/question/0D54u00009iYQv6CAG/zscaler-terraformer-migration-tool-v030-june-21-2023
- Type: Q&A
- Posted: 2023-06-21T18:41:47.000Z
- Last activity: 2024-07-08T07:09:44.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><strong style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">Notes</strong><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">- Release date:&nbsp;</span><strong style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">**(June 21, 2023)**</strong></p><p><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">- Supported Terraform version:&nbsp;</span><strong style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">**v1.x.x**### Enhancements</strong></p><p><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">-&nbsp;</span><a href="https://github.com/zscaler/zscaler-terraformer/pull/97" rel="noopener noreferrer" target="_blank" style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;"><strong>PR #97</strong></a><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">: Each generated resource, will not contain the internal numeric ID generated by the ZPA Cloud. This will prevent potential name duplication during the import process.</span></p><p><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">-&nbsp;</span><a href="https://github.com/zscaler/zscaler-terraformer/pull/115" rel="noopener noreferrer" target="_blank" style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;"><strong>PR #115</strong></a><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">&nbsp;The Zscaler Terraformer tool now checks for existing Terraform binary version installations. If the binary is not installed, ZT will automatically download and install the latest terraform binary.</span></p><p><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">-&nbsp;</span><a href="https://github.com/zscaler/zscaler-terraformer/pull/116" rel="noopener noreferrer" target="_blank" style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;"><strong>PR #116</strong></a><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">&nbsp;All imported resources will not contain the following message:&nbsp;</span><strong style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">``__generated__ by Zscaler Terraformer from [Resource Name]``</strong><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">&nbsp;in the auto-generated configuration file.</span></p><p><br></p><p><strong style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">Bug Fixes</strong></p><p><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">-&nbsp;</span><a href="https://github.com/zscaler/zscaler-terraformer/pull/96" rel="noopener noreferrer" target="_blank" style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;"><strong>PR #96</strong></a><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">&nbsp;- Fixed import of&nbsp;</span><span style="font-size: 12px; font-family: Monaco, Menlo, Consolas, &quot;Courier New&quot;, monospace;">zpa_segment_group</span><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">&nbsp;and&nbsp;</span><span style="font-size: 12px; font-family: Monaco, Menlo, Consolas, &quot;Courier New&quot;, monospace;">zpa_server_group</span><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">&nbsp;resources where the&nbsp;</span><span style="font-size: 12px; font-family: Monaco, Menlo, Consolas, &quot;Courier New&quot;, monospace;">applications</span><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">&nbsp;attribute was being imported as a Set instead of a list of IDs.</span></p><p><br></p><p><strong style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;"><span class="ql-cursor">﻿</span>Note:&nbsp;</strong><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">HashiCorp Terraform v1.5.0 now has native Config-driven import capabilities; however, our tool is considerably more robust and simpler to use, while at the same time being a multi-provider (ZPA and ZIA) config-driven import tool. You can read more in my article published&nbsp;</span><a href="https://medium.com/@willguibr/terraform-1-5-import-and-automatic-code-generation-caa4debfef28" rel="noopener noreferrer" target="_blank" style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">here</a><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">.</span></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009jZpGMCA0/python-script-to-add-zscaler-cidr-blocks-to-aws-security-groups","lastmod":"2023-07-06T11:48:40.000Z","id":"0D54u00009jZpGMCA0"} -->
## Python script to add Zscaler CIDR blocks to AWS Security Groups

- Source: https://community.zscaler.com/s/question/0D54u00009jZpGMCA0/python-script-to-add-zscaler-cidr-blocks-to-aws-security-groups
- Type: Q&A
- Posted: 2019-06-14T11:12:32.000Z
- Last activity: 2023-07-06T11:48:40.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,
 Recently I needed to put some rules in place to allow only Zscaler nodes to hit a AWS hosted service. Given my distain for copy pasting (at least 500 mouse click to make this happen), and thinking Iâ€™m probably not the first or last to need this, I scripted something up. Hopefully someone finds this useful.

```
import boto3 import requests from botocore.exceptions import ClientError ''' Script to call on Zscaler DC list and create AWS security groups (SG) in a VPC of your choosing We iterate of the Zscaler DC list and add each CIDR block to a SG, in groups of 50 (keeping within the default AWS limis for SG enteries) ''' GLOBALS - Becasue I'm lazy and can't be bothered passing vars around ec2 = boto3.client('ec2') seperator = "\n" count = 0 ipranges = "" change this to your VPC ID, the one where you want these SG's mnade vpc_id = "vpc-" def create_sg(count): gracioulsly lifted form --> https://boto3.amazonaws.com/v1/documentation/api/latest/guide/ec2-example-security-group.html groupname = "Zscaler" + str(count+1) + "-" + str(count+50) try: response = ec2.create_security_group(GroupName=groupname, Description='Zscaler ZIA Nodes', VpcId=vpc_id) security_group_id = response'GroupId' print('Security Group Created %s in vpc %s.' % (security_group_id, vpc_id)) return security_group_id except ClientError as e: print(e) return False This is the main(), but i didn't make a main, so this is the main. Ask Zscaler for the DC list - Example response, jusr CSV stuff, no header. 185.46.212.0/23,NL,,Amsterdam, 213.152.228.0/24,NL,,Amsterdam, 165.225.28.0/23,NL,,Amsterdam, zs_dc = requests.get("http://ips.zscaler.net/sites/default/files/geoips/geoip.csv") bust into an array, or a list for the python tragics.. zs_dc_list = zs_dc.content.decode().split(seperator) Let's iterate for line in zs_dc_list: Zacaler response data generates a blank line, parsing will thrown an exemotion, so let's test for it if "," not in line: continue AWS has a default max of 50 rules per security group, so, let's fill'em up then create a new if count % 50 == 0: security_group_id = create_sg(count) print ("Created new security group: " + str(security_group_id)) let's pop the CSV int the relevant variables dc_elements = line.split(",") ip_range = dc_elements0.strip('"') I wanted to ise these for the rule descriptions, but AWS doesn't have any documented support for this even thoiugh you cna pass the Descriotion param in on the AWSCLI. Boo! lets log them anyway, maybe one day we can add ot the actual API call city = dc_elements3.strip('"') state = dc_elements2.strip('"') country = dc_elements1.strip('"') description = country + " " + state + " " + city print("adding rule for:" + description) scrub the doct from the next part dc_elements = {} try: doing the needful. Callimng the actual API data = ec2.authorize_security_group_ingress( GroupId=security_group_id, IpPermissions= {'IpProtocol': "-1", 'IpRanges': {'CidrIp': ip_range}} ) print('Ingress Successfully Set %s' % data) count += 1 loop = False except ClientError as e: print(e)
```

End result.

[[image] image3348Ã—1834 818 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O6T5AAK)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009raTDKCA2/api-key-management-rotation","lastmod":"2023-08-09T12:53:17.000Z","id":"0D54u00009raTDKCA2"} -->
## API Key Management - Rotation?

- Source: https://community.zscaler.com/s/question/0D54u00009raTDKCA2/api-key-management-rotation
- Type: Q&A
- Posted: 2023-08-08T12:30:26.000Z
- Last activity: 2023-08-09T12:53:17.000Z
- Answers: 2
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>In order for the ZPA API to meet our security requirements we must be able to rotate keys and programmatically manage them.</p><p>Is there any way to do this with ZPA?</p><p>Are there any plans to enable this feature?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009zde7OCAQ/api-amd-powershell","lastmod":"2023-09-19T19:45:11.000Z","id":"0D54u00009zde7OCAQ"} -->
## API amd Powershell

- Source: https://community.zscaler.com/s/question/0D54u00009zde7OCAQ/api-amd-powershell
- Type: Q&A
- Posted: 2023-09-19T19:45:11.000Z
- Last activity: 2023-09-19T19:45:11.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Anyone has a example to connect to the API through powershell and Invoke-RestMethod?</p><p><br></p><p>I have this:</p><p><br></p><p>$URL_API = "https://zsapi.zscalerthree.net"</p><p>$requestUrl = $URL_API + &nbsp;"/api/v1/authenticatedSession"</p><p>$headers = @{</p><p>&nbsp; &nbsp; "Content-Type" = "application/json"</p><p>&nbsp; &nbsp; "Cache-Control" = "no-cache"</p><p>&nbsp; &nbsp; "apiKey" = "API_KEY"</p><p>&nbsp; &nbsp; }</p><p><br></p><p>(Invoke-RestMethod -Uri $requestUrl -Headers $headers -Method Post -Verbose)</p><p><br></p><p>But i got the message :</p><p><br></p><p>"Invoke-RestMethod : {"code":"UNEXPECTED_ERROR","message":"An unexpected error has occurred, please contact Zscaler's support"}"</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009zgbuyCAA/sdwan-partner-api-location-attributes","lastmod":"2023-10-30T16:27:16.000Z","id":"0D54u00009zgbuyCAA"} -->
## SD-WAN Partner API - Location Attributes

- Source: https://community.zscaler.com/s/question/0D54u00009zgbuyCAA/sdwan-partner-api-location-attributes
- Type: Q&A
- Posted: 2023-09-22T15:08:41.000Z
- Last activity: 2023-10-30T16:27:16.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>When using SD-WAN partner API to automate tunnel/location/vpn credentials etc. I've noticed the SD-WAN vendor creates locations that are usually un-readable. In this scenario we are using VMWare Velo Cloud and the location name is edge_<GUID></p><p><br></p><p>My question is, is it safe to change this location name on the Zscaler side? so it's meaningful? Is there a risk Vmware overwrites it down the road. </p><p><br></p><p>Cheers.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A6UMQnCQO/how-to-manage-zscaler-via-terraform","lastmod":"2023-11-04T09:53:47.000Z","id":"0D54u0000A6UMQnCQO"} -->
## How to manage Zscaler via Terraform

- Source: https://community.zscaler.com/s/question/0D54u0000A6UMQnCQO/how-to-manage-zscaler-via-terraform
- Type: Q&A
- Posted: 2023-10-26T11:20:34.000Z
- Last activity: 2023-11-04T09:53:47.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Team ,

,

We've just started using Terraform in our setup, and I'm new to it. I need assistance with Terraform management.

Any kind of documents /SOPs or video is helpful for me .

﻿
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AJWT6RCQX/getting-error-while-making-api-authentication-using-java","lastmod":"2024-03-01T03:33:53.000Z","id":"0D54u0000AJWT6RCQX"} -->
## Getting error while making API authentication using Java.

- Source: https://community.zscaler.com/s/question/0D54u0000AJWT6RCQX/getting-error-while-making-api-authentication-using-java
- Type: Q&A
- Posted: 2024-02-18T21:54:55.000Z
- Last activity: 2024-03-01T03:33:53.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Getting error while making authentication using Java.</p><p>Python script is mentioned in the website, I have written similar in java but sometimes it works sometimes not.</p><p>https://help.zscaler.com/cloud-branch-connector/managing-admin-users-and-roles-using-api#pythonGettinganAdminRoleByRoleID</p><p>Can anyone help me with Java code of the Python script mentioned in the URL above.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ALpTZmCQN/getting-invalidapikey-while-running-powershell-script","lastmod":"2024-02-29T17:43:19.000Z","id":"0D54u0000ALpTZmCQN"} -->
## Getting INVALID_API_KEY while running Powershell Script

- Source: https://community.zscaler.com/s/question/0D54u0000ALpTZmCQN/getting-invalidapikey-while-running-powershell-script
- Type: Q&A
- Posted: 2024-02-29T16:44:50.000Z
- Last activity: 2024-02-29T17:43:19.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>function obfuscateApiKey {</p><p>&nbsp; &nbsp; param (</p><p>&nbsp; &nbsp; &nbsp; &nbsp; [string]</p><p>&nbsp; &nbsp; &nbsp; &nbsp; #Your API key</p><p>&nbsp; &nbsp; &nbsp; &nbsp; $apiKey,</p><p>&nbsp; &nbsp; &nbsp; &nbsp; [string]</p><p>&nbsp; &nbsp; &nbsp; &nbsp; #Timestamp</p><p>&nbsp; &nbsp; &nbsp; &nbsp; $timestamp</p><p>&nbsp; &nbsp; )</p><p><br></p><p>&nbsp; &nbsp; $high = $timestamp.substring($timestamp.length - 6)</p><p>&nbsp; &nbsp; $low = ([int]$high -shr 1).toString()</p><p>&nbsp; &nbsp; $obfuscatedApiKey = ''</p><p><br></p><p>&nbsp; &nbsp; while ($low.length -lt 6) {</p><p>&nbsp; &nbsp; &nbsp; &nbsp; $low = '0' + $low</p><p>&nbsp; &nbsp; }</p><p><br></p><p>&nbsp; &nbsp; for ($i = 0; $i -lt $high.length; $i++) {</p><p>&nbsp; &nbsp; &nbsp; &nbsp; $obfuscatedApiKey += $apiKey[[int64]($high[$i].toString())]</p><p>&nbsp; &nbsp; }</p><p><br></p><p>&nbsp; &nbsp; for ($j = 0; $j -lt $low.length; $j++) {</p><p>&nbsp; &nbsp; &nbsp; &nbsp; $obfuscatedApiKey += $apiKey[[int64]$low[$j].ToString() + 2]</p><p>&nbsp; &nbsp; }</p><p><br></p><p>&nbsp; &nbsp; return &nbsp;"`"$($obfuscatedApiKey)`""</p><p>}</p><p><br></p><p>$headers = New-Object "System.Collections.Generic.Dictionary[[String],[String]]"</p><p>$headers.Add("Content-Type", "application/json")</p><p>$timestamp = [datetimeoffset]::utcnow.tounixtimemilliseconds()</p><p>$ts="`"$($timestamp)`""</p><p>$timestampString = $timestamp.ToString()</p><p>$apiKey = ""</p><p>Write-Host $apiKey</p><p>$apiKeyObfuscate = obfuscateApiKey -apiKey $apiKey -timestamp $timestampString</p><p>$POST_PARAMS = "{`"username`":`"`",`"password`":`"`",`"apiKey`":$apiKeyObfuscate,`"timestamp`":$ts}"</p><p>Write-Host $POST_PARAMS</p><p>$url = "https://zsapi.zscaler.net/api/v1/authenticatedSession"</p><p>[System.Net.ServicePointManager]::ServerCertificateValidationCallback = {$true} </p><p>$response = Invoke-RestMethod -Uri $url -Method 'POST' -Headers $headers -Body $POST_PARAMS</p><p>Write-Host $response</p><p>$cookies = $response.Headers</p><p>#$cookie = $cookies.Substring(0,43)</p><p>Write-Host $cookies</p><p><#</p><p>$headers1 = New-Object "System.Collections.Generic.Dictionary[[String],[String]]"</p><p>$headers1.Add("Cookie", $cookie)</p><p>$url1 = "https://zsapi.zscaler.net/api/v1/adminUsers"</p><p>$response1 = Invoke-RestMethod -Uri $url1 -Method 'GET' -Headers $headers1</p><p>Write-Host $response1</p><p>#></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ANF77SCQT/exceptions-tab-missing-in-saas-api-security-control-is-this-normal-behavior","lastmod":"2025-11-21T01:47:14.000Z","id":"0D54u0000ANF77SCQT"} -->
## Exceptions tab missing in SaaS API Security Control. Is this normal behavior?

- Source: https://community.zscaler.com/s/question/0D54u0000ANF77SCQT/exceptions-tab-missing-in-saas-api-security-control-is-this-normal-behavior
- Type: Q&A
- Posted: 2024-03-18T03:57:42.000Z
- Last activity: 2025-11-21T01:47:14.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">Hi Team,</span></p><p><span style="font-size: 15px; font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif;">Is it normal for the Exceptions tab to be missing from the "Collaboration" and "Public Cloud Storage" menus in SaaS Security API Control for Data Loss Prevention? Is this a bug?</span></p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000h7CbF?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000h7CbK?asPdf=false&amp;operationContext=CHATTER"></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000h7CbP?asPdf=false&amp;operationContext=CHATTER"></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ASP6EMCQ1/get-users-api-is-not-providing-complete-results","lastmod":"2024-05-06T15:40:15.000Z","id":"0D54u0000ASP6EMCQ1"} -->
## GET Users API is not providing complete results

- Source: https://community.zscaler.com/s/question/0D54u0000ASP6EMCQ1/get-users-api-is-not-providing-complete-results
- Type: Q&A
- Posted: 2024-05-06T05:02:12.000Z
- Last activity: 2024-05-06T15:40:15.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I am trying to extract user information from ZDX API. Using the below API query. It is not providing complete list of users. Instead providing only last 2 hours results. I tried using from parameter as suggested but still no success.</p><p><br></p><p># --------------------&nbsp;Generate Bearer Token ----------------------#</p><p><br></p><p>import requests</p><p>import io</p><p>import hashlib</p><p>import time</p><p>import json</p><p>from datetime import datetime, timezone</p><p>proxies = {</p><p>&nbsp;&nbsp;'http': 'xxxxxxxxxxxxxxxxxxxxxx',</p><p>&nbsp;&nbsp;'https': 'xxxxxxxxxxxxxxxxxxxxxx',</p><p>&nbsp;&nbsp;</p><p>}</p><p>keyID = "xxxxxxxxxxxxxxxxxxxxxx"</p><p>keySecret = "xxxxxxxxxxxxxxxxxxxxxxxxx"</p><p><br></p><p>payload = {</p><p>&nbsp;&nbsp;'key_id': keyID,</p><p>&nbsp;&nbsp;'key_secret': keySecret</p><p>}</p><p><br></p><p>response = requests.post('https://api.zdxcloud.net/v1/oauth/token',</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;headers={'Content-Type' : 'application/json', 'accept' : 'application/json'}, proxies=proxies, data=json.dumps(payload))</p><p><br></p><p>accessToken = ""</p><p>if response.status_code == 200:</p><p>&nbsp;&nbsp;#print("auth successful")</p><p>&nbsp;&nbsp;accessToken = response.json()['token']</p><p>&nbsp;&nbsp;atok = "Bearer"+" "+accessToken</p><p>&nbsp;&nbsp;#print(accessToken)</p><p>else:</p><p>&nbsp;&nbsp;raise Exception("auth failed")</p><p><br></p><p># --------------------&nbsp;Retrieve First Page ----------------------#</p><p><br></p><p>mylog=[]</p><p>url = "https://api.zdxcloud.net/v1/users"</p><p><br></p><p>payload={}</p><p>headers = {</p><p>&nbsp;'Accept': 'application/json',</p><p>&nbsp;'Authorization': atok</p><p>}</p><p><br></p><p>response = requests.request("GET", url, headers=headers, proxies=proxies, data=payload)</p><p>results = response.json()</p><p>#print(results)</p><p><br></p><p>mylog.extend(results['users'])</p><p><br></p><p>offset = results['next_offset']</p><p><br></p><p># --------------------&nbsp;Get Remaining Pages ----------------------#</p><p><br></p><p>while True:</p><p>&nbsp;&nbsp;url = f"https://api.zdxcloud.net/v1/users?offset={offset}"</p><p>&nbsp;&nbsp;response = requests.request("GET", url, headers=headers, proxies=proxies, data=payload)</p><p>&nbsp;&nbsp;results = response.json()</p><p>&nbsp;&nbsp;if len(results['users']) == 0:</p><p>&nbsp;&nbsp;&nbsp;&nbsp;break&nbsp;&nbsp;&nbsp;</p><p>&nbsp;&nbsp;mylog.extend(results['users'])</p><p>&nbsp;&nbsp;offset = results['next_offset']</p><p><br></p><p># --------------------&nbsp;Save Output as .Json ----------------------#</p><p><br></p><p>json_object = json.dumps(mylog, indent=2)</p><p>with open("D:\ZdxUsers.json", "w") as outfile:</p><p>&nbsp;&nbsp;outfile.write(json_object)</p><p><br></p><p>exit()</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AUknLxCQJ/zscaler-causes-random-internal-network-disruptions","lastmod":"2024-06-26T20:48:19.000Z","id":"0D54u0000AUknLxCQJ"} -->
## Zscaler causes random internal network disruptions

- Source: https://community.zscaler.com/s/question/0D54u0000AUknLxCQJ/zscaler-causes-random-internal-network-disruptions
- Type: Q&A
- Posted: 2024-06-06T11:37:30.000Z
- Last activity: 2024-06-26T20:48:19.000Z
- Answers: 10
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>hi.. </p><p>we have a client application which does not access the internet, but only to an internal server </p><p>it polls the server every second with a rest API</p><p>At random times during the day the application would timeout on a single query (and it wasn't the server's fault as wireshark showed traffic coming and going , but the app will not get a connection for more than 5 seconds) </p><p>initially ztunnel seemed to have been configured to tunnel the sockets (according to extra sockets we saw in netstat)</p><p>when the connector was uninstalled, the client was running for a week with zero interruptions. </p><p>we also noticed in the app's that when the connector was installed the round trip time was 16-31 ms, </p><p>when it wasn't installed the RTT was 0-1. </p><p>i'm not exactly sure how the connector is configured, but i suspect in some cases the connector holds the traffic from reaching the client.</p><p>in some cases IT said the connector has whitelisted the app, where we didn't see any tunnel sockets, but even then the RTT time was around 15ms as though the connector was still involved in the socket somehow. </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AXPhSICQ1/zscalerterraformer-import-not-working","lastmod":"2024-07-08T06:59:08.000Z","id":"0D54u0000AXPhSICQ1"} -->
## zscaler-terraformer import not working

- Source: https://community.zscaler.com/s/question/0D54u0000AXPhSICQ1/zscalerterraformer-import-not-working
- Type: Q&A
- Posted: 2024-07-07T15:03:19.000Z
- Last activity: 2024-07-08T06:59:08.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi team, </p><p>I was wrongly deleted the same thread. FYI, issue got fixed after followups the steps mentioned here, </p><p>https://community.zscaler.com/s/question/0D54u00009iYQv6CAG/zscaler-terraformer-migration-tool-v030-june-21-2023. </p><p>https://github.com/zscaler/zscaler-terraformer</p><p><br></p><p>I missed to setup environment variables. Hope this would help you if anyone looking for the same. </p><p><br></p><p>Regards</p><p>Ramesh M</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AXSy31CQD/zscaler-terraformer-tool-v130-july-12-2024","lastmod":"2024-07-25T11:47:00.000Z","id":"0D54u0000AXSy31CQD"} -->
## Zscaler Terraformer Tool - v1.3.0 (July, 12 2024)

- Source: https://community.zscaler.com/s/question/0D54u0000AXSy31CQD/zscaler-terraformer-tool-v130-july-12-2024
- Type: Q&A
- Posted: 2024-07-15T04:07:16.000Z
- Last activity: 2024-07-25T11:47:00.000Z
- Answers: 1
- Likes: 5
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><span style="font-size: 16px;">Zscaler Terraformer Tool - v1.3.0 (July, 12 2024)</span></p><p><br></p><p><span style="font-size: 16px;">Enhancements</span></p><ul><li><a href="https://github.com/zscaler/zscaler-terraformer/pull/213" rel="noopener noreferrer" target="_blank" style="font-size: 16px;"><strong>PR #213</strong></a><strong style="font-size: 16px;"> </strong><span style="font-size: 16px;">- The tool now creates a outputs.tf file containing the id export of each individual exported resource.</span></li><li><a href="https://github.com/zscaler/zscaler-terraformer/pull/213" rel="noopener noreferrer" target="_blank" style="font-size: 16px;"><strong>PR #213</strong></a><strong style="font-size: 16px;"> </strong><span style="font-size: 16px;">- The tool now displays a message when the resource import is successful.</span></li><li><a href="https://github.com/zscaler/zscaler-terraformer/pull/213" rel="noopener noreferrer" target="_blank" style="font-size: 16px;"><strong>PR #213</strong></a><strong style="font-size: 16px;"> </strong><span style="font-size: 16px;">- The following new flag have been introduced </span><strong style="font-size: 16px;">--supported-resources</strong><span style="font-size: 16px;">. When using the following syntax: </span><strong style="font-size: 16px;">zscaler-terraformer --supported-resources="zpa"</strong><span style="font-size: 16px;"> or </span><strong style="font-size: 16px;">zscaler-terraformer --supported-resources="zia"</strong><span style="font-size: 16px;"> a list of all current supported resources is displayed in table format.</span></li><li><a href="https://github.com/zscaler/zscaler-terraformer/pull/213" rel="noopener noreferrer" target="_blank" style="font-size: 16px;"><strong>PR #213</strong></a><strong style="font-size: 16px;"> </strong><span style="font-size: 16px;">- The tool now displays a warning message when the imported resource contain attributes that may carry sensitive values i.e passwords. Notice that the value is not included in the HCL code for security reasons.</span></li><li><a href="https://github.com/zscaler/zscaler-terraformer/pull/213" rel="noopener noreferrer" target="_blank" style="font-size: 16px;"><strong>PR #213</strong></a><strong style="font-size: 16px;"> </strong><span style="font-size: 16px;"> - Re-introduced support for the import of the following resources:</span><ul><li><span style="font-size: 16px;">zpa_application_segment_browser_access</span></li><li><span style="font-size: 16px;">zpa_application_segment_inspection</span></li><li><span style="font-size: 16px;">zpa_application_segment_pra</span></li></ul></li><li><a href="https://github.com/zscaler/zscaler-terraformer/pull/213" rel="noopener noreferrer" target="_blank" style="font-size: 16px;"><strong>PR #213</strong></a><strong style="font-size: 16px;"> </strong><span style="font-size: 16px;"> - Introduced support for the import of the following ZPA Privileged Remote Access resources:</span><ul><li><span style="font-size: 16px;">zpa_pra_approval_controller</span></li><li><span style="font-size: 16px;">zpa_pra_console_controller</span></li><li><span style="font-size: 16px;">zpa_pra_credential_controller</span></li><li><span style="font-size: 16px;">zpa_pra_portal_controller</span></li></ul></li><li><a href="https://github.com/zscaler/zscaler-terraformer/pull/213" rel="noopener noreferrer" target="_blank" style="font-size: 16px;"><strong>PR #213</strong></a><strong style="font-size: 16px;"> </strong><span style="font-size: 16px;">- Introduced support for the import of the following Cloud Browser Isolation resources:</span><ul><li><span style="font-size: 16px;">zpa_cloud_browser_isolation_banner</span></li><li><span style="font-size: 16px;">zpa_cloud_browser_isolation_certificate</span></li><li><span style="font-size: 16px;">zpa_cloud_browser_isolation_external_profile</span></li></ul></li></ul><p><strong style="font-size: 16px;">Bug Fixes</strong></p><ul><li><a href="https://github.com/zscaler/zscaler-terraformer/pull/213" rel="noopener noreferrer" target="_blank" style="font-size: 16px;"><strong>PR #213</strong></a><strong style="font-size: 16px;"> </strong><span style="font-size: 16px;"> - Fixed authentication methods to support both environment variables and inline credentials.</span></li><li><a href="https://github.com/zscaler/zscaler-terraformer/pull/213" rel="noopener noreferrer" target="_blank" style="font-size: 16px;"><strong>PR #213</strong></a><strong style="font-size: 16px;"> </strong><span style="font-size: 16px;"> - Fixed importing of ZIA resource zia_dlp_web_rules attribute blocks:</span><ul><li><span style="font-size: 16px;">icap_server</span></li><li><span style="font-size: 16px;">notification_templates</span></li><li><span style="font-size: 16px;">auditor</span></li></ul></li><li><a href="https://github.com/zscaler/zscaler-terraformer/pull/213" rel="noopener noreferrer" target="_blank" style="font-size: 16px;"><strong>PR #213</strong></a><strong style="font-size: 16px;"> </strong><span style="font-size: 16px;">- Fixed importing of ZPA resource </span><strong style="font-size: 16px;">zpa_inspection_custom_controls</strong><span style="font-size: 16px;"> rules block.</span></li></ul><p><strong style="font-size: 16px;">Deprecations</strong></p><ul><li><a href="https://github.com/zscaler/zscaler-terraformer/pull/213" rel="noopener noreferrer" target="_blank" style="font-size: 16px;"><strong>PR #213</strong></a><strong style="font-size: 16px;"> </strong><span style="font-size: 16px;"> - Deprecated the following ZIA resources:</span><ul><li><span style="font-size: 16px;">zia_user_management</span></li><li><span style="font-size: 16px;">zia_admin_users</span></li></ul></li><li><a href="https://github.com/zscaler/zscaler-terraformer/pull/213" rel="noopener noreferrer" target="_blank" style="font-size: 16px;"><strong>PR #213</strong></a><strong style="font-size: 16px;"> </strong><span style="font-size: 16px;">- Deprecated support to authentication via credentials.json file. The Tool now supports environment variables and inline based credentials. See README for further details.&nbsp;</span></li></ul>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Ac5mxnCQA/terraform-byo-nat-gateway","lastmod":"2024-09-18T19:21:41.000Z","id":"0D54u0000Ac5mxnCQA"} -->
## Terraform BYO NAT Gateway

- Source: https://community.zscaler.com/s/question/0D54u0000Ac5mxnCQA/terraform-byo-nat-gateway
- Type: Q&A
- Posted: 2024-09-17T19:56:51.000Z
- Last activity: 2024-09-18T19:21:41.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello, </p><p>I'm thinking of using this script for deploying cloud connectors in Azure.</p><p><a href="https://github.com/zscaler/terraform-azurerm-cloud-connector-modules/blob/v0.6.0/examples/cc_lb/terraform.tfvars" rel="noopener noreferrer" target="_blank">terraform-azurerm-cloud-connector-modules/examples/cc_lb/terraform.tfvars at v0.6.0 · zscaler/terraform-azurerm-cloud-connector-modules · GitHub</a></p><p><br></p><p>We use firewalls for NATing to the internet, not a dedicated Nat Gateway service. Will this be fine to use the firewall names for the byo_nat_gw_names and it will create the NAT policy?</p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Ac91ixCQA/mip-microsoft-information-protection-integration-api-permission","lastmod":"2024-11-07T06:36:11.000Z","id":"0D54u0000Ac91ixCQA"} -->
## MIP (Microsoft information protection) Integration - API Permission

- Source: https://community.zscaler.com/s/question/0D54u0000Ac91ixCQA/mip-microsoft-information-protection-integration-api-permission
- Type: Q&A
- Posted: 2024-09-24T13:21:17.000Z
- Last activity: 2024-11-07T06:36:11.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Scenario: I am currently documenting configuration procedure to implement MIP (Microsoft Information Protection) with Zscaler. I prefer to go with least privilege API permission so going with 'custom' setup (instead of ZScaler Defined) which require Azure App Registration with required API Application permission.</p><p><br></p><p><img src="/Zenith/sfc/servlet.shepherd/version/download/0684u00000jU9kL?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p><strong>Question:</strong></p><p><br></p><p><span style="font-family: &quot;AA Smart Sans&quot;;">There is permission I discovered that the 'Informationprotectionpolicy.read.all' API is now deprecated and it's not the great idea to grant permission to deprecated API.</span></p><p><br></p><p><span style="font-family: &quot;AA Smart Sans&quot;;">Can someone (who has adopted 'Customized' approach) or from engineering team guide me required replacement API permission that we need to grant at Azure App Registration? </span></p><p><span style="font-family: &quot;AA Smart Sans&quot;;">&nbsp;</span></p><p><span style="font-family: &quot;AA Smart Sans&quot;;">I have included details regarding the deprecation below for your reference:</span></p><p><span style="font-family: &quot;AA Smart Sans&quot;;">&nbsp;</span></p><p><a href="https://learn.microsoft.com/en-us/graph/api/resources/informationprotectionlabel?view=graph-rest-beta" rel="noopener noreferrer" target="_blank" style="font-family: &quot;AA Smart Sans&quot;;">informationProtectionLabel resource type (deprecated) - Microsoft Graph beta | Microsoft Learn</a></p><p><span style="font-family: &quot;AA Smart Sans&quot;;">&nbsp;</span></p><p>In more, if above deprecated API permission is not used or to have replaced with another valid API permission then documentation here requires update: <a href="https://help.zscaler.com/zia/authorizing-custom-zscaler-connector-microsoft-applications#api-permissions-section" rel="noopener noreferrer" target="_blank">Authorizing a Custom Zscaler Connector for Microsoft Applications | Zscaler</a></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AdqyU2CQI/zscaler-one-api","lastmod":"2024-11-18T09:12:16.000Z","id":"0D54u0000AdqyU2CQI"} -->
## Zscaler One API

- Source: https://community.zscaler.com/s/question/0D54u0000AdqyU2CQI/zscaler-one-api
- Type: Q&A
- Posted: 2024-11-04T08:04:56.000Z
- Last activity: 2024-11-18T09:12:16.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Anyone used Zscaler One API to integrate or even create an automation task using a third party tool?</p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000cqsN20AI/api-query-to-retrieve-users-and-roles-details","lastmod":"2025-08-14T08:45:29.000Z","id":"0D5PJ00000cqsN20AI"} -->
## API query to retrieve users and roles details

- Source: https://community.zscaler.com/s/question/0D5PJ00000cqsN20AI/api-query-to-retrieve-users-and-roles-details
- Type: Q&A
- Posted: 2025-08-12T14:24:15.000Z
- Last activity: 2025-08-14T08:45:29.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p><br></p><p>One of my customer is looking to retrieve the users and groups through API queries.</p><p>He did not find yet on how to do it.</p><p>I checked on the online help but couldn't find anything relevant</p><p>Would someone have some documentation ?</p><p><br></p><p>Kr,</p><p>Luca</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000e3StA0AU/oneapi-login-example-for-powershell","lastmod":"2025-09-03T09:25:25.000Z","id":"0D5PJ00000e3StA0AU"} -->
## OneAPI login example for Powershell>

- Source: https://community.zscaler.com/s/question/0D5PJ00000e3StA0AU/oneapi-login-example-for-powershell
- Type: Q&A
- Posted: 2025-09-02T18:33:40.000Z
- Last activity: 2025-09-03T09:25:25.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Any examples of a basic Powershell requests to https://.zslogin.net/oauth2/v1/token ?</p><p><br></p><p>I'm making the request and getting back an HTTP 200 but not getting a JSON object with token (as shown in the docs).</p><p>https://help.zscaler.com/oneapi/getting-started</p><p><br></p><p>Instead, I get a string reply that looks like this:</p><p><br></p><p>```</p><p>&nbsp;&nbsp;&nbsp;&nbsp;portalLink: null,</p><p>&nbsp;&nbsp;&nbsp;&nbsp;signup: null,</p><p>&nbsp;&nbsp;&nbsp;&nbsp;userSignup: null,</p><p>&nbsp;&nbsp;&nbsp;&nbsp;userSso: false,</p><p>&nbsp;&nbsp;&nbsp;&nbsp;fidoReset: null,</p><p>&nbsp;&nbsp;&nbsp;&nbsp;pwdReset: null,</p><p>&nbsp;&nbsp;&nbsp;&nbsp;secondFactorReset: null,</p><p>&nbsp;&nbsp;&nbsp;&nbsp;enableFido: true,</p><p>&nbsp;&nbsp;&nbsp;&nbsp;enable2fa: true,</p><p>&nbsp;&nbsp;&nbsp;&nbsp;enableEmail: false,</p><p>&nbsp;&nbsp;&nbsp;&nbsp;fromZcc: false,</p><p>&nbsp;&nbsp;&nbsp;&nbsp;secondFactorEnroll: null,</p><p>&nbsp;&nbsp;&nbsp;&nbsp;allowMfaSkip: null,</p><p>&nbsp;&nbsp;&nbsp;&nbsp;mfaGraceUntil: null,</p><p>&nbsp;&nbsp;&nbsp;&nbsp;showAL: null,</p><p>&nbsp;&nbsp;&nbsp;&nbsp;oauth2SuccessRedirectUri: null,</p><p>&nbsp;&nbsp;&nbsp;&nbsp;showPwd: null,</p><p>&nbsp;&nbsp;&nbsp;&nbsp;authSource: null,</p><p>&nbsp;&nbsp;&nbsp;};</p><p><br></p><p>```</p><p><br></p><p>Example code:</p><p>```</p><p>$loginbody = @{</p><p>&nbsp; &nbsp; &nbsp; &nbsp; grant_type = "client_credentials"</p><p>&nbsp; &nbsp; &nbsp; &nbsp; audience = $ZscalerOneAPIRoot -replace "\/$","" &nbsp;# Remove trailing slash</p><p>&nbsp; &nbsp; &nbsp; &nbsp; client_id = $ZsloginApiCred.UserName</p><p>&nbsp; &nbsp; &nbsp; &nbsp; client_secret = $ZsloginApiCred.GetNetworkCredential().Password</p><p>} | ConvertTo-Json</p><p><br></p><p>$zOauthUrl = "{0}oauth2/v1/token" -f $ZsloginURL</p><p><br></p><p>try{</p><p># &nbsp; &nbsp;if($ret = Invoke-RestMethod -Uri $zOauthUrl -Method Post -Body $loginbody -ContentType 'application/json') {</p><p>&nbsp; &nbsp; if($ret = Invoke-WebRequest -Uri $zOauthUrl -Method Post -Body $loginbody -ContentType 'application/json') {</p><p>```</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000rhLsY0AU/microsoft-sentinel-playbooks-oneapi-integration","lastmod":"2026-04-30T10:21:36.000Z","id":"0D5PJ00000rhLsY0AU"} -->
## Microsoft Sentinel Playbooks - OneAPI Integration

- Source: https://community.zscaler.com/s/question/0D5PJ00000rhLsY0AU/microsoft-sentinel-playbooks-oneapi-integration
- Type: Q&A
- Posted: 2026-03-26T21:01:35.000Z
- Last activity: 2026-04-30T10:21:36.000Z
- Answers: 0
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Is it possible to configure Sentinel Playbooks using OneAPI? The guide below has steps for API setup on the ZIA portal, but would prefer to use the new OneAPI instead via Z-Identity. Cloud NSS feeds already setup and working.</p><p><br></p><p>https://help.zscaler.com/zscaler-technology-partners/zscaler-and-microsoft-sentinel-deployment-guide</p>
<!-- /ZS-POST -->
