"""
Zscaler Help Center → NotebookLM テキスト生成スクリプト

help.zscaler.com/sitemap.xml の全ページを製品ごとにスクレイピングし、
NotebookLMにアップロード可能なテキストファイルに変換します。

使い方:
    pip install requests beautifulsoup4 lxml
    python scripts/scrape_for_notebooklm.py

    # 特定製品だけ処理する場合:
    python scripts/scrape_for_notebooklm.py --products zia zpa

    # 並列数・ウェイト調整:
    python scripts/scrape_for_notebooklm.py --workers 3 --delay 0.5

出力先: notebooklm_docs/
    zia_part1.txt, zia_part2.txt ...  ← NotebookLMにそのままアップロード
"""

import argparse
import re
import sys
import time
import xml.etree.ElementTree as ET
from concurrent.futures import ThreadPoolExecutor, as_completed
from pathlib import Path

import requests
from bs4 import BeautifulSoup

# ── 設定 ────────────────────────────────────────────────────────────────
SITEMAP_URL = "https://help.zscaler.com/sitemap.xml"
OUTPUT_DIR = Path("notebooklm_docs")

# NotebookLMのファイルサイズ上限に合わせた1ファイルあたりの文字数上限
MAX_CHARS_PER_FILE = 400_000

DEFAULT_WORKERS = 5    # 並列スレッド数（増やしすぎるとサーバーに負荷）
DEFAULT_DELAY   = 0.3  # リクエスト間の待機秒数

# 製品パスプレフィックス → (ファイル名, 表示名)
PRODUCTS = {
    "zia":                          ("zia",                   "ZIA (Zscaler Internet Access)"),
    "zpa":                          ("zpa",                   "ZPA (Zscaler Private Access)"),
    "zscaler-client-connector":     ("zcc",                   "ZCC (Zscaler Client Connector)"),
    "zdx":                          ("zdx",                   "ZDX (Zscaler Digital Experience)"),
    "dspm":                         ("dspm",                  "DSPM (Data Security Posture Management)"),
    "cloud-branch-connector":       ("cloud_branch_connector","Cloud Branch Connector"),
    "soc-workbench":                ("soc_workbench",         "SOC Workbench"),
    "ai-asset-mgmt":                ("ai_asset_mgmt",         "AI Asset Management"),
    "zero-trust-browser":           ("zero_trust_browser",    "Zero Trust Browser"),
    "ztw":                          ("ztw",                   "ZTW (Zero Trust Workloads)"),
    "posture-control":              ("posture_control",        "Posture Control"),
    "zscaler-workload-segmentation":("zws",                   "Zscaler Workload Segmentation"),
    "zscaler-deception":            ("zscaler_deception",     "Zscaler Deception"),
    "legacy-apis":                  ("legacy_apis",           "Legacy APIs"),
}

SESSION = requests.Session()
SESSION.headers.update({
    "User-Agent": "Mozilla/5.0 (compatible; NotebookLM-helper/1.0)"
})

# ── サイトマップ取得 ─────────────────────────────────────────────────────

def fetch_sitemap() -> list[str]:
    print(f"[sitemap] {SITEMAP_URL} を取得中...")
    resp = SESSION.get(SITEMAP_URL, timeout=30)
    resp.raise_for_status()
    xml_clean = re.sub(r' xmlns="[^"]+"', "", resp.text)
    root = ET.fromstring(xml_clean)
    urls = [loc.text.strip() for loc in root.iter("loc") if loc.text]
    print(f"[sitemap] {len(urls)} URLを取得しました")
    return urls


def categorize(urls: list[str]) -> dict[str, list[str]]:
    buckets: dict[str, list[str]] = {stem: [] for stem, _ in PRODUCTS.values()}
    buckets["other"] = []

    for url in urls:
        path = url.replace("https://help.zscaler.com", "").lstrip("/")
        matched = False
        for prefix, (stem, _) in PRODUCTS.items():
            if path.startswith(prefix + "/") or path == prefix:
                buckets[stem].append(url)
                matched = True
                break
        if not matched:
            buckets["other"].append(url)

    return {k: v for k, v in buckets.items() if v}

# ── ページスクレイピング ──────────────────────────────────────────────────

def scrape_page(url: str, delay: float) -> tuple[str, str]:
    """(url, text) を返す。失敗時は (url, "")"""
    time.sleep(delay)
    try:
        resp = SESSION.get(url, timeout=20)
        resp.raise_for_status()
        soup = BeautifulSoup(resp.text, "lxml")

        # ナビゲーション・フッター・サイドバーを除去
        for tag in soup.select("nav, header, footer, aside, .sidebar, "
                               ".breadcrumb, .navigation, script, style, "
                               "[role='navigation'], [role='banner']"):
            tag.decompose()

        # メインコンテンツ領域を優先
        main = (soup.find("main")
                or soup.find("article")
                or soup.find(class_=re.compile(r"content|article|main", re.I))
                or soup.body)

        if not main:
            return url, ""

        # タイトル取得
        title = ""
        h1 = soup.find("h1")
        if h1:
            title = h1.get_text(" ", strip=True)

        # テキスト抽出・正規化
        text = main.get_text(" ", strip=True)
        text = re.sub(r"[ \t]+", " ", text)
        text = re.sub(r"\n{3,}", "\n\n", text)

        block = f"{'='*60}\nURL: {url}\n"
        if title:
            block += f"タイトル: {title}\n"
        block += f"{'='*60}\n{text}\n\n"

        return url, block

    except Exception as e:
        print(f"  [SKIP] {url} — {e}")
        return url, ""


