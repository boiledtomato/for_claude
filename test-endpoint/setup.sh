#!/bin/bash
# VM初回起動時に自動実行されるセットアップスクリプト（cloud-init用）
set -e

apt-get update -y
apt-get install -y python3 ufw

# ファイアウォール設定
ufw allow 22/tcp
ufw allow 5349/tcp
ufw --force enable

# server.py を配置
cat > /opt/server.py << 'PYEOF'
#!/usr/bin/env python3
from http.server import HTTPServer, BaseHTTPRequestHandler

class TestHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        body = b"OK - port 5349 test endpoint (Zscaler policy check)\n"
        self.send_response(200)
        self.send_header("Content-Type", "text/plain")
        self.send_header("Content-Length", str(len(body)))
        self.end_headers()
        self.wfile.write(body)

    def do_HEAD(self):
        self.send_response(200)
        self.send_header("Content-Type", "text/plain")
        self.end_headers()

    def log_message(self, format, *args):
        print(f"{self.address_string()} - {format % args}", flush=True)

if __name__ == "__main__":
    server = HTTPServer(("0.0.0.0", 5349), TestHandler)
    print("Test endpoint listening on port 5349", flush=True)
    server.serve_forever()
PYEOF

# systemd サービスとして登録（VM再起動後も自動起動）
cat > /etc/systemd/system/test-endpoint.service << 'SVCEOF'
[Unit]
Description=Port 5349 Test Endpoint
After=network.target

[Service]
ExecStart=/usr/bin/python3 /opt/server.py
Restart=always
User=nobody

[Install]
WantedBy=multi-user.target
SVCEOF

systemctl daemon-reload
systemctl enable test-endpoint
systemctl start test-endpoint
