"""
community.zscaler.com (Zenith Community) → NotebookLM 用 Markdown 生成 / 週次更新

help.zscaler.com とは別サイト・別ノートブックとして扱う。ヘルプドキュメントは
公式・査読済みだが、こちらはユーザー投稿のフォーラムで査読されていない。同じ
ノートに混ぜると誤答を権威づけてしまうため、出力先も同期先も分けている。

Zenith Community は Salesforce Experience Cloud (Aura) の SPA で、どのURLも
同一の約576KBのシェルHTMLを返し本文を含まない。本文の取得経路は2つある。

  api        SPA自身が使う Aura API (/s/sfsites/aura) をゲストとして叩く。
             UIAPI の制約で **取得できるのは質問の本文とメタデータのみ**:
               - FeedComment (回答本文) は "not supported in UI API"
               - Zenith_Article__c / _Guide__c / _Blog__c の本文フィールドは
                 ゲストの項目レベルセキュリティで参照できない
             回答なしのQ&Aはナレッジベースとしての価値が低いことに注意。

  prerender  Salesforce が検索エンジン向けに返すサーバサイドレンダリング版を
             読む。質問+全回答+記事/ガイド/ブログ本文がすべて揃う唯一の経路
             だが、これを返させるには Googlebot 等のクローラを名乗る必要が
             あり、身元を偽ることになる。既定では無効。

どちらを使うかは --fetch-mode で選ぶ。既定は api。

使い方:
    pip install requests beautifulsoup4 lxml

    # 週次の差分更新（sitemap の lastmod を見て変更分のみ取得）
    python scripts/build_community_docs.py

    # 全件を取得して再構築
    python scripts/build_community_docs.py --full

    # 回答・記事本文まで取り込む（クローラUAを名乗る。上記の注意を読むこと）
    python scripts/build_community_docs.py --full --fetch-mode prerender

    # カテゴリを絞る / 小さく試す
    python scripts/build_community_docs.py --full --categories zia --limit 5

    # CATEGORIES のキーワードを調整したあと、再取得せずに分類だけやり直す
    python scripts/build_community_docs.py --recategorize

出力:
    community_docs/<category>/community_<category>_partN.md  ← NotebookLMに入れる
    community_docs/README.md                        ← ファイル一覧・語数
    data/community_docs_index.json                  ← 記事ごとの更新状態

ファイル名の `community_` 接頭辞は必須。NotebookLM ではファイル名がソース名に
なり、ソースの突き合わせもファイル名で行うため、ヘルプ側の `zia_part1.md` と
同名になると互いのソースを消し合う。
"""

import argparse
import hashlib
import json
import os
import re
import sys
import time
import xml.etree.ElementTree as ET
from concurrent.futures import ThreadPoolExecutor
from datetime import datetime, timezone
from pathlib import Path

import requests
from bs4 import BeautifulSoup, NavigableString, Tag

# ── 設定 ─────────────────────────────────────────────────────────────────────
BASE_URL = "https://community.zscaler.com"
SITEMAP_URL = f"{BASE_URL}/s/sitemap.xml"
AURA_URL = f"{BASE_URL}/s/sfsites/aura"

OUTPUT_DIR = Path("community_docs")
INDEX_FILE = Path("data/community_docs_index.json")

# community.zscaler.com は中間証明書を送出しないため、certifi だけでは検証に
# 失敗する（ブラウザは AIA で補完するが requests/OpenSSL は補完しない）。
CA_CHAIN_FILE = Path(__file__).with_name("certs") / "community-zscaler-chain.pem"

# NotebookLM の 1ソース上限は 50万語。1.2M文字 ≒ 18万語で安全側に寄せる。
MAX_CHARS_PER_PART = 1_200_000

DEFAULT_WORKERS = 4
DEFAULT_DELAY = 0.3

BLOCK_START = "<!-- ZS-POST {meta} -->"
BLOCK_END = "<!-- /ZS-POST -->"
BLOCK_RE = re.compile(
    r"<!-- ZS-POST (?P<meta>\{.*?\}) -->\n(?P<body>.*?)\n<!-- /ZS-POST -->",
    re.DOTALL,
)

# 取り込む sitemap の種別 → URLパスセグメント
CONTENT_TYPES = {
    "question": "question",
    "zenith_article__c": "Articles",
    "zenith_guide__c": "Guides",
    "zenith_blog__c": "Blogs",
}
# topic / tag__c / collaborationgroup / view は一覧ページなので取り込まない

