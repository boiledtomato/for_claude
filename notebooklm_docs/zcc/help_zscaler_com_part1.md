# ZCC (Zscaler Client Connector) ドキュメント — help.zscaler.com

---

<!-- source: https://help.zscaler.com/soc-workbench/configuring-zscaler-client-connector-devices-connector -->

Title: Configuring the Zscaler Client Connector Devices Connector | Zscaler

URL Source: https://help.zscaler.com/soc-workbench/configuring-zscaler-client-connector-devices-connector

Markdown Content:
![Image 1: icon-soc.svg](blob:http://localhost/f4ce189bfe333280ca5520f472798704)

## SOC Workbench

### Configuring the Zscaler Client Connector Devices Connector

Zscaler Client Connector is an application deployed on an end user device that automatically forwards all user traffic through the Zscaler Zero Trust Exchange (ZTE) to enforce policy and access controls while improving performance.

The Zscaler Client Connector Devices connector retrieves data on assets in your organization, including which Zscaler products are installed on which assets.

## Prerequisites

To configure the Zscaler Client Connector Devices connector, you need the source authentication configuration. The following parameters are required:

*   [Client ID and Secret Key](https://help.zscaler.com/soc-workbench/configuring-zscaler-client-connector-devices-connector#parameter1)

Before getting started, you must enable the API for your organization to obtain access to the Zscaler Client Connector API by contacting Zscaler Support. To create an API token, the admin must be assigned a role with full access to the public API resource in the Zscaler Client Connector Portal (Administration > Administration Management > Role Management).

To retrieve the API key and secret key:

    1.   Log in to the Zscaler Client Connector Portal.
    2.   Go to **Administration**>**Public API**.
    3.   Click **Add API Key**.
    4.   In the **Add API Key** window:
        *   **Name**: Enter a name for the API key. The name must be alphanumeric, cannot contain spaces, and has a maximum of 50 characters.
        *   **Status**: By default, **Enabled** is selected. If disabled, the key is unavailable to use.
        *   **Role**: Select **Read** access for the key.
        *   **Session Validity Interval (In Seconds)**: Enter the amount of time the key is available to use. The recommended session length is at least 43,200 seconds, which is equal to 12 hours.

    5.   Click **Save** to generate the client ID and client secret.
    6.   Copy the client secret and close the **Add API Key** window.

Store the client secret securely because it is only available when creating an API key. It is not available after you close the window.

    7.   On the **Public API** page, copy the **Client ID**.

[Close](https://help.zscaler.com/soc-workbench/configuring-zscaler-client-connector-devices-connector#parameter1)

*   [Cloud Name](https://help.zscaler.com/soc-workbench/configuring-zscaler-client-connector-devices-connector#parameter2)Your cloud name is located in the URL that the admin uses to log in to the Zscaler service. For example, if your organization logs into admin.zscalerbeta.net, then your organization's cloud name is zscalerbeta.net. To learn more, see [What Is My Cloud Name for ZIA?](https://help.zscaler.com/zia/what-my-cloud-name-zia)

![Image 2: The Zscaler cloud name in the URL of your Zscaler portal](https://help.zscaler.com/downloads/uvm/administration/connectors/sources/source-configuration-guides/configuring-zscaler-client-connector-devices-connector/zscaler-cloud-name.png) 
[Close](https://help.zscaler.com/soc-workbench/configuring-zscaler-client-connector-devices-connector#parameter2) 

To create the Zscaler Client Connector Devices data source in the Zscaler Security Operations (SecOps) platform:

1.   Go to **Configure**>**Sources**.
2.   Click **Create**.
3.   Search for and select the connector tile from the available data sources.

## Configuring Retrieval Filters and Specifications

After you create the Zscaler Client Connector Devices data source, enter the Authentication information in the source setup Retrieval section. To configure authentication:

1.   In the **Authentication** section, click **Create New**.

The **ZCC Authentication** window appears.

2.   In the **ZCC Authentication** window, enter the information from the [Prerequisites](https://help.zscaler.com/soc-workbench/configuring-zscaler-client-connector-devices-connector#prereqs) section.

3.   Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos -->

Title: Deploying Zscaler Client Connector with Workspace ONE UEM for macOS | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Deploying Zscaler Client Connector with Workspace ONE UEM for macOS

This guide is for admins only. If you are an end user, contact your organization’s administrator for deployment-related details.

With Workspace ONE Unified Endpoint Management (UEM), you can deploy Zscaler Client Connector for your macOS devices. The version used for the following steps is Workspace ONE UEM 26.2.1396.6 (2602) SaaS.

*   [Step 1: Download the Zscaler Client Connector .pkg file](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#download-pkg-file)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos)To deploy Zscaler Client Connector using the Workspace ONE UEM Portal, you must obtain a .pkg file from the Zscaler Admin Console.

To download the Zscaler Client Connector .pkg file:

    1.   In the **Zscaler Admin Console**, go to **Infrastructure**>**Common Resources**>**Deployment**>**Platform Releases**.
    2.   On the **New Releases**tab, select **macOS**.
    3.   Download the .pkg file for the version to deploy.

![Image 2: pkg file](https://help.zscaler.com/downloads/zscaler-client-connector/downloading-deployment/deploying-zscaler-client-connector-workspace-one-uem/deploying-zscaler-client-connector-workspace-one-uem-macos/zclient-connector-workspace-macos-app-store.png)

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#download-pkg-file)

*   [Step 2: Deploy Zscaler Client Connector from Workspace ONE UEM](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#deploy-client-connector-workspace-one)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos)To deploy Zscaler Client Connector on your macOS devices from the Workspace ONE UEM Portal:

    1.   Upload the .pkg file to the Workspace ONE Admin Assistant app.

The Workspace ONE Admin Assistant creates .pkg, property list (PLIST), and image files for uploading to the portal.

    2.   In the Workspace ONE UEM Portal, go to **Resources**>**Native Apps**.
    3.   On the **Internal** tab, click **Add** and select **Application File**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#add-application-file)

    4.   On the **Add Application** window, click **Upload**, upload the Zscaler Client Connector .pkg file created using the Workspace ONE Admin Assistant, and click **Continue**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#add-application-window)

    5.   Click **Upload**, upload the PLIST file created using the Workspace ONE Admin Assistant, and click **Continue**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#upload-plist-file)

    6.   On the **Images** tab, upload the Zscaler logo image file created using the Workspace ONE Admin Assistant, and click **Save & Assign**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#upload-image-file)

    7.   Click **Cancel** to not assign the app at this time (because you must first deploy other MDM payloads to the devices) and then click **Save**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#cancel-save)

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#deploy-client-connector-workspace-one)

*   [Step 3: (Optional) Configure a Custom Settings Profile](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#configure-custom-settings)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos)You can use a PLIST file to set values for various configuration keys in the Workspace ONE UEM Portal. To configure a custom settings profile in the Workspace ONE UEM Portal:

    1.   In the Workspace ONE UEM Portal, go to **Resources > Profiles > Add > Add Profile**.
    2.   On the **Add Profile** page, click **macOS**, keep the default **Device** context, and click **Next**.
    3.   Name your profile and enter an optional description.
    4.   In the **Custom Settings** section, click **Add**.
    5.   Enter the following PLIST file and edit [Zscaler configuration parameters](https://help.zscaler.com/zscaler-client-connector/supported-parameters-zscaler-client-connector-macos) and their corresponding values per your organization's needs.

```
<dict>
    <key>PayloadDisplayName</key>
    <string>Client Connector Install Params</string>
	<key>PayloadEnabled</key>
    <true/>
    <key>PayloadIdentifier</key>
    <string>com.zscaler.installparams.7F913555-E08B-4DD9-943F-34B7A38AD81C</string>
    <key>PayloadType</key>
    <string>com.zscaler.installparams</string>
    <key>PayloadUUID</key>
    <string>7F913555-E08B-4DD9-943F-34B7A38AD81C</string>
    <key>PayloadVersion</key>
    <integer>1</integer>
    <key>installation-parameters</key>
	<dict>
		<key>hideAppUIOnLaunch</key>
		<string>0</string>
		<key>deviceToken</key>
		<string></string>
		<key>cloudName</key>
		<string>zscalertwo</string>
		<key>policyToken</key>
		<string></string>
		<key>userName</key>
		<string></string>
		<key>externalRedirect</key>
		<string>false</string>
		<key>strictEnforcement</key>
		<string>0</string>
		<key>userDomain</key>
		<string>zclientpm.com</string>
		<key>launchTray</key>
		<string>1</string>
		<key>enableFips</key>
		<string>0</string>
	</dict>
</dict>
```

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#custom-settings)

    6.   Select **Next**and assign the profile to the Smart Group for deployment.
    7.   Click **Save & Publish** to deploy.

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#configure-custom-settings)

*   [Step 4: (Optional) Configure a Certificate Configuration Profile](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#certificate-config-profile)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos)If you use Internet & SaaS and you want to perform SSL inspection, you must configure a certificate profile to push the Certificate Authority (CA) certificate required for SSL inspection. You can use the default Zscaler CA certificate or a custom Root or Intermediate CA certificate. To learn more, see [Choosing the CA Certificate for SSL Inspection](https://help.zscaler.com/zia/choosing-ca-certificate-ssl-inspection) and [Certificate Pinning and SSL Inspection](https://help.zscaler.com/zia/certificate-pinning-and-ssl-inspection).

To configure a certificate configuration profile:

    *   [(Optional) Download the Zscaler CA Certificate.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#download-zscaler-certificate)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos)To download the certificate:

        1.   In the Zscaler Admin Console, go to **Policies**>**Common Configuration**>**SSL/TLS Inspection**>**SSL/TLS Inspection Intermediate Certificate.**
        2.   Click the **Edit** icon corresponding to the Zscaler Intermediate CA Certificate.

The **View Zscaler Intermediate CA Certificate** window appears.

        3.   In the **View Zscaler Intermediate CA Certificate** window, under the **Root Certificate** field, click **Download**.

The root certificate is downloaded as a ZIP file.

        4.   Navigate to the ZscalerRootCerts.zip file, unzip it, and rename the file extension to .crt.

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#download-zscaler-certificate)

    *   [Configure the Certificate Profile.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#configure-cert-profile)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos)To configure the profile:

        1.   In the Workspace ONE UEM Portal, go to **Resources > Profiles > Add > Add Profile**.
        2.   On the **Add Profile** page, click **macOS**, keep the default **Device** context, and click **Next**.
        3.   Name your profile and enter an optional description.
        4.   In the **Credentials** section, click **Add**.
        5.   Select **Choose File**, upload the ZscalerRootCerts.crt file, and click **Attach Certificate**.
        6.   Enable the **Allow access to all applications** setting.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#upload-credentials)

        7.   Select **Next** and assign the profile to the Smart Group for deployment.
        8.   Click **Save & Publish** to deploy.

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#configure-cert-profile)

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#certificate-config-profile)

*   [Step 5: (Optional) Configure Traffic Interception](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#transparent-proxy)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos)Zscaler Client Connector can intercept traffic locally using either Route-based Interception or Transparent Proxy-based Interception. Transparent Proxy-based Interception uses the macOS Transparent Network Proxy Extension to intercept traffic. The TRPTunnel Network Extension (`com.zscaler.zscaler.TRPTunnel`) is used for traffic interception and application bypass and has the following benefits:

    *   No reliance on routing tables
    *   Better performance
    *   Zscaler Tunnel (Z-Tunnel) 2.0 compatibility with third-party VPN clients
    *   Removes the need for inbound firewall rules

Transparent Proxy-based Interception is supported on Zscaler Client Connector version 4.5.2 and later for macOS Tahoe 26.2 and later.

To configure traffic interception, you must apply the following high-level steps:

    *   [Deploy a Configuration Profile to macOS Devices (Transparent Proxy-based Traffic Interception only)](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#trp-deploy-config-profile)

        1.   Download the [ZCC-TRP-CONFIG mobileconfig file.](https://help.zscaler.com/downloads/zscaler-client-connector/downloading-deployment/deploying-zscaler-client-connector-workspace-one-uem/deploying-zscaler-client-connector-workspace-one-uem-macos/ZCC-TRP-CONFIG.mobileconfig)

If you are deploying Zscaler Client Connector to devices running macOS releases older than macOS Sequoia, review the commented sections in the file and edit as required.

        2.   In the Workspace ONE UEM Portal, go to **Resources > Profiles > Add > Upload Profile**.
        3.   Select **macOS** and click **Device Profile**.
        4.   Upload the mobileconfig file to Workspace ONE.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#upload-mobile-config)

        5.   Select **Continue** and assign the profile to the Smart Group for deployment.
        6.   Click **Save & Publish** to deploy.

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#trp-deploy-config-profile)

    *   [Create a Forwarding Profile and Enable Z-Tunnel 2.0](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#trp-create-forwarding)

        1.   [](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos)Create a [forwarding profile](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).
        2.   Select Z-Tunnel 2.0 when configuring a forwarding profile with Tunnel mode and the packet filter driver is enabled.
        3.   Configure bypasses for Z-Tunnel 2.0 in [Zscaler Client Connector profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#mac-IP). To learn more, see [Best Practices for Adding Bypasses for Z-Tunnel 2.0](https://help.zscaler.com/zscaler-client-connector/best-practices-adding-bypasses-z-tunnel-2.0).

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#trp-create-forwarding)

    *   [Create an App Profile](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#trp-app-profile)

        1.   [](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos)Create a [macOS app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#macOS) or edit an existing one.
        2.   Under **General**, select the forwarding profile you configured in the previous step and enable the **Reconnect Tunnel on System Wakeup** option.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#enable-reconnect-tunnel-app-profile)

        3.   If you are configuring Transparent Proxy-based Traffic Interception, under **Traffic Steering**>**Advanced**, enable the **Enable Transparent Proxy-based Traffic Interception** option.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#enable-trp-app-profile)

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#trp-app-profile)

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#transparent-proxy)

*   [Step 6: (Optional) Configure Tunnel Parameters](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#config-tunnel-param)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos)You can configure the following tunnel parameters for Zscaler Client Connector with Workspace ONE UEM.

    *   [Prevent DNS Caching](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#prevent-dns-caching)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos)This configuration setting is only supported with Route-based Traffic Interception and is not compatible with Transparent Proxy-based Traffic Interception.

You can use a property list (PLIST) file to set tunnel parameters, allowing DNS caching to be cleared from users’ devices.

To configure tunnel parameters:

        1.   In the Workspace ONE UEM Portal, go to **Resources**>**Profiles**>**Add**>**Add Profile**.
        2.   On the **Add Profile** page, click **macOS**, keep the default **Device** context, and click **Next**.
        3.   Name your profile and enter an optional description.
        4.   In the **Custom Settings** section, click **Add**.
        5.   Enter the following PLIST file.

```
<dict>
    <key>PayloadDisplayName</key>
    <string>Client Connector Tunnel Params</string>
    <key>PayloadEnabled</key>
    <true/>
    <key>PayloadIdentifier</key>
    <string>com.zscaler.tunnelparams.8133D10A-DE5C-47D6-A55E-B20C0821FA42</string>
    <key>PayloadType</key>
    <string>com.zscaler.tunnelparams</string>
    <key>PayloadUUID</key>
    <string>8133D10A-DE5C-47D6-A55E-B20C0821FA42</string>
    <key>PayloadVersion</key>
    <integer>1</integer>
    <key>tunnel-parameters</key>
    <dict>
        <key>clearDnsCacheOnT2Start</key>
        <string>1</string>
    </dict>
</dict>
```

If the**clearDnsCacheOnT2Start**parameter is enabled, on Tunnel restart, DNS caches are cleared from the device. Enter `1` to enable, or `0` to disable.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#tunnel-params)

        6.   Select **Next**and assign the profile to the Smart Group for deployment.
        7.   Click S**ave & Publish** to deploy.

If you are already logged in to Zscaler Client Connector, exit and relaunch the app or click **Restart Service** in the More window of the app.

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#prevent-dns-caching)

    *   [Bring Zscaler Client Connector Window to the Foreground](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#window-foreground)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos)You can use a property list (PLIST) to set tunnel parameters, allowing you to bring the Zscaler Client Connector window to the foreground.

To bring the Zscaler Client Connector window to the foreground:

        1.   In the Workspace ONE UEM Portal, go to **Resources > Profiles > Add > Add Profile**.
        2.   On the **Add Profile** page, click **macOS**, keep the default **Device** context, and click **Next**.

If you already have an existing configuration profile (e.g., the profile you created in [step 3](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#configure-custom-settings)), select the profile and click **Edit**.

        3.   Name your profile and enter an optional description.
        4.   In the **Custom Settings** section, click **Add**.
        5.   Enter the following Custom Settings.

```
<dict>
    <key>PayloadDisplayName</key>
    <string>Client Connector Tunnel Params</string>
    <key>PayloadEnabled</key>
    <true/>
    <key>PayloadIdentifier</key>
    <string>com.zscaler.tunnelparams.5C58A204-FEC8-4415-885E-6BAA37C02E86</string>
    <key>PayloadType</key>
    <string>com.zscaler.tunnelparams</string>
    <key>PayloadUUID</key>
    <string>5C58A204-FEC8-4415-885E-6BAA37C02E86</string>
    <key>PayloadVersion</key>
    <integer>1</integer>
    <key>tunnel-parameters</key>
    <dict>
        <key>zccToForegroundDuringAuth</key>
        <string>1</string>
    </dict>
</dict>
```

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#bring-zcc-to-foreground)

If the **zccToForegroundDuringAuth**parameter is enabled, it brings the Zscaler Client Connector window to the foreground during WebView-based authentication. Enter `1` to enable, or `0` to disable.

        6.   Select **Next**and assign the profile to the Smart Group for deployment.
        7.   Click S**ave & Publish** to deploy.

If you are already logged in to Zscaler Client Connector, exit and relaunch the app or click **Restart Service** in the [More window](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector) of the app.

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#window-foreground)

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#config-tunnel-param)

*   [Step 7: (Optional) Configure a Custom VPN Profile for Private Access VPN (for Legacy Apps)](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#custom-VPN-profile-ZPA-VPN-service)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos)This configuration setting is supported with Route-based Traffic Interception on all supported releases of Zscaler Client Connector for macOS and with Transparent Proxy-based Traffic Interception on Zscaler Client Connector version 4.8 and later for macOS.

You can use the provided mobileconfig file to configure a custom VPN profile for [Private Access VPN (for Legacy Apps)](https://help.zscaler.com/zpa/about-network-connectors):

    1.   Download the [mobileconfig file.](https://help.zscaler.com/downloads/zscaler-client-connector/downloading-deployment/deploying-zscaler-client-connector-workspace-one-uem/deploying-zscaler-client-connector-workspace-one-uem-macos/ZscalerSampleMobileConfigVPN.mobileconfig)

If you are deploying Zscaler Client Connector to devices running macOS releases older than macOS Sequoia, review the commented sections in the file and edit as required.

    2.   In the Workspace ONE UEM Portal, go to **Resources > Profiles > Add > Upload Profile**.
    3.   Select **macOS** and click **Device Profile**.
    4.   Upload the mobileconfig file to Workspace ONE.
    5.   Select **Continue** and assign the profile to the Smart Group for deployment.
    6.   Click **Save & Publish** to deploy.

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#custom-VPN-profile-ZPA-VPN-service)

*   [Step 8: (Optional) Configure Full Disk Access for Endpoint DLP](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#configure-full-disk-access)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos)Zscaler Endpoint Data Loss Prevention (DLP) requires full disk access for proper operation.

To import a preconfigured mobileconfig file for full disk access into the Workspace ONE UEM Portal:

    1.   Download the [mobileconfig file.](https://help.zscaler.com/downloads/zscaler-client-connector/downloading-deployment/deploying-zscaler-client-connector-workspace-one-uem/deploying-zscaler-client-connector-workspace-one-uem-macos/ZscalerSampleMobileConfigZDP.mobileconfig)
    2.   In the Workspace ONE UEM Portal, go to **Resources > Profiles > Add > Upload Profile**.
    3.   Select **macOS** and click **Device Profile**.

You must remove any existing Endpoint DLP MDM profiles before uploading a new profile.

    4.   Upload the mobileconfig file to Workspace ONE UEM.
    5.   Select **Continue** and assign the profile to the Smart Group for deployment.
    6.   Click **Save & Publish** to deploy.

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#configure-full-disk-access)

*   [Step 9: (Optional) Configure Managed Login Items](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#managed-login-items)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos)You can configure managed login items to prevent users from disabling Zscaler Client Connector on their own devices.

To configure managed login items:

    1.   In the Workspace ONE UEM Portal, go to **Resources > Profiles > Add > Add Profile**.
    2.   On the **Add Profile** page, click **macOS**, keep the default **Device** context, and click **Next**.

If you already have an existing configuration profile (e.g., the profile you created in [step 3](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#configure-custom-settings)), select the profile and click **Edit**.

    3.   Name your profile and enter an optional description.
    4.   In the **Login and Background Items** section, click **Add**.
    5.   Click **Add** for each of the following **Rule Types** and **Rule Values**:

**Rule Type****Rule Value**
Bundle Identifier`com.zscaler.tray`
Bundle Identifier Prefix`com.zscaler`
Label`com.zscaler.tray`
Label Prefix`com.zscaler`

    *   **Bundle Identifier**: The bundle ID.
    *   **Bundle Identifier Prefix**: A part of the bundle identifier reflecting the developer or organization.
    *   **Label**: A descriptive name for an app or an element in the app's UI or settings.
    *   **Label Prefix**: Used to group or categorize labels.
    *   **Team Identifier**: A unique identifier for the developer team or organization within the Apple ecosystem. Applies to all of the configured rules. Enter `PCBCQZJ7S7`.
    *   **Rule comment**: (Optional) Enter a comment for the rule.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#managed-login-items-rule)

    1.   Select **Next**and assign the profile to the Smart Group for deployment.
    2.   Click S**ave & Publish** to deploy.

*   [Step 10: (Optional) Configure Process-Based Application Bypasses](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#config-custom-vpn-application-bypass)

Zscaler Client Connector supports configuring process-based application bypasses differently based on whether you intercept traffic using Route-based Interception or Transparent Proxy-based Interception and the Zscaler Client Connector version you use.

| Traffic Interception Type | Zscaler Client Connector Version | Configuration Type |
| --- | --- | --- |
| Transparent Proxy-based | 4.8 and later | Set up in the Zscaler Admin Console |
| 4.7 and earlier | Preconfigured mobileconfig file |
| Route-based | All supported versions | Preconfigured mobileconfig file | 
To add process-based application bypasses for macOS, use one of the following options based on the type of traffic interception and version of Zscaler Client Connector you use:

    *   [Deploy application bypass with Transparent Proxy-based Traffic Interception (Zscaler Client Connector version 4.8 and later)](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#bypass-app-admin-console)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos)To configure applications to bypass in the Zscaler Admin Console:

        1.   Define any applications and process that need to be bypassed. To learn more, see [Adding Process-Based Applications to Bypass Traffic](https://help.zscaler.com/zscaler-client-connector/adding-process-based-applications-bypass-traffic#macos).
        2.   In the Zscaler Admin Console, go to **Infrastructure**>**Connectors**>**Client**>**macOS**.
        3.   On the **App Profiles** tab, click the **Edit** icon on the app profile you [created in step 5](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#trp-app-profile).
        4.   Under **Traffic Steering**>**App and IP Bypass**>**Global Bypasses**, select the applications to bypass from the **Process-Based Application Bypass using AdminUI** drop-down menu.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#vpn-bypass-zac)

        5.   Click **Save**.

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#bypass-app-admin-console)

    *   [Deploy application bypass with Route-based Traffic Interception (all Zscaler Client Connector versions) or Transparent Proxy-based Traffic Interception (Zscaler Client Connector version 4.7 and earlier)](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#bypass-app-mobile-config)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos)Application Bypass is deployed using the VPN payload in a configuration profile and because the VPN payloads are different for Route-based Traffic Interception and Transparent Proxy-based Traffic Interception, the mobileconfig files differ based on the traffic interception type in use.

You can use bundle identifiers for application identification. If your identifier has both a Team ID and a Bundle ID in it, enter them in the format <Team ID>.<Bundle ID>. For example: `<string>UBF8T346G9.com.microsoft.teams</string>`

Where:

        *   `UBF8T346G9` is the Team ID
        *   `com.microsoft.teams` is the Bundle ID

To enter multiple processes or applications, you can add all the bundle identifiers using an array. As an example, to bypass all Zoom processes, modify the downloaded mobileconfig file’s `BypassAppProcesses` key with the following:

```
<key>BypassAppProcesses</key>
<array>
<string>BJ4HAAB9B3.us.zoom.xos</string>
<string>BJ4HAAB9B3.zoom.us.ZoomAudioDevice</string>
</array>
```

If you use Transparent Proxy-based Traffic Interception, you can also use process paths for application identification (e.g., `/opt/homebrew/Cellar/podman/5.7.0/libexec/podman/gvproxy`). Applies only to Zscaler Client Connector version 4.5.2.121 and later for macOS and Zscaler Client Connector version 4.7.0.87 and later for macOS.

To configure a custom settings profile in the Workspace ONE UEM Portal for the applications you want to bypass:

        *   [Step 1: Deploy a Configuration Profile to macOS Devices](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#app-bypass-deploy-config-profile)

            1.   [](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos)Download one of the following files as a starting template, and edit the values in the `<VendorConfig>` section of the file based on your needs.
                *   [ZscalerSampleMobileConfig (Route-based)](https://help.zscaler.com/downloads/zscaler-client-connector/downloading-deployment/deploying-zscaler-client-connector-workspace-one-uem/deploying-zscaler-client-connector-workspace-one-uem-macos/ZscalerSampleMobileConfigApp.mobileconfig)file for Route-based Traffic Interception
                *   [ZscalerSampleMobileConfig (Transparent Proxy-based)](https://help.zscaler.com/downloads/zscaler-client-connector/downloading-deployment/deploying-zscaler-client-connector-workspace-one-uem/deploying-zscaler-client-connector-workspace-one-uem-macos/ZCC-TRP-CONFIG.mobileconfig) file for Transparent Proxy-based Traffic Interception

This mobileconfig file is the same as the file in [step 5](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#trp-app-profile). If you already uploaded that file, you must replace it with this edited version.

            2.   In the Workspace ONE UEM Portal, go to **Resources > Profiles > Add > Upload****Profile**.
            3.   Select **macOS** and click **Device Profile**.
            4.   Upload the mobileconfig file to Workspace ONE UEM.
            5.   Select **Continue** and assign the profile to the Smart Group for deployment.
            6.   Click **Save & Publish** to deploy.

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#app-bypass-deploy-config-profile)

        *   [Step 2: Enable Application Bypass in App Profiles](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#app-bypass-enable)

            1.   [](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos)In the Zscaler Admin Console, go to **Infrastructure**>**Connectors**>**Client**>**macOS**.
            2.   On the **App Profiles** tab, click the **Edit** icon on the app profile you [created in step 5](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#trp-app-profile).
            3.   Under **Traffic Steering**>**App and IP Bypass**>**Global Bypasses**, enable the **Process-Based Application Bypass using MDM** option.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#vpn-bypass-mdm)

            4.   Click **Save**.

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#app-bypass-enable)

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-macos#bypass-app-mobile-config)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-fail-open-settings-zscaler-client-connector -->

Title: Configuring Failover Settings for Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-fail-open-settings-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Configuring Failover Settings for Zscaler Client Connector

There can be situations in which Zscaler Client Connector must automatically disable its Internet Security service and allow users to bypass the app and access the internet directly:

*   Your users might try to access the internet from an airport or a cafe where a captive portal configured on the network requires users to pay or accept an acceptable use policy on the business’s website before connecting. You can configure your app failover settings so that when Zscaler Client Connector detects a captive portal, it automatically disables its services for a specified period, allowing users first to complete the steps necessary to access the network.
*   Zscaler Client Connector might run into issues reaching Zscaler Internet Access (ZIA) Public Service Edges. If so, you can allow users to bypass the app and access the internet directly or, if you prefer, disable users' access to the internet altogether.
*   Zscaler Client Connector might run into issues properly setting up its Zscaler Tunnel (Z-Tunnel) (the lightweight tunnel it uses to forward traffic to ZIA Public Service Edges). If so, you can allow users to bypass the app and access the internet directly or, if you prefer, disable users' access to the internet altogether.

To configure failover settings:

When configuring failover settings, Zscaler recommends setting the captive portal detection to a value that gives users a reasonable amount of time measured from the network change detection to the time they complete entering information requested by the portal.

1.   In the Zscaler Client Connector Portal, go to **Administration**.
2.   In the left-side navigation, go to**Client Connector Support**.
3.   On the **App Fail Over Settings** tab:
    *   **If Captive Portal Detected, Then Disable Web Security for**: Select from the following options:

        *   To enable captive portal detection, enter the number of minutes the app must keep its services disabled after detecting a captive portal. You can enter any value between `1` and `60`. After the specified period, the app enables its services automatically and traffic is forwarded to the Zscaler service.
        *   To disable the captive portal detection, enter `0` (zero). The app does not perform captive portal detection and does not fail over directly.

If you use Zscaler Client Connector version 4.5 and later for Windows, captive portal detection is set up on the app profile. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).

The app continues to attempt to reach the ZIA Public Service Edge in the background and automatically re-enables itself after it successfully reaches the Public Service Edge.

Contact Zscaler Support to enable this feature.

    *   **ZIA Cloud Not Reachable**:Select one of the following options:
        *   **Direct Internet Access**:Users are allowed to bypass the app and access the internet directly.
        *   **Disable Internet Access**:Blocks HTTP/HTTPS traffic. All other traffic is allowed.

    *   **Z-Tunnel Failover**:Select one of the following options:

        *   **Direct Internet Access**:Users are allowed to bypass the app and access the internet directly.
        *   **Disable Internet Access**:Blocks HTTP/HTTPS traffic. All other traffic is allowed.

If you use Zscaler Client Connector version 4.6 and later for Windows or Zscaler Client Connector version 4.8 and later for macOS, you can [set up failover settings on the app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) that take precedence over these settings.

The app continues to attempt to establish the tunnel in the background and automatically re-enables itself after it's successful.

    *   **Notify End User**: Enable this option to display a notification message to users who attempt to access the internet before logging in if Zscaler Client Connector was installed in Strict Enforcement mode. Applies only to Zscaler Client Connector version 4.6 and later for Windows.
    *   **Rate Limit Strict Enforcement Notification**: Enter the number of minutes before Zscaler Client Connector displays the notification message again. The default value is 2 minutes.
    *   **Strict Enforcement Notification**: Enter the message that displays to users. The maximum number of characters is 200.

4.   Click **Save**.

To learn more about other Zscaler Client Connector Support features, see [About Zscaler Client Connector Support](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-support).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/about-network-performance -->

Title: Configuring Traceroute Information Collection for Network Performance | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/about-network-performance

Published Time: Wed, 17 Jun 2026 06:32:05 GMT

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Configuring Traceroute Information Collection for Network Performance

You can use Zscaler Client Connector to collect traceroute information on the connection between devices and the Zscaler cloud, and store the results to check or troubleshoot network performance.

To configure traceroute information collection:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**Network Performance**.
2.   On the **Network Performance** tab, configure the following:

*   **Collect Route Information Every (In Minutes)**:
    *   To enable traceroute: Enter any value from `10` to `1440` minutes. Zscaler Client Connector collects traceroute information for the number of minutes specified.
    *   To disable traceroute: Enter `0`. Zscaler Client Connector will not collect traceroute information.

*   **Collect Route Information on Network Change**:Enable this option to allow Zscaler Client Connector to collect traceroute information when the device changes network states, e.g., moving from Wi-Fi to cellular data.
*   **Collect Route Information on Reported Issues or Exported Logs**: Enable this option to allow Zscaler Client Connector to collect traceroute information when a user reports an issue or exports logs.

When Zscaler Client Connector collects traceroute information, it's stored locally in a log file directory. When a user reports an issue or exports logs, these traceroute log files are included.

![Image 2: Client Connector Network Performance](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/about-machine-tunnels-doc-63184/Network_Performance.png)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/enabling-private-access-partner-logins -->

Title: Enabling Private Access Partner Logins | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/enabling-private-access-partner-logins

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Enabling Private Access Partner Logins

A partner is a separate entity or company that owns a Private Access tenant. You can give partner organizations access to your organization's tenant. You can also allow your users to access a partner organization's tenant. A partner organization must have these same settings configured for their own tenant as well to ensure users from your organization and their organization can access each other's tenants.

To enable partner logins for Private Access:

1.   Go to **Administration**>**Identity**>**Private Access**>**Partner Login**.
2.   In **Allow Partner Login to This Tenant**:
    1.   **No Access**: Select this option to disallow access to your tenant to any partner organizations.
    2.   **Specific Tenants**: Select this option to allow access to your tenant by specific tenants:

        1.   **Allow Following Primary Tenants to Connect to this Tenant**: Enter a fully qualified domain name (FQDN) for the partner tenant and, optionally, a cloudname. If no cloudname is entered, a user on the partner domain on any cloudname can access your tenant.
        2.   **Restrict Old Zscaler Client Connector Versions to Select to this Tenant**: Enable to allow only partner tenant devices using Zscaler Client Connector version 4.7 and later for Windows to log in to this tenant.

If you enable this option, any existing partner tenants using Zscaler Client Connector for macOS or version 4.6 and earlier for Windows are unenrolled.

The **Specific Tenants** option applies only to Zscaler Client Connector 4.7 and later for Windows.

    3.   **All Tenants**: Select this option to give access to your tenant to all partner organizations.

3.   Enable **Allow Users of This Tenant to Login to Other Tenants** to let your users access a partner's tenant. By default, this setting is disabled.

If you use Zscaler Client Connector version 4.6 and later for Windows, you can configure access to a partner’s tenant on the [app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles). Partner login settings that are enabled on the app profile override these settings.

[See image.](https://help.zscaler.com/zscaler-client-connector/enabling-private-access-partner-logins#Enable-partner-login)

4.   Click **Save**.

---

<!-- source: https://help.zscaler.com/zscaler-deployments-operations/zscaler-client-connector-deployment-and-operations-guide -->

Title: Zscaler Client Connector Deployment and Operations Guide | Zscaler

URL Source: https://help.zscaler.com/zscaler-deployments-operations/zscaler-client-connector-deployment-and-operations-guide

Markdown Content:
![Image 1: icon-zscaler-deployments-operations.svg](blob:http://localhost/b4ec5c0c91cfb4a17b50f1ae38289e8b)

## Zscaler Deployments & Operations

### Zscaler Client Connector Deployment and Operations Guide

This guide describes how to use Zscaler Client Connector and the steps necessary for configuring Zscaler Client Connector. Zscaler Client Connector is a lightweight application that runs on a user’s endpoint device. Zscaler Client Connector automatically forwards all user traffic to the closest Zscaler Public Service Edge and enforces security and access policies across all devices, locations, and applications.

With Zscaler Private Access (ZPA) enabled, users can securely access your organization's internal resources from any location. Using Zscaler Digital Experience (ZDX), Zscaler Client Connector synthetically probes Software as a Service (SaaS) applications or internet-based services (e.g., OneDrive, Gmail, etc.) to triage and pinpoint the source of performance issues.

To learn more, see [What is Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/what-is-zscaler-client-connector)[?](https://help.zscaler.com/zscaler-client-connector/what-zscaler-app)

## Value of Deploying User Provisioning and Authentication

Using Zscaler Client Connector provides the following benefits:

*   Zero Trust policies follow users regardless of devices, locations, or applications accessed.
*   Enhances the user experience and streamlines application access.
*   Centralizes control and policy management.
*   Tracks and monitors user and device activities for IT teams.
*   Supports popular operating systems and device types (e.g., laptops, smartphones, tablets, etc.).
*   Strictly enforces internet access criteria for users not enrolled in Zscaler Client Connector.

## Deployment Phase

The deployment phase initially sets up and integrates Zscaler Client Connector. During the deployment phase, you configure Zscaler Client Connector to meet the needs of your infrastructure. The following sections discuss the steps to deploy Zscaler Client Connector.

## Prerequisites

For Zscaler Client Connector deployment, observe the following prerequisites:

*   Verify the [system requirements](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#step-1).
*   Zscaler Client Connector does not require an additional license or subscription. Licensing for ZIA and ZPA includes Zscaler Client Connector.

## Deployment Steps

The following steps explain how to deploy Zscaler Client Connector:

1.   Complete [system requirements and prerequisite tasks](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#step-1).
2.   Allowlist [Zscaler Client Connector processes](https://help.zscaler.com/zscaler-client-connector/zscaler-app-processes-whitelist) on client firewall and antivirus (AV).
3.   Allow [Zscaler Client Connector communication](https://config.zscaler.com/zscaler.net/zscaler-app) to the Zscaler cloud through your organization's firewall.
4.   [Download Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/downloading-zscaler-client-connector) from an app store.
5.   (Optional) Customize [Zscaler Client Connector with installer options](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi).
6.   Use your organization’s device management system to [deploy Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-app-active-directory-windows).

## Considerations

Review the following considerations:

*   If your users are running Zscaler Client Connector in conjunction with virtual private network (VPN) clients or VPN-like applications (e.g., Microsoft DirectAccess), check that users aren’t experiencing interoperability issues.
*   For a complete list of recommended steps, see [Best Practices for Zscaler Client Connector and VPN Client Interoperability](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-app-and-vpn-client-interoperability).
*   Ensure all authentication traffic goes directly to the identity provider (IdP) destination URL. Users who are off the trusted network and forwarding traffic via Zscaler Client Connector should not experience issues. However, check that other authentication traffic (e.g., PAC files, GRE tunnels, and IPSec tunnels) goes directly to the IdP.
*   Make sure traffic destined for the IdP that goes through Zscaler is not intercepted for inspection by Zscaler:
    *   Adjust [SSL Inspection exemptions](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-app#exempting-URLs-SSL).
    *   Adjust [Authentication exemptions](https://help.zscaler.com/zia/exempting-urls-cloud-apps-authentication).

*   If you are using Microsoft Windows Autopilot, see the [Zscaler and Microsoft Windows Autopilot Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-microsoft-windows-autopilot-deployment-guide).

## Operations Phase

This section describes standard practices used to operate Zscaler solutions when integrated with your environment. You can monitor and tune Zscaler Client Connector during operations to meet your infrastructure needs.

## Prerequisites

For Zscaler Client Connector operations, observe the following prerequisites:

*   Create a standard operating procedure (SOP) for adding domain bypasses in the PAC file used by Zscaler Client Connector.
*   Define a process to test configuration changes, such as diverting traffic to a different data center or bypassing a PAC file.
*   Test any changes with separate app profiles or PAC files and apply the policy to test users to avoid organization-wide impact.
*   Implement a process to clean up test profiles and PAC files.

## Deployment Checklist

Zscaler recommends downloading the [Zscaler Client Connector Deployment and Operations Checklist](https://help.zscaler.com/downloads/zscaler-deployments-operations/zscaler-client-connector-deployments-operations/zscaler-client-connector-deployment-and-operations-guide/Zscaler-Client-Connector-Deployment-Operations-Checklist.pdf) to help plan and implement Zscaler Client Connector: [Download PDF](https://help.zscaler.com/downloads/zscaler-deployments-operations/zscaler-client-connector-deployments-operations/zscaler-client-connector-deployment-and-operations-guide/Zscaler-Client-Connector-Deployment-Operations-Checklist.pdf "Zscaler Client Connector Deployment and Operations Checklist")

## Additional Information

For more Zscaler Client Connector information and troubleshooting instructions, see the [Zscaler Support Portal](https://zscaler.my.site.com/customers/s/) and the [Zscaler Zenith Community](https://community.zscaler.com/).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/secure-mobile-access-zscaler-client-connector -->

Title: Secure Mobile Access with Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/secure-mobile-access-zscaler-client-connector

Markdown Content:
# Secure Mobile Access with Zscaler Client Connector | Zscaler

[![Image 2: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Client Connector

- [x] All

- [x] Getting Started with Zscaler

- [x] Internet & SaaS (ZIA)

- [x] Private Access (ZPA)

- [x] Digital Experience Monitoring (ZDX)

- [x] Workflow Automation

- [x] Data Security Posture Management (DSPM)

- [x] Client Connector

- [x] Cloud & Branch Connector

- [x] Zscaler Cellular

- [x] Zero Trust Branch

- [x] Unified Vulnerability Management (UVM)

- [x] Asset Exposure Management

- [x] External Attack Surface Management

- [x] SOC Workbench

- [x] Deception

- [x] Identity Protection

- [x] Breach Predictor

- [x] Zero Trust Browser

- [x] Risk360

- [x] Business Insights

- [x] Authentication Service

- [x] Zscaler SDK for Mobile Apps

- [x] AI Asset Management

- [x] Secure Access to AI Apps

- [x] Secure AI Apps & Infrastructure

- [x] Legacy Zscaler APIs

- [x] Logs & Fair Use

- [x] Zscaler Support Troubleshooting Runbooks

- [x] Zscaler Deployments & Operations

- [x] Zscaler Technology Partners

- [x] Multi-Tenant Portal

- [x] Product Usage Terms & Policies

EN

English 日本語

*   [Zscaler Client Connector Help](https://help.zscaler.com/zscaler-client-connector)
*   [Reference Architecture](https://help.zscaler.com/zscaler-client-connector/reference-architecture)
*    Secure Mobile Access with Zscaler Client Connector 

![Image 3: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 4: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Secure Mobile Access with Zscaler Client Connector

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

The Zscaler Reference Architecture series delivers best practices based on real-world deployments. The recommendations in this series were developed by Zscaler's transformation experts from across the company. This guide will steer you through the architecture process and provide technical deep dives into specific platform functionality and integrations. The Zscaler Reference Architecture series is designed to be modular, so this guide will show you how to configure a different aspect of the platform in order to allow you meet your specific policy goals.

Zscaler Client Connector is a lightweight, tamper-resistant agent that connects your users and devices to the Zscaler services in the Zero Trust Exchange (ZTE). Zscaler Client Connector supports Zscaler Internet Access (ZIA), Zscaler Private Access (ZPA), and Zscaler Digital Experience (ZDX) by default, allowing your team to combine best-in-class internet security with Zero Trust access to internal applications

[Secure Mobile Access with Zscaler Client Connector: Reference Architecture Guide](https://help.zscaler.com/downloads/zscaler-client-connector/reference-architecture/secure-mobile-access-zscaler-client-connector/secure-mobile-access-with-zscaler-client-connector.pdf "Secure Mobile Access with Zscaler Client Connector")[Download PDF](https://help.zscaler.com/downloads/zscaler-client-connector/reference-architecture/secure-mobile-access-zscaler-client-connector/secure-mobile-access-with-zscaler-client-connector.pdf "Download PDF")

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Article

[Secure Mobile Access with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/secure-mobile-access-zscaler-client-connector)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-google-workspace-android -->

Title: Deploying Zscaler Client Connector with Google Workspace for Android | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-google-workspace-android

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Deploying Zscaler Client Connector with Google Workspace for Android

This guide is for admins only. If you're an end user, contact your organization’s administrator for deployment-related details.

With Google Workspace, you can distribute App Store apps, including apps purchased in volume, to mobile devices. After an app is distributed, you can use Google Workspace to manage future updates to Zscaler Client Connector. You must have the required license for managing Android devices before deployment.

This article contains terms specific to the Google Workspace. To learn more, refer to [Google Workspace Admin documentation](https://support.google.com/a/answer/6328701?sjid=6371310950853617416-NC).

To deploy Zscaler Client Connector with Google Workspace, you must add and manage public apps with Google Workspace:

1.   Log in to the Google Admin console.
2.   In the left-side navigation, go to **Apps**>**Web and mobile apps**>**Add App**. Select **Add private Android app**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-google-workspace-android#add-private-android)

3.   In the left-side navigation, click **Search Play Store**and enter `Zscaler Client Connector` in the **Search** field.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-google-workspace-android#search_play_store)

4.   Click **Select**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-google-workspace-android#selectZCC)

You're automatically redirected to Apps > Web and mobile apps > Zscaler Client Connector, where you can configure app settings. Click **View details**in the **User access**section.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-google-workspace-android#redirect)

5.   Select **Make this app available to users in this organizational unit**.Click **Save** and collapse the **User access** section.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-google-workspace-android#useraccess)

6.   In the **Settings**section, click **Learn more** and select the following values for each setting. When finished, collapse the **Settings** section.
    *   **Access method: Force install**or **Available**
    *   **Prevent users from uninstalling the app**: **OFF**
    *   **Allow users to add widgets to homescreen**: **ON**or **OFF**
    *   **Use as the****always-on VPN****app**: **ON**
    *   **Managed configuration:****IdP**

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-google-workspace-android#settings)

1.   In the **Runtime permissions**section, select **Allow** for the **Contacts**feature if [**Zscaler Client Connector Portal as IdP**](https://help.zscaler.com/zia/adding-zscaler-client-connector-portal-idp)is the preferred authentication type**.**Click **Save** and collapse the **Runtime permission**s section.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-google-workspace-android#runtime)

1.   In the **Managed configurations**section, enter the following JSON format:

```
{
"userDomain":"<org’s_domain>",
"cloudName":"zscalerone",
"autoEnrollWithMDM":"1",
"deviceToken":"<token_value>",
"externalDeviceId":"${DEVICE_SERIAL_NUMBER}"
}
```

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-google-workspace-android#json)

1.   You can add [Zscaler parameters](https://help.zscaler.com/zscaler-client-connector/parameters-guide-zscaler-client-connector-android-and-android-chromeos) to the JSON format based on your organization's needs and click **Save**.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/supported-parameters-zscaler-client-connector-ios -->

Title: Supported Parameters for Zscaler Client Connector for iOS | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/supported-parameters-zscaler-client-connector-ios

Markdown Content:
userDomain Your organization's domain name. If your instance has multiple domains associated with it, enter the primary domain for your instance.

The primary domain is only valid if you are using a single identity provider (IdP) with multiple domains. The primary domain won’t work if you have multiple domains across multiple IdPs.Example: `safemarch.com`

Specify the exact domain that is present in the SAML NameID field. For example, if your user’s NameID is john.doe@corp.company.com, then enter `corp.company.com`.This install option allows users to skip the app enrollment page. If SSO is enabled for your organization, users are taken directly to your organization's SSO login page.
cloudname The name of the cloud on which your organization is provisioned.

If your organization is provisioned on more than one cloud, your users are asked to select the cloud where their traffic is sent during the enrollment process. With this install option, you can specify the cloud where the app sends user traffic, so your users don't have to make the selection during enrollment.If your cloud name is zscalertwo.net, enter `zscalertwo`.Do not use this option if your organization is provisioned on one cloud. The app automatically sends traffic to the proper cloud and your users don't need to make a selection during enrollment.

This option is required if you use the strictEnforcement option.
strictEnforcement Allows you to require users to enroll with the app before accessing the internet and blocks traffic in the following situations:

*   The user has not yet logged in after a new installation.
*   A user logs in and logs out.
*   An administrator removes a device from the Zscaler Admin Console.`1` = Enable

`0` = Disable (default)If you enable Strict Enforcement, you must use the policyToken configuration key. Excluded URLs must also be defined in a device configuration policy or excludeList in an app configuration policy.
excludeList Allows you to exclude domains and IP addresses that should never be tunneled by Zscaler Client Connector with or without strictEnforcement. If you are using strictEnforcement, enter your MDM server or anything else the user should have access to before enrollment.Enter a value, for example, `apple.com`, `airwatch.com`.If you use the Private Access service, you must enter `authsp.prod.zpath.net`. For a list of additional requirements, refer to your MDM's requirements for this parameter. For a list of Apple's recommendations for destinations that should be excluded, refer to the [Apple documentation](https://support.apple.com/en-us/101555).
newBindFlow Enables multithreaded implementation of Zscaler Client Connector microservices binding with Zscaler Client Connector virtual interface.`1` = Enable (default)

`0` = Disable Applicable to per-app VPN deployments only.
deviceToken The appropriate device token from the Zscaler Admin Console if you want to use the [Zscaler Admin Console as an IdP](https://help.zscaler.com/zscaler-client-connector/using-zscaler-admin-console-identity-provider). The Zscaler service silently provisions and authenticates users even if you don't have an authentication mechanism in place.Example: `123456677754`This option applies only to Internet & SaaS. It is not supported by Private Access unless you also use Authentication Service.

Before adding this option, you must generate a device token in the Zscaler Admin Console and complete the full configuration in [Using Zscaler Client Connector Portal as an IdP](https://help.zscaler.com/zscaler-client-connector/using-zscaler-admin-console-identity-provider).
policyToken Allows you to specify which app profile policy you want to enforce for the app before the user enrolls. All relevant settings associated with the policy apply, including the bypass of the IdP login page.

After the user enrolls, this policy is replaced with an app profile policy that matches the user based on group affiliation.Example: 393837313A313A64666131313635652D376536372D343032312D393232362D363264393931666135762960 Applies only, and is required if you use the strictEnforcement option.

In the Zscaler Admin Console, you must configure the app profile policy that you want to enforce and ensure that the custom PAC file associated with that policy includes a bypass for your IdP login page. This allows the user to access the IdP page to log in as necessary before enrolling with the app.
username The username of the user.

You can also use an MDM macro to auto-populate this value. Refer to your MDM's documentation for available macros.A maximum of 255 alphanumeric and special characters.

For example, if the username is j.doe@zscaler.com, enter `j.doe`If you use this option, the userDomain option must not be empty.
authByTunnel The auto-enrollment settings for users if you want to use the [Zscaler Admin Console as an IdP](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector-portal-identity-provider) for authentication.`1`= Users will always auto-enroll even if they are logged out manually or forcefully removed from the Zscaler Admin Console

`2`= One-time auto-enrollment

`0`= Disable auto-enrollment This option applies only to Internet & SaaS. If Private Access is enabled, users would log in manually with their user credentials.
ownership If you use the Ownership Variable device posture type, add the key `ownership`. You can enter up to 32 alphanumeric characters in the **Configuration value**field. To learn more, see [Configuring Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles).Example: `acmecorp`The ownership variable must be configured and pushed exclusively through the managed app configuration using your MDM solution and should not be included in the VPN profile.
SkipInterfaceInstallation When enabled, only shows VPN icon in iOS when user is logged into Zscaler Client Connector.`1` = Enable

`0` = Disable (default)Requires Tunnel SDK version 3.7 and later.
DropNonRoutingTraffic Drops traffic without routes.`1` = Enable

`0` = Disable (default)
PAVConnectionSynced Delays Per-app VPN connection until Zscaler Client Connector is connected.`1` = Enable

`0` = Disable (default)Applicable to per-app VPN deployments only.
externalDeviceId The identifier that associates devices in an MDM solution with devices in the Zscaler Admin Console.

You can use an MDM macro to auto-populate this value. Refer to your MDM's documentation for available macros.`0` = Disable (default)

Enter a custom value to identify the device (e.g., `123456677754`).
enableFips Indicates whether Zscaler Client Connector uses FIPS-compliant libraries for communication with the Zscaler infrastructure.`1` = Enable

`0`= Disable (default)Enable this option only if you require FIPS-level security within your organization.
MatchDomainsNoSearch When enabled, Private Access match domains are not appended as search domains on the device.`1` = Enable

`0`= Disable (default)
disableCaptivePortalNotification When enabled, Zscaler Client Connector suppresses notifications when it detects a captive portal.`1` = Enable

`0`= Disable (default)
SystemDNSEnabled Zscaler Client Connector uses this parameter to resolve DNS queries which aren't forwarded to the Zscaler cloud. If Zscaler Client Connector fails to resolve the DNS using the system DNS domain, it falls back to Google DNS.`1` = Enable

`2` = Disable (default)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex -->

Title: Deploying Zscaler Client Connector with ChromeOS Flex | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Deploying Zscaler Client Connector with ChromeOS Flex

This guide is for admins only. If you're an end user, contact your organization’s administrator for deployment-related details.

Installing ChromeOS Flex can refresh your PC and Mac devices with a fast, secure, and cloud-first operating system. It’s a free, lightweight alternative to traditional operating systems like Windows or macOS, designed for users who primarily rely on web-based applications and services.

Zscaler supports ChromeOS Flex for managed devices through ChromeOS Device Management, which is accessible through the Google Admin console.

This article contains terms specific to Google Flex. To learn more, refer to the [Google Flex documentation](https://support.google.com/chromeosflex/?hl=en#topic=11618314).

## Prerequisites

Devices must meet the following hardware requirements to run Zscaler Client Connector with ChromeOS Flex:

*   16 GB of RAM
*   32 GB or larger solid-state storage device
*   AMD-based device from 2019 or newer
*   Intel-based device from 2017 (Kaby Lake +) or newer (excluding Ice Lake)
*   Hardware virtualization enabled in the device BIOS

Before configuring a device to run Zscaler Client Connector with ChromeOS Flex, verify the following:

*   ChromeOS Flex is installed on the device.
*   The device is enrolled in the Google Admin console.
*   The device is updated to the minimum ChromeOS Flex version 135.

To deploy and run Zscaler Client Connector on ChromeOS Flex from your Android and ChromeOS devices:

*   [Step 1: Enable Android Runtime for Chrome on ChromeOS Flex](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex#runtime)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex)Android Runtime for Chrome controls preloading a VM (ArcVM) image to devices running ChromeOS Flex. ArcVM is the current method for booting Android under ChromeOS. Selecting the Enable ArcVM image preloading option allows an ArcVM image to preload separately on ChromeOS Flex. This is required to deploy and run Zscaler Client Connector on ChromeOS Flex.

    1.   Log in to the Google Admin console.
    2.   Go to **Devices**>**Chrome**>**Settings**and click the **Device settings**tab.
    3.   Under **Other settings,** scroll down to the **Preload ArcVM image on ChromeOS Flex**drop-down menu and select **Enable ArcVM image preloading**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex#ArcVM_0)

    1.   Under **About this setting**, verify that the **Configuration** field is set to **Enable ArcVM image preloading**.
    2.   On the left-side navigation, under **Search for organizational units**, select the organizational unit level to which you want to apply the ArcVM image preloading option. You can select the top level or one of the child levels.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex#ArcVM)

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex#runtime)

*   [Step 2: Force Install Apps](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex#force)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex)ChromeOS Flex for Zscaler Client Connector requires a force install for users in your organization. After the force install, users can see Zscaler Client Connector using ChromeOS Flex on managed devices or accounts. To learn more about the automatic installation of apps and extensions, refer to [Google Chrome Help](https://support.google.com/chrome#topic=7439538).

    1.   Log in to the Google Admin console.
    2.   Go to **Devices**>**Chrome**>**Apps & extensions**.
    3.   Click the **Users & browsers** tab.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex#Apps)

    4.   Click **Search or add a filter** and search for **Client Connector - Chromebook** app using **Full-text** or **Title**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex#select)

    1.   Under **Installation policy**, select **Force install + pin to ChromeOS taskbar** or **Force install**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex#forceinstall)

    1.   Click **OK** and then click **Save**.

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex#force)

*   [Step 3: Set Up Virtual Private Networks for ChromeOS Flex](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex#VPN)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex)Zscaler Client Connector uses the ChromeOS Flex Android VPN app to establish and maintain a secure connection to the internet and to the Zscaler secure cloud platform. This ensures that all traffic is routed through Zscaler’s secure cloud platform, enhancing security and compliance without requiring manual user intervention.

    1.   Log in to the Google Admin console.
    2.   Go to **Devices**>**Chrome**>**Settings**.
    3.   Click the **User & browser settings**tab.
    4.   Under **Organizational Units**, leave the top organizational unit selected to apply the setting to all users. Otherwise, select a child organizational unit.
    5.   Select **Network**>**Always on VPN**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex#vpn)

    6.   Click **Edit**.
    7.   In the **Select an Always on VPN app** window, select **Client Connector - Chromebook**and click **OK**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex#chrome_app)

![Image 2: Select an Always on VPN app window](https://help.zscaler.com/downloads/zscaler-client-connector/downloading-deployment/deploying-zscaler-client-connector-google-flex/Select_an_Always-On_VPN-App_Annotated.png)[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex)

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex#VPN)

*   [Step 4: Install SSL/TLS Certificates](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex#SSL)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex)When SSL inspection is enabled, the Zscaler service establishes a separate SSL tunnel with the destination server and with the user's browser. This allows the service to decrypt and inspect the HTTPS traffic coming to and from the user’s browser, as well as all traffic coming to and from the destination server.

To establish an SSL tunnel and return content to the user’s browser, the service uses either the Zscaler intermediate certificate or a custom intermediate certificate signed by your own trusted Certificate Authority (CA). You can select and configure the CA certificate according to your requirements. The certificates must be installed manually on ChromeOS Flex, Android, and iOS devices, or using the Google Admin console. For ChromeOS Flex, the certificate can be installed on the Google Admin console.

To download and use the Zscaler Intermediate CA Certificate:

    1.   In the Zscaler Admin Console, go to **Policies**>**Common Configuration**>**SSL/TLS Inspection**>**SSL/TLS Inspection Intermediate Certificate**.
    2.   Click **Edit**corresponding to the Zscaler Intermediate CA Certificate. The **Edit Zscaler Intermediate CA Certificate** window appears.
    3.   In the **Edit Zscaler Intermediate CA Certificate** window, in the **Root Certificate** field, click **Download**. The root certificate is downloaded as a ZIP file.
    4.   Go to the `ZscalerRootCerts.zip` file and unzip it.
    5.   Log in to the Google Admin console.
    6.   Go to **Devices**>**Networks**.
    7.   Under **Organization units,** select the organizational unit where you want to apply the certificate. This is the unit containing the management of ChromeOS Flex.
    8.   In the **Certificates**section, click **Upload Certificate**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex#cert)

The **Add certificate** window appears.

    1.   In the **Add certificate** window, enter the certificate name and click **Upload**. Go to the unzipped certificate from the previous step, select it, and click **Open**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex#add_certificate)

    1.   Select the platform (e.g., ChromeOS Flex) for which this certificate acts as a certificate authority.
    2.   Click **Add**to complete the installation.

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-chromeos-flex#SSL)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/about-location-based-policies -->

Title: About Location-Based Policies | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/about-location-based-policies

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### About Location-Based Policies

Location-based policies are rulesets that steer traffic and apply endpoint firewall rules based on a user’s network type (e.g, On-Trusted or Off-Trusted). These rulesets can include inbound and outbound endpoint firewall rules, DNS domain inclusions and exclusions, and bypasses for applications and IP addresses. Rulesets can include traffic steering only, endpoint firewall rules only, or a combination of both. You can add a ruleset for each network type and assign the rulesets to app profiles.

This feature is available only for Zscaler Client Connector version 4.8 and later for Windows. Contact Zscaler Support to enable this feature.

Location-based policies provide the following benefits and enable you to do the following:

*   Create policies using a framework with support for reusable objects and bulk changes.
*   Bypass or tunnel traffic for destination IP addresses and subnets, traffic from predefined and custom IP-based applications, and DNS queries by domain.
*   Bypass all traffic from specific processes.
*   Enforce endpoint firewall rules for inbound and outbound traffic, including 5-tuples, applications, and default rules.
*   Enforce inbound rules for Private Access client-to-client traffic.
*   Save time by removing the need to configure default outbound Allow All rules in your host firewall settings.

On the Location-Based Policies page (Infrastructure > Connectors > Client > Location Based Policies), you can do the following:

1.   [Add a traffic steering IP list](https://help.zscaler.com/zscaler-client-connector/adding-traffic-steering-ip-list).
2.   [Add a domain list](https://help.zscaler.com/zscaler-client-connector/adding-domain-list).
3.   [Add an application for a ruleset](https://help.zscaler.com/zscaler-client-connector/adding-application-ruleset).
4.   [Add a firewall IP list](https://help.zscaler.com/zscaler-client-connector/adding-firewall-ip-list).
5.   [Add a ruleset](https://help.zscaler.com/zscaler-client-connector/adding-ruleset).
6.   Perform an exact match search of rulesets that include:
    *   **Policy Rulesets**: The name of the ruleset.
    *   **Traffic Steering IP List**: A list from the [Traffic Steering IP Lists](https://help.zscaler.com/zscaler-client-connector/adding-traffic-steering-ip-list) tab.
    *   **Domain List**: A list from the [Domain Lists](https://help.zscaler.com/zscaler-client-connector/adding-domain-list) tab.
    *   **Application List**: An application from the [Applications](https://help.zscaler.com/zscaler-client-connector/adding-application-ruleset) tab.
    *   **Firewall IP List**: A list from the [Firewall IP Lists](https://help.zscaler.com/zscaler-client-connector/adding-firewall-ip-list) tab.
    *   **Process-Based Application Bypass**: A [process-based application](https://help.zscaler.com/zscaler-client-connector/adding-process-based-applications-bypass-traffic) from Application Bypass.
    *   **Predefined IP-Based Application Bypass**: A Zscaler predefined IP-based application.
    *   **Custom IP-Based Application Bypass**: A [custom IP-based application](https://help.zscaler.com/zscaler-client-connector/adding-ip-based-applications-bypass-traffic) from Application Bypass.

7.   Search for a ruleset.
8.   View a list of all rulesets configured for your organization. For each ruleset, you can see the following details:
    *   **Ruleset Name**: The name of the ruleset.
    *   **Last Edited Time**: The date and time when the ruleset was last edited.

9.   Modify the table and its columns.
10.   Copy a ruleset.
11.   Edit a ruleset.
12.   Delete a ruleset.

![Image 2: Location-Based Policies page](https://help.zscaler.com/downloads/zscaler-client-connector/location-based-policies/about-location-based-policies/zclient-connector-location-based-policies-page.png)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/adding-ruleset -->

Title: Adding a Ruleset | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/adding-ruleset

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Adding a Ruleset

You can add a ruleset that steers traffic and applies endpoint firewall rules based on a user’s network type (e.g, On-Trusted or Off-Trusted). You can add up to 300 rulesets. For each ruleset, you can enter one or both of the following:

*   Traffic steering rules, including DNS domain inclusions and exclusions, IP address inclusions and exclusions, and application bypasses
*   Inbound and outbound endpoint firewall rules, including default rules

To learn more, see [About Location-Based Policies](https://help.zscaler.com/zscaler-client-connector/about-location-based-policies).

This feature is available only for Zscaler Client Connector version 4.8 and later for Windows. Contact Zscaler Support to enable this feature.

## Prerequisites

Before you add a ruleset, make sure that you have added any of the following items you want to use in the ruleset:

*   If you want to include traffic steering in the ruleset:
    *   [Lists of IP addresses to bypass or tunnel traffic](https://help.zscaler.com/zscaler-client-connector/adding-traffic-steering-ip-list) to the Internet & SaaS service.
    *   [Lists of domains](https://help.zscaler.com/zscaler-client-connector/adding-domain-list) for which to bypass or tunnel DNS queries to the Internet & SaaS service.

*   If you want to include endpoint firewall rules in the ruleset:
    *   [Applications](https://help.zscaler.com/zscaler-client-connector/adding-application-ruleset) to include in inbound or outbound endpoint firewall rules.
    *   [Lists of IP addresses](https://help.zscaler.com/zscaler-client-connector/adding-firewall-ip-list) to include in inbound or outbound endpoint firewall rules.

You must select a ruleset for the On-Trusted network type and a ruleset for the Off-Trusted network type. So, if you want a different ruleset for each network type, make sure to add items as necessary.

To add a ruleset:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**Location Based Policies**.
2.   On the **Policy Rulesets** tab, click **Add Ruleset**.

The **Add Ruleset** window appears.

3.   In the **Add Ruleset** window, you can configure the following settings:
    *   [General](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#general)

        *   [](https://help.zscaler.com/zscaler-client-connector/adding-ruleset)**Name**: Enter a unique alphanumeric name for your ruleset.

![Image 2: General section on Add Ruleset window](https://help.zscaler.com/downloads/zscaler-client-connector/location-based-policies/about-location-based-policies/zclient-connector-add-ruleset-general.png)[Close](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#general)

    *   [DNS](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#DNS)

[](https://help.zscaler.com/zscaler-client-connector/adding-ruleset)Enter the domain inclusions and exclusions that apply to the ruleset for traffic steering:

        *   **Domain Inclusion**: Select a domain list from the [Domain Lists](https://help.zscaler.com/zscaler-client-connector/adding-domain-list) tab for which Zscaler Client Connector should send DNS requests to the Public Service Edge for Internet & SaaS.
        *   **Domain Exclusion**: Select a domain list from the [Domain Lists](https://help.zscaler.com/zscaler-client-connector/adding-domain-list) tab for which Zscaler Client Connector should send DNS requests to the DNS server defined on the device (and not to the Public Service Edge).
        *   **Prioritize DNS Exclusions over Z-Tunnel 2.0**: When enabled, Zscaler Client Connector prioritizes DNS domains in Domain Exclusions over Domain Inclusions. To learn more, see the [How Zscaler Client Connector processes the domain inclusions and exclusions](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#DNS_IP_in_Inclusion) section.

![Image 3: DNS section on Add Ruleset window](https://help.zscaler.com/downloads/zscaler-client-connector/location-based-policies/about-location-based-policies/zclient-connector-add-ruleset-dns.png)[Close](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#DNS)

    *   [Global Bypasses & IP Inclusions/Exclusions](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#bypasses)

[](https://help.zscaler.com/zscaler-client-connector/adding-ruleset)Enter the bypasses and IP inclusions and exclusions that apply to the ruleset for traffic steering:

        *   **Process-Based Application Bypass**: Allows bypassing of [process-based applications](https://help.zscaler.com/zscaler-client-connector/adding-process-based-applications-bypass-traffic) that you can use for both Zscaler Tunnel (Z-Tunnel) 1.0 and Z-Tunnel 2.0. Click **Select All** or select applications from the drop-down menu.
        *   **Predefined IP-Based Application Bypass**: Allows bypassing of [predefined IP-based applications](https://help.zscaler.com/zscaler-client-connector/about-application-bypass-info). Click **Select All** or select applications from the drop-down menu.
        *   **Custom IP-Based Application Bypass**: Allows bypassing of [custom IP-based applications](https://help.zscaler.com/zscaler-client-connector/adding-ip-based-applications-bypass-traffic) that you can use for Z-Tunnel 2.0. Click **Select All** or select applications from the drop-down menu.
        *   **IP Exclusion List**: Select a list of IP addresses from the [Traffic Steering IP Lists](https://help.zscaler.com/zscaler-client-connector/adding-traffic-steering-ip-list) tab to exclude, or keep the default value of `Default IP Exclusion List` to use the default exclusion list on the tab.
        *   **IP Inclusion List**: Select a list of IP addresses from the [Traffic Steering IP Lists](https://help.zscaler.com/zscaler-client-connector/adding-traffic-steering-ip-list) tab to include, or keep the default value of `Default IP Inclusion List` to use the default inclusion list on the tab.
        *   **Source Port-Based Bypass**: Enter the source port and protocol from which Zscaler Client Connector bypasses existing inbound traffic. The port value can range from `1` to `65535`. The protocol value is `TCP`, `UDP`, or `*`.

Zscaler recommends you use endpoint firewall rules instead of a source port-based bypass because rules are stateful.

![Image 4: Global Bypasses and IP Inclusions/Exclusions section on Add Ruleset window](https://help.zscaler.com/downloads/zscaler-client-connector/location-based-policies/about-location-based-policies/zclient-connector-add-ruleset-bypasses.png)[Close](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#bypasses)

    *   [Inbound Firewall Rules](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#inbound-rule)

[](https://help.zscaler.com/zscaler-client-connector/adding-ruleset)Configure the endpoint firewall rule for inbound traffic:

        *   **Rule Order**: Select the appropriate rule order value from the drop-down menu. The rule order determines the processing order within the ruleset. Precedence is based on ascending numerical order.
        *   **Firewall IP List**: Select the criteria to apply the inbound firewall rule to:
            *   **Any**: Apply to any IP address, port, and protocol.
            *   **Local subnet**: Apply to the subnet range into which the device falls. The local subnet is auto-detected and Zscaler Client Connector creates both IPv4 (multicast address) and IPv6 (link-local IP and multicast address) filters based on the subnet.
            *   **<IP List name>**: Apply to the selected Firewall IP list from the [Firewall IP Lists](https://help.zscaler.com/zscaler-client-connector/adding-firewall-ip-list) tab.

        *   **Application**: Select the application from the [Applications](https://help.zscaler.com/zscaler-client-connector/adding-application-ruleset) tab that the inbound firewall rule applies to, or select **Any**to apply it to all applications.
        *   **Action**: Select the action for the rule:
            *   **Firewall Allow**: Allow inbound traffic that matches the rule.
            *   **Firewall Block**: Block inbound traffic that matches the rule.

You must select at least one Firewall IP List or Application. To reset to the default configuration, click **Restore Default**. You can click **Add Rule** to enter up to a maximum of 10 rules. Each new rule displays under the previously added one.

![Image 5: Inbound Firewall Rules section on Add Ruleset window](https://help.zscaler.com/downloads/zscaler-client-connector/location-based-policies/about-location-based-policies/zclient-connector-add-ruleset-inbound.png)[Close](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#inbound-rule)

    *   [Default Inbound Firewall Rule](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#default-inbound)

        *   [](https://help.zscaler.com/zscaler-client-connector/adding-ruleset)**Action**: Select the action that the filter applies to inbound traffic that doesn’t match any inbound firewall rules in the ruleset:
            *   **None**: Use the existing firewall policy (e.g., Windows Defender). This is the default value.
            *   **Firewall Allow**: Allow inbound traffic that doesn’t match the inbound firewall rules in the ruleset.
            *   **Firewall Block**: Block inbound traffic that doesn’t match the inbound firewall rules in the ruleset.

This action does not apply to loopback traffic as loopback traffic is always allowed.

        *   **Allow ZPA Client to Client Traffic**: Select this option to allow inbound access for Private Access (ZPA) client-to-client traffic (e.g., client-based remote assistance) even if all other inbound traffic is blocked. To learn more, see [Understanding Client-to-Client Connectivity](https://help.zscaler.com/zpa/understanding-client-client-connectivity).

![Image 6: Default Inbound Firewall Rule section on Add Ruleset window](https://help.zscaler.com/downloads/zscaler-client-connector/location-based-policies/about-location-based-policies/zclient-connector-add-ruleset-default-inbound.png)[Close](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#default-inbound)

    *   [Outbound Firewall Rules](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#outbound-rule)

[](https://help.zscaler.com/zscaler-client-connector/adding-ruleset)Configure the endpoint firewall rule for outbound traffic:

        *   **Rule Order**: Select the appropriate rule order value from the drop-down menu. The rule order determines the processing order within the ruleset. Precedence is based on ascending numerical order.
        *   **Firewall IP List**: Select the criteria to apply the outbound firewall rule to:
            *   **Any**: Apply to any IP address, port, and protocol.
            *   **Local subnet**: Apply to the subnet range into which the device falls. The local subnet is auto-detected and Zscaler Client Connector creates both IPv4 (multicast address) and IPv6 (link-local IP and multicast address) filters based on the subnet.
            *   **<IP List name>**: Apply to the selected Firewall IP list from the [Firewall IP Lists](https://help.zscaler.com/zscaler-client-connector/adding-firewall-ip-list) tab.

        *   **Application**: Select the application from the [Applications](https://help.zscaler.com/zscaler-client-connector/adding-application-ruleset) tab that the outbound firewall rule applies to or select **Any**to apply it to all applications.
        *   **Action**: Select the action for the rule:
            *   **Firewall Allow**: Allow outbound traffic that matches the rule.
            *   **Firewall Block**: Block outbound traffic that matches the rule.

This action does not apply to loopback traffic as loopback traffic is always allowed.

You must select at least one Firewall IP List or Application. To reset to the default configuration, click **Restore Default**. You can click **Add Rule** to enter up to a maximum of 10 rules. Each new rule displays under the previously added one.

![Image 7: Outbound Firewall Rules section on Add Ruleset window](https://help.zscaler.com/downloads/zscaler-client-connector/location-based-policies/about-location-based-policies/zclient-connector-add-ruleset-outbound.png)[Close](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#outbound-rule)

    *   [Default Outbound Firewall Rule](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#default-outbound)

        *   [](https://help.zscaler.com/zscaler-client-connector/adding-ruleset)**Action**: Select the action that the filter applies to inbound traffic that doesn’t match any of the outbound firewall rules:
            *   **None**: Use the existing firewall policy (e.g., Windows Defender). This is the default value.

If your host default outbound firewall rule is set to deny, you must ensure that the host firewall allows outbound traffic to your IdP.

            *   **Firewall Allow**: Allow outbound traffic that doesn’t match the outbound firewall rules in the ruleset. You must select this option if a ruleset includes any outbound firewall rules.

![Image 8: Default Outbound Firewall Rule section on Add Ruleset window](https://help.zscaler.com/downloads/zscaler-client-connector/location-based-policies/about-location-based-policies/zclient-connector-add-ruleset-default-outbound.png)[Close](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#default-outbound)

4.   Click **Save**.

After you add a ruleset, you can add it to an app profile on the [Location Policies](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#location-policies) tab and apply it to one or more network types.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/adding-traffic-steering-ip-list -->

Title: Adding a Traffic Steering IP List | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/adding-traffic-steering-ip-list

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Adding a Traffic Steering IP List

You can add a list of IP addresses to use for IP inclusions and exclusions when adding a ruleset for traffic steering to use with location-based policies. To learn more, see [About Location-Based Policies](https://help.zscaler.com/zscaler-client-connector/about-location-based-policies).

This feature is available only for Zscaler Client Connector version 4.8 and later for Windows. Contact Zscaler Support to enable this feature.

The following default lists are provided:

*   **Default IP Inclusion List**: Includes `0.0.0.0/0` for IPv4 Addresses.
*   **Default IP Exclusion List**:

    *   For IPv4 addresses, includes the `224.0.0.0/4` multicast range, the `169.254.0.0/16` link-local range, the `255.255.255.255` broadcast address, and the RFC 1918 default private networks (`10.0.0.0/8`, `172.16.0.0/12`, `192.168.0.0/16`). To learn more, refer to [RFC 1918 Address Allocation for Private Internets](https://datatracker.ietf.org/doc/html/rfc1918).
    *   For IPv6 addresses, includes the `FF00::/8` multicast range, the `FE80::/10` link-local range, and the `FC00::/7` unique local range.

These lists are view only, but you can click **Download CSV** to download a comma-separated value file with the entries in the list. You can use these default lists as is in the rulesets, or you can copy the values to include in a custom list.

To add an IP list:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**Location Based Policies**.
2.   Select the **Traffic Steering IP Lists** tab and click **Add IP List**.

[See image.](https://help.zscaler.com/zscaler-client-connector/adding-traffic-steering-ip-list#traffic-steering-ip-list-tab)

The **Add Traffic Steering IP List** window appears.

[See image.](https://help.zscaler.com/zscaler-client-connector/adding-traffic-steering-ip-list#add-traffic-steering-ip-list-window)

3.   In the **Add Traffic Steering IP List** window:
    *   **Name**: Enter a unique alphanumeric name for your list.
    *   **IPv4 Addresses**: Enter the specific subnets in the following formats:

        *   An IP address, subnet, or wildcard (e.g., `192.0.2.1`, `192.0.2.0/24`, or `*`)
        *   An IP:Port, IP:Port range, or IP:Port wildcard (e.g., `192.0.2.1:80`, `192.0.2.1:80-100`, or `192.0.2.1:*`)
        *   An IP:Port:Protocol or IP:Port:Protocol wildcard (e.g., `192.0.2.1:80:tcp`, `192.0.2.1:80-100:udp`, or `192.0.2.1:80:*`)

You can click **Download CSV** to download a comma-separated value file with the entries in the list, and you can click **Upload CSV** to upload a comma-separated value file that replaces the existing entries. The maximum number of characters is 6,144 (approximately 682 IPv4 addresses).

    *   **IPv6 Addresses**: Enter the specific subnets in the following formats:

        *   An IP address, subnet, or wildcard (e.g., `[2001:0000::]`, `[2001::/32]`, or `[*]`)
        *   An IP:Port, IP:Port range, or IP:Port wildcard (e.g., `[2001:0000::]:80`, `[2001:0000::]:80-100`, or `[2001:0000::]:*`)
        *   An IP:Port:Protocol or IP:Port:Protocol wildcard (e.g., `[2001:0000::]:80:tcp`, `[2001:000::/32]80-100:udp]`, or `[2001:0000::]:80:*`)

You can click **Download CSV** to download a comma-separated value file with the entries in the list, and you can click **Upload CSV** to upload a comma-separated value file that replaces the existing entries. The maximum number of characters is 6,144 (approximately 307 IPv6 addresses).

    *   **List Description**: Enter any notes regarding the list.

4.   Click **Save**.

You can enter a maximum of 300 traffic steering IP lists. After you create a list, you can select it in the IP Inclusion List and IP Exclusion List when [adding a ruleset](https://help.zscaler.com/zscaler-client-connector/adding-ruleset). You must use the list in a ruleset, and you cannot use it directly in an app profile.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/adding-domain-list -->

Title: Adding a Domain List | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/adding-domain-list

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Adding a Domain List

You can add a list of domains to use when adding a ruleset for traffic steering to use with location-based policies. To learn more, see [About Location-Based Policies](https://help.zscaler.com/zscaler-client-connector/about-location-based-policies).

This feature is available only for Zscaler Client Connector version 4.8 and later for Windows. Contact Zscaler Support to enable this feature.

To add a domain list:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**Location Based Policies**.
2.   Select the **Domain Lists** tab and click **Add Domain List**.

[See image.](https://help.zscaler.com/zscaler-client-connector/adding-domain-list#domain-list-tab)

[](https://help.zscaler.com/zscaler-client-connector/adding-domain-list)![Image 2: Domain Lists tab](https://help.zscaler.com/downloads/zscaler-client-connector/location-based-policies/adding-domain-list/zclient-connector-domain-lists-tab.png)

[Close](https://help.zscaler.com/zscaler-client-connector/adding-domain-list#domain-list-tab) 

The **Add Domain List** window appears.
[See image.](https://help.zscaler.com/zscaler-client-connector/adding-domain-list#add-domain-list-window)

![Image 3: Add Domain List window](https://help.zscaler.com/downloads/zscaler-client-connector/location-based-policies/adding-traffic-steering-ip-list/zclient-connector-add-domain-list.png)

[Close](https://help.zscaler.com/zscaler-client-connector/adding-domain-list#add-domain-list-window) 
3.   In the **Add Domain List** window:
    *   **Name**: Enter a unique alphanumeric name for your list.
    *   **Domains**: Enter the domains to use in a ruleset. You can click **Download CSV** to download a comma-separated value file with the entries in the list, and you can click **Upload CSV** to upload a comma-separated value file that replaces the existing entries.

You can enter specific domains (e.g., `google.com`) or enter a wildcard (e.g., `*` or `*.[internaldomain].com`) to include or exclude all or specific DNS domains. For specific domains, up to two levels of subdomains are automatically included or excluded (e.g., if you enter `safemarch.com`, Zscaler Client Connector includes `downloads.safemarch.com` but not `web.downloads.safemarch.com`). Press `Enter` after each entry. You can add multiple items at the same time by separating each item with a comma and then pressing `Enter` when finished. The maximum number of characters is 65,535 in each field.

    *   **List Description**: Enter any notes regarding the list.

4.   Click **Save**.

You can enter a maximum of 300 domain lists. After you create a list, you can select it in Domain Inclusion and Domain Exclusion when [adding a ruleset](https://help.zscaler.com/zscaler-client-connector/adding-ruleset). You must use the list in a ruleset, and you cannot use it directly in an app profile.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/adding-firewall-ip-list -->

Title: Adding a Firewall IP List | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/adding-firewall-ip-list

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Adding a Firewall IP List

You can add a list of host and remote IP addresses, ports, and protocols to use for inbound and outbound firewall rules when adding a ruleset to manage endpoint firewall rules to use with location-based policies. To learn more, see [About Location-Based Policies](https://help.zscaler.com/zscaler-client-connector/about-location-based-policies).

This feature is available only for Zscaler Client Connector version 4.8 and later for Windows. Contact Zscaler Support to enable this feature.

To add a firewall IP list:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**Location Based Policies**.
2.   Select the **Firewall IP Lists** tab and click **Add IP List**.

[See image.](https://help.zscaler.com/zscaler-client-connector/adding-firewall-ip-list#firewall-ip-list-tab)

The **Add Firewall IP List** window appears.

[See image.](https://help.zscaler.com/zscaler-client-connector/adding-firewall-ip-list#add-firewall-ip-list-window)

3.   In the **Add Firewall IP List** window:
    *   **Name**: Enter a unique alphanumeric name for your list.
    *   **IPv4 Addresses**: Enter a 5-tuple value with each field separated by a colon (i.e., `hostIP:hostport:remoteIP:remoteport:protocol`). For example, to define an inbound 5-tuple that allows inbound access from the `172.23.10.0/24` network to the host's default RDP port, enter `*:3389:172.23.10.0/24:*:tcp`.

The following formats are supported:

        *   An IP address, subnet using CIDR notation, or wildcard (e.g., `192.0.2.1`, `192.0.2.0/24`, or `*`)
        *   A port, port range, or port wildcard (e.g., `:80`, `:80-100`, or `:*`)
        *   A protocol or wildcard (e.g., `:tcp`, `:udp`, or `:*`)

You can click **Download CSV** to download a comma-separated value file with the entries in the list, and you can click **Upload CSV** to upload a comma-separated value file that replaces the existing entries. The maximum number of characters is 65,535 (approximately 1,213 IPv4 5-tuples).

    *   **IPv6 Addresses**: Enter a 5-tuple value with each field separated by a colon (i.e., `[hostIP]:hostport:[remoteIP]:remoteport:protocol`).

        *   An IP address, subnet using CIDR notation, or wildcard (e.g., `[2001:db8:1::18]`, `[2001::db8:2::/64]`, `[::/0]`, or `[*]`)
        *   A port, port range, or port wildcard (e.g., `:4000`, `:5060-5061`, or `:*`)
        *   A protocol or wildcard (e.g., `:tcp`, `:udp`, or `:*`)

You can click **Download CSV** to download a comma-separated value file with the entries in the list, and you can click **Upload CSV** to upload a comma-separated value file that replaces the existing entries. The maximum number of characters is 65,535 (approximately 600 IPv6 5-tuples).

    *   **List Description**: Enter any notes regarding the list.

4.   Click **Save**.

You can enter a maximum of 300 firewall IP lists. After you create a list, you can select it in [inbound](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#inbound-rule) and [outbound](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#outbound-rule) firewall rules when adding a ruleset.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/adding-application-ruleset -->

Title: Adding an Application for a Ruleset | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/adding-application-ruleset

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Adding an Application for a Ruleset

You can add an application to use when adding a ruleset to manage endpoint firewall rules to use with location-based policies. To learn more, see [About Location-Based Policies](https://help.zscaler.com/zscaler-client-connector/about-location-based-policies).

This feature is available only for Zscaler Client Connector version 4.8 and later for Windows. Contact Zscaler Support to enable this feature.

To add an application:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**Location Based Policies**.
2.   Select the **Applications** tab and click **Add Application**.

[See image.](https://help.zscaler.com/zscaler-client-connector/adding-application-ruleset#applications-tab)

The **Add Application** window appears.

[See image.](https://help.zscaler.com/zscaler-client-connector/adding-application-ruleset#add-application-window)

3.   In the **Add Application** window:
    *   **Name**: Enter a unique alphanumeric name for your list.
    *   **Path**: Enter a path for the application in one of the following formats:

        *   The absolute path to the application.
        *   The path to the application using built-in OS environment variables (e.g., `%programfiles%` or `%system32%`).

You cannot enter wildcards or directory names in the path. Paths with periods or commas must be enclosed within double quotes. You can enter a maximum of 5 paths per application, separated by commas. Each path can be a maximum of 256 characters.

    *   **List Description**: Enter any notes regarding the list.

4.   Click **Save**.

You can enter a maximum of 300 applications to use in rulesets. After you create an application, you can select it in [inbound](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#inbound-rule) and [outbound](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#outbound-rule) firewall rules when adding a ruleset. You must use the application in a ruleset, and you cannot use it for application-based bypasses directly in the app profile.

---

<!-- source: https://help.zscaler.com/cloud-branch-connector/zscaler-client-connector-vdi-release-summary-2026 -->

Title: Zscaler Client Connector for VDI Release Summary (2026) | Zscaler

URL Source: https://help.zscaler.com/cloud-branch-connector/zscaler-client-connector-vdi-release-summary-2026

Markdown Content:
![Image 1: icon-cloud-connector.svg](blob:http://localhost/6e416d15bcb50c1001e80e392c70c45c)

## Cloud & Branch Connector

### Zscaler Client Connector for VDI Release Summary (2026)

This article provides a summary of all new features and enhancements released per operating system (OS) for the Zscaler Client Connector for VDI on Zscaler Cloud & Branch Connector.

**Select an OS:**

**Select an app version:**

**Select a deployment date:**

* * *

The Zscaler Client Connector for VDI versions for Windows listed below were deployed on the following dates.

**June 01, 2026**

*   Feature Available: Zscaler Client Connector for VDI 1.7.0.14 for Windows

    *   ### **Zscaler Client Connector for VDI 1.7.0.14 Enhancements and Fixes**

        *   Fixes multiple vulnerabilities that allowed for local privilege escalation. (CVSS v3.1 Base Score: 7.3 - High)
        *   Improves handling of non-default root CA certificates. (CVSS v3.1 Base Score: 8.9 - High)
        *   Fixes an authentication bypass issue for Zscaler Client Connector. (CVSS v3.1 Base Score: 7.5 - High)
        *   Fixes slow upload speeds for TCP traffic through the Cloud Connector tunnel.

**March 11, 2026**

*   Feature Available: Zscaler Client Connector for VDI 1.7.0.10 for Windows

    *   ### **Zscaler Client Connector for VDI 1.7.0.10 Enhancements and Fixes**

Fixes an issue where Zscaler Client Connector for VDI added stray characters to the username field, causing the application to not start on some machines.

Feature Available: Zscaler Client Connector for VDI 1.6.0.8 for Windows

    *   ### **Zscaler Client Connector for VDI 1.6.0.8 Enhancements and Fixes**

        *   Fixes an issue where Zscaler Client Connector for VDI failed to start due to a connection error.
        *   Fixes `removeRule` failure issues during data channel reconnection.
        *   Fixes a blue screen of death (BSOD) issue caused by the Windows Filtering Platform (WFP) driver.
        *   Adds the global virtual IP (VIP) address in the mandatory TCP bypass rules.

**February 10, 2026**

*   Feature Available: Zscaler Client Connector for VDI 1.7.0.7 for Windows

    *   ### **Zscaler Client Connector for VDI 1.7.0.7 Enhancements and Fixes**

Fixes an issue that caused intermittent permission issues with WebView2.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/release-upgrade-summary-2026 -->

Title: Release Upgrade Summary (2026) | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/release-upgrade-summary-2026

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Release Upgrade Summary (2026)

This article provides a summary of all new features and enhancements per Zscaler cloud for the Zscaler Client Connector Portal. Zscaler will email a notification to your organization's registered support contacts approximately one week before your cloud is upgraded. To see scheduled maintenance updates for your cloud, visit the [Trust Portal](https://trust.zscaler.com/).

**Select your Zscaler cloud:**

**Select a deployment date:**

* * *

The following service updates were deployed to mobile.zscaler.net on the following dates.

**May 15, 2026**

*   Release Available

    *   ### **Zscaler Client Connector Portal 4.5.4**

        *   Adds validations for wildcard entry for[process-based application bypasses](https://help.zscaler.com/zscaler-client-connector/adding-process-based-applications-bypass-traffic) for Windows to limit asterisks to one or two and to allow only backslashes (not forward slashes), preventing invalid paths that could result in FW/AV errors.
        *   Fixes an issue where, even though the [Use 64-Bit Installer for Windows](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#configure-auto-update) option was selected, the Zscaler Client Connector Portal sent the 32-bit installer instead of the 64-bit installer if the device used Windows 11 and Advanced RISC Machines (ARM) architecture.
        *   Fixes an issue where the [Collect Location Info for ZDX](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-collect-zdx-location-information) field wasn’t visible after enablement for a Authentication Service tenant.
        *   Fixes an issue where the Process Path value in a [Detect Microsoft Defender](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Detect-MS-Defender) device posture profile for Linux was cleared after clicking Edit on the device posture.
        *   Fixes an issue where the Last Seen Connected to ZIA value sometimes displayed as 01/01/1970 or was blank for devices for a Authentication Service tenant.

**April 10, 2026**

*   Release Available

    *   ### **Zscaler Client Connector Portal 4.5.3**

        *   Enhances the Device Posture dashboard to display macOS devices in addition to Windows devices, the top 50 failed device postures, and new widgets that show compliant vs non-compliant information. To learn more, see [Understanding the Zscaler Client Connector Dashboard](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard).
        *   Updates the Failed Posture Devices page to display macOS devices in addition to Windows devices, add an Export option, and remove the options to soft remove a device or view device fingerprint details from this page. To learn more, see [About Failed Posture Devices](https://help.zscaler.com/zscaler-client-connector/about-failed-posture-devices).
        *   Supports the [Device Posture](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-portal-dashboard#device-posture-failure) dashboard for Authentication Service tenants. To learn more, see [Understanding the Zscaler Client Connector Dashboard](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-portal-dashboard).
        *   Fixes an issue where the call into the [Get Devices API](https://automate.zscaler.com/docs/api-reference-and-guides/api-reference/zcc/public-api-controller/get-devices-using-get) returned a `504` timeout error when there is a large number of devices returned in the API response.
        *   Fixes an issue where a forwarding profile change to enable the Same as “On-Trusted Network” option for the Off-Trusted Network and VPN-Trusted Network wasn’t saved on an existing profile if the On-Trusted Network was set to [Enforce Proxy](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#enforce-proxy) with a specific proxy port.
        *   Fixes an issue where updating the business continuity domain name in the DNS TXT record didn’t activate business continuity mode for macOS devices.
        *   Fixes an issue where editing a Windows app profile that contains duplicate VPN gateway entries resulted in a confusing `Server is not available at this time` error message. The message has been replaced with one that explains the duplicate entries problem.
        *   Fixes an issue where, for customers using Zscaler Digital Experience (ZDX) and Zscaler Private Access (ZPA) and who have ZDX entitlement enabled without using posture-based entitlements, a provisioning update could incorrectly disable ZDX entitlements for all users.

**March 13, 2026**

*   Release Available

    *   ### **Zscaler Client Connector Portal 4.5.2**

        *   Extends the maximum time allowed before an uninstall, upgrade, or revert password used in unattended mode expires from 48 hours to 90 days. To learn more, see [Configuring Passwords for Access in Unattended Mode](https://help.zscaler.com/zscaler-client-connector/configuring-passwords-access-unattended-mode).
        *   Fixes an issue where some device details (Last Registration Time, Last Deregistration Time, and MAC Address) didn’t display on the Zscaler Client Connector Registered Device Details window or in the exported CSV file for Authentication Service tenants.
        *   Fixes an issue where usernames for devices in a Removal Pending (inactive) state were not shown with strikethrough text in the App Profile window.
        *   Adds the ability to upload company logos to display on the app and in pop-up notifications and to assign the customized theme to a Windows app profile. To learn more, see [Customizing the Zscaler Client Connector Theme](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-theme).
        *   Fixes an issue where users received a message that the server wasn’t responding when saving an app profile after entering an IP address or an FQDN in the VPN Gateway Bypass field.
        *   Fixes an issue where the Zscaler Internet Access (ZIA) posture was not evaluated due to an incorrectly formatted device posture ID.
        *   Fixes an issue where users received a misleading `Your company is not subscribed` error message instead of an internal server error message when a connection was unstable.
        *   Fixes an issue where the Zscaler Client Connector Portal didn’t send all the Custom IP-Based Application Bypass entries from the app profile to Zscaler Client Connector in the policy.
        *   Fixes an issue where the call into the [Get Devices API](https://automate.zscaler.com/docs/api-reference-and-guides/api-reference/zcc/public-api-controller/get-devices-using-get) returned a `400 Bad Request` error instead of a `404 Not Found` error if the request was for a username using valid syntax but that isn’t in the database.
        *   Fixes an issue where the OS value in the CSV file exported from the [Device Management](https://help.zscaler.com/zscaler-client-connector/about-enrolled-devices) page displayed with unreadable characters.
        *   Updates the CrowdStrike key list used with the [Detect CrowdStrike](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Detect-CrowdStrike) device posture to fix an outdated list.
        *   Fixes an issue where a user could still access Zscaler Private Access (ZPA) even if the device posture check failed if the device was included in more than one device group and the device posture check passed for another device group.
        *   Fixes display issues on the Administration Management page where ZIA admin users weren’t sorted correctly after editing and where switching between tabs didn’t reset to the first page of results.
        *   Mitigates a medium security vulnerability where a list of users could be retrieved by a user from another tenant using an undocumented endpoint.

**February 06, 2026**

*   Release Available

    *   ### **Zscaler Client Connector Portal 4.5.1**

        *   Adds an option to clear the Kerberos ticket after Zscaler Private Access (ZPA) reauthentication to force a Kerberos ticket refresh. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#mac-adv).
        *   Adds an option to immediately reauthenticate ZPA after ZPA authentication expires due to an action selected in the [Force ZPA authentication to expire](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#auth) option instead of waiting for the user’s next ZPA access attempt. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#auth).
        *   Updates the Zscaler Client Connector Portal to always use the [V8 JavaScript based PAC Parser](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#pac-proxy) regardless of the setting in a user’s app profile.

If your organization has multiple versions of Zscaler Client Connector installed across devices, devices with versions 4.8 and earlier installed continue to use the PAC parser selected in the app profile.

        *   Adds an option to delay pop-up notifications for device posture failures after the user tunnel is established. To learn more, see [Configuring Notification Templates for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-notification-templates-zscaler-client-connector).
        *   Adds a device posture check for specific Zscaler Client Connector versions for macOS. To learn more, see [Configuring Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles).
        *   Adds the ability to enter process-based applications for macOS and select them in the [Global Bypasses](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#mac-global) section of the macOS app profile. To learn more, see [Adding Process-Based Applications to Bypass Traffic](https://help.zscaler.com/zscaler-client-connector/adding-process-based-applications-bypass-traffic).
        *   Adds an option to block all traffic and allow only PAC file exclusions when in strict enforcement mode. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#pac-proxy).
        *   Adds an option to check for specific software versions for the following device postures for Windows: Process Check, Detect Carbon Black, Detect CrowdStrike, Detect SentinelOne, and Detect Microsoft Defender. To learn more, see [Configuring Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles).
        *   Adds support for Zscaler to block enrollment of devices running very old versions of Zscaler Client Connector (versions 1.x and 2.x for Windows, macOS, iOS, and Android and version 1.5 and earlier for Linux). To learn more about Zscaler deprecation of older versions and the timeline, see the [Deprecation of Enrollment trust post](https://trust.zscaler.com/zscaler.net/posts/25466).
        *   Updates the display order for the [Parallel IPv4 and IPv6 DNS requests options](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#DNS) so that the Enabled option displays before the Disabled option.
        *   Updates the message that displays for customers who access the [Business Continuity](https://help.zscaler.com/zscaler-client-connector/about-business-continuity) page in the Zscaler Client Connector Portal but don’t have Business Continuity enabled in Zscaler Private Access (ZPA) from an error message to an advisory message.
        *   Fixes an issue where, when users expanded the Configure System Proxy Settings for one type of trusted network on the [forwarding profile](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector), the section expanded for all trusted network types.
        *   Fixes an issue where a group did not appear in the User Groups listed on the [App Store Group Policy](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#configure-auto-update) window when it was already used in another policy.
        *   Fixes an issue where devices couldn’t be soft removed or force removed from Authentication Service tenants using OneAPI.
        *   Fixes a validation error that occurred when entering a CIDR (Classless Inter-Domain Routing) range in the [Egress IP condition](https://help.zscaler.com/zscaler-client-connector/configuring-trusted-networks-zscaler-client-connector) for a trusted network (e.g., `190.0.2.0/24`).
        *   Fixes an issue where the Service Status CSV file exported from the [Device Management](https://zscaler-client-connector/about-enrolled-devices) page was empty for Authentication Service tenants.
        *   Fixes an issue where, after filtering the list of machine tunnels by the machine tunnel token, the CSV file exported from the [Machine Tunnels](https://help.zscaler.com/zscaler-client-connector/about-machine-tunnels) page was empty.
        *   Fixes an issue where the call into the [Delete Policy API](https://automate.zscaler.com/docs/api-reference-and-guides/api-reference/zcc/public-api-controller/delete-policy-using-delete) returned a `400` error and failed to delete the app profile.
        *   Fixes an issue where the Last Seen Connected to ZIA date and time column values were blank on the [Device Management](https://help.zscaler.com/zscaler-client-connector/about-enrolled-devices#Item1) page, even though there was a last known date and time of connection in the [device fingerprint details](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device).
        *   Fixes an issue where some users were still logging in to the previous tenant after their company domain was migrated over to a new Authentication Service tenant.
        *   Fixes an issue where users experienced machine tunnel authentication errors during re-enrollment after the machine tunnel certificate has expired.
        *   Fixes an issue where, when searching in the User Groups field on an app profile, search results were returned only when users pressed the Enter key and not when they clicked the search icon.
        *   Fixes an issue where Zscaler Client Connector didn’t prompt Android users for the password if they tapped Disable ZIA, Logout, or Uninstall on the app even though the passwords were configured in the [app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#android-otp).
        *   Fixes an issue where [device groups](https://help.zscaler.com/zscaler-client-connector/about-device-groups) couldn’t be deleted on Authentication Service tenants.
        *   Fixes an issue where users received a `Your Session has expired` message when creating an app profile if the Location-Based Policies feature was enabled but the [Location-Based Policies](https://help.zscaler.com/zscaler-client-connector/adding-roles) admin role permission was set to None.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2026 -->

Title: Client Connector App Release Summary (2026) | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2026

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Client Connector App Release Summary (2026)

**Select an OS:**

**Select an app version:**

**Select a deployment date:**

* * *

The Client Connector app versions for Windows listed below were deployed on the following dates.

**June 01, 2026**

*   Release Available: Client Connector 4.8.0.232 for Windows

    *   ### **Zscaler Client Connector 4.8.0.232 Enhancements and Fixes**

        *   Fixes an authentication bypass issue for Zscaler Client Connector to Zscaler Client Connector Portal-only communications. (CVSS v3.1 Base Score: 9.1 - Critical)
        *   Mitigates multiple remote code execution vulnerabilities. (CVSS v3.1 Base Score: 9.1 - Critical)
        *   Fixes multiple vulnerabilities that allowed for local privilege escalation. (CVSS v3.1 Base Score: 8.8 - High)
        *   Fixes a local and kernel denial-of-service attack vector. (CVSS v3.1 Base Score: 8.8 - High)
        *   Fixes an issue where traffic bypassed for URLs with hostnames greater than 64 characters resulted in truncated hostnames in Zscaler Internet Access (ZIA) logs when [Flow Logging](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#notification) was enabled.
        *   Fixes an issue where users couldn't change their Windows password while Zscaler Client Connector was connected over a machine tunnel in Strict Enforcement mode.
        *   Fixes an issue where domain profile detection failed after a Zscaler Private Access (ZPA) reauthentication timeout, leaving Windows Defender Firewall devices on the Public profile.
        *   Fixes an issue where Zscaler Client Connector intermittently disconnected due to repeatedly restarting the tunnel.
        *   Fixes an issue where incorrect handling of DNS resolution over TCP for ZPA domains caused intermittent application access failures.
        *   Fixes an issue that caused intermittent DNS timeouts and sporadic connection instability for VPN traffic bypassed using process-based application bypasses.
        *   Fixes an issue where Zscaler Client Connector failed to properly handle bursts of DNS requests, resulting in intermittent DNS failures and failures with Cisco ISE postures.
        *   Fixes an issue where a Windows driver change resulted in intermittent connectivity for devices with checksum offloading enabled in the network adapter settings.
        *   Fixes an issue where captive portal detection failed on networks using captive portals that return chunked HTTP responses (uncommon on guest Wi-Fi).
        *   Fixes an issue where captive portal detection failed when a device connected to a captive portal that redirected Zscaler Client Connector to an HTTPS site on a non-standard port or to a site with an atypical URL path (e.g., no leading slash).
        *   Fixes an issue where IP inclusions weren’t enforced after a device resumed from a multi-day sleep, resulting in internet traffic bypassing Zscaler Internet Access (ZIA) after switching network types.
        *   Fixes an issue where, even if the Restore LWF Adapter Binding feature was enabled, Zscaler Client Connector didn't attempt to rebind the adapter if the initial attempt failed.

Release Available: Client Connector 4.7.0.317 for Windows

    *   ### **Zscaler Client Connector 4.7.0.317 Enhancements and Fixes**

        *   Fixes an authentication bypass issue for Zscaler Client Connector to Zscaler Client Connector Portal-only communications. (CVSS v3.1 Base Score: 9.1 - Critical)
        *   Mitigates multiple remote code execution vulnerabilities. (CVSS v3.1 Base Score: 9.1 - Critical)
        *   Fixes multiple vulnerabilities that allowed for local privilege escalation. (CVSS v3.1 Base Score: 8.8 - High)
        *   Fixes a local and kernel denial-of-service attack vector. (CVSS v3.1 Base Score: 8.8 - High)
        *   Fixes an issue where, if Zscaler Client Connector is upgraded on a device with a Windows reboot pending (e.g., after a Windows update), there could be a delay in installing the Zscaler Client Connector driver which could cause the system to enter an error state (display a blue screen) after the upgrade or system restart.
        *   Fixes an issue where traffic bypassed for URLs with hostnames greater than 64 characters resulted in truncated hostnames in Zscaler Internet Access (ZIA) logs when [Flow Logging](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#notification) was enabled.
        *   Fixes an issue where users couldn't change their Windows password while Zscaler Client Connector was connected over a machine tunnel in Strict Enforcement mode.
        *   Updates the LWF driver so it can be accessed only by a local admin Windows user
        *   Fixes an issue where domain profile detection failed after a Zscaler Private Access (ZPA) reauthentication timeout, leaving Windows Defender Firewall devices on the Public profile.
        *   Fixes an issue where incorrect handling of DNS resolution over TCP for ZPA domains caused intermittent application access failures.
        *   Fixes an issue that caused intermittent DNS timeouts and sporadic connection instability for VPN traffic bypassed using process-based application bypasses.
        *   Fixes an issue where Zscaler Client Connector failed to properly handle bursts of DNS requests, resulting in intermittent DNS failures and failures with Cisco ISE postures.
        *   Fixes an issue where a Windows driver change resulted in intermittent connectivity for devices with checksum offloading enabled in the network adapter settings.
        *   Fixes an issue where, even if the Restore LWF Adapter Binding feature was enabled, Zscaler Client Connector didn't attempt to rebind the adapter if the initial attempt failed.

Release Available: Client Connector 4.6.0.457 for Windows

    *   ### **Zscaler Client Connector 4.6.0.457 Enhancements and Fixes**

        *   Fixes an authentication bypass issue for Zscaler Client Connector to Zscaler Client Connector Portal-only communications. (CVSS v3.1 Base Score: 9.1 - Critical)
        *   Mitigates multiple remote code execution vulnerabilities. (CVSS v3.1 Base Score: 9.1 - Critical)
        *   Fixes multiple vulnerabilities that allowed for local privilege escalation. (CVSS v3.1 Base Score: 8.8 - High)
        *   Fixes a local and kernel denial-of-service attack vector. (CVSS v3.1 Base Score: 8.8 - High)
        *   Fixes an issue where users couldn't change their Windows password while Zscaler Client Connector was connected over a machine tunnel in Strict Enforcement mode.
        *   Fixes out-of-bound reads that could occur when parsing `ClientHello` messages, which could result in the ZSATunnel crashing.

*   Release in Limited Availability: Client Connector 4.9.0.372 for Windows

    *   ### **Zscaler Client Connector 4.9.0.372 Enhancements and Fixes**

        *   Fixes an authentication bypass issue for Zscaler Client Connector to Zscaler Client Connector Portal-only communications. (CVSS v3.1 Base Score: 9.1 - Critical)
        *   Mitigates multiple remote code execution vulnerabilities. (CVSS v3.1 Base Score: 9.1 - Critical)
        *   Fixes multiple vulnerabilities that allowed for local privilege escalation. (CVSS v3.1 Base Score: 8.8 - High)
        *   Fixes a local and kernel denial-of-service attack vector. (CVSS v3.1 Base Score: 8.8 - High)
        *   Fixes an issue where, if Zscaler Client Connector is upgraded on a device with a Windows reboot pending (e.g., after a Windows update), there could be a delay in installing the Zscaler Client Connector driver which could cause the system to enter an error state (display a blue screen) after the upgrade or system restart.
        *   Fixes an issue where traffic bypassed for URLs with hostnames greater than 64 characters resulted in truncated hostnames in Zscaler Internet Access (ZIA) logs when [Flow Logging](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#notification) was enabled.
        *   Fixes an issue where users couldn't change their Windows password while Zscaler Client Connector was connected over a machine tunnel in Strict Enforcement mode.
        *   Fixes an issue where domain profile detection failed after a Zscaler Private Access (ZPA) reauthentication timeout, leaving Windows Defender Firewall devices on the Public profile.
        *   Fixes an issue where Zscaler Client Connector intermittently disconnected due to repeatedly restarting the tunnel.
        *   Fixes an issue where incorrect handling of DNS resolution over TCP for ZPA domains caused intermittent application access failures.
        *   Fixes an issue that caused intermittent DNS timeouts and sporadic connection instability for VPN traffic bypassed using process-based application bypasses.
        *   Fixes an issue where Zscaler Client Connector failed to properly handle bursts of DNS requests, resulting in intermittent DNS failures and failures with Cisco ISE postures.
        *   Fixes an issue where captive portal detection failed on networks using captive portals that return chunked HTTP responses (uncommon on guest Wi-Fi).
        *   Fixes an issue where captive portal detection failed when a device connected to a captive portal that redirected Zscaler Client Connector to an HTTPS site on a non-standard port or to a site with an atypical URL path (e.g., no leading slash).
        *   Fixes an issue where IP inclusions weren’t enforced after a device resumed from a multi-day sleep, resulting in internet traffic bypassing ZIA after switching network types.
        *   Fixes an issue where, even if the Restore LWF Adapter Binding feature was enabled, Zscaler Client Connector didn't attempt to rebind the adapter if the initial attempt failed.

**May 13, 2026**

*   Release Available: Client Connector 4.8.0.190 for Windows

    *   ### **Zscaler Client Connector 4.8.0.190 Enhancements and Fixes**

Fixes an issue where, if Zscaler Client Connector is upgraded on a device with a Windows reboot pending (e.g., after a Windows update), there could be a delay in installing the Zscaler Client Connector driver which could cause the system to enter an error state (display a blue screen) after the upgrade or system restart.

**May 01, 2026**

*   Release Available: Client Connector 4.8.0.172 for Windows

    *   ### **Zscaler Client Connector 4.8.0.172 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue affecting the Zscaler Client Connector filter driver. In certain scenarios, the issue could cause the system to enter an error state (display a blue screen) during a Zscaler Client Connector upgrade or system startup. If you encounter this issue, contact Zscaler Support for assistance. The issue has been corrected on Zscaler Client Connector version 4.8.0.190.

        *   Fixes an intermittent issue where the LTE cellular adapter was disconnected after a driver upgrade during a Zscaler Client Connector upgrade and didn't reconnect until the user reset the adapter.
        *   Fixes an issue where Zscaler Private Access (ZPA) remained in an `Authentication Required` state after clicking Authenticate for a partner tenant when the tenant's ZPA re-registration was pending.
        *   Fixes an issue where users received a `Private Access is Connected` notification in the [Notifications window](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#win-notification) in the app in addition to the `Private Access is Connected (Business Continuity mode)` notification when in business continuity.
        *   Fixes an issue where, for users with an app profile with a [notification template](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#general) selected, the Show All Notifications and Show Private Access Reauthentication Notification settings on the [More window](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector) in the app weren't reset to the default values from the [notification template](https://help.zscaler.com/zscaler-client-connector/configuring-notification-templates-zscaler-client-connector) after upgrading.
        *   Fixes an issue where [Inline Web DLP](https://help.zscaler.com/zia/configuring-euns-inline-web-dlp) and [Endpoint DLP](https://help.zscaler.com/zia/configuring-euns-endpoint-dlp) notifications weren't defined as critical, resulting in both notifications being suppressed for users with the Show notification pop-ups option disabled.
        *   Fixes an issue where the [Deception service didn't restart](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#endpoint-deception-verify-zd-service-status-client-connector) immediately after users logged out of and then logged back in to Zscaler Client Connector.
        *   Fixes an issue where the original system proxy settings were not restored due to a crash when [Cache System Proxy on Startup](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#pac-proxy) was enabled.
        *   Fixes an issue where users could experience a blue screen after uninstalling the Eggplant Functional application when anti-tampering was enabled.
        *   Fixes an issue where Endpoint Data Loss Prevention (DLP) wasn't enabled after starting the Zscaler Client Connector app if a previous attempt to uninstall Zscaler Client Connector didn't finish correctly.

Release Available: Client Connector 4.7.0.248 for Windows

    *   ### **Zscaler Client Connector 4.7.0.248 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue affecting the Zscaler Client Connector filter driver. In certain scenarios, the issue could cause the system to enter an error state (display a blue screen) during a Zscaler Client Connector upgrade or system startup. If you encounter this issue, contact Zscaler Support for assistance. The issue has been corrected on Zscaler Client Connector version 4.7.0.317.

        *   Fixes an intermittent issue where the LTE cellular adapter was disconnected after a driver upgrade during a Zscaler Client Connector upgrade and didn't reconnect until the user reset the adapter.
        *   Fixes an issue where Zscaler Private Access (ZPA) remained in an `Authentication Required` state after clicking Authenticate for a partner tenant when the tenant's ZPA re-registration was pending.
        *   Fixes an issue where users received a `Private Access is Connected` notification in the [Notifications window](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#win-notification) in the app in addition to the `Private Access is Connected (Business Continuity mode)` notification when in business continuity.
        *   Fixes an issue where the [Deception service didn't restart](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#endpoint-deception-verify-zd-service-status-client-connector) immediately after users logged out of and then logged back in to Zscaler Client Connector.
        *   Fixes an issue where users received an invalid password error after entering the correct Exit Password if they logged out of Zscaler Client Connector before trying to exit the app.
        *   Fixes an issue where users could experience a blue screen after uninstalling the Eggplant Functional application when anti-tampering was enabled.
        *   Fixes an issue where Endpoint Data Loss Prevention (DLP) wasn't enabled after starting the Zscaler Client Connector app if a previous attempt to uninstall Zscaler Client Connector didn't finish correctly.

**April 30, 2026**

*   Release in Limited Availability: Client Connector 4.9.0.331 for Windows

    *   ### **Zscaler Client Connector 4.9.0.331 Enhancements and Fixes**

Fixes an issue where Zscaler Client Connector didn't fail over to the [secondary DNS server on the network segment](https://help.zscaler.com/zpa/configuring-network-segments) if the device's network adapter had only one DNS server configured and the primary DNS server was unresponsive.

**April 29, 2026**

*   Release Available: Client Connector 4.6.0.418 for Windows

    *   ### **Zscaler Client Connector 4.6.0.418 Enhancements and Fixes**

        *   Fixes an issue where the [Deception service didn't restart](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#endpoint-deception-verify-zd-service-status-client-connector) immediately after users logged out of and then logged back in to Zscaler Client Connector.
        *   Fixes an issue where users received an invalid password error after entering the correct Exit Password if they logged out of Zscaler Client Connector before trying to exit the app.
        *   Fixes an issue where users could experience a blue screen after uninstalling the Eggplant Functional application when anti-tampering was enabled.
        *   Fixes an issue where Endpoint Data Loss Prevention (DLP) wasn't enabled after starting the Zscaler Client Connector app if a previous attempt to uninstall Zscaler Client Connector didn't finish correctly.

**April 21, 2026**

*   Release in Limited Availability: Client Connector 4.9.0.330 for Windows

    *   ### **Zscaler Client Connector 4.9.0.330 Enhancements and Fixes**

        *   Fixes an issue where the original system proxy settings were not restored due to a crash when [Cache System Proxy on Startup](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#pac-proxy) was enabled.
        *   Fixes an issue where, for users with an app profile with a [notification template](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#general) selected, the Show All Notifications and Show Private Access Reauthentication Notification settings on the [More window](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector) in the app weren't reset to the default values from the [notification template](https://help.zscaler.com/zscaler-client-connector/configuring-notification-templates-zscaler-client-connector) after upgrading.
        *   Fixes an issue where users could experience a blue screen after uninstalling the Eggplant Functional application when anti-tampering was enabled.
        *   Fixes an issue where [Inline Web DLP](https://help.zscaler.com/zia/configuring-euns-inline-web-dlp) and [Endpoint DLP](https://help.zscaler.com/zia/configuring-euns-endpoint-dlp) notifications weren't defined as critical, resulting in both notifications being suppressed for users with the Show notification pop-ups option disabled.
        *   Fixes an issue where Endpoint Data Loss Prevention (DLP) wasn't enabled after starting the Zscaler Client Connector app if a previous attempt to uninstall Zscaler Client Connector didn't finish correctly.
        *   Fixes an issue where a Windows driver change resulted in intermittent connectivity for devices with checksum offloading enabled in the network adapter settings.
        *   Fixes an LWF driver issue that could cause performance degradation when downloading over Zscaler Tunnel (Z-Tunnel) 2.0.

**April 07, 2026**

*   Release Available: Client Connector 4.7.0.239 for Windows

    *   ### **Zscaler Client Connector 4.7.0.239 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue affecting the Zscaler Client Connector filter driver. In certain scenarios, the issue could cause the system to enter an error state (display a blue screen) during a Zscaler Client Connector upgrade or system startup. If you encounter this issue, contact Zscaler Support for assistance. The issue has been corrected on Zscaler Client Connector version 4.7.0.317.

Fixes performance issues that occurred when accessing virtual machines if Hyper-V was enabled on the device. 

**March 31, 2026**

*   Release Available: Client Connector 4.8.0.156 for Windows

    *   ### **Zscaler Client Connector 4.8.0.156 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue affecting the Zscaler Client Connector filter driver. In certain scenarios, the issue could cause the system to enter an error state (display a blue screen) during a Zscaler Client Connector upgrade or system startup. If you encounter this issue, contact Zscaler Support for assistance. The issue has been corrected on Zscaler Client Connector version 4.8.0.190.

        *   Fixes performance issues that occurred when accessing virtual machines if Hyper-V was enabled on the device.
        *   Fixes an issue where VPN Gateway Bypasses weren't updated after switching from the machine tunnel to the user tunnel.

Release Available: Client Connector 4.6.0.410 for Windows

    *   ### **Zscaler Client Connector 4.6.0.410 Enhancements and Fixes**

        *   Fixes performance issues that occurred when accessing virtual machines if Hyper-V was enabled on the device.
        *   Fixes performance issues that occurred after enabling a Hyper-V external switch with a Wi-Fi adapter.
        *   Fixes out-of-bound reads that could occur when parsing ClientHello messages, which could result in the ZSATunnel crashing.
        *   Fixes a driver issue that could cause process-based bypasses, the Block Domain Profile Detection feature, the captive portal lockdown feature, and fail-close settings to work incorrectly.
        *   Fixes a tunnel IP handling issue that caused the VPN (for Legacy Apps) feature to not work, resulting in a blank VPN Tunnel section on the Private Access window in the app.
        *   Fixes an issue where Zscaler Client Connector didn't refresh the driver state after the device woke from sleep, causing the app to display a `Driver Error` even though it was connected to the Zscaler service.

*   Release in Limited Availability: Client Connector 4.9 for Windows

    *   ### **Zscaler Client Connector 4.9 Enhancements and Fixes**

        *   Adds support for Zscaler Deception service entitlement for Authentication Service tenants.
        *   Supports sending the network type for the Network Share, Printing, and Device Control channels when creating policy rules for Endpoint Data Loss Prevention (DLP). To learn more, see [Configuring Endpoint DLP Policy Rules](https://help.zscaler.com/zia/configuring-endpoint-dlp-policy-rules).
        *   Supports tunneling Azure Private Link domains over Zscaler Private Access (ZPA) by extracting the CNAME records from the DNS response and matching it against Zscaler Private Access (ZPA) applications. To learn more, see [Configuring Private Link Domains](https://help.zscaler.com/zpa/configuring-private-link-domains).
        *   Supports always using the [V8 JavaScript based PAC Parser](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#pac-proxy) regardless of the setting in a user’s app profile. This change is in support of [deprecating the Legacy (SpiderMonkey-based) PAC parser](https://help.zscaler.com/eos-eol/end-support-spidermonkey-based-pac-parser).

If your organization has multiple versions of Zscaler Client Connector installed across devices, devices with versions 4.8 and earlier installed continue to use the PAC parser selected in the app profile.

        *   Supports using a secondary DNS server as a backup in network segments configured for VPN (for Legacy Apps). To learn more, see [Configuring Network Segments](https://help.zscaler.com/zpa/configuring-network-segments).
        *   Supports immediate ZPA reauthentication after ZPA authentication expires due to an action selected in the [Force ZPA authentication to expire](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#auth) option instead of waiting for the user’s next ZPA access attempt. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#auth).
        *   Supports delaying pop-up notifications for device posture failures after the user tunnel is established. To learn more, see [Configuring Notification Templates for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-notification-templates-zscaler-client-connector).
        *   Supports selecting whether to send the UPN (User Principal Name) or the SAM (Security Account Manager) account name in the `login_hint` parameter used to automatically populate the username. To learn more, see [Configuring Automatic Username Population for IdP Authentication](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-username-population-idp-authentication).
        *   Adds the ability to update some installation parameters using the CLI without having to reinstall Zscaler Client Connector. To learn more, see [Interacting with Zscaler Client Connector Remotely](https://help.zscaler.com/zscaler-client-connector/interacting-zscaler-client-connector-remotely).
        *   Streamlines the end user experience of pop-up notifications by adding system tray options to pause notifications, rewording existing notifications for clarity, and removing unnecessary notifications. To learn more, see [Using Zscaler Client Connector.](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector)
        *   Supports blocking all traffic and allowing only PAC file exclusions when in strict enforcement mode. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#pac-proxy).
        *   Supports checking for specific software versions for the following device postures: Process Check, Detect Carbon Black, Detect CrowdStrike, Detect SentinelOne, and Detect Microsoft Defender. To learn more, see [Configuring Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles).
        *   Supports controlling how Zscaler Client Connector interacts with hardware offloading on network adapters to resolve compatibility issues. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#adv).
        *   Supports customizing the company logos and company name that displays on the app and in pop-up notifications. To learn more, see [Customizing the Zscaler Client Connector Theme](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-theme).
        *   Fixes an issue where traffic was intermittently sent to the secondary Public Service Edge instead of the primary Public Service Edge after the device woke from sleep.
        *   Fixes an issue where the Block Domain Profile Detection feature didn't correctly block domain profile detection when switching to a network type with the feature enabled if the policy also had process-based bypasses enabled.
        *   Fixes an issue where the fail-close lockdown settings didn't apply along with captive portal lockdown if the policy also had process-based bypasses enabled.
        *   Improves the retrial logic of the VPN (for Legacy Apps) client in cases where the VPN gateway port is unreachable.
        *   Fixes an issue when using an SCCM virtual adapter where DNS responses could be delayed after switching networks if the [Send Location Hint to Client Connector](https://help.zscaler.com/zpa/adding-ip-ranges) option was enabled.
        *   Fixes an issue where Zscaler Client Connector continued to use DNS suffixes from ZPA even after switching to a network type with a forwarding mode of None.
        *   Fixes an issue where Zscaler Client Connector incorrectly allowed fail-close settings on the app profile to control network lockdown even if the Install WFP Driver option was disabled.
        *   Updates Zscaler Client Connector to monitor changes to the system-level proxy configuration and to enforce the Zscaler proxy settings based on the forwarding profile.
        *   Fixes an issue where an `Internal Error` message displayed after users clicked Verify Now in the Zscaler Client Connector app for step-up authentication for customers using the ZPA service and not the Zscaler Internet Access (ZIA) service.
        *   Fixes an issue where, even if the device posture for posture-based service entitlement passed, ZIA was not enabled after enrollment if the user's app profile had Business Continuity enabled.
        *   Adds support for using registry keys with the REG_BINARY type with the Registry Key device posture profile.
        *   Fixes an issue where Zscaler Client Connector couldn't retrieve the PAC file when the source was the HKEY_CURRENT_USER Registry location provided in the forwarding profile.
        *   Fixes an issue where Zscaler Client Connector bypassed non-DNS requests on port 53.
        *   Fixes an issue where trusted network detection using the hostname and IP address criteria could cause a delay in switching between networks when using Zscaler Tunnel (Z-Tunnel) 2.0.
        *   Fixes an issue where the app didn't display a device posture failure notification from ZPA if the Enable ZIA Notifications option was disabled.
        *   Fixes a tunnel crash that occurred after upgrading which resulted in Zscaler Client Connector remaining in a Connecting state.
        *   Fixes an issue where DNS requests for domains added in the Domain Inclusions field in App Profiles were bypassed instead of being routed through Z-Tunnel 2.0 on IPv4-only (non-dual stack) networks.
        *   Fixes a timing issue that caused a delay in Zscaler Client Connector bypassing a URL after it was added to the app profile PAC file.
        *   Fixes an issue where Zscaler Client Connector didn’t bypass process-based applications with an environment variable (e.g., `%LocalAppData%`) in the application path.
        *   Fixes a timing issue where the custom app profile PAC file was not processed in time at startup, causing traffic to be forced through the default gateway domain without applying relevant bypasses and rules of the PAC file.
        *   Mitigates a vulnerability where specific session timeout conditions allowed for the bypass of application exit protections. (CVSS v3.1 Base Score: 3.2 - Low)
        *   Fixes an issue where traffic to a domain bypassed in the PAC file could be sent to the Zscaler service when an internal Health Check port was reused by another application.
        *   Fixes an issue where, if the forwarding profile was set to bypass DNS traffic on a trusted network and send the traffic to ZPA on an off-trusted network, network detection caused Zscaler Client Connector to send some DNS traffic to ZPA while on a trusted network.
        *   Fixes an issue where, if automatic reauthentication failed, users received a timeout error when manually reauthenticating.
        *   Fixes an issue where devices with the Domain Joined posture profile always passed the posture check, even if the domain name didn't match or the device wasn't domain joined.
        *   Fixes an issue that caused an `Internal Error` message on the Data Protection window in the app for customers using Endpoint Data Loss Prevention (DLP).
        *   Updates the anti-tampering installation files to add protection to Endpoint Data Loss Prevention (DLP) internal files.
        *   Fixes an issue where a trusted network defined by the Hostname and IP condition wasn't detected because the Zscaler Client Connector response included both the `CName` and the `ipaddress` in the response.
        *   Fixes an issue where the Zscaler proxy wasn't enforced in accordance with the forwarding profile when the profile was set to Enforce Proxy.
        *   Fixes an issue where the policy download might not occur immediately after upgrading Zscaler Client Connector, resulting in the app displaying in French if the system language was set to French but the [Client Connector UI Language Settings](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#UI-language-settings) was set to English.
        *   Fixes an issue that could cause FW/AV errors on Windows 11 due to an accumulation of App Container loopback exemptions when the Disable Loopback Restriction option was enabled.
        *   Fixes an issue where confirmation messages from DLP were delayed or not displayed.
        *   Fixes a DNS routing issue that caused connectivity problems for applications accessed over VPN (for Legacy Apps) on a trusted network when the DNS server was defined as an app segment.
        *   Fixes an issue where Zscaler Client Connector could bypass traffic on ports 53 and 67.
        *   Fixes an issue where the `Reauthenticate before your access expires` notification displayed for users on an On-Trusted Network with ZPA disabled.
        *   Fixes out-of-bound reads that could occur when parsing ClientHello messages, which could result in the ZSATunnel crashing.
        *   Fixes inconsistent behavior where, when reconnecting to a ZPA application through an app segment with the Double Encryption option enabled, Zscaler Client Connector closed the app client connection immediately instead of holding the connections as it does when the option is disabled.
        *   Mitigates a vulnerability where specific path validation conditions allowed for elevated write privileges. (CVSS v3.1 Base Score: 6.1 - Medium)
        *   Fixes an issue where, when using the packet filter-based driver, Zscaler Client Connector added a route for 100.64.0.2 when ZPA connected but did not delete it when ZPA was turned off, which sometimes caused problems with domain profile detection.
        *   Fixes an issue where, if the [Default Outbound Firewall Rule](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#default-outbound) was set to Firewall Allow in the Zscaler Endpoint Firewall config and [Block Domain Profile Detection](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#firewall) was enabled for any network type, the domain profile detection traffic wasn't blocked as expected after a network change.
        *   Fixes a driver issue that could cause process-based bypasses, the Block Domain Profile Detection feature, the captive portal lockdown settings, and [fail-close settings](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#zcc-fail-close-settings) to work incorrectly.
        *   Fixes an issue where, if [Automatic ZPA Reauthentication](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication) was enabled and auto reauthentication was initiated after the device woke from sleep, users had to attempt a manual authentication and received a timeout error.
        *   Fixes an issue where, after an application retried DNS requests using the same source port (e.g., due to heavy load or ZPA evaluation), a subsequent DNS request using that source port could be bypassed.
        *   Fixes a tunnel IP handling issue that caused the VPN (for Legacy Apps) feature to not work, resulting in a blank VPN Tunnel section on the Private Access window in the app.
        *   Fixes an issue where Zscaler Client Connector didn't refresh the driver state after the device woke from sleep, causing the app to display a `Driver Error` even though it was connected to the Zscaler service.

**March 17, 2026**

*   Release Available: Client Connector 4.8.0.151 for Windows

    *   ### **Zscaler Client Connector 4.8.0.151 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue affecting the Zscaler Client Connector filter driver. In certain scenarios, the issue could cause the system to enter an error state (display a blue screen) during a Zscaler Client Connector upgrade or system startup. If you encounter this issue, contact Zscaler Support for assistance. The issue has been corrected on Zscaler Client Connector version 4.8.0.190.

        *   Fixes performance issues that occurred after enabling Hyper-V and creating an external switch with a Wi-Fi adapter.
        *   Fixes ZSATunnel crash issues that could occur when parsing ClientHello messages.
        *   Fixes an issue where, if the [Default Outbound Firewall Rule](https://help.zscaler.com/zscaler-client-connector/adding-ruleset#default-outbound) was set to Firewall Allow in the Zscaler Endpoint Firewall config and [Block Domain Profile Detection](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#firewall) was enabled for any network type, the Block Domain Profile Detection feature was broken.
        *   Fixes a driver issue that could cause the process-based bypass feature, the Block Domain Profile Detection feature, the captive portal lockdown feature, and [fail-close settings](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#zcc-fail-close-settings) to work incorrectly.
        *   Fixes an issue where, if [Automatic ZPA Reauthentication](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication) was enabled and auto reauthentication was initiated after the device woke from sleep, users received a timeout error and had to attempt manual authentication.
        *   Fixes an issue where DNS requests could be bypassed if, during periods of heavy loading such as connecting to Zscaler Private Access (ZPA), an application retried the requests using the same source port.
        *   Fixes a tunnel IP handling issue that caused the VPN (for Legacy Apps) feature to not work, resulting in a blank VPN Tunnel section on the [Private Access](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector#win) window in the app.
        *   Fixes an issue where Zscaler Client Connector didn't refresh the driver state after the device woke from sleep, causing the app to display a `Driver Error` even though it was connected to the Zscaler service.

Release Available: Client Connector 4.7.0.232 for Windows

    *   ### **Zscaler Client Connector 4.7.0.232 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue affecting the Zscaler Client Connector filter driver. In certain scenarios, the issue could cause the system to enter an error state (display a blue screen) during a Zscaler Client Connector upgrade or system startup. If you encounter this issue, contact Zscaler Support for assistance. The issue has been corrected on Zscaler Client Connector version 4.7.0.317.

        *   Fixes performance issues that occurred after enabling Hyper-V and creating an external switch with a Wi-Fi adapter.
        *   Fixes ZSATunnel crash issues that could occur when parsing ClientHello messages.
        *   Fixes a driver issue that could cause the process-based bypass feature, the Block Domain Profile Detection feature, the captive portal lockdown feature, and [fail-close settings](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#zcc-fail-close-settings) to work incorrectly.
        *   Fixes a tunnel IP handling issue that caused the VPN (for Legacy Apps) feature to not work, resulting in a blank VPN Tunnel section on the [Private Access](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector#win) window in the app.
        *   Fixes an issue where Zscaler Client Connector didn't refresh the driver state after the device woke from sleep, causing the app to display a `Driver Error` even though it was connected to the Zscaler service.

**February 27, 2026**

*   Release Available: Client Connector 4.7.0.223 for Windows

    *   ### **Zscaler Client Connector 4.7.0.223 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue affecting the Zscaler Client Connector filter driver. In certain scenarios, the issue could cause the system to enter an error state (display a blue screen) during a Zscaler Client Connector upgrade or system startup. If you encounter this issue, contact Zscaler Support for assistance. The issue has been corrected on Zscaler Client Connector version 4.7.0.317.

        *   Supports blocking all traffic and allowing only PAC file exclusions when in strict enforcement mode.
        *   Supports controlling how Zscaler Client Connector interacts with hardware offloading on network adapters to resolve compatibility issues.
        *   Fixes an issue where trusted network detection using the hostname and IP address criteria could cause a delay in switching between networks when using Zscaler Tunnel (Z-Tunnel) 2.0.
        *   Fixes an issue where Zscaler Client Connector didn’t bypass process-based applications with an environment variable (e.g., `%LocalAppData%`) in the application path.
        *   Mitigates a vulnerability where specific session timeout conditions allowed for the bypass of application exit protections. (CVSS v3.1 Base Score: 3.2 - Low)
        *   Fixes an issue where, if the forwarding profile was set to bypass DNS traffic on a trusted network and send the traffic to Zscaler Private Access (ZPA) on an off-trusted network, network detection caused Zscaler Client Connector to send some DNS traffic to ZPA while on a trusted network.
        *   Fixes a DNS routing issue that caused connectivity problems for applications accessed over VPN (for Legacy Apps) on a trusted network when the DNS server was defined as an app segment.
        *   Fixes an issue where Zscaler Client Connector could bypass traffic on ports 53 and 67.
        *   Fixes inconsistent behavior where, when reconnecting to a ZPA application through an app segment with the [Double Encryption](https://help.zscaler.com/zpa/configuring-defined-application-segments#define-clientconnector) option enabled, Zscaler Client Connector closed the app client connection immediately instead of holding the connections as it does when the option is disabled.
        *   Fixes an issue where the legacy SpiderMonkey-based PAC parser didn’t handle single quotations or backslashes correctly, which could result in traffic bypassing the local proxy.
        *   Mitigates a vulnerability where specific path validation conditions allowed for elevated write privileges. (CVSS v3.1 Base Score: 6.1 - Medium)

Release Available: Client Connector 4.6.0.398 for Windows

    *   ### **Zscaler Client Connector 4.6.0.398 Enhancements and Fixes**

        *   Supports controlling how Zscaler Client Connector interacts with hardware offloading on network adapters to resolve compatibility issues. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#adv).
        *   Fixes an issue where Zscaler Client Connector didn’t bypass process-based applications with an environment variable (e.g., `%LocalAppData%`) in the application path.
        *   Fixes an issue where, if the forwarding profile was set to bypass DNS traffic on a trusted network and send the traffic to Zscaler Private Access (ZPA) on an off-trusted network, network detection caused Zscaler Client Connector to send some DNS traffic to ZPA while on a trusted network.
        *   Fixes a DNS routing issue that caused connectivity problems for applications accessed over VPN (for Legacy Apps) on a trusted network when the DNS server was defined as an app segment.
        *   Fixes an issue where the legacy SpiderMonkey-based PAC parser didn’t handle single quotations or backslashes correctly, which could result in traffic bypassing the local proxy.
        *   Mitigates a vulnerability where specific path validation conditions allowed for elevated write privileges. (CVSS v3.1 Base Score: 6.1 - Medium)

**February 26, 2026**

*   Release Available: Client Connector 4.8.0.140 for Windows

    *   ### **Zscaler Client Connector 4.8.0.140 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue affecting the Zscaler Client Connector filter driver. In certain scenarios, the issue could cause the system to enter an error state (display a blue screen) during a Zscaler Client Connector upgrade or system startup. If you encounter this issue, contact Zscaler Support for assistance. The issue has been corrected on Zscaler Client Connector version 4.8.0.190.

        *   Supports blocking all traffic and allowing only PAC file exclusions when in strict enforcement mode. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#pac-proxy).
        *   Supports controlling how Zscaler Client Connector interacts with hardware offloading on network adapters to resolve compatibility issues. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#adv).
        *   Fixes an issue where trusted network detection using the hostname and IP address criteria could cause a delay in switching between networks when using Zscaler Tunnel (Z-Tunnel) 2.0.
        *   Fixes an issue where Zscaler Client Connector didn’t bypass process-based applications with an environment variable (e.g., `%LocalAppData%`) in the application path.
        *   Mitigates a vulnerability where specific session timeout conditions allowed for the bypass of application exit protections. (CVSS v3.1 Base Score: 3.2 - Low)
        *   Fixes an issue where, if the forwarding profile was set to bypass DNS traffic on a trusted network and send the traffic to Zscaler Private Access (ZPA) on an off-trusted network, network detection caused Zscaler Client Connector to send some DNS traffic to ZPA while on a trusted network.
        *   Fixes a DNS routing issue that caused connectivity problems for applications accessed over VPN (for Legacy Apps) on a trusted network when the DNS server was defined as an app segment.
        *   Fixes an issue where Zscaler Client Connector could bypass traffic on ports 53 and 67.
        *   Fixes inconsistent behavior where, when reconnecting to a ZPA application through an app segment with the [Double Encryption](https://help.zscaler.com/zpa/configuring-defined-application-segments#define-clientconnector) option enabled, Zscaler Client Connector closed the app client connection immediately instead of holding the connections as it does when the option is disabled.
        *   Fixes an issue where the legacy SpiderMonkey-based PAC parser didn’t handle single quotations or backslashes correctly, which could result in traffic bypassing the local proxy.
        *   Mitigates a vulnerability where specific path validation conditions allowed for elevated write privileges. (CVSS v3.1 Base Score: 6.1 - Medium)
        *   Fixes an issue where, when using the packet filter-based driver, Zscaler Client Connector added a route for 100.64.0.2 when ZPA connected but did not delete it when ZPA was turned off, which sometimes caused problems with domain profile detection.

**January 30, 2026**

*   Release Available: Client Connector 4.8.0.115 for Windows

    *   ### **Zscaler Client Connector 4.8.0.115 Enhancements and Fixes**

        *   Supports controlling how Zscaler Client Connector interacts with the Receive Segment Coalescing (RSC) feature on network adapters to resolve compatibility issues. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#adv).
        *   Fixes an issue where devices with the Domain Joined posture profile always passed the posture check, even if the domain name didn't match or the device wasn't domain joined.
        *   Fixes an issue where the [Block Domain Profile Detection](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#firewall) feature didn't correctly block domain profile detection when switching to a network type with the feature enabled if the policy also had process-based bypasses enabled.
        *   Fixes an issue where the [Fail Close lockdown settings](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#zcc-fail-close-settings) didn't apply along with [captive portal lockdown](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#captive-portal) if the policy also had process-based bypasses enabled.
        *   Fixes an issue where an `Internal Error` message displayed after users clicked [Verify Now](https://help.zscaler.com/zscaler-client-connector/verifying-access-applications) in the Zscaler Client Connector app for step-up authentication for customers using the Zscaler Private Access (ZPA) service and not the Zscaler Internet Access (ZIA) service.
        *   Fixes a timing issue where the custom app profile PAC file was not processed in time at startup, causing traffic to be forced through the default gateway domain without applying the relevant bypasses and rules of the PAC file.
        *   Fixes an issue where traffic to a domain bypassed in the PAC file could be sent to the Zscaler service when an internal Health Check port was reused by another application.
        *   Fixes an issue where DLL files created by the installer during a Zscaler Client Connector installation or upgrade in the %temp% directory were blocked by Windows Defender Application Control (WDAC) by adding a system environment variable (`BITROCK_TEMP_DLLS_FOLDER`) that points to a specific path where the temporary DLLs are extracted. This path can be added to an allowlist of WDAC policies.
        *   Fixes an issue where Zscaler Client Connector didn't detect the captive portal when sending ZIA traffic through a proxy defined as a ZPA application.
        *   Fixes an issue with slow throughput that occurred when accessing an SMB (Server Message Block) file share through an app segment with the [Double Encryption](https://help.zscaler.com/zpa/configuring-defined-application-segments#define-clientconnector) option enabled.
        *   Fixes an issue where, if automatic reauthentication failed, users received a timeout error when manually reauthenticating.
        *   Fixes an issue that caused an `Internal Error` message on the Data Protection window in the app for customers using Endpoint Data Loss Prevention (DLP).
        *   Updates the anti-tampering installation files to add protection to Endpoint DLP internal files.
        *   Fixes an issue where a trusted network defined by the Hostname and IP condition wasn't detected because the Zscaler Client Connector response included both the `CName` and the `ipaddress` in the response.
        *   Fixes an issue where the Zscaler proxy wasn't enforced in accordance with the forwarding profile when the profile was set to Enforce Proxy.
        *   Fixes an issue that could cause FW/AV errors on Windows 11 due to an accumulation of App Container loopback exemptions when the [Disable Loopback Restriction](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#pac-proxy) option was enabled.

Release Available: Client Connector 4.7.0.202 for Windows

    *   ### **Zscaler Client Connector 4.7.0.202 Enhancements and Fixes**

        *   Supports controlling how Zscaler Client Connector interacts with the Receive Segment Coalescing (RSC) feature on network adapters to resolve compatibility issues. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#adv).
        *   Fixes an issue where the [Block Domain Profile Detection](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#firewall) feature didn't correctly block domain profile detection when switching to a network type with the feature enabled if the policy also had process-based bypasses enabled.
        *   Fixes an issue where the [Fail Close lockdown settings](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#zcc-fail-close-settings) didn't apply along with [captive portal lockdown](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#captive-portal) if the policy also had process-based bypasses enabled.
        *   Fixes an issue where an `Internal Error` message displayed after users clicked [Verify Now](https://help.zscaler.com/zscaler-client-connector/verifying-access-applications) in the Zscaler Client Connector app for step-up authentication for customers using the Zscaler Private Access (ZPA) service and not the Zscaler Internet Access (ZIA) service.
        *   Fixes a timing issue where the custom app profile PAC file was not processed in time at startup, causing traffic to be forced through the default gateway domain without applying the relevant bypasses and rules of the PAC file.
        *   Fixes a proxy authentication issue (recalculation of the nonce value) where, if load balancing was used by the ISP, sites could fail to load when using Zscaler Tunnel (Z-Tunnel) 1.0.
        *   Fixes an issue where traffic to a domain bypassed in the PAC file could be sent to the Zscaler service when an internal Health Check port was reused by another application.
        *   Fixes an issue where Zscaler Client Connector didn't detect the captive portal when sending ZIA traffic through a proxy defined as a ZPA application.
        *   Fixes an issue with slow throughput that occurred when accessing an SMB (Server Message Block) file share through an app segment with the [Double Encryption](https://help.zscaler.com/zpa/configuring-defined-application-segments#define-clientconnector) option enabled.
        *   Fixes an issue where, if automatic reauthentication failed, users received a timeout error when manually reauthenticating.
        *   Updates the process check for the Full Disk Encryption device posture to use a more reliable check, preventing incorrect encryption status results.
        *   Fixes a delay when logging in to Windows when Zscaler Client Connector is in strict enforcement and machine tunnel mode.
        *   Fixes an issue that caused an `Internal Error` message on the Data Protection window in the app for customers using Endpoint Data Loss Prevention (DLP).
        *   Updates the anti-tampering installation files to add protection to Endpoint DLP internal files.
        *   Fixes an issue where a trusted network defined by the Hostname and IP condition wasn't detected because the Zscaler Client Connector response included both the `CName` and the `ipaddress` in the response.
        *   Fixes an issue where the Zscaler proxy wasn't enforced in accordance with the forwarding profile when the profile was set to Enforce Proxy.
        *   Fixes an issue that could cause FW/AV errors on Windows 11 due to an accumulation of App Container loopback exemptions when the [Disable Loopback Restriction](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#pac-proxy) option was enabled.

Release Available: Client Connector 4.6.0.371 for Windows

    *   ### **Zscaler Client Connector 4.6.0.371 Enhancements and Fixes**

        *   Supports controlling how Zscaler Client Connector interacts with the Receive Segment Coalescing (RSC) feature on network adapters to resolve compatibility issues. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#adv).
        *   Fixes an issue where the [Block Domain Profile Detection](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#firewall) feature didn't correctly block domain profile detection when switching to a network type with the feature enabled if the policy also had process-based bypasses enabled.
        *   Fixes an issue where the [Fail Close lockdown settings](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#zcc-fail-close-settings) didn't apply along with [captive portal lockdown](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#captive-portal) if the policy also had process-based bypasses enabled.
        *   Fixes a timing issue where the custom app profile PAC file was not processed in time at startup, causing traffic to be forced through the default gateway domain without applying the relevant bypasses and rules of the PAC file.
        *   Fixes a proxy authentication issue (recalculation of the nonce value) where, if load balancing was used by the ISP, sites could fail to load when using Zscaler Tunnel (Z-Tunnel) 1.0.
        *   Fixes an issue where traffic to a domain bypassed in the PAC file could be sent to the Zscaler service when an internal Health Check port was reused by another application.
        *   Fixes an issue where Zscaler Client Connector didn't detect the captive portal when sending Zscaler Internet Access (ZIA) traffic through a proxy defined as a Zscaler Private Access (ZPA) application.
        *   Fixes an issue where, if automatic reauthentication failed, users received a timeout error when manually reauthenticating.
        *   Updates the process check for the Full Disk Encryption device posture to use a more reliable check, preventing incorrect encryption status results.
        *   Fixes a delay when logging in to Windows when Zscaler Client Connector is in strict enforcement and machine tunnel mode.
        *   Fixes an issue that caused an `Internal Error` message on the Data Protection window in the app for customers using Endpoint Data Loss Prevention (DLP).
        *   Updates the anti-tampering installation files to add protection to Endpoint DLP internal files.
        *   Fixes an issue where a trusted network defined by the Hostname and IP condition wasn't detected because the Zscaler Client Connector response included both the `CName` and the `ipaddress` in the response.
        *   Fixes an issue where the Zscaler proxy wasn't enforced in accordance with the forwarding profile when the profile was set to Enforce Proxy.
        *   Fixes an issue that could cause FW/AV errors on Windows 11 due to an accumulation of App Container loopback exemptions when the [Disable Loopback Restriction](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#pac-proxy) option was enabled.

---

<!-- source: https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows -->

Title: Deploying Endpoint Deception with Zscaler Client Connector for Windows | Zscaler

URL Source: https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows

Markdown Content:
![Image 1: icon-deception.svg](blob:http://localhost/69bbebb372369ed5f9f85edf24269e88)

### Deploying Endpoint Deception with Zscaler Client Connector for Windows

If your organization is provisioned for Zscaler Internet Access (ZIA), you can deploy endpoint deception capabilities on Microsoft Windows machines using Zscaler Client Connector.

This article provides information on prerequisites and how to deploy endpoint deception with Zscaler Client Connector for the Microsoft Windows operating system.

## Prerequisites

Before deploying endpoint deception with Zscaler Client Connector, ensure that you have:

*   Enabled endpoint deception capabilities on your Zscaler Client Connector by contacting your Zscaler Account team.
*   Endpoints with Windows 10 (64-bit only) or later with a 64-bit version of Zscaler Client Connector are installed. You must[upgrade Zscaler Client Connector](https://help.zscaler.com/client-connector/downloading-zscaler-client-connector) to one of the supported versions:

    *   4.6.0.123 or later
    *   4.7.0.31 or later
    *   4.8.0.88 or later
    *   4.9.0.316 or later

For Zscaler-recommended best practices for deploying Zscaler Client Connector, see [Best Practices for Zscaler Client Connector Deployment](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-deployment).

*   Enabled the Tunnel Internal Client Connector Traffic option in the Zscaler Client Connector Portal.

By default, the traffic sent to the Zscaler Deception Admin Portal is considered internal traffic. Enabling this option in the Zscaler Client Connector Portal ensures that all traffic sent to the Deception Admin Portal is tunneled through Zscaler. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#advanced).

*   Outbound HTTPS access from either or both of your proxy and firewall in your local environment to:

    *   Deception Admin Portal on port 443, i.e., <your_subdomain>.illusionblack.com on port 443
    *   dwv281inkfqg3.cloudfront.net on port 443

To learn more, see the [Zscaler Client Connector Config page](https://config.zscaler.com/zscaler.net/zscaler-app).

If you are running endpoint deception using a [landmine agent](https://help.zscaler.com/deception/about-landmine-agent-agentless) and want to migrate to Zscaler Client Connector, you can automatically migrate during the Zscaler Client Connector upgrade process. During the upgrade, important information such as lures applied and decoys deployed are automatically copied to the new installation location, and the standalone landmine agent is uninstalled.

## Deploying Endpoint Deception with Zscaler Client Connector

Follow these steps to deploy endpoint deception with Zscaler Client Connector:

*   [Step 1: Enable Deception in the Zscaler Client Connector Portal](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#endpoint-deception-enable-deception-client-connector-portal)

    1.   [](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows)Log in to the [Zscaler Client Connector Portal](https://help.zscaler.com/zscaler-client-connector/accessing-and-navigating-zscaler-client-connector-portal).
    2.   Go to **Zscaler Service Entitlement**>**Zscaler Deception****(Deception)**.
    3.   Enable **Zscaler Deception Enabled by Default** to enable endpoint deception for all Zscaler Client Connector users upgraded to Deception version 4.6 or later for Windows.

[See image.](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#ed-enable-zd-by-default)

        1.   To enable Deception for a specific group of users, disable **Zscaler Deception Enabled by Default**, and then select a group from the **User Groups** drop-down menu. To learn more, see [Enabling Deception for a Group of Users](https://help.zscaler.com/client-connector/selective-entitlement-enabling-deception-group-users).
        2.   To enable Deception for a specific device group, disable **Zscaler Deception Enabled by Default**, and then select a group from the **Device Groups** drop-down menu. To learn more, see [Enabling Deception for Device Groups](https://help.zscaler.com/zscaler-client-connector/enabling-zscaler-deception-device-groups).

[See image.](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#ed-enable-zd-for-group)

    4.   Click **Save**.

[Close](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#endpoint-deception-enable-deception-client-connector-portal)

*   [Step 2: Customize the Zscaler Deception Landmine Installer](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#endpoint-deception-customize-landmine-agent-service)

    1.   [](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows)Log in to the Deception Admin Portal.
    2.   Go to **Settings**>**Endpoint Settings**>**Agent Configuration**.
    3.   Customize the **Name**, **Display Name**, and **Description** of the landmine installer or service to prevent the agent from being fingerprinted. To learn more, see [Customizing Landmine Installer](https://help.zscaler.com/deception/customizing-landmine-installer).

[See image.](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#ed-customize-landmine-agent-service)

[Close](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#endpoint-deception-customize-landmine-agent-service)

*   [Step 3: Integrate Zscaler Client Connector with Deception](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#endpoint-deception-integrate-with-zcc)

    1.   [](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows)Log in to the Zscaler Client Connector Portal.
    2.   Click **Administration** on the top menu.
    3.   In the left-side navigation, click **Zscaler Deception**.
    4.   Verify if the Zscaler Deception installer settings (**Name**, **Display Name**, and **Description**) you see on this page match the settings you entered in [the previous step](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#endpoint-deception-customize-landmine-agent-service). If they don't match, click **Sync** to get the latest values. To learn more, see [Zscaler Client Connector Integration with Deception](https://help.zscaler.com/client-connector/about-zscaler-client-connector-integration-deception).

[See image.](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#ed-sync-zscaler-deception)

[Close](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#endpoint-deception-integrate-with-zcc)

*   [Step 4: Configure a Zscaler Client Connector Profile Policy Rule for Windows](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#endpoint-deception-add-windows-policy)

    1.   [](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows)In the Zscaler Client Connector Portal, click **App Profiles** on the top menu.
    2.   On the **App Profiles** page, select **Windows**.
    3.   If you already have existing policies configured, click the **Edit**icon for the policy that you want to modify. If you do not have an existing policy, click **Add Windows Policy**.

[See image.](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#ed-add-windows-policy-option)

    4.   In the **Edit Windows Policy** (or **Add Windows Policy**) window, enter the **Logout Password**, **Exit Password**, and **Password to access Deception Settings**.

[See image.](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#ed-config-zd-password-rule)

If you are adding a new policy, make sure you have configured all necessary fields as required. To learn more, see [Adding a Zscaler Client Connector Profile Policy Rule for Windows](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#windows). If you are editing an existing policy, make sure **Enable**is selected.

    5.   Click **Save**.

[Close](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#endpoint-deception-add-windows-policy)

*   [Step 5: Verify the Zscaler Deception Service Status in Zscaler Client Connector](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#endpoint-deception-verify-zd-service-status-client-connector)

    1.   [](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows)Log in to Zscaler Client Connector on your machine.

[See image.](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#ed-zcc-sign-in-page)

[](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows)![Image 2: Signing in to the Zscaler Client Connector](https://help.zscaler.com/downloads/deception/deceive/landmine-decoys/endpoint-deception-zscaler-client-connector/deploying-endpoint-deception-zscaler-client-connector/zscaler-deception-client-connector-log-in.png)

[Close](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#ed-zcc-sign-in-page) 
    2.   In the left-side navigation, click **More**, and then click **Update Policy.**

[See image.](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#ed-zcc-advanced-settings)

    3.   After the policy update is complete, click **Update App**.

[See image.](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#deception-zcc-update-app)

    4.   After the app update is complete, click **Advanced Settings**.

[See image.](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#deception-zcc-advanced-settings)

    5.   Enter the password you created in [the previous step](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#endpoint-deception-add-windows-policy).

[See image.](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#ed-zd-password)

    6.   Click **OK**.

[See image.](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#ed-zd-service-running)

The configuration is successful and the **Service Status** is **Running**.

[Close](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows#endpoint-deception-verify-zd-service-status-client-connector)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/zdx-module-release-summary-2026 -->

Title: ZDX Module Release Summary (2026) | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/zdx-module-release-summary-2026

Markdown Content:
# ZDX Module Release Summary (2026) | Zscaler

[![Image 1: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Client Connector

- [x] All

- [x] Getting Started with Zscaler

- [x] Internet & SaaS (ZIA)

- [x] Private Access (ZPA)

- [x] Digital Experience Monitoring (ZDX)

- [x] Workflow Automation

- [x] Data Security Posture Management (DSPM)

- [x] Client Connector

- [x] Cloud & Branch Connector

- [x] Zscaler Cellular

- [x] Zero Trust Branch

- [x] Unified Vulnerability Management (UVM)

- [x] Asset Exposure Management

- [x] External Attack Surface Management

- [x] SOC Workbench

- [x] Deception

- [x] Identity Protection

- [x] Breach Predictor

- [x] Zero Trust Browser

- [x] Risk360

- [x] Business Insights

- [x] Authentication Service

- [x] Zscaler SDK for Mobile Apps

- [x] AI Asset Management

- [x] Secure Access to AI Apps

- [x] Secure AI Apps & Infrastructure

- [x] Legacy Zscaler APIs

- [x] Logs & Fair Use

- [x] Zscaler Support Troubleshooting Runbooks

- [x] Zscaler Deployments & Operations

- [x] Zscaler Technology Partners

- [x] Multi-Tenant Portal

- [x] Product Usage Terms & Policies

[RSS](https://help.zscaler.com/rss)

EN

English 日本語

*   [Zscaler Client Connector Help](https://help.zscaler.com/zscaler-client-connector)
*   [Release Notes](https://help.zscaler.com/zscaler-client-connector/release-notes)
*   [Zscaler Client Connector - ZDX Module Release Notes (per OS)](https://help.zscaler.com/zscaler-client-connector/release-notes/zscaler-client-connector-zdx-module-release-notes-os)
*    ZDX Module Release Summary (2026) 

![Image 2: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 3: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### ZDX Module Release Summary (2026)

This article provides a summary of all new features and enhancements released per operating system (OS) for the Zscaler Digital Experience (ZDX) Module in the Zscaler Client Connector app.

**Select an OS:** 

**Select a ZDX Module version:** 

**Select a deployment date:** 

* * *

The ZDX Module app versions for Windows listed below were deployed on the following dates.

**June 01, 2026**

*    Release Available: ZDX Module 4.7.0.76 for Windows  
    *   ### **ZDX Module 4.7.0.76 Enhancements and Fixes**

###### **Compatible with the following Zscaler Client Connector versions for Windows: 4.9.0.331, 4.9.0.330, 4.9, 4.8.0.88, 4.8.0.63, 4.8.0.190, 4.8.0.172, 4.8.0.156, 4.8.0.151, 4.8.0.140, 4.8.0.115, 4.8, 4.7.0.88, 4.7.0.61, 4.7.0.47, 4.7.0.248, 4.7.0.239, 4.7.0.232, 4.7.0.223, 4.7.0.202, 4.7.0.168, 4.7.0.141, 4.7.0.113, 4.7, 4.6.0.418, 4.6.0.410, 4.6.0.398, 4.6.0.371, 4.6.0.351, 4.6.0.334, 4.6.0.310, 4.6.0.284, 4.6.0.282, 4.6.0.240, 4.6.0.216, 4.6.0.200, 4.6.0.168, 4.6.0.146, 4.6, 4.5.0.508, 4.5.0.498, 4.5.0.495, 4.5.0.484, 4.5.0.478, 4.5.0.471, 4.5.0.459, 4.5.0.434, 4.5.0.381, 4.5.0.366, 4.5.0.352, 4.5.0.344, 4.5.0.337, 4.5.0.325, 4.5.0.296, 4.5.0.286, 4.5.0.278, 4.5, 4.4.0.472, 4.4.0.468, 4.4.0.465, 4.4.0.464, 4.4.0.460, 4.4.0.456, 4.4.0.432, 4.4.0.428, 4.4.0.406, 4.4.0.395, 4.4.0.389, 4.4.0.383, 4.4.0.379, 4.4.0.368, 4.4.0.346, 4.4.0.335, 4.4.0.324, 4.4.0.309, 4.4.0.300, 4.4.0.294, 4.4.0.285, 4.4.0.276, 4.4, 4.3.500, 4.3.0.277, 4.3.0.272, 4.3.0.269, 4.3.0.264, 4.3.0.261, 4.3.0.255, 4.3.0.243, 4.3.0.238, 4.3.0.226, 4.3.0.218, 4.3.0.202, 4.3.0.190, 4.3.0.188, 4.3.0.181, 4.3.0.161, 4.3.0.151, 4.3.0.131, 4.3.0.121, 4.3, 4.2.1.237, 4.2.1.234, 4.2.1.229, 4.2.1.223, 4.2.1.212, 4.2.1.199, 4.2.1.194, 4.2.1.193, 4.2.1.181, 4.2.1, 4.2.0.217, 4.2.0.209, 4.2.0.198, 4.2.0.190, 4.2.0.178, 4.2.0.173, 4.2.**

Enhances security for Zscaler Client Connector and ZDX Module by:

        *   Resolving internally discovered vulnerabilities.
        *   Improving memory management, inter-process communication (IPC) validation, cryptographic checks, and HTTP redirect handling.

**February 17, 2026**

*    Release Available: ZDX Module 4.7 for Windows  
    *   ### **ZDX Module 4.7 Enhancements and Fixes**

###### **Compatible with the following Zscaler Client Connector versions for Windows: 4.8.0.88, 4.8.0.63, 4.8.0.156, 4.8.0.151, 4.8.0.140, 4.8.0.115, 4.8, 4.7.0.88, 4.7.0.61, 4.7.0.47, 4.7.0.239, 4.7.0.232, 4.7.0.223, 4.7.0.202, 4.7.0.168, 4.7.0.141, 4.7.0.113, 4.7, 4.6.0.398, 4.6.0.371, 4.6.0.351, 4.6.0.334, 4.6.0.310, 4.6.0.284, 4.6.0.282, 4.6.0.240, 4.6.0.216, 4.6.0.200, 4.6.0.168, 4.6.0.146, 4.6, 4.5.0.508, 4.5.0.498, 4.5.0.495, 4.5.0.484, 4.5.0.478, 4.5.0.471, 4.5.0.459, 4.5.0.434, 4.5.0.381, 4.5.0.366, 4.5.0.352, 4.5.0.344, 4.5.0.337, 4.5.0.325, 4.5.0.296, 4.5.0.286, 4.5.0.278, 4.5, 4.4.0.472, 4.4.0.468, 4.4.0.465, 4.4.0.464, 4.4.0.460, 4.4.0.456, 4.4.0.432, 4.4.0.428, 4.4.0.406, 4.4.0.395, 4.4.0.389, 4.4.0.383, 4.4.0.379, 4.4.0.368, 4.4.0.346, 4.4.0.335, 4.4.0.324, 4.4.0.309, 4.4.0.300, 4.4.0.294, 4.4.0.285, 4.4.0.276, 4.4, 4.3.500, 4.3.0.277, 4.3.0.272, 4.3.0.269, 4.3.0.264, 4.3.0.261, 4.3.0.255, 4.3.0.243, 4.3.0.238, 4.3.0.226, 4.3.0.218, 4.3.0.202, 4.3.0.190, 4.3.0.188, 4.3.0.181, 4.3.0.161, 4.3.0.151, 4.3.0.131, 4.3.0.121, 4.3, 4.2.1.237, 4.2.1.234, 4.2.1.229, 4.2.1.223, 4.2.1.212, 4.2.1.199, 4.2.1.194, 4.2.1.193, 4.2.1.181, 4.2.1, 4.2.0.217, 4.2.0.209, 4.2.0.198, 4.2.0.190, 4.2.0.178, 4.2.0.173, 4.2.**

        *   Addresses incomplete Network Interface data in Device Profile and Device Stats on Windows.
        *   Fixes an issue where there are missing Zscaler Internet Access (ZIA) Public Service Edge metrics on the Cloud Path.
        *   Fixes Cloud Path VIP selection to use the tunnel-detected VIP in IPSec/GRE scenarios.
        *   Fixes policy downloader to follow system proxy and retry via external proxy on PAC/TPG timeouts.

**January 06, 2026**

*    Release Available: ZDX Module 4.6.2.15 for Windows  
    *   ### **ZDX Module 4.6.2.15 Enhancements and Fixes**

###### **Compatible with the following Zscaler Client Connector versions for Windows: 4.8.0.63, 4.8, 4.7.0.88, 4.7.0.61, 4.7.0.47, 4.7.0.168, 4.7.0.141, 4.7.0.113, 4.7, 4.6.0.334, 4.6.0.310, 4.6.0.284, 4.6.0.282, 4.6.0.240, 4.6.0.216, 4.6.0.200, 4.6.0.168, 4.6.0.146, 4.6, 4.5.0.508, 4.5.0.498, 4.5.0.495, 4.5.0.484, 4.5.0.478, 4.5.0.471, 4.5.0.459, 4.5.0.434, 4.5.0.381, 4.5.0.366, 4.5.0.352, 4.5.0.344, 4.5.0.337, 4.5.0.325, 4.5.0.296, 4.5.0.286, 4.5.0.278, 4.5, 4.4.0.472, 4.4.0.468, 4.4.0.465, 4.4.0.464, 4.4.0.460, 4.4.0.456, 4.4.0.432, 4.4.0.428, 4.4.0.406, 4.4.0.395, 4.4.0.389, 4.4.0.383, 4.4.0.379, 4.4.0.368, 4.4.0.346, 4.4.0.335, 4.4.0.324, 4.4.0.309, 4.4.0.300, 4.4.0.294, 4.4.0.285, 4.4.0.276, 4.4, 4.3.500, 4.3.0.277, 4.3.0.272, 4.3.0.269, 4.3.0.264, 4.3.0.261, 4.3.0.255, 4.3.0.243, 4.3.0.238, 4.3.0.226, 4.3.0.218, 4.3.0.202, 4.3.0.190, 4.3.0.188, 4.3.0.181, 4.3.0.161, 4.3.0.151, 4.3.0.131, 4.3.0.121, 4.3, 4.2.1.237, 4.2.1.234, 4.2.1.229, 4.2.1.223, 4.2.1.212, 4.2.1.199, 4.2.1.194, 4.2.1.193, 4.2.1.181, 4.2.1, 4.2.0.217, 4.2.0.209, 4.2.0.198, 4.2.0.190, 4.2.0.178, 4.2.0.173, 4.2.**

        *   Corrects the Learn More button display in the Notifications tab on corporate devices.
        *   Enhances proxy handling by retrying with the system-configured external proxy when direct TPG connections time out.

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[ZDX Module Release Summary (2026)](https://help.zscaler.com/zscaler-client-connector/zdx-module-release-summary-2026)[ZDX Module Release Summary (2025)](https://help.zscaler.com/zscaler-client-connector/zdx-module-release-summary-2025)[ZDX Module Release Summary (2024)](https://help.zscaler.com/zscaler-client-connector/zdx-module-release-summary-2024)[ZDX Module Release Summary (2023)](https://help.zscaler.com/zscaler-client-connector/zdx-module-release-summary-2023)[ZDX Module Release Summary (2022)](https://help.zscaler.com/zscaler-client-connector/zdx-module-release-summary-2022)[ZDX Module Release Summary (2021)](https://help.zscaler.com/zscaler-client-connector/zdx-module-release-summary-2021)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/what-is-zscaler-client-connector -->

Title: What Is Zscaler Client Connector? | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/what-is-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### What Is Zscaler Client Connector?

Using Zscaler Client Connector, users can get all of the benefits of the Zscaler service for internet traffic, as well as granular, policy-based access to internal resources from a single point.

*   With Internet & SaaS, you can protect your users' web traffic even when they are outside your corporate network. You can also protect your users' mobile traffic, whether they're connected to Wi-Fi or cellular networks. The app forwards user traffic to the Zscaler service and ensures that your organization's security and access policies are enforced wherever they might be accessing the internet.
*   With Private Access, you can enable your users to securely access enterprise applications from outside the corporate network. Private Access establishes a secure transport for accessing your enterprise apps and services.
*   With Zscaler Digital Experience (ZDX), you can monitor your organization’s user devices to detect user experience and productivity issues. ZDX relies on Zscaler Client Connector to perform synthetic probing to a desired Software as a Service (SaaS) application or internet-based service (e.g., OneDrive, Gmail, etc.).
*   With Zscaler Endpoint Data Loss Prevention (DLP), you can protect your organization from data loss on endpoints. Endpoint DLP policy complements Zscaler DLP policy by extending the monitoring of sensitive data to the activities that end users take on endpoints (i.e., printing, saving to removable storage, saving to network shares, or uploading to personal cloud storage accounts).

You have the ability to control various settings for the app in the Zscaler Admin Console. The Zscaler Admin Console is dedicated to app management, accessible directly from the Internet & SaaS and Private Access. With administration options, you can configure general settings for the app, such as auto-update and in-app support.

You can also configure app profiles and specify, for example, how the app detects when a user is connected to a trusted network, and if a trusted network is detected, whether the app must disable its service. For greater flexibility, you can configure app profiles so that they apply to all users or to specific groups of users in your organization.

In the Zscaler Admin Console, you can define policies that control how a device forwards traffic to the Zscaler service and which apps, functionality, and content can be accessed from a device. For mobile devices, the Zscaler service also provides per-user and per-department logging and reporting.

After you configure settings and policies in the Zscaler Admin Console, you can silently deploy the app on users' devices for Windows and macOS. You can also deploy the app on users’ devices for Android, Android on ChromeOS, and iOS via MDM. Users need only complete a simple login process to enroll their devices with the Zscaler service.

![Image 2: Screenshot of the Zscaler Client Connector](https://help.zscaler.com/downloads/zia/documentation-knowledgebase/forwarding-your-traffic/zscaler-app/about-zscaler-app/what-zscaler-app/28af50d5-5118-45e4-bab0-b08a43c5db82_display.png)

When users enroll, the app downloads the administration settings you've configured, as well as the appropriate app profile, and begins forwarding traffic and protecting users immediately. The app regularly checks for updates to administration options and app profiles, and downloads any changes you make, ensuring the app reflects your latest settings.

For Android devices, Zscaler Client Connector also establishes a proprietary, secure HTTP-tunnel-based VPN to forward the mobile traffic from the user's applications to Zscaler Client Connector. Zscaler Client Connector then sends this traffic to the cloud. Zscaler uses Samsung Approved for Enterprise (SAFE) KNOX APIs for enforceability.

Users might be able to turn off the VPN on non-Samsung Android devices.

When you run Zscaler Client Connector on Android and iOS devices, it also installs the policy that you configured on the Zscaler Admin Console as a profile on your mobile device. Additionally, it enrolls the device in the Zscaler service. After the device is enrolled, the device establishes a local VPN that connects locally to Zscaler Client Connector to direct traffic. As the browser and other applications generate traffic, this is automatically forwarded to the Zscaler cloud.

## Key Features

The following are some key Zscaler Client Connector features and benefits:

*   Authentication: The app supports all authentication mechanisms supported by the Zscaler service, except Kerberos. It also supports SAML with two-factor authentication. Your organization's users can seamlessly log in and enroll with their existing user credentials. If you are using the app for Private Access, your organization must use SAML authentication.
*   Enforcement: You can configure the [app profile](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app) so that after users enroll, they cannot log out of, disable, or uninstall Zscaler Client Connector without an admin-provided password.
*   Trusted Network Detection: The app can detect when users are connecting from a trusted network (for example, from your corporate network) and disable its internet security service so that user traffic is forwarded to the Zscaler service via the network's configured traffic forwarding mechanism. Learn more about [configuring trusted networks](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app).
*   Captive Portal Detection: The app can detect when users try to connect to networks where a captive portal requires users to pay or accept a use policy before accessing the web (for example, Wi-Fi networks at airports or hotels). When it detects a captive portal, it can disable its service for a period of time you specify, allowing users to complete the steps necessary to access the network, before automatically re-enabling itself. Learn more about [captive portal detection](https://help.zscaler.com/zscaler-client-connector/configuring-fail-open-settings-zscaler-app).
*   SSL Inspection: If you are using Zscaler Client Connector to secure your web traffic, it can automatically install the Zscaler SSL certificate during enrollment so that the Zscaler service can perform SSL inspection on web traffic forwarded by the app. However, you must enable SSL inspection for mobile traffic in the ZIA Admin Portal. This feature applies to the Internet Security service only. Private Access does not support SSL inspection.
*   Auto-Update to Latest Release: You can enable auto-updates so that apps on users' devices are automatically updated whenever Zscaler releases a new version. If you prefer to test new app versions before allowing updates, you also have the option of pushing app updates from the Zscaler Admin Console when you're ready. Learn more about [update settings](https://help.zscaler.com/zscaler-client-connector/configuring-update-settings-zscaler-app).
*   Easy Administration with the Zscaler Admin Console: In the Zscaler Admin Console, you can easily manage [app profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-profiles) and administration settings. The app checks regularly for updates and downloads any changes you make. If users exit the app, log out and log back into the app, or restart their devices, the app also checks for updates and download changes.
*   Dashboards and Device Fingerprint Information: In the Zscaler Admin Console, you can view a dashboard that provides information about devices that have been enrolled with the Zscaler service, including the number of Zscaler Client Connector licenses being used, the device models, platforms, and operating systems on which the app is running, as well as information about which devices are running outdated app versions. You can also view device fingerprint information for all devices that have been enrolled.
*   In-App Access to Support: You can provide users with different options for requesting support in Zscaler Client Connector. You can allow users to send support request emails directly from the app to your organization's support team, or you can allow users to submit tickets directly from the app to Zscaler Support. Learn more about [support access](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-support-and-logging-zscaler-app) in Zscaler Client Connector.
*   Localization: Zscaler Client Connector supports changing the language of the app user interface based on the system language. To learn more, see [Localization Support](https://help.zscaler.com/zscaler-client-connector/localization-support).[](https://help.zscaler.com/zscaler-client-connector/what-is-zscaler-client-connector)

## How Does Zscaler Client Connector Work?

This section describes how Zscaler Client Connector works when you use it to secure your web and mobile traffic. To learn about how the app works when you use it with ZPA to provide secure access to your internal resources, see [What is Private Access?](https://help.zscaler.com/zpa/getting-started/what-zscaler-private-access) To learn about how the app works when you use it with ZDX to monitor your users' experience and productivity issues, see [What is Zscaler Digital Experience?](https://help.zscaler.com/zdx/what-is-zscaler-digital-experience) To learn about how the app works when you use it for Endpoint Data Loss Protection (DLP), see [Zscaler Endpoint Data Loss Prevention (DLP) Integration with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/zscaler-endpoint-data-loss-protection-dlp-integration-zscaler-client-connector) and [About Endpoint Data Loss Prevention](https://help.zscaler.com/zia/about-endpoint-dlp).

When you install Zscaler Client Connector for PC, a Zscaler Network Adapter is also installed on your user's computer. When the user connects to the web, the network adapter captures web traffic from that device. The app then uses geolocation technology to locate the Public Service Edge for Internet & SaaS closest to the user, establishes a lightweight tunnel (called the Z-Tunnel) to the Public Service Edge, and forwards the user's web traffic through the tunnel so that the Public Service Edge can apply appropriate security and access policies.

When you install Zscaler Client Connector on a mobile device, it authenticates the user using your corporate authentication mechanism and completes the following tasks:

*   Installs the appropriate app profile
*   Installs a VPN profile locally (if not already installed via MDM)
*   Registers the mobile device to the Zscaler service

The device then establishes a local VPN tunnel that captures application traffic and directs it to Zscaler Client Connector on the device.

While this is the default behavior of the app, you can modify the app's traffic forwarding settings as necessary. For example:

*   Instead of the app automatically determining the Public Service Edge for Internet & SaaS to which it tunnels traffic, you can specify the particular Public Service Edges to which the app must tunnel traffic (for example, you must do this if your organization uses Public Service Edges or Virtual Service Edges).
*   If you are running Zscaler Client Connector version 1.4 or later, you can choose multiple destinations for Zscaler Client Connector to send traffic (for example, you can send traffic for a certain domain to a Service Edge or Virtual Service Edge, and send the rest to the geographically closest Public Service Edge for Internet & SaaS.)
*   You can choose to allow some traffic (for example, traffic to certain domains like identity federation URLs) to bypass the app tunnel and go directly to the web.

To modify the app's traffic forwarding behavior in these ways, you can add a custom PAC file in your app profile so that the app forwards traffic according to its instructions. The app checks the PAC file regularly to make sure it retrieves the latest one, and whenever it retrieves a new PAC file, it saves that PAC file to your users' computers. This ensures that the PAC file is accessible even after users restart the app or their computers, allowing them to access internal resources and send traffic to private IP ranges even if your organization faces internet connectivity issues.

Whether you use a custom PAC file or have the app forward traffic to the service per its default behavior, the app regularly checks to make sure traffic is forwarded correctly and efficiently. For example, it checks at regular intervals whether the Public Service Edge for Internet & SaaS to which the app is currently tunneling traffic is still the best Public Service Edge for a given user's traffic. It also performs these checks whenever a user changes networks, or restarts the app or their devices.

By default, the app overrides any proxy settings configured on users' browsers so that users cannot manipulate the app's traffic routing. If you prefer to allow users' browser proxy settings to apply, you can do so with your app profile policy.

Zscaler can check IP addresses to avoid IP address conflict. For example, if you are using 100.64.0.0/16 and Zscaler sees a conflicting IP address, Zscaler changes it to 100.65.0.0/16. This change in the IP addresses can range from 100.64.0.0/16 to 100.83.0.0/16.

To learn more about the end user functionality within the app, see [End User Guides](https://help.zscaler.com/zscaler-client-connector/end-user-guides). To start the configuration process for Zscaler Client Connector, see [Signing in to the Zscaler Admin Console](https://help.zscaler.com/unified/signing-zscaler-admin-console) and the [Step-by-Step Configuration Guide for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-app-downloads -->

Title: Understanding Zscaler Client Connector App Downloads | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-app-downloads

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Understanding Zscaler Client Connector App Downloads

IT Admins can download installation files to use when deploying Zscaler Client Connector to end users.

There are no download links for Zscaler Client Connector in this article. This article applies only to admins for the Zscaler Admin Console. If you are an end user, contact your organization’s IT admin, IT support team, or equivalent for information about installing Zscaler Client Connector.

If you are an IT Admin, you can download Zscaler Client Connector for Windows, macOS, Linux, or Android from the Zscaler Client Connector App Store in the Zscaler Admin Console. You can download either the latest version or one of the older versions still available for download.

You cannot manually download and install the iOS version of Zscaler Client Connector from the Zscaler Admin Console. You must download and deploy the app with the Mobile Device Management (MDM) used by your organization. To learn more, see [Customizing Zscaler Client Connector with Install Options for iOS](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-ios).

You can also configure app updates to automatically update Zscaler Client Connector for end users with Windows, Linux, or macOS devices. To learn more, see [Configuring an App Update in the Zscaler Client Connector App Store](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store).

To download from the Zscaler Client Connector App Store:

1.   Go to **Infrastructure**>**Common Resources**>**Deployment**>**Platform Releases**.
2.   On the **New Releases** tab, you can view and enable the latest Zscaler Client Connector versions for Windows, macOS, and Linux. In the**EXE URL** and**MSI URL** columns for Windows, the **Download APP** or **Download PKG** columns for macOS, or the **Download Link** column for Linux, click the **Download** icon for the version you want to download.

After you enable a particular build, it is listed under the **Registered Devices** tab.

[See image.](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-app-downloads#new-releases-tab)

3.   On the **Registered Devices** tab, you can view the released versions of Zscaler Client Connector versions for Windows, macOS, Linux, and Android (SDK file only). Click the **Download** icon for the version you want to download in the **EXE URL** and **MSI URL** columns for Windows, the **Download APP** or **Download PKG** columns for macOS, or the **Download Link** column for Linux or Android.

The most recent release is listed at the top of each table. Versions that are not grayed out are still available to download. To learn more, see [About the Zscaler Client Connector Store](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-store).

[See image.](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-app-downloads#registered-devices-tab)

After downloading the file, you can use it to deploy Zscaler Client Connector to end users. To learn more, see the article that corresponds to the OS and any MDM used by your organization in the [Downloading and Deployment](https://help.zscaler.com/zscaler-client-connector/downloading-deployment) section.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors -->

Title: Zscaler Client Connector Errors | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors

Markdown Content:
**Error Code****Error Message****Error Description****Resolution**
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1 Zscaler Client Connector Internal Error, Please Contact Administrator.This is a generic error.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)2 Zscaler Internet Security Authentication Error.This authentication error occurs when the user’s cookie is expired or is no longer valid.Have the user reauthenticate to Zscaler Client Connector.

If the issue persists, export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)3 Zscaler Internet Security Enrollment Version Error.This error occurs when the device runs a version that is not supported by the cloud.Upgrade to the latest version of Zscaler Client Connector.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)4 Zscaler Internet Security Enrollment System Bad Timestamp Error, Please check the system time and ensure that it's accurate.This error occurs when there is a time mismatch between the device and the server.Check the system time and ensure that it is accurate.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)5 Zscaler Client Connector Internal Error, Please Contact Administrator.This error occurs when the device does not send its version to the cloud.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)6 Zscaler Client Connector Internal Error, Please Contact Administrator.This error occurs when the device does not send a timestamp to the server.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)7 Zscaler Client Connector Internal Error, Please Contact Administrator.This error is triggered by the cloud servers.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)8 Zscaler Client Connector Internal Error, Please Contact Administrator.This error occurs when the device does not send a cookie to the server.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)9 Zscaler Client Connector Internal Error, Please Contact Administrator.This error is triggered by the cloud servers.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)10 Zscaler Client Connector Internal Error, Please Contact Administrator.This error is triggered by the cloud servers.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)11 Zscaler Client Connector Internal Error, Please Contact Administrator.This error is triggered by the cloud servers.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)12 Zscaler Client Connector Internal Error, Please Contact Administrator.This error occurs when the Internet & SaaS service is disabled for your organization.

This error also occurs if your organization has not subscribed to Zscaler Client Connector license.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)13 Zscaler Client Connector Internal Error, Please Contact Administrator.This error occurs when a device with an invalid device type connects to the cloud.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)14 Zscaler Client Connector Internal Error, Please Contact Administrator.This error is triggered by the cloud servers.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)15 Zscaler Client Connector Internal Error, Please Contact Administrator.This error is triggered by the cloud servers.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)16 Zscaler Client Connector Internal Error, Please Contact Administrator.This error occurs when the server is provided invalid device information.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)17 Zscaler Client Connector Internal Error, Please Contact Administrator.This error occurs when the server is provided invalid device information.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)18 Zscaler Client Connector Internal Error, Please Contact Administrator.This error occurs when the server is provided invalid device information.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)19 Zscaler Client Connector Internal Error, Please Contact Administrator.This is a generic error.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)20 Zscaler Client Connector License not Subscribed.This error occurs when a device tries to connect to the cloud using an organization that does not exist.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)21 Zscaler Internet Security Enrollment Error - User has exceeded number of devices limit.This error occurs when the user tries to enroll more than 16 devices under one username.From the Zscaler Admin Console, remove devices for that user.

To learn more, see [Removing a Device if I Reach the Number of Devices Limit](https://help.zscaler.com/zscaler-client-connector/removing-device-if-i-reach-number-devices-limit).
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)22 Zscaler Client Connector License not Subscribed.This error occurs when the device attempts to connect to the cloud and your organization is not licensed or the status of your account is "Agreement Pending."Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)23 Zscaler Client Connector Internal Error, Please Contact Administrator.This is a generic error.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)24 Zscaler Client Connector Internal Error, Please Contact Administrator.This error is triggered by the cloud servers.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)25 Zscaler Client Connector Internal Error, Please Contact Administrator.This error is triggered by the cloud servers.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)26 Zscaler Client Connector Internal Error, Please Contact Administrator.This error occurs when the server is provided invalid device information.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)27 Zscaler Client Connector Internal Error, Please Contact Administrator.This error occurs when a device sends an invalid authentication token.Have the user reauthenticate to Zscaler Client Connector.

If the issue persists, export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)28 Zscaler Client Connector Internal Error, Please Contact Administrator.This error occurs when a device does not send an authentication token.Have the user reauthenticate to Zscaler Client Connector.

If the issue persists, export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1000 Zscaler Client Connector License not Subscribed.This error occurs when the device attempts to connect to the cloud and your organization is not licensed or the organization does not exist.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1001 Zscaler Client Connector License not Subscribed.This error occurs when the device attempts to connect to the cloud and your organization is not licensed or the organization does not exist.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1002 Failed to Authenticate, Credentials are not Valid.This error occurs when the user enters the incorrect credentials.Verify if the user’s credentials are correct.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1003 Zscaler Client Connector Internal Error, Please Contact Administrator.This is a generic error.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1004 Zscaler Client Connector Internal Error, Please Contact Administrator.This is a generic error.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1005 IdP Authentication Failed, Please Contact Administrator.This error occurs when the cloud receives an invalid SAML response.Verify the SAML configuration and check the SAML process end-to-end.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1006 Zscaler Client Connector Internal Error, Please Contact Administrator This is triggered when the cloud is unable to process the credentials provided.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1007 Zscaler Client Connector Internal Error, Please Contact Administrator.This error is triggered by the cloud servers.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1008 LDAP Authentication Failed, Please Contact Administrator.This error occurs when the Zscaler Authentication Bridge (ZAB) is not connected.Verify the LDAP configuration and ensure network connectivity.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1009 LDAP Authentication Failed, Please Contact Administrator.This is an internal error.Verify the LDAP configuration and ensure network connectivity.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1010 Zscaler Client Connector Internal Error, Please Contact Administrator.This error is triggered when the cloud is unable to process the credentials provided by the user.Verify the LDAP configuration and ensure that the credentials entered by the user are valid.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1011 Zscaler Client Connector Internal Error, Please Contact Administrator.This error is triggered when the cloud is unable to process the credentials provided by the user.Verify the LDAP configuration and ensure that the credentials entered by the user are valid.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1012 Failed to Authenticate, Please try again.This error occurs when user information cannot be found.Verify the user’s credentials.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1013 LDAP Authentication Failed, Please Contact Administrator.This error occurs when the user is not found or has been deleted.Verify the LDAP configuration and ensure network connectivity.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1014 LDAP Authentication Failed, Please Contact Administrator.This error occurs when LDAP services are down for the organization.Verify the LDAP configuration and ensure network connectivity.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1015 LDAP Authentication Failed, Please Contact Administrator.This error occurs when the Central Authority (CA) is not ready to authenticate.Verify the LDAP configuration and ensure network connectivity.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1016 LDAP Authentication Failed, Please Contact Administrator.This error is triggered when the cloud fails to communicate with LDAP.Verify the LDAP configuration and ensure network connectivity.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1017 LDAP Authentication Failed, Please Contact Administrator.This error is triggered when the cloud fails to communicate with LDAP.Verify the LDAP configuration and ensure network connectivity.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1018 LDAP Authentication Failed, Please Contact Administrator.This error is triggered when the cloud fails to communicate with LDAP.Verify the LDAP configuration and ensure network connectivity.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)1019 Failed to Authenticate, Credentials are not Valid.This error occurs when the user enters the incorrect credentials.Verify the user’s credentials.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)10060 Network Connection Failed: Check Your Network.This error occurs when Zscaler Client Connector fails to connect to your network.Check network connectivity.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)10101 Failed to Discover Service for Given User.This error occurs when Zscaler Client Connector fails to fetch user cloud information.Verify that the user's credentials.

Verify that the user exists in the user authentication source.

Verify your company name is provisioned correctly.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)10104 Service Configuration not Found.This error occurs when Zscaler Client Connector fails to fetch the policy.Export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)10108 Failed to Enroll Device.This error occurs when the device registration fails either due to an inability to reach the server or an error response from the server. This applies to Internet & SaaS and Private Access.Check network connectivity.

Check if there is a trust post for the cloud status.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)10110 Username not valid.This error occurs when the user enters a username that cannot be found.Verify that the user is entering the correct username.

Verify that the user exists in the user authentication source.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)10111 Internal Error.This error occurs due to an issue in the processing of a request.Retry the failed operation.

If the error persists, export logs and contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)10112 Internal Error: Contact Administrator.This is a generic error.Export logs and contact Zscaler Support.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-automated-device-cleanup -->

Title: Configuring Automated Device Cleanup | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-automated-device-cleanup

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Configuring Automated Device Cleanup

Zscaler Client Connector allows you to configure automatic device cleanup of old, inactive, and removed devices.

After modifying settings, it can take up to a week for Enrolled Devices to reflect changes.

To configure automatic device cleanup:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**Client Connector Device Management**.
2.   On the **Device Cleanup**tab:

    *   **Force Remove Oldest Device After User Enrolls**: Select the threshold number of devices. If a user attempts to enroll a device after reaching the threshold number, Zscaler Client Connector force removes the oldest device. The default setting is **Restrict**, which means no devices are removed. An error is displayed when a user tries to enroll more than 16 devices.

Contact Zscaler Client Connector to change the minimum threshold to 1 device.

    *   **Automatically Force Remove Inactive Devices After**: Select the period after which Zscaler Client Connector automatically removes a device if it doesn't connect to Internet & SaaS in the defined period and becomes inactive. Select from the following intervals: **30**, **60**, **90**, **120**, **150**, **180** days, or **Never**. The default setting is **Never**. A device becomes inactive when the most recent **KeepAlive Timestamp** is older than the defined period.
    *   **Permanently Delete Removed Devices After**: Select the period after which a device is permanently removed from the Zscaler Admin Console after being in the **Removed**or **Unregistered**state. Select from the following intervals: **60**, **90**, **120**, **150**, or **180** days. Zscaler Client Connector uses the device’s Last Deregistration Timestamp to determine how long the device was in the [Removed or Unregistered](https://help.zscaler.com/zscaler-client-connector/device-states-enrolled-devices) state since the time it was deregistered.

Removing the registration from the Zscaler Admin Console doesn’t [remove Zscaler Client Connector from the device](https://help.zscaler.com/zscaler-client-connector/uninstalling-zscaler-client-connector).

![Image 2: Device Cleanup](https://help.zscaler.com/downloads/zscaler-client-connector/managing-devices/configuring-automated-device-cleanup/zclient-connector-device-management.png)

3.   Click **Save**.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard -->

Title: Understanding the Zscaler Client Connector Dashboard | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Understanding the Zscaler Client Connector Dashboard

The Zscaler Client Connector Dashboard provides information [about enrolled devices](https://help.zscaler.com/zscaler-client-connector/about-enrolled-devices) for your organization, including information about service status.

To view the dashboard, click **Analytics**, enable the **Switch to Existing Reports** toggle, and then go to **Connectors > Dashboard**.

## Dashboard

The Dashboard option in the left-side navigation covers the following information:

![Image 2: Zscaler Client Connector Dashboard](https://help.zscaler.com/downloads/zscaler-client-connector/monitoring-usage/understanding-zscaler-client-connector-portal-dashboard/zclient-connector-dashboard-options.png)

*   [Platform Details](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#platform-details)

[](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard)This page provides OS platform details such as device model and device state.

## Dashboard Filters

Use the following filters to modify the content shown in the Platform Details page widgets. After you select filters, click **Apply** in the upper-right corner of the page.

    *   **States**: View data for devices with all device states, or a specific state (i.e., **Registered**, **Unregistered**, **Removal Pending**, **Removed**, **Quarantined**).
    *   **OS**: View data for all operating systems or a specific operating system.

## Widgets

The Platform Details page provides the following details:

    *   [Version Distribution](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#Sup-Unsupported)

[](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard)This widget displays the number of devices installed with supported and unsupported versions of Zscaler Client Connector, defined as:

        *   **Supported**: Zscaler Client Connector versions enabled on the [New Releases tab](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-store) of the Client Connector App Store and listed on the [Update Settings tab](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-store) of the Client Connector App Store.
        *   **Unsupported**: Zscaler Client Connector versions enabled on the [New Releases tab](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-store) of the Client Connector App Store but are earlier than the supported versions listed on the [Update Settings tab](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-store) of the Client Connector App Store.

![Image 3: Zscaler Client Connector Dashboard - Version Distribution chart](https://help.zscaler.com/downloads/zscaler-client-connector/monitoring-usage/about-failed-posture-devices/zclient-connector-platform-details-version-dist.png)

Hover over the chart to view the number of devices installed with supported and unsupported versions of Zscaler Client Connector, shown by percentage for each OS. Click the graph to view a list of all devices, by User ID, installed with either supported or unsupported versions of Zscaler Client Connector in [Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/about-enrolled-devices). You can use this list to view which Zscaler Client Connector versions are installed and identify users who must update to a supported version of Zscaler Client Connector.

[Close](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#Sup-Unsupported)

    *   [Device Model](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#Device-Model)

[](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard)This widget displays the enrolled device models. It displays the top 5 device models and groups all other models in the Others category.

![Image 4: Zscaler Client Connector Dashboard - Device Model chart](https://help.zscaler.com/downloads/zscaler-client-connector/monitoring-usage/about-failed-posture-devices/zclient-connector-platform-details-device-model.png)

        *   Hover over the chart to view the following:
            *   The details for a specific device model, which includes:
                *   The model name
                *   The number of enrolled devices for the model
                *   The percentage of enrolled devices for the model

            *   The details for the Others category, which includes:
                *   The number of enrolled devices grouped under Others
                *   The percentage of enrolled devices grouped under Others

        *   To view a list in **Enrolled Devices**for all devices for a specific model, click the model in the chart.
        *   To view a list in **Enrolled Devices** for all devices in the Others category, click **Others**in the chart. The list of devices is organized by device model.

[Close](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#Device-Model)

    *   [Device OS](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#Device-OS)

[](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard)This widget displays the enrolled devices operating systems. It displays the top 5 OSs and groups all other OSs in the Others category.

![Image 5: Zscaler Client Connector Dashboard - Device OS chart](https://help.zscaler.com/downloads/zscaler-client-connector/monitoring-usage/about-failed-posture-devices/zclient-connector-platform-details-device-os.png)

        *   Hover over the chart to view the following:
            *   The details for a specific OS, which includes:
                *   The OS name
                *   The number of enrolled devices for the OS
                *   The percentage of enrolled devices for the OS

            *   The details for the Other category, which includes:
                *   The number of enrolled devices grouped under Others
                *   The percentage of enrolled devices grouped under Others

        *   To view a list in **Enrolled Devices** for all devices for a specific OS, click the OS in the chart.
        *   To view a list in **Enrolled Devices** for all devices in the Others category, click **Others**in the chart. The list of devices is organized by OS.

[Close](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#Device-OS)

    *   [Device State](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#Device-State)

[](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard)This widget displays the states of the enrolled devices and the [app profiles](https://help.zscaler.com/zscaler-client-connector/about-zscaler-app-profiles) applied to the devices.

The possible device states are:

        *   Registered
        *   Unregistered
        *   Removal Pending
        *   Removed
        *   Quarantined

To learn more about device states, see [Device States for Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/device-states-enrolled-devices).

![Image 6: Zscaler Client Connector Dashboard - Device State chart](https://help.zscaler.com/downloads/zscaler-client-connector/monitoring-usage/about-failed-posture-devices/zclient-connector-platform-details-device-state.png)

        *   Hover over the chart to view the following:
            *   The state name
            *   The number of enrolled devices for the state
            *   The percentage of enrolled devices for the state

        *   To view a list in **Enrolled Devices** for all devices for a specific state, click the state in the chart.

[Close](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#Device-State)

    *   [Enrolled Devices by Platform](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#Zscaler-App-by-Platform)

[](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard)This widget displays the enrolled devices platforms.

![Image 7: Zscaler Client Connector Dashboard - Enrolled Devices by Platform chart](https://help.zscaler.com/downloads/zscaler-client-connector/monitoring-usage/about-failed-posture-devices/zclient-connector-platform-details-enrolled-devices.png)

        *   Hover over the chart to view the following:
            *   The platform name
            *   The number of enrolled devices for the platform

        *   To view a list in **Enrolled Devices** for all devices for a specific platform, click the platform in the chart.

[Close](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#Zscaler-App-by-Platform)

    *   [License Usage](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#Zscaler-App-License)

[](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard)This widget displays your organization's subscription and license information.

This widget only applies to Internet & SaaS. You won't see this widget if you only have Private Access.

![Image 8: Zscaler Client Connector Dashboard - License Usage chart](https://help.zscaler.com/downloads/zscaler-client-connector/monitoring-usage/about-failed-posture-devices/zclient-connector-platform-details-license-usage.png)

        *   Hover over the chart to view the following:

            *   **Subscribed**: The number of user subscriptions for Zscaler Client Connector.
            *   **Used**: The number of users that are enrolled to Zscaler Client Connector.

Licenses are calculated based on the unique username. For example, even if a user has Zscaler Client Connector installed on three devices, the user still has only one license.

        *   To view a list of all enrolled devices, click a category in the chart.

[Close](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#Zscaler-App-License)

![Image 9: Zscaler Client Connector Dashboard](https://help.zscaler.com/downloads/zscaler-client-connector/monitoring-usage/about-failed-posture-devices/zclient-connector-platform-details-main-page.png)

[Close](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#platform-details)

*   [Device Posture](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#device-posture-failure)

[](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard)This page provides information about devices that fail their [device posture checks](https://help.zscaler.com/zscaler-client-connector/about-device-posture-profiles).

The Device Posture page is available only if you have Zscaler Digital Experience (ZDX) or Zscaler Client Connector Telemetry enabled.

## Dashboard Filters

Use the following filters to modify the content shown in the Device Posture page widgets. After you select filters, click **Apply** in the upper-right corner of the page.

    *   **Time**: Modify the failed postures times ranging from three-hour intervals up to 24 hours. You can set the filter to show data up to the past 7 days.
    *   **OS**: View devices for either Windows or macOS or for both of the operating systems. By default, both operating systems are shown.

## Widgets

The Device Posture page provides the following widgets:

    *   [Top 10 Failed Device Posture Trend](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#top-10-failed-device-posture-trend)[](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard)This widget displays the trendline of the top 10 device posture profiles that have the highest number of devices failing their device posture checks. You can select a different set of up to 10 device posture profiles in the Top 50 Failed Posture Profiles widget to view the trendline for those profiles.

[Close](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#top-10-failed-device-posture-trend) 
    *   [Top 50 Failed Posture Profiles](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#top-50-failed-posture-profiles)[](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard)This widget displays the top 50 device posture profiles that have the highest number of devices failing their posture checks. The trendline for the selected device posture profiles displays in the Top 10 Failed Device Posture Trend widget. You can click a device posture profile to view the failed devices on the [Failed Devices](https://help.zscaler.com/zscaler-client-connector/about-failed-posture-devices) page.

[Close](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#top-50-failed-posture-profiles) 
    *   [Top 10 Non-Compliant Devices](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#top-10-non-compliant-devices)[](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard)This widget displays the total number of non-compliant devices for the top 10 device posture failures and the distribution of the devices by device posture profile. Hover over the chart to view the number of devices with posture failures for the specific device posture profile.

[Close](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#top-10-non-compliant-devices) 
    *   [Compliant vs Non-Compliant](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#compliant-vs-non-compliant)[](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard)This widget displays the number of devices with the top 10 failed device posture profiles that are compliant (passed the posture check) and non-compliant (failed the posture check). For the non-compliant devices, the widget displays the total number of posture failures and the upwards or downwards trend for each device posture profile.

[Close](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#compliant-vs-non-compliant) 

[Close](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#device-posture-failure)

*   [Device Events](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#device-events)

[](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard)This page provides information about Internet & SaaS and Private Access service status and about users turning off the services via passwords.

This page is available for Windows users only. You must also have Zscaler Digital Experience (ZDX) or Zscaler Client Connector Telemetry enabled.

## Dashboard Filter

Use the **Time Range** filter to view data for the selected time range. After you select the filter, click**Apply** in the upper-right corner of the page.

## Widgets

The Device Events page provides the following widgets:

    *   [ZIA and ZPA Device Service Status](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#zia-zpa-device-service-status)

[](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard)This widget displays information about the total number of devices enrolled in each service. You can view the following:

        *   **ZDX**: The number of enrolled devices with the ZDX service enabled.
        *   **Non-ZDX**: The number of enrolled devices without the ZDX service enabled.
        *   **ZIA**: The number of enrolled devices with the Internet & SaaS service enabled.
        *   **ZPA**: The number of enrolled devices with the Private Access service enabled.
        *   **Off ZIA**: The number of enrolled devices that have had the Internet & SaaS service turned off
        *   **Off ZPA**: The number of enrolled devices that have had the Private Access service turned off.

[Close](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#zia-zpa-device-service-status)

    *   [ZIA Service Turn Off](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#zia-service-turn-off)

[](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard)This widget displays information about the enrolled devices that have had the Internet & SaaS service turned off. You can view the following:

        *   **Users**: The number of unique users who have turned off the service, even if they have turned it back on.
        *   **Total Number**: The total number of times the service has been turned off by the users, even if a user has turned it back on.

[Close](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#zia-service-turn-off)

    *   [ZPA Service Turn Off](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#zpa-service-turn-off)

[](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard)This widget displays information about the enrolled devices that have had the Private Access service turned off. You can view the following:

        *   **Users**: The number of unique users who have turned off the service, even if they have turned it back on.
        *   **Total Number**: The total number of times the service has been turned off by the users, even if a user has turned it back on.

[Close](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#zpa-service-turn-off)

    *   [Number of Times ZIA and ZPA Turned Off](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#num-times-turned-off)[](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard)This widget displays information about the number of times Internet & SaaS and Private Access were turned off over the course of the past 24 hours. Hover over the graph lines to display the number of devices turned off per service for a particular time.

[Close](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#num-times-turned-off) 
    *   [Password Usage](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#password-usage)

[](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard)This widget displays information about the passwords used to turn off the services. You can view the following:

        *   **Users**: The number of individual users who have turned off the service.
        *   **OTP**: The number of users who used the Disable ZIA OTP password or Disable ZPA OTP password from the [Device Details](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device) for the enrolled device.
        *   **Master**: The number of users who used the Disable Password ZIA password or Disable Password ZPA password from the [app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).

[Close](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#password-usage)

    *   [Password Usage By Type](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#password-usage-by-type)

[](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard)This widget displays information about the passwords used to turn off the services. You can view the following:

        *   **ZIA OTP**: The number of times ZIA was turned off using the Disable ZIA OTP password.
        *   **ZPA OTP**:The number of times ZPA was turned off using the Disable ZPA OTP password.
        *   **ZPA Master**: The number of times ZPA was turned off using the Disable Password ZPA password.
        *   **ZIA Master**: The number of times ZIA was turned off using the Disable Password ZIA password.

[Close](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#password-usage-by-type)

![Image 10: View the Device Events page of the Dashboard](https://help.zscaler.com/downloads/zscaler-client-connector/monitoring-usage/understanding-zscaler-client-connector-portal-dashboard/zclient-connector-device-events-page.png)

[Close](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard#device-events)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-profiles -->

Title: About Zscaler Client Connector App Profiles | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-profiles

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### About Zscaler Client Connector App Profiles

In the Zscaler Admin Console, you can configure [app profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles) by adding policy rules to each profile. You can select the order of precedence among the rules and to whom each rule applies (all users or different groups of users). When a user enrolls the app with the Zscaler service, the app uses the order of precedence and identity of the user to download an app profile with the appropriate policy rule. To learn more, see [Zscaler Client Connector Profile Rule Example](https://help.zscaler.com/zscaler-client-connector/zscaler-app-profile-rule-example).

The app checks for profile updates and downloads any changes whenever users log out and back into the app or restart their computers.

App profiles provide the following benefits and allow you to:

*   Control whether users must enter an admin-provided password to log out of, disable, exit, or uninstall the app.
*   Select the forwarding profile for Internet & SaaS and Private Access services.
*   Determine whether the app can install the Zscaler SSL certificate on users' devices to allow SSL inspection of traffic forwarded by the app for the Internet & SaaS service.
*   Control how the app generates logs and the maximum size of its log files for the Internet & SaaS service.
*   Quickly locate an app profile using the search feature.
*   Easily view, edit, copy, and delete app profiles.

## About the App Profiles Page

On the App Profiles page (Infrastructure > Connectors > Client >_OS Name_), you can do the following:

1.   [Configure a new app profile rule](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-profiles).
2.   [Search for an app profile](https://help.zscaler.com/zscaler-client-connector/searching-app-profile).
3.   View a list of all configured app profile rules.
4.   [Copy an existing profile that you can customize to create a new profile](https://help.zscaler.com/zscaler-client-connector/copying-app-profile).
5.   Edit items in the Zscaler Admin Console or [view the policy token for an app profile rule](https://help.zscaler.com/zscaler-client-connector/viewing-policy-token-zscaler-app-profile).
6.   Delete items in the Zscaler Admin Console.
7.   View the default policy.

![Image 2: About Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/downloads/zscaler-client-connector/zscaler-client-connector-profile-management/about-zscaler-client-connector-app-profiles/zclient-connector-about-app-profiles.png)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-store -->

Title: About the Zscaler Client Connector App Store | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-store

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### About the Zscaler Client Connector App Store

[Watch a video about best practices for updating Zscaler Client Connector](https://fast.wistia.net/embed/iframe/y5kg0profi) (shows Legacy UI).

In the Zscaler Admin Console on the Zscaler Client Connector App Store page, you can control which version (if any) of Zscaler Client Connector is available when end users manually update the app or when you configure automatic app updates. You can also download Zscaler Client Connector from this page.

If you don't have access to the Zscaler Client Connector App Store, contact your organization's support team.

## About the Client Connector App Store Page

On the Client Connector App Store page (Infrastructure > Common Resources > Deployment > Registered Devices),you can do the following:

1.   [Configure update settings for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-update-settings-zscaler-client-connector).
2.   View the latest released versions in the **General Availability** section and versions that are not yet fully qualified in the**Limited Availability** section.

Contact Zscaler Support to enable versions in the **Limited Availability** section.

[See image.](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-store#app-store-new-releases)

Even if you select to install the latest version in the **Update Settings** tab, you must first enable the build in the **New Releases** tab. If you disable a version from the **New Releases** tab, the version is only disabled on the list and is not uninstalled from your system. To learn more, see [Configuring an App Update in the Zscaler Client Connector Store](https://help.zscaler.com/zscaler-client-connector/configuring-update-settings-zscaler-client-connector).

3.   [View and configure Zscaler Digital Experience (ZDX) Module upgrades](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades).
4.   View the list of available Zscaler Client Connector versions for Windows, macOS, Linux, and Android. The latest version is always listed at the top.
    *   In the **Application Version** column, view the latest versions of Zscaler Client Connector available to download.
    *   In the **Registered Devices**column, view the number of devices currently enrolled in the Zscaler service with this app version.
    *   In the **Release Notes** column, view release notes for that version by clicking the **Information** icon.
    *   In the **Revert Version** column, restore a previous version by clicking **Force Revert**. Applies to Zscaler Client Connector versions 4.2 and later for Windows.

Force Revert is not supported for non-persistent virtual desktop infrastructure (VDI).

5.   [Download the latest versions of Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/downloading-zscaler-client-connector).

![Image 2: View where to configure update settings on the app store page](https://help.zscaler.com/downloads/zscaler-client-connector/administration/zscaler-client-connector-store-settings/about-zscaler-client-connector-app-store/zclient-connector-client-connector-app-store.png)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/about-forwarding-profiles -->

Title: About Forwarding Profiles | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/about-forwarding-profiles

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### About Forwarding Profiles

[Watch a video about forwarding profiles.](https://fast.wistia.net/embed/iframe/3gikxer9e2)

The forwarding profile tells Zscaler Client Connector how to treat traffic from your users' systems in different network environments for the Internet & SaaS and Private Access services.

Zscaler Client Connector Forwarding Profiles provide the following benefits and allow you to:

*   Control how traffic flows from user devices in various network environments.
*   Configure different forwarding profiles with different network settings within multiple locations.
*   Save time locating a profile using the Search feature.
*   Easily manage existing profiles using view, edit, copy, and delete features.

Zscaler Client Connector recognizes the following network environments:

*   **On-Trusted Network**: When a user is connected to a private network that belongs to your organization. To allow the app to detect this network, you must set the [Trusted Network Criteria](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app#trusted-network-criteria).
*   **VPN-Trusted Network**: When a user is connected to a trusted network through a VPN in full-tunnel mode. The VPN must be configured to capture all, and not just some, of the user's traffic to the trusted network by installing a default route in the routing table of the client device.

The app does not consider the network a VPN-Trusted network and treats the user as connected to an Off-Trusted Network in the following scenarios:

    *   The VPN doesn't install a default route and uses some other mechanism to capture all of the user's traffic.
    *   The default interface description does not contain the words Cisco, Juniper, Fortinet, PanGP, or VPN.

macOS does not check the interface description for keywords. Instead, it only checks if the VPN created an utun, PPP, or GPD interface.

    *   The VPN runs in split-tunnel mode, so that the app takes only some of the user traffic. The VPN can do this by only installing routes for some subnets (e.g., `10/8` or `192.168/16`) or by installing a DNS on the device to resolve specific requests.

*   **Off-Trusted Network**: When a user is connected to an untrusted network.
*   **Split VPN-Trusted Network**: When a user is connected to a trusted network in split-tunnel mode through a VPN. The app does not consider the network a Split VPN-Trusted Network if:
    *   The VPN runs as a default adapter.
    *   The VPN interface description does not contain the words Cisco, Juniper, Fortinet, PanGP, or VPN. If these words are missing, the app treats the user as connected to an Off-Trusted Network.
    *   The defined Trusted Network Criteria evaluated against the VPN adapter does not match.

When a user connects to a network, the app checks to determine what type of network the user is connected to and displays the network type on the [Internet Security](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-internet-security-zscaler-client-connector) window and the [Private Access window](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector). You can configure as many forwarding profiles as you need, and then select the appropriate one when configuring your [app profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-profiles). For example, if you have multiple locations with different network information, you can configure different forwarding profiles so that the app can recognize the correct network for different users and know how to respond upon detecting those networks.

If your users are running the app in conjunction with a VPN client, see [Best Practices for Zscaler Client Connector and VPN Client Interoperability](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-app-and-vpn-client-interoperability) for important additional steps.

## About the Forwarding Profile Page

On the Forwarding Profile page (Infrastructure > Connectors > Client > Forwarding Profile for Platforms), you can do the following:

1.   [Add a forwarding profile.](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app)
2.   [Search for a forwarding profile](https://help.zscaler.com/zscaler-client-connector/searching-forwarding-profile?check_logged_in=1).
3.   View a list of all configured forwarding profiles.
4.   [Edit a forwarding profile.](https://help.zscaler.com/zscaler-client-connector/editing-or-deleting-items-zscaler-app-portal)
5.   [Copy an existing forwarding profile that you can customize to create a new profile.](https://help.zscaler.com/zscaler-client-connector/copying-forwarding-profile)
6.   [Delete a forwarding profile.](https://help.zscaler.com/zscaler-client-connector/editing-or-deleting-items-zscaler-app-portal)
7.   [View the default forwarding profile.](https://help.zscaler.com/zscaler-client-connector/about-forwarding-profiles#default-forwarding-profile)

[](https://help.zscaler.com/zscaler-client-connector/about-forwarding-profiles)If you don't configure additional forwarding profiles, Zscaler provides a default forwarding profile that is applied automatically. It is always the last forwarding profile in the table on the Forwarding Profile page. Click the **View**icon to see the profile's settings.

## Default Forwarding Profile Action for Internet & SaaS

    *   **On-Trusted Network**: When the user is connected to a trusted network, Zscaler Client Connector uses the Tunnel mode to forward user traffic to Zscaler. It also disables System Proxy settings so that users cannot change proxy settings to bypass Zscaler Client Connector for internet security.
    *   **VPN-Trusted Network**: When a VPN client on the user's device is running in full-tunnel mode, the app does not forward user traffic to Zscaler. All proxy settings are disabled to ensure users do not send their traffic to a different proxy.
    *   **Off-Trusted Network**: When the user is off a trusted network, the app uses the Tunnel mode to forward user traffic to Zscaler. It also disables System Proxy settings so that users cannot change proxy settings to bypass the app for internet security.

## Default Forwarding Profile Action for Private Access

    *   **On-Trusted Network**: Zscaler Client Connector uses the Private Access service to provide users with access to internal applications even when users are on trusted networks.
    *   **VPN-Trusted Network**: Private Access is disabled, and the app does not provide users with access to internal applications.
    *   **Off-Trusted Network**: The app uses the Private Access service to provide users with access to internal applications even when users are on trusted networks.

[Close](https://help.zscaler.com/zscaler-client-connector/about-forwarding-profiles#default-forwarding-profile)

![Image 2: Forwarding Profile page](https://help.zscaler.com/downloads/zclient-connector-forwarding-profile-page.png)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/about-app-supportability -->

Title: About App Supportability | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/about-app-supportability

Markdown Content:
# About App Supportability | Zscaler

[![Image 1: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Client Connector

- [x] All

- [x] Getting Started with Zscaler

- [x] Internet & SaaS (ZIA)

- [x] Private Access (ZPA)

- [x] Digital Experience Monitoring (ZDX)

- [x] Workflow Automation

- [x] Data Security Posture Management (DSPM)

- [x] Client Connector

- [x] Cloud & Branch Connector

- [x] Zscaler Cellular

- [x] Zero Trust Branch

- [x] Unified Vulnerability Management (UVM)

- [x] Asset Exposure Management

- [x] External Attack Surface Management

- [x] SOC Workbench

- [x] Deception

- [x] Identity Protection

- [x] Breach Predictor

- [x] Zero Trust Browser

- [x] Risk360

- [x] Business Insights

- [x] Authentication Service

- [x] Zscaler SDK for Mobile Apps

- [x] AI Asset Management

- [x] Secure Access to AI Apps

- [x] Secure AI Apps & Infrastructure

- [x] Legacy Zscaler APIs

- [x] Logs & Fair Use

- [x] Zscaler Support Troubleshooting Runbooks

- [x] Zscaler Deployments & Operations

- [x] Zscaler Technology Partners

- [x] Multi-Tenant Portal

- [x] Product Usage Terms & Policies

EN

English 日本語

*   [Zscaler Client Connector Help](https://help.zscaler.com/zscaler-client-connector)
*   [Zscaler Client Connector Support Settings](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-support-settings)
*   [App Supportability](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-support-settings/app-supportability)
*    About App Supportability 

![Image 2: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 3: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### About App Supportability

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

App Supportability settings provide you with the following benefits and allow you to configure the following settings for Zscaler Client Connector:

*   Allow users to request support for the app by clicking Report an Issue. You can specify whether their request goes only to your organization's support admin or whether it also goes directly to Zscaler Support.
*   Allow users to access logging controls. Users can export logs as a text file, clear logs, and change the logging mode.
*   Allow users to troubleshoot the app by clicking Restart Service or Repair Service.
*   Configure Zscaler Client Connector to automatically populate the username field for your organization’s IdP login form, using either JavaScript or SAML.

# About the App Supportability Page

On the App Supportability page (Infrastructure > Connectors > Client > App Supportability):

1.   [Configure user access to logging controls for Zscaler Client Connector.](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-logging-controls-zscaler-app)
2.   [Configure user access to support options for Zscaler Client Connector.](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-support-options-zscaler-app)
3.   [Configure user access to the restart and repair options for Zscaler Client Connector.](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app)
4.   [Configure Zscaler Client Connector Telemetry](https://help.zscaler.com/zscaler-client-connector/enabling-zscaler-client-connector-telemetry).
5.   [Configure automatic username population for IdP authentication.](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-username-population-idp-authentication)
6.   [Configure automatic ZPA reauthentication.](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication)
7.   [Register devices with the ZPA IdP username.](https://help.zscaler.com/zscaler-client-connector/registering-devices-zpa-idp-username)

![Image 4: App Supportability](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/about-app-supportability-doc-62854/App-Supportability1.png)
To learn more about other Zscaler Client Connector Support features, see [About Zscaler Client Connector Support](https://help.zscaler.com/zscaler-client-connector/about-zscaler-app-support).

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[About App Supportability](https://help.zscaler.com/zscaler-client-connector/about-app-supportability)[Configuring User Access to Logging Controls for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-logging-controls-zscaler-client-connector)[Configuring User Access to Support Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-support-options-zscaler-client-connector)[Configuring User Access to the Restart and Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-and-repair-options-zscaler-client-connector)[Enabling Zscaler Client Connector Telemetry](https://help.zscaler.com/zscaler-client-connector/enabling-zscaler-client-connector-telemetry)[Configuring Automatic Username Population for IdP Authentication](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-username-population-idp-authentication)[Configuring Automatic ZPA Reauthentication](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication)[Registering Devices with the ZPA IdP Username](https://help.zscaler.com/zscaler-client-connector/registering-devices-zpa-idp-username)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/advanced-configuration-zscaler-client-connector -->

Title: Advanced Configuration for Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/advanced-configuration-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Advanced Configuration for Zscaler Client Connector

Ask Zscaler Preview

Hello, what can I help you with?

In the Zscaler Admin Console, you can configure advanced settings for the Zscaler Admin Console itself and Zscaler Client Connector.

## About the Advanced Configuration Page

On the Advanced Configuration page (Infrastructure > Connectors > Client > Directory Sync and Custom Root Cert), you can do the following:

1.   [Check the next sync time and manually sync groups between Internet & SaaS and Zscaler Client Connector.](https://help.zscaler.com/zscaler-client-connector/syncing-directory-groups-between-internet-saas-and-zscaler-client-connector)
2.   [Upload your organization's custom SSL certificate for Zscaler to automatically use with Zscaler Client Connector.](https://help.zscaler.com/zscaler-client-connector/uploading-custom-ssl-certificate-zscaler-app)

![Image 2: Tasks on the Advanced Configuration page](https://help.zscaler.com/downloads/zclient-connector-advanced-configuration-page.png)

###### Was this article helpful? Click an icon below to submit feedback.

---

<!-- source: https://help.zscaler.com/legacy-apis/understanding-zscaler-client-connector-api -->

Title: Understanding the Zscaler Client Connector API | Zscaler

URL Source: https://help.zscaler.com/legacy-apis/understanding-zscaler-client-connector-api

Markdown Content:
# Understanding the Zscaler Client Connector API | Zscaler

[![Image 1: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Legacy Zscaler APIs

- [x] All

- [x] Getting Started with Zscaler

- [x] Internet & SaaS (ZIA)

- [x] Private Access (ZPA)

- [x] Digital Experience Monitoring (ZDX)

- [x] Workflow Automation

- [x] Data Security Posture Management (DSPM)

- [x] Client Connector

- [x] Cloud & Branch Connector

- [x] Zscaler Cellular

- [x] Zero Trust Branch

- [x] Unified Vulnerability Management (UVM)

- [x] Asset Exposure Management

- [x] External Attack Surface Management

- [x] SOC Workbench

- [x] Deception

- [x] Identity Protection

- [x] Breach Predictor

- [x] Zero Trust Browser

- [x] Risk360

- [x] Business Insights

- [x] Authentication Service

- [x] Zscaler SDK for Mobile Apps

- [x] AI Asset Management

- [x] Secure Access to AI Apps

- [x] Secure AI Apps & Infrastructure

- [x] Legacy Zscaler APIs

- [x] Logs & Fair Use

- [x] Zscaler Support Troubleshooting Runbooks

- [x] Zscaler Deployments & Operations

- [x] Zscaler Technology Partners

- [x] Multi-Tenant Portal

- [x] Product Usage Terms & Policies

EN

English 日本語

*   [Legacy Zscaler APIs Help](https://help.zscaler.com/legacy-apis)
*   [Zscaler Client Connector API](https://help.zscaler.com/legacy-apis/zscaler-client-connector-api)
*    Understanding the Zscaler Client Connector API 

*   [Understanding the Zscaler Client Connector API](https://help.zscaler.com/legacy-apis/understanding-zscaler-client-connector-api)

*   API Developer & Reference Guide 
    *   [Getting Started](https://help.zscaler.com/legacy-apis/getting-started-client-connector-api)
    *   [Understanding Rate Limiting](https://help.zscaler.com/legacy-apis/understanding-rate-limiting-3)
    *   [Understanding Error Codes](https://help.zscaler.com/legacy-apis/about-error-codes)

    *   Reference Guide 

        
            *   Login Controller 
                *   /papi/auth/v1/login 
                    *   [POST](https://help.zscaler.com/legacy-apis/login-controller#/papi/auth/v1/login-post)

        
            *   Public API Controller 
                *   /papi/public/v1/downloadDevices 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/downloadDevices-get)

                *   /papi/public/v1/downloadDisableReasons 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/downloadDisableReasons-get)

                *   /papi/public/v1/downloadServiceStatus 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/downloadServiceStatus-get)

                *   /papi/public/v1/editAdminUser 
                    *   [PUT](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/editAdminUser-put)

                *   /papi/public/v1/forceRemoveDevices 
                    *   [POST](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/forceRemoveDevices-post)

                *   /papi/public/v1/getAdminRoles 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/getAdminRoles-get)

                *   /papi/public/v1/getAdminUsers 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/getAdminUsers-get)

                *   /papi/public/v1/getAdminUsersSyncInfo 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/getAdminUsersSyncInfo-get)

                *   /papi/public/v1/getCompanyInfo 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/getCompanyInfo-get)

                *   /papi/public/v1/getDeviceCleanupInfo 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/getDeviceCleanupInfo-get)

                *   /papi/public/v1/getDeviceDetails 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/getDeviceDetails-get)

                *   /papi/public/v1/getDevices 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/getDevices-get)

                *   /papi/public/v1/getOtp 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/getOtp-get)

                *   /papi/public/v1/getPasswords 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/getPasswords-get)

                *   /papi/public/v1/managePass 
                    *   [POST](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/managePass-post)

                *   /papi/public/v1/getWebPrivacyInfo 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/getWebPrivacyInfo-get)

                *   /papi/public/v1/getZdxGroupEntitlements 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/getZdxGroupEntitlements-get)

                *   /papi/public/v1/getZpaGroupEntitlements 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/getZpaGroupEntitlements-get)

                *   /papi/public/v1/removeDevices 
                    *   [POST](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/removeDevices-post)

                *   /papi/public/v1/removeMachineTunnel 
                    *   [POST](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/removeMachineTunnel-post)

                *   /papi/public/v1/setDeviceCleanupInfo 
                    *   [PUT](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/setDeviceCleanupInfo-put)

                *   /papi/public/v1/setWebPrivacyInfo 
                    *   [PUT](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/setWebPrivacyInfo-put)

                *   /papi/public/v1/syncZiaZdxAdminUsers 
                    *   [POST](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/syncZiaZdxAdminUsers-post)

                *   /papi/public/v1/syncZpaAdminUsers 
                    *   [POST](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/syncZpaAdminUsers-post)

                *   /papi/public/v1/updateZdxGroupEntitlement 
                    *   [PUT](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/updateZdxGroupEntitlement-put)

                *   /papi/public/v1/updateZpaGroupEntitlement 
                    *   [PUT](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/updateZpaGroupEntitlement-put)

                *   /papi/public/v1/web/policy/activate 
                    *   [PUT](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/web/policy/activate-put)

                *   /papi/public/v1/web/policy/edit 
                    *   [PUT](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/web/policy/edit-put)

                *   /papi/public/v1/web/policy/listByCompany 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/web/policy/listByCompany-get)

                *   /papi/public/v1/web/policy/{policyId}/delete 
                    *   [DELETE](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/web/policy/{policyId}/delete-delete)

                *   /papi/public/v1/webAppService/listByCompany 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/webAppService/listByCompany-get)

                *   /papi/public/v1/webFailOpenPolicy/edit 
                    *   [PUT](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/webFailOpenPolicy/edit-put)

                *   /papi/public/v1/webFailOpenPolicy/listByCompany 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/webFailOpenPolicy/listByCompany-get)

                *   /papi/public/v1/webForwardingProfile/edit 
                    *   [POST](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/webForwardingProfile/edit-post)

                *   /papi/public/v1/webForwardingProfile/listByCompany 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/webForwardingProfile/listByCompany-get)

                *   /papi/public/v1/webForwardingProfile/{profileId}/delete 
                    *   [DELETE](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/webForwardingProfile/{profileId}/delete-delete)

                *   /papi/public/v1/webTrustedNetwork/create 
                    *   [POST](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/webTrustedNetwork/create-post)

                *   /papi/public/v1/webTrustedNetwork/edit 
                    *   [PUT](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/webTrustedNetwork/edit-put)

                *   /papi/public/v1/webTrustedNetwork/listByCompany 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/webTrustedNetwork/listByCompany-get)

                *   /papi/public/v1/webTrustedNetwork/{networkId}/delete 
                    *   [DELETE](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/webTrustedNetwork/{networkId}/delete-delete)

                *   /papi/public/v1/application-profiles 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/application-profiles-get)

                *   /papi/public/v1/application-profiles/{profileId} 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/application-profiles/{profileId}-get)
                    *   [PATCH](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/application-profiles/{profileId}-patch)

                *   /papi/public/v1/custom-ip-based-apps 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/custom-ip-based-apps-get)

                *   /papi/public/v1/custom-ip-based-apps/{appId} 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/custom-ip-based-apps/{appId}-get)

                *   /papi/public/v1/forwarding-profiles 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/forwarding-profiles-get)

                *   /papi/public/v1/forwarding-profiles/{profileId} 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/forwarding-profiles/{profileId}-get)
                    *   [PATCH](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/forwarding-profiles/{profileId}-patch)

                *   /papi/public/v1/predefined-ip-based-apps 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/predefined-ip-based-apps-get)

                *   /papi/public/v1/predefined-ip-based-apps/{appId} 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/predefined-ip-based-apps/{appId}-get)

                *   /papi/public/v1/process-based-apps 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/process-based-apps-get)

                *   /papi/public/v1/process-based-apps/{appId} 
                    *   [GET](https://help.zscaler.com/legacy-apis/public-api-controller#/papi/public/v1/process-based-apps/{appId}-get)

![Image 2: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

### Understanding the Zscaler Client Connector API

Availability of the Zscaler Client Connector API is limited. To enable the API for your organization, contact the Zscaler Account team.

The Zscaler Client Connector API gives you programmatic access to manage the following Zscaler Client Connector features:

*   [Login](https://help.zscaler.com/legacy-apis/understanding-zscaler-client-connector-api#LoginController)[](https://help.zscaler.com/legacy-apis/understanding-zscaler-client-connector-api)Login API resources allow you to authenticate with the API using the API Key and the Secret Key. To learn more, see [About API Key Management](https://help.zscaler.com/zscaler-client-connector/about-api-key-management).

[Close](https://help.zscaler.com/legacy-apis/understanding-zscaler-client-connector-api#LoginController) 
*   [Devices](https://help.zscaler.com/legacy-apis/understanding-zscaler-client-connector-api#PublicController)[](https://help.zscaler.com/legacy-apis/understanding-zscaler-client-connector-api)Devices API resources allow you to retrieve a list of devices, their basic details, OTP single-use passwords and app profile passwords for a device, etc. To learn more, see [About Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/about-enrolled-devices).

[Close](https://help.zscaler.com/legacy-apis/understanding-zscaler-client-connector-api#PublicController) 
*   [Application Profiles](https://help.zscaler.com/legacy-apis/understanding-zscaler-client-connector-api#applicationprofile-API)[](https://help.zscaler.com/legacy-apis/understanding-zscaler-client-connector-api)Application profile API resources allow you to configure application and forwarding profiles. To learn more, see [About Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-profiles).

[Close](https://help.zscaler.com/legacy-apis/understanding-zscaler-client-connector-api#applicationprofile-API) 

Prior to using the API, Zscaler recommends reviewing [Getting Started](https://help.zscaler.com/zscaler-client-connector/getting-started-client-connector-api) for information regarding prerequisites, authentication, and making API calls. For detailed information on all available API calls, endpoints, and parameters, see [Reference Guide](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-api/api-developer-reference-guide/reference-guide).

To learn more about rate limits and HTTP status codes, see [Understanding Rate Limiting](https://help.zscaler.com/zscaler-client-connector/understanding-rate-limiting) and [About Error Codes](https://help.zscaler.com/zscaler-client-connector/about-error-codes). If you encounter any issues with the Zscaler Client Connector API, contact Zscaler Support.

###### Was this article helpful? Click an icon below to submit feedback.

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector -->

Title: Configuring Forwarding Profiles for Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Configuring Forwarding Profiles for Zscaler Client Connector

[Watch a video about forwarding profiles.](https://fast.wistia.net/embed/iframe/3gikxer9e2)

The [forwarding profile](https://help.zscaler.com/zscaler-client-connector/about-forwarding-profiles) tells Zscaler Client Connector how to treat traffic from your users' systems in different network environments for the Internet & SaaS and Private Access services.

To configure a forwarding profile:

1.   Go to **Infrastructure > Connectors > Client > Forwarding Profile for Platforms**.
2.   Click **Add Forwarding Profile**.

The **Add Forwarding Profile** window appears.

3.   In the **Add Forwarding Profile** window:
    *   [Profile Definition](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#profile-definition)[](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector)In the **Profile Name** field, enter a unique alphanumeric name for the forwarding profile.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#profile-definition) 
    *   [Trusted Network Criteria](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#trusted-network-criteria)

[](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector)Define the network information and conditions that your network must meet for Zscaler Client Connector to verify that it's one of your trusted networks.

To configure the Trusted Network Criteria:

        1.   **Add Condition**: Select one of the following conditions, then click **Add Condition**.

Zscaler recommends using static properties, such as **DNS Server** and **DNS Search Domains**, for Trusted Network Criteria. In contrast, **Hostname and IP** resolution is a dynamic property because Zscaler Client Connector must resolve a hostname to see if it resolves to the IP address specified in the Trusted Network Criteria. There is a chance that a resolution might fail because of network transition processes. If a resolution fails, the app could incorrectly determine the network is an untrusted one, in which case it applies the incorrect forwarding profile action.

            *   **DNS Server**: The DNS servers where your corporate network sends DNS requests. Enter the DNS servers, separated by commas. IPv6 addresses are supported if you’re using Zscaler Client Connector version 3.4 and later for Windows. The app verifies at least one DNS server.
            *   **DNS Search Domains**: The search domains configured as the primary domains for the network adapter used for connecting to Zscaler. Enter the search domains, separated by commas. The app only verifies the primary domains assigned to the active network adapter.
            *   **Hostname and IP**: The hostname and the IP addresses where the hostname resolves when users are on the corporate network. For **Hostname**, enter the hostname. For **Resolved IPs for Hostname**, enter the IP addresses that the hostname resolves to, separated by commas. IPv6 addresses are supported if you’re using Zscaler Client Connector version 3.4 and later for Windows. The app verifies at least one IP address.
            *   **Network Range**: The network range for subnets and private IPs. Enter the valid network ranges, separated by commas.
            *   **Default Gateway**: Enter the IP address of the default gateway to the internet.
            *   **DHCP Server**: Enter the valid DHCP server, separated by commas.
            *   **Egress IP**: Enter the egress IP address of the network. This option allows you to differentiate a location that matches the trusted network criteria of other locations. Applies only to Zscaler Client Connector version 4.6 and later for Windows.
            *   **Predefined Trusted Networks**: This option is only applicable if you have Zscaler Client Connector version 2.1 and later for Windows. Using this option allows you to select multiple trusted networks. To select networks from the drop-down menu, you must first define the Trusted Network Criteria for each network on the **Trusted Networks** page. To learn more, see [About Trusted Networks](https://help.zscaler.com/zscaler-client-connector/about-trusted-networks).

You cannot configure Trusted Network Criteria for a network (i.e., **DNS Server**, **DNS Search Domains**, or **Hostname and IP**) and use the **Predefined Trusted Networks** option at the same time.

Repeat this step to specify more than one condition.

        2.   **Condition Match**: This field appears after you configure a condition.
            *   If specifying only one condition for the criteria, skip this step.
            *   If specifying multiple conditions for the criteria, select one of the following options:
                *   **Any**: Select if you want the app to validate only one of the conditions to determine if the network is trusted.
                *   **All**: Select if you want the app to validate all of the conditions to determine if the network is trusted.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#trusted-network-criteria)

    *   [Trusted Network Evaluation](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#trusted-network-evaluation)

[](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector)Select how Zscaler Client Connector evaluates trusted network criteria.

        *   Select **Evaluate Trusted NW against All Default Route Adapters** to have Zscaler Client Connector evaluate trusted network criteria against all adapters with a default route. Zscaler Client Connector uses the adapter with the lowest metric if it finds more than one match.
        *   Select**Enable Split VPN-Trusted Network** to have Zscaler Client Connector detect whether the device is running a split-tunnel VPN in addition to the currently supported full-tunnel VPN-trusted network detection.
        *   Select **Skip****Trusted Criteria match for VPN Adapters**to have Zscaler Client Connector not require trusted network criteria to match VPN adapters. The adapter's presence triggers network detection.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#trusted-network-evaluation)

    *   [Windows Driver Selection](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#windows-driver-selection)

[](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector)Select the **Tunnel Driver Type** to use when handling traffic for Windows devices when in [**Tunnel** mode](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app#tunnel):

        *   **Route-Based**: Zscaler Client Connector creates a virtual network adapter. The app uses OS routing to forward traffic via this interface to the Zscaler service.
        *   **Packet Filter-Based**: Zscaler Client Connector uses Windows filtering to capture and forward traffic to the Zscaler service. This option allows for more granular control over traffic, reduces interoperability issues for applications that manage network adapters on the system, and allows VPN clients to better interoperate with the app. You must use this driver if you [enable packet capture](https://help.zscaler.com/zscaler-client-connector/enabling-packet-capture-zscaler-client-connector). This is the default value.

Zscaler Client Connector version 4.8 and later for Windows does not use the route-based driver. If a Windows device has Zscaler Client Connector version 4.8 or later installed, the app always uses the packet filter-based driver, regardless of this setting.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#windows-driver-selection)

    *   [Forwarding Profile Action for Internet & SaaS](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#forwarding-profile-action-zia)

[](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector)Define how Zscaler Client Connector treats traffic from your users' systems for the Internet & SaaS service for On-Trusted Network, VPN-Trusted Network, Off-Trusted Network, or Split VPN-Trusted Network types. To learn more, see [About Forwarding Profiles](https://help.zscaler.com/zscaler-client-connector/about-forwarding-profiles).

To specify how the app treats traffic from your users' systems, select one of the following forwarding profile actions for each network type:

        *   [Tunnel](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#tunnel)

[](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector)In **Tunnel** mode, the app tunnels traffic at the network layer. It captures user traffic by setting IP routes on user devices. The app forwards all port 80/443 traffic to the Zscaler service through a routing mode tunnel called the Zscaler Tunnel (Z-Tunnel).

            1.   (Optional) For **Tunnel Version Selection**, you can select which version of Z-Tunnel to use. To see this field, you must select [Packet Filter Based for the driver method](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app#windows-driver-selection).

To use Z-Tunnel 2.0, you must complete all configuration steps. To learn more, see [Z-Tunnel 2.0](https://help.zscaler.com/zscaler-client-connector/about-z-tunnel-1.0-z-tunnel-2.0#z-tunnel-2).

If you select **Z-Tunnel 2.0**, the **Advanced Z-Tunnel Configuration** menu appears.

[See the instructions for Advanced Z-Tunnel Configuration.](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#advanced-z-tunnel-config)

Zscaler Client Connector version 4.3 and later for iOS supports Z-Tunnel 2.0 over TLS. DTLS is not supported on iOS devices.

                *   **Primary Transport Selection**: Select the primary transport for Z-Tunnel 2.0. This setting determines how Zscaler Client Connector connects to the cloud. **DTLS** uses UDP over port 443 and **TLS** uses TCP over port 443.
                *   **DTLS Connection Timeout**: Enter any value from 5 to 60 seconds. This setting determines how long Zscaler Client Connector waits before falling back to another transport mechanism.
                *   **TLS Connection Timeout**: Enter any value from 5 to 60 seconds. This setting determines how long Zscaler Client Connector waits before falling back to another transport mechanism.
                *   **MTU for Zscaler Adapter**: (Optional) This option is only applicable if you’re using Zscaler Client Connector version 2.1.2 or later. Select Maximum Transmission Unit (MTU) value between 800 and 1400 for Z-Tunnel 2.0 inner IP packet. The default value is 0 (zero). The default value for a direct connection is 1400 and 1360/1300 for GRE/IPSec tunnels, respectively. Windows leverages maximum Maximum Segment Size (MSS) clipping. For IPv6, the acceptable range is 1300-1400.

For the Windows version of Zscaler Client Connector, Z-Tunnel 2.0 (in DTLS mode) changes the MSS for the TCP stream based on the configured MTU value because it uses the Windows filter driver instead of the Zscaler adapter.

                *   **Path MTU Discovery**: Select this option to have Zscaler Client Connector discover the optimal path MTU for Z-Tunnel 2.0. You can use this option even if you change the value for MTU for Zscaler Adapter.
                *   **Allow TLS Fallback**: (Optional) This option is only applicable if you’re using Zscaler Client Connector version 3.4 or later for Windows, Zscaler Client Connector version 3.9 or later for macOS, and Zscaler Client Connector version 4.3 and later for iOS. If DTLS is set as your primary transport selection, you can enable TLS as a fallback option. If Zscaler Client Connector is unable to connect using DTLS, it attempts to connect using TLS.
                *   **Enable DTLS Instability Detection and Fallback**: If DTLS is set as your primary transport selection and **Allow TLS Fallback** is enabled, you can enable this option to have Zscaler Client Connector monitor the stability of the DTLS connection and automatically switch to TLS if a data transfer rate restriction is detected (e.g., throttling due to a misconfigured network device or a network policy).

Applies only to Zscaler Client Connector version 4.8 and later for Windows. Contact Zscaler Support to enable this feature.

                *   **Z-Tunnel 2.0 Setup Failure Behavior**: (Optional) This option is only applicable if you’re using Zscaler Client Connector version 3.4 or later. In cases where you might need Zscaler Client Connector to switch from Z-Tunnel 2.0 to Z-Tunnel 1.0, you can define the behavior for Zscaler Client Connector to follow.
                    *   **Fall back to Z-Tunnel 1.0 and bypass non-web traffic**: Zscaler Client Connector tunnels web traffic via Z-Tunnel 1.0 and bypasses everything else.
                    *   **Fall back to Z-Tunnel 1.0 and block non-web traffic**: Zscaler Client Connector tunnels web traffic via Z-Tunnel 1.0 and blocks all traffic that cannot be tunneled, except the DNS.
                    *   **Block all traffic**: Zscaler Client Connector blocks all traffic except DNS and control-channel communications to the Zscaler Zero Trust Exchange (ZTE) for policy, PAC file, and software updates.

                *   **Send All DNS During Fail-Close to Trusted-DNS Server**: (Optional) Select this option to send DNS traffic to a specific DNS server during a Z-Tunnel 2.0 failure. If enabled, you can enter IP addresses for an IPv4 DNS server and an IPv6 DNS server. If you select this option but don’t enter a server, Zscaler Client Connector uses the DNS server in the original request if it’s configured on the device or the first DNS server on the system.

Applies only to Zscaler Client Connector version 4.7 for Windows and is available only if **Fall back to Z-Tunnel 1.0 and bypass non-web traffic** or **Block all traffic** is selected. Contact Zscaler Support to enable this feature.

                    *   **IPv4 Trusted DNS Server During Fail-Close**: Enter an IPv4 IP address (e.g., `192.0.2.1`). Zscaler Client Connector uses this server for DNS requests to an IPv4 destination.
                    *   **IPv6 Trusted DNS Server During Fail-Close**: Enter an IPv6 IP address (e.g., `[2001:0000::]`). Zscaler Client Connector uses this server for DNS requests to an IPv6 destination.

                *   **Drop IPv6 Include Traffic When not supported**:Enable this option to drop IPv6 traffic that matches the destination inclusion if IPv6 is not provisioned for the company or the Public Service Edge.

Zscaler Client Connector version 4.1 and later for Windows supports IPv6 tunneling in dual stack and IPv6-only networks in both Z-Tunnel 1.0 and Z-Tunnel 2.0. While Internet & SaaS can process both IPv4 and IPv6 traffic, because the infrastructure supports only IPv4 traffic, IPv6-only network addresses are converted to IPv4.

                *   **Redirect Web Traffic to Zscaler Client Connector Listening Proxy**: Enable this option to redirect all 80/443 traffic to the Zscaler Client Connector listening proxy, with the remaining traffic passing through Z-Tunnel 2.0. Enabling this option allows bypasses in the app profile PAC to be applied to web traffic without a forwarding profile PAC.
                *   **Use Z-Tunnel 2.0 for Proxied Web Traffic**: Enable this option to have Zscaler Client Connector use the Z-Tunnel 2.0 protocol to tunnel web traffic received on the Zscaler Client Connector listening proxy. If this option is disabled and **Redirect Web Traffic to Zscaler Client Connector Listening Proxy** is enabled, Zscaler Client Connector uses Z-Tunnel 1.0 for web traffic and Z-Tunnel 2.0 for the remaining traffic.

Zscaler recommends enabling both **Redirect Web Traffic to Zscaler Client Connector Listening Proxy** and **Use Z-Tunnel 2.0 for Proxied Web Traffic**. For more information, see [Best Practices for Adding Bypasses for Z-Tunnel 2.0](https://help.zscaler.com/zscaler-client-connector/best-practices-adding-bypasses-z-tunnel-2.0).

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#advanced-z-tunnel-config)

            2.   For **Block Unreachable Domains Traffic**: This option drops or bypasses domains that are not reachable from the Internet & SaaS Public Service Edge.
            3.   For **Drop IPv6 in Dual Stack Network**:
                *   Enable this option to have Zscaler Client Connector intercept and drop IPv6 packets coming from a dual stack network (IPv4 and IPv6 run simultaneously alongside each other). For Windows platforms, this setting is supported only in LWF mode.
                *   Disable this option when using IPv6. When this option is enabled, it takes precedence over all IPv6 settings.

            4.   For **Drop IPv6 in IPv6 Only Network**:
                *   Enable this option to have Zscaler Client Connector intercept and drop IPv6 packets coming from an IPv6-only network. This setting prevents IPv6-only traffic from bypassing Zscaler Client Connector if IPv6 is not enabled or if IPv6 inclusions are not configured.
                *   Disable this option when using IPv6. When this option is enabled, it takes precedence over all IPv6 settings.

            5.   **Dynamic ZIA Service Edge Assignment**: Enable this option to configure settings for probe interval, probe sample size, and threshold rank. The default setting is disabled.
                *   **Probe Interval (In Seconds)**: The time interval in which an IP address connects with a server. Choose a time between 30 to 600 seconds. The default is 60 seconds.
                *   **Probe Sample Size**: The number of retries required to establish a connection before a failover. The default is 5 times.
                *   **Threshold Rank**: Ranks the change in latency of the primary and secondary Public Service Edges, Private Service Edges, or Virtual Service Edges. Select a rank from 1 to 5 from the drop-down menu. If the value exceeds the threshold limit, the switch is made from the primary to the secondary Service Edge, or vice versa. Rank 1 is a minimum change, which is the most aggressive for switching from the primary to the secondary Service Edge.

            6.   In the **Configure System Proxy Settings** drop-down menu, define the proxy settings for your users’ systems.
                *   **Proxy Action Type**: To define specific system proxy settings for user devices, select one of the following options:
                    *   **Enforce**: If you select this option, Zscaler Client Connector enforces your proxy settings by monitoring system proxy changes and reapplying settings, which ensures that users cannot tamper with their proxy settings.
                    *   **Apply on Trusted Network Type Change**: If you select this option, Zscaler Client Connector only applies your proxy settings when the network type changes. For example, the network shifts from a trusted network to an untrusted one. Zscaler Client Connector only updates system proxy settings on network type changes, but won’t monitor for proxy changes afterward.
                    *   **Apply on Any Network Change**: If you select this option, Zscaler Client Connector only applies your proxy settings if there is any network change, but does not monitor for proxy changes afterward.
                    *   **Never**: If you select this option, Zscaler Client Connector never alters your proxy settings.

                *   Configure the following system proxy settings:
                    *   **Automatically Detect Settings**: Select this option if you want the users’ devices to use proxy discovery on the network.
                    *   **PAC URL Location**: Select this option if you want to use a PAC file to specify automatic proxy settings on users’ devices. Zscaler Client Connector enforces your chosen proxy settings based on the PAC file retrieved from the selected location:
                        *   **PAC URL**: If you select this option, enter a valid PAC URL in the **Custom PAC URL** field. The maximum number of characters is 512. If [Enforce Secure PAC URLs](https://help.zscaler.com/zscaler-client-connector/enforcing-secure-pac-urls) is enabled, you must enter a URL with the https:// prefix (not http://).

Zscaler Client Connector downloads the PAC file from this URL directly. If you restrict access to the PAC file location (e.g., it's hosted in a SIPA domain), Zscaler Client Connector can download the file through Z-Tunnel 2.0. Contact Zscaler Support to enable this option.

                        *   **Registry Key**: If you select this option, enter the following:
                            *   **Registry Path**: The registry path for Zscaler Client Connector to locate the PAC URL from the device. The maximum number of characters is 61,440.
                            *   **Registry Name**: The registry name for Zscaler Client Connector to locate the PAC URL from the device. The maximum number of characters is 2,048.

                    *   **Use Proxy Server for Your LAN**: Select this option if you want to use a specific proxy server and port.
                        *   **IP Address or Domain**: Enter an FQDN, IP address, or a plain hostname with the http:// or https:// prefix.
                        *   **Port**: Enter any port ranging from 1 to 65534.
                        *   **Bypass Proxy Server for Local Addresses**: Select this option if you want to bypass local resources.

                    *   **Execute GPO Update**: Select this option if you want to execute the GPO update command on Windows devices when Zscaler Client Connector detects a network change.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#tunnel)

        *   [Tunnel with Local Proxy](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#tunnel-with-local-proxy)

[](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector)In **Tunnel with Local Proxy** mode, Zscaler Client Connector sets proxy settings on user devices so that all proxy-aware traffic is tunneled to Zscaler. The app does this by automatically installing a PAC file on the system to force all traffic to go to the local host.

When using Zscaler Client Connector for Linux in openSUSE KDE or Arch Linux KDE environments, use Firefox version 109 or earlier. Firefox version 110 can send traffic directly.

            *   **Block Unreachable Domains Traffic**: This option drops or bypasses domains that are not reachable from the Public Service Edge.
            *   **Configure System Proxy Settings:** In the**Proxy Action Type**drop-down menu, the**Enforce** option is selected by default and cannot be changed. This option allows Zscaler Client Connector to enforce your proxy settings by monitoring for network changes and reapplying settings. Zscaler Client Connector also ensures that users cannot tamper with their proxy settings.
            *   Configure the following system proxy settings:
                *   **PAC URL Location**: This option is selected by default and uses a PAC file to specify automatic proxy settings on users’ devices. Zscaler Client Connector enforces your chosen proxy settings based on the PAC file retrieved from the selected location:
                    *   **PAC URL**: If you select this option, enter a valid PAC URL in the **Custom PAC URL** field. The maximum number of characters is 512. If [Enforce Secure PAC URLs](https://help.zscaler.com/zscaler-client-connector/enforcing-secure-pac-urls) is enabled, you must enter a URL with the https:// prefix (not http://).

Zscaler Client Connector downloads the PAC file from this URL directly. If you restrict access to the PAC file location (e.g., it's hosted in a SIPA domain), Zscaler Client Connector can download the file through Z-Tunnel 2.0. Contact Zscaler Support to enable this option.

                    *   **Registry Key**: If you select this option, enter the following:
                        *   **Registry Path**: The registry path for Zscaler Client Connector to locate the PAC URL from the device. The maximum number of characters is 61,440.
                        *   **Registry Name**: The registry name for Zscaler Client Connector to locate the PAC URL from the device. The maximum number of characters is 2,048.

            *   **Execute GPO Update**: Select this option if you want to execute the GPO update command on Windows devices when Zscaler Client Connector detects a network change.

In **Tunnel with Local Proxy** mode, Zscaler recommends you use the **Disable Loopback Restriction**, **Override WPAD**, and **Restart WinHTTP Service** options to ensure the app can properly set proxy settings on Windows devices. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#tunnel-with-local-proxy)

        *   [Enforce Proxy](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#enforce-proxy)

[](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector)In **Enforce Proxy** mode, Zscaler Client Connector enforces system proxy settings as specified, without tunneling any traffic.

Zscaler Client Connector enforces system proxy settings, and all applications on the device must comply with these settings. Administrators will review any custom proxy settings within applications, then restrict these settings.

In the **Configure System Proxy Settings** drop-down menu, define the proxy settings for your users’ systems.

            *   **Proxy Action Type**: The**Enforce** option is selected by default and cannot be changed. This option allows Zscaler Client Connector to enforce your proxy settings by monitoring for network changes and reapplying settings. Zscaler Client Connector also ensures that users cannot tamper with their proxy settings.
            *   Configure the following system proxy settings:
                *   **Automatically Detect Settings**: Select this option if you want the users’ devices to use proxy discovery on the network.
                *   **PAC URL Location**: Select this option if you want to use a PAC file to specify automatic proxy settings on users’ devices. Zscaler Client Connector enforces your chosen proxy settings based on the PAC file retrieved from the selected location:
                    *   **PAC URL**: If you select this option, enter a valid PAC URL in the **Custom PAC URL** field. The maximum number of characters is 512. If [Enforce Secure PAC URLs](https://help.zscaler.com/zscaler-client-connector/enforcing-secure-pac-urls) is enabled, you must enter a URL with the https:// prefix (not http://).
                    *   **Registry Key**: If you select this option, enter the following:
                        *   **Registry Path**: The registry path for Zscaler Client Connector to locate the PAC URL from the device. The maximum number of characters is 61,440.
                        *   **Registry Name**: The registry name for Zscaler Client Connector to locate the PAC URL from the device. The maximum number of characters is 2,048.

                *   **Use Proxy Server for Your LAN**: Select this option if you want to use a specific proxy server and port.
                    *   **IP Address or Domain**: Enter an FQDN, IP address, or a plain hostname with the http:// or https:// prefix.
                    *   **Port**: Enter any port ranging from 1 to 65534.
                    *   **Bypass Proxy Server for Local Addresses**: Select this option if you want to bypass local resources.

                *   **Execute GPO Update**: Select this option if you want to execute the GPO update command on Windows devices when Zscaler Client Connector detects a network change.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#enforce-proxy)

        *   [None](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#none)

[](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector)In **None** mode, Zscaler Client Connector does not tunnel any traffic at all.

In the **Configure System Proxy Settings** drop-down menu, define the proxy settings for your users’ systems.

For **Proxy Action Type**: To define specific system proxy settings for user devices, select one of the following options:

            *   **Apply on Trusted Network Type Change**: If you select this option, Zscaler Client Connector only applies your proxy settings if there is any network change, but does not monitor for proxy changes afterward.
            *   **Apply on Any Network Change**:If you select this option, Zscaler Client Connector only enforces your proxy settings when the network changes, but does not monitor for proxy changes afterward.
            *   **Never**: If you select this option, Zscaler Client Connector never alters your proxy settings.
                *   Configure the following system proxy settings:
                    *   **Automatically Detect Settings**: Select this option if you want the users’ devices to use proxy discovery on the network.
                    *   **PAC URL Location**: Select this option if you want to use a PAC file to specify automatic proxy settings on users’ devices. Zscaler Client Connector enforces your chosen proxy settings based on the PAC file retrieved from the selected location:
                        *   **PAC URL**: If you select this option, enter a valid PAC URL in the **Custom PAC URL** field. The maximum number of characters is 512. If [Enforce Secure PAC URLs](https://help.zscaler.com/zscaler-client-connector/enforcing-secure-pac-urls) is enabled, you must enter a URL with the https:// prefix (not http://).
                        *   **Registry Key**: If you select this option, enter the following:
                            *   **Registry Path**: The registry path for Zscaler Client Connector to locate the PAC URL from the device. The maximum number of characters is 61,440.
                            *   **Registry Name**: The registry name for Zscaler Client Connector to locate the PAC URL from the device. The maximum number of characters is 2,048.

                    *   **Use Proxy Server for Your LAN**: Select this option if you want to use a specific proxy server and port.
                        *   **IP Address or Domain**: Enter an FQDN, IP address, or a plain hostname with the http:// or https:// prefix.
                        *   **Port**:Enter any port ranging from 1 to 65534.
                        *   **Bypass Proxy Server for Local Addresses**: Select this option if you want to bypass local resources.

                    *   **Execute GPO Update**: Select this option if you want to execute the GPO update command on Windows devices when Zscaler Client Connector detects a network change.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#none)

If you want On-Trusted Network behavior to apply to VPN-Trusted Network or Off-Trusted Network, select **Same as "On-Trusted Network"**for either environment.

Zscaler recommends using **Tunnel**(**Packet Filter-Based**) as your forwarding profile action for Windows and using **Tunnel with Local Proxy** for macOS. With these modes, users don’t encounter interoperability issues if they have VPN clients running alongside Zscaler Client Connector. If you experience issues with **Tunnel** (**Packet Filter-Based**), Zscaler recommends that you use **Tunnel with Local Proxy** instead. To learn more, see [Best Practices for Zscaler Client Connector and VPN Client Interoperability](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability).

[This table lists the capabilities for each Internet & SaaS forwarding mode](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#table-modes)

| Capability/Mode | Z-Tunnel 1.0 | Z-Tunnel 2.0 | Enforce Proxy | Tunnel with Local Proxy | None |
| --- | --- | --- | --- | --- | --- |
| Forward all web traffic (80/443) | ![Image 2](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_Yes.png) | ![Image 3](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_Yes.png) | ![Image 4](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_Yes.png) | ![Image 5](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_Yes.png) | ![Image 6](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) |
| Forward all web traffic on nonstandard ports | ![Image 7](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) | ![Image 8](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_Yes.png) | ![Image 9](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_Yes.png) | ![Image 10](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_Yes.png) | ![Image 11](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) |
| Forward all non-web traffic (all ports/protocols) | ![Image 12](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) | ![Image 13](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_Yes.png) | ![Image 14](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) | ![Image 15](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) | ![Image 16](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) |
| Forward web traffic transparently (includes non‑proxy‑aware apps). | ![Image 17](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_Yes.png) | ![Image 18](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_Yes.png) | ![Image 19](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) | ![Image 20](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) | ![Image 21](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) |
| Manage IP-layer bypasses/inclusions/excursions* | ![Image 22](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) | ![Image 23](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_Yes.png) | ![Image 24](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) | ![Image 25](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) | ![Image 26](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) |
| Supports no-default route environments | ![Image 27](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_Yes.png) | ![Image 28](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_Yes.png) | ![Image 29](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_Yes.png) | ![Image 30](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_Yes.png) | ![Image 31](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) |
| Forward no traffic | ![Image 32](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) | ![Image 33](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) | ![Image 34](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) | ![Image 35](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) | ![Image 36](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_Yes.png) |

*Z-Tunnel 1.0 is supported when Z-Tunnel 2.0 is configured to fall back to Z-Tunnel 1.0 for Zscaler Client Connector for Windows 3.4 and later.

To learn more, see [Best Practices for Adding Bypasses for Z-Tunnel 2.0](https://help.zscaler.com/zscaler-client-connector/best-practices-adding-bypasses-z-tunnel-2.0).

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#table-modes)

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#forwarding-profile-action-zia)

    *   [Forwarding Profile Action for Private Access](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#forwarding-profile-action-zpa)

[](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector)Define how Zscaler Client Connector treats traffic from your users' systems for the Private Access service for On-Trusted Network, VPN-Trusted Network, Off-Trusted Network, or Split VPN-Trusted Network types. To learn more, see [About Forwarding Profiles](https://help.zscaler.com/zscaler-client-connector/about-forwarding-profiles).

To specify how the app treats traffic from your users' systems, you can select **Tunnel**or **None**as the forwarding profile actions for each network type:

        *   **On-Trusted Network**:
            *   **Tunnel**: Zscaler Client Connector tunnels traffic for the Private Access service (i.e., Zscaler Client Connector uses the Private Access service to provide users with access to internal applications even when users are on trusted networks).

You can enable **Dynamic ZPA Service Edge Assignment**to configure settings for probe interval, probe sample size, and threshold rank:

                *   **Probe Interval (In Seconds)**: The time interval in which an IP address connects with a server. Select an option from the drop-down menu. Options are in 30-second intervals and range from 30 to 600 seconds. The default is 30 seconds.
                *   **Probe Sample Size**: The number of retries required to establish a connection before a failover. Select an option from the drop-down menu. The default is 5 times.
                *   **Threshold Rank**: Ranks the change in latency of the primary and secondary Public Service Edges. Select a rank from 1 to 5 from the drop-down menu. If the value exceeds the threshold limit, the switch is made from the primary to the secondary Service Edge, or vice versa. Rank 1 is a minimum change, which is the most aggressive for switching from the primary to the secondary Service Edge.
                *   **Enable Machine Tunnel**: Select to enable **Dynamic ZPA Service Edge Assignment** for machine tunnels.

            *   **None**: Private Access is disabled and Zscaler Client Connector does not provide access to internal applications. Users access the applications directly.

        *   **VPN-Trusted Network**:

Do not select **Tunnel**for the **VPN-Trusted Network**type**.**

            *   **Tunnel**: For Private Access, the app does not forward user traffic if a VPN is also running on the device.

You can enable **Dynamic ZPA Service Edge Assignment**to configure settings for probe interval, probe sample size, and threshold rank:

                *   **Probe Interval (In Seconds)**: The time interval in which an IP address connects with a server. Select an option from the drop-down menu. Options are in 30-second intervals and range from 30 to 600 seconds. The default is 30 seconds.
                *   **Probe Sample Size**: The number of retries required to establish a connection before a failover. Select an option from the drop-down menu. The default is 5 times.
                *   **Threshold Rank**: Ranks the change in latency of the primary and secondary Public Service Edges. Select a rank from 1 to 5 from the drop-down menu. If the value exceeds the threshold limit, the switch is made from the primary to the secondary Service Edge, or vice versa. Rank 1 is a minimum change, which is the most aggressive for switching from the primary to the secondary Service Edge.
                *   **Enable Machine Tunnel**: Select to enable **Dynamic ZPA Service Edge Assignment** for machine tunnels.

            *   **None**: Private Access is disabled and Zscaler Client Connector does not provide access to internal applications.

        *   **Off-Trusted Network**:
            *   **Tunnel**: Zscaler Client Connector tunnels traffic for the Private Access service (i.e., Zscaler Client Connector uses the Private Access service to provide users with access to internal applications when users are off trusted networks).

You can enable **Dynamic ZPA Service Edge Assignment**to configure settings for probe interval, probe sample size, and threshold rank:

                *   **Probe Interval (In Seconds)**: The time interval in which an IP address connects with a server. Select an option from the drop-down menu. Options are in 30-second intervals and range from 30 to 600 seconds. The default is 30 seconds.
                *   **Probe Sample Size**: The number of retries required to establish a connection before a failover. Select an option from the drop-down menu. The default is 5 times.
                *   **Threshold Rank**: Ranks the change in latency of the primary and secondary Public Service Edges. Select a rank from 1 to 5 from the drop-down menu. If the value exceeds the threshold limit, the switch is made from the primary to the secondary Service Edge, or vice versa. Rank 1 is a minimum change, which is the most aggressive for switching from the primary to the secondary Service Edge.
                *   **Enable Machine Tunnel**: Select to enable **Dynamic ZPA Service Edge Assignment** for machine tunnels.

            *   **None**: Private Access is disabled and Zscaler Client Connector does not provide users with access to internal applications. Users can only access internal applications if they have another mechanism for reaching internal networks.

        *   **Split VPN-Trusted Network**:
            *   **Tunnel**: For Private Access, the app can forward any traffic not going through Split-Tunnel VPN to Private Access. This condition can require VPN configuration to exclude Private Access infrastructure.

You can enable **Dynamic ZPA Service Edge Assignment**to configure settings for probe interval, probe sample size, and threshold rank:

                *   **Probe Interval (In Seconds)**: The time interval in which an IP address connects with a server. Select an option from the drop-down menu. Options are in 30-second intervals and range from 30 to 600 seconds. The default is 30 seconds.
                *   **Probe Sample Size**: The number of retries required to establish a connection before a failover. Select an option from the drop-down menu. The default is 5 times.
                *   **Threshold Rank**: Ranks the change in latency of the primary and secondary Public Service Edges. Select a rank from 1 to 5 from the drop-down menu. If the value exceeds the threshold limit, the switch is made from the primary to the secondary Service Edge, or vice versa. Rank 1 is a minimum change, which is the most aggressive for switching from the primary to the secondary Service Edge.
                *   **Enable Machine Tunnel**: Select to enable **Dynamic ZPA Service Edge Assignment** for machine tunnels.

            *   **None**: Private Access is disabled, and Zscaler Client Connector does not provide access to internal applications.

If you want On-Trusted Network behavior to apply to VPN-Trusted Network or Off-Trusted Network, select **Same as "On-Trusted Network"**for either environment.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#forwarding-profile-action-zpa)

4.   Click **Save**.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-user-access-logging-controls-zscaler-client-connector -->

Title: Configuring User Access to Logging Controls for Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-user-access-logging-controls-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Configuring User Access to Logging Controls for Zscaler Client Connector

From the [App Supportability](https://help.zscaler.com/zscaler-client-connector/about-app-supportability) page, you can configure your users’ access to the logging controls for Zscaler Client Connector. Your users can access these features from the **More**window of the app and the Zscaler Client Connector icon's shortcut menu from the system tray.

For Zscaler Client Connector 2.1.2 or later for Windows and macOS, exported logs aren't encrypted, but logs included in the **Report an Issue** form are encrypted. If you're using a Zscaler Client Connector version earlier than 2.1.2 for Windows or macOS, exported logs are always encrypted and users can't view them.

[See image.](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-logging-controls-zscaler-client-connector#more-window-tray-icon-image)

## About Logging Controls for Users

You can choose to show or hide logging controls for users. If you keep logging controls visible on Zscaler Client Connector, your users can:

*   See where encrypted logs are stored on their devices by clicking **Show/Hide Logs** in the **Report an Issue** form.

[See image.](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-logging-controls-zscaler-client-connector#encrypted-logs-stored-pt-1)

*   Export logs from the Zscaler Client Connector system tray icon shortcut menu. Upon export, the logs are saved as a ZIP file on the user's device.

[See image.](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-logging-controls-zscaler-client-connector#export-log-option-pt-2)

*   Send encrypted logs to support by email and send a copy of the email to themselves and others. When users request support from the app, an email with encrypted logs is automatically sent to your organization's support admin. If the option is enabled, it's also forwarded to Zscaler Support. An email is also sent to any recipient the user adds to the **CC** field of the **Report an Issue** form. Users cannot view the encrypted logs.
*   Change the log mode in the**More**window of Zscaler Client Connector. Using [App Profiles](https://help.zscaler.com/zscaler-client-connector/about-zscaler-app-profiles), you can determine how the app generates logs. The following log modes are available to users depending on the app profile configuration:

    *   **Error**: Logs only when the app encounters an error and functionality is affected.
    *   **Warn**: Logs when the app is functioning but is encountering potential issues, or when conditions for the Error log mode are met.
    *   **Info**: Logs general app activity, or when conditions for the Warn log mode are met.
    *   **Debug**: Logs all app activity that could assist Zscaler Support in debugging issues, or when conditions for the Info log mode are met.
    *   **Verbose**:Logs when invoked by some external events (e.g., Firebase Push Notification or Mobile Manager flags). This log mode is only available for Zscaler Client Connector version 1.5 and later for Android.

[See image.](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-logging-controls-zscaler-client-connector#change-log-mode-pt-4)

*   **Clear logs** from the **More** window of Zscaler Client Connector.

[See image.](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-logging-controls-zscaler-client-connector#clear-logs-pt-5)

## Configuring User Access to Logging Controls

To configure users' access to logging controls:

1.   In the Zscaler Client Connector Portal, go to **Administration**.
2.   In the left-side navigation, go to**Client Connector Support**.
3.   On the **App Supportability** tab, enable **Hide Logging Controls on Zscaler Client Connector**to block users from using logging controls for Zscaler Client Connector. Disable **Hide Logging Controls on Zscaler Client Connector**to allow users to use logging controls.

[See image.](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-logging-controls-zscaler-client-connector#hide-logging-controls-zscaler-app-switch)

1.   Click **Save**.

To learn more about other Zscaler Client Connector Support features, see [About Zscaler Client Connector Support](https://help.zscaler.com/zscaler-client-connector/about-zscaler-app-support).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store -->

Title: Configuring an App Update in the Zscaler Client Connector App Store | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Configuring an App Update in the Zscaler Client Connector App Store

Zscaler regularly releases new versions of Zscaler Client Connector. As an admin, you can decide what app version is used by your organization by configuring a Zscaler Client Connector app update. You can configure an app update to control which version (if any) is available when the app is automatically updated or when end users manually update the app.

You can configure an app update only for Windows, macOS, and Linux. Updates for iOS and Android are controlled by the App Store and Google Play or your MDM solution.

After your users enroll with Zscaler Client Connector, the app automatically checks every two hours to see if an updated version is available and automatically updates the app if one is available. Alternatively, the user can click **Update App** in the **More** window to check for and install any available updates.

The app update retains any existing installation parameters set on the user devices (e.g., strictEnforcement or policyToken).

[See image.](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#ZCC-App)

You can configure an app update for selected groups that's rolled out at the same time, or configure a phased rollout that gradually upgrades devices for selected groups.

With a phased rollout, admins can test a new build before deploying an update to all users. The admin can pause a phased rollout to address issues or modify the version or schedule. View the phased rollout progress on the Client Connector App Store page.

[See image.](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#Client-Connector-App-Store-Page)

[](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store)[](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store)![Image 2: Client Connector App Store Page Slow Rollout](https://help.zscaler.com/downloads/client-connector-app-store-phased-rollout.png)

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#Client-Connector-App-Store-Page)

The following app update options are available:

*   [Configure an App Update](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#configure-auto-update)

Before you can configure an app update for a release, you must first enable a build.

    1.   [](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store)To enable builds on the New Releases tab:

        1.   Go to **Infrastructure**>**Common Resources**>**Deployment**>**Platform Releases**.
        2.   On the **New Releases** tab, select a platform.
        3.   Click **Enable Build**for the Zscaler Client Connector versions you want to configure for an app update.
        4.   Click **Save**.

[See image.](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#new-releases)

Confirm the versions you enable on the **Registered Devices** tab, not the **New Releases** tab.

    2.   To configure an app update for a release, click **Add App Store Group Policy**on the **Update Settings** tab.

[See image.](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#add-config)

    3.   In the **Add App Store Group Policy** window, select from the following settings:

        1.   From the**User Groups** drop-down menu, select the groups to which you want to apply this configuration.

        *   [Groups are synced](https://help.zscaler.com/zscaler-client-connector/syncing-directory-groups-between-internet-saas-and-zscaler-client-connector) from Internet & SaaS.
        *   If a group is already configured for an app update, that group cannot be added to subsequent app updates until the pending app update is complete.

        1.   In the **Windows - Version to Install**, **macOS - Version to Install**, and **Linux - Version to Install** drop-down menus, select the Zscaler Client Connector version you want automatically updated on your users' devices.

        *   When multiple versions are configured for the same OS, and a user belongs to more than one group in those configurations, the latest version is updated for that user.
        *   If any devices are already upgraded to a later version, you cannot roll out an update for an earlier version.

        1.   For Zscaler Client Connector version 3.7 and later for Windows, enable or disable **Use 64-Bit Installer for Windows**to install the 64-bit build on devices.
        2.   (Optional) Click in the **Apply From Timestamp (in UTC)** field and select a date and time from the calendar to define a time and date for your auto-update to start. If this field is left blank, the app automatically checks for a new update during the next cycle, which is every two hours, and updates automatically.
        3.   
**Roll Out Type**: Select one of the following methods to deploy an app update:

            *   **Phased Rollout**: Enable this option to deploy the selected Zscaler Client Connector versions to the selected groups in a [gradual rollout.](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#table)| Day | Percentage of Devices Upgraded |
| --- | --- |
| 1 | 1 |
| 2 | 2 |
| 3 | 5 |
| 4 | 10 |
| 5 | 20 |
| 6 | 50 |
| 7 | 100 | 
[Close](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#table)  The rollout process begins according to the scheduled date and is completed within 7 days.
            *   **Mass Rollout**: Enable this option to deploy the selected Zscaler Client Connector versions to the selected groups at the same time.

To pause a phased rollout, click the **Pause**(![Image 3](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/configuring-app-update-zscaler-client-connector-app-store-doc-45501-doc-49543/Pause-Client-Connector.png))icon. To resume the rollout, click the **Play** (![Image 4](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/configuring-app-update-zscaler-client-connector-app-store-doc-45501-doc-49543/Play-Client-Connector.png)) icon. The phased rollout resumes from the point where you paused it.

        4.   Click **Save**.

[See image.](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#add-config2)

        5.   In the **Group Based Version Rollout - Confirmation** window, click **Proceed**.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#configure-auto-update)

*   [Edit an App Update](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#edit-rollout)[](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store)You can edit an app update, including a phased rollout that's in progress.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#edit-rollout) 
*   [No App Update](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#no-update)

[](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store)When you disable a Zscaler Client Connector update, Zscaler Client Connector never automatically updates on your users' devices. Your organization takes full responsibility for updating the app using its own mechanism (for example, using a GPO policy to do a fresh install of a desired version).

To disable the Zscaler Client Connector app update option:

    1.   Go to **Infrastructure**>**Common Resources**>**Deployment**>**Platform Releases**.
    2.   Select the **Update Settings** tab, and click **Add App Store Group Policy**.

[See image.](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#disable)

    3.   Select from the following settings in the **Add App Store Group Policy** window:
        1.   From the**User Groups** drop-down menu, select the groups to which you want to apply this configuration.

            *   [Groups are synced](https://help.zscaler.com/zscaler-client-connector/syncing-directory-groups-between-internet-saas-and-zscaler-client-connector) from Internet & SaaS.
            *   If a group is already configured for an app update, that group cannot be added to subsequent app updates until the pending app update is complete.

        2.   In the **Windows - Version to Install**, **macOS - Version to Install**, and **Linux - Version to Install** drop-down menus, select **Disable**.

    4.   Click **Save**.

[See image.](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#add-config3)

    5.   In the **Group Based Version Rollout - Confirmation** window, click **Proceed**.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#no-update)

*   [Edit the Default Policy](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#edit-grp-policy)

[](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store)You can edit the default app store group policy, which allows you to specify a Zscaler Client Connector app update option per OS. The default app store group policy applies to all groups that aren’t explicitly specified in your group policies.

To edit the default app store group policy:

    1.   Go to **Infrastructure**>**Common Resources**>**Deployment**>**Platform Releases**.
    2.   Select the **Update Settings** tab, locate the group policy named **ALL**, and then click the **Edit****(**![Image 5](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/configuring-app-update-zscaler-client-connector-app-store-doc-45501-doc-49543/pencil-icon.png)) icon.

[See image.](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#default-policy)

    3.   In the **Windows - Version to Install**, **macOS - Version to Install**, and **Linux - Version to Install**drop-down menus, select one of the following options:
        *   **Latest**: Zscaler Client Connector automatically updates to the latest version.
        *   **Disable**: Zscaler Client Connector never updates on its own. Your organization is responsible for updating the app.
        *   **Version to Install**: The version to which you want Zscaler Client Connector to automatically update.

    4.   For Zscaler Client Connector version 3.7 and later for Windows, enable **Use 64-Bit Installer for Windows** to install the 64-bit build on devices.
    5.   (Optional) Click in the **Apply From Timestamp (in UTC)** field and select a date and time from the calendar to define a time and date for your auto-update to start. If this field is left blank, the app automatically checks for a new update during the next cycle, which is every two hours, and updates automatically.
    6.   Click **Save**.

[See image.](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#add-config4)

    7.   In the **Group Based Version Rollout - Confirmation** window, click **Proceed**.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#edit-grp-policy)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi -->

Title: Customizing Zscaler Client Connector with Install Options for MSI | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Customizing Zscaler Client Connector with Install Options for MSI

You can use the MSI file to manually install Zscaler Client Connector on a device or if you're deploying the app to your users using GPO, SCCM, or other device management methods that support MSI files. After downloading the Zscaler Client Connector MSI installer file in the [Zscaler Client Connector Portal](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-store), you can deploy the file as is with your device management method. If you want to customize the MSI file and add install options (for example, to enable strict enforcement), you must first create an MST file.

You can also add to the file install options to customize the app for your organization using one of the following methods:

*   [Creating an MST and Deploying It Using GPO or a Compatible Device Management Tool](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi#CreateZAppMSTFile)

[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi)Orca.exe is available in the [Microsoft Windows Software Development Kit (SDK)](https://developer.microsoft.com/en-us/windows/downloads/windows-10-sdk). To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/windows/win32/msi/orca-exe).

Windows installers use an MST file to enable customized installations of an original MSI package during installation. You can create an MST file and then deploy it to users using a Group Policy Object (GPO) or a compatible device management tool such as Microsoft Intune or Jamf Pro.

To create an MST file using Orca:

Open Orca and go to **File**>**Open**.

    1.   Locate and double-click the MSI file.

![Image 2: Clicking the New Transform option to create a MST file for Zscaler Client Connector](https://help.zscaler.com/downloads/z-app/downloading-deployment/customizing-zscaler-app-install-options-msi/Zscaler-App-MSI-New-Transform.png)

    1.   Go to**Transform**>**New Transform**.

![Image 3: The Zscaler Client Connector MSI file properties](https://help.zscaler.com/downloads/z-app/downloading-deployment/customizing-zscaler-app-install-options-msi/Zscaler-App-MSI-Property.png)

    1.   In the **Tables** column, click **Property**.
    2.   Add [Zscaler configuration parameters](https://help.zscaler.com/zscaler-client-connector/supported-parameters-zscaler-client-connector-windows) and their corresponding values per your organization's needs.

To modify any of these parameters post-installation, app reinstallation with the new values is required.

    3.   To save your changes after adding the options you want, go to **Transform**>**Generate Transform.**

![Image 4: The Generate Transform option to create a MST file for Zscaler Client Connector](https://help.zscaler.com/downloads/z-app/downloading-deployment/customizing-zscaler-app-install-options-msi/Zscaler-App-MSI-Generate-Transform.png)

    1.   In the **Save Transform As** window, enter a file name and click **Save**.

![Image 5: Saving the Zscaler Client Connector MST file](https://help.zscaler.com/downloads/z-app/downloading-deployment/customizing-zscaler-app-install-options-msi/Zscaler-App-MSI-Saving.png?1602182163)

After creating the MST, you can use it when [deploying Zscaler Client Connector to your users with Active Directory](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-app-active-directory-windows).

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi#CreateZAppMSTFile)

*   [Running the MSI with CLI Options](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi#RunZAppMSICmdLine)

[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi)[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi)Zscaler recommends using the MST file to install Zscaler Client Connector with custom options. However, if you have a device management tool that does not support MST (e.g., SCCM or PSEXEC) or you are manually installing the MSI file, you can run the MSI file using the CLI and add the options needed.

To run the MSI file using CLI options:

    1.   Start a command prompt as an administrator:
        1.   Click **Start**.
        2.   In the **Start Search** box, enter cmd, then press `CTRL+SHIFT+ENTER`.
        3.   If the User Account Control (UAC) window appears, confirm that you want to continue.

![Image 6: Running the command prompt as an administrator to run the Zscaler Client Connector MSI file](https://help.zscaler.com/downloads/zia/documentation-knowledgebase/forwarding-your-traffic/zscaler-app/downloading-and-deploying-zscaler-app/customizing-zscaler-app-install-options-msi/5154fdeb-9591-4089-8262-a3e692a4c799_display.png)

    1.   Enter the following command:

`msiexec /i "<complete path>" /quiet <install options>`
    *   Replace `<complete path>` with the absolute pathname to the MSI install file. For example, `C:\Users\User\Downloads\Zscaler-windows-1.0.2.000018-installer.msi`
    *   Use the /`quiet`switch to install the app in silent mode.
    *   Replace <install options> with one or more of the [Zscaler configuration parameters.](https://help.zscaler.com/zscaler-client-connector/supported-parameters-zscaler-client-connector-windows)

To modify any of these parameters post-installation, app reinstallation with the new values is required.

The following image is an example of a CLI where:

    *   The absolute path to the MSI file is `C:\Users\User\Downloads\Zscaler-windows-1.2.0.000311-installer.msi`.
    *   The `/quiet` switch is used to install the app in silent mode.
    *   The cloud on which the organization is provisioned is `zscalertwo`.
    *   The device token value is `4e36647447326e5a553335303232416e6279784b51513d3d`.
    *   The policy token value is `32343A343A312E31204D6967726174696F6E`.
    *   The organization's domain name is `safemarch.com`.
    *   The UNAME is `test`.
    *   The EXTERNALDEVICEID is `TestDevice`.
    *   ANTITAMPERING is `1`.

![Image 7: Running the Zscaler Client Connector MSI file with a command line](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/customizing-zscaler-client-connector-install-options-msi-doc-39855/MSI-install-options-example.png)

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi#RunZAppMSICmdLine)

*   [Deploying Zscaler Client Connector with Non-Persistent Citrix VDIs](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi#InstallVDI)

Zscaler Client Connector only supports a dedicated, single-user virtual desktop infrastructure (VDI) model. For multi-session VDIs, [Zscaler Client Connector for VDI](https://help.zscaler.com/cloud-branch-connector/what-zscaler-client-connector-vdi) is required.

Follow these best practices when using Zscaler Client Connector in a VDI:

    *   Zscaler recommends that you don't log in to Zscaler Client Connector on the master VM.
    *   To use the STRICTENFORCEMENT install option, you must have the HIDEAPPUIONLAUNCH install option disabled. This allows Zscaler Client Connector to remind users to enroll with Zscaler Client Connector before accessing the internet.
    *   To use the USERDOMAIN install option, you must use Integrated Windows Authentication (IWA).

## Behavior of Install Parameters for VDI

The install parameters for VDI behave in the following manner:

    *   `VDI=1` : Configures the Zscaler Client Connector to store configuration data in the `%USERPROFILE%\AppData\Roaming\Zscaler` user profile store so it is roamed using a profile management tool instead of the default `%ProgramData%\Zscaler`. This allows admins to restore Zscaler's configuration as part of users' profiles using tools such as Citrix UPM as described in the [Installing Zscaler Client Connector on the Master VM](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi#install-vm) section.
    *   `CONFIGTIMEOUT=300` : If the Zscaler Client Connector configuration data is not found, Zscaler Client Connector waits for the specified interval for configuration data to be restored. In environments with roaming profiles such as non-persistent VDIs, it might take additional time for the profile management solution to restore the Zscaler Client Connector configuration data to the user profile.
    *   `INSTALLLWFDRIVER=1` : Install the lightweight filter driver used for traffic interception during installation of the Zscaler Client Connector. Without this parameter, Zscaler Client Connector doesn't install the LWF driver until configured to do so by a forwarding profile during policy download, which might cause network connection disruption and VDI disconnection.
    *   `LWFBOOTSTART=1` : Configures the LWF driver to load at system boot instead of the default at system startup. This prevents a blue screen during OS boot in environments where the OS is booted over the network, such as in Citrix Provisioning Services.

## [](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi)Installing Zscaler Client Connector on the Master VM

Install Zscaler Client Connector on the master VM using the following steps:

    1.   Configure Citrix UPM to backup and restore to the following folder: `{UserProfileFolder}\AppData\Roaming\Zscaler`
    2.   Run your MSI installer: msiexec/i ZCC_installer.msi `USERDOMAIN=`<AD domain>`CLOUDNAME=`<cloudname>`VDI=1``CONFIGTIMEOUT=300``INSTALLLWFDRIVER=1``LWFBOOTSTART=1`

The parameters listed earlier are samples for a VDI installation Zscaler Client Connector. Consult the installation parameters for Zscaler Client Connector to remove/add any options as necessary for the deployment requirements. For example, to support seamless SSO authentication with IWA, adding `USERDOMAIN` and `CLOUDNAME` parameters might be necessary. If the VDI deployment does not use a profile management solution, it's not necessary to apply the `CONFIGTIMEOUT` parameter. If you are using Citrix Provisioning Server, you must use the `LWFBOOTSTART` option.

For a complete list of the installation parameters, refer to [Running the MSI with CLI Options](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi#running-MSI-CLI-options).

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi#InstallVDI)

