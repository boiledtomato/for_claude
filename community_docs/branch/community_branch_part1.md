# Zscaler Zenith Community — Branch / Cloud Connector / SD-WAN (part 1)

Source: https://community.zscaler.com
Generated: 2026-08-01 20:41 UTC
Posts in this file: 72

> これはユーザー投稿のコミュニティフォーラムの内容であり、Zscaler の公式ドキュメントではない。

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Articles/aSmPJ00000DTvoD0AT/zscaler-pulse-sim-security","lastmod":"2025-06-17T13:46:01.000Z","id":"aSmPJ00000DTvoD0AT"} -->
## Zscaler Pulse | SIM Security

- Source: https://community.zscaler.com/s/Articles/aSmPJ00000DTvoD0AT/zscaler-pulse-sim-security
- Type: Article
- Last activity: 2025-06-17T13:46:01.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Article Details

Community Highlights

Ben_Garrison

(Employee) posted an Article

June 17, 2025 at 1:45 PM

Zscaler Pulse | SIM Security

Hello, here is another episode of Zscaler Pulse. We sit down with our VP of Innovation and discuss the "Final frontier" of security, protecting the SIM Card. Might not really be the final frontier, but it's something that has been overlooked in the cyber security space.

Join me and Huxley as we have a really fun conversation with Nathan

John_Buxbaum

(Ex-Customer)

1y ago

Pretty sure we chatted at Zenith in Vegas!

Ben_Garrison

(Ex-Customer)

1y ago

We may have yes! I met a lot of amazing people in Vegas. Did you stop by the booth?

John_Buxbaum

(Ex-Customer)

1y ago

probably more that you wanted!! :) (Security!!!)

Gianluca.bastia

(Partner)

1y ago

Exciting stuff!

avshch

(Ex-Customer)

10 months ago

Would Zscaler SIM work with Google ChromeOS?

avshch

(Ex-Customer)

10 months ago

Which mobile network (Verizon, T-Mobile etc.) does Zscaler use for Zscaler SIM?

Ben_Garrison

(Ex-Customer)

10 months ago

Hey so in the US we support the big 3 carriers. We do have a guarantee to support at minimum 2 per country. So we cover most but not all outside of the US.

https://www.zscaler.com/products-and-solutions/zscaler-cellular#use-cases

avshch

(Ex-Customer)

7 months ago

Does Zscaler cellular support eSIM?

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Article Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Articles/aSmPJ00000GdkzR0AR/microsubnets-using-airgap","lastmod":"2026-07-27T08:56:09.000Z","id":"aSmPJ00000GdkzR0AR"} -->
## Microsubnets Using Airgap+

- Source: https://community.zscaler.com/s/Articles/aSmPJ00000GdkzR0AR/microsubnets-using-airgap
- Type: Article
- Last activity: 2026-07-27T08:56:09.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Article Details

Zero Trust SD-WAN

sashraf

(Employee) posted an Article

Edited July 27, 2026 at 8:56 AM

Microsubnets Using Airgap+

Zero Trust Branch delivers true zero trust microsegmentation by assigning a /32 subnet mask to each endpoint, creating a "network-of-one" that isolates devices, blocks direct endpoint-to-endpoint communication, and reduces the attack surface.

Some Purdue Level 2 and below endpoints require direct L2 connectivity (e.g., PLC to PLC controller). Certain legacy endpoints do not support /32 subnet masks or struggle to operate within network-of-one subnets.

For these cases, Zscaler Zero Trust Branch identifies and whitelists such endpoints in a special group called AirgapLite, continuously monitoring them. Strict policies govern their interaction with endpoints isolated in the network-of-one.

Building on this, Airgap+ uses the Zero Trust Branch appliance to create microsubnets,highly granular subnetworks with small IP ranges (e.g., /27 to /30). Devices within a microsubnet communicate directly, while traffic crossing microsubnet boundaries is inspected and controlled by policies enforced at the Zero Trust border appliance.

This approach enhances security granularity, visibility, and control, enabling stronger east-west segmentation and minimizing lateral movement risks in complex environments.

For more details, check the following link.

https://zscaler.my.salesforce.com/sfc/p/70000000KG1j/a/PJ0000040e1Z/Uqcil98Pv24KdzAO9WES_KgEfrxTbV0bOBjSn7YopwU

https://youtu.be/L66PqP1qI6c?si=Sk1xp4KPqRzg2Sqq

Ramesh Mani

(Partner)

9 months ago

thanks for sharing the same

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Article Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Articles/aSmPJ00000Gdl130AB/airgap-lite","lastmod":"2026-07-27T08:56:11.000Z","id":"aSmPJ00000Gdl130AB"} -->
## Airgap Lite

- Source: https://community.zscaler.com/s/Articles/aSmPJ00000Gdl130AB/airgap-lite
- Type: Article
- Last activity: 2026-07-27T08:56:11.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Article Details

Zero Trust SD-WAN

sashraf

(Employee) posted an Article

Edited July 27, 2026 at 8:56 AM

Airgap Lite

Airgap-Lite

is a deployment option that:

Uses the same subnet mask as provided by the DHCP server, unlike full Airgap, which assigns /32 masks.

Enables direct communication between endpoints without forcing the traffic through the Zero Trust Branch gateway for policy evaluation.

Is ideal for environments where:

/32 addressing is not supported.

Full traffic isolation is not required.

For more details, check the following link

https://zscaler.my.salesforce.com/sfc/p/70000000KG1j/a/PJ0000040bWj/eLdG_sdCqZWJTMWvu7RJfJVe8P5q20wkEgAGqzA44gI

https://youtu.be/zYW4zP3Bcts?si=uQSqB3TfnGF4WjUy

Ramesh Mani

(Partner)

9 months ago

Thanks for sharing

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Article Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Articles/aSmPJ00000Gdl2f0AB/microsegmentation-using-airgap","lastmod":"2025-10-23T16:25:16.000Z","id":"aSmPJ00000Gdl2f0AB"} -->
## Microsegmentation Using Airgap

- Source: https://community.zscaler.com/s/Articles/aSmPJ00000Gdl2f0AB/microsegmentation-using-airgap
- Type: Article
- Last activity: 2025-10-23T16:25:16.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Article Details

Zero Trust SD-WAN

sashraf

(Employee) posted an Article

October 23, 2025 at 4:25 PM

Microsegmentation Using Airgap

Airgap delivers true zero-trust microsegmentation by assigning a /32 subnet mask to each endpoint, effectively creating a

network-of-one

that isolates devices and blocks direct endpoint-to-endpoint communication. It ensures all traffic is routed through the Zero Trust gateway (Zero Trust Branch gateway) for granular policy enforcement, inspection, and threat prevention, making it ideal for environments requiring strict isolation.

For more details, check the following link.

https://zscaler.my.salesforce.com/sfc/p/70000000KG1j/a/PJ0000040e3B/OYraPVlKFLA0KEpfporSqDMPWBojlgzlLdHaa8F0SnA

Ramesh Mani

(Partner)

9 months ago

thanks for sharing @

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Article Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Articles/aSmPJ00000Gdl5t0AB/zero-trust-branch-quick-start-guide","lastmod":"2026-02-03T04:13:29.000Z","id":"aSmPJ00000Gdl5t0AB"} -->
## Zero Trust Branch - Quick Start Guide

- Source: https://community.zscaler.com/s/Articles/aSmPJ00000Gdl5t0AB/zero-trust-branch-quick-start-guide
- Type: Article
- Last activity: 2026-02-03T04:13:29.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Article Details

Zero Trust SD-WAN

sashraf

(Employee) posted an Article

Edited February 3, 2026 at 4:13 AM

Zero Trust Branch - Quick Start Guide

Zscaler Zero Trust Branch securely connects and segments your branches, factories, and data centers without the complexity of VPNs or overlay routing. It enables zero trust access from users and IoT/OT devices to applications based on your organization’s security policies. By combining the power of Zscaler’s industry-leading Zero Trust Exchange platform with an integrated Branch appliance deployed in branches, campuses and factories, organizations can embrace a secure access service edge (SASE) framework, segment critical OT/IoT devices and enable a café-like branch.

The goal of this quick start guide is to walk customers through configuring the Zero Trust (ZT) Branch appliance and be able to pass traffic through it.

For more details, check the following link.

https://zscaler.my.salesforce.com/sfc/p/70000000KG1j/a/PJ000004Fw7p/3nzOr3DDzkLex.cVdsAO6UdKBfOiQgznUNDCFJ6uPuM

Ramesh Mani

(Partner)

9 months ago

This is good, thanks

ozanogur

(Partner)

5 months ago

Nice guide, thanks for sharing

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Article Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Articles/aSmPJ00000Gdl7V0AR/zero-trust-branch-bonding-interface","lastmod":"2025-10-23T17:10:57.000Z","id":"aSmPJ00000Gdl7V0AR"} -->
## Zero Trust Branch Bonding Interface

- Source: https://community.zscaler.com/s/Articles/aSmPJ00000Gdl7V0AR/zero-trust-branch-bonding-interface
- Type: Article
- Last activity: 2025-10-23T17:10:57.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Article Details

Zero Trust SD-WAN

sashraf

(Employee) posted an Article

October 23, 2025 at 5:10 PM

Zero Trust Branch Bonding Interface

Zero Trust Branch supports Bonding Interfaces (ebond0, ebond1), a logical interface created by combining multiple physical network interfaces (ports). It provides increased bandwidth, redundancy, and load balancing by distributing network traffic across the bundled physical links.

For more details, check the following link

https://zscaler.my.salesforce.com/sfc/p/70000000KG1j/a/PJ0000040eBF/DknUvH4g8doZ1qXE2RHInpQB.q2QKdMWIS.UO2X9ixc

Ramesh Mani

(Partner)

9 months ago

This is informative, thanks for sharing the same.

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Article Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Articles/aSmPJ00000Gdl970AB/zero-trust-branch-ransomware-kill-switch","lastmod":"2026-07-27T08:56:14.000Z","id":"aSmPJ00000Gdl970AB"} -->
## Zero Trust Branch Ransomware Kill Switch

- Source: https://community.zscaler.com/s/Articles/aSmPJ00000Gdl970AB/zero-trust-branch-ransomware-kill-switch
- Type: Article
- Last activity: 2026-07-27T08:56:14.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Article Details

Zero Trust SD-WAN

sashraf

(Employee) posted an Article

October 23, 2025 at 5:12 PM

Zero Trust Branch Ransomware Kill Switch

The Ransomware Kill Switch (RKS) is an instant, one-click attack surface reduction tool built into Zscaler's Zero Trust Branch solution. It empowers organizations to rapidly contain ransomware threats without disrupting critical business operations.

When an attack is underway, time is of the essence. Every second counts, and how quickly you respond can make the difference between a contained incident and a full-scale breach. RKS enables immediate isolation of infected devices, minimizing lateral movement and preventing further compromise, all with a single click.

For more details, check the following link

https://zscaler.my.salesforce.com/sfc/p/70000000KG1j/a/PJ0000040ddO/jimkJTPIxgNvvI9dqvSDgEJvQbtXXEj65NHo2rPic.M

Ramesh Mani

(Partner)

8 months ago

Thanks for sharing this, very informative.

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Article Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Articles/aSmPJ00000GdlDx0AJ/zero-trust-branch-armis-integration-guide","lastmod":"2026-07-27T08:56:17.000Z","id":"aSmPJ00000GdlDx0AJ"} -->
## Zero Trust Branch Armis Integration Guide

- Source: https://community.zscaler.com/s/Articles/aSmPJ00000GdlDx0AJ/zero-trust-branch-armis-integration-guide
- Type: Article
- Last activity: 2026-07-27T08:56:17.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Article Details

Zero Trust SD-WAN

sashraf

(Employee) posted an Article

October 23, 2025 at 5:18 PM

Zero Trust Branch Armis Integration Guide

This guide provides step-by-step instructions for integrating Zero Trust Branch (ZTB) with Armis to enable bi-directional data ingestion. With this integration:

Armis ingests ZTB device posture and policy data to enhance visibility of connected assets.

ZTB ingests Armis device intelligence (classification, tags, and risk insights) to strengthen policy enforcement and access control.

This allows security teams to correlate network enforcement from ZTB with device context from Armis, improving Zero Trust outcomes.

For more details, check the following link

https://zscaler.my.salesforce.com/sfc/p/70000000KG1j/a/PJ0000040e81/QYRdXfneHrPF9dEbwl89IvCMAPlp3MZKP63kiC3FwwI

Ramesh Mani

(Partner)

8 months ago

Thanks for sharing.

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Article Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Articles/aSmPJ00000GdlFZ0AZ/zero-trust-branch-and-crowdstrike-integration-guide","lastmod":"2026-07-27T16:16:07.000Z","id":"aSmPJ00000GdlFZ0AZ"} -->
## Zero Trust Branch and CrowdStrike Integration Guide

- Source: https://community.zscaler.com/s/Articles/aSmPJ00000GdlFZ0AZ/zero-trust-branch-and-crowdstrike-integration-guide
- Type: Article
- Last activity: 2026-07-27T16:16:07.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Article Details

Zero Trust SD-WAN

sashraf

(Employee) posted an Article

Edited July 27, 2026 at 4:16 PM

Zero Trust Branch and CrowdStrike Integration Guide

Zscaler Zero Trust Branch integrates with CrowdStrike Falcon to deliver endpoint-aware zero trust security across branches. By combining Zscaler’s zero trust enforcement with CrowdStrike’s endpoint risk insights, organizations gain unified, adaptive access control for both on-premises and remote users.

For more details, check the following link

https://zscaler.my.salesforce.com/sfc/p/70000000KG1j/a/PJ0000040eCr/f9i9fNckeD9f5GSWNg6PaeEo0AJh11HjIfICQN1BIUM

https://youtu.be/rWn2fqc1GaI?si=OArB39f-YZ_Z9PHc

Ramesh Mani

(Partner)

9 months ago

Thanks for sharing this

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Article Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Articles/aSmPJ00000GdlNd0AJ/zero-trust-branch-logging-and-monitoring","lastmod":"2026-07-27T08:56:24.000Z","id":"aSmPJ00000GdlNd0AJ"} -->
## Zero Trust Branch Logging and Monitoring

- Source: https://community.zscaler.com/s/Articles/aSmPJ00000GdlNd0AJ/zero-trust-branch-logging-and-monitoring
- Type: Article
- Last activity: 2026-07-27T08:56:24.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Article Details

Zero Trust SD-WAN

sashraf

(Employee) posted an Article

Edited July 27, 2026 at 8:56 AM

Zero Trust Branch Logging and Monitoring

Monitoring and logging are foundational to any secure and well-managed system. They provide the visibility needed to detect anomalies, ensure compliance, and troubleshoot issues before they become disruptions. Without them, you're essentially flying blind.

Zscaler Zero Trust Branch provides comprehensive logging and monitoring capabilities to ensure visibility and control across your branch network infrastructure. This document outlines the key aspects of logging and monitoring, including architecture, logging levels, log format, retention periods, size limits, log throttling mechanisms, and other critical considerations.

https://zscaler.my.salesforce.com/sfc/p/70000000KG1j/a/PJ0000059dNR/XkHIzBoc.a1gohNLXNYp3oeGK8Pmt16j04UK3rXKkQU

https://youtu.be/YiT9BT8VD1g?si=3ztPpGL0-4wLwhsR

Ramesh Mani

(Partner)

9 months ago

This is great. thanks for sharing

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Article Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Articles/aSmPJ00000GeHiT0AV/zero-trust-branch-whats-new-in-808p1","lastmod":"2026-01-30T19:12:24.000Z","id":"aSmPJ00000GeHiT0AV"} -->
## Zero Trust Branch - What's New in 8.0.8P1?

- Source: https://community.zscaler.com/s/Articles/aSmPJ00000GeHiT0AV/zero-trust-branch-whats-new-in-808p1
- Type: Article
- Last activity: 2026-01-30T19:12:24.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Article Details

Zero Trust SD-WAN

sashraf

(Employee) posted an Article

January 30, 2026 at 7:12 PM

Zero Trust Branch - What's New in 8.0.8P1?

Please refer this link below to learn about all the new features and understand what issues have been resolved in 8.0.8P1

https://zscaler.my.salesforce.com/sfc/p/70000000KG1j/a/PJ0000055XOf/gwBEospZl75bmoKp4004klxHitGyififywINLH3CuH8

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Article Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Articles/aSmPJ00000GeHk50AF/zero-trust-branch-whats-new-in-808p2","lastmod":"2026-01-30T19:15:01.000Z","id":"aSmPJ00000GeHk50AF"} -->
## Zero Trust Branch - What's New in 8.0.8P2?

- Source: https://community.zscaler.com/s/Articles/aSmPJ00000GeHk50AF/zero-trust-branch-whats-new-in-808p2
- Type: Article
- Last activity: 2026-01-30T19:15:01.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Article Details

Zero Trust SD-WAN

sashraf

(Employee) posted an Article

January 30, 2026 at 7:15 PM

Zero Trust Branch - What's New in 8.0.8P2?

Please refer this link below to learn about all the new features and understand what issues have been resolved in 8.0.8P2

https://zscaler.my.salesforce.com/sfc/p/70000000KG1j/a/PJ0000055XZx/l1Z5bf5ogd36IncuQBfGwv3bhA5kxaroRRCketj4aCM

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Article Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Articles/aSmPJ00000GeHlh0AF/zero-trust-branch-whats-new-in-808","lastmod":"2026-01-30T19:19:19.000Z","id":"aSmPJ00000GeHlh0AF"} -->
## Zero Trust Branch - What's New in 8.0.8

- Source: https://community.zscaler.com/s/Articles/aSmPJ00000GeHlh0AF/zero-trust-branch-whats-new-in-808
- Type: Article
- Last activity: 2026-01-30T19:19:19.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Article Details

Zero Trust SD-WAN

sashraf

(Employee) posted an Article

January 30, 2026 at 7:19 PM

Zero Trust Branch - What's New in 8.0.8

Please refer this link below to learn about all the new features and understand what issues have been resolved in 8.0.8

