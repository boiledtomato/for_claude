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
