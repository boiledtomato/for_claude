# Zscaler Zenith Community — ZCC — Zscaler Client Connector (part 4)

Source: https://community.zscaler.com
Generated: 2026-08-01 20:41 UTC
Posts in this file: 51

> これはユーザー投稿のコミュニティフォーラムの内容であり、Zscaler の公式ドキュメントではない。

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ZYTWp0AP/error-3049-on-chromeos-zcc","lastmod":"2025-12-11T13:42:06.000Z","id":"0D5PJ00000ZYTWp0AP"} -->
## error 3049 on ChromeOS ZCC

- Source: https://community.zscaler.com/s/question/0D5PJ00000ZYTWp0AP/error-3049-on-chromeos-zcc
- Type: Q&A
- Last activity: 2025-12-11T13:42:06.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

Pspearsjr

(Customer) asked a question.

June 16, 2025 at 11:10 AM

error 3049 on ChromeOS ZCC

Anyone have any additional information on what this error is and how to resolve it on ChromeOS? Somehow it auto resolved but not sure why. We tried uninstalling/reinstalling, restarting but that didn't help.

Failed to Register ZPA: ZPN signing failed (3049)

ZCC 3.10.0.47

Client Connector

6 answers

599 views

Pspearsjr

(Customer)

a year ago

I did find the list of error codes but unsure what would cause this temporarily and then it resolved on it's own.

https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors

1 like

Jainil_G

(Employee)

a year ago

Hello @Paul Spears​ This error occurs when the device attempts to register for ZPA, and during the certificate signing process, the signing fails. There is a high possibility of a network connection failure occurring intermittently. You can find more details in the ZCC logs (ZSATray_**.log).

BR

Jainil_G

If a

post

solves your

question

please use the '

Select as Best

option

pspears

(Customer)

a year ago

I did find the list of error codes but unsure what would cause this temporarily and then it resolved on it's own.

https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors

Corrales9602

(Employee)

8 months ago

Hello @Paul Spears​

Thank you for reaching out through our Zscaler Community Services.

Regarding your issue, this can be due to multiple things however, based on the documentation this is most-likely related to:

But, regardless of what is shown here, for more insight on why and how this happens we must need ZCC logs from the affected machine so we can find the RCA for this behavior.

I hope this helps, in case any further questions do not hesitate to reach us back!

avshch

(Customer)

7 months ago

@Paul Spears​ Are you running ZCC natively on ChromeOS?

pspears

(Customer)

7 months ago

This error was from the Android app.  I think we powerwashed the device and reinstalled the app and everything was fine after that.

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

error 3049 on ChromeOS ZCC
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ZlZ8P0AV/how-to-reliably-distinguish-between-client-connector-and-network-adapter","lastmod":"2025-07-25T08:25:20.000Z","id":"0D5PJ00000ZlZ8P0AV"} -->
## How to reliably distinguish between Client Connector and Network Adapter?

- Source: https://community.zscaler.com/s/question/0D5PJ00000ZlZ8P0AV/how-to-reliably-distinguish-between-client-connector-and-network-adapter
- Type: Q&A
- Last activity: 2025-07-25T08:25:20.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

jiri.pesik

(Customer) asked a question.

June 19, 2025 at 12:17 PM

How to reliably distinguish between Client Connector and Network Adapter?

Hi all,

I'm hoping someone in the community can help with a software detection challenge.

Our goal is to accurately detect the installed version of Zscaler Client Connector to identify if an upgrade is needed due to a reported security vulnerability.

However, we're finding it difficult to distinguish between the "Zscaler Client Connector" and the "Zscaler Network Adapter" by looking at the Windows registry. In the registry, it appears both components share the same

DisplayName

, which is simply "Zscaler".

This makes it challenging to reliably target the Client Connector for version checking without confusing it with the Network Adapter.

Does anyone know of a reliable method to differentiate between the two in the registry? For example:

Is there a different version numbering convention for the Client Connector versus the Network Adapter?

Are there other unique registry keys, values, or properties that can be used to definitively identify the Zscaler Client Connector and its specific version?

Any advice or insights would be greatly appreciated.

Thanks!

Client Connector

5 answers

604 views

Top Rated Answers

Abu Bakar

(Partner)

a year ago

Hi @Jiří Pešík​ ,

Please go through this and let me whether this help your case

Zscaler Client Connector (ZCC) Registry Keys:

Main ZCC Installation: You can find the main ZCC installation information under:

HKEY_LOCAL_MACHINE\SOFTWARE\Zscaler Inc.\Zscaler

This key contains details about the Zscaler Client Connector installation, including the version number (DisplayVersion) and publisher (Publisher).

Uninstall Information: The standard Windows uninstall registry path also contains information about the ZCC:

HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall

Look for an entry with DisplayName = Zscaler to confirm the ZCC installation.

Client Connector Portal Settings: You may also find settings related to the Zscaler Client Connector portal under:

HKEY_CURRENT_USER\Software\Zscaler\App

Zscaler Network Adapter Registry Keys:

Adapter Configuration: The Zscaler network adapter's settings are typically found under:

HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\Class\{4D36E972-E325-11CE-BFC1-08002BE10318}

Search within this key for an adapter named "Zscaler", "Zscaler-Network-Adapter", or similar.

You may see registry values like *RscIPv4 and *RscIPv6 or WdiRscIPv4 and WdiRscIPv6, which are related to Zscaler's Resource Reservation Protocol (RSC) for IPv4 and IPv6 traffic.

Adapter Binding: Check for the Zscaler network adapter binding information using PowerShell:

Get-NetAdapterBinding -AllBindings -ComponentID ZS_ZAPPRD

This command will list bindings related to the Zscaler adapter, identified by the component ID ZS_ZAPPRD

Selected as Best

All Answers

manuel

(Customer)

a year ago

Hello Jiri,

you could leverage "winget" to exactly identify the version of the installed ZCC:

Easier to show and easier to grep and parse for further processing :-)

BR

Manuel

1 like

jiri.pesik

(Customer)

a year ago

Hello Manuel,

thank you very much for your answer. I have not experience with `winget` but it looks really useful. And do I understand it correctly that it does not how Network Adapter?

Best regards

JIří

1 like

manuel

(Customer)

a year ago

Yes, winget explicitely shows/manages applications. The ntework adapter is no software which can be managed by winget and therefore is not shown.

BR

Manuel

Abu Bakar

(Partner)

a year ago

Hi @Jiří Pešík​ ,

Please go through this and let me whether this help your case

Zscaler Client Connector (ZCC) Registry Keys:

Main ZCC Installation: You can find the main ZCC installation information under:

HKEY_LOCAL_MACHINE\SOFTWARE\Zscaler Inc.\Zscaler

This key contains details about the Zscaler Client Connector installation, including the version number (DisplayVersion) and publisher (Publisher).

Uninstall Information: The standard Windows uninstall registry path also contains information about the ZCC:

HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall

Look for an entry with DisplayName = Zscaler to confirm the ZCC installation.

Client Connector Portal Settings: You may also find settings related to the Zscaler Client Connector portal under:

HKEY_CURRENT_USER\Software\Zscaler\App

Zscaler Network Adapter Registry Keys:

Adapter Configuration: The Zscaler network adapter's settings are typically found under:

HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\Class\{4D36E972-E325-11CE-BFC1-08002BE10318}

Search within this key for an adapter named "Zscaler", "Zscaler-Network-Adapter", or similar.

You may see registry values like *RscIPv4 and *RscIPv6 or WdiRscIPv4 and WdiRscIPv6, which are related to Zscaler's Resource Reservation Protocol (RSC) for IPv4 and IPv6 traffic.

Adapter Binding: Check for the Zscaler network adapter binding information using PowerShell:

Get-NetAdapterBinding -AllBindings -ComponentID ZS_ZAPPRD

This command will list bindings related to the Zscaler adapter, identified by the component ID ZS_ZAPPRD

Selected as Best

jiri.pesik

(Customer)

a year ago

Hi @Abubakar Khan​ , thank you very much for the detailed response!

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

How to reliably distinguish between Client Connector and Network Adapter?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000Zr0JC0AZ/how-does-1270019000ztestqusernamecompanycom-work","lastmod":"2025-07-09T14:43:36.000Z","id":"0D5PJ00000Zr0JC0AZ"} -->
## How does 127.0.0.1:9000?ztest?q=username@company.com work?

- Source: https://community.zscaler.com/s/question/0D5PJ00000Zr0JC0AZ/how-does-1270019000ztestqusernamecompanycom-work
- Type: Q&A
- Last activity: 2025-07-09T14:43:36.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

NetSecNick

(Customer) asked a question.

June 20, 2025 at 9:27 PM

How does 127.0.0.1:9000?ztest?q=username@company.com work?

Hello, like many others I have a slow internet connection issue when ZIA is enabled that I am trying to troubleshoot. I am currently working my way through all the community posts and I came across this tool that I can't find any information on. Can anyone tell me how this tool works?

I see that its pointing to my local host. So, would it only test connection from my machine or can it somehow gather information on other user's connections? One thing I am wondering is if I can use this tool in our HQ to test user's connections anywhere in the world. I know it might sound like ZDX is a better solution for us but our company has determined that there is not enough value in ZDX for us to adopt it. So I'm looking to understand how this tool works.

127.0.0.1:9000?ztest?q=username@company.com

Thanks in advance!

Client Connector

3 answers

966 views

NetSecNick

likes this.

Top Rated Answers

Osho_Dwivedi

(Employee)

a year ago

This is a connection Quality test which is now hosted at

speedtest.zscaler.com

, this can only gather information about the client you're running this from.

Selected as Best

2 likes

All Answers

sardinhaZ

(Customer)

a year ago

I would like to know this as well

manuel

(Customer)

a year ago

Hello NetSecNick,

127.0.0.1  is the loopback interface on your local machine, You cannot access this address from remote by design. Zscaler Client Connector binds to that interface and opens TCP port 9000. That means the speedtest is solely useable from your local machine and thus cannot be used remotely. IMHO the speedtest in ZCC is not an officially sanctioned  tool and maybe removed/disabled/modified by Zscaler anytime.

One COULD leverage scripts via MDM solution to trigger that, but I am not sure if that would work at all. I assume the "

username@company.com

" part only is used to verify the user who is logged on to ZCC.

BR

Manuel

2 likes

Osho_Dwivedi

(Employee)

a year ago

This is a connection Quality test which is now hosted at

speedtest.zscaler.com

, this can only gather information about the client you're running this from.

Selected as Best

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

2,880

2880 Views

0 Likes

11

11 Comments

7/3/2020

at

11:55 AM

ZAPP intune deployment

Client Connector

Mk001

1,374

1374 Views

0 Likes

4 Comments

1/28/2021

at

03:42 PM

Compare ezAgent and ZCC - when to use which?

Client Connector

hukel

698

698 Views

0 Likes

2 Comments

8/18/2020

at

12:15 PM

MacOS Zscaler App Log Location

Client Connector

brad

3,562

3562 Views

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

How does 127.0.0.1:9000?ztest?q=username@company.com work?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000arCxq0AE/policy-update-file-in-zcc","lastmod":"2025-07-11T23:01:45.000Z","id":"0D5PJ00000arCxq0AE"} -->
## policy update file in ZCC

- Source: https://community.zscaler.com/s/question/0D5PJ00000arCxq0AE/policy-update-file-in-zcc
- Type: Q&A
- Last activity: 2025-07-11T23:01:45.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

Athees

(Partner) asked a question.

July 9, 2025 at 1:24 PM

policy update file in ZCC

I need find a file in ZCC with respect zscaler (zpa) policy push for both MAC and Windows

Client Connector

4 answers

489 views

Eric N.

(Employee)

a year ago

Hi Athees. Can you clarify what you are trying to find? Which file? Are you seeking documentation regarding how Private Application related policy is updated in Client Connector?

Ramesh Mani

(Partner)

a year ago

Hi Athees,

can you provide the detailed requirements?

Are you referring configuration files ?

Regards

Ramesh M

Athees

(Partner)

a year ago

Let me explain use case ,

I have added new application segment in ZPA. in ZCC, I have updated policy.

want make sure latest zpa policy has been update or not.

is there a way check on endpoint to see updated zpa policy ??

if yes, how to check it.

I heard, it will on zscaler installer directory , help me with file location for both mac and windows endpoint

Osho_Dwivedi

(Employee)

a year ago

You'd find as to what applications are pushed to the Client Connector for ZPA, you'd find this information in ZSATunnel Logs.

C:\ProgramData\Zscaler\Logs

----  Windows

/Library/Application Support/Zscaler/Logs              ----  MAC

Keyword :

zpa_client_app

If this response solves your question please use the '

Select as Best

' option.﻿

Log In to Answer

Associated Tags

best-practice

azure

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

3,572

3572 Views

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

policy update file in ZCC
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000bG1Xt0AK/web-signin-for-windows-being-blocked-by-zcc-in-strict-enforcement-mode","lastmod":"2025-10-10T19:35:31.000Z","id":"0D5PJ00000bG1Xt0AK"} -->
## "Web Sign-In for Windows" being blocked by ZCC in strict enforcement mode

- Source: https://community.zscaler.com/s/question/0D5PJ00000bG1Xt0AK/web-signin-for-windows-being-blocked-by-zcc-in-strict-enforcement-mode
- Type: Q&A
- Last activity: 2025-10-10T19:35:31.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Logging

crw1987

(Customer) asked a question.

Edited July 16, 2025 at 12:37 PM

"Web Sign-In for Windows" being blocked by ZCC in strict enforcement mode

Hello - my org is trying to utilize "Web Sign-In for Windows"

Use Web Sign-In To Enable Passwordless Sign-In In Windows | Microsoft Learn

, which is a passwordless sign in method, and a different kind of credential provider than Windows Hello.

The ZCC in strictenforcement=1 is blocking use of this and it will not load. I've ran a web sign-in with ZCC disabled, and did a wireshark capture, put down every URL I could find in a PAC file bypass, but it still won't load with ZCC installed.

I am hoping some other orgs out there, or Zscaler support have managed to get Web Sign-In working, as it is a Windows 11 authentication method.

ZIA - Logging

13 answers

1.02K views

UserZ

likes this.

Abu Bakar

(Partner)

a year ago

Greeting @Carter Wilson​ ,

Have you Enable Browser-Based setting under ZCC portal, please find below snap for your reference.

crw1987

(Customer)

a year ago

Thanks, this is about signing into Windows itself.

We do have this option configured in the ZCC, but it doesn't really apply to this scenario.

Ramesh Mani

(Partner)

a year ago

Also add the IDP URLs in the whitelisted list using App profiles bypasses, PAC file anywhere.

crw1987

(Customer)

a year ago

Thanks, we already have the URLs we can find in PAC file. The issue is that they don't seem to be documented by Microsoft, and Zscaler only has documentation for Windows Hello, which is something else.

UserZ

(Customer)

a year ago

We are seeing the same issue--Web Sign-in fails with strict enforcement on, works fine with Zscaler temporarily disabled.

As crw1987 mentioned, it seems there's more required here than the URL in the screenshot error above.  Has anyone had success with this?

crw1987

(Customer)

a year ago

Also, wanted to add that these are URLs we have attempted to add in a PAC file so far:

*.

azureedge.net

*.

comodoca.com

*.

events.data.microsoft.com

*.

manage.microsoft.com

*.

msauthimages.net

*.

msftauthimages.net

*.

officeapps.live.com

*.

pushnp.svc.ms

*.

trafficmanager.net

https://*.microsoft.com

(general fallback if wildcard used in PAC/SSL inspection bypass)

aadcdn.msauth.net

aadcdn.msftauth.net

aadcdn.msftauthimages.net

browser.events.data.microsoft.com

checkin.dm.microsoft.com

client.wns.windows.com

eccusoftwaredist.blob.core.windows.net

ecs.office.com

edge.microsoft.com

edge-consumer-static.azureedge.net

endpoints.office.com

enterpriseregistration.windows.net

fd.api.iris.microsoft.com

g.live.com

go.microsoft.com

graph.microsoft.com

http://crl.microsoft.com

http://www.microsoft.com/pkiops/crl

https://aadcdn.msauth.net

https://aadcdn.msftauth.net

