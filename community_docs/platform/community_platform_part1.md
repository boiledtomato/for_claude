# Zscaler Zenith Community — Platform / 認証 / 管理 / ログ (part 1)

Source: https://community.zscaler.com
Generated: 2026-08-17 02:18 UTC
Posts in this file: 209

> これはユーザー投稿のコミュニティフォーラムの内容であり、Zscaler の公式ドキュメントではない。

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evimWCAQ/cloud-apps-and-sso","lastmod":"2023-05-31T08:13:37.000Z","id":"0D54u00009evimWCAQ"} -->
## Cloud Apps and SSO

- Source: https://community.zscaler.com/s/question/0D54u00009evimWCAQ/cloud-apps-and-sso
- Type: Q&A
- Posted: 2021-11-11T13:31:28.000Z
- Last activity: 2023-05-31T08:13:37.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Looking to see which applications on corporate machines are using cloud services via SSO.

Is there a way to run a report or view in the logs?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evkf9CAA/dns-cache-poisoning-and-dnssec","lastmod":"2023-07-06T11:44:34.000Z","id":"0D54u00009evkf9CAA"} -->
## DNS cache poisoning and DNSSEC

- Source: https://community.zscaler.com/s/question/0D54u00009evkf9CAA/dns-cache-poisoning-and-dnssec
- Type: Q&A
- Posted: 2022-04-22T15:47:30.000Z
- Last activity: 2023-07-06T11:44:34.000Z
- Answers: 0
- Likes: 6
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

The Zscaler Trusted Resolver (ZTR) clusters located in each data center along with the DNS Control capability of Advanced Cloud Firewall resist DNS poisoning is two main ways:

1. If the customer chooses to DNAT route (and redirect) DNS traffic to our Zscaler Trusted Resolver (ZTR) then we use DNSSEC on the backend for our iterative resolutions. This verifies the authoritative server and domain response when the external authoritative DNS supports. Not all authoritative DNS servers support DNSSEC but the ZTR will always try DNSSEC first. It will do this in an iterative manner and so, for example, get the key for root, then the TLD etc.
2. Regardless of whether our ZTR is used or some 3rd party public DNS is used, DNS Control will check the domain and the IP response for indications that the IP is used for malicious purposes or is categorized in a category that the customer does not want. This will eliminate some bad DNS responses (particularly the “Malicious? category) looking to poison the end user’s cache and detect caches that are poisoned.

We typically hold DNS records in the local cache per data center for the TTL duration.

The diagram below shows both where DNSSEC is employed and the resolver versus transit options enabled by Destination NAT configuration rules in the Firewall Filtering section of the UI console:

[[Screen Shot 2022-04-22 at 11.36.11] Screen Shot 2022-04-22 at 11.36.111852×968 149 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O8ZzAAK)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evlreCAA/pingone-identity-authentication-deployment-guide","lastmod":"2023-05-31T09:08:24.000Z","id":"0D54u00009evlreCAA"} -->
## PingOne Identity / Authentication Deployment Guide

- Source: https://community.zscaler.com/s/question/0D54u00009evlreCAA/pingone-identity-authentication-deployment-guide
- Type: Q&A
- Posted: 2021-01-27T18:36:00.000Z
- Last activity: 2023-05-31T09:08:24.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

This is a PingOne Identity / Authentication Deployment Guide specifically written for deploying Zscaler using PingOne as the IdP. It is a comprehensive guide designed to provide systematic detail for all of the Zscaler services.

**Topics Include:**

• ZIA SAML Authentication
 • ZIA SCIM Provisioning or SAML Auto-Provisioning
 • ZPA SAML Configuration
 • ZPA SCIM Provisioning or SAML Auto-Provisioning

**Optionally:**

• ZIA SAML Authentication for ZIA Administrators
 • ZPA SAML Authentication for ZPA Administrators

**The Guide also Contains:**

• Troubleshooting
 • IWA
 • Appendix of Resources

PingOne Identity / Authentication Deployment Guide:

accounts.google.com
##### [Google Drive: Sign-in](https://accounts.google.com/v3/signin/identifier?dsh=S-1710381742%3A1673533543572228&continue=https%3A%2F%2Fdrive.google.com%2Ffile%2Fd%2F1r86ev9orY1up7NGRXM6oDd03-z3RH4ej%2Fview%3Fusp%3Dsharing&followup=https%3A%2F%2Fdrive.google.com%2Ffile%2Fd%2F1r86ev9orY1up7NGRXM6oDd03-z3RH4ej%2Fview%3Fusp%3Dsharing&passive=1209600&service=wise&flowName=GlifWebSignIn&flowEntry=ServiceLogin&ifkv=AeAAQh4RbCArBlNCFZNQg1IZ-nyBt6asalu9ZBfF5qTmbScBWDZcOaFku-hEfGYnx1WFsAqAcoKv4g)

Access Google Drive with a Google account (for personal use) or Google Workspace account (for business use).
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmU8CAI/video-location-templates-provisioning-templates","lastmod":"2023-05-31T09:08:51.000Z","id":"0D54u00009evmU8CAI"} -->
## [VIDEO] Location Templates / Provisioning Templates

- Source: https://community.zscaler.com/s/question/0D54u00009evmU8CAI/video-location-templates-provisioning-templates
- Type: Q&A
- Posted: 2022-08-10T14:22:01.000Z
- Last activity: 2023-05-31T09:08:51.000Z
- Answers: 1
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Locations are one of the staples to policy enforcement within the Zero Trust Exchange. They identify the various networks from which your organization sends its traffic and, likewise, what sort of scrutiny the traffic should be subjected to. Cloud locations, such as AWS VPCs and Microsoft Azure VNets are no different. However, since Cloud Connector appliances dynamically learn of cloud locations and automatically populate ZIA and ZPA dashboards, a flexible option must exist that allows administrators to templatize the onboarding process - making it easier to ensure both existing and new cloud workloads adhere to organizational policy.

Furthermore, pinning newly learned cloud locations to the correct Location Template and its defined attributes is the job of a Provisioning Template. As part of the initialization process, Cloud Connector appliances get pre-configured to dial home using the Provisioning URL generated by the Provisioning Template. This Provisioning URL not only instructs the appliance on how to dial home, but also defines what attributes should be enabled or disabled on the Locations serviced by that appliance. Hence, multiple Provisioning Templates and URLs, as well as Location Templates, can be created for various portions of a multi-cloud environment to enable or disable required network services.

In this video, we’ll explore:
 [0:00 to 0:58] What are Location Templates?
 [0:58 to 1:14] How are Location Templates configured?
 [1:14 to 2:10] What is a Provisioning Template? And how are they configured?
 [2:10 to 2:50] What are the key takeaways?

Transcript
Hello, my name is Aaron and I’m one of the Principal Technical Product Specialists for Zscaler Cloud Workload Protection.
 In this video, we’ll be exploring some of the pre-requisites to deploying Zscaler Cloud Connector - namely, Location and Provisioning Templates.

If you’ve been around Zscaler long enough, you already know about how Locations are used to identify the various networks from which your organization sends its traffic… such as AWS VPCs and Microsoft Azure VNets. When the Zscaler Zero Trust Exchange receives this traffic, it checks whether the traffic is from a known location. If the traffic is from a known location, the service processes the traffic based on the Location’s settings… such as whether the Location has Authentication, Firewall or Bandwidth Control enabled and proceeds accordingly. The Zero Trust Exchange can also apply Location-based policies that you configure and logs network activity by Location.

Zscaler Cloud Connector appliances automatically create Locations based on the Cloud Service Provider networks that they serve. Controlling which features are enabled or disabled for dynamically created Locations is the job of a Location Template.

Location Templates are configured under the Administration menu. Provide a name and, optionally, a Template Prefix. The Template Prefix will be prepended to all Locations this template is attached to in order to help make a Location more easily identifiable. Select the options you wish to enable and click the Save button.

The glue that binds a cloud network to a Location Template and, hence, a Location and its configured attributes is a Provisioning Template. Provisioning Templates. Provisioning Templates are configured under the Administration menu as well. Provide a name and description, then select the Location Template you wish to bind to all appliances registering under this Provisioning Template. Each Provisioning Template you create has its own unique Provisioning URL. Although Provisioning Templates can be shared across many Cloud Connector appliances, they are unique to each Cloud Service Provider… meaning AWS Provisioning Templates are exclusive of Microsoft Azure Provisioning Templates.

As part of the initialization process, Cloud Connector appliances get pre-configured to dial home using the Provisioning URL generated by the Provisioning Template. This Provisioning URL not only instructs the appliance on how to dial home, but also defines what attributes should be enabled or disabled on the Locations serviced by that appliance. Hence, multiple Provisioning Templates and URLs can be created for various portions of a multi-cloud environment to enable to disable required network services.

The cloud Provisioning URL is a prerequisite for deploying the Cloud Connector as a virtual machine (VM) in Amazon Web Services (AWS) and Microsoft Azure as it provides a registration endpoint for initializing Cloud Connector appliances.

To obtain a Provisioning URL, you need to configure Provisioning Template and a Location Template. The Provisioning URL binds the appliance to a Provisioning Template, which further binds the appliance to a Location Template and, ultimately, to a Location and its attributes.

Once created, the Provisioning URL can be used multiple times based on your requirements.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmZUCAY/i-need-a-zscaler-to-output-logs-over-tls-i-cant-use-tcp","lastmod":"2023-05-31T08:13:49.000Z","id":"0D54u00009evmZUCAY"} -->
## I need a zscaler to output logs over TLS, I can't use TCP

- Source: https://community.zscaler.com/s/question/0D54u00009evmZUCAY/i-need-a-zscaler-to-output-logs-over-tls-i-cant-use-tcp
- Type: Q&A
- Posted: 2021-04-30T20:58:02.000Z
- Last activity: 2023-05-31T08:13:49.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I am trying to integrate zScaler from decentralized sites into a central Splunk instance. Each site has their own zScaler purchase with zScaler. So, multiple admins at the various sites will need to configure zScaler to send me logs. However, i cannot accept their logs over TCP. It has it come over TLS. So, what do I need to do to have the zScaler admins on their side setup to be able to output their logs over TLS so that I can ingest it to my splunk instance? To recap, my issue is that I cannot accept the logs over TCP as the integration documentation suggest… i need it over TLS.

Multiple zScaler Customers → TLS → My Load Balancer → My Splunk Instance

Thanks in advance.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmb9CAA/ipv6-enablement","lastmod":"2023-05-31T09:09:00.000Z","id":"0D54u00009evmb9CAA"} -->
## IPv6 Enablement

- Source: https://community.zscaler.com/s/question/0D54u00009evmb9CAA/ipv6-enablement
- Type: Q&A
- Posted: 2020-12-09T03:26:04.000Z
- Last activity: 2023-05-31T09:09:00.000Z
- Answers: 0
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I’m enquiring about when IPv6 will be available for your iOS and Android client apps to use IPv6 natively. I have sen posts and emails saying it is on your Roadmap but some are quite dated.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmcyCAA/unable-to-publish-build-through-visual-studio","lastmod":"2023-07-06T13:12:05.000Z","id":"0D54u00009evmcyCAA"} -->
## Unable to publish build through Visual Studio

- Source: https://community.zscaler.com/s/question/0D54u00009evmcyCAA/unable-to-publish-build-through-visual-studio
- Type: Q&A
- Posted: 2020-05-21T10:11:07.000Z
- Last activity: 2023-07-06T13:12:05.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Unable to publish Vishual Studio code to Azure, prompting to certificate issue.

Certificate error:
 [image]

I have tried adding trusted allow trusted hosts in .pubxml file also imported certificate. Still it is not working.

Publishing build requires any additional ports to be allowed for Azure in Zscaler. Kindly suggest me.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmdhCAA/authentication-with-azure-ad-if-azure-is-not-allowed-by-the-fw","lastmod":"2023-05-31T08:43:05.000Z","id":"0D54u00009evmdhCAA"} -->
## Authentication with Azure AD if Azure is not allowed by the FW

- Source: https://community.zscaler.com/s/question/0D54u00009evmdhCAA/authentication-with-azure-ad-if-azure-is-not-allowed-by-the-fw
- Type: Q&A
- Posted: 2023-03-13T08:45:30.000Z
- Last activity: 2023-05-31T08:43:05.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Community,

this is my first port and I hope I get support from you.
 short question, is it possible to authenticate with Azure although Azure is blocked by the firewalls?
 I read that Zscaler connects directly to O365 and so the MPLS is thus avoided.

Thank you.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmdsCAA/what-is-the-cause-nss-server-state-unhealthy","lastmod":"2025-10-30T00:23:21.000Z","id":"0D54u00009evmdsCAA"} -->
## What is the cause NSS Server State Unhealthy?

- Source: https://community.zscaler.com/s/question/0D54u00009evmdsCAA/what-is-the-cause-nss-server-state-unhealthy
- Type: Q&A
- Posted: 2023-03-03T03:46:32.000Z
- Last activity: 2025-10-30T00:23:21.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We start create NSS Server because want send log to elastic but i found problem NSS Server state Unhealthy. I want to know have effect to send log to SIEM right? and What is the cause ?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evme6CAA/send-all-policy-configuration-changes-to-splunk","lastmod":"2023-05-31T08:43:21.000Z","id":"0D54u00009evme6CAA"} -->
## Send all policy configuration changes to Splunk

- Source: https://community.zscaler.com/s/question/0D54u00009evme6CAA/send-all-policy-configuration-changes-to-splunk
- Type: Q&A
- Posted: 2023-02-14T21:08:25.000Z
- Last activity: 2023-05-31T08:43:21.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

What is required to get started on sending alerts from Zscaler on Splunk regarding policy configuration changes / audit logs.
 I am fairly new to this and planning to get this sorted for one of my clients.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmeGCAQ/how-to-install-zscaler-ssl-certificate-in-aws-linux-2","lastmod":"2023-05-31T08:12:40.000Z","id":"0D54u00009evmeGCAQ"} -->
## How to install ZScaler SSL certificate in AWS Linux 2

- Source: https://community.zscaler.com/s/question/0D54u00009evmeGCAQ/how-to-install-zscaler-ssl-certificate-in-aws-linux-2
- Type: Q&A
- Posted: 2023-02-05T15:33:36.000Z
- Last activity: 2023-05-31T08:12:40.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

How can I install the certificate in AWS Linux 2? Can I run any one of the below or do I need to perform any additional steps?

`cp ZscalerRootCertificate-2048-SHA256.crt $(openssl version -d | cut -f2 -d \")/certs`

or

`cp ZscalerRootCertificate-2048-SHA256.crt /etc/pki/ca-trust/source/anchors/ && update-ca-trust`

Kindly assist as how to install and which is the better solution.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmelCAA/accessing-internet-in-a-linux-vm-virtualbox-with-zscalaer","lastmod":"2023-05-31T08:43:30.000Z","id":"0D54u00009evmelCAA"} -->
## Accessing Internet in a Linux VM (VirtualBox) with Zscalaer

- Source: https://community.zscaler.com/s/question/0D54u00009evmelCAA/accessing-internet-in-a-linux-vm-virtualbox-with-zscalaer
- Type: Q&A
- Posted: 2023-01-24T12:30:25.000Z
- Last activity: 2023-05-31T08:43:30.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi all,