# カテゴリ定義: stem → (表示名, キーワード)
# コミュニティのトピック体系は sitemap から辿れないため、slug と本文の
# キーワードで分類する。並び順が優先順位（先に一致したものを採用）。
CATEGORIES: dict[str, tuple[str, list[str]]] = {
    "zcc": ("ZCC — Zscaler Client Connector", [
        "client connector", "client-connector", "zapp", "z-app", "zscaler app",
        "zcc", "tunnel 2.0", "tunnel 1.0", "zscalertunnel", "zpc",
        "machine tunnel", "captive portal", "enrollment", "app profile",
        "zsatunnel", "zstunnel", "packet filter driver", "z-tunnel",
    ]),
    "zpa": ("ZPA — Private Access", [
        "zpa", "private access", "app connector", "connector group",
        "browser access", "privileged remote access", "app segment",
        "segment group", "server group", "service edge", "double encryption",
        "application segment", "health reporting",
    ]),
    "zdx": ("ZDX — Digital Experience Monitoring", [
        "zdx", "digital experience", "probe", "cloudpath", "web probe",
        "user experience score", "deep tracing",
    ]),
    "zia": ("ZIA — Internet & SaaS", [
        "zia", "internet access", "url filtering", "ssl inspection",
        "cloud firewall", "sandbox", "dlp", "casb", "bandwidth control",
        "gre tunnel", "ipsec", "pac file", "location management",
        "sub-location", "surrogate ip", "threatlabz", "vzen",
        "firewall", "dns control", "dns tunnel", "malware", "antivirus",
        "url lookup", "url category", "url categorization", "web filtering",
        "youtube", "streaming media", "ssl interception", "ssl decryption",
        "proxy", "explicit proxy", "tenant restriction", "cloud app control",
        "file type control", "ips", "advanced threat", "atp", "safe search",
        "ssl bypass", "bypass list", "sub location",
    ]),
    "api": ("API / 自動化", [
        "api", "sdk", "postman", "terraform", "ansible", "rest call",
        "oneapi", "api key", "swagger", "python script", "powershell script",
        "powershell", "rest api", "json response", "curl command",
        "automation script", "graph api",
    ]),
    "branch": ("Branch / Cloud Connector / SD-WAN", [
        "branch connector", "cloud connector", "cellular", "sd-wan", "sdwan",
        "ztw", "zero trust sd-wan",
    ]),
    "data_security": ("Data Security / DSPM / Posture", [
        "dspm", "posture control", "data classification", "incident workflow",
        "data security posture", "cnap", "workload segmentation", "zws",
    ]),
    "deception": ("Deception / Threat", [
        "deception", "decoy", "threat intel", "breach predictor",
    ]),
    "platform": ("Platform / 認証 / 管理 / ログ", [
        "saml", "scim", "idp", "okta", "azure ad", "entra", "authentication",
        "admin portal", "nss", "log streaming", "lss", "audit log",
        "provisioning", "certificate", "sso", "single sign", "ldap",
        "active directory", "kerberos", "ipv6", "license", "subscription",
        "admin role", "sandbox report", "siem", "splunk", "syslog",
        "user provisioning", "directory sync",
    ]),
}
OTHER = "other"

# マーケティング・イベント・コミュニティ運営系は技術ナレッジではないので除外する。
# Guides / Blogs の多くがこれに該当する。
EXCLUDE_PATTERNS = [
    r"\bwebinar\b", r"\brsvp\b",
    # "register" / "registration" は技術的な文脈（デバイス登録、DNSレコード登録）
    # でも使われるため、イベント告知の言い回しに限定する
    r"\bregister\s+(now|today|here)\b",
    r"\bregister\s+for\s+(the\s+|a\s+|free\s+)*(webinar|event|training|session|roadshow|workshop|lab)",
    r"\bregistration\s+(is\s+)?(now\s+)?open\b", r"\bevent\s+registration\b",
    r"zenith\s*live", r"\bzenithlive", r"\broadshow\b", r"\bmeetup\b",
    r"\bconference\b", r"\bsummit\b", r"\bkeynote\b",
    r"\d+%\s*off", r"\bdiscount\b", r"\bpromo\b", r"\bgiveaway\b",
    r"\bsweepstake", r"\braffle\b", r"\bcontest\b", r"\bswag\b",
    r"\bhappy\s+(holi|new\s+year|holidays)", r"\bseason'?s\s+greetings\b",
    # "newsletter" 単体だと「更新通知を受け取る方法」のような技術的な質問も
    # 拾ってしまうため、コミュニティ運営のニュースレターに限定する
    r"\b(zenith|community|monthly|weekly)\s+newsletter\b",
    r"\bnewsletter\s+(sign-?up|subscri)", r"\bunsubscribe\s+from\s+the\s+newsletter\b",
    r"\bmember\s+spotlight\b", r"\bmember\s+recognition\b",
    r"\bcommunity\s+highlights?\b", r"\bmonthly\s+recap\b",
    r"\bcommunity\s+spotlight\b", r"\bcommunity\s+tip\b",
    r"\bauto\s+bumped\s+topics?\b", r"\bwhat\s+to\s+do\s+when\s+topic\s+is\s+closed\b",
    r"\binternational\s+women'?s\s+day\b", r"\bdark\s+mode\s+or\s+light\s+mode\b",
    r"\bcongratulations\b", r"\bwelcome\s+to\s+the\s+(zenith|community)",
    r"\bpodcast\b", r"\blast\s+chance\b", r"\bdon'?t\s+miss\b",
    r"\bsave\s+the\s+date\b", r"\bcyber\s+academy\b",
    r"\bcertification\s+(exam\s+)?(voucher|discount)\b",
    r"\bhands-?on\s+labs?\b", r"\btraining\s+(roadshow|event)s?\b",
    r"\binnovations?\s+highlights?\b", r"\buser\s+group\b", r"\bzug\b",
]
EXCLUDE_RE = re.compile("|".join(EXCLUDE_PATTERNS), re.IGNORECASE)


def build_session() -> requests.Session:
    """中間証明書を足した CA バンドルを使うセッションを作る。"""
    import certifi

    bundle = Path(os.environ.get("COMMUNITY_CA_BUNDLE", "")) if os.environ.get("COMMUNITY_CA_BUNDLE") else None
    if bundle is None:
        cache = Path(os.environ.get("TMPDIR", "/tmp")) / "zscaler-community-ca.pem"
        parts = [Path(certifi.where()).read_text(encoding="utf-8")]
        # プロキシ環境では既存のバンドルも信頼し続ける必要がある
        for env in ("REQUESTS_CA_BUNDLE", "SSL_CERT_FILE"):
            p = os.environ.get(env)
            if p and Path(p).exists():
                parts.append(Path(p).read_text(encoding="utf-8"))
        if CA_CHAIN_FILE.exists():
            parts.append(CA_CHAIN_FILE.read_text(encoding="utf-8"))
        cache.write_text("\n".join(parts), encoding="utf-8")
        bundle = cache

    s = requests.Session()
    s.verify = str(bundle)
    # requests は環境変数を session.verify より優先するため、明示的に上書きする
    os.environ["REQUESTS_CA_BUNDLE"] = str(bundle)
    return s


