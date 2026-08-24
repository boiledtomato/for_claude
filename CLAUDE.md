# CLAUDE.md

This file documents the codebase for AI assistants working in this repository.

## Project Overview

**Zscaler Learning Dashboard** — an automated pipeline that collects Zscaler-related articles from RSS feeds and surfaces them in a filterable, searchable single-page web app deployed to GitHub Pages.

Two components:
1. **`scripts/fetch_articles.py`** — Python script that fetches and normalizes articles
2. **`index.html`** — Vanilla JS/CSS single-page dashboard that reads `data/articles.json`

A GitHub Actions workflow runs both on a daily schedule and deploys the result.

## Repository Structure

```
for_claude/
├── index.html                        # Single-page app (HTML + CSS + JS, self-contained)
├── scripts/
│   ├── fetch_articles.py             # RSS aggregation script
│   ├── build_help_docs.py            # help.zscaler.com → NotebookLM Markdown builder
│   ├── build_community_docs.py       # community.zscaler.com → NotebookLM Markdown builder
│   ├── certs/
│   │   └── community-zscaler-chain.pem   # Intermediate cert the community site omits
│   └── sync_notebooklm.py            # Pushes the Markdown into a NotebookLM notebook
├── data/
│   ├── articles.json                 # Generated output — do not hand-edit
│   ├── help_docs_index.json          # Per-article state for build_help_docs.py
│   ├── help_bulletins.json           # "New & Improved Articles" snapshot
│   ├── community_docs_index.json     # Per-post state for build_community_docs.py
│   ├── notebooklm_sync_state.json    # Sync state for the help-docs notebook
│   └── community_notebooklm_sync_state.json  # Sync state for the community notebook
├── notebooklm_docs/                  # help.zscaler.com Markdown — not published
│   ├── README.md                     # File list + word counts
│   └── <category>/<category>_partN.md
├── community_docs/                   # Zenith Community Markdown — not published
│   ├── README.md
│   └── <category>/community_<category>_partN.md
├── android-app/                      # Kotlin + Compose ホームランチャー（独立プロジェクト）
│   └── app/src/main/java/com/example/zlauncher/
├── docs/
│   └── notebooklm-setup.md           # One-time auth setup for the sync
├── .github/
│   └── workflows/
│       ├── daily-update.yml          # Scheduled fetch + GitHub Pages deploy
│       ├── notebooklm-weekly.yml     # Weekly help.zscaler.com doc refresh
│       └── community-weekly.yml      # Weekly community.zscaler.com doc refresh
└── README.md
```

## Two independent NotebookLM pipelines

They share `sync_notebooklm.py` but nothing else. Keep them separate.

| | help docs | community |
|---|---|---|
| Source site | `help.zscaler.com` | `community.zscaler.com` (Zenith Community) |
| Builder | `build_help_docs.py` | `build_community_docs.py` |
| Output dir | `notebooklm_docs/` | `community_docs/` |
| Index | `data/help_docs_index.json` | `data/community_docs_index.json` |
| Sync state | `data/notebooklm_sync_state.json` | `data/community_notebooklm_sync_state.json` |
| Notebook | `Zscaler_help_docs` | `Zscaler_community` |
| Workflow | `notebooklm-weekly.yml` (Mon 00:00 UTC) | `community-weekly.yml` (Mon 01:00 UTC) |

Official documentation is reviewed; forum posts are not. Mixing them into one
notebook makes NotebookLM cite unvetted, sometimes years-old answers as
authoritative — that is why the notebooks are separate, and why every community
block carries a "not an official Zscaler statement" note plus its post date.

## Data Flow

```
RSS Feeds (9+ sources)
      │
      ▼
fetch_articles.py  →  data/articles.json
                              │
                              ▼
                         index.html  →  GitHub Pages
```

`data/articles.json` is the only interface between the Python backend and the HTML frontend. It is committed to the repo by the Actions bot on each successful run.

## Key Files

### `scripts/fetch_articles.py`

