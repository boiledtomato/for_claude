"""
One-time OneDrive OAuth setup script.
Run this LOCALLY to obtain the refresh_token needed for GitHub Actions.

Usage:
  pip install requests
  python scripts/onedrive_setup.py

You will be prompted to visit a URL and paste a code.
The script prints your refresh_token — copy it to GitHub Secrets.
"""

import json
import requests

# ── STEP 1: Create an Azure App Registration ──────────────────────────────────
# 1. Go to https://portal.azure.com → Azure Active Directory → App registrations
# 2. Click "New registration"
#    - Name: ZscalerMonitor (or anything)
#    - Supported account types: "Personal Microsoft accounts only"
#    - Redirect URI: Public client/native → https://login.microsoftonline.com/common/oauth2/nativeclient
# 3. After creation, copy the "Application (client) ID"
# 4. Go to "Certificates & secrets" → New client secret → copy the VALUE
# 5. Go to "API permissions" → Add "Files.ReadWrite" and "offline_access" (Microsoft Graph, Delegated)

CLIENT_ID     = input("Azure App Client ID: ").strip()
CLIENT_SECRET = input("Azure App Client Secret: ").strip()

TENANT = "common"
SCOPE  = "Files.ReadWrite offline_access"

# ── STEP 2: Device code flow ──────────────────────────────────────────────────
resp = requests.post(
    f"https://login.microsoftonline.com/{TENANT}/oauth2/v2.0/devicecode",
    data={"client_id": CLIENT_ID, "scope": SCOPE},
)
resp.raise_for_status()
data = resp.json()

print("\n" + "="*60)
print(data["message"])
print("="*60 + "\n")
input("Press Enter after you have signed in...")

# ── STEP 3: Poll for token ────────────────────────────────────────────────────
token_resp = requests.post(
    f"https://login.microsoftonline.com/{TENANT}/oauth2/v2.0/token",
    data={
        "grant_type": "urn:ietf:params:oauth:grant-type:device_code",
        "client_id": CLIENT_ID,
        "client_secret": CLIENT_SECRET,
        "device_code": data["device_code"],
    },
)
token_resp.raise_for_status()
tokens = token_resp.json()

print("\n✅ 認証成功！以下の値をGitHub Secretsに追加してください:\n")
print(f"  ONEDRIVE_CLIENT_ID     = {CLIENT_ID}")
print(f"  ONEDRIVE_CLIENT_SECRET = {CLIENT_SECRET}")
print(f"  ONEDRIVE_REFRESH_TOKEN = {tokens['refresh_token']}")
print()