SESSION = build_session()
BROWSER_UA = ("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 "
              "(KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36")
CRAWLER_UA = ("Mozilla/5.0 (compatible; Googlebot/2.1; "
              "+http://www.google.com/bot.html)")
SESSION.headers.update({"User-Agent": BROWSER_UA})


# ── Aura クライアント ─────────────────────────────────────────────────────────

class AuraClient:
    """SPA のシェルHTMLから Aura コンテキストを取り出して API を叩く。

    fwuid は Salesforce のリリース毎に変わるので、実行の度にシェルから読み直す。
    値をハードコードすると次のリリースで静かに壊れる。
    """

    CTX_RE = re.compile(r'"fwuid"\s*:\s*"([^"]+)"')
    APP_RE = re.compile(r'"(APPLICATION@markup://siteforce:communityApp)"\s*:\s*"([^"]+)"')

    def __init__(self, session: requests.Session, delay: float = DEFAULT_DELAY):
        self.session = session
        self.delay = delay
        self.ctx: dict | None = None

    def bootstrap(self) -> None:
        r = self.session.get(f"{BASE_URL}/s/", timeout=45)
        r.raise_for_status()
        html = requests.utils.unquote(requests.utils.unquote(r.text))
        fw = self.CTX_RE.search(html)
        app = self.APP_RE.search(html)
        if not fw or not app:
            raise RuntimeError(
                "Aura コンテキスト (fwuid) をシェルHTMLから取得できませんでした。"
                "Salesforce 側の構成が変わった可能性があります。"
            )
        self.ctx = {
            "mode": "PROD", "dfs": "21", "app": "siteforce:communityApp",
            "fwuid": fw.group(1),
            "loaded": {app.group(1): app.group(2)},
            "dn": [], "globals": {}, "uad": False,
        }
        print(f"[aura] fwuid={fw.group(1)[:24]}…")

    def call(self, descriptor: str, params: dict) -> tuple[str, object]:
        if self.ctx is None:
            self.bootstrap()
        body = {
            "message": json.dumps({"actions": [{
                "id": "1;a", "descriptor": descriptor,
                "callingDescriptor": "UNKNOWN", "params": params,
            }]}),
            "aura.context": json.dumps(self.ctx),
            "aura.pageURI": "/s/",
            "aura.token": "null",
        }
        headers = {"Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
                   "Referer": f"{BASE_URL}/s/"}
        r = self.session.post(f"{AURA_URL}?r=1", data=body, headers=headers, timeout=45)
        if self.delay:
            time.sleep(self.delay)
        try:
            actions = r.json().get("actions", [])
        except ValueError:
            return "INVALID_RESPONSE", None
        if not actions:
            return "UNKNOWN_ACTION", None
        a = actions[0]
        if a.get("state") == "SUCCESS":
            return "SUCCESS", a.get("returnValue")
        try:
            msg = a["error"][0]["event"]["attributes"]["values"]["error"]["message"]
        except Exception:
            msg = str(a.get("error"))[:200]
        return "ERROR", msg

    def get_record(self, record_id: str, fields: list[str]) -> dict | None:
        state, rv = self.call(
            "aura://RecordUiController/ACTION$getRecordWithFields",
            {"recordId": record_id, "fields": fields, "optionalFields": []},
        )
        if state != "SUCCESS" or not isinstance(rv, dict):
            return None
        return rv


# ── HTML → Markdown ──────────────────────────────────────────────────────────

_INLINE_TAGS = {"strong", "b", "em", "i", "code", "a", "span", "sup", "sub", "br", "u"}


def _inline_md(node) -> str:
    if isinstance(node, NavigableString):
        return re.sub(r"\s+", " ", str(node))
    if not isinstance(node, Tag):
        return ""
    name = node.name.lower()
    inner = "".join(_inline_md(c) for c in node.children)
    if name == "br":
        return "\n"
    if name in ("strong", "b"):
        return f"**{inner.strip()}**" if inner.strip() else ""
    if name in ("em", "i"):
        return f"*{inner.strip()}*" if inner.strip() else ""
    if name == "code":
        return f"`{inner.strip()}`" if inner.strip() else ""
    if name == "a":
        href = (node.get("href") or "").strip()
        text = inner.strip() or href
        if not href:
            return text
        if href.startswith("/"):
            href = BASE_URL + href
        return f"[{text}]({href})"
    if name == "img":
        alt = (node.get("alt") or "image").strip()
        return f"[{alt}]"
    return inner


def _list_md(lst: Tag, depth: int = 0) -> list[str]:
    out: list[str] = []
    ordered = lst.name.lower() == "ol"
    idx = 1
    for li in lst.find_all("li", recursive=False):
        nested = [c for c in li.find_all(["ul", "ol"], recursive=False)]
        for n in nested:
            n.extract()
        text = "".join(_inline_md(c) for c in li.children).strip()
        text = re.sub(r"\s*\n\s*", " ", text)
        marker = f"{idx}." if ordered else "-"
        if text:
            out.append("  " * depth + f"{marker} {text}")
            idx += 1
        for n in nested:
            out.extend(_list_md(n, depth + 1))
    return out