- **Runtime:** Python 3.11
- **Dependencies:** `feedparser`, `requests` — installed inline in CI, no `requirements.txt`
- **Output:** `data/articles.json` with shape:
  ```json
  {
    "last_updated": "2024-01-01T00:00:00Z",
    "total": 42,
    "articles": [
      {
        "id": "<sha256[:16] of url>",
        "title": "...",
        "url": "...",
        "summary": "<HTML-stripped, max 400 chars>",
        "date": "2024-01-01T00:00:00Z",
        "source": "Zscaler Product Blog",
        "category": "product_update | tech_blog | industry_news",
        "tags": ["ZIA", "ZPA", "ZDX"]
      }
    ]
  }
  ```

**Feed sources:**

| Constant | Description |
|---|---|
| `ZSCALER_FEEDS` | 3 Zscaler-owned feeds — all articles included unconditionally |
| `GENERAL_FEEDS` | 6 third-party security feeds — filtered by `ZSCALER_KEYWORDS` |

**Key constants** (`fetch_articles.py:18-90`):
- `MAX_ARTICLES = 200` — cap on total stored articles
- `MAX_SUMMARY_LEN = 400` — summary character limit
- `ZSCALER_KEYWORDS` — keyword list for filtering general feeds
- `PRODUCT_TAGS` — keyword→tag mapping for ZIA / ZPA / ZDX auto-tagging

**Key functions:**
- `fetch_feed(url)` — HTTP GET with 15 s timeout, returns feedparser dict or `None` on error
- `process_zscaler_feed(cfg)` — parses an owned feed, detects product tags
- `process_general_feed(cfg)` — parses a third-party feed, filters for relevance
- `_article_id(url)` — SHA-256 first 16 chars, used for deduplication
- `_clean_html(text)` — strips tags, collapses whitespace, truncates
- `_detect_tags(text)` — returns list of matching product tags
- `_is_zscaler_related(title, summary)` — keyword filter for general feeds

### `index.html`

Self-contained SPA — no build step, no external CDN dependencies, no framework.

**UI features:**
- Sticky header with full-text search (title + summary + source)
- Sidebar: category filter (product updates / tech blogs / industry news)
- Sidebar: product tag filter (ZIA / ZPA / ZDX)
- Sort by date ascending/descending
- Article count statistics
- Responsive layout (mobile/tablet breakpoints)
- Dark theme, blue/cyan accent palette
- Japanese UI labels

**Runtime behavior:** On load, fetches `data/articles.json` via `fetch()` and renders article cards. All filtering and sorting are client-side with no server calls.

### `.github/workflows/daily-update.yml`

- **Trigger:** `cron: "0 0 * * *"` (00:00 UTC = 09:00 JST) + `workflow_dispatch`
- **Permissions:** `contents: write`, `pages: write`, `id-token: write`
- **Steps:**
  1. Checkout → setup Python 3.11 → `pip install feedparser requests`
  2. `python scripts/fetch_articles.py`
  3. `git add data/articles.json` + commit only if file changed
  4. Configure Pages → upload artifact (entire repo root) → deploy
- **Commit message format:** `chore: daily article update YYYY-MM-DD`

### `scripts/build_help_docs.py`

Builds NotebookLM-ready Markdown from the **entire** `help.zscaler.com` documentation
set, and keeps it current on a weekly schedule.

**Why it does not scrape HTML:** `help.zscaler.com` is a React SPA. Every URL returns
the same ~4.7 KB shell, so `requests` + BeautifulSoup on the page HTML yields no body
text. The script calls the JSON API the SPA itself uses:

| Endpoint | Purpose |
|---|---|
| `/zapi/fetch-data?url_alias=<path>&view_type=full&…&_format=json` | One article's body as HTML, plus title, product, breadcrumbs, `nid` |
| `/zapi/bulletins?product_id=&limit=1000&…&_format=json` | The home page's **New & Improved Articles** list (~280 entries) |
| `/sitemap.xml` | All ~4,245 article URLs, each with `lastmod` |