https://zscaler.my.salesforce.com/sfc/p/70000000KG1j/a/PJ0000055XtJ/R2KKp3sfOyBx2pqffSWFFTLidIbMg.hWMP9Zn29Eos4

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Article Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Articles/aSmPJ00000GeJ4L0AV/zero-trust-branch-whats-new-in-811","lastmod":"2026-03-06T05:33:17.000Z","id":"aSmPJ00000GeJ4L0AV"} -->
## Zero Trust Branch - What's New in 8.1.1

- Source: https://community.zscaler.com/s/Articles/aSmPJ00000GeJ4L0AV/zero-trust-branch-whats-new-in-811
- Type: Article
- Last activity: 2026-03-06T05:33:17.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Article Details

Zero Trust SD-WAN

sashraf

(Employee) posted an Article

March 6, 2026 at 5:33 AM

Zero Trust Branch - What's New in 8.1.1

Please refer this link below to learn about all the new features and understand what issues have been resolved in 8.1.1

https://zscaler.my.salesforce.com/sfc/p/70000000KG1j/a/PJ000005RElZ/YH3Hf2TwlRGSsiO4vnK6n007ZFpyyMB75hvIhguAaZQ

BlueSocks

(Partner)

4 months ago

Thank you

Raj909

(Customer)

3 months ago

Thank you

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Article Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Articles/aSmPJ00000Ged330AB/zero-trust-branch-enhanced-ha","lastmod":"2026-07-15T06:19:49.000Z","id":"aSmPJ00000Ged330AB"} -->
## Zero Trust Branch Enhanced HA

- Source: https://community.zscaler.com/s/Articles/aSmPJ00000Ged330AB/zero-trust-branch-enhanced-ha
- Type: Article
- Last activity: 2026-07-15T06:19:49.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Article Details

Zero Trust SD-WAN

sashraf

(Employee) posted an Article

July 15, 2026 at 6:19 AM

Zero Trust Branch Enhanced HA

Zero Trust Branch Enhanced HA enabled both Gateways to utilize their WAN uplinks. While the LAN side continues to operate in an Active-Standby mode, where a single "Active" appliance receives all client traffic, the WAN side transitions into an Active-Active utilization model. Through the use of a dedicated WAN Transit Link, the Active appliance can forward sessions to the Standby appliance, allowing the site to utilize the bandwidth and path diversity of all available WAN uplinks across both hardwares simultaneously.

For more details, check the following link

https://zscaler.my.salesforce.com/sfc/p/#70000000KG1j/a/PJ000006wRXR/OYNvIcsmD1NuTSXGDZoKcBgSbGvNb9.qYFPkGj8Junc

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Article Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Articles/aSmPJ00000Ged4f0AB/zero-trust-branch-appliance-version-manager","lastmod":"2026-07-15T17:31:58.000Z","id":"aSmPJ00000Ged4f0AB"} -->
## Zero Trust Branch Appliance Version Manager

- Source: https://community.zscaler.com/s/Articles/aSmPJ00000Ged4f0AB/zero-trust-branch-appliance-version-manager
- Type: Article
- Last activity: 2026-07-15T17:31:58.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Article Details

Zero Trust SD-WAN

sashraf

(Employee) posted an Article

July 15, 2026 at 5:31 PM

Zero Trust Branch Appliance Version Manager

Zero Trust Branch release 8.0.7 introduced the Appliance Version Manager, a new image-based upgrade mechanism that replaced the legacy incremental package-based upgrade process. This guide walks through both the legacy and rootfs-based upgrade methods, including how to download, activate, and safely downgrade appliance images across active-standby deployments.

For more details, check the following link

https://zscaler.my.salesforce.com/sfc/p/#70000000KG1j/a/PJ000006wlaz/jgZwYgoNLzO5isWHl1aT9GhWYIBVdF.h8xzk7VT3ORg

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Article Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Articles/aSmPJ00000Geiyn0AB/zero-trust-branch-routed-tunnel","lastmod":"2026-07-27T16:27:33.000Z","id":"aSmPJ00000Geiyn0AB"} -->
## Zero Trust Branch Routed Tunnel

- Source: https://community.zscaler.com/s/Articles/aSmPJ00000Geiyn0AB/zero-trust-branch-routed-tunnel
- Type: Article
- Last activity: 2026-07-27T16:27:33.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Article Details

Zero Trust SD-WAN

sashraf

(Employee) posted an Article

18h ago

Zero Trust Branch Routed Tunnel

Routed tunnels provide a secure way to connect branch locations over IP networks. Legacy applications (e.g., VoIP and TACACS+, ATM, Active FTP) running in branches require source IP address visibility. Zscaler Zero Trust Branch supports direct site to site connectivity over routed tunnels and preserves IP required for these applications to function.

For more details check following resources:

https://zscaler.my.salesforce.com/sfc/p/#70000000KG1j/a/PJ0000074e9J/oDKSN9.KQ6DDH89Q5wYyATPAVgnwuAM7_cEzqYOBXO4

https://youtu.be/Tw-XluAp2Wg?si=klD3_gXnwdlGz_t1

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Article Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Blogs/aSnPJ00000003Qv0AI/july-2025-zscaler-cellular-news","lastmod":"2025-06-24T21:33:04.000Z","id":"aSnPJ00000003Qv0AI"} -->
## July 2025 - Zscaler Cellular News

- Source: https://community.zscaler.com/s/Blogs/aSnPJ00000003Qv0AI/july-2025-zscaler-cellular-news
- Type: Blog
- Last activity: 2025-06-24T21:33:04.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Blog Details

Zscaler Cellular Service

Nathan_Howe

(Employee) posted a Blog

June 24, 2025 at 9:33 PM

July 2025 - Zscaler Cellular News

Sharing the latest news about Zscaler Cellular:

Zscaler Cellular Website is launched:

https://www.zscaler.com/products-and-solutions/zscaler-cellular

Public Customer References:

Maverick Transportation

(blog)

Sandvik

(podcast)

Media Releases:

The Instillery:

eSIM simplicity of connectivity

UI walk through

Blogs:

Visibility

Control

Protection

#zscalercellular

johnsonto

(Customer)

1y ago

Great technology. Looking forward to seeing this more and how it all works.

Nathan_Howe

(Customer)

1y ago

Thanks Jonh Sonto, we are excited to share as much as possible. Is there anything you would like to specifically know?

johnsonto

(Customer)

1y ago

Seeing what's available at this time. Intrigued by it for sure and need to read what you posted and do some homework.

Todd

Ramesh Mani

(Partner)

1y ago

Great stuff, Is this available in all regions and countries?

Nathan_Howe

(Employee)

1y ago

Thanks Ramesh!

Yes, this is available in pretty much every country. I say pretty much as there are some caveats like North Korea. But let me know where you would expect to operate and I will happily confirm.

Scott Daniels

(Partner)

1y ago

Saw this at Zenith, very exciting! Plenty of use cases out there for this technology.

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Blog Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Blogs/aSnPJ0000000DbZ0AU/zscaler-cellular-spotlight-out-of-band-remote-access","lastmod":"2025-08-20T15:45:51.000Z","id":"aSnPJ0000000DbZ0AU"} -->
## Zscaler Cellular Spotlight - Out of Band Remote Access

- Source: https://community.zscaler.com/s/Blogs/aSnPJ0000000DbZ0AU/zscaler-cellular-spotlight-out-of-band-remote-access
- Type: Blog
- Last activity: 2025-08-20T15:45:51.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Blog Details

Zscaler Cellular Service

Nelson

(Employee) posted a Blog

Edited August 20, 2025 at 3:45 PM

Zscaler Cellular Spotlight - Out of Band Remote Access

This “spotlight” takes the focus on use cases covering Out Of Band(OOB) remote access using cellular (for reference, some may also use the term Lights Out Management or LOM when referencing this use case. This article will use the term OOB.)

No one wants to experience their primary network path being interrupted, but operational teams need access when it's most critical. OOB is not a new term, it's been used in the IT, Industrial and OT industry for quite some time. In essence, OOB is a term used to reference a backup or dedicated path for management and remote access to your critical systems. In some cases, completely separate networking, switching and routing is maintained for OOB access, in the event that your primary(or inband) network path is unavailable. Building out completely physically separate paths is expensive and complex and in many cases is just not feasible.

Using cellular connectivity for OOB is also not new, we’ve seen it deployed in many scenarios to address mobile as well as fixed access to critical infrastructure. Whether it's a physical rack in your DC, branch office location or an Industrial Control System(ICS). Cellular connectivity for many of these deployments still leverage classic hub and spoke design with VPNs or some other form of activation for remote access.

For one customer I worked with recently, they used SMS to activate their OOB management interface in order to have it publicly exposed to the internet. Once exposed, they used OTP and SSH keys in order to get access. While this type of access worked in most situations, we uncovered the following challenges:

Contract expiry on the SIM prevented access when they needed it most

Risk in exposing the management interface to the Internet

Lack of visibility of the SIM inventory

Lack of visibility by the security team of cellular device's network & cellular events

Enter Zscaler Cellular for OOB. Zscaler Cellular was able to overcome these challenges and provide much more. A full inventory of SIMs are made available in the Zscaler cellular dashboard including attributes like:

Connection State: Online/Offline

Country

IP Address

Data usage

So now if a SIM happens to go offline, they will now be able to see it as soon as it happens.

Digging in a little deeper, we can also drill into more detailed info over time such as:

Current Mobile Network Operator

Data usage per day

This provides full visibility of the SIM inventory, its location and recent activities.

Next we can see the SIMs are assigned private IP addresses. All network activity from the SIM is automatically delivered into the customer's Zscaler tenant. Access to the Internet has a ZIA policy applied to it. The customer now has full visibility and control of any access to the Internet. The security team team now has visibility, where previously they had none.

How about remote access? All the sims are integrated into the customers ZPA tenant. A simple ZPA policy is all that is needed to extend access from the Zscaler Client Connector into the actual SIM. This helps reduce their attack surface as well as make more progress on the customers adoption of Zero Trust.

This can be taken a step further, with my customer, they needed a way to provide third party access to these devices. This can be enabled with ZPA Browser Based Access. Using identity and a web browser, this same level of access can be extended. And not just for browser based apps, in this case, SSH is also supported through Privileged Remote Access:

In summary, we helped this customer overcome their challenges, enable access more simply and help them become more secure. #zscalercellular

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Blog Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/Guides/aSoPJ0000000E6D0AU/welcome-to-zscaler-cellular","lastmod":"2025-04-28T14:39:01.000Z","id":"aSoPJ0000000E6D0AU"} -->
## Welcome to Zscaler Cellular

- Source: https://community.zscaler.com/s/Guides/aSoPJ0000000E6D0AU/welcome-to-zscaler-cellular
- Type: Guide
- Last activity: 2025-04-28T14:39:01.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Guide Details

Zscaler Cellular Service

Nathan_Howe

(Employee) posted a Guide

Edited April 28, 2025 at 2:39 PM

Welcome to Zscaler Cellular

The rumors were true, Zscaler has been hard at work in building cellular interconnects to the Zero Trust Exchange. This is currently in Limited Availability for a large set of our customers (with GA being very close)

This cellular community is here to answer your questions, garner your feedback/direction and show you best practices. We look forward to engage with you about this exciting technology.

For more public available information, check out:

This blog post

This article

And don't forget to view this YouTube introduction

Lastly if you want to find out or get involved in using the platform, reach out via a post here on Zscaler Community!

Ramesh Mani

(Partner)

1y ago

Great initiatives, and simplified way of connecting corporate network and secure internet access for corporate users without agents.

Worth looking at this link.

Ijura Enterprise - Mobile Threat Defense

Osho_Dwivedi

(Employee)

1y ago

Innovation Excellence at it's peak! Great work Nathan and team!

ian.wharton

(Partner)

1y ago

Thanks Nathan

I'd be keen to learn more about this. Will there be more information at ZL 2025?

Regards

Ian

Nathan_Howe

(Partner)

1y ago

Hi Ian,

Yes, there will be a lot more detail at Zenith Live. We will have a breakout and will host an innovation booth, so come by and challenge us and ask any questions.

Excited to see you there!

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

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

Guide Details
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmU7CAI/video-aws-cloudformation-deployment","lastmod":"2023-05-31T09:08:53.000Z","id":"0D54u00009evmU7CAI"} -->
## [VIDEO] AWS CloudFormation Deployment

- Source: https://community.zscaler.com/s/question/0D54u00009evmU7CAI/video-aws-cloudformation-deployment
- Type: Q&A
- Last activity: 2023-05-31T09:08:53.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

Aaron_Rohyans

(Employee) to

sfdc

(Employee): asked a question.

August 10, 2022 at 2:28 PM

[VIDEO] AWS CloudFormation Deployment

Cloud Connector is a virtual appliance within AWS used to forward cloud workload traffic to the Zero Trust Exchange. It can be deployed within an AWS environment using both Terraform and, as a more native scripting option, CloudFormation. Zscaler CloudFormation scripts assume a customer already has an existing cloud deployment that they wish to integrate Zero Trust Network Access principles within. As such, VPCs, NAT Gateways, Internet Gateways, Subnets, and Route Tables should already be configured prior to running these scripts.

The Pre-deployment Template ensures certain prerequisites are met prior to running any additional CloudFormation scripts. This script should be run first before running the Starter Deployment Template.

The Starter Deployment Template will install a single Cloud Connector appliance within the Subnet chosen and is a requirement for all other scripts. In fact, we recommend that you run this script multiple times to install multiple Cloud Connector VMs within various Availability Zones to satisfy High Availability requirements.

The Add-on Template for ZPA will add AWS Route 53 functionality. You can learn more about how ZPA interacts with Cloud Connector via the other videos on this Communities page. In a nutshell, Route 53 allows an administrator to influence cloud workload DNS requests to cross over the Cloud Connector appliance - allowing the appliance to then proxy that traffic by responding to the DNS Request with a synthetic IP address.

The Add-on Template for ZPA and High-Availability will add support for both ZPA and AWS Lambda functionality. Please note that AWS Lambda functionality exists to provide backward compatibility for customers who have not yet migrated to Gateway Load Balancer. Zscaler recommends running the Add-on Template with Gateway Load Balancer script instead if a customer is seeking High Availability.

The Add-on Template with Gateway Load Balancer (GWLB), as the name would imply, installs a Gateway Load Balancer as well as all the necessary GWLB endpoints and Target Group necessary for High Availability.

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

The Starter Deployment Template will instantiate a single Cloud Connector appliance and associate it with the Subnet and Route Table specified in the CloudFormation workflow. This script is a requirement in order to run any of the other CloudFormation scripts.

The Add-on Template with ZPA script will instantiate Route 53 resources for outbound DNS resolution and redirection to the ZPA service for use-cases where Zscaler Zscaler Private Access is the requirement. For more information on ZPA, DNS redirection, and its interaction with Cloud Connector, please check out the AWS DNS Setup for ZPA video linked in the description.

The Add-on Template with High-Availability script will instantiate AWS Lambda functionality for high availability. This script assumes that a pair of Cloud Connector instances already exist (with associated Subnets, Route Tables, and Availability Zones) and that a High Availability port was selected during their instantiation. It should be noted that, as of this recording, AWS Gateway Load Balancer is now also supported and will become the new recommendation for High Availability instead of Lambda. AWS Gateway Load Balancer CloudFormation scripts will be available for download in the Cloud Connector portal as well and will be used as the basis for this demonstration.

Start by navigating to your AWS console and searching for CloudFormation. Click the Create Stack button and choose 'With new resources. Upload the Starter Deployment Template script. Provide a stack name, then select the resources that fit your deployment. In this case, we’ll choose a pre-configured VPC, Subnet, Availability Zone, and Keypair. For testing purposes, Zscaler recommends the T3.medium Instance Type. For production deployments, choose C5.large or M5.large. Provide the name of your Secrets Manager object as well as an HTTP port. Though the HTTP port is optional, Zscaler highly recommends a port be entered here so that high availability can be configured. This port identifies a heartbeat service that the appliance uses to report its current health to the AWS Gateway Load Balancer or Lambda function.

Click the Next button, followed by Next again… acknowledge the changes and click the Create Stack button. The script begins to execute and deploy the Cloud Connector resources.

It is highly recommended that you run this script a second time against an adjacent Availability Zone to provide fault tolerance for your implementation.

Once the appliances have been deployed and have registered with the Cloud Connector portal, you can proceed with installing the Gateway Load Balancer service. In the same way that the Cloud Connector appliances were installed, upload the GWLB macro script first. This script pre-configures the environment for GWLB. Once this script is executed, create a new CloudFormation stack with the GWLB script. Select the instances you created previously and identify the HTTP port they were instantiated with.

Choose whether to enable cross-zone load-balancing. GWLB will, by default, attempt to maintain Availability Zone affinity. In the event of an appliance failure, cross-zone load-balancing can be turned on using this dropdown - allowing GWLB to ignore Availability Zone affinity and forward traffic to any available appliance. Be aware that this may incur additional costs from AWS.

Lastly, if ZPA integration is desired, create a CloudFormation stack to enable Route53 functionality. Here, input your Cloud Connector and ZPA cloud name. Application Segment FQDNs defined in ZPA can then be configured in the Domain Name fields. This script will instruct Route 53 to redirect DNS traffic for these domains through the Cloud Connector appliance - allowing the appliance to proxy application traffic via synthetic IP Addresses.

Though useful in Greenfield situations, CloudFormation scripts shine when implemented in a Brownfield environment with existing infrastructure.

You can download CloudFormation scripts from the Cloud Connector portal via the Administration > Deployment Templates menu.

Make sure you have met the pre-requisites prior to running a CloudFormation script, then upload and execute them from your AWS console.

Cloud Connector

510 views

Niladri_Datta

wguilherme

, and

2 others

like this.

Log In to Answer

Associated Tags

aws

video

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,726

1726 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,475

1475 Views

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

[VIDEO] AWS CloudFormation Deployment
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmewCAA/how-to-find-current-cloud-connector-version","lastmod":"2023-07-07T11:22:11.000Z","id":"0D54u00009evmewCAA"} -->
## How to find current Cloud Connector version?

- Source: https://community.zscaler.com/s/question/0D54u00009evmewCAA/how-to-find-current-cloud-connector-version
- Type: Q&A
- Last activity: 2023-07-07T11:22:11.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