def _table_md(table: Tag) -> list[str]:
    rows = []
    for tr in table.find_all("tr"):
        cells = tr.find_all(["th", "td"])
        if not cells:
            continue
        rows.append([re.sub(r"\s*\n\s*", " ", "".join(_inline_md(c) for c in cell.children).strip())
                     for cell in cells])
    if not rows:
        return []
    width = max(len(r) for r in rows)
    rows = [r + [""] * (width - len(r)) for r in rows]
    out = ["| " + " | ".join(rows[0]) + " |",
           "| " + " | ".join(["---"] * width) + " |"]
    for r in rows[1:]:
        out.append("| " + " | ".join(r) + " |")
    return out


def _block_md(node, out: list[str]) -> None:
    if isinstance(node, NavigableString):
        text = re.sub(r"\s+", " ", str(node)).strip()
        if text:
            out.append(text)
        return
    if not isinstance(node, Tag):
        return
    name = node.name.lower()
    if name in ("script", "style", "noscript"):
        return
    if name in ("ul", "ol"):
        out.extend(_list_md(node))
        out.append("")
        return
    if name == "table":
        out.extend(_table_md(node))
        out.append("")
        return
    if re.fullmatch(r"h[1-6]", name):
        level = min(int(name[1]) + 2, 6)
        text = "".join(_inline_md(c) for c in node.children).strip()
        if text:
            out.append(f"{'#' * level} {text}")
            out.append("")
        return
    if name in ("pre",):
        out.append("```")
        out.append(node.get_text("\n").strip())
        out.append("```")
        out.append("")
        return
    if name == "blockquote":
        inner: list[str] = []
        for c in node.children:
            _block_md(c, inner)
        out.extend(f"> {l}" if l else ">" for l in inner)
        out.append("")
        return
    if name in ("p", "div", "section", "article", "li"):
        if any(isinstance(c, Tag) and c.name.lower() in
               ("p", "div", "ul", "ol", "table", "pre", "blockquote", "section")
               for c in node.children):
            for c in node.children:
                _block_md(c, out)
            return
        text = "".join(_inline_md(c) for c in node.children).strip()
        if text:
            out.append(text)
            out.append("")
        return
    for c in node.children:
        _block_md(c, out)


def html_to_md(html: str) -> str:
    if not html:
        return ""
    soup = BeautifulSoup(html, "lxml")
    out: list[str] = []
    root = soup.body or soup
    for child in root.children:
        _block_md(child, out)
    text = "\n".join(out)
    text = re.sub(r"\n{3,}", "\n\n", text)
    return text.strip()


# ── sitemap ──────────────────────────────────────────────────────────────────

NS = "{http://www.sitemaps.org/schemas/sitemap/0.9}"


def _get_with_retry(url: str, attempts: int = 3, timeout: int = 60,
                    headers: dict | None = None) -> requests.Response | None:
    for i in range(attempts):
        try:
            r = SESSION.get(url, timeout=timeout, headers=headers)
            if r.status_code == 200:
                return r
            if r.status_code in (404, 401, 403):
                return None
        except requests.RequestException:
            pass
        if i < attempts - 1:
            time.sleep(2 ** i)
    return None


def fetch_sitemap() -> dict[str, dict]:
    """{url: {"type":…, "lastmod":…, "record_id":…, "slug":…}} を返す。"""
    r = _get_with_retry(SITEMAP_URL)
    if r is None:
        raise RuntimeError(f"sitemap index を取得できませんでした: {SITEMAP_URL}")
    index = ET.fromstring(r.content)
    children = [loc.text for loc in index.iter(f"{NS}loc") if loc.text]

    wanted = {}
    for child in children:
        m = re.search(r"sitemap-([a-z_0-9]+?)-(?:\d+|weekly)\.xml$", child)
        if m and m.group(1) in CONTENT_TYPES:
            wanted.setdefault(m.group(1), []).append(child)

    entries: dict[str, dict] = {}
    for ctype, urls in sorted(wanted.items()):
        seg = CONTENT_TYPES[ctype]
        for u in urls:
            rr = _get_with_retry(u)
            if rr is None:
                print(f"[SKIP] sitemap を取得できません: {u}")
                continue
            root = ET.fromstring(rr.content)
            for url_el in root.iter(f"{NS}url"):
                loc = url_el.findtext(f"{NS}loc")
                if not loc:
                    continue
                lastmod = url_el.findtext(f"{NS}lastmod") or ""
                m = re.search(rf"/s/{re.escape(seg)}/([A-Za-z0-9]{{15,18}})/([^/?#]*)", loc)
                if not m:
                    continue
                prev = entries.get(loc)
                if prev and prev["lastmod"] >= lastmod:
                    continue
                entries[loc] = {
                    "type": ctype, "lastmod": lastmod,
                    "record_id": m.group(1), "slug": m.group(2),
                }
        print(f"[sitemap] {ctype:20s} {sum(1 for e in entries.values() if e['type'] == ctype):5d} 件")
    return entries


# ── 分類 / 除外 ───────────────────────────────────────────────────────────────

def slug_text(slug: str) -> str:
    return slug.replace("-", " ")


def is_excluded(title: str, slug: str) -> bool:
    return bool(EXCLUDE_RE.search(f"{title} {slug_text(slug)}"))