`robots.txt` allows all product paths (only `/admin`, `/search`, `/user/*` and Drupal
internals are disallowed).

**Output layout** — one directory per functional category, split into parts:

```
notebooklm_docs/<category>/<category>_part1.md, _part2.md, …
```

Each article is wrapped in marker comments so an incremental run can take existing
part files apart and put them back together:

```markdown
<!-- ZS-ARTICLE {"url":"/zia/about-url-filtering","lastmod":"…","nid":"…"} -->
## About URL Filtering
- Source / Product / Path / Last modified / Summary
…body markdown…
<!-- /ZS-ARTICLE -->
```

The part files are therefore both the deliverable and the storage — there is no
second copy of the text in the repo.

**Categories** (`CATEGORIES` in the script) — 15 buckets covering all sitemap paths,
anything unmatched lands in `other`:
`zia`, `zpa`, `zdx`, `zcc`, `api`, `deception`, `data_security`, `exposure_mgmt`,
`branch`, `partners`, `soc_workbench`, `platform`, `ai_security`, `browser`,
`operations`.

**Key constants:**
- `MAX_CHARS_PER_PART = 1_200_000` — ≈180k words, safely under NotebookLM's 500k
  word per-source limit
- `DEFAULT_WORKERS = 5`, `DEFAULT_DELAY = 0.2` — request pacing

**Change detection** (weekly run, no `--full`): an article is refetched when it is new
to `data/help_docs_index.json`, when its sitemap `lastmod` differs from the recorded
one, or when it newly appears in **New & Improved Articles**. Articles that dropped
out of the sitemap are removed from their part file.

**Content conversion:** custom HTML→Markdown (`html_to_md`) preserving headings,
nested lists, GFM tables, links and code. API reference articles come back as
`view_type=swagger_article` with an empty `content` and a `file_url` pointing at an
OpenAPI 3 JSON — `openapi_to_md` renders those into endpoint/parameter/response
tables.

### `scripts/build_community_docs.py`

Builds NotebookLM-ready Markdown from `community.zscaler.com` (Zenith Community) —
the user forum, **not** the help documentation.

**Why it does not scrape HTML:** the site is a Salesforce Experience Cloud (Aura)
SPA. Every URL returns the same ~576 KB shell with zero body text — verified: the
shell for `/s/` and for an individual question are byte-identical.

**Content inventory** from `/s/sitemap.xml` (robots.txt is `Allow: /`):

| Sitemap | Count | URL shape |
|---|---|---|
| `question` | ~2,766 | `/s/question/<id>/<slug>` |
| `zenith_article__c` | ~426 | `/s/Articles/<id>/<slug>` |
| `zenith_guide__c` | ~406 | `/s/Guides/<id>/<slug>` |
| `zenith_blog__c` | ~388 | `/s/Blogs/<id>/<slug>` |

`topic` / `tag__c` / `collaborationgroup` / `view` are listing pages and are skipped.

**Two fetch modes** (`--fetch-mode`), because neither is strictly better:

- **`api`** (default) — calls the Aura endpoint `/s/sfsites/aura` as a guest, the
  same API the SPA uses. `fwuid` is re-read from the shell on every run because it
  changes with each Salesforce release; hardcoding it breaks silently.
  **Hard limits, all verified against the live site:**
  - `FeedComment` (answer bodies) → *"Object FeedComment is not supported in UI API"*
  - the `FeedComments` related list → *"The related lists UI API currently does not
    support this related list"*
  - `Zenith_Article__c` / `_Guide__c` / `_Blog__c` → guest field-level security
    exposes only system fields (`Id`, `Name`, dates); no body field is visible

  So this mode yields **question bodies and metadata only** — no answers, no
  articles/guides/blogs. Those are counted and reported as "本文取得不可".
- **`prerender`** — reads the server-side-rendered page Salesforce returns to
  search engines, which contains the question, every answer (with author role and
  date), and the custom-object bodies. It is only returned to recognised crawler
  UAs (Googlebot/bingbot verified; Chrome and a custom UA both get the empty
  shell), so using it means **claiming to be Googlebot**. Off by default; the
  script prints a warning when it is enabled.

