# Zscaler Help — ZIA — Internet & SaaS (part 8)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-11-02 13:58 UTC
Articles in this file: 6

---

<!-- ZS-ARTICLE {"url":"/zia/writing-pac-file","lastmod":"2026-10-22T09:08Z","nid":"1399396"} -->
## Writing a PAC File

- Source: https://help.zscaler.com/zia/writing-pac-file
- Product: Internet & SaaS (ZIA)
- Path: Internet & SaaS (ZIA) Help > Traffic Forwarding > PAC Files > Using PAC Files > Writing a PAC File
- Last modified: 2026-10-22T09:08Z
- Summary: How to write a PAC file and include Zscaler-specific variables in the argument.

This article describes how to write a new [PAC file](https://help.zscaler.com/zia/understanding-pac-file).

1. Copy and paste any 1 of the 4 default PAC files, `recommended.pac`, `proxy.pac`, `mobile_proxy.pac`, or `kerberos.pac` from the Zscaler Admin Console based on your requirements. You can customize these default PAC files as necessary. See image.
2. Build your PAC file one element at a time.
3. Save the file and test it after each addition.

## Prerequisites

Before you begin, ensure that you review [Best Practices for Writing PAC Files](https://help.zscaler.com/zia/best-practices-writing-pac-files).

By default, each PAC file that you upload to the Zscaler service can be up to 256 KB in size. Contact Zscaler Support to increase the PAC file size limit to a maximum of 2 MB.

## PAC File Components

Be aware of the following components of a PAC file while writing it:

- FindProxyForURL Function
- Return Statements
- Arguments
- Zscaler-Specific Variables

## Manual Failover to Secondary Data Center

When the primary data center is down, the failover to the secondary data center is automatic. However, if the primary data center is unavailable for any other reason, such as ISP issues, latency issues, etc., modify the PAC file syntax as follows to manual failover to the secondary data center:

```
return "PROXY ${SECONDARY_GATEWAY_FX}:80; PROXY ${GATEWAY_FX}:80; DIRECT";
```

If your organization uses a subcloud, use the variables `${GATEWAY.``<Subcloud>``.``<Zscaler Cloud>``.net}` and `${SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler Cloud>``.net}`. For example:

```
return "PROXY ${SECONDARY.GATEWAY.
<Subcloud>.<
Zscaler
Cloud>
.net_FX}:80; PROXY ${GATEWAY.
<Subcloud>.<
Zscaler
Cloud>
.net_FX}:80; DIRECT";
```

- See a sample PAC file.

A PAC file must start with the opening function `FindProxyForURL(url, host)` on the first line. This function identifies which URLs, hostnames, or IP addresses are redirected to a proxy. The URL variable must contain the full URL of a destination (e.g., http://www.zscaler.com), and the host variable must contain a domain name or its IP address (e.g., zscaler.com or 72.249.144.174). Generally speaking, the host variable is preferable to the URL variable. If no URL or host is specified, all web requests use the return argument.

Enter a return statement in curly brackets after each argument. The return statement directs requests to a proxy or the specified destination. For example:

```
function FindProxyForURL(url, host)
                { return "PROXY ${GATEWAY}:9400"; return "DIRECT"; }
```

A return statement accepts one of two values:

- `DIRECT` tells the browser to bypass the proxy and go directly to the destination server.
- `PROXY` tells the browser to send the request to a proxy.

The `DIRECT` or `PROXY` values must be in uppercase characters and enclosed in quotation marks. Add a semicolon immediately after the closing quotation marks. For example:

```
return "DIRECT";
                return "PROXY ${GATEWAY}:9400";
```

Each PROXY statement must specify the fully qualified hostname or the IP address of the proxy and the port. IP addresses are generally discouraged because they can change at any time. Zscaler recommends that you use the variables `${GATEWAY}` and `${SECONDARY_GATEWAY}` instead. If your organization uses a subcloud, use the variables `${GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net}` and`${SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net}`.

The Zscaler service uses its geolocation technology to automatically find the Public Service Edge for Internet & SaaS (ZIA) that is closest to you and with the quickest response time. Naming a primary and secondary gateway provides failover when one of the Public Service Edges is unavailable for any reason.

Public Service Edges accept web requests on ports 80, 443, 9400, 9443, and 9480.

- Port 80 is the standard port used by almost all web servers.
- Port 443 is the standard port used for encrypted (HTTPS) traffic. Port 9400 can be used instead if another host between the end user and Public Service Edge attempts to redirect the user’s traffic before it can reach the Public Service Edge. Ensure to allow port 9400 for outbound traffic destined for Public Service Edge from the user's end before adding the proxy entry to the PAC file. If port 9400 is blocked at the user's end for outbound traffic, do not add the proxy entry for the port to the PAC file. This ensures that there is no latency due to the browser's multiple attempts to connect to the blocked port until it fails over to a reachable port.
- Port 9443 can be used for remote users who need the service to proxy and inspect HTTPS transactions.
- Port 9480 can be used to exempt authentication for traffic that is destined for Public Service Edges from a known location. Remote users can use this port to forward traffic to Zscaler. However, they are forced to authenticate because Zscaler does not accept unauthenticated traffic from unregistered IP addresses or unknown locations.

Write the opening function `FindProxyForURL(url,host)` { } followed by a return statement on the next line. For example:

```
function FindProxyForURL(url, host)
                { return "PROXY ${GATEWAY}:9400"; return "DIRECT"; }
```

Save the text file and test it. Verify that the PAC file sends your browser to the Zscaler service with no other arguments, IF statements, or other elements. Confirm by going to [ip.zscaler.com](http://ip.zscaler.com/), which indicates if you reached the web server directly or through a Public Service Edge.

You can add various arguments that identify which internal or external hosts must be proxied to. Add one nested argument at a time, and then test and confirm that the PAC file works properly after each addition.

The following is a sample PAC file that contains arguments, each of which is preceded by a comment.

```
function FindProxyForURL(url, host)
                {
                //
                //Exclude FTP from proxy
                //
                if (url.substring(0, 4) == "ftp:")
                {
                return "DIRECT";
                }
                //
                //Bypass proxy for internal hosts
                //
                if (isInNet(host, "0.0.0.0", "255.0.0.0")||
                isInNet(host, "10.0.0.0", "255.0.0.0") ||
                isInNet(host, "127.0.0.0", "255.0.0.0") ||
                isInNet(host, "169.254.0.0", "255.255.0.0") ||
                isInNet(host, "172.16.0.0", "255.240.0.0") ||
                isInNet(host, "192.0.2.0", "255.255.255.0")||
                isInNet(host, "64.206.157.136", "255.255.255.255"))
                {
                return "DIRECT";
                }
                //
                //Bypass proxy for this server
                //
                if (dnsDomainIs(host, "mail.domain.com"))
                {
                return "DIRECT";
                }
                return "PROXY ${GATEWAY}:9400; PROXY ${SECONDARY_GATEWAY}:9400; DIRECT";
                }
```

For the arguments in the preceding sample PAC file, specify the following:

- Excluding FTP Traffic
- Excluding Requests for Internal Hosts
- Excluding Internal Networks
- Excluding Servers

The following are the different Zscaler-specific variables you can use in your PAC file argument:

- Gateway Variable
- Gateway Host Variable
- Source IP Variable
- Country Variable
- Country Gateway Variable
- Country Gateway Host Variable
- Custom Port Variable
- Zscaler Client Connector Trusted Network Variable
- Zscaler Client Connector Z-Tunnel 2.0 Variable

The Zscaler-specific variables are applicable only if you host your PAC files on the Zscaler cloud.

The following lines exclude FTP traffic from being redirected to the proxy (since the service does not support native FTP):

```
//
                   //Exclude FTP from proxy
                   //
                if (url.substring(0, 4) == "ftp:") 
                    {
                     return "DIRECT";
                    }
```

The argument begins with a simple IF statement using the built-in function called `url.substring`. IF arguments are always followed by opening and closing parentheses—they describe the conditions under which the argument must be evaluated and for which a result is required. In the example, the argument specifies that if the URL contains the string `ftp:` in the first four characters of the URL (from 0 to 4), return DIRECT—bypass the proxy named in the following line.

The argument’s result must be enclosed within its own set of open and closed curly brackets.

Save this change to the PAC file and upload it to the Zscaler Admin Console. Reload the PAC file in your browser and go to an FTP download site such as ftp://ftp.hp.com/. After loading this page, log in to the specific Insights log (for example, Web Insights) to determine if this transaction was logged. The transaction should not appear in the logs.

The following lines in the PAC file example exclude requests for internal hosts from being redirected to a proxy:

```
//
                 //Do not send traffic to the following network to Zscaler
                 //
                if (isInNet(host), "192.168.0.0", "255.255.0.0")
                {
                 return "DIRECT";
                }
```

This argument uses the JavaScript function `IsInNet()`, which is typically used to identify either of the following:

- Client IP address (if the request comes from this IP address, use this proxy). Be aware that this argument returns the first IP address on your device, based on its OS. The first IP address, shown when you use the `ipconfig` command, might be the IPv6 address of the device or the IP address of virtual adapters and this can cause conflicts.
- Host server IP address (if the request is going to this address, use this proxy). Be aware that this argument results in a DNS lookup. It can impact performance if the DNS server is not available. Instead, you can use the following to constrain the `IsInNet()` function based on the host domains being accessed:

```
if dnsDomainIs(host, "internal.net") {
                if (isInNet(host, "10.0.0.0”, "255.0.0.0"))
                Return "DIRECT";
                }
```

Save the PAC file and test again. Browse to an internal host and ensure that you can reach it. If you were proxied through a Public Service Edge, your request would be denied. The request first goes outside your network to the Zscaler proxy but is then blocked as it tries to access an internal host as it comes back in from outside the network.

The following lines in the PAC file example exclude requests for multiple internal hosts from being redirected to a proxy:

```
//
                  //Bypass proxy for internal hosts
                  //
                 if(isInNet(host, "0.0.0.0", "255.0.0.0")||
                 isInNet(host, "10.0.0.0", "255.0.0.0") ||
                 isInNet(host, "127.0.0.0", "255.0.0.0") ||
                 isInNet(host, "169.254.0.0", "255.255.0.0") ||
                 isInNet(host, "172.16.0.0", "255.240.0.0") ||
                 isInNet(host, "192.0.2.0", "255.255.255.0")||
                 isInNet(host, "64.206.157.136", "255.255.255.255"))
                 {
                  return "DIRECT";
                 }
```

As shown in the example, before closing the IF argument with a final parenthesis, the Boolean `or` (||) operator is used to concatenate the internal networks. The last `isInNet` statement does not require the Boolean `or` (||) operator. Instead, it uses an additional parenthesis to close the opening parenthesis.

The last statement is a specific IP address, not a network. To bypass specific hosts, enter the exact IP address with a subnet mask of 255.255.255.255.

The use of `isInNet()` is extremely effective when the host you are trying to reach is an actual IP address. If you try to reach a host by a domain name, such as https://zscaler.com, your browser must perform a DNS lookup for it. If the hostname is not resolvable, the client needs to wait for DNS to time out before moving on. To avoid this and to avoid placing an undue strain on the name server, insert a variable that uses a regular expression immediately preceding the `isInNet()` argument to restrict the result just to IP addresses.

Therefore, instead of entering:

```
if (isInNet(host, "0.0.0.0", "255.0.0.0")||
                isInNet(host, "10.0.0.0", "255.0.0.0") ||
                isInNet(host, "127.0.0.0", "255.0.0.0") ||
                ...)
                {
                  return "DIRECT";
                }
```

Enter this:

```
reip = /^\d+\.\d+\.\d+\.\d+$/g;
                if (reip.test(host))
                {
                 if (isInNet(host, "0.0.0.0", "255.0.0.0")||
                 isInNet(host, "10.0.0.0", "255.0.0.0") ||
                 isInNet(host, "127.0.0.0", "255.0.0.0") ||
                    ...)
                 {
                  return "DIRECT";
                 }      
                }
```

Here, the variable `reip = /^\d+\.\d+\.\d+\.\d+$/g;` is used in the argument `if(reip.test(host))`, and the result is only used if the host is an IP address in one of the networks specified in the nested IF argument.

This can only be used for URLs that include IP addresses, such as http://192.0.2.3/example.

In some versions of Internet Explorer, the use of the preceding variable might not work. An alternative to this is to use the JavaScript shell expression match function:

```
if (shExpMatch(host, "/^\d+\.\d+\.\d+\.\d+$/g")) 
                {
                 if (isInNet(host, "10.0.0.0", "255.0.0.0") ||
                 isInNet(host, "192.168.0.0", "255.255.0.0")) 
                 {
                  return "DIRECT";
                 }      
                }
```

Save this change, reload the PAC file in your browser, and then try browsing to an internal web server in the internal network. If you can reach the server, you have bypassed the Public Service Edge.

The following lines in the PAC file example exclude e specific servers, such as mail.domain.com, from being redirected to a proxy. In the example, a separate `if isInNet()` argument lists internal hostnames:

```
//Bypass proxy for this server //
                if (dnsDomainIs(host, "mail.domain.com"))
                { return "DIRECT"; }
                //To bypass all hosts in a domain, use
                dnsDomainIs(host, "host.com")
                 
                You can also use the following to bypass specific internal hosts:
                var bypassHosts = /(remote\.mydomain\.com|mail\.mydomain\.com)/; 
                if (bypassHosts.test(host)) 
                {
                 
                  return "DIRECT";
                }
```

The preceding argument includes a variable that contains two hosts: remote.mydomain.com and mail.mydomain.com. Using the `JavaScript test (host)` function, any host you enter here returns `DIRECT` and does not require a DNS lookup. `var` is the JavaScript function to set a variable. `bypassHosts` is a JavaScript function. You must use this specific name or function. Forward slashes mark the beginning and ending boundaries of the variable. Open and close parentheses in the variable match the parentheses in the argument (test(host)). The periods in the hostnames must be “escaped” with a backslash. The variable itself requires a semicolon to close the variable argument.

You can use the `${GATEWAY}` and `${SECONDARY_GATEWAY}`variables to determine the Public Service Edge closest to the client. For example:

```
return "PROXY ${GATEWAY}:80; PROXY ${SECONDARY_GATEWAY}:80; DIRECT";
```

The Zscaler service uses its geolocation technology to find the closest Public Service Edge with the quickest response time. These variables provide the optimal user experience.

If your organization uses a subcloud, use the variables `${GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net}` and `${SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net}`. For example:

```
return "PROXY ${GATEWAY.
<Subcloud>
.
<Zscaler cloud>
.net}:80; PROXY ${SECONDARY.GATEWAY.
<Subcloud>
.
<Zscaler cloud>
.net}:80; DIRECT";
```

If your organization has many users behind a single egress IP address, you can configure the PAC file to choose from multiple gateway IP addresses to distribute the load. To do this, use the following variables:

- The load-balancing PAC variables are designed for non-tunnel traffic. You do not require these variables for load-balancing your tunnel (GRE/IPSec) traffic.
- Regional surcharge data centers are not included in the gateway variable.

- Gateway Index Tokens
- Dynamic Gateway Tokens

Zscaler recommends this method if you want the users to be distributed across multiple gateway IP addresses in a data center for load-balancing purposes.

Add the suffixes `_F0` through `_F7` to the `${GATEWAY}` variable in the PAC file for the PAC server to return the healthy gateway IP addresses through the 8 variables. For example, the `${GATEWAY_F0}` variable corresponds to the first healthy IP address that is available, the `${GATEWAY_F1}` variable corresponds to the second healthy IP address, and so on.

Use the following syntax to include the `${GATEWAY_F0}` variable in your PAC file:

```
return "PROXY ${GATEWAY_F0}:80; PROXY ${SECONDARY_GATEWAY_F0}:80; DIRECT";
```

If the data center has fewer than 8 healthy gateway IP addresses, then the PAC server allocates the available healthy VIP addresses to all 8 variables in a round-robin fashion.

If your organization uses a subcloud, you can use the gateway index tokens (`_F0`through`_F7`suffix) with the subcloud variables. For example, `${GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_F1}` and `${SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_F1}`.

Use the suffix `_FX` to the `${GATEWAY}` variable in the PAC file for the PAC server to dynamically issue gateway IP addresses based on the client fingerprints (all users coming from a single egress IP address are given an IP address from a pool of healthy gateway IP addresses). The fingerprint is used to ensure that a single device continues its session to the same gateway IP address.

Use the following syntax to include the `${GATEWAY_FX}`variable in your PAC file:

```
return "PROXY ${GATEWAY_FX}:80; PROXY ${SECONDARY_GATEWAY_FX}:80; DIRECT";
```

You can also use the `_FX` suffix with the subcloud variables. For example, `${GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_FX}` and `${SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_FX}`.

The `_FX` suffix provides load balancing across multiple VIP addresses depending on the HTTP headers (useragent, x-forwarded-for, and z-client). This variable is effective only for Zscaler Client Connector clients because the z-client ID is different for each user. To implement load balancing for non-Zscaler Client Connector clients, you can use the Gateway Index Tokens. To learn more, see [Load Balancing for PAC Forwarded Traffic](https://help.zscaler.com/zia/load-balancing-pac-forwarded-traffic).

You can use the `${Gateway_Host}` variable to resolve to a hostname instead of an IP address. For Kerberos authentication and IPv6 traffic, this is a recommended configuration.

- Zscaler recommends using this variable and resolving your hostname to forward IPv6 traffic t. You must also ensure that [IPv6 support](https://help.zscaler.com/zia/configuring-ipv6-settings) is enabled for your organization and locations and the [Enable IPv6 Resolution for Zscaler Domain](https://help.zscaler.com/zscaler-client-connector/enabling-ipv6-resolution-zscaler-domains) is enabled in the Zscaler Admin Console.
- Using this variable also allows browsers to configure PAC files with [Dynamic Gateway Host Tokens](https://help.zscaler.com/zia/writing-pac-file#dynamic-gateway-host-tokens) (i.e., `${GATEWAY_HOST_FX}` variable) to support IPv6 traffic for browser deployments.

Use the following syntax to include the `${GATEWAY_HOST}` variable in your PAC file:

```
return "PROXY ${GATEWAY_HOST}:80; PROXY ${SECONDARY_GATEWAY_HOST}:80; DIRECT";
```

If your organization uses a subcloud, use the variables `${GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST}` and `${SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST}`. For example:

```
return "PROXY ${GATEWAY.
<Subcloud>.<Zscaler cloud>
.net_HOST}:80; PROXY ${SECONDARY.GATEWAY.
<Subcloud>.<Zscaler cloud>
.net_HOST}:80; DIRECT";
```

If your organization has many users behind a single-egress IP address, you can configure the PAC file to choose from multiple gateway hosts to distribute the load. To do this, use the following variables:

The load-balancing PAC variables are designed for non-tunnel traffic. You do not require these variables for load-balancing your tunnel (GRE/IPSec) traffic.

- Gateway Host Index Tokens
- Dynamic Gateway Host Tokens

Zscaler recommends this method if you want the users to be distributed across multiple gateway hosts in a data center for load-balancing purposes.

Add the suffixes `_F0` through `_F7` to the `${GATEWAY_HOST}` variable in the PAC file for the PAC server to return the healthy gateway host through the 8 variables. For example, `${GATEWAY_HOST_F0}` corresponds to the first healthy host that is available, `${GATEWAY_HOST_F1}` corresponds to the second healthy host, and so on.

Use the following syntax to include the `${GATEWAY_HOST_F0}` variable in your PAC file:

```
return "PROXY ${GATEWAY_HOST_F0}:80; PROXY ${SECONDARY_GATEWAY_HOST_F0}:80; DIRECT";
```

If the data center has fewer than 8 healthy gateways, then the PAC server allocates the available healthy gateway hosts to all 8 variables in a round-robin fashion.

If your organization uses a subcloud, you can use the gateway host index tokens (`_F0`through`_F7`suffix) with the subcloud variables. For example, `${GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST_F1}` and `${SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST_F1}`.

Use the suffix `_FX` to the `${GATEWAY_HOST`} variable in the PAC file for the PAC server to dynamically issue gateway hosts based on the client fingerprint (all users coming from a single egress IP address are given an IP address from a pool of healthy gateway IP addresses). The fingerprint is used to ensure that a single device continues its session on the same gateway host.

Use the following syntax to include the `${GATEWAY_HOST_FX`} variable in your PAC file:

```
return "PROXY ${GATEWAY_HOST_FX}:80; PROXY ${SECONDARY_GATEWAY_HOST_FX}:80; DIRECT";
```

You can also use the `_FX` suffix with the subcloud variables. For example, `${GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST_FX}` and `${SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST_FX}`.

The `_FX` suffix provides load balancing for multiple gateway hosts depending on the HTTP headers (useragent, x-forwarded-for, and z-client). This variable is effective only for Zscaler Client Connector clients because the z-client ID is different for each user. To implement load balancing for non-Zscaler Client Connector clients, you can use the Gateway Host Index Tokens. To learn more, see [Load Balancing for PAC Forwarded Traffic](https://help.zscaler.com/zia/load-balancing-pac-forwarded-traffic).

You can use the `${SRCIP}` variable to determine the client's public IP address. For example:

```
var egressip = "${SRCIP}";
                if (shExpMatch(egressip,"203.0.113.10")) {
                		/* User is in the office */
                		return "PROXY 10.84.0.188:80;DIRECT";
                }
```

You can use the `${COUNTRY}` variable to determine the client's country as shown in the following sample:

```
var country = "${COUNTRY}";
                if (shExpMatch(country,"Canada")) { 
                        /* User is in Canada */ 
                        return "PROXY ${COUNTRY_GATEWAY}:80;DIRECT"; 
                }
```

The `${COUNTRY}` variable supports the countries listed on [GeoNames](https://www.geonames.org/countries/). Your IP addresses might not fall into those categories.

You can also set exceptions for specific URLs when accessed from a specific country using the `${COUNTRY}`variable as shown in the following sample:

```
var country = "${COUNTRY}";
                if (shExpMatch(country,"Canada") && 
                ((dnsDomainIs(host,"*.oracleindustry.com")) || 
                (dnsDomainIs(host,"cegbuger-p6.oracleindustry.com")))) {
                return "PROXY ${COUNTRY_GATEWAY}:80;DIRECT";
                }
```

You can use the `${COUNTRY_GATEWAY}` and `${COUNTRY_SECONDARY_GATEWAY}` variables to determine the closest Public Service Edge in the client's country. For example:

```
return "PROXY ${COUNTRY_GATEWAY}:80; PROXY ${COUNTRY_SECONDARY_GATEWAY}:80";
```

When there is no Public Service Edge configured in your country, the ${COUNTRY_GATEWAY} variable behaves just like the ${GATEWAY} variable.

If your organization uses a subcloud, use the variables `${COUNTRY_GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net}` and `${COUNTRY_SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net}`. For example:

```
return "PROXY ${COUNTRY_GATEWAY.
<Subcloud>.<Zscaler cloud>
.net}:80; PROXY ${COUNTRY_SECONDARY.GATEWAY.
<Subcloud>.<Zscaler cloud>
.net}:80; DIRECT";
```

This prevents clients that are near multiple Public Service Edges or in countries that are close to one another from having their traffic forwarded to the wrong Public Service Edges.

If your organization has many users behind a single egress IP address, you can configure the PAC file to choose from multiple gateway IP addresses within a country to distribute the load. To do this, use the following variables:

The load-balancing PAC variables are designed for non-tunnel traffic. You do not require these variables for load-balancing your tunnel (GRE/IPSec) traffic.

- Country Gateway Index Tokens
- Dynamic Country Gateway Tokens

Zscaler recommends this method if you want the users to be distributed across multiple gateway IP addresses in a data center for load-balancing purposes.

Add the suffixes `_F0` through `_F7` to the `${COUNTRY_GATEWAY}` variable in the PAC file for the PAC server to return the healthy gateway IP addresses within the country through the 8 variables. For example, the `${COUNTRY_GATEWAY_F0}` variable corresponds to the first healthy IP address available, the `${COUNTRY_GATEWAY_F1}` variable corresponds to the second healthy IP address, and so on.

Use the following syntax to include the `${COUNTRY_GATEWAY_F0}` variable in your PAC file:

```
return "PROXY ${COUNTRY_GATEWAY_F0}:80; PROXY ${COUNTRY_SECONDARY_GATEWAY_F0}:80; DIRECT";
```

If the data center has fewer than 8 healthy gateways, then the PAC server allocates the available healthy gateway IP addresses to all 8 variables in a round-robin fashion.

If your organization uses a subcloud, you can use the country gateway host index tokens (`_F0`through`_F7`suffix) with the subcloud variables. For example, `${COUNTRY_GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_F1}` and `${COUNTRY_SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_F1}`.

Use the suffix `_FX` to the `${COUNTRY_GATEWAY}` variable in the PAC file for the PAC server to dynamically issue the gateway IP addresses within a country based on the client fingerprints (all users coming from a single egress IP address are given an IP address from a pool of healthy gateway IP addresses). The fingerprint is used to ensure that a single device continues its session to the same gateway IP address.

Use the following syntax to include the `${COUNTRY_GATEWAY_FX}`variable in your PAC file:

```
return "PROXY ${COUNTRY_GATEWAY_FX}:80; PROXY ${COUNTRY_SECONDARY_GATEWAY_FX}:80; DIRECT";
```

You can also use the `_FX` suffix with the subcloud variables. For example, `${COUNTRY_GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_FX}` and `${COUNTRY_SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_FX}`.

The `_FX` suffix provides load balancing for multiple gateway IP addresses depending on the HTTP headers (useragent, x-forwarded-for, and z-client). This variable is effective only for Zscaler Client Connector clients because the z-client ID is different for each user. To implement load balancing for non-Zscaler Client Connector clients, you can use the Country Gateway Index Tokens. To learn more, see [Load Balancing for PAC Forwarded Traffic](https://help.zscaler.com/zia/load-balancing-pac-forwarded-traffic).

You can use the `${COUNTRY_GATEWAY_HOST}`variable to determine the closest Public Service Edge in the client's country using the hostname instead of an IP address.

Zscaler recommends this setting only for Kerberos authentication.

Use the following syntax to include the `${COUNTRY_GATEWAY_HOST}`variable in your PAC file:

```
return "PROXY ${COUNTRY_GATEWAY_HOST}:80; PROXY ${COUNTRY_SECONDARY_GATEWAY_HOST}:80; DIRECT";
```

When there is no Public Service Edge configured in your country, the ${COUNTRY_GATEWAY_HOST} variable behaves just like the ${GATEWAY_HOST} variable.

If your organization uses a subcloud, use the variables `${COUNTRY_GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST}` and `${COUNTRY_SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST}`. For example:

```
return "PROXY ${COUNTRY_GATEWAY.
<Subcloud>
.
<Zscaler cloud>
.net_HOST}:80; PROXY ${COUNTRY_SECONDARY.GATEWAY.
<Subcloud>
.
<Zscaler cloud>
.net_HOST}:80; DIRECT";
```

If your organization has many users behind a single-egress IP address, you can configure the PAC file to choose from multiple gateway hosts within a country to distribute the load. To do this, use the following variables:

The load-balancing PAC variables are designed for non-tunnel traffic. You do not require these variables for load-balancing your tunnel (GRE/IPSec) traffic.

- Country Gateway Host Index Tokens
- Dynamic Country Gateway Host Tokens

Zscaler recommends this method if you want the users to be distributed across multiple gateway hosts in a data center for load-balancing purposes.

Add the suffixes`_F0` through `_F7` to the `${COUNTRY_GATEWAY_HOST}` variable in the PAC file for the PAC server to return the healthy gateway hosts within the country through the 8 variables. For example, the `${COUNTRY_GATEWAY_HOST_F0}` variable corresponds to the first healthy host that is available, the `${COUNTRY_GATEWAY_HOST_F1}` variable corresponds to the second healthy host, and so on.

Use the following syntax to include the `${COUNTRY_GATEWAY_HOST_F0}` variable in your PAC file:

```
return "PROXY ${COUNTRY_GATEWAY_HOST_F0}:80; PROXY ${COUNTRY_SECONDARY_GATEWAY_HOST_F0}:80; DIRECT";
```

If the data center has fewer than 8 healthy gateways, then the PAC server allocates the available healthy gateway hosts to all 8 variables in a round-robin fashion.

If your organization uses a subcloud, you can use the country gateway host index tokens (`_F0`through`_F7`suffix) with the subcloud variables. For example, `${COUNTRY_GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST_F1}` and `${COUNTRY_SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST_F1}`.

Use the suffix `_FX` to the `${COUNTRY_GATEWAY_HOST}` variable in the PAC file for the PAC server to dynamically issue the gateway hosts within a country based on the client fingerprints, i.e., all users from a single egress IP address are given a gateway host from a pool of healthy gateway hosts. The fingerprint is used to ensure that a single device continues its session on the same gateway host.

Use the following syntax to include the `${COUNTRY_GATEWAY_HOST_FX}`variable in your PAC file:

```
return "PROXY ${COUNTRY_GATEWAY_HOST_FX}:80; PROXY ${COUNTRY_SECONDARY_GATEWAY_HOST_FX}:80; DIRECT";
```

You can also use the `_FX` suffix with the subcloud variables. For example, `${COUNTRY_GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST_FX}` and `${COUNTRY_SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST_FX}`.

The `_FX` suffix provides load balancing for multiple gateway hosts depending on the HTTP headers (useragent, x-forwarded-for, and z-client). This variable is effective only for Zscaler Client Connector clients because the z-client ID is different for each user. To implement load balancing for non-Zscaler Client Connector clients, you can use the Country Gateway Host Index Tokens. To learn more, see [Load Balancing for PAC Forwarded Traffic](https://help.zscaler.com/zia/load-balancing-pac-forwarded-traffic).

You can use the `${TRUSTED_NETWORK}` variable to determine Zscaler Client Connector's current network type. The following values correspond to the supported network types: `ON_TRUSTED`, `OFF_TRUSTED`, `VPN_TRUSTED`, and `SPLIT_VPN_TRUSTED`. These values are taken only if the variable is used in an APP Profile PAC file.

If there is no network type available or if the variable is used in a forwarding profile or Proxy PAC file, then the variable takes the value `NOT_AVAILABLE`.

For example:

```
var network = "${TRUSTED_NETWORK}";
                if (network == "ON_TRUSTED")
                    return "PROXY ${GATEWAY_FX}:12345; PROXY ${SECONDARY_GATEWAY_FX}:12345; DIRECT";
                else if (network == "OFF_TRUSTED")
                    return "PROXY ${GATEWAY_FX}:${ZS_CUSTOM_PORT}; PROXY ${SECONDARY_GATEWAY_FX}:${ZS_CUSTOM_PORT}; DIRECT";
```

This variable is applicable only if you use Zscaler Client Connector version 4.2.0 or later.

To use this variable, enable the **Use Preferred Port from PAC for Z-Tunnel 1.0** or the **Use Preferred Port from PAC for Z-Tunnel 2.0** option in the [Zscaler Client Connector Windows profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) policy rule.

You can use the`${ZS_CUSTOM_PORT}` variable to return an appropriate custom port based on your country and the Z-Tunnel version. For example, if you are in China, then the variable is replaced with port 9401 for Z-Tunnel 2.0 requests and 9400 for Z-Tunnel 1.0 requests. The ports are subject to change.

However, if you are in a country other than China, then the variable is replaced with port 443 for Z-Tunnel 2.0 and Z-Tunnel 1.0 requests.

Use the following syntax to include the `${ZS_CUSTOM_PORT}` variable in your PAC file:

```
return "PROXY ${GATEWAY_FX}:${ZS_CUSTOM_PORT}; PROXY ${SECONDARY_GATEWAY_FX}:${ZS_CUSTOM_PORT}; DIRECT";
```

This variable is applicable only if you use Zscaler Client Connector version 4.2.0 or later.

You can use the `${ZT2_REQUEST}`variable to determine the Z-Tunnel version for which the PAC file is requested. This variable takes the value `true` if the request is for Z-Tunnel 2.0 or the value `false` if the request is for Z-Tunnel 1.0.

In this example, port 9981 is used for Z-Tunnel 2.0 requests and port 9980 is used for Z-Tunnel 1.0 requests.

```
var tunnel2 = "${ZT2_REQUEST}";
                if (tunnel2 == "true")
                    return "PROXY ${GATEWAY_FX}:9981; PROXY ${SECONDARY_GATEWAY_FX}:9981; DIRECT";
                else
                    return "PROXY ${GATEWAY_FX}:9980; PROXY ${SECONDARY_GATEWAY_FX}:9980; DIRECT";
```

```
function FindProxyForURL(url, host)
                {
                //
                //Exclude FTP from proxy
                //
                if (url.substring(0, 4) == "ftp:")
                {
                return "DIRECT";
                }
                //
                //Bypass proxy for internal hosts
                //
                if (isInNet(host, "0.0.0.0", "255.0.0.0")||
                isInNet(host, "10.0.0.0", "255.0.0.0") ||
                isInNet(host, "127.0.0.0", "255.0.0.0") ||
                isInNet(host, "169.254.0.0", "255.255.0.0") ||
                isInNet(host, "172.16.0.0", "255.240.0.0") ||
                isInNet(host, "192.0.2.0", "255.255.255.0")||
                isInNet(host, "64.206.157.136", "255.255.255.255"))
                {
                return "DIRECT";
                }
                //
                //Bypass proxy for this server
                //
                if (dnsDomainIs(host, "mail.domain.com"))
                {
                return "DIRECT";
                }
return "PROXY ${SECONDARY_GATEWAY_FX}:9400; PROXY ${GATEWAY_FX}:9400; DIRECT";
}
```

[Image: Default PAC Files menu in the Zscaler Admin Console]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zia/zia-error-codes-kerberos-authentication","lastmod":"2026-10-30T08:40Z","nid":"1399596"} -->
## Internet & SaaS Error Codes for Kerberos Authentication

- Source: https://help.zscaler.com/zia/zia-error-codes-kerberos-authentication
- Product: Internet & SaaS (ZIA)
- Path: Internet & SaaS (ZIA) Help > Authentication & Administration > User Management & Authentication Settings > Kerberos Authentication > Internet & SaaS Error Codes for Kerberos Authentication
- Last modified: 2026-10-30T08:40Z
- Summary: Information on the cause and solutions to several Public Service Edge for Internet & SaaS error codes for Kerberos authentication.

If Kerberos authentication fails, the Zscaler service displays a page with an error code. The following table lists the information on the Kerberos authentication error codes. To learn how to troubleshoot Kerberos, see [Troubleshooting Kerberos Authentication](https://help.zscaler.com/zia/troubleshooting-kerberos-authentication).

| Error Code | Description | When It Occurs | What to Do |
| --- | --- | --- | --- |
| 391000 | The page cannot load. | Generic error code. | Contact Zscaler Support. |
| 441000, 461000 | The user cannot be found. | The user was deleted or cannot be found in the registered domain. | Add the user. |
| 451000 | The domain does not exist. | The realm is not a registered domain on the Zscaler service. | Contact Zscaler Support to add the realm as a registered domain. |
| 471000 (Occurrence 1) | The proxy authorization header does not contain the encoded Kerberos ticket. In almost all cases, this means that NTLM was used. | The traffic was sent to the Public Service Edge for Internet & SaaS (ZIA) IP address. The default Zscaler PAC file was used instead of the Kerberos PAC file. **Symptoms**: klist does not show either the Public Service Edge or KDC tickets. | Change the PAC file. Ensure that you use the Kerberos PAC file. |
| 471000 (Occurrence 2) |  | The user's computer does not have the GPO updates, the GPO updates are not on the domain controller, or the user is not logged in to the domain. **Symptoms**: A traffic capture from the user's computer does not show any query to the domain controller for the Zscaler ticket. | Verify the registry settings to ensure the user's computer has the Zscaler Kerberos settings. If they are not on the computer, verify that the domain controller has the GPO configurations. Verify that the user is logged in to the domain. |
| 471000 (Occurrence 3) |  | AES encryption is not enabled in the realm trust on the domain controller. **Symptoms**: klist does not show either the Public Service Edge or KDC tickets. A traffic capture shows the domain controller returning ETYPE_NOSUPP errors. | Modify the realm trust relationship and ensure that the AES encryption option is selected. |
| 471000 (Occurrence 4) |  | The realm trust relationship is not configured on the domain controller or the realm trust was configured with an incorrect password. **Symptoms**: klist does not show the Zscaler KDC or Public Service Edge tickets. A traffic capture shows the domain controller returning PRINCIPAL_UNKNOWN error. | Verify the realm trust configuration. Ensure that the realm name is in upper case and that the Zscaler cloud name is correct. To learn how you can find your cloud name, see [Understanding Zscaler Cloud Names](https://help.zscaler.com/unified/understanding-zscaler-cloud-names). If they are both correct, then the passwords in the organization's realm and the Zscaler realm might not match. Log in to the Zscaler Admin Console, regenerate the trust password, and copy it. On the domain controller, delete the trust configuration and create a new one as described in [Kerberos Trust Relationship Configuration for Windows Server and GPO Push](https://help.zscaler.com/zia/kerberos-trust-relationship-configuration-guide-windows-server-gpo-push#create-new-trust). Ensure that you use the newly generated password that you copied from the Zscaler Admin Console. |
| 471000 (Occurrence 5) |  | The user's computer cannot connect to the domain controller. **Symptoms**: When you run klist purge to clear the tickets, refresh the browser, and then run klist again, Kerberos tickets are not displayed, including those of the domain controller. | Verify that the computer can contact the domain controller. If the user is using DirectAccess, run netsh name show effectivepolicy to determine if the DirectAccess client considers the workstation as being in the intranet or outside. |
| 48100 | Invalid encoding of Kerberos credentials. | Possible header corruption. Ensure that there is no intermediate proxy or L7 device that might be corrupting the header. | Contact Zscaler Support. |
| 491000, 501000 (Occurrence 1) | Invalid Kerberos token or username. | The computer time is incorrect. | Kerberos is very sensitive to clocks being in sync. Ensure that computer time is correct and it synchronizes with an NTP server. |
| 491000, 501000 (Occurrence 2) |  | Other issues. | Contact Zscaler Support. |
| 510000 | The user name is too long. | The user's login name exceeds the maximum allowed characters. | Contact Zscaler Support. |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zia/zscaler-authentication-bridge-server-certificate-renewal-process","lastmod":"2026-05-07T21:06Z","nid":"1402261"} -->
## Zscaler Authentication Bridge Server Certificate Renewal Process

- Source: https://help.zscaler.com/zia/zscaler-authentication-bridge-server-certificate-renewal-process
- Product: Internet & SaaS (ZIA)
- Path: Internet & SaaS (ZIA) Help > Authentication & Administration > User Management & Authentication Settings > Zscaler Authentication Bridge > Zscaler Authentication Bridge Server Certificate Renewal Process
- Last modified: 2026-05-07T21:06Z
- Summary: Information on how to renew the Zscaler Authentication Bridge server certificate when it expires.

A server certificate that is uploaded onto the [Zscaler Authentication Bridge (ZAB)](https://help.zscaler.com/zia/about-zscaler-authentication-bridge) will eventually expire. When the certificate expires, it can be renewed using the following method:

- Step 1: Create a Directory on the ZAB
- Step 2: Verify that the Files are on the ZAB
- Step 3: Perform a Test and Validate the New ZAB Server Certificate

1. Create a directory on the ZAB to host the certificate files.
2. SCP into the ZAB and copy the certificate files to `var/cacert`.

- Server Certificate - `example.pem`
- Certificate Key - `example.key`
- Root Chain - `example.pem`

1. Validate that the files are copied onto the ZAB.
2. During the maintenance window, install the certificate files from the directory. To learn more, see [Deploying a Zscaler Authentication Bridge](https://help.zscaler.com/zia/deploying-zscaler-authentication-bridge#cert-signed-trusted-cert-auth).
3. Restart the ZAB service and validate the ZAB status.

1. After the new certificate files are uploaded to the ZAB and services are started, establish the SSL connection to the ZAB by using OpenSSL. Validate that the new certificate is being returned by the ZAB and look for the validity period of the certificate returned. Then, get redirected to the ZAB.
2. Test the browser and ensure that there are no certificate errors on the ZAB authentication page.
3. Configure the browser proxy settings to `gateway.<Zscaler cloud name>.net:9443`and add the ZAB hostname to proxy bypass. To learn more, see [Understanding Zscaler Cloud Names](https://help.zscaler.com/unified/understanding-zscaler-cloud-names). With proxy settings in place, access any internet page to get redirected for authentication. On the Zscaler authentication page, use a dummy `user@domain.com` account to get redirected to ZAB.
4. Sign in and validate the new certificate. After clicking **Sign In**, you will be redirected to the ZAB for authentication. Ensure the internet page loads without any certificate errors.
5. Validate that the new certificate is being presented on the ZAB authentication page.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zia/zscaler-dns-security-and-control-0","lastmod":"2025-09-04T09:51Z","nid":"1451391"} -->
## Zscaler DNS Security and Control

- Source: https://help.zscaler.com/zia/zscaler-dns-security-and-control-0
- Product: Internet & SaaS (ZIA)
- Path: Internet & SaaS (ZIA) Help > Reference Architecture > Zscaler DNS Security and Control
- Last modified: 2025-09-04T09:51Z
- Summary: The Zscaler DNS Security and Control reference architecture guide steers you through the architecture process, and provides technical deep dives into specific platform functionality and integrations.

The Zscaler Reference Architecture series delivers best practices based on real-world deployments. The recommendations in this series were developed by Zscaler's transformation experts from across the company. This guide will steer you through the architecture process and provide technical deep dives into specific platform functionality and integrations. The Zscaler Reference Architecture series is designed to be modular, so this guide will show you how to configure a different aspect of the platform in order to allow you meet your specific policy goals.

Zscaler DNS Security and Control services offer mechanisms to take control of your DNS architecture and response. By proxying the DNS request, you can enforce your organization’s DNS policies in the Zscaler Zero Trust Exchange (ZTE). When the DNS request reaches the ZTE, the request is open and inspected. No DNS requests can bypass inspection unless you authorize it, as you can restrict your users to only using DNS servers you specify.

[Zscaler DNS Security and Control: Reference Architecture Guide](https://help.zscaler.com/downloads/zia/reference-architecture/zscaler-dns-security-and-control-0/zscaler-dns-security-and-control-reference-architecture.pdf) [Download PDF](https://help.zscaler.com/downloads/zia/reference-architecture/zscaler-dns-security-and-control-0/zscaler-dns-security-and-control-reference-architecture.pdf)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zia/zscaler-endpoint-dlp-release-summary-2026","lastmod":"2026-10-26T09:35Z","nid":"1535357"} -->
## Zscaler Endpoint DLP Release Summary (2026)

- Source: https://help.zscaler.com/zia/zscaler-endpoint-dlp-release-summary-2026
- Product: Internet & SaaS (ZIA)
- Path: Internet & SaaS (ZIA) Help > Release Notes > Zscaler Endpoint DLP Release Notes > Zscaler Endpoint DLP Release Summary (2026)
- Last modified: 2026-10-26T09:35Z
- Summary: Zscaler Endpoint DLP release summary for updates deployed, per OS and version, in 2026.

This article provides a summary of all new features and enhancements released per operating system (OS) for Zscaler Endpoint DLP.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zia/zscaler-traffic-bypasses","lastmod":"2026-06-02T23:36Z","nid":"1440656"} -->
## Zscaler Traffic Bypasses

- Source: https://help.zscaler.com/zia/zscaler-traffic-bypasses
- Product: Internet & SaaS (ZIA)
- Path: Internet & SaaS (ZIA) Help > Traffic Forwarding > Zscaler Traffic Bypasses
- Last modified: 2026-06-02T23:36Z
- Summary: Information on traffic bypasses that are available in the Zscaler cloud.

This article provides detailed information about the types of traffic bypasses available in the Zscaler cloud. The following sections illustrate how you can bypass certain application traffic or web traffic in the Zscaler cloud.

- Zscaler Client Connector
- SSL/TLS Inspection
- Firewall Control Policy

Zscaler Client Connector has an Application Bypass feature that allows you to automatically bypass specific applications from being tunneled through Zscaler Tunnel (Z-Tunnel) 2.0. You need to add the applications that you want to bypass in the **Application Bypass** field while configuring the app profiles in the Zscaler Admin Console. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).

This application bypass is only applicable for [Windows](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles?referer=mobileadmin.zscalerbeta.net#windows) and [macOS](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles?referer=mobileadmin.zscalerbeta.net#macOS) app profiles and if you use Z-Tunnel 2.0 as your forwarding profile in Zscaler Client Connector. You can also view the details of the bypassed applications on the [Application Bypass](https://help.zscaler.com/zscaler-client-connector/viewing-information-bypassed-applications-z-tunnel-2.0-configuration) page.

Full visibility of the traffic bypassed by Zscaler Client Connector, such as bypassed session, bypassed session event time, and flow type is logged and displayed in the [Web](https://help.zscaler.com/zia/web-insights-logs-columns) and [Firewall](https://help.zscaler.com/zia/firewall-insights-logs-columns) Insights Logs in the Zscaler Admin Console.

See sample Web Logs.

See sample Firewall Logs.

## Application-Based Bypass in Android

Zscaler recommends bypassing Android application traffic. While configuring the [Android app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles?referer=mobileadmin.zscalerbeta.net#android), you can automatically bypass standard messaging applications on Android using the following steps:

1. Enable the **Bypass Traffic for MMS Applications** field to ensure that Android does not interfere with MMS, which also uses mobile data.
2. Add the app's identifier to the **Bypass Traffic for Specific Applications** field to bypass specific Android application traffic. You can find the app's identifier after the ID parameter in the URL of the app's Play Store details. For custom applications (i.e., applications not available in the Play Store), add the package name instead of the app’s identifier in the field.

If you are not aware of the package name of the application, use PAC file-based exclusion to bypass specific hostnames and IP addresses. To do that, configure a [custom PAC file](https://help.zscaler.com/zia/using-custom-pac-file-forward-traffic-internet-saas) in the Zscaler Admin Console to bypass the Google FQDNs, and add that PAC URL to the **Custom PAC URL** field while adding an Android app profile.

The Zscaler exception list for SSL/TLS inspection includes a few dozen known domains or destinations, such as Zscaler service IP addresses for Zscaler best practices, `contactservice.zoom.us` for UCaaS bypass, and `self.events.data.microsoft.com` for Microsoft 365 bypass events, which cannot be SSL/TLS inspected for various reasons. These domains are not exposed as they are subject to change.

The predefined **Zscaler Recommended Exemptions** rule under **Policies** >**Common Configuration**>**SSL/TLS Inspection**>**SSL/TLS Inspection Policy**automatically exempts known destinations that cannot be SSL/TLS inspected when it is enabled. If you want to SSL/TLS inspect certain domains from the exempted list, create an inspection rule with a higher rule order. Disable the predefined rule for SSL/TLS inspecting all domains. To learn more, see [About SSL/TLS Inspection Policy](https://help.zscaler.com/zia/about-ssltls-inspection-policy).

You can find transactions that are not SSL/TLS inspected or blocked after an inspection under the **SSL/TLS Policy Reason** filter in [Web Insights Logs](https://help.zscaler.com/zia/web-insights-logs-columns). The percentage of traffic automatically SSL/TLS bypassed is relatively small (less than 1%).

See sample Web Logs.

SSL/TLS inspection also does not work on applications that use [certificate pinning](https://help.zscaler.com/zia/certificate-pinning-and-ssltls-inspection) because the client application is hardcoded to accept only one specific client certificate. They should be included in the list of URL categories for which SSL/TLS transactions are not decrypted. To learn how to manage certificate pinning exempted applications, see [Certificate Pinning and SSL/TLS Inspection](https://help.zscaler.com/zia/certificate-pinning-and-ssltls-inspection).

The following are some of the Firewall Control policy rules applied to traffic that is bypassing the Zscaler cloud:

- Zscaler Bypass Traffic
- Blocked by Web Proxy Policy
- Other Implicit Rules for Traffic Bypasses

The Firewall Control policy has a predefined implicit **Zscaler Bypass Traffic**rulewhich applies to traffic that matches the following conditions. Transactions that match this rule are logged and displayed along with the rule name in [Firewall Insights Logs](https://help.zscaler.com/zia/firewall-insights-logs-columns) and [DNS Insights Logs](https://help.zscaler.com/zia/dns-insights-logs-columns).

- Firewall Logs
- DNS Logs

Zscaler recommends keeping the predefined **Zscaler Proxy Traffic** rule enabled. When the recommended predefined rule is disabled, the **Zscaler Bypass Traffic** rule is triggered.

The **Zscaler Bypass Traffic** rule matches with traffic that meets the following criteria and populates in the Firewall Logs:

- The web module does not accept control for the traffic evaluation from the firewall module because the handshake abruptly terminates during flow establishment.
- The traffic is forwarded from a firewall-enabled sublocation but not enabled to the parent location or another sublocation under the same parent location. This happens due to the latency in identifying these location differences, such as in the X-Forwarded-For (XFF) configuration.
- Traffic is destined to a Zscaler-owned IP address. For HTTP CONNECT requests destined to a virtual IP (VIP) address, this rule triggers the first policy match unless the inner traffic is destined elsewhere.
- Traffic forwarded using GRE tunnels matches this rule.
- Zscaler web proxy instructs the firewall that a connection should not pass through firewall policy enforcement.
- A sublocation has firewall disabled while other sublocations within the same parent location have it enabled, and there are policies applied to the sublocation (e.g., [Source IP Anchoring](https://help.zscaler.com/zia/configuring-source-ip-anchoring) or [Forwarding rules](https://help.zscaler.com/zia/configuring-forwarding-control-policy)). In this scenario, the Public Service Edge for Internet & SaaS (ZIA) holds the session temporarily to evaluate all the sublocation's policies and apply the most suitable rule, logging the traffic with the Zscaler Bypass Traffic rule until evaluation is complete.

See sample Firewall Logs.

The Zscaler Bypass Traffic rule populates in the DNS logs when:

- The domain name in the DNS request query matches a Zscaler cloud domain.
- The DNS request query matches a Microsoft 365 endpoint listed in the [Microsoft 365 One Click predefined Firewall Filtering rules](https://help.zscaler.com/zia/about-predefined-firewall-filtering-rules#office-one-click) if enabled.
- The DNS response does not contain a resolved IP address or CNAME.
- The DNS response is not completely analyzed because of its resource record type. DNS Control performs a detailed analysis of responses for A, AAAA, CNAME, and PTR record types.

See sample DNS Logs.

This implicit rule is applied when a web policy blocks traffic while it is being evaluated by deep packet inspection to identify the network application to which the traffic belongs. You can view the transactions that match this rule in the [Firewall Insights Logs](https://help.zscaler.com/zia/firewall-insights-logs-columns).

The traffic flow from users might bypass firewall or DNS modules when the following scenarios occur:

- When the Service Edge fails to establish a connection with the Zscaler Central Authority (CA), it results in the traffic flow passing through the firewall or DNS without a policy application. This might occur when traffic flow from a specific user or location arrives at the Service Edge for the first time and a connection to the CA is required to apply policies. Firewall logs this transaction, and you can view this in [Firewall Insights Logs](https://help.zscaler.com/zia/firewall-insights-logs-filters) by applying the **Bypassed due to missing config**filter. See sample Firewall Insights Logs.
- If the Service Edge has established a connection with the CA, but the requested configuration does not arrive from the CA within the expected time period (typically 5 seconds), it results in the traffic flow passing through the firewall without a policy application. This might occur when traffic flow from a specific user or location arrives at the Service Edge for the first time and a connection to the CA is established, but there is no response from the CA within the expected timeframe. Firewall logs this transaction, and you can view this in [Firewall Insights Logs](https://help.zscaler.com/zia/firewall-insights-logs-filters) by applying the **Timed out while waiting for a config** filter. <p> <a class="image-icon" href="#timed-out-while-waiting-for-config-filter-logs">See sample Firewall Logs.</a> </p>

[Image: Web Logs traffic bypass columns]

[Image: Firewall Logs traffic bypass columns]

[Image: Web Logs page for SSL bypassed traffic]

[Image: Zscaler Bypass Traffic in the DNS Logs]

[Image: Firewall Logs page for Zscaler Bypass Traffic rule]

[Image: Bypassed due to missing config filter in Firewall Logs]

<div class="subc"> <p> <a class="ck-anchor" id="timed-out-while-waiting-for-config-filter-logs"></a><img src="/downloads/zia/traffic-forwarding/zscaler-traffic-bypasses/firewall-logs-timed-out-while-waiting-for-config.png" data-entity-uuid="0" data-entity-type="image" alt=" Timed out while waiting for config filter in Firewall Logs" title="Firewall logs filtered to show transactions matching Timed out while waiting for config action " width="1425" height="234"> </p> </div>
<!-- /ZS-ARTICLE -->