jmolnar

(Employee) to

sfdc

(Employee): asked a question.

Edited by sfdc July 7, 2023 at 11:22 AM

How to find current Cloud Connector version?

Zscaler for Workloads (Cloud Connector) utilizes an IaaS VM deployment from the respective public cloud marketplaces.

What is Cloud Connector?

As with many cloud offerings, Cloud Connector follows a Shared Responsibility Model where Zscaler provides the VM image and maintains all software/application and OS patching. The customer simply needs to deploy the actual compute resources to run the appliance in their account/tenant. Generally, software updates are pushed incrementally per Zscaler release schedules. Periodically, we will release new versions of the VM image to the AWS/Azure marketplaces though. This could be due to a variety of reasons, such as a new feature release, OS/Kernel update that would be too disruptive or difficult to update in-place, or other performance/boot optimizations. When this happens, the following things will occur:

Zscaler will update Cloud Connector

Release Notes

. (Example linked when Azure Accelerating Networking support was introduced).

Cloud Connector Portal Banner update. This may include additional information or just link to the release notes.

What action is required for existing Cloud Connector customers?

Per the release notes, depending on the nature of the update you may want to upgrade existing Cloud Connector clusters by deploying new/updated appliances to replace the old images. (

Suggested options of accomplishing this will be documented in a future article

). In general though, Zscaler’s

recommendation is to always be running the latest AMI/VM

. If there is a specific new feature requirement/bug impacting an existing customer deployment, that may also be a driver to update.

How do I know if I am running the latest Cloud Connector VM version?

Zscaler provides automation templates CFT/Terraform (AWS) and Terraform/managed app (Azure) that, when run, will always pick up the Latest VM version so there is no customer action needed when deploying new VMs as long as they are deploying the latest automation templates. Locating your current virtual machine version can be accomplished a few different ways and also varies between AWS and Azure.

AWS:

You can locate all current and historical marketplace OS/VM versions and corresponding AMIs per region in the AWS marketplace

here

. From this example screenshot, you can see at the time of this article the latest AWS image is software version: ZS6.1.24.3 and corresponding AMI in region US West 2 is ami-08811594b4563ee8e.

image

603×529 31.2 KB

You can then verify what version you have deployed in the AWS Console for any running Cloud Connector EC2 instances.

image

1066×660 58.7 KB

Azure:

Azure does not make viewing a Virtual Machine image version from the Azure Portal very easy. From any VM Overview, they only display Publisher, Offer, and Plan details.

To locate the currently deployed version for this Offer, the easiest option is to query via AZ CLI. This can be done from a local system with AZ CLI installed and able to successfully authenticate with “az login?. Then, run the following commands:

az vm image show --urn zscaler1579058425289:zia_cloud_connector:zs_ser_gen1_cc_01:latest

<<< will provide all information about the latest VM offering version. You can then search for the latest Version in the “id? attribute. The output below from the command shows the latest version is “24.2.7?.

image

1897×820 27.9 KB

az vm list -g “resource group name? | grep -A 9 imageReference

<<< check that “exactVersion? for the currently deployed Cloud Connector appliances matches the previous latest version.

image

800×225 6.89 KB

Cloud Connector

Discourse-expand

What-action-is-required-for-existing-cloud-connector-customers-1

+4 more

1.46K views

Log In to Answer

Associated Tags

azure

aws

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,726

1726 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,473

1473 Views

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

How to find current Cloud Connector version?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmgUCAQ/difference-between-zscaler-dc-vs-zscaler-hub","lastmod":"2023-05-31T08:43:52.000Z","id":"0D54u00009evmgUCAQ"} -->
## Difference between Zscaler DC vs Zscaler HUB?

- Source: https://community.zscaler.com/s/question/0D54u00009evmgUCAQ/difference-between-zscaler-dc-vs-zscaler-hub
- Type: Q&A
- Last activity: 2023-05-31T08:43:52.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

kkang

(Customer) to

sfdc

(Employee): asked a question.

December 9, 2022 at 3:49 AM

Difference between Zscaler DC vs Zscaler HUB?

Hi there,

Can anyone clarify the difference between Zscaler DC and Zscaler HUB?

I see Zscaler has some IP subnets for Zscaler DC IPs and Zscaler Hub IPs, respectively.

But I am confused some with the difference.

What is Zscaler Hub roles?

Thank you

Cloud Connector

Hub-ip-addresses-1

1 answer

1.04K views

Pardeep09

(Partner)

4 years ago

As per the Zscaler note -

Hub IP Addresses

For customers using third party authentication hosted by the customer (Active Directory/OpenLDAP) with ports (389, 636, 3268, 3269) or Client Autentication is being achieved via Kerberos with port 88 (TCP/UDP) and any customers or partners with Zscaler private infrastructure deployed,

And Zscaler DC IP are the proxy ip to which end user communicated for internet access.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,726

1726 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,473

1473 Views

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

Difference between Zscaler DC vs Zscaler HUB?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhPCAQ/cannot-create-dropbox-transfershare-from-mac-finderdropbox-app","lastmod":"2023-05-31T09:27:35.000Z","id":"0D54u00009evmhPCAQ"} -->
## Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

- Source: https://community.zscaler.com/s/question/0D54u00009evmhPCAQ/cannot-create-dropbox-transfershare-from-mac-finderdropbox-app
- Type: Q&A
- Last activity: 2023-05-31T09:27:35.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

Chris_Kroll

(Customer) to

sfdc

(Employee): asked a question.

November 14, 2022 at 3:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

I’ve built out the appropriate SSL and tenant rules to allow Dropbox to work with our tenant but block other file sharing sites and it seems to work as expected if using a browser. However, when I attempt to create a file transfer and/or copy a link from within the Mac Finder/Dropbox shares, it appears to start the process but just stays at 0%. I can shut down the connector and the process works as expected.

When you review the logs, there are is no blocked activity related to Dropbox. Also, accessing Dropbox shares/folders/files via Mac Finder/Dropbox app works fine. It just seems to be this transfer/copy process that is broken. Anyone ever run across this??

Cloud Connector

3 answers

448 views

Top Rated Answers

Chris_Kroll

(Customer)

4 years ago

Figured it out with the help of support. It’s documented here:

help.zscaler.com

Certificate Pinning and SSL Inspection | Zscaler

Information on Certificate Pinning and SSL Inspection, and how it impacts the Zscaler service.

Selected as Best

All Answers

Chris_Kroll

(Customer)

4 years ago

NM…got it. I hate to be thick here but, how do I mark it as solved? Sorry…new guy to the forum.

Ben_Garrison

(Employee)

4 years ago

If that is the solution, can you please mark it as solved?

Thanks! Glad to hear it

Chris_Kroll

(Customer)

4 years ago

Figured it out with the help of support. It’s documented here:

help.zscaler.com

Certificate Pinning and SSL Inspection | Zscaler

Information on Certificate Pinning and SSL Inspection, and how it impacts the Zscaler service.

Selected as Best

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,718

1718 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,470

1470 Views

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

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhrCAA/allow-speedtest-app-on-windows10-machines","lastmod":"2023-07-06T12:53:41.000Z","id":"0D54u00009evmhrCAA"} -->
## Allow speedtest app on Windows10 machines

- Source: https://community.zscaler.com/s/question/0D54u00009evmhrCAA/allow-speedtest-app-on-windows10-machines
- Type: Q&A
- Last activity: 2023-07-06T12:53:41.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

AntoninoPace

(Customer) to

sfdc

(Employee): asked a question.

October 24, 2022 at 8:24 AM

Allow speedtest app on Windows10 machines

Hello dear comunity

I’ve been going crazy since last Friday but I can’t get the speed

test.net

app to work on windows 10 in any way and even from

speedtest.net

site , the test never starts and remains frozen in the search for the best server connection.

With Zscaler client connector turned off everything looks fine .

Any suggestions ?

Cloud Connector

6 answers

945 views

kj665

(Employee)

4 years ago

It sounds like you might just have some kind of issue with your tunnel 2 traffic. Try this link and see if your traffic flows as expected.

http://127.0.0.1:9000/ztest?=youruser@yourdomain.com

Also,

speedtest.zscaler.com

Check your weblogs to see what happens when you make that request to the speedtest sites. Using

speedtest.com

or similar is not the best test but you should at least be able to get to them.

AntoninoPace

(Customer)

Edited by sfdc July 6, 2023 at 11:54 AM

Hello to the whole Zscaler community

What i have discovered is that both Speed Test App and

speedtest.net

are working if i’m using 1.0 Ztunnel, but are not working when using Z-Tunnel 2.0.

Someone can tell me why and if there is something else i can do ?

kj665

(Employee)

4 years ago

I cannot speak for why that app is not working but you are better off looking at what is going on with your traffic. How are you forwarding your traffic? Do you subscribe to ZDX?

Check

trust.zscaler.com

for any issues at your DC.

If you are using ZCC and Tunnel 2.0, you might be seeing de-prioritization of UDP traffic with DTLS. You can test with TLS tunneling. Run packet captures and look for bad packets. There are other tools you can run built into ZCC that will show you more information too.

http://127.0.0.1:9000/ztest?=$youruser@yourdomain.com

If you are connected via tunnel 1.0 on ZCC, you can run PCAPs and review your bad packets too. You can also hard-code into your PAC file to use a different DC to see the experience. Review your weblogs for proxy latency.

If you use GRE vs IPsec, that would be a difference can of worms.

2 likes

G-Man8

(Customer)

4 years ago

I would check the ZIA Firewall logs for your

speedtest.net

session.

Also have a look at this

article

because we were constantly asked about this while deploying ZCC

1 like

AntoninoPace

(Customer)

4 years ago

Hi Gerhard , yes it works , showing a speed of around 41 megabits (which is not great btw).

With Zia off usually from office is around 80-100 megabits

G-Man8

(Customer)

4 years ago

Hi Antonino,

But

speedtest.zscaler.com

works fine ?

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,726

1726 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,473

1473 Views

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

Allow speedtest app on Windows10 machines
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmhvCAA/fedora-36-installation-error-upon-installing-prerequisite-dependencies","lastmod":"2023-05-31T08:12:57.000Z","id":"0D54u00009evmhvCAA"} -->
## Fedora 36 Installation error upon installing Prerequisite Dependencies

- Source: https://community.zscaler.com/s/question/0D54u00009evmhvCAA/fedora-36-installation-error-upon-installing-prerequisite-dependencies
- Type: Q&A
- Last activity: 2023-05-31T08:12:57.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

Da_Gr8_0n3

(Customer) to

sfdc

(Employee): asked a question.

October 19, 2022 at 1:46 PM

Fedora 36 Installation error upon installing Prerequisite Dependencies

Slack 0.0 B/s | 0 B 00:02

Errors during downloading metadata for repository ‘slack’:

Curl error (60): SSL peer certificate or SSH remote key was not OK for

https://packagecloud.io/slacktechnologies/slack/fedora/21/x86_64/repodata/repomd.xml

[SSL certificate problem: unable to get local issuer certificate]

Error: Failed to download metadata for repo ‘slack’: Cannot download repomd.xml: Cannot download repodata/repomd.xml: All mirrors were tried

Ignoring repositories: slack

Last metadata expiration check: 2:10:38 ago on Wed 19 Oct 2022 05:10:05 AM PST.

Package net-tools-2.0-0.61.20160912git.fc36.x86_64 is already installed.

No match for argument: libqt5dbus5

No match for argument: libqt5core5a

No match for argument: libqt5sql5

No match for argument: libqt5sql5-sqlite

Error: Unable to find a match: libqt5dbus5 libqt5core5a libqt5sql5 libqt5sql5-sqlite

bash: libqt5webchannel5: command not found…

bash: libqt5webkit5: command not found…

installation works fine, but it blocks Slack,

Cloud Connector

580 views

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,717

1717 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,470

1470 Views

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

Fedora 36 Installation error upon installing Prerequisite Dependencies
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmiWCAQ/adding-custom-saas-application-tenants-in-zscaler","lastmod":"2023-05-31T08:44:31.000Z","id":"0D54u00009evmiWCAQ"} -->
## Adding custom SaaS application tenants in Zscaler?

- Source: https://community.zscaler.com/s/question/0D54u00009evmiWCAQ/adding-custom-saas-application-tenants-in-zscaler
- Type: Q&A
- Last activity: 2023-05-31T08:44:31.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

Rahul7869788

(Customer) to

sfdc

(Employee): asked a question.

September 22, 2022 at 10:19 AM

Adding custom SaaS application tenants in Zscaler?

Adding custom SaaS application tenants in Zscaler ?

for example i want to connect Workday how is that possible?

Cloud Connector

1 answer

498 views

Ramesh Mani

(Partner)

4 years ago

You can use only the predefined SaaS application tenants. There is no option to define the custom SaaS application tenants on cloud version 6.1

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,726

1726 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,476

1476 Views

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

Adding custom SaaS application tenants in Zscaler?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evminCAA/how-to-find-urls-that-should-not-be-inspected","lastmod":"2023-07-06T12:55:05.000Z","id":"0D54u00009evminCAA"} -->
## How to find URLs that should not be inspected

- Source: https://community.zscaler.com/s/question/0D54u00009evminCAA/how-to-find-urls-that-should-not-be-inspected
- Type: Q&A
- Last activity: 2023-07-06T12:55:05.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

webb1976-bf

(Customer) to

sfdc

(Employee): asked a question.

September 10, 2022 at 11:34 AM

How to find URLs that should not be inspected

During our POC, our technician created a bunch of URL groups (ie: “Cert-Pinning Google?) with a bunch of URLs that are in an SSL Policy to NOT be inspected.

While this works in the environment, un-inspected traffic does not allow Zscaler to inspect and report on it.

On yesterday, I removed all of these URLs (for the Google one) and after a few hours I got reports that certain apps did not work like Google Drive. I put them back in and now am trying to filter through Web Insight Logs to see which ones were having SSL problems.

What is the proper things I should be filtering on in order to see this?

Cloud Connector

2 answers

1.06K views

Andreas

(Customer)

Edited by sfdc July 6, 2023 at 11:55 AM

Hi,

we do not inspect the “Finance? and the “Government? categories as those pages have often issues.

The others we basically found out due to “tickets and complaints?. Most offen caused by fat clients / java clients that do not use the system cert-store.

I am now noting down dates, when I activate “more? of full scale SSL Inspection… Since we event got tickets like… “does not work! - since when - 3 month ago? - and no one remembered that we activated more ssl inspections 3 month ago.

Best regards

Andreas

Best regards

Andreas

PavelK

(Customer)

4 years ago

Hello

@webb1976-bf

unfortunately, there is no easy way to find a certificate pinning issue in Insights Logs. When you have certificate pinning and when a user gets to a site and site is asking for certificate, the Zscaler will allow this all the way through, however the retrieval of certificate from local certificate store in local machine is what is failing.

Zscaler has put together a list of sites that are known to have certificate pissing issue:

Certificate Pinning and SSL Inspection | Zscaler

You might want to refer to this link to for exclusion.

I heard that in next ZIA release 6.2 there should be added feature to be able to recognize a certificate pinning from logs, however for now options are limited.

Kind Regards

Pavel

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,718

1718 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,471

1471 Views

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

How to find URLs that should not be inspected
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmj1CAA/using-sipa-source-ip-anchoring-with-cloud-connectors","lastmod":"2025-07-14T16:43:56.000Z","id":"0D54u00009evmj1CAA"} -->
## Using SIPA (source ip anchoring) with Cloud Connectors

- Source: https://community.zscaler.com/s/question/0D54u00009evmj1CAA/using-sipa-source-ip-anchoring-with-cloud-connectors
- Type: Q&A
- Last activity: 2025-07-14T16:43:56.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

zoltan

(Employee) to

sfdc

(Employee): asked a question.

September 7, 2022 at 3:18 PM

Using SIPA (source ip anchoring) with Cloud Connectors

You might be using SIPA for your existing Users on the Zscaler platform. This same capability is possible for Workloads that are forwarding to the Zscaler Zero Trust Exchange via Cloud Connectors. Please find the attach guide with step-by-step instructions and a demo video to walk you through the configuration.

Configure SIPA with Cloud Connectors - SEP2022.pdf

(1.1 MB)

Cloud Connector

5 answers

1.64K views

dperkins

avshch

, and

3 others

like this.

avshch

(Customer)

3 years ago

@zoltan

Thank you, Zoltan. Our use case is to access legacy .gov web-site. The access is locked by a 3rd party to specific public ip addresses. Any other access is not allowed. I was trying to follow the steps provided via

Loom | Free Screen & Video Recording Software | Loom

to configure the access with SIPA. I’m not even sure if the cert is required or not.

Unfortunately, I’ve run into an issue with uploading the cert to zscaler (for access from browser). It is described at the following post

ZPA application segment configuration for browser access with SIPA enabled

zoltan

(Employee)

3 years ago

Hey

@avshch

that’s a great question! I personally have not tested TBH as I am on the cloud connector team but that should absolutely work for all forwarding methods including GRE/IPSEC.

Similar caveats will be there - for web apps you don’t need to forward DNS to Zscaler, but for non-web apps you will need to ensure the initiating users/servers do have DNS going to Zscaler.

With that said, the only way to technically have workloads/servers forward to ZPA is using the Zscaler solution (cloud/branch connector) but since SIPA has the “next hop? going to ZIA, these flows will work.

Hope this helps!

avshch

(Customer)

3 years ago

@zoltan

Would this method work if the main forwarding method is all IPSEC tunnel?

PratikShelar3125

(Customer)

a year ago

Hi @Zoltan Kovacs​ ,

Greetings!!

Currently, I am unable to see the PDF(no longer available), Please let me know how can I get that PDF - if still available on some different links, please do share the same

Thanks

zoltan

(Employee)

a year ago

hey @Pratik Shelar​ apologies not sure what happened. Please find the guide attached as PDF again so you should be good to go!

Even though this guide is a bit older now, I can confirm nothing has changed from the Zscaler perspective. When it comes to Zscaler platform capabilities like SIPA (or even Dedicated IP /ENAT) the configurations are all just done in ZIA (and ZPA for SIPA) the same way for client and cloud connector. So just keep in mind no special configuration is needed for Cloud Connectors in order to take advantage of SIPA feature. Long as the forwarding control/profile in ZIA has the criteria set to locations (or sublocations) to send certain apps to ZPA for SIPA purposes, you'll be set.