def categorize(title: str, slug: str, body: str = "") -> str:
    """タイトル・slug を優先し、決まらなければ本文で判定する。"""
    strong = f"{title} {slug_text(slug)}".lower()
    for stem, (_, keywords) in CATEGORIES.items():
        if any(k in strong for k in keywords):
            return stem
    weak = body.lower()[:4000]
    best, best_hits = OTHER, 0
    for stem, (_, keywords) in CATEGORIES.items():
        hits = sum(weak.count(k) for k in keywords)
        if hits > best_hits:
            best, best_hits = stem, hits
    return best if best_hits >= 2 else OTHER


def category_name(stem: str) -> str:
    if stem == OTHER:
        return "その他"
    return CATEGORIES[stem][0]


# ── 本文取得 ─────────────────────────────────────────────────────────────────

QUESTION_FIELDS = [
    "FeedItem.Title", "FeedItem.Body", "FeedItem.CreatedDate",
    "FeedItem.CommentCount", "FeedItem.LikeCount",
    "FeedItem.HasVerifiedComment", "FeedItem.IsClosed", "FeedItem.BestCommentId",
]


def _fv(rec: dict, name: str):
    f = (rec.get("fields") or {}).get(name) or {}
    return f.get("value")


def fetch_via_api(aura: AuraClient, url: str, entry: dict) -> dict | None:
    """Aura UI API から取得する。質問のみ本文が取れる。"""
    if entry["type"] != "question":
        return {"unavailable": "api-no-body"}
    rec = aura.get_record(entry["record_id"], QUESTION_FIELDS)
    if rec is None:
        return None
    title = _fv(rec, "Title") or slug_text(entry["slug"]).title()
    body_md = html_to_md(_fv(rec, "Body") or "")
    return {
        "title": title,
        "body_md": body_md,
        "created": _fv(rec, "CreatedDate") or "",
        "comment_count": _fv(rec, "CommentCount"),
        "like_count": _fv(rec, "LikeCount"),
        "verified": _fv(rec, "HasVerifiedComment"),
        "closed": _fv(rec, "IsClosed"),
        "answers": None,          # UI API では取得できない
    }


_NOISE_LINES = {
    "Loading", "×", "Sorry to interrupt", "CSS Error", "Refresh", "close",
    "Expand Post", "Like", "Liked", "Unlike", "Reply", "Share", "Answer",
    "Get Started", "Products", "Zscaler Cyber Academy", "Events", "Resources",
    "Member Recognition", "Pulse Podcast", "Register", "Member Login",
    "Employee Login", "Search", "Home", "Filter Search Results", "This Category",
    "The results will appear automatically as you type",
}


def fetch_via_prerender(url: str, entry: dict) -> dict | None:
    """検索エンジン向けのサーバサイドレンダリング版を読む。

    クローラのUAを名乗ることでのみ返ってくる。--fetch-mode prerender 指定時のみ。
    """
    r = _get_with_retry(url, headers={"User-Agent": CRAWLER_UA})
    if r is None:
        return None
    soup = BeautifulSoup(r.text, "lxml")
    for t in soup(["script", "style", "noscript"]):
        t.decompose()
    title = (soup.title.string or "").strip() if soup.title else ""
    lines = [l.strip() for l in soup.get_text("\n").split("\n") if l.strip()]
    body: list[str] = []
    for l in lines:
        if l in _NOISE_LINES or len(l) <= 1:
            continue
        if body and body[-1] == l:
            continue
        body.append(l)
    # タイトルはヘッダーで別途出すので本文先頭の重複を落とす
    while body and title and body[0] == title:
        body.pop(0)
    text = "\n\n".join(body).strip()
    if len(text) < 80:
        return None
    return {
        "title": title or slug_text(entry["slug"]).title(),
        "body_md": text,
        "created": "", "comment_count": None, "like_count": None,
        "verified": None, "closed": None, "answers": "included",
    }


# ── ブロックの組み立て / 分解 ────────────────────────────────────────────────

TYPE_LABEL = {
    "question": "Q&A", "zenith_article__c": "Article",
    "zenith_guide__c": "Guide", "zenith_blog__c": "Blog",
}


def render_block(url: str, entry: dict, post: dict) -> str:
    meta = json.dumps(
        {"url": url, "lastmod": entry["lastmod"], "id": entry["record_id"]},
        ensure_ascii=False, separators=(",", ":"),
    )
    header = [f"## {post['title']}", "",
              f"- Source: {url}",
              f"- Type: {TYPE_LABEL.get(entry['type'], entry['type'])}"]
    if post.get("created"):
        header.append(f"- Posted: {post['created']}")
    if entry["lastmod"]:
        header.append(f"- Last activity: {entry['lastmod']}")
    if post.get("comment_count") is not None:
        header.append(f"- Answers: {post['comment_count']}")
    if post.get("like_count") is not None:
        header.append(f"- Likes: {post['like_count']}")
    if post.get("verified"):
        header.append("- Has verified answer: yes")
    if post.get("closed"):
        header.append("- Status: closed")
    header.append("- Note: ユーザー投稿であり Zscaler の公式見解ではない。"
                  "内容が古い場合があるため投稿日を確認すること。")
    if post.get("answers") is None and entry["type"] == "question":
        header.append("- Note: 回答本文は UI API では取得できないため未収録。")

    return (f"{BLOCK_START.format(meta=meta)}\n"
            + "\n".join(header) + "\n\n" + post["body_md"] + f"\n{BLOCK_END}")


def _part_number(path: Path) -> int:
    m = re.search(r"_part(\d+)\.md$", path.name)
    return int(m.group(1)) if m else 0


