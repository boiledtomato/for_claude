# Zscaler Help — ZCC — Zscaler Client Connector (part 2)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-08-03 02:47 UTC
Articles in this file: 52

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/understanding-zscaler-client-connector-dashboard","lastmod":"2026-05-18T07:06Z","nid":"1296751"} -->
## Understanding the Zscaler Client Connector Dashboard

- Source: https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard
- Product: Client Connector
- Path: Zscaler Client Connector Help > Monitoring Usage > Understanding the Zscaler Client Connector Dashboard
- Last modified: 2026-05-18T07:06Z
- Summary: Information on the Zscaler Client Connector Portal Dashboard and its widgets, which display information about enrolled devices.

The Zscaler Client Connector Dashboard provides information [about enrolled devices](https://help.zscaler.com/zscaler-client-connector/about-enrolled-devices) for your organization, including information about service status.

To view the dashboard, click **Analytics**, enable the **Switch to Existing Reports** toggle, and then go to **Connectors > Dashboard**.

## Dashboard

The Dashboard option in the left-side navigation covers the following information:

[Image: Zscaler Client Connector Dashboard]

- Platform Details
- Device Posture
- Device Events

This page provides OS platform details such as device model and device state.

### Dashboard Filters

Use the following filters to modify the content shown in the Platform Details page widgets. After you select filters, click **Apply** in the upper-right corner of the page.

- **States**: View data for devices with all device states, or a specific state (i.e., **Registered**, **Unregistered**, **Removal Pending**, **Removed**, **Quarantined**).
- **OS**: View data for all operating systems or a specific operating system.

### Widgets

The Platform Details page provides the following details:

- Version Distribution
- Device Model
- Device OS
- Device State
- Enrolled Devices by Platform
- License Usage

[Image: Zscaler Client Connector Dashboard]

This widget displays your organization's subscription and license information.

This widget only applies to Internet & SaaS. You won't see this widget if you only have Private Access.

[Image: Zscaler Client Connector Dashboard - License Usage chart]

- Hover over the chart to view the following: Licenses are calculated based on the unique username. For example, even if a user has Zscaler Client Connector installed on three devices, the user still has only one license.
  - **Subscribed**: The number of user subscriptions for Zscaler Client Connector.
  - **Used**: The number of users that are enrolled to Zscaler Client Connector.
- To view a list of all enrolled devices, click a category in the chart.

This widget displays the enrolled device models. It displays the top 5 device models and groups all other models in the Others category.

[Image: Zscaler Client Connector Dashboard - Device Model chart]

- Hover over the chart to view the following:
  - The details for a specific device model, which includes:
    - The model name
    - The number of enrolled devices for the model
    - The percentage of enrolled devices for the model
  - The details for the Others category, which includes:
    - The number of enrolled devices grouped under Others
    - The percentage of enrolled devices grouped under Others
- To view a list in **Enrolled Devices**for all devices for a specific model, click the model in the chart.
- To view a list in **Enrolled Devices** for all devices in the Others category, click **Others**in the chart. The list of devices is organized by device model.

This widget displays the enrolled devices operating systems. It displays the top 5 OSs and groups all other OSs in the Others category.

[Image: Zscaler Client Connector Dashboard - Device OS chart]

- Hover over the chart to view the following:
  - The details for a specific OS, which includes:
    - The OS name
    - The number of enrolled devices for the OS
    - The percentage of enrolled devices for the OS
  - The details for the Other category, which includes:
    - The number of enrolled devices grouped under Others
    - The percentage of enrolled devices grouped under Others
- To view a list in **Enrolled Devices** for all devices for a specific OS, click the OS in the chart.
- To view a list in **Enrolled Devices** for all devices in the Others category, click **Others**in the chart. The list of devices is organized by OS.

This widget displays the states of the enrolled devices and the [app profiles](https://help.zscaler.com/zscaler-client-connector/about-zscaler-app-profiles) applied to the devices.

The possible device states are:

- Registered
- Unregistered
- Removal Pending
- Removed
- Quarantined

To learn more about device states, see [Device States for Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/device-states-enrolled-devices).

[Image: Zscaler Client Connector Dashboard - Device State chart]

- Hover over the chart to view the following:
  - The state name
  - The number of enrolled devices for the state
  - The percentage of enrolled devices for the state
- To view a list in **Enrolled Devices** for all devices for a specific state, click the state in the chart.

This widget displays the enrolled devices platforms.

[Image: Zscaler Client Connector Dashboard - Enrolled Devices by Platform chart]

- Hover over the chart to view the following:
  - The platform name
  - The number of enrolled devices for the platform
- To view a list in **Enrolled Devices** for all devices for a specific platform, click the platform in the chart.

This widget displays the number of devices installed with supported and unsupported versions of Zscaler Client Connector, defined as:

- **Supported**: Zscaler Client Connector versions enabled on the [New Releases tab](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-store) of the Client Connector App Store and listed on the [Update Settings tab](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-store) of the Client Connector App Store.
- **Unsupported**: Zscaler Client Connector versions enabled on the [New Releases tab](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-store) of the Client Connector App Store but are earlier than the supported versions listed on the [Update Settings tab](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-store) of the Client Connector App Store.

[Image: Zscaler Client Connector Dashboard - Version Distribution chart]

Hover over the chart to view the number of devices installed with supported and unsupported versions of Zscaler Client Connector, shown by percentage for each OS. Click the graph to view a list of all devices, by User ID, installed with either supported or unsupported versions of Zscaler Client Connector in [Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/about-enrolled-devices). You can use this list to view which Zscaler Client Connector versions are installed and identify users who must update to a supported version of Zscaler Client Connector.

This page provides information about Internet & SaaS and Private Access service status and about users turning off the services via passwords.

This page is available for Windows users only. You must also have Zscaler Digital Experience (ZDX) or Zscaler Client Connector Telemetry enabled.

### Dashboard Filter

Use the **Time Range** filter to view data for the selected time range. After you select the filter, click**Apply** in the upper-right corner of the page.

### Widgets

The Device Events page provides the following widgets:

- ZIA and ZPA Device Service Status
- ZIA Service Turn Off
- ZPA Service Turn Off
- Number of Times ZIA and ZPA Turned Off
- Password Usage
- Password Usage By Type

[Image: View the Device Events page of the Dashboard]

This widget displays information about the total number of devices enrolled in each service. You can view the following:

- **ZDX**: The number of enrolled devices with the ZDX service enabled.
- **Non-ZDX**: The number of enrolled devices without the ZDX service enabled.
- **ZIA**: The number of enrolled devices with the Internet & SaaS service enabled.
- **ZPA**: The number of enrolled devices with the Private Access service enabled.
- **Off ZIA**: The number of enrolled devices that have had the Internet & SaaS service turned off
- **Off ZPA**: The number of enrolled devices that have had the Private Access service turned off.

This widget displays information about the enrolled devices that have had the Internet & SaaS service turned off. You can view the following:

- **Users**: The number of unique users who have turned off the service, even if they have turned it back on.
- **Total Number**: The total number of times the service has been turned off by the users, even if a user has turned it back on.

This widget displays information about the enrolled devices that have had the Private Access service turned off. You can view the following:

- **Users**: The number of unique users who have turned off the service, even if they have turned it back on.
- **Total Number**: The total number of times the service has been turned off by the users, even if a user has turned it back on.

This widget displays information about the number of times Internet & SaaS and Private Access were turned off over the course of the past 24 hours. Hover over the graph lines to display the number of devices turned off per service for a particular time.

This widget displays information about the passwords used to turn off the services. You can view the following:

- **Users**: The number of individual users who have turned off the service.
- **OTP**: The number of users who used the Disable ZIA OTP password or Disable ZPA OTP password from the [Device Details](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device) for the enrolled device.
- **Master**: The number of users who used the Disable Password ZIA password or Disable Password ZPA password from the [app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).

This widget displays information about the passwords used to turn off the services. You can view the following:

- **ZIA OTP**: The number of times ZIA was turned off using the Disable ZIA OTP password.
- **ZPA OTP**:The number of times ZPA was turned off using the Disable ZPA OTP password.
- **ZPA Master**: The number of times ZPA was turned off using the Disable Password ZPA password.
- **ZIA Master**: The number of times ZIA was turned off using the Disable Password ZIA password.

This page provides information about devices that fail their [device posture checks](https://help.zscaler.com/zscaler-client-connector/about-device-posture-profiles).

The Device Posture page is available only if you have Zscaler Digital Experience (ZDX) or Zscaler Client Connector Telemetry enabled.

### Dashboard Filters

Use the following filters to modify the content shown in the Device Posture page widgets. After you select filters, click **Apply** in the upper-right corner of the page.

- **Time**: Modify the failed postures times ranging from three-hour intervals up to 24 hours. You can set the filter to show data up to the past 7 days.
- **OS**: View devices for either Windows or macOS or for both of the operating systems. By default, both operating systems are shown.

### Widgets

The Device Posture page provides the following widgets:

- Top 10 Failed Device Posture Trend
- Top 50 Failed Posture Profiles
- Top 10 Non-Compliant Devices
- Compliant vs Non-Compliant

This widget displays the trendline of the top 10 device posture profiles that have the highest number of devices failing their device posture checks. You can select a different set of up to 10 device posture profiles in the Top 50 Failed Posture Profiles widget to view the trendline for those profiles.

This widget displays the top 50 device posture profiles that have the highest number of devices failing their posture checks. The trendline for the selected device posture profiles displays in the Top 10 Failed Device Posture Trend widget. You can click a device posture profile to view the failed devices on the [Failed Devices](https://help.zscaler.com/zscaler-client-connector/about-failed-posture-devices) page.

This widget displays the total number of non-compliant devices for the top 10 device posture failures and the distribution of the devices by device posture profile. Hover over the chart to view the number of devices with posture failures for the specific device posture profile.

This widget displays the number of devices with the top 10 failed device posture profiles that are compliant (passed the posture check) and non-compliant (failed the posture check). For the non-compliant devices, the widget displays the total number of posture failures and the upwards or downwards trend for each device posture profile.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/uninstalling-zscaler-client-connector","lastmod":"2025-10-06T13:45Z","nid":"1285486"} -->
## Uninstalling Zscaler Client Connector

- Source: https://help.zscaler.com/zscaler-client-connector/uninstalling-zscaler-client-connector
- Product: Client Connector
- Path: Zscaler Client Connector Help > Downloading & Deployment > Uninstalling Zscaler Client Connector
- Last modified: 2025-10-06T13:45Z
- Summary: Information on various methods of uninstalling Zscaler Client Connector from a device.

This article is for Zscaler Client Connector admin use only. If you are not a Zscaler Client Connector admin, contact your organization's support team about uninstalling Zscaler Client Connector.

This article provides instructions for uninstalling Zscaler Client Connector. When [configuring the app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-profiles), you have the option of providing an **Uninstall Password** that users must enter to uninstall the app. You can also utilize a single-use Uninstall One-Time Password (OTP) in [Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device).

You can manually uninstall the app from individual devices on each of the following platforms:

- Manually Uninstall Zscaler Client Connector in Windows
- Manually Uninstall Zscaler Client Connector in macOS
- Manually Uninstall Zscaler Client Connector in Linux
- Manually Uninstall Zscaler Client Connector in Android
- Manually Uninstall Zscaler Client Connector in Android on ChromeOS
- Manually Uninstall Zscaler Client Connector in iOS

To uninstall Zscaler Client Connector:

1. On the device, go to **Zscaler >** **ZSAInstaller**.
2. Double-click the **uninstall**application. [Image: The Zscaler Client Connector uninstall application for Windows]
3. Provide the **Uninstall Password** as [configured in the app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).

To uninstall Zscaler Client Connector:

1. Go to the Zscaler folder on the device.
2. Double-click **UninstallApplication**. [Image: The Zscaler Client Connector uninstall application for macOS]
3. Provide the **Uninstall Password** as [configured in the app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).

To uninstall Zscaler Client Connector:

1. Run the following command: sudo /opt/zscaler/UninstallApplication
2. Provide the **Uninstall Password** as [configured in the app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).

To uninstall Zscaler Client Connector:

1. On the device, tap and hold the Zscaler Client Connector icon on the home screen.
2. Drag the Zscaler Client Connector icon to the top of the home screen to the **Uninstall** icon.
3. Click **OK** in the message that displays.

[Image: Uninstalling the Zscaler Client Connector app from the Andriod home screen]

A message appears at the bottom of the home screen indicating that the app was uninstalled.

To uninstall Zscaler Client Connector:

1. Locate Zscaler Client Connector on the device.
2. Touch and hold Zscaler Client Connector, and then tap the **Uninstall**icon ([Image: The Uninstall icon for iOS]).
3. Tap **Delete**. [Image: Uninstalling the Zscaler Client Connector on iOS]
4. For iPhone X and later, tap **Done**. For iPhone 8 or earlier, tap the home button.

To uninstall Zscaler Client Connector:

1. On the device, right-click the Zscaler Client Connector icon and select the **Uninstall**icon.
2. Click **Uninstall** in the message that displays.

[Image: Uninstalling the Zscaler Client Connector app from Chromebook]

The icon and app are removed from the device.

You can also uninstall the app from your users' devices using one of the following options:

- Uninstall from the command-line using the MSI file
- Uninstall by removing the MST file from GPO
- Uninstall in macOS with a shell script
- Uninstall in Windows with a batch file
- Uninstall in Windows with PowerShell

If you are a Zscaler Client Connector admin and are unable to uninstall Zscaler Client Connector using the methods provided here, contact Zscaler Support.

To uninstall Zscaler Client Connector from your user's device:

1. Start a command prompt as an administrator.
2. Enter the following command: msiexec /x <complete path> /quiet UNINSTALLPASSWORD=<uninstall password>[Image: Running the MSI file with a command-line option to uninstall Zscaler Client Connector]
  - Replace <complete path> with the absolute pathname to the MSI install file (e.g., C:\Users\User\Downloads\Zscaler-windows-1.1.0.000213-installer.msi).
  - Use the /quiet switch to uninstall the app in silent mode.
  - Replace <uninstall password> with the password that was [configured in the app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-profiles). In this example, the organization's password is safemarch123.
  - If you [configured a password for access in unattended mode](https://help.zscaler.com/zscaler-client-connector/configuring-passwords-access-unattended-mode), replace `UNINSTALLPASSWORD` with `UNINSTALLPASSWORDCMDLINE`, and replace `<uninstall password>` with the uninstall **Password** that was generated in the platform settings.

Ensure that the [password you added to the MST file](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-app-install-options-msi#uninstallpw) is the same as the **Uninstall Password** that was [configured in the app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-profiles) or, if you [configured a password for access in unattended mode](https://help.zscaler.com/zscaler-client-connector/configuring-passwords-access-unattended-mode), the uninstall **Password** that was generated in the platform settings.

To remove the MST file from your users' devices in an Active Directory (AD) environment:

1. Log in to the AD environment (Domain Controller) as an admin user.
2. For your OU, right-click the GPO Policy and select **Edit**.
3. Go to **User Configuration**>**Policies**>**Software Settings**>**Software installation**.
4. Right-click the MST file, then select **All Tasks**> **Remove...**. [Image: Removing the MST file from GPO to uninstall Zscaler Client Connector]

After the file is removed, the app is uninstalled from your users' devices.

To uninstall Zscaler Client Connector from your user’s macOS device:

1. Go to the **Utilities** folder.
2. Double-click the **Terminal** icon.
3. Enter the following command: sudo sh /Applications/Zscaler/.Uninstaller.sh <uninstall password>Replace <uninstall password> with the password that was [configured in the app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-profiles). In this example, the organization's password is safemarch123. [Image: Using a shell script to uninstall the Zscaler Client Connector from macOS devices]

To uninstall Zscaler Client Connector from your users’ Windows device:

1. Create a `.bat` file with the following batch script: @ECHO OFF SET ZSCALER_PASSWORD=<uninstall password> IF EXIST ""%PROGRAMFILES(X86)%"\Zscaler\ZSAInstaller\uninstall.exe" ( "%PROGRAMFILES(X86)%"\Zscaler\ZSAInstaller\uninstall.exe --mode unattended ) ELSE ( "%PROGRAMFILES%"\Zscaler\ZSAInstaller\uninstall.exe --mode unattended )Replace <uninstall password> with the password that was [configured in the app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles). In this example, the organization’s password is safemarch123. If you [configured a password for access in unattended mode](https://help.zscaler.com/zscaler-client-connector/configuring-passwords-access-unattended-mode), replace `ZSCALER_PASSWORD` with `ZSCALER_UNINSTALL_PASSWORD_JWT`, and replace <uninstall password> with the uninstall **Password** that was generated in the platform settings.
2. Start a command prompt as an administrator.
3. To run the batch file, enter its absolute path. In this example, the batch file is Uninstall-Z-App.bat and its absolute path is C:\Users\Administrator\Desktop\batch\Uninstall-Z-App.bat [Image: Running the batch file to uninstall the Zscaler Client Connector]

If you are still running into issues with batch file, consider using Windows PowerShell as an alternative to uninstalling Zscaler Client Connector. For example, if you are using complex passwords that include a combination of numbers, letters, and special characters (e.g., 123jsf%2!!@&jaK), then Windows PowerShell is more suitable to use.

To uninstall Zscaler Client Connector via PowerShell from your user's device:

1. Go to PowerShell.
2. Enter the following command to ensure `ExecutionPolicy` is not restricted: Set-ExecutionPolicy RemoteSigned
3. You can enter the commands individually or create a PowerShell script to start the uninstallation process.
  - To enter the commands individually
  - To create a PowerShell script in NotePad
4. After uninstallation, enter the following to reset the password: [Environment]::SetEnvironmentVariable('ZSCALER_PASSWORD','', 'User')

1. To set the variable to your <uninstall password>, enter the following: [Environment]::SetEnvironmentVariable('ZSCALER_PASSWORD','<uninstall password>','User')Replace `<uninstall password>` with the password that was [configured in the app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles). If you [configured a password for access in unattended mode](https://help.zscaler.com/zscaler-client-connector/configuring-passwords-access-unattended-mode), replace `ZSCALER_PASSWORD` with `ZSCALER_UNINSTALL_PASSWORD_JWT`, and replace <uninstall password> with the uninstall **Password** that was generated in the platform settings.
2. To confirm the variable is set correctly, enter the following: [Environment]::GetEnvironmentVariable('ZSCALER_PASSWORD', 'User')The `<uninstall password>` is returned.
3. To start the uninstall process, enter the following. Start-Process -FilePath "C:\Program Files\Zscaler\ZSAInstaller\uninstall.exe"--mode unattended"You might need to change the file path if it is incorrect. Check where your `Zscaler` folder is located to find the file path to `uninstall.exe`.

1. In NotePad, create a PowerShell script with the following script. [Environment]::SetEnvironmentVariable('ZSCALER_PASSWORD','<uninstall password>','User') [Environment]::GetEnvironmentVariable('ZSCALER_PASSWORD', 'User') Start-Process FilePath "C:\Program Files\Zscaler\ZSAInstaller\uninstall.exe" -ArgumentList "--mode unattended"Replace `<uninstall password>` with the password that was [configured in the app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles). If you [configured a password for access in unattended mode](https://help.zscaler.com/zscaler-client-connector/configuring-passwords-access-unattended-mode), replace `ZSCALER_PASSWORD` with `ZSCALER_UNINSTALL_PASSWORD_JWT`, and replace <uninstall password> with the uninstall **Password** that was generated in the platform settings. You might need to change the file path if it is incorrect. Check where your `Zscaler` folder is located to find the file path to `uninstall.exe`.
2. Save it as `test.ps1`.
3. Return to PowerShell to enter the following to start uninstallation. .\test.ps1
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/upgrading-android-1.9","lastmod":"2024-10-23T03:51Z","nid":"1390666"} -->
## Upgrading to Zscaler Client Connector version 1.9 for Android

- Source: https://help.zscaler.com/zscaler-client-connector/upgrading-android-1.9
- Product: Client Connector
- Path: Zscaler Client Connector Help > Troubleshooting > Upgrading to Zscaler Client Connector version 1.9 for Android
- Last modified: 2024-10-23T03:51Z
- Summary: Steps to take when upgrading to Zscaler Client Connector version 1.9 for Android.

If you are currently running a 32-bit version, you must first remove the current version of Zscaler Client Connector (version 1.7.2 or earlier) before installing Zscaler Client Connector version 1.9 for Android on your device.

To learn more, [see March 20, 2022 Release Notes: Zscaler Client Connector 1.9 for Android](https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2022?applicable_category=Android&applicable_version=1.9&deployment_date=2022-04-20&id=1394271).

If you already installed Zscaler Client Connector version 1.9 for Android on your device, but you are currently using Zscaler Client Connector version 1.7.2 or a version that did not originate from the Google Play Store, only the current version will run. You must remove the current version to run Zscaler Client Connector version 1.9 for Android.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/upgrading-android-10","lastmod":"2024-10-23T03:55Z","nid":"1352181"} -->
## Upgrading to Android 10

- Source: https://help.zscaler.com/zscaler-client-connector/upgrading-android-10
- Product: Client Connector
- Path: Zscaler Client Connector Help > Troubleshooting > Upgrading to Android 10
- Last modified: 2024-10-23T03:55Z
- Summary: Information on what steps to take for Zscaler Client Connector when upgrading to Android 10.

If you want to upgrade to Android 10, you must:

- Upgrade your devices (excluding Samsung devices) to Zscaler Client Connector 1.5.2 for Android. To learn more, see [January 6, 2020 Release Notes: Zscaler Client Connector 1.5.2 for Android](https://help.zscaler.com/zscaler-client-connector/january-6-2020-release-notes-zscaler-app-1.5.2-android).
- Upgrade your Samsung devices to Zscaler Client Connector 1.5.3 for Android. To learn more, see [March 17, 2020 Release Notes: Zscaler Client Connector 1.5.3 for Android](https://help.zscaler.com/zscaler-client-connector/march-17-2020-release-notes-zscaler-app-1.5.3-android).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/upgrading-macos-big-sur","lastmod":"2021-03-24T12:11Z","nid":"1370126"} -->
## Upgrading to macOS Big Sur

- Source: https://help.zscaler.com/zscaler-client-connector/upgrading-macos-big-sur
- Product: Client Connector
- Path: Zscaler Client Connector Help > Troubleshooting > Upgrading to macOS Big Sur
- Last modified: 2021-03-24T12:11Z
- Summary: Information on upgrading to macOS Big Sur.

If you want to upgrade to macOS Big Sur, you must first upgrade Zscaler Client Connector to [version 2.2.4](https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2020?applicable_category=macOS&applicable_version=2.2.4) or later.

Also, on macOS Big Sur devices, Apple requires password confirmation for the user's account before Zscaler Client Connector can install and trust the root certificate. As a workaround, certificates can be installed via MDM for trust.

To learn more, see the Security section of the [macOS Big Sur release notes](https://developer.apple.com/documentation/macos-release-notes/macos-big-sur-11_0_1-release-notes).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/upgrading-macos-catalina","lastmod":"2023-11-28T15:30Z","nid":"1352046"} -->
## Upgrading to macOS Catalina

- Source: https://help.zscaler.com/zscaler-client-connector/upgrading-macos-catalina
- Product: Client Connector
- Path: Zscaler Client Connector Help > Troubleshooting > Upgrading to macOS Catalina
- Last modified: 2023-11-28T15:30Z
- Summary: Information on what steps to take for Zscaler Client Connector when upgrading to macOS Catalina.

Zscaler Client Connector 2.1 and Catalina are no longer supported. To learn more, see [Supported Versions](https://help.zscaler.com/eos-eol/supported-versions).

If you want to upgrade to macOS Catalina, you must use Zscaler Client Connector 2.1 or later. To learn more, see [(General Availability) Zscaler Client Connector 2.1.0.190 for macOS](https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2020?applicable_category=macOS&applicable_version=2.1.0.190).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/upgrading-windows-10-version-2004","lastmod":"2023-02-07T14:06Z","nid":"1356096"} -->
## Upgrading to Windows 10, Version 2004

- Source: https://help.zscaler.com/zscaler-client-connector/upgrading-windows-10-version-2004
- Product: Client Connector
- Path: Zscaler Client Connector Help > Troubleshooting > Upgrading to Windows 10, Version 2004
- Last modified: 2023-02-07T14:06Z
- Summary: Information on the limitation that Windows 10, version 2004 introduced, which affects organizations using Zscaler Client Connector for Zscaler Private Access (ZPA).

If you are using Zscaler Client Connector for Zscaler Private Access (ZPA), be aware of the new limitation that Windows 10 introduced in version 2004 and later. This new version of Windows only supports up to 50 DNS suffixes per system. So, when you configure DNS search domains in the ZPA Admin Portal, you must ensure that no system will exceed 50 DNS suffixes. To learn more, see [Adding DNS Search Domains](https://help.zscaler.com/zpa/about-applications/dnsDomains).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/upgrading-zscaler-client-connector","lastmod":"2026-05-07T16:11Z","nid":"1472721"} -->
## Upgrading Zscaler Client Connector

- Source: https://help.zscaler.com/zscaler-client-connector/upgrading-zscaler-client-connector
- Product: Client Connector
- Path: Zscaler Client Connector Help > Downloading & Deployment > Upgrading Zscaler Client Connector
- Last modified: 2026-05-07T16:11Z
- Summary: How to upgrade Zscaler Client Connector using a command line

This feature applies only to Zscaler Client Connector version 4.2.1 for Windows or later.

You can require an upgrade password if you update Zscaler Client Connector in unattended mode on your users' devices (e.g., you use GPO, SCCM, or other device management methods).

You can [configure update settings](https://help.zscaler.com/zscaler-client-connector/configuring-update-settings-zscaler-client-connector) to update Zscaler Client Connector automatically to the latest version or to a version you specify. If you disable the auto-update option, you can use this password to provide additional security.

Before you can upgrade using the password, you need to configure it. To learn more, see [Configuring Passwords for Access in Unattended Mode.](https://help.zscaler.com/zscaler-client-connector/configuring-passwords-access-unattended-mode)

You can upgrade the app on your users' devices using one of the following options:

- Create an MST and Deploy it Using GPO or a Compatible Device Management Tool
- Upgrade From a CLI Using the MSI File
- Upgrade Using the EXE Install File with CLI Options

To create an MST file:

1. Download the Zscaler Client Connector MSI installer file in the[Zscaler Admin Console](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-store).
2. Follow the instructions to [create an MST file using Orca](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi), and add UPGRADEPASSWORDCMDLINE as one of the install options:
  1. Click **Tables** from the top menu, and then click **Add Row**.
  2. In the **Add Row** window:
    1. For **Property**, enter `UPGRADEPASSWORDCMDLINE`.
    2. Press `Enter` or click the **Value** field.
    3. For **Value**, enter the [generated upgrade password from the platform settings](https://help.zscaler.com/zscaler-client-connector/configuring-passwords-access-unattended-mode).
3. Click **OK**.

The install option appears on a new line.

After creating the MST, you can use it when [deploying Zscaler Client Connector to your users with Active Directory](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-active-directory-windows).

To upgrade from a CLI using the MSI file:

1. Start a command prompt as an administrator.
2. Enter the following command:

```
msiexec /i
<complete path>
/quiet UPGRADEPASSWORDCMDLINE=
<upgrade password>
```

- Replace `complete path` with the absolute pathname to the MSI install file. For example, `C:\Users\User\Downloads\Zscaler-windows-1.1.0.000213-installer.msi`
- Use the `/quiet` switch to upgrade the app in silent mode.
- Replace `upgrade password` with the upgrade password that was [generated in the platform settings](https://help.zscaler.com/zscaler-client-connector/configuring-passwords-access-unattended-mode).

To upgrade using the EXE install file:

1. Start a command prompt as an administrator.
  1. Click **Start**.
  2. In the **Start Search** box, enter `cmd`, then press `CTRL+SHIFT+ENTER`.
  3. If the User Account Control (UAC) dialog window appears, confirm that you want to continue.
2. Add the upgrade password install option to the absolute path. To add this option, enter `--upgradePasswordCmdLine``<upgrade password>`, and replace `<upgrade password>` with the upgrade password that was [generated in the platform settings](https://help.zscaler.com/zscaler-client-connector/configuring-passwords-access-unattended-mode).
3. Add additional install options to customize the file based on your organization's needs. To learn more, see [Customizing Zscaler Client Connector Install Options for EXE](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-exe).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/upgrading-zscaler-client-connector-3.7-windows","lastmod":"2022-02-08T17:46Z","nid":"1386136"} -->
## Upgrading to Zscaler Client Connector 3.7 for Windows

- Source: https://help.zscaler.com/zscaler-client-connector/upgrading-zscaler-client-connector-3.7-windows
- Product: Client Connector
- Path: Zscaler Client Connector Help > Troubleshooting > Upgrading to Zscaler Client Connector 3.7 for Windows
- Last modified: 2022-02-08T17:46Z
- Summary: Information on upgrading to Zscaler Client Connector 3.7 version for Windows and requirements for the Windows operating system.

Zscaler Client Connector 3.7 for Windows requires Windows 10 or 11. Devices running an operating system with Windows 7, 8, and 8.1 can not use Zscaler Client Connector 3.7 for Windows.

## Upgrade

If attempts are made to upgrade devices running Windows 7, 8, and 8.1, the upgrade fails without disruption to the user of the device. Devices will continue to run older versions of Zscaler Client Connector.

## Support

If you choose to keep running older versions of Zscaler Client Connector on devices with Windows 7 and 8, Zscaler offers support with our best effort, but Zscaler does not provide any fixes to problems that arise. To learn more, [End of Support for Windows 7](https://help.zscaler.com/eos-eol/end-support-windows-7). If you choose to keep running older versions of Zscaler Client Connector with Windows 8.1, Zscaler provides full support for 6 months starting from November 22, 2021, which includes fixes to problems.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/uploading-custom-ssl-certificate-zscaler-client-connector","lastmod":"2026-06-04T14:19Z","nid":"1317671"} -->
## Uploading a Custom SSL Certificate for Zscaler Client Connector

- Source: https://help.zscaler.com/zscaler-client-connector/uploading-custom-ssl-certificate-zscaler-client-connector
- Product: Client Connector
- Path: Zscaler Client Connector Help > Zscaler Client Connector Support Settings > Advanced Configuration > Uploading a Custom SSL Certificate for Zscaler Client Connector
- Last modified: 2026-06-04T14:19Z
- Summary: How to upload the custom SSL certificate in the Zscaler Admin Console.

Uploading your organization's custom SSL certificate is an optional step you can complete when [configuring SSL inspection for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-app).

When you upload your organization’s custom SSL certificate in the Zscaler Admin Console, and [turn on the install Zscaler SSL Certificate option](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-app), the custom certificate is automatically installed on users’ devices.

The **Install Zscaler SSL Certificate** feature is not supported on devices running macOS Big Sur (11) and later.

To upload the custom certificate:

1. Go to **Infrastructure** > **Connectors** > **Client** > **Directory Sync and Custom Root Cert**.
2. On the **Advanced Configuration** tab, under **Custom Certificate**, click **Upload**. [Image: Upload the custom certificate]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/using-fiddler-zscaler-client-connector","lastmod":"2026-04-29T16:25Z","nid":"1333356"} -->
## Using Fiddler with Zscaler Client Connector

- Source: https://help.zscaler.com/zscaler-client-connector/using-fiddler-zscaler-client-connector
- Product: Client Connector
- Path: Zscaler Client Connector Help > Interoperability > Using Fiddler with Zscaler Client Connector
- Last modified: 2026-04-29T16:25Z
- Summary: How to configure Zscaler Client Connector to use the Telerik Fiddler application.

To use the Telerik Fiddler application with Zscaler Client Connector, you must configure Zscaler Client Connector to use a PAC file to point to a specific port. Then, you must ensure that Fiddler is listening to that port.

At a high level, the flow of traffic should be: Browser > Fiddler > Zscaler Client Connector > Destination Page.

To learn more, see [Best Practices for Using PAC Files with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-app).

## Configuring Zscaler Client Connector and Fiddler

For more information on Fiddler, refer to the [Telerik documentation](https://www.telerik.com/support). The version used for the following steps is Fiddler 5.0.20182.28034.

To use Fiddler with Zscaler Client Connector:

- 1. Create a custom PAC file.
- 2. Add the PAC file to a forwarding profile.
- 3. Add the forwarding profile to an app profile.
- 4. Configure the proxy and port for Fiddler.

After you finish the configuration for both Zscaler Client Connector and Fiddler:

1. Enroll in Zscaler Client Connector if you have not already.
2. When**Internet Security** for Zscaler Client Connector is **ON**, you can open and use Fiddler.

Ignore any errors that Fiddler displays during startup.

To learn more about creating a custom PAC file, see [Writing a PAC File](https://help.zscaler.com/zia/writing-pac-file).

Create a custom PAC file with the following return statement:

```
return "PROXY 127.0.0.1:
<Port for Fiddler>
; PROXY ${ZAPP_LOCAL_PROXY};";
```

You must add the port to the return statement. For example, if you choose port 8888, then the return statement is as follows:

```
return "PROXY 127.0.0.1:
8888
; PROXY ${ZAPP_LOCAL_PROXY};";
```

You can host the PAC file in the Zscaler Admin Console. To learn more, see [Using Custom PAC Files to Forward Traffic to ZIA](https://help.zscaler.com/zia/how-do-i-use-custom-pac-file-forward-traffic-zia#AddCustomPAC).

If you want to debug on a local web server, the browser’s PAC file may ignore the request for 127.0.0.1. As a workaround, create a host file entry pointing to 127.0.0.1 (e.g., 127.0.0.1 server.local).

This step is not required if you are using Fiddler to automatically configure the system proxy, as Fiddler manages the system proxy settings.

[Create a new forwarding profile](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app) or update an existing one, and then add the custom PAC file.

You must configure the forwarding profile with the following settings:

- Select **Tunnel with Local Proxy**or **Tunnel** mode for any application network (i.e., On-Trusted Network, VPN-Trusted Network, Off-Trusted Network). If you have configured Fiddler to automatically populate system proxy settings, you must use **Tunnel** mode and not **Tunnel With Local Proxy** mode. This is because the Zscaler Client Connector enforces its own system proxy in **Tunnel With Local Proxy** mode.
- For the **Use Automatic Configuration Script** field, select the checkbox and enter the custom PAC URL.

[Create a Zscaler Client Connector app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-profiles) or update an existing one, and then add the configured forwarding profile.
The forwarding profile in this example is Fiddler.

You must configure the app profile with the following settings:

- The **Rule Order** must be **1**.
- The app profile must be enabled. [Image: Enter the rule order and enable the rule]
- The **Disabled Loopback Restriction** switch must be enabled. [Image: Select the Disable Loopback Restriction option]

To configure Fiddler:

1. Open the Fiddler application.
2. Go to **Tools** > **Options…**

[Image: Navigating to the Fiddler options menu to configure settings for Zscaler Client Connector]

1. In the **Options** window, click the **Connections** tab and do the following:
  - **Fiddler listens on port**: Enter the port that you configured in the forwarding profile PAC file. The port in this example is 8888.

[Image: Configuring Fiddler to listen to the port from the Zscaler Client Connector forwarding profile PAC file]

1. Click the**Gateway** tab and do the following:
  - **Manual Proxy Configuration**: Select this option. In the first field, enter the following proxy string.

```
http=127.0.0.1:
<Zscaler Client Connector Port>
;https=127.0.0.1:
<Zscaler Client Connector Port>
;ftp=127.0.0.1:
<Zscaler Client Connector Port>
;
```

You must add [the port that Zscaler Client Connector listens on](https://help.zscaler.com/zscaler-client-connector/configuring-port-zscaler-app-listen) to the proxy string. For example, if Zscaler Client Connector listens on port 9000, then the proxy string is the following:

```
http=127.0.0.1:
9000
;https=127.0.0.1:
9000
;ftp=127.0.0.1:
9000
;
```

Optionally, to debug on a local web server, enter Bypass list: <local>; in the second **Manual Proxy Configuration** field.

[Image: Configuring Fiddler manual proxy configuration settings with the port Zscaler Client Connector listens on]

If you are using the Fiddler AutoResponder feature, the **Accept all CONNECTs** option must be disabled.

1. Click **OK**.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/using-unauthorized-modification-device-posture-profile","lastmod":"2024-10-23T03:55Z","nid":"1345591"} -->
## Using the Unauthorized Modification Device Posture Profile

- Source: https://help.zscaler.com/zscaler-client-connector/using-unauthorized-modification-device-posture-profile
- Product: Client Connector
- Path: Zscaler Client Connector Help > Troubleshooting > Using the Unauthorized Modification Device Posture Profile
- Last modified: 2024-10-23T03:55Z
- Summary: How to troubleshoot known issues with the Unauthorized Modification device posture profile for Zscaler Client Connector version 1.5.2 for iOS and 1.50 for Android.

For Zscaler Client Connector version 1.5.2 for iOS and version 1.5.0 for Android, there is a known issue with the [Unauthorized Modification device posture profile](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles-zpa#Unauthorized-Modification), where unmodified devices fail the posture check.

To use this device posture profile, you must update your posture profile and ZPA access policy. You must also use Zscaler Client Connector version 1.5.3 for iOS and Zscaler Client Connector version 1.5.3 for Android.

Zscaler Client Connector for iOS 1.5.3 is available for download from the iTunes App Store. Zscaler Client Connector for Android 1.5.3 is available for download from Google Play.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/using-webview2-authentication","lastmod":"2026-05-05T13:04Z","nid":"1477906"} -->
## Using WebView2 Authentication

- Source: https://help.zscaler.com/zscaler-client-connector/using-webview2-authentication
- Product: Client Connector
- Path: Zscaler Client Connector Help > Platform and Authentication Management > Using WebView2 Authentication
- Last modified: 2026-05-05T13:04Z
- Summary: Information about how to enable WebView2 in Zscaler Client Connector.

If your organization uses advanced multi-factor authentication (MFA) for SAML or FIDO2 (Fast Identity Online 2), your users can authenticate using WebView2 in their embedded browser. Zscaler Client Connector still manages traffic for Internet & SaaS and provides access to applications through Private Access.

This article describes how to configure and enable WebView2.

Users can also authenticate using their browser. To learn more, see [Enabling Browser-Based Authentication](https://help.zscaler.com/zscaler-client-connector/enabling-browser-based-authentication).

## Prerequisites

To use WebView2 authentication, your system must meet the following minimum versions:

- Zscaler Client Connector version 4.2 or later for Windows
- .Net Framework 4.5+
- WebView2 103.0.1264.42+

If an existing version of WebView2 is earlier than the minimum version, Zscaler Client Connector pulls the latest version from Microsoft's content delivery network (CDN), using the bootstrap installer included in the Zscaler Client Connector install package.

## Configuring WebView2

To avoid encountering issues with the automated download and install of WebView2 by the Evergreen WebView2 bootstrap installer included in the Zscaler Client Connector package, do the following:

- Bypass the following Microsoft CDN domains in strict enforcement mode or for any other security policies, so that the bootstrapper can download and install WebView2.
  - .delivery.mp.microsoft.com
  - .cdp.microsoft.com

The WebView2 installation is shared by other apps which use the WebView2 runtime.

- Allowlist the file `MicrosoftEdgeWebview2Setup.exe` in antivirus and other security policies on client machines. To learn more, see [Zscaler Client Connector Processes to Allowlist](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist).

WebView2 launches its own set of processes, so Zscaler Client Connector must bypass them in the strict enforcement mode. The full path of the exe is: `%ProgramFiles%\Zscaler\ThirdParty\WebView2\MicrosoftEdgeWebview2Setup.exe`.

## Enabling WebView2 Authentication

To enable WebView2 authentication in the Zscaler Admin Console:

1. Go to **Infrastructure** > **Connectors** > **Client**.
2. Under Platform Settings, select **Windows** and click the **Platform Settings** tab.
3. Under **Authentication Settings**: [Image: Enabling WebView2 authentication]
  1. **WebView2**: Select this option to enable WebView2 authentication.
  2. **SSO using Windows Primary Account**: (Optional) Select this option to automatically log in users in an Azure AD environment using Windows as an IdP. If this option is disabled, users must enter their credentials separately to log in to Zscaler Client Connector. This option is available only for Zscaler Client Connector version 4.4 and later for Windows.
  3. **Ignore Client Cert errors for Webview2**: (Optional) Select this option to have Zscaler Client Connector continue authenticating if the IdP requires a client certificate for mutual authentication but the client device does not have a client certificate or if the client certificate is invalid. This option is available only for Zscaler Client Connector version 4.4 and later for Windows.
  4. **Allow WebView2 to follow System Proxy**: (Optional) Deselect this option to connect directly to the internet. This option is available only for Zscaler Client Connector version 4.5 and later for Windows.
  5. **Additional IdP Domains**: (Optional) Enter additional domains if the user login domain is different from the IdP domain. These domains are added to the Auth Server allowlist in the embedded WebView2 browser along with the user login domain added by default. This option is available only for Zscaler Client Connector version 4.6 and later for Windows and you must [integrate with Imprivata](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-and-imprivata-integration) to use it with a version earlier than 4.8.
  6. **Display certificate selection popup on desktop**: (Optional) Select this option to display a list of certificates for the user to select from in a pop-up window if multiple authentication certificates for the IdP are available. If disabled, the selection window displays only when users are viewing the app. Available only with Zscaler Client Connector version 4.7 and later for Windows. If you select this option, you can enable [app profile options](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#auth) to control what displays when users reauthenticate to Private Access.
4. Click **Save**.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/using-zscaler-admin-console-identity-provider","lastmod":"2026-07-31T15:29Z","nid":"1285431"} -->
## Using the Zscaler Admin Console as an Identity Provider

- Source: https://help.zscaler.com/zscaler-client-connector/using-zscaler-admin-console-identity-provider
- Product: Client Connector
- Path: Zscaler Client Connector Help > Platform and Authentication Management > Using the Zscaler Admin Console as an Identity Provider
- Last modified: 2026-07-31T15:29Z
- Summary: How to configure the Zscaler Admin Console to function as an identity provider for the Zscaler service.

If you are a Authentication Service user, see [Using Authentication Service](https://help.zscaler.com/zidentity/using-zslogin-identity-provider)[with Zscaler Client Connector Device Tokens](https://help.zscaler.com/zidentity/using-zidentity-with-zscaler-client-connector-device-tokens).

This information applies to Internet & SaaS only. The Zscaler Admin Console can function as an identity provider (IdP) for the Zscaler service. With this feature, users do not need to be tied to your organization’s standard IdP in order to authenticate to the Zscaler service. Instead, if your organization uses SAML-based single sign-on (SSO), Zscaler Client Connector can use a device token to auto-provision and silently authenticate users and devices for the Zscaler service.

You can generate the device token in the Zscaler Admin Console and pass the token to Zscaler Client Connector in an installer option. In addition, in the Zscaler Admin Console, you must select the Zscaler Client Connector Portal as your authentication method. The app is then able to gather user ID and other relevant parameters from devices and send the information to the Zscaler cloud in SAML requests. The Zscaler Admin Console parses and verifies the SAML requests, enabling the Zscaler cloud to provision and silently authenticate users.

## Configuring the Zscaler Admin Console to function as an IdP

To configure the Zscaler Admin Console to function as an IdP:

1. [Create a device token](https://help.zscaler.com/zscaler-client-connector/creating-device-token).
2. [Add the Zscaler Client Connector as an IdP](https://help.zscaler.com/zia/adding-zscaler-client-connector-portal-idp).
3. Pass the device token, user domain, and cloud name.

To use Zscaler Client Connector as an IdP for your users, you must pass the device token, user domain, and cloud name to users' devices during installation.

- For Windows with MSI Installer, see [Customizing Zscaler Client Connector with Install Options for MSI](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi).
- For Windows with EXE Installer, see [Customizing Zscaler Client Connector with Install Options for EXE](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-exe).
- For macOS with Installer App, see [Customizing Zscaler Client Connector with Install Options for macOS](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-macos).
- For Android with MDM Deployment procedures, see [Customizing Zscaler Client Connector with Install Options for Android](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-android).
- For iOS with MDM Deployment procedures, see [Customizing Zscaler Client Connector with Install Options for iOS](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-ios).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/using-zscaler-client-connector","lastmod":"2026-07-12T07:06Z","nid":"1361641"} -->
## Using Zscaler Client Connector

- Source: https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector
- Product: Client Connector
- Path: Zscaler Client Connector Help > End User Guide > Using Zscaler Client Connector
- Last modified: 2026-07-12T07:06Z
- Summary: Information on Zscaler Client Connector and its features for the supported versions of OS.

[Watch a video about Using Zscaler Client Connector.](https://fast.wistia.net/embed/iframe/g4bd3u21b2)

## Overview

Zscaler Client Connector is an application installed on your device to ensure that your internet traffic and access to your organization's internal apps are secure and in compliance with your organization’s policies, even when you're off your corporate network.

No matter where you're accessing the web, Zscaler Client Connector ensures that your traffic is forwarded to and protected by the [Internet & SaaS](https://help.zscaler.com/zia/understanding-zscaler-cloud-architecture) service. You might also have the following services enabled:

- [Private Access](https://help.zscaler.com/zpa/what-zscaler-private-access): Securely access your organization's internal resources from any location.
- [Zscaler Digital Experience (ZDX)](https://help.zscaler.com/zdx/what-is-zscaler-digital-experience): Perform synthetic probing to a desired Software as a Service (SaaS) application or internet-based service (e.g., OneDrive, Gmail, etc.) to triage and pinpoint the source of performance issues.
- [Zscaler Endpoint Data Loss Prevention (DLP)](https://help.zscaler.com/zia/about-endpoint-dlp): Protect your organization from data loss on endpoints.

Zscaler Client Connector is designed to provide a seamless user experience. It automatically recognizes when you are connected to a trusted network (for example, your corporate office network) and depending on your organization's configuration, can disable Internet & SaaS, Private Access, ZDX, and Endpoint DLP services accordingly. It can also recognize when you connect to Wi-Fi hotspots (for example, at airports, hotels, and cafés) where you must pay or accept a use policy before connecting. The app disables its services for a period of time and re-enables itself after you've had a chance to complete the steps necessary to connect.

After you log in with your user ID and complete a one-step device enrollment process, you can begin safely connecting to the web and to your organization's internal applications and services with Zscaler Client Connector. To learn more, see [Enrolling in the Zscaler Service on Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/enrolling-zscaler-service-zscaler-client-connector).

## Zscaler Client Connector Features

After you enroll with the Zscaler service, you can view the features that are supported on your OS within the Zscaler Client Connector app interface:

- Windows
- macOS
- Linux
- Android
- Android on ChromeOS
- iOS

To learn more about the tasks you can perform with Zscaler Client Connector, see the following articles:

- [Viewing Information About Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-zscaler-client-connector)
- [Viewing Information About Private Access on Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector)
- [Viewing Information About Internet Security on Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-internet-security-zscaler-client-connector)
- [Viewing Information About Digital Experience on Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-digital-experience-zscaler-client-connector)
- [Viewing Information About Zscaler Endpoint DLP on Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-zscaler-endpoint-dlp-zscaler-client-connector)
- [Viewing Notifications on Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector)
- [Troubleshooting Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector)

To navigate the main controls in the app:

- Click the **Log Out** icon on the top right-side corner to log out of Zscaler Client Connector. You might be required to enter a password your organization's admin has set for the app. If you log out of the app, you must complete enrollment again when you log back in.
- Click the **Minimize** icon to minimize the window without closing it.
- Click the **Maximize** icon to maximize the window.
- Click the **Close** icon to close the window. This does not log you out of the app.

The Zscaler Client Connector app displays services your organization subscribes to in the left-side navigation. The following image shows that an organization has subscribed to the Private Access, Internet & SaaS, and ZDX services. If your organization is not subscribed to one of these services, you do not see that option in the left-side navigation.

The app might display in a language other than English based on your system language. To learn more, see [Localization Support](https://help.zscaler.com/zscaler-client-connector/localization-support).

[Image: The features of Zscaler Client Connector for Windows]

### Zscaler Client Connector System Tray Icon Options

Zscaler Client Connector displays an icon in the system tray, as shown in the following image:

[Image: Zscaler Client Connector system tray icon]

You can right-click the icon to display the following options:

- **Open Zscaler**: Click to open the app window.
- **Authenticate:** Click to authenticate Zscaler Client Connector without opening the application.
- **Authenticate Early**: Click to reauthenticate before your authentication expires.
- **Register**: Click to complete the device enrollment.
- **Notifications**: If you use Zscaler Client Connector version 4.9 and later and the [Zscaler Notification Framework](https://help.zscaler.com/zscaler-client-connector/about-zscaler-notification-framework), you can select to view options to pause pop-up notifications: [Image: System Tray Notifications Options]
  - **Mute for 30 minutes**: Do not display pop-up notifications for 30 minutes.
  - **Mute for 1 hour**: Do not display pop-up notifications for 1 hour.
  - **Mute for 2 hours**: Do not display pop-up notifications for 2 hours.
  - **Until Resumed**: Do not display pop-up notifications until you select to resume them. When you select this option, the Notifications option displays as **Notifications (Paused)**. You must click **Notifications** and then select **Resume** to turn the notifications back on.
- **Export Logs**: Click to export logs. Logs are saved as a text file on your device.
- **Report an Issue**: If your organization has enabled this option, you can click to report an issue. For instructions on completing the form, see [Reporting an Issue with Zscaler Client Connector for Windows](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#win).
- **Exit**: Click to exit the app and disable the Zscaler service. Depending on your organization's policies, you might be required to enter a password configured by your organization's admin.

[Image: Enabling Zscaler Client Connector for Windows to show system tray notifications]

If notifications are enabled, you see notifications in the system tray icon, as shown in the following images. To learn how to enable the system tray notifications, see [Viewing Notifications on Zscaler Client Connector for Windows](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#win).

[Image: Zscaler Client Connector system tray notifications]

To navigate the main controls in the app:

- Click the **Log Out** icon on the top right-side corner to log out of Zscaler Client Connector. You might be required to enter a password your organization's admin has set for the app. If you log out of the app, you must complete enrollment again when you log back in.
- Click the **Minimize** icon to minimize the window without closing it.
- Click the **Maximize** icon to maximize the window.
- Click the **Close** icon to close the window. This does not log you out of the app.

The Zscaler Client Connector app displays services your organization subscribes to in the left-side navigation. The following image shows that an organization has subscribed to the Private Access, Internet & SaaS, and ZDX services. If your organization is not subscribed to one of these services, you do not see that option in the left-side navigation.

The app might display in a language other than English based on your system language. To learn more, see [Localization Support](https://help.zscaler.com/zscaler-client-connector/localization-support).

[Image: The features of Zscaler Client Connector for macOS]

### Zscaler Client Connector Menu Bar Options

Zscaler Client Connector displays an icon in the menu bar, as shown in the following image.

[Image: Zscaler Client Connector menu bar icon]

You can click the icon to display the following options:

- **Open**: Click to open the app window.
- **Export Logs**: Click to export logs. Logs are saved as a text file on your device.
- **Report an Issue**: If your organization has enabled this option, you can click to report an issue. For instructions on completing the form, see [Reporting an Issue with Zscaler Client Connector for macOS](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#macos).
- **Exit**: Click to exit the app and disable the Zscaler service. Depending on your organization's policies, you might be required to enter a password configured by your organization's admin.

[Image: Zscaler Client Connector menu bar icon options]

If notifications are enabled, you see notifications, as shown in the following image. To learn how to enable the menu bar notifications, see [Viewing Notifications on Zscaler Client Connector for macOS](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#macos).

[Image: Zscaler Client Connector menu bar notifications]

To navigate the main controls in the app:

- Click the **Minimize**icon to hide the window to your system tray without closing the app.
- Click the **Close**icon to close the window. This does not sign you out of the app. If you click the **Log Out**icon instead of clicking **Close**, you must complete enrollment again when you sign in.
- Click the **Log Out** icon on the top menu bar to log out of Zscaler Client Connector. Enter a password if required.

The Zscaler Client Connector app displays services your organization subscribes to in the left-side navigation. The following image shows that an organization has subscribed to both the Private Access and Internet & SaaS services. If your organization is not subscribed to one of these services, you do not see that option in the left-side navigation.

[Image: Features of Zscaler Client Connector for Linux]

### Zscaler Client Connector System Tray Icon Options

Zscaler Client Connector displays an icon in the system tray, as shown in the following image. Right-click the Zscaler Client Connector icon to display the following options:

- **Open Zscaler**: Opens the app window.
- **Export Logs**: Exports log files and saves them as text files on your device.
- **Report An Issue**: Your organization must enable this option. For instructions on completing the form, see [Reporting an Issue with Zscaler Client Connector for Linux](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#linux).
- **Exit**: Exits the app and disables the Zscaler service. Depending on your organization's policies, you might be required to enter a password configured by your organization's admin.

[Image: Zscaler Client Connector for Linux task bar icon]

If notifications are enabled, they appear in the system tray icon as shown in the following images.

[Image: Zscaler Client Connector for Linux status notification on the task bar]

In the Zscaler Client Connector window for Android, you can:

- Tap the **Log Out** icon in the top right-side corner to log out of the app. You might be required to enter a password that your organization’s admin has set for the app. If you log out of the app, you must complete enrollment again when you log back in.
- View Zscaler Client Connector’s services in the menu at the bottom. The following image shows that an organization has subscribed to the Private Access, Internet & SaaS, and ZDX services. If your organization is not subscribed to one of these services, that service does not display.

[Image: Features of Zscaler Client Connector for Android]

### Zscaler Client Connector Home Screen Icon Options

Zscaler Client Connector displays an icon on the device’s home screen, as shown in the following image:

[Image: Zscaler Client Connector for Android home screen icon]

When authentication expires, the Zscaler Client Connector icon displays with an exclamation point([Image: Authentication expired notification icon for Android]).

Press and hold the icon to display the following options:

- **Reauthenticate**: This option only displays when your authentication expires. Click to authenticate.
- **Export Logs**: Click to export logs. Logs are saved as a text file on your device.
- **Report an Issue**: If your organization has enabled this option, you can click to report an issue to Zscaler. For instructions on completing the form, see [Reporting an Issue with Zscaler Client Connector for Android](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#android).

[Image: Zscaler Client Connector for Android Shortcut Menu]

In the Zscaler Client Connector window for Android on ChromeOS, you can:

- Tap the **Log Out** icon in the top right-side corner to log out of the app. You might be required to enter a password that your organization’s admin has set for the app. If you log out of the app, you must complete enrollment again when you log back in.
- View Zscaler Client Connector's services in the left-side navigation. The following image shows that an organization has subscribed to the Private Access, Internet & SaaS, and ZDX services. If your organization is not subscribed to one of these services, that service does not display.

[Image: Features of Zscaler Client Connector for Android on ChromeOS]

### Zscaler Client Connector Home Screen Icon Options

Zscaler Client Connector displays an icon on the device’s home screen, as shown in the following image:

[Image: Zscaler Client Connector for Android on ChromeOS home screen icon]

When authentication expires, the Zscaler Client Connector icon displays with an exclamation point ([Image: Authentication expired notification icon for Android on ChromeOS]).

Press and hold the icon to display the following options:

- **Reauthenticate**: This option only displays when your authentication expires. Click to authenticate.
- **Export Logs**: Click to export logs. Logs are saved as a text file on your device.
- **Report an Issue**: If your organization has enabled this option, you can click to report an issue to Zscaler. For instructions on completing the form, see [Reporting an Issue with Zscaler Client Connector for Android on ChromeOS](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#chrome).

[Image: Zscaler Client Connector for Android on ChromeOS Shortcut Menu Options]

- Tap the **Log Out** icon on the top right-side corner to log out of the app. You might be required to enter a password your organization’s admin has set for the app. If you log out of the app, you must complete enrollment again when you log back in.
- The app displays Zscaler Client Connector’s services in the menu at the bottom. The following image shows that an organization has subscribed to both the Private Access and Internet & SaaS services. If your organization is not subscribed to one of those services, you do not see that option on the bottom menu.

[Image: Features of Zscaler Client Connector for iOS]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/using-zscaler-client-connector-cisco-anyconnect-macos-catalina","lastmod":"2023-02-07T15:50Z","nid":"1355851"} -->
## Using Zscaler Client Connector with Cisco AnyConnect on macOS Catalina

- Source: https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector-cisco-anyconnect-macos-catalina
- Product: Client Connector
- Path: Zscaler Client Connector Help > Troubleshooting > Using Zscaler Client Connector with Cisco AnyConnect on macOS Catalina
- Last modified: 2023-02-07T15:50Z
- Summary: Information about Cisco AnyConnect on macOS Catalina when using Zscaler Client Connector.

If you are using Zscaler Client Connector with Cisco AnyConnect on macOS Catalina, you must use Cisco AnyConnect 4.8 or later. There is a known interoperability issue between Cisco AnyConnect 4.7 and macOS Catalina. To learn more, see the [Cisco documentation](https://www.cisco.com/c/en/us/td/docs/security/vpn_client/anyconnect/anyconnect48/release/notes/b_Release_Notes_AnyConnect_4_8.html).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/using-zscaler-diagnostics","lastmod":"2025-12-14T06:06Z","nid":"1374426"} -->
## Using Zscaler Diagnostics

- Source: https://help.zscaler.com/zscaler-client-connector/using-zscaler-diagnostics
- Product: Client Connector
- Path: Zscaler Client Connector Help > End User Guide > Using Zscaler Diagnostics
- Last modified: 2025-12-14T06:06Z
- Summary: Information on the Zscaler Diagnostic menu features of Zscaler Client Connector for Windows and macOS.

This article details how to access Zscaler Diagnostics features that are available for use after IdP authentication. This is the same information as provided in [Troubleshooting Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector), except users can access this information without being logged in to Zscaler Client Connector on a Windows or macOS device.

- Windows
- macOS

This feature is available with Zscaler Client Connector version 3.2 or later for Windows.

The Windows Policy setting for **ZPA Machine Authentication** must be enabled so users can authenticate and start the machine tunnel.

To authenticate and start the machine tunnel from the Windows login screen:

1. In the bottom-left corner of the screen, click **Zscaler Diagnostics**.
2. Click **Zscaler Options**. See image.
3. Based on your organization’s authentication mechanism, you are prompted to complete one of the following steps:
  1. Enter the IdP credentials for Username and Password. See image.
  2. On a separate device (e.g., a mobile phone), enter the link that displays in a browser and then enter the passcode. You can scan the QR code to automatically go to the verification site. See image.

After successful authentication, the **Tunnel Status** window displays current information about the machine tunnel, including the number of packets sent or received, and whether the tunnel is running on a trusted network. Users have the option to stop the machine tunnel by clicking **Turn Off**.

See image.

The following options are available in the **Diagnostics**menu:

- **Start Packet Capture**: If your organization's admin enabled packet captures, you can use this feature when reproducing an issue. To learn more, see [Using the Start Packet Capture Option](https://help.zscaler.com/zscaler-client-connector/enabling-packet-capture-zscaler-app#using-start-packet-capture).
- **Restart Service**: You can restart the [machine tunnel](https://help.zscaler.com/zscaler-client-connector/about-machine-tunnels). Restarting does not impact security enforcement.
- **Report an Issue**: If your organization's admin enabled support access, you can use this feature to report an issue. When you submit the form, depending on your organization's setup, Zscaler Client Connector can send an email to your organization's support admin or submit a ticket directly to Zscaler Support (your support admin also receives a copy of this ticket). To learn more, see [Reporting an Issue with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector).
- **Include Packet Captures**: Select this option to include [packet capture](https://help.zscaler.com/zscaler-client-connector/enabling-packet-capture-zscaler-app#using-start-packet-capture) files when using the Report an Issue feature.
- **Clear Logs**: You can clear stored logs.
- **Update Policy**: You can manually refresh the [machine tunnel](https://help.zscaler.com/zscaler-client-connector/about-machine-tunnels) policy.

See image.

[Image: Windows login screen]

[Image: IdP authentication screen]

[Image: OAuth 2.0 authentication screen]

[Image: Tunnel Status screen]

[Image: Diagnostics options screen]

To start the machine tunnel from the macOS login screen, in the bottom-left corner of the screen, click the Zscaler icon. This machine tunnel icon is only visible when a device logs out or reboots. The icon is not visible when a user locks the screen.

See image.

[Image: zscaler icon]

The **Tunnel Status** window displays current information about the machine tunnel, including the number of packets sent or received, and whether the tunnel is running on a trusted network.

See image.

[Image: Tunnel status]

The following options are available in the **Diagnostics**menu:

- **Restart Service**: You can restart the [machine tunnel](https://help.zscaler.com/zscaler-client-connector/about-machine-tunnels). Restarting does not impact security enforcement.
- **Report an Issue**: If your organization's admin enabled support access, you can use this feature to report an issue. When you submit the form, depending on your organization's setup, Zscaler Client Connector can send an email to your organization's support admin or submit a ticket directly to Zscaler Support (your support admin also receives a copy of this ticket). To learn more, see [Reporting an Issue with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector).
- **Clear Logs**: You can clear stored logs.
- **Update Policy**: You can manually refresh the [machine tunnel](https://help.zscaler.com/zscaler-client-connector/about-machine-tunnels) policy.

See image.

[Image: Diagnostics]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/using-zscaler-notification-framework","lastmod":"2026-06-03T11:27Z","nid":"1392841"} -->
## Using the Zscaler Notification Framework

- Source: https://help.zscaler.com/zscaler-client-connector/using-zscaler-notification-framework
- Product: Client Connector
- Path: Zscaler Client Connector Help > Administration > Zscaler Client Connector Notifications > Using the Zscaler Notification Framework
- Last modified: 2026-06-03T11:27Z
- Summary: Information on the Zscaler Notifications Framework for Zscaler Client Connector.

This article provides an overview of the Zscaler Notification Framework that, when enabled, overrides the Windows-based and macOS-based notification systems. Only administrators can enable and disable the Zscaler Notification Framework in the Zscaler Admin Console.

This feature is only available for Zscaler Client Connector version 3.8 and later for Windows and for Zscaler Client Connector version 4.1 and later for macOS.

Zscaler notifications display in the bottom right corner of the screen. Up to 5 notifications can appear and time out after 5 seconds. You can move and dismiss these notifications by clicking anywhere on the window. You can also view these notifications in the [Zscaler Client Connector Notifications](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#win-notification) window.

This notification framework limits duplicate messages. Duplicate messages that display within a 2-minute interval are suppressed.

## Enabling the Zscaler Notification Framework

This feature is required for Data Loss Prevention (DLP) notifications. To learn more, see [Support for Enabling Client Connector Notifications in Web DLP Rules](https://help.zscaler.com/zia/release-upgrade-summary-2023?applicable_category=zscalertwo.net&deployment_date=2023-06-09).

- Windows
- macOS

To enable the Zscaler Notification Framework on Windows devices:

1. Go to **Infrastructure** > **Connectors** > **Client** > **Windows**.
2. On the **App Profiles** tab, click **Add Windows Policy**. The **Add Windows Policy** window appears.
3. In the **Add Windows Policy** window, enable **Use Zscaler Notification Framework**. [Image: Use Zscaler Notification Framework option]
4. Click **Save**.

To enable the Zscaler Notification Framework on macOS devices:

1. Go to **Infrastructure** > **Connectors** > **Client** > **macOS**.
2. On the **App Profiles** tab, click **Add macOS Policy**. The **Add macOS Policy** window appears.
3. In the **Add macOS Policy** window, enable **Use Zscaler Notification Framework**. [Image: Use Zscaler Notification Framework option]
4. Click **Save**.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/verifying-access-applications","lastmod":"2026-07-31T15:38Z","nid":"1514426"} -->
## Verifying Access to Applications

- Source: https://help.zscaler.com/zscaler-client-connector/verifying-access-applications
- Product: Client Connector
- Path: Zscaler Client Connector Help > End User Guide > Verifying Access to Applications
- Last modified: 2026-07-31T15:38Z
- Summary: How to verify access with additional authentication

Your organization can require additional levels of authentication to access specific applications (e.g., your default access requires only a username and password, but an application with sensitive financial information requires multi-factor authentication). If you try to access an application that requires additional authentication, Zscaler Client Connector displays a pop-up notification prompting you to verify your access.

This feature is available for Private Access only with Zscaler Client Connector version 4.6 and later for Windows and for Internet & SaaS (ZIA) only with Zscaler Client Connector version 4.7 and later for Windows. This feature is also available for Private Access and Internet & SaaS with Zscaler Client Connector version 4.7 and later for macOS and Zscaler Client Connector version 4.2 for both Android and Android on ChromeOS. Additional authentication requirements are set up by administrators in the Zscaler Admin Console and are available only if you are subscribed to Authentication Service. To use this feature with Internet & SaaS, you must enable Use Zscaler Notification Framework in the [app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#notification) and Enable ZIA Notifications in the [end user notifications](https://help.zscaler.com/zscaler-client-connector/configuring-end-user-notifications-zscaler-client-connector).

To learn more, see [Understanding Step-Up Authentication](https://help.zscaler.com/zidentity/understanding-step-up-authentication), [Configuring Access Policies](https://help.zscaler.com/zpa/configuring-access-policies), and [Configuring the URL Filtering Policy](https://help.zscaler.com/zia/configuring-url-filtering-policy).

To verify access to applications:

1. Open Zscaler Client Connector and click **Private Access** or **Internet Security**. The pending verification status message appears. [Image: Verification status message]
2. Click **Verify Now**.
3. Based on your organization’s authentication requirements, you might be prompted to complete one of the following steps:
  - You might be redirected to your organization’s single sign-on (SSO) form. Enter your credentials and log in.
  - You might be directed to a window where you can select the application you want to access (if your organization requires verification for multiple applications). Select the application you want to access and click **Verify**. You are redirected to your organization’s authentication form. Enter your credentials and log in. [Image: Verification page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades","lastmod":"2026-06-03T11:20Z","nid":"1529195"} -->
## Viewing and Configuring ZDX Module Upgrades

- Source: https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades
- Product: Client Connector
- Path: Zscaler Client Connector Help > Administration > Zscaler Client Connector Store Settings > Viewing and Configuring ZDX Module Upgrades
- Last modified: 2026-06-03T11:20Z
- Summary: How to view and schedule ZDX Module upgrades

If you use Zscaler Digital Experience (ZDX), Zscaler regularly releases new versions of the base ZDX Module that are compatible with Zscaler Client Connector for Windows and Zscaler Client Connector for macOS. Zscaler Client Connector automatically rolls out the latest version. You can configure a delayed rollout to apply a version to selected user groups for testing purposes for up to 180 days.

Contact Zscaler Support to enable this feature.

You can download an upgrade package and manually deploy with the Mobile Device Management (MDM) used by your organization or with the CLI. This feature is available only with Zscaler Client Connector version 4.7 and later for Windows.

The following options are available:

- View Auto Rollout Information
- Configure a Delayed Rollout
- Freeze a Delayed Rollout
- Unfreeze a Frozen Rollout
- Download an Upgrade Package

1. Go to **Infrastructure** > **Common Resources** > **Deployment** > **ZDX Releases**.
2. Select the platform (**Windows** or **macOS**) and view the fields: [Image: View the ZDX Module tab]
  - **ZDX Module Version**: The ZDX Module version that has been rolled out.
  - **Zscaler Client Connector Min-Max Version**: The earliest and latest versions of Zscaler Client Connector that the ZDX Module version is compatible with.
  - **Devices**: The number of devices using the ZDX Module version. Click the**Information** icon to display the number.

1. Go to **Infrastructure** > **Common Resources** > **Deployment** > **ZDX Releases**.
2. Select **Delay Rollout** and click **Save**. See image.
3. Select the platform (**Windows** or **macOS**) and disable the **Rollout** option for the ZDX Module version you want to delay the rollout for.
4. Click **Submit** to confirm you want to delay the rollout. The **Edit** icon displays in the Action column.
5. Click **Edit** to display the **Rollout Version** window. See image.
6. In the **Rollout Version** window, select the **User Groups** that will receive the rollout, and enter the date and time to apply the rollout, then click **Rollout**. If you want to delay the rollout for all users, you can select all user groups and enter a date up to 180 days from the day the ZDX Module version was released. Zscaler Client Connector automatically rolls out the ZDX Module version to all users when the 180-day maximum is reached unless you freeze the rollout. The ZDX Module version **Status** is changed to **Pending** and the **Auto Rollout In** value is changed to the number of days before Zscaler Client Connector automatically rolls out the version. See image.

If you want to delay a rollout beyond the 180-day maximum (e.g., you encountered issues in testing and need additional time to address them), you can freeze a delayed rollout:

1. Go to **Infrastructure** > **Common Resources** > **Deployment** > **ZDX Releases**.
2. Click the **Lock** icon beside the ZDX Module version. The **Request to Freeze Rollout** window appears. See image.
3. Enter the case number (e.g., the Salesforce case number) if you know it and the reason you are freezing the rollout.
4. Click **Submit**. The **Status** changes to **Frozen** and the **Auto Rollout In** field is cleared.

1. Go to **Infrastructure** > **Common Resources** > **Deployment** > **ZDX Releases**.
2. Click the **Lock** icon beside the frozen ZDX Module version, and click **Unfreeze** in the confirmation window. The rollout returns to a **Status** of **Pending** (if you unfreeze it fewer than 180 days after it was released) or is immediately rolled out (if you unfreeze it 180 or more days after it was released).

1. Go to **Infrastructure** > **Common Resources** > **Deployment** > **ZDX Releases**.
2. Select **Windows** and select the **ZIP URL (32 bit)** or **ZIP URL (64 bit)** download link for the ZDX Module Version package. [Image: View the ZDX Module tab] You can use the downloaded package with your MDM or Group Policy Object (GPO) to upgrade the ZDX Module based on your organization’s procedures. You can also [upgrade from the CLI](https://help.zscaler.com/zscaler-client-connector/interacting-zscaler-client-connector-remotely).

[Image: View the Delay Rollout tab]

[Image: Select user groups in the Rollout Version window]

[Image: View the rollout pending status]

[Image: Enter the request to freeze the rollout]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/viewing-device-fingerprint-enrolled-device","lastmod":"2026-06-12T09:38Z","nid":"1317631"} -->
## Viewing Device Fingerprint for an Enrolled Device

- Source: https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device
- Product: Client Connector
- Path: Zscaler Client Connector Help > Monitoring Usage > Viewing Device Fingerprint for an Enrolled Device
- Last modified: 2026-06-12T09:38Z
- Summary: How to view device fingerprint information for enrolled devices in the Zscaler Client Connector Portal.

From the Zscaler Admin Console, you can view device fingerprint information for [enrolled devices](https://help.zscaler.com/zscaler-client-connector/about-enrolled-devices).

To view the device fingerprint:

1. Go to **Infrastructure** > **Connectors**>**Client**>**Device Overview**. The Device Management page appears.
2. Click the **Device Details** icon to view the device fingerprint from the enrolled device.

[Image: Device Management page]

The **Zscaler Client Connector Registered Device Details** window appears.

1. In the **Zscaler Client Connector Registered Device Details** window, you can view:
  - Under **Registration Details**: Contact Zscaler Support to enable this feature.
    - **User ID**: The username used for Zscaler Client Connector during enrollment.
    - **Department**: Department information synced from Internet & SaaS.
    - **Policy Name**: The Zscaler Client Connector profile assigned to the device. To learn more, see [About Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/about-zscaler-app-profiles).
    - **Device ID**: An internal Zscaler identifier for the device.
    - **External Device ID**: The identifier that associates an external Mobile Device Management (MDM) device ID with devices in the Zscaler Admin Console. For Android only, the External Device ID displays in the Unique-ID field in the Device Details section of the Zscaler Client Connector Registered Device Details page.
    - **Last Registration Time**: The last time the user logged in to Zscaler Client Connector on the device.
    - **Last Deregistration Time**: The last time the user logged out of Zscaler Client Connector on the device.
    - **Zscaler Client Connector Version**: The Zscaler Client Connector version on the device.
    - **Tunnel Version**: The last Zscaler Tunnel (Z-Tunnel) version the device connected with.
    - **Zscaler Digital Experience Version**: The Zscaler Digital Experience (ZDX) version, if enabled on the device.
    - **Active Tunnel SDK Version**: The current tunnel SDK version to allow admins to track the devices switching between multiple tunnel SDK versions.
    - **Installation Type**:
      - **Strict Enforcement**: All internet traffic is blocked until the end user logs in to Zscaler Client Connector.
      - **General Deployment**: The end user can access the internet before they log in to Zscaler Client Connector.

See image.

- Under **Device Details**:
  - **Owner**: If [Collect Device Owner Information](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-collect-device-owner-information) is enabled, this field displays the device owner information. For Windows and macOS, this is the locally logged in user. For Android, Android on ChromeOS, and iOS, this is the Zscaler Client Connector username. When disabled, this field does not display device owner information.
  - **Machine Hostname**: If [Collect Machine Hostname Information](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-collect-hostnames) is enabled, this field displays the machine hostname. When disabled, this field does not display the machine hostname.
  - **Unique-ID**: The device's unique identifier.
  - **OS**: The device's operating system.
  - **Model**: The device's model.
  - **Manufacturer**: The device's manufacturer.
  - **MAC Address**: The device's media access control address.
  - **Device Locale**: The device's locale.
  - **Hardware Fingerprint**: The device fingerprint.
  - **Serial Number**: The device’s serial number. Applies only to Zscaler Client Connector version 4.7 and later for Windows, Zscaler Client Connector version 4.3 and later for macOS, Zscaler Client Connector version 4.4 and later for iOS, and Zscaler Client Connector version 4.2.1 and later for Linux.
  - **Imprivata User**: **Yes**indicates an Imprivata user is logged in to Zscaler Client Connector.

See image.

- Under **Service Status**:
  - **ZIA Enabled**: Displays **True**if the user is entitled for the Internet & SaaS service in Zscaler Client Connector. Displays **False**if the user is not entitled for the Internet & SaaS service in Zscaler Client Connector.
  - **ZIA Health**:Displays **Active**if Zscaler Client Connector is connected to Internet & SaaS. Displays **Inactive**if Zscaler Client Connector is not connected to Internet & SaaS.
  - **Last Seen Connected to ZIA**:The last known date and time of connection to Internet & SaaS.
  - **ZPA Enabled**: Displays **True**if the user is entitled for the Private Access service in Zscaler Client Connector. Displays **False**if the user is not entitled for the Private Access service in Zscaler Client Connector.
  - **ZPA Health**: Displays **Active**if Zscaler Client Connector is connected to Private Access. Displays **Inactive**if Zscaler Client Connector is not connected to Private Access.
  - **Last Seen Connected to ZPA**: The last known date and time of connection to Private Access.
  - **ZDX Enabled**: Displays **True**if the user is entitled for the ZDX service in Zscaler Client Connector. Displays **False**if the user is not entitled for the ZDX service in Zscaler Client Connector.
  - **ZDX Health**: Displays **Active**if Zscaler Client Connector is connected to ZDX. Displays **Inactive**if Zscaler Client Connector is not connected to ZDX.
  - **Last Seen Connected to ZDX**: The last known date and time of connection to ZDX.
  - **Zscaler Deception Enabled**: Displays **True**if the user is entitled for the Deception service in Zscaler Client Connector. Displays **False**if the user is not entitled for the Deception service in Zscaler Client Connector.
  - **Zscaler Deception Health**: Displays **Active**if Zscaler Client Connector is connected to Deception. Displays **Inactive**if Zscaler Client Connector is not connected to Deception.
  - **Last Seen Connected to Zscaler Deception**: The last known date and time of connection to Deception.
  - **DC Location Method**: Displays **Source IP** if MaxMind’s GeoIP was used to find the nearest data center. Displays **Device Geolocation** if [Use Endpoint Location for Zscaler DC Selection](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#advanced) is enabled and the user’s location services were used to find the nearest data center. Applies to Zscaler Client Connector version 4.8 and later for Windows only.

See image.

- Under **Compliance Status**:
  - **Refresh Status**: Click this option to refresh the passwords manually to ensure you have the latest password to provide to an end user. Passwords refresh automatically every 60 minutes while the window is open.
  - **Device State**: The device’s policy status. To learn more, see [Device States for Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/policy-statuses-enrolled-devices).
  - **Last Seen with Zscaler Client Connector Active**: The last time that Zscaler Client Connector was active on the device.
  - **Last Configuration Download Time**: The last time the Zscaler Client Connector profile was updated. To learn more, see [Zscaler Client Connector Update Intervals](https://help.zscaler.com/zscaler-client-connector/zscaler-app-update-intervals).
  - **Configuration Download Count**: The total number of times the app profile was updated since enrollment.
  - **One-Time Password**: Displays a temporary password for login.
  - **Logout, Disable, Uninstall Password**: The password associated with the device’s app profile. Applies to Zscaler Client Connector version 4.0 for Windows and Zscaler Client Connector version 4.1 for macOS. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
  - For Zscaler Client Connector version 4.0 and later for Windows and Zscaler Client Connector version 4.1 and later for macOS, the following optional one-time passwords (OTPs) listed are associated with the device’s app profile and are configured in [app profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles): Click the **Copy** icon to copy the password to your clipboard.
    - **Logout OTP**: The password users must enter to log out of Zscaler Client Connector.
    - **Revert OTP**: The password users must enter to revert to the previous Zscaler Client Connector version.
    - **Uninstall OTP**: The password users must enter to uninstall Zscaler Client Connector.
    - **Exit OTP**: The password that users can enter to exit the app from the system tray without disabling Internet & SaaS.
    - **Disable ZIA OTP**: The password users must enter to disable the Internet & SaaS service.
    - **Disable ZPA OTP**: The password users must enter to disable the Private Access service.
    - **Disable ZDX OTP**: The password users must enter to disable the ZDX service.
    - **Disable Endpoint DLP OTP**: The password users must enter to disable data protection in Zscaler Client Connector.
  - **Device Trust Level**: Displays the device trust level based on the configured levels in [Internet & SaaS posture profiles](https://help.zscaler.com/zscaler-client-connector/adding-internet-saas-posture-profiles). Trust levels are **Low**, **Medium**, **High**, or **Unknown**.
  - **Disable Anti-Tampering OTP**: The password used to disable anti-tampering protection. This option is available for Zscaler Client Connector version 4.1 and later for Windows.
  - **Anti-Tampering Status**: Displays whether anti-tampering is enabled or disabled.

See image.

- Under **Fetch Logs**and **Fetch Lightweight Logs**: To enable **Fetch Logs** or **Fetch Lightweight Logs**, you must first enable the option Client Connector App Logs and then configure an email address on the [App Supportability tab in Client Connector Support](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-support-options-zscaler-client-connector).

When you click **Fetch Logs**or**Fetch Lightweight Logs**, Zscaler Client Connector uploads logs from the device to the web server during the next keepalive, which is usually once every hour while the device is active. Alternatively, you can click **Update Policy** in Zscaler Client Connector to immediately send a keepalive signal. To learn more, see [Viewing Information About Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-zscaler-client-connector).

This table lists the differences between Fetch Logs and Fetch Lightweight Logs:

| **Fetch Logs** | **Fetch Lightweight Logs** |
| --- | --- |
| Applies to all OSs for all supported versions. | Applies to Windows 4.7 and later. |
| Captures logs from the previous 14 days. | Captures logs from the previous 24 hours. |
| Includes PCAP files and crash files. | Doesn't include PCAP files. Includes crash files. |

These fields describe the process for remotely fetching logs from the device:

- **Fetch Log Status**: The following messages display to keep you informed of the fetch log status:
  - **Waiting for KeepAlive**: Displays after the fetch log request is initiated.
  - **Extraction in progress**: Displays after the KeepAlive message is displayed.
  - **Completion**: Displays if the fetch log request is successful.
  - **Error while fetching logs**: Displays if the fetch log request fails.
- **Log Timestamp**: Displays the date and time you clicked **Fetch Logs** and Zscaler Client Connector began uploading the logs to the web server.
- **Log Acknowledge Timestamp**: Displays the date and time the log fetch successfully completed.
- **Log URL**: Displays the URL provided after Zscaler Client Connector completes uploading logs. Click the **Copy** icon to copy the **Log URL**.

See image.

- Under **Revert Client Connector**: Displays the status of reverting to a previous Zscaler Client Connector version. Possible values are **Unknown**, **InProgress**, **PreviousBuildNotAvailable**, **RevertFailed**, and **RevertSuccess**.

See image.

- Under **Service Disable Reason**: Displays the service that was disabled, the time that the service was disabled, and the reason for disabling a service.

See image.

- Under **ZPA Partner Logins**: Displays partner logins that logged in to a device’s tenant. To learn more, see [Enabling Private Access Partner Logins](https://help.zscaler.com/zscaler-client-connector/enabling-private-access-partner-logins).

See image.

- Under **Device Upgrade Status**: Displays the status of an upgrade that is in progress.
  - **Upgrade Status**: Possible values are:
    - **In Progress**: Waiting for upgrade status from Zscaler Client Connector.
    - **Complete**: Device upgraded successfully.
    - **Failure**: Device failed to upgrade.
  - **Client Connector Target Version**: The Zscaler Client Connector version provided by the Zscaler Admin Console in the last auto-update call for Zscaler Client Connector.
  - **Client Connector Target Version Timestamp**: The date and time when the targeted Zscaler Client Connector version was sent to the client, regardless of whether the upgrade was successful or not.

See image.

- Under **Force Remove**: When viewing device fingerprint information for an enrolled device, you can also force remove the device from the Zscaler Admin Console. To force remove a device, click **Force Remove**.

You can only force remove devices with the device state of **Removal Pending**. To learn more about device states, see [Device States for Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/device-states-enrolled-devices).

See image.

- Under **Quarantine**: You can quarantine a device by clicking **Quarantine**. This prevents the device from re-enrolling. If a device is in quarantine, click **Unquarantine**. To learn more, see [Quarantining a Device in the Zscaler Admin Console](https://help.zscaler.com/zscaler-client-connector/quarantining-device-zscaler-admin-console). See image.

[Image: Registration Details on the Registered Device Details window]

[Image: Device Details on the Registered Device Details window]

[Image: Compliance Status on the Registered Device Details window]

[Image: Fetch Logs, Fetch Lightweight Logs on the Registered Device Details window]

[Image: Revert Client Connector on the Registered Device Details window]

[Image: Service Status on the Registered Device Details window]

[Image: Force Remove and Quarantine buttons on the Registered Device Details window]

[Image: Force Remove and Unquarantine buttons on the Registered Device Details window]

[Image: Service Disable Reason on the Registered Device Details window]

[Image: ZPA Partner Logins on the Registered Device Details window]

[Image: Device Upgrade Status on the Registered Device Details window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/viewing-device-fingerprint-partner-device","lastmod":"2026-07-24T17:47Z","nid":"1420311"} -->
## Viewing Device Fingerprint Information for a Partner Device

- Source: https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-partner-device
- Product: Client Connector
- Path: Zscaler Client Connector Help > Monitoring Usage > Viewing Device Fingerprint Information for a Partner Device
- Last modified: 2026-07-24T17:47Z
- Summary: How to view device fingerprint information for partner devices in the Zscaler Admin Console.

From the Zscaler Admin Console, you can view device fingerprint information for [partner devices](https://help.zscaler.com/zscaler-client-connector/about-partner-devices).

To view device fingerprint information for a partner device:

1. Go to **Infrastructure** > **Connectors**>**Client**>**Partner Devices**.
2. Click the **Partner Device Details** icon to view the device fingerprint from the enrolled device. The **Zscaler Client Connector Registered Partner Device Details** window appears. See image.
3. In the **Zscaler Client Connector Registered Partner Device Details** window, you can view:
  - Under **Registration Details**:
    - **User ID**: The username used for Zscaler Client Connector.
    - **Device ID**: An internal Zscaler identifier for the device.
    - **Last Registration Time**: The last time the user logged in to Zscaler Client Connector on the device.
    - **Last Deregistration Time**: The last time the user logged out of Zscaler Client Connector on the device.
    - **Zscaler Client Connector Version**: The Zscaler Client Connector version on the device.
    - **Tunnel Version**: The last Zscaler Tunnel (Z-Tunnel) version the device connected with.
    - **Device State**: The registration status of the device.

Unregistered partner devices are removed if inactive in the previous 24 hours, allowing the same user or a new user to log in.

See image.

- Under **Device Details**:
  - **Owner**: If [Collect Device Owner Information](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-collect-device-owner-information) is enabled, this field displays the device owner information. For Windows and macOS, this is the locally logged-in user. For Android and iOS, this is the Zscaler Client Connector username. If this option is disabled, this field does not display the device owner information.
  - **Machine Hostname**: If [Collect Machine Hostname Information](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-collect-hostnames) is enabled, this field displays the machine hostname. If this option is disabled, this field does not display the machine hostname.
  - **Unique-ID**: The device's unique identifier.
  - **OS**: The device's operating system.
  - **Model**: The device's model.
  - **Manufacturer**: The device’s manufacturer.
  - **MAC Address**: The device’s media access control address.
  - **Device Locale**: The device’s locale.
  - **Hardware Fingerprint**: The device fingerprint.

See image.

- Under **Service Status**:
  - **ZPA Enabled**: Displays whether Private Access is enabled or disabled.
  - **ZPA Health**: Displays whether the health of Private Access is active or inactive.
  - **Last Seen Connected to ZPA**: The last known date and time of connection to Private Access.

See image.

- Under **Compliance Status**:
  - **Device State**: The device’s policy status. To learn more, see [Device States for Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/policy-statuses-enrolled-devices).
  - **Last Seen with Zscaler Client Connector Active**: The last time that Zscaler Client Connector was active on the device.
  - **Last Configuration Download Time**: The last time the Zscaler Client Connector profile was updated. To learn more, see Zscaler Client Connector[Update Intervals](https://help.zscaler.com/zscaler-client-connector/zscaler-app-update-intervals).
  - **Configuration Download Count**: The total number of times the app profile was updated since enrollment.

See image.

When viewing device fingerprint information for a partner device, you can also force remove the device from the Zscaler Admin Console. To force remove a device, click **Force Remove**. To learn more, see [Force Removing a Device from the Zscaler Admin Console](https://help.zscaler.com/zscaler-client-connector/force-removing-device-zscaler-client-connector-portal).

[Image: Click to view device details]

[Image: Device registration details]

[Image: Device Details]

[Image: Service Status Details]

[Image: Compliance Status details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/viewing-information-about-digital-experience-zscaler-client-connector","lastmod":"2025-06-02T15:11Z","nid":"1370036"} -->
## Viewing Information About Digital Experience on Zscaler Client Connector

- Source: https://help.zscaler.com/zscaler-client-connector/viewing-information-about-digital-experience-zscaler-client-connector
- Product: Client Connector
- Path: Zscaler Client Connector Help > End User Guide > Viewing Information About Digital Experience on Zscaler Client Connector
- Last modified: 2025-06-02T15:11Z
- Summary: Information on the Digital Experience page on Zscaler Client Connector.

This article provides an overview of the Digital Experience window of Zscaler Client Connector. This window features connectivity information and troubleshooting options for [Zscaler Digital Experience (ZDX)](https://help.zscaler.com/zdx/what-is-zscaler-digital-experience).

This service is only available if you are using Zscaler Client Connector version 2.2.1 or later for Windows and macOS devices or Zscaler Client Connector version 1.12 or later for Android and Android on ChromeOS devices.

- Windows
- macOS
- iOS
- Android
- Android on ChromeOS

For information about other Zscaler Client Connector features, see [Using Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector).

[Image: Digital Experience window]

## Connectivity

This section displays the following connectivity information:

- **Username**: Displays the username with which you are logged in to the app.
- **Service Status**: Displays the app connection status. Tap **Turn Off** if you want to turn off the Zscaler Tunnel (Z-Tunnel) and disable the Digital Experience (i.e., ZDX) service while remaining logged in to the app. The Digital Experience service is disabled until you tap **Turn On**.
- **Authentication Status**: Displays the current authentication state, i.e., whether you are currently authenticated or not.
- **Server**: Displays the IP Address of the server to which your traffic is being forwarded.
- **Time Connected**: Displays your initial connection time for the present session.
- **ZDX Service Version**: Displays the ZDX version your organization is using.

## Troubleshoot

This section displays the following troubleshooting information:

- **Clear ZDX Data**: Tap to clear the ZDX data that Zscaler Client Connector stored.
- **Restart ZDX Service**: Tap to restart the ZDX service.

[Image: Digital Experience window]

## Connectivity

This section displays the following connectivity information:

- **Username**: Displays the username with which you are logged in to the app.
- **Service Status**: Displays the app connection status. Tap **Turn Off** if you want to turn off the Zscaler Tunnel (Z-Tunnel) and disable the Digital Experience (i.e., ZDX) service while remaining logged in to the app. The Digital Experience service is disabled until you tap **Turn On**.
- **Authentication Status**: Displays the current authentication state, i.e., whether you are currently authenticated or not.
- **Server Address**: Displays the IP address of the server to which your traffic is being forwarded.
- **Time Connected**: Displays your initial connection time for the present session.
- **ZDX Service Version**: Displays the ZDX version your organization is using.

## Troubleshoot

This section displays the following troubleshooting information:

- **Clear ZDX Data**: Tap to clear the ZDX data that Zscaler Client Connector stored.
- **Restart ZDX Service**: Tap to restart the ZDX service.

[Image: iOS ZDX]

## Zero Trust Connectivity

This section displays the following connectivity information:

- **Username**: Displays the username with which you are logged in to the app.
- **Service Status**: Displays the app connection status. Tap **Turn Off** if you want to turn off the Zscaler Tunnel (Z-Tunnel) and disable the Digital Experience (i.e., ZDX) service while remaining logged in to the app. The Digital Experience service is disabled until you tap **Turn On**.
- **Authentication Status**: Displays the current authentication state, i.e., whether you are currently authenticated or not.
- **Server Address**: Displays the IP address of the server to which your traffic is being forwarded.
- **Time Connected**: Displays your initial connection time for the present session.
- **ZDX Service Version**: Displays the ZDX version your organization is using.

## Troubleshoot

This section displays the following troubleshooting information:

- **Clear ZDX Data**: Tap to clear the ZDX data that Zscaler Client Connector stored.
- **Restart ZDX Service**: Tap to restart the ZDX service.

[Image: Digital Experience window]

## Connectivity

This section displays the following connectivity information:

- **Username**: Displays the username with which you are logged in to the app.
- **Service Status**: Displays the app connection status. Tap **Turn Off** if you want to turn off the Zscaler Tunnel (Z-Tunnel) and disable the Digital Experience (i.e., ZDX) service while remaining logged in to the app. The Digital Experience service is disabled until you tap **Turn On**.
- **Authentication Status**: Displays the current authentication state, i.e., whether you are currently authenticated or not.
- **Server Address**: Displays the IP address of the server to which your traffic is being forwarded.
- **Time Connected**: Displays your initial connection time for the present session.
- **ZDX Service Version**: Displays the ZDX version your organization is using.

## Troubleshoot

This section displays the following troubleshooting information:

- **Restart ZDX Service**: Tap to restart the ZDX service. You can tap **Restart ZDX Service** only once within a 30-second period. Applies to Zscaler Client Connector version 3.8 and later for Android.
- **Clear ZDX Data**: Tap to clear the ZDX data that Zscaler Client Connector stored.

[Image: Digital Experience window]

## Connectivity

This section displays the following connectivity information:

- **Username**: Displays the username with which you are logged in to the app.
- **Service Status**: Displays the app connection status. Tap **Turn Off** if you want to turn off the Zscaler Tunnel (Z-Tunnel) and disable the Digital Experience (i.e., ZDX) service while remaining logged in to the app. The Digital Experience service is disabled until you tap **Turn On**.
- **Authentication Status**: Displays the current authentication state, i.e., whether you are currently authenticated or not.
- **Server Address**: Displays the IP address of the server to which your traffic is being forwarded.
- **Time Connected**: Displays your initial connection time for the present session.
- **ZDX Service Version**: Displays the ZDX version your organization is using.

## Troubleshoot

This section displays the following troubleshooting information:

- **Restart ZDX Service**: Tap to restart the ZDX service. You can tap **Restart ZDX Service** only once within a 30-second period. Applies to Zscaler Client Connector version 3.8 and later for Android on ChromeOS.
- **Clear ZDX Data**: Tap to clear the ZDX data that Zscaler Client Connector stored.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/viewing-information-about-internet-security-zscaler-client-connector","lastmod":"2026-05-06T11:22Z","nid":"1363056"} -->
## Viewing Information About Internet Security on Zscaler Client Connector

- Source: https://help.zscaler.com/zscaler-client-connector/viewing-information-about-internet-security-zscaler-client-connector
- Product: Client Connector
- Path: Zscaler Client Connector Help > End User Guide > Viewing Information About Internet Security on Zscaler Client Connector
- Last modified: 2026-05-06T11:22Z
- Summary: Information on the Internet Security page of the Zscaler Client Connector.

This article provides an overview of the **Internet Security**window on Zscaler Client Connector for different OSs. This window features connectivity information and traffic statistics for [Internet & SaaS](https://help.zscaler.com/zia/understanding-zscaler-cloud-architecture).

- Windows
- macOS
- Linux
- Android
- Android on ChromeOS
- iOS

For information about other Zscaler Client Connector features, see [Using Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector).

This window includes the following features on Windows devices.

[Image: Internet Security Window]

## Connectivity

This section displays the following connectivity information:

- **Username**: Displays the username with which you are logged in to the app.
- **Service Status**: Displays the app connection status. Click **Turn Off**if you want to disable the Internet & SaaS service while remaining logged in to the app. Depending on your organization's policies, you might be required to enter a password. The Internet & SaaS service is disabled until you click**Turn On**.
- **Network Type:** Displays the type of network you are connected to (Trusted Network, VPN Trusted Network, Split VPN Trusted Network, or Off-Trusted Network).
- **Server**: Displays the IP address of the server to which your traffic is being forwarded.
- **Client**: Displays your client IP address.
- **Time Connected**: Displays your initial connection time for the present session.
- **Tunnel Version**: Displays the version of the Zscaler Tunnel (Z-Tunnel) that Zscaler Client Connector forwards your traffic through.

## Statistics

This section displays the following traffic statistics:

- **Total Bytes Sent:** Displays, in real time, bytes of traffic sent from your computer through the app.
- **Total Bytes Received**: Displays, in real time, bytes of traffic received by your computer through the app.

This window includes the following features on macOS devices.

[Image: Internet Security Window]

## Connectivity

This section displays the following connectivity information:

- **Username**: Displays the username with which you are logged in to the app.
- **Service Status**: Displays the app connection status. Click **Turn Off**if you want to disable the Internet & SaaS service while remaining logged in to the app. Depending on your organization's policies, you might be required to enter a password. The Internet & SaaS service is disabled until you click**Turn On**.
- **Network Type**: Displays the type of network you are connected to (Trusted Network, VPN Trusted Network, Split VPN Trusted Network, or Off-Trusted Network).
- **Server**: Displays the IP address of the server to which your traffic is being forwarded.
- **Client IP**: Displays your client IP address.
- **Time Connected**: Displays your initial connection time for the present session.
- **Tunnel Version**: Displays the version of the Zscaler Tunnel (Z-Tunnel) that Zscaler Client Connector forwards your traffic through.

## Statistics

This section displays the following traffic statistics:

- **Total Bytes Sent:** Displays, in real time, bytes of traffic sent from your computer through the app.
- **Total Bytes Received**: Displays, in real time, bytes of traffic received by your computer through the app.

This window includes the following features on Linux devices.

[Image: Internet Security Window]

## Connectivity

This section displays the following connectivity information:

- **Username**: Displays the username with which you are logged in to the app.
- **Service Status**: Displays the app connection status. Click **Turn Off** if you want to disable the Internet & SaaS service while remaining logged in to the app. Depending on your organization's policies, you might be required to enter a password. The Internet & SaaS service is disabled until you click**Turn On**.
- **Network Type:** Displays the type of network you are connected to (Trusted Network, VPN Trusted Network, Split VPN Trusted Network, or Off Trusted Network).
- **Server**: Displays the IP address of the server to which your traffic is being forwarded.
- **Client**: Displays your client IP address.
- **Time Connected**: Displays your initial connection time for the present session.
- **Tunnel Version**: Displays the version of the Zscaler Tunnel (Z-Tunnel) that Zscaler Client Connector forwards your traffic through.

## Statistics

This section displays the following traffic statistics:

- **Total Bytes Sent:** Displays, in real time, bytes of traffic sent from your computer through the app.
- **Total Bytes Received**: Displays, in real time, bytes of traffic received by your computer through the app.

This screen includes the following features on Android devices:

## Connectivity

This section displays the following connectivity information:

- **Username**: Displays the username with which you are logged in to the app.
- **Service Status**: Displays the app connection status. Tap **Turn Off** if you want to disable the Internet & SaaS service while remaining logged in to the app. Depending on your organization’s policies, you might be required to enter a password. The Internet & SaaS service is disabled until you tap **Turn On**.
- **Network Type**: Displays the type of network you are connected to (Trusted Network or Off-Trusted Network).
- **Proxy Server**: Displays the IP address of the server to which your traffic is being forwarded.
- **Client IP**: Displays your client IP address.
- **Time Connected**: Displays your initial connection time for the present session.
- **Tunnel Version**: Displays the version and protocol of the Zscaler Tunnel (Z-Tunnel) that Zscaler Client Connector forwards your traffic through.

## Statistics

This section displays the following traffic statistics:

- **Total Bytes Sent**: Displays, in real time, bytes of traffic sent from your device through the app.
- **Total Bytes Received**: Displays, in real time, bytes of traffic received by your device through the app.

[Image: Internet Security Screen]

This screen includes the following features on Android on ChromeOS devices:

## Connectivity

This section displays the following connectivity information:

- **Username**: Displays the username with which you are logged in to the app.
- **Service Status**: Displays the app connection status. Tap **Turn Off** if you want to disable the Internet & SaaS service while remaining logged in to the app. Depending on your organization’s policies, you might be required to enter a password. The Internet & SaaS service is disabled until you tap **Turn On**.
- **Network Type**: Displays the type of network you are connected to (Trusted Network or Off-Trusted Network).
- **Proxy Server**: Displays the IP address of the server to which your traffic is being forwarded.
- **Client IP**: Displays your client IP address.
- **Time Connected**: Displays your initial connection time for the present session.
- **Tunnel Version**: Displays the version and protocol of the Zscaler Tunnel (Z-Tunnel) that Zscaler Client Connector forwards your traffic through.

## Statistics

This section displays the following traffic statistics:

- **Total Bytes Sent**: Displays, in real time, bytes of traffic sent from your Chromebook through the app.
- **Total Bytes Received**: Displays, in real time, bytes of traffic received by your Chromebook through the app.

[Image: Internet Security Window]

This screen features connectivity information and traffic statistics for Internet & SaaS on iOS devices.

[Image: Internet Security Screen]

## Connectivity

This section displays the following connectivity information:

- **Username**: Displays the username with which you are logged in to the app.
- **Service Status**: Displays the app connection status. Tap **Turn Off** if you want to disable the Internet & SaaS service while remaining logged in to the app. Depending on your organization’s policies, you might be required to enter a password. The Internet & SaaS service is disabled until you tap **Turn On**.
- **Network Status**: Displays the type of network you are connected to (Trusted Network, VPN-Trusted Network, Split VPN Trusted Network, or Off-Trusted Network).
- **Server IP**: Displays the IP address of the server to which your traffic is being forwarded.
- **Client IP**: Displays your client IP address.
- **Time Connected**: Displays your initial connection time for the present session.
- **Tunnel SDK Version**: The current tunnel SDK version.
- **Tunnel Version**: Displays the version of the Zscaler Tunnel (Z-Tunnel) that Zscaler Client Connector forwards your traffic through.

## Statistics

This section displays the following traffic statistics:

- **Bytes Sent**: Displays, in real time, bytes of traffic sent from your mobile device through the app.
- **Bytes Received**: Displays, in real time, bytes of traffic received by your computer through the app.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector","lastmod":"2026-05-06T10:40Z","nid":"1363051"} -->
## Viewing Information About Private Access on Zscaler Client Connector

- Source: https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector
- Product: Client Connector
- Path: Zscaler Client Connector Help > End User Guide > Viewing Information About Private Access on Zscaler Client Connector
- Last modified: 2026-05-06T10:40Z
- Summary: Information on the Private Access window on Zscaler Client Connector.

This article provides an overview of the Private Accesswindow of Zscaler Client Connector for different OSs. This window features connectivity information and traffic statistics for [Private Access](https://help.zscaler.com/zpa/what-zscaler-private-access).

- Windows
- macOS
- Linux
- Android
- Android on ChromeOS
- iOS

For information about other Zscaler Client Connector features, see [Using Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector).

This window includes the following features on Windows devices.

## Zero Trust Connectivity

This section displays the following connectivity information:

- **Username**: Displays the username with which you are logged into the app.
- **Service Status**: Displays the app connection status. Click **Turn Off** if you want to turn off the Zscaler Tunnel (Z-Tunnel) and disable the Private Access service while remaining logged in to the app. The Private Access service is disabled until you click**Turn On**.
- **Network Type:** Displays the type of network you are connected to (**Trusted Network**, **VPN-Trusted Network**, **Split VPN-Trusted Network**, or **Off-Trusted Network**).
- **Authentication Status**: Displays the current authentication state (i.e., whether you are currently authenticated or not).
- **Authenticate Early**: Click **Authenticate Early** to reauthenticate Private Access before your authentication expires. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
- **Broker:** Displays the IP address of the server to which your traffic is being forwarded.
- **Client:**Displays the local IP address of your device.
- **Time Connected**: Displays your initial connection time for the present session.
- **Protocol:** Displays the protocol used for the tunnel (TLS or DTLS).

## Statistics

This section displays the following traffic statistics:

- **Total Bytes Sent**: Displays, in real time, bytes of traffic sent from your computer through the Z-Tunnel.
- **Total Bytes Received**: Displays, in real time, bytes of traffic received by your computer through the Z-Tunnel.

[Image: Private Access Window]

## VPN Tunnel

This section displays only if you are connected to the Zscaler cloud using a VPN connection. To learn more, see [About VPN Service Edges](https://help.zscaler.com/zpa/about-vpn-service-edges).

This section displays the following connectivity information:

- **VPN Service Edge**: Displays the IP address of the VPN Service Edge to which your traffic is being forwarded. This field displays as **Network Service Edge** in previous versions of Zscaler Client Connector.
- **Client IP**: Displays the IP address assigned to your device by the VPN Service Edge for the session.
- **Time Connected**: Displays your initial connection time for the present session.
- **Total Bytes Sent**: Displays, in real time, bytes of traffic sent from your computer through the VPN Tunnel.
- **Total Bytes Received**: Displays, in real time, bytes of traffic received from your computer through the VPN Tunnel.

[Image: VPN Tunnel section]

This window includes the following features on macOS devices.

## Zero Trust Connectivity

This section displays the following connectivity information:

- **Username**: Displays the username with which you are logged into the app.
- **Service Status**: Displays the app connection status. Click **Turn Off** if you want to turn off the Zscaler Tunnel (Z-Tunnel) and disable the Private Access service while remaining logged in to the app. The Private Access service is disabled until you click**Turn On**.
- **Network Type:** Displays the type of network you are connected to (Trusted Network, VPN-Trusted Network, Split VPN-Trusted Network, or Off-Trusted Network).
- **Authentication Status**: Displays the current authentication state (i.e., whether you are currently authenticated or not).
- **Authenticate Early**: Click **Authenticate Early** to reauthenticate Private Access before your authentication expires. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
- **Broker:** Displays the IP address of the server to which your traffic is being forwarded.
- **Client:**Displays the local IP address of your device.
- **Time Connected**: Displays your initial connection time for the present session.
- **Tunnel Protocol:** Displays the protocol used for the tunnel (TLS or DTLS).

## Statistics

This section displays the following traffic statistics:

- **Total Bytes Sent**: Displays, in real time, bytes of traffic sent from your computer through the Z-Tunnel.
- **Total Bytes Received**: Displays, in real time, bytes of traffic received by your computer through the Z-Tunnel.

[Image: Private Access Window]

## VPN Tunnel

This section displays only if you are connected to the Zscaler cloud using a VPN connection. To learn more, see [About VPN Service Edges](https://help.zscaler.com/zpa/about-vpn-service-edges).

This section displays the following connectivity information:

- **VPN Service Edge**: Displays the IP address of the VPN Service Edge to which your traffic is being forwarded. This field displays as **Network Service Edge** in previous versions of Zscaler Client Connector.
- **Client IP**: Displays the IP address assigned to your device by the VPN Service Edge for the session.
- **Time Connected**: Displays your initial connection time for the present session.
- **Total Bytes Sent**: Displays, in real time, bytes of traffic sent from your computer through the VPN Tunnel.
- **Total Bytes Received**: Displays, in real time, bytes of traffic received from your computer through the VPN Tunnel.

[Image: VPN Tunnel section]

This window includes the following features on Linux devices.

## Zero Trust Connectivity

This section displays the following connectivity information:

- **Username**: Displays the username with which you are logged into the app.
- **Service Status**: Displays the app connection status. Click **Turn Off** if you want to turn off the Zscaler Tunnel (Z-Tunnel) and disable the Private Access service while remaining logged in to the app. The Private Access service is disabled until you click**Turn On**.
- **Network Type**: Displays the type of network you are connected to (Trusted Network, VPN-Trusted Network, Split VPN-Trusted Network, or Off-Trusted Network).
- **Authentication Status**: Displays the current authentication state (i.e., whether you are currently authenticated or not).
- **Authenticate Early**: Click **Authenticate Early** to reauthenticate Private Access before your authentication expires. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
- **Broker**: Displays the IP address of the server to which your traffic is being forwarded.
- **Client**: Displays the local IP address of your device.
- **Time Connected**: Displays your initial connection time for the present session.

## Statistics

This section displays the following traffic statistics:

- **Total Bytes Sent:** Displays, in real time, bytes of traffic that your mobile device has sent through the Z-Tunnel.
- **Total Bytes Received**: Displays, in real time, bytes of traffic that your mobile device has received through the Z-Tunnel.

[Image: Private Access Window]

This window includes the following features on Android devices:

## Zero Trust Connectivity

This section displays the following connectivity information:

- **Username**: Displays the username with which you are logged in to the app.
- **Service Status**: Displays the app connection status. Tap **Turn Off** if you want to turn off the Zscaler Tunnel (Z-Tunnel) and disable the Private Access service while remaining logged in to the app. The Private Access service is disabled until you tap **Turn On**.
- **Network Type**: Displays the type of network you're connected to (Trusted Network or Off-Trusted Network).
- **Authentication Status**: Displays whether the user is authenticated to the Private Access service.
- **Authenticate Early**: Click to reauthenticate Private Access before your authentication expires.
- **Broker**: Displays the IP address of the server to which your traffic is being forwarded.
- **Time Connected**: Displays your initial connection time for the present session.
- **Protocol**: Displays the protocol used for the tunnel (TLS or DTLS).

## Statistics

This section displays the following traffic statistics:

- **Total Bytes Sent**: Displays, in real time, bytes of traffic that your mobile device has sent through the Z-Tunnel.
- **Total Bytes Received**: Displays, in real time, bytes of traffic that your mobile device has received through the Z-Tunnel.

[Image: Private Access Screen]

This window includes the following features of Android on ChromeOS devices:

## Connectivity

This section displays the following connectivity information:

- **Username**: Displays the username with which you are logged into the app.
- **Service Status**: Displays the app connection status. Tap **Turn Off** if you want to turn off the Zscaler Tunnel (Z-Tunnel) and disable the Private Access service while remaining logged in to the app. The Private Access service is disabled until you tap **Turn On**.
- **Network Type**: Displays the type of network you're connected to (Trusted Network or Off-Trusted Network).
- **Authentication Status**: Displays whether the user is authenticated to the Private Access service.
- **Authenticate Early**: Click to reauthenticate Private Access before your authentication expires.
- **Broker**: Displays the IP address of the server to which your traffic is being forwarded.
- **Client**: Displays the local IP address of your device.
- **Time Connected**: Displays your initial connection time for the present session.
- **Tunnel Protocol**: Displays the protocol used for the tunnel (TLS or DTLS).

## Statistics

This section displays the following traffic statistics:

- **Total Bytes Sent**: Displays, in real time, bytes of traffic that your mobile device has sent through the Z-Tunnel.
- **Total Bytes Received**: Displays, in real time, bytes of traffic that your mobile device has received through the Z-Tunnel.

[Image: Private Access Window]

This screen includes the following features on iOS devices.

## Zero Trust Connectivity

This section displays the following connectivity information:

- **Username**: Displays the username with which you are logged into the app.
- **Service Status**: Displays the app connection status. Tap **Turn Off** if you want to turn off the Zscaler Tunnel (Z-Tunnel) and disable the Private Access service while remaining logged in to the app. Depending on your organization’s policies, you might be required to enter a password. The Private Access service is disabled until you tap **Turn On**.
- **Network Status**: Displays the type of network you are connected to (Trusted Network, VPN Trusted Network, Split VPN Trusted Network, or Off-Trusted Network).
- **Authentication Status**: Displays whether or not the user is authenticated to the Private Access service.
- **Authenticate Early**: Click **Authenticate Early** to reauthenticate Private Access before your authentication expires.
- **Broker**: Displays the broker Zscaler Client Connector is connected to.
- **Client**: Displays your client IP address.
- **Time Connected**: Displays your initial connection time for the present session.
- **Tunnel Version**: Displays the version of the Zscaler Tunnel (Z-Tunnel) that Zscaler Client Connector forwards your traffic through.
- **Tunnel SDK Version**: The current tunnel SDK version.

## Statistics

This section displays the following traffic statistics:

- **Data Sent**: Displays, in real time, bytes of traffic sent from your mobile device through the Z-Tunnel.
- **Data Received**: Displays, in real time, bytes of traffic received by your mobile device through the Z-Tunnel.

[Image: Private Access Screen]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/viewing-information-about-zscaler-client-connector","lastmod":"2024-07-05T12:05Z","nid":"1363026"} -->
## Viewing Information About Zscaler Client Connector

- Source: https://help.zscaler.com/zscaler-client-connector/viewing-information-about-zscaler-client-connector
- Product: Client Connector
- Path: Zscaler Client Connector Help > End User Guide > Viewing Information About Zscaler Client Connector
- Last modified: 2024-07-05T12:05Z
- Summary: Information on the About menu features on Zscaler Client Connector.

html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/REC-html40/loose.dtd"

?xml version='1.0' encoding='UTF-8'?

In the **About**section of the **More** window in Zscaler Client Connector, you can view information about the app and also update the app or internet security policies on your device using the following fields:

- **App Version**: Displays the app version number.
- **Update App**: You can click or tap to update the app to the latest version approved by your organization if an update has not already occurred. For Android devices, this option is not available. If you think your app needs to be updated, contact your IT administrator.
- **App Policy**: Displays the name of the app profile assigned to the device.
- **Update Policy**: You can click or tap to manually refresh your internet security policies and PAC file.
- **License Agreement**: Applies to macOS. You can click to view the **License Agreement for Zscaler Software**.
- **License and Credits**: Applies to Android and Linux. You can click to view the **License Agreement for Zscaler Software**and **Third-Party Software Notice**.
- **License Agreement and Third Party Software Notice**: Applies to Windows, macOS and iOS. You can click to view the **License Agreement and Third-Party Software Notice**for Zscaler.

The following is a sample screenshot from the Windows platform. The screen might slightly differ based on the OS of your device:

[Image: About Menu for Zscaler Client Connector]

For information about other Zscaler Client Connector features, see [Using Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/viewing-information-about-zscaler-endpoint-dlp-zscaler-client-connector","lastmod":"2026-05-06T11:28Z","nid":"1464141"} -->
## Viewing Information About Zscaler Endpoint DLP on Zscaler Client Connector

- Source: https://help.zscaler.com/zscaler-client-connector/viewing-information-about-zscaler-endpoint-dlp-zscaler-client-connector
- Product: Client Connector
- Path: Zscaler Client Connector Help > End User Guide > Viewing Information About Zscaler Endpoint DLP on Zscaler Client Connector
- Last modified: 2026-05-06T11:28Z
- Summary: Information on the Data Protection page on Zscaler Client Connector.

This article provides an overview of the Data Protection window on Zscaler Client Connector. This window features connectivity information and troubleshooting steps for the Data Protection feature on Windows and macOS devices.

[Image: Data Protection window]

## Connectivity

- **Service Status**: Displays the app connection status. Click **Turn Off** if you want to disable the Data Protection service while remaining logged in to the app. The Data Protection service is disabled until you click **Turn On**. You can disable the service using the [Password to Disable Endpoint DLP](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles#windows) in App Profiles or by using a One-Time Password (OTP). Admins can access an OTP code from the Zscaler Admin Console:
  1. Go to **Infrastructure > Connectors > Client > Device Overview**.
  2. Click the View icon to view the device fingerprint for the enrolled device.
  3. Under **Compliance Status**, copy the password listed under **Disable Endpoint DLP OTP**. See image.
- **Product Version:**Displays the current version of the Data Protection feature.

## Troubleshoot

- **Request exemption**: Users that are blocked by the Endpoint DLP due to a rule violation can request to be exempted from the block action, and instead be monitored. Clicking this option prompts you to enter a password. Admins can access an OTP code from the Zscaler Admin Console: The OTP also works if you are in offline mode. Exemptions are granted for 12 hours.
  1. Go to **Infrastructure > Connectors > Client > Device Overview**.
  2. Click the View icon to view the device fingerprint for the enrolled device.
  3. Under **Compliance Status**, copy the password listed under **Disable Endpoint DLP OTP**.
- **Update DLP Policy**: Click to manually refresh your endpoint DLP policy. The DLP policy refreshes every 15 minutes. If you want to update immediately, click **Update DLP Policy**.
- **Open Quarantine Folder**: Contains the files that moved to this folder as a result of a block action for the personal cloud storage rules (e.g., Dropbox) or by other channels where the file source information is missing (e.g., copying an extract ZIP file to a removable storage device).

[Image: Disable Endpoint DLP OTP]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/viewing-notifications-zscaler-client-connector","lastmod":"2026-05-06T12:02Z","nid":"1370041"} -->
## Viewing Notifications on Zscaler Client Connector

- Source: https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector
- Product: Client Connector
- Path: Zscaler Client Connector Help > End User Guide > Viewing Notifications on Zscaler Client Connector
- Last modified: 2026-05-06T12:02Z
- Summary: Information on the Notifications window of Zscaler Client Connector.

This article provides an overview of the **Notifications**window of Zscaler Client Connector.

- Windows
- macOS
- Linux
- Android
- Android on ChromeOS
- iOS

For information about other Zscaler Client Connector features, see [Using Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector).

This section covers the following topics:

- Viewing Notifications on Windows
- Enabling Notifications in the System Tray

In the Notifications window, you can view the following information:

- **Application**: Displays the name of the application relevant to the action that triggered the notification. In the following example, **Zscaler Client Connector** is listed in the **Application** column when the notification is relevant only to Zscaler Client Connector. For example, you received a notification after the app required you to reauthenticate into the Private Access service, in order to access internal applications. As another example, if you attempted to upload content to Dropbox.com in violation of one of your organization's cloud app control policies in the Internet & SaaS service, you would receive a notification and see Dropbox.com in the **Application** column.
- **Time**: Displays the time when the notification was received.
- **Message**:Displays the notification message.

Click **Clear All** to clear all notifications from this page.

[Image: Notifications Window]

To display notifications from the system tray icon:

1. Click **More**.
2. In the **Settings**section, you can:

- Manually enable or disable [pop-up notifications](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-pop-notifications). The default setting is set by the admin. If notifications are enabled, notifications appear in the tray icon. This setting doesn’t affect Private Access reauthentication notifications.
- Override the default setting that was configured in the Zscaler Admin Console to show Private Access reauthentication notifications.
- Pause notifications by enabling **Do Not Disturb (DND) Mode**. You can pause notifications in 30-minute, 1-hour, or 2-hour increments. If you use Zscaler Client Connector version 4.9, you can select **Enabled** to pause notifications indefinitely until you select **Disabled**.

[Image: Show notifications in Zscaler tray icon]

For Zscaler Client Connector version 3.8 and later for Windows, you can instead use Zscaler's Notification Framework. You cannot disable notifications in Windows settings. To learn more, see [About the Zscaler Notification Framework](https://help.zscaler.com/zscaler-client-connector/about-zscaler-notification-framework).

This section covers the following topics:

- Viewing Notifications on macOS
- Enabling Notifications on the Menu Bar

In the Notifications window, you can view the following information:

- **Application**: Displays the name of the application relevant to the action that triggered the notification. In the following example, **Zscaler Client Connector**is listed in the **Application**column because the notification was relevant only to Zscaler Client Connector. For example, you received the notification after disabling the app's Internet & SaaS service. As another example, if you attempted to upload content to Dropbox.com in violation of one of your organization's cloud app control policies, you would receive a notification and see Dropbox.com in the **Application** column.
- **Time**: Displays the time when the notification was received.
- **Message**:Displays the notification message.

Click **Clear All** to clear all notifications from this page.

[Image: Notifications Window]

To display notifications from the menu bar icon:

1. Click **More**.
2. In the **Settings** section, you can:
  - Manually enable or disable menu bar notifications. The default setting is set by the admin. If notifications are enabled, you see notifications on the app icon.
  - Override the default setting that was configured in the Zscaler Admin Console to show Private Access reauthentication notifications.

[Image: Enabling Zscaler Client Connector for macOS to show menu bar notifications]

This section covers the following topics:

- Viewing Notifications on Linux
- Enabling Notifications in the System Tray

In the Notifications window, you can view the following information:

- **Application**: Displays the name of the application relevant to the action that triggered the notification. **Zscaler**is listed in the **Application**column because the notification was relevant only to Zscaler Client Connector. For example, you received the notification after disabling the app's Internet & SaaS service. As another example, if you attempted to upload content to Dropbox.com in violation of one of your organization's cloud app control policies, you would receive a notification and see Dropbox.com in the **Application** column.
- **Time**: Displays the time when the notification was received.
- **Message**:Displays the notification message.

Click **Clear All** to clear all notifications from this page.

[Image: Notifications Window]

To display notifications in the system tray:

1. Click **More**.
2. In the **Settings** section, you can:
  - Manually enable or disable system tray notifications. The default setting is set by the admin. If notifications are enabled, you see notifications on the app icon. This setting doesn't affect Private Access reauthentication notifications.
  - Override the default setting that was configured in the Zscaler Admin Console to show Private Access reauthentication notifications.

[Image: Enabling Zscaler Client Connector for Linux to show system tray notifications]

This section covers the following topics:

- Viewing Notifications on Android
- Enabling Notifications on the App Icon

In the Notifications screen, you can view the following information:

- The name of the application and the action that triggered it. For example, the following image shows a notification you’d receive after the app’s Private Access service started. As another example, if you attempted to upload content to Dropbox.com in violation of one of your organization’s cloud app control policies, you'd receive a notification with Dropbox.com listed.
- The notification message and the time when it was received.

Tap the **Delete** icon in the top-right corner to clear all notifications from this screen.

Use the drop-down menu to filter the notifications by the predefined time frames. You can view notifications from **Today**, the **Last 7 Days**, the **Last 10 Days**, or view **All** notifications.

[Image: The Notifications screen of Zscaler Client Connector for Android]

To display notifications on the app icon:

1. Tap **More**.
2. In the **Settings** section, you can:
  - Manually enable or disable pop-up notifications. The default setting is set by the admin. If notifications are enabled, notifications appear on the app icon. This setting doesn’t affect Private Access reauthentication notifications.
  - Override the default setting that was configured in the Zscaler Admin Console to enable or disable Private Access reauthentication notifications.

[Image: Enabling notifications for Android devices]

This section covers the following topics:

- Viewing Notifications on Android on ChromeOS
- Enabling Notifications on the App Icon

In the Notifications screen, you can view the following information:

- The name of the application and the action that triggered it. For example, the following image shows a notification you’d receive after the app’s Private Access service started. As another example, if you attempted to upload content to Dropbox.com in violation of one of your organization’s cloud app control policies, you'd receive a notification with Dropbox.com listed.
- The notification message and the time when it was received.

Tap the **Delete** icon in the top-right corner to clear all notifications from this screen.

Use the drop-down menu to filter the notifications by the predefined time frames. You can view notifications from **Today**, the **Last 7 Days**, the **Last 10 Days**, or view **All** notifications.

[Image: The Notifications screen of Zscaler Client Connector for Android on ChromeOS]

To display notifications on the app icon:

1. Tap **More**.
2. In the **Settings** section, you can:
  - Manually enable or disable menu bar notifications. The default setting is set by the admin. If notifications are enabled, you see notifications on the app icon.
  - Override the default setting that was configured in the Zscaler Admin Console to enable or disable Private Access reauthentication notifications.

[Image: Enabling notifications for Android on ChromeOS devices]

This section covers the following topics:

- Viewing Notifications on iOS
- Enabling Notifications on the App Icon

In the Notifications screen, you can view the following information:

- The name of the application relevant to the action that triggered it. In the following example, **Zscaler Client Connector Private Access** is listed because the notification was relevant only to Zscaler Client Connector. For example, you received the notification after the app’s Internet & SaaS service was turned on. For example, if you attempted to upload content to Dropbox.com in violation of one of your organization’s cloud app control policies, you would receive a notification and see Dropbox.com listed.
- The notification message and the time when it was received.

Tap the **Delete** icon in the top-right corner to clear all notifications from this screen.

[Image: Notifications Screen for iOS]

To display notifications on the app icon:

1. Tap **More**.
2. In the **Settings** section, you can enable or disable app icon notifications.

[Image: Enabling notifications for iOS devices]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/viewing-policy-token-zscaler-client-connector-app-profile","lastmod":"2026-06-05T21:06Z","nid":"1328911"} -->
## Viewing the Policy Token for a Zscaler Client Connector App Profile

- Source: https://help.zscaler.com/zscaler-client-connector/viewing-policy-token-zscaler-client-connector-app-profile
- Product: Client Connector
- Path: Zscaler Client Connector Help > Zscaler Client Connector Profile Management > Viewing the Policy Token for a Zscaler Client Connector App Profile
- Last modified: 2026-06-05T21:06Z
- Summary: How to view the policy token, which is automatically generated for each saved Zscaler Client Connector app profile.

After you save your Zscaler Client Connector [app profile](https://help.zscaler.com/zscaler-client-connector/about-zscaler-app-profiles), a policy token is automatically generated for the profile.

You need this policy token if you want to use the [STRICTENFORCEMENT install option](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-app-install-options-msi) which requires users to enroll with Zscaler Client Connector before accessing the Internet. The policy that corresponds with this policy token is enforced for the app until the user enrolls. After the user enrolls, this policy is replaced with the app profile policy that matches the user based on their group affiliation.

To view the policy token for an app profile:

1. Go to **Infrastructure** > **Connectors** > **Client**.
2. Under Platform Settings, select the OS.
3. On the **App Profiles** tab, click the **Edit** icon beside the profile.

[Image: Configured App Profile Policies Example]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/what-is-zscaler-client-connector","lastmod":"2026-03-31T11:28Z","nid":"1285401"} -->
## What Is Zscaler Client Connector?

- Source: https://help.zscaler.com/zscaler-client-connector/what-is-zscaler-client-connector
- Product: Client Connector
- Path: Zscaler Client Connector Help > What Is Zscaler Client Connector?
- Last modified: 2026-03-31T11:28Z
- Summary: Information on Zscaler Client Connector, its key features, and how it works.

Using Zscaler Client Connector, users can get all of the benefits of the Zscaler service for internet traffic, as well as granular, policy-based access to internal resources from a single point.

- With Internet & SaaS, you can protect your users' web traffic even when they are outside your corporate network. You can also protect your users' mobile traffic, whether they're connected to Wi-Fi or cellular networks. The app forwards user traffic to the Zscaler service and ensures that your organization's security and access policies are enforced wherever they might be accessing the internet.
- With Private Access, you can enable your users to securely access enterprise applications from outside the corporate network. Private Access establishes a secure transport for accessing your enterprise apps and services.
- With Zscaler Digital Experience (ZDX), you can monitor your organization’s user devices to detect user experience and productivity issues. ZDX relies on Zscaler Client Connector to perform synthetic probing to a desired Software as a Service (SaaS) application or internet-based service (e.g., OneDrive, Gmail, etc.).
- With Zscaler Endpoint Data Loss Prevention (DLP), you can protect your organization from data loss on endpoints. Endpoint DLP policy complements Zscaler DLP policy by extending the monitoring of sensitive data to the activities that end users take on endpoints (i.e., printing, saving to removable storage, saving to network shares, or uploading to personal cloud storage accounts).

You have the ability to control various settings for the app in the Zscaler Admin Console. The Zscaler Admin Console is dedicated to app management, accessible directly from the Internet & SaaS and Private Access. With administration options, you can configure general settings for the app, such as auto-update and in-app support.

You can also configure app profiles and specify, for example, how the app detects when a user is connected to a trusted network, and if a trusted network is detected, whether the app must disable its service. For greater flexibility, you can configure app profiles so that they apply to all users or to specific groups of users in your organization.

In the Zscaler Admin Console, you can define policies that control how a device forwards traffic to the Zscaler service and which apps, functionality, and content can be accessed from a device. For mobile devices, the Zscaler service also provides per-user and per-department logging and reporting.

After you configure settings and policies in the Zscaler Admin Console, you can silently deploy the app on users' devices for Windows and macOS. You can also deploy the app on users’ devices for Android, Android on ChromeOS, and iOS via MDM. Users need only complete a simple login process to enroll their devices with the Zscaler service.

[Image: Screenshot of the Zscaler Client Connector]

When users enroll, the app downloads the administration settings you've configured, as well as the appropriate app profile, and begins forwarding traffic and protecting users immediately. The app regularly checks for updates to administration options and app profiles, and downloads any changes you make, ensuring the app reflects your latest settings.

For Android devices, Zscaler Client Connector also establishes a proprietary, secure HTTP-tunnel-based VPN to forward the mobile traffic from the user's applications to Zscaler Client Connector. Zscaler Client Connector then sends this traffic to the cloud. Zscaler uses Samsung Approved for Enterprise (SAFE) KNOX APIs for enforceability.

Users might be able to turn off the VPN on non-Samsung Android devices.

When you run Zscaler Client Connector on Android and iOS devices, it also installs the policy that you configured on the Zscaler Admin Console as a profile on your mobile device. Additionally, it enrolls the device in the Zscaler service. After the device is enrolled, the device establishes a local VPN that connects locally to Zscaler Client Connector to direct traffic. As the browser and other applications generate traffic, this is automatically forwarded to the Zscaler cloud.

## Key Features

The following are some key Zscaler Client Connector features and benefits:

- Authentication: The app supports all authentication mechanisms supported by the Zscaler service, except Kerberos. It also supports SAML with two-factor authentication. Your organization's users can seamlessly log in and enroll with their existing user credentials. If you are using the app for Private Access, your organization must use SAML authentication.
- Enforcement: You can configure the [app profile](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app) so that after users enroll, they cannot log out of, disable, or uninstall Zscaler Client Connector without an admin-provided password.
- Trusted Network Detection: The app can detect when users are connecting from a trusted network (for example, from your corporate network) and disable its internet security service so that user traffic is forwarded to the Zscaler service via the network's configured traffic forwarding mechanism. Learn more about [configuring trusted networks](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app).
- Captive Portal Detection: The app can detect when users try to connect to networks where a captive portal requires users to pay or accept a use policy before accessing the web (for example, Wi-Fi networks at airports or hotels). When it detects a captive portal, it can disable its service for a period of time you specify, allowing users to complete the steps necessary to access the network, before automatically re-enabling itself. Learn more about [captive portal detection](https://help.zscaler.com/zscaler-client-connector/configuring-fail-open-settings-zscaler-app).
- SSL Inspection: If you are using Zscaler Client Connector to secure your web traffic, it can automatically install the Zscaler SSL certificate during enrollment so that the Zscaler service can perform SSL inspection on web traffic forwarded by the app. However, you must enable SSL inspection for mobile traffic in the ZIA Admin Portal. This feature applies to the Internet Security service only. Private Access does not support SSL inspection.
- Auto-Update to Latest Release: You can enable auto-updates so that apps on users' devices are automatically updated whenever Zscaler releases a new version. If you prefer to test new app versions before allowing updates, you also have the option of pushing app updates from the Zscaler Admin Console when you're ready. Learn more about [update settings](https://help.zscaler.com/zscaler-client-connector/configuring-update-settings-zscaler-app).
- Easy Administration with the Zscaler Admin Console: In the Zscaler Admin Console, you can easily manage [app profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-profiles) and administration settings. The app checks regularly for updates and downloads any changes you make. If users exit the app, log out and log back into the app, or restart their devices, the app also checks for updates and download changes.
- Dashboards and Device Fingerprint Information: In the Zscaler Admin Console, you can view a dashboard that provides information about devices that have been enrolled with the Zscaler service, including the number of Zscaler Client Connector licenses being used, the device models, platforms, and operating systems on which the app is running, as well as information about which devices are running outdated app versions. You can also view device fingerprint information for all devices that have been enrolled.
- In-App Access to Support: You can provide users with different options for requesting support in Zscaler Client Connector. You can allow users to send support request emails directly from the app to your organization's support team, or you can allow users to submit tickets directly from the app to Zscaler Support. Learn more about [support access](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-support-and-logging-zscaler-app) in Zscaler Client Connector.
- Localization: Zscaler Client Connector supports changing the language of the app user interface based on the system language. To learn more, see [Localization Support](https://help.zscaler.com/zscaler-client-connector/localization-support).

## How Does Zscaler Client Connector Work?

This section describes how Zscaler Client Connector works when you use it to secure your web and mobile traffic. To learn about how the app works when you use it with ZPA to provide secure access to your internal resources, see [What is Private Access?](https://help.zscaler.com/zpa/getting-started/what-zscaler-private-access) To learn about how the app works when you use it with ZDX to monitor your users' experience and productivity issues, see [What is Zscaler Digital Experience?](https://help.zscaler.com/zdx/what-is-zscaler-digital-experience) To learn about how the app works when you use it for Endpoint Data Loss Protection (DLP), see [Zscaler Endpoint Data Loss Prevention (DLP) Integration with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/zscaler-endpoint-data-loss-protection-dlp-integration-zscaler-client-connector) and [About Endpoint Data Loss Prevention](https://help.zscaler.com/zia/about-endpoint-dlp).

When you install Zscaler Client Connector for PC, a Zscaler Network Adapter is also installed on your user's computer. When the user connects to the web, the network adapter captures web traffic from that device. The app then uses geolocation technology to locate the Public Service Edge for Internet & SaaS closest to the user, establishes a lightweight tunnel (called the Z-Tunnel) to the Public Service Edge, and forwards the user's web traffic through the tunnel so that the Public Service Edge can apply appropriate security and access policies.

When you install Zscaler Client Connector on a mobile device, it authenticates the user using your corporate authentication mechanism and completes the following tasks:

- Installs the appropriate app profile
- Installs a VPN profile locally (if not already installed via MDM)
- Registers the mobile device to the Zscaler service

The device then establishes a local VPN tunnel that captures application traffic and directs it to Zscaler Client Connector on the device.

While this is the default behavior of the app, you can modify the app's traffic forwarding settings as necessary. For example:

- Instead of the app automatically determining the Public Service Edge for Internet & SaaS to which it tunnels traffic, you can specify the particular Public Service Edges to which the app must tunnel traffic (for example, you must do this if your organization uses Public Service Edges or Virtual Service Edges).
- If you are running Zscaler Client Connector version 1.4 or later, you can choose multiple destinations for Zscaler Client Connector to send traffic (for example, you can send traffic for a certain domain to a Service Edge or Virtual Service Edge, and send the rest to the geographically closest Public Service Edge for Internet & SaaS.)
- You can choose to allow some traffic (for example, traffic to certain domains like identity federation URLs) to bypass the app tunnel and go directly to the web.

To modify the app's traffic forwarding behavior in these ways, you can add a custom PAC file in your app profile so that the app forwards traffic according to its instructions. The app checks the PAC file regularly to make sure it retrieves the latest one, and whenever it retrieves a new PAC file, it saves that PAC file to your users' computers. This ensures that the PAC file is accessible even after users restart the app or their computers, allowing them to access internal resources and send traffic to private IP ranges even if your organization faces internet connectivity issues.

Whether you use a custom PAC file or have the app forward traffic to the service per its default behavior, the app regularly checks to make sure traffic is forwarded correctly and efficiently. For example, it checks at regular intervals whether the Public Service Edge for Internet & SaaS to which the app is currently tunneling traffic is still the best Public Service Edge for a given user's traffic. It also performs these checks whenever a user changes networks, or restarts the app or their devices.

By default, the app overrides any proxy settings configured on users' browsers so that users cannot manipulate the app's traffic routing. If you prefer to allow users' browser proxy settings to apply, you can do so with your app profile policy.

Zscaler can check IP addresses to avoid IP address conflict. For example, if you are using 100.64.0.0/16 and Zscaler sees a conflicting IP address, Zscaler changes it to 100.65.0.0/16. This change in the IP addresses can range from 100.64.0.0/16 to 100.83.0.0/16.

To learn more about the end user functionality within the app, see [End User Guides](https://help.zscaler.com/zscaler-client-connector/end-user-guides). To start the configuration process for Zscaler Client Connector, see [Signing in to the Zscaler Admin Console](https://help.zscaler.com/unified/signing-zscaler-admin-console) and the [Step-by-Step Configuration Guide for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/why-is-zscaler-client-connector-my-device","lastmod":"2026-06-05T13:44Z","nid":"1308821"} -->
## Why Is Zscaler Client Connector on My Device?

- Source: https://help.zscaler.com/zscaler-client-connector/why-is-zscaler-client-connector-my-device
- Product: Client Connector
- Path: Zscaler Client Connector Help > Why Is Zscaler Client Connector on My Device?
- Last modified: 2026-06-05T13:44Z
- Summary: Information on Zscaler Client Connector for end users, including details about the app, how to use the app, and how to do basic troubleshooting.

[Watch a video about Zscaler Client Connector.](https://fast.wistia.net/embed/iframe/sq0jjgyieg)

Zscaler is a SaaS security platform that provides fast, secure connections between you and your applications, regardless of device, location, or network. Zscaler Client Connector is an application that allows you to receive all of the benefits of the Zscaler service from your device, even when you are off your corporate network. Zscaler Client Connector forwards your traffic to the Internet & SaaS service. It also allows you to use the Private Access, Zscaler Digital Experience (ZDX), and Zscaler Endpoint Data Loss Prevention (DLP) services.

- With the Internet & SaaS service, you can protect your internet traffic and allow your users to securely access the internet. This service scans all traffic in real time to ensure compliance with corporate policies and protection from threats. These threats include viruses, advanced threats, malware, phishing attempts, malicious sites, and more.
- With the Private Access service, you can securely access internal applications and services from any location.
- With the Zscaler Digital Experience (ZDX) service, Zscaler Client Connector performs synthetic probing to a desired Software as a Service (SaaS) application or internet-based service (e.g., OneDrive, Gmail, etc.) to triage and pinpoint the source of performance issues.
- With the Endpoint DLP service, you can protect your organization from data loss on endpoints (i.e., printing, saving to removable storage, saving to network shares, or uploading to personal cloud storage accounts.

## Using Zscaler Client Connector

You must log in to Zscaler Client Connector with your user ID and complete the one-step device enrollment process. Then you can safely connect to the web and your organization's internal resources.

Zscaler Client Connector automatically recognizes when you are connected to a trusted network (i.e., your corporate office network), and depending on your organization's configuration, can disable its Internet & SaaS, Private Access, Zscaler Digital Experience (ZDX), or Endpoint DLP service accordingly. It can also recognize when you connect to Wi-Fi hotspots (i.e., airports, hotels, cafés) where you must pay or accept a use policy before connecting. The app disables its services for a period of time and re-enables itself after you've had a chance to complete the steps necessary to connect.

To learn more about using Zscaler Client Connector and its features, see [Using Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector).

You can view the connection status of the Internet & SaaS, Private Access, ZDX, and Endpoint DLP services on Zscaler Client Connector:

- On the **Internet Security** page, if the **Status** is **ON**, your internet traffic is protected.

See image.

- On the **Private Access** page, if the **Service Status** is **ON**, you can securely access your internal resources.

See image.

- On the **Digital Experience** page, if the **Service Status** is **ON**, your network, application, and device performance are being measured.

See image.

- On the **Data Protection** page, if the **Service Status** is **ON**, endpoint user activity is monitored.

See image.

[Image: The connection status of ZIA on the Zscaler Client Connector]

[Image: The connection status of ZPA on the Zscaler Client Connector]

[Image: The connection status of ZDX on the Zscaler Client Connector]

[Image: The service status of Endpoint DLP on Zscaler Client Connector]

## When does Zscaler protect me?

The Zscaler service protects your internet traffic when you connect to your corporate network or to a public internet connection, depending on your organization's configuration. For example, your organization's configuration might mean that you are protected when connected to the corporate network, but you are not protected when connected from home. Your organization can tell you when your connection is protected by Zscaler .

## What about privacy?

The Zscaler service does not record or store personal data when you browse the internet. The service only inspects your internet traffic for threats when you are connected to your corporate network or when Zscaler Client Connector is enabled. Your company might also choose to exempt non-corporate applications from inspection.

Depending on your organization’s corporate policy, your organization might track your internet browsing activity. Follow your organization’s terms of service (TOS) when browsing the internet.

## Why does my organization need to install a certificate on my device?

If your organization requires it, you might be prompted to install a certificate. This certificate is used to inspect your applications' traffic to protect against security threats. This certificate is typically not used when you access the internet from outside of your organization's network (e.g., home Wi-Fi, cafés, public hotspots) and when you disable Zscaler Client Connector.

## What should I be aware of when I browse the internet?

Follow your organization's TOS when browsing the internet. Your organization has configured policies to protect you from harmful websites, and to restrict access to sites that do not conform with its internal internet usage guidelines. Your organization might also limit bandwidth to streaming websites, in order to ensure access to business-related sites is undisrupted.

## How do I troubleshoot Zscaler Client Connector?

Zscaler Client Connector displays error messages in the **Status**. You can either:

- Click**Retry** to resolve the error.
- Click **Repair App** to resolve the error.

To learn more about connection status errors, see [Zscaler Client Connector: Connection Status Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors).

If you encounter an error that is not in the**Status** and has an error code, contact your organization's support team.

See image.

You must contact your organization's support team if you experience general issues not specific to Zscaler Client Connector.

The **Retry** option appears next to the **Status**.

[Image: The Retry option for Zscaler Client Connector]

The**Repair App** option appears on the **More** page in the **Troubleshoot** menu.

[Image: The Repair option for Zscaler Client Connector]

[Image: A Zscaler Client Connector error]

## Who should I contact if I experience any issues?

If you experience any issues with your internet connection, contact your organization's support team.

For issues with Zscaler Client Connector (if your organization enables this feature), you can send support request emails directly from the app to your organization's support team, or you can submit tickets directly from the app to Zscaler Support. If this feature is unavailable, contact your organization's support team.

To learn how to report issues from Zscaler Client Connector, see [Reporting an issue with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zdx-module-release-summary-2021","lastmod":"2022-10-17T16:17Z","nid":"1379126"} -->
## ZDX Module Release Summary (2021)

- Source: https://help.zscaler.com/zscaler-client-connector/zdx-module-release-summary-2021
- Product: Client Connector
- Path: Zscaler Client Connector Help > Release Notes > Zscaler Client Connector - ZDX Module Release Notes (per OS) > ZDX Module Release Summary (2021)
- Last modified: 2022-10-17T16:17Z
- Summary: Zscaler Client Connector ZDX module summary for updates deployed, per OS and version, in 2021.

This article provides a summary of all new features and enhancements released per operating system (OS) for the Zscaler Digital Experience (ZDX) Module in the Zscaler Client Connector app.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zdx-module-release-summary-2022","lastmod":"2022-10-17T16:17Z","nid":"1388646"} -->
## ZDX Module Release Summary (2022)

- Source: https://help.zscaler.com/zscaler-client-connector/zdx-module-release-summary-2022
- Product: Client Connector
- Path: Zscaler Client Connector Help > Release Notes > Zscaler Client Connector - ZDX Module Release Notes (per OS) > ZDX Module Release Summary (2022)
- Last modified: 2022-10-17T16:17Z
- Summary: Zscaler Client Connector ZDX module summary for updates deployed, per OS and version, in 2022.

This article provides a summary of all new features and enhancements released per operating system (OS) for the Zscaler Digital Experience (ZDX) Module in the Zscaler Client Connector app.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zdx-module-release-summary-2023","lastmod":"2023-02-10T06:06Z","nid":"1440756"} -->
## ZDX Module Release Summary (2023)

- Source: https://help.zscaler.com/zscaler-client-connector/zdx-module-release-summary-2023
- Product: Client Connector
- Path: Zscaler Client Connector Help > Release Notes > Zscaler Client Connector - ZDX Module Release Notes (per OS) > ZDX Module Release Summary (2023)
- Last modified: 2023-02-10T06:06Z
- Summary: Zscaler Client Connector ZDX module summary for updates deployed, per OS and version, in 2023.

This article provides a summary of all new features and enhancements released per operating system (OS) for the Zscaler Digital Experience (ZDX) Module in the Zscaler Client Connector app.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zdx-module-release-summary-2024","lastmod":"2024-03-07T06:06Z","nid":"1473316"} -->
## ZDX Module Release Summary (2024)

- Source: https://help.zscaler.com/zscaler-client-connector/zdx-module-release-summary-2024
- Product: Client Connector
- Path: Zscaler Client Connector Help > Release Notes > Zscaler Client Connector - ZDX Module Release Notes (per OS) > ZDX Module Release Summary (2024)
- Last modified: 2024-03-07T06:06Z
- Summary: Zscaler Client Connector ZDX module summary for updates deployed, per OS and version, in 2024.

This article provides a summary of all new features and enhancements released per operating system (OS) for the Zscaler Digital Experience (ZDX) Module in the Zscaler Client Connector app.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zdx-module-release-summary-2025","lastmod":"2025-03-05T06:06Z","nid":"1516131"} -->
## ZDX Module Release Summary (2025)

- Source: https://help.zscaler.com/zscaler-client-connector/zdx-module-release-summary-2025
- Product: Client Connector
- Path: Zscaler Client Connector Help > Release Notes > Zscaler Client Connector - ZDX Module Release Notes (per OS) > ZDX Module Release Summary (2025)
- Last modified: 2025-03-05T06:06Z
- Summary: Zscaler Client Connector ZDX module summary for updates deployed, per OS and version, in 2025.

This article provides a summary of all new features and enhancements released per operating system (OS) for the Zscaler Digital Experience (ZDX) Module in the Zscaler Client Connector app.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zdx-module-release-summary-2026","lastmod":"2026-07-22T09:55Z","nid":"1534304"} -->
## ZDX Module Release Summary (2026)

- Source: https://help.zscaler.com/zscaler-client-connector/zdx-module-release-summary-2026
- Product: Client Connector
- Path: Zscaler Client Connector Help > Release Notes > Zscaler Client Connector - ZDX Module Release Notes (per OS) > ZDX Module Release Summary (2026)
- Last modified: 2026-07-22T09:55Z
- Summary: Zscaler Client Connector ZDX module summary for updates deployed, per OS and version, in 2026.

This article provides a summary of all new features and enhancements released per operating system (OS) for the Zscaler Digital Experience (ZDX) Module in the Zscaler Client Connector app.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zscaler-app-charles-proxy-interoperability","lastmod":"2022-06-03T12:29Z","nid":"1285506"} -->
## Zscaler Client Connector and Charles Proxy Interoperability

- Source: https://help.zscaler.com/zscaler-client-connector/zscaler-app-charles-proxy-interoperability
- Product: Client Connector
- Path: Zscaler Client Connector Help > Interoperability > Zscaler Client Connector and Charles Proxy Interoperability
- Last modified: 2022-06-03T12:29Z
- Summary: Information on how the macOS version of Zscaler Client Connector interoperates with the Charles Web Debugging Proxy application.

On macOS devices, Zscaler Client Connector is interoperable with the Charles Web Debugging Proxy application. If Charles Proxy is detected, Zscaler Client Connector creates a proxy chain. The app automatically configures external proxy settings in the Charles application so that the user's system routes all traffic through the Charles Proxy first, then sends the traffic on to the app.

Keep in mind that if you install the Charles Proxy after installing Zscaler Client Connector, users must restart the Charles Proxy twice after installation, so that Zscaler Client Connector can automatically modify the Charles Proxy configuration for proxy chaining.

If the you installed Charles Proxy on a user's device before installing Zscaler Client Connector, Zscaler Client Connector works seamlessly and users do not need to restart the Charles Proxy.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zscaler-app-registry-keys","lastmod":"2026-07-29T17:15Z","nid":"1358826"} -->
## Zscaler Client Connector: Windows Registry Keys

- Source: https://help.zscaler.com/zscaler-client-connector/zscaler-app-registry-keys
- Product: Client Connector
- Path: Zscaler Client Connector Help > Troubleshooting > Zscaler Client Connector: Windows Registry Keys
- Last modified: 2026-07-29T17:15Z
- Summary: Information on Zscaler Client Connector registry keys with a list of all possible values and their explanation.

This article defines the values for the `ZNW_State`, `ZPA_State`, `ZWS_State`, and `ZPA_AuthState` registry keys. You can navigate to Zscaler Client Connector registry keys by using the following path: `HKEY_CURRENT_USER\Software\Zscaler\App`.

- This article is not an exhaustive list of all registry entries created by Zscaler Client Connector.
- When a user logs out of Zscaler Client Connector, registry entries are removed.

Use the `ZNW_State` registry key to determine a device’s Zscaler Client Connector network state. This table lists each possible value and its corresponding definition.

| Registry Value | Definition |
| --- | --- |
| TRUSTED_VPN | Connected to a trusted full-tunnel VPN. |
| NON_TRUSTED | Connected to an off-trusted network. |
| TRUSTED | Connected to a trusted network. |
| TRUSTED_SPLIT_VPN | Connected to a split VPN-trusted network. |

This table lists possible registry values and corresponding definitions for the registry keys `ZPA_State`and`ZWS_State,`which represent the Private Access service state and the Internet & SaaS service state, respectively.

| Registry Key(s) | Registry Value | Definition |
| --- | --- | --- |
| ZPA_State ZWS_State | OFF | Service is turned off. |
| ZPA_State ZWS_State | ON | Service is turned on. |
| ZPA_State ZWS_State | CONNECTING | Service is connecting. |
| ZPA_State ZWS_State | NONE_FORWARDING | Traffic is intercepted by network drivers. |
| ZPA_State ZWS_State | TUNNEL_FORWARDING | Traffic is intercepted by network drivers. |
| ZWS_State | LOCAL_PROXY_FORWARDING | Traffic is intercepted through a PAC file (local proxy). |
| ZWS_State | ENFORCE_PROXY_FORWARDING | A PAC file is enforced on the system. |
| ZPA_State ZWS_State | DR_ON | Disaster Recovery (safe mode) is active. Traffic handling follows the settings configured in [App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles). |
| ZWS_State | ADAPTER_DOWN_ERROR | No adapter with a default route is found (might occur during DHCP renewal). Note: Run the ipconfig /all command and verify the lease-obtained timestamp coincides with the exact moment the issue occurs (e.g., Lease Obtained. . . . . . . . . . : Wednesday, 9 February 2022 2:59:42). |
| ZPA_State | SERVICE_DOWN_ERROR | A required microservice is not operational. |
| ZPA_State ZWS_State | CAPTIVE_PORTAL_ERROR | A captive portal is detected, and the open timeout expires. |
| ZPA_State ZWS_State | SERVER_DOWN_ERROR | Cannot reach the Public Service Edge for Private Access or Internet & SaaS. |
| ZPA_State | INTERNET_UNREACHABLE_ERROR | The network is connected but Private Access cannot resolve the broker name. |
| ZPA_State ZWS_State | FIREWALL_BLOCK_ERROR | The outbound/inbound connection to itself fails. |
| ZWS_State | SYSTEM_SOCKETS_EXHAUSTED_ERROR | The system is at the maximum socket limit. |
| ZPA_State ZWS_State | DRIVER_ERROR | A network driver (TAP/TUN/LWF) fails to load. |
| ZPA_State ZWS_State | CAPTIVE_PORTAL_DETECTED | A captive portal is detected. |
| ZPA_State ZWS_State | SAFEMODE_FAILOPEN | Interception stops temporarily to allow authentication. |
| ZWS_State | PRE_ENROLMENT_PROXY_ENFORCEMENT | Strict enforcement is blocking network access until the user logs in to Zscaler Client Connector. |
| ZPA_State ZWS_State | SERVER_AUTH_ERROR | The Public Service Edge does not accept the authentication credentials. |
| ZPA_State ZWS_State | SERVER_AUTH_TERMINATED_AT_UNKNOWN | The user domain doesn’t match the service’s authentication domain. |
| ZPA_State | ZPA_UNTRUSTED_SERVER_CERT_ERROR | The service is in a Private Access Untrusted Server Error state. The Private Access connection receives an SSL exception while connecting. This registry key value is applicable only for Private Access. |
| ZWS_State | ZIA_REGISTRATION_REQUIRED | The device is not registered with Internet & SaaS and the user must register to proceed. |

Use the `ZPA_AuthState` registry key to determine a device’s Private Access authentication status. This table lists each possible value and its corresponding definition.

| Registry Value | Explanation |
| --- | --- |
| AUTHENTICATED | The user has a valid Private Access session. Access proceeds per policy. |
| AUTHENTICATION_REQUIRED | There is no valid Private Access session. The user must sign in or reauthenticate. |
| REGISTRATION_REQUIRED | The device or user is not registered for Private Access and must complete registration or enrollment before authentication. |
| ZPN_DISABLED | Zscaler Client Connector does not initiate Private Access. |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zscaler-app-update-intervals","lastmod":"2026-03-30T14:32Z","nid":"1330276"} -->
## Zscaler Client Connector Update Intervals

- Source: https://help.zscaler.com/zscaler-client-connector/zscaler-app-update-intervals
- Product: Client Connector
- Path: Zscaler Client Connector Help > Zscaler Client Connector Update Intervals
- Last modified: 2026-03-30T14:32Z
- Summary: Information on the automatic update intervals for Zscaler Client Connector.

Zscaler Client Connector automatically updates at the set intervals:

- Every 15 minutes, Zscaler Client Connector downloads the PAC files of the app profiles and forwarding profiles.
- Every 80 minutes, Zscaler Client Connector connects for policy updates from the app profiles, forwarding profiles, and administration settings.

If the PAC file URLs are changed, it automatically updates every 80 minutes because this counts as a profile change.

- Every 2 hours, Zscaler Client Connector checks for software updates.

You cannot configure these intervals. If the user manually updates the policy from their device, Zscaler Client Connector updates immediately.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zscaler-client-connector-and-imprivata-integration","lastmod":"2026-05-05T16:33Z","nid":"1474681"} -->
## Zscaler Client Connector and Imprivata Integration

- Source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-and-imprivata-integration
- Product: Client Connector
- Path: Zscaler Client Connector Help > Platform and Authentication Management > Zscaler Client Connector and Imprivata Integration
- Last modified: 2026-05-05T16:33Z
- Summary: How to integrate Zscaler Client Connector with Imprivata

This feature is available only for Zscaler Client Connector version 4.4 and later for Windows.

Zscaler Client Connector supports seamless integration with Imprivata OneSign. This integration is useful if your users authenticate on shared devices and allows you to:

- Silently log Imprivata users in to and out of Zscaler Client Connector.
- Apply different security policies on the same device based on the user.
- Log activity for each user.

Zscaler and Imprivata are technology partners. To learn more about integrating Zscaler and Imprivata, see the [Zscaler and Imprivata Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-imprivata-deployment-guide).

In the following example, a nurse taps an Imprivata badge to access the shared workstation in Room 1. Zscaler Client Connector silently logs them in, applies the appropriate security policy, and begins tracking end user activity. When the doctor accesses the shared workstation, Zscaler Client Connector logs out the nurse and logs in the doctor. The nurse and doctor can later access the workstation in Room 2, and Zscaler Client Connector applies the same security profiles and logging as in Room 1.

[Image: An example of Imprivata integration]

The Zscaler Client Connector system tray icon does not display when Imprivata users are logged in. Imprivata users can access Zscaler Client Connector from the Start menu. Updates to Zscaler Client Connector or changes in the Zscaler Admin Console that result in temporary downtime of the app do not affect the Imprivata login.

The following recommendations are best practices for integrating Zscaler Client Connector with Imprivata:

- Disable the [Zscaler Notification Framework](https://help.zscaler.com/zscaler-client-connector/using-zscaler-notification-framework) to prevent pop-up messages for users.
- Set your Private Access policies to expire at the same time.

To use Zscaler Client Connector integration with Imprivata:

1. Deploy Zscaler Client Connector with a customized install option. To learn more, see [Customizing Zscaler Client Connector with Install Options for MSI](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi) and [Customizing Zscaler Client Connector with Install Options for EXE](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-exe).
2. Enable Integrated Windows Authentication (IWA). To learn more about enabling IWA, see [Configuring Automatic Private Access Reauthentication](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication).
3. (Optional) If you [use WebView2 authentication](https://help.zscaler.com/zscaler-client-connector/using-webview2-authentication), enter additional domains if the user login domain is different from the IdP domain.
4. View the type of user accessing a device (Windows or Imprivata) on the [Zscaler Client Connector Registered Device Details page](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device).
5. View user activity on the Zscaler Admin Console.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zscaler-client-connector-connection-status-errors","lastmod":"2026-05-07T21:06Z","nid":"1285521"} -->
## Zscaler Client Connector: Connection Status Errors

- Source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors
- Product: Client Connector
- Path: Zscaler Client Connector Help > Troubleshooting > Zscaler Client Connector: Connection Status Errors
- Last modified: 2026-05-07T21:06Z
- Summary: Information on the possible Zscaler Client Connector connection status error messages and how to resolve them.

Zscaler Client Connector displays error messages in the **Service Status**field.

[Image: The Zscaler Client Connector displaying an error message in the Status row]

The following table provides a list of possible error messages, an explanation of the error, and the action users can take to resolve it.

| **Error Message** | **Explanation** | **Required Action** |
| --- | --- | --- |
| Intermediate Authentication Error | A tunnel authentication error has occurred because an intermediate proxy service has intercepted the app authentication request. | No action required. |
| Authenticating... | A tunnel authentication error has occurred because the Public Service Edge for Internet & SaaS is waiting for user configuration. | No action required. |
| Authentication Error | A tunnel authentication error has occurred. | For Internet Security: Click **Retry** to resolve the error. The **Retry** option appears next to the **Status**. For Private Access: Click **Authenticate** to resolve the error. The **Authentication** option appears next to the **Authentication Status**. If the error persists, click **More** and click **Restart** **Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app). If the error continues, log out of Zscaler Client Connector and log in again. If the error persists, contact Zscaler Support. |
| Captive Portal Detected | Zscaler Client Connector is in a fail-open state because Zscaler Client Connector detected a captive portal. | Click the **Open Browser**button to access the internet. The **Open Browser** button appears next to the **Status**. If you don't resolve the captive portal in time, click **Retry** to try again. If the error persists, click **More** and click **Restart Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app). If the error continues, contact Zscaler Support. |
| Captive Portal Error | The user has not resolved the captive portal within the time configured in the Zscaler Admin Console. The error message disappears when the user reconnects. | Click **Retry** and then resolve the captive portal. The **Retry** option appears next to the **Status**. If you don't resolve the captive portal in time, click **Retry** to try again. If the error persists, click **More** and click **Restart Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app). If the error continues, contact Zscaler Support. |
| Chaining Authentication Error | A tunnel authentication error has occurred due to proxy chaining. | For Internet Security: Click **Retry** to resolve the error. The **Retry** option appears next to the **Status**. For Private Access: Click **Authenticate** to resolve the error. The **Authentication** option appears next to the **Authentication Status**. If the error persists, click **More** and click **Restart** **Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app). If the error continues, log out of Zscaler Client Connector and log in again. If the error persists, contact Zscaler Support. |
| Connection Error | The Public Service Edge for Internet & SaaS cannot be reached. | Click **Retry** to resolve the error. The **Retry** option appears next to the **Status**. If the error persists, click **More** and click **Restart Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app). If the error continues, contact Zscaler Support. |
| Driver Error | A Windows driver installation issue has been detected, and the tunnel interface cannot be started. Zscaler Client Connector is in a fail-open state unless you have a [fail-close app profile option](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#zcc-fail-close-settings) enabled. | In the **More** window, click **Repair App**. This option is available in the **Troubleshoot**section. If the error persists, contact Zscaler Support. |
| Endpoint FW/AV Error | The device has a firewall or antivirus program blocking Zscaler Client Connector traffic. Zscaler Client Connector is in a fail-open state unless you have a [fail-close app profile option](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#zcc-fail-close-settings) enabled. | Contact your administrator for any required configuration changes on the device. To learn more, contact Zscaler Support. |
| Fail Open | Zscaler Client Connector is in a fail-open state because Zscaler Client Connector detected Windows safe mode activation. | Restart Windows without safe mode. |
| Fail Close <reason> | Zscaler Client Connector is in a fail-close state because the tunnel interface cannot be started (e.g., a driver error or an endpoint FW/AV error). | Click **Retry** to resolve the error. The **Retry** option appears next to the **Status**. If the error persists, click **More** and click **Restart Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app). If the error continues, contact Zscaler Support. |
| Internal Error | Internal socket problem has been detected. | Click **Retry** to resolve the error. The **Retry** option appears next to the **Status**. If the error persists, click **More** and click **Restart Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app). If the error continues, contact Zscaler Support. |
| Installation Error | Zscaler Client Connector experienced a network error while trying to connect to the Zscaler Digital Experience (ZDX) server. | Click **Retry** to resolve the error. The **Retry** option appears next to the **Status**. If the error persists, click **More** and click **Restart Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app). If the error continues, contact Zscaler Support. |
| Network Error | No network interface is detected. | Click **Retry** to resolve the error. The **Retry** option appears next to the **Status**. If the error persists, click **More** and click **Restart Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app). If the error continues, contact Zscaler Support. |
| Safe Mode | The Zscaler service is down. You'll only have access to critical resources determined by your organization. | No action required. To learn more, see [Configuring Disaster Recovery.](https://help.zscaler.com/zia/configuring-disaster-recovery) |
| Server Error | Zscaler Client Connector is unable to connect to the ZDX cloud. | Check network connectivity. Click **Retry** to resolve the error. The **Retry** option appears next to the **Status**. If the error persists, click **More** and click **Restart Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app). If the error continues, contact Zscaler Support. |
| Untrusted Root Cert | Zscaler Client Connector is unable to validate the Private Service Edge for Private Access root certificate. | Contact Zscaler Support. |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zscaler-client-connector-displays-blank-page","lastmod":"2024-10-18T07:06Z","nid":"1505346"} -->
## Zscaler Client Connector Displays Blank Page

- Source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page
- Product: Client Connector
- Path: Zscaler Client Connector Help > Troubleshooting > Zscaler Client Connector Displays Blank Page
- Last modified: 2024-10-18T07:06Z
- Summary: Information about Zscaler Client Connector displaying a blank page.

Zscaler Client Connector versions 4.3, 4.4, and 4.5 for Windows have a known issue if WebView2 is enabled where embedded web pages can appear blank if display settings have changed or if displays are attached or removed from the system. The issue affects SAML authentication during login, reauthentication, acceptable use policy, license agreement, data loss protection notifications, and self-service notifications.

You can temporarily resolve the issue by restarting the application (terminate the ZSATray.exe process via Task Manager) or restarting the system. The issue is fixed on Zscaler Client Connector version 4.3.0.255, version 4.4.0.346, and version 4.5.0.296 for Windows.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zscaler-client-connector-errors","lastmod":"2026-07-16T21:06Z","nid":"1285866"} -->
## Zscaler Client Connector Errors

- Source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors
- Product: Client Connector
- Path: Zscaler Client Connector Help > Troubleshooting > Zscaler Client Connector Errors
- Last modified: 2026-07-16T21:06Z
- Summary: Information on the error messages that Zscaler Client Connector might display while it is in use.

The following tables provide lists of error messages your user might see on Zscaler Client Connector while the app is in use:

- Cloud Authentication Error Codes
- Cloud Error Codes
- Zscaler Admin Console Error Codes
- Report an Issue Error Codes

| **Error Code** | **Error Message** | **Error Description** | **Resolution** |  |
| --- | --- | --- | --- | --- |
| -1 | Failed to Initialize Authentication: PAC Download Failed. | This error occurs when the device fails to download the PAC file, which stops Zscaler Client Connector from authenticating the user. | Check network connectivity. It is likely that the device could not connect to the cloud when downloading the PAC file. |  |
| -2 | Failed to Initialize Authentication: Invalid Custom PAC File. | This error occurs when the device downloads an invalid PAC file. For example, the format of the PAC file is incorrect. | Check the syntax of the arguments within the PAC file. To learn more, see [Best Practices for Writing PAC Files](https://help.zscaler.com/zia/best-practices-writing-pac-files). |  |
| -3 | Failed to Initialize Authentication: VPN Detected. | This error occurs if Zscaler Client Connector detects an active VPN on the device. | Check the [forwarding profile](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app) configuration. |  |
| -4 | Failed to Initialize Authentication: Authentication Disabled. | This error occurs if your organization has not configured an authentication source. | Check the [Authentication Profile](https://help.zscaler.com/zia/about-authentication-profile) configuration. |  |
| -5 | Failed to Identify Authentication Service. | This error occurs if Zscaler Client Connector cannot determine the configured authentication type. For example, differentiating between a Hosted Database user or an Active Directory user. | Check the [Authentication Profile](https://help.zscaler.com/zia/about-authentication-profile) configuration. |  |
| -6 | Failed to Authenticate: Login Failed. | This error occurs when the user enters incorrect credentials. | Verify if the user’s credentials are correct. |  |
| -7 | Network Connection not Available. | This error occurs when Zscaler Client Connector cannot find an active network on the device. | Search for an active network. If the device is connected to a network, try connecting to another network. |  |
| -8 | Network Connection Failed. | This error occurs when Zscaler Client Connector is unable to connect to the cloud. | Check network connectivity. Go to config.zscaler.com/<Zscaler Cloud Name> to check if you have connected to the Zscaler service. Go to config.zscaler.com/<Zscaler Cloud Name>/zscaler-app and verify that the device can connect to the listed IP addresses. To learn how to find your cloud name, see [Understanding Zscaler Cloud Names](https://help.zscaler.com/unified/understanding-zscaler-cloud-names). |  |
| -9 | Internal Error: Contact Administrator. | This is a generic error. | Export logs and contact Zscaler Support. |  |
| -10 | Internal Error: Contact Administrator. | This is a generic error. | Export logs and contact Zscaler Support. |  |
| -11 | Failed to Authenticate, Credentials are not Valid. | This error occurs when the user enters incorrect credentials. | Verify the user’s credentials. |  |
| -13 | DNS Resolution failed. | This error occurs due to an issue with the hostname conversion to IP address. | Verify that the correct DNS server is configured and that the DNS server is resolving the DNS queries correctly and on time. Use packet capture for further analysis. If the issue persists, export logs and contact Zscaler Support. |  |
| -14 | Internal Error: Contact Administrator. | This is a generic error. | Export logs and contact Zscaler Support. |  |

| **Error Code** | **Error Message** | **Error Description** | **Resolution** |
| --- | --- | --- | --- |
| 1 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This is a generic error. | Export logs and contact Zscaler Support. |
| 2 | Zscaler Internet Security Authentication Error. | This authentication error occurs when the user’s cookie is expired or is no longer valid. | Have the user reauthenticate to Zscaler Client Connector. If the issue persists, export logs and contact Zscaler Support. |
| 3 | Zscaler Internet Security Enrollment Version Error. | This error occurs when the device runs a version that is not supported by the cloud. | Upgrade to the latest version of Zscaler Client Connector. |
| 4 | Zscaler Internet Security Enrollment System Bad Timestamp Error, Please check the system time and ensure that it's accurate. | This error occurs when there is a time mismatch between the device and the server. | Check the system time and ensure that it is accurate. |
| 5 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error occurs when the device does not send its version to the cloud. | Export logs and contact Zscaler Support. |
| 6 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error occurs when the device does not send a timestamp to the server. | Export logs and contact Zscaler Support. |
| 7 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error is triggered by the cloud servers. | Export logs and contact Zscaler Support. |
| 8 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error occurs when the device does not send a cookie to the server. | Export logs and contact Zscaler Support. |
| 9 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error is triggered by the cloud servers. | Export logs and contact Zscaler Support. |
| 10 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error is triggered by the cloud servers. | Export logs and contact Zscaler Support. |
| 11 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error is triggered by the cloud servers. | Export logs and contact Zscaler Support. |
| 12 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error occurs when the Internet & SaaS service is disabled for your organization. This error also occurs if your organization has not subscribed to Zscaler Client Connector license. | Export logs and contact Zscaler Support. |
| 13 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error occurs when a device with an invalid device type connects to the cloud. | Export logs and contact Zscaler Support. |
| 14 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error is triggered by the cloud servers. | Export logs and contact Zscaler Support. |
| 15 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error is triggered by the cloud servers. | Export logs and contact Zscaler Support. |
| 16 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error occurs when the server is provided invalid device information. | Export logs and contact Zscaler Support. |
| 17 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error occurs when the server is provided invalid device information. | Export logs and contact Zscaler Support. |
| 18 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error occurs when the server is provided invalid device information. | Export logs and contact Zscaler Support. |
| 19 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This is a generic error. | Export logs and contact Zscaler Support. |
| 20 | Zscaler Client Connector License not Subscribed. | This error occurs when a device tries to connect to the cloud using an organization that does not exist. | Export logs and contact Zscaler Support. |
| 21 | Zscaler Internet Security Enrollment Error - User has exceeded number of devices limit. | This error occurs when the user tries to enroll more than 16 devices under one username. | From the Zscaler Admin Console, remove devices for that user. To learn more, see [Removing a Device if I Reach the Number of Devices Limit](https://help.zscaler.com/zscaler-client-connector/removing-device-if-i-reach-number-devices-limit). |
| 22 | Zscaler Client Connector License not Subscribed. | This error occurs when the device attempts to connect to the cloud and your organization is not licensed or the status of your account is *Agreement Pending*. | Export logs and contact Zscaler Support. |
| 23 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This is a generic error. | Export logs and contact Zscaler Support. |
| 24 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error is triggered by the cloud servers. | Export logs and contact Zscaler Support. |
| 25 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error is triggered by the cloud servers. | Export logs and contact Zscaler Support. |
| 26 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error occurs when the server is provided invalid device information. | Export logs and contact Zscaler Support. |
| 27 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error occurs when a device sends an invalid authentication token. | Have the user reauthenticate to Zscaler Client Connector. If the issue persists, export logs and contact Zscaler Support. |
| 28 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error occurs when a device does not send an authentication token. | Have the user reauthenticate to Zscaler Client Connector. If the issue persists, export logs and contact Zscaler Support. |
| 1000 | Zscaler Client Connector License not Subscribed. | This error occurs when the device attempts to connect to the cloud and your organization is not licensed or the organization does not exist. | Export logs and contact Zscaler Support. |
| 1001 | Zscaler Client Connector License not Subscribed. | This error occurs when the device attempts to connect to the cloud and your organization is not licensed or the organization does not exist. | Export logs and contact Zscaler Support. |
| 1002 | Failed to Authenticate, Credentials are not Valid. | This error occurs when the user enters the incorrect credentials. | Verify if the user’s credentials are correct. |
| 1003 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This is a generic error. | Export logs and contact Zscaler Support. |
| 1004 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This is a generic error. | Export logs and contact Zscaler Support. |
| 1005 | IdP Authentication Failed, Please Contact Administrator. | This error occurs when the cloud receives an invalid SAML response. | Verify the SAML configuration and check the SAML process end-to-end. |
| 1006 | Zscaler Client Connector Internal Error, Please Contact Administrator | This is triggered when the cloud is unable to process the credentials provided. | Export logs and contact Zscaler Support. |
| 1007 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error is triggered by the cloud servers. | Export logs and contact Zscaler Support. |
| 1008 | LDAP Authentication Failed, Please Contact Administrator. | This error occurs when the Zscaler Authentication Bridge (ZAB) is not connected. | Verify the LDAP configuration and ensure network connectivity. |
| 1009 | LDAP Authentication Failed, Please Contact Administrator. | This is an internal error. | Verify the LDAP configuration and ensure network connectivity. |
| 1010 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error is triggered when the cloud is unable to process the credentials provided by the user. | Verify the LDAP configuration and ensure that the credentials entered by the user are valid. |
| 1011 | Zscaler Client Connector Internal Error, Please Contact Administrator. | This error is triggered when the cloud is unable to process the credentials provided by the user. | Verify the LDAP configuration and ensure that the credentials entered by the user are valid. |
| 1012 | Failed to Authenticate, Please try again. | This error occurs when user information cannot be found. | Verify the user’s credentials. |
| 1013 | LDAP Authentication Failed, Please Contact Administrator. | This error occurs when the user is not found or has been deleted. | Verify the LDAP configuration and ensure network connectivity. |
| 1014 | LDAP Authentication Failed, Please Contact Administrator. | This error occurs when LDAP services are down for the organization. | Verify the LDAP configuration and ensure network connectivity. |
| 1015 | LDAP Authentication Failed, Please Contact Administrator. | This error occurs when the Central Authority (CA) is not ready to authenticate. | Verify the LDAP configuration and ensure network connectivity. |
| 1016 | LDAP Authentication Failed, Please Contact Administrator. | This error is triggered when the cloud fails to communicate with LDAP. | Verify the LDAP configuration and ensure network connectivity. |
| 1017 | LDAP Authentication Failed, Please Contact Administrator. | This error is triggered when the cloud fails to communicate with LDAP. | Verify the LDAP configuration and ensure network connectivity. |
| 1018 | LDAP Authentication Failed, Please Contact Administrator. | This error is triggered when the cloud fails to communicate with LDAP. | Verify the LDAP configuration and ensure network connectivity. |
| 1019 | Failed to Authenticate, Credentials are not Valid. | This error occurs when the user enters the incorrect credentials. | Verify the user’s credentials. |
| 10060 | Network Connection Failed: Check Your Network. | This error occurs when Zscaler Client Connector fails to connect to your network. | Check network connectivity. |
| 10101 | Failed to Discover Service for Given User. | This error occurs when Zscaler Client Connector fails to fetch user cloud information. | Verify that the user's credentials. Verify that the user exists in the user authentication source. Verify your company name is provisioned correctly. |
| 10104 | Service Configuration not Found. | This error occurs when Zscaler Client Connector fails to fetch the policy. | Export logs and contact Zscaler Support. |
| 10108 | Failed to Enroll Device. | This error occurs when the device registration fails either due to an inability to reach the server or an error response from the server. This applies to Internet & SaaS and Private Access. | Check network connectivity. Check if there is a trust post for the cloud status. |
| 10110 | Username not valid. | This error occurs when the user enters a username that cannot be found. | Verify that the user is entering the correct username. Verify that the user exists in the user authentication source. |
| 10111 | Internal Error. | This error occurs due to an issue in the processing of a request. | Retry the failed operation. If the error persists, export logs and contact Zscaler Support. |
| 10112 | Internal Error: Contact Administrator. | This is a generic error. | Export logs and contact Zscaler Support. |

| **Error Code** | **Error Message** | **Error Description** | **Resolution** |
| --- | --- | --- | --- |
| 3005 | Internal Error: Contact Administrator. | This error occurs when the device sends an invalid request. | Ensure that the version of Zscaler Client Connector is current. Check that traffic is not being modified between the device and the server. |
| 3006 | User Password is not Valid. | This error occurs when the user enters a password that does not match the username. | Check if the configuration of the [Authentication Profile](https://help.zscaler.com/zia/about-authentication-profile) is correct. Verify that the user is entering the correct password. |
| 3007 | Username is not Valid. | This error occurs when the user enters a username that cannot be found. | Verify that the user is entering the correct password. Verify that the user exists in the user authentication source. |
| 3008 | User is not Logged in. | This error occurs when the user attempts to log out of Zscaler Client Connector and the app does not have a record of a logged in user. | Have the user exit Zscaler Client Connector and attempt to log in again. |
| 3009 | Password has Expired. | This error occurs when the user’s password has expired. | Reset the password for the user and check the password expiration setting of the [Authentication Profile](https://help.zscaler.com/zia/about-authentication-profile). |
| 3010 | Password is not Valid. | This error occurs when the user attempts to change the password and enters the old password incorrectly. | Verify that the user is entering the old password correctly or reset the password for the user. |
| 3011 | User not Subscribed to Service. | This error occurs when the device attempts to connect to the cloud and your organization is not licensed. | Export logs and contact Zscaler Support. |
| 3012 | Internal Error: Contact Administrator. | This error occurs when the device attempts to connect to the cloud and sends an invalid device identifier that does not match any enrolled device. | Have the user reauthenticate to Zscaler Client Connector. |
| 3013 | Device is not Registered. | This error occurs if the user attempts to connect from a device that is not available in the Zscaler Admin Console. | Have the user reauthenticate to Zscaler Client Connector with the device. |
| 3014 | Service Subscription has Expired. | This error occurs when your organization license has expired. | Contact Zscaler Support. |
| 3015 | Provided Password is not Strong Enough. | This error occurs when the user tries to change the password and the entered password does not meet the password strength requirements. | Have the user enter a more secure password or reset the password for the user. Check the password strength settings of the [Authentication Profile](https://help.zscaler.com/zia/about-authentication-profile). |
| 3016 | PAC URL is not Valid. | This error occurs when an invalid PAC file URL is specified in the forwarding profile. | Verify that the PAC file URL entered in the forwarding profile is correct and resolves to a PAC file. |
| 3017 | PAC File is not Valid. | This error occurs when an invalid PAC file is specified in the forwarding profile. | Check the syntax of the arguments within the PAC file. To learn more, see [Best Practices For Writing PAC Files](https://help.zscaler.com/zia/best-practices-writing-pac-files). |
| 3018 | Already Subscribed to Zscaler service. | This error is triggered on the cloud when attempting to create a new domain that already exists. | Contact Zscaler Support. |
| 3019 | Profile Name Already in Use. | This error occurs when attempting to create an app profile with the same name as an existing app profile. | Change the name of the new app profile or remove the existing app profile. |
| 3020 | Invalid PAC File URL. | This error occurs when an invalid PAC file URL is specified in the forwarding profile. | Verify that the PAC file URL entered in the forwarding profile is correct and resolves to a PAC file. |
| 3021 | Invalid PAC File Content. | This error occurs when an invalid PAC file is specified in the forwarding profile. | Check the syntax of the arguments within the PAC file. To learn more, see [Best Practices For Writing PAC Files](https://help.zscaler.com/zia/best-practices-writing-pac-files). |
| 3022 | Internal Error: Contact Administrator. | This error occurs when the device tries to retrieve an updated profile and already has the latest profile. | Ignore this error. |
| 3023 | Internal Error: Contact Administrator. | This error occurs if the device attempts to download the policy and the download fails. This error also occurs for admins browsing the [forwarding profile](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app) or [app profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-profiles) pages. | Attempt to update the policy from the device again. |
| 3024 | Device not Supported. | This error occurs if the connecting device is not a recognized and supported platform. This error also occurs if a supported platform attempts to connect as a different platform. For example, a Windows device attempting to connect as a macOS device. | Ensure that the user is running the correct and current version of Zscaler Client Connector. |
| 3025 | Internal Error: Contact Administrator. | This error occurs when the device attempts to update its policy with a policy that no longer exists. | Download the policy again. If this fails, log out and reauthenticate to Zscaler Client Connector. |
| 3026 | Internal Error: Contact Administrator. | This error occurs if the session expires while the device downloads a policy. If the download is interrupted and then resumes, it might not be completed. | Ensure the device has connectivity, then update the policy again. |
| 3027 | Internal Error: Contact Administrator. | This error occurs when an iOS device sends an invalid push notification token. | Have the user reauthenticate to Zscaler Client Connector. If this fails, reinstall Zscaler Client Connector. |
| 3049 | Failed to register with Private Access. | This error occurs when the device attempts to register for Private Access and during the certificate signing process, the signing fails. | This error is followed by a more specific Private Access authentication error code. To learn more, see [Zscaler Client Connector: Private Access Authentication Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-private-access-authentication-errors). |
| 3050 | Failed to deregister with Private Access. | This error occurs when removing or deregistering a device from the Zscaler Admin Console and the cloud fails to remove it. | This error is followed by a more specific Private Access authentication error code. To learn more, see [Zscaler Client Connector: Private Access Authentication Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-private-access-authentication-errors). |
| 3051 | Invalid Device. | This error occurs when an iOS or Android device attempts to connect to a macOS or Windows endpoint. | Ensure that the version of Zscaler Client Connector is current. |
| 3053 | Failed to register with Internet & SaaS. | This error occurs when the device attempts to register for Internet & SaaS and fails. | Check if there is a trust post for the cloud status. Contact Zscaler Support. |
| 3054 | Failed to Deregister Device. | This error occurs if the user logs out of the device or the admin removes a device and the cloud fails to remove it. | Export logs and contact Zscaler Support. |
| 3068 | Service is disabled. | This error occurs if the Private Access service is disabled for the user. | Contact Zscaler Support. |
| 3071 | Zscaler Client Connector was unable to enroll at this time. It will automatically retry in 12 seconds. Failed to register with service. | This error occurs when too many requests are logged into the server. The retry time interval value is dynamic and might vary depending on the Zscaler Admin Console's load. | Have the user try logging in again after the retry limit is reached. |
| 3100 | Internal Error: Contact Administrator. | The device is already registered with another tenant under the same cloud. | Remove the device from the prior tenant via the Zscaler Admin Console. Login to the Zscaler Client Connector again to register with the current tenant. If the issue persists, export logs and contact Zscaler Support. |
| 3102 | Device is in quarantined state | The user logged in from a quarantined device. | In the Zscaler Admin Console, go to Infrastructure > Connectors > Client > Partner Devices, then select the View icon of the device. The device is removed from quarantine and the user is allowed to log in. |

| **Error Code** | **Error Message** | **Error Description** | **Resolution** |
| --- | --- | --- | --- |
| 8790 | Failed to report an issue | This is a generic error. | Export logs and contact Zscaler Support. |
| 8791 | Failed to report an issue | This error occurs when the user tries to report an issue and has not entered a username in the Name field of the form. | Export logs and contact Zscaler Support. |
| 8792 | Failed to report an issue | This error occurs when the user tries to report an issue and has entered a username in the Name field of the form that is too long. | Export logs and contact Zscaler Support. |
| 8793 | Failed to report an issue | This error occurs when the user tries to report an issue and has not provided a destination email address. | Export logs and contact Zscaler Support. |
| 8794 | Failed to report an issue | This error occurs when the user tries to report an issue and has provided an email address that is too long. | Export logs and contact Zscaler Support. |
| 8795 | Failed to report an issue | This error occurs when the user tries to report an issue and has not provided a subject. | Export logs and contact Zscaler Support. |
| 8796 | Failed to report an issue | This error occurs when the user tries to report an issue and has provided a subject that is too long. | Export logs and contact Zscaler Support. |
| 8797 | Failed to report an issue | This error occurs when the user tries to report an issue and the list of email addresses in the CC field of the form is too long. | Export logs and contact Zscaler Support. |
| 8798 | Failed to report an issue | This error occurs when the user tries to report an issue and enters a message in the Comments field of the form that is too long. | Export logs and contact Zscaler Support. |
| 8799 | Failed to report an issue | This error occurs when the user tries to report an issue and the internal field for the Problem field is too long. | Export logs and contact Zscaler Support. |
| 8800 | Failed to report an issue | This error occurs when the user tries to report an issue and the value for the Priority field is too long. | Export logs and contact Zscaler Support. |
| 8801 | Failed to report an issue | This error occurs when the user tries to report an issue and the internal value for the account type is too long. | Export logs and contact Zscaler Support. |
| 8802 | Failed to report an issue | This error occurs when the user tries to report an issue and the internally generated ticket ID number is too long. | Export logs and contact Zscaler Support. |
| 8803 | Failed to report an issue | This error occurs when the user tries to report an issue and the internal organization identifier is too long. | Export logs and contact Zscaler Support. |
| 8804 | Failed to report an issue | This error occurs when the user tries to report an issue and the internally provided email address for the admin is too long. | Export logs and contact Zscaler Support. |
| 8805 | Failed to report an issue | This error occurs when the user tries to report an issue and there is no log file to attach. | Export logs and contact Zscaler Support. |
| 8806 | Failed to report an issue | This error occurs when the user tries to report an issue and no log file is present in the submission. | Export logs and contact Zscaler Support. |
| 8807 | Failed to report an issue | This error occurs when the user tries to report an issue and an invalid log file is attached. | Export logs and contact Zscaler Support. |
| 8808 | Failed to report an issue | This error occurs when the user tries to report an issue and the size of the specified log file is too large. | Export logs and contact Zscaler Support. |
| 8809 | Failed to report an issue | This error occurs when the user tries to report an issue and the cloud responds with an invalid response. | Export logs and contact Zscaler Support. |
| 8810 | Failed to report an issue | This error occurs when the user tries to report an issue and the internal value for the application version is too long. | Export logs and contact Zscaler Support. |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zscaler-client-connector-interoperability-apple-icloud-private-relay","lastmod":"2026-04-16T16:50Z","nid":"1529917"} -->
## Zscaler Client Connector Interoperability with Apple iCloud Private Relay

- Source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-interoperability-apple-icloud-private-relay
- Product: Client Connector
- Path: Zscaler Client Connector Help > Interoperability > Zscaler Client Connector Interoperability with Apple iCloud Private Relay
- Last modified: 2026-04-16T16:50Z
- Summary: Describes [[Zscaler Client Connector]] interoperability with Apple iCloud Private Relay

iCloud Private Relay is a security feature available on Apple iOS and macOS devices. It routes internet traffic through Apple’s servers and encrypts client DNS traffic to obfuscate the end user's identity. iCloud Private Relay encrypts client DNS traffic before it is intercepted by Zscaler Client Connector. This interferes with Zscaler Client Connector’s ability to process DNS requests and can disrupt both Internet & SaaS and Private Access traffic flows.

Zscaler recommends disabling iCloud Private Relay when deploying Zscaler Client Connector to iOS and macOS devices to avoid connectivity issues.

## How do I disable iCloud Private Relay?

### End Users

End users can check whether iCloud Private Relay is enabled and then disable iCloud Private Relay on their individual devices. To learn more, refer to the following documentation based on the OS:

- [iOS](https://support.apple.com/en-sg/guide/iphone/iph499d287c2/ios)
- [macOS](https://support.apple.com/guide/mac-help/use-icloud-private-relay-mchlecadabe0/mac)

### Administrators

You can Disable iCloud Private Relay in App Profiles. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#iOS-advanced). Applies to Zscaler Client Connector version 4.4 and later for iOS.

Administrators can return an NXDOMAIN response for the hostnames used by iCloud Private Relay traffic to alert end users that they must disable iCloud Private Relay. To learn more, refer to the *Allow for network traffic audits* section in the [Apple documentation](https://developer.apple.com/icloud/prepare-your-network-for-icloud-private-relay/).

If you use Jamf Pro or another MDM to deploy Zscaler Client Connector, you can restrict iCloud Private Relay by distributing a configuration profile. To learn more, refer to the [Jamf documentation](https://learn.jamf.com/en-US/bundle/jamf-security-cloud-setup-guide/page/Enabling_the_HTTPS_Block_Page_for_Supervised_Apple_Devices.html).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zscaler-client-connector-pop-notifications","lastmod":"2026-05-06T12:32Z","nid":"1506376"} -->
## Zscaler Client Connector: Pop-Up Notifications

- Source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-pop-notifications
- Product: Client Connector
- Path: Zscaler Client Connector Help > End User Guide > Zscaler Client Connector: Pop-Up Notifications
- Last modified: 2026-05-06T12:32Z
- Summary: A list of pop-up notifications that can display for Zscaler Client Connector users

The following table provides a list of pop-up notifications for Zscaler Client Connector.

This list applies only to Zscaler Client Connector version 4.4 and later for Windows and for Zscaler Client Connector version 4.3 and later for macOS.

There are 6 types of notifications:

- **Default**: General notifications about Zscaler Client Connector.
- **Service Status**: Notifications about the status of a Zscaler service, such as when a service is in Disaster Recovery mode.
- **App Updates**: Notifications about the upgrade process for Zscaler Client Connector.
- **Internet & SaaS Notifications**: Notifications from Internet & SaaS, such as Data Loss Prevention (DLP) or device posture.
- **Private Access Reauthentication**: Notifications that users must reauthenticate Private Access.
- **Critical:** Notifications that display regardless of end user notification configuration. These notifications cannot be turned off.

The types of notifications that display are based on the end user notifications configuration in the Zscaler Admin Console. Users can disable or pause notifications. To learn more, see [Configuring End User Notifications for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-end-user-notifications-zscaler-client-connector) and [Viewing Notifications on Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector).

If a customizable [Strict Enforcement Notification](https://help.zscaler.com/zscaler-client-connector/configuring-fail-open-settings-zscaler-client-connector) is configured, an additional notification is displayed if users try to access the internet before enrolling in Zscaler Client Connector.

| Component | Notification Type | Notification Message | User Action | Notes |
| --- | --- | --- | --- | --- |
| Private Access | Private Access Reauthentication | Access to all or certain private applications will expire soon. Reauthenticate before your access expires. | Click **Authenticate Early** on the Private Access window to avoid losing access. |  |
| Private Access | Private Access Reauthentication | Access to certain applications requires you to reauthenticate into Zscaler Client Connector. | Click **Authenticate** on the Private Access window. |  |
| General | App Updates | App has been updated. | None required. |  |
| Private Access | Default | Application access is blocked by Private Access policy. | Contact your organization’s support admin. | Usually indicates a posture block. Customize this message in the Zscaler Admin Console. |
| General | Critical | Captive Portal detected. Please resolve it to access the internet. | Click **Open Browser** to access the internet. |  |
| ZDX | Default | Digital Experience is Enabled as per latest policy. Login again to register with Digital Experience. | Log out of the app and log back in again. |  |
| Internet & SaaS | Internet & SaaS Notification | Data Loss Protection | Click **Learn More** to view a report of the violation. | Customize this message in the Zscaler Admin Console. |
| General | App Updates | Failed to check for updates. Please try again after some time. | Retry the action later. |  |
| General | App Updates | Failed to check for ZDP updates. Please try again after some time. | Retry the action later. |  |
| General | App Updates | Failed to check for ZDX updates. Please try again after some time. | Retry the action later. |  |
| General | Default | Failed to report issue. | None required. |  |
| General | Critical | Finished capturing packets. | None required. |  |
| Internet & SaaS | Service Status | Internet Security is Connected. | None required. | Duplicate messages that display within a 1-minute interval are suppressed. |
| Internet & SaaS | Service Status | Internet Security is Disconnected. | None required. | Duplicate messages that display within a 1-minute interval are suppressed. |
| Internet & SaaS | Service Status | Internet Security is Enabled as per latest policy. Log in again to register with Internet Security. | Log out of the app and log back in again. |  |
| Internet & SaaS | Default | Internet Security is in Driver Error. | Click **Restart Service** on the More window. | Zscaler Client Connector experienced an error after you clicked **Repair App** on the More window. |
| Disaster Recovery | Service Status | Internet Security Safe Mode has started. (Your access to the internet will be limited.) | None required. | Duplicate messages that display within a 12-hour interval are suppressed. |
| Disaster Recovery | Service Status | Internet Security Safe Mode has stopped. | None required. |  |
| General | Default | Issue reported. | None required. |  |
| General | App Updates | Latest available app is already running. | None required. |  |
| General | App Updates | New update is available. App will auto-update now. | None required. |  |
| Private Access | Service Status | Partner Private Access is Connected. | None required. | Duplicate messages that display within a 1-minute interval are suppressed. |
| Private Access | Service Status | Partner Private Access is Disconnected. | None required. | Duplicate messages that display within a 1-minute interval are suppressed. |
| General | Default | Please restart the system for driver repair to complete. | Click **Restart Service** on the More window. |  |
| Private Access | Service Status | Private Access is Connected. | None required. | Duplicate messages that display within a 1-minute interval are suppressed. |
| Private Access | Critical | Private Access is Connected. (Business Continuity mode) | None required. |  |
| Private Access | Critical | Private Access is Connected. (Exited Business Continuity mode) | None required. |  |
| Private Access | Service Status | Private Access is Disconnected. | None required. | Duplicate messages that display within a 1-minute interval are suppressed. |
| Private Access | Default | Private Access is in Driver Error. | Click **Restart Service** on the More window. | Zscaler Client Connector experienced an error after you clicked **Repair App** on the More window. |
| Disaster Recovery | Service Status | Private Access Safe Mode has started. (Your access to the internet will be limited). | None required. | Duplicate messages that display within a 12-hour interval are suppressed. |
| General | Service Status | Service has been administratively disabled. | None required. |  |
| General | Service Status | Services are down because the network is disconnected. | None required. |  |
| General | Critical | Started capturing packets. | None required |  |
| General | Default | The SSL certificate could not be trusted automatically. Please trust the certificate from the keychain. | None required. | Applies to macOS only. |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zscaler-client-connector-private-access-authentication-errors","lastmod":"2026-07-16T21:06Z","nid":"1296846"} -->
## Zscaler Client Connector: Private Access Authentication Errors

- Source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-private-access-authentication-errors
- Product: Client Connector
- Path: Zscaler Client Connector Help > Troubleshooting > Zscaler Client Connector: Private Access Authentication Errors
- Last modified: 2026-07-16T21:06Z
- Summary: Information on the Private Access authentication errors that Zscaler Client Connector might display during the enrollment process.

The table below provides a list of error messages your users might see for Zscaler Client Connector during the enrollment process.

| Error Code | Error Message | Error Description | Resolution |
| --- | --- | --- | --- |
| 2008 | Authentication failed due to an invalid redirection URL. Please try again. | This error occurs when a user delays Private Access authentication. | Authenticate again. If the error persists, contact Zscaler Support. |
| 42000 | Inconsistency in user credentials is detected. Log out of the client and retry. | When the user attempts to reauthenticate to Private Access, this error occurs if: The user enters a different username instead of the one provided during initial enrollment.; The IdP SAML response has a different NameID instead of the one sent during initial enrollment. | Verify that the user has entered the username provided during initial enrollment, and have the user retry authentication. If the error persists, verify that the IdP SAML response has the NameID that Private Access received during initial enrollment. You can also have the user log out from Zscaler Client Connector and attempt to re-enroll into Private Access. |
| 42001 | Internal Error: Contact Administrator | This error occurs when a user attempts to log in to Zscaler Client Connector without a domain name. In this case, Private Access cannot identify the user's organization and as a result cannot proceed further. This is not applicable if you have specifically configured the App installer so that the domain name is not required in the username field. | Verify that the user has entered a valid domain as part of the username (for example, joe@safemarch.com). This is not applicable if you have specifically configured the App installer so that the domain name is not required in the username field (in this case, the user would only enter joe). |
| 42002 | ZscalerPrivate Access is not configured for your company. | This error occurs when Private Access is not configured correctly and is unable to identify the IdP that must be used for enrolling the user. | Verify that an IdP is configured for Private Access and that the IdP can communicate with Private Access. Prior to enrolling users with Zscaler Client Connector, verify that your [IdP Configuration is correctly set up for SSO.](https://help.zscaler.com/zpa/configuring-idp-single-sign) |
| 42004 | Internal Error: Contact Administrator | This error occurs if Zscaler Client Connector is not sending the expected information to Private Access during the user's enrollment process. | Verify that single sign-on (SSO) for Private Access has been configured correctly. Prior to enrolling users with Zscaler Client Connector, verify that your [IdP Configuration is correctly set up for SSO.](https://help.zscaler.com/zpa/configuring-idp-single-sign) |
| 42005 | Internal Error: Contact Administrator | This error occurs if Private Access cannot correctly interpret the information sent by Zscaler Client Connector during the user's enrollment process. | Verify that SSO for Private Access has been configured correctly. Prior to enrolling users with Zscaler Client Connector, verify that your [IdP Configuration is correctly set up for SSO.](https://help.zscaler.com/zpa/configuring-idp-single-sign) |
| 42006 | Internal Error: Contact Administrator | This error occurs when the SAML response validation fails. The failure could be due to issues like the system clock being out of sync, an expired IdP certificate, a failure in the SAML response signature validation, or issues around IdP lookup by IdP entity ID. | Verify that SSO for Private Access has been configured correctly. Prior to enrolling users with Zscaler Client Connector, verify that your [IdP Configuration is correctly set up for SSO.](https://help.zscaler.com/zpa/configuring-idp-single-sign) |
| 42007 | Internal Error: Contact Administrator | This error occurs when the certificate signing request in Private Access fails during the user enrollment process. | Verify that the [signing certificate](https://help.zscaler.com/zpa/about-signingcerts) chosen for enrolling the user device to Private Access is valid. Private Access provides a default signing certificate that can be used when enrolling user devices to Private Access. Check the Zscaler Admin Console to ensure this certificate is still available, or if a new, valid certificate has been added in its place. If the error persists, contact Zscaler Support. |
| 42010 | Internal Error: Contact Administrator | This error occurs if Private Access does not receive the expected information during the user enrollment process. | This is an internal error. Contact Zscaler Support. |
| 42013 | The message is not of the SAML response object type. | This error occurs if the IdP SAML response doesn’t match the expected SAML response object type. | Update the IdP configuration to send the expected object type in the SAML response. |
| 42014 | The SAML response status is unsuccessful. | This error occurs if the status in the SAML response is unsuccessful. | Review the user’s information in the IdP and have the user retry logging in. |
| 42015 | Failed to validate the SAML response signature. | This error occurs if: This error occurs if the IdP certificates aren't configured correctly, and the Private Access authentication service needs to validate the SAML response signature.; The public certificate used by Private Access to validate the SAML response from the IdP has expired. | Verify that the [IdP certificates are configured](https://help.zscaler.com/zpa/configuring-idp-single-sign) correctly in Private Access. Check the expiration date of the IdP certificate, and upload a valid certificate if the current certificate has expired. |
| 42016 | The response issue time is either too old or with date in the future. **IdP Issue Time**: [Timestamp]s **Accepted Range**: [Timestamp]s to [Timestamp]s | This error occurs if the IdP and the Private Access authentication service clocks have a large skew. The maximum accepted skew time is 120 seconds. | Ensure the value for the response issue time is in the accepted range. |
| 42017 | The IdP originated SSO is not supported. | This error occurs if the IdP sends Private Access a SAML response without the Private Access authentication service initiating it. | Only the service provider (SP) initiated SSO is supported with Private Access. |
| 42018 | Failed to look up the SAML request corresponding to the SAML response received. | This error occurs if the Private Access authentication service failed to look up the SAML request corresponding to the SAML response from its database. | Contact Zscaler Support. |
| 42019 | The intended destination doesn't match any of the configured endpoints. | This error occurs if the assertion consumer endpoint of the Private Access authentication service isn't properly configured in the IdP. | Review the SP configuration in your IdP. |
| 42020 | Failed to validate the issuer in the SAML response. | This error occurs if the IdP entity ID isn't properly configured in the Zscaler Admin Console. The entity ID is case sensitive. | In the Zscaler Admin Console, review the entity ID of the IdP configuration. |
| 42021 | Possible error messages: The assertion is too old to be used.; The assertion failed the validation because of the set notBefore condition.; The assertion failed the validation because of the set notOnOrAfter condition. | This error occurs if the Private Access authentication service failed to validate the assertions in the SAML response. The assertion validation may fail due to timing issues, unsupported assertion conditions (e.g., OneTimeUse condition), etc. | Ensure the value for the response issue time is in the valid range. |
| 42022 | Missing NameID in the SAML response. | This error occurs if the SAML response doesn't have NameID in it. | In the IdP configuration, ensure NameID is part of the subject in the SAML response message. |
| 42023 | The CA certificate (signing certificate) for Zscaler Client Connector has expired. | This error occurs if the Central Authority (CA) certificate for Zscaler Client Connector has expired. | Provision a valid CA certificate for Zscaler Client Connector. To learn more, see [Understanding Preloaded Enrollment (CA) Certificates](https://help.zscaler.com/zpa/understanding-preloaded-enrollment-ca-certificates). |
| 42024 | The CA certificate (signing certificate) for Zscaler Client Connector is missing. | This error occurs if the CA certificate for Zscaler Client Connector is missing. | Provision a valid CA certificate for Zscaler Client Connector. To learn more, see [Understanding Preloaded Enrollment (CA) Certificates](https://help.zscaler.com/zpa/understanding-preloaded-enrollment-ca-certificates). |
| 42025 | The private key for the Zscaler Client Connector CA certificate (signing certificate) is missing. | This error occurs if the private key for the Zscaler Client Connector CA certificate is missing. | Provision a valid CA certificate for Zscaler Client Connector. To learn more, see [Understanding Preloaded Enrollment (CA) Certificates](https://help.zscaler.com/zpa/understanding-preloaded-enrollment-ca-certificates). |
| 42026 | Unable to secure a valid certificate for this user. | This error occurs if Zscaler Client Connector fails to get a valid certificate. | Contact Zscaler Support. |
| 42027 | Your organization has reached the limit for the maximum number of allowed users. | This error occurs if your organization has provisioned more users than the number allowed by its subscription. | Verify that the existing Private Access subscription meets the needs of your organization. |
| 42028 | Unexpected or missing information when enrolling or unenrolling Zscaler Client Connector. | This error occurs if the Private Access authentication service receives a request from Zscaler Client Connector with missing or unexpected information. | Contact Zscaler Support. |
| 42029 | Unable to identify the user by domain from the provided username. | This error occurs if the user's username doesn't have a domain that is associated with the organization. | Contact Zscaler Support. |
| 42030 | Unable to look up the user’s organization information. | This error occurs due to missing information in the account associated with the Private Access service. | Contact Zscaler Support. |
| 42031 | Unable to authorize Zscaler Client Connector enrollment request. | This error occurs due to missing information in the account associated with the Private Access service. | Contact Zscaler Support. |
| 42032 | The Private Access authentication service doesn’t support the OneTimeUse condition in the SAML assertion. | This error occurs if the IdP issues a SAML assertion with the OneTimeUse condition. | Update the IdP configuration to not issue OneTimeUse SAML assertion. |
| 42033 | Private Access SP was not able to validate the SAML response. Please check the IdP configuration on both Private Access and your IdP. | This error occurs when the Private Access service cannot validate the SAML response for the Private Access admin. | Verify that an IdP is configured for Private Access administrator SSO and that the IdP can communicate with Private Access. Prior to enrolling users with Zscaler Client Connector, verify that your [IdP Configuration is correctly set up for SSO](https://help.zscaler.com/zpa/configuring-idp-single-sign). |
| 42034 | Private Access SP was not able to validate the SAML response. Please check the IdP configuration on both Private Access and your IDP. | This error occurs when the Private Access service cannot validate the SAML response for the Private Access user. | Verify that an IdP is configured for Private Access user SSO and that the IdP can communicate with Private Access. Prior to enrolling users with Zscaler Client Connector, verify that your [IdP Configuration is correctly set up for SSO.](https://help.zscaler.com/zpa/configuring-idp-single-sign) |
| 42035 | User not authorized because of domain mismatch. Please check configuration on both Private Access and your IdP. | This error occurs if the user’s username domain doesn’t match any domains associated with the organization. | Contact Zscaler Support. |
| 42036 | Unable to verify the IdP configuration for the IdP entity ID. | This error occurs when the Private Access service cannot verify the entity ID for the IdP configuration. | In the Zscaler Admin Console, review the entity ID of the IdP configuration. |
| 42037 | IdP is not enabled for admin SSO.Please check the IdP for user admin SSO in your IDP configuration. | This error occurs when the IdP isn’t enabled for the admin SSO. | Verify that SSO for Private Access is configured correctly. Prior to enrolling users with Zscaler Client Connector, verify that your [IdP Configuration is correctly set up for admin SSO](https://help.zscaler.com/zpa/configuring-idp-single-sign). |
| 42038 | Failed to insert into Object Store. | This is an internal error. | Contact Zscaler Support. |
| 42039 | Unable to verify the SP configuration for this domain. | This error occurs when the Private Access service cannot verify the service provider (SP) configuration for the domain. | Verify that the SP for IdP has been configured correctly. Prior to enrolling users with Zscaler Client Connector, verify that your [IdP Configuration is correctly set up for SSO.](https://help.zscaler.com/zpa/configuring-idp-single-sign) |
| 42040 | Failed to encrypt. | This is an internal error. | Contact Zscaler Support. |
| 42042 | Configured IdP is disabled for SSO. | This error occurs when IdP is disabled on Private Access. | Go to Administration > Identity > Private Access > IDP Configuration to enable the IdP. |
| 42043 | IdP configuration is incomplete. | This error occurs when the IdP is misconfigured. | Verify configuration.[/zscaler-client-connector/using-zscaler-app-portal-identity-provider](https://help.zscaler.com/zscaler-client-connector/using-zscaler-app-portal-identity-provider) To learn more, see [Using the Zscaler Admin Console as an Identity Provider](https://help.zscaler.com/zscaler-client-connector/using-zscaler-app-portal-identity-provider). |
| 42044 | IdP configuration has mismatched SSO type/usage. | This error occurs when the SSO type and usage for the IdP configuration do not match. | Verify that SSO for Private Access is configured correctly. Prior to enrolling users with Zscaler Client Connector, verify that your [IdP Configuration is correctly set up for SSO.](https://help.zscaler.com/zpa/configuring-idp-single-sign) |
| 42045 | Zscaler Private Access: SAML Assertion input too large. | This error occurs if the IdP issues a SAML assertion that is larger than expected. | Contact Zscaler Support. |
| 42046 | All the signing certificates associated with the IdP are expired. | This error occurs when a user tries to log in to Zscaler Client Connector and the IdP’s signing certificates in Private Access have expired. | Update the [IdP configuration](https://help.zscaler.com/zpa/configuring-idp-single-sign) to upload a valid signing certificate from the IdP. |
| 42047 | The SAML request signing certificate has expired. | This error occurs if the SAML request signing certificate configured in Zscaler Admin Console has expired. | Edit the IdP configuration to change the certificate used by the IdP to another certificate, and then update the IdP. To learn more, see [Managing a Service Provider Certificate Rotation](https://help.zscaler.com/zpa/managing-service-provider-certificate-rotation). |
| 42048 | The SAML request signing certificate is invalid. | This error occurs if the SAML request signing certificate configured in Zscaler Admin Console is not valid. | Edit the IdP configuration to change the certificate used by the IdP to another certificate, and then update the IdP. To learn more, see [Managing a Service Provider Certificate Rotation](https://help.zscaler.com/zpa/managing-service-provider-certificate-rotation). |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zscaler-client-connector-processes-allowlist","lastmod":"2026-08-02T07:06Z","nid":"1285511"} -->
## Zscaler Client Connector Processes to Allowlist

- Source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist
- Product: Client Connector
- Path: Zscaler Client Connector Help > Interoperability > Zscaler Client Connector Processes to Allowlist
- Last modified: 2026-08-02T07:06Z
- Summary: Information on Zscaler Client Connector binaries and processes that the users' devices should allowlist.

Zscaler recommends that your users' devices have inbound rules that allow the Zscaler Client Connector binaries and processes.

For some endpoint protection products like antivirus and personal firewalls, you might need to update additional allowlists for full Zscaler Client Connector functionality. This article covers processes and firewall rules that should be allowed.

Because Zscaler Client Connector modifies the networking component of the operating system, a Symantec Tamper Protection can trigger a false positive for the Zscaler service. While you can ignore this warning, you must update the allowlist for Symantec Tamper Protection.

## Allowlist Processes

This section lists the file paths to allowlist for Zscaler Client Connector.

- Windows
- macOS
- Linux

The location of files to allowlist depends on your version of Zscaler Client Connector.

If your organization uses [Group Policy Object (GPO)](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-active-directory-windows#Create-Group-Policy) to push policies on both Windows 32-bit and Windows 64-bit systems, you must add the file paths of both versions to your GPO policy.

- Zscaler Client Connector for Windows: 32-bit version
- Zscaler Client Connector for Windows: 64-bit version

`%ProgramFiles(x86)%` and `%ProgramFiles%` are environmental variables that represent the drive where the Windows program files are located. Typically, program files are located on the C drive. However, there are exceptions. For example, on Amazon WorkSpaces, program files are on the D drive.

Allowlist the entire path of the following files:

These files reside in the `%ProgramFiles(x86)` folder even if you are running the 32-bit version of Zscaler Client Connector on a 64-bit system.

- `%ProgramFiles(x86)%\Zscaler\ZSAHelper\ZSAHelper.exe`
- `%ProgramFiles(x86)%\Zscaler\ZSATray\ZSATray.exe`
- `%ProgramFiles(x86)%\Zscaler\ZSATrayManager\ZSATrayManager.exe`
- `%ProgramFiles(x86)%\Zscaler\ZSATunnel\ZSATunnel.exe`
- `%ProgramFiles(x86)%\Zscaler\ZSAService\ZSAService.exe`
- `%ProgramFiles(x86)%\Zscaler\ZSAZDP`
- `%ProgramFiles(x86)%\Zscaler\ZSAUpdater\ZSAUpdater.exe`
- `%ProgramFiles(x86)%\Zscaler\ZSAUpm\ZSAUpm.exe`
- `%ProgramFiles(x86)%\Zscaler\Updater\zscalerappupdater.exe`
- `%ProgramFiles(x86)%\Zscaler\Updater\zscalerchecksumverifier.exe`
- `%ProgramFiles(x86)%\Zscaler\ThirdParty\TAPDriver\x86\Zscaler-Network-Adapter-1.0.1.0.exe`
- `%ProgramFiles(x86)%\Zscaler\ThirdParty\TAPDriver\x86\Zscaler-Network-Adapter-1.0.2.0.exe`
- `%ProgramFiles(x86)%\Zscaler\ThirdParty\TAPDriver\x86\Zscaler-Network-Adapter-Win10-1.0.2.0.exe`
- `%ProgramData%\Zscaler`
- `%WINDIR%\system32\`
- `%ProgramFiles%\Zscaler\ZDP\ZDPService.exe`
- `%ProgramFiles%\Zscaler\ZDP\ZDPClassifier.exe`
- `%ProgramFiles%\Zscaler\ZDP\ZDPApp.exe`
- `%ProgramFiles%\Zscaler\ZDP\ZDPResources.exe`

If you are on a 64-bit device and install a 32-bit Zscaler Client Connector, the ZDPInstaller is installed in the 64-bit folder. If you are on a 32-bit device and install a 32-bit Zscaler Client Connector, the ZDPInstaller is not downloaded and installed.

- `%ProgramFiles%\Zscaler\ZDP`
- `%ProgramFiles%\Zscaler\ZEPInstaller`
- `%ProgramFiles(x86)%\Microsoft\EdgeWebView\Application\<``version number``>\msedgewebview2.exe`

Allowlist the entire path of the following files:

- `%ProgramFiles%\Zscaler\ZSAHelper\ZSAHelper.exe`
- `%ProgramFiles%\Zscaler\ZSATray\ZSATray.exe`
- `%ProgramFiles%\Zscaler\ZSATrayManager\ZSATrayManager.exe`
- `%ProgramFiles%\Zscaler\ZSATunnel\ZSATunnel.exe`
- `%ProgramFiles%\Zscaler\ZSAService\ZSAService.exe`
- `%ProgramFiles%\Zscaler\ZSAUpdater\ZSAUpdater.exe`
- `%ProgramFiles%\Zscaler\ZSAUpm\ZSAUpm.exe`
- `%ProgramFiles%\Zscaler\Updater\zscalerappupdater.exe`
- `%ProgramFiles%\Zscaler\Updater\zscalerchecksumverifier.exe`
- `%ProgramFiles%\Zscaler\ThirdParty\TAPDriver\x64\Zscaler-Network-Adapter-1.0.1.0.exe`
- `%ProgramFiles%\Zscaler\ThirdParty\TAPDriver\x64\Zscaler-Network-Adapter-1.0.2.0.exe`
- `%ProgramFiles%\Zscaler\ThirdParty\TAPDriver\x64\Zscaler-Network-Adapter-Win10-1.0.2.0.exe`
- `%ProgramFiles%\Zscaler\ThirdParty\ZSFFutil\x64\zsffutil.exe`
- `%ProgramFiles(x86)%\Zscaler\ThirdParty\CertUtil\certutil.exe`
- `%ProgramFiles(x86)%\Zscaler\ThirdParty\Filechecksum\fciv.exe`
- `%ProgramFiles(x86)%\Zscaler\ThirdParty\TAPDriver\Zscaler-Network-Adapter-1.0.1.0.exe`
- `%ProgramFiles(x86)%\Zscaler\ThirdParty\TAPDriver\Zscaler-Network-Adapter-1.0.2.0.exe`
- `%ProgramFiles(x86)%\Zscaler\ThirdParty\TAPDriver\Zscaler-Network-Adapter-Win10-1.0.2.0.exe`
- `%ProgramFiles(x86)%\Zscaler\ThirdParty\ZSFFutil\zsffutil.exe`
- `%ProgramFiles%\Zscaler\ThirdParty\WebView2\MicrosoftEdgeWebview2Setup.exe`
- `%ProgramFiles%\Zscaler\ThirdParty\CertUtil\certutil.exe`
- `%ProgramFiles%\Zscaler\ThirdParty\Filechecksum\fciv.exe`
- `%ProgramData%\Zscaler`
- `%ProgramFiles%\Zscaler\ZDP`
- `%ProgramFiles%\Zscaler\ZDP\ZDPService.exe`
- `%ProgramFiles%\Zscaler\ZDP\ZDPClassifier.exe`
- `%ProgramFiles%\Zscaler\ZDP\ZDPApp.exe`
- `%ProgramFiles%\Zscaler\ZDP\ZDPResources.exe`
- `%ProgramFiles%\Zscaler\ZSAZDP`
- `%ProgramFiles%\Zscaler\ZEPInstaller`
- `%ProgramFiles(x86)%\Zscaler\ThirdParty\ZSFFutil\x86\zsffutil.exe`
- `%ProgramFiles(x86)%\Microsoft\EdgeWebView\Application\<``version number``>\msedgewebview2.exe`

Allowlist the entire path of the following files:

- `/Applications/Zscaler/Zscaler.app/`
- `/Applications/Zscaler/.Updater/autoupdate-osx.app/Contents/MacOS/ZscalerUpdater`

This file is required only for Zscaler Client Connector version 3.7 and earlier for macOS.

- `/Library/Application Support/Zscaler/UPM/UPMServiceController`
- `com.zscaler.zscaler`

`com.zscaler.zscaler` is the Zscaler Client Connector identifier.

- `/Library/Application Support/Zscaler/ZDP`
- `com.zscaler.zscaler.TRPTunnel` This file is required only if you use Transparent Proxy-based Traffic Interception.

You must also include all binaries (executables) under each path.

Allowlist the entire path of the following files:

- `/opt/zscaler/bin/zsaservice`
- `/opt/zscaler/bin/zstunnel`
- `/opt/zscaler/bin/ZSTray`
- `/opt/zscaler/bin/zsupdater`
- `/opt/zscaler/ZSAUpm/bin/ZSAUpm`

## Bypasses for Firewall

If you have a GPO-managed or AV-managed host firewall, you can configure firewall rules on your endpoint protection product for `ZSATunnel.exe` processes for all ports, protocols, network interfaces, and network addresses (e.g., 0.0.0.0/0).

Zscaler Client Connector also uses carrier-grade NAT range 100.64.0.0/16 as part of internal health checking and for the Private Access service.

Zscaler can check IP addresses to avoid IP address conflict. For example, if you are using 100.64.0.0/16 and Zscaler sees a conflicting IP address, Zscaler changes it to 100.65.0.0/16. This change in the IP addresses can range from 100.64.0.0/16 to 100.83.0.0/16.

You can bypass the processes listed under the following platforms in your firewall rules:

- Windows
- macOS
- Linux

- `ZSATunnel.exe: Inbound`
- `ZSATunnel.exe: Outbound`
- `ZSATray.exe: Outbound`
- `ZSATrayManager.exe: Outbound`
- `ZSAUpdater.exe: Outbound`
- `ZSAService.exe: Outbound`
- `Zscalerappupdater.exe: Outbound`
- `ZDPService.exe: Outbound`
- `ZEPInstaller.exe: Outbound`

Zscaler Client Connector automatically adds required firewall rules to the Windows Defender Firewall. However, if the Local Policy Merge GPO setting is enabled, the rules are ignored and you must set up the firewall rules via the GPO. If you use Microsoft Defender Antivirus, you must configure any excluded paths and excluded processes from the allowlist as custom exclusions. To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/defender-endpoint/configure-exclusions-microsoft-defender-antivirus).

- `Zscaler: Inbound`
- `Zscaler: Outbound`
- `ZscalerService: Inbound`
- `ZscalerService: Outbound`
- `ZscalerTunnel: Inbound`
- `ZscalerTunnel: Outbound`
- `ZscalerUpdater: Outbound`
- `UPMServiceController: Inbound`
- `UPMServiceController: Outbound`
- `/Applications/Zscaler/.Updater/autoupdate-osx.app/Contents/MacOS/ZscalerUpdater: Inbound` `/Applications/Zscaler/.Updater/autoupdate-osx.app/Contents/MacOS/ZscalerUpdater: Outbound`
- `/Library/Application Support/Zscaler/ZDP/bin/zdpd.app/Contents/MacOS/zdpd: Outbound`

If you are receiving a local FW/AV error after entering the rules, examine the `%windir%\system32\logfiles\firewall\pfirewall.log` to verify that there are no *drop* entries for 100.64 addresses. If you see *drop* entries, the Windows Firewall rules are not applied correctly to allow Zscaler to communicate. You must revise the rules based on the processes listed earlier.

Linux provides several firewall frameworks that operate primarily at the network level (IP, port, interface), each offering varying levels of abstraction and functionality. All these frameworks leverage the underlying Netfilter kernel infrastructure.

Here are the common firewall tools used across Linux distributions:

- **iptables**: A widely used tool for packet filtering and firewall management.
- **nftables**: A modern replacement for iptables, offering improved performance and flexibility.
- **firewalld**: A daemon that uses nftables or iptables as its backend, commonly used in RPM-based distributions.
- **ufw**: The Uncomplicated Firewall, a user-friendly tool commonly used in Ubuntu.

The Zscaler Client Connector for Linux relies on the iptables command-line tool to configure the necessary rules within Netfilter.

## Firewall Hardening Guidelines for Linux Endpoints

The following example shows you how to harden your firewall for non-Zscaler traffic without affecting Zscaler Client Connector functionality. You must enter the commands in the terminal window in the specified order:

1. Delete all rules: `sudo iptables -F`

Although optional, performing step 1 ensures the following steps work properly.

1. Allow all outgoing connections: `sudo iptables -I OUTPUT -j ACCEPT`
2. Allow loopback connections: `sudo iptables -A INPUT -i lo -j ACCEPT`
3. Allow Zscaler Client Connector internal connections to the Zscaler Client Connector adapter: `sudo iptables -I INPUT -d 100.64.0.1 -j ACCEPT`
4. Allow DHCP traffic:
  - `sudo iptables -I INPUT -p udp --dport 68`
  - `sudo iptables -I OUTPUT -p udp --dport 67`
5. Allow SSH traffic (optional, comment out if not needed):
  - `sudo iptables -A INPUT -p tcp --dport ssh -j ACCEPT`
  - `sudo iptables -A OUTPUT -p tcp --sport ssh -j ACCEPT`
6. Allow established and related incoming connections: `sudo iptables -A INPUT -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT`
7. Deny everything else incoming: `sudo iptables -A INPUT -j DROP`

## Processes Usage

The following list describes what each process is used for:

- **ZSAHelper**: An internal process used by other Zscaler Client Connector processes.
- **ZSATray**: The UI of the application.
- **ZSATrayManager**: Manages the tray process and checks signatures for communication between tray and system services.
- **ZSATunnel**: Handles traffic tunneling.
- **ZSAService**: The main service and the watchdog that looks after all other services.
- **ZSAUpdater**:The process that looks after automatic updates for the app.
- **ZSAUpm**: The Zscaler Digital Experience (ZDX) service.
- **zscalerappupdater**: The executable that initiates updates if found by the updater service.
- **zscalerchecksumverifier**: Ensures the update being launched is legitimate.
- **zsffutil**: Replaces certutil and file checksum used to validate crypto functions.
- **NetworkAdapter**: Various versions of the network adapter, used by Zscaler Client Connector when in Tunnel (Route-Based).
- **ProgramData\Zscaler**: Directory storing logs, PCAPs, and configuration for Zscaler Client Connector.
- **LogonUI**: Used for pre-Windows login in Private Access.
- **ZDPService.exe**: The data protection process that evaluates Data Loss Prevention (DLP) policy rules and determines the protection action to perform.
- **ZDPClassifier.exe**: The text extraction and classification process that identifies the true file type of a file and extracts the text from supported file types and then performs content classification.
- **ZDPApp.exe**: This process interacts with the end user to get user confirmation for specific activities.
- **ZEPInstaller.exe**: Prevents end users from stopping, modifying, and deleting Zscaler products and services.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zscaler-client-connector-profile-rule-example","lastmod":"2026-06-05T21:06Z","nid":"1328906"} -->
## Zscaler Client Connector Profile Rule Example

- Source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-profile-rule-example
- Product: Client Connector
- Path: Zscaler Client Connector Help > Zscaler Client Connector Profile Management > Zscaler Client Connector Profile Rule Example
- Last modified: 2026-06-05T21:06Z
- Summary: Information on how the Zscaler Client Connector downloads an app profile with the appropriate policy rule for an enrolled user.

In the Zscaler Admin Console, you can configure [app profiles](https://help.zscaler.com/zscaler-client-connector/about-zscaler-app-profiles) by adding policy rules to each profile. You can select the order of precedence among the rules as well as to whom each rule applies (i.e., to all users or to different groups of users). When a user enrolls the app with the Zscaler service, the app takes into account the order of precedence and the identity of the user in order to download an app profile with the appropriate policy rule. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-profiles).

Consider an organization that has configured three app profile policies for Windows (see the following image). The rules are listed in the order of precedence (with the rule order value listed in **Rule** column).

When a user who belongs to the Sales user group enrolls with the Zscaler service, Zscaler Client Connector begins by checking whether rule 1 applies to the user. In this example, it does not, as rule 1 does not apply to any groups. The app moves on to rule 2 and upon determining it applies to the user, downloads the profile containing the appropriate policy rule.

[Image: An example of an organization's configured Zscaler Client Connector profile policies]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zscaler-client-connector-resource-usage","lastmod":"2026-03-30T14:33Z","nid":"1333281"} -->
## Zscaler Client Connector Resource Usage

- Source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-resource-usage
- Product: Client Connector
- Path: Zscaler Client Connector Help > Zscaler Client Connector Resource Usage
- Last modified: 2026-03-30T14:33Z
- Summary: Information on how Zscaler Client Connector uses device system resources.

When Zscaler Client Connector is installed on your device, it uses system resources such as memory, CPU, and battery. For mobile devices, the app uses cellular data when you are not connected to Wi-Fi.

## Android, Android on ChromeOS, and iOS

For the Android, Android on ChromeOS, and iOS versions of Zscaler Client Connector, you can expect the app to:

- Use approximately 20 MB of storage space for installation.
- Use between 10 and 20% of your device's battery life.

The battery usage applications on these platforms are not entirely accurate. They measure battery drain over time and look at which processes ran during that usage. However, because Zscaler Client Connector always forwards traffic, its perceived battery usage percentage can be much higher. The best way to measure battery drain is to leave the device running with and without Zscaler Client Connector, and time how long it takes the battery to drain to a certain percentage.

Other external factors can affect battery life. For example, in an area with poor cellular reception, the device's radio consumes more power in an attempt to receive a better signal.

## Windows and macOS

For the Windows and macOS versions of Zscaler Client Connector, you can expect the app to:

- Use approximately 200 MB of disk for installation and additional space for logging. The amount of MB used for logging depends on what you defined for log retention.
- Have no noticeable impact on your device's battery life.
- Use between 70 to 150 MB of RAM. The RAM usage may be higher on VDI systems.
- Use between 0 to 5% CPU, which can increase temporarily when Zscaler Client Connector processes traffic.

## Linux

For Linux, you can expect the app to use:

- Approximately 181 MB of storage space for installation.
- Approximately 0.5% of RAM.
- 0 to 5% CPU when idle and 7 to 15% CPU during traffic.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zscaler-endpoint-data-loss-prevention-dlp-integration-zscaler-client-connector","lastmod":"2026-05-22T08:05Z","nid":"1463876"} -->
## Zscaler Endpoint Data Loss Prevention (DLP) Integration with Zscaler Client Connector

- Source: https://help.zscaler.com/zscaler-client-connector/zscaler-endpoint-data-loss-prevention-dlp-integration-zscaler-client-connector
- Product: Client Connector
- Path: Zscaler Client Connector Help > Zscaler Client Connector Profile Management > Zscaler Endpoint Data Loss Prevention (DLP) Integration with Zscaler Client Connector
- Last modified: 2026-05-22T08:05Z
- Summary: Integration of the Zscaler Endpoint Data Loss Prevention (DLP) feature with Zscaler Client Connector

This feature is supported on devices running Zscaler Client Connector version 4.3 and later for Windows and Zscaler Client Connector version 4.2 and later for macOS. The Endpoint DLP feature is only supported on devices running macOS Monterey (12) and later versions. To learn about the prerequisites of this feature, see [Step-by-Step Configuration Guide](https://help.zscaler.com/zia/step-by-step-endpoint-dlp) for Zscaler Endpoint Data Loss Prevention (DLP).

Zscaler Endpoint Data Loss Prevention (DLP) safeguards your company’s data by monitoring activities that users take on endpoints such as, removable storage (e.g., USB external drives), printers, network shares, and personal cloud storage (e.g., Dropbox). To learn more, see [About Endpoint Data Loss Prevention](https://help.zscaler.com/zia/about-endpoint-dlp) and [Understanding Endpoint Policy Enforcement](https://help.zscaler.com/zia/understanding-endpoint-policy-enforcement).

When you configure and activate the Endpoint DLP policy in Zscaler Internet Access (ZIA), endpoints automatically use Zscaler Client Connector to retrieve the policy from the Zscaler cloud.

Endpoint DLP requires [Full Disk Access](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-jamf-pro-macos#configure-full-disk-access) for proper operation. MDM profiles that grant Full Disk Access must be deployed to endpoints prior to Endpoint DLP installation.

## Configuring Endpoint DLP in the Zscaler Client Connector Portal

Admin can enable Zscaler Endpoint DLP using the Zscaler Client Connector Portal. For steps to enable the **Install Endpoint DLP** feature, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).

[Image: Install Endpoint DLP]

## Endpoint DLP Integration With Zscaler Client Connector

On Zscaler Client Connector, the **Data Protection** page refers to the Zscaler Endpoint DLP feature. On the **Data Protection** page, the end-users can view the connection status of the service and perform troubleshooting.

[Image: Data Protection on Zscaler Client Connector]

Endpoint DLP enforces rules without an internet connection. After the connection is retrieved, it sends all the incidents and activities to the cloud.

### Endpoint DLP Notifications

When a user violates an Endpoint DLP rule, and the user notifications are enabled, they receive a message on the endpoint based on the triggered rule.

[Image: Endpoint DLP notifications]

You can [configure Endpoint DLP policy rules](https://help.zscaler.com/zia/configuring-endpoint-dlp-policy-rules) to Allow, Block, or ask end users to Confirm activities that match the rule. If you select Allow, the service allows and logs the activity. If you select Block, the service blocks and logs the activity. If you select Confirm, the end user can justify the activity to continue or cancel the activity altogether; in both cases, the service logs the activity accordingly.

#### Examples of Endpoint DLP Notifications

- Allow notification
- Block notification
- Confirm notification

When a user violates or triggers an Endpoint DLP rule that is configured for the Allow action and the[End User Notification is selected as Show](https://help.zscaler.com/zia/configuring-endpoint-dlp-policy-rules), the Allow notification is displayed. The user can click **Learn more** for more details about the notification or click **Do Not Disturb** to prevent the notification from reappearing. The allow notification automatically disappears in 10 seconds.

[Image: Allow notification]

When a user violates or triggers an Endpoint DLP rule that is configured for the Allow action and the [End User Notification is selected as Show](https://help.zscaler.com/zia/configuring-endpoint-dlp-policy-rules), the Block notification is displayed. The user can click **Learn more** to understand why their activity was blocked, and then click the **More options** drop-down menu and choose **Do Not Disturb** or **Request Exemption**.

[Image: Block notification]

If the admin has set rules with a confirm action, end users receive a notification requesting action. The user has 5 minutes to select an option. If the user doesn’t provide input within the time period, Zscaler blocks the user activity.

[Image: Confirm notification]

#### Mute Notifications

Users can turn off Endpoint DLP user notifications on the **Zscaler Client Connector** > **More** > **Settings** >**Show all notifications**. Even if this setting is turned off, the Endpoint DLP notifications are still logged on the **Notifications** page.

### Requesting Exemption from Data Protection

Authorized users can enter a One-Time Password (OTP) to disable the Endpoint DLP service on Zscaler Client Connector. To learn more about requesting exceptions for the Endpoint DLP service, see [Viewing Information About Zscaler Endpoint DLP on Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-zscaler-endpoint-dlp-zscaler-client-connector).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zscaler-client-connector/zscaler-services-inaccessible-after-upgrading-zscaler-client-connector-versions-4-4-and-4-4-1-ios","lastmod":"2026-04-23T13:27Z","nid":"1533920"} -->
## Zscaler Services Inaccessible After Upgrading to Zscaler Client Connector versions 4.4 and 4.4.1 for iOS

- Source: https://help.zscaler.com/zscaler-client-connector/zscaler-services-inaccessible-after-upgrading-zscaler-client-connector-versions-4-4-and-4-4-1-ios
- Product: Client Connector
- Path: Zscaler Client Connector Help > Troubleshooting > Zscaler Services Inaccessible After Upgrading to Zscaler Client Connector versions 4.4 and 4.4.1 for iOS
- Last modified: 2026-04-23T13:27Z
- Summary: Describes a known issue where access to Zscaler services was lost after upgrading to version 4.4 or 4.4.1 for iOS

Zscaler Client Connector versions 4.4 and 4.4.1 for iOS have a known issue that might cause users to lose access to Zscaler services, e.g., Internet & SaaS, Private Access, and Zscaler Digital Experience (ZDX).

This issue impacts iOS users who are using device groups for service entitlements on Zscaler Client Connector for Windows, macOS, Linux, or Android, but not for iOS.

Affected users will lose the Zscaler service tabs at the bottom of the Zscaler Client Connector window, along with access to all Zscaler service currently configured for device group-based service entitlement.

You are not impacted by this issue if you:

- Haven’t configured device group-based service entitlements for any OS using Zscaler Client Connector (e.g., Windows, macOS, Linux, Android, or iOS).
- Have already configured device group-based entitlements for iOS only.
- Use Authentication Service.

Zscaler has deployed a fix for this issue to all clouds starting December 2nd and ending December 13th. No client-side actions are required to address this issue and Zscaler Client Connector won’t require an upgrade after the fix has been rolled out to all clouds.

Customers who have configured device group-based entitlements for any platform other than iOS can use the following workaround to prevent disruption for iOS users until this issue is resolved:

1. Enable the **Save**button in the Zscaler Admin Console:
  1. In the Zscaler Admin Console, click **Administration** > **Platform Settings**.
  2. Click the **iOS** tab.
  3. Enable the **Save**button:
    1. Enable any option but don't click **Save**.
    2. Disable the option.
  4. Click **Save**.
2. Instruct the affected users to manually refresh internet security policies:
  1. Open Zscaler Client Connector.
  2. Select the **More**window.
  3. Click **Update Policy**.
3. Users with Private Access entitlements must re-register and authenticate with the IdP to access private applications.

Zscaler recommends that customers wait to configure the device group-based service entitlement feature for any OS platform until after December 13, 2025.
<!-- /ZS-ARTICLE -->
