"""
ZCC (Zscaler Client Connector) 全記事 → Markdown 生成スクリプト

以下の2サイトマップからZCC関連URLをすべて収集し、
ページ内容をMarkdownファイルに変換・出力します。

  - https://www.zscaler.com/sitemap.xml
  - https://help.zscaler.com/sitemap.xml

help.zscaler.com はJSレンダリングSPAのため Jina AI Reader 経由で取得します。
www.zscaler.com  は通常スクレイピングを使用します。

使い方:
    pip install requests beautifulsoup4 lxml
    python scripts/scrape_zcc_to_markdown.py

オプション:
    --workers N   並列スレッド数 (デフォルト: 3)
    --delay  N    リクエスト間の待機秒 (デフォルト: 1.0)
    --out    DIR  出力先 (デフォルト: notebooklm_docs/zcc)

出力:
    notebooklm_docs/zcc/
        help_zscaler_com.md     ← help.zscaler.com 由来
        www_zscaler_com.md      ← www.zscaler.com 由来
    ※ 400,000文字超過時は _part1.md, _part2.md と自動分割
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

# ── 設定 ─────────────────────────────────────────────────────────────────
SITEMAPS = [
    "https://help.zscaler.com/sitemap.xml",
    "https://www.zscaler.com/sitemap.xml",
]

ZCC_KEYWORDS = [
    "zscaler-client-connector",
    "client-connector",
    "/zcc",
    "z-app",
]

MAX_CHARS_PER_FILE = 400_000
DEFAULT_WORKERS = 3
DEFAULT_DELAY   = 1.0

SESSION = requests.Session()
SESSION.headers.update({
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36"
})

JINA_BASE = "https://r.jina.ai/"

# ── サイトマップ取得 ──────────────────────────────────────────────────────

def fetch_sitemap(sitemap_url: str) -> list[str]:
    print(f"[sitemap] {sitemap_url}")
    try:
        resp = SESSION.get(sitemap_url, timeout=30)
        resp.raise_for_status()
    except Exception as e:
        print(f"  [SKIP] 取得失敗: {e}")
        return []

    xml_clean = re.sub(r' xmlns="[^"]+"', "", resp.text)
    try:
        root = ET.fromstring(xml_clean)
    except ET.ParseError as e:
        print(f"  [SKIP] XML解析エラー: {e}")
        return []

    # sitemap index（子サイトマップへの参照）か通常サイトマップかを判定
    child_sitemaps = [e.text.strip() for e in root.iter("sitemap") if e.text]
    if child_sitemaps:
        all_urls: list[str] = []
        for child_url in child_sitemaps:
            try:
                child_resp = SESSION.get(child_url, timeout=30)
                child_xml = re.sub(r' xmlns="[^"]+"', "", child_resp.text)
                child_root = ET.fromstring(child_xml)
                all_urls.extend(
                    e.text.strip() for e in child_root.iter("loc") if e.text
                )
                time.sleep(0.2)
            except Exception as e:
                print(f"  [SKIP] {child_url}: {e}")
        return all_urls
    else:
        return [e.text.strip() for e in root.iter("loc") if e.text]


def collect_zcc_urls() -> dict[str, list[str]]:
    result: dict[str, list[str]] = {}
    for sitemap in SITEMAPS:
        host = sitemap.split("/")[2]
        urls = fetch_sitemap(sitemap)
        zcc_urls = [
            u for u in urls
            if any(kw in u.lower() for kw in ZCC_KEYWORDS)
        ]
        print(f"  → {host}: 全{len(urls)}件中 ZCC関連 {len(zcc_urls)}件")
        if zcc_urls:
            result[host] = zcc_urls
    return result

# ── ページ取得 ────────────────────────────────────────────────────────────

def scrape_via_jina(url: str, delay: float) -> tuple[str, str]:
    """Jina AI Reader でJSレンダリング済みMarkdownを取得（help.zscaler.com用）"""
    time.sleep(delay)
    jina_url = JINA_BASE + url
    try:
        resp = SESSION.get(
            jina_url,
            timeout=30,
            headers={"Accept": "text/plain"},
        )
        resp.raise_for_status()
        text = resp.text.strip()
        if not text:
            return url, ""
        block = f"---\n\n<!-- source: {url} -->\n\n{text}\n\n"
        return url, block
    except Exception as e:
        print(f"  [SKIP] {url} — {e}")
        return url, ""


def scrape_via_bs4(url: str, delay: float) -> tuple[str, str]:
    """通常スクレイピング → Markdown変換（www.zscaler.com用）"""
    time.sleep(delay)
    try:
        resp = SESSION.get(url, timeout=20)
        resp.raise_for_status()
        soup = BeautifulSoup(resp.text, "lxml")

        for tag in soup.select(
            "nav, header, footer, aside, .sidebar, .breadcrumb, "
            "script, style, noscript, [role='navigation'], [role='banner']"
        ):
            tag.decompose()

        main = (soup.find("main")
                or soup.find("article")
                or soup.find(id=re.compile(r"content|main", re.I))
                or soup.find(class_=re.compile(r"content|article|main", re.I))
                or soup.body)
        if not main:
            return url, ""

        title = soup.find("h1")
        title_text = title.get_text(" ", strip=True) if title else ""

        lines: list[str] = []
        for el in main.descendants:
            if not hasattr(el, "name"):
                continue
            text = el.get_text(" ", strip=True)
            if not text:
                continue
            if el.name == "h1":
                lines.append(f"# {text}")
            elif el.name == "h2":
                lines.append(f"## {text}")
            elif el.name == "h3":
                lines.append(f"### {text}")
            elif el.name in ("h4", "h5", "h6"):
                lines.append(f"#### {text}")
            elif el.name == "li":
                lines.append(f"- {text}")
            elif el.name in ("p", "td", "th"):
                lines.append(text)

        seen: set[str] = set()
        unique = [l for l in lines if not (l in seen or seen.add(l))]  # type: ignore
        md = "\n\n".join(unique)
        if not md.strip():
            return url, ""

        header = f"# {title_text}\n\n" if title_text else ""
        block = f"---\n\n<!-- source: {url} -->\n\n{header}{md}\n\n"
        return url, block
    except Exception as e:
        print(f"  [SKIP] {url} — {e}")
        return url, ""


def scrape_all(
    urls: list[str],
    host: str,
    workers: int,
    delay: float,
) -> list[str]:
    use_jina = "help.zscaler.com" in host
    scrape_fn = scrape_via_jina if use_jina else scrape_via_bs4
    method = "Jina AI Reader" if use_jina else "直接スクレイピング"

    print(f"\n[{host}] {len(urls)} ページを取得中 ({method})...")
    results: dict[str, str] = {}

    with ThreadPoolExecutor(max_workers=workers) as ex:
        futures = {ex.submit(scrape_fn, url, delay): url for url in urls}
        done = 0
        for future in as_completed(futures):
            url, block = future.result()
            results[url] = block
            done += 1
            if done % 20 == 0 or done == len(urls):
                success = sum(1 for v in results.values() if v)
                print(f"  [{host}] {done}/{len(urls)} 完了 (取得成功: {success}件)")

    return [results[url] for url in urls if results.get(url)]

# ── Markdownファイル書き出し ──────────────────────────────────────────────

def write_markdown(out_dir: Path, stem: str, source: str, blocks: list[str]) -> list[Path]:
    out_dir.mkdir(parents=True, exist_ok=True)
    written: list[Path] = []
    part = 1
    buf = f"# ZCC (Zscaler Client Connector) ドキュメント — {source}\n\n"

    for block in blocks:
        if len(buf) + len(block) > MAX_CHARS_PER_FILE:
            path = out_dir / f"{stem}_part{part}.md"
            path.write_text(buf, encoding="utf-8")
            written.append(path)
            print(f"  → {path}  ({len(buf):,} 文字)")
            part += 1
            buf = f"# ZCC — {source} (続き {part})\n\n"
        buf += block

    if buf.strip():
        suffix = f"_part{part}" if part > 1 else ""
        path = out_dir / f"{stem}{suffix}.md"
        path.write_text(buf, encoding="utf-8")
        written.append(path)
        print(f"  → {path}  ({len(buf):,} 文字)")

    return written

# ── メイン ───────────────────────────────────────────────────────────────

def main() -> None:
    parser = argparse.ArgumentParser(description="ZCC全記事→Markdown生成")
    parser.add_argument("--workers", type=int, default=DEFAULT_WORKERS)
    parser.add_argument("--delay",   type=float, default=DEFAULT_DELAY)
    parser.add_argument("--out",     type=Path, default=Path("notebooklm_docs/zcc"))
    args = parser.parse_args()

    start = time.time()
    zcc_by_host = collect_zcc_urls()

    if not zcc_by_host:
        print("[ERROR] ZCC関連URLが見つかりませんでした")
        sys.exit(1)

    all_written: list[Path] = []

    for host, urls in zcc_by_host.items():
        stem   = host.replace(".", "_")
        blocks = scrape_all(urls, host, args.workers, args.delay)
        written = write_markdown(args.out, stem, host, blocks)
        all_written.extend(written)

    elapsed = time.time() - start
    print(f"\n{'='*60}")
    print(f"完了！  経過時間: {elapsed/60:.1f} 分")
    print(f"\n生成ファイル:")
    for p in all_written:
        size_kb = p.stat().st_size // 1024
        print(f"  {p}  ({size_kb:,} KB)")
    print(f"\n--- NotebookLM へのアップロード手順 ---")
    print("1. NotebookLM で「ZCC」ノートブックを新規作成")
    print("2. 「ソースを追加」→「ファイルをアップロード」")
    print("3. 上記の .md ファイルをすべて選択してアップロード")


if __name__ == "__main__":
    main()