def part_name(stem: str, part: int) -> str:
    """part ファイル名。ヘルプ側 (`zia_part1.md`) と衝突させないため接頭辞を付ける。

    NotebookLM 側ではファイル名がそのままソース名になり、ソースの突き合わせも
    ファイル名で行うため、同名になると別ドキュメントセットのソースを上書き・
    削除してしまう。
    """
    return f"community_{stem}_part{part}.md"


def part_glob(stem: str) -> str:
    return f"community_{stem}_part*.md"


def block_title_and_body(block: str) -> tuple[str, str]:
    """既存ブロックから見出しと本文を取り出す（再分類用）。"""
    m = BLOCK_RE.search(block)
    inner = m.group("body") if m else block
    lines = inner.split("\n")
    title = lines[0][3:].strip() if lines and lines[0].startswith("## ") else ""
    # ヘッダー(- で始まる箇条書き)を読み飛ばして本文の開始位置を探す
    i = 1
    while i < len(lines) and (not lines[i].strip() or lines[i].startswith("- ")):
        i += 1
    return title, "\n".join(lines[i:])


def parse_existing(stem: str) -> dict[str, str]:
    blocks: dict[str, str] = {}
    for path in sorted((OUTPUT_DIR / stem).glob(part_glob(stem)), key=_part_number):
        text = path.read_text(encoding="utf-8")
        for m in BLOCK_RE.finditer(text):
            try:
                meta = json.loads(m.group("meta"))
            except json.JSONDecodeError:
                continue
            if meta.get("url"):
                blocks[meta["url"]] = m.group(0)
    return blocks


def write_parts(stem: str, blocks: dict[str, str]) -> list[Path]:
    out_dir = OUTPUT_DIR / stem
    out_dir.mkdir(parents=True, exist_ok=True)
    for old in out_dir.glob(part_glob(stem)):
        old.unlink()
    if not blocks:
        return []

    display = category_name(stem)
    written: list[Path] = []
    part = 1
    buf: list[str] = []
    size = 0

    def flush() -> None:
        nonlocal buf, size, part
        if not buf:
            return
        path = out_dir / part_name(stem, part)
        head = (f"# Zscaler Zenith Community — {display} (part {part})\n\n"
                f"Source: {BASE_URL}\n"
                f"Generated: {datetime.now(timezone.utc).strftime('%Y-%m-%d %H:%M UTC')}\n"
                f"Posts in this file: {len(buf)}\n\n"
                f"> これはユーザー投稿のコミュニティフォーラムの内容であり、"
                f"Zscaler の公式ドキュメントではない。\n\n---\n\n")
        path.write_text(head + "\n\n---\n\n".join(buf) + "\n", encoding="utf-8")
        written.append(path)
        print(f"  → {path}  ({len(head) + size:,} 文字 / {len(buf)} 件)")
        part += 1
        buf = []
        size = 0

    for url in sorted(blocks):
        block = blocks[url]
        if buf and size + len(block) > MAX_CHARS_PER_PART:
            flush()
        buf.append(block)
        size += len(block) + 8
    flush()
    return written


# ── インデックス ──────────────────────────────────────────────────────────────

def load_index() -> dict:
    if INDEX_FILE.exists():
        try:
            return json.loads(INDEX_FILE.read_text(encoding="utf-8"))
        except json.JSONDecodeError:
            print("[index] 破損しているため作り直します")
    return {"generated_at": "", "fetch_mode": "", "posts": {}, "unavailable": {}}


def save_index(index: dict) -> None:
    INDEX_FILE.parent.mkdir(parents=True, exist_ok=True)
    index["generated_at"] = datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")
    INDEX_FILE.write_text(
        json.dumps(index, ensure_ascii=False, indent=1, sort_keys=True) + "\n",
        encoding="utf-8",
    )


def block_hash(block: str) -> str:
    return hashlib.sha256(block.encode("utf-8")).hexdigest()[:16]


# ── README ───────────────────────────────────────────────────────────────────

def write_readme(index: dict, fetch_mode: str) -> None:
    OUTPUT_DIR.mkdir(parents=True, exist_ok=True)
    rows: list[str] = []
    total_words = 0
    total_files = 0
    for stem in sorted({p["category"] for p in index["posts"].values()}):
        files = sorted((OUTPUT_DIR / stem).glob(part_glob(stem)), key=_part_number)
        for f in files:
            words = len(f.read_text(encoding="utf-8").split())
            total_words += words
            total_files += 1
            rows.append(f"| `{f.relative_to(OUTPUT_DIR)}` | {category_name(stem)} | {words:,} |")

    body = [
        "# Zenith Community — NotebookLM ソース",
        "",
        f"生成: {datetime.now(timezone.utc).strftime('%Y-%m-%d %H:%M UTC')}  ",
        f"取得モード: `{fetch_mode}`  ",
        f"収録: {len(index['posts']):,} 件 / {total_files} ファイル / 約 {total_words:,} 語",
        "",
        "出典: <https://community.zscaler.com>",
        "",
        "> **注意**: これはユーザー投稿のフォーラム内容であり、Zscaler の公式",
        "> ドキュメントではない。査読されておらず古い情報も含む。help.zscaler.com",
        "> の公式ドキュメントとは**別のノートブック**に読み込むこと。",
        "",
    ]
    if fetch_mode == "api":
        body += [
            "> **このモードの制約**: Salesforce UI API の制約により、質問の本文と",
            "> メタデータのみを収録している。**回答の本文と、記事/ガイド/ブログの",
            "> 本文は含まれない**。全文を取り込むには `--fetch-mode prerender` が",
            "> 必要（クローラUAを名乗ることになる点に留意）。",
            "",
        ]
    body += ["| ファイル | カテゴリ | 語数 |", "|---|---|---|"] + rows + [""]
    (OUTPUT_DIR / "README.md").write_text("\n".join(body), encoding="utf-8")
    print(f"[readme] {OUTPUT_DIR / 'README.md'}")


