"""
NotebookLM URL List Generator for Zscaler Help Center

Fetches help.zscaler.com/sitemap.xml and outputs per-product URL lists
(max 100 URLs each) ready to paste into NotebookLM as web sources.

Usage:
    pip install requests
    python scripts/generate_notebooklm_urls.py

Output:
    notebooklm_urls/zia_urls.txt
    notebooklm_urls/zpa_urls.txt
    notebooklm_urls/zcc_urls.txt
    ... (one file per detected product)
"""

import re
import sys
import xml.etree.ElementTree as ET
from pathlib import Path

import requests

SITEMAP_URL = "https://help.zscaler.com/sitemap.xml"
OUTPUT_DIR = Path("notebooklm_urls")
MAX_URLS = 100

# Product path prefix → (file stem, display name)
PRODUCTS = {
    "zia": ("zia", "ZIA (Internet Access)"),
    "zpa": ("zpa", "ZPA (Private Access)"),
    "zscaler-client-connector": ("zcc", "ZCC (Client Connector)"),
    "zdx": ("zdx", "ZDX (Digital Experience)"),
    "zscaler-deception": ("zscaler_deception", "Zscaler Deception"),
    "cloud-branch-connector": ("cloud_branch_connector", "Cloud Branch Connector"),
    "dspm": ("dspm", "DSPM (Data Security Posture Management)"),
    "soc-workbench": ("soc_workbench", "SOC Workbench"),
    "ai-asset-mgmt": ("ai_asset_mgmt", "AI Asset Management"),
    "zero-trust-browser": ("zero_trust_browser", "Zero Trust Browser"),
    "ztw": ("ztw", "ZTW (Zero Trust Workloads)"),
    "posture-control": ("posture_control", "Posture Control"),
    "zscaler-workload-segmentation": ("zws", "Zscaler Workload Segmentation"),
    "legacy-apis": ("legacy_apis", "Legacy APIs"),
}


def fetch_sitemap(url: str) -> str:
    print(f"Fetching {url} ...")
    resp = requests.get(url, timeout=30)
    resp.raise_for_status()
    return resp.text


def parse_urls(xml_text: str) -> list[str]:
    # Strip namespace to simplify parsing
    xml_clean = re.sub(r' xmlns="[^"]+"', "", xml_text)
    root = ET.fromstring(xml_clean)
    return [loc.text.strip() for loc in root.iter("loc") if loc.text]


def categorize(urls: list[str]) -> dict[str, list[str]]:
    buckets: dict[str, list[str]] = {stem: [] for _, (stem, _) in PRODUCTS.items()}
    uncategorized: list[str] = []

    for url in urls:
        path = url.replace("https://help.zscaler.com", "").lstrip("/")
        matched = False
        for prefix, (stem, _) in PRODUCTS.items():
            if path.startswith(prefix + "/") or path == prefix:
                buckets[stem].append(url)
                matched = True
                break
        if not matched:
            uncategorized.append(url)

    if uncategorized:
        buckets["other"] = uncategorized

    return {k: v for k, v in buckets.items() if v}


def write_output(buckets: dict[str, list[str]]) -> None:
    OUTPUT_DIR.mkdir(exist_ok=True)

    # Build reverse map: stem → display name
    stem_to_name = {stem: name for _, (stem, name) in PRODUCTS.items()}
    stem_to_name["other"] = "Other / Uncategorized"

    summary_lines = []

    for stem, urls in sorted(buckets.items()):
        limited = urls[:MAX_URLS]
        out_file = OUTPUT_DIR / f"{stem}_urls.txt"
        display = stem_to_name.get(stem, stem)
        out_file.write_text("\n".join(limited), encoding="utf-8")
        summary_lines.append(
            f"  {out_file}  ({len(limited)} URLs / {len(urls)} total)  [{display}]"
        )

    print("\n=== 出力完了 ===")
    for line in summary_lines:
        print(line)

    total = sum(len(v[:MAX_URLS]) for v in buckets.values())
    print(f"\n合計 {total} URLs を {len(buckets)} ファイルに出力しました。")
    print(f"出力先: {OUTPUT_DIR.resolve()}")
    print("\n--- NotebookLM への貼り付け手順 ---")
    print("1. NotebookLM で製品ごとに新しいノートブックを作成")
    print("2. 「ソースを追加」→「ウェブサイト」を選択")
    print("3. 対応する *_urls.txt の内容をまとめて貼り付け（最大100件）")


def main() -> None:
    try:
        xml_text = fetch_sitemap(SITEMAP_URL)
    except Exception as e:
        print(f"[ERROR] サイトマップの取得に失敗しました: {e}", file=sys.stderr)
        sys.exit(1)

    urls = parse_urls(xml_text)
    print(f"取得URL数: {len(urls)}")

    buckets = categorize(urls)
    print(f"分類済み製品数: {len(buckets)}")

    write_output(buckets)


if __name__ == "__main__":
    main()