Hope this helps!

Log In to Answer

Associated Tags

azure

aws

sipa

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,718

1718 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,472

1472 Views

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

Using SIPA (source ip anchoring) with Cloud Connectors
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmj6CAA/url-getting-block","lastmod":"2023-05-31T08:44:42.000Z","id":"0D54u00009evmj6CAA"} -->
## URL getting block

- Source: https://community.zscaler.com/s/question/0D54u00009evmj6CAA/url-getting-block
- Type: Q&A
- Last activity: 2023-05-31T08:44:42.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

mohdfarhan

(Partner) to

sfdc

(Employee): asked a question.

September 1, 2022 at 5:12 PM

URL getting block

Hi team, I am new on the Zscaler portal please help with the below error send by client machine.

The below URL is not working fine. Please find the attached snapshot.

Access is denied due to bad certificate also We found a security threat.

As per my knowledge the server used the old cert so i will bypass it so can you please tell me the troubleshooting steps for bypass the URL.

Cloud Connector

1 answer

508 views

dan.carlson

(Partner)

4 years ago

I’d love to help, but I unfortunately don’t see a URL or an attachment.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,718

1718 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,470

1470 Views

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

URL getting block
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjXCAQ/video-call-home","lastmod":"2023-05-31T09:08:51.000Z","id":"0D54u00009evmjXCAQ"} -->
## [VIDEO] Call Home

- Source: https://community.zscaler.com/s/question/0D54u00009evmjXCAQ/video-call-home
- Type: Q&A
- Last activity: 2023-05-31T09:08:51.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

Aaron_Rohyans

(Employee) to

sfdc

(Employee): asked a question.

August 10, 2022 at 3:06 PM

[VIDEO] Call Home

When issues arise, as they often do, how can we guarantee that we capture enough information to not only remediate the issue but also identify what caused it and prevent it from happening again? This is the job of the Call Home functionality within Cloud Connector. As Cloud Connector appliances process cloud workload traffic, the Call Home process periodically captures log files and reports this information to Zscaler engineering. In the event your appliance experiences a problem, Zscaler engineering then immediately has access to the information necessary to both remediate the issue and perform a root-cause analysis.

The feature works by establishing a limited two-way communication channel between the customer’s AWS account and Zscaler’s AWS account. The Cloud Connector appliance, using its configured and attached IAM role, then assumes the

callhome-delegation-role

configured within Zscaler’s AWS account. If authorized, temporary credentials will then be passed back to the Cloud Connector appliance. These credentials are then used to authenticate to the Zscaler S3 bucket where the Cloud Connector will post its log files. Zscaler engineering can then collect the posted files from the S3 bucket and begin diagnosis.

In this video, we’ll explore:

[0:00 to 0:41] What is Call Home?

[0:41 to 1:28] How does Call Home work?

[1:28 to 3:13] How do you configure Call Home?

[3:13 to 4:10] What are the key takeaways?

Transcript

Hello, my name is Aaron and I’m one of the Principal Technical Product Specialists for Zscaler Cloud Workload Protection.

In this video, we’ll discuss how you can set up the Cloud Connector appliance to utilize Call Home functionality within AWS.

Call Home is built-in functionality to export certain data and statistics to Zscaler in the occurrence of a fatal event in the Cloud Connector VM - such as instance crashes, failure to boot, etc. These statistics are used by Zscaler engineering to diagnose and rectify any issues encountered, or to perform root-cause analysis. It is highly recommended that Call Home functionality be enabled when building new Cloud Connector appliances in order to assist with any future troubleshooting efforts.

The feature works by establishing limited 2-way communication between the customer’s AWS account and Zscaler’s AWS account. In the event of a failure or issue, the Call Home functionality is invoked. The Cloud Connector appliance, using its configured and attached IAM role, then attempts to assume the

callhome-delegation-role

configured within Zscaler’s AWS account. If authorized, temporary credentials will then be passed back to the Cloud Connector appliance. These credentials are then used to authenticate to the Zscaler S3 bucket where the Cloud Connector will post its log files. Zscaler engineering can then collect the posted files from the S3 bucket and begin diagnosis.

There are two steps to configure Call Home. First, the necessary policy needs to be added to the IAM role attached to the Cloud Connector appliance and second, the ARN of that role needs to be provided to Zscaler support. This will ensure a mutual trust exists between the two entities.

To begin, navigate to the EC2 Instances dashboard of your AWS account. Find the EC2 instance for the Cloud Connector appliance. Scroll down to the Instance IAM Role details and click on the role shown. In the permissions tab, click on the Add permissions button, followed by Create inline policy. Choose the JSON tab. In the blank field that appears, enter the following info shown on your screen. A copy of this information will be placed in the description of this video as well:

"Version": "2012-10-17",