def write_step_summary(stats: dict) -> None:
    path = os.environ.get("GITHUB_STEP_SUMMARY")
    if not path:
        return
    lines = [
        "## Zenith Community ドキュメント更新",
        "",
        f"- 取得モード: `{stats['fetch_mode']}`",
        f"- sitemap 総数: {stats['total']:,}",
        f"- 除外（マーケ・イベント系）: {stats['excluded']:,}",
        f"- 取得対象: {stats['targets']:,}",
        f"- 更新/新規: {stats['changed']:,}",
        f"- 本文取得不可: {stats['unavailable']:,}",
        f"- 失敗: {stats['failed']:,}",
        f"- 削除: {stats['removed']:,}",
        "",
    ]
    if stats["changed_urls"]:
        lines += ["<details><summary>更新された投稿</summary>", ""]
        lines += [f"- {u}" for u in stats["changed_urls"][:300]]
        lines += ["", "</details>", ""]
    with open(path, "a", encoding="utf-8") as f:
        f.write("\n".join(lines))


# ── メイン ───────────────────────────────────────────────────────────────────

def recategorize() -> int:
    """取得済みの part ファイルだけを使って分類をやり直す。

    CATEGORIES のキーワードを調整したあと、サイトに再度アクセスせずに
    バケットを組み直すための経路。
    """
    index = load_index()
    posts: dict[str, dict] = index.get("posts") or {}
    if not posts:
        print(f"[ERROR] {INDEX_FILE} が空です。先に通常のビルドを実行してください。",
              file=sys.stderr)
        return 1

    stems = sorted({p.get("category", OTHER) for p in posts.values()})
    all_blocks: dict[str, str] = {}
    for stem in stems:
        all_blocks.update(parse_existing(stem))
    print(f"[recat] part ファイルから {len(all_blocks):,} ブロックを読み込みました")

    moved = 0
    dropped = 0
    before = {u: posts[u].get("category") for u in posts}
    for url, block in all_blocks.items():
        meta = posts.get(url)
        if meta is None:
            continue
        title, body = block_title_and_body(block)
        slug = url.rstrip("/").rsplit("/", 1)[-1]
        # EXCLUDE_PATTERNS を足したあとの取り下げもここで反映する
        if is_excluded(title or meta.get("title", ""), slug):
            posts.pop(url, None)
            dropped += 1
            continue
        stem = categorize(title or meta.get("title", ""), slug, body)
        if stem != meta.get("category"):
            moved += 1
        meta["category"] = stem
        meta["_block"] = block

    print(f"[recat] カテゴリが変わった投稿: {moved:,} 件 / 除外対象になった投稿: {dropped:,} 件")

    touched = set(stems) | {p["category"] for p in posts.values()}
    for stem in sorted(touched):
        blocks = {u: p["_block"] for u, p in posts.items()
                  if p.get("category") == stem and p.get("_block")}
        write_parts(stem, blocks)

    for meta in posts.values():
        meta.pop("_block", None)
    index["posts"] = posts
    save_index(index)
    write_readme(index, index.get("fetch_mode", "api"))

    after: dict[str, int] = {}
    for p in posts.values():
        after[p["category"]] = after.get(p["category"], 0) + 1
    print("\n再分類後の内訳:")
    for stem, n in sorted(after.items(), key=lambda kv: -kv[1]):
        prev = sum(1 for u, c in before.items() if c == stem)
        print(f"  {stem:15s} {n:5d}  ({n - prev:+d})")
    return 0