**TLS gotcha:** `community.zscaler.com` serves its leaf certificate without the
DigiCert intermediate. Browsers recover via AIA fetching; `requests`/OpenSSL do
not, so certifi alone fails with `unable to get local issuer certificate` — in CI
too. `scripts/certs/community-zscaler-chain.pem` is committed for this, and
`build_session()` concatenates it with certifi (plus any existing
`REQUESTS_CA_BUNDLE`, so proxied environments keep working).

**Categories** — the community's own topic taxonomy is unusable directly: it mixes
product topics with autogenerated junk (`ffffff`, `0064b4`, `pr1`, `validate`), and
has no API topic at all. Instead `CATEGORIES` maps curated keyword sets, matched
against title + slug first and the body only as a fallback:
`zcc`, `zpa`, `zdx`, `zia`, `api`, `branch`, `data_security`, `deception`,
`platform`, plus `other`.

**Exclusions** — `EXCLUDE_PATTERNS` drops marketing/event/community-ops posts
(webinars, Zenith Live, discounts, newsletters, member spotlights). Most of
`Guides` and `Blogs` is this kind of content; leaving it in dilutes NotebookLM's
answers.

**Change detection** — the sitemap carries per-URL `lastmod`, and a thread's
`lastmod` advances when a new reply is posted, so incremental runs pick up new
answers. There are also `*-weekly.xml` sitemaps (a few dozen entries) confirming
the site changes slowly. Posts that drop out of the sitemap are removed from their
part file. Switching `--fetch-mode` forces a full refetch, since the two modes
produce different body text.

`index["unavailable"]` records the URL+`lastmod` of every post whose body could not
be retrieved, so the ~1,150 articles/guides/blogs that `api` mode can never read are
not re-requested every week. Without it a weekly run costs 1,157 requests instead of
about 4. `--full` and a `--fetch-mode` change both clear the record.

**`--recategorize`** re-buckets the already-fetched part files against the current
`CATEGORIES` and `EXCLUDE_PATTERNS` with **no network access** — the blocks stored in
the part files are the only copy of the text, so tuning keywords does not require
re-crawling the site. Use it after editing either constant.

**Part filenames carry a `community_` prefix** (`community_zia_part1.md`). This is
load-bearing, not cosmetic: NotebookLM uses the filename as the source title and
matches sources by it, so a bare `zia_part1.md` would collide with the help-docs
notebook. `part_name()` / `part_glob()` are the single source of truth — earlier the
glob in `parse_existing()` disagreed with the writer, which silently made every
incremental run drop all previously fetched content.

### `scripts/sync_notebooklm.py`

Pushes the generated Markdown into NotebookLM. **One fixed notebook, only changed
files replaced** — a new notebook per run would re-upload all ~29 MB every week and
throw away the notebook's chat history, saved notes and custom instructions.