"Statement": [

"Sid": "AllowDelegationForCallhome",

"Effect": "Allow",

"Action": "sts:AssumeRole",

"Resource": "arn:aws:iam::223544365242:role/callhome-delegation-role"

Please note that Terraform and CloudFormation scripts will eventually be adjusted to perform this step for you automatically. Hence, depending on when you’re watching this video, you may not need to adjust your IAM Role. As a matter of best practice, however, you should review the configured roles and permissions to both ensure they align with organization security policy and for the presence of the displayed permission for Call Home.

As a final step in establishing 2-way trust, copy the ARN of the updated IAM role attached to your appliance. This ARN must be supplied to Zscaler engineering to be added to our policy. Please open a support ticket and provide the ARN for each of your Cloud Connector IAM roles in use. Each Cloud Connector maintains its own IAM role and, hence, ARN… so ensure you include all ARNs in the ticket.

That’s it! The Cloud Connector will now use the Call Home functionality to report failures directly to Zscaler engineering. To test this functionality, you can do so from the command line of the appliance itself. Once logged in, enter the root shell and execute the following command:

januscli callhome backup \--instance-dir /sc/instances/edgeconnector0 \--paths /sc/instances/edgeconnector0/core/

…by default, the command will pack up the displayed paths and export them to Zscaler:

/etc/janus/

/var/run/janus*

/sc/instances/edgeconnector0/conf/

/sc/instances/edgeconnector0/logs/

Should the Call Home fail, ensure that the trust relationship exists and there are no 400 errors - such as a 403 Unauthorized - and that space exists on the appliance itself to generate the archives to be posted. If in doubt, please contact Zscaler support for more information.

– Cloud Connector appliances can automatically export logs and statistical information to Zscaler engineering for troubleshooting and root-cause analysis via Call Home

– Call Home works by establishing mutual trust between the Cloud Connector appliance IAM Role and Zscaler. In the event of issues, this trust can be leveraged to deposit log archives into Zscaler’s S3 bucket

– Terraform and CloudFormation will automatically add the necessary IAM permissions, but you should review them as a matter of best practice. You may need to manually add the IAM policy, depending on when you’re watching this video

– Open a Zscaler support ticket to have the Cloud Connector IAM Role ARNs added to the Zscaler Trust Relationship. Remember, each Cloud Connector has a separate IAM Role and ARN, so ensure all of them are provided

Cloud Connector

442 views

David.Glading

likes this.

Log In to Answer

Associated Tags

video

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,726

1726 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,475

1475 Views

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

[VIDEO] Call Home
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjYCAQ/video-session-logging","lastmod":"2023-05-31T09:08:53.000Z","id":"0D54u00009evmjYCAQ"} -->
## [VIDEO] Session Logging

- Source: https://community.zscaler.com/s/question/0D54u00009evmjYCAQ/video-session-logging
- Type: Q&A
- Last activity: 2023-05-31T09:08:53.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

Aaron_Rohyans

(Employee) to

sfdc

(Employee): asked a question.

August 10, 2022 at 3:04 PM

[VIDEO] Session Logging

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

Let’s generate a bit of traffic from one of our test hosts and follow it through the network. Logging in to our host at 10.2.1.81, let’s open a web browser and navigate to a few websites. First and foremost, some general connectivity checks to

ip.zscaler.com

and perhaps some random websites like Disney and ESPN. Of course, a staple in any engineering toolbag, let’s check our IP address with

ipinfo.io

. And maybe again through ipaddress.my. Interestingly, did you note how the IP address was different? One showed as an AWS address, the other showed as a ZIA address. Let’s check the logs to see what happened.

From the Session Insights tab, let’s narrow our logs down to our 10.2.1.81 host again. Here, we see our traffic to ESPN, Disney,

ip.zscaler.com

and

ipinfo.io

. Note how all of these use ZIA as the Forwarding Method. Our request to ipaddress.my, however, went Direct. This is because of a Forwarding Rule we have configured for that specific website. You can learn more about Traffic Forwarding rules in the Forwarding Policy video.

Moving into the ZIA dashboard, from the Web Insights log, we can review this same traffic as it was seen entering the Zero Trust Exchange.

– Cloud Connector appliances log control and data traffic within the Cloud Connector portal

– These logs provide a separate vantage point for engineers seeking to review network traffic as it passes out of the cloud

– Logs are split into three categories: Session, DNS, and Tunnel Insights. Each of these logs provides unique statistics on the traffic that passes through the Cloud Connector as well as the traffic originated by the Cloud Connector

– You can use Nanolog Streaming Service (NSS) to export these logs to an external event collector for further processing

Cloud Connector

473 views

Niladri_Datta

likes this.

Log In to Answer

Associated Tags

video

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,726

1726 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,475

1475 Views

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

[VIDEO] Session Logging
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjbCAA/video-forwarding-policy","lastmod":"2023-05-31T09:08:51.000Z","id":"0D54u00009evmjbCAA"} -->
## [VIDEO] Forwarding Policy

- Source: https://community.zscaler.com/s/question/0D54u00009evmjbCAA/video-forwarding-policy
- Type: Q&A
- Last activity: 2023-05-31T09:08:51.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

Aaron_Rohyans

(Employee) to

sfdc

(Employee): asked a question.

August 10, 2022 at 2:57 PM

[VIDEO] Forwarding Policy

Once a Cloud Connector is up and operational, traffic will be directed to it via cloud routing. This is generally done through a simple default route. Regardless, after traffic has reached the Cloud Connector, there are three Forwarding policy types available to direct traffic out of the cloud: Traffic Forwarding, Log and Control, and DNS Policies.

Log and Control Policies allow an administrator to identify control-plane traffic from specific cloud locations and redirect this traffic to a specified Zscaler Logging Gateway. Generally speaking, Zscaler recommends that this option be adjusted only under the supervision of Zscaler support, however, since the appliance will automatically select a Log and Control Gateway that compliments the Data Plane PoP chosen.

DNS Policies find their usefulness with regards to ZPA use-cases. As discussed in other videos, the Cloud Connector must see DNS traffic from workload machines in order to proxy their traffic within ZPA. The traffic is proxied via synthetic IP addressing hosted within the Cloud Connector. Administrators can use DNS Policies to allow, block, and forward DNS requests for ZPA-bound traffic. Furthermore, when forwarding to ZPA, DNS Policies also allow the administrator to specify the synthetic IP ranges used.

Traffic Forwarding Policies are by far the most common policy adjustment that a customer may wish to entertain. These policies allow administrators to influence how data plane traffic is directed through the Cloud Connector.

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

The Direct forwarding option allows traffic matching the criteria defined to bypass ZIA/ZPA and hair-pin back out of the appliance, where it will follow underlay cloud Route Tables towards the destination. This type of forwarding rule is useful for allowing workloads to reach cloud-native services without having to “boomerang? through ZIA or ZPA. For instance, when accessing an AWS S3 Bucket, a Direct Forwarding Rule can be created to allow AWS workloads to access the resource directly, without being inspected by ZIA. Furthermore, Direct Forwarding Rules also find usefulness in IP whitelisting, since traffic can be forwarded straight from the cloud, where a predictable IP will be used (in this case, the NAT Gateway). Keep in mind, however, that this traffic will be Source NAT’d to the Service Interface IP Address first, then NAT’d again when egressing the cloud.

Second, the Zscaler Internet Access (ZIA) option, as implied, will allow traffic matching the criteria defined to be forwarded to the Zscaler Internet Access cloud for inspection. By default, for ZIA customers a rule will be automatically created for you to send all traffic to ZIA. This may be acceptable, or, a customer may wish to modify this behavior by creating more specific rules to define which traffic should be delivered to ZIA for inspection.

And finally, using the Zscaler Private Access (ZPA) option, traffic matching the criteria defined will be forwarded to the Zscaler Private Access cloud. The Cloud Connector automatically downloads ZPA Application Segments from your ZPA portal. Hence, any traffic it receives that is destined to these segments will be proxied, assuming it is permitted within the ZPA Access Policy and Client Forwarding Policy. Similar to ZIA, for ZPA customers, a default rule will be added automatically to ensure ZPA-bound traffic is automatically forwarded to the ZPA Broker.

Whether using DNS Policies, Log and Control, or Traffic Forwarding, each of the three options permits the administrator to define a range of match criteria. This demonstration will focus on DNS Policies and Traffic Forwarding Policies, but the workflow remains roughly the same regardless of the policy chosen.

The forwarding policy is located in the Policy Management section of the Cloud Connector portal, under the Forwarding menu. Rule creation and assessment models ZIA and ZPA workflows. More specific rules should be ordered near the top, while more broad rules ordered towards the bottom. For our first example, let’s assume that we’d like to send traffic destined for Office365 directly out of Azure, instead of inspecting with ZIA. Since this is data plane traffic, we’ll use the Traffic Forwarding Policy type with a Direct Forwarding Rule to accomplish this.

Click the Add Traffic Forwarding Rule button

Set the Rule Order appropriately, so as not to conflict with other rules, provide a name and set the Forwarding Method to Direct. In the Criteria section, notice the options available to define how traffic that should adhere to this rule is matched. Within the General tab, Locations identify the various VPCs or VNets from which your workloads send traffic. As Cloud Connector appliances are brought online, the VPC or VNet they are installed within will automatically populate this menu. Location Groups can be created to organize various cloud VPCs and VNets - such as a “Dev VPCs? Location Group, “Prod VPCs? Location Group, etc. If there are many locations and associated sub-locations within your organization, consider using Location Groups. Branch and Cloud Connector Groups allow you to match traffic transiting specific Cloud Connector appliances.

In the Services menu, you can choose the protocol type that defines the traffic via the Network Service menu. For traffic profiles with multiple protocol types, Network Service Groups can be created to group the traffic for easier policy creation.

Within the Source tab, source IP Addresses or groups of IP Addresses can be used to define the source of incoming traffic that should adhere to this rule. IP addresses can be written as an individual host, in CIDR notation, or as a range of IP addresses using a hyphen.

In the Destination tab, you can enter the IP addresses and/or fully qualified domain names (FQDNs) that this traffic is destined for. Similarly, you can group together Destination IP Addresses and FQDNs that you want to control in a Forwarding Policy rule by Destination IP/FQDN Groups. And finally, as the name suggests, Destination Country allows this match criteria to specify the destination country of the remote machine.

Please note that wildcard domain identifiers (“*?) are not currently supported and that Destination criteria is not supported when Zscaler Private Access is selected as the Forwarding Method.

For our example here, we’ll select our Azure Cloud Connector, then set a Destination FQDN of

sharepoint.com

and

sharepointonline.com

. Once we activate our change, we can be assured that Sharepoint traffic to these FQDNs will now egress Azure locally, instead of being inspected by ZIA.

Similarly, similar match criteria can be set up to direct traffic to ZIA and ZPA as well. Remember, ZIA and ZPA have default policies out of the box that will forward traffic to their respective service.

As mentioned previously, the Cloud Connector proxies traffic for ZPA using synthetic IP addressing. This pool, by default, is 10.254.0.0/19, as shown on your screen within the IP & FQDN Group menu. Let’s assume that we want to update this pool to use the 10.253.0.0/16 address space for our AWS clouds, so as not to overlap with existing workload subnets in those regions. Click the Add IP Pool button. Provide a name, and description, and enter the IP address range.

Ne

Cloud Connector

692 views

Ramesh Mani

likes this.

Log In to Answer

Associated Tags

video

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,724

1724 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,473

1473 Views

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

[VIDEO] Forwarding Policy
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjcCAA/video-administrator-rbac-management","lastmod":"2023-05-31T09:08:51.000Z","id":"0D54u00009evmjcCAA"} -->
## [VIDEO] Administrator / RBAC Management

- Source: https://community.zscaler.com/s/question/0D54u00009evmjcCAA/video-administrator-rbac-management
- Type: Q&A
- Last activity: 2023-05-31T09:08:51.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

Aaron_Rohyans

(Employee) to

sfdc

(Employee): asked a question.

August 10, 2022 at 2:55 PM

[VIDEO] Administrator / RBAC Management

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

Cloud Connector

444 views

Ramesh Mani

likes this.

Log In to Answer

Associated Tags

video

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,717

1717 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,470

1470 Views

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

[VIDEO] Administrator / RBAC Management
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjeCAA/video-scheduled-upgrades","lastmod":"2023-05-31T09:08:51.000Z","id":"0D54u00009evmjeCAA"} -->
## [VIDEO] Scheduled Upgrades

- Source: https://community.zscaler.com/s/question/0D54u00009evmjeCAA/video-scheduled-upgrades
- Type: Q&A
- Last activity: 2023-05-31T09:08:51.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

Aaron_Rohyans

(Employee) to

sfdc

(Employee): asked a question.

August 10, 2022 at 2:46 PM

[VIDEO] Scheduled Upgrades

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

Cloud Connector

571 views

David.Glading

likes this.

Log In to Answer

Associated Tags

video

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,726

1726 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,476

1476 Views

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

[VIDEO] Scheduled Upgrades
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjiCAA/video-azure-managed-app-deployment","lastmod":"2023-05-31T09:08:51.000Z","id":"0D54u00009evmjiCAA"} -->
## [VIDEO] Azure Managed App Deployment

- Source: https://community.zscaler.com/s/question/0D54u00009evmjiCAA/video-azure-managed-app-deployment
- Type: Q&A
- Last activity: 2023-05-31T09:08:51.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

Aaron_Rohyans

(Employee) to

sfdc

(Employee): asked a question.

August 10, 2022 at 2:38 PM

[VIDEO] Azure Managed App Deployment

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

Cloud Connector

561 views

Ramesh Mani

and

David.Glading

like this.

Log In to Answer

Associated Tags

azure

video

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,717

1717 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,470

1470 Views

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

[VIDEO] Azure Managed App Deployment
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjuCAA/zscaler-cloud-connector-throughput-and-horizontal-scaling","lastmod":"2023-07-06T11:43:51.000Z","id":"0D54u00009evmjuCAA"} -->
## ZScaler Cloud Connector - throughput and horizontal scaling

- Source: https://community.zscaler.com/s/question/0D54u00009evmjuCAA/zscaler-cloud-connector-throughput-and-horizontal-scaling
- Type: Q&A
- Last activity: 2023-07-06T11:43:51.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

amirka

(Customer) to

sfdc

(Employee): asked a question.

Edited by sfdc July 6, 2023 at 11:43 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Hello All,

I am looking through the Deployment Architecture for CC

here

and there are a couple of questions I am struggling to answer - so looking at a wider community for some assistance

CloudConnector’s performance is limited to 400Mb, as mentioned by ZScaler: does it mean 400Gbps in total or only for inspected traffic. Will bypassed traffic also bound by this 400Mbps ceiling?

Is there a ZScaler-approved (=reference architecture) way to scale solution beyond that number (auto-scaling with GLB, etc)? I could not find any - document mentions that one can ‘scale with load-balancing’ without detailing it. Does CC support GENEVE protocol to be used with GWLB? If not, what is the exact supported scheme for horizontal scaling?

All in all, I am concerned about CC being a bottleneck for non-inspected traffic (and inspected also) in a large multi-account/multi-VPC deployment with a requirement to provide egress inspection for all internet-bound web-traffic AND with a requirement to bypass certain traffic classes from inspection.

Thank you!

Cloud Connector

2 answers

1.73K views

Top Rated Answers

wolson

(Employee)

4 years ago

Hello! Cloud Connector can take advantage of GWLB for scalability to meet your specific throughput needs. Please see the following Help article documenting several automation scripts within AWS/Azure/etc using cloud specific methods(AWS CloudFormation) or Terraform.

help.zscaler.com

About Cloud Automation Scripts | Zscaler

Information about the on Cloud Automation Scripts page within the Zscaler Cloud Connector Portal.

Selected as Best

All Answers

amirka

(Customer)

4 years ago

Thanks a lot Warren - I missed that scripting page!

wolson

(Employee)

4 years ago

Hello! Cloud Connector can take advantage of GWLB for scalability to meet your specific throughput needs. Please see the following Help article documenting several automation scripts within AWS/Azure/etc using cloud specific methods(AWS CloudFormation) or Terraform.

help.zscaler.com

About Cloud Automation Scripts | Zscaler

Information about the on Cloud Automation Scripts page within the Zscaler Cloud Connector Portal.

Selected as Best

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,717

1717 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,470

1470 Views

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

ZScaler Cloud Connector - throughput and horizontal scaling
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjvCAA/reference-architecture-documentation","lastmod":"2023-05-31T09:08:51.000Z","id":"0D54u00009evmjvCAA"} -->
## Reference Architecture Documentation

- Source: https://community.zscaler.com/s/question/0D54u00009evmjvCAA/reference-architecture-documentation
- Type: Q&A
- Last activity: 2023-05-31T09:08:51.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

Aaron_Rohyans

(Employee) to

sfdc

(Employee): asked a question.

August 8, 2022 at 7:00 AM

Reference Architecture Documentation

If you’re keen to get much more technical and “in the weeds? on not just Cloud Connector but all the CSP (Azure and AWS) resources, terminology, and dependencies that Cloud Connector leverages, check out our Reference Architectures below. They provide a 30,000 foot view of public cloud concepts and how they relate to Zscaler Cloud Connector.

AWS Reference Architecture:

Zscaler Cloud Connector - AWS Reference Architecture - Google Docs

Azure Reference Architecture:

Zscaler Cloud Connector - Azure Reference Architecture - Google Docs

Cloud Connector

549 views

ozanogur

wguilherme

, and

4 others

like this.

Log In to Answer

Associated Tags

azure

aws

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,727

1727 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,478

1478 Views

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

Reference Architecture Documentation
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmjxCAA/what-is-cloud-connector","lastmod":"2023-05-31T09:08:51.000Z","id":"0D54u00009evmjxCAA"} -->
## What is Cloud Connector?

- Source: https://community.zscaler.com/s/question/0D54u00009evmjxCAA/what-is-cloud-connector
- Type: Q&A
- Last activity: 2023-05-31T09:08:51.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

Aaron_Rohyans

(Employee) to

sfdc

(Employee): asked a question.

August 7, 2022 at 4:00 AM

What is Cloud Connector?

Cloud Infrastructure Protection using Cloud Connector

Enabled by the Zero Trust Exchange, Workload Communications is deployed as Cloud Connector virtual machines and extends the capabilities of Zscaler Internet Access (ZIA) and Zscaler Private Access (ZPA) to cloud-native workloads. This allows enterprises to secure cloud workload communications over any network. The Zscaler Zero Trust exchange will enable workloads to communicate with each other and have a granular security policy applied. The communication may be from private workloads (IaaS/Physical DC) to public workloads (SaaS/Internet), or between private workloads (IaaS to IaaS, or Physical DC to IaaS). Generally, where a private workload is communicating to a public workload through the ZTE, this will use the Zscaler Internet Access platform. Where a private workload is communicating with another private workload through the ZTE, this will use the Zscaler Private Access platform.

Key Features and Benefits

Security - Secures all inbound and outbound traffic to the internet. The security capabilities that are available through the

Zscaler Internet Access

platform for server internet access are Secure Sockets Layer (SSL), Intrusion Prevention System (IPS), Firewall, Data Loss Prevention (DLP), etc.

Connectivity - Provides seamless connectivity from Private/Public cloud applications to the internet.

Performance - Ensures better end-user experience and application performance by peering into relationships with SaaS providers (e.g., Microsoft Office 365, Amazon Web Services, and Microsoft Azure).

Reduces Cost - Consolidates multiple products (e.g., Squid proxies, firewalls, 3rd party NAT appliances, URL filtering, etc.) into a single solution. Additionally, the same policy applied to user traffic can be applied across the cloud infrastructure.

Highly Scalable - Ease of implementation across 1K service accounts in public clouds and single solution scales to connect 10K+ server environments in public clouds (e.g., AWS, Azure, etc.).

Ease of Deployment - Fully orchestrated deployment for AWS using Terraform and CloudFormation templates.

Real-Time Visibility - Dashboards and Insights provide unparalleled visibility into your users and applications and the health of your organization’s applications and servers.

Oftentimes, in the realm of networking, we find ourselves asking how we can secure traffic to the cloud. Rarely do we evaluate how traffic is secured within the cloud, as it leaves the cloud, or while in transit between clouds. This attack surface is growing as more and more workloads are being migrated and customers begin operating across multiple cloud service providers. Zscaler Workload Communications can help enable the following use-cases:

Applications to Internet Communications: Applications may need to access any Internet or SaaS destination, such as 3rd party APIs, software updates, etc. with a scalable, reliable security solution that inspects all transactions, applying advanced threat prevention and data loss protection controls.

Application to Application Communication to other public clouds and corporate data centers for multi/hybrid cloud connectivity, delivered with better security and a dramatically simplified operational model as compared with traditional solutions like proxies, virtual firewalls and IDS/IPS.

Application to Application Communications within a VPC by securing process-to-process communications to achieve micro-segmentation with no changes to the application or the network.

Cloud Connector

Cloud-infrastructure-protection-using-cloud-connector-1

Key-features-and-benefits-2

1 answer

1.77K views

wguilherme

zoltan

, and

User1579966403800120789

like this.

Ben_Garrison

(Employee)

4 years ago

Thank you

@Aaron_Rohyans

for all these wonderful resources. Such a treasure trove of amazingness!

1 like

Log In to Answer

Associated Tags

azure

aws

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,719

1719 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,473

1473 Views

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

What is Cloud Connector?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmoDCAQ/forwarding-profile-in-sdwan-visibility-or-authentication","lastmod":"2023-05-31T08:46:16.000Z","id":"0D54u00009evmoDCAQ"} -->
## Forwarding Profile in SDWAN : visibility or authentication?

- Source: https://community.zscaler.com/s/question/0D54u00009evmoDCAQ/forwarding-profile-in-sdwan-visibility-or-authentication
- Type: Q&A
- Last activity: 2023-05-31T08:46:16.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

GRoux

(Customer) to

sfdc

(Employee): asked a question.

January 31, 2022 at 4:45 PM

Forwarding Profile in SDWAN : visibility or authentication?

Hello ZSC users and members;

I come here today to seek advice as we are heading to implement SDWAN as our new WAN standard. And we face a decision that is not easy to make. Let me explain.

Today : traditionnal internet access

RW

, environnement is untrust, and we want full cybersecurity controll ==> ZCC running on all laptop + ZT2.0 to catch all internet trafic and manage it in our cloud.

For onsite users

, all standard users have ZCC on their computer (laptop and desktop), and we’re using Tunnel with local proxy forwarding mode and hosted pac file. But there is also some servers and shared station that doesn’t. So we setup IPSec tunnel to catch all internet traffic.

This work quite fine.

Tomorrow : sdwan

RW

: no change

onsite

: here comes the big question.

Should we deactivate our ZCC when ontrusted network to offer the sdwan device full visibility of the url in order to allow classification, and app recognition (we keep the IPSec “catch all? tunnel from branch to Zscaler ) ? It seems at first try to also create auth issue, as the ZCC doesn’t provide the user ID.

Should we keep tunnel mode + IPSec for better security and accept that for the sdwan device, all our internet access is seen as “flow to zscaler? ?

I cannot put my hand on some documentation covering this case. It’s either “here is how the ZCC works? or “zscaler integrates within sdwan thanks to API tunnel creation?.

Hope I’ve made myself clear enough (sorry french guy here)

Cheers

Client Connector

1 answer

656 views

GRoux

(Customer)

4 years ago

UP, anybody facing the same question ? or my issue is not well described ?

Log In to Answer

Associated Tags

trafficforwarding

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

Forwarding Profile in SDWAN : visibility or authentication?
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmofCAA/aws-server-internet-protection","lastmod":"2023-05-31T08:46:29.000Z","id":"0D54u00009evmofCAA"} -->
## AWS Server Internet Protection

- Source: https://community.zscaler.com/s/question/0D54u00009evmofCAA/aws-server-internet-protection
- Type: Q&A
- Last activity: 2023-05-31T08:46:29.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

mustafa.atamer

(Partner) to

sfdc

(Employee): asked a question.

January 14, 2022 at 8:43 AM

AWS Server Internet Protection

Hi All,

One of our customer wants to protect their AWS servers internet traffic.

option is IPSec tunnel, in this option is there any possibility for assymetric routing for incoming requests?

Can we use Client Connector for servers? If yes how can we authenticate them? Do we have user licenses for each servers?

Best regards.

Cloud Connector

1 answer

269 views

Jamie_Brown

(Employee)

5 years ago

Hi Mustafa,

Getting server traffic out of public clouds like AWS can be complex when you consider scale and multiple availability zones and/or regions. Have a look at the Cloud Connector which simplifies things greatly. Cloud Connector is providing a quasi-Client Connector function for all the servers. Note Client Connector is not supported on Windows server.

help.zscaler.com

Zscaler Cloud Connector Help

The official Zscaler Cloud Connector technical documentation and release notes within the Zscaler Help Portal. The Zscaler Cloud Connector documentation is also accessible via the Zscaler Cloud Connector Admin Portal.

1 like

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,726

1726 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,477

1477 Views

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

AWS Server Internet Protection
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evmsDCAQ/zscaler-cloud-connector-public-preview-availability","lastmod":"2023-07-06T11:45:36.000Z","id":"0D54u00009evmsDCAQ"} -->
## Zscaler Cloud Connector public preview availability

- Source: https://community.zscaler.com/s/question/0D54u00009evmsDCAQ/zscaler-cloud-connector-public-preview-availability
- Type: Q&A
- Last activity: 2023-07-06T11:45:36.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

lucaberta

(Partner) to

sfdc

(Employee): asked a question.

Edited by sfdc July 6, 2023 at 11:45 AM

Zscaler Cloud Connector public preview availability

Hi all,

the help page now includes

documentation for ZCC

, and I noticed this message too:

image

1024×648 36.5 KB

Can anyone share what this public preview is all about?

How does one access the public preview?

Many thanks,

Luca

Cloud Connector

Discourse-expand

Far-image

1 answer

428 views

manuel

likes this.

Trace Woodbury-RidgeIT

(Partner)

5 years ago

In general, I’m curious what “public preview means? Does that mean we need to request permission, does it mean everyone has access to them?

2 likes

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,727

1727 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,477

1477 Views

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

Zscaler Cloud Connector public preview availability
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009evn2JCAQ/zscaler-sdwan-integrations","lastmod":"2023-05-31T08:52:38.000Z","id":"0D54u00009evn2JCAQ"} -->
## ZScaler SD-WAN integrations

- Source: https://community.zscaler.com/s/question/0D54u00009evn2JCAQ/zscaler-sdwan-integrations
- Type: Q&A
- Last activity: 2023-05-31T08:52:38.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Client Connector

avshch

(Customer) to

sfdc

(Employee): asked a question.

May 25, 2019 at 6:13 PM

ZScaler SD-WAN integrations

Does ZScaler have any plans to integrate with

https://www.aryaka.com/

SD-WAN vendor?

Thanks,

Client Connector

1 answer

291 views

Lmay

(Customer)

7 years ago

Already does.

zscaler.com

partner-aryaka-deployment-guide.pdf

1852.41 KB

3 likes

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

2,890

2890 Views

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

ZScaler SD-WAN integrations
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykQ0CAI/awsazure-traffic-forwarding-options-to-cloud-connectors","lastmod":"2024-06-24T12:26:30.000Z","id":"0D54u00009eykQ0CAI"} -->
## AWS/Azure Traffic Forwarding Options to Cloud Connectors

- Source: https://community.zscaler.com/s/question/0D54u00009eykQ0CAI/awsazure-traffic-forwarding-options-to-cloud-connectors
- Type: Q&A
- Last activity: 2024-06-24T12:26:30.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

zoltan

(Employee) to

sfdc

(Employee): asked a question.

April 6, 2023 at 8:21 PM

AWS/Azure Traffic Forwarding Options to Cloud Connectors

Hello everyone, please find the attached solution guide describing the various ways to forward/route traffic from workloads to Cloud Connectors in an AWS and Azure environment. This guide covers:

ZIA forwarding with default routes for all traffic

ZIA forwarding with default routes for specific protocols

ZIA Explicit Proxy (PAC) Forwarding using Global Service Edges

ZPA Forwarding

Cloud Connector Workload Forwarding Options (Default Route & Explicit Proxy Configurations).pdf

(2.1 MB)

Cloud Connector

1 answer

648 views

dperkins

Ramesh Mani

, and

Ben_Garrison

like this.

ozanogur

(Partner)

2 years ago

@Zoltan Kovacs​  Looks like the file can not been downloaded, could you please reshare it.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,726

1726 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,473

1473 Views

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

AWS/Azure Traffic Forwarding Options to Cloud Connectors
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u00009eykRMCAY/onprem-server-to-aws-cc-zscaler","lastmod":"2023-06-02T17:13:16.000Z","id":"0D54u00009eykRMCAY"} -->
## Onprem server to AWS CC Zscaler

- Source: https://community.zscaler.com/s/question/0D54u00009eykRMCAY/onprem-server-to-aws-cc-zscaler
- Type: Q&A
- Last activity: 2023-06-02T17:13:16.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

mr.dek

(Customer) to

sfdc

(Employee): asked a question.

March 21, 2023 at 9:37 AM

Onprem server to AWS CC Zscaler

Hi, we have an onprem Server and we want to use our AWS CC Zscaler to connect to the internet. we use TGW to connect our onprem network to AWS. can you advise what are the requirements to do this setup?

Cloud Connector

1 answer

306 views

zoltan

(Employee)

3 years ago

Hi Dek,

Happy to provide a quick overview here but this would definitely be a topic to loop our team in for design review and deployment services. This topology technically works but might require some exploration to understand the use case (is it ZIA, ZPA, or both), if you already have connectivity from on prem to AWS TGW (is it DirectConnector or VPN Gateway), and/or if you have any edge devices at the physical location that can create IPSEC or GRE tunnels to ZIA (if ZIA only).

A combination of this information would be helpful for design. However, the short simple answer is that if you can configure a default route (or explicit proxy forwarding) from the on premise servers to go through your connection up to TGW, and the TGW attachment subnet is configured to route to the the Gateway Load Balancer endpoint(s) that is fronting the Cloud Connector(s) that will work.

There are known limitations with AWS VPN Gateways not being able to route traffic NOT destined to the AWS VPC from on premise location, but that should not be an issue if you are terminated in TGW.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,725

1725 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,473

1473 Views

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

Onprem server to AWS CC Zscaler
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A0eLLBCQ2/using-cloud-connector-to-replace-nat-gateway-and-reduce-cost-part-1","lastmod":"2023-10-09T08:47:26.000Z","id":"0D54u0000A0eLLBCQ2"} -->
## Using Cloud Connector to replace NAT Gateway (and reduce cost) (part 1)

- Source: https://community.zscaler.com/s/question/0D54u0000A0eLLBCQ2/using-cloud-connector-to-replace-nat-gateway-and-reduce-cost-part-1
- Type: Q&A
- Last activity: 2023-10-09T08:47:26.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

jhage

(Employee) asked a question.

Edited September 29, 2023 at 1:38 PM

Using Cloud Connector to replace NAT Gateway (and reduce cost) (part 1)

Using Cloud Connectors without NAT Gateway

The Zscaler Cloud Connector is a VM-based solution built to forward traffic from cloud-based workloads to public and/or private destinations using the Zscaler Cloud services. As such it needs to be able to initiate traffic to Zscaler Service Edges which requires public IP addresses (more detailed information on Cloud Connector communication can be found

in our help-portal

).

In general, Zscaler recommends setting up Cloud Connector with a NAT Gateway as it solves a number of required functions:

It assigns public IP addresses to all interfaces for outbound traffic

It prevents unsolicited inbound connections (from the Internet)

It allows for the use of private IP space within the Cloud, making for an easier local routing setup

Diagram: recommended Cloud Connector setup with NAT-GW

However, NAT Gateways can introduce significant additional costs, especially when combined with high data throughput. At the same time, Cloud Connectors are designed to be exposed to the Internet

and

only require outbound Internet access which makes them even less of a target. Moreover, since Cloud Connectors act as the default forwarding function this also means that NAT Gateway isn’t necessary for other non-public workloads either.

This article describes a Cloud Connector setup that replaces the NAT Gateway functionality where it makes sense, while still maintaining the same security considerations.

Diagram: alternative Cloud Connector setup without NAT-GW

Note that the Community site limits the article length (for now), so the next section can be found

here

Cloud Connector

1.02K views

GordonWright

and

Ramesh Mani

like this.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,719

1719 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,473

1473 Views

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

Using Cloud Connector to replace NAT Gateway (and reduce cost) (part 1)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A0eTJbCQM/using-cloud-connector-to-replace-nat-gateway-and-reduce-cost-part-2","lastmod":"2023-10-16T09:48:37.000Z","id":"0D54u0000A0eTJbCQM"} -->
## Using Cloud Connector to replace NAT Gateway (and reduce cost) (part 2)

- Source: https://community.zscaler.com/s/question/0D54u0000A0eTJbCQM/using-cloud-connector-to-replace-nat-gateway-and-reduce-cost-part-2
- Type: Q&A
- Last activity: 2023-10-16T09:48:37.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

jhage

(Employee) asked a question.

Edited September 29, 2023 at 1:37 PM

Using Cloud Connector to replace NAT Gateway (and reduce cost) (part 2)

Note: the previous section of this article can be found

here

Setting up public IP addresses to the Cloud Connector interfaces

The first thing to do is to actually assign public IP addresses to these interfaces. Note that (as the NAT Gateway already implied) this doesn’t have to be a fixed address, as long as it’s consistent during the Cloud Connector’s uptime.

In Azure

you link public IP addresses to the CloudConnector interfaces. First ensure there is no NAT Gateway associated with the subnet (or remove it when there is). Then go into the Cloud Connector VM, select the Network Interface, select IP Configuration and toggle the Public IP address settings to “Associate”. Do this for all interfaces.

In AWS

you need to place the Cloud Connector in a public subnet which will assign one public IP address to it, and assign Elastic IP addresses to all other interfaces.

When using Terraform this can be achieved by first creating an

aws_eip resource

, and then associate it to the Cloud Connector interface-ids through

aws_eip_association

When using CloudFormation you must assign a public subnet when creating the stack. This will automatically assign one Public IP address to the instance and, as such, to one of the Interfaces. Allocate an Elastic IP address and, once the Cloud Connector EC2 instance is created, associate it with another interface. Repeat until all Cloud Connector interfaces have a public IP association.

NB:

By default, AWS only allows a limited number of E-IPs per Region. For additional addresses the customer has to create a support ticket with AWS.

See

https://docs.aws.amazon.com/vpc/latest/userguide/amazon-vpc-limits.html

for more details

Since requesting additional E-IPs can be a cumbersome process

and

since the majority of cost for NAT-GW comes with the throughput used, it can interesting to not have E-IPs assigned to all interfaces, but to the service interfaces only and still use NAT Gateway for the management interface:

Diagram: alternative Cloud Connector setup with partial NAT-GW

The

next section of this article

describes the additional measures to protect against external attacks

Cloud Connector

619 views

ozanogur

GordonWright

, and

Ramesh Mani

like this.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,718

1718 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,470

1470 Views

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

Using Cloud Connector to replace NAT Gateway (and reduce cost) (part 2)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000A0eUfyCQE/using-cloud-connector-to-replace-nat-gateway-and-reduce-cost-part-3","lastmod":"2023-10-16T12:22:06.000Z","id":"0D54u0000A0eUfyCQE"} -->
## Using Cloud Connector to replace NAT Gateway (and reduce cost) (part 3)

- Source: https://community.zscaler.com/s/question/0D54u0000A0eUfyCQE/using-cloud-connector-to-replace-nat-gateway-and-reduce-cost-part-3
- Type: Q&A
- Last activity: 2023-10-16T12:22:06.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

jhage

(Employee) asked a question.

September 29, 2023 at 1:36 PM

Using Cloud Connector to replace NAT Gateway (and reduce cost) (part 3)

The previous part of this article can be found

here

Protecting against Internet-sourced attacks

Setting up Cloud Connectors without NAT Gateway requires that they are placed in a public subnet, which makes them addressable from the Internet. The attack-surface of a Cloud Connector itself is limited; it is hardened and only allows limited direct access. Still, the management interface allows inbound SSH access which

can

be a target for both compromise and denial-of-service and should be protected.

More fundamentally, the CC

service

interface must accept traffic coming from the internal Cloud workloads but should never accept unsolicited traffic from the Internet. However, if an attacker is able to mimic/spoof Workload traffic, CC will pick it up and process it as normal. This opens up attack-vectors towards ZIA and ZPA resources, which need to be mitigated.

Fortunately, some attacks are infeasible due to regular routing, and Azure and AWS have a few useful options that allow for a ruleset that doesn’t need continuous updating after adding new workloads:

Transparent access from the Internet through Cloud Connectors to ZIA or ZPA resources will be prevented by regular Internet routing (the traffic will never end up at the CC in the first place)

AWS and Azure have Anti-spoofing measures to block inbound traffic using cloud-local IP space

Azure has default labeling for local Cloud resources. This means you don’t have to change the Security Groups each time you add a new subnet

Unfortunately, although AWS and Azure do provide protection against spoofing (Cloud-) local addresses, (obviously) it can’t protect against spoofed Internet address space. And since the CC service interface must respond to DNS requests, it could be used as a target by itself

and

as facilitator to (D)DoS public and private services (and, incidentally, it could also lead to Zscaler counting these spoofed addresses towards the ZIA and ZPA Workload licenses). Combined, this leads to the following attacks and mitigation measures:

So we need a number of Security Group rules to mitigate these risks by making sure that only local resources can use the CCs.

In Azure

this is straightforward (in fact: our ARM and Terraform provisioning scripts create the correct Security Group rules by using Azure defined network TAGs). For the management interface only sources on “VirtualNetwork” should be allowed access to listening services, like SSH. Of course, if you have a specific subnet to manage workloads from (containing management systems and/or jump hosts) then further limit SSH access only to those systems. In addition, the management interface needs public outbound access towards DNS (UDP/TCP 53),  (D)TLS (UDP/TCP 443) and NTP (UDP 123).

For the service interface this means only sources on “VirtualNetwork” are allowed full TCP/UDP access to ANY destination behind the Cloud Connector. Note that

if

you have additional networks connected (through Direct Access, virtual WAN or VPN) that also want to use Cloud Connector to protect their traffic going out, you’ll need to manually add policy rules for them as well.

In AWS

this configuration is slightly less convenient; you’ll have to define these ACLs using your local IP subnets manually. Again, the management interface should only allow inbound SSH from a management subnet or from specific bastion/jump-hosts. The management also needs public outbound access towards DNS (UDP/TCP 53),  (D)TLS (UDP/TCP 443) and NTP (UDP 123).

For the service interface this means only your locally defined subnets (and IP ranges from other connected networks, if they need to use Cloud Connector to protect their traffic going out) should be allowed full TCP/UDP access to ANY destination behind the Cloud Connector. Note that since AWS will protect against traffic with (spoofed) private (RFC1918) IP addresses, allowing inbound connections only from RFC1918 sources will protect against attacks from external sources.

Cloud Connector

4 answers

581 views

ozanogur

GordonWright

, and

2 others

like this.

ozanogur

(Partner)

3 years ago

Do you have any ideas on how can we use Private Service Edges at the same setup with Cloud Connectors? Because we need to expose the 443 incoming for PSEs and what should we do to protect the DDoS and other security threats.

jhage

(Employee)

3 years ago

I'm not sure I understand the question. If you want to use the PSEs  publicly (ie: for your roaming  users in that region) then they need to be accessible (and therefore addressable) anyway.  If you host them for internal (non-public) access only then you can (but don't need to) limit access into them to whichever internal subnets you expect traffic from.