def scrape_product(
    stem: str,
    display_name: str,
    urls: list[str],
    workers: int,
    delay: float,
) -> list[str]:
    """製品の全ページをスクレイピングしてテキストブロックのリストを返す"""
    print(f"\n[{stem}] {display_name}: {len(urls)} ページを取得開始...")
    results: dict[str, str] = {}

    with ThreadPoolExecutor(max_workers=workers) as ex:
        futures = {ex.submit(scrape_page, url, delay): url for url in urls}
        done = 0
        for future in as_completed(futures):
            url, text = future.result()
            results[url] = text
            done += 1
            if done % 20 == 0 or done == len(urls):
                print(f"  [{stem}] {done}/{len(urls)} 完了")

    # URLの元の順序を維持
    return [results[url] for url in urls if results.get(url)]

# ── ファイル書き出し ──────────────────────────────────────────────────────

def write_files(stem: str, display_name: str, blocks: list[str]) -> list[Path]:
    OUTPUT_DIR.mkdir(exist_ok=True)
    written: list[Path] = []
    part = 1
    buf = f"# {display_name}\n# Zscaler Help Center\n\n"

    for block in blocks:
        if len(buf) + len(block) > MAX_CHARS_PER_FILE:
            path = OUTPUT_DIR / f"{stem}_part{part}.txt"
            path.write_text(buf, encoding="utf-8")
            written.append(path)
            print(f"  → {path} ({len(buf):,} 文字)")
            part += 1
            buf = f"# {display_name} (続き {part})\n\n"
        buf += block

    if buf.strip():
        suffix = f"_part{part}" if part > 1 else ""
        path = OUTPUT_DIR / f"{stem}{suffix}.txt"
        path.write_text(buf, encoding="utf-8")
        written.append(path)
        print(f"  → {path} ({len(buf):,} 文字)")

    return written

# ── メイン ───────────────────────────────────────────────────────────────

def main() -> None:
    parser = argparse.ArgumentParser(description="Zscaler Help → NotebookLM テキスト生成")
    parser.add_argument(
        "--products", nargs="*",
        help="処理する製品のファイル名 (例: zia zpa zcc)。省略時は全製品"
    )
    parser.add_argument("--workers", type=int, default=DEFAULT_WORKERS,
                        help=f"並列スレッド数 (デフォルト: {DEFAULT_WORKERS})")
    parser.add_argument("--delay", type=float, default=DEFAULT_DELAY,
                        help=f"リクエスト間の待機秒数 (デフォルト: {DEFAULT_DELAY})")
    args = parser.parse_args()

    try:
        all_urls = fetch_sitemap()
    except Exception as e:
        print(f"[ERROR] サイトマップ取得失敗: {e}", file=sys.stderr)
        sys.exit(1)

    buckets = categorize(all_urls)

    # 処理対象を絞り込む
    stem_to_name = {stem: name for _, (stem, name) in PRODUCTS.items()}
    stem_to_name["other"] = "Other"

    if args.products:
        targets = {s: v for s, v in buckets.items() if s in args.products}
        if not targets:
            print(f"[ERROR] 指定された製品が見つかりません。利用可能: {list(buckets.keys())}")
            sys.exit(1)
    else:
        targets = {s: v for s, v in buckets.items() if s != "other"}

    print(f"\n処理対象: {list(targets.keys())}")
    print(f"並列数: {args.workers}  待機: {args.delay}s\n")

    all_written: list[Path] = []
    total_start = time.time()

    for stem, urls in targets.items():
        display = stem_to_name.get(stem, stem)
        blocks = scrape_product(stem, display, urls, args.workers, args.delay)
        written = write_files(stem, display, blocks)
        all_written.extend(written)

    elapsed = time.time() - total_start
    print(f"\n{'='*60}")
    print(f"完了！  経過時間: {elapsed/60:.1f} 分")
    print(f"生成ファイル数: {len(all_written)}")
    print(f"出力先: {OUTPUT_DIR.resolve()}")
    print(f"\n--- NotebookLM へのアップロード手順 ---")
    print("1. 製品ごとに新しいノートブックを作成")
    print("2. 「ソースを追加」→「ファイルをアップロード」")
    print("3. 対応する *_part*.txt を選択（複数可）")
    for p in all_written:
        print(f"   {p.name}")


if __name__ == "__main__":
    main()