This is a follow on from this draft: [https://community.zscaler.com/t/virtualbox-linux-vm-cannot-access-internet/14486/11](https://community.zscaler.com/t/virtualbox-linux-vm-cannot-access-internet/14486/11)

I overcame this issue by using PuTTY (psftp) to transfer the Zscaler root certificate into the VM using SSH tunnelling.

Once the root certificate had copied into the linux VM (Ubuntu 22.04), I applied this to the default browser (Firefox) and I then could access the internet.

Breakdown of steps:

1. Install PuTTY
2. Config Ubuntu VM to allow SSH tunnelling from host machine (Note down IP) (Should be very similar to local machine IP)
3. Request Zscaler root certificate from IT Engineer (etc.)
4. Using PSFTP (PuTTY) copy files to VM
5. Apply root certificate in browser certificates section

There are still some issues with certificate verification when running `sudo apt-get update` but I can at least still access the internet!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmeoCAA/about-the-admin-portal-category","lastmod":"2023-06-22T12:18:39.000Z","id":"0D54u00009evmeoCAA"} -->
## About the Admin Portal category

- Source: https://community.zscaler.com/s/question/0D54u00009evmeoCAA/about-the-admin-portal-category
- Type: Q&A
- Posted: 2023-01-20T20:02:18.000Z
- Last activity: 2023-06-22T12:18:39.000Z
- Answers: 0
- Likes: 1
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

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmfKCAQ/nss-server-status-and-logs","lastmod":"2023-05-31T08:43:44.000Z","id":"0D54u00009evmfKCAQ"} -->
## NSS Server status and logs

- Source: https://community.zscaler.com/s/question/0D54u00009evmfKCAQ/nss-server-status-and-logs
- Type: Q&A
- Posted: 2023-01-06T08:35:54.000Z
- Last activity: 2023-05-31T08:43:44.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

Could any one let me know , if you can NSS server IP address of Mgmt and service interface, also the connectivity from NSS to Zscaler cloud and NSS to syslogs via ZIA Admin Portal.

Thanks,
 Pardeep Rawat
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmfLCAQ/use-chrome-instead-of-firefox-during-initial-sso-login","lastmod":"2023-05-31T08:43:44.000Z","id":"0D54u00009evmfLCAQ"} -->
## Use Chrome instead of Firefox during initial SSO login

- Source: https://community.zscaler.com/s/question/0D54u00009evmfLCAQ/use-chrome-instead-of-firefox-during-initial-sso-login
- Type: Q&A
- Posted: 2023-01-05T20:13:50.000Z
- Last activity: 2023-05-31T08:43:44.000Z
- Answers: 9
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are planning our ZIA rollout to the enterprise and have run into a bit of a snag. Our environment is:
 -All MacOS
 -Full remote workforce
 -GSuite as IDP et al
 -Managed Chrome Browser (can only access Google and SSO apps via managed browser)
 -Context aware (device authentication)

As described in Zscaler’s documentation, Firefox opens up to allow the user to authenticate with the IDP (Google). However, because we only allow access from a managed Chrome browser, the user cannot reach the authentication page. Additionally, as the user is not logged in, you cannot go into Chrome and login as all access is blocked.

Is there a way to have the agent open Chrome instead of Firefox? If not, what are out options?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmfXCAQ/about-the-authentication-category","lastmod":"2023-05-31T08:12:48.000Z","id":"0D54u00009evmfXCAQ"} -->
## About the Authentication category

- Source: https://community.zscaler.com/s/question/0D54u00009evmfXCAQ/about-the-authentication-category
- Type: Q&A
- Posted: 2023-01-03T16:24:07.000Z
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

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmfYCAQ/about-the-authentication-category","lastmod":"2023-05-31T08:12:48.000Z","id":"0D54u00009evmfYCAQ"} -->
## About the Authentication category

- Source: https://community.zscaler.com/s/question/0D54u00009evmfYCAQ/about-the-authentication-category
- Type: Q&A
- Posted: 2023-01-03T16:23:34.000Z
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

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmgqCAA/dnssec-behaviour-questions","lastmod":"2023-05-31T08:12:53.000Z","id":"0D54u00009evmgqCAA"} -->
## DNSSEC behaviour questions

- Source: https://community.zscaler.com/s/question/0D54u00009evmgqCAA/dnssec-behaviour-questions
- Type: Q&A
- Posted: 2022-11-30T13:28:43.000Z
- Last activity: 2023-05-31T08:12:53.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

When forwarding DNS traffic to ZIA, how does Zscaler handle DNSSEC and DNSSEC validation?

1. Does the Zscaler Trusted Resolver validate DNSSEC? If so, does it honor the CD Flag to disable DNSSEC validation?
2. Are there any configurable options regarding negative trust anchors for bogus domains? Are negative trust anchors global or per-tenant? In case they are global, is there a list of negative trust anchors we can consult? Does Zscaler add custom negative trust anchors based on individual requests?
3. Is there a way to disable DNSSEC for specific users?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmgsCAA/export-logs-to-splunk","lastmod":"2023-07-06T12:52:48.000Z","id":"0D54u00009evmgsCAA"} -->
## Export logs to splunk

- Source: https://community.zscaler.com/s/question/0D54u00009evmgsCAA/export-logs-to-splunk
- Type: Q&A
- Posted: 2022-11-29T09:05:27.000Z
- Last activity: 2023-07-06T12:52:48.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Good morning,

I need your help, I have to export the logs that I have in the zscaler with the ZIA module to an on premise collector that I have mounted, how do I do this configuration?

Thanks in advance

Best regards
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmh6CAA/maximum-bandwidth-per-user","lastmod":"2023-05-31T09:27:27.000Z","id":"0D54u00009evmh6CAA"} -->
## Maximum bandwidth per user

- Source: https://community.zscaler.com/s/question/0D54u00009evmh6CAA/maximum-bandwidth-per-user
- Type: Q&A
- Posted: 2022-11-21T08:03:01.000Z
- Last activity: 2023-05-31T09:27:27.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

What is the maximum bandwidth per user?
 Where can I find the information? Any license subscription based on bandwidth?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhqCAA/enabling-ad-authentication-for-zscaler-portal-administrators-instead-of-creating-accounts-in-the-portal","lastmod":"2023-07-06T12:54:10.000Z","id":"0D54u00009evmhqCAA"} -->
## Enabling "AD" authentication for Zscaler portal administrators (instead of creating accounts in the portal)

- Source: https://community.zscaler.com/s/question/0D54u00009evmhqCAA/enabling-ad-authentication-for-zscaler-portal-administrators-instead-of-creating-accounts-in-the-portal
- Type: Q&A
- Posted: 2022-10-25T16:34:40.000Z
- Last activity: 2023-07-06T12:54:10.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are trying to enable “AD? authentication for Zscaler portal administrators (instead of creating accounts in the portal), and we’re having a hard time getting it to work.

Please, could you help us ?

We are testing this process : [Admin SAML Configuration Guide for AD FS 3.0 | Zscaler](https://help.zscaler.com/zia/admin-saml-configuration-guide-adfs-3.0)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhvCAA/fedora-36-installation-error-upon-installing-prerequisite-dependencies","lastmod":"2023-05-31T08:12:57.000Z","id":"0D54u00009evmhvCAA"} -->
## Fedora 36 Installation error upon installing Prerequisite Dependencies

- Source: https://community.zscaler.com/s/question/0D54u00009evmhvCAA/fedora-36-installation-error-upon-installing-prerequisite-dependencies
- Type: Q&A
- Posted: 2022-10-19T13:46:01.000Z
- Last activity: 2023-05-31T08:12:57.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Slack 0.0 B/s | 0 B 00:02
 Errors during downloading metadata for repository ‘slack’:

- Curl error (60): SSL peer certificate or SSH remote key was not OK for [https://packagecloud.io/slacktechnologies/slack/fedora/21/x86_64/repodata/repomd.xml](https://packagecloud.io/slacktechnologies/slack/fedora/21/x86_64/repodata/repomd.xml) [SSL certificate problem: unable to get local issuer certificate] Error: Failed to download metadata for repo ‘slack’: Cannot download repomd.xml: Cannot download repodata/repomd.xml: All mirrors were tried Ignoring repositories: slack Last metadata expiration check: 2:10:38 ago on Wed 19 Oct 2022 05:10:05 AM PST. Package net-tools-2.0-0.61.20160912git.fc36.x86_64 is already installed. No match for argument: libqt5dbus5 No match for argument: libqt5core5a No match for argument: libqt5sql5 No match for argument: libqt5sql5-sqlite Error: Unable to find a match: libqt5dbus5 libqt5core5a libqt5sql5 libqt5sql5-sqlite bash: libqt5webchannel5: command not found… bash: libqt5webkit5: command not found…

installation works fine, but it blocks Slack,
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmieCAA/zscaler-multifactor-authentication","lastmod":"2023-05-31T08:44:31.000Z","id":"0D54u00009evmieCAA"} -->
## Zscaler Multi-factor authentication

- Source: https://community.zscaler.com/s/question/0D54u00009evmieCAA/zscaler-multifactor-authentication
- Type: Q&A
- Posted: 2022-09-19T07:15:55.000Z
- Last activity: 2023-05-31T08:44:31.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Everyone,
 is there any native option to activate the multi-factor authentication in ZIA, I didn’t find any option and I need to know why there is no option from Zscaler point of view.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmikCAA/ssl-verifications-by-zscaller-certificate-chain-trust-verification-subca-download","lastmod":"2023-05-31T08:44:36.000Z","id":"0D54u00009evmikCAA"} -->
## SSL verifications by zscaller - Certificate Chain Trust verification - SubCa download

- Source: https://community.zscaler.com/s/question/0D54u00009evmikCAA/ssl-verifications-by-zscaller-certificate-chain-trust-verification-subca-download
- Type: Q&A
- Posted: 2022-09-12T11:54:59.000Z
- Last activity: 2023-05-31T08:44:36.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi there,

1. Can we know how zscaler block network access to websites for which zscaller fail to verify certificate trust ?
2. If the website does not present the SubCa certificate (if it has not been configured to present all the server trust chain certificates but only the server’s certificate), but the CA certificate is referenced in the optional certificate extension Authority Information Access (AIA), does zscaller downloads the subca certificate to verify the chain as Internet browsers does ? (seems no)
3. Once the server’s certificate configuration has been updated, is there something to do to allow zscaller to give back network access to the server ?

Question raised on this post without answers:
 [SSL Inspection Policy and URL Filtering - Cloud Firewall - Zenith (zscaler.com)](https://community.zscaler.com/t/ssl-inspection-policy-and-url-filtering/17441)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjGCAQ/intune-autopilot-and-zscaler","lastmod":"2023-07-06T12:55:46.000Z","id":"0D54u00009evmjGCAQ"} -->
## Intune Autopilot and ZScaler

- Source: https://community.zscaler.com/s/question/0D54u00009evmjGCAQ/intune-autopilot-and-zscaler
- Type: Q&A
- Posted: 2022-08-25T12:57:12.000Z
- Last activity: 2023-07-06T12:55:46.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are trying to Autopilot Intune Azure AD join. We are not having much success with this due to some networking issue. We tried Hybrid Azure AD join and was told that you need VPN access on the device to be successful.

So, we tried the Hybrid Azure AD join from within network and we could not still succeed, as it does nothing. We do have ZScaler ZIA implemented. Unfortunately, we are not sure whether this is causing an issue. But, I was told that when we go through Wi-Fi the ZScaler is not in picture, but still wonder why it would not go through the process of Hybrid Azure join. Just curious I know, I see many people talking about this in ZScaler forum, but is there anyone who has done Hybrid Azure AD successfully from within the network with any kind of ZScaler ZIA configuration.

We have opened up the network as per the network document shown below, but still not successful. When we go through Wireless from home I can see the machine in Azure and in Onprem AD but does not allow me to login.

[https://docs.microsoft.com/en-us/mem/intune/fundamentals/intune-endpoints](https://docs.microsoft.com/en-us/mem/intune/fundamentals/intune-endpoints)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjfCAA/video-nanolog-streaming-service","lastmod":"2023-05-31T09:08:51.000Z","id":"0D54u00009evmjfCAA"} -->
## [VIDEO] Nanolog Streaming Service

- Source: https://community.zscaler.com/s/question/0D54u00009evmjfCAA/video-nanolog-streaming-service
- Type: Q&A
- Posted: 2022-08-10T14:44:12.000Z
- Last activity: 2023-05-31T09:08:51.000Z
- Answers: 0
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Cloud Connector supports the [Nanolog Streaming Service](https://help.zscaler.com/zia/about-nanolog-streaming-service) (NSS) for ZIA use-cases. NSS uses a virtual machine (VM) to stream traffic logs in real time to your Security Information and Event Management (SIEM) system (such as Splunk or ArcSight), enabling real-time alerting and correlation of logs with your other devices. NSS can be configured from the Cloud Connector or ZIA portal, but the NSS infrastructure must be set up as part of a separate workflow/subscription.

In this video, we’ll explore the components involved in NSS as well as its basic configuration:
 [0:00 to 1:41] Overview of NSS
 [1:41 to 4:15] Deploying NSS
 [4:15 to 4:55] Key Takeaways

Transcript
Hello, my name is Aaron and I’m one of the Principal Technical Product Specialists for Zscaler Cloud Workload Protection.

In this video, we’ll discuss how to leverage NSS logging for Zscaler Cloud Connector.

Before we get started, have a look at the Logging Insights and Filtering video within this series. This video will familiarize you with the type of logging collected by Cloud Connector appliances and, hence, what can be made available to NSS for exporting to a SIEM or other event collector.

The Nanolog Streaming Service (NSS) uses a virtual machine (VM) to stream traffic logs in real time from the Zscaler Nanolog to your security information and event management (SIEM) system, such as Splunk or ArcSight, enabling real-time alerting, correlation with the logs of other devices, and long-term local log archival.

Keep in mind that, in the absence of a Cloud Connector, NSS can be used to send ZIA traffic logs to your SIEM. All traffic flowing through ZIA, regardless of how it was delivered there, can be sent to NSS. Cloud Connector simply adds additional data points to this logging - particularly network-level information collected as the traffic was leaving the source cloud - which can be useful when correlating meta events.

Zscaler offers the following NSS subscriptions:

NSS for Web: which streams web and mobile traffic logs.

NSS for Firewall: which streams logs from solutions like Cloud Connector.

Organizations can optionally subscribe to Cloud NSS, which allows direct cloud-to-cloud log streaming. Rather than deploying, managing, and monitoring NSS VMs, you can simply configure an HTTPS API feed that will push logs into the cloud SIEM.

For the purposes of this video, we’ll focus only on NSS for Firewall, however, since (at the creation time of this video) it is the only type supported.

You can configure the Nanolog Streaming Service for Cloud Connector using the ZIA portal. Future revisions will allow you to also configure this functionality from the Cloud Connector portal as well, but for now, in the ZIA portal, navigate to the Administration tab, followed by Nanolog Streaming Service.

Here, we already have a few NSS servers deployed. For sake of example, however, we’ll add another. Click the Deploy NSS Virtual Appliance link. Select NSS for Firewall, Cloud, and Branch Connector. In the remaining fields, enter the information as it pertains to your organization. This will help size the VM appropriately. Select your platform and click the Compute button. The recommended VM specs will be displayed along with a link to download the appropriate image.

Next, click the Add NSS Server button. A dialog box then appears where you can name your new NSS integration. Once complete, download the certificate bundle that appears on the right of your screen.

In the interest of time, this video will not focus on deploying the NSS virtual machine in your hypervisor. You can visit the link on your screen, however, for step-by-step instructions. In a nutshell, when the VM is deployed, you will use its console to set its IP address and import the certificate bundle you just downloaded.

Assuming the certificate has been installed and network connectivity from the VM to the NSS service is good, the integration should transition to a healthy state, as shown here.

Now, we’re ready to add an NSS feed. Click the tab at the top of the screen. Again, in a production environment, you may already have feeds for Firewall and Web as well, but this video will focus on Cloud Connector.

Under the NSS Type dropdown, select NSS for Firewall, Cloud, and Branch Connector.

Cloud Connector will export two types of logs: Session and DNS. Here, we’ll create a Session Log feed. Select your NSS for Firewall VM in the NSS Server dropdown.

Enter the IP or FQDN and port number of the SIEM your NSS VM will export logs to. Remember, the SIEM will need to be configured to listen on these ports as well.

You can choose to rate-limit logs in the next field, though here, we’ll leave it at unlimited. For Log Domain, select Cloud/Branch Connector. Leave the Log Type as Session and change the Session Log Type to “Both Session and Aggregate Logs.?

You have the option of changing the output feed type as well, but in our example, we’ll leave it as CSV.

In the remaining options, you can select the timezone for the logs, as well as create a Filter to narrow down which logs get sent to the SIEM.

Click the Save button to continue.

If applicable, click the Add NSS Feed button again and create a feed for DNS Logs.

The final step is to activate your changes!

– You can use Nanolog Streaming Service (NSS) to export Cloud Connector logs to an external event collector for further processing

– Nanolog Streaming Service uses a VM to capture, aggregate, and export logs from Cloud Connector appliances

– Cloud Connector appliances export both Session and DNS Logs

– NSS can export logs from all types of traffic crossing ZIA, regardless of how the traffic arrived. Cloud Connector simply augments this data with network-level statistics from the source workload and cloud
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjgCAA/video-azure-ad-integration","lastmod":"2023-05-31T09:08:56.000Z","id":"0D54u00009evmjgCAA"} -->
## [VIDEO] Azure AD Integration

- Source: https://community.zscaler.com/s/question/0D54u00009evmjgCAA/video-azure-ad-integration
- Type: Q&A
- Posted: 2022-08-10T14:42:24.000Z
- Last activity: 2023-05-31T09:08:56.000Z
- Answers: 0
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

By default, the Cloud Connector portal provides a simple username and password mechanism as the primary authentication option for admins. However, the Cloud Connector portal also supports SAML version 2.0 and, as such, Zscaler recommends that organizations leverage SAML instead. SAML is a more secure option that allows for integration with multi-factor authentication wherein an admin can log in to the Cloud Connector Portal directly via single sign-on (SSO) by clicking the appropriate application icon within the Microsoft Azure AD portal. In this video, we’ll discuss how to integrate Zscaler Cloud Connector portal with Azure AD.

We’ll explore:
 [0:00 to 0:57] Overview and nuances of using SAML with Cloud Connector portal
 [0:57 to 2:56] Deploying SAML using Azure AD
 [2:56 to 3:11] Testing the integration
 [3:11 to 3:50] Key takeaways

Transcript
Hello, my name is Aaron and I’m one of the Principal Technical Product Specialists for Zscaler Cloud Workload Protection. In this video, we’ll explore how the Zscaler Cloud Connector administration portal can be provisioned with SAML authentication using Microsoft Azure AD.

The Cloud Connector portal supports SAML version 2.0 and above… and while the portal, by default, provides a simple username and password authentication option for admins, Zscaler recommends that organizations leverage SAML instead for authentication. That said, it is also recommended that you have at least one locally defined super admin account with password authentication enabled to ensure access to the Cloud Connector portal even if SAML servers become unreachable.

With SAML authentication, an admin can log in to the Cloud Connector Portal directly via single sign-on (SSO) by clicking the appropriate application icon within the provider’s portal. This feature also enables you to integrate admin authentication with your existing two-factor authentication solution.

From your Azure portal, navigate to Active Directory, followed by the Enterprise Applications blade.

Click the new application button.

Select the Create your own application option… Provide a name for your application, such as Cloud Connector Admin.

Click on the Single Sign-on blade. Enable the SAML option by clicking on the tile.

Under Basic SAML configuration, click the Edit link.

In the Entity ID field, click the Add Identifier link. Input the URL as shown on your screen, replacing the cloud name with your own, such as Zscaler, Zscalerthree, etc. Here, we will use [admin.zscalertwo.net](http://admin.zscalertwo.net).

Under the Reply URL field, click the Add Reply URL. Input the URL as shown on your screen again, replacing the cloud name with your own. Here, we will use [connector.zscalertwo.net/bac-adminsso.do](http://connector.zscalertwo.net/bac-adminsso.do), with an index of 1.

Click the Save button.

If prompted to test, click No, I’ll test later, since we must still configure the Cloud Connector portal.

Optionally, if you’d like to change the username identifier sent to the Cloud Connector portal, you can change it within the Attributes and Claims pane. Here, we will use the default.

Next, in the SAML Signing Certificates pane, download the base64 signing certificate.

In addition, copy the Azure AD Identifier URL to your notepad.

Navigate to the Cloud Connector portal, Administration, Administrator Management.

It’s important to note here that although Azure AD provides authentication for the portal, it does not create accounts automatically within the portal. Hence, you must still create these accounts on this screen in order for them to successfully authenticate. Here, our admin account has already been pre-created.

Click the Administrator Management tab.

Click the upload link to upload your Azure AD signing certificate.

Next, add in the Azure AD Identifier URL under the Issuer field.

Click to enable SAML authentication, followed by the save button.

Then, activate the change.

Before we test, let’s head back into the Azure portal to assign our app to our users.

Now, let’s test! From the [myapps.microsoft.com](http://myapps.microsoft.com) dashboard, click on the Cloud Connector Admin icon. You should be redirected to the Cloud Connector portal.

Zscaler highly recommends implementing SAML authentication for Cloud Connector portal administrators

Be sure to leave at least one password-enabled administrator account to provide access to the portal, should SAML be unavailable

The Cloud Connector portal supports SAML authentication v2.0 and can easily integrate with Microsoft Azure AD

SAML providers do not automatically provision accounts within the Cloud Connector portal. The administrator must configure an account name to match the IdP before the user can successfully log in via SAML
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjhCAA/video-okta-integration","lastmod":"2023-05-31T09:08:53.000Z","id":"0D54u00009evmjhCAA"} -->
## [VIDEO] Okta Integration

- Source: https://community.zscaler.com/s/question/0D54u00009evmjhCAA/video-okta-integration
- Type: Q&A
- Posted: 2022-08-10T14:40:09.000Z
- Last activity: 2023-05-31T09:08:53.000Z
- Answers: 0
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

By default, the Cloud Connector portal provides a simple username and password mechanism as the primary authentication option for admins. However, the Cloud Connector portal also supports SAML version 2.0 and, as such, Zscaler recommends that organizations leverage SAML instead. SAML is a more secure option that allows for integration with multi-factor authentication wherein an admin can log in to the Cloud Connector Portal directly via single sign-on (SSO) by clicking the appropriate application icon within the Okta portal. In this video, we’ll discuss how to integrate Zscaler Cloud Connector portal with Okta.

We’ll explore:
 [0:00 to 0:57] Overview and nuances of using SAML with Cloud Connector portal
 [0:57 to 3:18] Deploying SAML using Okta
 [3:18 to 3:32] Testing the integration
 [3:32 to 4:13] Key takeaways

Transcript
Hello, my name is Aaron and I’m one of the Principal Technical Product Specialists for Zscaler Cloud Workload Protection. In this video, we’ll explore how the Zscaler Cloud Connector administration portal can be provisioned with SAML authentication using Okta as the Identity Provider.

The Cloud Connector portal supports SAML version 2.0 and above… and while the portal, by default, provides a simple username and password authentication option for admins, Zscaler recommends that organizations leverage SAML instead for authentication. That said, it is also recommended that you have at least one locally defined super admin account with password authentication enabled to ensure access to the Cloud Connector portal even if SAML servers become unreachable.

With SAML authentication, an admin can log in to the Cloud Connector Portal directly via single sign-on (SSO) by clicking the appropriate application icon within the Okta portal. This feature also enables you to integrate admin authentication with your existing multi-factor authentication solution.

From the Okta portal, navigate to the Applications tab and click on the Applications link.

Click the Create App Integration button and select SAML 2.0…

Provide a name and choose a logo, then click next…

For the Single Sign-on URL, input the URL as shown on your screen, replacing the cloud name with your own, such as Zscaler, Zscalerthree, etc. Here, we will use [https://connector.zscalertwo.net/bac-adminsso.do](https://connector.zscalertwo.net/bac-adminsso.do)

Just below that, in the Audience URI, you’ll input a similar address. Here we’ll enter [admin.zscalertwo.net](http://admin.zscalertwo.net), but again, replace the cloud name with that of your own

The next three dropdown menus define how the username will be presented to the Cloud Connector portal. We’ll leave them at default, but feel free to change them if you’d like to manipulate how that value is passed from Okta to the Cloud Connector portal during login.

Click the next button to proceed…

If prompted, choose the radio button “I’m an Okta customer adding an internal app…? and click the finish button.

Next, we’ll assign this new application to one of our users under the Assignments tab…

Click the Assign button. Under normal circumstances, you might assign this application to groups of users, such as groups of admins. In our demo, we’ll assign it to a single user…

As a last step in the Okta portal, we need to capture a few items to import into our Cloud Connector portal. Navigate to the Sign-on tab.

Scroll down and click on the “View SAML Setup Instructions? button.

Copy the Identity Provider Issuer URL provided and download the X.509 certificate. Note that, by default, Okta names their certificates with a .cert extension. The Cloud Connector portal accepts extensions in .cer or .pem only, so you will need to rename the extension of this file.

Navigate to the Cloud Connector portal… Administration… then Administrator Management.

It’s important to note here that although Okta provides authentication for the portal, it does not create accounts automatically within the portal. Hence, you must still create admin accounts on this screen in order for them to successfully authenticate. Here, our admin account has already been pre-created.

Click the Administrator Management tab.

Click the upload link to upload your Okta X.509 certificate.

Next, provide the Okta Issuer URL you copied from the Okta portal.

Click to enable SAML authentication, followed by the save button.

Then, activate the change.

As a test, from the user’s Okta dashboard, click on the Cloud Connector Admin icon. You should be redirected to the Cloud Connector portal.

Zscaler highly recommends implementing SAML authentication for Cloud Connector portal administrators

Be sure to leave at least one password-enabled administrator account to provide access to the portal, should SAML be unavailable

The Cloud Connector portal supports SAML authentication v2.0 and can easily integrate with Okta as an Identity Provider

SAML providers do not automatically provision accounts within the Cloud Connector portal. The administrator must configure an account name to match the IdP before the user can successfully log in via SAML
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmkECAQ/using-browser-integrated-windows-authentication-with-okta-and-zscaler","lastmod":"2023-07-06T12:52:13.000Z","id":"0D54u00009evmkECAQ"} -->
## Using browser Integrated Windows Authentication with Okta and ZScaler

- Source: https://community.zscaler.com/s/question/0D54u00009evmkECAQ/using-browser-integrated-windows-authentication-with-okta-and-zscaler
- Type: Q&A
- Posted: 2022-07-26T15:08:42.000Z
- Last activity: 2023-07-06T12:52:13.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We’ve been using Okta AD Agents for end-users authentication for a while now. We would like the users to have less friction during sign-in process to ZScaler.
 Apparently there is browser feature called “Integrated Windows Authentication? which could be used with Okta Agentless Desktop Single Sign-on. It sounds that it could match our use case where users sign-in into Citrix and then access the Internet via ZScaler.
 Has anyone used this to authenticate users to ZScaler?
 If so any feedback is appreciated.
 Thanks,
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmknCAA/zscaler-idp-authentication-issue","lastmod":"2023-07-06T12:57:28.000Z","id":"0D54u00009evmknCAA"} -->
## Zscaler IDP authentication issue

- Source: https://community.zscaler.com/s/question/0D54u00009evmknCAA/zscaler-idp-authentication-issue
- Type: Q&A
- Posted: 2022-07-11T07:26:57.000Z
- Last activity: 2023-07-06T12:57:28.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

for POC purpose, We tried to integrate ZIA with keycloak IDP provider. but We are facing some error while tried to login with the test user in ZCC.

[[image] image743×554 18 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O524AAC)

We searched the error code we found this
[[image] image1075×410 52.3 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O82QAAS)

We checked the both end it seems to be fine.
Kindly suggest team to overcome this issue.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmktCAA/biometric-authentication-issue","lastmod":"2023-05-31T08:45:22.000Z","id":"0D54u00009evmktCAA"} -->
## Biometric authentication issue

- Source: https://community.zscaler.com/s/question/0D54u00009evmktCAA/biometric-authentication-issue
- Type: Q&A
- Posted: 2022-06-29T06:04:28.000Z
- Last activity: 2023-05-31T08:45:22.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

One of our user having access of govt website. They using the biometric authentication login those websites. Biometric device is connected via USB. They using tunnel 1.0 forwarding method. Without Zscaler it work fine. while turning on we facing error. we checked the logs it seems to fine.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmlICAQ/in-case-of-multiple-idps-what-idp-will-be-used-for-authentication","lastmod":"2023-07-06T12:58:08.000Z","id":"0D54u00009evmlICAQ"} -->
## In case of multiple idps what idp will be used for authentication?

- Source: https://community.zscaler.com/s/question/0D54u00009evmlICAQ/in-case-of-multiple-idps-what-idp-will-be-used-for-authentication
- Type: Q&A
- Posted: 2022-06-15T12:16:51.000Z
- Last activity: 2023-07-06T12:58:08.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,
 I’m working on a project migration from adfs to azure ad, and I have a few questions.
 First, we have ADFS as the default idp for all the domains on any location and I made a test domain uses azure AD as idp now when I authenticate with the test account I can not determine which idp i’m using.
 does the authentication is done by numeric order (1 is Azure AD) or it will use the default?
 and do i need to exclude the test domain from ADFS?
 and is there any way i can test to verify that the integration is done successfully?
 Thanks in advance ,
 Best Regards.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmmGCAQ/applications-with-independent-certificate-stores-and-environmental-variables","lastmod":"2023-05-31T08:45:50.000Z","id":"0D54u00009evmmGCAQ"} -->
## Applications with independent certificate stores and environmental variables

- Source: https://community.zscaler.com/s/question/0D54u00009evmmGCAQ/applications-with-independent-certificate-stores-and-environmental-variables
- Type: Q&A
- Posted: 2022-05-05T19:42:11.000Z
- Last activity: 2023-05-31T08:45:50.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello, I am tryin to get some applications to work that do not use the ms cert store and env settings. Some of the applications that fit into this are Python, Docker, Firefox, Azure Cli, CYGWIN, and many more. The main one I am working on to work now is the Azure cli. I have followed the suggestion here ([https://docs.microsoft.com/en-us/cli/azure/use-cli-effectively](https://docs.microsoft.com/en-us/cli/azure/use-cli-effectively)) to configure it to use the proxy but can not figure out how to make this work with a pac file. I want to try and use the HTTP_PROXY settings, and also try to set the netsh winhttp set proxy : setting. The problem is again how do I tell it to use a PAC file? I know this is not windows support; but I am hoping someone else has been able to get the azcli working from behind zscaler with a pac file.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmmLCAQ/prioritize-ipv4-over-ipv6","lastmod":"2025-08-22T07:27:15.000Z","id":"0D54u00009evmmLCAQ"} -->
## Prioritize IPv4 over IPv6

- Source: https://community.zscaler.com/s/question/0D54u00009evmmLCAQ/prioritize-ipv4-over-ipv6
- Type: Q&A
- Posted: 2022-05-03T12:10:41.000Z
- Last activity: 2025-08-22T07:27:15.000Z
- Answers: 7
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We’ve come across a URL which is accessible by some users but blocked for others. When the website is blocked, it shows in the logs but when the website is accessible, there are no logs in the portal. All users are assigned the same App Profile and rules. After performing and reviewing the packet capture on the machine where the website is accessible, traffic is using IPv6.

We performed a quick test by enabling the "Prioritize IPv4 over IPv6’ option in the App Profile, and now traffic is identified and blocked accordingly.

Wanted to know if others have this option enabled and if so, did you experience any other issues? Just trying to see if this could cause some other unforeseen issues. We are running ZCC 3.6.1.26 and soon to be rolling out 3.7.2.18.

Thanks

[image]
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmmWCAQ/untrusted-server-certs-ocsp-revocation-check-practical-settings","lastmod":"2023-05-31T08:44:42.000Z","id":"0D54u00009evmmWCAQ"} -->
## Untrusted Server Certs & OCSP Revocation Check - Practical Settings?

- Source: https://community.zscaler.com/s/question/0D54u00009evmmWCAQ/untrusted-server-certs-ocsp-revocation-check-practical-settings
- Type: Q&A
- Posted: 2022-04-26T19:13:07.000Z
- Last activity: 2023-05-31T08:44:42.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

See topic. Our SSL Inspection policy is currently configured to block untrusted server certificates and to perform OCSP Revocation Checks. We are seeing quite a few blocks in our logs for large companies such as FedEx, PayPal, etc. w/ the tag " Access Denied Due To Bad Server Certificate".

Are our settings too aggressive to be practical? Do most customers disable one or both of the two settings to avoid playing whack-a-mole with whitelisting every day?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmnSCAQ/ad-group-membership-not-reported-in-zscloud-admin-portale","lastmod":"2023-05-31T08:45:44.000Z","id":"0D54u00009evmnSCAQ"} -->
## AD group membership not Reported in Zscloud admin portale

- Source: https://community.zscaler.com/s/question/0D54u00009evmnSCAQ/ad-group-membership-not-reported-in-zscloud-admin-portale
- Type: Q&A
- Posted: 2022-03-21T13:42:39.000Z
- Last activity: 2023-05-31T08:45:44.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi, we have discovered that on Zscaler admin portal there are lots of user that are missing group membership. We use azure ad with SCIM. Into Azure AD, the group are reported correctly but not for all the users into Zscaler admin portal. why? what is the issue? thank you
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmnWCAQ/device-removal-pending","lastmod":"2023-07-06T12:53:00.000Z","id":"0D54u00009evmnWCAQ"} -->
## Device Removal Pending

- Source: https://community.zscaler.com/s/question/0D54u00009evmnWCAQ/device-removal-pending
- Type: Q&A
- Posted: 2022-03-17T11:27:00.000Z
- Last activity: 2023-07-06T12:53:00.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

I’m looking for some guidance on the device cleanup. We have a number of devices in removal-pending, according to the documentation the device enters this state when an admin soft removes the device as opposed to force removing a device.

We’re not aware of any admins doing this, but yet devices have entered this state. After some further testing it seems the devices also enter this state if the user associated with the device has been marked as disabled or removed from AD. At this point all devices associated with the user are moved into the removal-pending state.

There are some cases where neither or the above applies and the devices are still in removal-pending.

My first question is how devices transition into removal-pending if they’ve not been soft removed by an admin and the account is still active in AD?

My second question is in relation to the second option below “Automatically Force Remove Inactive Devices?. Is this referring to the clean-up of devices in removal-pending? It’s unclear as the tooltip describes it force removing inactive devices, I understood a “force? remove was to remove a device that already has a state “removed? and that’s what the last option is for.

And lastly, when does a device move into an inactive state? Is this configured somewhere?

[[image] image1096×363 19.8 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O5mLAAS)

If anyone is able to provide some clarity on the questions above that would be great.

Thanks,
 Jason
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmnYCAQ/zscaler-certificate-trust-using-workspace-one","lastmod":"2023-05-31T08:46:10.000Z","id":"0D54u00009evmnYCAQ"} -->
## Zscaler certificate trust using workspace one

- Source: https://community.zscaler.com/s/question/0D54u00009evmnYCAQ/zscaler-certificate-trust-using-workspace-one
- Type: Q&A
- Posted: 2022-03-11T06:44:00.000Z
- Last activity: 2023-05-31T08:46:10.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Team,

We are rolled out Zscaler Client connector to Mac endpoints installation is successful but the certificate is not getting trusted according to the below article this is know issue but this can be achieved by using MDM.

help.zscaler.com
##### [Upgrading to macOS Big Sur | Zscaler](https://help.zscaler.com/zscaler-client-connector/upgrading-macos-big-sur)

Information on upgrading to macOS Big Sur.

We have workspace one as an MDM so need help with workspace one configuration document if yyou have
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmnkCAA/zscaler-rootca-installed-but-not-recognised-correctly-by-chrome","lastmod":"2023-07-06T13:00:47.000Z","id":"0D54u00009evmnkCAA"} -->
## Zscaler RootCA installed but not recognised correctly by chrome

- Source: https://community.zscaler.com/s/question/0D54u00009evmnkCAA/zscaler-rootca-installed-but-not-recognised-correctly-by-chrome
- Type: Q&A
- Posted: 2022-03-03T15:57:46.000Z
- Last activity: 2023-07-06T13:00:47.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,
 we have seen lots of issues lately were Chrome (Version 98.0.4758.102 (Official Build) (64-bit)) sometimes indicates that the Zscaler Root CA is not installed, “NET:ERR_CERT_AUTHORITY_INVALID?, despite the fact that the Certificate is installed. The error is only on some https sites. It also disappears and re-appears randomly.
 If we check the certificate path, everything is OK an RootCA cert is present:

[[ssl-issue] ssl-issue1622×1043 54 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O8EHAA0)

We are quite clueless what causes this issue.

Has anyone seen this?
 BTW: The current example is with yandex… but yesterday it was with [software.cisco.com](http://software.cisco.com) - so most likely not an issue caused by the current “situation?.

Thank you & best regards
 Andreas
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmoGCAQ/idp-migration-from-adfs-to-okta","lastmod":"2023-05-31T08:13:28.000Z","id":"0D54u00009evmoGCAQ"} -->
## IDP migration from ADFS to Okta

- Source: https://community.zscaler.com/s/question/0D54u00009evmoGCAQ/idp-migration-from-adfs-to-okta
- Type: Q&A
- Posted: 2022-01-28T11:07:26.000Z
- Last activity: 2023-05-31T08:13:28.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

New to Zscaler Community. We are attempting to migrate SAML authentication from ADFS to Okta. We checked with support to check the feasibility of moving users group by group in a phased manner. but they told that suggested way of doing it via “migrate to a new saml idp? option and having the second idp in parallel would break the authentication and produce duplicte user entries. We already have ADFS auth enabled for **any** locations and domains and we can’t afford to migrate all users at once in one shot (cut over to okta in one change window). we would like to acheive it based on location or user group if possible. could you please suggest the better approach to what we are trying to achieve here.

since we can’t afford to test the things the way we want in prod cloud, the support has provided access to beta cloud to test two idp’s in parallel. adding our prod authentication domain (example [abc.com](http://abc.com)) to beta cloud would break prod authentication?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmooCAA/zscaler-ssl-errors-within-docker-build-process","lastmod":"2024-01-08T21:13:34.000Z","id":"0D54u00009evmooCAA"} -->
## Zscaler SSL Errors within Docker Build Process

- Source: https://community.zscaler.com/s/question/0D54u00009evmooCAA/zscaler-ssl-errors-within-docker-build-process
- Type: Q&A
- Posted: 2022-01-06T13:03:45.000Z
- Last activity: 2024-01-08T21:13:34.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

Currently facing an issue with Docker during Docker build process. All certs are imported as per instructions:

Installing TLS / SSL ROOT Certificates to non-standard environments
Client Connector
> Over 90% of websites now use TLS encryption (HTTPS) as the access method. Enterprises utilise TLS inspection for Advanced Threat Protection, Access controls, Visibility, and Data-Loss Prevention. Zscaler App is deployed on Windows and Mac devices and the Zscaler certificate is installed in the appropriate system Root Certificate Store so that the system/browser trusts the synthetic certificate generated during TLS Inspection. However, a number of applications do not read the system certificat…

We have Python/Docker working independently, however the specific action of the build process is failing with SSL errors. Question is how to inject the Root cert into the build process. Is this is even possible or if anyone has any experience with this.
 We do have an open case which is not really progressing. Many thanks.

Regards,
 Steven
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmpICAQ/posture-checks-on-chromebooks","lastmod":"2023-05-31T09:27:31.000Z","id":"0D54u00009evmpICAQ"} -->
## Posture checks on Chromebooks

- Source: https://community.zscaler.com/s/question/0D54u00009evmpICAQ/posture-checks-on-chromebooks
- Type: Q&A
- Posted: 2021-11-18T16:45:52.000Z
- Last activity: 2023-05-31T09:27:31.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,
 Regarding Certificate Trust posture profile on zscaler for android devices ( Chromebook in particular),
 where does zscaler look for the certificates on chromebook? I have devices failing cert check posture even though the Root CA is in the cert store in chromebook.( chrome browser settings).
 Does Zscaler look in another place to check for the certificates in chrome os devices?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmpqCAA/zscaler-connector-login-error","lastmod":"2023-08-29T06:43:10.000Z","id":"0D54u00009evmpqCAA"} -->
## Zscaler connector login error

- Source: https://community.zscaler.com/s/question/0D54u00009evmpqCAA/zscaler-connector-login-error
- Type: Q&A
- Posted: 2021-10-26T04:45:26.000Z
- Last activity: 2023-08-29T06:43:10.000Z
- Answers: 3
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello All,

We have client connector getting installed users computer automatically, we use Okta as IDP.
 Many users reported below error.

[[MicrosoftTeams-image (17)] MicrosoftTeams-image (17)1418×740 101 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O4U6AAK)

> Your Session expired due to idle timeout. Please try authenticating again, if the issue persists, contact your administrator.
>

Once user exit the app and opens it again it works fine.We are trying to identify why this might be happening and what could be the resolution. We are not able to reproduce this error on demand, but users trying to login first time gets this error.

Zscaler troubleshooting doc mentions below but it’s not helping.

`0xE550C The relay state wasn't present in the SAML response, or the relay state is invalid. Check if the relay state is being sent in the SAML response (sometimes, this happens when REDIRECT binding is configured, instead of POST binding), or check if the response that was sent through Zscaler didn't send the request.`
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqCCAQ/saml-authentication-group-sync","lastmod":"2023-05-31T09:08:34.000Z","id":"0D54u00009evmqCCAQ"} -->
## SAML authentication group sync

- Source: https://community.zscaler.com/s/question/0D54u00009evmqCCAQ/saml-authentication-group-sync
- Type: Q&A
- Posted: 2021-10-05T06:37:36.000Z
- Last activity: 2023-05-31T09:08:34.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

we have an issues in our environment when it comes to authentication.

Our current authentication method is SAML and we are facing issues when user’s Active Directory groups will change.

For example User-A is in AD group “Group-A?. Everything works fine, we see the AD group in the Zscaler Admin Portal’s users and groups menu and User-A is assiged to this group. Policies and the app profiles are also correctly assigned.

If we now deside to move User-A in AD group “TEST? it will not happen in the Zscaler Admin Portal. User-A is still assigned to Group-A and of course still to the same policies/app profile in Zscaler Admin Portal.
 Only after doing a logout and login at the Zscaler Client Connector the group membership will be synced.

For a single user we can provide the 1-time-password to logout/login but we are planning to move 4000 users into another AD group and want to assign a new app profile to exact this AD group.

So what’s the correct way to do it or do we need to rethink the SAML authentication method? I read something about SCIM.
 What are other Zscaler customers doing when using SAML and changing AD group memberships, because this is a typical process.

Thanks
 Jonas
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqDCAQ/automatic-deprovisioning-using-scim-with-azure-ad","lastmod":"2023-07-06T13:03:37.000Z","id":"0D54u00009evmqDCAQ"} -->
## Automatic de-provisioning using SCIM with Azure AD

- Source: https://community.zscaler.com/s/question/0D54u00009evmqDCAQ/automatic-deprovisioning-using-scim-with-azure-ad
- Type: Q&A
- Posted: 2021-10-04T22:43:05.000Z
- Last activity: 2023-07-06T13:03:37.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Can someone please confirm is user user deprovisioning is functional under the model of automatic provisioning with Azure AD? I thought that was the whole point of the feature but have support telling me users will be provisioned but not deprovisioned and reflected in User Management which for us is the point of the exercise because we are simply trying to get an accurate current user count. SCIM is configured and synchronising in a healthy state. It appears other Zscaler certified engineers have been able to get this working in the past:

Automatic de-provisioning of users while using Okta as IDP and SAML auto-provisioning
Authentication
> User deprovisiong is possible in Azure AD with SCIM method. I don’t experience any such limitation in my past deployments.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqICAQ/authentication-error-aadsts90072-using-zscaler-and-azure-for-external-contracters","lastmod":"2023-07-06T11:45:21.000Z","id":"0D54u00009evmqICAQ"} -->
## Authentication error AADSTS90072 using Zscaler and Azure for external contracters

- Source: https://community.zscaler.com/s/question/0D54u00009evmqICAQ/authentication-error-aadsts90072-using-zscaler-and-azure-for-external-contracters
- Type: Q&A
- Posted: 2021-09-29T19:45:06.000Z
- Last activity: 2023-07-06T11:45:21.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

My software is used by an organisation that has embraced Microsoft Azure. I was given Zscaler access to their Azure network so I could look after any database issues with SQL server within their Azure domain. My organisation also started using Azure for our email.

Firstly AADSTS90072 is not a Zscaler error it is an Azure error (sorry to those who already know this I am an Azure nubee and hopefully will die before I have to be)

SO when I was given by brand new Azure credentials for My organisation I entered them into my Outlook desktop app and clicked the dreaded ‘Let Microsoft take control of my life’ button instead of looking to the left and selecting the highlighted text ‘Only for this App’

Below is the offending screen. As soon as I removed my Orgaisations account from here Zscaler happily handshaked me off to our clients Azure account for authentication.

[[Access work or school] Access work or school795×909 80.1 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O7ctAAC)

I hope this helps someone.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqbCAA/okta-with-zscaler-connector-authentication","lastmod":"2023-05-31T08:46:00.000Z","id":"0D54u00009evmqbCAA"} -->
## OKTA with Zscaler connector authentication

- Source: https://community.zscaler.com/s/question/0D54u00009evmqbCAA/okta-with-zscaler-connector-authentication
- Type: Q&A
- Posted: 2021-09-12T23:53:29.000Z
- Last activity: 2023-05-31T08:46:00.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Everyone,

Currently our customer is evaluating OKTA as Idp with Zscaler. Does Zscaler connector with OKTA have an authentication limit like browser based authentication, e.g. login every time when the open browser or the authentication with OKTA only required once when initials installation of Zscaler connector for each login user?. Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqdCAA/azure-authentication-is-not-enforcing","lastmod":"2025-11-28T02:29:09.000Z","id":"0D54u00009evmqdCAA"} -->
## Azure authentication is not enforcing

- Source: https://community.zscaler.com/s/question/0D54u00009evmqdCAA/azure-authentication-is-not-enforcing
- Type: Q&A
- Posted: 2021-09-10T22:43:16.000Z
- Last activity: 2025-11-28T02:29:09.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

User is getting an error when going to [ip.zscaler.com](http://ip.zscaler.com) from a browser to ZEN via IPSec with auth enforced and URL policies are bypassed.

[[Carter] Carter1091×780 119 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O4e6AAC)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqnCAA/bypass-authentication-for-a-single-user","lastmod":"2023-05-31T08:47:18.000Z","id":"0D54u00009evmqnCAA"} -->
## Bypass authentication for a single user

- Source: https://community.zscaler.com/s/question/0D54u00009evmqnCAA/bypass-authentication-for-a-single-user
- Type: Q&A
- Posted: 2021-09-03T04:53:20.000Z
- Last activity: 2023-05-31T08:47:18.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,
 Would like to know if below configuration is achievable…
 how to bypass proxy authentication for a single user in ZScaler, i know how to do for websites.
 here is the requirement for specific user.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmrACAQ/local-user-authentication","lastmod":"2023-05-31T08:47:27.000Z","id":"0D54u00009evmrACAQ"} -->
## Local user authentication

- Source: https://community.zscaler.com/s/question/0D54u00009evmrACAQ/local-user-authentication
- Type: Q&A
- Posted: 2021-07-30T10:47:22.000Z
- Last activity: 2023-05-31T08:47:27.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello community,

is there any way to enable ZIA on an existing device for a LOCAL machine account? To be more specific:

1. All our users get ZCC pre-deployed on their laptop during setup and ZCC authenticates with the particular AzureAD UPN during windows login
2. On some laptops we need a secondary, local account (lets call it “localuser1?) created for user/app/project-reasons
3. When the user signs in with “localuser1? ZCC pops up but has no useraccount pre-configured and the user has to enter his credentials manually
4. If no credentials in ZCC are entered the user can just close ZCC which then stays in “disabled? state. Therefore e.g. browsing the web is possible without any restrictions/ZIA protection.

We think about some way to always have ZIA somehow connected regardless of the signed-in user e.g. by fetching the owner for the particular device from Zscaler portal fingerprint and activate ZIA this way.

Machine tunnel would not help here because it only enables ZPA for machine group access but not for ZIA (as far as I have understood machine tunnel doc). And requirements and functionality of device token seems also not sufficient for this scenario.

There was a similar request in [ZIA & machine authentication](https://community.zscaler.com/t/zia-machine-authentication/10740), but it seems there is no solution so far.

Any ideas/hints? Did I miss something?

Thanks
 Manuel
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmrTCAQ/azure-cli-on-wsl2-ubunto","lastmod":"2023-05-31T08:47:41.000Z","id":"0D54u00009evmrTCAQ"} -->
## Azure CLI on WSL2 Ubunto

- Source: https://community.zscaler.com/s/question/0D54u00009evmrTCAQ/azure-cli-on-wsl2-ubunto
- Type: Q&A
- Posted: 2021-06-08T16:04:46.000Z
- Last activity: 2023-05-31T08:47:41.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I’m using Azure CLI on WSL2 Ubuntu. I can successfully login but when I try to install AZ extensions using ‘az extension add’ I get this error:

```
Unable to get extension index.
Please ensure you have network connection. Error detail: HTTPSConnectionPool(host='aka.ms', port=443): Max retries exceeded with url: /azure-cli-extension-index-v1 (Caused by SSLError(SSLError("bad handshake: Error([('SSL routines', 'tls_process_server_certificate', 'certificate verify failed')],)",),))
```

I have already installed ZScaler root certificates in Ubuntu (as well as many other applications like git, Java, etc.). Does anybody know how I can install ZScaler root certificates specifically for Azure CLI?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmraCAA/bypassing-mfa-for-azure-ad-sso","lastmod":"2023-05-31T08:47:44.000Z","id":"0D54u00009evmraCAA"} -->
## Bypassing MFA for Azure AD SSO

- Source: https://community.zscaler.com/s/question/0D54u00009evmraCAA/bypassing-mfa-for-azure-ad-sso
- Type: Q&A
- Posted: 2021-06-03T20:14:28.000Z
- Last activity: 2023-05-31T08:47:44.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello!

I’ve setup Zscaler completely following every guide known to man. It works. No problems. Users can log in successfully and use it and keep it on. Our test group has been using it for about a month and a half now successfully. It’s all set up.

For our mass deployment, I want to remotely install it and have SSO not trigger a Microsoft MFA text. I’d like to skip MFA when someone signs in via the ZScaler Client Connector. This way I can deploy it and no one’s work will be interrupted. I’ve not found a lick of information on ZScaler’s documentation or the Windows ZScaler Documentation. There has to be a way, I doubt everyone who uses Client Connector doesn’t use MFA.

Please help, I’m losing my mind.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmsICAQ/zscaler-nss-web-logs-path","lastmod":"2023-05-31T08:48:03.000Z","id":"0D54u00009evmsICAQ"} -->
## Zscaler nss web logs path

- Source: https://community.zscaler.com/s/question/0D54u00009evmsICAQ/zscaler-nss-web-logs-path
- Type: Q&A
- Posted: 2021-04-20T11:04:12.000Z
- Last activity: 2023-05-31T08:48:03.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

I am new to Zscaler.
 I have a task of intregating Zscaler -IA to Arcsight.
 For this I created nss server and nss feed for web logs.

Can anyone please help me where can I locate web logs on nss server.
 I want to know the path of these logs on server.
 I did not find them under /varlog/

Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmsRCAQ/0xe550c-error-for-ios-device-with-idppac","lastmod":"2023-05-31T09:08:55.000Z","id":"0D54u00009evmsRCAQ"} -->
## 0xe550C Error for iOS device with IdP(PAC)

- Source: https://community.zscaler.com/s/question/0D54u00009evmsRCAQ/0xe550c-error-for-ios-device-with-idppac
- Type: Q&A
- Posted: 2021-04-11T01:20:18.000Z
- Last activity: 2023-05-31T09:08:55.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello there

I have multiple customers who reported about the error “0xe550C? for iOS devices.
 Ref: [Troubleshooting SAML | Zscaler](https://help.zscaler.com/zia/troubleshooting-saml)

The common points are followings.

1. the end users use iOS device. (No report from windows user)
2. Traffic Forwarding Method is PAC File. (One of customers uses PAC over GRE)

No matter their IdP, they face this issue on Safari.

A weird point is the issue is observable NOT 100% but frequently.
 Some customer reports this issue is seen once a week.
 Other one says this is seen 3 times in 5 times.

Every time we open a support ticket, then we receive the same answer: “Please collect header trace?.
 End user is uses iPhone either iPad. Those devices do not have developer tool or inspector. They need to have Mac OS device to use such.

Anyone else has seen this issue before? If you have any knowledge to resolve this, please could you teach us how you fixed it?

Thanks in advance!
 Best regards,
 Inori
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmscCAA/error-42024-ios","lastmod":"2023-07-06T11:45:41.000Z","id":"0D54u00009evmscCAA"} -->
## Error: 42024 - iOS

- Source: https://community.zscaler.com/s/question/0D54u00009evmscCAA/error-42024-ios
- Type: Q&A
- Posted: 2021-03-30T05:10:25.000Z
- Last activity: 2023-07-06T11:45:41.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

I am not sure if I post this in the right place.
 If not, please let me know.

I am trying to use Zscaler Client Connector with iOS.
 After Okta authentication, the attached error message comes up.
 My question is where I can find the instruction to resolve this issue.

Thank you.
 Best regards,
 Akira

[[2021033004] 2021033004750×1334 78.7 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O7RMAA0)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtXCAQ/zscaler-certificate","lastmod":"2023-05-31T08:48:37.000Z","id":"0D54u00009evmtXCAQ"} -->
## Zscaler certificate

- Source: https://community.zscaler.com/s/question/0D54u00009evmtXCAQ/zscaler-certificate
- Type: Q&A
- Posted: 2021-02-15T14:52:42.000Z
- Last activity: 2023-05-31T08:48:37.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Community,

I’ve help deploy multiple sites with Zscaler, for specific locations like servers where the proxy mode was transparent and the SSL inspection is enabled, we didn’t install the Zscaler certificate on the servers and everything worked fine with no errors. Is this normal?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtfCAA/scripted-downloads-and-tests-suffer-from-zscalers-poor-useability","lastmod":"2023-07-06T13:07:23.000Z","id":"0D54u00009evmtfCAA"} -->
## Scripted downloads and tests suffer from Zscaler's poor useability

- Source: https://community.zscaler.com/s/question/0D54u00009evmtfCAA/scripted-downloads-and-tests-suffer-from-zscalers-poor-useability
- Type: Q&A
- Posted: 2021-02-10T18:04:28.000Z
- Last activity: 2023-07-06T13:07:23.000Z
- Answers: 6
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

It’s surprising the forum posts do not appear to mention a developer’s point of view. Scripted downloads in “testing? or “continuous integration? of “software? fail due to 2 levels of interference by the enterprise-wide routing through Zscaler:

- Zscaler rewriting HTTPS. This may be worked around by adding the Zscaler Certificate Authority but causes loss of many hours to modify and maintain the scripts.
- Zscaler offering a 307 redirect to the “banned? message or to the “are you sure? dialog as a response to contentious decrypted requests. This requires a lot more effort to avoid or work around at a level of scripting often unavailable to mere mortal developers.

Zscaler as a product appears aiming at organizations full of personal machines or VPN users whose work involves browsing. The product appears extremely hostile to organizations of modern day turning their eye at quick prototyping and development. Zscaler’s meddling with automated downloads and other HTTP requests can be avoided by **Zscaler’s product development** who may consider the following useability fixes.

- Avoid HTTPS decryption and MitM rewrites for destination IP addresses and Server Name Indications found in an enterprise-wide white list. The white list can, by default, include a number of modern package repositories such as Microsoft Gallery, Maven Central and other Maven repositories, NodeJS Package Manager Registry, Docker Hub, Linux distributions etc. (The scenario of unintended downloads of malicious packages needs to be handled at a higher level involving setting up trusted build environments and human code reviews at each of the package repositories).
- The most developer-friendly approach would enable traffic inspection only through enterprise-enforced opt-in of the desktop browsers. For example, each enterprise-approved desktop browser could receive a policy update installing an open-source plugin (authored by Zscaler, I hope) that would accompany HTTPS requests with an unencrypted tag or a GUID indicating a request for Zscaler decryption and inspection. The plugin should tag only those HTTP requests that are initiated by the browser as document (frame) loads.
- The following option is the least effort for Zscaler product development but it is not so good for the developers at whom the product is thrown by their upper management. Decrypt HTTPS (requiring consumer developers carry the Zscaler CA) but inspect the decrypted contents and bring up the “are you sure? redirects or bans only when decrypted requests have User-Agent headers resembling known browsers.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtjCAA/authentication-problems-with-onedrive","lastmod":"2023-05-31T08:48:29.000Z","id":"0D54u00009evmtjCAA"} -->
## Authentication problems with OneDrive

- Source: https://community.zscaler.com/s/question/0D54u00009evmtjCAA/authentication-problems-with-onedrive
- Type: Q&A
- Posted: 2021-02-08T17:55:17.000Z
- Last activity: 2023-05-31T08:48:29.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi everyone,
 I woud like to ask you if you have ever encountered any problems regarding authentication with OneDrive.

I have verified that the Microsoft-Recommended One Click Office 365 Configuration feature is enabled.
 The problem is reported by some users and fortunately not by all, do you have any suggestions?

Thanks in advance.

Regards
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmuGCAQ/disabled-sso-for-zscaler-desktop-application","lastmod":"2023-07-06T13:08:05.000Z","id":"0D54u00009evmuGCAQ"} -->
## Disabled SSO for Zscaler desktop application

- Source: https://community.zscaler.com/s/question/0D54u00009evmuGCAQ/disabled-sso-for-zscaler-desktop-application
- Type: Q&A
- Posted: 2021-01-19T17:34:56.000Z
- Last activity: 2023-07-06T13:08:05.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

My group has run into an issue where we have Single Sign-on enabled, and one of our groups need to use Zscaler in order to connect to our customer’s infrastructure. When Zscaler is opened, the user is putting in his Customer username. Once he clicks on the Zcloud option, the following error appears:

User Account “[Useremail@ourdomain.com](mailto:Useremail@ourdomain.com)? from identity provider “URL? does not exist in tenant "Customer’s tenant and can not access the application zscloud(Zscaler) in that tenant.

I’ve come across a few suggested solutions on the Zscaler forums, but none have worked thus far.
 The first I tried was the solution found here: [Zscaler App login issue -- uses my infosys when given with my client account](https://community.zscaler.com/t/zscaler-app-login-issue-uses-my-infosys-when-given-with-my-client-account/7247)
 This thread suggested adding the user’s customer email to Emails and accounts; however this was already in place. Sadly this did NOT resolve my coworkers issue. Once this failed I reached out to the customer’s IT group since they’re more familiar with Zscaler.
 The customer’s IT group suggested turning off Single Sign-on on the system, but nothing I’ve done thus far has resolved the issue.
 I’ve also turned off Sync’ing in both Edge and Chrome (win10 accounts chrome ext), I’ve gone into internet options and set it so that the user should be prompted for username and password for both internet and local security settings, I’ve disabled the option for “Don’t prompt for client cert selection…? as suggested in another forum thread, in the advanced tab of internet options I’ve turned off “Enable Integrated Windows Auth.?

I know with other applications there are usually registry entries that can be set to disable SSO for that particular application while the rest of the system would still be able to use SSO, but if this exists for Zscaler I’m assuming it would need to be created as a new DWord.
 As you can tell, I’m grasping at straws trying to resolve my coworkers issue with Zscaler so he can start working… Any advice or suggestions on this matter, any guidance would be appreciated.

I’m hoping to have a meeting with the customer’s IT dept. to resolve this issue, but i’ve had difficulties trying to even get a response from them so far…
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmuHCAQ/questions-about-authentication-exemption-settings","lastmod":"2023-05-31T08:13:58.000Z","id":"0D54u00009evmuHCAQ"} -->
## Questions about authentication exemption settings

- Source: https://community.zscaler.com/s/question/0D54u00009evmuHCAQ/questions-about-authentication-exemption-settings
- Type: Q&A
- Posted: 2021-01-19T02:06:21.000Z
- Last activity: 2023-05-31T08:13:58.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Team,

I have two questions about setting authentication exemption.

1, Is it possible to use the “*" wildcard in the ZIA authentication exemption setting?
 Looking at [SAML & SCIM Configuration Guide for Azure Active Directory | Zscaler](https://help.zscaler.com/zia/saml-scim-configuration-guide-azure-active-directory#zscaler-authentication-exemptions-list), it seemed to use "*?.

2, Is it possible to use the IP address (0.0.0.0) and IP address range (0.0.0.0/24) in the authentication exemption setting?

Regards,
 Takashi Yanagihashi
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmuLCAQ/nss-certificate-install-error","lastmod":"2023-05-31T08:48:55.000Z","id":"0D54u00009evmuLCAQ"} -->
## NSS Certificate install error

- Source: https://community.zscaler.com/s/question/0D54u00009evmuLCAQ/nss-certificate-install-error
- Type: Q&A
- Posted: 2021-01-15T13:06:47.000Z
- Last activity: 2023-05-31T08:48:55.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

I am trying to install the NSS certificate on the NSS server (esxi) to start the nss functionality and getting he below error:

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 Please enter complete path to the certificate bundle(.zip): /usr/home/zsroot/
 tar: Unrecognized archive format
 tar: Error exit delayed from previous errors.
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Note:
 .zip - Downloaded from ZIA portal under NSS servers
 path - on the NSS server “/usr/home/zsroot/NssCertificate.zip?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmuSCAQ/microsoft-office-365-conditional-access-with-ip-address-and-hybrid-azure-ad-domain-join","lastmod":"2023-07-06T11:45:56.000Z","id":"0D54u00009evmuSCAQ"} -->
## Microsoft Office 365 Conditional access with IP address and Hybrid Azure AD Domain Join

- Source: https://community.zscaler.com/s/question/0D54u00009evmuSCAQ/microsoft-office-365-conditional-access-with-ip-address-and-hybrid-azure-ad-domain-join
- Type: Q&A
- Posted: 2021-01-12T07:59:21.000Z
- Last activity: 2023-07-06T11:45:56.000Z
- Answers: 1
- Likes: 5
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello team,

Someone recently came up with a request to only allow access to Office 365 if the device was coming from a Zscaler ZEN IP address and the device is Azure AD hybrid domain-joined.

Each of these policies individually is fairly straightforward to achieve. However, combining the two conditions required a bit of trial-and-error. After testing several combinations of policies, I finally figured out what would work and made a video documenting my findings:

Some great resources that helped me along the way:

learn.microsoft.com
##### [What is a hybrid Azure AD joined device? - Microsoft Entra](https://learn.microsoft.com/en-us/azure/active-directory/devices/concept-azure-ad-join-hybrid)

Learn how device identity management can help you to manage devices that are accessing resources in your environment.

learn.microsoft.com
##### [Configure hybrid Azure Active Directory join - Microsoft Entra](https://learn.microsoft.com/en-us/azure/active-directory/devices/howto-hybrid-azure-ad-join)

Learn how to configure hybrid Azure Active Directory join.

learn.microsoft.com
##### [Location condition in Azure Active Directory Conditional Access - Microsoft...](https://learn.microsoft.com/en-us/azure/active-directory/conditional-access/location-condition)

Use the location condition to control access based on user physical or network location.

ATA Learning – 16 Jan 20
[image]

##### [Setting Up Hybrid Azure AD-Join: The Best of Both Worlds](https://adamtheautomator.com/hybrid-azure-ad/)

Learn, step-by-step how to set up a hybrid Azure AD joined scenario in this tutorial! And begin using the various services Azure AD has to offer.

Daniel Chronlund Cloud Tech Blog – 23 Nov 18
[image]

##### [How Multiple Conditional Access Policies Are Applied](https://danielchronlund.com/2018/11/23/how-multiple-conditional-access-policies-are-applied/)

Updated 16th of February 2021. Friday morning and I’m on the train heading for our beautiful capitol of Sweden. Last workday before a well deserved weekend I think. In my last post I presented my C…

Hope this helps.

Warm Regards,
 Chris
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmuhCAA/zscaler-client-macos-certificate-identity-popup","lastmod":"2023-05-31T08:13:58.000Z","id":"0D54u00009evmuhCAA"} -->
## zScaler Client [macOS] certificate identity popup

- Source: https://community.zscaler.com/s/question/0D54u00009evmuhCAA/zscaler-client-macos-certificate-identity-popup
- Type: Q&A
- Posted: 2020-12-17T15:21:08.000Z
- Last activity: 2023-05-31T08:13:58.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi all,
 Maybe one can help me out here. I’m managing a fleet of macOS devices and the global VPN policy is using zScaler.
 I’m up to date to version 2.1.2.48

Everytime a user logs out, and during the login after entering the Microsoft Azure password zScaler asks for a certificate identity. This can’t be skipped and for lots of users I need to remotely login and use administrator credentials to set the certificate.

I don’t know which certificate, I’ve tried the zScaler root CA, and a /Client certificate from zScaler as well, but the popup keeps coming up and I can’t choose the certificates.

It doesn’t matter which certificate I choose, it does work.
 According to the admin there is no TLS/SSL inspection active.

Kind regards!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmv7CAA/export-userdefined-url-categories","lastmod":"2023-07-06T13:09:02.000Z","id":"0D54u00009evmv7CAA"} -->
## Export user-defined URL categories

- Source: https://community.zscaler.com/s/question/0D54u00009evmv7CAA/export-userdefined-url-categories
- Type: Q&A
- Posted: 2020-11-25T10:50:04.000Z
- Last activity: 2023-07-06T13:09:02.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is there any option to export the contents of a user-defined URL category (custom urls, retaining parent category, keywords) into a plaintext file or a .csv or alike?

Would very much help to easily see what changed etc.; esp. for admin roles which do not have access to audit logs.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvMCAQ/automatic-authentication-through-tunnel-gre","lastmod":"2023-05-31T08:49:19.000Z","id":"0D54u00009evmvMCAQ"} -->
## Automatic authentication through tunnel GRE

- Source: https://community.zscaler.com/s/question/0D54u00009evmvMCAQ/automatic-authentication-through-tunnel-gre
- Type: Q&A
- Posted: 2020-11-03T09:07:32.000Z
- Last activity: 2023-05-31T08:49:19.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi everyone,
 how can I ask users in my network to authenticate themselves each time the browser is opened?
 Users have a file PAC installed and arrive on ZEN nodes via a GRE Tunnel.
 The location they pass through is configured only with these features enabled:
 Enforce Authentication - Enabled
 Enforce Zscaler Client Connector SSL Setting - Enabled

When the user with pac file opens a browser by browsing [ip.zscaler.com](http://ip.zscaler.com) I see it correctly unauthenticated but as soon as he tries to browse any other https or http site the system automatically authenticates it.

Could you please help me to understand?

Regards,
 Luca.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvXCAQ/zscaler-administrators-override-groups","lastmod":"2025-08-22T07:27:36.000Z","id":"0D54u00009evmvXCAQ"} -->
## Zscaler administrators override groups

- Source: https://community.zscaler.com/s/question/0D54u00009evmvXCAQ/zscaler-administrators-override-groups
- Type: Q&A
- Posted: 2020-10-21T12:45:53.000Z
- Last activity: 2025-08-22T07:27:36.000Z
- Answers: 12
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello community,

When we assign an administrator’s role to a user that is already enrolled with Zscaler, this overrides the groups of the user and only leaves the default group “Service Admin?.
 We have Azure AD as the Idp.
 I don’t know why the groups attributes do not reappear, is this normal behaviour?
 Thanks,
 Jenny
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvaCAA/jamf-to-assign-groups-in-zscaler","lastmod":"2023-05-31T08:14:02.000Z","id":"0D54u00009evmvaCAA"} -->
## JAMF to assign groups in Zscaler

- Source: https://community.zscaler.com/s/question/0D54u00009evmvaCAA/jamf-to-assign-groups-in-zscaler
- Type: Q&A
- Posted: 2020-10-19T14:56:15.000Z
- Last activity: 2023-05-31T08:14:02.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is there a way to import groups assigned in JAMF into Zscaler?

For instance:

We have connected AD with JAMF, and once you have assigned the JAMF_AD group into your profile the device can be managed from the JAMF console.

Once inside the JAMF console we assign the users in groups:
 JAMF_A = Marketing
 JAMF_B = Legal
 JAMF_C = HR

I need to know if there’s a way to connect JAMF with Zscaler to import those groups only without affecting how Zscaler IdP works, we have a multi-factor authentication tool as our corporate login solution.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvlCAA/admin-console-for-2fa-authentication","lastmod":"2023-05-31T08:49:30.000Z","id":"0D54u00009evmvlCAA"} -->
## Admin console for 2FA authentication

- Source: https://community.zscaler.com/s/question/0D54u00009evmvlCAA/admin-console-for-2fa-authentication
- Type: Q&A
- Posted: 2020-10-11T20:34:35.000Z
- Last activity: 2023-05-31T08:49:30.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is there an Enhancement request to enable 2FA to the admin console for accounts that use User/Password only ?

We already have SSO configured but there are use cases where some accounts can only work with User/Password combination
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwBCAQ/unable-to-install-zscaler-cetificate-on-ios-simulator","lastmod":"2023-05-31T09:08:28.000Z","id":"0D54u00009evmwBCAQ"} -->
## Unable to install Zscaler Cetificate on iOS Simulator

- Source: https://community.zscaler.com/s/question/0D54u00009evmwBCAQ/unable-to-install-zscaler-cetificate-on-ios-simulator
- Type: Q&A
- Posted: 2020-09-23T13:19:42.000Z
- Last activity: 2023-05-31T09:08:28.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Unable to install Zscaler certificate on iOS simulator and the absence of certificate leads to no internet access inside iOS simulator
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwFCAQ/how-to-deploy-zscaler-connector-app-with-intune-and-sso","lastmod":"2026-01-05T18:32:20.000Z","id":"0D54u00009evmwFCAQ"} -->
## How to Deploy Zscaler Connector App with Intune and SSO

- Source: https://community.zscaler.com/s/question/0D54u00009evmwFCAQ/how-to-deploy-zscaler-connector-app-with-intune-and-sso
- Type: Q&A
- Posted: 2020-09-22T11:07:39.000Z
- Last activity: 2026-01-05T18:32:20.000Z
- Answers: 4
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Folks,

Since I had my scratches deploying Zscaler Connector App to Windows 10 using Intune and doing silent SSO, I thought it would be nice to share how I achieved it to save you some time.

This is my first post in the community, let me know if this goes somewhere else or if it requires re tagging. I had a rich post with images and links but since I’m new I can’t use more than 2 images and links. Sorry about that.

Beware I’m using ZIA only, but guess this should also work with ZPA.
 Also, SAML, SCIM and SSO must be already set up and tested with your Azure AD infrastructure.
 That’s outside our scope here, but check the wonderful Zscaler documentation.

You should manually install the agent first to confirm auto-enrolment works.
 Once that’s done you’ll create the Intune package and silently enrol your Windows 10 devices.

Shall we begin?

1 - Go to your Azure portal and select Groups

2 - Create a new test group that you will use to deploy Zscaler app

3 - Filled the fields as you see fit

4 - Click MEMBERS and select the devices that will get the agent and confirm with SELECT

5 - Create the group clicking CREATE on the bottom left

Now, to Intune…

6 - Go to [https://endpoint.microsoft.com/](https://endpoint.microsoft.com/), Intune’s new home since September 2020.

7 - Select APPS > WINDOWS and click ADD

8 - Select APP TYPE Life-of-business app and click SELECT at the bottom

9 - Upload the MSI file you download from your tenant and click OK. Wait until it is up:

Follow this article if you need help getting your MSI file:

help.zscaler.com
[image] [Downloading Zscaler Client Connector | Zscaler](https://help.zscaler.com/z-app/downloading-zscaler-app)

10 - The “Add app? page will be auto-filled.

- Insert “Zscaler? in the Publisher box, as it is required.

*** Command-line arguments are where I had some headaches. In order to work, it must have this single line:** 
 **/quiet CLOUDNAME=yourcloudname USERDOMAIN=your.domain**

If you don’t know your cloud name search the KB for “my cloud name?.

Your domain is what you told Zscaler when creating the tenant. You can get it from ADMINISTRATION > COMPANY PROFILE

You can leave the rest as it is if you want or tune it to your requirements.
 If you are managing agent updates from the Zscaler tenant you should set “Ignore app version? to YES

Click NEXT

11 - Select the group you created at the beginning in ADD GROUP.

12 - Click NEXT and CREATE after confirming everything is OK.

13 - Force an Intune SYNC on your devices in that group, or wait until they automatically do it and they should be onboarded and appear under the Zscaler Mobile Portal.

Enjoy!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwGCAQ/how-to-configure-secure-ldap","lastmod":"2023-05-31T08:14:06.000Z","id":"0D54u00009evmwGCAQ"} -->
## How to Configure Secure LDAP

- Source: https://community.zscaler.com/s/question/0D54u00009evmwGCAQ/how-to-configure-secure-ldap
- Type: Q&A
- Posted: 2020-09-22T03:10:16.000Z
- Last activity: 2023-05-31T08:14:06.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I am trying to configure Secure LDAP connection from my ZAB to AD server. I have network connectivity between ZAB and AD server. . I got internal certificate and not sure how to update it in ZAB. ZAB is build on free bsd and it doesn’t have path [/etc/ssl/certs] to update Root and Intermediate certificate. Some forum suggest to install package *ca_root_nss*. I don’t find any document in Zscaler porta. did any configured Secure LDAP connection ?

Note:we are not using ZAB as authentication proxy. ZAB is just to sync AD with Zscaler cloud
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwNCAQ/nss-and-ntp-sync","lastmod":"2023-05-31T08:14:06.000Z","id":"0D54u00009evmwNCAQ"} -->
## NSS and NTP sync

- Source: https://community.zscaler.com/s/question/0D54u00009evmwNCAQ/nss-and-ntp-sync
- Type: Q&A
- Posted: 2020-09-15T12:34:15.000Z
- Last activity: 2023-05-31T08:14:06.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

NTP is not synchronized with the peer despite the configuration of the crontab of the NSS (OVA) server:

`PATH=/sbin:/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/usr/games:/sc/update:/home/zsroot/bin:/sc/update */10 * * * * ntpdate <ntp-server-name>`

Another (public) NTP server is tried to be reached and blocked by our firewall. The NTP indicated in the crontab is not taken into account.

Any idea.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwXCAQ/zscaler-chrome-ubuntu-certificate","lastmod":"2023-05-31T08:14:06.000Z","id":"0D54u00009evmwXCAQ"} -->
## ZSCALER chrome ubuntu certificate

- Source: https://community.zscaler.com/s/question/0D54u00009evmwXCAQ/zscaler-chrome-ubuntu-certificate
- Type: Q&A
- Posted: 2020-08-25T09:29:21.000Z
- Last activity: 2023-05-31T08:14:06.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello every one,

I have an issue to connect to internet on my ubuntu 18.04 with chrome browser thro my corporate proxy .
 we use zscaler as a proxy and an adfs to authentificate the user; but on my ubuntu i can’t even reach my adfs server.
 any help to install the zscaler certificate on my ubuntu ??
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwsCAA/guide-integrate-zscaler-with-microsoft-cloud-app-security-mcas","lastmod":"2025-08-22T07:27:23.000Z","id":"0D54u00009evmwsCAA"} -->
## [Guide] Integrate Zscaler with Microsoft Cloud App Security (MCAS)

- Source: https://community.zscaler.com/s/question/0D54u00009evmwsCAA/guide-integrate-zscaler-with-microsoft-cloud-app-security-mcas
- Type: Q&A
- Posted: 2020-08-12T06:40:24.000Z
- Last activity: 2025-08-22T07:27:23.000Z
- Answers: 21
- Likes: 12
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

[[banner] banner1200×600 40.2 KB](https://global.discourse-cdn.com/zscaler/original/2X/a/a25296ad476e5c32603690516ce5c34c11289e1f.png)

> Originally posted @ [https://nathancatania.com/posts/integrate-zia-and-mcas/](https://nathancatania.com/posts/integrate-zia-and-mcas/)
>

Microsoft Cloud App Security (MCAS) is Microsoft’s CASB product. We can integrate this with Zscaler Internet Access (ZIA) - and vice-versa.

ZIA will be able to pull data from MCAS under the Cloud Applications dashboard, and MCAS will be able to push custom URL categories to your ZIA tenant for your specified sanctioned/unsanctioned applications - which you can then configure allow/coach/block rules for Zscaler to enforce as required.

You must have a subscription for both MCAS and Zscaler’s Nanolog Streaming Service (NSS) to be able to integrate the two products.

[:star:] You can view a demonstration of Zscaler’s integration with MCAS [here](https://community.zscaler.com/t/integrating-microsoft-mcas-and-the-zscaler-cloud/4939)

### [#requirements-1](#requirements-1)Requirements

#### [#h-1-zscaler-nss-2](#h-1-zscaler-nss-2)1. Zscaler NSS

To stream Zscaler logs to MCAS, you will need to have deployed and configured Zscaler’s Nanolog Streaming Service (NSS) in the ZIA admin portal - this allows you to stream logs from their logging clusters (called Nanolog) towards a SIEM or product of your choice (in this case, MCAS).

You can deploy the VM for NSS on-prem (available as an OVA image) or in AWS or Azure (Azure is strongly recommended).

For deployment in Azure, you can [review my guide here](https://community.zscaler.com/t/guide-deploy-zscaler-nss-in-azure/8571).

Zscaler documentation is available for [VMware](https://help.zscaler.com/zia/nss-deployment-guide-vmware-vsphere) and [AWS](https://help.zscaler.com/zia/nss-deployment-guide-amazon-web-services).

Your NSS VM must have a state of **Healthy** to be able to integrate with MCAS.

[[19] 191202×382 29.6 KB](https://global.discourse-cdn.com/zscaler/original/2X/7/7032fb83a3d6a7c5d562de6cdaa87f59e58713aa.png)

NB: Your NSS VM must have at least 8GB of memory for MCAS integration. If you’re running a 4GB instance as a demo/lab NSS VM, this will not work and you’ll need to deploy another instance with at 8GB assigned.

#### [#h-2-zscaler-mcas-nss-feed-3](#h-2-zscaler-mcas-nss-feed-3)2. Zscaler MCAS NSS Feed

Once you have deployed Zscaler NSS above, you’ll also need to have an MCAS Feed configured before you can properly integrate with MCAS.

An NSS feed specifies the data you wish to stream from Nanolog: You can stream everything, or filter the data to only receive what you care about (security events, information relating to a specific user, etc).

An MCAS NSS Feed, is an NSS Feed specifically pre-formatted for ingestion into MCAS.

##### [#add-a-new-mcas-nss-feed-4](#add-a-new-mcas-nss-feed-4)Add a New MCAS NSS Feed

In your ZIA portal, navigate to **Administration > Nanolog Streaming Service**.

[1]

Select the **NSS Feeds** tab, then select **Add MCAS NSS Feed**.

[[2] 21204×384 24.7 KB](https://global.discourse-cdn.com/zscaler/original/2X/8/8e5f0f8c2015118ecaf564cf3349dcfd988814cf.png)

Give the feed a name, select the NSS Server / VM instance that is associated with streaming this feed, and make sure you set the feed to **Enabled**. You can optionally define filters for the feed at the bottom of the panel. By default, EVERYTHING will be streamed to MCAS.

[[3] 31031×704 36.3 KB](https://global.discourse-cdn.com/zscaler/original/2X/2/22611be634d5d62ac49f79f920a05579f79c9495.png)

When you are done, click **Save**, then **Activate** your changes. This will apply the feed to the NSS VM you selected.

### [#configure-mcas-5](#configure-mcas-5)Configure MCAS

In this section, we’ll focus on the MCAS specific config, including:

- Generating an API token
- Setting at least 1 app as unsanctioned
- Adding NSS as a data source

#### [#access-the-mcas-portal-6](#access-the-mcas-portal-6)Access the MCAS Portal

You can access your MCAS Dashboard at the following link:

[https://portal.cloudappsecurity.com/](https://portal.cloudappsecurity.com/)

Alternatively, you access MCAS via the [Microsoft 365 Admin Center](https://security.microsoft.com/), under Security > More Resources > Cloud App Security.

#### [#generate-an-mcas-api-token-7](#generate-an-mcas-api-token-7)Generate an MCAS API Token

From the MCAS dashboard, click the **Settings** icon at the top right, and select **Security extensions**.

[https://global.discourse-cdn.com/zscaler/original/2X/d/d45348108559327aaa3450b0a838ac293370d85c.png](https://global.discourse-cdn.com/zscaler/original/2X/d/d45348108559327aaa3450b0a838ac293370d85c.png)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwwCAA/userprovisioning-only-memberof-info-from-okta","lastmod":"2023-05-31T08:14:11.000Z","id":"0D54u00009evmwwCAA"} -->
## User-Provisioning only "memberof" info from Okta

- Source: https://community.zscaler.com/s/question/0D54u00009evmwwCAA/userprovisioning-only-memberof-info-from-okta
- Type: Q&A
- Posted: 2020-08-11T12:26:00.000Z
- Last activity: 2023-05-31T08:14:11.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Could you please advice regarding to SAML User Provisioning?

We are looking for best way to user-provisioning with Okta. We woud like to do SAML authnetication, but we would not like to upload to zScaler Cloud user-informationwhich can specified personal such as username, UPN, email address. We can allow uploading group-info (memberof) to zScaler Cloud. We hope to apply policy by group-info (memberof).

Are there way to provisioning only “memberof? from Okta?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwyCAA/combination-of-kerberos-and-saml-from-a-location","lastmod":"2025-04-20T11:23:02.000Z","id":"0D54u00009evmwyCAA"} -->
## Combination of Kerberos and SAML from a location

- Source: https://community.zscaler.com/s/question/0D54u00009evmwyCAA/combination-of-kerberos-and-saml-from-a-location
- Type: Q&A
- Posted: 2020-08-10T06:59:05.000Z
- Last activity: 2025-04-20T11:23:02.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi all, trying to configure a new site to use a combination of Kerberos or SAML authentication. I want to Enforce Authentication for the location (no problem with this) and also allow Kerberos Authentication (but not enforce it) for a specific group of users. In isolation I have successfully tested both Kerberos and SAML authentication from the location.

Enable Kerberos Authentication on the Location:
 “Skip this task, if you want to use Kerberos for specific users and another authentication mechanism for all other users in the location.?

The Zscaler documentation states that we can use a combination of authentication methods from a location which is pretty much what I want to do, they just don’t explain the how. Has anyone else achieved this before?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmxDCAQ/zscaler-client-side-certificate-ussues","lastmod":"2023-05-31T08:50:00.000Z","id":"0D54u00009evmxDCAQ"} -->
## Zscaler + client side certificate ussues

- Source: https://community.zscaler.com/s/question/0D54u00009evmxDCAQ/zscaler-client-side-certificate-ussues
- Type: Q&A
- Posted: 2020-07-27T15:56:46.000Z
- Last activity: 2023-05-31T08:50:00.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello, i do have a website which works on 2 factor authentication i.e. client certificate which was issued to me by website along with username and password. When I enter my credentials on login page i am presented with chrome pop up which shows what certificate to select for the website. After selecting and pressing ok I am logged into the site.

After installaing zscaler I can enter my username / password but I don’t get the popup for certificate and website gives an error that client certificate is not found.

Any ideas are welcome plz.

Everything works fine when i turn off zapp agent.

Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmxPCAQ/zscaler-local-database-group-membership-using-azure-ad-and-scim","lastmod":"2023-07-06T11:46:34.000Z","id":"0D54u00009evmxPCAQ"} -->
## Zscaler Local Database Group Membership using Azure AD and SCIM

- Source: https://community.zscaler.com/s/question/0D54u00009evmxPCAQ/zscaler-local-database-group-membership-using-azure-ad-and-scim
- Type: Q&A
- Posted: 2020-07-17T07:27:40.000Z
- Last activity: 2023-07-06T11:46:34.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

We just changed IDP from ADFS to Azure AD. There’s no problem in authenticating users as well as user and group provisioning when we migrated to AAD with SCIM.

The problem is [user@company.com](mailto:user@company.com) doesn’t have members in the Zscaler local database. We’re using onprem AD groups which are syncrhonized to AAD.

When we were using ADFS, tokenGroup - Unqualified Names is used as LDAP Attribute, which is mapped to Outgoing Claim Type of “memberOf?

From: [Zscaler Help](https://help.zscaler.com/zia/saml-configuration-guide-adfs)

[image]

In Zscaler admin page this SAML Auto-provisioning options:

[image]

Now the question, since we moved from ADFS to Azure AD with SCIM, do we also need to configure group claims for user attributes and claims (the logic is similar with TokenGroups when using ADFS)?:

learn.microsoft.com
##### [Configure group claims for applications by using Azure Active Directory -...](https://learn.microsoft.com/en-us/azure/active-directory/hybrid/how-to-connect-fed-group-claims)

Get information on how to configure group claims for use with Azure AD.

[[image] image1906×660 50.6 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O4VZAA0)

I’m not really a systems guy so maybe some of you may have experienced this.

Regards,

MM
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmxXCAQ/pingidentity-with-zscaler","lastmod":"2023-07-06T13:12:06.000Z","id":"0D54u00009evmxXCAQ"} -->
## PingIdentity with zScaler

- Source: https://community.zscaler.com/s/question/0D54u00009evmxXCAQ/pingidentity-with-zscaler
- Type: Q&A
- Posted: 2017-11-15T11:45:58.000Z
- Last activity: 2023-07-06T13:12:06.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Does ZScaler Support PingIdentity as an iDP for users authentication?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmyPCAQ/integrated-windows-authentication-iwa-and-tunnel-mode","lastmod":"2023-07-06T11:46:45.000Z","id":"0D54u00009evmyPCAQ"} -->
## Integrated Windows Authentication (IWA) and Tunnel Mode

- Source: https://community.zscaler.com/s/question/0D54u00009evmyPCAQ/integrated-windows-authentication-iwa-and-tunnel-mode
- Type: Q&A
- Posted: 2020-05-26T11:49:32.000Z
- Last activity: 2023-07-06T11:46:45.000Z
- Answers: 0
- Likes: 6
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Many organisations move from an Explicit Proxy implementation to Zscaler and use Zscaler App to forward traffic to the Zscaler cloud. Some organisations have used Zscaler App in “Proxy Enforcement? or “Tunnel with Local Proxy? mode, which mimics their existing Explicit Proxy configuration - whether using a PAC file or using a browser configuration.

When the decision is made to move to Tunnel mode with Zscaler App - whether ZTunnel1.0 or ZTunnel2.0 - this will have a material change in the way your web browser authenticates to internal web sites.

In Internet Explorer Options -> Local Intranet Zone

[[image]image812×1064 171 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O5iPAAS)

The default level has “Automatic logon with current user name and password?
[[image]image724×226 50.5 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O4QnAAK)

Looking in the “Sites? configuration we can see that by default this is “Automatically Detect?, which includes any website which bypasses the proxy server.
[[image]image808×458 87.3 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O6OjAAK)

So - with an Explict Proxy configuration like this would mean *.welshgeek.net bypasses the proxy, and the browser would automatically perform IWA (NTLM/Kerberos) and the user would be transparently authenticated to the server.

[[image]image786×862 111 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O8KyAAK)

When you move to Zscaler App and Tunnel Mode, there is no longer a proxy configuration in the browser. Sites which were automatically identified as being in the “Local Intranet Zone? in Internet Explorer will now be in the “Internet Zone?, which will mean that transparent authentication no longer works.

When moving from Proxy mode (Explicit/PAC/TWLP) to Tunnel Mode, it is necessary to add the internal websites to the “Local Intranet? zone.

[[image]image760×682 72.8 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O5FrAAK)

This will ensure that the internal websites continue to be in the correct zone, and allows IWA to continue to occur.
 It’s worth noting that Safari and Chrome on Windows use the Internet Explorer configuration to identify which sites are in intranet zone. FireFox has it’s own configuration for identifying which sites NTLM/Kerberos authentication should occur to. On macOS (OSX) the configuration for Chrome/Firefox needs to be set separately, and Safari reads configuration from the keychain for authentication

This YouTueb Video [https://youtu.be/hB-7duWGC1U](https://youtu.be/hB-7duWGC1U) provides an example of the configuration and shows the effects of the changes.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmyeCAA/saml-autoprovisioning-vs-scim-autoprovisioning","lastmod":"2023-05-31T08:50:27.000Z","id":"0D54u00009evmyeCAA"} -->
## SAML Auto-Provisioning vs SCIM Auto-Provisioning

- Source: https://community.zscaler.com/s/question/0D54u00009evmyeCAA/saml-autoprovisioning-vs-scim-autoprovisioning
- Type: Q&A
- Posted: 2020-05-11T02:22:30.000Z
- Last activity: 2023-05-31T08:50:27.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

Just a quick question, what the difference between SAML Auto-Provisioning vs SCIM Auto-Provisioning for IdP like for example Microsoft AD or Microsoft Azure?

Thank you.

Regards,
 Matthews Loke
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmzDCAQ/authentication-in-non80443-traffic","lastmod":"2023-05-31T09:25:37.000Z","id":"0D54u00009evmzDCAQ"} -->
## Authentication in non80/443 traffic

- Source: https://community.zscaler.com/s/question/0D54u00009evmzDCAQ/authentication-in-non80443-traffic
- Type: Q&A
- Posted: 2020-03-03T10:18:28.000Z
- Last activity: 2023-05-31T09:25:37.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

for zapp tunnel2.0, i’m aware that all traffic including those that do not use port 80/443, will be sent to the ZEN. will the traffic be authenticated in this case?
 (let’s say that the user has not been logged into ZApp yet)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmzzCAA/f5-as-idp-for-saml","lastmod":"2023-07-07T11:29:51.000Z","id":"0D54u00009evmzzCAA"} -->
## F5 as IdP for SAML

- Source: https://community.zscaler.com/s/question/0D54u00009evmzzCAA/f5-as-idp-for-saml
- Type: Q&A
- Posted: 2020-01-24T16:22:32.000Z
- Last activity: 2023-07-07T11:29:51.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

our customer would like to use his F5 for SAML single-sign-on. As I was not even aware that an F5 is capable my question is, if someone did this already and if Zscaler supports an F5 as IdP?

Thank you & best regards
 Andreas
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn07CAA/adfs-to-scim-provisioning","lastmod":"2025-08-22T07:27:41.000Z","id":"0D54u00009evn07CAA"} -->
## ADFS to SCIM Provisioning

- Source: https://community.zscaler.com/s/question/0D54u00009evn07CAA/adfs-to-scim-provisioning
- Type: Q&A
- Posted: 2020-01-14T19:19:41.000Z
- Last activity: 2025-08-22T07:27:41.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We recently migrated our SAML configuration from ADFS Provisioning (on-prem) to SCIM Provisioning (Azure AD). Within ADFS, we have a claim rule bringing in groups with a wildcard (ie: “group.+? ). Within Azure AD, we are syncing only 3 groups.

I have noticed within the User Management section of the portal, there are many users still part of multiple groups that sync’d via ADFS. I would presume with SCIM it would strip out all the old group memberships and sync only the 3 referenced in Azure AD?

Just trying to understand the reason for this behavior and if other changes should be made.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn09CAA/running-redundant-nss","lastmod":"2023-05-31T08:51:18.000Z","id":"0D54u00009evn09CAA"} -->
## Running Redundant NSS

- Source: https://community.zscaler.com/s/question/0D54u00009evn09CAA/running-redundant-nss
- Type: Q&A
- Posted: 2020-01-10T03:39:14.000Z
- Last activity: 2023-05-31T08:51:18.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We plan to have two NSS(web) connector hosted in Azure in two different region for redundancy. Zscaler recommends to use same certificate in both the NSS since it will be active/standby. We are looking for a solution to

a) Standby Nss passes traffic when Active Nss has issue in reaching internet. [build custom monitoring to check internet status in Nss]
 b) Standby Nss turns on when Active Nss experience any issues [it might take couple of min, still nanolog can able to parse and re transmit logs for 1hr as per document ]

Azure propose using load balancer which doesn’t suit both the requirement. Does Zscaler/Anyone came into similar requirement and identified solution?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn0BCAQ/authenticating-user-without-an-interaction-from-user","lastmod":"2023-07-06T13:14:08.000Z","id":"0D54u00009evn0BCAQ"} -->
## Authenticating user without an interaction from user

- Source: https://community.zscaler.com/s/question/0D54u00009evn0BCAQ/authenticating-user-without-an-interaction-from-user
- Type: Q&A
- Posted: 2020-01-09T12:21:31.000Z
- Last activity: 2023-07-06T13:14:08.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is there a way to authenticate users without they interactions to put username and password?
 means for example using Kerberos or certificate authentication or whatever authentication method can do that.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn0mCAA/accessing-http-only-websites-over-zscaler","lastmod":"2023-05-31T08:51:27.000Z","id":"0D54u00009evn0mCAA"} -->
## Accessing HTTP only websites over Zscaler

- Source: https://community.zscaler.com/s/question/0D54u00009evn0mCAA/accessing-http-only-websites-over-zscaler
- Type: Q&A
- Posted: 2019-11-25T23:49:12.000Z
- Last activity: 2023-05-31T08:51:27.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I’m trying to access [http://ip.zscaler.com](http://ip.zscaler.com) and [http://neverssl.com](http://neverssl.com) but the page just hangs saying “Waiting for…?

In Z-App I can see we’re connected over port 443, and Wireshark confirms that there’s an HTTP Unauthorized error occurring due to “Proxy Authentication Required?, and that there’s an http protocol detected over encrypted port.

Does anyone know where in the Admin portal I can configure Zscaler to allow this?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn0wCAA/zens-supporting-ipv6","lastmod":"2023-05-31T09:24:36.000Z","id":"0D54u00009evn0wCAA"} -->
## ZENs supporting IPV6

- Source: https://community.zscaler.com/s/question/0D54u00009evn0wCAA/zens-supporting-ipv6
- Type: Q&A
- Posted: 2019-10-30T16:47:29.000Z
- Last activity: 2023-05-31T09:24:36.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Community,

I was wondering if you are aware of any Cloud Enforcement node that supports IPV6 ?
 I did the test with the web application : [http://test-ipv6.com](http://test-ipv6.com/)
 and it seems to be not supporting IPV6 when I test with Zapp.

If not, is it possible to assign an IPV6 to the VZEN in order to access IPV6 internet applications ?

Thanks for your answers.
 Best regards,
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn17CAA/saml-adfs-50-integration","lastmod":"2023-05-31T09:08:02.000Z","id":"0D54u00009evn17CAA"} -->
## SAML ADFS 5.0 Integration

- Source: https://community.zscaler.com/s/question/0D54u00009evn17CAA/saml-adfs-50-integration
- Type: Q&A
- Posted: 2019-10-14T12:18:07.000Z
- Last activity: 2023-05-31T09:08:02.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Does Zscaler support ADFS 5.0 integration, If yes are steps same as 2.0

Thanks

SB
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn1dCAA/okta-zscaler-20-app","lastmod":"2023-05-31T08:14:32.000Z","id":"0D54u00009evn1dCAA"} -->
## OKTA Zscaler 2.0 App

- Source: https://community.zscaler.com/s/question/0D54u00009evn1dCAA/okta-zscaler-20-app
- Type: Q&A
- Posted: 2019-09-10T13:39:03.000Z
- Last activity: 2023-05-31T08:14:32.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi there!

Has anyone tried migrating from the OKTA Zscaler app to the new Zscaler 2.0 app in their OKTA instance? I would love to hear your experience!

Tony
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn1zCAA/what-does-authentication-exemptions-list-mean-for-blocked-users","lastmod":"2023-05-31T08:52:10.000Z","id":"0D54u00009evn1zCAA"} -->
## What does Authentication Exemptions List mean for blocked users?

- Source: https://community.zscaler.com/s/question/0D54u00009evn1zCAA/what-does-authentication-exemptions-list-mean-for-blocked-users
- Type: Q&A
- Posted: 2019-06-27T12:56:17.000Z
- Last activity: 2023-05-31T08:52:10.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

We have added URLs to Authentication Exemptions List in the Admin Portal.
 And I have a group of users in the URL Filtering that are blocked from accessing Internet.

We’ve put block all for this group. So I was wondering, does this mean that these users actually have access to all URLs in this List?

Your help is much appreciated.

RT
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn27CAA/o365-teams-routing","lastmod":"2023-07-06T13:15:57.000Z","id":"0D54u00009evn27CAA"} -->
## O365 - Teams routing

- Source: https://community.zscaler.com/s/question/0D54u00009evn27CAA/o365-teams-routing
- Type: Q&A
- Posted: 2019-06-07T07:11:25.000Z
- Last activity: 2023-07-06T13:15:57.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hey there,

within my clients network, we use MPLS with Local Internet Breakout.
 We use Z-Application on each clients laptop.
 within Zscaler cloud, we used Microssoft recommended One Click.
 I have traced down traffic, and I have noticed following traffic pattern for MS Teams calls :

- within my company, we are creating P2P MS TEams calls, so we are not utilizing Zscaler tunnels via Internet (which is ok)
- when I`m calling outside, to Road Warrior, we are routed to realy.teams.microsoft servers …
- unfortunately, we are again routed to MPLS and then to our central DC for internet breakout …
  - I would assume, that Zen App will catch this traffic and send it to Zscaler …
  - Zen App is in Tunnel with Proxy mode, …
  - default route is towards MPLS … so from routing perspective it is clear, that we are routing towards MPLS …

Is there anything what we can do with Zscaler settings, that ZenApp would identify this MS teams flow towards Relay Teams and route it towards Zen nodes ?

Thank you !

Tomas
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2DCAQ/adfs-saml-authentication","lastmod":"2023-05-31T08:52:35.000Z","id":"0D54u00009evn2DCAQ"} -->
## ADFS / SAML Authentication

- Source: https://community.zscaler.com/s/question/0D54u00009evn2DCAQ/adfs-saml-authentication
- Type: Q&A
- Posted: 2019-06-05T08:07:45.000Z
- Last activity: 2023-05-31T08:52:35.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,
 New to ZAPP we have just rolled out the application via SCCM using the MSI. configured ADFS / SAML as per the Zscaler guide. (the guide is quite old and does not provide accturate info just to add)

I noticed when users sign into the ZAPP using their email address, they are not being prompted to enter their windows password. Is this the correct way? Once users enter their email address they start using the app, I can confirm this by going to [https://ip.zscaler.com/](https://ip.zscaler.com/)

Apologies if this has been answered before.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2ICAQ/provisioning-via-onelogin-scim","lastmod":"2023-05-31T08:52:27.000Z","id":"0D54u00009evn2ICAQ"} -->
## Provisioning via onelogin scim

- Source: https://community.zscaler.com/s/question/0D54u00009evn2ICAQ/provisioning-via-onelogin-scim
- Type: Q&A
- Posted: 2019-05-27T08:58:12.000Z
- Last activity: 2023-05-31T08:52:27.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,
 Anyone with experience for setting up Onelogin to provision to Zscaler via SCIM?
 It seems pretty straight forward, but currently provisioning all but new groups.

Thank you!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2XCAQ/hybrid-mode-directory-via-openldap-and-authentication-via-saml","lastmod":"2023-05-31T08:52:46.000Z","id":"0D54u00009evn2XCAQ"} -->
## Hybrid mode, directory via OpenLDAP and authentication via SAML?

- Source: https://community.zscaler.com/s/question/0D54u00009evn2XCAQ/hybrid-mode-directory-via-openldap-and-authentication-via-saml
- Type: Q&A
- Posted: 2019-05-10T13:08:01.000Z
- Last activity: 2023-05-31T08:52:46.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is it possible to have our Directory Type OpenLDAP and have authentication via SAML (Okta)? Within the Authentication Profile section, I see a section for Directory Type and and section for Authentication Type, but I am not sure if they are dependent or independent of each other. While reviewing the documentation on OpenLDAP, this is how authentication occurs, but I only want my directory (users) populated via OpenLDAP not authenticated.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2YCAQ/anyone-able-to-get-complete-okta-provisioning-to-work","lastmod":"2023-05-31T08:52:43.000Z","id":"0D54u00009evn2YCAQ"} -->
## Anyone able to get "COMPLETE" Okta provisioning to work?

- Source: https://community.zscaler.com/s/question/0D54u00009evn2YCAQ/anyone-able-to-get-complete-okta-provisioning-to-work
- Type: Q&A
- Posted: 2019-05-09T18:36:29.000Z
- Last activity: 2023-05-31T08:52:43.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Has anyone successfully got auto provisioning working properly? Department and Groups are not coming over to Zscaler. BTW we are using Okta. Much of the documentation is not aligning with the what I see in Okta or Zscaler. I have a case (794051) open on this.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2iCAA/mobile-locations-option","lastmod":"2023-05-31T08:52:27.000Z","id":"0D54u00009evn2iCAA"} -->
## Mobile locations option

- Source: https://community.zscaler.com/s/question/0D54u00009evn2iCAA/mobile-locations-option
- Type: Q&A
- Posted: 2019-03-20T20:08:52.000Z
- Last activity: 2023-05-31T08:52:27.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We have Zapp runing on regular laptops for traffic redirect and authentication with Okta and chrome devices which send the traffic via proxy config [https://cl.ly/0daf368fc2a2](https://cl.ly/0daf368fc2a2) but w/o user interaction for authentication.
 Is there an option to define a secondary mobile location?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3FCAQ/urls-with-unipass-client-based-authentication-not-loading-via-zsclaser","lastmod":"2023-05-31T08:53:44.000Z","id":"0D54u00009evn3FCAQ"} -->
## URLS with Unipass client based authentication not loading via zsclaser

- Source: https://community.zscaler.com/s/question/0D54u00009evn3FCAQ/urls-with-unipass-client-based-authentication-not-loading-via-zsclaser
- Type: Q&A
- Posted: 2018-11-25T16:18:55.000Z
- Last activity: 2023-05-31T08:53:44.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,
 I have a customer who uses IPSEC tunnel to send traffic to Zscaler. They have multiple websites which uses client certificate based authentication provided by UNIPASS, these don’t seems to work via Zscaler. I have tried adding them to auth bypass and ssl inspection bypass. Can you let me know if there is any way to make these work via Zscaler or bypass Zscaler is the only option ?

Thanks,
 Ajit
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3LCAQ/authentication-enforcement-for-remote-users","lastmod":"2023-07-06T13:18:12.000Z","id":"0D54u00009evn3LCAQ"} -->
## Authentication /Enforcement for Remote Users

- Source: https://community.zscaler.com/s/question/0D54u00009evn3LCAQ/authentication-enforcement-for-remote-users
- Type: Q&A
- Posted: 2018-11-21T04:57:58.000Z
- Last activity: 2023-07-06T13:18:12.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello All

I want to enable authentication and enforce Company’s remote users via zScaler. Apart from zScaler App, are there any other options which can be utilized to achieve this?

My Company has PAC files configured for every user. Right now if they browse the internet from a private/home internet, they are able to browse for a while and after that, the login screen appears. This seems an inconsistent behaviour and does not seem to be a good experience.

Thanks in Advance.
 C
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3PCAQ/nss-aws-connectivity","lastmod":"2025-08-22T07:27:26.000Z","id":"0D54u00009evn3PCAQ"} -->
## NSS Aws connectivity

- Source: https://community.zscaler.com/s/question/0D54u00009evn3PCAQ/nss-aws-connectivity
- Type: Q&A
- Posted: 2018-11-16T14:30:55.000Z
- Last activity: 2025-08-22T07:27:26.000Z
- Answers: 17
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi

i have an issue with the NSS server connectivity

my interfaces are in the Same Vlan but in different subnet , is that cause the issue

when i execute the nss test-firewall , i have all okey but in the report it make unable to resolve [smcacluster.zscalertwo.net](http://smcacluster.zscalertwo.net)

also , when i execute nss netstat | grep tcp , i have only an Syn to NSS on port 443

could you give me idea ?

regards
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3bCAA/gre-and-authentication-ip-of-gatewayzscalertwonet","lastmod":"2023-05-31T08:54:00.000Z","id":"0D54u00009evn3bCAA"} -->
## GRE and authentication - IP of gateway.zscalertwo.net

- Source: https://community.zscaler.com/s/question/0D54u00009evn3bCAA/gre-and-authentication-ip-of-gatewayzscalertwonet
- Type: Q&A
- Posted: 2018-10-30T16:44:58.000Z
- Last activity: 2023-05-31T08:54:00.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,
 we migrated some locations on a customer to GRE tunnels.
 All traffic will be routed per default route into the tunnel (only IP of GRE tunnel end is routed outside the tunnel).
 In this scenario we now get problems with traffic authentication that is handled through redirection to [gateway.zscalertwo.net](http://gateway.zscalertwo.net). I.e. in Vienna → resolving [gateway.zscaler.net](http://gateway.zscaler.net) per DNS leads to the same IP address as GRE tunnel endpoint address of this ZEN.
 (does not work that way ! Tunnel end IP is routed besides the tunnel and not inside!

This problem does not come up in every location. Only a few locations will resolve [gateway.zscalertwo.net](http://gateway.zscalertwo.net) to the same IP that is is used as gre tunnel end at the same ZEN.

Not a good idea to use such a important IP twice which could lead to such a Problem in this scenario.

Our Default route directs to an tunnel Interface. We do not want to build a more complex routing contruct using global zen ips etc. etc. as it is not really necessary - just to keep things simple (double usage of the IP is no good idea and will lead to a problem in this scenario).

Is it just an accidental slip or is there a hard reason why this has to be that way?

Regards
 Holger
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3wCAA/citrix-are-there-best-practises-or-experiences","lastmod":"2023-07-06T13:18:53.000Z","id":"0D54u00009evn3wCAA"} -->
## Citrix - Are there best practises or experiences?

- Source: https://community.zscaler.com/s/question/0D54u00009evn3wCAA/citrix-are-there-best-practises-or-experiences
- Type: Q&A
- Posted: 2018-09-20T13:50:23.000Z
- Last activity: 2023-07-06T13:18:53.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,
 we have some issues with the Citrix environment of one of our customers, basically with proper user authentication/identification without the IP Surrogate feature due to the shared IP addresses.
 We think, we can solve that with Kerberos, but any best practises reagrding citrix will help us.

Thanks in advance.

Best regards
 Andreas
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn49CAA/kerberos-udp-vs-tcp","lastmod":"2023-07-06T13:19:39.000Z","id":"0D54u00009evn49CAA"} -->
## Kerberos: UDP vs. TCP

- Source: https://community.zscaler.com/s/question/0D54u00009evn49CAA/kerberos-udp-vs-tcp
- Type: Q&A
- Posted: 2018-08-27T08:02:23.000Z
- Last activity: 2023-07-06T13:19:39.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We had some issues with Kerberos and remote users. Switching from UDP to TCP helped:
 More information here:

learn.microsoft.com
##### [How to force Kerberos to use TCP instead of UDP in Windows - Windows Server](https://learn.microsoft.com/en-US/troubleshoot/windows-server/windows-security/force-kerberos-use-tcp-instead-udp)

Describes how to force Kerberos to use TCP instead of UDP in Windows Server 2003, in Windows XP, and in Windows 2000.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4ACAQ/jumpcloud-as-sso-with-zscaler","lastmod":"2023-05-31T08:54:38.000Z","id":"0D54u00009evn4ACAQ"} -->
## JumpCloud as SSO with ZScaler

- Source: https://community.zscaler.com/s/question/0D54u00009evn4ACAQ/jumpcloud-as-sso-with-zscaler
- Type: Q&A
- Posted: 2018-08-25T21:47:40.000Z
- Last activity: 2023-05-31T08:54:38.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is JumpCloud supported as SSO by ZScaler?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4BCAQ/okta-for-zscaler-customers","lastmod":"2023-07-06T11:47:12.000Z","id":"0D54u00009evn4BCAQ"} -->
## Okta for Zscaler Customers

- Source: https://community.zscaler.com/s/question/0D54u00009evn4BCAQ/okta-for-zscaler-customers
- Type: Q&A
- Posted: 2018-08-24T12:42:01.000Z
- Last activity: 2023-07-06T11:47:12.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Team,
 Zscaler offers Okta for free to our Customers. I have deployed few customers for ZIA deployment.

okta.com
[image]

##### [Zscaler | Okta](https://www.okta.com/zscaler/)

Free SSO & Provisioning for Zscaler Rapidly deploy Zscaler organization-wide and increase end-user adoption. Okta Cloud Connect integrates Zscaler ...

Do we recommend/use Okta for customers having both ZIA+ZPA deployment?, I see that Okta for free only allows you to integrate with a single app. What if Cx add other application(like ZPA) to authenticate! Will Okta convert into the paid subscription if we add ZPA which is also a Zscaler module?
 Any tips?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4FCAQ/trial-test-review-license","lastmod":"2023-07-07T11:33:18.000Z","id":"0D54u00009evn4FCAQ"} -->
## Trial / Test / Review License?

- Source: https://community.zscaler.com/s/question/0D54u00009evn4FCAQ/trial-test-review-license
- Type: Q&A
- Posted: 2018-08-17T00:42:28.000Z
- Last activity: 2023-07-07T11:33:18.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We’re a software vendor working with a number of clients using Zscaler and are in need of a copy for testing purposes. Sales doesn’t seem responsive or to understand this. How might one go about buying/obtaining a single user license of the product for testing?

Thanks!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4HCAQ/sp-cert-and-azure-ad","lastmod":"2023-05-31T09:30:28.000Z","id":"0D54u00009evn4HCAQ"} -->
## SP cert and Azure AD

- Source: https://community.zscaler.com/s/question/0D54u00009evn4HCAQ/sp-cert-and-azure-ad
- Type: Q&A
- Posted: 2018-08-16T06:08:56.000Z
- Last activity: 2023-05-31T09:30:28.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello all,
 Your assistance please:
 With the SP certificate expiring soon, what are the steps needed to replace the SP certificate when authenticating to Azure AD?

Thx
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4MCAQ/zscaler-certificat-saml-change","lastmod":"2023-05-31T08:54:46.000Z","id":"0D54u00009evn4MCAQ"} -->
## Zscaler certificat SAML change

- Source: https://community.zscaler.com/s/question/0D54u00009evn4MCAQ/zscaler-certificat-saml-change
- Type: Q&A
- Posted: 2018-08-09T08:24:02.000Z
- Last activity: 2023-05-31T08:54:46.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

Zscaler has sent the notification abourt the SAML certificat, could you please tell me if it’s necessary to make the change of the Zscaler certificat on the SAML configuration if we do not sign saml request ? , if yes, why we have to do that ?

thank you
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4VCAQ/scimbased-user-provisioning-from-okta-to-zscaler","lastmod":"2023-07-07T11:33:28.000Z","id":"0D54u00009evn4VCAQ"} -->
## SCIM-based user provisioning from Okta to ZScaler

- Source: https://community.zscaler.com/s/question/0D54u00009evn4VCAQ/scimbased-user-provisioning-from-okta-to-zscaler
- Type: Q&A
- Posted: 2018-07-26T20:39:13.000Z
- Last activity: 2023-07-07T11:33:28.000Z
- Answers: 3
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is there any documentation available from ZScaler on what is required to configure SCIM-based provisioning from Okta side?
 Thanks,
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4nCAA/passing-saml-auth-token-from-one-browser-to-another","lastmod":"2023-05-31T08:14:45.000Z","id":"0D54u00009evn4nCAA"} -->
## Passing SAML auth token from one browser to another

- Source: https://community.zscaler.com/s/question/0D54u00009evn4nCAA/passing-saml-auth-token-from-one-browser-to-another
- Type: Q&A
- Posted: 2018-06-07T20:57:28.000Z
- Last activity: 2023-05-31T08:14:45.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is there a way to pass SAML token from one browser to another in a VDI environment with shared IP address (Citrix)?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4sCAA/customize-saml-authentication-redirect-page","lastmod":"2023-07-07T11:34:13.000Z","id":"0D54u00009evn4sCAA"} -->
## Customize SAML authentication redirect page

- Source: https://community.zscaler.com/s/question/0D54u00009evn4sCAA/customize-saml-authentication-redirect-page
- Type: Q&A
- Posted: 2018-05-26T20:41:07.000Z
- Last activity: 2023-07-07T11:34:13.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is there a way to customize SAML authentication redirect page with own company logo? It shows ZScaler logo.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn51CAA/which-login-attributes-can-be-used-with-ldap-when-my-organization-has-multiple-domains","lastmod":"2023-07-06T11:47:13.000Z","id":"0D54u00009evn51CAA"} -->
## Which login attributes can be used with LDAP when my organization has multiple domains?

- Source: https://community.zscaler.com/s/question/0D54u00009evn51CAA/which-login-attributes-can-be-used-with-ldap-when-my-organization-has-multiple-domains
- Type: Q&A
- Posted: 2018-05-10T18:48:00.000Z
- Last activity: 2023-07-06T11:47:13.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Zscaler uses the User/Email attribute to verify the login name that a user enters when logging in to the service for authentication.

[[934784e8-3cac-4965-8c36-eb52aab30b7a_display] 934784e8-3cac-4965-8c36-eb52aab30b7a_display794×225 14.1 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O3qbAAC)

The login attribute must be unique and in the form of an email address.Though it does not have to be a valid email address, the domain name must belong to the organization. If the value is not an email address, the service creates an email address by appending the primary domain name registered with the organization. If your organization has registered multiple domains and the value is not an email address, authentication will fail. To resolve this issue, Zscaler recommends that you use userPrincipalName, regardless of the number of domains hosted by the Zscaler service because the userPrincipalName is unique. Use sAMAccountName only if you have one domain hosted by the Zscaler service. Otherwise, you can use proxyAddresses, userPrincipalName or mail attributes.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn52CAA/adfs-30-on-windows-server-2012-r2-behavior-change-for-noninternet-explorer-browsers","lastmod":"2023-07-06T11:47:15.000Z","id":"0D54u00009evn52CAA"} -->
## ADFS 3.0 on Windows Server 2012 R2 – Behavior Change for Non-Internet Explorer Browsers

- Source: https://community.zscaler.com/s/question/0D54u00009evn52CAA/adfs-30-on-windows-server-2012-r2-behavior-change-for-noninternet-explorer-browsers
- Type: Q&A
- Posted: 2018-05-10T18:43:42.000Z
- Last activity: 2023-07-06T11:47:15.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Active Directory Federation Services (ADFS) 3.0 is built into Windows Server 2012 R2. In this environment, non-Internet Explorer browsers, like Google Chrome and Mozilla Firefox default to forms-based authentication, breaking single sign-on (SSO) authentication.

Following are the steps to change the authentication mechanism from forms-based to Integrated Windows Authentication (IWA).

1. Log in to your primary ADFS server.
2. Execute the following command to disable Extended Protection TokenCheck. Set-ADFSProperties ExtendedProtectionTokenCheck None

[[c137a12d-843f-47f1-b0da-6c561b2f7c3f_display] c137a12d-843f-47f1-b0da-6c561b2f7c3f_display855×381 52.9 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O8YxAAK)

1. Execute the following command to get the current list of supported user-agents for NTLM authentication. Get-ADFSProperties | Select -ExpandProperty WIASupportedUserAgents

4.Take all the values you received in step 3 and add Mozilla/5.0 to the end as an allowed user-agent.

Set-ADFSProperties -WIASupportedUserAgents @(“MSIE 6.0?, “MSIE 7.0?, “MSIE 8.0?, “MSIE 9.0?, “MSIE 10.0?, “Trident/7.0?, “MSIPC?, “Windows Rights Management Client?, “Mozilla/5.0?)

5.Restart the ADFS service on each of the ADFS servers for the changes to take effect. You do not need to make any changes to the proxy servers.

[[61b44d9a-28b2-4c07-bf33-d0ddb69cbf0f_display] 61b44d9a-28b2-4c07-bf33-d0ddb69cbf0f_display763×309 28.1 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O5dRAAS)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn54CAA/what-are-the-authentication-frequency-options","lastmod":"2023-05-31T08:14:49.000Z","id":"0D54u00009evn54CAA"} -->
## What are the Authentication Frequency options?

- Source: https://community.zscaler.com/s/question/0D54u00009evn54CAA/what-are-the-authentication-frequency-options
- Type: Q&A
- Posted: 2018-05-10T17:58:08.000Z
- Last activity: 2023-05-31T08:14:49.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

On the [Authentication Settings](https://help.zscaler.com/zia/about-authentication-profile) page, you can choose how often users are required to authenticate to the Zscaler service in Authentication Frequency.

When you go to Administration > Authentication > Authentication Settings, you can choose from one of the following options in the Authentication Frequency menu:

- Daily: Authentication will expire between 12 to 24 hours from the login time, depending on the time the user authenticated the day before.
- Only Once: This is the default authentication interval. Once users have logged in, they do not need to authenticate again as long as the cookie is saved in the browser or as an Adobe Flash object. (Typically, the cookie expires in about two years.) However, to log out of Zscaler, users must log out of the service explicitly or delete the cookie from their browser.

Note that Zscaler recommends choosing Only Once as your authentication frequency. For more information, see [Why does Zscaler recommend configuring the Authentication Frequency to be Only Once?](https://help.zscaler.com/zia/why-does-zscaler-recommend-configuring-authentication-frequency-be-only-once)

- Once Per Session: Authentication expires once the user closes the browser. In this case, no cookie is saved.
- Custom: Enter the number of days, between 1 and 180 inclusive. Authentication will be requested at midnight according to your timezone.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn56CAA/zab-and-saml-authentication","lastmod":"2023-05-31T08:55:21.000Z","id":"0D54u00009evn56CAA"} -->
## ZAB and SAML authentication

- Source: https://community.zscaler.com/s/question/0D54u00009evn56CAA/zab-and-saml-authentication
- Type: Q&A
- Posted: 2018-05-02T12:11:23.000Z
- Last activity: 2023-05-31T08:55:21.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Could ZAB be used for some locations and SAML for other locations for authentication?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn58CAA/how-to-logout-from-zscaler-while-using-saml-with-auto-provisioning-or-hosted-db-authentication","lastmod":"2023-07-06T11:47:20.000Z","id":"0D54u00009evn58CAA"} -->
## How to logout from Zscaler while using SAML with auto provisioning or Hosted DB authentication?

- Source: https://community.zscaler.com/s/question/0D54u00009evn58CAA/how-to-logout-from-zscaler-while-using-saml-with-auto-provisioning-or-hosted-db-authentication
- Type: Q&A
- Posted: 2018-04-27T07:05:25.000Z
- Last activity: 2023-07-06T11:47:20.000Z
- Answers: 0
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Follow one of the below steps to logout from Zscaler. This would help trigger re-authentication for the user.

- **Browse to [ip.zscaler.com](http://ip.zscaler.com) and click on logout button:**

[[image] image1004×694 58.4 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O874AAC)

- **Delete cookies from browser.**  `- **For Firefox:** - Goto Tools >> Options >> Privacy and Security >> History >> Show Cookies >> Remove All ![image|690x316](upload://wxiONe1Xaf7fCncgwJDskl3jQvj.png) ![image|507x499](upload://366dk9xo8SoVoWlpQaWQcy1VRqv.png)`
  - **For IE:**
    - Goto Settings >> Internet Options [[image] image405×531 14.6 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O6MxAAK)
  - **For Chrome:** - Goto Settings >> Clear Browsing Data [image]
- **Delete user from Zscaler Admin UI:** [[image] image598×616 17.5 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O3vkAAC)
- **Use CSV import to delete user/users from Zscaler data-base.** Refer below KB article for more details on CSV import. [Zscaler Help](https://help.zscaler.com/zia/how-do-i-configure-hosted-user-database)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn5ACAQ/using-azure-ad-domain-hint-for-saml-apps","lastmod":"2023-07-06T11:47:24.000Z","id":"0D54u00009evn5ACAQ"} -->
## Using Azure AD Domain Hint for SAML Apps

- Source: https://community.zscaler.com/s/question/0D54u00009evn5ACAQ/using-azure-ad-domain-hint-for-saml-apps
- Type: Q&A
- Posted: 2018-04-19T19:07:17.000Z
- Last activity: 2023-07-06T11:47:24.000Z
- Answers: 0
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Using Azure AD Domain Hint for SAML Apps : An Azure AD How-To Guide
 **Desired Outcome**
 For SP initiated SAML Single Sign On the application should not show the Azure AD Login page for user’s home realm discovery. It should directly be redirected to the ADFS page for authentication.
 If user is using the domain joined computer with organization user id and password, then after hitting the SP Initiated SSO URL they should be directly logged into the application. They should be getting the true SSO experience.

**Pre-requisites**
 Customer is using hybrid identity infrastructure, that means Azure AD with ADFS and federation setup.
 Application should support SAML Single Sign On with SP Initiated mode.
 Application should be configured in Azure AD for Single Sign On using gallery app or BYOA app

**Steps to implement**
 In the application Single Sign On configuration, configure the query string parameter along with the Azure AD Login page. The URL should look like this.
 Example:
 [https://login.windows.net/4f7437a6-3d76-4122-a907-624d965ba139/saml2?whr=contoso.com](https://login.windows.net/4f7437a6-3d76-4122-a907-624d965ba139/saml2?whr=contoso.com)
 *You will get the SAML Single Sign On URL from Azure AD application configuration wizard. Then append the query string parameter to it and then configure it in the SaaS application.
 [45%20PM]

**Consideration**
 1.Application is correctly configured for Single Sign with Azure AD.
 2.Application has ability to set the querystring parameter in the Login URL.
 3.All the users who are using the application are able to authenticate at ADFS server.
 4.If the user is accessing the application from outside of the network, then ADFS server should be public facing and reachable for authentication
 5.No external or guest users are using this application for Single Sign On
 6.If the application only supports the Federation Metadata import, then you might have to add the query string parameter in the Federation Metadata XML file first and then import it
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn5BCAQ/automatic-deprovisioning-of-users-while-using-okta-as-idp-and-saml-autoprovisioning","lastmod":"2023-07-06T11:47:25.000Z","id":"0D54u00009evn5BCAQ"} -->
## Automatic de-provisioning of users while using Okta as IDP and SAML auto-provisioning

- Source: https://community.zscaler.com/s/question/0D54u00009evn5BCAQ/automatic-deprovisioning-of-users-while-using-okta-as-idp-and-saml-autoprovisioning
- Type: Q&A
- Posted: 2018-04-18T13:56:26.000Z
- Last activity: 2023-07-06T11:47:25.000Z
- Answers: 3
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

**Requirement:**

- Automatically de-provision users on Zscaler when user is de-activated/removed at customer end.

**Solution:**

- If you are using Okta as IDP, users can be automatically deleted on Zscaler once user is removed in Okta.
- Please refer below article for configuration steps in Okta. [ZScaler Provisioning](https://saml-doc.okta.com/Provisioning_Docs/Zscaler_Provisioning.html)
- The configuration helps only if the user is deprovisioned in Okta i.e. `1. User un-assigned from Zscaler application in Okta 2. User account in Okta is de-activated`

**Note:** *It does not help in updating user attribute changes.*

- Below system log would be seen in Okta. [[image] image1715×138 11.6 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O6oTAAS)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn5sCAA/zscaler-mobility-manager-integration-with-okta-mdm","lastmod":"2023-05-31T08:56:59.000Z","id":"0D54u00009evn5sCAA"} -->
## ZScaler Mobility Manager integration with Okta MDM

- Source: https://community.zscaler.com/s/question/0D54u00009evn5sCAA/zscaler-mobility-manager-integration-with-okta-mdm
- Type: Q&A
- Posted: 2017-11-05T22:16:00.000Z
- Last activity: 2023-05-31T08:56:59.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We use Okta SSO with device trust feature to access SAML enabled app (G-Suite) and Okta Mobility Management. Could ZScaler Mobility manager integrate with Okta OMM so we can use ZScale app to protect our mobile devices?
 The same way as ZScaler Mobility Manager integrates with AirWatch.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn6CCAQ/sso-for-zscaler-admins-url","lastmod":"2023-05-31T09:30:55.000Z","id":"0D54u00009evn6CCAQ"} -->
## SSO for ZScaler admins URL

- Source: https://community.zscaler.com/s/question/0D54u00009evn6CCAQ/sso-for-zscaler-admins-url
- Type: Q&A
- Posted: 2017-09-13T12:00:38.000Z
- Last activity: 2023-05-31T09:30:55.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We’ve implemented SSO for admin authentication with SAML between Okta and ZScaler admin interface [https://admin.zscloud.net/](https://admin.zscloud.net/)
 However it appears that admins still can authenticate to [https://admin.zscloud.net/](https://admin.zscloud.net/) w/o redirect to Okta.
 Is there a way to enforce sp-initiated (ZScaler admin interface) auth flows to iDP (Okta)?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn6ECAQ/zscaler-options-for-onprem-ad-authentication","lastmod":"2025-01-22T03:05:01.000Z","id":"0D54u00009evn6ECAQ"} -->
## ZScaler options for onprem AD authentication

- Source: https://community.zscaler.com/s/question/0D54u00009evn6ECAQ/zscaler-options-for-onprem-ad-authentication
- Type: Q&A
- Posted: 2017-09-12T18:47:19.000Z
- Last activity: 2025-01-22T03:05:01.000Z
- Answers: 9
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We have all our users migrated into Okta SSO. ZScaler integration with Okta works well. We just have an issues with authenticating service accounts (on AD) for not Cloud aware applications.
 Is there any option to authenticate AD service accounts (application accounts) to ZScaler? Okta seems have an issue with service accounts (application accounts) typically used for applications updates from the internet.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnDGCAY/zscaler-client-macos-certificate-identity-popup-multiple-certificates","lastmod":"2023-05-31T08:18:55.000Z","id":"0D54u00009evnDGCAY"} -->
## zScaler Client [macOS] certificate identity popup - Multiple Certificates

- Source: https://community.zscaler.com/s/question/0D54u00009evnDGCAY/zscaler-client-macos-certificate-identity-popup-multiple-certificates
- Type: Q&A
- Posted: 2021-08-16T05:47:34.000Z
- Last activity: 2023-05-31T08:18:55.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I’m new to zScaler. As part of my work with our Mac fleet I’ve noticed when prompted to select the Identity Certificate I’m seeing multiple references to the same certificate in the “Choose an Identity�? dialog that appears. I can only see 1 reference to the identity certificate (SCEP deployed) in the keychain App.
 Has anyone else seen this? What am I missing?

Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnDKCAY/password-and-license-regex","lastmod":"2025-11-27T23:34:05.000Z","id":"0D54u00009evnDKCAY"} -->
## Password and License Regex

- Source: https://community.zscaler.com/s/question/0D54u00009evnDKCAY/password-and-license-regex
- Type: Q&A
- Posted: 2021-08-13T12:43:19.000Z
- Last activity: 2025-11-27T23:34:05.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

Do we have regex for password and License protection.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnDoCAI/account-balance-detection-regex","lastmod":"2023-05-31T08:18:59.000Z","id":"0D54u00009evnDoCAI"} -->
## Account Balance Detection Regex

- Source: https://community.zscaler.com/s/question/0D54u00009evnDoCAI/account-balance-detection-regex
- Type: Q&A
- Posted: 2021-07-12T21:51:38.000Z
- Last activity: 2023-05-31T08:18:59.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

### [#account-balances-detection-1](#account-balances-detection-1)Account balances detection

Account balances (can check for balances upto 999 billion and change, negative balances are checked for, $ sign is not checked for - assumes US format of currency presentation (grouping in 3 digits))

`\b1-90-9{0,2},0-9{0,3},0-9{3},0-9{3}\.0-9{2}\b`
 `\b1-90-9{0,2},0-9{3},0-9{3}\.0-9{2}\b`
 `\b1-90-9{0,2},0-9{3}\.0-9{2}\b`
 `\b1-90-9{0,2}\.0-9{2}\b`
 `\b0-9\.0-9{2}\b`

Copyright 2007-2021 ZSCALER, INC.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software�?), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS�?, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnDpCAI/date-formats-regex","lastmod":"2023-05-31T08:18:59.000Z","id":"0D54u00009evnDpCAI"} -->
## Date Formats Regex

- Source: https://community.zscaler.com/s/question/0D54u00009evnDpCAI/date-formats-regex
- Type: Q&A
- Posted: 2021-07-12T21:50:07.000Z
- Last activity: 2023-05-31T08:18:59.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

### [#date-related-1](#date-related-1)Date Related

#### [#us-date-of-birth-format-expected-mmddyy-or-mmddyyyy-2](#us-date-of-birth-format-expected-mmddyy-or-mmddyyyy-2)US-Date of Birth (Format expected MM/DD/YY or MM/DD/YYYY)

`13578-/31-/(0-9{2}|(19|20)0-9{2})|013578-/31-/(0-9{2}|(19|20)0-9{2})`

`102-/31-/(0-9{2}|(19|20)09-{2})`

`13-9-/30-/(0-9{2}|(19|20)0-9{2})|013-9-/30-/(0-9{2}|(19|20)0-9{2})`

`10-2-/30-/(0-9{2}|(19|20)09-{2})`

`1-9-/(1-9|(120-9))-/(0-9{2}|(19|20)0-9{2})|01-9-/(1-9|(120-9))-/(0-9{2}|(19|20)0-9{2})`

`10-2-/(1-9|(120-9))-/(0-9{2}|(19|20)09-{2})`

#### [#date-of-birth-in-ddmmyy-or-ddmmyyyy-format-englishaustralian-format-3](#date-of-birth-in-ddmmyy-or-ddmmyyyy-format-englishaustralian-format-3)Date of birth in DD/MM/YY or DD/MM/YYYY format (English/Australian format)

`30-/((13-9)|(10-2))-/(0-9{2}|(19|20)0-9{2})`

`31-/((13578)|(102))-/(0-9{2}|(19|20)0-9{2})`

`1-9-/((10-2)|(1-9))-/(0-9{2}|(19|20)0-9{2})|120-9/((10-2)|(1-9))/(0-9{2}|(19|20)0-9{2})`

#### [#date-of-birth-in-yyyymmdd-or-yymmdd-format-ansiinternational-format-4](#date-of-birth-in-yyyymmdd-or-yymmdd-format-ansiinternational-format-4)Date of birth in YYYY/MM/DD or YY/MM/DD format (ANSI/International format)

##### [#days-1-thru-29-of-the-month-5](#days-1-thru-29-of-the-month-5)Days 1 thru 29 of the month

`0-9{2}-/((10-2)|(0{0,1}1-9))-/((120-9)|(0{0,1}1-9))`

`190-9{2}-/((10-2)|(0{0,1}1-9))-/((120-9)|(0{0,1}1-9))`

`200-9{2}-/((10-2)|(0{0,1}1-9))-/((120-9)|(0{0,1}1-9))`

##### [#days-30-and-31-of-the-month-6](#days-30-and-31-of-the-month-6)Days 30 and 31 of the month

`0-9{2}-/((102)|(0{0,1}13578))-/31`

`190-9{2}-/((102)|(0{0,1}13578))-/31`

`200-9{2}-/((102)|(0{0,1}13578))-/31`

`0-9{2}-/((10-2)|(0{0,1}13-9))-/30`

`190-9{2}-/((10-2)|(0{0,1}13-9))-/30`

`200-9{2}-/((10-2)|(0{0,1}13-9))-/30`

#### [#date-of-birth-with-month-spelled-out-7](#date-of-birth-with-month-spelled-out-7)Date of birth with month spelled out

(Please use the pattern and phrases dictionary together - the number of occurrences of each dictionary in the engine should be same number ie if >4 for patterns combine that with >4 for phrases as well)

##### [#pattern-dictionary-8](#pattern-dictionary-8)Pattern dictionary

`01-9(th|st|nd|rd){0,1}-/, .*((189)|(20)){0,1}0-9{2}`

`1-9(th|st|nd|rd){0,1}-, .*((189)|(20)){0,1}0-9{2}`

`120-9(th|st|nd|rd){0,1}-/, .*((189)|(20)){0,1}0-9{2}`

`301(th|st){0,1}-/, .*((189)|(20)){0,1}0-9{2}`

##### [#phrases-dictionary-9](#phrases-dictionary-9)Phrases dictionary

`Jan`

`January`

`Feb`

`February`

`Mar`

`March`

`Apr`

`April`

`May`

`Jun`

`June`

`Jul`

`July`

`Aug`

`August`

`Sep`

`Sept`

`September`

`Oct`

`October`

`Nov`

`November`

`Dec`

`December`

Copyright 2007-2021 ZSCALER, INC.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software�?), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS�?, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnDqCAI/email-address-pattern-regex","lastmod":"2023-05-31T09:08:45.000Z","id":"0D54u00009evnDqCAI"} -->
## Email Address Pattern Regex

- Source: https://community.zscaler.com/s/question/0D54u00009evnDqCAI/email-address-pattern-regex
- Type: Q&A
- Posted: 2021-07-12T21:48:56.000Z
- Last activity: 2023-05-31T09:08:45.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

### [#email-addresses-1](#email-addresses-1)Email addresses

Checks main domain and 1st level subdomain email addresses as well

`a-zA-Z-_.a-zA-Z0-9*@-a-zA-Z0-9+\.a-zA-Z{2,5}\b`

`0-9-_.a-zA-Z0-9*@-a-zA-Z0-9+\.a-zA-Z{2,5}\b`

`a-zA-Z-_.a-zA-Z0-9*@-a-zA-Z0-9+\.-a-zA-Z0-9+\.a-zA-Z{2,5}\b`

`0-9-_.a-zA-Z0-9*@-a-zA-Z0-9+\.-a-zA-Z0-9+\.a-zA-Z{2,5}\b`

Copyright 2007-2021 ZSCALER, INC.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software�?), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS�?, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnDrCAI/source-code-detection-regex","lastmod":"2023-06-02T17:21:17.000Z","id":"0D54u00009evnDrCAI"} -->
## Source Code Detection Regex

- Source: https://community.zscaler.com/s/question/0D54u00009evnDrCAI/source-code-detection-regex
- Type: Q&A
- Posted: 2021-07-12T21:41:28.000Z
- Last activity: 2023-06-02T17:21:17.000Z
- Answers: 3
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

### [#source-code-detection-1](#source-code-detection-1)Source Code Detection

Language specific

#### [#cobol-2](#cobol-2)COBOL

Make sure that engine threshold is set to > 3 ID, we need at least 4 or more matches to be considered a COBOL program.

`IDENTIFICATION DIVISION\.`

`PROCEDURE DIVISION\.`

`FILE-CONTROL\.`

`ENVIRONMENT DIVISION\.`

`DATA DIVISION\.`

`PROGRAM-ID\.`

`WORKING-STORAGE SECTION\.`

`FILE SECTION\.`

#### [#sql-3](#sql-3)SQL

Set engine to greater than zero given the nature of SQL code

`sSeElLeEcCtT( |\n)+_a-zA-Z0-9.*, +fFrRoOmM( |\n)+_a-zA-Z0-9.`

`dDeElLeEtTeE +fFrRoOmM +_a-zA-Z0-9 *`

`iInNsSeErRtT +iInNtToO +_a-zA-Z0-9+ +vVaAlLuUeEsS *`

`cCrReEaAtTeE +((tTaAbBlLeE)|(dDaAtTabBaAsSeE)|(iInNdDeExX)) +`

`aAlLtTeErR +((tTaAbBlLeE)|(dDaAtTabBaAsSeE)|(iInNdDeExX)) +`

`dDrRoOpP +((tTaAbBlLeE)|(dDaAtTabBaAsSeE)|(iInNdDeExX)) +`

`gGrRoOuUpP +bByY`

`oOrRdDeErR +bByY`

#### [#plsql-4](#plsql-4)PL/SQL

Make sure that engine threshold is set to > 3 ID, we need atleast 4 or more matches to be considered a PL/SQL script.

`DECLARE *`

`BEGIN *`

`END;`

`a-zA-Za-zA-Z0-9*;`

#### [#c-c-sharp-5](#c-c-sharp-5)C# (C-Sharp)

Make sure that engine threshold is set to > 3 ID, we need atleast 4 or more matches to be considered a C# program.

`using +a-zA-Z0-9.+ *;`

`namespace +a-zA-Z0-9+ *\{`

`class +a-zA-Z0-9+ +\{`

`String.Format\(.+\) *;`

`Console.Write\(.+\) *;`

`Console.WriteLn\(.+\) *;`

#### [#javascript-6](#javascript-6)Javascript

Make sure that engine threshold is set to > 3 ID, we need at least 4 or more matches to be considered Javascript code.

`a-zA-Za-zA-Z0-9*\(.*\);{0,1}\n{0,1}.*`

`a-zA-Za-zA-Z0-9*\.length`

`a-zA-Za-zA-Z0-9*\.forEach`

`function +a-zA-Z0-9+ *\(.*\n*.*\)`

`var +a-zA-Z0-9+ *= *\b`

`console.log\(.*\)`

#### [#swift-7](#swift-7)Swift

Make sure that engine threshold is set to > 3 ID, we need at least 4 or more matches to be considered swift code.

`Resolution\(\)`

`struct +a-zA-Z0-9+ *\{`

`let +a-zA-Z0-9+ *= *.*`

`var +a-zA-Z0-9+ *= *.*`

`a-zA-Za-zA-Z0-9* *(===|!==) *.*`

`func +a-zA-Z0-9+\(.*\)`

Copyright 2007-2021 ZSCALER, INC.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software�?), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS�?, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnDsCAI/unix-related-files-regex","lastmod":"2023-05-31T08:18:59.000Z","id":"0D54u00009evnDsCAI"} -->
## Unix Related Files Regex

- Source: https://community.zscaler.com/s/question/0D54u00009evnDsCAI/unix-related-files-regex
- Type: Q&A
- Posted: 2021-07-12T21:39:54.000Z
- Last activity: 2023-05-31T08:18:59.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

### [#unix-related-expressions-1](#unix-related-expressions-1)Unix related expressions

Example passwd/shadow files

#### [#detect-etcpasswd-exfiltration-2](#detect-etcpasswd-exfiltration-2)Detect /etc/passwd exfiltration

`a-zA-Z-_a-zA-Z0-9{0,31}:x:0-9{1,5}:0-9{1,5}:^:*:(/^:*):/`

`0-9-_a-zA-Z0-9{0,31}:x:0-9{1,5}:0-9{1,5}:^:*:(/^:*):/`

#### [#detect-etcshadow-exfiltration-3](#detect-etcshadow-exfiltration-3)Detect /etc/shadow exfiltration

`a-zA-Z-_a-zA-Z0-9{0,31}:(\$^$\$^:+|*):0-9{1,5}:0-9{1,5}:0-9{1,5}:0-9{1,2}:^:*:^:*:`

`0-9-_a-zA-Z0-9{0,31}:(\$^$\$^:+|*):0-9{1,5}:0-9{1,5}:0-9{1,5}:0-9{1,2}:^:*:^:*:`

Copyright 2007-2021 ZSCALER, INC.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software�?), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS�?, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnDtCAI/usa-and-canada-address-regex","lastmod":"2023-05-31T09:08:28.000Z","id":"0D54u00009evnDtCAI"} -->
## USA and Canada Address Regex

- Source: https://community.zscaler.com/s/question/0D54u00009evnDtCAI/usa-and-canada-address-regex
- Type: Q&A
- Posted: 2021-07-12T21:38:24.000Z
- Last activity: 2023-05-31T09:08:28.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

### [#us-and-canadian-addresses-1](#us-and-canadian-addresses-1)US and Canadian Addresses

#### [#us-street-address-2](#us-street-address-2)US Street Address

Expected format: #####(upto 6 digits) Any string, 2 Letter State Abbreviation, 5 digit zip code followed by optional 4 digit zip code extension

2 line addresses are welcome

`1-90-9{0,5},{0,1} +.*,{0,1} +A-Za-z{2},{0,1} +0-9{5}`

`1-90-9{0,5},{0,1} +.*,{0,1} +A-Za-z{2},{0,1} +0-9{5}-0-9{4}`

`1-90-9{0,5},{0,1}\s+.*,{0,1}\n.*\s+\w{2}\s+\d{5}`

`1-90-9{0,5},{0,1}\s+.*,{0,1}\n.*\s+\w{2}\s+\d{5}-\d{4}`

#### [#canadian-street-addresses-3](#canadian-street-addresses-3)Canadian street addresses

Checks for proper format and accepts 2 line addresses as well

`1-90-9{0,5}-0-9*,{0,1} +.*,{0,1} +A-Za-z0-9A-Za-z +0-9A-Za-z0-9`

`1-90-9{0,5}-0-9*,{0,1} +.*,{0,1} *\n.*\s+A-Za-z0-9A-Za-z +0-9A-Za-z0-9`

Copyright 2007-2021 ZSCALER, INC.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software�?), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS�?, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnENCAY/transparent-authentication","lastmod":"2023-05-31T08:50:49.000Z","id":"0D54u00009evnENCAY"} -->
## Transparent authentication

- Source: https://community.zscaler.com/s/question/0D54u00009evnENCAY/transparent-authentication
- Type: Q&A
- Posted: 2020-03-31T09:11:16.000Z
- Last activity: 2023-05-31T08:50:49.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello
 I’am using Azure ad as IDP and I want to activate transparent authentication with the zapp agent (once the agent installed on the computer, the user will not need to enter these credentials manually, it will be authenticated automatically)
 For that:
 I have customized my packaging with the two attributes cloudname and userdomain,
 I added the urls ( [portal.azure.com](http://portal.azure.com) + [login.microsoftonline.com](http://login.microsoftonline.com) )in the local intranet zone I activated IWA
 And I bypass the sso url of the idp through the proxy.
 However the agent always asks me to perform the authentication.
 do you have any idea about this problem ?

Thank you
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eyjiLCAQ/way-to-verify-using-certificate","lastmod":"2025-10-30T09:13:27.000Z","id":"0D54u00009eyjiLCAQ"} -->
## Way to verify using certificate

- Source: https://community.zscaler.com/s/question/0D54u00009eyjiLCAQ/way-to-verify-using-certificate
- Type: Q&A
- Posted: 2023-05-10T05:57:04.000Z
- Last activity: 2025-10-30T09:13:27.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

We’ve been using custom certificate for SSL Inspection so far and plan to change to use Zscaler certificate.
 Hundreds of servers and massive numbers of user PCs will be using Zscaler certificate after cut-over.
 Is there any effective way to verify if PCs or servers are using Zscaler certificate rather than the custom certificate after cut-over? i.e> checking like Analytics log on ZIA portal.
 Also, I want to know if there is any recommended way to cut-over certificate in our case, like from custom certificate(current default certificate) to zscaler certificate.
 As our plan for cut-over, I am going to enable Default Certificate on Zscaler Intermediate CA Certificate.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eyjoBCAQ/zscaler-windows-client-for-some-users-ask-only-username-for-mfa-for-some-users-ask-username-and-password","lastmod":"2025-10-31T20:28:33.000Z","id":"0D54u00009eyjoBCAQ"} -->
## Zscaler windows client for some users ask only username for MFA, for some users ask username and password

- Source: https://community.zscaler.com/s/question/0D54u00009eyjoBCAQ/zscaler-windows-client-for-some-users-ask-only-username-for-mfa-for-some-users-ask-username-and-password
- Type: Q&A
- Posted: 2023-05-02T14:52:14.000Z
- Last activity: 2025-10-31T20:28:33.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello, all.

I have a question. Can’t find fix by myself.

Zscaler windows client for some users on authentication ask only username for MFA, for some users ask username and password.

Identity Providers is Azure User SSO

For all users assign the same policy, same windows version, same Zscaler client version.

What could be?

Thx
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eyjr6CAA/digicert-certificate-issue-in-zscaler","lastmod":"2023-06-05T15:09:18.000Z","id":"0D54u00009eyjr6CAA"} -->
## Digicert certificate issue in Zscaler

- Source: https://community.zscaler.com/s/question/0D54u00009eyjr6CAA/digicert-certificate-issue-in-zscaler
- Type: Q&A
- Posted: 2023-06-01T15:48:47.000Z
- Last activity: 2023-06-05T15:09:18.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

In Zscaler We have bypassed SSL inspection for our own domain as we have a root certificate issued by Digicert but many of the application are having issue. When I am trying to access the site the below error page is coming, showing Certificate is not valid The site i snot secure. But it is working fine without Zscaler no error page is coming.
 [image]
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykLbCAI/multiple-idp-to-same-azure-ad-tenant","lastmod":"2025-08-22T07:27:48.000Z","id":"0D54u00009eykLbCAI"} -->
## Multiple IdP to same Azure AD Tenant

- Source: https://community.zscaler.com/s/question/0D54u00009eykLbCAI/multiple-idp-to-same-azure-ad-tenant
- Type: Q&A
- Posted: 2023-05-19T19:49:04.000Z
- Last activity: 2025-08-22T07:27:48.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Does anyone know how to configure multiple IdP configurations to the same Azure AD tenant? I am getting a warning message that the IdP Entity ID cannot be the same.

“A resource already exists with the field value [https://sts.windows.net/xxxxxxxxxxxxxxx](https://sts.windows.net/xxxxxxxxxxxxxxx)”

This seems to be unique to the Azure tenant. Even though I am setting up a new IdP using a XML and certificate.

We have a need to create 2 IdPs, one that uses UPN as the required claim and the other that uses EMAIL as the required claim.

Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykLcCAI/group-membership-not-synching-with-onelogin-as-idp","lastmod":"2023-06-02T17:21:01.000Z","id":"0D54u00009eykLcCAI"} -->
## Group membership Not Synching with OneLogin as IDP

- Source: https://community.zscaler.com/s/question/0D54u00009eykLcCAI/group-membership-not-synching-with-onelogin-as-idp
- Type: Q&A
- Posted: 2023-05-19T18:34:00.000Z
- Last activity: 2023-06-02T17:21:01.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We have a SCIM integration between Zscaler and OneLogin. Initially all group membership was synching and everything looked good. However I see now the group membership of users are not getting updated in Zscaler, however they are updated in OneLogin.

Is there a way I can see what is broken? I dont see much logs on both ends, Zscaler and OneLogin.

Did any of you faced similar issue? Please help.

Thanks In advance.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykLeCAI/idp-and-zscaler-group-reflection","lastmod":"2023-06-02T16:21:52.000Z","id":"0D54u00009eykLeCAI"} -->
## IDP and Zscaler group reflection

- Source: https://community.zscaler.com/s/question/0D54u00009eykLeCAI/idp-and-zscaler-group-reflection
- Type: Q&A
- Posted: 2023-05-19T12:51:20.000Z
- Last activity: 2023-06-02T16:21:52.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

If user is a member of AD group in IDP( Azure), all the groups should be assigned to user in Zscaler user management section. But one important group was missing ( which was required to enable ZPA in ZCC) and had to manually assign from user management section.
 Why this would have happened?
 Any sync issues, if so, what can be done here
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eylAaCAI/log-encryption-at-rest","lastmod":"2023-06-02T17:16:53.000Z","id":"0D54u00009eylAaCAI"} -->
## Log encryption at rest

- Source: https://community.zscaler.com/s/question/0D54u00009eylAaCAI/log-encryption-at-rest
- Type: Q&A
- Posted: 2023-05-05T08:44:33.000Z
- Last activity: 2023-06-02T17:16:53.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,
 we received internal inquiry about the encryption of logs in ZScaler. I cannot find any information in the user documentation. Can you please clarify which encryption algorithm is applied to data logs at rest?

i have checked also this post of June 22 [What is the Encryption used in the system where the LSS/NSS logs are stored? - Platform - Zenith (zscaler.com)](https://community.zscaler.com/t/what-is-the-encryption-used-in-the-system-where-the-lss-nss-logs-are-stored/16971), but there is no reply.

Thanks in advance
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eylDFCAY/gsuite-as-an-idp","lastmod":"2023-06-05T22:21:27.000Z","id":"0D54u00009eylDFCAY"} -->
## GSuite as an iDP

- Source: https://community.zscaler.com/s/question/0D54u00009eylDFCAY/gsuite-as-an-idp
- Type: Q&A
- Posted: 2023-05-04T21:42:20.000Z
- Last activity: 2023-06-05T22:21:27.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,
 As we are trying to move away from Okta due to increased cost of the ownership.
 Could we use GSuite as an IDP for ZScaler ZIA and ZPA?
 Thanks,
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eylLECAY/video-zscaler-nss-deployment-with-azurerm-template","lastmod":"2023-06-05T04:51:35.000Z","id":"0D54u00009eylLECAY"} -->
## [Video] Zscaler NSS Deployment with Azurerm Template

- Source: https://community.zscaler.com/s/question/0D54u00009eylLECAY/video-zscaler-nss-deployment-with-azurerm-template
- Type: Q&A
- Posted: 2023-05-05T00:50:06.000Z
- Last activity: 2023-06-05T04:51:35.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

**Summary**
 The Nanolog Streaming Service (NSS) uses a virtual machine (VM) to stream traffic logs in real-time from the Zscaler Nanolog to your security information and event management (SIEM) system, such as Splunk or ArcSight, enabling real-time alerting, correlation with the logs of your other devices, and long-term local log archival. Zscaler offers the following NSS subscriptions:
 • NSS for Web: Streams web and mobile traffic logs.
 • NSS for Firewall: Streams logs from the Zscaler next-generation firewall.

In this video, we’ll explore
 Timeline:
 0:00:03;13 – 0:00:16;27 – Introduction
 0:00:17;00 – 0:00:44;21 – Pre-Requisites
 0:00:44;29 – 0:02:33;05 – Deployment Types
 0:02:33;05 – 0:03:13;28 – VHD Deployment Regions
 0:03:15;12 - 0:04:01;08 – GitHub Repository
 0:04:05;11 - 0:06:35;16 – Azurerm Deployment Wizard
 0:06:36;05 – 0:07:06;19 – ZIA Nanolog Streaming Service
 0:07:06;23 – 0:07:51;14 – Configuring NSS VM

References:

- [NSS Deployment Guide for Microsoft Azure](https://help.zscaler.com/zia/nss-deployment-guide-microsoft-azure)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009iZoovCAC/block-undecryptable-traffic-policy-setting-and-sites-that-offer-but-dont-require-client-certificate-authentication","lastmod":"2025-12-18T14:19:00.000Z","id":"0D54u00009iZoovCAC"} -->
## "Block Undecryptable Traffic" policy setting and sites that offer (but don't require) client certificate authentication

- Source: https://community.zscaler.com/s/question/0D54u00009iZoovCAC/block-undecryptable-traffic-policy-setting-and-sites-that-offer-but-dont-require-client-certificate-authentication
- Type: Q&A
- Posted: 2023-06-23T11:11:13.000Z
- Last activity: 2025-12-18T14:19:00.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We are new-ish ZIA customers and are noticing that the <strong style="font-family: &quot;Calibri&quot;, sans-serif; font-size: 11pt;">Block Undecryptable Traffic</strong><span style="font-family: &quot;Calibri&quot;, sans-serif; font-size: 11pt;"> setting in our default/main SSL policy rule is interrupting access to a lot of sites (not most, but enough that it's becoming a hassle to add them all to the SSL bypass).</span></p><p><br></p><p><span style="font-family: &quot;Calibri&quot;, sans-serif; font-size: 11pt;">How are others using this feature, Block Undecryptable Traffic? Do you encounter this issue as well?</span></p><p><br></p><p><span style="font-family: &quot;Calibri&quot;, sans-serif; font-size: 11pt;">The UI indicates that the traffic should only be blocked if the mutual auth is required, not just offered. This is not my experience. I'm working with Support on this now.</span></p><p><br></p><p><br></p><p><img src="/zenith/sfc/servlet.shepherd/version/download/0684u00000b5MeJ?asPdf=false&amp;operationContext=CHATTER"></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009jZowDCAS/usa-general-info-regex","lastmod":"2023-06-28T07:53:16.000Z","id":"0D54u00009jZowDCAS"} -->
## USA General Info Regex

- Source: https://community.zscaler.com/s/question/0D54u00009jZowDCAS/usa-general-info-regex
- Type: Q&A
- Posted: 2021-07-12T21:37:01.000Z
- Last activity: 2023-06-28T07:53:16.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

### [us-general-regex-1](us-general-regex-1)US General Regex

#### [us-date-of-birth-format-expected-mmddyy-or-mmddyyyy-2](us-date-of-birth-format-expected-mmddyy-or-mmddyyyy-2)US-Date of Birth (Format expected MM/DD/YY or MM/DD/YYYY)

`13578-/31-/(0-9{2}|(19|20)0-9{2})|013578-/31-/(0-9{2}|(19|20)0-9{2})`

`102-/31-/(0-9{2}|(19|20)09-{2})`

`13-9-/30-/(0-9{2}|(19|20)0-9{2})|013-9-/30-/(0-9{2}|(19|20)0-9{2})`

`10-2-/30-/(0-9{2}|(19|20)09-{2})`

`1-9-/(1-9|(120-9))-/(0-9{2}|(19|20)0-9{2})|01-9-/(1-9|(120-9))-/(0-9{2}|(19|20)0-9{2})`

`10-2-/(1-9|(120-9))-/(0-9{2}|(19|20)09-{2})`

#### [us-passport-3](us-passport-3)US Passport

`\d{8,9}\D`

#### [us-itintax-id-4](us-itintax-id-4)US ITIN/Tax ID

`\b(90-9{2}-{0,1}780-9-{0,1}0-9{4}\D)`

#### [us-military-id-5](us-military-id-5)US Military ID

`\b(\d{10}\D)`

#### [us-bank-account-numbers-6](us-bank-account-numbers-6)US Bank Account numbers

`\b(\d{6,17}\D)`

#### [us-medicare-numbers-7](us-medicare-numbers-7)US Medicare Numbers

##### [old-format-8](old-format-8)Old Format:

`\bhHpP0-9eEpPsSkK0-90-9{5,6}-{0,1}0-9{0,2}\b`

##### [new-mbid-medicare-beneficiary-id-format-9](new-mbid-medicare-beneficiary-id-format-9)New MBID (Medicare Beneficiary ID) format

`\b1-9aAc-hC-HjJkKmMnNp-yP-Y0-9a-yA-Y0-9aAc-hC-HjJkKmMnNp-yP-Y0-9a-yA-Y0-9aAc-hC-HjJkKmMnNp-yP-Y{2}0-9{2}\b`

##### [commercial-10](commercial-10)Commercial:

`\b7756\d{6}\b|\b8889\d{6}\b|\b99129\d{6}\b`

##### [medicare-per-some-organizations-11](medicare-per-some-organizations-11)Medicare: (per some organizations)

`\bsS000-9{6}\b`

Medicaid numbers:

`\bnN00bB0-9{8}\b`

Copyright 2007-2021 ZSCALER, INC.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software�?), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS�?, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009jZp0rCAC/international-telephone-number-patterns-regex","lastmod":"2023-06-28T07:54:33.000Z","id":"0D54u00009jZp0rCAC"} -->
## International Telephone Number Patterns Regex

- Source: https://community.zscaler.com/s/question/0D54u00009jZp0rCAC/international-telephone-number-patterns-regex
- Type: Q&A
- Posted: 2021-07-12T21:46:18.000Z
- Last activity: 2023-06-28T07:54:33.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

### [international-telephone-numbers-1](international-telephone-numbers-1)International Telephone Numbers

Please search for the country you are looking for.

#### [indonesia-2](indonesia-2)Indonesia

`\b+62-( \d{3,4}-) ?\d{3,4}- \d{3,4}\b|\b+62-( \d{3,4}-) ?\d{3,4}- \d{3,4}- \d{3,4}\b`

`\b0062-( \d{3,4}-) ?\d{3,4}- \d{3,4}\b|\b0062-( \d{3,4}-) ?\d{3,4}- \d{3,4}- \d{3,4}\b`

#### [taiwan-3](taiwan-3)Taiwan

`\b+886 (-\d{1,4} -\d{5,8}\b|\b\d{1,4} -\d{5,8}\b`

#### [cambodia-4](cambodia-4)Cambodia

`\b855 *-(? *0-9{2} *-)? *0-9{3} *-? *0-9{3,4}\b|\b00855 *-(? *0-9{2} *-)? *0-9{3} *-? *0-9{3,4}\b`

##### [without-international-code-5](without-international-code-5)Without international code:

`\b0 *0-9{2} *-? *0-9{3} *-? *0-9{3,4}\b`

#### [singapore-6](singapore-6)Singapore

`\b+65- \d{4}- \d{4}\b|\b00065- \d{4}- \d{4}\b`

#### [malaysia-7](malaysia-7)Malaysia

`\b+60-( \d{1,3}-) \d{6,8}\b|\b0060-( \d{1,3}-) \d{6,8}\b`

#### [hong-kong-8](hong-kong-8)Hong Kong

`\b+852- \d{4}- \d{4}\b`

#### [china-9](china-9)China

`\b+86-( \d{2,3}-) (\d{7,8}|\d{3,4}- \d{3,4})\b`

`\b+86-( \d{3}-) \d{4}- ?\d{4}\b|\b+86-( \d{3}-) \d{4}- ?\d{4}- ?\d{2}\b`

#### [vietnam-10](vietnam-10)Vietnam

`\b+84-( \d{2,3}-) \d{3,4}- ?\d{3,4}\b|\b0084-( \d{2,3}-) \d{3,4}- ?\d{3,4}\b`

Updated previous regex to:

`+84-( \d{2,3}-) \d{3,4}- ?\d{3,4}\b|\b0084-( \d{2,3}-) \d{3,4}- ?\d{3,4}\b`

#### [philippines-11](philippines-11)Philippines

`\b+63-( (2-) ?\d{4}- ?\d{4}|13-9\d- ?\d{3}- ?\d{4})\b`

`\b0063-( (2-) ?\d{4}- ?\d{4}|13-9\d- ?\d{3}- ?\d{4})\b`

#### [laos-12](laos-12)Laos

`\b+856-( (0\d{2}|\d{2})-) \d{6}\b|\b00856-( (0\d{2}|\d{2})-) \d{6}\b`

#### [thailand-13](thailand-13)Thailand

`\b+66-( 2-) \d{7}\b|\b+66-( \d{2}-) \d{6}\b`

`\b0066-( 2-) \d{7}\b|\b0066-( \d{2}-) \d{6}\b`

#### [japan-14](japan-14)Japan

##### [landlines-with-or-without-international-code-15](landlines-with-or-without-international-code-15)Landlines with or without international code

`\b81- ?\d{2,5}-?\d{1,4}-?\d{4}\b|\b\d{2,5}-?\d{1,4}-?\d{4}\b`

##### [cell-phone-with-or-without-international-code-16](cell-phone-with-or-without-international-code-16)Cell phone with or without international code

`\b81- ?\d{3}-?\d{4}-?\d{4}\b|\b\d{3}-?\d{4}-?\d{4}\b`

##### [toll-free-numbers-with-or-without-international-code-17](toll-free-numbers-with-or-without-international-code-17)Toll-free numbers with or without international code

`\b81-?0120-?\d{3}-?\d{3}\b|\b0120-?\d{3}-?\d{3}\b`

##### [in-order-to-obtain-the-correct-counters-japan-phone-regex-updated-to-18](in-order-to-obtain-the-correct-counters-japan-phone-regex-updated-to-18)In Order to obtain the correct counters Japan Phone regex updated to:

#### [pattern-1-19](pattern-1-19)Pattern 1:

`\b0\d{2}- +\d{4}- +\d{4}\b|\b0\d{1}- +\d{4}- +\d{4}\b|\b0\d{3}- +\d{2}- +\d{4}\b|\b0\d{2}- +\d{3}- +\d{4}\b`

#### [pattern-2-20](pattern-2-20)Pattern 2:

For International dialing 81 is followed by an area code it will be required to have two patterns due to 128 pattern length limit
 `\b81- ?\d{2}- ?\d{4}- ?\d{4}\b|\b81- ?\d{1}- ?\d{4}- ?\d{4}\b|\b81- ?\d{3}- ?\d{2}- ?\d{4}\b`

#### [pattern-3-21](pattern-3-21)Pattern 3:

`\b81- ?\d{2}- ?\d{3}- ?\d{4}\b`

Copyright 2007-2021 ZSCALER, INC.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software�?), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS�?, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009jZp5lCAC/international-drivers-license-numbers-regex","lastmod":"2023-06-28T07:54:29.000Z","id":"0D54u00009jZp5lCAC"} -->
## International Drivers License Numbers Regex

- Source: https://community.zscaler.com/s/question/0D54u00009jZp5lCAC/international-drivers-license-numbers-regex
- Type: Q&A
- Posted: 2021-07-12T21:48:13.000Z
- Last activity: 2023-06-28T07:54:29.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

### [international-drivers-licenses-1](international-drivers-licenses-1)International Drivers Licenses

Please search for the country you are looking for.

#### [international-dl-uk-2](international-dl-uk-2)International DL UK

`\dA-Za-z0-9{4}\d0156(01-9|12\d|301)\d{2}A-Za-z0-9{3}A-Za-z{2}\d{2}\D`

`A-Za-zA-Za-z0-9{4}\d0156(01-9|12\d|301)\d{2}A-Za-z0-9{3}A-Za-z{2}\d{2}\D`

#### [international-dl-finland-3](international-dl-finland-3)International DL Finland

`A-Za-zA-Za-z0-9{9}^A-Za-z0-9`

`\dA-Za-z0-9{9}^A-Za-z0-9`

#### [international-dl-portugal-4](international-dl-portugal-4)International DL Portugal

`A-Za-zA-Za-z0-9{7,9}^A-Za-z0-9`

`\dA-Za-z0-9{7,9}^A-Za-z0-9`

#### [international-dl-spain-5](international-dl-spain-5)International DL Spain

`A-Za-zA-Za-z0-9{8}^A-Za-z0-9`

`\dA-Za-z0-9{8}^A-Za-z0-9`

#### [international-dl-france-6](international-dl-france-6)International DL France

`\d{12}\D`

#### [international-dl-japan-7](international-dl-japan-7)International DL Japan

`\d{12}\D`

#### [international-dl-belgium-8](international-dl-belgium-8)International DL Belgium

`\d{10}\D`

#### [international-dl-netherlands-9](international-dl-netherlands-9)International DL Netherlands

`\d{10}\D`

#### [international-dl-sweden-10](international-dl-sweden-10)International DL Sweden

`\d{10}\D`

#### [international-dl-taiwan-also-doubles-as-taiwan-id-number-11](international-dl-taiwan-also-doubles-as-taiwan-id-number-11)International DL Taiwan (also doubles as Taiwan ID number)

`\ba-zA-Z12\d{8}\b`

#### [international-dl-philippines-12](international-dl-philippines-12)International DL Philippines

`\ba-zA-Z\d{2}-?\d{2}-?\d{6}\b`

Copyright 2007-2021 ZSCALER, INC.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software�?), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS�?, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009jZp5uCAC/national-ids-regex","lastmod":"2023-06-28T07:53:14.000Z","id":"0D54u00009jZp5uCAC"} -->
## National IDs Regex

- Source: https://community.zscaler.com/s/question/0D54u00009jZp5uCAC/national-ids-regex
- Type: Q&A
- Posted: 2021-07-12T21:43:52.000Z
- Last activity: 2023-06-28T07:53:14.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

### [national-ids-1](national-ids-1)National IDs

Please search for your country name.

#### [cambodia-2](cambodia-2)Cambodia

`\b\d{10}\b`

#### [malaysia-3](malaysia-3)Malaysia

`\b\d{2}(01-9|10-2)(01-9|120-9|301)- ?\d{2}- ?\d{4}\b`

#### [hong-kong-4](hong-kong-4)Hong Kong

`\ba-hA-Hj-nJ-NpPr-tR-TvwyzVWYZ\d{6}0-9aA\b|\bwWxX\d{6}0-9aA\b|\bxXa-eA-EgGhH\d{6}0-9aA\b`

##### [another-format-5](another-format-5)Another Format

`A-Z{1,2}\d{6}(?0-9A)?`

#### [macau-6](macau-6)Macau

`\b\d{7}(\d)\b`

##### [another-format-7](another-format-7)Another Format

`\b\d{7}(\d)`

#### [vietnam-8](vietnam-8)Vietnam

`\b\d{12}\b`

#### [philippines-9](philippines-9)Philippines

`\b\d{4}- ?\d{7}- ?\d\b`

#### [indonesia-10](indonesia-10)Indonesia

`\b\d{2}01237\d{3}0-7\d01\d{7}\b`

#### [singapore-11](singapore-11)Singapore

`\bFfGgSsTt\d{7}A-Za-z\b`

#### [china-12](china-12)China

`\b\d{6}(189|201)\d{2}(01-9|10-2)(01-9|120-9|301)\d{4}\b`

#### [thailand-13](thailand-13)Thailand

`\b0-8-?\d{4}-?\d{5}-?\d{2}-?\d\b`

#### [japan-14](japan-14)Japan

Please use support phrases for this. The definition is so generic that if used alone, there will be guaranteed false positives.

#### [south-korea-15](south-korea-15)South Korea

`\b\d{6}-\d{7}\b`

Copyright 2007-2021 ZSCALER, INC.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software�?), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS�?, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009jZpG7CAK/installing-tls-ssl-root-certificates-to-nonstandard-environments","lastmod":"2025-11-18T23:28:02.000Z","id":"0D54u00009jZpG7CAK"} -->
## Installing TLS / SSL ROOT Certificates to non-standard environments

- Source: https://community.zscaler.com/s/question/0D54u00009jZpG7CAK/installing-tls-ssl-root-certificates-to-nonstandard-environments
- Type: Q&A
- Posted: 2020-03-30T18:28:26.000Z
- Last activity: 2025-11-18T23:28:02.000Z
- Answers: 68
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Over 90% of websites now use TLS encryption (HTTPS) as the access method. Enterprises utilise TLS inspection for Advanced Threat Protection, Access controls, Visibility, and Data-Loss Prevention. Zscaler App is deployed on Windows and Mac devices and the Zscaler certificate is installed in the appropriate system Root Certificate Store so that the system/browser trusts the synthetic certificate generated during TLS Inspection.

However, a number of applications do not read the system certificate store – for example Python – and moreover developer tools such as Docker need to have the Root certificate installed in order for the applications which run there to trust the synthetic certificates.

This document describes techniques to deploy the Zscaler Root Certificate into these applications. It can be used as a basis to expand the certificate deployment into other applications. This document assumes you are using the Zscaler Intermediate certificate for TLS / SSL Inspection – if you are using a custom certificate for TLS / SSL Inspection, then you should replace all references to Zscaler Root with your custom Root certificate.

Select your OS from the list below:

- [Windows](heading--windows)
- [Linux](heading--linux)
- [MacOS](heading--macos)
- [Docker](heading--docker)

### Windows

Most applications will utilise the Windows certificate store, however many more especially those ported from Linux, start to use their own certificate stores.

#### GIT

From Powershell, run

`git config -l`

This will output

`core.symlinks=false`
 `core.autocrlf=true`
 `core.fscache=true`
 `color.diff=auto`
 `color.status=auto`
 `color.branch=auto`
 `color.interactive=true`
 `help.format=html`
 `rebase.autosquash=true`
 `http.sslcainfo=C:/Program Files/Git/mingw64/ssl/certs/ca-bundle.crt`
 `http.sslbackend=openssl`
 `diff.astextplain.textconv=astextplain`
 `filter.lfs.clean=git-lfs clean -- %f`
 `filter.lfs.smudge=git-lfs smudge -- %f`
 `filter.lfs.process=git-lfs filter-process`
 `filter.lfs.required=true`
 `credential.helper=manager`

The CA Certificate store is identified at http.sslcainfo . This should be updated to include the Zscaler certificate by running the following command as an administrator in PowerShell which appends the Zscaler certificate to the bundle.

`gc .\ZscalerRootCertificate-2048-SHA256.crt| ac $(git config --get http.sslcainfo)`

#### Python

Python on Windows automatically includes PIP and Certifi which is the default certificate bundle for certificate validation. Similar to GIT, the bundle needs to be updated – replacing the python directory with your own

`gc .\ZscalerRootCertificate-2048-SHA256.crt| ac C:\Python37\Lib\site-packages\pip\_vendor\certifi\cacert.pem`

### Linux

#### Base Operating System

Linux variants invariably use OpenSSL for their CA Trust. You can find the OpenSSL directory through the following command

`openssl version -d`

Which outputs

`OPENSSLDIR: "/etc/pki/tls"`

In this directory structure, you can add the Zscaler certificate into the certs directory by simply copying the file in.

`cp ZscalerRootCertificate-2048-SHA256.crt $(openssl version -d | cut -f2 -d \")/certs`

Alternatively you can place the file into the anchors directory and run the update-ca-trust command to push the certificate into the CA-Trust files. This is more effective since the CA-Trust file could be directly referenced by other applications

`cp ZscalerRootCertificate-2048-SHA256.crt /etc/pki/ca-trust/source/anchors/ && update-ca-trust`

#### Python

Python will (again) typically use it’s own CA store. You can identify the store if certifi package is installed

`python -m certifi`

Which will output

`/usr/lib/python2.7/site-packages/certifi/cacert.pem`

You can update the Zscaler certificate into this CA Store by doing the following

`cat ZscalerRootCertificate-2048-SHA256.crt >> $(python -m certifi)`

Similarly, you can configure system variables to point to this CA Store (or point to the OpenSSL store you’ve updated previously)

`export CERT_PATH=$(python -m certifi)`
 `export SSL_CERT_FILE=${CERT_PATH}`
 `export REQUESTS_CA_BUNDLE=${CERT_PATH}`

### MacOS

#### Base Operating System

MacOS behaves very similar to Linux, but has it’s own configurations and directories. MacOS will mostly use the keychain, which should keep the OpenSSL CA Store in sync. Either import the certificate to the trusted root store using Keychain, or perform the following in the terminal.

`sudo security add-trusted-cert -d -r trustRoot -k /Library/Keychains/System.keychain <CERTIFICATE>`

It may still be necessary to update the OpenSSL CA Store to include the Zscaler certificate for any application which reads it directly.

`sudo cat ZscalerRootCertificate-2048-SHA256.crt >> /usr/local/etc/openssl/cert.pem`

#### Python

Python will (again) typically use it’s own CA store. You can identify the store if certifi package is installed

`python -m certifi`

Which will output

`~/Library/Python/3.7/lib/python/site-packages/certifi/cacert.pem`

You can update the Zscaler certificate into this CA Store by doing the following

`cat ZscalerRootCertificate-2048-SHA256.crt >> $(python -m certifi)`

Similarly, you can configure system variables to point to this CA Store (or point to the OpenSSL store you’ve updated previously)

`export CERT_PATH=$(python -m certifi)`
 `export SSL_CERT_FILE=${CERT_PATH}`
 `export REQUESTS_CA_BUNDLE=${CERT_PATH}`

### Docker

Docker – on Windows, MacOS, and Linux, will use the OpenSSL CA Trust for it’s connections – ensure these are configured to allow Docker to download packages as you instantiate them in your Dockerfile

Once the Dockerfile is loaded and being processed, containers will make their own connections which will need to trust the Zscaler certificate. It’s therefore important to combine the above approaches to ensure your Docker container has the Zscaler certificates installed.

This example uses three files. The .env file controls whether the build is being run in production (no-Zscaler) or development (Zscaler). The docker-compose.yaml file reads the BUILD_ENV variables and passes to the Dockerfile

#### .env

`BUILD_ENV=production`

OR

`BUILD_ENV=development`

#### docker-compose.yaml

`version: '3.1'`
 
 `services:`
 
 `dotnetconf19:`
 `image: dockersamples/dotnetconf:19`
 `build:`
 `context: .`
 `args:`
 `- BUILD_ENV=${BUILD_ENV:-production}`
 `- CERT_FILE=${CERT_FILE:-/etc/ssl/certs/ca-certificates.crt}`
 `environment:`
 `- BUILD_ENV=${BUILD_ENV:-production}`
 `- CERT_FILE=${CERT_FILE:-/etc/ssl/certs/ca-certificates.crt}`

#### Dockerfile

`FROM mcr.microsoft.com/dotnet/core/sdk:3.0.100-preview9 AS builder`
 
 `No need to install certificates here – no Internet requests made`
 
 `WORKDIR /src`
 `COPY src/WebRequests.csproj .`
 `RUN dotnet restore`
 
 `COPY src/ .`
 `RUN dotnet publish -c Release -o /out WebRequests.csproj`
 
 `FROM mcr.microsoft.com/dotnet/core/runtime:3.0.0-preview9`
 
 `Image runs internet requests over HTTPS – Install Certs if dev environment`
 `Set ARG BUILD_ENV default = production`
 `ARG BUILD_ENV=production`
 
 `Assign the $BUILD_ENV the BUILD_ENV ENV so that it can be accessed`
 `ENV BUILD_ENV $BUILD_ENV`
 `Add the CA Certificate to the container`
 `ADD src/ZscalerRootCertificate-2048-SHA256.crt /tmp/ZscalerRootCertificate-2048-SHA256.crt`
 `Use BUILD_ENV variable within the container to copy the CA certificate into the certificate directory and update`
 `RUN if "$BUILD_ENV" = "production" ; then echo "production env"; else echo "non-production env:`BUILD_ENV"; CERT_DIR=(openssl version -d | cut `-f2 -d \")/certs ; cp /tmp/ZscalerRootCertificate-2048-SHA256.crt $CERT_DIR ; update-ca-certificates ; fi`
 
 `Continue the build where the HTTPS Connections are made`
 `WORKDIR /app`
 `ENTRYPOINT "dotnet", "WebRequests.dll"`
 `ENV DotNetBot:Message="docker4theEdge!"`
 
 `COPY --from=builder /out/ .`
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009jZpJQCA0/ipv4-address-and-cidr-network-notation-detection-regex","lastmod":"2023-06-28T07:54:35.000Z","id":"0D54u00009jZpJQCA0"} -->
## IPv4 Address and CIDR Network Notation Detection REGEX

- Source: https://community.zscaler.com/s/question/0D54u00009jZpJQCA0/ipv4-address-and-cidr-network-notation-detection-regex
- Type: Q&A
- Posted: 2021-07-12T21:45:06.000Z
- Last activity: 2023-06-28T07:54:35.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

### [ipv4-address-and-cidr-network-detection-1](ipv4-address-and-cidr-network-detection-1)IPv4 Address and CIDR network detection

#### [addresses-0000-thru-9255255255-with-cidr-mask-from-0-to-32-the-cidr-portion-is-optional-2](addresses-0000-thru-9255255255-with-cidr-mask-from-0-to-32-the-cidr-portion-is-optional-2)Addresses - 0.0.0.0 thru 9.255.255.255 with CIDR mask from /0 to /32 - the CIDR portion is optional

`\b0-9(\.(0-9|0-9{2}|010-9{1,2}|20-40-9|250-5)){3}(/(0-9|120-9|30-2)){0,1}\b`

#### [addresses-10000-thru-19255255255-and-100000-thru-199255255255-with-cidr-mask-from-0-to-32-the-cidr-portion-is-optional-3](addresses-10000-thru-19255255255-and-100000-thru-199255255255-with-cidr-mask-from-0-to-32-the-cidr-portion-is-optional-3)Addresses - 10.0.0.0 thru 19.255.255.255 and 100.0.0.0 thru 199.255.255.255 with CIDR mask from /0 to /32 - the CIDR portion is optional

`\b010-9{1,2}(\.(0-9|0-9{2}|010-9{1,2}|20-40-9|250-5)){3}(/(0-9|120-9|30-2)){0,1}\b`

#### [addresses-20000-thru-99255255255-with-cidr-mask-from-0-to-32-the-cidr-portion-is-optional-4](addresses-20000-thru-99255255255-with-cidr-mask-from-0-to-32-the-cidr-portion-is-optional-4)Addresses - 20.0.0.0 thru 99.255.255.255 with CIDR mask from /0 to /32 - the CIDR portion is optional

`\b2-90-9(\.(0-9|0-9{2}|010-9{1,2}|20-40-9|250-5)){3}(/(0-9|120-9|30-2)){0,1}\b`

#### [addresses-200000-thru-249255255255-with-cidr-mask-from-0-to-32-the-cidr-portion-is-optional-5](addresses-200000-thru-249255255255-with-cidr-mask-from-0-to-32-the-cidr-portion-is-optional-5)Addresses - 200.0.0.0 thru 249.255.255.255 with CIDR mask from /0 to /32 - the CIDR portion is optional

`\b20-40-9(\.(0-9|0-9{2}|010-9{1,2}|20-40-9|250-5)){3}(/(0-9|120-9|30-2)){0,1}\b`

#### [addresses-250000-thru-255255255255-with-cidr-mask-from-0-to-32-the-cidr-portion-is-optional-6](addresses-250000-thru-255255255255-with-cidr-mask-from-0-to-32-the-cidr-portion-is-optional-6)Addresses - 250.0.0.0 thru 255.255.255.255 with CIDR mask from /0 to /32 - the CIDR portion is optional

`\b250-5(\.(0-9|0-9{2}|010-9{1,2}|20-40-9|250-5)){3}(/(0-9|120-9|30-2)){0,1}\b`

Copyright 2007-2021 ZSCALER, INC.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software�?), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS�?, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009jaK5GCAU/issues-enabling-transparent-authentication-on-macos-devices-using-azure-ad-and-sso-extension-plugin","lastmod":"2023-11-07T16:05:28.000Z","id":"0D54u00009jaK5GCAU"} -->
## Issues enabling transparent authentication on macOS devices using Azure AD and SSO Extension plug-in

- Source: https://community.zscaler.com/s/question/0D54u00009jaK5GCAU/issues-enabling-transparent-authentication-on-macos-devices-using-azure-ad-and-sso-extension-plugin
- Type: Q&A
- Posted: 2023-06-28T16:13:20.000Z
- Last activity: 2023-11-07T16:05:28.000Z
- Answers: 3
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p>I am working on our deployment strategy for ZDX, and am running into an issue. Transparent authentication for the Client Connector with our SSO idp, Azure, is working on Windows without any issues, only passing the userdomain. </p><p><br></p><p>On macOS, I am passing the userdomain, but also attempting to use Microsoft's SSOe plugin to attain silent/transparent authentication for the user. The problem I am seeing is that the app launches, correctly reads my configuration profile (sees the browser_sso_interaction_enabled flag as on), but fails to retrieve a PRT. If I first 'manually' retrieve a PRT using Safari or another application, it works fine. Essentially, it's failing to bootstrap.</p><p><br></p><p>My question is - is anyone else out there attempting this process, and seeing the same issue? </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009nLJfiCAG/nss-interface-monitoring-not-possible","lastmod":"2023-07-24T05:20:18.000Z","id":"0D54u00009nLJfiCAG"} -->
## NSS interface monitoring not possible?

- Source: https://community.zscaler.com/s/question/0D54u00009nLJfiCAG/nss-interface-monitoring-not-possible
- Type: Q&A
- Posted: 2023-07-18T13:56:35.000Z
- Last activity: 2023-07-24T05:20:18.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi all,</p><p>as there is no topic like monitoring, i am asking here. </p><p>I am monitoring our NSS servers using SNMP. And as there are no interface OIDs for the zscaler interfaces, the only interfaces seen in the standard linux interface table are em0, em1 and so on but not zs0, zs1.</p><p>So i don't see any way to see interface statistics like sent and received packets and so on.</p><p>Is there no way to achieve this?</p><p>Thanks in advance</p><p>Vince</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009reVN2CAM/zscaler-unable-to-verify-idp-configuration-for-this-domain","lastmod":"2024-04-17T21:28:48.000Z","id":"0D54u00009reVN2CAM"} -->
## zscaler unable to verify IdP configuration for this domain

- Source: https://community.zscaler.com/s/question/0D54u00009reVN2CAM/zscaler-unable-to-verify-idp-configuration-for-this-domain
- Type: Q&A
- Posted: 2023-08-14T10:47:18.000Z
- Last activity: 2024-04-17T21:28:48.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p>I've been trying toconnect on Zscaler and I am getting this issue</p><p><br></p><p><br></p><p><img src="/zenith/sfc/servlet.shepherd/version/download/0684u00000c32LP?asPdf=false&amp;operationContext=CHATTER"></p><p>Regards,</p><p>Khalid</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009tu2wMCAQ/native-windows-11-having-trouble-in-authentication-ad-dependent-application-for-first-10min-for-home-users","lastmod":"2023-08-30T03:05:32.000Z","id":"0D54u00009tu2wMCAQ"} -->
## Native Windows 11 having trouble in authentication AD dependent application for First 10min for Home Users

- Source: https://community.zscaler.com/s/question/0D54u00009tu2wMCAQ/native-windows-11-having-trouble-in-authentication-ad-dependent-application-for-first-10min-for-home-users
- Type: Q&A
- Posted: 2023-08-21T01:56:12.000Z
- Last activity: 2023-08-30T03:05:32.000Z
- Answers: 8
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><span style="font-family: serif; font-size: 14px;">As we all know ZCC takes 5sec to 10sec to turn on to start function. Native Windows11 [Azure joined] machine speaks with Active Directory within the 10sec and try to cache a kerberos token. once this connection is failed at home with ZPA, we cannot connect sharedrive or AD native servers. </span></p><p><br></p><p><span style="font-family: serif; font-size: 14px;"><span class="ql-cursor">﻿</span>After 600sec [10min], some service in my machine communicates with Active Directory and caches a kerberos token. Post that all our sharedrive or Native AD dependant application works.&nbsp;This cache stays for 10hrs untill laptop reboots. Does anyone notice this issue in your organization?</span></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009x3cQQCAY/incident-receiver-sftp-storage-authentication-problem","lastmod":"2025-11-28T00:06:35.000Z","id":"0D54u00009x3cQQCAY"} -->
## incident receiver sftp storage authentication problem

- Source: https://community.zscaler.com/s/question/0D54u00009x3cQQCAY/incident-receiver-sftp-storage-authentication-problem
- Type: Q&A
- Posted: 2023-09-10T19:11:21.000Z
- Last activity: 2025-11-28T00:06:35.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>i have a trouble setting up incident receiver on Azure VM. following the documentation i am stuck at the point where i have to configure sftp server configuration on incident receiver VM. </p><p><br></p><p>Error: unable to update ssh public key on server "xxxxx@xxxx.blob.core.windows.net:22" using "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ssh-copy-id". If your SFTP server is a Windows based server, please read SFTP server's Admin Guide and setup public key authentication w&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ith ssh public key at "/home/zsroot/.ssh/id_rsa.pub", after public key is manually setup, then run this configuration again</p><p><br></p><p>while i am able to login to SFTP ( sftp is created on blob storage Azure ) from incident reciever VM but still this message appears. </p><p><br></p><p>Did i follow the defined steps or something is missing ? </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009zgxCYCAY/nss-server-configuration-in-nutanix","lastmod":"2023-09-25T08:54:10.000Z","id":"0D54u00009zgxCYCAY"} -->
## NSS server configuration in Nutanix

- Source: https://community.zscaler.com/s/question/0D54u00009zgxCYCAY/nss-server-configuration-in-nutanix
- Type: Q&A
- Posted: 2023-09-23T08:05:43.000Z
- Last activity: 2023-09-25T08:54:10.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I have deployed the NSS server in Nutanix cluster using the OVA file. I am able to configure the network settings and Cert-import, but unable to start the NSS.</p><p><br></p><p>NSS will start with PID for a sec and immediately it goes down and throws an error stating "Error starting the NSS service".</p><p><br></p><p>There are no reference articles available for Nutanix deployment.</p><p><br></p><p>Kindly help me out on this.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A0e8otCQA/mip-integration-account-requirement","lastmod":"2023-11-13T10:36:28.000Z","id":"0D54u0000A0e8otCQA"} -->
## MIP integration account requirement

- Source: https://community.zscaler.com/s/question/0D54u0000A0e8otCQA/mip-integration-account-requirement
- Type: Q&A
- Posted: 2023-09-29T08:11:53.000Z
- Last activity: 2023-11-13T10:36:28.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi All, I was hoping you can help me around specific requirement around integration of Zscaler with MIP. I was looking to understand how does this authentication works, any specific requirements on Azure AD account for integration. What happens when password expires or will it be affected by MFA?</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A1UBZCCQ4/about-saas-security-activity-alerts","lastmod":"2025-12-05T05:46:56.000Z","id":"0D54u0000A1UBZCCQ4"} -->
## About SaaS Security Activity Alerts

- Source: https://community.zscaler.com/s/question/0D54u0000A1UBZCCQ4/about-saas-security-activity-alerts
- Type: Q&A
- Posted: 2023-10-03T00:54:53.000Z
- Last activity: 2025-12-05T05:46:56.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Team,</p><p><br></p><p>Can this feature be used with a free license for SaaS apps?</p><p>I would like to know if an enterprise license is required.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A3xoj0CQA/switching-from-adfs-to-azure-as-the-idp","lastmod":"2023-10-17T13:18:40.000Z","id":"0D54u0000A3xoj0CQA"} -->
## Switching from ADFS to Azure as the IDP

- Source: https://community.zscaler.com/s/question/0D54u0000A3xoj0CQA/switching-from-adfs-to-azure-as-the-idp
- Type: Q&A
- Posted: 2023-10-17T00:09:23.000Z
- Last activity: 2023-10-17T13:18:40.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>In our current authentication framework, off-network users undergo authentication through Okta. However, on-network or VPN-connected users are not prompted for a secondary form of authentication when signing into the client. Our existing authentication mechanism relies on ADFS as the primary Identity Provider (IDP), but we are in the process of transitioning towards an Azure SAML configuration.</p><p><br></p><p>All workstation endpoints are presently connected to Zscaler via the Zscaler client. Upon effecting the transition to the Azure SAML configuration, it is expected that reauthentication will be necessitated due to the fundamental change in IDP.</p><p><br></p><p>I seek clarification regarding the potential user experience post-transition. Specifically, I would like to ascertain whether users will remain authenticated without the need for reauthentication as long as they sustain their connection through the Zscaler client. I hypothesize that reauthentication will be required only if the user exits the client or initiates a system reboot.</p><p>Furthermore, I am interested in understanding the probable implications and challenges that may arise once the transition is executed and committed.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A3xtc1CQA/azure-ad-integration-groups-sync-issue","lastmod":"2025-08-22T07:27:00.000Z","id":"0D54u0000A3xtc1CQA"} -->
## Azure AD Integration - Groups Sync Issue

- Source: https://community.zscaler.com/s/question/0D54u0000A3xtc1CQA/azure-ad-integration-groups-sync-issue
- Type: Q&A
- Posted: 2023-10-17T05:06:39.000Z
- Last activity: 2025-08-22T07:27:00.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><span style="font-family: tahoma;">Hi All,</span></p><p><br></p><p>Currently we are using Zscaler Internet Access (ZIA) with ADFS as the primary authentication mechanism where we have mapped some custom groups created in our Internal Active Directory.</p><p><br></p><p>We are planning to migrate to Azure AD Authentication from current ADFS. Post integrating, we have noticed that the authentication is successful, but the groups which the users are already a part of in the Internal AD are not populating in the Zscaler console.</p><p><br></p><p>Any suggestions for this issue ?</p><p><br></p><p>Thanks,</p><p>Rahul</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A57pOQCQY/gwlb-distribured-gwlb-endpoints-and-centralised-egress","lastmod":"2023-10-18T22:14:09.000Z","id":"0D54u0000A57pOQCQY"} -->
## GWLB - Distribured GWLB endpoints and centralised Egress

- Source: https://community.zscaler.com/s/question/0D54u0000A57pOQCQY/gwlb-distribured-gwlb-endpoints-and-centralised-egress
- Type: Q&A
- Posted: 2023-10-17T18:50:19.000Z
- Last activity: 2023-10-18T22:14:09.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello Team - </p><p><br></p><p>Hope you are doing well. I am trying to understand the packet flow for the pattern in AWS where the workload/spoke VPCs send traffic to Zscaler proxy using distributed GWLB endpoints. </p><p><br></p><p>Source - https://www.zscaler.com/resources/reference-architectures/zero-trust-security-for-aws-workloads.pdf ( Use case - Distributed Gateway Load-Balancing Endpoint) </p><p><br></p><p><br></p><p>As i understand the egress to internet is initiated by the instance in workload VPC and the packet will flow like - </p><p><br></p><p>Instance -> GWLB Endpoint -> (AWS Private Link)- >GWLB -> (GENEVE) -> Zscaler Cloud Connector Instance -> NAT Gateway -> IGW -> Zscaler Edge </p><p><br></p><p>Now how does the return packter get routed to the instance? I am not clear on how the GWLB is able to route the packet to the original GWLB endpoint which might be in a completely different VPC than the Zscaler VPC.</p><p><br></p><p>Any help on this is appreciated.</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A7B7PuCQK/nss-server-status-unhealthy","lastmod":"2025-08-22T07:27:02.000Z","id":"0D54u0000A7B7PuCQK"} -->
## NSS server status unhealthy

- Source: https://community.zscaler.com/s/question/0D54u0000A7B7PuCQK/nss-server-status-unhealthy
- Type: Q&A
- Posted: 2023-10-31T14:59:21.000Z
- Last activity: 2025-08-22T07:27:02.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

we have our NSS server status always showing unhealthy. what could be the reason for that.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ABHRZoCQP/send-cloud-nanolog-nss-to-kafka-queueazure-event-hub-or-azure-storage-account","lastmod":"2025-11-17T23:21:28.000Z","id":"0D54u0000ABHRZoCQP"} -->
## Send Cloud Nanolog NSS to Kafka queue/Azure Event Hub  or Azure Storage Account

- Source: https://community.zscaler.com/s/question/0D54u0000ABHRZoCQP/send-cloud-nanolog-nss-to-kafka-queueazure-event-hub-or-azure-storage-account
- Type: Q&A
- Posted: 2023-11-28T12:36:01.000Z
- Last activity: 2025-11-17T23:21:28.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>The current cloud NSS (https://help.zscaler.com/zia/adding-cloud-nss-feeds-web-logs) does not seem to have a generic Kafka queue type (that could be used with Azure Event Hub) or the ability to send to Azure Storage Accounts (though AWS S3 is there).</p><p><br></p><p>Are there any workarounds or features that I have overlooked?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ADH2KqCQL/migrated-from-adfs-to-azure-ad-using-scim-for-user-management-existing-users-not-being-updated","lastmod":"2023-12-18T16:11:48.000Z","id":"0D54u0000ADH2KqCQL"} -->
## Migrated from ADFS to Azure AD Using SCIM for User Management, Existing Users not being Updated

- Source: https://community.zscaler.com/s/question/0D54u0000ADH2KqCQL/migrated-from-adfs-to-azure-ad-using-scim-for-user-management-existing-users-not-being-updated
- Type: Q&A
- Posted: 2023-12-18T14:36:42.000Z
- Last activity: 2023-12-18T16:11:48.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi all,</p><p><br></p><p>We recently migrated from using a hosted DB using ADFS for user management, to using Azure AD as our IDP. We then set up SCIM provisioning so that the user management database can be automatically updated.</p><p><br></p><p>We're running into an issue with the existing user accounts. Since being on ADFS to start, the max group membership is 128. SCIM is supposed to fix that issue if the user accounts are being updated, but it doesn't appear the actual user accounts are being touched, it's just syncing groups.</p><p><br></p><p>Whenever we force a provision on demand, we see users being created in the event logs for example, so SCIM seems to be working as intended. We have used the help guide and will be double checking all the attributes today.</p><p><br></p><p>What are we missing here so that it updates existing user accounts and reflects their current groups? Thanks.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AELRzZCQX/zscaler-okta-saml-scim-setup","lastmod":"2025-10-28T20:45:30.000Z","id":"0D54u0000AELRzZCQX"} -->
## ZScaler Okta SAML SCIM Setup

- Source: https://community.zscaler.com/s/question/0D54u0000AELRzZCQX/zscaler-okta-saml-scim-setup
- Type: Q&A
- Posted: 2023-12-27T17:30:28.000Z
- Last activity: 2025-10-28T20:45:30.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We are an Okta shop.</p><p><br></p><p>We are setup for SCIM with ZScaler, but have noticed that the department attribute does not populate in ZScaler user management.</p><p><br></p><p>In ZScaler, under the IDP settings, we have both "Enable SAML Auto-Provisioning" and "Enable SCIM Provisioning' checked even though the documentation says to check only "Enable SCIM Provisioning".</p><p><br></p><p>We have tried unchecking "Enable SAML Auto-Provisioning", but if a new user attempts to sign into ZScaler without it checked, they cannot log in.&nbsp;They receive an error stating "There was an issue verifying your identity within our system.&nbsp;Please try authenticating again. Error Codes: 0xe5621 | IdP: 0x83c | CA5204".&nbsp;</p><p><br></p><p>I tried looking up the error codes here (https://help.zscaler.com/zcspm/troubleshooting-saml), but it isn't very helpful.</p><p><br></p><p>Our users are set to sign in once then never again, so the attributes don't update because they only sign in once with SAML which is why we opted for SCIM, but it does not seem to be working.</p><p><br></p><p>Does anyone have a working ZScaler Okta SCIM setup?&nbsp;Is there anything special you needed to do to get your setup running properly?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AIK6QhCQL/zstray-on-ubuntu-2310-core-dumps","lastmod":"2024-02-02T16:29:13.000Z","id":"0D54u0000AIK6QhCQL"} -->
## ZStray on Ubuntu 23.10 core dumps

- Source: https://community.zscaler.com/s/question/0D54u0000AIK6QhCQL/zstray-on-ubuntu-2310-core-dumps
- Type: Q&A
- Posted: 2024-02-02T16:29:13.000Z
- Last activity: 2024-02-02T16:29:13.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>$ ./ZSTray</p><p>2024-02-02 16:28:02.468939(-0600)[46598:46598] INF Generated UDID: :2bkzecva6CFFy3gX0D/oOb+tF7I=</p><p>2024-02-02 16:28:02.469120(-0600)[46598:46598] DBG Running cmd: [awk -F= '/^NAME/{print toupper($2) }' /etc/os-release]</p><p>2024-02-02 16:28:02.470198(-0600)[46598:46598] DBG Output: "UBUNTU"</p><p>2024-02-02 16:28:02.470207(-0600)[46598:46598] DBG Running cmd: [awk -F'"| ' '/^VERSION=/{print $2}' /etc/os-release]</p><p>2024-02-02 16:28:02.471295(-0600)[46598:46598] DBG Output: 23.10</p><p>2024-02-02 16:28:02.471308(-0600)[46598:46598] DBG Running cmd: [awk -F= '/^UBUNTU_CODENAME/{print $2}' /etc/os-release]</p><p>2024-02-02 16:28:02.472268(-0600)[46598:46598] DBG Output: mantic</p><p>2024-02-02 16:28:02.472284(-0600)[46598:46598] INF&nbsp;openssl version OpenSSL 1.0.2zi&nbsp;1 Aug 2023</p><p>Aborted (core dumped)</p><p><br></p><p>Is 23.10 not supported? If not, any idea when it will be?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AKjMvkCQF/getting-zscaller-certificate-error-when-access-url-in-edge-browser","lastmod":"2025-04-07T11:00:17.000Z","id":"0D54u0000AKjMvkCQF"} -->
## Getting Zscaller certificate error  when access URL in edge browser

- Source: https://community.zscaler.com/s/question/0D54u0000AKjMvkCQF/getting-zscaller-certificate-error-when-access-url-in-edge-browser
- Type: Q&A
- Posted: 2024-02-26T14:08:15.000Z
- Last activity: 2025-04-07T11:00:17.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>When try to access some public URL in edge blog search or edge browser getting some certificate error but same site able to open in chrome browser without any certificate error page.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ALpH1yCQF/can-authentication-type-as-oauth-give-the-result-from-this-endpoint-urladminusers-","lastmod":"2024-02-29T14:17:48.000Z","id":"0D54u0000ALpH1yCQF"} -->
## Can authentication type as OAuth give the result from this endpoint {{url}}/adminUsers ?

- Source: https://community.zscaler.com/s/question/0D54u0000ALpH1yCQF/can-authentication-type-as-oauth-give-the-result-from-this-endpoint-urladminusers-
- Type: Q&A
- Posted: 2024-02-29T10:11:05.000Z
- Last activity: 2024-02-29T14:17:48.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>As per this document looks like we can get the result from this endpoint <span style="font-size: 12px; font-family: Inter, system-ui, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Fira Sans&quot;, &quot;Droid Sans&quot;, Helvetica, Arial, sans-serif;">{{url}}/adminUsers</span> using OAuth.</p><p>https://help.zscaler.com/zia/getting-started-zia-api</p><p>Can anyone please confirm.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AOD1EmCQL/migration-of-onprem-adfs-to-azure-ad","lastmod":"2024-03-20T13:54:01.000Z","id":"0D54u0000AOD1EmCQL"} -->
## Migration of on-prem ADFS to Azure AD

- Source: https://community.zscaler.com/s/question/0D54u0000AOD1EmCQL/migration-of-onprem-adfs-to-azure-ad
- Type: Q&A
- Posted: 2024-03-20T06:25:36.000Z
- Last activity: 2024-03-20T13:54:01.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Planning to migrate AD authentication from on-prem ADFS to Azure AD.&nbsp;</p><p> Auth interval is to set to never expiry -> </p><p><br></p><p>Please confirm is there any impact?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AOGE5cCQH/cannot-disable-use-xff-from-client-request-on-sublocation","lastmod":"2024-04-06T23:52:35.000Z","id":"0D54u0000AOGE5cCQH"} -->
## Cannot disable "Use XFF from Client Request" on sublocation

- Source: https://community.zscaler.com/s/question/0D54u0000AOGE5cCQH/cannot-disable-use-xff-from-client-request-on-sublocation
- Type: Q&A
- Posted: 2024-03-27T16:02:16.000Z
- Last activity: 2024-04-06T23:52:35.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We want to use the IP address in the XFF header from a local proxy to be used into a URL policy. We enabled this f XFF first on the main location and want this enabled only on a few sublocations. Therefor we tried to disable it on the other sublocation. Unfortunately this is not possible it is greyed out. On a different customer I have seen it is possible. Could someone advise if this related to additional setting or is it a license issue? If license issue which license is missing or is it a bug?</p><p>Please advise</p><p>Kind regards Marco</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AROol0CQD/zscaler-certificate-error-in-android-emulator","lastmod":"2024-04-23T09:07:46.000Z","id":"0D54u0000AROol0CQD"} -->
## Zscaler certificate error in Android Emulator

- Source: https://community.zscaler.com/s/question/0D54u0000AROol0CQD/zscaler-certificate-error-in-android-emulator
- Type: Q&A
- Posted: 2024-04-23T07:21:37.000Z
- Last activity: 2024-04-23T09:07:46.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We are unable to download some plugins (maven , gradle etc) for android studio. Also We are unable to access internet inside android app in an emulator even after Zscaler is installed CA certificate. even local host urls also not working. Its throwing SSL error.</p><p><br></p><p>android emulator issue - </p><p>1. primary error: 3 certificate: Issued to: CN=www.google.com;Issued by: CN=Zscaler Intermediate Root CA (zscaler.net) (t)\ ,OU=Zscaler Inc.,O=Zscaler Inc.,ST=California,C=US;&nbsp;&nbsp;&nbsp;on URL: <a href="https://www.google.com/" rel="noopener noreferrer" target="_blank">https://www.google.com/</a></p><p><br></p><p>2. <strong>ssl_client_socket_impl.cc(996) handshake failed</strong></p><p><br></p><p>Even zscaler is not allowing to download NPM modules.</p><p><br></p><p>Do we need to bypass the zscaler ? if yes, can anybody help us?</p><p><br></p><p>Or is there any alternate solution for the above issues.</p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ASPEZWCQ5/get-certified-with-one-of-the-industrys-most-comprehensive-zero-trust-certification","lastmod":"2024-06-12T23:08:59.000Z","id":"0D54u0000ASPEZWCQ5"} -->
## Get certified with one of the industry's most comprehensive Zero Trust Certification!

- Source: https://community.zscaler.com/s/question/0D54u0000ASPEZWCQ5/get-certified-with-one-of-the-industrys-most-comprehensive-zero-trust-certification
- Type: Q&A
- Posted: 2024-05-06T10:25:14.000Z
- Last activity: 2024-06-12T23:08:59.000Z
- Answers: 0
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><a href="https://www.zscaler.com/zscaler-academy/ztca-zero-trust-certified-associate" rel="noopener noreferrer" target="_blank"><img src="/sfc/servlet.shepherd/version/download/0684u00000gCrCC?asPdf=false&amp;operationContext=CHATTER" title=""></a></p><p><span style="font-size: 14px; font-family: -apple-system, system-ui, &quot;system-ui&quot;, &quot;Segoe UI&quot;, Roboto, &quot;Helvetica Neue&quot;, &quot;Fira Sans&quot;, Ubuntu, Oxygen, &quot;Oxygen Sans&quot;, Cantarell, &quot;Droid Sans&quot;, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Lucida Grande&quot;, Helvetica, Arial, sans-serif;">Zscaler Zero Trust Certified Associate (ZTCA), one of the industry’s most comprehensive&nbsp;zero trust&nbsp;certifications, helps network and security professionals attest expertise in establishing a holistic, layered security approach based on zero trust principles. </span><a href="https://www.zscaler.com/zscaler-academy/ztca-zero-trust-certified-associate" rel="noopener noreferrer" target="_blank" style="font-size: 14px; font-family: -apple-system, system-ui, &quot;system-ui&quot;, &quot;Segoe UI&quot;, Roboto, &quot;Helvetica Neue&quot;, &quot;Fira Sans&quot;, Ubuntu, Oxygen, &quot;Oxygen Sans&quot;, Cantarell, &quot;Droid Sans&quot;, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Lucida Grande&quot;, Helvetica, Arial, sans-serif;">Start your journey now to earn the Zero Trust credential.</a></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ATH6zDCQT/nssweb","lastmod":"2025-11-17T23:06:31.000Z","id":"0D54u0000ATH6zDCQT"} -->
## NSS-Web

- Source: https://community.zscaler.com/s/question/0D54u0000ATH6zDCQT/nssweb
- Type: Q&A
- Posted: 2024-05-22T11:12:50.000Z
- Last activity: 2025-11-17T23:06:31.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><span style="font-family: sans-serif;">Hi Team,</span></p><p><br></p><p><span style="font-family: sans-serif;">I was trying to deployed NSS server on our premises but we are getting error missing SSL Client Certificate/Key</span></p><p><span style="font-family: sans-serif;">and also i am trying to enable to SSH in server but i can't</span></p><p><strong>find the error snapshot below</strong></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000gDzg4?asPdf=false&amp;operationContext=CHATTER"></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AVMC2GCQX/single-zscaler-client-for-multiple-enterprise-vpn-connection-possible-or-not","lastmod":"2024-07-01T14:35:37.000Z","id":"0D54u0000AVMC2GCQX"} -->
## Single  Zscaler client for multiple enterprise VPN connection possible or not?

- Source: https://community.zscaler.com/s/question/0D54u0000AVMC2GCQX/single-zscaler-client-for-multiple-enterprise-vpn-connection-possible-or-not
- Type: Q&A
- Posted: 2024-06-13T13:00:10.000Z
- Last activity: 2024-07-01T14:35:37.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello All,</p><p><br></p><p>I have a query regarding VPN authentication.</p><p>Suppose i am employee in company A and working on a project for company B and company C </p><p>All companies are using Zscaler for VPN authentication . Is it possible that i can switch my VPN connection from A to B and C to access the infra for company B and C ?</p><p><br></p><p>Please advise if there is some solution available.</p><p>Thanks</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AW1WKlCQN/randomly-getting-zscaler-root-certificate-not-installed-browser-errors-for-some-users","lastmod":"2025-06-16T14:48:44.000Z","id":"0D54u0000AW1WKlCQN"} -->
## Randomly getting 'ZScaler root certificate not installed' browser errors for some users

- Source: https://community.zscaler.com/s/question/0D54u0000AW1WKlCQN/randomly-getting-zscaler-root-certificate-not-installed-browser-errors-for-some-users
- Type: Q&A
- Posted: 2024-06-21T15:53:00.000Z
- Last activity: 2025-06-16T14:48:44.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Every now and then we will get a user that runs into this issue when trying to browse to a website (see screenshot below). The same issue would happen at my previous employer as well - completely different environment. This occurs with random users at random times - maybe 2 -3 users per month out of 1000.</p><p><br></p><p>We have verified that the ZScaler root certificate is installed in the Trusted Root Certification Authorities store and up-to-date.</p><p><br></p><p>Manually reinstalling the ZScaler root certificate and rebooting the machine resolves the issue.</p><p><br></p><p>Has anyone else encountered this issue? If so, have you found a permanent fix?</p><p><br></p><p>Thanks in advance.</p><p><br></p><p><img src="/Zenith/sfc/servlet.shepherd/version/download/0684u00000iDpIt?asPdf=false&amp;operationContext=CHATTER"></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AYUKEkCQP/admin-audit-log-not-working-with-qradar-ibm","lastmod":"2025-10-30T18:45:53.000Z","id":"0D54u0000AYUKEkCQP"} -->
## Admin Audit log not working with Qradar IBM

- Source: https://community.zscaler.com/s/question/0D54u0000AYUKEkCQP/admin-audit-log-not-working-with-qradar-ibm
- Type: Q&A
- Posted: 2024-07-24T13:01:42.000Z
- Last activity: 2025-10-30T18:45:53.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><strong>Admin Audit log not working with Qradar IBM</strong></p><p>I have created a Wed feed with Web log and Qradar LEEF format. And it successfully received the log.</p><p>Then I created a similar web feed with Admin Audit log but it doesn’t have LEEF format. Therefore I have selected all available formats but still not working.</p><p>Do I have to use another way to get ZIA’s audit log? How can ZIA send audit log to Qradar without using wed feed?</p><p>Thank you very much.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AamRYzCQM/github-isolation-with-idp","lastmod":"2024-09-04T23:24:35.000Z","id":"0D54u0000AamRYzCQM"} -->
## GitHub isolation with iDP

- Source: https://community.zscaler.com/s/question/0D54u0000AamRYzCQM/github-isolation-with-idp
- Type: Q&A
- Posted: 2024-08-28T03:22:17.000Z
- Last activity: 2024-09-04T23:24:35.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi! </p><p>Has anyone had success using browser isolation with an identity proxy enabled for GitHub?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AcbtHaCQI/nss-on-azure-is-supporting-enabling-accelerated-networking-azure-feature-","lastmod":"2024-10-11T05:51:55.000Z","id":"0D54u0000AcbtHaCQI"} -->
## NSS on Azure is supporting enabling ​"Accelerated Networking Azure feature" ?

- Source: https://community.zscaler.com/s/question/0D54u0000AcbtHaCQI/nss-on-azure-is-supporting-enabling-accelerated-networking-azure-feature-
- Type: Q&A
- Posted: 2024-10-02T02:29:28.000Z
- Last activity: 2024-10-11T05:51:55.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>NSS on Azure is supporting enabling "Accelerated Networking Azure feature" ?</p><p><br></p><p>For example, ZPA AppConnector does not support Accelerated Networking and we can see some problem on the ZPA service.</p><p>So NSS VM on Azure is also not supporting it? can we confirm defalut value of NSS on Azure?</p><p><br></p><p>https://learn.microsoft.com/en-us/azure/virtual-network/accelerated-networking-overview?tabs=redhat</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AccKBiCQM/zscaler-login-server-ssl-handshake-issue","lastmod":"2024-10-07T14:48:51.000Z","id":"0D54u0000AccKBiCQM"} -->
## Zscaler login server SSL Handshake issue

- Source: https://community.zscaler.com/s/question/0D54u0000AccKBiCQM/zscaler-login-server-ssl-handshake-issue
- Type: Q&A
- Posted: 2024-10-02T19:41:39.000Z
- Last activity: 2024-10-07T14:48:51.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p>It seems login.zscalertwo.net SSL handshake has an issue on some windows machines. We are unable to connect using our zscaler client and upon checking the logs there is an SSL handshake issue with login.zscalertwo.net.</p><p><br></p><p>It seems that ssl cert on your zscalertwo login server has been recently updated and it has issues connecting to. We are getting error "write:errno=10054" connecting to it. The link https://login.zscalertwo.net also gives failure on browser. </p><p>Please see below the error when I try connecting to it through openssl client:</p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000jUnuK?asPdf=false&amp;operationContext=CHATTER"></p><p>On the other hand if I try to connect to login.zscalerthree.net I dont have issue:</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000jUoGT?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p>Similarly I am able to connect to mobile.zscalertwo.net as well. So it seems the issue is with login.zscalertwo.net certificate. Can you advice if it is just us or something zscaler is working on.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AcqdoqCQA/anyone-have-cloud-nss-feeds-configured-with-sentinel","lastmod":"2025-08-22T07:27:59.000Z","id":"0D54u0000AcqdoqCQA"} -->
## Anyone have Cloud NSS Feeds configured with Sentinel?

- Source: https://community.zscaler.com/s/question/0D54u0000AcqdoqCQA/anyone-have-cloud-nss-feeds-configured-with-sentinel
- Type: Q&A
- Posted: 2024-10-07T15:16:31.000Z
- Last activity: 2025-08-22T07:27:59.000Z
- Answers: 14
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I wanted know if anyone has successfully created a Cloud NSS Feed with Microsoft Sentinel. Running into an issue when running the connectivity test.</p><p><br></p><p>I'm following the <a href="https://help.zscaler.com/downloads/zscaler-technology-partners/operations/zscaler-and-microsoft-sentinel-deployment-guide/Zscaler-Microsoft-Sentinel-Deployment-Guide-FINAL.pdf" rel="noopener noreferrer" target="_blank">integration guide</a> with page 31 being what needs to be entered.</p><p><br></p><p>Thanks</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AdAARDCQ4/authentication-from-2-idps","lastmod":"2024-10-15T04:22:18.000Z","id":"0D54u0000AdAARDCQ4"} -->
## Authentication from 2 IdPs

- Source: https://community.zscaler.com/s/question/0D54u0000AdAARDCQ4/authentication-from-2-idps
- Type: Q&A
- Posted: 2024-10-14T07:00:23.000Z
- Last activity: 2024-10-15T04:22:18.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Team,</p><p><br></p><p>Currently I have ADFS IdP configured to authenticate all users for any domain, now I am going to add new IdP (Azure), however not all the users have been migrated to Azure (some of them on Azure and some of them onprem).</p><p><br></p><p>So, is it possible to keep both IdPs, so some users authenticate through Azure and the rest to authenticate through ADFS (who are migrated to Azure) ?</p><p><br></p><p>If this is possible, then which IdP will be in the first order ?</p><p>Shall I mention "any" under the authentication domain for both IdPs ? or how will it be ?</p><p><br></p><p>Thanks</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AdZtI7CQK/google-chrome-does-not-work-with-zscaler","lastmod":"2024-10-31T01:38:27.000Z","id":"0D54u0000AdZtI7CQK"} -->
## Google Chrome does not work with ZScaler.

- Source: https://community.zscaler.com/s/question/0D54u0000AdZtI7CQK/google-chrome-does-not-work-with-zscaler
- Type: Q&A
- Posted: 2024-10-23T13:39:14.000Z
- Last activity: 2024-10-31T01:38:27.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello everyone! good morning/afternoon!</p><p><br></p><p>I am not an expert in your solutions (so I don't if i classified correctly Category and Topic...), but I am bringing this difficulty in Google Chrome here to see if there is any "how to" tip that I can forward to my security team to solve this problem, since we have several questions about problems with Chrome here in Brazil. My understanding is that security software in the context that we have implemented here is causing a lot of difficulty in the user experience, that is, we should be thinking about a security solution to help users and we end up "forcing" them to use personal equipment many times because they cannot execute things in a corporate environment.</p><p><br></p><p>I follow this steps but not possible to solve, when I unload ZScaler everything is good.</p><p><br></p><p>It sounds frustrating to have Zscaler working on Edge but not on Chrome. Here are some steps you can try to resolve these issues:</p><ol><li><strong>Update Chrome</strong>: Ensure that Chrome is updated to the latest version. Sometimes, compatibility issues can be fixed with an update.</li><li><strong>Clear Cache and Cookies</strong>: Go to Chrome’s settings and clear the cache and cookies. This can often resolve navigation and performance issues.</li><li><strong>Check Proxy Settings</strong>: Make sure the proxy settings in Chrome are correctly configured and match those in Edge. Zscaler might be using different settings for each browser.</li><li><strong>Disable Extensions</strong>: Some Chrome extensions might interfere with Zscaler. Try disabling all extensions and see if the problem persists. If it resolves the issue, enable them one by one to identify the culprit.</li><li><strong>Authentication Settings</strong>: Verify that the authentication settings in Zscaler are correctly configured for Chrome. Sometimes, different browsers handle authentication differently.</li><li><strong>Reinstall Zscaler Client Connector</strong>: If none of the above steps work, try reinstalling the Zscaler Client Connector. This can fix any corrupted installation issues.</li></ol><p>Thank you!</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AdaXSwCQM/issues-with-o365-contional-access-authentication","lastmod":"2024-11-01T09:22:05.000Z","id":"0D54u0000AdaXSwCQM"} -->
## Issues with O365 contional access authentication

- Source: https://community.zscaler.com/s/question/0D54u0000AdaXSwCQM/issues-with-o365-contional-access-authentication
- Type: Q&A
- Posted: 2024-10-24T16:48:54.000Z
- Last activity: 2024-11-01T09:22:05.000Z
- Answers: 12
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p><br></p><p>We are experiencing issues forwarding microsoft login domains to on premises, in some cases, after a network change, zscaler does not pay attention to the forwarding pac file and it tunnels the traffic to ZIA cloud. </p><p><br></p><p>It's been annoying and causing a lot of incidents, as the users get an error in their teams. Our proxy enforcement option is enforce and we are using Tunnel 2.0 with DTLS, we have tried to tune a lot of parameters following support advices, bu it keeps failing. </p><p><br></p><p>Does anybody overcome this issues? </p><p><br></p><p>Thanks in advance!</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AdrD8ACQU/cloud-nss-logs-to-azure-sentinel","lastmod":"2024-12-23T09:59:19.000Z","id":"0D54u0000AdrD8ACQU"} -->
## Cloud NSS logs to Azure Sentinel

- Source: https://community.zscaler.com/s/question/0D54u0000AdrD8ACQU/cloud-nss-logs-to-azure-sentinel
- Type: Q&A
- Posted: 2024-11-04T16:08:35.000Z
- Last activity: 2024-12-23T09:59:19.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I am attempting to configure cloud NSS feeds to Azure Sentinel. But the error message we are getting is a 204 (no content) and the events in Azure Sentinel reflect this as well. We have logs but there is no data feeding the logs. Essentially, they are empty other than timestamps.</p><p><br></p><p>Am I missing a configuration in the Zscaler UI to send logs via the Cloud NSS feed? </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AdsK6MCQU/exclusion-of-users-via-aadentra-security-group","lastmod":"2024-11-14T20:05:10.000Z","id":"0D54u0000AdsK6MCQU"} -->
## Exclusion of users via AAD/Entra Security group

- Source: https://community.zscaler.com/s/question/0D54u0000AdsK6MCQU/exclusion-of-users-via-aadentra-security-group
- Type: Q&A
- Posted: 2024-11-06T15:52:33.000Z
- Last activity: 2024-11-14T20:05:10.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have 'Cloud App Control Policy' where we like onboard users member of AAD/Entra Dynamic security but due to Microsoft limitation we unable to exclude user from another security group.</p><p><br></p><p>Question: What's the best way/approach exclude specific group of users at 'Cloud App Control Policy'? For now, i am unable to see any option at 'Cloud app control policy' to exclude users from specific group?</p><p><br></p><p>Can below approach block all users (except user part of 'exclusiongroup') access to scoped AI &amp; ML application?</p><p><br></p><ol><li>'Cloud app control policy' with <ol><li>cloud application = AI &amp; ML application</li><li>rule order = 1</li><li>groups = 'exclusiongroup'</li><li>action = allow</li></ol></li><li>'Cloud app control policy' with <ol><li>cloud application = AI &amp; ML application</li><li>rule order = 2</li><li>groups = 'allusergroup' ['this group has all users including users in exclusiongroup']</li><li>action = deny</li><li>rule order = 2</li></ol></li></ol>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AeGdM1CQK/recertification-of-older-certificates","lastmod":"2024-11-17T08:21:05.000Z","id":"0D54u0000AeGdM1CQK"} -->
## Recertification of older certificates

- Source: https://community.zscaler.com/s/question/0D54u0000AeGdM1CQK/recertification-of-older-certificates
- Type: Q&A
- Posted: 2024-11-14T09:03:09.000Z
- Last activity: 2024-11-17T08:21:05.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p><br></p><p>from the past I have quite a few of Zscaler certificates - 3 administrator certificates - ZCCA-IA; - ZCCA-PA ZCCA-ZDX and One proffesional - ZCCP-PA. However, since those are going to expire in the fist half of next year, I am trying to figure out how can I renew them.</p><p><br></p><p>I was looking into the available certificates and as I can see, the model has been completelly reworked. Do I undertand correctly that current administrator exam (ZDTA) is based on the old ZCCA-IA and ZCCA-PA and ZDTE is more of a old Proffesional exams (ZCCP-PA)?</p><p><br></p><p>Also, how exaclty the certification works? Is is proctored exam via organisations like Pearson Vue, through Zscaler itself or is it open-book online exam?</p><p><br></p><p>And last thing - in order to book my exam, do I have to complete all prerequisite trainings? I use to have some of them completed already, but that was under my old account when i was working in different company.</p><p><br></p><p>Thank you in advance for your reply.</p><p><br></p><p>Best regards</p><p>Pavel</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AfJDtECQW/change-in-python-313-that-breaks-certificate-trust","lastmod":"2025-09-11T19:12:20.000Z","id":"0D54u0000AfJDtECQW"} -->
## Change in python 3.13 that breaks certificate trust

- Source: https://community.zscaler.com/s/question/0D54u0000AfJDtECQW/change-in-python-313-that-breaks-certificate-trust
- Type: Q&A
- Posted: 2024-12-15T02:35:55.000Z
- Last activity: 2025-09-11T19:12:20.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>My python 3.13 (Windows) installation behaves differently from previous versions. My ZIA root cert is installed by ZCC and I have tried both pip-system-certs and REQUESTS_CA_BUNDLE variable.</p><p><br></p><p>I get</p><p><br></p><p><span style="font-family: courier;">urllib.error.URLError: </span></p><p><span style="font-family: courier;">urlopen error [SSL: CERTIFICATE_VERIFY_FAILED] certificate verify failed: Basic Constraints of CA cert not marked critical (_ssl.c:1018)</span></p><p><br></p><p>Test script is below. Only 3.13 fails. <span style="font-size: 11pt;">I believe this may be related (a change in the openSSL behavior in Python 3.13):</span></p><ul><li><a href="https://stackoverflow.com/questions/79123649/does-python-3-13-ignore-ssl-cert-file-environment-variable-by-design" rel="noopener noreferrer" target="_blank" style="font-size: 11pt;">https://stackoverflow.com/questions/79123649/does-python-3-13-ignore-ssl-cert-file-environment-variable-by-design</a></li><li><a href="https://docs.python.org/3/whatsnew/3.13.html" rel="noopener noreferrer" target="_blank" style="font-size: 11pt;">https://docs.python.org/3/whatsnew/3.13.html</a></li></ul><p><span style="font-size: 11pt;">&nbsp;</span></p><p><strong style="font-size: 11pt;">ssl</strong></p><ul><li><span style="font-size: 11pt;">The </span><a href="https://docs.python.org/3/library/ssl.html#ssl.create_default_context" rel="noopener noreferrer" target="_blank" style="font-size: 11pt;">create_default_context()</a><span style="font-size: 11pt;"> API now includes </span><a href="https://docs.python.org/3/library/ssl.html#ssl.VERIFY_X509_PARTIAL_CHAIN" rel="noopener noreferrer" target="_blank" style="font-size: 11pt;">VERIFY_X509_PARTIAL_CHAIN</a><span style="font-size: 11pt;"> and </span><a href="https://docs.python.org/3/library/ssl.html#ssl.VERIFY_X509_STRICT" rel="noopener noreferrer" target="_blank" style="font-size: 11pt;">VERIFY_X509_STRICT</a><span style="font-size: 11pt;"> in its default flags.</span></li></ul><p><span style="font-size: 11pt;">Note</span></p><p><a href="https://docs.python.org/3/library/ssl.html#ssl.VERIFY_X509_STRICT" rel="noopener noreferrer" target="_blank" style="font-family: &quot;Aptos&quot;, sans-serif; font-size: 11pt;">VERIFY_X509_STRICT</a><span style="font-family: &quot;Aptos&quot;, sans-serif; font-size: 11pt;"> may reject pre-</span><a href="https://datatracker.ietf.org/doc/html/rfc5280.html" rel="noopener noreferrer" target="_blank" style="font-family: &quot;Aptos&quot;, sans-serif; font-size: 11pt;"><strong>RFC 5280</strong></a><span style="font-family: &quot;Aptos&quot;, sans-serif; font-size: 11pt;"> or malformed certificates that the underlying OpenSSL implementation might otherwise accept. </span></p><p><br></p><p><span style="font-family: courier;">for /d %%p IN (</span></p><p><span style="font-family: courier;">&nbsp;&nbsp;&nbsp;"C:\Program Files\Microsoft SDKs\Azure\CLI2\python.exe"</span></p><p><span style="font-family: courier;">&nbsp;&nbsp;&nbsp;C:\Python\Python311\python.exe</span></p><p><span style="font-family: courier;">&nbsp;&nbsp;&nbsp;C:\Python\Python312\python.exe</span></p><p><span style="font-family: courier;">&nbsp;&nbsp;&nbsp;C:\Python\Python313\python.exe</span></p><p><span style="font-family: courier;">) DO (</span></p><p><span style="font-family: courier;">&nbsp;&nbsp;&nbsp;ECHO.## %%p&nbsp;##</span></p><p><span style="font-family: courier;">&nbsp;&nbsp;&nbsp;%%p -V</span></p><p><span style="font-family: courier;">&nbsp;&nbsp;&nbsp;%%p -m pip list | find "pip-sy"</span></p><p><span style="font-family: courier;">&nbsp;%%p -c "import urllib.request;&nbsp;print(urllib.request.urlopen(""https://icanhazip.com"").read())"&nbsp;</span></p><p><span style="font-family: courier;">&nbsp;&nbsp;&nbsp;ECHO.</span></p><p><span style="font-family: courier;">)</span></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000Vl9ki0AB/microsoft-entra-hybrid-join-and-strictenforcement-option","lastmod":"2025-05-08T08:41:24.000Z","id":"0D5PJ00000Vl9ki0AB"} -->
## Microsoft Entra Hybrid Join and StrictEnforcement Option

- Source: https://community.zscaler.com/s/question/0D5PJ00000Vl9ki0AB/microsoft-entra-hybrid-join-and-strictenforcement-option
- Type: Q&A
- Posted: 2025-04-09T10:38:09.000Z
- Last activity: 2025-05-08T08:41:24.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Dear Community,</p><p><br/></p><p>I have a question regarding the use of Application Profiles.</p><p><br/></p><p>I have two application profiles:</p><ul><li>Profile A is designed for M365 tenant restrictions and does not bypass "login.microsoftonline.com."</li><li>Profile B contains settings that allow bypassing "login.microsoftonline.com" and is used for Microsoft Entra Hybrid Join.</li></ul><p>The priority of the Profiles is such that A is ranked higher than B, and the assigned users are the same for both.</p><p><br></p><p>When enabling the Strict Enforcement option and installing ZCC, we can specify Profile B in the Policy Token.</p><p>As a result, "login.microsoftonline.com" would be bypassed before the user signs in,</p><p>But not after signing in, which leads me to believe that the tenant restriction would function as intended. </p><p><br></p><p>However, I would like to confirm if this configuration is supported.</p><p>I'm concerned about potential operational issues or other concerns due to the switching of profiles before and after user sign-in.</p><p><br></p><p>Thank you for your assistance.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000Wiotu0AB/user-laptop-generates-incorrect-dns-query-to-connect-active-directory-domain-controller","lastmod":"2025-07-25T00:20:34.000Z","id":"0D5PJ00000Wiotu0AB"} -->
## user laptop generates incorrect DNS query to connect active directory domain controller

- Source: https://community.zscaler.com/s/question/0D5PJ00000Wiotu0AB/user-laptop-generates-incorrect-dns-query-to-connect-active-directory-domain-controller
- Type: Q&A
- Posted: 2025-04-28T03:26:57.000Z
- Last activity: 2025-07-25T00:20:34.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>A user laptop generates wrong query before it establishes connection to active directory via AUDC console. </p><p>As a result , the user gets incorrect username and password error.</p><p>The issue happens only when he uses zpa via home network. </p><p>When he uses zpa via office network , everything works fine. </p><p><br></p><p>I do see the user laptop initially generates wrong SRV query like others</p><p>However, at the end the other users laptop generates correct DNS SRV query "_ldap._tcp.domain.com" and connection gets successful to domain controllers in the domain. </p><p>This is not happening to this user. we notice instead of DNS SRV query his laptop generates DNS A record query for "domain.com" </p><p><br></p><p>your inputs/suggestions are welcome </p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000Y7uVX0AZ/zidentity-migration","lastmod":"2025-06-04T18:01:53.000Z","id":"0D5PJ00000Y7uVX0AZ"} -->
## ZIdentity- Migration

- Source: https://community.zscaler.com/s/question/0D5PJ00000Y7uVX0AZ/zidentity-migration
- Type: Q&A
- Posted: 2025-05-22T05:24:18.000Z
- Last activity: 2025-06-04T18:01:53.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Everyone,</p><p><br></p><p>I've been asked to work on ZIdentity to migrate the existing customers and below is my plan.</p><p><br></p><p>Please note, Post migration, users will be accessing ZID, through external IDP by clicking the Zscaler tile or link.</p><p><br></p><ol><li>Establish SAML authentication between ZIdentity and IDP (Ex:MS Entra)</li><li>Ask AD team to create groups in Azure-AD such as (ZIA-RO, ZIA-RW, ZPA-RO, ZPA-RW , ZIdentity-RO, ZIdentity-RW) and add respective users into it.</li><li>Sync those AD groups with ZIdentity</li><li>In 'Admin entitlements', call those AD groups and associate it with the relevant Zscaler services (ZIA/ZPA, ZS Login) permissions (RO/RW) as needed.</li><li>Once working and tested, proceed with the migration.</li></ol><p>&nbsp;</p><p>Note: I'll be creating the users and groups in AD and calling it in the policies rather creating those groups locally in ZIdentity and adding those users.</p><p><br></p><p>Can you please let me know if any corrections are required? Thanks in advance</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000Yzt5r0AB/python-313-zscaler-certificate-nonconform","lastmod":"2025-09-11T19:11:58.000Z","id":"0D5PJ00000Yzt5r0AB"} -->
## Python 3.13 Zscaler certificate non-conform

- Source: https://community.zscaler.com/s/question/0D5PJ00000Yzt5r0AB/python-313-zscaler-certificate-nonconform
- Type: Q&A
- Posted: 2025-06-04T23:51:53.000Z
- Last activity: 2025-09-11T19:11:58.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p><br></p><p>I'm having the same problem as reported by some other users here:</p><p><br></p><ul><li>https://community.zscaler.com/s/question/0D54u0000AfJDtECQW/change-in-python-313-that-breaks-certificate-trust</li><li>https://gitlab.com/alelec/pip-system-certs/-/issues/32</li><li>https://discuss.python.org/t/python-3-13-x-ssl-security-changes/91266</li></ul><p><br></p><p>Since python 3.13, it seems that a stricter openSSL policy is required and that the Zscaler certificate provided by my organization is non conform to that. Most notably, it requires the Basic Constraint flag to be marked as Critical in the X509v3 extension.</p><p><br></p><p>Here's an example error reported by python:</p><p>[SSL: CERTIFICATE_VERIFY_FAILED] certificate verify failed: Basic Constraints of CA cert not marked critical</p><p><br></p><p>This is a major blocker for us! Can you please advise on how to resolve this?</p><p><br></p><p>Thank you</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000bRUUs0AO/non-profit-pricing-to-compete-with-microsoft-secure-entra-internet","lastmod":"2025-07-23T14:50:09.000Z","id":"0D5PJ00000bRUUs0AO"} -->
## Non profit pricing to compete with Microsoft Secure entra internet

- Source: https://community.zscaler.com/s/question/0D5PJ00000bRUUs0AO/non-profit-pricing-to-compete-with-microsoft-secure-entra-internet
- Type: Q&A
- Posted: 2025-07-18T19:33:22.000Z
- Last activity: 2025-07-23T14:50:09.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Does Zscaler offer non profit pricing for non profit orgs? Microsoft has a similar product now but we all no it doesn't compare.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000bdzRY0AY/cloud-nss-with-microsoft-sentinel-has-huge-aditional-extensions-field","lastmod":"2025-07-31T06:44:11.000Z","id":"0D5PJ00000bdzRY0AY"} -->
## Cloud NSS with Microsoft Sentinel has huge Aditional Extensions field

- Source: https://community.zscaler.com/s/question/0D5PJ00000bdzRY0AY/cloud-nss-with-microsoft-sentinel-has-huge-aditional-extensions-field
- Type: Q&A
- Posted: 2025-07-22T20:38:46.000Z
- Last activity: 2025-07-31T06:44:11.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I'm in the process of moving our existing Zscaler logging to Sentinel from NSS appliances to Cloud NSS. I've got a test feed working, but I found the "AdditionalExtensions" field in the Sentinel table is full of extraneous, useless data.</p><p><br></p><p>An example:</p><p class="ql-indent-1"><strong>ZscalerNSSWeblogURLClass=;ZscalerNSSWeblogDLPDictionaries=;contenttype=;unscannabletype=;deviceowner=;devicehostname=;keyprotectiontype=;cloudname=;company=;throttlereqsize=;throttlerespsize=;bwthrottle=;bwclassname=;bwrulename=;module=;app_risk_score=;datacenter=;datacentercity=;datacentercountry=;dlpdicthitcount=;dlpidentifier=;dlpmd5=;dlprulename=;fileclass=;filesubtype=;upload_fileclass=;upload_filetype=;upload_filename=;upload_filesubtype=;upload_doctypename=;rdr_rulename=;fwd_type=;fwd_gw_name=;fwd_gw_ip=;zpa_app_seg_name=;reqdatasize=;reqhdrsize=;respdatasize=;resphdrsize=;totalsize=;df_hosthead=;df_hostname=;erefererhost=;refererpath=;eurlpath=;reqversion=;respversion=;ua_token=;uaclass=;mobappname=;mobappcat=;mobdevtype=;clt_sport=;cpubip=;alpnprotocol=;trafficredirectmethod=;euserlocationname=;erulelabel=;ruletype=;eurlfilterrulelabel=;ourlfilterrulelabel=;eapprulelabel=;sha256=;ssldecrypted=Yes;externalspr=;clientsslcipher=;clienttlsversion=;clientsslsessreuse=;cltsslfailreason=;cltsslfailcount=;srvsslcipher=;srvtlsversion=;srvocspresult=;srvcertchainvalpass=;srvwildcardcert=;serversslsessreuse=;srvcertvalidationtype=;srvcertvalidityperiod=;is_ssluntrustedca=;is_sslselfsigned=;is_sslexpiredca=;threatseverity=;malwareclass=;urlcatmethod=;bypassed_traffic=;bypassed_etime=;deviceappversion=;devicemodel=;devicename=[redacted];deviceostype=;devicetype=;external_devid=;flow_type=;ztunnelversion=;productversion=;nsssvcip=</strong></p><p><br></p><p>By logging format string doesn't have any of those fields in it, we don't use them and this chews up a lot of ingestion and storage costs with Microsoft.</p><p><br></p><p>Does anyone know if this is normal? </p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000dKPnj0AG/saml-certificate-updation-in-jumpcloud","lastmod":"2025-08-26T17:09:01.000Z","id":"0D5PJ00000dKPnj0AG"} -->
## SAML Certificate updation in Jumpcloud

- Source: https://community.zscaler.com/s/question/0D5PJ00000dKPnj0AG/saml-certificate-updation-in-jumpcloud
- Type: Q&A
- Posted: 2025-08-20T15:42:47.000Z
- Last activity: 2025-08-26T17:09:01.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We recently received the email <strong>"Zscaler Support Notification: SAML Request Signing Certificate Update [Zscalertwo.net]". </strong>In the email they have only written the steps to perform on the Zscaler side.</p><p><br></p><p><strong>Now we use Jumpcloud as the IDP. In Jumpcloud, we see two options for certificates. </strong></p><p><br></p><p><span style="font-family: Arial, Helvetica, sans-serif; font-size: small;">1. SSO application > Zscaler > Action button (Top Right of the Window) > Upload certificate</span></p><p><span style="font-family: Arial, Helvetica, sans-serif; font-size: small;">&nbsp;2. SSO application > Zscaler > Replace SP certificate.</span></p><p><br></p><p><span style="font-family: Arial, Helvetica, sans-serif; font-size: small;">Now I am unsure which one to choose for uploading the latest certificate downloaded from zscaler. </span></p><p><br></p><p><span style="font-family: Arial, Helvetica, sans-serif; font-size: small;">Can someone help here? </span></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000dqkOp0AI/cannot-submit-a-ticket-found-single-signon-error","lastmod":"2025-09-04T07:29:57.000Z","id":"0D5PJ00000dqkOp0AI"} -->
## Cannot Submit a Ticket found Single Sign-On Error.

- Source: https://community.zscaler.com/s/question/0D5PJ00000dqkOp0AI/cannot-submit-a-ticket-found-single-signon-error
- Type: Q&A
- Posted: 2025-08-29T02:00:02.000Z
- Last activity: 2025-09-04T07:29:57.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<h2>When clicking &nbsp;Submit a Ticket on ZIA portal then open new tab and found this error.</h2><h2>How can I resolve this by myself or next action this?</h2><h2 style="text-align: center;">Single Sign-On Error</h2><p>We can't log you in because of an issue with single sign-on. Contact your Salesforce admin for help.</p><h2 style="margin-bottom: 12px; font-weight: 700; font-size: 18px; font-family: Montserrat; text-align: center;"><img src="https://community.zscaler.com/sfc/servlet.shepherd/version/download/068PJ00000PLBQH?asPdf=false&amp;operationContext=CHATTER" style="margin-bottom: 12px;"></h2>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ebzBN0AY/certificate-renewal","lastmod":"2025-12-10T19:01:26.000Z","id":"0D5PJ00000ebzBN0AY"} -->
## Certificate renewal

- Source: https://community.zscaler.com/s/question/0D5PJ00000ebzBN0AY/certificate-renewal
- Type: Q&A
- Posted: 2025-09-11T19:13:04.000Z
- Last activity: 2025-12-10T19:01:26.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Team,</p><p><br></p><p> I need some help with an issue.</p><p><br></p><p> This is my first time handling the PRA certificate renewal process. We are providing PRA access to a third-party vendors and the current certificate is going to expire next month. I already have the security certificate and CA bundle file with me in zip format but I’m not sure how to proceed.</p><p><br></p><p> Do I need to generate a CSR or simply upload the certificate? Even thought i tried uploading cert but it is throwing error that no matching csr or private key found within cert&nbsp;Could you please guide me on the correct steps?</p><p>Also existing cert was issued by sectigo so how they did earlier or what needs to be done please help </p><p><br></p><p> Thanks in advance!</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000f9WZX0A2/unable-to-determine-user-identification-method-please-contact-your-admin","lastmod":"2025-10-07T04:46:14.000Z","id":"0D5PJ00000f9WZX0A2"} -->
## Unable to determine user identification method. Please contact your admin

- Source: https://community.zscaler.com/s/question/0D5PJ00000f9WZX0A2/unable-to-determine-user-identification-method-please-contact-your-admin
- Type: Q&A
- Posted: 2025-09-21T14:52:01.000Z
- Last activity: 2025-10-07T04:46:14.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello Everyone,</p><p><br></p><p>Greetings</p><p><br></p><p>We have a issue where in when the user attempts to connect to the website(bluebeamstudio.com) they get the error message "Unable to determine user authentication method. Please contact your administrator for assistance.</p><p><img src="/sfc/servlet.shepherd/version/download/068PJ00000QK88w?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p>When researched on the error message found a okta article indiciating could be a network problem like firewall, VPN and zscaler.</p><p>https://support.okta.com/help/s/article/User-Gets-Unable-to-determine-user-identification-method-Error-While-Trying-to-Sign-In?language=en_US</p><p><br></p><p>The URL works fine from office/corporate network using on-prem private ZENs</p><p><br></p><p>When the user works from home or uses any other private network he does get the issue, user has external pac file configured to go to directly to nearest Zscaler node for service.</p><p><br></p><p>Verified the ZCC captures and found to be normal between the Client and Zscaler node.</p><p><br></p><p>On the HAR logs, its observed 307 Temporary Redirect.</p><p><img src="/sfc/servlet.shepherd/version/download/068PJ00000QK9jJ?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p>Seeking your advise if the solution to this is to authentication bypass or SSL bypass, or any other recommendations would be appreciated to resolve the issue, thanks.</p><p><br></p><p>Really appreciate your help.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000fYdZ00AK/zsadminzsidentity-and-google-workspace-samlscim","lastmod":"2025-10-06T13:35:05.000Z","id":"0D5PJ00000fYdZ00AK"} -->
## ZSAdmin/zsidentity and Google Workspace SAML_SCIM

- Source: https://community.zscaler.com/s/question/0D5PJ00000fYdZ00AK/zsadminzsidentity-and-google-workspace-samlscim
- Type: Q&A
- Posted: 2025-09-29T00:28:53.000Z
- Last activity: 2025-10-06T13:35:05.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi All,</p><p><br></p><p>Trying to get Google Identity working with ZSADMIN, there is a pre-wrapped app within Google admin, but I suspect it was written for ZIA rather than ZSADMIN.</p><p><br></p><p>I think I have SAML working (can't validate yet) but I keep getting the following error when it try to provision via SCIM:</p><p><br></p><p><span style="font-family: courier;">&nbsp;StatusCode: 400 : Bad Request : { schemas :[ urn:ietf:params:scim:api:messages:2.0:Error ]&nbsp;scimType : Input payload error&nbsp;detail : Primary Email is required\n&nbsp;status : 400 }</span></p><p><br></p><p>From what I can derive, the attribute mapping in ZIDENTITY is different to ZIA. In ZSADMIN it is expecting an attribute called "primaryemail" but in ZIA for which the Google integration is written is only to allow you to use the ZIA attribute of 'userName'. I google let me modify the mapping, I'd probably be away and running..</p><p><br></p><p> The documentation for Google workspace with ZIDENTITY is lacking at the moment, so any help here would be appreciated.</p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000fxbCP0AY/how-can-i-download-my-zdta-completion-certificate-just-i-received-badge","lastmod":"2025-10-06T18:11:14.000Z","id":"0D5PJ00000fxbCP0AY"} -->
## How can i download my ZDTA Completion Certificate just i received badge?

- Source: https://community.zscaler.com/s/question/0D5PJ00000fxbCP0AY/how-can-i-download-my-zdta-completion-certificate-just-i-received-badge
- Type: Q&A
- Posted: 2025-10-06T13:27:12.000Z
- Last activity: 2025-10-06T18:11:14.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I have completed my ZDTA Certification exam and i passed my exam. I received badge through credly. Still i didn't receive my ZDTA completion certificate. How can i download my ZDTA Completion Certificate which contains certificate number?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000gyks50AA/okta-error-when-provisioning-users-for-zidentity-app","lastmod":"2025-11-18T23:59:28.000Z","id":"0D5PJ00000gyks50AA"} -->
## Okta error when provisioning users for Zidentity app

- Source: https://community.zscaler.com/s/question/0D5PJ00000gyks50AA/okta-error-when-provisioning-users-for-zidentity-app
- Type: Q&A
- Posted: 2025-10-17T06:07:26.000Z
- Last activity: 2025-11-18T23:59:28.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have configured Okta to integrate with our Zidentity Administration with SAML &amp; SCIM to facilitate role based administration of our Zscaler applications. In Okta we have multiple groups which are pushed via SCIM to Zidentity which are then mapped to their respective administrative entitlements in Zidentity.</p><p><br></p><p>So far this configuration has worked fine. However recently when adding some more users to the Push Groups we occasionally get the bellow error. This does not effect all users, but does effect a significant amount and we have not been able to identity why some users specifically are failing to be provisioned in Zidentity. We have reviewed all the attributes of said users and all appears nominal and all users have the required attributes.</p><p><br></p><p>We have taken this up with TAC who seem somewhat stumped. We are kind of at the end of our tether and our next step is to blow away the configuration and rebuild it with OIDC. But would like to avoid that if possible.</p><p><br></p><p>Has anyone else encountered this in the past?</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/068PJ00000Rew8z?asPdf=false&amp;operationContext=CHATTER"></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000hB1JU0A0/zscaler-admin-portal-no-healthy-upstream","lastmod":"2025-10-21T05:39:55.000Z","id":"0D5PJ00000hB1JU0A0"} -->
## Zscaler Admin Portal - No Healthy Upstream

- Source: https://community.zscaler.com/s/question/0D5PJ00000hB1JU0A0/zscaler-admin-portal-no-healthy-upstream
- Type: Q&A
- Posted: 2025-10-20T08:45:18.000Z
- Last activity: 2025-10-21T05:39:55.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><img src="/sfc/servlet.shepherd/version/download/068PJ00000RmVra?asPdf=false&amp;operationContext=CHATTER"><img src="/sfc/servlet.shepherd/version/download/068PJ00000RmXoX?asPdf=false&amp;operationContext=CHATTER"></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000hbyRU0AY/could-not-able-to-search-previous-solution-articles","lastmod":"2025-10-25T01:18:52.000Z","id":"0D5PJ00000hbyRU0AY"} -->
## could not able to search  previous solution articles

- Source: https://community.zscaler.com/s/question/0D5PJ00000hbyRU0AY/could-not-able-to-search-previous-solution-articles
- Type: Q&A
- Posted: 2025-10-23T19:36:33.000Z
- Last activity: 2025-10-25T01:18:52.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>one of the customer not receiving nss feed logs from cloud nss</p><p>could not find any troubleshooting steps.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000kFtZe0AK/tmobile-5g-ipv6-connection-issues","lastmod":"2025-11-18T19:22:10.000Z","id":"0D5PJ00000kFtZe0AK"} -->
## TMobile 5G (ipv6) connection issues

- Source: https://community.zscaler.com/s/question/0D5PJ00000kFtZe0AK/tmobile-5g-ipv6-connection-issues
- Type: Q&A
- Posted: 2025-11-18T15:04:49.000Z
- Last activity: 2025-11-18T19:22:10.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I have a had several of employees trying to use T-Mobile 5G without success. Anyone else ran into this issue and found a work around. Seems the <span style="font-family: &quot;Google Sans&quot;, Roboto, Arial, sans-serif; font-size: 16px;">Tmo-G4Ar </span>gateway only supports IPV6. In Tunnelv2 it simply will not connect. When I swap them to Tunnel v1 it will connect but just not very stable. When they swap back to old ISP it's very stable. The only possible workaround I came up with but didn't implement due to the employees didn't want to go through the trouble is install a WIFI router behind the gateway and use it using IPV4. Also, we tried the latest ZCC. We also tried disabling IPV6 on the endpoint but that actually prevents the endpoint from working completely. Note ZIA/ZPA/ZDX are all in failed state.</p><p><br></p><p><span style="font-family: &quot;Google Sans&quot;, Roboto, Arial, sans-serif; font-size: 16px;">Tmo-G4Ar gateway.</span></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000kpKxE0AU/zidentity-service-account-authentication","lastmod":"2026-01-06T23:32:51.000Z","id":"0D5PJ00000kpKxE0AU"} -->
## Zidentity Service account authentication

- Source: https://community.zscaler.com/s/question/0D5PJ00000kpKxE0AU/zidentity-service-account-authentication
- Type: Q&A
- Posted: 2025-11-24T20:48:10.000Z
- Last activity: 2026-01-06T23:32:51.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p>Is there any way to authenticate Windows Services running on a machine (no human interaction) with ZIdentity?</p><p>Thanks,</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000lyphK0AQ/ztca","lastmod":"2025-12-18T14:22:37.000Z","id":"0D5PJ00000lyphK0AQ"} -->
## ZTCA

- Source: https://community.zscaler.com/s/question/0D5PJ00000lyphK0AQ/ztca
- Type: Q&A
- Posted: 2025-12-15T18:05:10.000Z
- Last activity: 2025-12-18T14:22:37.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p><br></p><p>I’ve completed the full ZTCA learning path (6/6 courses completed on Dec 6).</p><p>The completion is visible in my learning profile, but I haven’t received the certificate yet.</p><p><br></p><p>Could you please confirm when the certificate will be issued?</p><p><br></p><p>Kind regards,</p><p>Mohammed Arbaoui</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000mcrBg0AI/macos-disk-full-of-certificates","lastmod":"2025-12-29T16:39:47.000Z","id":"0D5PJ00000mcrBg0AI"} -->
## MacOS Disk Full of certificates

- Source: https://community.zscaler.com/s/question/0D5PJ00000mcrBg0AI/macos-disk-full-of-certificates
- Type: Q&A
- Posted: 2025-12-29T16:39:47.000Z
- Last activity: 2025-12-29T16:39:47.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I'm using zscaler to connect to a client's network, I keep having a recurring issue where zscaler fills my entire disk with PEM files in /var/root/Library/Application\ Support/com.zscaler.UPMServiceController/ I have to clear it out every week to keep it from eating 50gb+ in "system data"</p><p><br></p><p>Obviously something is wrong, we shouldn't have hundreds of thousands of PEM files for any reason on a client machine. I know they're mostly a windows environment so not sure if they missed something on their mac profile settings or failed to give proper deployment instructions for contractors to connect into their tenant. My company uses a different ZTNA solution but not sure if something between the two is causing conflict.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000nxwGf0AI/domain-provisioning-during-new-zscaler-tenant-activation-for-customer","lastmod":"2026-01-25T10:25:18.000Z","id":"0D5PJ00000nxwGf0AI"} -->
## Domain provisioning during New Zscaler tenant activation for customer.

- Source: https://community.zscaler.com/s/question/0D5PJ00000nxwGf0AI/domain-provisioning-during-new-zscaler-tenant-activation-for-customer
- Type: Q&A
- Posted: 2026-01-22T09:30:39.000Z
- Last activity: 2026-01-25T10:25:18.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Which domain we need to provide for domain provisioning during New Zscaler tenant activation for customer.&nbsp;I believe zscaler will only authenticate users whose domain matches one of the "Provisioned Domains" in your tenant. If a user tries to log in with user@unlisted-domain.com, the Zscaler service will reject the request. Is that correct ?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000p6ZAz0AM/unable-to-complete-zero-trust-cyber-associate-ztca","lastmod":"2026-02-17T04:08:10.000Z","id":"0D5PJ00000p6ZAz0AM"} -->
## Unable to complete Zero Trust Cyber Associate (ZTCA)

- Source: https://community.zscaler.com/s/question/0D5PJ00000p6ZAz0AM/unable-to-complete-zero-trust-cyber-associate-ztca
- Type: Q&A
- Posted: 2026-02-10T17:54:20.000Z
- Last activity: 2026-02-17T04:08:10.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I am unable to access the following two modules</p><h2>Zero Trust Architecture Deep Dive Introduction</h2><p>and</p><h1>Zero Trust Architecture Deep Dive Summary</h1><p>Both the sections open but there is no content so I am unable to finish the course.</p><p><br></p><p>any help/advice is highly appreciated</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000sUGqA0AW/support-of-cisco-duo-as-idp","lastmod":"2026-04-14T18:40:04.000Z","id":"0D5PJ00000sUGqA0AW"} -->
## Support of CISCO DUO as IDP

- Source: https://community.zscaler.com/s/question/0D5PJ00000sUGqA0AW/support-of-cisco-duo-as-idp
- Type: Q&A
- Posted: 2026-04-09T14:47:03.000Z
- Last activity: 2026-04-14T18:40:04.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Is there any idea of an official support for CISCO DUO as IDP in Zscaler ZPA and ZIA? </p><p>Both CISCO and Zscaler will benefit of happy costumer using both tecnologies integrated. </p><p>Ciao</p><p>Marco</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000uNnRL0A0/multiple-entra-id-ad-groups-with-same-displayname-only-one-gets-imported","lastmod":"2026-05-13T12:38:56.000Z","id":"0D5PJ00000uNnRL0A0"} -->
## Multiple Entra ID AD Groups with Same DisplayName — Only One Gets Imported

- Source: https://community.zscaler.com/s/question/0D5PJ00000uNnRL0A0/multiple-entra-id-ad-groups-with-same-displayname-only-one-gets-imported
- Type: Q&A
- Posted: 2026-05-11T06:38:03.000Z
- Last activity: 2026-05-13T12:38:56.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Community,</p><p><br></p><p>We have a scenario where multiple Azure AD (Entra ID) groups share the same displayName — for example, three different groups all named "sample_group" with different objectIds in Entra ID.</p><p><br></p><p>When we configure SCIM provisioning from Entra ID to ZIA, only one of these groups gets imported into the Zscaler user database. The remaining groups with the same displayName are silently skipped.</p><p><br></p><p>Wanted to check with the community:</p><p><br></p><p>1. Is this a known limitation of Zscaler SCIM — that group displayNames must be unique for provisioning to work correctly?</p><p>2. Is there any official Zscaler documentation that explicitly calls this out?</p><p>3. Has anyone found a workaround other than renaming the duplicate groups in Entra ID?</p><p><br></p><p>Our current understanding is that Zscaler uses displayName as the primary matching key for SCIM group sync (not objectId), which causes this behavior. Looking for confirmation and ideally a reference we can share with our client.</p><p><br></p><p>Any inputs from the community or Zscaler team would be greatly appreciated.</p><p><br></p><p>Thanks</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000wo03h0AA/exclusions-based-on-groups-from-entra-id","lastmod":"2026-06-18T16:49:45.000Z","id":"0D5PJ00000wo03h0AA"} -->
## Exclusions based on groups from Entra ID.

- Source: https://community.zscaler.com/s/question/0D5PJ00000wo03h0AA/exclusions-based-on-groups-from-entra-id
- Type: Q&A
- Posted: 2026-06-18T14:15:08.000Z
- Last activity: 2026-06-18T16:49:45.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>First of all, I wanted to say that I'm completely new to Zscaler and ZIA. I'm familiar with other SWG-type solutions, but I'm having trouble with ZIA.</p><p>It might seem simple, but it is.</p><p><br></p><p>I'd like to create the following rule:</p><p><br></p><p>If I'm a member of group X, I have access to category (e.g., AI), otherwise (other users), traffic should be blocked.</p><p><br></p><p>I've noticed that the ZIA mechanism works completely differently – what's not prohibited is allowed.</p><p><br></p><p>Do you have any ideas on how to solve my problem?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000xZllV0AS/zscaler-gov-enterprise-application-is-only-available-in-gcch","lastmod":"2026-08-11T19:04:52.000Z","id":"0D5PJ00000xZllV0AS"} -->
## "Zscaler Gov" Enterprise Application is Only Available in GCCH

- Source: https://community.zscaler.com/s/question/0D5PJ00000xZllV0AS/zscaler-gov-enterprise-application-is-only-available-in-gcch
- Type: Q&A
- Posted: 2026-06-30T19:12:21.000Z
- Last activity: 2026-08-11T19:04:52.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We noticed today that the <strong>Zscaler Gov</strong> Enterprise Application no longer appears in <strong>GCC </strong>Azure tenants. It still appears in <strong>GCC-High </strong>tenants, but we have some clients that use <strong>FedRAMP Moderate Zscaler Internet Access (zscalergov.net) </strong>along with their <strong>GCC </strong>tenant.</p><p>I instructed my team to use the standard <strong>Zscaler Internet Access ZSNet</strong> Enterprise Application, since that should work perfectly as long as we overwrite it with the correct Gov data during the SAML/SCIM configuration.</p><p><br></p><p>I wanted to confirm: is this the officially recommended workaround for GCC tenants? If so, can the documentation on the page below be updated to include this note?</p><p><br></p><p><a href="https://help.zscaler.us/zia/saml-scim-configuration-guide-microsoft-entra-id" rel="noopener noreferrer" target="_blank">SAML &amp; SCIM Configuration Guide for Microsoft Entra ID | Zscaler</a></p><p><span style="font-size: 13px;">https://help.zscaler.us/zia/saml-scim-configuration-guide-microsoft-entra-id</span></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000zoFsY0AU/saml-authentication-expired-causing-private-application-access-failures","lastmod":"2026-08-03T12:41:30.000Z","id":"0D5PJ00000zoFsY0AU"} -->
## SAML Authentication Expired Causing Private Application Access Failures

- Source: https://community.zscaler.com/s/question/0D5PJ00000zoFsY0AU/saml-authentication-expired-causing-private-application-access-failures
- Type: Q&A
- Posted: 2026-08-03T12:41:30.000Z
- Last activity: 2026-08-03T12:41:30.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I'm troubleshooting an issue where users are unable to access an internally published application. </p><p><br></p><p>It is a Domain Controller.</p><p><br></p><p>The logs show a BRK_MT_SETUP_FAIL_SAML_EXPIRED failure while attempting to access an internal resource. The transaction references UDP/389, but the connection is denied with a re-authentication action of Deny before any traffic is exchanged. Has anyone seen SAML expiration errors occur before connector/app selection? And what it is weird, we are not having any authentication issue internally.</p><p><br></p><p>Note</p><p>We don't have any <span style="font-size: 16px; font-family: Inter-Medium;">Timeout Policy.</span></p><p><br></p>
<!-- /ZS-POST -->