ozanogur

(Partner)

3 years ago

Correct, I want PSEs to be accessible publicly, and at the same time, I need to secure the PSEs against security threats.  Do I have to use a FW before the PSEs to secure them, is it the solution?

jhage

(Employee)

3 years ago

Not really; FWs won't be able to do much as they need to pass the traffic through anyway.

We have several deployment guides for Service Edges in our help, here's an

overview for running ZIA VSE

s, and here for

ZPA PSEs

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,726

1726 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,476

1476 Views

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

Using Cloud Connector to replace NAT Gateway (and reduce cost) (part 3)
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AA1Qb3CQF/azure-virtual-wan-route-configuration-for-cloud-connector-secure-egress","lastmod":"2024-01-25T20:06:12.000Z","id":"0D54u0000AA1Qb3CQF"} -->
## Azure Virtual WAN route configuration for Cloud Connector secure egress

- Source: https://community.zscaler.com/s/question/0D54u0000AA1Qb3CQF/azure-virtual-wan-route-configuration-for-cloud-connector-secure-egress
- Type: Q&A
- Last activity: 2024-01-25T20:06:12.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

dperkins

(Employee) asked a question.

Edited January 25, 2024 at 8:06 PM

Azure Virtual WAN route configuration for Cloud Connector secure egress

UPDATED DOCUMENT HERE

This article aims to provide an overview of how the Workloads team at Zscaler typically configures Azure VHub route tables to support a hub-and-spoke VNet model using Virtual WAN. More information on this Azure network topology can be found

here

Configuration Overview

Create a Virtual WAN and then a associated Virtual WAN Hub

Create two empty route tables on the VHub configuration screen named: SpokesToZscaler, ZscalerToSpokes

Attach all relevant VNets to the Virtual WAN. Use the following configuration options:

For a spoke VNet, associate the SpokesToZscaler route table and propagate routes to the ZscalerToSpokes route table

For the Zscaler Cloud Connector VNet, associate the ZscalerToSpokes route table and propagate routes to the SpokesToZscaler route table

Note: If you wish to enable traffic routing from spoke VNet 1 to spoke VNet 2, for example, ensure that in the configuration for spoke VNet 2 the route table SpokesToZscaler is selected for route propagation which will enable the spoke VNet(s) to route to spoke VNet 2. This design can be applied to all spoke VNets if you wish for full mesh connectivity.

Edit the SpokesToZscaler route table adding this static route:

Name: Internet via Zscaler

Destination type: CIDR

Destination prefix: 0.0.0.0/0

Next hop: Select the Zscaler Cloud Connector VNet to VWAN connection name

Select Configure and use these settings:

Next hop IP: Enter the Zscaler Cloud Connector load balancer frontend IP

Confirm and Create to finalize the route table configuration

Note: Routes will be propagated to connected VNets and their subnet route tables automatically. If your environment requires additional routing configuration, make sure to check a subnet's or a VM's effective routes before creating any additional static routes.

Step 4:

Step 4e:

Here is the resulting SpokesToZscaler effective route table:

Here is an example resulting effective route table for a VM that resides in a spoke VNet:

Caveats and other notes

The VNet subnet where the Zscaler Cloud Connectors reside should have an assigned NAT gateway and no other network services that would prevent this NAT gateway from being the 0.0.0.0/0 default route.

Some VWAN services are not compatible with user defined VHub route tables. Refer to the VWAN documentation for more information (

example 1

).

Services and components shown in the above example:

VHub private address space: 10.0.0.0/24

Spoke1 AKA VNet1: 10.1.0.0/24

Spoke2 AKA VNet2: 10.2.0.0/24

Spoke3 AKA VNet3: 10.3.0.0/24

Zscaler AKA VNet4: 10.4.0.0/24

Cloud Connector LB frontend IP: 10.4.3.4

Cloud Connector

2 answers

1.78K views

cloudseccrawler

(Partner)

3 years ago

Hi

dperkins

, If we are using private routing intent policy to route the traffic via NVA/Azure firewall and still want to use Zscaler cloud connector using Hub and spoke topology ( with Azure vWAN) to control internet traffic via ZTE,  would that be possible

dperkins

(Employee)

3 years ago

Hi

@cloudseccrawler

(Partner)

​ . No, as it stands today, once Routing Intent is enabled a lot of options for VWAN Hub routing for Internet egress are disabled. It may be possible to use two Hubs to achieve what you describe -- one hub for VNET to VNET via Routing Intent and one hub for Internet egress via ZScaler.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,727

1727 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,478

1478 Views

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

Azure Virtual WAN route configuration for Cloud Connector secure egress
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AJWSGoCQP/frequent-session-disconnections-with-sap-servers-after-sdwan-implementation","lastmod":"2024-03-08T17:23:03.000Z","id":"0D54u0000AJWSGoCQP"} -->
## Frequent Session Disconnections with SAP Servers after SDWAN Implementation

- Source: https://community.zscaler.com/s/question/0D54u0000AJWSGoCQP/frequent-session-disconnections-with-sap-servers-after-sdwan-implementation
- Type: Q&A
- Last activity: 2024-03-08T17:23:03.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

ZPA - Forwarding

VaishnaviSawant

(Customer) asked a question.

February 18, 2024 at 6:36 PM

Frequent Session Disconnections with SAP Servers after SDWAN Implementation

Hi All,

In my organization there has been implementations of SDWAN, after which some branch locations where it was implemented started facing internet slowness and frequent disconnections issues.

They were not able to use SAP application with private access on in office network but they use to access it properly while turning it off.

There were no error logs found over ZPA, what can be the issue?

ZPA - Forwarding

1 answer

897 views

hyminameisderek

(Customer)

2 years ago

A lot of this is dependent on the SDWAN Vendor that is being implemented and how it was deployed.

If your org is creating IPSEC or GRE Tunnels from the SDWAN appliances to ZIA AND you are using the Client Connector ZIA in Tunnel 2.0 mode, you will have performance issues unless you send this traffic direct out the internet or you force the client connector to run in Tunnel 1.0. Tunneling 2.0 traffic in an IPSEC or GRE Tunnel adds overhead and performance issue, it is zscalers recommendation to run in Tunnel 1.0 in these scenarios.

A benefit of SDWAN for private apps is to auto create tunnels to your DC for Private App Traffic, if you are using ZPA for this then you are essentially bypassing the SDWAN tunnel and this could be your issue. This is assuming the SAP application is in a remote location that the tunnel would be used for.

Not knowing the SDWAN Vendor or your environment, I can't say what problem you are having for sure but I would start with reviewing the architecture to see if it makes sense to have ZPA enabled on what sounds like a Trusted Network.

Log In to Answer

Associated Tags

accesspolicy

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

959

959 Views

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

Frequent Session Disconnections with SAP Servers after SDWAN Implementation
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AKiw25CQB/cloud-connector-vs-cloud-connector-application","lastmod":"2024-07-18T16:42:07.000Z","id":"0D54u0000AKiw25CQB"} -->
## Cloud Connector vs Cloud Connector Application

- Source: https://community.zscaler.com/s/question/0D54u0000AKiw25CQB/cloud-connector-vs-cloud-connector-application
- Type: Q&A
- Last activity: 2024-07-18T16:42:07.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

SriNarasimha

(Customer) asked a question.

February 24, 2024 at 5:18 AM

Cloud Connector vs Cloud Connector Application

Hi Experts,

We're planning to install Cloud connectors in Azure and in market place observed that there are two options relating to this: Zscaler Cloud Connector and Zscaler Cloud Connector Application.

Just trying to figure out which one to install and what is the difference of the two?

Thank you.

Cloud Connector

1 answer

867 views

Top Rated Answers

Ben_Garrison

(Employee)

2 years ago

Hey thanks for reaching out.. Here is what I can kind searching some of our documentation. Let me know if this helps.

Documented Explanations:

The Zscaler Cloud Connector is a tool that works by establishing zero trust access for server-to-server traffic, east-west traffic in a public cloud, IT/OT networks, and data centers. It simplifies traffic forwarding to the Zscaler service by securing server access to the internet and providing highly secure and simplified access to the Zscaler cloud.

On the other hand, the Zscaler Cloud Connector Application in Azure is a specific implementation of the Zscaler Cloud Connector within the Azure platform. It is added to Azure to enable the functionalities of Zscaler Cloud Connector within the Azure environment. This includes securing all inbound and outbound traffic to the internet, deploying SSL inspection, Intrusion Prevention System, Firewall, and Data Loss Prevention, and providing seamless connectivity from public cloud applications to the internet.

Selected as Best

All Answers

Ben_Garrison

(Employee)

2 years ago

Hey thanks for reaching out.. Here is what I can kind searching some of our documentation. Let me know if this helps.

Documented Explanations:

The Zscaler Cloud Connector is a tool that works by establishing zero trust access for server-to-server traffic, east-west traffic in a public cloud, IT/OT networks, and data centers. It simplifies traffic forwarding to the Zscaler service by securing server access to the internet and providing highly secure and simplified access to the Zscaler cloud.

On the other hand, the Zscaler Cloud Connector Application in Azure is a specific implementation of the Zscaler Cloud Connector within the Azure platform. It is added to Azure to enable the functionalities of Zscaler Cloud Connector within the Azure environment. This includes securing all inbound and outbound traffic to the internet, deploying SSL inspection, Intrusion Prevention System, Firewall, and Data Loss Prevention, and providing seamless connectivity from public cloud applications to the internet.

Selected as Best

Log In to Answer

Associated Tags

azure

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,716

1716 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,467

1467 Views

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

