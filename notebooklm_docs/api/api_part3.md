# Zscaler Help — API / SDK (part 3)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-09-21 22:17 UTC
Articles in this file: 91

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/obtaining-vpn-legacy-apps-resources-using-api","lastmod":"2026-01-06T23:49Z","nid":"1532173"} -->
## Obtaining VPN (for Legacy Apps) Resources Using API

- Source: https://help.zscaler.com/legacy-apis/obtaining-vpn-legacy-apps-resources-using-api
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Working with APIs > Obtaining VPN (for Legacy Apps) Resources Using API
- Last modified: 2026-01-06T23:49Z
- Summary: Information about VPN (for Legacy Apps) use cases applicable to the Zscaler Private Access (ZPA) cloud service API.

This article provides information on obtaining VPN (for Legacy Apps) resources using APIs. All APIs are rate limited. To learn more, see [Understanding Rate Limiting](https://help.zscaler.com/zpa/understanding-rate-limiting) and [VPN (for Legacy Apps)](https://help.zscaler.com/zpa/vpn-legacy-apps).

## Getting Details of All Users Connected to VPN Service Edges

To get details of all users that are connected to VPN Service Edges:

1. Send a `GET` request to the following endpoint: `/mgmtconfig/v1/admin/customers/{customerId}/vpnConnectedUsers`.
2. Provide the `customerId`, the ZPA tenant ID of the customer, in the request endpoint. For example: `/mgmtconfig/v1/admin/customers/73229488749543424/vpnConnectedUsers`.

- View an example response

```
{
  "totalPages": "1",
  "currentCount": "1",
  "totalCount": "1",
  "list": [
    {
      "id": "73229488749551415",
      "creationTime": "1756977137",
      "modifiedBy": "72057594038820580",
      "deviceState": "1",
      "clientIPAddress": "192.0.2.0",
      "vpnServiceEdgeId": "73229488749551620",
      "commonName": "dec1bb9059eb7f3392ad3630820f881939d6b3dd4b77795fddc@vishnu1.com",
      "UserName": "exampleUser2@sample.com",
      "vpnServiceEdgeName": "Sample VPN Service Edge Name"
    }
  ]
}
```

A successful response returns code 200. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/zpa/api-response-codes-and-error-messages).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/organization-details","lastmod":"2026-01-06T23:49Z","nid":"1510761"} -->
## Organization Details

- Source: https://help.zscaler.com/legacy-apis/organization-details
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > Organization Details
- Last modified: 2026-01-06T23:49Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /orgInformation`

Retrieves detailed organization information, including headquarter location, geolocation, address, and contact details.

- Operation ID: `OrganizationInformationResource_getOrganizationInformation`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /orgInformation/lite`

Retrieves minimal organization information.

- Operation ID: `OrganizationInformationResource_getOrganizationInformationLite`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /subscriptions`

Retrieves information about the list of subscriptions enabled for your tenant. Subscriptions define the various features and levels of functionality that are available to your organization.

- Operation ID: `SubscriptionsResource_getOrgStatus`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/organizations","lastmod":"2026-01-06T23:49Z","nid":"1532901"} -->
## Organizations

- Source: https://help.zscaler.com/legacy-apis/organizations
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > EASM API > API Developer & Reference Guide > Reference Guide > Organizations
- Last modified: 2026-01-06T23:49Z

API Reference Guide for the Zscaler EASM API

### `GET /organizations`

Retrieves all organizations configured for a tenant in the EASM Admin Portal

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `Authorization` | header | yes | object | API authentication token |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 401 | Unauthorized |
| 500 | Internal Server Error |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/outbound-email-dlp-policy","lastmod":"2026-08-23T07:06Z","nid":"1541555"} -->
## Outbound Email DLP Policy

- Source: https://help.zscaler.com/legacy-apis/outbound-email-dlp-policy
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > Outbound Email DLP Policy
- Last modified: 2026-08-23T07:06Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /emailDlpRules`

Retrieves the list of all Outbound Email DLP rules. You can filter the results based on rule attributes.

- Operation ID: `EmailDlpRuleResource_getRules`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `orgId` | query | no | object | The unique identifier for the organization. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /emailDlpRules`

Creates a new Outbound Email DLP rule. To learn more, see

[Configuring Outbound Email DLP Policy Rules](https://help.zscaler.com/zia/configuring-outbound-email-policy-rules)

.

- Operation ID: `EmailDlpRuleResource_addRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | yes | EmailDlpRule | Rule configuration details |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /emailDlpRules/lite`

Gets the list of all Outbound Email DLP rules with limited information about each rule. To get complete rule details, use the

`GET /emailDlpRules`

request.

- Operation ID: `EmailDlpRuleResource_getSummary`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /emailDlpRules/actions`

Retrieves a mapping of supported Outbound Email DLP rule actions for the specified email tenant applications in a CSV file

- Operation ID: `EmailDlpRuleResource_getEmailDlpPolicyActions`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `tenantIds` | query | yes | object | A set of unique identifiers for the email tenant applications |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /emailDlpRules/{ruleId}`

Retrieves information about an Outbound Email DLP rule based on the specified rule ID

- Operation ID: `EmailDlpRuleResource_getRuleById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | The unique identifier for the rule |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /emailDlpRules/{ruleId}`

Updates information for an existing Outbound Email DLP rule. To learn more, see

[Configuring Outbound Email DLP Policy Rules](https://help.zscaler.com/zia/configuring-outbound-email-policy-rules)

- Operation ID: `EmailDlpRuleResource_updateRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | The unique identifier for the rule |
| `body` | body | yes | EmailDlpRule | Updated rule configuration details |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /emailDlpRules/{ruleId}`

Deletes an Outbound Email DLP rule based on the specified rule ID

- Operation ID: `EmailDlpRuleResource_deleteRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | The unique identifier for the rule |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/pac-files","lastmod":"2026-01-06T23:49Z","nid":"1508406"} -->
## PAC Files

- Source: https://help.zscaler.com/legacy-apis/pac-files
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > PAC Files
- Last modified: 2026-01-06T23:49Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /pacFiles`

Retrieves the list of all PAC files which are in deployed state. This list includes default and [custom PAC files](https://help.zscaler.com/zia/using-custom-pac-file-forward-traffic-zia). To learn more, see [About Hosted PAC Files](https://help.zscaler.com/zia/about-hosted-pac-files).

- Operation ID: `PacResource_getPacs`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `search` | query | no | object | Returns PAC files with the names that match the search criteria |
| `filter` | query | no | object | Retrieves the list of PAC files without the PAC file content in the response |
| `page` | query | no | object | Specifies the page offset |
| `pageSize` | query | no | object | Specifies the page size. The default size is 100. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /pacFiles`

Adds a new custom PAC file. To learn more about PAC file configuration, see [Using Custom PAC Files to Forward Traffic to ZIA](https://help.zscaler.com/zia/using-custom-pac-file-forward-traffic-zia) and [Writing a PAC File](https://help.zscaler.com/zia/writing-pac-file).

**Note**: Before adding a new PAC file, you can validate the content of the PAC file by sending a POST request to `/pacFiles/validate`. This request only adds a PAC file. The PAC file needs to be transitioned to the **Deploy** state in order for the file to be deployed.

- Operation ID: `PacResource_addPac`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | yes | Pac | PAC object that defines PAC file to be created |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /pacFiles/validate`

Sends the PAC file content for validation and returns the validation result. To learn more, see

[Using Custom PAC Files to Forward Traffic to ZIA](https://help.zscaler.com/zia/using-custom-pac-file-forward-traffic-zia)

.

- Operation ID: `PacResource_validatePac`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | yes | string | PAC file content |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /pacFiles/{pacId}`

Deletes an existing PAC file including all of its versions based on the specified ID

- Operation ID: `PacResource_deletePac`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `pacId` | path | yes | object | Specifies the ID of the PAC file |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |

### `GET /pacFiles/{pacId}/version`

Retrieves all versions of a PAC file based on the specified ID

- Operation ID: `PacResource_getPacById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `pacId` | path | yes | object | Specifies the ID of the PAC file |
| `filter` | query | no | object | Excludes specific information about the PAC file from the response such as the PAC file content |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /pacFiles/{pacId}/version/{clonedPacVersion}`

Adds a new PAC file version by branching an existing version based on the specified ID. To learn more, see

[Using Custom PAC Files to Forward Traffic to ZIA](https://help.zscaler.com/zia/using-custom-pac-file-forward-traffic-zia)

.

- Operation ID: `PacResource_addPacVersion`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `pacId` | path | yes | object | Specifies the ID of the PAC file for which a new version needs to be created |
| `clonedPacVersion` | path | yes | object | Specifies the PAC file version that needs to be branched |
| `deleteVersion` | query | no | object | Currently, only 10 versions of a PAC file are supported. If this limit is reached and if you are adding a new version using the `POST /pacFiles/{pacId}/version/{clonedPacVersion}` endpoint, the **deleteVersion** parameter can be used to specify the PAC file version that must be replaced with the new version created using this request. However, if the limit is reached and if a version is not specified using this parameter, the version with the least number is automatically replaced. If the limit is not reached, this parameter is ignored. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /pacFiles/{pacId}/version/{pacVersion}`

Retrieves a specific version of a PAC file based on the specified ID

- Operation ID: `PacResource_getPacVersion`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `pacId` | path | yes | object | Specifies the ID of the PAC file |
| `pacVersion` | path | yes | object | Specifies the version of the PAC file |
| `filter` | query | no | object | Excludes specific information about the PAC file from the response such as the PAC file content |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /pacFiles/{pacId}/version/{pacVersion}/action/{pacVersionAction}`

Performs the specified action on the PAC file version and updates the file status. Supported actions include deploying, staging, unstaging, and marking or unmarking the file as last known good version can be performed on PAC file versions.

- Operation ID: `PacResource_updatePacVersionFlag`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `pacId` | path | yes | object | Specifies the ID of the PAC file |
| `pacVersion` | path | yes | object | Specifies the version of the PAC file |
| `pacVersionAction` | path | yes | object | Specifies the action that must be performed on the PAC file version |
| `newLKGVer` | query | no | object | If you are removing a PAC file version as the last known good version using this request, you need to specify a different version that can be marked as the last known good version. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/partner-federation-provisioning","lastmod":"2026-09-20T07:06Z","nid":"1541905"} -->
## Partner Federation Provisioning

- Source: https://help.zscaler.com/legacy-apis/partner-federation-provisioning
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Partner Federation Provisioning
- Last modified: 2026-09-20T07:06Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/customers/{customerId}/tenant-federation/partners`

Gets the active federation partners for the specified customer using search, sorting, and pagination.

- Operation ID: `getActiveFederationPartners`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |
| `sortBy` | query | no | string | Indicates the parameter to sort by. |
| `sortDir` | query | no | string | Specifies the sort direction (i.e., ascending or descending order). |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/customers/{customerId}/tenant-federation/approval`

Approves a partner federation for the specified ID using a token.

- Operation ID: `requestApproval`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → TenantFederationApprovalRequest

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/customers/{customerId}/tenant-federation/token`

Creates a federation token for the specified customer.

- Operation ID: `createFederationToken`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → TenantFederationTokenRequest

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/customers/{customerId}/tenant-federation/token/verify`

Verifies the federation token for the specified ID.

- Operation ID: `verifyToken`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → TenantFederationTokenVerifyRequest

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/customers/{customerId}/tenant-federation`

Gets the provisioning requests for the specified customer using search, sorting, and pagination.

- Operation ID: `getProvisionings`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |
| `sortBy` | query | no | string | Indicates the parameter to sort by. |
| `sortDir` | query | no | string | Specifies the sort direction (i.e., ascending or descending order). |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `DELETE /mgmtconfig/v1/customers/{customerId}/tenant-federation/{federation_id}`

Deletes the partner federation for the specified ID.

- Operation ID: `deleteProvisioning`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `federation_id` | path | yes | integer(int64) | The unique identifier of the federation. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/customers/{customerId}/tenant-federation/{federation_id}/provisioning-state/{status}`

Updates the provisioning status (APPROVED, DENIED, TERMINATED) of the partner federation request.

- Operation ID: `updateProvisioningState`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `federation_id` | path | yes | integer(int64) | The unique identifier of the federation. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |
| `status` | path | yes | string (enum: APPROVED, DENIED, TERMINATED) | The provisioning status of the federation. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/customers/{customerId}/tenant-federation/{federation_id}/notes`

Updates the notes for partner federation provisioning. The initiator updates

`initiatorNotes`

, and the partner updates

`partnerNotes`

.

- Operation ID: `updateNotes`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `federation_id` | path | yes | integer(int64) | The unique identifier of the federation you want to update the notes for. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → TenantFederationNotesUpdate

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/customers/{customerId}/tenant-federation/{federation_id}/federation-state/{status}`

Updates the status (ACTIVE or INACTIVE) of an existing federation for the specified ID.

- Operation ID: `updateFederationState`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `federation_id` | path | yes | integer(int64) | The provisioning status of the federation. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |
| `status` | path | yes | string (enum: INACTIVE, ACTIVE) | The federation status is ACTIVE (i.e., Active) or INACTIVE (i.e., Pause). |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`PageListPartnerInfoDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `currentCount` | integer(int64) | no |
| `list` | array<PartnerInfo> | no |
| `message` | string | no |
| `totalCount` | integer(int64) | no |
| `totalPages` | integer(int32) | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`TenantFederationApprovalRequest`**

| Property | Type | Required |
| --- | --- | --- |
| `partnerNotes` | string | no |
| `token` | string | yes |

**`TenantFederationTokenRequest`**

| Property | Type | Required |
| --- | --- | --- |
| `expiryTimeInSeconds` | integer(int64) | yes |
| `notes` | string | no |

**`TenantFederationTokenResponse`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `token` | string | no |
| `tokenExpirationEpochSeconds` | integer(int64) | no |

**`TenantFederationTokenVerifyRequest`**

| Property | Type | Required |
| --- | --- | --- |
| `token` | string | yes |

**`TenantFederationProvisioning`**

| Property | Type | Required |
| --- | --- | --- |
| `partnerInfo` | PartnerInfo | no |
| `partnerNotes` | string | no |
| `success` | boolean | no |
| `tokenExpirationEpochSeconds` | integer(int64) | no |

**`PageListTenantFederationListDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `currentCount` | integer(int64) | no |
| `list` | array<TenantFederationList> | no |
| `message` | string | no |
| `totalCount` | integer(int64) | no |
| `totalPages` | integer(int32) | no |

**`TenantFederationNotesUpdate`**

| Property | Type | Required |
| --- | --- | --- |
| `notes` | string | no |

**`PartnerInfo`**

| Property | Type | Required |
| --- | --- | --- |
| `approvalStatus` | string (enum: NOT_APPROVED, APPROVED) | no |
| `federationStatus` | string (enum: INACTIVE, ACTIVE) | no |
| `partnerGid` | integer(int64) | no |
| `partnerName` | string | no |
| `partnerScopeName` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/partner-integrations","lastmod":"2026-04-07T07:06Z","nid":"1532710"} -->
## Partner Integrations

- Source: https://help.zscaler.com/legacy-apis/partner-integrations
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > Zscaler Cloud & Branch Connector API > API Developer & Reference Guide > Reference Guide > Partner Integrations
- Last modified: 2026-04-07T07:06Z

**Servers:** `https://{host}:{port}/wapi/v1`, `https://{host}:{port}/api/v1`

### `GET /publicCloudInfo`

Retrieves the list of AWS accounts with metadata. To learn more, see

[About Amazon Web Services Accounts](https://help.zscaler.com/cloud-branch-connector/about-amazon-web-services-accounts)

.

- Operation ID: `AwsAccountZResource_getAwsAccount`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default response. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### `POST /publicCloudInfo`

Creates a new AWS account with the provided account and region details. You can create a maximum of 512 accounts in each organization. To learn more, see

[Adding an Amazon Web Services Account](https://help.zscaler.com/cloud-branch-connector/adding-amazon-web-services-account)

.

- Operation ID: `AwsAccountZResource_createAwsAccount`

**Request body:** `application/json` → AwsAccount

**Responses:**

| Code | Description |
| --- | --- |
| default | Default response. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### `GET /publicCloudInfo/cloudFormationTemplate`

Retrieves the CloudFormation template URL. To learn more, see

[Adding an Amazon Web Services Account](https://help.zscaler.com/cloud-branch-connector/adding-amazon-web-services-account)

.

- Operation ID: `AwsAccountZResource_getCloudFormationTemplateUrl`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `awsAccountId` | query | no | string | (Optional) The AWS account ID to customize the CloudFormation template URL. If an `awsAccountId` is provided, the URL is customized with account-specific values, or a generic template URL is returned. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default response. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### `GET /publicCloudInfo/count`

Retrieves the total number of AWS accounts.

- Operation ID: `AwsAccountZResource_getAwsAccountCount`

**Responses:**

| Code | Description |
| --- | --- |
| default | Default response. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### `POST /publicCloudInfo/generateExternalId`

Creates an external ID for an AWS account.

- Operation ID: `AwsAccountZResource_generateExternalId`

**Request body:** `application/json` → GenerateExternalIdRequest

**Responses:**

| Code | Description |
| --- | --- |
| default | Default response. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### `GET /publicCloudInfo/lite`

Retrieves basic information about the AWS cloud accounts. For additional details, use the

`GET /publicCloudInfo`

method.

- Operation ID: `AwsAccountZResource_getAwsAccountSummary`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `cloudType` | query | no | string (enum: AWS, AZURE, GCP) | The cloud type. The default and mandatory value is AWS. |
| `search` | query | no | string | The search string. |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Defaultresponse. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### `GET /publicCloudInfo/supportedRegions`

Retrieves a list of AWS regions supported for  workload discovery settings (WDS).

- Operation ID: `AwsAccountZResource_getAwsSupportedRegions`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default response. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### `GET /publicCloudInfo/{id}`

Retrieves the existing AWS account details based on the provided ID.

- Operation ID: `AwsAccountZResource_getAwsAccountById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | The unique ID of the AWS account. |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default response. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### `PUT /publicCloudInfo/{id}`

Updates the existing AWS account details based on the provided ID.

- Operation ID: `AwsAccountZResource_updateAwsAccount`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | The unique ID of the AWS account. |

**Request body:** `application/json` → AwsAccount

**Responses:**

| Code | Description |
| --- | --- |
| default | Default response. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### `DELETE /publicCloudInfo/{id}`

Removes a specific AWS account based on the provided ID.

- Operation ID: `AwsAccountZResource_deleteAwsAccount`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | The unique ID of the AWS account. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default response. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### `PUT /publicCloudInfo/{id}/changeState`

Enables or disables a specific AWS account in all regions based on the provided ID.

- Operation ID: `AwsAccountZResource_disableAwsAccount`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | The unique ID of the AWS account. |
| `enable` | query | no | boolean | Set `true` to enable the AWS account, and `false` to disable it. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default response. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### `GET /publicCloudTenant`

Retrieves a paginated list of all Microsoft Azure accounts. To learn more, see

[About Microsoft Azure Accounts](https://help.zscaler.com/cloud-branch-connector/about-microsoft-azure-accounts)

.

- Operation ID: `PublicCloudTenantZResource_getPublicCloudTenants`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | Specifies the starting point of the current page in pagination, determining which set of records is displayed. |
| `pageSize` | query | no | integer(int32) | Specifies the number of records included in a single response page. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `POST /publicCloudTenant`

Creates a new Azure account. To learn more, see

[Configuring the Workload Discovery Service for Microsoft Azure Accounts](https://help.zscaler.com/cloud-branch-connector/configuring-workload-discovery-service-microsoft-azure-accounts)

.

- Operation ID: `PublicCloudTenantZResource_createPublicCloudTenant`

**Request body:** `application/json` → PublicCloudTenant

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `GET /publicCloudTenant/ccGroups`

Retrieves the list of available Cloud Connector groups for the specified region and the associated Azure account ID.

- Operation ID: `PublicCloudTenantZResource_getCCGroupsAvailable`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `region` | query | no | array<integer(int32)> | The Azure account region. |
| `tenantId` | query | no | integer(int32) | The unique ID of the Azure account. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `GET /publicCloudTenant/count`

Retrieves the total number of Azure accounts.

- Operation ID: `PublicCloudTenantZResource_getPublicCloudTenantCount`

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `GET /publicCloudTenant/supportedRegions`

Retrieves supported Azure regions.

- Operation ID: `PublicCloudTenantZResource_getAzureSupportedRegions`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Retreives the list of supported regions for the cloud type. |

### `GET /publicCloudTenant/{id}`

Retrieves the details of an Azure account based on the specified unique account ID.

- Operation ID: `PublicCloudTenantZResource_getPublicCloudTenantById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | The unique ID of the Azure account. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `PUT /publicCloudTenant/{id}`

Updates the details of an Azure account based on the specified unique account ID.

- Operation ID: `PublicCloudTenantZResource_updatePublicCloudTenant`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | The unique ID of the Azure account. |

**Request body:** `application/json` → PublicCloudTenant

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `DELETE /publicCloudTenant/{id}`

Deletes an Azure account based on the specified unique account ID.

- Operation ID: `PublicCloudTenantZResource_deletePublicCloudTenant`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | The unique ID of the Azure account. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `PUT /discoveryService/azure/subscriptionSync`

Updates Azure subscription permission status.

- Operation ID: `DiscoveryServZResource_updateAzureSubscriptionStatus`

**Request body:** `application/json` → AzurePermissionReq

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `POST /discoveryService/azure/tenantPermission`

Verifies whether the provided Azure credentials are valid and can scan tags for Azure subscriptions.

- Operation ID: `DiscoveryServZResource_getAzureTenantPermission`

**Request body:** `application/json` → AzurePermissionReq

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `POST /discoveryService/azure/{region}/resourceGroups`

Retrieves the list of resource groups based on the specified Azure region name, subscription, and credentials.

- Operation ID: `DiscoveryServZResource_getAzureResourceGroups`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `region` | path | yes | string | The Azure region name. |

**Request body:** `application/json` → AzurePermissionReq

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `POST /discoveryService/azure/{region}/storageAccounts`

Retrieves the list of storage accounts based on the specified Azure region, subscription, and credentials.

- Operation ID: `DiscoveryServZResource_getAzureStorageAccounts`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `region` | path | yes | string | Azure region name. |

**Request body:** `application/json` → AzurePermissionReq

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `PUT /discoveryService/azure/{region}/topicSync`

Retrieves event grid details for the specified Azure account ID and region.

- Operation ID: `DiscoveryServZResource_updateAzureTopicStatus`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `tenantId` | query | no | integer(int32) | The unique ID of the Azure account. |
| `region` | path | yes | string | The Azure region. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `GET /discoveryService/workloadDiscoverySettings`

Retrieves the workload discovery service settings.

- Operation ID: `DiscoveryServZResource_getWorkloadDiscoverySettings`

**Responses:**

| Code | Description |
| --- | --- |
| default | Default response. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### `PUT /discoveryService/{id}/permissions`

Verifies the specified AWS account permissions using the discovery role and external ID.

- Operation ID: `DiscoveryServZResource_getPermissions`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | The unique identifier for the AWS account. |

**Request body:** `application/json` → PermissionReq

**Responses:**

| Code | Description |
| --- | --- |
| default | Default response. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### `GET /accountGroups`

Retrieves the details of AWS account groups with metadata. To learn more, see

[About Amazon Web Services Account Groups](https://help.zscaler.com/cloud-branch-connector/about-amazon-web-services-account-groups)

.

- Operation ID: `AwsAccountGroupZResource_getAccountGroups`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default response. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### `POST /accountGroups`

Creates an AWS account group. You can create a maximum of 128 groups in each organization. To learn more, see

[Adding an Amazon Web Services Account Group](https://help.zscaler.com/cloud-branch-connector/adding-amazon-web-services-account-group)

.

- Operation ID: `AwsAccountGroupZResource_createAccountGroup`

**Request body:** `application/json` → AwsAccountGroup

**Responses:**

| Code | Description |
| --- | --- |
| default | Default response. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### `GET /accountGroups/count`

Retrieves the total number of AWS account groups.

- Operation ID: `AwsAccountGroupZResource_getAccountGroupsCount`

**Responses:**

| Code | Description |
| --- | --- |
| default | Default response. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### `GET /accountGroups/lite`

Retrieves the ID and name of all the AWS account groups. For additional details, use the

`GET /accountGroups`

method.

- Operation ID: `AwsAccountGroupZResource_getAccountGroupsLite`

**Responses:**

| Code | Description |
| --- | --- |
| default | Default response. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### `GET /accountGroups/{id}`

Retrieves the specific AWS account group details based on the provided group ID.

- Operation ID: `AwsAccountGroupZResource_getAccountGroupById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | The ID of the AWS account group. |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default response. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### `PUT /accountGroups/{id}`

Updates the existing AWS account group details based on the provided ID.

- Operation ID: `AwsAccountGroupZResource_updateAccountGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | The unique ID of the AWS account group. |

**Request body:** `application/json` → AwsAccountGroup

**Responses:**

| Code | Description |
| --- | --- |
| default | Default response. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### `DELETE /accountGroups/{id}`

Removes a specific AWS account group based on the provided ID.

- Operation ID: `AwsAccountGroupZResource_deleteAccountGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | The unique ID of the AWS account group. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default response. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). |

### Schemas

**`AwsAccount`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `accountDetails` | AwsAccountDetails | no |
| `accountGroups` | array<EntityReference> | no |
| `cloudType` | string (enum: AWS, AZURE, GCP) | no |
| `externalId` | string | no |
| `lastModTime` | integer(int32) | no |
| `lastModUser` | EntityReference | no |
| `lastSyncTime` | integer(int32) | no |
| `permissionStatus` | object | no |
| `regionStatus` | array<RegionStatus> | no |
| `supportedRegions` | array<SupportedRegion> | no |

**`GenerateExternalIdRequest`**

| Property | Type | Required |
| --- | --- | --- |
| `awsAccountId` | string | no |
| `awsRoleName` | string | no |

**`PublicCloudInfoSummary`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `accountId` | string | no |
| `cloudType` | string (enum: AWS, AZURE, GCP) | no |

**`SupportedRegion`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `cloudType` | string (enum: AWS, AZURE, GCP) | no |

**`PublicCloudTenant`**

| Property | Type | Required |
| --- | --- | --- |
| `cloudType` | string (enum: AWS, AZURE, GCP) | no |
| `id` | integer(int32) | no |
| `infoMsg` | string | no |
| `lastModTime` | integer(int32) | no |
| `lastModUser` | EntityReference | no |
| `name` | string | no |
| `publicCloudStorageAccounts` | array<PublicCloudStorageAccount> | no |
| `publicCloudTopics` | array<PublicCloudTopic> | no |
| `subscriptionGroups` | array<SubscriptionGroup> | no |
| `tenantDetails` | AzureTenantDetails | no |

**`EcGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `autoScale` | boolean | no |
| `id` | integer(int32) | no |
| `name` | string | no |
| `desc` | string | no |
| `deployType` | string (enum: ONPREM, CLOUD, DISABLED, MON_DELETED, DELETING, DELETING_LAST, DELETED) | no |
| `status` | array<string (enum: ONPREM, CLOUD, DISABLED, MON_DELETED, DELETING, DELETING_LAST, DELETED)> | no |
| `platform` | string (enum: AWS, AZURE, CENTOS, REDHAT_LINUX, VMWARE_ESXI) | no |
| `awsAvailabilityZone` | string (enum: AF_SOUTH_1A, AF_SOUTH_1B, AF_SOUTH_1C, AP_EAST_1A, AP_EAST_1B, AP_EAST_1C, AP_NORTHEAST_1A, AP_NORTHEAST_1C) | no |
| `azureAvailabilityZone` | string (enum: ASIA_0, ASIA_1, ASIA_2, ASIA_3, ASIAPACIFIC_0, ASIAPACIFIC_1, ASIAPACIFIC_2, ASIAPACIFIC_3) | no |
| `azureRegion` | string (enum: AUSTRALIACENTRAL, AUSTRALIACENTRAL2, AUSTRALIAEAST, AUSTRALIASOUTHEAST, BRAZILSOUTH, BRAZILSOUTHEAST, CANADACENTRAL, CANADAEAST) | no |
| `namespace` | string | no |
| `location` | object | no |
| `maxEcCount` | integer(int32) | no |
| `provTemplate` | object | no |
| `tunnelMode` | string (enum: UNENCRYPTED, DTLS) | no |
| `ecVMs` | array<EcVM> | no |

**`AzurePermissionReq`**

| Property | Type | Required |
| --- | --- | --- |
| `appRegistration` | AzureAppRegistration | no |
| `subscriptionId` | string | no |

**`AzurePermissionResponse`**

| Property | Type | Required |
| --- | --- | --- |
| `applicationRegistrationPermission` | AzureAppRegistration | no |
| `subscription` | string | no |

**`AzureStorageAccountDetails`**

| Property | Type | Required |
| --- | --- | --- |
| `name` | string | no |
| `resourceGroup` | string | no |

**`AzureTopicDetails`**

| Property | Type | Required |
| --- | --- | --- |
| `partnerDestinationActivated` | boolean | no |
| `partnerDestinationCreated` | boolean | no |
| `partnerDestinationMessageCount` | string | no |
| `partnerTopicActivated` | boolean | no |
| `partnerTopicCreated` | boolean | no |
| `partnerTopicMessageCount` | string | no |

**`WorkloadDiscoverySetting`**

| Property | Type | Required |
| --- | --- | --- |
| `trustedAccountId` | string | no |
| `trustedRoleName` | string | no |

**`PermissionReq`**

| Property | Type | Required |
| --- | --- | --- |
| `discoveryRole` | string | no |
| `externalId` | string | no |

**`AwsAccountGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `description` | string | no |
| `cloudConnectorGroups` | array<EntityReference> | no |
| `cloudType` | string (enum: AWS, AZURE, GCP) | no |
| `publicCloudAccounts` | array<EntityReference> | no |

**`EntityResponse`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |

**`AwsAccountDetails`**

| Property | Type | Required |
| --- | --- | --- |
| `awsAccountId` | string | no |
| `awsRoleName` | string | no |
| `cloudWatchGroupArn` | string | no |
| `eventBusName` | string | no |
| `externalId` | string | no |
| `logInfoType` | string (enum: INFO, ERROR) | no |
| `troubleShootingLogging` | boolean | no |
| `trustedAccountId` | string | no |
| `trustedRole` | string | no |

**`EntityReference`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `name` | string | no |
| `isNameL10nTag` | boolean | no |
| `extensions` | object | no |
| `deleted` | boolean | no |
| `externalId` | string | no |
| `associationTime` | integer(int32) | no |

**`RegionStatus`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `cloudType` | string (enum: AWS, AZURE, GCP) | no |
| `status` | boolean | no |

**`PublicCloudStorageAccount`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `prefix` | string | no |
| `region` | SupportedRegion | no |
| `resourceGroup` | string | no |
| `subscription` | string | no |

**`PublicCloudTopic`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `lastSyncTime` | integer(int32) | no |
| `region` | SupportedRegion | no |
| `resourceGroup` | string | no |
| `status` | object | no |
| `subscription` | string | no |

**`SubscriptionGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `cloudConnectorGroupList` | array<EcGroup> | no |
| `cloudType` | string (enum: AWS, AZURE, GCP) | no |
| `id` | integer(int32) | no |
| `lastModTime` | integer(int32) | no |
| `lastModUser` | EntityReference | no |
| `name` | string | no |
| `subscriptionList` | array<PublicCloudSubscription> | no |
| `supportedRegions` | array<SupportedRegion> | no |

**`EcVM`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `status` | array<string (enum: DISABLED, REGISTERED, RESERVED1, PKG_REPO_REGISTERED, ENABLING, DISABLING, DELETING, DELETING_LAST)> | no |
| `autoScale` | boolean | no |
| `buildVersion` | string | no |
| `ccRoleName` | string | no |
| `failOpen` | boolean | no |
| `formFactor` | string (enum: SMALL, MEDIUM, LARGE) | no |
| `managementNw` | EcNetwork | no |
| `ecInstances` | array<EcInstance> | no |
| `cityGeoId` | integer(int32) | no |
| `deployAsGateway` | boolean | no |
| `dnsCache` | boolean | no |
| `haStatus` | string (enum: INIT, ACTIVE, STANDBY, DISABLED) | no |
| `hardwareInfo` | HardwareInfo | no |
| `lanDns` | EcDns | no |
| `lastTemplateConfigPushFailed` | boolean | no |
| `lastUpgradeTime` | integer(int32) | no |
| `metaConfig` | EcVmMetaConfig | no |
| `natIp` | string | no |
| `operationalStatus` | string | no |
| `pkgRepoSerialNumber` | integer(int32) | no |
| `provTemplate` | EntityReference | no |
| `provTemplateStatus` | string (enum: STAGED, NOT_DEPLOYED, DEPLOYED, EDITABLE) | no |
| `trafficDistribution` | string (enum: BALANCED, BEST_LINK) | no |
| `upgradeStatus` | integer(int32) | no |
| `upgradeStartTime` | integer(int32) | no |
| `upgradeEndTime` | integer(int32) | no |
| `upgradeDayOfWeek` | integer(int32) | no |
| `useWanDns` | boolean | no |
| `usedForZtp` | boolean | no |
| `zeroTrustVm` | boolean | no |
| `ziaGateway` | string | no |
| `ziaGateways` | array<string> | no |
| `zpaBroker` | string | no |
| `zpaBrokers` | array<string> | no |

**`EcNetwork`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `ipStart` | string | no |
| `ipEnd` | string | no |
| `admin` | string (enum: NOSHUTDOWN, SHUTDOWN) | no |
| `natIp` | string | no |
| `netmask` | string | no |
| `defaultGateway` | string | no |
| `nwType` | string (enum: AUTOMATIC, MANUAL, DELETING, SHUTDOWN_ON, PORT_STATUS_UP, DELETED) | no |
| `portStatusUp` | boolean | no |
| `shutdownOn` | boolean | no |
| `dns` | EcDns | no |

**`EcDns`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `ips` | array<string> | no |
| `dnsType` | string (enum: AUTOMATIC, MANUAL, DELETING, DELETED) | no |

**`EcInstance`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `ecInstanceType` | string (enum: ANY, NONE, ALERTEVAL, ALERTNOTIF, SMBA, SMBAG, SMBAC, SMESXI) | no |
| `flags` | string (enum: DISABLED, MON_DELETED, REGISTERED, ACTIVE, DELETING, DELETED) | no |
| `serviceNw` | EcNetwork | no |
| `virtualNw` | EcNetwork | no |
| `natIp` | string | no |
| `registerTime` | integer(int32) | no |
| `dnsIp` | array<string> | no |
| `zpaId` | integer(int64) | no |

**`HardwareInfo`**

| Property | Type | Required |
| --- | --- | --- |
| `deviceName` | string | no |
| `modelType` | string (enum: ZT600, ZT800, ZT400) | no |
| `serialNum` | string | no |

**`EcVmMetaConfig`**

| Property | Type | Required |
| --- | --- | --- |
| `hostName` | string | no |
| `nativeId` | string | no |
| `repoTag` | string | no |
| `uuid` | string | no |

**`PublicCloudSubscription`**

| Property | Type | Required |
| --- | --- | --- |
| `cloudType` | string (enum: AWS, AZURE, GCP) | no |
| `externalId` | string | no |
| `id` | integer(int32) | no |
| `lastSyncTime` | integer(int32) | no |
| `name` | string | no |
| `permissionStatus` | object | no |

**`AzureTenantDetails`**

| Property | Type | Required |
| --- | --- | --- |
| `applicationId` | string | no |
| `applicationSecret` | string | no |
| `azureDirectoryId` | string | no |

**`AzureAppRegistration`**

| Property | Type | Required |
| --- | --- | --- |
| `applicationId` | string | no |
| `applicationSecret` | string | no |
| `azureDirectoryId` | string | no |
| `canListSubscriptions` | boolean | no |
| `credentialsMismatched` | boolean | no |
| `subscriptions` | array<AzureSubscriptionDetails> | no |
| `tenantEntryId` | string | no |

**`AzureSubscriptionDetails`**

| Property | Type | Required |
| --- | --- | --- |
| `canDescribeNic` | boolean | no |
| `canDescribeVm` | boolean | no |
| `canDescribeVnet` | boolean | no |
| `errorDescription` | string | no |
| `resourceGroups` | array<string> | no |
| `status` | string (enum: PENDING, ALLOWED, DENIED) | no |
| `storageAccounts` | array<AzureStorageAccountDetails> | no |
| `subscription` | string | no |
| `subscriptionName` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/partner-integrations-0","lastmod":"2026-07-20T07:06Z","nid":"1541304"} -->
## Partner Integrations

- Source: https://help.zscaler.com/legacy-apis/partner-integrations-0
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > Partner Integrations
- Last modified: 2026-07-20T07:06Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /integrationPartners`

Retrieves a list of partners and services integrated with the Zscaler service. To learn more, see

[About Partner Integrations](https://help.zscaler.com/zia/about-partner-integrations)

.

- Operation ID: `IntegrationPartnerResource_getPartners`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `apiKeyProvisioned` | query | no | object | Filters the partners list based on the provisioned API key |
| `partnerType` | query | no | object | Filters the partners list based on the partner type |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /integrationPartners/crowdStrike/endpoints`

Retrieves the list of CrowdStrike endpoints based on the indicator of compromise (IOC) query, with pagination support. To learn more, see

[Viewing the CrowdStrike Endpoint Hits Report](https://help.zscaler.com/zia/viewing-crowdstrike-endpoint-hits-report)

.

- Operation ID: `IntegrationPartnerResource_getIocObservedDevices`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `type` | query | no | object | Filters based on the IOC type |
| `value` | query | no | object | Filters based on the IOC value |
| `limit` | query | no | object | Specifies the page size |
| `offset` | query | no | object | Specifies the page offset |
| `partnerJsonType` | query | no | object | Filters based on the partner JSON type |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /integrationPartners/crowdStrike/endpoints`

Accepts a list of CrowdStrike endpoint or device IDs in the request body and fetches detailed endpoint or device data for those IDs. To learn more, see

[Integrating with CrowdStrike](https://help.zscaler.com/zia/integrating-crowdstrike)

.

- Operation ID: `IntegrationPartnerResource_crowdStrikeEndPoints`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | yes | array<string> | Information about Crowdstrike endpoint or device IDs |
| `partnerJsonType` | query | no | object | Filters based on the partner JSON type |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /integrationPartners/crowdStrike/whitelistedBaseUrls`

Retrieves a list of CrowdStrike configured whitelisted base URLs (allowist URLs).

- Operation ID: `IntegrationPartnerResource_getCrowdStrikeWhietlistedDomains`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `partnerJsonType` | query | no | object | Filters based on the partner JSON type |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /integrationPartners/microsoftDefender/endpoints`

Configures the integration of Microsoft Defender for Endpoint APIs with Zscaler. To learn more, see

[Integrating with Microsoft Defender for Endpoint](https://help.zscaler.com/zia/integrating-microsoft-defender-endpoint)

.

- Operation ID: `IntegrationPartnerResource_getMicrosoftDefenderTransactions`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | MicrosoftDefenderTransactionRequest | Microsoft Defender transaction request details |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /integrationPartners/sandbox/report/{md5}`

Retrieves the MD5 hash of the file required to view the Sandbox Detail Report. To learn more, see

[Viewing Sandbox Reports and Data](https://help.zscaler.com/zia/viewing-sandbox-reports-data)

.

- Operation ID: `IntegrationPartnerResource_getMd5Detail`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `md5` | path | yes | object | Filters the Sandbox report based on the MD5 hash of the file |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/policies-b2b-federation","lastmod":"2026-09-20T07:06Z","nid":"1541906"} -->
## Policies for B2B Federation

- Source: https://help.zscaler.com/legacy-apis/policies-b2b-federation
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Policies for B2B Federation
- Last modified: 2026-09-20T07:06Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/customers/{customerId}/policySet/rules/policyType/GLOBAL_POLICY/guest/{guest_id}`

Retrieves a paginated list of policy rules created by the partner for applications that have been federated to them. The response is sanitized to hide sensitive information.

- Operation ID: `getPartnerPolicyRulesOnFederatedApps`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |
| `guest_id` | path | yes | integer(int64) | The partner or guest tenant ID for the policy rules you want to view. This is the `partnerGid` value when sending a GET request to get all active federation partners. |
| `search` | query | no | string | The search string to filter policy rules by name. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`PageListPolicyRule`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<PolicyRule> | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`PolicyRule`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `description` | string | no |
| `ruleOrder` | integer(int32) | no |
| `priority` | integer(int32) | no |
| `policyType` | integer(int32) | yes |
| `operator` | string (enum: AND, OR) | yes |
| `actionId` | integer(int64) | no |
| `action` | string (enum: ALLOW, DENY, LOG, RE_AUTH, NEVER, BYPASS, INTERCEPT, NO_DOWNLOAD) | yes |
| `reauthTimeout` | integer(int32) | no |
| `reauthIdleTimeout` | integer(int32) | no |
| `customMsg` | string | no |
| `disabled` | integer(int32) | no |
| `serviceEdgeGroups` | array<ServiceEdgeGroup> | no |
| `appConnectorGroups` | array<AppConnectorGroup> | no |
| `credential` | NameIdDto | no |
| `credentialPool` | NameIdDto | no |
| `zpnIsolationProfileId` | integer(int64) | no |
| `zpnInspectionProfileId` | integer(int64) | no |
| `postActionTypes` | array<string> | no |
| `postActions` | object | no |
| `zpnInspectionProfileName` | string | no |
| `extranetDTO` | ExtranetDTO | no |
| `extranetEnabled` | boolean | no |
| `inconsistentConfigDetails` | InconsistentConfigDetails | no |
| `policySetId` | integer(int64) | no |
| `privilegedCapabilities` | PrivilegedCapabilities | no |
| `privilegedPortalCapabilities` | PrivilegedPortalCapabilities | no |
| `conditions` | array<ConditionSet> | no |
| `appServerGroups` | array<AppServerGroup> | no |
| `defaultRule` | boolean | no |
| `defaultRuleName` | string | no |

**`ServiceEdgeGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | no |
| `description` | string | no |
| `geoLocationId` | integer(int64) | no |
| `versionProfileId` | integer(int64) | no |
| `overrideVersionProfile` | boolean | no |
| `siteId` | string | no |
| `versionProfileName` | string | no |
| `upgradePriority` | string (enum: WEEK, DAY, FORCE_NOW, NOW) | no |
| `versionProfileVisibilityScope` | string (enum: ALL, NONE, CUSTOM) | no |
| `upgradeTimeInSecs` | string | no |
| `upgradeDay` | string | no |
| `siteName` | string | no |
| `isPublic` | string | no |
| `location` | string | no |
| `serviceEdges` | array<ServiceEdge> | no |
| `latitude` | string | no |
| `city` | string | no |
| `longitude` | string | no |
| `cityCountry` | string | no |
| `countryCode` | string | no |
| `useInDrMode` | boolean | no |
| `graceDistanceEnabled` | boolean | no |
| `graceDistanceValue` | number(double) | no |
| `graceDistanceValueUnit` | string (enum: MILES, KMS) | no |
| `trustedNetworks` | array<TrustedNetwork> | no |
| `altCloud` | string | no |

**`ServiceEdge`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `description` | string | no |
| `fingerprint` | string | no |
| `issuedCertId` | integer(int64) | no |
| `enabled` | boolean | no |
| `ipAcl` | array<string> | no |
| `listenIps` | array<string> | no |
| `publishIps` | array<string> | no |
| `publishIpv6` | boolean | no |
| `privateBrokerVersion` | PrivateBrokerVersion | no |
| `provisioningKeyId` | integer(int64) | no |
| `provisioningKeyName` | string | no |
| `serviceEdgeGroupId` | string | no |
| `serviceEdgeGroupName` | string | no |
| `enrollmentCert` | object | no |
| `latitude` | string | no |
| `longitude` | string | no |
| `location` | string | no |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `previousVersion` | string | no |
| `lastUpgradeTime` | integer(int64) | no |
| `expectedUpgradeTime` | integer(int64) | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `controlChannelStatus` | string (enum: UNKNOWN, ZPN_STATUS_AUTHENTICATED, ZPN_STATUS_DISCONNECTED) | no |
| `upgradeAttempt` | integer(int32) | no |
| `ctrlBrokerName` | string | no |
| `lastBrokerConnectTime` | integer(int64) | no |
| `lastBrokerConnectTimeDuration` | string | no |
| `lastBrokerDisconnectTime` | integer(int64) | no |
| `lastBrokerDisconnectTimeDuration` | string | no |
| `privateIp` | string | no |
| `publicIp` | string | no |
| `platform` | string | no |
| `runtimeOS` | string | no |
| `applicationStartTime` | integer(int64) | no |
| `sargeVersion` | string | no |
| `platformDetail` | string | no |
| `zpnSubModuleUpgradeList` | array<ZpnSubModuleUpgrade> | no |

**`PrivateBrokerVersion`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `systemStartTime` | integer(int64) | no |
| `applicationStartTime` | integer(int64) | no |
| `lastConnectTime` | integer(int64) | no |
| `lastDisconnectTime` | integer(int64) | no |
| `platform` | string | no |
| `runtimeOS` | string | no |
| `brokerId` | integer(int64) | no |
| `serviceEdgeGroupId` | integer(int64) | no |
| `restartTimeInSec` | integer(int64) | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `ctrlChannelStatus` | string (enum: UNKNOWN, ZPN_STATUS_AUTHENTICATED, ZPN_STATUS_DISCONNECTED) | no |
| `privateIp` | string | no |
| `publicIp` | string | no |
| `loneWarrior` | boolean | no |
| `tunnelId` | string | no |
| `previousVersion` | string | no |
| `lastUpgradedTime` | integer(int64) | no |
| `restartInstructions` | string (enum: DELETE, FLUSHDB) | no |
| `upgradeAttempt` | integer(int32) | no |
| `disableAutoUpdate` | boolean | no |
| `sargeVersion` | string | no |
| `zpnSubModuleUpgrade` | array<ZpnSubModuleUpgrade> | no |
| `platformDetail` | string | no |
| `upgradeNowOnce` | boolean | no |

**`ZpnSubModuleUpgrade`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `entityGid` | integer(int64) | yes |
| `entityType` | string (enum: ASSISTANT, PRIVATE_BROKER, MMDB_GEOIP, MMDB_ISP, GUACD, SITE_CONTROLLER) | no |
| `role` | string (enum: ASSISTANT, PRIVATE_BROKER, MMDB_GEOIP, MMDB_ISP, GUACD, SITE_CONTROLLER) | yes |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `previousVersion` | string | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `upgradeTime` | integer(int64) | no |

**`TrustedNetwork`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `networkId` | string | no |
| `zscalerCloud` | string | no |
| `domain` | string | no |
| `masterCustomerId` | string | no |

**`AppConnectorGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | no |
| `description` | string | no |
| `geoLocationId` | integer(int64) | no |
| `versionProfileId` | integer(int64) | no |
| `overrideVersionProfile` | boolean | no |
| `siteId` | string | no |
| `versionProfileName` | string | no |
| `upgradePriority` | string (enum: WEEK, DAY, FORCE_NOW, NOW) | no |
| `versionProfileVisibilityScope` | string (enum: ALL, NONE, CUSTOM) | no |
| `upgradeTimeInSecs` | string | no |
| `upgradeDay` | string | no |
| `siteName` | string | no |
| `connectors` | array<Connector> | no |
| `location` | string | no |
| `latitude` | string | no |
| `city` | string | no |
| `longitude` | string | no |
| `serverGroups` | array<AppServerGroup> | no |
| `dnsQueryType` | string (enum: IPV4_IPV6, IPV4, IPV6) | no |
| `cityCountry` | string | no |
| `countryCode` | string | no |
| `tcpQuickAckApp` | boolean | no |
| `tcpQuickAckAssistant` | boolean | no |
| `tcpQuickAckReadAssistant` | boolean | no |
| `ipAcl` | array<string> | no |
| `praEnabled` | boolean | no |
| `useInDrMode` | boolean | no |
| `connectorGroupType` | string (enum: APP, NP) | no |
| `dcHostingInfo` | string | no |
| `wafDisabled` | boolean | no |
| `npAssistantGroup` | NPAssistantGroup | no |
| `lssAppConnectorGroup` | boolean | no |

**`Connector`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `description` | string | no |
| `fingerprint` | string | no |
| `issuedCertId` | integer(int64) | no |
| `enabled` | boolean | no |
| `ipAcl` | array<string> | no |
| `provisioningKeyId` | integer(int64) | no |
| `connectorType` | string (enum: APP, NP) | no |
| `provisioningKeyName` | string | no |
| `enrollmentCert` | object | no |
| `appConnectorGroupId` | string | no |
| `appConnectorGroupName` | string | no |
| `assistantVersion` | AssistantVersion | no |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `previousVersion` | string | no |
| `lastUpgradeTime` | integer(int64) | no |
| `expectedUpgradeTime` | integer(int64) | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `controlChannelStatus` | string (enum: UNKNOWN, ZPN_STATUS_AUTHENTICATED, ZPN_STATUS_DISCONNECTED) | no |
| `upgradeAttempt` | integer(int32) | no |
| `ctrlBrokerName` | string | no |
| `lastBrokerConnectTime` | integer(int64) | no |
| `lastBrokerConnectTimeDuration` | string | no |
| `sargeVersion` | string | no |
| `lastBrokerDisconnectTime` | integer(int64) | no |
| `lastBrokerDisconnectTimeDuration` | string | no |
| `privateIp` | string | no |
| `publicIp` | string | no |
| `platform` | string | no |
| `runtimeOS` | string | no |
| `applicationStartTime` | integer(int64) | no |
| `latitude` | number(double) | no |
| `longitude` | number(double) | no |
| `location` | string | no |
| `platformDetail` | string | no |
| `zpnSubModuleUpgradeList` | array<ZpnSubModuleUpgrade> | no |
| `npAssistant` | NPAssistant | no |

**`AssistantVersion`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `systemStartTime` | integer(int64) | no |
| `applicationStartTime` | integer(int64) | no |
| `lastBrokerConnectTime` | integer(int64) | no |
| `lastBrokerDisconnectTime` | integer(int64) | no |
| `brokerId` | integer(int64) | no |
| `restartTimeInSec` | integer(int64) | no |
| `disableAutoUpdate` | boolean | no |
| `appConnectorGroupId` | integer(int64) | no |
| `platform` | string | no |
| `runtimeOS` | string | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `ctrlChannelStatus` | string (enum: UNKNOWN, ZPN_STATUS_AUTHENTICATED, ZPN_STATUS_DISCONNECTED) | no |
| `latitude` | number(double) | no |
| `longitude` | number(double) | no |
| `privateIp` | string | no |
| `publicIp` | string | no |
| `loneWarrior` | boolean | no |
| `mtunnelId` | string | no |
| `previousVersion` | string | no |
| `lastUpgradedTime` | integer(int64) | no |
| `upgradeNowOnce` | boolean | no |
| `upgradeAttempt` | integer(int32) | no |
| `sargeVersion` | string | no |
| `platformDetail` | string | no |

**`NPAssistant`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `publicKey` | string | no |
| `publicKeyExpiry` | integer(int64) | no |
| `connectorState` | integer(int32) | no |
| `connectorId` | integer(int64) | yes |

**`AppServerGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | yes |
| `dynamicDiscovery` | boolean | no |
| `description` | string | no |
| `configSpace` | string (enum: DEFAULT, SIEM) | yes |
| `weight` | integer(int32) | no |
| `passive` | boolean | no |
| `extranetEnabled` | boolean | no |
| `zpnErId` | ZpnExtranetResource | no |

**`ZpnExtranetResource`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `ziaErId` | integer(int64) | yes |
| `ziaErName` | string | yes |
| `ziaCloud` | string | yes |
| `ziaOrgId` | integer(int64) | yes |
| `ziaModifiedTime` | integer(int64) | no |

**`NPAssistantGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `appConnectorGroupId` | integer(int64) | yes |
| `lanSubnets` | array<NpLanSubnet> | no |

**`NpLanSubnet`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `description` | string | no |
| `subnet` | string | yes |
| `appConnectorGroupId` | integer(int64) | yes |
| `npDnsNsRecord` | NpDnsNsRecord | no |
| `fqdns` | array<string> | no |
| `npserverips` | array<string> | no |
| `oldAuditString` | string | no |

**`NpDnsNsRecord`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `nameserverIps` | array<string> | yes |
| `fqdn` | array<string> | yes |

**`NameIdDto`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `name` | string | no |

**`ExtranetDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | string | no |
| `id` | string | no |
| `ziaErName` | string | no |
| `zpnErId` | integer(int64) | yes |
| `locationGroupDTO` | array<LocationGroupDTO> | yes |
| `locationDTO` | array<NameIdDto> | yes |

**`LocationGroupDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `name` | string | no |
| `id` | integer(int64) | yes |
| `ziaLocations` | array<NameIdDto> | no |

**`InconsistentConfigDetails`**

| Property | Type | Required |
| --- | --- | --- |
| `application` | array<InconsistentConfigNameReasonDto> | no |
| `sraApplication` | array<InconsistentConfigNameReasonDto> | no |
| `segmentGroup` | array<InconsistentConfigNameReasonDto> | no |
| `serverGroup` | array<InconsistentConfigNameReasonDto> | no |
| `appConnectorGroup` | array<InconsistentConfigNameReasonDto> | no |
| `baCertificate` | array<InconsistentConfigNameReasonDto> | no |
| `postureProfile` | array<InconsistentConfigNameReasonDto> | no |
| `trustedNetwork` | array<InconsistentConfigNameReasonDto> | no |
| `branchConnectorGroup` | array<InconsistentConfigNameReasonDto> | no |
| `cloudConnectorGroup` | array<InconsistentConfigNameReasonDto> | no |
| `samlAttributes` | array<InconsistentConfigNameReasonDto> | no |
| `scimAttributes` | array<InconsistentConfigNameReasonDto> | no |
| `machineGroup` | array<InconsistentConfigNameReasonDto> | no |
| `idp` | array<InconsistentConfigNameReasonDto> | no |
| `location` | array<InconsistentConfigNameReasonDto> | no |
| `workloadTagGroup` | array<InconsistentConfigNameReasonDto> | no |
| `userPortal` | array<InconsistentConfigNameReasonDto> | no |

**`InconsistentConfigNameReasonDto`**

| Property | Type | Required |
| --- | --- | --- |
| `name` | string | no |
| `reason` | string | no |

**`PrivilegedCapabilities`**

| Property | Type | Required |
| --- | --- | --- |
| `microtenantId` | integer(int64) | no |
| `capabilities` | array<string> | no |

**`PrivilegedPortalCapabilities`**

| Property | Type | Required |
| --- | --- | --- |
| `microtenantId` | integer(int64) | no |
| `capabilities` | array<string> | no |

**`ConditionSet`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `microtenantId` | integer(int64) | no |
| `operator` | string (enum: AND, OR) | no |
| `negated` | boolean | yes |
| `operands` | array<Operand> | no |

**`Operand`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `microtenantId` | integer(int64) | no |
| `objectType` | The criteria of the policy. (enum: USER, USER_GROUP, APP, APP_GROUP, LOCATION, WORKLOAD_TAG_GROUP, SAML, POSTURE) | no |
| `lhs` | string | yes |
| `rhs` | string | yes |
| `name` | string | no |
| `idpId` | integer(int64) | no |
| `idpName` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/policy-export","lastmod":"2026-01-06T23:49Z","nid":"1518351"} -->
## Policy Export

- Source: https://help.zscaler.com/legacy-apis/policy-export
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > Policy Export
- Last modified: 2026-01-06T23:49Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `POST /exportPolicies`

Exports the specified policies to a ZIP file. This API request supports exporting a wide range of policy types, and you can view the list of supported policy types in the Enum list included in the request's body parameter.

The API response is one ZIP file containing JSON representation of the exported policies. One JSON file is created for each policy type specified in the request input. For example, if the request body specifies three policy types, such as FIREWALL, BA, and URL_FILTERING, the API response is exported as one ZIP file containing three JSON files, one for each policy type, named firewall.json, ba.json, and url_filtering.json.

- Operation ID: `ExportPolicyResource_getPolicies`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | yes | array<string (enum: BA, FILETYPE_CONTROL, BANDWIDTH_CONTROL, MOBILE_APP_RULE, URL_FILTERING, CUSTOM_CAPP, FIREWALL, DNAT)> | Specifies the list of policy types for which the rules must be exported |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/policy-management","lastmod":"2026-07-31T16:34Z","nid":"1485296"} -->
## Policy Management

- Source: https://help.zscaler.com/legacy-apis/policy-management
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Policy Management
- Last modified: 2026-07-31T16:34Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/admin/customers/{customerId}/policySet/policyType/{policyType}`

Gets the policy set for the specified policy type.

- Operation ID: `getPolicySetByPolicyType_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `policyType` | path | yes | string | Specifies the policy type. The supported values are: `ACCESS_POLICY` or `GLOBAL_POLICY`: Access policy; `TIMEOUT_POLICY` or `REAUTH_POLICY`: Timeout policy; `CLIENT_FORWARDING_POLICY` or `BYPASS_POLICY`: Client forwarding policy; `INSPECTION_POLICY`: AppProtection policy; `CREDENTIAL_POLICY`: Privileged credentials policy; `CAPABILITIES_POLICY`: Privileged capabilities policy; `ISOLATION_POLICY`: Isolation policy; `CLIENTLESS_SESSION_PROTECTION_POLICY`: Browser Protection policy; `REDIRECTION_POLICY`: Redirection policy; `PRIVILEGED_PORTAL_POLICY`: Privileged portals policy |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v2/admin/customers/{customerId}/policySet/{policySetId}/rule`

Adds a new policy rule for the specified policy set.

- Operation ID: `addRuleToPolicySet_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `policySetId` | path | yes | integer(int64) | The unique identifier of the policy. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → PolicyRuleResource

**Responses:**

| Code | Description |
| --- | --- |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/policySet/{policySetId}/rule`

Adds a new policy rule for the specified policy set.

- Operation ID: `addRuleToPolicySet_3`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `policySetId` | path | yes | integer(int64) | The unique identifier of the policy set. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → PolicyRule

**Responses:**

| Code | Description |
| --- | --- |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/config/sessionTerminationOnReauth`

Gets the Bypass during Reauthentication status for the specified customer.

- Operation ID: `getSessionTerminationOnReauth_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/config/sessionTerminationOnReauth`

Adds or updates the Bypass during Reauthentication status for the specified customer.

- Operation ID: `addSessionTerminationOnReauth_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → SessionTerminationOnReauthResource

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/policySet/{policySetId}/reorder`

Bulk reorders all the rules in a policy set. Run this API only once to reorder the rules.

- Operation ID: `reOrderPolicySet_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `policySetId` | path | yes | integer(int64) | The unique identifier of the policy set. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → array<integer(int64)>

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/platform`

Gets all platforms for the specified customer.

- Operation ID: `getListOfPlatform_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/policySet/{policySetId}/rule/{ruleId}/reorder/{newOrder}`

Updates the rule order for the specified ID.

- Operation ID: `reOrderPolicyRule_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `policySetId` | path | yes | integer(int64) | The unique identifier of the policy set. |
| `ruleId` | path | yes | integer(int64) | The unique identifier of a rule in a policy. |
| `newOrder` | path | yes | integer(int32) | The new order of the rule. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v2/admin/customers/{customerId}/policySet/{policySetId}/rule/{ruleId}`

Updates a rule in a policy for the specified ID.

- Operation ID: `updateRuleToPolicySet_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `policySetId` | path | yes | integer(int64) | The unique identifier of the policy set. |
| `ruleId` | path | yes | integer(int64) | The unique identifier of a rule in a policy. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → PolicyRuleResource

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/policySet/rules/policyType/{policyType}`

Gets paginated policy rules for the specified policy type.

- Operation ID: `getPolicyRulesByPage_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `policyType` | path | yes | string | Specifies the policy type. The supported values are: `ACCESS_POLICY` or `GLOBAL_POLICY`: Access policy; `TIMEOUT_POLICY` or `REAUTH_POLICY`: Timeout policy; `CLIENT_FORWARDING_POLICY` or `BYPASS_POLICY`: Client forwarding policy; `INSPECTION_POLICY`: AppProtection policy; `CREDENTIAL_POLICY`: Privileged credentials policy; `CAPABILITIES_POLICY`: Privileged capabilities policy; `ISOLATION_POLICY`: Isolation policy; `CLIENTLESS_SESSION_PROTECTION_POLICY`: Browser Protection policy; `REDIRECTION_POLICY`: Redirection policy; `PRIVILEGED_PORTAL_POLICY`: Privileged portals policy |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The maximum page size is 500. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/clientTypes`

Gets all client types for the specified customer.

- Operation ID: `getListOfClientTypesForCustomer_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/policySet/{policySetId}/rule/{ruleId}`

Gets the rule in a policy for the specified ID.

- Operation ID: `getRuleInPolicySet_3`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `policySetId` | path | yes | integer(int64) | The unique identifier of the policy set. |
| `ruleId` | path | yes | integer(int64) | The unique identifier of a rule in a policy. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/policySet/{policySetId}/rule/{ruleId}`

Updates the rule in a policy for the specified ID.

- Operation ID: `updateRuleToPolicySet_3`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `policySetId` | path | yes | integer(int64) | The unique identifier of the policy set. |
| `ruleId` | path | yes | integer(int64) | The unique identifier of a rule in a policy. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within the Default Microtenant, pass `microtenantId` as `0` when making requests to retrieve data from the Default Microtenant. |

**Request body:** `application/json` → PolicyRule

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/policySet/{policySetId}/rule/{ruleId}`

Deletes the rule in a policy for the specified ID.

- Operation ID: `deleteRuleInPolicySet_3`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `policySetId` | path | yes | integer(int64) | The unique identifier of the policy set. |
| `ruleId` | path | yes | integer(int64) | The unique identifier of a rule in a policy. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v2/admin/customers/{customerId}/riskScoreValues`

Gets the user risk scores for the specified customer.

- Operation ID: `getListOfRiskScoreValuesForCustomer_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |
| `excludeUnknown` | query | no | boolean | Exclude unknown risk scores. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/policySet/rules/policyType/{policyType}/count`

Gets the count of policy rules for the policy type of the ZPA tenant. If you specify only the end time, then you get the overall count up to that end time. If you specify the start time and end time, then you get the count for that time duration.

- Operation ID: `getPolicyRulesCountByPolicyType_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `policyType` | path | yes | string | Specifies the policy type. The supported values are: `ACCESS_POLICY` or `GLOBAL_POLICY`: Access policy; `TIMEOUT_POLICY` or `REAUTH_POLICY`: Timeout policy; `CLIENT_FORWARDING_POLICY` or `BYPASS_POLICY`: Client forwarding policy; `INSPECTION_POLICY`: AppProtection policy; `CREDENTIAL_POLICY`: Privileged credentials policy; `CAPABILITIES_POLICY`: Privileged capabilities policy; `ISOLATION_POLICY`: Isolation policy; `CLIENTLESS_SESSION_PROTECTION_POLICY`: Browser Protection policy; `REDIRECTION_POLICY`: Redirection policy; `PRIVILEGED_PORTAL_POLICY`: Privileged portals policy |
| `search` | query | no | string | The search string to filter policy rules by name. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |
| `startTime` | query | no | integer(int32) | The start time in seconds. The default value is 0. |
| `endTime` | query | no | integer(int32) | The end time in seconds. The default value is the current system time. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/policySet/rules/policyType/{policyType}/application/{applicationId}`

Gets the policy rules for the policy type from the application.

- Operation ID: `getPolicyRulesByAppIdAndPage_2`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `applicationId` | path | yes | integer(int64) | The unique identifier of the application segment. |
| `policyType` | path | yes | string | Specifies the policy type. The supported values are: `ACCESS_POLICY` or `GLOBAL_POLICY`: Access policy; `TIMEOUT_POLICY` or `REAUTH_POLICY`: Timeout policy; `CLIENT_FORWARDING_POLICY` or `BYPASS_POLICY`: Client forwarding policy; `INSPECTION_POLICY`: AppProtection policy; `CREDENTIAL_POLICY`: Privileged credentials policy; `CAPABILITIES_POLICY`: Privileged capabilities policy; `ISOLATION_POLICY`: Isolation policy; `CLIENTLESS_SESSION_PROTECTION_POLICY`: Browser Protection policy; `REDIRECTION_POLICY`: Redirection policy; `PRIVILEGED_PORTAL_POLICY`: Privileged portals policy |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`PolicySet`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | yes |
| `description` | string | no |
| `policyType` | integer(int32) | yes |
| `sorted` | boolean | no |
| `rules` | array<PolicyRule> | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`PolicyRuleResource`**

| Property | Type | Required |
| --- | --- | --- |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | string | no |
| `id` | string | no |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `action` | string (enum: ALLOW, DENY, LOG, RE_AUTH, NEVER, BYPASS, INTERCEPT, NO_DOWNLOAD) | yes |
| `appServerGroups` | array<AppServerGroupResource> | no |
| `serviceEdgeGroups` | array<ServiceEdgeGroupResource> | no |
| `appConnectorGroups` | array<AppConnectorGroupResource> | no |
| `conditions` | array<ConditionSetResource> | no |
| `extranetEnabled` | boolean | no |
| `extranetDTO` | ExtranetDTO | no |
| `customMsg` | string | no |
| `description` | string | no |
| `reauthTimeout` | integer(int32) | no |
| `reauthIdleTimeout` | integer(int32) | no |
| `version` | integer(int64) | no |
| `disabled` | integer(int32) | no |
| `name` | string | yes |
| `policySetId` | integer(int64) | no |
| `ruleOrder` | integer(int32) | no |
| `zpnIsolationProfileId` | integer(int64) | no |
| `priority` | integer(int32) | no |
| `operator` | string (enum: AND, OR) | no |
| `policyType` | integer(int32) | no |
| `actionId` | integer(int64) | no |
| `zpnInspectionProfileId` | integer(int64) | no |
| `zpnInspectionProfileName` | string | no |
| `credential` | NameIdDto | no |
| `credentialPool` | NameIdDto | no |
| `privilegedCapabilities` | PrivilegedCapabilitiesResource | no |
| `privilegedPortalCapabilities` | PrivilegedPortalCapabilitiesResource | no |
| `postActions` | object | no |

**`PolicyRule`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `description` | string | no |
| `ruleOrder` | integer(int32) | no |
| `priority` | integer(int32) | no |
| `policyType` | integer(int32) | yes |
| `operator` | string (enum: AND, OR) | yes |
| `actionId` | integer(int64) | no |
| `action` | string (enum: ALLOW, DENY, LOG, RE_AUTH, NEVER, BYPASS, INTERCEPT, NO_DOWNLOAD) | yes |
| `reauthTimeout` | integer(int32) | no |
| `reauthIdleTimeout` | integer(int32) | no |
| `customMsg` | string | no |
| `disabled` | integer(int32) | no |
| `serviceEdgeGroups` | array<ServiceEdgeGroup> | no |
| `appConnectorGroups` | array<AppConnectorGroup> | no |
| `credential` | NameIdDto | no |
| `credentialPool` | NameIdDto | no |
| `zpnIsolationProfileId` | integer(int64) | no |
| `zpnInspectionProfileId` | integer(int64) | no |
| `postActionTypes` | array<string> | no |
| `postActions` | object | no |
| `zpnInspectionProfileName` | string | no |
| `extranetDTO` | ExtranetDTO | no |
| `extranetEnabled` | boolean | no |
| `inconsistentConfigDetails` | InconsistentConfigDetails | no |
| `policySetId` | integer(int64) | no |
| `privilegedCapabilities` | PrivilegedCapabilities | no |
| `privilegedPortalCapabilities` | PrivilegedPortalCapabilities | no |
| `conditions` | array<ConditionSet> | no |
| `appServerGroups` | array<AppServerGroup> | no |
| `defaultRule` | boolean | no |
| `defaultRuleName` | string | no |

**`SessionTerminationOnReauthResource`**

| Property | Type | Required |
| --- | --- | --- |
| `allowDisableSessionTerminationOnReauth` | boolean | yes |
| `sessionTerminationOnReauth` | boolean | yes |

**`PageListPolicyRule`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<PolicyRule> | no |

**`Count`**

| Property | Type | Required |
| --- | --- | --- |
| `count` | integer(int64) | no |

**`PageListNameIdDto`**

| Property | Type | Required |
| --- | --- | --- |
| `currentCount` | integer(int64) | no |
| `list` | array<NameIdDto> | no |
| `message` | string | no |
| `totalCount` | integer(int64) | no |
| `totalPages` | integer(int32) | no |

**`ServiceEdgeGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | no |
| `description` | string | no |
| `geoLocationId` | integer(int64) | no |
| `versionProfileId` | integer(int64) | no |
| `overrideVersionProfile` | boolean | no |
| `siteId` | string | no |
| `versionProfileName` | string | no |
| `upgradePriority` | string (enum: WEEK, DAY, FORCE_NOW, NOW) | no |
| `versionProfileVisibilityScope` | string (enum: ALL, NONE, CUSTOM) | no |
| `upgradeTimeInSecs` | string | no |
| `upgradeDay` | string | no |
| `siteName` | string | no |
| `isPublic` | string | no |
| `location` | string | no |
| `serviceEdges` | array<ServiceEdge> | no |
| `latitude` | string | no |
| `city` | string | no |
| `longitude` | string | no |
| `cityCountry` | string | no |
| `countryCode` | string | no |
| `useInDrMode` | boolean | no |
| `graceDistanceEnabled` | boolean | no |
| `graceDistanceValue` | number(double) | no |
| `graceDistanceValueUnit` | string (enum: MILES, KMS) | no |
| `trustedNetworks` | array<TrustedNetwork> | no |
| `altCloud` | string | no |

**`ServiceEdge`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `description` | string | no |
| `fingerprint` | string | no |
| `issuedCertId` | integer(int64) | no |
| `enabled` | boolean | no |
| `ipAcl` | array<string> | no |
| `listenIps` | array<string> | no |
| `publishIps` | array<string> | no |
| `publishIpv6` | boolean | no |
| `privateBrokerVersion` | PrivateBrokerVersion | no |
| `provisioningKeyId` | integer(int64) | no |
| `provisioningKeyName` | string | no |
| `serviceEdgeGroupId` | string | no |
| `serviceEdgeGroupName` | string | no |
| `enrollmentCert` | object | no |
| `latitude` | string | no |
| `longitude` | string | no |
| `location` | string | no |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `previousVersion` | string | no |
| `lastUpgradeTime` | integer(int64) | no |
| `expectedUpgradeTime` | integer(int64) | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `controlChannelStatus` | string (enum: UNKNOWN, ZPN_STATUS_AUTHENTICATED, ZPN_STATUS_DISCONNECTED) | no |
| `upgradeAttempt` | integer(int32) | no |
| `ctrlBrokerName` | string | no |
| `lastBrokerConnectTime` | integer(int64) | no |
| `lastBrokerConnectTimeDuration` | string | no |
| `lastBrokerDisconnectTime` | integer(int64) | no |
| `lastBrokerDisconnectTimeDuration` | string | no |
| `privateIp` | string | no |
| `publicIp` | string | no |
| `platform` | string | no |
| `runtimeOS` | string | no |
| `applicationStartTime` | integer(int64) | no |
| `sargeVersion` | string | no |
| `platformDetail` | string | no |
| `zpnSubModuleUpgradeList` | array<ZpnSubModuleUpgrade> | no |

**`PrivateBrokerVersion`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `systemStartTime` | integer(int64) | no |
| `applicationStartTime` | integer(int64) | no |
| `lastConnectTime` | integer(int64) | no |
| `lastDisconnectTime` | integer(int64) | no |
| `platform` | string | no |
| `runtimeOS` | string | no |
| `brokerId` | integer(int64) | no |
| `serviceEdgeGroupId` | integer(int64) | no |
| `restartTimeInSec` | integer(int64) | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `ctrlChannelStatus` | string (enum: UNKNOWN, ZPN_STATUS_AUTHENTICATED, ZPN_STATUS_DISCONNECTED) | no |
| `privateIp` | string | no |
| `publicIp` | string | no |
| `loneWarrior` | boolean | no |
| `tunnelId` | string | no |
| `previousVersion` | string | no |
| `lastUpgradedTime` | integer(int64) | no |
| `restartInstructions` | string (enum: DELETE, FLUSHDB) | no |
| `upgradeAttempt` | integer(int32) | no |
| `disableAutoUpdate` | boolean | no |
| `sargeVersion` | string | no |
| `zpnSubModuleUpgrade` | array<ZpnSubModuleUpgrade> | no |
| `platformDetail` | string | no |
| `upgradeNowOnce` | boolean | no |

**`ZpnSubModuleUpgrade`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `entityGid` | integer(int64) | yes |
| `entityType` | string (enum: ASSISTANT, PRIVATE_BROKER, MMDB_GEOIP, MMDB_ISP, GUACD, SITE_CONTROLLER) | no |
| `role` | string (enum: ASSISTANT, PRIVATE_BROKER, MMDB_GEOIP, MMDB_ISP, GUACD, SITE_CONTROLLER) | yes |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `previousVersion` | string | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `upgradeTime` | integer(int64) | no |

**`TrustedNetwork`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `networkId` | string | no |
| `zscalerCloud` | string | no |
| `domain` | string | no |
| `masterCustomerId` | string | no |

**`AppConnectorGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | no |
| `description` | string | no |
| `geoLocationId` | integer(int64) | no |
| `versionProfileId` | integer(int64) | no |
| `overrideVersionProfile` | boolean | no |
| `siteId` | string | no |
| `versionProfileName` | string | no |
| `upgradePriority` | string (enum: WEEK, DAY, FORCE_NOW, NOW) | no |
| `versionProfileVisibilityScope` | string (enum: ALL, NONE, CUSTOM) | no |
| `upgradeTimeInSecs` | string | no |
| `upgradeDay` | string | no |
| `siteName` | string | no |
| `connectors` | array<Connector> | no |
| `location` | string | no |
| `latitude` | string | no |
| `city` | string | no |
| `longitude` | string | no |
| `serverGroups` | array<AppServerGroup> | no |
| `dnsQueryType` | string (enum: IPV4_IPV6, IPV4, IPV6) | no |
| `cityCountry` | string | no |
| `countryCode` | string | no |
| `tcpQuickAckApp` | boolean | no |
| `tcpQuickAckAssistant` | boolean | no |
| `tcpQuickAckReadAssistant` | boolean | no |
| `ipAcl` | array<string> | no |
| `praEnabled` | boolean | no |
| `useInDrMode` | boolean | no |
| `connectorGroupType` | string (enum: APP, NP) | no |
| `dcHostingInfo` | string | no |
| `wafDisabled` | boolean | no |
| `npAssistantGroup` | NPAssistantGroup | no |
| `lssAppConnectorGroup` | boolean | no |

**`Connector`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `description` | string | no |
| `fingerprint` | string | no |
| `issuedCertId` | integer(int64) | no |
| `enabled` | boolean | no |
| `ipAcl` | array<string> | no |
| `provisioningKeyId` | integer(int64) | no |
| `connectorType` | string (enum: APP, NP) | no |
| `provisioningKeyName` | string | no |
| `enrollmentCert` | object | no |
| `appConnectorGroupId` | string | no |
| `appConnectorGroupName` | string | no |
| `assistantVersion` | AssistantVersion | no |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `previousVersion` | string | no |
| `lastUpgradeTime` | integer(int64) | no |
| `expectedUpgradeTime` | integer(int64) | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `controlChannelStatus` | string (enum: UNKNOWN, ZPN_STATUS_AUTHENTICATED, ZPN_STATUS_DISCONNECTED) | no |
| `upgradeAttempt` | integer(int32) | no |
| `ctrlBrokerName` | string | no |
| `lastBrokerConnectTime` | integer(int64) | no |
| `lastBrokerConnectTimeDuration` | string | no |
| `sargeVersion` | string | no |
| `lastBrokerDisconnectTime` | integer(int64) | no |
| `lastBrokerDisconnectTimeDuration` | string | no |
| `privateIp` | string | no |
| `publicIp` | string | no |
| `platform` | string | no |
| `runtimeOS` | string | no |
| `applicationStartTime` | integer(int64) | no |
| `latitude` | number(double) | no |
| `longitude` | number(double) | no |
| `location` | string | no |
| `platformDetail` | string | no |
| `zpnSubModuleUpgradeList` | array<ZpnSubModuleUpgrade> | no |
| `npAssistant` | NPAssistant | no |

**`AssistantVersion`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `systemStartTime` | integer(int64) | no |
| `applicationStartTime` | integer(int64) | no |
| `lastBrokerConnectTime` | integer(int64) | no |
| `lastBrokerDisconnectTime` | integer(int64) | no |
| `brokerId` | integer(int64) | no |
| `restartTimeInSec` | integer(int64) | no |
| `disableAutoUpdate` | boolean | no |
| `appConnectorGroupId` | integer(int64) | no |
| `platform` | string | no |
| `runtimeOS` | string | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `ctrlChannelStatus` | string (enum: UNKNOWN, ZPN_STATUS_AUTHENTICATED, ZPN_STATUS_DISCONNECTED) | no |
| `latitude` | number(double) | no |
| `longitude` | number(double) | no |
| `privateIp` | string | no |
| `publicIp` | string | no |
| `loneWarrior` | boolean | no |
| `mtunnelId` | string | no |
| `previousVersion` | string | no |
| `lastUpgradedTime` | integer(int64) | no |
| `upgradeNowOnce` | boolean | no |
| `upgradeAttempt` | integer(int32) | no |
| `sargeVersion` | string | no |
| `platformDetail` | string | no |

**`NPAssistant`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `publicKey` | string | no |
| `publicKeyExpiry` | integer(int64) | no |
| `connectorState` | integer(int32) | no |
| `connectorId` | integer(int64) | yes |

**`AppServerGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | yes |
| `dynamicDiscovery` | boolean | no |
| `description` | string | no |
| `configSpace` | string (enum: DEFAULT, SIEM) | yes |
| `weight` | integer(int32) | no |
| `passive` | boolean | no |
| `extranetEnabled` | boolean | no |
| `zpnErId` | ZpnExtranetResource | no |

**`ZpnExtranetResource`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `ziaErId` | integer(int64) | yes |
| `ziaErName` | string | yes |
| `ziaCloud` | string | yes |
| `ziaOrgId` | integer(int64) | yes |
| `ziaModifiedTime` | integer(int64) | no |

**`NPAssistantGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `appConnectorGroupId` | integer(int64) | yes |
| `lanSubnets` | array<NpLanSubnet> | no |

**`NpLanSubnet`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `description` | string | no |
| `subnet` | string | yes |
| `appConnectorGroupId` | integer(int64) | yes |
| `npDnsNsRecord` | NpDnsNsRecord | no |
| `fqdns` | array<string> | no |
| `npserverips` | array<string> | no |
| `oldAuditString` | string | no |

**`NpDnsNsRecord`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `nameserverIps` | array<string> | yes |
| `fqdn` | array<string> | yes |

**`NameIdDto`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `name` | string | no |

**`ExtranetDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | string | no |
| `id` | string | no |
| `ziaErName` | string | no |
| `zpnErId` | integer(int64) | yes |
| `locationGroupDTO` | array<LocationGroupDTO> | yes |
| `locationDTO` | array<NameIdDto> | yes |

**`LocationGroupDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `name` | string | no |
| `id` | integer(int64) | yes |
| `ziaLocations` | array<NameIdDto> | no |

**`InconsistentConfigDetails`**

| Property | Type | Required |
| --- | --- | --- |
| `application` | array<InconsistentConfigNameReasonDto> | no |
| `sraApplication` | array<InconsistentConfigNameReasonDto> | no |
| `segmentGroup` | array<InconsistentConfigNameReasonDto> | no |
| `serverGroup` | array<InconsistentConfigNameReasonDto> | no |
| `appConnectorGroup` | array<InconsistentConfigNameReasonDto> | no |
| `baCertificate` | array<InconsistentConfigNameReasonDto> | no |
| `postureProfile` | array<InconsistentConfigNameReasonDto> | no |
| `trustedNetwork` | array<InconsistentConfigNameReasonDto> | no |
| `branchConnectorGroup` | array<InconsistentConfigNameReasonDto> | no |
| `cloudConnectorGroup` | array<InconsistentConfigNameReasonDto> | no |
| `samlAttributes` | array<InconsistentConfigNameReasonDto> | no |
| `scimAttributes` | array<InconsistentConfigNameReasonDto> | no |
| `machineGroup` | array<InconsistentConfigNameReasonDto> | no |
| `idp` | array<InconsistentConfigNameReasonDto> | no |
| `location` | array<InconsistentConfigNameReasonDto> | no |
| `workloadTagGroup` | array<InconsistentConfigNameReasonDto> | no |
| `userPortal` | array<InconsistentConfigNameReasonDto> | no |

**`InconsistentConfigNameReasonDto`**

| Property | Type | Required |
| --- | --- | --- |
| `name` | string | no |
| `reason` | string | no |

**`PrivilegedCapabilities`**

| Property | Type | Required |
| --- | --- | --- |
| `microtenantId` | integer(int64) | no |
| `capabilities` | array<string> | no |

**`PrivilegedPortalCapabilities`**

| Property | Type | Required |
| --- | --- | --- |
| `microtenantId` | integer(int64) | no |
| `capabilities` | array<string> | no |

**`ConditionSet`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `microtenantId` | integer(int64) | no |
| `operator` | string (enum: AND, OR) | no |
| `negated` | boolean | yes |
| `operands` | array<Operand> | no |

**`Operand`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `microtenantId` | integer(int64) | no |
| `objectType` | The criteria of the policy. (enum: USER, USER_GROUP, APP, APP_GROUP, LOCATION, WORKLOAD_TAG_GROUP, SAML, POSTURE) | no |
| `lhs` | string | yes |
| `rhs` | string | yes |
| `name` | string | no |
| `idpId` | integer(int64) | no |
| `idpName` | string | no |

**`AppServerGroupResource`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `name` | string | no |

**`ServiceEdgeGroupResource`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `name` | string | no |

**`AppConnectorGroupResource`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `name` | string | no |

**`ConditionSetResource`**

| Property | Type | Required |
| --- | --- | --- |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | string | no |
| `id` | string | no |
| `negated` | boolean | no |
| `operands` | array<OperandResource> | no |
| `operator` | string (enum: AND, OR) | no |
| `setIds` | array<integer(int64)> | no |

**`OperandResource`**

| Property | Type | Required |
| --- | --- | --- |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | string | no |
| `id` | string | no |
| `entryValues` | array<LhsRhsValueResource> | no |
| `idpId` | integer(int64) | no |
| `idpName` | string | no |
| `objectType` | string (enum: USER, USER_GROUP, APP, APP_GROUP, LOCATION, WORKLOAD_TAG_GROUP, SAML, POSTURE) | yes |
| `values` | array<string> | no |
| `commonProperties` | Operand | no |

**`LhsRhsValueResource`**

| Property | Type | Required |
| --- | --- | --- |
| `lhs` | string | no |
| `rhs` | string | no |

**`PrivilegedCapabilitiesResource`**

| Property | Type | Required |
| --- | --- | --- |
| `capabilities` | array<string> | no |

**`PrivilegedPortalCapabilitiesResource`**

| Property | Type | Required |
| --- | --- | --- |
| `capabilities` | array<string> | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/policy-management-1","lastmod":"2026-01-27T04:24Z","nid":"1528421"} -->
## Policy Management

- Source: https://help.zscaler.com/legacy-apis/policy-management-1
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > Zscaler Cloud & Branch Connector API > API Developer & Reference Guide > Reference Guide > Policy Management
- Last modified: 2026-01-27T04:24Z

**Servers:** `https://{host}:{port}/wapi/v1`, `https://{host}:{port}/api/v1`

### `GET /ecRules/ecRdr`

Retrieves the list of traffic forwarding rules. To learn more, see

[About Traffic Forwarding](https://help.zscaler.com/cloud-branch-connector/about-traffic-forwarding)

.

- Operation ID: `EcRuleZResource_getForwardingRules`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleName` | query | no | string | The search string used to match against the rule name. |
| `ruleOrder` | query | no | string | The search string used to match against the rule order. |
| `ruleDescription` | query | no | string | The search string used to match against the rule description. |
| `ruleForwardMethod` | query | no | string (enum: INVALID, DIRECT, PROXYCHAIN, ZIA, ZPA, ECZPA, ECSELF, DROP) | The search string used to match against the rule forwarding method. Supported forwarding methods are ZIA, ZPA, DIRECT, and DROP. |
| `location` | query | no | string | The search string used to match against the locations or sublocations used in rule configurations. |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. The default size is 50. |
| `sortBy` | query | no | string | Rule parameter by which the list must be sorted. |
| `sortOrder` | query | no | string (enum: asc, desc, ruleExecution) | Specifies whether the list must be sorted by ascending or descending rule order or by rule execution order. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `POST /ecRules/ecRdr`

Creates a new traffic forwarding rule. To learn more, see

[Configuring Traffic Forwarding Rules](https://help.zscaler.com/cloud-branch-connector/configuring-traffic-forwarding-rule)

.

- Operation ID: `EcRuleZResource_createRdrRule`

**Request body:** `application/json` → ForwardingRule

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `GET /ecRules/ecRdr/{ruleId}`

Retrieves the traffic forwarding rule configuration based on the specified forwarding rule ID.

- Operation ID: `EcRuleZResource_getForwardingRuleById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | integer(int32) | The ID of the forwarding rule. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `PUT /ecRules/ecRdr/{ruleId}`

Updates a traffic forwarding rule configuration based on the specified ID.

- Operation ID: `EcRuleZResource_updateEcRdrRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | integer(int32) | ID of the rule. |

**Request body:** `application/json` → ForwardingRule

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `DELETE /ecRules/ecRdr/{ruleId}`

Deletes the traffic forwarding rule configuration based on the specified ID.

- Operation ID: `EcRuleZResource_deleteRdrForwardingRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | integer(int32) | The ID of the forwarding rule. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `GET /ecRules/ecRdr/count`

Retrieves the count of traffic forwarding rules available in the Cloud & Branch Connector Admin Portal.

- Operation ID: `EcRuleZResource_getForwardingRulesCount`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `predefinedRuleCount` | query | no | boolean | Indicates whether the count of predefined rules is retrieved (true) or the count all rules is retrieved (false). |
| `ruleName` | query | no | string | The search string used to match against the rule name. |
| `ruleOrder` | query | no | string | The search string used to match against the rule order. |
| `ruleDescription` | query | no | string | The search string used to match against the rule description. |
| `ruleForwardMethod` | query | no | string (enum: INVALID, DIRECT, PROXYCHAIN, ZIA, ZPA, ECZPA, ECSELF, DROP) | The search string used to match against the rule forwarding method. Supported values are `ZIA`, `ZPA`, `DIRECT`, and `DROP`. |
| `location` | query | no | string | The search string used to match against the locations or sublocations used in rule configurations. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### Schemas

**`EcForwardingRule`**

| Property | Type | Required |
| --- | --- | --- |
| `accessControl` | string (enum: NONE, READ_ONLY, READ_WRITE) | no |
| `appServiceGroups` | array<EntityReference> | no |
| `blockResponseCode` | string (enum: ANY, NONE, FORMERR, SERVFAIL, NXDOMAIN, NOTIMP, REFUSED, YXDOMAIN) | no |
| `departments` | array<EntityReference> | no |
| `description` | string | no |
| `destAddresses` | array<string> | no |
| `destCountries` | array<string (enum: ANY, NONE, COUNTRY_AD, COUNTRY_AE, COUNTRY_AF, COUNTRY_AG, COUNTRY_AI, COUNTRY_AL)> | no |
| `destIpCategories` | array<string> | no |
| `destIpGroups` | array<EntityReference> | no |
| `destIpv6Groups` | array<EntityReference> | no |
| `deviceGroups` | array<EntityReference> | no |
| `devices` | array<EntityReference> | no |
| `ecGroups` | array<EntityReference> | no |
| `forwardMethod` | string (enum: INVALID, DIRECT, PROXYCHAIN, ZIA, ZPA, ECZPA, ECSELF, DROP) | no |
| `groups` | array<EntityReference> | no |
| `id` | integer(int32) | no |
| `labels` | array<EntityReference> | no |
| `lastModifiedBy` | EntityReference | no |
| `lastModifiedTime` | integer(int32) | no |
| `locationGroups` | array<EntityReference> | no |
| `locations` | array<EntityReference> | no |
| `name` | string | yes |
| `nwApplicationGroups` | array<EntityReference> | no |
| `nwApplications` | array<string (enum: NOT_AVAILABLE, APNS, APPSTORE, DICT, EPM, GARP, ICLOUD, IOS_OTA_UPDATE)> | no |
| `nwServiceGroups` | array<EntityReference> | no |
| `nwServices` | array<EntityReference> | no |
| `order` | integer(int32) | yes |
| `proxyGateway` | EntityReference | no |
| `rank` | integer(int32) | no |
| `resCategories` | array<string> | no |
| `sourceCountries` | array<string (enum: ANY, NONE, COUNTRY_AD, COUNTRY_AE, COUNTRY_AF, COUNTRY_AG, COUNTRY_AI, COUNTRY_AL)> | no |
| `sourceIpGroupExclusion` | boolean | no |
| `srcIpGroups` | array<EntityReference> | no |
| `srcIps` | array<string> | no |
| `srcIpv6Groups` | array<EntityReference> | no |
| `state` | string (enum: DISABLED, ENABLED) | no |
| `templateSyncedRuleFlag` | string (enum: NONE, SMRULEF1_SECURELYSHARE_SUPPORT_OLD_VERSIONS, SMRULEF1_TEMPLATE_SYNCED_RULE, SMRULEF1_CASB_DOMAIN_PROFILE_IDS_INCLUDE, SMRULEF1_CASB_EMAIL_PROFILE_IDS_INCLUDE) | no |
| `timeWindows` | array<EntityReference> | no |
| `type` | string (enum: FIREWALL, DNS, DNAT, SNAT, FORWARDING, INTRUSION_PREVENTION, EC_DNS, EC_RDR) | no |
| `users` | array<EntityReference> | no |
| `wanSelection` | string (enum: SMRULEF_ZPA_BROKERS_RULE, SMRULEF_APPC_DYNAMIC_SRC_IPGROUP, SMRULEF_EXCL_SRC_IP, BALANCED_RULE, BESTLINK_RULE) | no |
| `zpaAppSegments` | array<AppSegment> | no |
| `zpaApplicationSegmentGroups` | array<ZpaApplicationSegmentGroup> | no |
| `zpaApplicationSegments` | array<ZpaApplicationSegment> | no |
| `zpaBrokerRule` | boolean | no |
| `zpaGateway` | EntityReference | no |

**`ForwardingRule`**

| Property | Type | Required |
| --- | --- | --- |
| `accessControl` | string (enum: NONE, READ_ONLY, READ_WRITE) | no |
| `id` | integer(int32) | no |
| `name` | string | yes |
| `type` | string (enum: FIREWALL, DNS, DNAT, SNAT, FORWARDING, INTRUSION_PREVENTION, EC_DNS, EC_RDR) | no |
| `order` | integer(int32) | no |
| `rank` | integer(int32) | no |
| `locations` | array<EntityReference> | no |
| `locationGroups` | array<EntityReference> | no |
| `ecGroups` | array<EntityReference> | no |
| `departments` | array<EntityReference> | no |
| `groups` | array<EntityReference> | no |
| `users` | array<EntityReference> | no |
| `forwardMethod` | string (enum: INVALID, DIRECT, PROXYCHAIN, ZIA, ZPA, ECZPA, ECSELF, DROP) | no |
| `state` | string (enum: DISABLED, ENABLED) | no |
| `description` | string | no |
| `lastModifiedTime` | integer(int32) | no |
| `lastModifiedBy` | EntityReference | no |
| `srcIps` | array<string> | no |
| `srcIpGroups` | array<EntityReference> | no |
| `srcIpv6Groups` | array<EntityReference> | no |
| `destAddresses` | array<string> | no |
| `destIpCategories` | array<string (enum: ANY, NONE, OTHER_ADULT_MATERIAL, ADULT_THEMES, LINGERIE_BIKINI, NUDITY, PORNOGRAPHY, SEXUALITY)> | no |
| `resCategories` | array<string (enum: ANY, NONE, OTHER_ADULT_MATERIAL, ADULT_THEMES, LINGERIE_BIKINI, NUDITY, PORNOGRAPHY, SEXUALITY)> | no |
| `destCountries` | array<string (enum: ANY, NONE, COUNTRY_AD, COUNTRY_AE, COUNTRY_AF, COUNTRY_AG, COUNTRY_AI, COUNTRY_AL)> | no |
| `destIpGroups` | array<EntityReference> | no |
| `destIpv6Groups` | array<EntityReference> | no |
| `nwServices` | array<EntityReference> | no |
| `nwServiceGroups` | array<EntityReference> | no |
| `appServiceGroups` | array<EntityReference> | no |
| `proxyGateway` | EntityReference | no |
| `zpaAppSegments` | array<AppSegment> | no |
| `zpaGateway` | EntityReference | no |
| `zpaBrokerRule` | boolean | no |
| `devices` | array<EntityReference> | no |
| `deviceGroups` | array<EntityReference> | no |
| `labels` | array<EntityReference> | no |
| `timeWindows` | array<EntityReference> | no |
| `nwApplications` | array<string (enum: NOT_AVAILABLE, APNS, APPSTORE, DICT, EPM, GARP, ICLOUD, IOS_OTA_UPDATE)> | no |
| `nwApplicationGroups` | array<EntityReference> | no |

**`EntityReference`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `name` | string | no |
| `isNameL10nTag` | boolean | no |
| `extensions` | object | no |
| `deleted` | boolean | no |
| `externalId` | string | no |
| `associationTime` | integer(int32) | no |

**`AppSegment`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `externalId` | integer(int64) | no |
| `zpaTenantId` | integer(int64) | no |

**`ZpaApplicationSegmentGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `deleted` | boolean | no |
| `id` | integer(int32) | no |
| `name` | string | no |
| `zpaAppSegmentsCount` | integer(int32) | no |
| `zpaId` | integer(int64) | no |

**`ZpaApplicationSegment`**

| Property | Type | Required |
| --- | --- | --- |
| `deleted` | boolean | no |
| `description` | string | no |
| `id` | integer(int32) | no |
| `name` | string | no |
| `zpaId` | integer(int64) | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/policy-resources","lastmod":"2026-09-09T03:07Z","nid":"1528426"} -->
## Policy Resources

- Source: https://help.zscaler.com/legacy-apis/policy-resources
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > Zscaler Cloud & Branch Connector API > API Developer & Reference Guide > Reference Guide > Policy Resources
- Last modified: 2026-09-09T03:07Z

**Servers:** `https://{host}:{port}/wapi/v1`, `https://{host}:{port}/api/v1`

### `GET /ipSourceGroups`

Retrieves the list of source IP groups. To learn more, see

[About Source IP Groups](https://help.zscaler.com/cloud-branch-connector/about-source-ip-groups)

.

- Operation ID: `IpSourceGroupZResource_getSourceIpGroups`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `search` | query | no | string | The search string used to match against the source IP group parameters, such as name and IP addresses. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `POST /ipSourceGroups`

Adds a new custom source IP group. To learn more, see

[Configuring Source IP Groups](https://help.zscaler.com/cloud-branch-connector/configuring-source-ip-groups)

.

- Operation ID: `IpSourceGroupZResource_addSourceIpGroup`

**Request body:** `application/json` → IpGroup

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `GET /ipSourceGroups/lite`

Retrieves the list of source IP groups. This request retrieves basic information about the source IP groups, such as name and ID. For extensive details, use the

`GET /ipSourceGroups`

request.

- Operation ID: `IpSourceGroupZResource_getSourceIpGroupsLite`

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `GET /ipSourceGroups/{ipGroupId}`

Retrieves the source IP group based on the specified source IP group ID.

- Operation ID: `IpSourceGroupZResource_getSourceIpGroupById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ipGroupId` | path | yes | integer(int32) | The ID of the source IP group. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `PUT /ipSourceGroups/{ipGroupId}`

Updates the source IP group based on the specified ID.

- Operation ID: `IpSourceGroupZResource_editSourceIpGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ipGroupId` | path | yes | integer(int32) | ID of the source IP group. |

**Request body:** `application/json` → IpGroup

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `DELETE /ipSourceGroups/{ipGroupId}`

Deletes a source IP group based on the specified ID.

- Operation ID: `IpSourceGroupZResource_deleteSourceIpGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ipGroupId` | path | yes | integer(int32) | ID of the source IP group. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `GET /ipDestinationGroups`

Retrieves the list of destination IP groups. To learn more, see

[About Destination IP Groups](https://help.zscaler.com/cloud-branch-connector/about-destination-ip-groups)

.

- Operation ID: `IpDestinationGroupZResource_getDestinationIpGroups`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `excludeType` | query | no | string (enum: DSTN_IP, DSTN_FQDN, DSTN_DOMAIN, DSTN_OTHER) | Specifies the destination IP group type that must be excluded from the list. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `POST /ipDestinationGroups`

Adds a new custom destination IP group. To learn more, see

[Configuring Destination IP Groups](https://help.zscaler.com/cloud-branch-connector/configuring-destination-ip-groups)

.

- Operation ID: `IpDestinationGroupZResource_addDestinationIpGroup`

**Request body:** `application/json` → DestinationIpGroup

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `GET /ipDestinationGroups/lite`

Retrieves the list of destination IP groups. This request retrieves basic information about the destination IP groups, ID, name, and type. For extensive details, use the

`GET /ipDestinationGroups`

request.

- Operation ID: `IpDestinationGroupZResource_getDestinationIpGroupsLite`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `type` | query | no | array<string (enum: DSTN_IP, DSTN_FQDN, DSTN_DOMAIN, DSTN_OTHER)> | List of destination IP group types that must be included in the response. |
| `excludeType` | query | no | string (enum: DSTN_IP, DSTN_FQDN, DSTN_DOMAIN, DSTN_OTHER) | Specifies the destination IP group type that must be excluded from the response. |
| `includeURLCategory` | query | no | boolean | Not applicable to Cloud & Branch Connector. |
| `search` | query | no | string | The search string used to match against the destination IP group parameters. |
| `includeZscalerDomains` | query | no | boolean | A Boolean field indicating whether or not to include the automatically generated **Zscaler Domains** group in the response. |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. The default size is 100. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `GET /ipDestinationGroups/{ipGroupId}`

Retrieves the destination IP group based on the specified source IP group ID.

- Operation ID: `IpDestinationGroupZResource_getDestinationIpGroupById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ipGroupId` | path | yes | integer(int32) | ID of the destination IP group. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `PUT /ipDestinationGroups/{ipGroupId}`

Updates the destination IP group based on the specified ID.

- Operation ID: `IpDestinationGroupZResource_editDestinationIpGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ipGroupId` | path | yes | integer(int32) | ID of the destination IP group. |

**Request body:** `application/json` → DestinationIpGroup

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `DELETE /ipDestinationGroups/{ipGroupId}`

Deletes the destination IP group based on the specified ID. Default destination groups that are automatically created cannot be deleted.

- Operation ID: `IpDestinationGroupZResource_deleteDestinationIpGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ipGroupId` | path | yes | integer(int32) | ID of the group. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `GET /ipGroups`

Retrieves the list of IP pools. To learn more, see

[About IP Pool](https://help.zscaler.com/cloud-branch-connector/about-ip-pool)

.

- Operation ID: `IpGroupZResource_getIpGroups`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `search` | query | no | string | The search string used to match against the IP pool parameters. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `POST /ipGroups`

Adds a new custom IP pool. To learn more, see

[Configuring IP Pool](https://help.zscaler.com/cloud-branch-connector/about-ip-pool)

.

- Operation ID: `IpGroupZResource_addIpGroup`

**Request body:** `application/json` → IpGroup

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `GET /ipGroups/lite`

Retrieves the list of IP pools. This request retrieves basic information about the IP pools, such as name and ID. For extensive details, use the

`GET /ipGroups`

request.

- Operation ID: `IpGroupZResource_getIpGroupsLite`

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `GET /ipGroups/{ipGroupId}`

Retrieves an IP pool based on the specified ID.

- Operation ID: `IpGroupZResource_getIpGroupById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ipGroupId` | path | yes | integer(int32) | The IP group ID. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `PUT /ipGroups/{ipGroupId}`

Updates the IP pool based on the specified ID.

- Operation ID: `IpGroupZResource_editIpGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ipGroupId` | path | yes | integer(int32) | The IP group ID. |

**Request body:** `application/json` → IpGroup

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `DELETE /ipGroups/{ipGroupId}`

Deletes an IP pool based on the specified ID.

- Operation ID: `IpGroupZResource_deleteIpGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ipGroupId` | path | yes | integer(int32) | ID of the IP pool. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `GET /networkServices`

Retrieves the list of all network services. The search parameters find matching values within the

`name`

or

`description`

attributes.

- Operation ID: `NetworkServiceResource_getNetworkServices`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `locale` | query | no | string | When set to one of the supported locales (i.e., en-US, de-DE, es-ES, fr-FR, ja-JP, zh-CN), the network service's description is localized into the requested language. |
| `search` | query | no | string | The search string used to match against a service's name or description attributes. |
| `protocol` | query | no | string (enum: ICMP, TCP, UDP, GRE, ESP, OTHER) | Filter based on the network service protocol. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `POST /networkServices`

Creates a new network service.

- Operation ID: `NetworkServiceResource_addCustomNetworkService`

**Request body:** `application/json` → NetworkService

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `GET /networkServices/{serviceid}`

Retrieves the network service information for the specified service ID.

- Operation ID: `NetworkServiceZResource_getNetworkServiceById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `serviceid` | path | yes | integer(int32) | ID of the network service. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `PUT /networkServices/{serviceid}`

Updates the network service information for the specified service ID.

- Operation ID: `NetworkServiceResource_editNetworkService`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `serviceid` | path | yes | integer(int32) | ID of the network service. |

**Request body:** `application/json` → NetworkService

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `DELETE /networkServices/{serviceid}`

Deletes the network service for the specified ID.

- Operation ID: `NetworkServiceResource_deleteCustomNetworkService`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `serviceid` | path | yes | integer(int32) | ID of the network service. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `GET /networkServiceGroups`

Retrieves the list of network service groups. To learn more, see

[About Network Service Groups](https://help.zscaler.com/cloud-branch-connector/about-network-service-groups)

.

- Operation ID: `NetworkServiceGroupZResource_getNetworkServiceGroups`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `search` | query | no | string | The search string used to match against the network service group parameters. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### `POST /networkServiceGroups`

Updates the list of network service groups.

- Operation ID: `NetworkServiceGroupZResource_addCustomNetworkServiceGroup`

**Request body:** `application/json` → NetworkServiceGroup

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `GET /networkServiceGroups/{serviceGroupId}`

Retrieves the network service group based on the service group ID.

- Operation ID: `NetworkServiceGroupZResource_getNetworkServiceGroupById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `serviceGroupId` | path | yes | integer(int32) | The ID of the network service group. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `PUT /networkServiceGroups/{serviceGroupId}`

Creates a network service group based on the service group ID.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `NetworkServiceGroupZResource_editNetworkServiceGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `serviceGroupId` | path | yes | integer(int32) | The ID of the network service group. |

**Request body:** `application/json` → NetworkServiceGroup

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `DELETE /networkServiceGroups/{serviceGroupId}`

Deletes the network service group based on the service group ID.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `NetworkServiceGroupZResource_deleteCustomNetworkServiceGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `serviceGroupId` | path | yes | integer(int32) | The ID of the network service group. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `GET /zpaResources/applicationSegments`

Retrieves the list of ZPA application segments that be configured in traffic forwarding rule criteria. To learn more, see

[Configuring Traffic Forwarding Rules](https://help.zscaler.com/cloud-branch-connector/configuring-traffic-forwarding-rule)

.

- Operation ID: `ZpaApplicationZResource_getZpaApplicationSegments`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `search` | query | no | string | The search string used to match against the ZPA application segment name. |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. The default size is 50. |
| `sortBy` | query | no | string | Specifies the application segment parameter by which the list must be sorted. By default, it is sorted by name. |
| `sortOrder` | query | no | string (enum: asc, desc, ruleExecution) | The order in which the list must be sorted, such as ascending or descending order of the specified parameter or rule execution order. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Default Response |

### Schemas

**`IpGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `ipAddresses` | array<string> | no |
| `description` | string | no |
| `creatorContext` | string (enum: ZIA, EC) | no |
| `isNonEditable` | boolean | no |

**`EntityReference`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `name` | string | no |
| `isNameL10nTag` | boolean | no |
| `extensions` | object | no |
| `deleted` | boolean | no |
| `externalId` | string | no |
| `associationTime` | integer(int32) | no |

**`DestinationIPGroupDecorator`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `type` | string (enum: DSTN_IP, DSTN_FQDN, DSTN_DOMAIN, DSTN_OTHER) | no |
| `addresses` | array<string> | no |
| `description` | string | no |
| `creatorContext` | string (enum: ZIA, EC) | no |
| `ipCategories` | array<string (enum: ANY, NONE, OTHER_ADULT_MATERIAL, ADULT_THEMES, LINGERIE_BIKINI, NUDITY, PORNOGRAPHY, SEXUALITY)> | no |
| `countries` | array<string (enum: ANY, NONE, COUNTRY_AD, COUNTRY_AE, COUNTRY_AF, COUNTRY_AG, COUNTRY_AI, COUNTRY_AL)> | no |
| `isNonEditable` | boolean | no |
| `ipAddresses` | array<string> | no |
| `urlCategories` | array<string (enum: ANY, NONE, OTHER_ADULT_MATERIAL, ADULT_THEMES, LINGERIE_BIKINI, NUDITY, PORNOGRAPHY, SEXUALITY)> | no |

**`DestinationIpGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `type` | string (enum: DSTN_IP, DSTN_FQDN, DSTN_DOMAIN, DSTN_OTHER) | no |
| `addresses` | array<string> | no |
| `description` | string | no |
| `creatorContext` | string (enum: ZIA, EC) | no |
| `ipCategories` | array<string (enum: ANY, NONE, OTHER_ADULT_MATERIAL, ADULT_THEMES, LINGERIE_BIKINI, NUDITY, PORNOGRAPHY, SEXUALITY)> | no |
| `countries` | array<string (enum: ANY, NONE, COUNTRY_AD, COUNTRY_AE, COUNTRY_AF, COUNTRY_AG, COUNTRY_AI, COUNTRY_AL)> | no |
| `isNonEditable` | boolean | no |

**`NetworkService`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `tag` | string (enum: ICMP_ANY, UDP_ANY, TCP_ANY, OTHER_NETWORK_SERVICE, DNS, NETBIOS, FTP, GNUTELLA) | no |
| `srcTcpPorts` | array<PortRange> | no |
| `destTcpPorts` | array<PortRange> | no |
| `srcUdpPorts` | array<PortRange> | no |
| `destUdpPorts` | array<PortRange> | no |
| `type` | string (enum: STANDARD, PREDEFINED, CUSTOM) | no |
| `description` | string | no |
| `creatorContext` | string (enum: ZIA, EC) | no |
| `isNameL10nTag` | boolean | no |

**`NetworkServiceGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `services` | array<NetworkService> | no |
| `description` | string | no |
| `creatorContext` | string (enum: ZIA, EC) | no |

**`ZpaApplicationSegment`**

| Property | Type | Required |
| --- | --- | --- |
| `deleted` | boolean | no |
| `description` | string | no |
| `id` | integer(int32) | no |
| `name` | string | no |
| `zpaId` | integer(int64) | no |

**`PortRange`**

| Property | Type | Required |
| --- | --- | --- |
| `start` | integer(int32) | no |
| `end` | integer(int32) | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/posture-profiles","lastmod":"2026-01-06T23:49Z","nid":"1485301"} -->
## Posture Profiles

- Source: https://help.zscaler.com/legacy-apis/posture-profiles
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Posture Profiles
- Last modified: 2026-01-06T23:49Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/admin/customers/{customerId}/posture/{id}`

**Gets the configured posture profile for the specified ID.**

- Operation ID: `getPostureProfile_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `id` | path | yes | integer(int64) | The unique identifier of the posture profile. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v2/admin/customers/{customerId}/posture`

**Gets all posture profiles for the specified customer.**

- Operation ID: `getAllAttributes`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`PostureProfile`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `postureUdid` | string | no |
| `postureType` | string | no |
| `applyToMachineTunnelEnabled` | boolean | no |
| `rootCert` | string | no |
| `crlCheckEnabled` | boolean | no |
| `nonExportablePrivateKeyEnabled` | boolean | no |
| `platform` | array<string> | no |
| `domain` | string | no |
| `zscalerCustomerId` | integer(int64) | no |
| `zscalerCloud` | string | no |
| `masterCustomerId` | string | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`PageListPostureProfile`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<PostureProfile> | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/private-cloud-controller-group-management","lastmod":"2026-01-06T23:49Z","nid":"1532092"} -->
## Private Cloud Controller Group Management

- Source: https://help.zscaler.com/legacy-apis/private-cloud-controller-group-management
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Private Cloud Controller Group Management
- Last modified: 2026-01-06T23:49Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/admin/customers/{customerId}/privateCloudControllerGroup/{privateCloudControllerGroupId}`

**Gets the Private Cloud Controller group details for the specified ID.**

- Operation ID: `getZpnSiteControllerGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `privateCloudControllerGroupId` | path | yes | integer(int64) | The unique identifier of the Private Cloud Controller group. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 200 | OK |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/privateCloudControllerGroup/{privateCloudControllerGroupId}`

**Updates the Private Cloud Controller group details for the specified ID.**

- Operation ID: `updateZpnSiteControllerGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `privateCloudControllerGroupId` | path | yes | integer(int64) | The unique identifier of the Private Cloud Controller group. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → ZpnSiteControllerGroupDTO

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 204 | No Content |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/privateCloudControllerGroup/{privateCloudControllerGroupId}`

**Deletes the Private Cloud Controller group for the specified ID.**

- Operation ID: `deleteZpnSiteControllerGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `privateCloudControllerGroupId` | path | yes | integer(int64) | The unique identifier of the Private Cloud Controller group. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 204 | No Content |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/privateCloudControllerGroup/summary`

**Gets all configured Private Cloud Controller groups by ID and name.**

- Operation ID: `getZpnSiteControllerGroupNameIds`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The maximum page size is 500. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 200 | OK |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/privateCloudControllerGroup`

**Gets details of all configured Private Cloud Controller groups for the specified customer.**

- Operation ID: `getZpnSiteControllerGroups`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The maximum page size is 500. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 200 | OK |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/privateCloudControllerGroup`

**Adds a new Private Cloud Controller group for the specified customer.**

- Operation ID: `addZpnSiteControllerGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → ZpnSiteControllerGroupDTO

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 201 | Created |

### Schemas

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`ZpnSiteControllerGroupDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | string | no |
| `id` | string | no |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `restrictionType` | string | no |
| `enabled` | boolean | no |
| `description` | string | no |
| `geoLocationId` | integer(int64) | no |
| `versionProfileId` | integer(int64) | no |
| `overrideVersionProfile` | boolean | no |
| `siteId` | string | no |
| `enrollmentCertId` | integer(int64) | no |
| `versionProfileName` | string | no |
| `upgradePriority` | string (enum: WEEK, DAY, FORCE_NOW, NOW) | no |
| `versionProfileVisibilityScope` | string (enum: ALL, NONE, CUSTOM) | no |
| `upgradeTimeInSecs` | string | no |
| `upgradeDay` | string | no |
| `siteName` | string | no |
| `isPublic` | string | no |
| `location` | string | no |
| `privateCloudControllers` | array<ZpnSiteController> | no |
| `latitude` | string | no |
| `longitude` | string | no |
| `city` | string | no |
| `cityCountry` | string | no |
| `countryCode` | string | no |
| `name` | string | yes |
| `readOnly` | boolean | no |
| `zscalerManaged` | boolean | no |

**`PageListZpnSiteControllerGroupDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<ZpnSiteControllerGroupDTO> | no |

**`ZpnSiteController`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `restrictionType` | string | no |
| `description` | string | no |
| `fingerprint` | string | no |
| `issuedCertId` | integer(int64) | no |
| `enabled` | boolean | no |
| `ipAcl` | array<string> | no |
| `listenIps` | array<string> | no |
| `publishIps` | array<string> | no |
| `privateCloudControllerVersion` | ZpnSiteControllerVersion | no |
| `provisioningKeyId` | integer(int64) | no |
| `provisioningKeyName` | string | no |
| `privateCloudControllerGroupId` | string | no |
| `privateCloudControllerGroupName` | string | no |
| `enrollmentCert` | object | no |
| `latitude` | string | no |
| `longitude` | string | no |
| `location` | string | no |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `previousVersion` | string | no |
| `lastUpgradeTime` | integer(int64) | no |
| `expectedUpgradeTime` | integer(int64) | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `controlChannelStatus` | string (enum: UNKNOWN, ZPN_STATUS_AUTHENTICATED, ZPN_STATUS_DISCONNECTED) | no |
| `upgradeAttempt` | integer(int32) | no |
| `ctrlBrokerName` | string | no |
| `lastBrokerConnectTime` | integer(int64) | no |
| `lastBrokerConnectTimeDuration` | string | no |
| `lastBrokerDisconnectTime` | integer(int64) | no |
| `lastBrokerDisconnectTimeDuration` | string | no |
| `masterLastSyncTime` | integer(int64) | no |
| `userdbLastSyncTime` | integer(int64) | no |
| `shardLastSyncTime` | integer(int64) | no |
| `privateIp` | string | no |
| `publicIp` | string | no |
| `platform` | string | no |
| `runtimeOS` | string | no |
| `applicationStartTime` | integer(int64) | no |
| `sargeVersion` | string | no |
| `platformDetail` | string | no |
| `expectedSargeVersion` | string | no |
| `sargeUpgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN) | no |
| `osUpgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN) | no |
| `lastSargeUpgradeTime` | integer(int64) | no |
| `lastOSUpgradeTime` | integer(int64) | no |
| `sargeUpgradeAttempt` | integer(int32) | no |
| `platformVersion` | string | no |
| `osUpgradeEnabled` | boolean | no |
| `zpnSubModuleUpgradeList` | array<ZpnSubModuleUpgrade> | no |
| `readOnly` | boolean | no |
| `zscalerManaged` | boolean | no |

**`ZpnSiteControllerVersion`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `systemStartTime` | integer(int64) | no |
| `applicationStartTime` | integer(int64) | no |
| `lastConnectTime` | integer(int64) | no |
| `lastDisconnectTime` | integer(int64) | no |
| `masterLastSyncTime` | integer(int64) | no |
| `userdbLastSyncTime` | integer(int64) | no |
| `shardLastSyncTime` | integer(int64) | no |
| `platform` | string | no |
| `runtimeOS` | string | no |
| `brokerId` | integer(int64) | no |
| `privateCloudControllerGroupId` | integer(int64) | no |
| `restartTimeInSec` | integer(int64) | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `ctrlChannelStatus` | string (enum: UNKNOWN, ZPN_STATUS_AUTHENTICATED, ZPN_STATUS_DISCONNECTED) | no |
| `privateIp` | string | no |
| `publicIp` | string | no |
| `loneWarrior` | boolean | no |
| `tunnelId` | string | no |
| `previousVersion` | string | no |
| `lastUpgradedTime` | integer(int64) | no |
| `restartInstructions` | string (enum: DELETE, FLUSHDB) | no |
| `upgradeAttempt` | integer(int32) | no |
| `disableAutoUpdate` | boolean | no |
| `sargeVersion` | string | no |
| `zpnSubModuleUpgrade` | array<ZpnSubModuleUpgrade> | no |
| `platformDetail` | string | no |
| `upgradeNowOnce` | boolean | no |
| `expectedSargeVersion` | string | no |
| `sargeUpgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN) | no |
| `osUpgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN) | no |
| `lastSargeUpgradeTime` | integer(int64) | no |
| `lastOSUpgradeTime` | integer(int64) | no |
| `sargeUpgradeAttempt` | integer(int32) | no |
| `platformVersion` | string | no |
| `osUpgradeEnabled` | boolean | no |

**`ZpnSubModuleUpgrade`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `entityGid` | integer(int64) | yes |
| `entityType` | string (enum: ASSISTANT, PRIVATE_BROKER, MMDB_GEOIP, MMDB_ISP, GUACD, SITE_CONTROLLER) | no |
| `role` | string (enum: ASSISTANT, PRIVATE_BROKER, MMDB_GEOIP, MMDB_ISP, GUACD, SITE_CONTROLLER) | yes |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `previousVersion` | string | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `upgradeTime` | integer(int64) | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/private-cloud-controller-management-zpa-api-reference","lastmod":"2026-01-06T23:49Z","nid":"1532089"} -->
## Private Cloud Controller Management

- Source: https://help.zscaler.com/legacy-apis/private-cloud-controller-management-zpa-api-reference
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Private Cloud Controller Management
- Last modified: 2026-01-06T23:49Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/admin/customers/{customerId}/privateCloudController`

**Gets all the configured Private Cloud Controller details for the specified customer.**

- Operation ID: `getAllSiteControllers_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The maximum page size is 500. |
| `sortBy` | query | no | string | Indicates the parameter to sort by. |
| `sortDir` | query | no | string | Specifies the sort direction (i.e., ascending or descending order). |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 200 | OK |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/privateCloudController/{privateCloudControllerId}/restart`

**Restarts the Private Cloud Controller for the specified ID.**

- Operation ID: `restartSiteController`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `privateCloudControllerId` | path | yes | integer(int64) | The unique identifier of the Private Cloud Controller. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 204 | No Content |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/privateCloudController/{privateCloudControllerId}`

**Gets the Private Cloud controller details for the specified ID.**

- Operation ID: `getSiteController`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `privateCloudControllerId` | path | yes | integer(int64) | The unique identifier of the Private Cloud Controller. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 200 | OK |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/privateCloudController/{privateCloudControllerId}`

**Updates the Private Cloud Controller details for the specified ID.**

- Operation ID: `updateSiteController`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `privateCloudControllerId` | path | yes | integer(int64) | The unique identifier of the Private Cloud Controller. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → ZpnSiteControllerDTO

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 204 | No Content |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/privateCloudController/{privateCloudControllerId}`

**Deletes the Private Cloud Controller for the specified ID.**

- Operation ID: `deleteSiteController`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `privateCloudControllerId` | path | yes | integer(int64) | The unique identifier of the Private Cloud Controller. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 204 | No Content |

### Schemas

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`PageListZpnSiteControllerDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<ZpnSiteControllerDTO> | no |

**`ZpnSiteControllerDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | string | no |
| `id` | string | no |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `restrictionType` | string | no |
| `description` | string | no |
| `fingerprint` | string | no |
| `issuedCertId` | integer(int64) | no |
| `enabled` | boolean | no |
| `name` | string | yes |
| `ipAcl` | array<string> | no |
| `listenIps` | array<string> | no |
| `publishIps` | array<string> | no |
| `privateCloudControllerVersion` | ZpnSiteControllerVersion | no |
| `provisioningKeyId` | integer(int64) | no |
| `provisioningKeyName` | string | no |
| `privateCloudControllerGroupId` | string | no |
| `privateCloudControllerGroupName` | string | no |
| `enrollmentCert` | object | no |
| `latitude` | string | no |
| `longitude` | string | no |
| `location` | string | no |
| `siteSpDnsName` | string | no |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `previousVersion` | string | no |
| `lastUpgradeTime` | integer(int64) | no |
| `expectedUpgradeTime` | integer(int64) | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `controlChannelStatus` | string (enum: UNKNOWN, ZPN_STATUS_AUTHENTICATED, ZPN_STATUS_DISCONNECTED) | no |
| `upgradeAttempt` | integer(int32) | no |
| `ctrlBrokerName` | string | no |
| `lastBrokerConnectTime` | integer(int64) | no |
| `lastBrokerConnectTimeDuration` | string | no |
| `lastBrokerDisconnectTime` | integer(int64) | no |
| `lastBrokerDisconnectTimeDuration` | string | no |
| `masterLastSyncTime` | integer(int64) | no |
| `userdbLastSyncTime` | integer(int64) | no |
| `shardLastSyncTime` | integer(int64) | no |
| `privateIp` | string | no |
| `publicIp` | string | no |
| `platform` | string | no |
| `runtimeOS` | string | no |
| `applicationStartTime` | integer(int64) | no |
| `sargeVersion` | string | no |
| `platformDetail` | string | no |
| `zpnSubModuleUpgradeList` | array<ZpnSubModuleUpgrade> | no |
| `expectedSargeVersion` | string | no |
| `sargeUpgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN) | no |
| `osUpgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN) | no |
| `lastSargeUpgradeTime` | integer(int64) | no |
| `lastOSUpgradeTime` | integer(int64) | no |
| `sargeUpgradeAttempt` | integer(int32) | no |
| `platformVersion` | string | no |
| `osUpgradeEnabled` | boolean | no |
| `readOnly` | boolean | no |
| `zscalerManaged` | boolean | no |

**`ZpnSiteControllerVersion`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `systemStartTime` | integer(int64) | no |
| `applicationStartTime` | integer(int64) | no |
| `lastConnectTime` | integer(int64) | no |
| `lastDisconnectTime` | integer(int64) | no |
| `masterLastSyncTime` | integer(int64) | no |
| `userdbLastSyncTime` | integer(int64) | no |
| `shardLastSyncTime` | integer(int64) | no |
| `platform` | string | no |
| `runtimeOS` | string | no |
| `brokerId` | integer(int64) | no |
| `privateCloudControllerGroupId` | integer(int64) | no |
| `restartTimeInSec` | integer(int64) | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `ctrlChannelStatus` | string (enum: UNKNOWN, ZPN_STATUS_AUTHENTICATED, ZPN_STATUS_DISCONNECTED) | no |
| `privateIp` | string | no |
| `publicIp` | string | no |
| `loneWarrior` | boolean | no |
| `tunnelId` | string | no |
| `previousVersion` | string | no |
| `lastUpgradedTime` | integer(int64) | no |
| `restartInstructions` | string (enum: DELETE, FLUSHDB) | no |
| `upgradeAttempt` | integer(int32) | no |
| `disableAutoUpdate` | boolean | no |
| `sargeVersion` | string | no |
| `zpnSubModuleUpgrade` | array<ZpnSubModuleUpgrade> | no |
| `platformDetail` | string | no |
| `upgradeNowOnce` | boolean | no |
| `expectedSargeVersion` | string | no |
| `sargeUpgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN) | no |
| `osUpgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN) | no |
| `lastSargeUpgradeTime` | integer(int64) | no |
| `lastOSUpgradeTime` | integer(int64) | no |
| `sargeUpgradeAttempt` | integer(int32) | no |
| `platformVersion` | string | no |
| `osUpgradeEnabled` | boolean | no |

**`ZpnSubModuleUpgrade`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `entityGid` | integer(int64) | yes |
| `entityType` | string (enum: ASSISTANT, PRIVATE_BROKER, MMDB_GEOIP, MMDB_ISP, GUACD, SITE_CONTROLLER) | no |
| `role` | string (enum: ASSISTANT, PRIVATE_BROKER, MMDB_GEOIP, MMDB_ISP, GUACD, SITE_CONTROLLER) | yes |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `previousVersion` | string | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `upgradeTime` | integer(int64) | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/private-service-edge-group-management","lastmod":"2026-01-06T23:49Z","nid":"1485311"} -->
## Private Service Edge Group Management

- Source: https://help.zscaler.com/legacy-apis/private-service-edge-group-management
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Private Service Edge Group Management
- Last modified: 2026-01-06T23:49Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/admin/customers/{customerId}/serviceEdgeGroup/{serviceEdgeGroupId}`

**Gets the ZPA Private Service Edge group details for the specified ID.**

- Operation ID: `getPrivateBrokerGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `serviceEdgeGroupId` | path | yes | integer(int64) | The unique identifier of the ZPA Private Service Edge group. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/serviceEdgeGroup/{serviceEdgeGroupId}`

**Updates the ZPA Private Service Edge group details for the specified ID.**

- Operation ID: `updatePrivateBrokerGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `serviceEdgeGroupId` | path | yes | integer(int64) | The unique identifier of the ZPA Private Service Edge group. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → ServiceEdgeGroup

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/serviceEdgeGroup/{serviceEdgeGroupId}`

**Deletes the ZPA Private Service Edge group for the specified ID.**

- Operation ID: `deletePrivateBrokerGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `serviceEdgeGroupId` | path | yes | integer(int64) | The unique identifier of the ZPA Private Service Edge group. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/serviceEdgeGroup`

**Gets details of all configured ZPA Private Service Edge groups for the specified customer.**

- Operation ID: `getPrivateBrokerGroups`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/serviceEdgeGroup`

**Adds a new ZPA Private Service Edge group for the specified customer.**

- Operation ID: `addPrivateBrokerGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → ServiceEdgeGroup

**Responses:**

| Code | Description |
| --- | --- |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`ServiceEdgeGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | no |
| `description` | string | no |
| `geoLocationId` | integer(int64) | no |
| `versionProfileId` | integer(int64) | no |
| `overrideVersionProfile` | boolean | no |
| `siteId` | string | no |
| `versionProfileName` | string | no |
| `upgradePriority` | string (enum: WEEK, DAY, FORCE_NOW, NOW) | no |
| `versionProfileVisibilityScope` | string (enum: ALL, NONE, CUSTOM) | no |
| `upgradeTimeInSecs` | string | no |
| `upgradeDay` | string | no |
| `siteName` | string | no |
| `isPublic` | string | no |
| `location` | string | no |
| `serviceEdges` | array<ServiceEdge> | no |
| `latitude` | string | no |
| `city` | string | no |
| `longitude` | string | no |
| `cityCountry` | string | no |
| `countryCode` | string | no |
| `useInDrMode` | boolean | no |
| `graceDistanceEnabled` | boolean | no |
| `graceDistanceValue` | number(double) | no |
| `graceDistanceValueUnit` | string (enum: MILES, KMS) | no |
| `trustedNetworks` | array<TrustedNetwork> | no |
| `altCloud` | string | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`PageListPrivateBrokerGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<ServiceEdgeGroup> | no |

**`ServiceEdge`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `description` | string | no |
| `fingerprint` | string | no |
| `issuedCertId` | integer(int64) | no |
| `enabled` | boolean | no |
| `ipAcl` | array<string> | no |
| `listenIps` | array<string> | no |
| `publishIps` | array<string> | no |
| `publishIpv6` | boolean | no |
| `privateBrokerVersion` | PrivateBrokerVersion | no |
| `provisioningKeyId` | integer(int64) | no |
| `provisioningKeyName` | string | no |
| `serviceEdgeGroupId` | string | no |
| `serviceEdgeGroupName` | string | no |
| `enrollmentCert` | object | no |
| `latitude` | string | no |
| `longitude` | string | no |
| `location` | string | no |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `previousVersion` | string | no |
| `lastUpgradeTime` | integer(int64) | no |
| `expectedUpgradeTime` | integer(int64) | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `controlChannelStatus` | string (enum: UNKNOWN, ZPN_STATUS_AUTHENTICATED, ZPN_STATUS_DISCONNECTED) | no |
| `upgradeAttempt` | integer(int32) | no |
| `ctrlBrokerName` | string | no |
| `lastBrokerConnectTime` | integer(int64) | no |
| `lastBrokerConnectTimeDuration` | string | no |
| `lastBrokerDisconnectTime` | integer(int64) | no |
| `lastBrokerDisconnectTimeDuration` | string | no |
| `privateIp` | string | no |
| `publicIp` | string | no |
| `platform` | string | no |
| `runtimeOS` | string | no |
| `applicationStartTime` | integer(int64) | no |
| `sargeVersion` | string | no |
| `platformDetail` | string | no |
| `zpnSubModuleUpgradeList` | array<ZpnSubModuleUpgrade> | no |

**`PrivateBrokerVersion`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `systemStartTime` | integer(int64) | no |
| `applicationStartTime` | integer(int64) | no |
| `lastConnectTime` | integer(int64) | no |
| `lastDisconnectTime` | integer(int64) | no |
| `platform` | string | no |
| `runtimeOS` | string | no |
| `brokerId` | integer(int64) | no |
| `serviceEdgeGroupId` | integer(int64) | no |
| `restartTimeInSec` | integer(int64) | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `ctrlChannelStatus` | string (enum: UNKNOWN, ZPN_STATUS_AUTHENTICATED, ZPN_STATUS_DISCONNECTED) | no |
| `privateIp` | string | no |
| `publicIp` | string | no |
| `loneWarrior` | boolean | no |
| `tunnelId` | string | no |
| `previousVersion` | string | no |
| `lastUpgradedTime` | integer(int64) | no |
| `restartInstructions` | string (enum: DELETE, FLUSHDB) | no |
| `upgradeAttempt` | integer(int32) | no |
| `disableAutoUpdate` | boolean | no |
| `sargeVersion` | string | no |
| `zpnSubModuleUpgrade` | array<ZpnSubModuleUpgrade> | no |
| `platformDetail` | string | no |
| `upgradeNowOnce` | boolean | no |

**`ZpnSubModuleUpgrade`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `entityGid` | integer(int64) | yes |
| `entityType` | string (enum: ASSISTANT, PRIVATE_BROKER, MMDB_GEOIP, MMDB_ISP, GUACD, SITE_CONTROLLER) | no |
| `role` | string (enum: ASSISTANT, PRIVATE_BROKER, MMDB_GEOIP, MMDB_ISP, GUACD, SITE_CONTROLLER) | yes |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `previousVersion` | string | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `upgradeTime` | integer(int64) | no |

**`TrustedNetwork`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `networkId` | string | no |
| `zscalerCloud` | string | no |
| `domain` | string | no |
| `masterCustomerId` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/private-service-edge-management-api","lastmod":"2026-01-06T23:49Z","nid":"1485306"} -->
## Private Service Edge Management

- Source: https://help.zscaler.com/legacy-apis/private-service-edge-management-api
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Private Service Edge Management
- Last modified: 2026-01-06T23:49Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/serviceEdgeSchedule/{id}`

**Updates the Auto Delete frequency for the disconnected ZPA Private Service Edge by the specified Auto Delete configuration ID.**

- Operation ID: `updatePrivateBrokerSchedule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `id` | path | yes | integer(int64) | The unique identifier for the ZPA Private Service Edge auto-deletion configuration for a customer. This field is only required for the PUT request to update the frequency of the ZPA Private Service Edge settings. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → ScheduledConfigDTO

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/serviceEdgeSchedule`

**Gets the Auto Delete frequency of the ZPA Private Service Edge for the specified customer.**

- Operation ID: `getPrivateBrokerSchedule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/serviceEdgeSchedule`

**Configures Auto Delete for the specified disconnected ZPA Private Service Edge.**

- Operation ID: `savePrivateBrokerSchedule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → ScheduledConfigDTO

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/serviceEdge/{serviceEdgeId}`

**Gets the ZPA Private Service Edge details for the specified ID.**

- Operation ID: `getPrivateBroker`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `serviceEdgeId` | path | yes | integer(int64) | The unique identifier of the ZPA Private Service Edge. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/serviceEdge/{serviceEdgeId}`

**Updates the ZPA Private Service Edge details for the specified ID.**

- Operation ID: `updatePrivateBroker`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `serviceEdgeId` | path | yes | integer(int64) | The unique identifier of the ZPA Private Service Edge. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → ServiceEdge

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/serviceEdge/{serviceEdgeId}`

**Deletes the ZPA Private Service Edge for the specified ID.**

- Operation ID: `deletePrivateBroker`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `serviceEdgeId` | path | yes | integer(int64) | The unique identifier of the ZPA Private Service Edge. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/serviceEdge`

**Gets all the configured ZPA Private Service Edge details for the specified customer.**

- Operation ID: `getAllPrivateBrokers_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |
| `sortBy` | query | no | string | Indicates the parameter to sort by. |
| `sortDir` | query | no | string | Specifies the sort direction (i.e., ascending or descending order). |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/serviceEdge/bulkDelete`

**Bulk deletes the ZPA Private Service Edges for the specified customer.**

- Operation ID: `bulkDeletePrivateBroker`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → BulkDeleteResource

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`ScheduledConfigDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `frequencyInterval` | integer(int32) | no |
| `frequency` | string | no |
| `enabled` | boolean | no |
| `customerId` | integer(int64) | no |
| `deleteDisabled` | boolean | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`ServiceEdge`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `description` | string | no |
| `fingerprint` | string | no |
| `issuedCertId` | integer(int64) | no |
| `enabled` | boolean | no |
| `ipAcl` | array<string> | no |
| `listenIps` | array<string> | no |
| `publishIps` | array<string> | no |
| `publishIpv6` | boolean | no |
| `privateBrokerVersion` | PrivateBrokerVersion | no |
| `provisioningKeyId` | integer(int64) | no |
| `provisioningKeyName` | string | no |
| `serviceEdgeGroupId` | string | no |
| `serviceEdgeGroupName` | string | no |
| `enrollmentCert` | object | no |
| `latitude` | string | no |
| `longitude` | string | no |
| `location` | string | no |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `previousVersion` | string | no |
| `lastUpgradeTime` | integer(int64) | no |
| `expectedUpgradeTime` | integer(int64) | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `controlChannelStatus` | string (enum: UNKNOWN, ZPN_STATUS_AUTHENTICATED, ZPN_STATUS_DISCONNECTED) | no |
| `upgradeAttempt` | integer(int32) | no |
| `ctrlBrokerName` | string | no |
| `lastBrokerConnectTime` | integer(int64) | no |
| `lastBrokerConnectTimeDuration` | string | no |
| `lastBrokerDisconnectTime` | integer(int64) | no |
| `lastBrokerDisconnectTimeDuration` | string | no |
| `privateIp` | string | no |
| `publicIp` | string | no |
| `platform` | string | no |
| `runtimeOS` | string | no |
| `applicationStartTime` | integer(int64) | no |
| `sargeVersion` | string | no |
| `platformDetail` | string | no |
| `zpnSubModuleUpgradeList` | array<ZpnSubModuleUpgrade> | no |

**`PageListPrivateBroker`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<ServiceEdge> | no |

**`BulkDeleteResource`**

| Property | Type | Required |
| --- | --- | --- |
| `ids` | array<integer(int64)> | yes |

**`PrivateBrokerVersion`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `systemStartTime` | integer(int64) | no |
| `applicationStartTime` | integer(int64) | no |
| `lastConnectTime` | integer(int64) | no |
| `lastDisconnectTime` | integer(int64) | no |
| `platform` | string | no |
| `runtimeOS` | string | no |
| `brokerId` | integer(int64) | no |
| `serviceEdgeGroupId` | integer(int64) | no |
| `restartTimeInSec` | integer(int64) | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `ctrlChannelStatus` | string (enum: UNKNOWN, ZPN_STATUS_AUTHENTICATED, ZPN_STATUS_DISCONNECTED) | no |
| `privateIp` | string | no |
| `publicIp` | string | no |
| `loneWarrior` | boolean | no |
| `tunnelId` | string | no |
| `previousVersion` | string | no |
| `lastUpgradedTime` | integer(int64) | no |
| `restartInstructions` | string (enum: DELETE, FLUSHDB) | no |
| `upgradeAttempt` | integer(int32) | no |
| `disableAutoUpdate` | boolean | no |
| `sargeVersion` | string | no |
| `zpnSubModuleUpgrade` | array<ZpnSubModuleUpgrade> | no |
| `platformDetail` | string | no |
| `upgradeNowOnce` | boolean | no |

**`ZpnSubModuleUpgrade`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `entityGid` | integer(int64) | yes |
| `entityType` | string (enum: ASSISTANT, PRIVATE_BROKER, MMDB_GEOIP, MMDB_ISP, GUACD, SITE_CONTROLLER) | no |
| `role` | string (enum: ASSISTANT, PRIVATE_BROKER, MMDB_GEOIP, MMDB_ISP, GUACD, SITE_CONTROLLER) | yes |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `previousVersion` | string | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `upgradeTime` | integer(int64) | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/privileged-approval-management","lastmod":"2026-01-06T23:49Z","nid":"1485971"} -->
## Privileged Approval Management

- Source: https://help.zscaler.com/legacy-apis/privileged-approval-management
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Privileged Approval Management
- Last modified: 2026-01-06T23:49Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/admin/customers/{customerId}/approval`

**Gets all configured privileged approvals for the specified customer.**

- Operation ID: `getAll_8`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |
| `sortBy` | query | no | string | The sort string used to support sorting on the given field for the API. |
| `sortdir` | query | no | string (enum: ASC, DESC) | Specifies the sort direction (i.e., ascending or descending order). |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/approval`

**Adds a new privileged approval for the specified customer.**

- Operation ID: `addPrivilegedApproval`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → PrivilegedApproval

**Responses:**

| Code | Description |
| --- | --- |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/approval/{id}`

**Gets the privileged approval details for the specified ID.**

- Operation ID: `getPrivilegedApproval`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `id` | path | yes | integer(int64) | The unique identifier of the privileged approval. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/approval/{id}`

**Updates the privileged approval details for the specified ID.**

- Operation ID: `updatePrivilegedApproval`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `id` | path | yes | integer(int64) | The unique identifier of the privileged approval. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → PrivilegedApproval

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/approval/{id}`

**Deletes the privileged approval for the specified ID.**

- Operation ID: `deletePrivilegedApproval`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `id` | path | yes | integer(int64) | The unique identifier of the privileged approval. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/approval/expired`

**Deletes all expired privileged approvals for the specified customer.**

- Operation ID: `deleteExpiredPrivilegedApprovals`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`PageListJITApprovalView`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<PrivilegedApproval> | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`PrivilegedApproval`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `microtenantId` | integer(int64) | no |
| `startTime` | integer(int32) | no |
| `endTime` | integer(int32) | no |
| `workingHours` | PrivilegedApprovalWorkHours | no |
| `status` | string (enum: INVALID, ACTIVE, FUTURE, EXPIRED) | no |
| `emailIds` | array<string> | no |
| `applications` | array<Application> | no |

**`PrivilegedApprovalWorkHours`**

| Property | Type | Required |
| --- | --- | --- |
| `startTimeCron` | string | no |
| `endTimeCron` | string | no |
| `startTime` | string | no |
| `endTime` | string | no |
| `days` | array<string> | no |
| `timeZone` | string | no |

**`Application`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `sharedMicrotenantIds` | array<integer(int64)> | no |
| `domainName` | string | no |
| `domainNames` | array<string> | no |
| `description` | string | no |
| `tcpPortsIn` | array<integer(int32)> | no |
| `tcpPortsOut` | array<integer(int32)> | no |
| `defaultIdleTimeout` | integer(int32) | no |
| `defaultMaxAge` | integer(int32) | no |
| `enabled` | boolean | no |
| `passiveHealthEnabled` | boolean | no |
| `tcpPortRanges` | array<integer(int32)> | no |
| `udpPortRanges` | array<integer(int32)> | no |
| `doubleEncrypt` | boolean | no |
| `healthCheckType` | string (enum: DEFAULT, NONE) | no |
| `icmpAccessType` | string (enum: PING_TRACEROUTING, PING, NONE) | no |
| `bypassType` | string (enum: ALWAYS, NEVER, ON_NET) | no |
| `configSpace` | string (enum: DEFAULT, SIEM) | no |
| `cnameConfig` | string (enum: NOFLATTEN, FLATTEN) | no |
| `ipAnchored` | boolean | no |
| `bypassOnReauth` | boolean | no |
| `inspectTrafficWithZia` | boolean | no |
| `logFeatures` | array<string (enum: skip_discovery, full_wildcard)> | no |
| `tcpKeepAlive` | integer(int32) | no |
| `useInDrMode` | boolean | no |
| `matchStyle` | string (enum: EXCLUSIVE, INCLUSIVE) | no |
| `serverGroups` | array<AppServerGroup> | no |
| `selectConnectorCloseToApp` | boolean | no |
| `weightedLoadBalancing` | boolean | no |
| `extranetEnabled` | boolean | no |
| `zpnErId` | ZpnExtranetResource | no |
| `tcpProtocolsBitmasks` | array<integer(int32)> | no |
| `udpProtocolsBitmasks` | array<integer(int32)> | no |
| `adpEnabled` | boolean | no |
| `apiProtectionEnabled` | boolean | no |
| `autoAppProtectEnabled` | boolean | no |
| `fqdnDnsCheck` | boolean | no |
| `tcpPortRange` | array<AppPortRange> | no |
| `udpPortRange` | array<AppPortRange> | no |

**`AppServerGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | yes |
| `dynamicDiscovery` | boolean | no |
| `description` | string | no |
| `configSpace` | string (enum: DEFAULT, SIEM) | yes |
| `weight` | integer(int32) | no |
| `passive` | boolean | no |
| `extranetEnabled` | boolean | no |
| `zpnErId` | ZpnExtranetResource | no |

**`ZpnExtranetResource`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `ziaErId` | integer(int64) | yes |
| `ziaErName` | string | yes |
| `ziaCloud` | string | yes |
| `ziaOrgId` | integer(int64) | yes |
| `ziaModifiedTime` | integer(int64) | no |

**`AppPortRange`**

| Property | Type | Required |
| --- | --- | --- |
| `from` | integer(int32) | no |
| `to` | integer(int32) | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/privileged-console-management","lastmod":"2026-01-06T23:49Z","nid":"1485976"} -->
## Privileged Console Management

- Source: https://help.zscaler.com/legacy-apis/privileged-console-management
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Privileged Console Management
- Last modified: 2026-01-06T23:49Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/admin/customers/{customerId}/praConsole/praPortal/{portalId}`

**Gets the privileged consoles for the specified ID of the privileged portal.**

- Operation ID: `getPRAConsolesByPortalId`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `portalId` | path | yes | integer(int64) | The unique identifier of the privileged portal. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/praConsole`

**Gets all configured privileged consoles for the specified customer.**

- Operation ID: `getAll_6`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/praConsole`

**Adds a new privileged console for the specified privileged portal and customer.**

- Operation ID: `addPRAConsole`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → PraConsole

**Responses:**

| Code | Description |
| --- | --- |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/praConsole/{id}`

**Gets the privileged console details for the specified ID.**

- Operation ID: `getPRAConsole`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `id` | path | yes | integer(int64) | The unique identifier of the privileged console. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/praConsole/{id}`

**Updates the privileged console details for the specified ID.**

- Operation ID: `updatePRAConsole`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `id` | path | yes | integer(int64) | The unique identifier of the privileged console. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → PraConsole

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/praConsole/{id}`

**Deletes the privileged console for the specified ID.**

- Operation ID: `deletePRAConsole`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `id` | path | yes | integer(int64) | The unique identifier of the privileged console. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/praConsole/bulk`

**Creates a list of privileged consoles for the specified privileged portal and customer.**

- Operation ID: `addListOfConsoles`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → array<PraConsole>

**Responses:**

| Code | Description |
| --- | --- |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`PageListSRAConsole`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<PraConsole> | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`PraConsole`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | no |
| `description` | string | no |
| `iconText` | string | no |
| `praApplication` | PraApplication | no |
| `praPortals` | array<PraPortal> | no |
| `inconsistentConfigDetails` | InconsistentConfigDetails | no |

**`PraApplication`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | no |
| `description` | string | no |
| `domain` | string | yes |
| `appId` | integer(int64) | no |
| `hidden` | boolean | no |
| `applicationProtocol` | string (enum: HTTP, HTTPS, FTP, RDP, SSH, WEBSOCKET, VNC, REALVNC) | no |
| `applicationPort` | integer(int32) | no |
| `connectionSecurity` | string (enum: ANY, NLA, NLA_EXT, TLS, VM_CONNECT, RDP) | no |

**`PraPortal`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | no |
| `description` | string | no |
| `certificateId` | integer(int64) | no |
| `certificateName` | string | no |
| `getcName` | string | no |
| `domain` | string | no |
| `userNotification` | string | no |
| `userNotificationEnabled` | boolean | no |
| `userPortalGid` | integer(int64) | no |
| `extLabel` | string | no |
| `extDomainTranslation` | string | no |
| `extDomain` | string | no |
| `extDomainName` | string | no |
| `userPortalName` | string | no |

**`InconsistentConfigDetails`**

| Property | Type | Required |
| --- | --- | --- |
| `application` | array<InconsistentConfigNameReasonDto> | no |
| `sraApplication` | array<InconsistentConfigNameReasonDto> | no |
| `segmentGroup` | array<InconsistentConfigNameReasonDto> | no |
| `serverGroup` | array<InconsistentConfigNameReasonDto> | no |
| `appConnectorGroup` | array<InconsistentConfigNameReasonDto> | no |
| `baCertificate` | array<InconsistentConfigNameReasonDto> | no |
| `postureProfile` | array<InconsistentConfigNameReasonDto> | no |
| `trustedNetwork` | array<InconsistentConfigNameReasonDto> | no |
| `branchConnectorGroup` | array<InconsistentConfigNameReasonDto> | no |
| `cloudConnectorGroup` | array<InconsistentConfigNameReasonDto> | no |
| `samlAttributes` | array<InconsistentConfigNameReasonDto> | no |
| `scimAttributes` | array<InconsistentConfigNameReasonDto> | no |
| `machineGroup` | array<InconsistentConfigNameReasonDto> | no |
| `idp` | array<InconsistentConfigNameReasonDto> | no |
| `location` | array<InconsistentConfigNameReasonDto> | no |
| `workloadTagGroup` | array<InconsistentConfigNameReasonDto> | no |
| `userPortal` | array<InconsistentConfigNameReasonDto> | no |

**`InconsistentConfigNameReasonDto`**

| Property | Type | Required |
| --- | --- | --- |
| `name` | string | no |
| `reason` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/privileged-credential-management","lastmod":"2026-01-06T23:49Z","nid":"1485981"} -->
## Privileged Credential Management

- Source: https://help.zscaler.com/legacy-apis/privileged-credential-management
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Privileged Credential Management
- Last modified: 2026-01-06T23:49Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `POST /mgmtconfig/v1/admin/customers/{customerId}/credential/{id}/move`

**Moves a privileged credential from one Microtenant to another Microtenant for the specified ID.**

- Operation ID: `moveCredential`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `id` | path | yes | integer(int64) | The unique identifier of the privileged credential. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |
| `targetMicrotenantId` | query | no | integer(int64) | The unique identifier of the target Microtenant that the privileged credential is being moved to. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/credential`

**Gets all configured privileged credentials for the specified customer.**

- Operation ID: `getAll_7`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |
| `sortBy` | query | no | string | Indicates the parameter to sort by. |
| `sortdir` | query | no | string (enum: ASC, DESC) | Specifies the sort direction (i.e., ascending or descending order). |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/credential`

**Adds a new privileged credential for the specified customer.**

- Operation ID: `addCredential`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → PrivilegedCredential

**Responses:**

| Code | Description |
| --- | --- |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/credential/{id}`

**Gets the privileged credential details for the specified customer.**

- Operation ID: `getCredential`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `id` | path | yes | integer(int64) | The unique identifier of the privileged credential. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/credential/{id}`

**Updates the privileged credential details for the specified ID.**

- Operation ID: `updateCredential`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `id` | path | yes | integer(int64) | The unique identifier of the privileged credential. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → PrivilegedCredential

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/credential/{id}`

**Deletes the privileged credential for the specified ID.**

- Operation ID: `deleteCredential`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `id` | path | yes | integer(int64) | The unique identifier of the privileged credential. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`PageListCredential`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<PrivilegedCredential> | no |

**`PrivilegedCredential`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `description` | string | no |
| `userDomain` | string | no |
| `userName` | string | no |
| `password` | string | no |
| `privateKey` | string | no |
| `passphrase` | string | no |
| `credentialType` | string (enum: USERNAME_PASSWORD, SSH_KEY, PASSWORD) | yes |
| `lastCredentialResetTime` | integer(int32) | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/privileged-portal-management","lastmod":"2026-01-06T23:49Z","nid":"1485986"} -->
## Privileged Portal Management

- Source: https://help.zscaler.com/legacy-apis/privileged-portal-management
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Privileged Portal Management
- Last modified: 2026-01-06T23:49Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/admin/customers/{customerId}/praPortal`

**Gets all configured privileged portals for the specified customer.**

- Operation ID: `getAll_5`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/praPortal`

**Adds a new privileged portal for the specified customer.**

- Operation ID: `add`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → PraPortal

**Responses:**

| Code | Description |
| --- | --- |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/praPortal/{id}`

**Gets the privileged portal details for the specified ID.**

- Operation ID: `getPRAPortal`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `id` | path | yes | integer(int64) | The unique identifier of the privileged portal. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/praPortal/{id}`

**Updates the privileged portal for the specified ID.**

- Operation ID: `updatePRAPortal`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `id` | path | yes | integer(int64) | The unique identifier of the privileged portal. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → PraPortal

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/praPortal/{id}`

**Deletes the privileged portal for the specified ID.**

- Operation ID: `deletePRAPortal`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `id` | path | yes | integer(int64) | The unique identifier of the privileged portal. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`PageListSRAPortal`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<PraPortal> | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`PraPortal`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | no |
| `description` | string | no |
| `certificateId` | integer(int64) | no |
| `certificateName` | string | no |
| `getcName` | string | no |
| `domain` | string | no |
| `userNotification` | string | no |
| `userNotificationEnabled` | boolean | no |
| `userPortalGid` | integer(int64) | no |
| `extLabel` | string | no |
| `extDomainTranslation` | string | no |
| `extDomain` | string | no |
| `extDomainName` | string | no |
| `userPortalName` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/provisioning","lastmod":"2026-01-06T23:49Z","nid":"1450261"} -->
## Provisioning

- Source: https://help.zscaler.com/legacy-apis/provisioning
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > Zscaler Cloud & Branch Connector API > API Developer & Reference Guide > Reference Guide > Provisioning
- Last modified: 2026-01-06T23:49Z

**Servers:** `https://{host}:{port}/wapi/v1`, `https://{host}:{port}/api/v1`

### `GET /apiKeys`

Lists available API keys.

- Operation ID: `ApiKeyResource_getApiKeys`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `includePartnerKey` | query | no | boolean | Include or exclude partner keys from the list. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |

### `POST /apiKeys/{keyId}/regenerate`

Regenerates an API key.

- Operation ID: `ApiKeyResource_regenerateApiKey`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `keyId` | path | yes | integer(int32) | The API key ID of the API key you want to regenerate. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |

### `GET /provUrl`

Lists provisioning templates.

- Operation ID: `EcProvUrlZResource_getProvUrls`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. The default size is 250. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response. See [API Response Codes](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages) |

### `GET /provUrl/{id}`

Gets a provisioning template by ID.

- Operation ID: `EcProvUrlZResource_getProvUrlById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | ID of provisioning template. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response. See [API Response Codes](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages) |

### `GET /publicCloudAccountDetails`

Lists public cloud account information.

- Operation ID: `PublicAccountIdSDetailsZResource_getPublicAccountDetails`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. The default size is 250. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response. See [API Response Codes](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages) |

### `GET /publicCloudAccountDetails/{id}`

Gets the public (Cloud Connector) cloud account information for the specified ID.

- Operation ID: `PublicAccountIdSDetailsZResource_getPublicAccountDetailsById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. The default size is 250. |
| `id` | path | yes | integer(int32) | ID of the public cloud account. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response. See [API Response Codes](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages) |

### `GET /publicCloudAccountDetails/lite`

Lists a subset of public (Cloud Connector) cloud account information.

- Operation ID: `PublicAccountIdSDetailsZResource_getPublicAccountDetailsLite`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. The default size is 250. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response. See [API Response Codes](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages) |

### `GET /publicCloudAccountIdStatus`

Lists public (Cloud Connector) cloud account status information (enabled/disabled).

- Operation ID: `PublicAccountIdStatusZResource_getPublicCloudIdInfo`

**Responses:**

| Code | Description |
| --- | --- |
| default | default response. See [API Response Codes](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages) |

### `PUT /publicCloudAccountIdStatus`

Enables/disables public (Cloud Connector) cloud account status.

- Operation ID: `PublicAccountIdStatusZResource_updatePublicCloudIdInfo`

**Request body:** `application/json` → PublicCloudIdStatus

**Responses:**

| Code | Description |
| --- | --- |
| default | default response. See [API Response Codes](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages) |

### Schemas

**`ApiKey`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `keyValue` | string | no |
| `permissions` | array<string (enum: USER_ACCESS, SECURE, MANAGE, COMPLY, SSL_POLICY, ADVANCED_SETTINGS, TRAFFIC_FORWARDING, AUTHENTICATION_CONFIGURATION)> | no |
| `enabled` | boolean | no |
| `lastModifiedTime` | integer(int32) | no |
| `lastModifiedBy` | object | no |
| `partner` | object | no |
| `partnerUrl` | string | no |

**`EcProvDetails`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `desc` | string | no |
| `provUrl` | string | no |
| `provUrlType` | string (enum: ONPREM, CLOUD, DISABLED, MON_DELETED, DELETING, DELETING_LAST, DELETED) | no |
| `provUrlData` | EcProvUrlContent | no |
| `usedInEcGroups` | array<EntityReference> | no |
| `status` | string (enum: DEPLOYED, NOT_DEPLOYED) | no |
| `lastModUid` | object | no |
| `lastModTime` | integer(int32) | no |

**`PublicCloudAccountDetails`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `accountId` | string | no |
| `platformId` | string (enum: AWS, AZURE) | no |

**`PublicCloudIdStatus`**

| Property | Type | Required |
| --- | --- | --- |
| `accountIdEnabled` | boolean | no |
| `subIdEnabled` | boolean | no |

**`EntityReference`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `name` | string | no |
| `isNameL10nTag` | boolean | no |
| `extensions` | object | no |
| `deleted` | boolean | no |
| `externalId` | string | no |
| `associationTime` | integer(int32) | no |

**`EcProvUrlContent`**

| Property | Type | Required |
| --- | --- | --- |
| `zsCloudDomain` | string | no |
| `orgId` | integer(int32) | no |
| `configServer` | string | no |
| `registrationServer` | string | no |
| `apiServer` | string | no |
| `pacServer` | string | no |
| `locationTemplate` | LocationTemplate | no |
| `cloudProvider` | object | no |
| `cloudProviderType` | string (enum: AWS, AZURE) | no |
| `formFactor` | string (enum: SMALL, MEDIUM, LARGE) | no |
| `hyperVisors` | string (enum: AWS, AZURE, CENTOS, REDHAT_LINUX, VMWARE_ESXI) | no |
| `location` | object | no |
| `bcGroup` | EcGroup | no |

**`LocationTemplate`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `desc` | string | no |
| `template` | LocationTemplateDetails | no |
| `editable` | boolean | no |
| `lastModUid` | object | no |
| `lastModTime` | integer(int32) | no |

**`LocationTemplateDetails`**

| Property | Type | Required |
| --- | --- | --- |
| `templatePrefix` | string | no |
| `xffForwardEnabled` | boolean | no |
| `authRequired` | boolean | no |
| `cautionEnabled` | boolean | no |
| `aupEnabled` | boolean | no |
| `aupTimeoutInDays` | integer(int32) | no |
| `ofwEnabled` | boolean | no |
| `ipsControl` | boolean | no |
| `enforceBandwidthControl` | boolean | no |
| `upBandwidth` | integer(int32) | no |
| `dnBandwidth` | integer(int32) | no |
| `displayTimeUnit` | string (enum: MINUTE, HOUR, DAY) | no |
| `idleTimeInMinutes` | integer(int32) | no |
| `surrogateIPEnforcedForKnownBrowsers` | boolean | no |
| `surrogateRefreshTimeUnit` | string (enum: MINUTE, HOUR, DAY) | no |
| `surrogateRefreshTimeInMinutes` | integer(int32) | no |
| `surrogateIP` | boolean | no |

**`EcGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `desc` | string | no |
| `deployType` | string (enum: ONPREM, CLOUD, DISABLED, MON_DELETED, DELETING, DELETING_LAST, DELETED) | no |
| `status` | array<string (enum: ONPREM, CLOUD, DISABLED, MON_DELETED, DELETING, DELETING_LAST, DELETED)> | no |
| `platform` | string (enum: AWS, AZURE, CENTOS, REDHAT_LINUX, VMWARE_ESXI) | no |
| `awsAvailabilityZone` | string (enum: AF_SOUTH_1A, AF_SOUTH_1B, AF_SOUTH_1C, AP_EAST_1A, AP_EAST_1B, AP_EAST_1C, AP_NORTHEAST_1A, AP_NORTHEAST_1C) | no |
| `azureAvailabilityZone` | string (enum: ASIA_0, ASIA_1, ASIA_2, ASIA_3, ASIAPACIFIC_0, ASIAPACIFIC_1, ASIAPACIFIC_2, ASIAPACIFIC_3) | no |
| `location` | object | no |
| `maxEcCount` | integer(int32) | no |
| `provTemplate` | object | no |
| `tunnelMode` | string (enum: UNENCRYPTED, DTLS) | no |
| `ecVMs` | array<EcVM> | no |

**`EcVM`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `status` | array<string (enum: DISABLED, REGISTERED, RESERVED1, PKG_REPO_REGISTERED, ENABLING, DISABLING, DELETING, DELETING_LAST)> | no |
| `operationalStatus` | string | no |
| `formFactor` | string (enum: SMALL, MEDIUM, LARGE) | no |
| `managementNw` | EcNetwork | no |
| `ecInstances` | array<EcInstance> | no |
| `cityGeoId` | integer(int32) | no |
| `natIp` | string | no |
| `ziaGateway` | string | no |
| `zpaBroker` | string | no |
| `buildVersion` | string | no |
| `lastUpgradeTime` | integer(int32) | no |
| `upgradeStatus` | integer(int32) | no |
| `upgradeStartTime` | integer(int32) | no |
| `upgradeEndTime` | integer(int32) | no |
| `upgradeDayOfWeek` | integer(int32) | no |

**`EcNetwork`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `ipStart` | string | no |
| `ipEnd` | string | no |
| `netmask` | string | no |
| `defaultGateway` | string | no |
| `nwType` | string (enum: AUTOMATIC, MANUAL, DELETING, DELETED) | no |
| `dns` | EcDns | no |

**`EcDns`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `ips` | array<string> | no |
| `dnsType` | string (enum: AUTOMATIC, MANUAL, DELETING, DELETED) | no |

**`EcInstance`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `instanceType` | string (enum: ANY, NONE, ALERTEVAL, ALERTNOTIF, SMBA, SMBAC, SMESXI, SMCA) | no |
| `serviceIps` | IP | no |
| `lbIpAddr` | IP | no |
| `outGwIp` | string | no |
| `natIp` | string | no |
| `dnsIp` | array<string> | no |

**`IP`**

| Property | Type | Required |
| --- | --- | --- |
| `ipStart` | string | no |
| `ipEnd` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/provisioning-key-management","lastmod":"2026-07-30T17:21Z","nid":"1485291"} -->
## Provisioning Key Management

- Source: https://help.zscaler.com/legacy-apis/provisioning-key-management
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Provisioning Key Management
- Last modified: 2026-07-30T17:21Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/admin/customers/{customerId}/associationType/{associationType}/provisioningKey/{provisioningKeyId}`

Gets details of the provisioning key for the specified ID.

- Operation ID: `getNonce_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `associationType` | path | yes | string | Specifies the provisioning key type for App Connectors, ZPA Private Service Edges, or Network Connectors. The supported values are App Connector group (i.e., CONNECTOR_GRP), Network Connector group (i.e., NP_ASSISTANT_GRP), and ZPA Private Service Edge group (i.e., SERVICE_EDGE_GRP). |
| `provisioningKeyId` | path | yes | integer(int64) | The unique identifier of the provisioning key. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/associationType/{associationType}/provisioningKey/{provisioningKeyId}`

Updates the provisioning key details for the specified ID.

- Operation ID: `updateNonce_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `associationType` | path | yes | string | Specifies the provisioning key type for App Connectors, ZPA Private Service Edges, or Network Connectors. The supported values are App Connector group (i.e., CONNECTOR_GRP), Network Connector group (i.e., NP_ASSISTANT_GRP), and ZPA Private Service Edge group (i.e., SERVICE_EDGE_GRP). |
| `provisioningKeyId` | path | yes | integer(int64) | The unique identifier of the provisioning key. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → Provisioning Key

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/associationType/{associationType}/provisioningKey/{provisioningKeyId}`

Deletes the provisioning key for the specified ID.

- Operation ID: `deleteNonce_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `associationType` | path | yes | string | Specifies the provisioning key type for App Connectors, ZPA Private Service Edges, or Network Connectors. The supported values are App Connector group (i.e., CONNECTOR_GRP), Network Connector group (i.e., NP_ASSISTANT_GRP), and ZPA Private Service Edge group (i.e., SERVICE_EDGE_GRP). |
| `provisioningKeyId` | path | yes | integer(int64) | The unique identifier of the provisioning key. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/associationType/{associationType}/provisioningKey`

Gets details of all configured provisioning keys for the specified customer.

- Operation ID: `getNonceForAssociationType_2`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `associationType` | path | yes | string | Specifies the provisioning key type for App Connectors, ZPA Private Service Edges, or Network Connectors. The supported values are App Connector group (i.e., CONNECTOR_GRP), Network Connector group (i.e., NP_ASSISTANT_GRP), and ZPA Private Service Edge group (i.e., SERVICE_EDGE_GRP). |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The maximum page size is 500. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/associationType/{associationType}/provisioningKey`

Adds a new provisioning key for the specified customer.

- Operation ID: `createNonce_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `associationType` | path | yes | string | Specifies the provisioning key type for App Connectors, ZPA Private Service Edges, or Network Connectors. The supported values are App Connector group (i.e., CONNECTOR_GRP), Network Connector group (i.e., NP_ASSISTANT_GRP), and ZPA Private Service Edge group (i.e., SERVICE_EDGE_GRP). |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → Provisioning Key

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/associationType/{associationType}/zcomponent/{zcomponentId}/provisioningKey`

Gets the provisioning key details for the specified customer.

- Operation ID: `getNonceByZComponentId_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `associationType` | path | yes | string | The association type of the customer ID. |
| `zcomponentId` | path | yes | integer(int64) | The unique identifier of the App Connector, ZPA Private Service Edge, or Network Connector. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`Provisioning Key`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `usageCount` | integer(int32) | no |
| `maxUsage` | integer(int32) | no |
| `enrollmentCertId` | integer(int64) | yes |
| `zcomponentId` | integer(int64) | no |
| `expirationInEpochSec` | integer(int32) | no |
| `ipAcl` | array<string> | no |
| `enabled` | boolean | no |
| `enrollmentCertName` | string | no |
| `zcomponentName` | string | no |
| `uiConfig` | string | no |
| `exportable` | boolean | no |
| `provisioningKey` | string | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`PageListNonce`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<Provisioning Key> | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/public-api-controller","lastmod":"2026-06-09T04:15Z","nid":"1417361"} -->
## Public API Controller

- Source: https://help.zscaler.com/legacy-apis/public-api-controller
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > Zscaler Client Connector API > API Developer & Reference Guide > Reference Guide > Public API Controller
- Last modified: 2026-06-09T04:15Z

Api Documentation

**Servers:** `http://mobileadmin.zscalerbeta.net:80`

### `GET /papi/public/v1/downloadDevices`

Downloads or exports device information as a CSV file.

- Operation ID: `downloadEnrolledDevicesUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `osTypes` | query | no | string | Filter the list of devices by operating systems. The default value is null, which represents ALL OS types. The following values represent different OS types: 1 - iOS; 2 - Android; 3 - Windows; 4 - macOS; 5 - Linux A sample API call: https://api-mobile.zscalerbeta.net/papi/public/v1/downloadDevices?osTypes=1,2 |
| `registrationTypes` | query | no | string | Filter the list of devices by registration types or states. The following values represent different device states: 0 - All states except Removed; 1 - Registered; 3 - Removal pending; 4 - Unregistered; 5 - Removed; 6 - Quarantined A sample API call: https://api-mobile.zscalerbeta.net/papi/public/v1/downloadDevices?registrationTypes=1,3 |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/downloadDisableReasons`

Downloads or exports a report as a CSV file showing the disable reasons of a device.

- Operation ID: `asyncDownloadServiceDisabledReasonsUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `Time-Zone` | header | no | string | Time-Zone |
| `osTypes` | query | no | string | Filter the list of entries by operating systems. The default value is null, which represents ALL OS types. The following values represent different OS types: 1 - iOS2 - Android3 - Windows4 - macOS5 - Linux |
| `startDate` | query | yes | string | startDate |
| `endDate` | query | no | string | endDate |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/downloadServiceStatus`

Downloads service status of all devices as a CSV file.

- Operation ID: `downloadServiceStatusForDevicesUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `osTypes` | query | no | string | Filter the list by operating systems. The default value is null, which represents ALL OS types. The following values represent different OS types: 1 - iOS; 2 - Android; 3 - Windows; 4 - macOS; 5 - Linux A sample API call: https://api-mobile.zscalerbeta.net/papi/public/v1/downloadServiceStatus?osTypes=1,2 |
| `registrationTypes` | query | no | string | Filter the list by registration types or states. The following values represent different device states: 0 - All states except Removed; 1 - Registered; 3 - Removal pending; 4 - Unregistered; 5 - Removed; 6 - Quarantined A sample API call: https://api-mobile.zscalerbeta.net/papi/public/v1/downloadServiceStatus?registrationTypes=1,3 |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `PUT /papi/public/v1/editAdminUser`

**editAdminUser**

Updates a specific admin user.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `editAdminUserUsingPUT`

**Request body:** `application/json` → AdminAccountsContract

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `POST /papi/public/v1/forceRemoveDevices`

Force removes the enrolled device from the portal. You can only remove devices that are in registered or device removal pending state. At least one criterion (username, Zscaler Client Connector version, OS type, or UDID) must be specified to remove devices.

The following are the valid values for the `osType` attribute. The default value is null, which represents ALL OS types.

- 1 - iOS
- 2 - Android
- 3 - Windows
- 4 - macOS
- 5 - Linux

- Operation ID: `forceRemoveDevicesUsingPOST`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `pageSize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 30. |

**Request body:** `application/json` → DeviceRemoveContract

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/getAdminRoles`

**getAdminRoles**

List of admin roles in your organization.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `getAdminRolesUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | page |
| `pageSize` | query | no | integer(int32) | pageSize |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/getAdminUsers`

**getAdminUsers**

Gets the list of admin users in your organization.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `getAdminUsersUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | page |
| `pageSize` | query | no | integer(int32) | pageSize |
| `userType` | query | no | string(byte) | userType |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/getAdminUsersSyncInfo`

**adminUsersSyncInfo**

Synchronizes the local copy of admin users.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `adminUsersSyncInfoUsingGET`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/getCompanyInfo`

**getCompanyInfo**

Gets information about your organization such as the name of the business, domains, etc.

**⚠ This API endpoint is allowed if called via OneAPI or if the token has admin or read-only admin privileges.**

- Operation ID: `getCompanyInfoUsingGET`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/getDeviceCleanupInfo`

**getDeviceCleanupInfo**

Gets the configuration for device cleanup.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `getDeviceCleanupInfoUsingGET`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/getDeviceDetails`

Lists device details of enrolled devices of your organization.

- Operation ID: `getDeviceDetailsUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `udid` | query | no | string | Filter by unique device identifier. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/getDevices`

Lists all enrolled devices of your organization and their basic details.

- Operation ID: `getDevicesUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `username` | query | no | string | Filter by enrolled user name for the device. |
| `osType` | query | no | string(byte) | Filter by device operating system type. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 50. The max page size is 5000. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/getOtp`

Gets the one-time password (OTP) for a specific device. These single-use passwords are unique and tied to the device Unique Device Identifier (UDID). It is made of 10 random alphanumeric characters and can only be used once.

- Operation ID: `getOtpUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `udid` | query | no | string | Unique device identifier. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/getPasswords`

Gets the app profile password for a specific device. You can determine the assigned global passwords by using the username and OS type of a device.

- Operation ID: `getPasswordsUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `username` | query | no | string | Enrolled user for the device. |
| `osType` | query | no | integer(int32) | Device operating system. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `POST /papi/public/v1/managePass`

Updates profile policy rule passwords. The JSON Web Token (JWT) passed in as a header must be associated with a user who has Full access to Zscaler Client Connector App Profiles with an API key created with Write access. To learn more, see [Adding Roles](https://help.zscaler.com/client-connector/adding-roles) and [Adding an API Key](https://help.zscaler.com/client-connector/adding-api-key).

- Operation ID: `managePassUsingPOST`

**Request body:** `application/json` → ManagePasswordContract

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/getWebPrivacyInfo`

**getPrivacyInfo**

Gets the configuration information for end user and device-related PII.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `getPrivacyInfoUsingGET`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/getZdxGroupEntitlements`

**getUpmGroupEntitlementEnabled**

Gets the list of ZDX entitlements for user groups.

- Operation ID: `getUpmGroupEntitlementEnabledUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | page |
| `pageSize` | query | no | integer(int32) | pageSize |
| `search` | query | no | string | search |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/getZpaGroupEntitlements`

**getGroupEntitlementEnabled**

Gets the list of Private Access (ZPA) entitlements for user groups.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `getGroupEntitlementEnabledUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | page |
| `pageSize` | query | no | integer(int32) | pageSize |
| `search` | query | no | string | search |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `POST /papi/public/v1/removeDevices`

Soft removes the enrolled device from the portal. The soft removal marks the device state as device removal pending. At least one criterion (username, Zscaler Client Connector version, OS type, or UDID) must be specified to remove devices.

The following are the valid values for the `osType` attribute. The default value is null, which represents ALL OS types.

- 1 - iOS
- 2 - Android
- 3 - Windows
- 4 - macOS
- 5 - Linux

- Operation ID: `removeDevicesUsingPOST`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `pageSize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 30. The max page size is 5000. |

**Request body:** `application/json` → DeviceRemoveContract

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `POST /papi/public/v1/removeMachineTunnel`

**removeMachineTunnelDevices**

Remove machine tunnel devices.

**Note:** For the parameters described below, you can specify either hostName or machineToken but not both.

- Operation ID: `removeMachineTunnelDevicesUsingPOST`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `hostName` | query | no | string | Comma-separated list of hostnames for the device. |
| `machineToken` | query | no | string | Machine token. |

**Request body:** `application/json` → MachineTunnelDeviceRemoveContract

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `PUT /papi/public/v1/setDeviceCleanupInfo`

**setDeviceCleanupInfo**

Adds or updates the configuration for device cleanup.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `setDeviceCleanupInfoUsingPUT`

**Request body:** `application/json` → WebDeviceCleanupContract

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `PUT /papi/public/v1/setWebPrivacyInfo`

**updatePrivacyInfo**

Adds or updates the configuration information for end user and device-related PII.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `updatePrivacyInfoUsingPUT`

**Request body:** `application/json` → WebPrivacyContract

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `POST /papi/public/v1/syncZiaZdxAdminUsers`

**adminUsersZiaZdxSyncInfo**

Synchronizes the local copy of all the Internet & SaaS (ZIA) and ZDX admin users.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `adminUsersZiaZdxSyncInfoUsingPOST`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `POST /papi/public/v1/syncZpaAdminUsers`

**adminUsersZpaSyncInfo**

Synchronizes the local copy of the Private Access (ZPA) admin users.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `adminUsersZpaSyncInfoUsingPOST`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `PUT /papi/public/v1/updateZdxGroupEntitlement`

**updateGroupEntitlementUpm**

Updates ZDX entitlement for user groups.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `updateGroupEntitlementUpmUsingPUT`

**Request body:** `application/json` → GroupListServiceUpdateContract

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `PUT /papi/public/v1/updateZpaGroupEntitlement`

**updateGroupEntitlementZpa**

Updates Private Access (ZPA) entitlement for user groups.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `updateGroupEntitlementZpaUsingPUT`

**Request body:** `application/json` → GroupZPAEntitlementUpdateContract

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `PUT /papi/public/v1/web/policy/activate`

**activateWebPolicy**

Enables or disables a policy or app profile for the company by platform (iOS, Android, Windows, macOS, and Linux).

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `activateWebPolicyUsingPUT`

**Request body:** `application/json` → WebPolicyActivateRequestContract

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `PUT /papi/public/v1/web/policy/edit`

**editPolicy**

Adds or updates a policy or app profile for the company by platform (iOS, Android, Windows, macOS, and Linux).

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `editPolicyUsingPUT`

**Request body:** `application/json` → WebPolicyEditRequestContract

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/web/policy/listByCompany`

**getPolicyListByCompanyId**

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

The following are the valid values for the deviceType attribute:

- 1 - iOS
- 2 - Android
- 3 - Windows
- 4 - macOS
- 5 - Linux

- Operation ID: `getPolicyListByCompanyIdUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | page |
| `pageSize` | query | no | integer(int32) | pageSize |
| `search` | query | no | string | search |
| `searchType` | query | no | string | searchType |
| `deviceType` | query | no | integer(int32) | deviceType |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `DELETE /papi/public/v1/web/policy/{policyId}/delete`

**deletePolicy**

Deletes a policy or app profile for the company by platform (iOS, Android, Windows, macOS, and Linux).

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `deletePolicyUsingDELETE`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `policyId` | path | yes | integer(int32) | policyId |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/webAppService/listByCompany`

**getAppServiceInfoByCompanyId**

Gets the list of applications to bypass Zscaler such as Zoom, Microsoft Teams, etc.

**⚠ This API is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `getAppServiceInfoByCompanyIdUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | page |
| `pageSize` | query | no | integer(int32) | pageSize |
| `search` | query | no | string | search |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `PUT /papi/public/v1/webFailOpenPolicy/edit`

**updateFailOpenPolicy**

Updates a specific FailOpen policy for the company.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `updateFailOpenPolicyUsingPUT`

**Request body:** `application/json` → WebFailOpenPolicyContract

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/webFailOpenPolicy/listByCompany`

**getFailOpenPolicy**

Gets the list of FailOpen policies for the company.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `getFailOpenPolicyUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | page |
| `pageSize` | query | no | integer(int32) | pageSize |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `POST /papi/public/v1/webForwardingProfile/edit`

**editForwardingProfile**

Updates a forwarding profile.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `editForwardingProfileUsingPOST`

**Request body:** `application/json` → WebForwardingProfileContract

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/webForwardingProfile/listByCompany`

**forwardingProfilesByCompanyId**

Gets the list of forwarding profiles by company.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `forwardingProfilesByCompanyIdUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | page |
| `pageSize` | query | no | integer(int32) | pageSize |
| `search` | query | no | string | search |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `DELETE /papi/public/v1/webForwardingProfile/{profileId}/delete`

**deleteForwardingProfile**

Deletes a forwarding profile.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `deleteForwardingProfileUsingDELETE`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `profileId` | path | yes | integer(int32) | profileId |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `POST /papi/public/v1/webTrustedNetwork/create`

**createTrustedNetwork**

Adds a new trusted network.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `createTrustedNetworkUsingPOST`

**Request body:** `application/json` → TrustedNetworkContract

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `PUT /papi/public/v1/webTrustedNetwork/edit`

**updateTrustedNetwork**

Updates a trusted network.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `updateTrustedNetworkUsingPUT`

**Request body:** `application/json` → TrustedNetworkContract

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/webTrustedNetwork/listByCompany`

**getMultipleTrustedNetworks**

Gets the list of trusted networks by company.

- Operation ID: `getMultipleTrustedNetworksUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | page |
| `pageSize` | query | no | integer(int32) | pageSize |
| `search` | query | no | string | search |
| `searchType` | query | no | string | searchType |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `DELETE /papi/public/v1/webTrustedNetwork/{networkId}/delete`

**deleteTrustedNetwork**

Deletes a trusted network.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `deleteTrustedNetworkUsingDELETE`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `networkId` | path | yes | integer(int64) | networkId |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/application-profiles`

**getPolicyList**

Retrieves the list of application profile policies.

- Operation ID: `getPolicyListUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. The default size is 50. |
| `search` | query | no | string | The search string used to match against the policies. |
| `searchType` | query | no | string | The search string used to match against the search type. This is enabled only for filename, name, policyToken, ruleset, or groups. |
| `deviceType` | query | no | integer(int32) | The search string used to match against the device type. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/application-profiles/{profileId}`

**getCompletePolicyDetails**

Retrieves the list of policies for application profiles.

- Operation ID: `getCompletePolicyDetailsUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `profileId` | path | yes | string | ID of the profile |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `PATCH /papi/public/v1/application-profiles/{profileId}`

**patchPolicyDetails**

Update the properties of the application profile using profile ID.

The following fields are invalid for application-profiles.

- id
- groups
- deviceGroups
- onNetPolicy
- notificationTemplateContract
- users
- ziaPostureConfig
- bypassApps
- bypassCustomApps
- appServices
- policyToken
- deviceType
- policyExtension.zccFailCloseSettingsThumbPrint
- policyExtension.locationRulesetPolicies
- policyExtension.generateCliPasswordContract
- policyExtension.zccFailCloseSettingsAppByPasses
- policyExtension.zccFailCloseSettingsAppByPassNames
- disasterRecovery.policyId

- Operation ID: `patchPolicyDetailsUsingPATCH`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `profileId` | path | yes | string | ID of the profile |

**Request body:** `application/json` → PublicWebPolicyResponseBaseContractReq

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/custom-ip-based-apps`

**getCustomIPBasedApplications**

Retrieves the list of custom IP-based applications.

- Operation ID: `getCustomIPBasedApplicationsUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. |
| `search` | query | no | string | The search string used to match against the policies. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/custom-ip-based-apps/{appId}`

**getCustomIPBasedApplicationById**

Retrieves the custom IP-based application using app ID.

- Operation ID: `getCustomIPBasedApplicationByIdUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `appId` | path | yes | string | ID of the application. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/forwarding-profiles`

**forwardingProfilesByCompanyId**

Retrieves the list of forwarding profiles by company ID.

- Operation ID: `forwardingProfilesByCompanyIDUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. |
| `search` | query | no | string | The search string used to match against the policies. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/forwarding-profiles/{profileId}`

**getCompleteForwardingProfileDetails**

Retrieves the complete list of forwarding profile details using profile ID.

- Operation ID: `getCompleteForwardingProfileDetailsUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `profileId` | path | yes | string | ID of the forwarding profile. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `PATCH /papi/public/v1/forwarding-profiles/{profileId}`

**patchForwardingProfile**

Updates the properties of the forwarding profile using profile ID.

The following fields are invalid for forwarding-profiles.

- trustedNetworks
- id
- forwardingProfileActions.networkType
- forwardingProfileZpaActions.networkType
- unifiedTunnel.networkType

- Operation ID: `patchForwardingProfileUsingPATCH`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `profileId` | path | yes | string | ID of the forwarding profile. |

**Request body:** `application/json` → WebForwardingProfileContract

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/predefined-ip-based-apps`

**getPredefinedIPBasedApplications**

Retrieves the list of predefined IP-based applications.

- Operation ID: `getPredefinedIPBasedApplicationsUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. |
| `search` | query | no | string | The search string used to match against the policies. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/predefined-ip-based-apps/{appId}`

**getPredefinedIPBasedApplicationById**

Retrieves the predefined IP-based application using application ID.

- Operation ID: `getPredefinedIPBasedApplicationByIdUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `appId` | path | yes | string | ID of the application. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/process-based-apps`

**getProcessBasedApplications**

Retrieves the list of process-based applications.

- Operation ID: `getProcessBasedApplicationsUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. |
| `search` | query | no | string | The search string used to match against the policies. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### `GET /papi/public/v1/process-based-apps/{appId}`

**getProcessBasedApplicationById**

Retrieves the process-based application using appID.

- Operation ID: `getProcessBasedApplicationByIdUsingGET`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `appId` | path | yes | string | ID of the application. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 429 | Too Many Requests |

### Schemas

- `StreamingResponseBody`: object

**`AdminAccountsContract`**

| Property | Type | Required |
| --- | --- | --- |
| `accountEnabled` | string | no |
| `companyId` | string | no |
| `companyRole` | AdminRolesContract | no |
| `editEnabled` | string | no |
| `id` | integer(int32) | no |
| `isDefaultAdmin` | string | no |
| `serviceType` | string(byte) | no |
| `userName` | string | no |

**`CommonResponseContract`**

| Property | Type | Required |
| --- | --- | --- |
| `companyIds` | array<integer(int32)> | no |
| `errorCode` | string | no |
| `errorInfoArguments` | array<string> | no |
| `errorMessage` | string | no |
| `responseData` | object | no |
| `success` | string | no |

**`DeviceRemoveContract`**

| Property | Type | Required |
| --- | --- | --- |
| `clientConnectorVersion` | array<string> | no |
| `osType` | integer(int32) | no |
| `udids` | array<string> | no |
| `userName` | string | no |

**`DeviceRemoveResponseContract`**

| Property | Type | Required |
| --- | --- | --- |
| `devicesRemoved` | integer(int32) | no |
| `errorMsg` | string | no |

**`AdminRolesContract`**

| Property | Type | Required |
| --- | --- | --- |
| `adminManagement` | string | no |
| `administratorGroup` | string | no |
| `androidProfile` | string | no |
| `appBypass` | string | no |
| `appProfileGroup` | string | no |
| `auditLogs` | string | no |
| `authSetting` | string | no |
| `clientConnectorAppStore` | string | no |
| `clientConnectorIdp` | string | no |
| `clientConnectorNotifications` | string | no |
| `clientConnectorSupport` | string | no |
| `companyId` | string | no |
| `createdBy` | string | no |
| `dashboard` | string | no |
| `ddilConfiguration` | string | no |
| `dedicatedProxyPorts` | string | no |
| `deviceGroups` | string | no |
| `deviceOverview` | string | no |
| `devicePosture` | string | no |
| `enrolledDevicesGroup` | string | no |
| `forwardingProfile` | string | no |
| `id` | string | no |
| `iosProfile` | string | no |
| `isEditable` | boolean | no |
| `linuxProfile` | string | no |
| `macProfile` | string | no |
| `machineTunnel` | string | no |
| `obfuscateData` | string | no |
| `partnerDeviceOverview` | string | no |
| `publicApi` | string | no |
| `roleName` | string | no |
| `trustedNetwork` | string | no |
| `updatedBy` | string | no |
| `userAgent` | string | no |
| `windowsProfile` | string | no |
| `zpaPartnerLogin` | string | no |
| `zscalerDeception` | string | no |
| `zscalerEntitlement` | string | no |

**`WebDeviceCleanupContract`**

| Property | Type | Required |
| --- | --- | --- |
| `active` | string | no |
| `autoPurgeDays` | string | no |
| `autoRemovalDays` | string | no |
| `companyId` | string | no |
| `createdBy` | string | no |
| `deviceExceedLimit` | string | no |
| `editedBy` | string | no |
| `forceRemoveType` | string | no |
| `forceRemoveTypeString` | string | no |
| `id` | string | no |

**`DeviceDetailResponseContract`**

| Property | Type | Required |
| --- | --- | --- |
| `agent_version` | string | no |
| `carrier` | string | no |
| `config_download_time` | string | no |
| `deregistration_time` | string | no |
| `devicePolicyName` | string | no |
| `device_locale` | string | no |
| `download_count` | integer(int32) | no |
| `external_model` | string | no |
| `hardwareFingerprint` | string | no |
| `keep_alive_time` | string | no |
| `last_seen_time` | string | no |
| `mac_address` | string | no |
| `machineHostname` | string | no |
| `manufacturer` | string | no |
| `os_version` | string | no |
| `owner` | string | no |
| `registration_time` | string | no |
| `rooted` | integer(int32) | no |
| `state` | string(byte) | no |
| `tunnelVersion` | string | no |
| `type` | string(byte) | no |
| `unique_id` | string | no |
| `upmVersion` | string | no |
| `user_name` | string | no |
| `zadVersion` | string | no |
| `zappArch` | string | no |

**`DeviceListResponseContract`**

| Property | Type | Required |
| --- | --- | --- |
| `agentVersion` | string | no |
| `companyName` | string | no |
| `config_download_time` | string | no |
| `deregistrationTimestamp` | string | no |
| `detail` | string | no |
| `download_count` | integer(int32) | no |
| `hardwareFingerprint` | string | no |
| `keepAliveTime` | string | no |
| `last_seen_time` | string | no |
| `macAddress` | string | no |
| `machineHostname` | string | no |
| `manufacturer` | string | no |
| `osVersion` | string | no |
| `owner` | string | no |
| `policyName` | string | no |
| `registrationState` | string | no |
| `registration_time` | string | no |
| `state` | string(byte) | no |
| `tunnelVersion` | string | no |
| `type` | string(byte) | no |
| `udid` | string | no |
| `upmVersion` | string | no |
| `user` | string | no |
| `vpnState` | string(byte) | no |
| `zappArch` | string | no |

**`DeviceOTPContract`**

| Property | Type | Required |
| --- | --- | --- |
| `antiTemperingDisableOtp` | string | no |
| `deceptionSettingsOtp` | string | no |
| `exitOtp` | string | no |
| `logoutOtp` | string | no |
| `otp` | string | no |
| `revertOtp` | string | no |
| `uninstallOtp` | string | no |
| `zdpDisableOtp` | string | no |
| `zdxDisableOtp` | string | no |
| `ziaDisableOtp` | string | no |
| `zpaDisableOtp` | string | no |

**`UserPasswordResponse`**

| Property | Type | Required |
| --- | --- | --- |
| `exitPass` | string | no |
| `logoutPass` | string | no |
| `uninstallPass` | string | no |
| `zdSettingsAccessPass` | string | no |
| `zdxDisablePass` | string | no |
| `ziaDisablePass` | string | no |
| `zpaDisablePass` | string | no |

**`ManagePasswordContract`**

| Property | Type | Required |
| --- | --- | --- |
| `companyId` | integer(int32) | no |
| `deviceType` | integer(int32) | yes |
| `exitPass` | string | no |
| `logoutPass` | string | no |
| `policyName` | string | yes |
| `uninstallPass` | string | no |
| `zadDisablePass` | string | no |
| `zdpDisablePass` | string | no |
| `zdxDisablePass` | string | no |
| `ziaDisablePass` | string | no |
| `zpaDisablePass` | string | no |

**`ManagePassResponseContract`**

| Property | Type | Required |
| --- | --- | --- |
| `errorMessage` | string | no |

**`WebPrivacyContract`**

| Property | Type | Required |
| --- | --- | --- |
| `active` | string | no |
| `collectMachineHostname` | string | no |
| `collectUserInfo` | string | no |
| `collectZdxLocation` | string | no |
| `disableCrashlytics` | string | no |
| `enablePacketCapture` | string | no |
| `exportLogsForNonAdmin` | string | no |
| `grantAccessToZscalerLogFolder` | string | no |
| `id` | string | no |
| `overrideT2ProtocolSetting` | string | no |
| `restrictRemotePacketCapture` | string | no |

**`GroupListUpmServiceEntitlementContract`**

| Property | Type | Required |
| --- | --- | --- |
| `collectZdxLocation` | integer(int32) | no |
| `computeDeviceGroupsForZDX` | integer(int32) | no |
| `logoutZCCForZDXService` | integer(int32) | no |
| `totalCount` | integer(int64) | no |
| `upmDeviceGroupList` | array<UpmGroupServiceEntitlementContract> | no |
| `upmEnableForAll` | integer(int32) | no |
| `upmGroupList` | array<UpmGroupServiceEntitlementContract> | no |

**`GroupListServiceEntitlementContract`**

| Property | Type | Required |
| --- | --- | --- |
| `computeDeviceGroupsForZPA` | integer(int32) | no |
| `deviceGroupList` | array<GroupServiceEntitlementContract> | no |
| `groupList` | array<GroupServiceEntitlementContract> | no |
| `machineTunEnabledForAll` | integer(int32) | no |
| `totalCount` | integer(int64) | no |
| `zpaEnableForAll` | integer(int32) | no |

**`MachineTunnelDeviceRemoveContract`**

| Property | Type | Required |
| --- | --- | --- |
| `hostNames` | array<string> | no |
| `machineToken` | string | no |

**`GroupListServiceUpdateContract`**

| Property | Type | Required |
| --- | --- | --- |
| `computeDeviceGroups` | integer(int32) | no |
| `deviceGroupIds` | array<integer(int64)> | no |
| `deviceGroupNames` | array<string> | no |
| `enableForAll` | integer(int32) | no |
| `groupIds` | array<integer(int32)> | no |
| `groupNames` | array<string> | no |
| `logoutZCC` | integer(int32) | no |

**`GroupZPAEntitlementUpdateContract`**

| Property | Type | Required |
| --- | --- | --- |
| `computeDeviceGroups` | integer(int32) | no |
| `deviceGroupIds` | array<integer(int64)> | no |
| `deviceGroupNames` | array<string> | no |
| `enableForAll` | integer(int32) | no |
| `groupIds` | array<integer(int32)> | no |
| `groupNames` | array<string> | no |
| `machineTunEnabledForAll` | integer(int32) | no |

**`WebPolicyActivateRequestContract`**

| Property | Type | Required |
| --- | --- | --- |
| `deviceType` | integer(int32) | no |
| `policyId` | integer(int32) | no |

**`WebPolicyEditRequestContract`**

| Property | Type | Required |
| --- | --- | --- |
| `active` | string | no |
| `allowUnreachablePac` | boolean | no |
| `androidPolicy` | WebPolicyEditRequestAndroidExtensionContract | no |
| `appIdentityNames` | array<string> | no |
| `appServiceIds` | array<integer(int64)> | no |
| `appServiceNames` | array<string> | no |
| `bypassAppIds` | array<integer(int64)> | no |
| `bypassCustomAppIds` | array<integer(int64)> | no |
| `description` | string | no |
| `deviceGroupIds` | array<integer(int64)> | no |
| `deviceGroupNames` | array<string> | no |
| `device_type` | string | no |
| `disasterRecovery` | WebPolicyDisasterRecoveryContract | no |
| `enableDeviceGroups` | string(byte) | no |
| `forwardingProfileId` | integer(int64) | no |
| `groupAll` | string | no |
| `groupIds` | array<integer(int32)> | no |
| `groupNames` | array<string> | no |
| `highlightActiveControl` | string(byte) | no |
| `id` | string | no |
| `iosPolicy` | WebPolicyEditRequestIOSExtensionContract | no |
| `linuxPolicy` | WebPolicyEditRequestLinuxExtensionContract | no |
| `logFileSize` | string | no |
| `logLevel` | string | no |
| `logMode` | string | no |
| `macPolicy` | WebPolicyEditRequestMacExtensionContract | no |
| `name` | string | no |
| `pac_url` | string | no |
| `policyExtension` | WebPolicyExtensionContract | no |
| `reactivateWebSecurityMinutes` | string | no |
| `reauth_period` | string | no |
| `ruleOrder` | string | no |
| `sendDisableServiceReason` | string(byte) | no |
| `tunnelZappTraffic` | string | no |
| `userIds` | array<integer(int64)> | no |
| `userNames` | array<string> | no |
| `windowsPolicy` | WebPolicyEditRequestWindowsExtensionContract | no |
| `ziaPostureConfigId` | integer(int32) | no |

**`AppServiceContract`**

| Property | Type | Required |
| --- | --- | --- |
| `active` | boolean | no |
| `appDataBlob` | array<AppServiceBlobContract> | no |
| `appDataBlobV6` | array<AppServiceBlobContractV6> | no |
| `appName` | string | no |
| `appSvcId` | integer(int32) | no |
| `appVersion` | integer(int32) | no |
| `createdBy` | string | no |
| `editedBy` | string | no |
| `editedTimestamp` | string | no |
| `id` | integer(int64) | no |
| `uid` | string | no |
| `version` | integer(int32) | no |

**`WebFailOpenPolicyContract`**

| Property | Type | Required |
| --- | --- | --- |
| `active` | string | no |
| `captivePortalWebSecDisableMinutes` | integer(int32) | no |
| `companyId` | string | no |
| `createdBy` | string | no |
| `editedBy` | string | no |
| `enableCaptivePortalDetection` | string(byte) | no |
| `enableFailOpen` | string(byte) | no |
| `enableStrictEnforcementPrompt` | string(byte) | no |
| `enableWebSecOnProxyUnreachable` | string | no |
| `enableWebSecOnTunnelFailure` | string | no |
| `id` | string | no |
| `strictEnforcementPromptDelayMinutes` | integer(int32) | no |
| `strictEnforcementPromptMessage` | string | no |
| `tunnelFailureRetryCount` | integer(int32) | no |

**`WebForwardingProfileContract`**

| Property | Type | Required |
| --- | --- | --- |
| `active` | string | no |
| `conditionType` | integer(int32) | no |
| `dnsSearchDomains` | string | no |
| `dnsServers` | string | no |
| `enableAllDefaultAdaptersTN` | string(byte) | no |
| `enableLWFDriver` | string | no |
| `enableSplitVpnTN` | string(byte) | no |
| `enableUnifiedTunnel` | string(byte) | no |
| `forwardingProfileActions` | array<ForwardingProfileActionContractv2> | no |
| `forwardingProfileZpaActions` | array<ForwardingProfileActionContractZpaV2> | no |
| `hostname` | string | no |
| `id` | string | no |
| `name` | string | no |
| `predefinedTnAll` | boolean | no |
| `predefinedTrustedNetworks` | boolean | no |
| `resolvedIpsForHostname` | string | no |
| `skipTrustedCriteriaMatch` | string(byte) | no |
| `trustedDhcpServers` | string | no |
| `trustedEgressIps` | string | no |
| `trustedGateways` | string | no |
| `trustedNetworkIds` | array<integer(int64)> | no |
| `trustedNetworks` | array<string> | no |
| `trustedSubnets` | string | no |
| `unifiedTunnel` | array<UnifiedTunnelContract> | no |

**`TrustedNetworkContract`**

| Property | Type | Required |
| --- | --- | --- |
| `active` | boolean | no |
| `companyId` | string | no |
| `conditionType` | string(byte) | no |
| `createdBy` | string | no |
| `dnsSearchDomains` | string | no |
| `dnsServers` | string | no |
| `editedBy` | string | no |
| `guid` | string | no |
| `hostnames` | string | no |
| `id` | string | no |
| `networkName` | string | no |
| `resolvedIpsForHostname` | string | no |
| `ssids` | string | no |
| `trustedDhcpServers` | string | no |
| `trustedEgressIps` | string | no |
| `trustedGateways` | string | no |
| `trustedSubnets` | string | no |

**`WebPolicyContract`**

| Property | Type | Required |
| --- | --- | --- |
| `policies` | array<PublicWebPolicyResponseBaseContractRes> | no |
| `totalCount` | integer(int64) | no |

**`PublicWebPolicyResponseBaseContractReq`**

| Property | Type | Required |
| --- | --- | --- |
| `active` | integer(int32) | no |
| `appServiceIds` | array<string> | no |
| `appServices` | array<AppServiceContractReq> | no |
| `bypassAppIds` | array<string> | no |
| `bypassApps` | array<AppIdentityContract> | no |
| `bypassCustomAppIds` | array<string> | no |
| `bypassCustomApps` | array<CustomAppContract> | no |
| `description` | string | no |
| `deviceGroupIds` | array<string> | no |
| `deviceGroups` | array<PublicWebDeviceGroupContract> | no |
| `deviceType` | string (enum: DEVICE_TYPE_ANDROID, DEVICE_TYPE_IOS, DEVICE_TYPE_LINUX, DEVICE_TYPE_MAC, DEVICE_TYPE_OTHER, DEVICE_TYPE_WINDOWS) | no |
| `disasterRecovery` | WebPolicyDisasterRecoveryContract | no |
| `enableDeviceGroups` | string(byte) | no |
| `forwardingProfileId` | integer(int64) | no |
| `groupAll` | integer(int32) | no |
| `groupIds` | array<string> | no |
| `groups` | array<PublicGroupContract> | no |
| `highlightActiveControl` | string(byte) | no |
| `id` | integer(int64) | no |
| `logFileSize` | integer(int32) | no |
| `logLevel` | integer(int32) | no |
| `logMode` | integer(int32) | no |
| `name` | string | no |
| `notificationTemplateContract` | PublicNotificationTemplateContract | no |
| `notificationTemplateId` | integer(int64) | no |
| `onNetPolicy` | PublicWebForwardingProfileContract | no |
| `pac_url` | string | no |
| `policyExtension` | WebPolicyExtensionContract | no |
| `policyToken` | string | no |
| `prioritizeIPv4` | integer(int32) | no |
| `reactivateWebSecurityMinutes` | string | no |
| `reauth_period` | string | no |
| `refreshKerberosToken` | string(byte) | no |
| `ruleOrder` | integer(int32) | no |
| `sendDisableServiceReason` | string(byte) | no |
| `tunnelZappTraffic` | integer(int32) | no |
| `userIds` | array<string> | no |
| `users` | array<PublicUserContract> | no |
| `ziaPostureConfig` | PublicWebZIAPostureProfileContract | no |
| `ziaPostureConfigId` | integer(int32) | no |

**`UpmGroupServiceEntitlementContract`**

| Property | Type | Required |
| --- | --- | --- |
| `active` | integer(int32) | no |
| `authType` | string | no |
| `groupId` | integer(int32) | no |
| `groupName` | string | no |
| `upmEnabled` | integer(int32) | no |

**`GroupServiceEntitlementContract`**

| Property | Type | Required |
| --- | --- | --- |
| `active` | integer(int32) | no |
| `authType` | string | no |
| `groupId` | integer(int32) | no |
| `groupName` | string | no |
| `zpaEnabled` | integer(int32) | no |

**`WebPolicyEditRequestAndroidExtensionContract`**

| Property | Type | Required |
| --- | --- | --- |
| `allowedApps` | string | no |
| `billingDay` | string | no |
| `bypassAndroidApps` | string | no |
| `bypassMmsApps` | string | no |
| `customText` | string | no |
| `disablePassword` | string | no |
| `enableVerboseLog` | string | no |
| `enforced` | string | no |
| `installCerts` | string | no |
| `limit` | string | no |
| `logoutPassword` | string | no |
| `quotaRoaming` | string | no |
| `uninstallPassword` | string | no |
| `wifissid` | string | no |

**`WebPolicyDisasterRecoveryContract`**

| Property | Type | Required |
| --- | --- | --- |
| `allowZiaTest` | boolean | no |
| `allowZpaTest` | boolean | no |
| `enableZiaDR` | boolean | no |
| `enableZpaDR` | boolean | no |
| `policyId` | string | no |
| `useZiaGlobalDb` | boolean | no |
| `ziaCustomDbUrl` | string | no |
| `ziaDRMethod` | integer(int32) | no |
| `ziaDomainName` | string | no |
| `ziaGlobalDbUrl` | string | no |
| `ziaGlobalDbUrlv2` | string | no |
| `ziaRSAPubKey` | string | no |
| `ziaRSAPubKeyName` | string | no |
| `zpaDomainName` | string | no |
| `zpaRSAPubKey` | string | no |
| `zpaRSAPubKeyName` | string | no |

**`WebPolicyEditRequestIOSExtensionContract`**

| Property | Type | Required |
| --- | --- | --- |
| `disablePassword` | string | no |
| `ipv6Mode` | string(byte) | no |
| `logoutPassword` | string | no |
| `passcode` | string | no |
| `showVPNTunNotification` | string(byte) | no |
| `uninstallPassword` | string | no |

**`WebPolicyEditRequestLinuxExtensionContract`**

| Property | Type | Required |
| --- | --- | --- |
| `disablePassword` | string | no |
| `installCerts` | string | no |
| `logoutPassword` | string | no |
| `uninstallPassword` | string | no |

**`WebPolicyEditRequestMacExtensionContract`**

| Property | Type | Required |
| --- | --- | --- |
| `addIfscopeRoute` | string | no |
| `cacheSystemProxy` | string | no |
| `clearArpCache` | string | no |
| `disablePassword` | string | no |
| `dnsPriorityOrdering` | string | no |
| `dnsPriorityOrderingForTrustedDnsCriteria` | string | no |
| `enableApplicationBasedBypass` | string | no |
| `enableZscalerFirewall` | string | no |
| `installCerts` | string | no |
| `logoutPassword` | string | no |
| `persistentZscalerFirewall` | string | no |
| `uninstallPassword` | string | no |

**`WebPolicyExtensionContract`**

| Property | Type | Required |
| --- | --- | --- |
| `advanceZpaReauth` | boolean | no |
| `advanceZpaReauthTime` | integer(int32) | no |
| `allowClientCertCachingForWebView2` | string | no |
| `allowPacExclusionsOnly` | string | no |
| `blockPrivateRelay` | string | no |
| `browserAuthType` | string | no |
| `bypassDNSTrafficUsingUDPProxy` | string(byte) | no |
| `clientConnectorUiLanguage` | integer(int32) | no |
| `customDNS` | string | no |
| `ddilConfig` | string | no |
| `deleteDHCPOption121Routes` | string | no |
| `disableDNSRouteExclusion` | string(byte) | no |
| `dropQuicTraffic` | string(byte) | no |
| `enableAPCforCriticalSections` | string(byte) | no |
| `enableAPCforOtherSections` | string(byte) | no |
| `enableAdapterHardwareOffloading` | string | no |
| `enableAntiTampering` | string | no |
| `enableAutomaticPacketCapture` | string(byte) | no |
| `enableCrashReporting` | string(byte) | no |
| `enableCustomProxyDetection` | string | no |
| `enableCustomTheme` | string(byte) | no |
| `enableFlowBasedTunnel` | string(byte) | no |
| `enableLocalPacketCapture` | string | no |
| `enableLocationPolicyOverride` | string(byte) | no |
| `enableNetworkTrafficProcessMapping` | string(byte) | no |
| `enablePCAdditionalSpace` | string(byte) | no |
| `enableSetProxyOnVPNAdapters` | string(byte) | no |
| `enableZCCRevert` | string | no |
| `enableZdpService` | string | no |
| `enforceSplitDNS` | string(byte) | no |
| `exitPassword` | string | no |
| `fallbackToGatewayDomain` | string | no |
| `followGlobalForPartnerLogin` | string | no |
| `followGlobalForZpaReauth` | string | no |
| `followRoutingTable` | string | no |
| `generateCliPasswordContract` | GenerateCliPasswordContract | no |
| `instantForceZPAReauthStateUpdate` | string(byte) | no |
| `interceptZIATrafficAllAdapters` | string | no |
| `locationRulesetPolicies` | LocationRulesetPolicies | no |
| `machineIdpAuth` | boolean | no |
| `machineToken` | string | no |
| `nonce` | string | no |
| `oneIdMTDeviceAuthEnabled` | string | no |
| `overrideATCmdByPolicy` | string | no |
| `packetTunnelDnsExcludeList` | string | no |
| `packetTunnelDnsIncludeList` | string | no |
| `packetTunnelExcludeList` | string | no |
| `packetTunnelExcludeListForIPv6` | string | no |
| `packetTunnelIncludeList` | string | no |
| `packetTunnelIncludeListForIPv6` | string | no |
| `partnerDomains` | string | no |
| `pcAdditionalSpace` | integer(int32) | no |
| `preventAutoReauthDuringDeviceLock` | string | no |
| `prioritizeDnsExclusions` | string(byte) | no |
| `purgeKerberosPreferredDCCache` | string | no |
| `reactivateAntiTamperingTime` | integer(int32) | no |
| `recacheSystemProxy` | string | no |
| `reconnectTunOnWakeup` | string(byte) | no |
| `rscModeOnAllAdapters` | integer(int32) | no |
| `showConfirmationDialogForCachedCert` | string | no |
| `sourcePortBasedBypasses` | string | no |
| `switchFocusToNotification` | string | no |
| `truncateLargeUDPDNSResponse` | string(byte) | no |
| `updateDnsSearchOrder` | string(byte) | no |
| `useDefaultAdapterForDNS` | string | no |
| `useDefaultBrowser` | string | no |
| `useEndPointLocationForDCSelection` | string | no |
| `useProxyPortForT1` | string | no |
| `useProxyPortForT2` | string | no |
| `useV8JsEngine` | string | no |
| `useWsaPollForZpa` | string | no |
| `useZscalerNotificationFramework` | string | no |
| `userAllowedToAddPartner` | string | no |
| `vpnGateways` | string | no |
| `zccAppFailOpenPolicy` | string(byte) | no |
| `zccFailCloseSettingsAppByPassIds` | array<integer(int64)> | no |
| `zccFailCloseSettingsAppByPassNames` | array<string> | no |
| `zccFailCloseSettingsAppByPasses` | array<AppIdentityContract> | no |
| `zccFailCloseSettingsExitUninstallPassword` | string | no |
| `zccFailCloseSettingsIpBypasses` | string | no |
| `zccFailCloseSettingsLockdownOnDriverError` | string(byte) | no |
| `zccFailCloseSettingsLockdownOnFirewallError` | string(byte) | no |
| `zccFailCloseSettingsLockdownOnTunnelProcessExit` | string(byte) | no |
| `zccFailCloseSettingsThumbPrint` | string | no |
| `zccRevertPassword` | string | no |
| `zccTunnelFailPolicy` | string(byte) | no |
| `zdDisablePassword` | string | no |
| `zdpDisablePassword` | string | no |
| `zdxDisablePassword` | string | no |
| `zdxLiteConfigObj` | string | no |
| `zpaAuthExpOnNetIpChange` | string(byte) | no |
| `zpaAuthExpOnSleep` | string(byte) | no |
| `zpaAuthExpOnSysRestart` | string(byte) | no |
| `zpaAuthExpOnWinLogonSession` | string(byte) | no |
| `zpaAuthExpOnWinSessionLock` | string(byte) | no |
| `zpaAuthExpSessionLockStateMinTimeInSecond` | integer(int32) | no |
| `zpaAutoReauthTimeout` | integer(int32) | no |
| `zpaDisablePassword` | string | no |
| `zpaReauthConfig` | string | no |

**`GenerateCliPasswordContract`**

| Property | Type | Required |
| --- | --- | --- |
| `allowZdxDisableWithoutPassword` | boolean | no |
| `allowZiaDisableWithoutPassword` | boolean | no |
| `allowZpaDisableWithoutPassword` | boolean | no |
| `cliPasswordType` | integer(int32) | no |
| `enableCli` | boolean | no |
| `policyId` | integer(int64) | no |

**`LocationRulesetPolicies`**

| Property | Type | Required |
| --- | --- | --- |
| `offTrusted` | RulesetDetailContract | no |
| `splitVpnTrusted` | RulesetDetailContract | no |
| `trusted` | RulesetDetailContract | no |
| `vpnTrusted` | RulesetDetailContract | no |

**`RulesetDetailContract`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `name` | string | no |

**`AppIdentityContract`**

| Property | Type | Required |
| --- | --- | --- |
| `appName` | string | no |
| `certificatePayload` | string | no |
| `createdBy` | string | no |
| `editedBy` | string | no |
| `editedTimestamp` | string | no |
| `fileNames` | array<string> | no |
| `filePaths` | array<string> | no |
| `id` | integer(int64) | no |
| `matchingCriteria` | string(byte) | no |
| `signaturePayload` | string | no |

**`WebPolicyEditRequestWindowsExtensionContract`**

| Property | Type | Required |
| --- | --- | --- |
| `cacheSystemProxy` | integer(int32) | no |
| `captivePortalConfig` | string | no |
| `disableLoopBackRestriction` | integer(int32) | no |
| `disableParallelIpv4andIpv6` | string(byte) | no |
| `disablePassword` | string | no |
| `flowLoggerConfig` | string | no |
| `forceLocationRefreshSccm` | integer(int32) | no |
| `installCerts` | string | no |
| `installWindowsFirewallInboundRule` | integer(int32) | no |
| `logoutPassword` | string | no |
| `overrideWPAD` | integer(int32) | no |
| `pacDataPath` | string | no |
| `pacType` | integer(int32) | no |
| `prioritizeIPv4` | integer(int32) | no |
| `removeExemptedContainers` | integer(int32) | no |
| `restartWinHttpSvc` | integer(int32) | no |
| `triggerDomainProfleDetection` | integer(int32) | no |
| `uninstallPassword` | string | no |
| `wfpDriver` | integer(int32) | no |

**`AppServiceBlobContract`**

| Property | Type | Required |
| --- | --- | --- |
| `fqdn` | string | no |
| `ipaddr` | string | no |
| `port` | string | no |
| `proto` | string | no |

**`AppServiceBlobContractV6`**

| Property | Type | Required |
| --- | --- | --- |
| `fqdn` | string | no |
| `ipaddr` | string | no |
| `port` | string | no |
| `proto` | string | no |

**`ForwardingProfileActionContractv2`**

| Property | Type | Required |
| --- | --- | --- |
| `DTLSTimeout` | integer(int32) | no |
| `TLSTimeout` | integer(int32) | no |
| `UDPTimeout` | integer(int32) | no |
| `actionType` | string(byte) | no |
| `allowTLSFallback` | string(byte) | no |
| `blockUnreachableDomainsTraffic` | string(byte) | no |
| `customPac` | string | no |
| `dropIpv6IncludeTrafficInT2` | string(byte) | no |
| `dropIpv6Traffic` | string(byte) | no |
| `dropIpv6TrafficInIpv6Network` | string(byte) | no |
| `enablePacketTunnel` | string(byte) | no |
| `latencyBasedZenEnablement` | string(byte) | no |
| `mtuForZadapter` | integer(int32) | no |
| `networkType` | string(byte) | no |
| `optimiseForUnstableConnections` | string(byte) | no |
| `pathMtuDiscovery` | string(byte) | no |
| `primaryTransport` | string(byte) | no |
| `redirectWebTraffic` | string(byte) | no |
| `sendAllDNSToTrustedServer` | string(byte) | no |
| `systemProxy` | string(byte) | no |
| `systemProxyData` | WebSystemProxyDataContract | no |
| `tunnel2FallbackType` | string(byte) | no |
| `useTunnel2ForProxiedWebTraffic` | string(byte) | no |
| `useTunnel2ForUnencryptedWebTraffic` | string(byte) | no |
| `v4FailCloseTrustedDNSServer` | string | no |
| `v6FailCloseTrustedDNSServer` | string | no |
| `zenProbeInterval` | integer(int32) | no |
| `zenProbeSampleSize` | integer(int32) | no |
| `zenThresholdLimit` | integer(int32) | no |

**`WebSystemProxyDataContract`**

| Property | Type | Required |
| --- | --- | --- |
| `bypassProxyForPrivateIP` | integer(int32) | no |
| `enableAutoDetect` | integer(int32) | no |
| `enablePAC` | integer(int32) | no |
| `enableProxyServer` | integer(int32) | no |
| `pacDataPath` | string | no |
| `pacURL` | string | no |
| `performGPUpdate` | integer(int32) | no |
| `proxyAction` | string(byte) | no |
| `proxyServerAddress` | string | no |
| `proxyServerPort` | string | no |

**`ForwardingProfileActionContractZpaV2`**

| Property | Type | Required |
| --- | --- | --- |
| `DTLSTimeout` | integer(int32) | no |
| `TLSTimeout` | integer(int32) | no |
| `actionType` | string(byte) | no |
| `latencyBasedServerMTEnablement` | integer(int32) | no |
| `latencyBasedZpaServerEnablement` | string(byte) | no |
| `lbsZpaProbeInterval` | integer(int32) | no |
| `lbsZpaProbeSampleSize` | integer(int32) | no |
| `lbsZpaThresholdLimit` | integer(int32) | no |
| `mtuForZadapter` | integer(int32) | no |
| `networkType` | string(byte) | no |
| `partnerInfo` | ForwardingProfileActionContractPartnerLogin | no |
| `primaryTransport` | string(byte) | no |
| `sendTrustedNetworkResultToZpa` | string(byte) | no |

**`ForwardingProfileActionContractPartnerLogin`**

| Property | Type | Required |
| --- | --- | --- |
| `allowTlsFallback` | string(byte) | no |
| `mtuForZadapter` | integer(int32) | no |
| `primaryTransport` | string(byte) | no |

**`UnifiedTunnelContract`**

| Property | Type | Required |
| --- | --- | --- |
| `DTLSTimeout` | integer(int32) | no |
| `TLSTimeout` | integer(int32) | no |
| `actionTypeZIA` | string(byte) | no |
| `actionTypeZPA` | string(byte) | no |
| `allowTLSFallback` | string(byte) | no |
| `blockUnreachableDomainsTraffic` | string(byte) | no |
| `dropIpv6IncludeTrafficInT2` | string(byte) | no |
| `dropIpv6Traffic` | string(byte) | no |
| `dropIpv6TrafficInIpv6Network` | string(byte) | no |
| `mtuForZadapter` | integer(int32) | no |
| `networkType` | string(byte) | no |
| `optimiseForUnstableConnections` | string(byte) | no |
| `pathMtuDiscovery` | string(byte) | no |
| `primaryTransport` | string(byte) | no |
| `redirectWebTraffic` | string(byte) | no |
| `sameAsOnTrusted` | string(byte) | no |
| `sendAllDNSToTrustedServer` | string(byte) | no |
| `systemProxyData` | WebSystemProxyDataContract | no |
| `tunnel2FallbackType` | string(byte) | no |
| `v4FailCloseTrustedDNSServer` | string | no |
| `v6FailCloseTrustedDNSServer` | string | no |

**`PublicWebPolicyResponseBaseContractRes`**

| Property | Type | Required |
| --- | --- | --- |
| `active` | integer(int32) | no |
| `appServiceIds` | array<string> | no |
| `appServices` | array<AppServiceContractRes> | no |
| `bypassAppIds` | array<string> | no |
| `bypassApps` | array<AppIdentityContract> | no |
| `bypassCustomAppIds` | array<string> | no |
| `bypassCustomApps` | array<CustomAppContract> | no |
| `description` | string | no |
| `deviceGroupIds` | array<string> | no |
| `deviceGroups` | array<PublicWebDeviceGroupContract> | no |
| `deviceType` | string (enum: DEVICE_TYPE_ANDROID, DEVICE_TYPE_IOS, DEVICE_TYPE_LINUX, DEVICE_TYPE_MAC, DEVICE_TYPE_OTHER, DEVICE_TYPE_WINDOWS) | no |
| `disasterRecovery` | WebPolicyDisasterRecoveryContract | no |
| `enableDeviceGroups` | string(byte) | no |
| `groupAll` | integer(int32) | no |
| `groupIds` | array<string> | no |
| `groups` | array<PublicGroupContract> | no |
| `highlightActiveControl` | string(byte) | no |
| `id` | integer(int64) | no |
| `logFileSize` | integer(int32) | no |
| `logLevel` | integer(int32) | no |
| `logMode` | integer(int32) | no |
| `name` | string | no |
| `notificationTemplateContract` | PublicNotificationTemplateContract | no |
| `notificationTemplateId` | integer(int64) | no |
| `onNetPolicy` | PublicWebForwardingProfileContract | no |
| `pac_url` | string | no |
| `policyExtension` | WebPolicyExtensionContract | no |
| `policyToken` | string | no |
| `reactivateWebSecurityMinutes` | string | no |
| `reauth_period` | string | no |
| `ruleOrder` | integer(int32) | no |
| `sendDisableServiceReason` | string(byte) | no |
| `tunnelZappTraffic` | integer(int32) | no |
| `userIds` | array<string> | no |
| `users` | array<PublicUserContract> | no |
| `ziaPostureConfig` | PublicWebZIAPostureProfileContract | no |
| `ziaPostureConfigId` | integer(int32) | no |

**`AppServiceContractRes`**

| Property | Type | Required |
| --- | --- | --- |
| `active` | boolean | no |
| `appDataBlob` | array<AppServiceBlobContract> | no |
| `appDataBlobV6` | array<AppServiceBlobContractV6> | no |
| `appName` | string | no |
| `appSvcId` | integer(int32) | no |
| `appVersion` | integer(int32) | no |
| `createdBy` | string | no |
| `editedBy` | string | no |
| `editedTimestamp` | string | no |
| `id` | integer(int64) | no |
| `uid` | string | no |
| `version` | integer(int32) | no |
| `zappDataBlob` | string | no |
| `zappDataBlobV6` | string | no |

**`CustomAppContract`**

| Property | Type | Required |
| --- | --- | --- |
| `active` | boolean | no |
| `appDataBlob` | array<AppServiceBlobContract> | no |
| `appDataBlobV6` | array<AppServiceBlobContractV6> | no |
| `appName` | string | no |
| `createdBy` | string | no |
| `editedBy` | string | no |
| `editedTimestamp` | string | no |
| `id` | integer(int64) | no |
| `uid` | string | no |
| `zappDataBlob` | string | no |
| `zappDataBlobV6` | string | no |

**`PublicWebDeviceGroupContract`**

| Property | Type | Required |
| --- | --- | --- |
| `companyId` | string | no |
| `description` | string | no |
| `devicePosture` | string | no |
| `id` | string | no |
| `name` | string | no |
| `platformType` | string(byte) | no |

**`PublicGroupContract`**

| Property | Type | Required |
| --- | --- | --- |
| `active` | integer(int32) | no |
| `authType` | string | no |
| `id` | integer(int32) | no |
| `lastModification` | string | no |
| `name` | string | no |

**`PublicNotificationTemplateContract`**

| Property | Type | Required |
| --- | --- | --- |
| `companyId` | integer(int32) | no |
| `createdBy` | string | no |
| `customTimer` | integer(int32) | no |
| `defaultTemplate` | string | no |
| `doNotDisturb` | string | no |
| `editedBy` | string | no |
| `enableAppUpdatesNotification` | string | no |
| `enableClientNotification` | string | no |
| `enableNotificationForZPAReauth` | string | no |
| `enablePersistantNotification` | string | no |
| `enableServiceStatusNotification` | string | no |
| `enableZiaNotification` | string | no |
| `id` | integer(int64) | no |
| `showDevicePostureFailureNotification` | string | no |
| `templateName` | string | no |
| `ziaDNS` | string | no |
| `ziaDNSPopup` | string | no |
| `ziaFirewall` | string | no |
| `ziaFirewallPopup` | string | no |
| `ziaIPS` | string | no |
| `ziaIPSPopup` | string | no |
| `ziaNotificationPersistant` | string | no |
| `zpaReauthNotificationTime` | integer(int32) | no |

**`PublicWebForwardingProfileContract`**

| Property | Type | Required |
| --- | --- | --- |
| `active` | string | no |
| `companyId` | string | no |
| `conditionType` | integer(int32) | no |
| `createdBy` | string | no |
| `dnsSearchDomains` | string | no |
| `dnsServers` | string | no |
| `editedBy` | string | no |
| `enableAllDefaultAdaptersTN` | string(byte) | no |
| `enableLWFDriver` | string | no |
| `enableSplitVpnTN` | string(byte) | no |
| `enableUnifiedTunnel` | string(byte) | no |
| `forwardingProfileActions` | array<PublicForwardingProfileActionContractv2> | no |
| `forwardingProfileZpaActions` | array<PublicForwardingProfileActionContractZpaV2> | no |
| `hostname` | string | no |
| `id` | string | no |
| `name` | string | no |
| `predefinedTnAll` | boolean | no |
| `predefinedTrustedNetworks` | boolean | no |
| `resolvedIpsForHostname` | string | no |
| `skipTrustedCriteriaMatch` | string(byte) | no |
| `trustedDhcpServers` | string | no |
| `trustedEgressIps` | string | no |
| `trustedGateways` | string | no |
| `trustedNetworkIds` | array<integer(int64)> | no |
| `trustedNetworks` | array<string> | no |
| `trustedSubnets` | string | no |

**`PublicForwardingProfileActionContractv2`**

| Property | Type | Required |
| --- | --- | --- |
| `DTLSTimeout` | integer(int32) | no |
| `TLSTimeout` | integer(int32) | no |
| `UDPTimeout` | integer(int32) | no |
| `actionType` | string(byte) | no |
| `allowTLSFallback` | string(byte) | no |
| `blockUnreachableDomainsTraffic` | string(byte) | no |
| `customPac` | string | no |
| `dropIpv6IncludeTrafficInT2` | string(byte) | no |
| `dropIpv6Traffic` | string(byte) | no |
| `dropIpv6TrafficInIpv6Network` | string(byte) | no |
| `enablePacketTunnel` | string(byte) | no |
| `latencyBasedZenEnablement` | string(byte) | no |
| `mtuForZadapter` | integer(int32) | no |
| `networkType` | string(byte) | no |
| `optimiseForUnstableConnections` | string(byte) | no |
| `pathMtuDiscovery` | string(byte) | no |
| `primaryTransport` | string(byte) | no |
| `redirectWebTraffic` | string(byte) | no |
| `sendAllDNSToTrustedServer` | string(byte) | no |
| `systemProxy` | string(byte) | no |
| `systemProxyData` | PublicWebSystemProxyDataContract | no |
| `tunnel2FallbackType` | string(byte) | no |
| `useTunnel2ForProxiedWebTraffic` | string(byte) | no |
| `useTunnel2ForUnencryptedWebTraffic` | string(byte) | no |
| `v4FailCloseTrustedDNSServer` | string | no |
| `v6FailCloseTrustedDNSServer` | string | no |
| `zenProbeInterval` | integer(int32) | no |
| `zenProbeSampleSize` | integer(int32) | no |
| `zenThresholdLimit` | integer(int32) | no |

**`PublicWebSystemProxyDataContract`**

| Property | Type | Required |
| --- | --- | --- |
| `bypassProxyForPrivateIP` | integer(int32) | no |
| `enableAutoDetect` | integer(int32) | no |
| `enablePAC` | integer(int32) | no |
| `enableProxyServer` | integer(int32) | no |
| `pacDataPath` | string | no |
| `pacURL` | string | no |
| `performGPUpdate` | integer(int32) | no |
| `proxyAction` | string(byte) | no |
| `proxyServerAddress` | string | no |
| `proxyServerPort` | string | no |

**`PublicForwardingProfileActionContractZpaV2`**

| Property | Type | Required |
| --- | --- | --- |
| `DTLSTimeout` | integer(int32) | no |
| `TLSTimeout` | integer(int32) | no |
| `actionType` | string(byte) | no |
| `latencyBasedServerEnablement` | string(byte) | no |
| `latencyBasedServerMTEnablement` | integer(int32) | no |
| `lbsProbeInterval` | integer(int32) | no |
| `lbsProbeSampleSize` | integer(int32) | no |
| `lbsThresholdLimit` | integer(int32) | no |
| `mtuForZadapter` | integer(int32) | no |
| `networkType` | string(byte) | no |
| `partnerInfo` | PublicForwardingProfileActionContractPartnerLogin | no |
| `primaryTransport` | string(byte) | no |
| `sendTrustedNetworkResultToZpa` | string(byte) | no |

**`PublicForwardingProfileActionContractPartnerLogin`**

| Property | Type | Required |
| --- | --- | --- |
| `allowTlsFallback` | string(byte) | no |
| `mtuForZadapter` | integer(int32) | no |
| `primaryTransport` | string(byte) | no |

**`PublicUserContract`**

| Property | Type | Required |
| --- | --- | --- |
| `active` | integer(int32) | no |
| `companyId` | string | no |
| `id` | string | no |
| `lastModification` | string | no |
| `loginName` | string | no |

**`PublicWebZIAPostureProfileContract`**

| Property | Type | Required |
| --- | --- | --- |
| `companyId` | string | no |
| `high` | string | no |
| `id` | string | no |
| `low` | string | no |
| `medium` | string | no |
| `name` | string | no |
| `platformType` | string(byte) | no |

**`AppServiceContractReq`**

| Property | Type | Required |
| --- | --- | --- |
| `active` | boolean | no |
| `appDataBlob` | array<AppServiceBlobContract> | no |
| `appDataBlobV6` | array<AppServiceBlobContractV6> | no |
| `appName` | string | no |
| `appSvcId` | integer(int32) | no |
| `appVersion` | integer(int32) | no |
| `createdBy` | string | no |
| `editedBy` | string | no |
| `editedTimestamp` | string | no |
| `id` | integer(int64) | no |
| `uid` | string | no |
| `zappDataBlob` | string | no |
| `zappDataBlobV6` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/remote-assistance-support","lastmod":"2026-01-06T23:49Z","nid":"1510446"} -->
## Remote Assistance Support

- Source: https://help.zscaler.com/legacy-apis/remote-assistance-support
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > Remote Assistance Support
- Last modified: 2026-01-06T23:49Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /remoteAssistance`

Retrieves information about the Remote Assistance option configured in the ZIA Admin Portal. Using this option, you can allow Zscaler Support to access your organization’s ZIA Admin Portal for a specified time period to troubleshoot issues. To learn more, see [Enabling Remote Assistance](https://help.zscaler.com/zia/enabling-remote-assistance).

- Operation ID: `RemoteAssistanceResource_getRemoteAssistanceInfo`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /remoteAssistance`

Retrieves information about the Remote Assistance option configured in the ZIA Admin Portal. Using this option, you can allow Zscaler Support to access your organization’s ZIA Admin Portal for a specified time period to troubleshoot issues. To learn more, see [Enabling Remote Assistance](https://help.zscaler.com/zia/enabling-remote-assistance).

- Operation ID: `RemoteAssistanceResource_setRemoteAssistanceInfo`

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/reports","lastmod":"2026-06-26T14:22Z","nid":"1409631"} -->
## Reports

- Source: https://help.zscaler.com/legacy-apis/reports
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZDX API > API Developer & Reference Guide > Reference Guide > Reports
- Last modified: 2026-06-26T14:22Z

APIs for Zscaler Digital Experience (ZDX).

**Servers:** `https://api.zdxcloud.net/v1`, `https://dev.zdxapi.zscaler.net/v1`, `http://localhost:8080/v1`

### `GET /apps`

Lists all active applications configured for a tenant. The endpoint gets each application’s ZDX Score (default for the previous 2 hours), most impacted location, and the total number of users impacted. To learn more, see

[Monitoring the Performance Dashboard](https://help.zscaler.com/zdx/monitoring-performance-dashboard)

.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation. The response contains the ZDX Score, most impacted location details, and total users impacted for each application. |
| 401 |  |
| 403 |  |

### `GET /apps/{appid}`

Gets the application’s ZDX Score (for the previous 2 hours), most impacted locations, and the total number of users impacted. To learn more, see

[About the ZDX Score](https://help.zscaler.com/zdx/about-zdx-score#user)

.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 |  |
| 403 |  |

### `GET /apps/{appid}/score`

Gets the application’s ZDX Score trend. If the time range is not specified, the endpoint defaults to the previous 2 hours.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 |  |
| 403 |  |

### `GET /apps/{appid}/metrics`

Gets the application’s metric trend.

For Web Probes, you can access Page Fetch Time, Server Response Time, DNS Time or Availability. If metrics are not specified, it defaults to Page Fetch Time (PFT).

For Cloud Path probes, you can access latency metrics for End to End, Client - Egress, Egress - Application, Service Edge for Internet & Access - Egress, and Service Edge for Internet & Access - Application. If latency metric is not specified, it defaults to End to End latency. If the time range is not specified, the endpoint defaults to the previous 2 hours.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `metric_name` | query | no | string (enum: pft, dns, availability) |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 |  |
| 403 |  |

### `GET /apps/{appid}/users`

Gets the list of all users and their devices that were used to access an application. The endpoint allows to get the list of users based on the score category (i.e., Poor, Okay, or Good), location, department, or geoloaction. If the time range is not specified, the endpoint defaults to the previouss 2 hours.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 |  |
| 403 |  |

### `GET /apps/{appid}/users/{userid}`

Gets user details including the device information, active geolocations, and Zscaler locations. If the time range is not specified, the endpoint defaults to the previous 2 hours.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `userid` | path | yes | string | The unique user ID assigned to the user. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 |  |
| 403 |  |

### `GET /users`

Gets the list of all active users, their devices, active geolocations, and Zscaler locations. If the time range is not specified, the endpoint defaults to the previous 2 hours.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `q` | query | no | string | Search is performed against name / email. Checks for active users in the first 1000 matches. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 |  |
| 403 |  |

### `GET /users/{userid}`

Gets user details including the device information, active geolocations, and Zscaler locations. If the time range is not specified, the endpoint defaults to the previous 2 hours.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `userid` | path | yes | string | The unique user ID assigned to the user. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 |  |
| 403 |  |

### `GET /users_location`

Gets the list of active users along with their devices, active geolocations, and Zscaler locations, filtered by the configured geolocation provider. If the time range is not specified, the endpoint defaults to the previous 2 hours. The limit is 1000.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `limit` | query | no | integer | The number of items returned per request from the list. The maximum allowed value for this endpoint is 1000. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 400 | Bad Request. For example, the limit exceeds the maximum allowed value of 1000. |
| 401 |  |
| 403 |  |

### `GET /users_location/{userid}`

Get the location details for a specific user (e.g., device information, active geolocations, associated Zscaler location). If the time range is not specified, the default time range is the previous 2 hours.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 |  |
| 403 |  |
| 404 | The user ID provided in the request is invalid. |

### `GET /devices`

Gets the list of all active devices and its basic details. The JSON must contain the user’s ID and email address to associate the device to the user. If the time range is not specified, the endpoint defaults to the previous 2 hours.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 |  |
| 403 |  |

### `GET /devices/{deviceid}`

Gets the device details including the device model information, tunnel type, network, and software details. The JSON must contain the user ID and email address to associate the device to a user. If the time range is not specified, the endpoint defaults to the previous 2 hours.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 |  |
| 403 |  |

### `GET /devices/{deviceid}/apps`

Gets the list all active applications for a device. The endpoint gets the ZDX Score each application. If the time range is not specified, the endpoint defaults to the previous 2 hours.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 |  |
| 403 |  |

### `GET /devices/{deviceid}/apps/{appid}`

Gets the application’s ZDX Score trend for a device. If the time range is not specified, the endpoint defaults to the previous 2 hours.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 |  |
| 403 |  |

### `GET /devices/{deviceid}/apps/{appid}/web-probes`

Gets the list of all active web probes on a device. If the time range is not specified, the endpoint defaults to the previous 2 hours.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 |  |
| 403 |  |

### `GET /devices/{deviceid}/apps/{appid}/cloudpath-probes`

Gets the list of all active Cloud Path probes on a device. If the time range is not specified, the endpoint defaults to the previous 2 hours.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 |  |
| 403 |  |

### `GET /devices/{deviceid}/apps/{appid}/web-probes/{probeid}`

Gets the Web probe metrics trend on a device for an application. For Web probes, you can access Page Fetch Time, Server Response Time, DNS Time, or Availability. If metrics are not specified, it defaults to Page Fetch Time (PFT). If the time range is not specified, the endpoint defaults to the previous 2 hours.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 |  |
| 403 |  |

### `GET /devices/{deviceid}/apps/{appid}/cloudpath-probes/{probeid}`

Gets the Cloud Path probe metric trend on a device for an application. For Cloud Path probes, you can access latency metrics for End to End, Client - Egress, Egress - Application, Service Edge for Internet & Access - Egress, and Service Edge for Internet & Access - Application. If metrics are not specified, it defaults to End to End latency. If the time range is not specified, the endpoint defaults to the previous 2 hours.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 |  |
| 403 |  |

### `GET /devices/{deviceid}/apps/{appid}/cloudpath-probes/{probeid}/cloudpath`

Gets the Cloud Path hop data for an application on a specific device. Includes the summary data for the entire path like the total number of hops, packet loss, latency, and tunnel type (if available). It also includes a similar summary of data for each individual hop. If the time range is not specified, the endpoint defaults to the previous 2 hours.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 |  |
| 403 |  |

### `GET /devices/{deviceid}/apps/{appid}/call-quality-metrics`

Gets the Call Quality metric trend for a device for a CQM application. If the time range is not specified, the endpoint defaults to the previous 2 hours.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 |  |
| 403 |  |

### `GET /devices/{deviceid}/health-metrics`

Gets the health metrics trend for a device. If the time range is not specified, the endpoint defaults to the previous 2 hours. The health metrics include CPU, Memory, Disk I/O, Network I/O, Wi-Fi, Network Bandwidth, etc.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 |  |
| 403 |  |

### `GET /devices/{deviceid}/events`

Gets the Events metrics trend for a device. If the time range is not specified, the endpoint defaults to the previous 2 hours. The event metrics include Zscaler, Hardware, Software and Network event changes.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 |  |
| 401 |  |
| 403 |  |

### `GET /active_geo`

Gets the list of all active geolocations for the time range specified. If time range is not specified, the endpoint defaults to the previous 2 hours. The state and city data is retrieved only for the US.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `parent geo id` | query | no | string | The parent geo ID. |
| `q` | query | no | string | The search string used to support search by name. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### Schemas

**`UserLocation`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | string | no |
| `city` | string | no |
| `region` | string | no |
| `country` | string | no |
| `geo_type` | string | no |

**`Application`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer | no |
| `name` | string | no |
| `score` | number | no |
| `most_impacted_geo` | UserLocation | no |
| `stats` | object | no |

**`Series`**

| Property | Type | Required |
| --- | --- | --- |
| `metric` | string | no |
| `unit` | string | no |
| `datapoints` | array<object> | no |

**`UserDetail`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer | no |
| `name` | string | no |
| `email` | string | no |
| `score` | integer | no |
| `devices` | array<object> | no |

**`DeviceLocation`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | string | no |
| `city` | string | no |
| `state` | string | no |
| `country` | string | no |
| `geo_type` | string | no |
| `geo_lat` | string | no |
| `geo_long` | string | no |
| `geo_detection` | string | no |

**`ZSLocation`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer | no |
| `name` | string | no |

**`DeviceDetail`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer | no |
| `name` | string | no |
| `hardware` | object | no |
| `network` | array<object> | no |
| `software` | object | no |

- `NetworkStats`: array<object>

- `Cloudpath`: array<CloudPathLeg>

- `DeviceEvents`: array<object>

**`CloudPathLeg`**

| Property | Type | Required |
| --- | --- | --- |
| `src` | string | no |
| `dst` | string | no |
| `num_hops` | integer | no |
| `latency` | integer | no |
| `loss` | integer | no |
| `num_unresp_hops` | integer | no |
| `tunnel_type` | integer | no |
| `hops` | array<object> | no |

**`Event`**

| Property | Type | Required |
| --- | --- | --- |
| `category` | string | no |
| `name` | string | no |
| `display_name` | string | no |
| `prev` | string | no |
| `curr` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/resource-servers","lastmod":"2026-07-27T07:06Z","nid":"1530802"} -->
## Resource Servers

- Source: https://help.zscaler.com/legacy-apis/resource-servers
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > Authentication Service API > API Developer & Reference Guide > Reference Guide > Resource Servers
- Last modified: 2026-07-27T07:06Z

**Servers:** `https://api.zsapi.net/ziam/admin/api/v1`

### `GET /resource-servers`

Retrieves a paginated list of resource servers with an optional query parameters for pagination. To learn more, see

[Viewing API Resources](https://help.zscaler.com/zidentity/viewing-api-resources)

.

- Operation ID: `ResourceServersOps_list`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `offset` | query | no | integer(int32) | The starting point for pagination, with the number of records that can be skipped before fetching results. |
| `limit` | query | no | integer(int32) | The maximum number of records to return per request. Minimum: `0`, Maximum: `1000` |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | The request was successful, and a paginated list of resource servers are returned. |
| 401 | Authentication failed or missing authorization credentials. The request requires a valid Bearer Token. However, either no token was provided, or the provided token is invalid or expired. |

### `GET /resource-servers/{id}`

Retrieves details about a specific resource server using the server

`ID`

.

- Operation ID: `ResourceServersOps_get`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | string | Unique identifier of the resource server to retrieve. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | The request was successful, and the resource server details are returned. |
| 401 | Authentication failed or missing authorization credentials. The request requires a valid Bearer Token. However, either no token was provided, or the provided token is invalid or expired. |

### Schemas

**`ResourceServerRespDto`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | string | yes |
| `name` | string | yes |
| `displayName` | string | yes |
| `description` | string | yes |
| `primaryAud` | string | yes |
| `defaultApi` | boolean | yes |
| `serviceScopes` | array<ServiceScopePubRespDto> | yes |

**`Zscaler.Core.UnauthorizedError`**

| Property | Type | Required |
| --- | --- | --- |
| `type` | string(uri) | yes |
| `title` | string | yes |
| `status` | integer(int32) | yes |
| `detail` | string | yes |

**`ServiceScopePubRespDto`**

| Property | Type | Required |
| --- | --- | --- |
| `service` | ServiceRefDto | yes |
| `scopes` | array<ScopeRespDto> | yes |

**`ServiceRefDto`**

| Property | Type | Required |
| --- | --- | --- |
| `cloudName` | string | yes |
| `orgName` | string | yes |

**`RefPubRespDto`**

| Property | Type | Required |
| --- | --- | --- |
| `name` | string | yes |
| `displayName` | string | yes |

**`RefPubReqDto`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | string | yes |

**`ScopeRespDto`**

| Property | Type | Required |
| --- | --- | --- |
| `name` | string | yes |

**`ScopeReqDto`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | string | yes |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/root-certificates","lastmod":"2026-07-20T07:06Z","nid":"1541306"} -->
## Root Certificates

- Source: https://help.zscaler.com/legacy-apis/root-certificates
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > Root Certificates
- Last modified: 2026-07-20T07:06Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /rootCertificates`

Retrieves a list of all configured root certificates for third-party proxy-chaining services and isolation profiles. To learn more, see

[About Root Certificates](https://help.zscaler.com/zia/about-root-certificates)

.

- Operation ID: `RootCertificateResource_getCerts`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `certTypes` | query | no | object | The type of root certificate |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /rootCertificates`

Adds a custom root certificate for a third-party proxy-chaining service or isolation profile. To learn more, see

[Adding Root Certificates](https://help.zscaler.com/zia/adding-root-certificates)

.

- Operation ID: `RootCertificateResource_createRootCertificate`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | SslCertificate | Information about the certificate |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 201 | Created |
| 400 | If the file upload fails, one of the following responses is displayed: Invalid request format; Invalid file name; No file selected; Unsupported file format; File size cannot exceed 64K; Certificate has already expired; Certificate not yet valid; Invalid certificate contents, certificate and key do not match; No CSR found; Certificate cannot be parsed properly; Not a valid root certificate; Error in certificate format |
| 401 | Unauthorized |
| 403 | Forbidden |

### `GET /rootCertificates/certDetail/{rootCertId}`

Retrieves the root certificate for third-party proxy-chaining services or isolation profiles based on the specified root certificate ID

- Operation ID: `RootCertificateResource_showCert`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `rootCertId` | path | yes | object | Unique identifier for the root certificate |
| `certTypes` | query | no | object | The type of root certificate |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /rootCertificates/{rootCertId}`

Updates the root certificate based on the specified root certificate ID

- Operation ID: `RootCertificateResource_updateRootCertificate`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `rootCertId` | path | yes | object | Unique identifier for the root certificate |
| `body` | body | no | SslCertificate | Information about the certificate |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /rootCertificates/{rootCertId}`

Deletes the root certificate based on the specified root certificate ID

- Operation ID: `RootCertificateResource_deleteCert`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `rootCertId` | path | yes | object | Unique identifier for the root certificate |
| `certTypes` | query | no | object | The type of root certificate |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | Successful Operation |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/rule-labels","lastmod":"2026-03-08T07:06Z","nid":"1402486"} -->
## Rule Labels

- Source: https://help.zscaler.com/legacy-apis/rule-labels
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > Rule Labels
- Last modified: 2026-03-08T07:06Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /ruleLabels`

Retrieves a list of rule labels. To learn more, see

[About Rule Labels](https://help.zscaler.com/zia/about-rule-labels)

.

- Operation ID: `RuleLabelResource_getRuleLabels`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | object | Specifies the page offset |
| `pageSize` | query | no | object | Specifies the page size. The default size is 100, but the maximum size is 1000. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /ruleLabels`

Adds a rule label

- Operation ID: `RuleLabelResource_addRuleLabel`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /ruleLabels/ruleType/{ruleType}`

Retrieves a list of rule labels based on the specified rule type

- Operation ID: `RuleLabelResource_getRuleLabelViaRuleType`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleType` | path | yes | object | Specifies the rule type |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /ruleLabels/{ruleLabelId}`

Gets rule label information for the specified ID.

- Operation ID: `RuleLabelResource_getRuleLabelById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleLabelId` | path | yes | object | The unique identifier for the rule label. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /ruleLabels/{ruleLabelId}`

Updates rule label information for the specified ID.

- Operation ID: `RuleLabelResource_updateRuleLabel`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleLabelId` | path | yes | object | The unique identifier for the rule label. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /ruleLabels/{ruleLabelId}`

Deletes the rule label for the specified ID.

- Operation ID: `RuleLabelResource_deleteRuleLabel`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleLabelId` | path | yes | object | The unique identifier for the rule label. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/saas-security-api","lastmod":"2026-09-15T07:06Z","nid":"1529817"} -->
## SaaS Security API

- Source: https://help.zscaler.com/legacy-apis/saas-security-api
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > SaaS Security API
- Last modified: 2026-09-15T07:06Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /casbDlpRules`

Retrieves the SaaS Security Data at Rest Scanning Data Loss Prevention (DLP) rules based on the specified rule type. To learn more, see

[About Data at Rest Scanning DLP](https://help.zscaler.com/zia/about-data-rest-scanning-dlp)

.

- Operation ID: `CASBDlpRuleResource_getRules`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleType` | query | no | object | Specifies the DLP rule type |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /casbDlpRules`

Adds a new SaaS Security Data at Rest Scanning DLP rule. To learn more, see

[Configuring the Data at Rest Scanning DLP Policy](https://help.zscaler.com/zia/configuring-data-rest-scanning-dlp-policy)

.

- Operation ID: `CASBDlpRuleResource_addRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | CASBDlpRule | Information about the SaaS Security Data at Rest Scanning DLP rule |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /casbDlpRules/all`

Retrieves all the SaaS Security Data at Rest Scanning DLP rules

- Operation ID: `CASBDlpRuleResource_getAllDlpRules`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /casbDlpRules/{ruleId}`

Retrieves the SaaS Security Data at Rest Scanning DLP rule based on the specified ID

- Operation ID: `CASBDlpRuleResource_getRuleById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | Rule ID |
| `ruleType` | query | no | object | Rule type |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /casbDlpRules/{ruleId}`

Updates the SaaS Security Data at Rest Scanning DLP rule based on the specified ID

- Operation ID: `CASBDlpRuleResource_updateRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | Rule ID |
| `body` | body | no | CASBDlpRule | Information about the SaaS Security Data at Rest Scanning DLP rule |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /casbDlpRules/{ruleId}`

Deletes the SaaS Security Data at Rest Scanning DLP rule based on the specified ID

- Operation ID: `CASBDlpRuleResource_deleteRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | Rule ID |
| `ruleType` | query | no | object | Rule type |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |

### `GET /casbMalwareRules`

Retrieves the SaaS Security Data at Rest Scanning Malware Detection rules based on the specified rule type. To learn more, see

[About Data at Rest Scanning Malware Detection](https://help.zscaler.com/zia/about-data-rest-scanning-malware-detection)

.

- Operation ID: `CASBMalwareRuleResource_getRules`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleType` | query | no | object | Rule type |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /casbMalwareRules`

Adds a new SaaS Security Data at Rest Scanning Malware Detection rule. To learn more, see

[Configuring the Data at Rest Scanning Malware Detection Policy](https://help.zscaler.com/zia/configuring-data-rest-scanning-malware-detection-policy)

.

- Operation ID: `CASBMalwareRuleResource_addRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | CASBMalwareRule | Information about the SaaS Security Data at Rest Scanning Malware Detection rule |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /casbMalwareRules/all`

Retrieves all the SaaS Security Data at Rest Scanning Malware Detection rules

- Operation ID: `CASBMalwareRuleResource_getAllMalwareRules`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /casbMalwareRules/{ruleId}`

Retrieves the SaaS Security Data at Rest Scanning Malware Detection rule based on the specified ID

- Operation ID: `CASBMalwareRuleResource_getRuleById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | Rule ID |
| `ruleType` | query | no | object | Rule type |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /casbMalwareRules/{ruleId}`

Updates the SaaS Security Data at Rest Scanning Malware Detection rule based on the specified ID

- Operation ID: `CASBMalwareRuleResource_updateRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | Rule ID |
| `body` | body | no | CASBMalwareRule | Information about the SaaS Security Data at Rest Scanning Malware Detection rule |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /casbMalwareRules/{ruleId}`

Deletes the SaaS Security Data at Rest Scanning Malware Detection rule based on the specified ID

- Operation ID: `CASBMalwareRuleResource_deleteRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | Rule ID |
| `ruleType` | query | no | object | Rule type |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |

### `GET /domainProfiles/lite`

Retrieves the domain profile summary. To learn more about domain profiles, navigate to the 'Domain Profiles' section from

[About Email Profiles](https://help.zscaler.com/zia/about-email-profiles)

.

- Operation ID: `DomainProfileResource_getDomainProfilesLite`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /quarantineTombstoneTemplate/lite`

Retrieves the templates for the tombstone file created when a file is quarantined. To learn more, see

[About Quarantine Tombstone File Templates](https://help.zscaler.com/zia/about-quarantine-tombstone-file-templates)

.

- Operation ID: `QuarantineTombstoneFileTemplateResource_getQtnTombstoneFileTemplates`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /casbEmailLabel/lite`

Retrieves the email labels generated for the SaaS Security policies in a user's email account. To learn more, see

[About Email Labels](https://help.zscaler.com/zia/about-email-labels)

.

- Operation ID: `CasbEmailLabelResource_getCasbEmailLabels`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /casbTenant/{tenantId}/tags/policy`

Retrieves the tags used in the policy rules associated with a tenant, based on the tenant ID.

- Operation ID: `CloudApplicationTenantResource_getTagsForRuleAssociation`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `tenantId` | path | yes | object | SaaS application tenant ID |
| `page` | query | no | object | Specifies the page offset |
| `pageSize` | query | no | object | Specifies the page size |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /casbTenant/lite`

Retrieves information about the SaaS application tenant. To learn more, see

[About SaaS Application Tenants](https://help.zscaler.com/zia/about-saas-application-tenants)

.

- Operation ID: `CloudApplicationTenantResource_getOrgCloudAppTenantsWithoutPolicyInfo`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `activeOnly` | query | no | object | Indicates that the tenant is in use. Policies are enforced for this SaaS application. |
| `includeDeleted` | query | no | object |  |
| `appType` | query | no | object | Specifies the SaaS application type |
| `app` | query | no | object | Specifies the sanctioned SaaS application |
| `scanConfigTenantsOnly` | query | no | object | Specifies the tenant for which the scan is already configured |
| `includeBucketReadyS3Tenants` | query | no | object | For the AWS S3 SaaS application, this parameter indicates that the buckets have been read and are ready for use in policies and scan configurations. |
| `filterByFeature` | query | no | object | Filters the SaaS application tenant by feature |
| `page` | query | no | object | Specifies the page offset |
| `pageSize` | query | no | object | Specifies the page size |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /casbTenant/scanInfo`

Retrieves the SaaS Security Scan Configuration information. To learn more, see

[About SaaS Security Scan Configuration](https://help.zscaler.com/zia/about-saas-security-scan-configuration)

.

- Operation ID: `CloudApplicationTenantResource_getScanInfo`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | object | Specifies the page offset |
| `pageSize` | query | no | object | Specifies the page size |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /casbTenant/validate/status/{tenantId}`

Retrieves the validation status of the SaaS application tenant based on the tenant ID

- Operation ID: `CloudApplicationTenantResource_validateTenantCredStatus`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `tenantId` | path | yes | object | SaaS application tenant ID |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/saml-attributes","lastmod":"2026-01-06T23:49Z","nid":"1485316"} -->
## SAML Attributes

- Source: https://help.zscaler.com/legacy-apis/saml-attributes
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > SAML Attributes
- Last modified: 2026-01-06T23:49Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v2/admin/customers/{customerId}/samlAttribute`

**Gets all SAML attributes by page for the specified customer.**

- Operation ID: `getAllAttributesByPage`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v2/admin/customers/{customerId}/samlAttribute/idp/{idpId}`

**Gets all SAML attributes configured for the specified ID.**

- Operation ID: `getAllAttributesByIdpIdAndPage`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `idpId` | path | yes | integer(int64) | The unique identifier of the IdP. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/samlAttribute/{attrId}`

**Gets the SAML attribute details for the specified ID.**

- Operation ID: `getSamlAttribute_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `attrId` | path | yes | integer(int64) | The unique identifier of the SAML attribute. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`PageListSamlAttribute`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<SamlAttribute> | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`SamlAttribute`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `userAttribute` | boolean | no |
| `idpId` | integer(int64) | yes |
| `samlName` | string | yes |
| `idpName` | string | no |
| `delta` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/sandbox-policy-settings","lastmod":"2026-08-30T23:58Z","nid":"1403026"} -->
## Sandbox Policy & Settings

- Source: https://help.zscaler.com/legacy-apis/sandbox-policy-settings
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > Sandbox Policy & Settings
- Last modified: 2026-08-30T23:58Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /sandboxRules`

Retrieves the list of all Sandbox policy rules configured in the ZIA Admin Portal. To learn more, see

[About Sandbox](https://help.zscaler.com/zia/about-sandbox)

.

- Operation ID: `BARuleResource_getRules`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /sandboxRules`

Adds a Sandbox policy rule. To learn more, see

[Configuring the Sandbox Policy](https://help.zscaler.com/zia/configuring-sandbox-policy)

.

- Operation ID: `BARuleResource_addRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | yes | BARule | Sandbox policy configuration |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /sandboxRules/{ruleId}`

Retrieves the Sandbox policy rule information based on the specified ID

- Operation ID: `BARuleResource_getRuleById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | Unique identifier of the Sandbox policy rule |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /sandboxRules/{ruleId}`

Updates the Sandbox policy rule configuration for the specified ID

- Operation ID: `BARuleResource_updateRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | Unique identifier of the Sandbox policy rule |
| `body` | body | yes | BARule | Sandbox policy configuration |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /sandboxRules/{ruleId}`

Deletes the Sandbox policy rule based on the specified ID

- Operation ID: `BARuleResource_deleteRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | Unique identifier of the Sandbox policy rule |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |

### `GET /behavioralAnalysisAdvancedSettings`

Gets the custom list of MD5 file hashes that are blocked by Sandbox

- Operation ID: `BaAdvancedSettingsResource_getCustomFileHash`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /behavioralAnalysisAdvancedSettings`

Updates the custom list of MD5 file hashes that are blocked by Sandbox. This overwrites a previously generated blocklist. If you need to completely erase the blocklist, submit an empty list.

**Note:** Only the file types that are supported by Sandbox analysis can be blocked using MD5 hashes.

- Operation ID: `BaAdvancedSettingsResource_updateCustomFileHash`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | BaAdvancedSettings |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /behavioralAnalysisAdvancedSettings/fileHashCount`

Gets the used and unused quota for blocking MD5 file hashes with Sandbox

- Operation ID: `BaAdvancedSettingsResource_getCustomFileHashQuota`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/sandbox-report","lastmod":"2026-01-06T23:49Z","nid":"1402456"} -->
## Sandbox Report

- Source: https://help.zscaler.com/legacy-apis/sandbox-report
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > Sandbox Report
- Last modified: 2026-01-06T23:49Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /sandbox/report/quota`

The resource access quota for retrieving Sandbox Detail Reports is restricted to 3,000 requests per day, with a rate limit of 2/sec and 1,000/hour. Use GET `/sandbox/report/quota` to retrieve details regarding your organization's daily Sandbox API resource usage (i.e., used quota, unused quota).

- Operation ID: `getVerdictReportQuotaUsageDetails`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /sandbox/report/{md5Hash}`

Gets a full (i.e., complete) or summary detail report for an MD5 hash of a file that was analyzed by Sandbox. To view a full summary report response example, as well as possible enum values for various Sandbox Report attributes, see [Sandbox Report Use Cases](https://help.zscaler.com/zia/sandbox-report-use-cases).

- Operation ID: `getVerdictReport`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `md5Hash` | path | yes | object | MD5 hash of the file that was analyzed by Sandbox. |
| `details` | query | no | object | Type of report, full or summary. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/sandbox-submission-api","lastmod":"2026-01-06T23:49Z","nid":"1402461"} -->
## Sandbox Submission API

- Source: https://help.zscaler.com/legacy-apis/sandbox-submission-api
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > Sandbox Submission API
- Last modified: 2026-01-06T23:49Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `POST /zscsb/submit`

Submits raw or archive files (e.g., ZIP) to Sandbox for analysis. You can submit up to 100 files per day and it supports all file types that are currently supported by Sandbox. To learn more, see [About Sandbox](https://help.zscaler.com/zia/about-sandbox). By default, files are scanned by Zscaler antivirus (AV) and submitted directly to the sandbox in order to obtain a verdict. However, if a verdict already exists for the file, you can use the 'force' parameter to make the sandbox to reanalyze it.

You must have a Sandbox policy rule configured within the ZIA Admin Portal in order to analyze files that aren't present in the default policy rule. Ensure that you have explicitly added Sandbox policy rules that include the appropriate file types within your request. If not, an 'Unknown' message is shown in the response.

To learn more, see [Configuring the Sandbox Policy](https://help.zscaler.com/zia/configuring-sandbox-policy) and [Configuring the Default Sandbox Rule](https://help.zscaler.com/zia/configuring-default-sandbox-rule).

After files are sent for analysis, you must use GET /sandbox/report/{md5Hash} in order to retrieve the verdict. You can get the Sandbox report 10 minutes after a file is sent for analysis.

- Operation ID: `submitFile`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `api_token` | query | yes | object | The Sandbox API token obtained from the ZIA Admin Portal to be used for authenticating the Sandbox Submission API. To learn more, see [Managing Sandbox API Token](https://help.zscaler.com/zia/about-sandbox-api-token). |
| `force` | query | no | object | Submit file to sandbox even if found malicious during AV scan and a verdict already exists. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /zscsb/discan`

Submits raw or archive files (e.g., ZIP) to the Zscaler service for out-of-band file inspection to generate real-time verdicts for known and unknown files. It leverages capabilities such as Malware Prevention, Advanced Threat Prevention, Sandbox cloud effect, AI/ML-driven file analysis, and integrated third-party threat intelligence feeds to inspect files and classify them as benign or malicious instantaneously.

All file types supported by [Malware Protection](https://help.zscaler.com/zia/configuring-malware-protection-policy) and [Advanced Threat Protection](https://help.zscaler.com/zia/configuring-advanced-threat-protection-policy) policies can be inspected with a file size limit of up to 400 MB for AI/ML, Malware, and Advanced Threat Protection and 20 MB for Sandbox and in a large volume of files.

**Note**: Dynamic file analysis is not included in out-of-band file inspection.

- Operation ID: `submitFileForScan`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `api_token` | query | yes | object | The Sandbox API token obtained from the ZIA Admin Portal to be used for authenticating the Sandbox Submission API. To learn more, see [Managing Sandbox API Token](https://help.zscaler.com/zia/about-sandbox-api-token). |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/scim-attributes","lastmod":"2026-01-06T23:49Z","nid":"1485321"} -->
## SCIM Attributes

- Source: https://help.zscaler.com/legacy-apis/scim-attributes
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > SCIM Attributes
- Last modified: 2026-01-06T23:49Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/admin/customers/{customerId}/idp/{idpId}/scimattribute`

**Gets all SCIM attributes for the specified ID.**

- Operation ID: `getAllSCIMAttributes_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `idpId` | path | yes | integer(int64) | The unique identifier of the IdP. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/idp/{idpId}/scimattribute/{scimAttributeId}`

**Gets the SCIM attribute details for the specified ID.**

- Operation ID: `getSCIMAttribute_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `idpId` | path | yes | integer(int64) | The unique identifier of the IdP. |
| `scimAttributeId` | path | yes | integer(int64) | The unique identifier of the SCIM attribute. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /userconfig/v1/customers/{customerId}/scimattribute/idpId/{idpId}/attributeId/{attributeId}`

**Gets all SCIM attribute values for the specified ID.**

- Operation ID: `getAllSCIMAttributeValues`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `idpId` | path | yes | integer(int64) | The unique identifier of the IdP. |
| `attributeId` | path | yes | integer(int64) | The unique identifier of the SCIM attribute. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 500 | Internal Server Error |

### Schemas

**`PageListSCIMAttributeHeader`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<SCIMAttributeHeader> | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`SCIMAttributeHeader`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `idpId` | integer(int64) | no |
| `dataType` | string | yes |
| `schemaURI` | string | no |
| `multivalued` | boolean | no |
| `description` | string | no |
| `required` | boolean | no |
| `canonicalValues` | array<string> | no |
| `caseSensitive` | boolean | no |
| `mutability` | string | yes |
| `returned` | string | yes |
| `uniqueness` | boolean | no |
| `delta` | string | no |

**`PageListString`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<string> | no |

**`ZAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/scim-groups","lastmod":"2026-01-06T23:49Z","nid":"1485346"} -->
## SCIM Groups

- Source: https://help.zscaler.com/legacy-apis/scim-groups
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > SCIM Groups
- Last modified: 2026-01-06T23:49Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /userconfig/v1/customers/{customerId}/scimgroup/{scimGroupId}`

**Gets the SCIM group details for the specified ID.**

- Operation ID: `getSCIMGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `scimGroupId` | path | yes | integer(int64) | The unique identifier for the SCIM group. |
| `allEntries` | query | no | boolean | Return all SCIM groups (including the deleted ones) if set to true. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 500 | Internal Server Error |

### `GET /userconfig/v1/customers/{customerId}/scimgroup/idpId/{idpId}`

**Gets details of all SCIM groups for the specified IdP.**

- Operation ID: `getAllSCIMGroups`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `idpId` | path | yes | integer(int64) | The unique identifier of the IdP. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `idpGroupId` | query | no | string | The unique identifier of the SCIM group. |
| `scimUserId` | query | no | integer(int64) | The unique identifier for the SCIM user. |
| `scimUserName` | query | no | string | The name of the SCIM user. |
| `startTime` | query | no | integer(int64) | The start of a time range for requesting last updated data (modified_time) for the SCIM group. This requires setting the endTime parameter as well. |
| `endTime` | query | no | integer(int64) | The end of a time range for requesting last updated data (modified_time) for the SCIM group. This requires setting the startTime parameter as well. |
| `sortBy` | query | no | string | Specifies the field name to sort the results. The supported Sort fields are id, name, creationTime, and modifiedTime. If not provided, the results are sorted by the modifiedTime field. |
| `sortOrder` | query | no | string | Sort the last updated time (modified_time) by ascending (ASC) or descending (DSC) order. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |
| `allEntries` | query | no | boolean | Return all SCIM groups including the deleted ones if set to true |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 500 | Internal Server Error |

### Schemas

**`SCIMGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int64) | no |
| `creationTime` | integer(int64) | no |
| `name` | string | yes |
| `idpId` | integer(int64) | yes |
| `idpGroupId` | string | yes |
| `internalId` | string | no |

**`ZAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |

**`PageListSCIMGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<SCIMGroup> | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/sd-wan-integrations-using-api","lastmod":"2026-08-28T16:56Z","nid":"1400806"} -->
## SD-WAN Integrations Using API

- Source: https://help.zscaler.com/legacy-apis/sd-wan-integrations-using-api
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Working with APIs > SD-WAN Integrations Using API
- Last modified: 2026-08-28T16:56Z
- Summary: Information on Software-Defined Wide Area Networking (SD-WAN) partner integrations, and how to enable SD-WAN API access to integrate with the Zscaler service and set up IPSec VPN tunnels for traffic forwarding.

This article only includes information on how to:

- Enable Software-Defined Wide Area Networking (SD-WAN) partner API key access.
- Determine the best virtual IP addresses (VIPs) for an SD-WAN partner to use for your IPSec VPN tunnels.
- Use [Locations](https://help.zscaler.com/zia/location-management#/locations-get) and [VPN Credentials](https://help.zscaler.com/zia/traffic-forwarding-0#/vpnCredentials-get) API resources to set up IPSec VPN tunnels.

For details and SD-WAN deployment configuration guides for each partner (i.e., Riverbed SteelConnect, HPE Aruba, etc.), see the [SD-WAN partner site](https://www.zscaler.com/partners/technology/sd-wan?_ga=2.151531454.664659488.1542647815-1285048483.1515448871) or contact Zscaler Business Development.

## Enabling SD-WAN API Access for Partners

An SD-WAN partner API key enables technology partner access to the [Locations](https://help.zscaler.com/zia/location-management#/locations-get) resources and a [VPN Credentials](https://help.zscaler.com/zia/traffic-forwarding-0#/vpnCredentials-get) resource within the cloud service API.

To enable SD-WAN partner API access:

1. Log in to the ZIA Admin Portal.
2. Configure a [SD-WAN partner API role](https://help.zscaler.com/zia/adding-sd-wan-partner-api-roles) with SD-WAN partner access enabled.
3. Configure a [SD-WAN partner API client](https://help.zscaler.com/zia/adding-sd-wan-partner-api-clients) for the SD-WAN partner, and make sure the proper SD-WAN partner API role is applied.
4. When you have an SD-WAN partner API client and role properly configured, [add a partner key](https://help.zscaler.com/zia/configuring-sdwan-integration#AddNewKey). You cannot use a cloud service API key configured under Administration > [Cloud Service API Key Management](https://help.zscaler.com/zia/about-cloud-service-api-key-management) to enable SD-WAN partner access. You must create an SD-WAN partner key under Administration > [Partner Integrations](https://help.zscaler.com/zia/about-partner-integration-management). Also, your organization can only have one key per SD-WAN partner.
5. Send the D-WAN partner API client credentials and partner key to your SD-WAN partner.

If you [edit](https://help.zscaler.com/zia/configuring-sdwan-integration#EditNewKey), [regenerate](https://help.zscaler.com/zia/configuring-sdwan-integration#RegenerateKey), or [delete](https://help.zscaler.com/zia/configuring-sdwan-integration#DeleteKey) a partner key, make sure that you inform your SD-WAN partner of the change.

## Determining Your Zscaler Data Center VIPs for SD-WAN Integrations

You need to select the best primary and secondary data center VIPs to which the SD-WAN partner establishes an IPSec VPN tunnel. To determine the best VIPs for your cloud, use one of the following methods:

- Method 1 (Recommended): Use the `GET /vips` API endpoint to get a flat list of all VIPs for your cloud. Zscaler recommends including all VIPs in the request if you want to add your own intelligence to determine the best VIPs for your IPSec VPN tunnels, or if you want to provide a complete list to a user in order to enable them to override a selection. For example, in Python, the GET request to include all VIPs would appear as follows: `conn.request("GET", "/api/v1/vips?include=all", headers=headers)`To learn more about `GET /vips`, see Traffic Forwarding in the [API Reference](https://help.zscaler.com/zia/traffic-forwarding-0#/vips-get).
- Method 2: Use the `GET https://pac.``<Zscaler cloud>``.net/getVpnEndpoints` API endpoint, where, `<Zscaler cloud>` is the cloud name provisioned for your organization by Zscaler (e.g., `zscalerbeta`). This endpoint returns your primary, secondary, and tertiary VIPs based on geolocation proximity to the source IP or location coordinates of the edge device. To learn more, see [What is my cloud name for ZIA?](https://help.zscaler.com/zia/what-my-cloud-name) A successful API call returns a 200 OK status code. However, if an invalid query parameter is specified, a 400 status code is returned. To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/zia/api-response-codes-and-error-messages). You should only pass integers to the `getVpnEndpoints` API endpoint. A successful API call returns the following VIP information within the response. For example: { "primaryIp": "165.225.72.39", "primaryMeta": { "region": "Europe", "country": "Germany", "city": "Frankfurt", "dcName": "FRA4", "latitude": 50.000000, "longitude": 9.000000 }, "secondaryIp": "104.129.194.39", "secondaryMeta": { "region": "NorthAmerica", "country": "United States", "city": "Washington, DC", "dcName": "WAS1", "latitude": 39.000000, "longitude": -77.000000 }, "tertiaryIp": "199.168.148.132", "tertiaryMeta": { "region": "NorthAmerica", "country": "United States", "city": "Fremont, CA", "dcName": "FMT1", "latitude": 37.000000, "longitude": -121.000000 } }
  - Use `getVpnEndpoints`, with no attributes, to obtain the VIPs based on the source IP where the HTTP request originated from (e.g., `https://pac.zscalerbeta.net/getVpnEndpoints`).
  - Use the `getVpnEndpoints?srcIp=``<source IP address>` query parameter to obtain the VIPs based on a specific source IP (e.g., `https://pac.zscalerbeta.net/getVpnEndpoints?srcIp=194.208.68.97`).
  - Use the `getVpnEndpoints?lat=``<latitude coordinate>``&long=``<longitude coordinate>` query parameters to obtain the VIPs based on location coordinates (e.g., `https://pac.zscalerbeta.net/getVpnEndpoints?lat=47.1275&long=10.2637`).
  - Use the `getVpnEndpoints?subcloud=``<subcloud>` query parameter to obtain the VIPs based on a specific subcloud. To learn more, see [Understanding Subclouds](https://help.zscaler.com/zia/understanding-subclouds) (e.g., `https://pac.zscalerbeta.net/getVpnEndpoints?subcloud=americas`).
  - The `subcloud` query parameter can be used along with other parameters for the endpoint (e.g., `https://pac.zscalerbeta.net/getVpnEndpoints?lat=47.1275&long=10.2637&subcloud=americas`).
  - Use the `getVpnEndpoints?domesticPerf=``<true/false>` query parameter to obtain the VIPs based on domestic country. The default is `false`. When set to `true`, the three closest endpoints that are within the country of the requesting user are provided in the response, if available (e.g., `https://pac.zscalerbeta.net/getVpnEndpoints?domesticPref=true`). The `domesticPref` query parameter can be used along with the source IP (`srcIP`) and location coordinates (`lat`/`long`) parameters for the endpoint (e.g., `https://pac.zscalerbeta.net/getVpnEndpoints?srcIp=194.208.68.97&lat=47.1275&long=10.2637&domesticPref=true`).
- Method 3 (Not Recommended): See the [Zscaler Data Center VIPs JSON](https://help.zscaler.com/zia/zscaler-api/ips-json-api) for your cloud and find the two data centers closest to the organization's location. To learn more, see [Locating the Hostnames and IP Addresses for ZIA Public Service Edges](https://help.zscaler.com/zia/locating-the-hostnames-and-ip-addresses-your-zens).

After you have determined your VIPs, be sure to provide this information to your SD-WAN partner.

## Using Locations and VPN Credentials API Resources for SD-WAN Integrations

Within the ZIA Admin Portal, review your configured [locations](https://help.zscaler.com/zia/about-locations) or [VPN credentials](https://help.zscaler.com/zia/about-vpn-credentials) and assign the proper SD-WAN partner to manage them by setting the Managed By field. In order for partners to retrieve location details using `GET /locations`, the Managed By field (i.e., `managedBy` attribute) must be set to one of the partners defined in our service (e.g., Riverbed SteelConnect, HPE Aruba, etc.). To learn more, see the [Understanding the managedBy Attribute](https://help.zscaler.com/zia/sd-wan-api-integration#UnderstandManagedBy) section.

The Locations resources, and the `POST /vpnCredentials`resources, are designed to support SD-WAN partner integration workflows. Therefore, some limitations apply if you are attempting to use them for workflows that are not partner-specific. Also, all the location features and functionality within the ZIA Admin Portal are not available via the API (i.e., sublocations, public IP addresses, proxy ports, and location groups).

### Understanding the managedBy Attribute

The `managedBy` attribute is a unique identifier for an entity. In this case, the entity managing the location or VPN credential. In the ZIA Admin Portal, this is represented by the Managed By field within the [Locations](https://help.zscaler.com/zia/configuring-locations) and [VPN Credentials](https://help.zscaler.com/zia/adding-individual-vpn-credentials#fqdn) pages, which can be set to you (i.e., Self) or your partner (e.g., Riverbed SteelConnect).

See image.

For Locations and VPN Credentials resources, the API includes a `managedBy` attribute in the object models. Locations and VPN Credentials resources only return objects that are managed by the proper entity. So, the `managedBy` attribute must be set to one of the following partners as named in our service:

- Cisco Viptela
- Citrix SD-WAN
- CloudGenix
- HPE Aruba
- ngena
- Riverbed SteelConnect
- Silver Peak
- VMware VeloCloud

For example, if your partner is Riverbed SteelConnect and they make a call to perform a GET action, then they can only get Riverbed SteelConnect-tagged location and VPN credential objects in the response. They cannot get objects that are managed by you (i.e., Self). Also, if the partner makes a call to perform a POST, PUT, or DELETE action, then the `managedBy` attribute is automatically assigned to them.

When an API call is made using an SD-WAN [partner key](https://help.zscaler.com/zia/sd-wan-api-integration#partner_key), the call fails if the payload does not include the matching partner name in the `managedBy` attribute.

### Getting a List of Locations

The Locations resources allow an SD-WAN partner to export all attributes of a Zscaler service-defined location as a request. So, for partners, calling `GET /locations` would retrieve a list of location objects managed by them. For example, in Python, a GET request by a Riverbed SteelConnect partner would appear as follows:

```
conn.request("GET", "/api/v1/locations", headers=headers)
```

So the response would be:

```
[
    {
        "name": "nyc-2",
        "id": 4562809,
        "managedBy": {
            "id": 1,
            "name": "Riverbed SteelConnect"
        },
        "vpnCredentials": [
            {
                "id": 4562807,
                "type": "UFQDN",
                "fqdn": "nyc-1-37@yourcompany.com",
                "comments": "created automatically"
            }
        ]
    },
    {
        "name": "sjc-1",
        "id": 4562808,
        "managedBy": {
            "id": 1,
            "name": "Riverbed SteelConnect"
        },
        "vpnCredentials": [
            {
                "id": 4562805,
                "type": "UFQDN",
                "fqdn": "sjc-1-37@yourcompany.com",
                "comments": "created automatically"
            }
        ]
    }
]
```

If used, Zscaler recommends changing the `pageSize` query parameter to more than 100, or iterating through all results using the `page` query parameter, until the last page has less than 100 results.

If you send a GET request to `/locations`, the pre-shared key (PSK) for the associated VPN credential is not included in the response for security reasons.

To learn more, see the [Understanding the managedBy Attribute](https://help.zscaler.com/zia/sd-wan-api-integration#UnderstandManagedBy) section.

### Adding, Updating, and Deleting Locations

SD-WAN partners cannot get objects that are managed by you (i.e., Self). If a partner makes a call to perform a POST, PUT, or DELETE action for a location, then they can only do so for locations that they manage. To learn more, see the [Understanding the managedBy Attribute](https://help.zscaler.com/zia/sd-wan-api-integration#UnderstandManagedBy) section.

You cannot update a location's fully qualified domain name (`fqdn`) by sending a PUT request to `/locations/{locationId}`. If the `fqdn` is modified in the request, the change is ignored.

### Adding VPN Credentials

Using the `POST /vpnCredentials` resource allows an SD-WAN partner to configure IPSec VPN authentication. To learn more, see [Adding VPN Credentials](https://help.zscaler.com/zia/adding-individual-vpn-credentials#fqdn).

[Image: The Managed By field in Add Location and Add VPN Credential windows]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/security-policy-settings","lastmod":"2026-01-06T23:49Z","nid":"1402491"} -->
## Security Policy Settings

- Source: https://help.zscaler.com/legacy-apis/security-policy-settings
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > Security Policy Settings
- Last modified: 2026-01-06T23:49Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /security`

Gets a list of URLs that are on the allowlist.

- Operation ID: `getConfig`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /security`

Updates the list of URLs on the allowlist. This overwrites a previously-generated allowlist. If you need to completely erase the allowlist, submit an empty list.

- Operation ID: `createWhitelist`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `policy` | body | no | SecurityPolicy | Updates the security policy. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /security/advanced`

Gets a list of URLs that are on the denylist.

- Operation ID: `getAdvancedPolicy`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /security/advanced`

Updates the list of URLs that are on the denylist. This overwrites a previously-generated denylist. If you need to completely erase the denylist, submit an empty list.

- Operation ID: `manageBlacklist`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `advSettings` | body | no | AdvancedSecurityPolicy | Updates the Advanced Threat Protection policy's denylist. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /security/advanced/blacklistUrls`

Adds a URL to or removes a URL from the denylist. To add a URL to the denylist, set the action parameter to `ADD_TO_LIST`. To remove a URL, set action to `REMOVE_FROM_LIST`.

- Operation ID: `updateBlacklistURLs`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `action` | query | yes | object | The action applied to the Advanced Threat Protection policy’s denylist (i.e., adding a URL or removing a URL). |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/security-ueba-alerts","lastmod":"2026-08-21T03:43Z","nid":"1541301"} -->
## Security & UEBA Alerts

- Source: https://help.zscaler.com/legacy-apis/security-ueba-alerts
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > Security & UEBA Alerts
- Last modified: 2026-08-21T03:43Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /alertDefinitions`

Retrieves a list of all configured alert definitions for the organization

- Operation ID: `AlertDefinitionResource_getAlertDefinitions`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 | Unauthorized |
| 403 | Forbidden |

### `POST /alertDefinitions`

Adds an alert definition. To learn more, see

[Configuring an Alert Rule](https://help.zscaler.com/zia/configuring-alert-rule)

.

- Operation ID: `AlertDefinitionResource_addAlertDefinition`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | AlertDefinition | Information about the alert definition |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 400 | Invalid |
| 401 | Unauthorized |
| 403 | Forbidden |

### `GET /alertDefinitions/{alertDefinitionId}`

Retrieves the alert definition information based on the specified ID

- Operation ID: `AlertDefinitionResource_getAlertDefinition`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `alertDefinitionId` | path | yes | object | The alert definition ID |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

### `PUT /alertDefinitions/{alertDefinitionId}`

Updates an existing alert definition based on the specified ID

- Operation ID: `AlertDefinitionResource_updateAlertDefinition`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `alertDefinitionId` | path | yes | object | The alert definition ID |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 400 | Invalid |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

### `DELETE /alertDefinitions/{alertDefinitionId}`

Deletes an alert definition based on the specified ID

- Operation ID: `AlertDefinitionResource_deleteAlertDefinition`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `alertDefinitionId` | path | yes | object | The alert definition ID |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | Successful Operation |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

### `GET /alertRuleConfiguration/rules`

Retrieves a list of all configured Security alert rules for the organization. To learn more, see

[About Security & UEBA Alerts](https://help.zscaler.com/zia/about-security-ueba-alerts)

.

- Operation ID: `AlertRuleConfigurationResource_getAllSecurityAlertRules`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 | Unauthorized |
| 403 | Forbidden |

### `POST /alertRuleConfiguration/rules`

Adds a new Security alert rule. To learn more, go to

**For Security Alerts**

from

[Configuring an Alert Rule](https://help.zscaler.com/zia/configuring-alert-rule)

.

- Operation ID: `AlertRuleConfigurationResource_createAlertRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | AlertRuleRequest | Information about Security alerts |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 400 | Invalid |
| 401 | Unauthorized |
| 403 | Forbidden |

### `GET /alertRuleConfiguration/rules/{ruleId}`

Retrieves the Security alert rule information based on the specified ID

- Operation ID: `AlertRuleConfigurationResource_getAlertRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | Alert rule ID |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 | Unauthorized |
| 404 | Not Found |

### `PUT /alertRuleConfiguration/rules/{id}`

Updates an existing Security alert rule based on the specified ID

- Operation ID: `AlertRuleConfigurationResource_updateRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | AlertRuleRequest | Information about Security alerts |
| `id` | path | yes | object | Unique identifier for the Security alert rule |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 400 | Invalid |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

### `DELETE /alertRuleConfiguration/rules/{id}`

Deletes a Security alert rule based on the specified ID

- Operation ID: `AlertRuleConfigurationResource_deleteRules`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | object | Unique identifier for the Security alert rule |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | Successful Operation |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

### `GET /alertRuleConfiguration/rules/rulestatus`

Retrieves the statuses of all alert rules for an organization

- Operation ID: `AlertRuleConfigurationResource_getRuleStatus`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 | Unauthorized |
| 403 | Forbidden |

### `GET /alertRuleConfiguration/uebaRules`

Retrieves a list of all configured User and Entity Behavior Analytics (UEBA) alert rules. To learn more, go to

**UEBA Alerts**

from

[About Security & UEBA Alerts](https://help.zscaler.com/zia/about-security-ueba-alerts)

.

- Operation ID: `AlertRuleConfigurationResource_getAllRules`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 | Unauthorized |
| 403 | Forbidden |

### `POST /alertRuleConfiguration/uebaRules`

Adds a new UEBA alert rule. To learn more, go to

**For UEBA Alerts**

from

[Configuring an Alert Rule](https://help.zscaler.com/zia/configuring-alert-rule)

- Operation ID: `AlertRuleConfigurationResource_createUEBAAlertRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | AlertRuleRequest | UEBA alert rule information |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 400 | Invalid |
| 401 | Unauthorized |
| 403 | Forbidden |

### `GET /alertRuleConfiguration/uebaRules/{ruleId}`

Retrieves information about UEBA alert rule based on the specified ID

- Operation ID: `AlertRuleConfigurationResource_getUEBAAlertRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | Alert rule ID |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 | Unauthorized |
| 404 | Not Found |

### `PUT /alertRuleConfiguration/uebaRules/{id}`

Updates an existing UEBA alert rule based on the specified ID

- Operation ID: `AlertRuleConfigurationResource_updateUEBARule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | AlertRuleRequest | UEBA alert rule information |
| `id` | path | yes | object | Unique identifier for the UEBA alert rule |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 400 | Invalid |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

### `DELETE /alertRuleConfiguration/uebaRules/{id}`

Deletes a UEBA alert rule based on the specified ID

- Operation ID: `AlertRuleConfigurationResource_deleteUEBARules`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | object | Unique identifier for the UEBA alert rule |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | Successful Operation |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

### `GET /alertRuleConfiguration/webhooks`

Retrieves a list of all configured webhooks for an organization. To learn more, see

[About Webhooks](https://help.zscaler.com/zia/about-webhooks)

.

- Operation ID: `AlertRuleConfigurationResource_getAllWebhooks`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 | Unauthorized |
| 403 | Forbidden |

### `POST /alertRuleConfiguration/webhooks`

Adds a new webhook for alert notifications. To learn more, see

[Adding a Webhook](https://help.zscaler.com/zia/adding-webhook)

.

- Operation ID: `AlertRuleConfigurationResource_createWebhook`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | Webhook | Information about the webhook |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 400 | Invalid |
| 401 | Unauthorized |
| 403 | Forbidden |

### `PUT /alertRuleConfiguration/webhooks/{id}`

Updates an existing webhook based on the specified ID

- Operation ID: `AlertRuleConfigurationResource_updateWebhook`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | Webhook | Information about the webhook |
| `id` | path | yes | object | Webhook ID |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 400 | Invalid |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

### `DELETE /alertRuleConfiguration/webhooks/{id}`

Deletes a webhook based on the specified ID

- Operation ID: `AlertRuleConfigurationResource_deleteWebhook`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | object | Webhook ID |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | Successful Operation |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

### `POST /alertRuleConfiguration/webhooks/test`

Tests a webhook configuration by sending a sample notification. Any non-2xx HTTP response from this webhook endpoint is treated as a failure, and redirects are not followed.

- Operation ID: `AlertRuleConfigurationResource_testWebhook`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | Webhook | Information about the webhook |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 400 | Invalid |
| 401 | Unauthorized |
| 403 | Forbidden |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/segment-group-management","lastmod":"2026-01-06T23:49Z","nid":"1485251"} -->
## Segment Group Management

- Source: https://help.zscaler.com/legacy-apis/segment-group-management
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Segment Group Management
- Last modified: 2026-01-06T23:49Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `PUT /mgmtconfig/v2/admin/customers/{customerId}/segmentGroup/{segmentGroupId}`

**Updates the segment group for the specified ID.**

- Operation ID: `updateApplicationGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `segmentGroupId` | path | yes | integer(int64) | The unique identifier of the segment group. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → ApplicationGroupDto

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/segmentGroup/{segmentGroupId}`

**Gets the segment group details for the specified ID.**

- Operation ID: `getApplicationGroup_2`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `segmentGroupId` | path | yes | integer(int64) | The unique identifier of the segment group. |
| `skipDetailedAppInfo` | query | no | boolean | If set to true, then the complete application details are returned. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/segmentGroup/{segmentGroupId}`

**Updates the segment group for the specified ID.**

- Operation ID: `updateApplicationGroup_2`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `segmentGroupId` | path | yes | integer(int64) | The unique identifier of the segment group. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → SegmentGroup

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/segmentGroup/{segmentGroupId}`

**Deletes the segment group for the specified ID.**

- Operation ID: `deleteApplicationGroup_2`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `segmentGroupId` | path | yes | integer(int64) | The unique identifier of the segment group. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/segmentGroup`

**Gets all configured segment groups for the specified customer.**

- Operation ID: `getAllApplicationGroups_2`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |
| `skipDetailedAppInfo` | query | no | boolean | If set to true, then the complete application details are returned. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/segmentGroup`

**Adds a new segment group for the specified customer.**

- Operation ID: `addApplicationGroup_2`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained in the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations are limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → SegmentGroup

**Responses:**

| Code | Description |
| --- | --- |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`ApplicationGroupDto`**

| Property | Type | Required |
| --- | --- | --- |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | string | no |
| `id` | string | no |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `name` | string | no |
| `description` | string | no |
| `enabled` | boolean | no |
| `addedApps` | array<integer(int64)> | no |
| `deletedApps` | array<integer(int64)> | no |
| `configSpace` | string (enum: DEFAULT, SIEM) | no |
| `tcpKeepAliveEnabled` | integer(int32) | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`SegmentGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `description` | string | no |
| `enabled` | boolean | no |
| `applications` | array<ApplicationBase> | no |
| `policyMigrated` | boolean | no |
| `configSpace` | string (enum: DEFAULT, SIEM) | no |
| `tcpKeepAliveEnabled` | integer(int32) | no |
| `applicationNames` | array<NameIdDto> | no |
| `sharedMicrotenantIds` | array<integer(int64)> | no |

**`PageListApplicationGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<SegmentGroup> | no |

**`ApplicationBase`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `sharedMicrotenantIds` | array<integer(int64)> | no |
| `domainName` | string | no |
| `domainNames` | array<string> | no |
| `description` | string | no |
| `tcpPortsIn` | array<integer(int32)> | no |
| `tcpPortsOut` | array<integer(int32)> | no |
| `defaultIdleTimeout` | integer(int32) | no |
| `defaultMaxAge` | integer(int32) | no |
| `enabled` | boolean | no |
| `passiveHealthEnabled` | boolean | no |
| `tcpPortRanges` | array<integer(int32)> | no |
| `udpPortRanges` | array<integer(int32)> | no |
| `doubleEncrypt` | boolean | no |
| `healthCheckType` | string (enum: DEFAULT, NONE) | no |
| `icmpAccessType` | string (enum: PING_TRACEROUTING, PING, NONE) | no |
| `bypassType` | string (enum: ALWAYS, NEVER, ON_NET) | no |
| `configSpace` | string (enum: DEFAULT, SIEM) | no |
| `ipAnchored` | boolean | no |
| `bypassOnReauth` | boolean | no |
| `inspectTrafficWithZia` | boolean | no |
| `logFeatures` | array<string (enum: skip_discovery, full_wildcard)> | no |
| `tcpKeepAlive` | integer(int32) | no |
| `useInDrMode` | boolean | no |
| `matchStyle` | string (enum: EXCLUSIVE, INCLUSIVE) | no |
| `serverGroups` | array<AppServerGroup> | no |
| `selectConnectorCloseToApp` | boolean | no |
| `weightedLoadBalancing` | boolean | no |
| `extranetEnabled` | boolean | no |
| `zpnErId` | ZpnExtranetResource | no |
| `tcpProtocolsBitmasks` | array<integer(int32)> | no |
| `udpProtocolsBitmasks` | array<integer(int32)> | no |
| `adpEnabled` | boolean | no |
| `apiProtectionEnabled` | boolean | no |
| `autoAppProtectEnabled` | boolean | no |
| `fqdnDnsCheck` | boolean | no |
| `tcpPortRange` | array<AppPortRange> | no |
| `udpPortRange` | array<AppPortRange> | no |

**`AppServerGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | yes |
| `dynamicDiscovery` | boolean | no |
| `description` | string | no |
| `configSpace` | string (enum: DEFAULT, SIEM) | yes |
| `weight` | integer(int32) | no |
| `passive` | boolean | no |
| `extranetEnabled` | boolean | no |
| `zpnErId` | ZpnExtranetResource | no |

**`ZpnExtranetResource`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `ziaErId` | integer(int64) | yes |
| `ziaErName` | string | yes |
| `ziaCloud` | string | yes |
| `ziaOrgId` | integer(int64) | yes |
| `ziaModifiedTime` | integer(int64) | no |

**`AppPortRange`**

| Property | Type | Required |
| --- | --- | --- |
| `from` | integer(int32) | no |
| `to` | integer(int32) | no |

**`NameIdDto`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `name` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/server-group-management","lastmod":"2026-07-30T17:26Z","nid":"1485326"} -->
## Server Group Management

- Source: https://help.zscaler.com/legacy-apis/server-group-management
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Server Group Management
- Last modified: 2026-07-30T17:26Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/admin/customers/{customerId}/serverGroup`

Gets all configured server groups for the specified customer.

- Operation ID: `getAllServerGroups_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The maximum page size is 500. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/serverGroup`

Adds a new server group for the specified customer.

- Operation ID: `addAppServerGroup_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → ServerGroupDTO

**Responses:**

| Code | Description |
| --- | --- |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/serverGroup/{groupId}`

Gets the server group details for the specified ID.

- Operation ID: `getServerGroup_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `groupId` | path | yes | integer(int64) | The unique identifier of the server group. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/serverGroup/{groupId}`

Updates the server group for the specified ID.

- Operation ID: `updateAppServerGroup_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `groupId` | path | yes | integer(int64) | The unique identifier of the server group. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → ServerGroupDTO

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/serverGroup/{groupId}`

Deletes the server group for the specified ID.

- Operation ID: `deleteAppServerGroup_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `groupId` | path | yes | integer(int64) | The unique identifier of the server group. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/serverGroup/summary`

Gets a summary of server groups for the specified customer.

- Operation ID: `getAllServerGroupNameIds_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`PageListServerGroupDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<ServerGroupDTO> | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`ServerGroupDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | string | no |
| `id` | string | no |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | no |
| `name` | string | yes |
| `dynamicDiscovery` | boolean | no |
| `description` | string | no |
| `appConnectorGroups` | array<AppConnectorGroup> | no |
| `applications` | array<NameIdDto> | no |
| `ipAnchored` | boolean | no |
| `configSpace` | string (enum: DEFAULT, SIEM) | yes |
| `inconsistentConfigDetails` | InconsistentConfigDetails | no |
| `extranetDTO` | ExtranetDTO | no |
| `extranetEnabled` | boolean | no |
| `servers` | array<ApplicationServer> | no |

**`PageListNameIdDto`**

| Property | Type | Required |
| --- | --- | --- |
| `currentCount` | integer(int64) | no |
| `list` | array<NameIdDto> | no |
| `message` | string | no |
| `totalCount` | integer(int64) | no |
| `totalPages` | integer(int32) | no |

**`AppConnectorGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | no |
| `description` | string | no |
| `geoLocationId` | integer(int64) | no |
| `versionProfileId` | integer(int64) | no |
| `overrideVersionProfile` | boolean | no |
| `siteId` | string | no |
| `versionProfileName` | string | no |
| `upgradePriority` | string (enum: WEEK, DAY, FORCE_NOW, NOW) | no |
| `versionProfileVisibilityScope` | string (enum: ALL, NONE, CUSTOM) | no |
| `upgradeTimeInSecs` | string | no |
| `upgradeDay` | string | no |
| `siteName` | string | no |
| `connectors` | array<Connector> | no |
| `location` | string | no |
| `latitude` | string | no |
| `city` | string | no |
| `longitude` | string | no |
| `serverGroups` | array<AppServerGroup> | no |
| `dnsQueryType` | string (enum: IPV4_IPV6, IPV4, IPV6) | no |
| `cityCountry` | string | no |
| `countryCode` | string | no |
| `tcpQuickAckApp` | boolean | no |
| `tcpQuickAckAssistant` | boolean | no |
| `tcpQuickAckReadAssistant` | boolean | no |
| `ipAcl` | array<string> | no |
| `praEnabled` | boolean | no |
| `useInDrMode` | boolean | no |
| `connectorGroupType` | string (enum: APP, NP) | no |
| `dcHostingInfo` | string | no |
| `wafDisabled` | boolean | no |
| `npAssistantGroup` | NPAssistantGroup | no |
| `lssAppConnectorGroup` | boolean | no |

**`Connector`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `description` | string | no |
| `fingerprint` | string | no |
| `issuedCertId` | integer(int64) | no |
| `enabled` | boolean | no |
| `ipAcl` | array<string> | no |
| `provisioningKeyId` | integer(int64) | no |
| `connectorType` | string (enum: APP, NP) | no |
| `provisioningKeyName` | string | no |
| `enrollmentCert` | object | no |
| `appConnectorGroupId` | string | no |
| `appConnectorGroupName` | string | no |
| `assistantVersion` | AssistantVersion | no |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `previousVersion` | string | no |
| `lastUpgradeTime` | integer(int64) | no |
| `expectedUpgradeTime` | integer(int64) | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `controlChannelStatus` | string (enum: UNKNOWN, ZPN_STATUS_AUTHENTICATED, ZPN_STATUS_DISCONNECTED) | no |
| `upgradeAttempt` | integer(int32) | no |
| `ctrlBrokerName` | string | no |
| `lastBrokerConnectTime` | integer(int64) | no |
| `lastBrokerConnectTimeDuration` | string | no |
| `sargeVersion` | string | no |
| `lastBrokerDisconnectTime` | integer(int64) | no |
| `lastBrokerDisconnectTimeDuration` | string | no |
| `privateIp` | string | no |
| `publicIp` | string | no |
| `platform` | string | no |
| `runtimeOS` | string | no |
| `applicationStartTime` | integer(int64) | no |
| `latitude` | number(double) | no |
| `longitude` | number(double) | no |
| `location` | string | no |
| `platformDetail` | string | no |
| `zpnSubModuleUpgradeList` | array<ZpnSubModuleUpgrade> | no |
| `npAssistant` | NPAssistant | no |

**`AssistantVersion`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `systemStartTime` | integer(int64) | no |
| `applicationStartTime` | integer(int64) | no |
| `lastBrokerConnectTime` | integer(int64) | no |
| `lastBrokerDisconnectTime` | integer(int64) | no |
| `brokerId` | integer(int64) | no |
| `restartTimeInSec` | integer(int64) | no |
| `disableAutoUpdate` | boolean | no |
| `appConnectorGroupId` | integer(int64) | no |
| `platform` | string | no |
| `runtimeOS` | string | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `ctrlChannelStatus` | string (enum: UNKNOWN, ZPN_STATUS_AUTHENTICATED, ZPN_STATUS_DISCONNECTED) | no |
| `latitude` | number(double) | no |
| `longitude` | number(double) | no |
| `privateIp` | string | no |
| `publicIp` | string | no |
| `loneWarrior` | boolean | no |
| `mtunnelId` | string | no |
| `previousVersion` | string | no |
| `lastUpgradedTime` | integer(int64) | no |
| `upgradeNowOnce` | boolean | no |
| `upgradeAttempt` | integer(int32) | no |
| `sargeVersion` | string | no |
| `platformDetail` | string | no |

**`ZpnSubModuleUpgrade`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `entityGid` | integer(int64) | yes |
| `entityType` | string (enum: ASSISTANT, PRIVATE_BROKER, MMDB_GEOIP, MMDB_ISP, GUACD, SITE_CONTROLLER) | no |
| `role` | string (enum: ASSISTANT, PRIVATE_BROKER, MMDB_GEOIP, MMDB_ISP, GUACD, SITE_CONTROLLER) | yes |
| `expectedVersion` | string | no |
| `currentVersion` | string | no |
| `previousVersion` | string | no |
| `upgradeStatus` | string (enum: COMPLETE, IN_PROGRESS, FAILED, UNKNOWN, RESTARTING, PARTIAL_FAILURE, REMOVAL_IN_PROGRESS) | no |
| `upgradeTime` | integer(int64) | no |

**`NPAssistant`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `publicKey` | string | no |
| `publicKeyExpiry` | integer(int64) | no |
| `connectorState` | integer(int32) | no |
| `connectorId` | integer(int64) | yes |

**`AppServerGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | yes |
| `dynamicDiscovery` | boolean | no |
| `description` | string | no |
| `configSpace` | string (enum: DEFAULT, SIEM) | yes |
| `weight` | integer(int32) | no |
| `passive` | boolean | no |
| `extranetEnabled` | boolean | no |
| `zpnErId` | ZpnExtranetResource | no |

**`ZpnExtranetResource`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `ziaErId` | integer(int64) | yes |
| `ziaErName` | string | yes |
| `ziaCloud` | string | yes |
| `ziaOrgId` | integer(int64) | yes |
| `ziaModifiedTime` | integer(int64) | no |

**`NPAssistantGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `appConnectorGroupId` | integer(int64) | yes |
| `lanSubnets` | array<NpLanSubnet> | no |

**`NpLanSubnet`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `description` | string | no |
| `subnet` | string | yes |
| `appConnectorGroupId` | integer(int64) | yes |
| `npDnsNsRecord` | NpDnsNsRecord | no |
| `fqdns` | array<string> | no |
| `npserverips` | array<string> | no |
| `oldAuditString` | string | no |

**`NpDnsNsRecord`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `nameserverIps` | array<string> | yes |
| `fqdn` | array<string> | yes |

**`NameIdDto`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `name` | string | no |

**`InconsistentConfigDetails`**

| Property | Type | Required |
| --- | --- | --- |
| `application` | array<InconsistentConfigNameReasonDto> | no |
| `sraApplication` | array<InconsistentConfigNameReasonDto> | no |
| `segmentGroup` | array<InconsistentConfigNameReasonDto> | no |
| `serverGroup` | array<InconsistentConfigNameReasonDto> | no |
| `appConnectorGroup` | array<InconsistentConfigNameReasonDto> | no |
| `baCertificate` | array<InconsistentConfigNameReasonDto> | no |
| `postureProfile` | array<InconsistentConfigNameReasonDto> | no |
| `trustedNetwork` | array<InconsistentConfigNameReasonDto> | no |
| `branchConnectorGroup` | array<InconsistentConfigNameReasonDto> | no |
| `cloudConnectorGroup` | array<InconsistentConfigNameReasonDto> | no |
| `samlAttributes` | array<InconsistentConfigNameReasonDto> | no |
| `scimAttributes` | array<InconsistentConfigNameReasonDto> | no |
| `machineGroup` | array<InconsistentConfigNameReasonDto> | no |
| `idp` | array<InconsistentConfigNameReasonDto> | no |
| `location` | array<InconsistentConfigNameReasonDto> | no |
| `workloadTagGroup` | array<InconsistentConfigNameReasonDto> | no |
| `userPortal` | array<InconsistentConfigNameReasonDto> | no |

**`InconsistentConfigNameReasonDto`**

| Property | Type | Required |
| --- | --- | --- |
| `name` | string | no |
| `reason` | string | no |

**`ExtranetDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | string | no |
| `id` | string | no |
| `ziaErName` | string | no |
| `zpnErId` | integer(int64) | yes |
| `locationGroupDTO` | array<LocationGroupDTO> | yes |
| `locationDTO` | array<NameIdDto> | yes |

**`LocationGroupDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `name` | string | no |
| `id` | integer(int64) | yes |
| `ziaLocations` | array<NameIdDto> | no |

**`ApplicationServer`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `address` | string | no |
| `enabled` | boolean | yes |
| `description` | string | no |
| `appServerGroupIds` | array<string> | no |
| `configSpace` | string (enum: DEFAULT, SIEM) | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/server-management","lastmod":"2026-07-30T17:26Z","nid":"1485241"} -->
## Server Management

- Source: https://help.zscaler.com/legacy-apis/server-management
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Server Management
- Last modified: 2026-07-30T17:26Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/admin/customers/{customerId}/server/summary`

Gets a summary of servers for the specified customer.

- Operation ID: `getAllAppServerNameIds_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. The default page size is 20. The maximum page size is 500. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/server`

Gets all configured servers for the specified customer.

- Operation ID: `getAllAppServers_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The maximum page size is 500. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/server`

Adds a new server for the specified customer.

- Operation ID: `addAppServer_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → ApplicationServer

**Responses:**

| Code | Description |
| --- | --- |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/server/{serverId}`

Gets the server details for the specified ID.

- Operation ID: `getAppServer_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `serverId` | path | yes | integer(int64) | The unique identifier of the server. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/server/{serverId}`

Updates the server details for the specified ID.

- Operation ID: `updateAppServer_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `serverId` | path | yes | integer(int64) | The unique identifier of the server. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → ApplicationServer

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/server/{serverId}`

Deletes the server for the specified ID.

- Operation ID: `deleteAppServer_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `serverId` | path | yes | integer(int64) | The unique identifier of the server. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`PageListNameIdDto`**

| Property | Type | Required |
| --- | --- | --- |
| `currentCount` | integer(int64) | no |
| `list` | array<NameIdDto> | no |
| `message` | string | no |
| `totalCount` | integer(int64) | no |
| `totalPages` | integer(int32) | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`PageListApplicationServer`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<ApplicationServer> | no |

**`ApplicationServer`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `address` | string | no |
| `enabled` | boolean | yes |
| `description` | string | no |
| `appServerGroupIds` | array<string> | no |
| `configSpace` | string (enum: DEFAULT, SIEM) | no |

**`NameIdDto`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `name` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/service-edges","lastmod":"2026-01-06T23:49Z","nid":"1529621"} -->
## Service Edges

- Source: https://help.zscaler.com/legacy-apis/service-edges
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > Service Edges
- Last modified: 2026-01-06T23:49Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /virtualZenNodes`

Retrieves the ZIA Virtual Service Edge for an organization. To learn more, see

[About Virtual Service Edges](https://help.zscaler.com/zia/about-virtual-service-edges)

.

- Operation ID: `VirtualZenNodeResource_getVirtualZenNodes`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `search` | query | no | object | Search for a configured Virtual Service Edge |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /virtualZenNodes`

Adds a ZIA Virtual Service Edge for an organization. To learn more, see

[Adding Virtual Service Edge Instances](https://help.zscaler.com/zia/adding-virtual-service-edge-instances)

.

- Operation ID: `VirtualZenNodeResource_addVirtualZenNode`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | VirtualZenNode | Information about the Virtual Service Edge |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /virtualZenNodes/{virtualZenNodeId}`

Retrieves the ZIA Virtual Service Edge for an organization based on the specified ID

- Operation ID: `VirtualZenNodeResource_getVirtualZenNode`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `virtualZenNodeId` | path | yes | object | The Virtual Service Edge ID |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /virtualZenNodes/{virtualZenNodeId}`

Updates the ZIA Virtual Service Edge for an organization based on the specified ID

- Operation ID: `VirtualZenNodeResource_updateVirtualZenNode`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `virtualZenNodeId` | path | yes | object | The Virtual Service Edge ID |
| `body` | body | no | VirtualZenNode | Information about the Virtual Service Edge |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /virtualZenNodes/{virtualZenNodeId}`

Deletes the ZIA Virtual Service Edge for an organization based on the specified ID

- Operation ID: `VirtualZenNodeResource_deleteVirtualZenNode`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `virtualZenNodeId` | path | yes | object | The Virtual Service Edge ID |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |

### `GET /virtualZenClusters`

Retrieves a list of ZIA Virtual Service Edge clusters. To learn more, see

[About Virtual Service Edge Clusters](https://help.zscaler.com/zia/about-virtual-service-edge-clusters)

.

- Operation ID: `VirtualZenClusterResource_getVirtualZenClusters`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `search` | query | no | object | Search for a configured Virtual Service Edge cluster |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /virtualZenClusters`

Adds a new Virtual Service Edge cluster. To learn more, see

[Adding Virtual Service Edge Clusters](https://help.zscaler.com/zia/adding-virtual-service-edge-clusters)

.

- Operation ID: `VirtualZenClusterResource_addVirtualZenCluster`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | VirtualZenCluster | Information about the Virtual Service Edge cluster |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /virtualZenClusters/{virtualZenClusterId}`

Retrieves the Virtual Service Edge cluster based on the specified ID

- Operation ID: `VirtualZenClusterResource_getVirtualZenCluster`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `virtualZenClusterId` | path | yes | object | The Virtual Service Edge cluster ID |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /virtualZenClusters/{virtualZenClusterId}`

Updates the Virtual Service Edge cluster based on the specified ID

- Operation ID: `VirtualZenClusterResource_updateVirtualZenCluster`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `virtualZenClusterId` | path | yes | object | The Virtual Service Edge cluster ID |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /virtualZenClusters/{virtualZenClusterId}`

Deletes the Virtual Service Edge cluster based on the specified ID

- Operation ID: `VirtualZenClusterResource_deleteVirtualZenCluster`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `virtualZenClusterId` | path | yes | object | The Virtual Service Edge cluster ID |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/shadow-it-report-api","lastmod":"2026-01-06T23:49Z","nid":"1457501"} -->
## Shadow IT Report

- Source: https://help.zscaler.com/legacy-apis/shadow-it-report-api
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > Shadow IT Report
- Last modified: 2026-01-06T23:49Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `PUT /cloudApplications/bulkUpdate`

Updates application status and tag information for predefined or custom cloud applications based on the IDs specified

- Operation ID: `CloudApplicationsResource_bulkUpdateCloudApplication`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | yes | CloudApplication |  |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |

### `GET /cloudApplications/lite`

Gets the list of predefined and custom cloud applications

- Operation ID: `CloudApplicationsResource_getCloudApplicationsLite`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `pageNumber` | query | no | object | Specifies the page number. The numbering starts at 0. |
| `limit` | query | no | object | Specifies the maximum number of cloud applications that must be retrieved in a page |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /customTags`

Gets the list of custom tags available to assign to cloud applications

- Operation ID: `CustomTagResource_getAllCustomTags`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /shadowIT/applications/export`

Export the Shadow IT Report (in CSV format) for the [cloud applications](https://help.zscaler.com/zia/about-cloud-applications) recognized by Zscaler based on their usage in your organization. This report lists cloud applications along with a number of parameters including the sanction status for each application, the risk index assigned for the application, application usage details, application hosting information, and application security-related information, such as SSL certificate validity, data encryption (in transit), security certifications, published CVE, involvement in data breaches, etc.

You can generate this report for specific applications by using the **application** parameter. If no value is specified for this parameter, the report is generated for all cloud applications. You can customize the report by using various filters to show only the cloud applications that match specific parameters. To learn more, see [About Shadow IT Report](https://help.zscaler.com/zia/about-shadow-it-report).

- Operation ID: `ShadowITReportResource_getReportDataApplicationsExport`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | yes | ShadowITApplicationRequest | Information used to generate the Shadow IT Report for the [cloud applications](https://help.zscaler.com/zia/about-cloud-applications) recognized by Zscaler based on their usage in your organization. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |

### `POST /shadowIT/applications/{entity}/exportCsv`

Export the Shadow IT Report (in CSV format) for the list of users or known [locations](https://help.zscaler.com/zia/about-locations) identified with using the [cloud applications](https://help.zscaler.com/zia/about-cloud-applications) specified in the request. This report includes information about each user who has interacted with the application or the known location from where the application is accessed, application category, application usage details, the number of transactions made, last accessed time, etc.

You can customize the report per your requirements by using various filters. To learn more, see [About Application Information](https://help.zscaler.com/zia/about-application-information).

- Operation ID: `ShadowITReportResource_getReportTransactionsExport`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | yes | ShadowITRequest | Information used to generate the Shadow IT Report for the list of users that have interacted with a specific [cloud application](https://help.zscaler.com/zia/about-cloud-applications) or the known [locations](https://help.zscaler.com/zia/about-locations) where the cloud application is accessed. |
| `entity` | path | yes | object | Indicates whether the Shadow IT Report must be generated to retrieve the list of users who have interacted with a cloud application or the known [locations](https://help.zscaler.com/zia/about-locations) where the cloud application is accessed |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/smpc-instance","lastmod":"2026-07-31T07:06Z","nid":"1541215"} -->
## SMPC Instance

- Source: https://help.zscaler.com/legacy-apis/smpc-instance
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > SMPC Instance
- Last modified: 2026-07-31T07:06Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /smpcInstance`

Retrieves the status of all Secure Message Processing Center (SMPC) instances grouped by data center

- Operation ID: `SMPCInstanceResource_getSmpcInstanceStatus`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 | Unauthorized |
| 403 | Forbidden |
| 500 | Internal Server Error |

### `PUT /smpcInstance/dc`

Updates the data center and instance details based on the specified data center ID

- Operation ID: `SMPCInstanceResource_updateDataCenterDetails`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `isEnabled` | query | yes | object | Set this field to **true** to enable DC isolation or **false** to disable it |
| `body` | body | yes | array<integer(int32)> | List of datacenter IDs to update |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 401 | Unauthorized |
| 403 | Forbidden |
| 500 | Internal Server Error |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/ssl-inspection-policy","lastmod":"2026-01-06T23:49Z","nid":"1517221"} -->
## SSL Inspection Policy

- Source: https://help.zscaler.com/legacy-apis/ssl-inspection-policy
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > SSL Inspection Policy
- Last modified: 2026-01-06T23:49Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /sslInspectionRules`

Retrieves all SSL inspection rules

- Operation ID: `SSLInspectionRuleResource_getSSLInspectionRules`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /sslInspectionRules`

Creates a new SSL inspection rule

- Operation ID: `SSLInspectionRuleResource_addSSLInspectionRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | SSLInspectionRule | The SSL inspection rule information |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /sslInspectionRules/{ruleId}`

Retrieves the SSL inspection rule based on the specified ID

- Operation ID: `SSLInspectionRuleResource_getRuleById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | Unique identifier for the SSL inspection rule |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /sslInspectionRules/{ruleId}`

Updates the SSL inspection rule based on the specified ID

- Operation ID: `SSLInspectionRuleResource_updateRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | Unique identifier for the SSL inspection rule |
| `body` | body | no | SSLInspectionRule | The SSL inspection rule information |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /sslInspectionRules/{ruleId}`

Deletes an existing SSL inspection rule based on the specified ID

- Operation ID: `SSLInspectionRuleResource_deleteRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | Unique identifier for the SSL inspection rule |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/system-audit-report","lastmod":"2026-01-06T23:49Z","nid":"1525836"} -->
## System Audit Report

- Source: https://help.zscaler.com/legacy-apis/system-audit-report
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > System Audit Report
- Last modified: 2026-01-06T23:49Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /configAudit`

Retrieves the System Audit Report. To learn more, see [About the System Audit Report](https://help.zscaler.com/zia/about-system-audit-report).

**Note**: This endpoint is accessible via [Zscaler OneAPI](https://help.zscaler.com/oneapi/understanding-oneapi) only.

- Operation ID: `ConfigAuditResource_getConfigReport`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /configAudit/ipVisibility`

Retrieves the IP visibility audit report.

**Note**: This endpoint is accessible via [Zscaler OneAPI](https://help.zscaler.com/oneapi/understanding-oneapi) only.

- Operation ID: `ConfigAuditResource_getIpVisibility`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /configAudit/pacFile`

Retrieves the PAC file audit report.

**Note**: This endpoint is accessible via [Zscaler OneAPI](https://help.zscaler.com/oneapi/understanding-oneapi) only.

- Operation ID: `ConfigAuditResource_getPacFile`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/tag-group-management","lastmod":"2026-08-04T14:12Z","nid":"1542627"} -->
## Tag Group Management

- Source: https://help.zscaler.com/legacy-apis/tag-group-management
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Tag Group Management
- Last modified: 2026-08-04T14:12Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `POST /mgmtconfig/v1/admin/customers/{customerId}/tagGroup/search`

Gets all configured tag groups for the specified customer.

- Operation ID: `getAlltagGroupDto`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → AdvancedFilterAndSortDto

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/tagGroup`

Adds a new tag group for the specified customer.

- Operation ID: `addTagGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → tagGroupDto

**Responses:**

| Code | Description |
| --- | --- |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/tagGroup/{tagGroupId}`

Gets the tag group details for the specified ID.

- Operation ID: `getTagGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `tagGroupId` | path | yes | integer(int64) | The unique identifier of the tag group. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/tagGroup/{tagGroupId}`

Updates the tag group for the specified ID.

- Operation ID: `updatetaggroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `tagGroupId` | path | yes | integer(int64) | The unique identifier of the tag group. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → tagGroupDto

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/tagGroup/{tagGroupId}`

Deletes the tag group for the specified ID.

- Operation ID: `deletetaggroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `tagGroupId` | path | yes | integer(int64) | The unique identifier of the tag group. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`AdvancedFilterAndSortDto`**

| Property | Type | Required |
| --- | --- | --- |
| `filterBy` | FilterGroupDto | no |
| `pageBy` | PaginationDto | no |
| `sortBy` | SortDto | no |

**`PageListTagGroupDto`**

| Property | Type | Required |
| --- | --- | --- |
| `currentCount` | integer(int64) | no |
| `list` | array<tagGroupDto> | no |
| `totalCount` | integer(int64) | no |
| `totalPages` | integer(int32) | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`tagGroupDto`**

| Property | Type | Required |
| --- | --- | --- |
| `createdByUsername` | string | no |
| `creationTime` | integer(int32) | no |
| `description` | string | no |
| `id` | string | no |
| `modifiedBy` | string | no |
| `modifiedByUsername` | string | no |
| `modifiedTime` | integer(int32) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `tags` | array<TagValueSummary> | no |

**`FilterGroupDto`**

| Property | Type | Required |
| --- | --- | --- |
| `filterGroups` | array<NestedFilterGroupDto> | no |
| `operator` | string (enum: AND, OR) | no |

**`NestedFilterGroupDto`**

| Property | Type | Required |
| --- | --- | --- |
| `filters` | array<FilterDto> | no |
| `operator` | string (enum: AND, OR) | no |

**`FilterDto`**

| Property | Type | Required |
| --- | --- | --- |
| `commaSepValues` | string | no |
| `filterName` | string | no |
| `operator` | string | no |
| `value` | string | no |
| `values` | array<string> | no |

**`PaginationDto`**

| Property | Type | Required |
| --- | --- | --- |
| `page` | string | no |
| `pageSize` | string | no |
| `validPageSize` | integer(int32) | no |
| `validPage` | integer(int32) | no |

**`SortDto`**

| Property | Type | Required |
| --- | --- | --- |
| `sortName` | string | no |
| `sortOrder` | string | no |

**`TagValueSummary`**

| Property | Type | Required |
| --- | --- | --- |
| `namespace` | TagNamespaceKeyInfo | no |
| `origin` | string (enum: CUSTOM, PREDEFINED, SEGMENTATION) | no |
| `tagKey` | TagNamespaceKeyInfo | no |
| `tagValue` | TagNameIdDto | no |

**`TagNamespaceKeyInfo`**

| Property | Type | Required |
| --- | --- | --- |
| `enabled` | boolean | no |
| `id` | integer(int64) | no |
| `name` | string | no |

**`TagNameIdDto`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `name` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/tag-key-management","lastmod":"2026-08-04T14:06Z","nid":"1542626"} -->
## Tag Key Management

- Source: https://help.zscaler.com/legacy-apis/tag-key-management
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Tag Key Management
- Last modified: 2026-08-04T14:06Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/namespace/{namespaceId}/tagKey/bulkUpdateStatus`

Enables or disables the tag keys for the specified customer and namespace IDs.

- Operation ID: `bulkUpdateStatus`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |
| `namespaceId` | path | yes | integer(int64) | The unique identifier of the namespace. |

**Request body:** `application/json` → TagKeyBulkUpdateResource

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/namespace/{namespaceId}/tagKey/search`

Gets all configured tag keys for the specified ID.

- Operation ID: `getAllTagKeyDTOs`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `namespaceId` | path | yes | integer(int64) | The unique identifier of the namespace. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → AdvancedFilterAndSortDto

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/namespace/{namespaceId}/tagKey`

Adds a new tag key for a given namespace.

- Operation ID: `addTagKey`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `namespaceId` | path | yes | integer(int64) | The unique identifier of the namespace. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → TagKeyDto

**Responses:**

| Code | Description |
| --- | --- |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/namespace/{namespaceId}/tagKey/{tagKeyId}`

Gets the tag key details for the specified ID.

- Operation ID: `getTagKey`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `namespaceId` | path | yes | integer(int64) | The unique identifier of the namespace. |
| `tagKeyId` | path | yes | integer(int64) | The unique identifier of the tag key. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/namespace/{namespaceId}/tagKey/{tagKeyId}`

Updates the tag key for the specified ID.

- Operation ID: `updateTagKey`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `namespaceId` | path | yes | integer(int64) | The unique identifier of the namespace. |
| `tagKeyId` | path | yes | integer(int64) | The unique identifier of the tag key. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → TagKeyUpdateDto

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/namespace/{namespaceId}/tagKey/{tagKeyId}`

Deletes the tag key for the specified ID.

- Operation ID: `deleteTagKey`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `namespaceId` | path | yes | integer(int64) | The unique identifier of the namespace. |
| `tagKeyId` | path | yes | integer(int64) | The unique identifier of the tag key. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`TagKeyBulkUpdateResource`**

| Property | Type | Required |
| --- | --- | --- |
| `enabled` | boolean | no |
| `tagKeyIds` | array<integer(int64)> | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`AdvancedFilterAndSortDto`**

| Property | Type | Required |
| --- | --- | --- |
| `filterBy` | FilterGroupDto | no |
| `pageBy` | PaginationDto | no |
| `sortBy` | SortDto | no |

**`PageListTagKeyDto`**

| Property | Type | Required |
| --- | --- | --- |
| `currentCount` | integer(int64) | no |
| `list` | array<TagKeyDto> | no |
| `totalCount` | integer(int64) | no |
| `totalPages` | integer(int32) | no |

**`TagKeyDto`**

| Property | Type | Required |
| --- | --- | --- |
| `createdByUsername` | string | no |
| `creationTime` | integer(int32) | no |
| `customerId` | integer(int64) | no |
| `description` | string | no |
| `enabled` | boolean | no |
| `id` | string | no |
| `modifiedBy` | string | no |
| `modifiedByUsername` | string | no |
| `modifiedTime` | integer(int32) | no |
| `name` | string | yes |
| `namespaceId` | integer(int64) | no |
| `origin` | string (enum: CUSTOM, PREDEFINED, SEGMENTATION) | no |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `skipAudit` | boolean | no |
| `tagValues` | array<TagNameIdDto> | no |
| `type` | string (enum: STATIC) | no |

**`TagKeyUpdateDto`**

| Property | Type | Required |
| --- | --- | --- |
| `creationTime` | integer(int32) | no |
| `customerId` | integer(int64) | no |
| `description` | string | no |
| `enabled` | boolean | no |
| `id` | string | no |
| `modifiedBy` | string | no |
| `modifiedTime` | integer(int32) | no |
| `name` | string | yes |
| `namespaceId` | integer(int64) | no |
| `origin` | string (enum: CUSTOM, PREDEFINED, SEGMENTATION) | no |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `tagValuesCreated` | array<TagNameIdDto> | no |
| `tagValuesDeleted` | array<TagNameIdDto> | no |
| `tagValuesUpdated` | array<TagNameIdDto> | no |
| `type` | string (enum: STATIC) | no |

**`FilterGroupDto`**

| Property | Type | Required |
| --- | --- | --- |
| `filterGroups` | array<NestedFilterGroupDto> | no |
| `operator` | string (enum: AND, OR) | no |

**`NestedFilterGroupDto`**

| Property | Type | Required |
| --- | --- | --- |
| `filters` | array<FilterDto> | no |
| `operator` | string (enum: AND, OR) | no |

**`FilterDto`**

| Property | Type | Required |
| --- | --- | --- |
| `commaSepValues` | string | no |
| `filterName` | string | no |
| `operator` | string | no |
| `value` | string | no |
| `values` | array<string> | no |

**`PaginationDto`**

| Property | Type | Required |
| --- | --- | --- |
| `page` | string | no |
| `pageSize` | string | no |
| `validPageSize` | integer(int32) | no |
| `validPage` | integer(int32) | no |

**`SortDto`**

| Property | Type | Required |
| --- | --- | --- |
| `sortName` | string | no |
| `sortOrder` | string | no |

**`TagNameIdDto`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `name` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/tag-namespace-management","lastmod":"2026-08-04T14:03Z","nid":"1542625"} -->
## Tag Namespace Management

- Source: https://help.zscaler.com/legacy-apis/tag-namespace-management
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Tag Namespace Management
- Last modified: 2026-08-04T14:03Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `POST /mgmtconfig/v1/admin/customers/{customerId}/tags/import`

Bulk imports the tag namespace, keys, and values for the specified customer.

- Operation ID: `bulkImportTags`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → BulkImportTagDto

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/namespace/{namespaceId}`

Gets the namespace details for the specified ID.

- Operation ID: `getNamespace`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `namespaceId` | path | yes | integer(int64) | The unique identifier of the namespace. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/namespace/{namespaceId}`

Updates the namespace for the specified ID.

- Operation ID: `updateNamespace`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `namespaceId` | path | yes | integer(int64) | The unique identifier of the namespace. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → TagNamespaceDto

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/namespace/{namespaceId}`

Deletes the namespace for the specified ID.

- Operation ID: `deleteNamespace`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `namespaceId` | path | yes | integer(int64) | The unique identifier of the namespace. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/tags/import/validate`

Validates the bulk import of tags (including namespace, keys, and values) for the specified customer.

- Operation ID: `validateBulkImportTags`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → BulkImportTagDto

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/namespace/search`

Gets all namespaces for the specified customer based on the given filters.

- Operation ID: `getAllNamespaceDtoByPage`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → AdvancedFilterAndSortDto

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/namespace`

Adds a new namespace for the specified customer.

- Operation ID: `addNamespace`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → TagNamespaceDto

**Responses:**

| Code | Description |
| --- | --- |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/namespace/{namespaceId}/status`

Enables or disables a predefined namespace.

- Operation ID: `updatePredefinedNamespaceStatus`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `namespaceId` | path | yes | integer(int64) | The unique identifier of the namespace. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → TagNamespacePredefinedOverrideDto

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`BulkImportTagDto`**

| Property | Type | Required |
| --- | --- | --- |
| `data` | object | no |
| `duplicateResolutionStrategy` | string (enum: MERGE, REPLACE_EXISTING) | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`TagNamespaceDto`**

| Property | Type | Required |
| --- | --- | --- |
| `creationTime` | integer(int32) | no |
| `description` | string | no |
| `enabled` | boolean | no |
| `id` | string | no |
| `modifiedBy` | string | no |
| `modifiedTime` | integer(int32) | no |
| `name` | string | yes |
| `origin` | string (enum: CUSTOM, PREDEFINED, SEGMENTATION) | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `type` | string (enum: STATIC) | no |

**`AdvancedFilterAndSortDto`**

| Property | Type | Required |
| --- | --- | --- |
| `filterBy` | FilterGroupDto | no |
| `pageBy` | PaginationDto | no |
| `sortBy` | SortDto | no |

**`PageListTagNamespaceView`**

| Property | Type | Required |
| --- | --- | --- |
| `currentCount` | integer(int64) | no |
| `list` | array<TagNamespaceView> | no |
| `message` | string | no |
| `totalCount` | integer(int64) | no |
| `totalPages` | integer(int32) | no |

**`TagNamespacePredefinedOverrideDto`**

| Property | Type | Required |
| --- | --- | --- |
| `creationTime` | integer(int32) | no |
| `enabled` | boolean | no |
| `id` | string | no |
| `modifiedBy` | string | no |
| `modifiedTime` | integer(int32) | no |
| `namespaceId` | integer(int64) | no |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |

**`FilterGroupDto`**

| Property | Type | Required |
| --- | --- | --- |
| `filterGroups` | array<NestedFilterGroupDto> | no |
| `operator` | string (enum: AND, OR) | no |

**`NestedFilterGroupDto`**

| Property | Type | Required |
| --- | --- | --- |
| `filters` | array<FilterDto> | no |
| `operator` | string (enum: AND, OR) | no |

**`FilterDto`**

| Property | Type | Required |
| --- | --- | --- |
| `commaSepValues` | string | no |
| `filterName` | string | no |
| `operator` | string | no |
| `value` | string | no |
| `values` | array<string> | no |

**`PaginationDto`**

| Property | Type | Required |
| --- | --- | --- |
| `page` | string | no |
| `pageSize` | string | no |
| `validPageSize` | integer(int32) | no |
| `validPage` | integer(int32) | no |

**`SortDto`**

| Property | Type | Required |
| --- | --- | --- |
| `sortName` | string | no |
| `sortOrder` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/time-intervals","lastmod":"2026-01-06T23:49Z","nid":"1528938"} -->
## Time Intervals

- Source: https://help.zscaler.com/legacy-apis/time-intervals
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > Time Intervals
- Last modified: 2026-01-06T23:49Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /timeIntervals`

Retrieves a list of all configured time intervals. To learn more, see

[About Time Intervals](https://help.zscaler.com/zia/about-time-intervals)

.

- Operation ID: `TimeIntervalResource_getTimeInterval`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `search` | query | no | object | Search for a configured time interval |
| `page` | query | no | object | Set the page number of the response |
| `pageSize` | query | no | object | Set the size of the response |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /timeIntervals`

Adds a new time interval. To learn more, see

[Defining Time Intervals](https://help.zscaler.com/zia/defining-time-intervals)

.

- Operation ID: `TimeIntervalResource_addTimeInterval`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | TimeInterval | Information about the defined time interval |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /timeIntervals/{Id}`

Retrieves the configured time interval based on the specified ID

- Operation ID: `TimeIntervalResource_getTimeIntervalById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `Id` | path | yes | object | Unique identifier for the time interval |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /timeIntervals/{Id}`

Updates the time interval based on the specified ID

- Operation ID: `TimeIntervalResource_updateTimeInterval`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `Id` | path | yes | object | Unique identifier for the time interval |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |

### `DELETE /timeIntervals/{Id}`

Deletes a time interval based on the specified ID

- Operation ID: `TimeIntervalResource_deleteTimeInterval`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `Id` | path | yes | object | Unique identifier for the time interval |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/traffic-capture-policy","lastmod":"2026-01-06T23:49Z","nid":"1533770"} -->
## Traffic Capture Policy

- Source: https://help.zscaler.com/legacy-apis/traffic-capture-policy
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > Traffic Capture Policy
- Last modified: 2026-01-06T23:49Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /trafficCaptureRules`

Retrieves the list of Traffic Capture policy rules configured in the ZIA Admin Portal. To learn more, see

[About Traffic Capture Policy](https://help.zscaler.com/zia/about-traffic-capture-policy)

.

- Operation ID: `TrafficCaptureRulesResource_getPacketCaptureRules`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleName` | query | no | object | Filter based on the rule name |
| `ruleLabel` | query | no | object | Filter based on the rule label |
| `ruleLabelId` | query | no | object | Filter based on the rule label ID |
| `ruleOrder` | query | no | object | Filter based on the rule order |
| `ruleDescription` | query | no | object | Filter based on the rule description |
| `ruleAction` | query | no | object | Filter based on the rule action |
| `location` | query | no | object | Filter based on the location criteria used in the rules |
| `department` | query | no | object | Filter based on the department criteria used in the rules |
| `group` | query | no | object | Filter based on the group criteria used in the rules |
| `user` | query | no | object | Filter based on the user criteria used in the rules |
| `device` | query | no | object | Filter based on the device criteria used in the rules |
| `deviceGroup` | query | no | object | Filter based on the device group criteria used in the rules |
| `deviceTrustLevel` | query | no | object | Filter based on the device trust level criteria used in the rules |
| `page` | query | no | object | Specifies the page offset |
| `pageSize` | query | no | object | Specifies the page size. Default size is 5000. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /trafficCaptureRules`

Creates a new Traffic Capture policy rule. To learn more about rule configuration, see

[Configuring the Traffic Capture Policy](https://help.zscaler.com/zia/configuring-traffic-capture-policy)

.

- Operation ID: `TrafficCaptureRulesResource_createPcapTrafficCaptureRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | TrafficCaptureRule | Specifies the rule details |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /trafficCaptureRules/count`

**Retrieves the rule count for the Traffic Capture policy based on the specified search criteria. If no search criteria are specified, the total number of Traffic Capture policy rules is retrieved by default.**

- Operation ID: `TrafficCaptureRulesResource_getTrafficCaptureRulesCount`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleName` | query | no | object | Filter based on the rule name |
| `ruleLabel` | query | no | object | Filter based on the rule label |
| `ruleLabelId` | query | no | object | Filter based on the rule label ID |
| `ruleOrder` | query | no | object | Filter based on the rule order |
| `ruleDescription` | query | no | object | Filter based on the rule description |
| `ruleAction` | query | no | object | Filter based on the rule action |
| `location` | query | no | object | Filter based on the location criteria used in the rules |
| `department` | query | no | object | Filter based on the department criteria used in the rules |
| `group` | query | no | object | Filter based on the group criteria used in the rules |
| `user` | query | no | object | Filter based on the user criteria used in the rules |
| `device` | query | no | object | Filter based on the device criteria used in the rules |
| `deviceGroup` | query | no | object | Filter based on the device group criteria used in the rules |
| `deviceTrustLevel` | query | no | object | Filter based on the device trust level criteria used in the rules |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /trafficCaptureRules/order`

Retrieves the rule order information for the Traffic Capture policy, including the admin rank and rule order mappings and the maximum configured rule order.

- Operation ID: `TrafficCaptureRulesResource_getRuleOrderInfoForgetTrafficCaptureRules`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /trafficCaptureRules/ruleLabels`

Retrieves the list of rule labels associated with the Traffic Capture policy rules

- Operation ID: `TrafficCaptureRulesResource_getRuleLabelInfo`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `searchByField` | query | no | object | Search option based on specific rule fields |
| `searchByValue` | query | no | object | Search option based on specified values for rule fields |
| `page` | query | no | object | Specifies the page offset |
| `pageSize` | query | no | object | Specifies the page size. Default size is 1024. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /trafficCaptureRules/{ruleId}`

Retrieves the Traffic Capture policy rule based on the specified rule ID

- Operation ID: `TrafficCaptureRulesResource_getTrafficCaptureRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | Specifies the rule ID. This value can be obtained using the `GET /trafficCaptureRules` request. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /trafficCaptureRules/{ruleId}`

Updates information for the Traffic Capture policy rule based on the specified rule ID. To learn more about rule configuration, see

[Configuring the Traffic Capture Policy](https://help.zscaler.com/zia/configuring-traffic-capture-policy)

.

- Operation ID: `TrafficCaptureRulesResource_updateTrafficCaptureRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | Specifies the rule ID. This value can be obtained using the `GET /trafficCaptureRules` request. |
| `body` | body | no | TrafficCaptureRule | Specifies the rule details |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /trafficCaptureRules/{ruleId}`

Deletes the Traffic Capture policy rule based on the specified rule ID

- Operation ID: `TrafficCaptureRulesResource_deleteTrafficCaptureRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | Specifies the rule ID. This value can be obtained using the `GET /trafficCaptureRules` request. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/traffic-forwarding-0","lastmod":"2026-01-06T23:49Z","nid":"1402501"} -->
## Traffic Forwarding

- Source: https://help.zscaler.com/legacy-apis/traffic-forwarding-0
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > Traffic Forwarding
- Last modified: 2026-01-06T23:49Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /datacenters`

Retrieves the list of Zscaler data centers (DCs) that can be excluded from service to your organization

- Operation ID: `DataCenterResource_getDatacenters`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | object | Specifies the page offset |
| `pageSize` | query | no | object | Specifies the page size. The default size is 250. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /dcExclusions`

Retrieves the list of Zscaler data centers (DCs) that are currently excluded from service to your organization based on configured exclusions in the ZIA Admin Portal

- Operation ID: `TenantDCExclusionResource_getDatacenterExclusions`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /dcExclusions`

Adds a data center (DC) exclusion to disable the tunnels terminating at a virtual IP address of a Zscaler DC, triggering a failover from primary to secondary tunnels in the event of service disruptions, [Zscaler Trust Portal](https://trust.zscaler.com) incidents, disasters, etc. You can configure to exclude a specific DC based on the traffic forwarding method for a designated time period.

**Note**: Currently, only the IPSec VPN tunnel forwarding method is supported for DC exclusion. To learn more, see [Excluding a Data Center Based on Traffic Forwarding Method](https://help.zscaler.com/zia/excluding-data-center-based-traffic-forwarding-method).

- Operation ID: `TenantDCExclusionResource_createDatacenterExclusions`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | array<TenantDCExclusion> | Information about the DC exclusion configuration |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /dcExclusions`

Updates a Zscaler data center (DC) exclusion configuration based on the specified ID. To learn more about the DC exclusion configuration, see

[Excluding a Data Center Based on Traffic Forwarding Method](https://help.zscaler.com/zia/excluding-data-center-based-traffic-forwarding-method)

.

- Operation ID: `TenantDCExclusionResource_updateDataCenterExclusions`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | array<TenantDCExclusion> | The unique identifier for the DC exclusion configuration |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /dcExclusions/{dcId}`

Deletes a Zscaler data center (DC) exclusion configuration based on the specified ID. The DC exclusion configuration ID can be obtained by sending a GET request to

`/dcExclusions`

.

- Operation ID: `TenantDCExclusionResource_deleteDataCentreExclusion`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `dcId` | path | yes | object | The unique identifier for the DC exclusion configuration |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |

### `GET /subclouds`

Retrieves all the subclouds and the excluded data centers that are associated with the subcloud

- Operation ID: `TenantSubCloudResource_getAllSubClouds`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | object | Page offset |
| `pageSize` | query | no | object | Page size |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /subclouds/isLastDcInCountry/{id}`

Retrieves the list of all the excluded data centers in a country

- Operation ID: `TenantSubCloudResource_getCountryDCExclusionInfo`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | object | Unique identifier for the country |
| `dcId` | query | no | object | Data center ID |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /subclouds/{id}`

Updates the subcloud and excluded data centers based on the specified ID

- Operation ID: `TenantSubCloudResource_updateTenantSubCloudExclusions`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | object | Unique identifier for the excluded data center |
| `body` | body | yes | TenantSubCloudExclusions | Information about the excluded data center |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operationabout-tenant-profiles |

### `GET /extranet`

Retrieves the list of extranets configured for the organization. Extranets are configured as part of Zscaler Extranet Application Support which allows an organization to connect its internal network with another organization’s network (e.g., partners, third-party vendors, etc.) that does not use the Zscaler service. Extranet Application Support enables Zscaler-managed organization users to securely access extranet resources through an IPSec VPN tunnel established between the Zscaler data center and the external organization’s data center, without requiring additional hardware or software installations.

To learn more, see [Understanding Extranet Application Support](https://help.zscaler.com/zia/understanding-extranet-application-support) and [About Extranet](https://help.zscaler.com/zia/about-extranet).

- Operation ID: `ExtranetResource_getAllExtranet`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `pageSize` | query | no | object | Specifies the page size. The default size is 200. |
| `page` | query | no | object | Specifies the page offset |
| `orderBy` | query | no | object | The field used to sort the list in a specific order |
| `order` | query | no | object | The arrangement of the list in ascending or descending order |
| `search` | query | no | object | The search string used to match against specific extranets |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /extranet`

Adds a new extranet for the organization. Extranets are configured as part of Zscaler Extranet Application Support which allows an organization to connect its internal network with another organization’s network (e.g., partners, third-party vendors, etc.) that does not use the Zscaler service. Extranet Application Support enables Zscaler-managed organization users to securely access extranet resources through an IPSec VPN tunnel established between the Zscaler data center and the external organization’s data center, without requiring additional hardware or software installations.

To learn more, see [Understanding Extranet Application Support](https://help.zscaler.com/zia/understanding-extranet-application-support) and [Configuring an Extranet](https://help.zscaler.com/zia/configuring-extranet).

- Operation ID: `ExtranetResource_addExtranet`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | Extranet | Information about the extranet |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /extranet`

Updates an extranet based on the specified ID

- Operation ID: `ExtranetResource_updateExtranet`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | Extranet | Information about the extranet |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /extranet/lite`

Retrieves the name-ID pairs of all extranets configured for an organization

- Operation ID: `ExtranetResource_getAllExtranetsLite`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /extranet/{id}`

Retrieves information about an extranet based on the specified ID

- Operation ID: `ExtranetResource_getExtranetWithID`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | object | The ID of the extranet that can be obtained by sending a GET request to `/extranet/lite` or `/extranet` |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /extranet/{id}`

Deletes an extranet based on the specified ID

- Operation ID: `ExtranetResource_deleteExtranet`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | object | The ID of the extranet that can be obtained by sending a GET request to `/extranet/lite` or `/extranet` |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |

### `GET /greTunnels`

Gets all provisioned GRE tunnel information.

- Operation ID: `GreTunnelResource_getAllGreTunnels`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | object | Specifies the page offset. |
| `pageSize` | query | no | object | Specifies the page size. The default size is 100, but the maximum size is 1000. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /greTunnels`

Adds a GRE tunnel configuration.

**Note:** Specifying `withinCountryOnly` or `subcloud` parameter is mandatory if this endpoint uses a virtual IP address (VIP) retrieved by passing either of these parameters in `GET /vips/recommendedList`.

- Operation ID: `GreTunnelResource_addGreTunnel`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | yes | GreTunnel |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /greTunnels/availableInternalIpRanges`

Gets the next available GRE tunnel internal IP address ranges.

- Operation ID: `GreTunnelResource_validateAndGetAvailableInternalIpRanges`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `internalIpRange` | query | no | object | Internal IP range information. |
| `staticIp` | query | no | object | Static IP information. |
| `limit` | query | no | object | The maximum number of GRE tunnel IP ranges that can be added. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /greTunnels/{Id}`

Gets the GRE tunnel information for the specified ID.

- Operation ID: `GreTunnelResource_getGreTunelById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `Id` | path | yes | object | The unique identifier for the GRE tunnel. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /greTunnels/{Id}`

Updates the GRE tunnel information for the specified ID.

- Operation ID: `GreTunnelResource_updateGreTunnel`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `Id` | path | yes | object | The unique identifier for the GRE tunnel. |
| `body` | body | yes | GreTunnel | GRE tunnel information. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /greTunnels/{Id}`

Deletes the GRE tunnel information for the specified ID.

- Operation ID: `GreTunnelResource_deleteGreTunnel`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `Id` | path | yes | object | The unique identifier for the GRE tunnel. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | Successful Operation |

### `GET /ipv6config`

Gets the [IPv6 configuration](https://help.zscaler.com/zia/configuring-ipv6-settings) details for the organization. For information about IPv6 support, see [Understanding IPv6 Support](https://help.zscaler.com/zia/understanding-ipv6-support).

- Operation ID: `IPv6ConfigResource_getIPv6Configuration`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /ipv6config/dns64prefix`

Gets the list of NAT64 prefixes configured as the DNS64 prefix for the organization. To learn more, see [About the DNS64 Prefix](https://help.zscaler.com/zia/about-dns64-prefix).

- Operation ID: `IPv6ConfigResource_getDns64Prefixes`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `search` | query | no | object | The search string used to match against a DNS64 prefix’s `name`, `description`, or `prefixMask` attributes. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /ipv6config/nat64prefix`

Gets the list of NAT64 prefixes configured for the organization. The prefix which has the `dnsPrefix` field set to `true` is identified as the DNS64 prefix. To learn more, see [About NAT64 Prefixes](https://help.zscaler.com/zia/about-nat64-prefixes).

- Operation ID: `IPv6ConfigResource_getNat64Prefixes`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `search` | query | no | object | The search string used to match against a NAT64 prefix’s `name`, `description`, or `prefixMask` attributes. |
| `page` | query | no | object | Specifies the page offset. |
| `pageSize` | query | no | object | Specifies the page size. The default size is 100 and the maximum size is 1000. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /orgProvisioning/ipGreTunnelInfo`

Gets a list of IP addresses with GRE tunnel details.

- Operation ID: `getIPGWDetails`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ipAddresses` | query | no | object | Filter based on an IP address range. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /region/byGeoCoordinates`

Retrieves the geographical data of the region or city that is located in the specified latitude and longitude coordinates. The geographical data includes the city name, state, country, geographical ID of the city and state, etc.

- Operation ID: `RegionInfoResource_getRegionByGeoCoordinates`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `latitude` | query | yes | object | The latitude coordinate of the city or region |
| `longitude` | query | yes | object | The longitude coordinate of the city or region |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /region/byIPAddress/{ip}`

Retrieves information about the geo-location of the specified IP address, including the city, state, and country of location, geographical ID of the city and state, postal code, etc.

- Operation ID: `RegionInfoResource_getRegionBySrcIPAddress`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ip` | path | yes | object | The IP address for which you want to retrieve the geo-location and other geographical data. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /region/search`

Retrieves the list of cities (along with their geographical data) that match the prefix search. The geographical data includes the latitude and longitude coordinates of the city, geographical ID of the city and state, country, postal code, etc.

- Operation ID: `RegionInfoResource_getRegionsByPrefix`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `prefix` | query | no | object | The string used in the prefix search of the city or region. The prefix can contain names of city, state, country in the following format: `city name, state name, country name`. A phrase for city name is mandatory, whereas the state and country names can be optionally provided to narrow down the search results. |
| `page` | query | no | object | Specifies the page offset |
| `pageSize` | query | no | object | Specifies the page size. The default size is 100 and the maximum size is 1,000. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /staticIP`

Gets all provisioned static IP addresses.

- Operation ID: `StaticIPResource_getAllStaticIPs`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `availableForGreTunnel` | query | no | object | Set to true to get only the static IP addresses that are not yet associated to a GRE tunnel. |
| `ipAddress` | query | no | object | Filter based on IP address |
| `page` | query | no | object | Specifies the page offset. |
| `pageSize` | query | no | object | Specifies the page size. The default size is 100, but the maximum size is 1000. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /staticIP`

Adds a static IP address.

- Operation ID: `StaticIPResource_addStaticIP`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | yes | StaticIP | Static IP address information. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /staticIP/validate`

Validates the static IP address.

- Operation ID: `StaticIPResource_validateIPAddress`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | yes | StaticIP | The static IP address information. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /staticIP/{Id}`

Gets static IP address for the specified ID

- Operation ID: `StaticIPResource_getStaticIPById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `Id` | path | yes | object | The unique identifier for the provisioned static IP address. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /staticIP/{Id}`

Updates the static IP address for the specified ID.

- Operation ID: `StaticIPResource_updateStaticIP`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `Id` | path | yes | object | The unique identifier for the provisioned static IP address. |
| `body` | body | yes | StaticIP | The static IP address information. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /staticIP/{Id}`

Deletes the static IP address for the specified ID.

- Operation ID: `StaticIPResource_deleteStaticIP`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `Id` | path | yes | object | The unique identifier for the provisioned static IP address. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | Successful Operation |

### `GET /vips`

Gets a paginated list of the virtual IP addresses (VIPs) available in the Zscaler cloud, including region and data center information. By default, the request gets all public VIPs in the cloud, but you can also include private or all VIPs in the request, if necessary.

- Operation ID: `getZscalerNodesDetails`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `dc` | query | no | object | Filter based on data center. |
| `region` | query | no | object | Filter based on region. |
| `page` | query | no | object | Specifies the page offset. |
| `pageSize` | query | no | object | Specifies the page size. The default size is 100, but the maximum size is 1000. |
| `include` | query | no | object | Include all, private, or public VIPs in the list. |
| `subcloud` | query | no | object | Filter based on the subcloud for the VIP. To learn more see, [About Subcloud](https://help.zscaler.com/zia/what-subcloud). |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /vips/groupByDatacenter`

Gets a list of recommended GRE tunnel virtual IP addresses (VIPs), grouped by data center, based on source IP address or latitude/longitude coordinates.

- Operation ID: `CloudVipsResource_getRecommendedVipsGroupedByDC`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `routableIP` | query | no | object | The routable IP address. |
| `withinCountryOnly` | query | no | object | Search within country only. |
| `includePrivateServiceEdge` | query | no | object | Include ZIA Private Service Edge VIPs. |
| `includeCurrentVips` | query | no | object | Include currently assigned VIPs. |
| `sourceIp` | query | no | object | The source IP address. |
| `latitude` | query | no | object | The latitude coordinate of the GRE tunnel source. |
| `longitude` | query | no | object | The longitude coordinate of the GRE tunnel source. |
| `subcloud` | query | no | object | The subcloud for the VIP. To learn more see, [About Subcloud](https://help.zscaler.com/zia/what-subcloud). |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /vips/recommendedList`

Gets a list of recommended GRE tunnel virtual IP addresses (VIPs), based on source IP address or latitude/longitude coordinates.

- Operation ID: `CloudVipsResource_getRecommendedGreVips`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `routableIP` | query | no | object | The routable IP address. |
| `withinCountryOnly` | query | no | object | Search within country only. |
| `includePrivateServiceEdge` | query | no | object | Include ZIA Private Service Edge VIPs. |
| `includeCurrentVips` | query | no | object | Include currently assigned VIPs. |
| `sourceIp` | query | no | object | The source IP address. |
| `latitude` | query | no | object | The latitude coordinate of the GRE tunnel source. |
| `longitude` | query | no | object | The longitude coordinate of the GRE tunnel source. |
| `subcloud` | query | no | object | The subcloud for the VIP. To learn more see, [About Subcloud](https://help.zscaler.com/zia/what-subcloud). |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /vpnCredentials`

Gets VPN credentials that can be associated to locations. To learn more, see [About VPN Credentials](https://help.zscaler.com/zia/about-vpn-credentials).

- Operation ID: `getVpnCredentials`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `search` | query | no | object | The search string used to match against a VPN credential's commonName, fqdn, ipAddress, comments, or locationName attributes. |
| `type` | query | no | object | Only gets VPN credentials for the specified type. This parameter is not supported for partner API keys. |
| `includeOnlyWithoutLocation` | query | no | object | Include VPN credential only if not associated to any location. |
| `locationId` | query | no | object | Gets the VPN credentials for the specified location ID. |
| `managedBy` | query | no | object | Gets the VPN credentials that are managed by the given partner. This filter is automatically applied when called with a partner API key, and it cannot be overridden. |
| `page` | query | no | object | Specifies the page offset. |
| `pageSize` | query | no | object | Specifies the page size. The default size is 100, but the maximum size is 1000. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /vpnCredentials`

Adds VPN credentials that can be associated to locations. When invoked with a partner API key, it automatically sets the managedBy attribute to the partner associated with the key. To learn more, see [Adding VPN Credentials](https://help.zscaler.com/zia/adding-vpn-credentials).

- Operation ID: `addVpnCredential`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `vpnCred` | body | no | VPNCredential | VPN credential information. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /vpnCredentials/bulkDelete`

Bulk delete VPN credentials up to a maximum of 100 credentials per request. The response returns the VPN IDs that were successfully deleted.

- Operation ID: `bulkDeleteVpnCredentials`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ids` | body | no | IdListInteger | The VPN IDs to bulk delete |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | Successful Operation |
| 404 | If the payload includes VPN IDs that are invalid a ['Resource does not exist' error](https://help.zscaler.com/zia/about-error-handling) is returned, but information about the invalid IDs is not included. |

### `GET /vpnCredentials/{vpnId}`

Gets the VPN credentials for the specified ID.

- Operation ID: `getVpnCredential`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `vpnId` | path | yes | object | The unique identifier for the VPN credential. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /vpnCredentials/{vpnId}`

Updates the VPN credentials for the specified ID.

- Operation ID: `updateVpnCredential`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `vpnId` | path | yes | object | The unique identifier for the VPN credential. |
| `vpnCred` | body | no | VPNCredential | VPN credential information. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /vpnCredentials/{vpnId}`

Deletes the VPN credentials for the specified ID.

- Operation ID: `deleteVpnCredential`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `vpnId` | path | yes | object | The unique identifier for the VPN credential. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | Successful Operation. No Content Returned. |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/troubleshooting","lastmod":"2026-01-06T23:49Z","nid":"1436291"} -->
## Troubleshooting

- Source: https://help.zscaler.com/legacy-apis/troubleshooting
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZDX API > API Developer & Reference Guide > Reference Guide > Troubleshooting
- Last modified: 2026-01-06T23:49Z

APIs for Zscaler Digital Experience (ZDX).

**Servers:** `https://api.zdxcloud.net/v1`, `https://dev.zdxapi.zscaler.net/v1`, `http://localhost:8080/v1`

### `GET /devices/{deviceid}/deeptraces`

Get a list of deep traces. The response contains an ID to identify the operation and can be used in subsequent endpoints that require access to data.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Lists the deep traces with their status. |

### `POST /devices/{deviceid}/deeptraces`

Start a deep trace on a device. Limited to 1 active session on a device at any time. There is a maximum of 15 active sessions at any point of time across all devices (includes sessions initiated from UI).

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |

**Request body:** `application/json` → object

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Deep trace request accepted. |
| 401 |  |
| 403 |  |

### `GET /devices/{deviceid}/deeptraces/{trace_id}`

Get the deep trace's status.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `trace_id` | path | yes | string | trace id |

**Responses:**

| Code | Description |
| --- | --- |
| 200 |  |
| 401 |  |
| 403 |  |

### `DELETE /devices/{deviceid}/deeptraces/{trace_id}`

Stop the deep trace session that is currently running.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `trace_id` | path | yes | string | trace id |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successfully deleted the deep trace request. |
| 401 |  |
| 403 |  |

### `GET /devices/{deviceid}/deeptraces/{trace_id}/webprobe-metrics`

Get the Web probe metrics from the deep trace session.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `trace_id` | path | yes | string | trace id |

**Responses:**

| Code | Description |
| --- | --- |
| 200 |  |
| 401 |  |
| 403 |  |

### `GET /devices/{deviceid}/deeptraces/{trace_id}/cloudpath-metrics`

Get the Cloud Path metrics (e.g., latency, packet drops, etc.) from the deep trace session.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `trace_id` | path | yes | string | The deep trace ID. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 |  |
| 401 |  |
| 403 |  |

### `GET /devices/{deviceid}/deeptraces/{trace_id}/cloudpath`

Get a list of Cloud Paths from the deep trace session.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `trace_id` | path | yes | string | The deep trace ID. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 |  |
| 401 |  |
| 403 |  |

### `GET /devices/{deviceid}/deeptraces/{trace_id}/health-metrics`

Get the device health metrics from a deep trace session.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `trace_id` | path | yes | string | trace id |

**Responses:**

| Code | Description |
| --- | --- |
| 200 |  |
| 401 |  |
| 403 |  |

### `GET /devices/{deviceid}/deeptraces/{trace_id}/events`

Get the events metrics trend for a device. The event metrics include Zscaler, Hardware, Software and Network event changes.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `trace_id` | path | yes | string | trace id |

**Responses:**

| Code | Description |
| --- | --- |
| 200 |  |
| 401 |  |
| 403 |  |

### `GET /devices/{deviceid}/deeptraces/{trace_id}/top-processes`

Get the top processes from the deep tracing session.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `` |  | no | object |  |
| `trace_id` | path | yes | string | trace id |

**Responses:**

| Code | Description |
| --- | --- |
| 200 |  |
| 401 |  |
| 403 |  |

### `POST /analysis`

Start a ZDX Score analysis on a device for a specific application. The response contains an ID to identify the operation and can be used in subsequent endpoints that require it.

**Request body:** `application/json` → AnalysisRequest

**Responses:**

| Code | Description |
| --- | --- |
| 200 |  |
| 401 |  |
| 403 |  |

### `GET /analysis/{analysis_id}`

Get the status of the score analysis (e.g., progress or results).

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `analysis_id` | path | yes | string | The analysis ID. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 |  |
| 401 |  |
| 403 |  |

### `DELETE /analysis/{analysis_id}`

Stop the score analysis that is currently running.

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `analysis_id` | path | yes | string | The analysis ID. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 |  |
| 401 |  |
| 403 |  |

### Schemas

**`DeepTraceSession`**

| Property | Type | Required |
| --- | --- | --- |
| `trace_id` | integer | no |
| `trace_details` | object | no |
| `status` | string (enum: not_started, in_progress, error, done) | no |
| `created_at` | integer | no |
| `started_at` | integer | no |
| `ended_at` | integer | no |

**`Series`**

| Property | Type | Required |
| --- | --- | --- |
| `metric` | string | no |
| `unit` | string | no |
| `datapoints` | array<object> | no |

- `NetworkStats`: array<object>

- `Cloudpath`: array<CloudPathLeg>

- `DeviceEvents`: array<object>

- `ImpactedProcessSeries`: array<object>

**`AnalysisRequest`**

| Property | Type | Required |
| --- | --- | --- |
| `device_id` | integer | no |
| `app_id` | integer | no |
| `t0` | integer | no |
| `t1` | integer | no |

- `AnalysisResult`: array<object>

**`CloudPathLeg`**

| Property | Type | Required |
| --- | --- | --- |
| `src` | string | no |
| `dst` | string | no |
| `num_hops` | integer | no |
| `latency` | integer | no |
| `loss` | integer | no |
| `num_unresp_hops` | integer | no |
| `tunnel_type` | integer | no |
| `hops` | array<object> | no |

**`Event`**

| Property | Type | Required |
| --- | --- | --- |
| `category` | string | no |
| `name` | string | no |
| `display_name` | string | no |
| `prev` | string | no |
| `curr` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/trusted-networks","lastmod":"2026-01-06T23:49Z","nid":"1485341"} -->
## Trusted Networks

- Source: https://help.zscaler.com/legacy-apis/trusted-networks
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Trusted Networks
- Last modified: 2026-01-06T23:49Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/admin/customers/{customerId}/network/{id}`

**Gets the trusted networks for the specified ID.**

- Operation ID: `getTrustedNetwork_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `id` | path | yes | integer(int64) | The unique identifier of the trusted network. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v2/admin/customers/{customerId}/network`

**Gets all trusted networks for the specified customer.**

- Operation ID: `getAllTrustedNetworks`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`TrustedNetwork`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `networkId` | string | no |
| `zscalerCloud` | string | no |
| `domain` | string | no |
| `masterCustomerId` | string | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`PageListTrustedNetwork`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<TrustedNetwork> | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/understanding-api-rate-limiting-workflow-automation-api","lastmod":"2026-01-06T23:49Z","nid":"1452116"} -->
## Understanding API Rate Limiting

- Source: https://help.zscaler.com/legacy-apis/understanding-api-rate-limiting-workflow-automation-api
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > Workflow Automation API > API Developer & Reference Guide > Understanding API Rate Limiting
- Last modified: 2026-01-06T23:49Z
- Summary: Information on rate limiting within Workflow Automation API.

Rate limits throttle the number of API calls you can make. Every endpoint has a weight, and every weight has a default rate limit, but some endpoints have rate limits that differ from the default. To learn more, see the [API Rate Limit Summary](https://help.zscaler.com/zia/api-rate-limit-summary-workflow-automation-api).

The following table provides the typical assignment and values for each weight. However, specific operations can have a different weight from these typical values.

| Weight | Typical Assignment | Req/min | Req/hr |
| --- | --- | --- | --- |
| Medium | POST | 20 | 400 |
| Light | GET, POST, DELETE | 30 | 1000 |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/understanding-authentication-service-apis","lastmod":"2026-08-23T07:06Z","nid":"1529189"} -->
## Understanding Authentication Service APIs

- Source: https://help.zscaler.com/legacy-apis/understanding-authentication-service-apis
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > Authentication Service API > Understanding Authentication Service APIs
- Last modified: 2026-08-23T07:06Z
- Summary: Introductory information about the Authentication Service API, which gives programmatic access to various Authentication Service features.

Authentication Service APIs give you programmatic access for managing identity and authentication-related features. The APIs allow you to integrate with Authentication Service for seamless identity lifecycle management and API client management.

The Authentication Service API gives you programmatic access to manage the following Authentication Service features:

- API Clients
- Users
- Groups
- Resource Servers

Zscaler can make periodic updates to the query and response parameters used by the Authentication Service API. Ensure you reference the latest API documentation to stay aligned with current configurations.

<p> Prior to using the API, Zscaler recommends reviewing <a href="/zidentity/getting-started-zidentity-api" target="_blank" data-entity-type="node" data-entity-uuid="fcdbdaa9-5856-48f4-a252-3edb971f4141" data-entity-substitution="canonical">Getting Started</a> for information regarding prerequisites, authentication, and making API calls. </p>

For detailed information on all available API calls, endpoints, and parameters, see [Working With APIs](https://help.zscaler.com/zidentity/zidentity-api/working-apis). To learn more about HTTP status codes, see [API Response Codes and Error Messages](https://help.zscaler.com/zidentity/api-response-codes-and-error-messages).

If you encounter any issues with the Authentication Service API, contact [Zscaler Support](https://help.zscaler.com/contact-support).

An API client refers to any application or service that wants to access the Zscaler API resources and retrieve data. To learn more, see:

- [Working With APIs > API Clients](https://help.zscaler.com/zidentity/api-clients)
- [About API Clients](https://help.zscaler.com/zidentity/about-api-clients)

You can add users to Authentication Service. To learn more, see:

- [Working With APIs > Users](https://help.zscaler.com/zidentity/users)
- [About Users](https://help.zscaler.com/zidentity/about-users)

A group is a logical entity that includes several users. Creating user groups helps in assigning multiple users to the Zscaler services quickly. To learn more, see:

- [Working With APIs > Groups](https://help.zscaler.com/zidentity/groups)
- [About Groups](https://help.zscaler.com/zidentity/about-user-groups)

Resource servers or API resources refer to the Zscaler APIs available via the OneAPI gateway. To learn more, see:

- [Working With APIs > API Resources](https://help.zscaler.com/zidentity/resource-servers)
- [Viewing API Resources](https://help.zscaler.com/zidentity/viewing-api-resources)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/understanding-easm-api","lastmod":"2026-03-05T22:39Z","nid":"1532904"} -->
## Understanding the EASM API

- Source: https://help.zscaler.com/legacy-apis/understanding-easm-api
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > EASM API > Understanding the EASM API
- Last modified: 2026-03-05T22:39Z
- Summary: Introductory information about the EASM API which gives programmatic access to various EASM features.

To enable the EASM API for your organization, contact the Zscaler Account team.

The EASM API gives you programmatic access to manage the following EASM features:

- Organizations
- Assets
- Findings
- Lookalike Domains

Zscaler provides secured access to the EASM API via [Zscaler OneAPI](https://automate.zscaler.com/docs/getting-started/getting-started), a unified programming interface for all Zscaler services specialized for platform automation. OneAPI uses a common endpoint that encompasses all Zscaler resources and secures API authorization with OAuth 2.0. API client identity registration and management are combined in Zscaler's unified identity service, [Authentication Service](https://help.zscaler.com/zidentity/what-zidentity).

Prior to using the API, Zscaler recommends reviewing [Getting Started](https://automate.zscaler.com/docs/getting-started/getting-started) with OneAPI for information regarding prerequisites, authentication, and making API calls. For detailed information on all available API calls, endpoints, and parameters, see the [Reference Guide](https://help.zscaler.com/easm/easm-api/api-developer-reference-guide/reference-guide). Zscaler can make periodic updates to the query and response parameters that the EASM API uses.

All endpoints within the EASM API are subject to a rate limit of 1,000 calls per second, per tenant. To learn more about HTTP error and status codes, see [API Response Codes and Error Messages](https://help.zscaler.com/easm/api-response-codes-and-error-messages). If you encounter any issues with the EASM API, contact Zscaler Support.

Organizations API resources allow you to retrieve all organizations configured for a tenant in the EASM Admin Portal. To learn more, see:

- [Reference Guide > Organizations](https://help.zscaler.com/easm/organizations)
- [Creating and Managing Organizations](https://help.zscaler.com/easm/creating-managing-organizations)

Assets API resources allow you to retrieve the assets that EASM discovers for an organization. In addition to the asset details, you can use separate endpoint resources to retrieve the SSL/TLS certificates that the assets use, services running on them, and technologies used on them. To learn more, see:

- [Reference Guide > Assets](https://help.zscaler.com/easm/assets)
- [About Asset Inventory](https://help.zscaler.com/easm/about-asset-inventory)
- [Understanding Asset Details](https://help.zscaler.com/easm/understanding-asset-details)

Findings API resources allow you to retrieve the findings identified for an organization's internet-facing assets after the initial scanning and discovery processes. The assets are investigated for various risk parameters, such as undetected vulnerabilities, misconfigurations, and compliance violations, and these detections are populated as findings in the EASM Admin Portal. Using these API resources, you can retrieve the list of findings, detailed information about individual findings, and evidence details and scan output for each finding. To learn more, see:

- [Reference Guide > Findings](https://help.zscaler.com/easm/findings)
- [About Findings](https://help.zscaler.com/easm/about-findings)
- [Understanding Finding Details](https://help.zscaler.com/easm/understanding-finding-details)

Lookalike Domains API resources allow you to retrieve fraudulent or fake domains that threat actors intentionally create to mimic the legitimate domains associated with your organization and deceive users for malicious activities. To learn more, see:

- [Reference Guide > Lookalike Domains](https://help.zscaler.com/easm/lookalike-domains)
- [About Lookalike Domains](https://help.zscaler.com/easm/about-lookalike-domains)
- [Understanding Lookalike Domain Details](https://help.zscaler.com/easm/understanding-lookalike-domain-details)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/understanding-error-codes","lastmod":"2026-01-06T23:49Z","nid":"1402756"} -->
## Understanding Error Codes

- Source: https://help.zscaler.com/legacy-apis/understanding-error-codes
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZDX API > API Developer & Reference Guide > Understanding Error Codes
- Last modified: 2026-01-06T23:49Z
- Summary: To provide information on HTTP status codes for ZDX API.

The table lists the HTTP status codes returned by the API.

| Code | Status | Description |
| --- | --- | --- |
| 200 | OK | The request was successful. |
| 201 | Created | The request was successful and a new resource has been created. |
| 202 | Accepted | The request has been received, but is not completed yet. |
| 203 | Non-authoritative Information | The returned information in the entity-header is not authorized, but is from a local or a third-party copy. The authorization set may be a subset or superset of the original version. |
| 204 | No Content | Content is not available. |
| 400 | Bad Request | The request could not be completed due to incorrect syntax. Repeat the request with modifications to the syntax. |
| 401 | Unauthorized | The session is not authenticated or the token duration has expired. |
| 402 | Payment Required (Experimental) | Reserved for future use. It is aimed for use in digital payment systems. |
| 403 | Forbidden | One of the following permission errors occurred:  The API key was disabled by your service provider.; User's role has no access permissions or functional scope.; A required SKU subscription is missing. Contact Zscaler Support or your Zscaler Account team for assistance. |
| 404 | Not Found | The server cannot find the requested resource. |
| 405 | Method Not Allowed | The request HTTP method is known by the server, but has been disabled and cannot be used for that resource. |
| 406 | Not Acceptable | The content does not meet the given criteria in the Accept header of the sent request. |
| 407 | Proxy Authentication Required | The client must first authenticate with the proxy. |
| 408 | Request Timeout | The server did not receive a complete request from the user within the server’s allotted timeout period. |
| 409 | Conflict | The request could not be completed due to a conflict with the current state of the resource. |
| 410 | Gone | The requested resource is no longer available on the server. |
| 411 | Length Required | The server refuses to accept the request without a defined Content- Length. Repeat the request by adding a valid Content-Length header field. |
| 412 | Precondition Failed | The user has indicated preconditions in its headers that the server does not accept. |
| 413 | Request Entity Too Large | The request entity is larger than the limits defined by the server. |
| 414 | Request-URI Too Long | The URI requested by the client is longer than the server can interpret. |
| 415 | Unsupported Media Type | The media type in Content-type of the request is not supported by the server. |
| 416 | Requested Range Not Satisfiable | The range specified by the Range header field in the request cannot be fulfilled. |
| 417 | Expectation Failed | The expectation indicated by the Expect request header field cannot be met by the server. |
| 429 | Too Many Requests | The user has sent too many requests in a given amount of time and exceeded the rate limit or device threshold. To learn more, see [Understanding Rate Limiting](https://help.zscaler.com/zdx/understanding-rate-limiting). |
| 431 | Request Header Fields Too Large | The server is unable to process the request because the header fields are too large. |
| 500 | Internal Server Error | The server encountered an unexpected condition which prevented the fulfillment of the request. |
| 501 | Not Implemented | The HTTP method is not supported by the server and cannot be handled. |
| 502 | Bad Gateway | The server received an invalid response while working as a gateway to handle the request. |
| 503 | Service Unavailable | The server is not ready to handle the request. |
| 504 | Gateway Timeout | The server is acting as a gateway and cannot get a response in time for a request. |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/understanding-rate-limiting","lastmod":"2026-01-06T23:49Z","nid":"1400406"} -->
## Understanding Rate Limiting

- Source: https://help.zscaler.com/legacy-apis/understanding-rate-limiting
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Understanding Rate Limiting
- Last modified: 2026-01-06T23:49Z
- Summary: Information on rate limiting within Zscaler's cloud service API.

Every endpoint and action has two of rate limit types:

- A lower bound limit that protects against high bursts of requests over a short period of time
- An upper bound limit that protects against a high volume of requests over a long period of time

These limits throttle the number of API calls you can make. Every endpoint has a weight, and every weight has a default rate limit, but some endpoints have exceptions. To learn more, see the [API Rate Limit Summary](https://help.zscaler.com/zia/api-rate-limit-summary).

The following table provides the typical assignment for each weight. However, specific operations can have a different weight from these typical values.

| Weight | Typical Assignment | Req/sec | Req/min | Req/hr |
| --- | --- | --- | --- | --- |
| Heavy | DELETE | - | 1 | 4 |
| Medium | POST, PUT | 1 | - | 400 |
| Light | GET | 2 | - | 1000 |

As a best practice, after each call to an endpoint, your script should include a wait (or sleep) period. For example in Python, you would use the `time.sleep()` function. When the rate limit is exceeded, a [429 HTTP error message](https://help.zscaler.com/zia/api-response-codes-and-error-messages) is returned with a `Retry-After` response in the Body. For example:

```
{
   "message": "Rate Limit (1/SECOND) exceeded",
   "Retry-After": "0 seconds"
}
```
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/understanding-rate-limiting-1","lastmod":"2026-01-06T23:49Z","nid":"1484866"} -->
## Understanding Rate Limiting

- Source: https://help.zscaler.com/legacy-apis/understanding-rate-limiting-1
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Understanding Rate Limiting
- Last modified: 2026-01-06T23:49Z
- Summary: Information about rate limiting applicable to the Zscaler Private Access (ZPA)cloud service API.

Rate limits throttle the number of API calls you can make for any basic CRUD operation.

The system will rate limit if an endpoint is called from a given IP more than:

- 20 times in a 10-second interval for a `GET` call.
- 10 times in a 10-second interval for any `POST/PUT/DELETE` call.

All rate limits start as soon as the first call is executed. Calls can occur more than once per second, but no more than the limits for each operation type.

When an API request is rate limited, error code 429 is returned along with a response header. The response header includes the `retry-after` field, which is a retry mechanism that indicates the time required to wait before another call can be made. The following example is a response header that includes the `retry-after` field. The value `13s` indicates 13 seconds:

```
{
  "content-type": "application/json",
  "date": "Wed, 6 Mar 2024 11:38 GMT",
  "retry-after": "13s"
}
```

To learn more, see [API Response Codes and Error Messages](https://help.zscaler.com/zpa/api-response-codes-and-error-messages).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/understanding-rate-limiting-2","lastmod":"2026-01-06T23:49Z","nid":"1397331"} -->
## Understanding Rate Limiting

- Source: https://help.zscaler.com/legacy-apis/understanding-rate-limiting-2
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZDX API > API Developer & Reference Guide > Understanding Rate Limiting
- Last modified: 2026-01-06T23:49Z
- Summary: To provide information on rate limitations based on tier levels within ZDX's API.

Rate limits throttle the number of API calls you can make for any basic CRUD operation for scalability purposes.

Based on your tier level, your system rate limits if an endpoint is called from a given IP when one of the following occurs:

| **Tier Level** | **Number of Licenses** | **API Calls/Second** | **API Calls/Minute** | **API Calls/Hour** | **API Calls/Day** |
| --- | --- | --- | --- | --- | --- |
| 1 | 5,000 | 5 | 30 | 1,000 | 10,000 |
| 2 | 20,000 | 5 | 60 | 3,000 | 15,000 |
| 3 | 100,000 | 5 | 120 | 6,000 | 30,000 |
| 4 | More than 100,000 | 5 | 180 | 9,000 | 60,000 |

All rate limits start as soon as the first call is executed. Calls may occur more than once per second, but no more than the limits for each operation type.

Clients who are subject to rate limiting need to back off exponentially in order to proceed further.

## Rate Limit Headers

The Rate Limit headers provide information about the current status of rate limits. This allows users to plan ahead for their API integration to prevent hitting the rate limit and receiving the 429 error code.

The Rate Limit headers are:

- RateLimit-Limit: The rate limit ceiling that is applicable for the current API request.
- RateLimit-Remaining: The number of API requests remaining for the current rate-limit window.
- RateLimit-Reset: The time at which the rate limit resets, specified in UTC epoch time (in seconds).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/understanding-rate-limiting-3","lastmod":"2026-01-15T20:05Z","nid":"1417131"} -->
## Understanding Rate Limiting

- Source: https://help.zscaler.com/legacy-apis/understanding-rate-limiting-3
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > Zscaler Client Connector API > API Developer & Reference Guide > Understanding Rate Limiting
- Last modified: 2026-01-15T20:05Z
- Summary: Information on rate limitations within Zscaler Client Connector's API.

Rate limits throttle the number of API calls you can make for any essential CRUD operation.

For an organization, the system rate limits all endpoints called from a given IP address to *100 API calls per hour*except for the `/downloadDevices`, `/downloadServiceStatus`, and `/downloadDisableReasons` endpoints, for which the rate limit is *3 API calls per day.*

All rate limits start as soon as the first call is executed. Calls can occur more than once per second, but no more than the limits for each operation type.

Clients subject to rate limits must back off exponentially to proceed further.

## Rate Limit Headers

Rate Limit headers in the response provide information about the current status of API rate limits. This helps users plan ahead for their API integrations to avoid reaching the rate limit and receiving the `429` (Too Many Requests) error code. The system sets the following headers when rate limits are enforced:

- `X-Rate-Limit-Remaining`: Indicates the number of API requests remaining within the current rate limit window.
- `X-Rate-Limit-Retry-After-Seconds`: Specifies the number of seconds the client must wait before making another API call if the rate limit has exceeded.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/understanding-rate-limits","lastmod":"2026-08-21T07:06Z","nid":"1447231"} -->
## Understanding Rate Limits

- Source: https://help.zscaler.com/legacy-apis/understanding-rate-limits
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > Zscaler Cloud & Branch Connector API > API Developer & Reference Guide > Understanding Rate Limits
- Last modified: 2026-08-21T07:06Z
- Summary: Information on rate limits for Zscaler Cloud & Branch Connector API

Rate limits throttle the number of API calls you can make for POST, GET, PUT, and DELETE operations. Every endpoint and operation has two rate limit types:

- A lower bound limit that protects against a high volume of requests over a short period of time
- An upper bound limit that protects against a high volume of requests over a long period of time

These limits throttle the number of API calls you can make. Every endpoint has a weight, and every weight has a default rate limit.

The following table provides the typical assignment for each weight. However, specific operations can have a different weight from these typical values.

| Weight | Typical Assignment | Requests/sec | Requests/min | Requests/hr |
| --- | --- | --- | --- | --- |
| Heavy | DELETE | - | 1 | 40 |
| Medium | POST, PUT | 1 | - | 400 |
| Light | GET | 2 | - | 1000 |

As a best practice, after each call to an endpoint, your script should include a wait (or sleep) period. For example in Python, you would use the `time.sleep()` function. When the rate limit is exceeded, a [429 HTTP error message](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages) is returned with a `Retry-After` response in the Body. For example:

```
{
   "message": "Rate Limit (1/SECOND) exceeded",
   "Retry-After": "0 seconds"
}
```
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/understanding-workflow-automation-api","lastmod":"2026-08-14T08:52Z","nid":"1452066"} -->
## Understanding Workflow Automation API

- Source: https://help.zscaler.com/legacy-apis/understanding-workflow-automation-api
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > Workflow Automation API > Understanding Workflow Automation API
- Last modified: 2026-08-14T08:52Z
- Summary: Information about the Workflow Automation API management.

The Workflow Automation API gives you programmatic access to manage DLP incidents created in your organization. DLP incidents are transactions that have violated the Data Protection (DLP and SaaS API) policies configured by your organization in Zscaler Internet Access (ZIA). The admin to whom a DLP incident is assigned can take various actions to resolve the incident using Workflow Automation API. To learn more, see:

- API Authentication
- Audit Logs
- CSV User Attributes
- DLP Incidents
- Email Quarantine

Email Quarantine API resources allow you to release the quarantined email to one or more of its recipients. It also provides the quarantined email release status for the recipients. To learn more, see:

- [Reference Guide > Email Quarantine](https://help.zscaler.com/legacy-apis/email-quarantine#/dlp/v1/incidents/%7BdlpIncidentId%7D/quarantine/release-post)
- [Viewing & Managing Incident Details](https://help.zscaler.com/workflow-automation/viewing-managing-incident-details)

Audit logs API resources allow you to filter records of the actions that every admin performs in Workflow Automation. To learn more, see:

- [Reference Guide > Audit Logs](https://help.zscaler.com/legacy-apis/audit-logs)
- [About Audit Logs](https://help.zscaler.com/workflow-automation/about-audit-logs)

CSV user attributes API resources allow you to import user attributes, such as the employee ID, email address, and manager's name, as a .csv file into Workflow Automation. To learn more, see:

- [Reference Guide > CSV User Attributes](https://help.zscaler.com/legacy-apis/csv-user-attributes)
- [Managing User Attributes](https://help.zscaler.com/workflow-automation/managing-user-attributes)

Authentication API resources allow you to authenticate and create an API session, check for an existing API session, etc. To learn more, see:

- [Reference Guide > API Authentication](https://help.zscaler.com/legacy-apis/api-authentication-workflow-automation-api)
- [Getting Started](https://help.zscaler.com/zia/getting-started-workflow-automation-api)

Workflow Automation API allows you to retrieve information about transactions that have violated the Data Protection policies of your organization. You can also find a DLP incident using the incident ID or transaction ID, get the actual data that triggered the incident, close the incident, etc. To learn more, see:

- [Reference Guide > DLP Incidents](https://help.zscaler.com/legacy-apis/dlp-incidents-workflow-automation-api)
- [About Incidents](https://help.zscaler.com/zia/about-incidents)
- [Viewing & Managing Incident Details](https://help.zscaler.com/zia/viewing-managing-incident-details)

To learn more about rate limits and HTTP status codes, see [Understanding API Rate Limiting](https://help.zscaler.com/zia/understanding-api-rate-limiting-workflow-automation-api) and [API Response Codes and Error Messages](https://help.zscaler.com/zia/api-response-codes-error-messages-workflow-automation-api). If you encounter any issues with the Workflow Automation API, contact Zscaler Support.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/understanding-zdx-api","lastmod":"2026-01-06T23:49Z","nid":"1397191"} -->
## Understanding the ZDX API

- Source: https://help.zscaler.com/legacy-apis/understanding-zdx-api
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZDX API > Understanding the ZDX API
- Last modified: 2026-01-06T23:49Z
- Summary: Introductory information about Digital Experience (ZDX) API, which gives programmatic access to various ZDX features.

The ZDX API gives you programmatic access to ZDX features:

- Authentication
- Administration
- Alerts
- Inventory
- Reports
- Troubleshooting

If you are using ZDX API, be aware of the caveats:

- Inconsistency in data
- Delay in data
- ZDX Score value of -1

Prior to the use of API, Zscaler recommends reviewing [Getting Started](https://help.zscaler.com/zdx/getting-started-zdx-api) for information regarding prerequisites, authentication, and making API calls.

For information on rate limits, see [Understanding Rate Limiting](https://help.zscaler.com/zdx/understanding-rate-limiting). To learn more about HTTP status codes, see [Understanding Error Codes](https://help.zscaler.com/zdx/understanding-error-codes). If you encounter any issues with the ZDX API, contact Zscaler Support.

Returns the authentication token for access to ZDX API.

Retrieves ZDX Scores for applications and specific device health metrics and events.

Most Report API endpoints require a 2-hour time range to provide 2 hours of data. If more data is required for a longer duration, another request with a different 2-hour time frame must be sent. For example, you cannot send an API request from 12:00 PM to 4:00 PM as this exceeds 2 hours. You must send an API request with a time range from 12:00 PM to 2:00 PM and another one from 2:00 PM to 4:00 PM.

The endpoint, `/devices/{deviceid}/events`, does not require a time range.

Lists the active locations and departments for a tenant.

Retrieves alert details such as device, application, network performance, and ZDX Score.

Some Alert API endpoints require a 2-hour time range to provide 2 hours of data. If more data is required for a longer duration, another request with a different 2-hour time frame must be sent. For example, you cannot send an API request from 12:00 PM to 4:00 PM as this exceeds 2 hours. You must send an API request with a time range from 12:00 PM to 2:00 PM and another one from 2:00 PM to 4:00 PM.

The following endpoints do not require a time range:

- `/alerts/{alert_id}`
- `/alerts/{alert_id}/affected_devices`

Start deep tracing on a specific user and their respective device.

Retrieve the distribution of software information across your organization.

The data returned by the ZDX API might not exactly match the data on the ZDX UI. The difference between the data is a marginal 2% because the aggregated metrics compute using approximate functions. The ZDX API does this in order to maximize performance.

For example, the Page Fetch Time (PFT) for a Web probe on the ZDX UI is 89ms while the API shows PFT as 90ms.

Zscaler Client Connector does the hard work of collecting all the telemetry and reporting. This can cause a delay from collection to reporting, which is estimated to be 20 minutes. To get the full data for an hour, ensure that you use the correct timestamp and adjust for this delay.

If you receive a ZDX Score of -1 on the ZDX API, then there is no data available.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/understanding-zia-api","lastmod":"2026-08-28T16:50Z","nid":"1400486"} -->
## Understanding ZIA APIs

- Source: https://help.zscaler.com/legacy-apis/understanding-zia-api
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > Understanding ZIA APIs
- Last modified: 2026-08-28T16:50Z
- Summary: Introductory information about Zscaler's APIs, which give programmatic access to various Zscaler Internet Access (ZIA) features.

Zscaler Internet Access (ZIA) provides three APIs: the cloud service API, Sandbox Submission API, and 3rd-Party App Governance API. To learn more about authentication, making API calls, and activating configuration changes, see [Getting Started](https://help.zscaler.com/zia/api-getting-started). For detailed information on all available API calls, endpoints, and parameters, see the [Reference Guide](https://help.zscaler.com/zia/about-api). For a table summarizing all available API calls, endpoints, and rate limits, see the [API Rate Limit Summary](https://help.zscaler.com/zia/api-rate-limit-summary). To try out requests and responses for API calls using the Postman app, see [Configuring the Postman REST API Client](https://help.zscaler.com/zia/configuring-postman-rest-api-client).

## Cloud Service API

Availability of the cloud service API is limited. To enable this API for your organization, contact Zscaler Support.

The cloud service API gives you programmatic access to the following ZIA features:

- Activation
- Adaptive Access Profiles
- Admin Audit Logs
- Admin & Role Management
- Advanced Settings
- Advanced Threat Protection Policy
- Alerts
- API Authentication
- Authentication Settings
- Azure Integrations
- Bandwidth Control & Classes
- Browser Isolation
- Cloud Applications
- Cloud App Control Policy
- Cloud Nanolog Streaming Service (NSS)
- Devices
- Device Groups
- Data Loss Prevention (DLP)
- DNS Control Policy
- End User Notifications
- Endpoint Data Loss Prevention (DLP)
- Event Logs
- File Type Control Policy
- Firewall Policies
- Forwarding Control Policy
- FTP Control Policy
- HTTP Header Control
- Intermediate CA Certificates
- IoT Report
- IPS Control Policy
- Location Management and Traffic Forwarding
- Malware Protection Policy
- Mobile Malware Protection Policy
- NAT Control Policy
- Organization Details
- Outbound Email DLP Policy
- PAC Files
- Partner Integrations
- Policy Export
- Remote Assistance Support
- Root Certificates
- Rule Labels
- SaaS Security API
- Sandbox Policy & Settings
- Sandbox Report
- Secure Browsing
- Security Policy Settings
- Security & UEBA Alerts
- Service Edges
- Shadow IT Report
- SMPC Instance
- SSL Inspection Policy
- System Audit Report
- Time Intervals
- Traffic Capture Policy
- URL Categories
- URL Filtering Policy
- URL & Cloud App Control Policy Settings
- User Authentication Settings
- User Management
- Votiro CDR
- Workload Groups

## Sandbox Submission API

To obtain access to the Sandbox Submission API, contact your Zscaler Account team.

The Sandbox Submission API gives you programmatic access to Zscaler Sandbox, which allows you to submit files to perform behavioral analysis. By default, files are directly submitted to the Sandbox to obtain a verdict. If a verdict already exists for the file, you can optionally force the Sandbox to reanalyze the file. You can submit up to 100 raw and archive files (e.g., ZIP) per day for Sandbox analysis. To learn more about the file types supported, see [About Sandbox](https://help.zscaler.com/zia/about-sandbox).

The Sandbox Submission API also allows you to perform out-of-band file inspection to generate real-time verdicts. Zscaler leverages capabilities such as Malware Prevention, Advanced Threat Prevention, Sandbox cloud effect, AI/ML-driven file analysis, and integrated third-party threat intelligence feeds to inspect files and classify them as benign or malicious instantaneously. You can submit raw and archive files (e.g., ZIP), and each file is limited to a maximum size of 400 MB. All file types that are supported by the [Malware Protection policy](https://help.zscaler.com/zia/configuring-malware-protection-policy) and [Advanced Threat Protection](https://help.zscaler.com/zia/configuring-advanced-threat-protection-policy) policy are supported.

Dynamic file analysis is not included in out-of-band file inspection.

- [Reference Guide > Sandbox Submission](https://help.zscaler.com/zia/cloud-sandbox-submission)
- [Configuring the Sandbox Policy](https://help.zscaler.com/zia/configuring-sandbox-policy)
- [Configuring the Default Sandbox Rule](https://help.zscaler.com/zia/configuring-default-sandbox-rule)

## 3rd-Party App Governance API

To access the 3rd-Party App Governance API, you must have an 3rd-Party App Governance trial or license. To obtain a trial or license, contact your Zscaler Account team.

The 3rd-Party App Governance API gives you programmatic access to [Zscaler 3rd-Party App Governance](https://help.zscaler.com/zia/what-apptotal), which allows you to search the 3rd-Party App Governance Catalog for an application by name, app ID, or a valid URL (i.e., consent or marketplace link). If the application is not found in the catalog, it is automatically submitted to the 3rd-Party App Governance Sandbox for analysis. After analysis is complete, you can perform a subsequent search for the application and retrieve its information.

The 3rd-Party App Governance API also allows you to retrieve the list of [custom views](https://help.zscaler.com/zia/custom-views/) that you have configured in the 3rd-Party App Governance Admin Portal and includes all configurations that define the custom view. You can then retrieve all applications that are related to a specified custom view.

To learn more, see [Reference Guide > 3rd-Party App Governance](https://help.zscaler.com/zia/apptotal-api).

You can retrieve information about Isolation profiles.

To learn more, see:

- [Reference Guide > Browser Isolation](https://help.zscaler.com/zia/browser-isolation) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with Browser Isolation permission under Policy & Components > Shared Policy Components.
- [What Is Isolation?](https://help.zscaler.com/isolation/what-isolation)
- [Creating Isolation Profiles for ZIA](https://help.zscaler.com/isolation/creating-zia-isolation-profile-isolation)

You can retrieve information about Cloud App Control policy rules.

To learn more, see:

- [Reference Guide > Cloud App Control Policy](https://help.zscaler.com/zia/cloud-app-control-policy) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full access to URL & Cloud App Control permission under Policy & Components > Access Control. The tenant profiles API resources require full access under Administration > Tenant Profiles.
- [About Cloud App Control](https://help.zscaler.com/zia/about-cloud-app-control)
- [About Tenant Profiles](https://help.zscaler.com/zia/about-tenant-profiles)

You can download and export CSV-formatted admin audit log reports that include all policy changes and API calls. Audit log reports are stored for the last 6 months, and you can download reports for up to 31 days or a maximum of 1,000 records at a time.

To learn more, see:

- [Reference Guide > Admin Audit Logs](https://help.zscaler.com/zia/admin-audit-logs) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Audit Logs permission under Administration Controls.
- [Admin Audit Logs Use Cases](https://help.zscaler.com/zia/audit-log-use-cases)
- [About Audit Logs](https://help.zscaler.com/zia/about-audit-logs)

A denylist is a list of malicious URLs to and from which Zscaler blocks all internet traffic. Zscaler provides a continuously updated global denylist, and each organization can manage a custom denylist. To retrieve or replace a denylist, use the `/security/advanced` endpoint. To add or remove individual URLs in a denylist, use the `security/advanced/blacklistUrls` endpoint.

An allowlist is a list of URLs that Zscaler exempts from security scanning. Zscaler does not provide a global allowlist, but each organization can manage a custom allowlist. A local allowlist can contain up to 255 URLs. To retrieve or replace an allowlist, use the `/security` endpoint. However, you cannot add or remove individual URLs to an allowlist using the API.

For your organization's custom denylist and allowlist, you can add up to 25K custom URLs and IPs across all categories (custom and predefined).

To learn more, see:

- [Reference Guide > Security Policy Settings](https://help.zscaler.com/zia/security-policy-settings) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Advanced Threat Protection permission under Policy & Components > Security.
- [Security Policy Settings Use Cases](https://help.zscaler.com/zia/security-policy-settings-use-cases)
- [About Policy Enforcement](https://help.zscaler.com/zia/about-policy-enforcement)
- [Adding URLs to the Denylist](https://help.zscaler.com/zia/adding-urls-denylist)
- [Adding URLs to the Allowlist](https://help.zscaler.com/zia/adding-urls-allowlist)

The Zscaler service performs SSL inspection by acting as a full SSL proxy or a trusted man-in-the-middle (MITM) proxy. To perform SSL inspection, Zscaler needs to generate domain certificates (end-entity certificates) dynamically using a Certificate Authority (CA) issued by either Zscaler or your organization. Using Intermediate CA Certificates API resources, you can:

- Create a custom intermediate CA certificate.
- Generate a key pair.
- Generate and download a Certificate Signing Request (CSR).
- Upload a signed intermediate CA certificate and certificate chain.
- Finalize a certificate.
- Mark a certificate as default.
- Download an intermediate CA certificate.
- Delete an intermediate CA certificate.
- Retrieve a list of intermediate CA certificates for SSL inspection

To learn more, see:

- [Reference Guide > Intermediate CA Certificates](https://help.zscaler.com/zia/intermediate-ca-certificates) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Intermediate CA Certificates permission under Policy & Components > Decryption.
- [Intermediate CA Certificates Use Cases](https://help.zscaler.com/zia/intermediate-ca-certificates-use-cases)
- [SSL Inspection](https://help.zscaler.com/zia/about-ssl-inspection)

Predefined and custom URL categories provide a way to classify URLs for your organization. Using URL Categories API resources, you can:

- Add or remove a URL for a predefined URL category.
- Get information about predefined and custom categories.
- Look up the categorization of specified URLs.
- Add, update, and delete custom categories.
- Update custom categories with IP addresses and URLs.
- Find matching entries for URLs in existing custom URL categories and add related entries to a single category.

To learn more, see:

- [Reference Guide > URL Categories](https://help.zscaler.com/zia/url-categories) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full permission for the respective URL Categories components under Policy & Components.
- [URL Categories Use Cases](https://help.zscaler.com/zia/url-categories-use-cases)
- [About URL Categories](https://help.zscaler.com/zia/about-url-categories)

Using User Management API resources, you can retrieve user, group, and department information as well as add, update, and delete users.

To learn more, see:

- [Reference Guide > User Management](https://help.zscaler.com/zia/user-management) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full User Management permission under Administration Controls.
- [User Management Use Cases](https://help.zscaler.com/zia/user-management-use-cases)
- [About Authentication Profile](https://help.zscaler.com/zia/about-authentication-profile)
- [Authenticating and Managing Users](https://help.zscaler.com/zia/about-provisioning-authenticating-users)

Location Management API resources allow you to retrieve all attributes of a Zscaler service-defined location or sublocation as a request, add or update locations with VPN credentials or static IP addresses, add or update sublocations, and delete locations and sublocations. You can also retrieve an up-to-date list of countries that are used in location configuration.

To learn more, see:

- [Reference Guide > Location Management](https://help.zscaler.com/zia/location-management) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Locations and VPN Credentials Management permissions under Traffic Forwarding.
- [Location Management Use Cases](https://help.zscaler.com/zia/location-management-use-cases)
- [About Locations](https://help.zscaler.com/zia/about-locations)
- [Understanding Sublocations](https://help.zscaler.com/zia/understanding-sublocations)
- [About VPN Credentials](https://help.zscaler.com/zia/about-vpn-credentials)

### Getting and Updating VPN Credentials for Specific Locations

The Zscaler service also inspects internal traffic within an organization's corporate network using ZIA Public Service Edges or secure web gateways. Traffic forwarding is enabled through IPSec VPN tunneling, and requires that the proper user credentials are configured. Using Location Management endpoints, you can get and update VPN credentials for specific locations.

To retrieve VPN credential information for locations, use the `/vpnCredentials` endpoint. To retrieve and update individual VPN credentials for a VPN ID, use the `/vpnCredentials/{vpnId}` endpoint.

User passwords can be randomly regenerated at regular intervals (e.g., every 30 days).

To learn more, see:

- [Reference Guide > Traffic Forwarding](https://help.zscaler.com/zia/traffic-forwarding-0) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full VPN Credentials Management permissions under Traffic Forwarding.
- [VPN Credentials Use Cases](https://help.zscaler.com/zia/vpn-credentials-use-cases)
- [About VPN Credentials](https://help.zscaler.com/zia/about-vpn-credentials)
- [Configuring an IPSec VPN Tunnel](https://help.zscaler.com/zia/configuring-ipsec-vpn-tunnel)

### Managing IPSec VPN Tunnels for SD-WAN Partner Integrations

The API resources used to support this functionality are for SD-WAN partner use only.

A Software-Defined Wide Area Networking (SD-WAN) partner API key enables technology partner access to the [Location Management](https://help.zscaler.com/zia/about-api) resources and a [VPN Credentials](https://help.zscaler.com/zia/about-api) resource within the cloud service API. For details and SD-WAN deployment configuration guides for each partner, refer to the [SD-WAN partner site](https://www.zscaler.com/partners/technology/sd-wan) or contact Zscaler Business Development.

To learn more, see:

- [Reference Guide > Location Management](https://help.zscaler.com/zia/location-management)
- [Reference Guide > Traffic Forwarding for information on the `POST /vpnCredentials` resource](https://help.zscaler.com/zia/traffic-forwarding-0#/vpnCredentials-post) To make calls to Locations and VPN Credentials resources, the authenticated [SD-WAN partner API client](https://help.zscaler.com/zia/adding-sd-wan-partner-api-clients) must have **SD-WAN Partner Access** for their [SD-WAN partner API role](https://help.zscaler.com/zia/adding-sd-wan-partner-api-roles) as well as a [partner API key](https://help.zscaler.com/zia/configuring-sdwan-integration#AddNewKey). To learn more, see [SD-WAN API Integration for IPSec VPN Tunnel Provisioning](https://help.zscaler.com/zia/sd-wan-api-integration) and [Getting Started](https://help.zscaler.com/zia/api-getting-started).
- [SD-WAN API Integration for IPSec VPN Tunnel Provisioning](https://help.zscaler.com/zia/sd-wan-api-integration)
- [About Locations](https://help.zscaler.com/zia/about-locations)
- [About VPN Credentials](https://help.zscaler.com/zia/about-vpn-credentials)
- [Configuring an IPSec VPN Tunnel](https://help.zscaler.com/zia/configuring-ipsec-vpn-tunnel)

### Getting GRE Tunnel, Static IP Address, Virtual IP Address, and Region Information

ZIA enables you to self-provision your static IP addresses or GRE tunnels to connect to the Zscaler service. Virtual IP addresses (VIPs) are used to establish IPSec VPN tunnels. The Traffic Forwarding API resources allow you to retrieve self-service GRE tunnel, static IP provisioning, data center VIP, and region-specific information.

To learn more, see:

- [Reference Guide > Traffic Forwarding](https://help.zscaler.com/zia/traffic-forwarding-0) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full GRE Tunnels and Static IPs permissions and the necessary permissions for other individual components under Traffic Forwarding.
- [About Generic Routing Encapsulation](https://help.zscaler.com/zia/about-generic-routing-encapsulation-gre)
- [About Static IP](https://help.zscaler.com/zia/about-static-ip)
- [SD-WAN API Integration for IPSec VPN Tunnel Provisioning](https://help.zscaler.com/zia/sd-wan-api-integration)

### Configuring and Managing Extranet Resources

You can configure and manage extranets that enable an organization to connect its internal network with another organization's network (e.g., partners, third-party vendors, etc.) that does not use a Zscaler service through Extranet Application Support. Extranet Application Support enables Zscaler-managed organization users to securely access extranet resources through an IPSec VPN tunnel established between a Zscaler data center and the external organization's data center, without requiring additional hardware or software installations. Using these resources, you can add, modify, and delete extranets and retrieve the list of extranets configured for your organization.

To learn more, see:

- [Reference Guide > Traffic Forwarding](https://help.zscaler.com/zia/traffic-forwarding-0) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Locations and VPN Credentials Management permissions under Traffic Forwarding.
- [Understanding Extranet Application Support](https://help.zscaler.com/zia/understanding-extranet-application-support)
- [About Extranet](https://help.zscaler.com/zia/about-extranet)
- [Configuring an Extranet](https://help.zscaler.com/zia/configuring-extranet)

### Managing Data Center Exclusion

Data Center (DC) Exclusion API resources allow you to disable the tunnels terminating at a virtual IP (VIP) address of a Zscaler DC, triggering a failover from primary to secondary tunnels in the event of service disruptions, Zscaler Trust Portal incidents, disasters, etc. This capability is supported by excluding DCs from service based on the traffic forwarding method. You can retrieve the list of Zscaler DCs that can be excluded from service to your organization and the list of DCs that are currently excluded from service based on your configured exclusions. Additionally, you can add, edit, and delete exclusions.

Currently, only the IPSec VPN tunnel forwarding method is supported for DC exclusion.

To learn more, see:

- [Reference Guide > Traffic Forwarding](https://help.zscaler.com/zia/traffic-forwarding-0) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Subclouds & DC Exclusion permissions under Traffic Forwarding.
- [About Data Center Exclusion Based on Traffic Forwarding Method](https://help.zscaler.com/zia/about-data-center-exclusion-based-traffic-forwarding-method)
- [Excluding a Data Center Based on Traffic Forwarding Method](https://help.zscaler.com/zia/excluding-data-center-based-traffic-forwarding-method)

Sandbox Policy & Settings API resources allow you to create, read, update, and delete Sandbox policy rules via the `/sandboxRules` endpoints. In addition, these resources allow each organization to create and manage a custom blocklist of MD5 file hashes for files that go through behavioral analysis by Sandbox. To retrieve or replace the custom blocklist, use the `/behavioralAnalysisAdvancedSettings` endpoint. To retrieve quota availability information for the MD5 file hashes, use the `/behavioralAnalysisAdvancedSettings/fileHashCount` endpoint.

You can add up to 10K MD5 file hashes to your custom blocklist.

To learn more, see:

- [Reference Guide > Sandbox Policy & Settings](https://help.zscaler.com/zia/sandbox-policy-settings) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Sandbox permission under Policy & Components > Security.
- [About Sandbox](https://help.zscaler.com/zia/about-sandbox)
- [Configuring the Sandbox Policy](https://help.zscaler.com/zia/configuring-sandbox-policy)
- [Add Custom File Hashes](https://help.zscaler.com/zia/add-custom-file-hashes)

Sandbox Report API resources allow you to get a full or summary [Sandbox Detail Report](https://help.zscaler.com/zia/viewing-sandbox-reports-data) for any file that was sent for analysis from any organization on the Zscaler cloud.

To learn more, see:

- [Reference Guide > Sandbox Report](https://help.zscaler.com/zia/cloud-sandbox-report) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Sandbox permission under Policy & Components > Security.
- [Sandbox Report Use Cases](https://help.zscaler.com/zia/sandbox-report-use-cases)
- [About Sandbox](https://help.zscaler.com/zia/about-sandbox)
- [Viewing Sandbox Reports and Data](https://help.zscaler.com/zia/viewing-sandbox-reports-data)

Admin & Role Management API resources allow you to retrieve admin role information, which dictates the level of access that admins have in the ZIA Admin Portal. These resources also allow you to add, update, or delete admins within your organization. You can also retrieve information about the current administrator or auditor user accessing the API, and update and retrieve the password expiration information.

To learn more, see:

- [Reference Guide > Admin & Role Management](https://help.zscaler.com/zia/admin-role-management) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Administrator Management and Role Management permissions under Administration Controls.
- [About Role Management](https://help.zscaler.com/zia/about-role-management)
- [About Administrators](https://help.zscaler.com/zia/about-administrators)

Data Loss Prevention (DLP) API resources allow you to retrieve information for DLP dictionaries, engines, incident receivers, Internet Content Adaptation Protocol (ICAP) servers, Cloud-to-Cloud Incident Receivers, etc. In addition, you can create and update DLP predefined dictionaries, Exact Data Match (EDM) and Indexed Document Match (IDM) dictionaries, notifications, and policy rules. You can also create and delete custom DLP engines, update predefined and custom DLP engines, and validate DLP engine expressions formed by combining DLP dictionaries using logical operators.

To learn more, see:

- [Reference Guide > Data Loss Prevention (DLP)](https://help.zscaler.com/zia/data-loss-prevention) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Data Loss Prevention permission and the necessary individual policy components under Policy & Components > Data Protection.
- [About Data Loss Prevention (DLP)](https://help.zscaler.com/zia/about-data-loss-prevention)
- [About DLP Dictionaries](https://help.zscaler.com/zia/about-dlp-dictionaries)
- [About DLP Notification Templates](https://help.zscaler.com/zia/about-dlp-notification-templates)
- [About Exact Data Match](https://help.zscaler.com/zia/about-exact-data-match)
- [About Indexed Document Match](https://help.zscaler.com/zia/about-indexed-document-match)
- [About DLP Engines](https://help.zscaler.com/zia/about-dlp-engines)
- [Understanding DLP Engines](https://help.zscaler.com/zia/understanding-dlp-engines)
- [About Cloud-to-Cloud Incident Forwarding](https://help.zscaler.com/zia/about-cloud-cloud-incident-forwarding)

Device Groups API resources allow you to retrieve device group information. ZIA maintains a list of all the devices in your organization that have Zscaler Client Connector deployed on them. These devices are categorized under predefined groups based on their OS type.

To learn more, see:

- [Reference Guide > Device Groups](https://help.zscaler.com/zia/device-groups) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with Device Management permission under Policy & Components > Shared Policy Components.
- [About Device Groups](https://help.zscaler.com/zia/about-device-groups)

Firewall Policies API resources allow you to create, read, update, and delete Firewall Filtering policy rules and their criteria.

To learn more, see:

- [Reference Guide > Firewall Policies](https://help.zscaler.com/zia/firewall-policies) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Firewall Control permission under Policy & Components > Access Control. Policy resources, such as network services and network applications, can be managed with any Firewall policy permission. However, some policy objects, such as IP & FQDN groups and time intervals have separate permissions alongside the respective policy permissions.
- [Configuring Firewall Policies](https://help.zscaler.com/zia/configuring-firewall-policies)

Rule Labels API resources allow you to create labels and associate them with URL Filtering policy rules. You can also retrieve a list of rule labels based on the specified rule type using the Rule Labels API resources.

To learn more, see:

- [Reference Guide > Rule Labels](https://help.zscaler.com/zia/rule-labels) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with the associated policy permissions.
- [About Rule Labels](https://help.zscaler.com/zia/about-rule-labels)
- [About URL Filtering](https://help.zscaler.com/zia/about-url-filtering)

URL Filtering Policy API resources allow you to retrieve information about and manage rules that limit your exposure to liability by managing access to web content based on a site's URL categorization. You can also retrieve the count of URL filtering rules based on specified search criteria using the URL Filtering Policy API resources.

To learn more, see:

- [Reference Guide > URL Filtering Policy](https://help.zscaler.com/zia/url-filtering-policy) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full URL & Cloud App Control policy permission under Policy & Components > Access Control.
- [About URL Filtering](https://help.zscaler.com/zia/about-url-filtering)
- [Fetching URL Filtering Rules Using Pagination and Search Filters](https://help.zscaler.com/zia/fetching-url-filtering-rules-using-pagination-and-search-filters)

User Authentication Settings API resources allow you to exempt URLs from cookie authentication.

To learn more, see:

- [Reference Guide > User Authentication Settings](https://help.zscaler.com/zia/user-authentication-settings) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Advanced Settings permission under Administration Controls.
- [About Zscaler Cookies](https://help.zscaler.com/zia/about-zscaler-cookies)

You can generate and download CSV-formatted event log reports that include provisioning and user and group management activities performed by System for Cross-domain Identity Management (SCIM) clients. The SCIM client's activities are recorded only if the SCIM-based provisioning is enabled for users on the Zscaler service.

To learn more, see:

- [Reference Guide > Event Logs](https://help.zscaler.com/zia/event-logs) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Audit Logs permission under Administration Controls.
- [About Event Logs](https://help.zscaler.com/zia/about-event-logs)
- [About SCIM](https://help.zscaler.com/zia/about-scim)

To make the configuration changes take effect, you must activate the changes. Activation API resources allow you to activate your configuration changes by pushing them to the Zscaler Central Authority (CA). You can also update the End User Subscription Agreement (EUSA) status and retrieve the EUSA acceptance status using these API resources.

To learn more, see:

- [Reference Guide > Activation](https://help.zscaler.com/zia/activation)
- [Getting Started](https://help.zscaler.com/zia/getting-started-zia-api)
- [Saving and Activating Changes in the ZIA Admin Portal](https://help.zscaler.com/zia/saving-and-activating-changes-admin-portal)
- [End User Subscription Agreement](https://www.zscaler.com/legal/end-user-subscription-agreement)

Authentication API resources allow you to authenticate and create an API session, check for an existing API session, and delete an API session.

To learn more, see:

- [Reference Guide > API Authentication](https://help.zscaler.com/zia/api-authentication)
- [Getting Started](https://help.zscaler.com/zia/getting-started-zia-api)

You can export the Shadow IT Report for the [cloud applications](https://help.zscaler.com/zia/about-cloud-applications) that Zscaler recognizes based on their usage in your organization. These reports include various security parameters of the cloud applications, information about users who have interacted with the applications, the list of locations from where the applications are accessed, and application usage details, as applicable.

To learn more, see:

- [Reference Guide > Shadow IT Report](https://help.zscaler.com/zia/shadow-it-report-api) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with the Web Data permission under Reporting Data.
- [About Shadow IT Report](https://help.zscaler.com/zia/about-shadow-it-report)
- [About Application Information](https://help.zscaler.com/zia/about-application-information)

SSL Inspection Policy API resources allow you to create, update, retrieve, and delete SSL Inspection policy rules and their criteria.

To learn more, see:

- [Reference Guide > SSL Inspection Policy](https://help.zscaler.com/zia/ssl-inspection-policy) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full SSL/TLS Inspection policy permission under Policy & Components > Decryption.
- [Configuring SSL Inspection Policy](https://help.zscaler.com/zia/configuring-ssl-inspection-policy)
- [About SSL Inspection Policy](https://help.zscaler.com/zia/about-ssl-inspection-policy)

Forwarding Control Policy API resources allow you to create, modify, retrieve, and delete forwarding rules. Additionally, these resources allow you to create, modify, retrieve, and delete Zscaler Private Access (ZPA) gateways that are used in forwarding rules for ZPA. You can also retrieve information about all proxy gateways using the Forwarding Control Policy API resources.

To learn more, see:

- [Reference Guide > Forwarding Control Policy](https://help.zscaler.com/zia/forwarding-control-policy) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Forwarding Control permission and the necessary permissions for traffic forwarding components under Traffic Forwarding.
- [About Forwarding Control](https://help.zscaler.com/zia/about-forwarding-policies)
- [Configuring Forwarding Policy](https://help.zscaler.com/zia/configuring-forwarding-policy)
- [About Zscaler Private Access (ZPA) Gateway](https://help.zscaler.com/zia/about-zpa-gateway)
- [Configuring ZPA Gateway](https://help.zscaler.com/zia/configuring-zpa-gateway)
- [About Gateways for Proxies](https://help.zscaler.com/zia/about-gateways-proxies)
- [Configuring Gateways for Proxies](https://help.zscaler.com/zia/configuring-gateways-proxies)

Workload Groups API resources allow you to add, update, delete, and retrieve workload groups configured in the ZIA Admin Portal. The workload groups can be configured as criteria in security policies such as Data Loss Prevention, URL Filtering, SSL Inspection, and Firewall Filtering rules.

To learn more, see:

- [Reference Guide > Workload Groups](https://help.zscaler.com/zia/workload-groups) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with the associated policy permissions.
- [About Workload Groups](https://help.zscaler.com/zia/about-workload-groups)
- [Configuring Workload Groups](https://help.zscaler.com/zia/configuring-workload-groups)

The IoT report API allows you to retrieve a list of devices (unmanaged user devices, servers, and IoT devices) that are identified by the Zscaler AI/ML engine from unauthenticated web traffic. You can also obtain the key contexts about the discovered devices, such as locations, ML auto-labels, classifications, etc. To learn more, see [Reference Guide > IoT Report](https://help.zscaler.com/zia/iot-report).

Access to these resources requires an [admin role](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) with the IoT Discovery permission under Reporting Data.

PAC Files API resources allow you to add and manage proxy auto-configuration (PAC) files in the ZIA Admin Portal. PAC files are one of the [traffic forwarding methods](https://help.zscaler.com/zia/choosing-traffic-forwarding-methods) supported by Zscaler and they allow you to forward your users' web traffic to the Zscaler service. All major browsers support PAC files and browsers simply require the address of the PAC file (i.e., PAC file URL) so they can fetch the file from the specified address, execute the file contents, and forward the web traffic to Zscaler's proxy server specified in the file. PAC files can be hosted on a workstation, an internal web server, or a server outside the corporate network. The Zscaler service hosts a default PAC file that uses geolocation technology to forward traffic to the nearest ZIA Public Service Edge. You can also upload custom PAC files to the Zscaler service.

Using these API resources, you can retrieve the list of hosted PAC files, add custom PAC files, validate the PAC file content and check for errors, branch an existing PAC file to create a new version, retrieve all or specific versions of a PAC file, and delete a PAC file.

To learn more, see:

- [Reference Guide > PAC Files](https://help.zscaler.com/zia/pac-files) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Hosted PAC Files permission under Traffic Forwarding.
- [About Hosted PAC Files](https://help.zscaler.com/zia/about-hosted-pac-files)
- [Using Custom PAC Files to Forward Traffic to ZIA](https://help.zscaler.com/zia/using-custom-pac-file-forward-traffic-zia)
- [Writing a PAC File](https://help.zscaler.com/zia/writing-pac-file)

Authentication Settings API resources allow you to retrieve or update your organization's default authentication settings information.

To learn more, see:

- [Reference Guide > Authentication Settings](https://help.zscaler.com/zia/authentication-settings) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Authentication Settings permission under Administration Controls.
- [About Authentication Default Settings](https://help.zscaler.com/zia/about-authentication-default-settings)

Cloud Applications API resources allow you to retrieve a list of cloud applications associated with Advanced Settings, Bandwidth Classes, DLP rules, Cloud App Control rules, File Type Control rules, and SSL Inspection rules. You can also create, update, delete, and retrieve cloud application risk profiles using the Cloud Applications API resources.

To learn more, see:

- [Reference Guide > Cloud Applications](https://help.zscaler.com/zia/cloud-applications) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with the associated policy permissions.
- [About Cloud Applications](https://help.zscaler.com/zia/about-cloud-applications)
- [About Cloud Application Risk Profile](https://help.zscaler.com/zia/about-cloud-application-risk-profile)

File Type Control Policy API resources allow you to create, update, retrieve, and delete File Type Control policy rules and their criteria. In addition, you can create, update, and delete custom file types, retrieve information about custom file types and the count of custom file types, and retrieve information about all file types that can be used as rule conditions in File Type Control and DLP policies.

To learn more, see:

- [Reference Guide > File Type Control Policy](https://help.zscaler.com/zia/file-type-control-policy) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full File Type Control permission under Policy & Components > Access Control.
- [Configuring the File Type Control Policy](https://help.zscaler.com/zia/configuring-file-type-control-policy)
- [About File Type Control](https://help.zscaler.com/zia/about-file-type-control)
- [Configuring Custom File Types](https://help.zscaler.com/zia/configuring-custom-file-types)

Organization Details API resources allow you to retrieve your organization's information, including headquarter location, geolocation, address, and contact details. It also allows you to retrieve your subscriptions to the Zscaler service.

To learn more, see:

- [Reference Guide > Organization Details](https://help.zscaler.com/zia/organization-details)
- [About the Company Profile](https://help.zscaler.com/unified/configuring-company-profile)
- [Viewing Subscriptions](https://help.zscaler.com/unified/viewing-subscriptions)

DNS Control Policy API resources allow you to create, read, update, and delete DNS filtering rules and their criteria.

To learn more, see:

- [Reference Guide > DNS Control Policy](https://help.zscaler.com/zia/dns-control-policy) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full DNS Control permission under Policy & Components > Access Control.
- [Configuring the DNS Control Policy](https://help.zscaler.com/zia/configuring-dns-control-policy)

IPS Control Policy API resources allow you to create, update, and delete custom IPS signature rules, and retrieve a list of custom IPS signature rules configured for an organization. These API resources also allow you to read, update, and delete DNS filtering rules and their criteria.

To learn more, see:

- [Reference Guide > IPS Control Policy](https://help.zscaler.com/zia/ips-control-policy) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full IPS Control permission under Policy & Components > Security.
- [Configuring the IPS Control Policy](https://help.zscaler.com/zia/configuring-ips-control-policy)

These API resources allow you to update the Malware Protection policy and retrieve information about the policy configurations.

To learn more, see:

- [Reference Guide > Malware Protection Policy](https://help.zscaler.com/zia/malware-protection-policy) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Malware Protection permission under Policy & Components > Security.
- [Configuring the Malware Protection Policy](https://help.zscaler.com/zia/configuring-malware-protection-policy)

These API resources allow you to update the Advanced Threat Protection policy and retrieve information about the policy configurations.

To learn more, see:

- [Reference Guide > Advanced Threat Protection Policy](https://help.zscaler.com/zia/advanced-threat-protection-policy) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Advanced Threat Protection permission under Policy & Components > Security.
- [Configuring the Advanced Threat Protection Policy](https://help.zscaler.com/zia/configuring-advanced-threat-protection-policy)

These API resources allow you to update the advanced settings available for URL Filtering and Cloud App Control policies and retrieve information about the advanced settings.

To learn more, see:

- [Reference Guide > URL & Cloud App Control Policy Settings](https://help.zscaler.com/zia/url-cloud-app-control-policy-settings) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with either full URL & Cloud App Control permission under Policy & Components > Access Control or full Advanced Settings permission under Administration Controls.
- [Configuring Advanced Policy Settings](https://help.zscaler.com/zia/configuring-advanced-policy-settings)

These API resources allow you to update the advanced cloud configuration settings in the ZIA Admin Portal and retrieve information about the settings.

To learn more, see:

- [Reference Guide > Advanced Settings](https://help.zscaler.com/zia/advanced-settings) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Advanced Settings permission under Administration Controls.
- [Configuring Advanced Settings](https://help.zscaler.com/zia/configuring-advanced-settings)

Cloud Nanolog Streaming Service (NSS) API resources allow you to add, update, validate, and delete cloud NSS feeds, NSS Collector servers, retrieve information about the feeds, collector servers, test connectivity, and get feed output format. Additionally, you can create, update, and delete NSS servers, retrieve a list of registered NSS servers, and download the NSS virtual appliance information based on the specified NSS server ID using these API resources.

To learn more, see:

- [Reference Guide > Cloud Nanolog Streaming Service (NSS)](https://help.zscaler.com/zia/cloud-nanolog-streaming-service-nss) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Nanolog Streaming Service permission under Cloud Configuration & Integration > Cloud Configuration.
- [Adding Cloud NSS Feeds](https://help.zscaler.com/zia/adding-cloud-nss-feeds)
- [About NSS Servers](https://help.zscaler.com/zia/about-nss-servers)
- [About NSS Collector Servers](https://help.zscaler.com/zia/about-nss-collector-servers)

These API resources allow you to update Remote Assistance preferences and retrieve information about the preferences.

To learn more, see:

- [Reference Guide > Remote Assistance Support](https://help.zscaler.com/zia/remote-assistance-support) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Remote Assistance Management permission under Administration Controls.
- [Enabling Remote Assistance](https://help.zscaler.com/zia/enabling-remote-assistance)

End User Notification (EUN) API resources allow you to retrieve information about browser-based notifications, Zscaler Client Connector-based EUNs, and user confirmation notifications, including configuration details and EUN enablement statuses for different policy types. In addition, you can update browser-based EUNs using the API.

To learn more, see:

- [Reference Guide > End User Notifications](https://help.zscaler.com/zia/end-user-notifications) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with the respective policy permission for which the EUN is configured.
- [Understanding Browser-Based End User Notifications](https://help.zscaler.com/zia/understanding-browser-based-end-user-notifications)
- [About Zscaler Client Connector-Based End User Notifications](https://help.zscaler.com/zia/about-zscaler-client-connector-based-end-user-notifications)

Policy Export API resources allow you to export the rules configured for various policy types to JSON files. To learn more, see [Reference Guide > Policy Export](https://help.zscaler.com/zia/policy-export).

Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with permission for the policies that need to be exported. Only policies to which the user or the API client has access are exported and other policies are excluded from the response.

These API resources allow you to create, update, and retrieve alert subscriptions.

To learn more, see:

- [Reference Guide > Alerts](https://help.zscaler.com/zia/alerts) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full permission under Administration > Alerts.
- [About Alert Subscriptions](https://help.zscaler.com/zia/about-alert-subscriptions)

Bandwidth Control & Classes API resources allow you to create, update, delete, and retrieve Bandwidth Control policy rules and their criteria. These API resources also allow you to create, update, delete, and retrieve bandwidth classes. Bandwidth classes identify the URL categories and cloud applications to which the service allocates bandwidth. You must configure the bandwidth classes before you can reference them in the Bandwidth Control policy rules.

To learn more, see:

- [Reference Guide > Bandwidth Control & Classes](https://help.zscaler.com/zia/bandwidth-control-classes) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full permission under Policy > Bandwidth Control.
- [About Bandwidth Classes](https://help.zscaler.com/zia/about-bandwidth-classes)
- [About Bandwidth Control](https://help.zscaler.com/zia/about-bandwidth-control)

Mobile Malware Control Policy API resources allow you to update and retrieve Mobile Malware Protection policy rules and their criteria.

To learn more, see:

- [Reference Guide > Mobile Malware Protection Policy](https://help.zscaler.com/zia/mobile-malware-protection-policy) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Mobile Malware Protection permission under Policy > Mobile.
- [Understanding Mobile Malware Protection](https://help.zscaler.com/zia/understanding-mobile-malware-protection)

NAT Control Policy API resources allow you to create, update, delete, and retrieve DNAT Control policy rules and their criteria.

To learn more, see:

- [Reference Guide > NAT Control Policy](https://help.zscaler.com/zia/nat-control-policy) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full NAT Control permission under Policy > Firewall Control.
- [About NAT Control](https://help.zscaler.com/zia/about-nat-control)

Time Intervals API resources allow you to create, update, and delete time intervals and retrieve a list of all configured time intervals.

To learn more, see:

- [Reference Guide > Time Intervals](https://help.zscaler.com/zia/time-intervals) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with the respective policy permission for which the Time Interval is configured.
- [About Time Intervals](https://help.zscaler.com/zia/about-time-intervals)

FTP Control Policy API resources allow you to update the FTP Control settings and retrieve the FTP Control status and the list of URL categories for which FTP is allowed.

To learn more, see:

- [Reference Guide > FTP Control Policy](https://help.zscaler.com/zia/ftp-control-policy) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full FTP Control permission under Policy > Access Control.
- [Understanding FTP Control](https://help.zscaler.com/zia/understanding-ftp-control)

System Audit Report API resources allow you to retrieve various configuration audit reports, such as System Audit Report, PAC File Audit Report, and IP Visibility Audit Report.

To learn more, see:

- [Reference Guide > System Audit Report](https://help.zscaler.com/zia/system-audit-report) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full permission under Analytics > System Audit Report.
- [About the System Audit Report](https://help.zscaler.com/zia/about-system-audit-report)

ZIA Virtual Service Edge API resources allow you to create, update, and delete Virtual Service Edge clusters and retrieve a list of Virtual Service Edge clusters.

To learn more, see:

- [Reference Guide > Service Edges](https://help.zscaler.com/zia/service-edges) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with respective permission under Administration > Cloud Configuration.
- [About Virtual Service Edge Clusters](https://help.zscaler.com/zia/about-virtual-service-edge-clusters)

These API resources allow you to update the Browser Control settings and retrieve the Browser Control status and list of configured browsers. Additionally, you can update the Smart Browser Isolation policy settings and retrieve a list of supported browsers and their versions using these API resources.

To learn more, see:

- [Reference Guide > Secure Browsing](https://help.zscaler.com/zia/browser-control-policy) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full permission under Policy > Secure Browsing.
- [Configuring the Browser Control Policy](https://help.zscaler.com/zia/configuring-browser-control-policy)
- [Configuring Smart Browser Isolation Policy](https://help.zscaler.com/zia/configuring-smart-browser-isolation-policy)

SaaS Security API resources allow you to create, update, and delete the SaaS Security Data at Rest Scanning Data Loss Prevention (DLP) and Malware Detection rules. You can also retrieve all the SaaS Security Data at Rest Scanning DLP and Malware Detection rules using the SaaS Security API resources.

To learn more, see:

- [Reference Guide > SaaS Security API](https://help.zscaler.com/zia/saas-security-api) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full permission under Policy > SaaS Security.
- [About Data at Rest Scanning DLP](https://help.zscaler.com/zia/about-data-rest-scanning-dlp)
- [About Data at Rest Scanning Malware Detection](https://help.zscaler.com/zia/about-data-rest-scanning-malware-detection)

Traffic Capture Policy API resources allow you to create, update, and delete Traffic Capture policy rules as well as retrieve information for all or specific rules. In addition, they allow you to retrieve the rule count, rule order information, and rule label associations.

To learn more, see:

- [Reference Guide > Traffic Capture Policy](https://help.zscaler.com/zia/traffic-capture-policy) Access to this resource requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full Traffic Capture permission under Traffic Forwarding > Traffic Forwarding Components > Traffic Capture.
- [About Traffic Capture Policy](https://help.zscaler.com/zia/about-traffic-capture-policy)
- [Configuring the Traffic Capture Policy](https://help.zscaler.com/zia/configuring-traffic-capture-policy)

Votiro CDR API resources allow you to update, retrieve, and delete Votiro CDR authentication credentials. You can also retrieve Votiro CDR policies configured for a tenant using these API resources.

To learn more, see:

- [Reference Guide > Votiro CDR](https://help.zscaler.com/legacy-apis/votiro-cdr) Access to this resource requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full permission.
- [Zscaler and Votiro Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-votiro-deployment-guide)
- [Understanding Votiro Integration for Zero Trust Browser](https://help.zscaler.com/zero-trust-browser/understanding-votiro-integration-zero-trust-browser)
- [Creating Isolation Profiles for ZIA](https://help.zscaler.com/zero-trust-browser/creating-isolation-profiles-zia)

SMPC Instance API resources allow you to update the data center and the Secure Message Processing Center (SMPC) instance details based on a specific data center ID and retrieve the status of all SMPC instances grouped by a data center.

To learn more, see:

- [Reference Guide > SMPC Instance](https://help.zscaler.com/legacy-apis/smpc-instance) Access to this resource requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full permission.

Header Control API resources allow you to create, update, delete, and retrieve HTTP header profiles and HTTP header insertion profiles.

To learn more, see:

- [Reference Guide > HTTP Header Control](https://help.zscaler.com/legacy-apis/http-header-control) Access to this resource requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full permission.
- [About HTTP Header Profile](https://help.zscaler.com/zia/about-http-header-profile)
- [About HTTP Header Insertion Profile](https://help.zscaler.com/zia/about-http-header-insertion-profile)

Security & UEBA Alerts API resources allow you to create, update, delete, and retrieve alert definitions, security alert rules, and User and Entity Behavior Analytics (UEBA) alert rules available for an organization. You can also retrieve a list of all configured webhooks for an organization using the Security & UEBA Alerts API resources.

To learn more, see:

- [Reference Guide > Security & UEBA Alerts](https://help.zscaler.com/legacy-apis/security-ueba-alerts) Access to this resource requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full permission.
- [Configuring an Alert Rule](https://help.zscaler.com/zia/configuring-alert-rule)
- [About Security & UEBA Alerts](https://help.zscaler.com/zia/about-security-ueba-alerts)
- [About Webhooks](https://help.zscaler.com/zia/about-webhooks)

These API resources allow you to retrieve information about all the devices in your organization that is registered with Zscaler.

To learn more, see:

- [Reference Guide > Devices](https://help.zscaler.com/legacy-apis/devices) Access to this resource requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full permission.
- [About Devices](https://help.zscaler.com/zia/about-devices)

Adaptive Access profile API resources allow you to retrieve a list of all Adaptive Access profiles applied in the access policy criteria and retrieve detailed information about these profiles.

To learn more, see:

- [Reference Guide > Adaptive Access Profiles](https://help.zscaler.com/legacy-apis/adaptive-access-profiles) Access to this resource requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full permission.
- [About Adaptive Access Profiles](https://help.zscaler.com/unified/about-adaptive-access-profiles)
- [Understanding Adaptive Access Engine](https://help.zscaler.com/unified/understanding-adaptive-access-engine)

Partner Integrations API resources allow you to retrieve a list of partners and services integrated with the Zscaler service, a list of configured CrowdStrike endpoints or device IDs, and detailed endpoint or device data based on the specified IDs. You can also configure the integration of Microsoft Defender for Endpoint APIs with Zscaler and retrieve the MD5 hash of the file required to view the Sandbox Detail Report using these API resources.

To learn more, see:

- [Reference Guide > Partner Integrations](https://help.zscaler.com/legacy-apis/partner-integrations-0) Access to this resource requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full permission.
- [Understanding Partner Integrations](https://help.zscaler.com/zia/understanding-partner-integrations)
- [Viewing the CrowdStrike Endpoint Hits Report](https://help.zscaler.com/zia/viewing-crowdstrike-endpoint-hits-report)
- [Integrating with CrowdStrike](https://help.zscaler.com/zia/integrating-crowdstrike)
- [Integrating with Microsoft Defender for Endpoint](https://help.zscaler.com/zia/integrating-microsoft-defender-endpoint)
- [Viewing Sandbox Reports and Data](https://help.zscaler.com/zia/viewing-sandbox-reports-data)

These API resources allow you to create, update, delete, and retrieve information about root certificates for third-party proxy-chaining services or isolation profiles.

To learn more, see:

- [Reference Guide > Root Certificates](https://help.zscaler.com/legacy-apis/root-certificates) Access to this resource requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full permission.
- [About Root Certificates](https://help.zscaler.com/zia/about-root-certificates)

These API resources allow you to retrieve the progress summary of the VPN connection refresh process and start a new asynchronous process to refresh the VPN connection status, retrieve the tunnel configuration status, start the tunnel configuration process, remove the tunnel configuration, and retrieve the status of the tunnel configuration removal process for a specified Azure hub. You can also retrieve the Azure hub sync process details and start a process to sync the hubs using these API resources.

To learn more, see:

- [Reference Guide > Azure Integration](https://help.zscaler.com/legacy-apis/azure-integration) Access to this resource requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full permission.
- [Integrating with Microsoft Azure Virtual WAN](https://help.zscaler.com/zia/integrating-microsoft-azure-virtual-wan)

These API resources allow you to create, update, and delete Outbound Email DLP policy rule configurations and retrieve information about the policy rules.

To learn more, see:

- [Reference Guide > Outbound Email Policy](https://help.zscaler.com/legacy-apis/outbound-email-dlp-policy) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full permission for Data Loss Prevention under Policy & Components > Data Protection > Policy Control.
- [What is Zscaler Outbound Email DLP?](https://help.zscaler.com/zia/what-zscaler-outbound-email-dlp)
- [Configuring Outbound Email Policy Rules](https://help.zscaler.com/zia/configuring-outbound-email-policy-rules)

These resources allow you to create, update, and delete Endpoint DLP policy rules and exception rules and retrieve information about these policy rules. In addition, you can create and manage Endpoint DLP resources and resource tag groups for Endpoint DLP channels, such as Printing, Network Share, and Removable Storage channels. You can also create endpoint applications and application tag groups to use for the Application File Access and Clipboard channels of Endpoint DLP and other supported policies (e.g., Firewall Filtering, DNS Control, and SSL/TLS Inspection).

To learn more, see:

- [Reference Guide > Endpoint Data Loss Prevention (DLP)](https://help.zscaler.com/legacy-apis/outbound-email-dlp-policy) Access to these resources requires an [admin](https://help.zscaler.com/zia/adding-admin-roles#administrators-access) or [API role](https://help.zscaler.com/zia/adding-api-roles#administrators-access) with full permission for Endpoint DLP under Policy & Components > Data Protection > Policy Control.
- [About Endpoint Data Loss Prevention](https://help.zscaler.com/zia/about-endpoint-dlp)
- [Configuring Endpoint DLP Policy Rules](https://help.zscaler.com/zia/configuring-endpoint-dlp-policy-rules)
- [About DLP & Endpoint Resources](https://help.zscaler.com/zia/about-dlp-endpoint-resources)
- [Adding DLP and Endpoint Resources](https://help.zscaler.com/zia/about-dlp-endpoint-resources)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/understanding-zpa-api","lastmod":"2026-08-21T07:06Z","nid":"1484766"} -->
## Understanding the ZPA API

- Source: https://help.zscaler.com/legacy-apis/understanding-zpa-api
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > Understanding the ZPA API
- Last modified: 2026-08-21T07:06Z
- Summary: Introductory information about the Zscaler Private Access (ZPA) API, which gives programmatic access to various ZPA features.

The Zscaler Private Access (ZPA) API gives you programmatic access to manage the following ZPA features:

- Admin Single Sign-On (SSO)
- Alternative Cloud Domains
- API Keys
- Application Segments
- Segment Groups
- AppProtection Controls
- AppProtection Profiles
- App Connectors
- App Connector Groups
- Business-to-Business (B2B) Federation
- Certificates
- Cloud Connector Groups
- Customers
- Customer Domains
- Emergency Access
- Enrollment Certificates
- Feature Configurations
- IdP Configurations
- Isolation Profiles
- Access Policies
- Client Forwarding Policies
- Timeout Policies
- AppProtection Policies
- Isolation Policies
- Privileged Policies
- Redirection Policies
- Log Streaming Service (LSS) Configurations
- Machine Groups
- Microtenants
- Posture Profiles
- Private Service Edges
- Private Service Edge Groups
- Private Cloud Controllers
- Private Cloud Controller Groups
- Privileged Approvals
- Privileged Consoles
- Privileged Credentials
- Privileged Portals
- Provisioning Keys
- SAML Attributes
- SCIM Attributes
- SCIM Groups
- Servers
- Server Groups
- Tag Management
- Trusted Networks
- User Portals
- User Portal Links
- Version Profiles
- VPN (for Legacy Apps)
- Zscaler Virtual IP Address Ranges

Zscaler can make periodic updates to the query and response parameters used by the ZPA API.

Prior to using the API, Zscaler recommends reviewing [Getting Started](https://help.zscaler.com/zpa/getting-started-zpa-api) for information regarding prerequisites, authentication, and making API calls.

For detailed information on all available API calls, endpoints, and parameters, see the [ZPA API Reference](https://help.zscaler.com/zpa/application-controller). For information on rate limits, see [Understanding Rate Limiting](https://help.zscaler.com/zpa/understanding-rate-limiting). To learn more about HTTP status codes, see [API Response Codes and Error Messages](https://help.zscaler.com/zpa/api-response-codes-and-error-messages). If you encounter any issues with the ZPA API, contact Zscaler Support.

Admin SSO API resources allow you to read and update admin SSO login configurations. To learn more, see:

- [Reference Guide > Admin Single Sign-On Management](https://help.zscaler.com/zpa/admin-single-sign-management)
- [Managing Admin Single Sign-On Login Configurations Using API](https://help.zscaler.com/zpa/managing-admin-single-sign-login-configurations-using-api)

Customer domain API resources allow you to read alternative cloud domains. To learn more, see:

- [Reference Guide > Zscaler Clouds](https://help.zscaler.com/zpa/zscaler-clouds)
- [Obtaining Alternative Cloud Domain Details Using API](https://help.zscaler.com/zpa/obtaining-alternative-cloud-domain-details-using-api)

API key resources allow you to create, read, update, and delete API keys. To learn more, see:

- [Reference Guide > API Key Management](https://help.zscaler.com/zpa/api-key-management-zpa-api-reference)
- [Configuring API Keys Using API](https://help.zscaler.com/zpa/configuring-api-keys-using-api)
- [About API Key Management](https://help.zscaler.com/zpa/about-api-key-management)

Application segment API resources allow you to create, read, update, and delete application segments. To learn more, see:

- [Reference Guide > Application Segment Management](https://help.zscaler.com/zpa/application-segment-management)
- [Configuring Application Segments Using API](https://help.zscaler.com/zpa/configuring-application-segments-using-api)
- [Configuring Browser Access Application Segments Using API](https://help.zscaler.com/zpa/configuring-browser-access-application-segments-using-api)
- [Managing Application Load Balancing and High Availability Using API](https://help.zscaler.com/zpa/managing-application-load-balancing-and-high-availability-using-api)
- [AppProtection Support](https://help.zscaler.com/zpa/configuring-application-segments-using-api#appprotectionSupport)
- [Microtenant Support](https://help.zscaler.com/zpa/configuring-application-segments-using-api#MicrotenantMoving)
- [About Applications](https://help.zscaler.com/zpa/about-applications)

Segment group API resources allow you to create, read, update, and delete segment groups. To learn more, see:

- [Reference Guide > Segment Group Management](https://help.zscaler.com/zpa/segment-group-management)
- [Configuring Segment Groups Using API](https://help.zscaler.com/zpa/configuring-segment-groups-using-api)
- [About Segment Groups](https://help.zscaler.com/zpa/about-segment-groups)

Server API resources allow you to create, read, update, and delete servers. To learn more, see:

- [Reference Guide > Server Management](https://help.zscaler.com/zpa/server-management)
- [Configuring Servers Using API](https://help.zscaler.com/zpa/configuring-servers-using-api)
- [About Servers](https://help.zscaler.com/zpa/about-servers)

Server group API resources allow you to create, read, update, and delete server groups. To learn more, see:

- [Reference Guide > Server Group Management](https://help.zscaler.com/zpa/server-group-management)
- [Configuring Server Groups Using API](https://help.zscaler.com/zpa/configuring-server-groups-using-api)
- [About Server Groups](https://help.zscaler.com/zpa/about-server-groups)

AppProtection control API resources allow you to create, read, update, and delete AppProtection controls. To learn more, see:

- [Reference Guide > AppProtection Control Management](https://help.zscaler.com/zpa/appprotection-control-management)
- [Configuring AppProtection Controls Using API](https://help.zscaler.com/zpa/configuring-appprotection-controls-using-api)
- [About AppProtection Controls](https://help.zscaler.com/zpa/about-appprotection-controls)

AppProtection profile API resources allow you to create, read, update, and delete AppProtection profiles. To learn more, see:

- [Reference Guide > AppProtection Profile Management](https://help.zscaler.com/zpa/appprotection-profile-management)
- [Configuring AppProtection Profiles Using API](https://help.zscaler.com/zpa/configuring-appprotection-profiles-using-api)
- [About AppProtection Profiles](https://help.zscaler.com/zpa/about-appprotection-profiles)

App Connector API resources allow you to read, update, and delete App Connectors. To learn more, see:

- [Reference Guide > App Connector Management](https://help.zscaler.com/zpa/app-connector-management-api)
- [Managing App Connectors Using API](https://help.zscaler.com/zpa/managing-app-connectors-using-api)
- [Configuring Auto Delete for Disconnected App Connectors](https://help.zscaler.com/zpa/configuring-auto-delete-disconnected-app-connectors-using-api)
- [About App Connectors](https://help.zscaler.com/zpa/about-connectors)

App Connector group API resources allow you to create, read, update, and delete App Connector groups. To learn more, see:

- [Reference Guide > App Connector Group Management](https://help.zscaler.com/zpa/app-connector-group-management)
- [Configuring App Connector Groups Using API](https://help.zscaler.com/zpa/configuring-app-connector-groups-using-api)
- [About App Connector Groups](https://help.zscaler.com/zpa/about-connector-groups)

B2B Federation API resources allow you to create, read, update, and delete partner federations. To learn more, see:

- [Reference Guide > Federate Applications](https://help.zscaler.com/legacy-apis/federate-applications)
- [Reference Guide > Partner Federation Provisioning](https://help.zscaler.com/legacy-apis/partner-federation-provisioning)
- [Reference Guide > Policies for B2B Federation](https://help.zscaler.com/legacy-apis/policies-b2b-federation)
- [Federating Partners Using API](https://help.zscaler.com/legacy-apis/federating-partners-using-api)
- [Federating Applications Using API](https://help.zscaler.com/legacy-apis/federating-applications-using-api)
- [Obtaining Access Policy Details for Federated Applications Using API](https://help.zscaler.com/legacy-apis/obtaining-access-policy-details-federated-applications-using-api)
- [Understanding Business-to-Business (B2B) Federation](https://help.zscaler.com/zpa/understanding-business-business-b2b-federation)
- [Configuring Business-to-Business Federation](https://help.zscaler.com/zpa/configuring-business-business-federation)

Certificate API resources allow you to create, read, update, and delete certificates. To learn more, see:

- [Reference Guide > Certificate Management](https://help.zscaler.com/zpa/certificate-management-api)
- [Configuring Certificates Using API](https://help.zscaler.com/zpa/configuring-certificates-using-api)
- [About Certificates](https://help.zscaler.com/zpa/about-certificates)

Cloud Connector group API resources allow you to read Cloud Connector groups. To learn more, see:

- [Reference Guide > Cloud Connector Groups](https://help.zscaler.com/zpa/cloud-connector-groups)
- [Obtaining Cloud Connector Group Details Using API](https://help.zscaler.com/zpa/obtaining-cloud-connector-group-details-using-api)
- [About Cloud Connector Groups](https://help.zscaler.com/zpa/about-cloud-connector-groups)

Customer API resources allow you to read authentication domains for a customer. To learn more, see:

- [Reference Guide > Customers](https://help.zscaler.com/zpa/customers)
- [Obtaining Customer Details Using API](https://help.zscaler.com/zpa/obtaining-customer-details-using-api)

Customer domain API resources allow you to create, update, and read customer domains. To learn more, see:

- [Reference Guide > Customer Domain Management](https://help.zscaler.com/zpa/customer-domain-management)
- [Managing Customer Domains Using API](https://help.zscaler.com/zpa/managing-customer-domains-using-api)

Emergency access API resources allow you to create, read, update, and delete emergency access users. To learn more, see:

- [Reference Guide > Emergency Access User Management](https://help.zscaler.com/zpa/emergency-access-user-management)
- [Configuring Emergency Access Users Using API](https://help.zscaler.com/zpa/configuring-emergency-access-users-using-api)
- [About Emergency Access Users](https://help.zscaler.com/zpa/about-emergency-access-users)

Enrollment certificate API resources allow you to read enrollment certificates. To learn more, see:

- [Reference Guide > Enrollment Certificates](https://help.zscaler.com/zpa/enrollment-certificates)
- [Configuring Enrollment Certificate Details Using API](https://help.zscaler.com/zpa/obtaining-enrollment-certificate-details-using-api)
- [About Enrollment (CA) Certificates](https://help.zscaler.com/zpa/about-enrollment-ca-certificates)

Feature configuration API resources allow you to create, read, and update feature configurations. To learn more, see:

- [Reference Guide > Feature Configuration Management](https://help.zscaler.com/zpa/feature-configuration-management)
- [Managing Feature Configurations Using API](https://help.zscaler.com/zpa/managing-feature-configurations-using-api)

IdP configuration API resources allow you to read IdP configurations. To learn more, see:

- [Reference Guide > IdP Configurations](https://help.zscaler.com/zpa/idp-configurations)
- [Configuring IdP Configuration Details Using API](https://help.zscaler.com/zpa/obtaining-idp-configuration-details-using-api)
- [About IdP Configuration](https://help.zscaler.com/zpa/about-idp-configuration)

Isolation profile API resources allow you to read isolation profiles. To learn more, see:

- [Reference Guide > Isolation Profiles](https://help.zscaler.com/zpa/isolation-profiles)
- [Obtaining Isolation Profile Details Using API](https://help.zscaler.com/zpa/obtaining-isolation-profile-details-using-api)

Policy management API resources allow you to create, read, update, and delete access policies. To learn more, see:

- [Reference Guide > Policy Management](https://help.zscaler.com/zpa/policy-management)
- [Configuring Access Policies Using API](https://help.zscaler.com/zpa/configuring-access-policies-using-api)
- [About Access Policy](https://help.zscaler.com/zpa/about-access-policy)

Policy management API resources allow you to create, read, update, and delete client forwarding policies. To learn more, see:

- [Reference Guide > Policy Management](https://help.zscaler.com/zpa/policy-management)
- [Configuring Client Forwarding Policies Using API](https://help.zscaler.com/zpa/configuring-client-forwarding-policies-using-api)
- [About Client Forwarding Policy](https://help.zscaler.com/zpa/about-client-forwarding-policy)

Policy management API resources allow you to create, read, update, and delete timeout policies. To learn more, see:

- [Reference Guide > Policy Management](https://help.zscaler.com/zpa/policy-management)
- [Configuring Timeout Policies Using API](https://help.zscaler.com/zpa/configuring-timeout-policies-using-api)
- [About Timeout Policy](https://help.zscaler.com/zpa/about-timeout-policy)

Policy management API resources allow you to create, read, update, and delete AppProtection policies. To learn more, see:

- [Reference Guide > Policy Management](https://help.zscaler.com/zpa/policy-management)
- [Configuring AppProtection Policies Using API](https://help.zscaler.com/zpa/configuring-appprotection-policies-using-api)
- [About AppProtection Policy](https://help.zscaler.com/zpa/about-appprotection-policy)

Policy management API resources allow you to create, read, update, and delete isolation policies. To learn more, see:

- [Reference Guide > Policy Management](https://help.zscaler.com/zpa/policy-management)
- [Configuring Isolation Policies Using API](https://help.zscaler.com/zpa/configuring-isolation-policies-using-api)
- [About Isolation Policy](https://help.zscaler.com/zpa/about-isolation-policy)

Policy management API resources allow you to create, read, update, and delete privileged credential policies. To learn more, see:

- [Reference Guide > Policy Management](https://help.zscaler.com/zpa/policy-management)
- [Configuring Privileged Policies Using API](https://help.zscaler.com/zpa/configuring-privileged-policies-using-api)
- [About Privileged Credentials Policy](https://help.zscaler.com/zpa/about-privileged-credentials-policy)
- [About Privileged Capabilities Policy](https://help.zscaler.com/zpa/about-privileged-capabilities-policy)

Policy management API resources allow you to create, read, update, and delete redirection policies. To learn more, see:

- [Reference Guide > Policy Management](https://help.zscaler.com/zpa/policy-management)
- [Configuring Redirection Policies Using API](https://help.zscaler.com/zpa/configuring-redirection-policies-using-api)
- [About Redirection Policy](https://help.zscaler.com/zpa/about-redirection-policy)

LSS configuration API resources allow you to create, read, update, and delete LSS configurations. To learn more, see:

- [Reference Guide > Log Streaming Service (LSS) Configuration](https://help.zscaler.com/zpa/log-streaming-service-lss-configuration)
- [Managing Log Streaming Service Configurations Using API](https://help.zscaler.com/zpa/managing-log-streaming-service-configurations-using-api)
- [About the Log Streaming Service](https://help.zscaler.com/zpa/about-log-streaming-service)

Machine group API resources allow you to read machine groups. To learn more, see:

- [Reference Guide > Machine Groups](https://help.zscaler.com/zpa/machine-groups)
- [Obtaining Machine Group Details Using API](https://help.zscaler.com/zpa/obtaining-machine-group-details-using-api)
- [About Machine Groups](https://help.zscaler.com/zpa/about-machine-groups)

Delegated tenant administration API resources allow you to create, read, update, and delete Microtenants. To learn more, see:

- [Reference Guide > Delegated Tenant Administration](https://help.zscaler.com/zpa/delegated-tenant-administration)
- [Configuring Microtenants Using API](https://help.zscaler.com/zpa/configuring-microtenants-using-api)
- [About Microtenants](https://help.zscaler.com/zpa/about-microtenants)

Posture profile API resources allow you to read posture profiles. To learn more, see:

- [Reference Guide > Posture Profiles](https://help.zscaler.com/zpa/posture-profiles)
- [Obtaining Posture Profile Details Using API](https://help.zscaler.com/zpa/obtaining-posture-profile-details-using-api)
- [Configuring Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)

Privileged approval API resources allow you to create, read, update, and delete privileged approvals. To learn more, see:

- [Reference Guide > Privileged Approval Management](https://help.zscaler.com/zpa/privileged-approval-management)
- [Configuring Privileged Approvals Using API](https://help.zscaler.com/zpa/configuring-privileged-approvals-using-api)
- [About Privileged Approvals](https://help.zscaler.com/zpa/about-privileged-approvals)

Privileged console API resources allow you to create, read, update, and delete privileged consoles. To learn more, see:

- [Reference Guide > Privileged Console Management](https://help.zscaler.com/zpa/privileged-console-management)
- [Configuring Privileged Consoles Using API](https://help.zscaler.com/zpa/configuring-privileged-consoles-using-api)
- [About Privileged Consoles](https://help.zscaler.com/zpa/about-privileged-consoles)

Privileged credential API resources allow you to create, read, update, and delete privileged credentials. To learn more, see:

- [Reference Guide > Privileged Credential Management](https://help.zscaler.com/zpa/privileged-credential-management)
- [Configuring Privileged Credentials Using API](https://help.zscaler.com/zpa/configuring-privileged-credentials-using-api)
- [About Privileged Credentials](https://help.zscaler.com/zpa/about-privileged-credentials)

Privileged portal API resources allow you to create, read, update, and delete privileged portals. To learn more, see:

- [Reference Guide > Privileged Portal Management](https://help.zscaler.com/zpa/privileged-portal-management)
- [Configuring Privileged Portals Using API](https://help.zscaler.com/zpa/configuring-privileged-portals-using-api)
- [About Privileged Portals](https://help.zscaler.com/zpa/about-privileged-portals)

Provisioning key API resources allow you to create, read, update, and delete provisioning keys. To learn more, see:

- [Reference Guide > Provisioning Key Management](https://help.zscaler.com/zpa/provisioning-key-management)
- [Configuring Provisioning Keys Using API](https://help.zscaler.com/zpa/configuring-provisioning-keys-using-api)
- [About App Connector Provisioning Keys](https://help.zscaler.com/zpa/about-connector-provisioning-keys)
- [About ZPA Private Service Edge Provisioning Keys](https://help.zscaler.com/zpa/about-zpa-service-edge-provisioning-keys)

SAML attribute API resources allow you to read SAML attributes. To learn more, see:

- [Reference Guide > SAML Attributes](https://help.zscaler.com/zpa/saml-attributes)
- [Configuring SAML Attribute Details Using API](https://help.zscaler.com/zpa/obtaining-saml-attribute-details-using-api)
- [About SAML Attributes](https://help.zscaler.com/zpa/about-saml-attributes)

SCIM attribute API resources allow you to read SCIM attributes. To learn more, see:

- [Reference Guide > SCIM Attributes](https://help.zscaler.com/zpa/scim-attributes)
- [Obtaining SCIM Attribute Details Using API](https://help.zscaler.com/zpa/obtaining-scim-attribute-details-using-api)
- [About SCIM](https://help.zscaler.com/zpa/about-scim)

SCIM group API resources allow you to read SCIM groups.

- [Reference Guide > SCIM Groups](https://help.zscaler.com/zpa/scim-groups)
- [Obtaining SCIM Group Details Using API](https://help.zscaler.com/zpa/obtaining-scim-group-details-using-api)
- [About SCIM Groups](https://help.zscaler.com/zpa/about-scim-groups)

ZPA Private Service Edge API resources allow you to read, update, and delete ZPA Private Service Edges. To learn more, see:

- [Reference Guide > Private Service Edge Management](https://help.zscaler.com/zpa/private-service-edge-management-api)
- [Managing Private Service Edges Using API](https://help.zscaler.com/zpa/managing-service-edges-using-api)
- [About ZPA Private Service Edges](https://help.zscaler.com/zpa/about-zpa-private-service-edges)

ZPA Private Service Edge group API resources allow you to create, read, update, and delete ZPA Private Service Edge groups. To learn more, see:

- [Reference Guide > Private Service Edge Group Management](https://help.zscaler.com/zpa/private-service-edge-group-management)
- [Configuring ZPA Private Service Edge Groups Using API](https://help.zscaler.com/zpa/configuring-service-edge-groups-using-api)
- [About ZPA Private Service Edge Groups](https://help.zscaler.com/zpa/about-zpa-private-service-edge-groups)

Private Cloud Controller API resources allow you to update, read, and delete Private Cloud Controllers. To learn more, see:

- [Reference Guide > Private Cloud Controller Management](https://help.zscaler.com/zpa/private-cloud-controller-management-zpa-api-reference)
- [Managing Private Cloud Controllers Using API](https://help.zscaler.com/zpa/managing-private-cloud-controllers-using-api)
- [About Private Cloud Controllers](https://help.zscaler.com/zpa/about-private-cloud-controllers)

Private Cloud Controller group API resources allow you to create, read, update, and delete Private Cloud Controller groups. To learn more, see:

- [Reference Guide > Private Cloud Controller Group Management](https://help.zscaler.com/zpa/private-cloud-controller-group-management)
- [Managing Private Cloud Controller Groups Using API](https://help.zscaler.com/zpa/managing-private-cloud-controller-groups-using-api)
- [About Private Cloud Controller Groups](https://help.zscaler.com/zpa/about-private-cloud-controller-groups)

Tag management API resources allow you to create, read, update, and delete tags and tag groups. To learn more, see:

- [Reference Guide > Tag Namespace Management](https://help.zscaler.com/legacy-apis/tag-namespace-management)
- [Reference Guide > Tag Key Management](https://help.zscaler.com/legacy-apis/tag-key-management)
- [Reference Guide > Tag Group Management](https://help.zscaler.com/legacy-apis/tag-group-management)
- [Reference Guide > Application Segment Management](https://help.zscaler.com/legacy-apis/application-segment-management)
- [Reference Guide > Policy Management](https://help.zscaler.com/legacy-apis/policy-management)
- [Configuring Tag Namespaces Using API](https://help.zscaler.com/legacy-apis/configuring-tag-namespaces-using-api)
- [Configuring Tag Key-Value Pairs Using API](https://help.zscaler.com/legacy-apis/configuring-tag-key-value-pairs-using-api)
- [Configuring Tag Groups Using API](https://help.zscaler.com/legacy-apis/configuring-tag-groups-using-api)
- [Configuring Tags for Application Segments Using API](https://help.zscaler.com/legacy-apis/configuring-tags-application-segments-using-api)
- [Configuring Tags and Tag Groups with Policy Rules Using API](https://help.zscaler.com/legacy-apis/configuring-tags-and-tag-groups-policy-rules-using-api)
- [About Tag Management for Application Segments](https://help.zscaler.com/zpa/about-tag-management-application-segments)

Trusted network API resources allow you to read trusted networks. To learn more, see:

- [Reference Guide > Trusted Networks](https://help.zscaler.com/zpa/trusted-networks)
- [Obtaining Trusted Network Details Using API](https://help.zscaler.com/zpa/obtaining-trusted-network-details-using-api)
- [Configuring Trusted Networks for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-trusted-networks-zscaler-client-connector)

User portal API resources allow you to create, read, update, and delete user portals. To learn more, see:

- [Reference Guide > User Portal Management](https://help.zscaler.com/zpa/user-portal-management)
- [Configuring User Portals Using API](https://help.zscaler.com/zpa/configuring-user-portals-using-api)
- [About User Portals](https://help.zscaler.com/zpa/about-user-portals)

User portal link API resources allow you to create, read, update, and delete user portals. To learn more, see:

- [Reference Guide > User Portal Link Management](https://help.zscaler.com/zpa/user-portal-link-management)
- [Configuring User Portal Links Using API](https://help.zscaler.com/zpa/configuring-user-portal-links-using-api)
- [About User Portal Links](https://help.zscaler.com/zpa/about-user-portal-links)

Version profile API resources allow you to read version profiles. To learn more, see:

- [Reference Guide > Version Profiles](https://help.zscaler.com/zpa/version-profiles)
- [Obtaining Version Profile Details Using API](https://help.zscaler.com/zpa/obtaining-trusted-network-details-using-api)
- [Configuring a Version Profile](https://help.zscaler.com/zpa/configuring-version-profile)

VPN (for Legacy Apps) API resources allow you to read VPN (for Legacy Apps) resources. To learn more, see:

- [Reference Guide > VPN (for Legacy Apps) API](https://help.zscaler.com/zpa/vpn-legacy-apps-api)
- [Obtaining VPN (for Legacy Apps) Resources Using API](https://help.zscaler.com/zpa/obtaining-vpn-legacy-apps-resources-using-api)
- [VPN (for legacy Apps)](https://help.zscaler.com/zpa/vpn-legacy-apps)

Zscaler virtual IP address range API resources allow you to create, read, update, and delete Zscaler virtual IP address ranges. To learn more, see:

- [Reference Guide > Zscaler Virtual IP Address Range Management](https://help.zscaler.com/zpa/zscaler-virtual-ip-address-range-management)
- [Configuring Zscaler Virtual IP address Ranges Using API](https://help.zscaler.com/zpa/configuring-zscaler-virtual-ip-address-ranges-using-api)
- [About Client Connector IP Assignment](https://help.zscaler.com/zpa/about-client-connector-ip-assignment)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/understanding-zscaler-client-connector-api","lastmod":"2026-01-08T22:06Z","nid":"1395531"} -->
## Understanding the Zscaler Client Connector API

- Source: https://help.zscaler.com/legacy-apis/understanding-zscaler-client-connector-api
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > Zscaler Client Connector API > Understanding the Zscaler Client Connector API
- Last modified: 2026-01-08T22:06Z
- Summary: Introductory information about the Zscaler Client Connector API, which gives programmatic access to various Zscaler Client Connector features.

Availability of the Zscaler Client Connector API is limited. To enable the API for your organization, contact the Zscaler Account team.

The Zscaler Client Connector API gives you programmatic access to manage the following Zscaler Client Connector features:

- Login
- Devices
- Application Profiles

Prior to using the API, Zscaler recommends reviewing [Getting Started](https://help.zscaler.com/zscaler-client-connector/getting-started-client-connector-api) for information regarding prerequisites, authentication, and making API calls. For detailed information on all available API calls, endpoints, and parameters, see [Reference Guide](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-api/api-developer-reference-guide/reference-guide).

To learn more about rate limits and HTTP status codes, see [Understanding Rate Limiting](https://help.zscaler.com/zscaler-client-connector/understanding-rate-limiting) and [About Error Codes](https://help.zscaler.com/zscaler-client-connector/about-error-codes). If you encounter any issues with the Zscaler Client Connector API, contact Zscaler Support.

Login API resources allow you to authenticate with the API using the API Key and the Secret Key. To learn more, see [About API Key Management](https://help.zscaler.com/zscaler-client-connector/about-api-key-management).

Devices API resources allow you to retrieve a list of devices, their basic details, OTP single-use passwords and app profile passwords for a device, etc. To learn more, see [About Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/about-enrolled-devices).

Application profile API resources allow you to configure application and forwarding profiles. To learn more, see [About Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-profiles).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/understanding-zscaler-cloud-branch-connector-api","lastmod":"2026-09-08T22:45Z","nid":"1447251"} -->
## Understanding the Zscaler Cloud & Branch Connector API

- Source: https://help.zscaler.com/legacy-apis/understanding-zscaler-cloud-branch-connector-api
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > Zscaler Cloud & Branch Connector API > Understanding the Zscaler Cloud & Branch Connector API
- Last modified: 2026-09-08T22:45Z
- Summary: Information about the Zscaler Cloud & Branch Connector API

The Zscaler Cloud & Branch Connector API gives you programmatic access to the following Zscaler Cloud & Branch Connector features:

- Authentication
- Activation
- Admin & Role Management
- Cloud & Branch Connector Groups
- DNS Control Forwarding Rule
- DNS Gateway
- Forwarding Gateways
- Location Management
- Log and Control Forwarding
- Partner Integrations
- Policy Management
- Policy Resources
- Provisioning
- Workload Groups
- Zero Trust Gateway

Prior to using the API, Zscaler recommends that you review [Getting Started](https://help.zscaler.com/cloud-branch-connector/getting-started-api) for information regarding prerequisites, authentication, and making API calls.

For information on rate limits, see [Understanding Rate Limits](https://help.zscaler.com/cloud-branch-connector/understanding-rate-limits). To learn more about HTTP status codes, see [API Response Codes and Error Messages](https://help.zscaler.com/cloud-branch-connector/api-response-codes-and-error-messages). If you encounter any issues with the Zscaler Cloud & Branch Connector API, contact Zscaler Support.

Authentication API resources allow you to authenticate and create an API session, check for an existing API session, and delete an API session. To learn more, see:

- [Reference Guide > Authentication](https://help.zscaler.com/cloud-branch-connector/authentication)
- [Getting Started](https://help.zscaler.com/cloud-branch-connector/getting-started-api)

Activation API resources allow you to activate your configuration changes by pushing them to the Central Authority (CA). In order for any configuration changes to take effect, the Zscaler service requires you to activate the changes. To learn more, see:

- [Reference Guide > Activation](https://help.zscaler.com/cloud-branch-connector/activation)
- [Getting Started](https://help.zscaler.com/cloud-branch-connector/getting-started-api)

Admin & Role Management API resources allow you to retrieve admin user and role information to configure the level of access admins have in the Zscaler Cloud & Branch Connector Portal and API. These resources also allow you to add, update, or delete admins within your organization. To learn more, see:

- [Reference Guide > Admin and Role Management](https://help.zscaler.com/cloud-branch-connector/admin-and-role-management)
- [Admin and Role Management](https://help.zscaler.com/cloud-branch-connector/administration/administrator-role-management)
- [Getting Started](https://help.zscaler.com/cloud-branch-connector/getting-started-api)

Cloud & Branch Connector group API resources allow you to apply policies to a group of deployed Cloud & Branch Connectors. They also make it easy to upgrade Cloud & Branch Connectors belonging to the same group to maintain redundancy while upgrades are being executed. To learn more, see:

- [Reference Guide > Cloud & Branch Connector Groups](https://help.zscaler.com/cloud-branch-connector/cloud-branch-connector-groups)
- [Cloud & Branch Connector Group Management](https://help.zscaler.com/cloud-branch-connector/administration/cloud-branch-connector-group-management)
- [Getting Started](https://help.zscaler.com/cloud-branch-connector/getting-started-api)

Location Management API resources allow you to retrieve location and location template information. These resources also allow you to create, update, and delete location templates. To learn more, see:

- [Reference Guide > Location Management](https://help.zscaler.com/cloud-branch-connector/location-management)
- [Location Management](https://help.zscaler.com/cloud-branch-connector/administration/location-management)

Partner Integrations API resources allow you to retrieve Amazon Web Services (AWS) accounts, AWS account group information, Microsoft Azure accounts, and supported regions. These resources also allow you to create, update, and delete AWS accounts, AWS account groups, and Azure accounts. To learn more, see:

- [Reference Guide > Partner Integrations](https://help.zscaler.com/cloud-branch-connector/partner-integrations)
- [Cloud Connector Partner Integrations](https://help.zscaler.com/cloud-branch-connector/administration/cloud-connector-partner-integrations)

Provisioning API resources allow you to retrieve available API keys and regenerate new API keys. To learn more, see:

- [Reference Guide > Provisioning](https://help.zscaler.com/cloud-branch-connector/provisioning)
- [Managing Credentials Using API](https://help.zscaler.com/cloud-branch-connector/managing-credentials-using-api)

Policy Management API resources allow you to create and update traffic forwarding rules and retrieve the list of available rules and the count of rules using the following endpoints. To learn more, see:

- [Reference Guide > Policy Management](https://help.zscaler.com/cloud-branch-connector/policy-management)
- [Traffic Forwarding](https://help.zscaler.com/cloud-branch-connector/forwarding/traffic-forwarding)

Policy Resources API endpoints allow you to create and manage specific objects used in policy configuration, such as IP & FQDN groups, network services, and ZPA application segments. To learn more, see:

- [Reference Guide > Policy Resources](https://help.zscaler.com/cloud-branch-connector/policy-resources)
- [Firewall Filtering](https://help.zscaler.com/cloud-branch-connector/administration/firewall-filtering)

Forwarding Gateways API resources allow you to retrieve the list of available ZPA application segments. To learn more, see:

- [Reference Guide > Forwarding Gateways](https://help.zscaler.com/cloud-branch-connector/forwarding-gateways)
- [Forwarding Methods](https://help.zscaler.com/cloud-branch-connector/administration/forwarding-methods)

DNS Control Forwarding Rule API resources allow you to retrieve DNS forwarding rules. These resources allow you to create, update, or delete DNS forwarding rule configuration. To learn more, see:

- [Reference Guide > DNS Control Forwarding Rule](https://help.zscaler.com/legacy-apis/dns-control-forwarding-rule)
- [Traffic Forwarding](https://help.zscaler.com/cloud-branch-connector/forwarding/traffic-forwarding)

DNS Gateway API resources allow you to retrieve the list of DNS gateways. To learn more, see:

- [Reference Guide > DNS Gateway](https://help.zscaler.com/legacy-apis/dns-gateway)
- [About DNS Gateways](https://help.zscaler.com/cloud-branch-connector/about-dns-gateways)

Log and Control Forwarding API resources allow you to retrieve the list of forwarding rules. These resources allow you to create, update, or delete forwarding rule configurations. To learn more, see:

- [Reference Guide > Log and Control Forwarding Rule](https://help.zscaler.com/legacy-apis/log-and-control-forwarding)
- [About Log and Control Forwarding](https://help.zscaler.com/cloud-branch-connector/about-log-and-control-forwarding)

Workload groups API resources allow you to retrieve the list of workload groups. To learn more, see:

- [Reference Guide > Workload Groups](https://help.zscaler.com/legacy-apis/workload-groups-0)

Zero Trust Gateway (ZTGW) APIs allow you to create or update ZTGWs, retrieve ZTGWs, configuration status, number of ZTGWs per organization, etc. To learn more, see:

- [Reference Guide > Zero Trust Gateway](https://help.zscaler.com/legacy-apis/zero-trust-gateway)
- [What Are Zero Trust Gateways?](https://help.zscaler.com/cloud-branch-connector/what-zero-trust-gateways)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/url-categories","lastmod":"2026-02-17T06:06Z","nid":"1402511"} -->
## URL Categories

- Source: https://help.zscaler.com/legacy-apis/url-categories
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > URL Categories
- Last modified: 2026-02-17T06:06Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /urlCategories`

Gets information about all or custom URL categories. By default, the response includes keywords.

- Operation ID: `getUrlCategory`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customOnly` | query | no | object | If set to true, gets information on custom URL categories only. |
| `includeOnlyUrlKeywordCounts` | query | no | object | By default this parameter is set to false, so the response includes URLs and keywords for custom URL categories only. If set to true, the response only includes URL and keyword counts. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /urlCategories`

Adds a new custom URL category. If keywords are included within the request, they are added to the new category.

- Operation ID: `addCustomCategory`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customCategory` | body | no | UrlCategoryInformation | Custom URL category information. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /urlCategories/lite`

Gets a lightweight key-value list of all or custom URL categories.

- Operation ID: `getLiteUrlCategories`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /urlCategories/review/domains`

For the specified list of URLs, finds matching entries present in existing custom URL categories. This endpoint allows you to review URLs for potential matches in existing categories to group-related entries in a single category.

The URLs specified are matched against the entries present in `urls` (referred as Custom URLs in the UI) and `dbCategorizedUrls` (referred as URLs Retaining Parent Category in the UI) fields of custom URL categories. The matches are returned only for URLs of Subdomain type that match with existing wildcard entries and not vice versa. The only exception is when a wildcard URL is matched against another wildcard entry, but the former is identified as a subdomain in relation to the wildcard match. For example, if a URL such as `.teams.microsoft.com` matches with `.microsoft.com`, the `.teams.microsoft.com` entry in this case is regarded as a subdomain in relation to the match found and is returned as part of the API response. However, if `.teams.microsoft.com` matches with `teams.microsoft.com`, this becomes a wildcard match against a subdomain and is discarded in the response.

**Note**: A maximum of 100 URLs can be sent in a request.

The matches found through this request can be added to the required custom URL categories using the `PUT /urlCategories/review/domains` request.

- Operation ID: `UrlCategoryResource_getCategoriesWithMatchingDomains`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | yes | array<string> | The list of URLs that must be matched against the entries present in existing custom URL categories |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /urlCategories/review/domains`

Adds the list of matching URLs fetched by `POST /urlCategories/review/domains` to the specified custom URL categories. The URL specified is added to either `urls` (referred to as Custom URLs in the UI) or `dbCategorizedUrls` (referred to as URLs Retaining Parent Category in the UI) fields of the custom category based on the following condition: If the matching URL is present in the `dbCategorizedUrls` field, the new entry is added to this field. Otherwise, it is added to the `urls` field.

**Note**:

- A maximum of 100 URL categories can be updated at once using this request.
- Only URLs that are of Subdomain type can be added to custom categories via this request. Wildcard matches cannot be added. To learn more, see the description for `POST /urlCategories/review/domains`.

- Operation ID: `UrlCategoryResource_updateCategoriesWithMatchingDomains`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | yes | array<UrlDomainReview> | Information about the URLs (of Subdomain type) and the respective custom categories to which they must be added. This information can be obtained from the response of POST `/urlCategories/review/domains` . |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |

### `GET /urlCategories/urlQuota`

Gets information on the number of unique URLs that are currently provisioned for your organization as well as how many URLs you can add before reaching that number.

- Operation ID: `getUrlQuota`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /urlCategories/{categoryId}`

Gets the URL category information for the specified ID.

- Operation ID: `getUrlCategories`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `categoryId` | path | yes | object | The unique identifier for the URL category. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /urlCategories/{categoryId}`

Updates the URL category for the specified ID. If keywords are included within the request, then they replace existing ones for the specified URL category . If the keywords attribute is not included the request, the existing keywords are retained.

You can perform a full update for the specified URL category. However, if attributes are omitted within the update request, the values for those attributes are cleared.

You can also perform an incremental update, to add or remove URLs, for the specified URL category using the action parameter. To learn more, see [URL Categories Use Cases](https://help.zscaler.com/zia/url-categories-use-cases).

- Operation ID: `updateCategory`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `categoryId` | path | yes | object | The unique identifer for the URL category. |
| `action` | query | no | object | The action applied to the URL category (i.e., add or remove from category list). When no action is specified, all the URLs sent in the request will replace the existing ones. It is recommended that you specify the add or remove action to update the URL categories. This ensures that update is performed only on the added or removed URLs, and is useful when the URL category list is large. To learn more, see [URL Categories Use Cases](https://help.zscaler.com/zia/url-categories-use-cases). |
| `customCategory` | body | no | UrlCategoryInformation | Updates the URL category. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /urlCategories/{categoryId}`

Deletes the custom URL category for the specified ID. You cannot delete a custom category while it is being used by a URL policy or NSS feed. Also, predefined categories cannot be deleted.

- Operation ID: `deleteCustomCategory`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `categoryId` | path | yes | object | The unique identifer for the custom URL category. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /urlLookup`

Retrieve Zscaler's default classification for a given set of URLs (e.g., ['abc.com', 'xyz.com']).

**Note**:

- Custom URL classification is not returned by this request. Any URLs that are not categorized under a predefined URL category return a value of `MISCELLANEOUS_OR_UNKNOWN`.
- Up to 100 URLs can be looked up per request, and a URL cannot exceed 1,024 characters.

- Operation ID: `searchURLsClassification`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `urls` | body | no | array<string> | The given set of URLs to be looked up. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/url-cloud-app-control-policy-settings","lastmod":"2026-05-18T07:06Z","nid":"1510471"} -->
## URL & Cloud App Control Policy Settings

- Source: https://help.zscaler.com/legacy-apis/url-cloud-app-control-policy-settings
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > URL & Cloud App Control Policy Settings
- Last modified: 2026-05-18T07:06Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /advancedUrlFilterAndCloudAppSettings`

Retrieves information about URL and Cloud App Control advanced policy settings

- Operation ID: `AdvancedUrlFilteringCloudAppResource_getAdvancedUrlFiltOptions`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /advancedUrlFilterAndCloudAppSettings`

Updates the URL and Cloud App Control advanced policy settings

- Operation ID: `AdvancedUrlFilteringCloudAppResource_updateAdvancedUrlFiltOptions`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | AdvancedUrlFilteringCloudAppOptions | URL and Cloud Control advanced policy settings |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/url-filtering-policy","lastmod":"2026-07-06T07:06Z","nid":"1402516"} -->
## URL Filtering Policy

- Source: https://help.zscaler.com/legacy-apis/url-filtering-policy
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > URL Filtering Policy
- Last modified: 2026-07-06T07:06Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /urlFilteringRules`

Retrieves all the rules in the URL Filtering Policy. To learn more, see

[About URL Filtering](https://help.zscaler.com/zia/about-url-filtering)

.

- Operation ID: `getRules`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | object | Specifies the page offset |
| `pageSize` | query | no | object | Specifies the page size |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /urlFilteringRules`

Adds a URL Filtering Policy rule.

If you are using the Admin Rank feature, refer to [About Admin Rank](https://help.zscaler.com/zia/about-admin-rank) to determine which value to provide for `rank` when adding a policy rule. If you are not using Admin Rank, the `rank` value must be 7.

- Operation ID: `addRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | UrlFilteringRule | The URL filtering rule information |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /urlFilteringRules/count`

Retrieves the count of URL Filtering rules based on the specified search criteria

- Operation ID: `UrlFilteringRuleResource_getRulesCount`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /urlFilteringRules/{ruleId}`

Gets the URL Filtering Policy rule for the specified ID.

- Operation ID: `getRuleById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | The unique identifier for the URL Filtering Policy rule. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /urlFilteringRules/{ruleId}`

Updates the URL Filtering Policy rule for the specified ID.

If you are using the Admin Rank feature, refer to [About Admin Rank](https://help.zscaler.com/zia/about-admin-rank) to determine which value to provide for `rank` when adding a policy rule. If you are not using Admin Rank, the `rank` value must be 7.

- Operation ID: `updateRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | The unique identifier for the URL Filtering Policy rule. |
| `body` | body | no | UrlFilteringRule | The URL filering rule information. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /urlFilteringRules/{ruleId}`

Deletes the URL Filtering Policy rule for the specified ID.

- Operation ID: `deleteRule`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ruleId` | path | yes | object | The unique identifier for the URL Filtering Policy rule. |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |

### `GET /timeWindows`

Gets a list of [time intervals](https://help.zscaler.com/zia/defining-time-intervals) used for by the Firewall policy or the URL Filtering policy.

- Operation ID: `TimeWindowResource_getTimeWindows`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /timeWindows/lite`

Gets a name and ID dictionary of [time intervals](https://help.zscaler.com/zia/defining-time-intervals) used by the Firewall policy or the URL Filtering policy.

- Operation ID: `TimeWindowResource_getTimeWindowSummary`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/user-authentication-settings","lastmod":"2026-01-06T23:49Z","nid":"1402521"} -->
## User Authentication Settings

- Source: https://help.zscaler.com/legacy-apis/user-authentication-settings
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > User Authentication Settings
- Last modified: 2026-01-06T23:49Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /authSettings/exemptedUrls`

Gets a list of URLs that were exempted from [cookie authentication](https://help.zscaler.com/zia/about-zscaler-cookies). To learn more, see [URL Format Guidelines](https://help.zscaler.com/zia/url-format-guidelines).

- Operation ID: `getAuthExemptedUrls`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /authSettings/exemptedUrls`

Adds a URL to or removes a URL from the [cookie authentication](https://help.zscaler.com/zia/about-zscaler-cookies) exempt list. To add a URL to the list, set the action parameter to `ADD_TO_LIST`. To remove a URL, set action to `REMOVE_FROM_LIST`.

- Operation ID: `updateAuthExemptedUrls`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `action` | query | yes | object | The action applied to the exempted URLs list (i.e., adding a URL or removing a URL). |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/user-management","lastmod":"2026-08-21T03:26Z","nid":"1402506"} -->
## User Management

- Source: https://help.zscaler.com/legacy-apis/user-management
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > User Management
- Last modified: 2026-08-21T03:26Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /departments`

Retrieves a list of departments. The search parameters find matching values within the `name` or `comments` fields.

- Operation ID: `getDepartments`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `search` | query | no | object | The search string used to match against a department's name or comments attributes |
| `page` | query | no | object | Specifies the page offset |
| `limitSearch` | query | no | object | Limits the search to match only against the department name |
| `pageSize` | query | no | object | Specifies the page size |
| `sortBy` | query | no | object | Sorts the departments based on available values |
| `sortOrder` | query | no | object | Sorts the order of departments based on available values |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /departments`

Adds a department for an organization. To learn more, see [Adding Departments](https://help.zscaler.com/zia/adding-departments).

**Note**: This endpoint is accessible via [Zscaler OneAPI](https://help.zscaler.com/oneapi/understanding-oneapi) only.

- Operation ID: `DepartmentResource_addDepartment`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | Department | Department information |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /departments/lite`

Retrieves a list of departments. The search parameters find matching values within the `name` or `comments` fields.

- Operation ID: `DepartmentResource_getDepartmentsLite`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `search` | query | no | object | The search string used to match against a department's name or comments attributes |
| `id` | query | no | object | Department ID |
| `includeDefaultDept` | query | no | object | Specifies whether to include the default department for a user |
| `page` | query | no | object | Specifies the page offset |
| `pageSize` | query | no | object | Specifies the page size |
| `sortBy` | query | no | object | Sorts the departments based on available values |
| `sortOrder` | query | no | object | Sorts the order of departments based on available values |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /departments/{id}`

Retrieves the department based on the specified ID

- Operation ID: `getDepartment`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | object | Unique identifier for the department |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /departments/lite/{id}`

Retrieves the department based on the specified ID

- Operation ID: `DepartmentResource_getDepartmentLite`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | object | Unique identifier for the department |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /departments/{departmentId}`

Updates the department for an organization based on the specified ID.

**Note**: This endpoint is accessible via [Zscaler OneAPI](https://help.zscaler.com/oneapi/understanding-oneapi) only.

- Operation ID: `DepartmentResource_updateDepartment`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `departmentId` | path | yes | object | Department ID |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /departments/{departmentId}`

Deletes a department for an organization based on the specified ID.

**Note**: This endpoint is accessible via [Zscaler OneAPI](https://help.zscaler.com/oneapi/understanding-oneapi) only.

- Operation ID: `DepartmentResource_deleteDepartment`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `departmentId` | path | yes | object | Department ID |

**Responses:**

| Code | Description |
| --- | --- |
| default | Successful Operation |

### `GET /groups`

Retrieves a list of groups. The search parameters find matching values in the `name` or `comments` attributes.

- Operation ID: `getGroups`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `search` | query | no | object | The search string used to match against a group's name or comments attributes |
| `adminScopeSupport` | query | no | object | A Boolean value that indicates whether the group is part of the admin scope. When you set the value to **true**, the group is included in the admin scope. |
| `definedBy` | query | no | object | The string value defined by the group name or other applicable attributes |
| `page` | query | no | object | Specifies the page offset |
| `pageSize` | query | no | object | Specifies the page size |
| `sortBy` | query | no | object | Sorts the groups based on available values |
| `sortOrder` | query | no | object | Sorts the order of groups based on available values |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /groups`

Adds a new group. To learn more, see [Adding Groups](https://help.zscaler.com/zia/adding-groups).

**Note**: This endpoint is accessible via [Zscaler OneAPI](https://help.zscaler.com/oneapi/understanding-oneapi) only.

- Operation ID: `GroupResource_addGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | Group | Group information |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 201 | Group created successfully |
| 401 | Unauthorized access |
| 403 | Forbidden access |

### `GET /groups/lite`

Retrieves a list of group names. The search parameters find matching values in the `name` or `comments` attributes.

- Operation ID: `GroupResource_getGroupsLite`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `search` | query | no | object | The search string used to match against a group's name or comments attributes |
| `adminScopeSupport` | query | no | object | A Boolean value that indicates whether the group is part of the admin scope. When you set the value to **true**, the group is included in the admin scope. |
| `definedBy` | query | no | object | The string value defined by the group name |
| `page` | query | no | object | Specifies the page offset |
| `pageSize` | query | no | object | Specifies the page size |
| `sortBy` | query | no | object | Sorts the group names based on available values |
| `sortOrder` | query | no | object | Sorts the order of group names based on available values |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /groups/{groupId}`

Retrieves the group based on the specified ID

- Operation ID: `GroupResource_getGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `groupId` | path | yes | object | Group ID |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /groups/{groupId}`

Updates an existing group based on the specified ID.

**Note**: This endpoint is accessible via [Zscaler OneAPI](https://help.zscaler.com/oneapi/understanding-oneapi) only.

- Operation ID: `GroupResource_updateGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `groupId` | path | yes | object | Group ID |
| `body` | body | no | Group | Group information |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Group updated successfully |
| 400 | Invalid request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 500 | Internal server error |

### `DELETE /groups/{groupId}`

Deletes the group based on the specified ID.

**Note**: This endpoint is accessible via [Zscaler OneAPI](https://help.zscaler.com/oneapi/understanding-oneapi) only.

- Operation ID: `GroupResource_deleteGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `groupId` | path | yes | object | Group ID |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | Group deleted successfully |
| 403 | User does not have the required permissions |
| 404 | Group not found |

### `GET /groups/lite/{groupId}`

Retrieves the group based on the specified ID

- Operation ID: `GroupResource_getGroupLite`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `groupId` | path | yes | object | Group ID |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /users`

Gets a list of all users and allows user filtering by name, department, or group. The `name` search parameter performs a partial match. The `dept` and `group` parameters perform a 'starts with' match.

- Operation ID: `getUsers`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `name` | query | no | object | Filters by user name. |
| `dept` | query | no | object | Filters by department name. |
| `group` | query | no | object | Filters by group name. |
| `page` | query | no | object | Specifies the page offset. |
| `pageSize` | query | no | object | Specifies the page size. The default size is 100 and the maximum size is 10,000. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /users`

Adds a new user. A user can belong to multiple groups, but can only belong to one department.

- Operation ID: `addUser`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `user` | body | no | User | User information. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `POST /users/bulkDelete`

Bulk delete users up to a maximum of 500 users per request. The response returns the user IDs that were successfully deleted.

- Operation ID: `bulkDeleteUsers`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `userIds` | body | no | IdListInteger | The user IDs to bulk delete. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /users/references`

Gets the list of Name-ID pairs for all users in the ZIA Admin Portal that can be referenced in user criteria within policies. Users can be filtered by name and the

`name`

search parameter performs a partial match. Administrators can be optionally included in the list by using the

`includeAdminUsers`

parameter.

- Operation ID: `UserZResource_getUserReferences`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `name` | query | no | object | Filters by user name |
| `includeAdminUsers` | query | no | object | Specifies whether to include the administrator users when retrieving the list |
| `page` | query | no | object | Specifies the page offset |
| `pageSize` | query | no | object | Specifies the page size. The default size is 1 and the maximum size is 100. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /users/{userId}`

Gets the user information for the specified ID.

- Operation ID: `getUser`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `userId` | path | yes | object | The unique identifer for the user. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /users/{userId}`

Updates the user information for the specified ID. However, the `email` attribute is read-only.

- Operation ID: `updateUser`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `userId` | path | yes | object | The unique identifer for the user. |
| `user` | body | no | User | User information. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /users/{userId}`

Deletes the user for the specified ID.

- Operation ID: `deleteUser`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `userId` | path | yes | object | The unique identifer for the user. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /users/auditors`

Gets a list of auditors. To learn more, see

[About Auditors](https://help.zscaler.com/zia/about-auditors)

.

- Operation ID: `UserResource_getAuditors`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/user-portal-link-management","lastmod":"2026-01-06T23:49Z","nid":"1532095"} -->
## User Portal Link Management

- Source: https://help.zscaler.com/legacy-apis/user-portal-link-management
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > User Portal Link Management
- Last modified: 2026-01-06T23:49Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/admin/customers/{customerId}/userPortalLink`

**Gets all configured user portal links for the specified customer.**

- Operation ID: `getAll_17`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The ZPA tenant ID of the customer. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The maximum page size is 500. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 200 | OK |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/userPortalLink`

**Adds a new user portal link for the specified ID.**

- Operation ID: `addUserPortalLink_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The ZPA tenant ID of the customer. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → UserPortalLink

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 201 | Created |

### `POST /mgmtconfig/v2/admin/customers/{customerId}/userPortalLink/bulk`

**Adds a list of user portal links for the specified ID.**

- Operation ID: `addListOfPortalLinks`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The ZPA tenant ID of the customer. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → UserPortalLinkBulkDTO

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 201 | Created |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/userPortalLink/userPortal/{portalId}`

**Gets user portal link details for the specified user portal ID.**

- Operation ID: `getUserPortalLinksByPortalId_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The ZPA tenant ID of the customer. |
| `portalId` | path | yes | integer(int64) | The unique identifier of the user portal. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The maximum page size is 500. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 200 | OK |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/userPortalLink/{id}`

**Gets details for a particular user portal link for the specfied ID.**

- Operation ID: `getUserPortalLink_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The ZPA tenant ID of the customer. |
| `id` | path | yes | integer(int64) | The unique identifier of the user portal link. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 200 | OK |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/userPortalLink/{id}`

**Updates the user portal link for the specified ID.**

- Operation ID: `updateUserPortalLink_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The ZPA tenant ID of the customer. |
| `id` | path | yes | integer(int64) | The unique identifier of the user portal link. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → UserPortalLink

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 204 | No Content |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/userPortalLink/{id}`

**Deletes the user portal link for the specified ID.**

- Operation ID: `deleteUserPortalLink_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The ZPA tenant ID of the customer. |
| `id` | path | yes | integer(int64) | The unique identifier of the user portal link. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 204 | No Content |

### Schemas

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`PageListUserPortalLink`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<UserPortalLink> | no |

**`UserPortalLink`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | no |
| `description` | string | no |
| `userPortalId` | integer(int64) | no |
| `link` | string | no |
| `iconText` | string | no |
| `protocol` | string | no |
| `linkPath` | string | no |
| `applicationId` | integer(int64) | no |
| `userPortals` | array<UserPortal> | no |

**`UserPortalLinkBulkDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `userPortalLinks` | array<UserPortalLinkBase> | yes |
| `userPortals` | array<UserPortal> | no |

**`UserPortal`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | no |
| `description` | string | no |
| `certificateId` | integer(int64) | no |
| `certificateName` | string | no |
| `getcName` | string | no |
| `domain` | string | no |
| `imageData` | string | no |
| `userNotification` | string | no |
| `userNotificationEnabled` | boolean | no |
| `inconsistentConfigDetails` | InconsistentConfigDetails | no |
| `extDomainTranslation` | string | no |
| `extLabel` | string | no |
| `extDomain` | string | no |
| `extDomainName` | string | no |

**`InconsistentConfigDetails`**

| Property | Type | Required |
| --- | --- | --- |
| `application` | array<InconsistentConfigNameReasonDto> | no |
| `sraApplication` | array<InconsistentConfigNameReasonDto> | no |
| `segmentGroup` | array<InconsistentConfigNameReasonDto> | no |
| `serverGroup` | array<InconsistentConfigNameReasonDto> | no |
| `appConnectorGroup` | array<InconsistentConfigNameReasonDto> | no |
| `baCertificate` | array<InconsistentConfigNameReasonDto> | no |
| `postureProfile` | array<InconsistentConfigNameReasonDto> | no |
| `trustedNetwork` | array<InconsistentConfigNameReasonDto> | no |
| `branchConnectorGroup` | array<InconsistentConfigNameReasonDto> | no |
| `cloudConnectorGroup` | array<InconsistentConfigNameReasonDto> | no |
| `samlAttributes` | array<InconsistentConfigNameReasonDto> | no |
| `scimAttributes` | array<InconsistentConfigNameReasonDto> | no |
| `machineGroup` | array<InconsistentConfigNameReasonDto> | no |
| `idp` | array<InconsistentConfigNameReasonDto> | no |
| `location` | array<InconsistentConfigNameReasonDto> | no |
| `workloadTagGroup` | array<InconsistentConfigNameReasonDto> | no |
| `userPortal` | array<InconsistentConfigNameReasonDto> | no |

**`InconsistentConfigNameReasonDto`**

| Property | Type | Required |
| --- | --- | --- |
| `name` | string | no |
| `reason` | string | no |

**`UserPortalLinkBase`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | no |
| `description` | string | no |
| `userPortalId` | integer(int64) | no |
| `link` | string | no |
| `iconText` | string | no |
| `protocol` | string | no |
| `linkPath` | string | no |
| `applicationId` | integer(int64) | no |
| `userPortals` | array<UserPortal> | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/user-portal-management","lastmod":"2026-08-03T10:08Z","nid":"1532093"} -->
## User Portal Management

- Source: https://help.zscaler.com/legacy-apis/user-portal-management
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > User Portal Management
- Last modified: 2026-08-03T10:08Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/admin/customers/{customerId}/userportal/aup/{id}`

Gets an acceptable use policy (AUP) for a specified customer.

- Operation ID: `getAUP_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `id` | path | yes | integer(int64) | The ID of the acceptable use policy (AUP). |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/userportal/aup/{id}`

Update the acceptable use policy (AUP) for a specified customer.

- Operation ID: `updateAUP_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `id` | path | yes | integer(int64) | The ID of the acceptable use policy (AUP). |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → UserPortalAUP

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/userportal/aup/{id}`

Delete the acceptable use policy (AUP) for a specified customer.

- Operation ID: `deleteAUP_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `id` | path | yes | integer(int64) | The ID of the acceptable use policy (AUP). |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/userportal/aup`

Gets all the acceptable use policies for a specified customer.

- Operation ID: `getAllAUPs_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/userportal/aup`

Add an acceptable use policy (AUP) for a specified customer.

- Operation ID: `addAUP_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → UserPortalAUP

**Responses:**

| Code | Description |
| --- | --- |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/userPortal/{id}`

Gets the user portal details for the specified ID.

- Operation ID: `getUserPortal_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The ZPA tenant ID of the customer. |
| `id` | path | yes | integer(int64) | The unique identifier of the user portal. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/userPortal/{id}`

Updates the user portal for the specified ID.

- Operation ID: `updateUserPortal_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The ZPA tenant ID of the customer. |
| `id` | path | yes | integer(int64) | The unique identifier of the user portal. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → UserPortal

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/userPortal/{id}`

Deletes the user portal for the specified ID.

- Operation ID: `deleteUserPortal_1`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The ZPA tenant ID of the customer. |
| `id` | path | yes | integer(int64) | The unique identifier of the user portal. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/userPortal`

Gets details of all configured user portals for the specified customer.

- Operation ID: `getAll_19`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The ZPA tenant ID of the customer. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The maximum page size is 500. |
| `uiConfig` | query | no | string |  |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/userPortal`

Adds a new user portal for the specified customer.

- Operation ID: `add_3`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The ZPA tenant ID of the customer. |
| `microtenantId` | query | no | integer(int64) | The unique identifier of the Microtenant for the ZPA tenant. If you are within a Microtenant, you must pass the microtenantId field when making an API call to retrieve data from that Microtenant. The microtenantId can be obtained from the API Keys page, or can be obtained programmatically using the ZPA cloud service API. Access to certain operations is limited when you are within a Microtenant. If you are within the Default Microtenant, pass microtenantId as 0 when making requests to retrieve data from the Default Microtenant. If the microtenantId is not passed in the request when creating or updating a resource, then the resource is created or updated in the Default Microtenant. |

**Request body:** `application/json` → UserPortal

**Responses:**

| Code | Description |
| --- | --- |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`UserPortalAUP`**

| Property | Type | Required |
| --- | --- | --- |
| `aup` | string | no |
| `creationTime` | integer(int32) | no |
| `description` | string | no |
| `email` | string | no |
| `enabled` | boolean | no |
| `id` | integer(int64) | no |
| `modifiedBy` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `name` | string | yes |
| `nameWithoutTrim` | string | no |
| `phoneNum` | string | no |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`PageListUserPortalAUP`**

| Property | Type | Required |
| --- | --- | --- |
| `currentCount` | integer(int64) | no |
| `list` | array<UserPortalAUP> | no |
| `message` | string | no |
| `totalCount` | integer(int64) | no |
| `totalPages` | integer(int32) | no |

**`UserPortal`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `microtenantId` | integer(int64) | no |
| `microtenantName` | string | no |
| `enabled` | boolean | no |
| `description` | string | no |
| `certificateId` | integer(int64) | no |
| `certificateName` | string | no |
| `getcName` | string | no |
| `domain` | string | no |
| `imageData` | string | no |
| `userNotification` | string | no |
| `userNotificationEnabled` | boolean | no |
| `inconsistentConfigDetails` | InconsistentConfigDetails | no |
| `extDomainTranslation` | string | no |
| `extLabel` | string | no |
| `extDomain` | string | no |
| `extDomainName` | string | no |

**`PageListUserPortal`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<UserPortal> | no |

**`InconsistentConfigDetails`**

| Property | Type | Required |
| --- | --- | --- |
| `application` | array<InconsistentConfigNameReasonDto> | no |
| `sraApplication` | array<InconsistentConfigNameReasonDto> | no |
| `segmentGroup` | array<InconsistentConfigNameReasonDto> | no |
| `serverGroup` | array<InconsistentConfigNameReasonDto> | no |
| `appConnectorGroup` | array<InconsistentConfigNameReasonDto> | no |
| `baCertificate` | array<InconsistentConfigNameReasonDto> | no |
| `postureProfile` | array<InconsistentConfigNameReasonDto> | no |
| `trustedNetwork` | array<InconsistentConfigNameReasonDto> | no |
| `branchConnectorGroup` | array<InconsistentConfigNameReasonDto> | no |
| `cloudConnectorGroup` | array<InconsistentConfigNameReasonDto> | no |
| `samlAttributes` | array<InconsistentConfigNameReasonDto> | no |
| `scimAttributes` | array<InconsistentConfigNameReasonDto> | no |
| `machineGroup` | array<InconsistentConfigNameReasonDto> | no |
| `idp` | array<InconsistentConfigNameReasonDto> | no |
| `location` | array<InconsistentConfigNameReasonDto> | no |
| `workloadTagGroup` | array<InconsistentConfigNameReasonDto> | no |
| `userPortal` | array<InconsistentConfigNameReasonDto> | no |

**`InconsistentConfigNameReasonDto`**

| Property | Type | Required |
| --- | --- | --- |
| `name` | string | no |
| `reason` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/users","lastmod":"2026-07-27T07:06Z","nid":"1529240"} -->
## Users

- Source: https://help.zscaler.com/legacy-apis/users
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > Authentication Service API > API Developer & Reference Guide > Reference Guide > Users
- Last modified: 2026-07-27T07:06Z

**Servers:** `https://api.zsapi.net/ziam/admin/api/v1`

### `GET /users`

Retrieves a list of users with optional query parameters for pagination and filtering. To learn more, see

[About Users](https://help.zscaler.com/zidentity/about-users)

.

- Operation ID: `UsersOps_list`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `offset` | query | no | integer(int32) | The starting point for pagination, with the number of records that can be skipped before fetching results. |
| `limit` | query | no | integer(int32) | The maximum number of records to return per request. Minimum: `0`, Maximum: `1000` |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |
| `` |  | no | object |  |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | The request was successful, and a paginated list of users are returned. |
| 401 | Authentication failed or missing authorization credentials. The request requires a valid Bearer Token. However, either no token was provided, or the provided token is invalid or expired. |

### `POST /users`

Creates a new user using the provided details.

- Operation ID: `UsersOps_add`

**Request body:** `application/json` → object

**Responses:**

| Code | Description |
| --- | --- |
| 202 | The request has been accepted for processing, but processing has not yet completed. |
| 401 | Authentication failed or missing authorization credentials. The request requires a valid Bearer Token. However, either no token was provided, or the provided token is invalid or expired. |

### `POST /users:bulkdelete`

Deletes multiple users in a single request.

**Note:** A maximum of 1,000 users can be deleted in a single API request. After deletion, the users cannot be recovered.

- Operation ID: `UsersOps_bulkDelete`

**Request body:** `application/json` → array<RefPubReqDto>

**Responses:**

| Code | Description |
| --- | --- |
| 200 | The request was successful, and the users have been deleted. |
| 400 | The request contains invalid syntax or missing required fields. |
| 401 | Authentication failed or missing authorization credentials. The request requires a valid Bearer Token. However, either no token was provided, or the provided token is invalid or expired. |

### `PUT /users/{id}`

Updates the details of an existing user based on the provided user

`ID`

.

- Operation ID: `UsersOps_update`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | string | Unique identifier of the user that needs to be updated. |

**Request body:** `application/json` → object

**Responses:**

| Code | Description |
| --- | --- |
| 200 | The request was successful, and the user details are updated. |
| 500 | Unexpected server issue occurred while processing the request. |

### `DELETE /users/{id}`

Deletes an existing user from the system by the provided user

`ID`

.

- Operation ID: `UsersOps_remove`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | string | Unique identifier of the user to be deleted. |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | The user was successfully deleted. No content is returned in the response, but headers may be useful for validation purposes. |
| 401 | Authentication failed or missing authorization credentials. The request requires a valid Bearer Token. However, either no token was provided, or the provided token is invalid or expired. |

### `GET /users/{id}`

Retrieves detailed information about a specific user using the provided user

`ID`

.

- Operation ID: `UsersOps_get`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | string | Unique identifier of the user to retrieve. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | The request was successful, and the user details are returned. |
| 401 | Authentication failed or missing authorization credentials. The request requires a valid Bearer Token. However, either no token was provided, or the provided token is invalid or expired. |

### `GET /users/{id}/admin-entitlements`

Retrieves the administrative entitlements for a specific user by their user

`ID`

.

- Operation ID: `UsersOps_getUserAdminEntitlements`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | string | The user `ID` of the individual whose admin entitlements are being retrieved. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | The request was successful, and returns a list of administrative entitlement objects. |
| 401 | Authentication failed or missing authorization credentials. The request requires a valid Bearer Token. However, either no token was provided, or the provided token is invalid or expired. |

### `GET /users/{id}/groups`

Retrieves a paginated list of groups associated with a specific user

`ID`

.

- Operation ID: `UsersOps_getUserGroups`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `offset` | query | no | integer(int32) | The starting point for pagination, with the number of records that can be skipped before fetching results. |
| `limit` | query | no | integer(int32) | The maximum number of records to return per request. Minimum: `0`, Maximum: `1000` |
| `id` | path | yes | string | The user `ID` of the individual whose groups details are to be retrieved. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | The request was successful, and returns a paginated list of groups for the specified user. |
| 401 | Authentication failed or missing authorization credentials. The request requires a valid Bearer Token. However, either no token was provided, or the provided token is invalid or expired. |

### `GET /users/{id}/service-entitlements`

Retrieves service entitlements for a specified user

`ID`

.

- Operation ID: `UsersOps_getUserServiceEntitlements`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | string | The user `ID` of the individual for whom the service entitlements are to be retrieved. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | The request was successful, and returns the service entitlements for the user. |
| 401 | Authentication failed or missing authorization credentials. The request requires a valid Bearer Token. However, either no token was provided, or the provided token is invalid or expired. |

### `POST /users/{id}:resetpassword`

Initiates a password reset for a specific user

`ID`

- Operation ID: `UsersOps_resetPassword`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | string | The user `ID` of the user for whom the password is to be reset. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | The password reset request has been initiated successfully. |
| 400 | The request could not be processed due to invalid syntax or missing required fields. |

### `POST /users/{id}:setskipmfa`

Sets a flag to skip Multi-Factor Authentication (MFA) for a specified user

`ID`

.

- Operation ID: `UsersOps_mfa`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | string | The user `ID` of the individual for whom the MFA needs to be skipped. |

**Request body:** `application/json` → SkipMfaDto

**Responses:**

| Code | Description |
| --- | --- |
| 200 | The request was successful, and MFA skip has been set. |
| 401 | Authentication failed or missing authorization credentials. The request requires a valid Bearer Token. However, either no token was provided, or the provided token is invalid or expired. |

### `PUT /users/{id}:updatepassword`

Updates the password for a specific user

`ID`

. Optionally, a boolean flag can be set to require the user to reset their password during next login.

- Operation ID: `UsersOps_updatePassword`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | string | The user `ID` of the individual for whom the password needs to be updated. |

**Request body:** `application/json` → UpdatePasswordRequestDto

**Responses:**

| Code | Description |
| --- | --- |
| 200 | The request was successful, and the password has been reset. |
| 400 | The request could not be processed due to invalid syntax or missing required fields. |

### Schemas

**`UserProfilePubRespDto`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | string | yes |
| `source` | object | yes |
| `idp` | object | yes |

**`Zscaler.Core.UnauthorizedError`**

| Property | Type | Required |
| --- | --- | --- |
| `type` | string(uri) | yes |
| `title` | string | yes |
| `status` | integer(int32) | yes |
| `detail` | string | yes |

**`RefPubReqDto`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | string | yes |

**`Zscaler.Core.ValidationError`**

| Property | Type | Required |
| --- | --- | --- |
| `type` | string(uri) | yes |
| `title` | string | yes |
| `status` | integer(int32) | yes |
| `detail` | string | yes |

**`Zscaler.Core.InternalServerError`**

| Property | Type | Required |
| --- | --- | --- |
| `type` | string(uri) | yes |
| `title` | string | yes |
| `status` | integer(int32) | yes |
| `detail` | string | yes |

**`AdminEntitlementsPubRespDto`**

| Property | Type | Required |
| --- | --- | --- |
| `roles` | array<RefPubRespDto> | yes |
| `scope` | RefPubRespDto | yes |
| `service` | TserviceProfileRespDto | yes |

**`GroupProfilePubRespDto`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | string | yes |
| `source` | object | yes |
| `idp` | object | yes |
| `isDynamicGroup` | boolean | yes |
| `adminEntitlementEnabled` | boolean | yes |
| `serviceEntitlementEnabled` | boolean | yes |

**`ServiceEntitlementsPubRespDto`**

| Property | Type | Required |
| --- | --- | --- |
| `service` | TserviceProfileRespDto | yes |

**`SkipMfaDto`**

| Property | Type | Required |
| --- | --- | --- |
| `timestamp` | integer(int32) | yes |

**`UpdatePasswordRequestDto`**

| Property | Type | Required |
| --- | --- | --- |
| `resetPwdOnLogin` | boolean | no |
| `password` | string | yes |

- `SourceType`: string (enum: UI, API, SCIM, JIT)

**`RefPubRespDto`**

| Property | Type | Required |
| --- | --- | --- |
| `name` | string | yes |
| `displayName` | string | yes |

**`TserviceProfileRespDto`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | string | yes |
| `serviceName` | string | yes |
| `cloudName` | string | yes |
| `cloudDomainName` | string | yes |
| `orgName` | string | yes |
| `orgId` | string | yes |

**`GroupProfilePubReqDto`**

| Property | Type | Required |
| --- | --- | --- |
| `name` | string | yes |
| `description` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/using-zscaler-microsegmentation-api","lastmod":"2026-04-28T07:06Z","nid":"1532646"} -->
## Using Zscaler Microsegmentation API

- Source: https://help.zscaler.com/legacy-apis/using-zscaler-microsegmentation-api
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > Microsegmentation API > Using Zscaler Microsegmentation API
- Last modified: 2026-04-28T07:06Z
- Summary: How to access the Microsegmentation API via the OneAPI GraphQL gateway.

The Microsegmentation API is available via the GraphQL API endpoint accessible via the OneAPI Gateway. A single endpoint allows clients to query multiple data domains relevant to Microsegmentation (e.g., agents, agent groups, versions, connection status, metadata).

### Prerequisites

To use the Microsegmentation API, ensure the following:

- [Create an API client](https://help.zscaler.com/zidentity/adding-api-client) with the appropriate role that can access the Microsegmentation APIs.
- Enable and provision [Microsegmentation](https://help.zscaler.com/zpa/what-is-microsegmentation) in your Zscaler Private Access (ZPA) tenant.

### Endpoints

Use the following endpoint URL based on your cloud environment:

- **Production**: `https://api.zsapi.net/zms/graphql`
- **Beta**: `https://api.beta.zsapi.net/zms/graphql`

### Setting Up Authentication in Postman

Zscaler recommends referring to the [Postman GraphQL Request Guide](https://learning.postman.com/docs/sending-requests/graphql/graphql-client-interface/) before making GraphQL requests.

To configure authentication for the Microsegmentation API:

1. In Postman, select **New** > **GraphQL** to open a request in a new tab. In the Postman desktop app, you can also select ⌘+N or Ctrl+N, then select **GraphQL.**
2. Paste the appropriate **OneAPI API Endpoint** (see Endpoints) in the **URL** field.
3. Select the **Authorization** tab.
  1. **Auth Type**: Select **OAuth 2.0**.
  2. **Add authorization data to**: Select **Request Headers**.
  3. Under **Configure New Token**, enter token details:
    1. **Token Name**: Enter any name for the token.
    2. **Grant Type**: Select **Client Credentials** from the drop-down menu.
    3. **Access Token URL**: Enter the Authentication Service access token URL, `<Authentication Service tenant domain>/oauth2/v1/token>`. For example, `https://acme.zsloginbeta.net/oauth2/v1/token`
    4. **Client ID**: Enter the Client ID from API client creation.
    5. **Client Secret**: Enter the Client Secret from API client creation.
    6. **Scope**: Enter the multiple scopes of access separated by a space.
    7. **Client Authentication**: Send as Basic Auth in header or Send client credentials in body.
  4. From the **Advanced** drop-down menu, add an audience key for the token request:
    1. **Key**: Select **audience**.
    2. **Value**: Enter `https://api.zscaler.com`.
    3. **Send in**: Select **Request Body**.
  5. Click **Get New Access Token** to verify that the setup was successful.

If you successfully retrieve an access token, authentication with Authentication Service has been set up correctly.

### Obtaining the API Schema

The GraphQL API schema is generally self-documenting and can be loaded into your GraphQL client to enable query validation.

#### Using Postman Introspection (Recommended)

The preferred method is to utilize the built-in introspection feature within the Postman client to retrieve the schema, as it automatically uses the preconfigured authentication credentials.

After you have configured the endpoint URL and authentication as described in the earlier section, the Postman can use these credentials to automatically fetch the schema.

To obtain the schema using introspection:

1. In your Postman GraphQL request, select the **Schema** tab.
2. Select the option to **Use GraphQL Introspection**.

The Postman loads the schema in the schema explorer and enables query auto-completion.

#### Alternative Method

Alternatively, you can fetch or download the schema directly.

These methods require a valid authorization header in the request, else the request would fail with a `403 Forbidden` error.

To get the schema manually, use any of the following methods:

- Fetch the schema with a GET request: [`https://api.zsapi.net/zms/graphql/supergraph-schema`](https://api.zsapi.net/zms/graphql/supergraph-schema)
- Download the schema to your local system: `https://api.zsapi.net/zms/graphql/supergraph-schema?download=true`

After obtaining the schema, import it into a GraphQL client such as [Postman](https://learning.postman.com/docs/sending-requests/graphql/graphql-client-interface/) or [Insomnia](https://konghq.com/blog/engineering/insomnia-graphql#:~:text=Working%20With%20Insomnia%20GraphQL) to explore the API. This populates the schema explorer of the GraphQL client, allowing you to easily build and validate queries.

### Setting Up the User-Agent Header

You are required to set the User-Agent header in this format:

`PartnerName/AppName/Version (Device; OS Version) Region=COUNTRY`

For example, `ACME/acme-insights-app/1.0.1 (iPhone; iOS 15.4) Region=US`

### Creating GraphQL Queries (Examples)

After importing the schema, you can build queries. Postman allows you to create a GraphQL query visually by selecting the checkboxes and providing appropriate values in the text inputs, as well as selecting fields to be present in the response.

#### Example: Creating a Simple Query

To create a query for fetching page 2 of agent group results with up to 10 records per page, for customer ID `97531` that matches the search term `doc`, ordered by `createdDate` in ascending order with the group description, admin region, and admin status:

1. Select **AgentGroups**.
2. Set the following arguments values in the query:
  - customerId: `97531`
  - page: `2`
  - pageSize: `10`
  - search: `doc`
  - sort: `createdDate`
  - sortDir: `ASC`
3. GraphQL allows selecting the exact fields that should be part of the response. Select an entry and the fields that must be part of the entry. For example, select **description**, **adminRegion**, and **adminStatus**.
4. Limit the number of returned nodes using **pageSize** and **pageNumber**.
5. Click **Query** next to the URL field to make the request and fetch the response, which you can see in the response tab.

See image.

#### Example: Creating a Multi-Domain Query

Paste the following snippet into the panel on the right on the **Query** tab. The request contains multiple queries from `agentGroups`, `agentVersionStatistics`, `agents`, and `policyRules` in a single request.

- Sample multi-domain query format

Click **Query** at the top-right of the query panel to make a request. The response appears in the **Response** panel. Click **Response** to view it.

See image.

```
query AgentGroups {
    agentGroups(customerId: "97531", page: 18) {
        nodes {
            adminStatus
            adminVPC
            agentCount
        }
    }
    agentVersionStatistics(customerId: "97531") {
        agentVersions {
            version
        }
    }
    agents(customerId: "97531", page: 18) {
        nodes {
            agentGroupAdminStatus
            agentGroupName
        }
    }
    policyRules(customerId: "97531") {
        nodes {
            action
            creationTime
            appZoneScopes {
                appZoneName
            }
        }
    }
}
```

[Image: Postman showing the GraphQL Query for AgentGroups with nodes and pageInfo.]

[Image: Postman showing the GraphQL Query for AgentGroups with nodes and pageInfo.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/version-profiles","lastmod":"2026-07-30T17:28Z","nid":"1485271"} -->
## Version Profiles

- Source: https://help.zscaler.com/legacy-apis/version-profiles
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Version Profiles
- Last modified: 2026-07-30T17:28Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/admin/customers/{customerId}/versionProfile`

Gets the version profile for the specified customer.

- Operation ID: `getAssociatedVersionProfileByCustomerId`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/versionProfiles/{versionProfileId}`

Updates the version profile for the specified customer.

- Operation ID: `updateVersionProfileForCustomer`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `versionProfileId` | path | yes | integer(int64) | The ID of the version profile. |

**Request body:** `application/json` → UpdateCustomerVersionProfileDto

**Responses:**

| Code | Description |
| --- | --- |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/visible/versionProfiles`

Gets all visible version profiles for the specified customer.

- Operation ID: `getAllVersionProfilesVisibileByCustomerId`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The maximum page size is 500. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`VersionProfileDto`**

| Property | Type | Required |
| --- | --- | --- |
| `enabled` | boolean | no |
| `id` | integer(int64) | no |
| `name` | string | no |
| `selectedUpgradePriority` | string (enum: WEEK, DAY, FORCE_NOW, NOW) | no |
| `upgradePriorities` | array<string (enum: WEEK, DAY, FORCE_NOW, NOW)> | no |
| `upgradePriority` | string (enum: WEEK, DAY, FORCE_NOW, NOW) | no |
| `versionDetails` | array<VersionDTO> | no |
| `visibilityScope` | string (enum: ALL, NONE, CUSTOM) | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`UpdateCustomerVersionProfileDto`**

| Property | Type | Required |
| --- | --- | --- |
| `removeOverrideFlag` | boolean | no |
| `selectedUpgradePriority` | string (enum: WEEK, DAY, FORCE_NOW, NOW) | no |

**`PageListVersionProfile`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<VersionProfile> | no |

**`VersionDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `latestPlatform` | string | no |
| `role` | string | no |
| `version` | The version. | no |

**`VersionProfile`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `name` | string | yes |
| `description` | string | no |
| `upgradePriority` | string (enum: WEEK, DAY, FORCE_NOW, NOW) | yes |
| `visibilityScope` | string (enum: ALL, NONE, CUSTOM) | no |
| `versions` | array<Version> | no |
| `customScopeCustomerIds` | array<CustomerIdNameDTO> | no |
| `customScopeRequestCustomerIds` | CustomScopeRequestCustomerIds | no |
| `numberOfAssistants` | integer(int64) | no |
| `numberOfPrivateBrokers` | integer(int64) | no |
| `numberOfUpdatedAssistants` | integer(int64) | no |
| `numberOfUpdatedPrivateBrokers` | integer(int64) | no |
| `numberOfSiteControllers` | integer(int64) | no |
| `numberOfUpdatedSiteControllers` | integer(int64) | no |
| `numberOfCustomers` | integer(int64) | no |
| `customerId` | integer(int64) | no |

**`Version`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `version_profile_gid` | integer(int64) | yes |
| `role` | string | yes |
| `platform` | string | yes |
| `version` | string | yes |
| `restartAfterUptimeInDays` | integer(int32) | no |
| `upgradeOrder` | integer(int32) | no |
| `upgradeWithoutRestart` | boolean | no |
| `customerId` | integer(int64) | no |

**`CustomerIdNameDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `customerId` | integer(int64) | no |
| `name` | string | no |
| `excludeConstellation` | boolean | no |
| `isPartner` | boolean | no |

**`CustomScopeRequestCustomerIds`**

| Property | Type | Required |
| --- | --- | --- |
| `addCustomerIds` | array<integer(int64)> | no |
| `deleteCustomerIds` | array<integer(int64)> | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/votiro-cdr","lastmod":"2026-07-09T07:06Z","nid":"1540761"} -->
## Votiro CDR

- Source: https://help.zscaler.com/legacy-apis/votiro-cdr
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > Votiro CDR
- Last modified: 2026-07-09T07:06Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /isolationVotiroCdr/policies`

**Retrieves Votiro CDR policies configured for a tenant. To learn more, see <a href="/zscaler-technology-partners/zscaler-and-votiro-deployment-guide" target="_blank">Zscaler and Votiro Deployment Guide</a>.**

- Operation ID: `IsolationVotiroCdrResource_getVotiroCdrPolicyList`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `GET /isolationVotiroCdr/tokenConfig`

Retrieves the Votiro CDR authentication credentials. To learn more, see

[Understanding Votiro Integration for Zero Trust Browser](https://help.zscaler.com/zscaler-technology-partners/understanding-votiro-integration-zero-trust-browser)

.

- Operation ID: `IsolationVotiroCdrResource_getVotiroTokenConfig`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `PUT /isolationVotiroCdr/tokenConfig`

Configures or updates the Votiro CDR authentication credentials. To learn more, see

**Votiro CDR Integration**

on

[Creating Isolation Profiles for ZIA](https://help.zscaler.com/zero-trust-browser/creating-isolation-profiles-zia)

.

- Operation ID: `IsolationVotiroCdrResource_updateVotiroTokenConfig`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `action` | query | no | object | Updates either the **token** or **hostname**. When you set the **token** or **hostname**, only the respective field in the **VotiroCdrTokenConfig** is updated. |
| `body` | body | no | VotiroCdrTokenConfig | Votiro CDR token configuration details |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |

### `DELETE /isolationVotiroCdr/tokenConfig`

Deletes the Votiro CDR authentication credentials

- Operation ID: `IsolationVotiroCdrResource_deleteVotiroTokenConfig`

**Responses:**

| Code | Description |
| --- | --- |
| default | successful operation |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/vpn-legacy-apps-api","lastmod":"2026-01-06T23:49Z","nid":"1532102"} -->
## VPN (for Legacy Apps) API

- Source: https://help.zscaler.com/legacy-apis/vpn-legacy-apps-api
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > VPN (for Legacy Apps) API
- Last modified: 2026-01-06T23:49Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/admin/customers/{customerId}/vpnConnectedUsers`

**Gets all users connected to the VPN Service Edges for the specified customer.**

- Operation ID: `getAllNpClients`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The ZPA tenant ID of the customer. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The maximum page size is 500. |

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 200 | OK |

### Schemas

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`PageListNpClient`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<NpClient> | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/workload-groups","lastmod":"2026-04-28T07:06Z","nid":"1467836"} -->
## Workload Groups

- Source: https://help.zscaler.com/legacy-apis/workload-groups
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > API Developer & Reference Guide > Reference Guide > Workload Groups
- Last modified: 2026-04-28T07:06Z

API Reference Guide for the ZIA Cloud Service and Sandbox Submission APIs

### `GET /workloadGroups`

Retrieves the list of workload groups configured in the ZIA Admin Portal. To learn more, see

[About Workload Groups](https://help.zscaler.com/zia/about-workload-groups)

.

- Operation ID: `WorkloadGroupResource_getWorkloadGroups`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | object | Specifies the page offset |
| `pageSize` | query | no | object | Specifies the page size. The default size is 250 and the maximum size is 1,000. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 | Unauthorized |
| 403 | Forbidden |
| 500 | Internal server error |

### `POST /workloadGroups`

Adds a workload group for an organization. To learn more, see

[Configuring Workload Groups](https://help.zscaler.com/zia/configuring-workload-groups)

.

- Operation ID: `WorkloadGroupResource_addWorkloadGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `body` | body | no | WorkloadGroup | Workload group information |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 400 | Invalid |
| 401 | Unauthorized |
| 403 | Forbidden |
| 500 | Internal Server Error |

### `DELETE /workloadGroups`

Deletes a list of workload groups for an organization

- Operation ID: `WorkloadGroupResource_deleteWorkloadGroups`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `ids` | query | no | object | Filters the workload groups to be deleted based on the IDs |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | Successful Operation |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 500 | Internal Server Error |

### `GET /workloadGroups/{workloadGroupId}`

Retrieves the workload group based on the specified ID

- Operation ID: `WorkloadGroupResource_getWorkloadGroupById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `workloadGroupId` | path | yes | object | The workload group ID |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 500 | Internal Server Error |

### `PUT /workloadGroups/{workloadGroupId}`

Updates the workload group for an organization based on the specified ID

- Operation ID: `WorkloadGroupResource_updateWorkloadGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `workloadGroupId` | path | yes | object | The workload group ID |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation |
| 400 | Invalid |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 500 | Internal Server Error |

### `DELETE /workloadGroups/{workloadGroupId}`

Deletes the workload group based on the specified ID

- Operation ID: `WorkloadGroupResource_deleteWorkloadGroup`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `workloadGroupId` | path | yes | object | The workload group ID |

**Responses:**

| Code | Description |
| --- | --- |
| 204 | Workload Group deleted successfully |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 500 | Internal Server Error |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/workload-groups-0","lastmod":"2026-01-27T22:34Z","nid":"1535257"} -->
## Workload Groups

- Source: https://help.zscaler.com/legacy-apis/workload-groups-0
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > Zscaler Cloud & Branch Connector API > API Developer & Reference Guide > Reference Guide > Workload Groups
- Last modified: 2026-01-27T22:34Z

**Servers:** `https://{host}:{port}/wapi/v1`, `https://{host}:{port}/api/v1`

### `GET /workloadGroups`

Retrieves the list of workload groups.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `WorkloadGroupZResource_getWorkloadGroups`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. The default size is 250. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `GET /workloadGroups/lite`

Retrieves the list of workload groups.

**⚠ This API endpoint is accessible only via [OneAPI](https://help.zscaler.com/oneapi).**

- Operation ID: `WorkloadGroupZResource_getWorkloadGroupsLite`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `page` | query | no | integer(int32) | Specifies the page offset. |
| `pageSize` | query | no | integer(int32) | Specifies the page size. The default size is 20. |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### Schemas

**`WorkloadGroup`**

| Property | Type | Required |
| --- | --- | --- |
| `description` | string | no |
| `expression` | string | no |
| `expressionJson` | WorkloadTagExpression | no |
| `id` | integer(int32) | no |
| `lastModifiedBy` | EntityReference | no |
| `lastModifiedTime` | integer(int32) | no |
| `name` | string | no |

**`EntityResponse`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |

**`WorkloadTagExpression`**

| Property | Type | Required |
| --- | --- | --- |
| `expressionContainers` | array<ExpressionContainer> | no |

**`ExpressionContainer`**

| Property | Type | Required |
| --- | --- | --- |
| `operator` | string (enum: AND, OR, OPEN_PARENTHESES, CLOSE_PARENTHESES) | no |
| `tagContainer` | TagContainer | no |
| `tagType` | string (enum: ANY, VPC, SUBNET, VM, ENI, ATTR) | no |

**`TagContainer`**

| Property | Type | Required |
| --- | --- | --- |
| `operator` | string (enum: AND, OR, OPEN_PARENTHESES, CLOSE_PARENTHESES) | no |
| `tags` | array<Tag> | no |

**`Tag`**

| Property | Type | Required |
| --- | --- | --- |
| `key` | string | no |
| `value` | string | no |

**`EntityReference`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `name` | string | no |
| `isNameL10nTag` | boolean | no |
| `extensions` | object | no |
| `deleted` | boolean | no |
| `externalId` | string | no |
| `associationTime` | integer(int32) | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/workload-tags","lastmod":"2026-09-20T07:06Z","nid":"1542222"} -->
## Workload Tags

- Source: https://help.zscaler.com/legacy-apis/workload-tags
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Workload Tags
- Last modified: 2026-09-20T07:06Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/admin/customers/{customerId}/workloadTagGroup/summary`

Gets a summary of workload tag groups for the specified customer.

- Operation ID: `getAllNameIds`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `search` | query | no | string | The search string used to support search by features and fields for the API. |
| `page` | query | no | integer(int32) | Specifies the page number. |
| `pagesize` | query | no | integer(int32) | Specifies the page size. If not provided, the default page size is 20. The max page size is 500. |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`PageListNameIdDto`**

| Property | Type | Required |
| --- | --- | --- |
| `currentCount` | integer(int64) | no |
| `list` | array<NameIdDto> | no |
| `message` | string | no |
| `totalCount` | integer(int64) | no |
| `totalPages` | integer(int32) | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`NameIdDto`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `name` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/zdx-snapshots","lastmod":"2026-03-31T07:06Z","nid":"1529965"} -->
## ZDX Snapshots

- Source: https://help.zscaler.com/legacy-apis/zdx-snapshots
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZDX API > API Developer & Reference Guide > Reference Guide > ZDX Snapshots
- Last modified: 2026-03-31T07:06Z

APIs for Zscaler Digital Experience (ZDX).

**Servers:** `https://api.zdxcloud.net/v1`, `https://dev.zdxapi.zscaler.net/v1`, `http://localhost:8080/v1`

### `POST /snapshot/alert`

Share a ZDX Snapshot of alert details for a given alert ID.

**Request body:** `application/json` → object

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation. The response contains ID, name, URL, expiration, and status of the ZDX Snapshot created. |
| 400 |  |
| 401 |  |
| 403 |  |

### `POST /snapshot/user`

Share a ZDX Snapshot for a given user ID to monitor user details (i.e., device and application). The "from" timestamp must not exceed 14 days from the current time. The time duration between the "from" and "to" must be within 15 minutes to 48 hours.

**Request body:** `application/json` → object

**Responses:**

| Code | Description |
| --- | --- |
| 200 | Successful Operation. The response contains ID, name, URL, expiration, and status of the ZDX Snapshot created. |
| 400 |  |
| 401 |  |
| 403 |  |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/zero-trust-gateway","lastmod":"2026-09-08T22:26Z","nid":"1545353"} -->
## Zero Trust Gateway

- Source: https://help.zscaler.com/legacy-apis/zero-trust-gateway
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > Zscaler Cloud & Branch Connector API > API Developer & Reference Guide > Reference Guide > Zero Trust Gateway
- Last modified: 2026-09-08T22:26Z

**Servers:** `https://{host}:{port}/wapi/v1`, `https://{host}:{port}/api/v1`

### `GET /ztGateway`

Retrieve list of Zero Trust Gateways

- Operation ID: `ZTGatewayZResource_getZTGateways`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `platform` | query | no | string (enum: AWS, AZURE, GCP, CENTOS, REDHAT_LINUX, VMWARE_ESXI, MICROSOFT_HYPER_V) | Platform of Cloud or Branch Connector group |
| `search` | query | no | string | The search string used to match against the policies |
| `region` | query | no | string | The region where the ZT gateway is deployed |
| `page` | query | no | integer(int32) | Specifies the page offset |
| `pageSize` | query | no | integer(int32) | Specifies the page size |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `POST /ztGateway`

Creates a Zero Trust Gateway based on the specified parameters

- Operation ID: `ZTGatewayZResource_createZTGateway`

**Request body:** `application/json` → ZTGatewayRequest

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `PUT /ztGateway/analytic/metrics/{id}`

Updates the Zero Trust Gateway based on the specified ID

- Operation ID: `ZTGatewayZResource_getZTGatewayMetrics`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | Unique identifier of the Zero Trust Gateway |

**Request body:** `application/json` → ZTGatewayMetrics

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `GET /ztGateway/config/compare/{id}`

Retrieves the list of Zero Trust Gateways with Internet & SaaS configuration versions that have been activated

- Operation ID: `ZTGatewayZResource_getZTGatewayConfigComparison`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | Unique identifier of the gateway |
| `action` | query | no | string |  |
| `version1` | query | no | string | The first version of the configuration selected for comparison |
| `version2` | query | no | string | The second version of the configuration selected for comparison |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `GET /ztGateway/config/status/{id}`

Retrieves the Zero Trust gateway configuration status based on the gateway ID

- Operation ID: `ZTGatewayZResource_getZTGatewayConfigStatus`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | Unique identifier of the Zero Trust gateway |
| `action` | query | no | string | The actions available for ZT gateway (Status and compare) |
| `size` | query | no | string | The number of configurations returned per page |
| `token` | query | no | string | Pagination cursor passed in the subsequent request when additional configs are available |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `GET /ztGateway/count`

Retrieves the total number of existing Zero Trust Gateways per organization

- Operation ID: `ZTGatewayZResource_getZTGatewaysCount`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `platform` | query | no | string (enum: AWS, AZURE, GCP, CENTOS, REDHAT_LINUX, VMWARE_ESXI, MICROSOFT_HYPER_V) | The cloud service provider used to deploy the gateway |
| `search` | query | no | string | The search string used to match against the policies |
| `region` | query | no | string | The region where the ZT gateway is deployed |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `GET /ztGateway/events/{id}`

Retrieves the list of events that occurred based on the ZT gateway resource ID

- Operation ID: `ZTGatewayZResource_getZTGatewayEvents`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | The ZT gateway resource ID |
| `size` | query | no | string | The number of events returned per page. The default is 25 |
| `ntoken` | query | no | string | The default is 25 |
| `begin` | query | no | string | Timestamp when the event started |
| `end` | query | no | string | Timestamp when the event ended |
| `category` | query | no | string | The category of the event |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `GET /ztGateway/licenseInfo`

Retrieves license details of ZT Gateway

- Operation ID: `ZTGatewayZResource_getZTGatewaysLicenseInfo`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `platform` | query | no | string (enum: AWS, AZURE, GCP, CENTOS, REDHAT_LINUX, VMWARE_ESXI, MICROSOFT_HYPER_V) | The cloud service provider used to deploy the gateway |

**Responses:**

| Code | Description |
| --- | --- |
| 200 | default response |

### `GET /ztGateway/lite`

Retrieves the list of ID and name of existing Zero Trust Gateways

- Operation ID: `ZTGatewayZResource_getZTGatewaysLite`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `platform` | query | no | string (enum: AWS, AZURE, GCP, CENTOS, REDHAT_LINUX, VMWARE_ESXI, MICROSOFT_HYPER_V) | The cloud service provider used to deploy the gateway |
| `search` | query | no | string | The search string used to match against the policies |
| `region` | query | no | string | The region where the ZT gateway is deployed |
| `page` | query | no | integer(int32) | Specifies the page offset |
| `pageSize` | query | no | integer(int32) | Specifies the page size |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `GET /ztGateway/status/{id}`

Retrieves the status of the ZT gateway based on the ID

- Operation ID: `ZTGatewayZResource_getZTGatewayStatus`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | The ZT gateway resource ID |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `GET /ztGateway/supportedRegions`

Retrieves the list of ZT gateway supported regions

- Operation ID: `ZTGatewayZResource_getZTGatewaySupportedRegions`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `platform` | query | no | string (enum: AWS, AZURE, GCP, CENTOS, REDHAT_LINUX, VMWARE_ESXI, MICROSOFT_HYPER_V) | The platform of Cloud or Branch Connector group |
| `search` | query | no | string | The search string used to match against the policies |
| `page` | query | no | integer(int32) | Specifies the page offset |
| `pageSize` | query | no | integer(int32) | Specifies the page size |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `GET /ztGateway/trafficTest`

Retrieves the list of traffic tests created

- Operation ID: `ZTGatewayZResource_getTrafficTests`

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `POST /ztGateway/trafficTest`

Create a traffic test environment

- Operation ID: `ZTGatewayZResource_createTrafficTest`

**Request body:** `application/json` → TrafficTest

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `DELETE /ztGateway/trafficTest/env/{id}`

Delete the traffic test based on the ID

- Operation ID: `ZTGatewayZResource_deleteTrafficTestEnvironment`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | The ID of the traffic test |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `GET /ztGateway/trafficTest/env/{id}`

Retrieve traffic test environment details based on the ZT gateway resource ID

- Operation ID: `ZTGatewayZResource_getTrafficTestEnvironment`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | The ZT gateway resource ID |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `POST /ztGateway/trafficTest/env/{id}`

Create a traffic test environment

- Operation ID: `ZTGatewayZResource_createTrafficTestEnvironment`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | The ZT gateway resource ID |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `DELETE /ztGateway/trafficTest/{testId}`

Delete the traffic test based on the traffic test ID

- Operation ID: `ZTGatewayZResource_deleteTrafficTest`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `testId` | path | yes | string | The traffic test ID |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `GET /ztGateway/trafficTest/{testId}`

Retrieve the traffic test details based on test ID

- Operation ID: `ZTGatewayZResource_getTrafficTestById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `testId` | path | yes | string | The traffic test ID |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `PUT /ztGateway/trafficTest/{testId}`

Update the traffic test based on the test ID

- Operation ID: `ZTGatewayZResource_updateTrafficTest`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `testId` | path | yes | string | The traffic test ID |

**Request body:** `application/json` → TrafficTest

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `POST /ztGateway/{gwId}/trafficTest/run`

Run a traffic test based on the gateway ID

- Operation ID: `ZTGatewayZResource_runTrafficTest`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `gwId` | path | yes | integer(int32) | The ZT gateway resource ID |

**Request body:** `application/json` → TrafficRunTest

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `DELETE /ztGateway/{id}`

Delete a ZT gateway based on the ZT gateway ID

- Operation ID: `ZTGatewayZResource_deleteZTGateway`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | The ZT gateway ID |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `GET /ztGateway/{id}`

Retrieves the ZT gateway details based on the gateway ID

- Operation ID: `ZTGatewayZResource_getZTGatewayById`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | The ZT gateway ID |

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### `PUT /ztGateway/{id}`

Update ZT gateway based on the ZT gateway ID

- Operation ID: `ZTGatewayZResource_updateZTGateway`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `id` | path | yes | integer(int32) | The ZT gateway ID |

**Request body:** `application/json` → ZTGatewayRequest

**Responses:**

| Code | Description |
| --- | --- |
| default | default response |

### Schemas

**`ZTGatewayResponse`**

| Property | Type | Required |
| --- | --- | --- |
| `availabilityZoneIds` | array<string> | no |
| `createTime` | integer(int32) | no |
| `egressIPs` | object | no |
| `healthStatus` | string | no |
| `id` | integer(int32) | no |
| `lastModTime` | integer(int32) | no |
| `lastModUid` | RequestResponseEntity | no |
| `name` | string | no |
| `platform` | string (enum: AWS, AZURE, GCP, CENTOS, REDHAT_LINUX, VMWARE_ESXI, MICROSOFT_HYPER_V) | no |
| `provData` | object | no |
| `region` | string | no |

**`ZTGatewayRequest`**

| Property | Type | Required |
| --- | --- | --- |
| `availabilityZoneIds` | array<string> | yes |
| `name` | string | yes |
| `platform` | string (enum: AWS, AZURE, GCP, CENTOS, REDHAT_LINUX, VMWARE_ESXI, MICROSOFT_HYPER_V) | yes |
| `provData` | object | yes |
| `region` | string | yes |

**`ZTGatewayMetrics`**

| Property | Type | Required |
| --- | --- | --- |
| `endTime` | string | no |
| `period` | string | no |
| `startTime` | string | no |
| `stat` | string | no |
| `type` | string | no |

**`ZeroTrustGatewayMetrics`**

| Property | Type | Required |
| --- | --- | --- |
| `statusCode` | string | no |
| `timestamps` | array<integer(int64)> | no |
| `values` | array<number(double)> | no |

**`ZeroTrustGatewayConfigCompare`**

| Property | Type | Required |
| --- | --- | --- |
| `dataAfter` | object | no |
| `dataBefore` | object | no |

**`ZeroTrustGatewayConfigStatus`**

| Property | Type | Required |
| --- | --- | --- |
| `configs` | array<Config> | no |
| `nextToken` | string | no |
| `syncStatus` | string | no |

**`ZTGatewayEventsMessage`**

| Property | Type | Required |
| --- | --- | --- |
| `events` | array<Event> | no |
| `nextToken` | string | no |
| `resourceId` | string | no |

**`ZTGatewayDetails`**

| Property | Type | Required |
| --- | --- | --- |
| `filteredDeployedAZs` | integer(int32) | no |
| `healthyAZs` | integer(int32) | no |
| `licenseCount` | integer(int32) | no |
| `totalDeployedAZs` | integer(int32) | no |
| `unhealthyAZs` | integer(int32) | no |
| `ztGateways` | array<UnifiedZTGatewayResponse> | no |

**`EntityResponse`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |

**`ZeroTrustGatewayStatus`**

| Property | Type | Required |
| --- | --- | --- |
| `serviceStatus` | array<ServiceStatus> | no |

**`SupportedRegion`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `regionName` | string | no |
| `cloudType` | string (enum: AWS, AZURE, GCP) | no |

**`TestInfo`**

| Property | Type | Required |
| --- | --- | --- |
| `description` | string | no |
| `headers` | string | no |
| `id` | string | no |
| `name` | string | no |
| `options` | array<string> | no |
| `type` | string | no |
| `url` | string | no |

**`TrafficTest`**

| Property | Type | Required |
| --- | --- | --- |
| `description` | string | no |
| `headers` | string | no |
| `name` | string | no |
| `options` | array<string> | no |
| `type` | string | no |
| `url` | string | no |

**`TrafficTestEnvironmentMessage`**

| Property | Type | Required |
| --- | --- | --- |
| `environment` | Environment | no |
| `resourceId` | string | no |

**`TrafficRunTest`**

| Property | Type | Required |
| --- | --- | --- |
| `testId` | string | no |

**`TrafficRunResponseMessage`**

| Property | Type | Required |
| --- | --- | --- |
| `testDetail` | TestDetail | no |

**`AwsZTGatewayResponse`**

| Property | Type | Required |
| --- | --- | --- |
| `endpointServiceName` | string | no |
| `endpoints` | array<object> | no |
| `endpointsCount` | string | no |

**`GcpZTGatewayResponse`**

| Property | Type | Required |
| --- | --- | --- |
| `interceptDeploymentGroup` | string | no |
| `interceptEndpointGroups` | array<object> | no |
| `interceptEndpointGroupsCount` | string | no |

**`RequestResponseEntity`**

| Property | Type | Required |
| --- | --- | --- |
| `deleted` | boolean | no |
| `id` | integer(int32) | no |
| `name` | string | no |

**`AwsZTGatewayContentResponse`**

| Property | Type | Required |
| --- | --- | --- |
| `accountGroups` | array<RequestResponseEntity> | no |
| `additionalAwsAccounts` | array<string> | no |
| `allowedAccounts` | array<RequestResponseEntity> | no |
| `drTestingMode` | boolean | no |
| `globalProfile` | RequestResponseEntity | no |
| `locationName` | string | no |
| `locationTemplate` | LocationTemplate | no |

**`LocationTemplate`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int32) | no |
| `name` | string | no |
| `desc` | string | no |
| `template` | LocationTemplateDetails | no |
| `editable` | boolean | no |
| `lastModUid` | object | no |
| `lastModTime` | integer(int32) | no |

**`LocationTemplateDetails`**

| Property | Type | Required |
| --- | --- | --- |
| `templatePrefix` | string | no |
| `xffForwardEnabled` | boolean | no |
| `authRequired` | boolean | no |
| `cautionEnabled` | boolean | no |
| `aupEnabled` | boolean | no |
| `aupTimeoutInDays` | integer(int32) | no |
| `ofwEnabled` | boolean | no |
| `ipsControl` | boolean | no |
| `enforceBandwidthControl` | boolean | no |
| `upBandwidth` | integer(int32) | no |
| `dnBandwidth` | integer(int32) | no |
| `displayTimeUnit` | string (enum: MINUTE, HOUR, DAY) | no |
| `idleTimeInMinutes` | integer(int32) | no |
| `surrogateIPEnforcedForKnownBrowsers` | boolean | no |
| `surrogateRefreshTimeUnit` | string (enum: MINUTE, HOUR, DAY) | no |
| `surrogateRefreshTimeInMinutes` | integer(int32) | no |
| `surrogateIP` | boolean | no |

**`EntityReference`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `name` | string | no |
| `isNameL10nTag` | boolean | no |
| `extensions` | object | no |
| `deleted` | boolean | no |
| `externalId` | string | no |
| `associationTime` | integer(int32) | no |

**`GcpZTGatewayContentResponse`**

| Property | Type | Required |
| --- | --- | --- |
| `drTestingMode` | boolean | no |
| `globalProfile` | RequestResponseEntity | no |
| `iamPrincipals` | array<IAMPrincipal> | no |
| `locationName` | string | no |
| `locationTemplate` | LocationTemplate | no |

**`IAMPrincipal`**

| Property | Type | Required |
| --- | --- | --- |
| `addedOn` | integer(int64) | no |
| `type` | string (enum: USER, GROUP, SERVICE_ACCOUNT) | no |
| `value` | string | no |

**`AwsZTGatewayContentRequest`**

| Property | Type | Required |
| --- | --- | --- |
| `accountGroups` | array<RequestResponseEntity> | no |
| `additionalAwsAccounts` | array<string> | no |
| `allowedAccounts` | array<RequestResponseEntity> | no |
| `drTestingMode` | boolean | no |
| `globalProfile` | RequestResponseEntity | no |
| `locationName` | string | no |
| `locationTemplate` | RequestResponseEntity | yes |

**`GcpZTGatewayContentRequest`**

| Property | Type | Required |
| --- | --- | --- |
| `drTestingMode` | boolean | no |
| `globalProfile` | RequestResponseEntity | no |
| `iamPrincipals` | array<IAMPrincipal> | no |
| `locationName` | string | no |
| `locationTemplate` | RequestResponseEntity | yes |

**`Config`**

| Property | Type | Required |
| --- | --- | --- |
| `appliedOn` | string | no |
| `configState` | string | no |
| `reason` | string | no |
| `status` | string | no |
| `version` | string | no |

**`Event`**

| Property | Type | Required |
| --- | --- | --- |
| `category` | string | no |
| `info` | string | no |
| `status` | string | no |
| `timestamp` | string | no |
| `type` | string | no |

**`UnifiedZTGatewayResponse`**

| Property | Type | Required |
| --- | --- | --- |
| `availabilityZoneIds` | array<string> | no |
| `createTime` | integer(int32) | no |
| `egressIPs` | object | no |
| `endpointServiceName` | string | no |
| `endpoints` | array<object> | no |
| `endpointsCount` | string | no |
| `geoLocation` | GeoLocation | no |
| `healthStatus` | string | no |
| `id` | integer(int32) | no |
| `interceptDeploymentGroup` | string | no |
| `interceptEndpointGroups` | array<object> | no |
| `interceptEndpointGroupsCount` | string | no |
| `lastModTime` | integer(int32) | no |
| `lastModUid` | RequestResponseEntity | no |
| `name` | string | no |
| `platform` | string (enum: AWS, AZURE, GCP, CENTOS, REDHAT_LINUX, VMWARE_ESXI, MICROSOFT_HYPER_V) | no |
| `provData` | UnifiedZTGatewayContentResponse | no |
| `region` | string | no |

**`GeoLocation`**

| Property | Type | Required |
| --- | --- | --- |
| `cityGeoId` | integer(int32) | no |
| `cityName` | string | no |
| `countryCode` | string | no |
| `countryName` | string | no |
| `longitude` | integer(int32) | no |
| `latitude` | integer(int32) | no |
| `stateGeoId` | integer(int32) | no |
| `stateName` | string | no |
| `continentCode` | string | no |

**`UnifiedZTGatewayContentResponse`**

| Property | Type | Required |
| --- | --- | --- |
| `accountGroups` | array<RequestResponseEntity> | no |
| `additionalAwsAccounts` | array<string> | no |
| `allowedAccounts` | array<RequestResponseEntity> | no |
| `drTestingMode` | boolean | no |
| `globalProfile` | RequestResponseEntity | no |
| `iamPrincipals` | array<IAMPrincipal> | no |
| `locationName` | string | no |
| `locationTemplate` | LocationTemplate | no |

**`ServiceStatus`**

| Property | Type | Required |
| --- | --- | --- |
| `availabilityZoneId` | string | no |
| `internet` | string | no |
| `localEgress` | string | no |
| `privateApplications` | string | no |

**`Environment`**

| Property | Type | Required |
| --- | --- | --- |
| `endTime` | string | no |
| `id` | string | no |
| `status` | string | no |

**`TestDetail`**

| Property | Type | Required |
| --- | --- | --- |
| `result` | string | no |
| `status` | string | no |
| `testId` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/zscaler-clouds","lastmod":"2026-01-06T23:49Z","nid":"1485841"} -->
## Zscaler Clouds 

- Source: https://help.zscaler.com/legacy-apis/zscaler-clouds
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Zscaler Clouds 
- Last modified: 2026-01-06T23:49Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `GET /mgmtconfig/v1/admin/zpathCloud/getAltClouds`

**Gets all alternative cloud domains for the Zscaler cloud that your organization is assigned to.**

- Operation ID: `getAllAltClouds_1`

**Responses:**

| Code | Description |
| --- | --- |
| 200 | OK |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

### Schemas

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/zscaler-service-endpoints","lastmod":"2026-01-06T23:49Z","nid":"1421001"} -->
## Zscaler Service Endpoints

- Source: https://help.zscaler.com/legacy-apis/zscaler-service-endpoints
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZIA API > Zscaler Data Center VIPs JSON > Zscaler Service Endpoints
- Last modified: 2026-01-06T23:49Z
- Summary: Information about the various API endpoints offered by Zscaler to deploy the required infrastructure and integrate the Zscaler service with your environment.

This article provides an overview of the various API endpoints offered by Zscaler to enable customers and partners to deploy the required infrastructure and integrate the Zscaler service with their environment. These API endpoints help you automate firewall configuration changes and their propagation in your environments to establish connectivity with the Zscaler cloud infrastructure.

For a list of all available Zscaler API endpoints, visit [config.zscaler.com](http://config.zscaler.com).

- To view insights into cloud operation status, upcoming maintenance, and security updates, visit the [Zscaler Trust Portal](https://trust.zscaler.com).
- The Zscaler service also provides the RSS Feed URLs for [blogs](https://trust.zscaler.com/blog-feed) and [notifications related to URL categories](https://trust.zscaler.com/blog-feed/url-category-notification).

## ZIA Public Service Edges

This section provides information specific to Zscaler’s data centers, such as the list of IP addresses along with the prefixes advertised by each data center, VPN hostname, GRE virtual IP address, SVPN virtual IP address, and more. This information is necessary for adding the Zscaler data center’s address prefix to the allowlist in your firewall or tunneling your organization’s traffic to the Zscaler service.

The following links provide information specific to Zscaler’s data centers for each cloud in JSON format: ZIA Public Service Edge

- [zscaler.net](https://config.zscaler.com/api/zscaler.net/cenr/json)
- [zscalerone.net](https://config.zscaler.com/api/zscalerone.net/cenr/json)
- [zscalertwo.net](https://config.zscaler.com/api/zscalertwo.net/cenr/json)
- [zscalerthree.net](https://config.zscaler.com/api/zscalerthree.net/cenr/json)
- [zscloud.net](https://config.zscaler.com/api/zscloud.net/cenr/json)

## Zscaler Central Authority (CA)

This section provides the list of IP addresses along with the prefixes used by the [Zscaler Central Authority](https://help.zscaler.com/zia/about-zscaler-cloud-architecture). You need to add these IP address prefixes to the allowlist in your firewall if you are using any of the following services:

- Third-party authentication service with Active Directory (AD)/OpenLDAP or Kerberos client authentication service is hosted in your organization’s data center. The AD and Kerberos servers must pass authentication requests directly to the CA hosted in the Zscaler cloud.
- ZIA Private Service Edges are installed in your organization’s data center. The Private Service Edges must communicate with other nodes in the Zscaler cloud, including the CA for user authentication and policy updates.

The IP addresses for the CA are split between required and recommended lists and the following links provide this information on a per-cloud basis in JSON format:

| Required | Recommended |
| --- | --- |
| [zscaler.net](https://config.zscaler.com/api/zscaler.net/hubs/cidr/json/required); [zscalerone.net](https://config.zscaler.com/api/zscalerone.net/hubs/cidr/json/required); [zscalertwo.net](https://config.zscaler.com/api/zscalertwo.net/hubs/cidr/json/required); [zscalerthree.net](https://config.zscaler.com/api/zscalerthree.net/hubs/cidr/json/required); [zscloud.net](https://config.zscaler.com/api/zscloud.net/hubs/cidr/json/required) | [zscaler.net](https://config.zscaler.com/api/zscaler.net/hubs/cidr/json/recommended); [zscalerone.net](https://config.zscaler.com/api/zscalerone.net/hubs/cidr/json/recommended); [zscalertwo.net](https://config.zscaler.com/api/zscalertwo.net/hubs/cidr/json/recommended); [zscalerthree.net](https://config.zscaler.com/api/zscalerthree.net/hubs/cidr/json/recommended); [zscloud.net](https://config.zscaler.com/api/zscloud.net/hubs/cidr/json/recommended) |

<h1>Proxy Auto-Configuration</h1> <p>This section provides a list of IP addresses where you can download proxy auto-configuration (PAC) files and use them to forward your user traffic to the ZIA Private Service Edges. You can use this method to forward browser-based traffic from devices without the Zscaler Client Connector installed. PAC files are also used with Zscaler Client Connector to direct the traffic to a specific data center.</p> <p>The following links provide the list of IP addresses for each cloud from where you can download PAC files:</p> <ul style="list-style-type: disc;"> <li><a class="url-external" href="https://config.zscaler.com/api/zscaler.net/pac/json" target="_blank">zscaler.net</a></li> <li><a class="url-external" href="https://config.zscaler.com/api/zscalerone.net/pac/json" target="_blank">zscalerone.net</a></li> <li><a class="url-external" href="https://config.zscaler.com/api/zscalertwo.net/pac/json" target="_blank">zscalertwo.net</a></li> <li><a class="url-external" href="https://config.zscaler.com/api/zscalerthree.net/pac/json" target="_blank">zscalerthree.net</a></li> <li><a class="url-external" href="https://config.zscaler.com/api/zscloud.net/pac/json" target="_blank">zscloud.net</a></li> </ul>

## Zscaler Client Connector

This section provides a list of the ZIA Public Service Edge IP addresses to which the Zscaler Client Connector forwards traffic. If you need to forward traffic from a site that is behind a firewall to the ZIA Public Service Edge (destination server) using Zscaler Client Connector, you need to add the destination server's IP address to the allowlist in your firewall.

The following links provide the list of destination servers’ IP addresses (referred to as SVPN IP addresses) for each Zscaler cloud in JSON format:

- [zscaler.net](https://config.zscaler.com/api/zscaler.net/svpn/json)
- [zscalertwo.net](https://config.zscaler.com/api/zscalertwo.net/svpn/json)
- [zscalerthree.net](https://config.zscaler.com/api/zscalerthree.net/svpn/json)
- [zscloud.net](https://config.zscaler.com/api/zscloud.net/svpn/json)

For zscalerone.net, the endpoints are yet to be published.

## Future Data Centers

This section provides the list of IP addresses along with the prefixes that are allocated for Zscaler’s future data centers. These data centers will become operative as Zscaler expands its operations to new geographical regions and adds new cloud infrastructure. If you are setting up new organization sites, you can add these address prefixes to the allowlist in your firewall ahead of time to simplify the firewall configuration later. You can also add these address prefixes to your access control lists and application allowlists, as applicable.

The following links provide a list of IP addresses for each cloud that are advertised by Zscaler’s future data centers in JSON format:

- [zscaler.net](https://config.zscaler.com/api/zscaler.net/future/json)
- [zscalerone.net](https://config.zscaler.com/api/zscalerone.net/future/json)
- [zscalertwo.net](https://config.zscaler.com/api/zscalertwo.net/future/json)
- [zscalerthree.net](https://config.zscaler.com/api/zscalerthree.net/future/json)
- [zscloud.net](https://config.zscaler.com/api/zscloud.net/future/json)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/legacy-apis/zscaler-virtual-ip-address-range-management","lastmod":"2026-01-06T23:49Z","nid":"1532097"} -->
## Zscaler Virtual IP Address Range Management

- Source: https://help.zscaler.com/legacy-apis/zscaler-virtual-ip-address-range-management
- Product: Legacy Zscaler APIs
- Path: Legacy Zscaler APIs Help > ZPA API > API Developer & Reference Guide > Reference Guide > Zscaler Virtual IP Address Range Management
- Last modified: 2026-01-06T23:49Z

To access detailed ZPA API documentation, including references and use cases, refer to the [Zscaler Help Portal](/zpa/about-zpa-api).

### `POST /mgmtconfig/v1/admin/customers/{customerId}/v2/ipRanges/search`

**Gets the virtual IP address range by page and page size for the specified customer.**

- Operation ID: `getIpRangesByPage_2`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |

**Request body:** `application/json` → FilterAndSortDto

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 200 | OK |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/v2/ipRanges`

**Gets all virtual IP address ranges for the specified customer.**

- Operation ID: `getAllIpRanges_2`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 200 | OK |

### `POST /mgmtconfig/v1/admin/customers/{customerId}/v2/ipRanges`

**Adds a new virtual IP address range for the specified customer.**

- Operation ID: `addIpRanges_2`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |

**Request body:** `application/json` → C2cIpRangesDTO

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 200 | OK |

### `GET /mgmtconfig/v1/admin/customers/{customerId}/v2/ipRanges/{ipRangeId}`

**Gets the virtual IP address range details for the specified ID.**

- Operation ID: `getIpRange_2`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `ipRangeId` | path | yes | integer(int64) | The unique identifier of the virtual IP address range. |

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 200 | OK |

### `PUT /mgmtconfig/v1/admin/customers/{customerId}/v2/ipRanges/{ipRangeId}`

**Updates the virtual IP address range for the specified ID.**

- Operation ID: `updateIpRanges_2`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The ZPA tenant ID of the customer. |
| `ipRangeId` | path | yes | integer(int64) | The unique identifier of the virtual IP address range. |

**Request body:** `application/json` → C2cIpRangesDTO

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 204 | No Content |

### `DELETE /mgmtconfig/v1/admin/customers/{customerId}/v2/ipRanges/{ipRangeId}`

**Deletes the virtual IP address range for the specified ID.**

- Operation ID: `deleteIpRange_2`

**Parameters:**

| Name | In | Required | Type | Description |
| --- | --- | --- | --- | --- |
| `customerId` | path | yes | integer(int64) | The unique identifier of the ZPA tenant. |
| `ipRangeId` | path | yes | integer(int64) | The unique identifier of the virtual IP address range. |

**Responses:**

| Code | Description |
| --- | --- |
| 400 | Bad Request |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 405 | Method Not Allowed |
| 401 | Unauthorized |
| 404 | Not Found |
| 403 | Forbidden |
| 503 | Service Unavailable |
| 500 | Internal Server Error |
| 200 | OK |

### Schemas

**`FilterAndSortDto`**

| Property | Type | Required |
| --- | --- | --- |
| `sortBy` | SortDto | no |
| `filterBy` | array<UIFilterDto> | no |
| `pageBy` | PaginationDto | no |

**`ZPathAPIError`**

| Property | Type | Required |
| --- | --- | --- |
| `reason` | string | no |
| `id` | string | no |
| `params` | array<object> | no |
| `hostname` | string | no |

**`PageListC2cIpRangesDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `totalPages` | integer(int32) | no |
| `currentCount` | integer(int64) | no |
| `totalCount` | integer(int64) | no |
| `list` | array<C2cIpRangesDTO> | no |

**`C2cIpRangesDTO`**

| Property | Type | Required |
| --- | --- | --- |
| `id` | integer(int64) | no |
| `modifiedTime` | integer(int32) | no |
| `creationTime` | integer(int32) | no |
| `modifiedBy` | integer(int64) | no |
| `isDeleted` | integer(int32) | no |
| `customerId` | integer(int64) | yes |
| `name` | string | no |
| `description` | string | no |
| `ipRangeBegin` | string | no |
| `ipRangeEnd` | string | no |
| `subnetCidr` | string | no |
| `latitudeInDb` | string | no |
| `longitudeInDb` | string | no |
| `location` | string | no |
| `countryCode` | string | no |
| `enabled` | boolean | no |
| `totalIps` | integer(int32) | no |
| `usedIps` | integer(int32) | no |
| `availableIps` | integer(int32) | no |
| `sccmFlag` | boolean | no |
| `locationHint` | string | no |

**`SortDto`**

| Property | Type | Required |
| --- | --- | --- |
| `sortName` | string | no |
| `sortOrder` | string | no |

**`UIFilterDto`**

| Property | Type | Required |
| --- | --- | --- |
| `filterName` | string | no |
| `operator` | string | no |
| `values` | array<string> | no |
| `commaSepValues` | string | no |

**`PaginationDto`**

| Property | Type | Required |
| --- | --- | --- |
| `page` | string | no |
| `pageSize` | string | no |
| `validPageSize` | integer(int32) | no |
| `validPage` | integer(int32) | no |
<!-- /ZS-ARTICLE -->