def main() -> int:
    ap = argparse.ArgumentParser(
        description="community.zscaler.com → NotebookLM 用 Markdown")
    ap.add_argument("--full", action="store_true",
                    help="差分ではなく全件を取得して再構築する")
    ap.add_argument("--fetch-mode", choices=["api", "prerender"], default="api",
                    help="api: Aura UI API（既定・質問本文のみ） / "
                         "prerender: クローラ向けSSR（全文だがUAを偽装する）")
    ap.add_argument("--categories", nargs="*", metavar="STEM",
                    help="対象カテゴリを絞る")
    ap.add_argument("--limit", type=int, default=0,
                    help="取得件数の上限（動作確認用）")
    ap.add_argument("--workers", type=int, default=DEFAULT_WORKERS)
    ap.add_argument("--delay", type=float, default=DEFAULT_DELAY)
    ap.add_argument("--recategorize", action="store_true",
                    help="取得済みの part ファイルを CATEGORIES で分類し直す。"
                         "ネットワークアクセスなし（キーワード調整後に使う）")
    args = ap.parse_args()

    if args.recategorize:
        return recategorize()

    if args.fetch_mode == "prerender":
        print("[warn] prerender モードは Googlebot の User-Agent を名乗ります。"
              "利用規約上の判断はご自身で行ってください。")

    print(f"[1/5] sitemap を取得中 … ({SITEMAP_URL})")
    entries = fetch_sitemap()
    print(f"      合計 {len(entries):,} 件")

    index = load_index()
    posts: dict[str, dict] = index.get("posts") or {}
    mode_changed = index.get("fetch_mode") not in ("", args.fetch_mode)
    if mode_changed:
        print(f"[info] 取得モードが変わりました "
              f"({index.get('fetch_mode')} → {args.fetch_mode})。全件を取り直します。")

    # 除外・分類（本文取得前に slug/タイトルで一次判定）
    excluded = 0
    targets: dict[str, dict] = {}
    for url, e in entries.items():
        title_guess = slug_text(e["slug"])
        if is_excluded(title_guess, e["slug"]):
            excluded += 1
            continue
        targets[url] = e
    print(f"[2/5] マーケ・イベント系を除外: {excluded:,} 件 → 対象 {len(targets):,} 件")

    # 本文が取得できないと判明済みの投稿（api モードの記事/ガイド/ブログなど）。
    # 記録しておかないと毎回 1,000 件超を無駄に取りに行くことになる。
    # モードが変われば本文が取れる可能性があるので、その時は無視する。
    unavailable_seen: dict[str, str] = index.get("unavailable") or {}
    if args.full or mode_changed:
        unavailable_seen = {}

    # 変更検出
    todo: list[tuple[str, dict]] = []
    skipped_known_unavailable = 0
    for url, e in targets.items():
        prev = posts.get(url)
        if not (args.full or mode_changed) and unavailable_seen.get(url) == e["lastmod"]:
            skipped_known_unavailable += 1
            continue
        if args.full or mode_changed or prev is None or prev.get("lastmod") != e["lastmod"]:
            todo.append((url, e))
    if skipped_known_unavailable:
        print(f"      本文取得不可と判明済みのためスキップ: {skipped_known_unavailable:,} 件")
    if args.categories:
        todo = [(u, e) for u, e in todo
                if posts.get(u, {}).get("category") in args.categories
                or posts.get(u) is None]
    if args.limit:
        todo = todo[:args.limit]
    print(f"[3/5] 取得対象: {len(todo):,} 件")

    aura = AuraClient(SESSION, delay=args.delay)
    if args.fetch_mode == "api":
        aura.bootstrap()

    fetched: dict[str, dict] = {}
    failed: list[str] = []
    unavailable = 0

    def work(item):
        url, e = item
        try:
            if args.fetch_mode == "api":
                return url, e, fetch_via_api(aura, url, e)
            return url, e, fetch_via_prerender(url, e)
        except Exception as ex:
            print(f"[SKIP] {url}: {ex}")
            return url, e, None

    done = 0
    with ThreadPoolExecutor(max_workers=max(1, args.workers)) as pool:
        for url, e, post in pool.map(work, todo):
            done += 1
            if done % 100 == 0:
                print(f"      {done:,}/{len(todo):,}")
            if post is None:
                failed.append(url)
                continue
            if post.get("unavailable"):
                unavailable += 1
                unavailable_seen[url] = e["lastmod"]
                continue
            if is_excluded(post["title"], e["slug"]):
                # slug では拾えず本文取得後に判明した除外対象。以前に収録されて
                # いたなら取り下げ、以後の実行では対象から外す。
                targets.pop(url, None)
                continue
            if not post["body_md"].strip():
                unavailable += 1
                unavailable_seen[url] = e["lastmod"]
                continue
            fetched[url] = post

    print(f"[4/5] 取得成功 {len(fetched):,} / 本文取得不可 {unavailable:,} / 失敗 {len(failed):,}")

    # カテゴリごとにブロックを組み直す
    touched: set[str] = set()
    for url, post in fetched.items():
        e = targets[url]
        stem = categorize(post["title"], e["slug"], post["body_md"])
        # --categories 指定時に対象外へ分類された投稿を index に記録すると、
        # part ファイルには書かれないのに「取得済み」と見なされて次回以降
        # 取りこぼす。記録せず次回の対象として残す。
        if args.categories and stem not in args.categories:
            continue
        block = render_block(url, e, post)
        prev = posts.get(url) or {}
        if prev.get("category") and prev["category"] != stem:
            touched.add(prev["category"])
        posts[url] = {
            "category": stem, "lastmod": e["lastmod"], "type": e["type"],
            "title": post["title"], "hash": block_hash(block),
        }
        touched.add(stem)
        posts[url]["_block"] = block

    # sitemap から消えた投稿を落とす
    removed = [u for u in list(posts) if u not in targets]
    for u in removed:
        touched.add(posts[u].get("category", OTHER))
        posts.pop(u)
    if removed:
        print(f"      sitemap から消えた投稿を削除: {len(removed):,} 件")

    if args.categories:
        touched &= set(args.categories)

    print(f"[5/5] Markdown を書き出し（{len(touched)} カテゴリ）")
    for stem in sorted(touched):
        blocks = parse_existing(stem)
        for url, meta in posts.items():
            if meta.get("category") != stem:
                blocks.pop(url, None)
        for url, meta in posts.items():
            if meta.get("category") == stem and meta.get("_block"):
                blocks[url] = meta["_block"]
        for u in removed:
            blocks.pop(u, None)
        write_parts(stem, blocks)

    for meta in posts.values():
        meta.pop("_block", None)
    index["posts"] = posts
    index["fetch_mode"] = args.fetch_mode
    # sitemap から消えたものは記録からも落とす
    index["unavailable"] = {u: lm for u, lm in unavailable_seen.items() if u in targets}
    save_index(index)
    write_readme(index, args.fetch_mode)

    write_step_summary({
        "fetch_mode": args.fetch_mode, "total": len(entries), "excluded": excluded,
        "targets": len(targets), "changed": len(fetched), "unavailable": unavailable,
        "failed": len(failed), "removed": len(removed),
        "changed_urls": sorted(fetched),
    })

    if args.fetch_mode == "api" and unavailable:
        print(f"\n[note] {unavailable:,} 件は UI API では本文を取得できませんでした"
              f"（回答本文・記事/ガイド/ブログ本文）。")
    return 0


if __name__ == "__main__":
    sys.exit(main())
