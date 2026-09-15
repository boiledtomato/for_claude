# Zscaler Zenith Community — ZIA — Internet & SaaS (part 4)

Source: https://community.zscaler.com
Generated: 2026-08-01 20:41 UTC
Posts in this file: 147

> これはユーザー投稿のコミュニティフォーラムの内容であり、Zscaler の公式ドキュメントではない。

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AeXDjZCQW/howto-translate-regular-dns-traffic-to-doh-using-dns-gateway","lastmod":"2024-11-29T09:27:55.000Z","id":"0D54u0000AeXDjZCQW"} -->
## How-To: Translate Regular DNS Traffic to DoH using DNS Gateway

- Source: https://community.zscaler.com/s/question/0D54u0000AeXDjZCQW/howto-translate-regular-dns-traffic-to-doh-using-dns-gateway
- Type: Q&A
- Last activity: 2024-11-29T09:27:55.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Cloud Firewall

siddhartha.aggarwal

(Employee) asked a question.

Edited November 23, 2024 at 8:30 PM

How-To: Translate Regular DNS Traffic to DoH using DNS Gateway

References

https://help.zscaler.com/zia/about-dns-gateways

https://help.zscaler.com/zia/about-dns-control

Overview

In this article we see how you can use DNS Gateway to translate regular DNS traffic (over UDP or TCP) to DNS-over-HTTPS (DoH) and forward it to a DoH resolver.

Note:

Zscaler provides DNS resolution service through Zscaler Trusted Resolvers (ZTR) in more than 150 edge locations. Using DNS Control + ZTR you can eliminate the need to send DNS traffic to a 3rd party DNS resolver.

Pre-requisites

1) DoH Resolver

You should have a publicly accessible DoH resolver. Here we will use a custom DoH resolver that was setup for the purpose of this article.

2) Recommended Firewall Rules

Also make sure that the Recommended Firewall Rule rule to allow DNS, HTTP and HTTPS traffic is enabled, and that there is no higher ordered rule that would block DNS traffic:

Step-by-step guide

1) Enable Enforce Firewall Control option for the location

If the location from where the user will access the ZIA Cloud is defined as a Location, make to enable the Enable Firewall Control option for that location.

Go to Administration > Resources > Traffic Forwarding > Location Management

Click on the location you want to add/edit and enable the Enforce Firewall Control option as shown:

2) Add DNS Gateway

Go to Administration > Resources > Forwarding Methods > Proxies & Gateways

Under the tab DNS Gateways: Click on Add DNS Gateway:

Specify values for the DNS Gateway as shown:

Name:

e.g. DNSGW1.

Protocol:

DNS over HTTPS.

Primary DNS Server:

IP address or FQDN of the DoH service (e.g. doh.gs-tests.com).

Failure behavior:

The action to be performed when both primary and secondary resolvers are unavailable to serve the requests. Here we have chosen the option "Return Error Response".

Note:

Here we have just specified a primary DNS server, however as a best practice, you should specify both a primary and secondary DNS server.

Click on Save. The Gateway should now appear in the list of DNS Gateways.

3) Add DNS Control rule to forward DNS traffic to this DoH Gateway:

Go to Policy > Firewall > Access Control > DNS Control

Then click on Add DNS Filtering Rule and add a new DNS Filtering rule as shown:

Rule Order:

Make sure the rule is ordered high enough, before the default DNS rule to allow DNS traffic. Here we have set it to 1.

Rule Name:

e.g. Redirect to DoH.

Rule Status:

Enabled.

Action:

Redirect Request using DoH.

DNS Gateway:

DNSGW1.

Click Save. The DNS Control rule will appear in the list.

4) Activate changes

Click on Activation > Activate.

Verification

Connect a client using ZCC/Z-Tunnel 2.0:

Now initiate a regular DNS lookup. Specify a public resolver, such as 8.8.8.8, to ensure that DNS traffic is tunneled to ZIA Cloud.

To confirm that these DNS requests were translated to DoH and forwarded to our DoH resolver, check the DNS logs.

Go to Analytics > Insights > DNS Insights > Logs

Select the timeframe, say last 5 minutes, and click on Apply Filters:

Additional References

Zscaler DNS Security and Control Reference Architecture Guide:

https://help.zscaler.com/downloads/zia/reference-architecture/zscaler-dns-security-and-control-0/zscaler-dns-security-and-control-reference-architecture_0.pdf

ZIA - Cloud Firewall

2 answers

1.26K views

G-Man8

and

Jainil_G

like this.

Jainil_G

(Employee)

2 years ago

@Siddhartha Aggarwal​ Another great article, Thanks a lot for sharing this.

siddhartha.aggarwal

(Employee)

2 years ago

@Jainil Gajjar​ Thanks!

Log In to Answer

Associated Tags

dns

dns-control

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2018

at

01:07 PM

Ip address as criteria in url policy

ZIA - Cloud Firewall

ram75

1,190

1190 Views

0 Likes

1 Comment

10/8/2020

at

08:41 AM

Block All access & Allow certain user or group (ZIA)

ZIA - Cloud Firewall

Sec_def_Def_sec

1,817

1817 Views

1 Like

2 Comments

12/19/2022

at

04:14 PM

URL filtering policy vs Cloud App policy control

ZIA - Cloud Firewall

Ahmed

4 Views

0 Likes

2 Comments

12/9/2022

at

09:40 PM

Apple News RSS Feed

ZIA - Cloud Firewall

Trace Woodbury-RidgeIT

2 Views

0 Likes

1 Comment

9/20/2022

at

03:20 PM

How does Zscaler Internet Access itself route the traffic to the internet, using what outgoing/next hop GW

ZIA - Cloud Firewall

tamerz

6 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

How-To: Translate Regular DNS Traffic to DoH using DNS Gateway
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AeY5j2CQC/policy-migration-from-forcepoint-to-zscaler","lastmod":"2025-09-15T11:21:51.000Z","id":"0D54u0000AeY5j2CQC"} -->
## Policy migration from Forcepoint to Zscaler

- Source: https://community.zscaler.com/s/question/0D54u0000AeY5j2CQC/policy-migration-from-forcepoint-to-zscaler
- Type: Q&A
- Last activity: 2025-09-15T11:21:51.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

somayeh.dianati

(Partner) asked a question.

November 26, 2024 at 5:33 AM

Policy migration from Forcepoint to Zscaler

Hi Experts,

We have got a project to migrate Forcepoint policies to ZIA. Do you think it's easier to get customer to send us requirements for URL filtering, SSL Inspection, Cloud Firewall, ... or the current Forcepoint policies to convert to ZIA?

P.S. I am not familiar with Forcepoint. Any past experience or knowledge share would be highly appreciated.

ZIA - URL Filtering

8 answers

445 views

Arvind L Jaiswal

(Partner)

2 years ago

No , you have to import all Forcepoint  Data for reference and then manually implement/configured same on ZIA.

somayeh.dianati

(Partner)

2 years ago

Thanks Arvindj for your reply. Have you done this before? There is protocol filter in Forcepoint. Do you know how it should be configured in ZIA?

Ramesh Mani

(Partner)

2 years ago

You can use protocol in the criteria on URL  / cloud App control policies.

Most importantly you may not directly use the categories, certain URLs may not work as each vendor will have different categorization mechanism. At least validate it for business-critical apps.

WarrenP

(Customer)

2 years ago

We used Forcepoint (on-prem) for years then migrated to Zscaler this year. Now is the best time to start fresh. I would advise against importing old policies since Zscaler works differently.  This is the best time to clean up old whitelists and bypasses that may not be used any more. We implemented Zscaler best practices, and I have not added the same number of whitelist entries that existed in Forcepoint. Overall, the migration was very smooth.

Go with a phased roll out to critical teams and solicit feedback. Once that is covered rollout to everyone else.

1 like

somayeh.dianati

(Partner)

2 years ago

Hi Warren,

Thank you for your reply. Very useful advises. Our client is using the below some protocols in the Forcepoint Protocol Filters which I couldn't find them in ZIA protocol list of URL filtering or Cloud App Control. Here is some example:

Access Grid, Adobe Updater (Requires Content Gateway), AOL Instant Messenger or ICQ, AOL Instant Messenger or ICQ attachments, AOL RadioProtocolAres, Badongo Buddy, Baidu Hi

Did you have the similar case? What should I do with them?

Ramesh Mani

(Partner)

2 years ago

Hi,

For Adobe, lot of Cloud apps already there in Zscaler, based on the application you are using you can choose them.  OR use the attached list for Adobe services.

I could See AOL , ICQ, Baidu - May need to identify the purpose of the tools and its naming conventions. Used to run a workshop with test users and finalize the cloud apps or URL categories

You can very well identify the URLs using web insights and create URL category for those Apps and whitelist or blacklist them.

BDR

(Partner)

2 years ago

As already said - no there is no policy migration tool.

However, this would be a great feature request for ZIA.

Some other vendors have policy migration tools.  It would be great to see this be made available to ZIA as well.

WarrenPhillips

(Customer)

2 years ago

We used Forcepoint (on-prem) for years then migrated to Zscaler this year. Now is the best time to start fresh. I would advise against importing old policies since Zscaler works differently.  This is the best time to clean up old whitelists and bypasses that may not be used any more. We implemented Zscaler best practices, and I have not added the same number of whitelist entries that existed in Forcepoint. Overall, the migration was very smooth.

Go with a phased roll out to critical teams and solicit feedback. Once that is covered rollout to everyone else.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,913

5913 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

888

888 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Policy migration from Forcepoint to Zscaler
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AeY6O8CQK/url-filtering-difference-between-windows-and-macos","lastmod":"2024-12-02T20:48:18.000Z","id":"0D54u0000AeY6O8CQK"} -->
## URL filtering difference between Windows and macOS

- Source: https://community.zscaler.com/s/question/0D54u0000AeY6O8CQK/url-filtering-difference-between-windows-and-macos
- Type: Q&A
- Last activity: 2024-12-02T20:48:18.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

byeongyul.ha

(Customer) asked a question.

Edited by Ben_Garrison November 27, 2024 at 2:06 PM

URL filtering difference between Windows and macOS

When I access "

" on my Windows PC, URL Filtering works and site access is blocked.

But

URL Filtering doesn't work if I access from macOS with the same account, so I can access "

The Zscaler account is linked to ADFS,

and the URL Filtering policy I created is designated in the AD group,

﻿and accounts belonging to that group are subject to the same policy.

Does macOS require additional measures when applying policies using ADFS?

ZIA - URL Filtering

2 answers

347 views

Ramesh Mani

(Partner)

2 years ago

I never see such differences in policy access. Verify Web insights which policy is allowing / blocking.  Verify SSL inspection too.

Try to Block QUIC protocol and see.

Try the below options,

Firewall Control on ZScaler :

Access your firewall Control.

Create a rule to block QUIC( port 443 UDP) application.

Browser settings:

Open your preferred browser (like Chrome or Edge).

Access the flags settings (e.g., "chrome://flags" in Chrome).

Search for "Experimental QUIC protocol" and set it to "Disabled".

1 like

joemozdy

(Customer)

2 years ago

How are your MacOs users authenticating into the client connector? There are certain integrations with Intune that allows an Entra/Azure AD account to be set up for SSO on Macs (

https://learn.microsoft.com/en-us/mem/intune/configuration/platform-sso-macos

), but it requires some steps to be taken. If your users aren't being authenticated to the client connector under their Entra/Azure AD accounts, that could cause issues.

I'd also echo Ramesh's post, mainly with ensuring that SSL inspection is enabled for those domains/operating systems, along with your URL filtering rule applying to Macs.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,909

5909 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

888

888 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

URL filtering difference between Windows and macOS
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AeYcB3CQK/unable-to-block-gmail-and-google-drive-by-url-filtering","lastmod":"2024-12-04T23:31:54.000Z","id":"0D54u0000AeYcB3CQK"} -->
## Unable to block Gmail and Google Drive by URL Filtering

- Source: https://community.zscaler.com/s/question/0D54u0000AeYcB3CQK/unable-to-block-gmail-and-google-drive-by-url-filtering
- Type: Q&A
- Last activity: 2024-12-04T23:31:54.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

byeongyul.ha

(Customer) asked a question.

November 27, 2024 at 5:01 AM

Unable to block Gmail and Google Drive by URL Filtering

Hi Experts,

I have a problem with the URL filtering function.

Blocking Gmail and Google Drive through URL Filtering is not working well.

It has been confirmed that other mail sites or cloud storage are blocked.

Only Google's services are not blocked.

Both Edge and Chrome are version 130 and the same as not being blocked.

Do I need other settings to block Google's services?

ZIA - URL Filtering

5 answers

933 views

Top Rated Answers

Jainil_G

(Employee)

2 years ago

Hello @Byungyul Ha​ , This means that for the reported non-blocked websites, SSL inspection is not being applied. There could be some configuration for SSL inspection that is causing it to be exempted. SSL bypass could be either due to QUIC or any rule from which it is getting bypassed.

Web Insight logs should be able to show you if SSL inspection is being bypassed, and possibly the reason as well. Just for a quick check, you can create a user-based rule at the top position 1 of SSL inspection rule with action inspect to check for test user.  I believe if this is sorted, then your issue should be resolved as well.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Selected as Best

1 like

All Answers

Jainil_G

(Employee)

2 years ago

Hello @Byungyul Ha​ ,

As a first step, you can check if the QUIC protocol is blocked or not as per this guide (

https://help.zscaler.com/zia/managing-quic-protocol

). Zscaler's best practice is to block QUIC. When it's blocked, QUIC has a failsafe to fall back to TCP. This enables SSL inspection without negatively impacting user experience.

As the next step, review the web insights to check corresponding entries and verify which specific policy is being marked.

It has been observed that SSL inspection is required for some of the URLs. Therefore, check if you have SSL inspection enabled or not. If not, you can try enabling it for a specific user and review the output.

If you are using a custom URL category for Google URLs, then verify with the help of web insight logs if it is

being marked properly or not.

If you are still facing issues, please share your observations on the above details.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

1 like

byeongyul.ha

(Customer)

2 years ago

When I compared the site where URL Filtering is applied and the site that is not applied, I found the following differences.

Site covered by URL Filtering : Zscaler certificate

Site not covered by URL Filtering : Site certificate

SSL certificate installation and SSL inspection are set up.

Please let me know if there are any measures I need to add to apply Zscaler certificate.

Best Regards,

Byeongyul Ha

Jainil_G

(Employee)

2 years ago

Hello @Byungyul Ha​ , This means that for the reported non-blocked websites, SSL inspection is not being applied. There could be some configuration for SSL inspection that is causing it to be exempted. SSL bypass could be either due to QUIC or any rule from which it is getting bypassed.

Web Insight logs should be able to show you if SSL inspection is being bypassed, and possibly the reason as well. Just for a quick check, you can create a user-based rule at the top position 1 of SSL inspection rule with action inspect to check for test user.  I believe if this is sorted, then your issue should be resolved as well.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Selected as Best

1 like

byeongyul.ha

(Customer)

2 years ago

I found a setting that excludes ".

google.com

" from SSL Inspection.

I think another administrator registered the content, so I will check and revise the content.

Thank you for your help.

Best Regards,

Byeongyul Ha

1 like

byeongyul.ha

(Customer)

2 years ago

Thank you for your guide.

The contents I checked are as follows:

QUIC is blocked in Edge/Chrome Browser.

I checked the logs for Gmail & Gdrive and found "Policy Action:Allowed" and "Blocked Policy Type:None". The blocking policy for Gmail, Gdrive does not apply or seemed to be allowed access due to other policies.

I created a new SSL Inspection Policy for Gmail and Gdrive. But it doesn;t block Gmail and Gdrive.

The custom category is applied to Gmail, not Gdrive. I'll check if there is a problem with this.

Based on the information you provided, I will review the issue further.

Best Regards,

Byeongyul_Ha

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,923

5923 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

893

893 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Unable to block Gmail and Google Drive by URL Filtering
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Aev6CNCQY/website-is-working-with-mobile-hotspot-but-not-with-wifi-when-zia-enabled","lastmod":"2024-12-06T09:24:47.000Z","id":"0D54u0000Aev6CNCQY"} -->
## Website is working with Mobile hotspot but not with WIFI when ZIA enabled

- Source: https://community.zscaler.com/s/question/0D54u0000Aev6CNCQY/website-is-working-with-mobile-hotspot-but-not-with-wifi-when-zia-enabled
- Type: Q&A
- Last activity: 2024-12-06T09:24:47.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - SSL Inspection

DurgaChitturi

(Partner) asked a question.

December 5, 2024 at 3:02 PM

Website is working with Mobile hotspot but not with WIFI when ZIA enabled

Hello Team ,

We have one user is sitting in US location and he is trying to access invoice portal but when is using mobile hotspot it is working without any issues but when he turned on WIFI it is not working and throwing no valid certificate .

Also same URL is working for me with ZIA enabled but I'm from India location

Can you please help in understand this loop

ZIA - SSL Inspection

2 answers

1.06K views

Jainil_G

(Employee)

2 years ago

Hello @Durga Prasad​ ,

Below information can help for the further investigation

For both Hotspot and Wi-Fi, is ZIA enabled? Or is ZIA enabled only for Wi-Fi and disabled for the Hotspot? Understanding this will help us narrow down whether the issue is related to specific network forwarding or another factor.

Do you have different policies based on the location, or is it the same policy for all locations? If it is different, you can try applying the India location policy for one US test user and check the result. If it is the same policy, then you can proceed with the other steps below.

Are you getting an invalid certificate error for Zscaler, or is it a different certificate? For any certificate, check the validity period to investigate further.

If you are using WiFi and have any intermediate device at the US location performing scanning, it could also be causing the issue.

If it is a Zscaler certificate, check the Web Insight logs for any problematic information.

If the problem still persists, share some more information based on the above diagnostics to help further.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

BDR

(Partner)

2 years ago

Are you performing SSL inspection? If so, check the certificate being used when you're on WiFi versus a mobile hotspot. The difference might explain your issue.

From what I've seen, mobile hotspots don’t use the Zscaler certificate, and I’ve never gotten a clear answer as to why. On a standard WiFi connection, the Zscaler certificate is applied. Since this involves an invoicing portal, it’s may be that SSL inspection is causing problems.

Try adding the invoicing portal domains to the "do not inspect" list in your SSL inspection settings.  See if that fixes the issue.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2021

at

07:54 PM

Youtube Restricted Mode Bypass Safe Search

ZIA - SSL Inspection

JonM

8 Views

1 Like

5 Comments

11/24/2021

at

06:09 AM

Reflection time of SSL inspection exclusion settings

ZIA - SSL Inspection

Nyajima

4 Views

0 Likes

1 Comment

4/26/2024

at

02:39 PM

End User Notifcation - Submit To Security Cloud option

ZIA - SSL Inspection

citrus3118

309

309 Views

0 Likes

1 Comment

8/21/2024

at

08:06 PM

StackHawk with Zscaler SSL Inspection

ZIA - SSL Inspection

User16182582818315999266

565

565 Views

0 Likes

4 Comments

3/10/2025

at

12:22 AM

Reputation Block policy

ZIA - SSL Inspection

JM

415

415 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Website is working with Mobile hotspot but not with WIFI when ZIA enabled
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Af0ZZrCQM/zscaler-causes-error-connection-reset-for-specific-websites","lastmod":"2024-12-10T07:38:42.000Z","id":"0D54u0000Af0ZZrCQM"} -->
## Zscaler causes Error connection reset for specific websites?

- Source: https://community.zscaler.com/s/question/0D54u0000Af0ZZrCQM/zscaler-causes-error-connection-reset-for-specific-websites
- Type: Q&A
- Last activity: 2024-12-10T07:38:42.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

MoZenit

(Partner) asked a question.

December 6, 2024 at 1:59 PM

Zscaler causes Error connection reset for specific websites?

Hello together,

a few users have issues to access a specific website even if I added the URL to ssl bypass and the connection is allowed according to the logs. Nslookup and IP reachability also works for this website but the users cannot browse to it. Does anyone has an idea why with ZIA enabled this website does not work? When ZIA disabled the user are able to browse this website.

ZIA - URL Filtering

7 answers

2.76K views

joemozdy

(Customer)

2 years ago

What sort of site is this? Is it a general website like going to Youtube or CNN, or is this a site that has a relationship with your company like a 3rd party vendor? If it's the later, it might be a situation where that site can only be reached from pre-cleared sources like the outbound gateway from your corporate network. In that situation, you could take different steps that would give you different levels of resolution.

First you could put the domain of the url in the VPN Gateway Bypass field of the app policy that being used. That will bypass that traffic entirely from ZIA, so you wont see any traffic to that site anymore and lose out on all Zscaler protection for that site. Despite it's name, this is how you completely bypass traffic getting routed into ZIA, when you are using Tunneling mode. If you are using Tunneling With Local Proxy mode, then you need to set up a bypass in the pac file that is being used.

Second option is if you are using ZPA, is to set up a wildcard app segment for the website's domain on all ports. This will route the user traffic through ZPA, into your network structure, then out your company's external gateway (hopefully, depending on your network architecture). You do lose out on some of the ZIA specific protections, but the traffic is still protected via ZPA microtunnel. This is a good trick to keep in your back pocket when deploying ZPA and dealing with long term business relations or internal/external users who aren't technically savy or are able to otherwise adjust long term agreements and configurations. Note that you will still run into your original issue if a user is on a trusted network where ZPA is disabled. You can always make a policy that keeps ZPA on when on a trusted network though to solve that particular hurdle.

Third option is to contact the site and ask them to allow traffic to/from the Zscaler Public Service Edge. This is the option that will most completely solve the issue, but is also the hardest one to pull off as you are asking a 3rd party to make a change and you may have limited sway in convincing them. It can help to narrow the criteria down in this situation, like if your users are only in the United States you can reasonably make the assumption that almost all of your traffic will be coming from one of the Zscaler data centers in the United States so only give them the information about those specific data centers.

Hopefully these options give you a direction to head in.

MoZenit

(Partner)

2 years ago

This is a public site which everyone can access. They do not use ZPA. Is there no other solution instead of bypassing traffic to this site? Why is Zscaler acting like that?

joemozdy

(Customer)

2 years ago

Is it a public public site? Like my examples of something like Youtube or CNN where there isn't an inherent relationship between the company and the website, or is this something like a vendor's website where the login portal may be public facing but there is limited to no functionality unless you log in? What happens when you try to access the site on a non-networked device like your phone when it's off wifi? You said you are already bypassing SSL inspection for that site, did you verify that in in the ZIA logs that it's not being inspected? And in the ZIA logs, you said the connection was showing as successful but what sort of response codes are you getting? Does the browser give you any error screens or codes, possibly something along the lines of "server refused connection"?

That sort of stuff can help drive the best answer. If there is any sort of relationship between your company and that site, I'd recommend following that line and seeing if they are not allowing traffic from the Zscaler Public Service Edge. Successful response codes but browser messages along the lines of "server refused connection" would be a strong indicator to me that that would be the case. I've even found this to be the case on certain public facing company owned resources! I've found the situation where a vendor only allows traffic from pre-cleared sources to be common, and any existing network connections you are trying from are covered by that. I've found some services still need explicit bypasses despite integrations with Zscaler as cloud applications, mainly because they don't tolerate any sort of proxy behaviors (Five9 comes to mind in this situation).

Bypassing the traffic from ZIA completely, I tend to treat it like the nuclear option. Once you do that, it becomes really difficult to get any other traction on the subject, like making sure that the site allows traffic from Zscaler because why would anyone fix anything when nothing is causing problems? Without more info like the site you are trying to reach or logs or other information, I'm not sure what else to recommend beyond speaking with your TAM and opening a support case.

1 like

MoZenit

(Partner)

2 years ago

Hello, yes it is a public site like youtube etc. and I verified that in the logs. The browser displays "connection error reset" and sometimes Error 504. In the inkognitomode it shows me that the website is unsecure and from curl I could found out that tls/ssl handshake is resetted by the webserver.

joemozdy

(Customer)

2 years ago

That's really interesting. Can you share what site this at all? The 504 response code is a server error code, "The server was acting as a gateway or proxy and did not receive a timely response from the upstream server." If you've already bypassed the site from SSL inspection, it might be worth it to revisit that to make sure the entry is right. There might be an entry for that site as a cloud application that might more completely cover it. You could also make the case that because the site is insecure, your users shouldn't be using it in the first place. Outside of that, yeah it sounds like you might have bypass the site from ZIA. Hopefully you can get internal approval and document so you have something to refer back to in the future.

BDR

(Partner)

2 years ago

I've seen a few cases where users have received 504 errors with certain websites when Secure DNS is enabled in the browser.   Try disabling it and see if that makes any difference.

MoZenit

(Partner)

2 years ago

I found a solution for this issue. I contacted the host of the webserver and they had geoblocking for their webserver. Since Zscaler registers its Node IP address ranges in America, Switzerland, etc., they had to release them first. Strangely enough, some IPs still worked even if they were registered outside of Germany.

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,930

5930 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

895

895 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Zscaler causes Error connection reset for specific websites?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AfAg8HCQS/sipa-for-remote-access","lastmod":"2024-12-17T02:22:21.000Z","id":"0D54u0000AfAg8HCQS"} -->
## SIPA for remote access

- Source: https://community.zscaler.com/s/question/0D54u0000AfAg8HCQS/sipa-for-remote-access
- Type: Q&A
- Last activity: 2024-12-17T02:22:21.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

BDR

(Partner) asked a question.

December 11, 2024 at 4:05 PM

SIPA for remote access

We have a server that we need to provide remote access to and usually, we'd use ZPA. However, could we use SIPA instead to provide access to that server? After all ZIA forwarding policies will send traffic to that server down the tunnel, do some security policy on it, and send it to an app connector which will resolve the DNS and send to the server. I appreciate that ZPA and SIPA are essentially the same but the licensing is different. We'd not get the two tunnels (both TLS and DTLS) and just have one tunnel.

Is there some limitation or other reason why SIPA cannot be used for remote access via ZIA?

ZIA - Forwarding

1 answer

401 views

Ronnie Meekers

(Partner)

2 years ago

In theory this would work but you have a few dependencies on how you do your traffic steering on the client side.

Traffic needs to arrive at ZIA for this to work, so consider the following:

Does the private destination resolve in public DNS?

Does the private destination's IP address fall within the inclusions of your ZCC tunnel 2.0 inclusions (if that's what you're using)

if not using tunnel 2.0 - how are you directing this traffic to ZIA.

It's not a stock standard scenario so you'll need to work through the detail.

In additional to the above, keep in mind that there is volume limit (license) for the SIPA traffic.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

490

490 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

SIPA for remote access
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AfBBHDCQ4/enhancing-security-in-developer-environments-with-ssl-inspection","lastmod":"2025-11-18T03:19:23.000Z","id":"0D54u0000AfBBHDCQ4"} -->
## Enhancing Security in Developer Environments with SSL Inspection

- Source: https://community.zscaler.com/s/question/0D54u0000AfBBHDCQ4/enhancing-security-in-developer-environments-with-ssl-inspection
- Type: Q&A
- Last activity: 2025-11-18T03:19:23.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - SSL Inspection

manuel

(Customer) asked a question.

December 11, 2024 at 6:23 PM

Enhancing Security in Developer Environments with SSL Inspection

Hello Zscaler Community,

We hear from many of you that you’re constantly balancing the need for robust security measures with the necessity to maintain a seamless software development workflow. One critical area where this balance is crucial is in the implementation of SSL/TLS inspection within developer environments.

Encrypted traffic inspection is a powerful tool in our security arsenal that provides the visibility necessary to block threats hidden within. However, setting it up in a way that doesn't disrupt developer productivity can be challenging. I'd like to share some insights and open up a discussion on how you can effectively implement SSL/TLS inspection without slowing down your developers.

Key Steps for Implementing SSL Inspection

Here are some high-level recommendations to think about as you consider implementing encrypted traffic inspection for your dev team:

Creating an SSL Inspection Policy:

It's essential to configure policies that selectively bypass traffic associated with specific development tools while inspecting other types of traffic. This targeted approach helps minimize disruptions to developer workflows.

Integration of Development Tools with Zscaler:

Ensuring that all development tools are integrated with Zscaler's certificate store is crucial. Zscaler can support 20 different tools and programming languages for traffic inspection but without adding extra processes for developers to contend with.

Automating Certificate Management:

Automating the use of the correct certificate stores can significantly reduce the manual overhead for developers. This automation ensures that security is a background process, not a hurdle.

Custom CA Bundles:

Building a custom CA bundle that includes the Zscaler root certificate is vital. This ensures that all tools and applications within the development environment trust the intercepted traffic, thereby maintaining the integrity of the development process.

Tool-Specific Configuration:

Each tool might require specific steps to integrate with the SSL/TLS inspection process. For instance, configuring Oracle Java with the

keytool

command or setting up Git to recognize the custom CA-bundle with

git config --global http.sslCAInfo

are key to those specific tools.

We have a blog that dives deep into this as well as a solution guide with practical examples that show you how to implement encrypted traffic inspection for many developer tools. There’s also valuable help documentation on this topic that covers how to put SSL inspection to work with specific developer tools.

We’d also love to get your “from the trenches” perspective on:

How you’ve implemented SSL inspection in your environments, and what challenges have you faced.

The specific tools or programming languages that you find more challenging to configure for SSL inspection.

What strategies have you found effective in maintaining a balance between security and developer productivity?

How you solved problems with these special developer-favorite tools like ngrok or similar.

Here are the resources:

Blog:

Setting Up SSL Inspection in Developer Environments to Defend Against Advanced Threats

Solution guide

Help files for adding custom app certificates

ZIA - SSL Inspection

1 answer

514 views

pengellyd

BrendonLA_PMM

, and

G-Man8

like this.

Ramesh Mani

(Partner)

2 years ago

Thanks for sharing .

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2021

at

07:54 PM

Youtube Restricted Mode Bypass Safe Search

ZIA - SSL Inspection

JonM

8 Views

1 Like

5 Comments

11/24/2021

at

06:09 AM

Reflection time of SSL inspection exclusion settings

ZIA - SSL Inspection

Nyajima

4 Views

0 Likes

1 Comment

4/26/2024

at

02:39 PM

End User Notifcation - Submit To Security Cloud option

ZIA - SSL Inspection

citrus3118

309

309 Views

0 Likes

1 Comment

8/21/2024

at

08:06 PM

StackHawk with Zscaler SSL Inspection

ZIA - SSL Inspection

User16182582818315999266

565

565 Views

0 Likes

4 Comments

3/10/2025

at

12:22 AM

Reputation Block policy

ZIA - SSL Inspection

JM

414

414 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Enhancing Security in Developer Environments with SSL Inspection
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AfI1I2CQK/ssl-inspection-pkix-path-building-failed-sunsecurityprovidercertpathsuncertpathbuilderexception-errors","lastmod":"2024-12-17T02:22:21.000Z","id":"0D54u0000AfI1I2CQK"} -->
## SSL Inspection - PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException errors

- Source: https://community.zscaler.com/s/question/0D54u0000AfI1I2CQK/ssl-inspection-pkix-path-building-failed-sunsecurityprovidercertpathsuncertpathbuilderexception-errors
- Type: Q&A
- Last activity: 2024-12-17T02:22:21.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - SSL Inspection

ramaonline

(Customer) asked a question.

Edited December 13, 2024 at 8:18 PM

SSL Inspection - PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException errors

Hello community:

After enabling SSL Inspection, we are running into these errors when connecting to an iPaaS application from the Linux server. The client application on Linux connects to its web hosted control plane (Example cdn.xxx.com). The java cacert truststore on Linux has already been updated with the complete chain of ZScaler root and intermediate certificates.

PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target]","exc

In addition, we also see a TLSv1.3 Client Hello (SNI cdn.xxx.com), followed by a TCP RST in the network tcp dump around the time of this failure.

The target platform does not support TLSv1.3, and the inspection policy has TLSv1.2 and above set so not sure why the TLSV1.3 Client Hello is showing up in the TCP Dump.

As a workaround, we have enabled an exception for the control plane host address (example: cdn.xxx.com)

Are there other steps to be performed on the web application side to resolve these connectivity issues? The control plane web application has a different root cert and does not have the ZScaler root certificate pinned.

The exception cannot be kept in place for an extended period of time.

Thanks

RB

ZIA - SSL Inspection

3 answers

1.71K views

Jainil_G

(Employee)

2 years ago

Hello @Ram Bysani​

As a first step, my suggestion is to re-validate whether the certificate installation is proper. As a reference, you can check this guide for the

Java Trust store

. While it may not be 100% applicable, it can be used as a reference. Additionally, if you have any other applications using the same trust store, you can attempt to check the TLS connection from them, which might confirm if the application is the issue or if it is a certificate installation problem.

If the destination has a requirement for certificate pinning (

https://help.zscaler.com/zia/certificate-pinning-and-ssl-inspection

), then Zscaler performing MiTM with its certificate won't solve the issue, even if you have the proper certificate installed. You can confirm with the destination control plane web application if they have any such hardcoded configuration of certificate pinning.

Additionally, my suggestion is to check direct TLS v1.2 or v1.3 connectivity from the local machine via the following commands:

openssl s_client -connect

cdn.xxx.com

:443 -tls1_2

openssl s_client -connect

cdn.xxx.com

:443 -tls1_3

The important details to review from the above command outputs are:

CONNECTED: Indicates that the connection was established successfully.

Protocol: TLSv1.2: Confirms that the server supports and is communicating over TLS 1.2.

If there is certificate pinning, SSL exception is only the option.

ramaonline

(Customer)

2 years ago

Thanks Jainil. Let me verify this information again, and will update here.

1 like

Dion_Thanos

(Partner)

2 years ago

Also remember that linux/java/applications can have different trust stores. So even though you have the os certificates implemented the application or java might not honor those trust stores.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2021

at

07:54 PM

Youtube Restricted Mode Bypass Safe Search

ZIA - SSL Inspection

JonM

8 Views

1 Like

5 Comments

11/24/2021

at

06:09 AM

Reflection time of SSL inspection exclusion settings

ZIA - SSL Inspection

Nyajima

4 Views

0 Likes

1 Comment

4/26/2024

at

02:39 PM

End User Notifcation - Submit To Security Cloud option

ZIA - SSL Inspection

citrus3118

311

311 Views

0 Likes

1 Comment

8/21/2024

at

08:06 PM

StackHawk with Zscaler SSL Inspection

ZIA - SSL Inspection

User16182582818315999266

574

574 Views

0 Likes

4 Comments

3/10/2025

at

12:22 AM

Reputation Block policy

ZIA - SSL Inspection

JM

420

420 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

SSL Inspection - PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException errors
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AfYeVMCQ0/utf8-encoded-zia-logs","lastmod":"2025-01-03T05:01:51.000Z","id":"0D54u0000AfYeVMCQ0"} -->
## UTF-8 encoded ZIA logs

- Source: https://community.zscaler.com/s/question/0D54u0000AfYeVMCQ0/utf8-encoded-zia-logs
- Type: Q&A
- Last activity: 2025-01-03T05:01:51.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Logging

bpirone

(Customer) asked a question.

December 20, 2024 at 6:32 PM

UTF-8 encoded ZIA logs

Has anyone experienced issues with non-English character like filenames in the ZIA console web insights?

ZIA - Logging

1 answer

306 views

Jainil_G

(Employee)

2 years ago

Hello @Bill Pirone​ ZIA detects the file names from the "Content-Disposition" field in the HTTP header. If the "Content-Disposition" field records the file name, ZIA fetches it and displays it in the Web Insights Logs. However, if the upload website sets a blank filename or a name in an unreadable format, ZIA will not be able to detect the filename.

If you have a specific non-English character, and if it is readable from "Content-Disposition" field in the HTTP header, it would display it.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Log In to Answer

Associated Tags

azure

logging

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

6/8/2023

at

08:51 PM

URL Reporting

ZIA - Logging

akita_down

12

12 Views

0 Likes

4 Comments

11/3/2023

at

06:08 PM

ZIA - NSS Feed Status

ZIA - Logging

Rallis

618

618 Views

0 Likes

3 Comments

2/21/2024

at

10:43 PM

Find if User is behind a VPN

ZIA - Logging

venG

544

544 Views

0 Likes

2 Comments

3/13/2025

at

01:56 AM

How to Extract Logs from Web Insight

ZIA - Logging

JSK27

674

674 Views

0 Likes

8 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

UTF-8 encoded ZIA logs
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AfYlNICQ0/detect-antivirus-device-posture-on-macos","lastmod":"2025-01-02T18:49:13.000Z","id":"0D54u0000AfYlNICQ0"} -->
## Detect Antivirus Device Posture on macOS

- Source: https://community.zscaler.com/s/question/0D54u0000AfYlNICQ0/detect-antivirus-device-posture-on-macos
- Type: Q&A
- Last activity: 2025-01-02T18:49:13.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

eryherawan

(Customer) asked a question.

December 21, 2024 at 3:47 AM

Detect Antivirus Device Posture on macOS

Has anyone here ever create Detect Antivirus as device posture on macOS?

I already create 7 device posture based on systemextensionsctl list output but none of it hit it, here is my systemextensionsctl list output:

also here is my 7 device posture based on that ouput:

com.avast.Antivirus.SystemExtension

Avast Security

com.avast.Antivirus.SystemExtension (13.14.480/13.14.480)

com.avast.Antivirus.SystemExtension (13.14.480/13.14.480) Avast Security

com.avast.Antivirus.SystemExtension Avast Security

Avast Security com.avast.Antivirus.SystemExtension

Avast Security com.avast.Antivirus.SystemExtension (13.14.480/13.14.480)

What is the correct way to write AV name for Detect Antivirus device posture on macOS?

Client Connector

1 answer

784 views

joemozdy

(Customer)

2 years ago

Based on the documentation at

https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Detect-Antivirus

, you will need the complete name to enter in the Device Posture field in the Client Connector admin portal. From your screenshot, the fields lining up are

enabled  - *

active  - *

teamID  - 6H4HRTUSE3

bundleID (version) - com.avast.Antivirus.SystemExtension (13.14.480/13.14.480)

name - Avast Security

[state] - [activated enabled]

So you should just put in 'Avast Security' into the Device Posture field. Not sure how you are checking if the device posture is getting checked properly though. You can either set up a device posture profile for MacOS in the Client Connector admin portal (set it higher than any other posture profile you happen to be using), or set up an access policy in ZPA that requires you to actively pass that check to access something. If you go the device posture profile in the Client Connector admin portal route, fully reboot the device to have trigger the device posture check and then you can just check your enrolled devices tab. Approximately 15 min after you reboot the device, it should have updated there. If it says either Unknown or anything lower than what you set the device posture profile level to be, then it's not passing the posture check. If you set up a ZPA policy, you shouldn't have to reboot the device but I would still wait about 15 minutes after setting up the policy. It could help to set up two policies so you can check whether you can access the resource if you both pass or fail the posture check. If you only set up a block policy on failing the posture check, you can still access the resource with an Unknown device posture because that's not an explicit failure. Unknown device postures will fail a positive check though.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/7/2020

at

04:05 AM

Z-App -8 Network Error when users log in on Windows 10

Client Connector

cburge97

2,884

2884 Views

0 Likes

11

11 Comments

7/3/2020

at

11:55 AM

ZAPP intune deployment

Client Connector

Mk001

1,378

1378 Views

0 Likes

4 Comments

1/28/2021

at

03:42 PM

Compare ezAgent and ZCC - when to use which?

Client Connector

hukel

704

704 Views

0 Likes

2 Comments

8/18/2020

at

12:15 PM

MacOS Zscaler App Log Location

Client Connector

brad

3,567

3567 Views

0 Likes

1 Comment

3/7/2022

at

03:41 PM

Can a User with multiple devices use them simultaneously whilst logged in using that same single account

Client Connector

michael.makombe

4 Views

0 Likes

12

12 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Detect Antivirus Device Posture on macOS
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AftFXpCQM/connectivity-issue-for-a-url","lastmod":"2025-01-13T06:27:52.000Z","id":"0D54u0000AftFXpCQM"} -->
## Connectivity Issue for a URL

- Source: https://community.zscaler.com/s/question/0D54u0000AftFXpCQM/connectivity-issue-for-a-url
- Type: Q&A
- Last activity: 2025-01-13T06:27:52.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

Anurag12345

(Customer) asked a question.

January 6, 2025 at 1:41 AM

Connectivity Issue for a URL

Hi,

My Team is trying to access a website, but it's getting blocked. We are getting error as 'Not Allowed to Browse this website' and 'Not allowed to Browse Miscellaneous or Unknown Category'.

So, we added the URL into a custom Cloud App category and then created a policy in URL Filtering Policy to allow it.

Even this the user is not able to access. Also, i am not able to view any logs for this URL under Web Insights. The URL is legitimate as i am able to access in another laptop without ZCC.

Questions:

Why I don't see logs in Web Insights?

How to solve this issue?

ZIA - URL Filtering

4 answers

880 views

Top Rated Answers

Jainil_G

(Employee)

2 years ago

Hi @Anurag Maurya​ , I'll suggest the following approach to troubleshoot the issue.

Let's say you have "

www.exampleformisc.com

" as a website that has not yet been classified by Zscaler. As a result, it falls under the Miscellaneous or Unknown Category. If the Miscellaneous or Unknown Category is blocked, your website "

www.exampleformisc.com

" will be blocked as well. To allow "

www.exampleformisc.com

," simply create a new custom category and add "

www.exampleformisc.com

" under URLs Retaining Parent Category. Then, allow the new custom category in the URL Policy, ensuring it is placed above your deny URL policy.

If you are still facing issues, you can check the Web Insights log. If there are no bypasses, it should show the blocked website. If you are not getting logs, verify whether you are receiving logs for any other website for the same user/laptop to confirm if traffic is passing through Zscaler.

If a website is on custom port, and if the custom port is block, then you can see logs in the Firewall Insights as well.

Additionally, it is possible that some other sub-URLs for the main website are not allowed. In this case, you can capture a HAR file as per the instructions at

https://help.zscaler.com/zia/capturing-http-headers-google-chrome

and add the missing URLs.

I hope the above information helps you further.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Selected as Best

1 like

All Answers

Jainil_G

(Employee)

2 years ago

Hi @Anurag Maurya​ , I'll suggest the following approach to troubleshoot the issue.

Let's say you have "

www.exampleformisc.com

" as a website that has not yet been classified by Zscaler. As a result, it falls under the Miscellaneous or Unknown Category. If the Miscellaneous or Unknown Category is blocked, your website "

www.exampleformisc.com

" will be blocked as well. To allow "

www.exampleformisc.com

," simply create a new custom category and add "

www.exampleformisc.com

" under URLs Retaining Parent Category. Then, allow the new custom category in the URL Policy, ensuring it is placed above your deny URL policy.

If you are still facing issues, you can check the Web Insights log. If there are no bypasses, it should show the blocked website. If you are not getting logs, verify whether you are receiving logs for any other website for the same user/laptop to confirm if traffic is passing through Zscaler.

If a website is on custom port, and if the custom port is block, then you can see logs in the Firewall Insights as well.

Additionally, it is possible that some other sub-URLs for the main website are not allowed. In this case, you can capture a HAR file as per the instructions at

https://help.zscaler.com/zia/capturing-http-headers-google-chrome

and add the missing URLs.

I hope the above information helps you further.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Selected as Best

1 like

Gianluca.bastia

(Partner)

2 years ago

When you created the custom URL Category, did you use the URL Retaining Parent category? If you did, you need to make sure that the allow policy is above the block policy.

Anurag12345

(Customer)

2 years ago

Thank you @Jainil Gajjar​  and @GianlUca Bastia​

Your solution has resolved the issue.

User is able to access the URL after I added it in URL Retaining Parent Category.

A quick question,

Why adding in custom URL didn't work out?

Jainil_G

(Employee)

2 years ago

Hi @Anurag Maurya​ Thanks for the feedback.

The URLs retaining the parent category are covered by policies that reference both the original parent category and this custom category. For example, if you enter

www.amazon.com

is in custom category, it will be covered by policies that reference this custom category as well as by policies that reference Online Shopping, its parent URL category.

In your case, if you have added a URL to a custom category and that custom category is not at the top or hasn't been configured properly, it could result in such issues.

BR

Jainil_G

Log In to Answer

Associated Tags

accesspolicy

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,913

5913 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

888

888 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Connectivity Issue for a URL
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AfvTSeCQM/best-options-choosing-a-data-centre","lastmod":"2025-01-10T07:38:07.000Z","id":"0D54u0000AfvTSeCQM"} -->
## Best options choosing a data centre

- Source: https://community.zscaler.com/s/question/0D54u0000AfvTSeCQM/best-options-choosing-a-data-centre
- Type: Q&A
- Last activity: 2025-01-10T07:38:07.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

JM

(Partner) asked a question.

January 10, 2025 at 2:35 AM

Best options choosing a data centre

Hello,

Can someone make a recommendation/suggestion on which data centre is the best option here?

We have 2 GRE tunnels configured;

MEL2 (Primary DC) & SYD3 (Secondary)

SYD3 (Primary DC) & AKL1 (Secondary)

AKL1 has been decommissioned by Zscaler last December 2024. Can you please help me choose a data centre on which is the best option to choose for our second GRE tunnel? Is it good to have MEL2 to be the secondary Dc or the new AKL2?

ZIA - Forwarding

3 answers

347 views

Top Rated Answers

Jainil_G

(Employee)

2 years ago

Hello @Joanna Villamor​  With the configuration of GRE on ZIA, when selecting the Primary DC and Secondary DC, it will list the locations nearest to your Public IP which would make you aware about proximity. However, if you want regional diversity, AKL2 would be better. If you prefer a domestic preference, then Canberra could be an option for Australia.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Selected as Best

1 like

All Answers

JM

(Partner)

2 years ago

Please include the latency of each, we are checking the Canberra I, and Auckland II and the least option is the Melbourne 2 (since it is already been used).

Ramesh Mani

(Partner)

2 years ago

Do a MTTR trace from the CPE at your location and decide the best location.

2 likes

Jainil_G

(Employee)

2 years ago

Hello @Joanna Villamor​  With the configuration of GRE on ZIA, when selecting the Primary DC and Secondary DC, it will list the locations nearest to your Public IP which would make you aware about proximity. However, if you want regional diversity, AKL2 would be better. If you prefer a domestic preference, then Canberra could be an option for Australia.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Selected as Best

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

490

490 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Best options choosing a data centre
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000Qo9fb0AB/how-to-find-aipmippureview-sensitivity-label-of-file","lastmod":"2025-01-14T12:45:08.000Z","id":"0D5PJ00000Qo9fb0AB"} -->
## How to find AIP/MIP/Pureview Sensitivity Label of file

- Source: https://community.zscaler.com/s/question/0D5PJ00000Qo9fb0AB/how-to-find-aipmippureview-sensitivity-label-of-file
- Type: Q&A
- Last activity: 2025-01-14T12:45:08.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - DLP

vaibhav_j

(Customer) asked a question.

January 13, 2025 at 11:46 AM

How to find AIP/MIP/Pureview Sensitivity Label of file

I have DLP rule (rule monitors multiple sensitivity labels added at single DLP Dictionary) just to monitor (upload to cloud app like gmail/gdrive etc.) files labelled with microsoft sensitivity label.

DLP rule has worked but checking report/logs available from Web Insight there is no detail of sensitivity label associated to each uploaded file to gmail/gdrive etc.

I can see file name e.g. file.docx, file2.pdf but no detail of sensitivity label

example:

file1 - labelled with [confidential]

file2 - lablled with [highly confidential]

file3 - labelled with [public]

Question: How/where can i find sensitivity label associated to each uploaded file so customer can get more insight and measure risk?

ZIA - DLP

3 answers

374 views

Gianluca.bastia

(Partner)

2 years ago

Within the web insights you won’t be able to see this information unless you split the engines or dictionaries.

A few options to see the violating content are to utilize the email notifications and include that within the notification template. However, because sensitivity labels are part of metadata, this might not actually work as expected. Or, you’d need the incident management license and leverage workflow automation with incident receivers.

incident receivers were included in some legacy licensing so you may be able to leverage those without the workflow automation. You will not git as much benefit out of them though.

vaibhav_j

(Customer)

2 years ago

Thanks for the response!

You are right, easiest way is to split the engines and dictionaries since we are at basic license.

Very disappointed with ZScaler's ability to meet need of simple ask.

Maarten van den Bogaard

(Partner)

2 years ago

We've ran into the same limitation with one of our clients. If there aren't many labels then Gianluca's suggestion of splitting the dictionaries/engines works well enough. Let's hope this gets improved upon in the future.

1 like

Log In to Answer

Associated Tags

logging

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/9/2023

at

05:32 PM

DLP notification via Zscaler Client Connector

ZIA - DLP

thacarvalho123

7 Views

2 Likes

2 Comments

11/20/2022

at

09:26 PM

External DLP Engine

ZIA - DLP

H.zyD.zy

4 Views

0 Likes

3 Comments

11/9/2022

at

04:51 PM

Zscaler Preventing Image Upload and Download on iPhone

ZIA - DLP

MacDanorld1

2 Views

0 Likes

3 Comments

6/20/2022

at

07:53 AM

Error during Microsoft Information Protection(MIP) Integration

ZIA - DLP

ozanogur

3 Views

0 Likes

4 Comments

1/31/2021

at

08:35 PM

Zscaler ZIA and ZPA with NetSkope CASB and DLP services

ZIA - DLP

jonathan.holt

1,341

1341 Views

1 Like

6 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

How to find AIP/MIP/Pureview Sensitivity Label of file
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000RKAwd0AH/zscalers-one-click-for-microsoft-365","lastmod":"2025-06-27T00:31:47.000Z","id":"0D5PJ00000RKAwd0AH"} -->
## Zscaler's One Click for Microsoft 365

- Source: https://community.zscaler.com/s/question/0D5PJ00000RKAwd0AH/zscalers-one-click-for-microsoft-365
- Type: Q&A
- Last activity: 2025-06-27T00:31:47.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Cloud Firewall

siddhartha.aggarwal

(Employee) asked a question.

Edited June 27, 2025 at 12:31 AM

Zscaler's One Click for Microsoft 365

Overview

Microsoft 365 (M365) is a suite of cloud-based services designed to enhance security, reliability, and user productivity for organizations. It offers a comprehensive set of applications and services, updated automatically to ensure users always have the latest versions.

If your organization utilizes any M365 applications, you can seamlessly route all M365 traffic (including remote user traffic) through the Zscaler Zero Trust Exchange (ZTE) to the Microsoft cloud.

Currently, Zscaler has two configuration options to choose from for Microsoft 365 traffic:

Microsoft-Recommended One Click Office 365 Configuration

In Experience Center: Under Policy > Access Control > Internet & SaaS > Advanced Settings

Office 365 One Click Configuration:

In Experience Center: Under

Policies > Common Configuration > Advanced > Advanced Settings

In this article, we look at the "Microsoft-Recommended One Click Office 365 Configuration" since it is based on Microsoft's recommendations.

Microsoft recommends that proxies transparently forward end-user M365 traffic to their cloud. To facilitate this for security vendors like Zscaler, Microsoft provides a publicly available list of FQDNs, wildcard domain names, and IP address ranges for the endpoint set. Zscaler leverages this list to identify M365 application traffic based on IP address and FQDN.

Zscaler’s ‘One Click’ setting allows this traffic to be forwarded directly to the Microsoft cloud, bypassing SSL Inspection and other security policies for optimal performance.

Note:

While the One Click setting optimizes performance, Zscaler selectively inspects M365 traffic to prioritize security. This ensures that while performance is enhanced, your organization maintains a robust security posture. See more on this under Zscaler Philosophy: Selective Inspection.

Data Sources and Updates

Zscaler uses a select list of FQDNs and IP ranges listed in the following Microsoft 365 endpoint sources:

Office 365 Worldwide (+GCC)

Office 365 operated by 21 Vianet

Office 365 U.S. Government DoD

Office 365 U.S. Government GCC High

Zscaler regularly updates this information. Zscaler checks for updates every 24 hours and performs a complete update weekly to ensure comprehensive accuracy.

Zscaler Philosophy: Selective Inspection

While the One Click setting bypasses SSL Inspection for most M365 traffic, Zscaler may selectively inspect certain traffic to ensure robust security.

Zscaler conducts thorough security assessments of Microsoft’s recommendations. In cases where a recommendation may pose a security risk, Zscaler will prioritize customer security and may not include it in the default ‘allow’ in the One Click setting.

For example, at one Microsoft recommended allowing access to a broad range of Azure blobs (e.g., *.blob.core.windows.net). This was deemed to be a potential security risk by Zscaler ThreatLabz and so Zscaler did not include this in the One Click setting.

Customization Options

Bypass Specific Traffic:

If you need to bypass SSL Inspection for any specific FQDN or URL (including general blob access), you can easily create a custom URL category and configure an SSL Inspection policy to exempt that category.

Apply Inspection to M365 Traffic:

If you determine that the One Click setting is too permissive for your specific security requirements, you can create an SSL Inspection policy to apply inspection to M365 traffic.

Key Benefits of Using Zscaler with Microsoft 365

Enhanced Security:

Zscaler's selective inspection approach ensures a strong security posture while optimizing performance for M365 applications.

Comprehensive Visibility:

Gain complete visibility into all user traffic, both Microsoft and non-Microsoft, through a single source of logs. Additionally, with Zscaler Client Connector's Flow Logging feature, one can get visibility into traffic that may be bypassed from the endpoint via ZCC, such as Microsoft Teams traffic.

Selective Inspection:

Maintain the ability to inspect all M365 traffic for selected services or during periods of heightened risk (e.g., during attacks or mergers).

Bandwidth Control:

Optimize network performance by controlling bandwidth for both Microsoft and non-Microsoft services.

Global Network and Peering:

Zscaler's global network with extensive peering with Microsoft ensures efficient and secure communication with the Microsoft cloud.

Tenant Restrictions:

Enhance data security and prevent data leakage by dividing personal and corporate Microsoft services and enforcing tenant restrictions.

References and Further Reading

Zscaler Help documentation for M365/O365 (Experience Center)

Microsoft 365 URLs and IP address ranges

Microsoft 365 network connectivity principles

Zscaler and Microsoft Best Practices Guide

Zscaler and Microsoft partnership

ZIA - Cloud Firewall

3 answers

3.35K views

User16740286988473709053

Anamika_R

, and

3 others

like this.

Maarten van den Bogaard

(Partner)

2 years ago

Thank you for this clear and informative overview.

1 like

Ramesh Mani

(Partner)

2 years ago

Thanks for this detailed information.

1 like

Danielo

(Partner)

2 years ago

This is an excellent article @Siddhartha Aggarwal​

Many thanks for sharing this.

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2018

at

01:07 PM

Ip address as criteria in url policy

ZIA - Cloud Firewall

ram75

1,193

1193 Views

0 Likes

1 Comment

10/8/2020

at

08:41 AM

Block All access & Allow certain user or group (ZIA)

ZIA - Cloud Firewall

Sec_def_Def_sec

1,833

1833 Views

1 Like

2 Comments

12/19/2022

at

04:14 PM

URL filtering policy vs Cloud App policy control

ZIA - Cloud Firewall

Ahmed

4 Views

0 Likes

2 Comments

12/9/2022

at

09:40 PM

Apple News RSS Feed

ZIA - Cloud Firewall

Trace Woodbury-RidgeIT

2 Views

0 Likes

1 Comment

9/20/2022

at

03:20 PM

How does Zscaler Internet Access itself route the traffic to the internet, using what outgoing/next hop GW

ZIA - Cloud Firewall

tamerz

6 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Zscaler's One Click for Microsoft 365
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000RKPQG0A5/tenant-profile-support-for-box-cloud-application","lastmod":"2025-01-24T05:11:26.000Z","id":"0D5PJ00000RKPQG0A5"} -->
## Tenant Profile  support for Box cloud application

- Source: https://community.zscaler.com/s/question/0D5PJ00000RKPQG0A5/tenant-profile-support-for-box-cloud-application
- Type: Q&A
- Last activity: 2025-01-24T05:11:26.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

avshch

(Customer) asked a question.

January 22, 2025 at 4:09 AM

Tenant Profile  support for Box cloud application

We need an option to restrict access to specific Box account, which for other applications is done via Tenant Profiles.

Is there a feature request for Box application to support

Tenant Profile?

Thanks,

ZIA - URL Filtering

4 answers

443 views

Gianluca.bastia

(Partner)

2 years ago

For Box you could use the Application Instances option -

About Cloud Application Instances | Zscaler

Jainil_G

(Employee)

2 years ago

Hello @Alex Shchukin​ As highlighted in an earlier comment, you can use the Cloud Application Instances configuration(

https://help.zscaler.com/zia/about-cloud-application-instances

) for the Box application. You will need to raise a provision request, and once the feature is enabled, it will be available for your tenant.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

avshch

(Customer)

2 years ago

We have the apps definitions enabled. The problem is there is no way to associated tenant profile for box. We can do this for Google Drive. Thanks,

Jainil_G

(Employee)

2 years ago

Hello @Alex Shchukin​ , As of now, we do not have Tenant profile like configuration option for box, however, this can be achieved with Cloud Instance feature. This configuration of Cloud Instance is different from the Tenant profile. Once the feature is enabled, you will need to configure the Box Application Instance under Cloud Instance configuration and apply granular controls as per your requirements.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,922

5922 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

893

893 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Tenant Profile  support for Box cloud application
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000RLYg50AH/allowing-selective-youtube-channel","lastmod":"2025-01-23T08:50:11.000Z","id":"0D5PJ00000RLYg50AH"} -->
## Allowing Selective YouTube Channel

- Source: https://community.zscaler.com/s/question/0D5PJ00000RLYg50AH/allowing-selective-youtube-channel
- Type: Q&A
- Last activity: 2025-01-23T08:50:11.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

akuswara_bingo

(Customer) asked a question.

January 22, 2025 at 2:59 PM

Allowing Selective YouTube Channel

What is the best way to block direct access to YouTube while still allowing embedded YouTube videos in our LMS? We have been struggling to achieve this, and there must be a reliable method available. Our audience is using either Google Chrome or Microsoft Edge.

ZIA - URL Filtering

2 answers

436 views

avshch

(Customer)

a year ago

there is a similar question raised via

https://community.zscaler.com/s/question/0D54u00009evn65CAA/youtube-content-control

2 likes

Jainil_G

(Employee)

a year ago

Hi @Andreas Kuswara​ , You can check for the

Tenant Profile

Youtube configuration.

https://help.zscaler.com/zia/adding-tenant-profiles#youtube-channel-id

Step 1: Find the Channel (External Ref. link -

https://mixedanalytics.com/blog/find-a-youtube-channel-id/

),

Step 2: Configure Tenant Profile with channel ID obtained in Step 1

Step 3: Create two cloud application policies: (Policy > Cloud App Control > Streaming and Media) i.e First Rule (Allow Rule): To allow the particular channel with tenant profile, and Second Rule (Block Rule): To block the rest of YouTube.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

1 like

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,909

5909 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

888

888 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Allowing Selective YouTube Channel
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000RMpFz0AL/copy-of-overlap-between-custom-url-category-and-authentication-exemptions","lastmod":"2025-01-24T10:26:43.000Z","id":"0D5PJ00000RMpFz0AL"} -->
## Copy of Overlap between custom URL category and authentication exemptions

- Source: https://community.zscaler.com/s/question/0D5PJ00000RMpFz0AL/copy-of-overlap-between-custom-url-category-and-authentication-exemptions
- Type: Q&A
- Last activity: 2025-01-24T10:26:43.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

Kannan_S

(Employee) asked a question.

January 22, 2025 at 6:35 PM

Copy of Overlap between custom URL category and authentication exemptions

When a URL is added to Exempted URLs under Authentication Exemptions on the Advanced Settings page, it is assigned to an internal URL category called Auth_Bypass, while the parent category is preserved.This URL category is not visible under Administration > URL Categories and cannot be selected in any policy rules because it is only used to bypass cookie-based authentication. You can only add or remove URLs from this category by adding or removing them from Authentication Exemptions. Read more about these exemptions

here

Assume "xyz.safemarch.com" is added to the Authentication Exemptions URL list in Advanced settings.

If you create a custom URL category and enter the wildcard domain for the aforementioned authentication-exempted URL in the URLs Retaining Parent Category field, the policy evaluation will treat it as a less specific match. Continuing from the previous example, suppose ".safemarch.com" has been added to the custom category CAT1, and the predefined URL category for this domain is Professional Services.

When a user visits the website "xyz.safemarch.com," the categorization engine chooses the most specific category, in this case Auth_Bypass. Because no policy can be defined for this special URL category, the action taken will be determined solely by the policy for the predefined URL category for “xyz.safemarch.com”; any CAT1 rules will be ignored for this transaction. This logic of the most specific match is explained

here

The logs would show the predefined URL category. In this case, “Professional services” will be displayed

Solution:

On the Advanced Settings page, under Authentication Exemptions, add the URL "xyz.safemarch.com" to the Exempted URLs list.

Add "xyz.safemarch.com" to the URLs Retaining Parent Category field in URL category CAT1, along with ".safemarch.com". Create a URL filtering rule that allows the category CAT1. Create a second rule blocking the category Professional Services

Now, when you access "xyz.safemarch.com," both Auth_Bypass and CAT1 are equivalent. As a result, the transaction will be permitted in accordance with the policy for the custom category. In this case, CAT1 will be logged as the URL category in web insights.

ZIA - URL Filtering

1 answer

528 views

User16740286988473709053

Ramesh Mani

, and

Jainil_G

like this.

Gianluca.bastia

(Partner)

a year ago

Great insight

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,909

5909 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

888

888 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Copy of Overlap between custom URL category and authentication exemptions
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000RQrmg0AD/nonbrowser-user-agent-identification","lastmod":"2025-02-10T07:46:15.000Z","id":"0D5PJ00000RQrmg0AD"} -->
## non-browser user agent identification

- Source: https://community.zscaler.com/s/question/0D5PJ00000RQrmg0AD/nonbrowser-user-agent-identification
- Type: Q&A
- Last activity: 2025-02-10T07:46:15.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Authentication

avshch

(Customer) asked a question.

January 23, 2025 at 6:18 PM

non-browser user agent identification

Is there a way to identify non-browser user agents used (Power Shell etc)?

Traffic forwarding IPSEC.

ZIA - Authentication

4 answers

474 views

Gianluca.bastia

likes this.

Jainil_G

(Employee)

a year ago

Hello @Alex Shchukin​ ​ You can utilize the feature IP Surrogate -

https://help.zscaler.com/zia/about-surrogate-ip

With Surrogate IP, a user can authenticate to the service in one web browser and will not have to authenticate again if they open another web browser or use non-browser applications (Applications that do not support cookies, such as Google Earth and Skydrive,  HTTPS transactions that are not decrypted, Transactions that use unknown user agents).

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

avshch

(Customer)

a year ago

@Jainil Gajjar​ Does Surrogate IP work inside of IPSEC tunnel?

1 like

Jainil_G

(Employee)

a year ago

@Alex Shchukin​ It does not apply to Zscaler Client Connector deployments, as we are able to obtain the private IP address in this case. However, for GRE/IPSec or PAC over GRE deployments, it would be applied.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

1 like

Totti

(Partner)

a year ago

Does Jainil's answer help you or is it that you want to know to which application the user agent belongs to (--> identify rather than authenticate)?

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/15/2022

at

12:39 PM

Onprem Active directory integrate with Zscaler cloud ZIA

ZIA - Authentication

aravindhan.m

3 Views

0 Likes

3 Comments

7/11/2022

at

07:26 AM

Zscaler IDP authentication issue

ZIA - Authentication

aravindhan.m

4 Views

0 Likes

2 Comments

5/27/2022

at

01:49 PM

Zscaler ZIA O365 Apps not authenticating

ZIA - Authentication

abmaclean

4 Views

0 Likes

6 Comments

10/4/2021

at

10:43 PM

Automatic de-provisioning using SCIM with Azure AD

ZIA - Authentication

Paul_Wineberg

4 Views

0 Likes

4 Comments

6/27/2020

at

03:01 PM

Zscaler public preview

ZIA - Authentication

Muhammad

364

364 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

non-browser user agent identification
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000RVZw30AH/soft-ssl-bypass","lastmod":"2025-01-28T19:56:59.000Z","id":"0D5PJ00000RVZw30AH"} -->
## Soft SSL bypass

- Source: https://community.zscaler.com/s/question/0D5PJ00000RVZw30AH/soft-ssl-bypass
- Type: Q&A
- Last activity: 2025-01-28T19:56:59.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - SSL Inspection

sukanyas

(Customer) asked a question.

January 24, 2025 at 7:44 PM

Soft SSL bypass

How do you set up Soft SSL Bypass ? How does it different from Hard SSL bypass?

Thanks,

ZIA - SSL Inspection

3 answers

484 views

joemozdy

(Customer)

a year ago

What do you mean by soft or hard ssl bypass in this context? Are you referring more to a situation where you want to bypass ssl inspection for only a group of people vs having to bypass ssl inspection for all users?

sukanyas

(Customer)

a year ago

No, I want to inspect, check the Minumum TLS version and OCSP and untrusted server certificate, however, i do not want to user Man-in the middle certificate by Zscaler for the particular URL. I wanted to inspect for security check then no decryption and encryption by Zscaler. How can I achieve this?

joemozdy

(Customer)

a year ago

So if you want to check for minimum TLS and OCSP, but not do any SSL traffic inspection, then you would just make or edit the appropriate SSL rule and select Do Not Inspect and Evaluate Other Policies. You will still have the options to specify a minimum TLS version, OCSP,  and check for untrusted server certificates. If you select Bypass Other Policies, you will not have these options present.

1 like

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2021

at

07:54 PM

Youtube Restricted Mode Bypass Safe Search

ZIA - SSL Inspection

JonM

8 Views

1 Like

5 Comments

11/24/2021

at

06:09 AM

Reflection time of SSL inspection exclusion settings

ZIA - SSL Inspection

Nyajima

4 Views

0 Likes

1 Comment

4/26/2024

at

02:39 PM

End User Notifcation - Submit To Security Cloud option

ZIA - SSL Inspection

citrus3118

309

309 Views

0 Likes

1 Comment

8/21/2024

at

08:06 PM

StackHawk with Zscaler SSL Inspection

ZIA - SSL Inspection

User16182582818315999266

565

565 Views

0 Likes

4 Comments

3/10/2025

at

12:22 AM

Reputation Block policy

ZIA - SSL Inspection

JM

414

414 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Soft SSL bypass
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000RXX1H0AX/error-during-any-change-submission-data-access-error-0x18","lastmod":"2025-02-10T07:39:33.000Z","id":"0D5PJ00000RXX1H0AX"} -->
## Error During Any change submission Data Access Error 0x18

- Source: https://community.zscaler.com/s/question/0D5PJ00000RXX1H0AX/error-during-any-change-submission-data-access-error-0x18
- Type: Q&A
- Last activity: 2025-02-10T07:39:33.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

AvinashKashid

(Customer) asked a question.

January 25, 2025 at 5:21 AM

Error During Any change submission Data Access Error 0x18

Error During Any change submission

Error Code : Data Access Error 0x18

ZIA - URL Filtering

3 answers

242 views

joemozdy

(Customer)

2 years ago

Is this an error that you saw in your ZIA logs? What is the context for what happened?

User16594623363841085861

(Customer)

a year ago

I am seeing this as well. Only with one particular cloud app control rule. I cannot make any changes to it. When I save, it gives me the Data Access Error 0x18 in a zscaler popup at the top of the screen and does not apply the edits I made.

Jainil_G

(Employee)

a year ago

Hello @Avinash Kashid​ , We have a known bug — BUG-198726 — which is scheduled to be fixed in the next release by the end of February.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,933

5933 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

897

897 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Error During Any change submission Data Access Error 0x18
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000RfgLi0AJ/direct-certain-countries-to-different-se-for-a-url","lastmod":"2025-03-06T13:48:08.000Z","id":"0D5PJ00000RfgLi0AJ"} -->
## Direct certain countries to different SE for a URL

- Source: https://community.zscaler.com/s/question/0D5PJ00000RfgLi0AJ/direct-certain-countries-to-different-se-for-a-url
- Type: Q&A
- Last activity: 2025-03-06T13:48:08.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

danbro185

(Customer) asked a question.

January 28, 2025 at 9:23 AM

Direct certain countries to different SE for a URL

Hi,

We have a number of sites in east Asia which use the H.K SE DC for ZIA access, however ChatGPT has Geo blocked China/HK from accessing the site. Is there a way adding a command in the PAC file which first uses the var country = "${COUNTRY}"

for multiple countries? and then uses an IF for the domain to send it to the Singapore PROXY?

var country = "${COUNTRY}";

if (shExpMatch(country,"Vietnam") 'plus 5 more countries in the var

&&

((dnsDomainIs(host,".chatgpt.com")) ||

(dnsDomainIs(host,".

chat.openai.com

")))) {

return "PROXY ${COUNTRY_GATEWAY}:80;DIRECT";

tried the above and it didnt seem to do anything?

5 answers

492 views

ut141

likes this.

Top Rated Answers

Osho_Dwivedi

(Employee)

a year ago

If I understood your requirement correctly,

If Country: (C1 OR C2 OR C3 OR C4 OR C5)

AND

URL: (U1 OR U2)

return proxy P1;

Something like this should work:

var country = "${COUNTRY}";

if ((shExpMatch(country,"C1")) ||

(shExpMatch(country,"C2")) ||

(shExpMatch(country,"C3")) ||

(shExpMatch(country,"C4")) ||

(shExpMatch(country,"C5")) &&

(shExpMatch(host,".

chatgpt.com

")) ||

(shExpMatch(host,".

chat.openai.com

")))

return "PROXY sin4.sme.<cloudname>.net:80;DIRECT";

Selected as Best

All Answers

joemozdy

(Customer)

a year ago

An easier method for you might be to make use of a Zscaler Subcloud,

https://help.zscaler.com/zia/about-subclouds

, where you just don't include the Hong Kong data center in your subcloud. That way your users would not be routed to that data center at all and you would avoid this issue.

Osho_Dwivedi

(Employee)

a year ago

If I understood your requirement correctly,

If Country: (C1 OR C2 OR C3 OR C4 OR C5)

AND

URL: (U1 OR U2)

return proxy P1;

Something like this should work:

var country = "${COUNTRY}";

if ((shExpMatch(country,"C1")) ||

(shExpMatch(country,"C2")) ||

(shExpMatch(country,"C3")) ||

(shExpMatch(country,"C4")) ||

(shExpMatch(country,"C5")) &&

(shExpMatch(host,".

chatgpt.com

")) ||

(shExpMatch(host,".

chat.openai.com

")))

return "PROXY sin4.sme.<cloudname>.net:80;DIRECT";

Selected as Best

danbro185

(Customer)

a year ago

Support helped me and we got the following  working for us:

var country = "${COUNTRY}";

if (((shExpMatch(country,"Cambodia")) ||

(shExpMatch(country,"Vietnam")) ||

(shExpMatch(country,"Malaysia")) ||

(shExpMatch(country,"Thailand")) ||

(shExpMatch(country,"Indonesia"))) &&

((shExpMatch(host,"*.

chatgpt.com

")) ||

(shExpMatch(host,"*.

chat.openai.com

")))) {

return "PROXY

sin4.sme.zscaler.net

:80;DIRECT";

danbro185

(Customer)

a year ago

osho_dwivedi your code helped, just missed a set of parenthesis and it started working perfectly. Cheers

1 like

Osho_Dwivedi

(Employee)

a year ago

glad it did, I didn't validate it in PAC validator but yeah syntax is important any given day, my apologies.

If you found my answer valid, and  it solves your

question

please use the '

Select as Best

option

Log In to Answer

Associated Tags

pac

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

10/17/2024

at

06:58 AM

Misspelled  "Zscaler" Footer in Communication Emails

ozanogur

235

235 Views

3 Likes

1 Comment

1/28/2025

at

09:23 AM

Direct certain countries to different SE for a URL

danbro185

491

491 Views

1 Like

5 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Direct certain countries to different SE for a URL
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000RmsoX0AR/aws-sign-in-page-block-only-for-gmail-email-id","lastmod":"2025-02-10T07:24:49.000Z","id":"0D5PJ00000RmsoX0AR"} -->
## AWS sign in page block only for Gmail  email ID

- Source: https://community.zscaler.com/s/question/0D5PJ00000RmsoX0AR/aws-sign-in-page-block-only-for-gmail-email-id
- Type: Q&A
- Last activity: 2025-02-10T07:24:49.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

AvinashKashid

(Customer) asked a question.

January 29, 2025 at 7:29 PM

AWS sign in page block only for Gmail  email ID

AWS sign in page block only for Gmail email ID;

Any one have any possible solution please let me know the details.

ZIA - URL Filtering

1 answer

207 views

Jainil_G

(Employee)

a year ago

Hello @Avinash Kashid​ , We have the Tenant Restriction feature (

https://help.zscaler.com/zia/adding-tenant-profiles?referer=admin.zscalerthree.net#AWS

), which can help enforce restrictions on AWS accounts — for example, to allow specific Account IDs and block the rest. However, I am unsure if it is possible to enforce restrictions based on Email IDs.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,909

5909 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

888

888 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

AWS sign in page block only for Gmail  email ID
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000S6zaC0AR/comparison-with-palo-alto-prisma","lastmod":"2025-02-10T07:28:26.000Z","id":"0D5PJ00000S6zaC0AR"} -->
## Comparison with Palo Alto Prisma

- Source: https://community.zscaler.com/s/question/0D5PJ00000S6zaC0AR/comparison-with-palo-alto-prisma
- Type: Q&A
- Last activity: 2025-02-10T07:28:26.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Cloud Sandbox

Kostas

(Partner) asked a question.

February 4, 2025 at 12:26 PM

Comparison with Palo Alto Prisma

Hi everyone,

is there any comparison document betweend ZIA and Palo Alto Prisma,

Thakns,

Regards,

Kosta

ZIA - Cloud Sandbox

1 answer

343 views

Jainil_G

(Employee)

a year ago

Hello @Kosta Skulic​ , You can check -

https://www.zscaler.com/zscaler-vs-palo-alto-networks

, if it helps you with your requirement

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

1/21/2020

at

01:31 PM

Does Zscaler Scan Email Traffic?

ZIA - Cloud Sandbox

Omar

1,228

1228 Views

1 Like

3 Comments

1/2/2020

at

12:25 PM

Prevent Malicious VPN Connection

ZIA - Cloud Sandbox

Omar

1,197

1197 Views

0 Likes

8 Comments

10/2/2017

at

04:47 AM

Alerting positive 'allow and scan'

ZIA - Cloud Sandbox

Lmay

430

430 Views

1 Like

2 Comments

9/11/2017

at

09:37 PM

How do I determine which of the files that were submitted to Cloud Sandbox were found to be malicious?

ZIA - Cloud Sandbox

awl

527

527 Views

0 Likes

1 Comment

9/11/2017

at

09:35 PM

What is the recommended “best practice? Cloud Sandbox rule policy?

ZIA - Cloud Sandbox

awl

588

588 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Comparison with Palo Alto Prisma
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000SXO6p0AH/zscaler-health-check","lastmod":"2025-02-11T10:18:41.000Z","id":"0D5PJ00000SXO6p0AH"} -->
## Zscaler Health Check

- Source: https://community.zscaler.com/s/question/0D5PJ00000SXO6p0AH/zscaler-health-check
- Type: Q&A
- Last activity: 2025-02-11T10:18:41.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

omar.hosny

(Partner) asked a question.

February 11, 2025 at 7:39 AM

Zscaler Health Check

Hello Zscalers,

I have a health check for one of our customers, they have ZIA & ZPA.

What key areas I should be checking with them during my session, I don't want to miss critical things.

Also, if there is specific questionnaire or procedures I can follow.

Thanks in advance.

1 answer

375 views

Osho_Dwivedi

(Employee)

a year ago

The Primary Goal of the Health Check is to ensure that the pillars of the platform are configured to leading practices for each of the following areas when it comes to ZIA and ZPA, it can involve many procedures/tasks as below (not limited to):

1) Authentication & Identity Configuration : Verify SAML and SCIM are configured aligning with the recommendations. Check for management of users on IdP is done via group assignments (dynamic or static) validate any non-corp / guest user rules are in alignment with Organizational policy. In case of ZPA check if the SCIM for policy is used.

2) Traffic Forwarding : Ensure appropriate configurations related to forwarding traffic using ZCC,GRE/IPSec, PAC, CC/BC. Check PAC file length and clean-up. Validate HA configurations and it's periodic testing is done. Also make sure that the Version management on the ZCC is addressed. For ZPA, App Connectors placement, management and capacity planning (active session vs limits, AC Scaling and load distribution), Server group - Connector group mapping is optimal.

3) Application Configurations : Validate that the segmentation approach is scalable and if can be consolidated into segments for better management and so that the customer doesn't hit limits. Verify application discovery trends and suggest the correct way to segment apps accordingly.

3) Policy Configuration : Review Web Security (URL/CAC), ATP, Malware, Firewall /DNS and SSL Policies  along with DLP policies if in scope, for ZPA check for Access Policies are created with Postures and adhering to the concept of least privilege. Check the Timeout policies are granularly configured for crown jewel apps, specific apps and general apps.  The client forwarding policies should also be checked in correlation to IP vs FQDN based SIPA app recommendations. Also include BA and PRA if in scope.

4) Third party Integrations : Check for NSS and any other integrations with third parties to digest information into Zscaler is configured properly and working as intended.

5) Compliance and Governance : Check for RBAC and Audit logs setup and DR configurations.

Finally, create a backup and restore point once the health check recommendations are actioned upon.

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

10/17/2024

at

06:58 AM

Misspelled  "Zscaler" Footer in Communication Emails

ozanogur

235

235 Views

3 Likes

1 Comment

1/28/2025

at

09:23 AM

Direct certain countries to different SE for a URL

danbro185

493

493 Views

1 Like

5 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Zscaler Health Check
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000SXc810AD/isolation-profile-is-missing-for-isolate-action-profile-id-and-name-are-required","lastmod":"2025-02-12T04:43:12.000Z","id":"0D5PJ00000SXc810AD"} -->
## Isolation profile is missing for isolate action. Profile id and name are required

- Source: https://community.zscaler.com/s/question/0D5PJ00000SXc810AD/isolation-profile-is-missing-for-isolate-action-profile-id-and-name-are-required
- Type: Q&A
- Last activity: 2025-02-12T04:43:12.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Browser Isolation

Danielo

(Partner) asked a question.

February 11, 2025 at 9:11 AM

Isolation profile is missing for isolate action. Profile id and name are required

Hi,

Could someone help out here, please?

I am not sure if something has changed on CBI but I am not able to create a CBI on ZIA - Cloud App Control.

When I am creating a Cloud App Control rule, I am receiving this error message "Isolation profile is missing for isolate action. Profile id and name are required".

It is weird because that happens even with rules that I had already created and working with and an Isolation Profile already created and assigned to that rule.

Any ideas?

ZIA - Browser Isolation

5 answers

466 views

Jainil_G

(Employee)

a year ago

Hello @Daniel Montilla​ The reported issue is a bug with the ID BUG-198490. You can report the instance to support, and the tentative ETA for fix availability across all clouds is February 22. In some scenarios, this issue has been observed with existing Isolation profile. However, if you create a new Isolation profile, no issues are observed. This behavior is limited to certain tenants only, hence you can verify for your tenant with new Isolation profile.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

1 like

Danielo

(Partner)

a year ago

Hi @Jainil Gajjar​

That smelled like a bug, yes. Thanks for confirming.

However, I already tried to create a new Isolation Profile and I experienced the same behaviour.

I tried again after reading your answer and same thing.

BTW, is there any link where we can see the existing ID BUGs?

Thanks for your help.

BDR

(Partner)

a year ago

Hi Jainil,

Just on the topic of bugs...  Is there a publically available page that lists all the known bugs?  It would be  useful to be able to check these things without needing to log a ticket.

Thanks

1 like

Jainil_G

(Employee)

a year ago

Hello @Daniel Montilla​ and @Barry Ralph​   We do not have any public link available for tracking ongoing bugs. However, once the bug is fixed, the details will be mentioned in the release notes. If you want updates regarding any bug or possible bug, the best way is to raise a ticket and get confirmation from the Support Team.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

1 like

Danielo

(Partner)

a year ago

Hi @Jainil Gajjar​

Given that I had opened a ticket before posting anything here, they have confirm the bug.

They told me there is no workaround atm.

Thanks for your help!!

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

No posts to show.

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Isolation profile is missing for isolate action. Profile id and name are required
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000SYyum0AD/sending-logs-to-sql","lastmod":"2025-02-11T18:46:09.000Z","id":"0D5PJ00000SYyum0AD"} -->
## Sending logs to SQL

- Source: https://community.zscaler.com/s/question/0D5PJ00000SYyum0AD/sending-logs-to-sql
- Type: Q&A
- Last activity: 2025-02-11T18:46:09.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Logging

BDR

(Partner) asked a question.

February 11, 2025 at 4:28 PM

Sending logs to SQL

Hi

Does anyone know of a way or has been able to send logs from ZIA to SQL?

I think perhaps it could be done by sending logs to syslog and then from there to SQL but this doesn't seem ideal.

Thanks

ZIA - Logging

1 answer

244 views

joemozdy

(Customer)

a year ago

So your basic way of getting logs from ZIA is through a Nanalog Streaming Service server that you set up on-prem and configure the log feed in the ZIA portal. There are some different feed outputs that you can use, some are going to be dependent on different SIEMs. The CSV, Custom, or Tab-seperated might be the most useful to you in this situation, but there is nothing there thats just like a straight SQL format. You will need to tell the NSS feed where to send the logs that come in, either by IP address or FQDN, but you will need to configure some sort of other process that will ingest these log files into an SQL server. That doesn't appear to be anything that natively works like that.

If there is a SIEM service that you use and that has integration with Zscaler, I'd definitely recommend that route. You can find the guides at

https://help.zscaler.com/zia/integrating-vm-based-nss-siems

. I struggle to see how searching logs in a straight SQL client would be either enjoyable or successful though.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

6/8/2023

at

08:51 PM

URL Reporting

ZIA - Logging

akita_down

12

12 Views

0 Likes

4 Comments

11/3/2023

at

06:08 PM

ZIA - NSS Feed Status

ZIA - Logging

Rallis

613

613 Views

0 Likes

3 Comments

2/21/2024

at

10:43 PM

Find if User is behind a VPN

ZIA - Logging

venG

542

542 Views

0 Likes

2 Comments

3/13/2025

at

01:56 AM

How to Extract Logs from Web Insight

ZIA - Logging

JSK27

666

666 Views

0 Likes

8 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Sending logs to SQL
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000SdvGe0AJ/source-ip-firewall-policy-not-triggering","lastmod":"2025-02-13T15:08:28.000Z","id":"0D5PJ00000SdvGe0AJ"} -->
## Source IP Firewall Policy not triggering

- Source: https://community.zscaler.com/s/question/0D5PJ00000SdvGe0AJ/source-ip-firewall-policy-not-triggering
- Type: Q&A
- Last activity: 2025-02-13T15:08:28.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Cloud Firewall

Gianluca.bastia

(Partner) asked a question.

February 12, 2025 at 7:10 PM

Source IP Firewall Policy not triggering

I created a policy exception set to trigger based on source IP groups. However, the policy was not being hit and instead traffic from these IPs is hitting the block policy below it.

I noticed in the logs that the "Client IP" shows as the internal IP of the device and the Client Tunnel IP shows the actual public source IP... I've always been under the impression that the FW rule is based off of the Source IP, but never seen it return the internal IP of the device.

Am I missing something?

And no - I cannot create a location for these IPs as they are large IP ranges.

ZIA - Cloud Firewall

1 answer

425 views

Top Rated Answers

Jainil_G

(Employee)

a year ago

Hello @GianlUca Bastia​  We have different scenarios for various forwarding methods:

ZCC with Z-Tunnel 1.0:

When using Z-Tunnel 1.0 with Zscaler Client Connector (ZCC), the forwarding method relies on the client’s external IP address to determine whether to allow or block communication for a URL. This is because the Zscaler proxy does not receive the client’s internal IP address in this setup, hence you can have control based on the Source Public IP.

ZCC with Z-Tunnel 2.0 (DTLS/TLS

): In Z-Tunnel 2.0 with DTLS/TLS, the forwarding method requires the client’s actual internal IP address (private) to be added to the Source IPv4 Group in order to allow or block communication. Unlike Z-Tunnel 1.0, Zscaler in this scenario receives the client’s actual internal IP address, and the decision is based on this internal IP information.

GRE/IPSec

: For GRE/IPSec, the forwarding method also requires adding the client’s internal source IP address to the Source IPv4 Group in the URL filtering policy to allow or block communication. In this setup, Zscaler has visibility into the client’s actual internal source IP address, and decisions are made based on it.

In your case, if the setup involves Scenario 2 (Z-Tunnel 2.0) or Scenario 3 (GRE/IPSec), you will need to include the actual source IP (i.e., the end-user’s private/internal IP address) in the Source IP Group configuration for your requirement.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Selected as Best

All Answers

Jainil_G

(Employee)

a year ago

Hello @GianlUca Bastia​  We have different scenarios for various forwarding methods:

ZCC with Z-Tunnel 1.0:

When using Z-Tunnel 1.0 with Zscaler Client Connector (ZCC), the forwarding method relies on the client’s external IP address to determine whether to allow or block communication for a URL. This is because the Zscaler proxy does not receive the client’s internal IP address in this setup, hence you can have control based on the Source Public IP.

ZCC with Z-Tunnel 2.0 (DTLS/TLS

): In Z-Tunnel 2.0 with DTLS/TLS, the forwarding method requires the client’s actual internal IP address (private) to be added to the Source IPv4 Group in order to allow or block communication. Unlike Z-Tunnel 1.0, Zscaler in this scenario receives the client’s actual internal IP address, and the decision is based on this internal IP information.

GRE/IPSec

: For GRE/IPSec, the forwarding method also requires adding the client’s internal source IP address to the Source IPv4 Group in the URL filtering policy to allow or block communication. In this setup, Zscaler has visibility into the client’s actual internal source IP address, and decisions are made based on it.

In your case, if the setup involves Scenario 2 (Z-Tunnel 2.0) or Scenario 3 (GRE/IPSec), you will need to include the actual source IP (i.e., the end-user’s private/internal IP address) in the Source IP Group configuration for your requirement.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Selected as Best

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2018

at

01:07 PM

Ip address as criteria in url policy

ZIA - Cloud Firewall

ram75

1,189

1189 Views

0 Likes

1 Comment

10/8/2020

at

08:41 AM

Block All access & Allow certain user or group (ZIA)

ZIA - Cloud Firewall

Sec_def_Def_sec

1,816

1816 Views

1 Like

2 Comments

12/19/2022

at

04:14 PM

URL filtering policy vs Cloud App policy control

ZIA - Cloud Firewall

Ahmed

4 Views

0 Likes

2 Comments

12/9/2022

at

09:40 PM

Apple News RSS Feed

ZIA - Cloud Firewall

Trace Woodbury-RidgeIT

2 Views

0 Likes

1 Comment

9/20/2022

at

03:20 PM

How does Zscaler Internet Access itself route the traffic to the internet, using what outgoing/next hop GW

ZIA - Cloud Firewall

tamerz

6 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Source IP Firewall Policy not triggering
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000SvVqg0AF/detect-content-without-mip-sensitivity-label-via-dlp","lastmod":"2025-02-26T06:26:53.000Z","id":"0D5PJ00000SvVqg0AF"} -->
## Detect content without MIP Sensitivity Label via DLP

- Source: https://community.zscaler.com/s/question/0D5PJ00000SvVqg0AF/detect-content-without-mip-sensitivity-label-via-dlp
- Type: Q&A
- Last activity: 2025-02-26T06:26:53.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - DLP

vaibhav_j

(Customer) asked a question.

February 18, 2025 at 12:54 PM

Detect content without MIP Sensitivity Label via DLP

Current setup:

MIP integrated with ZScaler

Two DLP Dictionaries where

1st dictionary with sensitivity label(s) without encryption (dictionary#1)

2nd dictionary with sensitivity labels(s) with encryption (dictionary#2)

Two DLP Engine where

1st engine checking dictionary#1 > 0 (any)

2nd engine checking dictionary#2 > 0 (any)

One DLP Rule where

any of above 2 dictionary match

detect all upload and download activities

Question: As you can see, i can scan and detect Microsoft sensitivity label from DLP files download and upload rule but i now i want to find which files where there is no MIP label (example : my CV downloaded from my Onedrive personal which do not have MIP label) which are downloaded/uploaded to/from specific application?

Why?: I like to understand upload/download files count for - file with label vs. file with label (with encryption) vs. file without label

ZIA - DLP

4 answers

423 views

joemozdy

(Customer)

a year ago

In dictionaries 1 and 2, how are you determining encryption? That seems like something to check with the dlp rule itself, using the protocol field.

You should be able to just duplicate your existing work, only dropping the MIPS requirement from the engine. Then copy your dlp rule and use your non-MIPS engine and place it above the MIPS rule in the ordering. You might want to consider breaking up some of these rules and engines though for clarity. That can help you see which engine is getting triggered and help give further insight into what is happening. One thing  that I can see happening depends on how the MIPS labels get applied. If that happens by some other automatic process in OneDrive/Sharepoint, you may get a lot of hits that happen at document creation/upload and continue on until whatever automatic labeling process kicks in. It's not really a false positive, but it might muddy the waters for you. You would have to sort those kinds of results out manually in some way, as the dlp rules don't have a way to exclude a cloud application from a rule criteria, except for wholesale from dlp entirely.

vaibhav_j

(Customer)

a year ago

Not to confuse with protocol, here is the clarification: i am referring MIP sensitivity labels which has encryption vs. which do not have at Microsoft Purview. i  know which microsoft label has encryption and which one not example : internal, public labels i know are labels without encryption, confidential and restricted are labels i know labels with encryption.

having two separate DPL rule where 1st one is to scan content with MIP label and 2nd one to scan like attached one - Are you referring below way?

Question: if 1st DLP rule matches, will request also get assess to further more rules down in order where there are multiple DLP rules?

joemozdy

(Customer)

a year ago

Ok, so in this case encryption is just another MIPS label. It's not specifically looking to see if a file was transmitted outside of an encrypted transmission method.

In your picture, you are just looking for any files sent to those specific cloud applications you have selected with no dlp engine being used, which will work with those other rules. I think your rule order should look like this:

1st rule uses 1st engine (checks for files that have the not-encrypted label)

2nd rule uses 2nd engine (checks for files that have the encryption label)

3rd rule uses no engine (checks for all files)

So with that rule order, anything caught by rules 1 and 2 must have either MIP label, and rule 3 would catch everything else. I would probably approach it in a way that I'm always checking for one specific a MIP label per rule. That way I can better tell what file sensitivities are being uploaded or downloaded based on what rule gets triggered.

And for your question about rule matches, it depends on both the engine criteria and the action in the rule. If the transaction matches against the first rule and is blocked, then no further rules are evaluated. If the transaction matches against the first rule and is allowed, then yes further rules could be evaluated if the transaction meets them. You want your rules to be more general at the top and more specific at the bottom. For example if two rules are active, one catching credit card numbers if 5 or more are transferred then blocking, and one catching credit cards if 10 or more are transferred and blocking, then in my rule order I need to make sure the 10 rule is above the 5 rule. Otherwise, the 5 rule would always trigger first because it is more stringent. If both the 10 rule and the 5 rule were active and the action was to allow, then someone sending 10 credit card numbers would trigger both rules no matter what order they were in. I'd make sure any rules where you aren't using a dlp engine are below those with dlp engines.

There is an Evaluate All Rules Mode, but thats something that you will need to contact your account team for.

vaibhav_j

(Customer)

a year ago

thanks for the detail!

Here has worked in my case and my DLP actions are limited to 'Allowed' (not Block):

1st rule uses 1st engine (checks for files that have the not-encrypted label)

2nd rule uses 2nd engine (checks for files that have the encryption label)

3rd rule uses no engine (checks selected files)

1 like

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/9/2023

at

05:32 PM

DLP notification via Zscaler Client Connector

ZIA - DLP

thacarvalho123

7 Views

2 Likes

2 Comments

11/20/2022

at

09:26 PM

External DLP Engine

ZIA - DLP

H.zyD.zy

4 Views

0 Likes

3 Comments

11/9/2022

at

04:51 PM

Zscaler Preventing Image Upload and Download on iPhone

ZIA - DLP

MacDanorld1

2 Views

0 Likes

3 Comments

6/20/2022

at

07:53 AM

Error during Microsoft Information Protection(MIP) Integration

ZIA - DLP

ozanogur

3 Views

0 Likes

4 Comments

1/31/2021

at

08:35 PM

Zscaler ZIA and ZPA with NetSkope CASB and DLP services

ZIA - DLP

jonathan.holt

1,337

1337 Views

1 Like

6 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Detect content without MIP Sensitivity Label via DLP
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000T21Zp0AJ/blocked-malicious-urls-end-use-notification","lastmod":"2025-03-05T05:07:09.000Z","id":"0D5PJ00000T21Zp0AJ"} -->
## Blocked Malicious URLs End Use Notification

- Source: https://community.zscaler.com/s/question/0D5PJ00000T21Zp0AJ/blocked-malicious-urls-end-use-notification
- Type: Q&A
- Last activity: 2025-03-05T05:07:09.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

User17157998465646368383

(Customer) asked a question.

February 19, 2025 at 10:49 PM

Blocked Malicious URLs End Use Notification

We have a bunch of URLs blocked in ZIA - Policy - Advanced Threat Policy - Blocked Malicious URLs. When a user accesses any of these sites, they don't get our normal end user notification "site has been blocked due to policy violation", they just get a generic, unhelpful message: "This site can't be reached. Check if there is a typo in URL.com. DNS_PROBE_FINISHED_NXDOMAIN." How can we get a useful message provided to our end users when they access a site we've specifically blocked?

ZIA - URL Filtering

10 answers

700 views

Top Rated Answers

Jainil_G

(Employee)

a year ago

Hello @Julie Johnson​ The rules you have above the default rule are specifically intended for SSL inspection and for action: Inspect, Do Not Inspect, or Block.

The reason we are making changes to the last rule is that the "Malicious Content" category where the custom added URL falls under is not available as a selection under the URL category for SSL Inspection. As a result, the default rule uses  ANY, which includes all categories, and its action is set to "Do Not Inspect."

By enabling "Show Notifications for Blocked Traffic" and selecting the "Show Notifications for ATP Blocks" option, notifications EUN will be sent for traffic blocked by ATP policies, even if SSL inspection is not performed.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Selected as Best

All Answers

Jainil_G

(Employee)

a year ago

Hello @Julie Johnson​ , You can enable ATP notification feature with the use of "Support for ATP Notification Block for SSL Inspection" option as highlighted in the

https://help.zscaler.com/zia/release-upgrade-summary-2024?applicable_category=zscaler.net&deployment_date=2024-11-15

In your case, since you have manually blocked the websites under ATP, their categorization would fall under "Malicious Content." By default, this would be governed by the "Default SSL Inspection Rule" unless you have an "Inspect All" rule placed above it. If there are no rules above the "Default SSL Inspection Rule," simply enable the "Show Notification for ATP Blocks" option would should notification.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

User17157998465646368383

(Customer)

a year ago

That is very helpful, thank you! Learning what rules/policies govern what in ZS is a challenge. I have 6 rules above the Default SSL Inspection Rule, should I enable the "Show Notifications" for each rule with "Action = Do Not Inspect" if I want the users to get useful notifications if something is blocked?

Jainil_G

(Employee)

a year ago

Hello @Julie Johnson​ The rules you have above the default rule are specifically intended for SSL inspection and for action: Inspect, Do Not Inspect, or Block.

The reason we are making changes to the last rule is that the "Malicious Content" category where the custom added URL falls under is not available as a selection under the URL category for SSL Inspection. As a result, the default rule uses  ANY, which includes all categories, and its action is set to "Do Not Inspect."

By enabling "Show Notifications for Blocked Traffic" and selecting the "Show Notifications for ATP Blocks" option, notifications EUN will be sent for traffic blocked by ATP policies, even if SSL inspection is not performed.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Selected as Best

User17157998465646368383

(Customer)

a year ago

Perfect, thank you again for the explanation!

1 like

User17157998465646368383

(Customer)

a year ago

Ok, now I have another question. I enabled the "show notifications" on the default rule but I'm still not getting a user friendly notification, just that annoying "This site can't be reached" message. Do I need to create a new End User Notification? If so, I don't see where to do that - there's no "Add new" in the EUN.

Jainil_G

(Employee)

a year ago

Hello @Julie Johnson​ Hello @Julie Johnson​ If you have enabled the option mentioned above, and you do not have "

ANY" type

inspection rule above the default rule, then you should receive the default notification as shown in the attached snapshot. The website "

www.example.com

" was added as a reference.

User17157998465646368383

(Customer)

a year ago

I do have an "ANY" type inspection rule above the default, called SSL_Catch_All that is set to Inspect traffic. This was setup by our ZS implementer.

Jainil_G

(Employee)

a year ago

Hello @Julie Johnson​ In this case as mentioned earlier, you would require to select the required all categories in Catch All Rule, and enable "Show Notifications for Blocked Traffic" and selecting the "Show Notifications for ATP Blocks" option.

User17157998465646368383

(Customer)

a year ago

Since the SSL_Catch_All rule has Action = Inspect, I do not have the "Show Notifications" options available. That appears to only be available for Action of "Do Not Inspect" or "Block"

Jainil_G

(Employee)

a year ago

Hello @Julie Johnson​ I was specifically referring to have setting like below

1) Select All Categories manually in the Catch All Rule

2) In the default rule enable "Show Notifications for ATP Blocks" option.

BR

Jainil_G

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,916

5916 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

888

888 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Blocked Malicious URLs End Use Notification
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000TBThh0AH/test-url-miscellaneous-or-unknown-category","lastmod":"2025-07-17T14:29:43.000Z","id":"0D5PJ00000TBThh0AH"} -->
## Test URL - Miscellaneous or Unknown Category

- Source: https://community.zscaler.com/s/question/0D5PJ00000TBThh0AH/test-url-miscellaneous-or-unknown-category
- Type: Q&A
- Last activity: 2025-07-17T14:29:43.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Browser Isolation

Nathan3

(Customer) asked a question.

February 21, 2025 at 10:48 PM

Test URL - Miscellaneous or Unknown Category

I'm testing browser isolation against the Miscellaneous or Unknown category, can you provide a URL or two in that category for testing?

ZIA - Browser Isolation

4 answers

1K views

Jainil_G

(Employee)

a year ago

Hello @Nathan Chu​  "Miscellaneous or Unknown" is the category assigned to sites that have not yet been classified by Zscaler. You can check any unknown website and review its categorization using the URL check tool at

https://sitereview.zscaler.com/

As an example, you can currently check "

www.fakebankstatement.co[.]uk

" for reference ( [ ] at the end is added to avoid accidental click, just enter the website removing [ ] ).

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

TheDaveCason

(Customer)

a year ago

I would like this as well.  I need to be able to quickly demonstrate a category decision with a URL for both continuous testing and for demonstrations in training and executive reviews.   I would like something similar to this:

Symantec Sitereview

https://sitereview.symantec.com/#/category-test

Nathan3

(Customer)

a year ago

Thank you. I was unable to use your example since the site did not resolve and trigger the policy. I was able to get a sample of newly registered domains from another site and use those URLs for testing.

Jainil_G

(Employee)

a year ago

Hello @Nathan Chu​ , Thanks for the update. I am not sure, why it didn't resolve at your side, as it is getting open, and isolated at my side. Anyway, you have already verified with another website, and it's sorted now.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

No posts to show.

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Test URL - Miscellaneous or Unknown Category
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000TK6Kb0AL/dlp-policy-best-practices","lastmod":"2025-08-22T07:28:04.000Z","id":"0D5PJ00000TK6Kb0AL"} -->
## DLP Policy Best practices

- Source: https://community.zscaler.com/s/question/0D5PJ00000TK6Kb0AL/dlp-policy-best-practices
- Type: Q&A
- Last activity: 2025-08-22T07:28:04.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - DLP

k.dave1

(Partner) asked a question.

February 25, 2025 at 10:54 AM

DLP Policy Best practices

Hello All,

Can Anyone suggests where should we find the best practices for configuring DLP policies in Zscaler and if possible all best practices recommended by Zscaler for configuring different policies.

ZIA - DLP

4 answers

737 views

Raj90909

(Customer)

a year ago

This would be a good starting point -

https://help.zscaler.com/zscaler-deployments-operations/data-loss-prevention-deployment-and-operations-guide

3 likes

JoeE

(Customer)

a year ago

One tip I have is that before you start creating any DLP policies, make sure you receive clear guidance from Management on what types of data they want blocked.  If it is up to you to decide what gets blocked, make sure you present that to Management and get their approval.  I've seen issues in the past where these things don't get clearly communicated/documented, and then Management comes back later asking why something isn't getting blocked.  It is nice to be able to go back to your documentation and show what everyone agreed on to be blocked.

Also, I've never seen much value in creating DLP policies for data types your company does not collect.  For example, if your company does not collect or process customer SSNs there isn't much value in creating a DLP policy to block SSNs.  Any policy you create will inevitably have some false positives that will require tuning.  So having polices that block data your company does not process will just create extra work.

And just in case you are not aware, SSL Inspection is necessary for DLP.

4 likes

Ramesh Mani

(Partner)

a year ago

Good to follow:)

Raj909

(Customer)

a year ago

This would be a good starting point -

https://help.zscaler.com/zscaler-deployments-operations/data-loss-prevention-deployment-and-operations-guide

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/9/2023

at

05:32 PM

DLP notification via Zscaler Client Connector

ZIA - DLP

thacarvalho123

7 Views

2 Likes

2 Comments

11/20/2022

at

09:26 PM

External DLP Engine

ZIA - DLP

H.zyD.zy

4 Views

0 Likes

3 Comments

11/9/2022

at

04:51 PM

Zscaler Preventing Image Upload and Download on iPhone

ZIA - DLP

MacDanorld1

2 Views

0 Likes

3 Comments

6/20/2022

at

07:53 AM

Error during Microsoft Information Protection(MIP) Integration

ZIA - DLP

ozanogur

3 Views

0 Likes

4 Comments

1/31/2021

at

08:35 PM

Zscaler ZIA and ZPA with NetSkope CASB and DLP services

ZIA - DLP

jonathan.holt

1,341

1341 Views

1 Like

6 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

DLP Policy Best practices
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000Tcg4D0AR/ms-one-click-config-disables-ssl-inspection-not-risky","lastmod":"2025-03-05T07:23:05.000Z","id":"0D5PJ00000Tcg4D0AR"} -->
## MS one click config disables ssl inspection, not risky?

- Source: https://community.zscaler.com/s/question/0D5PJ00000Tcg4D0AR/ms-one-click-config-disables-ssl-inspection-not-risky
- Type: Q&A
- Last activity: 2025-03-05T07:23:05.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - SSL Inspection

kevinm

(Customer) asked a question.

March 2, 2025 at 1:07 AM

MS one click config disables ssl inspection, not risky?

The article For Microsoft one click configuration mentions that ssl inspection will be disabled here is a screenshot of the policy.

isnt this a security risk because the attachments and urls inside emails won't be scanned anymore? what am I missing here?

I tried to search some other community posts and one post says that zs enables ssl inspection for office 365 traffic but this screenshot clearly says otherwise can somebody explain this a bit?

and this link says zs enables SSL inspection with one-click config, very confusing: https://community.zscaler.com/s/question/0D54u00009evlHTCAY/zscaler-and-office-365

ZIA - SSL Inspection

3 answers

477 views

Jainil_G

(Employee)

a year ago

Hello @Allwyn Mascarenhas​   The highlighted section of the help document is currently under review and is expected to be updated soon. To address your main question, let me provide a detailed explanation. Zscaler offers a simple one-click setting to forward O365 traffic (both web and non-web) and bypass SSL inspection (for web traffic) for these endpoints (

https://learn.microsoft.com/en-us/microsoft-365/enterprise/urls-and-ip-address-ranges?view=o365-worldwide

). However, we are not adding all the domains/IPs listed in the link above. Instead, to strike a balance between allowing traffic and maintaining security, Zscaler includes most, but not all, of the endpoints listed in the JSON file.

We have published a detailed best practices guide with all the necessary information, which is available at

https://www.zscaler.com/resources/white-papers/best-practices-for-microsoft365-and-zscaler.pdf

The important thing to keep in mind is that this approach enhances security rather than reducing it. Still, if you have specific requirements, you can always configure your own SSL Inspection bypass policies to allow such traffic.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

1 like

Maarten van den Bogaard

(Partner)

a year ago

Hi @Allwyn Mascarenhas​

It is my understanding that Zscaler, when using the One Click option, selectively inspects M365 traffic to prioritize security. This ensures that while performance is enhanced, your organization maintains a robust security posture.

See this blogpost by Siddhartha Aggarwal, it may contain the information that you are looking for:

https://community.zscaler.com/s/question/0D54u00009evlHTCAY/zscaler-and-office-365

JSK27

(Partner)

a year ago

Hello

A policy named

"Office 365 One Click"

is automatically created under

Policy > SSL Inspection

. This policy allows Zscaler to automatically bypass SSL inspection for URLs that require exceptions. If you need more granular control over Outlook or other O365 services, you must manually create an

SSL Inspect

policy.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2021

at

07:54 PM

Youtube Restricted Mode Bypass Safe Search

ZIA - SSL Inspection

JonM

8 Views

1 Like

5 Comments

11/24/2021

at

06:09 AM

Reflection time of SSL inspection exclusion settings

ZIA - SSL Inspection

Nyajima

4 Views

0 Likes

1 Comment

4/26/2024

at

02:39 PM

End User Notifcation - Submit To Security Cloud option

ZIA - SSL Inspection

citrus3118

311

311 Views

0 Likes

1 Comment

8/21/2024

at

08:06 PM

StackHawk with Zscaler SSL Inspection

ZIA - SSL Inspection

User16182582818315999266

572

572 Views

0 Likes

4 Comments

3/10/2025

at

12:22 AM

Reputation Block policy

ZIA - SSL Inspection

JM

419

419 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

MS one click config disables ssl inspection, not risky?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000Tfi5X0AR/inspect-downloads-for-nondlp","lastmod":"2025-03-04T08:34:36.000Z","id":"0D5PJ00000Tfi5X0AR"} -->
## Inspect downloads for non-DLP

- Source: https://community.zscaler.com/s/question/0D5PJ00000Tfi5X0AR/inspect-downloads-for-nondlp
- Type: Q&A
- Last activity: 2025-03-04T08:34:36.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - DLP

vaibhav_j

(Customer) asked a question.

March 3, 2025 at 3:50 PM

Inspect downloads for non-DLP

Scenario:

I can see 'inspect downloads' when 'Content Marking = Select DLP Engines' is enabled to scan content while its downloaded:

Question: is there similar option ('Inspect Downloads') while 'Content Marking = None'? (i can't see similar option)

ZIA - DLP

1 answer

269 views

Ramesh Mani

(Partner)

a year ago

Inspect downloads not needed if None chosen. I believe this inspect is only for validate if there is any engine / dictionaries mentioned the DLP rule.

2 likes

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/9/2023

at

05:32 PM

DLP notification via Zscaler Client Connector

ZIA - DLP

thacarvalho123

7 Views

2 Likes

2 Comments

11/20/2022

at

09:26 PM

External DLP Engine

ZIA - DLP

H.zyD.zy

4 Views

0 Likes

3 Comments

11/9/2022

at

04:51 PM

Zscaler Preventing Image Upload and Download on iPhone

ZIA - DLP

MacDanorld1

2 Views

0 Likes

3 Comments

6/20/2022

at

07:53 AM

Error during Microsoft Information Protection(MIP) Integration

ZIA - DLP

ozanogur

3 Views

0 Likes

4 Comments

1/31/2021

at

08:35 PM

Zscaler ZIA and ZPA with NetSkope CASB and DLP services

ZIA - DLP

jonathan.holt

1,343

1343 Views

1 Like

6 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Inspect downloads for non-DLP
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000U2Jtz0AF/reputation-block-policy","lastmod":"2025-03-23T22:39:34.000Z","id":"0D5PJ00000U2Jtz0AF"} -->
## Reputation Block policy

- Source: https://community.zscaler.com/s/question/0D5PJ00000U2Jtz0AF/reputation-block-policy
- Type: Q&A
- Last activity: 2025-03-23T22:39:34.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - SSL Inspection

JM

(Partner) asked a question.

March 10, 2025 at 12:22 AM

Reputation Block policy

Hi,

Can anyone explain this to me? Chrome is currently blocking the chrome web store for Hoxx VPN, for which I received a

"Reputation block outbound request: anonymization site"

page which is good. However, MS Edge is allowing us to access the microsoft edge.microsoft. Below is what I've done so far:

Created a URL Category for microsoftedge.microsoft/addons - BLK-VPN

SSL inspection (inspected) for BLK-VPN

This adjustment works, but I want the same reputation-policy to be received on MS Edge.

What could be the policy to adjust to receive the reputation-policy reason?

ZIA - SSL Inspection

2 answers

419 views

Top Rated Answers

Jainil_G

(Employee)

a year ago

Hello @Joanna Villamor​ , The Hoxx VPN classification under the Edge Store and Chrome Store seems to differ, which is likely the reason you are seeing different results. Typically, for anonymization sites—i.e., destinations classified with the reputation of being anonymizers—the classification should be consistent across both platforms. You can raise a request with Support, who can coordinate with the ThreatLab Team to verify the proper classification as an ideal solution.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Selected as Best

All Answers

JM

(Partner)

a year ago

Chrome is allowing to access the web store itself but not the Hoxx VPN specifically.

Jainil_G

(Employee)

a year ago

Hello @Joanna Villamor​ , The Hoxx VPN classification under the Edge Store and Chrome Store seems to differ, which is likely the reason you are seeing different results. Typically, for anonymization sites—i.e., destinations classified with the reputation of being anonymizers—the classification should be consistent across both platforms. You can raise a request with Support, who can coordinate with the ThreatLab Team to verify the proper classification as an ideal solution.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Selected as Best

Log In to Answer

Associated Tags

url-filtering

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2021

at

07:54 PM

Youtube Restricted Mode Bypass Safe Search

ZIA - SSL Inspection

JonM

8 Views

1 Like

5 Comments

11/24/2021

at

06:09 AM

Reflection time of SSL inspection exclusion settings

ZIA - SSL Inspection

Nyajima

4 Views

0 Likes

1 Comment

4/26/2024

at

02:39 PM

End User Notifcation - Submit To Security Cloud option

ZIA - SSL Inspection

citrus3118

311

311 Views

0 Likes

1 Comment

8/21/2024

at

08:06 PM

StackHawk with Zscaler SSL Inspection

ZIA - SSL Inspection

User16182582818315999266

570

570 Views

0 Likes

4 Comments

3/10/2025

at

12:22 AM

Reputation Block policy

ZIA - SSL Inspection

JM

418

418 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Reputation Block policy
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000UFG3q0AH/egress-zscaler-node-change","lastmod":"2025-03-13T17:03:12.000Z","id":"0D5PJ00000UFG3q0AH"} -->
## Egress Zscaler Node change

- Source: https://community.zscaler.com/s/question/0D5PJ00000UFG3q0AH/egress-zscaler-node-change
- Type: Q&A
- Last activity: 2025-03-13T17:03:12.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

Anu_12

(Customer) asked a question.

March 12, 2025 at 10:01 PM

Egress Zscaler Node change

Can anyone help if there is a solution for a Road Warrior travels to a country (ex: Germany) but originally from United states get a US based Zscaler node as the egress for browsing ?

Can this be achieved through PAC file variables matching a specific country but return to a different datacenter outside that country ?

Basically we want the user to experience the same websites or resources as he is in US from Germany.

ZIA - Forwarding

2 answers

225 views

Maarten van den Bogaard

(Partner)

a year ago

Hi @Anurag Thokala​ ,

You can achieve this by adding a return proxy rule to the PAC file that checks the country they are in. You could use the example below where if the user is located in Germany it will direct traffic to the NYC3 datacenter proxy hostname.

/* Germany to US NYC3 DC*/

var country = "${COUNTRY}";

if (shExpMatch(country,"Germany")) {

/* User is in Germany*/

return "PROXY

nyc3.sme.zscaler.net

:80;DIRECT";

You can create a separate PAC file with this rule and link that to an app profile that you scope only to the users based in the US,

You can find the list proxy hostnames here:

Zscaler Config

. Don't forget to select the appropriate Zscaler cloud at the top of the page.

If a

post

solves your

question,

please use the '

Select as Best

option

1 like

Ramesh Mani

(Partner)

a year ago

Wherever you go Zscaler policy would be user/group specific.  So the service edge in US or Germany will enforce same security / filtering policy for the user.

More info, when you specify US node in PAC file will add additional latency and slowness.

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

492

492 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Egress Zscaler Node change
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000UFqbN0AT/how-to-extract-logs-from-web-insight","lastmod":"2025-03-14T07:44:09.000Z","id":"0D5PJ00000UFqbN0AT"} -->
## How to Extract Logs from Web Insight

- Source: https://community.zscaler.com/s/question/0D5PJ00000UFqbN0AT/how-to-extract-logs-from-web-insight
- Type: Q&A
- Last activity: 2025-03-14T07:44:09.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Logging

JSK27

(Partner) asked a question.

March 13, 2025 at 1:56 AM

How to Extract Logs from Web Insight

Hello

I know that Web Insight allows log extraction in CSV format, but this method limits extraction to 100,000 rows at a time. I need a way to extract logs for a specific time period or an entire day's logs at once. Is there a solution?

ZIA - Logging

8 answers

667 views

Top Rated Answers

Ramesh Mani

(Partner)

a year ago

Please see this,

Selected as Best

1 like

All Answers

Maarten van den Bogaard

(Partner)

a year ago

Hi @강준수 Kang​ ,

Have you considered streaming the web logs to a SIEM? This may be the most feasible, if not the only way to work around these kinds of limitations.

You can find information on it here:

Understanding Nanolog Streaming Service (NSS) | Zscaler

Hopefully this helps you.

If a

post

solves your

question,

please use the '

Select as Best

option

1 like

JSK27

(Partner)

a year ago

Hi @Maarten van den Bogaard​

Thanks for reply

I am considering the NSS configuration. I am thinking about implementing NSS redundancy, and I am wondering if there is a recommended configuration for this. Also, I am curious about how to set up the redundancy configuration.

1 like

Ramesh Mani

(Partner)

a year ago

There are Active / Active and Active/cold stand by options.  You may look at this -

https://help.zscaler.com/zia/understanding-nanolog-streaming-service

1 like

JSK27

(Partner)

a year ago

It seems like the redundancy structure isn't mentioned in the URL you shared. Could you possibly capture where it is mentioned?

Ramesh Mani

(Partner)

a year ago

Please see this,

Selected as Best

1 like

Ramesh Mani

(Partner)

a year ago

You can reach out support team for logs capture from backend for high log volumes and upto 6 months logs.

1 like

JSK27

(Partner)

a year ago

Hi @Ramesh Mani​

Thanks for reply

Is there no way for the administrator to extract it directly? Do I necessarily have to contact the backend team for this?​

Ramesh Mani

(Partner)

a year ago

From the zia console we can download , cap it with log size and download multiple copies

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

6/8/2023

at

08:51 PM

URL Reporting

ZIA - Logging

akita_down

12

12 Views

0 Likes

4 Comments

11/3/2023

at

06:08 PM

ZIA - NSS Feed Status

ZIA - Logging

Rallis

613

613 Views

0 Likes

3 Comments

2/21/2024

at

10:43 PM

Find if User is behind a VPN

ZIA - Logging

venG

542

542 Views

0 Likes

2 Comments

3/13/2025

at

01:56 AM

How to Extract Logs from Web Insight

ZIA - Logging

JSK27

666

666 Views

0 Likes

8 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

How to Extract Logs from Web Insight
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000UGibj0AD/upload-block-rule-doesnt-work","lastmod":"2025-03-20T07:04:52.000Z","id":"0D5PJ00000UGibj0AD"} -->
## Upload block rule doesnt work

- Source: https://community.zscaler.com/s/question/0D5PJ00000UGibj0AD/upload-block-rule-doesnt-work
- Type: Q&A
- Last activity: 2025-03-20T07:04:52.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

NicoMeijni

(Customer) asked a question.

March 13, 2025 at 10:53 AM

Upload block rule doesnt work

Hello everyone,

Im trying to block the upload on Whatsapp.

I have created a Cloud App Control policy to block upload and its working fine on the whatsapp computer application.

But the upload is still working on whatsapp WEB...

I have created a URL Category with ".whatsapp.com" and i have created a File Type Control policy who block upload on my URL category but its still not working, i can upload file on whatsapp website :(

Can you help me please :)

ZIA - URL Filtering

2 answers

758 views

Ramesh Mani

(Partner)

a year ago

Try these URL,

Key Domains:

WhatsApp uses specific domains for file uploads and downloads, including

mmg.whatsapp.net

mms.whatsapp.net

pps.whatsapp.net

dyn.web.whatsapp.com

, and

mmg-fna.whatsapp.net

Other URLs:

You might also need to block URLs like .

cdn.whatsapp.net/mms/

, .

fna.whatsapp.net

, and media.fkul13-1.fna.whatsapp.net.

Also ensure SSL inspection is enabled ?

1 like

Jainil_G

(Employee)

a year ago

Hello @Nicolas Meijni​ WhatsApp has end-to-end encryption, hence, the below steps are recommended to achieve your requirement:

Step 1

. Create a Custom URL Category: In the ZIA Admin Portal, navigate to Administration > URL Categories > Click Add to create a new custom URL category. In the Custom URLs field, add the below URLs:

--

cdn.whatsapp.net/mms/document/

cdn.whatsapp.net/mms/image/

cdn.whatsapp.net/mms/sticker/

fna.whatsapp.net/mms/document/

fna.whatsapp.net/mms/image/

fna.whatsapp.net/mms/sticker/

dit.whatsapp.net/mms/document/

dit.whatsapp.net/mms/image/

dit.whatsapp.net/mms/sticker/

mmg.whatsapp.net/mms/document/

mmg.whatsapp.net/mms/image/

mmg.whatsapp.net/mms/sticker/

pps.whatsapp.net/mms/document/

pps.whatsapp.net/mms/image/

pps.whatsapp.net/mms/ppt/

pps.whatsapp.net/mms/sticker/

web.whatsapp.com/img/

cdn.whatsapp.net/mms/gif/

to block .gif images

--

In the Custom Keywords field, add the below:

--

mms-type=document

mms-type=image

mms-type=sticker

mms-type=video

mode=manual&mms-type=document&

mms-type=document&

mms-type=gif

--> to block .gif related urls

--

Step 2.

Create a URL Filtering Rule to block the URL category created for WhatsApp file transfer: Navigate to Policy > URL & Cloud App Control > Add URL Filtering Rule. Select the custom URL category you created in the first step and choose the action as block.

Step 3.

Additionally to have a granular search and block-only file transfer, create a Cloud App Control Policy to allow WhatsApp Web: Navigate to Policy > URL & Cloud App Control > Add Cloud App Control Policy. Select the WhatsApp Web under the Cloud application and choose the action as allowed.

Make sure you have allowed the Cascade to URL filtering feature in the rule.

The above configuration will block file transfer on WhatsApp while allowing text messages

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,913

5913 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

888

888 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Upload block rule doesnt work
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000UOv5x0AD/zidentity-admin-vs-service-entitlements","lastmod":"2025-08-22T07:28:06.000Z","id":"0D5PJ00000UOv5x0AD"} -->
## ZIdentity - Admin vs Service Entitlements

- Source: https://community.zscaler.com/s/question/0D5PJ00000UOv5x0AD/zidentity-admin-vs-service-entitlements
- Type: Q&A
- Last activity: 2025-08-22T07:28:06.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Authentication

SriNarasimha

(Customer) asked a question.

March 15, 2025 at 5:34 PM

ZIdentity - Admin vs Service Entitlements

Hi Experts,

I've been asked to work on the ZIdentity and doing the prep work now. My understanding is that ZIdentity is a unified portal to login to multiple Zscaler services which is a Management plane and not affecting end-users from the data plane perspective. Is my understanding correct..?

Noticed that ZIdentity have Admin and Entitlements. As the name suggest 'Admin Entitlements' is for making Administrative tasks to the each service while 'Service Entitlements' is used to assign users to a Zscaler service as "end users".

If ZIdentity is a management portal which simplfies access to various Zscaler portal, why we need to add Zscaler service to the end users..?

If no users/groups are assigned to the Zscaler services (ZIA/ZPA) in ZIdentity portal, will they no longer be able to enrol and authenticate with these services,.?

Can someone please clarify as this is very confusing..

Thank you.

ZIA - Authentication

7 answers

580 views

Top Rated Answers

Gianluca.bastia

(Partner)

a year ago

ZIdentity will allow you to manage both admins and users.  The users' portion is still not GA for all tenants, at least not for previous customers.

Service Entitlements will be used to assign users to ZIA / ZPA / ZDX services that they are able to login via the Client connector. It replaces the "Entitlements" menu option from the mobile portal.

Selected as Best

1 like

All Answers

Gianluca.bastia

(Partner)

a year ago

ZIdentity will allow you to manage both admins and users.  The users' portion is still not GA for all tenants, at least not for previous customers.

Service Entitlements will be used to assign users to ZIA / ZPA / ZDX services that they are able to login via the Client connector. It replaces the "Entitlements" menu option from the mobile portal.

Selected as Best

1 like

avshch

(Customer)

a year ago

Is this available for ZscalerGov clouds?

Raj90909

(Customer)

a year ago

Not sure if you have a beta tenant setup, but you could request one to carry out any pilot testing.  I was hesitant at first but it is not user impacting.

We recently migrated out beta tenant to Z-Identity and so far no issues for managing our Administrative Entitlements.

1 like

Urs Schoeler

(Customer)

a year ago

We moved last Week as well to ZIdentity with Admins and the only Issue so far have been errors with the SSO to the Zscaler Support Portal. When you make the Switch make sure to move from SAML to OIDC as well as that way you will skip an additional migration later on.

SriNarasimha

(Customer)

a year ago

Hi @GianlUca Bastia​ @Raj Khatri​

I apologize for the delayed response and greatly appreciate your suggestions.

I am in the process of transitioning various existing ZIA services into ZIdentity under 'Administrative Entitlements' through SCIM Provisioning.

Given that any user or role assignments made in ZIdentity will be reflected in the administrators for ZIA/ZPA, which role will take precedence for the respective user? For instance, if

user123@domain.com

has been assigned RW privileges locally in ZIA, while the same user or user-group has been assigned RO in ZIdentity for ZIA, how will this be managed?

I would appreciate your insights on migrating users from the current tenants to ZIdentity without causing any disruption to the administrators. Thank you.

Gianluca.bastia

(Partner)

a year ago

Once you migrate to ZIdentity, the local administrator roles will no longer be available as everything is administered from ZIdentity entitlements

1 like

Raj909

(Customer)

a year ago

Not sure if you have a beta tenant setup, but you could request one to carry out any pilot testing.  I was hesitant at first but it is not user impacting.

We recently migrated out beta tenant to Z-Identity and so far no issues for managing our Administrative Entitlements.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/15/2022

at

12:39 PM

Onprem Active directory integrate with Zscaler cloud ZIA

ZIA - Authentication

aravindhan.m

3 Views

0 Likes

3 Comments

7/11/2022

at

07:26 AM

Zscaler IDP authentication issue

ZIA - Authentication

aravindhan.m

4 Views

0 Likes

2 Comments

5/27/2022

at

01:49 PM

Zscaler ZIA O365 Apps not authenticating

ZIA - Authentication

abmaclean

4 Views

0 Likes

6 Comments

10/4/2021

at

10:43 PM

Automatic de-provisioning using SCIM with Azure AD

ZIA - Authentication

Paul_Wineberg

4 Views

0 Likes

4 Comments

6/27/2020

at

03:01 PM

Zscaler public preview

ZIA - Authentication

Muhammad

364

364 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

ZIdentity - Admin vs Service Entitlements
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000UUvNE0A1/i-cannot-use-vscode-live-share","lastmod":"2025-04-07T13:44:10.000Z","id":"0D5PJ00000UUvNE0A1"} -->
## I cannot use VSCode Live Share.

- Source: https://community.zscaler.com/s/question/0D5PJ00000UUvNE0A1/i-cannot-use-vscode-live-share
- Type: Q&A
- Last activity: 2025-04-07T13:44:10.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

JSK27

(Partner) asked a question.

March 18, 2025 at 6:01 AM

I cannot use VSCode Live Share.

Hello

The Live Share extension in VSCode does not work when Zscaler is enabled. Even after bypassing all policies through SSL Bypass, it still doesn't function. I also made exceptions for the related URLs that appear in the Web Log from PAC, but it still doesn't work. It only doesn't work on the installed VSCode. When using it on the web, it works fine without making any exceptions. How do you all use VSCode Live Share? Can anyone help with how to use VSCode Live Share with Zscaler Agent Tunnel 2.0?

ZIA - URL Filtering

11 answers

704 views

Ramesh Mani

(Partner)

a year ago

Have you verified logs in web insights if any blocks ?

JSK27

(Partner)

a year ago

Even with Zscaler enabled, Liveshare isn’t working. I’ve allowed SSL Bypass and permitted all policies, but it still doesn’t function. There are no block logs either.

Jainil_G

(Employee)

a year ago

Hello @강준수 Kang​ , You will need to check the Web Insight logs to confirm whether any required URLs are being blocked. If none of the URLs are being blocked, it is possible that SSL Inspection is causing the issue. To quickly verify this, create a temporary source IP-based rule for a single device with an SSL Inspection bypass. Test the VSCode Live Share  and review the results. If it resolves the issue, you may need to bypass additional URLs.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

1 like

JSK27

(Partner)

a year ago

Even with Zscaler enabled, Liveshare isn’t working. I’ve allowed SSL Bypass and permitted all policies, but it still doesn’t function. There are no block logs either.

Jainil_G

(Employee)

a year ago

Hello @강준수 Kang I just want to double-check if you have allowed an SSL bypass via URL/FQDN. If there is any URL that has been missed, it could possibly be the cause of the issue. Did you try the method mentioned earlier, i.e., creating a temporary source IP-based rule for a single device with an SSL inspection bypass?

If the issue still persists, I would suggest checking once using the process-based bypass approach -

https://help.zscaler.com/zscaler-client-connector/adding-process-based-applications-bypass-traffic

or using the IP exclusion/inclusion option -

https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#IP

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

JSK27

(Partner)

a year ago

Hello

Full Traffic SSL Bypass > Liveshare Not Working

Full Traffic Tunnel 2.0 Bypass > Liveshare Not Working

Process Bypass > Not tested, but VSCode should not be treated as an exception.

Running Liveshare does not create another process (runs as Visual Studio Code process)

JSK27

(Partner)

a year ago

It only works fine when used with TWLP (Tunnel 1.0 also has issues)

Jainil_G

(Employee)

a year ago

Hello @강준수 Kang​  With a process-based bypass, we would be able to implement an additional check by bypassing the process completely from forwarding traffic to Zscaler. You can review this option.

Additionally, if this is only working with TWLP, I am unsure whether you have any VPN( Full Or Pseudo Spilt Tunnel) working with ZCC. If yes, then TWLP is required as a forwarding mode.

Thanks,

Jainil

JSK27

(Partner)

a year ago

Hello @Jainil Gajjar​

If we use a process-based bypass, wouldn’t Zscaler be unable to apply policies to that process?

Also, there is no VPN used with ZCC. I only tested using the Zscaler Agent, and the same issue with Liveshare occurred on other environments and PCs as well.

Thanks,

Kang

Jainil_G

(Employee)

a year ago

Hello @강준수 Kang​ If you bypass traffic using a process-based approach, all traffic for that process will be bypassed from Zscaler  -

https://help.zscaler.com/zscaler-client-connector/about-application-bypass-info

Thanks,

Jainil

More answers

10 of 11

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,909

5909 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

888

888 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

I cannot use VSCode Live Share.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000UrfNi0AJ/zscaler-enat-feature","lastmod":"2025-07-23T14:50:50.000Z","id":"0D5PJ00000UrfNi0AJ"} -->
## Zscaler ENAT Feature

- Source: https://community.zscaler.com/s/question/0D5PJ00000UrfNi0AJ/zscaler-enat-feature
- Type: Q&A
- Last activity: 2025-07-23T14:50:50.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

avshch

(Customer) asked a question.

March 25, 2025 at 1:24 AM

Zscaler ENAT Feature

Hello,

Does Zscaler have a feature called ENAT?

If so any ref documentation?

Thanks,

ZIA - Forwarding

6 answers

2.94K views

Gianluca.bastia

(Partner)

a year ago

It's on the roadmap... We'll probably get more info at ZenithLive

avshch

(Customer)

a year ago

I heard it is in GA for commercial tenants already, but no documentation on

zscaler.com

avshch

(Customer)

a year ago

It sounds like  ENAT is a couple  of public IP  addresses allocated by Zscaler to a specific customer for traffic egress.

Maarten van den Bogaard

(Partner)

a year ago

That would definitely be a nice feature to have for certain scenarios. Fingers crossed that we get more info at Zenith Live this year.

Osho_Dwivedi

(Employee)

a year ago

This is currently offered with the below:

https://help.zscaler.com/zia/understanding-zscaler-managed-dedicated-ip

Keep tuned in for ZL2025 for the Dedicated Egress IP Feature!

Raj909

(Customer)

a year ago

Geolocalization has been released and your account team can put in a provisioning request to get it enabled for your tenant.

https://help.zscaler.com/zia/understanding-geolocalization-ip

https://www.zscaler.com/blogs/product-insights/zscaler-introduces-geolocalization

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

492

492 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Zscaler ENAT Feature
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000UwbxD0AR/safe-search-bypass-for-youtube","lastmod":"2025-04-03T09:12:53.000Z","id":"0D5PJ00000UwbxD0AR"} -->
## Safe Search Bypass for YouTube

- Source: https://community.zscaler.com/s/question/0D5PJ00000UwbxD0AR/safe-search-bypass-for-youtube
- Type: Q&A
- Last activity: 2025-04-03T09:12:53.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

Veit

(Partner) asked a question.

March 26, 2025 at 8:13 AM

Safe Search Bypass for YouTube

Hi, is there a way to exclude YouTube from SafeSearch without creating an SSL bypass?

ZIA - URL Filtering

4 answers

782 views

Jainil_G

and

User16661218782334559576

like this.

Top Rated Answers

Jainil_G

(Employee)

a year ago

Hello @Veit Heni​ Your requirement can be fulfilled either through NAT Control or DNS Control.

Google offer fixed DNS and FQDN which enforce safe search. We can utilized these domains to ensure that traffic from the sources is always redirected to these enforced domains.

Youtube : Youtube provides 2 domains for enforcement

Moderate :

restrictmoderate.youtube.com

Strict :

restrict.youtube.com

In Zscaler, the NAT Control feature is utilized to force traffic to land on specific domains. Using a NAT Control Policy, the traffic directed to domains for YouTube services is subjected to Destination NAT, pointing to these fixed domains.

Under Policy -> Firewall Control (NAT Control Policy), create rules for destination domains accessed for search pages and streaming services.

Destination Domains

www.youtube.com

m.youtube.com

youtubei.googleapis.com

www.youtube-nocookie.com

youtube.googleapis.com

DNAT IP or FQDN

Two restrict modes:

Moderate :

restrictmoderate.youtube.com

Full :

restrict.youtube.com

You can keep Global Safe Search policy disable, and even have SSL Inspection enabled

The same solution can also be implemented using DNS redirects. If there has a centralized DNS architecture, a CNAME record for the domains can be created to resolve the sites to the safe/restricted domains.

Example :

A CNAME record for all the youtube domains to

restrict.youtube.com

will ensure that the IP resolved for youtube domains will be redirected to

restrict.youtube.com

IP address and the user will receive the search page with Safe Search always turned on.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Selected as Best

3 likes

All Answers

Ramesh Mani

(Partner)

a year ago

This is global config, I dont see options to exclude only one

Jainil_G

(Employee)

a year ago

Hello @Veit Heni​ Your requirement can be fulfilled either through NAT Control or DNS Control.

Google offer fixed DNS and FQDN which enforce safe search. We can utilized these domains to ensure that traffic from the sources is always redirected to these enforced domains.

Youtube : Youtube provides 2 domains for enforcement

Moderate :

restrictmoderate.youtube.com

Strict :

restrict.youtube.com

In Zscaler, the NAT Control feature is utilized to force traffic to land on specific domains. Using a NAT Control Policy, the traffic directed to domains for YouTube services is subjected to Destination NAT, pointing to these fixed domains.

Under Policy -> Firewall Control (NAT Control Policy), create rules for destination domains accessed for search pages and streaming services.

Destination Domains

www.youtube.com

m.youtube.com

youtubei.googleapis.com

www.youtube-nocookie.com

youtube.googleapis.com

DNAT IP or FQDN

Two restrict modes:

Moderate :

restrictmoderate.youtube.com

Full :

restrict.youtube.com

You can keep Global Safe Search policy disable, and even have SSL Inspection enabled

The same solution can also be implemented using DNS redirects. If there has a centralized DNS architecture, a CNAME record for the domains can be created to resolve the sites to the safe/restricted domains.

Example :

A CNAME record for all the youtube domains to

restrict.youtube.com

will ensure that the IP resolved for youtube domains will be redirected to

restrict.youtube.com

IP address and the user will receive the search page with Safe Search always turned on.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Selected as Best

3 likes

Maarten van den Bogaard

(Partner)

a year ago

This is very helpful, thank you for the detailed explanation.

1 like

Veit

(Partner)

a year ago

Thank you very much for explaining !

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,906

5906 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

885

885 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Safe Search Bypass for YouTube
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000V2bTN0AZ/office365-bypass","lastmod":"2025-08-22T07:28:06.000Z","id":"0D5PJ00000V2bTN0AZ"} -->
## Office365 Bypass

- Source: https://community.zscaler.com/s/question/0D5PJ00000V2bTN0AZ/office365-bypass
- Type: Q&A
- Last activity: 2025-08-22T07:28:06.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

Jk1789tw

(Customer) asked a question.

March 27, 2025 at 4:35 PM

Office365 Bypass

In regard to Office365 traffic, Microsoft has indicated to us to bypass ZIA using the publicly accessible list of Office 365 URLS and IP addresses. The two reasons for their recommendation is for performance and the implementation of conditional access policies. I am trying to get a consensus as to how everyone else is handling Office365 traffic? Are you using OneClick only, are you bypassing using the list provided by Microsoft or any other configuration? To note, we utilize ZCC on all desktops/workstations and not a GRE tunnel.

ZIA - URL Filtering

3 answers

1.49K views

Raj90909

(Customer)

a year ago

Most customers use the One Click option as this recommendation between Microsoft & Zscaler.  This was enabled on our tenant from the onset, without any issues.  However, depending on your organization's policy regarding SSL inspection, you can inspect specific traffic.

https://www.zscaler.com/products-and-solutions/zscaler-and-microsoft-365

https://help.zscaler.com/zia/about-microsoft-one-click-options

1 like

Jainil_G

(Employee)

a year ago

Hello @Joe Oneill​   To add, we have an excellent whitepaper with a detailed technical explanation about Best Practices for Implementing Access to Microsoft 365 with Zscaler, which should be helpful. Let us know if you have any additional questions.

https://www.zscaler.com/resources/white-papers/best-practices-for-microsoft365-and-zscaler.pdf

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Raj909

(Customer)

a year ago

Most customers use the One Click option as this recommendation between Microsoft & Zscaler.  This was enabled on our tenant from the onset, without any issues.  However, depending on your organization's policy regarding SSL inspection, you can inspect specific traffic.

https://www.zscaler.com/products-and-solutions/zscaler-and-microsoft-365

https://help.zscaler.com/zia/about-microsoft-one-click-options

Log In to Answer

Associated Tags

office365

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,932

5932 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

896

896 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Office365 Bypass
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000VQ1iK0AT/patient-0-alert-visibility-in-streamed-zia-logs","lastmod":"2025-04-07T06:04:34.000Z","id":"0D5PJ00000VQ1iK0AT"} -->
## patient 0 alert visibility in streamed zia logs

- Source: https://community.zscaler.com/s/question/0D5PJ00000VQ1iK0AT/patient-0-alert-visibility-in-streamed-zia-logs
- Type: Q&A
- Last activity: 2025-04-07T06:04:34.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Cloud Sandbox

Veit

(Partner) asked a question.

April 3, 2025 at 7:18 AM

patient 0 alert visibility in streamed zia logs

Hi everyone,

I am seeking your insights on how Patient Zero alerts appear within the streamed ZIA log data. Our goal is to create filters in Sentinel that will trigger a process when a Patient Zero alert occurs, without relying on email notifications.

Is there a way to identify Patient Zero alerts in the logs? If so, how do this logs look like?

Kind regards

Veit

ZIA - Cloud Sandbox

1 answer

300 views

Top Rated Answers

Jainil_G

(Employee)

a year ago

Hello @Veit Heni​  Currently, the only way to trigger notifications is via email Alerts-

https://help.zscaler.com/zia/configuring-patient-0-alert

. For NSS Stream, we have an enhancement request (ER-3338 i.e Sandbox Activity Logging to SIEM) that is expected to be available in the future.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Selected as Best

1 like

All Answers

Jainil_G

(Employee)

a year ago

Hello @Veit Heni​  Currently, the only way to trigger notifications is via email Alerts-

https://help.zscaler.com/zia/configuring-patient-0-alert

. For NSS Stream, we have an enhancement request (ER-3338 i.e Sandbox Activity Logging to SIEM) that is expected to be available in the future.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Selected as Best

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

1/21/2020

at

01:31 PM

Does Zscaler Scan Email Traffic?

ZIA - Cloud Sandbox

Omar

1,220

1220 Views

1 Like

3 Comments

1/2/2020

at

12:25 PM

Prevent Malicious VPN Connection

ZIA - Cloud Sandbox

Omar

1,192

1192 Views

0 Likes

8 Comments

10/2/2017

at

04:47 AM

Alerting positive 'allow and scan'

ZIA - Cloud Sandbox

Lmay

412

412 Views

1 Like

2 Comments

9/11/2017

at

09:37 PM

How do I determine which of the files that were submitted to Cloud Sandbox were found to be malicious?

ZIA - Cloud Sandbox

awl

524

524 Views

0 Likes

1 Comment

9/11/2017

at

09:35 PM

What is the recommended “best practice? Cloud Sandbox rule policy?

ZIA - Cloud Sandbox

awl

583

583 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

patient 0 alert visibility in streamed zia logs
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000VbAQ80AN/cloud-app-control-google-drive-subactions","lastmod":"2025-04-10T08:15:13.000Z","id":"0D5PJ00000VbAQ80AN"} -->
## Cloud App Control - Google Drive subactions

- Source: https://community.zscaler.com/s/question/0D5PJ00000VbAQ80AN/cloud-app-control-google-drive-subactions
- Type: Q&A
- Last activity: 2025-04-10T08:15:13.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - CASB

Danielo

(Partner) asked a question.

April 6, 2025 at 5:57 PM

Cloud App Control - Google Drive subactions

Hi guys,

Is this working for you?

We want to be able to allow Google Drive and control some sub-actions (upload, download, creation, edit, etc.)

The rule is catching fine the action of Allow, Caution, Block or Isolate.

The problem is when I define ALLOW, none of the sub-actions (Uploading, Creating, Deleting, etc.) are doing something.

I am using the browser for that.

ZCC ZTunnel 2.0

SSL Inspection enabled.

Am I missing something?

Again: Block, Caution or Isolate are working fine. It is the sub-actions of ALLOW the ones are not working properly.

ZIA - CASB

7 answers

501 views

Top Rated Answers

Danielo

(Partner)

a year ago

Hi everyone,

I have been doing some tests and if you just scan all SSL traffic, it works.

If you only scan the SSL content for an specific Cloud App, like Google Drive, that might not be enough.

You might have to create some Custom URL categories with URLs that GDrive uses and then creating an SSL Inspection policy with that User-Defined category and that would work also.

If you scan all the SSL content, apart from One-Click for MS and other exceptions you could need, it will work.

Selected as Best

All Answers

Osho_Dwivedi

(Employee)

a year ago

This is not expected, could you check in the logs if your transactions are hitting the right policy?

Danielo

(Partner)

a year ago

Hi @Osho Dwivedi​

transactions are hitting the right policy.

If I change the action and select: BLOCK or ISOLATE, that action is executed.

It is when I select ALLOW, that some sub-actions are not working.

I have done some tests and now, seing the logs, I see that downloads are blocked as expected.

However I am able to do the rest of the things: Upload, Share, Rename, Create, etc. Although in the policy I selected "Block" for all the sub-actions.

mahantr

(Customer)

a year ago

I have seen this issue with other cloud apps and have opened case. Typically, Zscaler will identify the issue with the cloud app and resolve it in next cloud update/maintentance. I suspect the issue is that these websites are constantly changing and Zscaler can't always keep up with the individual cloud app controls.

Danielo

(Partner)

a year ago

Hi everyone,

I have been doing some tests and if you just scan all SSL traffic, it works.

If you only scan the SSL content for an specific Cloud App, like Google Drive, that might not be enough.

You might have to create some Custom URL categories with URLs that GDrive uses and then creating an SSL Inspection policy with that User-Defined category and that would work also.

If you scan all the SSL content, apart from One-Click for MS and other exceptions you could need, it will work.

Selected as Best

Danielo

(Partner)

a year ago

thanks all for your answers, btw!

Osho_Dwivedi

(Employee)

a year ago

Possible Cause:

When you upload anything on Google Drive, it can be observed in the header trace that the upload happens on the domain "

clients6.google.com

". By default the URL "

clients6.google.com

" is not part of the Google Drive cloud app, and does not get inspected.

Consider that adding the URL "

clients6.google.com

" under the Google Drive cloud app and the user configures a policy for the Google Drive upload block.

When the user accesses something else and not Google Drive, the URL can be "

clients6.google.com

" as it is a generic URL used for multiple purposes by Google. In that case, it will trigger the Google Drive cloud app policy block even though it was not a Google Drive upload.

The only way to know that it is a Google Drive cloud app upload is by viewing the content of the HTTP packet, which you will be able to see only if SSL inspection is enabled.

In short: The Fully Qualified Domain Name (FQDN) "

clients6.google.com

" should get inspected to apply an upload block for the cloud application: Google Drive.

You can confirm this with the following:

Access the URL "

https://clients6.google.com/

" on the browser and have a look at the certificate on the page. It should be of Zscaler.

If not, follow the steps in the "Solution" section to block upload to Google Drive.

Solution: (As already figured out by you)

Create a custom URL category with

clients6.google.com

, and add the same in the SSL inspection rule to inspect traffic for this FQDN "

clients6.google.com

".

If this response

solves your

question

please use the

Select as Best

option

1 like

Danielo

(Partner)

a year ago

Hi @Osho Dwivedi​ ,

Thanks for this. That is exactly what we saw on the logs. That's why either scanning all SSL content or that specific URL used by GDrive, situation should be solved. We tried both things.

Again, thanks for sharing your knowledge.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

4/6/2025

at

05:57 PM

Cloud App Control - Google Drive subactions

ZIA - CASB

Danielo

500

500 Views

0 Likes

7 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Cloud App Control - Google Drive subactions
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000Vj51u0AB/zscaler-cloud-app-browser-updatetool","lastmod":"2025-04-25T11:19:37.000Z","id":"0D5PJ00000Vj51u0AB"} -->
## Zscaler Cloud app "Browser updatetool"

- Source: https://community.zscaler.com/s/question/0D5PJ00000Vj51u0AB/zscaler-cloud-app-browser-updatetool
- Type: Q&A
- Last activity: 2025-04-25T11:19:37.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

avshch

(Customer) asked a question.

April 8, 2025 at 6:40 PM

Zscaler Cloud app "Browser updatetool"

Hello,

What is the definition of "Browser updatetool" with Zscaler cloud apps?

Is there a way to look which url's Zsacler cloud apps include?

Thanks,

ZIA - URL Filtering

3 answers

314 views

Osho_Dwivedi

(Employee)

a year ago

Hi @Alex Shchukin​

I looked into the logs and filtered it with the Cloud App "Browser Updatetool"

I found that

https://browser-update.org/

is the URL associated with this Cloud App. It is  a service initiative by websites to inform users to update their web browser.

If there are anymore URLs within the cloud app you can derive them from your company's log by filtering with the Cloud App.

Zscaler doesn't publish the URLs associated with a cloud app explicitly given it is Zscaler proprietary information , but if you're using the platform you'll be able to derive a subset of URLs at least which are used within your environment this way or more efficiently via a query on your SIEM (given you're streaming the logs via NSS).

If this response

solves your

question

please use the

Select as Best

option

avshch

(Customer)

a year ago

@Osho Dwivedi​ I don't know what is so proprietary in cloud apps definitions that Zscaler can not disclosed the information to customers. We have on-going audit and not enable to provide any info on Cloud apps defined on Zscaler platform.

Osho_Dwivedi

(Employee)

a year ago

Hi @Alex Shchukin​ I understand your concern, if you deem fit, please talk with your accounts team to take this up with the responsible team within Zscaler.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,900

5900 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

881

881 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Zscaler Cloud app "Browser updatetool"
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000VkDnK0AV/pac-file-to-bypass-a-website","lastmod":"2025-04-09T09:03:57.000Z","id":"0D5PJ00000VkDnK0AV"} -->
## PAC file to bypass a website

- Source: https://community.zscaler.com/s/question/0D5PJ00000VkDnK0AV/pac-file-to-bypass-a-website
- Type: Q&A
- Last activity: 2025-04-09T09:03:57.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

JM

(Partner) asked a question.

April 9, 2025 at 12:48 AM

PAC file to bypass a website

Can anyone enlighten me with this.

We have created a Block policy for a certain website, and it is location-based. So, we created a location group to easily determine which locations this policy should work. The users can receive a block policy when they have ZCC installed.

Here's the problem:

We have devices using PAC files, and we have already updated the PAC configuration by adding this section:

/*====== Section IV-A ==== Bypass Websites ===================*/

if (shExpMatch(host, "*.[URL].com") || shExpMatch(url, "*[URL].com*") ||

shExpMatch(host, "*.[URL].com") || shExpMatch(url, "*[URL].com*")) {

return "DIRECT";

The policy we made to block these URLs is also working on the devices even though we added the above script to bypass it.

The PC's location is already excluded from the Block location group

We confirmed that there is no GPO policy blocking the proxy-related settings.

Can anyone help me with what I should do next? Should I:

Create a firewall control policy in ZIA?

Or should my PAC script be added more script?

ZIA - URL Filtering

2 answers

396 views

Ramesh Mani

(Partner)

a year ago

shExpMatch is a regular expression match function. RegEx considers a dot as matching any character - so "

abc.com

" would match “

abc.com

" as well as “abcxxcom" and abcex1com".

You should consider dnsDomainis(Host,“

abc.com

") as a function, which is more efficient, but also more specific.

User1654064317289579473

(Customer)

a year ago

@Ramesh M​

As far as I know shExpMatch(Sheel Expression Match) is not a Regular

Expression.It

uses shell-style wildcards, not regex.

If you can clarify more about this ?

@Joanna Villamor​

The URL is already bypassed through PAC which means you want to allow the user to access the URL ?  Please check this diagram which will clarify your answer.

Log In to Answer

Associated Tags

pac

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,916

5916 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

888

888 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

PAC file to bypass a website
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000VmvmD0AR/cloud-app-control-block-uploads-to-personal-google-drive-allow-uploads-to-corporate-cloud-instance","lastmod":"2026-01-22T03:23:33.000Z","id":"0D5PJ00000VmvmD0AR"} -->
## Cloud App Control - Block uploads to Personal Google Drive, Allow Uploads to Corporate Cloud Instance

- Source: https://community.zscaler.com/s/question/0D5PJ00000VmvmD0AR/cloud-app-control-block-uploads-to-personal-google-drive-allow-uploads-to-corporate-cloud-instance
- Type: Q&A
- Last activity: 2026-01-22T03:23:33.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Policy Rules

User16621555906218443402

(Customer) asked a question.

April 9, 2025 at 6:13 PM

Cloud App Control - Block uploads to Personal Google Drive, Allow Uploads to Corporate Cloud Instance

Is anyone else using Cloud Application instances for Google Drive combined with Cloud Application Control Rules. We have defined a Cloud Application instance for our Google Drive instance and created two Cloud Application Control rules. The first rule (in rule order) is to allow uploads to our Google Drive cloud instance with the Cloud instance selected in the rule, The second rule (in rule order) is block file uploads to Google Drive.

This rules appears to work when uploading files to our cloud instance and does block files when attempting to upload files to a personal Google Drive account. However, we are seeing an issue where the blocking rule sometimes prevents saving changes to a file in our Google Drive instance.

Policy Rules

1 answer

569 views

Danielo

(Partner)

a year ago

Hi,

what does the logs say?

I am not sure if this would be an option but have you considered "isolating" the session for the non-corporate GDrive tenants instead of blocking uploads?

1 like

Log In to Answer

Associated Tags

casb

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

No posts to show.

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Cloud App Control - Block uploads to Personal Google Drive, Allow Uploads to Corporate Cloud Instance
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000Vohvm0AB/custom-url-category-related","lastmod":"2025-04-28T07:25:48.000Z","id":"0D5PJ00000Vohvm0AB"} -->
## Custom URL Category related

- Source: https://community.zscaler.com/s/question/0D5PJ00000Vohvm0AB/custom-url-category-related
- Type: Q&A
- Last activity: 2025-04-28T07:25:48.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

rajesh

(Partner) asked a question.

April 10, 2025 at 5:24 AM

Custom URL Category related

Hi, faced a weird scenario with regards to custom URL category. A customer has .whatsapp.net and .whatsapp.com under a custom category (SSL_Bypass) added as custom URL (not under Retaining to Parent category) which means any FQDNs under these domains should be part of this custom category alone.

What we saw is that .cdn.whatsapp.net and .fna.whatsapp.net were reported under Online Chat. resulting in failure of SSL Bypass.

Once I added these subdomains as well under the SSL_Bypass, then the SSL bypass worked seamlessly as well as the reporting of logs under the custom category. Please let me know why this behaviour in the custom category.

ZIA - URL Filtering

5 answers

343 views

ozanogur

(Partner)

a year ago

I think because the .

cdn.whatsapp.net

and .

fna.whatsapp.net

are more specific than

whatsapp.net

. And the matching done through the more specific entry/category.

Ramesh Mani

(Partner)

a year ago

see if this helps:

https://community.zscaler.com/zenith/s/question/0D54u00009evmiHCAQ/controlling-whatsapp

JSK27

(Partner)

a year ago

Hello

Please check if .

cdn.whatsapp.net

and .

fna.whatsapp.net

are defined under a different category. If they are assigned to another category, subdomains other than the specified URL will fall under that category instead.

Jainil_G

(Employee)

a year ago

Hello @Rajeshkumar Chemalli​ The more specific logic as per below will come

Let's start with our first example. CAT1 contains only .

yahoo.com

in the Custom URLs field and CAT2 contains only

mail.yahoo.com

in the Custom URLs field. If the user tries to access

mail.yahoo.com

, only the policy for CAT2 will be enforced.

Even if no policy matches for the CAT2 category, the policy for CAT1 or the parent predefined category will not be evaluated. So, this transaction will be implicitly allowed unless there's an explicit block any rule at the bottom.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

Ramesh Mani

(Partner)

a year ago

I think retain parent category will change this behavior right?

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,927

5927 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

895

895 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Custom URL Category related
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000WQEt80AH/google-maps-doesnt-load-properly-with-zscaler-on","lastmod":"2025-05-06T07:34:25.000Z","id":"0D5PJ00000WQEt80AH"} -->
## Google maps doesn't load properly with Zscaler ON

- Source: https://community.zscaler.com/s/question/0D5PJ00000WQEt80AH/google-maps-doesnt-load-properly-with-zscaler-on
- Type: Q&A
- Last activity: 2025-05-06T07:34:25.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Authentication

RKV

(Partner) asked a question.

Edited April 22, 2025 at 1:16 PM

Google maps doesn't load properly with Zscaler ON

Hi Everyone,

We have a several user groups attempting to access a website and redirect to the Find page in the Website and the Google Map doesn't load properly.

Problematic URL : www.arlobeef.com, once we search for this URL it opens as expected but when we click on Find Us option in the Website, it opens a Google map without any data.

Working Setup: When we browse the URL "https://australiangrocerywholesalers--agwdev.sandbox.my.site.com/arlo1/findus" it does work without any issues and the google map does show us.

With the URL "arlobeef.com" we have experiencing the issue, and this URL is a customized version of the above working URL.

It happens to both office and remote user irrespective of location. Looking forward to your response.

HAR traces indicates as below.

Working one : When browse directly to the below URL

------------------------

Request URL:

https://australiangrocerywholesalers.my.site.com/agw/apex/GoogleMapArloVF

Request Method:GET

Status Code: 200 OK

Remote Address:165.225.226.XX : 108xx

Non-working one:

----------------

Request URL:

https://australiangrocerywholesalers.my.site.com/agw/apex/GoogleMapArloVF

Request Method: GET

Status Code:307 Temporary Redirect

Remote Address::80

The Zscaler diagnostics logs and packet captures doesn't indicate any issues.

The problematic URL works fine without Zscaler and access the Find us section of Google maps without any issues.

Application owner made clear that there is no issues with plugins or any issue of ? Would like to know where this is blocking at the Zscaler level or the application components issue ?

Non-working section of Google maps when accessed through Arlobeef.com URL with Zscaler

Working section of Google maps when accessed with "

australiangrocerywholesalers.my.site.com"

ZIA - Authentication

10 answers

509 views

Ramesh Mani

(Partner)

a year ago

Go to Web insights and see if any blocks. Certain URLs required to SSL bypass in order to work properly.

Look at below URLs,

https://support.google.com/chrome/a/answer/6334001?hl=en&ref_topic=3504941#allowlist&zippy=%2Chostname-allowlist-for-chromeos-devices-using-android-apps-google-play-store

RKV

(Partner)

a year ago

Hi @Ramesh Mani​

Thanks for your response. Web insights in Zscaler doesn't show any blocks for the URL and its all allowed.

The map works pretty well with the actual URL :

https://australiangrocerywholesalers--agwdev.sandbox.my.site.com/arlo1/findus

But with the custom URL it doesn't when the traffic is steered through Zscaler (Attached error message)

https://www.arlobeef.com/findus

The same URL works fine with Map when accessed directly without Zscaler.

RKV

(Partner)

a year ago

I have missed to add the below details.

The issue currently appears to be for the users working from home and PAC file configured.

RKV

(Partner)

a year ago

posted a file.

RKV

(Partner)

a year ago

The above error message indicates the Zscaler voilates the content security policy. So i am unsure whether i need to do ssl and auth bypass for the URL though there is no blocks oberved in the web singhts.

Can somone please advise on this if you have encountered similar experience. Thanks.

Jainil_G

(Employee)

a year ago

Hello @Virupaksha Rajapur​ ​ As highlighted in the reported issue, a specific Google Map is not working when accessed via

http://www.arlobeef.com/

but works directly through a specific link. I believe this could be due to a link being blocked or not working with SSL inspection when accessing the website via Arlobeef. This issue may not occur when accessing the link directly without SSL in place.

Here are my suggestions:

If possible, please check using a different browser in the same scenario.

Check the HAR file, and see, if there is any specific URL getting block or timeout with 4xx or 5xx error.

If the issue persists, try SSL bypassing the Arlobeef and Australiangrocerywholesalers URLs using the

"Do Not Inspect" action and "Bypass Other Policies"

to observe further.

Based on the outcomes from the suggestions above, we can determine the next course of action.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

RKV

(Partner)

a year ago

Hi @Jainil Gajjar​

Thanks for your feedback , really appreciate.

Regarding the HAR file , we dont see anything getting blocked but there is a difference in the redirection between working URL and non-working as per the below.

Working one : When browse directly to the below URL

------------------------

Request URL:

https://australiangrocerywholesalers.my.site.com/agw/apex/GoogleMapArloVF

Request Method:GET

Status Code: 200 OK

Remote Address:165.225.226.XX : 108xx

Non-working one, when we browse through the URL:

Alrobeef.com

----------------

Request URL:

https://australiangrocerywholesalers.my.site.com/agw/apex/GoogleMapArloVF

Request Method: GET

Status Code:307 Temporary Redirect

Remote Address::80

In the web sights logs of Zscaler there is no details of the traffic being blocked or any traces which indicates its blocking.

Regarding the SSL bypass , yes thats the last option i am thinking of. Thanks.

1 like

User16521879494873617772

(Customer)

a year ago

I think the solution to this is doing an authentication bypass since you're seeing a 307 code. Here's Zscaler's documentation when to use auth bypass.

https://help.zscaler.com/zia/exempting-urls-cloud-apps-authentication

RKV

(Partner)

a year ago

Hi @Vang Thao​

Thanks for your insightful feedback, really appreciate.

In between we have observed below from the user.

1) User in office --> Goes through internal app-proxy(Private VZEN) -> It works

2) User in remote/ off-network -> Goes through external pac file hosted in Zscaler -> Doesn't work.

Any idea whether we need to engage Zscaler TAC to whiteliest the application from their end ?

Jainil_G

(Employee)

a year ago

Hello @Virupaksha Rajapur​ , If you whitelist the application/website, it would create a complete bypass from Zscaler, resulting in a direct connection. Ideally, bypassing/whitelisting is not required unless there is a specific reason for it. In your case, the application is working directly and not via a specific website. Therefore, in terms of accessibility, there doesn’t seem to be an issue. However, it appears that some feature or traffic flow might be causing the problem.

Previously, I requested testing with a user by bypassing SSL inspection. Did you get a chance to perform the test?

Thanks,

Jainil

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/15/2022

at

12:39 PM

Onprem Active directory integrate with Zscaler cloud ZIA

ZIA - Authentication

aravindhan.m

3 Views

0 Likes

3 Comments

7/11/2022

at

07:26 AM

Zscaler IDP authentication issue

ZIA - Authentication

aravindhan.m

4 Views

0 Likes

2 Comments

5/27/2022

at

01:49 PM

Zscaler ZIA O365 Apps not authenticating

ZIA - Authentication

abmaclean

4 Views

0 Likes

6 Comments

10/4/2021

at

10:43 PM

Automatic de-provisioning using SCIM with Azure AD

ZIA - Authentication

Paul_Wineberg

4 Views

0 Likes

4 Comments

6/27/2020

at

03:01 PM

Zscaler public preview

ZIA - Authentication

Muhammad

364

364 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Google maps doesn't load properly with Zscaler ON
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000Wo0EA0AZ/mac-strict-enforcement-not-blocking-safari","lastmod":"2025-05-08T05:43:05.000Z","id":"0D5PJ00000Wo0EA0AZ"} -->
## MAC: Strict Enforcement not blocking Safari

- Source: https://community.zscaler.com/s/question/0D5PJ00000Wo0EA0AZ/mac-strict-enforcement-not-blocking-safari
- Type: Q&A
- Last activity: 2025-05-08T05:43:05.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Browser Isolation

MichaelOlvo

(Customer) asked a question.

April 29, 2025 at 1:23 PM

MAC: Strict Enforcement not blocking Safari

I have deployed Zscaler 4.3 on my Mac using JAMF. Strict enforcement is on and it is blocking other browsers, and Microsoft Teams when not logged in to Zscaler but Safari still works. How can I block Safari as well?

I'm using this as the Socket plist:

general

allowTrafficToDefaultGateway

detectAltInterfaceTraffic

inbound

untrustednet

ips

lanlocal

action

block

outbound

untrustednet

ips

lanlocal

action

block

I tried other ways such as below

outbound

untrustednet

apps

com.apple.Safari

action

block

ZIA - Browser Isolation

3 answers

307 views

Jainil_G

(Employee)

a year ago

Hello @Michael Zolby​  After reviewing the Safari issue related to strict enforcement, I found that this behavior is expected when using "Tunnel with Local Proxy" (TWLP) as the traffic-forwarding method.

This is a known issue on Safari's end, and while we have reported it to Apple, we have not received a response. Based on similar cases, it seems there have been no recent updates or resolutions from Apple regarding the reported tickets.

If you are currently using TWLP, we recommend testing the issue by switching to Tunnel 1.0 as a workaround.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

MichaelOlvo

(Customer)

a year ago

Signed into Zscaler the UI says Tunnel Version: v.10

Can you tell me how the plist should look for the blocking?

Jainil_G

(Employee)

a year ago

Hello @Michael Zolby​ For TWLP, you need to check in the forwarding profile, if it is set as TWLP or T1.0 -

https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector?referer=mobileadmin.zscalerthree.net#tunnel-with-local-proxy

.  If it is TWLP, I would suggest to check once with T1.0. I don't believe we have a plist file available for reference.

Thanks,

Jainil

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

No posts to show.

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

MAC: Strict Enforcement not blocking Safari
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000WusMJ0AZ/zscaler-customer-migration-to-zero-trust-architecture","lastmod":"2025-05-14T13:54:02.000Z","id":"0D5PJ00000WusMJ0AZ"} -->
## Zscaler customer migration to Zero Trust Architecture

- Source: https://community.zscaler.com/s/question/0D5PJ00000WusMJ0AZ/zscaler-customer-migration-to-zero-trust-architecture
- Type: Q&A
- Last activity: 2025-05-14T13:54:02.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Authentication

avshch

(Customer) asked a question.

April 30, 2025 at 8:12 PM

Zscaler customer migration to Zero Trust Architecture

Hello,

Does Zscaler have a document for Zscaler generic customer migration path to Zero Trust Architecture?

Thanks,

ZIA - Authentication

11 answers

290 views

ian.wharton

(Partner)

a year ago

Hi

I haven't seen the kind of document that you're looking for. Probably because different customers have different requirements.

Part of my job is to look at what a migration path would look like for a customer and what phases/priorities are required and although there are similarities, a lot depends on what you currently have in place.

In terms of high level Zscaler products though, you would be looking at ZIA, ZPA, Branch and cloud connector and possibly Airgap.

Hope that helps

Ian

avshch

(Customer)

a year ago

@Ian Wharton​ Any idea on when when AirGap would become Fedramp complaint?

ian.wharton

(Partner)

a year ago

Sorry, I don't know at the moment. I'm meeting with an airgap specialist in a few days though and I'll ask the question.

Ramesh Mani

(Partner)

a year ago

For Zero trust migration jiurney, you can statrt with ZIA , ZPA, cyber threat protection and data protection modules in ZIA, also cloud connector for cloud forwarding ad branch connector deployments are best way to upgrade.

Jainil_G

(Employee)

a year ago

Hello @Alex Shchukin​  I think you can check this link:

https://www.zscaler.com/resources/videos/understanding-zero-trust-architecture

. It includes various eBooks, resources, and even webinars. Hope this helps as a starting point.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

avshch

(Customer)

a year ago

@Jainil Gajjar​ Thank you for the referenced link, however it does not help to understand which Zscaler offering are in line with ZTNA. For example Zscaler offers a firewall as part of ZIA. Is that firewall offering in line with ZTNA? if traditional VPN's are not ZTNA complaint, does this a customer need to use ZCC with Branch Connector to forward the traffic instead of IPSEC?

How does Airgap integrates Zscaler ZIA to accomplish ZTNA?

Thanks,

ian.wharton

(Partner)

a year ago

Hi Alex

ZTNA is all about imposing a policy on all traffic irrespective of where it originates. The opposite of 'plug and play'.

ZCC deals with authenticated traffic wherever the client is to access Internet (ZIA) or private apps (ZPA) as you know.

For unauthenticated traffic such as IoT devices etc. you can tunnel (IPSec or GRE) into Zscaler and use the ZIA firewall as you suggest (You could use a standard firewall or Branch Connector for that) but that doesn't address communication on site between devices on a LAN for example. To get segmentation on a LAN and control traffic , the traditional (legacy) way has been to use VLANs, VRFs, ACLs, Private VLANs etc. It's possible but very messy and complex.

Airgap is novel approach to LAN segmentation and applying policy without all the LAN complexity.

So... Unauthenticated traffic within a LAN - Airgap

Unauthenticated traffic (IoT/OT etc) to the Internet or Private apps - IPSec or GRE or Branch Connector tunnel to ZS and ZIA firewall etc.

Corporate devices /authenticated traffic from anywhere - ZCC

I've simplified it a bit and much depends on your particular environment and requirements but I hope that helps.

Regards

Ian

avshch

(Customer)

a year ago

@Ian Wharton​ Thank you for the clarification.

Would the following use case be compliant with ZTNA?

For authenticated  users ZCC for VDI (Citrix on-prem)  combined with  workloads advanced and Branch Connector.  The traffic would be filtering with traditional Zscaler ZIA firewall.

Unauthenticated traffic from other systems (without ability to install ZCC for VDI) within a LAN - Airgap.

Ramesh Mani

(Partner)

a year ago

For authenticated users ZCC for VDI (Citrix on-prem) combined with workloads advanced and Branch Connector. The traffic would be filtering with traditional Zscaler ZIA firewall.

Yes, this architecture can frame as Zero trust architecture.

----------

Unauthenticated traffic from other systems (without ability to install ZCC for VDI) within a LAN - Airgap.

This can be still zero trust using  IP based or device based architecture.

In this architecture, you may need a device to route to Zscaler for policy control  or a secured segmented architecture. If our journey is towards Zscaler architecture think of how to place components to achieve all traffic in consideration.

ian.wharton

(Partner)

a year ago

I would want to know more about your environment before I would say that it's 'compliant'.

The question that I always ask my customers is - 'Consider each of your networks/VLANs/subnets etc. Now imagine that I connect my laptop to this network (with whatever threats/tools it might contain). What private resources could I potentially get access to either within the LAN or across the WAN? What devices (IoT/OT etc) could I access, what applications/servers etc? If the answer is 'None' then you're in a good place.

Bear in mind that if you're on a MPLS or SD-WAN network, I would probably be able to access resources at remote sites and this would need addressing.

Fundamentally, this is what ZS mean when they talk about treating your network like it's a Starbucks. (personally I use the term 'Costafication of your network' but other coffee shops are just as relevant). If you would be reasonably relaxed when a member of the public wandered into your site and plugged some device into it,  then you're probably compliant.

I hope that helps

Ian

More answers

10 of 11

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/15/2022

at

12:39 PM

Onprem Active directory integrate with Zscaler cloud ZIA

ZIA - Authentication

aravindhan.m

3 Views

0 Likes

3 Comments

7/11/2022

at

07:26 AM

Zscaler IDP authentication issue

ZIA - Authentication

aravindhan.m

4 Views

0 Likes

2 Comments

5/27/2022

at

01:49 PM

Zscaler ZIA O365 Apps not authenticating

ZIA - Authentication

abmaclean

4 Views

0 Likes

6 Comments

10/4/2021

at

10:43 PM

Automatic de-provisioning using SCIM with Azure AD

ZIA - Authentication

Paul_Wineberg

4 Views

0 Likes

4 Comments

6/27/2020

at

03:01 PM

Zscaler public preview

ZIA - Authentication

Muhammad

363

363 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Zscaler customer migration to Zero Trust Architecture
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000WxCqu0AF/using-pac-files-for-nonpersistent-vdi","lastmod":"2025-05-08T02:45:40.000Z","id":"0D5PJ00000WxCqu0AF"} -->
## Using PAC files for Non-Persistent VDI

- Source: https://community.zscaler.com/s/question/0D5PJ00000WxCqu0AF/using-pac-files-for-nonpersistent-vdi
- Type: Q&A
- Last activity: 2025-05-08T02:45:40.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

rfletcher

(Customer) asked a question.

May 1, 2025 at 11:27 AM

Using PAC files for Non-Persistent VDI

We're currently transitioning to PAC files for routing traffic from our non-persistent VDI environment. Traffic is successfully routed to the ZIA cloud and authenticated, but the client IP address in the logs is showing as our external IP address. Does anyone know how we can get the true internal client IP to show in the logs?

ZIA - Forwarding

1 answer

252 views

Ramesh Mani

(Partner)

a year ago

In PAC based architecture egress IP will shown to users as the PAC servers are publically hosted.

Try sending VDI through GRE / IPSec tunnel .

Leverage,

https://help.zscaler.com/cloud-branch-connector/what-zscaler-client-connector-vdi

Log In to Answer

Associated Tags

pac

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

490

490 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Using PAC files for Non-Persistent VDI
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000XXEVq0AP/tips-and-tricks-for-zscaler-log-analysis","lastmod":"2025-06-20T21:46:31.000Z","id":"0D5PJ00000XXEVq0AP"} -->
## Tips and Tricks for Zscaler Log Analysis

- Source: https://community.zscaler.com/s/question/0D5PJ00000XXEVq0AP/tips-and-tricks-for-zscaler-log-analysis
- Type: Q&A
- Last activity: 2025-06-20T21:46:31.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Logging

NetSecNick

(Customer) asked a question.

May 12, 2025 at 6:29 PM

Tips and Tricks for Zscaler Log Analysis

What are your Tips and Tricks for analyzing logs?

HAR Files, ZCC Logs, and PCAPs what do you do to identify problems with the traffic and help you resolve issues?

ZIA - Logging

4 answers

526 views

NetSecNick

likes this.

NetSecNick

(Customer)

a year ago

I will start the conversation.

I used to open HAR files in Notepad and search for "Error" or "Block".

Now I use a company built GPT product to analyze the data for me and tell me if there are any errors and if so, what websites they are associated with.

2 likes

Ben_Garrison

(Employee)

a year ago

AI has taken over. I used to have to write a parser in python to do this. Fitting that Python is now the leading ML language.

Good topic

1 like

Ramesh Mani

(Partner)

a year ago

This is good, reduce the time by automated investigation. :)

Danielo

(Partner)

a year ago

Hi @White Nick​

Apart from the logs on the client, when it comes to ZIA policies, I am also using the logs already provided by the solution itself through Analytics.

That helps me a lot to see if the policy matches or not, if something is missing in that policy (maybe an URL, a parameter...).

And I'd say that for ZPA, using the logs is very useful as well to see what App Connector is used, what Servide Error, the codes return after the request, etc.

1 like

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

6/8/2023

at

08:51 PM

URL Reporting

ZIA - Logging

akita_down

12

12 Views

0 Likes

4 Comments

11/3/2023

at

06:08 PM

ZIA - NSS Feed Status

ZIA - Logging

Rallis

614

614 Views

0 Likes

3 Comments

2/21/2024

at

10:43 PM

Find if User is behind a VPN

ZIA - Logging

venG

542

542 Views

0 Likes

2 Comments

3/13/2025

at

01:56 AM

How to Extract Logs from Web Insight

ZIA - Logging

JSK27

667

667 Views

0 Likes

8 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Tips and Tricks for Zscaler Log Analysis
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000XXdcc0AD/configure-site2site-vpn-between-cisco-firepower-thread-defense-ftd-and-zscaler","lastmod":"2025-12-02T21:46:49.000Z","id":"0D5PJ00000XXdcc0AD"} -->
## configure site-2-site vpn between Cisco Firepower Thread Defense (FTD) and zscaler

- Source: https://community.zscaler.com/s/question/0D5PJ00000XXdcc0AD/configure-site2site-vpn-between-cisco-firepower-thread-defense-ftd-and-zscaler
- Type: Q&A
- Last activity: 2025-12-02T21:46:49.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Cloud Firewall

zscaler_cisco

(Customer) asked a question.

May 12, 2025 at 8:07 PM

configure site-2-site vpn between Cisco Firepower Thread Defense (FTD) and zscaler

I am trying to configure site-2-site VPN between Cisco FTD and Zscaler in order to support ZIA Private Serivce Edge. According to zscaler, it on supports this: https://help.zscaler.com/zia/configuring-ipsec-vpn-tunnel

I only see ASA-55xx is listed here. I do NOT see Cisco Firepower Thread Defense (FTD) which is a much newer NG firewall than ASA. Is this supported by Zscaler? Anyone has documentation to prove it?

ZIA - Cloud Firewall

6 answers

910 views

Ramesh Mani

(Partner)

a year ago

IPSEC from Firepower supported,

zscaler_cisco

(Customer)

a year ago

What about this in the documentation?

Configure multiple IPSec VPN tunnels with the same public source IP address using NAT-T and source port randomization with IKEv2 for all the configured tunnels.

I assume the VPN Peer must be behind a NAT device that perform Port Address Translation (PAT) for this to work, right?  It just can not be done on the VPN device such as the FTD itself, right?

zscaler_cisco

(Customer)

a year ago

So nobody from Zscaler can answer this question?

Osho_Dwivedi

(Employee)

a year ago

A Cisco FTD and an ASA really have a similar configuration.

https://help.zscaler.com/zia/ipsec-vpn-configuration-guide-cisco-asa-55xx

Cisco FTD does not support Null encryption. So, keep that in mind while configuring the tunnel and check for ZIA-ENC-VPN SKU under the Zscaler subscription which lets you build a encrypted Phase2.

If this response

solves your

question

please use the

Select as Best

option

mekhan19

(Customer)

8 months ago

Hi Osho,

As a firewall engineer working in a multivendor environment, I wanted to share some observations regarding FTD integration with ZScaler ZIA.

While ZScaler's documentation doesn't officially list FTD as a supported platform (only ASA via the LINA engine), we've been testing both. ASA integration worked after some configuration adjustments, but we're encountering a persistent issue with FTD.

The Issue (consistent across policy-based and route-based VPN):

The IPSec tunnel establishes successfully with visible encaps/decaps on FTD, and ZScaler firewall logs show activity. However, web traffic doesn't flow initially. After approximately 4 hours (variable), traffic begins working normally with complete TCP handshakes visible. If we then flap the tunnel, the issue recurs—requiring another extended wait before traffic resumes.

We've engaged both ZScaler and Cisco TAC jointly but haven't identified a root cause. It appears traffic may be held on ZScaler's side during some analysis or throttling process.

Our Ask:

Has the community encountered this behavior? Is there a validated FTD configuration for ZScaler, or are there known product gaps being addressed?

We've paused our ZScaler rollout pending resolution. While we recognize ZScaler's value, FTD compatibility appears to need further development.

Any guidance would be appreciated.

Best regards, Mahira

Corrales9602

(Employee)

8 months ago

Hello @David Tran​ ,

You're absolutely correct to notice that the Zscaler documentation primarily lists Cisco ASA 55xx series. However, rest assured, Cisco Firepower Threat Defense (FTD) is indeed fully supported for site-to-site VPN connectivity with Zscaler's Private Service Edges (PSEs) for ZIA.

Why it's not explicitly listed in the Zscaler documentation:

Underlying IPsec Implementation: Cisco FTD, while a newer next-gen firewall, leverages the same core IPsec VPN engine and protocols as the older ASA operating system. Zscaler's documentation focuses on the

IPsec protocol requirements

(IKEv1/IKEv2, encryption algorithms, hashing, DH groups, lifetimes) which are consistent across most Cisco platforms, including FTD.

Generalization vs. Specifics: Zscaler aims for vendor-agnostic documentation. Providing specific configuration guides for

every

firewall vendor and

every

model would be an enormous, constantly outdated task. They provide examples with popular devices like the ASA 55xx, which many customers still use, assuming that skilled network engineers can translate the protocol requirements to their specific platform.

Market Adoption & Documentation Lag: While FTD is newer, the ASA 55xx series has been around for a very long time, and many Zscaler customers initially configured their VPNs using ASA. Documentation updates can sometimes lag behind new product adoption.

"Proof" and Documentation:

While you won't find a dedicated "Zscaler VPN with Cisco FTD" guide directly from Zscaler, the "proof" comes from:

Successful Deployments: A very large number of Zscaler customers (including enterprises with significant Cisco footprints) successfully use FTD appliances to establish VPN tunnels to Zscaler.

Standard IPsec: FTD fully supports standard IPsec IKEv1 (and IKEv2), which is what Zscaler utilizes for these tunnels. As long as your device can speak standard IPsec, it can connect.

Mapping ASA Concepts to FTD: The principles outlined in the ASA 55xx documentation regarding Phase 1/Phase 2 parameters, DPD, NAT-T, and interesting traffic are directly applicable to FTD, even if the GUI (Firepower Management Center - FMC or Firepower Device Manager - FDM) or CLI commands are different.

Key Configuration Elements for FTD to Zscaler VPN:

When configuring the VPN on your FTD, you'll generally need to address the following, mapping them to the equivalent FTD objects and policies:

IKEv1 Policy (Phase 1):

Authentication: Pre-shared Key (PSK)

Encryption: AES256 or AES128

Hashing: SHA256 or SHA1

Diffie-Hellman Group: Group 5 (most common), Group 14

Lifetime: 86400 seconds (24 hours) for Zscaler, but

Zscaler recommends 28800 seconds (8 hours)

for more aggressive rekeys, or you can stick to FTD's default. Match Zscaler's requirement here for stability.

PFS: Disabled (Zscaler typically doesn't use PFS for Phase 1).

IPsec Proposal / Transform Set (Phase 2):

Encryption: AES256 or AES128

Hashing: SHA256 or SHA1

Diffie-Hellman Group (PFS): Group 5 (most common), Group 14 (Zscaler usually recommends a DH group for Phase 2, often matching Phase 1).

Lifetime: 3600 seconds (1 hour)

Protocol: ESP

Mode: Tunnel

Tunnel Group / Connection Profile:

Define the remote peer (Zscaler PSE IP address).

Enter the Pre-shared Key.

Set the Local and Remote Identity (usually IP address of the tunnel interface on FTD and the Zscaler PSE IP).

Interesting Traffic (Access Control List / Network Object):

This defines which traffic

should

go over the VPN tunnel.

Source: Your internal networks that need ZIA access (e.g., 192.168.1.0/24)

Destination: Zscaler's internal 100.64.0.0/10  space (used for internal routing within the Zscaler cloud for policy enforcement). Crucially, do NOT use 0.0.0.0/0 as destination, as this can create routing loops if not handled carefully with static routes.

This ACL is typically referenced in a crypto map equivalent in FTD.

NAT Exemption / No-NAT Rule:

You

must

exempt the interesting traffic from any NAT policies on your FTD. Traffic going over the VPN tunnel should

not

be source NAT'd by your FTD. This is usually done with an identity NAT rule or a specific NAT exemption rule, ensuring traffic between your LAN and the Zscaler cloud is not translated.

Dead Peer Detection (DPD):

Enable DPD on the FTD side. Zscaler expects DPD to monitor tunnel health. Configure it for reasonable intervals (e.g., 10 seconds, 2 retries).

Route Configuration:

Once the tunnel is up, you need a static route on your FTD for the Zscaler cloud's internal network (

100.64.0.0/10) pointing towards the IPSec tunnel interface or crypto map.

For failover, you'll typically configure two primary tunnels to two different Zscaler PSEs, and optionally two secondary tunnels (using track or SLA monitoring on the routes for failover).

Resources for FTD Configuration:

Cisco's Documentation: Refer to Cisco's official documentation for FTD (either CLI or FMC guides) on configuring site-to-site IPsec VPNs. Look for "IKEv1 IPsec VPN" or "S2S VPN."

Community Forums: Search Cisco Support Community or Zscaler Community forums. Many users have shared FTD configurations for Zscaler VPNs.

In summary: Yes, FTD is fully capable and widely used for Zscaler VPNs. Focus on applying Zscaler's IPsec protocol requirements to the correct FTD configuration objects via FMC or FDM.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2018

at

01:07 PM

Ip address as criteria in url policy

ZIA - Cloud Firewall

ram75

1,191

1191 Views

0 Likes

1 Comment

10/8/2020

at

08:41 AM

Block All access & Allow certain user or group (ZIA)

ZIA - Cloud Firewall

Sec_def_Def_sec

1,818

1818 Views

1 Like

2 Comments

12/19/2022

at

04:14 PM

URL filtering policy vs Cloud App policy control

ZIA - Cloud Firewall

Ahmed

4 Views

0 Likes

2 Comments

12/9/2022

at

09:40 PM

Apple News RSS Feed

ZIA - Cloud Firewall

Trace Woodbury-RidgeIT

2 Views

0 Likes

1 Comment

9/20/2022

at

03:20 PM

How does Zscaler Internet Access itself route the traffic to the internet, using what outgoing/next hop GW

ZIA - Cloud Firewall

tamerz

6 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

configure site-2-site vpn between Cisco Firepower Thread Defense (FTD) and zscaler
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000XXzLe0AL/dropped-due-to-failed-client-ssl-handshake","lastmod":"2025-08-22T07:28:09.000Z","id":"0D5PJ00000XXzLe0AL"} -->
## Dropped due to failed client SSL handshake

- Source: https://community.zscaler.com/s/question/0D5PJ00000XXzLe0AL/dropped-due-to-failed-client-ssl-handshake
- Type: Q&A
- Last activity: 2025-08-22T07:28:09.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - SSL Inspection

Julio Saldaña

(Customer) asked a question.

May 12, 2025 at 9:55 PM

Dropped due to failed client SSL handshake

When reviewing logs in the ZIA Administration console I get the following block Dropped due to failed client SSL handshake to this URL vtexid.vtex.com.br.

I check on the following SSL Checker platform https://www.sslchecker.com/sslchecker and when verifying the provider signing it shows SSL is not trusted

Is it correct to apply an SSL Inspection exception?

ZIA - SSL Inspection

4 answers

874 views

Ramesh Mani

(Partner)

a year ago

This would happen if the Destination using self-signed, validate the reputation of  the site as well

Osho_Dwivedi

(Employee)

a year ago

Client SSL Handshake Failure Reason

lists the reasons for this error.

From the looks of the results from

SSL Checker

it looks like the certificate is not trusted and the cert chain is missing as well.

If this response

solves your

question

please use the

Select as Best

option

.﻿

Raj90909

(Customer)

a year ago

Looks like they may have fixed the certificate.  Not getting certificate warning.

Both show valid -

https://www.sslshopper.com/ssl-checker.html#hostname=vtexid.vtex.com.br

https://www.ssl.org/

Raj909

(Customer)

a year ago

Looks like they may have fixed the certificate.  Not getting certificate warning.

Both show valid -

https://www.sslshopper.com/ssl-checker.html#hostname=vtexid.vtex.com.br

https://www.ssl.org/

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2021

at

07:54 PM

Youtube Restricted Mode Bypass Safe Search

ZIA - SSL Inspection

JonM

8 Views

1 Like

5 Comments

11/24/2021

at

06:09 AM

Reflection time of SSL inspection exclusion settings

ZIA - SSL Inspection

Nyajima

4 Views

0 Likes

1 Comment

4/26/2024

at

02:39 PM

End User Notifcation - Submit To Security Cloud option

ZIA - SSL Inspection

citrus3118

311

311 Views

0 Likes

1 Comment

8/21/2024

at

08:06 PM

StackHawk with Zscaler SSL Inspection

ZIA - SSL Inspection

User16182582818315999266

568

568 Views

0 Likes

4 Comments

3/10/2025

at

12:22 AM

Reputation Block policy

ZIA - SSL Inspection

JM

417

417 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Dropped due to failed client SSL handshake
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000XakFq0AJ/youtube-bypass-for-safesearch","lastmod":"2025-05-16T10:21:53.000Z","id":"0D5PJ00000XakFq0AJ"} -->
## Youtube bypass for SafeSearch

- Source: https://community.zscaler.com/s/question/0D5PJ00000XakFq0AJ/youtube-bypass-for-safesearch
- Type: Q&A
- Last activity: 2025-05-16T10:21:53.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

cscmd

(Customer) asked a question.

May 13, 2025 at 3:04 PM

Youtube bypass for SafeSearch

I would like to keep SafeSearch enabled but bypass restrictions for Youtube. How may I do this?

Other answers here seem to show how to disable SafeSearch and still redirect Youtube to the restricted site.

ZIA - URL Filtering

4 answers

562 views

Top Rated Answers

Osho_Dwivedi

(Employee)

a year ago

As of today, Safesearch is global and the only option to disable safesearch for YouTube is to either disable the safesearch completely or bypass YouTube from SSL inspection.

We're working on an ER for delivering Granular service control for SafeSearch.

If this response

solves your

question

please use the

Select as Best

option

.﻿

Selected as Best

1 like

All Answers

Ramesh Mani

(Partner)

a year ago

What is the intention of keep  SafeSearch enabled ? if you dont need restrictions ?

cscmd

(Customer)

a year ago

I do want the restrictions (for everything else) however I'd like to allow Youtube to operate in a non-restricted setting.  There are professional (live) videos that are not allowed in the restricted mode.

BigMac

(Customer)

a year ago

The biggest problem for us is that SafeSearch on Youtube automatically restricts access to all live streams.

Osho_Dwivedi

(Employee)

a year ago

As of today, Safesearch is global and the only option to disable safesearch for YouTube is to either disable the safesearch completely or bypass YouTube from SSL inspection.

We're working on an ER for delivering Granular service control for SafeSearch.

If this response

solves your

question

please use the

Select as Best

option

.﻿

Selected as Best

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,922

5922 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

893

893 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Youtube bypass for SafeSearch
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000XanL60AJ/zia-cloud-app-urls-for-sipa-redirect","lastmod":"2025-05-15T06:38:10.000Z","id":"0D5PJ00000XanL60AJ"} -->
## ZIA cloud app URLs for SIPA redirect

- Source: https://community.zscaler.com/s/question/0D5PJ00000XanL60AJ/zia-cloud-app-urls-for-sipa-redirect
- Type: Q&A
- Last activity: 2025-05-15T06:38:10.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZPA - Forwarding

avshch

(Customer) asked a question.

May 13, 2025 at 4:39 PM

ZIA cloud app URLs for SIPA redirect

Hello,

I need to forward traffic for DuoSecurity authentication service via SIPA.

Is there a way to figure out which urls "Duo" Cloud app consists of at ZIA Side? so SIPA forwarding can be correctly defined in ZPA on pre url basis.

Also, It appears doufederal.com is not a part of "Duo" Cloud app in ZIA for some reason.

Please advice.

Thanks,

ZPA - Forwarding

4 answers

361 views

Ramesh Mani

(Partner)

a year ago

Hi, See if this helps:

https://help.duo.com/s/article/1337?language=en_US

avshch

(Customer)

a year ago

@Ramesh Mani​ The provided link does not help as I need the definitions on Zscaler ZIA side.

Duofederal.com

is not defined as a part of "Duo" Cloud application.

1 like

Osho_Dwivedi

(Employee)

a year ago

You can use this article to know Duo's IPs/FQDN

https://help.duo.com/s/article/1337?language=en_US

I will check if I can find the URLs listed in Duo Cloud App at ZS. But probably as mentioned on a previous thread, we will have to find it via logs.

As for

duofederal.com

it's permanently moved to

duo.com

If this response

solves your

question

please use the

Select as Best

option

.﻿﻿

1 like

Jainil_G

(Employee)

a year ago

Hello @Alex Shchukin​ For your query, the "Duo" Cloud App on the Zscaler side consists only of ".duosecurity.com."  ( "

(.) leading period

i.e wildcard URL"). The article shared earlier (

https://help.duo.com/s/article/1337?language=en_US

) includes all wildcard-based URLs for Duo's IPs/FQDNs, which are part of Zscaler's "Duo" Cloud App wildcard entry.

If you come across any additional required URLs from Duo's side, you can request their addition to the Duo Cloud App on Zscaler side.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

1 like

Log In to Answer

Associated Tags

sipa

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/5/2024

at

09:21 PM

Remote Tanium PXE Boot Server with ZPA allowing local ports to pass.

ZPA - Forwarding

User16182582818315999266

452

452 Views

0 Likes

6 Comments

5/5/2025

at

06:17 AM

wifi issue with zpa

ZPA - Forwarding

Athees

353

353 Views

0 Likes

3 Comments

7/25/2025

at

12:13 AM

A single server group vs two or more server groups

ZPA - Forwarding

jaicybersec

341

341 Views

0 Likes

3 Comments

7/30/2025

at

08:47 AM

Difference between SIPA and ZPA

ZPA - Forwarding

Dhananjay_Bhakte

950

950 Views

0 Likes

8 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

ZIA cloud app URLs for SIPA redirect
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000XkqKD0AZ/youtube-playlists-auto-play-does-not-work-using-zscaler","lastmod":"2025-05-16T18:05:58.000Z","id":"0D5PJ00000XkqKD0AZ"} -->
## Youtube playlists auto play does not work using Zscaler

- Source: https://community.zscaler.com/s/question/0D5PJ00000XkqKD0AZ/youtube-playlists-auto-play-does-not-work-using-zscaler
- Type: Q&A
- Last activity: 2025-05-16T18:05:58.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

liviuionut.olteanu

(Customer) asked a question.

May 15, 2025 at 6:22 PM

Youtube playlists auto play does not work using Zscaler

Hello,

Youtube playlist autoplay does not work while having active Zscaler Client Connector version 4.5.0.296

I noticed some playlists work fine but mostly of them do not.

Example:

Acces a specific track within a specific playlist (ex https://www.youtube.com/watch?v=LhZwYqwqxRI&list=PLWwLH8FlyN98HSn5-RBh14jEkQoCSqqEH&index=10)

When this track ends, the browser gets redirected to the next track, https://www.youtube.com/watch?v=JYM7NsqFKPQ&list=PLWwLH8FlyN98HSn5-RBh14jEkQoCSqqEH&index=11, but the track wont start until i manually reload the tab.

When Zscaler is logged out the playlist works fine.

Could someone please help me with this issue ? What configurations should be done in order to bypass this behaviour ?

Thanks

Liviu

Client Connector

1 answer

460 views

Ben_Garrison

(Employee)

a year ago

If this is caused by Zscaler, then it could be a safe mode browser policy enabled to stop autoplay. In that case, you cannot "bypass" it.

This is likely to prevent screen timeout workarounds by playing a long video or playlist in the background.

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/7/2020

at

04:05 AM

Z-App -8 Network Error when users log in on Windows 10

Client Connector

cburge97

2,883

2883 Views

0 Likes

11

11 Comments

7/3/2020

at

11:55 AM

ZAPP intune deployment

Client Connector

Mk001

1,377

1377 Views

0 Likes

4 Comments

1/28/2021

at

03:42 PM

Compare ezAgent and ZCC - when to use which?

Client Connector

hukel

704

704 Views

0 Likes

2 Comments

8/18/2020

at

12:15 PM

MacOS Zscaler App Log Location

Client Connector

brad

3,567

3567 Views

0 Likes

1 Comment

3/7/2022

at

03:41 PM

Can a User with multiple devices use them simultaneously whilst logged in using that same single account

Client Connector

michael.makombe

4 Views

0 Likes

12

12 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Youtube playlists auto play does not work using Zscaler
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000XvIFR0A3/block-teamviewer-local-app-and-allow-access-to-teamviewer-web-app","lastmod":"2025-08-22T07:28:10.000Z","id":"0D5PJ00000XvIFR0A3"} -->
## Block TeamViewer local App and Allow access to TeamViewer web App

- Source: https://community.zscaler.com/s/question/0D5PJ00000XvIFR0A3/block-teamviewer-local-app-and-allow-access-to-teamviewer-web-app
- Type: Q&A
- Last activity: 2025-08-22T07:28:10.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Cloud Firewall

ju.kr

(Customer) asked a question.

May 19, 2025 at 10:03 AM

Block TeamViewer local App and Allow access to TeamViewer web App

Hi everyone,

I have a question regarding TeamViewer. We want to differentiate the access between the TeamViewer local app and the web app. Our plan is to block the local app of TeamViewer because other people can access our employees' computers, but we want to allow access to the web app since it only allows connections to other computers and doesn't receive support from other devices. I have tested several ways, but in the end, the web app and local app use the same URLs. How do you separate the access to the local and web app?

Thank you in advance.

ZIA - Cloud Firewall

3 answers

731 views

manuel

(Customer)

a year ago

Hello

ju.kr

Zscaler is no solution for blocking usage of local apps. If you need to stop your users from using local application I would recommend to leverage tools like intune/Microsoft AppLocker or any other client-based tools. The preferred approach should be to block users from generally using these local apps, not trying to block traffic from local apps whereas you allow the same traffic from browsers.

BR

Manuel

1 like

Raj90909

(Customer)

a year ago

Manuel's approach is spot on.  We've used AppLocker as well to block certain applications from installing/launching on local machines.

Raj909

(Customer)

a year ago

Manuel's approach is spot on.  We've used AppLocker as well to block certain applications from installing/launching on local machines.

Log In to Answer

Associated Tags

accesspolicy

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2018

at

01:07 PM

Ip address as criteria in url policy

ZIA - Cloud Firewall

ram75

1,193

1193 Views

0 Likes

1 Comment

10/8/2020

at

08:41 AM

Block All access & Allow certain user or group (ZIA)

ZIA - Cloud Firewall

Sec_def_Def_sec

1,832

1832 Views

1 Like

2 Comments

12/19/2022

at

04:14 PM

URL filtering policy vs Cloud App policy control

ZIA - Cloud Firewall

Ahmed

4 Views

0 Likes

2 Comments

12/9/2022

at

09:40 PM

Apple News RSS Feed

ZIA - Cloud Firewall

Trace Woodbury-RidgeIT

2 Views

0 Likes

1 Comment

9/20/2022

at

03:20 PM

How does Zscaler Internet Access itself route the traffic to the internet, using what outgoing/next hop GW

ZIA - Cloud Firewall

tamerz

6 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Block TeamViewer local App and Allow access to TeamViewer web App
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000YU2KN0A1/source-ip-address-whitelisting-in-api-access-in-zia","lastmod":"2025-06-04T05:43:44.000Z","id":"0D5PJ00000YU2KN0A1"} -->
## source ip address whitelisting in API access in ZIA?

- Source: https://community.zscaler.com/s/question/0D5PJ00000YU2KN0A1/source-ip-address-whitelisting-in-api-access-in-zia
- Type: Q&A
- Last activity: 2025-06-04T05:43:44.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Authentication

mohan_falak

(Partner) asked a question.

May 29, 2025 at 9:09 AM

source ip address whitelisting in API access in ZIA?

Is it possible to implement source ip address whitelisting in API access in ZIA? if yes how

ZIA - Authentication

1 answer

200 views

Ramesh Mani

(Partner)

a year ago

We have option to define source IP specific access for Zidentity portal , but I dont see such option for OneAPI

Log In to Answer

Associated Tags

accesspolicy

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/15/2022

at

12:39 PM

Onprem Active directory integrate with Zscaler cloud ZIA

ZIA - Authentication

aravindhan.m

3 Views

0 Likes

3 Comments

7/11/2022

at

07:26 AM

Zscaler IDP authentication issue

ZIA - Authentication

aravindhan.m

4 Views

0 Likes

2 Comments

5/27/2022

at

01:49 PM

Zscaler ZIA O365 Apps not authenticating

ZIA - Authentication

abmaclean

4 Views

0 Likes

6 Comments

10/4/2021

at

10:43 PM

Automatic de-provisioning using SCIM with Azure AD

ZIA - Authentication

Paul_Wineberg

4 Views

0 Likes

4 Comments

6/27/2020

at

03:01 PM

Zscaler public preview

ZIA - Authentication

Muhammad

364

364 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

source ip address whitelisting in API access in ZIA?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000YePPw0AN/endpoint-dlp-capabilities","lastmod":"2025-11-19T02:18:16.000Z","id":"0D5PJ00000YePPw0AN"} -->
## Endpoint DLP capabilities

- Source: https://community.zscaler.com/s/question/0D5PJ00000YePPw0AN/endpoint-dlp-capabilities
- Type: Q&A
- Last activity: 2025-11-19T02:18:16.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Enforcement Points

JB

(Partner) asked a question.

May 31, 2025 at 7:33 AM

Endpoint DLP capabilities

Hi Zscaler Team, Is there an estimated timeline for when Zscaler's Endpoint DLP solution will support application-level data leakage detection and prevention for Windows and macOS devices? and Endpoint DLP support for Linux ?

Enforcement Points

1 answer

308 views

Corrales9602

(Employee)

8 months ago

Hello @Jagadeesh Boddula​ ,

Thank you for reaching out through out Community Services.

Regarding your concern, Endpoint DLP for Linux is currently not supported however our Dev team is currently aware that customers are looking for this feature to be implemented. There is currently an ongoing Enhancement Request (ER) ticket for this matter specifically.

As a recommendation, you can reach out to your Account Team (Sales and TSM) for them to add your company/account to the wait list of customers looking for this feature to be implemented.

Regarding DLP for application-level, currently there is no official ask to our Dev team for this to be implemented. My suggestion will be to follow the same path as for EDLP for Linux, reach out your Account Team so they can file an ER ticket in order to either have engineering working on this or to give us the green/red light on this Enhancement Request.

I hope this information helps, in case of any doubts or comments please do not hesitate to reach us back!

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

No posts to show.

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Endpoint DLP capabilities
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ZRUz50AH/how-can-i-install-standalone-vzen-on-esxi-hypervisor","lastmod":"2025-06-15T13:06:44.000Z","id":"0D5PJ00000ZRUz50AH"} -->
## How can I install standalone VZEN on ESXI hypervisor

- Source: https://community.zscaler.com/s/question/0D5PJ00000ZRUz50AH/how-can-i-install-standalone-vzen-on-esxi-hypervisor
- Type: Q&A
- Last activity: 2025-06-15T13:06:44.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

Fred Tsang

(Partner) asked a question.

June 13, 2025 at 8:39 AM

How can I install standalone VZEN on ESXI hypervisor

Hi,

I'm trying to set up a standalone VZEN on ESXI, but turn on it unsuccessfully. I tried to raise suppot ticket, and suggest to use Free BSD higher version, but Free BSD (64 bits) already in use. Seems no higher version.....

Also, he insists that I need to enable Promiscuous mode, which current enviroment I don't have. I just want to run standalone VZEN for testing. Is it a must?

Let' see any advice. Thanks!

Fred

ZIA - Forwarding

1 answer

188 views

Ramesh Mani

(Partner)

a year ago

See if these requirements met-

https://help.zscaler.com/zia/configuring-virtual-service-edge-clusters

Steps to follow : -

https://help.zscaler.com/zia/configuring-virtual-service-edge-clusters

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

490

490 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

How can I install standalone VZEN on ESXI hypervisor
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000Zc7Nx0AJ/zscaler-cloud-firewall-custom-application-signatures","lastmod":"2025-06-19T04:31:45.000Z","id":"0D5PJ00000Zc7Nx0AJ"} -->
## Zscaler Cloud Firewall custom application signatures

- Source: https://community.zscaler.com/s/question/0D5PJ00000Zc7Nx0AJ/zscaler-cloud-firewall-custom-application-signatures
- Type: Q&A
- Last activity: 2025-06-19T04:31:45.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Cloud Firewall

Nikoolayy1

(Customer) asked a question.

June 17, 2025 at 8:07 AM

Zscaler Cloud Firewall custom application signatures

I know that the Zscaler cloud firewall now supports custom Snort signatures for IPS but what about custom app signatures ?

About Custom IPS Signature Rules | Zscaler

I did find

Creating Custom Application Signatures | Zscaler

but can then this be used in the firewall or at least in the web policy protection ?

ZIA - Cloud Firewall

2 answers

287 views

Gianluca.bastia

(Partner)

a year ago

The article is specifically for Business Insights.

For FW you can create custom services and or IP destinations.

Jainil_G

(Employee)

a year ago

Hello @Niokolay Dimitrov​ -  Currently, we do not have a feature for Custom Application Signatures. However, there is an enhancement request (ER-8192), and based on its priority, this feature may be implemented in the future.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

3 likes

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2018

at

01:07 PM

Ip address as criteria in url policy

ZIA - Cloud Firewall

ram75

1,195

1195 Views

0 Likes

1 Comment

10/8/2020

at

08:41 AM

Block All access & Allow certain user or group (ZIA)

ZIA - Cloud Firewall

Sec_def_Def_sec

1,844

1844 Views

1 Like

2 Comments

12/19/2022

at

04:14 PM

URL filtering policy vs Cloud App policy control

ZIA - Cloud Firewall

Ahmed

4 Views

0 Likes

2 Comments

12/9/2022

at

09:40 PM

Apple News RSS Feed

ZIA - Cloud Firewall

Trace Woodbury-RidgeIT

2 Views

0 Likes

1 Comment

9/20/2022

at

03:20 PM

How does Zscaler Internet Access itself route the traffic to the internet, using what outgoing/next hop GW

ZIA - Cloud Firewall

tamerz

6 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Zscaler Cloud Firewall custom application signatures
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ZlVkL0AV/is-there-any-api-to-fetch-web-firewall-logs","lastmod":"2025-06-24T06:15:08.000Z","id":"0D5PJ00000ZlVkL0AV"} -->
## Is there any API to fetch Web / Firewall logs?

- Source: https://community.zscaler.com/s/question/0D5PJ00000ZlVkL0AV/is-there-any-api-to-fetch-web-firewall-logs
- Type: Q&A
- Last activity: 2025-06-24T06:15:08.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Logging

GeetaM

(Customer) asked a question.

June 19, 2025 at 11:28 AM

Is there any API to fetch Web / Firewall logs?

Hi,

Is there any API to fetch Web / Firewall logs from Zscaler->Analytics -->insights--> logs ( Web/Firewall ). How can we get the Zscaler API document to check the same?

Thanks

ZIA - Logging

1 answer

314 views

Ramesh Mani

(Partner)

a year ago

As of now no API for Web / firewall log fetch. NSS (ZIA) and LSS (ZPA ) is mandatory.

Check here,

https://help.zscaler.com/oneapi/understanding-oneapi

Log In to Answer

Associated Tags

api

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

6/8/2023

at

08:51 PM

URL Reporting

ZIA - Logging

akita_down

12

12 Views

0 Likes

4 Comments

11/3/2023

at

06:08 PM

ZIA - NSS Feed Status

ZIA - Logging

Rallis

616

616 Views

0 Likes

3 Comments

2/21/2024

at

10:43 PM

Find if User is behind a VPN

ZIA - Logging

venG

542

542 Views

0 Likes

2 Comments

3/13/2025

at

01:56 AM

How to Extract Logs from Web Insight

ZIA - Logging

JSK27

668

668 Views

0 Likes

8 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Is there any API to fetch Web / Firewall logs?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ZlYpA0AV/query-zia-zss-logs-in-xsoar","lastmod":"2025-06-24T06:16:34.000Z","id":"0D5PJ00000ZlYpA0AV"} -->
## Query ZIA ZSS logs in XSOAR

- Source: https://community.zscaler.com/s/question/0D5PJ00000ZlYpA0AV/query-zia-zss-logs-in-xsoar
- Type: Q&A
- Last activity: 2025-06-24T06:16:34.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Logging

GeetaM

(Customer) asked a question.

June 19, 2025 at 12:42 PM

Query ZIA ZSS logs in XSOAR

Hi,

How can I query ZIA Web/firewall logs from XSOAR ?

Thanks

ZIA - Logging

1 answer

266 views

Ramesh Mani

(Partner)

a year ago

Hi, you have to install a NSS or LSS servers , forward it to your SIEM and pull the same from your XSOAR solution.

there is no direct log collection from Zscaler to any solution without NSS / LSS

Log In to Answer

Associated Tags

api

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

6/8/2023

at

08:51 PM

URL Reporting

ZIA - Logging

akita_down

12

12 Views

0 Likes

4 Comments

11/3/2023

at

06:08 PM

ZIA - NSS Feed Status

ZIA - Logging

Rallis

616

616 Views

0 Likes

3 Comments

2/21/2024

at

10:43 PM

Find if User is behind a VPN

ZIA - Logging

venG

542

542 Views

0 Likes

2 Comments

3/13/2025

at

01:56 AM

How to Extract Logs from Web Insight

ZIA - Logging

JSK27

667

667 Views

0 Likes

8 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Query ZIA ZSS logs in XSOAR
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ZwPPw0AN/recommended-adjustments-due-to-iranian-tensions","lastmod":"2025-06-24T13:05:03.000Z","id":"0D5PJ00000ZwPPw0AN"} -->
## Recommended Adjustments Due to Iranian Tensions?

- Source: https://community.zscaler.com/s/question/0D5PJ00000ZwPPw0AN/recommended-adjustments-due-to-iranian-tensions
- Type: Q&A
- Last activity: 2025-06-24T13:05:03.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

User16502919808147811813

(Customer) asked a question.

June 23, 2025 at 4:52 PM

Recommended Adjustments Due to Iranian Tensions?

Are there any recommendations from ZIA customers or Zscaler Support in terms of strengthening our ZIA policy to mitigate the risk of a potential cyberattack from Iran?

Thanks in advance.

ZIA - URL Filtering

2 answers

247 views

Ramesh Mani

(Partner)

a year ago

Refer,

https://www.zscaler.com/products-and-solutions/cyberthreat-protection

https://www.zscaler.com/campaign/threatlabz-phishing-report

https://www.youtube.com/watch?v=vHhL6nQQQKs

Ramesh Mani

(Partner)

a year ago

you may try to block country specific sites, under Policy > Web > Security > Advanced Threat Protection.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,927

5927 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

895

895 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Recommended Adjustments Due to Iranian Tensions?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000aKOi20AG/forwarding-methods-tunnel-vs-tunnel-with-local-proxy","lastmod":"2025-06-30T15:17:25.000Z","id":"0D5PJ00000aKOi20AG"} -->
## Forwarding methods - Tunnel vs Tunnel with Local Proxy

- Source: https://community.zscaler.com/s/question/0D5PJ00000aKOi20AG/forwarding-methods-tunnel-vs-tunnel-with-local-proxy
- Type: Q&A
- Last activity: 2025-06-30T15:17:25.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

Apoorva17

(Customer) asked a question.

June 30, 2025 at 12:48 PM

Forwarding methods - Tunnel vs Tunnel with Local Proxy

Hello Team,

Can anyone please explain what the exact difference is between

Tunnel vs Tunnel with Local Proxy along with its use cases?

For ex: we have Tunnel 1.0/Tunnel 2.0 and external VPN app what should be the Forwarding profile setting?

Many thanks,

Apoorva Kale

Client Connector

2 answers

711 views

MikeRuiz

(Employee)

a year ago

There are two functions that happen Traffic Capture and Traffic Forwarding.

In Tunnel 1.0 mode, traffic on tcp/80 and tcp/443 are captured and put into an HTTP Connect tunnel to  Forward the traffic to ZIA

In Tunnel 2.0 mode, traffic on any tcp or udp port can be captured and put into a TLS or DTLS Tunnel for forwarding to ZIA.

Tunnel with Local Proxy mode on it's own does not capture any traffic.  It relies on a PAC file being in the System/Browser/App to capture traffic and set it's proxy destination to localhost:9000 which is a traffic listener in Client Connector.  Traffic sent to that listener can be forwarded to ZIA.    That PAC can be set in the Forwarding Profile PAC field.

Tunnel with Local Proxy mode is most commonly used on MacOS today to coexist with VPN clients where we would end up fighting with that VPN client for routes.

You should work with your SE, TAM, and Zscaler Support for any specific coexistence questions, or best config for your environment...

4 likes

Jainil_G

(Employee)

a year ago

Hello @Apoorva Kale​ A good detail explanation and use cases discussion is available on the

https://community.zscaler.com/Zenith/s/question/0D54u00009evn1LCAQ/difference-between-tunnel-and-tunnel-with-local-proxy

, hope this helps.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

2 likes

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/7/2020

at

04:05 AM

Z-App -8 Network Error when users log in on Windows 10

Client Connector

cburge97

2,884

2884 Views

0 Likes

11

11 Comments

7/3/2020

at

11:55 AM

ZAPP intune deployment

Client Connector

Mk001

1,378

1378 Views

0 Likes

4 Comments

1/28/2021

at

03:42 PM

Compare ezAgent and ZCC - when to use which?

Client Connector

hukel

704

704 Views

0 Likes

2 Comments

8/18/2020

at

12:15 PM

MacOS Zscaler App Log Location

Client Connector

brad

3,571

3571 Views

0 Likes

1 Comment

3/7/2022

at

03:41 PM

Can a User with multiple devices use them simultaneously whilst logged in using that same single account

Client Connector

michael.makombe

4 Views

0 Likes

12

12 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Forwarding methods - Tunnel vs Tunnel with Local Proxy
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000aSlic0AC/chatgpt-bypass-for-hongkong-node","lastmod":"2025-07-07T12:37:55.000Z","id":"0D5PJ00000aSlic0AC"} -->
## chatgpt bypass for HongKong Node

- Source: https://community.zscaler.com/s/question/0D5PJ00000aSlic0AC/chatgpt-bypass-for-hongkong-node
- Type: Q&A
- Last activity: 2025-07-07T12:37:55.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

YtseJam

(Customer) asked a question.

July 2, 2025 at 12:20 AM

chatgpt bypass for HongKong Node

Hello Everyone,

Since chatgpt is blocked in HongKong, im trying to implement the below PAC File script below but does not work, and still going out to HongKong node.

Anyone here able to implement similar pac file?

if (((shExpMatch(country,"Philippines"))) &&

((shExpMatch(host,"*.chatgpt.com")) ||

(shExpMatch(host,"*.chat.openai.com")))) {

return "PROXY sin4.sme.zscaler.net:80;DIRECT";

ZIA - Forwarding

1 answer

360 views

Jainil_G

(Employee)

a year ago

Hello @Ytse Jam​ , The Country variable needs to be defined as var country = "${COUNTRY}"; , which I do not see in your PAC file. Kindly ensure it is defined according to the guidelines provided at

Writing PAC Files

. Additionally, ensure that the correct cloud proxy hostname is configured. In the given sample, it should correspond to the

zscaler.net

cloud for your tenant. If it is not configured correctly, please update it as per your tenant's cloud.

BR

Jainil_G

If a post solves your question please use the '

Select as Best

' option.

Log In to Answer

Associated Tags

accesspolicy

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

492

492 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

chatgpt bypass for HongKong Node
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000aSqQc0AK/aiml-site-access-issue","lastmod":"2025-07-14T07:15:39.000Z","id":"0D5PJ00000aSqQc0AK"} -->
## AI/ML  site access issue

- Source: https://community.zscaler.com/s/question/0D5PJ00000aSqQc0AK/aiml-site-access-issue
- Type: Q&A
- Last activity: 2025-07-14T07:15:39.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

cyberjunkie

(Customer) asked a question.

July 2, 2025 at 2:16 AM

AI/ML  site access issue

I am having an issue with accessing co-pilot site. I see inconsistent behavior. some of my users are able to access it , and some get blocked by Gen AI/ML application. I don't have different policies which can cause this issue. for example.g I can access the site while on premises or on road, but some of my colleague can't if they are on premises or on road.

I have URL filtering policy which allow to access the co-pilot site. I have a default policy which also has co-pilot block since we don't want everybody to access it. I am thinking removing co-pilot from my default policy and just use allow policy. I may have to create another policy based on users or location so only users who are allowed can access it and rest will get blocked by default policy. any idea or any other suggestions.

ZIA - URL Filtering

2 answers

554 views

Jainil_G

(Employee)

a year ago

Hello @js sjan​ Based on the issue description, your team is randomly observing inconsistent behavior while accessing the Co-Pilot website. Despite having the same policy, access is allowed for some users and blocked for others.

To investigate the issue further, you should re-review the policy configuration and cross-verify it with the Web Insight logs, which provide detailed information to pinpoint the source of the problem.  Another possible cause could be Cloud App Control settings that allow or block access for certain users. In such cases, Cloud App Control takes precedence, and access is determined accordingly. Therefore, it is recommended to review the Cloud App Control configuration and verify it against the Web Insight logs.

BR

Jainil_G

If a post solves your question please use the '

Select as Best

' option.

Ronnie Meekers

(Partner)

a year ago

or possibly validate the SSL inspection policy to see if there are any differences between the different team members.

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,930

5930 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

895

895 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

AI/ML  site access issue
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000aY6ze0AC/zia-custom-group-removed-from-user-after-saml-login","lastmod":"2025-07-14T05:09:26.000Z","id":"0D5PJ00000aY6ze0AC"} -->
## ZIA Custom group removed from user after SAML login

- Source: https://community.zscaler.com/s/question/0D5PJ00000aY6ze0AC/zia-custom-group-removed-from-user-after-saml-login
- Type: Q&A
- Last activity: 2025-07-14T05:09:26.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Authentication

User17188002634799136580

(Customer) asked a question.

July 3, 2025 at 9:13 AM

ZIA Custom group removed from user after SAML login

Hello,

we've noticed that when creating a new custom group in User Administration, and assign that group to users , the attribution is removed if users login again on ZCC through the IdP (SAML)

When the IdP pass the user's attributes (username, ad groups etc) in the envelope, ZIA reset the group assignement under user administration.

This cause a loss of policies defined for custom groups created on ZIA.

Unfortunately at the moment we cannot/want to deal with new AD groups to deal with this, as we are reviewing all the AD groups.

Any hint

ZIA - Authentication

2 answers

269 views

Jainil_G

(Employee)

a year ago

Hello @Leonardo Lerede​,  This behavior in ZIA is expected when using SAML authentication with user attribute provisioning. Essentially, ZIA processes the attributes provided by the IDP during login via SAML, including details like username, department, and group memberships.

To address the issue based on your current requirements, you can either temporarily disable group synchronization from the IdP in the SAML assertion or adjust the IdP configuration on Zscaler to exclude the group claim until your AD group strategy is finalized.

BR

Jainil_G

If a post solves your question please use the '

Select as Best

' option.

Stefano

(Partner)

a year ago

Hi,

As @Jainil Gajjar​ already mentioned, this is an expected behavior of SAML auto-provisioning. Another solution that I recommend is to consider migrating to SCIM-based provisioning, as this method will decouple local groups and IdP groups, allowing sync of groups to happen without removing existing local groups that have already been assigned to the users (but need to make sure that the local groups and IdP-based groups don't share the same name). Here is a guide for reference:

https://help.zscaler.com/zia/active-directory-scim-provisioning-migration-guide

1 like

Log In to Answer

Associated Tags

user-group

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/15/2022

at

12:39 PM

Onprem Active directory integrate with Zscaler cloud ZIA

ZIA - Authentication

aravindhan.m

3 Views

0 Likes

3 Comments

7/11/2022

at

07:26 AM

Zscaler IDP authentication issue

ZIA - Authentication

aravindhan.m

4 Views

0 Likes

2 Comments

5/27/2022

at

01:49 PM

Zscaler ZIA O365 Apps not authenticating

ZIA - Authentication

abmaclean

4 Views

0 Likes

6 Comments

10/4/2021

at

10:43 PM

Automatic de-provisioning using SCIM with Azure AD

ZIA - Authentication

Paul_Wineberg

4 Views

0 Likes

4 Comments

6/27/2020

at

03:01 PM

Zscaler public preview

ZIA - Authentication

Muhammad

364

364 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

ZIA Custom group removed from user after SAML login
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000amdsV0AQ/ipsec-tunnel-between-my-organization-and-zscaler-for-zia-access","lastmod":"2025-07-16T11:29:03.000Z","id":"0D5PJ00000amdsV0AQ"} -->
## IPSec tunnel between my organization and Zscaler for ZIA access

- Source: https://community.zscaler.com/s/question/0D5PJ00000amdsV0AQ/ipsec-tunnel-between-my-organization-and-zscaler-for-zia-access
- Type: Q&A
- Last activity: 2025-07-16T11:29:03.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

zscaler_cisco

(Customer) asked a question.

July 8, 2025 at 11:49 AM

IPSec tunnel between my organization and Zscaler for ZIA access

I've read various zscaler documentation that the maximum throughput between an IPSec tunnel between an organization and zscaler is 400Mbps. If I need to scale over 1Gbps, I need to setup at least 3 IPSec tunnel between my organization and zscaler. I understand that part, but I have this question for zscaler Subject Matter Expert (SME).

Currently, I am able to download a 1TB size file from Oracle via https in a single stream at an 800Mbps through my Palo Alto firewall that also implement DLP, malware, URL filtering, etc... Now if I move this function over to Zscaler, the maximum download I can have for this session/connection will be maxed out at 400Mbps. GRE tunnel between my organization and Zscaler is NOT an option for me.

How does zscaler address this issue?

6 answers

428 views

Eric N.

(Employee)

a year ago

For end users using devices like laptops and workstations, Zscaler Client Connector is a recommended solution.

For IoT/OT or servers in a branch location, Cloud Connector or Zero Trust Branch might be a better solution.

I wonder, though, is your firewall solution doing TLS Inspection on this 1TB file?

zscaler_cisco

(Customer)

a year ago

@Eric Nute​ .  Thank you for your response.  I am only interested in IPSec between my Data Center and Zscaler infrastructure. Yes, my Palo Alto firewall can handle over 1Gbps throughput with TLS inspection.  I tested by downloading 1TB files from two different Linux servers to Oracle and CentOS mirror sites, and I got 800Mbps throughput on each servers, AT the same time.  Btw, cloud connector also has a 400Mbps limitations, isn't it?

I guess the 400Mbps is the limit with Zscaler for IPSec?  I've read various articles that the throughput is actually closer to 200Mbps and NOT 400Mbps, as demonstrated by the Aruba to Zscaler over IPSec tunnel:

https://arubanetworking.hpe.com/techdocs/sdwan-PDFs/integrations/int_Zscaler-EC-IPSec_latest.pdf

"Zscaler supports a soft limit of 200 Mbps per tunnel. If you require more bandwidth, create multiple tunnels in Zscaler. For  example, two tunnels to a single ZEN provide 400 Mbps. Three tunnels provide 600 Mbps. On the EdgeConnect appliance,  the tunnel capacity depends on the appliance model and the available WAN bandwidth. If you configure parallel tunnels, each IPsec tunnel must source from a unique IP address".

It is like having a Ferrari, but you can't speed above 55mph 😂.

Osho_Dwivedi

(Employee)

a year ago

You can use Zscaler Branch Connector which is now the Zero Trust Branch.

https://help.zscaler.com/downloads/cloud-branch-connector/reference-architecture/zero-trust-branch-connectivity-zscaler-branch-connector/Zero-Trust-Branch-Connectivity-with-Zscaler-Branch-Connector.pdf

zscaler_cisco

(Customer)

a year ago

@Osho Dwivedi​ :  We do NOT have license for Branch Connector.  Therefore, is 200Mbps is the maximum bandwidth one can get through IPSec with Zscaler the correct assumption?

Osho_Dwivedi

(Employee)

a year ago

Correct, that is 400Mbps without and 200Mbps with Phase 2 Encryption.

zscaler_cisco

(Customer)

a year ago

Thank you @Osho Dwivedi​ for the response.  Just so that I understand this correctly, before migrating to ZIA, I was able to download 800Mbps on a single session with Oracle from a server in my data center with NO ZCC.  After the ZIA migration into the cloud with Zscaler, the maximum download from the same server is 200Mbps (with phase 2 encryption).  Is that correct?

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

10/17/2024

at

06:58 AM

Misspelled  "Zscaler" Footer in Communication Emails

ozanogur

234

234 Views

3 Likes

1 Comment

1/28/2025

at

09:23 AM

Direct certain countries to different SE for a URL

danbro185

490

490 Views

1 Like

5 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

IPSec tunnel between my organization and Zscaler for ZIA access
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000b7F1q0AE/browser-block","lastmod":"2025-07-14T22:25:00.000Z","id":"0D5PJ00000b7F1q0AE"} -->
## Browser Block

- Source: https://community.zscaler.com/s/question/0D5PJ00000b7F1q0AE/browser-block
- Type: Q&A
- Last activity: 2025-07-14T22:25:00.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

Anu_12

(Customer) asked a question.

July 14, 2025 at 1:01 PM

Browser Block

Does anyone have an idea how to block " Brave Browser" explicitly in ZIA . We are currently leveraging default Secure browsing in ZIA with edge,safari,firefox,chrome & opera (block older versions). Is there a specific way to block through policies.

ZIA - URL Filtering

4 answers

631 views

Ramesh Mani

(Partner)

a year ago

Policy - Secure browsing

click block all browsers and allow only specific browsers as per your need.

Also user agent in URL filtering policy helps you to allow / block for specific traffic and browsers.

Anu_12

(Customer)

a year ago

We already have that blocked. However that setting doesn't apply to this  browser . For now we are leveraging block "

brave.com

" however this isnt listed as the browser to control in ZIA.

manuel

(Customer)

a year ago

As already stated in other threads, I think there is a misunderstanding on how to achieve what by which tool. To block usage of Brave Browser I would recommend to leverage AppLocker or similar tools, otherwise you will not be able to reliable block Brave. Zscaler ZIA is primarily a tool for traffic inspection and not for blocking executables on your clients.

BR

Manuel

Osho_Dwivedi

(Employee)

a year ago

The User-Agent condition in HTTP Header Control can help you with browser governance by enabling block/allow rules for unknown or newer browsers.

https://help.zscaler.com/zia/policies/http-header-control

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,922

5922 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

893

893 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Browser Block
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000bB7NS0A0/internal-ip-for-an-iot-device-cctv-to-whitelist-in-zscaler","lastmod":"2025-07-17T23:24:26.000Z","id":"0D5PJ00000bB7NS0A0"} -->
## Internal IP for an IOT device (CCTV) to whitelist in Zscaler

- Source: https://community.zscaler.com/s/question/0D5PJ00000bB7NS0A0/internal-ip-for-an-iot-device-cctv-to-whitelist-in-zscaler
- Type: Q&A
- Last activity: 2025-07-17T23:24:26.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - SSL Inspection

JM

(Partner) asked a question.

July 15, 2025 at 8:43 AM

Internal IP for an IOT device (CCTV) to whitelist in Zscaler

We want to whitelist the IoT device in Zscaler (either in ZIA or ZPA or both) but it only has an internal IP address.

I'm not a hundred percent sure if:

We need to configure the SIPA from ZPA to ZIA (as referred to this blog https://community.zscaler.com/zenith/s/question/0D54u00009evmfcCAA/how-can-i-connect-my-ip-camera-to-the-zscaler-cloud)

We need the IPsec configuration and will need the public IP address (?)

So far, what we have done is creating an SSL bypass inspection and then a Firewall control policy, we tried creating one at a time and none of them work.

Anyone can help or did the blog post I attached here, is what I should follow?

ZIA - SSL Inspection

4 answers

450 views

Osho_Dwivedi

(Employee)

a year ago

How does the infrastructure at the site where camera is looks like?

SIPA solution may work, but we need to undermine the traffic path and that's possible after knowning how is this location setup. Is the app connector you're going to be spinning up/use for SIPA in the same location or a different one?

IPsec configuration shall be needing a Public IP address ofcourse for the connectivity (peer authentication and traffic steering come after it).

JM

(Partner)

a year ago

Thanks for the reply, and yes, they are in the same location.

zywong

(Partner)

a year ago

Hi, may i know what you wish to achieve here? For the endpoint to reach to the IoT device or call home to your in house servers?

JM

(Partner)

a year ago

Initially, we have Sophos as our VPN client, and the IoT/CCTV works fine on that. So when we transition to Zscaler, the CCTV is inaccessible.

We have software to use when accessing the CCTV's internal IP. We want the user to access the CCTV from the software.

The error we are getting is " Address is not responsive or blocked by firewall or router".

This is why I think of having a SIPA config to our ZPA and ZIA as per the blog I have attached (?)

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2021

at

07:54 PM

Youtube Restricted Mode Bypass Safe Search

ZIA - SSL Inspection

JonM

8 Views

1 Like

5 Comments

11/24/2021

at

06:09 AM

Reflection time of SSL inspection exclusion settings

ZIA - SSL Inspection

Nyajima

4 Views

0 Likes

1 Comment

4/26/2024

at

02:39 PM

End User Notifcation - Submit To Security Cloud option

ZIA - SSL Inspection

citrus3118

311

311 Views

0 Likes

1 Comment

8/21/2024

at

08:06 PM

StackHawk with Zscaler SSL Inspection

ZIA - SSL Inspection

User16182582818315999266

574

574 Views

0 Likes

4 Comments

3/10/2025

at

12:22 AM

Reputation Block policy

ZIA - SSL Inspection

JM

420

420 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Internal IP for an IOT device (CCTV) to whitelist in Zscaler
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000bbw2D0AQ/email-dlp-for-o365-email","lastmod":"2025-07-24T14:40:27.000Z","id":"0D5PJ00000bbw2D0AQ"} -->
## Email DLP for O365 email

- Source: https://community.zscaler.com/s/question/0D5PJ00000bbw2D0AQ/email-dlp-for-o365-email
- Type: Q&A
- Last activity: 2025-07-24T14:40:27.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - DLP

mani_dayal

(Partner) asked a question.

Edited July 22, 2025 at 4:15 PM

Email DLP for O365 email

I know Zscaler recommends not to inspect O365 traffic and comes with default policies to bypass SSL inspection. how does one enable email DLP in O365. Do we put exceptions for outlook URLs? any recommendations?

This is for O365 webmail outbound

ZIA - DLP

3 answers

284 views

Ronnie Meekers

(Partner)

a year ago

Email DLP does not happen "inline". it's done "out-of-band" through the O365 API integration.

The detail is documented here:

https://help.zscaler.com/zia/what-zscaler-outbound-email-dlp

and here:

https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-outbound-email-dlp

The outlook client enforces cert pinning so SSL inspection is not possible.

Hope that helps.

Totti

(Partner)

a year ago

On a technical level I have to disagree with the statement that Email DLP is out-of-band.

Mails are forwarded to the "Smart Host" and inspected once the mail has been received. Thus it is inline.

But in regards to the original question: Email is not considered time critical and any delay due to inline inspection does not really affect the user experience.

Hence there is no need to bypass mails or SMTP traffic.

2 likes

JB

(Partner)

a year ago

Email DLP inspection is done at SMTP level, not via browser traffic

Traffic from Exchange Online to Zscaler's SMTP relay is encrypted via STARTTLS

Zscaler acts as a mail transfer agent (MTA), not a proxy,  so there is no SSL inspection needed or recommended

Zscaler receives full email headers, body, and attachments for DLP

I hope this answers

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/9/2023

at

05:32 PM

DLP notification via Zscaler Client Connector

ZIA - DLP

thacarvalho123

7 Views

2 Likes

2 Comments

11/20/2022

at

09:26 PM

External DLP Engine

ZIA - DLP

H.zyD.zy

4 Views

0 Likes

3 Comments

11/9/2022

at

04:51 PM

Zscaler Preventing Image Upload and Download on iPhone

ZIA - DLP

MacDanorld1

2 Views

0 Likes

3 Comments

6/20/2022

at

07:53 AM

Error during Microsoft Information Protection(MIP) Integration

ZIA - DLP

ozanogur

3 Views

0 Likes

4 Comments

1/31/2021

at

08:35 PM

Zscaler ZIA and ZPA with NetSkope CASB and DLP services

ZIA - DLP

jonathan.holt

1,337

1337 Views

1 Like

6 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Email DLP for O365 email
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000beovc0AA/default-url-categories-domain-is-being-allowed-but-not-the-server-ip","lastmod":"2025-07-25T07:42:33.000Z","id":"0D5PJ00000beovc0AA"} -->
## Default URL Categories | Domain is being allowed but not the Server IP

- Source: https://community.zscaler.com/s/question/0D5PJ00000beovc0AA/default-url-categories-domain-is-being-allowed-but-not-the-server-ip
- Type: Q&A
- Last activity: 2025-07-25T07:42:33.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

JM

(Partner) asked a question.

July 22, 2025 at 11:34 PM

Default URL Categories | Domain is being allowed but not the Server IP

We have used the pre-defined/default URL categories Miscellaneous; we haven't defined a custom URL since we want to BLOCK all URL that has been classified as Misc.

We have a block policy in the URL Filtering but the domain is being accessed but when we tried its server IP (the IP from the website), it is being blocked.

What I have done so far is:

Create an SSL inspection policy

Create a DNS control policy and add the URL category and the IP as destination IP (I haven't tried putting it in Source IP)

What we want to achieve is, the website is insecure (uses HTTP), and we want all insecure websites not to be accessed, regardless of the URL category or classification.

What I have also observed when accessing this website, its subdomain is being classified as Entertainment.

Can anyone explain why?

I have also requested a site review in Zscaler to change the classification of the said website but no response yet

ZIA - URL Filtering

4 answers

344 views

Jainil_G

(Employee)

a year ago

Hello @Joanna Mae Villamor​  Based on your explanation, I think there are multiple things to be addressed. Let me try to answer them:

If you have blocked the Miscellaneous category, it should ideally block all the related domains. However, if the domain/IP is added under any user-defined URL category, it will no longer be part of the Miscellaneous category. As soon as a you adds a URL/IP to a user-defined URL category, it is automatically removed from the Miscellaneous category. This happens regardless of whether the domain was added under "URLs retaining parent category." To match policies for a specific uncategorized domain under the Miscellaneous category, the domain must be removed from any custom categories. Alternatively, a similar policy rule must be defined for the custom category. Additionally, SSL Inspection must be enabled for the Miscellaneous category.

For URLs accessed via IP addresses, not all websites generally allow direct access through IP addresses. However, if there are any websites that do, you can block them using Firewall or URL Control if needed.

For a subdomain to be classified under Entertainment, it is normal for websites to have different categorizations for various subdomains that are part of the webpage. Each subdomains could belong to a different category, depending on its content.

Regarding site reviews: For each request, based on your email address, a support case is automatically created. Zscaler Support reviews the request and takes the necessary action for the URL category. Once the request is reviewed, you will receive an email notification. If you have not received an email yet, you may need to wait. -

https://help.zscaler.com/zia/using-site-review-lookup-urls

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

JM

(Partner)

a year ago

Hi Jainil, this really helps. However, I can confirm that there are no domains related to the Misc website been added to the other URL category.

The website is HTTP[://]ww1[.]shoplux[.]store

1 like

Jainil_G

(Employee)

a year ago

Hello @Joanna Mae Villamor​  I can see the categorization overlaps with Entertainment. Have you tried blocking the "Entertainment" category? (Please note it would block all other websites in this category as well.)

OR

have you tried creating a custom category to block just this website? I think, the custom website block in this case should block the website.

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

JB

(Partner)

a year ago

Accessing the domain via URL uses DNS resolution + policy evaluation on the domain name.

Accessing via IP skips the domain-based policies and applies IP-based control only,   Zscaler treats IP access as unclassified/miscellaneous or unknown, often triggering blocks if IP category or Geo/IP reputation is flagged.

HTTP websites with direct IP access are often blocked because Zscaler can’t classify or verify them without hostname (SNI or HTTP Host header)

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,922

5922 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

893

893 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Default URL Categories | Domain is being allowed but not the Server IP
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000bnjAb0AI/checkpoint-and-zscaler-in-aws","lastmod":"2025-07-28T16:45:05.000Z","id":"0D5PJ00000bnjAb0AI"} -->
## Checkpoint and ZScaler in AWS

- Source: https://community.zscaler.com/s/question/0D5PJ00000bnjAb0AI/checkpoint-and-zscaler-in-aws
- Type: Q&A
- Last activity: 2025-07-28T16:45:05.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Cloud Firewall

akskap

(Customer) asked a question.

July 24, 2025 at 8:36 PM

Checkpoint and ZScaler in AWS

Hello ZScaler Community, I would like to request your inputs on the following use-case in AWS. I am new to ZScaler so I might be lacking some fundamental knowledge around the offerings.

My team wants to use both Checkpoint Firewall and ZScaler Inspection for internet egress traffic. I have the following questions:

Is this a common architecture, to have both Checkpoint and ZScaler in the internet egress path on AWS? Initial inspection by Checkpoint and then forwarded to ZScaler? In the past I had come across usecases where either of these products were used for egress in AWS, but

not both

I read about ZScaler Cloud Connector offering for AWS. How is this different from the tunnels that my team has setup (to ZScaler service edge) in the on-premise setup, through the edge WAN router.

Will there be tunnels established between Checkpoint security gateways in AWS and ZScaler Cloud Connectors (in AWS), or is there a better way to deal with this?

If ZScaler Cloud Connectors are not used, how can the traffic be forwarded from Checkpoint to ZScaler?

Are there some reference architecture around such a setup?

Thanks!

ZIA - Cloud Firewall

3 answers

336 views

Abu Bakar

(Partner)

a year ago

Greeting @Akshay Kapoor​ ,

Let me simplify you this, but need few more details what kind of tunnel is implemented in your On-Premise as this will help me to make you understand the architecture, the below is traffic forwarding method which is use to deploy Zscaler in an Organization.

1) On-Premise Setup

In the traffic is forwarded via IPSec or GRE Tunnel, this is nothing but a Site-to-Site VPN

Though Zscaler recommends GRE tunne. However ,most Organization goes with IPSec as this comes with encryption and enhance Secuirty.

There might be a tunnel configured in your on-premise where your edge router or FW had either GRE or IPSec which forward all the organization traffic via this tunnel.

Note here traffic is egress from your edge router/FW towards Zscaler Data Centers there will be 2 tunnel as per Zscaler recommendation for failover and redundancy,

2) Zscaler Cloud Connector

As a said earlier Zscaler Cloud Connector is a VM template which have baked-in Zscaler Service.

The key difference here is that the Cloud Connector will be behind your FW or Edge router in your VPC(Virtual Private Cloud)

This Solution was introduced by Zscaler as Organisation were facing challenge as they need to route their North-South traffic to their VPC to their data center and route the traffic from the edge router/FW to Zscaler data center as this create a major complexity and additonal hops to send traffic to zscaler and than the destination end.

Most of the time the Destination Server will refuse to whitelist Zscaler Public Service Edges due to their secuirty configuration or any kind of techincal limitation.

But they can whitelist a single Public IP where the traffic is originate.

Zscaler Cloud connector share the single public IP behind your FW NAT service.

This benefit as there is no need to add extra routes to organization data center  and that egress traffic from there.

Zscaler Cloud Connector will be behind your Cloud FW here Checkpoint and there will be NAT policy to route that traffic from the cloud itself. no need to create additional routes.

Hope this Answer your question.

Regards

Abubakar Khan

Ben_Garrison

(Employee)

a year ago

I went and asked around for some clarity. Aaron Royans, Sr. Principal Solutions Architect (which we will have a podcast real soon with a special guest ) wanted to provide some insights.

Is this a common architecture, to have both Checkpoint and ZScaler in the internet egress path on AWS? Initial inspection by Checkpoint and then forwarded to ZScaler? In the past I had come across usecases where either of these products were used for egress in AWS, but

not both

AR>> It's not terribly common, but also not uncommon either. There are two primary use-cases we see this type of architecture:

A customer has traffic

from

AWS destined

to

AWS (i.e… local traffic) that they want inspected. Zscaler can provide inspection with ZPA, but generally this means you have to send the traffic to our cloud (unless you get fancy with local Private Service Edges). In this case, some customers opt to just run a Firewall to provide local inspection so that traffic doesn't have to leave. Any traffic destined to the Internet (or outside of AWS in general) goes through Zscaler.

A customer wants to do SSL inspection on Internet-bound traffic. Generally speaking, SSL inspection is very CPU-taxing on a standalone appliance. You end up having to spend more $$ on a beefier box to support X amount of SSL throughput with inspection. For this reason, some customers opt to provide preliminary inspection with a local FW, then leave SSL decrypt/encrypt to our cloud so that they don’t incur the CPU tax.

I read about ZScaler Cloud Connector offering for AWS. How is this different from the tunnels that my team has setup (to ZScaler service edge) in the on-premise setup, through the edge WAN router.

AR>> There are several answers to this question:

The idea behind

Cloud Connector

is to remove the administrative burden required with maintaining a connection to the Zscaler cloud. You no longer need to worry about tunnel redundancy/failover or manually building VPNs. The CC appliance takes care of all of that. It is offered in two flavors:

as-a-Service

and as a self-managed appliance. In both options, CC automatically builds redundant tunnels to the nearest / best-performing data centers and manages the failover between them. You can have 1 or more appliances (or leverage the as-a-Service option) to meet your business needs.

Traditional VPN tunnels like IPsec or GRE have a cap on them of 500Mbps - 1Gbps. You would also need to manage failover and load-balancing across them in order to scale out, when necessary. On the other hand, Cloud Connector has very high scale when compared to IPsec / GRE since it is front-ended by AWS Gateway Load Balancer - meaning, you can have several appliances in the load-balancing pool and scale out on-demand almost infinitely to meet your organization's bandwidth needs.

Cloud Connector fully supports both ZIA

and ZPA

. At present, IPsec / GRE only support ZIA with some support for business-to-business use-cases for ZPA.

Will there be tunnels established between Checkpoint security gateways in AWS and ZScaler Cloud Connectors (in AWS), or is there a better way to deal with this?

AR>> It would be recommended to use the Cloud Connector as the 'gateway' to Zscaler and discontinue your IPsec / GRE VPNs from Checkpoint. When traffic needs Zscaler inspection, you would simply default-route from the Checkpoint to the Cloud Connector.

If ZScaler Cloud Connectors are not used, how can the traffic be forwarded from Checkpoint to ZScaler?

AR>> You will have to use IPsec or GRE based VPNs from your Checkpoint or load PAC files on your workloads/devices in AWS.

Are there some reference architecture around such a setup?

AR>> Yes here...

https://help.zscaler.com/downloads/cloud-connector/reference-architecture/zero-trust-[…]-zscaler-cloud-connector/Zero-Trust-Security-AWS-Zscaler.pdf

and here...

https://help.zscaler.com/downloads/zscaler-technology-partners/b/zscaler-and-aws-deployment-guide/Zscaler-AWS-Deployment-Guide-FINAL.pdf

JB

(Partner)

a year ago

is it common to have both check point and zscaler in aws egress path?

it's not typical, but technically possible and sometimes preferred when:

you need to enforce zone-specific or contextual policies in aws using check point (e.g, east-west inspection, nat, segmentation).

then offload full internet security stack (ssl inspection, threat intelligence, dlp, etc.) to zscaler.

however, this increases complexity and introduces potential latency unless well-designed.

2. zscaler cloud connector vs on-prem tunnel:

zscaler cloud connector

a lightweight virtual appliance (deployed in aws/azure/gcp).

automatically establishes ipsec tunnels to zscaler public service edges (or private service edge if used).

it acts as a "zscaler tunnel proxy" for workloads in the cloud (no client connector/zcc agent needed).

integrates with aws vpc routing and enis to steer egress traffic from specific subnets.

difference from on-prem tunnel via wan router:

on-prem tunnels are manual and static, typically using edge routers or firewalls.

cloud connector is cloud-native, scalable, integrated with cloud workloads, and auto-heals/auto-scales.

3. can check point in aws forward traffic to zscaler cloud connector?

no direct tunnel is created between check point gw and cloud connector, because:

cloud connector is meant for receiving routed traffic from vpcs/subnets, not firewall peers.

check point can, however, forward traffic to zscaler public service edges using gre/ipsec tunnels, just like on-prem setup.

so, either:

option a: let cloud connector manage zscaler tunneling, and route all subnet traffic via cloud connector.

option b: route egress from check point via gre/ipsec tunnel directly to zscaler (public service edge).

note: zscaler doesn’t support check point → cloud connector tunnel setup as a native integration.

4. if not using cloud connector, how to forward traffic from check point to zscaler?

you can:

set up a gre or ipsec tunnel from check point (in aws) directly to the nearest zscaler public service edge.

nat traffic appropriately.

apply domain-based or subnet-based policies on check point.

forward only internet-bound traffic to zscaler (0.0.0.0/0) using custom routing and nat rules.

Log In to Answer

Associated Tags

aws

cloudfirewall

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2018

at

01:07 PM

Ip address as criteria in url policy

ZIA - Cloud Firewall

ram75

1,190

1190 Views

0 Likes

1 Comment

10/8/2020

at

08:41 AM

Block All access & Allow certain user or group (ZIA)

ZIA - Cloud Firewall

Sec_def_Def_sec

1,818

1818 Views

1 Like

2 Comments

12/19/2022

at

04:14 PM

URL filtering policy vs Cloud App policy control

ZIA - Cloud Firewall

Ahmed

4 Views

0 Likes

2 Comments

12/9/2022

at

09:40 PM

Apple News RSS Feed

ZIA - Cloud Firewall

Trace Woodbury-RidgeIT

2 Views

0 Likes

1 Comment

9/20/2022

at

03:20 PM

How does Zscaler Internet Access itself route the traffic to the internet, using what outgoing/next hop GW

ZIA - Cloud Firewall

tamerz

6 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Checkpoint and ZScaler in AWS
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000bpUzh0AE/chatgpt-resstriction","lastmod":"2025-07-28T12:57:37.000Z","id":"0D5PJ00000bpUzh0AE"} -->
## ChatGPT Resstriction

- Source: https://community.zscaler.com/s/question/0D5PJ00000bpUzh0AE/chatgpt-resstriction
- Type: Q&A
- Last activity: 2025-07-28T12:57:37.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

Deepak474

(Partner) asked a question.

July 25, 2025 at 8:11 AM

ChatGPT Resstriction

Is it possible to restrict end users to access ChatGPT only with organizational credentials and block personal credentials?

ZIA - URL Filtering

3 answers

577 views

JB

(Partner)

a year ago

Yes, this can be achieved by configuring a tenant profile for ChatGPT and mapping it under the Cloud Application Control policies

Abu Bakar

(Partner)

a year ago

Hi @DEEPAK PAL​ ,

If you have an Zscaler DLP and policy inspection you can achieve same with DLP by creating a custom dictionary where you can specify the your organization domain. this is an alternative method to get the same result.

Regards.

Ramesh Mani

(Partner)

a year ago

https://help.zscaler.com/zia/adding-tenant-profiles#tr-chatgpt

- Create the Tenant and apply this in your cloud app policy for chatGPT.  This is straight forward.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,916

5916 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

888

888 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

ChatGPT Resstriction
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000c6rdG0AQ/e911-and-zscaler","lastmod":"2025-08-05T20:11:34.000Z","id":"0D5PJ00000c6rdG0AQ"} -->
## E911 and zScaler

- Source: https://community.zscaler.com/s/question/0D5PJ00000c6rdG0AQ/e911-and-zscaler
- Type: Q&A
- Last activity: 2025-08-05T20:11:34.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

dpsusp

(Customer) asked a question.

July 30, 2025 at 1:19 PM

E911 and zScaler

Has anyone implemented E911 with MS Teams Direct Routing with zScaler? App bypass is turned on for MS Teams, but the public IP associated with the device is still intermittently reporting the zScaler public IPs instead of our actual public IP for trusted networks to MS. Is there some workaround for this besides tracking all the public IPs from zScaler.

ZIA - Forwarding

3 answers

268 views

Ramesh Mani

(Partner)

a year ago

You can create domain and IP bypasses in PAC file and tag under app profile.

JB

(Partner)

a year ago

Microsoft also doesn't recommend using cloud proxies in the path of Teams E911

dpsusp

(Customer)

a year ago

Thank you for the information.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

492

492 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

E911 and zScaler
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000cEWWN0A4/zdta-exam-prep-help-needed","lastmod":"2026-01-30T02:35:14.000Z","id":"0D5PJ00000cEWWN0A4"} -->
## ZDTA Exam Prep - Help Needed

- Source: https://community.zscaler.com/s/question/0D5PJ00000cEWWN0A4/zdta-exam-prep-help-needed
- Type: Q&A
- Last activity: 2026-01-30T02:35:14.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Certification

stephanieculver

(Customer) asked a question.

August 1, 2025 at 5:04 AM

ZDTA Exam Prep - Help Needed

Hi everyone,

I am planning to take the

ZDTA - Zscaler Digital Transformation Administrator

certification exam, and I’m looking for the best way to prepare for it. Since this is a relatively specialized certification, I want to make sure I’m using the right study materials and focusing on the key topics that are likely to be covered on the actual exam.

If you’ve taken the exam or are currently preparing for it, I’d really appreciate your help. Specifically:

What are the most important topics to focus on?

Are there any official guides, study material, or online courses you would recommend?

Can anyone suggest a good practice test or question bank to get a better feel for the exam format?

Any personal tips or strategies that helped you pass the exam?

I’m serious about preparing effectively, so any guidance, whether it’s about hands-on experience, resources, or general advice, would be a huge help.

Certification

2 answers

1.16K views

Ramesh Mani

(Partner)

a year ago

You will have a blueprint for ZTDA certification when you enroll it. That will help you to see the exact quantification for each topic within Zscaler ZTDA Syllabus

Jerry P. Gurrea

(Customer)

8 months ago

I passed it last year and eLearning help me a lot. Answer all those quizzes until you are 100% confident. Doing the eLabs is a good memory retaining tool but best of all is your access to ZPA, ZIA and ZDX and understanding how all components work.

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

1/12/2024

at

04:57 AM

ZIA administrator certification

Certification

User16739629112679781823

1,212

1212 Views

0 Likes

2 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

ZDTA Exam Prep - Help Needed
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000cQ1yx0AC/zscaler-alert-how-do-you-response-to-alerts","lastmod":"2025-08-07T11:24:26.000Z","id":"0D5PJ00000cQ1yx0AC"} -->
## Zscaler Alert - How do you response to Alerts?

- Source: https://community.zscaler.com/s/question/0D5PJ00000cQ1yx0AC/zscaler-alert-how-do-you-response-to-alerts
- Type: Q&A
- Last activity: 2025-08-07T11:24:26.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

miguel.villaruel

(Customer) asked a question.

August 5, 2025 at 3:25 AM

Zscaler Alert - How do you response to Alerts?

What is your best practice or how do you response to security and UEBA alerts?

ZIA - URL Filtering

1 answer

321 views

Ramesh Mani

(Partner)

a year ago

Once the alerts are triggered, do validate if its true positive, do detailed investigation before /after isolate / contain the user traffic.  Also end user coaching and alert tuning also needed to avoid false positives.

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,922

5922 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

893

893 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Zscaler Alert - How do you response to Alerts?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000cZkox0AC/disable-zia-for-a-group","lastmod":"2025-08-15T07:23:15.000Z","id":"0D5PJ00000cZkox0AC"} -->
## Disable ZIA for a group

- Source: https://community.zscaler.com/s/question/0D5PJ00000cZkox0AC/disable-zia-for-a-group
- Type: Q&A
- Last activity: 2025-08-15T07:23:15.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

Diode

(Customer) asked a question.

August 7, 2025 at 6:58 AM

Disable ZIA for a group

Hi ZIA is enabled for all users but I have developer group I need ZIA to be disabled. Currently I have a app profile and set reactive ZIA after 0 minutes. Any other way we can do this pls

ZIA - Forwarding

9 answers

516 views

Arvid22

(Customer)

a year ago

Best way to do it is to make a app profile higher then the one for all other people.

use a group in ZiA to house the developers. or if you have the group already use that in ZCC Console. (group has to be in ZiA to be consumable in ZCC Console)

Make a new App-Profile for Developers by using the Group

Make a new Forwarding profile

Under Forwarding Profile Action for ZiA, you can set it to None for On-Trusted, VPN & Off-Trusted.

You can play more with this by using defining what your trusted network is.

https://help.zscaler.com/zscaler-client-connector/configuring-trusted-networks-zscaler-client-connector

Tie the App Profile to the Forwarding Profile

just be careful to not be too forgiving to Developers. they are a risky userbase. you def want ZiA enabled if they work with 3th party repo's etc.

Diode

(Customer)

a year ago

Thanks Arvid22 I know what you are saying about developers 😂 but ZIA is been a pain for me with them. ZIA is like Marmite you like or hate it lol. Cheers

1 like

Ramesh Mani

(Partner)

a year ago

You can create separate APP profile for Developer group and disable ZIA for them.

1 like

Ramesh Mani

(Partner)

a year ago

You can create separate APP profile for Developer group and disable ZIA for them using a forward profile with ZIA forwarding action none.

1 like

Ramesh Mani

(Partner)

a year ago

Arvid22

- Have you got chance to check this ? Let me know if further discussion needed on this otherwise mark it solution.

Arvid22

(Customer)

a year ago

im not the requestor sir. 😀

djramous

(Partner)

a year ago

I assume the issue is SSL inspection. If that assumption is correct you could bypass SSL inspection for the group AND specific cloud apps like Github. There is also documentation about how to add the ZS root certificate to many popular developer tools that could help alleviate some of the pressure to disable security entirely.

https://help.zscaler.com/zia/adding-custom-certificate-application-specific-trust-store

1 like

Fileno

(Partner)

a year ago

Likely, the challenge here with the Developers is SSL inspection and you can bypass specific Applications | Domains using SSL inspection policies.

Note that disabling ZIA will removed the ATP Protection for your Developers. We have seen cases where a ransomware is injected on some Github projects. The best practices is to insert Zscaler certificate on Development Apps on Java, NPM, Phyton, etc and where is possible, but also you chose bypass SSL.

1 like

Diode

(Customer)

a year ago

Cheers for all your help 😁

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

490

490 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Disable ZIA for a group
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000cqhBZ0AY/team-viewer-freeze-for-a-short-while","lastmod":"2025-08-19T13:25:31.000Z","id":"0D5PJ00000cqhBZ0AY"} -->
## Team Viewer freeze for a short while

- Source: https://community.zscaler.com/s/question/0D5PJ00000cqhBZ0AY/team-viewer-freeze-for-a-short-while
- Type: Q&A
- Last activity: 2025-08-19T13:25:31.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

Diode

(Customer) asked a question.

August 12, 2025 at 2:07 PM

Team Viewer freeze for a short while

Team Viewer freezes when restart services on ZCC

But MS Teams sharing is fine & don't freeze

How can I add a bypass for Team Viewer pls

Our desktop support staff uses Team Viewer to help remote users

ZIA - URL Filtering

4 answers

261 views

Ramesh Mani

(Partner)

a year ago

Go to Administration  - Application bypass  -  process bypass and create bypass for team viewer

And call out this process in APpp profile, process bypass under global bypass configuration.

1 like

Diode

(Customer)

a year ago

Thanks I already have 1st rule but now I have added 2nd and testing now

TeamViewer - Bypass 1

C:\Program

Files (x86)\TeamViewer\TeamViewer.exe

None

TeamViewer - Bypass 2

C:\Program

Files (x86)\TeamViewer\TeamViewer_Desktop.exe

None

Fileno

(Partner)

a year ago

If are using Tunnel 2.0, you may have to create a Firewall Rule Allowing TeamViewer Ports (TCP/UDP) and if there are SSL handshake errors, you may have to bypass TeamViewer App. Alos, you can configure for Windows | macOS an Application bypass as described by Ramesh.

1 like

Diode

(Customer)

a year ago

Reply form ZS support

After consulting internally we came to the conclusion that this behavior (freezing of the TeamViewer session briefly) is most likely expected considering how ZCC interacts with the underlying operating system when using the "Restart Service" option.

Restarting ZCC causes the network settings on the system to change which in-turn causes interruption of the opened network connections. Each app handles such disruptions differently, some of them more gracefully than others. In this case TeamViewer reacts by freezing briefly while it tires to restore the session considering the network adapter switch happening in the background.

I would also advice contacting TeamViewer support and explaining the situation to them, maybe there is something you can do on the TeamViewer side to handle internet changes better. There is also a option for them to provide you with a list of either domains or IP ranges so we can bypass them from Zscaler as a alternative for the process-based bypass

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,933

5933 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

896

896 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Team Viewer freeze for a short while
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000cqmZd0AI/outlook-connecting-to-the-server-to-retrieve-new-emails","lastmod":"2025-08-14T08:48:15.000Z","id":"0D5PJ00000cqmZd0AI"} -->
## Outlook connecting to the server to retrieve new emails

- Source: https://community.zscaler.com/s/question/0D5PJ00000cqmZd0AI/outlook-connecting-to-the-server-to-retrieve-new-emails
- Type: Q&A
- Last activity: 2025-08-14T08:48:15.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

Diode

(Customer) asked a question.

August 12, 2025 at 2:12 PM

Outlook connecting to the server to retrieve new emails

Few users seems to be having a lot of issues with outlook connecting to the server to retrieve new emails or search for old ones. If I turn off ZIA it works and this is an issue for only few users. Any help pls

ZIA - URL Filtering

2 answers

365 views

Ramesh Mani

(Partner)

a year ago

Look at web insights logs and see if there is any block.  See if there is any URL needs to auth or SSL bypass.

Use one click 0365 configuration if O365 is  the mail server.

ozanogur

(Partner)

a year ago

Do you use Exchange Online ?

If so, do you have  M365 One Click Policy enabled ?

Do you see any blocks/drops in web insights towards Exchange Online application in ZIA ?

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,900

5900 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

881

881 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Outlook connecting to the server to retrieve new emails
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000d2YWZ0A2/sipa-ticketmaster","lastmod":"2025-08-18T13:21:58.000Z","id":"0D5PJ00000d2YWZ0A2"} -->
## SIPA - ticketmaster

- Source: https://community.zscaler.com/s/question/0D5PJ00000d2YWZ0A2/sipa-ticketmaster
- Type: Q&A
- Last activity: 2025-08-18T13:21:58.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

DooZscaler

(Customer) asked a question.

August 15, 2025 at 12:30 AM

SIPA - ticketmaster

Hello Team,

We are currently facing challenges with Ticketmaster in our environment because they do not whitelist Zscaler IP addresses.

At present, we use several applications for Ticketmaster access.

One of these involves an RDP application, and we have started experiencing SIPA-related issues, including random disconnects and problems with SIPA's three-leg handshake process.

Has anyone encountered similar issues or have suggestions to help address the SIPA connectivity problems?

ZIA - Forwarding

2 answers

312 views

Fileno

(Partner)

a year ago

SIPA steers selective traffic processed by ZIA to the internal or external destination servers of choice. This ensures that Zscaler secures the traffic and that the source IP address is the organization's choice.

When using SIPA, the traffic is steered via the IP address defined by your organization, and traffic should arrive at Ticketmaster with your organization IP address and not Zscaler IP Ranges. Likely, part of traffic is going via ZIA and part via SIPA, but requires a troubleshooting to understand the scenario.

Ramesh Mani

(Partner)

a year ago

Your App connector (Public IP / internet NAT IP) Whitelisted at Ticketmaster end?

Validat configuration,

https://help.zscaler.com/zia/configuring-source-ip-anchoring

Collect logs and ensure logs are forwarding from ZIA to ZPA and ZPA process the traffic without any issues.

Log In to Answer

Associated Tags

accesspolicy

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

490

490 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

SIPA - ticketmaster
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000d4fHt0AI/feature-request-improve-web-insights-log-search","lastmod":"2025-09-03T11:05:44.000Z","id":"0D5PJ00000d4fHt0AI"} -->
## Feature Request: Improve Web Insights Log Search

- Source: https://community.zscaler.com/s/question/0D5PJ00000d4fHt0AI/feature-request-improve-web-insights-log-search
- Type: Q&A
- Last activity: 2025-09-03T11:05:44.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Logging

fran-naeby1

(Customer) asked a question.

August 15, 2025 at 3:40 PM

Feature Request: Improve Web Insights Log Search

What is the deal with filtering options in the Web Insight Logs?

Can't do an include and exclude conditions together.

Can't keep it open and open another tab to go make policy adjustments, it resets the search.

Can't save the search parameters that we use frequently.

Doing an initial setup of ZIA and find a lot of things being blocked. Totally makes sense but trying to find what the problem is and get it fixed is an extremely painful process and it mostly centers around the Web insights logs.

A simple search of urls blocked for a single users for 1 day is difficult to clean up because once you have those parameters set I can't filter out the junk or things I've already delt with, just have to manually sift through it all.

This really needs to be better.

ZIA - Logging

4 answers

305 views

Anamika_R

and

jzsaiz

like this.

Fileno

(Partner)

a year ago

For "Can't keep it open and open another tab to go make policy adjustments, it resets the search."

Try to use duplicate the ZIA | ZPA Browser tab as a workaround.

Osho_Dwivedi

(Employee)

a year ago

Please use the log streams to the the SIEM for searching the logs effectively.

As for the Feature Request, get in touch with your Sales Engineer to file or update the Enhancement Requests on Zscaler side.

1 like

mahantr

(Customer)

a year ago

We have been a Zscaler customer for ~7 years and have complained about the web insights reporting. Unfortunately, we have been getting the same answer - use a SIEM. It would be so nice if we could do this searching and filtering right in the tool!

Ramesh Mani

(Partner)

a year ago

SIEM is the right tools for better correlation and better view as we can use it for all tools (central place). But you are correct, Web insights don't much option as other similar tools .:)

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

6/8/2023

at

08:51 PM

URL Reporting

ZIA - Logging

akita_down

12

12 Views

0 Likes

4 Comments

11/3/2023

at

06:08 PM

ZIA - NSS Feed Status

ZIA - Logging

Rallis

618

618 Views

0 Likes

3 Comments

2/21/2024

at

10:43 PM

Find if User is behind a VPN

ZIA - Logging

venG

544

544 Views

0 Likes

2 Comments

3/13/2025

at

01:56 AM

How to Extract Logs from Web Insight

ZIA - Logging

JSK27

672

672 Views

0 Likes

8 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Feature Request: Improve Web Insights Log Search
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000dIyVy0AK/forwarding-traffic-to-vse","lastmod":"2025-08-25T03:31:23.000Z","id":"0D5PJ00000dIyVy0AK"} -->
## Forwarding Traffic to VSE

- Source: https://community.zscaler.com/s/question/0D5PJ00000dIyVy0AK/forwarding-traffic-to-vse
- Type: Q&A
- Last activity: 2025-08-25T03:31:23.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

mayankg

(Partner) asked a question.

August 20, 2025 at 4:43 AM

Forwarding Traffic to VSE

I have deployed ZIA Virtual Service Edge to the VMware ESXi, and configured the network, firewall and all required configurations.

Then I made PAC file to forward traffic to VSE but it doesn't working.

Please help me in this, how can i forward traffic to VSE

ZIA - Forwarding

1 answer

255 views

Ramesh Mani

(Partner)

a year ago

You can forward the logs to VSE using PAC file, GRE / IPSec ,  and L2 redirect,

https://help.zscaler.com/zia/forwarding-traffic-virtual-service-edges

This would help you.

Sniff the packet at VSE end if still failing.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

489

489 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Forwarding Traffic to VSE
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000dRkfP0AS/zs-uk-ip-whit-listing-for-our-web-apps","lastmod":"2025-09-01T05:03:41.000Z","id":"0D5PJ00000dRkfP0AS"} -->
## ZS (UK) IP whit listing for our web apps

- Source: https://community.zscaler.com/s/question/0D5PJ00000dRkfP0AS/zs-uk-ip-whit-listing-for-our-web-apps
- Type: Q&A
- Last activity: 2025-09-01T05:03:41.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

Diode

(Customer) asked a question.

August 22, 2025 at 8:42 AM

ZS (UK) IP whit listing for our web apps

Hi we have some websites and we like to add some IP whit listing on our web server

All our users are in the UK so what ZS IPs I need to whitelist pls ? Or can I add the below to VPN Gateway Bypass and whitelist our PAT address ?

Please advise

ZIA - URL Filtering

1 answer

436 views

Arun

(Partner)

10 months ago

Hi Diode,

You'll get ips from

config.zscaler.com

& you can whitelist using that.

Regards,

Arun

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,909

5909 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

888

888 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

ZS (UK) IP whit listing for our web apps
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000dSuMJ0A0/zscaler-passing-hostname-instead-of-userid-to-proxy","lastmod":"2025-11-03T16:37:43.000Z","id":"0D5PJ00000dSuMJ0A0"} -->
## zscaler passing hostname instead of userid to proxy

- Source: https://community.zscaler.com/s/question/0D5PJ00000dSuMJ0A0/zscaler-passing-hostname-instead-of-userid-to-proxy
- Type: Q&A
- Last activity: 2025-11-03T16:37:43.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Authentication

francisirizarry

(Customer) asked a question.

August 22, 2025 at 3:24 PM

zscaler passing hostname instead of userid to proxy

All, Current machine specs are win10/11. i9 /Workstation class machines / 64 gig of ram. Zscaler ver 4.6.2 / 4.5.8. We are seeing randomly zscaler is passing machine hostname instead of userid to the proxy for authentication. We have been struggling for this for about 6 months. Its a random occurrence on workstations. The only way to resolve permanently is to remove zscaler. I have tried disabling NCSI active probing / and changing the timeout of wsa - servicepipetimeout to lengthen the time for the machine to request info from the proxy in order for zscaler to catch up. Thinking zscaler is throwing hostname/userid but it should be submitting domain/userid to the proxy. Any Ideas?

ZIA - Authentication

1 answer

640 views

ceminaycem177

(Customer)

8 months ago

Hello

Could you find any solution? I will be glad if you can also share the solution.  ​

Log In to Answer

Associated Tags

certification

accesspolicy

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/15/2022

at

12:39 PM

Onprem Active directory integrate with Zscaler cloud ZIA

ZIA - Authentication

aravindhan.m

3 Views

0 Likes

3 Comments

7/11/2022

at

07:26 AM

Zscaler IDP authentication issue

ZIA - Authentication

aravindhan.m

4 Views

0 Likes

2 Comments

5/27/2022

at

01:49 PM

Zscaler ZIA O365 Apps not authenticating

ZIA - Authentication

abmaclean

4 Views

0 Likes

6 Comments

10/4/2021

at

10:43 PM

Automatic de-provisioning using SCIM with Azure AD

ZIA - Authentication

Paul_Wineberg

4 Views

0 Likes

4 Comments

6/27/2020

at

03:01 PM

Zscaler public preview

ZIA - Authentication

Muhammad

364

364 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

zscaler passing hostname instead of userid to proxy
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000da7uJ0AQ/wetransfer-not-blocked-with-the-cloud-app","lastmod":"2025-10-07T18:47:02.000Z","id":"0D5PJ00000da7uJ0AQ"} -->
## WeTransfer not blocked with the cloud app

- Source: https://community.zscaler.com/s/question/0D5PJ00000da7uJ0AQ/wetransfer-not-blocked-with-the-cloud-app
- Type: Q&A
- Last activity: 2025-10-07T18:47:02.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - DLP

NicoMeijni

(Customer) asked a question.

August 25, 2025 at 2:21 PM

WeTransfer not blocked with the cloud app

Hello,

I allow viewing but block uploading for the entire category of cloud app file hosting.

In this list, WeTransfer is indeed included.

However, uploading is still possible on WeTransfer, do you know why ?

When I noticed this, I also tested Google Drive, Dropbox, etc., and uploading is correctly blocked there.

This is quite problematic.

Thanks for your help.

ZIA - DLP

2 answers

569 views

Fileno

(Partner)

a year ago

Start looking if SSL inspection is happening for the test user / App you are looking.

SSL inspection is basic requirement for ZIA acting as expected.

Miguel_Faldutti

(Partner)

9 months ago

Hi!

Is it still happening?

If the answer is yes: How do you configure the policy? Is it the same for all the Online Storage category? Or do you have one for Cloud App?

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/9/2023

at

05:32 PM

DLP notification via Zscaler Client Connector

ZIA - DLP

thacarvalho123

7 Views

2 Likes

2 Comments

11/20/2022

at

09:26 PM

External DLP Engine

ZIA - DLP

H.zyD.zy

4 Views

0 Likes

3 Comments

11/9/2022

at

04:51 PM

Zscaler Preventing Image Upload and Download on iPhone

ZIA - DLP

MacDanorld1

2 Views

0 Likes

3 Comments

6/20/2022

at

07:53 AM

Error during Microsoft Information Protection(MIP) Integration

ZIA - DLP

ozanogur

3 Views

0 Likes

4 Comments

1/31/2021

at

08:35 PM

Zscaler ZIA and ZPA with NetSkope CASB and DLP services

ZIA - DLP

jonathan.holt

1,338

1338 Views

1 Like

6 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

WeTransfer not blocked with the cloud app
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000e4WrF0AU/dlp-in-zscaler","lastmod":"2025-09-04T12:02:28.000Z","id":"0D5PJ00000e4WrF0AU"} -->
## DLP in ZScaler

- Source: https://community.zscaler.com/s/question/0D5PJ00000e4WrF0AU/dlp-in-zscaler
- Type: Q&A
- Last activity: 2025-09-04T12:02:28.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Data Classification

TheEnd

(Customer) asked a question.

September 2, 2025 at 10:34 PM

DLP in ZScaler

In working with ZScaler, when we set a DLP policy we get the words for that DLP policy but not the tags, etc we're looking for. Has anyone else had this issue, and if so, how did you fix it?

Data Classification

3 answers

404 views

Maarten van den Bogaard

(Partner)

10 months ago

Hi there! Zscaler DLP may detect content but not metadata like tags or labels unless properly integrated. If for example you're referring to Microsoft Information Protection labels, could you clarify whether those are the "tags" you're expecting? That may help narrow down the fix.

TheEnd

(Customer)

10 months ago

Hi Maarten, to begin, we in IT will have to add these labels to any Controlled Information we're looking to have a DLP policy on. Is there a best policy for this? Meaning, does ZScaler perform with one label better over another? I remember with working from Sophos this was the case.

Basically there's a small selection of information we're trying to protect.

Maarten van den Bogaard

(Partner)

10 months ago

In my experience Zscaler works flawlessly with MIP labels if your integration and DLP policy is setup correctly. Once you have MIP integrated with Zscaler you can import labels used on your sensitive data and create DLP Dictionaries with them to use in your DLP Engines, which you can then create a policy with. Keep in mind that SSL inspection must be performed on your traffic for this to work.

You can follow the documentation here which I expect should have everything you need:

Labels and Tags | Zscaler

With Zscaler Client Connector you can also extend DLP to the endpoint, more info on that here:

About Endpoint Data Loss Prevention | Zscaler

I hope this answers your question. If so please mark the answer as best, thank you.

Log In to Answer

Associated Tags

automation

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

No posts to show.

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

DLP in ZScaler
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000e5ro30AA/dlp-incident-receiver-in-azure","lastmod":"2025-09-15T04:09:55.000Z","id":"0D5PJ00000e5ro30AA"} -->
## DLP Incident Receiver in Azure

- Source: https://community.zscaler.com/s/question/0D5PJ00000e5ro30AA/dlp-incident-receiver-in-azure
- Type: Q&A
- Last activity: 2025-09-15T04:09:55.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Analytics

luca.dallaturca

(Partner) asked a question.

September 3, 2025 at 9:21 AM

DLP Incident Receiver in Azure

Hi,

One of my customer is trying to deploy the DLP Incident Receiver into its Azure tenant. He does face problems by doing it following the instructions from the online help (case open with Zscaler for it).

It looks like there would be a different way to create it and which is not documented by Zscaler online help : create the DLP Incident receiver directly from the Azure Marketplace.

Would someone have already deployed successfully the receiver from the marketplace ? Any showstopper ?

Thanks in advance for your help :)

Luca

Analytics

1 answer

277 views

Ramesh Mani

(Partner)

10 months ago

Please publish if you got any instructions from Zscaler support

Log In to Answer

Associated Tags

azure

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

9/24/2024

at

01:11 PM

Data Privacy - Sensitive Data storage and processing

Analytics

vaibhav_j

415

415 Views

0 Likes

1 Comment

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

DLP Incident Receiver in Azure
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000e7Or20AE/aws-ec2-instance-secure-access-via-zia","lastmod":"2025-09-09T13:18:06.000Z","id":"0D5PJ00000e7Or20AE"} -->
## AWS EC2 Instance Secure Access via ZIA

- Source: https://community.zscaler.com/s/question/0D5PJ00000e7Or20AE/aws-ec2-instance-secure-access-via-zia
- Type: Q&A
- Last activity: 2025-09-09T13:18:06.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

JoshuaEngracia

(Partner) asked a question.

September 3, 2025 at 5:32 PM

AWS EC2 Instance Secure Access via ZIA

Hi Zenith Community,

Good Day!

To give you a brief context, we have this AWS EC2 instance that we want to access via RDP and securely accessing it by using ZIA, but we don't know if there is a policy or module that we may utilize to accomplish this. so, the questions are,

1. Is there any possible way to do this? Is it doable? If yes, what ZIA module/feature that we may use to accomplish this?

2. Does ZIA can mask/customize a port? Since TCP port 3389 is for RDP service, is there a way to customize/mask this port 3389 as TCP 10001?

3. Also, one of the requirements is that only users that has Zscaler IP and the orgs ISP/public IP may be able to access the instance. We already tried to place the Zscaler IP and ISP IPs on the AWS side but still we may not be able to access it. So, is there any procedure or way to do this one?

If you have any documentation/procedure for this said concerns, just let us know. Thank you

ZIA - Forwarding

1 answer

412 views

Aaron_Rohyans

(Employee)

10 months ago

Hello -

Zscaler Internet Access (ZIA) does not currently provide an option that supports the type of inbound connectivity you’re describing. By design, ZIA is focused on securing and brokering outbound connections from users to the internet.

What you’re describing falls under Zscaler Private Access (ZPA) functionality, specifically a capability known as Privileged Remote Access (PRA). With PRA, Zscaler acts as the broker for access, authentication, and authorization to the target RDP machine. This is done through a secure web portal rather than exposing TCP/3389 (RDP) directly to the public internet or any untrusted networks.

Here’s how it works in practice:

A user authenticates into the Zscaler web portal (which we manage and expose to the outside world) and is validated against the organization’s policies.

Once permitted, an RDP session is established within the user’s web browser. This session is tunneled through ZPA, making it appear as if the user is already inside the corporate environment (so no need to manage trusted IP lists).

The end result is that the RDP machine can be reached securely without ever opening inbound firewall ports or directly publishing RDP services to the outside world.

For more detail, you can reference Zscaler’s overview of Privileged Remote Access here:

https://www.zscaler.com/zpedia/what-is-privileged-remote-access

Log In to Answer

Associated Tags

aws

accesspolicy

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

490

490 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

AWS EC2 Instance Secure Access via ZIA
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000eNZMr0AO/miscellaneous-as-a-cloud-application","lastmod":"2025-10-28T18:49:24.000Z","id":"0D5PJ00000eNZMr0AO"} -->
## Miscellaneous as a cloud application

- Source: https://community.zscaler.com/s/question/0D5PJ00000eNZMr0AO/miscellaneous-as-a-cloud-application
- Type: Q&A
- Last activity: 2025-10-28T18:49:24.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - CASB

Raj909

(Customer) asked a question.

September 8, 2025 at 4:35 PM

Miscellaneous as a cloud application

I've recently noticed the applications below show up as cloud applications when adding a filter within Web Insights. As you can see based on the name, these are not actual cloud applications or cloud application classes and more so categories. These also do not appear within the cloud applications search under Administration | Access Control, so wondering why they show up under the filter.

This causes an issue as they will appear in your reports or dashboard widgets when they should actually list out the actual cloud application - which is present in the logs.

• Miscellaneous AI & ML Apps

• Miscellaneous Productivity and CRM Tools Apps

• Miscellaneous Consumer Apps

• Miscellaneous Collaboration and Online Meetings Apps

• Miscellaneous File Sharing Apps

• Miscellaneous Finance Apps

• Miscellaneous Health Care Apps

• Miscellaneous Hosting Providers Apps

• Miscellaneous Human Resources Apps

• Miscellaneous Instant Messaging Apps

• Miscellaneous IT Services Apps

• Miscellaneous Legal Apps

• Miscellaneous Sales & Marketing Apps

• Miscellaneous Social Networking Apps

• Miscellaneous Streaming Media Apps

• Miscellaneous System & Development Apps

• Miscellaneous Web Mail Apps

• Miscellaneous Web Search Apps

ZIA - CASB

1 answer

364 views

Ankita Patel

likes this.

Armando

(Employee)

9 months ago

@Raj Khatri​  The data in the GenAI report typically appears 24 hours after it is visible in the Web Insight logs. This is due to a URL being associated with AI & ML Apps, where no corresponding cloud app exists yet. However, this classification is temporary as the URL will be fully categorized as part of the transition process, which usually takes a week to complete (Usually less than a week)

Log In to Answer

Associated Tags

cloud-app

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

4/6/2025

at

05:57 PM

Cloud App Control - Google Drive subactions

ZIA - CASB

Danielo

491

491 Views

0 Likes

7 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Miscellaneous as a cloud application
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000eWfxc0AC/local-ftp","lastmod":"2025-09-15T03:31:51.000Z","id":"0D5PJ00000eWfxc0AC"} -->
## Local FTP

- Source: https://community.zscaler.com/s/question/0D5PJ00000eWfxc0AC/local-ftp
- Type: Q&A
- Last activity: 2025-09-15T03:31:51.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Cloud Firewall

supply_peter

(Customer) asked a question.

September 10, 2025 at 4:19 PM

Local FTP

We are rolling out Zscaler to our organization. In one of our offices, we have a Konica Minolta MFP. The MFP is setup to FTP scanned documents to a user's Windows Workstation. The Windows endpoints have a Windows program called "FTP Utility" installed on them to receive the scanned documents. The MFP and endpoints are on the same VLAN- no firewalls, switches, routers between them. Before Zscaler, everything worked fine. Once we installed Zscaler, the FTP functionality "broke." If we disable ZIA, the MFP scan send scanned documents to endpoints without issue. So something in ZIA is blocking INCOMING FTP from a local MFP. I have been working with support on this, but they have been unable to identify the cause. We did follow this KB:

https://help.zscaler.us/zia/configuring-ftp-control-policy

. No change- FTP fails when Zia is enabled. I have done a packet capture, and can see that the traffic is blocked but there is no clear indication as to why.

One thought I had is that the Firewall Control policy in ZIA is blocking this incoming FTP traffic. However, I am not entirely clear on how to setup the firewall rule.

From the packet capture, the FTP server uses Port 21 and ports 45800-45900.

How should the Firewall Policy rule be setup to allow this traffic?

Is there another setting within ZIA that might be blocking this traffic?

Thanks.

ZIA - Cloud Firewall

1 answer

607 views

Ramesh Mani

(Partner)

10 months ago

Better to bypass this traffic as both are internal.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2018

at

01:07 PM

Ip address as criteria in url policy

ZIA - Cloud Firewall

ram75

1,190

1190 Views

0 Likes

1 Comment

10/8/2020

at

08:41 AM

Block All access & Allow certain user or group (ZIA)

ZIA - Cloud Firewall

Sec_def_Def_sec

1,818

1818 Views

1 Like

2 Comments

12/19/2022

at

04:14 PM

URL filtering policy vs Cloud App policy control

ZIA - Cloud Firewall

Ahmed

4 Views

0 Likes

2 Comments

12/9/2022

at

09:40 PM

Apple News RSS Feed

ZIA - Cloud Firewall

Trace Woodbury-RidgeIT

2 Views

0 Likes

1 Comment

9/20/2022

at

03:20 PM

How does Zscaler Internet Access itself route the traffic to the internet, using what outgoing/next hop GW

ZIA - Cloud Firewall

tamerz

6 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Local FTP
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000eZQCX0A4/adding-a-new-domain-in-the-zscaler-cloud-portal","lastmod":"2025-09-15T05:11:53.000Z","id":"0D5PJ00000eZQCX0A4"} -->
## Adding a New Domain in the Zscaler Cloud Portal

- Source: https://community.zscaler.com/s/question/0D5PJ00000eZQCX0A4/adding-a-new-domain-in-the-zscaler-cloud-portal
- Type: Q&A
- Last activity: 2025-09-15T05:11:53.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Logging

abolfazl.yadegari

(Customer) asked a question.

September 11, 2025 at 6:38 AM

Adding a New Domain in the Zscaler Cloud Portal

We have registered a new domain and would like to add it to the Zscaler cloud portal, so that the Zscaler login works with the new addresses.

ZIA - Logging

1 answer

959 views

JSK27

(Partner)

10 months ago

You can create a Zscaler ticket to add a new login domain.

Zscaler will request you to add a TXT record to verify ownership of the domain.

Once the domain ownership is verified, the domain will be added.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

6/8/2023

at

08:51 PM

URL Reporting

ZIA - Logging

akita_down

12

12 Views

0 Likes

4 Comments

11/3/2023

at

06:08 PM

ZIA - NSS Feed Status

ZIA - Logging

Rallis

613

613 Views

0 Likes

3 Comments

2/21/2024

at

10:43 PM

Find if User is behind a VPN

ZIA - Logging

venG

542

542 Views

0 Likes

2 Comments

3/13/2025

at

01:56 AM

How to Extract Logs from Web Insight

ZIA - Logging

JSK27

667

667 Views

0 Likes

8 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Adding a New Domain in the Zscaler Cloud Portal
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000eiP0c0AE/hashes-not-being-logged-for-zia-file-downloads","lastmod":"2025-09-15T04:34:40.000Z","id":"0D5PJ00000eiP0c0AE"} -->
## Hashes not being logged for ZIA file downloads

- Source: https://community.zscaler.com/s/question/0D5PJ00000eiP0c0AE/hashes-not-being-logged-for-zia-file-downloads
- Type: Q&A
- Last activity: 2025-09-15T04:34:40.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Logging

hukel

(Customer) asked a question.

September 13, 2025 at 3:57 PM

Hashes not being logged for ZIA file downloads

Is anyone getting file download hash values in your ZIA logs (aside from the sandbox MD5s)? It's hard to compare/search your ZIA logs with threat feeds and other IOCs if we don't have any hash values.

ZIA - Logging

1 answer

339 views

Paddy_Ganti

(Employee)

a year ago

Zscaler Internet Access (ZIA) primarily logs sandbox MD5s as part of its logging capabilities. If you’re not seeing other file hash types like SHA-1 or SHA-256, it’s likely because current logging focuses on sandbox-related activities, which may not include all hash types for all file downloads. For broader hash logging capabilities, consider reaching out to Zscaler support for any potential feature updates or configuration options that may align with your needs for comparing logs with threat feeds and other IOCs.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

6/8/2023

at

08:51 PM

URL Reporting

ZIA - Logging

akita_down

12

12 Views

0 Likes

4 Comments

11/3/2023

at

06:08 PM

ZIA - NSS Feed Status

ZIA - Logging

Rallis

618

618 Views

0 Likes

3 Comments

2/21/2024

at

10:43 PM

Find if User is behind a VPN

ZIA - Logging

venG

544

544 Views

0 Likes

2 Comments

3/13/2025

at

01:56 AM

How to Extract Logs from Web Insight

ZIA - Logging

JSK27

674

674 Views

0 Likes

8 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Hashes not being logged for ZIA file downloads
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000fG4CW0A0/2-cedges-with-latest-code-we-want-to-implement-3-ipsec-tunnel-on-one-cedge-and-6","lastmod":"2025-10-06T13:59:00.000Z","id":"0D5PJ00000fG4CW0A0"} -->
## 2 Cedges with latest Code we want to implement 3 IPSEC Tunnel on one cedge and 6

- Source: https://community.zscaler.com/s/question/0D5PJ00000fG4CW0A0/2-cedges-with-latest-code-we-want-to-implement-3-ipsec-tunnel-on-one-cedge-and-6
- Type: Q&A
- Last activity: 2025-10-06T13:59:00.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

Techlearner

(Customer) asked a question.

September 23, 2025 at 2:48 PM

2 Cedges with latest Code we want to implement 3 IPSEC Tunnel on one cedge and 6

2 Cedges with latest Code we want to implement 3 IPSEC Tunnel on one cedge and 6 on both cedegs.we want to configure active active tunnels. we are using these tunnels to access the internet. we are using sig services. I am familiar with 2 Tunnels on each edge with weight 50 command. but not sure How I will build 3 tunnels on each cedge.

need your expertise advise and help to complete the task

Client Connector

1 answer

234 views

Ramesh Mani

(Partner)

10 months ago

You may reach out cisco team for cedge configuration. At Zscaler end only two tunnels from a public IP,  accordingly architect the same.

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/7/2020

at

04:05 AM

Z-App -8 Network Error when users log in on Windows 10

Client Connector

cburge97

2,886

2886 Views

0 Likes

11

11 Comments

7/3/2020

at

11:55 AM

ZAPP intune deployment

Client Connector

Mk001

1,379

1379 Views

0 Likes

4 Comments

1/28/2021

at

03:42 PM

Compare ezAgent and ZCC - when to use which?

Client Connector

hukel

704

704 Views

0 Likes

2 Comments

8/18/2020

at

12:15 PM

MacOS Zscaler App Log Location

Client Connector

brad

3,575

3575 Views

0 Likes

1 Comment

3/7/2022

at

03:41 PM

Can a User with multiple devices use them simultaneously whilst logged in using that same single account

Client Connector

michael.makombe

4 Views

0 Likes

12

12 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

2 Cedges with latest Code we want to implement 3 IPSEC Tunnel on one cedge and 6
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000fq5XH0AY/when-to-configure-gre-tunnels","lastmod":"2025-10-06T22:08:13.000Z","id":"0D5PJ00000fq5XH0AY"} -->
## When to configure GRE Tunnels?

- Source: https://community.zscaler.com/s/question/0D5PJ00000fq5XH0AY/when-to-configure-gre-tunnels
- Type: Q&A
- Last activity: 2025-10-06T22:08:13.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

JM

(Partner) asked a question.

October 3, 2025 at 7:32 AM

When to configure GRE Tunnels?

We currently don't have GRE tunnels configured. And we have multiple nearby data centres:

NYC3, CHI1, CH2, DFW2, DFW1, SIN5, SIN4, MEL2

With this number of Data centres, we collect based on the logs, I think it will require us to have at least 5 GRE tunnels? Correct me if I'm wrong, because two GRE tunnels for each (?); primary and secondary.

Can anyone tell me if there is a specific business need to configure GRE tunnels? Because we have data centres located in MEL, CHI, DFW and NYC, and we are only having a trial first with Zscaler, which has a limited and short number of licenses.

'cause in my understanding, GRE tunnel is only advised to configure when you have clientless servers.

But personally, GRE tunnels will help us manage multiple locations, depending on the subnets and so on.

ZIA - Forwarding

3 answers

281 views

Top Rated Answers

Sachin Chauhan

(Partner)

10 months ago

Hi @Joanna Mae Villamor​ ,

GRE tunnels are typically required when you have

sites with users, guests, OT/IoT devices, or servers

that cannot use the

Zscaler Client Connector (ZCC)

. They are also recommended for

high-bandwidth locations (above 1 Gbps)

for better throughput and reliability.

If a site’s bandwidth is

below 250 Mbps

, either

IPsec or GRE

can be used. For medium sites (250 Mbps–1 Gbps), the choice depends on the firewall and design preference.

Each site usually has

two GRE tunnels

(primary and secondary) to different Zscaler data centers.

In your current case (trial with limited licenses and ZCC in use),

GRE tunnels are not required

. You can plan for them later when onboarding

data center or clientless traffic

Selected as Best

2 likes

All Answers

Ramesh Mani

(Partner)

10 months ago

GRE will need to Zscaler if you have sites with users, Guests, OT/IOT and Servers.

And most importantly if the site has more than 1GB, go with GRE.

If the site has  below 250MB, go with IPsec/ GRE tunnels.

For Users laptops or workstations only Go with ZCC agents.

If Zscaler have multiple DC nearby use WINMTR traces to look at the best destination and establish he tunnel towards the same.

1 like

Sachin Chauhan

(Partner)

10 months ago

Hi @Joanna Mae Villamor​ ,

GRE tunnels are typically required when you have

sites with users, guests, OT/IoT devices, or servers

that cannot use the

Zscaler Client Connector (ZCC)

. They are also recommended for

high-bandwidth locations (above 1 Gbps)

for better throughput and reliability.

If a site’s bandwidth is

below 250 Mbps

, either

IPsec or GRE

can be used. For medium sites (250 Mbps–1 Gbps), the choice depends on the firewall and design preference.

Each site usually has

two GRE tunnels

(primary and secondary) to different Zscaler data centers.

In your current case (trial with limited licenses and ZCC in use),

GRE tunnels are not required

. You can plan for them later when onboarding

data center or clientless traffic

Selected as Best

2 likes

JM

(Partner)

10 months ago

Thank you very much!!

Log In to Answer

Associated Tags

gre

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

491

491 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

When to configure GRE Tunnels?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000fxfcM0AQ/browser-isolation","lastmod":"2025-10-10T15:40:00.000Z","id":"0D5PJ00000fxfcM0AQ"} -->
## Browser Isolation

- Source: https://community.zscaler.com/s/question/0D5PJ00000fxfcM0AQ/browser-isolation
- Type: Q&A
- Last activity: 2025-10-10T15:40:00.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Browser Isolation

Wale Odusote

(Customer) asked a question.

Edited October 6, 2025 at 1:16 PM

Browser Isolation

I've trieed this and it is not working as I thought it would.

Will Browser Isolation (BI) works for a client witn no ZCC installed?

ZIA - Browser Isolation

5 answers

346 views

Ramesh Mani

(Partner)

9 months ago

Hi , Refer here -

https://help.zscaler.com/isolation/what-is-isolation

GRE Tunnel, Zscaler Client Connector, or any of the other Zscaler recommended traffic forwarding methods.

Sachin Chauhan

(Partner)

9 months ago

Hi @Adebowale Odusote​ , Could you please elaborate more about your exact issue?

Wale Odusote

(Customer)

9 months ago

We have a GRE tunnel configured with a sublocation designated for the guest wireless subnet. The BI system is currently set up to monitor and capture traffic from guest users who connect to the guest wireless network and attempt to access harmful or malicious websites.

I was exploring the possibility of leveraging the sublocation attribute to engineer a more targeted policy—specifically to isolate and control access to certain URLs based on this location context. This would enhance our ability to enforce security controls and improve visibility into guest traffic behavior.

The problem is that it is not working on endpoint without ZCC.

User15799669235102008483

(Customer)

9 months ago

browser isolation can't work without the client.

look instead into DNS Control

Ramesh Mani

(Partner)

9 months ago

Hi, Isolation will work without client connector as well, a Url request should hit on the url filtering policy,

Log In to Answer

Associated Tags

accesspolicy

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

No posts to show.

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Browser Isolation
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000g5jfm0AA/recommended-for-traffic-forwarding-with-corporate-vpn","lastmod":"2025-10-12T19:59:59.000Z","id":"0D5PJ00000g5jfm0AA"} -->
## Recommended for Traffic forwarding with corporate VPN

- Source: https://community.zscaler.com/s/question/0D5PJ00000g5jfm0AA/recommended-for-traffic-forwarding-with-corporate-vpn
- Type: Q&A
- Last activity: 2025-10-12T19:59:59.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

Kalsy

(Partner) asked a question.

October 8, 2025 at 7:14 AM

Recommended for Traffic forwarding with corporate VPN

The users are using corporate VPN ​with Full tunnel mode with ZCC.

We wanted to make sure the user with both ZCC and Corporate VPN should work seamlessly and ZIA take priority over VPN when a user has full tunnel mode

I wanted to know what is the recommended forwarding method (Tunnel or Tunnel with Local proxy) to configure in Zscaler forwarding profile on VPN trusted network. ​

Please advise

ZIA - Forwarding

2 answers

338 views

Ramesh Mani

(Partner)

10 months ago

The best way would be for you to do a split-tunnel deployment for the VPN client, sending internally destined traffic over the IPSec tunnel from the VPN client back to your VPN concentrator. All other traffic, internet-bound traffic, send to ZCC and ultimately our cloud.

Or You would need to get that traffic that lands in your DC through full tunnel VPN to somehow make it to us in order for policy enforcement to be applied. This can be done by placing that VPN traffic as it leaves your DC into a tunnel (IPsec or GRE).

Full-Tunnel Mode

When your VPN runs full-tunnel mode, all of your users’ traffic is routed to the VPN client. Zscaler Client Connector treats the network as a VPN-Trusted Network and applies the forwarding profile action you chose for that network. However, the following important caveats apply to this configuration:

Zscaler strongly recommends against using the forwarding profile Tunnel (Route-Based) for VPN-Trusted Network. It can cause interoperability issues because Zscaler Client Connector in Tunnel mode works on the IP layer (the same layer as the VPN client). Zscaler recommends selecting Tunnel with Local Proxy in this scenario for Windows and macOS.

If your VPN doesn't set a default route, select Tunnel with Local Proxy. Zscaler Client Connector detects a full tunnel VPN by looking for a default route in the routing table. If the VPN doesn’t set a default route and uses a different mechanism to capture all traffic, Zscaler Client Connector won't consider the VPN a full-tunnel VPN. It won't treat the user as connected to a VPN Trusted Network. Instead, the app treats the user as Off-Trusted Network and applies the corresponding forwarding profile action.

Zscaler Client Connector looks for the following words in the default interface description to detect a VPN-Trusted Network: Cisco, Juniper, Fortinet, PanGP, Check Point, and VPN. If these words are missing, the app treats the user as Off-Trusted Network.

Refer :

https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability

Kalsy

(Partner)

10 months ago

Thanks for you reply !

Actually we are in process of migration, so we don't want to disturb the current setup by changing the it to split tunnel.

Note: Only few users has enabled with Full tunnel mode.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

492

492 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Recommended for Traffic forwarding with corporate VPN
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000gg29K0AQ/geolocation-issues","lastmod":"2025-10-21T05:46:34.000Z","id":"0D5PJ00000gg29K0AQ"} -->
## GeoLocation issues

- Source: https://community.zscaler.com/s/question/0D5PJ00000gg29K0AQ/geolocation-issues
- Type: Q&A
- Last activity: 2025-10-21T05:46:34.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

Thfiorini

(Customer) asked a question.

Edited by Ben_Garrison October 16, 2025 at 2:37 PM

GeoLocation issues

Hi all,

We have a VPN concentrator in our DC in Dublin, Ireland, which is used as the Hub for all offices in Europe.

Some users based in Ireland, when connected to the this VPN, have their traffic forwarded to the ZSCaler pop in Madrid, Spain (zs2-mad3-3a4-sme.gateway.zscalertwo.net). The source IP address here is masked by the DC Firewall, but still shown as a public IP in Dublin/Ireland - 158.xxx.xx.xxx.

When they log out of the VPN, and user their own local connection (Irish ISP), the traffic is sent to Manchester UK (zs2-man1-2f1-sme.gateway). The source IP in this case is also Ireland-based, 51.xxx.xx.xxx. This is technically the best and faster path.

How can we fix this so ZScaler ensures both IPs are set to use the path to Manchester, UK, instead of Spain?

ZIA - Forwarding

3 answers

306 views

Arun

(Partner)

9 months ago

Try to write PAC for those users wants to connect UK dc instead of spain.You give both dcs.

Thfiorini

(Customer)

9 months ago

hi Arun, thanks for your reply, but I don't think it's an option for us, we need to leave it the config without PACs being added.

I found out that MaxMind had associated our public subnet to one of our offices in Spain. I raised a ticket and they fixed it on their side yeterday, and now it is correctly showing the subnet located in Ireland. Despite that, ZSCaler still forwards the traffic to Spain.

Would you know if there is any other GeoLocation service used by ZScaler ?

Thanks!

Ramesh Mani

(Partner)

9 months ago

Work with Zscaler support team to hardcode this if not set it through PAC file. If maxmind shows correctly geo then it should work.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

491

491 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

GeoLocation issues
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ggEbK0AU/zia-cloud-app-control-policy-for-instant-messaging-download-block","lastmod":"2025-10-17T18:26:24.000Z","id":"0D5PJ00000ggEbK0AU"} -->
## ZIA - Cloud App Control Policy for Instant Messaging 'Download' block

- Source: https://community.zscaler.com/s/question/0D5PJ00000ggEbK0AU/zia-cloud-app-control-policy-for-instant-messaging-download-block
- Type: Q&A
- Last activity: 2025-10-17T18:26:24.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - CASB

vaibhav_j

(Customer) asked a question.

October 15, 2025 at 12:30 PM

ZIA - Cloud App Control Policy for Instant Messaging 'Download' block

Does having File Transfers = 'Block' also blocks 'download' of file for scoped 'instant messaging' application cloud app control policy?

If no, is there any other way to allow user do 'chat' but 'block' from downloading files from all 'instant messaging' application?

ZIA - CASB

2 answers

177 views

Arun

(Partner)

9 months ago

Currently there is not posting option but in browser isolation will work.We can define in browser isolation profile

Miguel_Faldutti

(Partner)

9 months ago

It should work, because the user is transfering an image to another user. My suggestion is that you create a testing rule only for you account, and test it. But it should work just fine.

2 likes

Log In to Answer

Associated Tags

cloud-app

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

4/6/2025

at

05:57 PM

Cloud App Control - Google Drive subactions

ZIA - CASB

Danielo

495

495 Views

0 Likes

7 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

ZIA - Cloud App Control Policy for Instant Messaging 'Download' block
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000hTRyX0AW/free-tool-export-and-analyze-zia-custom-url-categories","lastmod":"2025-12-18T14:18:44.000Z","id":"0D5PJ00000hTRyX0AW"} -->
## Free Tool: Export and Analyze ZIA Custom URL Categories

- Source: https://community.zscaler.com/s/question/0D5PJ00000hTRyX0AW/free-tool-export-and-analyze-zia-custom-url-categories
- Type: Q&A
- Last activity: 2025-12-18T14:18:44.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

Daniele Màtyàs Tieghi

(Partner) asked a question.

Edited October 22, 2025 at 5:01 PM

Free Tool: Export and Analyze ZIA Custom URL Categories

The Problem

Manual audits of large custom URL categories through the ZIA UI are impractical when you have thousands of URLs. No easy way to:

- Export all URLs in a custom category

- See which Zscaler category each URL belongs to

- Identify redundancies across multiple categories

- Document configurations for compliance

The Tool

ZIA Custom Category Analyzer

- Free, open-source Python script that exports your custom URL categories to Excel with automatic Zscaler category lookups.

Who it's for:

- ZIA admins managing 1000+ URLs across custom categories

- Security teams auditing URL filtering policies

- Organizations needing compliance documentation

What You Get

The tool generates an Excel file with:

Detailed insights:

- Each URL with its Zscaler-assigned category

- Summary sheet: total URLs, categorization breakdown

Complete setup guide and video tutorial:

- GitHub repository:

https://github.com/zscaler-hero/zia-custom-category-analyzer

- Video walkthrough (5:59 min):

https://www.youtube.com/watch?v=4BaY2-Q2L7g

Requirements:

- Python 3.7+ (many systems already have it)

- ZIA One API access (400 requests/day, 100 URLs per request)

ZIA - URL Filtering

2 answers

840 views

bd_engineer

likes this.

Ramesh Mani

(Partner)

9 months ago

This is great, thanks for sharing this

bd_engineer

(Customer)

7 months ago

This is great, thanks for sharing!

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,916

5916 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

888

888 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Free Tool: Export and Analyze ZIA Custom URL Categories
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000hbyRU0AY/could-not-able-to-search-previous-solution-articles","lastmod":"2025-10-25T01:18:52.000Z","id":"0D5PJ00000hbyRU0AY"} -->
## could not able to search  previous solution articles

- Source: https://community.zscaler.com/s/question/0D5PJ00000hbyRU0AY/could-not-able-to-search-previous-solution-articles
- Type: Q&A
- Last activity: 2025-10-25T01:18:52.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Logging

venkat.narsimulu-banda

(Partner) asked a question.

October 23, 2025 at 7:36 PM

could not able to search  previous solution articles

one of the customer not receiving nss feed logs from cloud nss

could not find any troubleshooting steps.

ZIA - Logging

2 answers

145 views

Ramesh Mani

(Partner)

9 months ago

https://help.zscaler.com/zia/troubleshooting-deployed-nss-servers

https://help.zscaler.com/zia/cloud-nanolog-streaming-service-nss

venkat.narsimulu-banda

(Partner)

9 months ago

Not these URLs,  i could able to access all content from

help.zscaler.com

how to access below articles

How to allow legitimate traffic which is blocked due to mismatch between the CONNECT Host and the SNI

https://zscaler.my.site.com/customers/s/article/000006321

How to verify which traffic is getting blocked due to mismatch between the CONNECT Host and the SNI

https://zscaler.my.site.com/customers/s/article/000006322

its asking credential, not sure what credentials we need use here....

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

6/8/2023

at

08:51 PM

URL Reporting

ZIA - Logging

akita_down

12

12 Views

0 Likes

4 Comments

11/3/2023

at

06:08 PM

ZIA - NSS Feed Status

ZIA - Logging

Rallis

618

618 Views

0 Likes

3 Comments

2/21/2024

at

10:43 PM

Find if User is behind a VPN

ZIA - Logging

venG

544

544 Views

0 Likes

2 Comments

3/13/2025

at

01:56 AM

How to Extract Logs from Web Insight

ZIA - Logging

JSK27

673

673 Views

0 Likes

8 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

could not able to search  previous solution articles
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000hl5TP0AY/zscaler-zia-authentication-through-microsoft-365-noname-generic-account","lastmod":"2025-10-27T13:25:53.000Z","id":"0D5PJ00000hl5TP0AY"} -->
## Zscaler ZIA authentication through Microsoft 365 noname generic account

- Source: https://community.zscaler.com/s/question/0D5PJ00000hl5TP0AY/zscaler-zia-authentication-through-microsoft-365-noname-generic-account
- Type: Q&A
- Last activity: 2025-10-27T13:25:53.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Authentication

fobrien

(Customer) asked a question.

October 25, 2025 at 10:28 PM

Zscaler ZIA authentication through Microsoft 365 noname generic account

Hello everybody,

We are currently testing the Zscaler ZIA solution with a Microsoft 365 authentication connector in our company before a massive deployment on all our client computers.

However, I was wondering if anybody here already came across this issue : we are having a couple of generic (noname) Active Directory accounts who are not linked to a Microsoft 365 license.

My question is : how can we manage these accounts with an alternate solution in order to enable them to log on through Zscaler ?

Thanks in advance for your help.

Fred

ZIA - Authentication

174 views

Log In to Answer

Associated Tags

azure-ad

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/15/2022

at

12:39 PM

Onprem Active directory integrate with Zscaler cloud ZIA

ZIA - Authentication

aravindhan.m

3 Views

0 Likes

3 Comments

7/11/2022

at

07:26 AM

Zscaler IDP authentication issue

ZIA - Authentication

aravindhan.m

4 Views

0 Likes

2 Comments

5/27/2022

at

01:49 PM

Zscaler ZIA O365 Apps not authenticating

ZIA - Authentication

abmaclean

4 Views

0 Likes

6 Comments

10/4/2021

at

10:43 PM

Automatic de-provisioning using SCIM with Azure AD

ZIA - Authentication

Paul_Wineberg

4 Views

0 Likes

4 Comments

6/27/2020

at

03:01 PM

Zscaler public preview

ZIA - Authentication

Muhammad

364

364 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Zscaler ZIA authentication through Microsoft 365 noname generic account
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000hsowm0AA/oneapi-zia-role-administration","lastmod":"2025-11-03T16:51:40.000Z","id":"0D5PJ00000hsowm0AA"} -->
## OneAPI ZIA Role Administration

- Source: https://community.zscaler.com/s/question/0D5PJ00000hsowm0AA/oneapi-zia-role-administration
- Type: Q&A
- Last activity: 2025-11-03T16:51:40.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Authentication

carter.cook

(Partner) asked a question.

Edited October 28, 2025 at 5:11 PM

OneAPI ZIA Role Administration

I am trying to use the OneAPI to create and manage roles within ZIA, but I'm continuing to run into permission errors. I have been testing with both the POST and PUT endpoints, https://api.zsapi.net/zia/api/v1/adminRoles/ and https://api.zsapi.net/zia/api/v1/adminRoles/.

When using the PUT endpoint to update an existing role, I always receive a 403 SCOPE_LIMITED error with the message "Administrator with less permissions can not create or update role with more permissions." The API client I am using has full access to every resource available, and the role I am attempting to edit is a new API role that only has read permissions. I have also tried editing other roles including user or admin roles, but they all have the same result. Out of curiosity I tried editing my API client's role, but that gave an error saying you can't edit your own role.

Why trying to use the POST endpoint, whether creating an API or org admin role type, I always received the 403 error "Only super admins can create, update or delete a role." When I went to ZIdentity to try and update my API client to use the Super Admin role, it wasn't an option since the Super Admin role is an administrator user type.

Do these errors mean that it is not possible to create or update roles via OneAPI, or is there a different method that I should be following? If it is not currently possible to create/update roles, is it planned to be in the future, or will the endpoints be removed from the API documentation page?

ZIA - Authentication

2 answers

300 views

Armando

(Employee)

9 months ago

Hi @Carter Cook​ by any chance do we have admin ranks enabled in your ZIA?

By default, only the Super Admin and Default admin have access to change the properties of all other admins.

This is because all administrators by default have a rank.

Creating a new role with all accesses does not make it the same as the

default 'Super Admin'

role setup when the org was provisioned.

Default 'Super Admin' has the internal rank of 0 (highest).

So, irrespective of the role access you have provided, the rank will be the same and similar rank admins cannot make any changes to other admins.

If admin ranking is enabled, an admin with a certain rank (0-7) will be able to manage all other admins having lower ranks (

higher the number, lower the rank:

0 is the highest rank, and 7 is the lowest rank

).

This applies for the UI, but it could apply also for ONEAPI calls.

Would it be possible to try this out?

Thanks.

carter.cook

(Partner)

9 months ago

Hi @Armando Carballo Picado​, we did not have admin ranking enabled. I did just try enabling it to see if it changed anything, however I am still seeing the same 403 error. I kept using the same API client role and attempting to create the same rank 7 role and only changed the admin ranking setting, but nothing changed unfortunately. I appreciate the help though, thanks!

Log In to Answer

Associated Tags

api

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/15/2022

at

12:39 PM

Onprem Active directory integrate with Zscaler cloud ZIA

ZIA - Authentication

aravindhan.m

3 Views

0 Likes

3 Comments

7/11/2022

at

07:26 AM

Zscaler IDP authentication issue

ZIA - Authentication

aravindhan.m

4 Views

0 Likes

2 Comments

5/27/2022

at

01:49 PM

Zscaler ZIA O365 Apps not authenticating

ZIA - Authentication

abmaclean

4 Views

0 Likes

6 Comments

10/4/2021

at

10:43 PM

Automatic de-provisioning using SCIM with Azure AD

ZIA - Authentication

Paul_Wineberg

4 Views

0 Likes

4 Comments

6/27/2020

at

03:01 PM

Zscaler public preview

ZIA - Authentication

Muhammad

364

364 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

OneAPI ZIA Role Administration
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000iEstq0AC/unable-to-find-the-zscaler-proxy-ip-showing-only-organization-ip","lastmod":"2025-10-31T06:59:47.000Z","id":"0D5PJ00000iEstq0AC"} -->
## Unable to find the Zscaler proxy ip , showing only organization ip

- Source: https://community.zscaler.com/s/question/0D5PJ00000iEstq0AC/unable-to-find-the-zscaler-proxy-ip-showing-only-organization-ip
- Type: Q&A
- Last activity: 2025-10-31T06:59:47.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZUG - India

kartikay.mishra

(Partner) asked a question.

October 31, 2025 at 6:59 AM

Unable to find the Zscaler proxy ip , showing only organization ip

Need to know why visible source ip address post configuarion of zcc in user end

ZUG - India

135 views

Log In to Answer

Associated Tags

accesspolicy

azure

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/21/2025

at

07:29 AM

Zscaler Learning Path for Zscaler Private Access

ZUG - India

TechExeprt

220

220 Views

0 Likes

2 Comments

7/7/2026

at

07:26 AM

Guided Tour asking Enter your username or email to sign in -Solution Demo Center

ZUG - India

neerajkumar.jagetia

264

264 Views

2 Likes

5 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Unable to find the Zscaler proxy ip , showing only organization ip
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000jCM3U0AW/route-traffic-from-one-zscaler-customer-to-another","lastmod":"2025-11-18T13:36:58.000Z","id":"0D5PJ00000jCM3U0AW"} -->
## Route traffic from one zscaler customer to another

- Source: https://community.zscaler.com/s/question/0D5PJ00000jCM3U0AW/route-traffic-from-one-zscaler-customer-to-another
- Type: Q&A
- Last activity: 2025-11-18T13:36:58.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

avshch

(Customer) asked a question.

November 10, 2025 at 2:45 PM

Route traffic from one zscaler customer to another

Is it possible to route traffic from one zscaler customer to another via ZTE?

ZIA - Forwarding

3 answers

207 views

Ramesh Mani

(Partner)

9 months ago

You can go with adding partner tenants in ZCC to access resources in other company.

Also you can go with Zscaler Extranet configurations - other company can route the traffic through ZTE using IPSec towards ZIA and use them in ZPA , configure app segments, server groups etc.

For more :

https://help.zscaler.com/zia/about-extranet

avshch

(Customer)

8 months ago

@Ramesh Mani​ Is Zscaler extranet available with all clouds?

Ramesh Mani

(Partner)

8 months ago

Hi, I don't see such info in the release notes, you may reach out your Zscaler Account team to access Extranet Application Support

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

492

492 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Route traffic from one zscaler customer to another
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000jMHA10AO/ssl-inspection-shortlived-issuing-ca","lastmod":"2025-11-13T05:26:38.000Z","id":"0D5PJ00000jMHA10AO"} -->
## SSL Inspection / short-lived issuing CA

- Source: https://community.zscaler.com/s/question/0D5PJ00000jMHA10AO/ssl-inspection-shortlived-issuing-ca
- Type: Q&A
- Last activity: 2025-11-13T05:26:38.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - SSL Inspection

hesham.ismail

(Partner) asked a question.

November 11, 2025 at 10:45 AM

SSL Inspection / short-lived issuing CA

Hello Team,

I would like to clarify my understanding regarding the concept of Certificate Chain Trust. My current assumption is that in order to successfully establish an SSL handshake, I need to trust the entire chain of certificates leading up to the root Certificate Authority (CA). This includes trusting the root CA itself as well as any intermediate CAs involved in the chain.

However, I have a question about how SSL inspection functions when dealing with short-lived issuing CAs. Specifically, these CAs have a validity period of only 14 days and are rotated every 7 days. Since these short-lived CAs are not permanently installed or trusted beforehand, I am curious about how SSL inspection manages to validate certificates issued by such transient CAs within this rapid rotation cycle.

Could someone please explain how trust is maintained in these scenarios and how SSL inspection handles the validation process with such short-lived CAs?

Thank you!

ZIA - SSL Inspection

3 answers

253 views

Ramesh Mani

(Partner)

8 months ago

Source : -  Internet :)

Trust Maintenance in SSL Inspection

The key to how SSL inspection (often performed by a security device like a firewall, proxy, or load balancer) handles trust, even with short-lived CAs, lies in two distinct trust relationships:

1. Trust in the Original Certificate Chain

The SSL inspection device must first validate the server's original certificate (issued by the short-lived CA) just as a normal client would.

a) The server sends its certificate, plus the Intermediate Certificate (the short-lived CA) that issued it.

b) The inspection device must check the signature of the short-lived CA's certificate against the next certificate in the chain (likely a long-lived Intermediate CA or the Root CA).

c) For validation to work, the short-lived CA's certificate must itself be signed by a trusted, long-lived Intermediate or Root CA that is present in the inspection device's (and the world's) trust store.

The Short-Lived CA is *not* the root of trust. The trust is still rooted in a permanently trusted Root CA (e.g., Let's Encrypt's R3 or ISRG Root X1, or a corporate Root CA). The short-lived CA is simply an Intermediate CA signed by that long-lived trust anchor. The client/inspector does not need to permanently trust the short-lived CA itself; it only needs the short-lived CA's certificate to be present in the chain and validly signed by a trusted anchor.

2. Trust in the Inspection Device's Own Certificate Chain

The second, crucial step is the SSL Inspection Proxy operation:

* The inspection device generates a new, fake certificate for the server's domain (e.g., for `

www.example.com

`).

* This new, fake certificate must be signed by a special, Inspection-Specific Issuing CA.

* For the end-user's device (browser) to accept this fake certificate, the end-user's device must explicitly trust the Inspection-Specific Issuing CA (or its Root CA).

In essence, the SSL inspection device acts as a trusted middleman because the end-user is configured to trust the inspection device's own internal CA hierarchy.

hesham.ismail

(Partner)

8 months ago

Sorry I didn't get your point well

let's have an example below

1- I'm trying to access youtube that's inspected by Zscaler

Root CA (trusted on my device) ---> Intermediate CA (Trusted) --->

short-lived CA (New/untrusted every 7-day rotation )

--> Youtube

So I get youtube certificate issued by short-lived CA which is not trusted on my browser/system

this is the point I want to understand as there's intermediate certificate is not trusted so chain of trust is broken.

2- If it's Root CA (trusted ) ---> Intermediate CA (Trusted) --> Youtube

it'll be ok as I trust whole certificate chain , if I don't trust intermediate CA I'll get warning .

hesham.ismail

(Partner)

8 months ago

however I trust Root CA and intermediate CA is issue by Root CA which I trust , but in above example #2 it won't work if I don't trust intermediate CA itself and put it in my trusted certificates store.

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2021

at

07:54 PM

Youtube Restricted Mode Bypass Safe Search

ZIA - SSL Inspection

JonM

8 Views

1 Like

5 Comments

11/24/2021

at

06:09 AM

Reflection time of SSL inspection exclusion settings

ZIA - SSL Inspection

Nyajima

4 Views

0 Likes

1 Comment

4/26/2024

at

02:39 PM

End User Notifcation - Submit To Security Cloud option

ZIA - SSL Inspection

citrus3118

308

308 Views

0 Likes

1 Comment

8/21/2024

at

08:06 PM

StackHawk with Zscaler SSL Inspection

ZIA - SSL Inspection

User16182582818315999266

564

564 Views

0 Likes

4 Comments

3/10/2025

at

12:22 AM

Reputation Block policy

ZIA - SSL Inspection

JM

413

413 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

SSL Inspection / short-lived issuing CA
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000m6Pt20AE/browser-extension-block","lastmod":"2025-12-17T14:33:35.000Z","id":"0D5PJ00000m6Pt20AE"} -->
## Browser Extension Block

- Source: https://community.zscaler.com/s/question/0D5PJ00000m6Pt20AE/browser-extension-block
- Type: Q&A
- Last activity: 2025-12-17T14:33:35.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Browser Isolation

gayatri.debnath

(Partner) asked a question.

December 17, 2025 at 2:33 PM

Browser Extension Block

Is there a feature to enable browser extension blocking in ZIA ?

I am aware of being able to block specific file types or vpn domains , so that the extensions cannot reach out to the destination.

However is there a way to straight up block browser extensions itself?

ZIA - Browser Isolation

153 views

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

No posts to show.

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Browser Extension Block
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000mDOfB0AW/sandbox","lastmod":"2025-12-19T07:22:57.000Z","id":"0D5PJ00000mDOfB0AW"} -->
## Sandbox

- Source: https://community.zscaler.com/s/question/0D5PJ00000mDOfB0AW/sandbox
- Type: Q&A
- Last activity: 2025-12-19T07:22:57.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Cloud Sandbox

gopinath.m

(Partner) asked a question.

December 19, 2025 at 7:22 AM

Sandbox

Any suggestions for how to demonstrate the full sandbox feature? How to simulate a file download sent to sandbox?

ZIA - Cloud Sandbox

235 views

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

1/21/2020

at

01:31 PM

Does Zscaler Scan Email Traffic?

ZIA - Cloud Sandbox

Omar

1,229

1229 Views

1 Like

3 Comments

1/2/2020

at

12:25 PM

Prevent Malicious VPN Connection

ZIA - Cloud Sandbox

Omar

1,197

1197 Views

0 Likes

8 Comments

10/2/2017

at

04:47 AM

Alerting positive 'allow and scan'

ZIA - Cloud Sandbox

Lmay

430

430 Views

1 Like

2 Comments

9/11/2017

at

09:37 PM

How do I determine which of the files that were submitted to Cloud Sandbox were found to be malicious?

ZIA - Cloud Sandbox

awl

527

527 Views

0 Likes

1 Comment

9/11/2017

at

09:35 PM

What is the recommended “best practice? Cloud Sandbox rule policy?

ZIA - Cloud Sandbox

awl

588

588 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Sandbox
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000n4OjK0AU/pzen-behind-the-network-firewall-deployment-model","lastmod":"2026-01-09T09:46:30.000Z","id":"0D5PJ00000n4OjK0AU"} -->
## PZEN Behind the Network Firewall deployment model

- Source: https://community.zscaler.com/s/question/0D5PJ00000n4OjK0AU/pzen-behind-the-network-firewall-deployment-model
- Type: Q&A
- Last activity: 2026-01-09T09:46:30.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Discourse-expand

Ramesh M

(Employee) to

sfdc

(Employee): asked a question.

February 6, 2020 at 5:57 AM

PZEN Behind the Network Firewall deployment model

Hi Team,

One of our customer chosen private ZEN deployment as behind the Network Firewall. Is there anyone deployed in such model.

Can you help me with IP requirements like public and private IPs.

Also the tunnel sources will be MPLS router, internal router for PZEN and tunnel to be via MPLS with private IP. Great if you worked on such scenarios to share with me.

Regards

Ramesh M

Discourse-expand

Far-image

4 answers

76 views

skottieb

(Employee)

6 years ago

Hi

@ramesh.mani1

, that’s a support topology, you find it documented here —>

https://help.zscaler.com/zia/deploying-service-edge

Network requirements are defined here —>

https://help.zscaler.com/zia/about-service-edge

Please also contact you account team so they can assist you with sizing and other prerequisites for Service Edge (formerly PZEN)

Cheers,

@skottieb

Ramesh M

(Employee)

6 years ago

Hi Scott,

The documentation is not clear enough.

I don’t see IP requirements,

Can I establish GRE from private IP as well as public IP. etc.

Now I am working with Zscaler SE to find out the best solution. ’

Regards

Ramesh M

skottieb

(Employee)

Edited by sfdc July 7, 2023 at 11:59 AM

Hi

@ramesh.mani1

, glad to hear you’re engaged with your SE.

I don’t see IP requirements,

These are in this link

https://help.zscaler.com/zia/about-service-edge

image

1886×568 36.3 KB

Can I establish GRE from private IP as well as public IP. etc.

Yes you can!

Ramesh M

(Employee)

Edited by sfdc July 6, 2023 at 12:46 PM

ramesh.mani1:

Hi Scott,

Thanks for your update.

But If I am placing the Service edge internally or in DMZ with Private IPs. Still I need some public IPs to be reserved (NAT) for the service edge to reach out the Zscaler cloud. I am looking for public IP in this case.

Regards

Ramesh M

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/3/2020

at

11:55 AM

ZAPP intune deployment

Discourse-expand

Mk001

1,379

1379 Views

0 Likes

4 Comments

8/30/2022

at

05:43 AM

Reporting on the Zscaler

Platform

User1654060199066250247

3 Views

0 Likes

5 Comments

2/3/2023

at

03:28 AM

Airplane WiFi / ZIA

Secure Internet Access (ZIA)

Oshaq1

20

20 Views

1 Like

10

10 Comments

11/8/2022

at

03:46 PM

ERROR: zpa-connector.service: entered a failed state after updates

Secure Private Access (ZPA)

cnicholas

3 Views

0 Likes

5 Comments

9/7/2019

at

10:49 PM

ZPA Up Before Windows Login

Secure Private Access (ZPA)

Omar

5,678

5678 Views

3 Likes

76

76 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

PZEN Behind the Network Firewall deployment model
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000nPg3E0AS/miscategorization-of-domainwebsite","lastmod":"2026-01-14T18:59:51.000Z","id":"0D5PJ00000nPg3E0AS"} -->
## Miscategorization of domain/website

- Source: https://community.zscaler.com/s/question/0D5PJ00000nPg3E0AS/miscategorization-of-domainwebsite
- Type: Q&A
- Last activity: 2026-01-14T18:59:51.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

jdtech

(Customer) asked a question.

January 13, 2026 at 1:41 AM

Miscategorization of domain/website

How do non-ZScaler businesses get their domains recategorized? I've heard only if you're a ZScaler customer, but I find that hard to believe. Any idea?

ZIA - URL Filtering

1 answer

173 views

Ben_Garrison

(Employee)

6 months ago

Yes that is correct, only Zscaler customers can request a domain recategorization.  Please review the below post for more

https://community.zscaler.com/s/question/0D5PJ00000koRAg0AM/domain-reclassification-requests-on-community

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,909

5909 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

888

888 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Miscategorization of domain/website
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000noWov0AE/feature-request-santioned-and-unsantioned-cloud-apps","lastmod":"2026-01-25T10:37:25.000Z","id":"0D5PJ00000noWov0AE"} -->
## Feature Request: Santioned and Unsantioned Cloud Apps

- Source: https://community.zscaler.com/s/question/0D5PJ00000noWov0AE/feature-request-santioned-and-unsantioned-cloud-apps
- Type: Q&A
- Last activity: 2026-01-25T10:37:25.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - DLP

dwaine.duthie

(Partner) asked a question.

January 20, 2026 at 9:10 AM

Feature Request: Santioned and Unsantioned Cloud Apps

Hi all

I am busy with setting up DLP for our tennant and I came across the fact that it seems we are unable to control the DLP Policies using Cloud Application Status, This would greatly improve the managablity of the DLP Policies if we were able to set the policy to a Sanctioned vs Unsanctioned policy and then control the Cloud Application Status via the Cloud Applications Portal. Another metric would be the Cloud Applicaton Risk index which is more of a Dynamic field. Blocking on a Risk Index of 5, Cautioning on a Risk index of 4, etc would streamline the policy rules in DLP greatly.

I am not sure how to raise this as an officel Feature Request.

Please help.

ZIA - DLP

3 answers

212 views

Ben_Garrison

(Employee)

6 months ago

I will see if I can forward this up. Keep in mind that at this time, the community does not support feature requests. Maybe in future, we will have a way to handle feature requests properly on community. This should be handled through your account managers or through support (by using the support/partner portal)

1 like

AR

(Partner)

6 months ago

Noted

dwaine.duthie

(Partner)

6 months ago

thank you I have raised it with my Account team and hoping to get some traction on it, the admin to do the DLP without is going to be interesting and will result in missed things.

Log In to Answer

Associated Tags

accesspolicy

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/9/2023

at

05:32 PM

DLP notification via Zscaler Client Connector

ZIA - DLP

thacarvalho123

7 Views

2 Likes

2 Comments

11/20/2022

at

09:26 PM

External DLP Engine

ZIA - DLP

H.zyD.zy

4 Views

0 Likes

3 Comments

11/9/2022

at

04:51 PM

Zscaler Preventing Image Upload and Download on iPhone

ZIA - DLP

MacDanorld1

2 Views

0 Likes

3 Comments

6/20/2022

at

07:53 AM

Error during Microsoft Information Protection(MIP) Integration

ZIA - DLP

ozanogur

3 Views

0 Likes

4 Comments

1/31/2021

at

08:35 PM

Zscaler ZIA and ZPA with NetSkope CASB and DLP services

ZIA - DLP

jonathan.holt

1,343

1343 Views

1 Like

6 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Feature Request: Santioned and Unsantioned Cloud Apps
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000npvGV0AY/feature-request-zia-web-insight-filters","lastmod":"2026-01-21T14:29:57.000Z","id":"0D5PJ00000npvGV0AY"} -->
## Feature Request: ZIA Web Insight Filters

- Source: https://community.zscaler.com/s/question/0D5PJ00000npvGV0AY/feature-request-zia-web-insight-filters
- Type: Q&A
- Last activity: 2026-01-21T14:29:57.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Logging

SHaley

(Customer) asked a question.

January 20, 2026 at 4:21 PM

Feature Request: ZIA Web Insight Filters

I would like to have the ability to save user specific filter/dashboard settings when looking through Insight Logs. Would also like the ability to save the column orientations for the results. I have a very specific scope where being able to have the columns I need always show up in the order I need them to would save me a lot of time.

On a side note - being able to freeze the first column in the results would be great (kind of like excel). For instance, I can have the TunnelName or Source IP frozen in the first row and when I slide the bar to the right, I can keep track of what row I'm focused on without losing my place.

ZIA - Logging

1 answer

118 views

Ben_Garrison

(Employee)

6 months ago

I approved the comment, in case anyone has any suggestions or workarounds.  However the community does not have a way to handle feature requests at this time. I will see if I can forward this up, but doing so would not be in any official channel. Hopefully in future we will have the ability to properly handle feature requests through the community. For now, the only way to do this is through your account manager, or support portal.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

6/8/2023

at

08:51 PM

URL Reporting

ZIA - Logging

akita_down

12

12 Views

0 Likes

4 Comments

11/3/2023

at

06:08 PM

ZIA - NSS Feed Status

ZIA - Logging

Rallis

616

616 Views

0 Likes

3 Comments

2/21/2024

at

10:43 PM

Find if User is behind a VPN

ZIA - Logging

venG

542

542 Views

0 Likes

2 Comments

3/13/2025

at

01:56 AM

How to Extract Logs from Web Insight

ZIA - Logging

JSK27

668

668 Views

0 Likes

8 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Feature Request: ZIA Web Insight Filters
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000oO25h0AC/gre-and-tunnel-version","lastmod":"2026-01-29T14:33:17.000Z","id":"0D5PJ00000oO25h0AC"} -->
## GRE and tunnel version

- Source: https://community.zscaler.com/s/question/0D5PJ00000oO25h0AC/gre-and-tunnel-version
- Type: Q&A
- Last activity: 2026-01-29T14:33:17.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

Giovanni

(Customer) asked a question.

January 29, 2026 at 8:35 AM

GRE and tunnel version

Hi, on our company we had two location with ipsec tunnel + zcc with tunnel 2.0. Now for a new location we have set up the GRE tunnel but performance are very poor. Support says that with GRE tunnel we should use tunnel 1.0 for web and 2.0 with all the rest of the traffic and it's possible by turn no on the forwarding profile the option "

Redirect Web Traffic to Zscaler Client Connector Listening Proxy"

They did not provided any official documentation nor I have found anything about it on the help manual. Do you have any experience about it? what is the official correct configuration? thank you

ZIA - Forwarding

3 answers

200 views

Ramesh M

(Employee)

6 months ago

Always recommended to go with Tunnel 1.0 when you have GRE / IPSec tunnels from the location.

Change ZCC to tunnel 1,0 and sent it via GRE /IPsec   (OR)

Split ZCC traffic and all other traffic, Keep ZCC to tunnel 2.0 and bypass it from GRE / IPSec and keep all other traffic to GRE/IPSec.

Giovanni

(Customer)

6 months ago

Hi Ramesh, thank you, do you know where is documented? I can't find anything. The point is that our requisites is to have all the traffic not only web tunneled through zscaler for security reason. So  how can I set set up point 1 for example? isn't "Redirect Web Traffic to Zscaler Client Connector Listening Proxy" an option?

Ramesh M

(Employee)

6 months ago

Point 1 : -  ZCC tunnel 1.0 route all 80 and 443 forwarded to ZScaler service edge through GRE/IPSEC tunnel. All other traffic (other than web traffic) routed to Zscaler service edge through the GRE /IPSec tunnel from the local gateway device.

Reg :  "Redirect Web Traffic to Zscaler Client Connector Listening Proxy"

Ztunnel 2.0 Domain Wildcard based bypasses typically require a forwarding profile pac, which essentially downgrades any browser traffic to Ztunnel 1.0, so it can be parsed through the app profile configuration for bypasses. Having the system proxy there was cumbersome, so this feature will explicitly route 80/443 TCP to the local proxy listener, which can then be parsed through the app profile pac. It gives you the ability to do wildcard domain bypasses without defining a system proxy essentially.

The second setting determines whether this now Ztunnel 1.0 traffic, should be sent to the cloud as Ztunnel 1.0, or sent inside Ztunnel 2.0. The former is the behavior today

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

491

491 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

GRE and tunnel version
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000og1y00AA/using-firewall-rules-without-affecting-browser-web-traffic","lastmod":"2026-02-05T15:01:02.000Z","id":"0D5PJ00000og1y00AA"} -->
## Using Firewall Rules Without Affecting Browser Web Traffic

- Source: https://community.zscaler.com/s/question/0D5PJ00000og1y00AA/using-firewall-rules-without-affecting-browser-web-traffic
- Type: Q&A
- Last activity: 2026-02-05T15:01:02.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Cloud Firewall

JeremyPPC

(Customer) asked a question.

Edited by Naman February 4, 2026 at 4:22 AM

Using Firewall Rules Without Affecting Browser Web Traffic

Hi Team!

This is my first post here.

I would like to use Firewall Control by adding all ports captured in ZCC.

For example, I have an application that uses TCP 8080 to connect, and the application cannot read the system proxy settings, so I would like to capture all traffic from this app.

That’s why I added port 8080 in the ZCC inclusion list.

I noticed that if I access a website that also uses port 8080, the firewall blocks the traffic.

What I want is to use URL Filtering only for browser traffic, and Firewall Rules for all other flows.

How can I configure this properly?

After several hours of testing, I concluded that I can achieve this by using a Forwarding Profile PAC file to send all non‑80/443 ports to the local proxy at

127.0.0.1:9000

I tried using

Redirect Web Traffic to Zscaler Client Connector Listening Proxy

, combined (or not) with

Use Z‑Tunnel 2.0 for Proxied Web Traffic

, without a PAC file, but it seems this does not work.

Only ports 80/443 are redirected to the local proxy

127.0.0.1:9000

Can you confirm this behavior?

Any other suggestions are appreciated. 😊

Thanks!

ZIA - Cloud Firewall

268 views

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2018

at

01:07 PM

Ip address as criteria in url policy

ZIA - Cloud Firewall

ram75

1,190

1190 Views

0 Likes

1 Comment

10/8/2020

at

08:41 AM

Block All access & Allow certain user or group (ZIA)

ZIA - Cloud Firewall

Sec_def_Def_sec

1,818

1818 Views

1 Like

2 Comments

12/19/2022

at

04:14 PM

URL filtering policy vs Cloud App policy control

ZIA - Cloud Firewall

Ahmed

4 Views

0 Likes

2 Comments

12/9/2022

at

09:40 PM

Apple News RSS Feed

ZIA - Cloud Firewall

Trace Woodbury-RidgeIT

2 Views

0 Likes

1 Comment

9/20/2022

at

03:20 PM

How does Zscaler Internet Access itself route the traffic to the internet, using what outgoing/next hop GW

ZIA - Cloud Firewall

tamerz

6 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Using Firewall Rules Without Affecting Browser Web Traffic
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000oofDL0AY/zscaler-zia-issue","lastmod":"2026-02-10T16:33:54.000Z","id":"0D5PJ00000oofDL0AY"} -->
## zscaler zia issue

- Source: https://community.zscaler.com/s/question/0D5PJ00000oofDL0AY/zscaler-zia-issue
- Type: Q&A
- Last activity: 2026-02-10T16:33:54.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

dushyant

(Partner) asked a question.

February 5, 2026 at 11:02 AM

zscaler zia issue

I cannot access the Zia URL filtering policy; when I apply it, only the Incognito policy is enforced, not the default browser policy.

ZIA - URL Filtering

1 answer

221 views

Ramesh M

(Employee)

5 months ago

Try closing the browser and reopen. Test it. Raise a support case if not working

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,913

5913 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

888

888 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

zscaler zia issue
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000osNCn0AM/understanding-workflows-in-zscaler-workflow-automation","lastmod":"2026-02-06T06:13:30.000Z","id":"0D5PJ00000osNCn0AM"} -->
## Understanding workflows in Zscaler Workflow Automation

- Source: https://community.zscaler.com/s/question/0D5PJ00000osNCn0AM/understanding-workflows-in-zscaler-workflow-automation
- Type: Q&A
- Last activity: 2026-02-06T06:13:30.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - DLP

harishp

(Partner) asked a question.

February 6, 2026 at 6:13 AM

Understanding workflows in Zscaler Workflow Automation

Hi everyone,

I’m exploring

Workflow Automation in Zscaler

and would like some clarity around how

workflows

are designed and used.

Specifically, I’m trying to understand:

How workflows are structured (triggers, conditions, actions)

What types of triggers are supported

Whether workflows can be chained or reused

Any limits around workflow complexity, execution, or frequency

I’m also interested in:

Common use cases where workflows are most effective

Best practices for designing scalable or maintainable workflows

If anyone has examples or lessons learned from real implementations, that would be really helpful.

ZIA - DLP

209 views

Log In to Answer

Associated Tags

aws

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/9/2023

at

05:32 PM

DLP notification via Zscaler Client Connector

ZIA - DLP

thacarvalho123

7 Views

2 Likes

2 Comments

11/20/2022

at

09:26 PM

External DLP Engine

ZIA - DLP

H.zyD.zy

4 Views

0 Likes

3 Comments

11/9/2022

at

04:51 PM

Zscaler Preventing Image Upload and Download on iPhone

ZIA - DLP

MacDanorld1

2 Views

0 Likes

3 Comments

6/20/2022

at

07:53 AM

Error during Microsoft Information Protection(MIP) Integration

ZIA - DLP

ozanogur

3 Views

0 Likes

4 Comments

1/31/2021

at

08:35 PM

Zscaler ZIA and ZPA with NetSkope CASB and DLP services

ZIA - DLP

jonathan.holt

1,338

1338 Views

1 Like

6 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Understanding workflows in Zscaler Workflow Automation
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000otE8v0AE/remove-proxy-server-config","lastmod":"2026-02-25T09:27:40.000Z","id":"0D5PJ00000otE8v0AE"} -->
## Remove Proxy server config

- Source: https://community.zscaler.com/s/question/0D5PJ00000otE8v0AE/remove-proxy-server-config
- Type: Q&A
- Last activity: 2026-02-25T09:27:40.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Posture

GilderJ

(Customer) asked a question.

February 6, 2026 at 11:10 AM

Remove Proxy server config

Hey,

we are in the process of moving to ZIA from an on-premise Cisco WSA, we configure the users proxy server via GPO I was wondering if there was a way ZCC could remove that proxy server config without messing with our group policy?

ZIA - Posture

297 views

Saffiiqq

likes this.

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

1/10/2024

at

09:42 AM

ZIA strict enforcement

ZIA - Posture

Diode

1,609

1609 Views

0 Likes

15

15 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Remove Proxy server config
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000p0JoW0AU/i-cant-login-to-submit-ticket","lastmod":"2026-02-25T05:07:01.000Z","id":"0D5PJ00000p0JoW0AU"} -->
## i can't login to  submit ticket

- Source: https://community.zscaler.com/s/question/0D5PJ00000p0JoW0AU/i-cant-login-to-submit-ticket
- Type: Q&A
- Last activity: 2026-02-25T05:07:01.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Logging

User17279421535626607243

(Customer) asked a question.

Edited February 9, 2026 at 7:46 AM

i can't login to  submit ticket

We recently migrated to Zidentity, but now we get an error when trying to submit a ticket via ZIA portal.

when we try to login with https://admin.zslogin.net/ i get error tenant not existe

ZIA - Logging

2 answers

231 views

Ben_Garrison

(Employee)

5 months ago

You may need to call the support .. You can find the support numbers at the bottom of the page:

https://help.zscaler.com/contact-support

urvi.joshi

(Partner)

5 months ago

Hi,

You should have a company domain like

https://<company-name>.zslogin.net

. Please make sure to use your company-specific domain when logging in to submit the case.

Thanks.

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

6/8/2023

at

08:51 PM

URL Reporting

ZIA - Logging

akita_down

12

12 Views

0 Likes

4 Comments

11/3/2023

at

06:08 PM

ZIA - NSS Feed Status

ZIA - Logging

Rallis

616

616 Views

0 Likes

3 Comments

2/21/2024

at

10:43 PM

Find if User is behind a VPN

ZIA - Logging

venG

542

542 Views

0 Likes

2 Comments

3/13/2025

at

01:56 AM

How to Extract Logs from Web Insight

ZIA - Logging

JSK27

668

668 Views

0 Likes

8 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

i can't login to  submit ticket
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000p6HPH0A2/inspect-sharepoint-traffic-while-using-microsoftrecommended-one-click-config","lastmod":"2026-03-02T09:38:35.000Z","id":"0D5PJ00000p6HPH0A2"} -->
## Inspect Sharepoint traffic while using Microsoft-Recommended One Click config

- Source: https://community.zscaler.com/s/question/0D5PJ00000p6HPH0A2/inspect-sharepoint-traffic-while-using-microsoftrecommended-one-click-config
- Type: Q&A
- Last activity: 2026-03-02T09:38:35.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - SSL Inspection

hukel

(Customer) asked a question.

February 10, 2026 at 4:36 PM

Inspect Sharepoint traffic while using Microsoft-Recommended One Click config

Is there a way for us to continue using the Microsoft-Recommended One Click Office 365 Configuration in general, but apply it only to our own tenant's Sharepoint online?

We want to maintain DLP and anti-malware controls on "foreign" Sharepoint sites.

ZIA - SSL Inspection

1 answer

299 views

mahantr

likes this.

Ramesh M

(Employee)

5 months ago

Yes, you can do SSL inspection for specific URL MS URL. leverage cloud APP instance features as well for better control.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2021

at

07:54 PM

Youtube Restricted Mode Bypass Safe Search

ZIA - SSL Inspection

JonM

8 Views

1 Like

5 Comments

11/24/2021

at

06:09 AM

Reflection time of SSL inspection exclusion settings

ZIA - SSL Inspection

Nyajima

4 Views

0 Likes

1 Comment

4/26/2024

at

02:39 PM

End User Notifcation - Submit To Security Cloud option

ZIA - SSL Inspection

citrus3118

311

311 Views

0 Likes

1 Comment

8/21/2024

at

08:06 PM

StackHawk with Zscaler SSL Inspection

ZIA - SSL Inspection

User16182582818315999266

570

570 Views

0 Likes

4 Comments

3/10/2025

at

12:22 AM

Reputation Block policy

ZIA - SSL Inspection

JM

418

418 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Inspect Sharepoint traffic while using Microsoft-Recommended One Click config
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000pECG40AO/explicit-ftp-over-tls-configuration-required-in-ziaftp-client-issue","lastmod":"2026-03-02T09:42:32.000Z","id":"0D5PJ00000pECG40AO"} -->
## Explicit FTP over TLS configuration required in ZIA/FTP client - Issue

- Source: https://community.zscaler.com/s/question/0D5PJ00000pECG40AO/explicit-ftp-over-tls-configuration-required-in-ziaftp-client-issue
- Type: Q&A
- Last activity: 2026-03-02T09:42:32.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Cloud Firewall

injamamul.haque01

(Partner) asked a question.

February 12, 2026 at 11:55 AM

Explicit FTP over TLS configuration required in ZIA/FTP client - Issue

Can somebody help in the required configuration for allowing an explicit FTP over TLS connection via ZIA as I have already tried SSL bypassing, allowing via FTP control and configuring the FTP client with proxy but still couldn't get the issue to get resolved.

The traffic is showing allowed via Firewall insights on ZIA - FTPS (identified)

But the connection is timing out after every 20 seconds on the FTP client - Filezilla and winscp

ZIA - Cloud Firewall

1 answer

326 views

Ramesh M

(Employee)

5 months ago

Check if it resolves after increase the timeout settings in FileZilla - Edit > Settings > Connection , make it 300 seconds and verify.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2018

at

01:07 PM

Ip address as criteria in url policy

ZIA - Cloud Firewall

ram75

1,193

1193 Views

0 Likes

1 Comment

10/8/2020

at

08:41 AM

Block All access & Allow certain user or group (ZIA)

ZIA - Cloud Firewall

Sec_def_Def_sec

1,837

1837 Views

1 Like

2 Comments

12/19/2022

at

04:14 PM

URL filtering policy vs Cloud App policy control

ZIA - Cloud Firewall

Ahmed

4 Views

0 Likes

2 Comments

12/9/2022

at

09:40 PM

Apple News RSS Feed

ZIA - Cloud Firewall

Trace Woodbury-RidgeIT

2 Views

0 Likes

1 Comment

9/20/2022

at

03:20 PM

How does Zscaler Internet Access itself route the traffic to the internet, using what outgoing/next hop GW

ZIA - Cloud Firewall

tamerz

6 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Explicit FTP over TLS configuration required in ZIA/FTP client - Issue
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000pQHeR0AW/zia-internet-access-audit-logs-report-is-completed-check-api-url","lastmod":"2026-02-16T15:12:09.000Z","id":"0D5PJ00000pQHeR0AW"} -->
## ZIA (Internet Access) - Audit Logs Report  - Is Completed check API URL

- Source: https://community.zscaler.com/s/question/0D5PJ00000pQHeR0AW/zia-internet-access-audit-logs-report-is-completed-check-api-url
- Type: Q&A
- Last activity: 2026-02-16T15:12:09.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Logging

tamerz

(Customer) asked a question.

Edited February 15, 2026 at 1:42 PM

ZIA (Internet Access) - Audit Logs Report  - Is Completed check API URL

Hi everyone,

I hope my message finds you well,

ZIA cloud: zscloud

I have a question regarding the following API link

https://help.zscaler.com/legacy-apis/admin-audit-logs

/auditlogEntryReport

GET

Gets the status of a request for an audit log report. After sending a POST request to

/auditlogEntryReport

to generate a report, you can continue to call GET

/auditlogEntryReport

to check whether the report has finished generating. Once the status is

COMPLETE

, you can send another GET request to

/auditlogEntryReport/download

to download the report as a CSV file.

Parameters

No parameters

Is there any new official change in this API url, that check if the report is completed or not to trigger download ?

We think someone from ZIA team mentioned some new parameter as follows: GET

/auditlogEntryReport/statusId={{ReportId}}

Thanks,

Tamer

ZIA - Logging

165 views

Log In to Answer

Associated Tags

api

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

6/8/2023

at

08:51 PM

URL Reporting

ZIA - Logging

akita_down

12

12 Views

0 Likes

4 Comments

11/3/2023

at

06:08 PM

ZIA - NSS Feed Status

ZIA - Logging

Rallis

616

616 Views

0 Likes

3 Comments

2/21/2024

at

10:43 PM

Find if User is behind a VPN

ZIA - Logging

venG

543

543 Views

0 Likes

2 Comments

3/13/2025

at

01:56 AM

How to Extract Logs from Web Insight

ZIA - Logging

JSK27

670

670 Views

0 Likes

8 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

ZIA (Internet Access) - Audit Logs Report  - Is Completed check API URL
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000q659B0AQ/browser-isolation-policy-based-on-tenant-profiles","lastmod":"2026-03-05T01:34:02.000Z","id":"0D5PJ00000q659B0AQ"} -->
## Browser isolation policy based on tenant profiles

- Source: https://community.zscaler.com/s/question/0D5PJ00000q659B0AQ/browser-isolation-policy-based-on-tenant-profiles
- Type: Q&A
- Last activity: 2026-03-05T01:34:02.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Browser Isolation

avshch

(Customer) asked a question.

February 27, 2026 at 4:47 AM

Browser isolation policy based on tenant profiles

Could browser isolation be applied based on tenant profiles?

Thanks,

ZIA - Browser Isolation

5 answers

184 views

mahantr

likes this.

Ramesh M

(Employee)

5 months ago

You can have multiple isolation profiles and attached them with url / cloud app policies. The tenant profiles has to attach with the access control policies like URL / Cloud APP controls.

avshch

(Customer)

5 months ago

@Ramesh Mani​ basically you have to pick either isolation profile or tenant profile for processing, correct? There is no way to say isolate traffic for tenant ID 1 and don't isolate for tenant ID 2 for the same Cloud App (for example MSO365).

Ramesh M

(Employee)

5 months ago

Try if you can use, cloud instance and browser isolation instead of tenant restrictions.

Ramesh M

(Employee)

5 months ago

@Alex Shchukin​ , Which application you are trying to isolation and tenant restrict ?

avshch

(Customer)

5 months ago

Google Workspace.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

No posts to show.

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Browser isolation policy based on tenant profiles
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000qMy660AC/mbi-hicn-for-dlp-policies","lastmod":"2026-03-06T20:27:29.000Z","id":"0D5PJ00000qMy660AC"} -->
## MBI, HICN for DLP policies

- Source: https://community.zscaler.com/s/question/0D5PJ00000qMy660AC/mbi-hicn-for-dlp-policies
- Type: Q&A
- Last activity: 2026-03-06T20:27:29.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - DLP

avshch

(Customer) asked a question.

March 4, 2026 at 3:08 PM

MBI, HICN for DLP policies

In ref to MBI for DLP policies previously posted at https://community.zscaler.com/s/question/0D54u00009evn5vCAA/mbi-dlp-phi-indicator-add. With Zscaler's "A

dvanced classification features" it

appears there is a support for MBI's and HICN numbers now.

ZIA - DLP

124 views

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/9/2023

at

05:32 PM

DLP notification via Zscaler Client Connector

ZIA - DLP

thacarvalho123

7 Views

2 Likes

2 Comments

11/20/2022

at

09:26 PM

External DLP Engine

ZIA - DLP

H.zyD.zy

4 Views

0 Likes

3 Comments

11/9/2022

at

04:51 PM

Zscaler Preventing Image Upload and Download on iPhone

ZIA - DLP

MacDanorld1

2 Views

0 Likes

3 Comments

6/20/2022

at

07:53 AM

Error during Microsoft Information Protection(MIP) Integration

ZIA - DLP

ozanogur

3 Views

0 Likes

4 Comments

1/31/2021

at

08:35 PM

Zscaler ZIA and ZPA with NetSkope CASB and DLP services

ZIA - DLP

jonathan.holt

1,341

1341 Views

1 Like

6 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

MBI, HICN for DLP policies
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000qYjeQ0AS/just-a-bit-introduction-about-me","lastmod":"2026-03-20T06:23:13.000Z","id":"0D5PJ00000qYjeQ0AS"} -->
## Just a Bit Introduction about me

- Source: https://community.zscaler.com/s/question/0D5PJ00000qYjeQ0AS/just-a-bit-introduction-about-me
- Type: Q&A
- Last activity: 2026-03-20T06:23:13.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Reward

Parinoor_18

(Customer) asked a question.

March 8, 2026 at 10:07 AM

Just a Bit Introduction about me

Hello Zenith Community!

I am thrilled to officially join this space. I am currently a

2nd-year B.Tech Computer Science and Engineering student

, and I’ve been fascinated by how the shift to Zero Trust architecture is redefining modern cybersecurity.

My Aim in the Zenith Community:

Certification Goals:

I am focused on mastering the fundamentals and working toward my

ZTCA (Zero Trust Certified Associate)

certification.

Knowledge Sharing:

As a student and active freelancer, I want to bridge the gap between academic theory and the practical application of Zscaler’s cloud security solutions.

Networking:

I’m eager to connect with industry professionals and fellow students to stay updated on ZIA and ZPA trends.

I am looking forward to contributing to the discussions here and learning from the expertise of this community.

Quick Question for the Experts:

For someone just starting their Zscaler journey in university, what is the one "must-do" lab or module you would recommend for a solid foundation?

Looking forward to connecting with you all!

Reward

4 answers

190 views

Ramesh M

(Employee)

4 months ago

Go with elearning modules like Introduction to Networking for Cyber Professionals (EDU-101),  COURSE | Fundamentals of Cybersecurity (EDU-102), this will give you good understanding about the networking and cybersecurity.

2 likes

Parinoor_18

(Customer)

4 months ago

Thank You so much

GordonWright

(Customer)

4 months ago

The Zscaler YouTube channel is very good as well for high level overview of products and services.

1 like

Parinoor_18

(Customer)

4 months ago

Thank you! I will definitely go through it.

Log In to Answer

Associated Tags

certification

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/5/2022

at

09:04 AM

How to earn points after complete the Certification

Vinodth

10

10 Views

1 Like

9 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Just a Bit Introduction about me
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000qgjHc0AI/eun-customization-fetch-parameter-values","lastmod":"2026-03-15T17:30:29.000Z","id":"0D5PJ00000qgjHc0AI"} -->
## EUN customization - fetch parameter values

- Source: https://community.zscaler.com/s/question/0D5PJ00000qgjHc0AI/eun-customization-fetch-parameter-values
- Type: Q&A
- Last activity: 2026-03-15T17:30:29.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - CASB

vaibhav_j

(Customer) asked a question.

Edited March 10, 2026 at 5:17 PM

EUN customization - fetch parameter values

I like to pull and pass below ZScaler controlled value from EUN block message so flow will be as following:

when user click on URL which is blocked by cloud app policy then user will redirect to ZScaler EUN block page

if user want to know more about it then user can click on link surfaced at ZScaler EUN BLOCK page (this link which user user to know more about should pass below values as query string]

From

Configuring Block Notifications | Zscaler

> Custom section which is automatically passing different query parameter if 'Redirect URL' is enabled instead of EUN

ASK

: i like to fetch parameter value generated by ZScaler (like action, url, reasoncode, user etc..) at out of box EUN block message to consume (WITHOUT using 'Redirect URL' feature)

example : https://xxx.sharepoint.com/sites/xx-112/SitePages/denied.aspx?url=

{URL}

&user=

{USER}

(Iis it something possible?)

ZIA - CASB

98 views

Log In to Answer

Associated Tags

casb

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

4/6/2025

at

05:57 PM

Cloud App Control - Google Drive subactions

ZIA - CASB

Danielo

491

491 Views

0 Likes

7 Comments

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

EUN customization - fetch parameter values
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000rBEqx0AG/the-bsod-is-caused-by-zscaler-kernel-driver-zepprotectsys-zep-2508012","lastmod":"2026-05-18T19:59:10.000Z","id":"0D5PJ00000rBEqx0AG"} -->
## The BSOD is caused by Zscaler kernel driver zepprotect.sys (ZEP 25.08.0.12)

- Source: https://community.zscaler.com/s/question/0D5PJ00000rBEqx0AG/the-bsod-is-caused-by-zscaler-kernel-driver-zepprotectsys-zep-2508012
- Type: Q&A
- Last activity: 2026-05-18T19:59:10.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - SSL Inspection

iulian

(Customer) asked a question.

March 18, 2026 at 9:45 AM

The BSOD is caused by Zscaler kernel driver zepprotect.sys (ZEP 25.08.0.12)

Hi everyone,

We are experiencing consistent BSOD crashes caused by the Zscaler kernel driver

zepprotect.sys

(ZEP version 25.08.0.12).

Details:

Bugcheck: SYSTEM_SERVICE_EXCEPTION (0x3B)

Exception: 0xC0000005 (Access Violation)

Faulting module: zepprotect.sys

Crash point: zepprotect+0x31c6c

Root cause: confirmed null pointer dereference in kernel mode

Environment:

Windows (kernel build 26100)

VBS / HVCI enabled

WSL2 in use (Hyper-V active)

The crash occurs inside the Zscaler driver, not at application level (java.exe was only the active process at the time).

Has anyone encountered similar issues with this ZEP version or under VBS/HVCI environments? Any known fixes, patches, or recommended versions?

Thanks in advance.

ZIA - SSL Inspection

1 answer

1.43K views

Allen Geiser

(Employee)

2 months ago

The official fix is to upgrade to Zscaler Client Connector (ZCC) version 4.5.3 or higher. This release includes an updated driver that resolves the stability issues within VBS/HVCI environments.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2021

at

07:54 PM

Youtube Restricted Mode Bypass Safe Search

ZIA - SSL Inspection

JonM

8 Views

1 Like

5 Comments

11/24/2021

at

06:09 AM

Reflection time of SSL inspection exclusion settings

ZIA - SSL Inspection

Nyajima

4 Views

0 Likes

1 Comment

4/26/2024

at

02:39 PM

End User Notifcation - Submit To Security Cloud option

ZIA - SSL Inspection

citrus3118

311

311 Views

0 Likes

1 Comment

8/21/2024

at

08:06 PM

StackHawk with Zscaler SSL Inspection

ZIA - SSL Inspection

User16182582818315999266

570

570 Views

0 Likes

4 Comments

3/10/2025

at

12:22 AM

Reputation Block policy

ZIA - SSL Inspection

JM

418

418 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

The BSOD is caused by Zscaler kernel driver zepprotect.sys (ZEP 25.08.0.12)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000rJNtm0AG/how-to-restrict-wechat-work-login-and-usage-in-zia","lastmod":"2026-04-29T21:43:16.000Z","id":"0D5PJ00000rJNtm0AG"} -->
## How to restrict WeChat Work login and usage in ZIA

- Source: https://community.zscaler.com/s/question/0D5PJ00000rJNtm0AG/how-to-restrict-wechat-work-login-and-usage-in-zia
- Type: Q&A
- Last activity: 2026-04-29T21:43:16.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Cloud Firewall

User16692696236069260570

(Customer) asked a question.

March 20, 2026 at 3:30 AM

How to restrict WeChat Work login and usage in ZIA

Hello

I've already added restrictions for WeChat Work in ZIA's Cloud App Control Policy, but WeChat Work still prompts me to scan a QR code and log in successfully after phone authentication. How can I restrict WeChat Work logins?

ZIA - Cloud Firewall

1 answer

186 views

Pablo Guzman

(Employee)

3 months ago

To reliably block WeChat Work logins in ZIA, add a firewall-level block and cover both cloud apps. Cloud App Control alone often won’t stop QR-code–based login flows.

Policy > Firewall Filtering: add a rule with Network Applications = WeChat; Action = Block/Drop; place high in the rule order. Note: requires Advanced Cloud Firewall, all traffic must be forwarded to ZIA (Z-Tunnel 2.0/GRE/IPsec), and existing logged-in sessions may persist until the user logs out or switches networks

Policy > Cloud App Control: add Block rules for both apps:

WeChat (Instant Messaging) — covers domains like .

weixin.qq.com

, .

wechat.com

, .

wx.qq.com

, .

wx2.qq.com

WeChat Work (Collaboration and Online Meetings) — covers .

wxwork.qq.com

, .

wework.ftn.qq.com

, .

wxworklive.com

, .

wework.qpic.cn

Ensure you don’t have an SSL rule that uses “Bypass all policies” for WeChat/WeCom domains; that exemption would override your block policies

(Optional hard stop) URL Filtering: explicitly Block key login endpoints used by WeChat Work (e.g.,

i.work.weixin.qq.com

work.weixin.qq.com

open.work.weixin.qq.com

) to stop the web session established after QR scan

Test guidance: have users sign out of WeChat/WeCom and retry (or toggle network) so new flows hit the block; previously established sessions may continue until reauthentication

If you lack Advanced Cloud Firewall, rely on the dual Cloud App Control blocks plus targeted URL Filtering for the login domains; coverage may be less complete due to dynamic endpoints

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2018

at

01:07 PM

Ip address as criteria in url policy

ZIA - Cloud Firewall

ram75

1,191

1191 Views

0 Likes

1 Comment

10/8/2020

at

08:41 AM

Block All access & Allow certain user or group (ZIA)

ZIA - Cloud Firewall

Sec_def_Def_sec

1,821

1821 Views

1 Like

2 Comments

12/19/2022

at

04:14 PM

URL filtering policy vs Cloud App policy control

ZIA - Cloud Firewall

Ahmed

4 Views

0 Likes

2 Comments

12/9/2022

at

09:40 PM

Apple News RSS Feed

ZIA - Cloud Firewall

Trace Woodbury-RidgeIT

2 Views

0 Likes

1 Comment

9/20/2022

at

03:20 PM

How does Zscaler Internet Access itself route the traffic to the internet, using what outgoing/next hop GW

ZIA - Cloud Firewall

tamerz

6 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

How to restrict WeChat Work login and usage in ZIA
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000rRUmw0AG/getting-icmp-drop-for-google-or-any-other-destination","lastmod":"2026-04-29T21:40:34.000Z","id":"0D5PJ00000rRUmw0AG"} -->
## Getting icmp drop for google or any other destination

- Source: https://community.zscaler.com/s/question/0D5PJ00000rRUmw0AG/getting-icmp-drop-for-google-or-any-other-destination
- Type: Q&A
- Last activity: 2026-04-29T21:40:34.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

Arun

(Partner) asked a question.

March 23, 2026 at 7:55 AM

Getting icmp drop for google or any other destination

Whenever we do continous ping to google or other url, then we can see the RTOs & again gets reply.So is this normal

behaviour

or an issue?

Please share if any document available.

ZIA - URL Filtering

1 answer

323 views

Pablo Guzman

(Employee)

3 months ago

Intermittent ICMP ping RTOs are usually normal. Many networks rate-limit or deprioritize ICMP, and Zscaler data centers intentionally deprioritize ICMP replies; this can show drops in ping without real traffic loss

What to do instead:

Use a TCP/HTTP check (e.g., HTTP GET) or the Zscaler Cloud Performance Test (HTTP ping, Cloud Path) to validate true user experience

If you run traceroute/MTR, judge loss at the final hop; mid-path loss alone often reflects ICMP rate limiting

If apps work fine while pings show occasional RTOs, it’s expected behavior, not an issue

References:

Measuring the Performance of the Zscaler Service (why not to rely on ping; ICMP deprioritization):

https://help.zscaler.com/zia/measuring-performance-zscaler-service

Validate packet loss at Zscaler DC (ICMP deprioritization details; ~200 ICMP/s cap):

https://confluence.corp.zscaler.com/spaces/~MMir/pages/342903890/Validate+packet+loss+at+Zscaler+DC

Using the Zscaler Cloud Performance Test Tool (HTTP ping and Cloud Path):

https://help.zscaler.com/zia/using-zscaler-cloud-performance-test-tool

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,930

5930 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

895

895 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Getting icmp drop for google or any other destination
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000rX3QU0A0/email-dlp-inspection-for-internal-emails","lastmod":"2026-04-04T02:55:52.000Z","id":"0D5PJ00000rX3QU0A0"} -->
## Email DLP - inspection for internal emails

- Source: https://community.zscaler.com/s/question/0D5PJ00000rX3QU0A0/email-dlp-inspection-for-internal-emails
- Type: Q&A
- Last activity: 2026-04-04T02:55:52.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Policy Rules

JB

(Partner) asked a question.

Edited by Ben_Garrison March 26, 2026 at 6:02 PM

Email DLP - inspection for internal emails

<p>Hi,</p><p><br></p><p>As part of our email DLP inspection requirements for Microsoft Exchange, we want to ensure that emails sent within our organization are also inspected. Specifically, for our domain, any emails sent by our employees to other internal users (within the same domain) should be inspected by Zscaler.</p><p>Based on our understanding, the default Zscaler configuration does not inspect internal (intra-domain) email traffic.</p><p>Could you please advise on the required configuration changes or steps in Microsoft 365 / Exchange Online to enable inspection of internal emails via Zscaler?</p><p><br></p><p>Regards,</p><p> </p>

Policy Rules

1 answer

206 views

Ramesh M

(Employee)

4 months ago

Zscaler Outbound Email Data Loss Prevention (DLP) stops the exfiltration of sensitive data by enforcing policy rules on

outbound email content sent to external domains

, including content in subject lines, body text, and attachments. Using connectors and rules, your email server sends email to, and receives email from, the Zscaler smart host. The Zscaler smart host receives the email and sends it to the Zscaler DLP service for inspection. The Zscaler DLP service then inspects the email content for sensitive data, adding headers that define DLP actions to emails that trigger outbound email policy. When your email server receives inspected email from the Zscaler smart host, it uses those headers to determine enforcement actions. This is only for external domain -outbound mails not for internal. You can look rules and action around the MS365 itself.

Log In to Answer

Associated Tags

accesspolicy

automation

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

No posts to show.

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Email DLP - inspection for internal emails
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ts3UU0AY/best-practices-on-gre-tunnel-with-pac-file","lastmod":"2026-07-30T14:51:22.000Z","id":"0D5PJ00000ts3UU0AY"} -->
## Best practices on GRE Tunnel  with PAC file

- Source: https://community.zscaler.com/s/question/0D5PJ00000ts3UU0AY/best-practices-on-gre-tunnel-with-pac-file
- Type: Q&A
- Last activity: 2026-07-30T14:51:22.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

SriNarasimha

(Customer) asked a question.

May 1, 2026 at 9:06 AM

Best practices on GRE Tunnel  with PAC file

Hi There,

At present, we have corporate users (Saml:OKTA) utilizing PAC/ZCC tunnel1.0, with traffic being routed to the Internet through Zscaler via the 'Direct'/locally. The web traffic is being routed through Zscaler, while non-web traffic is being forwarded locally. The Public IP address is registered at a location with Surrogate IP enabled.

In accordance with compliance policy, there is a requirement that non-web traffic must also be routed to Zscaler using GRE.

Given this situation, should I bypass any Zscaler control plane traffic (for example, PAC retrieval and ZCC updates) through 'Direct' Internet, followed by routing Data-plane user traffic (such as web traffic and non-web traffic) via GRE?

Alternatively, should I route EVERYTHING through the tunnel?

Can you please help to share the best practices to avoid any connectivity issues? Thank you.

ZIA - Forwarding

4 answers

852 views

jmoulton

(Employee)

2 months ago

Sri,

When deploying GRE tunnels in conjunction with PAC (Proxy Auto-Configuration) files, the goal is to ensure high availability, optimal performance, and a seamless transition for users moving between corporate and roaming environments.

Here are some best practices to follow:

1. High Availability and Redundancy

Dual Tunnel Architecture: Always configure a primary and a secondary GRE tunnel. These should terminate at different Zscaler Data Centers (ZDCs) to ensure service continuity if one site experiences an outage.

Health Monitoring: Implement automated failover mechanisms. Use GRE Keepalives or Layer 7 health checks (e.g., Cisco IP SLA or HTTP probes) to monitor the health of the primary tunnel and automatically reroute traffic to the secondary if the primary fails.

Source IP Stability: Ensure the source public IP address of your GRE tunnel is static. If your egress IP changes, the GRE tunnel will drop, as Zscaler identifies your traffic based on that source IP.

2. MTU and MSS Optimization

GRE adds a 24-byte header to packets, which can lead to fragmentation if the standard 1500-byte MTU is used. To prevent performance degradation:

Tunnel MTU: Set the GRE tunnel MTU to 1476.

TCP MSS: Set the Maximum Segment Size (MSS) to 1436. This ensures that the TCP payload, plus the TCP/IP and GRE headers, does not exceed the physical interface's MTU.

3. PAC File Logic for GRE Tunnels

A well-designed PAC file is critical for ensuring that traffic is steered correctly whether the user is inside or outside the tunnel.

Use Dynamic Variables: Use ${GATEWAY} and ${SECONDARY_GATEWAY} variables in the PAC file. When a user is behind a GRE tunnel, these variables resolve to the internal Zscaler Service Edge IP of the data center where the tunnel terminates.

Geoproximity Routing: When the user is roaming (outside the GRE tunnel), these same variables resolve to the nearest public Zscaler node based on the user's current egress IP.

Internal Traffic Handling: Ensure your PAC file contains DIRECT statements for internal applications and local subnets to prevent internal traffic from being unnecessarily sent through the tunnel.

4. PAC File Performance (Optimization) - Large or inefficient PAC files can cause browser latency.

Avoid DNS Functions: Minimize the use of dnsResolve(), isResolvable(), and isInNet() when they require a DNS lookup. These functions are "blocking" and can significantly slow down page load times.

Use shExpMatch: Use shell expression matching for domain lists instead of complex regular expressions where possible.

Order of Operations: Place the most frequently hit rules (like internal bypasses) at the top of the PAC file to reduce the processing time for each request.

5. Traffic Splitting and Bypasses

Trusted Apps: Explicitly bypass performance-sensitive or trusted traffic that doesn't require inspection (e.g., Microsoft 365 or Zoom) if your organization's policy allows.

Zscaler Control Plane: Ensure that traffic destined for Zscaler infrastructure (like ZCC enrollment or policy updates) is bypassed from the tunnel to avoid "tunnel-in-tunnel" encapsulation issues.

2 likes

SriNarasimha

(Customer)

2 months ago

Hi @Jeremy Moulton​

Thanks for your reply. While we're discussing on the best practices, can you please assist on how to reach the PAC file?

Should I bypass any Zscaler control plane traffic (for example, PAC retrieval and ZCC updates) through 'Direct' Internet, followed by routing Data-plane user traffic (such as web traffic and non-web traffic) via GRE?

Alternatively, should I route EVERYTHING (including PAC file download) through the GRE tunnel?

Ecruz lZ30030

(Employee)

5 days ago

Hello @Sri Narasimha​ ,

That is correct. Please make sure Zscaler services, including PAC File downloads are bypassed through DIRECT internet from PAC File while keeping user traffic (such as web traffic and non-web traffic) via GRE.

GordonWright

(Customer)

2 months ago

Stealing these best practices :)

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

490

490 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Best practices on GRE Tunnel  with PAC file
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000uFvkW0AS/ai-application-login-restriction-corporate-account-ok-personal-account-no","lastmod":"2026-05-08T15:02:31.000Z","id":"0D5PJ00000uFvkW0AS"} -->
## AI Application Login Restriction (Corporate Account OK, Personal Account NO)

- Source: https://community.zscaler.com/s/question/0D5PJ00000uFvkW0AS/ai-application-login-restriction-corporate-account-ok-personal-account-no
- Type: Q&A
- Last activity: 2026-05-08T15:02:31.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Browser Isolation

byeongyul.ha

(Customer) asked a question.

May 8, 2026 at 2:22 AM

AI Application Login Restriction (Corporate Account OK, Personal Account NO)

I would like to restrict the use of AI Applications with personal accounts.

Corporate account login: Allow unrestricted use

Personal account login: Block login or prevent app execution

Can this be implemented through Tenant Profiles configuration?

ZIA - Browser Isolation

1 answer

501 views

jmoulton

(Employee)

3 months ago

Yes, you can implement this restriction using Tenant Profiles in conjunction with Cloud App Control policies. This configuration allows you to distinguish between your corporate-managed instance of an AI application and a personal account.

To restrict AI applications to corporate accounts only, you follow a three-step process:

Key Requirements & Capabilities

SSL Inspection: You must have SSL Inspection enabled for the AI application's URL. Zscaler needs to inspect the HTTPS headers to see the tenant information (like the Workspace ID) to determine if it matches your corporate profile.

Preventing Execution: Beyond just blocking login, you can use AI Guard and Data Loss Prevention (DLP) features to prevent the execution of prompts. For example, you can allow a user to log in but block them from "Chatting" or "Uploading" files if the content contains sensitive information.

Granular Controls: Within the Cloud App Control policy, you can specifically allow or block actions such as:

Post/Chat: Preventing the user from sending messages.

Upload/Download: Preventing file transfers to the AI service.

This method is commonly used for:

ChatGPT Enterprise: Using the Workspace ID.

Google Gemini: Using corporate domains.

Microsoft Copilot: Using the Tenant ID/Tenant Profile.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

No posts to show.

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

AI Application Login Restriction (Corporate Account OK, Personal Account NO)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000uRiG30AK/cloud-browser-isolation-how-are-folks-using-it","lastmod":"2026-05-28T00:54:42.000Z","id":"0D5PJ00000uRiG30AK"} -->
## Cloud Browser Isolation - How Are Folks Using It?

- Source: https://community.zscaler.com/s/question/0D5PJ00000uRiG30AK/cloud-browser-isolation-how-are-folks-using-it
- Type: Q&A
- Last activity: 2026-05-28T00:54:42.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Browser Isolation

jlw52761

(Customer) asked a question.

May 11, 2026 at 9:58 PM

Cloud Browser Isolation - How Are Folks Using It?

I am working on rolling out Cloud Browser Isolation (CBI) using a combination of Cloud App Policies and URL Filtering Rules. Primary goal is to target a couple Cloud App Categories based on risk, and using a custom tag to force allow Line of Business (LoB) apps.

So what I've done is setup three Isolation Profiles, High Trust, Medium Trust, and Zero Trust, and changed the level of isolation in each one. For instance, High Trust allows for copy/paste, upload/download, and Zero Trust allows none of that.

Then I created matching Risk Profiles, High Risk = Risk Index 4, Medium Risk = Risk Index 3, Low Risk = Risk Index 2, then using Cloud App Policy map those across so High Risk Consumer apps are sent to the Zero Trust Isolation Profile, and Low Risk Consumer apps are sent to the Low Risk Isolation Profile. Anything with a Risk Index of 5 is outright blocked. Overriding that is a forced allow, Cloud Apps that are tagged with my custom tag are matched in this policy and allowed regardless of Risk Index.

What are other folks doing in this space? I'm curious if I'm trying to implement too much at once or not. I'm only targeting the high risk, low impact Cloud App Categories; Consumer, AI & ML, File Sharing, Social Media, Streaming Services, and Web Mail.

The intent is maximum protection, lowest risk, with maximum permissiveness.

ZIA - Browser Isolation

4 answers

479 views

oliver.skibbe

likes this.

avshch

(Customer)

Edited May 23, 2026 at 11:23 PM

We use Cloud Browser Isolation for "Miscellaneous or Unknown" category assigned to sites that have not yet been classified by Zscaler. Another use case is for vendor's who need access to email platforms (MS Outlook etc.), which are different from ours (Gmail).  Just to clarify this is applicable to Cloud Browser isolation, which is based on Zscaler ZIA. There is another Zscaler browser isolation product, which is based on ZPA. It is called Remote Browser Isolation

https://www.zscaler.com/resources/security-terms-glossary/what-is-remote-browser-isolation

.  The use cases are different compare to Cloud Browser Isolation.

jlw52761

(Customer)

3 months ago

Definitely target Web Mail for isolation and exclude M365 from that. It would be nice to be able to use our Tenant Profile for that, but for now it's seems to work.

I definitely also want to change the "Miscellaneous or Unknown" category from Blocked to full Isolation, that's the bulk of my tickets is those unknown domains that haven't had time to churn through ThreatLabz yeat.

Raj909

(Customer)

2 months ago

We use CBI for the following URL categories - Other Miscellaneous;  Other Security;  Miscellaneous or Unknown.  Also, Enable AI/ML based Smart Browser Isolation under Secure Browsing | Smart Isolate.

JohnBuxbaum

(Customer)

2 months ago

i think that the guidance from Zscaler Professional Services has been spot on - Misc, newly registered and unknown.

thats how we have been using CBI. its been pretty successful for our users.

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

No posts to show.

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Cloud Browser Isolation - How Are Folks Using It?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000uv12T0AQ/how-to-block-ms-office-copilot-chat-gcc-high-tenants","lastmod":"2026-06-04T15:55:13.000Z","id":"0D5PJ00000uv12T0AQ"} -->
## How to block MS Office Copilot Chat (GCC High tenants)?

- Source: https://community.zscaler.com/s/question/0D5PJ00000uv12T0AQ/how-to-block-ms-office-copilot-chat-gcc-high-tenants
- Type: Q&A
- Last activity: 2026-06-04T15:55:13.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

avshch

(Customer) asked a question.

Edited May 27, 2026 at 1:13 PM

How to block MS Office Copilot Chat (GCC High tenants)?

Is there any way to block access to Microsoft Office GCC High Copilot Chat with Zscaler?

ZIA - URL Filtering

3 answers

391 views

Allen Geiser

(Employee)

2 months ago

Yes, you can block access to Microsoft Copilot (formerly Bing Chat) using Zscaler through several layers of policy. Because Copilot is integrated into multiple Microsoft services, a multi-layered approach is often most effective.

1. Using Cloud App Control (Recommended)

Zscaler has a dedicated category for AI and ML applications within its Cloud App Control engine. This is the simplest and most effective method.

Navigate to:

Policy > Cloud App Control > AI & ML Applications

Action: Click Add Cloud App Control Rule.

Rule Configuration:

Cloud Applications: Select Microsoft Copilot.

Action: Set to Block.

Granular Control: Alternatively, you can allow access but block specific activities like  Upload or Post to prevent data leakage while allowing the chat interface to remain visible.

2. URL Filtering & Custom Categories

For more granular control or as a secondary layer, you can block the specific domains associated with Copilot.

Domains to Block:

copilot.microsoft.com

bing.com/chat

sydney.bing.com

Configuration: Create a Custom URL Category containing these domains and then create a URL Filtering Policy to block that category.

3. Redirecting to "No Chat" Bing

Microsoft provides a specific endpoint for organizations that want to allow Bing search but disable the chat component.

Method: Create a rule (using DNS or Zscaler NAT Control) to redirect traffic for

www.bing.com

to

nochat.bing.com

This ensures that the "Chat" tab and the Copilot sidebar do not appear when users visit Bing.

4. Critical Prerequisite: SSL Inspection

For any of the above methods to work reliably, SSL Inspection must be enabled for these domains.

Because these services use HTTPS, Zscaler cannot "see" the specific URLs or the application type (Cloud App Control) unless it is decrypting the traffic.

Check Bypasses: Ensure that your "Microsoft One Click" or "Office 365" bypass settings are not inadvertently bypassing these Copilot domains from inspection.

avshch

(Customer)

2 months ago

The problem is we are trying to block MS Copilot which is a part of MS Office 365  (

https://www.office365.us/chat/

) which is categorized by Zscaler as a cloud app "Productivity and CRM Tools - Common Office 365 Applications" not as "AI & ML Applications . This does not give an option to block it w/o affecting other MS Office 365 common applications.

Subrata Ghosh

(Partner)

a month ago

Use Zscaler Cloud App Control (Primary Control)

Go to ZIA → Policy → Cloud App Control

Search for applications like:

Microsoft Copilot

Bing Chat / Copilot

Generative AI applications

Create a rule:

Action: Block

Users: Required users or all users

Place this rule above allow policies

This is the safest and most effective way to control Copilot.

URL Filtering (Backup Control)

Create a custom URL category (example: Block-MS-Copilot)

Add the following URLs:

copilot.microsoft.com

bing.com/chat

bing.com/copilot

edge.microsoft.com/copilot

Then create a URL filtering policy to block this category.

Enable SSL Inspection

Copilot traffic is encrypted, so without SSL inspection you may not get full visibility or control.

Enable SSL inspection for Microsoft AI and Bing-related traffic.

Optional – DLP Control

If you do not want to fully block Copilot, you can allow it but use DLP policies to prevent sensitive data from being shared.

Avoid Blocking These (Important)

Do NOT block:

graph.microsoft.com

login.microsoftonline.com

office.com

Blocking these will break Microsoft 365 services.

Microsoft Tenant-Level Control (Very Important)

Zscaler alone is not enough.

You should also disable Copilot from Microsoft side:

Microsoft 365 Admin Center

Purview policies

Conditional Access

Best Practice Strategy:

Use App Control as primary enforcement

Use URL filtering as backup

Enable SSL inspection

Disable Copilot at Microsoft tenant level

Impact:

App Control – minimal impact

URL blocking – may affect Bing features slightly

Blocking Graph API – breaks M365 (avoid)

Tenant-level control – most effective

Summary:

Treat Copilot as a data security risk, not just a website.

Combine network control (Zscaler) with identity and policy control (Microsoft).

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,913

5913 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

888

888 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

How to block MS Office Copilot Chat (GCC High tenants)?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000uzLQn0AM/antigravity-zia","lastmod":"2026-05-26T17:56:34.000Z","id":"0D5PJ00000uzLQn0AM"} -->
## Antigravity & ZIA

- Source: https://community.zscaler.com/s/question/0D5PJ00000uzLQn0AM/antigravity-zia
- Type: Q&A
- Last activity: 2026-05-26T17:56:34.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - SSL Inspection

LuckySeven

(Customer) asked a question.

Edited May 21, 2026 at 8:12 AM

Antigravity & ZIA

Hello all,

Did anybody get Antigravity (incl. Login) to work, with ZIA running and without disabling SSL Inspections for .googleapis.com ?

I also looked into process based exceptions but this is a pain as well because of the way Antigravity is built. Zscaler support just told me to ask Google on what to bypass, Google says they don't support any SSL inspection for Antigravity.

Did anybody had any luck?

Thanks

edit:_________

It worked for us now with the newest version of Antigravity, the certificate just needs to be added to Node. Following the script we used:

#!/bin/zsh

# Create file containing all macOS trusted root certs, plus Zscaler RootCA

security find-certificate -a -p /System/Library/Keychains/SystemRootCertificates.keychain > /Users/Shared/zscaler-for-developers/CAbundle.pem

cat /Users/Shared/zscaler-for-developers/ZscalerRootCA.pem >> /Users/Shared/zscaler-for-developers/CAbundle.pem

# Set the NODE_EXTRA_CA_CERTS environment variable

echo 'export NODE_EXTRA_CA_CERTS=/Users/Shared/zscaler-for-developers/CAbundle.pem' >> ~/.zshrc

source ~/.zshrc

ZIA - SSL Inspection

369 views

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2021

at

07:54 PM

Youtube Restricted Mode Bypass Safe Search

ZIA - SSL Inspection

JonM

8 Views

1 Like

5 Comments

11/24/2021

at

06:09 AM

Reflection time of SSL inspection exclusion settings

ZIA - SSL Inspection

Nyajima

4 Views

0 Likes

1 Comment

4/26/2024

at

02:39 PM

End User Notifcation - Submit To Security Cloud option

ZIA - SSL Inspection

citrus3118

311

311 Views

0 Likes

1 Comment

8/21/2024

at

08:06 PM

StackHawk with Zscaler SSL Inspection

ZIA - SSL Inspection

User16182582818315999266

574

574 Views

0 Likes

4 Comments

3/10/2025

at

12:22 AM

Reputation Block policy

ZIA - SSL Inspection

JM

420

420 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Antigravity & ZIA
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000vOmKo0AK/private-ip-192168167-is-intercepted-by-zscaler-is-there-anyway-to-bypass-it","lastmod":"2026-06-03T03:00:37.000Z","id":"0D5PJ00000vOmKo0AK"} -->
## private ip 192.168.1.67 is intercepted by zscaler . is there anyway to bypass it

- Source: https://community.zscaler.com/s/question/0D5PJ00000vOmKo0AK/private-ip-192168167-is-intercepted-by-zscaler-is-there-anyway-to-bypass-it
- Type: Q&A
- Last activity: 2026-06-03T03:00:37.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

User16884610075752258444

(Customer) asked a question.

May 27, 2026 at 2:03 PM

private ip 192.168.1.67 is intercepted by zscaler . is there anyway to bypass it

private ip 192.168.1.67 is intercepted by zscaler . is there anyway to bypass it ? n why its showing that ip's traffic

ZIA - Forwarding

2 answers

222 views

JohnBuxbaum

(Customer)

2 months ago

How about in the ZCC profile being used?

User16884610075752258444

(Customer)

2 months ago

it is application issue ..it is resolved.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

492

492 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

private ip 192.168.1.67 is intercepted by zscaler . is there anyway to bypass it
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000w0I2f0AE/gemini-and-other-ai-apps-no-ssl-decryption","lastmod":"2026-06-18T15:13:29.000Z","id":"0D5PJ00000w0I2f0AE"} -->
## Gemini and other AI apps - No SSL Decryption

- Source: https://community.zscaler.com/s/question/0D5PJ00000w0I2f0AE/gemini-and-other-ai-apps-no-ssl-decryption
- Type: Q&A
- Last activity: 2026-06-18T15:13:29.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - SSL Inspection

skywalker

(Partner) asked a question.

June 5, 2026 at 4:48 PM

Gemini and other AI apps - No SSL Decryption

Hello , I have ZCC tunnel 2.0 and my top SSL rule is to have all AI apps Gemini , claude , etc to be inspected . I also have top firewall rule to block QUIC for everybody .

WHat i am seeing is when i browse gemini ,ssl inspection works intermittently . when i enter my google creds to login to my gemini account , and then if i ask some question to LLM , and then if i check the certificate it shows Google's own cert and not Zscaler

Same with claude and chatgpt . The goal here is not to see what is being asked to LLM to make some broader strict controls . but SSL decryption being intermittent in this case is the problem.

I have Zscaler Platform license .

Any idea what is causing this ?

ZIA - SSL Inspection

1 answer

386 views

Ramesh M

(Employee)

a month ago

You would need to work with support team and understand if this is failing when traffic goes to  specific instance or all the instances. Try with another browser and try disable QUIC in browser level.

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2021

at

07:54 PM

Youtube Restricted Mode Bypass Safe Search

ZIA - SSL Inspection

JonM

8 Views

1 Like

5 Comments

11/24/2021

at

06:09 AM

Reflection time of SSL inspection exclusion settings

ZIA - SSL Inspection

Nyajima

4 Views

0 Likes

1 Comment

4/26/2024

at

02:39 PM

End User Notifcation - Submit To Security Cloud option

ZIA - SSL Inspection

citrus3118

309

309 Views

0 Likes

1 Comment

8/21/2024

at

08:06 PM

StackHawk with Zscaler SSL Inspection

ZIA - SSL Inspection

User16182582818315999266

566

566 Views

0 Likes

4 Comments

3/10/2025

at

12:22 AM

Reputation Block policy

ZIA - SSL Inspection

JM

415

415 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Gemini and other AI apps - No SSL Decryption
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000w5mJD0AY/feature-request-http-header-profile-is-enabled-by-default","lastmod":"2026-06-15T15:41:29.000Z","id":"0D5PJ00000w5mJD0AY"} -->
## Feature Request - HTTP Header Profile is Enabled by Default

- Source: https://community.zscaler.com/s/question/0D5PJ00000w5mJD0AY/feature-request-http-header-profile-is-enabled-by-default
- Type: Q&A
- Last activity: 2026-06-15T15:41:29.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

Frazza

(Partner) asked a question.

June 8, 2026 at 3:04 AM

Feature Request - HTTP Header Profile is Enabled by Default

Making a feature request here for HTTP Header Profiles to be enabled by DEFAULT and not a back-end feature flag request (

https://help.zscaler.com/zia/about-http-header-profile

). From a partner perspective and having done many deployments, this feature request is almost always needed for improved fine-tuning of policies that adds flexibility in addressing edge cases. A common example is Windows Delivery Optimization traffic for Windows updates. This traffic is often IP-based and categorised as Miscellaneous by Zscaler. However, the User-Agent HTTP request header almost always contains the

Windows-Delivery-Optimization

User-Agent value. It's far more reliable and accurate to target this traffic via HTTP header profiles than using URL category keyword matching.

Additionally, Zscaler's major competitor, Netskope, offer HTTP Header Profiles out the box without any feature request required, saving deployment partners lots of time and providing the flexibility straight away to fine-tune policies. My ask is that this is enabled by default by Zscaler as I see no reason why additional time needs to be spent asking support to enable this backend flag.

Thanks,

Fraser

ZIA - URL Filtering

1 answer

237 views

Ramesh M

(Employee)

a month ago

You can reach out your account team to have this feature by default.  Sometimes a ER request is enough to have this by default.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,930

5930 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

895

895 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Feature Request - HTTP Header Profile is Enabled by Default
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000wQ4LL0A0/zia-authentication-bypass-for-the-specific-user-or-usergroup","lastmod":"2026-06-12T14:34:15.000Z","id":"0D5PJ00000wQ4LL0A0"} -->
## ZIA authentication bypass for the Specific User or User-group

- Source: https://community.zscaler.com/s/question/0D5PJ00000wQ4LL0A0/zia-authentication-bypass-for-the-specific-user-or-usergroup
- Type: Q&A
- Last activity: 2026-06-12T14:34:15.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - URL Filtering

RKV

(Partner) asked a question.

June 12, 2026 at 1:01 AM

ZIA authentication bypass for the Specific User or User-group

Hi Team,

Greetings !

I’d like to confirm whether it is possible to configure ZIA authentication bypass for a specific FQDN, limited to a designated user group. Could you please advise on the feasibility and recommended approach?

ZIA - URL Filtering

1 answer

1.05K views

avshch

(Customer)

2 months ago

You can do auth-bypass per cloud app. I'm not sure if it could be applied on per user group level.

https://help.zscaler.com/zia/exempting-urls-cloud-apps-authentication

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

12/2/2022

at

06:35 PM

SafeSearch Forces YouTube into Restricted Mode

ZIA - URL Filtering

Chunter

5 Views

0 Likes

3 Comments

9/29/2022

at

08:51 PM

What takes precedence - File type control or Cloud App Control

ZIA - URL Filtering

cnicholas

5 Views

0 Likes

2 Comments

11/24/2021

at

08:22 AM

How to prioritize user-defined URLs over URLs defined in the super category

ZIA - URL Filtering

Nyajima

4 Views

0 Likes

4 Comments

2/23/2020

at

11:49 AM

Cloud App Control - whatsapp web

ZIA - URL Filtering

eli.shauly

5,932

5932 Views

2 Likes

20

20 Comments

10/24/2019

at

03:36 PM

Automated way of Cleaning Known Malicious Sites

ZIA - URL Filtering

chsmith

896

896 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

ZIA authentication bypass for the Specific User or User-group
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000wnVEs0AM/zscaler-zia-scim-provisioning-for-entra-id","lastmod":"2026-06-26T16:20:39.000Z","id":"0D5PJ00000wnVEs0AM"} -->
## Zscaler ZIA SCIM Provisioning for Entra ID

- Source: https://community.zscaler.com/s/question/0D5PJ00000wnVEs0AM/zscaler-zia-scim-provisioning-for-entra-id
- Type: Q&A
- Last activity: 2026-06-26T16:20:39.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Authentication

CPhillips

(Customer) asked a question.

June 18, 2026 at 1:29 PM

Zscaler ZIA SCIM Provisioning for Entra ID

We've been a customer of Zscaler (ZIA) for ~8yrs now and love the product. But would it be too hard to have an intern or entry level Zscaler employee update their documentation for SCIM provisioning with MSFT Entra? I know you should pretty much set it up and forget it, but we had an issue with ours (groups changing in Entra) and I decided that because it's been a handful of years since it was originally setup, I'd just start from scratch and setup a new Enterprise App in Entra. Yeah...thanks Zscaler (and MSFT for that matter), referencing menus that no longer exist and Enterprise apps that don't work with SAML.

I vaguely remember this being an issue when we originally set SAML/SCIM up years ago. I understand Entra is a moving target with it's never ending changes, but Zscaler is no different as well as most SaaS providers for that matter. So please Zscaler, don't use this as an excuse. I mean setting up an idp and SCIM provisioning is the first thing you do as a new customer, so I'd think they'd want to make a good first impression. Maybe?

No answers needed. Just a Thursday morning rant.

ZIA - Authentication

2 answers

288 views

Ben_Garrison

(Employee)

a month ago

Hey, thanks for that. We do have a process to request for documentation updates. Can you provide which articles need to be updated and I will be sure to submit that request for you!

CPhillips

(Customer)

a month ago

Hi Ben,

Sure, here are the links that need updating.

https://help.zscaler.com/zia/saml-scim-configuration-guide-microsoft-entra-id

https://help.zscaler.com/zia/oauth-2.0-configuration-guide-microsoft-entra-id

Chris

Log In to Answer

Associated Tags

azure-ad

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/15/2022

at

12:39 PM

Onprem Active directory integrate with Zscaler cloud ZIA

ZIA - Authentication

aravindhan.m

3 Views

0 Likes

3 Comments

7/11/2022

at

07:26 AM

Zscaler IDP authentication issue

ZIA - Authentication

aravindhan.m

4 Views

0 Likes

2 Comments

5/27/2022

at

01:49 PM

Zscaler ZIA O365 Apps not authenticating

ZIA - Authentication

abmaclean

4 Views

0 Likes

6 Comments

10/4/2021

at

10:43 PM

Automatic de-provisioning using SCIM with Azure AD

ZIA - Authentication

Paul_Wineberg

4 Views

0 Likes

4 Comments

6/27/2020

at

03:01 PM

Zscaler public preview

ZIA - Authentication

Muhammad

364

364 Views

0 Likes

2 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Zscaler ZIA SCIM Provisioning for Entra ID
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000xNslk0AC/need-a-way-to-reliably-simulate-sandbox-alerts-testing-security-automation","lastmod":"2026-06-27T13:05:40.000Z","id":"0D5PJ00000xNslk0AC"} -->
## Need a way to reliably simulate sandbox alerts - testing security automation

- Source: https://community.zscaler.com/s/question/0D5PJ00000xNslk0AC/need-a-way-to-reliably-simulate-sandbox-alerts-testing-security-automation
- Type: Q&A
- Last activity: 2026-06-27T13:05:40.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Cloud Sandbox

hukel

(Customer) asked a question.

June 27, 2026 at 1:05 PM

Need a way to reliably simulate sandbox alerts - testing security automation

Because sandbox alerts are so complicated (different logs for sandbox alert and the web traffic that initiated the scan), we need to test our alerting and automation extensively.

However, we find it very hard to simulate sandbox detonations with alert scores.

Is there any way we can get a feature that will let customers upload a JSON file that contains the sandbox results, and have the sandbox engine just pass that through an alert?

Or, are there other ways to reliably trigger sandboxing alerts?

ZIA - Cloud Sandbox

180 views

Log In to Answer

Associated Tags

automation

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

1/21/2020

at

01:31 PM

Does Zscaler Scan Email Traffic?

ZIA - Cloud Sandbox

Omar

1,220

1220 Views

1 Like

3 Comments

1/2/2020

at

12:25 PM

Prevent Malicious VPN Connection

ZIA - Cloud Sandbox

Omar

1,192

1192 Views

0 Likes

8 Comments

10/2/2017

at

04:47 AM

Alerting positive 'allow and scan'

ZIA - Cloud Sandbox

Lmay

412

412 Views

1 Like

2 Comments

9/11/2017

at

09:37 PM

How do I determine which of the files that were submitted to Cloud Sandbox were found to be malicious?

ZIA - Cloud Sandbox

awl

524

524 Views

0 Likes

1 Comment

9/11/2017

at

09:35 PM

What is the recommended “best practice? Cloud Sandbox rule policy?

ZIA - Cloud Sandbox

awl

583

583 Views

0 Likes

4 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Need a way to reliably simulate sandbox alerts - testing security automation
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000y9tR40AI/zia-disaster-recovery","lastmod":"2026-07-09T17:47:09.000Z","id":"0D5PJ00000y9tR40AI"} -->
## ZIA Disaster Recovery

- Source: https://community.zscaler.com/s/question/0D5PJ00000y9tR40AI/zia-disaster-recovery
- Type: Q&A
- Last activity: 2026-07-09T17:47:09.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

Raj909

(Customer) asked a question.

July 9, 2026 at 5:47 PM

ZIA Disaster Recovery

We have ZIA DR configured which has been tested and works as expected. However, that is a manual process which needs to be invoked via DNS TXT file, in the event of a major cloud outage. We are looking at the new APP FAILOVER SETTINGS option within the Client Connector Disaster Recovery section - Fallback to ZIA DR option.

This essentially provides automatic failover on a more granular user-level if the cloud is not reachable. Was curious if anyone has this setup and tested. Curious if you faced any issues or have any insight with this setting.

https://help.zscaler.com/zscaler-client-connector/configuring-fail-open-settings-zscaler-client-connector

Thanks

ZIA - Forwarding

169 views

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/15/2023

at

10:07 PM

Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

ZIA - Forwarding

rajk5

3 Views

0 Likes

6 Comments

2/14/2023

at

04:13 PM

Forwarding Port 8443 through GRE Tunnel

ZIA - Forwarding

Omar

9 Views

0 Likes

2 Comments

10/23/2022

at

02:29 PM

PZEN localized content

ZIA - Forwarding

mohammad.rummaneh

3 Views

0 Likes

2 Comments

2/18/2022

at

08:24 AM

Premium DC in China

ZIA - Forwarding

Ezzzzh

6 Views

0 Likes

6 Comments

2/15/2021

at

11:58 PM

Disney Circle + Zscaler blocking internet access

ZIA - Forwarding

JamesK

492

492 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

ZIA Disaster Recovery
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000zQDQC0A4/device-control-priorities","lastmod":"2026-07-31T19:34:30.000Z","id":"0D5PJ00000zQDQC0A4"} -->
## Device Control - Priorities

- Source: https://community.zscaler.com/s/question/0D5PJ00000zQDQC0A4/device-control-priorities
- Type: Q&A
- Last activity: 2026-07-31T19:34:30.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - DLP

reklukas

(Partner) asked a question.

Yesterday at 8:49 AM

Device Control - Priorities

This is the situation - Device Control.

Scenario

(Removable Storage) When a file is copied to an external device - monitor (allow). Of course, with a notification to the user.

(Application) When a file is copied using WinSCP, block.

And now

When a file is transferred to another server (SFTP) using WinSCP, everything is fine, the transfer is blocked.

But... If a file is transferred using WinSCP to an external device, the file is ultimately transferred to that resource.

From our perspective, this is unacceptable. Is there any way to block such a transfer?

ZIA - DLP

24 views

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

2/9/2023

at

05:32 PM

DLP notification via Zscaler Client Connector

ZIA - DLP

thacarvalho123

7 Views

2 Likes

2 Comments

11/20/2022

at

09:26 PM

External DLP Engine

ZIA - DLP

H.zyD.zy

4 Views

0 Likes

3 Comments

11/9/2022

at

04:51 PM

Zscaler Preventing Image Upload and Download on iPhone

ZIA - DLP

MacDanorld1

2 Views

0 Likes

3 Comments

6/20/2022

at

07:53 AM

Error during Microsoft Information Protection(MIP) Integration

ZIA - DLP

ozanogur

3 Views

0 Likes

4 Comments

1/31/2021

at

08:35 PM

Zscaler ZIA and ZPA with NetSkope CASB and DLP services

ZIA - DLP

jonathan.holt

1,343

1343 Views

1 Like

6 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Device Control - Priorities
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000zaSVz0AM/windows-endpoint-occasionally-matched-to-ios-posturebased-firewall-policy","lastmod":"2026-07-31T11:37:28.000Z","id":"0D5PJ00000zaSVz0AM"} -->
## Windows Endpoint Occasionally Matched to iOS Posture-Based Firewall Policy

- Source: https://community.zscaler.com/s/question/0D5PJ00000zaSVz0AM/windows-endpoint-occasionally-matched-to-ios-posturebased-firewall-policy
- Type: Q&A
- Last activity: 2026-07-31T11:37:28.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Cloud Firewall

funakura.naoki

(Partner) asked a question.

19h ago

Windows Endpoint Occasionally Matched to iOS Posture-Based Firewall Policy

Has anyone seen a Windows endpoint being matched to an iOS posture-based firewall policy?

Environment:

- Windows PC

- ZCC

- Static IP Location

We have a firewall policy that should only apply to iOS(ZCC) devices with a Low Trust posture.

Very rarely, traffic from a Windows endpoint is blocked by that policy. Firewall Insights shows:

- OS Type : Windows

- Matched Rule : iOS(ZCC) only firewall policy

At the same time, ZCC logs show successful posture evaluation and a compliant state.

The issue disappears immediately after either:

- Restarting the browser, or

- Reloading the browser, or

- Running "Repair App" in ZCC.

Has anyone experienced something similar or identified a root cause?

I'm particularly interested in posture evaluation timing, policy matching issues, or session/token caching behavior.

Thanks!

ZIA - Cloud Firewall

13 views

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/29/2018

at

01:07 PM

Ip address as criteria in url policy

ZIA - Cloud Firewall

ram75

1,195

1195 Views

0 Likes

1 Comment

10/8/2020

at

08:41 AM

Block All access & Allow certain user or group (ZIA)

ZIA - Cloud Firewall

Sec_def_Def_sec

1,844

1844 Views

1 Like

2 Comments

12/19/2022

at

04:14 PM

URL filtering policy vs Cloud App policy control

ZIA - Cloud Firewall

Ahmed

4 Views

0 Likes

2 Comments

12/9/2022

at

09:40 PM

Apple News RSS Feed

ZIA - Cloud Firewall

Trace Woodbury-RidgeIT

2 Views

0 Likes

1 Comment

9/20/2022

at

03:20 PM

How does Zscaler Internet Access itself route the traffic to the internet, using what outgoing/next hop GW

ZIA - Cloud Firewall

tamerz

6 Views

0 Likes

5 Comments

See More >>

Zenith Community

An open, collaborative knowledge base for customers, users, and partners

Community

Tech Thoughts

Support

Support plans

Best practices

Service Level Agreement

Zscaler

Zscaler.com

Zenith Live

Zscaler Zero Trust

CXO REvolutionaries

CXO Home

Insights

CXO Knowledge Base

Sign up for our Community Newsletter

Click below to stay up to date on all things community activities

Subscribe

Top

Privacy

Terms of service

About

FAQ

Copyright 2008-2026 Zscaler

Windows Endpoint Occasionally Matched to iOS Posture-Based Firewall Policy
<!-- /ZS-POST -->
