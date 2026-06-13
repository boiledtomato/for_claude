#!/bin/bash
# Azure VM デプロイスクリプト
# 実行前に: az login

set -e

# ── 設定（必要に応じて変更） ──────────────────────
RG="zscaler-test-rg"
LOCATION="japaneast"
VM_NAME="zscaler-test-vm"
VM_SIZE="Standard_B1s"          # 無料枠対象
IMAGE="Ubuntu2204"
ADMIN_USER="azureuser"
# ─────────────────────────────────────────────────

echo "==> リソースグループ作成: $RG"
az group create --name "$RG" --location "$LOCATION"

echo "==> VM作成（SSH公開鍵認証、cloud-initでサーバー自動設定）"
az vm create \
  --resource-group "$RG" \
  --name "$VM_NAME" \
  --size "$VM_SIZE" \
  --image "$IMAGE" \
  --admin-username "$ADMIN_USER" \
  --generate-ssh-keys \
  --custom-data setup.sh \
  --output json

echo "==> ポート5349をNSGで開放"
az vm open-port \
  --resource-group "$RG" \
  --name "$VM_NAME" \
  --port 5349 \
  --priority 100

echo "==> パブリックIPアドレス取得"
PUBLIC_IP=$(az vm show \
  --resource-group "$RG" \
  --name "$VM_NAME" \
  --show-details \
  --query publicIps \
  --output tsv)

echo ""
echo "======================================"
echo "デプロイ完了！"
echo "パブリックIP: $PUBLIC_IP"
echo ""
echo "約1分後に動作確認:"
echo "  curl http://$PUBLIC_IP:5349/"
echo ""
echo "Zscalerテスト用URL:"
echo "  http://$PUBLIC_IP:5349/"
echo "======================================"