Cloud Connector vs Cloud Connector Application
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AQRZiiCQH/how-to-connect-gihub-hosted-runner-to-private-vpc-in-the-cloud-to-be-able-to-access-private-vm-or-k8s-cluster","lastmod":"2024-04-15T17:57:40.000Z","id":"0D54u0000AQRZiiCQH"} -->
## How to connect gihub hosted runner to private vpc in the cloud to be able to access private VM or k8s cluster

- Source: https://community.zscaler.com/s/question/0D54u0000AQRZiiCQH/how-to-connect-gihub-hosted-runner-to-private-vpc-in-the-cloud-to-be-able-to-access-private-vm-or-k8s-cluster
- Type: Q&A
- Last activity: 2024-04-15T17:57:40.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

kbelanger

(Customer) asked a question.

April 12, 2024 at 11:43 AM

How to connect gihub hosted runner to private vpc in the cloud to be able to access private VM or k8s cluster

We already have ZScaler (ZIA, ZPA) and we would like to use ZScaler to connect to a private k8s cluster, db or vm in a VPC from a hosted github runner. Is that something we can do? After lot of readings, it not seems to be something possible easily.

Regards

Cloud Connector

2 answers

426 views

manuel

(Customer)

2 years ago

Just to understand it correctly:

1) k8s Cluster is located in a Google/AWS/Azure/etc VPC?

2) "hosted" means somewhere in public internet?

Without further infos this sounds like a classic use case for the Cloud/Branch Connector.

kbelanger

(Customer)

2 years ago

Hi thank you for your response.

1) yes, k8s cluster is in a cloud provider private VPC

2) yes, hosted means somewhere in the public internet

So we want, from those Github runner, to deploy k8s resources on a cluster in a VPC.

Log In to Answer

Associated Tags

automation

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,726

1726 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,473

1473 Views

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

How to connect gihub hosted runner to private vpc in the cloud to be able to access private VM or k8s cluster
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AWTIjECQX/users-not-authenticatin","lastmod":"2024-07-07T15:21:14.000Z","id":"0D54u0000AWTIjECQX"} -->
## Users not authenticatin

- Source: https://community.zscaler.com/s/question/0D54u0000AWTIjECQX/users-not-authenticatin
- Type: Q&A
- Last activity: 2024-07-07T15:21:14.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

marlow096

(Customer) asked a question.

June 27, 2024 at 3:47 PM

Users not authenticatin

We have been using zscaller for a while, the users use to have a authentication window pop up when they browse the net, a while ago that stopped working and all the traffic is now logged under a single generic user with the company name. Does anyone have an idea how to get the authenticating window up again?

Cloud Connector

3 answers

439 views

G-Man8

(Customer)

2 years ago

Hi Marlow,

I'm guessing here but possible that someone disabled

IP surrogate

marlow096

(Customer)

2 years ago

thanks, i had a look and this is still enabled, if I install the client app, something which we never had to do, the logging works fine, but the landing page on blocked sites also doesn't seem to work

Ramesh Mani

(Partner)

2 years ago

Still the issue persists ? Great if you could share the screenshots.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,715

1715 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,466

1466 Views

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

Users not authenticatin
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AXTKKUCQ5/cloud-connector-installation-azure","lastmod":"2025-05-28T16:29:41.000Z","id":"0D54u0000AXTKKUCQ5"} -->
## Cloud Connector Installation- Azure

- Source: https://community.zscaler.com/s/question/0D54u0000AXTKKUCQ5/cloud-connector-installation-azure
- Type: Q&A
- Last activity: 2025-05-28T16:29:41.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

SriNarasimha

(Customer) asked a question.

Edited July 17, 2024 at 9:17 AM

Cloud Connector Installation- Azure

Hi There,

We're planning to install Cloud connector in Azure and while going through the below link it seems that Azure key vault needs to be allowed from All public networks. If my understanding is correct, Zscaler Cloud connector VM will try to reach out to key vault for creds and try ti connect with the Cloud connector portal for registration via management interface which is a private IP.

So, in this case, we can restrict the Azure key vault networking settings specific to this Vnet/Internal IP range and UDR of the management subnet can be configured to reach the Key vault via service tags (MS backbone network).

Can someone please advise why "Allow access from: Select All networks' (Public) should be selected as per Zscaler recommendation?

Thank you.

Deploying Zscaler Cloud Connector with Microsoft Azure | Zscaler

Cloud Connector

2 answers

717 views

iamolivasmith

(Customer)

2 years ago

Hi,

You're correct in your understanding that the Zscaler Cloud Connector VM needs to reach the Azure Key Vault for credentials and connect to the Cloud Connector portal for registration via the management interface, which uses a private IP.

But Zscaler recommends "Allow access from: Select All networks (Public)" to ensure broader accessibility during the initial setup and to avoid connectivity issues that might arise from more restrictive settings.

If security is a concern, you can restrict access to the Azure Key Vault by configuring the Azure Key Vault networking settings to allow access from the specific VNet or internal IP range.

Also you can try by setting up User-Defined Routes (UDR) on the management subnet to route traffic to the Key Vault via service tags (MS backbone network).

Check question number 16th in this

Azure interview questions

resource and also check this

deploying zscaler cloud connector Microsoft azure

Thanks

1 like

jlw52761

(Customer)

a year ago

Why couldn't one use a Private Endpoint Link from the KeyVault?

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,718

1718 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,470

1470 Views

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

Cloud Connector Installation- Azure
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000AZH4OmCQL/branch-connector-dns-record-registration","lastmod":"2024-07-30T18:13:34.000Z","id":"0D54u0000AZH4OmCQL"} -->
## Branch Connector DNS record registration

- Source: https://community.zscaler.com/s/question/0D54u0000AZH4OmCQL/branch-connector-dns-record-registration
- Type: Q&A
- Last activity: 2024-07-30T18:13:34.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

john.parry

(Customer) asked a question.

July 30, 2024 at 10:38 AM

Branch Connector DNS record registration

Hi, we've just deployed a Virtual Branch Connector as part of the Proof of Value Project.

Azure has App Connector and Cloud Connector deployed.

One domain controller in Azure has been promoted and is running as a DC for the new test domain.

The app connector in Azure is pointing to Azure DC for DNS.

The branch site has a new Branch Connector with an integrated App Connector deployed.

App Connector in Branch is pointing to DC in Azure for DNS.

I am trying to deploy a new server in the branch and join it to the domain.

The Domain Join is working; however, when I look at the DC in Azure, no DNS A record is being created.

I want to promote the server in the branch to Domain Controller, but without performing DNS registration, I think this will never work.

Is there a policy change I must update to allow DNS registration to work?

In ZPA I have the wildcard policy set with *.domain.com.

Let me know if I missed any further information.

Cloud Connector

2 answers

393 views

Naman

(Employee)

2 years ago

To address the issue of DNS A records not being created on the Azure DC when deploying a new server in the branch:

1.

Verify DNS Update Credentials

: Ensure the branch server has the necessary permissions to update DNS records. The computer account or the user account performing the join should have the necessary permissions on the DNS server.

2.

Configure Dynamic Updates

: Check that the DNS zone allows dynamic updates. In the DNS Manager, right-click the zone, select Properties, and ensure that "Dynamic updates" is set to "Secure only."

3.

Check Network Configuration

: Verify that the new branch server can communicate with the Azure DC and that DNS queries and updates are allowed through the network and firewall.

4.

DNS Client Settings

: Ensure the branch server's DNS client settings point to the Azure DC for DNS resolution.

5.

Review Group Policies

: Ensure there are no Group Policies restricting DNS updates. Specifically, check settings under Computer Configuration > Policies > Administrative Templates > Network > DNS Client.

6.

ZPA Configuration

: Ensure the ZPA policy allows for DNS updates. You may need to check if additional configurations are required for DNS registration to work properly with your ZPA setup.

7.

Event Logs

: Review the event logs on the branch server and the Azure DC for any errors related to DNS updates.

By ensuring these configurations are correct, the branch server should be able to register its DNS A record with the Azure DC.

1 like

john.parry

(Customer)

2 years ago

Verify DNS Update Credentials

I am using Domain Admin user to join Server to Domain. This account has no issues with Domain joins or DNS registration when I do this in the  Azure Vnet.

Configure Dynamic Updates

Dynamic updates are enabled.

ZPA Configuration

I am unsure of this bit. Is there a specific DNS setup that allows DNS registrations over ZPA?

Event Logs

On the Azure server, I am getting event 5504:  The DNS server encountered an invalid domain name in a packet from 10.x.x.x. The packet will be rejected.

I think the above is a red herring. when I do a wireshark capture on the Azure server, I am not seeing any DNS dynamic updates coming in to the server.

Log In to Answer

Associated Tags

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,718

1718 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,470

1470 Views

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

Branch Connector DNS record registration
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Ab5y20CQA/cloud-connector-inactive-status","lastmod":"2024-09-04T08:37:04.000Z","id":"0D54u0000Ab5y20CQA"} -->
## Cloud Connector - Inactive status

- Source: https://community.zscaler.com/s/question/0D54u0000Ab5y20CQA/cloud-connector-inactive-status
- Type: Q&A
- Last activity: 2024-09-04T08:37:04.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

SriNarasimha

(Customer) asked a question.

Edited September 3, 2024 at 2:45 PM

Cloud Connector - Inactive status

Hi There,

We've deployed Cloud connectors which in Azure and in the cloud connector portal it's showing as 'Inactive status'. Everything seems to be in place as per the Zscaler's recommendation (

Troubleshooting Cloud Connector with Microsoft Azure | Zscaler

While checking the bootup logs, noticed below message.

{nimbus.system.azure.update_system_config} Retrying in 60 seconds

In the runtime logs, noticed below message.

{janus.init.runtime.wait_systemconfig_load} System Config not yet initialized, waiting

Can you please advise on the same. Thank you.

Cloud Connector

3 answers

949 views

Ramesh Mani

(Partner)

2 years ago

Can you try these steps to troubleshoot:

Check the registration and policy fetch: Run the sudo januscli status command to verify the registration and policy fetch.

Check the Azure Management Console: Confirm that the Cloud Connector instance is running.

Check the network: Verify the route tables, NAT gateway, and DNS.

Check the network security group: Make sure the network security group is not blocking the Cloud Connector TCP/UDP 443 traffic.

Check the Azure KeyVault: Ensure that the Azure KeyVault has the correct secret credentials.

Check the Cloud & Branch Connector Admin Portal: Confirm that you can reach the portal from your VNet/subnet.

SriNarasimha

(Customer)

2 years ago

Hi Ramesh,

As mentioned in the initial post, I've checked and everything is in place.

Unable to infer from the log messages pasted above.

Ramesh Mani

(Partner)

2 years ago

Ok, I think we would need a deeper investigation with Support team. Please reach out support.

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,725

1725 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,473

1473 Views

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

Cloud Connector - Inactive status
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D54u0000Acbm3lCQA/dns-updates-on-branch-connector","lastmod":"2024-10-07T05:57:36.000Z","id":"0D54u0000Acbm3lCQA"} -->
## DNS Updates on Branch Connector

- Source: https://community.zscaler.com/s/question/0D54u0000Acbm3lCQA/dns-updates-on-branch-connector
- Type: Q&A
- Last activity: 2024-10-07T05:57:36.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

AndrewAG

(Customer) asked a question.

October 1, 2024 at 7:20 PM

DNS Updates on Branch Connector

We are running into a problem having clients register dynamic DNS updates with AD integrated DNS. Through logs and packet captures, we verify that the DNS dynamic update packet makes it from the branch location to the DNS server. When the zone is set to only accept dynamic updates from secure sources, the DNS server refuses the updates. It works when the zone is set to accept nonsecure sources, but that is not a viable solution.

I have seen some posts about adding DHCP servers to the DNSUpdateProxy group, but that seems to be for domain machines. Is there a way to add our Zscaler app connectors to this group?

Cloud Connector

418 views

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,728

1728 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,478

1478 Views

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

DNS Updates on Branch Connector
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000RCzIZ0A1/isp-link-monitoring","lastmod":"2025-01-22T09:27:21.000Z","id":"0D5PJ00000RCzIZ0A1"} -->
## ISP Link Monitoring

- Source: https://community.zscaler.com/s/question/0D5PJ00000RCzIZ0A1/isp-link-monitoring
- Type: Q&A
- Last activity: 2025-01-22T09:27:21.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

Sharkey

(Customer) asked a question.

January 20, 2025 at 10:17 AM

ISP Link Monitoring

Hi. We are deploying ZT600 Branch connectors globally and terminating dual ISP links on the hardware. We used to have the ability to monitor link status and degradation however the hardware does not seem to be able to provide monitoring and reporting/alerting for ISP links. Is this a roadmap feature and/or how are other customers monitoring link quality for ISP links terminated on Zscaler hardware ? Is there a recommended solution ?

Cloud Connector

1 answer

303 views

avshch

likes this.

Ramesh Mani

(Partner)

a year ago

Definitely this feature need to have.

Connect with your Zscaler TAM, CSM or Account team who can help you to register the ER on Zscaler internal repo, so that the product team will prioritize the request.

Log In to Answer

Associated Tags

automation

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,718

1718 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,470

1470 Views

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

ISP Link Monitoring
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000RKi3x0AD/cloud-connector","lastmod":"2025-01-23T11:06:17.000Z","id":"0D5PJ00000RKi3x0AD"} -->
## cloud connector

- Source: https://community.zscaler.com/s/question/0D5PJ00000RKi3x0AD/cloud-connector
- Type: Q&A
- Last activity: 2025-01-23T11:06:17.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

Psairam

(Customer) asked a question.

January 22, 2025 at 7:52 AM

cloud connector

Hi team,

We are currently in the process of deploying the Cloud Connector. As part of this deployment, it is necessary to create the service account?

Could you please provide information on the role of the service account in Cloud Connector deployment?

Regards.

Sairam

Cloud Connector

1 answer

356 views

Ramesh Mani

(Partner)

a year ago

You shall follow the below,

https://help.zscaler.com/cloud-branch-connector/deploying-zscaler-cloud-connector-microsoft-azure

1 like

Log In to Answer

Associated Tags

aws

automation

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,718

1718 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,470

1470 Views

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

cloud connector
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000TskXA0AZ/cannot-connect-to-mapped-drive-with-zscaler-turned-on","lastmod":"2025-05-07T13:55:38.000Z","id":"0D5PJ00000TskXA0AZ"} -->
## Cannot Connect to Mapped drive with ZScaler turned on

- Source: https://community.zscaler.com/s/question/0D5PJ00000TskXA0AZ/cannot-connect-to-mapped-drive-with-zscaler-turned-on
- Type: Q&A
- Last activity: 2025-05-07T13:55:38.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

TH01

(Partner) asked a question.

March 6, 2025 at 2:55 PM

Cannot Connect to Mapped drive with ZScaler turned on

So this is a strange issue facing users at work. A few users have a mapped network drive on their laptop, the drive is a Synology NAS on the network (no Firewall to it at present). So when ZScaler Private Access is turned on and the user enters the credentials to log on to the mapped drive they get a message saying 'The specified network password is incorrect'.

With ZPA turned off it works. It seems like the username is recognised but not the password. Anyone else come across this or similar issue?

Cloud Connector

4 answers

1.09K views

Kris

(Customer)

a year ago

What authentication do you use ? Is it just username/password or is there Kerberos?

TH01

(Partner)

a year ago

It is just username and password. So the Synology NAS sits on a local network at site which is also accessible if the user another VPN such as Ivanti Secure Access Client. But when using ZScalar it comes up with password incorrect message. In the office users use ZScalar to access the corporate services and therefore cannot authenticate to the NAS. If it is turned off then it works. Wondering if there is any bugs or even something related to Synology if any one knows.

And just clarify, I myself don't manage the ZScalar ot have access to manage it. It is managed by another team of ours. They have checked and cannot see any issues.

Urs Schoeler

(Customer)

a year ago

Just a couple thoughts.

You established that direct access to the share is possible from the Clients. If you access it with Zscaler turned on you apparently are routed via Zscaler, which means the NAS is either defined as an Application Segment or you have a Wildcard Segment encompassing it (This would need to be checked, if there is a port not defined it could result in that error).

You can check that by doing a nslookup on the share while Zscaler is on and off, the IP Adress should change.

It is possible that it could  be a Network restriction  as well.

Problem is that the wrong Password Message is a very generic error for network shares unless the exact Logs are checked. Another Avenue would be checking which Account the Authentication to the NAS is tried with?

Nuno_Diogo

(Employee)

a year ago

+1 on the network restriction possibility.  Check that the NAS does not have any Source IP restrictions.  If it does, add the IP of the local AppConnectors (your ZPA Admins can help).

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,727

1727 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,478

1478 Views

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

Cannot Connect to Mapped drive with ZScaler turned on
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000YCWn30AH/cloud-connector-traffic-forwarding","lastmod":"2025-05-27T16:41:02.000Z","id":"0D5PJ00000YCWn30AH"} -->
## Cloud Connector- Traffic Forwarding

- Source: https://community.zscaler.com/s/question/0D5PJ00000YCWn30AH/cloud-connector-traffic-forwarding
- Type: Q&A
- Last activity: 2025-05-27T16:41:02.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

SriNarasimha

(Customer) asked a question.

May 23, 2025 at 6:31 AM

Cloud Connector- Traffic Forwarding

Hi Everyone,

We have already deployed Cloud Connectors (CC) in Azure across several regions, and at present, Multi-session AVD users are experiencing difficulties in resolving Wildcard FQDN URLs.

The suggested solution is to install the VDI agent and direct DNS requests through the Cloud Connectors by utilizing the DNS Gateway policy.

As I mentioned, given that we have multiple regions across continents provisioned with the CC, how can I configure the DNS servers for each location within the DNS gateway policy when I only see the primary and secondary DNS server IP addresses? The issue with this setup is that I want the DNS requests to be handled locally rather than routing all requests to a single DNS server.

Furthermore, since Azure employs the concept of Service tags and service endpoints, according to Zscaler's recommendation (link provided below), it is advised to exclude the Azure ranges from being forwarded to CC in the VDI forwarding profile. As these IP ranges are updated weekly by Microsoft, this necessitates manual updates to prevent service disruptions. Is there a workaround available to eliminate the need for manual intervention?

Configuring VDI Forwarding Profiles | Zscaler