https://autologon.microsoftazuread-sso.com

(for Seamless SSO)

https://dc.services.visualstudio.com

(telemetry, but can be involved in login flow)

https://device.login.microsoftonline.com

https://edge.microsoft.com

(Chromium/Edge WebView)

https://graph.windows.net

https://login.live.com

https://login.microsoft.com

https://login.microsoftonline.com

https://ocsp.msocsp.com

(OCSP certificate validation)

https://www.microsoft.com

https://www.msftconnecttest.com

(used by NCSI / connectivity test)

login.live.com

login.microsoftonline.com

manage.microsoft.com

mp.microsoft.com

ocsp.digicert.com

ocsp.msocsp.com

officeclient.microsoft.com

r.manage.microsoft.com

static.edge.microsoftapp.net

substrate.office.com

unitedstates.smartscreen.microsoft.com

urs.microsoft.com

winatp-gw-eus.microsoft.com

UserZ

(Customer)

a year ago

I opened a case with Zscaler support yesterday and they had me add the set of exclusions below to the PAC file associated with app profile in question.  After updating policy on several machines and testing, web sign-in seems to be working for us.

if (shExpMatch(host, "

login.live.com

") ||

shExpMatch(host, "

www.msftconnecttest.com

") ||

shExpMatch(host, "

www.msftncsi.com

") ||

shExpMatch(host, "

login.microsoftonline.com

") ||

shExpMatch(host, "

aadcdn.msftauth.net

") ||

shExpMatch(host, "

aadcdn.msauth.net

") ||

shExpMatch(host, "

aadcdn.msftauthimages.net

") ||

shExpMatch(host, "

aadcdn.msauthimages.net

") ||

shExpMatch(host, "

licensing.mp.microsoft.com

") ||

shExpMatch(host, "

endpoints.office.com

"))

return "DIRECT";

1 like

crw1987

(Customer)

a year ago

Thanks, appreciate the reply.

I tested this but still not working, getting a blank white login box, sometimes it gets to send a notification to your phone, but doesn't go further, other times it displays the error cloud not load login.microsoftonline.....

Any chance you have other URLs in your pac file, possibly intune/autopilot urls?

UserZ

(Customer)

a year ago

We do have other MS IPs and URLs in various places (eg the VPN bypass, ZIA 'Enable Microsoft-Recommended One Click Office 365 Configuration', etc.  Have you opened a case with Zscaler yet?  They may be able to suggest what might be missing.

crw1987

(Customer)

a year ago

We have the One Click in place, as well all the URLs and endpoints required for Intune, but these are bypassed within ZIA policy rather than in PAC file on the local device.

My ISP (Bell Canada) actually manages our Zscaler, I've had an open case with them, and they have had an open case with Zscaler for weeks now, and we're not making any progress.

More answers

10 of 13

Log In to Answer

Associated Tags

azure-ad

windows

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

671

671 Views

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

"Web Sign-In for Windows" being blocked by ZCC in strict enforcement mode
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000byt4u0AA/vpn-gateway-bypasses","lastmod":"2025-08-14T18:53:50.000Z","id":"0D5PJ00000byt4u0AA"} -->
## VPN Gateway Bypasses

- Source: https://community.zscaler.com/s/question/0D5PJ00000byt4u0AA/vpn-gateway-bypasses
- Type: Q&A
- Last activity: 2025-08-14T18:53:50.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

pats22

(Customer) asked a question.

July 28, 2025 at 6:27 PM

VPN Gateway Bypasses

Hi All,

If a user is on tunnel 2.0 configuration and if we had given VPN Gateway bypass to an application ..is it applicable only to Port 443/80 or all the other ports as well? or if some other ports coming into picture we need to add it in destination exclusion for 2.0 along with ports?

ZIA - Forwarding

5 answers

1.61K views

pats22

likes this.

Osho_Dwivedi

(Employee)

a year ago

VPN Gateway Bypass means that traffic destined for the specified application will completely bypass the ZCC. This applies regardless of the port being used by the application.

VPN Gateway bypasses allow you to create routes and filters for direct traffic. You can use IPs, subnets, or FQDNs.

This type of bypass has the highest priority over all other bypass types. These bypasses can also affect IP-based applications by directly sending traffic.

When you create a VPN gateway bypass, the system sets a filter to ignore VPN traffic without Zscaler Client Connector needing to process the bypass.

If you want to only bypass (send via device physical adapter/Direct) certain traffic to the application while using T2.0 you then can use the Tunnel2.0 Exclusions option otherwise  ZCC Tunnel2.0 captures everything.

If this response

solves your

question

please use the

Select as Best

option

.﻿

1 like

JB

(Partner)

a year ago

when you apply a vpn gateway bypass policy (in the zscaler client connector portal), it instructs zcc to completely bypass zscaler for the specified destination across all ports.

vpn gateway bypass = ip/fqdn based global bypass (applies to all ports and protocols)

destination exclusion in tunnel 2.0 = fqdn/ip + specific ports/protocols  - needs manual addition for non-default ports

1 like

Fileno

(Partner)

a year ago

When specified in ZCCP inside an App Profile a domain and/or IP(s) at VPN Gateway Bypass configuration, then all traffic for thoses destination(s) defined are going to be bypass by ZCC.

pats22

(Customer)

a year ago

It depends. There seems to be more nuisance to it.

What came to my observation is that , if in Forwarding Profile under Tunnel 2.0 , if we enable "Redirect Web Traffic to Zscaler Client Connector Listening Proxy ", then we have observed that only SSL VPNs which work on ports 443/80 is getting bypassed if the Domain/IP part of VPN Gateway bypass. But all other ports won't get bypassed.

Like for e.g.  If a https destination utilizing non standard port >>

abc.com

:8443 ..even if

abc.com

is in Gateway bypass, the hits for 8443 we can see in Web and Firewall insights which suggests its not getting completely bypassed

We have kept "Use Z-Tunnel 2.0 for Proxied Web Traffic" unticked as it was mandatory for us to improve Web based performance.

1 like

Fileno

(Partner)

a year ago

What I mentioned it was related to standard Tunnel 2.0 configurations.

There at least 3 possible configurations for Tunnel 2.0 and it depends on results to be achieved and requirements.  We've observed better performance with "Use Z-Tunnel 2.0 for Proxied Web Traffic" enabled as proxy traffic uses Tunnel 1.0 and non-proxy Tunnel 2.0.

Suggest to make some tests with both and compare, nevertheless the best solution is the one that works best for your Business.

Log In to Answer

Associated Tags

vpn

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

VPN Gateway Bypasses
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000cWNF30AO/zpa-diagnostic-logs-se-connection-closed-by-client-connector","lastmod":"2025-08-11T12:56:34.000Z","id":"0D5PJ00000cWNF30AO"} -->
## ZPA Diagnostic Logs: SE: Connection closed by Client Connector

- Source: https://community.zscaler.com/s/question/0D5PJ00000cWNF30AO/zpa-diagnostic-logs-se-connection-closed-by-client-connector
- Type: Q&A
- Last activity: 2025-08-11T12:56:34.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZPA - Forwarding

SASE007

(Customer) asked a question.

August 6, 2025 at 2:54 PM

ZPA Diagnostic Logs: SE: Connection closed by Client Connector

We are attempting to have our ZPA Private Service Edges available 'publicly' and 'locally'.

ZCC is able to establish connectivity to the Private Service Edges, but traffic is then routed back to the Public Service Edges to broker a connection with the App Connectors.

When hovering over the log entries in the ZPA portal, the following message is displayed:

"The ZPA Private Service Edge forwarded the user's request to a ZPA Public Service Edge, since the App Connector was unable to reach a ZPA Private Service Edge. Click the Hop Connection ID to see the hop transaction details."

When expanding the log entry, the following status code is displayed:

SE: Connection closed by Client Connector

Internal Status Code:

BRK_MT_CLOSED_FROM_CLIENT

The transaction to the application on the network is successful though.

I suspect that the App Connectors are potentially not connecting to the Private Service Edge, although we see successful traffic on the firewall.

ZPA - Forwarding

1 answer

480 views

Osho_Dwivedi

(Employee)

a year ago

BRK_MT_CLOSED_FROM_CLIENT is a info message and mean that the connection from the App Connector to the ZPA Public Service Edge or ZPA Private Service Edge was terminated, resulting in the ZPA Public Service Edge or ZPA Private Service Edge terminating all application sessions for that App Connector.

You point about app connector not able to reach the PSE is correct, check your connectivity from the App Connector to the PSE and monitor the firewall if you've missed something there.

Log In to Answer

Associated Tags

No tags associated with this post!!

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

455

455 Views

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

344

344 Views

0 Likes

3 Comments

7/30/2025

at

08:47 AM

Difference between SIPA and ZPA

ZPA - Forwarding

Dhananjay_Bhakte

971

971 Views

0 Likes

8 Comments

4/23/2026

at

05:00 PM

App Segments populating in Edge Appliance

ZPA - Forwarding

BlueSocks

151

151 Views

0 Likes

1 Comment

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

ZPA Diagnostic Logs: SE: Connection closed by Client Connector
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000cbT4C0AU/zcc-and-network-connectors-on-macos","lastmod":"2025-08-14T09:02:31.000Z","id":"0D5PJ00000cbT4C0AU"} -->
## ZCC and Network Connectors on macOS

- Source: https://community.zscaler.com/s/question/0D5PJ00000cbT4C0AU/zcc-and-network-connectors-on-macos
- Type: Q&A
- Last activity: 2025-08-14T09:02:31.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

manuel

(Customer) asked a question.

August 7, 2025 at 4:03 PM

ZCC and Network Connectors on macOS

Hello all,

we deployed a Zscaler Network Connector for VPN/VoIP legacy apps. Everything is up&running and ZCC on Windows (!) shows an active Wireguard connection and we can access configured applications via wg-tunnel here.

On macOS it is a completely different thing: in the logs we see an IP address got successfully assigned/reserved, but starting Wireguard/establishing the wg-tunnel fails and ZCC gui just shows "nothing":

The ZCC debugs logs are also not very helpful:

2025-08-07 15:13:19.490075(+0200)[1162:11797] DBG ZPN:0: Response tag: 0

2025-08-07 15:13:19.490147(+0200)[1162:11797] DBG ZPN:0: Response length: 273

2025-08-07 15:13:19.490221(+0200)[1162:11797] INF ZPN:0: Control Message Response Data: {"zpn_np_config":{"gateways":[{"zpn_np_gateway_config":{"gid":XXX,"public_key":"XXX","key_expiry_s":1755812675,"listen_ip":"XXX","listen_port":51820,"mtu":1420,"client_ip":"192.168.XXX.XXX"}}],"status_code":0}}

2025-08-07 15:13:19.490370(+0200)[1162:11797] INF Got zpn_np_config response.

2025-08-07 15:13:19.490458(+0200)[1162:11797] INF [ZSANP]: In gatewayListRcvdFromBroker

2025-08-07 15:13:19.490572(+0200)[1162:11796] INF [ZSANP]: In handleFetchGatewayListRcvd.

2025-08-07 15:13:19.490682(+0200)[1162:11796] INF [ZSANP]: Starting the network presence tunnel

2025-08-07 15:13:19.490754(+0200)[1162:11796] ERR [ZSAWGM]::In start()

2025-08-07 15:13:19.490821(+0200)[1162:11796] INF isConfigValid isPrivateKeySet: 1, serverList: 1, includeSubnetMap: 3

2025-08-07 15:13:19.491032(+0200)[1162:450021] INF ZSAWGM::Start: trying server: XXX:51820

2025-08-07 15:13:19.491192(+0200)[1162:450021] INF ZSAWGM: Address: 192.168.XXX.XXX

2025-08-07 15:13:19.491376(+0200)[1162:450021] INF ZSAWGM: PublicKey: XXX

2025-08-07 15:13:19.491504(+0200)[1162:450021] INF ZSAWGM: AllowedIPs: XXX/24, XXX/24, XXX/24

2025-08-07 15:13:19.491599(+0200)[1162:450021] INF ZSAWGM: Endpoint = XXX:51820

2025-08-07 15:13:19.491671(+0200)[1162:450021] INF ZSAWGM: start WG...

2025-08-07 15:13:19.491803(+0200)[1162:450021] DBG XPC connection initiated, returning the proxy object

2025-08-07 15:13:19.526842(+0200)[1162:164853] INF ZSAWGM: start WG failed

2025-08-07 15:13:19.526909(+0200)[1162:450021] INF ZSAWGM: start wireguard failed

2025-08-07 15:13:19.526961(+0200)[1162:450021] ERR ZSAWGM::Start: Failed to start wireguard interface

2025-08-07 15:13:19.527004(+0200)[1162:450021] INF [ZSANP]: onAllGatewaysFailed called

2025-08-07 15:13:19.527051(+0200)[1162:11796] INF [ZSANP]: In handleAllGatewaysFailed

2025-08-07 15:13:19.527101(+0200)[1162:11796] INF [ZSANP]:handleFetchGatewayListRetry, nextGatewayFetchWaitInterval: 30 secs

We suspect some macOS policies we enforce are interrupting WG setup but although after double-checking all our settings we were unable to identify any blocking rule. And yes, IP address of VPN Service Edge has been added to "VPN Gateway Exemption" list.

We use latest ZCC (4.5.0.279) and latest macOS 15.6. Systems are managed by intune. Except Microsoft Defender there are no additional security apps installed. MacOS Firewall is enforced via profiles, particular Zscaler Services are allowed:

The only binary we miss in the list above is "ZscalerService" and we are unsure if this needs also to be explicitly exempted. Logs show no hint what exactly is failing or why the wireguard tunnel cannot be established - neither macOS console nor ZCC logs.

In ZPA VPN Legacy Portal we see the particular user and the assigned IP, but "Connected Device State" is just showing "down".

Anyone else run into similar issues? Ideas are very welcome :-)

Thanks & BR

Manuel

Client Connector

1 answer

582 views

Ramesh Mani

(Partner)

a year ago

Lets create a support case and work to identify , isolate and fix the issue.

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

3,573

3573 Views

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

ZCC and Network Connectors on macOS
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000cutRB0AY/zscaler-client-connector-over-gre","lastmod":"2025-08-14T14:04:07.000Z","id":"0D5PJ00000cutRB0AY"} -->
## Zscaler client connector over GRE

- Source: https://community.zscaler.com/s/question/0D5PJ00000cutRB0AY/zscaler-client-connector-over-gre
- Type: Q&A
- Last activity: 2025-08-14T14:04:07.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

RohitK

(Customer) asked a question.

August 13, 2025 at 12:24 PM

Zscaler client connector over GRE

Hi Everyone ,

I need some understanding on how data gets forwarded when ZCC is enabled in tunnel mode on a GRE branch site.

While using tunnel 2.0 and GRE at branch site , in the web insights log i see both Identity and location details being fetched by ZEN.

I am assuming user identity details are from ZCC and location is via static ip address of location.

While doing a traceroute towards internet i see the 1st hop as ZEN node ip , although the default route is towards the local gateway.

So the data traffic is flowing via ZCC or GRE in this case ?

ZIA - Forwarding

4 answers

448 views

mdg77

(Customer)

a year ago

In this case, the traffic goes through tunnel v2 of ZCC. You can perform a simple test: connect the GRE tunnel to a data center (CPD) in one country, and configure ZCC to use another. Then go to

ip.zscaler.com

and you’ll see that the traffic is going through ZCC.

RohitK

(Customer)

a year ago

Hi , I have done the testing and and from

ip.zscaler.com

information the traffic is going to GRE service edge.

That's make it clear that the traffic is going via GRE tunnel and not ZCC tunnel.

Another thing is the traceroute shows 1st hop as a ZEN node hop. Why is it that when the traffic is indeed going via GRE tunnel ( which was verified via

ip.zscaler.com

) it does not show next hop as local gateway ?

Ramesh Mani

(Partner)

a year ago

Hi Rohit,

Still there is a chance of go your ZCC traffic via GRE. Until you have any specific routes in place.

Your next hop for the GRE will be the other end of GRE not your local gateway.

Ramesh Mani

(Partner)

a year ago

Two possibilities

ZCC traffic may go via GRE

ZCC traffic NAT and breakout locally , all other traffic through GRE.

Look at your route, PBR  and NAT configuration on the edge device.

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

Zscaler client connector over GRE
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000dDEC80AO/where-do-i-get-the-client","lastmod":"2025-08-19T05:07:00.000Z","id":"0D5PJ00000dDEC80AO"} -->
## Where do I get the client?

- Source: https://community.zscaler.com/s/question/0D5PJ00000dDEC80AO/where-do-i-get-the-client
- Type: Q&A
- Last activity: 2025-08-19T05:07:00.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Training Portfolio

christian.call

(Partner) asked a question.

August 18, 2025 at 7:56 PM

Where do I get the client?

I signed up for Demo Center access, and the Guided Tour tells me I need to download the client from one of the placed circled in the screen shot below. But I have no links to those places -- either in the tour or in the email I got when I registered.

Can anyone tell me where to get the client? Thanks.

Training Portfolio

1 answer

1.3K views

Ramesh Mani

(Partner)

a year ago

Hi ,

From Zscaler Internet-Access administration portal or directly Zscaler Client connector portal from the Z-Identity portal (SDC)

Use the below links to navigate to Zscaler client connector portal -

https://help.zscaler.com/zscaler-client-connector/accessing-and-navigating-zscaler-client-connector-portal

Once logged in Go to Administration --> Client Connector App store _-> App store Select the OS and version to download

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

Where do I get the client?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000dGs6C0AS/cant-access-resources-on-an-old-domain-when-logged-in-to-zcc","lastmod":"2025-08-22T08:02:19.000Z","id":"0D5PJ00000dGs6C0AS"} -->
## Can't access resources on an old domain when logged in to ZCC

- Source: https://community.zscaler.com/s/question/0D5PJ00000dGs6C0AS/cant-access-resources-on-an-old-domain-when-logged-in-to-zcc
- Type: Q&A
- Last activity: 2025-08-22T08:02:19.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

Diode

(Customer) asked a question.

August 19, 2025 at 4:44 PM

Can't access resources on an old domain when logged in to ZCC

Hi I'm deploying ZIA on our remote site.

We have an old domain which some users still access form the new network. Access to old domain resources denied while ZCC is running (Like file share access) as soon as you log out of ZCC all are back to normal

Planning to de-com this old domain soon but for now we need access & I'm not keen on disabling ZIA for end user devices. Any idea what might be causing this pls

ZIA - Forwarding

2 answers

234 views

Maarten van den Bogaard

(Partner)

a year ago

Hi, this sounds like you may need to exempt traffic to the old-domain resources from being forwarded to Zscaler. You can do this in the ZCC app profile or in the hosted PAC file. Without more detailed information that would be my best guess.

Hope this helps. If this resolves your issue please mark the answer as best, thanks!

Diode

(Customer)

a year ago

Thanks I fixed it by adding the resources VPN Gateway Bypass. This old domain is going away so no issue. Cheers

Happy Friday

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

Can't access resources on an old domain when logged in to ZCC
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000eQSje0AG/vpn-for-legacy-solution-not-working","lastmod":"2026-02-07T17:21:46.000Z","id":"0D5PJ00000eQSje0AG"} -->
## VPN (for legacy solution) not working

- Source: https://community.zscaler.com/s/question/0D5PJ00000eQSje0AG/vpn-for-legacy-solution-not-working
- Type: Q&A
- Last activity: 2026-02-07T17:21:46.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

Giovanni

(Customer) asked a question.

September 9, 2025 at 9:43 AM

VPN (for legacy solution) not working

Hi, we are trying to evaluate with a POC the vpn for legacy apps. We have deployed the Ova for the network connector , configured all the requisites , on network connector all is fine connected to the service edge, on client connector side I see the vpn tunnel is up and connected but I can not reach any destination defined in the network segment. Honestly I don't know where to look at, I have opened meanwhile a ticket to the support but I would like to try to find what's the issue. Is there anyone that have experience with this feature?

thank you

Client Connector

4 answers

598 views

avshch

likes this.

dcreedy

(Employee)

a year ago

Hi Giovanni,

Do you see any information in the ZPA console logs?

Spewler

(Partner)

a year ago

VPN for legacy Apps has no diagnostic logs yet.    They dont show anywhere.   The traffic going to a network segment occurs prior to ZPA (bypasses zpa)

Giovanni

(Customer)

a year ago

in the end the issue was on our side, network team did not applied properly the required requisites (allow outgoing udp on 51820 port) , anyway I hope zscaler vpn will be  enhanced with logs, troubleshooting section and an enriched documentation.

1 like

dcreedy

(Employee)

a year ago

Thanks for this Giovanni, I'll pass the feedback regarding logging on to the ZPA team and let them know!

Log In to Answer

Associated Tags

privateaccess

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

2,896

2896 Views

0 Likes

11

11 Comments

7/3/2020

at

11:55 AM

ZAPP intune deployment

Client Connector

Mk001

1,386

1386 Views

0 Likes

4 Comments

1/28/2021

at

03:42 PM

Compare ezAgent and ZCC - when to use which?

Client Connector

hukel

706

706 Views

0 Likes

2 Comments

8/18/2020

at

12:15 PM

MacOS Zscaler App Log Location

Client Connector

brad

3,604

3604 Views

0 Likes

2 Comments

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

VPN (for legacy solution) not working
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000gLPbT0AW/url-bypass-in-zcc-20-over-ipsec","lastmod":"2025-12-25T19:37:05.000Z","id":"0D5PJ00000gLPbT0AW"} -->
## URL Bypass in ZCC 2.0 Over IPSEC

- Source: https://community.zscaler.com/s/question/0D5PJ00000gLPbT0AW/url-bypass-in-zcc-20-over-ipsec
- Type: Q&A
- Last activity: 2025-12-25T19:37:05.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

Dhananjay_Bhakte

(Partner) asked a question.

Edited October 15, 2025 at 2:15 PM

URL Bypass in ZCC 2.0 Over IPSEC

With ZCC over IPsec, we configure bypasses in the AAP profile or PAC file and also set up a local breakout on the router. Why do we need the AAP/PAC bypass if traffic is sent via IPsec? For this customer, certain URLs must be sent straight to the internet, so bypasses must be defined in both the AAP/PAC and on the router.

ZIA - Forwarding

5 answers

533 views

Dhananjay_Bhakte

(Partner)

9 months ago

Based on above scenario I am trying to understand traffic flow to Zscaler cloud when ZCC is ON in trusted network with IPsec tunnel.

I want to understand which Zscaler data center it connects to one for which IPsec tunnel is created or one which mentioned in PAC file? I am confusing here.

Daniele Màtyàs Tieghi

(Partner)

9 months ago

You can always use

ip.zscaler.com

to understand which Zero Trust Exchange are you connecting to.

While for bypassing an URL if your ZCC is behind an IPsec tunnel it depends if you have a default route sending all the traffic to the tunnel also without ZCC, because if you do then you will need some Policy Based Routing to create the exception for the URL also on your firewall/router, because bypassing it on ZCC is not enough.

Ramesh Mani

(Partner)

9 months ago

ZCC creates tunnel and forward to IPSec if your traffic forwarding is ZCC+IPSec.  So you would need bypass from endpoints using ZCC APP profile or PAC file and then on IPSec gateway you would add routes to bypass it from network.

Dhananjay_Bhakte

(Partner)

7 months ago

Hi Ramesh and Daniele,

I want to  understand ,

If IPsec is configured to forward traffic to the Mumbai Zscaler DC, but the PAC file is set to use the New Delhi Zscaler DC, could you please confirm which DC the user traffic will ultimately route to when using the ZCC over the IPsec method?

Ecruz lZ30030

(Employee)

7 months ago

Hello @Dhananjay Bhakte​ ,

The PAC File will take precedence here. The ZCC will check against the App Profile PAC File to determine which Service Edge (SME) it will connect to.

Since the PAC File explicitly points out to New Delhi ZCC will attempt to establish its tunnel (DTLS or TLS) to New Delhi DC IP Address Datacenter.

The ZCC will encapsulate the traffic, and the "outer" destination IP Address of the packet will be New Delhi. When the packet arrives to your local edge device (router or firewall) the IPSec Policy comes into play.

In case the IPSec Tunnel you have configured is set to route all traffic meaning 0.0.0.0/0 to Mumbai DC, your edge device will wrap the ZCC packets (originally destined to New Delhi in PAC File) inside your IPSec Tunnel and it will send it to Mumbai.

Once the packet arrives to Mumbai DC it will then route the ZCC Tunneled traffic within Zscaler internal infrastructure to New Delhi Datacenter where there will be the policies applied and then will be sent out to the destination websites/resources.

However,  this is something that can impact performance producing a little latency because the traffic will go physically to Mumbai Datacenter due to the IPSec Tunnel but then based on PAC File logic it will be  send to New Delhi.

The best suggestion I can give here is to make sure both the IPSec Tunnel and ZCC PAC File logic use the same destination DC for both to target the same location or "Subcloud" in case you use Subclouds in your environment, unless you require to target different Zscaler DCs for each tunnel (ZCC & IPSec).

Hope this helps.

Log In to Answer

Associated Tags

casb

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

URL Bypass in ZCC 2.0 Over IPSEC
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000hzcsQ0AQ/mac-upgrade-workflow-question","lastmod":"2025-10-28T20:13:19.000Z","id":"0D5PJ00000hzcsQ0AQ"} -->
## Mac Upgrade Workflow Question

- Source: https://community.zscaler.com/s/question/0D5PJ00000hzcsQ0AQ/mac-upgrade-workflow-question
- Type: Q&A
- Last activity: 2025-10-28T20:13:19.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

bmarks

(Customer) asked a question.

October 28, 2025 at 8:13 PM

Mac Upgrade Workflow Question

Good afternoon. Forgive me if this has been previously answered, but I couldn't find any info via the search. What is the best practice for pushing out Mac Zscaler Client Connector updates via an MDM tool like Jamf? Can a Jamf policy use the package installer to update the client connector app silently while a user has an active Zscaler connection? Or, should a user be notified in advance that an upgrade is about to happen in case the installation causes any brief losses in connectivity? Currently, we use a popup and let users defer, but this slows down the app upgrade rate, so if we can push out the new versions silently and without user interaction, that would improve the rate of user upgrades. Thanks.

Client Connector

239 views

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

2,875

2875 Views

0 Likes

11

11 Comments

7/3/2020

at

11:55 AM

ZAPP intune deployment

Client Connector

Mk001

1,366

1366 Views

0 Likes

4 Comments

1/28/2021

at

03:42 PM

Compare ezAgent and ZCC - when to use which?

Client Connector

hukel

696

696 Views

0 Likes

2 Comments

8/18/2020

at

12:15 PM

MacOS Zscaler App Log Location

Client Connector

brad

3,559

3559 Views

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

Mac Upgrade Workflow Question
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000iNj4q0AC/forbury-addin-in-excel-doesnt-load-while-connected-to-zcc","lastmod":"2025-11-17T23:43:23.000Z","id":"0D5PJ00000iNj4q0AC"} -->
## Forbury Add-in in Excel doesn't load while connected to ZCC

- Source: https://community.zscaler.com/s/question/0D5PJ00000iNj4q0AC/forbury-addin-in-excel-doesnt-load-while-connected-to-zcc
- Type: Q&A
- Last activity: 2025-11-17T23:43:23.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - SSL Inspection

RKV

(Partner) asked a question.

November 3, 2025 at 6:13 AM

Forbury Add-in in Excel doesn't load while connected to ZCC

Hi Team,

We have an issue with Forbury Add-in in Excel applications where it doesn't load while working from home Zscaler ZCC connected.

It does work for the same user when working from office connected through corp network ( PAC over GRE tunnel)

The Forbury Add-in integrates with Excel to perform complex calculations for commercial real estate analysis by sending data to a cloud engine and returning results to your workbook

Any idea what could be blocking here, we are guessing SSL inspection could work but unable to find the evidence for the same.

Really appreciate your feedback.

ZIA - SSL Inspection

1 answer

236 views

Corrales9602

(Employee)

8 months ago

Hello @Virupaksha Rajapur​ ,

Thank you for reaching us out via our Community.

From what you have explained, this issue is only happening while users are working from home, as soon as they move to the Office (GRE tunnel as forwarding method) the issue goes away.

This can be due to:

Different PAC file when users are connected to GRE or different policies configuration

SSL causing any impact

After checking the below FAQs documentation from Forbury, the state that the domains:

forbury.com

forburyproperty.com

Must be whitelist from ZS. This is documentation I am talking about:

https://www.forbury.com/support/faq/?utm_source=google&utm_medium=organic

The URLs have a dot ( . ) at the beginning, which means they are requesting us to bypass not only the domains but also the sub-domains.

In order to bypass such domains and sub-domains, you can follow the steps below:

Bypass the domains from SSL inspection, to confirm is the SSL decryption process can affect during this process somehow.

If SSL bypass is not solving the issue, then you can add the bypass in the Application Profile, under VPN Gateway bypass. This bypass will only be effective if the domains

forbury.com

, .

forburyproperty.com

resolved to a single IP. If the domains resolve to more than 1 IP Address then this bypass wont be effective.

You can also apply the bypass via PAC file. If your ZCC version is 4.0 or above you can add the bypass on the APP Profile PAC file, there is no need to add it on the Forwarding Profile PAC.

As reference for the bypass:

https://help.zscaler.com/zscaler-client-connector/best-practices-adding-bypasses-z-tunnel-2.0

For the PAC file, you can use this other documentation for guidance process on which statements to use:

https://help.zscaler.com/zia/writing-pac-file

I hope this has clear all of your doubts on this behavior.

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

Forbury Add-in in Excel doesn't load while connected to ZCC
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000jKoJh0AK/starlink-disconnect-issues","lastmod":"2025-12-25T19:51:41.000Z","id":"0D5PJ00000jKoJh0AK"} -->
## Starlink disconnect issues

- Source: https://community.zscaler.com/s/question/0D5PJ00000jKoJh0AK/starlink-disconnect-issues
- Type: Q&A
- Last activity: 2025-12-25T19:51:41.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

emccaughrin

(Customer) asked a question.

November 10, 2025 at 11:38 PM

Starlink disconnect issues

Have a Starlink connection at a remote site where there are some obstructions -- but still 97% ping success. There are occasional blackout times with packet loss for several seconds (sometimes as long as 10s). These events are causing a ZScalar disconnect while not frequent are nonetheless annoying.

Is there a ZScalar setting that would allow for greater timeout threshold before triggering disconnect?

The Starlink works fine with streaming and even MS Teams calls, but the ZScalar is causing headaches.

Client Connector

2 answers

412 views

Ramesh Mani

(Partner)

8 months ago

Have you captured the Wireshark  logs at Zscaler ZCC which will help you to identify the route cause.

Ecruz lZ30030

(Employee)

7 months ago

Hello @Orac Dork​ ,

Is the ZCC Client connecting to the closest Zscaler Datacenter based on the geographic location  of the device at the remote location? If not, you can override the setting from a PAC File to force the client to connect to the closest DC.

Also, if you are suing Z-Tunnel 2.0 you can trigger Dynamic Service Edge assignment in which ZCC Client will be sending constant HTTP-Based probes to the Primary and Secondary Service Edges by comparing the latency and trigger a failover in case the performance to the Primary Service Edge is degraded while connected to Starlink. It will then connect to the Secondary DC and keep the connection.

You can  configure the three main parameters: Probe Interval, Probe Sample Size, and Threshold Limit.

Probe Interval dictates how often the probe is made to the primary and secondary service edge (The minimum value is 0.5, and the maximum value is 10 min). Probe Sample Size dictates the confidence level required to fail from the primary to the secondary service edge or vice versa. To fail over, it requires all consecutive n tries (i.e., the value set by the administrators) to meet the Threshold value.

Finally, the Threshold Limit, which represents the minimum percentage delta in latency between the primary and the secondary that is required to trigger the failover.

You can also try to run a Wireshark packet capture at ZCC level and also from the device level itself to see where the Resets are being generating and see which host is closing the connection.

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

2,888

2888 Views

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

Starlink disconnect issues
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000khixp0AA/zscaler-causing-slow-internet-connection-mostly-from-800pm-1000pm-ph-time","lastmod":"2026-01-07T17:44:38.000Z","id":"0D5PJ00000khixp0AA"} -->
## Zscaler causing slow internet connection mostly from 8:00PM - 10:00PM PH time

- Source: https://community.zscaler.com/s/question/0D5PJ00000khixp0AA/zscaler-causing-slow-internet-connection-mostly-from-800pm-1000pm-ph-time
- Type: Q&A
- Last activity: 2026-01-07T17:44:38.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

broQ

(Customer) asked a question.

November 21, 2025 at 1:10 PM

Zscaler causing slow internet connection mostly from 8:00PM - 10:00PM PH time

Who else here experiencing this kind of weird issue. Since deploying zcc on our endpoint in the philippines, internet connection is very slow specially around 8-10pm, even their own speed testing tool (http://speedtest.zscaler.com/perf) keeps on loading and no result found. Weird because it is working smoothly on other timeframe. and same result on different ISP.

Client Connector

5 answers

519 views

ozanogur

(Partner)

8 months ago

Have you performed some basic troubleshooting steps like checking the MTR etc? The following link could be helpful:

https://help.zscaler.com/zia/measuring-performance-zscaler-service

1 like

broQ

(Customer)

8 months ago

Yes, we did check MTR and seems to have more packet loss within the timeframe compare to normal business hours. Speed testing also inaccurate even the same saas application and services opened in a a single device results give us kbps up to 10Mbps out of a 100Mpbs average. It doesn't make sense as the only remedy to have better performance is turning off zcc but not recommended for security and compliance.

ozanogur

(Partner)

8 months ago

I would suggest a coordinated support call with Zscaler and someone from your ISP side to check potential issues between Zscaler and ISP.

Ramesh Mani

(Partner)

8 months ago

If the issue is specific to a time duration, definitely this would be at ISP end issues or the network devices (CE) at your end.  Do a detailed network level troubleshooting.

ricoybanez

(Customer)

7 months ago

we are also experiencing same issue started dec. 28, 2025

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

Zscaler causing slow internet connection mostly from 8:00PM - 10:00PM PH time
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000kpyJA0AY/ios-shared-ipad-zcc-deployment-help","lastmod":"2025-11-25T14:59:09.000Z","id":"0D5PJ00000kpyJA0AY"} -->
## iOS Shared iPad + ZCC Deployment: Help

- Source: https://community.zscaler.com/s/question/0D5PJ00000kpyJA0AY/ios-shared-ipad-zcc-deployment-help
- Type: Q&A
- Last activity: 2025-11-25T14:59:09.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

Alex_Pilallis

(Customer) asked a question.

November 24, 2025 at 10:14 PM

iOS Shared iPad + ZCC Deployment: Help

Hey everyone — looking for guidance from anyone who has deployed

Zscaler Client Connector (ZCC)

on

shared iPads

at scale.

Environment

~70 shared iPads, used by ~350+ rotating users

Configured via

Intune

Using

ZCC as IDP

(Touchless SSO)

Not enrolling individual users into ZIA (we don’t want user-based policy here)

Using a

Device Token

created in ZCC and pushed through Intune app config.

Problem

When ZCC launches, it

does

authenticate, but always signs in as:

MobileUser@

That identity is reused across devices, and as soon as we hit the limit (~16 active MobileUser logins), authentication starts failing. This is a typical setting for ZCC.

Ask

Has anyone successfully forced ZCC on iOS (shared iPad mode) to authenticate

only using the device token

, without auto-assigning the MobileUser@… identity?

Ideally, we want:

Username =

Device Serial Number

Intune Device ID

, or

any unique per-device value

No reliance on MobileUser@… since it’s non-unique and breaks at scale

What we’ve tried

Device token assigned in ZCC → Intune profile

Touchless SSO configured with ZCC as IDP

Verified device token is accepted

Still ends up identifying as MobileUser

Any successful shared-device ZCC deployments on iOS that avoid this behavior? Looking for best practices or config tricks others are using.

Thanks in advance.

Client Connector

312 views

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

3,572

3572 Views

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

iOS Shared iPad + ZCC Deployment: Help
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000lFzWd0AK/syncing-directory-groups-between-the-zia-and-zcc","lastmod":"2025-12-04T16:00:41.000Z","id":"0D5PJ00000lFzWd0AK"} -->
## Syncing Directory Groups between the ZIA and ZCC

- Source: https://community.zscaler.com/s/question/0D5PJ00000lFzWd0AK/syncing-directory-groups-between-the-zia-and-zcc
- Type: Q&A
- Last activity: 2025-12-04T16:00:41.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

User15799672102206064289

(Customer) asked a question.

December 2, 2025 at 5:59 AM

Syncing Directory Groups between the ZIA and ZCC

Hello

I use official SDK Python for manage my users and groups with API in ZIA. API work fine

But, with API, I can't sync directory group between ZIA and ZCC.

This API call (sync group ZIA on ZCC) is in the SDK roadmap ?

Thanks

Client Connector

1 answer

430 views

Ramesh Mani

(Partner)

8 months ago

No API endpoints available for group sync between ZIA to ZCC.

Log In to Answer

Associated Tags

api

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

2,888

2888 Views

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

Syncing Directory Groups between the ZIA and ZCC
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000lny8F0AQ/zcc-vdi-and-zpa-how-do-we-set-an-access-policy-for-the-vdi-system-user","lastmod":"2026-04-30T14:08:06.000Z","id":"0D5PJ00000lny8F0AQ"} -->
## ZCC VDI and ZPA - how do we set an access policy for the VDI system user?

- Source: https://community.zscaler.com/s/question/0D5PJ00000lny8F0AQ/zcc-vdi-and-zpa-how-do-we-set-an-access-policy-for-the-vdi-system-user
- Type: Q&A
- Last activity: 2026-04-30T14:08:06.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZPA - Authentication

hukel

(Customer) asked a question.

December 11, 2025 at 8:48 PM

ZCC VDI and ZPA - how do we set an access policy for the VDI system user?

We have set up ZCC VDI behind a Cloud Connector and are forwarding traffic to ZPA.

I can't figure out how to to grant permissions for "

vdi-system-user@9999.zscalerxxxx.net

" (from the VDI side) to SMB access policies in the ZPA side.

The troubleshooting guide mentions the problem, but does not point to the solution.

https://help.zscaler.com/cloud-branch-connector/troubleshooting-zscaler-client-connector-vdi

SMB, Kerberos, Windows network, DNS, ICMP, and NFS traffic are treated as system user traffic by Zscaler Client Connector for VDI. Ensure that ZPA has a correct access policy configured for the system user.

This "system user" isn't in our ZPA IDP, so how do I grant access to it?

I see the SMB traffic being forwarded to ZPA (in the CC logs), but don't see any logs for it in the ZPA side. It's as is it gets dropped and not even logged.

ZPA - Authentication

2 answers

469 views

Jerry P. Gurrea

(Customer)

7 months ago

Might not be a complete solution but hopefully can get you going.

To onboard users who access resources via SMB, Kerberos, Windows network, DNS, ICMP, and NFS, you must configure your IdP (such as Azure AD, ADFS, Okta, etc.) to authenticate these users. ZPA does not directly add system users; instead, it relies on your IdP to manage user identities and authentication.

For Kerberos and SMB (such as access to Distributed File Servers), you should define an application segment in ZPA using a wildcard domain and the required ports (e.g., TCP 445 for SMB, TCP/UDP 88 for Kerberos, etc.).

Example: To configure access to a DFS using Kerberos, create an application segment with a wildcard domain (e.g., *.purple.world) and specify the necessary TCP/UDP ports (e.g., TCP 135, 445, 464, 3268, 3269, 88, 139; UDP 389, 445, 464, 88, 138).

Once the application segment is defined, users authenticated via your IdP can access these resources through ZPA.

Example Configuration Steps:

Set up your IdP (e.g., Azure AD, ADFS) and specify ZPA as the Service Provider (SP).

In the ZPA Admin Portal, go to Add IdP Configuration:

Give the IdP a name.

Select User under Single Sign-On.

Add authentication domains (e.g.,

testmypacket.com

).

Upload the Federation Metadata XML file and IdP certificate.

Save the configuration.

Define application segments for your network resources (e.g., SMB, Kerberos, NFS) using wildcard domains and required ports.

zezo

(Customer)

3 months ago

Ran into this myself and was able to address it using the following

-----------------------------------------------------------

Implement a dedicated VDI System user identity, ensure it is synced and recognized across ZIA/ZPA, and then permit it in ZPA Access Policy. Apply the updated VDI template in CC to enforce the mapping.

1. Create a system user in your IdP and synchronize it to ZIA:

In your IdP, create a dummy user representing the VDI System identity with group and department attributes.

Ensure this user is synced to ZIA (via your usual IdP- ZIA integration).

2. Assign the user to the predefined ZIA “System user group”:

In the ZIA admin portal, add the newly created user to the System user group so that system traffic can be attributed to this identity.

3. Confirm the System user appears in Zscaler Cloud Connector Portal:

In the Zscaler Cloud Connector portal, navigate to Administration → VDI Template and open the System user dropdown.

Verify the new user is listed as a System_user option.

4. Ensure SCIM is enabled in ZPA:

In ZPA, verify SCIM provisioning is enabled so that the user and group information is available for policy evaluation.

5. Create or update a ZPA Access Policy to permit the VDI System user:

In ZPA Access Policy, add or modify a rule to allow the new VDI System user to the SMB application(s) required.

6. Create a new VDI Template in Zscaler Cloud Connector Portal and apply to AVDs:

Create a new VDI template in the Zscaler Cloud Connector portal with Auth_type->IDP and new user as System_user

Apply the new template to the affected AVD machines.

7. Validate post-change:

Re-test SMB access from an affected AVD.

Confirm if Zscaler Cloud Connector Session Logs still shows SMB traffic forwarded to ZPA and whether ZPA logs/policy hits appear for the new VDI System user.

If policy still does not match, re-check ZPA Access Policy criteria (attributes, client type).

------------------------

Hope this helps :)

Log In to Answer

Associated Tags

accesspolicy

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

5/27/2022

at

03:55 PM

ZPA Authentication Timeout

ZPA - Authentication

katlyn.gallo

8 Views

0 Likes

4 Comments

12/18/2021

at

04:06 AM

ZPA Authentication timers

ZPA - Authentication

xavi1

3 Views

0 Likes

4 Comments

10/1/2020

at

02:18 PM

Accessing AWS Workspaces

ZPA - Authentication

Gordon Wright

4,357

4357 Views

0 Likes

5 Comments

8/22/2024

at

08:03 PM

ZPA device authentication

ZPA - Authentication

avshch

682

682 Views

0 Likes

3 Comments

1/28/2025

at

07:02 AM

PSE can't connect to APC

ZPA - Authentication

Vinod

274

274 Views

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

ZCC VDI and ZPA - how do we set an access policy for the VDI system user?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000lnype0AA/automating-app-profile-assignment-to-new-users","lastmod":"2026-01-23T12:34:51.000Z","id":"0D5PJ00000lnype0AA"} -->
## automating app profile assignment to new users

- Source: https://community.zscaler.com/s/question/0D5PJ00000lnype0AA/automating-app-profile-assignment-to-new-users
- Type: Q&A
- Last activity: 2026-01-23T12:34:51.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZPA - Admin Portal

rootedmind

(Customer) asked a question.

December 11, 2025 at 8:11 PM

automating app profile assignment to new users

Is it possible to automate app profile assignment to new users via SCIM? Currently, I have to wait for new users to login first before I have the option to assign an app profile to a new user. Just wondering if anyone else has solved for this by using automation? Any ideas or suggestions will help, thanks.

ZPA - Admin Portal

4 answers

307 views

Arun

(Partner)

7 months ago

You can create group based app profile,so the users will get applied & get access.SCIM will do autoprovision ,so that will sync your users and group.

rootedmind

(Customer)

7 months ago

Thank you for the reply Arun, I appreciate it. Looking at the documentation, this feature appears to be restricted to ZIA but we do not have ZIA unfortunately, we're running ZPA only. I can assign specific users to the app profile but unfortunately, I have to wait for the user to sign-in first before I see their username populated and then I'm able to assign the app profile to their account.

rootedmind

(Customer)

7 months ago

I'm wondering if it is possible to automate the app profile assignment using an alternative method for companies that have ZPA only. If anyone has any suggestions, please let me know.  Any help is greatly appreciated.

Kris

(Customer)

6 months ago

Arun already answered this, app profile has notihng to do with ZIA or ZPA, that is controlled by the forwarding policy.

You cannot take away the ZIA entitlement as that is the core of this whole service, but you can disable ZIA on all network types.

Log In to Answer

Associated Tags

automation

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/5/2024

at

12:52 PM

Agent version report

ZPA - Admin Portal

Chandram81

322

322 Views

0 Likes

3 Comments

1/24/2025

at

11:09 AM

App connector Enrollment issue

ZPA - Admin Portal

Vinod

341

341 Views

0 Likes

3 Comments

8/26/2025

at

09:00 AM

Short name on ZPA on Macs don't work

ZPA - Admin Portal

Diode

412

412 Views

0 Likes

3 Comments

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

automating app profile assignment to new users
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000mBt7B0AS/client-connector-on-linux-wayland","lastmod":"2025-12-25T20:15:09.000Z","id":"0D5PJ00000mBt7B0AS"} -->
## Client Connector on Linux Wayland

- Source: https://community.zscaler.com/s/question/0D5PJ00000mBt7B0AS/client-connector-on-linux-wayland
- Type: Q&A
- Last activity: 2025-12-25T20:15:09.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

rcox

(Customer) asked a question.

December 18, 2025 at 8:33 PM

Client Connector on Linux Wayland

Has anyone figured out how to get the UI to work correctly for the zScaler Client Connector on a Linux machine using wayland. I have it running and the tunnel works but the UI just shows the service status as connecting. While its not a big deal since I still can access internal resources was wondering if anyone had any success fixing this.

Client Connector

1 answer

360 views

Ecruz lZ30030

(Employee)

7 months ago

Hello @Rob Cox​ ,

So, the access to internal and external resources is working fine with ZCC logged In but stuck in Connecting state?

Please share the screenshot of what ZCC is showing and also ZCC debug logs along with the timestamp.

Also, is the latest ZCC Linux version 4.2 installed on the device?

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

2,881

2881 Views

0 Likes

11

11 Comments

7/3/2020

at

11:55 AM

ZAPP intune deployment

Client Connector

Mk001

1,376

1376 Views

0 Likes

4 Comments

1/28/2021

at

03:42 PM

Compare ezAgent and ZCC - when to use which?

Client Connector

hukel

701

701 Views

0 Likes

2 Comments

8/18/2020

at

12:15 PM

MacOS Zscaler App Log Location

Client Connector

brad

3,565

3565 Views

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

Client Connector on Linux Wayland
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000mLQXO0A4/zscaler-cli-on-macbook","lastmod":"2025-12-22T19:14:45.000Z","id":"0D5PJ00000mLQXO0A4"} -->
## Zscaler CLI on macbook

- Source: https://community.zscaler.com/s/question/0D5PJ00000mLQXO0A4/zscaler-cli-on-macbook
- Type: Q&A
- Last activity: 2025-12-22T19:14:45.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

m.stoof

(Partner) asked a question.

December 22, 2025 at 12:58 PM

Zscaler CLI on macbook

Hi,

I've been using Zscaler for a little bit and wanted to start doing some automations using the zscli on my macbook. However I noticed that on macbook only ZPA is allowed to disable and enable using the CLI.

Is this on the roadmap to enable this for ZIA and ZDX aswell?

I couldn't find any of this information anywhere. Thanks in advanced.

Resources used:

https://help.zscaler.com/zscaler-client-connector/interacting-zscaler-client-connector-remotely

394 views

Log In to Answer

Associated Tags

automation

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

492

492 Views

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

Zscaler CLI on macbook
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000mMKOS0A4/zscaler-450381installation-failed-","lastmod":"2026-01-28T18:44:55.000Z","id":"0D5PJ00000mMKOS0A4"} -->
## Zscaler 4.5.0.381_Installation Failed _

- Source: https://community.zscaler.com/s/question/0D5PJ00000mMKOS0A4/zscaler-450381installation-failed-
- Type: Q&A
- Last activity: 2026-01-28T18:44:55.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

manju1985

(Customer) asked a question.

December 22, 2025 at 5:45 PM

Zscaler 4.5.0.381_Installation Failed _

Hi Team,

We are installing the Zscaler 4.5.0.381 application in Windows 11 environment, but the application failed to install with error code 14.

Could you please suggest on this issue.

Client Connector

2 answers

1.2K views

Raj909

(Customer)

6 months ago

It would be best to open a ticket with support regarding this so they can review the ZCC logs.

https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors

14

Zscaler Client Connector Internal Error, Please Contact Administrator.

This error is triggered by the cloud servers.

Export logs and contact Zscaler Support.

Ramesh M

(Employee)

6 months ago

Error code 14 during a Zscaler Client Connector (ZCC) installation on Windows 11 (or any Windows OS) is a relatively generic installer error, but it often points to issues with permissions, conflicting software, or system integrity.

Remove the previous configurations files/ folders from the system and try again

you might need to manually check for:

C:\Program

Files\Zscaler

C:\ProgramData\Zscaler

Registry keys under HKEY_LOCAL_MACHINE\SOFTWARE\Zscaler Network adapters (Device Manager -> Network adapters -> Check for Zscaler Tunnel adapter and uninstall if present) Reboot after any manual cleanup.

*. try Run as administrator

*. Check if there is any block in the EDR solution side

*. Check if the installed file corrupted, download a fresh copy if yes. Tested the same version on my lab and working file,

*. Try install and go with latest versions of ZCC for better support from support team and better performance

Log In to Answer

Associated Tags

azure

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

Zscaler 4.5.0.381_Installation Failed _
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000nDGET0A4/app-stuck-in-white-screen-after-entering-credentials","lastmod":"2026-01-16T15:38:12.000Z","id":"0D5PJ00000nDGET0A4"} -->
## App stuck in white screen after entering credentials

- Source: https://community.zscaler.com/s/question/0D5PJ00000nDGET0A4/app-stuck-in-white-screen-after-entering-credentials
- Type: Q&A
- Last activity: 2026-01-16T15:38:12.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

ak470

(Customer) asked a question.

January 8, 2026 at 7:28 PM

App stuck in white screen after entering credentials

I'm on a Mac (Tahoe 26.2) trying to connect to Zscaler (4.3.1.91), but after I enter in my azure SSO credentials, I just get a white screen - no error or anything. After awhile it just sends me back to the main Zscaler login screen.

Client Connector

2 answers

1.76K views

Top Rated Answers

UserPixie

(Employee)

6 months ago

Good day,

My two cents here.

https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2025

"Fixes an issue where authentication with the IdP for Zscaler Internet Access (ZIA), Zscaler Private Access (ZPA), and ZIdentity failed,

resulting in a blank screen on macOS

Tahoe 26.2 beta."

Please verify the link above and upgrade to a fix in release.

4.5.2.105

4.5.0.331

4.3.1.193

Selected as Best

1 like

All Answers

UserPixie

(Employee)

6 months ago

Good day,

My two cents here.

https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2025

"Fixes an issue where authentication with the IdP for Zscaler Internet Access (ZIA), Zscaler Private Access (ZPA), and ZIdentity failed,

resulting in a blank screen on macOS

Tahoe 26.2 beta."

Please verify the link above and upgrade to a fix in release.

4.5.2.105

4.5.0.331

4.3.1.193

Selected as Best

1 like

ak470

(Customer)

6 months ago

Yes, version 4.5.2.105 solves this! 👏

Log In to Answer

Associated Tags

azure

azure-ad

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

App stuck in white screen after entering credentials
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000nN0uh0AC/search-functionality-within-zoom-workplace-not-functional-when-zscaler-is-used","lastmod":"2026-01-14T19:02:09.000Z","id":"0D5PJ00000nN0uh0AC"} -->
## Search Functionality within Zoom workplace not functional when Zscaler is used

- Source: https://community.zscaler.com/s/question/0D5PJ00000nN0uh0AC/search-functionality-within-zoom-workplace-not-functional-when-zscaler-is-used
- Type: Q&A
- Last activity: 2026-01-14T19:02:09.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

jby_dw_top

(Customer) asked a question.

January 12, 2026 at 1:12 PM

Search Functionality within Zoom workplace not functional when Zscaler is used

Hello everyone, we are piloting Zscaler deployment and since then people who evaluating it via Zscaler client connector we have the problem that certain contacts in our zoom workplace directory are not found. This is only with people using Zscaler and there is no clarity on why certain people cannot be seen in the contacts-search. We only see that when Zscaler people turn off their Zscaler client these missing contacts can be found.

We have waited for the latest Zoom version 6.6.6 to see if anything changed but unfortunetely it didn´t. Zscaler version used 4.5.0.344

Thanks for all ideas.

Client Connector

194 views

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

2,888

2888 Views

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

Search Functionality within Zoom workplace not functional when Zscaler is used
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000oAhdx0AC/zscaler-client-connector","lastmod":"2026-01-28T18:33:58.000Z","id":"0D5PJ00000oAhdx0AC"} -->
## zscaler client connector

- Source: https://community.zscaler.com/s/question/0D5PJ00000oAhdx0AC/zscaler-client-connector
- Type: Q&A
- Last activity: 2026-01-28T18:33:58.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

omar.hosny

(Partner) asked a question.

January 26, 2026 at 3:31 PM

zscaler client connector

Client connector is giving me authentication failed, although the credentials are correct, cloud name is correct.

Client Connector

2 answers

243 views

Raj909

(Customer)

6 months ago

Check that your IdP configuration has been setup successfully.

Client Connector Errors -

https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors

Ramesh M

(Employee)

6 months ago

Whats the error message ? Is this ZCC error / IDP specific error, depending upon that you could troubleshoot the problem.

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

zscaler client connector
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ohNUm0AM/tunnel-10-use-case","lastmod":"2026-04-29T22:31:38.000Z","id":"0D5PJ00000ohNUm0AM"} -->
## Tunnel 1.0 use case

- Source: https://community.zscaler.com/s/question/0D5PJ00000ohNUm0AM/tunnel-10-use-case
- Type: Q&A
- Last activity: 2026-04-29T22:31:38.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

biplab.ghosh

(Partner) asked a question.

February 3, 2026 at 9:45 PM

Tunnel 1.0 use case

I'm new to Zscaler and trying to understand the purpose of Tunnel 1.0. From what I’ve learned, Tunnel 1.0 was the original method Zscaler used, and it only forwards proxy‑aware traffic or traffic on TCP ports 80 and 443. Now that Tunnel 2.0 exists — which can forward all ports and protocols using DTLS or TLS — do we still need Tunnel 1.0?

If Tunnel 1.0 cannot handle anything beyond TCP 80 and 443, meaning all other ports bypass Zscaler and result in less security, then why is Tunnel 1.0 still enabled? Please correct me if I am misunderstanding.

ZIA - Forwarding

1 answer

389 views

Pablo Guzman

(Employee)

3 months ago

Your understanding is mostly correct, but there are a few important details that help explain why

Z-Tunnel 1.0 is still used even when Z-Tunnel 2.0 is available

Z-Tunnel 1.0

was the original Zscaler Client Connector forwarding method. It is proxy-based and primarily handles

web traffic

, meaning proxy-aware traffic and TCP traffic on ports

80 and 443

. It does not provide full-port/full-protocol forwarding. Because of that, if an environment is using

Z-Tunnel 1.0 only

, then non-web traffic, such as traffic on custom TCP ports or UDP-based applications, may bypass Zscaler unless another forwarding method is in place.

Z-Tunnel 2.0

was introduced to address that limitation. It uses

DTLS or TLS tunnels

and can forward

all ports and protocols

, which allows organizations to apply broader ZIA security controls, including Cloud Firewall policies, to non-web traffic.

That said, Z-Tunnel 1.0 still has valid use cases and is commonly kept available for compatibility, performance, and resiliency reasons.

Why Z-Tunnel 1.0 is still useful

Web traffic handling and compatibility

Z-Tunnel 1.0 is lightweight and efficient for standard HTTP and HTTPS traffic. In some deployments, organizations use a hybrid approach where web traffic is handled through the local proxy/Tunnel 1.0 path, while Z-Tunnel 2.0 is used for all other ports and protocols.

PAC and domain-based logic

Certain PAC-based behaviors, including domain-based bypass logic and App Profile PAC logic, rely on the ZCC local listening proxy associated with the Z-Tunnel 1.0 flow. Z-Tunnel 2.0 is tunnel-based and does not process PAC-based domain logic in the same way.

Explicit proxy and proxy-chaining scenarios

Some explicit proxy use cases, third-party proxy chaining, and related forwarding scenarios may still depend on the Z-Tunnel 1.0/local proxy path.

Fallback and resiliency

Z-Tunnel 1.0 can provide a fallback path if Z-Tunnel 2.0 cannot establish properly. For example, some networks or ISPs may block, rate-limit, or deprioritize UDP/DTLS traffic. In other cases, NAT behavior or egress IP differences may impact tunnel establishment. In those situations, keeping Z-Tunnel 1.0 available helps ensure users can still receive web protection instead of losing connectivity or bypassing Zscaler entirely.

Application compatibility

Some applications or network behaviors may perform better or be more compatible through the 1.0/proxy-based path. This is one reason some organizations continue to use Tunnel 1.0 selectively, especially for standard web traffic.

Security clarification

You are correct that

Tunnel 1.0 by itself does not provide the same level of coverage as Tunnel 2.0

. If only Tunnel 1.0 is used, then non-web traffic is not captured by ZIA through that tunnel. For full traffic visibility and enforcement across all ports and protocols, Z-Tunnel 2.0, GRE, IPSec, or another full-forwarding method should be used.

The reason Tunnel 1.0 remains enabled is not because it replaces Tunnel 2.0, but because it still supports important operational use cases such as PAC-based logic, explicit proxy behavior, compatibility, performance optimization for web traffic, and fallback scenarios.

Recommended approach

In most modern deployments, the preferred design is to use

Z-Tunnel 2.0 as the primary forwarding method

for full traffic coverage, while keeping

Z-Tunnel 1.0 available

for specific web proxy use cases, PAC/domain-based logic, explicit proxy scenarios, and fallback resiliency.

So the short answer is:

yes, Tunnel 2.0 should generally be the primary method for full security coverage, but Tunnel 1.0 is still kept because it provides compatibility, web-traffic optimization, PAC/local proxy functionality, and fallback protection when Tunnel 2.0 cannot be used successfully.

1 like

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

Tunnel 1.0 use case
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ohPQB0A2/tunnel-10-use-case","lastmod":"2026-04-29T22:31:33.000Z","id":"0D5PJ00000ohPQB0A2"} -->
## Tunnel 1.0 use case

- Source: https://community.zscaler.com/s/question/0D5PJ00000ohPQB0A2/tunnel-10-use-case
- Type: Q&A
- Last activity: 2026-04-29T22:31:33.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Forwarding

biplab.ghosh

(Partner) asked a question.

February 3, 2026 at 9:45 PM

Tunnel 1.0 use case

I'm new to Zscaler and trying to understand the purpose of Tunnel 1.0. From what I’ve learned, Tunnel 1.0 was the original method Zscaler used, and it only forwards proxy‑aware traffic or traffic on TCP ports 80 and 443. Now that Tunnel 2.0 exists — which can forward all ports and protocols using DTLS or TLS — do we still need Tunnel 1.0?

If Tunnel 1.0 cannot handle anything beyond TCP 80 and 443, meaning all other ports bypass Zscaler and result in less security, then why is Tunnel 1.0 still enabled? Please correct me if I am misunderstanding.

ZIA - Forwarding

1 answer

296 views

Pablo Guzman

(Employee)

3 months ago

Your understanding is mostly correct, but there are a few important details that help explain why

Z-Tunnel 1.0 is still used even when Z-Tunnel 2.0 is available

Z-Tunnel 1.0

was the original Zscaler Client Connector forwarding method. It is proxy-based and primarily handles

web traffic

, meaning proxy-aware traffic and TCP traffic on ports

80 and 443

. It does not provide full-port/full-protocol forwarding. Because of that, if an environment is using

Z-Tunnel 1.0 only

, then non-web traffic, such as traffic on custom TCP ports or UDP-based applications, may bypass Zscaler unless another forwarding method is in place.

Z-Tunnel 2.0

was introduced to address that limitation. It uses

DTLS or TLS tunnels

and can forward

all ports and protocols

, which allows organizations to apply broader ZIA security controls, including Cloud Firewall policies, to non-web traffic.

That said, Z-Tunnel 1.0 still has valid use cases and is commonly kept available for compatibility, performance, and resiliency reasons.

Why Z-Tunnel 1.0 is still useful

Web traffic handling and compatibility

Z-Tunnel 1.0 is lightweight and efficient for standard HTTP and HTTPS traffic. In some deployments, organizations use a hybrid approach where web traffic is handled through the local proxy/Tunnel 1.0 path, while Z-Tunnel 2.0 is used for all other ports and protocols.

PAC and domain-based logic

Certain PAC-based behaviors, including domain-based bypass logic and App Profile PAC logic, rely on the ZCC local listening proxy associated with the Z-Tunnel 1.0 flow. Z-Tunnel 2.0 is tunnel-based and does not process PAC-based domain logic in the same way.

Explicit proxy and proxy-chaining scenarios

Some explicit proxy use cases, third-party proxy chaining, and related forwarding scenarios may still depend on the Z-Tunnel 1.0/local proxy path.

Fallback and resiliency

Z-Tunnel 1.0 can provide a fallback path if Z-Tunnel 2.0 cannot establish properly. For example, some networks or ISPs may block, rate-limit, or deprioritize UDP/DTLS traffic. In other cases, NAT behavior or egress IP differences may impact tunnel establishment. In those situations, keeping Z-Tunnel 1.0 available helps ensure users can still receive web protection instead of losing connectivity or bypassing Zscaler entirely.

Application compatibility

Some applications or network behaviors may perform better or be more compatible through the 1.0/proxy-based path. This is one reason some organizations continue to use Tunnel 1.0 selectively, especially for standard web traffic.

Security clarification

You are correct that

Tunnel 1.0 by itself does not provide the same level of coverage as Tunnel 2.0

. If only Tunnel 1.0 is used, then non-web traffic is not captured by ZIA through that tunnel. For full traffic visibility and enforcement across all ports and protocols, Z-Tunnel 2.0, GRE, IPSec, or another full-forwarding method should be used.

The reason Tunnel 1.0 remains enabled is not because it replaces Tunnel 2.0, but because it still supports important operational use cases such as PAC-based logic, explicit proxy behavior, compatibility, performance optimization for web traffic, and fallback scenarios.

Recommended approach

In most modern deployments, the preferred design is to use

Z-Tunnel 2.0 as the primary forwarding method

for full traffic coverage, while keeping

Z-Tunnel 1.0 available

for specific web proxy use cases, PAC/domain-based logic, explicit proxy scenarios, and fallback resiliency.

So the short answer is:

yes, Tunnel 2.0 should generally be the primary method for full security coverage, but Tunnel 1.0 is still kept because it provides compatibility, web-traffic optimization, PAC/local proxy functionality, and fallback protection when Tunnel 2.0 cannot be used successfully.

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

Tunnel 1.0 use case
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000pfgxF0AQ/zcaler-connection-issue","lastmod":"2026-04-03T18:30:19.000Z","id":"0D5PJ00000pfgxF0AQ"} -->
## Zcaler connection issue

- Source: https://community.zscaler.com/s/question/0D5PJ00000pfgxF0AQ/zcaler-connection-issue
- Type: Q&A
- Last activity: 2026-04-03T18:30:19.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZIA - Browser Isolation

scagle4

(Customer) asked a question.

February 19, 2026 at 3:38 PM

Zcaler connection issue

I am trying to access asn application but my IT team says i need Zscaler to access the app. however when I click on my icon for zscaler, it says connection error in the service status. we have clicked retry, we have uninstalled and reinstalled the app, updates aap and policies. at one point it popped up on my IT ticket that altiris agent might be having issues. She also said there is something stopping zscaler form connecting to my network

ZIA - Browser Isolation

3 answers

639 views

Ecruz lZ30030

(Employee)

5 months ago

Hello @savannah Cagle​

Are you getting the "

Connection Error

" at the

Private Access

tab or

Internet Security

tab in the client?

That appears to be related to general reachability to Zscaler Services either something directly on the machine blocking the Zscaler App connections, or at the network/ISP level. You may want to check with your IT team if you have any 3rd party security software (Antivurus/Firewall/VPN) that night be blocking the Zscaler App inbound connections, by default running over TCP port 9000.

A packet capture that can be enabled under the

More

tab option in the Zscaler App would also help if you start it and then click on "

Retry

" option.  This will provide better understanding on where the connections are being closed along with the Zscaler App client logs in

Debug

mode.

GordonWright

(Customer)

4 months ago

Try turning off DoH if you have it enabled.

Raj909

(Customer)

4 months ago

If it's related to ZIA, it appears the Public Service Edge cannot be reached.

Zscaler Client Connector: Connection Status Errors

https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors

Please confirm allowed rules and proper exceptions are allowed on local firewall and antivirus.

https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist

Log In to Answer

Associated Tags

azure

azure-ad

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

Zcaler connection issue
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000pk0xN0AQ/zscaler-client-connector-version-470202-or-480115","lastmod":"2026-04-07T11:51:59.000Z","id":"0D5PJ00000pk0xN0AQ"} -->
## Zscaler Client connector version 4.7.0.202 or 4.8.0.115

- Source: https://community.zscaler.com/s/question/0D5PJ00000pk0xN0AQ/zscaler-client-connector-version-470202-or-480115
- Type: Q&A
- Last activity: 2026-04-07T11:51:59.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

cjharmening

(Customer) asked a question.

February 20, 2026 at 3:36 PM

Zscaler Client connector version 4.7.0.202 or 4.8.0.115

Hello all, With the craziness of most past ZCC versions have vulnerabilities , not supported as LTS or having issues. Has anyone started looking at the two latest ZCC 4.7.0.202 or 4.8.0.115 and done any testing. We have started testing against both lately and was wondering if anyone was seeing inconsistencies or issues. We have not seen any issues in our testing but never know sometimes other people can find things others do not. Much appreciated .

Client Connector

3 answers

1.34K views

Arvind L Jaiswal

(Partner)

5 months ago

Both versions are enabled in one of our customer environments, and no issues have been observed so far.

Raj909

(Customer)

4 months ago

No issues observed with 4.7.0.202 build as that is our current standard.   Early builds of 4.8 have caused odd issues, but the latest 4.8.0.156 is stable in our testing and recommend moving to this release.

1 like

GordonWright

(Customer)

4 months ago

They key here is to have a robust and repeatable testing process.

Upgrade quarterly/bi-annually.

Deploy cautiously. We find the 'Phased Rollout' works really well.

Keep pushing Zscaler to release new client features simultaneously on Windows/macOS  😀

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

2,892

2892 Views

0 Likes

11

11 Comments

7/3/2020

at

11:55 AM

ZAPP intune deployment

Client Connector

Mk001

1,383

1383 Views

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

3,578

3578 Views

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

Zscaler Client connector version 4.7.0.202 or 4.8.0.115
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000qENNo0AO/zapp-ends-in-endpoint-fwav-error","lastmod":"2026-03-02T14:49:48.000Z","id":"0D5PJ00000qENNo0AO"} -->
## Zapp ends in Endpoint FW/AV error

- Source: https://community.zscaler.com/s/question/0D5PJ00000qENNo0AO/zapp-ends-in-endpoint-fwav-error
- Type: Q&A
- Last activity: 2026-03-02T14:49:48.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

INFO

Ganesh_Krishnan

(Customer) to

sfdc

(Employee): asked a question.

July 24, 2019 at 9:54 AM

Zapp ends in Endpoint FW/AV error

user facing issue Zapp is logged in. It stays connected for 30sec and get error related to Endpoint FW/AV error. Restart service, Repair, Reboot, Logout ends in same error. We open port 9000 via GPO. Gpupdate is successful in user machine. Still issue persists only with one user. ZSATray logs says “FIREWALL BLOCK ERROR?.

#NORMAL

#INFO

: ZIA state changed, From: TUNNEL_FORWARDING To: FIREWALL_BLOCK_ERROR

Regards

Ganesh krishnan

NORMAL

INFO

76 views

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/24/2019

at

09:54 AM

Zapp ends in Endpoint FW/AV error

INFO

Gk

17,123

17123 Views

1 Like

20

20 Comments

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

Zapp ends in Endpoint FW/AV error
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000qaYHK0A2/zcc-on-ipads-certificate-issue","lastmod":"2026-03-10T13:58:30.000Z","id":"0D5PJ00000qaYHK0A2"} -->
## ZCC on iPads - Certificate issue

- Source: https://community.zscaler.com/s/question/0D5PJ00000qaYHK0A2/zcc-on-ipads-certificate-issue
- Type: Q&A
- Last activity: 2026-03-10T13:58:30.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

dzambrano

(Customer) asked a question.

Edited by Ben_Garrison March 10, 2026 at 1:58 PM

ZCC on iPads - Certificate issue

<p>Hello community,</p><p><br></p><p>I would like to know if anyone has successfully deployed the ZCC client on iPads and made it works.</p><p>We are currently facing an issue: ZCC is already installed on our iPads through Workspace ONE, which is our MDM solution.</p><p><br></p><p>However, we are encountering a certificate problem. Even though the certificate installed is the root certificate used by Zscaler in our environment, the iPads still mark it as untrusted. We have attempted to install the certificate both through our MDM and manually, but the issue persists.</p><p><br></p><p>After contacting Apple Support, they indicated that iPads need to receive the full certificate chain, including the intermediate certificates.</p><p>Has anyone experienced this issue before or found a solution? is there any well-known workaround? </p><p><br></p><p>Thank you,</p><p>David.</p>

Client Connector

227 views

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

ZCC on iPads - Certificate issue
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000qlMae0AE/zcc-upgrade-with-phase-rollout","lastmod":"2026-03-19T15:06:48.000Z","id":"0D5PJ00000qlMae0AE"} -->
## ZCC Upgrade with Phase rollout

- Source: https://community.zscaler.com/s/question/0D5PJ00000qlMae0AE/zcc-upgrade-with-phase-rollout
- Type: Q&A
- Last activity: 2026-03-19T15:06:48.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

Kalsy

(Partner) asked a question.

March 11, 2026 at 6:29 PM

ZCC Upgrade with Phase rollout

I initiated the

ZCC upgrade from the ZCC portal using the Phased Rollout option

about a week ago. I noticed that the rollout percentage now shows

100% completed

. However, most users’

ZCC versions are still showing the older version

in the

ZCC Enrolled Devices

list.

Could anyone please confirm whether this is

expected behavior

or if this is a

known issue

Client Connector

4 answers

636 views

GordonWright

(Customer)

4 months ago

This is normal. Some of the clients may have not logged in yet to get the upgrade or perhaps the client upgrade is failing and a re-install is required.

1 like

Kalsy

(Partner)

4 months ago

I agree; however, I can see that users who have not yet been upgraded are still showing very recent last connection times for both ZIA and ZPA.

GordonWright

(Customer)

4 months ago

I would suggest that the client update was failing and a re-install is required.

Tuchek

(Customer)

4 months ago

Verify the group membership that your rollout is scoped to as well.  We ran into this where our scoping was wrong so Zscaler reported 100% complete while most were not upgraded.

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

ZCC Upgrade with Phase rollout
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000rBrha0AC/zcc-for-vdi-support-for-partner-tenant-logins","lastmod":"2026-03-24T14:27:19.000Z","id":"0D5PJ00000rBrha0AC"} -->
## ZCC for VDI support for partner tenant logins

- Source: https://community.zscaler.com/s/question/0D5PJ00000rBrha0AC/zcc-for-vdi-support-for-partner-tenant-logins
- Type: Q&A
- Last activity: 2026-03-24T14:27:19.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

avshch

(Customer) asked a question.

March 18, 2026 at 1:32 PM

ZCC for VDI support for partner tenant logins

Hello,

Would ZCC for VDI support partner tenant logins?

Thanks,

Client Connector

1 answer

778 views

CasperKroes

(Customer)

4 months ago

As far as I know it would not. The ZCC for VDI just tags the traffic for the Cloud/Branch connector. The Cloud/Branch connector then routes the traffic to the tenant it belongs to.

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

3,573

3573 Views

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

ZCC for VDI support for partner tenant logins
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000rLPjW0AW/zstray-segfault-on-fedora-43","lastmod":"2026-05-04T15:52:18.000Z","id":"0D5PJ00000rLPjW0AW"} -->
## ZSTray Segfault on Fedora 43

- Source: https://community.zscaler.com/s/question/0D5PJ00000rLPjW0AW/zstray-segfault-on-fedora-43
- Type: Q&A
- Last activity: 2026-05-04T15:52:18.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

nick_at_coty

(Customer) asked a question.

Edited March 20, 2026 at 10:43 PM

ZSTray Segfault on Fedora 43

I recently updated Fedora from 41 to 43, since 41 is officially out of support. I can no longer launch ZSTray - I get a segfault/core dump. I've uninstalled and reinstalled, but still hitting the error. Any known issues with this, or anything I can do to troubleshoot or fix it? Or is there any way to authenticate to ZScaler without the Tray icon?

Also, I've tried the install on a clean Fedora 43 system, and, after resolving missing dependencies (dbus-glib and qt5-webengine), the behavior is the same - segfault when trying to start ZSTray.

Client Connector

1 answer

351 views

VladimirMichalek

(Customer)

Edited May 4, 2026 at 10:47 AM

Today I get ZSTray segfault on Ubuntu 22 with Gnome 42.9 and latest updates, kernel 6.8.0-111 (reboot with kernel -107 didn't help). Likely some recent patch broke it.

CLIENT_CONNECTOR_VERSION="4.2.0.64"

Log In to Answer

Associated Tags

linux

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

2,881

2881 Views

0 Likes

11

11 Comments

7/3/2020

at

11:55 AM

ZAPP intune deployment

Client Connector

Mk001

1,374

1374 Views

0 Likes

4 Comments

1/28/2021

at

03:42 PM

Compare ezAgent and ZCC - when to use which?

Client Connector

hukel

698

698 Views

0 Likes

2 Comments

8/18/2020

at

12:15 PM

MacOS Zscaler App Log Location

Client Connector

brad

3,562

3562 Views

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

ZSTray Segfault on Fedora 43
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000raozj0AA/assistance-needed-for-zscaler-installation-on-macbook","lastmod":"2026-04-04T03:01:27.000Z","id":"0D5PJ00000raozj0AA"} -->
## Assistance Needed for Zscaler Installation on MacBook

- Source: https://community.zscaler.com/s/question/0D5PJ00000raozj0AA/assistance-needed-for-zscaler-installation-on-macbook
- Type: Q&A
- Last activity: 2026-04-04T03:01:27.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

IAMKC

(Customer) asked a question.

Edited by Ben_Garrison March 26, 2026 at 6:00 PM

Assistance Needed for Zscaler Installation on MacBook

<p>Dear Zenith Community / IT Support,</p><p>I hope you are doing well.</p><p>I am currently able to access the Zscaler Client Connector VPN on my iPhone (iOS) without any issues. However, I am unable to find or install the Zscaler Client Connector on my MacBook.</p><p>Could you kindly provide guidance or the installer link for macOS, or any instructions required to set it up on my MacBook? </p><p>Thank you very much for your support.</p><p><img src="/sfc/servlet.shepherd/version/download/068PJ00000ZSdaI?asPdf=false&amp;operati</p><p>Kind regards,</p><p>Kaycelyn</p><p><br></p><p><br></p>

Client Connector

3 answers

593 views

GordonWright

(Customer)

4 months ago

Your own internal support team should be able to provide you with a link.

This is not a general support forum. You should raise a ticket directly with Zscaler through the support portal.

Raj909

(Customer)

4 months ago

You can download  Zscaler Client Connector for macOS from the Client Connector Portal.

Ramesh M

(Employee)

4 months ago

If you have ZCC console access, download from Administration -Client connector Appstore - Registered devices - Select platform - MAC and download APP or PKG  file. But your IT team will maintain version and customised packaging. So consult with you IT team.

Log In to Answer

Associated Tags

accesspolicy

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

2,895

2895 Views

0 Likes

11

11 Comments

7/3/2020

at

11:55 AM

ZAPP intune deployment

Client Connector

Mk001

1,386

1386 Views

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

3,589

3589 Views

0 Likes

2 Comments

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

Assistance Needed for Zscaler Installation on MacBook
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000rtqag0AA/zcc-for-android-support-for-partner-login","lastmod":"2026-03-31T09:31:18.000Z","id":"0D5PJ00000rtqag0AA"} -->
## ZCC for Android support for partner login

- Source: https://community.zscaler.com/s/question/0D5PJ00000rtqag0AA/zcc-for-android-support-for-partner-login
- Type: Q&A
- Last activity: 2026-03-31T09:31:18.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZPA - Forwarding

avshch

(Customer) asked a question.

March 30, 2026 at 8:28 PM

ZCC for Android support for partner login

Hello,

Does anyone know if ZCC for Android OS can support partnet login?

Thanks,

ZPA - Forwarding

1 answer

116 views

GordonWright

(Customer)

3 months ago

I'm not sure but I'd be very surprised if it does today.

Log In to Answer

Associated Tags

No tags associated with this post!!

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

449

449 Views

0 Likes

6 Comments

5/5/2025

at

06:17 AM

wifi issue with zpa

ZPA - Forwarding

Athees

352

352 Views

0 Likes

3 Comments

7/25/2025

at

12:13 AM

A single server group vs two or more server groups

ZPA - Forwarding

jaicybersec

339

339 Views

0 Likes

3 Comments

7/30/2025

at

08:47 AM

Difference between SIPA and ZPA

ZPA - Forwarding

Dhananjay_Bhakte

944

944 Views

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

ZCC for Android support for partner login
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000tqU4T0AU/zcc-for-vdi-with-branch-connect-seamless-authentication","lastmod":"2026-05-24T15:55:38.000Z","id":"0D5PJ00000tqU4T0AU"} -->
## ZCC for VDI with Branch Connect - Seamless authentication

- Source: https://community.zscaler.com/s/question/0D5PJ00000tqU4T0AU/zcc-for-vdi-with-branch-connect-seamless-authentication
- Type: Q&A
- Last activity: 2026-05-24T15:55:38.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

j369

(Customer) asked a question.

April 30, 2026 at 9:11 PM

ZCC for VDI with Branch Connect - Seamless authentication

We are testing deployment of ZCC for VDI agent to our Microsoft RDS environment. We have the branch connector stood up and functioning properly. But are there any methods to make the ZCCVDI client automatically authenticate a user when they log in? We have this seamless SSO functioning on ZCC for our endpoints by passing the

login_hint SAML attribute

, but the options are way less fleshed out in the Branch and Cloud Connector portal than in the ZCC portal. For reference, we are using Entra as our IdP and the RDS servers are hybrid joined.

If we can't get the automatic sign in to work, we'll have to pivot to something different for these servers.

Client Connector

3 answers

427 views

avshch

likes this.

Allen Geiser

(Employee)

3 months ago

Yes — automatic or near-seamless sign-in for ZCC VDI in Microsoft RDS is possible, but it’s not as feature-rich or as explicitly exposed in the Branch/Cloud Connector UI as the standard endpoint ZCC portal flow.

Short answer

For ZCC for VDI in RDS, the usual approach is:

Use Entra ID as the SAML IdP

Preconfigure the VDI client with the correct cloud/domain parameters

Let authentication occur through the user’s existing browser/OS-backed Entra session

Optionally pass login_hint

to reduce or eliminate username prompts

So if your RDS hosts are hybrid joined and users already have a valid Entra-backed session, you can often get to silent or almost-silent auth.

However, it is not always identical to the seamless endpoint experience on full ZCC, and in some RDS/VDI cases the user may still get a sign-in prompt at least once.

Allen Geiser

(Employee)

3 months ago

The Long Answer

What typically works

1. Entra ID SAML with login hint

Your current thinking is aligned with the supported pattern.

If you already use login_hint successfully for standard ZCC endpoints, that same concept is the best-fit method for ZCCVDI as well. In the newer connector-based client settings, ZCC can be set to send the user identity to the IdP so Entra can pre-resolve the account and reduce prompts.

What this means practically:

Best case: user gets silent sign-in

Common case: Entra username is prefilled and auth is effectively one-click or invisible

Worst case: user still gets an interactive prompt if the session/browser context is not sufficient for silent auth

2. Rely on existing Entra session context

In RDS/VDI, seamless auth usually depends less on a big portal feature set and more on whether the session has enough identity context already available.

This generally works best when:

RDS servers are Hybrid Entra joined

Users are signing into Windows with identities that map cleanly to Entra

The session browser stack can leverage existing Entra sign-in state

Entra SSO/PRT/session conditions are healthy in that RDS design

If those conditions are met, ZCCVDI can often authenticate automatically or with minimal user interaction.

Important limitation

The main caveat - ZCC for VDI does not always have the same polished “endpoint-style” automatic auth behavior as standard ZCC on physical endpoints.

So if you’re expecting:

full endpoint-style silent auth,

all the same portal knobs,

or identical behavior across every RDS session model,

that expectation is usually where deployments hit friction.

The Branch/Cloud Connector workflow for VDI is more focused on:

provisioning the VDI client

steering traffic through the connector

enabling auth in shared/non-persistent environments

…rather than exposing every desktop-client SSO tuning option in the UI.

Based on leading practices:

For RDS specifically

Because this is Microsoft RDS and not a standard dedicated VDI desktop, you should validate these items:

Is the RDS user session getting enough Entra SSO context for silent web auth?

Are user profiles roaming consistently?

Is there any delay before the user shell/browser context becomes available?

Is ZCCVDI attempting auth before the user identity is fully available?

Are you using a non-persistent/shared-host model where the client must re-enroll or re-auth frequently?

If profiles are non-persistent or delayed, the behavior can look like “SSO doesn’t work,” when really the issue is timing and session context, not lack of feature support.

If silent auth does not work

If you cannot get true automatic sign-in working, that does not automatically mean the whole design is unworkable. Usually your fallback options are:

Option A: Accept near-seamless auth

If users only see:

a prefilled Entra account, or

an occasional sign-in once per session/profile lifecycle,

many customers accept that for RDS/VDI.

Option B: Adjust the RDS identity/session design

Sometimes the blocker is not ZCCVDI itself, but:

Entra session persistence

browser SSO behavior in RDS

profile management

timing of client startup versus user profile load

Option C: Re-evaluate where client auth should happen

If your use case absolutely requires fully silent, deterministic per-user auth on shared RDS hosts, and ZCCVDI is not meeting that bar in testing, then yes — that’s the point where many teams consider a different access pattern for those servers.

My engineering take

I would not pivot yet based only on the fact that the Branch/Cloud Connector portal has fewer SSO options exposed than the endpoint ZCC portal.

Instead, I would test this exact sequence:

Confirm ZCCVDI is deployed using the VDI-specific provisioning method

Verify Entra SAML is the active auth method

Enable/use

login_hint

Test with a clean hybrid-joined RDS host

Validate whether Edge/Entra silent SSO is actually working in the user session

Check whether profile loading timing is delaying or breaking auth

Test persistent vs non-persistent behavior separately

If after that the client still always requires manual auth, then I’d treat that as a real design limitation for your RDS use case.

Bottom line

Yes, there are methods to make ZCCVDI authenticate users automatically or semi-automatically in RDS, mainly through:

Entra ID SAML

existing session-based SSO

login_hint

proper VDI template/provisioning configuration

But it may not be as seamless or configurable as endpoint ZCC, and in some RDS designs it may still fall back to an interactive sign-in.

If you want, I can also give you a recommended validation checklist for:

Entra config

RDS host config

ZCCVDI install parameters

what to capture in logs

so you can quickly determine whether this is a configuration issue or a true product limitation.

1 like

avshch

(Customer)

2 months ago

@Allen Geiser​ We have a similar use case but with Citrix VDI and Okta as iDP.  Do you have any recommendations regarding ZCC for VDI if Okta's Desktop Single Sign-On used as per the following

https://help.okta.com/en-us/content/topics/directory/configuring_agentless_sso.htm

Thanks,

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

ZCC for VDI with Branch Connect - Seamless authentication
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000uOcH20AK/zpa-app-connector-oauth-20-token-enrollment","lastmod":"2026-07-15T05:32:29.000Z","id":"0D5PJ00000uOcH20AK"} -->
## ZPA App Connector - OAuth 2.0 Token enrollment

- Source: https://community.zscaler.com/s/question/0D5PJ00000uOcH20AK/zpa-app-connector-oauth-20-token-enrollment
- Type: Q&A
- Last activity: 2026-07-15T05:32:29.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZPA - Admin Portal

Danielo

(Partner) asked a question.

May 11, 2026 at 11:39 AM

ZPA App Connector - OAuth 2.0 Token enrollment

Hi,

In February this year, Zscaler released a new capability that allows App Connector enrollment to be performed in a more secure and simpler way compared to using the traditional

provision_key

file.

We like this new method; however, we would like to know whether it is possible to generate new OAuth tokens in some way, for example by restarting services or using another mechanism.

Rebooting the App Connector is not a solution, as it generates the same OAuth token.

https://help.zscaler.com/zpa/release-upgrade-summary-2026?applicable_category=private.zscaler.com&deployment_date=2026-02-17&id=1532690

ZPA - Admin Portal

1 answer

397 views

Sai Venkat

(Employee)

17 days ago

cat /etc/issue - to view the OAuth token

sudo systemctl stop zpa-connector

sudo find /opt/zscaler/var/ -type f -name * -delete

sudo systemctl start zpa-connector

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

7/5/2024

at

12:52 PM

Agent version report

ZPA - Admin Portal

Chandram81

322

322 Views

0 Likes

3 Comments

1/24/2025

at

11:09 AM

App connector Enrollment issue

ZPA - Admin Portal

Vinod

341

341 Views

0 Likes

3 Comments

8/26/2025

at

09:00 AM

Short name on ZPA on Macs don't work

ZPA - Admin Portal

Diode

412

412 Views

0 Likes

3 Comments

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

ZPA App Connector - OAuth 2.0 Token enrollment
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000uWWvy0AG/microsoft-surface-laptop-with-windows-show-performance-degradation-via-lan","lastmod":"2026-05-15T15:59:32.000Z","id":"0D5PJ00000uWWvy0AG"} -->
## Microsoft Surface Laptop with Windows show performance degradation via LAN

- Source: https://community.zscaler.com/s/question/0D5PJ00000uWWvy0AG/microsoft-surface-laptop-with-windows-show-performance-degradation-via-lan
- Type: Q&A
- Last activity: 2026-05-15T15:59:32.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZUG - India

mayank.pratapsingh

(Partner) asked a question.

May 12, 2026 at 8:32 PM

Microsoft Surface Laptop with Windows show performance degradation via LAN

The Symptom:

Wired connections via the Surface Dock were dropping below 100 Mbps when Zscaler was enabled, while wireless connections remained relatively unaffected.

Wireless Network:

Speed without ZCC: 250 Mbps; Speed with ZCC: 225 Mbps.

Wired Network:

Speed without ZCC: 900 Mbps; Speed with ZCC: 60 Mbps (ZCC versions 4.8.0.140 and 4.8.0.115)

Update:

ZCC version was updated to 4.8.0.172 and we are now seeing improvements in overall performance.

Performance:

Wired: 353 Mbps (ZCC version 4.8.0.172) with MTU size 1350. Wireless remains unaffected.

Technical Note:

ZCC version 4.8.0.140 has a fragmentation bug which can be resolved by updating to the next version. It is important to note that everything works fine on Mac devices, as they are smart enough to dynamically maintain the MTU sizing and do not experience TCP retransmission.

Tried URB and buffers changes with offloads.

Any one would like to help in understanding if this is related to LAN adapter or any other endpoints setting can be changed.

ZUG - India

7 answers

284 views

GordonWright

(Customer)

3 months ago

We had some weird issue like this many years but can't remember all the details.

I seem to remember us turning off or turning on this feature in the Forwarding Profile:

1 like

mayank.pratapsingh

(Partner)

3 months ago

We did try disabling it but no major difference, do you have any other details which could help us.

1 like

GordonWright

(Customer)

3 months ago

This was suggested in another thread:

Workaround

: Disabling the Intel NPU from Device Manager restores stable Zscaler (ZPA) connectivity on affected systems.

1 like

mayank.pratapsingh

(Partner)

3 months ago

it is not for ZPA, it is for slowness

1 like

GordonWright

(Customer)

2 months ago

The fix didn't appear to be ZPA specific.

jatinsangwan

(Customer)

3 months ago

We are also facing the same issue. And when i checked the logs on Zscaler portal it was showing high latency when the traffic was going from the Zscaler to 365 servers.

1 like

GordonWright

(Customer)

2 months ago

We made a change with Large Send Offload (LSO) settings.

Can't remember if we turned them on or off.

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

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

Microsoft Surface Laptop with Windows show performance degradation via LAN
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000uatb30AA/windows-laptop-dropping-performance-and-have-slowness-but-mac-doesnt","lastmod":"2026-05-26T16:02:19.000Z","id":"0D5PJ00000uatb30AA"} -->
## Windows laptop dropping performance and have slowness but Mac doesn't

- Source: https://community.zscaler.com/s/question/0D5PJ00000uatb30AA/windows-laptop-dropping-performance-and-have-slowness-but-mac-doesnt
- Type: Q&A
- Last activity: 2026-05-26T16:02:19.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

mayank.pratapsingh

(Partner) asked a question.

May 13, 2026 at 10:40 PM

Windows laptop dropping performance and have slowness but Mac doesn't

Can any one have any idea?

We are facing slowness on all the windows devices.

We tried with Dell, microsoft surface but no improvement in the slowness.

+ We tried with tunnel 1.0 no improvment

+currently using tunnel 2.0 DTLS and tried changing to TLS however no improvment

+ the only change in mtu does improve but still performance is degraded.

+Ticket is already raised but we are still unable to isolate the issue.

Can someone shed some light on this and share some experience what can fix the problem.

Client Connector

1 answer

297 views

Raj909

(Customer)

2 months ago

Please elaborate on what exactly is slow.  ZIA or ZPA, Road Warrior or VPN tunnel, internet browsing, file transfer, etc.

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

2,895

2895 Views

0 Likes

11

11 Comments

7/3/2020

at

11:55 AM

ZAPP intune deployment

Client Connector

Mk001

1,385

1385 Views

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

3,578

3578 Views

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

Windows laptop dropping performance and have slowness but Mac doesn't
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000utSHz0AM/zcc-for-vdi-citrix-browserbased-users-auth-with-idp-okta-via-kerberos","lastmod":"2026-05-28T15:17:38.000Z","id":"0D5PJ00000utSHz0AM"} -->
## ZCC for VDI (Citrix) browser-based users auth with iDP (Okta) via Kerberos

- Source: https://community.zscaler.com/s/question/0D5PJ00000utSHz0AM/zcc-for-vdi-citrix-browserbased-users-auth-with-idp-okta-via-kerberos
- Type: Q&A
- Last activity: 2026-05-28T15:17:38.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

avshch

(Customer) asked a question.

Edited May 27, 2026 at 1:44 AM

ZCC for VDI (Citrix) browser-based users auth with iDP (Okta) via Kerberos

We use Okta as iDP for users authentication and recently configured Okta DSSO (https://help.okta.com/en-us/content/topics/directory/configuring_agentless_sso.htm) for Citrix VDI (on-prem). Could ZCC for VDI rely on browser-based authentication with iDP while browser uses Kerberos for user auth via DSSO? Another question if ZCC for VDI uses external browser for user's authentication?

Client Connector

226 views

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

3,570

3570 Views

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

ZCC for VDI (Citrix) browser-based users auth with iDP (Okta) via Kerberos
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000vNDk00AG/zcc-for-vdi-webbrowser-use-for-users-authentication","lastmod":"2026-06-24T14:31:50.000Z","id":"0D5PJ00000vNDk00AG"} -->
## ZCC for VDI web-browser use for user's authentication

- Source: https://community.zscaler.com/s/question/0D5PJ00000vNDk00AG/zcc-for-vdi-webbrowser-use-for-users-authentication
- Type: Q&A
- Last activity: 2026-06-24T14:31:50.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

avshch

(Customer) asked a question.

May 27, 2026 at 1:37 AM

ZCC for VDI web-browser use for user's authentication

Does ZCC for VDI use external browser for users authentication?

Client Connector

3 answers

539 views

M_Hariharan

(Employee)

a month ago

Yes—by default Zscaler Client Connector for VDI can launch the system’s default web browser for user authentication, but you can configure it to use an embedded browser to avoid opening an external browser.

To prevent external browser use, set USEEMBEDDEDBROWSER=1 in the MSI parameters; this enables the embedded browser so users authenticate within the app instead of an external browser.

Please refer to this article for more details:

https://help.zscaler.com/cloud-branch-connector/customizing-zscaler-client-connector-vdi-install-options-msi

1 like

avshch

(Customer)

a month ago

@Hariharan M​ Thank you. We've discovered Okta DSSO (

https://help.okta.com/en-us/content/topics/directory/configuring_agentless_sso.htm

does not work with embedded browsers. So using external browser would be ideal. Thanks,

1 like

M_Hariharan

(Employee)

Edited June 19, 2026 at 2:00 AM

Thanks for the update.

By default Zscaler Client Connector for VDI can launch the system’s default web browser for user authentication

Customize your ZCC deployment by using any of the steps as mentioned in the article

https://help.zscaler.com/cloud-branch-connector/customizing-zscaler-client-connector-vdi-install-options-msi

Make sure the ZCC parameter USEEMBEDDEDBROWSER=0

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

2,896

2896 Views

0 Likes

11

11 Comments

7/3/2020

at

11:55 AM

ZAPP intune deployment

Client Connector

Mk001

1,386

1386 Views

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

3,601

3601 Views

0 Likes

2 Comments

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

ZCC for VDI web-browser use for user's authentication
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000vRG6v0AG/how-to-get-access-to-public-previews-for-zscaler-client-connector","lastmod":"2026-06-05T03:00:35.000Z","id":"0D5PJ00000vRG6v0AG"} -->
## How to get access to Public Previews for Zscaler Client Connector

- Source: https://community.zscaler.com/s/question/0D5PJ00000vRG6v0AG/how-to-get-access-to-public-previews-for-zscaler-client-connector
- Type: Q&A
- Last activity: 2026-06-05T03:00:35.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

charles.choy

(Customer) asked a question.

May 28, 2026 at 3:50 AM

How to get access to Public Previews for Zscaler Client Connector

Hi,

We received notification from Zscaler that new version of Zscaler Client Connector for windows and macOS is available via Public Previews in Zenith Community. However, I do not see that tab in the community (even after logging in). Where can I find it? or what is the process to register for it?

Client Connector

4 answers

241 views

Ben_Garrison

(Employee)

2 months ago

Hey Charles,

I had your issue fixed. Appears they may have been two different accounts with the same email address and one was not identified as a customer/partner account. You should be able to see the Public Preview link now!

May need to logout and login

1 like

charles.choy

(Customer)

2 months ago

Hi Ben_Garrison,

Thanks for your help, I can now see the Public Previews.

In fact, I was originally guided to this site because Zscaler had recently made available  Zscaler Connector Beta for Windows and macOS (see pic). However, inside Public Previews, I can only see ios and Android beta updates. Can you check and tell me if they are really not there, or should I be requesting access to those specifically? Thanks.

Charles.

charles.choy

(Customer)

2 months ago

Hi Ben_Garrison,

Thanks for your help.

On the original problem, is there something we can add to the

Getting Started

for this community

that customers can check if they are correctly associated with right account to get access done correctly in the beginning? Thanks.

mahantr

(Customer)

2 months ago

Hello, I'm having the same issue. Can you resolve for me?

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

2,896

2896 Views

0 Likes

11

11 Comments

7/3/2020

at

11:55 AM

ZAPP intune deployment

Client Connector

Mk001

1,386

1386 Views

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

3,601

3601 Views

0 Likes

2 Comments

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

How to get access to Public Previews for Zscaler Client Connector
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000w0O1Z0AU/zscaler-client-connector-audit","lastmod":"2026-06-27T12:09:08.000Z","id":"0D5PJ00000w0O1Z0AU"} -->
## Zscaler client connector Audit

- Source: https://community.zscaler.com/s/question/0D5PJ00000w0O1Z0AU/zscaler-client-connector-audit
- Type: Q&A
- Last activity: 2026-06-27T12:09:08.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

skywalker

(Partner) asked a question.

June 5, 2026 at 4:42 PM

Zscaler client connector Audit

hello , I am looking for some best practices guide for Zscaler client connector configuration .( ZCC Portal) . We are using Tunnel2.0 but in general are there some guidelines or best practices to be followed ?

Client Connector

1 answer

537 views

avshch

likes this.

Ecruz lZ30030

(Employee)

a month ago

Hello @Anupam Gaur​ ,

Hope you are doing well!

It may depend on your environment and requirements, but below are some general recommendations to consider regarding Z-Tunnel 2.0 setup:

Z‑Tunnel 2.0 intercepts packets and sends them directly on the data channel:

It does not act as a proxy, so App Profile PAC rules are not evaluated for that traffic by default. When you need domain-based decisions (for example, send certain domains DIRECT or to a different proxy), you must first route those web requests to the ZCC listening proxy. The way to do that in Z‑Tunnel 2.0 is via the Forwarding Profile PAC returning the Z-Tunnel 2.0 bypass micro (PROXY ${ZAPP_TUNNEL2_BYPASS}), which directs the flow to the local listener so the App Profile PAC can then apply the desired action (often DIRECT).

You need a Forwarding Profile PAC in Z-Tunnel 2.0 for the Domain-based bypasses for non-standard ports or traffic that is not automatically redirected to the listener require entries in both the Forwarding Profile PAC (to steer to the listener) and the App Profile PAC (to send DIRECT or to the desired destination).

Prefer VPN Gateway Bypasses for highest priority/direct routing:

If you plan to add hostnames make sure they do not resolve to Dynamic IP Addresses. VPN Gateway Bypass works by resolving an FQDN to IPs once and then bypassing those IPs; when the FQDN later resolves to different IPs, traffic won’t be bypassed reliably. This leads to intermittent behavior and unintended side effects. This is because Zscaler Client Connector resolves each FQDN you add to VPN Gateway Bypass and creates bypass filters for the IPs returned at tunnel start or policy refresh. If the FQDN later resolves to new IPs, those new IPs are not covered, so traffic still goes through Zscaler instead of being bypassed, causing inconsistent results.

Z-Tunnel 2.0 and GRE:

Avoid sending Z-Tunnel 2.0 through GRE in case you have or plans to configure GRE tunnels. This is because it adds double encapsulation and forces sub‑optimal processing at the Zscaler data center, which can degrade performance and even cause fragmentation.

DTLS vs TLS:

By default Z-Tunnel 2.0 uses DTLS as transport method to establish the connection. DTLS generally delivers better performance (lower latency/overhead), however, in case the network degrades UDP due to some ISPs or firewalls throttle/block UDP 443, in which case TLS will be more reliable. This can be configured in the Forwarding Profile.

Process-Based & IP-Based Bypasses:

You can also apply the built-in and also custom IP-Based Application bypasses for Tunnel 2.0 for specific services and enable built-in Process-Based bypass for services like ZOOM and Teams and also you can create you custom process-based bypass.

Belo aresome links for reference regarding the Best Practices for Deploying Z-Tunnel 2.0:

https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0

https://help.zscaler.com/zscaler-client-connector/best-practices-adding-bypasses-z-tunnel-2.0

https://help.zscaler.com/zscaler-client-connector/adding-ip-based-applications-bypass-traffic

https://help.zscaler.com/zscaler-client-connector/adding-process-based-applications-bypass-traffic

In case you have any questions, please let me know.

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

3,572

3572 Views

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

Zscaler client connector Audit
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000wZZ3L0AW/user-has-to-input-password-everytime","lastmod":"2026-06-29T15:44:17.000Z","id":"0D5PJ00000wZZ3L0AW"} -->
## User has to input password everytime

- Source: https://community.zscaler.com/s/question/0D5PJ00000wZZ3L0AW/user-has-to-input-password-everytime
- Type: Q&A
- Last activity: 2026-06-29T15:44:17.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

bruno.vaz

(Customer) asked a question.

June 15, 2026 at 11:57 AM

User has to input password everytime

Hi Team,

We have a user that complains Zscaler Client Connector doesn't store his password.

Everyday he's being requested to input his full credentials and never received the "remember password" popup.

We reinstalled the app, reset his password and the popup never shows.

Zscaler Client Connector: App Version: 4.8.0.115

Windows 11 machine.

All other users in our corporation have no issues like this.

We do have OKTA SSO and it works with everyone including this user but he's not able to "store" his credentials like all other users.

Can someone please assist?

Client Connector

4 answers

439 views

Jobejara07Z

(Employee)

a month ago

You may try the following:

-Confirm same tenant policy: user has the same App Profile and Windows Platform Settings as peers; ensure WebView2 auth is enabled; optionally enable “SSO using Windows Primary Account.”

-Fix WebView2 on the PC: install/update Microsoft Edge WebView2 Runtime (≥ 103.0.1264.42); allow

delivery.mp.microsoft.com

cdp.microsoft.com

, and MicrosoftEdgeWebView2Setup.exe in proxy/AV.

-Reset auth state: in ZCC log out and fully exit; delete the user’s ZCC/WebView2 caches; relaunch ZCC, sign in once, and click “Stay/Keep me signed in.”

-If proxy interferes: temporarily disable “Allow WebView2 to follow System Proxy” or permit the CDN domains above; retry sign-in.

-Fallback: enable browser-based authentication; if persistence works, the issue is WebView2 on this PC—keep browser auth or fix WebView2.

-Last resort: reinstall ZCC with correct cloudName/userDomain and avoid hiding first-login UI; validate persistence after reboot and next day.

Hope this helps!

Ecruz lZ30030

(Employee)

25 days ago

@Bruno Vaz​ ,

I assume you have configured SSO in your IDP, right?

We may need to check why SSO is not triggering after the user logs into the device, that should happened seamlessly without user interaction every time the user logs into the device.

I would suggest you to open a support case and provide ZCC debug logs along with the timestamps for review.

bruno.vaz

(Customer)

22 days ago

Thank you both for your help.

We do use SSO for all our users and this one of the few that's having this issue.

I haven't reinstalled Microsoft Edge WebView2 Runtime but we did try reinstalling the Zscaler client and logout, unfortunately to no avail. Will update once I reinstall Webview2.

Thanks again.

avshch

(Customer)

Edited June 27, 2026 at 12:29 PM

You can try using Okta's agentless Desktop Single Sign-on (

https://help.okta.com/en-us/content/topics/directory/configuring_agentless_sso.htm

), assuming ZCC's use of

https://help.zscaler.com/zscaler-client-connector/about-machine-tunnels

, Windows machine is MS AD Domain-joined.

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

2,881

2881 Views

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

701

701 Views

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

User has to input password everytime
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000xjb8L0AQ/zcc-idp-traffic-exempt-from-forwarding-to-zia","lastmod":"2026-07-15T00:01:15.000Z","id":"0D5PJ00000xjb8L0AQ"} -->
## ZCC idp traffic exempt from forwarding to ZIA

- Source: https://community.zscaler.com/s/question/0D5PJ00000xjb8L0AQ/zcc-idp-traffic-exempt-from-forwarding-to-zia
- Type: Q&A
- Last activity: 2026-07-15T00:01:15.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

avshch

(Customer) asked a question.

July 2, 2026 at 8:51 PM

ZCC idp traffic exempt from forwarding to ZIA

Hello,

Could you please advice on how to exempt traffic to iDP (Okta) from being forwarded to ZIA while ZCC is connected?

ZCC for Chromebooks v4.2.0.137 connected with Tunnel 2.0 with DTLS Protocol.

Thanks,

Client Connector

3 answers

203 views

zywong

(Partner)

9 days ago

Hi! if i may just point you to the documentations from Zscaler and Okta which you need to configure in your PAC file.

Parameters to configure:

https://help.zscaler.com/zia/saml-scim-configuration-guide-okta#authentication-exemptions-list

Additional domains to add:

https://help.okta.com/oie/en-us/Content/Topics/Security/ip-address-allow-listing.htm

(You might want to focus on the table row "Required Okta domains") it should help to completely bypass your Okta traffic completely from your Zscaler Client Connector.

Hope it helps!

BlueSocks

(Partner)

5 days ago

Typically to bypass all traffic you put a bypass in the pac file. There are other considerations though, what is your full use case?

Ecruz lZ30030

(Employee)

4 days ago

Hello @Alex Shchukin​ ,

To apply the appropriate PAC file bypass logic when using Z-Tunnel 2.0, it is suggested to configure the relevant OKTA FQDN bypass entries in both of the following locations:

Forwarding Profile PAC File

App Profile PAC File

The rationale for implementing the bypass in both profiles is to provide a fallback mechanism for web traffic traversing Z-Tunnel 2.0 that still requires PAC file evaluation. By defining the same OKTA-related destinations in both locations, the client can apply the correct forwarding decision depending on how the traffic is classified and processed.

From a technical standpoint:

The Forwarding Profile PAC File should return

ZAPP_TUNNEL2_BYPASS

for the listed OKTA and certificate-related destinations.

The App Profile PAC File should return

DIRECT

for the same destinations.

This dual configuration helps ensure the traffic is excluded appropriately from the Z-Tunnel 2.0 forwarding path when necessary, while still allowing direct connectivity for app-profile PAC-evaluated flows.

Please find below the recommended PAC file logic for each profile.

Forwarding Profile PAC File:

if (

shExpMatch(host, "*.

okta.com

") ||

shExpMatch(host, "*.

mtls.okta.com

") ||

shExpMatch(host, "*.

oktapreview.com

") ||

shExpMatch(host, "*.

mtls.oktapreview.com

") ||

shExpMatch(host, "*.

oktacdn.com

") ||

shExpMatch(host, "*.

okta-emea.com

") ||

shExpMatch(host, "*.

mtls.okta-emea.com

") ||

shExpMatch(host, "*.

kerberos.okta.com

") ||

shExpMatch(host, "*.

kerberos.okta-emea.com

") ||

shExpMatch(host, "*.

kerberos.oktapreview.com

") ||

shExpMatch(host, "*.

okta-gov.com

") ||

shExpMatch(host, "*.

mtls.okta-gov.com

") ||

shExpMatch(host, "*.

okta.mil

") ||

shExpMatch(host, "*.

mtls.okta.mil

") ||

shExpMatch(host, "*.

awsglobalaccelerator.com

") ||

host == "

okta-featureflag-edge.azureedge.net

" ||

host == "

ocsp.digicert.com

" ||

host == "

crl3.digicert.com

" ||

host == "

crl4.digicert.com

) {

return "ZAPP_TUNNEL2_BYPASS";

App Profile PAC File:

if (

shExpMatch(host, "*.

okta.com

") ||

shExpMatch(host, "*.

mtls.okta.com

") ||

shExpMatch(host, "*.

oktapreview.com

") ||

shExpMatch(host, "*.

mtls.oktapreview.com

") ||

shExpMatch(host, "*.

oktacdn.com

") ||

shExpMatch(host, "*.

okta-emea.com

") ||

shExpMatch(host, "*.

mtls.okta-emea.com

") ||

shExpMatch(host, "*.

kerberos.okta.com

") ||

shExpMatch(host, "*.

kerberos.okta-emea.com

") ||

shExpMatch(host, "*.

kerberos.oktapreview.com

") ||

shExpMatch(host, "*.

okta-gov.com

") ||

shExpMatch(host, "*.

mtls.okta-gov.com

") ||

shExpMatch(host, "*.

okta.mil

") ||

shExpMatch(host, "*.

mtls.okta.mil

") ||

shExpMatch(host, "*.

awsglobalaccelerator.com

") ||

host == "

okta-featureflag-edge.azureedge.net

" ||

host == "

ocsp.digicert.com

" ||

host == "

crl3.digicert.com

" ||

host == "

crl4.digicert.com

) {

return "DIRECT";

If you questions, let me know.

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

ZCC idp traffic exempt from forwarding to ZIA
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000yvj990AA/ondemand-zcczpa-for-3rd-party-vendors","lastmod":"2026-07-28T17:05:17.000Z","id":"0D5PJ00000yvj990AA"} -->
## On-demand ZCC/ZPA for 3rd Party Vendors

- Source: https://community.zscaler.com/s/question/0D5PJ00000yvj990AA/ondemand-zcczpa-for-3rd-party-vendors
- Type: Q&A
- Last activity: 2026-07-28T17:05:17.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZPA - Forwarding

wbocash

(Customer) asked a question.

July 21, 2026 at 7:41 PM

On-demand ZCC/ZPA for 3rd Party Vendors

Looking for a way to have our 3rd Party vendors use ZCC/ZPA on-demand similar to how they use our VPN client. So, they can open/start it only when needed and doesn't run persistently. Then easily turn it off. PRA is not suitable due to protocol limitations.

ZPA - Forwarding

10 views

Log In to Answer

Associated Tags

No tags associated with this post!!

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

456

456 Views

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

345

345 Views

0 Likes

3 Comments

7/30/2025

at

08:47 AM

Difference between SIPA and ZPA

ZPA - Forwarding

Dhananjay_Bhakte

972

972 Views

0 Likes

8 Comments

4/23/2026

at

05:00 PM

App Segments populating in Edge Appliance

ZPA - Forwarding

BlueSocks

158

158 Views

0 Likes

1 Comment

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

On-demand ZCC/ZPA for 3rd Party Vendors
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000zAVVt0AO/zcc-v-48-macos-golden-gate-testing","lastmod":"2026-07-28T17:04:18.000Z","id":"0D5PJ00000zAVVt0AO"} -->
## ZCC v 4.8 + MacOS Golden Gate testing

- Source: https://community.zscaler.com/s/question/0D5PJ00000zAVVt0AO/zcc-v-48-macos-golden-gate-testing
- Type: Q&A
- Last activity: 2026-07-28T17:04:18.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

Akanksha.bakshi

(Customer) asked a question.

July 24, 2026 at 9:53 PM

ZCC v 4.8 + MacOS Golden Gate testing

Would like to know best practices for ZCC and Golden Gate testing. Beta testing reveals ZCC connection instability for some users. Are there recommended ZCC versions to test with.

Client Connector

8 views

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

2,889

2889 Views

0 Likes

11

11 Comments

7/3/2020

at

11:55 AM

ZAPP intune deployment

Client Connector

Mk001

1,381

1381 Views

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

3,577

3577 Views

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

ZCC v 4.8 + MacOS Golden Gate testing
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000zXTyA0AW/zscaler-and-type2-imprivata-integration","lastmod":"2026-07-31T00:52:13.000Z","id":"0D5PJ00000zXTyA0AW"} -->
## Zscaler and type2 imprivata integration

- Source: https://community.zscaler.com/s/question/0D5PJ00000zXTyA0AW/zscaler-and-type2-imprivata-integration
- Type: Q&A
- Last activity: 2026-07-31T00:52:13.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

rohit.patil1

(Partner) asked a question.

47m ago

Zscaler and type2 imprivata integration

Has anyone integrated zscaler with type2 imprivata.

I have tried with custom parameters, when user1 taps to login zscaler doesn't automatically login to user1 , instead it tries to login with local account/generic id used to login local dekstop.

Client Connector

2 views

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

2,895

2895 Views

0 Likes

11

11 Comments

7/3/2020

at

11:55 AM

ZAPP intune deployment

Client Connector

Mk001

1,385

1385 Views

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

3,578

3578 Views

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

Zscaler and type2 imprivata integration
<!-- /ZS-POST -->