NotebookLM has no public consumer API. This uses the community library
[`notebooklm-py`](https://github.com/teng-lin/notebooklm-py), which drives Google's
**undocumented internal API** — it can break without notice. When it does, the
fallback is uploading `notebooklm_docs/` by hand; nothing else in the pipeline depends
on it.

| | |
|---|---|
| Notebook title | `Zscaler_help_docs` (`--notebook-title` / `NOTEBOOKLM_NOTEBOOK_TITLE`) |
| Auth | Playwright `storage_state.json`; path via `NOTEBOOKLM_STORAGE_STATE`, else the library's default profile |
| Docs dir | `notebooklm_docs` (`--docs-dir` / `NOTEBOOKLM_DOCS_DIR`) |
| State | `data/notebooklm_sync_state.json` (`--state-file` / `NOTEBOOKLM_STATE_FILE`) — notebook id + per-file `sha256` and `source_id` |
| Source naming | The md filename (`zia_part1.md`) is the NotebookLM source title — that is how local files and remote sources are matched |

**Pointing it at a second doc set:** pass `--docs-dir`, `--state-file` and
`--notebook-title` together. Two rules, both load-bearing:

- **Never share a state file between doc sets.** Deletion is driven by "recorded in
  state but not present locally", so a shared state file makes each run delete the
  other notebook's sources.
- **Filenames must not collide across doc sets**, because the filename *is* the
  source title used for matching. `build_community_docs.py` therefore emits
  `community_<category>_partN.md`.

**Sync algorithm:** hash every `notebooklm_docs/*/*_part*.md`; skip files whose hash
matches the recorded one *and* whose source still exists; otherwise delete the old
source and re-add the file. A remote source with no corresponding local file is deleted
**only when the state file says this script uploaded it** — sources it does not know
about are left alone and reported as `[KEEP]`, so pointing the sync at a notebook that
already holds other material does not wipe it. Deletion is skipped entirely under
`--only`, which only sees a subset of the local files.

Flags: `--dry-run` (report adds/updates/deletes without touching anything),
`--only <category…>`, `--wait-timeout`, `--docs-dir`, `--state-file`.

**Security:** `storage_state.json` holds live Google session cookies — effectively full
account access. Prefer a dedicated Google account for this notebook rather than a
personal one.

### `.github/workflows/notebooklm-weekly.yml`

- **Trigger:** `cron: "0 0 * * 1"` (Monday 00:00 UTC = 09:00 JST) + `workflow_dispatch`
  with `mode` (`incremental` / `full`) and `categories` inputs
- **Trigger inputs:** also `sync` (`enabled` / `dry-run` / `skip`)
- **Permissions:** `contents: write` only — this workflow does not deploy Pages
- **Timeout:** 330 min (a `--full` rebuild of all ~4,200 articles takes ~2 h)
- Writes a job summary listing every changed article
- **Commit message format:** `docs: Zscaler ヘルプドキュメント週次更新 YYYY-MM-DD`
- **Steps:** build → commit docs → sync to NotebookLM → commit sync state. Docs are
  committed *before* the sync so a sync failure never loses the fetched content.
- The sync steps are skipped entirely when the `NOTEBOOKLM_STORAGE_STATE_JSON` secret
  is absent; the job summary then says to upload by hand. Note that `secrets` cannot be
  referenced in a step `if:`, so it is lifted to a job-level `env` for that test — and
  because `workflow_dispatch` inputs are empty on scheduled runs, the conditions test
  `inputs.sync != 'dry-run' && inputs.sync != 'skip'` rather than `== 'enabled'`.

### `.github/workflows/community-weekly.yml`

Same shape as `notebooklm-weekly.yml`, with the doc-set-specific values.

- **Trigger:** `cron: "0 1 * * 1"` (Monday 01:00 UTC = 10:00 JST) + `workflow_dispatch`
  with `mode`, `fetch_mode` (`api` / `prerender`), `categories`, `sync` inputs
- **Deliberately one hour after `notebooklm-weekly.yml`** — both workflows commit and
  push to the same branch, so overlapping runs would collide on push
- **Timeout:** 180 min (`--full` in `api` mode takes ~20 min)
- **Commit message format:** `docs: Zenith Community 週次更新 YYYY-MM-DD`
- Syncs with `--docs-dir community_docs --state-file
  data/community_notebooklm_sync_state.json --notebook-title Zscaler_community`

## Android launcher app (`android-app/`)

An independent Gradle project — it shares nothing with the Python/HTML pipeline and is
not deployed by any workflow. Kotlin + Jetpack Compose home launcher (`HOME` intent
filter) with three screens: a home screen (widgets, app search, an auto-sorted app grid,
a favourites dock), a card-based console screen with drag-and-drop reordering, and a
widget picker. Work profile apps appear in the same grid with badged icons.

- Build: `cd android-app && ./gradlew assembleDebug` (needs an Android SDK with
  compileSdk 36; `local.properties` is git-ignored)
- Debug builds carry `applicationIdSuffix ".debug"` so they coexist with the device's
  real launcher — **do not set a debug build as the default home until it is verified**
- Console values come from `DashboardDataSource` and are dummy data
- See `android-app/README.md` for the placement model, the Japanese app-name sorting
  limitation, and the launcher-specific manifest flags

`daily-update.yml` excludes `android-app/` (and `ios-app/`) from the Pages staging tar —
app sources are not published.

## Development Workflows

### Running the fetch script locally

```bash
pip install feedparser requests
python scripts/fetch_articles.py
```

Output is written to `data/articles.json`. The script prints per-feed counts and a total.

### Previewing the dashboard locally

Open `index.html` directly in a browser — no dev server required. If `data/articles.json` is empty or stale, run the fetch script first.

Some browsers block `fetch()` from `file://` URLs. To work around:

```bash
python -m http.server 8000
# then open http://localhost:8000
```

### Triggering the workflow manually

Use the GitHub Actions UI → "Daily Zscaler Update & Deploy" → "Run workflow", or:

```bash
gh workflow run daily-update.yml
```

### Rebuilding the NotebookLM documentation set

```bash
pip install requests beautifulsoup4 lxml

# Weekly-equivalent incremental run — only changed articles are refetched
python scripts/build_help_docs.py

# Full rebuild of everything (~2 h, ~4,200 requests)
python scripts/build_help_docs.py --full

# One category, and a small slice for a quick sanity check
python scripts/build_help_docs.py --full --categories zdx --limit 8
```

Then upload the `notebooklm_docs/<category>/*.md` files as NotebookLM sources.
NotebookLM has no public API, so that upload step is manual — see
`notebooklm_docs/README.md`.

### Rebuilding the Zenith Community documentation set

```bash
pip install requests beautifulsoup4 lxml certifi

# Weekly-equivalent incremental run
python scripts/build_community_docs.py

# Full rebuild (~3,800 posts, ~20 min in api mode)
python scripts/build_community_docs.py --full

# Include answers and article bodies — reads the crawler-facing prerender,
# which means presenting a Googlebot UA. Read the warning first.
python scripts/build_community_docs.py --full --fetch-mode prerender

# Quick sanity check
python scripts/build_community_docs.py --full --categories zia --limit 5

# After tuning CATEGORIES / EXCLUDE_PATTERNS — re-buckets without re-crawling
python scripts/build_community_docs.py --recategorize
```

Current split of the 2,649 collected posts: `zia` 727, `other` 715, `zcc` 655,
`zpa` 230, `platform` 208, `api` 39, `branch` 36, `zdx` 28, `data_security` 6,
`deception` 5. The `other` bucket is genuinely cross-cutting forum chatter; tune
`CATEGORIES` and re-run `--recategorize` if it grows.

Then sync with the community-specific flags (see `docs/notebooklm-setup.md`) or
upload `community_docs/<category>/*.md` by hand — into the **`Zscaler_community`
notebook, not the help-docs one**.

### Adding a category to the documentation set

1. Add an entry to `CATEGORIES` in `build_help_docs.py`: `stem: (display name, [url prefixes])`
2. Run `python scripts/build_help_docs.py --full --categories <stem>` to build it
3. Articles matching no prefix fall into `other` — check that bucket after
   Zscaler adds a new product area to the sitemap

For `build_community_docs.py` the shape is `stem: (display name, [keywords])` and
matching is on title/slug/body rather than URL prefix, since community URLs carry
no product path. Order matters — the first matching category wins, so put the more
specific product (e.g. `zcc`) above the broader one (`zia`).

## Conventions

### Commit messages

Use [Conventional Commits](https://www.conventionalcommits.org/) prefixes:

```
feat:   new feature
fix:    bug fix
chore:  maintenance (deps, data updates, tooling)
docs:   documentation only
refactor: code restructure without behaviour change
```

Commit bodies may be written in Japanese.

### Python style

- No formatter or linter configured — follow the existing style in `fetch_articles.py`
- Type hints on function signatures where present; keep them
- Prefer `pathlib.Path` over `os.path`
- Error handling in feed fetching: catch broadly, print `[SKIP]`, return `None`/`[]` — do not raise

### Adding a new RSS feed

1. Determine if it is Zscaler-owned or third-party
2. Add to `ZSCALER_FEEDS` or `GENERAL_FEEDS` in `fetch_articles.py` with the correct `source`, `category`, and (for Zscaler feeds) `default_tags`
3. Run locally to verify articles appear and are categorised correctly

### Adding a new product tag

1. Add a new key to `PRODUCT_TAGS` in `fetch_articles.py` with associated keywords
2. Update the sidebar filter in `index.html` to include the new tag
3. Update `ZSCALER_FEEDS` `default_tags` entries if appropriate

### Modifying article schema

`data/articles.json` is the API contract between the Python script and the frontend. If you add or remove fields:
- Update `process_zscaler_feed` and `process_general_feed` in `fetch_articles.py`
- Update the card rendering and filter logic in `index.html`
- Regenerate `data/articles.json` locally and commit the updated file

## Constraints and Gotchas

- **No requirements.txt** — dependencies (`feedparser`, `requests`) are installed inline in the workflow. If adding new Python dependencies, update the `pip install` line in `daily-update.yml`.
- **`.gitignore` covers only `__pycache__/` and `_site/`** — everything else in the repo is tracked. Avoid creating ephemeral files without adding them to `.gitignore` first.
- **`data/articles.json` is auto-committed** by the Actions bot. Avoid manually editing it; changes will be overwritten on the next run.
- **Deduplication is URL-based** via SHA-256 ID. Changing a feed's URL for an existing article will cause it to appear as a new entry.
- **General feeds are noisy** — `ZSCALER_KEYWORDS` and `PRODUCT_TAGS` keywords must stay conservative to avoid unrelated articles.
- **GitHub Pages serves a staged copy of the repo root** — `daily-update.yml` copies
  everything except `.git`, `_site`, `notebooklm_docs` and `community_docs` into
  `_site/` and publishes that. Do not place sensitive files at the top level, and keep
  both doc directories excluded: `notebooklm_docs` is a full-text reproduction of
  Zscaler's copyrighted documentation, and `community_docs` reproduces user posts
  including author display names. Neither may be served publicly. **Adding a new doc
  directory means adding a matching `--exclude` to that `tar` command.**
- **`notebooklm_docs/*.md` are machine-managed** — the `<!-- ZS-ARTICLE {…} -->` markers
  are how `build_help_docs.py` locates and replaces individual articles on an
  incremental run. Hand-editing a part file will be silently overwritten, and removing
  a marker orphans that article. `community_docs/*.md` works the same way with
  `<!-- ZS-POST {…} -->` markers.
- **The community `api` fetch mode cannot see answers** — Salesforce UI API excludes
  `FeedComment` and the custom objects' body fields from guest access. A question
  block will show `Answers: 5` with no answer text. This is a platform limit, not a
  bug; only `--fetch-mode prerender` closes it, at the cost of presenting a crawler
  User-Agent.
- **`community.zscaler.com` omits its TLS intermediate** — certifi alone fails with
  `unable to get local issuer certificate`, in CI as well as locally. The fix is the
  committed `scripts/certs/community-zscaler-chain.pem`; do not work around it by
  disabling verification.
- **Aura `fwuid` rotates with every Salesforce release** — `AuraClient.bootstrap()`
  re-reads it from the shell HTML on each run. Never hardcode it.
- **NotebookLM cannot be automated** — it has no public API. The weekly workflow keeps
  the Markdown current; re-uploading the changed files into the notebook is a manual
  step. Read the job summary of `notebooklm-weekly.yml` to see which categories changed.
- **`view_type` matters** — `/zapi/fetch-data` returns `status: 301` with a valid body for
  relocated articles (accept it), and `swagger_article` with an empty `content` for API
  reference pages (follow `file_url` to the OpenAPI JSON). Listing pages such as
  `/bulletins` legitimately have no body and are skipped.
