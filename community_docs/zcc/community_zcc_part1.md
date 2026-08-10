# Zscaler Zenith Community — ZCC — Zscaler Client Connector (part 1)

Source: https://community.zscaler.com
Generated: 2026-08-10 03:10 UTC
Posts in this file: 655

> これはユーザー投稿のコミュニティフォーラムの内容であり、Zscaler の公式ドキュメントではない。

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eviNaCAI/unusual-ip-connections-from-zscalertunnel-process","lastmod":"2023-05-31T08:50:09.000Z","id":"0D54u00009eviNaCAI"} -->
## Unusual IP connections from zscalertunnel process

- Source: https://community.zscaler.com/s/question/0D54u00009eviNaCAI/unusual-ip-connections-from-zscalertunnel-process
- Type: Q&A
- Posted: 2020-07-07T15:00:59.000Z
- Last activity: 2023-05-31T08:50:09.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

From looking at my EDR tool logs I have noticed the zscalertunnel process (on macOS) is connecting to some unusual IPs in other countries. Could anyone explain why this might be happening?

My expectation is that the zscalertunnel process should make connections to Zscaler IP ranges (e.g. those at [https://ips.zscaler.net](https://ips.zscaler.net)) and nothing else. Are there conditions when this is not true?

Thanks,
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eviNbCAI/zapp-8-network-error-when-users-log-in-on-windows-10","lastmod":"2023-07-06T13:11:39.000Z","id":"0D54u00009eviNbCAI"} -->
## Z-App -8 Network Error when users log in on Windows 10

- Source: https://community.zscaler.com/s/question/0D54u00009eviNbCAI/zapp-8-network-error-when-users-log-in-on-windows-10
- Type: Q&A
- Posted: 2020-07-07T04:05:44.000Z
- Last activity: 2023-07-06T13:11:39.000Z
- Answers: 11
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello, has anyone experienced the networking error -8 when the app runs on certain versions of windows 10 Enterprise? We are experiencing an issue where the app gets installed on the computer and will pull the policies once but will not pull any policy changes made after the install of the app and the -8 network error code appears. I’ve created a ticket with Zscaler, but none of the techs have given anything that resolves the error on multiple computers. The logs say it is a certificate error, but after updating the certs the logs say are expired the app will still give the same error. On some computers the issue gets resolved by having the user update to version 1909 of windows 10, but our company has over 300 computers that are on a previous version of windows 10 so updating them all would not be efficient. Does anyone have any suggestions on what to try? Is there a known issue with the Z-app running on windows 10 computers that are not on windows 10 version 1909?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eviNdCAI/zapp-intune-deployment","lastmod":"2023-07-06T11:46:36.000Z","id":"0D54u00009eviNdCAI"} -->
## ZAPP intune deployment

- Source: https://community.zscaler.com/s/question/0D54u00009eviNdCAI/zapp-intune-deployment
- Type: Q&A
- Posted: 2020-07-03T11:55:35.000Z
- Last activity: 2023-07-06T11:46:36.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello, I am currently trying to deploy ZAPP to our Windows 10 environment using intunes. We are fully cloud environment and use Azure AD for SSO.

I followed following video to push ZAPP to my WIndows users. I can see app is deployed perfectly fine. But only issue I get is it does not do SSO automatically. You can the screen where you have to put your username and then it downloads all the configuration.

I was wondering if there is a way where I can do fully automatic deployment so user does not have to login etc.

Regards

Tutorial URL: [Z App deployment with Microsoft Intune](https://community.zscaler.com/t/z-app-deployment-with-microsoft-intune/4606)

[[image.]image.1596×1225 212 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O5AvAAK)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evimVCAQ/change-of-username-while-using-zscaler","lastmod":"2025-08-22T07:27:13.000Z","id":"0D54u00009evimVCAQ"} -->
## Change of Username While Using Zscaler

- Source: https://community.zscaler.com/s/question/0D54u00009evimVCAQ/change-of-username-while-using-zscaler
- Type: Q&A
- Posted: 2021-11-12T11:44:57.000Z
- Last activity: 2025-08-22T07:27:13.000Z
- Answers: 9
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I come across an issue where the user has just joined the company.
 Initially he login zscaler using [user1@abcd.com](mailto:user1@abcd.com).
 The next day he requested to change his username to [user2@abcd.com](mailto:user2@abcd.com) due to misspelling.

Now, when I checked on the user ZCC, its showing [user2@abcd.com](mailto:user2@abcd.com) as the login ID. ZIA works, but not ZPA. I took at look at the Zscaler Mobile Portal and noticed 2 entries with the same machine hostname. The only difference is the ‘Last Seen Connected to ZIA’ date.

Now, what is the best method to solve this? Should the earlier device entry be removed? Should I just logout from the ZCC and re-login?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evimYCAQ/zcc-sso-on-vdi-instance","lastmod":"2025-01-29T16:11:43.000Z","id":"0D54u00009evimYCAQ"} -->
## ZCC SSO on VDI instance

- Source: https://community.zscaler.com/s/question/0D54u00009evimYCAQ/zcc-sso-on-vdi-instance
- Type: Q&A
- Posted: 2021-11-09T20:44:23.000Z
- Last activity: 2025-01-29T16:11:43.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Documentation under Supporting Citrix states
 "If you are using non-persistent VDIs where users get fresh desktops each time they connect, they will need to re-enroll each time. "

But under " Best Practices for Using Zscaler Client Connector in a VDI" it states
 The following conditions apply when using Zscaler Client Connector (formerly Zscaler App or Z App) in a virtual desktop infrastructure (VDI):

- Zscaler recommends that you don’t log in to Zscaler Client Connector on the master VM.
- To use the [STRICTENFORCEMENT install option](https://help.zscaler.com/z-app/customizing-zscaler-app-install-options-msi#se), you must have the [HIDEAPPUIONLAUNCH install option](https://help.zscaler.com/z-app/customizing-zscaler-app-install-options-msi#hideapp) disabled. This allows Zscaler Client Connector to remind users to enroll with Zscaler Client Connector before accessing the internet.
- To use the [USERDOMAIN install option](https://help.zscaler.com/z-app/customizing-zscaler-app-install-options-msi#ud), you must use Integrated Windows Authentication (IWA).

Does this mean that a user can be automatically logged into ZCC on a non-persistent VDI using strictenforcement and userdomain integrated with IWA?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evkqUCAQ/internet-speed-is-very-slow-when-zcc-connected-in-home-network","lastmod":"2023-11-21T12:58:02.000Z","id":"0D54u00009evkqUCAQ"} -->
## Internet Speed is Very Slow when ZCC Connected in Home Network

- Source: https://community.zscaler.com/s/question/0D54u00009evkqUCAQ/internet-speed-is-very-slow-when-zcc-connected-in-home-network
- Type: Q&A
- Posted: 2022-07-14T04:12:57.000Z
- Last activity: 2023-11-21T12:58:02.000Z
- Answers: 36
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

- We are using Tunnel Ver 1.0
- ZCC Connected Speed drop from 100Mbps to 1Mbps
- System Restarted Without Zscaler No issue
- System Restart with Zscaler works fine 10-15 min then Speen goes to 1 Mbps
- Try to identify using ZDX by nothing happen
- Try with help of Zscaler TAC no resolution till now
- System IPv6 disbaled If anyone has any idea please share we will try to do that. Regards Hriday
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evl5WCAQ/how-zscaler-and-anyconnect-coexist","lastmod":"2023-05-31T08:44:39.000Z","id":"0D54u00009evl5WCAQ"} -->
## How Zscaler and Anyconnect coexist

- Source: https://community.zscaler.com/s/question/0D54u00009evl5WCAQ/how-zscaler-and-anyconnect-coexist
- Type: Q&A
- Posted: 2022-09-08T02:04:28.000Z
- Last activity: 2023-05-31T08:44:39.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I’m new to Zscaler and I would like to better understand how ZCC interoperates with Anyconnect. I’d like to understand the order of things, i.e. are applications directed to ZCC and then the anyconnect VPN or vice versa. The packet captures I have collected from the tunnel adapters and via the packet capture feature in ZCC just aren’t conclusive. Understanding how packets flow would be really helpful.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evlrcCAA/compare-ezagent-and-zcc-when-to-use-which","lastmod":"2023-05-31T09:25:22.000Z","id":"0D54u00009evlrcCAA"} -->
## Compare ezAgent and ZCC - when to use which?

- Source: https://community.zscaler.com/s/question/0D54u00009evlrcCAA/compare-ezagent-and-zcc-when-to-use-which
- Type: Q&A
- Posted: 2021-01-28T15:42:25.000Z
- Last activity: 2023-05-31T09:25:22.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are evaluating Zscaler and are thinking through our various use cases. Once area that is still fuzzy for me is when/where to use ezAgent for proxy management vs Zscaler Client Connector.

Is there a write-up somewhere that compares these two?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evlrfCAA/tunneling-zscaler-internal-traffic-in-a-no-default-route-environment","lastmod":"2023-05-31T09:08:24.000Z","id":"0D54u00009evlrfCAA"} -->
## Tunneling Zscaler internal traffic in a "No Default Route" environment

- Source: https://community.zscaler.com/s/question/0D54u00009evlrfCAA/tunneling-zscaler-internal-traffic-in-a-no-default-route-environment
- Type: Q&A
- Posted: 2021-01-27T15:29:02.000Z
- Last activity: 2023-05-31T09:08:24.000Z
- Answers: 0
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Zscaler Client Connector needs to be able to reach the Zscaler Cloud service to update the application, policy, etc. This can be a challenge in an environment without a Default-Route out to the Internet.

A new ZCC feature allows you to configure an explicit proxy (such as ZIA) for this traffic.

This video walks you through how to set this up.
 [Video - Tunnel ZCC Traffic](https://drive.google.com/file/d/1nsAmDMPNEMb_MJTVIrfNUefzUEkSeJzK/view?usp=sharing)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmSYCAY/zapp-ssl-inspection-on-android","lastmod":"2025-09-03T12:05:54.000Z","id":"0D54u00009evmSYCAY"} -->
## Z-APP SSL inspection on Android

- Source: https://community.zscaler.com/s/question/0D54u00009evmSYCAY/zapp-ssl-inspection-on-android
- Type: Q&A
- Posted: 2020-08-19T14:37:05.000Z
- Last activity: 2025-09-03T12:05:54.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

When we have SSL inspection enabled for Android we find it seems to break most apps on the Android mobile device. The browsers seem to be fine as we push the Zscaler CA from our MDM and we have the Z-APP AKA zscaler client connector checked to install the Zscaler CA. Still many apps lose internet connectivity when Android SSL inspection is enabled. Example apps Garmin Connect, Pandora, Amazon Music and many others. I have confirmed manually the zscaler cert is in the User store and trusted to network and apps. Is there a fix for this or is this issue just due to cert pinning of the Android apps themself? I am wondering if there is possibly a fix to this? We cannot possibly disable ssl inspection for these domain as that would impact our ability to inspect SSL on our windows devices etc. THe only fix I have found is disable SSL inspection for Android in the Z-app settings.

Additionally we tested with ChromeOS and it has this same issue I presume due to it’s android apps.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmSZCAY/how-to-get-a-new-ip-address","lastmod":"2023-05-31T08:49:49.000Z","id":"0D54u00009evmSZCAY"} -->
## How to get a new IP address?

- Source: https://community.zscaler.com/s/question/0D54u00009evmSZCAY/how-to-get-a-new-ip-address
- Type: Q&A
- Posted: 2020-08-18T20:53:28.000Z
- Last activity: 2023-05-31T08:49:49.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello All, (Reposting since no replies on the other group)

Our users are assigned an IP address from the San Francisco IV block by zApp. We are configured to get one of 512 (?) available addresses and they are randomly assigned to our users when they first visit the internet. This assignment is persistent. (I’ve had the same IP for over three week now).

The problem: one of the IPs assigned to our users is black-flagged due to it being used by a SPAM / BOT.

We use Cloudflare service to protect our website. Every one of our users with the suspicious IP visits our site, they are challenged by a Captcha because Cloudflare considers traffic from this IP “suspicous?. Since the IP is cached for my users, they see the Captcha every time they visit our website. Our business requires them to visit several times a day. So they are challenged several times a day.

Q: Is there a way to get the zApp to assign a new IP to my users (so they are not forced to continue using the problematic IP)?

Thank you,
 -tomas
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmSaCAI/macos-zscaler-app-log-location","lastmod":"2026-07-31T01:22:10.000Z","id":"0D54u00009evmSaCAI"} -->
## MacOS Zscaler App Log Location

- Source: https://community.zscaler.com/s/question/0D54u00009evmSaCAI/macos-zscaler-app-log-location
- Type: Q&A
- Posted: 2020-08-18T12:15:28.000Z
- Last activity: 2026-07-31T01:22:10.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I’m looking for information on where the log files are located on a MacOS install. We are still running version 1.5 of the agent so the “export logs? option from the client is not a viable option as it is in the .enc format. (Client update is planned for later this year).
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmSbCAI/window-7-w-tunnel-20","lastmod":"2023-05-31T09:08:16.000Z","id":"0D54u00009evmSbCAI"} -->
## Window 7 w/ Tunnel 2.0

- Source: https://community.zscaler.com/s/question/0D54u00009evmSbCAI/window-7-w-tunnel-20
- Type: Q&A
- Posted: 2020-08-18T01:48:56.000Z
- Last activity: 2023-05-31T09:08:16.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

I would like to ask if will there be any incompatibility issues using Window 7 w/ Tunnel 2.0?

Regards,
 Matthews Loke
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmXECAY/zsatray-icon-issue-client-connector","lastmod":"2023-05-31T08:44:58.000Z","id":"0D54u00009evmXECAY"} -->
## ZSATray Icon issue client connector

- Source: https://community.zscaler.com/s/question/0D54u00009evmXECAY/zsatray-icon-issue-client-connector
- Type: Q&A
- Posted: 2021-03-11T13:22:13.000Z
- Last activity: 2023-05-31T08:44:58.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello community,

Some of our colleagues experience an annoying / hindering behavior of the ZCC on their Win 10 clients (1909 or 20H2).
 In the mid of January we upgraded successfully couple thousand ZCC installations from 1.5.x to 3.1.x . Around 3-4 weeks later, problems on clients started when users connect to their virtual or physical workstations using Citrix.
 The ZCC doesn’t start/connect as usual, the ZSATray.exe process starts and gets stopped within the next second. Just a few seconds later that is repeated, again, again and again … On some clients all ZSATray.exe processes disappear after getting stopped, in some cases couple of them remain as stopped.
 Each time a ZSATray.exe process tries to start, it generates a new Zscaler tray icon, which will not automatically disappear when the process is stopped / disappearing.

Result: An whole screen full of Zscaler tray icons when you check the hidden tray icons. Usually they are all gray (like disabled/not connect ZCC), rarely there is a lonely “connected? blue icon, but you cannot open ZCC through any of them (neither gray, nor blue), they disappear as soon as you move the mouse over them.
 You can clean up all tray icons, but shortly there start to reappear, one by one.
 Due to the frequent attempt to restart the process, a ton of logfiles get generated, over 100k files are easily reached.

We already tried the newer version 3.1.0.129 instead of our .103 we currently use, as upgrade installation as well as clean installation. No success.

As soon as you login via RDP (Remote Desktop User group), it works fine, if the colleagues connect again via Citrix, the issue reappears.
 After you logged in once with admin permission via RDP, the issue seems to be solved, also when you login later via Citrix is seem to work fine so far.
 But that cannot be the solution, granting admin permission generally to all users and hoping it is solved afterwards – logistical nightmare, especially as we currently only from a few workstations that issue is reported, but potentially couple hundreds could be effected!

We checked the location service settings, but that also wasn’t the solution:
 Logging in with one user after activating the location service – ZCC works fine. Next day another user works with the same workstation, the issue appears again.
 For that user it helped to perform a cold reboot (Windows > Shutdown > Turn on via power button), for another colleague on another workstation it didn’t work.

So far, everything we tried and eventually worked feels like rolling a dice.

We would appreciate if one of you had similar experiences and a working solution for it or an idea / suggestion what we can check or change to fix it properly.

Thanks!

regards
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmZXCAY/wsl2-connectivity","lastmod":"2023-05-31T08:47:57.000Z","id":"0D54u00009evmZXCAY"} -->
## WSL2 connectivity?

- Source: https://community.zscaler.com/s/question/0D54u00009evmZXCAY/wsl2-connectivity
- Type: Q&A
- Posted: 2021-04-27T17:31:53.000Z
- Last activity: 2023-05-31T08:47:57.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Has anyone had success with ZApp and WSL2? Seems WSL is using the built-in Internet Connection Sharing functionality to get out. I can get teh WSL instance running, but seem to have no connectivity outside. Using Tunnel 2.0 DTLS.

Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmbRCAQ/can-a-user-with-multiple-devices-use-them-simultaneously-whilst-logged-in-using-that-same-single-account","lastmod":"2023-05-31T09:26:49.000Z","id":"0D54u00009evmbRCAQ"} -->
## Can a User with multiple devices use them simultaneously whilst logged in using that same single account

- Source: https://community.zscaler.com/s/question/0D54u00009evmbRCAQ/can-a-user-with-multiple-devices-use-them-simultaneously-whilst-logged-in-using-that-same-single-account
- Type: Q&A
- Posted: 2022-03-07T15:41:03.000Z
- Last activity: 2023-05-31T09:26:49.000Z
- Answers: 12
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Can a User with multiple devices use them simultaneously whilst logged in using that same single account?
 Can a User log into their Zscaler Client Connector on one Device using their single user account and also on their other Device with ZCC using the same username?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmdaCAA/err-zpc-failed-to-start-capture-on-interfaces","lastmod":"2023-05-31T08:12:40.000Z","id":"0D54u00009evmdaCAA"} -->
## ERR ZPC: Failed to start capture on interfaces

- Source: https://community.zscaler.com/s/question/0D54u00009evmdaCAA/err-zpc-failed-to-start-capture-on-interfaces
- Type: Q&A
- Posted: 2021-09-01T20:03:01.000Z
- Last activity: 2023-05-31T08:12:40.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Community,

When i clicked on start packet capture , i am getting below error.

**Failed to start capture on interfaces.**

After debugging the export logs i found below one ,

**Error in pcap_findalldevs_ex: No interfaces found! Make sure libpcap/WinPcap is properly installed on the local machine.**
 ** ERR ZPC: Failed to get Adapter list while initializing packet capturing module.**
 **ERR ZPC: Failed to start capture on interfaces.**

What would be the solution for this ?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmddCAA/about-zcc-frequent-disabled-enabled","lastmod":"2023-05-31T08:12:40.000Z","id":"0D54u00009evmddCAA"} -->
## About ZCC frequent disabled enabled

- Source: https://community.zscaler.com/s/question/0D54u00009evmddCAA/about-zcc-frequent-disabled-enabled
- Type: Q&A
- Posted: 2023-03-17T02:29:54.000Z
- Last activity: 2023-05-31T08:12:40.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

i had this weird issue with a user’s new laptop.
 tried reinstall still the same, wahts causing the frequent disabled enabled notification of ZCC?
 user has Ethernet port connected and possibly wifi but usually ethernet should take the precedence.
 user has other machines signed in too.
 anything that can cause this?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmdiCAA/recommended-ios-byod-zcc-config-advice","lastmod":"2024-03-27T18:05:38.000Z","id":"0D54u00009evmdiCAA"} -->
## Recommended IOS BYOD ZCC Config Advice

- Source: https://community.zscaler.com/s/question/0D54u00009evmdiCAA/recommended-ios-byod-zcc-config-advice
- Type: Q&A
- Posted: 2023-03-12T17:02:24.000Z
- Last activity: 2024-03-27T18:05:38.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

While I’ve found plenty of articles for deploying ZCC on IOS and setting config options, I can’t seem to find much on recommended forwarding/app profile configuration and/or pac files.

The use case is we want users to use ZCC on IOS purely for:

1. Access to a few internal web apps over ZPA
2. Leverage our existing O365 SIPA config for [login.microsoftonline.com](http://login.microsoftonline.com)
3. Detect/Block potential security threats

However we DO NOT want to inspect or log any traffic except for the above since these are BYOD devices-- users should feel free to surf facebook or whatever without having their traffic recorded.

I would figure this is a fairly common use case, but I can’t get it working the way I want. With “per-app? VPN I can get the internal apps available on-demand, and adding [login.microsoftonline.com](http://login.microsoftonline.com) as a safari url works via SIPA (though it seems to mess with sso or create an auth loop of some type, and for some reason breaks the reporting of device compliance status in azure for the logon entry via safari, which is the main issue), but it doesn’t filter or block and malware style traffic, which I guess is expected since it’s only forwarding specific URLS. If I go full on demand VPN, I can’t figure out how to write a bypass in a PAC file that basically bypasses everything except for a few URL’s but maintains security monitoring.

Anybody have a sample IOS BYOD config they could shed some light on?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmdlCAA/zcc-for-android-configuration-on-chrome-os","lastmod":"2024-12-06T19:49:38.000Z","id":"0D54u00009evmdlCAA"} -->
## ZCC for Android configuration on Chrome OS

- Source: https://community.zscaler.com/s/question/0D54u00009evmdlCAA/zcc-for-android-configuration-on-chrome-os
- Type: Q&A
- Posted: 2023-03-09T15:06:07.000Z
- Last activity: 2024-12-06T19:49:38.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

We are looking to run ZCC application for Android on Chrome OS as it is supported by Google as per [Install and use Android apps on your Chromebook - Chromebook Help](https://support.google.com/chromebook/answer/7021273?hl=en)

Does ZScaler have any article on how to configure ZCC for Android on Chrome OS?

Something similar to Zoom’s article would be helpful.

Zoom Support
[image]

##### [Configuring the Zoom for ChromeOS app](https://support.zoom.us/hc/en-us/articles/203806069-Configuring-the-Zoom-for-ChromeOS-app)

If you're an admin that manages Chrome OS devices, you can configure Zoom for Chrome OS using the Google Admin console. You can set policies that control login features. Note: The Zoom for Chrome P...

Thanks,
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmdmCAA/full-tunnel-vpn-zscaler-client-connector","lastmod":"2023-07-06T12:50:37.000Z","id":"0D54u00009evmdmCAA"} -->
## Full Tunnel VPN -- Zscaler Client Connector

- Source: https://community.zscaler.com/s/question/0D54u00009evmdmCAA/full-tunnel-vpn-zscaler-client-connector
- Type: Q&A
- Posted: 2023-03-09T10:34:17.000Z
- Last activity: 2023-07-06T12:50:37.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

We’ve encountered with an issue where one of our end-user is using a client provided VPN which is working in Full Tunnel Mode (Complete Traffic is going via VPN Network).

How ever, we’ve noticed that there is no proper **Default Route** defined in the routing table but still the traffic is passing via Client Network…

Due to this, we are unable to add Trusted NW in our Forwarding Profile Configuration to make ZCC disabled when the user is connected to VPN.

Zscaler is interfering here with VPN Traffic and causing access issues.

Can someone share any suggestions to resolve this ?

Thanks,
 Rahul V
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmduCAA/captive-portal-issue","lastmod":"2023-07-06T12:50:54.000Z","id":"0D54u00009evmduCAA"} -->
## Captive Portal issue

- Source: https://community.zscaler.com/s/question/0D54u00009evmduCAA/captive-portal-issue
- Type: Q&A
- Posted: 2023-03-01T04:47:47.000Z
- Last activity: 2023-07-06T12:50:54.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Team,

Some of our systems are facing issues like a captive portal issues once they connect to internet. Internet is connected but unable to access ar browse. Can anyone guide how to fix this?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmdxCAA/performance-issues-in-brazil","lastmod":"2025-08-22T07:27:06.000Z","id":"0D54u00009evmdxCAA"} -->
## Performance issues in Brazil

- Source: https://community.zscaler.com/s/question/0D54u00009evmdxCAA/performance-issues-in-brazil
- Type: Q&A
- Posted: 2023-02-28T02:55:15.000Z
- Last activity: 2025-08-22T07:27:06.000Z
- Answers: 11
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is anybody facing performance issues in Brazil via SAO4 with ZIA enabled? No VPN/GRE, just ZCC.

Switching between Tunnel 1.0 and 2.0 make no difference as well as trying DTLS and TLS. Once ZIA is turned off, download and upload speeds are lightening fast.

Just curious…
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evme2CAA/zscaler-disconnecting-daily-at-a-specific-time","lastmod":"2023-05-31T08:43:18.000Z","id":"0D54u00009evme2CAA"} -->
## Zscaler Disconnecting Daily at a Specific Time

- Source: https://community.zscaler.com/s/question/0D54u00009evme2CAA/zscaler-disconnecting-daily-at-a-specific-time
- Type: Q&A
- Posted: 2023-02-23T16:02:07.000Z
- Last activity: 2023-05-31T08:43:18.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Dear Support Team, I use Zscaler Client Connector to login to our company’s system while working remotely. I daily face this weird issue that around 4-4:30 PM my local time I get disconnected. I’d need to log out of Client Connector and do the authentication process again to connect. However the connection keeps being instable (i.e. frequent disconnections). I’m not sure what is causes this around this specific timeframe. The application version is 3.9.0.175. I appreciate if someone could help. Happy tp provide necessary logs and information if needed.

Many thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evme3CAA/performance-issue-with-zcc","lastmod":"2026-05-25T11:32:28.000Z","id":"0D54u00009evme3CAA"} -->
## Performance issue with ZCC

- Source: https://community.zscaler.com/s/question/0D54u00009evme3CAA/performance-issue-with-zcc
- Type: Q&A
- Posted: 2023-02-21T13:07:23.000Z
- Last activity: 2026-05-25T11:32:28.000Z
- Answers: 10
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Everyone,

recently, users are claiming that they are having performance issue with teams and internet while on ZCC client If they turn off ZIA everything works fine. I also followed best practice from Zscaler best-practices-for-microsoft365-and-zscaler.pdf during the implementation. has anyone experience performance issue and what was solution?

We are only using ZCC forwarding pac file. We don’t have GRE/site to site tunnel setup.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evme5CAA/initial-login-to-azure-vdi-gets-struck-for-few-minutes-with-zcc-tunnel-20","lastmod":"2024-11-19T16:45:34.000Z","id":"0D54u00009evme5CAA"} -->
## Initial login to Azure VDI gets struck for few minutes with ZCC (Tunnel 2.0)

- Source: https://community.zscaler.com/s/question/0D54u00009evme5CAA/initial-login-to-azure-vdi-gets-struck-for-few-minutes-with-zcc-tunnel-20
- Type: Q&A
- Posted: 2023-02-15T22:07:02.000Z
- Last activity: 2024-11-19T16:45:34.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

A customer has VDIs in Azure environment. They would like to have ZCC clients on those VDIs for both ZIA and ZPA. With Zscaler Internet enabled they are running into an issue during initial login to the VDI.
 These are Azure Virtual Desktop, running Windows 10 single session persistent VDIs.
 **Issue is:**
 VDI initiates the launch process and stuck at welcome screen for few minutes, then it gives a reconnecting message and tries to continue login. I believe during this time ZCC tries to login and that is when we see reconnecting message.
 Found a community discussion on the topic: [Azure WVD and ZIA - #3 by andy11](https://community.zscaler.com/t/azure-wvd-and-zia/11832/3). It talks about adding WVD ranges to bypass using tunnel 2.0 bypass in app profile. The article talks about WVD IP range of approx. 288 IPs. But there is no reference to where those Ips are listed. We also tried contacting Microsoft but they say there is no fixed range of IPs.
 Has anyone worked on Azure VDI deployment before? Any recommendations? (
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evme9CAA/zcc-portal-download-links-zia","lastmod":"2023-05-31T08:12:40.000Z","id":"0D54u00009evme9CAA"} -->
## ZCC portal download links - ZIA

- Source: https://community.zscaler.com/s/question/0D54u00009evme9CAA/zcc-portal-download-links-zia
- Type: Q&A
- Posted: 2023-02-13T21:50:26.000Z
- Last activity: 2023-05-31T08:12:40.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is there a way to setup a portal for users to download the Zscaler Client Connector?
 In ZPA you can use user portals, but I don’t see a similar option for ZIA.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmeBCAQ/zcc-ui-nonresponsive-but-connectivity-is-fine","lastmod":"2023-05-31T08:43:24.000Z","id":"0D54u00009evmeBCAQ"} -->
## ZCC UI Non-Responsive but Connectivity is Fine

- Source: https://community.zscaler.com/s/question/0D54u00009evmeBCAQ/zcc-ui-nonresponsive-but-connectivity-is-fine
- Type: Q&A
- Posted: 2023-02-09T20:19:45.000Z
- Last activity: 2023-05-31T08:43:24.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We’ve had this issue across multiple versions of ZCC for over a year now, across our entire fleet of laptops, and am finally getting around to trying to tackle the cause.

When a laptop moves between networks, or between wired and wifi, or sometimes when coming out of sleep mode, the taskbar zscaler icon will show a red X, however when you open up the UI, everything shows connected and both ZPA/ZIA are functional. But the UI itself is frozen and does not update. Killing the zsatray.exe process fixes the issue, but it inevitably re-occurs. It really only causes an issue when a user needs to re-authenticate, as they can’t click it, but it is annoying and creates helpdesk tickets from users concerned about the red X. We use tunnel 1.0 and 4.0.7 CC x64 along with tunnel with local proxy. Any ideas?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmeCCAQ/dlp-notification-via-zscaler-client-connector","lastmod":"2023-05-31T09:27:20.000Z","id":"0D54u00009evmeCCAQ"} -->
## DLP notification via Zscaler Client Connector

- Source: https://community.zscaler.com/s/question/0D54u00009evmeCCAQ/dlp-notification-via-zscaler-client-connector
- Type: Q&A
- Posted: 2023-02-09T17:32:56.000Z
- Last activity: 2023-05-31T09:27:20.000Z
- Answers: 2
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Guys,

The cloud of zscaler updated last week, and these version, there are new features available.

Do you know how can I configure DLP notification by zscaler client connector? This is which one new features now from the version 6.2.

Regards,
 Thais.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmeHCAQ/zscaler-zcc-with-citrix-daas","lastmod":"2026-05-19T08:28:26.000Z","id":"0D54u00009evmeHCAQ"} -->
## Zscaler ZCC with Citrix DaaS

- Source: https://community.zscaler.com/s/question/0D54u00009evmeHCAQ/zscaler-zcc-with-citrix-daas
- Type: Q&A
- Posted: 2023-02-04T13:36:45.000Z
- Last activity: 2026-05-19T08:28:26.000Z
- Answers: 10
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

Is Citrix DaaS supported with ZCC/ZDX?

Citrix.com
[image]

##### [Citrix DaaS Delivers Secure Virtual Apps and Desktops to Any Device - Citrix](https://www.citrix.com/products/citrix-daas/)

Citrix DaaS modernizes IT with a secure, easy-to-manage cloud solution to quickly deliver apps or desktops from any cloud or datacenter worldwide.

Thanks,
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmeJCAQ/authentication-issues-during-deployment-of-zcc","lastmod":"2023-07-06T12:51:49.000Z","id":"0D54u00009evmeJCAQ"} -->
## Authentication Issues during deployment of ZCC

- Source: https://community.zscaler.com/s/question/0D54u00009evmeJCAQ/authentication-issues-during-deployment-of-zcc
- Type: Q&A
- Posted: 2023-02-03T21:00:25.000Z
- Last activity: 2023-07-06T12:51:49.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are deploying the ZCC in 1000 users batches via Altiris, for most of the users its getting installed smoothly and user logged in automatically and all works fine, but 10% of the user we are facing issues in their auto-login.

During deployment user goes in specific aap-profile which is call Global and then once successfully logged in it takes it own specific profile. XXX-USA

Can anyone suggest what could be the issue during first attempt? we are using the strictenforcement so Internet stopped working for user bcoz user in not logged in and they face outage.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmeeCAA/what-is-the-difference-between-pac-in-forwarding-profile-and-app-profile","lastmod":"2023-07-06T12:51:59.000Z","id":"0D54u00009evmeeCAA"} -->
## What is the difference between PAC in Forwarding profile and APP profile?

- Source: https://community.zscaler.com/s/question/0D54u00009evmeeCAA/what-is-the-difference-between-pac-in-forwarding-profile-and-app-profile
- Type: Q&A
- Posted: 2023-01-30T08:53:03.000Z
- Last activity: 2023-07-06T12:51:59.000Z
- Answers: 15
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

My customer is rolling out Zscaler ZIA with Zscaler Client Connector (Tunnel 2.0). But I’m fully confused about the PAC files in Forwarding profile and APP profile.

I’ve read this thread [Difference in use of "App Profile PAC file" and "Forwarding Profile PAC file"](https://community.zscaler.com/t/difference-in-use-of-app-profile-pac-file-and-forwarding-profile-pac-file/6847) and had an insight that PAC in Forwarding Profile will directly apply to Windows system proxy script, PAC in APP Profile will be used by Zscaler Client Connector only for determine how to deal with traffic.

But I still have some confusions:

1. In that thread, David_Cooper said “Forwarding profile PAC applies only to Tunnel with Local Proxy and Enforce PAC modes.? But what will happen if I select “Tunnel? and also define a PAC file? Actually this is what is it in our Zscaler Console.
2. According to my understanding, PAC in Forwarding Profile will directly apply to Windows system proxy script. So if I define a “DIRECT? action for some domains, those domains should be bypassed, right? But from the testing, it will not bypass but still send to Zscaler? Could you please guide me what did I mis-understand?
3. From Zscaler Help document. I saw one highlight “When using Tunnel mode, only use the PAC file to bypass traffic away from Zscaler Client Connector or to tunnel traffic to Zscaler Client Connector. Do not use it to tunnel traffic to the Zscaler cloud.? But could you please let me know what will happen if using the PAC file to tunnel traffic to Zscaler Service Edge? Since this is what our customer did in the forwarding profile’s PAC file. Will it due to we are using Regional Surcharge Location?

Thank you very much.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmevCAA/strict-enforcement-system-updates","lastmod":"2023-07-06T12:52:02.000Z","id":"0D54u00009evmevCAA"} -->
## Strict Enforcement - System Updates

- Source: https://community.zscaler.com/s/question/0D54u00009evmevCAA/strict-enforcement-system-updates
- Type: Q&A
- Posted: 2023-01-19T16:23:53.000Z
- Last activity: 2023-07-06T12:52:02.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We have deployed ZIA using tunnel 1.0 using strict enforcement and the enforcement of policies have been working as expected. The issue is, as mentioned in another post, is that the system updates fail when a user is not logged into the system. I have worked with tech support and the response was to create PAC file exceptions for the list of sites requiring direct access which is a long list when it comes to Microsoft. I have added Operating and System Updates as an auth exempt as well but did not work. I did see in another post about a new client coming out, per my SE maybe this year, that will allow for creating exceptions to address the issue. I guess I am asking what is everyone else doing to resolve this issue when using strict enforcement with tunnel 1.0. Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmezCAA/zcc-41062-testing","lastmod":"2025-08-22T07:27:08.000Z","id":"0D54u00009evmezCAA"} -->
## ZCC 4.1.0.62 Testing

- Source: https://community.zscaler.com/s/question/0D54u00009evmezCAA/zcc-41062-testing
- Type: Q&A
- Posted: 2023-01-16T13:46:59.000Z
- Last activity: 2025-08-22T07:27:08.000Z
- Answers: 9
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We’ve recently deployed this Limited Availability version for testing and noticed issues related to ZPA where our corporate domain is no longer reachable. Mapped drives not accessible, NLTEST checks fail to internal domain, unable to elevate privileges since internal domain is not reachable.

Reverting back to 3.9 resolved the issue.

Has anyone deployed the latest 4.1 version and faced a similar issue?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmf3CAA/zscaler-trigger-alert-when-disable-zcc","lastmod":"2023-07-06T12:52:16.000Z","id":"0D54u00009evmf3CAA"} -->
## Zscaler trigger alert when disable zcc

- Source: https://community.zscaler.com/s/question/0D54u00009evmf3CAA/zscaler-trigger-alert-when-disable-zcc
- Type: Q&A
- Posted: 2023-01-12T09:05:38.000Z
- Last activity: 2023-07-06T12:52:16.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

I want to create an alert when logging out from the zscaler client connector. Can I do this through the ZIA admin panel? or by another method? I couldn’t find a solution.

Thank you
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmf9CAA/route-web-and-nonweb-traffic-through-zscaler","lastmod":"2023-05-31T08:43:40.000Z","id":"0D54u00009evmf9CAA"} -->
## Route Web and Non-Web traffic through Zscaler

- Source: https://community.zscaler.com/s/question/0D54u00009evmf9CAA/route-web-and-nonweb-traffic-through-zscaler
- Type: Q&A
- Posted: 2023-01-11T10:37:44.000Z
- Last activity: 2023-05-31T08:43:40.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

I am curios to know how to route the web and non web traffic through Zscaler, as we have enabled Advance cloud firewall.
 and client is asking to route all non web traffic through Zscaler.
 recently we have complete local break out and enable GRE tunnel on all regional Plant.

below Forwarding Method used .

1. off trusted network ZCC Tunnel 2.0
2. VPN trusted network ZCC tunnel 2.0
3. Trusted Network GRE+Tunnel 1.0

in first two forwarding method all web non web traffic is routing via ZScaler.
 but trusted Network ( office network ) non web traffic is not routing via Zscaler, its back-haul to DC firewall.

Can someone suggest how to route all web and non web traffic via Zscaler when user on Trusted network.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmfBCAQ/guide-zscaler-client-connector-deployment-with-workspace-one-uem-for-macos","lastmod":"2024-10-24T17:41:34.000Z","id":"0D54u00009evmfBCAQ"} -->
## [Guide] Zscaler Client Connector Deployment with Workspace ONE UEM for macOS

- Source: https://community.zscaler.com/s/question/0D54u00009evmfBCAQ/guide-zscaler-client-connector-deployment-with-workspace-one-uem-for-macos
- Type: Q&A
- Posted: 2023-01-10T22:27:31.000Z
- Last activity: 2024-10-24T17:41:34.000Z
- Answers: 3
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Updated: Apr 24, 2023

The Zscaler Client Connector can be configured and deployed with Workspace ONE UEM on macOS devices with a few simple steps. This guide is based on Workspace ONE UEM running version 23.2.0.0 (2302) and deploys Zscaler Client Connector v3.9.0.81 to a macOS computer running Ventura. ZCC v3.9.x and newer releases can now be deployed using custom property list (plist) files.

*Note: Workspace ONE UEM is required for the initial installation only. Zscaler Client Connector upgrades can be managed thereafter from within the Zscaler Client Connector Portal.*

**High-Level Steps:**

1. Download the Zscaler Client Connector Installer PKG from Zscaler Client Connector Portal
2. Use the Workspace ONE Admin Assistant tool to generate the Workspace ONE client installer and metadata (.plist) file
3. Create an Internal Native App in Workspace ONE UEM and assign to managed MacOS endpoints
4. Create a macOS Profile in Workspace ONE UEM to deploy a payload with custom installation options and to install and trust the Zscaler Root CA certificate used for SSL inspection in the System Keychain

**Before You Begin:**

A few key pieces of information will be required to create the script if SSO support is required:

- The Zscaler cloud name used for your organization
- The primary domain used for SAML authentication by your organization

**Determine your Zscaler Cloud Name:**

If your organization is provisioned on more than one cloud, your users will normally be prompted to select the cloud to which their traffic will be sent during the enrollment process. To avoid this prompt, you can pre-configure the Zscaler Client Connector to automatically connect to the intended cloud automatically by using this installation option.

To determine your cloud name, you can follow the directions in [this article](https://help.zscaler.com/zia/what-my-cloud-name-zia)

**Determine your Primary Authentication Domain:**

This installation option allows users to skip the app enrollment page. If SSO is enabled for your organization, users are taken directly to your organization’s SSO login page. If you’ve integrated SSO with the app, users can also skip the SSO login page and are automatically enrolled with the Zscaler service and logged in. If your instance has multiple domains associated with it, use the primary domain for your instance.

**Step 1: Download the Zscaler Client Connector installer PKG**

When the macOS endpoint executes the script, it will download the Zscaler Client Connector directly from Zscaler using the URL. You can determine the download URL for the Zscaler Client Connector to be deployed by following the below steps:

1. From the Zscaler Internet Access Admin UI, select Policy from the left navigation bar and click on Zscaler Client Connector Portal in the Mobile section

[image]

2. Select Administration from the top navigation bar in the Zscaler Client Connector Portal and click on Client Connector App Store from the navigation bar on the left and select New Releases

3. Select macOS from the list of platforms click on Download PKG to download the ZCC Installer PKG file to your Downloads folder

[image]

**Step 2:****Generate the Workspace ONE installer package and metadata files**

Launch the Workspace ONE Admin Assistant tool and drag the downloaded PKG file into the app. The tool will parse the installer PKG file and create the required .plist, .pkg and image files in ~/Documents/Workspace ONE Admin Assistant folder/

[image]

**Step 3: Create the Workspace ONE UEM Native Internal App**

1. In the Workspace ONE UEM admin console, select Resources > Apps > Native, click on Add and select Application File to add the app

[image]

2. Upload the installer package file created with the Workspace ONE Admin Assistant tool (not the one you downloaded)

[image]

3. Upload the metadata (plist) file created with the Workspace ONE Admin Assistant tool

[image]

[image]

4. Click on Images and upload the PNG icon file created by the Workspace ONE Admin Assistant tool

[image]

5. Select the appropriate Assignment Group to use, name the Distribution and click on Save and Publish to start the deployment

**Step 4: Configuration profile for payload with custom install paramaters and Zscaler Root CA certificate**

A Configuration Profile is required to deploy payload with custom installation parameters and the Zscaler Root CA certificate to managed macOS computers. If using the default Zscaler certificate, the certificate will need to be downloaded from the Zscaler Internet Access Admin UI and added to a Configuration Profile in Workspace ONE UEM by following the below directions.

*Note: Steps 1 and 2 are only applicable when using the Zscaler default certificate. If the organization is using a custom Root CA certificate, use the custom certificate instead.*

1. To download the certificate, login to the Zscaler Internet Access Admin UI, select Policy from the left navigation bar and click on SSL Inspection in the Access Control section

[image]

x2. Select Intermediate CA Certificates and click on the pencil icon for Zscaler Intermadiate Certificate to show the view certificate popup. Click on the Download link to download the certificate as a zip file. Unzip the downloaded Zscaler Root CA certificate archive.

[image]

[image]

3. Create a Profile in Workspace ONE UEM by clicking on Resources > Profiles & Baselines > Profiles and then click on Add and select Add Profile. Name your profile.

[image]

4. Create the payload to install ZCC with custom install options

*Note: The userDomain and cloudName install options are used in this example to leverage existing desktop SSO. These install options enable Zscaler Client Connector to leverage existing SSO in your environment. Use the values from the Before You Begin section*

﻿
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmfCCAQ/once-we-block-the-user-on-azure-ad-zcc-automatically-been-removed","lastmod":"2023-05-31T08:12:44.000Z","id":"0D54u00009evmfCCAQ"} -->
## Once we block the user on Azure AD, ZCC automatically been removed

- Source: https://community.zscaler.com/s/question/0D54u00009evmfCCAQ/once-we-block-the-user-on-azure-ad-zcc-automatically-been-removed
- Type: Q&A
- Posted: 2023-01-10T20:40:01.000Z
- Last activity: 2023-05-31T08:12:44.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

We are using SCIM auto provision. This gives near-real-time updates to user status in the application when the change is made. Sometimes, we need to block the user access for forensics.

I noticed that whenever we do that on Azure AD. ZCC automatically been removed for this user. Although the "Automatically Force Remove Inactive Devices " option is never.

Any Suggestions, What might cause this removal?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmfHCAQ/redirect-web-traffic-to-zscaler-client-connector-listening-proxy","lastmod":"2025-01-30T09:02:15.000Z","id":"0D54u00009evmfHCAQ"} -->
## Redirect Web Traffic to Zscaler Client Connector Listening Proxy

- Source: https://community.zscaler.com/s/question/0D54u00009evmfHCAQ/redirect-web-traffic-to-zscaler-client-connector-listening-proxy
- Type: Q&A
- Posted: 2023-01-06T15:31:31.000Z
- Last activity: 2025-01-30T09:02:15.000Z
- Answers: 6
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

So with this new feature, I am not finding anything in the docs or trainings about what exactly this does and what the use cases would be, in combination with the “Use Z-Tunnel 2.0 for Proxied Web Traffic?. What exactly do these two options do to the traffic and what would the use cases be for these two new options? If there’s updated documentation, then that would be awesome to share.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmfICAQ/zcc-and-traffic-tunneling","lastmod":"2023-05-31T08:43:40.000Z","id":"0D54u00009evmfICAQ"} -->
## ZCC and Traffic Tunneling

- Source: https://community.zscaler.com/s/question/0D54u00009evmfICAQ/zcc-and-traffic-tunneling
- Type: Q&A
- Posted: 2023-01-06T15:21:17.000Z
- Last activity: 2023-05-31T08:43:40.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Ok, maybe I’m having a brain fart, but when using Z-Tunnel 2.0 for forwarding ZIA traffic using the ZCC, doesn’t that encapsulate all traffic, not just 80/443? In the past I saw that all traffic was encapsulated in the Z-Tunnel, but since upgrading to the 3.9.0.183 ZCC I’m seeing what I have dubbed “leaking traffic?, or traffic that I expect to be encapsulated in the Z-Tunnel appearing outside the tunnel on my WAN routers. Some of this traffic is HTTPS which I do not have excluded from ZCC, such as [mobileadmin.zscalertwo.net](http://mobileadmin.zscalertwo.net), so not sure why all of a sudden this is appearing outside of the tunnel.
 Just wanting to make sure I am not having a huge brain fart and remembering something wrong.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmfWCAQ/zscaler-folder-not-accessible-after-zcc-upgrade","lastmod":"2023-05-31T08:43:44.000Z","id":"0D54u00009evmfWCAQ"} -->
## Zscaler Folder not accessible after ZCC Upgrade

- Source: https://community.zscaler.com/s/question/0D54u00009evmfWCAQ/zscaler-folder-not-accessible-after-zcc-upgrade
- Type: Q&A
- Posted: 2023-01-03T16:27:27.000Z
- Last activity: 2023-05-31T08:43:44.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

After upgrading to ZCC version 3.9.0.175, I am unable to access Zscaler Folder in my local machine under Program Data. It gives a permission error. Nothing has changed from client side.
 It works with the old version 3.6.x.x.
 Any idea on the fix for this?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmfhCAA/zscaler-machine-tunnel-with-duo-security","lastmod":"2023-05-31T09:09:00.000Z","id":"0D54u00009evmfhCAA"} -->
## Zscaler Machine Tunnel with Duo Security

- Source: https://community.zscaler.com/s/question/0D54u00009evmfhCAA/zscaler-machine-tunnel-with-duo-security
- Type: Q&A
- Posted: 2022-12-22T19:48:54.000Z
- Last activity: 2023-05-31T09:09:00.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I wanted to share my experience in this journey. Using Duo Security for MFA presents some challenges because by default it blocks other Credential Providers from activating until after MFA occurs. In the case of a Machine Tunnel and the Zscaler Diagnostics tab on the login screen, this doesn’t work very well.
 What you have to do is add the GUID of the ZSACredentialProvider to the registry value for the ProvidersWhitelist key. In this case, the GUID is {bb6c9014-b670-4f4f-80cb-4fda1ef98d81}, so adding that and enabling the Machine Tunnel, then you will see the Zscaler Diagnostics tab on the login screen before Duo authentication.
 Just thought I would share.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmfiCAA/hide-zcc-from-showing-in-taskbar","lastmod":"2023-05-31T08:43:46.000Z","id":"0D54u00009evmfiCAA"} -->
## Hide ZCC from showing in taskbar

- Source: https://community.zscaler.com/s/question/0D54u00009evmfiCAA/hide-zcc-from-showing-in-taskbar
- Type: Q&A
- Posted: 2022-12-20T22:21:37.000Z
- Last activity: 2023-05-31T08:43:46.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is there a way to hide the Zscaler Client Connector icon from showing up in the taskbar?

Customer request.
 Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmfsCAA/i-would-like-to-bypass-o365-traffic-from-zcc","lastmod":"2023-07-06T12:52:44.000Z","id":"0D54u00009evmfsCAA"} -->
## I would like to bypass O365 traffic from ZCC

- Source: https://community.zscaler.com/s/question/0D54u00009evmfsCAA/i-would-like-to-bypass-o365-traffic-from-zcc
- Type: Q&A
- Posted: 2022-12-16T11:59:52.000Z
- Last activity: 2023-07-06T12:52:44.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,
 i have scenario Silverpeak SDWAN for onprime location , Pulse secure for VPN & ZCC for home users currenlty iam by passing the teams traffic and i am using 1 click solution
 i would like to send all O365 traffic locally for home users in automated way i dont want to update manually o365 IPs and urls in PAC file is there any solution for that?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmfuCAA/tunnel-fallback","lastmod":"2023-07-06T12:52:45.000Z","id":"0D54u00009evmfuCAA"} -->
## Tunnel fallback

- Source: https://community.zscaler.com/s/question/0D54u00009evmfuCAA/tunnel-fallback
- Type: Q&A
- Posted: 2022-12-15T18:26:42.000Z
- Last activity: 2023-07-06T12:52:45.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Team,
 Anyone can explain in technical way why tunnel is fallback to from tunnel 2.0 to tunnel 1.0?
 Thank you
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmfyCAA/minimum-and-required-os-versions-supported-web-browsers-internet-bandwidth-minimums-etc","lastmod":"2023-06-02T17:09:55.000Z","id":"0D54u00009evmfyCAA"} -->
## Minimum and required OS versions, supported web browsers, Internet bandwidth minimums, etc

- Source: https://community.zscaler.com/s/question/0D54u00009evmfyCAA/minimum-and-required-os-versions-supported-web-browsers-internet-bandwidth-minimums-etc
- Type: Q&A
- Posted: 2022-12-14T16:33:51.000Z
- Last activity: 2023-06-02T17:09:55.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I’m looking for information such as minimum and required operating system versions, compatible web browsers, Internet bandwidth minimums, and so on. This way we can ensure users are running on the preferred requirements and avoid a number of potential issues and troubleshooting paths. For example, if we know a user is using the most recent version of Chrome, we can verify that everyone is using the most recent version of Chrome. This saves us time investigating a potential chrome issue. The same is true for network settings, operating systems, and so forth. The purpose of this exercise is to incorporate those requirements into our base image and have no problems when performing a new or fresh deployment.

I referred to this page [Zscaler Help](https://help.zscaler.com/client-connector/step-by-step-configuration-guide-zscaler-client-connector), but it lacks information on the browser version and the minimum network requirements recommended by Zscaler.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmg0CAA/creating-packet-capture-with-client-connector-for-apple-ios","lastmod":"2023-07-07T11:22:24.000Z","id":"0D54u00009evmg0CAA"} -->
## Creating packet capture with client connector for Apple iOS

- Source: https://community.zscaler.com/s/question/0D54u00009evmg0CAA/creating-packet-capture-with-client-connector-for-apple-ios
- Type: Q&A
- Posted: 2022-12-13T22:32:14.000Z
- Last activity: 2023-07-07T11:22:24.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Packet capture for the Zscaler Client Connector on iOS has been an option since [version 1.8.7 released in January 6, 2022](https://help.zscaler.com/client-connector/client-connector-app-release-summary-2022?applicable_category=iOS&applicable_version=1.8.7&deployment_date=2022-01-06&id=1388691). The one aspect that isn’t spelled out in detail is how to export the packet capture from the iPhone to a computer to view the capture. This post provides the steps to export and view the capture as well as the log files.

1. Open the Zscaler app on the iPhone. If the Zscaler app is not seen, search for it by swiping all the way to the left. [[image] image1170×2532 118 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O8PtAAK)
2. Click on **More…** in the bottom right corner [[image] image1170×2532 94.2 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O6goAAC)
3. Click on **Start Packet Capture** [[image] image1170×2532 94.4 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O4usAAC)
4. Once the required data is captured click **Stop Packet Capture** [[image] image1170×2532 93.8 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O68IAAS)
5. Click on **Export Logs**. [[image] image1170×2532 94.2 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O5NfAAK)
6. Choose Mail and email the logs and capture to an email address [[image] image1170×2532 98 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O5nzAAC)
7. Download the zip file from the email and expand. The following files should be seen [image]

With this you should be able use the log files and captures to continue your troubleshooting
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmgTCAQ/how-to-configure-postman-for-zcc-api-calls","lastmod":"2024-04-15T14:26:57.000Z","id":"0D54u00009evmgTCAQ"} -->
## How to configure Postman for ZCC API Calls

- Source: https://community.zscaler.com/s/question/0D54u00009evmgTCAQ/how-to-configure-postman-for-zcc-api-calls
- Type: Q&A
- Posted: 2022-12-09T12:48:18.000Z
- Last activity: 2024-04-15T14:26:57.000Z
- Answers: 3
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

The Zscaler help documentation for the ZCC API call is located here: [About the Zscaler Client Connector API | Zscaler](https://help.zscaler.com/client-connector/about-zscaler-client-connector-api).

Steps for creating the Public API are here: [About API Key Management | Zscaler](https://help.zscaler.com/client-connector/about-api-key-management). If you do not see the Public API section in your mobile admin portal you will need to contact Zscaler support to enable the feature.

Below are the steps I completed in order to make the API calls via postman.

**Getting Started**
 From Postman, create a new collection, I called mine ZCC API. Create a new POST request:

- Click Collections
- Click Create New
- Set a Collection Name
- Click Ellipses next to Collection Name
- Click add request

[collection]

**API Authentication**
 In order to complete API calls you will need to authenticate. This authentication will provide a JWTToken that will have a TTL as set in the ZCC portal when the API key was made.

- Type = Post
- URL = [https://api-mobile](https://api-mobile).**CLOUD**.net/papi/auth/v1/login
- The example tenant is on zscloud.
- Create the following headers: -Content-Length with no value -Host with no value -Accept with a value of */* -Content-Type with a value of application/json

[[request post] request post758×146 10.7 KB](https://global.discourse-cdn.com/zscaler/original/2X/3/3b35d7738b4c088bec37ef52bf9902498d77ad00.png)

[[auth post headers] auth post headers1767×374 26.2 KB](https://global.discourse-cdn.com/zscaler/original/2X/6/61cf1f2a7c4b226773ea5ddaae9e82fc58bac1bb.png)

In the body enter the apiKey and secretKey in the below format and replace string with the actual values (leave the quotation marks as shown in the image below):

> {
 “apiKey?: “string?,
 “secretKey?: “string?
 }
>

*Note: You may need to type out the above syntax. Postman may not recognize the unicoding of the quotation marks and the call will fail as a result.*

Click Save and send the call. You should receive a JWTToken in response and you will use this in your additional calls.

[[save and send] save and send1492×146 11.6 KB](https://global.discourse-cdn.com/zscaler/original/2X/4/4528099779f2df70eb7e8234af82066fdb38a4cb.png)

[[jwttoken] jwttoken1781×609 51 KB](https://global.discourse-cdn.com/zscaler/original/2X/7/72735c1c86d325b7da8cbb6f8f5bba37fb0a0d77.png)

**API Call-GetDevices**
 Now that you have the JWTToken you can complete additional calls. Create a new GET request under your ZCC API collection for GetDevices.

- Set the request to a GET type
- URL = [https://mobileadmin](https://mobileadmin).**CLOUD**.net/papi/public/v1/getDevices
- Create a header of auth-token and enter your JWTToken as the Value
- Click Save and Click Send
- Your response will contain the device information from the ZCC portal, including the udid used in the GetOTP call

[[getdevices response] getdevices response1084×784 36 KB](https://global.discourse-cdn.com/zscaler/original/2X/a/a7a0011b0254e72cc45ea2f57938c9248500318e.png)

**API Call-GetOTP**
 To get the OTP for a specific device you need that device’s UDID which is obtained by the GetDevices call completed previously.

- Create New Request
- URL = [https://mobileadmin](https://mobileadmin).**CLOUD**.net/papi/public/v1/getOtp
- Create a Params of udid and enter the device udid as the value (this is provided by the GetDevices call)
- Create a header of auth-token and enter your JWTToken as the Value
- Click Save and Click Send

[[udid params]](https://global.discourse-cdn.com/zscaler/original/2X/f/fc52a56e0160aeaa9fc6569fe22c2693c265215b.png)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmgXCAQ/how-to-temporarily-bypass-specific-dcs-for-road-warriors-running-zcc","lastmod":"2023-07-07T11:22:33.000Z","id":"0D54u00009evmgXCAQ"} -->
## How to temporarily bypass specific DCs for Road Warriors running ZCC

- Source: https://community.zscaler.com/s/question/0D54u00009evmgXCAQ/how-to-temporarily-bypass-specific-dcs-for-road-warriors-running-zcc
- Type: Q&A
- Posted: 2022-12-08T17:56:27.000Z
- Last activity: 2023-07-07T11:22:33.000Z
- Answers: 4
- Likes: 4
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Updated: Jan-5-2023

Zscaler has a number of options when customers want to bypass specific DCs for their Road Warriors. The recommended solution is to use Subclouds and remove the DC.

You can find more information on Subclouds [here](https://help.zscaler.com/zia/what-subcloud). As of v6.2+, customers can [manage their own Subclouds](https://help.zscaler.com/zia/editing-subcloud) in the ZIA AdminUI and no longer need to open a case with Zscaler support to make changes. This is the recommended long term approach.

Another approach is to use an App Profile PAC for situations where you may want to avoid a specific DC or a specific VIP for a short period of time.

**Caveats:**

- This bypass method is meant for web traffic (80/443) over **Z-Tunnel 1.0 only**
- This method is also applicable for customers using Z-Tunnel 2.0 with the new “Redirect Web Traffic to ZCC Proxy? feature enabled + “Use Z-Tunnel 2.0 for Proxied Web Traffic? feature disabled which results in Z-Tunnel 1.0 being used for web traffic. This is used in situations where customers need to bypass destinations for non proxy-aware Windows applications. [[image] image669×858 55.2 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O7IYAA0)
- In other scenarios where Z-Tunnel 2.0 is in use, Zscaler recommends the use of Subclouds

**Configuration Steps:**

In the below example, the Chicago DC will be bypassed and the secondary DC will be used instead.

1. In order to bypass Chicago, find the Proxy hostnames to bypass using [Zscaler Config for ZS3](https://config.zscaler.com/zscalerthree/cenr). In this case [zscalerthree.net](http://zscalerthree.net) (ZS3) is in use and the hostnames are [chi1.sme.zscalerthree.net](http://chi1.sme.zscalerthree.net) and [chi1-2.sme.zscalerthree.net](http://chi1-2.sme.zscalerthree.net) [[image] image931×219 25.5 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O41iAAC)
2. Create a PAC file to bypass the Chicago DC by entering the Proxy names from step 2 above. Example PAC file shown below.

```
function FindProxyForURL(url, host) {

    var privateIP = /^(0|10|127|192\.168|172\.1[6789]|172\.2[0-9]|172\.3[01]|169\.254|192\.88\.99)\.[0-9.]+$/;
    var resolved_ip = dnsResolve(host);

    /* Don't send non-FQDN or private IP auths to us */
    if (isPlainHostName(host) || isInNet(resolved_ip, "192.0.2.0","255.255.255.0") || privateIP.test(resolved_ip))
        return "DIRECT";
                        
    /* test with ZPA */
    if (isInNet(resolved_ip, "100.64.0.0","255.255.0.0"))
        return "DIRECT";
                        
    /* Trust sites are directly accessible */
    var trust = /^trust\.(zscaler|zscalerone|zscalertwo|zscloud|zscalergov|zsfalcon|zdxcloud)\.(com|net)$/;
    if (trust.test(host))
        return "DIRECT";

    /* === Chicago DC Bypass ================ */
    var gateway_port = "${GATEWAY}";
    var gateway = gateway_port.replace(":80","");
    var chicago1 = dnsResolve("chi1.sme.zscalerthree.net");
    var chicago2 = dnsResolve("chi1-2.sme.zscalerthree.net");

        /* Bypass Chicago */        
        if ((gateway == chicago1)||
                    (gateway == chicago2))
            return "PROXY ${COUNTRY_SECONDARY_GATEWAY_FX}:80; DIRECT";
    /* === End Bypass Section ================ */

    /* Default Traffic Forwarding. Forwarding to Zen on port 80, but you can use port 9400 also */
    return "PROXY ${GATEWAY_FX}:80; PROXY ${SECONDARY_GATEWAY_FX}:80; DIRECT";
```

1. Update the App Profile with the new PAC URL. Its best to use a new PAC URL as Zscaler Client Connector will enable it quicker can changing the contents of an existing PAC. [[image] image821×211 14 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O7oOAAS)
2. Instead of sending web traffic to the Chicago DC, Zscaler Client Connector will now send traffic to the backup DC
3. When you want to start using the Chicago DC again, replace the App Profile PAC URL with the original PAC URL and Zscaler Client Connector will start using the Chicago DC in the next 15 mins
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmgaCAA/unknown-access-from-zsatunnelexe","lastmod":"2023-05-31T08:43:44.000Z","id":"0D54u00009evmgaCAA"} -->
## Unknown access from ZSATunnel.exe

- Source: https://community.zscaler.com/s/question/0D54u00009evmgaCAA/unknown-access-from-zsatunnelexe
- Type: Q&A
- Posted: 2022-12-08T13:36:37.000Z
- Last activity: 2023-05-31T08:43:44.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi

I checked EDR(Endpoint Detection and Response) logs and there were many connection logs that zsatunnel.exe access to many global IP address without zscaler IP range([https://ips.zscaler.net/sites/default/files/geoips/geoip.csv](https://ips.zscaler.net/sites/default/files/geoips/geoip.csv)) .

Do you know the reason?
 Because zsatunnel.exe access to internet instead of other process ??
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmgmCAA/getting-captive-portal-detected-error-frequently","lastmod":"2023-07-06T12:53:08.000Z","id":"0D54u00009evmgmCAA"} -->
## Getting Captive portal detected error frequently

- Source: https://community.zscaler.com/s/question/0D54u00009evmgmCAA/getting-captive-portal-detected-error-frequently
- Type: Q&A
- Posted: 2022-11-30T17:41:33.000Z
- Last activity: 2023-07-06T12:53:08.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

while working from home getting Captive portal detected error frequently if we click retry the error cleared and the ZCC app is getting connected and after some the error keep on repeating.

Any help would be much appreciated
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmgrCAA/zcc-showing-app-policy-as-all-users-which-doesnt-exist","lastmod":"2023-07-06T12:53:14.000Z","id":"0D54u00009evmgrCAA"} -->
## ZCC showing app policy as "All users" which doesnt exist

- Source: https://community.zscaler.com/s/question/0D54u00009evmgrCAA/zcc-showing-app-policy-as-all-users-which-doesnt-exist
- Type: Q&A
- Posted: 2022-11-29T11:57:08.000Z
- Last activity: 2023-07-06T12:53:14.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Even though I am mapping my user to a different app profile but ZCC of her is showing as “All users? policy which doesnt exist in our configuration.
 Unable to logout her from client as passowrd is not correct( as policy is not matching/configured even).
 Tried upgrading client but no luck.
 Tried re-auth user forcefully with no luck.

Please suggest
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmh2CAA/zcc-pac-file-fail-over-to-secondary-data-center","lastmod":"2023-07-06T12:53:12.000Z","id":"0D54u00009evmh2CAA"} -->
## ZCC PAC File Fail Over to Secondary Data Center

- Source: https://community.zscaler.com/s/question/0D54u00009evmh2CAA/zcc-pac-file-fail-over-to-secondary-data-center
- Type: Q&A
- Posted: 2022-11-21T21:03:26.000Z
- Last activity: 2023-07-06T12:53:12.000Z
- Answers: 2
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I had a bit of difficulty finding a good way to fail over our users to their secondary gateways during cloud incidents so I decided to write up what we have in our PAC in hopes it will help some of you recover from cloud incidents / issue’s quicker until the functionality is built in to ZCC. Support provided me with the syntax, I just added all US data centers so that I didn’t need to write it out on the fly while having an issue.

Until ZCC has this functionality built in we are forced to use the PAC file to fail over, or rather exclude specific data centers, from our clients. Keep in mind that this only works if the primary data center is the only one affected / having an issue. If both the primary and secondary data center are having issues for the client you would need to use this same logic but return a specific data center rather than using the variables I have below or find another means to move them to a different one outside of the impacted regions.

Use at your own risk, this is general information that works in our environment but may not work in yours depending on how you are setup. This is only for US data centers, add or remove based on your needs.

The below PAC file is specific for [Zscalertwo.net](http://Zscalertwo.net), modify it as necessary for whatever cloud instance you’re on.

**Variable Explanation:**

Variable “TEMP? gets the current primary gateway. Make sure you use whatever variable you’re currently using in your default return statement for finding the primary gateway here, we use COUNTRY_GATEWAY_FX so that is what is used in this example.

Variable “GATEWAY? uses the temp variable and removes the “:80? from the end of it so that we just have the IP address set for the GATEWAY variable

For the RETURN statement in each data center section make sure you use whatever you’re using for your secondary gateway in your default RETURN statement. We are using COUNTRY_SECONDARY_GATEWAY_FX so that is what is used in this example.

I have both of these variables set in the beginning of the PAC file with my other variables, I put them right above the IF statements here though to keep it simple.

During a cloud incident you remove the “//? from the lines for the data center you wish to exclude. If the clients primary gateway is found to be within the ranges of the isInNet the return statement will apply and only return their secondary gateway. When the issue is resolved you comment out the sections and they will begin using that DC again if it is their primary DC.

Note that it can take up to 15 minutes before the change is applied to the client after activating the change.

Add the below lines above your default return statement and below any DIRECT statements in your PAC file.

//*-------------------------Beginning Of Zscaler Data Center Bypass Section-------------------------------------------------

The Below Sections Are For Bypassing Specified Data Centers During Cloud Incidents

------------------------------------------------------------------------------------------------------------*//
 var temp = “${COUNTRY_GATEWAY_FX}?;
 var gateway = temp.replace(“:80?,“?);

/* Bypass Atlanta */

// if (
 // (isInNet(gateway,“136.226.2.0?,“255.255.252.0?))||
 // (isInNet(gateway,“104.129.204.0?,“255.255.254.0?))||
 // (isInNet(gateway,“104.129.204.0?,“255.255.254.0?))
 // )
 // return “PROXY ${COUNTRY_SECONDARY_GATEWAY_FX}:80; DIRECT?;

/* Bypass Boston */

// if (
 // (isInNet(gateway,“136.226.72.0?,“255.255.252.0?))||
 // (isInNet(gateway,“136.226.74.0?,“255.255.254.0?))||
 // (isInNet(gateway,“136.226.70.0?,“255.255.254.0?))
 // )
 // return “PROXY ${COUNTRY_SECONDARY_GATEWAY_FX}:80; DIRECT?;

/* Bypass Chicago */

// if (
 // (isInNet(gateway,“165.225.56.0?,“255.255.252.0?))||
 // (isInNet(gateway,“104.129.196.0?,“255.255.254.0?))||
 // (isInNet(gateway,“165.225.60.0?,“255.255.252.0?))
 // )
 // return “PROXY ${COUNTRY_SECONDARY_GATEWAY_FX}:80; DIRECT?;

/* Bypass Dallas */

// if (
 // (isInNet(gateway,“165.225.34.0?,“255.255.254.0?))||
 // (isInNet(gateway,“165.225.216.0?,“255.255.254.0?))||
 // (isInNet(gateway,“165.225.36.0?,“255.255.254.0?))
 // )
 // return “PROXY ${COUNTRY_SECONDARY_GATEWAY_FX}:80; DIRECT?;

/* Bypass Denver */

// if (
 // (isInNet(gateway,“165.225.10.0?,“255.255.254.0?))
 // )
 // return “PROXY ${COUNTRY_SECONDARY_GATEWAY_FX}:80; DIRECT?;

/* Bypass Los Angeles */

// if (
 // (isInNet(gateway,“104.129.198.0?,“255.255.254.0?))||
 // (isInNet(gateway,“136.226.66.0?,“255.255.254.0?))
 // )
 // return “PROXY ${COUNTRY_SECONDARY_GATEWAY_FX}:80; DIRECT?;

/* Bypass Miami */

// if (
 // (isInNet(gateway,“136.226.58.0?,“255.255.254.0?))||
 // (isInNet(gateway,“165.225.222.0?,“255.255.254.0?))
 // )
 // return “PROXY ${COUNTRY_SECONDARY_GATEWAY_FX}:80; DIRECT?;

/* Bypass New York */

// if (
 // (isInNet(gateway,“165.225.38.0?,“255.255.254.0?))||
 // (isInNet(gateway,“165.225.220.0?,“255.255.254.0?))||
 // (isInNet(gateway,“136.226.80.0?,“255.255.254.0?))
 // )
 // return “PROXY ${COUNTRY_SECONDARY_GATEWAY_FX}:80; DIRECT?;

/* Bypass San Francisco */

// if (
 // (isInNet(gateway,“104.129.192.0?,“255.255.254.0?))||
 // (isInNet(gateway,“165.225.242.0?,“255.255.254.0?))
 // )
 // return “PROXY ${COUNTRY_SECONDARY_GATEWAY_FX}:80; DIRECT?;

/* Bypass Seattle */

// if (
 // (isInNet(gateway,“136.226.54.0?,“255.255.254.0?))||
 // (isInNet(gateway,“136.226.56.0?,“255.255.254.0?))||
 // (isInNet(gateway,“165.225.50.0?,“255.255.254.0?))
 // )
 // return “PROXY ${COUNTRY_SECONDARY_GATEWAY_FX}:80; DIRECT?;

/* Bypass Washington */

// if (
 // (isInNet(gateway,“165.225.8.0?,“255.255.254.0?))||
 // (isInNet(gateway,“136.226.50.0?,“255.255.254.0?))||
 // (isInNet(gateway,“136.226.48.0?,“255.255.254.0?))||
 // (isInNet(gateway,“136.226.52.0?,“255.255.254.0?))||
 // (isInNet(gateway,“104.129.194.0?,“255.255.254.0?))
 // )
 // return “PROXY ${COUNTRY_SECONDARY_GATEWAY_FX}:80; DIRECT?;

//*-------------------------End Of Zscaler Data Center Bypass Section-------------------------------------------------*//
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhFCAQ/force-specific-zscaler-datacenter-at-app-profile-pac-file","lastmod":"2023-05-31T09:27:32.000Z","id":"0D54u00009evmhFCAQ"} -->
## Force specific Zscaler DataCenter at App Profile PAC file

- Source: https://community.zscaler.com/s/question/0D54u00009evmhFCAQ/force-specific-zscaler-datacenter-at-app-profile-pac-file
- Type: Q&A
- Posted: 2022-11-17T13:05:42.000Z
- Last activity: 2023-05-31T09:27:32.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

Here in the company we are using Z-Tunnel 1.0 and we would like to force the connection through a specific Zscaler Cloud DataCenter.

We are using Zscaler Client Connector with the mode “tunnel with local proxy? and APP profile PAC to redirect the traffic to the Cloud.

Our goal is to force the traffic to be directed to the Sao Paulo IV Datacenter, the proxy hostname is: [sao4.sme.zscloud.net](http://sao4.sme.zscloud.net)

What we did in the .pac file was:

return "PROXY [sao4.sme.zscaler.net:80](http://sao4.sme.zscaler.net:80); PROXY [sao2-2.sme.zscaler.net:80](http://sao2-2.sme.zscaler.net:80); DIRECT;

I also tried before to use the same servers at the port 443, but it doesn´t work. Using the port 80 as indicated above, the ZCC keeps warning that there is a “captive portal? detected and the tunnel is not being closed with Zscaler.

I also tried to use the variable “GATEWAY_FN? but it doesn´t work, the connection continues to be redirected to SAO PAULO 2 some times.

Any ideas on how to accomplish my goal to force the connection only to SAO PAULO IV Datacenter?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhJCAQ/how-to-read-zcc-logs","lastmod":"2023-06-02T17:09:38.000Z","id":"0D54u00009evmhJCAQ"} -->
## How to read ZCC logs

- Source: https://community.zscaler.com/s/question/0D54u00009evmhJCAQ/how-to-read-zcc-logs
- Type: Q&A
- Posted: 2022-11-16T13:10:22.000Z
- Last activity: 2023-06-02T17:09:38.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Dear,

Could someone help me to understand how to read ZCC logs for some specific error. There are multiple file in ZCC logs so i am bit confused from where to start and which file is related to which type of error.

Thanks in Advanced!!

Best Regards,
 Pardeep Rawat
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhQCAQ/using-client-connector-with-multiple-windows-accounts","lastmod":"2023-05-31T08:44:07.000Z","id":"0D54u00009evmhQCAQ"} -->
## Using client connector with multiple Windows accounts

- Source: https://community.zscaler.com/s/question/0D54u00009evmhQCAQ/using-client-connector-with-multiple-windows-accounts
- Type: Q&A
- Posted: 2022-11-14T08:37:14.000Z
- Last activity: 2023-05-31T08:44:07.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I have a personal Windows 11 laptop that I also use to access work at my employer, and my work has installed Zscaler on my laptop. My company uses Private Access only. My partner also uses the laptop and she has her own user account on the laptop. On her account, the Zscaler Client Connector is asking for my work login details. Even if the app is closed, the window re-appears again after a couple of minutes. Is there anything that can be done to prevent this? It seems that the only solution might be for me to sign in to the account on her account as well, to stop the window constantly popping up. Is this correct?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhRCAQ/zscaler-is-captive-portal-detected-at-personal-network","lastmod":"2024-02-27T17:39:08.000Z","id":"0D54u00009evmhRCAQ"} -->
## Zscaler is Captive Portal Detected at Personal network

- Source: https://community.zscaler.com/s/question/0D54u00009evmhRCAQ/zscaler-is-captive-portal-detected-at-personal-network
- Type: Q&A
- Posted: 2022-11-13T16:22:15.000Z
- Last activity: 2024-02-27T17:39:08.000Z
- Answers: 13
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Zscaler displays Captive Portal Detected in home and personal network environments. But working in a corporate network environment is fine. Zscaler cannot be used outside the company and the internal website cannot be accessed. This error occurs frequently. And only on home and personal networks. It involves most of the people in the company. PS: We are using the Chinese network. This is a problem that has never arisen before. There will be a brief return to normal after restarting Zscaler.

[[Screenshot 2022-11-13 at 23.57.35] Screenshot 2022-11-13 at 23.57.351534×1210 83.3 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O4iIAAS)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhUCAQ/client-connector-does-not-authenticate-with-windows-public-network-setting-enabled","lastmod":"2023-05-31T08:44:10.000Z","id":"0D54u00009evmhUCAQ"} -->
## Client Connector does not authenticate with Windows public network setting Enabled

- Source: https://community.zscaler.com/s/question/0D54u00009evmhUCAQ/client-connector-does-not-authenticate-with-windows-public-network-setting-enabled
- Type: Q&A
- Posted: 2022-11-09T20:21:34.000Z
- Last activity: 2023-05-31T08:44:10.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Client Connector does not authenticate with the Windows public network configuration Enabled, however, with the private network configuration the authentication is performed completely, what are the possible solutions?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhVCAQ/need-help-in-notify-users-for-zcc-upgrade","lastmod":"2023-05-31T08:44:07.000Z","id":"0D54u00009evmhVCAQ"} -->
## Need help in notify users for ZCC upgrade

- Source: https://community.zscaler.com/s/question/0D54u00009evmhVCAQ/need-help-in-notify-users-for-zcc-upgrade
- Type: Q&A
- Posted: 2022-11-09T20:21:06.000Z
- Last activity: 2023-05-31T08:44:07.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello family ,

Need your help here ,

In my environement wanted to deploy new ZCC version but here client requirement is users who are connecting to ZCC they should get notified that they have upgraded to new version with an mail .

example :
 The Zscaler Application allows remote access to enterprise applications and resources. For instance, team members with our organisation laptops must use this application to access such resources .

Beginning November 14th, IT will begin deploying an upgrade to the Zscaler client.

Users will be asked to reboot their device to complete the upgrade, however, it will not be forced and can be scheduled at a convenient time within 24 hours.

This will be a phased roll out over lasting approximately 4 weeks and should not be impactful based on our testing.

When the upgrade is complete the APP version will change to 3.8.0.102 depending on OS version when clicking More in the application

Can you please help me with this ASAP.

Regards,
 Durga Chitturi .
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhaCAA/zscaler-client-connector-api-issues","lastmod":"2023-05-31T08:43:40.000Z","id":"0D54u00009evmhaCAA"} -->
## Zscaler Client Connector API - Issues

- Source: https://community.zscaler.com/s/question/0D54u00009evmhaCAA/zscaler-client-connector-api-issues
- Type: Q&A
- Posted: 2022-11-07T15:48:58.000Z
- Last activity: 2023-05-31T08:43:40.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I have got a lots of problems with this API:

- RateLimiting: it is too low to implement any process.
- GetDevices: It does not bring all the information, and if you have a long list of devices (+50000) it fails too many times.

The Zscaler solution was the new endopint /public/v1/downloadDevices. In this endpoint you can get a CSV con all devices, the problem is that I am only able to download a few fields. For example I need:

- ZCC Versions.
- All timestamps
- Policy

And in the CSV only appears:

User
 Device type
 Device model
 UDID
 Mac Address
 Company Name
 OS Version
 Status
 OS Type

Can someone work fine with a large group of devices?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhfCAA/zcc-api-timestamp-issue","lastmod":"2023-07-06T11:43:17.000Z","id":"0D54u00009evmhfCAA"} -->
## ZCC API 'TimeStamp' Issue

- Source: https://community.zscaler.com/s/question/0D54u00009evmhfCAA/zcc-api-timestamp-issue
- Type: Q&A
- Posted: 2022-11-04T13:33:18.000Z
- Last activity: 2023-07-06T11:43:17.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We have recently been given access to the ZCC API. I am using PowerShell to run the queries, ‘getDevices’ runs successfully and returns ZCC devices. However, it appears any field with a timestamp is not returning correct results.

Format appears to be ‘DateTime Ticks’. Converting the Ticks, I get wildly incorrect Date and Times. The Tick timestamps appear to be truncated in some form as they are way too short compared to current date/time Ticks.

Anyone else having this issue? I opened a ticket with support and am awaiting their thoughts. Thought I would submit here as well. Thanks.

[image]
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhhCAA/zcc-linux-ztunnel-20-dtls-support","lastmod":"2023-07-06T12:53:58.000Z","id":"0D54u00009evmhhCAA"} -->
## ZCC Linux Ztunnel 2.0 (DTLS) Support?

- Source: https://community.zscaler.com/s/question/0D54u00009evmhhCAA/zcc-linux-ztunnel-20-dtls-support
- Type: Q&A
- Posted: 2022-11-03T14:36:07.000Z
- Last activity: 2023-07-06T12:53:58.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Zenith Community,

i couldn’t find anything documented on the Zscaler Documentation Guides for ZCC Linux, in regards if it will support Ztunnel 2.0 (DTLS) on any Linux running the ZCC Linux.

with kind regards Steve
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhiCAA/possible-to-tether-to-an-iphoneandroid-phone-with-zscaler-app","lastmod":"2023-05-31T08:44:13.000Z","id":"0D54u00009evmhiCAA"} -->
## Possible to tether to an Iphone/Android Phone with ZScaler App?

- Source: https://community.zscaler.com/s/question/0D54u00009evmhiCAA/possible-to-tether-to-an-iphoneandroid-phone-with-zscaler-app
- Type: Q&A
- Posted: 2022-11-03T11:38:30.000Z
- Last activity: 2023-05-31T08:44:13.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

If we were to tether a device with Zsacler App running and the device running as a Wifi Hotspot - would we be able to access the same restricted locations that the Zscaler app on the phone has configured?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhkCAA/difference-between-exit-disable-logout","lastmod":"2023-05-31T09:27:41.000Z","id":"0D54u00009evmhkCAA"} -->
## Difference between exit, disable, logout

- Source: https://community.zscaler.com/s/question/0D54u00009evmhkCAA/difference-between-exit-disable-logout
- Type: Q&A
- Posted: 2022-11-02T16:43:16.000Z
- Last activity: 2023-05-31T09:27:41.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Windows app profiles have options to set passwords for

Logout Password
 Uninstall Password
 Password to Disable ZIA
 Password to Disable ZPA
 Exit Password

Mac and Linux app profiles have the options to set passwords for
 Logout Password
 Disable Password

What is the functionality difference between disabling and logging out? Exiting and logging out? Disabling and exiting?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhlCAA/success-using-zcc-on-aws-workspaces-instances","lastmod":"2024-07-16T19:59:42.000Z","id":"0D54u00009evmhlCAA"} -->
## Success using ZCC on AWS Workspaces instances?

- Source: https://community.zscaler.com/s/question/0D54u00009evmhlCAA/success-using-zcc-on-aws-workspaces-instances
- Type: Q&A
- Posted: 2022-11-02T06:49:50.000Z
- Last activity: 2024-07-16T19:59:42.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Has anyone had success using ZCC on AWS Workspaces instances? I’ve followed the guides and modified both the App and Forwarding profiles, as well as configuring a custom do not inspect for SSL, but have intermittent success using Tunnel 1.0, meaning I can reconnect to my instance but only with repeated retries With Tunnel 2.0 my remote session gets dropped almost immediately after updating the policy on the Workstation’s ZCC.
 If you have it working, please share!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhmCAA/configuring-client-connector-for-dns-control-and-cloud-firewall","lastmod":"2023-10-31T13:50:26.000Z","id":"0D54u00009evmhmCAA"} -->
## Configuring Client Connector for DNS Control (and Cloud Firewall)

- Source: https://community.zscaler.com/s/question/0D54u00009evmhmCAA/configuring-client-connector-for-dns-control-and-cloud-firewall
- Type: Q&A
- Posted: 2022-10-31T23:17:03.000Z
- Last activity: 2023-10-31T13:50:26.000Z
- Answers: 3
- Likes: 5
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Follow these example steps to insure that DNS traffic is sent to the Zscaler Zero Trusted Exchange (ZTE) from Client Connector users and endpoints.

The steps below are intended to be a simplistic explanation for the purposes of highlighting general guidelines for configuring Client Connector. It is important to consult the more comprehensive [Helpdocs configurations](https://help.zscaler.com/client-connector/configuring-zscaler-client-connector-profiles) in order to fully assess the impacts and decide on an approach (among other considerations) prior to making changes or enabling.

##### Step 1: Enable Tunnel 2

First step is to ensure that the Client Connector can send non-web traffic. To do this, ensure that Tunnel 2 is enabled.

[[image] image352×640 67.9 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O5eSAAS)

If migrating from a Tunnel 1 deployment to a Tunnel 2 deployment, there are a series of best practice recommendations that should be examined to do this in both a comprehensive and non-disruptive manner. It is therefore important that [the Helpdocs for tunnel 1 to 2 migration](https://help.zscaler.com/z-app/migrating-z-tunnel-1.0-z-tunnel-2.0) are consulted, understood, and part of the migration plan.

Two further considerations here:

First is that Cloud Firewall policy will apply to any non-web traffic that is sent to the Zero Trust Exchange (ZTE and specifically ZIA). Today, Cloud Firewall can apply policy to traffic forwarded via Tunnel 1 or forwarded from fixed Location deployments (GRE, IPSec, etc). Please see the [Cloud Firewall Helpdocs](https://help.zscaler.com/zia/about-firewall) and take note of configurations like the [“Enable Firewall for Z-Tunnel 1.0 and PAC Road Warriors" in Advanced Settings](https://help.zscaler.com/zia/about-advanced-settings) and the [required enablement of Cloud Firewall for each fixed Location](https://help.zscaler.com/zia/enabling-firewall-locations) in the Location Management of the Admin settings.

Second, enabling Tunnel 2 is also a step towards enabling Cloud Firewall for your users. This ensures that corporate security follows these users wherever they go and branches and locations are no longer constrained by the functional and operational limits physical or VM/logical legacy-generation firewalls. This means that all traffic is examined with DPI, any non-standard web is directed to the SWG, and IPS signature rules are applied to all non-web threats – all in addition to DNS Control for standard DNS and DNS over HTTPS (DoH).

A final note is that new Zscaler customers will one day have Tunnel 2 enabled by default and this will become the standard Client Connector deployment especially targeting remote users (Road Warriors). The above described first step is for existing customers who have not already enabled Tunnel 2.

##### Step 2: Set your ZCC Includes and Excludes

Order of operations:

1. DNS inclusion take precedence over DNS exclusions
2. DNS inclusion and exclusion will be only checked for the Destination IPv4 exclusion listed IP addresses
3. If DNS query is destined to any IP address present in the destination inclusion IPv4 then it will check DNS inclusions/exclusions and Inclusion will be given priority

The Includes tell what IPs, ports, protocols, and domains should be sent to the ZTE for DNS Control to examine. The Excludes indicate what should not be sent. Generally, the most specific designation here wins.

To ensure that standard DNS is sent we want to add a more specific Destination Inclusion to target just the standard DNS traffic and enter “0.0.0.0/0:53".

Domain Inclusions for DNS can start by simply assuming the asterisk wildcard meaning “all domains should be included and sent to ZTE".

Consider adding any private domains that are not publicly resolvable to the Domain Exclusions list like “*.INTERNAL" or "*.CORP" or “*.MYCORPDOMAIN.NET" etc.

[image]

##### Step 3: Modify Further According to Real Use Case

Any DNS over non-standard ports needs to be explicitly added to the port Includes but also the DNS network service needs to be modified to include the non-standard DNS. Also, DNS over HTTPS (DoH) is not considered non-standard DNS and if web traffic is already being directed to the ZIA then DNS Control policy will apply to DoH traffic as it does to standard DNS.

Be sure to consult the [Helpdocs for Client Connector](https://help.zscaler.com/z-app/policy-administration-settings) for more details: [Policy & Administration Settings](https://help.zscaler.com/z-app/policy-administration-settings)

##### Endnote: What this Client Connector Config Doesn't Cover for DNS

Well, quite a bit and hence the reason for the noted caution above. But most notably, organizations that have a DNS server cluster on premises may wish to start getting the benefits of DNS Control security and performance by simply forwarding their recursive DNS queries to the Zscaler Trusted Resolvers (ZTR) and implementing security policies for these forwarders.

[DNS Control deployment architectures, options](https://community.zscaler.com/t/dns-control-deployment-architectures-options/16497) [Cloud Firewall](https://community.zscaler.com/c/access-control/55)

> The DNS Control module provides DNS security and DNS performance capabilities in an industry-unique DNS proxy model. Being a DNS proxy (and along with web proxy in the SWG) means that all DNS traffic is inspected regardless of destination and traffic type. User endpoint traffic simply needs to pass through our proxy and then all DNS Control policy can be applied to all user traffic regardless of DNS resolver used. Proxy advantage of DNS Control The DNS proxy model is particularly effective w…
>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhpCAA/3005-zcc-error-wmi-fix-not-working","lastmod":"2024-07-11T11:34:49.000Z","id":"0D54u00009evmhpCAA"} -->
## 3005 ZCC Error - wmi fix not working

- Source: https://community.zscaler.com/s/question/0D54u00009evmhpCAA/3005-zcc-error-wmi-fix-not-working
- Type: Q&A
- Posted: 2022-10-27T10:50:34.000Z
- Last activity: 2024-07-11T11:34:49.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I’ve got a machine here that’s receiving the 3005 error, it was on 3.1, and that’s been update to 3.7. We’ve tried running the WMI fix batch file, and that completes succesfully but we still end up with the same errors

Anyone any other ideas to tyr.

[image]
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhtCAA/2-mail-id-login-in-single-zscaler-client-connector","lastmod":"2023-06-05T05:09:26.000Z","id":"0D54u00009evmhtCAA"} -->
## 2 Mail ID login in single zscaler client connector

- Source: https://community.zscaler.com/s/question/0D54u00009evmhtCAA/2-mail-id-login-in-single-zscaler-client-connector
- Type: Q&A
- Posted: 2022-10-21T11:17:41.000Z
- Last activity: 2023-06-05T05:09:26.000Z
- Answers: 4
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Team,
 How to use 2 different credentials in single ZCC?
 company own credential working in ZCC but when we are using different credential from another business for zcc it is showing wrong password?

example: working in company ABC and Using ABC laptop and Using ABC zscaler credential and it is working.
 Not working when we are using XYZ credential in ABC company laptop(ZCC) showing wrong password
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhzCAA/zscaler-client-connector-cannot-be-started","lastmod":"2023-05-31T09:27:39.000Z","id":"0D54u00009evmhzCAA"} -->
## Zscaler Client Connector cannot be started

- Source: https://community.zscaler.com/s/question/0D54u00009evmhzCAA/zscaler-client-connector-cannot-be-started
- Type: Q&A
- Posted: 2022-10-17T13:24:51.000Z
- Last activity: 2023-05-31T09:27:39.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

I have the following problem: the client connector cannot be started by clicking on ‘Open Zscaler’ in the tray menu. I can see an empty, white window and nothing happens.

I have the version 3.6.1.23. I have tried to reinstall the application but it didn’t help.

Could you please help me?

Thank you!

Laszlo
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmi6CAA/client-connector-for-rhel8","lastmod":"2023-06-02T17:09:34.000Z","id":"0D54u00009evmi6CAA"} -->
## Client connector for RHEL8

- Source: https://community.zscaler.com/s/question/0D54u00009evmi6CAA/client-connector-for-rhel8
- Type: Q&A
- Posted: 2022-10-12T15:38:17.000Z
- Last activity: 2023-06-02T17:09:34.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,
 I installed the Zscaller client on RHEL8 using the binary installer (Zscaler-linux-1.3.0.31-installer.run).
 I get the following error when trying to start the installed client:

```
# /opt/zscaler/bin/ZSTray 
/opt/zscaler/bin/ZSTray: /lib64/libstdc++.so.6: version `GLIBCXX_3.4.26' not found (required by /opt/zscaler/bin/ZSTray)
/opt/zscaler/bin/ZSTray: /lib64/libc.so.6: version `GLIBC_2.32' not found (required by /opt/zscaler/bin/ZSTray)
```

Is the Zscaler client compatible with RHEL8? The installer didn’t complain, yet obviously I won’t be able to install glibc 2.32.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmiACAQ/ms-company-portal-application-does-not-work-with-twlp-zcc-version-390160","lastmod":"2024-06-07T09:19:42.000Z","id":"0D54u00009evmiACAQ"} -->
## MS Company portal Application does not work with TWLP Zcc version 3.9.0.160

- Source: https://community.zscaler.com/s/question/0D54u00009evmiACAQ/ms-company-portal-application-does-not-work-with-twlp-zcc-version-390160
- Type: Q&A
- Posted: 2022-10-10T16:27:30.000Z
- Last activity: 2024-06-07T09:19:42.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Its been observed in our environment that the company portal application fails to load for users with Tunnel with Local Proxy mode under forwarding profile
 Post we upgraded to ZCC 3.9.0.160 with tunnel with proxy forwarding mode. It seems the loop back IP used in the tunnel with proxy pac file “PROXY 127.0.0.1:9000? is not supported by MS team application. The application is stays on Sign-in page

Also if we use Tunnel 2.0 Mode there is no issue in launching the Company portal app
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmiJCAQ/express-vpn-zscaler-client","lastmod":"2023-05-31T08:44:25.000Z","id":"0D54u00009evmiJCAQ"} -->
## Express VPN + Zscaler Client

- Source: https://community.zscaler.com/s/question/0D54u00009evmiJCAQ/express-vpn-zscaler-client
- Type: Q&A
- Posted: 2022-10-03T13:27:13.000Z
- Last activity: 2023-05-31T08:44:25.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

When we are trying to run Express VPN along side Zscaler Client connector, we’ve noticed that there is an error in ZCC as **Endpoint FW/AV** Error.

When we disconnect the Express VPN, Its working fine with out any issues.

Upon checking we’ve noticed that Express VPN is assigning IP Address in 100.64.X.X series.

Is this the same IP Range used by Zscaler also which is causing this issue ?

Thanks,
 Rahul Vennu
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmiMCAQ/export-zscaler-client-connector-logs-remotely-with-powershell","lastmod":"2023-07-06T12:54:31.000Z","id":"0D54u00009evmiMCAQ"} -->
## Export Zscaler Client Connector logs remotely with PowerShell

- Source: https://community.zscaler.com/s/question/0D54u00009evmiMCAQ/export-zscaler-client-connector-logs-remotely-with-powershell
- Type: Q&A
- Posted: 2022-09-29T22:03:57.000Z
- Last activity: 2023-07-06T12:54:31.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

Is it possible to export the Zscaler Client Connector logs remotely with PowerShell? I currently have my customers manually export the logs which delays my workflow.

Thanks all for the help!
 Steve
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmiQCAQ/captive-port-detected-in-zscaler","lastmod":"2023-05-31T08:44:28.000Z","id":"0D54u00009evmiQCAQ"} -->
## Captive port detected in Zscaler

- Source: https://community.zscaler.com/s/question/0D54u00009evmiQCAQ/captive-port-detected-in-zscaler
- Type: Q&A
- Posted: 2022-09-26T19:00:49.000Z
- Last activity: 2023-05-31T08:44:28.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

When ever we are connected to that Corporate Wi-Fi, ZCC is throwing an error as “Captive Portal Detected?. we went through all the troubleshooting and configuration but still its throwing captive portal error popup
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmiTCAQ/zscaler-client-connector-showing-connecting-from-home-network","lastmod":"2024-06-19T21:46:09.000Z","id":"0D54u00009evmiTCAQ"} -->
## Zscaler Client connector Showing connecting from home network

- Source: https://community.zscaler.com/s/question/0D54u00009evmiTCAQ/zscaler-client-connector-showing-connecting-from-home-network
- Type: Q&A
- Posted: 2022-09-23T19:40:57.000Z
- Last activity: 2024-06-19T21:46:09.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Team,
 We have one user who is facing connecting issue for private access TAB on zscaler client connector when connected through wifi.
 If user connect through LAN (same network) then user not facing the issue.
 Can anyone suggest why it is happening on wifi only?
 Is it ISP issue?
 if yes then we can ask to ISP to resolve?
 or is there any bug on 3.8 version client connector?
 Thank you
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmicCAA/zcc-support-on-wsl2","lastmod":"2023-05-31T09:27:58.000Z","id":"0D54u00009evmicCAA"} -->
## ZCC support on WSL2

- Source: https://community.zscaler.com/s/question/0D54u00009evmicCAA/zcc-support-on-wsl2
- Type: Q&A
- Posted: 2022-09-19T23:21:33.000Z
- Last activity: 2023-05-31T09:27:58.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

What is the expected behaviour when you generate web traffic through WSL2 by deploying ZCC in Tunnel 1.0 and Tunnel
 2.0 packet filtering mode on Windows 10?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmigCAA/zcc-on-ipad-plane-wifi","lastmod":"2023-05-31T08:44:34.000Z","id":"0D54u00009evmigCAA"} -->
## ZCC on IPAD + plane wifi

- Source: https://community.zscaler.com/s/question/0D54u00009evmigCAA/zcc-on-ipad-plane-wifi
- Type: Q&A
- Posted: 2022-09-16T15:53:07.000Z
- Last activity: 2023-05-31T08:44:34.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Running iOS ZCC on IPAD. Users having an issue when connecting to airplane wifi.
 Iphone and clientOS works.

We SSL bypassed several of the URl’s but no change.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmiiCAA/new-3005-error-when-using-zcc-36","lastmod":"2025-08-22T07:27:09.000Z","id":"0D54u00009evmiiCAA"} -->
## New "3005" error when using > ZCC 3.6?

- Source: https://community.zscaler.com/s/question/0D54u00009evmiiCAA/new-3005-error-when-using-zcc-36
- Type: Q&A
- Posted: 2022-09-14T15:54:13.000Z
- Last activity: 2025-08-22T07:27:09.000Z
- Answers: 13
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

Who else has seen this error ? (Post windows patching)
 [wmi_error_new]

Symptoms are the same as the ZCC ‘3005’ error (WMI issue) but I thought I’ll ask the wider community.

Thank you
 G
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmimCAA/how-does-zcc-over-gre-works","lastmod":"2023-07-06T12:54:50.000Z","id":"0D54u00009evmimCAA"} -->
## How Does ZCC over GRE Works

- Source: https://community.zscaler.com/s/question/0D54u00009evmimCAA/how-does-zcc-over-gre-works
- Type: Q&A
- Posted: 2022-09-10T21:34:30.000Z
- Last activity: 2023-07-06T12:54:50.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

How Does ZCC over GRE Works ?
 we have deployed ipsec tunnel and also using ZCC with forwarding mode as TWLP.
 Will be helpful if we get easy explanation for this and not KB article
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmipCAA/webauthn-fido2-support-for-zcc","lastmod":"2024-04-05T10:24:04.000Z","id":"0D54u00009evmipCAA"} -->
## WebAuthn / FIDO2 support for ZCC

- Source: https://community.zscaler.com/s/question/0D54u00009evmipCAA/webauthn-fido2-support-for-zcc
- Type: Q&A
- Posted: 2022-09-09T14:05:33.000Z
- Last activity: 2024-04-05T10:24:04.000Z
- Answers: 5
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Does the ZCC support WebAuthn for SAML authentication with Azure MFA?
 If not, are there any plans to support it in the future?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmj7CAA/prevent-turn-off-zcc-on-ios","lastmod":"2023-07-06T12:55:26.000Z","id":"0D54u00009evmj7CAA"} -->
## Prevent turn off ZCC on iOS

- Source: https://community.zscaler.com/s/question/0D54u00009evmj7CAA/prevent-turn-off-zcc-on-ios
- Type: Q&A
- Posted: 2022-09-01T11:48:18.000Z
- Last activity: 2023-07-06T12:55:26.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi everyone,
 Is it possible?

Thank you!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmj8CAA/sharepoint-401-unauthorize-error-on-tunnel-20","lastmod":"2023-07-06T12:55:25.000Z","id":"0D54u00009evmj8CAA"} -->
## Sharepoint 401 unauthorize error on Tunnel 2.0

- Source: https://community.zscaler.com/s/question/0D54u00009evmj8CAA/sharepoint-401-unauthorize-error-on-tunnel-20
- Type: Q&A
- Posted: 2022-08-31T13:01:03.000Z
- Last activity: 2023-07-06T12:55:25.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Altab_Khan,
 have you solved this?
 Now, we have the same issue.
 Best regards
 Andreas
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjACAQ/deployment-with-strict-enforcement-switch-creating-issues","lastmod":"2023-05-31T08:44:46.000Z","id":"0D54u00009evmjACAQ"} -->
## Deployment with Strict Enforcement switch creating issues!

- Source: https://community.zscaler.com/s/question/0D54u00009evmjACAQ/deployment-with-strict-enforcement-switch-creating-issues
- Type: Q&A
- Posted: 2022-08-30T00:11:38.000Z
- Last activity: 2023-05-31T08:44:46.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are deploying the ZCC with strict enforcement switch On via SCCM/Tanium and its working as expected but sometimes we faced issues when user is not logged in properly with IWA and Internet stops working and IT team is not able to take remote etc…to solve the problem and its a outage as well for end user. Although we have bypassed the Teams app from ZCC still sometimes it not easy to troubleshoot with teams.

Is there any way where we can deploy ZCC first without Strict enforcement switch and then enable it later with some options from GPO etc…?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjCCAQ/client-connector-internal-dns-resolution-in-machine-tunnel-mode","lastmod":"2023-07-06T11:43:38.000Z","id":"0D54u00009evmjCCAQ"} -->
## Client Connector - internal DNS resolution in machine tunnel mode

- Source: https://community.zscaler.com/s/question/0D54u00009evmjCCAQ/client-connector-internal-dns-resolution-in-machine-tunnel-mode
- Type: Q&A
- Posted: 2022-08-29T10:02:58.000Z
- Last activity: 2023-07-06T11:43:38.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello community!

Our Windows Autopilot client deployment relies on the machine tunnel connectivity to our AD controllers hosted on Azure.

It has turned out that once the Zscaler client connector is deployed via Intune during the Windows setup, the machine tunnel is successfully established (app package is deployed with the machine provisioning key), but the internal domain is not resolved.

Result: Windows Autopilot deployment is stuck in the “Enrollment Status Page? Stage 3, where the device is trying to join the on-premises domain.

Eventlog:

```
Pre-verification tasks for automatic device joining have been completed. NO join can be performed for the device because no domain controller was found. The device must be connected to a network that is connected to an Active Directory domain controller.
```

Let’s assume our internal domain is company.lan where the domain is added to the search domains in ZPA.

What I’ve tested so far (and whats really strange IMHO):

> nslookup company.lan ? fails
 ping company.lan ? fails (should be resolved by Zscaler AFAIK)
>
> ping ad01.company.lan ? success (ICMP response when using a FQDN)
>

What I didn’t get is, why DNS resolution using a FQDN is working and resolving the domain itself is not.

Some screenshots for further clarification:

[[image] image1610×484 24.7 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O7b5AAC)

The required servers that needs to be contacted are added by IP and FQDN in the application segment.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjFCAQ/zcc-manual-installation-in-windows-and-mac-machines","lastmod":"2023-07-06T12:55:42.000Z","id":"0D54u00009evmjFCAQ"} -->
## ZCC manual installation in Windows and Mac Machines

- Source: https://community.zscaler.com/s/question/0D54u00009evmjFCAQ/zcc-manual-installation-in-windows-and-mac-machines
- Type: Q&A
- Posted: 2022-08-25T20:07:42.000Z
- Last activity: 2023-07-06T12:55:42.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi, Just wanted to check if we manually install the ZCC in Windows or Mac machine then do we need to install the Zscaler root certificate manually in store or in browser?

for Mac we installed the ZCC manually and was getting error for all sites after installing the Zscaler Root certificate in browser and trusted then it worked fine.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjHCAQ/how-does-zcc-control-traffic-on-end-device","lastmod":"2023-05-31T08:44:46.000Z","id":"0D54u00009evmjHCAQ"} -->
## How does ZCC control traffic on end device?

- Source: https://community.zscaler.com/s/question/0D54u00009evmjHCAQ/how-does-zcc-control-traffic-on-end-device
- Type: Q&A
- Posted: 2022-08-25T02:16:57.000Z
- Last activity: 2023-05-31T08:44:46.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

I’m having trouble understanding how client connector direct traffic to zscaler cloud. I read somewhere that it leverages Pac file to do so, but my understanding is that Pac file only applies to web browser traffic.

Any clarification on this topic would be helpful.

Best,
 Steven
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjICAQ/deception-in-zcc","lastmod":"2023-05-31T08:44:07.000Z","id":"0D54u00009evmjICAQ"} -->
## Deception in ZCC

- Source: https://community.zscaler.com/s/question/0D54u00009evmjICAQ/deception-in-zcc
- Type: Q&A
- Posted: 2022-08-23T12:17:51.000Z
- Last activity: 2023-05-31T08:44:07.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Can someone let me know the Windows ZCC release version that will have the Deception landmines built in?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjKCAQ/script-error-zcc-client","lastmod":"2023-07-06T11:43:44.000Z","id":"0D54u00009evmjKCAQ"} -->
## Script error ZCC Client

- Source: https://community.zscaler.com/s/question/0D54u00009evmjKCAQ/script-error-zcc-client
- Type: Q&A
- Posted: 2022-08-22T14:09:10.000Z
- Last activity: 2023-07-06T11:43:44.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

[[Script error.PNG] Script error.PNG785×582 44.5 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O67OAAS)

When ZCC client pushed from SCCM. Initial setup it shows an error failed load an external resource.

please find error screenshot as attached

Quick response is very much appreciated
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjMCAQ/zscaler-client-connector-no-display","lastmod":"2023-07-06T11:43:45.000Z","id":"0D54u00009evmjMCAQ"} -->
## ZSCALER CLIENT CONNECTOR no display

- Source: https://community.zscaler.com/s/question/0D54u00009evmjMCAQ/zscaler-client-connector-no-display
- Type: Q&A
- Posted: 2022-08-19T05:06:17.000Z
- Last activity: 2023-07-06T11:43:45.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Zscaler client connector no display even after reinstallation, restart PC clear cache in IE.

[[MicrosoftTeams-image (1)] MicrosoftTeams-image (1)765×575 13 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O5VFAA0)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjqCAA/about-the-need-of-client-connector","lastmod":"2023-07-06T11:43:48.000Z","id":"0D54u00009evmjqCAA"} -->
## About the need of Client Connector

- Source: https://community.zscaler.com/s/question/0D54u00009evmjqCAA/about-the-need-of-client-connector
- Type: Q&A
- Posted: 2022-08-09T14:52:30.000Z
- Last activity: 2023-07-06T11:43:48.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello ,
 Sorry We are beginners and have a question about the Zscaler solution.
 We want to test in ZIA the SWG and the DLP.
 at this point do we need to deploy the Client Connector on users device?
 Witch component or feature of ZIA need this Client Connector as preriquisite to work ?
 Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjtCAA/some-users-in-zia-admin-portal-not-showing-in-zcc-portal","lastmod":"2026-02-12T08:23:25.000Z","id":"0D54u00009evmjtCAA"} -->
## Some Users in ZIA admin portal not showing in ZCC portal

- Source: https://community.zscaler.com/s/question/0D54u00009evmjtCAA/some-users-in-zia-admin-portal-not-showing-in-zcc-portal
- Type: Q&A
- Posted: 2022-08-08T11:50:19.000Z
- Last activity: 2026-02-12T08:23:25.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi everyone,

Not sure what is happening and if anyone have had a similar issue.

I just realized that some of the accounts created in Azure AD and currently available in my ZIA admin portal are not available in Zscaler Client Connector App portal.

I thought it was provisioning issue between Azure AD and Zscaler, but since the users are available under users management > users, i am sure it is not.

Does anyone have an idea what could be going on and how i can resolve this?

Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmk1CAA/zcc-deployment-on-ios-not-blocking-restricted-websites","lastmod":"2023-05-31T09:29:39.000Z","id":"0D54u00009evmk1CAA"} -->
## ZCC Deployment on iOS not Blocking Restricted Websites

- Source: https://community.zscaler.com/s/question/0D54u00009evmk1CAA/zcc-deployment-on-ios-not-blocking-restricted-websites
- Type: Q&A
- Posted: 2022-08-04T09:13:27.000Z
- Last activity: 2023-05-31T09:29:39.000Z
- Answers: 14
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

I recently deployed ZCC Application to our cooperate iPhones. Today I decided to attempt accessing some of the restricted websites, to my surprise, I was able to access them on my work iPhone without them being blocked by Zscaler. It is worth mentioning that these apps are blocked by Zscaler when accessed using our cooperate laptops.

Is there something I did not do right?

The deployment was done using this guide
 [[Guide] Deploy Zscaler Client Connector with Intune (iOS & Android)](https://community.zscaler.com/t/guide-deploy-zscaler-client-connector-with-intune-ios-android/9123)

Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmk3CAA/autoconnect-after-zcc-deployed","lastmod":"2023-07-06T12:56:25.000Z","id":"0D54u00009evmk3CAA"} -->
## Autoconnect After ZCC Deployed

- Source: https://community.zscaler.com/s/question/0D54u00009evmk3CAA/autoconnect-after-zcc-deployed
- Type: Q&A
- Posted: 2022-08-03T13:39:20.000Z
- Last activity: 2023-07-06T12:56:25.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Everyone,

Has anyone deployed ZCC and once the application is installed it automatically logs the user in?

Dhaval
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmk6CAA/is-it-mantory-to-have-zapp-v20-for-zscaler-dlp","lastmod":"2023-05-31T08:45:04.000Z","id":"0D54u00009evmk6CAA"} -->
## Is it mantory to have ZAPP v2.0 for Zscaler DLP

- Source: https://community.zscaler.com/s/question/0D54u00009evmk6CAA/is-it-mantory-to-have-zapp-v20-for-zscaler-dlp
- Type: Q&A
- Posted: 2022-08-02T14:41:16.000Z
- Last activity: 2023-05-31T08:45:04.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi ,

I would like to know if ZAPP v2.0 is mandatory for Zscaler DLP ?

Thanks.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmkCCAQ/forward-traffic-from-zia-zcc-to-cisco-anyconnect-client","lastmod":"2023-05-31T09:29:40.000Z","id":"0D54u00009evmkCCAQ"} -->
## Forward Traffic from ZIA (ZCC) to Cisco AnyConnect Client

- Source: https://community.zscaler.com/s/question/0D54u00009evmkCCAQ/forward-traffic-from-zia-zcc-to-cisco-anyconnect-client
- Type: Q&A
- Posted: 2022-07-27T12:27:25.000Z
- Last activity: 2023-05-31T09:29:40.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Everyone,

We are currently implementing ZIA company wide. One issue we are having is trying to off load traffic from ZIA to Cisco AnyConnect Client for certain domains. In the PAC File I have configured following settings

```
/* Redirect traffic to vpn
   if (shExpMatch(host, “gs1us.XXX?) || shExpMatch(host, “*.gs1us.XXX?)) ||
      (shExpMatch(host, “windows.XXX?) || shExpMatch(host, “*.windows.XXX?))
    return "50.236.XXX.XXX"; < vpn gateway ip address
```

We could see that traffic for domain .gs1us.org is routing the the vpn ip address but [windows.net](http://windows.net) is still going over Zscaler ip address vs cisco AnyConnect client.

Please see below continuation of this post
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmkDCAQ/zcc-tunnel-20-with-f5-gui-app-tunnel","lastmod":"2023-07-06T12:56:18.000Z","id":"0D54u00009evmkDCAQ"} -->
## ZCC Tunnel 2.0 with F5 GUI APP Tunnel

- Source: https://community.zscaler.com/s/question/0D54u00009evmkDCAQ/zcc-tunnel-20-with-f5-gui-app-tunnel
- Type: Q&A
- Posted: 2022-07-26T20:40:43.000Z
- Last activity: 2023-07-06T12:56:18.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

I am trying to deploy ZCC on win 10 and Tunnel 2.0 for Trusted, VPN and Off Trusted network.

- Using FWD PAC File for exceptions to be bypassed from ZCC
- Respective entry in APP PAC File to send it either DIRECT or any other proxy.
- Using separate FWD PAC File for Trusted ( LAN, SPLIT, FULL VPN)
- Another FWD PAC File for Off trusted Network.
- Trusted Network identification is based on DNS Server IP when on LAN/VPN.

All Works great, except I may have a unique use case where:

1. User is on LAN or VPN
2. Connects to F5 GUI App Tunnel - This is available on internal network only - so Off trusted network is out of this issue.
3. Certain Internal Web apps are open from F5 APP Tunnel Source IP
4. App Tunnel connects fine over https. So now user is on LAN + F5 APP Tunnel(only for internal web apps) + ZCC 2.0 tunnel or User is on VPN + F5 APP Tunnel(only for internal web apps) + ZCC 2.0 tunnel But any web application (browser based) which is routed through that F5 tunnel fails with **ERR_EMPTY_RESPONSE** on all browser that uses system proxy.
5. Web apps through F5 App tunnel works on Firefox under **Auto detect proxy or No proxy** - fails if used system proxy option.
6. At L3 syslog, client only connects to F5 App Tunnel IP on port 443, ZCC logs does show actual IP that client is trying to reach through the tunnel.
7. SSL Bypass in place, Private IP bypass in place.
8. ZCC Logs shows **Error: Application Exception - Both Client and Server sockets are closed**

Has anyone had similar use case or issue ?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmkHCAQ/tenant-restriction-tunnel-10","lastmod":"2023-05-31T08:45:10.000Z","id":"0D54u00009evmkHCAQ"} -->
## Tenant Restriction -- Tunnel 1.0

- Source: https://community.zscaler.com/s/question/0D54u00009evmkHCAQ/tenant-restriction-tunnel-10
- Type: Q&A
- Posted: 2022-07-25T11:55:29.000Z
- Last activity: 2023-05-31T08:45:10.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Will it be possible to enable Tenant Restriction on Z-Tunnel 1.0 ?

0r

Z-Tunnel 2.0 is mandatory ?

Thanks,
 Rahul
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmkNCAQ/zcc-and-onedrive-issues","lastmod":"2023-05-31T08:13:11.000Z","id":"0D54u00009evmkNCAQ"} -->
## ZCC and OneDrive Issues

- Source: https://community.zscaler.com/s/question/0D54u00009evmkNCAQ/zcc-and-onedrive-issues
- Type: Q&A
- Posted: 2022-07-20T20:17:27.000Z
- Last activity: 2023-05-31T08:13:11.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Currently we have several users when they have Internet Security turned to on and trying to sign-in to their OneDrive we get an error back saying we could not sign you in.

When turning off\disabling ZCC the user is able to sign into OneDrive without issues.

We have:

O365 One Click config enabled
 Added OneDrive to SSL bypass policies
 Added some URLs to the PAC file, app profile for ZCC in bypass

Users are still having issues while the ZCC is enabled.

Has anyone else had this issue with OneDrive?

Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmkOCAQ/zscaler-client-connector-portal-as-idp-error","lastmod":"2023-07-06T11:43:57.000Z","id":"0D54u00009evmkOCAQ"} -->
## Zscaler Client Connector Portal as IdP Error

- Source: https://community.zscaler.com/s/question/0D54u00009evmkOCAQ/zscaler-client-connector-portal-as-idp-error
- Type: Q&A
- Posted: 2022-07-20T10:13:11.000Z
- Last activity: 2023-07-06T11:43:57.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Dear Community,

I have configured the Zscaler client connector portal as IDP feature but during the authentication, I am receiving an error like shown below. Does anyone use this feature and have any insight about it?

help.zscaler.com
##### [Using Zscaler Client Connector Portal as an Identity Provider | Zscaler](https://help.zscaler.com/z-app/using-zscaler-app-portal-identity-provider)

How to configure the Zscaler Client Connector Portal to function as an identity provider for the Zscaler service.

[[image] image741×555 13.9 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O7ZiAAK)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmkPCAQ/recommended-version-of-windows-11-by-zcc-32bit-or-64bit","lastmod":"2023-05-31T08:45:14.000Z","id":"0D54u00009evmkPCAQ"} -->
## Recommended version of Windows 11 by ZCC 32bit or 64bit

- Source: https://community.zscaler.com/s/question/0D54u00009evmkPCAQ/recommended-version-of-windows-11-by-zcc-32bit-or-64bit
- Type: Q&A
- Posted: 2022-07-19T12:23:25.000Z
- Last activity: 2023-05-31T08:45:14.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I am currently updating from windows 10 to windows 11.
 Currently, ZCC 3.6 32-bit version is used.
 I wonder if it is necessary to update ZCC to be used in Windows 11 from 32bit to 64bit.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmkQCAQ/ztunnel-20-pac-file-traffic-forwarding-to-cen-based-on-origin-country","lastmod":"2023-07-06T12:57:00.000Z","id":"0D54u00009evmkQCAQ"} -->
## Z-Tunnel 2.0 PAC File - Traffic forwarding to CEN based on origin country

- Source: https://community.zscaler.com/s/question/0D54u00009evmkQCAQ/ztunnel-20-pac-file-traffic-forwarding-to-cen-based-on-origin-country
- Type: Q&A
- Posted: 2022-07-18T12:25:22.000Z
- Last activity: 2023-07-06T12:57:00.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

In this scenario, how do we do if we want to forward traffic to a specific Zscaler node when it’s coming from a specific country?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmkRCAQ/api-access-to-zscaler-client-connector-portal","lastmod":"2023-07-06T12:57:05.000Z","id":"0D54u00009evmkRCAQ"} -->
## API access to Zscaler Client Connector Portal

- Source: https://community.zscaler.com/s/question/0D54u00009evmkRCAQ/api-access-to-zscaler-client-connector-portal
- Type: Q&A
- Posted: 2022-07-15T15:44:59.000Z
- Last activity: 2023-07-06T12:57:05.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I have got access to the ZCC Public API and have been sent the (beta) document detailing the API use. But I’m struggling with using the forceRemoveDevices method.
 Specifically the syntax of the “deviceRemoveContract? attribute.

Anyone used this and can provide advice? I have a support call logged, but nothing useful from there just yet.

Thanks in advance
 N.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmkTCAQ/o365-connection-issues-using-tunnel-10","lastmod":"2024-10-07T05:57:51.000Z","id":"0D54u00009evmkTCAQ"} -->
## O365 Connection issues using Tunnel 1.0

- Source: https://community.zscaler.com/s/question/0D54u00009evmkTCAQ/o365-connection-issues-using-tunnel-10
- Type: Q&A
- Posted: 2022-07-15T01:31:29.000Z
- Last activity: 2024-10-07T05:57:51.000Z
- Answers: 9
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Nicely said,

I can absolutely spin up another post for these discussions, as I am sure others are having the same issues as us.

Thanks!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmkoCAA/client-connector-authentication-against-azure-ad-when-multiple-accounts-are-used","lastmod":"2023-07-06T12:57:30.000Z","id":"0D54u00009evmkoCAA"} -->
## Client connector authentication against Azure AD when Multiple accounts are used

- Source: https://community.zscaler.com/s/question/0D54u00009evmkoCAA/client-connector-authentication-against-azure-ad-when-multiple-accounts-are-used
- Type: Q&A
- Posted: 2022-07-10T21:07:09.000Z
- Last activity: 2023-07-06T12:57:30.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Guys,

We currently have a requirement to deploy Zscaler client connectors to 3rd party vendors to support some of the systems/apps they support in our environment. During pilot one of the issues I’ve seen is when vendor try to sign in to client connector with their username, they automatically redirected to their own Azure AD tenant to authenticate, rather than redirected to Azure AD SSO sign in page or giving an option to select an account when multiple azure accounts are used.

Has anyone come across similar challages and find a solution, would customizing client connector package help in this scenario?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmkrCAA/zscaler-auth-client-cert-enforcement","lastmod":"2023-07-06T12:52:28.000Z","id":"0D54u00009evmkrCAA"} -->
## ZScaler auth - client cert enforcement

- Source: https://community.zscaler.com/s/question/0D54u00009evmkrCAA/zscaler-auth-client-cert-enforcement
- Type: Q&A
- Posted: 2022-06-30T16:06:41.000Z
- Last activity: 2023-07-06T12:52:28.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is it possible to enforce client certificates when authenticating to ZIA/ZPA? I am finding that I can bypass some security controls by installing the Client Connector inside of a VM and connecting to the network. I cant find anything in the documentation about enforcing client certificates for authenticating. The Client Connector is configured with a Pre Login Machine Tunnel, so somehow i am able to register a new machine with ZScaler? Can anyone help me with this? I want to prevent people from signing in to ZScaler from personal devices.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmksCAA/machine-tunnel-and-app-profiles","lastmod":"2025-08-22T07:27:12.000Z","id":"0D54u00009evmksCAA"} -->
## Machine Tunnel and App Profiles

- Source: https://community.zscaler.com/s/question/0D54u00009evmksCAA/machine-tunnel-and-app-profiles
- Type: Q&A
- Posted: 2022-06-29T16:57:10.000Z
- Last activity: 2025-08-22T07:27:12.000Z
- Answers: 11
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

A couple questions:

User A has Machine Tunnel enabled in their App Profile, logs into a machine, and the machine is then enrolled in Machine Tunnel. Then, User B logs into the same machine, but does NOT have Machine Tunnel enabled in their App Profile. How does that effect Machine Tunnel on that machine? (I’ve done some limited testing with this and it appears the machine remains enrolled in Machine Tunnel even if a different user logs in that does NOT have Machine Tunnel in their app profile, but I just wanted to confirm this is expected behavior as we have a use case for this functionality.)

Also, if we specify an App Profile during ZCC installation, and a user with a different App Profile logs into the machine, does the ZCC then use that user’s App Profile instead?

Thanks.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmkvCAA/how-zcc-checks-for-vpn-trusted-network","lastmod":"2023-05-31T08:13:15.000Z","id":"0D54u00009evmkvCAA"} -->
## How ZCC checks for VPN Trusted network?

- Source: https://community.zscaler.com/s/question/0D54u00009evmkvCAA/how-zcc-checks-for-vpn-trusted-network
- Type: Q&A
- Posted: 2022-06-27T20:29:20.000Z
- Last activity: 2023-05-31T08:13:15.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

How ZCC checks for VPN Trusted network ?

How does it check these requirements:

The app does not consider the network a VPN trusted network and treats the user as connected to an Off Trusted Network in the following scenarios:

- The VPN doesn’t install a default route and uses some other mechanism to capture all of the user’s traffic.
- The default interface description does not contain the words Cisco, Juniper, Fortinet, PanGP, or VPN.
- The VPN runs in split-tunnel mode, so that the app takes only some of the user traffic. The VPN can do this by installing routes only for some subnets (e.g., 10/8 or 192.168/16) or by installing a DNS on the device to resolve specific requests.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evml2CAA/how-to-use-fiddler-to-capture-zcc-traffic","lastmod":"2023-05-31T08:45:28.000Z","id":"0D54u00009evml2CAA"} -->
## How to use fiddler to capture ZCC traffic?

- Source: https://community.zscaler.com/s/question/0D54u00009evml2CAA/how-to-use-fiddler-to-capture-zcc-traffic
- Type: Q&A
- Posted: 2022-06-22T11:38:09.000Z
- Last activity: 2023-05-31T08:45:28.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,
 I woud like to test idp integration with Azure AD and I can’t find a way to do so except with fiddler.
 but when Itried I cant find ZCC in winconfig in fiddler , I’m using ‘‘tunnel with local proxy’’ and I found on Zscaler help that I should create a custom app profile is that necessary?
 If yes how exactly should I do that?
 Hope you can help me.
 Thank you.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evml3CAA/zcc-version-upgrade","lastmod":"2023-05-31T09:26:30.000Z","id":"0D54u00009evml3CAA"} -->
## ZCC version upgrade

- Source: https://community.zscaler.com/s/question/0D54u00009evml3CAA/zcc-version-upgrade
- Type: Q&A
- Posted: 2022-06-22T08:42:06.000Z
- Last activity: 2023-05-31T09:26:30.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

We are planning to upgrade the ZCC version. Currently we have 3.5.0.108 version installed on most of the devices. We have disabled auto-update and all the upgradation is done via Intune.

1. What is the latest stable ZCC version to work with?
2. We are planning to test it on couple of users first, how long should we do the observation to consider it stably working ?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evml7CAA/enable-zcc-crash-logs-without-gui","lastmod":"2023-07-06T12:57:56.000Z","id":"0D54u00009evml7CAA"} -->
## Enable ZCC crash logs without GUI

- Source: https://community.zscaler.com/s/question/0D54u00009evml7CAA/enable-zcc-crash-logs-without-gui
- Type: Q&A
- Posted: 2022-06-21T14:44:12.000Z
- Last activity: 2023-07-06T12:57:56.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

One of my customers uses ZScaler Client Connector for remote access.
 I installed a new Win 10 system and installed Zscaler Client Connector.
 I can dial in, Zscaler is working fine. After a reboot, Zscaler will no longer open up, though. No tray icon.

I believe that the ZScaler Client Connector keeps on crashing. I would like to enable the crash log, but it seems I can only do that from the GUI. But I can’t access the GUI.

Is there any way, maybe through the registry, how I can enable the Zscaler Client Connector crash log?
 ZScaler Client Connector 3.6.0.26_V3222
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evml8CAA/how-to-zcc-version-from-mobile-portal","lastmod":"2023-07-06T12:57:59.000Z","id":"0D54u00009evml8CAA"} -->
## How to Zcc version from Mobile portal

- Source: https://community.zscaler.com/s/question/0D54u00009evml8CAA/how-to-zcc-version-from-mobile-portal
- Type: Q&A
- Posted: 2022-06-21T13:43:19.000Z
- Last activity: 2023-07-06T12:57:59.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

How can we check which user has which ZCC version from Mobile Portal. In my environment, we have disabled auto-update.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evml9CAA/ios-devices-automatically-having-vpn-disabled","lastmod":"2023-10-17T06:39:59.000Z","id":"0D54u00009evml9CAA"} -->
## iOS devices automatically having VPN disabled

- Source: https://community.zscaler.com/s/question/0D54u00009evml9CAA/ios-devices-automatically-having-vpn-disabled
- Type: Q&A
- Posted: 2022-06-20T14:18:25.000Z
- Last activity: 2023-10-17T06:39:59.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Good Day. We are in the process of setting up the zScaler Client Connector for MDM in a corporate device scenario. We have implemented configuration profiles for forwarding, as well as the http proxy which pushes down a PAC file. All works well once the user gets the zScaler connector app and registers their device after enrolling in MDM.

The problem we are seeing is the VPN slider within iOS settings is turning itself off at some point. It appears to consistently break overnight, after getting up in the morning a device will show all notifications come in, but the VPN services/slider is turned off. The user needs to enable the VPN slider each morning (Or open the zScaler app) to restore services. Otherwise all traffic to any app or website is completely disabled.

Does anyone know where a control or setting would be to cause iOS devices to automatically shut down VPN services?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmlHCAQ/zcc-for-linux-vpn-support","lastmod":"2023-05-31T08:13:15.000Z","id":"0D54u00009evmlHCAQ"} -->
## ZCC for Linux - VPN Support

- Source: https://community.zscaler.com/s/question/0D54u00009evmlHCAQ/zcc-for-linux-vpn-support
- Type: Q&A
- Posted: 2022-06-16T10:18:30.000Z
- Last activity: 2023-05-31T08:13:15.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

Can we have the list of supported Full Tunnel VPN Applications for Ubuntu OS with Zscaler?

Thanks,
 Rahul
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmlLCAQ/issue-zsatunnel-data-consumption","lastmod":"2023-05-31T08:45:32.000Z","id":"0D54u00009evmlLCAQ"} -->
## Issue - ZSATunnel data consumption

- Source: https://community.zscaler.com/s/question/0D54u00009evmlLCAQ/issue-zsatunnel-data-consumption
- Type: Q&A
- Posted: 2022-06-09T18:38:44.000Z
- Last activity: 2023-05-31T08:45:32.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I will soon be working in an environment with limited data and am therefore becoming conscious of my work computer’s data usage. ZSATunnel is by far the largest data consumer, generally triple that of the Teams app after hours of video calls. Is there any way to reduce the amount of data consumed by the Tunnel?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmlNCAQ/microsoft-forms-is-not-working-with-zcc","lastmod":"2023-05-31T08:13:15.000Z","id":"0D54u00009evmlNCAQ"} -->
## Microsoft Forms is Not working with ZCC

- Source: https://community.zscaler.com/s/question/0D54u00009evmlNCAQ/microsoft-forms-is-not-working-with-zcc
- Type: Q&A
- Posted: 2022-06-08T11:06:24.000Z
- Last activity: 2023-05-31T08:13:15.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Dear Team,

We are unable to open Microsoft Form(Polls) with Zscaler client Connector. But the same thing is working with Browser base outlook.

We are using tunnel 2.0 and we have enabled o365 one click mechanism.

Kindly help me on it.

Thank You,
 Malik
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmlOCAQ/zcc-for-countries-without-hubs","lastmod":"2023-05-31T08:45:35.000Z","id":"0D54u00009evmlOCAQ"} -->
## ZCC for countries without Hubs

- Source: https://community.zscaler.com/s/question/0D54u00009evmlOCAQ/zcc-for-countries-without-hubs
- Type: Q&A
- Posted: 2022-06-08T02:04:19.000Z
- Last activity: 2023-05-31T08:45:35.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi, we are starting to use ZCC for our users. Users in countries where there’s no hubs feedback that access to the local country website is slow. Understand that this is due to the traffic going to the nearest hubs. Is there other ways to allow faster access to the local country website
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmlPCAQ/bypassing-traffic-in-forwarding-profile-pac-vs-app-profile-pac","lastmod":"2023-05-31T08:45:35.000Z","id":"0D54u00009evmlPCAQ"} -->
## Bypassing traffic in Forwarding Profile PAC vs App Profile PAC

- Source: https://community.zscaler.com/s/question/0D54u00009evmlPCAQ/bypassing-traffic-in-forwarding-profile-pac-vs-app-profile-pac
- Type: Q&A
- Posted: 2022-06-07T13:31:56.000Z
- Last activity: 2023-05-31T08:45:35.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Per the guidance [here](https://help.zscaler.com/z-app/best-practices-using-pac-files-zscaler-app) we have configured and tested using both a PAC in the ZCC Forwarding Profile and a PAC in the ZCC App Profile. I understand that the Forwarding PAC routes traffic to the ZCC and the App Profile PAC routes traffic to the Zscaler cloud. My question is, if we want to completely bypass a destination from Zscaler, does it matter which PAC we enter the bypass information into? If I understand correctly, adding the bypass to the Forwarding PAC would be the most “complete? bypass since it would bypass the ZCC and cloud both. Would there be any reason to allow the traffic to be routed to the ZCC but then bypassed from Zscaler cloud?

Also, we are using Tunnel 2.0 if that factors into the question.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmlQCAQ/user-privacy-for-zcc-on-android","lastmod":"2023-05-31T08:13:15.000Z","id":"0D54u00009evmlQCAQ"} -->
## User privacy for ZCC on Android

- Source: https://community.zscaler.com/s/question/0D54u00009evmlQCAQ/user-privacy-for-zcc-on-android
- Type: Q&A
- Posted: 2022-06-06T10:53:20.000Z
- Last activity: 2023-05-31T08:13:15.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi.

Asking about the user privacy of our ZCC when testing on Android due to the access it asks for.
 One specific question we need help answering is to understand what permissions ZCC require on Android and **why** does it need each one?

Thank you
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmlRCAQ/zscaler-client-connector-version-38-now-can-use-dtls-for-tunnel-type-20","lastmod":"2023-07-06T12:58:17.000Z","id":"0D54u00009evmlRCAQ"} -->
## Zscaler client connector version 3.8 now can use DTLS for tunnel type 2.0!

- Source: https://community.zscaler.com/s/question/0D54u00009evmlRCAQ/zscaler-client-connector-version-38-now-can-use-dtls-for-tunnel-type-20
- Type: Q&A
- Posted: 2022-06-06T10:35:31.000Z
- Last activity: 2023-07-06T12:58:17.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

This is great news as DTLS like IPsec has better performance than TLS, especially for voip traffic. Hope the ZPA tunnel will have soon such option! [About Z-Tunnel 1.0 & Z-Tunnel 2.0 | Zscaler](https://help.zscaler.com/z-app/about-z-tunnel-1.0-z-tunnel-2.0)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmlTCAQ/zcc-installation-with-strictenforcement-make-browser-proxy-settings-greyed-out","lastmod":"2023-07-06T12:58:16.000Z","id":"0D54u00009evmlTCAQ"} -->
## ZCC installation with STRICTENFORCEMENT make browser proxy settings greyed out?

- Source: https://community.zscaler.com/s/question/0D54u00009evmlTCAQ/zcc-installation-with-strictenforcement-make-browser-proxy-settings-greyed-out
- Type: Q&A
- Posted: 2022-06-05T19:57:47.000Z
- Last activity: 2023-07-06T12:58:16.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Installing ZCC with STRICTENFORCEMENT option,
 a. will set forward profile PAC file to browser?
 b. browser proxy setting will be greyed out?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmlrCAA/how-to-uninstall-zcc","lastmod":"2023-07-06T12:53:20.000Z","id":"0D54u00009evmlrCAA"} -->
## How to uninstall ZCC

- Source: https://community.zscaler.com/s/question/0D54u00009evmlrCAA/how-to-uninstall-zcc
- Type: Q&A
- Posted: 2022-06-01T15:09:56.000Z
- Last activity: 2023-07-06T12:53:20.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Team, the subscription for our company has expired. The users got logged out of the ZCC before we could uninstall the app from their device. Now the major challenge we are facing is that we are not able to uninstalled the ZCC from the machines because we are not aware of the uninstall password. Now if we use the new logout password it doesn’t get synchronized with the users as they are already logged out and ZCC is not communicating with the portal and a one-time password is not an option because we have more than 500 machines from where we need to uninstall the Zapp.

Is there any other way through which we can uninstall the app in such situation?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmlwCAA/zt2-protocol-settings-override","lastmod":"2023-05-31T08:45:38.000Z","id":"0D54u00009evmlwCAA"} -->
## ZT2 protocol settings override

- Source: https://community.zscaler.com/s/question/0D54u00009evmlwCAA/zt2-protocol-settings-override
- Type: Q&A
- Posted: 2022-05-25T10:50:32.000Z
- Last activity: 2023-05-31T08:45:38.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Does anyone know what the feature ‘Allow end-users to override Z-Tunnel 2.0 protocol settings’ (coming with ZS Client Connector Portal v3.20 and depending on ZCC v3.8.0+) does or should do exactly?
 I can’t notice any visible difference anywhere in between having this active or not - currently testing with ZCC 3.8.0.100
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmlzCAA/zcc-zia-disabled-logs","lastmod":"2023-12-04T14:00:50.000Z","id":"0D54u00009evmlzCAA"} -->
## ZCC ZIA Disabled Logs

- Source: https://community.zscaler.com/s/question/0D54u00009evmlzCAA/zcc-zia-disabled-logs
- Type: Q&A
- Posted: 2022-05-23T12:01:08.000Z
- Last activity: 2023-12-04T14:00:50.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is there logs(from Admin Portal) for when a user disables Zscaler Client Connector ZIA.

The point is to setup alerting when someone disables ZCC or even track who disabled it and when.

If someone acheived this using 3rd party tools please share
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmm1CAA/how-does-zcc-knows-whether-to-redirect-traffic-to-go-through-zia-or-zpa","lastmod":"2023-05-31T09:26:37.000Z","id":"0D54u00009evmm1CAA"} -->
## How does ZCC knows whether to redirect traffic to go through ZIA or ZPA?

- Source: https://community.zscaler.com/s/question/0D54u00009evmm1CAA/how-does-zcc-knows-whether-to-redirect-traffic-to-go-through-zia-or-zpa
- Type: Q&A
- Posted: 2022-05-19T17:04:53.000Z
- Last activity: 2023-05-31T09:26:37.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi. New to Zscaler. I’m using ZCC with both ZIA and ZPA enabled, with Tunnel 2.0 and Packet Filter enabled for both. The Forwarding Profile Actions for both ZIA and ZPA is “Tunnel? for Off Trusted Network. My question is how does ZCC decides where to redirect (i.e. via LWF) traffic to go through either ZIA or ZPA? What is the process flow for this?

I read through tons of documentation but couldn’t figure this out. Is there any tool that we can use to check the behavior?

Thanks in advance.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmm3CAA/captive-portal-detected-error-in-zcc","lastmod":"2023-07-06T12:59:06.000Z","id":"0D54u00009evmm3CAA"} -->
## Captive Portal Detected -- Error in ZCC

- Source: https://community.zscaler.com/s/question/0D54u00009evmm3CAA/captive-portal-detected-error-in-zcc
- Type: Q&A
- Posted: 2022-05-18T03:35:04.000Z
- Last activity: 2023-07-06T12:59:06.000Z
- Answers: 11
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We have a situation where our users are working from a corporate office in Malaysia.

When ever they are connected to that Corporate Wi-Fi, ZCC is throwing an error as “Captive Portal Detected? & after some time users are losing the Internet Access.

We also verified that there is no Authentication required on that WiFi network once they are connected. We have also added the DNS Servers IPs in our Forwarding Profile to make ZCC understand that its a trusted network & to go DISABLED.

Even though the ZCC Status is showing as **Trusted Network**, but still Captive Portal Error is throwing.

**Note: [gateway.zscaler.net](http://gateway.zscaler.net) & [pac.zscaler.net](http://pac.zscaler.net) is not reachable from that Corporate Wi-Fi Network. We are unable to ping both from that corporate Wi-Fi Network.**

We would required some suggestions on this issue.

Thanks,
 Rahul V
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmm5CAA/mobileiron-deployment-pac","lastmod":"2023-05-31T08:13:20.000Z","id":"0D54u00009evmm5CAA"} -->
## MobileIron Deployment PAC

- Source: https://community.zscaler.com/s/question/0D54u00009evmm5CAA/mobileiron-deployment-pac
- Type: Q&A
- Posted: 2022-05-17T18:44:32.000Z
- Last activity: 2023-05-31T08:13:20.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Deploying ZCC to iOS using MobileIron. Using Global HTTP Proxy Policy to block all traffic until ZCC is open and the user logs in to configure the VPN. Once complete traffic to the iPad is restored. I have configured an if in the PAC file:
 /* test MobileIron exceptions */
 if (dnsDomainIs(host, "*.midev.wyn."))
 return “DIRECT?;

The goal is to only allow traffic to MobileIron is the user disables or deletes the ZCC as well as to be able to still manage the device from the MDM. When testing I log out of the ZCC and attempt to access the Enterprise App Store. It appears to allow access but the store never fully loads. The store which is a web clip gets to the logo and just hangs but never times out… Also I can’t get devices to check in or perform any other device management until I log back into ZCC.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmm7CAA/zscaler-client-connector-38-general-availability","lastmod":"2023-05-31T08:45:44.000Z","id":"0D54u00009evmm7CAA"} -->
## Zscaler Client Connector 3.8 General availability

- Source: https://community.zscaler.com/s/question/0D54u00009evmm7CAA/zscaler-client-connector-38-general-availability
- Type: Q&A
- Posted: 2022-05-16T09:55:16.000Z
- Last activity: 2023-05-31T08:45:44.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Team,

Please let us know the tentative date when Zscaler Client Connector 3.8 version will be available as “General Availability?

Thank you,
 Regards,
 Ganeshkumar Ramamurthy
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmmACAQ/detect-microsoft-defender-broken-after-ms-patching","lastmod":"2025-08-22T07:27:51.000Z","id":"0D54u00009evmmACAQ"} -->
## Detect Microsoft Defender broken after MS patching

- Source: https://community.zscaler.com/s/question/0D54u00009evmmACAQ/detect-microsoft-defender-broken-after-ms-patching
- Type: Q&A
- Posted: 2022-05-11T12:48:42.000Z
- Last activity: 2025-08-22T07:27:51.000Z
- Answers: 13
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Our SCCM Team started testing with May 2022 security patches which seems to break posture check for ‘Detect Microsoft Defender’, anyone else seeing this ? Issues reported so far is with ZCC 3.7.1.54 and ZCC 3.8.0.93.

G
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmmBCAQ/with-zcc-unable-to-do-cmd-output","lastmod":"2023-07-06T12:59:05.000Z","id":"0D54u00009evmmBCAQ"} -->
## With ZCC unable to do CMD output

- Source: https://community.zscaler.com/s/question/0D54u00009evmmBCAQ/with-zcc-unable-to-do-cmd-output
- Type: Q&A
- Posted: 2022-05-11T08:03:33.000Z
- Last activity: 2023-07-06T12:59:05.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Dear Team,

I am stuck in small issue with ZCC forwarding traffics method.

if i do without zscaler below command in the CMD, i am able to see the output with other proxy but not with ZCC

cmd command= nslookup space IP(any internal IP) (it will get us that IP’s hostname).

I have already excluded the DNS hostname as well as IP in the “App Profile? but no luck.

Kindly help me on it.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmmKCAQ/tunnel-20-nonwebtraffic-forwarding-to-other-zen","lastmod":"2023-07-06T12:59:27.000Z","id":"0D54u00009evmmKCAQ"} -->
## Tunnel 2.0 Non-Web-Traffic forwarding to other ZEN

- Source: https://community.zscaler.com/s/question/0D54u00009evmmKCAQ/tunnel-20-nonwebtraffic-forwarding-to-other-zen
- Type: Q&A
- Posted: 2022-05-03T14:16:46.000Z
- Last activity: 2023-07-06T12:59:27.000Z
- Answers: 5
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi together

Is it possible to forward Non-Web-Based Traffic (Web Traffic is no Problem and descriped in help-article) forward to a specific ZEN?

e.g. I want to forward SSH Traffic to a private ZEN.

Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmmNCAQ/guide-zscaler-client-connector-deployment-with-workspaceone-uem-pro-for-windows","lastmod":"2023-07-06T12:57:48.000Z","id":"0D54u00009evmmNCAQ"} -->
## [Guide] Zscaler Client Connector Deployment with WorkspaceONE UEM Pro for Windows

- Source: https://community.zscaler.com/s/question/0D54u00009evmmNCAQ/guide-zscaler-client-connector-deployment-with-workspaceone-uem-pro-for-windows
- Type: Q&A
- Posted: 2022-05-02T13:58:24.000Z
- Last activity: 2023-07-06T12:57:48.000Z
- Answers: 2
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

The Zscaler Client Connector can be configured and deployed with Workspace ONE UEM on Windows devices with a few simple steps. This guide is based on Workspace ONE UEM running version 22.3.0.2 (2203) and deploys Zscaler Client Connector v3.7.2.18 to a Windows computer running Windows 10.

*Note: Workspace ONE UEM is required for the initial installation only. Zscaler Client Connector upgrades thereafter can be managed from within the Zscaler Client Connector Portal.*

**High-Level Steps:**

1. Download the Zscaler Client Connector MSI from Zscaler Client Connector Portal
2. Create an Internal Native App in Workspace ONE UEM for Client Connector and assign to managed Windows endpoints

**Before You Begin:**

A few key pieces of information will be required to setup the Native App in Workspace ONE UWM

- The Zscaler cloud name used for your organization
- The primary domain used for SAML authentication by your organization

**Determine your Zscaler Cloud Name:**

If your organization is provisioned on more than one cloud, your users will normally be prompted to select the cloud to which their traffic will be sent during the enrollment process. To avoid this prompt, you can pre-configure the Zscaler Client Connector to automatically connect to the intended cloud automatically by using this installation option.
 To determine your cloud name, you can follow the directions [in this article](https://help.zscaler.com/zia/what-my-cloud-name-zia)

**Determine your Primary Authentication Domain:**

This installation option allows users to skip the app enrollment page. If SSO is enabled for your organization, users are taken directly to your organization’s SSO login page. If you’ve integrated SSO with the app, users can also skip the SSO login page and are automatically enrolled with the Zscaler service and logged in. If your instance has multiple domains associated with it, use the primary domain for your instance.

**Step 1: Download the Zscaler Client Connector MSI**
 The Zscaler Client Connector will need to be downloaded from Zscaler before the Native App can be created. The client can be downloaded by following the below steps:

1. From the Zscaler Internet Access Admin UI, select Policy from the left navigation bar and click on Zscaler Client Connector Portal in the Mobile section

[image]

1. Select Administration from the top navigation bar in the Zscaler Client Connector Portal and click on Client Connector App Store from the navigation bar on the left
2. Select the Personal Computer tab, select Windows from the list of Platforms and click on the download icon under the Download MSI 32-bit column to download

[Picture1]

**Step 2: Create the Workspace ONE UEM Native Internal App**

1. In the Workspace ONE UEM admin console, select Resources and Native under Apps and click on Add and select Application File to add the app

[Picture2]

1. In the Add Application page select Upload and upload the downloaded software from the previous step and click Continue

[Picture3]

1. Name the App “Zscaler Client Connector-3.7.2.18? and click on Files to upload a MST with installation options. Directions to generate a MST for Zscaler Client Connector are available at [Customizing Zscaler Client Connector with Install Options for MSI | Zscaler](https://help.zscaler.com/z-app/customizing-zscaler-app-install-options-msi#CreateZAppMSTFile) MST file used in this example was created with the following install options

| Property | Value | Description |
| --- | --- | --- |
| USERDOMAIN | [myauthdomain.com](http://myauthdomain.com) | Your organization’s domain name identified earlier. If your instance has multiple domains associated with it, enter the primary domain for your instance |
| REINSTALLDRIVER | 1 | This install option forces a reinstallation of the driver, even if you already have a driver installed. Use this option if you are having issues with the currently installed driver. |
| cloudname | zscalerthree | The name of the cloud on which your organization is provisioned identified earlier. Example, if your cloud name is [zscalertwo.net](http://zscalertwo.net), you would enter zscalertwo To learn more, see What is my cloud name for ZIA? |

Additional installation options are available at [https://help.zscaler.com/z-app/customizing-zscaler-app-install-options-msi#RunZAppMSICmdLine](https://help.zscaler.com/z-app/customizing-zscaler-app-install-options-msi#RunZAppMSICmdLine)

[Picture4]

1. Select Add to add your MST file to the App

[Picture5]

1. Select Deployment Options and ensure that the Install Command references the uploaded MST. Select Save & Assign to select endpoints for deployment. For example: `msiexec /i "Zscaler-windows-3.7.2.18-installer.msi" TRANSFORMS={transform files} /qn`

[Picture6]

1. Select the appropriate Assignment Group to use, name the Distribution and click on Save and Publish to start the deployment

[Picture7]

Zscaler Client Connector will now be downloaded and installed on managed Windows computers along with the Zscaler Root CA certificate (if using the default Zscaler certificate). If your organization plans to deploy a custom Intermediate Root certificate, then you must make sure that your certificate is already installed on the Windows endpoint for SSL inspection to work. After installation, Zscaler Client Connector will auto-launch.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmmPCAQ/how-to-manually-set-the-zia-public-service-edge-for-zcc","lastmod":"2023-07-06T11:44:26.000Z","id":"0D54u00009evmmPCAQ"} -->
## How to Manually Set the ZIA Public Service Edge for ZCC

- Source: https://community.zscaler.com/s/question/0D54u00009evmmPCAQ/how-to-manually-set-the-zia-public-service-edge-for-zcc
- Type: Q&A
- Posted: 2022-05-01T12:24:53.000Z
- Last activity: 2023-07-06T11:44:26.000Z
- Answers: 2
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

First find the FQDN of the public service edge (PSE) you want to use

1. Access [https://config.zscaler.com](https://config.zscaler.com)
2. Choose the cloud you are assigned for ZIA
3. Select the proxy hostname. In this example we are selecting [zscalerone.net](http://zscalerone.net) and the New York III PSE. Notice that the green check is not enabled to the left of the proxy hostname showing that this PSE will not be used in the dynamic selection process. In other words, New York will never be dynamically selected when a user connects to [zscalerone.net](http://zscalerone.net). This screenshot was for 5/1/22.

**[[image] 1004×697 69.2 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O8RbAAK)**

To manually set the PSE that is used

1. Access ZIA GUI
2. Access “Administration > Resources > Hosted PAC Files?
3. Click “Add PAC File?
4. Modify PAC file

- Change domain to the login domain
- Go to the bottom of the PAC file
- Duplicate the last two lines so that you can modify it and still have a reference to the original.
- Replace ${COUNTRY_GATEWAY} with the PSE chosen above. This is shown below with the original line commented out with “//?

> /* Forwarding statically to New York */
 return “PROXY [nyc3.sme.zscalerone.net:9400](http://nyc3.sme.zscalerone.net:9400); PROXY ${COUNTRY_SECONDARY_GATEWAY}:9400; DIRECT?;
 /* Default Traffic Forwarding. Forwarding to Zen on port 80, but you can use port 9400 also */
 // return “PROXY ${COUNTRY_GATEWAY}:9400; PROXY ${COUNTRY_SECONDARY_GATEWAY}:9400; DIRECT?;
>

- Click “Verify PAC File? to validate that the syntax is correct
- Click Save
- Copy the name of the Hosted URL for applying to the ZCC App Profile in a later step. This is shown below

[[image] 1600×392 120 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O4VJAA0)

1. Open the ZCC configuration policy by accessing “Policy > Mobile > Zscaler Client Connector Portal?
2. Select the App Profile to be used and edit
3. Change the “Custom PAC URL? to the URL of the PAC file created earlier. This is shown below.

[[image] 718×524 38.2 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O6tsAAC)

Test

1. Open ZCC client on a computer to test
2. Click on “More? option on the bottom left as shown below and click “Update Policy?

[[image] 762×593 47.9 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O3GmAAK)

1. Access [https://ip.zscaler.com](https://ip.zscaler.com) to verify that the manually configured PSE is being used

[[image] 989×325 42 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O6ZmAAK)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmmRCAQ/bypassing-2from-tunnel-20","lastmod":"2023-07-06T12:59:41.000Z","id":"0D54u00009evmmRCAQ"} -->
## By-passing 2from Tunnel 2.0

- Source: https://community.zscaler.com/s/question/0D54u00009evmmRCAQ/bypassing-2from-tunnel-20
- Type: Q&A
- Posted: 2022-04-29T04:11:48.000Z
- Last activity: 2023-07-06T12:59:41.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is there any way to by-pass traffic from Zscaler Tunnel 2.0 without using Forwarding PAC ?

The reason I am asking is if we are using a Forwarding PAC, Local proxy is being enforces on the end-user system proxy settings.

We have a requirement where some VPNs DO NOT establish the connection if there is a proxy enabled on the Local Machine.

Thanks,
 Rahul V
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmmUCAQ/zcc-connectivity-issue","lastmod":"2023-05-31T08:45:52.000Z","id":"0D54u00009evmmUCAQ"} -->
## ZCC connectivity issue

- Source: https://community.zscaler.com/s/question/0D54u00009evmmUCAQ/zcc-connectivity-issue
- Type: Q&A
- Posted: 2022-04-28T07:21:12.000Z
- Last activity: 2023-05-31T08:45:52.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

we observing internet connected issue with ZCC with laptop users only. whenever system goes to sleep mode that moment ZCC state is “on? but user unable to browse anything. our environment doesn’t have external DNS resolution. We configured tunnel 2.0 and we have excluded DNS hostnames as well as DNS IPs into the app profile.

when issue occur simply we do update policy of ZCC or flush DNS then internet starts working.

do we have permanent solution for this issue?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmmVCAQ/bypass-country-url-with-geolocation-using-local-node","lastmod":"2023-07-06T12:59:47.000Z","id":"0D54u00009evmmVCAQ"} -->
## Bypass country URL with geolocation using local node

- Source: https://community.zscaler.com/s/question/0D54u00009evmmVCAQ/bypass-country-url-with-geolocation-using-local-node
- Type: Q&A
- Posted: 2022-04-28T06:19:35.000Z
- Last activity: 2023-07-06T12:59:47.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

Currently our country ZIA node does not support tunnel 2.0 (SVPN) hence there will be a latency and geolocation blocking issue when redirect to a supported node

Does Zscaler support or able to create a custom URL domain rule, e.g. *.com.au URL and redirect other traffic to a supported tunnel 2.0 node?. Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmmZCAQ/guide-zscaler-client-connector-deployment-with-jamf-pro-for-ios","lastmod":"2023-06-02T17:09:17.000Z","id":"0D54u00009evmmZCAQ"} -->
## [Guide] Zscaler Client Connector Deployment with JAMF Pro for iOS

- Source: https://community.zscaler.com/s/question/0D54u00009evmmZCAQ/guide-zscaler-client-connector-deployment-with-jamf-pro-for-ios
- Type: Q&A
- Posted: 2022-04-25T14:36:33.000Z
- Last activity: 2023-06-02T17:09:17.000Z
- Answers: 3
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

The Zscaler Client Connector can be configured and deployed with JAMF Pro on iOS devices with a few simple steps. This guide uses a cloud hosted version of JAMF Pro v10.37.2 and deploys Zscaler Client Connector v1.8.x to an iOS 15 device.

**High-Level Steps:**

1. Create a Configuration Profile to install and trust the Root CA certificate used for SSL inspection
2. Create a Mobile Device App definition for Zscaler Client Connector for managed iOS devices

**Before You Begin:**

To customize the Zscaler Client Connector App install, a few key pieces of information will be required:

- The Zscaler cloud name used for your organization
- The primary domain used for SAML authentication by your organization

**Determine your Zscaler Cloud Name:**

If your organization is provisioned on more than one cloud, your users will normally be prompted to select the cloud to which their traffic will be sent during the enrollment process. In order to avoid this prompt, you can pre-configure the Zscaler Client Connector to automatically connect to the intended cloud automatically by using this installation option.

To determine your cloud name, you can follow the directions in [this article](https://help.zscaler.com/zia/what-my-cloud-name-zia)

**Determine your Primary Authentication Domain:**

This installation option allows users to skip the app enrollment page. If SSO is enabled for your organization, users are taken directly to your organization’s SSO login page. If you’ve integrated SSO with the app, users can also skip the SSO login page and are automatically enrolled with the Zscaler service and logged in. If your instance has multiple domains associated with it, use the primary domain for your instance.

**Step 1: Create a Configuration Profile in JAMF Pro**

A Configuration Profile is required to deploy the Root CA certificate to managed iOS devices for SSL inspection. If using the default Zscaler certificate, the certificate will need to be downloaded from the Zscaler Internet Access Admin UI and added to a Configuration Profile in JAMF Pro by following the below directions.

*Note: Steps 1 and 2 are only applicable when using the Zscaler default certificate. If the organization is using a custom Root CA certificate, use the custom certificate instead.*

1. To download the certificate, login to the Zscaler Internet Access Admin UI, select Policy from the left navigation bar and click on SSL Inspection in the Access Control section [image]
2. Select Advanced SSL Inspection Settings and click on the “Download Zscaler Root Certificate? link to download the Zscaler Root CA certificate file in a zip archive. Unzip the downloaded Zscaler Root CA certificate archive and change the file extension from .crt to .cer. [image]
3. In JAMF Pro, select Devices from the left navigation bar, select Configuration Profiles (under Content Management) and click on New to create a new Configuration Profile and name the profile. [image]
4. Name the Profile and select Certificate from the list of options and click Configure. Name the certificate and select Upload from the certificate option from the dropdown to upload the Root CA certificate. [image]

[image] [image]

1. Select Scope to determine iOS endpoints where the profile will be deployed. Select Add, and add the “All Managed iPhones? and “All Managed iPads? Smart Computer groups to target all managed iOS devices and Save the profile. [image]

**Step 2: Create a Mobile Device App in JAMF Pro**

Jamf Pro allows you to distribute App Store apps and apps purchased in volume (including custom apps and apps offered as a Universal Purchase) to mobile devices. After an app is distributed, Jamf Pro can be used to manage future updates to the Zscaler Client Connector. Create a Mobile Device App for Zscaler Client Connector by following the below directions in JAMF Pro.

1. Create a new app by clicking on Devices from the left navigation bar and select Mobile Device Apps (under Content Management) and click on New
2. Select “App Store app or apps purchased in volume? as the app type and click Next [image]
3. Enter “Zscaler? in the Search bar select the App Store country from the drop-down and click Next [image]
4. JAMF Pro will connect to the App Store and search for all Zscaler apps. Select iPhone and iPod touch Apps and click on Add, next to Zscaler Client Connector. If deploying to iPads, select iPad Apps. [image]
5. JAMF Pro will automatically populate the App name, version number and Bundle Identifier from the App manifest in the App Store. There is no need to change any of this info. Select the checkbox to enable JAMF Pro to automatically check the App Store for updates as Zscaler releases new versions on a regular basis and JAMF Pro can automatically update the App on your managed iOS devices. You may want to disable the checkbox next to “Allow users to remove app (iOS 14 or later)? to prevent users from uninstalling the App [image]
6. Select Scope to determine iOS endpoints where the policy will be deployed. In this case select the “All Managed iPhones? Smart Computer Group to target all managed iPhones. [image]
7. Click on App Configuration to configure Zscaler Client Connector before distributing it to mobile devices. Managed App Configuration is a set of key-value pairs (in XML format) used to configure iOS applications. We will use the below key-value pairs for this deployment. See below table for deployed key-value details.

| Key | Description |
| --- | --- |
| userdomain | Your organization’s domain name identified earlier. If your instance has multiple domains associated with it, enter the primary domain for your instance |
| cloudname | The name of the cloud on which your organization is provisioned identified earlier. Example, if your cloud name is [zscalertwo.net](http://zscalertwo.net), you would enter zscalertwo To learn more, see What is my cloud name for ZIA? |
| ownership | Used for device posture in Zscaler to identify an organization owned asset |
| strictenforcement | Requires users to enroll with Client Connector before they can browse the Internet |
| excludeList | This option allows you to exclude domains and IP addresses that should be bypassed from Zscaler (note that if you are using the strictEnforcement option, you must add the domains for your IdP, URL for Zscaler authentication services and your MDM using this option). JAMF Pro, Microsoft, OKTA and ZPA are shown in the example below. |

Additional keys and details are available at Zscaler’s [online help site](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-ios)

```
<dict>
	<key>cloudname</key>
	<string>zscalerthree</string>
	<key>ownership</key>
	<string>Dattalabz</string>
	<key>strictEnforcement</key>
	<string>0</string>
	<key>userDomain</key>
	<string>dattalabz.com</string>
	<key>excludeList</key>
	&
```
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmmaCAA/guide-zscaler-client-connector-deployment-with-jamf-pro-for-macos","lastmod":"2023-07-06T12:57:45.000Z","id":"0D54u00009evmmaCAA"} -->
## [Guide] Zscaler Client Connector Deployment with JAMF Pro for MacOS

- Source: https://community.zscaler.com/s/question/0D54u00009evmmaCAA/guide-zscaler-client-connector-deployment-with-jamf-pro-for-macos
- Type: Q&A
- Posted: 2022-04-25T14:23:45.000Z
- Last activity: 2023-07-06T12:57:45.000Z
- Answers: 13
- Likes: 4
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Updated: April, 2023

**Note**: It is no longer necessary to use a script to deploy Client Connector using JAMF Pro. ZCC v3.9.x and newer releases can now be deployed using custom property list (plist) files. The updated directions are available in the [Zscaler online help portal](https://help.zscaler.com/client-connector/deploying-zscaler-client-connector-jamf-pro-macos).

*You will however still need to create a Configuration Profile to install and trust the Root CA certificate for SSL inspection in the System Keychain. Directions to deploy the certificate are shown below.*

**Create a Configuration Profile in JAMF Pro**

A Configuration Profile is required to deploy the Root CA certificate to managed macOS computers for SSL inspection. If using the default Zscaler certificate, the certificate will need to be downloaded from the Zscaler Internet Access Admin UI and added to a Configuration Profile in JAMF Pro by following the below directions.

*Note: Steps 1 and 2 are only applicable when using the Zscaler default certificate. If the organization is using a custom Root CA certificate, use the custom certificate instead.*

1. To download the certificate, login to the Zscaler Internet Access Admin UI, select Policy from the left navigation bar and click on SSL Inspection in the Access Control section [image]
2. Select Intermediate CA Certificates and click on the pencil icon for Zscaler Intermadiate Certificate to show the view certificate popup. Click on the Download link to download the certificate as a zip file. [[image] image1061×398 42.8 KB](https://global.discourse-cdn.com/zscaler/original/2X/f/fe0f240867c1f79dffc094d4b4a0e3611b3ef8c1.png) [[image] image701×418 22.1 KB](https://global.discourse-cdn.com/zscaler/original/2X/9/921c67b3ed055660d6a6ffde8c20c678fd858548.png)
3. Unzip the downloaded Root CA certificate archive and convert the certificate from PEM to DER format using the below openssl command.

```
openssl x509 -outform der -in ZscalerRootCertificate-2048-SHA256.crt -out ZscalerRootCertificate-2048-SHA256.der
```

1. In JAMF Pro, select Computers from the left navigation bar, select Configuration Profiles (under Content Management) and click on New to create a new Configuration Profile [[image] image935×664 74.9 KB](https://global.discourse-cdn.com/zscaler/original/2X/e/ee6fefd3654e22aa4e891415fa48f9a8e16e6833.png)
2. Name the Profile and select Certificate from the list of options and click on Configure. Name the certificate and select Upload from the certificate option from the dropdown to upload the Root CA certificate in DER format. [[image] image935×664 75.6 KB](https://global.discourse-cdn.com/zscaler/original/2X/1/117df2ad74baab3930f8d0e1555a5928c34ab096.png) [image] [[image] image1414×644 85.6 KB](https://global.discourse-cdn.com/zscaler/original/2X/c/c58fb167adf3e56452956cea873b9069de546d4e.png)
3. Select Scope to determine macOS endpoints where the profile will be deployed. In this case, select the applicable group and click to Save the profile [image]

The Zscaler Root CA certificater for SSL inspection will be downloaded, imported and trusted by the macOS KeyChain.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmmwCAA/how-to-reauthenticate-zcc-clients","lastmod":"2023-05-31T08:45:58.000Z","id":"0D54u00009evmmwCAA"} -->
## How to re-authenticate ZCC clients

- Source: https://community.zscaler.com/s/question/0D54u00009evmmwCAA/how-to-reauthenticate-zcc-clients
- Type: Q&A
- Posted: 2022-04-20T01:29:25.000Z
- Last activity: 2023-05-31T08:45:58.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi, just posting a question on how can we re-authenticate ZCC users? “Force re-authentication? does not apply to ZCC clients as per documentation. Authentication frequency setting only applies to browsers. What would be the option for ZCC then? Please help.

Joseph
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmn2CAA/sample-python-script-to-programmatically-retrieve-otp-for-client-connector-users","lastmod":"2025-08-22T07:27:47.000Z","id":"0D54u00009evmn2CAA"} -->
## Sample Python script to programmatically retrieve OTP for Client Connector users

- Source: https://community.zscaler.com/s/question/0D54u00009evmn2CAA/sample-python-script-to-programmatically-retrieve-otp-for-client-connector-users
- Type: Q&A
- Posted: 2022-04-16T20:27:33.000Z
- Last activity: 2025-08-22T07:27:47.000Z
- Answers: 8
- Likes: 4
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

**Use Case**: ServiceDesk users need to retrieve OTP for Client Connector users for troubleshooting purposes and customer wants to avoid giving ServiceDesk access to the Zscaler Admin UI. ServiceDesk can programmatically retrieve OTP for users from within applications like ServiceNow using the new Mobile Admin API

The script uses the new Mobile Admin API.

**Script requirements:**

1. API enabled in your tenant
2. Python3 with Requests module
3. API access setup in Mobile Admin UI (Step 1 below)
4. Script updated with API Endpoint URL, Client ID and Client Secret (Step 2 below)

```
% python get_device_otp.py username@authdomain.com

HOSTNAME            |TYPE                     |OS                                                |EXITOTP
____________________________________________________________________________________________________________
sales1               VMware, Inc. VMware7,1    Microsoft Windows 10 Enterprise;64 bit;amd64       ays0bmiu9e
salesuser1’s iPad    Apple iPad13,4            Version 15.4.1 (Build 19E258)                      0rnbg2omrb
```

Step 1: Configure API access in Mobile Admin UI by creating your API Key and Secret

[[image] image1920×1086 76.1 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O3oPAAS)

[[image] image1920×740 56.2 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O6tcAAC)

Step 2: Update the Python script with your credentials

```
#
# written: Niladri Datta ndatta@zscaler.com
# date: April 16 2022
# updated: Feb 2023
# This script accepts username as a parameter and retrieves exitOTP for all
# devices registered in Zscaler Mobile Admin for user
#
# Usage:
# > python3 get_device_otp.py user@domain.com
# HOSTNAME            |TYPE                     |OS                                                |EXITOTP
# ________________________________________________________________________________________________________________________
# sales1               VMware, Inc. VMware7,1    Microsoft Windows 10 Enterprise;64 bit;amd64       ays0bmiu9e
# salesuser1’s iPad    Apple iPad13,4            Version 15.4.1 (Build 19E258)                      0rnbg2omrb
#
# Requirements:
# Environment: Python3 with Requests module installed
# base_url: API endpoint URL for your tenant. Change it below
# key: Change this to the Client ID value from the Mobile Admin UI
# secret: Change this to the Client Secret value from the Mobile Admin UI

import sys
import requests
import json
import urllib3

# Change this to your cloud used by tenant
base_url = "https://api-mobile.zscalerbeta.net/papi"

# Insert generated API key and secret from Mobile Admin UI
key = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx"
secret = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx"

# Disable unverified HTTPS warnings
urllib3.disable_warnings()

def getdeviceotp(session_token: str, url: str, dev_udid: str) -> str:
    # This function retrieves the exitOTP for a device

    # Include the session token in your header
    auth_header = {
        'Content-Type': 'application/json',
        'auth-token': session_token
    }

    # Send udid as parameter in URL query string
    request_params = {
        'udid': dev_udid
    }

    rurl = url + "/public/v1/getOtp"

    # Get the OTP
    response_otp = requests.get(rurl, headers=auth_header, params=request_params, verify=False)
    device_exitotp = response_otp.json()["exitOtp"]

    return device_exitotp

####
# Step 1: Get username parameter
if len(sys.argv) == 2:
    user = sys.argv[1]
else:
    sys.exit("Exiting. Script executed incorrectly.\nExample syntax: python thisscript.py someuser@somedomain.com")

###
# Step 2: Authenticate and get session token
stdheader = {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
}

fullurl = base_url + "/auth/v1/login"

# Send API key and secret in body
auth_param = {
    "apiKey": key,
    "secretKey": secret
}

response = requests.post(fullurl, headers=stdheader, data=json.dumps(auth_param), verify=False)

if response.status_code == 200:
    mytoken = response.json()["jwtToken"]
else:
    sys.exit("Check URL and API key and secret. Authentication failed")

###
# Step 3: Get all devices enrolled by user
header = {
    'Content-Type': 'application/json',
    'auth-token': mytoken
}

fullurl = base_url + "/public/v1/getDevices"

# Send username parameter in URL query string
payload = {
    'username': user
}

response = requests.get(fullurl, headers=header, params=payload, verify=False)

if response.status_code == 200:
    registered_device_count = 0
    all_user_devices = response.json()

    # Print the header
    print("{0:<20}|{1:<25}|{2:<50}|{3:<15}".format("HOSTNAME", "TYPE", "OS", "EXITOTP"))
    print("_"*120)
    ##
    # Step 4: Get OTP for all registered user devices
    for index in all_user_devices:
        if index["registrationState"] == "Registered":
            udid = index["udid"]
            dev_otp = getdeviceotp(mytoken, base_url, udid)
            print("{0:<20} {1:<25} {2:<50} {3:<15}".format(index['machineHostname'], index['detail'],
                                                                 index['osVersion'], dev_otp))
            registered_device_count += 1

    # Existing user with no Registered devices
    if registered_device_count == 0:
        print(f"User {user} has no active devices enrolled")

# User not found returns a 400
elif response.status_code == 400:
    print(f"User {user} not found")
else:
    sys.exit("Error getting device list for user")
```

Customers can also retrieve all devices as well as App Profile passwords with the new API.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmn5CAA/zscaler-client-connector-app-version-310129-logged-off-automatically","lastmod":"2023-07-06T11:44:40.000Z","id":"0D54u00009evmn5CAA"} -->
## Zscaler Client Connector App version 3.1.0.129 logged off automatically

- Source: https://community.zscaler.com/s/question/0D54u00009evmn5CAA/zscaler-client-connector-app-version-310129-logged-off-automatically
- Type: Q&A
- Posted: 2022-04-13T12:21:40.000Z
- Last activity: 2023-07-06T11:44:40.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello team,

There are 5 computers where Zscaler Client Connector is logged of automatically. Hence Internet does not work.
 Account used to login in the Windows 10 64 bit computers is Automation Account. Internet works till the account is logged in to Zscaler Client Connector. Not sure when does the Logoff happens.

How to check why the zscaler is logged of automatically

[[Zscaler Client Connector] Zscaler Client Connector832×507 43.5 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O3sMAAS)

Please help
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmn9CAA/deploying-zcc-to-ios-mobile-device-using-mobileiron-mdm-strict-enforcement","lastmod":"2024-02-22T09:29:13.000Z","id":"0D54u00009evmn9CAA"} -->
## Deploying ZCC to iOS mobile device using MobileIron (MDM) + strict enforcement

- Source: https://community.zscaler.com/s/question/0D54u00009evmn9CAA/deploying-zcc-to-ios-mobile-device-using-mobileiron-mdm-strict-enforcement
- Type: Q&A
- Posted: 2022-04-11T15:45:26.000Z
- Last activity: 2024-02-22T09:29:13.000Z
- Answers: 3
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,
 we intend to deploy the ZCC App to all of our iOS mobile devices. These devices are managed by MobileIron.

Is there any configuration guide or template we can follow to set up the app and enable strict enforcement?

Appreciate all your feedback.

Thank you
 Yasser
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmnCCAQ/enhancement-request-mobile-platform-device-posture-options","lastmod":"2023-05-31T08:46:00.000Z","id":"0D54u00009evmnCCAQ"} -->
## Enhancement Request: Mobile platform Device posture options

- Source: https://community.zscaler.com/s/question/0D54u00009evmnCCAQ/enhancement-request-mobile-platform-device-posture-options
- Type: Q&A
- Posted: 2022-04-06T19:02:55.000Z
- Last activity: 2023-05-31T08:46:00.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Are there any plans in the near future to add Operating system version checking to the device posture options for iOS and Android? The information already exists within the ZCC portal and not being able to set a OS level baseline for mobile devices is hampering our ability to securely deploy ZCC for mobile.

Thanks!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmnECAQ/zscaler-client-connector-portal-api","lastmod":"2023-05-31T09:26:36.000Z","id":"0D54u00009evmnECAQ"} -->
## Zscaler Client Connector Portal API

- Source: https://community.zscaler.com/s/question/0D54u00009evmnECAQ/zscaler-client-connector-portal-api
- Type: Q&A
- Posted: 2022-04-06T14:58:49.000Z
- Last activity: 2023-05-31T09:26:36.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hey guys, i was wondering if there is going to be an API added to the Client Connector Portal?
 Specifically in order to query device details of enrolled devices connected to the ZPA via the Client Connector.

Thanks!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmnICAQ/azure-avd-and-zapp","lastmod":"2023-05-31T08:45:25.000Z","id":"0D54u00009evmnICAQ"} -->
## Azure AVD and ZApp

- Source: https://community.zscaler.com/s/question/0D54u00009evmnICAQ/azure-avd-and-zapp
- Type: Q&A
- Posted: 2022-03-31T19:15:07.000Z
- Last activity: 2023-05-31T08:45:25.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Running Tunnel 1.0 for ZIA, installed latest ZApp (3.7.2.18) on the AVD. Having minor issue when trying to connect to the AVD using the Microsoft Remote Desktop client, not mtsc.exe, during the connection get an error “Reconnecting? and eventually I am connected.

Wondering if anyone has seen this issue?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmnPCAQ/zscaler-client-connector-zia-not-connecting-after-pc-wakes-up-from-sleephibernate","lastmod":"2023-07-06T13:00:08.000Z","id":"0D54u00009evmnPCAQ"} -->
## Zscaler Client Connector - ZIA not connecting after PC wakes up from sleep/hibernate

- Source: https://community.zscaler.com/s/question/0D54u00009evmnPCAQ/zscaler-client-connector-zia-not-connecting-after-pc-wakes-up-from-sleephibernate
- Type: Q&A
- Posted: 2022-03-25T05:57:47.000Z
- Last activity: 2023-07-06T13:00:08.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

ZIA does not connect after workstations come back up after sleep/hibernate. This happens usually in the morning, and sometimes when users unplug their laptops and move somewhere.

We know this because we are using Azure AD conditional access policy to only allow Microsoft 365 logins if the client IP belongs to any Zscaler Cloud IP, meaning ZCC has to be running and ZIA connected before the login is allowed.

If you check the ZCC UI, ZIA will say “connection error?. Sometimes, it gets stuck on “connecting?.
 Checking [ip.zscaler.com](http://ip.zscaler.com) will tell you that you are indeed offline from Zscaler Cloud.
 Support said it has something to do with the network adapter being one of the last components to “wake up?.

This has happened to all our users at some point over the last year. The remedy is always to restart zscaler services (via ZCC) but it’s a bit inconvenient to figure out from an end-user’s perspective. It has also given a bad rep for Zscaler, since it’s always the same app causing issues during critical times of the day.

I’ve reported this issue several times to Support, sad to say I have not received any good answers. I only found out earlier this year that this is apparently a “bug? since version 3.6, maybe even earlier, when I decided to review the ZCC changelogs.

On one of my tickets to Support, I was told that this bug has been fixed in 3.7.1.54. After upgrading, I noticed the bug is still happening (less frequently now, to be fair with R&D).

The good news is that ZCC can now correct itself after approximately 15 minutes without any user intervention.
 the not so good news is that ZIA will actually say “connected? and [ip.zscaler.com](http://ip.zscaler.com) also says yes. But I know the issue is still happening just by checking the Microsoft 365 sign-in logs, as well as good old whatismyip. Kinda sneaky to be honest, Zscaler.

I’m giving this forum a try, hoping to be able to share more sensible thoughts.
 Anyone else encountered this issue before or still facing it now?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmnUCAQ/pulse-secure-not-working-after-switching-between-pulse-secure-vpns","lastmod":"2025-08-22T07:27:17.000Z","id":"0D54u00009evmnUCAQ"} -->
## Pulse Secure not working after switching between Pulse Secure VPNs

- Source: https://community.zscaler.com/s/question/0D54u00009evmnUCAQ/pulse-secure-not-working-after-switching-between-pulse-secure-vpns
- Type: Q&A
- Posted: 2022-03-19T19:38:31.000Z
- Last activity: 2025-08-22T07:27:17.000Z
- Answers: 9
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Dears,
 We are having an issue with Pulse Secure VPN where we have users that uses multiple Pulse Secure VPNs to perform their work. If a user reboots their PC and connects first time on a Pulse Secure VPN all works fine, but if he disconnnects from a Pulse Secure VPN and tries to connect again then VPN authentication fails.

The only way of making VPN work again after connecting on a Pulse Secure VPN is to restart Pulse Secure client service on Windows 10. We updated Zscaler client connector to latest version, but issue persists. Any good ideia of how to resolve it? Thanks!

If Zscaler client connector is disabled, then switching between Pulse Secure VPNs works fine …
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmnVCAQ/client-connector-behavior-and-traffic-handling-before-user-gets-logged-in-zia","lastmod":"2023-05-31T08:46:00.000Z","id":"0D54u00009evmnVCAQ"} -->
## Client Connector behavior and traffic handling before User gets logged in (ZIA)

- Source: https://community.zscaler.com/s/question/0D54u00009evmnVCAQ/client-connector-behavior-and-traffic-handling-before-user-gets-logged-in-zia
- Type: Q&A
- Posted: 2022-03-18T13:16:52.000Z
- Last activity: 2023-05-31T08:46:00.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hey everyone,

I just wanted to make sure that I’m on the right track, while we are evaluating the interactions from Zscaler Client Connector (ZIA), Endpoint Protection and so on.
 Especially with Windows 11 around the corner and some Windows Auto Pilot stuff that gets introduced.

While for ZPA there seems to be a Function for Pre Windows Login, I couldn’t find anything regarding Zscaler Client Connector with only ZIA and if there is anything I should be aware of.

I would guess that this would lead us to the following Situation:
 **1.)** If the Device is connected to a “normal internet connection? like in Homeoffice, the ZApp would do nothing before the user logs in. So the traffic would go directly to the internet (if no other vpn solution or so is activated/interrupts that).

**2.)** If the User/Laptop is on a corporate network, he has no direct access to the internet. In our case he would get routed to Zscaler via GRE-Tunnels and would there be seen as “Unauthenticated Traffic? that would get handled according to the policies for unauthenticated traffic.

**3.)** Last but not least: All our Locations have “Enforce Authentication? enabled.
 This will block every traffic that is not authenticated with the exception of those configured under "Administration > Advanced Settings → “Authentication Exemptions? - Correct?

In conjunction to that, if I would just “restart? my Laptop on the corperate network, while I was already authenticated… I would keep “authenticated? via IP Surrogate until this gets disassociated?

Thanks in advance and kind regards,

Simon
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmnZCAQ/not-sure-how-to-use-zscaler-client-connector-in-linux","lastmod":"2024-05-29T14:07:33.000Z","id":"0D54u00009evmnZCAQ"} -->
## Not sure how to use Zscaler client connector in Linux

- Source: https://community.zscaler.com/s/question/0D54u00009evmnZCAQ/not-sure-how-to-use-zscaler-client-connector-in-linux
- Type: Q&A
- Posted: 2022-03-11T02:54:30.000Z
- Last activity: 2024-05-29T14:07:33.000Z
- Answers: 10
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I’m trying the ZScaler client for Linux (ZorinOS 16), but unlike the Windows client which asked me to log on on its first start up, the client for Linux does nothing. I’ve seen applications where they run as service and it takes a CLI command to bring up some GUI. For example, when using GlobalProtect on Linux, the service would start on boot up, but I needed to run a command to bring up the gui to tell it to connect. I wonder if that’s something I need to call up Zscaler so I can do the initial log on.

Using Zscaler-linux-1.1.0.24-installer.run
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmnbCAA/is-it-proxy-chaining-when-using-client-connector-forwarding-profile","lastmod":"2023-07-06T13:00:37.000Z","id":"0D54u00009evmnbCAA"} -->
## Is it « proxy chaining » when using Client Connector + forwarding profile?

- Source: https://community.zscaler.com/s/question/0D54u00009evmnbCAA/is-it-proxy-chaining-when-using-client-connector-forwarding-profile
- Type: Q&A
- Posted: 2022-03-10T10:49:40.000Z
- Last activity: 2023-07-06T13:00:37.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

I’m trying to setup zscaler connector on a LAN using a http proxy (squid).

I’ve read this part of doc : [Choosing Traffic Forwarding Methods | Zscaler](https://help.zscaler.com/zia/choosing-traffic-forwarding-methods)

I was thinking at first i had to setup *proxy chaining*.

Now, i’m not sure. As we are using *zscaler client connector* , i think it’s more like setting up a *forwarding profile*.

So to sum up, with *zscaler client connector* and an http proxy, all i need to do is to enforce the proxy in the *forwarding profile*.

[Zscaler Mobile](https://mobile.zscloud.net/index.html) → Administration (top menu) → Forwarding profile (left menu)

Can you confirm, please ?

Thank you.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmnnCAA/help-with-zcc-error-username-not-valid-10110","lastmod":"2023-05-31T08:46:16.000Z","id":"0D54u00009evmnnCAA"} -->
## Help with ZCC error - Username not valid (10110)

- Source: https://community.zscaler.com/s/question/0D54u00009evmnnCAA/help-with-zcc-error-username-not-valid-10110
- Type: Q&A
- Posted: 2022-02-28T21:44:58.000Z
- Last activity: 2023-05-31T08:46:16.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

A user has reported this error. I cant find anything on ZIA Help about it. (like here)

help.zscaler.com
##### [Zscaler Client Connector Errors | Zscaler](https://help.zscaler.com/client-connector/zscaler-client-connector-errors#mobile-admin-portal)

Information on the error messages that Zscaler Client Connector might display while it is in use.

The user previously had been running ZCC without issue. Now he is getting this issue. We have uninstalled ZCC and our SCCM re-installed it. After a reboot the user is presented with a Login screen. When he clicks Login the Windows Authentication takes over an he gets the error - he is not getting the SSO login (Okta) screen,
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmnoCAA/zcc-vs-cisco-anyconnect-hostscan-csd-prelogin-verification-failed","lastmod":"2023-07-06T13:00:51.000Z","id":"0D54u00009evmnoCAA"} -->
## ZCC vs. Cisco AnyConnect: Hostscan CSD prelogin verification failed

- Source: https://community.zscaler.com/s/question/0D54u00009evmnoCAA/zcc-vs-cisco-anyconnect-hostscan-csd-prelogin-verification-failed
- Type: Q&A
- Posted: 2022-02-28T12:29:33.000Z
- Last activity: 2023-07-06T13:00:51.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

we are currently running a poc for the Client Connector on Windows (Tunnel 1.0-mode; packet filter based driver).
 When ZCC is running and AnyConnect tries to reconnect, e.g., after 16hrs timeout.
 AnyConnect does not reconnect due to:
 Posture Assessment Failed: Hostscan CSD prelogin verification failed.

I am currenly waiting on feedback from the AnyConnect Colleagues but if someone could give me a hint, what check may fail due to ZCC, I am quite thankful.

Thank you & best regards
 Andreas
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmnuCAA/south-africa-issues","lastmod":"2023-07-06T13:00:55.000Z","id":"0D54u00009evmnuCAA"} -->
## South Africa Issues?

- Source: https://community.zscaler.com/s/question/0D54u00009evmnuCAA/south-africa-issues
- Type: Q&A
- Posted: 2022-02-21T16:10:50.000Z
- Last activity: 2023-07-06T13:00:55.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi all,

In August 2021, my org moved to tunnel 2.0 with cloud firewall. After a few months, we started receiving complaints from the tech team based out of South Africa that the internet was practically unusable. After almost two months of troubleshooting with Zscaler, it was determined that the problem was with Tunnel 2.0 and the ISP, so we ended up creating a separate app profile that would use tunnel 1.0 for in-office SA users, and tunnel 2.0 when users were off the trusted network in SA.

This seemed to work fine for a few months but now we’re back to square one where tunnel 2.0 is causing major latency for SA users regardless of whether they’re in office or remote.

We’ve tried to work with our ISP, but now that this is happening on a larger scale, it’s becoming apparent that it seems to be more of a Zscaler problem. We currently have no fix outside of putting the users back on Tunnel 1.0, which isn’t ideal given its backtracking in terms of our internet security posture.

Do any customers with a presence in South Africa have similar issues? Are there any Zscaler employees that have been receiving similar reports?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmnwCAA/juniper-pulse-secure-full-tunnel","lastmod":"2025-08-22T07:27:18.000Z","id":"0D54u00009evmnwCAA"} -->
## Juniper Pulse Secure - Full Tunnel

- Source: https://community.zscaler.com/s/question/0D54u00009evmnwCAA/juniper-pulse-secure-full-tunnel
- Type: Q&A
- Posted: 2022-02-18T14:45:48.000Z
- Last activity: 2025-08-22T07:27:18.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We have a number of users that use Pulse Secure VPN client to connect to a 3rd party, which is configured in Full Tunnel mode. Everything works in Tunnel 1.0 configuration but we are making a move to Tunnel 2.0 where it fails to function. The connection is not established fully and both Pulse Secure and ZCC go into Connecting and Connected state.

Working Scenario (current)

- Tunnel Driver Type: Packet Filter Based
- On Trusted Network: Tunnel, Tunnel 1.0
- System Proxy: Never
- VPN Trusted Network: Same as On Trusted Network
- Off Trusted Network: Same as On Trusted Network

Non-Working Scenario (goal)

- Tunnel Driver Type: Packet Filter Based
- On Trusted Network: Tunnel, Tunnel 1.0
- System Proxy: Never
- VPN Trusted Network: Same as On Trusted Network
- Off Trusted Network: Tunnel 2.0
- Transport Settings: TLS
- System Proxy: FWD PAC

The VPN destination is in the App PAC, Fwd PAC, VPN Gateway Bypass and still fails. Tried the following without success:

- added the DNS domain of the 3rd party to the DNS search suffix so it drops down to Tunnel 1.0 - fails
- added the DNS domain of the 3rd party to the DNS search suffix so it’s detected as VPN Trusted Network and set to None (disabled) - fails
- tried Tunnel with Local Proxy for VPN Trusted Network - fails
- tried DTLS transport - fails

It appears when switching from Z-Tunnel 2.0 to 1.0 or to disable ZIA completely, it fails and only likes when it remains on Z-Tunnel 1.0 throughout the connection.

Looking for suggestions?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmnyCAA/zcc-frequent-disconnects-in-the-office","lastmod":"2023-05-31T08:46:18.000Z","id":"0D54u00009evmnyCAA"} -->
## ZCC Frequent disconnects in the office

- Source: https://community.zscaler.com/s/question/0D54u00009evmnyCAA/zcc-frequent-disconnects-in-the-office
- Type: Q&A
- Posted: 2022-02-17T13:43:16.000Z
- Last activity: 2023-05-31T08:46:18.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hey everyone,

we currently have issues with the Zscaler Client Connector at our company sites where the ZCC at apparently random times reconnects to the Zscaler Infrastructure. This results in an interruption of network connectivity for eg. Microsoft Teams and Azure Virtual Desktop. Also the “Time connected? field in the ZCC resets to the time of the reconnect.

We are using Tunnel 2.0 DTLS via the Packet Filter Based Driver Type. The traffic is routed via our firewall into the vpn tunnel to Zscaler. The problem only occurs in this constellation on the company network. When we are working from home the problem does not occur.

Has anyone already met such an issue?

I have already opened a ticket with the Zscaler support but as of now without any success.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmo2CAA/sftp-support-on-tunnel-20","lastmod":"2023-05-31T09:26:51.000Z","id":"0D54u00009evmo2CAA"} -->
## SFTP Support on Tunnel 2.0

- Source: https://community.zscaler.com/s/question/0D54u00009evmo2CAA/sftp-support-on-tunnel-20
- Type: Q&A
- Posted: 2022-02-15T15:11:19.000Z
- Last activity: 2023-05-31T09:26:51.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I keep seeing conflicting information regarding Zscaler’s ability to support SFTP traffic. As of now, we are using Tunnel 2.0 with the app profile. No GRE or IPSec tunnels are in use.

The OOTB pac file for tunnel 2.0 we were instructed to use has logic to bypass any “sftp://? traffic. If we were to remove this and allow traffic to come to Zscaler, can we use the Cloud Firewall to allow SFTP traffic by exception?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmoACAQ/application-bypass-option-microsoft-teams-gone-zscloud-only","lastmod":"2023-07-06T13:01:09.000Z","id":"0D54u00009evmoACAQ"} -->
## Application bypass option "Microsoft Teams" gone (Zscloud only?)

- Source: https://community.zscaler.com/s/question/0D54u00009evmoACAQ/application-bypass-option-microsoft-teams-gone-zscloud-only
- Type: Q&A
- Posted: 2022-02-03T10:27:32.000Z
- Last activity: 2023-07-06T13:01:09.000Z
- Answers: 2
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi guys,

it seems like the original/old “Microsoft Teams? application bypass option is gone from the ZSCloud portal. That affected our app profile configuration. When I checked the configuration of our default app profile there was Zoom configured, which I never did.
 Then I went to the application bypass overview page and the first option was indeed Zoom. Before there used to be two MS Teams App bypass definements. The original one was more general.

I don’t know when this change has happend and I couldn’t find a communication about that.
 But after some big troubleshooting I’ve found the root cause for our MS-Teams problems.

I hope this information prevents someone from getting headaches.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmoCCAQ/unable-to-accurately-measure-connection-latency-via-a-socket-connection","lastmod":"2023-05-31T08:13:28.000Z","id":"0D54u00009evmoCCAQ"} -->
## Unable to accurately measure connection latency via a socket connection

- Source: https://community.zscaler.com/s/question/0D54u00009evmoCCAQ/unable-to-accurately-measure-connection-latency-via-a-socket-connection
- Type: Q&A
- Posted: 2022-02-02T16:52:30.000Z
- Last activity: 2023-05-31T08:13:28.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I have a company macbook with Zscaler running on it, and honestly I know next to nothing about it.
 I do know that I can’t accurately gauge the latency of a connection and I’m not sure why. This issue is 100% related to Zscaler. If I bypass the Zscaler tunnel, it works perfectly.

I created the following script to measure the latency of a connection

[akira128@pro.example.com ~]$ cat socket.latency.py
 #!/usr/bin/python3

import sys
 import time
 import socket

def main():
 if len(sys.argv) == 1:
 print("Usage: %s " % sys.argv[0])
 sys.exit(-1)

```
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
time_before = time.time()
sock.connect((sys.argv[1], 80))
result = (time.time() - time_before) * 1000
sock.close()
print(sys.argv[1] + ": %f ms" % result)
```

if **name** == ‘**main**’:
 main()

### [#and-when-i-run-it-against-a-web-server-located-half-a-world-away-i-get-this-1](#and-when-i-run-it-against-a-web-server-located-half-a-world-away-i-get-this-1)and when I run it against a web server located half a world away, I get this:

[akira128@C02YM0JEJGH7 ~]$ ./socket.latency.py mirrors.maine.edu
 mirrors.maine.edu: 1.875877 ms

### [#the-zsatunnel-log-shows-this-2](#the-zsatunnel-log-shows-this-2)The ZSATunnel log shows this

2022-02-02 16:12:18.006999(+0100)[1908:760729] INF ===> ID=1871962298, TUN-Proxy: connection to 130.111.32.173 sock-fd=13, src_port=61876, dst_port=80
 2022-02-02 16:12:18.007067(+0100)[1908:760729] DBG ID=1871962298, Client socket SO_SNDBUF: 131768 SO_RCVBUF: 131768
 2022-02-02 16:12:18.007139(+0100)[1908:760729] DBG ID=1871962298, Use Sme: 1 Sme IP: 136.226.18.26
 2022-02-02 16:12:18.007259(+0100)[1908:760729] DBG ID=1871962298, readFromClient: read bytes: 0
 2022-02-02 16:12:18.007361(+0100)[1908:760729] ERR ID=1871962298, TcpServer: poll client revent code: 17
 2022-02-02 16:12:18.007399(+0100)[1908:760729] DBG ID=1871962298, Disconnecting!

If I run that script on a system without zscaler, I get a much different result:
 [akira128@black ~]$ ./socket.latency.py mirrors.maine.edu
 mirrors.maine.edu: 110.070229 ms

2 ms vs 110 ms is a massive difference. Basically, none of our Centos VMs are able to locate the fastest mirrors and as a result downloading packages is horrendously slow and prone to timeouts and failures. Has anyone experienced this issue before? Do you know what could be causing it.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmoECAQ/zscaler-client-connector","lastmod":"2023-05-31T08:46:23.000Z","id":"0D54u00009evmoECAQ"} -->
## Zscaler client connector

- Source: https://community.zscaler.com/s/question/0D54u00009evmoECAQ/zscaler-client-connector
- Type: Q&A
- Posted: 2022-01-28T18:36:16.000Z
- Last activity: 2023-05-31T08:46:23.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Would like to know if below configuration is achievable…
 I would like to rollout Zscaler client connector to all users and Zscaler app should Auto sign based on current user user name and credentials…? We are using SAML authentication.

I mean , without user intervention Zscaler app should auto sign.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmoHCAQ/video-confernece-application-questions-tunnel-10","lastmod":"2023-07-06T13:01:16.000Z","id":"0D54u00009evmoHCAQ"} -->
## Video confernece application questions - Tunnel 1.0

- Source: https://community.zscaler.com/s/question/0D54u00009evmoHCAQ/video-confernece-application-questions-tunnel-10
- Type: Q&A
- Posted: 2022-01-27T13:40:15.000Z
- Last activity: 2023-07-06T13:01:16.000Z
- Answers: 10
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Recently we have been challenged with bypassing web conference traffic via the ZCC tunnel in order to better tune video conference application performance. Seems that only web traffic used for signaling traverses the tunnel while the main audio/video traffic bypasses the ZCC tunnel since the real time application traffic is mainly UDP traffic. I have been tasked with weighing out if the signaling traffic could interfere or cause issues for these types of traffic and determine if there were any advantages of updating our PAC files to bypass said traffic altogether. Thoughts?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmoJCAQ/experience-zapp-strictenforcement","lastmod":"2023-05-31T08:13:28.000Z","id":"0D54u00009evmoJCAQ"} -->
## Experience Z-App & Strictenforcement

- Source: https://community.zscaler.com/s/question/0D54u00009evmoJCAQ/experience-zapp-strictenforcement
- Type: Q&A
- Posted: 2022-01-25T10:45:12.000Z
- Last activity: 2023-05-31T08:13:28.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

we are currently testing deployment with strictenforcement and I would like to let you know about some caveats/issues we ran into.

Initial situation:

- we use AzureAD as IDP and doing all deployment via AzureAD/Autopilot/intune.
- our users have no local admin permissions
- on all our clients there is a personal local admin account which some users e.g. can use to install software
- we have SSL inspection enabled
- we configured app&fwd profile with **Tunnel 1.0**, dedicated pac-files with exemptions
- Z-App MSI is deployed with switches STRICTENFORCEMENT, POLICYTOKEN, CLOUDNAME, USERDOMAIN
- latest Z-App Version 3.6.1.26 (same applied to 3.7.x.xx, but we rolled back for testing)

In general this setup works for our users as configured and user is automatically signed in to Zscaler with his AzureAD-Accont and all policies work as expected.

As soon as the user logs in with his personal local admin account we would expect:

- no Internet access except to configured exemptions via pac-files-bypasses
- Z-App pops up with login screen stating user have to sign in due to corporate security requirements
- Internet access possible after manual authentication with username/password/mfa.

What really happened:

- Z-App starts instantly showing our Microsoft IDP SSO page. After some time (30s) Z-App-Auth times out with an according message and Z-App screen switches back from our MS SSO to Z-App Loginscreen stating “Internet Access Blocked?, only to switch back to MS SSO page few seconds later on. This flapping keeps going on endless.
- Logs show Z-App tries to authenticate the local user (lets call it “supervisor?) by using “supervisor@company.tld?. Of course this user is not known within our IDP and therefore login fails - obviously because of USERDOMAIN is set
- User can still access Internet “somehow? without login, at least some pages using SSL. Some pages work, others do not (e.g. [https://www.cnn.com](https://www.cnn.com) worked whereas [https://www.spiegel.de](https://www.spiegel.de) did not). We initially assumed this has to to with IPv6 and disabled IPv6 in fwd-profile, but no success. We double checked pac-file exemptions and even removed all exemptions, no success either. Internet access still worked for a majority of tested sites. We did not drilled down why some sites worked and other didn’t. An idea was it could be caused by sites using QUIC or HSTS but as I said, we did not investigate any further.

Fixes/Workarounds:

- To disable Internet access completly for local admin user without authenticated Z-App we had to switch fwd-profile to **Tunnel 2.0** and configure bypasses in fwd AND app pac-files as stated somewhere in [help.zscaler.com](http://help.zscaler.com). Calling http-sites now shows a hint to login with Z-App, https-sites just do not work (ERR_CONNECTION_CLOSED). I have opted for Tunnel 1.0 in the beginning as it is easier to setup and maintain and seemed sufficient for our scenario.
- To disable signin-page-flapping and authentication attempts with “supervisor@company.tld? we removed the USERDOMAIN-switch.

Yet unsolved issues:

- After removal of USERDOMAIN-switch the app-login-screen-flapping is gone, but automatically signing in a new user to Z-App by SSO & IWA does not work anymore. A new user now must manually login to Z-App once by entering his mailaddress.
- We now have to check for new issues as no policies or intune-scripts are applied (because of “no internet?) until the user manually authenticates in Z-App.

Maybe the issues mentioned here [https://community.zscaler.com/t/msi-version-installed-zscaler-client-connector-accepting-bogus-email-id/14974](https://community.zscaler.com/t/msi-version-installed-zscaler-client-connector-accepting-bogus-email-id/14974) are also related to strictenforcement w/ Tunnel 1.0.

BR
 Manuel
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmoMCAQ/network-mapping-windows-http-website","lastmod":"2023-05-31T08:13:28.000Z","id":"0D54u00009evmoMCAQ"} -->
## Network mapping windows (http website)

- Source: https://community.zscaler.com/s/question/0D54u00009evmoMCAQ/network-mapping-windows-http-website
- Type: Q&A
- Posted: 2022-01-20T11:04:22.000Z
- Last activity: 2023-05-31T08:13:28.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello:
 When I map a SharePoint site http site to “My Computer? and I have ZCC after 5 minutes if I try to access any file it asks me again for credentials.
 If I don’t use ZCC (just pac file) this never fails.

I happens with windows 10, I’ve tried 1703 and 21H2 so it is not related to the windows version.

anybody has this issue?, how did you solve it?

regards.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmoNCAQ/zscaler-app-auto-reenable-feature-required","lastmod":"2023-05-31T08:46:26.000Z","id":"0D54u00009evmoNCAQ"} -->
## Zscaler App Auto reenable feature required

- Source: https://community.zscaler.com/s/question/0D54u00009evmoNCAQ/zscaler-app-auto-reenable-feature-required
- Type: Q&A
- Posted: 2022-01-20T10:52:03.000Z
- Last activity: 2023-05-31T08:46:26.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Team,
 We are currently using Zscaler APP version 3.6.1.20.
 When we disable it using admin account it will keep it in a disable state is there any new feature where we can reenable it with manual intervention
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmoOCAQ/using-dedicated-proxy-port-on-zcc","lastmod":"2023-07-06T13:01:34.000Z","id":"0D54u00009evmoOCAQ"} -->
## Using Dedicated Proxy Port on ZCC

- Source: https://community.zscaler.com/s/question/0D54u00009evmoOCAQ/using-dedicated-proxy-port-on-zcc
- Type: Q&A
- Posted: 2022-01-19T20:25:54.000Z
- Last activity: 2023-07-06T13:01:34.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We recently ran into a problem with Source IP Anchoring on iOS devices running Zscaler Client Connector (ZCC). As per below, SIPA is only supported on Tunnel 2 but ZCC on iOS only supports Tunnel 1.

help.zscaler.com
##### [Configuring Source IP Anchoring | Zscaler](https://help.zscaler.com/zia/configuring-source-ip-anchoring)

How to enable and configure Source IP Anchoring to selectively forward traffic processed by Zscaler Internet Access (ZIA) to the destination servers using a source IP address of your choice.

We decided to go with Dedicated Proxy Port for the iOS devices as a way of mitigation but the only way to enforce this (as far as I can tell) is by using the “Dedicated Proxy Port? setting in the mobile dashboard.

help.zscaler.com
##### [About Dedicated Proxy Ports | Zscaler](https://help.zscaler.com/zscaler-client-connector/about-dedicated-proxy-ports)

Information about Dedicated Proxy Port settings.

Since this is a global setting for the entire tenant I’m wondering what effect this could have to the other devices i.e. Windows, Mac, etc.

Has anyone on this forum used this setting before? Has anyone solved this issue in a different way?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmoQCAQ/about-application-version-setting-of-zapp","lastmod":"2023-05-31T08:46:29.000Z","id":"0D54u00009evmoQCAQ"} -->
## About application version setting of zapp

- Source: https://community.zscaler.com/s/question/0D54u00009evmoQCAQ/about-application-version-setting-of-zapp
- Type: Q&A
- Posted: 2022-01-19T07:51:40.000Z
- Last activity: 2023-05-31T08:46:29.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

If I set the following in “Apdate Settings? of “Client Connector App Store? setting of ZCC management portal, do I need to press “Update App? on the client PC?
 Automatic Rollout: Always Latest Version

Also, if I need to press “Update App? on the client PC, do I need to run it on the PC with administrator privileges?

Please let me know if you know!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmoRCAQ/meaning-of-the-message-displayed-on-ipzscalercom","lastmod":"2023-07-06T12:53:19.000Z","id":"0D54u00009evmoRCAQ"} -->
## Meaning of the message displayed on ip.zscaler.com

- Source: https://community.zscaler.com/s/question/0D54u00009evmoRCAQ/meaning-of-the-message-displayed-on-ipzscalercom
- Type: Q&A
- Posted: 2022-01-18T21:09:09.000Z
- Last activity: 2023-07-06T12:53:19.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Community.

In my environment the user is using ZCC and in the PAC file the Global Public Service Edge IP address is configured as a proxy. Communications destined to the proxy reach the Zscaler cloud via a GRE registered to a known location.

If you are logged in to ZCC and go to [ip.zscaler.com](http://ip.zscaler.com), you will go through Zscaler, but you will see “You’re logged out of your company’s security service? is displayed.

What does this message mean, and why am I getting this message when I am logged into ZCC?" What is the difference between this and the “Would you like to logout? message?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmogCAA/the-local-traffic-isnt-being-captured-by-fiddler","lastmod":"2023-05-31T08:46:26.000Z","id":"0D54u00009evmogCAA"} -->
## The local traffic isn't being captured by fiddler

- Source: https://community.zscaler.com/s/question/0D54u00009evmogCAA/the-local-traffic-isnt-being-captured-by-fiddler
- Type: Q&A
- Posted: 2022-01-13T14:46:12.000Z
- Last activity: 2023-05-31T08:46:26.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Techies,
 My user is a developer and using fiddler to trace the internet and local traffic.
 I have set up wverything as described in article- [Using Fiddler with Zscaler Client Connector | Zscaler](https://help.zscaler.com/z-app/using-fiddler-zscaler-app) . He is now able to capture Internet traffic via fiddler but not the local(intranet) traffic. Please suggest what needs to be checked here?
 I have selected forwarding modes as “tunnel with LP? with packet filter driver and used custom pac there( as described in article).
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmoiCAA/msi-version-installed-zscaler-client-connector-accepting-bogus-email-id","lastmod":"2023-05-31T08:46:29.000Z","id":"0D54u00009evmoiCAA"} -->
## MSI version installed Zscaler Client Connector accepting bogus email id

- Source: https://community.zscaler.com/s/question/0D54u00009evmoiCAA/msi-version-installed-zscaler-client-connector-accepting-bogus-email-id
- Type: Q&A
- Posted: 2022-01-12T21:39:01.000Z
- Last activity: 2023-05-31T08:46:29.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

After installing MSI , ZCC is accepting bogus email id and not requesting for password .
 Email Id which was initially used to log in is log out . Then tried with some other bogus user id and it is not directly log in without password request .

Example – Suppose I have user in AD [Shiela@xyz.abc]

I can install this Shiela and she will be prompted by STRICTENFORCEMENT to enter her credentials before going to the Internet, When I enter e.g. fraud@xyz.abc and can browse thru zscaler without any issues, no group policy will be applied because there is NO REAL ACCOUNT for FRAUD account on AD.

Used below link to customize installation option -

help.zscaler.com
##### [Customizing Zscaler Client Connector with Install Options for MSI | Zscaler](https://help.zscaler.com/z-app/customizing-zscaler-app-install-options-msi#CreateZAppMSTFile)

How to customize Zscaler Client Connector MSI file with install options by creating and deploying an MST, or running the MSI with command-line options.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmokCAA/automatic-update-rollout-nonpersistent-vdi-group-based-restrictions","lastmod":"2023-07-06T13:01:48.000Z","id":"0D54u00009evmokCAA"} -->
## Automatic update Rollout non-persistent vdi - Group Based restrictions

- Source: https://community.zscaler.com/s/question/0D54u00009evmokCAA/automatic-update-rollout-nonpersistent-vdi-group-based-restrictions
- Type: Q&A
- Posted: 2022-01-09T12:36:43.000Z
- Last activity: 2023-07-06T13:01:48.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Good day, trying to get my arms around non-persistent VDI deployment of ZCC and how to control automatic software updates.

The challenge on non-persistent VDIs with software updates are, is that upon machine creation the image is taken from a saved “image? file vmware uses, so the ZCC will attempt to update every time.

Goal is to enable auto-updates for the enterprise, but block them on non-persistent vdi.

I see the feature for group based rollout - so I could restrict the rollout based on an AD group. My challenge is that some users have both a laptop and VDI, so blocking by a user AD group (user attribute passed to ZIA on SAML assertion) is not really what I want, I want to block by the machine type, or attribute.

Has anyone had anyluck solving this problem, I really dont want to turn off updates for the entire account.

I was thinking maybe there was an install option to pass for the non-persistent VDI such that it would tell it to not check for updates, or an IP address I could block at the network level so it can not reach the update server.

Thanks for you
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmoxCAA/application-bypass-microsoft-teams","lastmod":"2023-05-31T08:46:37.000Z","id":"0D54u00009evmoxCAA"} -->
## Application Bypass - Microsoft Teams

- Source: https://community.zscaler.com/s/question/0D54u00009evmoxCAA/application-bypass-microsoft-teams
- Type: Q&A
- Posted: 2021-12-08T12:14:12.000Z
- Last activity: 2023-05-31T08:46:37.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We have observed that although MS Teams is a listed application that will be bypassed in Tunnel 2.0 as mentioned here [About Application Bypass Info | Zscaler](https://help.zscaler.com/zscaler-client-connector/about-application-bypass-info), we still get this traffic going to Zscaler and visible in the logs.

URL is [teams.microsoft.com](http://teams.microsoft.com)

Anyone seen such problem before ?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmp2CAA/zscaler-client-connector-script-to-log-out-end-user","lastmod":"2023-05-31T08:46:40.000Z","id":"0D54u00009evmp2CAA"} -->
## Zscaler Client Connector Script to log out end user

- Source: https://community.zscaler.com/s/question/0D54u00009evmp2CAA/zscaler-client-connector-script-to-log-out-end-user
- Type: Q&A
- Posted: 2021-12-01T21:29:33.000Z
- Last activity: 2023-05-31T08:46:40.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is there a script method to possibly log out a user from zscaler. I have some end users who tend to forget to log out of zscaler.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmp3CAA/macs-not-populating-the-service-status-field-in-the-zcc-portal-under-enrolled-devices","lastmod":"2023-07-06T11:45:03.000Z","id":"0D54u00009evmp3CAA"} -->
## MACs not populating the Service status field in the ZCC portal under Enrolled devices

- Source: https://community.zscaler.com/s/question/0D54u00009evmp3CAA/macs-not-populating-the-service-status-field-in-the-zcc-portal-under-enrolled-devices
- Type: Q&A
- Posted: 2021-11-29T20:48:13.000Z
- Last activity: 2023-07-06T11:45:03.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Team I’m looking into an issue with MACs not populating the Service status field in the ZCC portal under Enrolled devices. See below the MAC screenshot shows ZIA enabled status as “Not Applicable? but the Windows device shows the ZIA Enabled as “True?… Is this a bug or not implemented for MACs yet?
 [image (5)]

[[image (6)] image (6)692×378 55.5 KB](https://global.discourse-cdn.com/zscaler/original/2X/1/1736b6fe67ec5a5be473a0d01d7d67e2509ce47a.png)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmp8CAA/zscaler-client-connector-app-ssl-inspection-on-android-causing-issues-still","lastmod":"2025-09-22T11:32:56.000Z","id":"0D54u00009evmp8CAA"} -->
## Zscaler Client connector app - SSL inspection on Android causing issues still

- Source: https://community.zscaler.com/s/question/0D54u00009evmp8CAA/zscaler-client-connector-app-ssl-inspection-on-android-causing-issues-still
- Type: Q&A
- Posted: 2021-11-25T16:36:46.000Z
- Last activity: 2025-09-22T11:32:56.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

When SSL inspection is enabled for Android it breaks most apps on the Android mobile device, due to most Android apps having their certificates pinned within them.
 We cannot turn off SSL inspection because that would leave a security hole within our Android/Windows devices. We have been advised that we would need to list down all the url’s/domains that our apps connect to so that SSL inspection is by-passed against these. This would be a laborious process for around 200 apps, and would also introduce an administration burden for ongoing maintenance when we add new apps or when existing apps change their url’s/domains (of which we would not know when this would happen).
 From another thread back in 2020 you advised that “in upcoming release we will allow you to SSL bypass applications/domains/URL categories based on the device OS type from which the traffic is originating. That would help you workaround the android/chrome OS certificate pinning challenge, while ensuring security and visibility for browser based access on Windows?.
 Please can you provide an update on this, this is important for us.
 Thanks.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmp9CAA/os-supported-platform","lastmod":"2024-11-27T13:37:57.000Z","id":"0D54u00009evmp9CAA"} -->
## OS supported platform

- Source: https://community.zscaler.com/s/question/0D54u00009evmp9CAA/os-supported-platform
- Type: Q&A
- Posted: 2021-11-25T08:47:27.000Z
- Last activity: 2024-11-27T13:37:57.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Team,

I have this case Case Number : 03145437 : [KEIYO] ZCC Support OS Lists .

Customer is asking for a documentation which says each OS system like Windows, linux, android, macOS and so on which operating system is compatible with ZIA.

I know that there is this documentation:

[https://help.zscaler.com/z-app/step-step-configuration-guide-zscaler-app#windows-requirements](https://help.zscaler.com/z-app/step-step-configuration-guide-zscaler-app#windows-requirements)

However customer would like something more specified for instance:

- Windows server
- Windows 7 home edition.
- Windows 10 enterprise

He would like a documentation specifying all the suported platform (OS) if that support ZIA or not and if this is supported, he wants to know the version which it supports.

Please let me know if something else is needed from my side.

Best regards
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmpKCAQ/how-to-bypass-multiple-urls-with-one-definition-in-the-zcc-portal","lastmod":"2023-05-31T09:27:08.000Z","id":"0D54u00009evmpKCAQ"} -->
## How to bypass multiple URLs with one definition in the ZCC portal

- Source: https://community.zscaler.com/s/question/0D54u00009evmpKCAQ/how-to-bypass-multiple-urls-with-one-definition-in-the-zcc-portal
- Type: Q&A
- Posted: 2021-11-17T01:17:58.000Z
- Last activity: 2023-05-31T09:27:08.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I specified the URL of “[Box.com](http://box.com/)? in the “Host name or Ipaddress Bypass for vpn gateway? setting in the App Profile of the ZCC management portal.
 However, when a subdirectory or subdomain was added to the “[box.com](http://box.com/)? URL, it was no longer bypassed.
 Does anyone know how to specify all URLs including “[box.com](http://box.com/)? in the App Profile settings?

I want to minimize the number of URLs to set.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmpMCAQ/zcaler-client-connector-doesnt-work-in-china","lastmod":"2023-05-31T08:46:47.000Z","id":"0D54u00009evmpMCAQ"} -->
## Zcaler client connector doesn't work in China

- Source: https://community.zscaler.com/s/question/0D54u00009evmpMCAQ/zcaler-client-connector-doesnt-work-in-china
- Type: Q&A
- Posted: 2021-11-16T06:56:40.000Z
- Last activity: 2023-05-31T08:46:47.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Our company is a global company, we are deploying zscaler client connector to all global computers for internet protect when they are out of office. In other contries, no issue. But in China, we find the Zscaler auto proxy the data to Japan Zscaler datacenters There are three datacenters in China, by geographical location auto section, it should use China datacenter proxies first…

Anyone got the similar problem? And any solution for it?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmpOCAQ/full-tunnel-vpn-identification-zscaler-client-connector","lastmod":"2023-05-31T08:46:47.000Z","id":"0D54u00009evmpOCAQ"} -->
## Full Tunnel VPN Identification - Zscaler Client Connector

- Source: https://community.zscaler.com/s/question/0D54u00009evmpOCAQ/full-tunnel-vpn-identification-zscaler-client-connector
- Type: Q&A
- Posted: 2021-11-15T03:14:08.000Z
- Last activity: 2023-05-31T08:46:47.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Does the Zscaler Client Connector identifies a Full Tunnel VPN **only** if the **DEFAULT ROUTE** is installed ?

Else, are there any other ways as well which it will identify?

Thanks,
 Rahul
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmpQCAQ/boxcomurl%E3%82%A4%E3%83%91%E3%82%B9%E6%96%B9%E6%B3%95","lastmod":"2023-05-31T08:13:33.000Z","id":"0D54u00009evmpQCAQ"} -->
## box.comURL??イパス方法

- Source: https://community.zscaler.com/s/question/0D54u00009evmpQCAQ/boxcomurl%E3%82%A4%E3%83%91%E3%82%B9%E6%96%B9%E6%B3%95
- Type: Q&A
- Posted: 2021-11-12T17:13:33.000Z
- Last activity: 2023-05-31T08:13:33.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I specified the URL of “[Box.com](http://Box.com)? in the “Host name or Ipaddress Bypass for vpn gateway? setting in the App Profile of the ZCC management portal.
 However, when a subdirectory or subdomain was added to the “[box.com](http://box.com)? URL, it was no longer bypassed.
 Does anyone know how to specify all URLs including “[box.com](http://box.com)? in the App Profile settings?

I want to minimize the number of URLs to set.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmpbCAA/error-503-in-zapp-login","lastmod":"2023-05-31T08:13:37.000Z","id":"0D54u00009evmpbCAA"} -->
## Error 503 in ZAPP login

- Source: https://community.zscaler.com/s/question/0D54u00009evmpbCAA/error-503-in-zapp-login
- Type: Q&A
- Posted: 2021-11-08T10:36:09.000Z
- Last activity: 2023-05-31T08:13:37.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

What could be the issue for Error 503 in ZAPP login. Tried all possible domains
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmpdCAA/block-internet-access-until-user-login-with-zcc","lastmod":"2023-07-06T11:45:07.000Z","id":"0D54u00009evmpdCAA"} -->
## Block Internet Access until user login with ZCC

- Source: https://community.zscaler.com/s/question/0D54u00009evmpdCAA/block-internet-access-until-user-login-with-zcc
- Type: Q&A
- Posted: 2021-11-05T06:26:17.000Z
- Last activity: 2023-07-06T11:45:07.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Everyone,

Customer is using below setup to control their end-user Internet access. They have specific requirement enforce user login to Zscaler Client Connector for internet access. We have tried below community link. However. the deployment only applicable for Zscaler Client Connector IdP instead of OKTA as primary.

Does customer requirement able to configured with Zscaler Client Connector or we have to use other method e.g. PAC file to block all internet access?. Thanks

Current Infra

1. Zscaler Client Connector with OKTA (Authentication)
2. Base URL rule block ALL.

Community Post

Enforce users to use Zscaler before they do any Internet browsing
Client Connector
> How can I enforced all of the users to have to use Zscaler or block the internet access if they are not logged into Zscaler?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmpgCAA/client-connector-location-data","lastmod":"2023-05-31T08:46:47.000Z","id":"0D54u00009evmpgCAA"} -->
## Client Connector Location Data

- Source: https://community.zscaler.com/s/question/0D54u00009evmpgCAA/client-connector-location-data
- Type: Q&A
- Posted: 2021-11-04T13:44:01.000Z
- Last activity: 2023-05-31T08:46:47.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I have a customer who is currently deploying Zscaler in their environment. One of their use cases is tracking when someone is working from home or working from the office. They will be using the Client Connector as the means for the workforce to connect to the internet. The challenge is the way their traffic is backhauled it will still look like the same (since they still VPN in). Any recommendations on how to work around it or setup reporting?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmpiCAA/force-acceptance-of-permissions-zscaler-client-connector-on-mobile-device-with-intune","lastmod":"2023-05-31T08:13:37.000Z","id":"0D54u00009evmpiCAA"} -->
## Force Acceptance of permissions - ZScaler Client Connector on Mobile Device with Intune

- Source: https://community.zscaler.com/s/question/0D54u00009evmpiCAA/force-acceptance-of-permissions-zscaler-client-connector-on-mobile-device-with-intune
- Type: Q&A
- Posted: 2021-11-03T07:18:42.000Z
- Last activity: 2023-05-31T08:13:37.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi all,
 We are deploying ZScaler Client Connector on Mobile Device with Intune. As I see final user needs to accept permissions on the application in order to have it up and running.
 Is there any way we can force to the user that appears always this “pop up? until he accept the needed permissions?

Thank you and best regards
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmpkCAA/the-curious-case-of-zscaler-client-connector","lastmod":"2023-07-06T13:02:58.000Z","id":"0D54u00009evmpkCAA"} -->
## The Curious Case of Zscaler client connector

- Source: https://community.zscaler.com/s/question/0D54u00009evmpkCAA/the-curious-case-of-zscaler-client-connector
- Type: Q&A
- Posted: 2021-10-29T07:56:20.000Z
- Last activity: 2023-07-06T13:02:58.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

This is my first topic here, we just deployed ZCC for our environment but having a tough time in terms of traffic forwarding. Out setup use tunnel 2.0 with tunnel mode.

1. How do we avoid sending traffic to Zapp(PSE/CA) instead use local routing to go internet. I understand from all the documents that i got to bypass in forwarding pac and then app pac, but i still see traffic in Zscaler webinsights. and in some case we needed to bypass some url/ip in vpn bypass also to bypass completely.

All i want Zscaler is to bypass domains/IP so that my local firewall can take care of such traffic.

1. Any network diagram associate with ZCC traffic handling behavior, i received one from Zscaler PS team but it seems the App doesn’t handle the traffic that way.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmplCAA/winhttp-traffic-and-zcc-in-tunnel-v2-mode","lastmod":"2023-05-31T08:46:54.000Z","id":"0D54u00009evmplCAA"} -->
## WINHTTP traffic and ZCC in Tunnel v2 mode

- Source: https://community.zscaler.com/s/question/0D54u00009evmplCAA/winhttp-traffic-and-zcc-in-tunnel-v2-mode
- Type: Q&A
- Posted: 2021-10-28T12:29:42.000Z
- Last activity: 2023-05-31T08:46:54.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

if I have the following winhttp settings on my client and I am running ZCC in tunnel v2 mode will it catch this traffic ?

C:\Users\xxx>netsh winhttp show proxy

Current WinHTTP proxy settings:

```
Direct access (no proxy server).
```

Thanks Thomas
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmpnCAA/zsatunnelexe-and-lsass","lastmod":"2023-05-31T08:46:54.000Z","id":"0D54u00009evmpnCAA"} -->
## ZSATunnel.exe and LSASS

- Source: https://community.zscaler.com/s/question/0D54u00009evmpnCAA/zsatunnelexe-and-lsass
- Type: Q&A
- Posted: 2021-10-27T05:55:58.000Z
- Last activity: 2023-05-31T08:46:54.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We’re trying to enable Windows Defender ASR rules and one of the rules blocks processes from stealing credentials from LSASS.

ZSATunnel.exe is the only application that shows up when audit mode is enabled. Can you please advise if blocking ZSATunnel.exe from LSASS will impact any Z-App functionality? Thanks.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmppCAA/bandwidth-control-and-ztunnel-20","lastmod":"2023-05-31T09:24:53.000Z","id":"0D54u00009evmppCAA"} -->
## Bandwidth Control and Z-Tunnel 2.0

- Source: https://community.zscaler.com/s/question/0D54u00009evmppCAA/bandwidth-control-and-ztunnel-20
- Type: Q&A
- Posted: 2021-10-26T08:10:54.000Z
- Last activity: 2023-05-31T09:24:53.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi

Currently we send Z-Tunnel2.0 traffic via GRE (we’ve been told this is no longer recommended). We’d like to bypass GRE for any Z-Tunnel2.0 traffic (anything that’s encapsulated into ZT2.0 will be sent directly into Internet). So, how does this impact Bandwidth Control for the location?

Is Zscaler going to honor ZT2.0 source IP address (NAT’ed using the same IP as GRE endpoint configured as Static IP under specific location) and will apply that location’s bandwidth control policy? Or, is it going to treat these ZTunnels as if they are Road Warriors (with unknown location)?

Many thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmpsCAA/machine-tunnel-questions","lastmod":"2023-05-31T08:46:54.000Z","id":"0D54u00009evmpsCAA"} -->
## Machine Tunnel Questions

- Source: https://community.zscaler.com/s/question/0D54u00009evmpsCAA/machine-tunnel-questions
- Type: Q&A
- Posted: 2021-10-25T16:16:16.000Z
- Last activity: 2023-05-31T08:46:54.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

If a user is logged into a Windows machine with the screen locked and their ZPA authentication expires, does the Machine Tunnel automatically activate? If so, what happens when the user unlocks their screen? Will the Machine Tunnel stay connected until the user re-authenticates to ZPA? Just trying to understand how/if machine tunnel functions if a user is logged into the machine.

Also, if Machine Tunnel is active pre-login, and a user who is NOT enrolled in ZPA logs in to Windows, does the Machine tunnel stay connected?

Thanks.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmpuCAA/control-unified-communication-with-zia-client-connector","lastmod":"2023-05-31T08:13:37.000Z","id":"0D54u00009evmpuCAA"} -->
## Control Unified communication with ZIA & Client Connector

- Source: https://community.zscaler.com/s/question/0D54u00009evmpuCAA/control-unified-communication-with-zia-client-connector
- Type: Q&A
- Posted: 2021-10-25T03:25:37.000Z
- Last activity: 2023-05-31T08:13:37.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

Our current customer is using standard license which doesn’t include Application control features.

They would like to control certain Unified communication UCAAS e.g. Google Meet, GoToMeeting and etc.

Does URL Filtering policy able or have the option to control authorized or blocked the URL and services?. Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmpvCAA/exceptions-to-strict-enforcement","lastmod":"2024-09-10T06:28:12.000Z","id":"0D54u00009evmpvCAA"} -->
## Exceptions to Strict Enforcement

- Source: https://community.zscaler.com/s/question/0D54u00009evmpvCAA/exceptions-to-strict-enforcement
- Type: Q&A
- Posted: 2021-10-21T18:02:57.000Z
- Last activity: 2024-09-10T06:28:12.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello, I’m looking for a solution to a problem we are having. We use Strict enforcement on the ZSCaler Client Connector because we want users to be logged into ZCC while using the computer. We are still doing our initial deployment across the company. Occasionally we deploy it and the SSO doesn’t work either because of MFA or the user wasn’t provisioned or whatever. In these situations where ZCC is blocking internet access, we have to get the user on the phone and have them click through exiting the application from the system tray. We then uninstall it, troubleshoot, and redeploy.

My question is: Is there a way to remotely unblock access to the internet while still using Strict Enforcement? Maybe not all of the internet, but at least our remote tool. We could then see that the SSO authentication failed and then remotely send an uninstall command to remove ZScaler. Currently Strict Enforcement blocks ALL internet traffic so we cannot send a remote command to uninstall it. I know there are onetime passwords people can use to not authenticate, but I’d like for this deployment to be completely in the background without any user involvement.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmq7CAA/can-we-proxy-dns-request-through-zcc-and-apply-policy-on-returned-values","lastmod":"2023-07-18T02:06:18.000Z","id":"0D54u00009evmq7CAA"} -->
## Can we proxy DNS request through ZCC and apply policy on returned values?

- Source: https://community.zscaler.com/s/question/0D54u00009evmq7CAA/can-we-proxy-dns-request-through-zcc-and-apply-policy-on-returned-values
- Type: Q&A
- Posted: 2021-10-12T17:58:26.000Z
- Last activity: 2023-07-18T02:06:18.000Z
- Answers: 7
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We basically want ZPA to send only RFC 1918 to a ZPA wildcard domain? Names, not Ip/Networks? Customer doesn’t have a way, aka subdomain, to distinguish public Internet vs internal if they use a wildcard domain to discover apps. If they send everything to ZPA, it will break their public facing websites. Creating a bypass segment won’t work either as they don’t even know the URL’s that need to be excluded from ZPA other than if it’s based on private IP’s.

In other words, we want ZCC to proxy DNS requests and if the response IP is within 1918 space we forward to ZPA or else let the external sites go direct. How can I enable this solution?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmq9CAA/upload-speed-is-almost-0-when-using-zccany-version","lastmod":"2023-07-06T13:03:31.000Z","id":"0D54u00009evmq9CAA"} -->
## Upload speed is almost 0 when using ZCC(any version)

- Source: https://community.zscaler.com/s/question/0D54u00009evmq9CAA/upload-speed-is-almost-0-when-using-zccany-version
- Type: Q&A
- Posted: 2021-10-07T16:51:02.000Z
- Last activity: 2023-07-06T13:03:31.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Techies,

Upload speed is almost 0 when using ZCC(any version). Checked without Zscaler, speed for upload and download is too good.User is working from home. Upload speed is 0mbps when using ZCC.What could be the issue here?This is the single user who is facing this issue as of now.
 Please help!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqACAQ/full-tunnel-vpn-zscaler-client-connector-configuration","lastmod":"2023-05-31T08:47:02.000Z","id":"0D54u00009evmqACAQ"} -->
## Full Tunnel VPN - Zscaler Client Connector Configuration

- Source: https://community.zscaler.com/s/question/0D54u00009evmqACAQ/full-tunnel-vpn-zscaler-client-connector-configuration
- Type: Q&A
- Posted: 2021-10-06T04:02:50.000Z
- Last activity: 2023-05-31T08:47:02.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

Some of our users are using a client VPN which is configures as a Full Tunnel.
 As it is a Full Tunnel VPN, all the traffic is routed to the VPN client. Hence we have added the DNS servers to trusted criteria which will populate when connected to Client VPN & selected the Forwarding Profile in VPN Trusted Network as NONE.

So when the user is connected to client VPN, ZCC identifies it is connected to a VPN Network and goes to DISABLED identifying it as a VPN Trusted Network.

Now the issue here is end-users are able to access all the unrestricted sites on the Client VPN as ZCC is disabled on the system.

Is our configuration correct ? or we can also restrict internet access even though users are using a Full Tunnel VPN?

Please share your thoughts on this.

Thanks,
 Rahul V
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqBCAQ/ios-client-connector-ms-defender-for-endpoint","lastmod":"2023-05-31T09:08:34.000Z","id":"0D54u00009evmqBCAQ"} -->
## iOS Client Connector & MS Defender for Endpoint

- Source: https://community.zscaler.com/s/question/0D54u00009evmqBCAQ/ios-client-connector-ms-defender-for-endpoint
- Type: Q&A
- Posted: 2021-10-05T11:00:30.000Z
- Last activity: 2023-05-31T09:08:34.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

All of our iOS devices have client connector deployed to them. We are interested in onboarding these devices into [security.microsoft.com](http://security.microsoft.com) by using MS Defender for Endpoint.

Defender for Endpoint requires it’s own VPN config to be setup on the iPhone, which means you have a Zscaler VPN profile, and a Defender for Endpoint VPN profile - of course you can’t use both.

If you turn on the Zscaler profile, you lose Defender for Endpoint web protection, if you enable Endpoint web protection, well Zscaler stops working.

Anyone figured out how to get both working?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqFCAQ/poor-performance-with-zcc-on-macos-bigsur","lastmod":"2023-05-31T09:24:56.000Z","id":"0D54u00009evmqFCAQ"} -->
## Poor Performance with ZCC on MacOS BigSur

- Source: https://community.zscaler.com/s/question/0D54u00009evmqFCAQ/poor-performance-with-zcc-on-macos-bigsur
- Type: Q&A
- Posted: 2021-10-04T08:01:31.000Z
- Last activity: 2023-05-31T09:24:56.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

We’re actual running a PoC with Zscaler and we are confronted with some strange performance issues with ZCC on MacOS. The support seems a little bit clueless so I want to ask if someone facing the same issues or has an idea what could be wrong.

We’re using the following configuration:

- MacOS Big Sur and Catalina, lastet Patch level, only Intel CPU
- Tunnel Version 2.0
- ZCC version 3.2.5.5 and 3.4.0.143 (beta)
- PAC File routes traffic only through german datacentres Frankfurt and Munich
- WAN connection tested via fibre (Deutsche Telekom 300Mb/sec syncron) and different coaxial cable connections in homeoffices (Vodafone up to 600 Mbit /sec down and 40 Mbit /sec upstream)
- same Forwarding Profile for all Clients

If we do some speed tests via [fast.com](http://fast.com), [gateway.zscaler.net/test](http://gateway.zscaler.net/test) or simply download some testing files via “Speedtest Belwü? we facing a speed degradation with activated ZCC to more then a half of the regular speed without Zscaler.
 At the homeoffice from one colleague the speed drops vom 240 to 65-70 downstream.
 Tested with several devices and WAN connections.

With windows clients there are no speed issues, sure a little bit degraded performance because routing through Zscaler network but not this huge impact at all.

We disabled and uninstalled Endpoint Security, disabled the MacOS Firewall and tested with devices complete separated from MDM policies. We also thought that it has something to do with carrier grade NAT at Vodafone WAN connections, but the issue persists also on our fibre connection which is only using IPv4.

The whole issue is only mostly observable by WAN connection with more than ca. 150MBit /sec downstream. With less the full bandwidth is used.

I’m happy for any suggestions or ideas.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqJCAQ/zcc-service-status-help","lastmod":"2023-07-06T13:03:41.000Z","id":"0D54u00009evmqJCAQ"} -->
## ZCC Service Status Help

- Source: https://community.zscaler.com/s/question/0D54u00009evmqJCAQ/zcc-service-status-help
- Type: Q&A
- Posted: 2021-09-29T16:33:17.000Z
- Last activity: 2023-07-06T13:03:41.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I am looking in the help portal for any information to this section of the ZCC Registered Device Details, however I cannot find any information pertaining to what these items are.

Mouse over shows some info, but i seems to be a new feature in 3.6, however some of my devices are showing ZIA health active, and some as ZIA health inactive.

Can Someone help explain what the Health portions of this SC are?

[ZCC Health Service]

Any assistance would be great, thank you

Regards,
 Jamison W.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqKCAQ/direct-sso-access-to-client-connector-portal-mobilezscalertwonet","lastmod":"2023-05-31T08:47:06.000Z","id":"0D54u00009evmqKCAQ"} -->
## Direct SSO access to Client Connector Portal (mobile.zscalertwo.net)

- Source: https://community.zscaler.com/s/question/0D54u00009evmqKCAQ/direct-sso-access-to-client-connector-portal-mobilezscalertwonet
- Type: Q&A
- Posted: 2021-09-29T14:36:33.000Z
- Last activity: 2023-05-31T08:47:06.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Has anyone found a way to setup direct SSO access to the Client Connector Portal ([mobile.zscalertwo.net](http://mobile.zscalertwo.net) for us)? We have SSO setup to access both the ZIA and ZPA admin portals (Azure AD). However to access the Client Connector Portal via SSO we currently have to first login to either the ZIA or ZPA admin portals, then click “Client Connector? to access the Client Connector Portal. Looking to save a step here if possible.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqPCAQ/ftp-has-broken-on-tunnel-20","lastmod":"2023-05-31T09:08:39.000Z","id":"0D54u00009evmqPCAQ"} -->
## FTP has broken on Tunnel 2.0

- Source: https://community.zscaler.com/s/question/0D54u00009evmqPCAQ/ftp-has-broken-on-tunnel-20
- Type: Q&A
- Posted: 2021-09-29T14:14:21.000Z
- Last activity: 2023-05-31T09:08:39.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We recently cut all of our users over to our new tunnel 2.0 app profile and after doing so, have seen more and more issues with FTP trickle in. We quickly realized we needed to enable FTP control, so we turned on support for Native FTP and FTP over HTTP, and created a URL category to input the allowed destinations. We also added FTP and Implicit FTPS to the allowed network services on our Cloud Firewall policy. We continue to face issues and we need to figure out how to get this resolved. My Zscaler case has gotten me nowhere so far, so I’m hoping the Zscaler community can help me out a bit.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqQCAQ/tunnel-20-and-connecting-to-lan-based-services","lastmod":"2023-05-31T08:47:06.000Z","id":"0D54u00009evmqQCAQ"} -->
## Tunnel 2.0 and Connecting to LAN Based Services

- Source: https://community.zscaler.com/s/question/0D54u00009evmqQCAQ/tunnel-20-and-connecting-to-lan-based-services
- Type: Q&A
- Posted: 2021-09-25T07:05:38.000Z
- Last activity: 2023-05-31T08:47:06.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

All,

I’m new to using ZIA.

I’ve a tunnel 2.0 client connector setup in an office.
 Now, a LAN based program that used to connect to a license server in the same subnet is not working.
 There’s no option setting on the program (guessing its using broadcast, perhaps?) to connect to the license server.

Would like to ask, whether there is other user who has similar setup?
 I’m wondering whether I can add the subnet / 24 into the Forwarding Profile VPN gateway or exclusion list for the LAN traffic.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqUCAQ/client-connector-saml-sign-issue","lastmod":"2023-05-31T08:47:10.000Z","id":"0D54u00009evmqUCAQ"} -->
## Client connector SAML Sign Issue

- Source: https://community.zscaler.com/s/question/0D54u00009evmqUCAQ/client-connector-saml-sign-issue
- Type: Q&A
- Posted: 2021-09-22T23:46:30.000Z
- Last activity: 2023-05-31T08:47:10.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Team,

we have run in to an issue where once the client connector is pushed to machines and it is unable to sign in within the corporate network, it seems to be work fine off corporate network which suspected to be issue with connectivity to IDP (Azure AD). Once sign in from offnet and bring the laptop over to corporate network connectivity to Zscaler via Ztunnel 2.0 continue to work.

Has anyone come across with this issue and anyone know What IP addresses needs to be allowed on corporate firewalls to allow SAML SSO with Azure AD? Also believe there is no way to route SAML traffic to IDP via zscaler unless user is logged in and ztunnel is built successfully.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqXCAQ/zia-client-connector-inhibiting-cloud-app-discovery-using-defender-for-endpoint","lastmod":"2023-05-31T08:13:41.000Z","id":"0D54u00009evmqXCAQ"} -->
## ZIA Client connector inhibiting Cloud App Discovery using Defender for Endpoint

- Source: https://community.zscaler.com/s/question/0D54u00009evmqXCAQ/zia-client-connector-inhibiting-cloud-app-discovery-using-defender-for-endpoint
- Type: Q&A
- Posted: 2021-09-15T21:38:48.000Z
- Last activity: 2023-05-31T08:13:41.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I have Windows 10 laptops with ZIA Client Connector which is offering a proxy service at [http://127.0.0.1:9000/localproxy](http://127.0.0.1:9000/localproxy). I’ve also rolled out Defender for Endpoint to these machine.

What I’m finding is that the Cloud App Discovery based on Defender telemetry is very patchy. I’m getting alerts when ZSATunnel.exe connects to an unsanctioned URL. However, the overall visibility in Microsoft Cloud App Security of the apps being used is very limited.

To investigate this, I’ve used “SwitchOmega? add-in to direct Edge to go Direct and bypass the System Proxy. After doing this, the cloud apps access by these test machines is accurately reported in Microsoft Cloud App Security.

I need to find a way so that Defender correctly logs the web traffic even when ZIA Client Connector is running as a proxy and tunneling the traffic to a ZEN node. NB I want to use Microsoft Cloud App Security as my device management portal to sanction and unsanction web apps rather than be jumping over to the ZScaler admin portal.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqZCAQ/zscaler-is-picking-up-different-id","lastmod":"2023-05-31T08:47:12.000Z","id":"0D54u00009evmqZCAQ"} -->
## Zscaler is picking up different ID

- Source: https://community.zscaler.com/s/question/0D54u00009evmqZCAQ/zscaler-is-picking-up-different-id
- Type: Q&A
- Posted: 2021-09-14T14:23:42.000Z
- Last activity: 2023-05-31T08:47:12.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Techies,
 My user is unable to access Microsoft applications because she uses [Fname.Lname@xyz.com](mailto:Fname.Lname@xyz.com) for logging in to MS Applications where as ZAPP is logged in for that user via [Fname.Lname@abc.com](mailto:Fname.Lname@abc.com) domain. I tried to logout user from ZAPP forcefully from ID: [Fname.Lname@abc.com](mailto:Fname.Lname@abc.com) as I had to try with another ID from which MS Apps could be logged in but after logging in again( after selection of cloud), ZAPP was not asking her to log in via the [ID:Fname.Lname@xyz.com](mailto:ID:Fname.Lname@xyz.com). Rather it turned ON automatically with old ID which is [Fname.Lname@abc.com](mailto:Fname.Lname@abc.com).
 This is causing issue to login to her other applications like Teams,outlook. Need assistance in this please!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqgCAA/using-remotepc-with-zscaler-enforcement-option","lastmod":"2023-05-31T08:47:16.000Z","id":"0D54u00009evmqgCAA"} -->
## Using RemotePC with Zscaler enforcement option

- Source: https://community.zscaler.com/s/question/0D54u00009evmqgCAA/using-remotepc-with-zscaler-enforcement-option
- Type: Q&A
- Posted: 2021-09-08T17:48:50.000Z
- Last activity: 2023-05-31T08:47:16.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I am using Zscaler Client Connector with the STRICTENFORCEMENT option configured. We have techs that are working with users or they are setting up new machines that have not signed into the Client Connector and they are not able to connect remotely with RemotePC. Is there a way for me to bypass the STRICTENFORCEMENT option so they can remote into the PC they are working on if no one has signed into the Client Connector yet?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqhCAA/policy-update-failes-intermittently","lastmod":"2023-07-06T11:45:25.000Z","id":"0D54u00009evmqhCAA"} -->
## Policy update failes intermittently

- Source: https://community.zscaler.com/s/question/0D54u00009evmqhCAA/policy-update-failes-intermittently
- Type: Q&A
- Posted: 2021-09-08T13:31:23.000Z
- Last activity: 2023-07-06T11:45:25.000Z
- Answers: 19
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello folks,

all of our user experience intermittently policy updates since some days. When manually refreshing policy via button in ZCC we got the following error:

[image]

Sometimes it works instantly, sometime we need 3-10 retries. After repeated pressing “refresh policy? it eventually works. We use ZCC V3.5.0.108.

Any ideas? Hints? ZPA/ZIA are running fine, we even tried forcefully remove one client and competely reauth ZCC, which worked without issues.

Thanks and BR
 Manuel
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqkCAA/8-error-while-logging-to-zapp","lastmod":"2023-07-06T12:53:35.000Z","id":"0D54u00009evmqkCAA"} -->
## -8 error while logging to ZAPP

- Source: https://community.zscaler.com/s/question/0D54u00009evmqkCAA/8-error-while-logging-to-zapp
- Type: Q&A
- Posted: 2021-09-07T13:09:19.000Z
- Last activity: 2023-07-06T12:53:35.000Z
- Answers: 15
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Getting -8 error while logging to ZAPP…
 What could be the issue?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqlCAA/zcc-or-gre-tunnel-in-office-network","lastmod":"2023-05-31T09:08:36.000Z","id":"0D54u00009evmqlCAA"} -->
## ZCC or GRE Tunnel in Office network

- Source: https://community.zscaler.com/s/question/0D54u00009evmqlCAA/zcc-or-gre-tunnel-in-office-network
- Type: Q&A
- Posted: 2021-09-06T10:24:27.000Z
- Last activity: 2023-05-31T09:08:36.000Z
- Answers: 18
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Which is best ZCC or GRE tunnel inside office network ?

What are advantages/disadvantages of using ZCC / GRE tunnel for web traffic routing via Zscaler Cloud?

Thanks.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqqCAA/client-connector-opens-with-white-screen","lastmod":"2023-07-06T11:45:26.000Z","id":"0D54u00009evmqqCAA"} -->
## Client connector opens with white screen

- Source: https://community.zscaler.com/s/question/0D54u00009evmqqCAA/client-connector-opens-with-white-screen
- Type: Q&A
- Posted: 2021-08-31T10:58:11.000Z
- Last activity: 2023-07-06T11:45:26.000Z
- Answers: 13
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi all,

We recently rolled out zScaler in our environment, we had one or two issues but one remains which I am unable to resolve

Essentially after installation (and this has been done via SCCM and thereafter manually using both an MSI and EXE without any installation switches) the client connector application (3.4.1.4 but also tried 3.5.0.108) just opens and displays a blank, white screen. It does not ask for credentials and does not get any further.

Anyone seen this before and have any ideas?

cheers!

[[Screenshot 2021-08-31 115522] Screenshot 2021-08-31 115522977×726 11.6 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O68JAAS)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqxCAA/ztunnel20-always-bypasses-tcp53","lastmod":"2023-07-06T13:03:49.000Z","id":"0D54u00009evmqxCAA"} -->
## Z-Tunnel2.0 always bypasses TCP53

- Source: https://community.zscaler.com/s/question/0D54u00009evmqxCAA/ztunnel20-always-bypasses-tcp53
- Type: Q&A
- Posted: 2021-08-24T02:31:29.000Z
- Last activity: 2023-07-06T13:03:49.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi.

// We’ve been told that this is the FEATURE by ZScaler support.
 // But we want to restrict our employee not to directly access to the internet.
 // Does someone has any idea?

We have configured ZCC to send almost all traffic to ZEN with Z-Tunnel 2.0.
 Of course some types of traffics are configured to be bypassed due to the necessity of local function, such as DNS,DHCP on home router.
 But we’ve expected traffics directed to internet to go through ZEN without any exception.

However, we found that when we use TCP53 then the traffics are always bypassed.
 We can directly access to the internet using TCP53.

We’ve conducted several tests with my AWS instance,binding some server application to TCP53.
 For example…

case1) HTTP-Proxy(squid) binded to TCP53
 ideal) [Browser] → [ZEN] → [AWS/Squid:TCP53] → [Origin Server]
 actual) [Browser] → [AWS/Squid:TCP53] → [Origin Server]

In this case,using TCP53, I was able to access the internet without any policy enforcement.
 // Proxy configuration is enforced by ZCC.
 // So in my test,I used FireFoxPortable.

case2) SMTP-Server(postfix) binded to TCP53.
 ideal) [MUA] → [ZEN] → [AWS/postfix:TCP53]
 actual) [MUA] → [AWS/postfix:TCP53]

In each test I found provider-provided global IP address(NOT ZEN IP) logged on server log.
 So traffics should have been sent directly to the internet, bypassing ZScaler.

We want our employee not to directly access the internet.
 Does anyone have possible solution?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmqyCAA/zcc-blocking-app-install-from-mdm","lastmod":"2023-05-31T08:13:41.000Z","id":"0D54u00009evmqyCAA"} -->
## ZCC blocking app install from MDM

- Source: https://community.zscaler.com/s/question/0D54u00009evmqyCAA/zcc-blocking-app-install-from-mdm
- Type: Q&A
- Posted: 2021-08-23T21:02:58.000Z
- Last activity: 2023-05-31T08:13:41.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We use Workspace ONE for our MDM solution, and have some app installations being blocked by ZCC. I already have the [awmdm.com](http://awmdm.com) domain added to whitelist, but apps will not install if ZCC is enabled. I can disable ZCC and the required apps install just fine.

Edit: these are Windows apps
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmrECAQ/user-name-associated-with-cookie-in-client-connector","lastmod":"2023-05-31T08:13:45.000Z","id":"0D54u00009evmrECAQ"} -->
## User name associated with cookie in client connector

- Source: https://community.zscaler.com/s/question/0D54u00009evmrECAQ/user-name-associated-with-cookie-in-client-connector
- Type: Q&A
- Posted: 2021-07-28T14:20:21.000Z
- Last activity: 2023-05-31T08:13:45.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

My organisation has implemented ZIA services and used a perpetual authentication cookie to authorise use for ZIA. The user base is using Client connector to provide Zscaler services.

The issue is that the UPN for users has been changed on our domain. All users now show an incorrect UPN in the Client connector. This still authenticates but has led to an issue when we have begun to provision ZPA services. the ZPA service is trying to associate with the new UPN but the client connector is still associated with the old UPN. Due to this mismatch, we cannot provision seamlessly.

query: Is there any method of forcing the Client connector authentication cookie to refresh without asking all the user base to press login on the client Connector GUI?

The issue is that if we simply remove the affinity between user and the device in the client connector portal, all users will be logged out of the client connector and this will then fail open until a manual login is selected by the user. This compromises the users security protection without the user being aware and could lead to a breach.

Ideally a solution would create a situation where the client connector cookie can be expired and Single Sign on would take place to generate a new authentication cookie. The new authentication cookie would generate with the updated UPN. This would allow ZIA and ZPA to integrate effectively.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmrICAQ/public-wifi-connection-issue","lastmod":"2023-05-31T08:47:38.000Z","id":"0D54u00009evmrICAQ"} -->
## Public wifi connection issue

- Source: https://community.zscaler.com/s/question/0D54u00009evmrICAQ/public-wifi-connection-issue
- Type: Q&A
- Posted: 2021-06-26T00:33:23.000Z
- Last activity: 2023-05-31T08:47:38.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

With Zscaler Client connector installed, does anyone experience connection issue with public WIFI, like Starbucks?
 I do have the option turn the zscaler off for 10 minutes when captive portal detected.

thanks,

Tao
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmrNCAQ/can-android-zcc-not-install-on-x86x8664-devices","lastmod":"2023-05-31T08:47:38.000Z","id":"0D54u00009evmrNCAQ"} -->
## Can Android ZCC not install on x86/x86_64 devices?

- Source: https://community.zscaler.com/s/question/0D54u00009evmrNCAQ/can-android-zcc-not-install-on-x86x8664-devices
- Type: Q&A
- Posted: 2021-06-18T12:17:11.000Z
- Last activity: 2023-05-31T08:47:38.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

When I found ZCC on my x86/x86_64 Android’s google play,
 it showed “This app isn’t compatible with your device?.

I guess there is something to do with the big update (1.7.xx → 1.8.xx) last month?
 I just wonder if ZCC don’t support x86/x86_64 Android device anymore.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmrXCAQ/zcc-certificate-renewal","lastmod":"2023-05-31T08:47:41.000Z","id":"0D54u00009evmrXCAQ"} -->
## ZCC Certificate Renewal

- Source: https://community.zscaler.com/s/question/0D54u00009evmrXCAQ/zcc-certificate-renewal
- Type: Q&A
- Posted: 2021-06-06T11:43:22.000Z
- Last activity: 2023-05-31T08:47:41.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I noticed this paragraph in the [documentation](https://help.zscaler.com/zpa/about-timeout-policy)

> The Zscaler Client Connector (formerly Zscaler App or Z App) certificates have a validity of 365 days from the date of enrollment. If the authentication timeout is set to Never, users will only be prompted to re-enroll to renew device certificates. This means the end user will need to log out and log back in to the Zscaler Client Connector in order to obtain a new certificate.
>

Does anyone who has experience with renewing ZCC certificates know how this will happen?

If we have a logout password for ZCC, does that prevent users from logging out and logging back in in order to obtain a new certificate?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmrfCAA/when-using-fetch-logs-in-client-connector-portal-mac-logs-are-encrypted","lastmod":"2023-06-02T17:08:56.000Z","id":"0D54u00009evmrfCAA"} -->
## When using "Fetch Logs" in Client Connector Portal, Mac logs are encrypted

- Source: https://community.zscaler.com/s/question/0D54u00009evmrfCAA/when-using-fetch-logs-in-client-connector-portal-mac-logs-are-encrypted
- Type: Q&A
- Posted: 2021-05-27T12:48:53.000Z
- Last activity: 2023-06-02T17:08:56.000Z
- Answers: 10
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I had my first opportunity to use the new “Fetch Logs? feature in the Zscaler Client Connector portal yesterday for a Mac user. It seems to have worked really well, but unfortunately the logs that are uploaded are the “file.enc2? encrypted filetype. Surely it wasn’t intentional to have those logs be encrypted in a way that only Zscaler Support can decrypt them? It seems to defeat the purpose of having that feature in the first place.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmrgCAA/zia-tunnel-20-bloomberg-via-internet","lastmod":"2023-07-06T12:58:04.000Z","id":"0D54u00009evmrgCAA"} -->
## ZIA - Tunnel 2.0 + Bloomberg via Internet

- Source: https://community.zscaler.com/s/question/0D54u00009evmrgCAA/zia-tunnel-20-bloomberg-via-internet
- Type: Q&A
- Posted: 2021-05-27T06:45:56.000Z
- Last activity: 2023-07-06T12:58:04.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi there,

We are just checking with Tunnel 2.0 and ZIA. How do we allow custom ports outside the 80/443 range to be permitted for the likes of Bloomberg?

Do we need to follow and add custom ports such as: [Configuring Custom Ports | Zscaler](https://help.zscaler.com/zia/configuring-custom-ports) ?

Keen to hear if anyone has got Bloomberg via Internet fully working through ZIA using Tunnel 2.0 without any bypass at all.

Regards,
 John
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmrmCAA/tunnel-20-issues-with-countryspecific-restrictions","lastmod":"2023-05-31T08:47:49.000Z","id":"0D54u00009evmrmCAA"} -->
## Tunnel 2.0 issues with country-specific restrictions

- Source: https://community.zscaler.com/s/question/0D54u00009evmrmCAA/tunnel-20-issues-with-countryspecific-restrictions
- Type: Q&A
- Posted: 2021-05-18T15:34:52.000Z
- Last activity: 2023-05-31T08:47:49.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Good Morning

I currently have a client who moved away from using PAC files, post moving to Tunnel 2.0, however, due to some restrictions in the way we do country-specific restrictions and apply some block rules they are experiencing issues accessing some sites. Anyone encountered this issue when moving to tunnel 2.0 ?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmrrCAA/zcc-ios-tunnel-20","lastmod":"2023-05-31T08:47:52.000Z","id":"0D54u00009evmrrCAA"} -->
## ZCC iOS Tunnel 2.0

- Source: https://community.zscaler.com/s/question/0D54u00009evmrrCAA/zcc-ios-tunnel-20
- Type: Q&A
- Posted: 2021-05-13T07:14:24.000Z
- Last activity: 2023-05-31T08:47:52.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi everyone,
 can you tell me if and when Tunnel 2.0 will also be supported by ZCC for iOS?

Regards,
 Luca.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmryCAA/zcc-disconnects-when-recognizing-a-remote-captive-portal","lastmod":"2023-05-31T09:25:10.000Z","id":"0D54u00009evmryCAA"} -->
## ZCC disconnects when recognizing a **remote** captive portal

- Source: https://community.zscaler.com/s/question/0D54u00009evmryCAA/zcc-disconnects-when-recognizing-a-remote-captive-portal
- Type: Q&A
- Posted: 2021-05-02T17:00:26.000Z
- Last activity: 2023-05-31T09:25:10.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

My client uses VPN clients to connect securely to clients networks. They have different clients with different VPN concentrators, and thus different VPN clients.

In most cases, the VPN client connection works fine with the Tunnel 2.0 ZIA setup we are testing.

In one case, a client of theirs uses Checkpoint VPN, and while the VPN tunnel comes up nicely, the quirk is that behind the Checkpoint VPN concentrator there is a captive portal for further authentication of the user before allowing access.

The issue is, ZCC sees the remote captive portal and treats it like a standard local captive portal allowing access to the internet, and thus stops ZCC and disconnects Tunnel 2.0, which makes the VPN tunnel drop. Not exactly conducive to anything good…

This is with Windows 10 running ZCC 3.4.0.101.

Would anyone here have any suggestion on what could be done on ZIA or the ZCC configuration to avoid this issue?

Would avoiding using Tunnel 2.0 for ZIA change altogether be the best practice, by configuring ALL the remote VPN concentrators from being routed to ZIA and rather be split locally before the VPN traffic hits the packet filter driver?

Many thanks for any suggestion you might have!

Bye, Luca
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evms0CAA/zscaler-client-connector-devices-api","lastmod":"2023-05-31T09:08:36.000Z","id":"0D54u00009evms0CAA"} -->
## Zscaler Client Connector Devices API?

- Source: https://community.zscaler.com/s/question/0D54u00009evms0CAA/zscaler-client-connector-devices-api
- Type: Q&A
- Posted: 2021-05-01T23:03:51.000Z
- Last activity: 2023-05-31T09:08:36.000Z
- Answers: 5
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

i’m looking for a way to fetch the client devices via API, does this exist?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmsECAQ/what-is-the-right-zscaler-client-connector-version-for-windows","lastmod":"2023-05-31T08:48:03.000Z","id":"0D54u00009evmsECAQ"} -->
## What is the right Zscaler Client Connector Version for Windows?

- Source: https://community.zscaler.com/s/question/0D54u00009evmsECAQ/what-is-the-right-zscaler-client-connector-version-for-windows
- Type: Q&A
- Posted: 2021-04-22T12:48:58.000Z
- Last activity: 2023-05-31T08:48:03.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

I’m confused about the different versions of the Zscaler Client Connector for Windows. In the [mobile.zscaler.net](http://mobile.zscaler.net) portal I can see 3.1.0.117 under Adminstration => Client Connector App Store => Personal Computers and under New Releases I can see 3.1.0.129 and also 3.2.0.87. On the release notes page [Client Connector App Release Summary (2021) | Zscaler](https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2021) I can see 3.2.1.1 and also 3.4.

What the heck is the right and best version to use?

Best Regards,
 Patrick
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmsGCAQ/how-does-zcc-with-tunnel-v10-handles-ftp-traffic","lastmod":"2023-07-06T11:45:38.000Z","id":"0D54u00009evmsGCAQ"} -->
## How does ZCC with Tunnel V1.0 handles FTP traffic?

- Source: https://community.zscaler.com/s/question/0D54u00009evmsGCAQ/how-does-zcc-with-tunnel-v10-handles-ftp-traffic
- Type: Q&A
- Posted: 2021-04-21T14:14:43.000Z
- Last activity: 2023-07-06T11:45:38.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Team, I have a question about how ZCC with Tunnel v1.0 handles FTP traffic.

To make a long story short, I have a working configuration now that forces Internet Explorer in the end user PC to send FTP traffic as FTP over HTTP through ZCC wit Tunnel V1.0
 This is working fine.

However our customer is requesting this (FTP over ZCC with Tunnel v1.0) to work as well for FileZilla.
 But if I try similar settings with FileZilla (FileZilla Settings > Generic Proxy > HTTP 1.1 Using CONNECT Method)…

[[image] image1244×1022 168 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O7ZFAA0)

…FileZilla does reach the proxy but the connection times out waiting for the welcome message:

Status: Connecting to ftp.rediris.es through HTTP proxy
 Status: Connecting to 127.0.0.1:9000…
 Status: Connection with proxy established, performing handshake…
 Response: Proxy reply: HTTP/1.1 200 Connection Established
 Status: Connection established, waiting for welcome message…
 Error: Connection timed out after 20 seconds of inactivity
 Error: Could not connect to server

Since there are no logs for this transaction in ZIA (Web Insights), I believe the transaction is actually not progressing beyond ZCC’s local proxy

Also, I have read this in our documentation: [About FTP Control | Zscaler](https://help.zscaler.com/zia/about-ftp-control)
 With FTP Control, Zscaler provides access control for native FTP and FTP over HTTP traffic. This can be particularly useful if you are using a Z-App or PAC based deployment, as they only support FTP over HTTP traffic.

So I have reached to the hypothesis that FileZilla is actually tunnelling native FTP traffic over HTTP CONNECT, but it’s not actually using FTP over HTTP (as Internet Explorer does). But ZCC’s local proxy is, somehow, not accepting FTP tunneling over HTTP CONNECT…

QUESTION: Could someone please confirm/correct this hypothesis, please?
 Many thanks in advance!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmsHCAQ/z-tunnel-20-no-tunnel-version-selection-option","lastmod":"2023-07-06T11:45:39.000Z","id":"0D54u00009evmsHCAQ"} -->
## Z Tunnel 2.0 - No Tunnel Version Selection option

- Source: https://community.zscaler.com/s/question/0D54u00009evmsHCAQ/z-tunnel-20-no-tunnel-version-selection-option
- Type: Q&A
- Posted: 2021-04-20T13:53:27.000Z
- Last activity: 2023-07-06T11:45:39.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello

In the zscaler help document, the following entry exits 1. (Optional) For **Tunnel Version Selection** , you can select which version of Z-Tunnel to use. To see this field, you must select [Packet Filter Based for the driver method](https://help.zscaler.com/z-app/configuring-forwarding-profiles-zscaler-app#windows-driver-selection).

When I select Packet Filter Based, I dont see any Tunnel Version Selection option at all. How do I configure Z Tunnel 2.0 in the forwarding profile?

Thanks

Charlie

[[CaptureZscaler] CaptureZscaler600×754 50.8 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O71qAAC)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmsJCAQ/zapp-with-strict-enforcement-disabling-health-check","lastmod":"2023-05-31T08:13:49.000Z","id":"0D54u00009evmsJCAQ"} -->
## Zapp with strict enforcement disabling health check

- Source: https://community.zscaler.com/s/question/0D54u00009evmsJCAQ/zapp-with-strict-enforcement-disabling-health-check
- Type: Q&A
- Posted: 2021-04-19T03:55:05.000Z
- Last activity: 2023-05-31T08:13:49.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are looking for the reason why ZCC with StrictEnforcement enabled does not work properly in the logged off state. It works fine in the logged on state.

I found out that the cause of ZCC not working properly in the logged off state is the blocking of 100.64.0.0/16 by FW.

So why does it work correctly in the logged on state?

The ZCC log says the following.

“ZFHM: Skip firewall check as last packet is read within 5 seconds.?

It seems that the health check is skipped when user traffic is passing between the local machine and the ZscalerNIC.

So, I think ZCC is working fine because ZFHM is skipped.

Is our understanding correct?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmsLCAQ/how-do-you-harden-a-zcc-zapp-install-to-prevent-bypass","lastmod":"2023-05-31T08:48:06.000Z","id":"0D54u00009evmsLCAQ"} -->
## How do you harden a ZCC / ZAPP install to prevent bypass?

- Source: https://community.zscaler.com/s/question/0D54u00009evmsLCAQ/how-do-you-harden-a-zcc-zapp-install-to-prevent-bypass
- Type: Q&A
- Posted: 2021-04-15T14:57:52.000Z
- Last activity: 2023-05-31T08:48:06.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I’ve been evaluating the ZCC instance deployed to my test workstation and have come across one glaring weakness that allows me to bypass the ZCC. I wanted to ask the community if there’s a way to harden the install to prevent this (perhaps it’s just my org, I wasn’t involved in the deployment).

If you have local administrator privileges you can rename the executables associated with each of the ZScaler services (ZSAService, ZSATrayManager, ZSATunnel, ZSAUpdater) and then after reboot the services fail to start.

Examining the ownership of the files themselves they all appear to be owned by the local Administrator. This differs from other products (AV, for example) which are owned by SYSTEM and protected from modification by local admins.

I’ve searched through the community and examined the ZAPP / ZCC install guides. I’ve come up empty thus far. Appreciate any feedback you can provide, thank you.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmsMCAQ/device-limitation-for-zcc","lastmod":"2023-05-31T08:13:49.000Z","id":"0D54u00009evmsMCAQ"} -->
## Device Limitation for ZCC

- Source: https://community.zscaler.com/s/question/0D54u00009evmsMCAQ/device-limitation-for-zcc
- Type: Q&A
- Posted: 2021-04-14T16:33:49.000Z
- Last activity: 2023-05-31T08:13:49.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

So… in healthcare we have clinical workstations that are ‘shared’ among personnel. So, apps are authenticated individually but the workstation itself may not be. We’re running into a deployment issue of 16 devices ‘per user’ (a generic local user), when trying to install / push out the client.

A way around this?

Steve
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmsPCAQ/ztunnel-20-application-bypass-in-appprofile","lastmod":"2023-05-31T08:48:06.000Z","id":"0D54u00009evmsPCAQ"} -->
## Z-Tunnel 2.0 Application Bypass in App-Profile

- Source: https://community.zscaler.com/s/question/0D54u00009evmsPCAQ/ztunnel-20-application-bypass-in-appprofile
- Type: Q&A
- Posted: 2021-04-13T05:29:09.000Z
- Last activity: 2023-05-31T08:48:06.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi together,
 I try to bypass some Microsoft Applications like ATP, Login, Update and some more.
 After several tries with 2 Pac Files ( App/Fwd), I will try Destinations Exclusions or Application Bypass Fields in the App-Profile.
 Is it possible to try more Application Bypasses then Microsoft Teams?
 That is the only App I can select.
 And is it possible to import Destination Exclusion Lists?
 To set one Ip-Range after another is a long way

Regards
 Thorsten
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmsQCAQ/mac-os-users-are-getting-dns-error-while-accessing-internet-websites-after-login-into-zapp","lastmod":"2023-07-06T13:06:04.000Z","id":"0D54u00009evmsQCAQ"} -->
## MAC OS users are getting DNS error while accessing internet websites after login into Zapp

- Source: https://community.zscaler.com/s/question/0D54u00009evmsQCAQ/mac-os-users-are-getting-dns-error-while-accessing-internet-websites-after-login-into-zapp
- Type: Q&A
- Posted: 2021-04-12T06:17:16.000Z
- Last activity: 2023-07-06T13:06:04.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Team,

One of our client with MAC OS facing DNS error issue while accessing internet website through Zscaler Client connector.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmsUCAQ/zscaler-client-connector-on-chromebooks","lastmod":"2024-08-28T08:22:36.000Z","id":"0D54u00009evmsUCAQ"} -->
## Zscaler Client Connector on Chromebooks

- Source: https://community.zscaler.com/s/question/0D54u00009evmsUCAQ/zscaler-client-connector-on-chromebooks
- Type: Q&A
- Posted: 2021-04-08T13:15:15.000Z
- Last activity: 2024-08-28T08:22:36.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I know we can run Client Connector as an Android app in ChromeOS, but can we natively support client connector on ChromeOS? Maybe with the new client connector for Linux?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmsVCAQ/zapp-username-change","lastmod":"2025-08-22T07:27:13.000Z","id":"0D54u00009evmsVCAQ"} -->
## Zapp username change

- Source: https://community.zscaler.com/s/question/0D54u00009evmsVCAQ/zapp-username-change
- Type: Q&A
- Posted: 2021-04-06T07:14:45.000Z
- Last activity: 2025-08-22T07:27:13.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello, we renamed all our AD account from [Jdoe@xxx.com](mailto:Jdoe@xxx.com) to [jdoe@yyy.com](mailto:jdoe@yyy.com) but the Zapp will not update it and keep the old account, only a Zapp logout will update it (key to provide more 5 mn to reautenticate teh user, do you know a method to change it smoothly ?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmshCAA/stop-zcc-via-admin-commandline","lastmod":"2023-06-02T17:08:42.000Z","id":"0D54u00009evmshCAA"} -->
## Stop ZCC via admin command-line

- Source: https://community.zscaler.com/s/question/0D54u00009evmshCAA/stop-zcc-via-admin-commandline
- Type: Q&A
- Posted: 2021-03-25T11:44:04.000Z
- Last activity: 2023-06-02T17:08:42.000Z
- Answers: 9
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

is there a way to stop a ZCC in login state (no user logged in) via an admin commandline ?

I can kill all ZSA processes via:
 c:> taskkill /IM ZSA* /f

But after doing this the Login popup comes up again and processes restart.

Thanks Tom
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmsmCAA/captive-portal-error","lastmod":"2023-05-31T08:48:17.000Z","id":"0D54u00009evmsmCAA"} -->
## Captive Portal error

- Source: https://community.zscaler.com/s/question/0D54u00009evmsmCAA/captive-portal-error
- Type: Q&A
- Posted: 2021-03-19T07:52:55.000Z
- Last activity: 2023-05-31T08:48:17.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Team,

I am getting captive portal error in the office corporate network. can we have a solution for this?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmsnCAA/captive-portal-error","lastmod":"2023-05-31T09:25:12.000Z","id":"0D54u00009evmsnCAA"} -->
## Captive Portal error

- Source: https://community.zscaler.com/s/question/0D54u00009evmsnCAA/captive-portal-error
- Type: Q&A
- Posted: 2021-03-19T06:47:39.000Z
- Last activity: 2023-05-31T09:25:12.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are getting captive portal in the office corporate network.

can we have a solution for this?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmsoCAA/can-we-configure-the-zscaler-client-connector-both-full-and-split-tunneling-vpn-profiles","lastmod":"2023-05-31T08:48:20.000Z","id":"0D54u00009evmsoCAA"} -->
## Can we configure the Zscaler client connector both Full and Split tunneling VPN profiles?

- Source: https://community.zscaler.com/s/question/0D54u00009evmsoCAA/can-we-configure-the-zscaler-client-connector-both-full-and-split-tunneling-vpn-profiles
- Type: Q&A
- Posted: 2021-03-17T21:16:32.000Z
- Last activity: 2023-05-31T08:48:20.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are using Pulse secure VPN client with Full tunneling profile.
 And we are planning to install Zscaler client connector.
 During migration transition, we created both split tunneling and full tunneling profiles on the Pulse secure VPN client for evaluation pilot users.
 However following URL, “Your VPN can run in full-tunnel mode or split-tunnel mode,
 but each mode requires different Zscaler Client Connector configurations?
 So can’t we configure both split tunneling and full tunneling profiles on the Pulse secure VPN client for evaluation pilot users ? Because the Trusted VPN network , you can’t configure split and full type of connections.
 [https://help.zscaler.com/z-app/best-practices-zscaler-app-and-vpn-client-interoperability?_ga=2.30901754.474226014.1615787824-511688009.1615205400&_gac=1.3933828.1615964254.EAIaIQobChMI_afV9t-27wIVIQZ7Ch360QggEAAYASAAEgJKffD_BwE](https://help.zscaler.com/z-app/best-practices-zscaler-app-and-vpn-client-interoperability?_ga=2.30901754.474226014.1615787824-511688009.1615205400&_gac=1.3933828.1615964254.EAIaIQobChMI_afV9t-27wIVIQZ7Ch360QggEAAYASAAEgJKffD_BwE)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmspCAA/question-on-leveraging-client-connector-with-o365-conditional-accessmfa","lastmod":"2023-05-31T08:13:49.000Z","id":"0D54u00009evmspCAA"} -->
## Question on leveraging Client Connector with O365 Conditional Access/MFA

- Source: https://community.zscaler.com/s/question/0D54u00009evmspCAA/question-on-leveraging-client-connector-with-o365-conditional-accessmfa
- Type: Q&A
- Posted: 2021-03-16T08:33:20.000Z
- Last activity: 2023-05-31T08:13:49.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hey -

Currently planning a deployment of the ZScaler Client Connector and have embedded integration with AzureAD SSO SAML.

Once the app is installed on a machine and the user has logged in/authenticated for the first time, does anybody know if this eventually “times out? or prompts for re-authentication? We leverage conditional access policies so just wondering whether the user would ever be prompted to fully re-authenticate via Microsoft (assuming they did not log out of the app).

Thanks!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmsrCAA/http-with-non-default-port-zcc-tunnel","lastmod":"2023-05-31T08:48:20.000Z","id":"0D54u00009evmsrCAA"} -->
## HTTP with non default port ZCC Tunnel

- Source: https://community.zscaler.com/s/question/0D54u00009evmsrCAA/http-with-non-default-port-zcc-tunnel
- Type: Q&A
- Posted: 2021-03-15T14:59:03.000Z
- Last activity: 2023-05-31T08:48:20.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

I want to confirm a point about using the Tunnel Packet Filter mode, in Zscaler documentation, in tunnel mode only port 80 and 443 are forwareded,

I did the test, and i see in ZCC logs and Zscaler portal that the traffic with port 666 for example, is forwarded by ZCC and going through Zscaler

could you confirm what is the behavior expected please

regards
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmssCAA/trend-micro-apex-one-together-with-zcc-performance-issues","lastmod":"2023-05-31T08:48:17.000Z","id":"0D54u00009evmssCAA"} -->
## Trend Micro Apex One together with ZCC Performance Issues

- Source: https://community.zscaler.com/s/question/0D54u00009evmssCAA/trend-micro-apex-one-together-with-zcc-performance-issues
- Type: Q&A
- Posted: 2021-03-15T13:38:00.000Z
- Last activity: 2023-05-31T08:48:17.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Guys,

we are currently facing performance issues if we use the Trend Micro Apex One Agent Version 14.0.9167 together with the Zscaler Client Connector (at the Moment 3.1.0.103). We noticed that the performance issues are mainly with Websites that are using some kind of authentication. As soon as we deactivate one of the products the Performance is fine again.

Does anyone faced similar issues with ZCC and Trend Micro Apex One?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmsuCAA/cant-uninstall-zscaler-client-connector","lastmod":"2023-05-31T08:48:23.000Z","id":"0D54u00009evmsuCAA"} -->
## Can't uninstall Zscaler client connector

- Source: https://community.zscaler.com/s/question/0D54u00009evmsuCAA/cant-uninstall-zscaler-client-connector
- Type: Q&A
- Posted: 2021-03-12T07:01:27.000Z
- Last activity: 2023-05-31T08:48:23.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

When I try to uninstall the ZCC in control penal by run the uninstaller in the corresponding directory,neither works.The windows just appears for a few seconds and vanished,without asking me for the uninstall password.How can I uninstall it?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtHCAQ/sophos-1004-network-extension-app-big-sur-and-zscaler-zcc-tunnel-issues","lastmod":"2023-07-06T13:06:32.000Z","id":"0D54u00009evmtHCAQ"} -->
## Sophos 10.0.4 Network Extension App, Big Sur and Zscaler ZCC tunnel issues?

- Source: https://community.zscaler.com/s/question/0D54u00009evmtHCAQ/sophos-1004-network-extension-app-big-sur-and-zscaler-zcc-tunnel-issues
- Type: Q&A
- Posted: 2021-03-04T18:12:23.000Z
- Last activity: 2023-07-06T13:06:32.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Sophos 10.0.4 has a network extension app (doing some inspection of traffic) that when installed seems to block the ZCC tunnel process from binding to port 9000. Basically the ZCC won’t connect and shows a network error until you remove the Sophos extension. Has anyone else had this issue w/ Sophos? How did you get the 2 to play together well? I’m not seeing documentation on Sophos’s side sayings its trying to use 9000 or even blocking it but its not allowing Ztunnel to start. Also this all works fine in 10.0.3 so it can’t be the typical whitelist of the process names, etc.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtICAQ/issue-in-mobile-apps-in-trusted-network","lastmod":"2023-05-31T08:13:54.000Z","id":"0D54u00009evmtICAQ"} -->
## Issue in Mobile Apps in Trusted network

- Source: https://community.zscaler.com/s/question/0D54u00009evmtICAQ/issue-in-mobile-apps-in-trusted-network
- Type: Q&A
- Posted: 2021-03-04T16:14:11.000Z
- Last activity: 2023-05-31T08:13:54.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Our users are currently facing some issues with loading the mobile application in the office network. Please let us know how to overcome these hiccups in this case. Please review the policies let me know about tweaking the changes for mobile apps traffic alone.

Is there any way to debugging the traffic involved with mobile gadgets such as Andriod as well as iPhone & BYOD within the trusted network?

is there any recommendation on providing access based on the mobile Apps with a specific restriction on download the Apps from the Apps store and Play store respectively apart from MDM solutions ?

Please provide us a recommendation on capturing the traffic with help of mobile-based effective tools to fix and resolve instead of providing the SSL inspections directly?

- Is there is a relationship between URL filtering/cloud controls that will do any kind of actions with mobile apps too?
- Is it mandatory to have a Zscaler client connector app installer with each gadget along with zscaler certification installed to work with mobile applications?
- Please suggest to me an alternate way to avoid using the zscaler client connector inside the office network for the mobile devices?
- Is there a possible way to check the logs from the zscaler portal on access issues with a mobile application which are currently connected in-office wifi network (trusted network)
- how does the zscaler client connector will be behaving with Mobile devices?
- Please share us some kind of suggestion from your end on resolve these kind of issues?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtKCAQ/force-remove-function-in-zscaler-client-connector-registered-device-details","lastmod":"2023-05-31T08:48:29.000Z","id":"0D54u00009evmtKCAQ"} -->
## Force Remove Function in Zscaler Client Connector Registered Device Details

- Source: https://community.zscaler.com/s/question/0D54u00009evmtKCAQ/force-remove-function-in-zscaler-client-connector-registered-device-details
- Type: Q&A
- Posted: 2021-03-01T15:38:46.000Z
- Last activity: 2023-05-31T08:48:29.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Can someone from Zscaler team help us understand what is the “Force Remove? Function in Zscaler Client Connector Registered Device Details (under the Zapp Admin GUI “Enrolled Devices?)?

Will “Force Remove? logout Zapp users from the application and force them to reauthenticate? What will happen when we click on “Force Remove? , but the user is not actively connection with Zscaler?

Thanks,

Jane
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtNCAQ/tunnel-20-not-provisioned-need-zscaler-se-signoff","lastmod":"2023-05-31T08:48:29.000Z","id":"0D54u00009evmtNCAQ"} -->
## Tunnel 2.0 not provisioned: need Zscaler SE signoff?

- Source: https://community.zscaler.com/s/question/0D54u00009evmtNCAQ/tunnel-20-not-provisioned-need-zscaler-se-signoff
- Type: Q&A
- Posted: 2021-02-26T16:30:46.000Z
- Last activity: 2023-05-31T08:48:29.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello all,

I wanted to run tests using Tunnel 2.0 on our instance we have access to as Zscaler partners.

As I could not see the tunnel version selector in the forwarding profile, I opened a case with TAC.

TAC told me the Tunnel 2.0 is not provisioned on the instance, and that I should open a new case asking to have Tunnel 2.0 provisioned, which I did.

The interesting part is that I have discovered that a Zscaler Sales Engineer has to fill in some Google Form in order for TAC to approve provisioning Tunnel 2.0 on our instance.

I hate to waste the time of our local Zscaler SE, and while I have already emailed him about this need, I wonder now what is the rationale behind the need to have a Zscaler SE being involved with such a request, especially since we are an official Zscaler partner, and I have received both ZCCP-PA and ZCCP-IA certification, if that would be the issue.

Why isn’t Tunnel 2.0 available by default?

Inquiring minds want to know…

Have a great weekend!

Bye, Luca
 CryptoNet Labs
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtOCAQ/ztunnel-20-dtlstls-mtu","lastmod":"2025-08-22T07:27:21.000Z","id":"0D54u00009evmtOCAQ"} -->
## ZTunnel 2.0, DTLS/TLS, MTU

- Source: https://community.zscaler.com/s/question/0D54u00009evmtOCAQ/ztunnel-20-dtlstls-mtu
- Type: Q&A
- Posted: 2021-02-24T15:26:00.000Z
- Last activity: 2025-08-22T07:27:21.000Z
- Answers: 42
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello community,

I hope someone can shed some light on this. We are still (sic!) in the process of switching all our users to ZTunnel 2.0.

We run/ran into multiple issues for our homeoffice users.This ranges from non-Zscaler related internet provider issues to DTLS/TLS issues and MTU/fragmentation issues (and a whole bunch more private network issues ;-)).

In the Zscaler Mobile Portal we read next to MTU in fwd profile-section:
 [image]

Now, what should we set here when we have X homeoffice-users with - in worst case - Y different internet uplinks and therefore Z different MTU-settings?

1. We **could** do a survey and ask users to do some PINGs to evaluate their personal-homeoffice-best-MTU and create and assign kind of “user-based?-profiles. Ehm. No. We won’t do that.
2. We could in general set a “lowest possible MTU? to avoid fragmentation (lowest value would be 576 for IPv4 and 1280 for IPv6 based connections…) but increasing overhead and maybe causing other performance (?) issues. Maybe 1280 for IPv4 and IPv6 would do it…
3. We force TLS and ignore advantages of DTLS

To ease things and we went initially for 3) and doing 2) now for some testusers.

Would be PMTUD for ZCC a solution?
 Any other best practices/solutions/ideas?
 Did we miss something?

BR
 Manuel
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtVCAQ/apple-m1-chips-and-zscaler-client-connector-compatibility","lastmod":"2023-07-06T13:07:12.000Z","id":"0D54u00009evmtVCAQ"} -->
## Apple M1 chips and Zscaler Client Connector compatibility

- Source: https://community.zscaler.com/s/question/0D54u00009evmtVCAQ/apple-m1-chips-and-zscaler-client-connector-compatibility
- Type: Q&A
- Posted: 2021-02-16T18:12:33.000Z
- Last activity: 2023-07-06T13:07:12.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I could not find this any any Zscaler help documentation but was trying to figure out if there are any compatibility issues with Zscaler Client Connector 2.2.4.0 and Apples new M1 chips.

Apple
[image]

##### [Apple M1 Chip](https://www.apple.com/mac/m1/)

Apple M1 chip. It’s a whole new era for Mac. Bringing incredible power, astonishing graphics, and unbelievable battery life.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtbCAA/zscaler-sip-and-udp-call-flow","lastmod":"2023-07-06T13:07:22.000Z","id":"0D54u00009evmtbCAA"} -->
## Zscaler, SIP and UDP call flow

- Source: https://community.zscaler.com/s/question/0D54u00009evmtbCAA/zscaler-sip-and-udp-call-flow
- Type: Q&A
- Posted: 2021-02-12T09:43:05.000Z
- Last activity: 2023-07-06T13:07:22.000Z
- Answers: 2
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi all, I am hoping I can find some answers to some of these questions/concerns I have with Zsclaer deployment using a combination of client connectors, PAC and GRE-Tunnel for routing traffic to Zoom Phone cloud. I have read some articles that raised latency and jitter as one issue seen within Zscaler used for voice traffic. I have also read an article from Zscaler that suggests bypassing Zscaler for UC traffic.

Bypassing Zscaler for UC traffic:

[[Bypassing Unified Communications Traffic | Zscaler](http://help.zscaler.com/zpa/bypassing-unified-communications-traffic)]

"Unified communications (UC) traffic for off-network and on-network users should use edge servers, externally accessible Session Border Controllers (SBCs), or UC gateways. These deployment models are recommended by UC vendors (for example, Skype for Business). Zscaler highly recommends adopting one of these UC deployment models to provide the best performance. **Zscaler also recommends not sending UC traffic to ZPA, as this has the potential to add latency and jitter to the communication** **?**

Also:
 Bypassing Zscaler for MSFT media

Bypassing Zscaler for all Microsoft Traffic
Client Connector
> Hello Everyone! We have rolled out the ZIA App to our organization - but we have felt some issues with the o365 One Click - The main bug being intermittent silent calls when using MS Teams Enterprise Voice. As a test, we assigned some test users their own app profile, and bypassed everything Microsoft related from
> https://docs.microsoft.com/en-us/microsoft-365/enterprise/urls-and-ip-address-ranges?view=o365-worldwide
> This has had a positive affect and vastly reduced the silent calls. Mgmt ha…

In the light of all this I have a few concerns and queries that I would appreciate Zscaler to address:

1. Will Zscaler NAT traffic coming from clients before forwarding to Zoom Phone cloud? What will the impact of this on VOIP traffic since traffic going to Zscaler through the GRE tunnel via DMZ firewall will be NATTED.
2. What is the interaction of Zscaler and SIP traffic?. Based on my tests so far, I don’t see Zscaler involved in the SIP flow. How does Zscaler cloud intercept media and proxy it back through its cloud from Zoom’s cloud SBC
3. How is UDP traffic/media routed through Zscaler cloud and to the Zscaler client running client connector and how do you view the media within the zscaler portal
4. How many Zscaler customers are using it for voip traffic? What are the optimisations done to achieve a good voice quality with these customers?
5. How many latency and jitter related issues is Zscaler aware of and based on the links above, should we indeed not be using Zscaler for voice traffic?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtlCAA/windows-10-zapp-ssh","lastmod":"2023-05-31T08:13:54.000Z","id":"0D54u00009evmtlCAA"} -->
## Windows 10, ZApp, SSH

- Source: https://community.zscaler.com/s/question/0D54u00009evmtlCAA/windows-10-zapp-ssh
- Type: Q&A
- Posted: 2021-02-07T21:52:53.000Z
- Last activity: 2023-05-31T08:13:54.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

I have a few VMs with the ZApp installed on them by the team that manages our network. My only access to these machines are remotely via RDP. Unless I am connected to a VM via RDP, zscaler is not ‘connected’ so accessing internet resources does not work. I have a few user services running that depend on being able to access the internet. Based on the logs, zscaler detects user events and exits.

```
2021-01-26 17:00:16.279658(-0500)[12624:17312] INF User session switch: SessionLock
2021-01-26 17:00:16.405654(-0500)[12624:17312] INF User session switch: RemoteDisconnect
2021-01-26 17:00:16.406656(-0500)[12624:17312] INF Performing exit clean up
......
```

So SSH. If the built-in Windows SSH server was enabled, and a user connected, would Zscaler see that and ‘connect’?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtmCAA/captive-portal-causing-issue-with-ping-command","lastmod":"2023-05-31T08:13:54.000Z","id":"0D54u00009evmtmCAA"} -->
## Captive portal Causing issue with ping command

- Source: https://community.zscaler.com/s/question/0D54u00009evmtmCAA/captive-portal-causing-issue-with-ping-command
- Type: Q&A
- Posted: 2021-02-06T09:58:56.000Z
- Last activity: 2023-05-31T08:13:54.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I have my office Laptop, We are connecting the laptop to local wifi router . This router is isolated from office internet. we have other ethernet device on same router.
 When the laptop is connected to Router we get the message of Captive Device Detection.

1. We are ping(ing) from device to laptop (local ip), ping works successfully.
2. Suddenly the ping stops to respond for some time .

On other zscaler forum pages I read that there is timeout for captive network.

1. Does after timeout the zscaler disconnects Laptop network completely from router/
2. What is the default timeout if any ?
3. How can I increase the timeout for laptop ?

When the laptop stops to respond to ping at that time again I get the message of captive network detection.

TIA.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtoCAA/bank-websites-were-not-loading-when-zapp-is-turned-on","lastmod":"2023-05-31T08:48:43.000Z","id":"0D54u00009evmtoCAA"} -->
## Bank websites were not loading when Zapp is turned ON

- Source: https://community.zscaler.com/s/question/0D54u00009evmtoCAA/bank-websites-were-not-loading-when-zapp-is-turned-on
- Type: Q&A
- Posted: 2021-02-05T16:42:54.000Z
- Last activity: 2023-05-31T08:48:43.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Team,

Bank sites were not loading from home with Zapp (ZIA) is turned ON, it throws connection timed out error but the same sites load if the Zapp (ZIA) is turned OFF and also if we move the user to different ISP then bank sites open even with Zapp (ZIA) turned ON.

I am not sure if we can say it is an ISP issue because if we turn off Zapp (ZIA), website loads without any issues and also I can see no SSL inspection happening on the sites from zscaler end.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtpCAA/zscaler-client-connector-auth-behavior","lastmod":"2023-07-06T13:07:45.000Z","id":"0D54u00009evmtpCAA"} -->
## Zscaler Client Connector auth behavior

- Source: https://community.zscaler.com/s/question/0D54u00009evmtpCAA/zscaler-client-connector-auth-behavior
- Type: Q&A
- Posted: 2021-02-05T06:07:18.000Z
- Last activity: 2023-07-06T13:07:45.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi ,

What will be the authentication behavior when Zapp is configured as Disabled (FWD profile: NONE, Cisco VPN: Full tunnel, zapp state: Disabled:VPN Trusted Network). Our Trusted network is configured with GRE.

Will authentication frequency play any role in re-authenticating users with zapp mentioned in above scenario?

Current Behavior:
 We are seeing re-authntication getting triggered for some users with Zapp (FWD profile: NONE, Cisco VPN: Full tunnel, zapp state: Disabled:VPN Trusted Network)

The behavior is not consistent. I would like to know if there is any exception to the zapp one time login behavior.

Thank you
 Richardson Jose
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtqCAA/ipv6-support-for-zcc","lastmod":"2023-05-31T08:43:55.000Z","id":"0D54u00009evmtqCAA"} -->
## IPv6 support for ZCC

- Source: https://community.zscaler.com/s/question/0D54u00009evmtqCAA/ipv6-support-for-zcc
- Type: Q&A
- Posted: 2021-02-04T09:11:36.000Z
- Last activity: 2023-05-31T08:43:55.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

With the current work from home scenario our users are seeing issues where ZCC stop connecting to both ZIA and ZPA because their home broadband provider shifted to IPv6. I contacted the CSM and he mentioned that it is in road-map but not on high priority list as customer are not moving to IPv6 but with the work from home scenario this issue has started surfacing and I am getting more and more tickets everyday.

Do we have a definitive date for IPv6 support? Else we may need to look for alternatives as to how to make it work.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtrCAA/every-how-many-seconds-does-the-zscaler-client-connector-check-the-status-of-the-zscaler-broker-to-which-it-is-connected","lastmod":"2023-05-31T09:08:39.000Z","id":"0D54u00009evmtrCAA"} -->
## Every how many seconds does the Zscaler Client Connector check the status of the Zscaler Broker to which it is connected?

- Source: https://community.zscaler.com/s/question/0D54u00009evmtrCAA/every-how-many-seconds-does-the-zscaler-client-connector-check-the-status-of-the-zscaler-broker-to-which-it-is-connected
- Type: Q&A
- Posted: 2021-02-03T16:30:24.000Z
- Last activity: 2023-05-31T09:08:39.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,
 every how many seconds does the Zscaler Client Connector check the status of the Zscaler Broker to which it is connected?

Zscaler confirmed to me that the ZCC performs a keepalive but it wasn’t able to specify its duration.

Can you help me?

Hi,
 Luca.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmttCAA/ztunnel-20-dtls-and-vodafone-cable","lastmod":"2025-09-03T12:05:11.000Z","id":"0D54u00009evmttCAA"} -->
## ZTunnel 2.0 DTLS and Vodafone Cable

- Source: https://community.zscaler.com/s/question/0D54u00009evmttCAA/ztunnel-20-dtls-and-vodafone-cable
- Type: Q&A
- Posted: 2021-02-01T11:37:04.000Z
- Last activity: 2025-09-03T12:05:11.000Z
- Answers: 11
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello all,

just solved one issue which haunted us last week: we had 3 (homeoffice) users having trouble connecting to ZPA, Sharepoint Online, sometimes Outlook just disconnected and various unspecific laggy performance when ZCC was in place. No issues with their private PCs. Since we found no salvation in configuring all possible profiles-settings we switched back to ZTunnel 1.0, what immediatly solved the issue. But as we do not want to use ZTunnel 1.0 anymore I found no peace

Via some extra work and asking users about their providers we found that all these users had one thing in common in their homeoffices: a Vodafone Cable Broadband Internet Access (via Coax).

As one of the differences between ZTunnel 1.0 and 2.0 is usage of TCP vs UDP (by default DTLS) we forced this users via fwd-profile settings to use TLS. Although this is a fallback option for DTLS it seems it never fell back to TLS automatically, maybe because it worked for initialization of ZCC. After the new policy was applied to the clients everything instantly worked. No issues anymore.

The only caveat seems to be that the users do not get their full bandwidth when ZCC is up&running. At least [ip.zscaler.com](http://ip.zscaler.com)’s connection quality test results are always reporting around 80/40 Mbit down/up inspite of the users are claiming they have a 1000 mbit connection. One of the users has an 250 mbit connection and get the same results. And yes, they are using different ZENs. And yes, there are obvioulsy no “UDP?-specific configuration switches on the home-routers.

Maybe thats a Vodafone Germany specific issue but in case you also happen to have support your homeoffice-users with their private internet infrastructure this is possibly something to keep in mind.

BR
 Manuel
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmtxCAA/question-concerning-pacfiles","lastmod":"2023-05-31T08:48:46.000Z","id":"0D54u00009evmtxCAA"} -->
## Question concerning PAC-files

- Source: https://community.zscaler.com/s/question/0D54u00009evmtxCAA/question-concerning-pacfiles
- Type: Q&A
- Posted: 2021-01-28T18:14:47.000Z
- Last activity: 2023-05-31T08:48:46.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello folks,

because of a discussion I had today one quick question:
 If no pac-file URL is entered in app-profile or fwd-profile: does ZCC pull and use default pac-files? If so, which one?

I did not found any info about that in [help.zscaler.com](http://help.zscaler.com), but maybe I just did not search carefully enough…

EDIT: ZCC 3.1.0.103, packet filter, tunnel mode, tunnel 2.

Keeping [https://help.zscaler.com/z-app/best-practices-adding-bypasses-z-tunnel-2.0](https://help.zscaler.com/z-app/best-practices-adding-bypasses-z-tunnel-2.0) in mind:

- If I explicitly use a pac-file ONLY in app-profile most settings would be without effect
- If I want to have app-profile pac working, I would need also a fwd-profile pac containing nearly the same configuration

If I use no pac file either in app or fwd profile, will Zscaler do the work for me and do the magic and configure a pac-file for app and a matching pac-file for fwd-profile?

Thanks and BR
 Manuel
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmu9CAA/gpo-conflicts-with-zcc-proxy-configuration","lastmod":"2023-05-31T08:48:46.000Z","id":"0D54u00009evmu9CAA"} -->
## GPO conflicts with ZCC proxy configuration?

- Source: https://community.zscaler.com/s/question/0D54u00009evmu9CAA/gpo-conflicts-with-zcc-proxy-configuration
- Type: Q&A
- Posted: 2021-01-26T16:08:33.000Z
- Last activity: 2023-05-31T08:48:46.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are piloting Zscaler so this is a new setup.

We have GPO-managed Edge and Chrome on endpoints. Firefox is unmanaged.

ZCC is dropping a “c:\Program Files\Mozilla Firefox\zscaler.cfg? file to manage Firefox.

Does ZCC set the proxy via the registry for Chrome and Edge? Could that be where/how the GPO confict occurs? If there a way to force ZCC to reapply the policy so that I can see it “take? (at least until the next GP refresh)?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmuBCAQ/zcc-in-authenticationregistering-constant-loop","lastmod":"2025-04-11T14:00:57.000Z","id":"0D54u00009evmuBCAQ"} -->
## ZCC in Authentication/Registering constant loop

- Source: https://community.zscaler.com/s/question/0D54u00009evmuBCAQ/zcc-in-authenticationregistering-constant-loop
- Type: Q&A
- Posted: 2021-01-26T09:53:01.000Z
- Last activity: 2025-04-11T14:00:57.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

One of the users that received ZCC (ZAPP) is constantly in loop and have issues. This is second time i see this problem and its not disappearing as first one.
 User received APP normally thru our deployment cycle and it’s stuck/blocked due to this behavior, app is constantly crashing again and again, first it identifies the user, authenticate, register device, download the config, and then it crashes and start everything again.

Has anyone faced this issue before, and is there any solution for this? Version of APP is 2.1.2 i want to upgrade it automatically but its not possible. Next is to make manual upgrade to 3.1 and see if that helps…

Edit: 3.1 version seems to fixed this issue, but am still curious if anyone has experienced same issue?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmuJCAQ/zscaler-app-tries-to-authenticate-wrong-tenant-id","lastmod":"2023-07-06T13:08:04.000Z","id":"0D54u00009evmuJCAQ"} -->
## Zscaler App tries to authenticate wrong tenant id

- Source: https://community.zscaler.com/s/question/0D54u00009evmuJCAQ/zscaler-app-tries-to-authenticate-wrong-tenant-id
- Type: Q&A
- Posted: 2021-01-18T04:28:29.000Z
- Last activity: 2023-07-06T13:08:04.000Z
- Answers: 3
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We instructed to install Zscaler app in 3rd party organization laptop to use ZPA as service in order to access our internal network in secure way. Both the organization uses Azure SSO as SAML IDP. When 3rd party user try to login Zapp with our UPN [user1@xxx.com], Zscaler app always picks the cached UPN credential [user1@yyy.com] from the laptop and throws error

Error:
 [user1@yyy.com](mailto:user1@yyy.com) from identity provider does not exist in tenant “XXX? and cannot access the application Zscaler.

Queries:
 a) is there way to force Zscaler app always use given UPN instead of looking for cached crdential

Regards
 Ganesh Krishnan
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmuKCAQ/iot-devices-dten-me-and-etc-authentication-options-to-zscalergov-no-zapp-install-option","lastmod":"2023-05-31T08:13:58.000Z","id":"0D54u00009evmuKCAQ"} -->
## IoT devices (DTEN Me and etc) authentication options to ZScalerGov (no Zapp install option)

- Source: https://community.zscaler.com/s/question/0D54u00009evmuKCAQ/iot-devices-dten-me-and-etc-authentication-options-to-zscalergov-no-zapp-install-option
- Type: Q&A
- Posted: 2021-01-16T15:22:44.000Z
- Last activity: 2023-05-31T08:13:58.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,
 What are the options for IoT devices (DTEN Me) authentication to ZScalerGov ZIA without ZScaler Cloud Connect app install? DTEN Me can support PAC file.
 Thanks,
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmuNCAQ/zcc-change-userdomain-after-deployment","lastmod":"2023-05-31T08:47:32.000Z","id":"0D54u00009evmuNCAQ"} -->
## ZCC - Change USERDOMAIN after deployment

- Source: https://community.zscaler.com/s/question/0D54u00009evmuNCAQ/zcc-change-userdomain-after-deployment
- Type: Q&A
- Posted: 2021-01-15T08:41:38.000Z
- Last activity: 2023-05-31T08:47:32.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi all,

We have deployed ZCC using the msi and the USERDOMAIN switch.
 However, we now have a large number of users who also need to logon to a second domain.
 Is there a way to remove the USERDOMAIN configuration without redeploying the msi again without the switch?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmuOCAQ/zscaler-client-connector-upgrades","lastmod":"2023-07-06T13:08:13.000Z","id":"0D54u00009evmuOCAQ"} -->
## Zscaler client connector upgrades

- Source: https://community.zscaler.com/s/question/0D54u00009evmuOCAQ/zscaler-client-connector-upgrades
- Type: Q&A
- Posted: 2021-01-14T16:41:06.000Z
- Last activity: 2023-07-06T13:08:13.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello community,

We’re trying to update Zscaler devices but for some machines, it isn’t happening. We observed that the Zscaler client connector is last seen active is pretty recent ( today) whereas the Zscaler Last Configuration Download Time happened days before.
 Supposedly, every 2 hours, Zscaler Client Connector checks for software updates.
 So, my questions:
 1- Do you know why the ZCC isn’t getting updated?
 2- How can we know why without bothering the users?
 3- Is there any specific conditions to be met so that the ZCC can be updated successfully?

thank you,

Jenny
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmuRCAQ/zscaler-app-is-not-turned-on","lastmod":"2023-05-31T08:48:55.000Z","id":"0D54u00009evmuRCAQ"} -->
## Zscaler App is not turned ON

- Source: https://community.zscaler.com/s/question/0D54u00009evmuRCAQ/zscaler-app-is-not-turned-on
- Type: Q&A
- Posted: 2021-01-12T07:59:45.000Z
- Last activity: 2023-05-31T08:48:55.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All

I am not able to turn on Zscaler APP. I install Zscaler Analyzer and looking like issue occured after the installation.

Regards
 Hitesh
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmuUCAQ/client-connector-function-comparison-between-windows-and-macos","lastmod":"2023-05-31T08:48:57.000Z","id":"0D54u00009evmuUCAQ"} -->
## Client Connector Function Comparison between Windows and MacOS

- Source: https://community.zscaler.com/s/question/0D54u00009evmuUCAQ/client-connector-function-comparison-between-windows-and-macos
- Type: Q&A
- Posted: 2021-01-11T06:25:07.000Z
- Last activity: 2023-05-31T08:48:57.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello everyone，

I would like to ask where I can see all menus of client connector(windows version and MacOS version). Are there any differences in functions between the two system versions?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmuWCAQ/cisco-asdm-client-not-working-when-zscaler-app-is-on","lastmod":"2023-05-31T09:25:29.000Z","id":"0D54u00009evmuWCAQ"} -->
## Cisco ASDM Client not working when Zscaler App is on

- Source: https://community.zscaler.com/s/question/0D54u00009evmuWCAQ/cisco-asdm-client-not-working-when-zscaler-app-is-on
- Type: Q&A
- Posted: 2021-01-08T16:34:47.000Z
- Last activity: 2023-05-31T09:25:29.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

When the Zscaler App is on the Cisco ASDM client wont connect to anything. I’ve tried bypass the ip address that is used for the connection but the client still wont connect and just hangs when it tries to connect. The client works perfectly when the zscaler app is turned off.

If anyone has experienced this issue before and has a solution, I’d appreciate the help.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmuaCAA/how-to-uniformly-deploy-client-connectors-in-vdi-environment-users-need-not-install-them","lastmod":"2023-05-31T08:47:52.000Z","id":"0D54u00009evmuaCAA"} -->
## How to uniformly deploy client connectors in VDI environment, users need not install them

- Source: https://community.zscaler.com/s/question/0D54u00009evmuaCAA/how-to-uniformly-deploy-client-connectors-in-vdi-environment-users-need-not-install-them
- Type: Q&A
- Posted: 2021-01-06T02:45:59.000Z
- Last activity: 2023-05-31T08:47:52.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

At present, the company adopts VDI office environment, the VDI management platform currently manages 1500 users’ PCs, and the VDI system environment is Windows Server 2016. I want to achieve the effect of silently deploying ZAPP to users’ PCs in batches. Our r&d director’s deployment method is to push ZAPP to all PCs through VDI management platform, and then use BAT batch command [/VERYSILENT] to realize silent deployment. is this feasible? How does Zscaler do the correct batch silent deployment? Included in AD domain, Radius and desktop management system.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmubCAA/soft-phone-use-case","lastmod":"2023-05-31T08:49:00.000Z","id":"0D54u00009evmubCAA"} -->
## Soft Phone Use Case

- Source: https://community.zscaler.com/s/question/0D54u00009evmubCAA/soft-phone-use-case
- Type: Q&A
- Posted: 2021-01-04T16:34:11.000Z
- Last activity: 2023-05-31T08:49:00.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are very new to the Zscaler Client Connector. It works amazing for virtually everything we use. With one exception. We are setting up a new phone system which has support for soft phones. This software would typically work on a traditional VPN. I’m guessing due to the inherent design of the client connector, and for DNS and NAT reasons, soft phones aren’t supported over the ZPA tunnels. Knowing this, I’m curious how other people are using soft phones back to their internal network while using Zscaler Client Connector VPN.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmueCAA/zapp-not-auto-updating-to-31096","lastmod":"2023-05-31T08:49:04.000Z","id":"0D54u00009evmueCAA"} -->
## Zapp not auto updating to 3.1.0.96

- Source: https://community.zscaler.com/s/question/0D54u00009evmueCAA/zapp-not-auto-updating-to-31096
- Type: Q&A
- Posted: 2020-12-30T09:47:15.000Z
- Last activity: 2023-05-31T08:49:04.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Systems are not getting auto update to “Zscaler Client Connector 3.1.0.96?. Does anyone have any solution rather than having to manually update manually hundreds of systems.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmugCAA/client-connector-disconnects","lastmod":"2023-05-31T08:49:04.000Z","id":"0D54u00009evmugCAA"} -->
## Client Connector Disconnects

- Source: https://community.zscaler.com/s/question/0D54u00009evmugCAA/client-connector-disconnects
- Type: Q&A
- Posted: 2020-12-22T16:48:44.000Z
- Last activity: 2023-05-31T08:49:04.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Client connector is getting disconnected in between and after the reboot of the machine, it is working. Please let me know the cause, every time the machine needs to be rebooted for the fix. Any specific reason for this
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmuiCAA/zcc-updates-resulting-in-broken-zia-or-zpa","lastmod":"2023-07-06T13:08:36.000Z","id":"0D54u00009evmuiCAA"} -->
## Zcc updates resulting in broken ZIA or ZPA

- Source: https://community.zscaler.com/s/question/0D54u00009evmuiCAA/zcc-updates-resulting-in-broken-zia-or-zpa
- Type: Q&A
- Posted: 2020-12-17T15:06:56.000Z
- Last activity: 2023-07-06T13:08:36.000Z
- Answers: 17
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We’ve been testing rollout of ZApp 3.1.0.88 and .96 for Windows only (no Macs here) in a ZIA+ZPA environment, and across about 100 users, we’ve seen 3 that end up in an unusable state. Specifically, ZPA may give a Connection Error, or fail authentication. Maybe Retry will work, maybe Reboot, but more often it is Logout/Login that is required. For one user, we also tripped over the bug where the one-time logout password wouldn’t work, so we had to force remove them.

We’re using Azure AD for SAML auth. Are any other organizations encountering this? Are you also using Azure AD? I’m looking for commonalities, as I’m not sure there’s anything specific we can raise a ticket for.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmukCAA/client-connector-for-big-sur-and-m1-hardware","lastmod":"2023-07-06T13:08:48.000Z","id":"0D54u00009evmukCAA"} -->
## Client Connector for Big Sur and M1 hardware

- Source: https://community.zscaler.com/s/question/0D54u00009evmukCAA/client-connector-for-big-sur-and-m1-hardware
- Type: Q&A
- Posted: 2020-12-17T07:44:30.000Z
- Last activity: 2023-07-06T13:08:48.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

Thanks for the great Zenith Live experience! Nowhere near an in-person event, yet great sessions and good and fruitful interactions.

What’s missing are the beers together!

Quick question on whether Big Sur and M1 chip are already fully supported by the Client Connector?

Is there a specific version and build to install in such cases?

Many thanks,

Luca
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmupCAA/distribution-issue-of-client-connector","lastmod":"2023-05-31T08:49:09.000Z","id":"0D54u00009evmupCAA"} -->
## Distribution Issue of client connector

- Source: https://community.zscaler.com/s/question/0D54u00009evmupCAA/distribution-issue-of-client-connector
- Type: Q&A
- Posted: 2020-12-12T03:49:06.000Z
- Last activity: 2023-05-31T08:49:09.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi, community

My organization has long been using same client connector version.
 Recently we decided to start to deploy newer version.

Added admin group first to automated rollouting feature.
 One day, two days, three days passed…however target group does not have new version.

Antivirus and Firewall were checked without fruit.

Someone has same problem?

Kind Regard,
 Muhammad
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmv1CAA/zcc-zapp-traffic-handling-for-macos-ios-and-androis-devices","lastmod":"2023-05-31T09:25:31.000Z","id":"0D54u00009evmv1CAA"} -->
## ZCC (ZApp) Traffic handling for MacOS, iOS and Androis devices

- Source: https://community.zscaler.com/s/question/0D54u00009evmv1CAA/zcc-zapp-traffic-handling-for-macos-ios-and-androis-devices
- Type: Q&A
- Posted: 2020-12-04T17:54:41.000Z
- Last activity: 2023-05-31T09:25:31.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

Except Windows devices which handles traffic with ZCC through ‘Windows Filter Driver’,

How the traffic is performed with the other OS for Mac, iPhone, iPAD and Android devices.

Regards,

Fatih
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmv2CAA/clientconnector-v31088-stays-on-tunnel-v10","lastmod":"2023-05-31T08:49:09.000Z","id":"0D54u00009evmv2CAA"} -->
## ClientConnector v3.1.0.88 - Stays on Tunnel v1.0

- Source: https://community.zscaler.com/s/question/0D54u00009evmv2CAA/clientconnector-v31088-stays-on-tunnel-v10
- Type: Q&A
- Posted: 2020-12-04T05:47:02.000Z
- Last activity: 2023-05-31T08:49:09.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

All,

We have used several versions of Windows Client Connector - using Tunnel v2 - without problems for a long time. I’m obviously testing new Windows Client Connector release v3.1.0.88. But this new version stays on Tunnel v1.0 so can’t be deployed. I can’t figure out why it stays on Tunnel v1.0 while App Profile Policy is unchanged and it dictate; use Tunnel v2.

Does anyone experience the same issue, and have you found a solution for it?

Thanks,
 /Jesper
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmv8CAA/zscaler-zapp-linux-client","lastmod":"2023-05-31T09:08:21.000Z","id":"0D54u00009evmv8CAA"} -->
## ZScaler ZApp Linux Client

- Source: https://community.zscaler.com/s/question/0D54u00009evmv8CAA/zscaler-zapp-linux-client
- Type: Q&A
- Posted: 2020-11-20T17:02:13.000Z
- Last activity: 2023-05-31T09:08:21.000Z
- Answers: 3
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Apparently I can no longer add replies to the previous thread ([ZPA ZApp for Linux required](https://community.zscaler.com/t/zpa-zapp-for-linux-required/5730)), so I’m starting a new one. When will a ZApp client be available for Linux?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmv9CAA/use-case-for-f5-application-in-zcc-installed-device","lastmod":"2023-05-31T09:28:02.000Z","id":"0D54u00009evmv9CAA"} -->
## Use case for F5 application in ZCC installed device

- Source: https://community.zscaler.com/s/question/0D54u00009evmv9CAA/use-case-for-f5-application-in-zcc-installed-device
- Type: Q&A
- Posted: 2020-11-20T09:18:11.000Z
- Last activity: 2023-05-31T09:28:02.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello there

My customer uses F5 and ZCC.
 When a user runs F5, some weird behavior happens.
 So he cannot use F5 and ZCC at the same time.

Current Configuration in ZCC is like as follow.

Forwarding Profile : Enforce Proxy(Enforce)
 Check “Use Automatic Configuration Script?

However, when I opened ticket to investigate, the cause was proxy confliction both by ZCC and by F5.

But our customer found our that there was no proxy setting has been done in F5 application.

What I am looking for are these 2 things below.

1. To detect other trigger/application which is pushing proxy settings. -> When my customer runs F5 application, so obviously F5 is pushing it though…
2. Whether there is not such a big change from Enforce Proxy(Enforce) to None(Apply on Network Changes) -> This might be the solution. But we are afraid if this change will cause some other problem.

I have heard that F5 application cannot use in Enforce Proxy nor in TWLP mode.

Therefore, 1 does not matter that much.
 I am looking for the differences about 2 if there is someone knows.

Regards,
 Tokio
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvCCAQ/script-error-popups-in-zapp-during-login-when-using-google-saml","lastmod":"2023-07-06T13:06:31.000Z","id":"0D54u00009evmvCCAQ"} -->
## Script Error Popups in ZApp During Login when Using Google SAML

- Source: https://community.zscaler.com/s/question/0D54u00009evmvCCAQ/script-error-popups-in-zapp-during-login-when-using-google-saml
- Type: Q&A
- Posted: 2020-11-19T15:26:07.000Z
- Last activity: 2023-07-06T13:06:31.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

OS: Windows 10

We’re getting IE Script Debug popups during the initial login to ZApp after deploying it to users.

Users have to keep clicking “Yes? on several of these popup messages in order to login to ZApp with their Google credentials. Some resources on the Google login also fail to load making it more difficult to login with 2FA.

Just wondering if anyone else has encountered this issue and what the solution was. We have tried adding [ssl.gstatic.com](http://ssl.gstatic.com) to IE’s trusted sites list to no avail. ZScaler Support advised that it’s an IE caching issue but clearing the cache & temp internet files doesn’t seem to help. I have also tried disabling Scipt Debugging in the IE settings.

[[zapp_script_error]zapp_script_error752×546 22 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O66WAAS)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvDCAQ/chrome-root-ca-store","lastmod":"2023-07-06T11:46:10.000Z","id":"0D54u00009evmvDCAQ"} -->
## Chrome root CA store

- Source: https://community.zscaler.com/s/question/0D54u00009evmvDCAQ/chrome-root-ca-store
- Type: Q&A
- Posted: 2020-11-18T07:29:58.000Z
- Last activity: 2023-07-06T11:46:10.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello:
 In the near future chrome will have its own certificate store like firefox does.

chromium.org
##### [Chrome Root Program - The Chromium Projects](https://www.chromium.org/Home/chromium-security/root-ca-policy)

Home of the Chromium Open Source Project

Should zscaler client connector clients worry about it?, since it won’t use OS cert store I think there is some kind of modification to the client connector in order to work properly for ssl inspection.
regards.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvFCAQ/unable-to-browse-through-the-windows-proxy-tool-after-zapp-uninstall","lastmod":"2023-05-31T08:49:16.000Z","id":"0D54u00009evmvFCAQ"} -->
## Unable to browse through the windows proxy tool after zapp uninstall

- Source: https://community.zscaler.com/s/question/0D54u00009evmvFCAQ/unable-to-browse-through-the-windows-proxy-tool-after-zapp-uninstall
- Type: Q&A
- Posted: 2020-11-17T16:42:33.000Z
- Last activity: 2023-05-31T08:49:16.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello there

I have used zapp for a long time but due some activities and tests that I need to perform I had to completely uninstall zapp. But what I don’t undestand is why my connection does not go through the proxy settings on Windows. Plus, the windows proxy I use, still it’s zscaler but not the app.

Per my tshoot I conclued that the connection does not even read the proxy info, going right to my router.

I’m unable to browse however it is possible to connect do [admin.zscaler.net](http://admin.zscaler.net)

Any help is a aprecciated.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvJCAQ/vpn-issues-since-version-2-of-client-connector","lastmod":"2023-05-31T08:14:02.000Z","id":"0D54u00009evmvJCAQ"} -->
## VPN Issues - Since version 2 of client connector

- Source: https://community.zscaler.com/s/question/0D54u00009evmvJCAQ/vpn-issues-since-version-2-of-client-connector
- Type: Q&A
- Posted: 2020-11-09T12:49:17.000Z
- Last activity: 2023-05-31T08:14:02.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We subscribe to the ‘latest version’ of the Zscaler Client Connector, and it looks like in the past couple of weeks we’ve been upgraded from version 1 to 2.

Changes logs from example laptop:
 Add Zscaler - 2.1.2.113 - Zscaler Inc. - 29/10/2020 12:21:53
 Remove Zscaler - 1.5.1.8 - Zscaler Inc. - 29/10/2020 12:21:53

Also in the past couple of weeks we’ve been having more and more reports of users not being able to access corp resources since connecting. This is usually resolved by disconnecting/reconnecting, or restarting.

Could there be a link? The next person to report it I plan to tell them the ‘disable’ password to see if it springs to life, however I suspect a reconnect would still be required which then leaves us uncertain if disabling the app helped or just the disconnect/reconnect fixed it.

Any other troubleshooting tips welcomed. Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvKCAQ/zscaler-zpa-client-connector-is-disabling-automatically-on-logoff","lastmod":"2023-05-31T08:49:16.000Z","id":"0D54u00009evmvKCAQ"} -->
## Zscaler ZPA client connector is disabling automatically on logoff

- Source: https://community.zscaler.com/s/question/0D54u00009evmvKCAQ/zscaler-zpa-client-connector-is-disabling-automatically-on-logoff
- Type: Q&A
- Posted: 2020-11-06T21:23:23.000Z
- Last activity: 2023-05-31T08:49:16.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Zscaler ZPA client connector is disabling automatically on Windows 10 azure PC is not connect through RDP. As soon as I connect Windows 10 azure PC using RDP Zscaler ZPA client connector enables can someone please let me know how I can keep Zscaler ZPA client connector always connected even if I disconnect or logoff Windows 10 azure PC from RDP.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvOCAQ/zscalar-login-loop","lastmod":"2023-05-31T08:49:22.000Z","id":"0D54u00009evmvOCAQ"} -->
## Zscalar Login Loop

- Source: https://community.zscaler.com/s/question/0D54u00009evmvOCAQ/zscalar-login-loop
- Type: Q&A
- Posted: 2020-11-02T21:42:46.000Z
- Last activity: 2023-05-31T08:49:22.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hey guys,

I have a user who is experiencing a login loop with the client connector. As soon as the user logs in, they get redirected to the login page of the ZAPP.
 uninstalled the old ZAPP version and installed 2.1.2.105. Tried again and still having issues. Any thoughts?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvPCAQ/tunnel-20-making-windows-10-19041-making-surface-pro-6-crash","lastmod":"2023-05-31T08:14:02.000Z","id":"0D54u00009evmvPCAQ"} -->
## Tunnel 2.0 making windows 10 (19041) making surface pro 6 crash?

- Source: https://community.zscaler.com/s/question/0D54u00009evmvPCAQ/tunnel-20-making-windows-10-19041-making-surface-pro-6-crash
- Type: Q&A
- Posted: 2020-11-01T17:16:20.000Z
- Last activity: 2023-05-31T08:14:02.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Good Afternoon,
 Has anyone received reports from user’s machines freezing/ forcing user to reboot using windows 10 in surface pro 6 while in Tunnel 2.0?
 The user has never had this type of issue while on Tunnel 1.0, I am trying to figure out if this is windows updates related or tunnel 2.0 related.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvQCAQ/shared-activation-accounts-for-ms-officeo365-apps-not-working-after-windows-10-2004-update","lastmod":"2023-05-31T08:49:22.000Z","id":"0D54u00009evmvQCAQ"} -->
## Shared Activation Accounts for MS Office/O365 Apps not working after Windows 10 2004 Update

- Source: https://community.zscaler.com/s/question/0D54u00009evmvQCAQ/shared-activation-accounts-for-ms-officeo365-apps-not-working-after-windows-10-2004-update
- Type: Q&A
- Posted: 2020-10-29T20:23:26.000Z
- Last activity: 2023-05-31T08:49:22.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I’m wondering if anyone has run into an issue where users with shared activation accounts for MS Office have not been able to signin to onedrive/office/other O365 apps after their windows 10 machine was updated to 2004? It doesn’t seem to matter if it’s zscaler client connector v1.5.2.7 or v2.1.2.81, we get the same results. When we disabled the zscaler client connector and set an explicit proxy in the browser, the issue was resolved. Has anyone seen this behavior and can explain what’s happening?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvSCAQ/zapp-connection-error","lastmod":"2023-07-06T11:46:12.000Z","id":"0D54u00009evmvSCAQ"} -->
## ZApp connection error

- Source: https://community.zscaler.com/s/question/0D54u00009evmvSCAQ/zapp-connection-error
- Type: Q&A
- Posted: 2020-10-28T00:43:04.000Z
- Last activity: 2023-07-06T11:46:12.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

HI All,

I have followed and check below community reference and suggestion. However, ZAPP still prompt the same error. Appreciate if anyone can assist and guide if there are overlook information and configuration that I miss out to resolve the issue. Thanks

[Zapp ends in Endpoint FW/AV error](https://community.zscaler.com/t/zapp-ends-in-endpoint-fw-av-error/4901) [Zscaler Client Connector](https://community.zscaler.com/c/zscaler-products/zscaler-app/52)   user facing issue Zapp is logged in. It stays connected for 30sec and get error related to Endpoint FW/AV error. Restart service, Repair, Reboot, Logout ends in same error. We open port 9000 via GPO. Gpupdate is successful in user machine. Still issue persists only with one user. ZSATray logs says “FIREWALL BLOCK ERROR?. #NORMAL #INFO : ZIA state changed, From: TUNNEL_FORWARDING To: FIREWALL_BLOCK_ERROR Regards Ganesh krishnan

help.zscaler.com
[image] [Zscaler Client Connector: Connection Status Errors | Zscaler](https://help.zscaler.com/z-app/zscaler-app-connection-status-errors)

help.zscaler.com
[image] [Zscaler Client Connector Processes to Whitelist | Zscaler](https://help.zscaler.com/z-app/zscaler-app-processes-whitelist)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvUCAQ/zapp-unable-to-block-google-site","lastmod":"2023-07-06T13:09:26.000Z","id":"0D54u00009evmvUCAQ"} -->
## ZApp unable to block google site

- Source: https://community.zscaler.com/s/question/0D54u00009evmvUCAQ/zapp-unable-to-block-google-site
- Type: Q&A
- Posted: 2020-10-28T00:23:23.000Z
- Last activity: 2023-07-06T13:09:26.000Z
- Answers: 14
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

Anyone encounter issue as per subject, current only one policy is enable which is to block all site.
 We only encounter this issue when using the ZApp which able to block other except google site including youtube.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvVCAQ/log-entries-loopback-exception-is-required-in-zapp","lastmod":"2023-06-02T17:08:17.000Z","id":"0D54u00009evmvVCAQ"} -->
## Log entries "loopback exception is required" in Z-App

- Source: https://community.zscaler.com/s/question/0D54u00009evmvVCAQ/log-entries-loopback-exception-is-required-in-zapp
- Type: Q&A
- Posted: 2020-10-27T09:59:22.000Z
- Last activity: 2023-06-02T17:08:17.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello all,

we use Z-App 2.1.2.113 (Windows 10) with Z-Tunnel 2.0, Tunnel-mode and PacketFilterDriver. In the ZSATunnel-Log we found entries like these:

2020-10-27 09:15:33.354039(+0100)[12860:7720] ERR Number of AppContainers in Loopback Exception List: 95
 2020-10-27 09:15:33.354039(+0100)[12860:7720] ERR Loopback Exception is required! Windows8OrGreater: true, appContainerCount: 168, loopbackExceptionCount: 95 Result: 1

Sounds like we should enable “Disable Loopback Restriction?-switch, but AFAIK it should not be needed for this configuration (neither should “Restart WinHTTP Service? nor “Override WPAD?). Zscaler documentation states these switches are only applicable if using Local Proxy Mode. But maybe I am just misinterpreting this logentry.

Can someone enlighten me please what exactly that means?

Thanks and best regards
 Manuel
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvZCAQ/periodic-issues-connecting-to-o365-with-teams-and-outlook-clients","lastmod":"2025-01-29T17:59:57.000Z","id":"0D54u00009evmvZCAQ"} -->
## Periodic Issues Connecting to O365 with Teams and Outlook clients

- Source: https://community.zscaler.com/s/question/0D54u00009evmvZCAQ/periodic-issues-connecting-to-o365-with-teams-and-outlook-clients
- Type: Q&A
- Posted: 2020-10-20T20:39:32.000Z
- Last activity: 2025-01-29T17:59:57.000Z
- Answers: 54
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I have looked around and there are a number of threads such as this [such as this one](https://community.zscaler.com/t/issues-with-outlook-when-working-outside-corporate-network-with-zapp/7112) which are similar but do not match exactly, so I figured I would start another thread just in case there are others out there.

While piloting Zscaler Client Connector/ZApp (herein ZCC) with our I.S. department, I received periodic complaints from remote users that Outlook would not connect to their mailbox to send/receive email. If you “Turn Off? ZCC, the problem will resolve itself immediately. And once Outlook and/or Teams has connected, you can turn ZCC back on and everything will work properly going forward. These are the only apps I have received complaints or noticed the issue. If I open up Excel or Word, I seem to be logged in/connected properly.

Unlike the link above, I DO NOT see tons of entries in the event logs indicating a problem. I have opened up a support ticket and the first round provided some subtle change(s) which may or may not have fixed the issue. It is hard saying since it is intermittant.

I am able to replicate the issue with a VMware VM running Windows on my MacBook, but this could also have something to do with the way things are networked from Fusion, so I am waiting to hear back from my testing pool. I would love to get this problem behind us so it doesn’t bite us as we roll out to 3,350 more users. So if anyone else has had this issue and has fixed it, I would love to hear how. We are only using ZIA right now. And this is with MS Teams and Outlook clients connecting to O365. It almost seems like the clients are unable to authenticate.

Thanks for your time -
 -Rob
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvbCAA/transparent-authentication-for-pac-zapp","lastmod":"2023-05-31T08:49:27.000Z","id":"0D54u00009evmvbCAA"} -->
## Transparent authentication for PAC & Zapp

- Source: https://community.zscaler.com/s/question/0D54u00009evmvbCAA/transparent-authentication-for-pac-zapp
- Type: Q&A
- Posted: 2020-10-19T01:58:45.000Z
- Last activity: 2023-05-31T08:49:27.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Guys,

Would like to know if below configuration is achievable. We receive enquiry scenario from customer, due to their limited IT team resource they would like to deploy Zscaler without user interaction and notice.

Authentication Method : On premise Microsoft AD
 Requirement :
 -Hidden Zapp tray/agent
 -Transparent authentication without prompt for login.
 -Notified IT if user are not authenticate or uninstall the agent.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvdCAA/how-to-check-if-traffic-correctly-forwarded-by-zapp-or-not","lastmod":"2023-05-31T08:49:30.000Z","id":"0D54u00009evmvdCAA"} -->
## How to check if traffic correctly forwarded by zapp or not

- Source: https://community.zscaler.com/s/question/0D54u00009evmvdCAA/how-to-check-if-traffic-correctly-forwarded-by-zapp-or-not
- Type: Q&A
- Posted: 2020-10-15T10:06:51.000Z
- Last activity: 2023-05-31T08:49:30.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi all
 i have a question about zapp forwarding with road warrior users.
 All workstations have zapp client running, and one some sites or ressources, it seems we are seen with the public ip of the client but not with zscaler proxy ip (so problem with ip filtering).
 We use ztunnel 1.0, mode tunnel for rw, pac in forwarding and app profile (previously in tunnel with loca proxy).
 How can i check traffic is correctly forwarded via zapp and not sent direct ?
 Thanks for your help
 Regards
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvfCAA/zscaler-client-connector-not-updating","lastmod":"2023-05-31T08:14:02.000Z","id":"0D54u00009evmvfCAA"} -->
## Zscaler Client Connector not updating

- Source: https://community.zscaler.com/s/question/0D54u00009evmvfCAA/zscaler-client-connector-not-updating
- Type: Q&A
- Posted: 2020-10-14T13:18:19.000Z
- Last activity: 2023-05-31T08:14:02.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We made some adjustments to our Client Connector to use Strict Enforcement. Once we made the modification to the installer, it no longer is able to update when you go into the Zscaler portal and allow the updates to happen on test users. Everything worked fine until we tested this process.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvhCAA/windows-ncsi-network-connectivity-status-indicator-issue-with-zapp","lastmod":"2023-05-31T08:49:30.000Z","id":"0D54u00009evmvhCAA"} -->
## Windows NCSI (Network Connectivity Status Indicator) Issue with ZAPP

- Source: https://community.zscaler.com/s/question/0D54u00009evmvhCAA/windows-ncsi-network-connectivity-status-indicator-issue-with-zapp
- Type: Q&A
- Posted: 2020-10-13T16:02:46.000Z
- Last activity: 2023-05-31T08:49:30.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Zscaler Community,

I would like to check with the community if anyone has experienced an issue during the deployment as we did and how did you manage to overcome such an problem?

Summary:
 We bought the ZIA for all of our users where we in process of deployment but we are having a big blocker during this process as we experiencing some quite weird issue with Yellow triangle or Globe icon
 (depending on a Windows 10 build version) this problem is not appearing when we are off the VPN (Cisco Anyconnect) But as soon as we connect there is a yellow triangle appearing and causing all O365 application not to work, on other hand there is a internet connectivity all normal but it seems that WIndows NCSI process is failing to do it’s job.

What we have in our setup is ZAPP 2.1.2.81, we have on prem VZEN deployed our LB and having dual arm setup, VZEN is only handling local web traffic and some public websites that are allowing only our public IP so we are anchoring that thru VZEN.

Configuration wise we have APP profile with default PAC file that is responsible for internet traffic and routing everything thru Ztunnel 1.0, nothing fancy there except couple of bypasses for URL’s or routing something to go via on prem VZEN,
 next to that we have forwarding profile with Packet Filer + tunnel mode and we are enforcing PAC file to the users by the ZAPP. In that PAC file we have have routing based on SRC and DST ip’s for certain countries to go via their own VZEN’s.

In the begining this issue with NCSI was present on all WIndows 10 Machines as soon as they connect on VPN, but later on we add line in PAC file that i will share below that we belived fixed the issue.

> ```
> /MS
        if (shExpMatch(host, "activation.sls.microsoft.com") ||
        shExpMatch(host, "officecdn.microsoft.com") ||
        shExpMatch(host, "*.msftconnecttest.com") ||
        shExpMatch(host, "*.msftncsi.com") ||
        shExpMatch(host, "*.msedge.net") ||
        shExpMatch(host, "*.c-msedge.net") ||
        shExpMatch(host, "msftncsi.com"))
        {
        return "PROXY ${GATEWAY_FX}:443; PROXY ${SECONDARY_GATEWAY_FX}:443; DIRECT;"
        }
> ```
>

So once we added this in the Forwarding profile PAC file this fixed the issue so far for Windows 10 build 1909, but as majority of build version is 1809 this still remains unresolved for them.

From our firewall perspective there is no any kind of blocking toward MS domains or IP’s that are used for testing if there is internet or not (NCSI Probes)

One interesting finding was that if we disable automatic probing in registry and restart the Network Location Awareness service, the yellow triangle is gone even when you are connected on VPN, but this is not a solution since even if you dont have internet it will show normal icon as you would have it.

> Computer\HKEY_LOCAL_MACHINE\SOFTWARE\Policies\Microsoft\Windows\NetworkConnectivityStatusIndicator |NoActiveProbe = 0|
>

So am curious if there is anyone in community that experienced such an issue, and how did you guys fix it?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvjCAA/hash-values-for-zcc-processes","lastmod":"2023-05-31T08:14:02.000Z","id":"0D54u00009evmvjCAA"} -->
## Hash values for ZCC Processes

- Source: https://community.zscaler.com/s/question/0D54u00009evmvjCAA/hash-values-for-zcc-processes
- Type: Q&A
- Posted: 2020-10-13T06:13:47.000Z
- Last activity: 2023-05-31T08:14:02.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Team,

Can we get hash values of the processes listed on [https://help.zscaler.com/z-app/zscaler-app-processes-whitelist](https://help.zscaler.com/z-app/zscaler-app-processes-whitelist).
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvvCAA/split-tunnel-scenarios","lastmod":"2023-06-02T17:08:15.000Z","id":"0D54u00009evmvvCAA"} -->
## Split Tunnel Scenarios

- Source: https://community.zscaler.com/s/question/0D54u00009evmvvCAA/split-tunnel-scenarios
- Type: Q&A
- Posted: 2020-10-06T19:52:52.000Z
- Last activity: 2023-06-02T17:08:15.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

I’ve been doing a lot of reading on the community and zscaler article but i’m still getting confuse about app profile, forwarding profile and split tunnel. So I want to see if anyone can help.

(1) when should I use app profile pac vs forwarding profile pac? this is my current understanding of app profile & forwarding profile:
 user request —> app profile (to decide if traffic should forward or bypass zapp all together) --> forwarding profile (decide what to do with that traffic for zia/zpa)

(2) my client wants to implement split tunnel because some of their internal application are server to client so it would not work with zpa. specifically we are looking at following scenarios:
 a. when user is on-premise:
 i. zpa —> disable
 ii. zia —> enable; all traffic will go through gre tunnel
 b. when user is off-premise:
 i. zpa —> enable; private app traffic will go through zpa, and some exception apps will go through anyconnect vpn if they are not compatible
 ii. zia ----> enable; all traffic will go to zia
 They had zia for years and is now looking to implement zpa along with zapp. Any guidance on the best way to configure these scenario would be helpful.

Thanks,
 Steven
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmvyCAA/guide-deploy-zscaler-client-connector-with-intune-windows-macos","lastmod":"2024-11-12T15:12:03.000Z","id":"0D54u00009evmvyCAA"} -->
## [Guide] Deploy Zscaler Client Connector with Intune (Windows & macOS)

- Source: https://community.zscaler.com/s/question/0D54u00009evmvyCAA/guide-deploy-zscaler-client-connector-with-intune-windows-macos
- Type: Q&A
- Posted: 2020-10-05T23:19:41.000Z
- Last activity: 2024-11-12T15:12:03.000Z
- Answers: 12
- Likes: 18
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

banner1200×600 44.8 KB

> Originally posted @ [https://nathancatania.com/posts/deploy-zapp-with-intune/](https://nathancatania.com/posts/deploy-zapp-with-intune/)
 You can use the Table of Contents at this link to jump to the sections you need.
>

In this guide, we’ll walkthrough how to configure Microsoft Intune from scratch and use it to deploy the Zscaler Client Connector agent (ZCC) - formerly known as Zscaler Client Connector (ZCC).

Due to length, I’ve split this into two posts:

- This post covers deployment on Windows and macOS.
- The other post, [available here](https://community.zscaler.com/posts/deploy-zapp-mobile-with-intune), covers iOS and Android.

### What is Intune?

[According](https://docs.microsoft.com/en-us/mem/intune/fundamentals/what-is-intune) to Microsoft:

> Microsoft Intune is a cloud-based service that focuses on mobile device management (MDM) and mobile application management (MAM).
 With Intune, you can:
>
> - Set rules and configure settings on personal and organization-owned devices to access data and networks.
> - Deploy and authenticate apps on devices – on-premises and mobile.
> - Be sure devices and apps are compliant with your security requirements.
>

In order to access Intune, you need to have either a Microsoft 365 or [Enterprise & Mobility E3/E5](https://www.microsoft.com/en-au/microsoft-365/enterprise-mobility-security/compare-plans-and-pricing) subscription. If you’re using a free Azure account, you’ll need to sign up to a trial, or pay per user (which can get costly).

### Video Demonstration

Scott Bullock (@scottyb) has [posted a great 10 minute video](https://community.zscaler.com/t/z-app-deployment-with-microsoft-intune/4606) that runs through the user experience of enrolling a fresh Windows 10 device into Intune. ZCC is automatically pushed out and transparently authenticated for both ZIA and ZPA.

### Before You Begin

When adding an app to Intune, you’ll be prompted to allocate the groups of users (or devices) that the app will be rolled out to. Hence before beginning, ensure you have the users of Zscaler inside of an AD or Azure AD group that you can assign the Zscaler Client Connector app to.

Depending on whether you want the ZCC app to be mandatory or optional for certain groups of users, you may want to divide your users into two groups:

1. The users to which the app is MANDATORY. Any user in this group will have the app automatically pushed out to them.
2. The users to which the app is OPTIONAL. The app will not be automatically pushed for users in this group, allowing them to go to the Company Portal and download it themselves if they choose.

In my examples below, I have 3 groups:

| Group | Description |
| --- | --- |
| ZIA_Entitlement | This is the group of all users that are entitled to use Zscaler Internet Access (ZIA) |
| ZPA_Entitlement | This is the group of all users that are entitled to use Zscaler Private Access (ZPA). In my case, this is a subset of users from the ZIA_Entitlement group as I might not want to roll ZPA out to every user in the organization. |
| Zscaler - Mandatory | This group contains every user in the organization to which the ZCC app will be automatically rolled out to. Ie: The majority of users from the above two groups. If this is your organization, you might include the whole org in this group, except select users (eg: some from IT) for which the app will be optional. |

### Microsoft Endpoint Manager

We’ll be using the **Microsoft Endpoint Manager console** (MEM) to orchestrate Intune. You can log in using the same Azure Portal credentials here: [https://endpoint.microsoft.com](https://endpoint.microsoft.com)

[[1]11396×872 103 KB](https://global.discourse-cdn.com/zscaler/original/2X/a/a678191c08c1859deaea29f76da171cab6a6c40d.png)

#### (Optional) Setting the MDM Authority

If you’re using an existing Office 365 account **and** have been using the Office 365 MDM, you’ll need to change the MDM authority from Office 365 to Intune. [This](https://docs.microsoft.com/en-us/mem/intune/fundamentals/mdm-authority-set) Microsoft help article will guide you through it.

### Windows

This section will cover deploying ZCC onto Windows using Intune.

#### 1. Download the Zscaler Client Connector MSI

To start you’ll need the .MSI installer for ZCC from the Zscaler Client Connector Portal. Log into the portal (either through ZIA or ZPA) and navigate to **Administration > Zscaler Client Connector Store**.

[[4]41399×794 87.2 KB](https://global.discourse-cdn.com/zscaler/original/2X/8/82d6193d3fa5de9d532f741a27fa02ea238aeaab.png)

In the **Windows** panel, download the **MSI** for the latest **2.X.X** version. Do not use the older 1.X.X releases.

#### 2. Add a new Line-of-Business App

##### Add a new Line of Business (LoB) App

Back in the **Apps** menu of the MEM portal, navigate to **Apps > All Apps > Add**. In the panel that appears, scroll to the bottom and under the **Other** heading, select **Line-of-business app**.

[https://global.discourse-cdn.com/zscaler/original/2X/9/92cd1ee6580551393970a25098c9b3eea95f0fe9.png](https://global.discourse-cdn.com/zscaler/original/2X/9/92cd1ee6580551393970a25098c9b3eea95f0fe9.png)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmw0CAA/configuring-client-connector-deployment-with-automated-authentication-with-okta","lastmod":"2023-07-06T13:10:09.000Z","id":"0D54u00009evmw0CAA"} -->
## Configuring Client Connector deployment with automated authentication with Okta

- Source: https://community.zscaler.com/s/question/0D54u00009evmw0CAA/configuring-client-connector-deployment-with-automated-authentication-with-okta
- Type: Q&A
- Posted: 2020-10-02T17:16:18.000Z
- Last activity: 2023-07-06T13:10:09.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello everyone,

One of my clients would like to deploy Client Connector with fully automated SSO authentication while using OKTA as the IDP. I m not sure if this is possible. From the deployment guide we have the USERDOMAIN variable that says:

help.zscaler.com
[image] [Customizing Zscaler Client Connector with Install Options for MSI | Zscaler](https://help.zscaler.com/z-app/customizing-zscaler-app-install-options-msi)

This install option allows users to skip the app enrollment page. ([https://help.zscaler.com/z-app/customizing-zscaler-app-install-options-msi#ud2image](https://help.zscaler.com/z-app/customizing-zscaler-app-install-options-msi#ud2image)) If SSO is enabled for your organization, users are taken right to your organization’s SSO login page. If you’ve integrated SSO with the app (i.e., using a mechanism like Integrated Windows Authentication (IWA)), users can also skip the SSO login page and are automatically enrolled with Zscaler service and logged in.

I ve found some docs explaining it is possible with ADFS or Azure but nothing really regarding Okta. Has anyone already done this config?

Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmw3CAA/remotely-triggered-logout-and-reenrollment-for-zscaler-client-connector","lastmod":"2023-05-31T08:49:36.000Z","id":"0D54u00009evmw3CAA"} -->
## Remotely Triggered logout and re-enrollment for Zscaler Client Connector

- Source: https://community.zscaler.com/s/question/0D54u00009evmw3CAA/remotely-triggered-logout-and-reenrollment-for-zscaler-client-connector
- Type: Q&A
- Posted: 2020-10-01T07:12:09.000Z
- Last activity: 2023-05-31T08:49:36.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We have zscaler implementation integrated with AD. we have configured a password to prevent users from logging-off. We are using Zscaler client connector version (1.4.3.1)
 when we get a whitelist request we add the URL’s to an AD group and subsequently RDP to the user machine log-off(due to password control) from Zscaler and have the user login again and re-enroll for the whitelist to take effect. we are trying to automate this process need help with remotely logging-out and triggering re-enrollment in user machines. Has anyone tried this before.
 Is this possible to trigger log-out and re-enrollment through Command Line (or) Script.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmw4CAA/chromeos-linux-beta","lastmod":"2023-07-06T11:46:18.000Z","id":"0D54u00009evmw4CAA"} -->
## ChromeOS Linux Beta

- Source: https://community.zscaler.com/s/question/0D54u00009evmw4CAA/chromeos-linux-beta
- Type: Q&A
- Posted: 2020-09-30T12:58:49.000Z
- Last activity: 2023-07-06T11:46:18.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are starting to test the ChromeOS Linux Beta and finding it cannot use ZPA Android app when requesting apps by hostnames. It can however make use of ZPA Android app by IP addresses. It’s my understanding that this Linux runs as a VM in parallel to the ChromeOS. Is this something that can be corrected to use only one app or will employees have to run a Zscaler client connector in each OS?

I didn’t put this in the thread requesting a linux client connector because I am unclear if it will be necessary to run two zscaler client connectors from one system at the same time. The Chrome OS is moving in that direction where you have Chrome Apps, Android Apps and Linux apps all working at the same time. Sometimes you could have an app available for all three and you have to decide which makes the most sense for the app so I can’t imagine it needing to run in all three at the same time.

support.google.com
[image] [Set up Linux (Beta) on your Chromebook - Chromebook Help](https://support.google.com/chromebook/answer/9145439?hl=en)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwACAQ/want-to-enforce-outbound-firewall-rules-for-zapp","lastmod":"2023-07-06T11:46:19.000Z","id":"0D54u00009evmwACAQ"} -->
## Want to enforce outbound firewall rules for Zapp

- Source: https://community.zscaler.com/s/question/0D54u00009evmwACAQ/want-to-enforce-outbound-firewall-rules-for-zapp
- Type: Q&A
- Posted: 2020-09-23T17:50:45.000Z
- Last activity: 2023-07-06T11:46:19.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi everyone

I would like to use Windows Defender Firewall and apply strict firewall rules to ZApp.

I’m going to make the following rules.

**Outbound**, action: **allow**, program: **ZSATunnel.exe**, remotePort: **80,443/tcp**

The forwarding mode is **Packet Filter Based** and **Tunnel 1.0.**

How about this?

I’m waiting for your reply.
 Best regards.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwECAQ/zscaler-client-connector-and-unsecured-wlans","lastmod":"2023-05-31T08:14:06.000Z","id":"0D54u00009evmwECAQ"} -->
## Zscaler Client Connector and unsecured WLANs

- Source: https://community.zscaler.com/s/question/0D54u00009evmwECAQ/zscaler-client-connector-and-unsecured-wlans
- Type: Q&A
- Posted: 2020-09-23T07:00:45.000Z
- Last activity: 2023-05-31T08:14:06.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello folks,

I was just wondering about usage of open/unsecured WLANs (sigh!) e.g. at public places like Internet Cafes or Airports. Usually a traditional VPN approach here was some kind of “basic assurance? to make things kind of secure.
 Now, as far as I know Ztunnel 1.0 does not encrpyt/encapsulate traffic and therefore general staying away from public unsecured WiFi is a good idea (which is ALWAYS a good idea, I know). But as Dan stated [here](https://community.zscaler.com/t/difference-between-tunnel-and-tunnel-with-local-proxy/5404/4) with Ztunnel 2.0 **all** traffic will be encapsulated in DTLS/TLS. So - beside other security reservations on unsecured WiFis - enforcing Ztunnel 2.0 seems like a no-brainer. Or did I miss anything here?

BR
 Manuel
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwICAQ/zscaler-client-connector-on-vdi","lastmod":"2024-08-29T08:16:41.000Z","id":"0D54u00009evmwICAQ"} -->
## Zscaler client connector on vdi

- Source: https://community.zscaler.com/s/question/0D54u00009evmwICAQ/zscaler-client-connector-on-vdi
- Type: Q&A
- Posted: 2020-09-19T07:38:46.000Z
- Last activity: 2024-08-29T08:16:41.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello
 I’ve installed zscaler client connector on two vdi machines running under the same windows server 2016 computer (same ip address). The second user could not login, if the user with zapp opened closes it, it gets closed for the other user.

Is there any good way to implement zapp for vdi running multiple user under the same computer?

kind regards.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwOCAQ/cursor-blinks-after-enabling-zscaler-with-cisco-anyconnect-vpn-client","lastmod":"2023-05-31T08:14:06.000Z","id":"0D54u00009evmwOCAQ"} -->
## Cursor blinks after enabling Zscaler with Cisco Anyconnect VPN client

- Source: https://community.zscaler.com/s/question/0D54u00009evmwOCAQ/cursor-blinks-after-enabling-zscaler-with-cisco-anyconnect-vpn-client
- Type: Q&A
- Posted: 2020-09-15T10:04:03.000Z
- Last activity: 2023-05-31T08:14:06.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

We’ve a peculiar behaviour with Cisco Anyconnect VPN client & Zscaler Client Connector.

When we enable Cisco VPN & then activate Zscaler Client connector; cursor keeps blinking making it practically impossible to perform any sort of operation on machine.

We’re running Windows 7 with Zscaler client connector version 2.1.2.105.

Could someone possible guide us in correct direction for the reason of this problem…?

Thanks…!!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwPCAQ/chocolatey-package-for-zscaler-cloud-connect","lastmod":"2023-07-06T11:46:22.000Z","id":"0D54u00009evmwPCAQ"} -->
## Chocolatey Package for zScaler Cloud connect

- Source: https://community.zscaler.com/s/question/0D54u00009evmwPCAQ/chocolatey-package-for-zscaler-cloud-connect
- Type: Q&A
- Posted: 2020-09-10T20:03:57.000Z
- Last activity: 2023-07-06T11:46:22.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Just wanted to see if Z-Scaler could manage Chocolatey package for Z-app?

Chocolatey Software
[image]

##### [Packages](https://community.chocolatey.org/packages)

Chocolatey is software management automation for Windows that wraps installers, executables, zips, and scripts into compiled packages. Chocolatey integrates w/SCCM, Puppet, Chef, etc. Chocolatey is trusted by businesses to manage software...

This would significantly help to widely deploy ZApp to endpoints via the Internet.
 Thanks,
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwQCAQ/zs-client-connector-app","lastmod":"2023-05-31T08:14:06.000Z","id":"0D54u00009evmwQCAQ"} -->
## ZS Client Connector (App)

- Source: https://community.zscaler.com/s/question/0D54u00009evmwQCAQ/zs-client-connector-app
- Type: Q&A
- Posted: 2020-09-09T19:42:11.000Z
- Last activity: 2023-05-31T08:14:06.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Recently, my fellow intake team has been receiving calls from end-users that their ZS Client Connector (App) is requesting to renew ZScaler credentials on the endpoint for now reason.

Is there any known issue or conditions that would require ZScaler to re-authenticate to the endpoint?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwUCAQ/ztunnel-20-interception-of-nonhttp-ports-and-logging","lastmod":"2023-07-06T13:10:46.000Z","id":"0D54u00009evmwUCAQ"} -->
## ZTunnel 2.0, Interception of non-HTTP ports, and logging

- Source: https://community.zscaler.com/s/question/0D54u00009evmwUCAQ/ztunnel-20-interception-of-nonhttp-ports-and-logging
- Type: Q&A
- Posted: 2020-09-02T15:32:51.000Z
- Last activity: 2023-07-06T13:10:46.000Z
- Answers: 8
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We migrated to Tunnel 2.0 a few weeks ago. For the most part, it’s worked out really well for us.

Today, I had a user say that his SSH connections (using Putty on Windows) were being routed through ZIA and due to IP restrictions on the remote end, he asked me to bypass it. Honestly, I didn’t think that we were tunneling SSH traffic through ZIA, but my own testing showed that we were.

Ultimately, I found that I could exclude that traffic by adding the IP to the “Destination Exclusion? list in the App Profile. I don’t like this approach, because I have quite a few app profiles, and it makes management a pain to have to add it to all profiles.

Furthermore, I couldn’t find any logs in ZIA or ZPA indicating this traffic was being processed by Zscaler at all!

So I have these questions:

- Is there a more global way to exclude certain traffic from being tunneled?
- What’s the point in tunneling the traffic if there aren’t any logs of the traffic? Am I missing something here?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwWCAQ/total-bytes-sent-showed-on-zapp","lastmod":"2023-05-31T08:49:49.000Z","id":"0D54u00009evmwWCAQ"} -->
## Total Bytes Sent showed on Zapp

- Source: https://community.zscaler.com/s/question/0D54u00009evmwWCAQ/total-bytes-sent-showed-on-zapp
- Type: Q&A
- Posted: 2020-08-26T08:31:53.000Z
- Last activity: 2023-05-31T08:49:49.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi all,

I want to know if I bypass O365 urls from the pac file for Zapp.
 Will the “Total Bytes Sent? on the Zapp still contains the traffic that was bypassed?

Thanks!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwcCAA/forwarding-profiles-for-zappnot-use-the-trusted-network-criteria","lastmod":"2023-05-31T09:08:24.000Z","id":"0D54u00009evmwcCAA"} -->
## Forwarding profiles for ZApp(not use the trusted network criteria)

- Source: https://community.zscaler.com/s/question/0D54u00009evmwcCAA/forwarding-profiles-for-zappnot-use-the-trusted-network-criteria
- Type: Q&A
- Posted: 2020-08-20T05:59:43.000Z
- Last activity: 2023-05-31T09:08:24.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

when I don’t set or use the trusted network criteria ,can I set the forwarding profile actions to [NONE] which in the [on trusted network ] instead of [Tunnel] or [[Tunnel with Local Proxy] or [Enforce]?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwnCAA/is-zapp-zia-tunnel-10-encrypted","lastmod":"2023-05-31T08:49:52.000Z","id":"0D54u00009evmwnCAA"} -->
## Is Zapp (ZIA) tunnel 1.0 encrypted?

- Source: https://community.zscaler.com/s/question/0D54u00009evmwnCAA/is-zapp-zia-tunnel-10-encrypted
- Type: Q&A
- Posted: 2020-08-14T06:49:51.000Z
- Last activity: 2023-05-31T08:49:52.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Friends,

Quick question: Is Zapp (ZIA) tunnel 1.0 encrypted?

Thanks

Adrian
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwqCAA/z-client-connector-ios-installation-with-ms-intune","lastmod":"2023-05-31T08:14:11.000Z","id":"0D54u00009evmwqCAA"} -->
## Z Client Connector - iOS installation with MS Intune

- Source: https://community.zscaler.com/s/question/0D54u00009evmwqCAA/z-client-connector-ios-installation-with-ms-intune
- Type: Q&A
- Posted: 2020-08-12T08:54:55.000Z
- Last activity: 2023-05-31T08:14:11.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I created a document to show how I installed ZCC for iOS from MS [Microsoft Intune Zapp iOS device setup v0.9.docx](https://community.zscaler.com/uploads/short-url/wIBpCXdcLObK2uaF6TOPp2EIYti.docx) (1.7 MB) Intune.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwxCAA/zapp-bw-requirement","lastmod":"2023-07-06T12:54:41.000Z","id":"0D54u00009evmwxCAA"} -->
## ZApp BW requirement

- Source: https://community.zscaler.com/s/question/0D54u00009evmwxCAA/zapp-bw-requirement
- Type: Q&A
- Posted: 2020-08-11T07:06:41.000Z
- Last activity: 2023-07-06T12:54:41.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

Multi times now i’m getting same question from several customers. They asking about how much ZApp BW requirement? or how big ZApp CONNECT added to the each packets/transactions. I couldn’t find such document in online kb/APEX. Although we told customer that it’s negligible but they still pushing back to get the official numbers from Zscaler. Some consider this as crucial part of getting approval from mgmnt committee to deploy Zscaler. Can help?

Regards,
 Zul
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmwzCAA/request-new-ip-from-zapp","lastmod":"2023-05-31T08:49:55.000Z","id":"0D54u00009evmwzCAA"} -->
## Request new IP from zApp

- Source: https://community.zscaler.com/s/question/0D54u00009evmwzCAA/request-new-ip-from-zapp
- Type: Q&A
- Posted: 2020-08-07T15:42:52.000Z
- Last activity: 2023-05-31T08:49:55.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello All,
 we use zApp on our workstations. Our users are assigned a IP address from the San Francisco IV block that appears to be cached on the client (I have had the same IP address for one week now).

The problem is that at some of the zScaler IPs assigned are black-listed as SPAM / BOT suspicious.

We use Cloudflare to protect our company site. Our users are directed to the company site for business several times per day. For the users assigned the suspicious IPs they are challenged by a Captcha the first time they visit the site each day, sometimes several times per day.

Is there any way to have the zApp get/request a new IP address assigned so that the users are not stuck with the suspicious IP address?

Thank you,
 Tomas
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmx0CAA/how-to-get-new-ip-from-zapp","lastmod":"2023-05-31T08:49:55.000Z","id":"0D54u00009evmx0CAA"} -->
## How to get new IP from zApp

- Source: https://community.zscaler.com/s/question/0D54u00009evmx0CAA/how-to-get-new-ip-from-zapp
- Type: Q&A
- Posted: 2020-08-07T15:29:24.000Z
- Last activity: 2023-05-31T08:49:55.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello All,
 (my other post got blocked by Askimet so I will try to re-phrase my question)

Our users are assigned an IP address from the San Francisco IV block by zApp.

This IP is persistent (I’ve had the same IP for over one week now). The problem: one of the IPs assigned to our users is black-flagged due to it being used by a SPAM / BOT.

We use Cloudflare service to protect our website. Every one of our users with the suspicious IP visits our site, they are challenged by a Captcha because Cloudflare considers traffic from this IP “suspicous?. Since the IP is cached for my users, they see the Captcha every time they visit our website. Our business requires them to visit several times a day. So they are challenged several times a day.

Q: Is there a way to get the zApp to assign a new IP to my users (so they are not forced to continue using the problematic IP)?

Thank you,
 -tomas
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmx8CAA/saml-response-refresh-button-to-be-created-on-zapp","lastmod":"2023-05-31T08:14:11.000Z","id":"0D54u00009evmx8CAA"} -->
## SAML response refresh button to be created on Zapp

- Source: https://community.zscaler.com/s/question/0D54u00009evmx8CAA/saml-response-refresh-button-to-be-created-on-zapp
- Type: Q&A
- Posted: 2020-07-31T20:33:57.000Z
- Last activity: 2023-05-31T08:14:11.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Zscaler,

We should have a SAML refresh button just like other services on Zapp. Because in general case, user should logout and login to send fresh/updated SAML response.

Instead if possible, if you we can build a button where SAML response can be refreshed, it would be helpful.

I know this is dependent on Authentication but for Domain joined machines, we are not completing Authentication in all steps instead it is using System credentials. So using this system credentials, we should have some SAML refresh by doing a silent Re-auth.

Not sure if this is a good idea. But just posting…
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmxCCAQ/manually-override-datacentre-to-connect-to-from-zapp","lastmod":"2023-07-06T13:10:25.000Z","id":"0D54u00009evmxCCAQ"} -->
## Manually override Datacentre to connect to from Zapp

- Source: https://community.zscaler.com/s/question/0D54u00009evmxCCAQ/manually-override-datacentre-to-connect-to-from-zapp
- Type: Q&A
- Posted: 2020-07-28T05:26:18.000Z
- Last activity: 2023-07-06T13:10:25.000Z
- Answers: 6
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

I have raised an enhancement request with my TAM to be able to manually override which datacentre Zapp connects to in the event that users experience issues with the Datacentre used for GEOIP, get an unsuitable DC via GEOIP or just want to test the usage against multiple DCs. Similar to how you select a DC to connect to in NordVPN.

I think it would be a really handy feature so everyone vote it up

Regards,
 Mark Fellows
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmxHCAQ/bios-update-in-dell-laptop-break-zapp-connection","lastmod":"2023-05-31T09:08:45.000Z","id":"0D54u00009evmxHCAQ"} -->
## BIOS Update in DELL Laptop break Zapp Connection

- Source: https://community.zscaler.com/s/question/0D54u00009evmxHCAQ/bios-update-in-dell-laptop-break-zapp-connection
- Type: Q&A
- Posted: 2020-07-24T05:27:56.000Z
- Last activity: 2023-05-31T09:08:45.000Z
- Answers: 2
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We installed BIOS update in some DELL laptop last week. After that Zscaler app struck with login loop. Zapp authenticates, login and logout out next moment. We raised TAC case and working with Zscaler engineering team. Does anyone experienced similar issue recently ?

Regards
 Ganesh Krishnan
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmxJCAQ/zapp-21244-mac-os-x-loopback-apache-maven-npm-xcode","lastmod":"2024-10-19T05:07:50.000Z","id":"0D54u00009evmxJCAQ"} -->
## ZApp 2.1.2.44 Mac OS X loopback - Apache Maven, npm, Xcode

- Source: https://community.zscaler.com/s/question/0D54u00009evmxJCAQ/zapp-21244-mac-os-x-loopback-apache-maven-npm-xcode
- Type: Q&A
- Posted: 2020-07-22T02:56:02.000Z
- Last activity: 2024-10-19T05:07:50.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Currently having issues with some developers that code locally using Mac OS X, using local package builders like apache maven, npm or XCode runs into issues

Apache maven settings.xml wont apply proxy settings, ZApp doesnt seem to intercept and pass this on.
 npm gets local port 9000 is already in use, however we’ve set our environment for different port, I have verified the user is getting the correct tunnel mode w/ local proxy port in checking local environment.

XCode gets MITM attack, importing certificate works, we’ve come up with a script to auto import Zscaler Root CA since there are 50+ simulators, however Live Preview or SwiftUI updates, there is limitations to running commands on this.

If anyone is having issues or things for me to check, please advise

Thanks in advance!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmxKCAQ/zapp-forwarding-profile-to-local-onpremise-private-proxy","lastmod":"2023-05-31T09:08:13.000Z","id":"0D54u00009evmxKCAQ"} -->
## Zapp forwarding profile to local OnPremise private proxy

- Source: https://community.zscaler.com/s/question/0D54u00009evmxKCAQ/zapp-forwarding-profile-to-local-onpremise-private-proxy
- Type: Q&A
- Posted: 2020-07-21T13:31:24.000Z
- Last activity: 2023-05-31T09:08:13.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Guru,

I read below community discussions.

> difference-between-tunnel-and-tunnel-with-local-proxy/5404
 difference-in-use-of-app-profile-pac-file-and-forwarding-profile-pac-file/6847
>

and below docs as well.

> best-practices-using-pac-files-zscaler-app
 configuring-forwarding-profiles-zscaler-app#forwarding-profile-action-zia
>

but still confusing what should do.

BACKGROUND.
 We use ZIA for office users. All good.Now we are implementing ZPA and Zapp
 Our internal domains (applications) can reachable via ZPA application segments + access policy. Our public domains also reachable as we bypass those not to come ZPA.
 So far so good. We are fine Zapp + ZPA until we start Zapp + ZIA forwarding profile.

We have to implement Zapp ZIA for our Road Warrior because we want them to use ZIA even they are outside office (Off-Trusted). We also have our partner proxy which is written in PAC file with private IP address and proxy port. Those are able to use for our internal On-trusted users.
 for example,
 if (
 shExpMatch(url, “[https://abc.def.hijk.com:8443/*](https://abc.def.hijk.com:8443/*)?)
 ) {
 return “PROXY 10.1.1.1:8080?;
 }
 That 10.1.1.1 is partner proxy we cannot touch. And it is private IP that connect with our internal routing. And those FQDN “[abc.def.hijk.com](http://abc.def.hijk.com)? cannot nslookup even from our internal On-trusted clients but we can access those as of PAC file.We have 3 to 4 partners with similar PAC configuration, different proxies and ports.

QUESTION
 Which mode should I use in forwarding profile (TWLP or Enforce Proxy?) because we need to use existing PAC file with above partner proxy? Requirement is Zapp Off-trusted Road warrior should able to reach all those partner URLs. Note : ZPA connector cannot resolve those FQDN to IP. CentOS ZPA connector can configure proxy but not PAC files.

We are also discussing at case #02483308 & remote assistance is available.

Kindly suggest me.

Regards
 Minn
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmxLCAQ/bandwidth-usage-for-zapp","lastmod":"2023-05-31T08:14:11.000Z","id":"0D54u00009evmxLCAQ"} -->
## Bandwidth usage for ZApp

- Source: https://community.zscaler.com/s/question/0D54u00009evmxLCAQ/bandwidth-usage-for-zapp
- Type: Q&A
- Posted: 2020-07-21T10:57:02.000Z
- Last activity: 2023-05-31T08:14:11.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,
 May I have the bandwidth requirement for Zscaler health check, update PAC, policy and ZApp update?
 Thanks for help.
 Jacky
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmxMCAQ/u2f-support-for-authentication-in-zscaler-app","lastmod":"2023-07-06T11:46:31.000Z","id":"0D54u00009evmxMCAQ"} -->
## U2F Support for authentication in zscaler app

- Source: https://community.zscaler.com/s/question/0D54u00009evmxMCAQ/u2f-support-for-authentication-in-zscaler-app
- Type: Q&A
- Posted: 2020-07-19T07:39:57.000Z
- Last activity: 2023-07-06T11:46:31.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi everyone,

Is there any way to allow U2F support in the zscaler app for the sake of 2FA in Okta?

[[u2f]u2f768×601 55.2 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O3q6AAC)

Thanks!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmxNCAQ/windows-zscaler-adapter-for-ztunnel-20","lastmod":"2024-06-10T15:49:19.000Z","id":"0D54u00009evmxNCAQ"} -->
## Windows Zscaler Adapter for Z-tunnel 2.0?

- Source: https://community.zscaler.com/s/question/0D54u00009evmxNCAQ/windows-zscaler-adapter-for-ztunnel-20
- Type: Q&A
- Posted: 2020-07-19T01:47:40.000Z
- Last activity: 2024-06-10T15:49:19.000Z
- Answers: 2
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello, Community

Understanding Z-tunnel 2.0 is hard hurdle for everyone, at least for me.
 Hence I repeatedly read and read kbs.
 On the all way down to the description, there is one point I cannot understand.
 Here is the ones.

**MTU for Zscaler Adapter** : (Optional) This option is only applicable if you’re using Z App version 2.1.2 or later. Zscaler recommends only configuring this setting if you experience IP fragmentation when using Z-Tunnel 2.0 with the default value of 0. This setting allows you to decrease MTU to avoid IP fragmentation. To configure this setting, enter any value from 68 to 1500.
 [https://help.zscaler.com/z-app/configuring-forwarding-profiles-zscaler-app](https://help.zscaler.com/z-app/configuring-forwarding-profiles-zscaler-app)

I can understand the issue with IP fragmentation, it is general solution.
 The problem is “Zscaler Adapter?.
 I have assumed Windows Zscaler Adapter is only for Route Based, not LWF scenario.

What deepens my confusion is following description.

For the Windows version of Z App, Z-Tunnel 2.0 (in DTLS mode) changes the MSS for the TCP stream based on the configured MTU value, because it uses the Windows filter driver instead of the Zscaler adapter.
 [https://help.zscaler.com/z-app/configuring-forwarding-profiles-zscaler-app](https://help.zscaler.com/z-app/configuring-forwarding-profiles-zscaler-app)

My confusion reached the bottom of my brain and found a dearth of my personal IT knowledge.
 Z-tunnel 2.0 uses or not uses Zscaler Adapter? DTLS only? Does it mean non-DTLS 2.0 tunnel will fall back on non-LWF driver?

Kindly someone help let me understand what these description means.

Best Regards,
 Yosh
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmxRCAQ/issues-with-zapp-versions-21x","lastmod":"2023-05-31T08:50:06.000Z","id":"0D54u00009evmxRCAQ"} -->
## Issues with ZApp versions 2.1.x

- Source: https://community.zscaler.com/s/question/0D54u00009evmxRCAQ/issues-with-zapp-versions-21x
- Type: Q&A
- Posted: 2020-07-14T10:37:17.000Z
- Last activity: 2023-05-31T08:50:06.000Z
- Answers: 9
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

In my company we have been using the Zscaler App version 1.5.2.7 for a long time and never had any notable issues.
 We recently decided to update to the latest Version which is 2.1.2.105. Since then we are experiencing major issues from Applications freezing to constant disconnects from Remote-Sessions and VPN’s.
 Can anyone else confirm these problems? Or is this caused by the ZTunnel 1.0 to 2.0 change? Our Forwarding Profile is set to Packet Filter Based - Tunnel and we have not changed anything since then.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmxlCAA/zpa-authentication-through-client-connector","lastmod":"2023-07-06T11:46:39.000Z","id":"0D54u00009evmxlCAA"} -->
## ZPA Authentication through Client Connector

- Source: https://community.zscaler.com/s/question/0D54u00009evmxlCAA/zpa-authentication-through-client-connector
- Type: Q&A
- Posted: 2020-07-01T18:38:29.000Z
- Last activity: 2023-07-06T11:46:39.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

End-users allowing their ZPA session to timeout and receives the message to re-authenticate prior to using services. Clicking the re-authenticate button ends up with a white screen as such:
 [Screen Shot 2020-07-01 at 2.28.08 PM]

It never makes it to the IdP screen and requires an exit of the Client Connector and relaunch to authenticate into services. Has anyone experienced this issue?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmxmCAA/zscaler-app-password-protecting-for-exit-zapp","lastmod":"2023-07-06T13:04:50.000Z","id":"0D54u00009evmxmCAA"} -->
## Zscaler App - Password protecting for "Exit" ZAPP

- Source: https://community.zscaler.com/s/question/0D54u00009evmxmCAA/zscaler-app-password-protecting-for-exit-zapp
- Type: Q&A
- Posted: 2020-06-30T17:59:18.000Z
- Last activity: 2023-07-06T13:04:50.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We have an issue where our clients are able to “Exit? the ZAPP from both MacOS and Win10. Is there a way that we can password protect when they try to “Exit? ZAPP?

I would have assume that exiting ZAPP is like “Logout?, but instead the same password that allow the ability to “Turn-Off? ZIA is the same password that control “Exit? ZAPP. We have a need to allow our users the ability to turn-off and turn-on ZIA due to ZIA issue flapping between Tunnel-2 reverting to backTunnel-1. We have a timer set that will enable ZIA back after 15 minutes. But “Exit? the app, will both exit ZIA and ZPA and it does not re-enable ZAPP back again. You have to manually open ZAPP and login again. This is a big security risk as we have seen users will not log back in to ZAPP.

[Screen Shot 2020-06-30 at 10.48.35 AM]
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmxoCAA/how-zapp-forwards-non-http-and-https-traffic-to-zen","lastmod":"2023-05-31T08:50:12.000Z","id":"0D54u00009evmxoCAA"} -->
## How Z-App forwards non http and https traffic to Zen

- Source: https://community.zscaler.com/s/question/0D54u00009evmxoCAA/how-zapp-forwards-non-http-and-https-traffic-to-zen
- Type: Q&A
- Posted: 2020-06-29T21:57:43.000Z
- Last activity: 2023-05-31T08:50:12.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

I am trying to understand the functionality of traffic forwarding via Z-App.
 How the non http and https traffic are processed (Application not using web based traffic).
 How the Ztunnel working with Z-App is it similar to IPsec or GRE tunnel?
 What happens when Microsoft related traffic with packet of higher size than MTU value is floated.
 Does it gets dropped or this passes without any issue as Microsoft recommends do not fragment bit set.

Thanks.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmxrCAA/posture-domain-check-how-does-it-work","lastmod":"2023-05-31T09:28:02.000Z","id":"0D54u00009evmxrCAA"} -->
## Posture Domain Check - How does it Work

- Source: https://community.zscaler.com/s/question/0D54u00009evmxrCAA/posture-domain-check-how-does-it-work
- Type: Q&A
- Posted: 2020-06-23T11:44:11.000Z
- Last activity: 2023-05-31T09:28:02.000Z
- Answers: 1
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

We noticed that some devices fail the Domain Joined Check by Zapp when connecting to a ZPA product. Even if the device is Joined to the domain, it fails sometimes. A /gpupdate /force resolves the issue.

Want to know, Technically, how the Zapp checks if a Machine is joined to the domain and compares it with the value in Posture Profile “Domain Joined?.

Thanks
 Omar
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmxvCAA/does-rtt-value-between-zapp-and-zen-node-will-be-displayed-in-zsa-tunnel-logs","lastmod":"2023-05-31T08:14:16.000Z","id":"0D54u00009evmxvCAA"} -->
## Does RTT value between Zapp and ZEN node will be displayed in ZSA Tunnel logs?

- Source: https://community.zscaler.com/s/question/0D54u00009evmxvCAA/does-rtt-value-between-zapp-and-zen-node-will-be-displayed-in-zsa-tunnel-logs
- Type: Q&A
- Posted: 2020-06-18T17:39:17.000Z
- Last activity: 2023-05-31T08:14:16.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Does RTT value between app and ZEN node will be displayed in ZSA Tunnel logs??
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmxwCAA/zapp-mobile-portal-idp-a-hidden-gem-in-you-tool-kit","lastmod":"2023-05-31T09:08:27.000Z","id":"0D54u00009evmxwCAA"} -->
## Z-App Mobile Portal IdP - A Hidden Gem in you Tool Kit

- Source: https://community.zscaler.com/s/question/0D54u00009evmxwCAA/zapp-mobile-portal-idp-a-hidden-gem-in-you-tool-kit
- Type: Q&A
- Posted: 2020-06-18T15:33:48.000Z
- Last activity: 2023-05-31T09:08:27.000Z
- Answers: 6
- Likes: 4
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Guys,

Just a quick note. If you are unfamiliar with using the Z-App Portal IdP it can be a very useful tool. It has saved me in 2 accounts and potentially 21k of ZIA seats in the last 2 months. With the release of 6.0 and multiple IdP support, it has made the IdP even more powerful because now you can use it for a subset of users in an organization.

What is it? It is our own built in IdP, but instead of creating credential for each user, you use a device token that is loaded with Z-App installation switches. Z-App will then automatically authenticate when the device enrolls to the Z-App portal. It is completely transparent to the users.

If you have accounts that talk about no IdP availability or absolutely no prompts for credentials, or my users are not technical enough to authenticate (No Kidding). This may help. It is probably the easiest deployment we have available.

The two circumstances I recently used it for were, One of my customers purchased an 8k person company, they would not deploy ZIA to that group until they were all entered into their Okta instance. Lots of going back and forth with solutions, but with the Z-IdP they were easily able to deploy ZIA and get around that limitation.

Customer 2, bought 14000 seats of ZIA for their Android devices (from a former SE), but they couldn’t get the users to authenticate and use ZIA. With Intune, Android Enterprise and Z-IdP, they now have one button to push and they are protected. Once Z-App brought up it is persistent as well. We are looking at an installation script to launch it automatically, but the customer now has a useable solution and is moving forward once again.

I just wanted to pass on some recent tribal knowledge, and encourage you to take a look.

There are a some limitations, because you do not have groups, or potentially users to assign policies to. I typically tell the customer this will use the default policy and it has not been an issue to this point.

Thank you and best regards,

-Todd Harcourt-

Z-App Portal IdP

[https://help.zscaler.com/z-app/using-zscaler-app-portal-identity-provider](https://help.zscaler.com/z-app/using-zscaler-app-portal-identity-provider)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmxxCAA/ios-zscaler-app-trigger-reauthentication","lastmod":"2023-05-31T08:14:16.000Z","id":"0D54u00009evmxxCAA"} -->
## iOS Zscaler App - trigger re-authentication

- Source: https://community.zscaler.com/s/question/0D54u00009evmxxCAA/ios-zscaler-app-trigger-reauthentication
- Type: Q&A
- Posted: 2020-06-17T05:34:15.000Z
- Last activity: 2023-05-31T08:14:16.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I’m working on an app, that requires Zscaler tunneling in-house, to reach a data path.
 When I install my app on my device through a cable (first time use) Zscaler is triggered, I re-authenticate and all is good.

(let’s just make it clear that the app i registered in the interface and all that…it works)

However now I send it out to a bunch of testers, and none of them worked. Seemed like Zscaler was not triggered to re-authenticate.
 Yesterday I looked at the testers phone, and all settings were correct, my app was supervised by Zscaler and when opening the app the little VPN logo showed up in the status bar.

however no connection - whne I installed same version of app through cable, when using the app first time run Zscaler was triggered.

So I am wondering if there is app end-point I can trigger in my code, i.e send the user to Zscaler app with the intent of re-authenticate .
 com.zscaler.app:reauth
 or something like it ? I can’t seem to google any documentation on the iOS app.

Hope you can help.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmxyCAA/zscaler-app-mfa-support-okta","lastmod":"2026-01-06T23:23:01.000Z","id":"0D54u00009evmxyCAA"} -->
## Zscaler App MFA Support - Okta

- Source: https://community.zscaler.com/s/question/0D54u00009evmxyCAA/zscaler-app-mfa-support-okta
- Type: Q&A
- Posted: 2020-06-16T18:58:59.000Z
- Last activity: 2026-01-06T23:23:01.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I came across this community post as I am currently trying to get MFA setup via Okta for use in the Zscaler App. [Can ZPA support two factor authentication?](https://community.zscaler.com/t/can-zpa-support-two-factor-authentication/1197/6)

I have a ticket open on this, but I’m not entirely sure if the resolution in that post is accurate. According to support, the browser that’s built into the Zscaler App is actually IE 11. This browser is not supported on many Okta MFA factors. That post claims that basically any IDP MFA will work. I’m not so sure that is true since I see browser incompatibility errors when we try various MFA factors in Okta. Although the Okta Verify app seems to work just fine in the Zscaler App.

Does anyone have any idea what Okta MFA factors are supported within the Zscaler App?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmy0CAA/zapp-win-10-2004-update-outlook-connectivity","lastmod":"2023-05-31T08:14:16.000Z","id":"0D54u00009evmy0CAA"} -->
## Zapp & Win 10 2004 Update & Outlook Connectivity

- Source: https://community.zscaler.com/s/question/0D54u00009evmy0CAA/zapp-win-10-2004-update-outlook-connectivity
- Type: Q&A
- Posted: 2020-06-16T09:03:31.000Z
- Last activity: 2023-05-31T08:14:16.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Morning All,

Here we go again, since the release of win 10 2004, this seems to have brought back the old problem of windows thinking there is no internet access, which then has a knock on affect of outlook not connecting, everything else resolves and works fine, we have found that turning off the Windows internet probing to the msftconnect site works on some machines, but on others we have to roll back to the zscaler scripting file to get it to work.
 Anybody else experiencing this, any have any other workarounds.

Cheers

Gaz
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmy8CAA/zapp-with-citrix-vpn-ft","lastmod":"2023-07-06T13:12:13.000Z","id":"0D54u00009evmy8CAA"} -->
## ZAPP with Citrix VPN FT

- Source: https://community.zscaler.com/s/question/0D54u00009evmy8CAA/zapp-with-citrix-vpn-ft
- Type: Q&A
- Posted: 2020-06-11T00:40:23.000Z
- Last activity: 2023-07-06T13:12:13.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Guys

We are new customer of Zscaler and currently planning to roll out Zscaler in our company.

We have some challenges in Trusted Network Criteria check.

We are using Citrix NetScaler as a VPN. I have already gone through articles mentioning ZAPP does not detect VPN in case of Citrix until we use FULL TUNNEL. In our case, we are using FULL Tunnel mode.

For network criteria check, we are using two conditions 1. DNS 2. Name Lookup. ‘Any’ condition would trigger trusted network change.

I know lot of articles mention that using name lookup caused issue in detecting a network change as it’s a dynamic property.

Network Criteria works fine 1. when we are connected to office network (On-Net) 2. When we are connected to home network (Off-Net) but having issue when user connects to Citrix VPN (Full tunnel).

ZAPP detects Trusted network change intermittently. At times, it works fine but sometime it wont work. When it does not work, it breaks all the traffic flow as we are using TWLP on VPN and TM V2.0 on Off-Network.

Reason we are using name lookup in trusted network criteria is because the way DNS works in NetScaler, when user connects to VPN it pushes virtual/dummy IP as a DNS server and when client does a lookup, NetScaler does DNS forwarding/Proxy for all the DNS request. In short, we cant use ‘DNS’ check only when we are on VPN so we had to use ‘Name Lookup’ as well.

Can any one help us to troubleshoot the issue why on VPN ZAPP having issue in detecting network change? How can we solve this?

Also, below discussion is more than a year old where user requested a ER to add Citrix in VPN adaptor list. Can I get an update on it?

Zapp with Citrix VPN
Client Connector
> Hi Team, One of our customer using Cirtix VPN client. When they connect the VPN client and ZAPP, the ZAPP is not recognizing the network as VPN trusted network. Is there any criteria / keyword required to be available in the VPN adapter ? Share your experience. and suggestions. Regards / Ramesh M
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmyCCAQ/directaccess-client-with-zscaler-app","lastmod":"2023-05-31T08:14:16.000Z","id":"0D54u00009evmyCCAQ"} -->
## DirectAccess client with Zscaler APP

- Source: https://community.zscaler.com/s/question/0D54u00009evmyCCAQ/directaccess-client-with-zscaler-app
- Type: Q&A
- Posted: 2020-06-05T11:36:24.000Z
- Last activity: 2023-05-31T08:14:16.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

(topic withdrawn by author, will be automatically deleted in 24 hours unless flagged)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmyDCAQ/zscaler-app-requires-restart","lastmod":"2023-05-31T08:14:16.000Z","id":"0D54u00009evmyDCAQ"} -->
## Zscaler App requires restart

- Source: https://community.zscaler.com/s/question/0D54u00009evmyDCAQ/zscaler-app-requires-restart
- Type: Q&A
- Posted: 2020-06-05T09:56:51.000Z
- Last activity: 2023-05-31T08:14:16.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

Facing issue with some machines where outlook, internet or VPN stops working. In that case, it is identified that Zsacler Application needs to be double-clicked & ten everything starts working fine.

This App is already part of Startup application & works fine on most of the machines. However, it is seen sometimes that when a machine is restarted, issue occurs & the only solution to that is double-click the Zscaler app.

With VPN, it is seen that server connection entry is deleted & that enrty needs to be entered.

This is being faced at one of my customer location & is one of the top-call driver which customer wants to eliminate.

Please help in resolving this issue.

Regards,
 Shashank
 +91-9717050042
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmyECAQ/route-based-recommended-sceanrio","lastmod":"2023-05-31T08:50:18.000Z","id":"0D54u00009evmyECAQ"} -->
## Route Based Recommended Sceanrio?

- Source: https://community.zscaler.com/s/question/0D54u00009evmyECAQ/route-based-recommended-sceanrio
- Type: Q&A
- Posted: 2020-06-04T11:35:25.000Z
- Last activity: 2023-05-31T08:50:18.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

I have a little question about Driver Types of Zscaler App.

All of Zscaler kb says “Packet Filter Based? is recommended.
 One of these even says ,Quote,“Previously, when in Tunnel mode or when using ZPA, Z App created a new virtual network adapter on Windows platforms.?
 [https://help.zscaler.com/z-app/using-windows-filter-driver-zscaler-app](https://help.zscaler.com/z-app/using-windows-filter-driver-zscaler-app)
 It is as if this is past thing.

Does Zscaler see “Route Based? as out-of-date tool?
 “Route Based? is Don Quijote of Zscaler service?
 There are no scenario to recommend “Route Based??

Regards,
 Yosh
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmyGCAQ/zscaler-app-g-suite","lastmod":"2023-05-31T08:50:18.000Z","id":"0D54u00009evmyGCAQ"} -->
## Zscaler App - G suite

- Source: https://community.zscaler.com/s/question/0D54u00009evmyGCAQ/zscaler-app-g-suite
- Type: Q&A
- Posted: 2020-06-03T05:41:09.000Z
- Last activity: 2023-05-31T08:50:18.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

Have anyone tries integrating G suite with Zscaler as IdP (SAML)? And do you guys met with this issue when logging into Zapp, after entering username and password, when prompt for security keys this messages appears “This browser does not support security key?. Because of this, users unable to proceed on as they cannot use security key to verify themselves and login to Zapp successfully.

Regards,
 Matthews Loke
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmyJCAQ/zapp-21292-and-windows-10-2004","lastmod":"2023-07-06T13:12:19.000Z","id":"0D54u00009evmyJCAQ"} -->
## ZAPP 2.1.2.92 and Windows 10 2004

- Source: https://community.zscaler.com/s/question/0D54u00009evmyJCAQ/zapp-21292-and-windows-10-2004
- Type: Q&A
- Posted: 2020-05-28T14:52:52.000Z
- Last activity: 2023-07-06T13:12:19.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello!

I just upgraded two computers to Windows 10 2004 May update to test before releasing to users and got a problem with ZAPP 2.1.2.92.

When the service status is ON, all DNS from the machine stops working.
 The tunnel access works, as well other network services, if you try to access them by the IP address.

Is there a new version of the ZAPP or anything we can do to make it work?

Thanks.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmyNCAQ/zapp-use-vzen-to-forward-tunnel-20-traffic","lastmod":"2023-07-06T13:12:22.000Z","id":"0D54u00009evmyNCAQ"} -->
## ZAPP use VZEN to forward tunnel 2.0 traffic

- Source: https://community.zscaler.com/s/question/0D54u00009evmyNCAQ/zapp-use-vzen-to-forward-tunnel-20-traffic
- Type: Q&A
- Posted: 2020-05-27T15:49:30.000Z
- Last activity: 2023-07-06T13:12:22.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I have a question for VZEN, does it support tunnel 2.0 traffic?
 we want to use ZAPP to forward traffic via tunnel 2.0 to VZEN.
 Can i do it in this way?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmySCAQ/zapp-fetches-internal-domains-to-zia","lastmod":"2023-07-06T13:12:27.000Z","id":"0D54u00009evmySCAQ"} -->
## Zapp fetches internal domains to ZIA

- Source: https://community.zscaler.com/s/question/0D54u00009evmySCAQ/zapp-fetches-internal-domains-to-zia
- Type: Q&A
- Posted: 2020-05-21T16:13:37.000Z
- Last activity: 2023-07-06T13:12:27.000Z
- Answers: 10
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello, community.

Sometimes Z-app catches internal domains and brings them to ZIA gateway and tries to resolve them without fruits.
 PAC file is based on recommended one, seems to cover private up ranges.
 Does someone have similar issue?
 Zapp abduction is too inconvenient.

Best Regards,
 Muhammad
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmyTCAQ/zapp-tunnel-v20-support-for-ios","lastmod":"2023-05-31T08:14:16.000Z","id":"0D54u00009evmyTCAQ"} -->
## ZAPP Tunnel v2.0 Support for iOS

- Source: https://community.zscaler.com/s/question/0D54u00009evmyTCAQ/zapp-tunnel-v20-support-for-ios
- Type: Q&A
- Posted: 2020-05-21T10:28:01.000Z
- Last activity: 2023-05-31T08:14:16.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

When will ZAPP (ZIA) Tunnel v2.0 be supported for iOS? Application like Workday required all ports/protocol to work and Tunnel v1.0 is breaking the app.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmyZCAQ/is-it-a-way-to-setup-zapp-user-configuration-on-a-windows-roaming-profile","lastmod":"2023-07-06T13:12:24.000Z","id":"0D54u00009evmyZCAQ"} -->
## Is it a way to setup ZApp user configuration on a Windows roaming profile?

- Source: https://community.zscaler.com/s/question/0D54u00009evmyZCAQ/is-it-a-way-to-setup-zapp-user-configuration-on-a-windows-roaming-profile
- Type: Q&A
- Posted: 2020-05-19T20:52:59.000Z
- Last activity: 2023-07-06T13:12:24.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi folks,
 I’d like to deploy ZApp in our infrastructure where our users have roaming profiles. They constantly change of computer depending on their shift.
 As we have quite a lot of users and computers, is it a way to save the ZApp user ID and password configuration in the Windows Roaming user profile?

The goal is to avoid the user to each single time having to logon on ZApp as they will probably not do it and just leave the window aside.

Any suggestions/recommendations/ideas?

Looking forward to hearing from you.
 Dave
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmybCAA/zapp-configuration-best-practice-to-use-with-exchange-online","lastmod":"2023-07-06T13:12:31.000Z","id":"0D54u00009evmybCAA"} -->
## Zapp configuration best practice to use with Exchange Online

- Source: https://community.zscaler.com/s/question/0D54u00009evmybCAA/zapp-configuration-best-practice-to-use-with-exchange-online
- Type: Q&A
- Posted: 2020-05-13T09:26:43.000Z
- Last activity: 2023-07-06T13:12:31.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi folks,

hope you’re doing great. We recently started the trial of ZApp tool and I’m facing some recurrent connections issues while on remote (out of office) with EXchange Online.

I get disconnected from Online Exchange server (Connected through Outlook 2019) and I can’t reach it anymore until I disconnect the ZApp tool.

It’s not very convenient.
 I’ve read some documentation and asked our ZScaler vendor for assistance but they seem to struggle to find a solution.

Our setup is based on Kerberos auth with SSL inspection.

So far, we have implemented the following:
 Advanced Settings => Authentication Exemptions : [roaming.officeapps.live.com](http://roaming.officeapps.live.com)
 SSL inspection => exemption hosts : Autodiscover.*ourdomain*.com & autodiscover.*ourdomain*.onmicrosoft.com
 URL & Cloud App Control => Enabled Microsoft-Recommended One-Click 365 Configuration => Actived

on ZApp config
 Forwarding Profile => Custom
 Trusted Network Criteria => our internal DNS are set
 Windows Driver selection ?> Packet Filter Based
 Forwarding Profile for ZIA =>
 On trusted network => Enforce Proxy (System proxy settings)
 VPN Trusted Network => Tunnel with Local Proxy (System proxy settings)
 Off Trusted Network => Tunnel with Local Proxy (System proxy settings) => defined pac file

on ZApp Policy, it’s just a different pac file than Off Trusted Network to allow to connect to our infrastructure via VPN Client.

Any thoughts?
 Looking forward to hearing from you.
 Dave
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmycCAA/certificatebased-authentication-with-zapp-on-mac-os","lastmod":"2023-05-31T08:14:20.000Z","id":"0D54u00009evmycCAA"} -->
## Certificate-based authentication with Zapp on Mac OS

- Source: https://community.zscaler.com/s/question/0D54u00009evmycCAA/certificatebased-authentication-with-zapp-on-mac-os
- Type: Q&A
- Posted: 2020-05-11T14:04:57.000Z
- Last activity: 2023-05-31T08:14:20.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi, I am struggling to get certificate-based authentication working with Zapp (Version 2.1.2.38 but also prior with 2.1.0.190 and before) on Mac OS Catalina. Has anyone similar issues and an idea how to fix? All browser and non browser based apps are working fine with SAML auth but Zapp.

Thanks,
 Bernd
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmyfCAA/network-connection-failed-8","lastmod":"2023-05-31T08:50:18.000Z","id":"0D54u00009evmyfCAA"} -->
## Network Connection Failed [-8]

- Source: https://community.zscaler.com/s/question/0D54u00009evmyfCAA/network-connection-failed-8
- Type: Q&A
- Posted: 2020-05-07T07:16:55.000Z
- Last activity: 2023-05-31T08:50:18.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

Need some help and clarifications here. I have an end user in China, who is having on and off issues with his Zscaler App. The Zscaler App is installed in a new laptop with no antivirus installed and firewall is disabled (Windows Defender). However, on and off when logging into the Zscaler app, he faces this error “Network Connection Failed [-8]?. For example, when first or subsequent login might face this error. But after waiting for a while it is okay, can login into the app. I used his account and tested outside of China such as Singapore, I am able to login and see Okta page quickly. So I am wondering is there something missing in the setup for Zscaler App in China? Would appreciate help from Zscaler engineers working in China.

Regards,
 Matthews Loke
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmygCAA/zscaler-app-212-and-netgear-nighthawk-r7000-challenge","lastmod":"2023-05-31T08:50:30.000Z","id":"0D54u00009evmygCAA"} -->
## Zscaler App 2.1.2 and Netgear Nighthawk R7000 challenge

- Source: https://community.zscaler.com/s/question/0D54u00009evmygCAA/zscaler-app-212-and-netgear-nighthawk-r7000-challenge
- Type: Q&A
- Posted: 2020-04-29T14:16:55.000Z
- Last activity: 2023-05-31T08:50:30.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi there!

Have an odd issue with using Zscaler App 2.1.2.71 DTLS 2.0 across a expensive Netgear Nighthawk R7000 DSL router. A client computer connected to this router via WIFI and wired Ethernet can’t access Internet via Zscaler Internet Access service. Zscaler App show connected, but it’s not working. Nothing is accessible besides the destinations that we bypass in Forwarding Profile.

We use Windows Filter Driver, without proxy settings, and with reduced MTU in forwarding profile so TCP MSS is reasonable safe. We have 20000+ computers that work normally…

As soon as replace the R7000 with another old router, then it works OK, and it’s also working OK when computer is connected via WIFI to mobile phone 4G Internet sharing.

Every time we put the R7000 back in line, then it fails. R7000 run latest firmware.

Do you have any ideas on what is could be causing this…?

Thanks,
 /Jesper
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmyhCAA/chromebook-updates-on-zapp","lastmod":"2025-09-03T12:05:47.000Z","id":"0D54u00009evmyhCAA"} -->
## Chromebook updates on z-app

- Source: https://community.zscaler.com/s/question/0D54u00009evmyhCAA/chromebook-updates-on-zapp
- Type: Q&A
- Posted: 2020-04-28T10:05:32.000Z
- Last activity: 2025-09-03T12:05:47.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I have been looking into an issue that has been reported where chromebooks cannot update unless they log out of Z-App. I have setup fiddler and reviewed all the URL"s in use and ensure they are setup for No-AUth and NO-SSL inspection thinking that could be the cause. I am now wondering is it possible the chrome OS updates cannot make use of the Z-App tunnels possibly since Z-app is an android app. ANyone else having this issue on chromebooks?

Case Number : 02510362
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmyiCAA/zapp-tunnel-mode-ztunnel-20-domain-bypass","lastmod":"2023-05-31T08:50:15.000Z","id":"0D54u00009evmyiCAA"} -->
## Zapp Tunnel Mode Z-tunnel 2.0 Domain Bypass

- Source: https://community.zscaler.com/s/question/0D54u00009evmyiCAA/zapp-tunnel-mode-ztunnel-20-domain-bypass
- Type: Q&A
- Posted: 2020-04-28T02:56:22.000Z
- Last activity: 2023-05-31T08:50:15.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello, Community

I am testing Z-tunnel 2.0 bypass setting.
 I tried to configure with Best Practice kb, I read multiple times.
 [https://help.zscaler.com/z-app/best-practices-adding-bypasses-z-tunnel-2.0](https://help.zscaler.com/z-app/best-practices-adding-bypasses-z-tunnel-2.0)

In this article, Domain Bypass Configuration section says

> You can configure domain-based bypasses with custom PAC files for the forwarding profile and the app profile.
>

However Domain Bypass seems to be successfully conducted only with App Profile PAC file.

Why does this kb recommend Forwarding Profile PAC?
 Does anything problematic happen only with App Profile PAC?

Best Regards,
 Yosh
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmylCAA/pac-file-for-zapp-tunnel-mode","lastmod":"2023-07-06T11:46:48.000Z","id":"0D54u00009evmylCAA"} -->
## PAC File for Z-App "Tunnel mode"

- Source: https://community.zscaler.com/s/question/0D54u00009evmylCAA/pac-file-for-zapp-tunnel-mode
- Type: Q&A
- Posted: 2020-04-25T06:13:34.000Z
- Last activity: 2023-07-06T11:46:48.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Howdy folks.

My client is affected by the same issue like this below

Google 404 in Internet explorer when using Zscaler App
Client Connector
> When entering an address into the IE address bar a Google 404 page is returned. This happens for valid addresses such as:
> hotmail.com
> or
> msn.co.uk
> . This is an intermittent problem and if searching Google for the same sites and clicking on the same URLs the loads. we are using the Zscaler app with tunnel mode and packet based tunnel driver type. has anyone else had this issue?

They cannot change from Tunnel mode to TWLP mode for some reason.
 We think that the issue might be caused because they use the same PAC file for both AP and FP
 (I checked this below as well
 [Zapp Bypass PAC file configuration](https://community.zscaler.com/t/zapp-bypass-pac-file-configuration/7039))

So, now I am looking for the return statement for FP PAC to forward traffic to Z-App
 Since the user uses the return statement below for FP PAC
 return “PROXY ${GATEWAY}:80; PROXY ${SECONDARY_GATEWAY}:80; DIRECT?;

And this is not recommended because this means FP forwards the traffic to Zscaler cloud, right?

Thus, I need the return statement, which is added in the bottom of PAC, to forward traffic to Z-App if the traffic is not forwarded directly (return “DIRECT?

I referred the help this below, but there is not only sample PAC for FP Tunnel mode…
 
 [https://help.zscaler.com/z-app/best-practices-using-pac-files-zscaler-app?_ga=2.126602829.1549648291.1587601183-1650288619.1576123141](https://help.zscaler.com/z-app/best-practices-using-pac-files-zscaler-app?_ga=2.126602829.1549648291.1587601183-1650288619.1576123141)

If you have some idea or solution, please help me in this case!
 Thank you in advance.

Tokio
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmypCAA/zapp-with-pulsesecure-vpn-split-tunneling","lastmod":"2023-07-06T13:12:45.000Z","id":"0D54u00009evmypCAA"} -->
## Z-App with PulseSecure VPN Split Tunneling

- Source: https://community.zscaler.com/s/question/0D54u00009evmypCAA/zapp-with-pulsesecure-vpn-split-tunneling
- Type: Q&A
- Posted: 2020-04-20T14:50:43.000Z
- Last activity: 2023-07-06T13:12:45.000Z
- Answers: 44
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We have been using Z-App since 1.0. Many new feature have been added over the years. We currently run our VPN with no split tunneling. This was fine until the use of video conferencing during the pandemic. Currently we are set with Tunnel-Routed mode, and NONE for On-Trusted and NONE for VPN Trusted. We don’t use PAC files.

I’d like to turn on Split Tunneling, and allow default route to go through Zscaler. We have some traffic bypassing Zscaler today on-net due to various reasons (usually when login move to using a non-standard TLS port). We also bypass GSuite since Zscaler doesn’t inspect it. So it would appear from reading that the best practice for us is:

1. On-Net = NONE
2. VPN-Trusted = Tunnel
3. OFF-Trusted = Tunnel

Should we set the Tunnel to Packet Filter so we can create bypass rules?
 It seems that the “HOSTNAME OR IP ADDRESS BYPASS FOR VPN GATEWAY? setting could be used for this with Routing mode.

Thanks for your suggestions.
 -=Dan=-
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmyqCAA/zapp-prevents-clicking-file-name-change-and-other-basic-computer-behaviors","lastmod":"2023-05-31T08:50:38.000Z","id":"0D54u00009evmyqCAA"} -->
## Z-app prevents clicking, file name change and other basic computer behaviors

- Source: https://community.zscaler.com/s/question/0D54u00009evmyqCAA/zapp-prevents-clicking-file-name-change-and-other-basic-computer-behaviors
- Type: Q&A
- Posted: 2020-04-18T17:05:12.000Z
- Last activity: 2023-05-31T08:50:38.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello! Everyone!

I am one of Zscaler app users.
 Thank you for your proxy!

Do you have problem with ZApp?
 I can not move scroll bar, change file name, select multiple files or anything others to use good computer function!
 When Z-App is turned off, all clear, no problem.

Do someone have any hints? Any setting and Z-App have conflict?

Best Regard
 Ngyen
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmysCAA/zapp-client-root-certificate-for-mobile-and-tablet-devices","lastmod":"2023-05-31T08:14:20.000Z","id":"0D54u00009evmysCAA"} -->
## ZAPP client root certificate for Mobile and tablet devices

- Source: https://community.zscaler.com/s/question/0D54u00009evmysCAA/zapp-client-root-certificate-for-mobile-and-tablet-devices
- Type: Q&A
- Posted: 2020-04-16T15:24:10.000Z
- Last activity: 2023-05-31T08:14:20.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Team,

I need some help on SOP on installing and SOP for Zscaler Root CA certificate from URL’s (legitimate Url’s) to download the certificate directly via the devices directly.

Please let me know if you have any clarifications.

Regards
 SecurityGeek
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmyvCAA/zscaler-app-assign-application-profile-based-on-source-ip-dns-etc-zia","lastmod":"2023-07-06T13:13:04.000Z","id":"0D54u00009evmyvCAA"} -->
## Zscaler App - Assign Application Profile based on Source IP, DNS etc (ZIA)

- Source: https://community.zscaler.com/s/question/0D54u00009evmyvCAA/zscaler-app-assign-application-profile-based-on-source-ip-dns-etc-zia
- Type: Q&A
- Posted: 2020-04-14T15:29:58.000Z
- Last activity: 2023-07-06T13:13:04.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

So we are looking at rolling out the Zscaler App to machines and one of the things we need to determine is what application profile they get for Zapp to enforce ZIA forwarding profiles.

At the moment it looks like only users group membership is supported which isn’t ideal as it makes the set up fairly static. We are a global company and some of our users travel the world, China included. So in an ideal scenario we would could vary the application profile assigned based on their location to ensure optimal network traffic.

Does anyone know if this is on the roadmap?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmz3CAA/zscaler-app-on-servers","lastmod":"2023-07-06T13:13:07.000Z","id":"0D54u00009evmz3CAA"} -->
## Zscaler App on servers?

- Source: https://community.zscaler.com/s/question/0D54u00009evmz3CAA/zscaler-app-on-servers
- Type: Q&A
- Posted: 2020-04-06T13:53:20.000Z
- Last activity: 2023-07-06T13:13:07.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is there any plan from Zscaler to make available Zscaler App on servers?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmz5CAA/report-an-issue-in-zapp","lastmod":"2025-09-03T12:06:05.000Z","id":"0D54u00009evmz5CAA"} -->
## Report an issue in Z-App

- Source: https://community.zscaler.com/s/question/0D54u00009evmz5CAA/report-an-issue-in-zapp
- Type: Q&A
- Posted: 2020-04-04T00:31:28.000Z
- Last activity: 2025-09-03T12:06:05.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is it possible for customers to analyze the logs from report an issue?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmz7CAA/issues-with-outlook-when-working-outside-corporate-network-with-zapp","lastmod":"2023-05-31T09:08:19.000Z","id":"0D54u00009evmz7CAA"} -->
## Issues with Outlook when working outside corporate network with Zapp

- Source: https://community.zscaler.com/s/question/0D54u00009evmz7CAA/issues-with-outlook-when-working-outside-corporate-network-with-zapp
- Type: Q&A
- Posted: 2020-03-16T15:24:59.000Z
- Last activity: 2023-05-31T09:08:19.000Z
- Answers: 9
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

Since few weeks now, we are struggling, that some users have problems with Outlook (mainly, but sometimes Skype4 Business or Office account is affected). Problems occurring only when users are outisde office so when Zapp is started to work. Off trusted networks we have tunnel with local proxy, since in on trusted network we have proxy enforced.For other, we have route based tunnel driver type, disable loopback restriction enabled, override wpad enabled and restartwinhttp disabled. As Zapp we are using normally 1.5.1.8. In addition we have ModernAuthentication and MFA enforced. Policy for MFA is that Zscaler node ip are trusted, so when user is connecting from it, he will NOT be challanged for MFA. In Azure logs, I have noticed that for this affected users, traffic from Outlook is skipping proxy settings, so in logs it is visible that thay are login from outside IP. But for whatever reason window for MFA does not appear. Outlook or is not able to connect or displays message needs password. In Azure logs it is visible that user did not pass MFA challenge. I also notice that often or affected laptops Edge or Office has issues described here [https://docs.microsoft.com/en-us/office365/troubleshoot/authentication/connection-issue-when-sign-in-office-2016](https://docs.microsoft.com/en-us/office365/troubleshoot/authentication/connection-issue-when-sign-in-office-2016), and in Windows event logs (Applications and Services logs\Microsoft\Windows\AAD\Operational) there are hundreds of errors 1908
 "Error: 0xCAA70004 The server or proxy was not found.
 Exception of type ‘class HttpException’ at xmlhttpwebrequest.cpp, line: 171, method: XMLHTTPWebRequest::ReceiveResponse.
 Log: 0xcaa10083 Exception in WinRT wrapper.
 Logged at authorizationclient.cpp, line: 224, method: ADALRT::AuthorizationClient::AcquireToken.
 Request: authority: [https://login.microsoftonline.com/common](https://login.microsoftonline.com/common), client: {268761a2-03f3-40df-8a8b-c3db24145b6b}, redirect URI: ms-appx-web://Microsoft.AAD.BrokerPlugin/S-1-15-2-1609473798-1231923017-684268153-4268514328-882773646-2760585773-1760938157 "

When Zapp is removed or disabled, all is back to normal. User has windows to enter MFA code, Outlook is able to connect to Exchange online.

I thought, that issue maybe coused by Zapp 1.5.18, what quite often is in status “Connecting?, but even I did update for App 2.1, and it is connecting normally, issue is still there.

Unfortunately issue is more and more problematic, as since lot of people are working from home nowadays, we have lot of complains. We have ticket 02328163 opened, but for now no clear solution.

Sorry for so long post, and maybe not written good enough, but if something is not clear please let me know.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmz9CAA/zapp-bypass-pac-file-configuration","lastmod":"2023-05-31T08:50:56.000Z","id":"0D54u00009evmz9CAA"} -->
## Zapp Bypass PAC file configuration

- Source: https://community.zscaler.com/s/question/0D54u00009evmz9CAA/zapp-bypass-pac-file-configuration
- Type: Q&A
- Posted: 2020-03-07T07:44:20.000Z
- Last activity: 2023-05-31T08:50:56.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

Zscaler App can contain PAC file both in App Profile and Forwarding Profile.
 Do the bypass setting of PAC in AP and the PAC in FP make difference?
 Are both of them simply bypassing and nothing different at all?

Best Regards,
 Yosh
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmzACAQ/differences-between-zscaler-security-and-zscaler-app","lastmod":"2023-05-31T08:50:56.000Z","id":"0D54u00009evmzACAQ"} -->
## Differences between Zscaler Security and Zscaler App

- Source: https://community.zscaler.com/s/question/0D54u00009evmzACAQ/differences-between-zscaler-security-and-zscaler-app
- Type: Q&A
- Posted: 2020-03-06T03:48:43.000Z
- Last activity: 2023-05-31T08:50:56.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

I just notice that there is slight differences when installing Zscaler App on my Windows PC. For my colleague, the one display on the PC was named “Zscaler Security? as for mine is “Zscaler App?. May I know why does this happen, is it because different installer used?

Regards,
 Matthews Loke
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmzGCAQ/android-zapp-stucks","lastmod":"2025-09-03T12:06:10.000Z","id":"0D54u00009evmzGCAQ"} -->
## Android Zapp stucks

- Source: https://community.zscaler.com/s/question/0D54u00009evmzGCAQ/android-zapp-stucks
- Type: Q&A
- Posted: 2020-03-02T10:06:46.000Z
- Last activity: 2025-09-03T12:06:10.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi.
 I would to share with the community an issue in order to understand if this is a problem only for my company or also for others.
 We have some users that use ZAPP from android devices. Really the model it isn’t very important because the issue occurs on any our devices also if the main brand is Samsung.

The problem is that ZAPP works always on the first installation/use but it stucks always on “starting tunnel? message when you restart again.

STEPS FOR TEST

1. Open app, login and activate the tunnel,
2. Stop tunnel and go on the device home.
3. Come back on the app…it stuck always on the “starting tunnel?.
4. Reboot is not the solution because the app autostart and stucks again. The way is the uninstallation but the users can to do only after the removing the association user/device from ZAPP portal.

I’ve an ticket opened since 1 month about but still no good new about. Only I found a “trick? in order to delay the hangs. After to have stopped the tunnel, perform an logout from Zscaler app. This permit to use the app also 4,5 times before that it stucks again, but cannot be the solution.

Thx,
 Matteo.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmzHCAQ/zapp-migration-to-lwf","lastmod":"2025-08-22T07:27:39.000Z","id":"0D54u00009evmzHCAQ"} -->
## ZApp Migration to LWF

- Source: https://community.zscaler.com/s/question/0D54u00009evmzHCAQ/zapp-migration-to-lwf
- Type: Q&A
- Posted: 2020-02-28T19:25:22.000Z
- Last activity: 2025-08-22T07:27:39.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are running Zscaler App 1.5.1 in Tunnel with Local Proxy mode, using the default installation with the virtual network adapter. We are planning to move to Tunnel mode as well as switch to the LWF driver installation.

Is there a way to repair ZApp to remove the virtual network adapter and install LWF driver? Or is the only solution a uninstallation, reboot and installation with LWF switch?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmzICAQ/difference-in-use-of-app-profile-pac-file-and-forwarding-profile-pac-file","lastmod":"2023-07-06T13:10:54.000Z","id":"0D54u00009evmzICAQ"} -->
## Difference in use of "App Profile PAC file" and "Forwarding Profile PAC file"

- Source: https://community.zscaler.com/s/question/0D54u00009evmzICAQ/difference-in-use-of-app-profile-pac-file-and-forwarding-profile-pac-file
- Type: Q&A
- Posted: 2020-02-28T15:04:11.000Z
- Last activity: 2023-07-06T13:10:54.000Z
- Answers: 15
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,
 I have some difficulties in understanding the different “use-cases? of “App Profile PAC? and “Forwarding Profile PAC?

My customer likes to use ZAPP with filter driver and tunnel mode.

Due to customer requirements, we need to send traffic not only Zscaler but some traffic needs to be sent to “on-site proxies?, for example “PROXY <RFC 1918 IP address>:8080?; or “PROXY [proxy.acme.com:8080](http://proxy.acme.com:8080)?; ( we call this “proxy switching?)

I checked the availble documentation here
 [https://help.zscaler.com/z-app/configuring-zscaler-app-profiles](https://help.zscaler.com/z-app/configuring-zscaler-app-profiles)
 and here
 [https://help.zscaler.com/z-app/configuring-forwarding-profiles-zscaler-app](https://help.zscaler.com/z-app/configuring-forwarding-profiles-zscaler-app) (with the enforce Proxy settings) but I did not find clarification.

- What exactly is the different use of the pac files in forwarding profile and app profile?
- I understand that App Profile PAC is only used by ZAPP and used for selecting a specific ZEN; and for bypassing the ZTunnels
- Forwarding Profile PAC will be applied as “System Proxy? beside ZAPP?
- Nils from Zscaler told me, that “proxy switching? needs to be done in the “Forwarding Profile PAC file?
- But how do I get traffic to Zscaler, if we are using a PAC in the forwarding profile. Do I have to use ${ZAPP_LOCAL_PROXY}"? My first assumption was that this variable can only be used if “tunnel with local proxy? is used.
- Do I need the same “DIRECT? exceptions in both PAC files? If not, which exceptions to put where? Both ZAPP training slides contain “Optionally specify Z-Tunnel bypasses? “App Profile PAC file? and “Forwarding Profile PAC file?.

Thank you and best regards
 Andreas
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmzbCAA/zapp-auto-update-not-possible","lastmod":"2023-05-31T08:50:59.000Z","id":"0D54u00009evmzbCAA"} -->
## ZApp auto update not possible

- Source: https://community.zscaler.com/s/question/0D54u00009evmzbCAA/zapp-auto-update-not-possible
- Type: Q&A
- Posted: 2020-02-24T10:54:07.000Z
- Last activity: 2023-05-31T08:50:59.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

we are currently trying to breakout the IP´s behind the [d32a6ru7mhaq0c.cloudfront.net](http://d32a6ru7mhaq0c.cloudfront.net) (ZApp auto Update URL) locally.
 We don´t have a default internet route, so we have to type in the ip addresses behind that.
 But we recognized that there are so many dynamic public ip addresses behind this fqdn, that we are not able to implement the routing correctly.
 We already route the recommended Zscaler HUB IP´s, but this doesn´t work for the App Update.

Can anyone help me or do have the same experience?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmzjCAA/zapp-32bit-vs-64bit-support","lastmod":"2025-08-22T07:27:18.000Z","id":"0D54u00009evmzjCAA"} -->
## ZApp 32bit vs 64bit support

- Source: https://community.zscaler.com/s/question/0D54u00009evmzjCAA/zapp-32bit-vs-64bit-support
- Type: Q&A
- Posted: 2020-02-10T15:23:56.000Z
- Last activity: 2025-08-22T07:27:18.000Z
- Answers: 29
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We’re using ZApp 2.1.0 which Windows reports as being 32-bit. What is the Zscaler vision for a 64-bit version of ZApp?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmzlCAA/zscaler-app-install-failure-during-osd-wmdt","lastmod":"2023-05-31T08:51:07.000Z","id":"0D54u00009evmzlCAA"} -->
## Zscaler app install failure during OSD w/MDT

- Source: https://community.zscaler.com/s/question/0D54u00009evmzlCAA/zscaler-app-install-failure-during-osd-wmdt
- Type: Q&A
- Posted: 2020-02-07T18:15:01.000Z
- Last activity: 2023-05-31T08:51:07.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are having issues when installing the Zscaler App during operating system deployment using the Microsoft Deployment Toolkit.

Sometimes Zscaler will hang for anywhere from 10-50 minutes and then the reinstall will restart and fail with exit code 2 - file not found - as if the network share where the install media are located is inaccessible.

We’re installing Windows 10 1909 and MDT is version 8456. Zscaler is v1.5.1.8. We wrap the installer in a Winbatch executable. Log files show the executable being run from a network share but nothing more until the install starts again and dies with exit code 2.

It deploys fine via SCCM as an application outside of the OSD process.

Anyone else run into installing it during OSD?

Skip
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmzoCAA/users-cannot-reset-or-set-a-rsa-token-pin-on-zapp","lastmod":"2023-05-31T08:51:07.000Z","id":"0D54u00009evmzoCAA"} -->
## Users cannot reset or set a RSA Token PIN on Zapp

- Source: https://community.zscaler.com/s/question/0D54u00009evmzoCAA/users-cannot-reset-or-set-a-rsa-token-pin-on-zapp
- Type: Q&A
- Posted: 2020-02-05T17:47:15.000Z
- Last activity: 2023-05-31T08:51:07.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We recently deployed ZPA which uses Siteminder but unable to find functionality to support RSA first time pin reset feature for new users. We also have an F5 remote access VPN which currently supports resetting/setting RSA PIN via it’s web interface for new users and existing users.

Is there an alternative method to support that feature in ZPA?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmzpCAA/zapp-vpn-trusted-network","lastmod":"2023-05-31T08:51:07.000Z","id":"0D54u00009evmzpCAA"} -->
## Zapp VPN Trusted Network

- Source: https://community.zscaler.com/s/question/0D54u00009evmzpCAA/zapp-vpn-trusted-network
- Type: Q&A
- Posted: 2020-02-04T14:48:38.000Z
- Last activity: 2023-05-31T08:51:07.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

How can i use the “VPN Trusted Network? in Zapp in forwarding profile. I can switch between trusted and off trusted network but i couldn’t utilize VPN Trusted Network.

Regards
 Ganesh Krishnan
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmzqCAA/zscaler-app-version-21-mac-os","lastmod":"2023-05-31T08:51:07.000Z","id":"0D54u00009evmzqCAA"} -->
## Zscaler App version 2.1 - Mac OS

- Source: https://community.zscaler.com/s/question/0D54u00009evmzqCAA/zscaler-app-version-21-mac-os
- Type: Q&A
- Posted: 2020-02-03T17:26:46.000Z
- Last activity: 2023-05-31T08:51:07.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I see that ZApp 2.1 has made it to GA with a note that users running Mac OS should update to 2.1 before updating to Catalina. Does this apply to user that were running ZApp 1.5.2.6 or is it specific to users that were running a previous version of ZApp 2?

[https://help.zscaler.com/z-app/upgrading-macos-catalina](https://help.zscaler.com/z-app/upgrading-macos-catalina)
 [https://help.zscaler.com/z-app/january-30-2020-release-notes-zscaler-app-2.1-windows-and-macos](https://help.zscaler.com/z-app/january-30-2020-release-notes-zscaler-app-2.1-windows-and-macos)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmzsCAA/zscaler-app-and-setting-sublocation","lastmod":"2023-05-31T08:51:07.000Z","id":"0D54u00009evmzsCAA"} -->
## Zscaler App and setting sublocation

- Source: https://community.zscaler.com/s/question/0D54u00009evmzsCAA/zscaler-app-and-setting-sublocation
- Type: Q&A
- Posted: 2020-01-30T05:36:24.000Z
- Last activity: 2023-05-31T08:51:07.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi

Question about location settings on ZIA.
 We are trying to configure connection using the ZApp and apply policies based on location.
 We have a lot of branch offices under one Global IP and want to configure polices according to segments under it, meaning that we want to set policies based on private IP.
 Is this possible?
 I know we can do this with GRE, but we have to do this with ZApp only.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmzuCAA/zscaler-app-and-google-earth","lastmod":"2023-05-31T08:50:59.000Z","id":"0D54u00009evmzuCAA"} -->
## Zscaler App and Google Earth

- Source: https://community.zscaler.com/s/question/0D54u00009evmzuCAA/zscaler-app-and-google-earth
- Type: Q&A
- Posted: 2020-01-27T12:40:17.000Z
- Last activity: 2023-05-31T08:50:59.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi all,

If we launch the latest google earth through the pac file it works a treat, if we divert the traffic through the app, google earth will launch, but you only get a black screen, we have added the relevant URLS to the app bypass list, and still nothing.
 Anybody come across this issue before, we are using tunnel with proxy.

Thanks

Gary
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmzwCAA/least-amount-of-user-interaction-with-zapp-on-ios","lastmod":"2023-05-31T09:25:42.000Z","id":"0D54u00009evmzwCAA"} -->
## Least amount of user interaction with Zapp on IOS

- Source: https://community.zscaler.com/s/question/0D54u00009evmzwCAA/least-amount-of-user-interaction-with-zapp-on-ios
- Type: Q&A
- Posted: 2020-01-24T21:24:49.000Z
- Last activity: 2023-05-31T09:25:42.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are trying to make zscaler installation the most transparent experience possible for our users.

We deploy the application and config using JAMF as our MDM.

To avoid login prompts/authentication we used policy token method of authentication with a username dynamically populated by jamf.

To avoid VPN prompt + passcode prompt for the VPN install we populate the VPN profile.

The only thing we can’t seem to do is…to actually have Zscaler enroll a user, the user just has to launch the zscaler app on their iPhones.

Does anyone have any ideas how to make the enrollment of the device transparent to the user? It doesn’t seem like IOS gives MDM the capability to launch an app.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmzxCAA/windows-technically-how-does-the-zapp-enforce-the-proxy-settings","lastmod":"2023-05-31T08:14:24.000Z","id":"0D54u00009evmzxCAA"} -->
## Windows - Technically how does the ZApp Enforce the Proxy settings?

- Source: https://community.zscaler.com/s/question/0D54u00009evmzxCAA/windows-technically-how-does-the-zapp-enforce-the-proxy-settings
- Type: Q&A
- Posted: 2020-01-24T17:48:06.000Z
- Last activity: 2023-05-31T08:14:24.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

I’m currently working for a client that is using the ZApp with the Enforce Proxy as Forwarding Profile Method.

From my understanding and what I have been told, with this parameter, the ZApp is only forcing the proxypac file and nothing else.
 But technically, how does it enforce it ? Indeed, It seems to not modify the proxy settings on the control pannel or in the registry of the current user.

Kind regards,

TSanto
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn01CAA/zapp-tunnel-20-nat-device","lastmod":"2023-05-31T08:51:13.000Z","id":"0D54u00009evn01CAA"} -->
## Zapp Tunnel 2.0 NAT device

- Source: https://community.zscaler.com/s/question/0D54u00009evn01CAA/zapp-tunnel-20-nat-device
- Type: Q&A
- Posted: 2020-01-21T14:21:48.000Z
- Last activity: 2023-05-31T08:51:13.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello

could you explain us this extract on you knowledge base “For Z-Tunnel 2.0, use a NAT device that doesn’t require a different egress IP for each device’s separate sessions, because doing so might cause Z App to fall back to Z-Tunnel 1.0? ?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn04CAA/seamless-sso-with-zapp-on-ios-and-azure-ad-as-idp","lastmod":"2023-05-31T08:51:13.000Z","id":"0D54u00009evn04CAA"} -->
## Seamless SSO with ZApp on iOS and Azure AD as IdP

- Source: https://community.zscaler.com/s/question/0D54u00009evn04CAA/seamless-sso-with-zapp-on-ios-and-azure-ad-as-idp
- Type: Q&A
- Posted: 2020-01-17T12:58:13.000Z
- Last activity: 2023-05-31T08:51:13.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello!

We are currently doing a Proof-of-Value with ZPA and ZIA, focusing on iOS devices for now.
 We use our Azure AD as the IdP. Manual sign-on works in ZApp.
 Our devices are enrolled to Intune. For O365 apps (Outlook, OneDrive, …) we use the Microsoft Authenticator app to provide cross-app SSO (sign-in once, access all O365 apps without additional login).

Our expectation is that the ZApp also connects to the Authenticator app and use the same, existing, valid token. This is not happening. User has to open ZApp and authenticate to Azure AD once more.

Any ideas?

I think Zapp would need to include ADAL/MSAL libraries to support “broker-assisted single sign-on?.
 ([Android](https://docs.microsoft.com/en-us/azure/active-directory/develop/brokered-auth) / [iOS](https://docs.microsoft.com/en-us/azure/active-directory/develop/single-sign-on-macos-ios#sso-through-authentication-broker-on-ios) docs)
 Or is there anything we can do with mobile app config?
 We had a lead with the Apple Kerberos SSO extension. But our devices are internet only (never on corporate network), so they don’t have line-of-sight to the Domain Controller / KDC. So that won’t work.

Kind regards,
 Thomas
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn05CAA/reactivate-internet-security","lastmod":"2023-05-31T08:51:13.000Z","id":"0D54u00009evn05CAA"} -->
## Reactivate Internet Security

- Source: https://community.zscaler.com/s/question/0D54u00009evn05CAA/reactivate-internet-security
- Type: Q&A
- Posted: 2020-01-16T17:01:24.000Z
- Last activity: 2023-05-31T08:51:13.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Looking at ZApp App Profiles, we can reactivate the ZApp enforcement at up to 24 hours after a user disables it. Is there a maximum number of times that this can happen? My concern is that if I allow a group to have 2 hours of ‘disabled’ time, they will be able to immediately disable it again as soon as Internet Security is automatically turned back on. Is there any way to only allow this to happen once a day and/or week possibly?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn0DCAQ/zscaler-app-compatibility-issue-with-cisco-ise-agent","lastmod":"2023-07-06T12:56:37.000Z","id":"0D54u00009evn0DCAQ"} -->
## Zscaler App compatibility issue with Cisco ISE agent

- Source: https://community.zscaler.com/s/question/0D54u00009evn0DCAQ/zscaler-app-compatibility-issue-with-cisco-ise-agent
- Type: Q&A
- Posted: 2020-01-08T07:24:51.000Z
- Last activity: 2023-07-06T12:56:37.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello! We are currently deploying Zscaler App to customer and having compatibility issue with Cisco AnyConnect Secure Mobility Client. The agent is only for Network access control.
 We found that the issue happen when user switch from wired network to wireless network. Both network are also connected to the same corporate network internally. When user switch network, the Cisco AnyConnect Secure Mobility Client will act as NAC solution and check for 3 things before allow the user connect to internal corporate network:

1. Check if the PC is domain-joined
2. Check the Antivirus is installed
3. Check the Virus signature is up-to-date After all checking are compliant, user will be allowed to access the internal corporate network.

After installed Zscaler App, we found on Zscaler App that when switch between network, the ZApp will show message that indicate not able to reach internet. This is normal since Cisco agent need to complete the checking before allow the network. However after the Cisco agent completed the checking and show compliant, the ZApp still showing the same error message. We observed the symptom on the affected PC as below:

- Not able to access any internal website
- Not able to access internet
- not able to resolve DNS with internal DNS server The issue remain the same when we try to restart the checking on Cisco agent. The issue is Intermittent and it does NOT happen every time when switch between network. It could only be resumed when user restart the PC.

May I know if there is any previous experience sharing that install Zscaler App to Cisco ISE agent environment, or any other NAC solution?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn0JCAQ/authentication-vs-always-on-zapp-client","lastmod":"2023-05-31T08:51:18.000Z","id":"0D54u00009evn0JCAQ"} -->
## Authentication vs Always on Zapp client

- Source: https://community.zscaler.com/s/question/0D54u00009evn0JCAQ/authentication-vs-always-on-zapp-client
- Type: Q&A
- Posted: 2019-12-30T22:43:02.000Z
- Last activity: 2023-05-31T08:51:18.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

According to our system audit report we should change authentication from our current setting to once and not have it challenge until the cookie expires (2 years). Besides not blocking a person no longer employed, what are security risks if any exist? Also, where does having Zapp on full time instead of using on trusted network come into play? Is turning Zapp on full time still a advised way to go? The issue i have with this is bypasses needed where our external IP is needed will have to be added to both Zapp and Network infrastructure.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn0KCAQ/zscaler-network","lastmod":"2023-05-31T08:51:18.000Z","id":"0D54u00009evn0KCAQ"} -->
## Zscaler network

- Source: https://community.zscaler.com/s/question/0D54u00009evn0KCAQ/zscaler-network
- Type: Q&A
- Posted: 2019-12-27T14:44:43.000Z
- Last activity: 2023-05-31T08:51:18.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

If we are using zapp, how the traffic is flowing. And if some urls are added in exception (to go thru firewall), how zapp is applying rule for users outside corporate network? Is the request coming to organisations firewall to pass it DIRECt
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn0LCAQ/forward-traffice-using-zapp-and-gre-tunnel","lastmod":"2023-07-06T13:14:11.000Z","id":"0D54u00009evn0LCAQ"} -->
## Forward Traffice using Zapp and GRE Tunnel

- Source: https://community.zscaler.com/s/question/0D54u00009evn0LCAQ/forward-traffice-using-zapp-and-gre-tunnel
- Type: Q&A
- Posted: 2019-12-26T19:09:17.000Z
- Last activity: 2023-07-06T13:14:11.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

What are the side effects of having a Zapp inside a Trusted network where traffic is forwarded through a GRE tunnel. Will it create another tunnel inside the GRE tunnel?

We set the rule for Zapp to forward traffic to Zscaler(Using PROXY ${GATEWAY}:9400;…) even when inside a trusted network.

I understand we can set it to disable when inside a trusted network but if the GRE tunnel overrides the Zapp then no need.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn0aCAA/zapp-users-are-receiving-a-saml-400-error","lastmod":"2023-05-31T09:08:12.000Z","id":"0D54u00009evn0aCAA"} -->
## ZApp - Users are receiving a SAML 400 Error

- Source: https://community.zscaler.com/s/question/0D54u00009evn0aCAA/zapp-users-are-receiving-a-saml-400-error
- Type: Q&A
- Posted: 2019-12-17T23:10:22.000Z
- Last activity: 2023-05-31T09:08:12.000Z
- Answers: 7
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

When the authentication expires, users will click “Re-Authenticate? within the Private Access service of the ZApp. If the user logs out of the app, and logs back in, the error doesn’t show. It’s only when they select “Re-Authenticate?. This happens to only a handful of users. Not all.

Anyone experience the same issue?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn0jCAA/best-practices-for-bypassing-domains-in-tunnel-20","lastmod":"2023-05-31T08:51:27.000Z","id":"0D54u00009evn0jCAA"} -->
## Best practices for bypassing domains in tunnel 2.0

- Source: https://community.zscaler.com/s/question/0D54u00009evn0jCAA/best-practices-for-bypassing-domains-in-tunnel-20
- Type: Q&A
- Posted: 2019-12-04T03:45:08.000Z
- Last activity: 2023-05-31T08:51:27.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

[https://help.zscaler.com/z-app/best-practices-adding-bypasses-z-tunnel-2.0](https://help.zscaler.com/z-app/best-practices-adding-bypasses-z-tunnel-2.0)
 i was following this doc on how to test the scenario out. I cannot seem to get a test domain to work. I am confused about the part looking for a tunnel 1.0 listener.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn0lCAA/zscaler-app-issue-azure-ad","lastmod":"2023-05-31T08:51:30.000Z","id":"0D54u00009evn0lCAA"} -->
## Zscaler App Issue - Azure AD

- Source: https://community.zscaler.com/s/question/0D54u00009evn0lCAA/zscaler-app-issue-azure-ad
- Type: Q&A
- Posted: 2019-11-26T15:39:41.000Z
- Last activity: 2023-05-31T08:51:30.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

I am getting the below error while doing the IWA for Z-App via Azure AD SSO

****AADSTS900561: The endpoint only accepts GET requests. Received a POST request.** **
 **Request Id: 9bfad77f-78ac-4cab-91ac-df8dec4c4300 **

**Correlation Id: a07ac6b5-a2f0-4853-b378-bb17236a2736 **

**Timestamp: 2019-11-26T15:33:52Z **

**Message: AADSTS900561: The endpoint only accepts GET requests. Received a POST request.**
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn0oCAA/zscaler-app-forwarding-profiles-and-windows-directaccess","lastmod":"2023-05-31T08:51:30.000Z","id":"0D54u00009evn0oCAA"} -->
## Zscaler App, Forwarding Profiles and Windows DirectAccess

- Source: https://community.zscaler.com/s/question/0D54u00009evn0oCAA/zscaler-app-forwarding-profiles-and-windows-directaccess
- Type: Q&A
- Posted: 2019-11-19T05:42:37.000Z
- Last activity: 2023-05-31T08:51:30.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

I’m trying to configure Z-app to work with our DirectAccess setup, but keep running into an issue where DirectAccess can’t connect when I’m running tunnel mode, or any other mode for that matter. Do I need to configure split-tunnel? A pac file? A VPN gateway bypass? I’m a bit lost currently.

Wondering if anyone has experience setting this up? Cheers
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn0sCAA/zscaler-app-ends-in-connection-error-with-egypt-isp","lastmod":"2023-07-06T13:14:16.000Z","id":"0D54u00009evn0sCAA"} -->
## Zscaler App ends in Connection Error with Egypt ISP

- Source: https://community.zscaler.com/s/question/0D54u00009evn0sCAA/zscaler-app-ends-in-connection-error-with-egypt-isp
- Type: Q&A
- Posted: 2019-11-06T06:52:49.000Z
- Last activity: 2023-07-06T13:14:16.000Z
- Answers: 25
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We have a team in Egypt whose Zscaler app ends in connection error. We have open access to internet and no restriction in our network/security devices. Does anyone experienced any issue from Egypt ?

Regards
 Ganesh Krishnan
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn0tCAA/zpa-zapp-for-linux-required","lastmod":"2025-09-03T12:06:12.000Z","id":"0D54u00009evn0tCAA"} -->
## ZPA ZApp for Linux required

- Source: https://community.zscaler.com/s/question/0D54u00009evn0tCAA/zpa-zapp-for-linux-required
- Type: Q&A
- Posted: 2019-11-03T19:56:03.000Z
- Last activity: 2025-09-03T12:06:12.000Z
- Answers: 262
- Likes: 26
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Today many potential customers have a DevOps department that often use Linux computers. Unlike competitors that offer Liunx clients, Zscaler does not offer Zscaler APP (ZAPP) for Linux. This has in the past led our customers not to choose the ZPA solution. It can’t be that difficult to offer a Linux ZAPP client. When will it be offered?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn0uCAA/zscaler-app-with-org2org-saml-auth-and-sso","lastmod":"2023-07-06T13:14:29.000Z","id":"0D54u00009evn0uCAA"} -->
## Zscaler App with Org2Org SAML Auth and SSO

- Source: https://community.zscaler.com/s/question/0D54u00009evn0uCAA/zscaler-app-with-org2org-saml-auth-and-sso
- Type: Q&A
- Posted: 2019-11-02T13:35:58.000Z
- Last activity: 2023-07-06T13:14:29.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello.

I have the following question around SAML Auth with Org2Org integration.

We have configured Zscaler for SAML authentication via Okta and this one works well with SSO. Users onprem are redirected to IWA and are authenticated transparently - no user/Zapp interaction is needed. We then moved further and tried extending this to Org2Org (Okta “federation?).

Org2Org integration works well - users are populated from Okta Tenant B into Okta Tenant A and from there are provisioned into Zscaler. There’s only one thing which we don’t like and I am not sure if Zscaler can help resolving it at all…

Users in Tenant B use different domain (it is provisioned on Zscaler cloud). To be able to redirect them to Tenant B IdP in Okta we had to configure Authentication Rule to look for @domain-B and in this case redirect to Okta in Tenant B.

IWA works well for Tenant A, as well as standard SAML flow
 IWA works well for Tenant B, as well as standard SAML flow

The difference is that when Zscaler redirects user who belongs to Okta Tenant B to Okta Tenant A (initial auth), a blank username prompt appears because IWA rule is not being hit in Okta Tenant A… so user MUST enter full UPN/email to make sure Okta Tenant A can detect he belongs to Okta Tenant B, once user puts the username and presses Enter, user is redirected to Okta Tenant B and IWA sequence in Okta Tenant B kicks in and user is seamlessly authenticated (no password prompt)

Is there anything at Zscaler end (App specifically) that can help to populate full username into IdP username prompt and submit it transparently on user’s behalf?

I’ve noticed there’s feature

Automatically populate Username field for IDP Authentication

But it is only available starting from App v2.1 (we are on 1.5) and I am not even sure if this is what I am looking for

Regards
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn11CAA/zapp-and-anyconnect-best-forwarding-method","lastmod":"2023-07-06T13:09:39.000Z","id":"0D54u00009evn11CAA"} -->
## ZApp and AnyConnect - Best Forwarding Method?

- Source: https://community.zscaler.com/s/question/0D54u00009evn11CAA/zapp-and-anyconnect-best-forwarding-method
- Type: Q&A
- Posted: 2019-10-24T16:01:17.000Z
- Last activity: 2023-07-06T13:09:39.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi

I have read the following article

[https://help.zscaler.com/z-app/best-practices-zscaler-app-and-vpn-client-interoperability](https://help.zscaler.com/z-app/best-practices-zscaler-app-and-vpn-client-interoperability)

But it raises more questions. So, for example. At the very beginning it says that for Windows either **Tunnel with Local Proxy** or **Tunnel** ( **Packet Filter Based** ) are recommended.

But then, for both cases (Full Tunnel and Split-Tunnel) it is recommended to use Tunnel with Local Proxy.

I have to admit this article seems like it wasn’t fully updated. Can someone please confirm what is the recommended Forwarding Method for ZApp with AnyConnect in Split Tunnel mode?

Regards
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn14CAA/terminal-server-with-zapp","lastmod":"2023-07-06T13:00:59.000Z","id":"0D54u00009evn14CAA"} -->
## Terminal Server with Zapp

- Source: https://community.zscaler.com/s/question/0D54u00009evn14CAA/terminal-server-with-zapp
- Type: Q&A
- Posted: 2019-10-21T10:50:37.000Z
- Last activity: 2023-07-06T13:00:59.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Guys,

We have recently deployed some 2012R2 terminal servers that our users will be working from, I deployed the zapp on these servers and it was working okay, however now that we have moved more users on to these the app is just stuck on connecting, no error or anything just says connecting. Does anyone here use zapp on terminal servers for multiple users ? is there some limation here? I have a ticket open at the moment
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn15CAA/zscaler-app-for-ios","lastmod":"2023-07-07T11:30:07.000Z","id":"0D54u00009evn15CAA"} -->
## Zscaler app for IOS

- Source: https://community.zscaler.com/s/question/0D54u00009evn15CAA/zscaler-app-for-ios
- Type: Q&A
- Posted: 2019-10-19T14:42:59.000Z
- Last activity: 2023-07-07T11:30:07.000Z
- Answers: 11
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

This topic is strictly about iphones.
 I’m currently using a PAC file to send traffic to Zscaler for content filtering and policies, I’m having quit a few issues with this option,
 I did a test with installing Zapp from iTunes store and it seems to work fine.
 My question is how can I deploy the app to my devices so that my employees are forces to log into the app in order to be able to browse.
 I also noticed that even when I do log into the Zscaler app I can just go into setting and turn the VPN off then I can browse freely again.
 I did see some info about strict enforcement that seemed like what I was looking for…
 I’m currently using Airwatch as my MDM and my devices are supervised.
 I’m a small business owner with 25 employees, I don’t have a IT person on staff.
 if somebody could explain this to me as you would a five your old it would be greatly appreciated.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn18CAA/mac-os-zapp-with-f5-splittunnel-vpn","lastmod":"2023-07-06T13:14:40.000Z","id":"0D54u00009evn18CAA"} -->
## Mac OS Zapp with F5 Split-tunnel VPN

- Source: https://community.zscaler.com/s/question/0D54u00009evn18CAA/mac-os-zapp-with-f5-splittunnel-vpn
- Type: Q&A
- Posted: 2019-10-12T04:36:11.000Z
- Last activity: 2023-07-06T13:14:40.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi, We are newly implementing Zapp for Mac OS for Internet access. On-net (“Enforce Proxy?) & Off-net (“Tunnel with Local proxy?) is working fine, but didnt get Internet access while on Split-tunnelled VPN. Both the Zapp connection & the split-tunnelled VPN are unstable & getting disconnected time to time.

As per Zscaler docs , the split-tunnelled VPN is NOT considered as VPN-network, but considered as Off-net in the Forwarding profile which is acceptable.

As per Zscaler recommendation for Mac OS, the Off-net access is set to “Tunnel with Local proxy? and the browser correctly receives the pac file URL as [http://127.0.0.1:9000/proxy.pac](http://127.0.0.1:9000/proxy.pac) making the Internet access working via Zapp for Off-net. But, with split-tunnel VPN, the same “Tunnel with Local proxy? mode is applied, & the pac file URL is seen set at browser, but the actual pac file not reachable via browser. In contrast, command line curl command is able to fetch the pac file correctly even while on split-tunnelled VPN.

Appreciate if any one could advise any troubleshooting steps to do.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn19CAA/zapp-device-clean-up-option","lastmod":"2023-05-31T09:24:42.000Z","id":"0D54u00009evn19CAA"} -->
## Zapp Device clean Up Option

- Source: https://community.zscaler.com/s/question/0D54u00009evn19CAA/zapp-device-clean-up-option
- Type: Q&A
- Posted: 2019-10-11T11:03:16.000Z
- Last activity: 2023-05-31T09:24:42.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We have configured Device clean Up threshold as 8 in Mobile portal -> Zscaler App Support -> Device Clean Up. What will happen is a single account logged in 9 different machine.

a) Will 9th machine fails to login
 b) Will Zscaler mobile portal removes the Old registration (First In First Out)

Regards
 Ganesh Krishnan
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn1ECAQ/zscaler-app-and-tunnel-client","lastmod":"2023-05-31T08:51:51.000Z","id":"0D54u00009evn1ECAQ"} -->
## Zscaler App and Tunnel Client

- Source: https://community.zscaler.com/s/question/0D54u00009evn1ECAQ/zscaler-app-and-tunnel-client
- Type: Q&A
- Posted: 2019-09-25T19:36:15.000Z
- Last activity: 2023-05-31T08:51:51.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I’m working on behalf of a client who is running in packet filter mode with full tunnel.

They are trying to deploy a new tunnel app that sits on the 100.64.0.1 address and adds a route for the whole of the CGN network (100.64.0.0/10).

When this tunnel client is running the ZAPP fails to connect. Is there any way to debug the issue on the ZAPP to see where the traffic is going for the DNS tunnels which I also believe are on 100.64.0.3, 4, 5?

Is it the route that is the issue? Or will there be a clash somewhere else that could be causing this?

Is there any way to move zscaler off the 100.64 network ?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn1ICAQ/zapp-and-10064","lastmod":"2023-07-06T13:14:45.000Z","id":"0D54u00009evn1ICAQ"} -->
## Zapp and 100.64

- Source: https://community.zscaler.com/s/question/0D54u00009evn1ICAQ/zapp-and-10064
- Type: Q&A
- Posted: 2019-09-23T06:01:28.000Z
- Last activity: 2023-07-06T13:14:45.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello All,
 When local networks consist of 100.64.0.0/22, tunnel mode seem to work fine, however once connecting to a split tunnel VPN, the app fails to connect, probably due to the static route to this network being added and pointing to the virtual adapter representing the VPN connection.

Any solution for this scenario? Aside for switching to local proxy…

Thanks!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn1MCAQ/zapp-and-ios-13","lastmod":"2023-05-31T08:51:51.000Z","id":"0D54u00009evn1MCAQ"} -->
## ZAPP and iOS 13

- Source: https://community.zscaler.com/s/question/0D54u00009evn1MCAQ/zapp-and-ios-13
- Type: Q&A
- Posted: 2019-09-17T15:25:52.000Z
- Last activity: 2023-05-31T08:51:51.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

What’s the current state of ZAPP and iOS 13?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn1cCAA/zapp-policy-on-device-level","lastmod":"2023-05-31T08:51:54.000Z","id":"0D54u00009evn1cCAA"} -->
## ZApp Policy on Device Level

- Source: https://community.zscaler.com/s/question/0D54u00009evn1cCAA/zapp-policy-on-device-level
- Type: Q&A
- Posted: 2019-09-16T21:04:29.000Z
- Last activity: 2023-05-31T08:51:54.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

Is there a way to have a policy for Zapp on a device level instead of a user level. We have users using both there Work and Personnel devices, where we want to enforce ZIA on the Work PC but allow the user to turn it off on their personnel PC.
 Take into account they are using the same user account for both devices.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn1iCAA/zapp-root-certificate-not-installing","lastmod":"2023-05-31T08:52:02.000Z","id":"0D54u00009evn1iCAA"} -->
## Zapp Root Certificate Not Installing

- Source: https://community.zscaler.com/s/question/0D54u00009evn1iCAA/zapp-root-certificate-not-installing
- Type: Q&A
- Posted: 2019-08-22T21:40:37.000Z
- Last activity: 2023-05-31T08:52:02.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We deployed the Zapp and it is not installing the SSL certificate; verified that the policy is set to enable and ‘install the Zscaler SSL Certificate’ upon authentication within the Zapp. Checked to make sure the app was updated and the policy was the correct one. We have not tried manually installing the root certificate, as this would be a pain if we had to expand to the enterprise. Anything we can try to force the app to install or re-install the Zscaler certificate?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn1jCAA/zscaler-app-end-with-tls-error-while-login","lastmod":"2023-07-06T13:11:30.000Z","id":"0D54u00009evn1jCAA"} -->
## Zscaler App end with TLS error while login

- Source: https://community.zscaler.com/s/question/0D54u00009evn1jCAA/zscaler-app-end-with-tls-error-while-login
- Type: Q&A
- Posted: 2019-08-14T13:35:16.000Z
- Last activity: 2023-07-06T13:11:30.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I get below error in Zscaler app when i login in desktop. Issue exists only when i am in our corporate network not in open internet. is this known error ?

[image]

Regards
 Ganesh Krishnan
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn1kCAA/after-done-zscaler-certified-cloud-professional-internet-access-zccpia-elearning","lastmod":"2023-07-07T11:30:27.000Z","id":"0D54u00009evn1kCAA"} -->
## After done Zscaler Certified Cloud Professional - Internet Access (ZCCP-IA) elearning

- Source: https://community.zscaler.com/s/question/0D54u00009evn1kCAA/after-done-zscaler-certified-cloud-professional-internet-access-zccpia-elearning
- Type: Q&A
- Posted: 2019-08-10T18:18:54.000Z
- Last activity: 2023-07-07T11:30:27.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

hi team
 I did Zscaler Certified Cloud Professional - Internet Access (ZCCP-IA) eLearning
 after that i try to start Zscaler Certified Cloud Professional - Internet Access (ZCCP-IA) Lab
 but it is still showing pending below the snap for ref.
 hence could you guide me how can I will proceed the lab

[Capture]
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn1qCAA/zapp-ends-in-endpoint-fwav-error","lastmod":"2025-08-22T07:27:40.000Z","id":"0D54u00009evn1qCAA"} -->
## Zapp ends in Endpoint FW/AV error

- Source: https://community.zscaler.com/s/question/0D54u00009evn1qCAA/zapp-ends-in-endpoint-fwav-error
- Type: Q&A
- Posted: 2019-07-24T09:54:24.000Z
- Last activity: 2025-08-22T07:27:40.000Z
- Answers: 20
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

user facing issue Zapp is logged in. It stays connected for 30sec and get error related to Endpoint FW/AV error. Restart service, Repair, Reboot, Logout ends in same error. We open port 9000 via GPO. Gpupdate is successful in user machine. Still issue persists only with one user. ZSATray logs says “FIREWALL BLOCK ERROR?.

#NORMAL #INFO : ZIA state changed, From: TUNNEL_FORWARDING To: FIREWALL_BLOCK_ERROR

Regards
 Ganesh krishnan
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn1rCAA/zapp-notification-application-access-is-blocked-by-private-access-policy","lastmod":"2023-07-06T11:47:00.000Z","id":"0D54u00009evn1rCAA"} -->
## Zapp notification "application access is blocked by Private Access Policy"

- Source: https://community.zscaler.com/s/question/0D54u00009evn1rCAA/zapp-notification-application-access-is-blocked-by-private-access-policy
- Type: Q&A
- Posted: 2019-07-23T09:01:20.000Z
- Last activity: 2023-07-06T11:47:00.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi guys.

Since some time we get an annoying windows notification popup for “application access is blocked by Private Access Policy?. Of course I can disable windows notification for this app but somebody know the meant of the message?

[[Cattura3] Cattura3.PNG903×360 49.5 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O5byAAC)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn1tCAA/zscaler-app-shows-0bytes-in-and-out-and-user-not-able-to-access-internet","lastmod":"2023-07-06T13:15:21.000Z","id":"0D54u00009evn1tCAA"} -->
## Zscaler APP shows 0bytes in and out and user not able to access internet

- Source: https://community.zscaler.com/s/question/0D54u00009evn1tCAA/zscaler-app-shows-0bytes-in-and-out-and-user-not-able-to-access-internet
- Type: Q&A
- Posted: 2019-07-11T07:12:00.000Z
- Last activity: 2023-07-06T13:15:21.000Z
- Answers: 11
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Zscaler APP shows 0bytes in and out and user not able to access internet.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn1xCAA/generation-logs-zapp","lastmod":"2023-05-31T08:52:18.000Z","id":"0D54u00009evn1xCAA"} -->
## Generation logs, zapp

- Source: https://community.zscaler.com/s/question/0D54u00009evn1xCAA/generation-logs-zapp
- Type: Q&A
- Posted: 2019-07-02T08:12:05.000Z
- Last activity: 2023-05-31T08:52:18.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

hello,
 Currently i go through the zscaler proxy via the zapp and everything works alright !
 But when i access the zscaler console to see my logs, i can not see all the sites i access.
 Zscaler shows me some logs but not all.
 Does a person have an idea about this problem?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn1yCAA/zapp-enrolled-devices","lastmod":"2023-05-31T09:24:34.000Z","id":"0D54u00009evn1yCAA"} -->
## ZAPP, enrolled devices

- Source: https://community.zscaler.com/s/question/0D54u00009evn1yCAA/zapp-enrolled-devices
- Type: Q&A
- Posted: 2019-06-28T08:10:18.000Z
- Last activity: 2023-05-31T09:24:34.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello
 I use zia and zpa today through the zapp to forward my flow (external and internal), Everything works well I can see that my zapp has the status enabled
 but I can not find my account in Enrolled Devices !
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn20CAA/zapp-app-profile-pac-with-an-exception-to-forward-specific-url-to-internal-proxy","lastmod":"2023-05-31T09:24:45.000Z","id":"0D54u00009evn20CAA"} -->
## ZAPP: App Profile PAC with an exception to forward specific url to Internal Proxy

- Source: https://community.zscaler.com/s/question/0D54u00009evn20CAA/zapp-app-profile-pac-with-an-exception-to-forward-specific-url-to-internal-proxy
- Type: Q&A
- Posted: 2019-06-27T11:23:03.000Z
- Last activity: 2023-05-31T09:24:45.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I have scenaio where i need to forward specific url to our internal proxy server. when i added an exception in App profile (Tunnel Mode) like below, it forwards the traffic to the internal server but to the port 443 by default. I cannot change the listening port in my internal server.

**Forwarding profile PAC**
 if(shExpMatch(host, “[www.example.com](http://www.example.com)?)) {
 return “PROXY [internalA-proxy.domain.com:80](http://internalA-proxy.domain.com:80); PROXY [internalB-proxy.domain.com:80](http://internalB-proxy.domain.com:80); DIRECT?;
 }

I was suggested (by TAC) to use Forwarding profile PAC with similar exception which will send the encapsulated traffic (src to internal proxy:80) to Zapp and Add the internal proxy server in App PAC Exception list to send DIRECt. However this doesn’t work becasue Zapp still forwards the traffic to internal server in port 443

**APP Profile PAC**
 if(shExpMatch(host, “[internalA-proxy.domain.com](http://internalA-proxy.domain.com)?) ||
 shExpMatch(host, “[internalB-proxy.domain.com](http://internalB-proxy.domain.com)?)) {
 return “DIRECT?;
 }

Regards
 Ganesh Krishnan
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn22CAA/zapp-zia-traffic-to-be-allowed-through-non-httphttps-ports","lastmod":"2023-05-31T09:28:42.000Z","id":"0D54u00009evn22CAA"} -->
## ZApp & ZIA traffic to be allowed through non http/https ports

- Source: https://community.zscaler.com/s/question/0D54u00009evn22CAA/zapp-zia-traffic-to-be-allowed-through-non-httphttps-ports
- Type: Q&A
- Posted: 2019-06-24T16:33:57.000Z
- Last activity: 2023-05-31T09:28:42.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

I am trying to see if Zscaler can send the non http/https traffic (like SQL traffic - 1433 port) using the zscaler app through ZEN proxy to a cloud on internet and I came to know that this feature is going to be implemented soon.
 Is this implementation is under the radar ? or Will it be included in upcoming releases ?

Thanks

Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2CCAQ/zapp-server-security","lastmod":"2023-07-06T13:11:24.000Z","id":"0D54u00009evn2CCAQ"} -->
## ZAPP & Server Security

- Source: https://community.zscaler.com/s/question/0D54u00009evn2CCAQ/zapp-server-security
- Type: Q&A
- Posted: 2019-06-05T08:17:40.000Z
- Last activity: 2023-07-06T13:11:24.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

I have rolled out ZAPP across to my users for each endpoint desktop/laptops. We are using a hosted PAC file, and I understand the ZAPP uses the hosted pack file.

For my servers which are based in multiple locations, is there a way to secure my servers? Do I install the ZAPP or do I via group policy apply a PAC file?

I would like some servers to access the internet and some not. Out servers sit behind a Cisco Firewall.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2FCAQ/win10-unable-to-access-loginmicrosoftonlinecom-via-zapp","lastmod":"2023-07-06T12:58:32.000Z","id":"0D54u00009evn2FCAQ"} -->
## Win10 unable to access login.microsoftonline.com via Zapp

- Source: https://community.zscaler.com/s/question/0D54u00009evn2FCAQ/win10-unable-to-access-loginmicrosoftonlinecom-via-zapp
- Type: Q&A
- Posted: 2019-05-31T01:18:47.000Z
- Last activity: 2023-07-06T12:58:32.000Z
- Answers: 18
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi

I have logged a ticket with support (Case 803043). We are having an issue with Windows 10/Edge unable to access [login.microsoftonline.com](http://login.microsoftonline.com) when using Zapp.

Other browsers (IE/Chrome) are able to access [login.microsoftonline.com](http://login.microsoftonline.com) via Zapp.

Edge is able to get onto other websites via Zapp.

As edge is unable to access the above website, we are unable to configure Outlook CtR.

Ideas?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2GCAQ/app-profile-pac-doesnt-indentifies-the-internal-ip-exception","lastmod":"2023-07-06T13:15:38.000Z","id":"0D54u00009evn2GCAQ"} -->
## APP profile PAC doesn't indentifies the Internal IP Exception

- Source: https://community.zscaler.com/s/question/0D54u00009evn2GCAQ/app-profile-pac-doesnt-indentifies-the-internal-ip-exception
- Type: Q&A
- Posted: 2019-05-28T11:18:21.000Z
- Last activity: 2023-07-06T13:15:38.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I use Zapp in Tunnel Mode. We have exception for Internal IP in our App Profile PAC but it doesn’t sent the traffic DIRECT instead it send the traffic to ZEN. I could see logs for internal IP in WebInsight. I use same PAC file in my forwarding profile and i confirm my forwaridng profile PAC exception works well and it send the traffic direct, but partial traffic caught by APP Profile PAC and forward it to ZEN.

When i connect internal VPN, Forwarding profile exception works and traffic from browser uses VPN routes to reach internal server.

Whereas when i connect destination without VPN(in Office), Forwarding profile exception works, the traffic forwards to APP (by default: bcos of Tunnel Mode) and APP PAC doesn’t identifies Exception and forward the traffic to ZEN.
 So its there any specific syntax that must to used in APP profile PAC to exempt internal IP

/* If the URL is an internal IP address, send DIRECT */
 reip = /^\d+.\d+.\d+.\d+$/g;
 if (reip.test(host))
 {if (isInNet(host, “10.0.0.0?, “255.0.0.0?) ||
 … 
 return “DIRECT?;
 }

Regards
 Ganesh Krishnan
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2KCAQ/zapp-authentication-frequency","lastmod":"2023-05-31T09:28:41.000Z","id":"0D54u00009evn2KCAQ"} -->
## Zapp authentication frequency

- Source: https://community.zscaler.com/s/question/0D54u00009evn2KCAQ/zapp-authentication-frequency
- Type: Q&A
- Posted: 2019-05-23T15:26:40.000Z
- Last activity: 2023-05-31T09:28:41.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi, we are using Zapp on Windows 10 devices to forward traffic to Zscaler. We use SAML for provisioning and authenticating users against our Microsoft ADFS service.

We use a custom msi to provide SSO for the user using their integrated windows authentication credentials so they never have to enter creds into the Zscaler app.

The issue we have is that users are added to AD groups regularly which we have Zscaler URL and and cloud app rules for. currently to update the zscaler user database so rules work correctly we ask the user to logout of the Zapp to generate a new SAML assertion which updates the Zscaler user database with the user’s updated groups, however this doesn’t scale for a lot of users.

Best practice says to only authenticate once, if we changed this to daily or weekly for example does this work for Zapp users or does this authentication frequency setting only apply to web browsers, or is there another scalable way to regularly update the user database. Note we aren’t going to change from using SAML, ADFS or the Zscaler app. Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2SCAQ/uninstall-script-for-zapp","lastmod":"2023-05-31T09:28:45.000Z","id":"0D54u00009evn2SCAQ"} -->
## Uninstall script for Zapp

- Source: https://community.zscaler.com/s/question/0D54u00009evn2SCAQ/uninstall-script-for-zapp
- Type: Q&A
- Posted: 2019-05-17T12:52:02.000Z
- Last activity: 2023-05-31T09:28:45.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Everyone,

We recently started rolling out Zscaler in our environment. I have a VSA system that i can push out the installation package from. The issue is we used the .exe instead of .MSI. Does anyone have a script or could tell me what switch to use for my script so i can use the password for uninstall?

Thanks,

Justin
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2TCAQ/device-removal-pending-issue","lastmod":"2023-05-31T08:52:43.000Z","id":"0D54u00009evn2TCAQ"} -->
## Device removal Pending issue

- Source: https://community.zscaler.com/s/question/0D54u00009evn2TCAQ/device-removal-pending-issue
- Type: Q&A
- Posted: 2019-05-15T14:19:34.000Z
- Last activity: 2023-05-31T08:52:43.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,
 Can any one help me. I am not able to remove the devices with state “removal pending? from Zscaler app portal getting “ERROR! Access Denied. You do not have necessary permission to perform this operation.?

I am able to remove the device one by one after selecting the single device and through Force remove option in Zscaler App Registered Device Details.

Please let me know how I can remove multiple entries of Device removal Pending.

> Blockquote
>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2UCAQ/skype-screen-sharing-fails-with-zapp-tunnel-mode","lastmod":"2023-05-31T09:24:44.000Z","id":"0D54u00009evn2UCAQ"} -->
## Skype Screen Sharing fails with Zapp Tunnel mode

- Source: https://community.zscaler.com/s/question/0D54u00009evn2UCAQ/skype-screen-sharing-fails-with-zapp-tunnel-mode
- Type: Q&A
- Posted: 2019-05-15T04:30:10.000Z
- Last activity: 2023-05-31T09:24:44.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

My screen sharing start with Zapp in tunnel mode and fails within 3 to 5 seconds stating “There is trouble in sharing?. I suspect whether Zscaler firewall might block and UDP ports. But Screen sharng works fine with Zapp tunnel with local proxy and PAC file.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2bCAA/ios-strict-enforcement","lastmod":"2023-07-06T12:56:15.000Z","id":"0D54u00009evn2bCAA"} -->
## iOS strict enforcement

- Source: https://community.zscaler.com/s/question/0D54u00009evn2bCAA/ios-strict-enforcement
- Type: Q&A
- Posted: 2019-04-08T13:30:25.000Z
- Last activity: 2023-07-06T12:56:15.000Z
- Answers: 9
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are working on distributing the Zscaler app among our iOS users and running into some issues trying to figure out how strict enforcement works. I’ve looked through the 7/31 release notes here: [https://help.zscaler.com/z-app/zscaler-app-1.0.1-ios-july-31-2017-release-notes](https://help.zscaler.com/z-app/zscaler-app-1.0.1-ios-july-31-2017-release-notes) and using the sample .mobileconfig file, deployed the vpn settings through our MDM with strictenforcement set to 1. Once deployed, I see the vpn establish on the phone, and opening the zscaler app for the first time, a message is presented saying internet access is blocked until you sign in, however it is not actually blocked. You can browse just like normal. I’m wondering if something has changed in iOS since these notes were released. I haven’t been able to find any more recent documentation…I’ve played around with globalproxy as well, but haven’t had satisfactory results there thus far.

TLDR: we want to block all web traffic on iPhones until users are signed into the Zapp… how do you guys accomplish this?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2dCAA/zscaler-and-vpn-client","lastmod":"2023-05-31T08:52:56.000Z","id":"0D54u00009evn2dCAA"} -->
## Zscaler and vpn client

- Source: https://community.zscaler.com/s/question/0D54u00009evn2dCAA/zscaler-and-vpn-client
- Type: Q&A
- Posted: 2019-04-03T08:09:44.000Z
- Last activity: 2023-05-31T08:52:56.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

hi im using a zscaler app and a vpn client on my pc.
 i want the zscaler client to be turned off when i turn on the vpn client (internal traffic)
 but the problem is that zapp doesnt turn off, instead just shows an error when i turn on my vpn.
 the vpn (works in full tunnel mode) doesnt show an error but my pc cannot connect to the internet.

can you tell me what the flow of traffic is like when the above happens?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2hCAA/zscaler-app-causes-no-internet-access-exclamation-mark","lastmod":"2023-07-06T13:16:16.000Z","id":"0D54u00009evn2hCAA"} -->
## Zscaler APP causes No Internet Access exclamation mark

- Source: https://community.zscaler.com/s/question/0D54u00009evn2hCAA/zscaler-app-causes-no-internet-access-exclamation-mark
- Type: Q&A
- Posted: 2019-03-22T11:26:27.000Z
- Last activity: 2023-07-06T13:16:16.000Z
- Answers: 30
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

We are using the Zscaler app on our laptops, just recently we have noticed that the machines can surf and get to the internet fine, even though windows is saying no internet access, but this is having a knock on affect for outlook as it relies on the machine thinking it should have internet access.
 If we uninstall the app, and use the normal pac file in a browser it works fine, soon as the app goes on, the error is back, but it only happens when on the local lan, if we put the traffic through the wifi the exclamation mark goes and all is well.
 Double checked IP’S on the firewall and all the app ones are in there and allowed.
 Anybody experienced anything similar ?
 Thanks
 Gary
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2kCAA/android-zapp-on-chromebooks-chrome-os","lastmod":"2023-06-05T21:49:05.000Z","id":"0D54u00009evn2kCAA"} -->
## Android ZApp on Chromebooks / Chrome OS

- Source: https://community.zscaler.com/s/question/0D54u00009evn2kCAA/android-zapp-on-chromebooks-chrome-os
- Type: Q&A
- Posted: 2019-03-13T14:45:57.000Z
- Last activity: 2023-06-05T21:49:05.000Z
- Answers: 2
- Likes: 8
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Android is a supported operating system for the Zscaler App. Some Chrome OS devices support native Android applications, and in these instances, the Zscaler App can be installed and will forward traffic for other applications on that device. As the Android operating system running on these devices differs slightly from a real physical Android device, it’s recommended to test specific applications to ensure they can run in this environment.

There are roughly three categories of how Zscaler works on Chromebooks:

***Early Chromebooks with no Android support***
 ZIA through PAC file only. Deploy a PAC to the browser, and users traffic will go through. Of course, unless it’s locked down the user can change/disable the proxy settings

***Chromebooks with sandboxed Android support***
 Essentially running an Android emulator, but completely sandboxed from ChromeOS. Traffic generated in the Android VM was separated from ChromeOS traffic.
 You can install Android Z App in this Android VM, but it will only capture traffic from Android apps, not Chrome OS apps.

***Chromebooks with full Android support***
 Android app traffic is no longer separated from ChromeOS app traffic.
 You can install Android Z App into Android, and Chrome OS browser traffic will flow through the VPN plugin we install. This means ZIA + ZPA will work for Chrome OS browser tabs as long as the Android Zscaler app is running.

In field testing of the integrated Android support, we’ve seen that it works for ZPA not only for web apps, but also for SSH and RDP…

Some useful resources and notes:

- Google Play store / Android app support was introduced in Chrome OS starting in the developer channel with M53 (May 2016) for certain devices - see [https://blog.google/products/chromebooks/the-google-play-store-coming-to/](https://blog.google/products/chromebooks/the-google-play-store-coming-to/) - and in the stable channel starting with build 59 (June 2017).
- Chrome OS only supports Android apps on specific hardware: [https://www.chromium.org/chromium-os/chrome-os-systems-supporting-android-apps](https://www.chromium.org/chromium-os/chrome-os-systems-supporting-android-apps)
- Devices that are managed centrally can be configured to force install apps. This is all done via Google admin console. Managed devices may have policy set in Google admin console to either not allow installation of Android apps, or whitelist which apps can be installed. If you have supported hardware on the above list and do not see Google Play Store on the Chromebook, you need to configure the policy to allow it in the Google admin console.
- For unmanaged devices, here are the instructions to enable Android app installation: [https://support.google.com/chromebook/answer/7021273](https://support.google.com/chromebook/answer/7021273)

Regards,
 Lisa
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2lCAA/support-for-certificatebased-authentication-with-zapp","lastmod":"2023-07-06T12:57:51.000Z","id":"0D54u00009evn2lCAA"} -->
## Support for Certificate-based authentication with Zapp

- Source: https://community.zscaler.com/s/question/0D54u00009evn2lCAA/support-for-certificatebased-authentication-with-zapp
- Type: Q&A
- Posted: 2019-03-13T10:59:33.000Z
- Last activity: 2023-07-06T12:57:51.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Does ZScaler support cert-based authentication with Zapp?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2mCAA/zscaler-app-sso-with-azure-ad","lastmod":"2023-05-31T08:53:07.000Z","id":"0D54u00009evn2mCAA"} -->
## Zscaler App SSO with Azure AD

- Source: https://community.zscaler.com/s/question/0D54u00009evn2mCAA/zscaler-app-sso-with-azure-ad
- Type: Q&A
- Posted: 2019-03-12T19:25:53.000Z
- Last activity: 2023-05-31T08:53:07.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

[https://help.zscaler.com/zia/saml-scim-configuration-example-microsoft-azure-active-directory](https://help.zscaler.com/zia/saml-scim-configuration-example-microsoft-azure-active-directory)

I’ve followed the steps on this page and I’ve configured SSO in the Azure portal as well as Zscaler portal. it “works? in that if you enter an email address into the zscaler app you can then click login and it will sign you in without a password.

However, when I used ADFS there was no need to even enter an email address. It would just have a login button and the whole thing was seamless.

What am I missing? Why wouldn’t it be completely seamless. I’ve added the Azure sign in url to intranet zone on our pcs and enabled the “allow script to update address bar? feature that Microsoft recommends. I just want the same experience where a pc that is currently connected to the corporate network will not require an email or password to sign on.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2rCAA/zapp-and-vpn-client","lastmod":"2023-07-07T11:31:18.000Z","id":"0D54u00009evn2rCAA"} -->
## ZApp and VPN client

- Source: https://community.zscaler.com/s/question/0D54u00009evn2rCAA/zapp-and-vpn-client
- Type: Q&A
- Posted: 2019-03-08T05:50:55.000Z
- Last activity: 2023-07-07T11:31:18.000Z
- Answers: 12
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I have a SonicWALL VPN client on my device and downloaded the Zscaler App.
 When I turn on the VPN client, the Zscaler App status says “Connection Error Off-trusted network?.

As for settings, when the ZApp recognizes the “trusted network? and “VPN trusted network? (under certain DNS), the ZApp is supposed to be turned off. Whereas, when in “Off trusted network? the ZApp is supposed to turn on.
 But it seems that the VPN client is in full-tunnel mode, thus all traffic would be defined as “trusted network? even when it’s really supposed to be" off trusted network"

What do i need to set up in order to make it work right?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2vCAA/push-zscaler-cert-with-zscaler-app-for-ios-devices-wo-mdm","lastmod":"2023-05-31T08:53:13.000Z","id":"0D54u00009evn2vCAA"} -->
## Push ZScaler cert with ZScaler app for iOS devices w/o MDM

- Source: https://community.zscaler.com/s/question/0D54u00009evn2vCAA/push-zscaler-cert-with-zscaler-app-for-ios-devices-wo-mdm
- Type: Q&A
- Posted: 2019-02-27T12:17:21.000Z
- Last activity: 2023-05-31T08:53:13.000Z
- Answers: 9
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is there an option to push ZScaler cert with ZScaler app for iOS devices (iPad, iPhone) w/o MDM?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2xCAA/zapp-no-internet-access-in-ie-ff-and-chrome-work","lastmod":"2023-05-31T09:29:18.000Z","id":"0D54u00009evn2xCAA"} -->
## Z-APP - No Internet access in IE (FF and Chrome work)

- Source: https://community.zscaler.com/s/question/0D54u00009evn2xCAA/zapp-no-internet-access-in-ie-ff-and-chrome-work
- Type: Q&A
- Posted: 2019-02-18T15:39:37.000Z
- Last activity: 2023-05-31T09:29:18.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are testing ZAPP in our environment and since installing version 1.4.3.1 Z-APP seems o break internet explorer.

What i mean is that IE can access internal sites but cannot access the internet (google, [ip.zscaler.com](http://ip.zscaler.com) etc). stange thing is Firefox and Chrome work fine. I have opened a case with support but they are not being too helpful.

Removing the app and reinstalling doesnt help either.

Has anyone else encountered this and if so how did you resolve it?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn32CAA/zscaler-app-not-working","lastmod":"2023-05-31T08:53:22.000Z","id":"0D54u00009evn32CAA"} -->
## Zscaler App not working

- Source: https://community.zscaler.com/s/question/0D54u00009evn32CAA/zscaler-app-not-working
- Type: Q&A
- Posted: 2019-01-29T06:12:00.000Z
- Last activity: 2023-05-31T08:53:22.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,
 When trying to connect Zapp from Internal Network on Android phones . I get SSL ERROR Root Certificate is invalid and SAML URL Login Page was directed as well . Installed New Root CA as well but no luck.

Tried Bypassing SAML by explicit browser which worked on Browser but not via ZAPP.

Any help would be appreciated.

Regards
 santosh
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn39CAA/zscaler-app-seamless-adfs-auth","lastmod":"2023-05-31T08:53:36.000Z","id":"0D54u00009evn39CAA"} -->
## zScaler App Seamless ADFS Auth

- Source: https://community.zscaler.com/s/question/0D54u00009evn39CAA/zscaler-app-seamless-adfs-auth
- Type: Q&A
- Posted: 2018-12-18T01:55:56.000Z
- Last activity: 2023-05-31T08:53:36.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

I got the ADFS seamless auth working for internal organizational users. The off-network users (employees taking their laptops home) still need to be authenticated and enforced via zScaler.

What is the best way to make zScaler App authenticate seamlessly with ADFS for off-network users?

I noticed that if the laptop is on the network and zAP authenticates, then it works off-network as well unless the user explicitly logs out of the app. Restart of the app still retains the credential information.

Is that expected behaviour?

Regards
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3ECAQ/zapp-with-citrix-vpn","lastmod":"2023-07-06T13:18:04.000Z","id":"0D54u00009evn3ECAQ"} -->
## Zapp with Citrix VPN

- Source: https://community.zscaler.com/s/question/0D54u00009evn3ECAQ/zapp-with-citrix-vpn
- Type: Q&A
- Posted: 2018-11-28T08:01:30.000Z
- Last activity: 2023-07-06T13:18:04.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Team,
 One of our customer using Cirtix VPN client. When they connect the VPN client and ZAPP, the ZAPP is not recognizing the network as VPN trusted network.

Is there any criteria / keyword required to be available in the VPN adapter ?
 Share your experience. and suggestions.

Regards / Ramesh M
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3JCAQ/zapp-adfs-transparent","lastmod":"2023-05-31T08:53:47.000Z","id":"0D54u00009evn3JCAQ"} -->
## Zapp adfs transparent

- Source: https://community.zscaler.com/s/question/0D54u00009evn3JCAQ/zapp-adfs-transparent
- Type: Q&A
- Posted: 2018-11-21T14:41:29.000Z
- Last activity: 2023-05-31T08:53:47.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I called in to support yesterday and have an open ticket. The support person didn’t seem to know answers to some of my questions so I want to confirm here…

1. We want to use the zscaler app universally rather than browser pac file. So knowing that, how can we force people to have to authenticate before they can browse the web? Currently you could just ignore the app and not ever sign in and your browsing seems unaffected (which makes sense as nothing is sending your traffic to zscaler)
2. We are using ADFS as authentication method. Currently if you try to sign in to the app, what happens is that you are redirected within the app to the ADFS portal and then a pop up box shows up and you have to put in your credentials. I was under the impression that this is not right and it should be automatic sign in. The support person told me this is normal behavior and you have to sign in once to ADFS and then you are OK after that. I don’t see what the point of using ADFS over the directory sign in method if this is the case.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3KCAQ/accessing-website-before-login","lastmod":"2023-05-31T08:53:47.000Z","id":"0D54u00009evn3KCAQ"} -->
## Accessing website before login

- Source: https://community.zscaler.com/s/question/0D54u00009evn3KCAQ/accessing-website-before-login
- Type: Q&A
- Posted: 2018-11-21T13:43:17.000Z
- Last activity: 2023-05-31T08:53:47.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Any idea how to get this to work.

the users have a link on the windows login screen to reset their passwords, this is is a web link

software is [https://hitachi-id.com/password-manager/features/self-service-password-reset.html](https://hitachi-id.com/password-manager/features/self-service-password-reset.html)

when a user clicks on this link ZAPP loads and asks for a username behind it the website loads and errors as this is all done before a user logins in then i assume there are no polices loaded. what is needed is somehow to add this as a type of captive portal.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3QCAQ/user-cannot-authenticat-in-zapp","lastmod":"2023-05-31T09:30:16.000Z","id":"0D54u00009evn3QCAQ"} -->
## User cannot authenticat in Zapp

- Source: https://community.zscaler.com/s/question/0D54u00009evn3QCAQ/user-cannot-authenticat-in-zapp
- Type: Q&A
- Posted: 2018-11-15T15:34:55.000Z
- Last activity: 2023-05-31T09:30:16.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

i have some users that are not able to connect with zapp Zscaler App Error, User not Subscribed to Zscaler Service

i have licences for,

regards
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3VCAQ/zscaler-app-fiddler-support","lastmod":"2025-08-22T07:27:46.000Z","id":"0D54u00009evn3VCAQ"} -->
## Zscaler App - Fiddler support

- Source: https://community.zscaler.com/s/question/0D54u00009evn3VCAQ/zscaler-app-fiddler-support
- Type: Q&A
- Posted: 2018-11-09T15:23:33.000Z
- Last activity: 2025-08-22T07:27:46.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Has anyone ever had any success getting Fiddler to work with the Zscaler App enabled?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3cCAA/ipsec-tunnel-to-zscaler-with-zapp-on-captive-portal-detected-disabling","lastmod":"2023-05-31T08:54:03.000Z","id":"0D54u00009evn3cCAA"} -->
## IPSec Tunnel to Zscaler with Zapp On - Captive Portal Detected Disabling

- Source: https://community.zscaler.com/s/question/0D54u00009evn3cCAA/ipsec-tunnel-to-zscaler-with-zapp-on-captive-portal-detected-disabling
- Type: Q&A
- Posted: 2018-10-25T17:42:43.000Z
- Last activity: 2023-05-31T08:54:03.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

I am currently trialing SD-WAN which will allow branch sites to use their local Internet bandwidth to connect to Zscaler as the default route. I have resilient IPsec tunnels configured to London and Amsterdam which are connected.

I have a laptop heavy estate which is Windows 10 using Zapp 1.4.0 to enable protection off-network, VPN (PAN Global Protect) and on-network. Zapp is configured for tunnel with local proxy mode for each network profile as was best practice. Zapp is on on-network because it is a no-default route environment so clients are routed to specific Zscaler ZEN ranges and NAT’d behind the DC firewall. ADFS is used as authentication.

When i connect my laptop to the SD-WAN site, Zapp prompts that a Captive Portal has been detected and disables the app. When i try and access the Internet i can only get to certain URLs and my traffic appears in the logs as the site IP range not the user. I was hoping that by leaving Zapp on on-trusted network it would authenticate the user traffic transparently and effectively allow me to do a tunnel-within-a-tunnel. Any ideas why i am getting the captive portal issue?

Notes

- Tried turning off authentication at the SD-WAN site, made no difference
- Turning Zapp off on-network is not an option as it will break existing users.
- Adding users to a group is not scalable as the users will float between non SD-WAN and SD-WAN sites.
- Tried upgrading my Zapp client to 1.4.2 to see if it was a bug, no change.
- I can’t think of any logic for a forwarding statement as there is no local DNS so DNS server, DNS domain and host/IP resolution will be the same at any site.

Any ideas on how to resolve this or a better way to achieve this with the constraints?

thanks
 Alex
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3dCAA/zscaler-app-connection-logic","lastmod":"2023-05-31T08:54:03.000Z","id":"0D54u00009evn3dCAA"} -->
## Zscaler App connection logic

- Source: https://community.zscaler.com/s/question/0D54u00009evn3dCAA/zscaler-app-connection-logic
- Type: Q&A
- Posted: 2018-10-25T11:31:31.000Z
- Last activity: 2023-05-31T08:54:03.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

If a user is on a site and using Z-APP, the GRE tunnels on the site are connected to a Datacentree that is listed as “Not in gateway?, is there some intelligence that recognises this or does it just connect to the DC’s as per the geo located $gateway variable.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3hCAA/zscaler-app-with-check-point-e80-endpoint-sechrity","lastmod":"2023-05-31T08:54:09.000Z","id":"0D54u00009evn3hCAA"} -->
## ZScaler App with Check Point E80 Endpoint Sechrity

- Source: https://community.zscaler.com/s/question/0D54u00009evn3hCAA/zscaler-app-with-check-point-e80-endpoint-sechrity
- Type: Q&A
- Posted: 2018-10-18T08:41:27.000Z
- Last activity: 2023-05-31T08:54:09.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

has anyone experience with zapp and check point endpoint security? if we connect via VPN Zapp show us the error: “Endpoint FW/AV Error?

We don’t use the FW Blade from Check Point on the Clients.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3lCAA/outlook-disconnection-over-zapp","lastmod":"2023-07-06T13:13:35.000Z","id":"0D54u00009evn3lCAA"} -->
## Outlook Disconnection Over ZApp

- Source: https://community.zscaler.com/s/question/0D54u00009evn3lCAA/outlook-disconnection-over-zapp
- Type: Q&A
- Posted: 2018-10-12T21:58:28.000Z
- Last activity: 2023-07-06T13:13:35.000Z
- Answers: 31
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,
 We have recently deployed Zscaler App to multiple locations. And have started receiving complains of outlook disconnection, stuck on trying to connect etc when ZApp is ON. The same works when it’s turned OFF. We have the Office 365 one click enabled, Common Office 365 Applications exempted from SSL inspection.
 Could someone advise what we can perform/troubleshoot to take care of this.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3oCAA/google-404-in-internet-explorer-when-using-zscaler-app","lastmod":"2023-05-31T08:54:17.000Z","id":"0D54u00009evn3oCAA"} -->
## Google 404 in Internet explorer when using Zscaler App

- Source: https://community.zscaler.com/s/question/0D54u00009evn3oCAA/google-404-in-internet-explorer-when-using-zscaler-app
- Type: Q&A
- Posted: 2018-10-08T11:25:20.000Z
- Last activity: 2023-05-31T08:54:17.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

When entering an address into the IE address bar a Google 404 page is returned.
 This happens for valid addresses such as: [hotmail.com](http://hotmail.com) or [msn.co.uk](http://msn.co.uk).
 This is an intermittent problem and if searching Google for the same sites and clicking on the same URLs the loads.

we are using the Zscaler app with tunnel mode and packet based tunnel driver type.

has anyone else had this issue?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3qCAA/internet-access-after-zapp-user-authenticate","lastmod":"2023-07-07T11:32:21.000Z","id":"0D54u00009evn3qCAA"} -->
## Internet Access after ZAPP user authenticate

- Source: https://community.zscaler.com/s/question/0D54u00009evn3qCAA/internet-access-after-zapp-user-authenticate
- Type: Q&A
- Posted: 2018-10-03T07:10:11.000Z
- Last activity: 2023-07-07T11:32:21.000Z
- Answers: 13
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Team,
 One of customer deployment, customer can access internet only after he authenticate in zapp.
 Customer corporate IT team is pushing the zapp through SCCM or some other tool.
 Now the customer is expecting to pop up the notification on end user machine if he doesn’t login the zapp before access internet.

Do we have such authentication notification ? Any other way to notify the customer when is start access internet.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn3yCAA/zscaler-for-iphone","lastmod":"2023-05-31T08:54:34.000Z","id":"0D54u00009evn3yCAA"} -->
## Zscaler for iPhone

- Source: https://community.zscaler.com/s/question/0D54u00009evn3yCAA/zscaler-for-iphone
- Type: Q&A
- Posted: 2018-09-05T14:10:29.000Z
- Last activity: 2023-05-31T08:54:34.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is anyone using Zscaler App on iPhones? How is your experience? We are having a few issues:

1. Zscaler app does not check in to get policy updates. App needs to be opened by the user to get checked-in.
2. Zscaler app is not updating from within the app.
3. Zscaler is blocking the sign-in connection to iCloud.

Thanks,
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4ICAQ/zscaler-app-traffic-forwarding","lastmod":"2023-07-07T11:33:19.000Z","id":"0D54u00009evn4ICAQ"} -->
## Zscaler APP Traffic forwarding

- Source: https://community.zscaler.com/s/question/0D54u00009evn4ICAQ/zscaler-app-traffic-forwarding
- Type: Q&A
- Posted: 2018-08-16T01:13:21.000Z
- Last activity: 2023-07-07T11:33:19.000Z
- Answers: 11
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I have the users in branch who are mobile users (mostly)
 My branch will be configured for local internet break out.(GRE / IPSec)
 Since the users will be mobile planning to have zAPP installed (so that even if they connect via non corporate network their traffic will be protected via Zscaler)

I have the below questions.

1. When the users is connected to the corporate network, how will the traffic flow ? - It should take the GRE/IPSec tunnel to reach Zscaler via local breakout. Is this possible ? - ZApp should be used only when connecting to untrusted network.
2. Also for certain SAAS application like Sales force, it should not go via Zscaler, instead it should forward to our existing proxy (No internet breakout) - Is this possible ?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4NCAQ/search-domain-dns-zapp","lastmod":"2023-05-31T08:54:46.000Z","id":"0D54u00009evn4NCAQ"} -->
## Search domain dns Zapp

- Source: https://community.zscaler.com/s/question/0D54u00009evn4NCAQ/search-domain-dns-zapp
- Type: Q&A
- Posted: 2018-08-06T14:49:06.000Z
- Last activity: 2023-05-31T08:54:46.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

could you please tell me what does mean “Search domain? in DNS condition in Zapp,

should i put a local domain, that Zapp can resolve when it is on trusted network

did Zapp send DNS request of Public domain ?

regards
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4QCAQ/zapp-off-trusted-network","lastmod":"2023-07-06T13:11:44.000Z","id":"0D54u00009evn4QCAQ"} -->
## Zapp OFF TRUSTED NETWORK

- Source: https://community.zscaler.com/s/question/0D54u00009evn4QCAQ/zapp-off-trusted-network
- Type: Q&A
- Posted: 2018-08-02T14:02:45.000Z
- Last activity: 2023-07-06T13:11:44.000Z
- Answers: 12
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi,

i configured my forwarding profile in tunnel with local proxy mode, and set my condition to DNS server

the issue is that the Zapp display that i am on off trusted network when i am in

is this some reason for that

regards
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4SCAQ/zapp-tunnel-local-proxy-not-catching-office-365-installationactivation-flows","lastmod":"2023-07-06T13:19:57.000Z","id":"0D54u00009evn4SCAQ"} -->
## Z-App (Tunnel Local Proxy) not catching Office 365 installation/activation flows?

- Source: https://community.zscaler.com/s/question/0D54u00009evn4SCAQ/zapp-tunnel-local-proxy-not-catching-office-365-installationactivation-flows
- Type: Q&A
- Posted: 2018-07-30T17:57:33.000Z
- Last activity: 2023-07-06T13:19:57.000Z
- Answers: 17
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

I do have an issue running Z-app in tunnel with local proxy :

In some country (I don’t know why), the office 365 installer and/or the office 365 activation does not work (network failed).

By looking into a packet capture, it looks like it is not following the settings in the PAC files (nothing set about Microsoft’s servers there).

What is even more strange is that it is working perfectly in other countries (like in France).

The network configuration is the same worldwide : the only way to access internet is to reach a ZEN.

Does anyone already met such issue?

The countries impacted are the Panama & Italy.

There is already a ticket opened at Zscaler Support (ID 542992), but it seems that this issue is stuck for a long time now.

Regards,
 Vincent.[quote=“vgo, post:1, topic:2827, full:true?]
 Hello,

I do have an issue running Z-app in tunnel with local proxy :

In some country (I don’t know why), the office 365 installer and/or the office 365 activation does not work (network failed).

By looking into a packet capture, it looks like it is not following the settings in the PAC files (nothing set about Microsoft’s servers there).

What is even more strange is that it is working perfectly in other countries (like in France).

The network configuration is the same worldwide : the only way to access internet is to reach a ZEN.

Does anyone already met such issue?

The countries impacted are the Panama & Italy.

There is already a ticket opened at Zscaler Support (ID 542992), but it seems that this issue is stuck for a long time now.

Regards,
 Vincent.
 [/quote]
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4TCAQ/zapp-stops-forwarding-traffic","lastmod":"2024-09-17T20:34:46.000Z","id":"0D54u00009evn4TCAQ"} -->
## Z-App stops forwarding traffic

- Source: https://community.zscaler.com/s/question/0D54u00009evn4TCAQ/zapp-stops-forwarding-traffic
- Type: Q&A
- Posted: 2018-07-30T10:53:20.000Z
- Last activity: 2024-09-17T20:34:46.000Z
- Answers: 7
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello.

I do have a support case open for this but wanted to see if anyone else has experienced this issue.

We find that Z-App intermittently stops forwarding traffic to ZIA. We are running Z-App in Tunnel mode with Windows Filter Drive (Packet Filter Based). The cause is unknown right now but when it happens the bytes sent and received in Z-App stop incrementing. The user has to log out of Z-App and then log back in to resolve the issue.

We were only made aware of the issue because we still have our old proxy servers in place with WCCP configured whilst we continue to fully migrate our estate to ZIA. On the old proxy servers we added a rule that displays a notification to the user if they hit the old proxies but they’re a member of the new Zscaler Users AD group.

Is it just us? I did ask the person working the support ticket to check for similar cases but apparently there are none.

Z-App version 1.4.0.188.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4YCAQ/windows-10-builds-zscaler-app-zapp-support","lastmod":"2023-05-31T08:54:52.000Z","id":"0D54u00009evn4YCAQ"} -->
## Windows 10 Builds + Zscaler App (Z-App) Support

- Source: https://community.zscaler.com/s/question/0D54u00009evn4YCAQ/windows-10-builds-zscaler-app-zapp-support
- Type: Q&A
- Posted: 2018-07-24T20:44:04.000Z
- Last activity: 2023-05-31T08:54:52.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Our organization is beginning to adopt “Windows 10 as a Service? and will start to test out newer builds on a more frequent basis as Microsoft releases them. Right now we are moving to test Windows 10 Feature Update 1803.

Does anyone know how we determine the minimum versions of Zscaler App required to support these builds? Or where do we determine if Zscaler needs to release an updated version of Zscaler App in order to support the Windows 10 update?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4eCAA/zscaler-app-authentication","lastmod":"2023-07-06T13:13:12.000Z","id":"0D54u00009evn4eCAA"} -->
## Zscaler APP Authentication

- Source: https://community.zscaler.com/s/question/0D54u00009evn4eCAA/zscaler-app-authentication
- Type: Q&A
- Posted: 2018-07-06T06:32:18.000Z
- Last activity: 2023-07-06T13:13:12.000Z
- Answers: 18
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I was testing Zscaler App with ADFS authentication.
 It works fine. But the issue i found is that, even when the PC in which the Zscaler App is installed reboots when the PC comes back again it is not prompted for the authentication.
 Would like to know if this the normal behaviour. Once the PC reboots the Zscaler APP should reauthenticate.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4jCAA/using-zscaler-app-as-idp-and-adfs","lastmod":"2023-05-31T09:07:48.000Z","id":"0D54u00009evn4jCAA"} -->
## Using Zscaler App as IDP and ADFS

- Source: https://community.zscaler.com/s/question/0D54u00009evn4jCAA/using-zscaler-app-as-idp-and-adfs
- Type: Q&A
- Posted: 2018-07-02T01:38:18.000Z
- Last activity: 2023-05-31T09:07:48.000Z
- Answers: 4
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I was if it is possible to try out Zscaler App on some computers without modifying our current setup? We’re using a PAC file deployed to all computers which allows us to enforce web browsing policies on all browser activity from port 80/443.

I would like to enable Zscaler App as the IDP without changing our current IDP, which is ADFS.

Basically what I’m after is a good strategy on testing out Zscaler App on 1 computer to start without disrupting any of the other systems on our network.

What I’m hoping to gain from Zscaler App is better identity association and improved security policy enforcement. Our biggest issue is not having a username ([random.person@company.com](mailto:random.person@company.com)) when 80/443 is detecting outside web browsers.

Any help is greatly appreciated!

Thank you!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4mCAA/zapp-using-adfs-saml-has-massive-delay-during-first-time-authentication","lastmod":"2023-05-31T08:55:09.000Z","id":"0D54u00009evn4mCAA"} -->
## Zapp using ADFS / SAML has massive delay during first time authentication

- Source: https://community.zscaler.com/s/question/0D54u00009evn4mCAA/zapp-using-adfs-saml-has-massive-delay-during-first-time-authentication
- Type: Q&A
- Posted: 2018-06-08T09:59:24.000Z
- Last activity: 2023-05-31T08:55:09.000Z
- Answers: 9
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are using ADFS/SAML to authenticate and provision users for Zscaler. We use a mix of Zscaler App for laptops and Pac File only for desktops. Everything is working fine, in that users can authenticate and use the Zscaler service. However new laptop users using Zapp, the first time they login to their device it takes Zapp 3-5 minutes to authenticate against ADFS. It eventually logs the user in successfully but concerned over the delay. If I logout of Zapp and log back in authentication is instant so it is only the first time login. Pac File only users authenticate instantly so there can’t be anything wrong with ADFS it is specific to Zapp.

To add to my confusion, when on the LAN the Zapp forwarding profile is set to tunnel with local proxy mode. If I change this to tunnel mode, the user is authenticated instantly. So there is something different between these Zapp forwarding modes which impact SAML authentication. I have a PAC file exception for our ADFS URL so not sure what it could be. I can’t change the forwarding mode to tunnel as it causes issues and breaks certain proxy chaining we are doing so need to resolve the issue.

I have opened a case but have had limited feedback on the Wireshark traces I provided.

Anyone else experienced and resolved this issue?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn4uCAA/zapp-forced-enablement","lastmod":"2023-05-31T08:55:18.000Z","id":"0D54u00009evn4uCAA"} -->
## Zapp forced enablement

- Source: https://community.zscaler.com/s/question/0D54u00009evn4uCAA/zapp-forced-enablement
- Type: Q&A
- Posted: 2018-05-23T15:22:31.000Z
- Last activity: 2023-05-31T08:55:18.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Have rolled out over 5,000 zapp, used strictenforcement for rollout but the disable/logout password has become too widely known. Any way to tell how many users have logged out? After changing the password, is there any way to force enable deployed zapp?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn59CAA/deprovision-users-while-using-zapp-with-saml-auto-provisioning","lastmod":"2023-07-06T11:47:22.000Z","id":"0D54u00009evn59CAA"} -->
## De-provision users while using ZAPP with SAML Auto Provisioning

- Source: https://community.zscaler.com/s/question/0D54u00009evn59CAA/deprovision-users-while-using-zapp-with-saml-auto-provisioning
- Type: Q&A
- Posted: 2018-04-20T09:44:02.000Z
- Last activity: 2023-07-06T11:47:22.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

**Requirement:**

- De-provision/remove users who have left customer organization from Zscaler DB.

**Solution:**

**Step 1: Delete device from Zscaler Mobile portal**

- Steps are mentioned below:
  1. Obtain the username which needs to be logged out.
  2. Login to Zscaler admin UI (admin..net) and then browse to Policy >> Zscaler App Portal.
  3. Once in Zscaler App Portal goto Enrolled Devices and search with the username.

[[image] image1040×208 24 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O6gzAAC)

1. Now click on extreme right checkbox and click “Remove Checked Devices?.

[[image] image1037×213 26.1 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O6cIAAS)

1. Now the policy status would move to “Device Removal Pending?.

[[image] image1034×213 25.7 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O863AAC)

**Step 2: Delete users from Zscaler admin UI**

- Steps are mentioned below:
  1. Login to Zscaler admin UI (admin..net) and then browse to Administration >> User Management
  2. Search for the user.

[[image] image1108×193 15.8 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O6l4AAC)

[[image] image597×622 18 KB](https://community.zscaler.com/zenith/servlet/servlet.FileDownload?file=0154u000009O8KsAAK)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn5ECAQ/new-zapp-version","lastmod":"2023-05-31T08:55:54.000Z","id":"0D54u00009evn5ECAQ"} -->
## New z-app version

- Source: https://community.zscaler.com/s/question/0D54u00009evn5ECAQ/new-zapp-version
- Type: Q&A
- Posted: 2018-03-11T07:00:08.000Z
- Last activity: 2023-05-31T08:55:54.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Zscaler
 Could you please update on the latest best practice with the new Zscaler app 1.4?
 So far we used 1.3.1 with the LWD and tunnel mode.

What would be suggested for existing customers? What would be the flow to upgrade existing devices?
 Do you do POCs with 1.4 and LWD and tunnel?

Thx
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn5TCAQ/heads-up-zscaler-app-in-local-proxy-mode-and-cisco-anyconnect-vpn-client","lastmod":"2023-05-31T09:07:44.000Z","id":"0D54u00009evn5TCAQ"} -->
## Heads up: Zscaler App in local proxy mode and Cisco AnyConnect VPN Client

- Source: https://community.zscaler.com/s/question/0D54u00009evn5TCAQ/heads-up-zscaler-app-in-local-proxy-mode-and-cisco-anyconnect-vpn-client
- Type: Q&A
- Posted: 2018-01-19T00:38:46.000Z
- Last activity: 2023-05-31T09:07:44.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

In case you have Zscaler App running in Tunnel With Local Proxy mode, and upon launching Cisco AnyConnect receive the error that AnyConnect will not connect through a local proxy, support for local proxy must be enabled on the AnyConnect VPN Profile:

Cisco
##### [Cisco AnyConnect Secure Mobility Client Administrator Guide, Release 4.0 -...](https://www.cisco.com/c/en/us/td/docs/security/vpn_client/anyconnect/anyconnect40/administration/guide/b_AnyConnect_Administrator_Guide_4-0/configure-vpn.html#ID-1428-0000032f)

Configure VPN Access

**Allow a Local Proxy Connection**
 Procedure
 Step 1 Open the VPN Profile Editor and choose Preferences (Part 2) from the navigation pane.
 Step 2 Select (default) or unselect Allow Local Proxy Connections. Local proxy is disabled by default.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn5VCAQ/zscaler-app-sso","lastmod":"2023-07-06T13:22:43.000Z","id":"0D54u00009evn5VCAQ"} -->
## Zscaler App SSO

- Source: https://community.zscaler.com/s/question/0D54u00009evn5VCAQ/zscaler-app-sso
- Type: Q&A
- Posted: 2018-01-09T12:14:50.000Z
- Last activity: 2023-07-06T13:22:43.000Z
- Answers: 22
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,
 We are trying to achieve single-sign-on with ADFS authentication using Zscaler app.
 Users accessing from external networks are prompt for credentials upon z-app login, however sso works fine when the same are accessing from an internal network.

I was able to get this to work with ADFS2.0 , however not in ADFS 3.0.
 Anyone come across a similar scenario and can advise?

Thx
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn5XCAQ/zapp-and-portt-8080-bypass","lastmod":"2023-07-07T11:35:51.000Z","id":"0D54u00009evn5XCAQ"} -->
## Zapp and portt 8080 bypass

- Source: https://community.zscaler.com/s/question/0D54u00009evn5XCAQ/zapp-and-portt-8080-bypass
- Type: Q&A
- Posted: 2018-01-08T16:55:49.000Z
- Last activity: 2023-07-07T11:35:51.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I have a customer who is attempting to bypass port 8080 traffic for a specific site by the zapp pac file. The policy defined in the admin portal says that only traffic cannot be allowed to be tunnels on non standard ports. They obviously seem to conflict with each other. So, should it be possible to bypass port 8080 in the pac since it would seem that happens before zapp does anything?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn5ZCAQ/zscaler-app-for-chromeos","lastmod":"2024-08-27T17:26:58.000Z","id":"0D54u00009evn5ZCAQ"} -->
## ZScaler app for ChromeOS

- Source: https://community.zscaler.com/s/question/0D54u00009evn5ZCAQ/zscaler-app-for-chromeos
- Type: Q&A
- Posted: 2017-12-30T20:53:12.000Z
- Last activity: 2024-08-27T17:26:58.000Z
- Answers: 12
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Does ZScaler have ZScaler app for Chrome OS to forward the traffic to ZScaler cloud?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn5lCAA/use-of-zapp-in-lan","lastmod":"2023-07-06T13:23:12.000Z","id":"0D54u00009evn5lCAA"} -->
## Use of zapp in LAN

- Source: https://community.zscaler.com/s/question/0D54u00009evn5lCAA/use-of-zapp-in-lan
- Type: Q&A
- Posted: 2017-11-20T08:00:00.000Z
- Last activity: 2023-07-06T13:23:12.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,
 For customers with traffic forwarding limitations for using GRE/IPSEC, would it be a good idea using Zscaler App for all stations for forwarding traffic while in LAN?

Thx
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn5tCAA/what-portion-of-10064-does-zapp-use-for-zpa","lastmod":"2023-05-31T08:56:59.000Z","id":"0D54u00009evn5tCAA"} -->
## What portion of 100.64 does ZApp use for ZPA?

- Source: https://community.zscaler.com/s/question/0D54u00009evn5tCAA/what-portion-of-10064-does-zapp-use-for-zpa
- Type: Q&A
- Posted: 2017-11-01T19:07:26.000Z
- Last activity: 2023-05-31T08:56:59.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is it the full /16, or some subset? We have a ZPA prospect who uses 100.64 space for some internal endpoint DHCP ranges and were wondering if there’s any part of that space they could use without colliding (for a full SDP approach leveraging ZPA for on-premise as well as remote users).
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn6MCAQ/minimum-os-versions-for-zapp","lastmod":"2023-05-31T09:30:02.000Z","id":"0D54u00009evn6MCAQ"} -->
## Minimum OS versions for Z-App

- Source: https://community.zscaler.com/s/question/0D54u00009evn6MCAQ/minimum-os-versions-for-zapp
- Type: Q&A
- Posted: 2017-09-11T19:44:46.000Z
- Last activity: 2023-05-31T09:30:02.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

What are the minimum supported version for Z-App?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn6SCAQ/about-the-client-connector-category","lastmod":"2023-07-07T11:20:51.000Z","id":"0D54u00009evn6SCAQ"} -->
## About the Client Connector category

- Source: https://community.zscaler.com/s/question/0D54u00009evn6SCAQ/about-the-client-connector-category
- Type: Q&A
- Posted: 2016-12-01T22:39:03.000Z
- Last activity: 2023-07-07T11:20:51.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Zscaler Client Connector, formerly Zscaler App, simplifies the enforcement of security and policy control from the Zscaler Cloud for both Zscaler Internet Access (ZIA) and Zscaler Private Access (ZPA). Client Connector is available for desktop and mobile devices.

Zscaler
[image]

##### [Zscaler Client Connector - Cloud-based Mobile Security](https://www.zscaler.com/products/zscaler-client-connector)

Deliver the power of Zscaler to all your mobile users with Zscaler Client Connector and simplify the enforcement of security from the Zscaler Cloud.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnBjCAI/tunnel-20-country-variable","lastmod":"2023-07-07T11:29:42.000Z","id":"0D54u00009evnBjCAI"} -->
## Tunnel 2.0 country variable

- Source: https://community.zscaler.com/s/question/0D54u00009evnBjCAI/tunnel-20-country-variable
- Type: Q&A
- Posted: 2021-08-23T11:34:02.000Z
- Last activity: 2023-07-07T11:29:42.000Z
- Answers: 11
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

Can you please explain if it is possible to use country variables in pac file when tunnel 2.0 is used?

We have some plans to move from tunnel 1.0 to 2.0 but we are using country variable to keep some traffic inside specific countries, as for example we had some issues that users from Sweden were connected to Denmark etc.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnBlCAI/zscaler-client-connector-on-linux-and-systemdresolved","lastmod":"2023-05-31T08:47:21.000Z","id":"0D54u00009evnBlCAI"} -->
## ZScaler Client Connector on Linux and systemd-resolved

- Source: https://community.zscaler.com/s/question/0D54u00009evnBlCAI/zscaler-client-connector-on-linux-and-systemdresolved
- Type: Q&A
- Posted: 2021-08-21T19:52:19.000Z
- Last activity: 2023-05-31T08:47:21.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I’m using ZScaler Client Connection on Fedora Core 33, which has switched from the traditional /etc/resolve.conf configuration to using systemd-resolved. With this install, the DNS configuration for ZPA does not get updated properly, and I have to manually use “resolvectl�? on Linux to set the domain name and DNS server for ZPA to work properly.

Is there any configuration that can be done to make this work properly, or any way to file a feature request to get systemd-resolved support added to ZCC for Linux?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnD1CAI/pac-file-exclusions-on-tunnel-20","lastmod":"2023-07-06T13:04:21.000Z","id":"0D54u00009evnD1CAI"} -->
## Pac File Exclusions on Tunnel 2.0

- Source: https://community.zscaler.com/s/question/0D54u00009evnD1CAI/pac-file-exclusions-on-tunnel-20
- Type: Q&A
- Posted: 2021-08-20T13:57:22.000Z
- Last activity: 2023-07-06T13:04:21.000Z
- Answers: 7
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I’ve been having a lot of trouble getting pac exclusions to work for Tunnel 2.0. We’ve been piloting Tunnel 2.0 and there are a few Chinese/Taiwanese sites we need to bypass and the pac file exclusions aren’t working. Zscaler support recommended using the VPN bypass in the app profile, but my understanding is that should only be used for VPN hosts. Anyone else running into this??

Here’s are example exclusions that aren’t working as the traffic still comes to Zscaler:
 /* China Hospital site added Aug 9, 2021 by KG */
 if (dnsDomainIs(host, “*[gov.cn](http://gov.cn)�?)) return “DIRECT�?;
 if (dnsDomainIs(host, “[login.gjzwfw.gov.cn](http://login.gjzwfw.gov.cn)�?)) return “DIRECT�?;

if (shExpMatch(url, “*.gov.cn�?)) return “DIRECT�?;

The second exclusion was working fine in the Tunnel 1.0 pac file.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnD4CAI/ztunnel-20-pac-file-to-use-specific-cen","lastmod":"2023-05-31T08:47:24.000Z","id":"0D54u00009evnD4CAI"} -->
## Z-Tunnel 2.0 PAC File to use specific CEN

- Source: https://community.zscaler.com/s/question/0D54u00009evnD4CAI/ztunnel-20-pac-file-to-use-specific-cen
- Type: Q&A
- Posted: 2021-08-20T09:39:06.000Z
- Last activity: 2023-05-31T08:47:24.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,
 currently we switched to Z-Tunnel 2.0 in a small test environment.
 We now came to an point where we need to bypass and create some other exceptions in our PAC Files.
 I know with Z-Tunnel 2.0 and Tunnel mode if you want to bypass traffic from Zscaler you need that
 return “PROXY ${ZAPP_TUNNEL2_BYPASS}�?; statement in the FWD PAC file to bypass Tunnel 2.0 and the same entry in the APP PAC File with “DIRECT�? statement to fully complete the Zscaler bypass.

Thats working fine. In addition we need to configure exact CEN or PZEN to use due to some websites which are only accessible via a public IP from the same Country.

Before switching to Z-Tunnel 2.0 we did this in our APP PAC File.
 For example:
 if ((shExpMatch(url, “someURL�?)))
 return “PROXY [fra4.sme.zscalertwo.net:80](http://fra4.sme.zscalertwo.net:80)�?;

Now with this FWD Profile when using Z-Tunnel 2.0 as written in the KB articles you should use the FWD PAC File to configure a bypass to a specific Proxy.

So my question is, how do we configure exceptions to use a specific official Zscaler CEN? Still in the APP PAC File, if yes do we need to bypass tunnel2.0 in the FWD PAC File? Or just create this exception in the FWD PAC File?

Thanks
 Jonas
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnD7CAI/zapp-connector-on-aws-transit-gateway","lastmod":"2023-05-31T08:47:24.000Z","id":"0D54u00009evnD7CAI"} -->
## ZAPP Connector on AWS Transit Gateway

- Source: https://community.zscaler.com/s/question/0D54u00009evnD7CAI/zapp-connector-on-aws-transit-gateway
- Type: Q&A
- Posted: 2021-08-17T11:13:44.000Z
- Last activity: 2023-05-31T08:47:24.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello Team,

We want to Deploy APP Connectors on AWS to reach AWS resources/workloads.

We do have 30+ AWS accounts, with NO VPC Peering enabled between the accounts.
 I have read about deploying APP Connector on Transit Gateways.
 But how the Connectivity should be Made and how all the applications can be reachable.
 Is the connector must be placed on the transit Gateway or Near the Load balancer.
 Can you Please give some inputs on this.

Manoj
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnDCCAY/packet-filter-mode-and-windows-nla","lastmod":"2023-05-31T08:18:55.000Z","id":"0D54u00009evnDCCAY"} -->
## Packet filter mode and Windows NLA

- Source: https://community.zscaler.com/s/question/0D54u00009evnDCCAY/packet-filter-mode-and-windows-nla
- Type: Q&A
- Posted: 2021-08-16T10:20:28.000Z
- Last activity: 2023-05-31T08:18:55.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi everyone,

we see problems with the packet filter mode and Windows NLA service not recognizing, that the PC is connected to the copr. network, hence not switching the fw profile. Sometimes, it even occurs with the routed mode, but it is way more stable.

In the packet filter mode, how does ZApp let Windows know that it is now connected? Is there anything special that needs to be configured on the client side,… ?

This is causing us headaches for some time now and the local IT tshooted it for good 1/2y testing various configurations,… The problem comes and goes with different ZApp versions and sometimes it works for a month and then it stops working (same user, same PC,…). For some users it never works,…
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnDPCAY/zcc-logout-tracking","lastmod":"2023-05-31T08:47:29.000Z","id":"0D54u00009evnDPCAY"} -->
## ZCC logout tracking

- Source: https://community.zscaler.com/s/question/0D54u00009evnDPCAY/zcc-logout-tracking
- Type: Q&A
- Posted: 2021-07-26T16:14:20.000Z
- Last activity: 2023-05-31T08:47:29.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is there anyway to tell from the portal if a user has logged out of ZCC?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnDQCAY/zcc-working-while-on-trusted-network","lastmod":"2023-07-06T13:04:36.000Z","id":"0D54u00009evnDQCAY"} -->
## ZCC Working while on Trusted Network

- Source: https://community.zscaler.com/s/question/0D54u00009evnDQCAY/zcc-working-while-on-trusted-network
- Type: Q&A
- Posted: 2021-07-26T12:52:21.000Z
- Last activity: 2023-07-06T13:04:36.000Z
- Answers: 2
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

Greetings!!!

Can someone within the community folks help me to understand ZCC ( Z-App) working while a user is on Trusted network. Assuming I already set the trusted network criteria in forwarding profile;

1. Does Zpp goes to fail open mode when it detects trusted network ? Considering the fact I have transparent forwarding from the network edge device using GRE/IPSEC tunnels to public service edge?
2. Or does it still establishes its own tunnel 1.0/2.0 depending on the configuration in the profiles ? In this case will it be a tunnel 1.0/2.0 inside a GRE /IPSEC tunnel at the edge device? or how does it work in this way?
3. What would be a particular use case to use a Zpp for ZIA service when user is in corporate network and all my internet based traffic can be routed to public service edges using transparent forwarding at edge routers ? even my browser will use this GRE/IPSEC tunnel to forward interned based traffic to Zscaler public edges?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnDaCAI/internet-bandwidth-issue","lastmod":"2023-07-06T13:00:48.000Z","id":"0D54u00009evnDaCAI"} -->
## Internet Bandwidth issue

- Source: https://community.zscaler.com/s/question/0D54u00009evnDaCAI/internet-bandwidth-issue
- Type: Q&A
- Posted: 2021-07-20T05:46:55.000Z
- Last activity: 2023-07-06T13:00:48.000Z
- Answers: 30
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

We have been noticing that Internet Speed of some end-users is reducing drastically post installing Zscaler Client Connectors.

If the ISP speed is 100 MBPS, user is getting just around 2-5 MBPS when performing a speedtest.

If we remove the ZCC from the system, then again Internet speed is normal (80-100 MBPS).

Noticed that this is happening only for some Random users.

Can some one share any thoughts on this?

Thanks,
 Rahul V
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnDfCAI/pac-logic-for-wildcard-bypass-for-zcc","lastmod":"2023-05-31T09:08:32.000Z","id":"0D54u00009evnDfCAI"} -->
## PAC logic for Wildcard Bypass for ZCC

- Source: https://community.zscaler.com/s/question/0D54u00009evnDfCAI/pac-logic-for-wildcard-bypass-for-zcc
- Type: Q&A
- Posted: 2021-07-15T17:57:49.000Z
- Last activity: 2023-05-31T09:08:32.000Z
- Answers: 0
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

If you want a wildcard to bypass traffic from ZCC using a wildcard there are a few interesting situations.

Using the following guide to help create App profile PAC and Forwarding profile PAC: [Best Practices for Adding Bypasses for Z-Tunnel 2.0 | Zscaler](https://help.zscaler.com/z-app/best-practices-adding-bypasses-z-tunnel-2.0)

If you use “(dnsDomainIs(host, “�?))�?. To create a wildcard you have to use the “.�? not “*�?.

Ie “ (dnsDomainIs(host, “.zscaler.com�?) will capture all subdomains. But if you did “*.zscaler.com�? if it wouldn’t work.

HOWEVER!

If you use “(shExpMatch(host,"")�?, then you have to use “*�? and NOT “.�?.

Ie “(shExpMatch(host,"*.zscaler.com")�? will capture all subdomains, and “.zscaler.com�? would not!

Hope this helps.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnDhCAI/zscaler-tunnel-20-restrict-access-to-ports","lastmod":"2023-05-31T08:47:32.000Z","id":"0D54u00009evnDhCAI"} -->
## Zscaler Tunnel 2.0 - Restrict access to ports

- Source: https://community.zscaler.com/s/question/0D54u00009evnDhCAI/zscaler-tunnel-20-restrict-access-to-ports
- Type: Q&A
- Posted: 2021-07-14T04:00:12.000Z
- Last activity: 2023-05-31T08:47:32.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,
 Z-Tunnel 2.0 has a tunneling architecture that uses DTLS or TLS to send packets to the Zscaler service. Because of this, Z-Tunnel 2.0 is capable of sending all ports and protocols.

I have only a few users on tunnel 2.0 and I would like to know if:
 1)Is there a way to find out which “protocols�? are going through zscaler which is not http/https? I guess the answer is not.
 2)Is there a way to restrict which protocols are allowed?

Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnDiCAI/client-connector-supported-oss-2008r2","lastmod":"2023-07-06T13:04:40.000Z","id":"0D54u00009evnDiCAI"} -->
## Client connector supported OSs? 2008R2?

- Source: https://community.zscaler.com/s/question/0D54u00009evnDiCAI/client-connector-supported-oss-2008r2
- Type: Q&A
- Posted: 2021-07-14T02:49:51.000Z
- Last activity: 2023-07-06T13:04:40.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi, I’ve searched Zscalers site and google for quite a bit today trying to find what I would think would be a simple datasheet somewhere. I’m trying to find the list of supported Windows OSes that can run Zscaler Client Connector.

More specifically I have a branch office that is still running a 2008 R2 Server that is not able to authenticate. Other 2016 Servers at his location can connect, but not the 2008 R2 which is leading me to think that it is not supported. I know its EOL with Microsoft and we are trying to move off of it. Can anyone point me to this info or let me know if it is already known that 2008 R2 wont work?

The error he is getting is: "Login Failed. res://ieframe.dll/navcancl.htm
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnDwCAI/zcc-android-excessive-app-permissions","lastmod":"2023-05-31T08:18:59.000Z","id":"0D54u00009evnDwCAI"} -->
## ZCC Android - Excessive app permissions?

- Source: https://community.zscaler.com/s/question/0D54u00009evnDwCAI/zcc-android-excessive-app-permissions
- Type: Q&A
- Posted: 2021-07-08T19:55:00.000Z
- Last activity: 2023-05-31T08:18:59.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

When I install ZCC onto my Android 11 phone (Pixel 3), it asks for permissions that include Phone (making calls) and Contacts. Can someone elaborate on what’s going on, and why these are needed?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnE4CAI/cloud-fw-controls-not-applying-to-zapp-connections-from-a-defined-network-location","lastmod":"2023-05-31T08:18:59.000Z","id":"0D54u00009evnE4CAI"} -->
## Cloud FW controls not applying to ZApp connections from a defined network location?

- Source: https://community.zscaler.com/s/question/0D54u00009evnE4CAI/cloud-fw-controls-not-applying-to-zapp-connections-from-a-defined-network-location
- Type: Q&A
- Posted: 2021-07-06T16:15:51.000Z
- Last activity: 2023-05-31T08:18:59.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Has anyone run into this? All our machines have ZApp installed, they connect from defined locations (i.e. NOT road warrior) and the location has FW enabled, however the FW rules are apparently completely being ignored. These machines connect using Tunnel 2.0 (I’ve tried both TLS and DTLS flavors) As an aside I also maintain IPSec tunnels for a different internal network location to the ZIA edge.

For example I have a rule defined to block TCP/4099 outbound. On machines riding the IPSec tunnel to ZIA I go to portquiz and test 4099, sure enough the traffic is blocked. An endpoint machine, in a different subnet defined as a different network location, I try the same test and get right out via TCP/4099. I’ve tried numerous other ports and the behavior stays the same. For my machines with ZApp installed the FW only seems to apply when the machines are in the ‘road warriors’ location.

I’ve done the whole packet capture thing and the outbound traffic IS being tunneled through to ZIA, its just not being filtered.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnE6CAI/zscaler-client-connector-sso","lastmod":"2023-05-31T08:18:59.000Z","id":"0D54u00009evnE6CAI"} -->
## Zscaler client connector SSO

- Source: https://community.zscaler.com/s/question/0D54u00009evnE6CAI/zscaler-client-connector-sso
- Type: Q&A
- Posted: 2021-07-05T10:50:13.000Z
- Last activity: 2023-05-31T08:18:59.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi All,

We received requirement from our client which is used OKTA for Idp. They required for seamless user authentication whereby user just uses their desktop\laptop (Onprem & Road Warrior) as usual without required to login Zscaler client connector.

Based on below guide the deployment is doable. However. Does anyone deploy the scenario and if they encounter any issue?. Thanks.

help.zscaler.com
##### [Zscaler-Okta-Deployment-Guide-FINAL.pdf](https://help.zscaler.com/downloads/zscaler-technology-partners/identity/zscaler-and-okta-deployment-guide/Zscaler-Okta-Deployment-Guide-FINAL.pdf)

9.18 MB
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnE8CAI/exceptionfilter-in-zsatunnel-log-file-for-8888","lastmod":"2023-07-06T13:04:46.000Z","id":"0D54u00009evnE8CAI"} -->
## ExceptionFilter in ZSATunnel log file for 8.8.8.8

- Source: https://community.zscaler.com/s/question/0D54u00009evnE8CAI/exceptionfilter-in-zsatunnel-log-file-for-8888
- Type: Q&A
- Posted: 2021-07-02T20:10:07.000Z
- Last activity: 2023-07-06T13:04:46.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

While working through some DNS issues I see in the ZSATunnel Log file entries such as these below. However, I don’t have such an exception listed in any of our ZCC App Profiles. Does anyone else have this entry in their log files?

ZSATUNNEL_DATE.log: DBG getExceptionFilters: Adding exception filter for Entry: IP: 8.8.8.8 MASK: 32 Protocol 17 Port: 53-53

ZSATUNNEL_DATE.log: DBG addToFilterTable: Adding: IP: 8.8.8.8 Mask: 255.255.255.255 Adapter: 8 Type: ZIA Include/Exclude SRC_PORT: 0 - 0 DST_PORT: 53 - 53 Protocol: UDP ACTION: Redirect IP_PROTO: IPv4

ZSATUNNEL_DATE.log: IP: 8.8.8.8 Mask: 255.255.255.255 Adapter: 8 Type: ZIA Include/Exclude SRC_PORT: 0 - 0 DST_PORT: 53 - 53 Protocol: UDP ACTION: Redirect IP_PROTO: IPv4

I’m trying to determine where this is originating. Any guidance would be greatly appreciated.

ZCC - Windows 10, 3.4.0.124 & 3.4.1.4

Thank you,

Joe Ringham
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnERCAY/zscaler-app-login-issue-uses-my-infosys-when-given-with-my-client-account","lastmod":"2023-07-06T13:11:01.000Z","id":"0D54u00009evnERCAY"} -->
## Zscaler App login issue -- uses my infosys when given with my client account

- Source: https://community.zscaler.com/s/question/0D54u00009evnERCAY/zscaler-app-login-issue-uses-my-infosys-when-given-with-my-client-account
- Type: Q&A
- Posted: 2020-03-27T20:12:02.000Z
- Last activity: 2023-07-06T13:11:01.000Z
- Answers: 13
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

When logging in to the Zscaler app installed on my Companies machine, it is picking my company account instead of taking my client account which am trying to use when logging in…

I clearly give my client email id and hit login, but it will automatically pick my Infosys account and throw me AD error —AADSTS50177: User account ‘**********@ad.infosys.com’ from identity provider ‘[https://sts.windows.net/63ce7d59-2f3e-42cd-a8cc-be764cff5eb6/](https://sts.windows.net/63ce7d59-2f3e-42cd-a8cc-be764cff5eb6/)’ does not exist in tenant ‘XX’ and cannot access the application ‘[zscloud.net](http://zscloud.net)’(Zscaler) in that tenant. The account needs to be added as an external user in the tenant first. Sign out and sign in again with a different Azure Active Directory user account…

Is this happened to anyone?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnETCAY/what-is-the-update-policy-interval-for-zscaler-app","lastmod":"2023-05-31T09:25:35.000Z","id":"0D54u00009evnETCAY"} -->
## What is the Update policy Interval for Zscaler App?

- Source: https://community.zscaler.com/s/question/0D54u00009evnETCAY/what-is-the-update-policy-interval-for-zscaler-app
- Type: Q&A
- Posted: 2020-03-25T20:13:44.000Z
- Last activity: 2023-05-31T09:25:35.000Z
- Answers: 1
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

What is the update policy interval for Zscaler app? Can we change it from the default? How can we check it and where to check it?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnEVCAY/tunnel-20-pac-file-variable","lastmod":"2024-06-10T15:40:30.000Z","id":"0D54u00009evnEVCAY"} -->
## Tunnel 2.0 PAC file variable

- Source: https://community.zscaler.com/s/question/0D54u00009evnEVCAY/tunnel-20-pac-file-variable
- Type: Q&A
- Posted: 2020-03-25T06:54:29.000Z
- Last activity: 2024-06-10T15:40:30.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi, can someone tell me what this variable is for PAC file configurations for Tunnel 2.0?
 ${ZAPP_TUNNEL2_BYPASS}

[https://help.zscaler.com/z-app/best-practices-adding-bypasses-z-tunnel-2.0?_ga=2.179416612.1241781466.1584938403-1177754173.1579504917](https://help.zscaler.com/z-app/best-practices-adding-bypasses-z-tunnel-2.0?_ga=2.179416612.1241781466.1584938403-1177754173.1579504917)

It is written in this URL, and I was curious where I can declare the variable.
 I wanted to declare this variable to an IP address for our on premise proxy server.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnEeCAI/zapp-switch-between-fedcloud-and-commercial-cloud","lastmod":"2023-05-31T08:19:03.000Z","id":"0D54u00009evnEeCAI"} -->
## Zapp switch between FedCloud and commercial Cloud

- Source: https://community.zscaler.com/s/question/0D54u00009evnEeCAI/zapp-switch-between-fedcloud-and-commercial-cloud
- Type: Q&A
- Posted: 2020-03-22T03:08:49.000Z
- Last activity: 2023-05-31T08:19:03.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We need for Zapp (Windows version) to have an option to switch between FedCloud and Commercial Cloud without re-installing the application.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnEgCAI/anyone-having-trouble-with-bluejeans-conferencing-and-zia-on-the-zapp","lastmod":"2023-05-31T08:50:51.000Z","id":"0D54u00009evnEgCAI"} -->
## Anyone having trouble with Bluejeans conferencing and ZIA on the ZApp

- Source: https://community.zscaler.com/s/question/0D54u00009evnEgCAI/anyone-having-trouble-with-bluejeans-conferencing-and-zia-on-the-zapp
- Type: Q&A
- Posted: 2020-03-19T21:41:17.000Z
- Last activity: 2023-05-31T08:50:51.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello -

```
We have been having intermittent issues with our Bluejeans Web Conferencing software and users have discovered that disabling the Internet Security portion of the Zapp allows the user to connect to the Bluejeans conference.  Most users are on a Windows Laptop.  Just curious if anyone has seen this.
```

Thank you
 Joe DiVito
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnF5CAI/zapp-trafic-forwarding-port-8080","lastmod":"2023-07-07T11:30:48.000Z","id":"0D54u00009evnF5CAI"} -->
## Zapp Trafic forwarding Port 8080

- Source: https://community.zscaler.com/s/question/0D54u00009evnF5CAI/zapp-trafic-forwarding-port-8080
- Type: Q&A
- Posted: 2019-06-13T13:27:34.000Z
- Last activity: 2023-07-07T11:30:48.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

hello,
 We have a problem with the port through which pass the flows.
 Indeed we opened the flows to 80 and 443 on the firewall, but we notice that the flows pass through port 8080.
 We looked at the logs and on wireshark to check which port the flows are going through and we found that this is port 8080.

Anyone have a solution to this problem ?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnF6CAI/unable-to-login-skype","lastmod":"2023-06-02T17:08:00.000Z","id":"0D54u00009evnF6CAI"} -->
## Unable to login Skype

- Source: https://community.zscaler.com/s/question/0D54u00009evnF6CAI/unable-to-login-skype
- Type: Q&A
- Posted: 2019-06-13T10:40:42.000Z
- Last activity: 2023-06-02T17:08:00.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Team,

Unable to login Skype when zapp is turned on, when zapp is disabled then skype is logging in.Can you please help on this
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnFDCAY/intune-zapp-deployment-on-ios","lastmod":"2023-05-31T09:29:04.000Z","id":"0D54u00009evnFDCAY"} -->
## Intune - ZApp Deployment on IOS

- Source: https://community.zscaler.com/s/question/0D54u00009evnFDCAY/intune-zapp-deployment-on-ios
- Type: Q&A
- Posted: 2019-04-29T23:27:32.000Z
- Last activity: 2023-05-31T09:29:04.000Z
- Answers: 1
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Howdy Folks,

Shamelessly hoping to leverage off someone else’s work.

I’ve got a ZIA only customer that’s looking to deploy Z-APP via Intune to their fleet of IOS devices. Most of the reference documentation from Zscaler and Microsoft for Intune deployments references ZPA. Does anyone know if the same process as described in the below docs applies to ZIA only customers, specifically in regards to the VPN profile:

- [https://uem4all.com/2019/04/10/intune-zpa/](https://uem4all.com/2019/04/10/intune-zpa/)
- [https://techcommunity.microsoft.com/t5/Enterprise-Mobility-Security/Microsoft-and-Zscaler-enhance-the-remote-user-experience-for-iOS/ba-p/253640](https://techcommunity.microsoft.com/t5/Enterprise-Mobility-Security/Microsoft-and-Zscaler-enhance-the-remote-user-experience-for-iOS/ba-p/253640)

I’m assuming it does but would be good to have validation.

Environmental Info:

- Currently they’re still using on-prem ADFS instead of AAD for their IDP (understand this means that auth can’t be bypassed)
- Using Intune Cloud (not hybrid)

Cheers,
 Andrew
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evnFJCAY/on-zapp-user-is-getting-authentication-error-after-every-34minutes","lastmod":"2023-07-07T11:31:10.000Z","id":"0D54u00009evnFJCAY"} -->
## On Zapp User is getting Authentication error after every 3-4minutes

- Source: https://community.zscaler.com/s/question/0D54u00009evnFJCAY/on-zapp-user-is-getting-authentication-error-after-every-34minutes
- Type: Q&A
- Posted: 2019-04-25T03:51:37.000Z
- Last activity: 2023-07-07T11:31:10.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

User is able to authenticate with Z-app, however after login every 3-4 minutes z-app shows “authentication error?? and traffic is blocked. For all the users its working fine, only for a specific user.

If anyone came across to such cases and resolution around let me know.

[image] [image]
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykL5CAI/installing-zscaler-client-connector-with-meraki-systems-manager-for-ios","lastmod":"2023-06-02T17:12:28.000Z","id":"0D54u00009eykL5CAI"} -->
## Installing ZScaler Client Connector with Meraki Systems Manager for IOS

- Source: https://community.zscaler.com/s/question/0D54u00009eykL5CAI/installing-zscaler-client-connector-with-meraki-systems-manager-for-ios
- Type: Q&A
- Posted: 2023-06-01T00:43:37.000Z
- Last activity: 2023-06-02T17:12:28.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Spieth On Security – 31 May 23
[image]

##### [Installing ZScaler Client Connector with Meraki Systems Manager for IOS](https://spiethonsecurity.com/2023/05/31/installing-zscaler-client-connector-with-meraki-systems-manager-for-ios/)

Zscaler and its SSE offering is a powerful platform that helps secure users as they connect their applications whether they are on premises or in the cloud. The power of Zscaler can’t be achieved u…
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykLDCAY/start-zcc-packet-capture-from-command-line-or-api","lastmod":"2023-06-02T17:12:26.000Z","id":"0D54u00009eykLDCAY"} -->
## Start ZCC Packet Capture from command line or API

- Source: https://community.zscaler.com/s/question/0D54u00009eykLDCAY/start-zcc-packet-capture-from-command-line-or-api
- Type: Q&A
- Posted: 2023-05-29T10:54:57.000Z
- Last activity: 2023-06-02T17:12:26.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We sometimes need to capture a trace on a Windows client computer without user involvement. Is there a way to start and stop a trace via API or CLI from a different user session (SYSTEM)?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykLFCAY/in-egypt-all-user-zscaler-traffic-automatically-bypassed","lastmod":"2023-06-02T17:16:50.000Z","id":"0D54u00009eykLFCAY"} -->
## In Egypt All user Zscaler Traffic automatically bypassed

- Source: https://community.zscaler.com/s/question/0D54u00009eykLFCAY/in-egypt-all-user-zscaler-traffic-automatically-bypassed
- Type: Q&A
- Posted: 2023-05-25T09:49:27.000Z
- Last activity: 2023-06-02T17:16:50.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We are trying to send some traffic via Zscaler and the rest of the internet traffic directly, but unfortunately, it works in the Office network But when connected to the outside office network it did work at all.
 Only **Egypt** location
 Initially, it was :
 Using Tunnel 1.0
 Version 4.1.0
 All Egypt User
 Only Off-trusted network has the issue
 Upgrade to Tunnel 2.0
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykLHCAY/zcc-windows-client-pcap-contains-duplicates-for-every-sent-packet","lastmod":"2023-06-21T01:16:19.000Z","id":"0D54u00009eykLHCAY"} -->
## ZCC Windows Client PCAP contains duplicates for every sent packet

- Source: https://community.zscaler.com/s/question/0D54u00009eykLHCAY/zcc-windows-client-pcap-contains-duplicates-for-every-sent-packet
- Type: Q&A
- Posted: 2023-05-25T18:11:38.000Z
- Last activity: 2023-06-21T01:16:19.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We’re troubleshooting some client performance/stability issues and I have been looking at a lot of ZCC packet captures recently. I’ve noticed that every sent/outbound TCP packet is duplicated in the PCAP (within milliseconds of the original send, so I doubt these are retries).

Is this a known issue? Can it be corrected? It’s a real hassle to grab a PCAP and then have it full of meaningless red. It’s even worse if you show it to another vendor who uses it as an easy excuse to point the finger at Zscaler.

[[image] image1474×359 41.8 KB](https://global.discourse-cdn.com/zscaler/original/2X/0/07fecd1027d6db79097b2f2e2ce677228e84a1a2.png)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykLSCAY/zcc-upgrade-from-32bit-to-64bit","lastmod":"2023-09-21T03:41:58.000Z","id":"0D54u00009eykLSCAY"} -->
## ZCC Upgrade from 32bit to 64bit

- Source: https://community.zscaler.com/s/question/0D54u00009eykLSCAY/zcc-upgrade-from-32bit-to-64bit
- Type: Q&A
- Posted: 2023-05-23T08:31:27.000Z
- Last activity: 2023-09-21T03:41:58.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We were upgrading our Zscaler client app from 3.7 to 4.1.0.85 version. We have mixure of 32bit and 64bit users using 3.7 version. When we upgrade via mobile portal, it affected ZPA access of few users. From the logs we notice Zscaler device posture is empty post upgrade. Our ZPA access policy blocks the connection which fails minimum posture.

**Workaround:**
 a) ZCC → More → update policy
 b) ZCC → More → restart service
 c) ZCC → Relogin
 d) Mobile portal → De-register/ register

We did few more testing to understand why few machine were affected and found this Upgrade path.

|a)|ZCC 3.7 64bit to 4.1 64bit upgrade via mobile portal => Working|
 |b)|ZCC 3.7 32bit to 4.1 64bit upgrade via mobile portal => Not Working|
 |c)|ZCC 3.7 32bit to 3.7 64bit to 4.1 64bit via mobile portal => Not Working|
 |d)|ZCC 3.7 32bit to 4.0 64bit to 4.1 64bit via mobile portal => Working|
 |e)|ZCC 3.7 32bit to 4.1 32bit to 4.1 64bit via mobile portal => Working|
 |f)|ZCC 3.7 32bit to 4.1 64bit upgrade via SCCM Portal => Not Working|
 |g)|ZCC 3.8 32bit to 4.1 64bit upgrade via mobile portal => Not Working|
 |h)|ZCC 4.1 32bit to 4.1 64bit upgrade via mobile portal => Working|

Zscaler suggested to upgrade to 4.1.0.98 version which has fix. We are testing this upgrade path. Do anyone faced similar issue before or notice any issue with 4.1.0.98 version?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykOeCAI/zscaler-client-connector-api-failed-to-login","lastmod":"2023-06-02T17:11:52.000Z","id":"0D54u00009eykOeCAI"} -->
## Zscaler Client Connector API failed to login

- Source: https://community.zscaler.com/s/question/0D54u00009eykOeCAI/zscaler-client-connector-api-failed-to-login
- Type: Q&A
- Posted: 2023-04-25T06:45:09.000Z
- Last activity: 2023-06-02T17:11:52.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

hey guys, I do have a problem regarding the client connector API-
 I have created the API key and secret key and I can’t get the jwtToken no matter which URL am I trying to use. do you have a way to help me? I have tried “[https://api-mobile.zscloud.net/papi/auth/v1/login”](https://api-mobile.zscloud.net/papi/auth/v1/login%E2%80%9D), “[https://api-mobile.zscalerbeta.net/papi/auth/v1/login”](https://api-mobile.zscalerbeta.net/papi/auth/v1/login%E2%80%9D).
 in both I get via postman: 400-bad request “message”: “Invalid keys”
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykOuCAI/partner-users-popping-up-in-my-admin-consoles-","lastmod":"2023-06-02T17:11:47.000Z","id":"0D54u00009eykOuCAI"} -->
## Partner users popping up in my admin consoles... ?!

- Source: https://community.zscaler.com/s/question/0D54u00009eykOuCAI/partner-users-popping-up-in-my-admin-consoles-
- Type: Q&A
- Posted: 2023-04-19T12:36:44.000Z
- Last activity: 2023-06-02T17:11:47.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Currently Zscaler support has no clue how this is possible and seems to not have even addressed the ZCC logs I have attached to the case. I hope others are able to detect this quickly so Zscaler can fix a new extremely worrisome error.

I recently noticed some new machines popping up into my Admin console, this is not super alarming as we are an ever growing department and I was able to verify that the accounts existed and were in fact a part of our customer care team. I targeted their workstations for upgrades and successfully pushed to 3/4 identified users. When meeting with the 4th user I noticed they were on a policy for a different tenant that was not under my control…

These members of our customer care team are external to our company and managed by a completely different Zscaler admin team. They are on zscalerthree while I am on zscalertwo. They are on ABC policy while I am pushing XYZ policies.

THE ONLY safe haven I see here is that it appears that Zscaler is working on some sort of partner device functionality for 4.1++. None of my users are at this version. I can confirm the users popping up in my environment were not as well.

I really need any sort of assurance that the right department at Zscaler is seeing this so that they can look into why this would have happened. It should not be possible for other company users to just pick up my policy and be under my control. That is a massive error and I am not being asked how I can confirm that all my users are still under my control.

It doesn’t help that this happens a week after a massive AT&T outage that amplified an issue my field agents experience with Captive Portal detections.

If other users are experiencing this please open tickets!

Thanks,
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykOvCAI/user-or-machine-context-get-information-from-zsatunnel-log","lastmod":"2023-06-02T16:22:13.000Z","id":"0D54u00009eykOvCAI"} -->
## User or machine context - get information from ZSATunnel log

- Source: https://community.zscaler.com/s/question/0D54u00009eykOvCAI/user-or-machine-context-get-information-from-zsatunnel-log
- Type: Q&A
- Posted: 2023-04-19T12:54:21.000Z
- Last activity: 2023-06-02T16:22:13.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is it possible to get information about network traffic context (user or machine) from ZSATunnel log or any other way from endpoint (windows 10) perspective? I am troubleshooting Azure AD connection problems where some devices cannot authenticate in Azure AD. And Microsoft says that traffic must be possible in machine\system context
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykP2CAI/zscaler-client-connector-command-line-to-update-policy","lastmod":"2024-10-24T21:44:52.000Z","id":"0D54u00009eykP2CAI"} -->
## Zscaler client connector command line to update policy

- Source: https://community.zscaler.com/s/question/0D54u00009eykP2CAI/zscaler-client-connector-command-line-to-update-policy
- Type: Q&A
- Posted: 2023-04-18T07:38:33.000Z
- Last activity: 2024-10-24T21:44:52.000Z
- Answers: 11
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

I am looking for a way to run Update Policy from command line - the same as user can click in Zscaler Client connector GUI on More tab. Is it possible? are there any cmd switched for ZSATrayManager.exe or ZSATray.exe ?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykP4CAI/zscaler-client-connector-enable-build-option","lastmod":"2023-06-02T17:11:24.000Z","id":"0D54u00009eykP4CAI"} -->
## Zscaler Client Connector "Enable Build" Option

- Source: https://community.zscaler.com/s/question/0D54u00009eykP4CAI/zscaler-client-connector-enable-build-option
- Type: Q&A
- Posted: 2023-04-17T11:07:40.000Z
- Last activity: 2023-06-02T17:11:24.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello All,

I am very new in Zscaler proxy solution. In our organization we are using ZCC for colleagues system for internet access. In ZCC application download option in Client Connector portal i have seen the “enable build” option. It will be very helpful if some one can help to understand what is option for and when this need to enable and what is the benefit for this option if enable?

Thank you in Advance

[[MicrosoftTeams-image (1)] MicrosoftTeams-image (1)1497×606 52.4 KB](https://global.discourse-cdn.com/zscaler/original/2X/c/c21d2134e604f80bf81f5a64eadbb3b3c509fc6c.png)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykPwCAI/enhancement-request-email-notification-for-newly-enrolled-and-inactive-device-on-zcc","lastmod":"2023-06-21T05:56:13.000Z","id":"0D54u00009eykPwCAI"} -->
## Enhancement Request - Email Notification for Newly Enrolled and Inactive Device on ZCC

- Source: https://community.zscaler.com/s/question/0D54u00009eykPwCAI/enhancement-request-email-notification-for-newly-enrolled-and-inactive-device-on-zcc
- Type: Q&A
- Posted: 2023-04-09T23:20:24.000Z
- Last activity: 2023-06-21T05:56:13.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi Zscaler,

Please consider adding this functionality on ZIA/ZCC. We want to create email notification rule for Newly Enrolled and Inactive Device on ZCC (Zscaler Client Connector).

Zscaler support confirmed this functionality was not available as of today.

Regards,
 Matt
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykQ2CAI/troubleshoot-zscaler-client-constant-disconnectreconnects","lastmod":"2026-03-17T19:10:06.000Z","id":"0D54u00009eykQ2CAI"} -->
## Troubleshoot Zscaler Client Constant Disconnect/Reconnects

- Source: https://community.zscaler.com/s/question/0D54u00009eykQ2CAI/troubleshoot-zscaler-client-constant-disconnectreconnects
- Type: Q&A
- Posted: 2023-04-06T19:25:36.000Z
- Last activity: 2026-03-17T19:10:06.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Some of our users are getting random/constant disconnects from ZCC. I don’t believe it’s an MTU issue, even ran a few ping tests and didn’t seem to any fragmentation issues. Our client connector MTU settings have been adjusted to what others in the community have set to and it seems like it’s stable for the majority of our workforce.

It seems in the Zsaler log export the ZSATunnel logs is where one should look to check for these disconnects. Are there any pointers on exactly what needs to be searched for? Tried looking through a few and it’s a bit daunting.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykQECAY/zcc-versions-consfusion","lastmod":"2023-06-05T04:51:35.000Z","id":"0D54u00009eykQECAY"} -->
## ZCC Versions Consfusion

- Source: https://community.zscaler.com/s/question/0D54u00009eykQECAY/zcc-versions-consfusion
- Type: Q&A
- Posted: 2023-03-30T19:50:51.000Z
- Last activity: 2023-06-05T04:51:35.000Z
- Answers: 49
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Maybe this has been asked before, but I can’t find it.

Why does Zscaler maintain 3 Major/Minor versions of ZCC? I understand General Availability (GA) vs Limited Availability (RC), but why 3.9, 4.0, and 4.1?
 This really is a bit confusing when trying to determine what version I should be pushing to my users. Even more so when I open a case with Zscaler and their answer is to upgrade to the 4.1 version of ZCC, which is all in Limited Availability and really shouldn’t be run in production, IMHO.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykQWCAY/windows-time-syncronization-issue-with-zscaler-client-connector","lastmod":"2023-06-02T17:20:23.000Z","id":"0D54u00009eykQWCAY"} -->
## Windows Time Syncronization issue with Zscaler client connector

- Source: https://community.zscaler.com/s/question/0D54u00009eykQWCAY/windows-time-syncronization-issue-with-zscaler-client-connector
- Type: Q&A
- Posted: 2023-03-27T08:54:28.000Z
- Last activity: 2023-06-02T17:20:23.000Z
- Answers: 9
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi all , i notice today (after the time change of this weekend) that on many of ours windows laptops the windows time synchronization is having and issue ( we are using [time.windows.com](http://time.windows.com) as NTP server).
 I have added on SSL-Bypass-URLs [time.windows.com](http://time.windows.com) but still the sync is failing and is working only if i disable for a while Zscaler client connector on the machine.
 Any suggestions ?

Many thanks ad usual folks!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykQaCAI/ziazpa-client-connector-auth-azure-ad-sso","lastmod":"2023-08-08T12:28:30.000Z","id":"0D54u00009eykQaCAI"} -->
## ZIA/ZPA Client Connector Auth Azure AD SSO

- Source: https://community.zscaler.com/s/question/0D54u00009eykQaCAI/ziazpa-client-connector-auth-azure-ad-sso
- Type: Q&A
- Posted: 2023-03-24T07:46:14.000Z
- Last activity: 2023-08-08T12:28:30.000Z
- Answers: 0
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hi all,

I have integrated ZIA/ZIA with Azure AD (same IDP for both).
 MFA is enabled on AZ AD and some conditionnal Access rules are configured.

When using Zscaler Client connected, we need to authenticate twice (one for ZIA and one for ZPA).
 So the user needs to enter two times the password and token.

What I need to do to avoid this behaviour ??

Regards,

HA
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykQfCAI/zia-client-connector-auth-via-azure-sso-question","lastmod":"2023-06-02T17:20:54.000Z","id":"0D54u00009eykQfCAI"} -->
## ZIA Client Connector Auth via Azure SSO Question

- Source: https://community.zscaler.com/s/question/0D54u00009eykQfCAI/zia-client-connector-auth-via-azure-sso-question
- Type: Q&A
- Posted: 2023-03-22T23:52:07.000Z
- Last activity: 2023-06-02T17:20:54.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello all,

Before I get started down this road, I’d like to verify if this is possible.
 Recently I set up our ZScaler Admins to SSO via Azure and it works great (we have on-prem AD and Azure AD sync). Now I’d like to do that with Client Connector users. What I’m after is for the Client Connector authentication to be completely seamless and transparent to the end user. We have machines that multiple users will log into during the day and I’d like their Client Connector sessions to automatically authenticate based upon their Windows authentication. I don’t want my end users interacting with the agent at all.

Is that something that works out there?

Thanks in advance!
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykZ7CAI/client-connector-updates-mailing-list","lastmod":"2023-06-02T17:12:06.000Z","id":"0D54u00009eykZ7CAI"} -->
## Client Connector Updates Mailing list?

- Source: https://community.zscaler.com/s/question/0D54u00009eykZ7CAI/client-connector-updates-mailing-list
- Type: Q&A
- Posted: 2023-05-03T16:23:28.000Z
- Last activity: 2023-06-02T17:12:06.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Is there an email group or some other list that I need to be added on in order to get notified of when there are new versions of the Zscaler Client Connector? I have searched all around and didn’t find an answer.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eyl9FCAQ/zcc-api-integration-with-react-js","lastmod":"2023-06-02T17:12:01.000Z","id":"0D54u00009eyl9FCAQ"} -->
## ZCC API integration with React JS

- Source: https://community.zscaler.com/s/question/0D54u00009eyl9FCAQ/zcc-api-integration-with-react-js
- Type: Q&A
- Posted: 2023-04-27T19:25:09.000Z
- Last activity: 2023-06-02T17:12:01.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Hello,

I’m creating a web portal for my clients, im using Reactjs to dveelop the webportal. I wanted to fetch few details from ZCC portal and show it on my webpage. Will Zscaler support me to achieve this, can i use the API of ZCC in my react js code and fetch the details i want. If so how can i do it.

Im new to this part so i just wanted to your help.

Also when im trying to get my JWT token using login controller on swagger, i get error Stating type error failed to fetch and no jwt token is fetched i tried the urls provided in help portal but i still get same error. I also required help regarding this.

Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009fsZfiCAE/periodic-issues-connecting-to-o365-with-teams-and-outlook-clients","lastmod":"2023-06-13T00:47:35.000Z","id":"0D54u00009fsZfiCAE"} -->
## Periodic Issues Connecting to O365 with Teams and Outlook clients

- Source: https://community.zscaler.com/s/question/0D54u00009fsZfiCAE/periodic-issues-connecting-to-o365-with-teams-and-outlook-clients
- Type: Q&A
- Posted: 2023-06-05T15:33:40.000Z
- Last activity: 2023-06-13T00:47:35.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I have some users who are complaining about outlook/ms teams stop working after 30 minutes or couple of hours while on ZCC agent on but as soon as we logout of ZCC both applications work without any issues. Has anyone come across this issue before or have any suggestions, please feel free to reach out to me. </p><p><br></p><p>BTW, I am bypassing all Microsoft domains in pac file. </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009ftM1fCAE/frproposal-custom-notifications-to-zcc-users","lastmod":"2023-07-17T16:21:56.000Z","id":"0D54u00009ftM1fCAE"} -->
## FR-Proposal: Custom notifications to ZCC Users

- Source: https://community.zscaler.com/s/question/0D54u00009ftM1fCAE/frproposal-custom-notifications-to-zcc-users
- Type: Q&A
- Posted: 2023-06-06T11:39:52.000Z
- Last activity: 2023-07-17T16:21:56.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello Zscalers,</p><p><br></p><p>today we had - again - a short but annoying, intermittend outage of multiple M365 services (Outlook, Sharepoint, Teams). We were disconnected from most ways of communicating these outage to all our employees (except e.g. communication over company emergency mobile channels). Zscaler was still up&amp;running.</p><p><br></p><p>Since ZCC uses its own notification framework, IMHO it would be really helpful be able to send CUSTOM notifications to all ZCC users via Mobility Portal. Since ZCC is a trusted AND well-known app, I don't think our staff would have any concerns about the trustworthiness of these notifications.</p><p><br></p><p>Thanks and BR</p><p>Manuel</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009jY7h0CAC/okta-oie-zscaler-autoenrollment","lastmod":"2023-06-29T22:32:08.000Z","id":"0D54u00009jY7h0CAC"} -->
## okta oie zscaler auto-enrollment

- Source: https://community.zscaler.com/s/question/0D54u00009jY7h0CAC/okta-oie-zscaler-autoenrollment
- Type: Q&A
- Posted: 2023-06-26T17:51:39.000Z
- Last activity: 2023-06-29T22:32:08.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello Zscaler users, </p><p><br></p><p> We have Okta integration in place with Zscaler. User is able to login to Zscaler Client Connector using there Okta credentials and the login works fine. But in this process the enrollment is really slow and we want to speed up the enrollment with current step up.</p><p>With Okta OIE and Okta Verify/Fastpass have someone done some setup using which user don't have to interact with Zscaler Client Connector login or Okta login page and as soon as Zscaler Client Connector is installed on the machine it logs in automatically. We can't use Machine Tunnel in our setup.</p><p><br></p><p>Thank you in advance.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009kXMUeCAO/configuring-client-connector-to-block-local-nas","lastmod":"2023-07-27T16:40:01.000Z","id":"0D54u00009kXMUeCAO"} -->
## Configuring Client Connector to block local NAS

- Source: https://community.zscaler.com/s/question/0D54u00009kXMUeCAO/configuring-client-connector-to-block-local-nas
- Type: Q&A
- Posted: 2023-07-04T20:50:03.000Z
- Last activity: 2023-07-27T16:40:01.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello Fellow community members,</p><p>We recently found that our road warrior users can connect to any other NAS or laptop in private network.</p><p><br></p><p>We tried to block it using the tunnel exclusions/inclusions etc. but it seems the list never ending with thousands of possibilities with personal NAS, Printers, Home office routers, captive portals etc. to ensure what is needed is still works and we just block NAS etc. considering the security issues.</p><p><br></p><p>What is the best to achieve this? Any suggestions?</p><p><br></p><p>#client connectors #tunnel inclusions #tunnel #tunnel exclusions</p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009pTkGeCAK/zscaler-client-connector-macos-pulse-secure-vpn-trusted-network-adapter-criteria","lastmod":"2023-08-02T13:09:30.000Z","id":"0D54u00009pTkGeCAK"} -->
## Zscaler Client Connector (macOS) - Pulse Secure - VPN Trusted Network Adapter Criteria

- Source: https://community.zscaler.com/s/question/0D54u00009pTkGeCAK/zscaler-client-connector-macos-pulse-secure-vpn-trusted-network-adapter-criteria
- Type: Q&A
- Posted: 2023-08-01T19:15:42.000Z
- Last activity: 2023-08-02T13:09:30.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Customer is using full tunnel on Pulse Secure. </p><p><br></p><p>Want to disable ZIA when user connects to Pulse Secure VPN. Has anyone done this? </p><p><br></p><p>adding "Pulse" and "Pulse Secure" to the VPN Trusted Network Adapter Criteria is not working. </p><p><br></p><p>Works fine off VPN with ZT2.0 DTLS</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009qbr84CAA/zscaler-client-connector-sso-broken-starting-with-version-42","lastmod":"2023-09-25T19:42:57.000Z","id":"0D54u00009qbr84CAA"} -->
## Zscaler client connector SSO broken starting with version 4.2

- Source: https://community.zscaler.com/s/question/0D54u00009qbr84CAA/zscaler-client-connector-sso-broken-starting-with-version-42
- Type: Q&A
- Posted: 2023-08-04T19:48:54.000Z
- Last activity: 2023-09-25T19:42:57.000Z
- Answers: 8
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi everyone,</p><p><br></p><p>We are currently deploying Zscaler Client connector using the MSI modifiers to add the CLOUDNAME and USERDOMAIN parameters for Single sign-on. This used to work so that users enrolling in an Hybrid Azure AD joined computer without line of sight to a Domain Controller were automatically signed into the application, but after Client connector version 4.2, where Zscaler started to use WebView2 as the built-in browser, this stopped working and now users need to sign-in manually to the Zscaler app.</p><p><br></p><p>Zscaler support suggested us to to enable Azure seamless sign on as a workaround, but this does not prevent the issue from happening when users are off the corporate network without LoS to a Domain Controller.</p><p><br></p><p>Has anyone else experienced this issue, or happen to know a fix? </p><p><br></p><p>Thank you!</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009rbEiQCAU/long-standing-captive-portal-issue","lastmod":"2023-08-24T16:14:32.000Z","id":"0D54u00009rbEiQCAU"} -->
## Long Standing Captive Portal Issue

- Source: https://community.zscaler.com/s/question/0D54u00009rbEiQCAU/long-standing-captive-portal-issue
- Type: Q&A
- Posted: 2023-08-09T02:43:50.000Z
- Last activity: 2023-08-24T16:14:32.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Would like to start another thread for conversation on Captive Portal issues that are experienced for people on Windows Clients</p><p><br></p><p>I have ample findings on this issue and there are several different threads hosted elsewhere that all seem to point the fingers at ZIA regardless of what configurations people are running and ZCC versions. This is supported in the fact that I had P1 case open with Zscaler for weeks but had to close it due Microsoft April KIR.</p><p><br></p><p>Without diving too deep into my specific config I would like to state that issue is relevant on every Zscaler Client Connector App version from 3.6.0.26 to 4.2.0.190.</p><p><br></p><p>I would also like to ask what other solutions people have come to because at this point my direction has deemed CrowdStrike more beneficial to our end points than Zscaler traffic forwarding and users that travel contact me directly instead of our Care Center to resolve this. I have consulted with other Zscaler administrators possible resolutions that weren't effective for my environment.</p><p><br></p><p>Something I have noticed in my recent testing is that it seems CrowdStrike(Falcon) firewall appears to have an impact on this as I can turn it to monitor mode and the issue is no longer relevant. Falcon takes the place of Windows Defender on our workstations which has policy for the Captive Portal that is forced by Zscaler. With Captive Portal off, this issue does not go away. I have a ticket open with CrowdStrike as well because this configuration doesn't come with more than "allow all" or "block all". I would be very interested if other customers who have experienced this issue also have CrowdStrike active or something alternative to Defender.</p><p><br></p><p>Thanks in advance and lets try to keep this conversation headed in towards progress while addressing an issue that has previously gone unsolved on this portal. My primary goal is to get traffic forwarding enabled on these users ASAP.</p><p><br></p><p>Ryan</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009sVdQfCAK/zcc-client-strict-enforcement-not-working","lastmod":"2023-08-23T01:26:50.000Z","id":"0D54u00009sVdQfCAK"} -->
## ZCC client strict enforcement not working?

- Source: https://community.zscaler.com/s/question/0D54u00009sVdQfCAK/zcc-client-strict-enforcement-not-working
- Type: Q&A
- Posted: 2023-08-16T15:18:18.000Z
- Last activity: 2023-08-23T01:26:50.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I'm trying to deploy the ZCC client in strict enforcement mode. I have defined the msi parameter --strictEnforcement per documentation (<a href="https://help.zscaler.com/client-connector/customizing-zscaler-client-connector-install-options-msi" rel="noopener noreferrer" target="_blank">https://help.zscaler.com/client-connector/customizing-zscaler-client-connector-install-options-msi</a>), and I have a policy token and all prerequisites defined per documentation using tunnel with local proxy. The ZCC client shows it is in strict enforcement mode.</p><p><img src="/zenith/sfc/servlet.shepherd/version/download/0684u00000c3Eql?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p><br></p><p>One problem - it isn't blocking the internet. I'm wanting the strict enforcement behavior of blocking internet access until the ZCC client is logged into, however, with the ZCC client in strict enforcement mode, the internet is still fully accessible.</p><p><br></p><p>I have opened a ticket with support to assist in troubleshooting this, however, I wanted to check with the community and see if anyone had any ideas. Has anyone run into this?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009ttNOdCAM/detect-stricrenforcement","lastmod":"2024-06-11T18:17:10.000Z","id":"0D54u00009ttNOdCAM"} -->
## Detect STRICRENFORCEMENT

- Source: https://community.zscaler.com/s/question/0D54u00009ttNOdCAM/detect-stricrenforcement
- Type: Q&A
- Posted: 2023-08-18T15:02:36.000Z
- Last activity: 2024-06-11T18:17:10.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Is it possible to detect if ZCC is installed with the STRICTENFORCEMENT option? We want to be able to check that all installs are correctly configured for STRICTENFORCEMENT and if not initiate a reinstallation of ZCC with the correct options.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009uU1JkCAK/zcc-client-breaking-microsoft-authenticator-challenges","lastmod":"2024-09-02T05:47:48.000Z","id":"0D54u00009uU1JkCAK"} -->
## ZCC client breaking Microsoft Authenticator challenges

- Source: https://community.zscaler.com/s/question/0D54u00009uU1JkCAK/zcc-client-breaking-microsoft-authenticator-challenges
- Type: Q&A
- Posted: 2023-08-24T05:22:30.000Z
- Last activity: 2024-09-02T05:47:48.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We've got an intermittemt issue with the ZCC forwading profile breaking MS Authenticator challenges. As in, when the user tries to access something that triggers a MFA prompt which is a pop-up with the numbers you need to type into the Authenticator app, this window opens and it either just presents a blank window or a spinny wait icon. So effectively the user is locked out of the system if they can't pass MFA challenge. Quitting the ZCC app resolves the issue every time.</p><p>I've got a case open with support but this isn't going anywhere as I can't reproduce the issue on demand, it's intermittent and not affecting everyone consistently. We already have the "Microsoft Exclusions" enabled.</p><p><br/></p><p>Any advice appreciated</p><p>Thanks</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009uzEzCCAU/how-to-enable-sso-for-zcc-client","lastmod":"2023-08-31T01:34:19.000Z","id":"0D54u00009uzEzCCAU"} -->
## How to enable SSO for ZCC client

- Source: https://community.zscaler.com/s/question/0D54u00009uzEzCCAU/how-to-enable-sso-for-zcc-client
- Type: Q&A
- Posted: 2023-08-25T02:08:24.000Z
- Last activity: 2023-08-31T01:34:19.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have an issue that new laptops are beng imaged and sent to users directly, the problem is, the user sees a prompt to login to Zscaler, they click close, and then forever their device never logs into the ZCC app. - We aren't currently enforcing it's use.</p><p>The question is, how does one enable SSO or automatic login to the ZCC client? I recall reading somethign about this option, but I can't locate it now in settings or the Zscaler client app docs.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009vvH1ECAU/pac-file-proxy-statement-for-ztunnel-20","lastmod":"2024-06-10T15:58:36.000Z","id":"0D54u00009vvH1ECAU"} -->
## PAC file proxy statement for Z-Tunnel 2.0

- Source: https://community.zscaler.com/s/question/0D54u00009vvH1ECAU/pac-file-proxy-statement-for-ztunnel-20
- Type: Q&A
- Posted: 2023-08-30T15:11:32.000Z
- Last activity: 2024-06-10T15:58:36.000Z
- Answers: 2
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p>I have a technical doubt regarding Pac file and Z-Tunnel 2.0. We have Z-Tunnel 2.0 configured on App profile/FW Profile. </p><p>On the Pac file we've configured this statements for default traffic forwarding:</p><p>PROXY ${COUNTRY_GATEWAY_FX}:80</p><p>What I understand is the following:</p><ol><li>Web traffic generated by browsers (that have knowledge of Pac file) will flow to port 80 on the end user device</li><li>This traffic will get captured by Client Connector</li><li>Cli Conn will forward this traffic using DTLS to ZEN</li></ol><p>Is this correct? I'm a bit confused regarding the port used at the end of the proxy statement :80 as we are using Z-Tunnel 2.0 I think we should use port 443 may be? But if this is only local traffic at the end it will be captured by Client Connector.</p><p>Thanks for your time :)</p><p>Regards,</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009vwE1iCAE/update-your-browser-unsupported-browser-error-in-zscaler-client-connector-during-first-login","lastmod":"2023-12-21T16:43:22.000Z","id":"0D54u00009vwE1iCAE"} -->
## Update your browser / Unsupported Browser Error in ZScaler Client Connector During First Login

- Source: https://community.zscaler.com/s/question/0D54u00009vwE1iCAE/update-your-browser-unsupported-browser-error-in-zscaler-client-connector-during-first-login
- Type: Q&A
- Posted: 2023-08-31T15:23:03.000Z
- Last activity: 2023-12-21T16:43:22.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have been experiencing an issue with the embedded browser within ZScaler Client Connector.</p><p><br></p><p>Users that try to authenticate to ZScaler Client Connector on a machine for the first time receive this error message:</p><p><img src="/zenith/sfc/servlet.shepherd/version/download/0684u00000c4Mes?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p>We are using Azure AD as our IdP.</p><p><br></p><p>Has anyone else experienced this issue? If so, were you able to find a fix?</p><p><br></p><p>Support recommended we try enabling WebView 2.0 in the Platform Settings in the ZCC admin GUI; however, it does not seem to support the Azure AD Conditional Access Policy that checks to see if the computer you are logging in from is domain-joined so authentication fails.</p><p><br></p><p>ZCC App Version: 4.2.0.190</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009vxYUWCA2/zcc-frequently-disconnecting-and-reconnecting","lastmod":"2024-05-29T11:24:44.000Z","id":"0D54u00009vxYUWCA2"} -->
## ZCC frequently disconnecting and reconnecting

- Source: https://community.zscaler.com/s/question/0D54u00009vxYUWCA2/zcc-frequently-disconnecting-and-reconnecting
- Type: Q&A
- Posted: 2023-09-01T16:05:50.000Z
- Last activity: 2024-05-29T11:24:44.000Z
- Answers: 15
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi. We have a handful of users reporting their client frequently disconnects and then reconnects. This is causing issues such as killing large browser downloads and dropping RDP sessions. They are running the same app profile as most of the company and are both on the latest Windows version of the client.</p><p><br></p><p>Rebooting their laptops and home routers have not solved the issue. One user confirms the issue persists when connected to a secondary&nbsp;internet connection.</p><p><br></p><p>Any advice is welcome.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009x2ekFCAQ/zcc-using-1006401-as-the-source-ip-address-for-zia","lastmod":"2024-08-28T11:05:12.000Z","id":"0D54u00009x2ekFCAQ"} -->
## ZCC Using 100.64.0.1 as the source IP address for ZIA

- Source: https://community.zscaler.com/s/question/0D54u00009x2ekFCAQ/zcc-using-1006401-as-the-source-ip-address-for-zia
- Type: Q&A
- Posted: 2023-09-08T08:50:44.000Z
- Last activity: 2024-08-28T11:05:12.000Z
- Answers: 11
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I can't be sure but this problem seems to have started after we upgraded to ZCC v4.1</p><p><br></p><p>Occasionally we see the ZCC client using 100.64.0.1 as the source IP address when on-network.</p><p>ZPA is disabled when on-network. This causes all the client traffic to get blocked by the firewall as this network is not defined in our policy or in a sub-location.</p><p><br></p><p>Anyone else seen this?</p><p><img src="/zenith/sfc/servlet.shepherd/version/download/0684u00000c4tzz?asPdf=false&amp;operationContext=CHATTER"></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009zdt6WCAQ/zscaler-client-connector-windows-defender-application-control-wdac","lastmod":"2023-09-22T08:19:45.000Z","id":"0D54u00009zdt6WCAQ"} -->
## Zscaler Client Connector - Windows Defender Application Control (WDAC)

- Source: https://community.zscaler.com/s/question/0D54u00009zdt6WCAQ/zscaler-client-connector-windows-defender-application-control-wdac
- Type: Q&A
- Posted: 2023-09-20T05:44:34.000Z
- Last activity: 2023-09-22T08:19:45.000Z
- Answers: 0
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Team,</p><p><br></p><p>We have a public sector customer has reported that the Windows Defender Application Control (WDAC) is blocking our Zscaler Client Connector on Windows 11.</p><p><br></p><p>We have previously requested to have the ZCC process whitelisted for Windows Defender for Endpoint.&nbsp;https://help.zscaler.com/client-connector/zscaler-client-connector-processes-allowlist</p><p><br></p><p>A few questions:-</p><p>Would like to seek clarity on if the WDAC also needs to explicitly whitelist the ZCC process as well? => I am guessing yes?</p><p>Is this also the same thing that we have seen in the field for our customers?</p><p>Is there any plans to work with Microsoft Intelligent Security Graph on getting some integrations or possibly fixing our zcc reputation?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A0dLA6CQM/zscaler-client-connector-and-proxyman-on-macos-coexistence-not-working","lastmod":"2024-09-24T13:50:23.000Z","id":"0D54u0000A0dLA6CQM"} -->
## Zscaler Client Connector and proxyman on MacOS coexistence not working

- Source: https://community.zscaler.com/s/question/0D54u0000A0dLA6CQM/zscaler-client-connector-and-proxyman-on-macos-coexistence-not-working
- Type: Q&A
- Posted: 2023-09-28T14:51:45.000Z
- Last activity: 2024-09-24T13:50:23.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><span style="font-size: 9px;">Hi team,</span></p><h1><span style="font-size: 9px;">Zscaler Client Connector is blocking local macOS Proxy settings from being enabled , we tried the way we use fiddler and doesn't help. Anyone encounter this issues ? </span></h1><p>Understand from support that there is an ER -12111. </p><p><br/></p><p>Please share if any workarounds as of now. </p><p><br/></p><p>Traffic forwarding is tunnel 2.0. </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A0dzoyCQA/mexico-datacenter-packet-loss-latencies-etc","lastmod":"2023-10-02T13:36:31.000Z","id":"0D54u0000A0dzoyCQA"} -->
## Mexico Datacenter - Packet loss, latencies, etc.

- Source: https://community.zscaler.com/s/question/0D54u0000A0dzoyCQA/mexico-datacenter-packet-loss-latencies-etc
- Type: Q&A
- Posted: 2023-09-28T20:58:48.000Z
- Last activity: 2023-10-02T13:36:31.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p><br></p><p>In recent weeks we have been experiencing numerous incidents related to latencies, packet losses, etc. in the NLD1 and MEX1 (Mexico) nodes.</p><p><br></p><p>Is anyone else having these types of problems?</p><p><br></p><p>You happen to us using GRE tunnels, ZCC tv1, ZCC tv2, etc...</p><p><br></p><p>Thanks!</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A2lNBhCQM/viewing-traffic-bypassed-by-zscaler","lastmod":"2025-08-22T07:26:59.000Z","id":"0D54u0000A2lNBhCQM"} -->
## Viewing traffic bypassed by Zscaler

- Source: https://community.zscaler.com/s/question/0D54u0000A2lNBhCQM/viewing-traffic-bypassed-by-zscaler
- Type: Q&A
- Posted: 2023-10-05T20:20:12.000Z
- Last activity: 2025-08-22T07:26:59.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>What is the best way to confirm traffic is being bypassed from ZIA? I know you can review Web Insight logs and technically you shouldn't see the bypassed site. Are there any other ways to determine this information (i.e. PCAPs not through ZCC / is there some indication in the ZCC PCAP that shows it is bypassed by Zscaler / other)?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A4N7zwCQC/zcc-otp-via-api","lastmod":"2025-08-22T07:26:59.000Z","id":"0D54u0000A4N7zwCQC"} -->
## ZCC OTP via API

- Source: https://community.zscaler.com/s/question/0D54u0000A4N7zwCQC/zcc-otp-via-api
- Type: Q&A
- Posted: 2023-10-19T15:29:40.000Z
- Last activity: 2025-08-22T07:26:59.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Has anyone been successful in using Postman to get the ZCC OTP? I cannot get it working via Postman after authenticating successfully via API. Getting the device list request fails.

I'm able to successfully get the Python script working using the sample script provided in the article below. Just trying to do the same using API via Postman.

How to configure Postman to get One Time Password for ZCC

https://zscaler.my.site.com/customers/s/article/How-to-configure-Postman-to-get-One-Time-Password-for-ZCC

How to programmatically retrieve OTP for Zscaler Client Connector users?

https://zscaler.my.site.com/customers/s/article/How-to-programmatically-retrieve-OTP-for-Zscaler-Client-Connector-users

Sample Python script to programmatically retrieve OTP for Client Connector users

https://community.zscaler.com/zenith/s/question/0D54u00009evmn2CAA/sample-python-script-to-programmatically-retrieve-otp-for-client-connector-users

Thanks
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A6TbJ0CQK/zscaler-client-connector-version","lastmod":"2025-08-22T07:27:01.000Z","id":"0D54u0000A6TbJ0CQK"} -->
## Zscaler Client Connector Version

- Source: https://community.zscaler.com/s/question/0D54u0000A6TbJ0CQK/zscaler-client-connector-version
- Type: Q&A
- Posted: 2023-10-25T17:33:37.000Z
- Last activity: 2025-08-22T07:27:01.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p>We are planning to upgrade Zscaler Client connector from Version 3.9.0.183&nbsp;to version 4.3.0.131.</p><p>Can someone kindly help me know whether version 4.3.0.131 is stable or not ?</p><p>&nbsp;</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A7AZ3gCQG/issue-with-onenote-not-syncing-from-zcc","lastmod":"2023-11-01T08:26:29.000Z","id":"0D54u0000A7AZ3gCQG"} -->
## Issue with OneNote not syncing from ZCC

- Source: https://community.zscaler.com/s/question/0D54u0000A7AZ3gCQG/issue-with-onenote-not-syncing-from-zcc
- Type: Q&A
- Posted: 2023-10-30T21:31:08.000Z
- Last activity: 2023-11-01T08:26:29.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We have users that are unable to sync OneNote for Windows 10 when working remotely with ZCC running. As soon as you exit the ZCC app, Onenote syncs again. I can't see anything being blocked in the Insight logs and it's not happening to every user. It works fine for some users who are on the same forward profile, same app version etc. I have no issue with it syncing when on a GRE tunnel or using the ZCC in road warrior mode, but the affected user tells me it doesn't work for him either when he's on an office GRE tunnel

I've tried support, but they have been usless, any suggestions welcome.
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A7Al5zCQC/zcc-390189","lastmod":"2023-10-31T13:55:48.000Z","id":"0D54u0000A7Al5zCQC"} -->
## Zcc 3.9.0.189

- Source: https://community.zscaler.com/s/question/0D54u0000A7Al5zCQC/zcc-390189
- Type: Q&A
- Posted: 2023-10-31T07:19:02.000Z
- Last activity: 2023-10-31T13:55:48.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

Good Day all !

While checking release note for Zcc client 3.9.0.189, I found this version has one fix "User ran into connection error when there was multiple active interface running on user system"

I just wanted to know more about this issue and was this issue present in version prior to 3.9.0.189?

Thanks

Dhananjay
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AA1RYoCQN/zcc-deletion-with-admin-access","lastmod":"2023-12-07T23:26:57.000Z","id":"0D54u0000AA1RYoCQN"} -->
## ZCC deletion with admin access

- Source: https://community.zscaler.com/s/question/0D54u0000AA1RYoCQN/zcc-deletion-with-admin-access
- Type: Q&A
- Posted: 2023-11-21T16:47:18.000Z
- Last activity: 2023-12-07T23:26:57.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p> We can move Zscaler folders which is in applications folder in macOS with admin rights even if we enabled password protection. Is there anyway to protect Zscaler folder not to delete in macOS?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AA2HuHCQV/zcc-deletion-with-admin-access","lastmod":"2023-11-23T05:35:07.000Z","id":"0D54u0000AA2HuHCQV"} -->
## ZCC deletion with admin access

- Source: https://community.zscaler.com/s/question/0D54u0000AA2HuHCQV/zcc-deletion-with-admin-access
- Type: Q&A
- Posted: 2023-11-23T05:35:07.000Z
- Last activity: 2023-11-23T05:35:07.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We can move Zscaler folders which is in applications folder in macOS with admin rights even if we enabled password protection. Is there anyway to protect Zscaler folder not to delete in macOS?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AA2QaqCQF/zcc-logs-wartend-message","lastmod":"2023-11-23T10:31:15.000Z","id":"0D54u0000AA2QaqCQF"} -->
## ZCC Logs - WARTEND message

- Source: https://community.zscaler.com/s/question/0D54u0000AA2QaqCQF/zcc-logs-wartend-message
- Type: Q&A
- Posted: 2023-11-23T10:31:14.000Z
- Last activity: 2023-11-23T10:31:15.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Experts,</p><p><br></p><p>I was hovering on the ZCC logs regarding a ZPA destination connectivity issue and in logs I found the destination server IP logs in ZSATrayManager file and status for this destination connectivity is "<strong>WARTEND</strong>". </p><p><br></p><p>Could anyone let me know what does this means , i am not able to solve this puzzle even after multiple internet search query :( </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ABHrGNCQ1/zcc-authenticate-early","lastmod":"2023-11-29T17:16:05.000Z","id":"0D54u0000ABHrGNCQ1"} -->
## ZCC Authenticate EARLY

- Source: https://community.zscaler.com/s/question/0D54u0000ABHrGNCQ1/zcc-authenticate-early
- Type: Q&A
- Posted: 2023-11-28T17:02:31.000Z
- Last activity: 2023-11-29T17:16:05.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p><br></p><p>After updating our ZCC client to version 4.2.0.209, we have this new status for private Access : </p><p>Authenticated : Authenticate EARLY </p><p><br></p><p>Do you have the same stauts when using this version ?</p><p>Thank you in advanced for your reply</p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000ed78d?asPdf=false&amp;operationContext=CHATTER"></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ABKyDlCQL/zcc-http-authentication-redirects","lastmod":"2023-12-07T23:31:06.000Z","id":"0D54u0000ABKyDlCQL"} -->
## ZCC HTTP Authentication Redirects

- Source: https://community.zscaler.com/s/question/0D54u0000ABKyDlCQL/zcc-http-authentication-redirects
- Type: Q&A
- Posted: 2023-12-04T17:07:50.000Z
- Last activity: 2023-12-07T23:31:06.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have deployed ZCC but we still see HTTP 307 redirects for authentication when using a browser.</p><p>We assumed that there won't be any 307 anymore because authentication takes place in ZCC.</p><p>Did we miss something?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ACCSNMCQ5/traffic-bypass-from-zscaler","lastmod":"2024-01-11T12:54:29.000Z","id":"0D54u0000ACCSNMCQ5"} -->
## Traffic Bypass From Zscaler

- Source: https://community.zscaler.com/s/question/0D54u0000ACCSNMCQ5/traffic-bypass-from-zscaler
- Type: Q&A
- Posted: 2023-12-10T11:24:41.000Z
- Last activity: 2024-01-11T12:54:29.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p><br></p><p>Good Day !</p><p><br/></p><p>Can someone kindly explain,</p><p><br/></p><p>As we know we can define in Zscaler profile that what type of traffic to keep away from client connector. </p><p>So to define this in Zscaler, traffic should first reach to ZCC. Now my query is, when traffic is already reaching to ZCC then what is the meaning to define "keep traffic away from ZCC"</p><p><br></p><p>Is this my understanding is correct?</p><p><br></p><p>Thanks</p><p>Dhananjay</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AGOVk8CQH/checking-service-status-of-ziazpazdx-shown-in-zcc-using-cmd","lastmod":"2024-01-30T13:03:50.000Z","id":"0D54u0000AGOVk8CQH"} -->
## Checking service status of ZIA,ZPA,ZDX shown in ZCC using cmd

- Source: https://community.zscaler.com/s/question/0D54u0000AGOVk8CQH/checking-service-status-of-ziazpazdx-shown-in-zcc-using-cmd
- Type: Q&A
- Posted: 2024-01-19T07:20:53.000Z
- Last activity: 2024-01-30T13:03:50.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi</p><p><br></p><p>As in the title, Are there ways to check service staus of ZIA, ZPA, ZDX shown in ZCC by using cmd?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AGP97gCQD/linux-client-connector","lastmod":"2024-01-24T17:15:31.000Z","id":"0D54u0000AGP97gCQD"} -->
## Linux client connector

- Source: https://community.zscaler.com/s/question/0D54u0000AGP97gCQD/linux-client-connector
- Type: Q&A
- Posted: 2024-01-22T00:35:26.000Z
- Last activity: 2024-01-24T17:15:31.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I've been given the Linux Client connector to install on our Linux systems. Ver 1.50.30</p><p>I'm noticing the pre-requisites require quite a few Qt libs. As these are servers I'm working with we have no gui, to a bit pointless installing windowing libs.</p><p>However when I try and run the .run file it also fails to run. Complaining about selinux being on. </p><p>I've put selinux in permissive for the moment. Still no joy.</p><p>Does this connector actually support being run on RHEL 8.9? </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AHNuwiCQD/zcc-auto-update","lastmod":"2024-01-27T09:01:41.000Z","id":"0D54u0000AHNuwiCQD"} -->
## ZCC auto update

- Source: https://community.zscaler.com/s/question/0D54u0000AHNuwiCQD/zcc-auto-update
- Type: Q&A
- Posted: 2024-01-25T12:44:42.000Z
- Last activity: 2024-01-27T09:01:41.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi we normally block MSI download for all normal users. I think it's preventing ZCC auto update which I do t a staging group first &amp; the push to the rest. What's the best way to sort this pls </p><p><br></p><p>Cheers</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AHP6ODCQ1/zia-zcc-when-systemfwd-pacfile-syntax-is-invalid","lastmod":"2024-01-29T14:46:21.000Z","id":"0D54u0000AHP6ODCQ1"} -->
## ZIA - ZCC when system/FWD pacfile syntax is invalid.

- Source: https://community.zscaler.com/s/question/0D54u0000AHP6ODCQ1/zia-zcc-when-systemfwd-pacfile-syntax-is-invalid
- Type: Q&A
- Posted: 2024-01-29T14:46:21.000Z
- Last activity: 2024-01-29T14:46:21.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello, </p><p>This is my first post on Zscaler community ;-) And I am not a specialist ; </p><p><br></p><p>Our FWD profile is : Windows > Packet Filter Based </p><p>On Trusted Network ==> Tunnel > Z-Tunnel 1.0 with System Proxy Settings > Enforce Custom PAC.</p><p>Off Trusted Network > Same as "On trusted Network"</p><p><br></p><p>My question is : </p><p><br></p><p>What happens / How ZCC behaves when System proxy pac syntax/parse string fails ?</p><p><br></p><p>Indeed we made a mistake in the (FWD system proxy) pacfile syntax and implemented it anyways (while the application pacfile syntax was correct).</p><p><br></p><p>======== in the ZSCATunnel logs we could see =============</p><p>DBG Validating Tunnel System proxy pac syntax.</p><p>DBG validatePacFileImpl: Pac parser init success!</p><p>ERR validatePacFileImpl: Pac parser parse pac string failed!</p><p>ERR Exception occurred in downloadTunnelSystemProxyPacFile (Error: Exception: validatePacFile: PAC file invalid)</p><p>DBG Tunnel system proxy pac download failed! Will try again after 160 seconds</p><p>DBG Scheduling tunnel system proxy pac download after 160 sec!</p><p>====================================================</p><p><br></p><p>During the incident most of the users it was still working fine for any proxied website, however for some Proxy ByPass websites (from the fwd/system pac), it was not working.</p><p><br></p><p>For those Bypass proxy exception The logs were showing destination IP 165.225.242.16 :80 (instead of the direct website public IP in normal conditions). </p><p>Not sure what the IP 165.225.242.16 is exactly corresponding to (in the Dallas I DC).</p><p><br></p><p>Thank you very much for your suggestion.</p><p>Karim</p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AHPLa8CQH/msteam-outlook-doesnt-work-in-zcc-42-when-window-defender-isolate-the-machine","lastmod":"2024-06-07T09:36:29.000Z","id":"0D54u0000AHPLa8CQH"} -->
## MsTeam & Outlook doesn't work in ZCC 4.2 when Window Defender Isolate the machine

- Source: https://community.zscaler.com/s/question/0D54u0000AHPLa8CQH/msteam-outlook-doesnt-work-in-zcc-42-when-window-defender-isolate-the-machine
- Type: Q&A
- Posted: 2024-01-29T23:59:29.000Z
- Last activity: 2024-06-07T09:36:29.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Windows Defender Service level isolation is used to quarantine the unpatched machine. This option will allow end user to still use MsTeams and Outlook even after isolation. Recently this feature stopped working. Once the Machine isolated, it cannot connect to MsTeams, Outlook, even msftconntest fails. Did anyone notice this issue and fixed it? </p><p><br></p><p>i use ZCC 4.2.0.217 </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AHQLPTCQ5/zcc-error-14","lastmod":"2024-07-16T04:32:56.000Z","id":"0D54u0000AHQLPTCQ5"} -->
## ZCC Error -14

- Source: https://community.zscaler.com/s/question/0D54u0000AHQLPTCQ5/zcc-error-14
- Type: Q&A
- Posted: 2024-01-31T15:40:06.000Z
- Last activity: 2024-07-16T04:32:56.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>This is the first time seeing this error and I can't find what it means. Error codes stop at -13. Is there somewhere this is explained</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AHQUCgCQP/ios-client-connector-and-msft-edge","lastmod":"2024-02-07T17:59:33.000Z","id":"0D54u0000AHQUCgCQP"} -->
## iOS Client Connector and MSFT Edge

- Source: https://community.zscaler.com/s/question/0D54u0000AHQUCgCQP/ios-client-connector-and-msft-edge
- Type: Q&A
- Posted: 2024-01-31T20:16:29.000Z
- Last activity: 2024-02-07T17:59:33.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Has anyone seen this behavior on iOS devices? </p><p><br></p><p>On an iPad if the default browser is MSFT edge, and someone tries to authenticate to the zscaler app, it doesn't prompt for the login in the Edge browser. It just sits at the "launch" page and doesn't prompt for username/password/security key</p><p><br></p><p>When we switch the tablet back to Safari for the default browser, it behaves as expected. </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AILJl6CQH/zscaler-client-connector-assistance-on-how-to-authenticate-with-device-certificate-or-user-certificate-as-idp-is-only-based-user-identity-on-azure-ad","lastmod":"2025-11-22T01:19:15.000Z","id":"0D54u0000AILJl6CQH"} -->
## Zscaler Client Connector - Assistance on How to authenticate with Device certificate or User Certificate as IDP is only based User identity on Azure AD

- Source: https://community.zscaler.com/s/question/0D54u0000AILJl6CQH/zscaler-client-connector-assistance-on-how-to-authenticate-with-device-certificate-or-user-certificate-as-idp-is-only-based-user-identity-on-azure-ad
- Type: Q&A
- Posted: 2024-02-06T10:02:14.000Z
- Last activity: 2025-11-22T01:19:15.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Zscaler Client Connector - Assistance on How to authenticate with Device certificate or User Certificate as IDP is only based User identity on Azure AD.</p><p>We need the better solution to deploy it as we have no proper feedback .</p><p>If any can share , how you have deployed Shared Devices to authenticate to Zscaler in your configuration in your setup it will be very helpful.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AILajACQT/slow-performance-resolution-requires","lastmod":"2024-02-07T11:06:12.000Z","id":"0D54u0000AILajACQT"} -->
## Slow performance resolution requires

- Source: https://community.zscaler.com/s/question/0D54u0000AILajACQT/slow-performance-resolution-requires
- Type: Q&A
- Posted: 2024-02-06T19:00:39.000Z
- Last activity: 2024-02-07T11:06:12.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Community, I wanted to pick everyone's brain as I've experienced an issue that has us and support stumped.</p><p><br></p><p>We're using ZPA, and had an issue where only Windows clients had very slow performance when access resources. In addition, these were only Windows clients that were on-prem routing through our Private Service Edge. (All users on/off network require Zscaler to access resources) Oddly, Macs that were on-prem weren't impacted.</p><p><br></p><p>The solution was asking users to log-out and completely exit the client connector. We had a fairly large group of individuals that had to do this by hand. Now, I know the client can have hicups, but what would cause 200-300 individuals to be impacted by a client issue like this? We're on <strong style="font-family: Inter, Inter-Regular, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Lucida Grande&quot;, &quot;sans-serif&quot;;">4.3.0.151 </strong><span style="font-family: Inter, Inter-Regular, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Lucida Grande&quot;, &quot;sans-serif&quot;;">of the client connector and don't have anti-tampering configured. (There's one newer release that addresses a network issue when using anti-tampering) Also, the client was updated a few weeks ago too.</span></p><p><br></p><p><span style="font-family: Inter, Inter-Regular, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Lucida Grande&quot;, &quot;sans-serif&quot;;">Overall I'm concerned that I don't have a real root cause unless its common practice to have users exist/re-start their ZCC during the workday.</span></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AIMAeQCQX/how-to-get-zscaler-client-connector-logs-with-zscaler-admin-portals-ziazpazdx","lastmod":"2024-02-08T14:34:40.000Z","id":"0D54u0000AIMAeQCQX"} -->
## How to get Zscaler Client Connector logs with Zscaler Admin Portals (ZIA,ZPA,ZDX)

- Source: https://community.zscaler.com/s/question/0D54u0000AIMAeQCQX/how-to-get-zscaler-client-connector-logs-with-zscaler-admin-portals-ziazpazdx
- Type: Q&A
- Posted: 2024-02-07T19:28:13.000Z
- Last activity: 2024-02-08T14:34:40.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I wanted to know if there was a way we could get the client connector logs of a user from Zscaler Portals such as ZIA, ZPA, and ZDX remotely. Remotely fetching the Zscaler Client Connector logs of the users will be extremely beneficial. </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AIMgl6CQD/intune-zscaler-strict-enforcement-not-working","lastmod":"2024-06-07T23:33:10.000Z","id":"0D54u0000AIMgl6CQD"} -->
## Intune Zscaler Strict Enforcement not working

- Source: https://community.zscaler.com/s/question/0D54u0000AIMgl6CQD/intune-zscaler-strict-enforcement-not-working
- Type: Q&A
- Posted: 2024-02-08T17:01:11.000Z
- Last activity: 2024-06-07T23:33:10.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Using Intune as the MDM and using the following guide: <a href="https://help.zscaler.com/client-connector/deploying-zscaler-client-connector-microsoft-intune-ios" rel="noopener noreferrer" target="_blank">Deploying Zscaler Client Connector with Microsoft Intune for iOS | Zscaler</a> </p><p><br></p><p>Created the config profile enabling strict enforcement. It seems that strict enforcement is not working. The profile is applied. The iPad is supervised but all internet traffic is allowed when the Zscaler Client Connector is not installed on the iPad.</p><p><br></p><p>I have set this up successfully in Ivanti EPMM but you have to use a Global HTTP Proxy pointing back to a PAC file host in the cloud.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AINUBNCQ5/zscaler-tunnel-20","lastmod":"2024-02-12T09:22:19.000Z","id":"0D54u0000AINUBNCQ5"} -->
## Zscaler Tunnel 2.0

- Source: https://community.zscaler.com/s/question/0D54u0000AINUBNCQ5/zscaler-tunnel-20
- Type: Q&A
- Posted: 2024-02-09T21:35:40.000Z
- Last activity: 2024-02-12T09:22:19.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p>We enabled Zscaler Tunnel 2.0 for test users, if I remove them from Tunnel 2.0 profile will the traffic pass from T 1.0 or it will be stopped?</p><p>If the above removal causes no impact do we need to configure anything on Client?</p><p>Please suggest me.</p><p>Thank you!</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AJW1ukCQD/isp-dns-traffic-flow","lastmod":"2024-02-19T08:54:28.000Z","id":"0D54u0000AJW1ukCQD"} -->
## ISP DNS TRAFFIC FLOW

- Source: https://community.zscaler.com/s/question/0D54u0000AJW1ukCQD/isp-dns-traffic-flow
- Type: Q&A
- Posted: 2024-02-16T13:33:05.000Z
- Last activity: 2024-02-19T08:54:28.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Team,</p><p><br/></p><p>I have Zscaler installed on my system. I am connected to home router. I got DNS ip 192.168.1.1.</p><p>In Zscaler app profile I have 192.168.0.0/16 in a destination exclusion list. So dns resolution request traffic from my system will be excluded from Zscaler. </p><p>So dns traffic is like src 192.168.1.10(system ip) destination 192.168.1.1 port 53 . and traffic will route to Home router since home router is my DNS server.</p><p>When home router enable to resolve Dns queries, it will send to ISP dns since isp dns server.is configured in home router.</p><p>Can someone help me to understand does Zscaler control dns traffic between home router and ISP dns server ? and If Zscaler intercept dns traffic how it will route to Zscaler .?</p><p><br></p><p><br></p><p>Thanks</p><p>Dhananjay</p><p><br/></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AKjdo8CQB/deploying-zcc-with-strict-enforcement-to-mac-os-using-workspace-one","lastmod":"2024-03-01T14:33:43.000Z","id":"0D54u0000AKjdo8CQB"} -->
## Deploying ZCC with strict enforcement to Mac OS using workspace one

- Source: https://community.zscaler.com/s/question/0D54u0000AKjdo8CQB/deploying-zcc-with-strict-enforcement-to-mac-os-using-workspace-one
- Type: Q&A
- Posted: 2024-02-26T20:08:26.000Z
- Last activity: 2024-03-01T14:33:43.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p><br></p><p>I used the following instructions to deploy ZCC to MacOS with Workspace One: https://community.zscaler.com/Zenith/s/question/0D54u00009evmfBCAQ/guide-zscaler-client-connector-deployment-with-workspace-one-uem-for-macos</p><p><br></p><p>Using zcc version 4.1, the first set of directions worked flawlessly and it was able to deploy zcc to brand new machines at first boot.</p><p><br></p><p>My problem is trying to enable strict enforcement at first login. I know with versions >3.9 it is now controlled via plist but I tried that and it didnt work.</p><p><br></p><p>Any assistance would be great.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AM63bOCQR/how-to-dns-filtering-to-block-domains-with-zccztunnel-20","lastmod":"2024-03-08T05:26:32.000Z","id":"0D54u0000AM63bOCQR"} -->
## How To: DNS Filtering to block domains with ZCC/Z-Tunnel 2.0

- Source: https://community.zscaler.com/s/question/0D54u0000AM63bOCQR/how-to-dns-filtering-to-block-domains-with-zccztunnel-20
- Type: Q&A
- Posted: 2024-03-08T01:47:12.000Z
- Last activity: 2024-03-08T05:26:32.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><span style="font-size: 14px; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Oxygen, Ubuntu, &quot;Fira Sans&quot;, &quot;Droid Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif;">In this article, we will see how to apply DNS filtering to block access to a gambling site, such as 888.com.</span></p><p><br></p><p><br></p><p><strong style="font-size: 14px;">Setup</strong></p><p><br></p><p>A working ZIA tenant and a client machine with Zscaler Client Connector (ZCC) configured for Z-Tunnel 2.0 (here we will only be looking at this deployment scenario).</p><p><br></p><p>Open the Client Connector on your client machine and verify you are connected to the ZIA tenant:</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000g5gyn?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p><br></p><p><br></p><p>Verify that your traffic is indeed being tunneled to the ZIA cloud, for this do the following on your client machine:</p><p><br></p><p>Go to whatismyip.com and verify the public IP address is that of Zscaler:&nbsp;</p><p><br></p><p>&nbsp;<img src="/sfc/servlet.shepherd/version/download/0684u00000g5gz2?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p><br></p><p>Go to&nbsp;<a rel="noopener noreferrer" href="https://health.com/">https://health.com</a>&nbsp;and verify that the certificate has been signed by Zscaler:</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000g5go7?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p><br></p><p><br></p><p><br></p><p><strong style="font-size: 14px;">Step-by-step approach</strong></p><p><br></p><p>Set the DNS server on your client machine to a public DNS resolver, such 8.8.8.8 or 9.9.9.9:</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000g5gz7?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p>This is because you want to ensure that your DNS traffic gets tunneled to the ZIA Cloud.</p><p>If the DNS server setting on your client machine points to a local IP (e.g., 192.168.x.x), then the DNS traffic by default will not be sent to the ZIA cloud. While there is way by which you can configure the ZCC to send such local DNS traffic to the ZIA cloud, we want to keep things simple and easy to follow at this stage.</p><p><br></p><p>Before we configure a DNS filtering rule to block gambling sites such as&nbsp;<a rel="noopener noreferrer" href="http://888.com/">888.com</a>, verify that you can do a DNS lookup for it:&nbsp;</p><p><br></p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000g5gzH?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p><br></p><p>Now, in the admin portal of your ZIA tenant, go to: Policy > Firewall > DNS Control.</p><p><br></p><p>Then click on&nbsp;Add DNS Filtering Rule:</p><p><br></p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000g5gui?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000g5gzb?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p><br></p><p>Under DNS Application tab: Choose Gambling in the Request Categories:&nbsp;</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000g5gzg?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p><br></p><p>You can choose to set Gambling in the Response Categories as well. If you set both Request and Response Categories, the matching is an OR condition.</p><p><br></p><p>Click on Save. Your rules should now look as follows:</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000g5gzl?asPdf=false&amp;operationContext=CHATTER"></p><p>&nbsp;</p><p><br></p><p><br></p><p>Click on Activation > Activate for the new changes to take effect:</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000g5gzq?asPdf=false&amp;operationContext=CHATTER"></p><p>&nbsp;</p><p><br></p><p><br></p><p><strong style="font-size: 14px;">Verification</strong></p><p><br></p><p>Now, from your client machine, try doing a DNS lookup for 888.com:</p><p><br></p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000g5h00?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p><br></p><p><br></p><p><br></p><p>Verify that DNS resolution is working for regular sites not blocked by DNS filtering:</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000g5h05?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p><strong>Check logs:</strong> Go to Analytics > DNS Insights:</p><p><br></p><p>Under Logs: Select the time duration and click on Apply Filter.</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000g5h0U?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p>You should see a log entry such as the following :</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000g5gyi?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p><br></p><p>&nbsp;As one can see:</p><ul><li>Request Category is "Gambling"</li><li>Request Rule Name is "Block Gambling Request"</li><li>Request Action is "Block"</li></ul><h2><br></h2><p><br></p><h2><strong style="font-size: 14px;">Subtle point to keep in mind</strong></h2><p>While it appears that DNS resolution for regular sites that are not blocked, such as sdxcentral.com, is performed by a public DNS resolver such as 8.8.8.8 or 9.9.9.9, that is not the case.</p><p><br></p><p>By default there is a DNAT rule by which the DNS traffic in the ZIA tenant is sent to ZTR for resolution. It is the ZTR, acting as a recursive resolver, that actually resolves this DNS query.</p><p><br></p><p>See the following documentation for reference:</p><p><a rel="noopener noreferrer" href="https://help.zscaler.com/zia/about-nat-control">https://help.zscaler.com/zia/about-nat-control</a></p><p><br></p><h2><br></h2><h2><strong style="font-size: 14px;">Next steps</strong></h2><p>You can implement DNS filtering based on the recommended best practices:</p><p><a rel="noopener noreferrer" href="https://help.zscaler.com/zia/best-practices-dns-control-rules">https://help.zscaler.com/zia/best-practices-dns-control-rules</a></p><p><br></p><p>Alternatively, the DNS control policies for these best practices should now be available by default in your ZIA tenant:</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000g5hMI?asPdf=false&amp;operationContext=CHATTER"></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AM6OiBCQV/zcc-client-upgrade","lastmod":"2024-03-12T12:36:54.000Z","id":"0D54u0000AM6OiBCQV"} -->
## ZCC Client Upgrade

- Source: https://community.zscaler.com/s/question/0D54u0000AM6OiBCQV/zcc-client-upgrade
- Type: Q&A
- Posted: 2024-03-08T16:17:17.000Z
- Last activity: 2024-03-12T12:36:54.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I see best practice on upgrading the Client Connector is by doing it through Zscaler. However; I have concerns about the lack of control over when the client upgrades. We deployed ours originally with SCCM but I can't find any specific KBs on upgrading with SCCM. Would it be just deploying the new version with the exact same install strings?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AOGnq6CQD/zcc-bandwidth-and-speed-drop","lastmod":"2024-04-02T09:21:44.000Z","id":"0D54u0000AOGnq6CQD"} -->
## ZCC bandwidth and speed drop

- Source: https://community.zscaler.com/s/question/0D54u0000AOGnq6CQD/zcc-bandwidth-and-speed-drop
- Type: Q&A
- Posted: 2024-03-28T10:07:49.000Z
- Last activity: 2024-04-02T09:21:44.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi All,</p><p><br></p><p>Recently our customer is facing issue with ZCC tunnel (On-premise profile) to Singapore DC. Internet speed and bandwidth drop to below 1 Mbps with ZCC and revert back to normal speed without enable ZCC.</p><p><br></p><p>During the encounter, customer have no option to redirect their traffic to Hong Kong (support tunnel 2.0) in order for resume business operation. However customer revert back the finding. after redirect back tunnel to Singapore the traffic and bandwidth returned as normal.</p><p><br></p><p>Does anyone has encounter the same issue before?.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000APeOq2CQF/redirect-web-traffic-to-zscaler-client-connector-listening-proxy-and-use-ztunnel-20-for-proxied-web-traffic-break-zcc","lastmod":"2024-04-16T07:14:45.000Z","id":"0D54u0000APeOq2CQF"} -->
## Redirect Web Traffic to Zscaler Client Connector Listening Proxy and Use Z-Tunnel 2.0 for Proxied Web Traffic break ZCC

- Source: https://community.zscaler.com/s/question/0D54u0000APeOq2CQF/redirect-web-traffic-to-zscaler-client-connector-listening-proxy-and-use-ztunnel-20-for-proxied-web-traffic-break-zcc
- Type: Q&A
- Posted: 2024-04-05T12:10:44.000Z
- Last activity: 2024-04-16T07:14:45.000Z
- Answers: 13
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We're using ZCC on both Azure AD joined and Azure Hybrid joined computers with no issues. Our current configuration is to use Tunnel 2.0 with a very basic (almost out of the box) App Profile .pac file only. We aren't using a Forwarding Profile pac. We've had a need to bypass ZCC for some stand alone domains in the past and have placed those domains in "<span style="font-family: Inter-Medium, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Lucida Grande&quot;, &quot;sans-serif&quot;;">HOSTNAME OR IP ADDRESS BYPASS FOR VPN GATEWAY" settings of the App Profile. These domains are standalone domains "domain.com" or "app.domain.com" for example. You can't place wildcard domains here, but we haven't had a need for those as of yet. Again, no issues with this setup.</span></p><p><br></p><p><span style="font-family: Inter-Medium, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Lucida Grande&quot;, &quot;sans-serif&quot;;">However, we now have a need to bypass ZCC for some wildcard domains. "*.domain.com" for example. According to Zscaler's documentation, if I'm using Tunnel 2.0 (with no proxy) then I need to place the wildcard domains in my App Profile .pac. The documentation also says I need to enable the following two settings in my Forwarding Profile under "</span><span style="font-family: Inter, Inter-Regular, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Lucida Grande&quot;, &quot;sans-serif&quot;;">Advanced Z-Tunnel 2.0 Configuration"</span></p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000gUHgX?asPdf=false&amp;operationContext=CHATTER"></p><p>However, when I enable these two settings and pull down the new profile to ZCC, I can no longer get to any websites. All websites, even the wildcard domain in the .pac file timeout when trying to browse to them. </p><p>Now here's the kicker. That only happens on a PC that's Azure AD joined. If I log into a Azure AD Hybrid joined computer using the same test user, pulling the same profile down to ZCC, everything seems to work correctly. I've tried using different ZCC versions and that experiences the same issue with the AAD joined computer. </p><p>Looking at the Zscaler web insight logs show I'm using Tunnel 2.0 and my wildcard "Direct" entry in my App Profile .pac file works correctly. I've also ensured that this Hybrid joined PC isn't pulling any local Group Policies from Active Directory by placing it in a OU that is blocking all GP inheritance. So in essense, this Hybrid joined PC should be no different than my Azure AD joined PC in terms of policy/configuration. My thinking here was that an old GP that I was unaware of might have been making a configuration change to the Hybrid joined PC, making it work. Where the AAD joined computer doesn't get local Active Directory Group Policies applied to it. That's the only real difference with these two PCs in my testing. I'm really throwing darts at the wall at this point.</p><p> </p><p>Going back to my Azure AD joined PC, if I turn off the above two settings, I can then surf the web again with no issues. And I see that traffic in the Zscaler Web Insight logs using Tunnel 2.0 like it should. It's only when these two settings are on that ZCC just stops passing traffic.</p><p><br></p><p>Has anyone seen this issue before or have any insight on what might be the cause?</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000APg3OICQZ/is-it-possible-to-script-zcc-to-logout","lastmod":"2024-04-11T12:56:14.000Z","id":"0D54u0000APg3OICQZ"} -->
## Is it possible to script ZCC to log-out?

- Source: https://community.zscaler.com/s/question/0D54u0000APg3OICQZ/is-it-possible-to-script-zcc-to-logout
- Type: Q&A
- Posted: 2024-04-09T23:52:35.000Z
- Last activity: 2024-04-11T12:56:14.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Given that a ZCC client requires a log-out password that can be entered into the GUI, is it possible to execute any of the ZCC EXEs with certain parameters that contain the password to log-out without the GUI?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AQUGY6CQP/unable-to-access-certain-url-after-deployed-dynamic-token-pac","lastmod":"2025-10-30T20:21:23.000Z","id":"0D54u0000AQUGY6CQP"} -->
## Unable to access certain URL after deployed dynamic token PAC

- Source: https://community.zscaler.com/s/question/0D54u0000AQUGY6CQP/unable-to-access-certain-url-after-deployed-dynamic-token-pac
- Type: Q&A
- Posted: 2024-04-18T09:53:19.000Z
- Last activity: 2025-10-30T20:21:23.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi everyone,</p><p><br></p><p>Referring to my previous post regarding ZCC <a href="https://community.zscaler.com/s/question/0D54u0000AOGnq6CQD/zcc-bandwidth-and-speed-drop" rel="noopener noreferrer" target="_blank">bandwidth drop</a>, we are currently testing Dynamic Gateway PAC with the following string return: "PROXY ${GATEWAY_FX}:80; PROXY ${SECONDARY_GATEWAY_FX}:80; DIRECT".</p><p><br></p><p>So far, there haven't been any major issues. However, we received reports from end users stating that certain websites failed to load or access until redirected using the fixed node "sin4.sme.zscalerthree.net" instead of using load-balanced nodes.</p><p><br></p><p>Is it normal to manually add URLs, or have we overlooked some configuration? Thanks</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AQUJpwCQH/client-connector","lastmod":"2024-04-18T15:14:11.000Z","id":"0D54u0000AQUJpwCQH"} -->
## Client Connector

- Source: https://community.zscaler.com/s/question/0D54u0000AQUJpwCQH/client-connector
- Type: Q&A
- Posted: 2024-04-18T11:31:38.000Z
- Last activity: 2024-04-18T15:14:11.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Could anyone point me in the right direction of using the Zscaler Client, in a country where the incountry Data Centre's are not configured as "Auto Geo Proximity Enabled" - and as a result when using the Zscaler Client, our traffic is going via another country.</p><p><br></p><p>Thanks</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AQUiY2CQL/dns-traffic-method","lastmod":"2025-10-30T20:40:05.000Z","id":"0D54u0000AQUiY2CQL"} -->
## DNS Traffic Method

- Source: https://community.zscaler.com/s/question/0D54u0000AQUiY2CQL/dns-traffic-method
- Type: Q&A
- Posted: 2024-04-19T01:20:07.000Z
- Last activity: 2025-10-30T20:40:05.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Team,</p><p><br></p><p>I need some advice about DNS traffic method, in condition ZCC pre login (user didn’t login yet and strict enforcement turned on) what DNS internal traffic method should i applied, it’s bypassed or over tunnel? Because for road warrior we use split tunnel with VPN and user still can access internal application without need to login ZCC</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ARP11CCQT/make-zcc-authenticate-using-azure-i-dont-want-the-seemless-logins","lastmod":"2024-04-30T16:06:00.000Z","id":"0D54u0000ARP11CCQT"} -->
## Make ZCC Authenticate using Azure (I don't want the seemless logins)

- Source: https://community.zscaler.com/s/question/0D54u0000ARP11CCQT/make-zcc-authenticate-using-azure-i-dont-want-the-seemless-logins
- Type: Q&A
- Posted: 2024-04-23T12:51:46.000Z
- Last activity: 2024-04-30T16:06:00.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hey Folks, </p><p><br></p><p>We use AzureAD/EntraID as our IdP and it enforces the MFA we are looking for on MacOS (after timeout it forces you to enter in username/password + Authenticator after our Timeout schedule is triggered). On WinOS I don't think I've ever seen it do this. I think when we first set this up years back we were told it had something to do with the cached session from logging into windows or something. We're writing our FedRamp controls right now and this is making it very tricky to explain why folks on WinOS aren't being forced into MFA. </p><p><br></p><p>Any guidance would be much appreciated!</p><p><br></p><p>Thanks, </p><p>Chuck</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ARQuw1CQD/zcc-strict-enforcement-with-tunnel-v1-blocking-sophos-intercept-x-update","lastmod":"2024-04-30T11:34:06.000Z","id":"0D54u0000ARQuw1CQD"} -->
## ZCC strict enforcement with tunnel v1 blocking Sophos intercept X update

- Source: https://community.zscaler.com/s/question/0D54u0000ARQuw1CQD/zcc-strict-enforcement-with-tunnel-v1-blocking-sophos-intercept-x-update
- Type: Q&A
- Posted: 2024-04-25T11:00:28.000Z
- Last activity: 2024-04-30T11:34:06.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi as per the title. If I disable ZCC then it works. May be need to whitelist </p><p><br></p><p>https://docs.sophos.com/central/customer/help/en-us/PeopleAndDevices/ProtectDevices/DomainsPorts/index.html#domains</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000ASPU9cCQH/delta-airlines-wifi-and-zcc-version-430181-already-set-to-10-min-fail-open-no-joy","lastmod":"2024-05-06T16:24:51.000Z","id":"0D54u0000ASPU9cCQH"} -->
## Delta Airlines WIFI and ZCC version 4.3.0.181 already set to 10 min fail open, no joy.

- Source: https://community.zscaler.com/s/question/0D54u0000ASPU9cCQH/delta-airlines-wifi-and-zcc-version-430181-already-set-to-10-min-fail-open-no-joy
- Type: Q&A
- Posted: 2024-05-06T15:08:45.000Z
- Last activity: 2024-05-06T16:24:51.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We already have our client connector version set to 4.3.0.181 and our policy setting is that when a captive portal is detected the connector fails open for 10 mins. However we have a few users who travel often and are complaining that even with this provision the connector will not work with wifi captive potals. In particular with the Delta Airlines wifi captive portal. I have read in the various posts about this that an option is to go to&nbsp;http://neverssl.com when the user gets the captive portal error. Has anyone else had complaints of this nature regarding the Delta airlines wifi portal? Does anyone here in the community have a work around for this when it happens or is the "neverssl" the best option for getting the connection to work? </p><p>Please let me know if you do.</p><p><br></p><p>Thank you.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AUhhnoCQB/enforce-zcc-login","lastmod":"2024-06-05T09:29:27.000Z","id":"0D54u0000AUhhnoCQB"} -->
## Enforce ZCC login

- Source: https://community.zscaler.com/s/question/0D54u0000AUhhnoCQB/enforce-zcc-login
- Type: Q&A
- Posted: 2024-05-31T17:27:46.000Z
- Last activity: 2024-06-05T09:29:27.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Are there methods to enforce an endpoint to login ZCC when Unregistered and Last Deregistration Time is not equal to current time?</p><p>Looking for ways to enforce ZCC to always be logged in whenever the endpoint is powered up and user is signed into endpoint.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AUjldkCQB/problem-accessing-an-smb-shared-drive-in-amazon-aws","lastmod":"2025-08-22T17:18:25.000Z","id":"0D54u0000AUjldkCQB"} -->
## Problem accessing  an SMB shared drive in Amazon AWS

- Source: https://community.zscaler.com/s/question/0D54u0000AUjldkCQB/problem-accessing-an-smb-shared-drive-in-amazon-aws
- Type: Q&A
- Posted: 2024-06-04T21:42:02.000Z
- Last activity: 2025-08-22T17:18:25.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello guys.</p><p><br></p><p>We have a shared drive/folder in an Amazon AWS server (<em>access controlled by IP segments</em>) that we want to access and map through the <strong>net </strong>command. This worked for a while but all of a sudden we lost access to the resource and we haven't been able to connect again (we get the error: <em>System error 67 has occurred, The network name cannot be found</em>)</p><p><br></p><p>We are using ZIA with ZCC, and the SMB connection is done using the v2 of SMB (port 445). Only by adding our Zscaler segments to the Amazon whitelist was enough to connect, but it is not working now. If we add a public IP to the whitelist and execute the command, the remote drive is mapped (external machine with no ZCC/ZIA)</p><p><br></p><p>Command used before: net use q: \\34.XX.XX.XX\remote-folder /user:username-XXXX\smbguest (this worked and asked for a password)</p><p><br></p><p>Some questions that can lead us to solve this problem:</p><ul><li>Is it necessary to create a bypass if with the default settings that worked before?</li><li>How we can be sure that Zscaler hasn't changed something impacting the connection?</li><li>Do you know if some Windows updates could have caused problems for SMB shares in the last months?</li><li>Do we have to configure a special set up for this kind of connections?</li><li>In the case of a problem with a DNS resolution, how we can fix that while keeping Zscaler ON?</li></ul><p>I would appreciate any help or ideas you can bring to the table.</p><p><br></p><p>Thank you.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AUjnlQCQR/how-to-enable-ipv6-with-client-connector-walkthrough","lastmod":"2025-02-19T10:04:38.000Z","id":"0D54u0000AUjnlQCQR"} -->
## How to enable IPv6 with Client Connector Walkthrough

- Source: https://community.zscaler.com/s/question/0D54u0000AUjnlQCQR/how-to-enable-ipv6-with-client-connector-walkthrough
- Type: Q&A
- Posted: 2024-06-05T00:06:28.000Z
- Last activity: 2025-02-19T10:04:38.000Z
- Answers: 0
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Zscaler cloud supports IPv4/IPv6 egress. Traffic must be forwarded to the Zscaler cloud in a tunnel (Tunnel 2.0, -Z-Connector, GRE, IPSec). Within the tunnel, Zscaler supports single or dual stack IPv4/IPv6 clients.</p><p><br></p><p>Dual stacked IPv4/IPv6 is a very common amongst residential telcos and mobile service providers around the world. Many in-fact deploy single stack IPv6 only for mobile devices with support for CGNAT to provide 'dual stacked' experience for mobile devices (This is what Telstra and T-Mobile does today).&nbsp;</p><p><br></p><p>Prerequisites:</p><ul><li>Dual stacked client, IPv4 and IPv6.</li><li>Traffic forwarded to Zscaler using a tunnel: Client Connector, ZS Workloads, or IPSec/GRE Tunnel.</li><li>Enable IPv6 Configuration in ZIA tenant (Requires support provisioning ticket. <a rel="noopener noreferrer" href="https://help.zscaler.com/zia/understanding-ipv6-support">https://help.zscaler.com/zia/understanding-ipv6-support</a>. This request should enable IPv6 in both the ZIA Tenant as well as Mobile Admin.)</li></ul><p>Config steps:</p><ol><li>Goto Administration>IPv6 Configuration</li><li>Enable the IPv6 toggle. (Make note of the message "<em>Enabling IPv6 creates a firewall filtering rule (Block All IPv6) that blocks all IPv6 traffic. You must edit this rule to allow IPv6 traffic.</em>")<img src="/sfc/servlet.shepherd/version/download/0684u00000iCbVK?asPdf=false&amp;operationContext=CHATTER"><img src="/sfc/servlet.shepherd/version/download/0684u00000iCbVF?asPdf=false&amp;operationContext=CHATTER"></li><li>Navigate to Policy>Firewall>Firewall Control</li><li>Edit Cloud Firewall policy and add a new IPv6 policy above the default block policy. Make sure you specify Source IPv6 Groups to 'IPv6 All'</li></ol><p>Please note, in order to differentiate your IPv4 and IPv6 policies, it is recommended to use the 'IPv6 All' object.</p><p><br></p><p>Additionally, IPv6 must also be enabled on the location(s) for location users(If tunneled from ZSWorkload/GRE/IPSec locations).&nbsp;</p><p><br></p><p>(optional) Custom NAT64 and DNS64 prefixes can be configured from their tabs under Administration > Address Family > IPv6 Configuration. You'd then be able to select one of these DNS64 prefixes under Location settings. (This applies to ZSworkload/GRE/IPSec location only)</p><p><br></p><p>Mobiler Admin</p><p>In order for ZCC send IPv6 traffic to Zscaler, <strong>the you MUST configure IPv6 inclusions in the Mobile Admin App Profile</strong>. These are <strong>NOT set by default.</strong></p><ol><li>Edit the App Profile for the devices your want to control</li><li><strong>An IPv6 default route ( </strong>[::/0] <strong>) should be configured for capturing all IPv6 traffic, here's an example.</strong></li></ol><p><img src="/sfc/servlet.shepherd/version/download/0684u00000iCbV0?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p><br></p><p><a rel="noopener noreferrer" href="https://www.loom.com/share/f72095c13e2546ac9d38905078a42668?sid=470833f2-a0b0-4837-8d08-1013ef2e3037">How to enable IPv6 with ZCC walkthrough</a></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AUk0VMCQZ/zpa-only-subscription-app-profiles","lastmod":"2024-06-05T10:28:05.000Z","id":"0D54u0000AUk0VMCQZ"} -->
## ZPA only subscription - app profiles

- Source: https://community.zscaler.com/s/question/0D54u0000AUk0VMCQZ/zpa-only-subscription-app-profiles
- Type: Q&A
- Posted: 2024-06-05T10:09:01.000Z
- Last activity: 2024-06-05T10:28:05.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello, </p><p>I have a question regarding ZPA. In the scenario that a customer have ONLY ZPA subscription, do we still have the option to create multiple app profiles?</p><p>I'm asking because it seems that we create an app profile and want to select groups/users, these are from the ZIA database.</p><p>So, If let's say a customer purchase only ZPA, does he still the option to configure SAML on the ZIA portal, so he can use the configured ZIA groups at the APP profiles?</p><p><br></p><p>If that's not the case, I assume that that we can still create app profiles, e.g. according to trusted network, so in that case all users will much the app profile we created, but at least we have the option to select what happens on/off trusted networks.</p><p><br></p><p>Thank you for any assistance in advance </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AVNCwYCQX/connection-issues-with-aws-services-from-office-lan","lastmod":"2024-06-19T08:19:23.000Z","id":"0D54u0000AVNCwYCQX"} -->
## Connection Issues with AWS Services from Office LAN

- Source: https://community.zscaler.com/s/question/0D54u0000AVNCwYCQX/connection-issues-with-aws-services-from-office-lan
- Type: Q&A
- Posted: 2024-06-17T00:56:07.000Z
- Last activity: 2024-06-19T08:19:23.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p></p><p><span style="font-family: UICTFontTextStyleBody;">We have encountered an issue where users on the office LAN are unable to access internal services hosted on AWS. Interestingly, users working from home and connected through our SSL VPN can access these services without any problems. The public IP of the SSL VPN segment and its DNS are bypassed in the ZCC profile.</span></p><p><br></p><p><span style="font-family: UICTFontTextStyleBody;">Currently, we have both Internet Security and Private Access active. To resolve the connection issue for users in the office, we have to disable the Internet security : ZIA module on the ZCC in all users . This allows office users to reach the internal services on AWS, but it's not an ideal long-term solution.</span></p><p><br></p><p><span style="font-family: UICTFontTextStyleBody;">Could you please provide advice on what changes are needed to permanently resolve this issue without disabling the Internet Access module? Your expertise on this matter would be greatly appreciated.</span></p><p><br></p><p><span style="font-family: UICTFontTextStyleBody;">Thank you for your assistance.</span></p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000iDP5k?asPdf=false&amp;operati</p><p><br></p><p><br></p><p><span style=">Best regards,</p><p></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AW0LQGCQ3/zcc-on-windows-server-2019-os","lastmod":"2024-07-07T16:56:01.000Z","id":"0D54u0000AW0LQGCQ3"} -->
## ZCC on Windows Server 2019 OS?

- Source: https://community.zscaler.com/s/question/0D54u0000AW0LQGCQ3/zcc-on-windows-server-2019-os
- Type: Q&A
- Posted: 2024-06-19T13:14:19.000Z
- Last activity: 2024-07-07T16:56:01.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Dear Zenith Community,</p><p><br></p><p>my technical team has the requirement to deploy ZCC on a Windows Terminal Server environment.</p><p><br></p><p>I have some sources indicating to me that ZCC is not supported under Windows Server 2019 OS but would like to get feedback and experience from you folks here.</p><p><br></p><p>Thanks for letting me know and kind regards,</p><p>Daniel</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AW20FaCQJ/technical-query-on-zscaler-branch-connector-and-zscaler-client-connector-for-vdi","lastmod":"2024-08-29T07:40:03.000Z","id":"0D54u0000AW20FaCQJ"} -->
## Technical Query on Zscaler Branch Connector and Zscaler Client Connector for VDI

- Source: https://community.zscaler.com/s/question/0D54u0000AW20FaCQJ/technical-query-on-zscaler-branch-connector-and-zscaler-client-connector-for-vdi
- Type: Q&A
- Posted: 2024-06-24T07:33:00.000Z
- Last activity: 2024-08-29T07:40:03.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I would like to establish a Site-to-Site LAN connectivity through Branch Connectors. LAN Devices are Network SDWAN, Switches &amp; Firewalls. I am trying to establish Managament Connectivity of remote network devices through branch connectors. Can Branch Connectors satisfy below points ?</p><ol><li>Only HTTPS/SSH connectivity to Remote Branch Network Devices from DC through Branch Connectors.</li><li>Can I use "Zscaler Client Connector for VDI" to access the Network Devices through Branch Connectors. </li></ol>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AYSSbcCQH/zcc-connectivity-reporting","lastmod":"2024-07-24T07:24:22.000Z","id":"0D54u0000AYSSbcCQH"} -->
## ZCC Connectivity Reporting

- Source: https://community.zscaler.com/s/question/0D54u0000AYSSbcCQH/zcc-connectivity-reporting
- Type: Q&A
- Posted: 2024-07-19T18:34:08.000Z
- Last activity: 2024-07-24T07:24:22.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>So I have no issue comparing what is in the ZCC portal to see which systems have it installed to those who do not, but has anyone come up with a solution that would tell me who has it installed but never connected? Since the endpoint does not appear in the console until it actually connects for the 1st time?</p><p>Thanks!</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AYU5uTCQT/how-to-be-use-shared-pc-with-zcc","lastmod":"2024-07-24T13:12:31.000Z","id":"0D54u0000AYU5uTCQT"} -->
## How to be use shared PC With ZCC

- Source: https://community.zscaler.com/s/question/0D54u0000AYU5uTCQT/how-to-be-use-shared-pc-with-zcc
- Type: Q&A
- Posted: 2024-07-24T04:48:59.000Z
- Last activity: 2024-07-24T13:12:31.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have one shared PC with Non-Person account. The particular department using that PC for specific use with NPA user id login. How we can Use ZCC on this PC. Every time login ZCC log-out is not feasible option on it. </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AZFLaOCQX/zcc-authentication-popups","lastmod":"2024-08-28T11:35:13.000Z","id":"0D54u0000AZFLaOCQX"} -->
## ZCC Authentication Pop-ups

- Source: https://community.zscaler.com/s/question/0D54u0000AZFLaOCQX/zcc-authentication-popups
- Type: Q&A
- Posted: 2024-07-25T20:05:02.000Z
- Last activity: 2024-08-28T11:35:13.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I am piloting Zscaler and trying to figure out how to suppress the ZPA authentication pop-ups. It has become a very annoying issue for some users, especially when they are working remotely and don't need to access private resources. I know the users can turn off Private Access in ZCC but I feel that from a zero-trust perspective, ZPA should be disabled by default until the user needs to enable and then authenticate. </p><p><br></p><p>When the authenticated session times out, it should also stay disabled without having to display constant pop-ups reminding the user to authenticate. We have discussed w/ Zscaler and looks like there is not a way that you can disable the pop-up by default. We have turned off all notifications in ZCC End User Notification Setting but that does not help.</p><p><br></p><p>I would like to know if anyone has experienced this issue in your company and how you remediate it. While we can try to educate the users to either disable Private Access or authenticate but it requires user intervention. I am also trying some workarounds using Windows notification framework that we can suppress and using the command line to disable ZPA. Has anyone tried either methods?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AZIcoICQT/example-pac-files-for-app-profile-and-forwarding-profile-for-tunnel-v20","lastmod":"2024-09-05T15:47:25.000Z","id":"0D54u0000AZIcoICQT"} -->
## Example PAC files for App Profile and Forwarding profile for Tunnel v2.0

- Source: https://community.zscaler.com/s/question/0D54u0000AZIcoICQT/example-pac-files-for-app-profile-and-forwarding-profile-for-tunnel-v20
- Type: Q&A
- Posted: 2024-08-01T21:28:22.000Z
- Last activity: 2024-09-05T15:47:25.000Z
- Answers: 12
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I am having a hard time visualizing how the configuration of the PAC files should go for App Profiles and Forwarding Profile and if they even needed. It seems the more I read the more confused I get. Does someone have an example of each? </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AZjaYVCQZ/microsoft-store-apps-updates","lastmod":"2024-08-08T13:33:52.000Z","id":"0D54u0000AZjaYVCQZ"} -->
## Microsoft Store Apps Updates

- Source: https://community.zscaler.com/s/question/0D54u0000AZjaYVCQZ/microsoft-store-apps-updates
- Type: Q&A
- Posted: 2024-08-06T12:39:17.000Z
- Last activity: 2024-08-08T13:33:52.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi, we are using the Zscaler Client Connector App for some of our laptops. We have some applications that require updates from the Microsoft Store, such as Snipping Tool or Paint. Our issue is that with a policy being set, it is causing the Store to be blocked it seems. Does anyone know of a way to allow just updates from the Microsoft Store, but still prevent the store itself from being utilized? We do have a gpo in place to prevent the downloading of apps from the store, but the client connector still seems to be stopping the updates from coming through.</p><p><br></p><p><img src="/zenith/sfc/servlet.shepherd/version/download/0684u00000ixEFP?asPdf=false&amp;operationContext=CHATTER"></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AZlYH6CQN/zscaler-cloud-branch-connector-zscaler-client-connector-for-vdi-availability-for-government-customers","lastmod":"2024-09-20T13:10:35.000Z","id":"0D54u0000AZlYH6CQN"} -->
## Zscaler Cloud & Branch Connector & Zscaler Client Connector for VDI availability for Government customers

- Source: https://community.zscaler.com/s/question/0D54u0000AZlYH6CQN/zscaler-cloud-branch-connector-zscaler-client-connector-for-vdi-availability-for-government-customers
- Type: Q&A
- Posted: 2024-08-09T17:48:57.000Z
- Last activity: 2024-09-20T13:10:35.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>What is ETA on Zscaler Cloud &amp; Branch Connector &amp; Zscaler Client Connector for VDI availability for Government customers?</p><p>https://help.zscaler.com/cloud-branch-connector</p><p>https://help.zscaler.com/cloud-branch-connector/what-zscaler-client-connector-vdi</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AakE8hCQE/can-i-set-the-xff-on-egress-for-my-road-warriormobile-users-ziaclient-connector","lastmod":"2024-08-28T11:25:07.000Z","id":"0D54u0000AakE8hCQE"} -->
## Can i set the XFF on egress for my Road Warrior/Mobile Users (ZIA/Client Connector)

- Source: https://community.zscaler.com/s/question/0D54u0000AakE8hCQE/can-i-set-the-xff-on-egress-for-my-road-warriormobile-users-ziaclient-connector
- Type: Q&A
- Posted: 2024-08-22T15:42:24.000Z
- Last activity: 2024-08-28T11:25:07.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi All,</p><p><br></p><p>We use the XFF header value set by Zscaler (by default) to the address of our site Internet access routers for some downstream security visibility (i.e. we see traffic from zscaler and check the XFF header to see if the string matches one of our Internet egress IP addresses in A.B.C.D format. Yes, aware that this can easily be spoofed and this is not part of any authentication process).</p><p><br></p><p>We would like to be able to do something similar with our Mobile user base (AKA Road Warriors). The current XFF value is obviously the address of the Internet router through which they are connecting.</p><p><br></p><p>We only have ZIA. Apologies if this is an obvious question - We have observed some proxy chaining in the Zscaler environment for mobile users and I have read about anchoring and associated configuration in the documentation but I am unsure of the applicability in this use case.</p><p><br></p><p>To summarise: Can i set the XFF header for my organisation's mobile users to a defined value (for all mobile users), or possibly defined values (based on geographical location) or in any other way be able to identify traffic that may be sourced from our mobile users ?</p><p><br></p><p>Is it possible, for instance, to use a pool of source addresses for mobile users? </p><p><br></p><p>Are there licencing or cost implications in order to do this, or is it not doable ?</p><p><br></p><p>Thanks very much for your attention and assistance.</p><p><br></p><p>Kev</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Aal8UOCQY/zcc-vs-globalprotect","lastmod":"2024-09-23T02:16:19.000Z","id":"0D54u0000Aal8UOCQY"} -->
## ZCC vs GlobalProtect

- Source: https://community.zscaler.com/s/question/0D54u0000Aal8UOCQY/zcc-vs-globalprotect
- Type: Q&A
- Posted: 2024-08-24T16:31:38.000Z
- Last activity: 2024-09-23T02:16:19.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Team,</p><p><br></p><p>I am new to Zscaler, I am a firewall engineer, So my question might be silly, But just wanted to understand the actual flow how it is working. </p><p><br></p><p>If we have zcc with tunnel2.0 and Installed GlobalProtect VPN with full tunnel. Which will take precedence and how. </p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Ab5cILCQY/proxyman-and-zscaler-client-connector","lastmod":"2024-09-24T13:53:46.000Z","id":"0D54u0000Ab5cILCQY"} -->
## Proxyman and Zscaler Client Connector

- Source: https://community.zscaler.com/s/question/0D54u0000Ab5cILCQY/proxyman-and-zscaler-client-connector
- Type: Q&A
- Posted: 2024-09-02T22:41:19.000Z
- Last activity: 2024-09-24T13:53:46.000Z
- Answers: 1
- Likes: 4
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><a href="https://proxyman.io/" rel="noopener noreferrer" target="_blank">Proxyman</a> is an excellent tool for intercepting and debugging http traffic on <a href="https://proxyman.io/download" rel="noopener noreferrer" target="_blank">Windows, Linux and MacOS systems</a>. </p><p><br></p><p>Proxyman does this by creating a local listening port on your computer and can also modify/override the default system proxy to allow for easy interception of http traffic (How it does this exactly is beyond the scope of this article). Proxyman users may find this does not operate as expected when using it alongside Zscaler Client Connector. Zscaler Client Connector provides a number of different options to achieve traffic forwarding, in this example, I will focus on ZTunnel 2.0 method running on Windows. (Other tunnel or proxy forwarding methods should also work).</p><p><br></p><p>The result is that the two do not appear to be compatible with one another by default. One way around this is to force your application to explicitly use the proxy that Proxyman has setup for us. The idea is that we have traffic flow as follows:</p><p><br></p><p><strong>Chrome-->Proxyman-->Zscaler-->Internet</strong></p><p><br></p><p>One way to achieve this is to force <a href="https://www.google.com/intl/en_ca/chrome/?_gl=1*1ywamis*_up*MQ..*_ga*MzYyMTM3NTI5LjE3MjUzMTYyNzk.*_ga_B7W0ZKZYDK*MTcyNTMxNjI3OS4xLjAuMTcyNTMxNjI3OS4wLjAuMA..&amp;gclid=CjwKCAjwxNW2BhAkEiwA24Cm9MsmRfDjhSIdg-YS5SHjJlTpr88KRKjKsS_Wl6XZabBXVxx5LkqSCRoCmNYQAvD_BwE&amp;gclsrc=aw.ds" rel="noopener noreferrer" target="_blank">Chrome</a> to use a particular proxy on startup. Unfortunately Chrome leverages the default Windows System proxy settings with no option for customization. However, Chrome does support <a href="https://peter.sh/experiments/chromium-command-line-switches/" rel="noopener noreferrer" target="_blank">command line switches</a> that can be passed during startup. You could do this through the command line with the following (a similar command can also be used on MacOS and Linux):</p><p><br></p><p>"c:\Program Files\Google\Chrome\Application\chrome.exe" --proxy-server="127.0.0.1:9090"</p><p><br></p><p>Please note, you must stop/shutdown all running instances of Chrome before you run this command. Once Chrome is running, all tabs and windows spawned off this instance will following these proxy settings.</p><p><br></p><p>Here's a screenshot showing my instance of Chrome being proxied from <strong>Chrome->Proxyman->Zscaler->Internet</strong></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000iz7un?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p><strong>Notes on other browsers:</strong></p><p><br></p><p><strong>Chromium</strong> also support a similar <a href="https://www.chromium.org/developers/design-documents/network-settings/" rel="noopener noreferrer" target="_blank">command syntax</a>. </p><p><br></p><p><strong>Firefox</strong> supports UI based proxy configuration. In addition, Firefox also supports containers allowing you to dedicate particular browser Tabs to different forwarding proxies or destinations.</p><p><br></p><p><strong>Microsoft Edge</strong> also support <a href="https://learn.microsoft.com/en-us/deployedge/edge-learnmore-cmdline-options-proxy-settings" rel="noopener noreferrer" target="_blank">command line driven proxy configuration</a>. Example:</p><p>"C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe" --proxy-server="127.0.0.1:9090"</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Ab5pNECQY/android-app-is-getting-crashed-when-accessing-however-the-application-is-working-fine-via-ba-and-client-connector-on-zpa","lastmod":"2024-09-05T09:04:10.000Z","id":"0D54u0000Ab5pNECQY"} -->
## Android app is getting crashed when accessing however the application is working fine via BA and Client connector on ZPA

- Source: https://community.zscaler.com/s/question/0D54u0000Ab5pNECQY/android-app-is-getting-crashed-when-accessing-however-the-application-is-working-fine-via-ba-and-client-connector-on-zpa
- Type: Q&A
- Posted: 2024-09-03T10:11:28.000Z
- Last activity: 2024-09-05T09:04:10.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi, Can any one know how we can resolve the android app which is getting crashed after migrating to ZPA</p><p><br></p><p>Below is the application error but it works fine when accessing via BA and client connector </p><p><br></p><p>javax.net.ssl.SSLHandshakeException: java.security.cert.CertPathValidatorException: Trust anchor for certification path not found.</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Ab6hBsCQI/dns-control-and-app-profile-exclusions-priority","lastmod":"2024-09-05T13:18:14.000Z","id":"0D54u0000Ab6hBsCQI"} -->
## DNS Control and APP profile exclusions priority

- Source: https://community.zscaler.com/s/question/0D54u0000Ab6hBsCQI/dns-control-and-app-profile-exclusions-priority
- Type: Q&A
- Posted: 2024-09-04T16:08:08.000Z
- Last activity: 2024-09-05T13:18:14.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi!</p><p><br></p><p>I am going through DNS control set up guides and there is something that came up to my mind, we have set some different exclusions in the app profile in the mobility portal. I wonder which is the precedence for those exclusions, I mean does Destination Ipv4 exclusions take precedence over DNS exclusions/inclusions?</p><p><br></p><p>Thanks in advance!</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Ab7TDWCQ2/zscaler-client-connector-logs","lastmod":"2024-09-06T15:01:05.000Z","id":"0D54u0000Ab7TDWCQ2"} -->
## Zscaler Client Connector Logs

- Source: https://community.zscaler.com/s/question/0D54u0000Ab7TDWCQ2/zscaler-client-connector-logs
- Type: Q&A
- Posted: 2024-09-05T19:00:32.000Z
- Last activity: 2024-09-06T15:01:05.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi everyone, does anyone know of a way to remotely view the logs a device's ZCC from the Mobile Admin portal or ZIA? Or would I have to remote into the machine, launch the application from the system tray, and then export the logs?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AbVE6zCQG/what-is-the-difference-between-pac-in-forwarding-profile-and-app-profile","lastmod":"2024-09-15T13:14:35.000Z","id":"0D54u0000AbVE6zCQG"} -->
## What is the difference between PAC in Forwarding profile and APP profile?

- Source: https://community.zscaler.com/s/question/0D54u0000AbVE6zCQG/what-is-the-difference-between-pac-in-forwarding-profile-and-app-profile
- Type: Q&A
- Posted: 2024-09-11T15:26:44.000Z
- Last activity: 2024-09-15T13:14:35.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><strong style="font-size: 24px; font-family: Haptik-Bold;">What is the difference between PAC in Forwarding profile and APP profile?</strong></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AbVUgZCQW/internet-speed-is-slow-while-zcc-enabled","lastmod":"2024-10-07T18:19:44.000Z","id":"0D54u0000AbVUgZCQW"} -->
## Internet Speed  is slow while ZCC enabled

- Source: https://community.zscaler.com/s/question/0D54u0000AbVUgZCQW/internet-speed-is-slow-while-zcc-enabled
- Type: Q&A
- Posted: 2024-09-11T21:07:37.000Z
- Last activity: 2024-10-07T18:19:44.000Z
- Answers: 11
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Few users Internet speed is very slow while Zscaler enabled, while it is disabled the speed is good.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AcblvDCQQ/client-ip-and-client-external-ip-equal-using-zcc","lastmod":"2024-10-11T00:01:07.000Z","id":"0D54u0000AcblvDCQQ"} -->
## Client IP and Client External IP equal using ZCC

- Source: https://community.zscaler.com/s/question/0D54u0000AcblvDCQQ/client-ip-and-client-external-ip-equal-using-zcc
- Type: Q&A
- Posted: 2024-10-01T19:14:31.000Z
- Last activity: 2024-10-11T00:01:07.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We are seeing some logs in which the client IP and the external Client IP is equal and we cannot set a relation between those cases in which this is happening. Why is this happening? My first insight was analyzing if this just happened with unauthenticated traffic or with no inspected traffic, but it isn't.</p><p><br></p><p>Does somebody know why we may see the public IP in both fields?</p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Acc3aDCQQ/zscaler-client-connector-api-getdevicedetailsusername","lastmod":"2024-10-08T08:10:52.000Z","id":"0D54u0000Acc3aDCQQ"} -->
## Zscaler Client Connector API - getDeviceDetails?username=

- Source: https://community.zscaler.com/s/question/0D54u0000Acc3aDCQQ/zscaler-client-connector-api-getdevicedetailsusername
- Type: Q&A
- Posted: 2024-10-02T10:54:54.000Z
- Last activity: 2024-10-08T08:10:52.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi there,</p><p><br></p><p>Not sure if anyone has a solution here but I am getting stuck with getting Device Details. </p><p>I am successful using the {udid} by using: /papi/public/v1/getDeviceDetails?udid=XXXX-XXXX-XXXX-XXX-XXX:123"</p><p><br></p><p>This device is assign to user_name: "test.tester@test.example.com" AND owner "test.tester".</p><p><br></p><p>the roller in username is the same as user_name. HOWEVER!</p><p><br></p><p>When I run the following: /papi/public/v1/getDeviceDetails?username=test.tester%40test.example.com"</p><p>it does not work (I also tried "@" instead of %40, AND I tried without "%40test.example.com"</p><p><br></p><p>Do I miss something here?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AdBbC9CQK/linux-client-connector-repo","lastmod":"2024-10-16T15:52:45.000Z","id":"0D54u0000AdBbC9CQK"} -->
## Linux Client Connector repo

- Source: https://community.zscaler.com/s/question/0D54u0000AdBbC9CQK/linux-client-connector-repo
- Type: Q&A
- Posted: 2024-10-16T13:58:35.000Z
- Last activity: 2024-10-16T15:52:45.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Does anyone have any intel on whether Zscaler will be setting up a repo for the Linux Client Connector? Would be nice to be able to update via the typical means (yum, apt, etc) rather than download/reinstall. </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AdDMcfCQG/macos-disconnection-post-42-zscaler-app-version","lastmod":"2025-03-25T17:00:54.000Z","id":"0D54u0000AdDMcfCQG"} -->
## macOS disconnection post 4.2+ Zscaler app version

- Source: https://community.zscaler.com/s/question/0D54u0000AdDMcfCQG/macos-disconnection-post-42-zscaler-app-version
- Type: Q&A
- Posted: 2024-10-20T10:58:26.000Z
- Last activity: 2025-03-25T17:00:54.000Z
- Answers: 8
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Post zcc 4.2 updates we notice more frequent disconnection in macOS . Upgrade to 4.3 didn't fix the issue. Meanwhile the introduction of macOS 14 sequoia the troubleshooting was mislead. Connectivity became stable with 4.1 version. However recently the disconnection started to reflect in 4.1 version. so the issue has 2 corners now. some changes in ZCC 4.2 conflicts with macOS as well as recent updates in macOS 15.1 breaks ZCC 4.1 mac. </p><p><br></p><p>Most of the disconnection is in ZIA module. when we disable ZIA, end user didn't notice any disconnection. so far the analysis went in below direction</p><p>a) Defender conflicts with ZCC -> asked to test by turning it off -> didn't tried yet</p><p>b) Disable ZIA -> worked</p><p><br></p><p><br></p><p>Did anyone facing ZCC disconnection in macOS ? did you able to fix it? </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Ada6g5CQA/what-directory-does-the-macos-installer-expect-the-installparameters-plist-file-to-be-in","lastmod":"2024-10-24T17:39:55.000Z","id":"0D54u0000Ada6g5CQA"} -->
## What Directory Does the macOS Installer Expect the InstallParameters plist file to be in?

- Source: https://community.zscaler.com/s/question/0D54u0000Ada6g5CQA/what-directory-does-the-macos-installer-expect-the-installparameters-plist-file-to-be-in
- Type: Q&A
- Posted: 2024-10-23T21:31:17.000Z
- Last activity: 2024-10-24T17:39:55.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Where does the macOS installer expect the plist file containing the installation parameters to be? Is it /Library/Preferences or perhaps /Library/Managed Preferences?</p><p><br></p><p>I am having some trouble creating a profile for macOS that gives the installer the parameters. I have followed this guide: https://community.zscaler.com/s/question/0D54u00009evmfBCAQ/guide-zscaler-client-connector-deployment-with-workspace-one-uem-for-macos. However, the very last step "4. Create the payload to install ZCC with custom install options" isn't very descriptive. I know how to generate the plist in the correct format, I'm just having trouble getting it to the right place in macOS so that the installer can use it.</p><p><br></p><p>Thanks for any help in advance.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Adbp8vCQA/after-connecting-to-zcc-the-internet-connection-drops","lastmod":"2024-11-06T04:43:04.000Z","id":"0D54u0000Adbp8vCQA"} -->
## After connecting to ZCC the internet connection drops

- Source: https://community.zscaler.com/s/question/0D54u0000Adbp8vCQA/after-connecting-to-zcc-the-internet-connection-drops
- Type: Q&A
- Posted: 2024-10-28T12:50:24.000Z
- Last activity: 2024-11-06T04:43:04.000Z
- Answers: 9
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p>Good Day!</p><p><br></p><p>After connecting to the ZCC the internet connection drops and the user is not able to access any domains.</p><p>Only ZIA is configured we are not using ZPA or ZDX</p><p>Kindly help</p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AdbpeECQQ/after-connecting-to-zcc-the-internet-connection-drops","lastmod":"2024-11-05T03:49:25.000Z","id":"0D54u0000AdbpeECQQ"} -->
## After connecting to ZCC the internet connection drops

- Source: https://community.zscaler.com/s/question/0D54u0000AdbpeECQQ/after-connecting-to-zcc-the-internet-connection-drops
- Type: Q&A
- Posted: 2024-10-28T12:50:26.000Z
- Last activity: 2024-11-05T03:49:25.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p>Good Day!</p><p><br></p><p>After connecting to the ZCC the internet connection drops and the user is not able to access any domains.</p><p>Only ZIA is configured we are not using ZPA or ZDX</p><p>Kindly help</p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Add94FCQQ/zcc-and-changing-the-userdomain-value","lastmod":"2024-11-05T03:57:56.000Z","id":"0D54u0000Add94FCQQ"} -->
## ZCC and changing the UserDomain value

- Source: https://community.zscaler.com/s/question/0D54u0000Add94FCQQ/zcc-and-changing-the-userdomain-value
- Type: Q&A
- Posted: 2024-10-30T19:12:44.000Z
- Last activity: 2024-11-05T03:57:56.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Our org is going through a corporate name change, including a new Auth Domain. I.e. we're going from First.Last@OldName.com to First.Last@NewName.com, and this will be reflected with a new User Principle Name change. I figure that we can't be the first Zscaler customer using ZCC to go through this, so I'm seeking guidance from all you folk.</p><p><br></p><p>We're using ZCC 4.3 in a ZIA/ZPA/ZDX environment, Windows, and a mix of laptops and Azure VDIs. </p><p><br></p><p>Here's what we know:</p><ol><li>We apparently cannot change the UserDomain to newdomain.com without installing/reinstalling ZCC.</li><li>User auth with NewDomain credential and ZCC/UPN with OldDomain simply results in the user being authenticated with user@OldDomain.com</li><li>User auth with NewDomain credentials and NewDomain UPN, but with ZCC set at OldDomain results in ZPA breakage due to Authentication Error. </li><li>Installing new ZCC package (i.e. MST file with UserDomain = NewDomain.com) over top of the old ZCC (using MS deployment tools) results in ZCC error state. Reboot doesn't fix. Logging out of ZCC and logging back in resolved it effectively, but this requires user action, and it violates security (ZIA is disabled until the user decides to log back in).</li><li>Removing the old ZCC package and installing the NewDomain ZCC package, while complex (requires a script), works effectively. However, the complexity is happening at the same time as other changes (i.e. UPN change), and the risk of users being left in a "No ZCC situation" is real if things go sideways.</li></ol><p>Has anyone travelled down this road before and found a solution that's more elegant than the clumsy ones that I've listed above? </p><p><br></p><p>There's a Community thread where a "ZCC CLI" is mentioned which could allow ZCC-UI actions to be issued by command line (e.g. ZCCpkgname.exe --LogOut and then "ZCCpkgname.exe --LogIn") but the current CLI only offers ZPA off/on control. No joy here.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AddBxHCQU/updating-the-username-in-zcc","lastmod":"2024-11-07T13:11:11.000Z","id":"0D54u0000AddBxHCQU"} -->
## Updating the username in ZCC

- Source: https://community.zscaler.com/s/question/0D54u0000AddBxHCQU/updating-the-username-in-zcc
- Type: Q&A
- Posted: 2024-10-30T21:39:39.000Z
- Last activity: 2024-11-07T13:11:11.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I'm trying to find a solution to minimize friction when users change their username, which happens fairly often since part of our naming convention includes surname.</p><p><br></p><p>We've currently got Okta configured to update the username for apps whenever the user profile gets updated, but since ZCC holds onto the username provided during enrollment, we get the 42000 mismatch error when they try to reauthenticate. Far as I can tell from searching here, the only way to update the username is to give the user a OTP and have them manually log out.</p><p><br></p><p>I don't see anything here more recent than three years ago, so hopefully something has changed? Is there a way to force that update now?</p><p><br></p><p>Our other alternative is to not update their username, but that presents an issue down the road if their device gets reset or replaced, when they will go to log in and everyone has forgotten the username change.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AdrFXwCQM/zcc-active-users-not-accurately-showing-when-users-are-using-zia-zpa-or-zdx","lastmod":"2024-11-06T05:37:17.000Z","id":"0D54u0000AdrFXwCQM"} -->
## ZCC Active Users not accurately showing when users are using ZIA, ZPA, or ZDX

- Source: https://community.zscaler.com/s/question/0D54u0000AdrFXwCQM/zcc-active-users-not-accurately-showing-when-users-are-using-zia-zpa-or-zdx
- Type: Q&A
- Posted: 2024-11-04T19:01:47.000Z
- Last activity: 2024-11-06T05:37:17.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We encountered an issue where the Zscaler Client Connector (ZCC) was incorrectly reporting the status of our users’ connections. Specifically, it showed users as disconnected from ZPA, ZIA, and ZDX when they were actually connected. Conversely, there were instances where users were reported as connected when they were not. Has this happened to anyone else? We reported the issue to Zscaler and confirmed a bug and are looking into it not sure if there is a workaround in the meantime anyone discovered? Thanks. </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AdrYvPCQU/zcc-450337-logs-itself-out-automatically","lastmod":"2024-11-05T16:42:45.000Z","id":"0D54u0000AdrYvPCQU"} -->
## ZCC 4.5.0.337 Logs itself out automatically.

- Source: https://community.zscaler.com/s/question/0D54u0000AdrYvPCQU/zcc-450337-logs-itself-out-automatically
- Type: Q&A
- Posted: 2024-11-05T09:31:40.000Z
- Last activity: 2024-11-05T16:42:45.000Z
- Answers: 3
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We are started get log out from the ZCC automatically after a restart (Windows 10). This looks like to be happen after ZCC version &nbsp;4.5.0.337. </p><p>Has anybody else also experienced anything similar? </p><p><br></p><p>Regards</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Ads0fzCQA/various-apps-randomly-stop-launching-on-macs-with-zcc-enabled","lastmod":"2024-11-12T15:36:03.000Z","id":"0D54u0000Ads0fzCQA"} -->
## Various Apps Randomly Stop Launching on MACs with ZCC Enabled

- Source: https://community.zscaler.com/s/question/0D54u0000Ads0fzCQA/various-apps-randomly-stop-launching-on-macs-with-zcc-enabled
- Type: Q&A
- Posted: 2024-11-06T03:00:09.000Z
- Last activity: 2024-11-12T15:36:03.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello All, </p><p><br></p><p>Wondering if anyone has seen or experienced this issue. We recently started deploying ZCC in our environment and we are noticing that on MACs certain desktop apps will fail to launch randomly while ZCC is enabled. When you click on the app, the icon just bounces around in the system tray and does nothing. If you disable ZIA, the app launches immediately we are certain this is Zscaler related. Review of Web and Firewall logs do not show anything that that would explain this. So far this has been observed with Chrome, Edge, Teams and Zoom Clients. Again, this is only being seen on the MACs and not Windows clients. Any help on this would be appreciated as this is starting to become interruptive. Not we have also tried updating to the latest ZCC agents and the issue is presents randomly.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AeFSX8CQO/zcc-packet-capture-analysis","lastmod":"2024-11-13T01:44:14.000Z","id":"0D54u0000AeFSX8CQO"} -->
## ZCC Packet Capture Analysis

- Source: https://community.zscaler.com/s/question/0D54u0000AeFSX8CQO/zcc-packet-capture-analysis
- Type: Q&A
- Posted: 2024-11-12T07:19:17.000Z
- Last activity: 2024-11-13T01:44:14.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I am trying to find documentation on the list of files and their significance in the ZCC packet capture. I know a few but there are many other to know and i want to leverage them to get additional insight on the issue troubleshooting. Any help is greatly appreciated.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AeGGbHCQW/zcc-authentication-using-ms-authenticator","lastmod":"2024-11-21T16:32:46.000Z","id":"0D54u0000AeGGbHCQW"} -->
## ZCC authentication using MS Authenticator

- Source: https://community.zscaler.com/s/question/0D54u0000AeGGbHCQW/zcc-authentication-using-ms-authenticator
- Type: Q&A
- Posted: 2024-11-13T15:40:24.000Z
- Last activity: 2024-11-21T16:32:46.000Z
- Answers: 9
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Currently ZCC first time setup authenticates with text/call on mobile devices (iOS/iPadOS/Android(. Is there a way to instead authenticate via Microsoft Authenticator? Are there bypasses that can be made to allow such when in strict enforcement mode?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AeH55JCQS/how-to-configure-zcc-for-sending-dns-traffic-to-zia","lastmod":"2025-07-29T19:28:10.000Z","id":"0D54u0000AeH55JCQS"} -->
## How To: Configure ZCC for sending DNS traffic to ZIA

- Source: https://community.zscaler.com/s/question/0D54u0000AeH55JCQS/how-to-configure-zcc-for-sending-dns-traffic-to-zia
- Type: Q&A
- Posted: 2024-11-15T03:08:29.000Z
- Last activity: 2025-07-29T19:28:10.000Z
- Answers: 2
- Likes: 4
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><strong>References</strong></p><p><br></p><p><a rel="noopener noreferrer" href="https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles">https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles</a></p><p><a rel="noopener noreferrer" href="https://help.zscaler.com/zscaler-client-connector/best-practices-adding-bypasses-z-tunnel-2.0">https://help.zscaler.com/zscaler-client-connector/best-practices-adding-bypasses-z-tunnel-2.0</a></p><p><a rel="noopener noreferrer" href="https://community.zscaler.com/s/question/0D54u00009evmhmCAA/configuring-client-connector-for-dns-control-and-cloud-firewall">https://community.zscaler.com/s/question/0D54u00009evmhmCAA/configuring-client-connector-for-dns-control-and-cloud-firewall</a></p><p><br></p><p><strong>Overview</strong></p><p><br></p><p>To apply DNS Control policies to DNS traffic, you need to send your DNS traffic to ZIA Cloud. There are various methods of forwarding your DNS traffic to ZIA Cloud, and one of them is to use Zscaler Client Connector (ZCC) with Z-Tunnel 2.0.</p><p><br></p><p><span style="font-size: 16px;"><img src="https://community.zscaler.com/sfc/servlet.shepherd/version/download/0684u00000jXbi3?asPdf=false&amp;operationContext=CHATTER"></span></p><p><br></p><p>In this article we will look into the various ZCC settings for sending DNS traffic to ZIA Cloud when connected using ZCC with ZT2.0.</p><p><br></p><p><strong>Note:&nbsp;</strong>Zscaler Client Connector and VPN Client Interoperability is beyond the scope of this article. Please refer to ZCC help pages for this.</p><h2><br></h2><p><br></p><p><strong>Step-by-step guide</strong></p><p><br></p><p>Go to&nbsp;the Client Connector portal&nbsp;(under&nbsp;Policy > Mobile > Zscaler Client Connector Portal).</p><p>Then go to the tab App Profiles and click on a profile.</p><p><br></p><p>By default the ZCC App Profile contains the following settings under the&nbsp;Z-Tunnel 2.0 Configuration&nbsp;section:</p><p><br></p><p><em>Destinations Exclusions for IPv4:</em></p><ul><li>10.0.0.0/8</li><li>172.16.0.0/12</li><li>192.168.0.0/16</li><li>224.0.0.0/4</li><li>255.255.255.255</li><li>169.254.0.0/16</li></ul><p><em>Destination Inclusions for IPv4:</em></p><ul><li>0.0.0.0/0</li></ul><p><em>Domain Inclusions for DNS Requests:</em></p><ul><li>blank</li></ul><p><em>Domain Exclusions for DNS Requests:</em></p><ul><li>blank</li></ul><p><br></p><p>As one can see:</p><p><br></p><p>Destination Inclusions&nbsp;for IPv4&nbsp;list is 0.0.0.0/0.</p><p>On the other hand,&nbsp;Destination Exclusions&nbsp;for IPv4&nbsp;list is:</p><ul><li>10.0.0.0/8</li><li>172.16.0.0/12</li><li>192.168.0.0/16</li><li>224.0.0.0/4</li><li>255.255.255.255</li><li>169.254.0.0/16</li></ul><p><br></p><p>In general, more specific entries take precedence.</p><p><br></p><p>This means that any traffic (including DNS traffic) that is destined to the IP prefixes specified under the&nbsp;Destination Exclusions&nbsp;list&nbsp;will not be sent to ZIA by the ZCC. For example, if the DNS server address assigned to a client machine is 192.168.86.1, then DNS traffic will not be sent to ZIA.</p><p><br></p><p><strong>In such a case, how can you send DNS traffic to ZIA Cloud?</strong></p><p><br></p><p><strong><em>Option 1: Set DNS server to a public IP (e.g. 8.8.8.8)</em></strong></p><p>One way to overcome this and send DNS traffic to ZIA is by assigning a public DNS resolver's IP address (e.g. 8.8.8.8) as a DNS server address to the client machine. By default, DNS traffic to such a public IP will be intercepted by the ZCC (since it will not match any of the IP prefixes in the&nbsp;Destinations Exclusions&nbsp;list) and will therefore be sent to ZIA (assuming you are using ZT2.0).</p><p><br></p><p>However, this may not be always a feasible option if you have an internal DNS server (with a private IP address) to resolve internal domains. Also, it is possible the client could get assigned a private IP as DNS server address when at home or some other remote location, outside the control of an organization.</p><p><br></p><p><br></p><p><strong><em>Option 2: Use Domain Inclusions and Exclusions lists</em></strong></p><p>Another option is to use&nbsp;<em>Domain Inclusions for DNS Requests</em>&nbsp;and&nbsp;<em>Domain Exclusions for DNS Requests</em>&nbsp;lists.</p><p><br></p><p>&nbsp;For example,&nbsp;to send DNS traffic to ZIA Cloud while excluding specific private domains, do the following:</p><ul><li>Add * to the&nbsp;Domain Inclusions&nbsp;list</li><li>Add the internal domains which you want to exclude from being sent to ZIA in the&nbsp;Domain Exclusions&nbsp;list (e.g. *.lan).</li></ul><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000jXgFa?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p><br></p><p><strong>Note: </strong>Domain inclusions and exclusions&nbsp;take effect only if the DNS server IP address belongs to subnet ranges in the&nbsp;Destinations Exclusions&nbsp;list that are by default excluded from Z-Tunnel 2.0. So if the DNS server address assigned to a client machine is a public IP, such as 8.8.8.8, then Domains Inclusions and Exclusions will not apply to the DNS traffic destined to this public resolver (<strong>Update:</strong> Also see details about the setting "Prioritize DNS Exclusions over Z-Tunnel 2.0": <a rel="noopener noreferrer" href="https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles">https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles</a>).</p><p><br></p><p><br></p><p><strong>﻿Summary</strong></p><p><br></p><p>The limitation of using&nbsp;Domain Inclusions and Exclusions&nbsp;lists&nbsp;is that it requires you to know before hand the list of internal domains for which you want to bypass DNS traffic from being sent to ZIA Cloud.</p><p><br></p><p>However, in general,&nbsp;Domain inclusions and exclusions&nbsp;lists provides&nbsp;a granular control over DNS traffic that is sent to ZIA.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AeHf0UCQS/zscaler-client-connector-for-ios","lastmod":"2024-12-05T16:49:17.000Z","id":"0D54u0000AeHf0UCQS"} -->
## Zscaler Client Connector For iOS

- Source: https://community.zscaler.com/s/question/0D54u0000AeHf0UCQS/zscaler-client-connector-for-ios
- Type: Q&A
- Posted: 2024-11-17T08:32:37.000Z
- Last activity: 2024-12-05T16:49:17.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We noticed that after installing ZCC and setting ZIA/ZPA to "disabled," it still displays "VPN Connected" on iOS.</p><p><br></p><p>Some banking applications won't function properly if they detect that VPN is on (even though we are not using it).</p><p><br></p><p>Currently, the only way to turn off the VPN is by signing out of ZCC.</p><p><br></p><p>Has anyone else experienced this? I don’t recall this being an issue in earlier versions.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AeHrnVCQS/ztunnel-10-vs-ztunnel-20","lastmod":"2024-11-19T11:55:23.000Z","id":"0D54u0000AeHrnVCQS"} -->
## Z-Tunnel 1.0 vs Z-Tunnel 2.0

- Source: https://community.zscaler.com/s/question/0D54u0000AeHrnVCQS/ztunnel-10-vs-ztunnel-20
- Type: Q&A
- Posted: 2024-11-18T08:29:52.000Z
- Last activity: 2024-11-19T11:55:23.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi all,</p><p>sorry if this question has been raised previously. I haven't been able to find the answer here.</p><p>I am curious to know in which scenarios or use cases you are using Z-Tunnel 1.0 instead of Z-Tunnel 2.0.</p><p>According to Zscaler, recommended option is Z-Tunnel 2.0, then:</p><p>Why isn’t Z-Tunnel 2.0 always used? In what situations or use cases is it still necessary to implement Z-Tunnel 1.0?</p><p><br></p><p>https://www.zscaler.com/resources/reference-architectures/secure-mobile-access-with-zscaler-client-connector-reference-architecture.pdf</p><p><img src="/sfc/servlet.shepherd/version/download/0684u00000jXhp0?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AeHy72CQC/how-to-force-zcc-to-login-every-time-computer-starts-and-use-username-and-password","lastmod":"2024-11-22T12:49:04.000Z","id":"0D54u0000AeHy72CQC"} -->
## How to force ZCC to login every time computer starts and use username and password

- Source: https://community.zscaler.com/s/question/0D54u0000AeHy72CQC/how-to-force-zcc-to-login-every-time-computer-starts-and-use-username-and-password
- Type: Q&A
- Posted: 2024-11-18T11:32:51.000Z
- Last activity: 2024-11-22T12:49:04.000Z
- Answers: 5
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>For security purposes customer wants to that every time when computer is restarted user needs to login ZCC. This is now achieved by using short reauthentication interval. But since windows caches authentication token, new authentication does not require password (or mfa). Since im not Entra Id expert does anyone know how can password query be forced for every login attempt for sso?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AeIYE7CQO/changing-install-options-without-reinstalling-the-client-connector","lastmod":"2024-11-25T16:04:53.000Z","id":"0D54u0000AeIYE7CQO"} -->
## Changing Install Options without Reinstalling the Client Connector

- Source: https://community.zscaler.com/s/question/0D54u0000AeIYE7CQO/changing-install-options-without-reinstalling-the-client-connector
- Type: Q&A
- Posted: 2024-11-19T09:44:10.000Z
- Last activity: 2024-11-25T16:04:53.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi all,</p><p>We are facing an upcoming tenant merge. During this procedure we will be changing Zscaler clouds and will need to change the MSI install options (cloudname, userdomain) <strong>without</strong> reinstalling the client connector! Our idea is to change these parameters directly in the registry, log-out/log-in again. </p><p><br></p><p>We are still in the pre-sales phase and can't test this . Does anybody have any experience using this method or any other idea how to do this and is this at all a Zscaler supported procedure?</p><p><br></p><p>thx</p><p>Jon</p><p><br></p><p><img src="/servlet/rtaImage?refid=0EM4u00000ALoSS"></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AeX46cCQC/learning-how-to-troubleshoot-zcc","lastmod":"2024-11-26T10:27:06.000Z","id":"0D54u0000AeX46cCQC"} -->
## Learning how to troubleshoot ZCC

- Source: https://community.zscaler.com/s/question/0D54u0000AeX46cCQC/learning-how-to-troubleshoot-zcc
- Type: Q&A
- Posted: 2024-11-22T16:06:49.000Z
- Last activity: 2024-11-26T10:27:06.000Z
- Answers: 4
- Likes: 2
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>A fairly common question during Zscaler training classes is how to best teach the front line support staff to rapidly collect and analyze Client Connector logs from a user's machine for their application-related error(s).</p><p><br></p><p>As the logs can be a bit overwhelming at first, a bit of orientation and simple tips to build confidence is going to go a long way.</p><p><br></p><p>Without being too exhaustive, here's my simple no-frills answer:</p><p><br></p><ul><li>Provide baseline training on PAC files and, of course, ZCC itself (including packet captures). I don't expect them to debug any PAC or PCAP files, but want everyone to at least have a high level common understanding of where they fit and cool tips like showing how PCAPs and logs align.</li><li>Go to the likely ZSATunnel log (usually the most recent unzipped one) and search for each of the common terms, looking to see if any of them match up with the application that is having trouble (Notepad++ is highly recommended for those running Windows): <ul><li>Exception</li><li>Fail</li><li>Error</li><li>Down</li><li>Crashed</li><li>Invalid</li><li>Compromised</li><li>Detected</li></ul></li><li><strong>ZIA:</strong> If the PAC file is suspected in any way, search for FindProxyForURL to actually see the PAC files in the log. Then see if the action (PAC Parse Host / PAC Parse Action via search) have any glaring issues.</li><li><strong>ZPA:</strong> Look for each of the following for any issues:<ul><li>QRY=SRV(33) -- Useful whenever <a href="https://community.zscaler.com/s/question/0D54u00009jZovjCAC/zscaler-private-access-active-directory" rel="noopener noreferrer" target="_blank">troubleshooting Active Directory</a>. If looking at a PCAP as well, you will notice that the same SRV(33) DNS lookup present -- at the same timestamp.</li><li>mtunnel</li><li>NXDOMAIN</li><li>ERR Connection to ZPN</li><li>100.64.0.6 -- for SYN blocks causing FW/AV error</li><li>Connection Reset by Peer</li><li>Any<a href="https://help.zscaler.com/zpa/about-zpa-session-status-codes" rel="noopener noreferrer" target="_blank"> additional ZPA session status codes</a></li></ul></li></ul><p><br></p><p>So with a just a handful of keyword searches and then focusing on the lines just above and below any discovered application-related events, the real troubleshooting can begin at a much earlier stage. And community.zscaler.com can then help provide even deeper insight and ideas.</p><p><br></p><p>Happy faster troubleshooting!</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AeXaMQCQ0/zcc-and-prisma-access-agent","lastmod":"2024-11-26T04:47:37.000Z","id":"0D54u0000AeXaMQCQ0"} -->
## ZCC and Prisma Access agent

- Source: https://community.zscaler.com/s/question/0D54u0000AeXaMQCQ0/zcc-and-prisma-access-agent
- Type: Q&A
- Posted: 2024-11-25T08:07:07.000Z
- Last activity: 2024-11-26T04:47:37.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p><br></p><p>Has someone tried to configure ZCC for the internet traffic (ZIA) and at the same time, in the same machine, having the agent for Prisma Access (PaloAlto) only used to get access to private applications?</p><p>Is this doable?</p><p>Thanks,</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AeZAwSCQW/o365-authentication-issue-in-zcc-45","lastmod":"2024-12-05T08:10:39.000Z","id":"0D54u0000AeZAwSCQW"} -->
## O365 authentication Issue in ZCC 4.5

- Source: https://community.zscaler.com/s/question/0D54u0000AeZAwSCQW/o365-authentication-issue-in-zcc-45
- Type: Q&A
- Posted: 2024-11-28T07:28:57.000Z
- Last activity: 2024-12-05T08:10:39.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

We upgraded ZCC from 4.3 to 4.5 recently. Post that random users face 0365 app authentication [MsTeams, Outlook, Onedrive etc] every day. We use strict enforcement during our installation. O365 caches the authentication for several hours. when they reauthenticate again, they face issue.

**Workaround:**

a) Reinstall ZCC 4.5 [same version] without strict enforcement.

b) Restart ZCC service or Repair -? This failopen for 5 to 10sec. O365 authenticated within the failopen and stays connected for several hours. Issue repeats next day.

Did anyone upgrade to 4.5 and faced this issue? How did you sort it?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Af3UlQCQU/unable-to-access-a-certain-website-while-zcc-and-vpn-is-enabled-but-when-using-other-isp-all-is-working","lastmod":"2024-12-09T09:37:08.000Z","id":"0D54u0000Af3UlQCQU"} -->
## Unable to access a certain website while ZCC and VPN is enabled BUT, when using other ISP all is working

- Source: https://community.zscaler.com/s/question/0D54u0000Af3UlQCQU/unable-to-access-a-certain-website-while-zcc-and-vpn-is-enabled-but-when-using-other-isp-all-is-working
- Type: Q&A
- Posted: 2024-12-08T23:37:20.000Z
- Last activity: 2024-12-09T09:37:08.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p><br></p><p>We have a staff here that experiencing this kind of issue where she can't access specific websites while the ZCC and VPN are enabled. She is using the Dodo ISP at home. Currently, her ZCC version is 4.5.0.286 from the old device, currently, we have provided her a new one, 1.5.1.8 that is scheduled to be patched in the next few weeks. </p><p><br></p><p>On the ZIA portal, we already created a test policy only for her (e.g., SSL bypassing, and URL categories), but the issue persists. </p><p><br></p><p>Is there anyone here who uses Dodo and experiences the same thing?</p><p><br></p><p>As of now, we are currently in touch with the user to gather some logs from her device either new or old.</p><p><br></p><p>For now, I wanna know if anybody else experienced the same thing, if so, how did you troubleshoot and resolve the issue?</p><p><br></p><p>If anyone can also advise on some troubleshooting procedures, that would be a big help.</p><p><br></p><p>Recent Troubleshooting I've done so far,</p><ol><li>Create a test SSL bypass and URL category (not work).</li><li>Ask the user to try using a different network (works at a different house not sure what ISP it uses)</li><li>Hotspot is not working as well</li><li>Disable/enable ZCC and VPN (working when both are disabled)</li></ol><p>Thank you for your time and response.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Af4tEBCQY/dubai-etisilat-issues-using-zscaler","lastmod":"2025-02-11T06:35:59.000Z","id":"0D54u0000Af4tEBCQY"} -->
## Dubai Etisilat issues using Zscaler

- Source: https://community.zscaler.com/s/question/0D54u0000Af4tEBCQY/dubai-etisilat-issues-using-zscaler
- Type: Q&A
- Posted: 2024-12-09T13:16:34.000Z
- Last activity: 2025-02-11T06:35:59.000Z
- Answers: 4
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi there. Has anyone in the community come across issues using Etisilat broadband in Dubai ?. I have several users who have significant performance issues using ZCC from home only using Etisilat home broadband. With ZCC turned on they are only achieving very slow speeds, however testing without ZCC turned on I get several hundred Mbps throughput. It doesnt happen for users using the alternative in region provider (Du). Wondering if it may be an issue with the provider home router blocking Zscaler protocols or a NAT issue but would be interested if anyone in the community has had this issue in Dubai also ? Thanks in advance.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Af9qXGCQY/use-zscaler-client-connector-in-iran","lastmod":"2024-12-16T12:57:44.000Z","id":"0D54u0000Af9qXGCQY"} -->
## Use Zscaler Client Connector in Iran

- Source: https://community.zscaler.com/s/question/0D54u0000Af9qXGCQY/use-zscaler-client-connector-in-iran
- Type: Q&A
- Posted: 2024-12-11T07:00:25.000Z
- Last activity: 2024-12-16T12:57:44.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><span style="font-family: &quot;YS Text&quot;, -apple-system, BlinkMacSystemFont, Arial, Helvetica, sans-serif;">I need to go to Iran. Can I use the Zscaller Client there? We are talking only about restrictions on the part of the Zcaller infrastructure, I am aware of the restrictions of the Iranian Government.</span></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AfEvoKCQS/zpa-server-to-client-configuration-using-zcc","lastmod":"2024-12-31T14:33:45.000Z","id":"0D54u0000AfEvoKCQS"} -->
## ZPA Server to client configuration Using ZCC

- Source: https://community.zscaler.com/s/question/0D54u0000AfEvoKCQS/zpa-server-to-client-configuration-using-zcc
- Type: Q&A
- Posted: 2024-12-12T16:38:55.000Z
- Last activity: 2024-12-31T14:33:45.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>So we have an interesting and short term use case to have a specific server talk to remote clients so we can inventory them and kick off a domain migration. Iddeally we would use a branch connector but its not in scope for the timeline we have. So i intend to install ZCC on the server and use a service account to login. Make a profile that turns ZPA on on premise for that user and use that to get connectivty to other clients configured for hostname resolution. I am pretty confident in this working well. While on prem i would like the client for just that server to use on premise resources locally and not through the connector but use the client to do client to client communication. I am thinking about using the bypasses in the client to accomplish this but wanted to throw this out there in case someone has a better idea of how to accomplish this. Thank You</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AfJQLRCQ4/zcc-api-returns-service-unavailable-503-error","lastmod":"2025-01-14T10:20:26.000Z","id":"0D54u0000AfJQLRCQ4"} -->
## ZCC API returns service unavailable (503) error

- Source: https://community.zscaler.com/s/question/0D54u0000AfJQLRCQ4/zcc-api-returns-service-unavailable-503-error
- Type: Q&A
- Posted: 2024-12-16T08:20:07.000Z
- Last activity: 2025-01-14T10:20:26.000Z
- Answers: 4
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I am <strong>unable</strong> to send API requests to certain ZCC API endpoints.</p><p>e.g.</p><p>{</p><p> "timestamp": "2024-12-16T08:12:58.117+00:00",</p><p> "path": "/papi/public/v1/getDevices",</p><p> "status": 503,</p><p> "error": "Service Unavailable",</p><p> "requestId": "5e1b79a4-13169"</p><p>} </p><p><br></p><p>Other endpoints I am <strong>able</strong> to reach.</p><p>e,g. <em>/papi/public/v1/getOtp</em></p><p><br></p><p>Any idea what might be the issue?</p><p>It was working a week ago and suddenly it stopped.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AfXGoLCQW/zcc-logs","lastmod":"2024-12-19T13:41:33.000Z","id":"0D54u0000AfXGoLCQW"} -->
## ZCC Logs

- Source: https://community.zscaler.com/s/question/0D54u0000AfXGoLCQW/zcc-logs
- Type: Q&A
- Posted: 2024-12-18T08:40:20.000Z
- Last activity: 2024-12-19T13:41:33.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I would like to gain a deeper understanding of how to interpret ZCC logs effectively. Could you provide comprehensive documentation or guidance on reading and analyzing ZCC logs, including details about key fields, log structure, and troubleshooting insights?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AfZNqtCQG/how-to-setup-zcc-agent-for-autologin-using-windows-login-account-","lastmod":"2025-10-24T10:17:55.000Z","id":"0D54u0000AfZNqtCQG"} -->
## How to Setup ZCC Agent for AutoLogin Using Windows Login Account ?

- Source: https://community.zscaler.com/s/question/0D54u0000AfZNqtCQG/how-to-setup-zcc-agent-for-autologin-using-windows-login-account-
- Type: Q&A
- Posted: 2024-12-23T22:07:04.000Z
- Last activity: 2025-10-24T10:17:55.000Z
- Answers: 7
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Dear Community Members,</p><p><br></p><p>My Company is in a situation where we need to re-install ZCC Agent on some devices ~1800. The devices are windows OS based and mix of workstation , laptops. The laptop users are connected sometimes from the Home Office.</p><p><br></p><p>We use Intune to deploy ZCC Agent, our current Infrastructure is Hybrid Domain (On Prem AD + Entra ID, aka AzureAD). </p><p><br></p><p>When we re-install ZCC Agent 4.3.0.202 from Intune we except the Users to do nothing. So please let me know what is the configuration i need to make in Zscaler Portal and what are the .msi options i need to use to achieve the desired situation ? </p><p><br></p><p>I am Curious to know what is "EnableSSOUsingWindowsPrimaryAccount " registry key means and How does it help us ?</p><p><br></p><p>In Zscaler Admin Portal "Pre-Populate Client Connector Username" is enabled Using Javascript. see below picutre<img src="/sfc/servlet.shepherd/version/download/0684u00000jyyNR?asPdf=false&amp;operationContext=CHATTER"></p><p>I also tried to create .mst file with options USERDOMAIN, CLOUDNAME. We do not use STRICTENFORCEMENT</p><p><br></p><p>I raise ticket withe Zscaler Support but it seems like the Support Engineer is not able to find the information which could help to achieve the desired behaviour.</p><p><br></p><p>We no more use ADF so using ADFS with Integrated Windows Authentication is not viable for my company. we have seamless SSO login enabled for Web Authentication and on the ZCC we just have to enter the email address and click on Login.</p><p><br></p><p>We wanted to avoid users entering the email address but complete the login without strict enforcement set.</p><p><br></p><p>Thank you and Best Regards</p><p>Sriram</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AfvuxuCQA/zcc-uninstall-passwords-good-to-know","lastmod":"2026-02-03T14:05:14.000Z","id":"0D54u0000AfvuxuCQA"} -->
## ZCC Uninstall Passwords - Good to Know

- Source: https://community.zscaler.com/s/question/0D54u0000AfvuxuCQA/zcc-uninstall-passwords-good-to-know
- Type: Q&A
- Posted: 2025-01-10T19:42:50.000Z
- Last activity: 2026-02-03T14:05:14.000Z
- Answers: 11
- Likes: 5
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I recently had a customer stumble upon an unusual scenario with uninstall passwords that has brought this "issue" to my attention, and I feel it beneficial to share. </p><p><br></p><p>A while back, Zscaler introduced "Unattended Uninstall Password" option under the Platform settings in the mobile portal. This is meant to allow for GPO or SCCM script uninstallation with a token rather than using the uninstall password configured in the App Profile. </p><p><br></p><p>As most new features, this comes disabled by default. What this causes though is the unintended consequence that anyone with Admin privileges on the devices can now uninstall ZCC without the need for a password. </p><p><br></p><p>Both of these must be configured to ensure no unauthorized uninstalls take place.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000R8fMe0AJ/machine-tunnel-with-client-certcomputer-cert-not-user-cert-and-county-codes","lastmod":"2025-01-23T11:00:55.000Z","id":"0D5PJ00000R8fMe0AJ"} -->
## machine tunnel with client cert(computer cert not user cert) and county codes

- Source: https://community.zscaler.com/s/question/0D5PJ00000R8fMe0AJ/machine-tunnel-with-client-certcomputer-cert-not-user-cert-and-county-codes
- Type: Q&A
- Posted: 2025-01-17T21:22:38.000Z
- Last activity: 2025-01-23T11:00:55.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello:</p><p>has anyone tried machine tunnel with computer cert(posture checks) and country codes?</p><p>thanks</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ROppq0AD/zcc-authentication-period-and-policies","lastmod":"2025-02-10T21:02:18.000Z","id":"0D5PJ00000ROppq0AD"} -->
## ZCC authentication period and policies

- Source: https://community.zscaler.com/s/question/0D5PJ00000ROppq0AD/zcc-authentication-period-and-policies
- Type: Q&A
- Posted: 2025-01-23T04:46:37.000Z
- Last activity: 2025-02-10T21:02:18.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I would like to enquiry on Mobile Devices with ZCC on the below :</p><p>1) Can we select and specify some mobile devices to be login for X number of days ?</p><p>2) Can we selective some mobile devices to be excluded from the login authentication policy or period, Eg, if yes what are the frequency available ? Daily, Once a week, a month or 3 months etc</p><p>3) For mobile devices running both IOS and Android, both with ZCC above can be achieved ? </p><p><br></p><p>Pl advise where to configures the above ?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000RTSgA0AX/app-connector-enrollment-issue","lastmod":"2025-08-22T07:28:02.000Z","id":"0D5PJ00000RTSgA0AX"} -->
## App connector Enrollment issue

- Source: https://community.zscaler.com/s/question/0D5PJ00000RTSgA0AX/app-connector-enrollment-issue
- Type: Q&A
- Posted: 2025-01-24T11:09:17.000Z
- Last activity: 2025-08-22T07:28:02.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi There,</p><p><br></p><p>We are getting below error message while provisioning the new app connector </p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/068PJ00000GGzCq?asPdf=false&amp;operationContext=CHATTER"></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000RaZZD0A3/password-less-logout-of-zpa-on-zscaler-client-connector","lastmod":"2025-01-28T04:17:03.000Z","id":"0D5PJ00000RaZZD0A3"} -->
## Password Less Logout of ZPA on Zscaler Client Connector

- Source: https://community.zscaler.com/s/question/0D5PJ00000RaZZD0A3/password-less-logout-of-zpa-on-zscaler-client-connector
- Type: Q&A
- Posted: 2025-01-27T07:14:48.000Z
- Last activity: 2025-01-28T04:17:03.000Z
- Answers: 4
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi, our organization have different vendors for support. They need to access our Private Applications, so we have provided them with ZPA access. </p><p>Since our vendor provide services to different customers, they want to turn off ZPA. Our top management has disallowed sharing of password to logout on zscaler client connector.</p><p>Please let me know, how can I create an App Profile where user is able to turn off ZPA in ZCC without entering any password. Also let me know whether this is feasible or not.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000SqYDw0AN/login-pumps-off-after-desktop-unlock","lastmod":"2025-02-20T14:33:13.000Z","id":"0D5PJ00000SqYDw0AN"} -->
## Login pumps off after desktop unlock

- Source: https://community.zscaler.com/s/question/0D5PJ00000SqYDw0AN/login-pumps-off-after-desktop-unlock
- Type: Q&A
- Posted: 2025-02-17T02:16:30.000Z
- Last activity: 2025-02-20T14:33:13.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi All,</p><p><br></p><p>We have just started rolling out ZCC on surface pro desktops and after installing ZCC, when the user tries to unlock the desktop, the user will be asked to add the login with user/pass again. The user can cancel the login with no issues. But is there anyway to not get this login pop out? </p><p><br></p><p>Thank you. </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000TFyq80AD/zcc-roll-out-and-auth-question","lastmod":"2025-03-04T08:26:04.000Z","id":"0D5PJ00000TFyq80AD"} -->
## ZCC roll out and auth question

- Source: https://community.zscaler.com/s/question/0D5PJ00000TFyq80AD/zcc-roll-out-and-auth-question
- Type: Q&A
- Posted: 2025-02-24T12:27:55.000Z
- Last activity: 2025-03-04T08:26:04.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi all</p><p><br></p><p>i've read the deployment docs and all that but just wanted to understand when exactly do we push the client to all machines via whatever deployment we are using.</p><p><br></p><p>And what should be the bare min config on the agent or the portal to do this?</p><p><br></p><p>And finally once I deployment the zcc agent, do all users manually have to sign in to the client agent to register with the ZTE? So do people just email everyone to start zs scaler and ask them to login? Or is there a way to do it automatically in the background?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000TK9AM0A1/application-security-product-of-zscaler","lastmod":"2025-03-03T10:51:30.000Z","id":"0D5PJ00000TK9AM0A1"} -->
## Application Security product of Zscaler

- Source: https://community.zscaler.com/s/question/0D5PJ00000TK9AM0A1/application-security-product-of-zscaler
- Type: Q&A
- Posted: 2025-02-25T10:38:13.000Z
- Last activity: 2025-03-03T10:51:30.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello All,</p><p><br></p><p>I am not sure does Zscaler provide any solution for Internet hosted application security or not. Please help me know the solution if there are any.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000TojUj0AJ/zcc-corporate-vpn","lastmod":"2025-03-07T11:15:35.000Z","id":"0D5PJ00000TojUj0AJ"} -->
## ZCC - Corporate VPN

- Source: https://community.zscaler.com/s/question/0D5PJ00000TojUj0AJ/zcc-corporate-vpn
- Type: Q&A
- Posted: 2025-03-05T14:53:01.000Z
- Last activity: 2025-03-07T11:15:35.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Team,</p><p>Looking for some insight on the expected behavior for the below scenario.</p><p><br></p><p><u>Scenario - Windows ZCC Tunnel v2</u></p><p>User A, a remote worker, connects to the corporate VPN (Cisco). Before Zscaler was implemented, the public IP changed according to the VPN profile selected by the end-user.</p><p>&nbsp;</p><p>Now, with Zscaler ZIA/ZCC, the user's public IP changes to Zscaler IPs (which is expected). However, should the Zscaler IP also change according to the VPN profile location? For example, User A based in Spain connects to a UK VPN GW, but ZCC and speedtest.zscaler.com still shows Zscaler Spanish DCs instead of Zscaler's UK DCs (closer to the VPN GW)</p><p><br></p><p>Is this expected behavior or should be investigated?</p><p>Thanks</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000U3DES0A3/machine-tunnel-app-profile","lastmod":"2025-03-11T05:32:31.000Z","id":"0D5PJ00000U3DES0A3"} -->
## machine tunnel app profile

- Source: https://community.zscaler.com/s/question/0D5PJ00000U3DES0A3/machine-tunnel-app-profile
- Type: Q&A
- Posted: 2025-03-10T11:05:04.000Z
- Last activity: 2025-03-11T05:32:31.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>hi,</p><p>i am testing machine tunnel.</p><p>i have 2 app profile for windows.</p><p>profile 1 is machine for machine tunnel</p><p>user group and user set as none. machine token is selected. </p><p>profile 2 is for all user.</p><p>user group is set as all. and no machine token selected.</p><p><br></p><p>i install my computer with policy token from profile 1.</p><p>machine tunnel is up and running. but profile name is shown as "profile 2"</p><p>is this a correct configuration? </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000V6Op20AF/how-to-remove-unused-zscaler-clouds-from-zcc-login-options","lastmod":"2025-04-29T08:49:03.000Z","id":"0D5PJ00000V6Op20AF"} -->
## How to Remove Unused Zscaler Clouds from ZCC Login Options

- Source: https://community.zscaler.com/s/question/0D5PJ00000V6Op20AF/how-to-remove-unused-zscaler-clouds-from-zcc-login-options
- Type: Q&A
- Posted: 2025-03-28T14:13:19.000Z
- Last activity: 2025-04-29T08:49:03.000Z
- Answers: 1
- Likes: 3
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>When your users log into the Zscaler Client Connector (ZCC), they may be presented with a list of available clouds to choose from. This occurs if your organization is provisioned on multiple Zscaler clouds, offering users the option to select the cloud where their traffic will be routed. However, there may be instances where users don't want to see this full list of clouds, especially if some of the clouds—such as "<a href="http://zscalertwo.net/" rel="noopener noreferrer" target="_blank">zscalertwo.net</a>"—are unused or not relevant to their operations.</p><p>If you want to remove these unused clouds from the login screen and streamline the process, here’s how to do it:</p><p>The Solution: Pre-configure ZCC to Automatically Connect to the Intended Cloud</p><p>To avoid displaying a list of clouds during the login process, you can pre-configure the Zscaler Client Connector (ZCC) to automatically connect users to the designated cloud. This can be done using the installation option that specifies the cloud your organization uses.</p><p><br></p><p><strong>What to Do if the Cloud Information is Incorrect</strong></p><p>If the "ZIA Organization ID and Cloud Name" are incorrect, or if the user unexpectedly sees the cloud selection prompt, the issue might stem from provisioning settings. In such cases, it's advisable to contact Zscaler Support by submitting a "Provisioning" support ticket. Include the following details:</p><p>- A screenshot of the issue.</p><p>- The ZPA Tenant ID information.</p><p><br></p><p><strong>Does This Belong to the Provisioning Team?</strong></p><p>Yes, this issue typically falls under the provisioning team's responsibilities. They handle the setup and configuration of Zscaler clouds for your organization. If you're encountering issues with incorrect cloud information or unwanted cloud prompts, the provisioning team should be your first point of contact.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000VB9UM0A1/need-help-on-pushing-zcc-via-app-profile-token","lastmod":"2025-04-04T14:58:27.000Z","id":"0D5PJ00000VB9UM0A1"} -->
## Need help on pushing ZCC via App profile token

- Source: https://community.zscaler.com/s/question/0D5PJ00000VB9UM0A1/need-help-on-pushing-zcc-via-app-profile-token
- Type: Q&A
- Posted: 2025-03-30T11:58:49.000Z
- Last activity: 2025-04-04T14:58:27.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>While installing zcc via app policy token and strict enforcement flag as 1. ZCC is not getting downloaded via intune.</p><p>Any comments or help related to this</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000W9LMA0A3/zcc-client-connector-error-14-","lastmod":"2025-04-18T15:27:20.000Z","id":"0D5PJ00000W9LMA0A3"} -->
## ZCC client connector error -14 ??

- Source: https://community.zscaler.com/s/question/0D5PJ00000W9LMA0A3/zcc-client-connector-error-14-
- Type: Q&A
- Posted: 2025-04-16T14:20:41.000Z
- Last activity: 2025-04-18T15:27:20.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have a user computer that was recently upgraded to Windows 11. We have seen MANY issues around the client connectors and windows 11 updates. The support staff was trying to log back into the pc after having logged out after initially getting an FW/AV errr. After attempting a fix the connector was showing "connection error". We logged the user account out of the connector. Now when attempting to log back in we are getting a message "Internal Error, Please contact Admin...[-14]" . Has anyone here on the forum seen this error before? Is this tied to the pc firewall possibly? Please let me know if you do. Thank you.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000Watzo0AB/zscaler-cloud-name-retention-for-zcc-cleint","lastmod":"2025-04-28T05:13:11.000Z","id":"0D5PJ00000Watzo0AB"} -->
## Zscaler cloud  name retention for ZCC cleint

- Source: https://community.zscaler.com/s/question/0D5PJ00000Watzo0AB/zscaler-cloud-name-retention-for-zcc-cleint
- Type: Q&A
- Posted: 2025-04-24T22:16:18.000Z
- Last activity: 2025-04-28T05:13:11.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi All,</p><p>We have the following issue with ZCC</p><p>A user have to provide Zscaler cloud name every time after a user logs out from ZCC client. Is there a way to retain Zscaler Cloud name for ZCC client?</p><p>Thanks,</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000XRoe40AD/t64-libs-version-selected-instead-of-required-by-linux-connector-installer","lastmod":"2025-05-21T10:56:26.000Z","id":"0D5PJ00000XRoe40AD"} -->
## t64 libs version selected instead of required by Linux connector installer

- Source: https://community.zscaler.com/s/question/0D5PJ00000XRoe40AD/t64-libs-version-selected-instead-of-required-by-linux-connector-installer
- Type: Q&A
- Posted: 2025-05-10T15:40:11.000Z
- Last activity: 2025-05-21T10:56:26.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello, I have downloaded Linux Zscaler client installer (as run file: <strong>Zscaler-linux-1.5.1.27-installer.run</strong>).</p><p><br></p><p>I run installer following the process described here https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux. </p><p><br></p><p>Installation process is done for 99%, when I get following <strong>error</strong>: "<em> libglib2.0-0, libqt5dbus5, libqt5core5a, libqt5gui5, libqt5opengl5, libqt5widgets5, libpcap0.8 Failed installation due to missing dependency : , libglib2.0-0, libqt5dbus5, libqt5core5a, libqt5gui5, libqt5opengl5, libqt5widgets5, libpcap0.8"</em>. </p><p><br></p><p>If I execute installation of ZScaler dependencies (as descibed in link posted above) or I run "<em>apt-get install</em>" for all those libraries I can clearly see in output I have *t64 libs versions of libraries already installed - apt output: <em>Note, selecting 'libglib2.0-0t64' instead of 'libglib2.0-0', Note, selecting 'libqt5core5t64' instead of 'libqt5core5a', Note, selecting 'libqt5sql5t64' instead of 'libqt5sql5', Note, selecting 'libpcap0.8t64' instead of 'libpcap0.8' etc</em>.</p><p><br></p><p>I have <strong>Ubuntu 24.04.2 LTS </strong>(noble), here is <em>uname -a</em> command output: 6.11.0-25-generic #25~24.04.1-Ubuntu SMP PREEMPT_DYNAMIC Tue Apr 15 17:20:50 UTC 2 x86_64 x86_64 x86_64 GNU/Linux.</p><p><br></p><p>Could you please help me somehow?</p><p><br></p><p>Thanks &amp; regards,</p><p>Peter</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000XaAJV0A3/verifying-zscaler-client-connector-installation-via-registry","lastmod":"2025-05-15T11:02:49.000Z","id":"0D5PJ00000XaAJV0A3"} -->
## Verifying Zscaler Client Connector Installation via Registry

- Source: https://community.zscaler.com/s/question/0D5PJ00000XaAJV0A3/verifying-zscaler-client-connector-installation-via-registry
- Type: Q&A
- Posted: 2025-05-13T13:50:34.000Z
- Last activity: 2025-05-15T11:02:49.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p>I am currently developing an automated verification process to check whether the Zscaler Client Connector is installed on our Windows computers and to confirm the installed version. I plan to utilize registry keys located under:</p><p><strong>HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall</strong></p><p>Could someone please clarify exactly what values I should expect for the following registry fields when Zscaler Client Connector is installed?</p><ul><li><strong>DisplayName</strong></li><li><strong>DisplayVersion</strong></li><li><strong>Publisher</strong></li></ul><p>Specifically, I'm interested in real-world examples of these fields to accurately detect the presence and exact version of the Zscaler Client Connector. If there are variations based on different versions or deployment methods, please mention that as well. If you have some general knowledge or experience with Zscaler Client Connector values in that registry keys, I will be happy to hear about it.</p><p>Thank you very much! :-)</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000XzIJc0AN/zcc-disconnections-and-reconnections-occur-repeatedly-in-a-short-period","lastmod":"2026-03-10T13:59:41.000Z","id":"0D5PJ00000XzIJc0AN"} -->
## ZCC disconnections and re-connections occur repeatedly in a short period

- Source: https://community.zscaler.com/s/question/0D5PJ00000XzIJc0AN/zcc-disconnections-and-reconnections-occur-repeatedly-in-a-short-period
- Type: Q&A
- Posted: 2025-05-20T04:52:15.000Z
- Last activity: 2026-03-10T13:59:41.000Z
- Answers: 5
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi, all.</p><p><br/></p><p>Our staffs have an issue with the ZCC that ZCC repeats disconnections and re-connections of ZIA/ZPA repeatedly.</p><p>The issue occurs randomly and about 10 of 500+ staffs have this issue.</p><p>Based on my investigation, this issue occurs only using wireless communications on Windows OS.</p><p>And this issue have been reported since I updated ZCC from 4.1.0.96 to 4.5.0.337. (I updated ZCC to 4.5.0.434 but still happened)</p><p><img src="/sfc/servlet.shepherd/version/download/068PJ00000L55xR?asPdf=false&amp;operationContext=CHATTER"></p><p><br/></p><p>What I have done is below:</p><ol><li>I changed some configurations of Wifi adaptor (multi-bandwidth selection, power consumption and so on...)</li><li>I updated Wifi driver to latest version</li><li>I added many policies in FW that allow every communications related to Zscaler.</li><li>I found there were no network issues and this issue occurs not only at office, but also on each staff's home or outside.</li><li>I also found this issue don't occur in wired environment.</li><li>I disabled IPv6 on Wifi adaptor.</li><li>There have been no such issue on Mac OS.</li></ol><p><br/></p><p>All network communications are temporarily interrupted while the problem occurs.</p><p>I can see many ZCC notifications that shows disconnections and re-connections sine communications with SME(Service Management Engine/***.sme.zscalerthree.net) was failed.</p><p>But I think the failed communications are only results of this issue, not a root cause.</p><p><br/></p><p>As a result of my all investigations, it seems that there are conflicts between ZCC and wireless network adapter(or driver).</p><p>I have opened many tickets on Zscaler support portal, but a root cause has not yet been determined.</p><p>Has anyone already had such an issue?</p><p>I would appreciate any advice on this issue.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000YEyCS0A1/zcc-api-endpoint-404","lastmod":"2025-05-27T14:09:45.000Z","id":"0D5PJ00000YEyCS0A1"} -->
## ZCC API Endpoint 404

- Source: https://community.zscaler.com/s/question/0D5PJ00000YEyCS0A1/zcc-api-endpoint-404
- Type: Q&A
- Posted: 2025-05-23T18:52:27.000Z
- Last activity: 2025-05-27T14:09:45.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Has anyone been able to use the /papi/public/v1/downloadDisableReasons endpoint?</p><p><br></p><p>I keep getting a 404 error. I tried using both the mobile.zscaler and oneAPI base URL but still same outcome.</p><p><br></p><p>Thanks</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000YbkIc0AJ/zcc-strict-enforcement-https-sites-dont-prompt-to-keep-you-safe","lastmod":"2025-06-04T05:50:54.000Z","id":"0D5PJ00000YbkIc0AJ"} -->
## ZCC Strict Enforcement -- https sites don't prompt "to keep you safe..."

- Source: https://community.zscaler.com/s/question/0D5PJ00000YbkIc0AJ/zcc-strict-enforcement-https-sites-dont-prompt-to-keep-you-safe
- Type: Q&A
- Posted: 2025-05-30T18:15:12.000Z
- Last activity: 2025-06-04T05:50:54.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Has any one else determined where in policy or pac file to fix the issue where https sites do not show the "to keep you safe..." redirect to login to ZCC agent when strict enforcement is enabled?</p><p><br></p><p>We can use the ip.zscaler.com page as an example. If the user puts in http://ip.zscaler.com the page is replaced with the ZCC prompt to log into ZCC. If they use https, it shows the browser default "cannot connect" page.</p><p><br></p><p>Similar behavior in Edge, Chrome, and Firefox.</p><p><br></p><p>The device is connected to a hotspot to emulate a "public wifi" connection, not connected via VPN or otherwise to our on-prem environment or Zscaler Pzens proxies. </p><p><br></p><p>The default route, except for specific URLs to get to the help desk system, teams, email is to route to the Zscaler Cloud.</p><p><br></p><p>The tunnel is set to "v1".</p><p><br></p><p>Thanks!</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000Z1Jko0AF/unable-to-login-with-correct-domain-in-zcc","lastmod":"2025-06-25T06:45:05.000Z","id":"0D5PJ00000Z1Jko0AF"} -->
## Unable to login with correct domain in ZCC

- Source: https://community.zscaler.com/s/question/0D5PJ00000Z1Jko0AF/unable-to-login-with-correct-domain-in-zcc
- Type: Q&A
- Posted: 2025-06-05T13:04:40.000Z
- Last activity: 2025-06-25T06:45:05.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>ZCC is fetching different domain when trying to login ZCC.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000Z1KFn0AN/is-windows-datacenter-2019-a-supported-os-for-zcc-in-an-aws-workspace-instance","lastmod":"2025-06-11T20:00:51.000Z","id":"0D5PJ00000Z1KFn0AN"} -->
## Is Windows Datacenter 2019 a supported OS for ZCC in an AWS Workspace instance?

- Source: https://community.zscaler.com/s/question/0D5PJ00000Z1KFn0AN/is-windows-datacenter-2019-a-supported-os-for-zcc-in-an-aws-workspace-instance
- Type: Q&A
- Posted: 2025-06-05T13:16:05.000Z
- Last activity: 2025-06-11T20:00:51.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p><strong style="font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif; font-size: 15px;">CONTEXT:</strong></p><p><span style="font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif; font-size: 15px;">The Zscaler Client Connector Supported Versions page (&nbsp;</span><a href="https://help.zscaler.com/eos-eol/supported-versions" rel="noopener noreferrer" target="_blank" style="font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif; font-size: 15px;">https://help.zscaler.com/eos-eol/supported-versions</a><span style="font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif; font-size: 15px;">&nbsp;) states "Amazon Workspaces supports Zscaler Client Connector all versions". What the Zscaler support page for Zscaler Client Connector does not detail is "what operating systems" within the Amazon Workspace are supported. As it reads today, at first glance it could be any/every operating system with an AWS Workspace.The AWS support page (&nbsp;</span><a href="https://docs.aws.amazon.com/workspaces/latest/adminguide/amazon-workspaces.html" rel="noopener noreferrer" target="_blank" style="font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif; font-size: 15px;">https://docs.aws.amazon.com/workspaces/latest/adminguide/amazon-workspaces.html</a><span style="font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif; font-size: 15px;">&nbsp;) states "Amazon WorkSpaces enables you to provision virtual, cloud-based desktops known as&nbsp;</span><em style="font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif; font-size: 15px;">WorkSpaces</em><span style="font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif; font-size: 15px;">&nbsp;for your users. These desktops can run Microsoft Windows, Amazon Linux 2, Ubuntu Linux, Rocky Linux, or Red Hat Enterprise Linux."</span></p><p><br></p><p><strong style="font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif; font-size: 15px;"><span class="ql-cursor">﻿</span>ASK:</strong></p><p><span style="font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif; font-size: 15px;">Customer is running Windows Datacenter 2019 as the OS in their AWS Workspace with ZCC version 4.4.500.19 and it's works in this configuration.</span></p><p><span style="font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif; font-size: 15px;">Upgrading to Version 4.6.0.200 does not work.</span></p><p><span style="font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif; font-size: 15px;">Support has suggested migrating to ZCC 4.7.x.</span></p><p><span style="font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif; font-size: 15px;">﻿</span></p><p><strong style="font-family: Slack-Lato, Slack-Fractions, appleLogo, sans-serif; font-size: 15px;">Is Windows Datacenter 2019 a supported OS for ZCC in an AWS Workspace instance?</strong></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ZYTWp0AP/error-3049-on-chromeos-zcc","lastmod":"2025-12-11T13:42:06.000Z","id":"0D5PJ00000ZYTWp0AP"} -->
## error 3049 on ChromeOS ZCC

- Source: https://community.zscaler.com/s/question/0D5PJ00000ZYTWp0AP/error-3049-on-chromeos-zcc
- Type: Q&A
- Posted: 2025-06-16T11:10:58.000Z
- Last activity: 2025-12-11T13:42:06.000Z
- Answers: 6
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Anyone have any additional information on what this error is and how to resolve it on ChromeOS? Somehow it auto resolved but not sure why. We tried uninstalling/reinstalling, restarting but that didn't help.</p><p><br></p><p>Failed to Register ZPA: ZPN signing failed (3049)</p><p><br></p><p>ZCC 3.10.0.47</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/068PJ00000MBUe1?asPdf=false&amp;operationContext=CHATTER"></p><p><img src="/sfc/servlet.shepherd/version/download/068PJ00000MBUkT?asPdf=false&amp;operationContext=CHATTER"></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ZlZ8P0AV/how-to-reliably-distinguish-between-client-connector-and-network-adapter","lastmod":"2025-07-25T08:25:20.000Z","id":"0D5PJ00000ZlZ8P0AV"} -->
## How to reliably distinguish between Client Connector and Network Adapter?

- Source: https://community.zscaler.com/s/question/0D5PJ00000ZlZ8P0AV/how-to-reliably-distinguish-between-client-connector-and-network-adapter
- Type: Q&A
- Posted: 2025-06-19T12:17:54.000Z
- Last activity: 2025-07-25T08:25:20.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi all,</p><p>I'm hoping someone in the community can help with a software detection challenge.</p><p>Our goal is to accurately detect the installed version of Zscaler Client Connector to identify if an upgrade is needed due to a reported security vulnerability.</p><p>However, we're finding it difficult to distinguish between the "Zscaler Client Connector" and the "Zscaler Network Adapter" by looking at the Windows registry. In the registry, it appears both components share the same <span style="font-family: &quot;Google Sans Text&quot;, sans-serif;">DisplayName</span>, which is simply "Zscaler".</p><p>This makes it challenging to reliably target the Client Connector for version checking without confusing it with the Network Adapter.</p><p>Does anyone know of a reliable method to differentiate between the two in the registry? For example:</p><ul><li>Is there a different version numbering convention for the Client Connector versus the Network Adapter?</li><li>Are there other unique registry keys, values, or properties that can be used to definitively identify the Zscaler Client Connector and its specific version?</li></ul><p>Any advice or insights would be greatly appreciated.</p><p>Thanks!</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000arCxq0AE/policy-update-file-in-zcc","lastmod":"2025-07-11T23:01:45.000Z","id":"0D5PJ00000arCxq0AE"} -->
## policy update file in ZCC

- Source: https://community.zscaler.com/s/question/0D5PJ00000arCxq0AE/policy-update-file-in-zcc
- Type: Q&A
- Posted: 2025-07-09T13:24:44.000Z
- Last activity: 2025-07-11T23:01:45.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I need find a file in ZCC with respect zscaler (zpa) policy push for both MAC and Windows</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000bG1Xt0AK/web-signin-for-windows-being-blocked-by-zcc-in-strict-enforcement-mode","lastmod":"2025-10-10T19:35:31.000Z","id":"0D5PJ00000bG1Xt0AK"} -->
## "Web Sign-In for Windows" being blocked by ZCC in strict enforcement mode

- Source: https://community.zscaler.com/s/question/0D5PJ00000bG1Xt0AK/web-signin-for-windows-being-blocked-by-zcc-in-strict-enforcement-mode
- Type: Q&A
- Posted: 2025-07-16T12:10:34.000Z
- Last activity: 2025-10-10T19:35:31.000Z
- Answers: 13
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello - my org is trying to utilize "Web Sign-In for Windows" <a href="https://learn.microsoft.com/en-us/windows/security/identity-protection/web-sign-in/?tabs=intune" rel="noopener noreferrer" target="_blank">Use Web Sign-In To Enable Passwordless Sign-In In Windows | Microsoft Learn</a> , which is a passwordless sign in method, and a different kind of credential provider than Windows Hello.</p><p><br></p><p>The ZCC in strictenforcement=1 is blocking use of this and it will not load. I've ran a web sign-in with ZCC disabled, and did a wireshark capture, put down every URL I could find in a PAC file bypass, but it still won't load with ZCC installed.</p><p><br></p><p><br></p><p>I am hoping some other orgs out there, or Zscaler support have managed to get Web Sign-In working, as it is a Windows 11 authentication method.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000cWNF30AO/zpa-diagnostic-logs-se-connection-closed-by-client-connector","lastmod":"2025-08-11T12:56:34.000Z","id":"0D5PJ00000cWNF30AO"} -->
## ZPA Diagnostic Logs: SE: Connection closed by Client Connector

- Source: https://community.zscaler.com/s/question/0D5PJ00000cWNF30AO/zpa-diagnostic-logs-se-connection-closed-by-client-connector
- Type: Q&A
- Posted: 2025-08-06T14:54:14.000Z
- Last activity: 2025-08-11T12:56:34.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We are attempting to have our ZPA Private Service Edges available 'publicly' and 'locally'.</p><p><br></p><p>ZCC is able to establish connectivity to the Private Service Edges, but traffic is then routed back to the Public Service Edges to broker a connection with the App Connectors.</p><p><br></p><p>When hovering over the log entries in the ZPA portal, the following message is displayed:</p><p>"The ZPA Private Service Edge forwarded the user's request to a ZPA Public Service Edge, since the App Connector was unable to reach a ZPA Private Service Edge. Click the Hop Connection ID to see the hop transaction details."</p><p><br></p><p>When expanding the log entry, the following status code is displayed:</p><p>SE: Connection closed by Client Connector</p><p><br></p><p>Internal Status Code:</p><p>BRK_MT_CLOSED_FROM_CLIENT</p><p><br></p><p><strong>The transaction to the application on the network is successful though.</strong></p><p><br></p><p>I suspect that the App Connectors are potentially not connecting to the Private Service Edge, although we see successful traffic on the firewall.</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/068PJ00000OMxuu?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000cbT4C0AU/zcc-and-network-connectors-on-macos","lastmod":"2025-08-14T09:02:31.000Z","id":"0D5PJ00000cbT4C0AU"} -->
## ZCC and Network Connectors on macOS

- Source: https://community.zscaler.com/s/question/0D5PJ00000cbT4C0AU/zcc-and-network-connectors-on-macos
- Type: Q&A
- Posted: 2025-08-07T16:03:09.000Z
- Last activity: 2025-08-14T09:02:31.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello all,</p><p><br></p><p>we deployed a Zscaler Network Connector for VPN/VoIP legacy apps. Everything is up&amp;running and ZCC on Windows (!) shows an active Wireguard connection and we can access configured applications via wg-tunnel here.</p><p><br></p><p>On macOS it is a completely different thing: in the logs we see an IP address got successfully assigned/reserved, but starting Wireguard/establishing the wg-tunnel fails and ZCC gui just shows "nothing":</p><p><br></p><p><img src="/Zenith/sfc/servlet.shepherd/version/download/068PJ00000OQKlJ?asPdf=false&amp;operationContext=CHATTER"></p><p><br></p><p>The ZCC debugs logs are also not very helpful:</p><p><br></p><p><span style="font-family: courier;">2025-08-07 15:13:19.490075(+0200)[1162:11797] DBG ZPN:0: Response tag: 0</span></p><p><span style="font-family: courier;">2025-08-07 15:13:19.490147(+0200)[1162:11797] DBG ZPN:0: Response length: 273</span></p><p><span style="font-family: courier;">2025-08-07 15:13:19.490221(+0200)[1162:11797] INF ZPN:0: Control Message Response Data: {"zpn_np_config":{"gateways":[{"zpn_np_gateway_config":{"gid":XXX,"public_key":"XXX","key_expiry_s":1755812675,"listen_ip":"XXX","listen_port":51820,"mtu":1420,"client_ip":"192.168.XXX.XXX"}}],"status_code":0}}</span></p><p><span style="font-family: courier;">2025-08-07 15:13:19.490370(+0200)[1162:11797] INF Got zpn_np_config response.</span></p><p><span style="font-family: courier;">2025-08-07 15:13:19.490458(+0200)[1162:11797] INF [ZSANP]: In gatewayListRcvdFromBroker</span></p><p><span style="font-family: courier;">2025-08-07 15:13:19.490572(+0200)[1162:11796] INF [ZSANP]: In handleFetchGatewayListRcvd.</span></p><p><span style="font-family: courier;">2025-08-07 15:13:19.490682(+0200)[1162:11796] INF [ZSANP]: Starting the network presence tunnel</span></p><p><span style="font-family: courier;">2025-08-07 15:13:19.490754(+0200)[1162:11796] ERR [ZSAWGM]::In start()</span></p><p><span style="font-family: courier;">2025-08-07 15:13:19.490821(+0200)[1162:11796] INF isConfigValid isPrivateKeySet: 1, serverList: 1, includeSubnetMap: 3</span></p><p><span style="font-family: courier;">2025-08-07 15:13:19.491032(+0200)[1162:450021] INF ZSAWGM::Start: trying server: XXX:51820</span></p><p><span style="font-family: courier;">2025-08-07 15:13:19.491192(+0200)[1162:450021] INF ZSAWGM: Address: 192.168.XXX.XXX</span></p><p><span style="font-family: courier;">2025-08-07 15:13:19.491376(+0200)[1162:450021] INF ZSAWGM: PublicKey: XXX</span></p><p><span style="font-family: courier;">2025-08-07 15:13:19.491504(+0200)[1162:450021] INF ZSAWGM: AllowedIPs: XXX/24, XXX/24, XXX/24</span></p><p><span style="font-family: courier;">2025-08-07 15:13:19.491599(+0200)[1162:450021] INF ZSAWGM: Endpoint = XXX:51820</span></p><p><strong style="font-family: courier;">2025-08-07 15:13:19.491671(+0200)[1162:450021] INF ZSAWGM: start WG...</strong></p><p><strong style="font-family: courier;">2025-08-07 15:13:19.491803(+0200)[1162:450021] DBG XPC connection initiated, returning the proxy object</strong></p><p><strong style="font-family: courier;">2025-08-07 15:13:19.526842(+0200)[1162:164853] INF ZSAWGM: start WG failed</strong></p><p><strong style="font-family: courier;">2025-08-07 15:13:19.526909(+0200)[1162:450021] INF ZSAWGM: start wireguard failed</strong></p><p><strong style="font-family: courier;">2025-08-07 15:13:19.526961(+0200)[1162:450021] ERR ZSAWGM::Start: Failed to start wireguard interface</strong></p><p><span style="font-family: courier;">2025-08-07 15:13:19.527004(+0200)[1162:450021] INF [ZSANP]: onAllGatewaysFailed called</span></p><p><span style="font-family: courier;">2025-08-07 15:13:19.527051(+0200)[1162:11796] INF [ZSANP]: In handleAllGatewaysFailed</span></p><p><span style="font-family: courier;">2025-08-07 15:13:19.527101(+0200)[1162:11796] INF [ZSANP]:handleFetchGatewayListRetry, nextGatewayFetchWaitInterval: 30 secs</span></p><p><br></p><p>We suspect some macOS policies we enforce are interrupting WG setup but although after double-checking all our settings we were unable to identify any blocking rule. And yes, IP address of VPN Service Edge has been added to "VPN Gateway Exemption" list.</p><p><br></p><p>We use latest ZCC (4.5.0.279) and latest macOS 15.6. Systems are managed by intune. Except Microsoft Defender there are no additional security apps installed. MacOS Firewall is enforced via profiles, particular Zscaler Services are allowed:</p><p><br></p><p><img src="/Zenith/sfc/servlet.shepherd/version/download/068PJ00000OQNst?asPdf=false&amp;operationContext=CHATTER"></p><p>The only binary we miss in the list above is "ZscalerService" and we are unsure if this needs also to be explicitly exempted. Logs show no hint what exactly is failing or why the wireguard tunnel cannot be established - neither macOS console nor ZCC logs. </p><p><br></p><p>In ZPA VPN Legacy Portal we see the particular user and the assigned IP, but "Connected Device State" is just showing "down".</p><p><br></p><p>Anyone else run into similar issues? Ideas are very welcome :-)</p><p><br></p><p>Thanks &amp; BR</p><p>Manuel</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000cutRB0AY/zscaler-client-connector-over-gre","lastmod":"2025-08-14T14:04:07.000Z","id":"0D5PJ00000cutRB0AY"} -->
## Zscaler client connector over GRE

- Source: https://community.zscaler.com/s/question/0D5PJ00000cutRB0AY/zscaler-client-connector-over-gre
- Type: Q&A
- Posted: 2025-08-13T12:24:56.000Z
- Last activity: 2025-08-14T14:04:07.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Everyone ,</p><p><br></p><p>I need some understanding on how data gets forwarded when ZCC is enabled in tunnel mode on a GRE branch site.</p><p>While using tunnel 2.0 and GRE at branch site , in the web insights log i see both Identity and location details being fetched by ZEN.</p><p>I am assuming user identity details are from ZCC and location is via static ip address of location.</p><p>While doing a traceroute towards internet i see the 1st hop as ZEN node ip , although the default route is towards the local gateway.</p><p>So the data traffic is flowing via ZCC or GRE in this case ?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000dGs6C0AS/cant-access-resources-on-an-old-domain-when-logged-in-to-zcc","lastmod":"2025-08-22T08:02:19.000Z","id":"0D5PJ00000dGs6C0AS"} -->
## Can't access resources on an old domain when logged in to ZCC

- Source: https://community.zscaler.com/s/question/0D5PJ00000dGs6C0AS/cant-access-resources-on-an-old-domain-when-logged-in-to-zcc
- Type: Q&A
- Posted: 2025-08-19T16:44:01.000Z
- Last activity: 2025-08-22T08:02:19.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi I'm deploying ZIA on our remote site. <span style="font-size: 14px; font-family: Inter;">We have an old domain which some users still access form the new network. Access to old domain resources denied while ZCC is running (Like file share access) as soon as you log out of ZCC all are back to normal</span></p><p><span style="font-size: 14px; font-family: Inter;">Planning to de-com this old domain soon but for now we need access &amp; I'm not keen on disabling ZIA for end user devices. Any idea what might be causing this pls </span></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000g5jfm0AA/recommended-for-traffic-forwarding-with-corporate-vpn","lastmod":"2025-10-12T19:59:59.000Z","id":"0D5PJ00000g5jfm0AA"} -->
## Recommended for Traffic forwarding with corporate VPN

- Source: https://community.zscaler.com/s/question/0D5PJ00000g5jfm0AA/recommended-for-traffic-forwarding-with-corporate-vpn
- Type: Q&A
- Posted: 2025-10-08T07:14:47.000Z
- Last activity: 2025-10-12T19:59:59.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>The users are using corporate VPN ​with Full tunnel mode with ZCC.</p><p>​</p><p>We wanted to make sure the user with both ZCC and Corporate VPN should work seamlessly and ZIA take priority over VPN when a user has full tunnel mode</p><p>​</p><p>I wanted to know what is the recommended forwarding method (Tunnel or Tunnel with Local proxy) to configure in Zscaler forwarding profile on VPN trusted network. ​</p><p><br></p><p>Please advise</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000gLPbT0AW/url-bypass-in-zcc-20-over-ipsec","lastmod":"2025-12-25T19:37:05.000Z","id":"0D5PJ00000gLPbT0AW"} -->
## URL Bypass in ZCC 2.0 Over IPSEC

- Source: https://community.zscaler.com/s/question/0D5PJ00000gLPbT0AW/url-bypass-in-zcc-20-over-ipsec
- Type: Q&A
- Posted: 2025-10-13T07:05:15.000Z
- Last activity: 2025-12-25T19:37:05.000Z
- Answers: 5
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>With ZCC over IPsec, we configure bypasses in the AAP profile or PAC file and also set up a local breakout on the router. Why do we need the AAP/PAC bypass if traffic is sent via IPsec? For this customer, certain URLs must be sent straight to the internet, so bypasses must be defined in both the AAP/PAC and on the router.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000hzcsQ0AQ/mac-upgrade-workflow-question","lastmod":"2025-10-28T20:13:19.000Z","id":"0D5PJ00000hzcsQ0AQ"} -->
## Mac Upgrade Workflow Question

- Source: https://community.zscaler.com/s/question/0D5PJ00000hzcsQ0AQ/mac-upgrade-workflow-question
- Type: Q&A
- Posted: 2025-10-28T20:13:19.000Z
- Last activity: 2025-10-28T20:13:19.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Good afternoon. Forgive me if this has been previously answered, but I couldn't find any info via the search. What is the best practice for pushing out Mac Zscaler Client Connector updates via an MDM tool like Jamf? Can a Jamf policy use the package installer to update the client connector app silently while a user has an active Zscaler connection? Or, should a user be notified in advance that an upgrade is about to happen in case the installation causes any brief losses in connectivity? Currently, we use a popup and let users defer, but this slows down the app upgrade rate, so if we can push out the new versions silently and without user interaction, that would improve the rate of user upgrades. Thanks.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000iNj4q0AC/forbury-addin-in-excel-doesnt-load-while-connected-to-zcc","lastmod":"2025-11-17T23:43:23.000Z","id":"0D5PJ00000iNj4q0AC"} -->
## Forbury Add-in in Excel doesn't load while connected to ZCC

- Source: https://community.zscaler.com/s/question/0D5PJ00000iNj4q0AC/forbury-addin-in-excel-doesnt-load-while-connected-to-zcc
- Type: Q&A
- Posted: 2025-11-03T06:13:05.000Z
- Last activity: 2025-11-17T23:43:23.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Team,</p><p><br></p><p>We have an issue with Forbury Add-in in Excel applications where it doesn't load while working from home Zscaler ZCC connected.</p><p><br></p><p>It does work for the same user when working from office connected through corp network ( PAC over GRE tunnel)</p><p><br></p><p>&nbsp;The Forbury Add-in integrates with Excel to perform complex calculations for commercial real estate analysis by sending data to a cloud engine and returning results to your workbook</p><p><img src="/sfc/servlet.shepherd/version/download/068PJ00000Sbkjh?asPdf=false&amp;operationContext=CHATTER"></p><p>Any idea what could be blocking here, we are guessing SSL inspection could work but unable to find the evidence for the same.</p><p><br></p><p>Really appreciate your feedback.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000kpyJA0AY/ios-shared-ipad-zcc-deployment-help","lastmod":"2025-11-25T14:59:09.000Z","id":"0D5PJ00000kpyJA0AY"} -->
## iOS Shared iPad + ZCC Deployment: Help

- Source: https://community.zscaler.com/s/question/0D5PJ00000kpyJA0AY/ios-shared-ipad-zcc-deployment-help
- Type: Q&A
- Posted: 2025-11-24T22:14:37.000Z
- Last activity: 2025-11-25T14:59:09.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hey everyone — looking for guidance from anyone who has deployed <strong>Zscaler Client Connector (ZCC)</strong> on <strong>shared iPads</strong> at scale.</p><h3><strong>Environment</strong></h3><ul><li>~70 shared iPads, used by ~350+ rotating users</li><li>Configured via <strong>Intune</strong></li><li>Using <strong>ZCC as IDP</strong> (Touchless SSO)</li><li>Not enrolling individual users into ZIA (we don’t want user-based policy here)</li><li>Using a <strong>Device Token</strong> created in ZCC and pushed through Intune app config.</li></ul><p><br></p><h3><strong>Problem</strong></h3><p>When ZCC launches, it <em>does</em> authenticate, but always signs in as:</p><p>MobileUser@<mysubdomain></p><p>That identity is reused across devices, and as soon as we hit the limit (~16 active MobileUser logins), authentication starts failing. This is a typical setting for ZCC. </p><h3><strong>Ask</strong></h3><p>Has anyone successfully forced ZCC on iOS (shared iPad mode) to authenticate <strong>only using the device token</strong>, without auto-assigning the MobileUser@… identity?</p><p>Ideally, we want:</p><ul><li>Username = <strong>Device Serial Number</strong>, <strong>Intune Device ID</strong>, or <strong>any unique per-device value</strong></li><li>No reliance on MobileUser@… since it’s non-unique and breaks at scale</li></ul><h3><strong>What we’ve tried</strong></h3><ul><li>Device token assigned in ZCC → Intune profile</li><li>Touchless SSO configured with ZCC as IDP</li><li>Verified device token is accepted</li><li>Still ends up identifying as MobileUser</li></ul><p>Any successful shared-device ZCC deployments on iOS that avoid this behavior? Looking for best practices or config tricks others are using.</p><p>Thanks in advance.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000lFzWd0AK/syncing-directory-groups-between-the-zia-and-zcc","lastmod":"2025-12-04T16:00:41.000Z","id":"0D5PJ00000lFzWd0AK"} -->
## Syncing Directory Groups between the ZIA and ZCC

- Source: https://community.zscaler.com/s/question/0D5PJ00000lFzWd0AK/syncing-directory-groups-between-the-zia-and-zcc
- Type: Q&A
- Posted: 2025-12-02T05:59:27.000Z
- Last activity: 2025-12-04T16:00:41.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello</p><p><br/></p><p> I use official SDK Python for manage my users and groups with API in ZIA. API work fine</p><p>But, with API, I can't sync directory group between ZIA and ZCC.</p><p><br/></p><p>This API call (sync group ZIA on ZCC) is in the SDK roadmap ?</p><p>Thanks</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000lny8F0AQ/zcc-vdi-and-zpa-how-do-we-set-an-access-policy-for-the-vdi-system-user","lastmod":"2026-04-30T14:08:06.000Z","id":"0D5PJ00000lny8F0AQ"} -->
## ZCC VDI and ZPA - how do we set an access policy for the VDI system user?

- Source: https://community.zscaler.com/s/question/0D5PJ00000lny8F0AQ/zcc-vdi-and-zpa-how-do-we-set-an-access-policy-for-the-vdi-system-user
- Type: Q&A
- Posted: 2025-12-11T20:48:39.000Z
- Last activity: 2026-04-30T14:08:06.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We have set up ZCC VDI behind a Cloud Connector and are forwarding traffic to ZPA.</p><p><br></p><p>I can't figure out how to to grant permissions for "<a href="mailto:vdi-system-user@9999.zscalerxxxx.net" rel="noopener noreferrer" target="_blank">vdi-system-user@9999.zscalerxxxx.net</a>" (from the VDI side) to SMB access policies in the ZPA side.</p><p><br></p><p>The troubleshooting guide mentions the problem, but does not point to the solution.</p><p><br></p><p><a href="https://help.zscaler.com/cloud-branch-connector/troubleshooting-zscaler-client-connector-vdi" rel="noopener noreferrer" target="_blank">https://help.zscaler.com/cloud-branch-connector/troubleshooting-zscaler-client-connector-vdi</a></p><p><br></p><p><em>SMB, Kerberos, Windows network, DNS, ICMP, and NFS traffic are treated as system user traffic by Zscaler Client Connector for VDI. Ensure that ZPA has a correct access policy configured for the system user.</em></p><p><br></p><p>This "system user" isn't in our ZPA IDP, so how do I grant access to it?</p><p><br></p><p>I see the SMB traffic being forwarded to ZPA (in the CC logs), but don't see any logs for it in the ZPA side. It's as is it gets dropped and not even logged.</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000lnype0AA/automating-app-profile-assignment-to-new-users","lastmod":"2026-01-23T12:34:51.000Z","id":"0D5PJ00000lnype0AA"} -->
## automating app profile assignment to new users

- Source: https://community.zscaler.com/s/question/0D5PJ00000lnype0AA/automating-app-profile-assignment-to-new-users
- Type: Q&A
- Posted: 2025-12-11T20:11:33.000Z
- Last activity: 2026-01-23T12:34:51.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Is it possible to automate app profile assignment to new users via SCIM? Currently, I have to wait for new users to login first before I have the option to assign an app profile to a new user. Just wondering if anyone else has solved for this by using automation? Any ideas or suggestions will help, thanks.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000mBt7B0AS/client-connector-on-linux-wayland","lastmod":"2025-12-25T20:15:09.000Z","id":"0D5PJ00000mBt7B0AS"} -->
## Client Connector on Linux Wayland

- Source: https://community.zscaler.com/s/question/0D5PJ00000mBt7B0AS/client-connector-on-linux-wayland
- Type: Q&A
- Posted: 2025-12-18T20:33:36.000Z
- Last activity: 2025-12-25T20:15:09.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Has anyone figured out how to get the UI to work correctly for the zScaler Client Connector on a Linux machine using wayland. I have it running and the tunnel works but the UI just shows the service status as connecting. While its not a big deal since I still can access internal resources was wondering if anyone had any success fixing this.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000mLQXO0A4/zscaler-cli-on-macbook","lastmod":"2025-12-22T19:14:45.000Z","id":"0D5PJ00000mLQXO0A4"} -->
## Zscaler CLI on macbook

- Source: https://community.zscaler.com/s/question/0D5PJ00000mLQXO0A4/zscaler-cli-on-macbook
- Type: Q&A
- Posted: 2025-12-22T12:58:46.000Z
- Last activity: 2025-12-22T19:14:45.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi, </p><p><br></p><p>I've been using Zscaler for a little bit and wanted to start doing some automations using the zscli on my macbook. However I noticed that on macbook only ZPA is allowed to disable and enable using the CLI. </p><p><br></p><p>Is this on the roadmap to enable this for ZIA and ZDX aswell? </p><p><br></p><p>I couldn't find any of this information anywhere. Thanks in advanced. </p><p><br></p><p>Resources used:</p><ul><li>https://help.zscaler.com/zscaler-client-connector/interacting-zscaler-client-connector-remotely</li></ul><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000oAhdx0AC/zscaler-client-connector","lastmod":"2026-01-28T18:33:58.000Z","id":"0D5PJ00000oAhdx0AC"} -->
## zscaler client connector

- Source: https://community.zscaler.com/s/question/0D5PJ00000oAhdx0AC/zscaler-client-connector
- Type: Q&A
- Posted: 2026-01-26T15:31:33.000Z
- Last activity: 2026-01-28T18:33:58.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Client connector is giving me authentication failed, although the credentials are correct, cloud name is correct.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ohNUm0AM/tunnel-10-use-case","lastmod":"2026-04-29T22:31:38.000Z","id":"0D5PJ00000ohNUm0AM"} -->
## Tunnel 1.0 use case

- Source: https://community.zscaler.com/s/question/0D5PJ00000ohNUm0AM/tunnel-10-use-case
- Type: Q&A
- Posted: 2026-02-03T21:45:47.000Z
- Last activity: 2026-04-29T22:31:38.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I'm new to Zscaler and trying to understand the purpose of Tunnel 1.0. From what I’ve learned, Tunnel 1.0 was the original method Zscaler used, and it only forwards proxy‑aware traffic or traffic on TCP ports 80 and 443. Now that Tunnel 2.0 exists — which can forward all ports and protocols using DTLS or TLS — do we still need Tunnel 1.0?</p><p>If Tunnel 1.0 cannot handle anything beyond TCP 80 and 443, meaning all other ports bypass Zscaler and result in less security, then why is Tunnel 1.0 still enabled? Please correct me if I am misunderstanding.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ohPQB0A2/tunnel-10-use-case","lastmod":"2026-04-29T22:31:33.000Z","id":"0D5PJ00000ohPQB0A2"} -->
## Tunnel 1.0 use case

- Source: https://community.zscaler.com/s/question/0D5PJ00000ohPQB0A2/tunnel-10-use-case
- Type: Q&A
- Posted: 2026-02-03T21:45:51.000Z
- Last activity: 2026-04-29T22:31:33.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I'm new to Zscaler and trying to understand the purpose of Tunnel 1.0. From what I’ve learned, Tunnel 1.0 was the original method Zscaler used, and it only forwards proxy‑aware traffic or traffic on TCP ports 80 and 443. Now that Tunnel 2.0 exists — which can forward all ports and protocols using DTLS or TLS — do we still need Tunnel 1.0?</p><p>If Tunnel 1.0 cannot handle anything beyond TCP 80 and 443, meaning all other ports bypass Zscaler and result in less security, then why is Tunnel 1.0 still enabled? Please correct me if I am misunderstanding.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000pk0xN0AQ/zscaler-client-connector-version-470202-or-480115","lastmod":"2026-04-07T11:51:59.000Z","id":"0D5PJ00000pk0xN0AQ"} -->
## Zscaler Client connector version 4.7.0.202 or 4.8.0.115

- Source: https://community.zscaler.com/s/question/0D5PJ00000pk0xN0AQ/zscaler-client-connector-version-470202-or-480115
- Type: Q&A
- Posted: 2026-02-20T15:36:52.000Z
- Last activity: 2026-04-07T11:51:59.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello all, With the craziness of most past ZCC versions have vulnerabilities , not supported as LTS or having issues. Has anyone started looking at the two latest ZCC 4.7.0.202 or 4.8.0.115 and done any testing. We have started testing against both lately and was wondering if anyone was seeing inconsistencies or issues. We have not seen any issues in our testing but never know sometimes other people can find things others do not. Much appreciated . </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000qENNo0AO/zapp-ends-in-endpoint-fwav-error","lastmod":"2026-03-02T14:49:48.000Z","id":"0D5PJ00000qENNo0AO"} -->
## Zapp ends in Endpoint FW/AV error

- Source: https://community.zscaler.com/s/question/0D5PJ00000qENNo0AO/zapp-ends-in-endpoint-fwav-error
- Type: Q&A
- Posted: 2019-07-24T09:54:24.000Z
- Last activity: 2026-03-02T14:49:48.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

user facing issue Zapp is logged in. It stays connected for 30sec and get error related to Endpoint FW/AV error. Restart service, Repair, Reboot, Logout ends in same error. We open port 9000 via GPO. Gpupdate is successful in user machine. Still issue persists only with one user. ZSATray logs says “FIREWALL BLOCK ERROR?.

#NORMAL #INFO : ZIA state changed, From: TUNNEL_FORWARDING To: FIREWALL_BLOCK_ERROR

Regards
 Ganesh krishnan
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000qaYHK0A2/zcc-on-ipads-certificate-issue","lastmod":"2026-03-10T13:58:30.000Z","id":"0D5PJ00000qaYHK0A2"} -->
## ZCC on iPads - Certificate issue

- Source: https://community.zscaler.com/s/question/0D5PJ00000qaYHK0A2/zcc-on-ipads-certificate-issue
- Type: Q&A
- Posted: 2026-03-09T10:27:58.000Z
- Last activity: 2026-03-10T13:58:30.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello community,</p><p><br></p><p>I would like to know if anyone has successfully deployed the ZCC client on iPads and made it works.</p><p>We are currently facing an issue: ZCC is already installed on our iPads through Workspace ONE, which is our MDM solution.</p><p><br></p><p>However, we are encountering a certificate problem. Even though the certificate installed is the root certificate used by Zscaler in our environment, the iPads still mark it as untrusted. We have attempted to install the certificate both through our MDM and manually, but the issue persists.</p><p><br></p><p>After contacting Apple Support, they indicated that iPads need to receive the full certificate chain, including the intermediate certificates.</p><p>Has anyone experienced this issue before or found a solution? is there any well-known workaround? </p><p><br></p><p>Thank you,</p><p>David.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000qlMae0AE/zcc-upgrade-with-phase-rollout","lastmod":"2026-03-19T15:06:48.000Z","id":"0D5PJ00000qlMae0AE"} -->
## ZCC Upgrade with Phase rollout

- Source: https://community.zscaler.com/s/question/0D5PJ00000qlMae0AE/zcc-upgrade-with-phase-rollout
- Type: Q&A
- Posted: 2026-03-11T18:29:28.000Z
- Last activity: 2026-03-19T15:06:48.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>I initiated the <strong>ZCC upgrade from the ZCC portal using the Phased Rollout option</strong> about a week ago. I noticed that the rollout percentage now shows <strong>100% completed</strong>. However, most users’ <strong>ZCC versions are still showing the older version</strong> in the <strong>ZCC Enrolled Devices</strong> list.</p><p>Could anyone please confirm whether this is <strong>expected behavior</strong> or if this is a <strong>known issue</strong>?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000rBrha0AC/zcc-for-vdi-support-for-partner-tenant-logins","lastmod":"2026-03-24T14:27:19.000Z","id":"0D5PJ00000rBrha0AC"} -->
## ZCC for VDI support for partner tenant logins

- Source: https://community.zscaler.com/s/question/0D5PJ00000rBrha0AC/zcc-for-vdi-support-for-partner-tenant-logins
- Type: Q&A
- Posted: 2026-03-18T13:32:08.000Z
- Last activity: 2026-03-24T14:27:19.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p>Would ZCC for VDI support partner tenant logins?</p><p>Thanks,</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000raozj0AA/assistance-needed-for-zscaler-installation-on-macbook","lastmod":"2026-04-04T03:01:27.000Z","id":"0D5PJ00000raozj0AA"} -->
## Assistance Needed for Zscaler Installation on MacBook

- Source: https://community.zscaler.com/s/question/0D5PJ00000raozj0AA/assistance-needed-for-zscaler-installation-on-macbook
- Type: Q&A
- Posted: 2026-03-25T14:16:21.000Z
- Last activity: 2026-04-04T03:01:27.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Dear Zenith Community / IT Support,</p><p>I hope you are doing well.</p><p>I am currently able to access the Zscaler Client Connector VPN on my iPhone (iOS) without any issues. However, I am unable to find or install the Zscaler Client Connector on my MacBook.</p><p>Could you kindly provide guidance or the installer link for macOS, or any instructions required to set it up on my MacBook? </p><p>Thank you very much for your support.</p><p><img src="/sfc/servlet.shepherd/version/download/068PJ00000ZSdaI?asPdf=false&amp;operati</p><p>Kind regards,</p><p>Kaycelyn</p><p><br></p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000rtqag0AA/zcc-for-android-support-for-partner-login","lastmod":"2026-03-31T09:31:18.000Z","id":"0D5PJ00000rtqag0AA"} -->
## ZCC for Android support for partner login

- Source: https://community.zscaler.com/s/question/0D5PJ00000rtqag0AA/zcc-for-android-support-for-partner-login
- Type: Q&A
- Posted: 2026-03-30T20:28:09.000Z
- Last activity: 2026-03-31T09:31:18.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p>Does anyone know if ZCC for Android OS can support partnet login?</p><p>Thanks,</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000tqU4T0AU/zcc-for-vdi-with-branch-connect-seamless-authentication","lastmod":"2026-05-24T15:55:38.000Z","id":"0D5PJ00000tqU4T0AU"} -->
## ZCC for VDI with Branch Connect - Seamless authentication

- Source: https://community.zscaler.com/s/question/0D5PJ00000tqU4T0AU/zcc-for-vdi-with-branch-connect-seamless-authentication
- Type: Q&A
- Posted: 2026-04-30T21:11:42.000Z
- Last activity: 2026-05-24T15:55:38.000Z
- Answers: 3
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We are testing deployment of ZCC for VDI agent to our Microsoft RDS environment. We have the branch connector stood up and functioning properly. But are there any methods to make the ZCCVDI client automatically authenticate a user when they log in? We have this seamless SSO functioning on ZCC for our endpoints by passing the <span style="font-size: 14px; font-family: Inter, ui-sans-serif, system-ui, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Noto Color Emoji&quot;;">login_hint SAML attribute</span>, but the options are way less fleshed out in the Branch and Cloud Connector portal than in the ZCC portal. For reference, we are using Entra as our IdP and the RDS servers are hybrid joined. </p><p><br></p><p>If we can't get the automatic sign in to work, we'll have to pivot to something different for these servers. </p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000uOQJF0A4/users-falling-back-to-blue-policy-stellantis-windows-policy","lastmod":"2026-07-15T05:43:45.000Z","id":"0D5PJ00000uOQJF0A4"} -->
## Users Falling Back to Blue Policy (“Stellantis Windows Policy”)

- Source: https://community.zscaler.com/s/question/0D5PJ00000uOQJF0A4/users-falling-back-to-blue-policy-stellantis-windows-policy
- Type: Q&A
- Posted: 2026-05-11T11:09:40.000Z
- Last activity: 2026-07-15T05:43:45.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We are currently in the process of <strong>migrating users from a legacy Blue Zscaler tenant to a new Pink tenant</strong>. As part of this migration, <strong>all post‑migration users are expected to be enforced under the policy profile</strong>:</p><blockquote><p class="ql-quote-block"><strong>CiscoUmbrella‑Stellantis Profile</strong> (Pink tenant)</p></blockquote><p>and <strong>should no longer receive</strong>:</p><blockquote><p class="ql-quote-block"><strong>Stellantis Windows Policy</strong> (Blue tenant)</p></blockquote><p>However, we are observing that <strong>some users intermittently fall back to the Blue policy profile</strong>, even though:</p><ul><li>The users are already migrated</li><li>Zscaler Client Connector (ZscalerTwo) is installed</li><li>Entra ID (Azure AD) group assignments are aligned to the Pink tenant</li><li>Users are correctly assigned to Zscaler ZIA/ZPA enterprise applications for the Pink tenant</li></ul><p>This fallback is impacting access (e.g., Salesforce IP restrictions), indicating the <strong>Blue tenant policy is still being applied</strong>.</p><p><strong>Questions:</strong></p><ol><li>What are the common causes for users reverting to a legacy tenant policy profile after a tenant migration?</li><li>Are there specific <strong>Client Connector, Intune, or App Policy Profile settings</strong> that could cause this behavior?</li><li>Is there a recommended way to <strong>force tenant and policy profile binding</strong> (Pink) and prevent fallback to Blue?</li><li>Are there logs or indicators (Client Connector / ZIA / ZPA) we should check to confirm which tenant and policy profile is being enforced at runtime?</li></ol><p>Any best practices or migration lessons learned would be greatly appreciated.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000uOcH20AK/zpa-app-connector-oauth-20-token-enrollment","lastmod":"2026-07-15T05:32:29.000Z","id":"0D5PJ00000uOcH20AK"} -->
## ZPA App Connector - OAuth 2.0 Token enrollment

- Source: https://community.zscaler.com/s/question/0D5PJ00000uOcH20AK/zpa-app-connector-oauth-20-token-enrollment
- Type: Q&A
- Posted: 2026-05-11T11:39:04.000Z
- Last activity: 2026-07-15T05:32:29.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p>In February this year, Zscaler released a new capability that allows App Connector enrollment to be performed in a more secure and simpler way compared to using the traditional <strong>provision_key</strong> file.</p><p>We like this new method; however, we would like to know whether it is possible to generate new OAuth tokens in some way, for example by restarting services or using another mechanism.</p><p>Rebooting the App Connector is not a solution, as it generates the same OAuth token.</p><p><br></p><p>https://help.zscaler.com/zpa/release-upgrade-summary-2026?applicable_category=private.zscaler.com&amp;deployment_date=2026-02-17&amp;id=1532690 </p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/068PJ00000bj1SV?asPdf=false&amp;operationContext=CHATTER"></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000uWWvy0AG/microsoft-surface-laptop-with-windows-show-performance-degradation-via-lan","lastmod":"2026-05-15T15:59:32.000Z","id":"0D5PJ00000uWWvy0AG"} -->
## Microsoft Surface Laptop with Windows show performance degradation via LAN

- Source: https://community.zscaler.com/s/question/0D5PJ00000uWWvy0AG/microsoft-surface-laptop-with-windows-show-performance-degradation-via-lan
- Type: Q&A
- Posted: 2026-05-12T20:32:28.000Z
- Last activity: 2026-05-15T15:59:32.000Z
- Answers: 7
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<ul><li><strong style="font-size: inherit;">The Symptom:</strong><span style="font-size: inherit;"> Wired connections via the Surface Dock were dropping below 100 Mbps when Zscaler was enabled, while wireless connections remained relatively unaffected.</span></li><li><strong style="font-size: inherit;">Wireless Network:</strong><span style="font-size: inherit;"> Speed without ZCC: 250 Mbps; Speed with ZCC: 225 Mbps.</span></li><li><strong style="font-size: inherit;">Wired Network:</strong><span style="font-size: inherit;"> Speed without ZCC: 900 Mbps; Speed with ZCC: 60 Mbps (ZCC versions 4.8.0.140 and 4.8.0.115)</span></li><li><strong style="font-size: inherit;">Update:</strong><span style="font-size: inherit;"> ZCC version was updated to 4.8.0.172 and we are now seeing improvements in overall performance.</span></li><li><strong style="font-size: inherit;">Performance:</strong><span style="font-size: inherit;"> Wired: 353 Mbps (ZCC version 4.8.0.172) with MTU size 1350. Wireless remains unaffected.</span></li><li><strong style="font-size: inherit;">Technical Note:</strong><span style="font-size: inherit;"> ZCC version 4.8.0.140 has a fragmentation bug which can be resolved by updating to the next version. It is important to note that everything works fine on Mac devices, as they are smart enough to dynamically maintain the MTU sizing and do not experience TCP retransmission.</span></li><li><span style="font-size: inherit;">Tried URB and buffers changes with offloads.</span></li></ul><p><span style="font-size: inherit;">Any one would like to help in understanding if this is related to LAN adapter or any other endpoints setting can be changed.</span></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000uatb30AA/windows-laptop-dropping-performance-and-have-slowness-but-mac-doesnt","lastmod":"2026-05-26T16:02:19.000Z","id":"0D5PJ00000uatb30AA"} -->
## Windows laptop dropping performance and have slowness but Mac doesn't

- Source: https://community.zscaler.com/s/question/0D5PJ00000uatb30AA/windows-laptop-dropping-performance-and-have-slowness-but-mac-doesnt
- Type: Q&A
- Posted: 2026-05-13T22:40:00.000Z
- Last activity: 2026-05-26T16:02:19.000Z
- Answers: 1
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Can any one have any idea?</p><p>We are facing slowness on all the windows devices.</p><p><br></p><p>We tried with Dell, microsoft surface but no improvement in the slowness.</p><p>+ We tried with tunnel 1.0 no improvment</p><p>+currently using tunnel 2.0 DTLS and tried changing to TLS however no improvment</p><p>+ the only change in mtu does improve but still performance is degraded.</p><p>+Ticket is already raised but we are still unable to isolate the issue.</p><p><br></p><p>Can someone shed some light on this and share some experience what can fix the problem.</p><p><br></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000utSHz0AM/zcc-for-vdi-citrix-browserbased-users-auth-with-idp-okta-via-kerberos","lastmod":"2026-05-28T15:17:38.000Z","id":"0D5PJ00000utSHz0AM"} -->
## ZCC for VDI (Citrix) browser-based users auth with iDP (Okta) via Kerberos

- Source: https://community.zscaler.com/s/question/0D5PJ00000utSHz0AM/zcc-for-vdi-citrix-browserbased-users-auth-with-idp-okta-via-kerberos
- Type: Q&A
- Posted: 2026-05-19T02:05:02.000Z
- Last activity: 2026-05-28T15:17:38.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>We use Okta as iDP for users authentication and recently configured Okta DSSO (https://help.okta.com/en-us/content/topics/directory/configuring_agentless_sso.htm) for Citrix VDI (on-prem). Could ZCC for VDI rely on browser-based authentication with iDP while browser uses Kerberos for user auth via DSSO? Another question if ZCC for VDI uses external browser for user's authentication?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000vNDk00AG/zcc-for-vdi-webbrowser-use-for-users-authentication","lastmod":"2026-06-24T14:31:50.000Z","id":"0D5PJ00000vNDk00AG"} -->
## ZCC for VDI web-browser use for user's authentication

- Source: https://community.zscaler.com/s/question/0D5PJ00000vNDk00AG/zcc-for-vdi-webbrowser-use-for-users-authentication
- Type: Q&A
- Posted: 2026-05-27T01:37:39.000Z
- Last activity: 2026-06-24T14:31:50.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Does ZCC for VDI use external browser for users authentication?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000vRG6v0AG/how-to-get-access-to-public-previews-for-zscaler-client-connector","lastmod":"2026-06-05T03:00:35.000Z","id":"0D5PJ00000vRG6v0AG"} -->
## How to get access to Public Previews for Zscaler Client Connector

- Source: https://community.zscaler.com/s/question/0D5PJ00000vRG6v0AG/how-to-get-access-to-public-previews-for-zscaler-client-connector
- Type: Q&A
- Posted: 2026-05-28T03:50:04.000Z
- Last activity: 2026-06-05T03:00:35.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi,</p><p><br></p><p>We received notification from Zscaler that new version of Zscaler Client Connector for windows and macOS is available via Public Previews in Zenith Community. However, I do not see that tab in the community (even after logging in). Where can I find it? or what is the process to register for it?</p><p><br></p><p><img src="/sfc/servlet.shepherd/version/download/068PJ00000cZOmM?asPdf=false&amp;operationContext=CHATTER"></p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000w0O1Z0AU/zscaler-client-connector-audit","lastmod":"2026-06-27T12:09:08.000Z","id":"0D5PJ00000w0O1Z0AU"} -->
## Zscaler client connector Audit

- Source: https://community.zscaler.com/s/question/0D5PJ00000w0O1Z0AU/zscaler-client-connector-audit
- Type: Q&A
- Posted: 2026-06-05T16:42:35.000Z
- Last activity: 2026-06-27T12:09:08.000Z
- Answers: 1
- Likes: 1
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>hello , I am looking for some best practices guide for Zscaler client connector configuration .( ZCC Portal) . We are using Tunnel2.0 but in general are there some guidelines or best practices to be followed ?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000wZZ3L0AW/user-has-to-input-password-everytime","lastmod":"2026-06-29T15:44:17.000Z","id":"0D5PJ00000wZZ3L0AW"} -->
## User has to input password everytime

- Source: https://community.zscaler.com/s/question/0D5PJ00000wZZ3L0AW/user-has-to-input-password-everytime
- Type: Q&A
- Posted: 2026-06-15T11:57:23.000Z
- Last activity: 2026-06-29T15:44:17.000Z
- Answers: 4
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hi Team,</p><p><br></p><p>We have a user that complains Zscaler Client Connector doesn't store his password.</p><p>Everyday he's being requested to input his full credentials and never received the "remember password" popup.</p><p>We reinstalled the app, reset his password and the popup never shows.</p><p>Zscaler Client Connector: App Version: 4.8.0.115</p><p>Windows 11 machine.</p><p>All other users in our corporation have no issues like this. </p><p>We do have OKTA SSO and it works with everyone including this user but he's not able to "store" his credentials like all other users.</p><p>Can someone please assist?</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000xjb8L0AQ/zcc-idp-traffic-exempt-from-forwarding-to-zia","lastmod":"2026-07-15T00:01:15.000Z","id":"0D5PJ00000xjb8L0AQ"} -->
## ZCC idp traffic exempt from forwarding to ZIA

- Source: https://community.zscaler.com/s/question/0D5PJ00000xjb8L0AQ/zcc-idp-traffic-exempt-from-forwarding-to-zia
- Type: Q&A
- Posted: 2026-07-02T20:51:57.000Z
- Last activity: 2026-07-15T00:01:15.000Z
- Answers: 3
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Hello,</p><p>Could you please advice on how to exempt traffic to iDP (Okta) from being forwarded to ZIA while ZCC is connected?</p><p>ZCC for Chromebooks v4.2.0.137 connected with Tunnel 2.0 with DTLS Protocol.</p><p>Thanks,</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000yvj990AA/ondemand-zcczpa-for-3rd-party-vendors","lastmod":"2026-08-05T15:31:45.000Z","id":"0D5PJ00000yvj990AA"} -->
## On-demand ZCC/ZPA for 3rd Party Vendors

- Source: https://community.zscaler.com/s/question/0D5PJ00000yvj990AA/ondemand-zcczpa-for-3rd-party-vendors
- Type: Q&A
- Posted: 2026-07-21T19:41:41.000Z
- Last activity: 2026-08-05T15:31:45.000Z
- Answers: 2
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Looking for a way to have our 3rd Party vendors use ZCC/ZPA on-demand similar to how they use our VPN client. So, they can open/start it only when needed and doesn't run persistently. Then easily turn it off. PRA is not suitable due to protocol limitations.</p>
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000zAVVt0AO/zcc-v-48-macos-golden-gate-testing","lastmod":"2026-07-28T17:04:18.000Z","id":"0D5PJ00000zAVVt0AO"} -->
## ZCC v 4.8 + MacOS Golden Gate testing

- Source: https://community.zscaler.com/s/question/0D5PJ00000zAVVt0AO/zcc-v-48-macos-golden-gate-testing
- Type: Q&A
- Posted: 2026-07-24T21:53:02.000Z
- Last activity: 2026-07-28T17:04:18.000Z
- Answers: 0
- Likes: 0
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。
- Note: 回答本文は UI API では取得できないため未収録。

<p>Would like to know best practices for ZCC and Golden Gate testing. Beta testing reveals ZCC connection instability for some users. Are there recommended ZCC versions to test with.</p>
<!-- /ZS-POST -->
