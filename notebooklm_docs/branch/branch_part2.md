# Zscaler Help — Branch / Cellular / Cloud Connector (part 2)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-08-10 01:47 UTC
Articles in this file: 108

---

<!-- ZS-ARTICLE {"url":"/cloud-branch-connector/understanding-high-availability-and-failover","lastmod":"2026-08-07T21:06Z","nid":"1455436"} -->
## Understanding High Availability and Failover

- Source: https://help.zscaler.com/cloud-branch-connector/understanding-high-availability-and-failover
- Product: Cloud & Branch Connector
- Path: Zscaler Cloud & Branch Connector Help > Deployment Management for Virtual Devices > Cloud Connector Deployment Management > Understanding High Availability and Failover
- Last modified: 2026-08-07T21:06Z
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

<!-- ZS-ARTICLE {"url":"/cloud-branch-connector/understanding-namespaces-amazon-web-services-and-microsoft-azure-accounts","lastmod":"2026-04-27T21:06Z","nid":"1508906"} -->
## Understanding Namespaces for Amazon Web Services and Microsoft Azure Accounts

- Source: https://help.zscaler.com/cloud-branch-connector/understanding-namespaces-amazon-web-services-and-microsoft-azure-accounts
- Product: Cloud & Branch Connector
- Path: Zscaler Cloud & Branch Connector Help > Administration > Cloud Connector Partner Integrations > Understanding Namespaces for Amazon Web Services and Microsoft Azure Accounts
- Last modified: 2026-04-27T21:06Z
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

<!-- ZS-ARTICLE {"url":"/cloud-branch-connector/understanding-zero-trust-sd-wan-devices","lastmod":"2026-06-23T07:15Z","nid":"1468276"} -->
## Understanding Zero Trust SD-WAN Devices

- Source: https://help.zscaler.com/cloud-branch-connector/understanding-zero-trust-sd-wan-devices
- Product: Cloud & Branch Connector
- Path: Zscaler Cloud & Branch Connector Help > Deployment Management for Physical Devices > Understanding Zero Trust SD-WAN Devices
- Last modified: 2026-06-23T07:15Z
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

<!-- ZS-ARTICLE {"url":"/cloud-branch-connector/using-sublocation-scopes-group-cloud-connector-workloads-amazon-web","lastmod":"2026-07-28T07:38Z","nid":"1532858"} -->
## Using Sublocation Scopes to Group Cloud Connector Workloads in Amazon Web Services

- Source: https://help.zscaler.com/cloud-branch-connector/using-sublocation-scopes-group-cloud-connector-workloads-amazon-web
- Product: Cloud & Branch Connector
- Path: Zscaler Cloud & Branch Connector Help > Deployment Management for Virtual Devices > Cloud Connector Deployment Management > Cloud Connector Deployment Management for AWS > Using Sublocation Scopes to Group Cloud Connector Workloads in Amazon Web Services
- Last modified: 2026-07-28T07:38Z
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

<!-- ZS-ARTICLE {"url":"/cloud-branch-connector/what-zero-trust-gateways","lastmod":"2026-08-03T13:51Z","nid":"1517756"} -->
## What Are Zero Trust Gateways?

- Source: https://help.zscaler.com/cloud-branch-connector/what-zero-trust-gateways
- Product: Cloud & Branch Connector
- Path: Zscaler Cloud & Branch Connector Help > Zero Trust Gateway Management > What Are Zero Trust Gateways?
- Last modified: 2026-08-03T13:51Z
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

<!-- ZS-ARTICLE {"url":"/cloud-branch-connector/what-zscaler-branch-connector","lastmod":"2026-04-08T10:21Z","nid":"1420881"} -->
## What Is Zscaler Branch Connector?

- Source: https://help.zscaler.com/cloud-branch-connector/what-zscaler-branch-connector
- Product: Cloud & Branch Connector
- Path: Zscaler Cloud & Branch Connector Help > What Is Zscaler Branch Connector?
- Last modified: 2026-04-08T10:21Z
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

<!-- ZS-ARTICLE {"url":"/cloud-branch-connector/what-zscaler-client-connector-vdi","lastmod":"2026-08-04T21:06Z","nid":"1472116"} -->
## What Is Zscaler Client Connector for VDI?

- Source: https://help.zscaler.com/cloud-branch-connector/what-zscaler-client-connector-vdi
- Product: Cloud & Branch Connector
- Path: Zscaler Cloud & Branch Connector Help > Zscaler Client Connector for VDI Management > What Is Zscaler Client Connector for VDI?
- Last modified: 2026-08-04T21:06Z
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

<!-- ZS-ARTICLE {"url":"/cloud-branch-connector/what-zscaler-cloud-connector","lastmod":"2026-07-13T08:51Z","nid":"1420636"} -->
## What Is Zscaler Cloud Connector?

- Source: https://help.zscaler.com/cloud-branch-connector/what-zscaler-cloud-connector
- Product: Cloud & Branch Connector
- Path: Zscaler Cloud & Branch Connector Help > What Is Zscaler Cloud Connector?
- Last modified: 2026-07-13T08:51Z
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

- **Workloads to Internet Communication**: Applications might need to access an internet or SaaS destination (third-party APIs, software updates, etc.) with a scalable, reliable security solution that inspects all transactions, applying advanced threat prevention and DLP controls.
- **Workload to Workload Communication**: Public clouds and corporate data centers for multi-cloud or hybrid cloud connectivity deliver better security and a simplified operational model compared to traditional solutions like proxies, virtual firewalls, Intrusion Detection System (IDS), and IPS.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/cloud-branch-connector/what-zscaler-zero-trust-sd-wan","lastmod":"2026-03-25T12:32Z","nid":"1478621"} -->
## What Is Zscaler Zero Trust SD-WAN?

- Source: https://help.zscaler.com/cloud-branch-connector/what-zscaler-zero-trust-sd-wan
- Product: Cloud & Branch Connector
- Path: Zscaler Cloud & Branch Connector Help > What Is Zscaler Zero Trust SD-WAN?
- Last modified: 2026-03-25T12:32Z
- Summary: Information about Zscaler Zero Trust SD-WAN

Zscaler Zero Trust SD-WAN (software-defined wide area network) provides branches, on-premises data centers, and public clouds with fast and reliable access to the internet and private applications with a direct-to-cloud architecture that features strong security and operational simplicity. The solution eliminates lateral threat movement by connecting users and IoT/OT devices to applications through the Zscaler Zero Trust Exchange (ZTE).

Cloud and branch traffic is securely forwarded directly to the ZTE, where you can apply Internet & SaaS (ZIA) or Private Access (ZPA) policies depending on traffic destination for full security inspection and access identity-based control of cloud, branch, and data center communications.

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

<!-- ZS-ARTICLE {"url":"/cloud-branch-connector/zero-trust-branch-connectivity-zscaler-branch-connector","lastmod":"2025-01-05T06:06Z","nid":"1514256"} -->
## Zero Trust Branch Connectivity with Zscaler Branch Connector

- Source: https://help.zscaler.com/cloud-branch-connector/zero-trust-branch-connectivity-zscaler-branch-connector
- Product: Cloud & Branch Connector
- Path: Zscaler Cloud & Branch Connector Help > Reference Architecture > Zero Trust Branch Connectivity with Zscaler Branch Connector
- Last modified: 2025-01-05T06:06Z
- Summary: The Zero Trust Branch Connectivity with Zscaler Branch Connector reference architecture guide that steers you through the architecture process, and provides technical deep dives into specific platform functionality and integrations.

The Zscaler Reference Architecture series delivers best practices based on real-world deployments. The recommendations in this series were developed by Zscaler's transformation experts from across the company. This guide will steer you through the architecture process and provide technical deep dives into specific platform functionality and integrations. The Zscaler Reference Architecture series is designed to be modular, so this guide will show you how to configure a different aspect of the platform in order to allow you meet your specific policy goals.

Zscaler Branch Connector takes a different approach to the SD-WAN model. It starts with a Zero Trust approach to the branch office, using the Secure Access Service Edge (SASE) model. Leveraging both Zscaler Internet Access (ZIA) and Zscaler Private Access (ZPA), users gain access to applications, not networks. Designed to be deployed anywhere, from small branches to large campuses and data centers, Zscaler Branch Connector simplifies connecting your devices with the Zscaler cloud, the Zero Trust Exchange (ZTE).

[Zero Trust Branch Connectivity with Zscaler Branch Connector](https://help.zscaler.com/downloads/cloud-branch-connector/reference-architecture/zero-trust-branch-connectivity-zscaler-branch-connector/Zero-Trust-Branch-Connectivity-with-Zscaler-Branch-Connector.pdf) [Download PDF](https://help.zscaler.com/downloads/cloud-branch-connector/reference-architecture/zero-trust-branch-connectivity-zscaler-branch-connector/Zero-Trust-Branch-Connectivity-with-Zscaler-Branch-Connector.pdf)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/cloud-branch-connector/zero-trust-sd-wan-devices-wall-and-rack-mount-instruction-manual","lastmod":"2026-06-23T07:15Z","nid":"1478656"} -->
## Zero Trust SD-WAN Devices Wall and Rack Mount Instruction Manual

- Source: https://help.zscaler.com/cloud-branch-connector/zero-trust-sd-wan-devices-wall-and-rack-mount-instruction-manual
- Product: Cloud & Branch Connector
- Path: Zscaler Cloud & Branch Connector Help > Deployment Management for Physical Devices > Zero Trust SD-WAN Devices Wall and Rack Mount Instruction Manual
- Last modified: 2026-06-23T07:15Z
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

<!-- ZS-ARTICLE {"url":"/cloud-branch-connector/zero-trust-security-aws-workloads-zscaler-cloud-connector","lastmod":"2025-06-24T07:06Z","nid":"1420871"} -->
## Zero Trust Security for AWS Workloads with Zscaler Cloud Connector

- Source: https://help.zscaler.com/cloud-branch-connector/zero-trust-security-aws-workloads-zscaler-cloud-connector
- Product: Cloud & Branch Connector
- Path: Zscaler Cloud & Branch Connector Help > Reference Architecture > Zero Trust Security for AWS Workloads with Zscaler Cloud Connector
- Last modified: 2025-06-24T07:06Z
- Summary: The Zero Trust Security for Amazon Web Services (AWS) Workloads with Zscaler Cloud Connector reference architecture guide that steers you through the architecture process, and provides technical deep dives into specific platform functionality and integrations.

The Zscaler Reference Architecture series delivers best practices based on real-world deployments. The recommendations in this series were developed by Zscaler's transformation experts from across the company. This guide will steer you through the architecture process and provide technical deep dives into specific platform functionality and integrations. The Zscaler Reference Architecture series is designed to be modular, so this guide will show you how to configure a different aspect of the platform in order to allow you meet your specific policy goals.

Zscaler Cloud Connector ensures that cloud workloads adhere to organizational security policy when accessing both public and private endpoints. This is achieved by intelligently forwarding traffic to the Zscaler Internet Access (ZIA) and Zscaler Private Access (ZPA) platforms. Cloud Connector also enables multi-cloud connectivity and enforces a security policy for cloud-to-cloud traffic. Cloud Connector is delivered in several form factors. It is available as a virtual appliance in both Amazon Web Services (AWS) and Microsoft Azure, as well as VMs for on-premises deployment. This guide walks you through the AWS deployment. To learn more about the Azure deployment, see [Zero Trust Security for Azure Workloads with Zscaler Cloud Connector](https://help.zscaler.com/cloud-branch-connector/zero-trust-security-azure-workloads-zscaler-cloud-connector).

[Download English PDF](https://help.zscaler.com/downloads/cloud-branch-connector/reference-architecture/zero-trust-security-aws-workloads-zscaler-cloud-connector/zero-trust-security-for-aws-workloads-with-zscaler-cloud-connector.pdf)

[Download Japanese PDF](https://help.zscaler.com/downloads/cloud-branch-connector/reference-architecture/zero-trust-security-aws-workloads-zscaler-cloud-connector/Zero-Trust-Security-AWS-Zscaler-v20230724_ja-JP.pdf)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/cloud-branch-connector/zero-trust-security-azure-workloads-zscaler-cloud-connector","lastmod":"2024-12-19T06:06Z","nid":"1420866"} -->
## Zero Trust Security for Azure Workloads with Zscaler Cloud Connector

- Source: https://help.zscaler.com/cloud-branch-connector/zero-trust-security-azure-workloads-zscaler-cloud-connector
- Product: Cloud & Branch Connector
- Path: Zscaler Cloud & Branch Connector Help > Reference Architecture > Zero Trust Security for Azure Workloads with Zscaler Cloud Connector
- Last modified: 2024-12-19T06:06Z
- Summary: The Zero Trust Security for Azure Workloads with Zscaler Cloud Connector reference architecture guide that steers you through the architecture process, and provides technical deep dives into specific platform functionality and integrations.

The Zscaler Reference Architecture series delivers best practices based on real-world deployments. The recommendations in this series were developed by Zscaler's transformation experts from across the company. This guide will steer you through the architecture process and provide technical deep dives into specific platform functionality and integrations. The Zscaler Reference Architecture series is designed to be modular, so this guide will show you how to configure a different aspect of the platform in order to allow you meet your specific policy goals.

Zscaler Cloud Connector ensures that cloud workloads adhere to organizational security policy when accessing both public and private endpoints. This is achieved by intelligently forwarding traffic to the Zscaler Internet Access (ZIA) and Zscaler Private Access (ZPA) platforms. Cloud Connector also enables multi-cloud connectivity and enforces a security policy for cloud-to-cloud traffic. Cloud Connector is delivered in several form factors. It is available as a virtual appliance in both Amazon Web Services (AWS) and Microsoft Azure, as well as VMs for on-premises deployment. This guide walks you through the Azure deployment. To learn more about the AWS deployment, see [Zero Trust Security for AWS Workloads with Zscaler Cloud Connector](https://help.zscaler.com/cloud-branch-connector/zero-trust-security-aws-workloads-zscaler-cloud-connector).

[Download English PDF](https://help.zscaler.com/downloads/cloud-branch-connector/reference-architecture/zero-trust-security-azure-workloads-zscaler-cloud-connector/Zero-Trust-Security-for-Azure-Workloads-with-Zscaler-Cloud-Connector.pdf)

[Download Japanese PDF](https://help.zscaler.com/downloads/cloud-branch-connector/reference-architecture/zero-trust-security-azure-workloads-zscaler-cloud-connector/Zero-Trust-Security-Azure-Zscaler-v20230715_ja-JP.pdf)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/cloud-branch-connector/zero-trust-security-gcp-workloads-zscaler-cloud-connector","lastmod":"2024-12-20T06:06Z","nid":"1474436"} -->
## Zero Trust Security for GCP Workloads with Zscaler Cloud Connector

- Source: https://help.zscaler.com/cloud-branch-connector/zero-trust-security-gcp-workloads-zscaler-cloud-connector
- Product: Cloud & Branch Connector
- Path: Zscaler Cloud & Branch Connector Help > Reference Architecture > Zero Trust Security for GCP Workloads with Zscaler Cloud Connector
- Last modified: 2024-12-20T06:06Z
- Summary: The Zero Trust Security for Google Cloud Platform (GCP) Workloads with Zscaler Cloud Connector reference architecture guide that steers you through the architecture process, and provides technical deep dives into specific platform functionality and integrations.

The Zscaler Reference Architecture series delivers best practices based on real-world deployments. The recommendations in this series were developed by Zscaler's transformation experts from across the company. This guide will steer you through the architecture process and provide technical deep dives into specific platform functionality and integrations. The Zscaler Reference Architecture series is designed to be modular, so this guide will show you how to configure a different aspect of the platform in order to allow you meet your specific policy goals.

Zscaler Cloud Connector ensures that cloud workloads adhere to organizational security policy when accessing both public and private endpoints. This is achieved by intelligently forwarding traffic to the Zscaler Internet Access (ZIA) and Zscaler Private Access (ZPA) platforms. Cloud Connector also enables multi-cloud connectivity and enforces a security policy for cloud-to-cloud traffic. This guide walks you through the Google Cloud Platform (GCP) deployment.

[Zero Trust Security for GCP Workloads with Zscaler Cloud Connector](https://help.zscaler.com/downloads/cloud-branch-connector/reference-architecture/zero-trust-security-gcp-workloads-zscaler-cloud-connector/Zero-Trust-Security-for-GCP-Workloads-with-Zscaler-Cloud-Connector.pdf) [Download PDF](https://help.zscaler.com/downloads/cloud-branch-connector/reference-architecture/zero-trust-security-gcp-workloads-zscaler-cloud-connector/Zero-Trust-Security-for-GCP-Workloads-with-Zscaler-Cloud-Connector.pdf)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/cloud-branch-connector/zscaler-client-connector-vdi-processes-allowlist","lastmod":"2026-08-03T21:06Z","nid":"1516321"} -->
## Zscaler Client Connector for VDI Processes to Allowlist

- Source: https://help.zscaler.com/cloud-branch-connector/zscaler-client-connector-vdi-processes-allowlist
- Product: Cloud & Branch Connector
- Path: Zscaler Cloud & Branch Connector Help > Zscaler Client Connector for VDI Management > Zscaler Client Connector for VDI Processes to Allowlist
- Last modified: 2026-08-03T21:06Z
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

<!-- ZS-ARTICLE {"url":"/cloud-branch-connector/zscaler-client-connector-vdi-release-summary-2024","lastmod":"2024-03-07T23:36Z","nid":"1478781"} -->
## Zscaler Client Connector for VDI Release Summary (2024)

- Source: https://help.zscaler.com/cloud-branch-connector/zscaler-client-connector-vdi-release-summary-2024
- Product: Cloud & Branch Connector
- Path: Zscaler Cloud & Branch Connector Help > Release Notes > Zscaler Cloud & Branch Connector VDI Release Notes (per OS) > Zscaler Client Connector for VDI Release Summary (2024)
- Last modified: 2024-03-07T23:36Z
- Summary: Zscaler Client Connector for VDI release summary for updates deployed, per OS and version, in 2024 on Zscaler Cloud & Branch Connector.

This article provides a summary of all new features and enhancements released per operating system (OS) for the Zscaler Client Connector for VDI on Zscaler Cloud & Branch Connector.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/cloud-branch-connector/zscaler-client-connector-vdi-release-summary-2025","lastmod":"2025-12-30T10:41Z","nid":"1516236"} -->
## Zscaler Client Connector for VDI Release Summary (2025)

- Source: https://help.zscaler.com/cloud-branch-connector/zscaler-client-connector-vdi-release-summary-2025
- Product: Cloud & Branch Connector
- Path: Zscaler Cloud & Branch Connector Help > Release Notes > Zscaler Cloud & Branch Connector VDI Release Notes (per OS) > Zscaler Client Connector for VDI Release Summary (2025)
- Last modified: 2025-12-30T10:41Z
- Summary: Zscaler Client Connector for VDI release summary for updates deployed, per OS and version, in 2025 on Zscaler Cloud & Branch Connector.

This article provides a summary of all new features and enhancements released per operating system (OS) for the Zscaler Client Connector for VDI on Zscaler Cloud & Branch Connector.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/cloud-branch-connector/zscaler-client-connector-vdi-release-summary-2026","lastmod":"2026-07-15T01:57Z","nid":"1534300"} -->
## Zscaler Client Connector for VDI Release Summary (2026)

- Source: https://help.zscaler.com/cloud-branch-connector/zscaler-client-connector-vdi-release-summary-2026
- Product: Cloud & Branch Connector
- Path: Zscaler Cloud & Branch Connector Help > Release Notes > Zscaler Cloud & Branch Connector VDI Release Notes (per OS) > Zscaler Client Connector for VDI Release Summary (2026)
- Last modified: 2026-07-15T01:57Z
- Summary: Zscaler Client Connector for VDI release summary for updates deployed, per OS and version, in 2026 on Zscaler Cloud & Branch Connector.

This article provides a summary of all new features and enhancements released per operating system (OS) for the Zscaler Client Connector for VDI on Zscaler Cloud & Branch Connector.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/about-integrations","lastmod":"2026-07-15T16:10Z","nid":"1532892"} -->
## About Integrations

- Source: https://help.zscaler.com/zero-trust-branch/about-integrations
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust Device Segmentation > Third-Party Integrations > About Integrations
- Last modified: 2026-07-15T16:10Z
- Summary: Information on different types of third-party integrations supported by Zero Trust Branch.

Zero Trust Branch integrates with third-party and Zscaler services to extend visibility, automate response workflows, and enrich analytics across your enterprise systems. You can integrate Zero Trust Branch with other tools from a centralized interface in the Zscaler Admin Console. The supported integrations include tools for monitoring, security orchestration, IT service management, and analytics.

Integrations provide the following benefits and enable you to:

- Unify monitoring and analytics across your environment by forwarding data to external tools such as security information and event management (SIEM) systems, SNMP dashboards, and Kibana for richer operational insights.
- Automate incident response and IT workflows by integrating with platforms, such as ServiceNow, CrowdStrike, and SentinelOne to streamline ticketing, alerting, and remediation.
- Enhance security intelligence and visibility by correlating telemetry with third-party and Zscaler services, helping to identify threats faster and reduce response time.

## About the Integrations Page

On the Integrations page (Infrastructure > Connectors > Edge > Integrations), you can view and manage the following integrations:

1. **SNMP Monitoring**: Monitor your assets over SNMP using SNMP channels. You can add and manage multiple SNMP channels.
2. **Kibana Credentials**: Visualize Zero Trust Branch data on Kibana dashboards.
3. **CrowdStrike Integration**: For devices connected to Zero Trust Branch, [provide device telemetry to CrowdStrike](https://help.zscaler.com/zero-trust-branch/integrating-crowdstrike-zero-trust-branch) for correlation.
4. **SentinelOne Integration**: Enhance visibility of endpoints connected to Zero Trust Branch.
5. **SIEM Integration**: Forward Zero Trust Branch events to your [SIEM platform](https://help.zscaler.com/zero-trust-branch/configuring-siem-integration) for centralized security analytics.
6. **Armis Integration**: Enhance the accuracy of device discovery by integrating with Armis.
7. **Ordr Integration**: Enhance the accuracy of device discovery by integrating with Ordr.
8. **ServiceNow Integration**: Automate incident creation by integrating with ServiceNow.
9. **Zscaler Services Integration**: Manage connectivity with your other Zscaler services.

[Image: The Integrations page in Zero Trust Branch showing different integration options]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/about-objects","lastmod":"2026-07-22T09:03Z","nid":"1533894"} -->
## About Objects

- Source: https://help.zscaler.com/zero-trust-branch/about-objects
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Policy Management > Objects > About Objects
- Last modified: 2026-07-22T09:03Z
- Summary: Information about the objects in the Zero Trust Branch Admin Portal.

Zero Trust Branch allows you to create, organize, and manage reusable logical objects. These objects help you simplify policy creation by grouping related resources such as devices, networks, applications, and ports, so they can be referenced consistently across [firewall](https://help.zscaler.com/zero-trust-branch/understanding-firewall-policies), [routing](https://help.zscaler.com/zero-trust-branch/understanding-routing-policies), and [DNS](https://help.zscaler.com/zero-trust-branch/configuring-site-dns-policies) policies.

You can manage objects in two categories: Airgap objects for internal assets and network definitions, and Microsoft Active Directory (AD) objects for directory-based identities and groups for AD integration.

The Microsoft AD objects are synced automatically if an AD server is integrated with a site.

By separating object definition from policy configuration, you can build modular, scalable configurations without duplicating information across multiple policies.

Objects provide the following benefits and enable you to:

- Define resources once and reference them across multiple policies.
- Simplify configuration and reduce repetition while ensuring policy definitions remain consistent and accurate, even as deployments expand over time.
- Apply changes to policies faster by modifying shared objects rather than individual rules.

## Types of Objects

The following are the various types of objects supported across different policies:

| Object | Supported Fields Across Policies |  |  |
| --- | --- | --- | --- |
| Firewall Policies | Routing Policies | DNS Policies |  |
| Device | Source Destination | Source Destination | Not applicable |
| Domain | Destination | Source Destination | Destination |
| DNS Gateway | Not applicable | Not applicable | DNS Gateways |
| Network | Source Destination | Source Destination | Source |
| MAC Address | Source | Source | Not applicable |
| Port | Port | Port | Not applicable |
| Time Schedule | Time Schedule Groups | Not applicable | Not applicable |
| Zone | Source Zone Destination Zone | Not applicable | Not applicable |
| SaaS App | Destination | Not applicable | Not applicable |

## About the Objects Page

On the Objects page (Resources > Objects), you can do the following:

- Airgap Objects
- Microsoft AD Objects

Manage Airgap objects on the Airgap tab:

1. Search for an object by name.
2. Filter objects based on specific sites.
3. [Add a new object.](https://help.zscaler.com/zero-trust-branch/adding-object)
4. View the list of all objects. For each object, you can see:
  - **Name**: The name of the object.
  - **Type**: The category of the object, such as device, domain, DNS gateway, network, MAC address, port, time schedule, zone, or SaaS app.
  - **Members**: The list of entities that are grouped into the object. For example, IP addresses or IP address ranges can be members of a network type object.
  - **Member Groups**: The list of groups that contain members. This applies only to the network and MAC address type objects.
  - **Autonomous**: Whether the devices in the object are autonomous or not. This field applies only to the device type objects.
  - **Policies**: The number of [firewall](https://help.zscaler.com/zero-trust-branch/understanding-firewall-policies), [routing](https://help.zscaler.com/zero-trust-branch/understanding-routing-policies), and [DNS](https://help.zscaler.com/zero-trust-branch/configuring-site-dns-policies) policies that use this object.
5. [Edit, clone, view details of, or delete an object](https://help.zscaler.com/zero-trust-branch/managin-objects).
6. Move to the next or previous page.
7. Limit the number of objects displayed per page. You can choose to display 5, 10, 25, or 100 objects per page.

[Image: Objects page with annotations showing various options]

Manage Microsoft AD objects on the Microsoft AD tab:

1. Search for an object by name.
2. Filter objects based on specific sites.
3. View the list of all objects. For each object, you can see:
  - **Name**: The name of the object.
  - **Type**: The category of the object, such as device, domain, DNS gateway, network, MAC address, port, time schedule, zone, or SaaS app.
  - **Members**: The list of entities that are grouped into the object. For example, IP addresses or IP address ranges can be members of a network type object.
  - **Member Groups**: The list of groups that contain members. This applies only to the network and MAC address type objects.
  - **Autonomous**: Whether the devices in the object are autonomous or not. This field applies only to the device type objects.
  - **Policies**: The number of [firewall](https://help.zscaler.com/zero-trust-branch/understanding-firewall-policies), [routing](https://help.zscaler.com/zero-trust-branch/understanding-routing-policies), and [DNS](https://help.zscaler.com/zero-trust-branch/configuring-site-dns-policies) policies that use this object.
4. [View object details](https://help.zscaler.com/zero-trust-branch/managin-objects).
5. Move to the next or previous page.
6. Limit the number of objects displayed per page. You can choose to display 5, 10, 25, or 100 objects per page.

[Image: Objects page with annotations showing various options]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/adding-app-connector-site","lastmod":"2026-07-20T15:06Z","nid":"1529430"} -->
## Adding App Connectors to a Site

- Source: https://help.zscaler.com/zero-trust-branch/adding-app-connector-site
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Adding App Connectors to a Site
- Last modified: 2026-07-20T15:06Z
- Summary: How to add App Connectors to a site in Zero Trust Branch.

App Connectors provide an authenticated interface between a site and the Zscaler Admin Console cloud. You need the provisioning key for the App Connector in order to add it to the site. To learn more, see [About App Connector Provisioning Keys](https://help.zscaler.com/zpa/about-connector-provisioning-keys).

To add an App Connector to a site:

1. Go to **Infrastructure > Connectors > Edge > Sites**.
2. In the**Site Name** column, click the name of the site to which you want to add an App Connector. See image.
3. On the site details page, click the **Settings**tab, then click **ZPA**in the left-side navigation. Click **Add** in the upper-right corner to add a new App Connector. See image. In the **Add App Connector**panel: See image.
  - **Name**: Enter a name for this App Connector.
  - **Provision Key**: Enter the provisioning key for this App Connector.
4. Click **Save** to save the configuration.

[Image: Accessing details for a site on the Sites page]

[Image: Adding an App Connector on the Settings tab for a Site]

[Image: Add App Connector panel]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/adding-bgp-site","lastmod":"2026-07-20T14:17Z","nid":"1525631"} -->
## Adding a Border Gateway Protocol to a Site

- Source: https://help.zscaler.com/zero-trust-branch/adding-bgp-site
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Adding a Border Gateway Protocol to a Site
- Last modified: 2026-07-20T14:17Z
- Summary: How to add border gateway protocols (BGPs) to a site in Zero Trust Branch.

A border gateway protocol (BGP) governs routing among devices within a site.

To add a BGP configuration:

1. Go to **Deployment > Sites**.
2. In the**Site Name** column, click the name of the site to which you want to add a BGP configuration. See image.
3. On the site details page, click the **Settings**tab, then click **BGP**in the left-side navigation. Click **Add** in the upper-right corner to add a new BGP configuration. See image. In the **Add Peer**panel: See image.
  - **Name**: Enter a name for this BGP configuration.
  - **Neighbor IP**: Enter the IP address of the peer router for this configuration.
  - **Neighbor AS**: Enter the autonomous system (AS) number that identifies this peer router's network.
  - **Local IP**: Enter the IP address of the local network.
  - **Local AS**: Enter the AS number that identifies the local network.
  - **Password**: Enter the password shared between the two networks.
  - **Graceful Restart Time**: Enter a time in milliseconds to allow the peer router to restart before timing out.
4. Click **Save** to save the configuration.

[Image: Accessing details for a site on the Sites page]

[Image: Adding a BGP configuration on the Settings tab for a Site]

[Image: Add Peer panel]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/adding-hub","lastmod":"2026-07-15T16:26Z","nid":"1525471"} -->
## Adding a Hub

- Source: https://help.zscaler.com/zero-trust-branch/adding-hub
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust SD-WAN > Adding a Hub
- Last modified: 2026-07-15T16:26Z
- Summary: Adding a hub in Zero Trust Branch.

In Zero Trust Branch, a hub enables site-to-site communication. To learn more, see [Configuring Zero Trust Branch Site-to-Site Connectivity Over Routed Tunnels](https://help.zscaler.com/zero-trust-branch/configuring-zero-trust-branch-site-site-connectivity-over-routed-tunnels).

## Add a Hub

To add a hub:

1. In the Zscaler Admin Console, go to **Infrastructure**> **Connectors**> **Edge**> **Hubs**.
2. Click **Add On-Prem Hub**in the upper-right corner. See image.
3. In the **Add On-Prem Hub**drawer:
  1. To add another gateway to an existing hub: See image.
    - **Location**: Select an existing hub from the drop-down menu.
    - **Gateway Name**: Enter a name for the gateway.
    - **WAN Virtual IP**: Enter the floating IP address to be used.
    - **WAN VRRP Group ID (1 - 255)**: Enter a number between 1 and 255 to uniquely identify the WAN router.
    - **Provision using ZTP**: Disable if you do not want to provision this site using Zero Touch Provisioning (ZTP). ZTP is not supported in Zero Trust Branch version 7.7 and earlier.
    - **WAN IP Address**: Enter the WAN IP address.
    - **WAN Subnet Mask**: Enter the WAN subnet mask.
    - **Default Gateway IP Address**: Enter the gateway IP address.
  2. To add a new hub location: See image.
    - **Location**: Select **Add New Location**.
    - **Name**: Enter a name for the location.
    - **Gateway Name**: Enter a name for the gateway.
    - **User Reachable IP**: Enter the IP address for the gateway.
    - **Provision Using ZTP**: Disable if you do not want to provision this site using ZTP. ZTP is not supported in Zero Trust Branch version 7.7 and earlier.
    - **WAN IP Address**: Enter the WAN IP address.
    - **WAN Subnet Mask**: Enter the WAN subnet mask.
    - **Default Gateway IP Address**: Enter the gateway IP address.
  3. Click **Add**to save the hub.

[Image: Hubs page]

[Image: Add On-Prem Hub drawer for an existing hub]

[Image: Add On-Prem Hub drawer for a new hub]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/adding-objects","lastmod":"2026-07-22T09:04Z","nid":"1533897"} -->
## Adding Objects

- Source: https://help.zscaler.com/zero-trust-branch/adding-objects
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Policy Management > Objects > Adding Objects
- Last modified: 2026-07-22T09:04Z
- Summary: How to create an object in the Zscaler Admin Console.

Zero Trust Branch supports several different types of objects (e.g., devices, networks, ports, DNS gateways, etc.). You can add groups of objects and enforce security policies dynamically to prevent lateral movement of threats.

To add an object:

1. Go to **Policies > Access Control > Segmentation > Objects & Groups.**Click on the **Airgap** tab.
2. Click **Add**and select the object type. See image.
3. Define any of the following object types, as required:
  - Device
  - Domains
  - DNS Gateways
  - Network
  - MAC
  - Port
  - Time Schedule
  - Zone
  - SaaS Apps
4. Click **Add**.

The object is added and displayed on the Objects page in the Zscaler Admin Console.

A device object provides the ability to group devices based on different attributes.

In the **Add Device**drawer:

- **Name:**Enter a name for this device object.
- **Autonomous**: Enable this to automatically add newly discovered assets matching the defined attributes to this group.
- **Device Attribute:** Select an attribute from the drop-down menu and define additional parameters:
  - **Manufacturers**:Select the device manufacturer from the **Attribute Value** drop-down menu.
  - **Operating System**: Select the OS that runs on the device from the **Attribute Value** drop-down menu.
  - **Services:** Select the services running on the device from the **Attribute Value** drop-down menu.
  - **Network**: Enter the network address to which the device belongs in the **Attribute Value** field and click **Add**.
  - **Tags**: Select or enter one or more tags to identify groups of devices and click **Add**. For instance, all Windows devices can be grouped using the tag `type:windows`. You can also create custom tags to organize devices. To learn more, see [Working with Tags](https://help.zscaler.com/zero-trust-branch/working-with-tags).

Click the **Add**icon to add multiple groups.

See image.

You can reference device objects as source or destination criteria when creating [firewall](https://help.zscaler.com/zero-trust-branch/configuring-firewall-policies) or [routing](https://help.zscaler.com/zero-trust-branch/configuring-routing-policies) policies.

A domain object provides specific domains that you can use as a destination in a DNS policy rule, or in other policy engines. In addition to custom domains, you can use predefined domain objects, such as **All Zscaler App Segments** and **All Zscaler Domains**, to support one or more domains.

In the **Add Domains**drawer:

- **Name:**Enter a name for this object.
- **Domains**: Enter one or more wildcard domains (e.g., *.example.com) or FQDNs (e.g., host.example.com). Top-level domains (e.g., example.com) are not supported.
- **Any Domain:** Select this checkbox to make this object match any domain (i.e., *.*).

See image.

You can reference domain objects when creating [firewall](https://help.zscaler.com/zero-trust-branch/configuring-firewall-policies) (as destination criteria) or [routing](https://help.zscaler.com/zero-trust-branch/configuring-routing-policies) (as source and destination criteria) policies.

A DNS object supports the addition of one or more DNS servers that can be used as redirect (forwarding) targets in a DNS policy rule. To learn more, see [Configuring Site DNS Policies](https://help.zscaler.com/zero-trust-branch/configuring-site-dns-policies).

In the **Add DNS Gateways**drawer:

- **Name:**Enter a name for this object.
- **DNS Servers**: Enter one or more IP addresses.
- **On Failure**: Choose a failure behavior:
  - **Forward to original DNS server**
  - **Return error response**

See image.

A network object supports grouping networks based on both a CIDR block and FQDN.

In the **Add Network**drawer:

- **Name**: Enter a name for this object.
- Enter the network address in CIDR notations or FQDNs and click **Add**. Non-CIDR block IP address ranges (e.g., 192.168.0.2–192.168.0.10) are converted to CIDR notation.; When an FQDN is added to a network group, the branch appliance resolves the FQDN locally and adds the IP addresses to the group. The branch appliance also honors the Time to Live (TTL) value of the DNS records, and it refreshes when the TTL value expires.
- **Member Groups**:Select other network objects as members to make this a super object comprising multiple objects. This feature is useful when creating a single policy for multiple network objects.

See image.

You can reference network objects as source or destination criteria when creating [firewall](https://help.zscaler.com/zero-trust-branch/configuring-firewall-policies) or [routing](https://help.zscaler.com/zero-trust-branch/configuring-routing-policies) policies.

A MAC object supports grouping of MAC addresses.

In the**Add MAC**drawer:

- **Name**: Enter a name for this object.
- Enter a MAC address (e.g., `20:7B:D2:24:33:83`) and click **Add**.
- **Member Groups**: Select other MAC objects as members to make this a super object comprising multiple objects. This feature is useful when creating a single policy for multiple MAC objects.

See image.

You can reference MAC objects as source criteria when creating [firewall](https://help.zscaler.com/zero-trust-branch/configuring-firewall-policies) or [routing](https://help.zscaler.com/zero-trust-branch/configuring-routing-policies) policies.

A port object supports ports in several formats:

- protocol:port-number pairs (e.g., `udp:8080`)
- protocol:port-range pairs (e.g., `TCP:1024-1030`)
- Well-known protocols or ports (e.g., `bgp`)

In the **Add Port**drawer:

- **Name**: Enter a name for this object.
- Enter a port (e.g., `udp:8080, TCP:1024-1030`) and click **Add**.

See image.

You can reference port objects as source criteria when creating [firewall](https://help.zscaler.com/zero-trust-branch/configuring-firewall-policies) or [routing](https://help.zscaler.com/zero-trust-branch/configuring-routing-policies) policies.

A time schedule object allows you to specify a group of absolute and periodic time ranges.

In the **Add Time Schedule**drawer:

- **Name:**Enter a name for this object.
- **Time Zone**: Select the time zone where the time values are configured in this object.
- **Absolute Time Range**: Select the start and end dates and times to create a specific date and time range.
- **Periodic Time Range**: Select the days and time schedules for a specific period (e.g., every Monday, Wednesday, and Friday).

To add multiple pairs of absolute and periodic time ranges within this object, click **Add Time Range**.

See image.

You can reference time schedule objects as time schedule group criteria when creating [firewall](https://help.zscaler.com/zero-trust-branch/configuring-firewall-policies) policies.

A zone object allows you to group one or more networks of the same type.

In the **Add Zone**drawer:

- **Zone type:**Select the network type for this zone:
  - **LAN Zone**: Local area network zone
  - **WAN Zone**: Wide area network zone
  - **Management Zone**: Management entities zone
  - **HA Zone**: High availability (HA) network zone
- **Name**: Enter a name for the group.

See image.

You can reference zone objects as source zone or destination zone criteria when creating [firewall](https://help.zscaler.com/zero-trust-branch/configuring-firewall-policies) policies.

A SaaS Apps object is a list of predefined Software as a Service (SaaS) apps. You can use this object as a destination in [firewall policies](https://help.zscaler.com/zero-trust-branch/configuring-firewall-policies) or an [application-based routing policy](https://help.zscaler.com/zero-trust-branch/configuring-routing-policies).

In the **Add SaaS Apps**drawer:

- **Name**: Enter a name for this object.
- **SaaS Apps**: Select one or more apps that must be grouped from the drop-down menu.

See image.

You can reference SaaS Apps objects as destination criteria when creating [firewall](https://help.zscaler.com/zero-trust-branch/configuring-firewall-policies) or [routing](https://help.zscaler.com/zero-trust-branch/configuring-routing-policies) policies.

[Image: The Objects page showing the option to add an object]

[Image: The Objects page showing the Add Device drawer]

[Image: The Objects page showing the Add Domains drawer]

[Image: The Objects page showing the Add DNS Gateways drawer]

[Image: The Objects page showing the Add Network drawer]

[Image: The Objects page showing the Add MAC drawer]

[Image: The Objects page showing the Add Port drawer]

[Image: The Objects page showing the Add Time Schedule drawer]

[Image: The Objects page showing the Add Zone drawer]

[Image: The Objects page showing the Add SaaS Apps drawer]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/configuring-airgap-lite-mode-assets","lastmod":"2026-08-06T20:11Z","nid":"1532714"} -->
## Configuring Airgap-Lite Mode for Assets

- Source: https://help.zscaler.com/zero-trust-branch/configuring-airgap-lite-mode-assets
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust Device Segmentation > Configuring Airgap-Lite Mode for Assets
- Last modified: 2026-08-06T20:11Z
- Summary: How to configure Airgap-Lite mode for assets in the Zscaler Admin Console.

Zero Trust Branch offers three protection solutions for your assets that are designed to meet different requirements for varying environments. These solutions, Airgap, Airgap-Lite, and Airgap+, address varying levels of network isolation and functionality needs. Airgap-Lite mode allows devices to use the same subnet mask provided by the DHCP server.

You can configure Airgap-Lite mode for assets in the following cases:

- Full isolation is not a strict requirement.
- If the /32 subnet mask is not supported, you choose one of the following modes based on isolation requirements: To learn more, [Understanding Protection Solutions](https://help.zscaler.com/zero-trust-branch/understanding-protection-solutions).
  - Airgap-Lite mode (if full isolation is not a requirement)
  - Airgap Plus mode

## Configuring Airgap-Lite Mode for Assets

You can configure Airgap-Lite mode using one of the following methods:

- Device Level
- VLAN Level

The device level configuration allows you to enable Airgap-Lite mode for each device independently. Use this method when you need to configure Airgap-Lite mode for specific devices that do not require full isolation.

To configure Airgap-Lite mode:

1. Go to **Infrastructure**> **Connectors > Edge > Assets**.
2. Locate and select the device for which you want to configure **Airgap-Lite** mode, and click **Edit**. See image.
3. In the asset details drawer, go to the **Security**section on the **Properties**tab.
4. Locate the **Protection**field and select **Airgap-Lite**from the drop-down menu. See image.
5. Click **Apply**.
6. Confirm that the **Airgapped**column for the device does not show a check mark. The absence of a check mark indicates that the device is running in **Airgap-Lite** mode. See image.
7. In the device terminal, run the following command for the DHCP lease release:
  - For Windows: `ipconfig /release`
  - For Linux: `dhclient -r`
8. In the device terminal, run the following command to request a new IP address from the DHCP server: **Airgap-Lite**mode is applied at the device level.
  - For Windows: `ipconfig /renew`
  - For Linux: `dhclient`
9. Repeat these steps for each device that you want to be part of the subnet.

The VLAN level configuration allows you to enable Airgap-Lite mode for multiple devices simultaneously. Use this method when you need to configure Airgap-Lite mode for all devices within a VLAN.

To configure Airgap-Lite mode:

1. Go to **Infrastructure**> **Sites > Connectors > Edge > Sites**.
2. Select the name of the site whose VLAN must be configured. See image.
3. Click **VLANs**.
4. On the **VLANs**tab, locate the VLAN whose devices must be configured with **Airgap-Lite** mode, click the **Gear**icon, and select **Edit**. See image.
5. In the **Edit Airgap VLAN**drawer, go to the **Network**section, and select **ON (Airgap-Lite)**from the **DHCP Service**drop-down menu. See image.
6. Click **Save**.
7. For each device in the VLAN, go to the device terminal and run the following command for the DHCP lease release:
  - For Windows: `ipconfig /release`
  - For Linux: `dhclient -r`
8. For each device in the VLAN, go to the device terminal and run the following command to request a new IP address from the DHCP server: **Airgap-Lite** mode is applied to the devices in the VLAN.
  - For Windows: `ipconfig /renew`
  - For Linux: `dhclient`

[Image: Assets page showing an asset selection with the option to edit the asset]

[Image: Asset details drawer showing the Protection field]

[Image: Assets page showing Airgapped column]

[Image: Sites page showing a site]

[Image: Site details page showing option to edit a VLAN]

[Image: Edit Airgap VLAN drawer showing the DHCP Service field]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/configuring-dhcp-options-site","lastmod":"2026-07-20T15:06Z","nid":"1538823"} -->
## Configuring DHCP Options for a Site

- Source: https://help.zscaler.com/zero-trust-branch/configuring-dhcp-options-site
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Configuring DHCP Options for a Site
- Last modified: 2026-07-20T15:06Z
- Summary: How to configure DHCP options for a site in Zero Trust Branch.

You can configure Dynamic Host Configuration Protocol (DHCP) options to provide additional network configuration parameters for devices that obtain IP addresses from the Zero Trust Branch appliance when the [DHCP service is enabled while configuring a site](https://help.zscaler.com/zero-trust-branch/adding-site). These options allow admins to automatically provide settings such as DNS servers, domain names, Network Basic Input/Output System (NetBIOS) information, or vendor-specific parameters to clients when they join the network.

To configure DHCP options for a site:

1. Go to **Infrastructure**> **Connectors**> **Edge**> **Sites**.
2. On the **Sites**page, click the site in the **Site Name**column for which you want to configure DHCP options. See image.
3. On the site details page, click the **Settings**tab, then click **DHCP Options**in the left-side navigation.
4. In the upper-right corner, click **Add**. See image.
5. In the **Add DHCP Options** drawer: See image.
  1. **DHCP Option**: Select a DHCP option from the drop-down menu. You can choose a predefined DHCP option (e.g., **Domain Server**, **Domain Name**, or **Vendor Specific**) from the list or select **Custom**to define your own option. See available predefined options.
  2. **Name**: (Optional) Enter a name for the custom DHCP option. This field appears only if you choose **Custom**as the **DHCP Option**.
  3. **Code**: This field auto-populates with the numeric DHCP option code associated with the selected option. Custom options require you to manually enter the option code.
  4. **Type**: This field auto-populates with the data format for the option value associated with the selected option, such as byte, string, word, long, or IP address. Custom options require you to manually select the data format from the drop-down menu.
  5. **Value**: Enter the value for the DHCP option. The value's format must match the selected type. For example, if the **Type**is **IP-address**, then the value must be an IP address.
  6. **Filter**: (Optional) Select a filter parameter from the drop-down menu. The filter parameter allows DHCP options to apply to specific devices in the network based on attributes such as MAC address, DHCP client identifier, etc.
  7. **Filter Value**: (Optional) Enter the value that must be used to filter devices to which the DHCP options are applied. This field appears only if you select an option for the **Filter**field.
6. Click **Save**.

The following table lists of supported DHCP options:

| DHCP Option | Description |
| --- | --- |
| Domain Server (6) | Specifies the DNS servers that DHCP clients use to resolve domain names. |
| Domain Name (15) | Defines the domain name assigned to DHCP clients for hostname resolution. |
| Vendor Specific (43) | Provides vendor-specific configuration parameters to supported devices. |
| NETBIOS Name Server (44) | Specifies the NetBIOS name servers that clients use for NetBIOS name resolution. |
| NETBIOS Dist Server (45) | Identifies the NetBIOS datagram distribution server used for NetBIOS message distribution. |
| NETBIOS Node Type (46) | Defines the NetBIOS node type that determines how NetBIOS names are resolved. |
| NETBIOS Scope (47) | Specifies the NetBIOS scope identifier that clients use for NetBIOS communication. |
| Address Time (51) | Specifies the duration of the DHCP lease assigned to clients. |
| DHCP Server Identifier (54) | Identifies the DHCP server that issued the IP address lease to the client. |
| Renewal Time (58) | Specifies the time when a DHCP client begins renewing its IP address lease. |
| Rebinding Time (59) | Specifies the time when a DHCP client attempts to renew the IP address lease from any available DHCP server if renewal fails. |
| Vendor Class ID (60) | Identifies the vendor type of the DHCP client to enable device-specific configuration. |
| Client ID (61) | Specifies a unique identifier that the DHCP client uses to identify itself to the DHCP server. |
| Server Name (66) | Specifies the hostname or IP address of the server that clients use to retrieve configuration files. |
| Bootfile Name (67) | Specifies the name of the boot file that clients use during network boot (PXE). |
| User Class (77) | Identifies a group or class of DHCP clients to which specific DHCP options or policies are applied. |
| Domain Search (119) | Specifies a list of domain suffixes that clients use when resolving hostnames. |
| Vendor Identified Vendor Class (124) | Identifies vendor-specific client classes using enterprise identifiers. |
| V-I Vendor-Specific Information (125) | Provides vendor-specific configuration data associated with the vendor class identifier. |
| TFTP Server (150) | Specifies the Trivial File Transfer Protocol (TFTP) server used by devices to retrieve configuration files (commonly for VoIP phone provisioning). |

[Image: The Sites page with name of a site highlighted]

[Image: The DHCP options page with the Add option highlighted]

[Image: Add DHCP Options drawer showing examples of configuring a predefined and custom option]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/configuring-firewall-policies","lastmod":"2026-07-22T09:41Z","nid":"1532549"} -->
## Configuring Firewall Policies

- Source: https://help.zscaler.com/zero-trust-branch/configuring-firewall-policies
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Policy Management > Firewall Policies > Configuring Firewall Policies
- Last modified: 2026-07-22T09:41Z
- Summary: How to configure a firewall policy in the Zero Trust Branch Admin Portal.

Firewall policies in Zero Trust Branch allow you to define how traffic is controlled between zones, sites, or network segments. Policies are essential for defining and enforcing access controls between different network segments, applications, and user groups, thereby enhancing security and compliance. Policies can apply to individual sources and destinations or to source and destination zones, and be based on networks, MAC addresses, devices, and Software as a Service (SaaS) apps. To learn more, see [Managing Objects](https://help.zscaler.com/zero-trust-branch/managing-objects).

To configure a [firewall policy](https://help.zscaler.com/zero-trust-branch/understanding-firewall-policies) in the Zero Trust Branch Admin Portal:

1. Go to **Firewall**> **Policies**. Click the tab for the scope (**Global Policies**, **Template Policies**, or **Site Policies**) under which you want to create the policy.
2. Click **Configure**> **Add Policy**. See image. The **Add Policy**drawer appears.
3. In the **Add Policy**drawer: See image.
  - **Action**: Choose the policy action (**Accept**, **Reject**, **Drop**, or **Skip**) from the drop-down menu.
  - **Name**: Enter a name for the policy.
  - **Site**: The site to which the policy must be applied. This option is applicable only for site policies.
  - **Source Zone**: (Optional) Select the LAN or WAN zone to which this policy must apply from the drop-down menu. Select the **Negate**checkbox if you want the policy to apply to all source zones except the ones selected.
  - **Destination Zone**: (Optional) Select the LAN or WAN zone to which this policy must apply from the drop-down menu. Select the **Negate**checkbox if you want the policy to apply to all destination zones except the ones selected.
  - **Source**: Select the sources to which this policy must apply from the drop-down menu. Select the **Negate**checkbox if you want the policy to apply to all sources except the ones selected.
  - **Add CIDR**: Enter the IP address range in CIDR format for the selected sources. This option appears only if the **Subnet/host**type source is selected.
  - **Destination**: Select the destinations to which this policy must apply from the drop-down menu. Select the **Negate**checkbox if you want the policy to apply to all destinations except the ones selected.
  - **Add CIDR**: Enter the IP address range in CIDR format for the selected destinations. This option appears only if the **Subnet/host**type destination is selected.
  - **Ports**: Select the port category to which this policy must apply from the drop-down menu. You can select **All**, **Allowed Ports**, **Custom Port**, or any objects created for ports. If you select **Custom Port**, enter the required ports, and click **Add**.
  - **Time Schedule Group**: If the policy applies to a specific time schedule, select it from the drop-down menu.
  - **Disable Log Throttling**: Select this option to disable throttling. When log throttling is enabled, the Zero Trust Branch log flows once per tuple per hour.
  - **Ransomware Kill Switch**: Select the color code corresponding to the threat levels for this policy. To learn more, see [Understanding the Ransomware Kill Switch](https://help.zscaler.com/zero-trust-branch/understanding-ransomware-kill-switch)[.](https://help.zscaler.com/zero-trust-branch/about-ransomware-kill-switch)
  - **Description**: Enter a description for the policy.
4. Click **Add.** The firewall policy is created.
5. Click **Commit**to apply the changes. See image.

Based on your requirements, you can edit, reorder, clone, or delete policies. To learn more, see [Managing Firewall Policies](https://help.zscaler.com/zero-trust-branch/managing-firewall-policies).

[Image: Policies page showing the Add Policy button]

[Image: Configuring a firewall policy in the Zero Trust Branch Admin Portal.]

[Image: Policies page shwoing the Commit button]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/configuring-ha-site-vrrp","lastmod":"2026-07-20T14:17Z","nid":"1529555"} -->
## Configuring a High Availability Site with Virtual Router Redundancy Protocol

- Source: https://help.zscaler.com/zero-trust-branch/configuring-ha-site-vrrp
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Configuring a High Availability Site with Virtual Router Redundancy Protocol
- Last modified: 2026-07-20T14:17Z
- Summary: How to set up a high availability (HA) site utilizing Virtual Router Redundancy Protocol (VRRP).

To ensure that mission-critical systems in a network remain resilient to failures, you can configure Zero Trust Branch with a dedicated high availability (HA) link that uses Virtual Router Redundancy Protocol (VRRP). VRRP allows multiple Zero Trust Branch appliances to share a virtual IP address, minimizing downtime and enhancing overall reliability and security.

Prior to release 7.8, Zero Trust Branch used a WAN link to run VRRP, which is less reliable due to the potential for latency, jitter, packet loss, and unpredictable failover behavior due to WAN performance.

Beginning with release 7.8, Zero Trust Branch introduced a dedicated HA link. This ensures that VRRP is independent of WAN issues, leading to faster and more reliable failovers, and also ensures that state synchronization and failover signaling occur within a controlled, secure environment, reducing attack surfaces.

## VRRP Authentication

VRRP authentication is a recommended security best practice, as it provides critical safeguards against misconfiguration and unauthorized disruptions, and ensures the integrity and security of the VRRP environment. Without configured authentication, any appliance within the same broadcast domain could send VRRP advertisements with a higher priority and take over as the master router, creating potential network instability. A misconfigured appliance with an identical VRRP ID could unintentionally disrupt an active VRRP group. Authentication adds a layer of protection by preventing such appliances from being recognized and accepted as part of the VRRP group.

It is recommended to configure VRRP authentication with a password length of 6 characters or fewer because there are known issues when handling longer passwords with VRRP. The password must be a secure combination containing only printable ASCII characters (A–Z, a–z, 0–9). Additionally, when you are updating the password on an existing site, perform this task during a scheduled maintenance window, as this change could potentially trigger a VRRP switchover, which might briefly impact the availability of network services. Planning for this activity during a controlled time frame minimizes operational risks.

## Configuring a HA Site with VRRP

To configure an HA site:

1. Go to **Infrastructure** > **Connectors** > **Edge**> **Sites**.
2. In the**Site Name** column, click the name of the site that you want to enable for HA. See image.
3. On the site details page, click the **Settings**tab, then click **VRRP**in the left-side navigation. In the **VRRP**panel: See image.
  - **VRRP Group ID**: A unique numerical identifier (between 1 and 255) that groups routers together for redundancy purposes. Zero Trust Branch automatically fills in this value.
  - **VRRP interface for****<gateway 1>**: Select the interface to use for VRRP for the primary gateway.
  - **VRRP interface for****<gateway 2>**: Select the interface to use for VRRP for the secondary gateway.
  - **Track Interface for <****gateway 1>**: Select the interface to be tracked for the primary gateway. If this interface goes down, the secondary gateway becomes the primary.
  - **Track Interface for****<gateway 2>**: Select the interface to be tracked for the secondary gateway.
  - **Password**: (Optional) Enter a password to encrypt this VRRP configuration (maximum of 8 alphabetic characters).
  - **Advertisement Interval**: The interval, in seconds, for the primary gateway to send VRRP advertisements to the secondary gateway. The default value is `1`. If the secondary gateway misses three advertisements from the primary, it becomes the new primary.
  - **Priority**: Enter the rank (between 1 and 255) for this Zero Trust Branch to determine which is the primary in the VRRP group. The Zero Trust Branch with the highest number (e.g., 255 is higher than 254) has priority.
4. Click **Save** to save the VRRP configuration for this site.

[Image: Accessing details for a site on the Sites page]

[Image: VRRP configuration panel for a Site]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/configuring-ip-reservation-assets-within-vlan","lastmod":"2026-07-20T14:06Z","nid":"1533840"} -->
## Configuring IP Address Reservation for Assets within a VLAN

- Source: https://help.zscaler.com/zero-trust-branch/configuring-ip-reservation-assets-within-vlan
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Configuring IP Address Reservation for Assets within a VLAN
- Last modified: 2026-07-20T14:06Z
- Summary: How to configure IP address reservation for assets within a VLAN.

Zero Trust Branch allows admins to reserve IP addresses for specific assets within a VLAN, ensuring predictable and stable connectivity for critical endpoints such as servers, Internet of Things devices, and infrastructure components. IP address reservation is supported only when the VLAN is configured with Zero Trust Branch operating as its DHCP server. In a Zero Trust Branch environment, Airgap VLANs provide strong endpoint isolation by assigning unique IP addressing policies per VLAN. When the Zero Trust Branch gateway functions as the DHCP server, admins can fully control how IP addresses are assigned. This includes reserving IP addresses for known assets so that these devices always receive the same IP address during DHCP lease renewals.

IP address reservation for assets provides the following benefits:

- Reliable access to essential services
- Predictable routing and policy enforcement
- Stable connectivity for devices that require static IP addresses

## Configuring IP Address Reservation for Assets

To configure IP address reservation for assets:

1. In the Zscaler Admin Console, go to **Deployment**> **Sites**.
2. Locate the site for whose VLAN the IP address reservation for assets must be configured and click the site name. See image.
3. On the site details page, click **VLANs**.
4. Locate the VLAN whose assets must use reserved IP addresses, click the **Gear**icon, and select **Import Endpoint CSV**. See image.
5. In the **Import Endpoints CSV**drawer: See image.
  1. Click **Download Template**. See image. The template CSV file is downloaded to your system.
  2. Edit the CSV file and map the known MAC addresses with the preferred IP addresses.
  3. Upload the CSV file and click **Validate**. See image.
  4. Review the mapping details.
6. Click **Submit**. The IP addresses are uploaded to the VLAN.

Upon discovery, Zero Trust Branch checks the asset’s MAC address and assigns the corresponding reserved IP address when a matching MAC address is found in the CSV file. You can also reserve an IP address allocated to an asset that is already discovered.

To reserve an IP address allocated to an already discovered asset:

1. In the Zscaler Admin Console, go to **Asset Intelligence**> **Asset**.
2. On the **Assets**page, locate and select the asset for which you want to use a reserved IP address and click **Edit**. See image. The asset must be part of the VLAN with DHCP enabled, and the CSV file containing the reserved IP addresses must have been uploaded.
3. In the asset details drawer, go to the **IP Allocation**section and select **Reserved**from the **Assignment Type** drop-down menu. See image.
4. Click **Apply**. The IP address currently assigned to the asset is added to the DHCP's reserved IP address list along with the MAC address mapping. This ensures that the IP address is assigned only to this asset in the future even after the DHCP lease's expiration.

[Image: Sites page showing a site to be selected]

[Image: VLANs page showing the option to import endpoints CSV]

[Image: Downloading the CSV template file]

[Image: Uploading and validating IP addresses of assets]

[Image: Reviwing uploaded IP addresses]

[Image: Assets page showing an option to edit a selected asset]

[Image: Selecting assignment type for IP address of an asset]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/configuring-ransomware-kill-switch-site","lastmod":"2026-07-15T16:05Z","nid":"1532702"} -->
## Configuring the Ransomware Kill Switch for a Site

- Source: https://help.zscaler.com/zero-trust-branch/configuring-ransomware-kill-switch-site
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust Device Segmentation > Ransomware Kill Switch > Configuring the Ransomware Kill Switch for a Site
- Last modified: 2026-07-15T16:05Z
- Summary: How to configure the Ransomware Kill Switch in Zero Trust Branch.

The Ransomware Kill Switch allows you to change a site's threat level color code to one of four preset severities with a single click to immediately shut down vulnerable protocols, disable access to critical networks, and minimize downtime.

To learn more about use cases and examples of use, see [Understanding the Ransomware Kill Switch](https://help.zscaler.com/zero-trust-branch/understanding-ransomware-kill-switch).

To configure the Ransomware Kill Switch for a site:

1. Go to **Policies**> **Access Control**> **Segmentation**>**Kill Switch**. See image.
2. On the **Ransomware Kill Switch**page, select the site for which you want to change threat levels from the drop-down menu.
3. Click the dial to change the threat level color code and apply the corresponding policies to the selected site.
  - Green: Lowest threat level
  - Yellow: Moderate risk
  - Orange: High risk
  - Red: Critical risk
4. Confirm the change by entering the name of the new threat level (e.g., `yellow`) in the pop-up window. Click **Confirm** to change the threat level immediately. See image.

Click **Notification Settings** to optionally customize an email notification template that will be sent when the threat level is changed. Add one or more email addresses to receive the notification and edit the email text as needed. 
See image.

[Image: Configuring Ransomware Kill Switch]

[Image: Confirming the threat level from the Ransomware Kill Switch page]

[Image: Configuring email notifications for Ransomware Kill Switch]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/configuring-routing-policies","lastmod":"2026-07-22T09:07Z","nid":"1525141"} -->
## Configuring Routing Policies

- Source: https://help.zscaler.com/zero-trust-branch/configuring-routing-policies
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Policy Management > Routing Policies > Configuring Routing Policies
- Last modified: 2026-07-22T09:07Z
- Summary: How to configure routing policies in the Zscaler Admin Console.

Zero Trust Branch routing policies provide a secure, scalable, and highly available framework for branch network traffic, enabling you to control traffic from branch sites to specific destinations.

To configure a [routing policy](https://help.zscaler.com/zero-trust-branch/understanding-routing-policies):

1. In the Zscaler Admin Console, go to **Infrastructure** > **Connectors** > **Edge** > **Sites**.
2. On the **Sites**page, locate and click the name of the site in the **Site**column. See image.
3. On the site details page, click the **Routing Policy**tab and then click **Configure**. See image.
4. Click **Add route**. See image. The **Add Routing Rule** drawer appears.
5. In the **Add Routing Rule**drawer: See image.
  - **Rule Name**: Enter a name for the routing rule.
  - **Sources**: Select the sources to which the routing rule must apply from the drop-down menu. Sources can include one or more networks, MAC addresses, devices, and Active Directory (AD) users. Select the **Negate**checkbox if you want the policy to apply to all sources except the ones selected.
  - **Add CIDR**: Enter the IP address range in CIDR format for the selected sources. This option appears only if the **Subnet/host** type source is selected.
  - **Destinations**: Select the destinations to which the routing rule must apply from the drop-down menu. Destinations can include one or more networks, devices, domains, AD users, Software as a Service (SaaS) apps, and Private Access (ZPA) application segments. Select the **Negate**checkbox if you want the policy to apply to all destinations except the ones selected. You can configure application-based routing policies that allow the Zero Trust Branch appliance to route traffic only to specific applications. To do this, select a SaaS Apps object from the **Destinations**drop-down menu. Zscaler provides a list of SaaS apps (e.g., 1Password, Adobe, etc.) that you can use to create a SaaS Apps object. You can define a [SaaS Apps object](https://help.zscaler.com/zero-trust-branch/adding-object#saas) with specific applications based on your requirements.
  - **Add CIDR**: Enter the IP address range in CIDR format for the selected destinations. This option appears only if the **Subnet/host** type destination is selected.
  - **Ports**: Select the port category to which this routing rule must apply from the drop-down menu. Select the **Negate**checkbox if you want the policy to apply to all ports except the ones selected. You can select **All**, **Allowed Ports**, **Custom Port**, or any objects created for ports. If you select **Custom Port**, enter the required ports, and click **Add**.
  - **Gateway 1**: The gateway for the site is filled in automatically. If the site is running Zero Trust Branch gateways in a cluster, the**Gateway 2** section also appears.
  - **Nexthop Interface Type**: Select **LAN/WAN**, **ZIA**, or **ZPA**from the drop-down menu. This decides how the traffic should be routed. If you select **LAN/WAN**, provide the following information:
    - **Primary Interface**: Select the primary WAN or LAN interface.
    - **Nexthop IP**: Enter the IP address of the primary router or gateway to which the traffic must be routed.
    - **Secondary Interface**: Select the secondary WAN or LAN interface.
    - **Nexthop IP**: Enter the IP address of the secondary router or gateway to which the traffic must be routed.
  - **Traffic Distribution**: If WAN is selected as both primary and secondary interfaces, you can select how you want to balance traffic between the primary and secondary nexthops:
    - **Best**: Zero Trust Branch decides programmatically how to distribute traffic.
    - **Balanced**: Load is balanced between primary and secondary WANs.
    - **None**: The primary interface takes as much load as possible.
6. Click **Save**. The routing policy is created.
7. Click **Commit**to apply the changes. See image.

Based on your requirements, you can edit, reorder, or delete policies and configure routing settings. To learn more, see [Managing Routing Policies](https://help.zscaler.com/zero-trust-branch/managing-routing-policies).

[Image: Selecting a site to configure routing policies]

[Image: Site details page showing the option to configure routing policies]

[Image: Site details page showing the option to add a route]

[Image: Configuring a routing rule]

[Image: Site details page showing the option to commit changes]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/configuring-siem-integration","lastmod":"2026-07-15T16:15Z","nid":"1532893"} -->
## Configuring SIEM Integration

- Source: https://help.zscaler.com/zero-trust-branch/configuring-siem-integration
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust Device Segmentation > Third-Party Integrations > Configuring SIEM Integration
- Last modified: 2026-07-15T16:15Z
- Summary: How to integrate SIEM services with Zero Trust Branch.

You can use security information and event management (SIEM) integration to forward Zero Trust Branch events to your organization's SIEM platform for centralized security analytics. The integration supports real-time event streaming over syslog to platforms such as Splunk or other compatible SIEM engines. Within the Zero Trust Branch architecture, branch gateways securely stream events directly to the configured SIEM engine. Depending on your organization's requirements, you can deploy the SIEM engine either on premises or in the cloud.

Zero Trust Branch uses a generic syslog format for SIEM integration. The Zero Trust Branch appliance sends syslog messages directly to the configured syslog server. Currently, Zero Trust Branch generates only one type of traffic log: the session-init logs. These session-init logs capture Layer 2, Layer 3, and Layer 4 header information when a new session is created. They also provide metadata about session initiation events and are exported to the SIEM server using the standard syslog format.

You can also view these traffic logs in the Zscaler Admin Console under Packet Logs (Logs> Insights >Packet Logs) or Flow Logs (Logs > Insights > Flow Logs). To learn more, see [Understanding Log Types, Levels, and Formats](https://help.zscaler.com/zero-trust-branch/understanding-log-types-levels-and-formats).

SIEM integration is supported over both WAN and Private Access (ZPA).
Only the session logs are forwarded to the SIEM server.

## Prerequisites

Before configuring SIEM integration in the Zscaler Admin Console, do the following:

- Obtain the SIEM server details, such as SIEM host (IP address), SIEM protocol, and SIEM port number. Zero Trust Branch supports TCP and UDP protocols only.
- Ensure that there is connectivity between the SIEM server and Zero Trust Branch's WAN interface.

## Configuring SIEM Integration

To set up integration with a SIEM server:

1. Go to **Infrastructure > Connectors > Edge >** **Integrations**.
2. Locate the **SIEM Integration**tile and click **Settings**. See image.
3. In the**SIEM Integration** drawer: See image.
  - **SIEM Host**: Enter the IP address of the SIEM server.
  - **SIEM Protocol**: From the drop-down menu, select the SIEM protocol (**TCP**or **UDP**) you want to use.
  - **SIEM Port Number**: Enter the port number that the SIEM server uses.
4. Click **Save**.
5. (Optional) If the SIEM server is hosted on a cloud platform, ensure that you have configured the necessary [firewall policies](https://help.zscaler.com/zero-trust-branch/understanding-firewall-policies) to allow access to the port.

## Verifying SIEM Integration

To verify whether SIEM integration works as expected:

1. Go to **Infrastructure> Connectors > Edge > Sites**. Click on the **Console** tab. See image.
2. Run the following command: `cat /etc/airgap/policy_container/rsyslog.d/20-siem.conf`
3. Check your syslog server for the message sent from the gateway. All session logs are sent to the syslog server.

[Image: Integrations page showing the option to integrate a SIEM server]

[Image: Configuring SIEM integration]

[Image: Sites page showing the option to launch Console]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/configuring-site-dns-policies","lastmod":"2026-07-16T11:08Z","nid":"1531196"} -->
## Configuring Site DNS Policies

- Source: https://help.zscaler.com/zero-trust-branch/configuring-site-dns-policies
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust SD-WAN > Site DNS Policies > Configuring Site DNS Policies
- Last modified: 2026-07-16T11:08Z
- Summary: How to configure DNS policies in Zero Trust Branch.

You can configure a site to manage DNS queries with the DNS policy engine. Zscaler provides several preconfigured DNS gateways, or you can create custom domain and DNS gateway objects as described in [Managing Objects](https://help.zscaler.com/zero-trust-branch/managing-objects).

To learn more about site DNS policies, see [What Are Site DNS Policies?](https://help.zscaler.com/zero-trust-branch/what-site-dns-policies)

To configure a site for DNS policies:

1. Go to **Deployment > Sites**.
2. In the**Site Name** column, click the name of the site that you want to configure for DNS routing. See image.
3. On the site details page, click the **DNS Policies**tab. See image.
4. To add a new DNS policy:
  1. On the **DNS Policies** tab, click **Configure**, then click **Add Policy**. The **Add Policy** panel appears. See image.
  2. In the **Add Policy** panel, enter the following information: See image.
    - **Name**: Enter a name for this policy.
    - **Source**: Select the source from which you want to route queries.
    - **Domain Name**: Select the domain object for the domain you want to match. To learn more about creating custom domain objects, see [Managing Objects](https://help.zscaler.com/zero-trust-branch/managing-objects).
    - **Action**: Select the routing action for this policy:
      - **Reject**: Reject queries that match this policy. When you select this action, in the **Error Code** field, choose the error you want to return.
      - **Redirect**: Redirect queries that match this policy. When you select this action, DNS queries that match this policy are forwarded to the DNS gateway you select in the **DNS Gateways** field. To learn more about creating custom DNS objects, see [Managing Objects](https://help.zscaler.com/zero-trust-branch/managing-objects).
      - **Override**: Override queries that match this policy. When you select this action, the IP address that you enter in the **Override IP** field is returned as the response.
      - **Skip**: Disable this policy.
    - **Description**: Enter a detailed description of this DNS policy.
  3. Click **Add**to create the new policy.
  4. (Optional) You can reorder the policies by clicking **Reorder**, dragging the policies into the order you want, and clicking **Save Policy Order**. DNS policies are processed based on first match. When a DNS query is matched to a query, subsequent policies are not evaluated. See image.
5. To verify that the policies are working as expected, click the **Console**tab and view the last few entries of the DNS policy log using the `tail` command (e.g., `tail -n 20 /etc/airgap/dns_proxy/policy_logs/dns_policy.log`). See image.

[Image: Accessing details for a site on the Sites page]

[Image: Accessing the DNS Policies tab on the site details page]

[Image: Reordering DNS Policies tab on the site details page]

[Image: Viewing the DNS Policies tab for a Site]

[Image: Adding a DNS Policy for a Site]

[Image: Console tab on the Sites page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/configuring-vlan","lastmod":"2026-07-17T14:32Z","nid":"1533839"} -->
## Configuring a VLAN

- Source: https://help.zscaler.com/zero-trust-branch/configuring-vlan
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Configuring a VLAN
- Last modified: 2026-07-17T14:32Z
- Summary: How to configure a VLAN for Zero Trust Branch in the Zscaler Admin Console

After deploying a Zero Trust Branch appliance or virtual machine (VM), you need to configure the VLAN that must be protected in the Zscaler Admin Console.

Ensure that a LAN port on the Zero Trust Branch appliance is connected to the switch, and the switch port must be configured as a trunk port with the relevant VLAN IDs permitted. This ensures the appliance is in the same VLAN as the devices (broadcast packets from the devices must reach the LAN port).

1. Go to **Infrastructure**>**Connectors**>**Edge**>**Sites**.
2. On the **Sites** page, search for and click the site name for which the VLAN must be added. See image.
3. On the site details page, click the **VLANs** tab, and click **Add VLAN**. See image.
4. In the **Add VLAN**drawer:
  1. **VLAN Name**: Enter a name for the VLAN.
  2. **VLAN ID**: Enter an ID for the VLAN.
  3. **Zone**: Select a zone from the drop-down menu:
    - **LAN Zone**: Select this if the VLAN is configured on the LAN-side interface with endpoints.
    - **Management Zone**: Select this if the VLAN is configured on the management-side interface.
    - **WAN Zone**: Select this if the VLAN is configured on the WAN-side interface.
  4. Configure interfaces and network settings based on the zone selected:
    - LAN-side interface and network settings
    - Management-side interface and network settings
    - WAN-side interface and network settings
5. Click **Add**.
6. Log in to your existing L2/L3 switch, router, or firewall, and shut down the SVI/VLAN interface. Add a return route for VLAN with the Zero Trust Branch WAN Virtual IP address as a nexthop. Here are sample commands from the switch or L3 device (for VLAN 226 with subnet mask 10.16.226.0/24): `#conf t #int vlan 226 #shutdown #exit #ip route 10.16.226.0 255.255.255.0 < airgap-wan-vip >`
7. On the **VLANs** tab, click the toggle in the **Enable**column. See image. By default, the VLANs are created in the staged state. Each VLAN must be enabled to configure it into the branch appliances or VMs.

1. In the **Interfaces**section, select the interface from the **Interface for Gateway**drop-down menu.
2. In the **Network**section: See image.
  1. **Interface** **IP**: Enter the IP address of the interface.
  2. **DHCP Service**: This option allows you to configure whether Zero Trust Branch also serves as the DHCP server. Select an option: See image. If you have enabled DHCP service (**ON (Airgap)** or**ON (Airgap-Lite)**), you can choose to use reserved IP addresses for specific assets. To learn more, see [Understanding Protection Solutions](https://help.zscaler.com/zero-trust-branch/understanding-protection-solutions) and [Configuring IP Address Reservation for Assets within a VLAN](https://help.zscaler.com/zero-trust-branch/configuring-ip-reservation-assets-within-vlan).
    - **ON (Airgap)**: Assigns /32 subnet masks to endpoints, placing them in a network of one. Select this option if you need full isolation for the devices in this VLAN.
    - **ON (Airgap-Lite)**: Assigns the original subnet masks to all endpoints connected to this VLAN. DHCP continues to function, but /32 masks are not provided. Select this option if you want to enable the Airgap-Lite mode for this VLAN.
    - **OFF (Static IPs)**: Forces static IP address assignment for the configured VLAN. No DHCP-based IP addresses are assigned to the endpoints.
  3. **DHCP Range Start**: Enter the starting IP address for the DHCP address range. This option is not shown when **OFF (Static IPs)** is selected in the **DHCP Service**field.
  4. **DHCP Range End**: Enter the ending IP address for the DHCP address range. This option is not shown when **OFF (Static IPs)** is selected in the **DHCP Service**field.
  5. **Private DNS Servers**: Enter the IP address of the private DNS servers that must be used for the VLAN and click **Add**. You can add multiple private DNS servers.
  6. **Options**: Select **Enable Airgap+** to enable Airgap+ mode for this VLAN. To learn more, see [Understanding Protection Solutions](https://help.zscaler.com/zero-trust-branch/understanding-protection-solutions).
  7. **Netmask**: Select the subnet mask from the drop-down menu that must be used to determine the network scope for the asset in Airgap+ mode. This option displays only if **Enable Airgap+** is selected.
  8. **Airgap+ Range Start**: Enter the starting IP address for the Airgap+ mode range. This option displays only if **Enable Airgap+** is selected.
  9. **Airgap+ Range End**: Enter the ending IP address for the Airgap+ mode range. This option displays only if **Enable Airgap+** is selected.

1. In the **Interfaces**section:
  1. **Gateway**: Select the gateway from the drop-down menu.
  2. **Interface for Gateway**: Select the interface from thedrop-down menu.
2. In the **Network**section, enter the IP address of the interface in the **Interface IP**field. See image.

1. In the **Interfaces**section:
  1. **Gateway**: Select the gateway from the drop-down menu.
  2. **Interface for Gateway**: Select the interface from thedrop-down menu.
2. In the **Network**section: See image.
  - **Use DHCP for addressing**: Enable this if you want to use DHCP addressing. Otherwise, disable this option, and enter the respective IP addresses for the **Interface IP**and **Default Gateway IP**fields.
  - **Virtual IP**: (Optional) Enter a virtual IP address if the upstream router requires a consistent return address for reverse routing.

[Image: Sites page showing a site]

[Image: VLANs page showing the option to add a VLAN]

[Image: Adding a VLAN for LAN-side interface with different options]

[Image: Adding a VLAN for a management-side interface]

[Image: Configuring a DCHP mode for VLAN]

[Image: Adding a VLAN for a WAN-side interface with different options]

[Image: VLANs page showing the option to enable a VLAN]

## Configuring a VLAN for DNS Proxy

You can specify a single VLAN to serve as the source IP address for outbound DNS requests made from the appliance's DNS proxy. This is needed when an upstream DNS server requires a fixed, internal source IP address, and when the server is accessed via a Routed Tunnel.

To configure a VLAN for DNS proxy:

1. Go to **Infrastructure** > **Connectors** > **Edge**> **Sites**.
2. In the **Site Name** column, click the name of the site you want to configure.
3. Click the **VLANs** tab.
4. Click the **Gear** icon in the row for the desired VLAN, and then select **Use for DNS Proxy**. See image.
  - A **DNS Proxy** tag appears in the **Features** column for the selected VLAN.

You can designate only one VLAN per site. Selecting a different VLAN will automatically clear the previous selection.

## Configuring a VLAN with Ebond

To configure a VLAN with an ebond interface, complete the following steps in the Zscaler Admin Console:

- 1. Create or clone a template.
- 2. Add a bonding interface.
- 3. Add a VLAN.
- 4. Configure the switch.
- 5. Verify the ebond status.

Templates are the prerequisites for [adding a new site](https://help.zscaler.com/zero-trust-branch/adding-site) in Zero Trust Branch. For [bonding interfaces](https://help.zscaler.com/zero-trust-branch/understanding-bonding-interfaces), create a new custom template or clone a default template. To learn more, see [Managing Templates](https://help.zscaler.com/zero-trust-branch/managing-templates).

1. Go to **Infrastructure**>**Connectors**>**Edge**>**Site** **Templates**.
2. On the **Templates** page, click the required template name. See image.
3. On the **Template** page, click the **Interfaces** tab, click **Add**, and then select **Bonding Interface**. See image.
4. In the **Add Bonding Interface** window: See image. Bonding interfaces support tagging (with 802.1Q, sub-interfaces) for both LAN and WAN. After the bonding interface is created, it appears under the **Interfaces** tab for the template.
  - **Name**: Select the bonding interface name (**ebond0** or **ebond1**) from the drop-down menu.
  - **Gateway**: Specify the Zero Trust Branch gateway associated with the bonding interface.
  - **Interface Type**: Select the type of physical interface (**None**, **LAN**, or **WAN**) included in the bonding interface.
  - **Member Interfaces**: Select the physical interfaces that must be members of the bonding interface. These interfaces are aggregated to form one virtual link with combined throughput and failover capabilities. Members must have the same port speed.

The template with the bonding interface is now available to [add a site](https://help.zscaler.com/zero-trust-branch/adding-site).

After the [Zero Trust Branch gateway is registered](https://help.zscaler.com/product-usage-terms/hardware-rma), you must add the VLANs as the switched virtual interface (SVI) moved to Zero Trust Branch. To learn more, see [Configuring a VLAN](https://help.zscaler.com/zero-trust-branch/configuring-vlan).

The ebond interface is now available for use.

The following example shows how to configure a port channel on a Cisco 3560 switch. In this configuration, two physical FastEthernet interfaces, FastEthernet0/1 and FastEthernet0/4, are combined to form a single logical port channel, which connects to a ZT800. This setup uses the Link Aggregation Control Protocol (LACP) for dynamic link aggregation.
The following is a sample for reference only. It is recommended to use the appropriate parameters based on the switch model and network environment you are working with.

```
interface Port-channel1
switchport trunk encapsulation dot1q
switchport trunk allowed vlan 10,20
switchport mode trunk
!
interface FastEthernet0/1
switchport trunk encapsulation dot1q
switchport trunk allowed vlan 10,20
switchport mode trunk
channel-group 1 mode active
!
interface FastEthernet0/4
switchport trunk encapsulation dot1q
switchport trunk allowed vlan 10,20
switchport mode trunk
channel-group 1 mode active
!
```

1. Verify the bonding interface status in the console. See image.
2. Verify the Cisco 3560 physical and port-channel interfaces. The following is a sample. You can modify the verification as required.

```
#show interfaces fastEthernet 0/1
	FastEthernet0/1 is up, line protocol is up (connected) 
	Hardware is Fast Ethernet, address is bc16.f5ca.f301 (bia bc16.f5ca.f301)
	MTU 1500 bytes, BW 100000 Kbit/sec, DLY 100 usec, 
	reliability 255/255, txload 1/255, rxload 1/255
	Encapsulation ARPA, loopback not set
	Keepalive set (10 sec)
	Full-duplex, 100Mb/s, media type is 10/100BaseTX
	input flow-control is off, output flow-control is unsupported 
	ARP type: ARPA, ARP Timeout 04:00:00
	Last input 00:00:20, output 00:00:02, output hang never
	Last clearing of "show interface" counters never
	Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0
	Queueing strategy: fifo
	Output queue: 0/40 (size/max)
	5 minute input rate 0 bits/sec, 0 packets/sec
	5 minute output rate 0 bits/sec, 0 packets/sec
	592 packets input, 147949 bytes, 0 no buffer
	Received 585 broadcasts (585 multicasts)
	0 runts, 0 giants, 0 throttles 
	272 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored
	0 watchdog, 585 multicast, 0 pause input
	0 input packets with dribble condition detected
	4120 packets output, 1279978 bytes, 0 underruns
	0 output errors, 0 collisions, 2 interface resets
	0 unknown protocol drops
	0 babbles, 0 late collision, 0 deferred
	0 lost carrier, 0 no carrier, 0 pause output
                    0 output buffer failures, 0 output buffers swapped out
```

```
#show interfaces fastEthernet 0/4
                    FastEthernet0/4 is up, line protocol is up (connected) 
                    Hardware is Fast Ethernet, address is bc16.f5ca.f304 (bia bc16.f5ca.f304)
                    MTU 1500 bytes, BW 100000 Kbit/sec, DLY 100 usec, 
                    reliability 255/255, txload 1/255, rxload 1/255
                    Encapsulation ARPA, loopback not set
                    Keepalive set (10 sec)
                    Full-duplex, 100Mb/s, media type is 10/100BaseTX
                    input flow-control is off, output flow-control is unsupported 
                    ARP type: ARPA, ARP Timeout 04:00:00
                    Last input 00:00:16, output 00:00:00, output hang never
                    Last clearing of "show interface" counters never
                    Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0
                    Queueing strategy: fifo
                    Output queue: 0/40 (size/max)
                    5 minute input rate 0 bits/sec, 0 packets/sec
                    5 minute output rate 0 bits/sec, 0 packets/sec
                    542 packets input, 123192 bytes, 0 no buffer
                    Received 484 broadcasts (484 multicasts)
                    0 runts, 0 giants, 0 throttles 
                    12 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored
                    0 watchdog, 484 multicast, 0 pause input
                    0 input packets with dribble condition detected
                    6862 packets output, 1036513 bytes, 0 underruns
                    0 output errors, 0 collisions, 1 interface resets
                    0 unknown protocol drops
                    0 babbles, 0 late collision, 0 deferred
                    0 lost carrier, 0 no carrier, 0 pause output
                    0 output buffer failures, 0 output buffers swapped out
```

```
#show running-config interface port-channel 1 
                    Building configuration...
                    Current configuration : 129 bytes
                    !
                    interface Port-channel1
                    switchport trunk encapsulation dot1q
                    switchport trunk allowed vlan 10,20
                    switchport mode trunk
                    end
                    #show etherchannel summary 
                    Flags: D - down P - bundled in port-channel
                    I - stand-alone s - suspended
                    H - Hot-standby (LACP only)
                    R - Layer3 S - Layer2
                    U - in use f - failed to allocate aggregator
                    M - not in use, minimum links not met
                    u - unsuitable for bundling
                    w - waiting to be aggregated
                    d - default port
                    Number of channel-groups in use: 1
                    Number of aggregators: 1
                    Group Port-channel Protocol Ports
                    ------+-------------+-----------+---------------------------------------------
	1 Po1(SU) LACP Fa0/1(P) Fa0/4(P)
```

Proxmox simulates endpoints that are microsegmented by the ZT800 appliance and connected via the bonding interface.

[Image: VLANs tab showing VLAN list with the Gear icon clicked and annotation for the Use for DNS Proxy option.]

[Image: Verify the bonding interface status]

[Image: View the list of templates]

[Image: Click the Interfaces tab and add a bonding interface]

[Image: Configure the bonding interface]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/configuring-zero-trust-branch-site-site-connectivity-over-routed-tunnels","lastmod":"2026-07-15T16:26Z","nid":"1532667"} -->
## Configuring Zero Trust Branch Site-to-Site Connectivity Over Routed Tunnels

- Source: https://help.zscaler.com/zero-trust-branch/configuring-zero-trust-branch-site-site-connectivity-over-routed-tunnels
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust SD-WAN > Configuring Zero Trust Branch Site-to-Site Connectivity Over Routed Tunnels
- Last modified: 2026-07-15T16:26Z
- Summary: Information about configuring legacy applications running in branches for direct site-to-site connectivity.

Routed tunnels provide a secure way to connect branch locations over IP networks. Some applications, like VoIP phones, TACACS+, and Active FTP running in branches require direct source IP address visibility. Zscaler Zero Trust Branch supports remote site connectivity over Zero Trust Branch Routed Tunnels (RTs) and preserves the IP addresses required for these applications to function. The applications are deployed in a hub-and-spoke architecture, where a physical or virtual Zero Trust Branch appliance in a data center (the hub) and Zero Trust Branch appliances in the branch offices (the spokes) connect via the RTs. RTs use state cryptography to secure connections and are easy to implement.

Contact the Zscaler Account team for guidance on hub positioning.

Values from the example topology are used throughout this article for illustration purposes only.

## Topology

The following sections provide a diagram depicting the topology of site-to-site connectivity over RTs and a description of its components and flow.

- Topology diagram
- Topology details

[Image: Diagram showing two sites connected to two hubs via Routed Tunnel and BGP]

- The Pod30 and Pod50 sites are the spokes.
- For redundancy, both spokes are connected to a pair of identical hubs (hubA and hubB). The hub appliances do not communicate with each other.
- The following interfaces are available at each spoke and hub:
  | Spoke Interfaces |  |
  | --- | --- |
  | **Interface** | **Connection** |
  | s2s_overlay0 | Generic Routing Encapsulation (GRE) tunnel to hubA |
  | s2s_overlay1 | GRE tunnel to hubB |
  | wg_vpn_client0 | RT to hubA |
  | wg_vpn_client1 | RT to hubB |
  | ge2 | Physical interface |
  | Hub Interfaces |  |
  | --- | --- |
  | **Interface** | **Connection** |
  | s2s_overlay0 | GRE tunnel to Pod30 spoke |
  | s2s_overlay1 | GRE tunnel to Pod50 spoke |
  | wg_client | RT to Pod50 spoke |
  | ge2 | Physical interface |

## Configuration

Perform the following steps to configure BGP and establish peering between the spokes and hubs:

- Step 1: Add Routed Tunnel Hubs
- Step 2: Activate the Gateways
- Step 3: View the Hubs
- Step 4: Identify Spokes
- Step 5: Configure the RTs for the Spokes
- Step 6: Enable Sharing Over RTs
- Step 7: Add a Policy-Based Routing Rule for the Spokes

Hubs can be physical or virtual Zero Trust Branch appliances. They are typically deployed in your data center. Launch hubs using the same image as the Zero Trust Branch appliance.

Routed tunnel hubs cannot be deployed in a public cloud.

To add a new hub:

1. Go to **Infrastructure**> **Connectors**> **Edge**> **Hubs**.
2. Click **Add On-Prem Hub**. See image.
3. In the **Add On-Prem Hub** drawer: See image.
  - **Location**: Select **Add New Location**.
  - **Name**: Enter a name for the location.
  - **Gateway Name**: Enter a name for the gateway.
  - **User Reachable IP**: Enter the IP address for the gateway. This is the address the spokes use to communicate with the hub. If the hub and spokes at the branch site are separated by an external network such as the internet, enter the post-NAT address of the hub seen on the internet. Make sure the IP address is entered correctly. An incorrect configuration can cause unexpected results or an end-to-end connectivity failure.
4. Click **Add**.
5. Copy and save the activation code that displays. You use this code when you activate the gateway. See image.
6. [Add a site.](https://help.zscaler.com/zero-trust-branch/adding-site)

[Image: Hubs page with an Add On-Prem Hub button]

[Image: Add On-Prem Hub drawer with fields for location, name, gateway name, and user-reachable IP address]

[Image: Add On-Prem Hub drawer displaying the activation code to paste when you activate the gateway]

To review the ports that need to be open, see [Open Ports on the Upstream Device](https://help.zscaler.com/zero-trust-branch/deploying-zero-trust-branch-appliance#OpenPorts).

The activation process uses the activation code and URL that you saved during the [Adding a Site](https://help.zscaler.com/zero-trust-branch/managing-sites#adding-a-site) procedure in the Zscaler Admin Console. If you cannot find this information:

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Sites**.
2. In the row for the required site, click the **Send Activation Link** icon. See image.

Zscaler supports one subinterface per WAN interface. However, Zero Trust Branch activation is supported over the WAN interface on the main interface (which is tagged), not on a subinterface (which is untagged).

There are two ways to activate a Zero Trust Branch appliance.

- Activation Code Method
- Activation URL Method

This method uses the activation code that you saved during the [Adding a Site](https://help.zscaler.com/zero-trust-branch/managing-sites#adding-a-site) procedure in the Zscaler Admin Console. Before you activate the appliance, you must configure it with the same parameters you configured when you created the site in the Zscaler Admin Console.

This procedure uses these example values:

- **WAN interface**: ge3 set for DHCP (This interface is hard-coded.)
- **DNS servers**: 1.1.1.1 and 8.8.8.8
- **Web proxy**: hub3.goairgap.com:1883

- a. Configure the network.
- b. Configure the web proxy.
- c. Activate the appliance.

This method uses the URL that you saved during the [Adding a Site](https://help.zscaler.com/zero-trust-branch/managing-sites#adding-a-site) procedure in the Zscaler Admin Console. The URL contains the configuration data for the WAN interface and the activation code.

1. Connect a laptop directly to the [Management port (GE1)](https://help.zscaler.com/zero-trust-branch/zero-trust-branch-physical-port-mapping#ZT400) on the appliance. The laptop automatically gets an IP address directly from the appliance. See image.
2. Open a browser on the laptop and paste the URL that you copied when you created the site. After the appliance processes the configuration, it displays an **Activation is completed** message on the web page. See image.
3. In the Zscaler Admin Console, go to **Infrastructure** > **Connectors** > **Edge** > **Sites**and click the site in the **Site Name**column. In the site details, verify that the appliance is activated. The **State**field value for an activated appliance is: Show image.
  - **Standalone**for a standalone appliance
  - **Active**for the primary node of an HA cluster
  - **Standby**for the secondary node of an HA cluster

1. In the console, enter `2` (Configure Gateway) from the menu and press `Enter`. See image.
2. Enter `1` (Configure Network) from the next menu and press `Enter`. See image.
3. Respond to the prompts to complete the network configuration. See image.

1. In the console, press `Enter` to return to the main menu.
2. Enter `3` (Configure Web Proxy) and press `Enter`. See image.
3. Respond to the prompts to complete the web proxy configuration. See image.

1. In the console, press `Enter` to return to the main menu.
2. Enter `1` (Activate Gateway) and press `Enter`. See image.
3. Enter `1` (Activate Airgap Gateway) from the next menu and press `Enter`. See image.
4. Enter the activation code that you saved when you created the site and press `Enter`. See image.
5. Enter `2` (Main Menu) and press `Enter`. The screen shows that the appliance is activated. See image.

[Image: Option 2 - Configure Gateway in console menu]

[Image: Option 1 - Configure Network option in console menu]

[Image: Network configuration wizard in console with prompts and responses]

[Image: Option 3 - Configure Web Proxy in console menu]

[Image: Web proxy configuration wizard in console with prompts and responses]

[Image: Option 1 - Activate Gateway option in the console menu]

[Image: Option 1 - Activate Airgap Gateway in the console menu]

[Image: Console output with the gateway activation code]

[Image: Console displaying the activated gateway]

[Image: Browser showing that the gateway activation completed]

[Image: Site details showing a green check mark icon showing the gateway is activated]

[Image: Send Activation Link icon at the end of the row for a site on the Sites page]

[Image: Diagram showing a laptop directly connected to the GE1 management port on the appliance and the appliance connected to the internet via the GE3 WAN port. The laptop receives the 192.168.0.0/24 address from the appliance.]

To view the hubs and the activated gateways, go to **Infrastructure** > **Connectors** > **Edge**> **Hubs.**

See image.

To identify the spokes:

1. Go to **Infrastructure** > **Connectors** > **Edge**> **Sites**.
2. Note the pods in the **Site Name** column. In this example, Pod30 and Pod50 are the spokes that BGP advertises over the routed tunnel. See image.

Configure the RT from each spoke, terminating at the primary and secondary hubs.

To configure the routed tunnels:

1. Go to **Infrastructure** > **Connectors** > **Edge**> **Sites** and select the first spoke.
2. Click **Settings**> **Routed Tunnel (RT)**.
3. Select the primary and secondary hubs and the WAN interfaces used to reach them. See image
4. Enable **Connect To Hub**.
5. Click **Save Changes**.
6. Repeat these steps for the second spoke. See image.

Spokes enable sharing over RTs at the virtual LAN (VLAN) level. These encrypted routed tunnels advertise the BGP routes.

- To enable sharing over RTs for a VLAN:
- To assign a VLAN for the source IP address of the DNS proxy:
- To enable sharing over RTs for a static route:

1. Go to **Infrastructure** > **Connectors** > **Edge**> **Sites**.
2. In the **Site Name** column, click the name of the site you want to configure.
3. Click the **Settings** tab, then click **Static Routes**.
  - Enable **Share Over RT**. See image.
  - Click **Add route** for the site. To learn more, see [Managing Sites](https://help.zscaler.com/zero-trust-branch/managing-sites).

1. Go to **Infrastructure** > **Connectors** > **Edge**> **Sites**.
2. In the **Site Name** column, click the name of the site you want to configure. See image.
  1. Click the **VLANs** tab.
  2. Click the **Gear** icon of the desired VLAN at the end of the row, and select **Use for DNS Proxy**. This ensures that DNS queries sent over the RT use the IP address of this specific VLAN as the source.

1. Go to **Infrastructure** > **Connectors** > **Edge**> **Sites** and select the first spoke.
2. Do one of the following, depending on your Zero Trust Branch version:
  - Click **VLANs**and enable **Share Over RT**. See image.
  - Click **VLANs**, click the **Gear** icon, then select **Share on Routed Tunnel**. See image.
3. Repeat these steps for the second spoke.

Configure a policy-based routing (PBR) rule on each spoke to direct traffic over a site-to-site VPN. To learn more, see [Configuring Routing Policies](https://help.zscaler.com/zero-trust-branch/configuring-routing-policies).

To configure a policy-based rule:

1. Go to **Infrastructure** > **Connectors** > **Edge**> **Sites**and select the first spoke.
2. Do one of the following:
  - If you are adding a new route, click **Routing Policy** > **Configure**> **Add Route**.
  - If you are editing an existing rule, click **Routing Policy**, click the **Gear**icon at the end of the row, and then click the **Edit** icon.
3. Complete the **Add Routing Rule** or **Edit Routing Rule** panel, being sure to select **VPN**as the **Nexthop Interface Type**. See image.
4. Click **Save**.
5. Repeat these steps for the second spoke, if that spoke initiates traffic. See image.
6. View the configured rules from both spokes. See image.

Only spokes that initiate traffic require rules. For example, if the Pod30 spoke initiates traffic to the Pod50 spoke, but not vice versa, only Pod30 needs a rule. Response traffic is handled automatically.

[Image: Hubs page showing the hub names, gateway names, state, user-reachable IP address, and Zero Trust Branch version]

[Image: Sites page showing the sites (known as spokes in this article), templates used to create the sites, ZIA location, gateway name, state, IP address, and Zero Trust Branch version]

[Image: Routed Tunnel configuration to create a routed path between the Pod30 spoke and the two hubs]

[Image: Routed Tunnel configuration to create a routed path between the Pod50 spoke and the two hubs]

[Image: Pod30 VLANs tab, with the Share Over RT switch enabled]

[Image: VLAN tab showing VLAN list with the Gear icon clicked and annotation for the Use for DNS Proxy option.]

[Image: Site Static Routes page listing routes and annotation for Share Over RT toggle.]

[Image: VLANs tab with the Share on Routed Tunnel menu item selected from the Gear icon]

[Image: Routing rule configuration for traffic flowing from Pod30 to Pod50]

[Image: Pod30 Routing Policy tab showing the rule number and name, Pod30 as source, Pod50 as destination, any protocols, and the gateway]

[Image: Pod50 Routing Policy tab showing the rule number and name, Pod50 as source, Pod30 as destination, any protocols, and the gateway]

[Image: Routing rule configuration for traffic flowing from Pod50 to Pod30]

## Verification

The following sections describe how to verify site-to-site connectivity over routed tunnels.

You run the CLI commands in these sections from the Zscaler Admin Console. To open the console, click the **>_Console** tab on the site details page.

See image.

- Verify the Hub
- Verify the Spoke
- Verify Routed Tunnel Traffic Flow

Run the commands in the following sections to verify the hub:

- Hub status
- PBR rule configuration
- Virtual Routing and Forwarding (VRF) status
- BGP peering with spokes over site-to-site GRE tunnels
- Peer uptime
- Routes advertised by spokes via s2s_overlay GRE tunnels
- Bidirectional Forwarding Detection (BFD) and BGP peering with spokes

To verify the hub, run the `ipconfig` command from the Zscaler Admin Console.

Physical interface:

```
ztb-user--pod-40-hubA--pod-40-hubA:~$ifconfig ge2
ge2
: flags=4161<
UP
, BROADCAST,
RUNNING
, MULTICAST> mtu 1500
...
```

GRE tunnel-facing spokes:

```
ztb-user--pod-40-hubA--pod-40-hubA:~$ifconfig s2s_overlay0
s2s_overlay0
: flags=193<
UP
,
RUNNING
, NOARP> mtu 1256
...
ztb-user--pod-40-hubA--pod-40-hubA:~$ifconfig s2s_overlay1
s2s_overlay1
: flags=193<
UP
,
RUNNING
, NOARP> mtu 1256
...
```

RT-facing spokes:

```
ztb-user--pod-40-hubA--pod-40-hubA:!$ifconfig wg_client
wg_client
: flags=209<
UP
, POINTOPOINT,
RUNNING
, NOARP> mtu 1420
 ...
```

To confirm that the policy-based rule is configured correctly, run the `ip rule` command from the Zscaler Admin Console.

Table 500 manages site-to-site tunnels (s2s overlays).

```
user-test--pod-40-hubA--pod-40-hubA:~$docker exec vyos_container su - vyos sh -c 'ip rule'
1000:     from all lookup [13mdev-table] 
1999:     from all goto 2001
2000:     from all lookup [13mdev-table] unreachable  
2001:     from all lookup local
30000:    from all lookup main suppress_prefixlength 0
30100:    from all lookup 500
32765:    from all lookup local 
32766:    from all lookup main
32767:    from all lookup default
```

To verify that the site-to-site BGP Virtual Routing and Forwarding (VRF) on both hubs is up, run the `show vrf` command for each hub from the Zscaler Admin Console.

```
user-test--pod-40-hubA--pod-40-hubA:~$ sudo docker exec vyos_container su - vyos sh -c '/opt/vyatta/bin/vyatta-op-cmd-wrapper show vrf'
Name     State MAC address         Flags                      Interfaces
—---     —---- —----------------   —------------------------  —-----------------------------------  
vrf-s2s
up
5e:cx:2x:ex:8x:cx   noarp,master,up, lower_up  s2s_overlay0,s2s_overlay1,pim6reg500

user-test--pod-40-hubB--pod-40-hubB:~$ sudo docker exec vyos_container su - vyos sh -c '/opt/vyatta/bin/vyatta-op-cmd-wrapper show vrf'
Name     State MAC address         Flags                      Interfaces
—---     —---- —----------------   —------------------------  —-----------------------------------  
vrf-s2s
up
5x:cx:2x:ex:8x:cx   noarp,master,up, lower_up  s2s_overlay0,pim6reg500,s2s_overlay1
```

To verify that the hubs established BGP peering with spokes over site-to-site GRE tunnels, run the `ip neighbor | grep` command for each hub from the Zscaler Admin Console.

```
user-test--pod-40-hubA--pod-40-hubA:~$ docker exec vyos_container su - vyos sh -c 'ip neighbor | grep s2s'
100.64.219.144 dev s2s_overlay0 1laddr 100.64.174.92 PERMANENT
                                                                                                                                                                                                                            100.64.233.132 dev s2s_overlay0 1laddr 100.64.141.18 PERMANENT
```

```
user-test--pod-40-hubB--pod-40-hubB:~$ docker exec vyos_container su - vyos sh -c 'ip neighbor | grep s2s'
100.65.216.183 dev s2S_overlay1 Iladdr 100.64.151.96 PERMANENT
```

To verify that the hubs established BGP peering with the spokes and to view the peer uptime, run the `show ip bgp vrf vrf-s2s summary` command from the Zscaler Admin Console.

```
user-test--pod-40-hubA--pod-40-hubA:~$ sudo docker exec vyos_container su - vyos sh -c '/opt/vyatta/bin/vyatta-op-cmd-wrapper show ip bgp vrf vrf-s2s summary'
IPv4 Unicast Summary (VRF vrf-s2s) :
BGP router identifier 100.65.195.202, local AS number 229 vrf-id 26
BGP table version 2
RIB entries 3, using 576 bytes of memory
Peers 4, using 82 KiB of memory
Neighbor           V      AS     MsgRevd   MsgSent     TblVer    InQ    OutQ      Up/Down  State/PfxRcd   PfxSnt Desc
100.64.219.144     4     219        1724      1725          2      0       0
1d04h34m
1        2  N/A
100.64.233.132     4     221        1726      1725          2      0       0
1d04h34m
1        2  N/A
```

To view the hub BGP routing table and the BGP routes advertised by the spokes reachable via s2s_overlay GRE tunnels, run the `ip route show table 500` command for each hub from the Zscaler Admin Console.

```
user-test--pod-40-hubA--pod-40-hubA:~$sudo docker exec vyos_container su - vyos sh -c '/opt/vyata/bin/vyatta-op-cmd-wrapper show ip route table 500'
...
VRF default table 500:
B›* 10.90.30.0/24 [20/0] via 100.64.233.132, s2s_overlay0, weight 1, 1d04h36m
B>* 10.90.50.0/24 [20/0] via 100.64.219.144, s2s_overlay0, weight 1, 1d04h37m
C>* 100.64.192.0/18 is directly connected, s2s_overlay0, 1004h44m
C>﻿* 100.65.192.0/18 is directly connected, s2s_overlay1, 1d04h44m
...
user-test--pod-40-hubB--pod-40-hubB:~$sudo docker exec vyos_container su - vyos sh -c '/opt/vyata/bin/vyatta-op-cmd-wrapper show ip route table 500'
...
VRF default table 500:
B›* 10.90.30.0/24 [20/0] via 100.65.216.183, s2s_overlay1, weight 1, 1d04h36m
B›* 10.90.50.0/24 [20/0] via 100.65.214.20, s2s_overlay1, weight 1, 1d04h36m
C>* 100.64.192.0718 1s directly connected, s2s_overlay0, 1004h44m 
C›* 100.65.192.0/18 is directly connected, s2s_overlay1, 1d04h44m
```

To view the hub's BFD and BGP peering with the spokes, run the `show configuration` command from the Zscaler Admin Console.

BFD detects link failure.

```
user-test--pod-40-hubA--pod-40-hubA:~$sudo docker exec vyos_container su - vyos sh -c '/opt/vyata/bin/vyatta-op-cmd-wrapper show configuration'
...
protocols {
bfd
{
peer 100.64.219.144
{
            interval {
                multiplier 50
            }
            source {
interface s2s_overlay0
}
            vrf vrf-s2s
       }
peer 100.64.233.132
{
            interval {
                multiplier 50
            }
            source {
interface s2s_overlay0
}
            vrf vrf-s2s
       }
peer 100.64.219.144
{
            interval {
                multiplier 50
            }
            source {
interface s2s_overlay1
}
            vrf vrf-s2s
       }
peer 100.64.233.132
{
            interval {
                multiplier 50
            }
            source {
interface s2s_overlay1
}
            vrf vrf-s2s
       }
    }
}
     vrf {
         name vrf-s2s {
             protocols {
bgp
{
...
neighbor 100.64.219.144
{        //BGP Neighbor (Spoke)
              address-family {
                  ipv4-unicast {
                      nexthop-self {
                      {
                  {
              {
              bfd {
              {
              remote-as external
              update-source s2s_overlap0
neighbor 100.64.233.132
}        //BGP Neighbor (Spoke)
              address-family {
                  ipv4-unicast {
                      nexthop-self {
                      }
                  }
              }
```

Run the commands in the following sections to verify the spokes:

- Spoke status
- PBR rule configuration
- Virtual Routing and Forwarding (VRF) status
- BGP peering with spokes over site-to-site GRE tunnels
- Peer uptime
- Routes advertised by spokes via s2s_overlay GRE tunnels
- Verify communication between the spokes

To verify the spokes, run the `ifconfig` command from the Zscaler Admin Console.

Physical interface:

```
user-test--Pod50--pod50-gw-st:~$ifconfig ge3.50
ge3.50
: flags=4163<
UP
, BROADCAST,
RUNNING
, MULTICAST> mtu 1500
...
```

GRE tunnel-facing hubs:

```
user-test--Pod50--pod50-gw-st:~$ifconfig s2s_overlay0
s2s_overlay0
: flags=193<
UP
,
RUNNING
, NOARP> mtu 1256
        inet 100.64.219.144 netmask 255.255.192.0
        ...
user-test--Pod50--pod50-gw-st:~$ifconfig s2s_overlay1
s2s_overlayl
: flags=193<
UP
,
RUNNING
, NOARP> mtu 1256
        inet 100.65.214.20 netmask 255.255.192.0
        ...
```

Routed tunnel-facing hubs:

```
user-test--Pod50--pod50-gw-st:~$ifconfig wg_vpn_client0
wg_vpn_client0
: flags=209<
UP
,POINTOP0INT,
RUNNING
, NOARP> mtu 1280
		inet 100.64.174.92 netmask 255.255.255.255 destination 100.64.174.92
		...
user-test--Pod50--pod50-gw-st:~$ifconfig wg_vpn_client1
wg_vpn_client1
: flags=209<
UP
,POINTOPOINT,
RUNNING
, NOARP> mtu 1280
		inet 100.64.154.189 netmask 255.255.255.255 destination 100.64.154.189
		...
```

Site-to-site VPN uses the `wg_vpn_client0` and `wg_vpn_client1` interfaces. The previous command output shows that site-to-site VPN is enabled.

To confirm that the policy-based rule is configured correctly, run the `ip rule` command for each spoke from the Zscaler Admin Console.

Table 500 manages site-to-site tunnels (s2s overlays).

```
user-test--Pod30--pod30-gw-st:~$ ip rule
1000:   from all lookup [13mdev-table]
1999:   from all goto 2001
2000:   from all lookup [13mdev-table] unreachable
2001:   from all lookup local
2100:   from all fwmark 0x800 lookup 500
3000:   from all fwmark 0x1000 lookup 300
                                                                                                                                                                                                                                                                                                                    ...
```

```
user-test--Pod50--pod50-gw-st:~$ ip rule
                                                                                                                                                                                                                                                                                        1000:   from all lookup [13mdev-table]
                                                                                                                                                                                                                                                                                        1999:   from all got 2001
                                                                                                                                                                                                                                                                                        2000:   from all lookup [13mdev-table]
                                                                                                                                                                                                                                                                                        2001:   from all lookup local
2100:   from all fwmark 0x800 lookup 500
3000:   from all fwmark 0x1000 lookup 300
...
```

To see a list of installed rules in order and the corresponding flow-mark of each rule, run the `iptables -nVL -t mangle` command from the Zscaler Admin Console.

```
user-test--Pod30--pod30-gw-st:~$ iptables -nVL -t mangle
Chain PREROUTING (policy ACCEPT 253K packets, 93M bytes)
pkts bytes target     prot opt in  out  source      destination
346K 120M PBR-RULES  all  --  *   *    0.0.0.0/0    0.0.0.0/0
527K  27M CONNMARK    all  --  *   *    0.0.0.0/0   0.0.0.0/0   ctdir ORIGINAL CONNMARK set 0x1000
...                                                                                                        
Chain POSTROUTING (policy ACCEPT 222K packets, 134M bytes)
pkts bytes target     prot opt in  out  source      destination
Chain PBR-RULES (1 references)
pkts bytes target     prot opt in  out   source       destination
0     0   MARK        all  --  *   *    0.0.0.0/0    0.0.0.0/0   ctdir ORIGINAL match-set
37 src match-set 38 dst MARK set 0x800
0     0   RETURN      all  --  *   *    0.0.0.0/0    0.0.0.0/0   mark match 0x800
Chain PBR-RULES-X (0 references)
pkts bytes target     prot opt in  out  source      destination
```

To see what is within the source, destination, and protocols in match sets 37 and 38, run the `ipset list 37` and `ipset list 38` commands for each spoke from the Zscaler Admin Console.

```
user-test--Pod30--pod30-80-gw-st:~$ ipset list 37
Name: 37
Type: hash:net
Revision: 6
Header: family inet hashsize 1024 maxelem 65536
Size in memory: 504
References: 1
Number of entries: 1
Members:
10.90.30.0/24
user-test--Pod30--pod30-gw-st:~$ ipset list 38
Name: 38
Type: hash:net
Revision: 6
Header: family inet hashsize 1024 maxelem 65536
Size in memory: 504
References: 1
Number of entries: 1
Members:
10.90.50.0/24
```

To verify that the site-to-site BGP VRF that was configured on each spoke is up, run the `show vrf` command from the Zscaler Admin Console.

```
user-test--Pod30--pod30-gw-st:~$ sudo docker exec vyos_container su - vyos sh -c '/opt/vyatta/bin/vyatta-op-cmd-wrapper show vrf'
Name      State    MAC address         Flags                         Interfaces
-------   -------  ----------------    ---------------------------   ------------------------------------
vrf-s2s
up
5e:cx:xx:ex:xx:x8   noarp, master, up, lower_up   pim6reg500,s2s_overlay0,s2s_overlay1

user-test--Pod50--pod50-gw-st:~$ sudo docker exec vyos_container su - vyos sh -c '/opt/vyatta/bin/vyatta-op-cmd-wrapper show vrf'
Name	 State	  MAC address	       Flags			              Interfaces
-------  -------  ---------------- 	   ---------------------------    ------------------------------------
vrf-s2s
up
5e:cx:xx:ex:xx:x8   noarp, master, up, lower_up    pim6reg500,s2s_overlay0,s2s_overlay1
```

To verify that the spokes established BGP peering through the primary and secondary hubs over site-to-site GRE tunnels, run the `ip neighbor | grep s2s` command for each spoke from the Zscaler Admin Console.

```
user-test--Pod30--pod30-gw-st:~$ ip neighbor | grep s2s
100.65.192.133 dev s2s_overlay1 1laddr 100.64.129.207 PERMANENT
100.64.195.202 dev s2s_overlay0 1laddr 100.64.128.68 PERMANENT

user-test--Pod50--pod50-gw-st:~$ ip neighbor | grep s2s
100.65.192.133 dev s2s_overlay1 1laddr 100.64.129.207 PERMANENT
100.64.195.202 dev s2s_overlay0 1laddr 100.64.128.68 PERMANENT
```

To verify that the spoke established BGP peering with primary and secondary hubs and view the peer uptime, run the `show ip bgp vrf vrf-s2s summary` command for each spoke from the Zscaler Admin Console.

```
user-test--Pod30--pod30-gw-st:~$ sudo docker exec vyos_container su - vyos sh -c '/opt/vyatta/bin/vyatta-op-cmd-wrapper show ip bgp vrf vrf-s2s summary'

IPv4 Unicast Summary (VRF vrf-s2s):
BGP router identifier 100.65.216.183, Local AS number 221 vrf-id 21
BGP table version 4
RIB entries 3, using 576 bytes of memory
Peers 4, using 82 KiB of memory

Neighbor	    V  AS	MsgRcvd	  MsgSent  TblVer InQ  OutQ  Up/Down  State/PfxRcd	PfxSnt Desc
100.64.195.202	4 229	    135	      136	 4     0     0
02:08:40
1       2 N/A
100.65.192.133	4 230	    138	      138	 4     0     0
02:09:04
1       1 N/A

user-test--Pod50--pod50-gw-st:~$ sudo docker exec vyos_container su - vyos sh -c '/opt/vyatta/bin/vyatta-op-cmd-wrapper show ip bgp vrf vrf-s2s summary'
...
Neighbor	    V  AS	MsgRcvd	  MsgSent  TblVer InQ  OutQ  Up/Down  State/PfxRcd	PfxSnt Desc
100.64.195.202	4 229	    128	      129	  2    0     0
02:01:37
1       2 N/A
100.65.192.133	4 230	    128	      130	  2    0     0
02:01:04
1       1 N/A
```

To view the spoke BGP routing table and the BGP route advertised by the remote spoke reachable via the s2s_overlay0 GRE tunnel, run the `ip route show table 500` command for each spoke from the Zscaler Admin Console. In the first example, the Pod30 spoke sees the route advertised by Pod50. In the second example, the Pod50 spoke sees the route advertised by Pod30.

```
user-test--Pod30--pod30-gw-st:~$ ip route show table 500
10.90.50.0/24 nhid 67 via 100.64.195.202 dev S2S_overlay0 proto bgp metric 20
100.64.192.0/18 dev S2S_overlayo proto kernel scope link src 100.64.233.132
local 100.64.233.132 dev s2s_overlay0 proto kernel scope host src 100.64.233.132
broadcast 127.255.255.255 dev s2s_overlay0 proto kernel scope link src 100.64.233.132
...
user-test--Pod50--pod50-gw-st:~$ ip route show table 500
10.90.30.0/24 nhid 457 via 100.64.195.202 dev s2s_over layo proto bep metric 20
100.64.192.0/18 dev s2s_overlay0 proto kernel scope link src 100.64.219.144
local 100.64.219.144 dev s2s_overlay0 proto kernel scope host src 100.64.219.144
broadcast 100.64.255.255 dev s2s_overlay0 proto kernel scope link src 100.64.219.144
...
```

To verify connectivity between Pod30 and Pod50, ping the Pod30 host or an endpoint in Pod30 from an endpoint in Pod50. In this example, the `ip address show` command output shows the Pod30 host IP address.

```
admin30@
pod-30
-1nx-a:~$ ip address show
1: lo: <LOOPBACK, UP, LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/Loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
    inet6 ::1/128 scope host
        valid_lft forever preferred_lft forever
2: ﻿﻿﻿ens160: <BROADCAST, MULTICAST,UP, LOWER_UP> mu 1500 qdisc mq state UP group default qlen 1000
    link/ether 00:0c:29:db:81:c7 brd ff:ff:ff:ff:ff:ff
    altname enp3s0
    inet
10.90.30.2/32
brd 10.90.30.2 scope global dynamic ens160
       valid_lft 57353sec preferred_lft 57353sec
    inet6 fe80::6310:cd9c:af3c:2c30/64 scope link noprefixroute
        valid_lft forever_preferred_lft forever

admin50@
pod-50
-1nx-a:~$ ping 10.90.30.2
PING
10.90.30.2
(10.90.30.2) 56(84) bytes of data.
64 bytes from 10.90.30.2: icmp_seq=1 ttl=61 time=6.89 ms
64 bytes from 10.90.30.2: icmp_seq=2 ttl=61 time=1.38 ms
...
```

To verify that traffic is sent over the routed tunnel:

1. In the Zscaler Admin Console, go to **Monitoring & Logs** > **Flow Logs**.
2. View the highlighted information in the following excerpts from the flow log. See image.

[Image: Tab on the site details page that opens the Zscaler Admin Console]

This excerpt shows the source IP and destination IP for traffic flowing from Pod30 to Pod50:

[Image: Flow logs showing source and destination IPs]

This excerpt shows the destination IP for traffic flowing from Pod30 to Pod50:

[Image: Flow Logs showing destination IP]

This excerpt shows the interface through which traffic is sent over the routed tunnel:

[Image: Flow Logs showing traffic sent over the routed tunnel]

This excerpt shows the source IP for traffic flowing from Pod30 to Pod50:

[Image: Flow Logs showing source IP]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/creating-micro-subnets","lastmod":"2026-07-15T16:02Z","nid":"1538693"} -->
## Creating Micro-Subnets

- Source: https://help.zscaler.com/zero-trust-branch/creating-micro-subnets
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust Device Segmentation > Micro-Subnets > Creating Micro-Subnets
- Last modified: 2026-07-15T16:02Z
- Summary: How to create micro-subnets using Airgap+ and Zero Trust Branch.

Zero trust microsegmentation offers flexibility for per-device segmentation while continuing to reduce attack surfaces.

Zero Trust Branch appliances are used to create highly granular subnetworks called [micro-subnets](https://help.zscaler.com/zero-trust-branch/understanding-micro-subnets) with small IP address ranges (e.g., /27 to /30) within existing virtual local area networks (VLANs) and add endpoints that require direct L2 connectivity to these micro-subnets. Devices within a micro-subnet communicate directly, while traffic between different micro-subnets or external destinations is routed through the Zero Trust Branch gateway for inspection and controlled by enforcing policies.

To configure micro-subnets, complete the following steps in the Zscaler Admin Console:

- 1. Configure Zero Trust Branch as a DHCP server.
- 2. Add VLAN and enable Airgap+.
- 3. Update assets using a CSV file.
- 4. Verify assets.
- 5. Create a network object group.
- 6. Create a firewall policy.

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Sites**.
2. On the **Sites** page, click the required site in the **Site Name** column. See image.
3. Click the **Settings**tab and in the left-side panel, select **DHCP Service**. See image.
4. For **DHCP Service**, select **DHCP Server** from the drop-down menu.
5. Click **Save**.

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Sites**.
2. On the **Sites** page, click the required site in the **Site Name** column.
3. Click the **VLANs** tab.
4. For the required Airgap VLAN, click the **Gear** icon and select **Edit**. See image.
5. In the**Edit Airgap VLAN** window: See image.
  - **Enable Airgap+**: Enable and enter the following details in the fields that appear: While adding the VLAN to create a micro-subnet, the Airgap range cannot overlap with Airgap+. For example, 10.90.10.8 to 10.90.10.23 defines the range for /29 micro-subnets. 10.90.10.x/24 endpoints outside Airgap+ range are assigned with /32 IP addresses.
    - **Netmask**: Select the subnet mask that must be used to determine the network scope for the asset in Airgap+ mode.
    - **Airgap+ Range Start**: Enter the network address of the first micro-subnet.
    - **Airgap+ Range End**: Enter the broadcast address of the last micro-subnet.
6. Click **Save**.

This configuration creates the following two micro-subnets:

| Micro-Subnet | Network Address | Gateway | Available Network Addresses for Endpoint | Broadcast Address |
| --- | --- | --- | --- | --- |
| micro-subnet-1 | 10.90.10.8 | 10.90.10.9 | 10.90.10.10; 10.90.10.11; 10.90.10.12; 10.90.10.13; 10.90.10.14 | 10.90.10.15 |
| micro-subnet-2 | 10.90.10.16 | 10.90.10.17 | 10.90.10.18; 10.90.10.19; 10.90.10.20; 10.90.10.21; 10.90.10.22 | 10.90.10.23 |

Airgap+ is suited for deployments where:

- Some endpoints cannot get a /32 mask.
- The device IP address is flexible, because it must fit into the microsegments available for endpoints defined by the Airgap+ configuration.

If the device IP address is not flexible, use Airgap-Lite by changing the security protection at the asset level.

To change the security protection:

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Assets**.
2. On the **Assets** page, select the checkbox for the required **Device**, then click **Edit**.
3. Select **Network** from the **Segmentation Granularity** drop-down menu.
4. Click **Save**.

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Sites**.
2. On the **Sites** page, click the required site in the **Site Name** column.
3. Click the **VLANs** tab.
4. For the required Airgap VLAN, click the **Gear** icon and select **Import Endpoints CSV**. See image.
5. In the **Import Endpoints CSV** window, upload a CSV file. You can also download the template and use it to add the endpoints and then upload the CSV file. See image.
6. Click **Validate** to verify the imported data and view the details. Note that the IP addresses for the Airgap+ endpoints (vlan10-dev3 and vlan10-dev4) are used from the CSV file. For the endpoints that are not part of the micro-subnet (vlan10-dev1 and vlan10-dev2), the IP address field is empty and the /32 IP address is assigned by the DHCP server.
7. Click **Submit**.
8. In the confirmation window that appears, read the message and click **Confirm**. See image.

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Assets**.
2. On the **Assets** page, you can see that the assets in the micro-subnet (as per the imported CSV) are assigned with /29 IP addresses.

To create a network type object group for devices in the micro-subnet:

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Resources** > **Objects**.
2. On the **Objects** page, click **Add**, then select **Network** from the list. See image.
3. In the **Add Network**window: See image.
  - **Name**: Enter a name for the network.
  - Add a CIDR block or FQDN and click **+Add**.
  - **Member Groups**: Select the groups from the drop-down menu.
4. Click **Add**.

Create a firewall policy to restrict the Airgap+ endpoints' communication within the micro-subnets. To learn more, see [Understanding Firewall Policies](https://help.zscaler.com/zero-trust-branch/understanding-firewall-policies).

[Image: Click the Settings tab and select DHCP Service]

[Image: Select the Edit option for the required Airgap VLAN]

[Image: Add the Airgap+ details]

[Image: Import the endpoints using a CSV file]

[Image: Upload a CSV file]

[Image: Confirm that the CSV file can be uploaded]

[Image: Add a network]

[Image: Add the network details]

[Image: Click the required site in the Site Name column]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/creating-zero-trust-branch-enhanced-wan-edge-high-availability-cluster","lastmod":"2026-05-13T07:06Z","nid":"1535048"} -->
## Creating a Zero Trust Branch Enhanced WAN Edge High Availability Cluster

- Source: https://help.zscaler.com/zero-trust-branch/creating-zero-trust-branch-enhanced-wan-edge-high-availability-cluster
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Deploying Zero Trust Branch > High Availability Clusters > Creating a Zero Trust Branch Enhanced WAN Edge High Availability Cluster
- Last modified: 2026-05-13T07:06Z
- Summary: How to create a Zero Trust Branch enhanced high availability (WAN Edge HA) cluster.

A Zscaler Zero Trust Branch Enhanced high availability (HA) configuration, also known as a WAN Edge HA cluster, enhances the standard HA cluster by enabling an active-active traffic distribution for Zero Trust Branch appliances in an HA mode. By leveraging a dedicated WAN Transit Link between the active and standby nodes, Enhanced HA ensures both appliances actively participate in WAN traffic routing. By leveraging a dedicated WAN Transit Link between the active and standby nodes, it ensures both appliances actively participate in WAN traffic routing. This optimized configuration eliminates dependency on Layer 2 switches and ensures uninterrupted services while maintaining redundancy and load balancing under real world SD-WAN and high-traffic conditions.

Unlike a [standard HA cluster](https://help.zscaler.com/zero-trust-branch/creating-zero-trust-branch-high-availability-cluster) that operates in an active-standby mode, an Enhanced HA cluster allows both appliances to forward traffic via their WAN interfaces for traffic forwarding.

Enhanced HA does not support stateful failover for traffic routed through the secondary appliance. As a result, any active TCP, HTTP, or other session-based traffic is interrupted and must be re-established if it switches between the WAN links of the two different appliances.

The Enhanced HA feature offers the following key benefits:

- Forwards traffic concurrently from both active and standby appliances, maximizing bandwidth utilization.
- Ensures minimal disruptions during failovers, reboots, or appliance restarts.
- Eliminates the need for Layer 2 switches.
- Enables balanced traffic distribution across WAN links in active-active mode.
- Reverts seamlessly to standard HA upon a WAN transit link failure for uninterrupted service.

## Private Access (ZPA) App Connector Behavior in Enhanced HA Clusters

While the Zero Trust Branch gateway operates in an active-standby mode, the embedded Private Access App Connector in a WAN Edge HA deployment operates in an active-active mode. In these deployments, both App Connectors run concurrently on the active and standby Zero Trust Branch appliances, ensuring no service disruption during App Connector container restarts or appliance failovers. Both the primary (active) and secondary (standby) Zero Trust Branch appliances establish independent connections to the Private Access cloud, so if the primary node fails, the App Connector on the secondary Zero Trust Branch appliance is already available to process private application traffic, minimizing service disruption.

This active-active behavior applies only to the embedded App Connector in WAN Edge HA deployments.

## Prerequisites

Before deploying an Enhanced WAN Edge HA cluster, make sure the following prerequisites are met:

- Use only supported appliances (ZT600, ZT800, and ZT8010). The ZT400 appliance is not supported.
- Reserve a dedicated physical port for the WAN Transit Link on each appliance:
  - **ZT600**: Use port **GE5** on both appliances.
  - **ZT800**: Use port **GE5** on both appliances.
  - **ZT8010**: Use port **XE10** on both appliances.
- Connect the WAN Transit Link using a direct back‑to‑back Ethernet cable between the specified ports, or via a switch. If you use a switch for the connection, you must place the WAN Transit Link ports on a different VLAN from the standard HA control link.
- WAN links must be connected to separate ISPs for redundancy and failover support.

## Topology Diagram

The following sections provide a diagram depicting the topology of an Enhanced WAN Edge HA deployment and a description of its components and flow.

- Topology diagram
- Topology details

## Creating a Cluster

To create and activate a cluster, complete the following steps in the Zscaler Admin Console:

- Step 1: Add a Template
- Step 2: Add a Site
- Step 3: Activate the Gateways
- Step 4: Configure the Forwarding Policy

## Monitoring WAN Links

You can monitor the WAN links by running the following commands:

- To check the score of the WAN interfaces received from WAN monitoring:
  1. Go to **Infrastructure** > **Connectors** > **Edge** > **Sites**.
  2. Click the required site in the **Site Name** column.
  3. On the site details page, click the **Settings** tab, and set **Debug Options** to **High**.
  4. Click the **>_Console** tab and run the following command:
  5. Run the following command to check the score: `ztb-lab--Proctor02-KS--proctor02-ztb-1:~$cat /var/log/syslog | grep wan-mon`Sample output: `Sep 4 04:51:03 ztblab--Proctor02-KS--proctor02-ztb-1 network-poller[2718540]: wan-monitor - module_wanmon.py:697 - DEBUG - ======================================================================================================================================================= Sep 4 04:51:03 ztblab--Proctor02-KS--proctor02-ztb-1 network-poller[2718540]: wan-monitor - module_wanmon.py:700 - DEBUG - Intf: ge6 Sep 4 04:51:03 ztblab--Proctor02-KS--proctor02-ztb-1 network-poller[2718540]: wan-monitor - module_wanmon.py:701 - DEBUG - TX packets: 208720 Sep 4 04:51:03 ztblab--Proctor02-KS--proctor02-ztb-1 network-poller[2718540]: wan-monitor - module_wanmon.py:702 - DEBUG - RX packets: 208609 Sep 4 04:51:03 ztblab--Proctor02-KS--proctor02-ztb-1 network-poller[2718540]: wan-monitor - module_wanmon.py:703 - DEBUG - Global ctxt: pkt_loss: 0.0, pkt_latency: 3.96, pkt_jitter: 1.2, norm_loss: 0.0, norm_latency: 0.29, norm_jitter: 0.16, score: 25 Sep 4 04:51:03 ztblab--Proctor02-KS--proctor02-ztb-1 network-poller[2718540]: wan-monitor - module_wanmon.py:705 - DEBUG - Cum Resp : Interfaces: 2, quality: pkt_loss: 0.0, pkt_latency: 3.96, pkt_jitter: 1.2, norm_loss: 0.0, norm_latency: 0.29, norm_jitter: 0.16, score: 25 nw_loss: no`
- To check the route and verify which WAN interface is identified as the best among the available WAN interfaces: `ztb-lab--Proctor02-KS--proctor02-ztb-1:~$ip rule`Sample output: `0: from all lookup local 220: from all lookup 220 2001: from all lookup local 2100: from all fwmark 0x800 lookup p_ae410fa3f9f1_best ===> shows the best route table 2101: from all fwmark 0x601 lookup zia_ipsec_rt 2102: from all fwmark 0x801 lookup p_eb198e76db82 2103: from all fwmark 0x802 lookup p_053ec9af8551 2104: from all fwmark 0x803 lookup p_f0fe3e64da42 2105: from all fwmark 0x804 lookup p_7e89186e52ac 2106: from all fwmark 0x602 lookup zia_gre_rt 2601: from all fwmark 0x600 lookup 900 32766: from all lookup main 32767: from all lookup default`
- Next, check the metric for all the default routes:. `ztb-lab--Proctor02-KS--proctor02-ztb-1:~$ip route show table p_ae410fa3f9f1_best`Sample output: `default via 100.120.1.254 dev ge5 metric 10 ===> ge5 has the best metric default via 100.130.1.254 dev ge6 metric 20`
- To view the default routes for each WAN interface: `ztb-lab--Proctor02-KS--proctor02-ztb-1:~$ip route show | grep default`Sample output: `default via 100.120.1.254 dev ge5 proto dhcp src 100.120.1.183 metric 100 default via 100.130.1.254 dev ge6 proto dhcp src 100.130.1.50 metric 100`

The Enhanced HA topology includes two connections between the appliances: the standard HA link for control plane communication and the new WAN Transit Link for data plane traffic.

[Image: Topology diagram with two Zero Trust Branch appliances in the enhanced HA cluster, connected to each other via a LAN and the GE ports on a switch, and connected to the endpoint devices via a separate LAN]

- The Enhanced HA Cluster topology consists of two identical Zero Trust Branch appliances.
- The WAN Transit Link ensures active-active forwarding between appliances for WAN traffic, enabling balanced traffic distribution across appliances and ISPs.
- LAN segments connect to endpoints that require secure access and protection.
- Standby appliances actively forward traffic via WAN interfaces, while LAN interfaces remain in [standard HA mode](https://help.zscaler.com/zero-trust-branch/creating-zero-trust-branch-high-availability-cluster) (active-standby).
- Failover scenarios automatically revert to [standard HA](https://help.zscaler.com/zero-trust-branch/creating-zero-trust-branch-high-availability-cluster) using the HA link assigned for redundancy.

You must use an Enhanced HA template to launch a Zero Trust Branch cluster. You can use a default Enhanced HA template or create a custom template. To create a custom template:

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Site Templates**.
2. On the **Templates** page, complete the [Add a New Template](https://help.zscaler.com/zero-trust-branch/managing-templates#add) procedure, ensuring that you select **enhanced_mode_ha** for the **Deployment Type.** See image.

When you add a site in a Zero Trust Branch HA deployment, you configure a gateway for each appliance.

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Sites**.
2. On the **Sites** page, complete the [Add a Site](https://help.zscaler.com/zero-trust-branch/adding-site) procedure, ensuring that you select the Enhanced HA template, and configure both gateways. After the site is saved, you are returned to the **Sites** page.
3. Review the site details. Both gateways are in a **Disconnected**state because they have not been activated. See image.

The example used in this procedure shows how to activate one gateway and is not intended to represent the cluster setup described in this article. Repeat this procedure for the secondary node. The gateway that is activated first becomes the primary node and the other gateway becomes the secondary node. These primary and secondary roles are essential for the HA control plane to manage cluster health and failover.

To review the ports that need to be open, see [Open Ports on the Upstream Device](https://help.zscaler.com/zero-trust-branch/deploying-zero-trust-branch-appliance#OpenPorts).

The activation process uses the activation code and URL that you saved during the [Adding a Site](https://help.zscaler.com/zero-trust-branch/managing-sites#adding-a-site) procedure in the Zscaler Admin Console. If you cannot find this information:

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Sites**.
2. In the row for the required site, click the **Send Activation Link** icon. See image.

Zscaler supports one subinterface per WAN interface. However, Zero Trust Branch activation is supported over the WAN interface on the main interface (which is tagged), not on a subinterface (which is untagged).

There are two ways to activate a Zero Trust Branch appliance.

- Activation Code Method
- Activation URL Method

This method uses the activation code that you saved during the [Adding a Site](https://help.zscaler.com/zero-trust-branch/managing-sites#adding-a-site) procedure in the Zscaler Admin Console. Before you activate the appliance, you must configure it with the same parameters you configured when you created the site in the Zscaler Admin Console.

This procedure uses these example values:

- **WAN interface**: ge3 set for DHCP (This interface is hard-coded.)
- **DNS servers**: 1.1.1.1 and 8.8.8.8
- **Web proxy**: hub3.goairgap.com:1883

- a. Configure the network.
- b. Configure the web proxy.
- c. Activate the appliance.

This method uses the URL that you saved during the [Adding a Site](https://help.zscaler.com/zero-trust-branch/managing-sites#adding-a-site) procedure in the Zscaler Admin Console. The URL contains the configuration data for the WAN interface and the activation code.

1. Connect a laptop directly to the [Management port (GE1)](https://help.zscaler.com/zero-trust-branch/zero-trust-branch-physical-port-mapping#ZT400) on the appliance. The laptop automatically gets an IP address directly from the appliance. See image.
2. Open a browser on the laptop and paste the URL that you copied when you created the site. After the appliance processes the configuration, it displays an **Activation is completed** message on the web page. See image.
3. In the Zscaler Admin Console, go to **Infrastructure** > **Connectors** > **Edge** > **Sites**and click the site in the **Site Name**column. In the site details, verify that the appliance is activated. The **State**field value for an activated appliance is: Show image.
  - **Standalone**for a standalone appliance
  - **Active**for the primary node of an HA cluster
  - **Standby**for the secondary node of an HA cluster

1. In the console, enter `2` (Configure Gateway) from the menu and press `Enter`. See image.
2. Enter `1` (Configure Network) from the next menu and press `Enter`. See image.
3. Respond to the prompts to complete the network configuration. See image.

1. In the console, press `Enter` to return to the main menu.
2. Enter `3` (Configure Web Proxy) and press `Enter`. See image.
3. Respond to the prompts to complete the web proxy configuration. See image.

1. In the console, press `Enter` to return to the main menu.
2. Enter `1` (Activate Gateway) and press `Enter`. See image.
3. Enter `1` (Activate Airgap Gateway) from the next menu and press `Enter`. See image.
4. Enter the activation code that you saved when you created the site and press `Enter`. See image.
5. Enter `2` (Main Menu) and press `Enter`. The screen shows that the appliance is activated. See image.

[Image: Option 2 - Configure Gateway in console menu]

[Image: Option 1 - Configure Network option in console menu]

[Image: Network configuration wizard in console with prompts and responses]

[Image: Option 3 - Configure Web Proxy in console menu]

[Image: Web proxy configuration wizard in console with prompts and responses]

[Image: Option 1 - Activate Gateway option in the console menu]

[Image: Option 1 - Activate Airgap Gateway in the console menu]

[Image: Console output with the gateway activation code]

[Image: Console displaying the activated gateway]

[Image: Browser showing that the gateway activation completed]

[Image: Site details showing a green check mark icon showing the gateway is activated]

[Image: Send Activation Link icon at the end of the row for a site on the Sites page]

[Image: Diagram showing a laptop directly connected to the GE1 management port on the appliance and the appliance connected to the internet via the GE3 WAN port. The laptop receives the 192.168.0.0/24 address from the appliance.]

On the **Sites**page, you can observe the activation process, which goes through several states, such as **Initializing**and **Post-update**. When both gateways are activated, the primary gateway state is **Active**and the secondary gateway state is **Standby**.

In an Enhanced HA cluster, the UI shows one appliance as **Active** and the other as **Standby** to indicate their control‑plane roles, but both appliances can forward WAN traffic.

See image.

Configure the WAN links on both appliances:

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Sites**.
2. On the **Sites** page, click the site configured for Advanced HA.
3. Go to the **Forwarding Policy** tab and click **Configure**.
4. Click **Add Route** or edit an existing route.
5. Configure the source, destination, and other criteria for the traffic to load balance.
6. Scroll down to the **WAN HA Mode** section. (This section only appears for sites using an **Advanced HA** template.) See image.
  1. Select **Enhanced**. This enables the active-active routing functionality.
  2. From the **Next Hop Interface Type** section, select **WAN** interfaces for both Appliance 1 (primary) and Appliance 2 (secondary).
  3. Choose a **Traffic Distribution** method:
    - **Balanced**: This enables true active-active load balancing. Traffic is distributed across all selected WAN links on both appliances. The **Failover Group** shows as **Active-Active** **Links**.
    - **Best**: The cluster sends traffic to the single best-performing WAN link at any given time, regardless of whether it is on the primary or secondary appliance. The **Failover Group** shows as**Active-Standby Links**.
    - **None**: The system selects a path without active direction.
  4. Click **Save**.

After saving the rule, the cluster begins forwarding traffic matching the policy across the WAN links of both appliances according to the distribution method you selected.

[Image: WAN HA Mode UI with Enhanced selected. Traffic is balanced across Next Hop interfaces from primary & secondary appliances.]

[Image: Add Template panel showing configuration of a new custom template for an HA cluster]

[Image: Sites page details for an HA cluster where both gateways are in the Disconnected state because they are not activated]

[Image: Sites page details for an HA cluster where both gateways are activated. One gateway is in the Active state and the other gateway is in the Standby state.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/creating-zero-trust-branch-high-availability-cluster","lastmod":"2026-05-17T07:06Z","nid":"1533622"} -->
## Creating a Zero Trust Branch High Availability Cluster

- Source: https://help.zscaler.com/zero-trust-branch/creating-zero-trust-branch-high-availability-cluster
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Deploying Zero Trust Branch > High Availability Clusters > Creating a Zero Trust Branch High Availability Cluster
- Last modified: 2026-05-17T07:06Z
- Summary: How to create a Zero Trust Branch high availability (HA) cluster

A Zscaler Zero Trust Branch high availability (HA) cluster consists of two identical Zero Trust Branch appliances (or *nodes*). One node is the primary node and has the active role. The other node is the secondary node and has the standby role. If the primary node fails, the secondary node takes over as the primary, active node. The nodes coordinate stateful failover and traffic handling using protocols such as traffic session state synchronization and Virtual Router Redundancy Protocol (VRRP).

- Traffic session state synchronization enables seamless, stateful failover between the nodes. This ensures uninterrupted service for session-based applications such as SSH, SCP, FTP, and HTTP during node failovers. The session state information on the primary node is continuously synchronized to the secondary node to ensure that it has up-to-date session data to seamlessly continue traffic processing if the primary node fails.
- VRRP determines the active and standby roles of the nodes, monitors the health and availability of the nodes, and initiates a failover when necessary. When VRRP detects a node failure and triggers a failover, the new active node uses the synchronized session data to maintain ongoing traffic sessions without disruption.

## ZPA App Connector Behavior in HA Clusters

While the Zero Trust Branch gateway operates in an active-standby mode, the embedded Private Access (ZPA) App Connector on each appliance in a WAN Edge HA cluster (also known as Enhanced HA) operates in an active-active mode. In these deployments, both App Connector instances run concurrently on the active and standby Zero Trust Branch appliances, ensuring no service disruption during App Connector container restarts or appliance failovers. Both the primary (active) and secondary (standby) Zero Trust Branch appliances establish independent connections to the ZPA cloud, so if the primary node fails, the App Connector on the secondary Zero Trust Branch appliance is already available to process private application traffic, minimizing service disruption.

This active-active behavior applies only to the embedded App Connector in [WAN Edge HA](https://help.zscaler.com/zero-trust-branch/creating-zero-trust-branch-enhanced-wan-edge-high-availability-cluster) deployments.

## Prerequisites

Make sure the following prerequisites are met:

- Dedicate an unused VLAN for HA only.
- Configure two ports on the switch for that VLAN.
- Connect a cable from the GE4 port of each appliance to one of the two ports on the switch. The two appliances are connected to each other via a switch, not via a crossover cable that connects them directly.

## Topology Diagram

The following sections provide a diagram depicting the topology of an HA cluster deployment and a description of its components and flow.

- Topology diagram
- Topology details

## Creating a Cluster

To create and activate a cluster, complete the following steps in the Zscaler Admin Console:

- Step 1: Add a Template
- Step 2: Add a Site
- Step 3: Activate the Gateways
- Step 4: (Optional) Configure VRRP

[Image: Topology diagram with two Zero Trust Branch appliances in the cluster, connected to each other via a LAN and the GE ports on a switch, and connected to the endpoint devices via a separate LAN]

- The LAN segment hosts the endpoints that the HA cluster manages and secures.
- The Zero Trust Branch HA cluster consists of two identical Zero Trust Branch appliances configured for HA.
- The appliances use a dedicated HA interface, and are connected to a switch via the GE4 port. TCP, UDP, and ICMP connections are synchronized between the nodes using this link. To learn more, see [Zero Trust Branch Physical Port Mapping](https://help.zscaler.com/zero-trust-branch/zero-trust-branch-physical-port-mapping). This is the only supported way to connect the appliances. If you connect the appliances to each other directly, the failover does not function properly.
- The WAN uplink allows secure communication with Zero Trust Branch management and other external networks.

You must use an HA template to launch a Zero Trust Branch cluster. You can use a default HA template or create a custom template. To create a custom template:

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Site Templates**.
2. On the **Templates** page, complete the [Add a New Template](https://help.zscaler.com/zero-trust-branch/managing-templates#add) procedure, ensuring that you select **standard_mode_ha** for the **Deployment Type.** See image.

When you add a site in a Zero Trust Branch HA deployment, you configure a gateway for each appliance.

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Sites**.
2. On the **Sites** page, complete the [Add a Site](https://help.zscaler.com/zero-trust-branch/adding-site) procedure, ensuring that you select the default HA template or a custom HA template, and configure both gateways. After the site is saved, you are returned to the **Sites** page.
3. Review the site details. Both gateways are in a **Pending Activation** or **Disconnected** state because they have not been activated. See image.

The example used in this procedure shows how to activate one gateway and is not intended to represent the cluster setup described in this article. Repeat this procedure for the secondary node. The gateway that is activated first becomes the primary node and the other gateway becomes the secondary node.

To review the ports that need to be open, see [Open Ports on the Upstream Device](https://help.zscaler.com/zero-trust-branch/deploying-zero-trust-branch-appliance#OpenPorts).

The activation process uses the activation code and URL that you saved during the [Adding a Site](https://help.zscaler.com/zero-trust-branch/managing-sites#adding-a-site) procedure in the Zscaler Admin Console. If you cannot find this information:

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Sites**.
2. In the row for the required site, click the **Send Activation Link** icon. See image.

Zscaler supports one subinterface per WAN interface. However, Zero Trust Branch activation is supported over the WAN interface on the main interface (which is tagged), not on a subinterface (which is untagged).

There are two ways to activate a Zero Trust Branch appliance.

- Activation Code Method
- Activation URL Method

This method uses the activation code that you saved during the [Adding a Site](https://help.zscaler.com/zero-trust-branch/managing-sites#adding-a-site) procedure in the Zscaler Admin Console. Before you activate the appliance, you must configure it with the same parameters you configured when you created the site in the Zscaler Admin Console.

This procedure uses these example values:

- **WAN interface**: ge3 set for DHCP (This interface is hard-coded.)
- **DNS servers**: 1.1.1.1 and 8.8.8.8
- **Web proxy**: hub3.goairgap.com:1883

- a. Configure the network.
- b. Configure the web proxy.
- c. Activate the appliance.

This method uses the URL that you saved during the [Adding a Site](https://help.zscaler.com/zero-trust-branch/managing-sites#adding-a-site) procedure in the Zscaler Admin Console. The URL contains the configuration data for the WAN interface and the activation code.

1. Connect a laptop directly to the [Management port (GE1)](https://help.zscaler.com/zero-trust-branch/zero-trust-branch-physical-port-mapping#ZT400) on the appliance. The laptop automatically gets an IP address directly from the appliance. See image.
2. Open a browser on the laptop and paste the URL that you copied when you created the site. After the appliance processes the configuration, it displays an **Activation is completed** message on the web page. See image.
3. In the Zscaler Admin Console, go to **Infrastructure** > **Connectors** > **Edge** > **Sites**and click the site in the **Site Name**column. In the site details, verify that the appliance is activated. The **State**field value for an activated appliance is: Show image.
  - **Standalone**for a standalone appliance
  - **Active**for the primary node of an HA cluster
  - **Standby**for the secondary node of an HA cluster

1. In the console, enter `2` (Configure Gateway) from the menu and press `Enter`. See image.
2. Enter `1` (Configure Network) from the next menu and press `Enter`. See image.
3. Respond to the prompts to complete the network configuration. See image.

1. In the console, press `Enter` to return to the main menu.
2. Enter `3` (Configure Web Proxy) and press `Enter`. See image.
3. Respond to the prompts to complete the web proxy configuration. See image.

1. In the console, press `Enter` to return to the main menu.
2. Enter `1` (Activate Gateway) and press `Enter`. See image.
3. Enter `1` (Activate Airgap Gateway) from the next menu and press `Enter`. See image.
4. Enter the activation code that you saved when you created the site and press `Enter`. See image.
5. Enter `2` (Main Menu) and press `Enter`. The screen shows that the appliance is activated. See image.

[Image: Option 2 - Configure Gateway in console menu]

[Image: Option 1 - Configure Network option in console menu]

[Image: Network configuration wizard in console with prompts and responses]

[Image: Option 3 - Configure Web Proxy in console menu]

[Image: Web proxy configuration wizard in console with prompts and responses]

[Image: Option 1 - Activate Gateway option in the console menu]

[Image: Option 1 - Activate Airgap Gateway in the console menu]

[Image: Console output with the gateway activation code]

[Image: Console displaying the activated gateway]

[Image: Browser showing that the gateway activation completed]

[Image: Site details showing a green check mark icon showing the gateway is activated]

[Image: Send Activation Link icon at the end of the row for a site on the Sites page]

[Image: Diagram showing a laptop directly connected to the GE1 management port on the appliance and the appliance connected to the internet via the GE3 WAN port. The laptop receives the 192.168.0.0/24 address from the appliance.]

On the **Sites**page, you can observe the activation process, which goes through several states, such as **Initializing**and **Post-update**. When both gateways are activated, the primary gateway state is **Active**and the secondary gateway state is **Standby**.

See image.

You can configure a password and additional settings to prevent other devices using VRRP from communicating with the nodes in the HA cluster. To learn more, see [Configuring a High Availability Site with Virtual Router Redundancy Protocol](https://help.zscaler.com/zero-trust-branch/configuring-ha-site-vrrp).

[Image: Add Template panel showing configuration of a new custom template for an HA cluster]

[Image: Sites page details for an HA cluster where both gateways are in the Disconnected state because they are not activated]

[Image: Sites page details for an HA cluster where both gateways are activated. One gateway is in the Active state and the other gateway is in the Standby state.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/deploying-device-using-zero-touch-provisioning","lastmod":"2026-07-22T09:36Z","nid":"1529033"} -->
## Deploying an Appliance Using Zero Touch Provisioning

- Source: https://help.zscaler.com/zero-trust-branch/deploying-device-using-zero-touch-provisioning
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Deploying Zero Trust Branch > Deploying an Appliance Using Zero Touch Provisioning
- Last modified: 2026-07-22T09:36Z
- Summary: How to deploy Zero Trust Branch appliances using Zero Touch Provisioning.

You can quickly deploy an appliance to Zero Trust Branch by adding a site with Zero Touch Provisioning (ZTP):

1. Follow all the steps in [Adding a Site](https://help.zscaler.com/zero-trust-branch/adding-site). Refer to the following table to determine which template to use for your appliance: You can clone and edit the template to meet your deployment configuration. To learn more, see [Managing Templates](https://help.zscaler.com/zero-trust-branch/managing-templates).
  | Appliance | Template (High Availability) | Template (Standalone) |
  | --- | --- | --- |
  | ZT800 | `zt800-ha-default` | `zt800-standalone-default` |
  | ZT600 | `zt600-ha-default` | `zt600-standalone-default` |
  | ZT400 | `zt400-ha-default` | `zt400-standalone-default` |
  | ZT8010 | `zt8010-ha-default` | `zt8010-standalone-default` |
2. In the **DHCP Service** field, if not set by the template, select **DHCP S****erver** for a DHCP server; for a relay to a server, select **r****elay**.
3. Verify that all other site parameters are set correctly for the appliance you are deploying.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/deploying-zero-trust-branch-appliance","lastmod":"2026-08-05T15:58Z","nid":"1532526"} -->
## Deploying a Zero Trust Branch Appliance

- Source: https://help.zscaler.com/zero-trust-branch/deploying-zero-trust-branch-appliance
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Deploying Zero Trust Branch > Deploying a Zero Trust Branch Appliance
- Last modified: 2026-08-05T15:58Z
- Summary: Information about deploying a Zero Trust Branch appliance and passing traffic through it

You can deploy Zero Trust Branch and pass traffic through it with minimal configuration because:

- The Zero Trust Branch appliance automatically establishes an IPSec tunnel to Internet & SaaS (ZIA) via the GE3 WAN port.
- The default Zscaler routing policy allows traffic to be sent to Internet & SaaS.
- The default (system) firewall policy allows traffic over LANs and WANs.

## Prerequisites

Make sure the following prerequisites are met:

- Verify That Your Tenant Is Provisioned
- Review the Appliance Port Mapping
- Determine the Template to Use
- Determine the Interfaces to Use
- Open Ports on the Upstream Device
- Verify Internet Connectivity for the Primary WAN Interface
- Gather Details for the Configuration

## Deploying Zero Trust Branch

To deploy Zero Trust Branch, complete the following steps:

- Step 1: Mount and Cable the Appliance
- Step 2: Change the Default Password
- Step 3: Clone and Modify a Template
- Step 4: Add a Site
- Step 5: Activate the Appliance
- Step 6: Configure the LAN Interface
- Step 7: Configure the VLAN
- Step 8. Verify the Deployment

Verify that Zscaler provisioned your tenant. If not, contact your Zscaler account team. After the tenant is provisioned, Zscaler sends the super admin an email invitation that must be accepted. The super admin can then access Zero Trust Branch in the Zscaler Admin Console.

Review the port mapping on your appliance model. To learn more, see [Zero Trust Branch Physical Appliance Port Mapping](https://help.zscaler.com/zero-trust-branch/zero-trust-branch-physical-port-mapping).

Each site uses a single template, which defines the Zero Trust Branch model to be deployed at the site and configuration settings such as the DNS server address, DHCP service type, deployment type, platform, and NAT enablement. Choose from one of the following options:

- Clone the default template or an existing template, save it with a new name, and customize it (recommended). To find a template to clone, search for templates matching your Zero Trust Branch model number.
- Use the default template for your site.
- Create your own template from scratch. To learn more, see [Managing Templates](https://help.zscaler.com/zero-trust-branch/managing-templates).

The following table shows the interfaces that each Zero Trust Branch model uses in a high availability (HA) deployment. The interfaces with an asterisk (*) are fixed and cannot be changed, even on cloned templates.

The GE4 interface is fixed in an HA deployment, but in a standalone deployment it can be used for LAN. All other interfaces with an asterisk are fixed in a standalone deployment as well.

| Purpose | ZT400 | ZT600 | ZT800 | ZT8010 |
| --- | --- | --- | --- | --- |
| Management | GE1* | GE1* | GE3* | GE1* |
| LAN | GE2 | GE2, GE3 | GE1, GE5, GE6 | GE3, GE5, GE6, XE11, XE12, XE13, XE14 |
| WAN | GE3* | GE2, GE5* | GE2*, GE7, GE8 | GE2*, XE7, XE8, XE9, XE10 |
| HA | GE4* | GE4* | GE4* | GE4* |

A Zero Trust Branch appliance requires ports to be open on a Layer 3 (L3) device, such as an upstream firewall or a router, for initial activation and ongoing management. For a complete list of ports, refer to [Ports that need to be open for appliance activation and upgrade](https://config.zscaler.com/zscaler.net/ztb).

Test that the primary WAN interface can reach the internet.

Collect the following information:

- For each interface that is statically configured, collect the IP address, mask, gateway, and primary and secondary DNS servers.
- If the Zero Trust Branch appliance is to operate as a DHCP server, collect the scope that will be used, the DNS server IP addresses, the LAN interface IP address (or IP addresses if there is more than one LAN interface), and the corresponding VLAN (or VLANs if there is more than one VLAN on the LAN side).
- If the Zero Trust Branch appliance is to operate as a DHCP relay, collect the IP address of the DHCP server.

To mount and cable the appliance:

1. Review the package contents and mount the Zero Trust Branch appliance using the instruction manual for your model. To learn more, see [Zero Trust Branch Appliances Wall and Rack Mount Instruction Manual](https://help.zscaler.com/zero-trust-branch/zero-trust-branch-appliances-wall-and-rack-mount-instruction-manual).
2. Cable the appliance based on the interfaces that you decided to use.

To connect to the console port and change the default admin password:

1. Download an SSH, telnet, and terminal emulator client such as PuTTY, Minicom, screen2, SecureCRT, Terminus, or ZOC Terminal.
2. Connect one end of the serial console cable to your laptop, and make sure the other end is connected to the console port on the appliance.
3. Establish a serial connection using the following parameters:
  - **Baud Rate**: 115,200
  - **Data Bits**: 8
  - **Stop Bits**: 1
  - **Parity**: None
  - **Flow Control**: XON/XOFF
4. Log in to the console using the default credentials:
  - **Login ID**: `admin`
  - **Password**: `a!rg@p`
5. Enter `3` (Change Administrator Password) in the console menu and press `Enter`.
6. Enter the current password and press `Enter`.
7. Enter the new password and press `Enter`.
8. Enter the new password again and press `Enter`.
9. Press `Enter` to continue and to log out of the console.
10. Use the new password to log in to the console.

Zscaler recommends that you clone a template and then modify it to meet your requirements. Alternatively, you can create a new template. To learn more, see [Managing Templates](https://help.zscaler.com/zero-trust-branch/managing-templates).

To learn more, see [Managing Sites](https://help.zscaler.com/zero-trust-branch/managing-sites).

To review the ports that need to be open, see [Open Ports on the Upstream Device](https://help.zscaler.com/zero-trust-branch/deploying-zero-trust-branch-appliance#OpenPorts).

The activation process uses the activation code and URL that you saved during the [Adding a Site](https://help.zscaler.com/zero-trust-branch/managing-sites#adding-a-site) procedure in the Zscaler Admin Console. If you cannot find this information:

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Sites**.
2. In the row for the required site, click the **Send Activation Link** icon. See image.

Zscaler supports one subinterface per WAN interface. However, Zero Trust Branch activation is supported over the WAN interface on the main interface (which is tagged), not on a subinterface (which is untagged).

There are two ways to activate a Zero Trust Branch appliance.

- Activation Code Method
- Activation URL Method

This method uses the activation code that you saved during the [Adding a Site](https://help.zscaler.com/zero-trust-branch/managing-sites#adding-a-site) procedure in the Zscaler Admin Console. Before you activate the appliance, you must configure it with the same parameters you configured when you created the site in the Zscaler Admin Console.

This procedure uses these example values:

- **WAN interface**: ge3 set for DHCP (This interface is hard-coded.)
- **DNS servers**: 1.1.1.1 and 8.8.8.8
- **Web proxy**: hub3.goairgap.com:1883

- a. Configure the network.
- b. Configure the web proxy.
- c. Activate the appliance.

This method uses the URL that you saved during the [Adding a Site](https://help.zscaler.com/zero-trust-branch/managing-sites#adding-a-site) procedure in the Zscaler Admin Console. The URL contains the configuration data for the WAN interface and the activation code.

1. Connect a laptop directly to the [Management port (GE1)](https://help.zscaler.com/zero-trust-branch/zero-trust-branch-physical-port-mapping#ZT400) on the appliance. The laptop automatically gets an IP address directly from the appliance. See image.
2. Open a browser on the laptop and paste the URL that you copied when you created the site. After the appliance processes the configuration, it displays an **Activation is completed** message on the web page. See image.
3. In the Zscaler Admin Console, go to **Infrastructure** > **Connectors** > **Edge** > **Sites**and click the site in the **Site Name**column. In the site details, verify that the appliance is activated. The **State**field value for an activated appliance is: Show image.
  - **Standalone**for a standalone appliance
  - **Active**for the primary node of an HA cluster
  - **Standby**for the secondary node of an HA cluster

1. In the console, enter `2` (Configure Gateway) from the menu and press `Enter`. See image.
2. Enter `1` (Configure Network) from the next menu and press `Enter`. See image.
3. Respond to the prompts to complete the network configuration. See image.

1. In the console, press `Enter` to return to the main menu.
2. Enter `3` (Configure Web Proxy) and press `Enter`. See image.
3. Respond to the prompts to complete the web proxy configuration. See image.

1. In the console, press `Enter` to return to the main menu.
2. Enter `1` (Activate Gateway) and press `Enter`. See image.
3. Enter `1` (Activate Airgap Gateway) from the next menu and press `Enter`. See image.
4. Enter the activation code that you saved when you created the site and press `Enter`. See image.
5. Enter `2` (Main Menu) and press `Enter`. The screen shows that the appliance is activated. See image.

[Image: Option 2 - Configure Gateway in console menu]

[Image: Option 1 - Configure Network option in console menu]

[Image: Network configuration wizard in console with prompts and responses]

[Image: Option 3 - Configure Web Proxy in console menu]

[Image: Web proxy configuration wizard in console with prompts and responses]

[Image: Option 1 - Activate Gateway option in the console menu]

[Image: Option 1 - Activate Airgap Gateway in the console menu]

[Image: Console output with the gateway activation code]

[Image: Console displaying the activated gateway]

[Image: Browser showing that the gateway activation completed]

[Image: Site details showing a green check mark icon showing the gateway is activated]

[Image: Send Activation Link icon at the end of the row for a site on the Sites page]

[Image: Diagram showing a laptop directly connected to the GE1 management port on the appliance and the appliance connected to the internet via the GE3 WAN port. The laptop receives the 192.168.0.0/24 address from the appliance.]

A Zero Trust Branch appliance can be deployed to protect multiple VLANs. To process multiple VLANs, the switchport connecting to the LAN-side interface of the Zero Trust Branch appliance must be configured as a trunk port, and it must allow the VLANs that need to be protected by Zero Trust Branch.

The following is an example configuration of a switchport connecting to the LAN side of the Zero Trust Branch appliance via the GE2 interface. The F0/5 interface is configured with trunk and allows VLANs 50, 60, 70, and 80. The laptop connects to the switch via the F0/4 interface, which is configured to allow access to VLAN 50.

```
interface FastEthernet0/5
 description connected to ZT400 Ge2
 switchport trunk encapsulation dot1q
 switchport trunk allowed clan 50,60,70,80
 
 interface FastEthernet0/4
  description connected to test laptop
  switchport access vlan 50
  switchport mode access
end
```

Zero Trust Branch deployment is not complete until the VLAN to be protected is enabled in the Zscaler Admin Console. Ensure that a LAN port is connected to the switch and that it is in the same VLAN as the devices (i.e., broadcast packets from the devices must reach the LAN port). The following example describes how to configure Zero Trust Branch to receive traffic from VLANs 50, 60, 70, and 80 and protect devices connected to those VLANs.

See image.

This procedure shows how to add a new VLAN. If you are using an existing production VLAN, make sure that the default gateway IP address is the same as the existing Switch Virtual Interface (SVI) address for that VLAN.

1. In the Zscaler Admin Console, go to **Infrastructure** > **Connectors** >**Edge** > **Sites**.
2. Select the site that you activated, and click the **VLANs** tab.
3. Click **Add Airgap VLAN**. See image.
4. In the **Add Airgap VLAN** panel, complete the information for this VLAN. If you are configuring an untagged port, enter `1` in the **VLAN Tag** field. See image.
5. Click **Add**.
6. On the **VLANs**tab, in the **Disable/Enable** column, enable the VLAN. See image.
7. Connect the laptop to the access port on the switch. In this example, you configured F0/4 as an access port on VLAN 50. The laptop automatically gets an IP address within the DHCP range that you configured when you added the VLAN. All the Zero Trust Branch appliances connected to the switch have the same subnet mask, which provides device segmentation.

[Image: Diagram showing a Zero Trust Branch appliance connected to the Zero Trust Exchange via the GE3 WAN port and to a switch via the GE2 LAN port. The F0/5 LAN interface is a trunk port allowing access to VLANs 50, 60, 70, and 80. The F0/4 interface is the access port on VLAN 50, which provides access to the laptop]

[Image: VLAN page for a site with the button to add a new VLAN]

[Image: Panel used to add a new VLAN, including configuration of the gateway interface, interface IP, DHCP service, DHCP address range, and DNS servers]

[Image: VLAN page with a Disable/Enable switch to enable the new VLAN]

To verify the Zero Trust Branch deployment:

1. Open a browser and go to a few websites.
2. Go to `ip.zscaler.com`.
3. Observe that traffic from the laptop goes to the internet via Internet & SaaS:
  1. In the Zscaler Admin Console, go to **Infrastructure**> **Connectors** > **Edge**> **Sites**,and click the site in the **Site Name** column.
  2. Select **Settings** > **ZIA** > **IPSec Tunnels**.
  3. Verify the value in the **Status**column is **ESTABLISHED**.

(Optional) Only if directed by Zscaler Support, edit the IPSec tunnel configuration for the site:

1. In **IPSec Tunnels**, click the **Edit** icon for the tunnel to edit the configuration.
2. In the **Remote address** field, enter the IP address for the Internet & SaaS global VPN.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/deploying-zero-trust-branch-using-ebond-interface","lastmod":"2026-07-22T09:38Z","nid":"1538679"} -->
## Deploying Zero Trust Branch Using Ebond Interface

- Source: https://help.zscaler.com/zero-trust-branch/deploying-zero-trust-branch-using-ebond-interface
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Deploying Zero Trust Branch > Deploying Zero Trust Branch Using Ebond Interface
- Last modified: 2026-07-22T09:38Z
- Summary: How to deploy Zero Trust Branch using the ebond on the WAN side.

Zero Trust Branch supports link aggregation on wide area network (WAN) and local area network (LAN) to provide redundancy and high throughput.

This article explains how to deploy Zero Trust Branch appliances using the ebond Link Aggregation Control Protocol (LACP) interface on the WAN side.

To deploy the Zero Trust Branch appliance, complete the following steps in the Zscaler Admin Console:

- 1. Clone and modify the template.
- 2. Add a site.
- 3. Configure ebond on the appliance console.
- 4. Activate the appliance.

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Site Templates**.
2. On the **Templates** page, search for the appliance model number (e.g., ZT600, ZT800, or ZT8010) you are using. You can either create a new template or clone an existing template. Zscaler recommends that you clone a template and modify it to meet your requirements. To learn more, see [Managing Templates](https://help.zscaler.com/zero-trust-branch/managing-templates). The ZT400 appliance is not supported. See image.
3. In the **Template** column, click the template you created or cloned in the previous step. The template properties page appears.
4. Click the **Interfaces**tab, click **Add**, and then select **Bonding Interface**. See image.
5. In the **Add Bonding Interface**window: See image.
  - **Interface Name**: Select **ebond0** or **ebond1** from the drop-down menu.
  - **Gateway**: Select the required gateway (**Gateway-1** or **Gateway-2**).
  - **Interface Type**: Select **LAN** or **WAN**from the drop-down menu.
  - **Member Interfaces**: Select the network interfaces that must be bonded by this interface.
6. Click **Add**.
7. Repeat the previous steps for the second appliance to add the ebond interface.

Follow the instructions in [Adding a Site](https://help.zscaler.com/zero-trust-branch/adding-site).

While adding the site, select **ebond** for the WAN interface. After the site is added, activation codes are generated for both appliances. Copy and save the activation codes as you need them in the next step.

Before proceeding, configure LACP (802.3ad) on the switch where the WAN interfaces will connect. Zero Trust Branch supports LACP using the 802.3ad standard.

1. Connect to the appliance console using the console cable and log in with admin credentials.
2. In the console, enter `2 - Configure Gateway` and press `Enter`. See image.
3. Enter `1 - Configure Network` to proceed with the network configuration. See image.
4. Configure the following parameters for the required WAN bonding interface: ``available interfaces: ['ebond0', 'ebond1', 'ge1', 'ge2', 'ge5', 'ge6',’ge7’,’ 'ge8']WAN interface (? to list available options) [ge8]: ebond1` `ebond1 currently does not have any member interface` `Would you like to add(a) interface to ebond1, or satisfy(s) with the current configuration? [s]: a` `Select an interface to be added: ['ge1', 'ge2', 'ge5', 'ge6',’ge7,’ 'ge8']: ge8` `ebond1 currently configured with 1 interfaces ['ge8']` `Would you like to add(a), delete(d) interface to/from ebond1, or satisfy(s) with the current configuration? [s]: a` `Select an interface to be added: ['ge1', 'ge2', 'ge5', 'ge6,’ge7’']: ge7` `ebond1 currently configured with 2 interfaces ['ge8', 'ge7']` `Would you like to add(a), delete(d) interface to/from ebond1, or satisfy(s) with the current configuration? [s]: s` `WAN VLAN ID (1..4092) [1]:` `Use DHCP for WAN interface ebond1 (Y/y or N/n) [n]:` `WAN Nameservers [1.1.1.1, 8.8.8.8]:` `WAN IP Prefix (w.x.y.z/n) [10.112.23.174/25]:` `WAN Default Gateway IP Address (w.x.y.z) [10.112.23.254]:` `Management Interface: ge3` `Management IP Address: 10.112.21.47` `WAN Interface: ebond1 (members: ['ge8', 'ge7'])` `WAN IP Address: 10.112.23.174/25` `WAN Default Gateway: 10.112.23.254` `WAN Nameservers: 1.1.1.1, 8.8.8.8` `Web Proxy: None` `Apply Configuration? (y/n)y``

1. In the console, return to the main menu, and enter `1 - Activate Gateway` and press `Enter`.
2. Next, enter `1 - Activate Airgap Gateway` and enter the activation code you saved earlier while adding a site. See image.
3. Press `2` to return to the main menu. The appliance status is shown as **Active**. See image.
4. Repeat the same process for the second appliance. After activation, the appliance automatically enters **Standby** mode. See image.

[Image: Add a new template or clone an existing template]

[Image: Select the bonding interface]

[Image: Provide the bonding interface details]

[Image: Select option 2 to configure the gateway]

[Image: Select option 1 to configure the network]

[Image: Select option 1 to activate the gateway]

[Image: Status of the gateway is active]

[Image: The appliance is in standby mode]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/deploying-zero-trust-branch-virtual-machine-vmware-esxi","lastmod":"2026-07-22T09:38Z","nid":"1533836"} -->
## Deploying Zero Trust Branch as a Virtual Machine on VMware ESXi

- Source: https://help.zscaler.com/zero-trust-branch/deploying-zero-trust-branch-virtual-machine-vmware-esxi
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Deploying Zero Trust Branch > Deploying Zero Trust Branch as a Virtual Machine on VMware ESXi
- Last modified: 2026-07-22T09:38Z
- Summary: How to deploy Zero Trust Branch as a virtual machine on VMware ESXi.

Zero Trust Branch supports deployment as either a hardware appliance or a virtual machine (VM), allowing you to choose the model that best fits your branch environments.

This article explains how to deploy Zero Trust Branch as a VM on VMware ESXi.

See typical topology

## Prerequisites

Before deploying a Zero Trust Branch VM on VMware ESXi, ensure that:

- Hardware requirements are met. See hardware requirements
- The Zero Trust Branch ISO image is obtained from Zscaler Support.
- [Templates](https://help.zscaler.com/zero-trust-branch/managing-templates) and interfaces to be used are determined. See Zero Trust Branch VM port mapping
- The necessary ports are open on the Zero Trust Branch VM. See port access requirements

## Deploying a Zero Trust Branch VM on VMware ESXi

Follow these steps to deploy Zero Trust Branch VM gateway on VMware ESXi:

- 1. Create LAN and WAN port groups in VMware ESXi.
- 2. Launch the Zero Trust Branch VM gateway in VMware ESXi.
- [3. Add a site in the Zscaler Admin Console.](https://help.zscaler.com/zero-trust-branch/adding-site)
- 4. Associate the site and activate the Zero Trust Branch VM gateway.
- [5. Configure a VLAN.](https://help.zscaler.com/zero-trust-branch/configuring-vlan)

[Image: Typical topology for deploying Zero Trust Branch VM gateway]

| Specifications | Option 1 | Option 2 |
| --- | --- | --- |
| **CPU** | 4 vCPU | 8 vCPU |
| **Memory** | 16 GB | 32 GB |
| **Storage** | 256 GB | 256 GB |
| **Ports** | 4 x vNICs (VMXNET3 recommended) | 8 x vNICs (VMXNET3 recommended) |
| **Throughput (Unencrypted LAN-to-LAN or LAN-to-WAN flows)** | 10 Gbps | 20 Gbps |
| **Sessions** | 500K | 1 Million |
| **Number of Endpoints** | 500 | 1,000 |

| Port with Protocol | Access Type | Host or URL | Description |
| --- | --- | --- | --- |
| TCP 443 | Outbound | `gwactivation.goairgap.com` | This port is used for appliance activation. This is a mandatory requirement. |
| TCP 80 | Outbound | `a24c1b36b6f9f1f88.awsglobalaccelerator.com` | This port is required for improved Zscaler Admin Console performance. |
| UDP 443 | Inbound | Public-routable IP address of the spoke appliance used for tunnel establishment | This port is required for Routed Tunnels if Zero Trust Branch appliances are deployed in the Hub-and-Spoke model. |
| UDP 500 UDP 4500 ESP - IP Protocol 50 | Outbound | `pac.zscaler.net`; `gateway.zscaler.net`; One of the following hosts depending on your Internet & SaaS (ZIA) cloud:`config.zscaler.com/zscaler.net/cenr`; `config.zscaler.com/zscalertwo.net/cenr` | This port is required if the Zero Trust Branch appliance must connect to Internet & SaaS. |
| TCP 443 | Outbound | `config.zscaler.com/private.zscaler.com/zpa`; `prod.zpath.net`; `private.zscaler.com` | This port is required if the Zero Trust Branch appliance must connect to Private Access (ZPA). |
| TCP 443 | Outbound | `dl.cloudsmith.io` | This port is required for software upgrades. This is a mandatory requirement. |
| TCP 1883 | Outbound | `hub3.goairgap.com` | This port is required for management-plane communication. This is a mandatory requirement. |
| TCP 443 | Outbound | `gateway.``<cloud>``.net` | This port is required for WAN monitoring. This is a mandatory requirement. |
| UDP 51820 | Outbound | `wg.goairgap.com` | This port is required for remote debugging. This is a mandatory requirement. |
| UDP 53 | Outbound | IP address of DNS service | This port is required if the Zero Trust Branch appliances must use specific DNS servers for name resolution. |
| UDP 123 | Outbound | `0.pool.ntp.org` `1.pool.ntp.org` `2.pool.ntp.org` `3.pool.ntp.org` | This port is required if the Zero Trust Branch appliance must use specific NTP servers for time synchronization. |

| Port | Description | Role |
| --- | --- | --- |
| 1 | ge1 | LAN |
| 2 | ge2 | WAN |
| 3 | ge3 | Management |
| 4 | ge4 | LAN |
| 5 | ge5 | LAN |
| 6 | ge6 | LAN |
| 7 | ge7 | WAN |
| 8 | ge8 | WAN |

1. Log in to the VMware Host Client.
2. Go to **Networking**> **Port groups**.
3. To create a LAN port group, click **Add port group**. See image.
4. In the **Add port group** window: See image.
  - **Name**: Enter a name for the LAN port group.
  - **VLAN ID**: Enter `4095` as the LAN port group must be a trunk port group. This allows support for many VLANs for the network adapter and Zero Trust Branch VM handles the tagging.
  - Click **Security**and select **Accept**for the following options:
    - **Promiscuous mode**
    - **MAC address changes**
    - **Forged transmits**
5. Click **Add**. The LAN port group is created.
6. To create a WAN port group, click **Add port group**.
7. In the **Add port group** window: See image.
  - **Name**: Enter a name for the WAN port group.
  - **VLAN ID**: Enter a VLAN ID (value between `0` and `4094`) as the WAN port group must be an access port group. This ensures only one VLAN for the network adapter and ESXi handles the tagging.
8. Click **Add**. The WAN port group is created.

1. In the VMware Host Client, go to **Virtual Machines**.
2. On the **Virtual Machines** panel, click **Create / Register VM**. See image.
3. In the **New virtual machine** wizard: The Zero Trust Branch VM is created in VMware ESXi.
  1. In the **Select creation type** step: See image.
    1. Select **Create a new virtual machine**.
    2. Click **Next**.
  2. In the **Select a name and guest OS**step: See image.
    1. **Name**: Enter a name for the Zero Trust Branch VM.
    2. **Compatibility**: Select **ESXi 6.7 virtual machine** from the drop-down menu.
    3. **Guest OS family**: Select **Linux**from the drop-down menu.
    4. **Guest OS version**: Select **Ubuntu Linux (64-bit)** from the drop-down menu.
    5. Click **Next**.
  3. In the **Select storage**step: See image.
    1. Select the **Standard**storage type, and select a datastore.
    2. Click **Next**.
  4. In the **Customize settings**step:
    1. On the **Virtual Hardware** tab: See image.
      - **CPU**: Select the number of CPUs from the drop-down menu. Zscaler recommends a minimum of 4 CPUs.
      - **Memory**: Enter the RAM size in GB. Zscaler recommends a minimum of 16 GB.
      - **Hard Disk 1**: Enter the storage size in GB. Zscaler recommends a minimum of 256 GB.
      - **Network Adapter 1**: Select the LAN port group from the drop-down menu, and ensure that the **Connect**option is selected.
      - Click **Add Network Adapter**. The **New Network Adapter** field is added.
      - **New Network Adapter**: Select the WAN port group from the drop-down menu, and ensure that the **Connect**option is selected.
      - **CD/DVD Drive 1**: Select **Datastore ISO** **file**and upload the Zero Trust Branch ISO file obtained from Zscaler Support.
    2. On the **VM Options**tab, go to **Boot Options** and select **EFI**from the drop-down menu as the firmware. See image.
    3. Click **Next**.
  5. In the **Ready to complete** step: See image.
    1. Review the configurations.
    2. Click **Finish**.
4. On the **Virtual Machines** panel, select the Zero Trust Branch VM, and click **Power on**. See image. The Zero Trust Branch VM is launched in VMware ESXi.
5. Log in to the Zero Trust Branch VM using the default credentials (enter `admin` as the username and `a!rg@p` as the password). The Zero Trust Branch VM is running in VMware ESXi and is ready for registration.
6. In the terminal, enter the numerical value corresponding to the **Change Password**option and press `Enter`.
7. In the **Change password** configuration, enter the current password and new password of your choice, and then retype the new password when prompted. The default password for the Zero Trust Branch VM terminal is updated.

1. Log in to the Zero Trust Branch VM instance on VMware ESXi.
2. In the terminal, enter the numerical value corresponding to the **Configure Gateway**option and press `Enter`. See image.
3. In the**Airgap Gateway Configuration**, enter the numerical value corresponding to the **Configure Network** option and press `Enter`. See image.
4. Configure the following network details when prompted:
  - **LAN interface**: Enter the LAN interface value.
  - **WAN interface**: Enter the WAN interface value.
  - **WAN Nameservers**: Enter the IP addresses of nameservers.
  - **WAN IP Prefix**: Enter the WAN IP address in CIDR notation.
  - **WAN Default Gateway IP Address**: Enter the IP address of the default WAN gateway
  - **WAN Virtual IP Address**: Enter the virtual IP address for the WAN.
5. Review and apply the configuration. See image.
6. Press `Enter`.
7. In the terminal, enter the numerical value corresponding to the **Activate Gateway** option and press `Enter`. See image.
8. In the **Airgap Gateway Configuration**, enter the numerical value corresponding to the **Activate Airgap Gateway** option and press `Enter`. See image.
9. Enter the activation code copied from the Zscaler Admin Console while [adding the site](https://help.zscaler.com/zero-trust-branch/adding-site), and press `Enter`. See image. The Zero Trust Branch VM is activated.

After the activation, the Zero Trust Branch VM gateway runs the necessary scripts in the background. This process takes a few minutes and during this period, the **State**column for the sitein the Zscaler Admin Console shows **Post Update**. Finally, when the background processes are completed, the **State**column is updated to **Standalone**and the Zero Trust Branch VM terminal shows the site name.

See image.

[Image: Choosing a VM type while creating a VM]

[Image: Configuing the OS details for the VM]

[Image: Customizing and hardware options for the VM]

[Image: Configuring boot settings for VM]

[Image: Selecting the Configure Gateway option in the VM terminal]

[Image: Selecting the Configure Network option in the terminal]

[Image: Reviewing and applying network configuration for the VM]

[Image: Selecting the Activate Gateway option in the VM terminal]

[Image: Selecting the Activate Airgap Gateway option]

[Image: Activating the Zero Trust Branch VM using an activation code]

[Image: Status of the Zscaler Admin Console and the VM terminal]

[Image: Option to add a port group in VMware EXSi]

[Image: Adding a LAN port group]

[Image: Adding a WAN port group]

[Image: Option to create a VM in VMware EXSi]

[Image: Selecting a storage type for the VM]

[Image: Reviewing and completing the VM creation in VMware ESXi]

[Image: Option to power on the Zero Trust Branch VM]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/deploying-zscaler-zt800-without-zero-trust-provisioning","lastmod":"2026-07-22T09:35Z","nid":"1509601"} -->
## Deploying on a Zscaler ZT800 without Zero Touch Provisioning

- Source: https://help.zscaler.com/zero-trust-branch/deploying-zscaler-zt800-without-zero-trust-provisioning
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Deploying Zero Trust Branch > Deploying on a Zscaler ZT800 without Zero Touch Provisioning
- Last modified: 2026-07-22T09:35Z
- Summary: How to deploy Zero Trust Branch on a Zscaler ZT800 appliance without Zero Touch Provisioning in version 7.8 or earlier.

Zero Trust Branch appliances can be deployed on a ZT800 appliance without using Zero Touch Provisioning for version 7.8 or earlier.

## Prerequisites

- Hardware Specifications
- Network Information
- Typical Setup

## Configuration

The steps below describe how to configure Zero Trust Branch on a Zscaler ZT800 when using version 7.8 or earlier.

- Step 1: Connect to a Zscaler ZT800.
- Step 2: Configure the WAN port.
- Step 3: Activate Zero Trust Branch appliances.
- Step 4: Configure Zero Trust Branch appliance LAN.
- Step 5: Configure VLAN.

When deployed on a Zscaler ZT800, Zero Trust Branch uses six 1GbE RJ45 ports and two 1GbE SFP ports. Any two of these can be configured as LAN (device-side) and WAN (firewall-/router-side) ports.
Connect a laptop to the management port of the branch appliance over the RJ45 cable. Configure your laptop’s Ethernet interface with a static IP address in the subnet of 192.168.99.0/24 (e.g., 192.168.99.10).

1. Log in to the branch appliance over SSH using the default management IP address and credentials. You can also connect to the branch appliance over the serial RJ45 console port.
2. In the gateway command line interface, configure the LAN and WAN ports. Select `1` to configure the branch appliance and `1` to configure the network. Review [this recording](https://www.loom.com/share/9ea0049d436d4116997dc92e42eeab4d?sid=44a4b036-6e01-40ac-9857-e7176c74a96d) for more information.
3. Select the LAN and WAN interface from the available options.

Zscaler recommends that you connect the WAN port group to the network and ensure internet connectivity is active.

1. Connect the switchport on the WAN side to the untagged or access port and refer to the following sample Cisco configuration. In this example, the switchport (Ge1/1/5) connecting to the WAN side of Zero Trust Branch is part of the VLAN445 and is configured as an access port. `interface GigabitEthernet1/1/5 switchport access vlan 445 switchport mode access`
2. The WAN port can be configured with the static address or the DHCP. Virtual IP address is a floating IP address shared across both branch appliances when configured in the HA pair. Review [this recording](https://www.loom.com/share/9ea0049d436d4116997dc92e42eeab4d?sid=4b4d899b-cd31-4454-9df4-ddbf4cab10fc) where enp8s0f0 and enp10s0f0 are 1GbE interfaces and configured as a LAN/WAN interface.
3. Branch appliances communicate with various AWS public cloud services such as RDS, ALB, and Elastic over the WAN uplink. These connections can be aggregated into a single outgoing connection via Zero Trust Branch-hosted forward proxy (e.g., `hub3.goairgap.com`) on TCP port 1883. To configure this proxy setting, select option `2` in the **Configure Gateway** menu of the gateway command line interface.
4. After configuring the IP address and proxy, and establishing internet connectivity for the branch appliance, a 6-digit code displays in the gateway command line interface.

Branch appliance configuration, policy management, logging, and reporting are managed via the SaaS-based Zscaler Admin Console.

To activate the branch appliance:

1. Go to **Networking > Gateways.**
2. Click **Add Gateway**. See image.
3. In the **Add Gateway** panel, complete the following information: [This recording](https://www.loom.com/share/894d129cae994d939de9e64ed98ba4ff?sid=67950ff9-4826-4237-9620-7f07c5e7fdc0) shows the gateway activation process. See image.
  1. **Location**: Select the location for this branch appliance, or select **Add New Location**.
  2. **Name**: (Optional) If adding a new location, enter a name for the location.
  3. **Gateway Name**: Enter a name for the branch appliance.
  4. **DHCP Service**: Select **DHCP Server** or **DHCP Relay**, based on whether the branch appliance is a DHCP server or relay to your existing DHCP server.
  5. **NAT Enable**: (Optional) Select this checkbox if your branch appliance uses NAT to route all the traffic leaving the branch appliance toward the non-Zero Trust Branch network.
  6. **Activation Code**: Enter the code you received when you configured the WAN port. It can take 5 to 10 minutes to activate and provision the appropriate microservices.
  7. **WAN Virtual IP**: Enter the floating IP address to be used between two branch appliances.
  8. **WAN VRRP Group ID (1 - 255**): Enter a number between 1–255 to uniquely identify the WAN router.
  9. Click **Add**.
4. To deploy the branch appliance's high availability pair, configure another appliance and activate it as a standby gateway using the following steps:
  1. On the **Gateways** page, click the **Gear**icon for the gateway that you want to make a standby.
  2. Select **Add Standby Gateway** from the menu.
  3. Provide the standby gateway name, 6-digit code from the newly installed VM, and WAN virtual IP address and group ID number as described in the previous step.
  4. Click **Add**.

A single cluster of branch appliances can be deployed to protect multiple VLANs. To process multiple VLANs, the switchport connecting to the LAN-side interface of the branch appliance must be configured as a trunk port, and it must allow the VLANs that need to be protected by Zero Trust Branch.

The following is a sample Cisco configuration of a switchport connecting to the LAN side of branch appliances. In this example, Ge1/1/4 is connected to the LAN side, is configured with trunk, and allows VLAN 226 and VLAN 227.

```
interface GigabitEthernet1/1/4
switchport trunk allowed vlan 226,227
switchport mode trunk
```

Zero Trust Branch deployment is not complete until the VLAN to be protected is enabled in the Zscaler Admin Console. Ensure that a LAN port is connected to the switch and that it is in the same VLAN as the devices (broadcast packets from the devices must reach the LAN port). This section describes how to configure Zero Trust Branch to receive traffic from VLAN 226 and VLAN 227 and protect devices connected to those VLANs.

1. Go to **Infrastructure**>**Connectors**>**Edge**>**Sites**.
2. On the **Sites** page, select the site where the Zero Trust Branch-protected VLAN is configured, and click **Add Airgap VLAN**. See image.
3. In the **Add Airgap VLAN** panel, complete the information for this VLAN. For an untagged port, enter `1` in the **VLAN Tag** field. See image.
4. If you are using an existing production VLAN, make sure that the default gateway IP address is the same as the existing Switch Virtual Interface (SVI) address for that VLAN. [This recording](https://www.loom.com/share/6677958ff3b84ba0bc5e60d0c5cab544?sid=67dc69db-238e-4884-88f3-08bc07d2ac22) shows how to add a VLAN.
5. Log in to your existing L2/L3 switch, router, or firewall, and shut down the SVI/VLAN interface. Add a return route for VLAN with Zero Trust Branch WAN Virtual IP address as a nexthop. Here are sample Cisco commands (for VLAN 226 with subnet mask 10.16.226.0/24): `#conf t #int vlan 226 #shutdown #exit #ip route 10.16.226.0 255.255.255.0 < airgap-wan-vip >`
6. Turn on the VLAN in the Zscaler Admin Console. By default, the VLANs are created in the staged state. Each VLAN must be enabled to configure it into the branch appliances.

The DHCP Service option provides DHCP service ON/OFF and non-airgapped options:

- DHCP Service ON: The branch appliance assigns the IP address and `/32` net mask and ringfences the endpoints in the VLAN.
- DHCP Service OFF: The branch appliance acts as a DHCP relay and modifies the DHCP response to `/32` net mask and ringfences all the endpoints in the VLAN.
- Non-Airgapped: The branch appliance assigns the net mask as per the default configured network subnet mask or network mask received from the DHCP servers. It does not ringfence the endpoints. However, the admin can still create segmentation policies based on network and group-level policies.

[Image: Adding and updating network gateways on the Gateways page.]

[Image: Add Gateway panel.]

[Image: VLANs page.]

[Image: Add VLAN panel.]

| Parameter | Specification |
| --- | --- |
| CPU | 8C Atom |
| Memory | 16 GB |
| Storage | 256 GB |
| Ports | 6x 1GbE; 2x 1GbE small form-factor pluggable (SFP) Supported SFPs:Finisar FCLF8522P2BTL; Finisar FTLF8519P3BNL; Finisar FTLF1318P3BTL |
| Form Factor | Desktop |
| Throughput (64KB HTTP) | 6 Gbps |
| Sessions | 500K |
| Number of Endpoints | 750 |

| Details | Value | Description |
| --- | --- | --- |
| Site/Location Name | <name> | Site or branch name. |
| Branch Appliance Names | <name1>, <name2> | Appliance names. |
| Zero Trust Branch WAN/Uplink IPs | <IP1>, <IP2> | Zero Trust Branch upstream/WAN/NIC. Two IP addresses must be in the existing network. Three IP addresses are required for the high availability deployment. |
| Appliance Default Gateway | <IP> | Default gateways for the branch appliances. |
| DNS Servers | <IP1>, <IP2> | Enterprise private DNS server IP addresses that must be assigned to devices in the Zero Trust Branch network. |
| DHCP Servers | <IP1>, <IP2> | DHCP servers to which DHCP requests are relayed. Ensure that you have a pool assigned for Zero Trust Branch VLANs. |
| Internet Connectivity | `hub3.goairgap.com` (TCP:1883); `wg.goairgap.com` (UDP:51820) | Branch appliances require internet connectivity using these outbound TCP and UDP ports. |

### Port Configuration

[Image: Port configuration for a ZT800]

The ZT800 has the following ports:

1. Serial RJ45 Console Port: You can use PuTTY or similar serial console application to access the Zero Trust Branch console. Use the following settings for proper serial connection:
  - Baud Rate: 115,200
  - Data Bits: 8
  - Parity: None
  - Stop Bits: 1
  - Flow Control: Off
2. enp2s0f0 - 1GbE SFP LAN/WAN Port
3. enp2s0f1 - 1GbE SFP LAN/WAN Port
4. enp2s0f2 - Reserved
5. Management Port (enp2s0f3): Out-of-band management port with a fixed address of 192.168.99.99/24.
6. enp8s0f0 - 1GbE Port
7. enp8s0f1 - 1GbE Port
8. enp10s0f0 - 1GbE Port
9. enp10s0f1 - 1GbE Port

Ports 1, 2, and 6–9 can be used as either LAN or WAN ports.

- When used as LAN ports, device-side interfaces connected to the L2 switch via the trunk interface with Zero Trust Branch-protected VLANs allowed as member ports. The LAN port acts as the default gateway for both Zero Trust Branch-protected VLANs and devices.
- When used as WAN ports, the upstream interface connects to the L2 switch as an untagged port in the same VLAN as the upstream firewall or L2/L3 switch. All internet-bound or non-Zero Trust Branch destined traffic is routed to the network/VLAN after the policy check.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/deployment-overview","lastmod":"2026-07-22T09:35Z","nid":"1509391"} -->
## Deployment Overview

- Source: https://help.zscaler.com/zero-trust-branch/deployment-overview
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Deploying Zero Trust Branch > Deployment Overview
- Last modified: 2026-07-22T09:35Z
- Summary: Information about deploying Zero Trust Branch appliances.

Zscaler Zero Trust Branch has two components:

- Zero Trust Branch appliances: Provide forwarding and network enforcement points deployed in the customer environments. Depending on the throughput and scale requirements, there are different physical and virtual options available.
- Zscaler Admin Console: A centralized Software as a Service (SaaS) management portal used to manage Zero Trust Branch appliances.

There are several ways to deploy Zero Trust Branch:

- [Deploying an Appliance Using Zero Touch Provisioning](https://help.zscaler.com/zero-trust-branch/deploying-device-using-zero-trust-provisioning)
- [Deploying Zero Trust Branch as a Virtual Machine on VMware ESXi](https://help.zscaler.com/zero-trust-branch/deploying-zero-trust-branch-virtual-machine-vmware-esxi)
- [Deploying on a Zscaler ZT800 without Zero Touch Provisioning](https://help.zscaler.com/zero-trust-branch/deploying-zero-trust-branch-virtual-machine-vmware-esxi) (version 7.8 or earlier)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/integrating-armis-zero-trust-branch","lastmod":"2026-07-15T16:21Z","nid":"1535171"} -->
## Integrating Armis with Zero Trust Branch

- Source: https://help.zscaler.com/zero-trust-branch/integrating-armis-zero-trust-branch
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust Device Segmentation > Third-Party Integrations > Integrating Armis with Zero Trust Branch
- Last modified: 2026-07-15T16:21Z
- Summary: How to integrate Armis with Zero Trust Branch.

Zscaler Zero Trust Branch integrates with Armis to enable bidirectional data integration. With this integration:

- Armis ingests Zero Trust Branch device posture and policy data to enhance visibility of connected assets.
- Zero Trust Branch ingests Armis device intelligence (e.g., classification, tags, and risk insights) to strengthen policy enforcement and access control.
- Security teams can correlate network enforcement from Zero Trust Branch with device context from Armis, improving zero trust outcomes.

For complete details on working in the Armis management console, refer to your Armis management console documentation.

## Configuring Zero Trust Branch for Armis Integration

Follow these steps to configure the integration between Zero Trust Branch and Armis:

- Step 1: Configure the Zero Trust Branch-to-Armis integration
- Step 2: Configure the Armis-to-Zero Trust Branch integration
- Step 3: Verify the integration

1. Create an API key in Zero Trust Branch: In the Zscaler Admin Console, go to **Infrastructure > Connectors > Edge > Settings.** Click **Settings**in the **API Keys** panel and select **Add API Key** from the drop-down menu. See image.; In the **Add API Key** panel, enter a descriptive name and click **Add API Key**. See image.; The API key displays. Click the **Copy**icon to copy and save the API key. You need this key to complete your integration. See image. After you close the **Add API Key** panel, you are no longer able to view or copy the key. [Image: Adding an API key from the Zscaler Admin Console Global settings page.] [Image: Add API Key panel in theZscaler Admin Console with the Add API Key button highlighted.] [Image: Add API Key panel in the Zscaler Admin Console with the Copy button highlighted.]
2. In the Armis management console, click the **Settings**icon and select **Medical Device Security Settings**. See image.
3. On the **Integrations**page, click **Add Integration**. See image.
4. Enter `Airgap` in the **Library**search box. In the resulting Airgap panel, click **Connect**. See image.
5. In the **Connect Airgap Integration** panel: Click **Test Connection** to verify that the connection is successful. See image.
  1. **Instance Name**: Enter a unique name for this connection.
  2. **Host**: Enter the host name of your Zero Trust Branch tenant in the form `https://``<ztbtenant>``-api.goairgap.com`. For example, if your tenant is `https://myztbtenant.goairgap.com/`, enter `https://**myztbtenant-api**.goairgap.com`.
  3. **API Key**: Enter the key you saved in step 1c previously.
6. Click **Connect**to save the integration. See image.
7. On the **Integrations**page, verify that the new integration status is **Active**. Click **Run Now** if you want to begin the integration now. See image.

1. In the Armis management console, go to **API Management** and click **Show**. See image.
2. Copy the API secret key from the pop-up window and click **OK**. See image.
3. In the Zscaler Admin Console, go to **Infrastructure> Connectors > Edge > Settings > Integrations**. Click **Settings**in the **Armis Integration** panel. See image.
4. In the **Armis Integration** panel: Click **Test**to verify the connection, then click **Save**to finalize the integration. See image.
  - **Server URL**: Enter the URL of the Armis management console.
  - **Authentication Method**: The **API KEY** authentication method is filled in automatically.
  - **Authentication Key**: Enter the API secret key you copied in step 2.
  - **Description**: Optionally, add a description for this integration.

1. In the Zscaler Admin Console, go to **Infrastructure> Connectors > Edge > Assets**. Click an asset and verify that the tags from Armis are displayed in the **Tags**section of the asset profile. See image.
2. Click the **Armis**tab to view additional data for that asset from Armis. See image. Data from Armis is only synced for assets that have already been discovered.
3. In the Armis management console, go to **Assets**. Click any asset to view its details. See image.
4. On the **Overview**tab for the asset, verify that it has the **airgap:protected** tag. See image.

[Image: Settings menu in the Armis management console.]

[Image: Integrations page in the Armis management console.]

[Image: Searching for the Airgap integration from the Library page in the Armis management console.]

[Image: Connect Airgap Integration panel on the Integrations page in the Armis management console.]

[Image: Saving the connection on the Connect Airgap Integration panel on the Integrations page in the Armis management console.]

[Image: List of integrations on the Integrations page in the Armis management console.]

[Image: API Management page in the Armis management console with the Show Secret API key button highlighted.]

[Image: API Secret Key dialog in the Armis management console.]

[Image: Integrations page in the Zero Trust Branch Admin Portal highlighting the Armis Integration Settings button.]

[Image: Armis Integration settings panel in the Zscaler Admin Console.]

[Image: Assets page in the Zscaler Admin Console highlighting Armis-specific tags.]

[Image: Armis asset data viewed on the Assets page in the Zscaler Admin Console.]

[Image: Assets page in the Armis management console.]

[Image: Asset overview on the Assets page in the Armis management console highlighting the Airgap tags.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/integrating-crowdstrike-zero-trust-branch","lastmod":"2026-07-15T16:16Z","nid":"1534196"} -->
## Integrating CrowdStrike with Zero Trust Branch

- Source: https://help.zscaler.com/zero-trust-branch/integrating-crowdstrike-zero-trust-branch
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust Device Segmentation > Third-Party Integrations > Integrating CrowdStrike with Zero Trust Branch
- Last modified: 2026-07-15T16:16Z
- Summary: How to integrate CrowdStrike with Zero Trust Branch.

Zscaler Zero Trust Branch integrates with CrowdStrike Falcon to deliver endpoint-aware zero trust security across branches. By combining Zscaler's zero trust enforcement with CrowdStrike’s endpoint risk insights, organizations gain unified, adaptive access control for both on-premises and remote users. Organizations can dynamically adapt access based on device posture while extending Zscaler policies to remote endpoints through CrowdStrike Falcon, ensuring consistent zero trust protection across all environments.

For complete details on working in the CrowdStrike Falcon Console, refer to your CrowdStrike Falcon Console documentation.

## Integrating CrowdStrike with Zero Trust Branch

Follow these steps to integrate CrowdStrike Falcon with Zero Trust Branch:

- 1. Configure the CrowdStrike–Zero Trust Branch API integration.
- 2. Install the CrowdStrike Falcon sensor on the host.
- 3. Create CrowdStrike Falcon prevention policies.
- 4. Configure CrowdStrike Falcon host groups.
- 5. View the Zero Trust Assessment and Secure Posture Score on the hosts.
- 6. Configure Zero Trust Branch devices for high, medium, and low risk.

Configuring the API integration requires steps in both the CrowdStrike Falcon Console and the Zscaler Admin Console.

1. In the CrowdStrike Falcon Console, go to **Support and Resources > API Clients and Keys > Add new API client**.
2. CrowdStrike creates a new API client. Copy the values in the **Client ID**, **Secret**, and **Base**URL fields for use in the next step. Note that the secret is only available once at creation. See image. CrowdStrike recommends that you only give Zscaler read-only access (no write or admin functions) for hosts, devices, zero trust appliances, user management, sensor download, and event streams.
3. In the Zscaler Admin Console, go to **Settings > Integrations** and click **Settings**in the **CrowdStrike Integration** panel. See image.
4. In the **CrowdStrike Integration** panel, enter the client ID, client secret, and base URL from the previous step and click **Confirm**. See image.
5. Click **Test**to validate the integration. See image. When complete, a dialog window confirms that the integration has been validated. See image.
6. Click **Save** to save the integration.

1. In the CrowdStrike Falcon Console, go to **Host setup and management > Host dashboard.**
2. Click **Download sensor**to download the Falcon sensor. See image.
3. Note the customer ID during the installation. This ID is used later when the sensor is installed on the host. See image.
4. Copy the sensor file to the Zero Trust Branch host and install using the command for your platform:
  - Debian/Ubuntu: `sudo dpkg -i falcon-sensor-<version>.deb`
  - RHEL/CentoS/Amazon Linux: `sudo rpm -ivh falcon-sensor-<version>.rpm`
  - SUSE: `sudo zypper install falcon-sensor-<version>.rpm`
  - Windows: Download `WindowsSensor.exe`, double-click the file, and follow the prompts.
5. Register Falcon using the customer ID you copied previously:
  - Linux: `sudo /opt/CrowdStrike/falconctl -s --cid=``<your_customer-ID>`
  - Windows: `WindowsSensor.exe /install /quiet /norestart CID=``<your_customer-ID>`
6. Enable and start the Falcon service: `sudo systemctl enable falcon-sensor` `sudo systemctl start falcon-sensor`
7. Check the Falcon sensor agent ID: `sudo /opt/CrowdStrike/falconctl -g--aid` This command returns the agent ID string, which is a unique identifier assigned to this Falcon sensor. `aid="dbb*xxxxxxxxxxxxxxxxxxxxxxxxxx*226`" Make a note of the ID, which is useful for managing, tracking, and troubleshooting the sensor as it communicates with the CrowdStrike cloud.
8. Run the following `curl`command from the hosts running the Falcon sensor to make an HTTPS request to CrowdStrike cloud services and infrastructure: `curl -v https://ts01-gyr-maverick.cloudsink.net` The Falcon sensor communicates with the CrowdStrike cloud through secure TLS (SSL) connections and returns the server certificate. You might need to configure an SSL inspection policy in Internet & SaaS to bypass the CrowdStrike domains: *.cloudsink.net; *.csa.cloudsink.net; *.falcon.crowdstrike.comIf the domains are not bypassed, the Falcon sensor might detect tampering or certificate mismatches, causing registration failures or blocking sensor communication to the cloud. For more information, see [Configuring SSL Inspection Policy](https://help.zscaler.com/zia/configuring-ssl-inspection-policy).
9. Finally, verify your integration. In the CrowdStrike Falcon Console, go to **Host setup and management > Host management**. See image. Verify that the Zero Trust Branch topology matches the system configuration shown in the CrowdStrike Falcon Console. In this example, assume this is your system configuration:

[Image: Schematic showing example of Zero Trust Branch integration with CrowdStrike Falcon.]

CrowdStrike Falcon prevention policies are used to define and enforce rules on endpoints that control how the Falcon agent detects, blocks, and responds to various types of malware, exploits, and malicious behaviors. Their main purpose is to protect endpoints from threats by preventing malicious processes, suspicious behaviors, and known or unknown malware from executing on machines.

To create a new policy in CrowdStrike:

1. In the CrowdStrike Falcon Console, go to **Endpoint security > Prevention policies**.
2. Select your platform (e.g., Linux) from the drop-down menu and click **Create policy**.
3. Complete the information on the new policy and click **Submit**. See image.

CrowdStrike Falcon host groups are used to organize and manage sets of endpoints (hosts) within the CrowdStrike Falcon platform. They simplify policy assignment, sensor deployment, and operational control.

1. Create a host group:
  1. In the CrowdStrike Falcon Console, go to **Host setup and management** **>** **Host groups.**
  2. Click **Create new group**. Enter a name, description, and group type for the host group and click **Submit**. See image.
2. Add your Zero Trust Branch hosts to the host group:
  1. In the CrowdStrike Falcon Console, go to **Host setup and management > Host groups**.
  2. Select the host group to which you want to add your hosts, then click **Add hosts**. See image.
  3. Select the hosts to be added from the list and click **Add hosts**.
3. Assign policies to the host group: See image.
  1. In the CrowdStrike Falcon Console, go to **Host setup and management > Host groups**.
  2. Click the host group to which you want to assign policies, then click the **Policy assignment** tab.
  3. In the **Prevention policies** panel, click **Add policy**.
  4. Select the policy you want to add from the list and click **Assign policy**.

The CrowdStrike Zero Trust Assessment (ZTA) score is a real-time risk score from 0 to 100 for each device, indicating its overall security posture based on telemetry data from the CrowdStrike Falcon sensor, OS configurations, and user behavior. Higher scores signify better security, and this dynamic score can be integrated with Zscaler to enforce conditional access policies, ensuring that only high-trust devices can access sensitive resources.

To view the ZTA score for a host:

1. In the CrowdStrike Falcon Console, go to **Host setup and management > Zero Trust Assessment** and click the **Hosts by assessment score** panel. See image.
2. The **Overall assessment** column shows the ZTA score for each device. See image.

In Zero Trust Branch, the Secure Posture Score also provides a risk evaluation. To view the Secure Posture Score for your host:

1. In the Zscaler Admin Console, go to **Asset Intelligence > Assets**. See image.
2. Select a host to view its Secure Posture Score. See image.

To learn more, see [Managing Your Assets](https://help.zscaler.com/zero-trust-branch/managing-your-assets).

Zero Trust Branch supports several different types of objects used in the firewall policies to dynamically enforce control and prevent lateral movement of threats. Examples include:

- Endpoints with risk score > 70: Low risk with full application and network access
- Endpoints with risk score 30–70: Medium risk with limited access to applications and networks
- Endpoints with risk score < 30: High risk with no access to applications and networks

To create device objects for high-, medium-, and low-risk devices in the Zscaler Admin Console:

1. Go to **Resources > Objects**.
2. Click **Add**and select **Devices**from the drop-down menu. See image.
3. In the **Add Devices**panel, create device groups for each of the high-, medium-, and low-risk devices using the **Device Security Posture** attribute, which is synced from CrowdStrike during the integration process. For example: To learn more, see [Managing Objects](https://help.zscaler.com/zero-trust-branch/managing-objects).
  - High-risk device settings
  - Medium-risk device settings
  - Low-risk device settings
4. Go to **Firewall > Policies** and create policies that allow access based on device Secure Posture Score; for example: To learn more about creating firewall policies, see [Configuring Firewall Policies](https://help.zscaler.com/zero-trust-branch/configuring-firewall-policies).
  - Low-risk devices: Allow all protocols.
  - Medium-risk devices: Only allow ICMP.
  - High-risk devices: Block all protocols. See image.
5. Using our example setup, validate that Zero Trust Branch applies policies based on the learned Secure Posture Score, as shown in the following table:
  | Host | IP Address | Posture Score | Protocol | Action |
  | --- | --- | --- | --- | --- |
  | vlan10-dev1 | 192.0.2.1 | 17 | all | Reject |
  | vlan10-dev2 | 192.0.2.5 | 69 | icmp | Allow |
  | vlan10-dev3 | 192.0.2.10 | 100 | all | Allow |
  - Ping the high-risk host (vlan10-dev1) to verify that ICMP and Secure Shell (SSH) are blocked: `# **ping 192.0.2.1** PING 192.0.2.1 (192.0.2.1) 56(84) bytes of data. From 192.0.2.1 icmp_seq=1 Destination Port Unreachable From 192.0.2.1 icmp_seq=2 Destination Port Unreachable ^C ... 192.0.2.1 ping statistics ... 2 packets transmitted, 0 received, +2 errors, 100% packet loss, time 1000ms # **ssh admin@192.0.2.1** ssh: connect to host 192.0.2.1 post 22: Connection refused`
  - Ping the medium-risk host (vlan10-dev2) to verify that only ICMP is allowed: `# **ping 192.0.2.5** PING 192.0.2.5 (192.0.2.5) 56(84) bytes of data. 64 bytes from 192.0.2.5 icmp_seq=1 ttl=63 time=1.36 ms 64 bytes from 192.0.2.5 icmp_seq=2 ttl=63 time=1.01 ms 64 bytes from 192.0.2.5 icmp_seq=3 ttl=63 time=1.01 ms ^C ... 192.0.2.5 ping statistics ... 3 packets transmitted, 3 received, 0% packet loss, time 2003ms rtt min/avg/max/mdev = 1.012/1.126/1.356/0.162 ms # **ssh admin@192.0.2.5** ssh: connect to host 192.0.2.5 post 22: Connection timed out`
  - Ping the low-risk host (vlan10-dev3) to verify that ICMP and SSH are allowed: `# **ping 192.0.2.10** PING 192.0.2.10 (192.0.2.10) 56(84) bytes of data. 64 bytes from 192.0.2.10 icmp_seq=1 ttl=63 time=1.06 ms 64 bytes from 192.0.2.10 icmp_seq=2 ttl=63 time=1.03 ms ^C ... 192.0.2.10 ping statistics ... 2 packets transmitted, 2 received, 0% packet loss, time 1002 ms rtt min/avg/max/mdev = 1.033/1.047/1.061/0.014 ms # **ssh sudo admin@192.0.2.10** admin@192.0.2.10's password: Welcome to Ubuntu 24.04.1 LTS (GNU/Linux 6.8.0-41-generic x86_64)`

[Image: CrowdStrike Falcon dialog showing API secret]

[Image: Integrations page in the Zero Trust Branch Admin Portal highlighting the CrowdStrike Integrations Settings button]

[Image: CrowdStrike Integration panel in the Zero Trust Branch Admin Portal.]

[Image: CrowdStrike Integration panel in the Zero Trust Branch Admin Portal with Test button highlighted..]

[Image: Dialog box confirming that the CrowdStrike API integration was successful.]

[Image: CrowdStrike Falcon Console Host setup and management page highlighting the Download sensor button.]

[Image: Sensor downloads page in the CrowdStrike Falcon portal highlighting the customer ID.]

[Image: Host management page in the CrowdStrike Falcon Console.]

[Image: Prevention policies page in the CrowdStrike Falcon Console.]

[Image: Creating a host group on the Host groups page in the CrowdStrike Falcon Console.]

[Image: Host groups page in the CrowdStrike Falcon Console with the Add host button highlighted.]

[Image: Assigning policies to a host group on the Host groups page in the CrowdStrike Falcon Console.]

[Image: Zero Trust Assessment page in the CrowdStrike Falcon Console.]

[Image: Viewing the ZTA score in the CrowdStrike Falcon Console.]

[Image: Assets page in the Zero Trust Branch Admin Portal.]

[Image: Viewing the Secure Posture Score for an asset in the Zero Trust Branch Admin Portal.]

[Image: Add a Devices group on the Objects page in the Zero Trust Branch Admin Portal.]

[Image: Settings for high-risk devices in the Add Devices panel.]

[Image: Settings for medium-risk devices in the Add Devices panel.]

[Image: Settings for low-risk devices in the Add Devices panel.]

[Image: Viewing the Secure Posture Score for an asset in the Zero Trust Branch Admin Portal.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/integrating-sentinelone-zero-trust-branch","lastmod":"2026-07-15T16:21Z","nid":"1539720"} -->
## Integrating SentinelOne with Zero Trust Branch

- Source: https://help.zscaler.com/zero-trust-branch/integrating-sentinelone-zero-trust-branch
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust Device Segmentation > Third-Party Integrations > Integrating SentinelOne with Zero Trust Branch
- Last modified: 2026-07-15T16:21Z
- Summary: How to integrate SentinelOne with Zero Trust Branch.

Zero Trust Branch integrates with SentinelOne to provide unified visibility, control, and automated threat response across distributed enterprise environments. You can extend endpoint intelligence into network and branch-level security decisions with an API-based, policy-driven framework.

Zero Trust Branch leverages SentinelOne’s real-time endpoint telemetry and threat intelligence to enhance asset awareness and security posture within the branch. SentinelOne provides detailed device attributes, threat indicators, and behavioral data that enrich Zero Trust Branch's asset inventory and policy engines. Zero Trust Branch enforces segmentation, quarantine, or policy adjustments at the network level based on SentinelOne’s risk scores and incident detections.

This integration simplifies security management, enhances automation and response accuracy, and offers a complete zero trust posture to prevent lateral movement, minimize attack surfaces, and provide continuous verification across all assets, users, and applications.

## Integrating SentinelOne with Zero Trust Branch

Follow these steps to integrate SentinelOne with Zero Trust Branch:

- 1. Generate the SentinelOne API token.
- 2. Integrate the API token with Zero Trust Branch.
- 3. Set up the SentinelOne agent.
- 4. Verify the SentinelOne endpoints.
- 5. Verify the integration.

1. Log in to the SentinelOne portal.
2. Go to **Settings** > **Users** >**Actions** > **Add New User**. See image.
3. In the **Add a new user** window: See image. A new user link is sent to the email address mentioned in the previous step.
  1. Enter the **Full Name** and **Email Address** for the account.
  2. For **Role**, select **Viewer** from the drop-down menu.
  3. Click **Save**.
4. Open the email and click the new user link to confirm the account creation, and then enter a strong password for the user account.
5. Log in to the SentinelOne portal as the new user and accept the end user license agreement (EULA).
6. In the top-right corner, click your profile name and select **My User**. See image.
7. Go to**Action** > **API Token Operation** > **Generate API token**.
8. Enter the two-factor authentication code when prompted, then click **Confirm Action**. See image.
9. An API token displays. Click **Copy API Token** to copy the API token to the clipboard, then click **Close**. See image. The SentinelOne API token is now ready for use.

1. In the Zscaler Admin Console, go to **Infrastructure** > **Connectors** > **Edge** > **Integrations**.
2. On the **Integrations** page, click **Settings** under **SentinelOne Integration**. See image.
3. In the **SentinelOne Integration** window:
  1. Enter the **API Token** that you previously copied, and the **Base URL**.
  2. Click **Confirm**. See image.
  3. Next,click **Test** to verify the details. See image. A confirmation message appears indicating that the API token and Base URL are configured correctly.
4. Click **Save**.

To download the SentinelOne agent:

1. In the left-side navigation of the SentinelOne portal, click **Settings** > **SITES**. See image. The list of sites displays on the **Settings** page.
2. In the **Name** column, click the site name to go to the **SENTINELS** page. See image.
3. On the **SENTINELS** page, click the **PACKAGES** tab, then click the **Download** icon to download the required agent. See image.
4. On your Linux host system, open the terminal and run the following commands to install the agent: `-rw-rw-r-- 1 admin60 admin60 60786350 Oct 14 18:09 SentinelAgent_linux_x86_64_v25_2_2_14.deb admin60@pod-60-lnx-b:~/Downloads$ admin60@pod-60-lnx-b:~/Downloads$ sudo dpkg -i SentinelAgent_linux_x86_64_v25_2_2_14.deb [sudo] password for admin60: Selecting previously unselected package sentinelagent. (Reading database ... 276178 files and directories currently installed.) Preparing to unpack SentinelAgent_linux_x86_64_V25_2_2_14.deb ... Unpacking sentinelagent (25.2.2.14) ... Setting up sentinelagent (25.2.2.14) ...`
5. Set the SentinelOne URL and the site token: `sudo /opt/sentinelone/bin/sentinelctl management url set <sentinelone_URL> sudo /opt/sentinelone/bin/sentinelctl management token set '<site_token>'`
6. Start the service and check the agent's status.

1. In the left-side navigation of the SentinelOne portal, click **Settings** > **SITES**.
2. In the **Name** column, click the site name to go to the **SENTINELS** page.
3. On the **SENTINELS** page, click the **ENDPOINTS** tab to view the list of endpoints. See image.
4. Click the required device name. A drawer appears. By default, the **GENERAL** tab is selected and you can view the device details. See image.

1. In the Zscaler Admin Console, go to **Infrastructure** > **Connectors** > **Edge** > **Assets**.
2. On the **Assets** page, and in the **Device** column, click the required device name. See image. A drawer appears.
3. Click the **SentinelOne** tab and verify the integration details. See image.

[Image: Select the Add New User option]

[Image: Enter the user details]

[Image: Select the My User option]

[Image: Enter the two-factor authentication code]

[Image: Click Copy API Token]

[Image: Click Settings under SentinelOne Integration]

[Image: Click Test to verify the details]

[Image: Click Confirm after entering the API token and Base URL]

[Image: View the endpoints]

[Image: View the device details]

[Image: View the SentinelOne integration details]

[Image: Click the device name to view the integration details]

[Image: Click the SITES tab]

[Image: Download the agent from the PACKAGES tab]

[Image: Click the site name to go to the SENTINELS page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/interface-monitoring","lastmod":"2026-07-20T15:07Z","nid":"1525246"} -->
## Interface Monitoring

- Source: https://help.zscaler.com/zero-trust-branch/interface-monitoring
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Interface Monitoring
- Last modified: 2026-07-20T15:07Z
- Summary: How to use interface monitoring to avoid network disruptions in Zero Trust Branch.

Link failures can cause significant network disruptions. An intelligent switch, router, or firewall should identify failures and adjust routing decisions accordingly. Interface health monitoring sends continuous probes to a predefined target to monitor the liveliness of the interface link.

Zscaler routing policies are based on dynamic groups (e.g. device attributes, device score, etc.). These groups or objects are used to define segmentation policies. To learn more, see [Managing Objects](https://help.zscaler.com/zero-trust-branch/managing-objects).

Zero Trust Branch also supports link scoring on a scale of 0 to 30 based on jitter, latency, and packet loss. Since these measurements units are different, we normalize the result for a window of 30 iterations.

To enable monitoring on a site:

1. Go to **Deployments > Sites**.
2. In the**Site Name** column, click the name of the site that you want to monitor. See image.
3. On the site details page, click the **Interfaces**tab. Then in the **Monitoring status** column, click **Disabled** in the interface for which you want to enable monitoring. See image.
4. In the **Interface Health Monitor** panel: See image.
  - **Enable interface health monitoring**: Enable to start monitoring the health of this interface.
  - **Probe target (IP address or FQDN)**: Enter the IP address or FQDN of the interface that you want to monitor.
  - **Probe interval (seconds)**: Enter the probe interval in seconds.
  - **Retry count (number of attempts)**: Enter the number of times to retry the probe before timing out.
5. Click **Save**.

[Image: Accessing details for a site on the Sites page]

[Image: Viewing the Interfaces tab for a site]

[Image: Inteface Health Monitor panel]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/managing-api-keys-zero-trust-branch","lastmod":"2026-07-15T16:13Z","nid":"1535170"} -->
## Managing API Keys in Zero Trust Branch

- Source: https://help.zscaler.com/zero-trust-branch/managing-api-keys-zero-trust-branch
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust Device Segmentation > Third-Party Integrations > Managing API Keys in Zero Trust Branch
- Last modified: 2026-07-15T16:13Z
- Summary: How to create, view, and revoke API keys in Zero Trust Branch.

To integrate Zero Trust Branch with other applications using the Zero Trust Branch APIs, you must create a unique API key and then copy it into the other applications. You can view a list of keys you have created, but you can only view and copy the key itself once.

## Add an API Key

To add an API key, follow these steps:

1. In the Zscaler Admin Console, go to **Infrastructure > Connectors > Edge > Settings.** Click **Settings**in the **API Keys** panel and select **Add API Key** from the drop-down menu. See image.
2. In the **Add API Key** panel, enter a descriptive name and click **Add API Key**. See image.
3. The API key displays. Click the **Copy**icon to copy and save the API key. You need this key to complete your integration. See image. After you close the **Add API Key** panel, you are no longer able to view or copy the key.

[Image: Adding an API key from the Zscaler Admin Console Global settings page.]

[Image: Add API Key panel in theZscaler Admin Console with the Add API Key button highlighted.]

[Image: Add API Key panel in the Zscaler Admin Console with the Copy button highlighted.]

## View and Revoke API Keys

You can view a list of the API keys you have created and revoke them to sever the integration with the external application.

To view and revoke API keys, follow these steps:

1. In the Zscaler Admin Console, go to **Settings > Global.**Click **Settings**in the **API Keys** panel and select **View API Keys** from the drop-down menu. See image.
2. In the **API Keys** panel, you can view API keys you have created along with their creation dates. Note that the key itself can only be viewed when it is first created.
3. To revoke an API key, click the **Revoke**(trash can) icon. The key is deleted and any integrations using that key for authentication will no longer work. See image.

[Image: Viewing API keys from the Zero Trust Branch Admin Portal Global settings page.]

[Image: Revoking an API key from the Zero Trust Branch Admin Portal API Keys panel.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/managing-firewall-policies","lastmod":"2026-07-22T09:42Z","nid":"1509381"} -->
## Managing Firewall Policies

- Source: https://help.zscaler.com/zero-trust-branch/managing-firewall-policies
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Policy Management > Firewall Policies > Managing Firewall Policies
- Last modified: 2026-07-22T09:42Z
- Summary: Information about managing firewall policies in the Zscaler Admin Console.

You can view, edit, clone, delete, or organize firewall policies at global, template, or site levels. Thesystem (default) policiescreated for each site are editable but cannot be reordered.

## Editing a Policy

To edit a policy:

1. Go to **Policies** > **Access Control** > **Segmentation** > **Policies**. Click the tab for the scope (**Global Policies**, **Template Policies**, or **Site Policies**) that includes the policy you want to edit.
2. Locate the policy that you want to edit, click the corresponding **Gear**icon, and select **Edit**. See image.
3. In the **Edit Policy**drawer, make the necessary changes. See image.
4. Click **Save**. The policy changes are saved.
5. Click **Commit**to apply the changes. See image.

## Reordering Policies

You can reorder policies to change the order of evaluation.

To reorder policies:

1. Go to **Policies** > **Access Control** > **Segmentation** > **Policies**. Click the tab for the scope (**Global Policies**, **Template Policies**, or **Site Policies**) under which you want to reorder policies.
2. Click **Reorder**. See image. A **Drag**icon appears for each custom policy.
3. Use the **Drag**icon to rearrange the policies as required. You can either click and drag the policies using the **Drag**icon or click the **Drag**icon to select the policy name after which the policy must be inserted. See image.
4. Click **Save Policy Order**. See image.

## Cloning a Policy

You can clone an existing policy and create a new policy with minimal changes.

To clone a policy:

1. Go to **Policies** > **Access Control** > **Segmentation** > **Policies**. Click the tab for the scope (**Global Policies**, **Template Policies**, or **Site Policies**) under which you want to clone a policy.
2. Locate the policy that you want to clone, click the corresponding **Gear**icon, and select **Clone**. See image.
3. In the **Clone <policy_name> Policy**drawer, make the necessary changes. See image.
4. Click **Clone**. The new policy is added.
5. Click **Commit**to apply the changes. See image.

## Deleting a Policy

To delete a policy:

1. Go to **Policies** > **Access Control** > **Segmentation** > **Policies**. Click the tab for the scope (**Global Policies**, **Template Policies**, or **Site Policies**) that includes the policy you want to delete.
2. Locate the policy that you want to delete, click the corresponding **Gear**icon, and select **Delete**. See image.
3. In the delete confirmation window, enter `DELETE` and click **Confirm**. See image.
4. Click **Commit**to apply the changes. See image. The policy is removed from the Zscaler Admin Console.

[Image: Policies page showing the option to edit a policy]

[Image: Editing a firewall policy]

[Image: Policies page showing the option to commit changes]

[Image: Policies page showing the option to reorder firewall policies]

[Image: Policies page showing different methods to reorder firewall policies]

[Image: Policies page showing the option to save the order of policies]

[Image: Policies page showing the option to delete a policy]

[Image: Confirming the policy deletion]

[Image: Policies page showing the option to commit changes]

[Image: Policies page showing the option to clone a policy]

[Image: Cloning a firewall policy]

[Image: Policies page showing the option to commit changes]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/managing-objects","lastmod":"2026-07-22T09:04Z","nid":"1525226"} -->
## Managing Objects

- Source: https://help.zscaler.com/zero-trust-branch/managing-objects
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Policy Management > Objects > Managing Objects
- Last modified: 2026-07-22T09:04Z
- Summary: How to manage objects in the Zero Trust Branch Admin Portal.

You can view, edit, clone, or delete Airgap objects. Microsoft Active Directory (AD) objects are read-only objects.

## View Object Details

To view details of Airgap or Microsoft AD objects:

1. Go to **Resources**> **Objects**> **Airgap**or **Microsoft AD**.
2. Locate the object that you want to view, click the **Gear**icon, and select **Details**. See image. The **Group Details** drawer appears. See image.

## Edit an Object

To edit an Airgap object:

1. Go to **Resources**> **Objects**> **Airgap**.
2. Locate the object that you want to edit, click the **Gear**icon, and select **Edit**. See image.
3. In the edit object drawer, make the necessary changes to the object. See image.
4. Click **Save**.

## Clone an Object

To clone an Airgap object:

1. Go to **Resources**> **Objects**> **Airgap**.
2. Locate the object that you want to clone, click the **Gear**icon, and select **Clone**. See image.
3. In the clone object drawer, make the necessary changes to the object before cloning. See image.
4. Click **Clone**.

## Delete an Object

To delete an Airgap object:

1. Go to **Resources**> **Objects**> **Airgap**.
2. Locate the object that you want to delete, click the **Gear**icon, and select **Delete**. See image.
3. In the confirmation window, enter `DELETE` and click **Confirm**. See image.

[Image: Objects page showing the option to view object details]

[Image: Viewing group details]

[Image: Objects page showing the option to edit an object]

[Image: Editing an object]

[Image: Objects page showing the option to clone an object]

[Image: Cloning an object]

[Image: Objects page showing the option to delete an object]

[Image: Deleting an object]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/managing-routing-policies","lastmod":"2026-07-22T09:07Z","nid":"1532642"} -->
## Managing Routing Policies

- Source: https://help.zscaler.com/zero-trust-branch/managing-routing-policies
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Policy Management > Routing Policies > Managing Routing Policies
- Last modified: 2026-07-22T09:07Z
- Summary: How to manage routing policies in the Zscaler Admin Console.

You can view, edit, delete, or organize routing policies and configure routing settings when required.

## Editing a Policy

To edit a policy:

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Sites**.
2. On the **Sites**page, locate the entry for the site whose routing policy you want to edit, and click the name of the site in the **Site Name**column. See image.
3. On the site details page, click the **Routing Policy**tab.
4. Locate the policy that you want to edit, click the corresponding **Gear**icon, and select **Edit**. See image.
5. In the **Edit Routing Rule**drawer, make the necessary changes. See image.
6. Click **Save**. The policy changes are saved.
7. Click **Commit**to apply the changes. See image.

## Reordering Policies

You can reorder policies to change the order of evaluation.

To reorder policies:

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Sites**.
2. On the **Sites**page, locate the entry for the site whose routing policies must be reordered, and click the name of the site in the **Site Name**column. See image.
3. On the site details page, click the **Routing Policy**tab.
4. Click **Reorder**. See image. A **Drag**icon appears for each custom policy.
5. Click and drag each policy to the desired position using the **Drag**icon.
6. Click **Save policy reorder**. See image.

## Deleting a Policy

To delete a policy:

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Sites**.
2. On the **Sites**page, locate the entry for the site whose routing policy you want to delete, and click the name of the site in the **Site Name**column. See image.
3. On the site details page, click the **Routing Policy**tab.
4. Locate the policy that you want to delete, click the corresponding **Gear**icon, and select **Delete**. See image.
5. In the delete confirmation window, enter `DELETE` and click **Confirm**. See image. The policy is removed from the Zscaler Admin Console.

## Configuring Routing Settings

You can configure Zero Trust Branch to use local learned routes over Internet & SaaS (ZIA) and Private Access (ZPA) policies.

To configure route preference:

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Sites**.
2. On the **Sites**page, locate the entry for the site whose routing policy settings must be updated, and click the name of the site in the **Site Name**column. See image.
3. On the site details page, click the **Routing Policy**tab.
4. Click **Settings**. See image.
5. In the **Policy Routing Settings** drawer, enable or disable **Prefer local learned routes over the ZIA and ZPA policies**. See image.
6. Click **Save**. The routing preference is applied.

[Image: Selecting a site to manage routing policies]

[Image: Site details page showing the option to edit a policy]

[Image: Editing a routing rule]

[Image: Site details page showing the option to commit changes]

[Image: Selecting a site to manage routing policies]

[Image: Site details page showing the option to reorder policies]

[Image: Animation showing how to reorder routing policies]

[Image: Selecting a site to manage routing policies]

[Image: Site details page showing the option to delete policy]

[Image: Deleting a routing policy]

[Image: Selecting a site to manage routing policies]

[Image: Site details page showing the option to configure routing policy settings]

[Image: Configuring policy routing settings]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/managing-sites","lastmod":"2026-07-22T16:11Z","nid":"1525146"} -->
## Managing Sites

- Source: https://help.zscaler.com/zero-trust-branch/managing-sites
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Managing Sites
- Last modified: 2026-07-22T16:11Z
- Summary: How to add new sites, manage site-specific DNS configurations, and configure static routes in Zero Trust Branch.

Sites are where Zero Trust Branch appliances are deployed. From the Zscaler Admin Console, you can add new sites, manage site-specific DNS configurations, and configure static routes. To learn more about templates, see [Managing Templates](https://help.zscaler.com/zero-trust-branch/managing-templates).

If you are adding a high availability (HA) site, see the appropriate guide for additional configuration steps after adding the site:

- For a standard HA cluster (active-standby), see [Creating a Zero Trust Branch High Availability Cluster](https://help.zscaler.com/zero-trust-branch/creating-zero-trust-branch-high-availability-cluster).
- For an Advanced HA cluster (active-active), see [Creating a Zero Trust Branch Enhanced WAN Edge High Availability Cluster](https://help.zscaler.com/zero-trust-branch/creating-zero-trust-branch-enhanced-wan-edge-high-availability-cluster).

## Adding a Site

The example shown in this procedure uses a custom standalone template, a new Internet & SaaS (ZIA) location, and DHCP for the WAN interface IP address.

To add a site, complete the following steps in the Zscaler Admin Console:

1. Go to **Infrastructure > Connectors > Edge > Sites**.
2. On the **Sites** page, click **Add Site**. See image.
3. In the **Add Site**panel: See image.
  - **Name**: Enter a name to identify the site.
  - **Platform**: Select the hardware model or virtual machine for this site.
  - **Template**: Select the template to use for this site. It can be a template that you cloned and customized or a default template.
  - **WAN DNS Servers**: Review the DNS servers used by Zero Trust Branch. The template populates this field, but you can override it for a site that uses a cloned and customized template.
  - Select the Internet & SaaS location for this site and provide the requested information:
    - **Existing Location**: Select an existing Internet & SaaS location for this site. To learn more, see [About Locations](https://help.zscaler.com/zia/about-locations).
    - **New Location**: Enter a name for this location and select the country where it resides. Zero Trust Branch saves this location in your Internet & SaaS tenant and configures an Internet Protocol Security (IPSec) virtual private network (VPN) tunnel to that location.
    - **None**: Select this option if the site does not have a physical location.
  - **DHCP Service**: This field defines whether the Zero Trust Branch operates as a **DHCP Server** or a **DHCP Relay**. The template populates this field, but you can override it for a site that uses a cloned and customized template.
  - **DHCP Server IP Address**: If the **DHCP Service** field displays **DHCP Relay**, enter the IP address of the DHCP server.
  - **Gateway 1**: Enter the site-specific information for the primary gateway:
    - **Name**: Enter a name to identify the primary gateway. By default, the name is appended with `-gw-01`, but you can enter any name to identify the primary gateway.
    - **WAN Interface**: Select the WAN interface.
    - **Use DHCP for IP**: Enable to use DHCP to obtain the IP address. If you do not enable DHCP, enter the following details:
    - **WAN IP Address**: Enter the WAN IP address.
    - **WAN Prefix Length**: Enter the WAN prefix length (subnet mask).
    - **Default Gateway IP Address**: Enter the gateway IP address.
  - **Gateway 2**: If you are using an HA template, a **Gateway 2** section appears. Complete this section for the secondary gateway, as described previously for **Gateway 1**. By default, the gateway name is appended with `-gw-02`, but you can enter any name to identify the secondary gateway.
4. Click **Add**to add the site.
5. The **Add Site** panel displays the site URL and activation code. Copy both values and paste them somewhere safe. You need them when you activate the appliance. See image.

## Managing Site DNS Settings

You can view and manage the DNS configuration for an existing site.

To review and configure the DNS servers:

1. Go to **Infrastructure > Connectors > Edge > Sites**.
2. In the **Site Name** column, click the name of the site you want to manage.
3. Click the **Settings** tab, then click **DNS**.
4. View or edit the following fields: See image.
  - **WAN DNS Servers**: Review and configure the WAN DNS servers used for site activation and local system processes.
    - To add a server, enter the IP address and click **Add**.
    - To remove a server, click the **X** next to the DNS address.
  - **Private DNS Servers**: Review and configure the private DNS servers used to resolve both private namespaces and public domains.
    - To add a server, enter the IP address and click **Add**.
    - To remove a server, click the **X** next to the DNS address.
5. Click **Save** to apply any changes.

[Image: Site DNS settings page showing options to review and configure WAN and Private DNS servers.]

## Configuring Static Routes for a Site

You can add static routes to define manual paths for network traffic and choose whether to share them across your SD-WAN fabric.

To configure static routes for a site:

1. Go to **Infrastructure > Connectors > Edge > Sites**.
2. In the **Site Name** column, click the name of the site you want to configure.
3. Click the **Settings** tab, then click **Static Routes**.
4. Click **Add route**. See image.
5. In the **Add Static Route** window: See image.
  - **Name**: Enter a name for the static route.
  - **Share Over RT**: Enable this toggle to advertise the route to other sites and the hub over site-to-site routed tunnels.
  - **Network/Mask**: Enter the destination network and subnet mask in CIDR format.
  - **Nexthop Type**: Select the nexthop type:
    - **IP**: Directs traffic to a specific IP address.
    - **Interface**: Directs traffic to specific gateway interfaces. For HA configurations, users can select interfaces for both gateways to ensure path redundancy.
      - **Gateway:**`**<site-name>**`**-gw-01**: Select interfaces for a gateway to ensure path redundancy.
      - **Gateway:**`**<site-name>**`**-gw-02:** Select interfaces for a gateway to ensure path redundancy.
  - **Nexthop IP Address**: Enter the IP address of the nexthop gateway.
  - **Metrics**: Enter a metric value to determine routing priority if multiple routes exist for the same destination.
6. Click **Save**.

You can also enable or disable the **Share Over RT** option directly from the toggle on the **Static Routes** list page.

See image.

[Image: Site Static Routes page listing routes and annotation for Share Over RT toggle.]

[Image: Site Static Routes page displays the list of added static routes and an annotation for Add route button]

[Image: Add Static Route window showing details to add a static route for a site.]

[Image: Sites page with Add Site button and list of existing sites]

[Image: Add Site panel populated with values to add a site for a standalone ZT400 appliance using DHCP]

[Image: Add Site panel showing the activation code and URL]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/managing-snmp-configurations","lastmod":"2026-07-12T07:06Z","nid":"1532443"} -->
## Managing SNMP Configurations

- Source: https://help.zscaler.com/zero-trust-branch/managing-snmp-configurations
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Analytics & Monitoring > Managing SNMP Configurations
- Last modified: 2026-07-12T07:06Z
- Summary: Managing SNMP Configurations in Zero Trust Branch.

Zero Trust Branch supports the Simple Network Management Protocol (SNMP) standard for network monitoring and management. You can use the following standard management information bases (MIBs):

- IF-MIB
- SYS-MIB
- Standard Linux MIBs

Custom MIBs are not supported.

If you are monitoring branch appliances remotely from Private Access (ZPA), Zscaler recommends using the appliances' Management IP addresses for SNMP queries. This allows remote monitoring without manually adding multiple LAN IP addresses or subnets.

Both SNMPv2 and SNMPv3 are supported. Zscaler recommends SNMPv3 for better security, and it supports more MIBs. You can enable both SNMP versions on Zero Trust Branch simultaneously, but only one version can be active on a site at a time. Legacy versions (7.x and earlier) use SNMPv2, while later versions use SNMPv3.

- Configure SNMPv2.
- Configure SNMPv3.

1. Go to **Infrastructure**> **Connectors**> **Edge**> **Settings**.
2. Locate the **SNMP Configurations** tile and click **Settings**. See image.
3. In the **SNMP Settings**drawer, select **Enable SNMPv2**. The following additional fields appear: See image.
  - **Community**: Enter the community string (plain-text password) to access network devices via SNMPv2.
  - **Client IPs**: Enter the client IP addresses that will do SNMP monitoring via SNMPv2.
  - **SNMP MIB (Management Information Base)**: Click the **Download**icon to download the MIB to your computer.
4. Click **Save**.
5. To verify the configuration, from the SNMP server, run the command `snmpwalk` for the IP address of the WAN management interface to view the MIBs. See image.

To configure SNMPv3, enable it and manage its configuration via SNMP profiles.

1. Go to **Infrastructure**> **Connectors**> **Edge**> **Settings**.
2. Locate the **SNMP Configurations** tile and click **Settings**. See image.
3. In the **SNMP Settings**drawer, select **Enable SNMPv3**.
4. Click **Save**. See image.
5. Back in the **SNMP Configurations** tile, click **Manage SNMPv3** to open the **SNMPv3**page. See image.
6. On the **SNMPv3** page, click **Add SNMP Profile** to create a new profile, or click the **Gear**icon to edit an existing profile. See image.
7. In the **Add SNMP Profile** (or **Edit SNMP Profile**) drawer, complete the information on all tabs.
  1. On the **SNMP Profile Info** tab: See image.
    - **Profile name**: Enter a name for this profile.
    - **Profile description**: Enter a description for this profile.
  2. Click **Next.**
  3. On the **Security Group & View**tab, provide information about this profile's security level, the object identifiers (OIDs) you are monitoring, and the login information for this profile. OIDs are unique identifiers used to identify specific variables included in SNMP MIBs, such as device serial numbers, interface status, memory, temperature, and so on. See image.
    - In the **Security Group Details**section:
      - **Choose or create a new security group**: Select an existing group from the drop-down menu or select **Create a new group**.
      - **Security Group Name**: If creating a new group, enter a name for the group. If using an existing group, the current name displays.
      - **Choose Security Group Level**: Select the security group level for this group: `authPriv` (provides authentication and encryption) or `authNoPriv` (provides authentication but no encryption).
    - In the **View Details**section:
      - **Choose or create a new security view**: Select an existing security view from the drop-down menu or select **Create a new view**.
      - **View name**: If creating a new view, enter a name for the view. If using an existing view, the current name displays.
      - **OIDs to include**: Enter one or more OIDs that you want to include in this security view and click **Add** after each one.
      - **OIDs to exclude**: Enter one or more OIDs that you want to exclude from this security view and click **Add** after each one.
    - In the **User Details**section:
      - **Username**: Enter the username to log in to SNMPv3 for this profile.
      - **Authentication protocol**: Select the authentication protocol to use with this username and password, such as `sha`.
      - **Authentication Password**: Enter the password for this username.
      - **Privacy method**: If you selected `authPriv` as the security level, select the encryption algorithm, e.g., `aes` (Advanced Encryption Standard).
      - **Authpriv Password**: If you selected `authPriv` as the security level, select the password for this encryption method.
  4. Click **Next.**
  5. On the **Trap**tab, enter information about the SNMP trap for this profile. An SNMP trap is an asynchronous message sent from the device to the SNMP manager when a critical event happens, such as a link down, high CPU, power failure, and so on. See image.
    - **Choose or create a new trap**: Select an existing trap from the drop-down menu or select **Create a new trap**.
    - **Trap Name**: If creating a new trap, enter a name for the trap. If using an existing trap, the current name displays.
    - **Receiving server IP address**: Enter the IP address at which the SNMP manager will receive SNMP traps.
    - **Receiving server port**: Enter the UDP port where the SNMP manager is listening for SNMP traps. Typically, port 162 is used. Port 161 is used for queries from SNMP managers. Both ports must be enabled on the firewall.
  6. Click **Next.**
8. Click **Save configuration**.
9. To apply a profile to a site, go to **Infrastructure**> **Connectors**> **Edge**> **Sites** and click the name of the site you want in the **Site Name**column. See image.
10. On the site details page, click the **Settings**tab, then click **SNMPv3**in the left-side navigation. Then select the SNMPv3 profile you want to use from the drop-down menu and click **Save**. See image.
11. To verify the configuration, from the SNMP server, run the command `snmpwalk` for the IP address of the device to view the MIBs. See image.

[Image: Accessing SNMP Configurations Settings on the Global settings page]

[Image: Accessing SNMP Configurations Settings on the Global settings page]

[Image: Configuring SNMP settings]

[Image: Configuring SNMP settings]

[Image: Accessing SNMP Configurations Settings on the Global settings page]

[Image: Adding and editing SNMP profiles on the SNMPv3 page]

[Image: Accessing SNMP Configurations Settings on the Global settings page]

[Image: Accessing SNMP Configurations Settings on the Global settings page]

[Image: Accessing SNMP Configurations Settings on the Global settings page]

[Image: Sample iptables output used to verify SNMP configuration]

[Image: Sites page with a site highlighted]

[Image: Configuring site settings]

[Image: Sample iptables output used to verify SNMP configuration]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/managing-templates","lastmod":"2026-07-22T09:05Z","nid":"1525136"} -->
## Managing Templates

- Source: https://help.zscaler.com/zero-trust-branch/managing-templates
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Policy Management > Templates > Managing Templates
- Last modified: 2026-07-22T09:05Z
- Summary: How to manage site templates in Zero Trust Branch.

In a large-scale global deployment of Zero Trust Branch, managing each site individually is time-consuming and prone to errors. Zscaler provides a set of templates so that you can build sites quickly. You can also create custom templates based on the default set. Templates support both standalone and high availability (HA) configurations.

After you have created or selected the template you want to use, see[Adding a Site](https://help.zscaler.com/zero-trust-branch/adding-site) to create a site using that template.

## Predefined Templates

To start creating Zero Trust Branch sites, Zscaler provides templates for virtual machine (VM) and supported devices in both standalone and HA configurations. To learn more, see [Deployment Overview](https://help.zscaler.com/zero-trust-branch/deployment-overview).

You cannot delete or edit default templates, but you can clone them.

## Managing Templates

You can perform the following actions from the Templates page (Infrastructure > Connectors > Edge > Site Templates):

- Add a New Template
- Edit a Template
- Clone a Template
- Delete a Template

To add a new template:

1. Click **Add Template** in the upper-right corner. See image.
2. In the**Add Template** panel: See image.
  - **Name**: Enter a name to identify the template.
  - **Primary WAN DNS**: Enter the IP address of the primary WAN DNS server for this template.
  - **Secondary WAN DNS**: (Optional) Enter the IP address of the secondary WAN DNS server for this template.
  - **DHCP Service**: If this template is for a DHCP server, select **server**; for a relay to a server, select **relay**.
  - **Deployment Type**: Select the deployment mode this template will be used for:
    - **standalone**: For sites with a single appliance.
    - **standard_mode_ha**: For a standard HA cluster operating in active-standby mode.
    - **enhanced_mode_ha**: For an Advanced HA (WAN Edge HA) cluster that supports an active-active configuration for WAN links. To learn more, see [Creating a Zero Trust Branch Enhanced WAN Edge High Availability Cluster](https://help.zscaler.com/zero-trust-branch/creating-zero-trust-branch-enhanced-wan-edge-high-availability-cluster).
  - **Platform [Gateway-1]**: Select the platform this template will be used for: either **vm**for a VM or the device type.
  - **Platform [Gateway-2]**: If you select an HA deployment, select the platform for the secondary gateway. You can have a different platform (e.g., VM for an appliance) for the secondary gateway.
  - **NAT Enabled**: Enable if your Zero Trust Branch appliance uses NAT to route traffic leaving the branch appliance toward the non-Zero Trust Branch network.
3. Click **Save**.

To edit a template:

1. Click the name of the template you want to edit.
2. On the template details page, manage the following tabs:
  - **Overview**: View the template properties and the number of sites currently using the template.
  - **Settings**: Configure global behavior for associated sites: See image.
    - **DHCP Service**: Choose whether the template functions as a **DHCP Server** or **DHCP Relay**.
    - **NAT**: Use the toggle to enable or disable NAT configuration.
    - **DNS**: Manage the WAN DNS configuration. Configure the **Primary WAN DNS** and **Secondary WAN DNS** IP addresses.
    - Click **Save** after making any changes in this tab.
  - **Interfaces**: Manage the interfaces for the template gateways. Click **Add** to configure new interfaces or click the **Gear** icon to delete an interface. To learn more, see [Understanding Bonding Interfaces](https://help.zscaler.com/zero-trust-branch/understanding-bonding-interfaces).
  - **Routing Policy**: Manage the routing rules for the template. To learn more, see [Configuring Routing Policies](https://help.zscaler.com/zero-trust-branch/configuring-routing-policies).
  - **Policies**: Manage the firewall policies for the template. To learn more, see [Configuring Firewall Policies](https://help.zscaler.com/zero-trust-branch/configuring-firewall-policies).

To clone a template:

1. Click the **Gear**icon next to a template and select **Clone**.
2. Enter a name for the cloned template and click **Save**.

To delete a template:

1. Click the **Gear**icon next to a template and select **Delete**.
2. In the **Delete**dialog box, enter `DELETE` and click **Confirm**to delete the template.

[Image: Templates page showing the templates along with their details and an annotation for the Add Template button.]

[Image: Add Template panel]

[Image: Edit Template panel]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/managing-your-assets","lastmod":"2026-07-13T15:35Z","nid":"1509846"} -->
## Managing Your Assets

- Source: https://help.zscaler.com/zero-trust-branch/managing-your-assets
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust Device Segmentation > Asset Management > Managing Your Assets
- Last modified: 2026-07-13T15:35Z
- Summary: How to view and edit Zero Trust Branch assets.

The Assets section provides admins with visibility into all endpoints connected to the network along with their health status.

Zero Trust Branch leverages multiple techniques (e.g., DHCP options, HTTP User-Agent, SSL fingerprint, mDNS) to help automatically identify assets. In addition, the application integrates with third-party vendors such as Armis to further enhance the accuracy of the device discovery.

After an asset has been discovered, Zero Trust Branch tags the asset automatically. If you have integrated Zero Trust Branch with third-party vendors (e.g., Armis), then additional tags are created. You can also assign custom tags to assets. To learn more, see [Working with Tags](https://help.zscaler.com/zero-trust-branch/working-with-tags).

## View Assets

To view assets:

1. Go to **Asset Intelligence > Assets**.
2. In the upper right, select how you want to view the assets.
  - View Assets by Type
  - View Assets by Category
  - View Assets in a List
3. To view more detail on an asset, click the link for that asset in any list (either all assets or for a specific type or category). See image.

## Edit a Device

To edit a device:

1. View the assets in a list view (either all assets or for a specific type or category).
2. Select the checkbox next to one or more devices and click **Edit**. See image.
3. In the edit device drawer, make the desired changes and click **Apply**. If you are editing multiple devices, your changes affect all devices. See image. The **Protection**drop-down menu allows you to select among three different solutions: **Airgap**, **Airgap-Lite**, and **Airgap+**. To learn more, see [Understanding Protection Solutions](https://help.zscaler.com/zero-trust-branch/understanding-protection-solutions).

## Quarantine a Device

If an unfamiliar device appears on the Assets page, you can put that device into quarantine while you perform additional investigation. A device placed into quarantine can access the internet but not the private networks. Inbound connectivity is also allowed so that an admin could remotely connect to the quarantined device.

To place a device into quarantine, edit the device as described previously, and then select **Yes**from the **Quarantined** drop-down menu, and click **Apply**.

Select **Type**to view assets grouped by device type. Click any type to open a list of assets of that type.

See image.

Select **Category**to view assets grouped by device category. Click any category to open a list of assets of that category.

See image.

Select **List**to view assets in a detailed list.

See image.

[Image: Viewing asset details from the Assets page.]

[Image: Viewing assets by type on the Assets page.]

[Image: Viewing assets by category on the Assets page.]

[Image: Viewing a list of assets on the Assets page.]

[Image: Editing an asset on the Assets page.]

[Image: Edit device drawer on the Assets page.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/release-upgrade-summary-2025","lastmod":"2026-01-28T01:24Z","nid":"1506291"} -->
## Release Upgrade Summary (2025)

- Source: https://help.zscaler.com/zero-trust-branch/release-upgrade-summary-2025
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Release Notes > Release Upgrade Summary (2025)
- Last modified: 2026-01-28T01:24Z
- Summary: Zero Trust Branch Release Upgrade Summary for service updates deployed in 2025.

This article provides a summary of all new features and enhancements for Zero Trust Branch.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/release-upgrade-summary-2026","lastmod":"2026-08-07T15:27Z","nid":"1534294"} -->
## Release Upgrade Summary (2026)

- Source: https://help.zscaler.com/zero-trust-branch/release-upgrade-summary-2026
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Release Notes > Release Upgrade Summary (2026)
- Last modified: 2026-08-07T15:27Z
- Summary: Zero Trust Branch Release Upgrade Summary for service updates deployed in 2026.

This article provides a summary of all new features and enhancements for Zero Trust Branch.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/resolving-zero-trust-branch-configuration-activation-issues","lastmod":"2026-07-31T07:06Z","nid":"1540224"} -->
## Resolving Zero Trust Branch Configuration and Activation Issues

- Source: https://help.zscaler.com/zero-trust-branch/resolving-zero-trust-branch-configuration-activation-issues
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Troubleshooting > Resolving Zero Trust Branch Configuration and Activation Issues
- Last modified: 2026-07-31T07:06Z
- Summary: How to resolve issues related to the Zero Trust Branch appliance.

This article includes steps to resolve some of the following issues that you might encounter while configuring and activating Zero Trust Branch appliances:

- Location Is Not Recognized
- Activation Issues
- Connectivity Issues
- High Availability (HA) Is Not Working
- Devices Are Not Receiving the IP Address
- Devices Are Not Connecting to the Internet
- East-West Traffic Is Not Working
- Segmentation Policy Is Not Working
- Gateway Shows as Disconnected

While adding a site, the location might not be recognized, and the following error message displays: "ZIAM SendLocationRequest: Error creating location response." To resolve this error:

1. In the Zscaler Admin Console, go to **Infrastructure** > **Locations** > **Locations**.
2. Check whether the site or location name already exists in Internet & SaaS (ZIA).

Verify the following to resolve any activation issues:

- The WAN interface is connected correctly and can communicate with the internet.
- The activation code is visible on the Airgap console.
- DNS can resolve:
  - `gwactivation.goairgap.com`
  - `<customer name>-api.goairgap.com`
  - `hub3.goairgap.com`
- Web proxy is configured.

If the gateway is not working after an update and DNS cannot resolve `*-api.goairgap.com`:

1. In the Zscaler Admin Console, go to **Infrastructure** > **Connectors** > **Edge** > **Sites**.
2. On the**Sites** page, delete the required site.
3. Next, reconfigure the site, gateway, network, and web proxy, and then check the connectivity again.

If there is an activation issue, the Zero Trust Branch appliance status appears red in the Zscaler Admin Console. This indicates that the communication between the appliance and the Zscaler Admin Console is severed, and the appliance cannot communicate with the internet.

To resolve the activation issue:

- Check whether the WAN IP address is configured properly (ping from an upstream device).
- Verify that the appliance is allowed to send outbound connections to the following ports:
  - hub.goairgap.com (TCP: 1883)
  - wg.goairgap.com (UDP: 51820)
- Disable the App ID, Intrusion Detection System (IDS), Intrusion Prevention System (IPS), and SSL Forward Proxy (FP) for the control traffic (initiated from the appliance) on the upstream L3 device.
- Verify the alarms and audit logs to see whether the appliance's storage utilization has crossed the threshold level.

- For appliances in standby-standby mode, check the following:
  - The LAN-side interfaces on both appliances are up and connected to the switch on trunk ports.
  - The optics or DAC interoperability with Cisco (link up, interface statistics, and packet capture) are appropriate.
- For appliances in active-active mode, check the following:
  - Appliances can reach each other over the HA interface.
  - The switch connecting to the HA interface is not blocking the VRRP messages.
  - There are no MAC move or snooping violations on the switch connecting to the HA interface.
  - The Gratuitous Address Resolution Protocol (GARP) messages from the appliances are not blocked.
  - The SVI interface for the Airgapped VLAN on the switch is disabled.

If the appliance is a DHCP server:

- Check whether the packets are reaching the appliance that is appropriately tagged. In the Airgap console, select Troubleshooting (5) > Packet Capture (7) to verify the packets.
- Check whether the VLAN is configured correctly on the appliance. Run the `ifconfig` command to list the LAN interface.
- Try a manual switchover.

If the appliance is a DHCP relay:

- Check whether the packets are reaching the appliance that is appropriately tagged. In the Airgap console, select Troubleshooting (5) > Packet Capture (7) to verify the packets.
- Check whether the VLAN is configured correctly on the appliance. Run the `ifconfig` command to list the LAN interface.
- Ensure the DHCP server has a route and can reach the Airgapped subnet.
- Ensure there are no other DHCP servers or relays running in the Airgapped VLAN.

Devices can receive the IP address and ping the Zero Trust Branch appliance's default gateway but cannot reach the internet. This occurs when the upstream firewall or router does not have a return route to the Airgapped VLAN with the appliance WAN virtual IP (VIP) address as a nexthop.

- Verify that policies on the appliance are blocking the internet-bound traffic.
- If border gateway protocol (BGP) is running between the appliance and the upstream firewall, ensure the peer is in an established state, and routes are accepted on the upstream firewall.
- In the Airgap console, run the `show bgp summary` command to check the BGP status.
- Ensure the devices do not have an alternate path (secondary NIC), or an asymmetric routing in the network.
- If the link aggregation control protocol (LACP) is configured, ensure that it is configured on the appliance and core switch.

If Network Address Translation (NAT) is enabled, check whether the packets egressing the WAN interface have the source IP address of the WAN interface. In the Airgap console, select Troubleshooting (5) > Packet Capture (7). The packet capture must show the outgoing packets with the source IP address of the WAN interface.

- Verify that the east-west traffic is allowed in the Airgap segmentation policies.
- For a Windows test device, verify that the host firewall is not blocking the inbound traffic (Windows has three firewall profiles).
- Start the packet capture on the appliance and ensure that it is receiving the traffic and routing it back appropriately.
- Verify that the device and appliance are reachable.

Verify that the policy hit count is increasing (requires 7.7.x version).

To verify the policies:

1. In the Zscaler Admin Console, go to **Logs** > **Insights** > **Zero Trust Branch** **-** **Flow Logs**.
2. On the **Flow Logs** page, filter for the source IP address or destination IP address and verify the policy executed for the traffic. If you don’t see the flow logs, disable log throttling for the specific policies and verify the flow logs again.
3. Verify that the source groups and destination groups are populated correctly, and members are associated with these groups.
4. Go to **Administration** > **Alerts** > **Alarms** and check for any policy sync errors. Check whether any other policy is overriding the policy being executed. If you are testing with Windows Internet Control Message Protocol (ICMP), wait for the session to close (5 minutes) and try again. Windows ICMP uses the same ICMP ID.

1. In the Airgap console, enter `4` (Show Gateway Status) and press `Enter` to check the gateway status. The output shows Python errors. See sample output.
2. Check the WAN interface configuration:
  1. In the Zscaler Admin Console, go to **Infrastructure** > **Connectors** > **Edge** > **Sites**.
  2. On the**Sites** page, click the required site.
  3. On the site details page, click the **VLANs** tab, then click the **Gear** icon for the required VLAN.
  4. In the **Edit Airgap VLAN** window, change the WAN interface configuration (DHCP or Static) as required, then click **Save**.
3. In the Airgap console, enter `1` (Configure Gateway), press`Enter`, and then configure the WAN interface as in the previous step. Sample command and output: `WAN interface (? to list available options) [ge5]: WAN VLAN ID (1..4092) [1]: Use DHCP for WAN interface ge5 (Y/y or N/n) [n]: y Use DHCP to set WAN nameservers (Y/y or N/n) [n]: n WAN Nameservers [8.8.8.8, 4.4.2.2]: Management Interface: ge1 Management IP Address: not configured WAN Interface: ge5 WAN IP Address: DHCP WAN Nameservers: 8.8.8.8, 4.4.2.2 Web Proxy: None Apply Configuration? (y/n)y`
4. Enter `4` (Show Gateway Status) and press `Enter` to check the gateway status again, and it should start working. Sample output: `Gateway State : Standby WAN Connectivity: Checking Default Gateway(100.120.1.254) ...OK Checking reachability to internet...OK Web Proxy is not configured. Checking Management Portal reachability...OK Checking Analytics connection...OK Checking Debug Port reachability...OK Services Status: Service State Network Poller active (running) since Thu 2026-01-15 02:13:01 UTC; 3 weeks 4 days ago DHCP Server active (running) since Tue 2026-02-03 02:25:36 UTC; 6 days ago Network Status: NAMES IMAGE STATUS strongswan docker.repos.goairgap.com/7-8/strongswan:latest Up 3 minutes dnsproxy_container docker.repos.goairgap.com/7-8/dnsproxy:8.0.8P1 Up 47 minutes vyos_container docker.repos.goairgap.com/7-8/vyos:1.4.4-sagitta Up 52 minutes policy_container docker.repos.goairgap.com/7-8/poller:8.0.8P1 Up 53 minutes`
5. If the appliance is not reaching the cloud, run the following command in the Airgap console: `tail -f /var/log/syslog` If there is a name resolution error, then resolve this error before the appliance reaches the cloud. Also, check whether the upstream L3 device is blocking the DNS (UDP 53) port.

```
Gateway State : Standby
WAN Connectivity: 
Checking Default Gateway(100.120.1.254) ...FAILED
Routing Debug Information:
Route to 8.8.8.8
8.8.8.8 via 100.120.1.254 dev ge5 src 169.254.255.254 uid 0 
   cache
Default Route
default via 100.120.1.254 dev ge5 proto static metric 200 onlink
WAN link status
ge5: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
       inet6 fe80::290:bff:feb2:5da7  prefixlen 64  scopeid 0x20<link>
       ether 00:90:0b:b2:5d:a7  txqueuelen 1000  (Ethernet)
       RX packets 32383644  bytes 15557031433 (15.5 GB)
       RX errors 0  dropped 0  overruns 0  frame 0
       TX packets 28881099  bytes 3819410647 (3.8 GB)
       TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

MgmtControllerClient - mgmt_controller.py:540 - ERROR - Failed to get authorization token HTTPSConnectionPool(host='ztblab-api.goairgap.com', port=443): Max retries exceeded with url: /api/v2/auth/login (Caused by NameResolutionError("<urllib3.connection.HTTPSConnection object at 0x7fe54aa33f40>: Failed to resolve 'ztblab-api.goairgap.com' ([Errno -2] Name or service not known)"))
Traceback (most recent call last):
 File "/usr/local/lib/python3.8/dist-packages/urllib3/connection.py", line 199, in _new_conn
   sock = connection.create_connection(
 File "/usr/local/lib/python3.8/dist-packages/urllib3/util/connection.py", line 60, in create_connection
   for res in socket.getaddrinfo(host, port, family, socket.SOCK_STREAM):
 File "/usr/lib/python3.8/socket.py", line 918, in getaddrinfo
   for res in _socket.getaddrinfo(host, port, family, type, proto, flags):
socket.gaierror: [Errno -2] Name or service not known
The above exception was the direct cause of the following exception:
Traceback (most recent call last):
 File "/usr/local/lib/python3.8/dist-packages/urllib3/connectionpool.py", line 789, in urlopen
   response = self._make_request(
 File "/usr/local/lib/
python3
.8/dist-packages/urllib3/connectionpool.py", line 490, in _make_request
   raise new_e
 File "/usr/local/lib/
python3
.8/dist-packages/urllib3/connectionpool.py", line 466, in _make_request
   self._validate_conn(conn)
 File "/usr/local/lib/
python3
.8/dist-packages/urllib3/connectionpool.py", line 1095, in _validate_conn
   conn.connect()
 File "/usr/local/lib/
python3
.8/dist-packages/urllib3/connection.py", line 693, in connect
   self.sock = sock = self._new_conn()
 File "/usr/local/lib/
python3
.8/dist-packages/urllib3/connection.py", line 206, in _new_conn
   raise NameResolutionError(self.host, self, e) from e
urllib3.exceptions.NameResolutionError: <urllib3.connection.HTTPSConnection object at 0x7fe54aa33f40>: Failed to resolve 'ztblab-api.goairgap.com' ([Errno -2] Name or service not known)
The above exception was the direct cause of the following exception:
Traceback (most recent call last):
 File "/usr/local/lib/
python3
.8/dist-packages/requests/adapters.py", line 667, in send
   resp = conn.urlopen(
 File "/usr/local/lib/
python3
.8/dist-packages/urllib3/connectionpool.py", line 843, in urlopen
   retries = retries.increment(
 File "/usr/local/lib/
python3
.8/dist-packages/urllib3/util/retry.py", line 519, in increment
   raise MaxRetryError(_pool, url, reason) from reason  # type: ignore[arg-type]
urllib3.exceptions.MaxRetryError: HTTPSConnectionPool(host='ztblab-api.goairgap.com', port=443): Max retries exceeded with url: /api/v2/auth/login (Caused by NameResolutionError("<urllib3.connection.HTTPSConnection object at 0x7fe54aa33f40>: Failed to resolve 'ztblab-api.goairgap.com' ([Errno -2] Name or service not known)"))
During handling of the above exception, another exception occurred:
Traceback (most recent call last):
 File "/usr/local/lib/
python3
.8/dist-packages/mgmt_controller/mgmt_controller.py", line 518, in get_authorization_token
   auth_response = reqs.post(
 File "/usr/local/lib/
python3
.8/dist-packages/requests/api.py", line 115, in post
   return request("post", url, data=data, json=json, **kwargs)
 File "/usr/local/lib/
python3
.8/dist-packages/requests/api.py", line 59, in request
   return session.request(method=method, url=url, **kwargs)
 File "/usr/local/lib/
python3
.8/dist-packages/requests/sessions.py", line 589, in request
   resp = self.send(prep, **send_kwargs)
 File "/usr/local/lib/
python3
.8/dist-packages/requests/sessions.py", line 703, in send
   r = adapter.send(request, **kwargs)
 File "/usr/local/lib/
python3
.8/dist-packages/requests/adapters.py", line 700, in send
   raise ConnectionError(e, request=request)
requests.exceptions.ConnectionError: HTTPSConnectionPool(host='ztblab-api.goairgap.com', port=443): Max retries exceeded with url: /api/v2/auth/login (Caused by NameResolutionError("<urllib3.connection.HTTPSConnection object at 0x7fe54aa33f40>: Failed to resolve 'ztblab-api.goairgap.com' ([Errno -2] Name or service not known)"))
Checking Management Portal reachability...FAILED ? HTTP https://ztblab-api.goairgap.com
sh: 1: Syntax error: "(" unexpected
Checking Analytics connection...ERROR
FAILED status False
Checking Debug Port reachability...OK
Services Status:
Service                       State                                             
Network Poller                active (running) since Thu 2026-01-15 02:13:01 UTC; 3 weeks 4 days ago
DHCP Server                   active (running) since Tue 2026-02-03 02:25:36 UTC; 6 days ago
Network Status:
NAMES                IMAGE                                              STATUS
dnsproxy_container   docker.repos.goairgap.com/7-8/dnsproxy:8.0.8P1     Up 32 minutes
vyos_container       docker.repos.goairgap.com/7-8/vyos:1.4.4-sagitta   Up 38 minutes
policy_container     docker.repos.goairgap.com/7-8/poller:8.0.8P1       Up 38 minutes

Press [Enter] to continue
```
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/step-step-configuration-guide-zero-trust-branch","lastmod":"2026-08-03T14:36Z","nid":"1532103"} -->
## Step-by-Step Configuration Guide for Zero Trust Branch

- Source: https://help.zscaler.com/zero-trust-branch/step-step-configuration-guide-zero-trust-branch
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Step-by-Step Configuration Guide for Zero Trust Branch
- Last modified: 2026-08-03T14:36Z
- Summary: Step-by-Step Configuration Guide for Zero Trust Branch

This guide takes you through the configuration steps you need to complete before using Zscaler Zero Trust Branch for your organization.

Before you begin configuring Zero Trust Branch, Zscaler recommends reading the following articles:

- [What Is Zero Trust Branch?](https://help.zscaler.com/zero-trust-branch/what-zero-trust-branch)
- [Hardware Usage Terms](https://help.zscaler.com/product-usage-terms/hardware-usage-terms-policy)

## Configuring Zero Trust Branch

To configure a Zero Trust Branch appliance, complete the following steps:

- Step 1: Update Administrator and Role Management Information
- Step 2: Deploy the Appliance
- Step 3: Configure Policies
- Step 4: Configure Network Services
- Step 5: Configure High Availability and Resilience
- Step 6: Set Up Identity-Aware Connectivity
- Step 7: Configure Network Segmentation and Isolation
- Step 8: Perform Network Monitoring and Analytics
- Step 9: Handle Appliance Maintenance and Lifecycle Management

Zero Trust Branch uses Authentication Service as its sole identity and authentication management service, and Zero Trust Branch has predefined roles.

To learn more, see:

- [Assigning Entitlements to Users and User Groups](https://help.zscaler.com/zidentity/assigning-entitlements-users-and-user-groups)

Deployment steps include creating a site, testing connectivity, activating the site, and verifying traffic flow.

To learn more, see [Deploying a Zero Trust Branch Appliance](https://help.zscaler.com/zero-trust-branch/deploying-zero-trust-branch-appliance).

Now that the appliance is up and running, configure policies to secure traffic and control its flow.

- DNS Policies
- Firewall Policies
- Routing Policies

You can use DNS policies to define rules that control DNS requests and responses to your Zero Trust Branch sites.

To learn more, see:

- [What Are Site DNS Policies?](https://help.zscaler.com/zero-trust-branch/what-site-dns-policies)
- [Configuring Site DNS Policies](https://help.zscaler.com/zero-trust-branch/configuring-site-dns-policies)

Zero Trust Branch allows you to create network-isolation and endpoint-isolation policies to mitigate lateral threat movements.

To learn more, see:

- [Understanding Firewall Policies](https://help.zscaler.com/zero-trust-branch/understanding-firewall-policies)
- [Configuring Firewall Policies](https://help.zscaler.com/zero-trust-branch/configuring-firewall-policies)

You can create routing policies to control traffic from branch sites to specific destinations based on defined criteria, ensuring secure and optimized routing.

To learn more, see:

- [Understanding Routing Policies](https://help.zscaler.com/zero-trust-branch/understanding-routing-policies)
- [Configuring Routing Policies](https://help.zscaler.com/zero-trust-branch/configuring-routing-policies)

Network services manage IP addressing and access to the internet.

- Dynamic Host Configuration Protocol (DHCP)
- Domain Name System (DNS)

Zero Trust Branch appliances rely on a DHCP server to assign IP addresses and other network settings. Your site acts as either a DHCP server or as a DHCP relay, depending on the template used to create the site. When configured as a DHCP relay, Zero Trust Branch receives client requests and forwards them to DHCP servers across secure WAN links.

Zero Trust Branch appliances allow admins to define and enforce DNS policies that regulate the handling of DNS queries originating from branch sites. These policies enable precise control over DNS requests and responses, ensuring adherence to security and compliance requirements.

To learn more, see [Configuring Site DNS Policies](https://help.zscaler.com/zero-trust-branch/configuring-site-dns-policies).

Use the following features to ensure that your Zero Trust Branch appliances are continuously accessible and reliable, and operate at the highest level possible.

- Zero Trust Branch High Availability Clusters
- Virtual Router Redundancy Protocol (VRRP)
- Routed Tunnels

A Zero Trust Branch high availability (HA) cluster consists of two identical Zero Trust Branch appliances (or *nodes*). One node is the primary node and has the active role. The other node is the secondary node and has the standby role. If the primary node fails, the secondary node takes over as the primary, active node.

To learn more, see [Creating a Zero Trust Branch High Availability Cluster](https://help.zscaler.com/zero-trust-branch/creating-zero-trust-branch-high-availability-cluster).

VRRP allows multiple Zero Trust Branch appliances to share a virtual IP address, minimizing downtime and enhancing overall reliability and security.

To learn more, see [Configuring a High Availability Site with Virtual Router Redundancy Protocol](https://help.zscaler.com/zero-trust-branch/configuring-ha-site-vrrp).

Zero Trust Branch allows secure remote site connectivity over routed tunnels via border gateway protocol (BGP).

To learn more, see [Configuring Zero Trust Branch Site-to-Site Connectivity Over Routed Tunnels](https://help.zscaler.com/zero-trust-branch/configuring-zero-trust-branch-over-routed-tunnels).

Private Access (ZPA) leverages the App Connector to securely connect users and applications in branch offices to private applications hosted in other branch environments.

To learn more, see [Understanding Private Application Connectivity for Branches](https://help.zscaler.com/zero-trust-branch/understanding-private-app-connectivity-branches).

Zero Trust Branch supports features for network segmentation, control, and security.

- Airgap Solutions: Airgap-Lite and Airgap+
- NAC-MAC Authentication
- Ransomware Kill Switch

Zero Trust Branch offers several solutions tailored to meet the unique security and isolation requirements of highly sensitive or regulated organizations. These solutions, which you select when you add or edit an asset, address varying levels of network isolation and functionality needs.

To learn more, see:

- [Understanding Protection Solutions](https://help.zscaler.com/zero-trust-branch/understanding-protection-solutions)
- [Configuring Airgap-Lite Mode for Assets](https://help.zscaler.com/zero-trust-branch/configuring-protection-mode-assets)
- [Managing Your Assets](https://help.zscaler.com/zero-trust-branch/managing-your-assets)

NAC-MAC authentication is a network access control (NAC) method that allows access based on MAC address lists.

To learn more, see [Uploading a NAC-MAC Authentication List](https://help.zscaler.com/zero-trust-branch/uploading-nac-mac-authentication-list).

The Zscaler Ransomware Kill Switch is a one-click attack surface-reduction tool integrated into Zero Trust Branch. It empowers you to rapidly contain ransomware threats without disrupting critical business operations.

To learn more, see:

- [Understanding the Ransomware Kill Switch](https://help.zscaler.com/zero-trust-branch/understanding-ransomware-kill-switch)
- [Configuring the Ransomware Kill Switch for a Site](https://help.zscaler.com/zero-trust-branch/configuring-ransomware-kill-switch-site)

Zero Trust Branch supports a variety of ways to view and analyze traffic, review interface health, and monitor and manage your network.

- Traffic Analytics
- Interface Health Monitoring
- Integration with SIEM and Other Monitoring Tools
- SNMP Configuration (v2/v3)

Zero Trust Branch provides extensive visibility into lateral traffic, which allows you to monitor and troubleshoot your packet logs and flow logs.

To learn more, see:

- [Viewing Traffic Flow Charts](https://help.zscaler.com/zero-trust-branch/viewing-traffic-flow-charts)
- [Understanding Logging and Monitoring](https://help.zscaler.com/zero-trust-branch/understanding-logging-and-monitoring)

Zero Trust Branch offers support for multiple WAN links and can automatically switch traffic upon detecting failures. This is achieved by tracking key metrics on the WAN interface, such as loss, latency, and jitter, and using "best link" traffic distribution within the routing policy rule.

To learn more, see [Interface Monitoring](https://help.zscaler.com/zero-trust-branch/interface-monitoring).

Zero Trust Branch integrates with third-party and Zscaler services to extend visibility, automate response workflows, and enrich analytics across your enterprise systems. The supported integrations include tools for monitoring, security orchestration, IT service management, and analytics.

To learn more, see:

- [About Integrations](https://help.zscaler.com/zero-trust-branch/about-integrations)
- [Configuring SIEM Integration](https://help.zscaler.com/zero-trust-branch/configuring-siem-integration)

Zero Trust Branch supports the Simple Network Management Protocol (SNMP) standard for network monitoring and management. Both SNMPv2 and SNMPv3 are supported.

To learn more, see [Managing SNMP Configurations](https://help.zscaler.com/zero-trust-branch/managing-snmp-configurations).

Zero Trust Branch upgrades provide new features, bug fixes, and performance and security improvements. Zscaler Support works with you to troubleshoot issues you might encounter with your Zero Trust Branch appliances.

- Software Upgrades
- Return Merchandise Authorization (RMA)

A rich set of upgrade options are available from the **Sites**page in the Zscaler Admin Console. An **Upgrade available** button indicates that a site is eligible for upgrade.

Zscaler Support works with you to troubleshoot and diagnose Zero Trust Branch appliance issues remotely. If the issue cannot be resolved remotely or is determined to be a hardware fault, Zscaler offers comprehensive advanced replacement services.

To learn more, see [Return Merchandise Authorization (RMA) Process and Policy](https://help.zscaler.com/product-usage-terms/hardware-rma).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/testing-routing-policies","lastmod":"2026-07-22T09:08Z","nid":"1532643"} -->
## Testing Routing Policies

- Source: https://help.zscaler.com/zero-trust-branch/testing-routing-policies
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Policy Management > Routing Policies > Testing Routing Policies
- Last modified: 2026-07-22T09:08Z
- Summary: How to test routing policies configured in the Zero Trust Branch Admin Portal.

You can test [routing policies](https://help.zscaler.com/tech-pubs-drafts/understanding-routing-policies) to verify that network traffic is correctly routed through the intended interfaces in your Zero Trust Branch configuration. This helps ensure that the routing logic you defined is functioning as expected.

To test a routing policy:

1. Log in to the branch appliance over SSH using the corresponding management IP address and credentials.
2. Choose a source device (e.g., a virtual machine) to which the policy applies and ping an IP address (e.g., 8.8.8.8).
3. While the ping test is in progress, run the following command to capture packets on both primary and secondary interfaces: `sudo tcpdump -i <interface_link_name> icmp`
4. Verify the output, which should reflect the [policy configuration](https://help.zscaler.com/tech-pubs-drafts/configuring-routing-policies).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/understanding-alarms-and-notifications","lastmod":"2026-04-08T22:47Z","nid":"1533791"} -->
## Understanding Alarms and Notifications

- Source: https://help.zscaler.com/zero-trust-branch/understanding-alarms-and-notifications
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Analytics & Monitoring > Understanding Alarms and Notifications
- Last modified: 2026-04-08T22:47Z
- Summary: Information about alarms and notifications in Zero Trust Branch.

The Zero Trust Branch management controller operates as a distributed, declarative, event-driven platform. Zero Trust Branch appliances detect critical events, such as heartbeat failures or upgrade failures, and send `set` or `clear` alarm requests to the API server. The API server acts as the central point for processing alarm signals. You can interact with the API server through the Zscaler Admin Console to review current alarms and acknowledge or clear them as needed. Alarm-related information is stored in a dedicated database table for tracking and auditing.

## How Alarms Are Processed

Zero Trust Branch follows a clear, structured sequence for alarm handling:

1. **Event detection**: The Zero Trust Branch appliance detects critical conditions (e.g., heartbeat failure, upgrade failure).
2. **Alarm submission**: The Zero Trust Branch appliance sends a set/clear alarm request to the management system’s API server.
3. **Alarm processing**: The API server receives the request and forwards the alarm to the Alarm/Notification Interface.
4. **Storage and tracking**: All alarms and notifications are stored in a dedicated alarms table for the customer environment.
5. **Operator interaction**: Admins review, acknowledge, or clear alarms through the Zscaler Admin Console.

[Image: Alarm processing in Zero Trust Branch]

## Notification Flow to Zero Trust Branch Appliances

When changes are made in the management system, it issues state change notifications to Zero Trust Branch appliances using the MQTT protocol. Appliances work to achieve the declared state based on an eventual consistency model. If an appliance cannot converge to the required state, it sends asynchronous alarm notifications back to the management system. This ensures configuration changes are propagated reliably and that failures to converge are reported back promptly.

## Alarm Storage and Isolation

All alarms and notifications are stored in a dedicated alarms table within the customer’s database.

This ensures:

- Persistent storage of historical alarm data
- Complete per-customer isolation
- Secure tracking and auditing of events

No alarm information is shared between tenants.

## Viewing Alarms and Notifications

You can view alarms and notifications from the Alarms page (Administration > Alerts > Alarms).

See image.

You can also create notifications using the Ransomware Kill Switch. To learn more, see [Configuring the Ransomware Kill Switch for a Site](https://help.zscaler.com/zero-trust-branch/configuring-ransomware-kill-switch-site).

[Image: Viewing alarms for Zero Trust Branch]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/understanding-bonding-interfaces","lastmod":"2026-07-21T14:15Z","nid":"1538749"} -->
## Understanding Bonding Interfaces

- Source: https://help.zscaler.com/zero-trust-branch/understanding-bonding-interfaces
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Deploying Zero Trust Branch > Bonding Interfaces > Understanding Bonding Interfaces
- Last modified: 2026-07-21T14:15Z
- Summary: Information on bonding interfaces on the LAN side in [variable:zero-trust-branch]].

Zero Trust Branch bonding interfaces empower organizations to seamlessly combine high availability, increased bandwidth, and streamlined network management in distributed branch, factory, and data center environments. The two bonding interfaces, ebond0 and ebond1, are logical interfaces created by combining multiple physical network interfaces (ports). By leveraging bonding interfaces, you can ensure that the traffic is load balanced and protected against link failures, while administrative overhead is greatly reduced through centralized, template-driven configuration.

The following are key benefits of using bonding interfaces:

- **Increased Capacity**: Provides higher aggregate bandwidth than any single physical link.
- **High Availability**: Offers redundancy, ensuring network availability even during component failures.
- **Enhanced Performance**: Load balancing using 5-tuple hashing improves network throughput and efficiency.
- **Simplified Configuration**: Network admins can configure settings once on the logical port channel interface, and those settings are then automatically applied to the individual physical member interfaces.

### **Limitations**

The following are some of the limitations with bonding interfaces:

- Members must be of the same interface type (LAN/WAN).
- Members must have the same port speed.
- Multiple tags (Q-in-Q) are not supported.
- Bonding interface on primary WAN is not supported.
- Bonding interface members can’t be shared across multiple switches (no multi-chassis link aggregation (MLAG)).

## Topology

The following diagram illustrates the topology for Zero Trust Branch bonding interfaces:

[Image: Topology of bonding interfaces]

- Proxmox simulates endpoints in VLAN 10 and VLAN 20 and connects to a Cisco switch via a LAN link.
- ZT800 connects to:
  - The WAN router (for internet access) on GE8
  - The Cisco switch on ports GE5 and GE6 forming port channel
- The Cisco switch links everything together.
- Bonding (LACP) on ZT800 aggregates ports fa0/1 and fa0/4.
- VLAN traffic flows from Proxmox > Cisco switch > ZT800 > WAN.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/understanding-firewall-policies","lastmod":"2026-07-22T09:11Z","nid":"1532548"} -->
## Understanding Firewall Policies

- Source: https://help.zscaler.com/zero-trust-branch/understanding-firewall-policies
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Policy Management > Firewall Policies > Understanding Firewall Policies
- Last modified: 2026-07-22T09:11Z
- Summary: Information about the firewall policies in Zero Trust Branch.

Zero Trust Branch allows you to create firewall policies to isolate and control communication between various network environments. Built on Zscaler's Zero Trust framework, these policies ensure that no connection is trusted by default, effectively preventing lateral movement and minimizing attack surfaces.

Zero Trust Branch provides a comprehensive set of capabilities to secure and manage network communications:

- **Granular Isolation**: Segment and control traffic between zones, sites, or network segments.
- **Zero Trust Enforcement**: Authenticate and authorize every connection before granting access.
- **Threat Containment**: Restrict lateral movement, minimizing the blast radius in the event of a breach.
- **Object-Based Policy Building**: Define source and destination criteria using reusable network, service, and group objects.

You can create firewall policies at different levels:

- **Global**: Policies that apply to all sites.
- **Template**: Policies that apply to all sites that share a specific template.
- **Site**: Policies that apply only to an individual site. When a new site is created, Zero Trust Branch automatically generatessystem (default) policiesfor the site. These policies are editable but cannot be reordered.

## Policy Evaluation

Firewall policies are evaluated in the following sequence:

1. **Site policies**: Checked first. If a match is found, the policy is applied and evaluation stops.
2. **Template policies**: Evaluated if no site-level policy matches. If a match is found, the policy is applied and evaluation stops.
3. **Global policies**: Evaluated if no template-level policy matches. If a match is found, the policy is applied and evaluation stops.
4. **System (default) policies**: Applied only if no match is found at the site, template, and global levels.

With Zero Trust Branch firewall policies, security teams can standardize enforcement, maintain flexibility for site-specific needs, and align with zero trust principles—all while simplifying branch network security management.

To learn more, see [Configuring Firewall Policies](https://help.zscaler.com/zero-trust-branch/configuring-firewall-policies) and [Managing Firewall Policies](https://help.zscaler.com/zero-trust-branch/managing-firewall-policies).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/understanding-log-types-levels-and-formats","lastmod":"2026-04-08T23:29Z","nid":"1533790"} -->
## Understanding Log Types, Levels, and Formats

- Source: https://help.zscaler.com/zero-trust-branch/understanding-log-types-levels-and-formats
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Analytics & Monitoring > Understanding Log Types, Levels, and Formats
- Last modified: 2026-04-08T23:29Z
- Summary: Information about log types, levels, and formats in Zero Trust Branch.

Monitoring and logging are crucial to securing any system, as they help detect anomalies, ensure compliance, and troubleshoot issues. Zero Trust Branch provides comprehensive logging and monitoring capabilities to ensure visibility and control across your branch network infrastructure.

Zero Trust Branch appliances generate several types of logs that help admins analyze branch traffic, understand policy outcomes, and troubleshoot operational issues. This article explains the log types, logging levels, log formats, retention behavior, throttling characteristics, and how customer logs are isolated within the system.

- Log Types
- Logging Levels
- Log Format
- Log Retention and Size Limits
- Log Throttling
- Segregated Customer Logs

## Log Types

Zero Trust Branch appliances capture two primary types of traffic logs: **packet logs** and **flow logs**. These logs provide different levels of detail and are used for complementary troubleshooting needs.

By default, Zscaler only logs the traffic flow once per hour based on the 4-tuple (source IP address, destination IP address, destination port, protocol) for each policy match. For example, an admin creates a policy denying access sourced from any IP address and destined for 8.8.8.8 on port 53. If Host A sends multiple DNS requests to 8.8.8.8, then only one request is logged within an hour. If Host B does the same thing, then only a single entry appears for Host B. If Host A or B repeats this process after an hour, a new entry appears in the log.

To enable more verbose logging, select **Disable Log Throttling** when you add or edit a policy. To learn more, see [Configuring Firewall Policies](https://help.zscaler.com/zero-trust-branch/configuring-firewall-policies).

### Packet Logs

Packet logs provide a rich set of data that allows you to analyze traffic flowing through a network, providing insight into traffic patterns and helping you to troubleshoot issues. Packet logs help track packet flow, verify firewall rules, and ensure trusted communication paths. For example, when troubleshooting DNS resolution or verifying a rule, these logs give all relevant context including interface, protocol, and rule hits. Packet logs can be filtered by a number of attributes, including source (e.g., IP address, port, OS, browser, or geolocation), destination (e.g., name, location, country, IP address, or port), and host (name and OS). Zero Trust Branch Management sends metadata to the Zero Trust Branch appliances.

Packet logs display all captured packet headers that hit any policy in a per-packet manner.

To access packet logs, go to Logs > Insights > Packet Logs.
See image.

### Flow Logs

Flow logs help you to troubleshoot issues using session flow details for a source/destination pair and a policy/action set.

You can filter them by a number of attributes, including source (e.g., IP address, port, OS, browser, or geolocation), destination (e.g., name, location, country, IP address, or port), and more.

To access flow logs, go to Logs > Insights > Flow Logs.
See image.

Flow logs are near real time. Traffic charts need to collect and compile the data before it is displayed in the graphs. As a result, expect a 5- to 7-minute delay in traffic charts.
Flow logs display selected fields in a table format. You can see the policy name and the action (drop, allow) clearly.

## Logging Levels

Zero Trust Branch supports multiple logging levels to give admins flexibility based on operational needs and troubleshooting depth:

- **Info**: Captures standard operational events and routine activities. This is the default level.
- **Warning**: Records potential issues that do not interrupt operations but might require attention.
- **Error:** Captures critical incidents or failures that need immediate investigation, such as misconfigurations or connectivity failures.
- **Debug**: Provides detailed, granular information for diagnostic purposes. This level produces significantly more logs and is typically enabled only during targeted troubleshooting.

Admins can adjust logging levels to match their compliance, performance, or diagnostic needs.

## Log Format

### Stored Logs Format

Logs are stored in a structured JSON format to support consistent parsing, analysis, and integration with third-party security information and event management (SIEM) platforms.

Each entry includes key fields such as:

- **Action**
- **Agent information**: Name, ID, type, version
- **Destination**: IP, port, MAC
- **Event**: Action, dataset, module, time zone
- **IPTables data**: Input/output device, length, type-of-service (TOS), time-to-live (TTL), precedence bits
- **Log metadata**: File path, offset, original log reference
- **Network type**
- **Policy**
- **Protocol**
- **Source**: IP address, port, MAC
- **Tags**
- **Timestamp**
- **Tunnel information**

Logs can be exported in JSON or CSV formats for external analysis or long-term storage.

### Traffic Log Format

The traffic log is a consolidated record of network activity showing various parameters. The following is an example of a typical traffic log:

```
Nov 18 13:59:49 mcdl--POD130--AGN01 [HTTP-ALLOW-10-A] IN=eth0 OUT=eth1 MAC=00:0c:29:bd:a5:e7:00:0c:29:00:ff:9d:08:00 SRC=10.90.130.22 DST=34.107.243.93 LEN=60 TOS=00 PREC=0x00 TTL=63 ID=44295 DF PROTO=TCP SPT=33714 DPT=443 SEQ=651940018 ACK=0 WINDOW=64240 SYN URGP=0 MARK=802
```

The following table provides information about fields in the traffic log:

| Field | Description |
| --- | --- |
| `Nov 18 13:59:49` | Timestamp of the log |
| `mcdl--POD130--AGN01` | Combined value indicating tenant name, site name, and gateway name, separated by double hyphens (`--`) |
| `[HTTP-ALLOW-10-A]` | Combined value indicating policy name, policy ID, and policy action, separated by a hyphen (`-`) |
| `IN=eth0` | Input interface |
| `OUT=eth1` | Output interface |
| `MAC=00:0c:29:bd:a5:e7:00:0c:29:00:ff:9d:08:00` | Combined value indicating destination MAC address and source MAC address, separated by a colon (`:`) |
| `SRC=10.90.130.22` | Source IP address |
| `DST=34.107.243.93` | Destination IP address |
| `LEN=60 TOS=00 PREC=0x00 TTL=63 ID=44295 DF` | IP header options |
| `PROTO=TCP` | Transport protocol |
| `SPT=33714` | Source port |
| `DPT=443` | Destination port |
| `SEQ=651940018 ACK=0 WINDOW=64240 SYN URGP=0 MARK=802` | TCP header options |

## Log Retention and Size Limits

Zero Trust Branch applies retention and sizing limits to ensure efficient storage and predictable performance. Session logs (including flow records) are retained for 7 days by default. Customers requiring longer retention can do one of the following:

- Export logs to external SIEM platforms.
- Request extended retention through their master service agreement (MSA).

### Additional Retention Behavior

Zero Trust Branch applies the following additional behavior for log retention:

- The Zscaler Admin Console displays up to 500 log entries at a time.
- Exported log files can be up to 10 MB in size.
- Private Access (ZPA)-related logs do not appear in the Zscaler Admin Console. They are available only in [Private Access diagnostics](https://help.zscaler.com/zpa/viewing-and-managing-events-diagnostics).

## Log Throttling

To maintain performance and prevent excessive log volume, Zero Trust Branch employs log throttling mechanisms. By default, throttling is enabled in Firewall and Segmentation policies for east–west traffic.

### Throttling Behavior

Only one flow per hour is logged for each unique combination of:

- Source IP address
- Destination IP address
- Destination port
- Protocol

If throttling is disabled, Zero Trust Branch logs every matching flow.

## Segregated Customer Logs

Zero Trust Branch ensures complete log isolation for each customer tenant, ensuring strict multi-tenant isolation, compliance, and security. The isolation mechanisms include:

- Dedicated ingestion records (tables) for each customer’s graph-generation data
- Separate Analytics Data Store spaces for packet logs and flow logs
- Individual cloud storage buckets for syslogs
- Dedicated tables for each customer’s alarms and notifications

[Image: Typical Packet Logs chart]

[Image: Typical Flow Logs chart.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/understanding-logging-and-monitoring","lastmod":"2026-01-13T06:06Z","nid":"1533597"} -->
## Understanding Logging and Monitoring

- Source: https://help.zscaler.com/zero-trust-branch/understanding-logging-and-monitoring
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Analytics & Monitoring > Understanding Logging and Monitoring
- Last modified: 2026-01-13T06:06Z
- Summary: Information on logging and monitoring capabilities in Zero Trust Branch.

Logging and monitoring are essential to maintaining secure, reliable, and well-managed branch networks. These activities provide the visibility required to detect anomalies, ensure compliance, and troubleshoot issues before they cause disruption. Zscaler's Zero Trust Branch provides comprehensive logging and monitoring capabilities to help admins visualize traffic, analyze branch activity, and maintain operational control. Built on scalable, cloud-native Analytics Data Lake, the Zero Trust Branch logging framework supports real-time log ingestion, high-throughput data streaming, and interactive analytics dashboards.

This article explains how Zero Trust Branch collects, stores, and presents logging data. It also describes the log formats, retention policies, throttling mechanisms, and integration options that enhance visibility across your Zero Trust Branch environment.

The Zero Trust Branch logging and monitoring system helps you:

- Gain real-time visibility into branch-level traffic, device activity, and policy enforcement.
- Troubleshoot faster using packet and flow logs streamed to the Analytics Data Store for rapid search and visualization.
- Protect performance through built-in throttling, structured JSON log formats, and tiered retention options.
- Stay audit-ready with isolated per-tenant data stores and integrations with third-party security information and event management (SIEM) platforms.

## Architecture Overview

The Zero Trust Branch appliance logging and monitoring frameworks are designed to handle large volumes of branch network telemetry with cloud-scale performance and reliability. It is built on scalable, cloud-native Analytics Data Lake that collects, processes, analyzes, and stores network telemetry from distributed branch environments. This design ensures high-volume log ingestion, real-time analytics, multi-tenant isolation, and reliable long-term visibility across branch gateways.

The Analytics Data Lake comprises two core subsystems:

- **Ingestion and Analytics Engine**: The ingestion, processing, and analytics layer
- **Analytics Data Store**: The searchable, query-ready storage and visualization layer

Together, these components deliver real-time observability for east–west traffic, device activity, asset discovery, and security events.

[Image: Logging architecture in Zero Trust Branch]

### Ingestion and Analytics Engine

The Ingestion and Analytics Engine collects and processes all traffic logs and asset metadata from branch gateways. It normalizes incoming data, manages load and throttling, and generates the analytics used for traffic charts and device discovery.

The key functions of the Ingestion and Analytics Engine include:

- **Traffic logs and asset discovery**: Zero Trust Branch gateway clusters forward traffic logs and asset discovery metadata to the management plane, which streams this data into the Ingestion and Analytics Engine.
- **Data visualization**: The log analyzer microservice consumes incoming data and is responsible for device discovery and building detailed traffic charts, including east–west traffic visualizations.
- **Scalability**: Designed to handle very high throughput and process millions of log records per second, ensuring support for large enterprise deployments without bottlenecks.
- **Load balancing and throttling**: Applies automatic load balancing and can throttle or rate-limit ingestion to protect downstream services. Throttling and built-in retry logic help to manage bursts in log volume.
- **Per-environment deployment**: One engine instance is deployed per Zero Trust Branch management instance, isolating log flows for each environment.
- **Traffic charts**: Logs processed by the engine enable dynamic, interactive traffic charts and device maps. Each node in a chart can represent up to 200 neighboring connections.

### Analytics Data Store

The Analytics Data Store retains structured packet and flow logs in dedicated tenant spaces. It supports real-time search, visualization, and short-term analytics for troubleshooting and operational monitoring.

The key functions of the Analytics Data Store include:

- **Real-time debugging**: A near real-time copy of logs processed by the engine is written to the Analytics Data Store, enabling immediate access to raw traffic logs for troubleshooting.
- **Multi-tenancy**: Each management instance connects to a shared Analytics Data Store cluster. When a new customer tenant is onboarded, a dedicated space is created to ensure data isolation and secure access.
- **Retention**: Logs are retained for up to 7 days, supporting short-term analytics and rapid incident response.
- **Access**: Admins can query, filter, and visualize their traffic data using dashboards backed by the Analytics Data Store with no additional agents.
- **Log types**: The Analytics Data Store retains both packet logs and flow logs, providing full context for traffic analysis and troubleshooting.

To learn more, see [Understanding Log Types, Levels, and Formats](https://help.zscaler.com/zero-trust-branch/understanding-log-types-levels-and-formats).

## Monitoring Capabilities

Zero Trust Branch provides built-in monitoring tools that give admins real-time visibility into branch activity and system behavior. These capabilities help admins track traffic trends, device status, and security events, and quickly identify issues that require attention. Zero Trust Branch supports the following monitoring capabilities:

- **Real-time dashboards**: Live dashboards display traffic insights, device health, and security events, allowing admins to monitor branch activity as it happens. To learn more, see [Viewing Traffic Flow Charts](https://help.zscaler.com/zero-trust-branch/viewing-traffic-flow-charts).
- **Alerts and notifications**: The system generates alerts for anomalies, failures, or policy violations, helping operators detect and respond to issues quickly. To learn more, see [Understanding Alarms and Notifications](https://help.zscaler.com/zero-trust-branch/understanding-alarms-and-notifications).
- **Third-party integrations**: Logs can be exported to external SIEM platforms, such as Splunk or QRadar, via the API for broader visibility, longer retention, and centralized analysis. To learn more, see [Configuring SIEM Integration](https://help.zscaler.com/zero-trust-branch/configuring-siem-integration).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/understanding-micro-subnets","lastmod":"2026-07-14T11:22Z","nid":"1538698"} -->
## Understanding Micro-Subnets

- Source: https://help.zscaler.com/zero-trust-branch/understanding-micro-subnets
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust Device Segmentation > Micro-Subnets > Understanding Micro-Subnets
- Last modified: 2026-07-14T11:22Z
- Summary: Information about micro-subnets and their key capabilities in Zero Trust Branch.

Zero Trust Branch delivers true zero trust microsegmentation by assigning a /32 subnet mask to each endpoint, creating a "network of one" that isolates devices, blocks direct endpoint-to-endpoint communication, and reduces attack surfaces.

Some [Purdue Level 2](https://www.zscaler.com/resources/security-terms-glossary/what-is-purdue-model-ics-security) and below endpoints require direct L2 connectivity (e.g., PLC controller to PLC controller). Certain legacy endpoints do not support /32 subnet masks or struggle to operate within "network of one" subnets. For these cases, Zero Trust Branch identifies and whitelists such endpoints in a special group called Airgap+, and continuously monitors those endpoints. Strict policies govern their interaction with endpoints isolated in the "network of one."

Building on this, Airgap+ uses the Zero Trust Branch appliance to create micro-subnets that are highly granular subnetworks with small IP address ranges (e.g., /27 to /30). Devices within a micro-subnet communicate directly, while traffic crossing micro-subnet boundaries is inspected and controlled by policies enforced at the Zero Trust Branch appliance. This approach enhances security granularity, visibility, and control, enabling stronger east-west segmentation and minimizing lateral movement risks in complex environments.

## Key Features

The following are some of the key capabilities of micro-subnets:

- **Granular Micro-Subnets**: Supports subnet masks /27 through /30, ensuring granular logical segmentation. The micro-subnet has three reserved IP addresses. For example, a /29 subnet creates a segment of 8 IP addresses. Out of these 8 IP addresses, the first IP address is reserved for the network address, the second is reserved for the gateway, and the last is reserved for the broadcast address, leaving 5 IP addresses for endpoints. This level of segmentation intelligence is built into the Zero Trust Branch DHCP server. Airgap+ is supported only in DHCP server mode.
- **Direct Intra-Micro-Subnet Communication**: Devices within the same micro-subnet interact without routing overhead.
- **Forced Policy Enforcement across Boundaries**: Any micro-subnet-to-micro-subnet or external traffic is always routed through the appliance.
- **Zero Trust Branch DHCP Server**: Airgap+ uses the Zero Trust Branch DHCP server, as the micro-subnet allocation must be known to the server.

## Topology

The following diagram depicts the topology of a micro-subnet:

[Image: Micro-subnet connected to an appliance]

| Device | MAC Address | IP Address | IP Address Allocation |
| --- | --- | --- | --- |
| vlan10-dev1 | bc:24:11:a1:24:fd | 10.90.10.3/32 | Airgap |
| vlan10-dev2 | bc:24:11:21:fd:b4 | 10.90.10.5/32 | Airgap |
| vlan10-dev3 | bc:24:11:11:d2:53 | 10.90.10.10/29 | Airgap+ |
| vlan10-dev4 | bc:24:11:8b:d2:85 | 10.90.10.11/29 | Airgap+ |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/understanding-private-app-connectivity-branches","lastmod":"2026-07-13T15:45Z","nid":"1532805"} -->
## Understanding Private Application Connectivity for Branches

- Source: https://help.zscaler.com/zero-trust-branch/understanding-private-app-connectivity-branches
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust SD-WAN > Understanding Private Application Connectivity for Branches
- Last modified: 2026-07-13T15:45Z
- Summary: Information on private app connectivity for branches using Zero Trust Branch and Zscaler Private Access (ZPA).

You can integrate Zscaler's Zero Trust Branch and Zscaler Private Access (ZPA) to enable secure, identity-aware connectivity between branches and private applications without extending the corporate network or relying on traditional Multiprotocol Label Switching (MPLS) and VPN architectures. This integration leverages the ZPA App Connector to securely connect users and appliances in branch offices to private applications hosted in other branch environments. In traditional networks, branches are connected to private apps through the corporate data center using hub-and-spoke routing. This approach introduces latency, cost, and complexity, and it exposes the internal network to potential lateral movement.

When Zero Trust Branch integrates with ZPA App Connector-based connectivity, it offers:

- A secure connection to private applications in branch sites through the App Connector, an outbound-only component deployed as part of ZPA.
- A mechanism to route branch traffic securely through the Zero Trust Exchange (ZTE), where access policies are enforced.
- Connections that are user based, application specific, and dynamically brokered, eliminating the need to extend the internal network between branches.

## Key Advantages

Integrated App Connector–based connectivity allows Zero Trust Branch and ZPA to work as a unified system for secure, private application access. It replaces traditional network-centric approaches with an application-centric, policy-driven zero trust model that gives organizations a more efficient, scalable, and secure way to connect branches to private apps. The following are some of the key advantages of integrated App Connector–based connectivity:

- **No Network Extension**: Branches connect through the ZTE without extending corporate IP spaces.
- **Identity-Driven Access**: Access decisions are based on user identity, device posture, and application context.
- **Simplified Architecture**: Eliminates complex routing, VPNs, and MPLS dependencies.
- **Enhanced Security**: Reduces attack surface by replacing network segmentation with application segmentation.
- **Seamless User Experience**: Enables direct, fast, and secure access to private applications between branch environments.

This model ensures that connectivity between branches and private applications remains secure, segmented, and invisible to the internet.

## Private Application Connectivity Workflow

The following workflow explains how Zero Trust Branch uses an integrated ZPA App Connector to enable secure, policy-based access to private applications hosted across branch sites. Each connection is established through the ZTE, ensuring identity verification and device posture checks before granting access.

1. A user or appliance in a branch attempts to access a private application in another branch.
2. Zero Trust Branch securely forwards the traffic to the ZTE.
3. The ZTE evaluates the traffic based on identity, device posture, and policy context as configured in ZPA.
4. A temporary, policy-based (ZPA) connection is created between the user and the application through the App Connector.

## Configuring Private Application Connectivity for Branches

Complete the following steps to configure private application connectivity for branches:

1. [Add an App Connector in ZPA for each branch site that hosts private applications](https://help.zscaler.com/zero-trust-branch/adding-app-connector-site).
2. [Deploy the App Connectors to the respective sites in the Zero Trust Branch Admin Portal](https://help.zscaler.com/zpa/configuring-connectors).
3. Define [application segments](https://help.zscaler.com/zpa/configuring-defined-application-segments) and [access policies](https://help.zscaler.com/zpa/about-access-policy) as required for the private applications in ZPA.

Depending on your requirements, the private applications in branch environments can use App Connector-based DNS resolutions or a private DNS server within the branch site behind its App Connector.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/understanding-protection-solutions","lastmod":"2026-07-13T16:40Z","nid":"1509976"} -->
## Understanding Protection Solutions

- Source: https://help.zscaler.com/zero-trust-branch/understanding-protection-solutions
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust Device Segmentation > Asset Management > Understanding Protection Solutions
- Last modified: 2026-07-13T16:40Z
- Summary: Information about Zscaler Zero Trust Branch solutions.

Zero Trust Branch offers several solutions tailored to meet the unique security and isolation requirements of highly sensitive or regulated organizations. The three solutions, Airgap, Airgap-Lite, and Airgap+, address varying levels of network isolation and functionality needs.

When you add or edit an asset, you can choose one of these solutions in the Protection drop-down menu. To learn more, see[Managing Your Assets](https://help.zscaler.com/zero-trust-branch/managing-your-assets).

- **Airgap**
  - Behavior: Assigns a /32 network mask to endpoints, giving each device its own unique IP address and enabling admins to enforce device-level isolation. Admins can use firewall policies to explicitly prevent direct communication between hosts and effectively create a secure "network of one."
  - Use Case: Ideal for environments that require complete isolation between endpoints, ensuring all traffic routes through the Zero Trust Branch appliance for inspection and enforcement.
  - Challenge: Some systems might not support /32 masks due to hardware or software limitations. In such environments where the /32 subnet mask is not supported, the admins can choose Airgap+ or Airgap-Lite (if full isolation is not a strict requirement) mode.
- **Airgap-Lite** To learn more, see [Configuring Airgap-Lite Mode for Assets](https://help.zscaler.com/zero-trust-branch/configuring-protection-mode-assets).
  - Behavior: Uses the same subnet mask as the one the DHCP server provides, which simplifies integration in environments where endpoints need to communicate directly without routing all traffic through the gateway.
  - Use Case: Suitable for systems or networks where full isolation is not a strict requirement.
  - Trade-Off: Reduces isolation as compared to Airgap.
- **Airgap+**
  - Behavior: Implements micro-subnets by supporting subnet masks between /27 and /30. Devices within the same micro-subnet can communicate directly without routing through the Zero Trust Branch appliance. Traffic between different micro-subnets or external destinations is routed through the gateway for inspection and enforcement.
  - Use Case: Provides a balance between complete isolation and full subnet communication, which is ideal for environments needing some level of local communication while still enforcing Zero Trust Branch policies for external traffic.
  - Trade-Off: Adds configuration and maintenance complexity as multiple subnets are managed.
  - Requirements:
    - The Zero Trust Branch appliance must operate in server mode for the associated VLAN(s).
    - Available starting with Airgap OS 7.7.6.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/understanding-ransomware-kill-switch","lastmod":"2026-07-15T16:04Z","nid":"1532701"} -->
## Understanding the Ransomware Kill Switch

- Source: https://help.zscaler.com/zero-trust-branch/understanding-ransomware-kill-switch
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust Device Segmentation > Ransomware Kill Switch > Understanding the Ransomware Kill Switch
- Last modified: 2026-07-15T16:04Z
- Summary: Information about the Zero Trust Branch Ransomware Kill Switch, used to quickly change the threat level for a site.

The Zscaler Ransomware Kill Switch is a one-click attack surface-reduction tool integrated into Zero Trust Branch. It empowers organizations to rapidly contain ransomware threats without disrupting critical business operations.

## Core Capabilities

With the Ransomware Kill Switch, you can:

- Contain threats with a single click using one of four preset severity tiers designed like [DEFCON levels](https://en.wikipedia.org/wiki/DEFCON). This allows progressive lockdowns, from blocking vulnerable protocols such as Remote Desktop Protocol (RDP) and Server Message Block (SMB), to isolating entire Operational Technology (OT) segments like hospital floors or factory lines.
- Enact a tiered, policy-based response to block high-risk access (e.g., RDP/Command and Control items), all nonessential communication, and access to critical crown jewel applications.
- Maintain business uptime by enacting targeted containment to keep essential operations running and avoid total shutdowns.
- Have integrated visibility and control to provide complete east-west network traffic visibility for all endpoints, enabling real-time, granular policy enforcement.
- Fully control your configuration via APIs to allow seamless integration with security information and event management (SIEM); security orchestration, automation, and response (SOAR); endpoint detection and response (EDR); and extended detection and response (XDR) systems to enable automated incident response.
- Use Message Queuing Telemetry Transport (MQTT) to publish policy updates, configuration changes, and Ransomware Kill Switch changes.

## Use Cases

Here are some typical use cases for the Ransomware Kill Switch:

- **Incident response**: The Ransomware Kill Switch provides incident response with user-selectable security levels to lock down vulnerable protocols, stop lateral movement, and restrict access to critical assets, ensuring business continuity during cyberthreats.
- **Enhanced visibility and control**: Constantly changing network devices make securing communications difficult. The Ransomware Kill Switch provides full visibility into all endpoint transactions, overcoming the limitations of monitoring lateral traffic across access switches. This enables real-time security policy enforcement and improved protection.
- **Critical infrastructure protection**: Integrates with SIEM, SOAR, and EDR/XDR via APIs, enabling automated incident response. It quarantines compromised endpoints, limiting infection spread, which enhances security while protecting existing IT investments.

## Example of the Ransomware Kill Switch in Action

When creating a site-level firewall policy for device segmentation, you must assign one or more Ransomware Kill Switch threat level color codes. To learn more about assigning a threat level, see [Configuring the Ransomware Kill Switch for a Site](https://help.zscaler.com/zero-trust-branch/configuring-ransomware-kill-switch-site). During an incident, only the firewall policies associated with the selected threat level color code are evaluated in a top-down sequence. The evaluation stops at the first matching policy, ensuring fast and precise containment.

In this example, Pod 80 has two hosts, `pod-80-lnx-a` and `pod-80-lnx-b`, connected to the LAN side of Zero Trust Branch, while the Zero Trust Branch WAN port is connected to the DHCP server. Each host is microsegmented to subnet `32`, as shown in the following image.

See image.

In this example, Pod 80 (designated as `site-80` in the Zscaler Admin Console) has the following policy rules:

- Rule 1, `block-ssh`, blocks Secure Shell (SSH) traffic when the threat level is orange.
- Rule 2, `all-protocol-allow`, allows all ports when the threat level is either green or yellow. See image.

Initially, the Ransomware Kill Switch is set to green.
See image.

With this setting, `pod-80-lnx-b` can ping and SSH into `pod-80-lnx-a` successfully.

```
admin80@pod-80-Inx-b:~$
ping 10.
xx
.
xx
.4
PING 10.98.80.4 (10.
xx.
xx.4) 56(84) bytes of data.
64 bytes from 10.
xx.xx
.4: icmp_seq=1 ttl=63 time=0.463 ms
64 bytes from 10.
xx.xx
.4: icmp_seq=2 ttl=63 time=0.373 ms
^C
--- 10.xx.xx.4 ping statistics •--
2 packets transmitted, 2 received, 0% packet loss, time 1036ms
rtt min/avg/max/mdev = 0.373/8.418/0.463/.045 ms

admin80@pod-80-lnx-b:~$
admin80@pod-80-2nx-b: ~$
ssh admin@10.
xx.xx
.4
The authenticity of host '10.
xx.xx
.4 (10.
xx.xx
.4)' can't be established.
ED25519 key fingerprint is SHA256:HsYaMx...vs0YtJLtyso.
This key is not known by any other names
```

Now, let’s change `site-80`'s threat level to orange to simulate a high-risk network scenario.
See image.

In this case, Rule 1, which blocks SSH traffic when the threat level is orange (and was previously skipped), is now enforced. In the following example, the ping is successful, but SSH access is blocked and does not return a response.

```
admin80@pod-80-Inx-b:~$ ping
10.xx.xx.4
PING 10.90.80.4 (10.xx.xx.4) 56(84) bytes of data.
64 bytes from 10.xx.xx.4: icmp_seq=1 ttl=63 time=0.486 ms
64 bytes from 10.xx.xx.4: icmp_seq=2 ttl=63 time=0.456 ms
^C
--- 10.xx.xx.4 ping statistics ---
2 packets transmitted, 2 received, 0% packet loss, time 1036ms
rtt min/avg/max/mdev = 0.456/0.471/0.486/0.015 ms
admin80@pod-80-1nx-b:~$
admin80@pod-80-1nx-b:~$
ssh admin@10.xx.xx.4
admin80@pod-80-1nx-b:~$
```

The Hit Count column on the Site Policies tab indicates the number of times the policy was accessed.
See image.

This example highlights how the Ransomware Kill Switch enables dynamic, color-coded policy enforcement for rapid incident response. By simply adjusting the threat level, you can instantly contain high-risk traffic, block malicious activity, and isolate compromised workloads.

[Image: Schematic diagram showing a Zero Trust Branch installation.]

[Image: Ransomware Kill Swtch]

[Image: Site Policies tab on the Policies page showing how policies with different threat levels can enhance site security.]

[Image: Ransomware Kill Switch set to orange]

[Image: Site Policies tab on the Policies page highlighting the Hit Count column.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/understanding-routing-policies","lastmod":"2026-07-22T09:06Z","nid":"1532641"} -->
## Understanding Routing Policies

- Source: https://help.zscaler.com/zero-trust-branch/understanding-routing-policies
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Policy Management > Routing Policies > Understanding Routing Policies
- Last modified: 2026-07-22T09:06Z
- Summary: Information about routing policies in Zero Trust Branch.

Zero Trust Branch allows you to create routing policies and control traffic from branch sites to specific destinations based on defined criteria, ensuring secure and optimized routing. These policies allow granular control over how traffic flows to Internet & SaaS (ZIA), Private Access (ZPA), or directly to the internet.

Zero Trust Branch provides a comprehensive set of capabilities to enable secure and efficient routing:

- **Policy-Based Routing**: Dynamically route traffic based on defined criteria such as source, destination, port, device, user, etc. to ensure optimal traffic flow.
- **Granular Segmentation**: Apply routing policies at the network, device, domain, application, or user level for precise control and visibility.
- **High Availability**: Use redundant gateways and interfaces to maintain continuous traffic flow and prevent service disruption.
- **Integrated Security**: Seamlessly integrate with Internet & SaaS and Private Access to enforce zero trust principles across all traffic paths.
- **Simplified Management**: Provide centralized configuration and monitoring via the Zscaler Admin Console.
- **Performance Optimization**: Direct critical traffic along optimal paths to minimize latency and enhance user experience.

When a new site is created, Zero Trust Branch automatically generates the following default routing policies:

- **Default-ZPA-PBR**: Ensures all traffic originating from the Airgap network and destined for any Zscaler application segment is routed through ZPA. This rule provides branches secure, zero trust access to internal applications based on the source.
- **Default ZIA Rule**: Ensures all internet-bound or Software as a Service (SaaS) traffic originating from the Airgap network is routed to ZIA via IPSec. This rule ensures secure access to the internet and SaaS applications for branch sites. It uses auto-established IPSec tunnels for secure branch-to-ZIA connectivity.
- **Default-App-Segment-PBR**: Ensures traffic originating directly from Zero Trust Branch destined for Zscaler app segments is routed to ZPA.
- **Default Zscaler Rule**: A catch-all routing rule that ensures any traffic from any IPv4 network destined for Zscaler IP addresses is sent through the LAN/WAN interfaces. It routes traffic to the nearest Zscaler data center and uses primary and secondary interfaces for redundancy.

By leveraging default policies, you can immediately enforce zero trust principles while maintaining seamless connectivity to internal applications, cloud services, and the internet.

## Policy-Based Routing

Zero Trust Branch makes routing decisions based on defined criteria in the routing policies, allowing traffic to be steered dynamically between WAN interfaces. Zero Trust Branch can load-balance, fail over, or programmatically optimize distribution. For example, you can configure a routing policy that connects a source (e.g., virtual machine (VM), endpoints, or users) to the internet via two WAN links, using the primary interface for active traffic and the secondary interface for failover or redundancy. The following image illustrates the topology for policy-based routing:

[Image: Illustration of polciy-based routing topology]

With these policies, IT teams can define routing rules to optimize performance, maintain compliance, and adapt to evolving business needs, ensuring that every connection and device is protected at all times.

To learn more, see [Configuring Routing Policies](https://help.zscaler.com/zero-trust-branch/configuring-routing-policies) and [Managing Routing Policies](https://help.zscaler.com/zero-trust-branch/managing-routing-policies).

## Application-Based Routing

You can configure application-based routing policies that allow the Zero Trust Branch appliance to route traffic only to specific applications. You can define a SaaS Apps object with specific applications based on your requirements. Zscaler provides a list of SaaS apps (e.g., 1Password, Adobe, etc.) that you can use to create a SaaS Apps object. Then, you can define a routing policy with the SaaS Apps object as the destination. For example, you can create a SaaS Apps object (e.g., Corp Apps) for corporate applications (e.g., ServiceNow, Atlassian, etc.), and then create a routing policy with the SaaS Apps object (Corp Apps) as the destination criteria. This ensures that only traffic to the specific applications defined in the Corp Apps object is allowed, and all other traffic is blocked.

To learn more, see [Adding Objects](https://help.zscaler.com/zero-trust-branch/adding-objects) and [Configuring Routing Policies](https://help.zscaler.com/zero-trust-branch/configuring-routing-policies).

Zero Trust Branch Management handles and maintains the IP addresses of the SaaS apps, reducing manual maintenance.

Verify IP addresses associated with a routing policy

1. In the console, run the following command to find the destination IP address match set: `iptables -nvL PBR-RULES -t mangle`
2. From the output, note the destination match set value and use it in the following command: `ipset list``<destination-match-set-no>`

The list of IP addresses associated with the routing policy displays.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/understanding-zero-trust-branch-access-roles","lastmod":"2026-08-03T14:35Z","nid":"1532522"} -->
## Understanding Zero Trust Branch Access Roles

- Source: https://help.zscaler.com/zero-trust-branch/understanding-zero-trust-branch-access-roles
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Configuration > Understanding Zero Trust Branch Access Roles
- Last modified: 2026-08-03T14:35Z
- Summary: Information about access roles in the Zero Trust Branch.

Zero Trust Branch uses role-based access control (RBAC) to determine the granted permissions that enable admins and users to perform their job functions. By separating duties across clearly defined roles, RBAC minimizes the risk of misconfigurations, unauthorized access, and insider threats while maintaining operational efficiency and compliance.

## Working with Zero Trust Branch Roles

Beginning with version 8.0.1, Zero Trust Branch is enabled with Authentication Service as its sole identity and authentication management service. To add and manage Zero Trust Branch users and user groups, log in to the Zscaler Admin Console and select the Zero Trust Branch service. To learn more, see [Assigning Entitlements to Users and User Groups](https://help.zscaler.com/zidentity/assigning-entitlements-users-and-user-groups).

## Predefined Roles

Zero Trust Branch does not have custom roles. The following predefined roles are available:

- **Super Admin**: The super admin has the highest level of privileges. This role has full access to security policy configuration, network settings (including routing and VLANs), site and template management, as well as user account creation and administration. Super admins are authorized to audit system logs, apply firmware updates, initiate failover operations, and restore the system as required. Given its broad access scope, this role is typically reserved for trusted system admins with comprehensive oversight responsibilities.
- **Network Admin**: The network admin is responsible for configuring and maintaining all network-related components. This includes managing interfaces, IP addressing, routing protocols, VLAN assignments, and[Simple Network Management Protocol (SNMP) configurations](https://help.zscaler.com/zero-trust-branch/managing-snmp-configurations). This role ensures reliable network connectivity and optimal performance, while operating independently of the platform's security policy configurations.
- **Security Admin**: The security admin is tasked with managing and enforcing the platform's overall security posture. Core responsibilities include the definition and implementation of security policies, continuous monitoring of logs and alerts, and the fine-tuning of countermeasures to safeguard against both external and internal threats. In addition to policy management, the security admin is authorized to initiate device quarantine actions in response to anomalous or malicious activity. This role is also responsible for triggering the [Ransomware Kill Switch](https://help.zscaler.com/zero-trust-branch/understanding-ransomware-kill-switch), which isolates compromised endpoints and restricts lateral movement to prevent the spread of attacks across the site.
- **Viewer**: The viewer role grants users read-only access, including access to associated logs. This role is typically assigned to personnel responsible for auditing, reporting, or oversight functions. Viewers cannot alter any configuration settings, ensuring that the integrity and stability of the system is not compromised.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/understanding-zero-trust-branch-software-upgrades","lastmod":"2026-07-20T15:15Z","nid":"1533885"} -->
## Understanding Zero Trust Branch Software Upgrades

- Source: https://help.zscaler.com/zero-trust-branch/understanding-zero-trust-branch-software-upgrades
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Upgrades > Understanding Zero Trust Branch Software Upgrades
- Last modified: 2026-07-20T15:15Z
- Summary: Information on Zero Trust Branch software upgrades.

Zero Trust Branch appliances support in-place upgrades to ensure access to the latest security features, stability improvements, and platform capabilities. The upgrade workflow varies based on how Zero Trust Branch is deployed in the network.

This article provides a conceptual overview of how the upgrade behavior differs between standalone and high availability (HA) deployments.

## Standalone Deployments

In a standalone deployment, a single Zero Trust Branch appliance manages branch networking and security. Upgrading the appliance requires temporarily taking it offline while the new version is installed and services restart.

### Upgrade Workflow

When a single Zero Trust Branch appliance is deployed in standalone mode, the upgrade process follows this sequence:

1. The upgrade applies directly to the single appliance.
2. Traffic forwarding is briefly interrupted while the appliance reboots (no failover occurs, as there is no secondary appliance).
3. After the upgrade completes, the appliance restores branch connectivity using the new software version.

To learn more, see [Upgrading Zero Trust Branch Appliances in Standalone Deployments](https://help.zscaler.com/zero-trust-branch/upgrading-zero-trust-branch-appliances-standalone-deployments).

## HA Deployments

In HA deployments, the Zero Trust Branch appliances are deployed as a pair in a cluster. The upgrade process is designed to preserve operational continuity with minimal disruption. Zero Trust Branch uses a staged, in-service upgrade workflow to keep HA clusters online while new software is applied. The upgrade is performed on one appliance at a time, allowing the cluster to stay online while each appliance transitions to the new software version.

### Staged Upgrade Workflow

When two Zero Trust Branch appliances—A (**Active**) and B (**Standby**)—are deployed in HA mode, the upgrade process follows this sequence:

1. Appliance B (**Standby**) is upgraded first.
2. After the upgrade, appliance B becomes **Active**and starts handling traffic.
3. Appliance A (originally **Active**and now **Standby**) is upgraded.
4. After both appliances are on the same new version, the appliances remain in their new **Active**and **Standby**states.

For versions 8.0.7 or later, appliances need not run the same version. You can upgrade one appliance first and test it for performance and stability before upgrading the other appliance. If any errors or issues occur with the appliance after upgrading to a new version, you can revert to the old version.

To learn more, see [Upgrading Zero Trust Branch Appliances in an HA Cluster](https://help.zscaler.com/zero-trust-branch/upgrading-zero-trust-branch-appliances-ha-cluster).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/upgrading-zero-trust-branch-appliances-ha-cluster","lastmod":"2026-07-20T16:48Z","nid":"1533867"} -->
## Upgrading Zero Trust Branch Appliances in an HA Cluster

- Source: https://help.zscaler.com/zero-trust-branch/upgrading-zero-trust-branch-appliances-ha-cluster
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Upgrades > Upgrading Zero Trust Branch Appliances in an HA Cluster
- Last modified: 2026-07-20T16:48Z
- Summary: How to upgrade Zero Trust Branch appliances in a high availability (HA) cluster.

Zero Trust Branch uses a staged, in-service upgrade workflow to keep high availability (HA) clusters online while a new software version is applied. The upgrade is performed one gateway at a time, allowing the cluster to stay online while each appliance transitions through the new software version.

To upgrade Zero Trust Branch appliances running in HA mode:

1. Log in to the Zscaler Admin Console.
2. Go to **Deployments**> **Sites**. The **Sites**page appears.
3. Locate the site for which you want to upgrade the Zero Trust Branch appliances, and use one of the following options: The **Gateway Version Manager**drawer appears.
  - Click the **Upgrade**icon next to the version number for the Zero Trust Branch appliance running in the **Standby**state. See image.
  - Click the **Gear**icon for the Zero Trust Branch appliance running in the **Standby**state, and select **Upgrade Gateway Version**. See image.
4. In the **Gateway Version Manager**drawer, upgrade the appliance using one of the following methods: During the upgrade process, each Zero Trust Branch appliance transitions through multiple stages indicated by the upgrade status. After completing the upgrade process, the Zero Trust Branch appliance automatically transitions to the **Active**state. The other Zero Trust Branch appliance goes to the **Standby**state, and the upgrade process starts automatically based on the method used for the previous Zero Trust Branch appliance. Upgrade statuses
  - Standard method
  - Root FS method

The following table provides the list of upgrade statuses:

| Status | Description |
| --- | --- |
| Downloading | The Zero Trust Branch appliance software version is being downloaded. |
| Updating | The downloaded version is being applied to the Zero Trust Branch appliance. |
| Restarting | The Zero Trust Branch appliance is rebooting after the update. |
| Initializing | The Zero Trust Branch appliance is initializing with the new software version. |
| Post-Update | The Zero Trust Branch appliance is running the necessary scripts, utilities, and containers. |
| Active or Standby | The update is completed and the Zero Trust Branch appliance has resumed normal operation. |

1. **Available Versions**: Select the version to which you want the Zero Trust Branch appliance to be upgraded from the drop-down menu. See image.
2. Click **Upgrade**.

The Root FS upgrade method allows you to download up to three image versions of the Zero Trust Branch appliance software, and you can install one of these versions based on your requirements. You can also designate one version as a default version, which allows Zero Trust Branch to apply the image automatically during a factory reset.

- This upgrade method is currently in Preview.
- Only Zero Trust Branch appliances with versions 8.0.6 or later are supported.

1. Enable **Use Root FS Upgrade Method**.
2. **Downloadable Versions**: Select the version image that must be downloaded to the Zero Trust Branch appliance.
3. Click **Download**. See image. The selected version image is downloaded to the Zero Trust Branch appliance and the status displays in the **Downloaded Versions**table. Beginning with version 8.0.6 and later, the image size was optimized to allow downloading and storing up to three images on the appliance. If your appliance includes older images (e.g., 8.0.4 HF1), the downloading and installation of the third image might fail due to space constraints. To avoid this issue, you can delete one of the older images.
4. After the download is complete, select the corresponding entry in the **Downloaded Versions**table and click **Activate**. See image. The selected version is installed on the Zero Trust Branch appliance and the status displays in the **Downloaded Versions**table.
5. Select the image and click **Set as Default**.

[Image: The Sites page showing the option to upgrade in the Version column]

[Image: The Sites page showing the option to upgrade in the Gear drop-down menu]

[Image: Upgrading Zero Trust Branch appliances using the Standard method]

[Image: Upgrading Zero Trust Branch appliances using the Root FS method]

[Image: Activating a downloaded version in the Zero Trust Branch appliance]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/upgrading-zero-trust-branch-appliances-standalone-deployments","lastmod":"2026-07-20T16:47Z","nid":"1533886"} -->
## Upgrading Zero Trust Branch Appliances in Standalone Deployments

- Source: https://help.zscaler.com/zero-trust-branch/upgrading-zero-trust-branch-appliances-standalone-deployments
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Upgrades > Upgrading Zero Trust Branch Appliances in Standalone Deployments
- Last modified: 2026-07-20T16:47Z
- Summary: How to upgrade a Zero Trust Branch appliance running in standalone mode.

Zero Trust Branch upgrades standalone appliances through an in-place software update. The appliance briefly goes offline while the new version is installed and the system restarts.

To upgrade Zero Trust Branch appliances running in standalone mode:

1. Log in to the Zscaler Admin Console.
2. Go to **Deployments**> **Sites**. The **Sites**page appears.
3. Locate the site for which you want to upgrade the Zero Trust Branch appliance, and use one of the following options: The **Gateway Version Manager**drawer appears.
  - Click the **Upgrade**icon next to the version number. See image.
  - Click the **Gear**icon and select **Upgrade Gateway Version**. See image.
4. In the **Gateway Version Manager**drawer, upgrade the appliance using one of the following methods: During the upgrade process, the Zero Trust Branch appliance transitions through multiple stages indicated by the upgrade status. After completing the upgrade process, the Zero Trust Branch appliance automatically transitions to the **Active**state. Upgrade statuses
  - Standard method
  - Root FS method

The following table provides the list of upgrade statuses:

| Status | Description |
| --- | --- |
| Downloading | The Zero Trust Branch appliance software version is being downloaded. |
| Updating | The downloaded version is being applied to the Zero Trust Branch appliance. |
| Restarting | The Zero Trust Branch appliance is rebooting after the update. |
| Initializing | The Zero Trust Branch appliance is initializing with the new software version. |
| Post-Update | The Zero Trust Branch appliance is running the necessary scripts, utilities, and containers. |
| Standalone | The update is completed and the Zero Trust Branch appliance has resumed normal operation. |

1. **Available Versions**: Select the version to which you want the Zero Trust Branch appliance to be upgraded from the drop-down menu. See image.
2. Click **Upgrade**.

The Root FS upgrade method allows you to download up to three image versions of the Zero Trust Branch appliance software, and you can install one of these versions based on your requirements. You can also designate one version as a default version, which allows Zero Trust Branch to apply the image automatically during a factory reset.

- This upgrade method is currently in Preview.
- Only Zero Trust Branch appliances with versions 8.0.6 or later are supported.

1. Enable **Use Root FS Upgrade Method**.
2. **Downloadable Versions**: Select the version image that must be downloaded to the Zero Trust Branch appliance.
3. Click **Download**. See image. The selected version image is downloaded to the Zero Trust Branch appliance and the status displays in the **Downloaded Versions**table. Beginning with version 8.0.6 and later, the image size was optimized to allow downloading and storing up to three images on the appliance. If your appliance includes older images (e.g., 8.0.4 HF1), the downloading and installation of the third image might fail due to space constraints. To avoid this issue, you can delete one of the older images.
4. After the download is complete, select the corresponding entry in the **Downloaded Versions**table and click **Activate**. See image. The selected version is installed on the Zero Trust Branch appliance and the status displays in the **Downloaded Versions**table.
5. Select the image and click **Set as Default**.

[Image: The Sites page showing the option to upgrade in the Version column]

[Image: The Sites page showing the option to upgrade in the Gear drop-down menu]

[Image: Upgrading Zero Trust Branch appliances using the Standard method]

[Image: Upgrading Zero Trust Branch appliances using the Root FS method]

[Image: Activating a downloaded version in the Zero Trust Branch appliance]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/uploading-nac-mac-authentication-list","lastmod":"2026-07-13T16:54Z","nid":"1529368"} -->
## Uploading a NAC-MAC Authentication List

- Source: https://help.zscaler.com/zero-trust-branch/uploading-nac-mac-authentication-list
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust Device Segmentation > Uploading a NAC-MAC Authentication List
- Last modified: 2026-07-13T16:54Z
- Summary: How to upload a list of MAC addresses for use with network access control (NAC).

Rather than allowing Zero Trust Branch to scan and discover your devices, for greater network security, you can upload a comma-separated values (CSV) list of MAC addresses that you want to include in your installation. Zero Trust Branch uses this list to verify devices and authenticate them during network access control (NAC).

To upload a list of MAC addresses to Zero Trust Branch:

1. Go to **Firewall > NAC-MAC Authentication** and click **Upload CSV**. See image.
2. In the **Upload CSV**panel, click **Download Template**to download a CSV template. The template consists of two fields: MAC address and a corresponding description for each device. See image.
3. On your computer, complete the CSV template with the MAC addresses and descriptions of the devices that you want to include in your installation, then save the template file. See image.
4. In the **Upload CSV** panel, drag the template file to the box, or click**Upload a CSV file** and select the file location on your computer. See image.
5. Click **Validate**to view a preview of the information you are uploading. See image.
6. Verify that the information is correct, and click **Submit**to add the devices to Zero Trust Branch. See image.
7. The **NAC-MAC Authentication** list displays with the devices that you added. See image.

[Image: NAC-MAC Authentication page.]

[Image: Downloading a template from the Upload CSV panel.]

[Image: Sample CSV file]

[Image: Uploading a CSV file from the Upload CSV panel]

[Image: Validating a CSV file from the Upload CSV panel]

[Image: Submitting a CSV file from the Upload CSV panel]

[Image: NAC-MAC Authentication page with a device.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/using-appliance-version-manager","lastmod":"2026-06-29T07:06Z","nid":"1539798"} -->
## Using Appliance Version Manager

- Source: https://help.zscaler.com/zero-trust-branch/using-appliance-version-manager
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Deploying Zero Trust Branch > Using Appliance Version Manager
- Last modified: 2026-06-29T07:06Z
- Summary: Information on how to use the new upgrade mechanism called Appliance Version Manager in Zero Trust Branch.

Zero Trust Branch release 8.0.7 introduces a new upgrade mechanism called Appliance Version Manager for appliances already registered to a Zero Trust Branch tenant. With this release, the [software upgrade process](https://help.zscaler.com/zero-trust-branch/understanding-zero-trust-branch-software-upgrades) is transitioned to a full image–based system, including support for downgrades. Prior to release 8.0.7, Zero Trust Branch used the legacy incremental upgrade method where only selected packages were upgraded and downgrades were not supported.

To begin using the Appliance Version Manager, Zscaler recommends that you use the legacy method to upgrade the appliance to version 8.0.7. After upgrading to version 8.0.7, Appliance Version Manager becomes fully available for subsequent upgrades and downgrades. Zscaler recommends using Appliance Version Manager for appliances running version 8.0.7 or later.

## Advantages of Appliance Version Manager

Appliance Version Manager provides a reliable and safer upgrade mechanism for Zero Trust Branch appliances. The full image–based approach improves upgrade predictability, reduces boot time, enables controlled rollbacks, and simplifies maintenance, including these advantages:

- Boot time reduction: During activation or reboot, the appliance downloads microservices from the repository, which is a major task. Appliance Version Manager (based on root file system (Root FS)) downloads the entire package to the file system and everything is preconfigured and preregistered, which significantly reduces the appliance boot time.
- Full file sytem: The full file-system upgrade bundle contains the kernel, Root FS, feature file, and bootloader. The feature file specifies supported features like migration and disk encryption. Migration helps determine how existing data and configurations are transitioned during the upgrade, and disk encryption ensures data is protected during and after the upgrade.

## Upgrade an Appliance

Zero Trust Branch supports both legacy and Root FS-based Appliance Version Manager methods for upgrading appliances running version 8.0.7 or later. Starting with version 8.1.1, you can upgrade using only the Root FS-based Appliance Version Manager, as the legacy method is no longer supported.

Image upgrade using Appliance Version Manager includes the following two stages:

- 1. Download and install the image.
- 2. Activate the image.

## Downgrade an Appliance

For Zero Trust Branch appliances that are in Active and Standby states, you must first downgrade the appliance in Standby state, switch over, validate the action, and then downgrade the second appliance in Active state.

Image downgrade is supported up to version 8.0.7.

To downgrade a Zero Trust Branch appliance from a later version to an earlier version:

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Sites**.
2. On the **Sites** page, view the list of Zero Trust Branch appliances in **Standby** and **Active** states. See image. A38-ZT800 and A39-ZT800 appliances are used as examples to explain the procedure.
3. Downgrade the A38-ZT800 appliance that is in **Standby** state. Click the **Download** icon for A38-ZT800. See image.
4. In the **Appliance Version Manager** window, under **Downloaded Versions**, select**8.0.8P2**, then click **Activate**. See image.
5. Go back to the **Sites**page. The appliance state shows as **Restarting** in the **State column**. See image.
6. After the downgrade is completed, the appliance's state shows as **Standby**in the **State column**. See image.
7. To verify the A38-ZT800 appliance's health before switchover, log in to the Zero Trust Branch console, enter `7`, and press `Enter`. See image.
8. Run the following commands:
  - ``ztb--Site1-ZT800--AG38-ZT800:~$ifconfig zpa0``
  - `ztb--Site1-ZT800--AG38-ZT800:~$docker ps`
  - `ztb--Site1-ZT800--AG38-ZT800:~$ls -ltr /etc/airgap/zcc`
  - `ztb--Site1-ZT800--AG38-ZT800:~$service airgap-dhcp status`
  - `ztb--Site1-ZT800--AG38-ZT800:~$service securedhcp-relay status`
  - `ztb--Site1-ZT800--AG38-ZT800:~$service agn-state-monitor status`
  - `ztb--Site1-ZT800--AG38-ZT800:~$docker exec vyos_container su - vyos sh -c '/opt/vyatta/bin/vyatta-op-cmd-wrapper show vrrp'`
9. After confirming that the A38-ZT800 appliance is healthy, you can perform the switchover.
10. Click the **Gear** icon to start the switchover. See image.
11. Repeat the steps to downgrade the A39-ZT800 appliance to an earlier version.

This step does not require downtime and can be performed at any time.

Appliance Version Manager allows you to download and store up to three Zero Trust Branch image versions (8.0.7 or later). The disk might not have sufficient space to store three images if older versions (e.g., 8.0.7) already exist. Zscaler recommends deleting the older images before downloading newer versions. One downloaded image (also known as the factory default image) is always marked as default, and it has the following advantages:

- If any unknown issue occurs and requires a factory reset, the appliance rolls back to this image.
- An unexpected power failure or network connectivity loss during the upgrade process could lead to incomplete kernel or Root FS deployment. In such cases, you can roll back to the factory default image.
- The default image cannot be deleted.
- During upgrade, the kernel and Root FS are downloaded to the boot partition. The active kernel extracts the Root FS to the data partition. Performing a factory reset (UI Delete Site, UI RMA, or airgap-console factory-reset) erases the data partition and restores the factory default Root FS.

You can set any of the other downloaded images as default. To learn more, see [Upgrading Zero Trust Branch Appliances in Standalone Deployments](https://help.zscaler.com/zero-trust-branch/upgrading-zero-trust-branch-appliances-standalone-deployments) and [Upgrading Zero Trust Branch Appliances in an HA Cluster](https://help.zscaler.com/zero-trust-branch/upgrading-zero-trust-branch-appliances-ha-cluster).

You can activate any of the downloaded images. This step requires a system reboot, which must be performed during a scheduled downtime. The appliance will boot using this image during the next restart. To learn more, see [Upgrading Zero Trust Branch Appliances in Standalone Deployments](https://help.zscaler.com/zero-trust-branch/upgrading-zero-trust-branch-appliances-standalone-deployments) and [Upgrading Zero Trust Branch Appliances in an HA Cluster](https://help.zscaler.com/zero-trust-branch/upgrading-zero-trust-branch-appliances-ha-cluster).

[Image: View the list of appliances on the Sites page]

[Image: Click the Download icon for the appliance in Standby state]

[Image: Select the downgrade version]

[Image: The appliance restarts after it is downgraded to a lower version]

[Image: The appliance is downgraded to version 8.0.8P2]

[Image: Click the Gear icon to start the switchover]

[Image: Verify the appliance version]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/using-zero-trust-branch-debug-console-for-troubleshooting","lastmod":"2026-05-31T20:37Z","nid":"1533903"} -->
## Using the Zero Trust Branch Debug Console for Troubleshooting

- Source: https://help.zscaler.com/zero-trust-branch/using-zero-trust-branch-debug-console-for-troubleshooting
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Troubleshooting > Using the Zero Trust Branch Debug Console for Troubleshooting
- Last modified: 2026-05-31T20:37Z
- Summary: Understanding and using the Zero Trust Branch Debug Console.

The Zero Trust Branch Debug Console provides a purpose-built diagnostic framework that exposes the internal state of an appliance through simple, direct commands. Commands are grouped by functional area, so you can quickly locate the information you need—whether validating IPSec tunnels, checking DHCP leases, analyzing routing tables, or reviewing Virtual Router Redundancy Protocol (VRRP) status. The Zero Trust Branch Debug Console supports both deployment readiness checks and ongoing operational troubleshooting.

## Accessing the Debug Console

The Debug Console is intentionally site specific, matching the appliance deployed at that location. This ensures that you always work in the correct operational context.

To access the Debug Console:

1. Go to **Infrastructure**> **Connectors**> **Edge**> **Sites**.
2. Click the name of the site for which you want to access the Debug Console. See image.
3. On the site details page, click the **Console**tab. See image.

## Troubleshooting Commands

You can use the Debug Console to run site-specific commands for validation and troubleshooting of network issues. Some of the commonly used commands are listed for the following categories:

- Basic Network Troubleshooting
- DHCP Diagnostics
- Policy-Based Routing (PBR)
- Internet & SaaS (ZIA) Tunnel Validation
- Private Access (ZPA) App Connector Diagnostics
- VRRP for High Availability (HA)
- Routed Tunnels (RTs)
- WAN Link Monitoring
- DNS Forwarding
- DNS Policies
- SNMP Configuration

The following are some of the commands for basic network diagnostics and troubleshooting:

| Command | Purpose | Use Case |
| --- | --- | --- |
| `ifconfig` | Verifies interface status, link state, and assigned IP addresses | Check whether the interface is up, has an IP address, or is negotiating the link correctly. |
| `docker exec vyos_container su - vyos sh -c` | Shows operational status of VyOS-level network interfaces or runs advanced diagnostics | Troubleshoot internal network behavior. |
| `ping -c``<execution-count>``<host>` When using the ping command, always use the execution count parameter. Unbounded ping command usage results in failure. | Validates reachability and basic latency to a destination | Check if the appliance can reach another host, service, or upstream gateway. |
| `traceroute``<host>` | Shows the hop-by-hop path to a destination | Verify the path used if the traffic reaches the destination via an unexpected path or fails mid-route. |
| `ip route` | Displays the active routing table and nexthop decisions | Validate how the traffic should be forwarded or confirm the default route selection. |
| `tcpdump -i``<interface>``-c``<execution-count>` When using the tcpdump command, always use the execution count parameter. Unbounded tcpdump command usage results in failure. | Captures packets on a specific interface for analysis | Diagnose packet drops, tunnel negotiation failures, DNS anomalies, or unexpected traffic behavior. |
| `service systemd-timesyncd status` | Shows whether the Network Time Protocol time is synchronized | The Network Time Protocol (NTP) must be configured and the appliance and device clocks must be synchronized. If the clocks are inaccurate, a valid certificate might be shown as expired or not valid, which could prevent a secure connection. Make sure the L-3 device allows the User Datagram Protocol (UDP) 123 traffic that is sourced from the appliance to these destinations. |

The following are some of the commands for DHCP diagnostics and troubleshooting:

| Command | Purpose | Use Case |
| --- | --- | --- |
| `ls /etc/dhcp` | Displays the DHCP configuration directory and files | Locate DHCP configuration files and verify that the expected files are present. |
| `cat /etc/dhcp/``<file-name>` | Shows the contents of the specified DHCP configuration file | Confirm DHCP lease ranges, DNS settings, and other configuration options. |
| `service airgap-dhcp status` | Shows the status of the DHCP service | When endpoints fail to receive an IP address, verify whether the DHCP service is running. |
| `service securedhcp-relay status` | Checks the operational state of the DHCP relay | Validate DHCP processing if endpoints are not receiving IP addresses. |
| `airgap-dhcp-cli -a list-leases` | Lists current DHCP leases, including IP and MAC address bindings | Check allocation history or troubleshoot lease conflicts. |
| `tail -n 10 /var/log/airgap-dhcp` You can increase the -n count to show more entries. | Shows the list of recent DHCP events | Debug DHCP failures or unexpected behaviors. |
| `cat /etc/dhcp/dhcpd.conf` | Shows the DHCP configuration details stored in the `dhcpd.conf` file. | Check if the DHCP server service is running. |
| `airgap-dhcp-cli -a list-leases` | Shows the IP addresses assigned. | Check the list of IP addresses that are assigned to DHCP. |
| `tail -20 /var/log/airgap-dhcp` | Shows the DHCP-related log messages. | Check the allocated lease for MAC. |
| `airgap-dhcp-cli -a get-debug-level` | Changes the log level for DHCP messages. | Change the default log level (4) to another level. |
| `tcpdump -c 10 -i <``gateway interface``> "<``udp port xx or udp port yy``>"` | Shows DHCP-related packets. | Collect DHCP packets. |

The following are some of the commands for PBR diagnostics and troubleshooting:

| Command | Purpose | Use Case |
| --- | --- | --- |
| `iptables -nvL PBR-RULES -t mangle \| grep MARK` | Lists PBR rules along with applied flow marks | Validate routing behavior based on policies. |
| `ipset list``<set-name>` | Lists the details and members of a specific IP address set | Validate and monitor which IP addresses are currently included in a set referenced by policy rules. |
| `ip rule ls \| grep``<flow-mark>` | Shows which routing rule and routing table apply to traffic with the specified flow mark | Verify how traffic tagged with the specific flow mark is routed (i.e., the table it uses). |
| `ip route show table``<table-name>` | Displays the routes configured in a specific PBR table | Verify the nexthop path and interface selection for traffic mapped to that routing table. |

The following are some of the commands for Internet & SaaS tunnel validation diagnostics and troubleshooting:

| Command | Purpose | Use Case |
| --- | --- | --- |
| `docker ps \| grep strongswan` | Checks whether the strongSwan container is running | Use when the Internet & SaaS IPSec tunnel is down or not responding, and you want to confirm the IPSec process is up. |
| `cat /mnt/agdisk/etc/airgap/strongswan/swanctl.conf \| grep "zia\\|addrs\\|proposal"` | Shows Internet & SaaS-related entries (e.g., tunnel name, addresses, proposals) from the swanctl.conf file | Verify the Internet & SaaS tunnel ID, local or remote addresses, or crypto proposals configured on the appliance. |
| `docker logs strongswan` | Shows logs from the strongSwan container If the command output displays 'auth failed', then check in Internet & SaaS that the location associated with the site is not deleted or the IPSec credentials are not removed or changed. | Use when tunnel negotiation is failing, and you need error details from the IPSec daemon. |
| `docker exec strongswan swanctl --terminate --ike``<tunnel-name>` | Terminates the Internet & SaaS IPSec tunnel | Terminate the IPSec tunnel due to unexpected behavior. |
| `docker exec strongswan swanctl --initiate --ike``<tunnel-name>` | Initiates the Internet & SaaS IPSec tunnel | Re-establish the IPSec tunnel after termination or configuration changes. |
| `docker exec strongswan ipsec status` | Shows the current IPSec tunnel status | Verify whether the Internet & SaaS tunnel is established and functioning. |
| `ip tunnel` | Lists all GRE tunnels (local and remote) associated with the interface | Verify whether the expected GRE tunnels exist and are up. |
| `ip route \| grep ge5` | Checks the static routes for the GRE endpoints via an interface | Verify that Internet & SaaS traffic (or tunnel endpoints) are routed out through the correct WAN interface. |
| `ip rule ls \| grep ipsec` | Checks the flow mark that will take the IPSec path | Verify whether there is a flow mark that takes the IPSec path. |

The following are some of the commands for Private Access App Connector diagnostics and troubleshooting:

| Command | Purpose | Use Case |
| --- | --- | --- |
| `service agn-poller status` | Checks whether the Private Access App Connector polling service is running | Use when the Private Access application is not reachable. |
| `ls -l /etc/airgap/zcc` | Lists all configuration, metadata, and enrollment files | Validate whether Zero Trust Branch is correctly enrolled with Private Access. |
| `cat /etc/airgap/zcc/zcc_config.json` | Shows information such as Internet & SaaS Org ID, Private Access Org ID, Internet & SaaS cloud, Private Access cloud, and Private Access broker | Validate enrollment details, cloud information, and other parameters. |
| `cat /etc/airgap/zcc/fqdn.txt` | Displays the Private Access application segments (in FQDN format) downloaded to the Zero Trust Branch appliance | Validate the Private Access application segments (in FQDN format) downloaded to the Zero Trust Branch appliance. |
| `cat /etc/airgap/zcc/ip.txt` | Displays the Private Access application segments (in IP address format) downloaded to the Zero Trust Branch appliance | Validate the Private Access application segments (in IP address format) downloaded to the Zero Trust Branch appliance. |
| `tail -n 10` | Displays the recent DNS logs | Troubleshoot DNS resolution. |
| `docker ps -a` | Shows the Private Access App Connector operational status | Check whether the Private Access App Connector is running. |
| `docker volume ls` | Shows details of the provisioning key, certificate, etc. stored in the App Connector | Check where the provisioning key, certificate, etc. are stored. |
| `docker logs --tail 100 Proctor02AppC` | Shows the App Connector docker logs | Verify the App Connector's docker logs. |
| `curl localhost:9000/fohh/clients?small` | Shows the brokers that are connected to the App Connector | Verify the brokers connected to the App Connector. |
| `curl localhost:9000/assistant/state/dump \| grep 1` | Shows the App Connector name and instance ID | Verify the App Connector name and instance ID that is spun up in the docker container. |
| `curl localhost:9000/assistant/app/dump/apps` | Shows the list of Private Access apps that are downloaded | Verify the list of Private Access apps that are downloaded. |
| `docker restart <``appconnector name``>` | Restarts the App Connector docker container | Restart the App Connector docker container. |
| `docker logs -n 100 <``appconnector name``>` | Shows the logs for the specific App Connector | Verify the App Connector logs. |

The following are some of the commands for VRRP diagnostics and troubleshooting:

| Command | Purpose | Use Case |
| --- | --- | --- |
| `sudo docker exec vyos_container su - vyos sh -c '/opt/vyatta/bin/vyatta-op-cmd-wrapper show config'` | Displays the HA configuration status | Check whether the VRRP, sync settings, and interface bindings for HA settings are configured correctly. |
| `docker exec vyos_container su - vyos sh -c '/opt/vyatta/bin/vyatta-op-cmd-wrapper show vrrp'` | Shows the current VRRP status, including role and priority | Confirm whether an appliance is active or troubleshoot unexpected VRRP role changes. |
| `docker exec vyos_container su - vyos sh -c 'sudo systemctl status keepalived` | Verifies whether the keepalived service is running | Use when the appliance does not switch roles or when the VRRP state appears unstable. |
| `docker exec vyos_container su - vyos sh -c '/opt/vyatta/bin/vyatta-op-cmd-wrapper show conntrack-sync status'` | Displays the connection tracking synchronization status between HA peers | Use when failover results in a new session drop to confirm that connection sync is working between active and standby appliances. |
| `docker exec vyos_container su - vyos sh -c '/opt/vyatta/bin/vyatta-op-cmd-wrapper show vrrp detail'` | Displays the detailed VRRP operational information | Perform deeper diagnostics to investigate timing issues, state transitions, or instability between HA roles. |
| `docker exec vyos_container su - vyos sh -c 'journalctl -u keepalived \| head -200'` | Displays the recent logs for the keepalived (VRRP) service | Troubleshoot VRRP issues and review keepalived service logs for errors and failover events. |
| `docker exec vyos_container su - vyos sh -c '/opt/vyatta/bin/vyatta-op-cmd-wrapper show int summary'` | Shows the HA interface details | Verify the HA interface details. |
| `tcpdump -i ge4 -c <``number of packets``>` | Shows the packets captured on the HA interface | Verify the packets captured on the HA interface. |

The following are some of the commands for RT diagnostics and troubleshooting:

| Command | Purpose | Use Case |
| --- | --- | --- |
| `ifconfig``<overlay-tunnel-interface>` | Displays the interface details for the specific GRE tunnel | Verify whether the GRE tunnel interface is up and reachable, and has expected IP address assignment. |
| `ifconfig``<routed-tunnel-interface>` | Displays the status and IP address configuration of the RT interface | Verify whether the RT interface is active and configured correctly. |
| `docker exec vyos_container su - vyos sh -c 'ip rule'` | Displays routing rules associated with tunnel-based border gateway protocol (BGP) routing | Verify whether PBR is correctly steering BGP-learned prefixes into the RT. |
| `sudo docker exec vyos_container su - vyos sh -c '/opt/vyatta/bin/vyatta-op-cmd-wrapper show vrf'` | Displays Virtual Routing and Forwarding (VRF) instances used for routing BGP traffic through RTs | Verify whether a dedicated VRF instance exists and is applied for BGP peering and traffic separation. |
| `docker exec vyos_container su - vyos sh -c 'ip neighbor \| grep s2s'` | Displays BGP peering information over the GRE tunnel path | Verify whether a BGP session over the routed GRE tunnel is established and the peer is reachable. |
| `sudo docker exec vyos_container su - vyos sh -c '/opt/vyatta/bin/vyatta-op-cmd-wrapper show ip route table 500'` | Displays routing table entries for the RTs | Verify whether the expected prefixes are present and routed through the tunnel interface. |
| `ip address \| grep wg_vpn` | Shows the RT details | Verify the RT at the spoke. |
| `ip address \| grep s2s_overlay` | Shows the GRE tunnel over RT at the spoke | Verify the GRE overlay interface. |
| `ip route show table 500` | Shows the spoke subnets | Verify the spoke subnets that are advertised and available at the spokes. |

The following are some of the commands for WAN link monitoring and troubleshooting:

| Command | Purpose | Use Case |
| --- | --- | --- |
| `cat /var/log/syslog \| grep wan-mon` | Displays WAN-monitoring log entries from syslog | Verify WAN monitor events such as link up/down or score changes. |
| `ip rule` | Displays routing rules for WAN | Verify how routing decisions are being made based on WAN scoring or priority. |
| `ip route show table``<wan-table>` | Displays the WAN routing table | Check which WAN interface is preferred based on WAN performance scoring. |
| `ip route show \| grep default` | Displays the current default route entries | Check which WAN interface is used as the default egress path. |

The following are some of the commands for DNS forwarding diagnostics and troubleshooting:

| Command | Purpose | Use Case |
| --- | --- | --- |
| `cat /etc/airgap/dns_proxy/baseconfig` | Displays the DNS policy base configuration | Verify whether DNS policy enforcement and forwarding behavior are configured correctly. |
| `cat /etc/airgap/dns_proxy/cache_config.json` | Displays the DNS cache configuration | Verify DNS cache settings such as TTL and cache size for troubleshooting. |
| `curl -s localhost:9153/metrics \| grep "^coredns_dnscache"` | Shows DNS cache statistics | Verify DNS cache effectiveness based on hit/miss patterns. |
| `docker ps -a` | Shows the DNS proxy operational status | Verify whether the DNS proxy is running. |
| `iptables -t nat -nvL PREROUTING` |  | Verify the DNS traffic diverted from the LAN-side client to the DNS proxy. To divert client DNS traffic to a DNS proxy, use the DNAT method. DNS proxy runs on 127.0.0.1:1053. Add a DNAT rule in the PREROUTING chain in the NAT table, to capture and divert all client-generated DNS traffic to the DNS proxy. |
| `cat /etc/airgap/zcc/mapping_debug.txt` | Shows the FQDN to synthetic IP address mapping | Check the FQDN to synthetic IP address mapping |

The Simple Network Management Protocol (SNMP) standard is used for network monitoring and management. The following are some of the commands to verify the SNMP configuration:

| Command | Purpose | Use Case |
| --- | --- | --- |
| `sudo docker exec vyos_container su - vyos sh -c '/opt/vyatta/bin/vyatta-op-cmd-wrapper show configuration'` | Shows the SNMP configuration details | Verify whether the SNMP is enabled on Zero Trust Branch. |
| `iptables -nvL SECURITY_RULES` | Shows the interfaces on which SNMP monitoring can be performed | Verify the management and WAN interfaces on which SNMP monitoring can be performed. SNMP monitoring is not supported on the LAN interface. |
| `tcpdump -i lo udp port 161` | Shows the SNMP walk packets received in Zero Trust Branch; Shows that Zero Trust Branch is responding to the SNMP queries | Verify whether the SNMP walk packets are received in Zero Trust Branch, and whether Zero Trust Branch is responding to the SNMP queries. If SNMP monitoring is performed on the management interface over Private Access, the packet is received at the management interface and sent to the loopback interface. Perform the packet capture on the loop interface. |
| `tcpdump -i ge3 udp port 161` | Captures SNMP packets on an active or standby appliance | A single App Connector runs on an active appliance, so the packets are received from the active gateway and forwarded to the standby gateway using the management interface. If packets are received from the active gateway on the management interface, then there are no packets on the loopback interface. |

Zero Trust Branch appliances log all DNS activities and send a copy of the log to Zero Trust Branch Management for storage. The following are some of the commands for DNS diagnostics and troubleshooting based on DNS policies:

| Command | Purpose | Use Case |
| --- | --- | --- |
| `ls -lrt /etc/airgap/dns_proxy/policy_logs` | Lists available DNS policy log files | Verify whether DNS policy logs are generated or check the timestamps for the last log file. |
| `tail /etc/airgap/dns_proxy/policy_logs/dns_policy.log` | Displays the recent DNS policy log entries | Troubleshoot DNS policy enforcement issues and monitor live DNS resolution attempts. |
| `tail /var/log/syslog \| grep module_dns_policy` | Filters syslog messages to display entries specific to the DNS policy module | View syslog entries for the DNS policy module. |

[Image: Sites page showing a site name]

[Image: Zero Trust Branch CLI Console.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/viewing-managing-security-insights","lastmod":"2026-04-30T08:04Z","nid":"1524951"} -->
## Viewing and Managing Security Insights

- Source: https://help.zscaler.com/zero-trust-branch/viewing-managing-security-insights
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Analytics & Monitoring > Viewing and Managing Security Insights
- Last modified: 2026-04-30T08:04Z
- Summary: Information on the Security Insights dashboard in Zero Trust Branch.

The Security Insights dashboard provides customizable widgets that display important information about the assets, VLANs, gateways, and devices in your organization to give you a real-time overview of your organization's security operations.

## About the Security Insights Dashboard

On the Security Insights dashboard (Analytics > Zero Trust Branch > Insights), you can do the following:

This page is available only if the **Switch to Existing Reports** option is enabled in the left-side navigation of the **Analytics**tab.

See image.

1. View predefined widgets that show a summary of information about an aspect of your security profile and link to pages with more detailed information.
2. Refresh the data shown in the dashboard by clicking **Refresh**.
3. Access a menu that lets you save the current view as the default, or revert to the default view.
4. View and editcustomized widgets.
5. View tasks that require your action.

[Image: Typical Security Insights dashboard.]

## Predefined Widgets

There are two predefined widgets at the top of the dashboard that provide links to other areas of the Zscaler Admin Console:

- Assets
- Sites

The Assets widget shows the total number of assets in your organization. Click **Learn more**to view more details on the Assets page. To learn more, see [Managing Your Assets](https://help.zscaler.com/zero-trust-branch/managing-your-assets).

The Sites widget shows the total number of sites in your organization. Click **Learn more** to view more details on the Sites page. To learn more, see [Adding a Site](https://help.zscaler.com/zero-trust-branch/adding-site).

## Customizing the Security Insights Dashboard

You can customize the Security Insights dashboard to display graphs most helpful to you in keeping your organization secure. By default, the dashboard shows the following 5 graphs with commonly analyzed data:

- **Devices by Category** shows all devices in your organization in a bar chart, organized by category (e.g., VMware).
- **IoT by Model** shows all Internet of Things devices in a bar chart, organized by model (e.g., Roomba).
- **Medical by Type** shows all medical devices in a pie chart, organized by device type (e.g., alarms).
- **Manufacturing Equipment by Brand** shows all manufacturing equipment in a pie chart, organized by equipment brand (e.g., Hewlett-Packard).
- **Network Equipment by Type** shows all network equipment in a pie chart, organized by equipment type (e.g., access points).

You can customize any or all of these widgets to show information in a different chart type or to display different information types. To modify a dashboard widget:

1. Hover over a widget to make the **Edit**icon appear.
2. Click the **Edit**icon.
3. Modify the fields to display the information you want:
  - **Title**: The widget's name. To bold any text, surround the text with `**` (e.g., `**Title**`). Hover over **?** to see a list of variables you can add to the description (e.g., `$groupBy` displays the field used to group the chart).
  - **Description**: (Optional) A description of the widget. To bold any text, surround the text with `**` (e.g., `**Title**`). Hover over **?** to see a list of variables you can add to the description (e.g., `$groupBy` displays the field used to group the chart).
  - **TopN**: The maximum number of categories to display, starting with the most populated categories.

[Image: Enabling existing reports]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/viewing-traffic-flow-charts","lastmod":"2026-04-21T02:29Z","nid":"1509071"} -->
## Viewing Traffic Flow Charts

- Source: https://help.zscaler.com/zero-trust-branch/viewing-traffic-flow-charts
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Analytics & Monitoring > Viewing Traffic Flow Charts
- Last modified: 2026-04-21T02:29Z
- Summary: How to use traffic flow charts in Zero Trust Branch to visualize your network.

Zero Trust Branch provides extensive visibility into lateral traffic. Every communication between the endpoints is logged and mapped onto the traffic flow chart. This information is saved for 90 days by default. The traffic flow chart visualizes the entire network, connected endpoints, and the communication between them. Note that it can take up to 10 minutes after an event before the traffic is reflected in the traffic flow chart. For more real-time response, consider using flow logs. To learn more, see [Monitoring & Logs](https://help.zscaler.com/zero-trust-branch/monitoring-logs).

The traffic flow chart is built on the graph database. Each endpoint is a node in the graph, and communication between endpoints is shown as an edge between the nodes. Clicking a node provides additional details on the endpoint, including network details, tags, and other endpoint attributes.

To access the traffic flow chart:

1. Go to **Infrastructure** > **Connectors** > **Edge** > **Charts**.
2. Click any node to see details of the endpoint attributes. Double-click a node to drill down into that node. See image. Each of the edges/communications is color-coded based on the policy outcomes (allow vs. deny):
  - Red: Communication is blocked.
  - Green: Communication is allowed.
  - Grey: Communication uses a default policy.
  - Black: There are multiple traffic flows with different protocols.
3. Click an edge to view its traffic flow details in a table.

[Image: A typical traffic flow chart.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/what-site-dns-policies","lastmod":"2026-07-16T11:06Z","nid":"1531224"} -->
## What Are Site DNS Policies?

- Source: https://help.zscaler.com/zero-trust-branch/what-site-dns-policies
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust SD-WAN > Site DNS Policies > What Are Site DNS Policies?
- Last modified: 2026-07-16T11:06Z
- Summary: Introductory information, key features, and benefits of DNS policies used for Zero Trust Branch sites.

The DNS is a key part of the internet, offering the power of quickly translating between the human language of FQDNs and the computer language of IP addresses.

Within Zero Trust Branch, you can use DNS policies to define rules that control DNS requests and responses to your Zero Trust Branch sites. To learn more about configuring site DNS policies, see [Configuring Site DNS Policies](https://help.zscaler.com/zero-trust-branch/configuring-site-dns-policies).

## Key Features and Benefits

Within Zero Trust Branch, site DNS policies provide the following benefits and enable you to:

- Monitor and apply policies to all DNS requests and responses, regardless of the protocol. This includes UDP and TCP.
- Define granular DNS filtering rules using a number of criteria, such as source IP address or subnet, FQDN, wildcard domains, or any domain.
- Enforce condition-based actions on DNS traffic, such as allowing or rejecting DNS queries, redirecting queries to specific DNS gateways, or overriding DNS responses.
- Redirect DNS queries to specific gateways, such as WAN DNS servers for site-related resolution or private DNS servers for internal and general internet resolution.
- Use a VLAN IP address as the source for DNS proxy requests. This ensures all outbound DNS queries use the same internal IP address, which is required for DNS servers accessed through a Routed Tunnel.
- Use default system policies to automatically route Zscaler and Airgap destinations to the WAN DNS gateway, ensuring the appliance can always resolve critical infrastructure domains.
- Configure a system default rule that allows and forwards all DNS traffic. This rule maintains the lowest precedence and can modify the default behavior by establishing a policy that matches any domain.

See image.

[Image: DNS Policies tab on the Sites page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/what-zero-trust-branch","lastmod":"2026-04-29T19:30Z","nid":"1508551"} -->
## What Is Zero Trust Branch?

- Source: https://help.zscaler.com/zero-trust-branch/what-zero-trust-branch
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > What Is Zero Trust Branch?
- Last modified: 2026-04-29T19:30Z
- Summary: Overview of Zero Trust Branch.

Zscaler's Zero Trust Branch revolutionizes how you connect, and it protects your branches, factories, and data centers by combining high-performance Software-Defined Wide Area Network (SD-WAN) capabilities with advanced device segmentation. This unified solution eliminates the complexity and overhead of traditional virtual private networks (VPNs) and overlay routing, enabling secure, direct-to-cloud access and robust east-west security through a single, integrated platform.

Zero Trust Branch leverages the power of the Zscaler Zero Trust Exchange (ZTE) and integrated Zero Trust Branch appliances deployed at your sites, establishing secure inbound and outbound networking without the need for additional firewall appliances. It directly terminates ISP connections and manages traffic forwarding across multiple links for maximum availability and resiliency. Cloud and branch traffic is securely forwarded directly to the ZTE, where Internet & SaaS (ZIA) and Private Access (ZPA) policies are applied based on traffic destination and user identity, ensuring comprehensive security inspection and granular access control for all communications, and eliminating lateral threat movement between branches and to the internet and cloud applications.

Zero Trust Branch extends the principles of zero trust into your internal network with agentless device segmentation. Our innovative "network-of-one" technology automatically discovers, classifies, and isolates each device, including Internet of Things (IoT), Operational Technology (OT), Internet of Medical Things (IoMT), headless devices, and legacy systems connected to your branch or factory network. This architecture eliminates lateral threat movement within the site without the cost and complexity associated with legacy segmentation approaches like east-west firewalls and network access control (NAC).

## Key Features and Benefits

Software as a Service (SaaS)-based Zero Trust Branch provides visibility and control over east-west traffic (intra- and inter-VLAN) as well as autonomous grouping and adaptive policy constructs for automated incident response.

With Zero Trust Branch, you have the following features and benefits:

- Enables zero trust everywhere for all users, devices, servers, and IoT/OT, regardless of location or cloud.
- Improves application performance by replacing complex site-to-site VPNs with a simple, direct-to-cloud architecture that enhances application performance and increases productivity.
- Minimizes the internet attack surface by placing private applications behind the ZTE, where they can’t be discovered or attacked from the internet.
- Prevents lateral threat movement by connecting directly to applications, not the network.
- Enables organizations to discover and classify shadow IoT devices with automatic device classification based on traffic profiles.
- Simplifies secure access to OT resources with clientless, browser-based access to Secure Shell (SSH), Remote Desktop Protocol (RDP), and virtual network computing (VNC) ports on OT assets.
- Enforces finely grained forwarding policies for internet and non-internet traffic using Internet & SaaS or Private Access.
- Introduces plug-and-play deployment with Zero Touch Provisioning (ZTP), which simplifies deployment and reduces time to integration.
- When enabled, the Zero Trust Branch gateway assumes the role of default gateway for VLANs and auto-provisions every endpoint with a /32 subnet mask through the intelligent Dynamic Host Configuration Protocol (DHCP) proxy or via automation for static IP address-enabled devices.
- Requires all devices, even those within the same VLAN, to communicate with each other via the Zero Trust Branch gateway for maximum security.

[Image: How Zero Trust Branch works.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/working-with-tags","lastmod":"2026-07-13T15:43Z","nid":"1509861"} -->
## Working with Tags

- Source: https://help.zscaler.com/zero-trust-branch/working-with-tags
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Zero Trust Device Segmentation > Asset Management > Working with Tags
- Last modified: 2026-07-13T15:43Z
- Summary: How to use tags in Zero Trust Branch.

Tagging provides additional information about an asset, allowing you to create policies that are not based on IP addresses. Tagging helps with resource categorization, simplified policy management, granular access control, traffic flow analysis, and operational efficiency. Zero Trust Branch supports tags for associating devices (guests, corporate, etc.) to one or more group or profile. Device tags can be used to search for and report on devices currently deployed within the environment.

For example, consider a business requirement to only allow Windows 7/Server 2008 machines to access an old Network Attached Storage (NAS). With a traditional firewall, you could allocate static IP addresses to the Windows 7/Server 2008 machines and create policies based on those IP addresses. While this works in a small environment, it is not a scalable solution in an enterprise environment.

With Zero Trust Branch, you can create a tag called `microsoft windows kernel 6.1` and apply it to all Windows 7/Server 2008 machines. You can then create a policy based on this tag as the source. Any changes in IP addresses do not require the admin to adjust the policy. To learn more, see [Managing Firewall Policies](https://help.zscaler.com/zero-trust-branch/managing-firewall-policies).

Zero Trust Branch provides the following tagging options:

- Auto tagging when devices are detected via auto-discovery
- Third-party integration (e.g., Armis)
- Manual tagging
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/zero-trust-branch-appliances-wall-and-rack-mount-instruction-manual","lastmod":"2026-07-28T15:42Z","nid":"1529460"} -->
## Zero Trust Branch Appliances Wall and Rack Mount Instruction Manual

- Source: https://help.zscaler.com/zero-trust-branch/zero-trust-branch-appliances-wall-and-rack-mount-instruction-manual
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Deploying Zero Trust Branch > Zero Trust Branch Appliances Wall and Rack Mount Instruction Manual
- Last modified: 2026-07-28T15:42Z
- Summary: Instructions for wall and rack mounting the Zero Trust Branch appliances.

After you receive the Zscaler Zero Trust Branch appliance, you can mount the Zero Trust Branch appliance as follows:

- The Zero Trust Branch ZT400: Rack posts using the rack mount kit. (Only available by request.)
- The Zero Trust Branch ZT600: Rack posts using the rack mount kit.
- The Zero Trust Branch ZT800:
  - Rack posts using the rack mount kit.
  - To the wall, using the wall mount kit. (Only available by request.)
- The Zero Trust Branch ZT8010: Rack posts using the ear brackets.

Mounting on a rack or wall optimizes your workspace and makes your Zero Trust Branch appliance more accessible for[installation and deployment](https://help.zscaler.com/zero-trust-branch/deploying-zero-trust-branch). It protects your appliance from any external damage and helps streamline the organization of your cables and power adapters. Additionally, rack or wall mounting helps ensure proper airflow, preventing overheating and dust accumulation caused by restricted ventilation. Proper airflow also maintains a cleaner environment, prolonging the lifespan of the appliance.

## Instruction Manuals

- Zero Trust Branch ZT400 Instruction Manual
- Zero Trust Branch ZT600 Instruction Manual
- Zero Trust Branch ZT800 Instruction Manual
- Zero Trust Branch ZT8010 Instruction Manual

- Package Contents
- Rack Mount the Appliance

The package includes the following items:

- 1x ZT400 unit
- 1x PSU
- 1x USB serial console cable
- 1x RJ45 cable
- 5x cable ties
- 3x A screws for attaching the bracket to the appliance
- 4x B screws for mounting the appliance on the rack

The rack mount kit is available only by request.

To rack mount your Zero Trust Branch ZT400:

1. Place the appliance in the middle of the rack mounting bracket with the appliance's rear panel facing away from the bracket. See image.
2. On the back of the mount, secure the appliance to the rack mounting bracket with three screws. See image.
3. Turn over the appliance and rack mounting bracket and place the power adapter next to the appliance, in the adapter holder section.
4. Secure the power adapter with two zip ties. Secure the cables with one zip tie. See image.
5. Attach the power adapter connector to the appliance's rear panel. See image.
6. Hold the appliance and lift carefully to insert the appliance into the rack. Zscaler recommends installing a shelf in the rack to support the appliance. Attach the brackets to the rail rack using rack mounting screws. See image.

- Package Contents
- Rack Mount the Appliance

The package includes the following items:

- 1x ZT600 unit
- 1x PSU
- 1x USB serial console cable
- 1x RJ45 cable
- 2x rack mounting brackets
- 6x A screws for attaching the brackets to the appliance
- 4x B screws for mounting the appliance on the rack

To rack mount the Zero Trust Branch ZT600:

1. Align one ear bracket to the screw holes on the side panel of the appliance and attach the bracket using three A screws. See image.
2. Secure the other ear bracket to the other side of the appliance. See image.
3. Place the adapter in the adapter holder. Then position the adapter bracket so that the holes align with the two B screws. See image.
4. Attach the adapter holder to the left side of the appliance and secure it with two A screws. See image.
5. Secure the adapter's cable onto the adapter holder. See image.
6. Zscaler recommends installing a shelf in the rack to support the appliance. Attach the brackets to the rail rack using rack mounting screws. Hold the appliance and lift carefully to insert the appliance into the rack. See image.

- Package Contents
- Wall Mount the Appliance
- Rack Mount the Appliance

The package includes the following items:

- 1x ZT800 unit
- 1x PSU
- 1x USB serial console cable
- 2x RJ45 cable
- 2x wall mount brackets
- 4x white wall plugs
- 4x A screws for attaching the brackets to the appliance
- 4x B screws for mounting the appliance on the rack

The wall mount kit is available only by request.

The rack mount kit contains the following items:

- 2x rack mounting brackets
- 6x A screws for attaching the brackets to the appliance
- 4x B screws for mounting the appliance on the rack

To wall mount the Zero Trust Branch ZT800:

1. Turn the appliance over and attach the wall brackets onto the bottom side of the appliance. Secure with the four provided screws. See image.
2. On the wall, measure the exact place where you want to mount the appliance. Drill four holes that align with the four mounting holes on the brackets. See image.
3. Insert the four white wall plugs into the holes you drilled into the wall.
4. Insert the long screws into the wall plugs. See image.
5. Align the four screw holes on the appliance's wall brackets with the four long screws installed on the wall. See image.
6. Secure the four screws in the bracket holes and push the appliance downward to lock the screws into position. See image.

To rack mount the Zero Trust Branch ZT800:

1. Align one ear bracket to the screw holes on the side panel of the appliance and secure the bracket using three A screws. See image.
2. Secure the other ear bracket to the other side of the appliance. See image.
3. Place the adapter in the adapter holder. Then position the adapter bracket so that the holes align with the two B screws. See image.
4. Attach the adapter holder to the side of the appliance and secure it with two A screws. See image.
5. Secure the adapter's cable onto the adapter holder. See image.
6. Hold the appliance and lift carefully to insert the appliance into the rack. Zscaler recommends installing a shelf in the rack to support the appliance. Attach the brackets to the rail rack using rack mounting screws. See image.

- ZT8010 Specifications
- Package Contents
- Rack Mount the Appliance

The following table provides environmental and power specifications for ZT8010:

| ZT8010 Specifications |  |
| --- | --- |
| **Environmental Specifications** |  |
| Operating Temperature | 32 to 104°F (0 to 40°C) |
| Non-Operating Temperature | -4 to 150°F (-20 to 65°C) |
| Relative Humidity | 5 to 90% (Operating); 5 to 95% (Non-operating) |
| Altitude | 5,000 feet (1,524 m) Operating; 50,000 feet (15,240 m) Non-operating |
| **Power Specifications** |  |
| Power Supply | Internal AC |
| Redundant Power Supply | 1 + 1 |
| Typical Power Consumption | Idle Mode: 107.8W Full Load Mode: 225.5W |
| Maximum Power Consumption | Redundant PSU: 300W Single PSU: 350W |
| Power Cord Rating | C14 10A |
| Input Range | Redundant PSU: AC 100–240V @ 50–60 Hz Single PSU: AC 100–240V @ 47–63Hz |
| Input Current | 5A (max) |
| Output Rating | 11.4 to 12.6V |

The package includes the following items:

- 1x ZT8010 unit
- 2x PSUs
- 2x RJ45 cable
- 1x USB serial console cable
- 4x SFP+ FTLX8574D3BCL
- 6x A screws for attaching the brackets to the appliance See image.
- 6x B screws for mounting the appliance to the rack See image.
- 2x ear brackets See image.

To rack mount the Zero Trust Branch ZT8010:

1. Align one of the ear brackets to screw holes on the side of the chassis and secure the brackets using 3x A screws. See image.
2. Secure the other ear bracket to the other side of the appliance.
3. Insert the ZT8010 appliance into the rack with the ear brackets fixed on the posts using the B screws. See image. The method and parts required for mounting depend on whether a shelf or rail kit is used.
4. Connect the AC power cords to the ZT8010 power supply unit. The appliance supports redundant power supply units (PSUs). Connect both cords to their respective power sources. A continuous beep indicates that either the second power source is unplugged or faulty. To silence the beep, press the round button located near or between the power supplies, or connect the second power source.
5. Connect the WAN cable to the designated port of the ZT8010 appliance. The ZT8010 has several ports, including 10x 1GbE RJ45 ports and 8x 10G SFP+ ports. To learn more, see [Zero Trust Branch Physical Port Mapping](https://help.zscaler.com/zero-trust-branch/zero-trust-branch-physical-port-mapping#ZT8010). See image.

[Image: Placing the appliance in the rack mounting bracket]

[Image: Securing the appliance to the rack mount bracket using three screws]

[Image: Securing power adapter and cables with zip ties]

[Image: Attaching the power adapter connector to the appliance rear panel]

[Image: Inserting the appliance in the rack]

[Image: Attaching ear bracket to side panel of appliance]

[Image: Attaching second ear bracket to side panel of appliance]

[Image: Placing the adapter in the adapter holder and positioning the adapter bracket]

[Image: Attaching adapter holder to left side of appliance]

[Image: Securing the adapter cable to the adapter holder]

[Image: Installing the appliance to the rack]

[Image: Attaching wall brackets to the appliance.]

[Image: Drilling four holes aligned with the two mounting brackets]

[Image: Inserting long screws into wall plugs]

[Image: Aligning four screw holes with four long screws on wall]

[Image: Locking four bracket screws into position]

[Image: Securing ear bracket to the side panel of the appliance]

[Image: Securing second ear bracket to the other side of appliance]

[Image: Positioning adapter and adapter holder]

[Image: Attaching adapter holder to appliance]

[Image: Securing adapter cable to adapter holder]

[Image: Installing the appliance on the rack]

[Image: Set of A screws for ZT8010]

[Image: Set of B screws for ZT8010]

[Image: Ear brackets for ZT8010]

[Image: Ear bracket mounted to ZT8010]

[Image: ZT8010 mounted to a rack]

[Image: Illustration of ports in ZT8010]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zero-trust-branch/zero-trust-branch-physical-port-mapping","lastmod":"2026-07-22T09:37Z","nid":"1532276"} -->
## Zero Trust Branch Physical Port Mapping

- Source: https://help.zscaler.com/zero-trust-branch/zero-trust-branch-physical-port-mapping
- Product: Zero Trust Branch
- Path: Zero Trust Branch Help > Deploying Zero Trust Branch > Zero Trust Branch Physical Port Mapping
- Last modified: 2026-07-22T09:37Z
- Summary: A description of the physical ports on Zscaler Zero Trust Branch devices, and their interfaces, port types, and roles

This article depicts the physical ports on Zero Trust Branch appliances and identifies their interface names, port types, and roles.

For appliance specifications, refer to the [Zero Trust Branch Data Sheet](https://www.zscaler.com/resources/data-sheets/zscaler-zero-trust-branch.pdf).

- Zscaler ZT400
- Zscaler ZT600
- Zscaler ZT800
- Zscaler ZT8010

This section describes the physical port mapping on the ZT400 appliance.

[Image: ZT400 appliance physical port diagram that identifies the console port on the back of the appliance] [Image: ZT400 appliance physical port diagram that identifies four interface ports on the front of the appliance]

| Port | Interface | Port Type | Role |
| --- | --- | --- | --- |
| C | Console | RJ45 | Serial console |
| 1 | GE1/enp4s0 | 1GbE RJ45 | Management |
| 2 | GE2/enp5s0 | 1GbE RJ45 | LAN or WAN |
| 3 | GE3/enp6s0 | 1GbE RJ45 | WAN |
| 4 | GE4/enp7s0 | 1GbE RJ45 | LAN or high availability (HA) (when appliances are running in a cluster) |

This section describes the physical port mapping on the ZT600 appliance.

[Image: ZT600 appliance physical port diagram that identifies the console port and six interface ports]

| Port | Interface | Port Type | Role |
| --- | --- | --- | --- |
| C | Console | RJ45 | Serial console |
| 1 | GE1/enp3s0 | 1GbE RJ45 | Management |
| 2 | GE2/enp2s0 | 1GbE RJ45 | LAN |
| 3 | GE3/eno1 | 1GbE RJ45 | LAN |
| 4 | GE4/eno2 | 1GbE RJ45 | LAN or high availability (HA) (when appliances are running in a cluster) |
| 5 | GE5/eno3 | 1GbE RJ45 | WAN |
| 6 | GE6/eno4 | 1GbE RJ45 | WAN |

This section describes the physical port mapping on the ZT800 appliance.

[Image: ZT800 appliance physical port diagram that identifies the console port and eight interface ports]

| Port | Interface | Port Type | Role |
| --- | --- | --- | --- |
| C | Console | RJ45 | Serial console |
| 1 | GE1/enp2s0f0 | 1G SFP | LAN |
| 2 | GE2/enp2s0f1 | 1G SFP | WAN |
| 3 | GE3/enp2s0f2 | 1GbE RJ45 | Management |
| 4 | GE4/enp2s0f3 | 1GbE RJ45 | LAN or high availability (HA) (when appliances are running in a cluster) |
| 5 | GE5/enp8s0f0 | 1GbE RJ45 | LAN |
| 6 | GE6/enp8s0f1 | 1GbE RJ45 | LAN |
| 7 | GE7/enp10s0f0 | 1GbE RJ45 | WAN |
| 8 | GE8/enp10s0f1 | 1GbE RJ45 | WAN |

This section describes the physical port mapping on the ZT8010 appliance.

[Image: ZT8010 appliance physical port diagram that identifies the console port and 18 interface ports]

| Port | Interface | Port Type | Role |
| --- | --- | --- | --- |
| C | Console | RJ45 | Serial console |
| 1 | GE1/eno1 | 1GbE RJ45 | Management |
| 2 | GE2/enp1s0 | 1GbE RJ45 | WAN |
| 3 | GE3/ens84f3 | 1GbE RJ45 | LAN |
| 4 | GE4/ens84f2 | 1GbE RJ45 | LAN or high availability (HA) (when appliances are running in a cluster) |
| 5 | GE5/ens84f1 | 1GbE RJ45 | LAN |
| 6 | GE6/ens84f0 | 1GbE RJ45 | LAN |
| 7 | XE7/eno4 | 10G SFP+ | WAN |
| 8 | XE8/eno5 | 10G SFP+ | WAN |
| 9 | XE9/eno2 | 10G SFP+ | WAN |
| 10 | XE10/eno3 | 10G SFP+ | WAN |
| 11 | GE11/ens83f0 | 1GbE RJ45 | LAN |
| 12 | GE12/ens83f1 | 1GbE RJ45 | LAN |
| 13 | GE13/ens83f2 | 1GbE RJ45 | LAN |
| 14 | GE14/ens83f3 | 1GbE RJ45 | LAN |
| 15 | XE15/ens5f3 | 10G SFP+ | LAN |
| 16 | XE16/ens5f2 | 10G SFP+ | LAN |
| 17 | XE17/ens5f1 | 10G SFP+ | LAN |
| 18 | XE18/ens5f0 | 10G SFP+ | LAN |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/about-network-events","lastmod":"2026-03-30T21:06Z","nid":"1518096"} -->
## About Network Events

- Source: https://help.zscaler.com/zscaler-cellular/about-network-events
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > Network Events and Logins Monitoring > About Network Events
- Last modified: 2026-03-30T21:06Z
- Summary: Information on the Network Events page.

The Network Events page offers a centralized interface for monitoring and analyzing network activity across all Zscaler SIMs provisioned to your organization. It provides detailed insights into network events and authorization events for each SIM. This comprehensive logging enables you to troubleshoot efficiently and ensure seamless connectivity.

The Network Events page provides the following benefits and enables you to:

- Track SIM transitions between online and offline states to ensure consistent network availability and performance.
- Access detailed logs of authorization events and connectivity changes to quickly identify and resolve issues.
- Gain centralized insights into network activity across all provisioned SIMs, improving oversight and management efficiency.

## About the Network Events Page

On the Network Events page (Infrastructure > Connectors > Cellular> Network Events), you can do the following:

1. [Filter network events based on different parameters](https://help.zscaler.com/unified/using-tables).
2. Select a default date range (**Today**, **Yesterday**, **Last 7 days**, **Last 30 Days**, **This Month**, or **Last Month**) or choose a custom rangeby selecting start and end dates to display network events for the specific period.
3. Refresh the page to fetch and show the latest data. This action does not remove the applied filters.
4. [Show or hide columns.](https://help.zscaler.com/unified/using-tables#hide)
5. View a list of records of network events for all SIMs provisioned to your organization. For each record, you can see:
  - **ICCID**: The Integrated Circuit Card Identifier is a globally unique identifier assigned to each SIM card. It is used to track and manage the SIM within the network infrastructure. Click to view [detailed information](https://help.zscaler.com/zscaler-cellular/viewing-network-event-details) for each network event.
  - **Date**: The date and time when the network event occurred.
  - **EID**: The Embedded Identity Document is a unique identifier for eSIMs.
  - **IMSI**: The International Mobile Subscriber Identity is a unique number assigned to each mobile subscriber. It is used to identify the user on the mobile network.
  - **Country**: The geographical location (country) where the event was recorded.
  - **Operator**: The network operator handling the connectivity.
  - **Category**: The type of event (session start, session end, online/offline status, or authorization).
6. Limit the number of records displayed per page. You can choose to display 10, 20, or 50 records per page.
7. Go to a specific page using the page number or move to the next or previous page.

[Image: A table with columns for Date, ICCID, EID, etc. along with annotations for highlighting available options]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/about-sim-location-groups","lastmod":"2026-04-03T21:06Z","nid":"1534048"} -->
## About SIM Location Groups

- Source: https://help.zscaler.com/zscaler-cellular/about-sim-location-groups
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > Anomaly Detection > About SIM Location Groups
- Last modified: 2026-04-03T21:06Z
- Summary: Explaining the Zscaler Cellular Anomaly detection SIM Location Groups.

SIM Location Groups help you to manage and monitor SIM-enabled devices based on their geographic zone. You can enforce geofencing policies and detect anomalies in device movement. You can create groups based on the number of devices that need to be tracked within a specific geographic zone.

SIM Location Groups provide the following benefits and enable you to:

- Define geographic zones that can be linked to SIM-enabled IoT devices.
- Use in anomaly detection policies to monitor device movement and detect deviations from assigned zones.
- Define geographical areas and set device limits per group.
- Identify unauthorized activity, enforce security controls, and maintain compliance.

## About the SIM Location Groups Page

On the SIM Location Groups page (Infrastructure > Connectors > Cellular> SIM Location Groups), you can do the following:

1. [Filter the SIM Location Groups page based on the Group Name parameter](https://help.zscaler.com/unified/using-tables).
2. [Add a new group](https://help.zscaler.com/zscaler-cellular/adding-sim-location-group).
3. Refresh the page to fetch and show the latest data. This action does not remove the applied filters.
4. View the group details. For each group, you can see:
  - **Group Name**: Name of the SIM Location Group. Click to view: See image.
    - Tracking details
    - Assigned Policies
  - **Tracked Devices:**Number of SIM devices being tracked within the group.
  - **Action:**Option to delete a SIM Location Group.

[Image: View SIM Location Groups page]

- A map view showing the geofencing area along with distance radius in kilometers and miles.
- The tracking status of devices is defined relative to a boundary:
  - Inside & Tracked: SIMs are located within the area and actively monitored.
  - Inside & Untracked: SIMs are present but not tracked.
  - Outside & Tracked: SIMs are outside the area yet still under active monitoring.

The policy assigned to the group, including anomaly name, type, enabled status, and current running status.

[Image: View Group details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/about-sims","lastmod":"2026-04-21T21:06Z","nid":"1519081"} -->
## About SIMs

- Source: https://help.zscaler.com/zscaler-cellular/about-sims
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > SIMs > About SIMs
- Last modified: 2026-04-21T21:06Z
- Summary: Information about the SIMs page in the Zscaler Cellular Edge Admin Portal.

A Zscaler SIM—both physical SIMs and eSIMs—serves as the gateway for a cellular-connected device to access the Zero Trust Exchange (ZTE). It is a data-only SIM that integrates directly with the ZTE, providing seamless security for IoT devices such as vending machines, EV chargers, machinery, and tablets/kiosks where agent-based solutions are not feasible. You can view essential details and statuses, and manage Zscaler SIMs provisioned to your organization, in the Zscaler Admin Console.

Zscaler SIMs provide the following benefits and enable you to:

- View critical information for each device, such as ICCID, IMEI, IP address, and data usage, and track its status and connectivity.
- Identify high-usage or offline devices. Use this information to address issues proactively and ensure seamless connectivity or update the SIM status.

## About the SIMs Page

On the SIMs page (Infrastructure > Connectors > Cellular > SIMs), you can do the following:

1. View the total data usage across all SIMs in the current view. If filters are applied, the Total Usage field shows only the data across the SIMs that match the applied filters.
2. Update [SIM Status](https://help.zscaler.com/zscaler-cellular/changing-status-zscaler-sims) or [IMEI](https://help.zscaler.com/zscaler-cellular/changing-imei-association-zscaler-sims) for multiple SIMs. This option is displayed only when a SIM is selected.
3. Download the list of all SIMs and their details as a CSV file for the current view. If filters are applied, the downloaded file contains only the details of the SIMs that are shown for the selected filters.
4. Refresh the page to fetch and show the latest data. This action does not remove the applied filters.
5. [Show or hide columns.](https://help.zscaler.com/unified/using-tables#hide)
6. [Filter the SIMs page based on different parameters](https://help.zscaler.com/unified/using-tables).
7. View the list of all Zscaler SIMs provisioned for your organization. For each SIM, you can see:
  - **ICCID**: The Integrated Circuit Card Identifier is a globally unique identifier assigned to each SIM. It is used to track and manage the SIM within the network infrastructure.
  - **IMEI**:The International Mobile Equipment Identity is a unique identifier for the mobile device associated with the SIM. It helps with identifying and managing the devices in the network.
  - **IMSI**: The International Mobile Subscriber Identity is a unique number assigned to each mobile subscriber. It is used to identify the user on the mobile network.
  - **MSISDN**: The Mobile Station International Subscriber Directory Number is the associated mobile number of the SIM, if applicable.
  - **IP Address**: The IP address assigned to the device using the SIM, allowing it to connect and communicate over the internet.
  - **Form Factor**: The type of SIM, indicating whether it is a physical SIM or an eSIM.
  - **Device Manufacturer**: The name of the company that produces the device associated with the SIM.
  - **Device Model**: The full official identifier of the type of device associated with the SIM.
  - **Device Type**: The category of the device associated with the SIM (e.g., router, modem, and IoT gateway).
  - **Operating System**: The operating system running on the device associated with the SIM.
  - **Usage**: The amount of data consumed during the session (the duration between the start of the network connection and its termination), displayed in MB or GB.
  - **Status**: The operational state of the SIM, either **Active**(available for use) or **Inactive**(not in use).
  - **Country**: The country where the SIM is located.
  - **Tags**: The tags associated with the SIM. You can click a specific tag to filter the SIMs page and display only the SIMs that have the tag associated with them.
  - **Connection**: The connectivity state of the SIM.
    - **Online**:The SIM isconnected to the network.
    - **Offline**: The SIM is disconnected from the network.
    - **Inventory**: The SIM is ready for assignment, or if already assigned, awaiting profile registration on a device. This option applies only to eSIMs. If an eSIM was originally assigned to a user and then released back to the inventory, the connection state might not be updated on the SIMs page. You can refresh the connection state by viewing the eSIM details.
8. [View detailed information for each SIM](https://help.zscaler.com/zscaler-cellular/viewing-sim-details).
9. [Edit status](https://help.zscaler.com/zscaler-cellular/changing-status-zscaler-sims), [update the IMEI](https://help.zscaler.com/zscaler-cellular/changing-imei-association-zscaler-sims), or [update tags](https://help.zscaler.com/zscaler-cellular/managing-tags-zscaler-sims) for a particular SIM.
10. Limit the number of records displayed per page. You can choose to display 10, 20, or 50 entries per page.
11. Go to a specific page or move to the next or previous page.

[Image: SIMs page with annotations showing various options]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/about-zscaler-cellular-audit-logs","lastmod":"2026-08-03T21:06Z","nid":"1539636"} -->
## About Zscaler Cellular Audit Logs

- Source: https://help.zscaler.com/zscaler-cellular/about-zscaler-cellular-audit-logs
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > Audit Logs > About Zscaler Cellular Audit Logs
- Last modified: 2026-08-03T21:06Z
- Summary: Information regarding Audit Logs for Zscaler Cellular.

Zscaler Cellular audit logs allow you to view a record of all administrative actions performed in the Zscaler Cellular configurations. It helps track configuration changes, identify who performed an action, and understand when the action occurred.

Zscaler Cellular audit logs provide the following benefits and enable you to:

- Gain visibility into all administrative actions performed in the Zscaler Cellular environment
- Track who performed an action and when it occurred
- Quickly identify and troubleshoot configuration changes
- Maintain an auditable record of system activity
- Filter and analyze logs to investigate specific events efficiently

## About the Zscaler Cellular Audit Logs Page

On the Zscaler Cellular Audit Logs page (Administration > Admin Management > Audit Logs > Cellular), you can do the following:

1. Apply time filters to view log entries specific to that period.
2. Show or hide filtering options
3. Refresh the table data
4. Add or remove columns from the table
5. Apply filters based on specific parameters. For each parameter, you can specify a value to filter the table data.
6. View audit log entries based on the applied filters. For each log entry, you can view:
  - **Timestamp**: The date and time when the action occurred.
  - **Principal ID**: The user or system that performed the action.
  - **Action**: The type of operation performed, such as Create or Update.
  - **Resource Type**: The type of resource affected (for example, Tag, SIM to Tag Mapping, or Anomaly Policy).
  - **Resource Name**: The name of the affected resource.
  - **Resource ID**: The unique identifier of the resource.
  - **View Changes**: View details of the configuration changes. See image.
7. Limit the number of records displayed per page. You can choose to display 10, 25, 50, or 100 entries per page.
8. Go to a specific page or move to the next or previous page.

[Image: The Zscaler Cellular Audit Logs page]

[Image: Viewing audit log changes]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/adding-sim-location-group","lastmod":"2026-04-03T21:06Z","nid":"1534049"} -->
## Adding a SIM Location Group

- Source: https://help.zscaler.com/zscaler-cellular/adding-sim-location-group
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > Anomaly Detection > Adding a SIM Location Group
- Last modified: 2026-04-03T21:06Z
- Summary: Explaining how to add a SIM Location Group.

For Zscaler Cellular anomaly detection, creating a [SIM Location Group](https://help.zscaler.com/zscaler-cellular/about-sim-location-groups) serves as the initial step in enabling anomaly detection. Within each group, you can define the geofencing area and specify the number of tracked devices according to your requirements. After the group is created, it can be added to a policy. When the policy is enabled, the system monitors the movement of tracked devices and raises a violation if any device crosses the defined geofence area.

To learn more, see [Understanding Anomaly Detection](https://help.zscaler.com/zscaler-cellular/understanding-anomaly-detection).

## Adding a SIM Location Group

To add a SIM Location Group:

1. In the Zscaler Admin Console, go to **Infrastructure**> **Connectors**> **Cellular**>**SIM Location Groups**.
2. Click **Add New**.
3. In the Location Group drawer: See image.
  1. **ICCID**: From the drop-down menu, select the Integrated Circuit Card Identifier (ICCID) associated with a SIM card you want to add to the Location Group.
  2. **Tags**: From the drop-down menu, select a predefined tag. Tags are labels assigned to a SIM or group of SIMs to facilitate identification and filtering. ICCID and tags are optional. Use them if you need to include specific devices and already have their details.
  3. Create a geographical zone on the map using the mouse cursor. To reset the map to the default global view, click **Reset View**.
  4. **Group Name:** Enter a name for the group.
4. Click **Add Group.** The group is created and displayed on the [SIM Location Groups](https://help.zscaler.com/zscaler-cellular/about-sim-location-groups) page.

[Image: Adding a SIM Location Group.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/changing-imei-association-zscaler-sims","lastmod":"2026-04-03T21:06Z","nid":"1524936"} -->
## Changing the IMEI Association for Zscaler SIMs

- Source: https://help.zscaler.com/zscaler-cellular/changing-imei-association-zscaler-sims
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > SIMs > Changing the IMEI Association for Zscaler SIMs
- Last modified: 2026-04-03T21:06Z
- Summary: How to update the IMEI associated with a Zscaler SIM.

You can modify the IMEI associated with a Zscaler SIM provisioned to your organization from the SIMs page.

To change the IMEI association for a Zscaler SIM:

1. In the Zscaler Admin Console, go to **Infrastructure**> **Connectors**> **Cellular**> **SIMs**. The **SIMs**page appears.
2. On the **SIMs**page, you can:
  - Modify the IMEI association for a single SIM
  - Modify the IMEI association for multiple SIMs

1. Locate the SIM card for which you want to change the IMEI association.
2. In the **Manage**column, click the **Lock**icon. See image. Alternatively, you can go to the SIM details page by clicking the **ICCID**link and select the **Update IMEI Lock/Unlock**option from the **Update**drop-down menu in the top-right corner. See image. The **Update IMEI Lock/Unlock**window appears.
3. In the **Update IMEI Lock/Unlock**window: See image.
  - If you want to create a new IMEI association or update an existing association, enter the new IMEI value in the **IMEI Lock Value** field. You can lock a SIM card to a particular IMEI. To do this, enable **Lock**while creating or updating an IMEI association.; Updating the lock status without changing the IMEI value is allowed for existing associations.
  - If you want to unlock an IMEI from a SIM card, disable **Lock**.
4. Click **Save**. The IMEI association for the SIM card is updated. The lock is not immediate. After initiation, the SIM enters a pending lock state and might take approximately 15 to 30 minutes to transition into the locked state.

1. Locate and select the SIM cards whose IMEI associations you want to change.
2. Click **Update**and select **Update IMEI Lock/Unlock**. See image. The **Update IMEI Lock/Unlock**window appears.
3. In the **Update IMEI Lock/Unlock**window: See image.
  - If you want to create new IMEI associations or update existing associations, enter the new IMEI values in the **IMEI Lock Value** field for the required SIM cards. You can lock a SIM card to a particular IMEI. To do this, enable **Lock**while creating or updating IMEI associations.; Updating the lock status without changing the IMEI value is allowed for existing associations.
  - If you want to unlock IMEI from SIM cards, disable **Lock**.
4. Click **Save**. The IMEI associations for the selected SIM cards are updated. The lock is not immediate. After initiation, the SIM enters a pending lock state and might take approximately 15 to 30 minutes to transition into the locked state.

[Image: Option to update IMEI for a SIM card highlighted]

[Image: The SIM Details page, showing widgets for basic information, current data usage, and a usage graph. The Update drop-down menu shows options for Update Status, Update IMEI, and Update Tags. The Update IMEI option is highlighted.]

[Image: A toggle with Lock selected, a Save button, and a table with columns for ICCID, IMEI, Data Authorize, and Data Authorize Value]

[Image: Option to update IMEI from the Update drop-down menu when multiple SIM cards are selected]

[Image: A toggle with Lock selected, a Save button, and a table with columns for ICCID, IMEI, Data Authorize, and Data Authorize Value]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/changing-status-zscaler-sims","lastmod":"2026-04-03T21:06Z","nid":"1519086"} -->
## Changing the Status of Zscaler SIMs

- Source: https://help.zscaler.com/zscaler-cellular/changing-status-zscaler-sims
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > SIMs > Changing the Status of Zscaler SIMs
- Last modified: 2026-04-03T21:06Z
- Summary: How to modify the status of Zscaler SIMs.

You can modify the status of the Zscaler SIMs—both physical SIMs and eSIMs—provisioned to your organization from the SIMs page.

To change the status of Zscaler SIMs:

1. In the Zscaler Admin Console, go to **Infrastructure**> **Connectors**> **Cellular**> **SIMs**. The **SIMs**page appears.
2. On the **SIMs**page, you can:
  - Modify the status of a single SIM
  - Modify the status of multiple SIMs

1. Locate and select the SIM card whose status you want to modify.
2. In the **Manage**column, click the **Edit**icon. See image. Alternatively, you can go to the **SIM** **Details**page by clicking the **ICCID**link and select **Update Status**from the **Update**drop-down menu in the top-right corner. See image. The **Update Status** window appears.
3. In the **Update Status** window: The SIMs are activated or deactivated based on your chosen option.
  1. Select a status you want to assign to the SIMs, either **Active**or **Inactive**. For example, if you want to inactivate your active SIMS, select **Inactive**. If you want to activate your inactive SIMs, select **Active**. See image.
  2. Click **Save**.

1. Locate and select the SIM cards whose status you want to modify. See image. The SIM cards you select must be of the same status.
2. Click **Update**and select **Update Status**. See image. The **Update Status** window appears.
3. In the **Update Status** window: The SIMs are activated or deactivated based on your chosen option.
  1. Select a status you want to assign to the SIMs, either **Active**or **Inactive**. For example, if you want to inactivate your active SIMS, select **Inactive**. If you want to activate your inactive SIMs, select **Active**. See image.
  2. Click **Save**.

[Image: A table with columns for ICCID, IMEI, IMSI, etc. with the edit icon highlighted]

[Image: The SIM Details page, with the Update drop-down menu showing the Update Status option]

[Image: Radio buttons for Active and Inactive statuses, with Save and Cancel buttons below]

[Image: Selecting multiple SIMs on the SIMs page]

[Image: Choosing the option to update SIM status from the Update drop-down menu when multiple SIM cards are selected]

[Image: Updating status for multiple SIMs]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/configuring-and-updating-credentials-cellular-edge-deployment","lastmod":"2026-04-03T21:06Z","nid":"1519071"} -->
## Configuring and Updating Credentials for Zscaler Cellular Service Deployment

- Source: https://help.zscaler.com/zscaler-cellular/configuring-and-updating-credentials-cellular-edge-deployment
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > Dashboard, Deployment, and Configuration > Configuring and Updating Credentials for Zscaler Cellular Service Deployment
- Last modified: 2026-04-03T21:06Z
- Summary: How to configure or update Cloud & Branch Connector credentials to deploy Cellular Service.

Zscaler requires your organization's Cloud & Branch Connector credentials to initiate deployment of Zscaler Cellular Service across regions. You must set up Cloud & Branch Connector credentials to allow Zscaler to deploy Cellular Services.

To configure Cloud & Branch Connector credentials:

If you change the super admin credentials of the Cloud & Branch Connector Admin Portal, then you must update the credentials on the Configuration page in the Zscaler Admin Console. Failure to update credentials in the Zscaler Admin Console can lead to service disruption, affecting connectivity of your cellular devices that use Zscaler SIMs.

1. In the Zscaler Admin Console, go to **Administration**> **API Configuration**> **Legacy API** > **Cloud & Branch Connector API**. See image.
2. Copy the API Key and save it for future use.
3. Go to **Infrastructure**> **Connectors**> **Cellular**> **Configuration**.
4. On the **Configuration**page: See image.
  1. **Branch Connector Username**: Enter the username of the super admin for the Cloud & Branch Connector Admin Portal.
  2. **Branch Connector Password**: Enter the password of the super admin for the Cloud & Branch Connector Admin Portal.
  3. **Branch Connector API Key**: Enter the API key copied in the previous step.
5. Click **Save**.

[Image: Obtaining API key from the Cloud & Branch Connector Admin Portal]

[Image: Updating the Branch Connector credentials]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/configuring-geofence-anomaly-detection-policies","lastmod":"2026-05-04T07:06Z","nid":"1534050"} -->
## Configuring Geofence Anomaly Detection Policies

- Source: https://help.zscaler.com/zscaler-cellular/configuring-geofence-anomaly-detection-policies
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > Anomaly Detection > Configuring Geofence Anomaly Detection Policies
- Last modified: 2026-05-04T07:06Z
- Summary: Explaining how to configure a new network detection anomaly policy for Zscaler Cellular Anomaly Detection.

In Zscaler Cellular Anomaly Detection, you can configure the Geofence Anomaly Detection Policy. This policy allows administrators to define and manage rules that detect anomalies based on SIM Location Groups. It is designed to monitor device activity within a specified geofence area, and when a device moves outside its assigned boundary, the system triggers an anomaly.

To learn more, see [Understanding Anomaly Detection.](https://help.zscaler.com/zscaler-cellular/understanding-anomaly-detection)

## Prerequisites

Before configuring an anomaly detection policy, create [SIM Location Groups](https://help.zscaler.com/zscaler-cellular/about-sim-location-groups).

## Configuring a Geofence Anomaly Detection Policy

To add a new anomaly detection policy:

1. In the Zscaler Admin Console, go to **Infrastructure**> **Connectors**> **Cellular**> **Anomaly Dashboard**.
2. Click **Add Policy**.
3. In the policy drawer: Geofencing is the only policy type currently available. See image.
  1. Select the checkbox next to the **SIM Group Name** and click the **SIM Group Name** to add it to your policy.
  2. In the **Policy Details** tab:
    - **Rule Name:**Enter the name of the rule.
    - **Add Description** (Optional): Enter a description of the rule.
    - **Rule Status**: Enable or disable the status.
  3. In the **Group Details** tab, you can see a map view with the geofencing details, device status, and assigned policy details, including anomaly type and current status.
4. Click **Add Policy**. You are redirected to the [Anomaly Dashboard](https://help.zscaler.com/zscaler-cellular/viewing-anomaly-detection-dashboard). In the **Enabled** column, click the **Enable** toggle to run the policy.

[Image: Geofence Anomaly Detection Policy Configuration page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/deploying-new-cellular-edges","lastmod":"2026-04-03T21:06Z","nid":"1518161"} -->
## Deploying New Cellular Edges

- Source: https://help.zscaler.com/zscaler-cellular/deploying-new-cellular-edges
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > Dashboard, Deployment, and Configuration > Deploying New Cellular Edges
- Last modified: 2026-04-03T21:06Z
- Summary: How to request deployment of additional Cellular Edges.

Deploying additional Cellular Edges ensures that your devices have seamless and secure connectivity to the Zscaler cloud in the designated regions where you need additional coverage or capacity. Zscaler Cellular offers coverage expansion in three regions: AMER (North and South America), EMEA (Europe, Middle East, and Africa), and APAC (Asia-Pacific). Deploying more Cellular Edges allows you to expand the reach of your Zscaler Cellular services, improve performance by reducing latency, and ensure reliable network access for IoT devices and other connected systems. On the Deployment Regions Page, you can identify regions requiring deployment and submit requests for provisioning new Cellular Edges.

Before requesting new Cellular Edges, ensure that you have a subscription for the region where you want to deploy them.

To request deployment of new Cellular Edges:

1. In the Zscaler Admin Console, go to **Infrastructure**> **Connectors**> **Cellular**> **Deployment Regions**. The**Deployment Regions**page appears.
2. Locate the **Choose region** section in the lower-left corner of the page, and select the region where you want new Cellular Edges. See image.
3. Click **Request Deployment**. The request to deploy new Cellular Edges is submitted to Zscaler.

[Image: Deployment Regions page with the option to select a region highlighted]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/managing-assignment-and-activation-esims","lastmod":"2026-04-20T21:06Z","nid":"1532654"} -->
## Assigning and Activating eSIMs

- Source: https://help.zscaler.com/zscaler-cellular/managing-assignment-and-activation-esims
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > SIMs > Assigning and Activating eSIMs
- Last modified: 2026-04-20T21:06Z
- Summary: How to manage assignment and activation for eSIMs from the Zscaler Admin Console.

You can manage eSIMs provisioned for your organization in the Zscaler Admin Console. After provisioning, the eSIMs are listed on the SIMs page. You can view the SIM details and assign or activate it.

This feature is in limited availability. To access this feature, contact your Zscaler Account team.

## Assigning an eSIM

After eSIMs are provisioned to your organization, you need to assign them to users.

To assign the eSIM to a user:

1. Go to **Infrastructure**> **Connectors**> **Cellular**> **SIMs**. The **SIMs**page appears.
2. Locate the eSIM you want to assign to a user and click the link in the **ICCID**column. See image. The **SIM details** page appears. See image.
3. On the **SIM details** page, go to the **eSIM Info** widget and do the following: See image. The eSIM is recorded as assigned to the specified user. You can activate or modify the assignment, if required.
  1. **Assign Recipient**: Enter the name or email address of the user to whom you want to assign the eSIM.
  2. Click **Assign**.

If you want to modify the assigned user, click **Reassign**, enter the name or email address of the new user, and click **Update**. The eSIM installed on the previous user's device must be manually uninstalled before reassigning it to the new user. To learn more about uninstalling eSIMs, refer to the official user manual for the device.

## Activating an eSIM

After assigning an eSIM to a user, you need to activate the eSIM.

To activate an eSIM:

1. Go to **Infrastructure**> **Connectors**> **Cellular**> **SIMs**. The **SIMs**page appears.
2. Locate the eSIM that you want to activate and click the link in the **ICCID**column. The **SIM details**page appears.
3. On the **SIM details**page, go to the **eSIM Info** widget and click **View QR Details**. See image.
4. In the **SIM QR Info** window, scan the QR code from the device that needs the eSIM to be activated. Alternatively, copy the activation code and enter it manually into the device. See image. For detailed steps on how to scan the QR code or enter the activation code manually on the device, refer to the official device manual.

[Image: SIM page with the link to view the details of an eSIM]

[Image: SIM details page of an eSIM]

[Image: eSIM Info widget showing the option to assign a user]

[Image: eSIM Info widget showing the option to view QR details for activating the eSIM]

[Image: eSIM activation with QR code or activation code]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/managing-tags-zscaler-sims","lastmod":"2026-04-03T21:06Z","nid":"1524941"} -->
## Managing Tags for Zscaler SIMs

- Source: https://help.zscaler.com/zscaler-cellular/managing-tags-zscaler-sims
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > SIMs > Managing Tags for Zscaler SIMs
- Last modified: 2026-04-03T21:06Z
- Summary: How to create and manage tags for Zscaler SIMs.

You can add tags to your Zscaler SIMs to group and classify them based on your business requirements. You can use tags to view all SIMs that have the tag associated with them by clicking that tag on the [SIMs page](https://help.zscaler.com/zscaler-cellular/about-sims) or [SIM details page](https://help.zscaler.com/zscaler-cellular/viewing-sim-details).

To add tags to Zscaler SIMs:

1. In the Zscaler Admin Console, go to **Infrastructure**> **Connectors**> **Cellular**> **SIMs**.
  1. In the table, locate the SIM card to which you want to add tags and click the **Update Tags** icon in the **Manage**column. See image. Alternatively, you can go to the **SIM details**page by clicking the **ICCID**link and select the **Update Tags** option from the **Update**drop-down menu in the top-right corner. See image. The **Update Tags**window appears.
2. In the **Update Tags**window:
  - If you want to associate an existing tag with the SIM card, enter some or all of the characters of a tag name in the **Tags**field and choose an existing tag from the drop-down menu. Repeat this process to add more tags as required. See image.
  - If you want to associate a new tag with the SIM cards, enter the name for the new tag in the **Tags**field, and click **Add New Tag**. See image.
3. Click **Update**. The selected tags are associated with the SIM card.

[Image: A table wih columns for ICCID, IMEI, IMSI, etc. with the option to update tags highlighted]

[Image: The SIM details page with the Update drop-down menu showing the option to update tags.]

[Image: Adding an existing tag]

[Image: Adding a new tag]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/release-upgrade-summary-2025","lastmod":"2026-01-28T01:26Z","nid":"1515641"} -->
## Release Upgrade Summary (2025)

- Source: https://help.zscaler.com/zscaler-cellular/release-upgrade-summary-2025
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > Release Notes > Release Upgrade Summary (2025)
- Last modified: 2026-01-28T01:26Z
- Summary: Zscaler Cellular Release Upgrade Summary for service updates deployed in 2025.

This article provides a summary of all new features and enhancements for Zscaler Cellular.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/release-upgrade-summary-2026","lastmod":"2026-07-22T09:55Z","nid":"1534297"} -->
## Release Upgrade Summary (2026)

- Source: https://help.zscaler.com/zscaler-cellular/release-upgrade-summary-2026
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > Release Notes > Release Upgrade Summary (2026)
- Last modified: 2026-07-22T09:55Z
- Summary: Zscaler Cellular Release Upgrade Summary for service updates deployed in 2026.

This article provides a summary of all new features and enhancements for Zscaler Cellular.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/step-step-configuration-guide-zscaler-sim","lastmod":"2026-06-14T20:08Z","nid":"1519076"} -->
## Step-by-Step Configuration Guide for Zscaler SIMs

- Source: https://help.zscaler.com/zscaler-cellular/step-step-configuration-guide-zscaler-sim
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > Step-by-Step Configuration Guide for Zscaler SIMs
- Last modified: 2026-06-14T20:08Z
- Summary: A step-by-step configuration guide for configuring Zscaler SIM for your organization.

This guide takes you through the configuration steps you need to complete to begin using Zscaler SIMs for your organization. Each step guides you through essential tasks, ensuring that you are fully equipped to secure and monitor your IoT and mobile devices. Before you begin configuring Zscaler SIMs, Zscaler recommends reading the following articles to learn more about Zscaler Cellular:

- [What Is Zscaler Cellular?](https://help.zscaler.com/zscaler-cellular/what-zscaler-cellular)
- [Understanding the Zscaler Cellular Architecture](https://help.zscaler.com/zscaler-cellular/understanding-zscaler-cellular-architecture)

## Configuring Zscaler SIMs

- Step 1: Set Up Your Account
- Step 2: Configure Credentials for Cellular Edge Deployment
- Step 3: Deploy Cellular Edges
- Step 4: Configure Polices
- Step 5: Deploy Zscaler SIMs
- Step 6: Monitor SIM Usage

After Zscaler SIM service is enabled for your organization, a tenant is provisioned in Internet & SaaS (ZIA), Private Access (ZPA), Cloud & Branch Connector, Zscaler Cellular, and Authentication Service. The Zscaler service sends you an email with details such as the URL to access and set up Authentication Service. To learn more, see [Signing In to the Zscaler Admin Console](https://help.zscaler.com/unified/signing-zscaler-admin-console).

After completing the setup, log in to the Zscaler Admin Console.

Before you can set up Zscaler SIMs, you must go to the Zscaler Admin Console and enter the credentials for the Cloud & Branch Connector Admin Portal. To learn how to obtain and configure credentials, see [Configuring and Updating Credentials for Cellular Edge Deployment](https://help.zscaler.com/zscaler-cellular/configuring-and-updating-credentials-cell-edge-deployment).

Based on your subscriptions, deploy Cellular Edges in your preferred regions. To learn how to request Cellular Edge deployment, see [Deploying New Cellular Edges](https://help.zscaler.com/zscaler-cellular/deploying-new-cell-edges).

After this request, Zscaler starts deploying and setting up the necessary hardware and software systems to enable egress points for your IoT and mobile devices.

After successful deployment of Cellular Edges, configure necessary policies in the Zscaler Admin Console as required using parameters relating to mobile and IoT devices. The Cellular Edge appears as a location in Internet & SaaS. If you have different types of locations or devices, you can configure it with sublocations.

Insert the Zscaler SIMs supplied to you into your mobile or IoT devices. The Zscaler SIMs automatically connect with the Zscaler cloud.

In the Zscaler Admin Console, monitor the devices and data usage to gather insights into the cellular traffic.

To learn more, see:

- [Understanding the Zscaler Cellular Dashboard](https://help.zscaler.com/zscaler-cellular/understanding-zscaler-cellular-dashboard)
- [About SIMs](https://help.zscaler.com/zscaler-cellular/about-sims)
- [About Network Events](https://help.zscaler.com/zscaler-cellular/about-network-events)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/understanding-anomaly-detection","lastmod":"2026-04-03T21:06Z","nid":"1532771"} -->
## Understanding Anomaly Detection

- Source: https://help.zscaler.com/zscaler-cellular/understanding-anomaly-detection
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > Anomaly Detection > Understanding Anomaly Detection
- Last modified: 2026-04-03T21:06Z
- Summary: Detailed information about anomaly detection.

The anomaly detection leverages metadata, geolocation insights, and network usage patterns to continuously monitor tracked devices and detect when they move beyond a defined geofence area. This detection framework plays a critical role in identifying potential theft, unauthorized movements, or breaches of safety protocols. Administrators configure SIM Location Groups to define geofences within each group. After being linked to a policy and enabled, Zscaler Cellular tracks the device's activity in real time and flags any movement outside the permitted boundary as a violation. This strengthens security, improves operational reliability, and ensures compliance across the connected environment.

You can help detect anomalies using the following features:

- SIM Location Groups
- Anomaly Dashboard

The Anomaly Dashboard provides detailed visibility into policy configurations. It detects anomalies when devices operate beyond the designated geofence area, signaling breaches of active policies. These policies are based on predefined SIM Location Groups, enabling efficient threat detection and streamlined operational control.

See image.

To learn more, see [Viewing the Anomaly Dashboard](https://help.zscaler.com/zscaler-cellular/viewing-anomaly-detection-dashboard).

[Image: Anomaly Detection Dashboard]

Administrators set up SIM Location Groups by defining a specific geofence area for each group and assigning the number of SIM-enabled devices to be monitored within that boundary. These groups form the basis for network anomaly detection policies, enabling the system to track device activity and flag any unauthorized movements or violations. You can view groups on the SIM Location Groups page.

See image

To learn more, see [About the SIM Location Groups](https://help.zscaler.com/zscaler-cellular/about-sim-location-groups).

[Image: SIM Location Groups page with the table containing Group Name, Tracked Devices, and Action column.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/understanding-zscaler-cellular-architecture","lastmod":"2026-03-27T21:06Z","nid":"1518221"} -->
## Understanding the Zscaler Cellular Architecture

- Source: https://help.zscaler.com/zscaler-cellular/understanding-zscaler-cellular-architecture
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > Understanding the Zscaler Cellular Architecture
- Last modified: 2026-03-27T21:06Z
- Summary: Understanding the Zscaler Cellular Architecture Securing IoT and mobile devices with Zscaler SIM and Cellular Edge.

Zscaler Cellular is a transformative solution designed to address the complexities of securing cellular-connected IoT and mobile devices. By leveraging the Zscaler Zero Trust Exchange (ZTE), it extends Zero Trust principles to the cellular domain, ensuring secure, scalable, and efficient connectivity for diverse use cases. This article outlines the Zscaler Cellular architecture and how its components work together to deliver robust security and operational simplicity.

## Key Architectural Components

The key architectural components of Zscaler Cellular are:

### Zscaler SIM

A Zscaler SIM serves as the gateway for a cellular-connected device to access the ZTE. It is a data-only SIM card that integrates directly with the ZTE, providing seamless security for IoT devices such as vending machines, EV chargers, machinery, and tablets/kiosks where agent-based solutions are not feasible. It provides:

- **Secure Traffic Forwarding**: Cellular traffic is routed directly to the ZTE for inspection, policy enforcement, and visibility.
- **Agentless Security**: Devices connected via Zscaler SIMs do not require additional software agents, simplifying deployment and management.
- **Policy Enforcement**: Zscaler SIMs enable you to enforce security policies based on IP address, IMEI, or IMSI via Zscaler Internet Access (ZIA) and Zscaler Private Access (ZPA).
- **Anomaly Detection**: Telemetry data from Zscaler SIMs helps identify abnormal behavior in IoT and mobile deployments.

### Zscaler Cellular Edge

Cellular Edge is an intelligent mechanism to forward traffic from or to a Zscaler SIM to the ZTE. It is an egress point to funnel cellular traffic to the Zero Trust Exchange (ZTE) for inspection and policy enforcement. It provides:

- **Traffic Aggregation**: Funnels cellular traffic to the ZTE for inspection and policy application.
- **Bidirectional Traffic Control**: Ensures secure communication between devices and their endpoints.
- **High Availability**: Supports continuous service delivery with its failover infrastructure.
- **Telemetry Insights**: Delivers granular data on SIM traffic, enabling detailed analytics and reporting.

[Image: Illustration of Zscaler Cellular Architecture]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/understanding-zscaler-cellular-dashboard","lastmod":"2026-04-05T22:46Z","nid":"1518151"} -->
## Understanding the Zscaler Cellular Dashboard

- Source: https://help.zscaler.com/zscaler-cellular/understanding-zscaler-cellular-dashboard
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > Dashboard, Deployment, and Configuration > Understanding the Zscaler Cellular Dashboard
- Last modified: 2026-04-05T22:46Z
- Summary: Information on the Zscaler Cellular Dashboard.

The Zscaler Cellular Dashboard provides a centralized view of your organization's SIM card usage, network activity, and connectivity metrics. It offers a graphical representation of key metrics and real-time insights into your deployed SIM cards and their connection to the Zscaler cloud.

See image.

## Widgets

The Zscaler Cellular Dashboard provides the following widgets:

- Total SIMs
- Used SIMs
- SIMs State Overview
- Global Map
- Total Usage Graph
- Usage by top 10 SIMs
- Usage by top 20 Countries

This widget displays the total number of SIM cards provisioned for your organization. You can click the numerical value representing the number of SIMs to go the [SIMs page](https://help.zscaler.com/zscaler-cellular/about-sims) and see all SIM cards provisioned for the organization.

See image.

This widget shows the number of SIM cards that are actively transmitting data. You can click the numerical value representing the number of used SIMs to go the [SIMs page](https://help.zscaler.com/zscaler-cellular/about-sims) with appropriate filters applied. When you click the numerical value, the SIMs page displays with the Status parameter set to Active, ensuring that only the SIMs cards that are active are displayed.

See image.

This widget provides an overview of the current state of the SIM cards in the network. It categorizes SIMs into the following statuses:

- **Activated**: The number of SIM cards currently active and in use. You can click the numerical value representing the number of active SIMs to go the [SIMs page](https://help.zscaler.com/zscaler-cellular/about-sims) to see all SIM cards that are currently active. When you click the numerical value, the SIMs page is displayed by applying the filter with the Status parameter set to Active, ensuring that only the SIMs cards that are active are displayed.
- **Inactive**: The number of SIM cards that are not in use or have been deactivated. You can click the numerical value representing the number of inactive SIMs to go the [SIMs page](https://help.zscaler.com/zscaler-cellular/about-sims) to see all SIM cards that are currently inactive. When you click the numerical value, the SIMs page is displayed by applying the filter with the Status parameter set to Inactive, ensuring that only the SIMs cards that are inactive are displayed.

See image.

This widget displays the geographical distribution of active SIM cards on a world map with country granularity. Each blue dot represents active connections in a specific region, providing a global view of your SIM deployments.

See image.

This widget shows data usage as a line and bar graph showing daily data usage trends over the selected period. The graph provides both a visual breakdown of usage spikes and a view of overall trends. In the top-left corner of this widget, the cumulative data usage for all SIM cards within the selected date range is shown. You can customize the date range by selecting a default range (**Today**, **Yesterday**, **Last 7 Days**, **Last 30 Days**, **This Month**, or **Last** **Month**) or selecting start and end dates for a custom range.

To download the graph data for the specified date range, click the **Menu**icon in the top-right corner of the graph, and select a download option based on the preferred format (**Download as SVG**, **Download as PNG**, or **Download as CSV**).

See image.

This widget displays the top 10 SIMs with the highest data consumption within the selected date range. Each SIM is identified by its unique number, and its corresponding data usage is visualized as a bar chart. You can customize the date range by selecting a default range (**Today**, **Yesterday**, **Last 7 Days**, **Last 30 Days**, **This Month**, or **Last** **Month**) or selecting start and end dates for a custom range.

To download the graph data for the specified date range, click the **Menu**icon in the top-right corner of the graph, and select a download option based on the preferred format (**Download as SVG**, **Download as PNG**, or **Download as CSV**).

See image.

This widget presents data consumption by country, highlighting the top 20 countries where the SIMs have consumed the most data within the selected period. It helps in identifying regions with high network activity. You can customize the date range by selecting a default range (**Today**, **Yesterday**, **Last 7 Days**, **Last 30 Days**, **This Month**, or **Last** **Month**) or selecting start and end dates for a custom range.

To download the graph data for the specified date range, click the **Menu**icon in the top-right corner of the graph, and select a download option based on the preferred format (**Download as SVG**, **Download as PNG**, or **Download as CSV**).

See image.

[Image: Complete view of the Zscaler Cellular dashboard]

[Image: Widget showing total number of SIMs]

[Image: Widget showing total number of used SIMs]

[Image: Widget showing total number of SIMs across different statuses]

[Image: Widget showing global distribution of SIMs]

[Image: Widget showing data usage trend]

[Image: Widget showing top 10 SIMs by usage]

[Image: Widget showing top 20 countries by usage]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/viewing-and-monitoring-cellular-edge-deployments","lastmod":"2026-04-05T22:46Z","nid":"1518156"} -->
## Viewing and Monitoring Cellular Edge Deployments

- Source: https://help.zscaler.com/zscaler-cellular/viewing-and-monitoring-cellular-edge-deployments
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > Dashboard, Deployment, and Configuration > Viewing and Monitoring Cellular Edge Deployments
- Last modified: 2026-04-05T22:46Z
- Summary: How to view and request regional Cellular Edge deployments.

Zscaler deploys and maintains Cellular Edges that serve as secure access on-ramps to the Zscaler Zero Trust Exchange (ZTE), ensuring seamless and secure connectivity for your devices. Depending on your usage requirements, you can request additional Cellular Edges in your preferred regions. You can also monitor the deployment and service status of the Cellular Edges directly from the Zscaler Admin Console.

To view the Cellular Edge deployments:

1. Go to **Infrastructure**> **Connectors**> **Cellular**> **Deployment Regions**. The **Deployment Regions** page appears.
2. On the **Deployment Regions**page, you can view regions on the map where Cellular Edges are deployed. The regions are color-coded to indicate the following deployment and service statuses: See image. You can view and refresh the map to update deployment and service statuses.
  - **Online**: Represented by a green color, indicating that the service is fully operational.
  - **Disruption**: Represented by a red color, signaling a complete service outage or unavailability.
  - **Degradation**: Represented by a gray color, highlighting reduced performance or partial service issues.
  - **Pending Deployment**: Represented by a yellow color, indicating that deployment of Cellular Edges is currently underway for the region but not yet active.

You can also request deployment of new Cellular Edges. To learn more, see [Deploying New Cellular Edges](https://help.zscaler.com/zscaler-cellular/deploying-new-cell-edges).

[Image: Viewing and monitoring Cellular Edge deployments]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/viewing-anomaly-dashboard","lastmod":"2026-04-03T21:06Z","nid":"1534047"} -->
## Viewing the Anomaly Dashboard

- Source: https://help.zscaler.com/zscaler-cellular/viewing-anomaly-dashboard
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > Anomaly Detection > Viewing the Anomaly Dashboard
- Last modified: 2026-04-03T21:06Z
- Summary: Explaining all the features and insights available on the Zscaler Cellular Anomaly Detection dashboard.

The Anomaly Dashboard provides an overview of all active anomaly detection policies and their associated violations when tracked devices move outside the specified geofence area. It provides a detailed breakdown of each policy. Additionally, you can easily manage existing policies and create new policies.

See image.

On the Anomaly Dashboard (Infrastructure > Connectors > Cellular > Anomaly Dashboard), you can see the following:

- Add Policy Button
- Policy Details Table

[Image: Anomaly Detection Dashboard]

Create a new [anomaly detection policy](https://help.zscaler.com/zscaler-cellular/configuring-geofence-anomaly-detection-policies) by specifying the policy name, type, and SIM Location Groups.

This table displays detailed information for each policy, including its name, whether it is enabled, its current running status, the total number of violations, and the timestamp of the last recorded activity. It also provides options for managing each policy.

You can manage policies using the following options in the **Action**column:

1. **Edit:**Update policy configurations, including changing the policy name and adding or removing SIM Location Groups.
2. **View:**Display detailed policy configuration information, including the policy name, type, and associated SIM Location Groups.
3. **Delete:**Delete the policy. Deleted policies remain visible in the Policy Details table.

See image.

You can edit or delete a policy only when it is disabled.

[Image: The Policy Details table with the Edit, View, and Delete icons highlighted in the Action column.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/viewing-network-event-details","lastmod":"2026-04-05T22:49Z","nid":"1518101"} -->
## Viewing Network Event Details

- Source: https://help.zscaler.com/zscaler-cellular/viewing-network-event-details
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > Network Events and Logins Monitoring > Viewing Network Event Details
- Last modified: 2026-04-05T22:49Z
- Summary: How to view detailed network events.

You can access in-depth information about specific network events associated with each SIM provisioned to your organization. This detailed view helps you analyze connectivity behavior, troubleshoot issues, and understand event-specific details for enhanced network management.

To view the details of a specific network event:

1. In the Zscaler Admin Console, go to **Infrastructure**> **Connectors**> **Cellular**> **Network Events**. The **Network Events**page appears.
2. Locate the event of interest in the table and click **ICCID**. The **Event Details** drawer appears with the following details: You can view, copy, or download the JSON file containing the network event details. See image.
  - **Country**: The country where the event occurred.
  - **IMSI**: The International Mobile Subscriber Identity associated with the SIM.
  - **Operator**: The telecom operator managing the network connection.
  - **SIM Name**: The name or alias assigned to the SIM.
  - **Source System**: The system or node responsible for the event.
  - **Timestamp**: The exact date and time when the event occurred.
  - **IP Address**: The public or private IP address assigned to the SIM during the session when the event occurred.
  - **Location Details**: Including Location Area Code (LAC), Cell Identifier (CID), Mobile Country Code (MCC), and Mobile Network Code (MNC).
  - **RAT Type**: The Radio Access Technology used for the network connection (e.g., LTE, 5G).
  - **Data Cap Reached**: Indicates whether the SIM has exceeded the allocated data usage limit.
  - **Event Name**: The type of event that occurred, such as session initiated, authorized, or disconnected (e.g., DATA_SESSION_ONLINE).
  - **ICCID**: The Integrated Circuit Card Identifier assigned to the SIM.
  - **Session ID**: A unique identifier assigned to the session.
  - **SIM ID**: A unique identifier assigned to the SIM card used in the device.
  - **SIM EID**: A unique identifier for the embedded SIM (eSIM) used in the device.
  - **Connection Status Log**: Provides options to view, download, and copy network event details in JSON format.

[Image: Viewing network event details with the available actions highlighted.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/viewing-sim-details","lastmod":"2026-07-21T21:06Z","nid":"1518126"} -->
## Viewing SIM Details

- Source: https://help.zscaler.com/zscaler-cellular/viewing-sim-details
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > SIMs > Viewing SIM Details
- Last modified: 2026-07-21T21:06Z
- Summary: How to view details of each SIM card.

You can access in-depth information about each SIM—both physical SIMs and eSIMs—provisioned to your organization. The SIM details page provides a comprehensive overview of a selected SIM's session activity, including its current status, historical data usage, key connectivity details, and location history.

To view the details of a SIM card:

1. Go to**Infrastructure**> **Connectors**> **Cellular**> **SIMs**. The **SIMs**page appears.
2. In the table, click the **ICCID**link for the SIM you are interested in. The **SIM details**page opens.
3. On the **SIM details**page, you can view: See image.
  - **SIM Info**: Displays the basic details about the SIM:
    - **Connection**: Indicates whether the SIM is online or offline.
    - **APN**: The Access Point Name used for the SIM's network connectivity.
    - **ICCID**: The Integrated Circuit Card Identifier associated with the SIM card.
    - **MSISDN**: The Mobile Station International Subscriber Directory Number is the associated mobile number of the SIM, if applicable.
    - **Activation Date**: The date and time the SIM was activated.
    - **Country**: The location where the SIM card is located.
    - **Last Usage Updated**: The time when the last change was recorded.
    - **EID**: The Embedded Identity Document for eSIMs.
    - **Operator**: The telecom operator providing the network service.
    - **IP Address**: The IP address currently assigned to the SIM.
    - **IMEI**: The International Mobile Equipment Identity, the unique identifier for the device using the SIM.
    - **IMSI**: The International Mobile Subscriber Identity associated with the SIM.
    - **Tags**: The tags associated with the SIM card. You can click a specific tag to filter the SIMs page to display only the SIMs that have the tag associated with them.
  - **Device Info**: Displays the basic details about the device associated with the SIM: See image.
    - **Device Manufacturer**: The name of the company that produces the device associated with the SIM card.
    - **Device Type**: The category of the device associated with the SIM card (e.g., router, modem, and IoT gateway).
    - **Operating System**: The operating system running on the device associated with the SIM card.
    - **Device Model**: The full official identifier of the type of device associated with the SIM card.
    - **Model Name**: The specific code of the type of device associated with the SIM card.
  - **Current Data Usage**: A dynamic widget showing the cumulative data usage for the date range selected in the **Usage Chart**. See image.
  - **eSIM Info**: A widget showing to whom an eSIM must be assigned. This widget appears only for eSIMs. See image.
  - **Total Usage**: A widget that provides a detailed visual representation of data usage over time and allows you to identify usage trends, peak usage periods, and potential anomalies. You can customize the chart by selecting a default date range (**Today**, **Yesterday**, **Last 7 Days**, **Last 30 Days**, **This Month**, or **Last Month**) or choosing **Custom Range**and selecting start and end dates to display usage details for the specific period. See image.
  - Option to view network events associated with the SIM. See image.
  - Option to update the [status](https://help.zscaler.com/zscaler-cellular/changing-status-zscaler-sims), [IMEI](https://help.zscaler.com/zscaler-cellular/changing-imei-association-zscaler-sims), and [tags](https://help.zscaler.com/zscaler-cellular/managing-tags-zscaler-sims) for the SIM card. See image.
  - **Location History**: A widget that provides a detailed visual representation of your SIM’s movements across different locations and the network events that triggered those transitions. It helps you identify travel patterns, frequent activity areas, and connectivity changes. It shows: See image.
    - Customize Date: You can adjust the display by selecting a default date range.
    - Map Indicators: Blue pins on the map represent SIM activity and movements. Hovering over a pin shows detailed information, including the event’s date and time, type of event, operator, Mobile Country Code (MCC), Mobile Network Code (MNC), and Cell ID.
    - Timeline Bar: The timeline bar displays the total number of SIM location events and allows you to move through them from the oldest to the newest. This gives you a quick, chronological view of activity over time.

[Image: Viewing basic information on the SIM details page with sensitive information blurred]

[Image: Widget showing current data usage]

[Image: Widget showing eSIM information]

[Image: Option to select a data range in the data usage chart]

[Image: Button to view network events associated with the SIM]

[Image: Drop-down menu showing options to update SIM values]

[Image: Viewing the SIM Details page, with widgets showing basic information, current usage data, and a usage chart]

[Image: Location History]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-cellular/what-zscaler-cellular","lastmod":"2026-04-03T21:06Z","nid":"1518216"} -->
## What Is Zscaler Cellular?

- Source: https://help.zscaler.com/zscaler-cellular/what-zscaler-cellular
- Product: Zscaler Cellular
- Path: Zscaler Cellular Help > What Is Zscaler Cellular?
- Last modified: 2026-04-03T21:06Z
- Summary: Information on Zscaler Cellular, its features, benefits, and use cases to secure IoT and mobile devices.

Zscaler Cellular is a comprehensive solution designed to provide secure, scalable, and efficient connectivity for IoT and mobile devices on a Zero Trust architecture. It includes two key products: Zscaler SIM and Zscaler Cellular Edge. This offering enables organizations to secure cellular traffic seamlessly while leveraging Zscaler's industry-leading Zero Trust Exchange (ZTE) for policy enforcement, visibility, and control. Zscaler Cellular addresses the growing challenges of securing billions of cellular-connected IoT devices and mobile endpoints, particularly in environments where traditional security solutions fail to scale. The solution integrates into existing telecom infrastructure to deliver a secure and unified connectivity experience, ensuring:

- **Accessibility**: Secure access to devices worldwide on any protocol.
- **Security**: Network segmentation, least-privilege access, full visibility, and no attack surface.
- **Simplification**: Streamlined management of cellular estates through a Network-as-a-Service model.
- **Connectivity**: Global connectivity with regional egress and multi-operator coverage.

Zscaler Cellular ensures secure traffic steering, eliminates the risk of lateral movements, and provides centralized visibility and control over all cellular data.

## Key Features and Benefits

Zscaler Cellular combines simplified provisioning, intelligent traffic management, and robust Zero Trust policy enforcement to deliver unparalleled security and usability for cellular-connected devices. With features like IP address, IMEI, or IMSI-based policy constructs, real-time logging, and centralized dashboards, organizations gain:

- **Near-Instant Deployment**: Streamlined provisioning with minimal changes to telecom infrastructure.
- **No Client-Side Software**: Eliminates the need for software agents on endpoints.
- **Granular Control**: Enables precise policy enforcement for SIM-connected devices.
- **Full Traffic Visibility**: Offers centralized monitoring of telemetry metrics, including SIM activity and anomaly detection.
- **Eliminated Attack Surface**: Prevents lateral movement within networks by enforcing Zero Trust principles.
- **Improved Performance**: Optimizes routing for reduced latency and enhanced device connectivity.

By leveraging the ZTE, Zscaler Cellular seamlessly integrates with policies in Zscaler Internet Access (ZIA) and Zscaler Private Access (ZPA) to provide visibility and scalability for IoT devices, addressing challenges such as carrier inefficiencies and security gaps in public cellular networks.

## How Does Zscaler SIM Work?

- **Device Connection**: An IoT or mobile device equipped with Zscaler SIM connects to public 4G/5G networks.
- **Traffic Forwarding**: Zscaler SIM routes cellular traffic to the nearest Zscaler Cellular Edge, which acts as the bridge between the telecom network and Zscaler. The Cellular Edge then forwards traffic to the ZTE, which inspects and enforces security policies.
- **Policy Application**: Traffic is subjected to ZIA or ZPA policies based on predefined constructs (e.g., IP address, IMEI, and IMSI).
- **Visibility and Control**: Administrators access centralized dashboards to monitor traffic, enforce policies, and generate reports.

## Use Cases by Industry

- **Critical Infrastructure**: Secure connectivity for railway systems, power grids, and other critical operational technology systems.
- **Industrial IoT**: Secure telemetry for connected machinery and logistics equipment.
- **Retail**: Enable secure point-of-sale (POS) systems and kiosks.
- **Mobility**: Provide connectivity for connected vehicles and EV chargers.
- **Logistics/Transportation**: Secure upload of telemetry data and connected cabins.
<!-- /ZS-ARTICLE -->