Cloud Connector

2 answers

562 views

Gianluca.bastia

(Partner)

a year ago

How is the traffic getting routed to the CC in the current state from the AVD users?

SriNarasimha

(Customer)

a year ago

The default route of the AVD subnet is Cloud Connector LB IP.

Log In to Answer

Associated Tags

azure

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,725

1725 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,473

1473 Views

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

Cloud Connector- Traffic Forwarding
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000ezPXk0AM/routing-to-cloud-connector","lastmod":"2025-09-22T07:36:20.000Z","id":"0D5PJ00000ezPXk0AM"} -->
## Routing to Cloud Connector

- Source: https://community.zscaler.com/s/question/0D5PJ00000ezPXk0AM/routing-to-cloud-connector
- Type: Q&A
- Last activity: 2025-09-22T07:36:20.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

NeilC

(Partner) asked a question.

September 18, 2025 at 8:05 AM

Routing to Cloud Connector

We have a customer implementing Cloud Connector in AWS.

They want traffic from a server in AWS to go via Cloud Connector to reach a ZPA-Published App, but all other traffic to go via the default route. I don't want to set the CC as the Default Route, as then it would become part of the critical inline path to the internet for all traffic. How can I set a route to send just traffic destined for the ZPA App via the CC (as the destination IP isn't visible)?

I'm thinking a DNS entry that resolves a FQDN to a specific IP that is routed via the CC??

Thanks!

Cloud Connector

2 answers

578 views

D4rkie

(Customer)

10 months ago

Hello Neil,

Application you reach behind Cloud Connector via ZPA have a configurable

Synthetic IP range

. You just need to route this synthetic IP range towards Cloud Connector if you don´t want default route / ZIA via Cloudconnector as well. This should be unqiue in your underlay and that´s it.

As per my understanding your Cloud Connector must somehow become involved to your local AWS DNS resolution (to resolve FQDn to those synthethic IPs). This can be via inline DNS proxy our out of band via DNS forwarding. Depending on your DNS design you may can proper delegate from your corp DNS some zones to Cloud Connector or you need to resolve against CC and fallback to corp DNS in case cloud connector cannot/should not resolve it.

WIth this setup you can only connect to FQDNs behind ZPA from your AWS workloads. If your AWS workload needs to connect to a raw IP this is only possible if you introduce

Network Connector

as well.

It is not that easy ;)

I would recommend to contact your zscaler contacts and request support from Zscaler Product Field specialist for cloud connector. Makes a lot of sense.

Also use ful

https://help.zscaler.com/cloud-branch-connector/zero-trust-security-aws-workloads-zscaler-cloud-connector

Cheers

Philip

NeilC

(Partner)

10 months ago

That's awesome D4rkie. Just was I was looking for. I have read that attached doc before, but missed the part about Synthetic IP Pools.

Log In to Answer

Associated Tags

aws

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,725

1725 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,473

1473 Views

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

Routing to Cloud Connector
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000fXqoD0AS/unable-to-onboard-azure-vmss-cloud-connector","lastmod":"2025-10-26T12:56:09.000Z","id":"0D5PJ00000fXqoD0AS"} -->
## Unable to onboard Azure VMSS Cloud Connector

- Source: https://community.zscaler.com/s/question/0D5PJ00000fXqoD0AS/unable-to-onboard-azure-vmss-cloud-connector
- Type: Q&A
- Last activity: 2025-10-26T12:56:09.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

User15799275676232513743

(Partner) asked a question.

Edited September 28, 2025 at 8:50 AM

Unable to onboard Azure VMSS Cloud Connector

Hi There,

We are currently trying to deploy Zscaler Cloud Connectors VMSS in Azure via Terraform. VMs are deployed and able to reach Zscaler teanant with API calls (successful API login in Audit logs) however the Cloud connectors don't appear on the Cloud Connector portal. I've seen on some documetations, there is an option for enabling auto-scaling in the provisioning template. However I don't see that option on the GUI.

Appreciate feedback from anyone who deployed this before.

Namal

Cloud Connector

1 answer

246 views

nullmann

(Employee)

9 months ago

Hi Namal,

if the "Auto-Scaling" toggle isn't available in your provioning template, you have to create a support ticket requesting to enable the Azure Scale-Set feature for your tenant. Same would be true for AWS Auto-Scaling as well.

You are also right, this is mandatory in order to get the setup working.

Yours,

Nils

Log In to Answer

Associated Tags

azure

automation

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,725

1725 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,473

1473 Views

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

Unable to onboard Azure VMSS Cloud Connector
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000mdXdO0AU/zsatrayexe-unhandled-exception-kernelbasedll","lastmod":"2025-12-29T19:10:48.000Z","id":"0D5PJ00000mdXdO0AU"} -->
## ZSATray.exe unhandled exception KERNELBASE.dll

- Source: https://community.zscaler.com/s/question/0D5PJ00000mdXdO0AU/zsatrayexe-unhandled-exception-kernelbasedll
- Type: Q&A
- Last activity: 2025-12-29T19:10:48.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

cscmd

(Customer) asked a question.

December 29, 2025 at 7:10 PM

ZSATray.exe unhandled exception KERNELBASE.dll

I've suddenly begun getting an error related on a single machine causing an application crash of the zsatray.exe applicaiton. I'm troubleshooting the issue but wanted to make certain that this wasn't something more widespread on the zscaler side. Event viewer is reporting the following:

Application: ZSATray.exe

Framework Version: v4.0.30319

Description: The process was terminated due to an unhandled exception.

Exception Info: System.InvalidProgramException

at System.Uri.GetComponentsHelper(System.UriComponents, System.UriFormat)

at System.IO.Packaging.PackUriHelper.Create(System.Uri, System.Uri, System.String)

at System.Windows.Navigation.BaseUriHelper..cctor()

Exception Info: System.TypeInitializationException

at System.Windows.Application.ApplicationInit()

at System.Windows.Application..cctor()

Exception Info: System.TypeInitializationException

at System.Windows.Application..ctor()

at ZSATray.ZSATrayMain.Main()

Faulting application name: ZSATray.exe, version: 4.7.0.61, time stamp: 0x686391f7

Faulting module name: KERNELBASE.dll, version: 10.0.26100.7462, time stamp: 0xe9f9d656

Exception code: 0xe0434352

Fault offset: 0x00000000000c782a

Faulting process id: 0x2E00

Faulting application start time: 0x1DC78F58BF282C1

Faulting application path: C:\Program Files\Zscaler\ZSATray\ZSATray.exe

Faulting module path: C:\WINDOWS\System32\KERNELBASE.dll

Report Id: a506e2fc-a2cd-4ee2-a78c-a247eeba9000

Faulting package full name:

Faulting package-relative application ID:

Cloud Connector

290 views

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,726

1726 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,474

1474 Views

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

ZSATray.exe unhandled exception KERNELBASE.dll
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000oWTAC0A4/aws-cloud-connector-fails-to-provision-python-39311-package-mismatch-in-ami","lastmod":"2026-02-03T04:11:46.000Z","id":"0D5PJ00000oWTAC0A4"} -->
## AWS Cloud Connector fails to provision - Python 3.9/3.11 package mismatch in AMI

- Source: https://community.zscaler.com/s/question/0D5PJ00000oWTAC0A4/aws-cloud-connector-fails-to-provision-python-39311-package-mismatch-in-ami
- Type: Q&A
- Last activity: 2026-02-03T04:11:46.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

vitalemazo

(Customer) asked a question.

Edited January 31, 2026 at 5:53 AM

AWS Cloud Connector fails to provision - Python 3.9/3.11 package mismatch in AMI

AWS Environment - AMI: ami-0a7dfac79f3f24414 / CC_AMI_PROD-05_06_2025 - Product Code: 2l8tfysndbav4tv2nfjwak3cu -

Deployment: Terraform + ASG, AWS Cloud Connector us-east-1/us-west-2 Issue

Cloud Connector fails to provision. zagent crashes on startup with: File "/sc/zagent/bin/zutil.py", line 19, in import requests

ModuleNotFoundError:

No module named 'requests' Root Cause Python symlink mismatch: $ readlink -f /usr/local/bin/python3 /usr/local/bin/python3.9

# zagent uses this - NO requests $ /usr/local/bin/python3.11 -c "import requests; print(requests.__version__)" 2.32.3

# requests IS installed here Site-packages comparison: - /usr/local/lib/python3.9/site-packages/ → empty (only README.txt) - /usr/local/lib/python3.11/site-packages/ → has requests 2.32.3

Proof Running zutil.py with Python 3.11 works (gets past imports): $ /usr/local/bin/python3.11 /sc/zagent/bin/zutil.py IndexError: list index out of range #

Expected - needs args, but imports succeeded

Fix Required ln -sf /usr/local/bin/python3.11 /usr/local/bin/python3 Request 1. Provide sudo password to apply fix, OR 2. Publish corrected AMI

Example testing: Below

$ /usr/local/bin/python3.11 -c "import requests; print('OK')"

OK

$ /usr/local/bin/python3 -c "import requests; print('OK')"

Traceback (most recent call last):

File "", line 1, in

ModuleNotFoundError: No module named 'requests'

$ /usr/local/bin/python3.11 /sc/zagent/bin/zutil.py

Traceback (most recent call last):

File "/sc/zagent/bin/zutil.py", line 783, in

cmd = sys.argv[1]

~~~~~~~~^^^

IndexError: list index out of range

Cloud Connector

142 views

Log In to Answer

Associated Tags

automation

aws

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,726

1726 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,476

1476 Views

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

AWS Cloud Connector fails to provision - Python 3.9/3.11 package mismatch in AMI
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000pQS8S0AW/branch-connectors-inactive","lastmod":"2026-02-16T15:11:10.000Z","id":"0D5PJ00000pQS8S0AW"} -->
## Branch connectors inactive

- Source: https://community.zscaler.com/s/question/0D5PJ00000pQS8S0AW/branch-connectors-inactive
- Type: Q&A
- Last activity: 2026-02-16T15:11:10.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

JimKing

(Customer) asked a question.

February 15, 2026 at 3:53 PM

Branch connectors inactive

Is anyone else have issues provisioning new branch connectors? I can get them to deploy but they are stuck on installed SMEDGE.

The same symptoms a few weeks back and Zscaler posted a global incident.

Cloud Connector

186 views

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,715

1715 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,466

1466 Views

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

Branch connectors inactive
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000pSbir0AC/how-to-resolve-wildcard-fqdn-in-cloud-connectors","lastmod":"2026-02-16T15:14:59.000Z","id":"0D5PJ00000pSbir0AC"} -->
## How to resolve Wildcard FQDN  in Cloud Connectors

- Source: https://community.zscaler.com/s/question/0D5PJ00000pSbir0AC/how-to-resolve-wildcard-fqdn-in-cloud-connectors
- Type: Q&A
- Last activity: 2026-02-16T15:14:59.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

SriNarasimha

(Customer) asked a question.

Edited February 16, 2026 at 3:13 PM

How to resolve Wildcard FQDN  in Cloud Connectors

Hi There,

We have several workloads (AVD) provisioned in Azure that are accessing the Internet via the Cloud Connectors (CC) installed in Azure.

We are encountering an issue where these workloads are experiencing frequent disconnections when accessing wildcard FQDN that are random in nature. The client has subscribed to both ZIA and ZPA.

Do you have any recommendations on how to resolve this problem?

Cloud Connector

164 views

Log In to Answer

Associated Tags

azure

best-practice

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,726

1726 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,475

1475 Views

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

How to resolve Wildcard FQDN  in Cloud Connectors
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000pVztx0AC/connectivity-between-org-with-zscaler-and-private-gcp-vpcs","lastmod":"2026-02-17T21:29:02.000Z","id":"0D5PJ00000pVztx0AC"} -->
## Connectivity between Org with Zscaler and Private GCP VPCs

- Source: https://community.zscaler.com/s/question/0D5PJ00000pVztx0AC/connectivity-between-org-with-zscaler-and-private-gcp-vpcs
- Type: Q&A
- Last activity: 2026-02-17T21:29:02.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

ganest-tiwari-brevan

(Customer) asked a question.

February 17, 2026 at 2:56 PM

Connectivity between Org with Zscaler and Private GCP VPCs

Hey Folks,

We have Zscaler used across our organisation and we enable a lot of connectivity to our services using Zscaler already. We have our infrastructure on a GCP Private Shared VPC and we have always faced a problem of connectivity to our private workloads that we have running on our vpcs. I recently about Zscaler Connectors and I was wondering if it is possible to do so using Zscaler somehow.

Lets this for an example

We have a CloudSQL instance on a private network in a subnet which is not exposed to the internet. We use CloudSQL Auth Proxy and client libraries to connect to this instance even within the vpc so as to the connections are encrypted.

Our employees sometimes want to connect to these dbs and the way they do it is log on to the bastion, open up a tunnel between their computer and bastion, run a auth proxy on that tunnel port & connect from their computers using that tunnel.

Can we simplify this somehow, is there a zscaler tech we can use specifically the connectors.

Cloud Connector

170 views

Log In to Answer

Associated Tags

No tags associated with this post!!

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,718

1718 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,470

1470 Views

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

Connectivity between Org with Zscaler and Private GCP VPCs
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000qis5T0AQ/branch-connector","lastmod":"2026-03-19T15:07:04.000Z","id":"0D5PJ00000qis5T0AQ"} -->
## Branch Connector

- Source: https://community.zscaler.com/s/question/0D5PJ00000qis5T0AQ/branch-connector
- Type: Q&A
- Last activity: 2026-03-19T15:07:04.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Rama

(Partner) asked a question.

March 11, 2026 at 7:17 AM

Branch Connector

Branch Connector deployment scenario , traffic forwarding methods and routed tunnel purpose in BRANCH CONNECTOR

1 answer

151 views

jpgurrea

(Customer)

4 months ago

Is this a link to a resource? It is not opening up properly.

Log In to Answer

Associated Tags

best-practice

certificate

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

Branch Connector
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000qixuf0AA/branch-connector","lastmod":"2026-03-11T07:17:46.000Z","id":"0D5PJ00000qixuf0AA"} -->
## Branch Connector

- Source: https://community.zscaler.com/s/question/0D5PJ00000qixuf0AA/branch-connector
- Type: Q&A
- Last activity: 2026-03-11T07:17:46.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Rama

(Partner) asked a question.

March 11, 2026 at 7:17 AM

Branch Connector

Branch Connector deployment scenario , traffic forwarding methods and routed tunnel purpose in BRANCH CONNECTOR

115 views

Log In to Answer

Associated Tags

best-practice

certificate

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

Branch Connector
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000vPTAs0AO/need-a-test-account-to-test-icap-receiver","lastmod":"2026-06-02T16:54:51.000Z","id":"0D5PJ00000vPTAs0AO"} -->
## Need a test account to test ICAP receiver

- Source: https://community.zscaler.com/s/question/0D5PJ00000vPTAs0AO/need-a-test-account-to-test-icap-receiver
- Type: Q&A
- Last activity: 2026-06-02T16:54:51.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

harsh.kumar

(Customer) asked a question.

May 27, 2026 at 3:08 PM

Need a test account to test ICAP receiver

Hi, we have built a ICAP Receiver and we want to test it. Can we get a test account to test the ICAP Receiver

Cloud Connector

2 answers

141 views

Ben_Garrison

(Employee)

2 months ago

The Zenith Community cannot field this request. You may contact your account manager or open a support portal ticket.

Thank you

Ramesh M

(Employee)

2 months ago

Reach out your Account & Sales team, TAM/ TSM/CSM team to enable the same.

Log In to Answer

Associated Tags

aws

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,726

1726 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,477

1477 Views

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

Need a test account to test ICAP receiver
<!-- /ZS-POST -->

---

<!-- ZS-POST {"url":"https://community.zscaler.com/s/question/0D5PJ00000z7NO90AM/cloud-connector-has-two-service-nics-primary-and-secondary","lastmod":"2026-07-28T17:05:07.000Z","id":"0D5PJ00000z7NO90AM"} -->
## Cloud Connector has two service NICs: primary and secondary

- Source: https://community.zscaler.com/s/question/0D5PJ00000z7NO90AM/cloud-connector-has-two-service-nics-primary-and-secondary
- Type: Q&A
- Last activity: 2026-07-28T17:05:07.000Z
- Note: ユーザー投稿であり Zscaler の公式見解ではない。内容が古い場合があるため投稿日を確認すること。

Cloud Connector

ssugawara

(Customer) asked a question.

July 24, 2026 at 7:04 AM

Cloud Connector has two service NICs: primary and secondary

If anyone knows about this, I would appreciate your help.

I deployed Zscaler Cloud Connector via the Azure Marketplace. As a result, the VM had three NICs by default: a primary service NIC, a secondary service NIC, and a management NIC.

However, the Cloud Connector deployment guide did not mention that multiple service NICs would be created.

- Is it correct to consider the secondary service NIC as a standby NIC?

- Is there any documentation that explains how the primary and secondary service NICs are used differently?

Cloud Connector

11 views

Log In to Answer

Associated Tags

azure

Do you like what

you read?

Please show your appreciation if you like the content on this post.

Click the Like icon if you find the content of this post useful and you would like to show your appreciation.

Solutions

11/14/2022

at

03:45 PM

Cannot create Dropbox transfer/share from Mac Finder/Dropbox App

Cloud Connector

Chris_Kroll

0 Views

0 Likes

3 Comments

9/12/2022

at

08:09 AM

ZPA cloud connector in 3rd party IaaS/SaaS

Cloud Connector

jankowalski

3 Views

1 Like

2 Comments

8/8/2022

at

08:52 AM

ZScaler Cloud Connector - throughput and horizontal scaling

Cloud Connector

amirka

2 Views

0 Likes

2 Comments

1/10/2024

at

05:15 PM

ZPA APP connector support for RHEL v9

Cloud Connector

Dave-Bourke

1,726

1726 Views

0 Likes

5 Comments

2/23/2024

at

02:48 PM

App connector in Azure vs Cloud Connector

Cloud Connector

johan.castro.a

1,475

1475 Views

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

Cloud Connector has two service NICs: primary and secondary
<!-- /ZS-POST -->
