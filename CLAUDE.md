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
│   └── build_help_docs.py            # help.zscaler.com → NotebookLM Markdown builder
├── data/
│   ├── articles.json                 # Generated output — do not hand-edit
│   ├── help_docs_index.json          # Per-article state for build_help_docs.py
│   └── help_bulletins.json           # "New & Improved Articles" snapshot
├── notebooklm_docs/                  # Generated Markdown for NotebookLM — not published
│   ├── README.md                     # File list + word counts
│   └── <category>/<category>_partN.md
├── .github/
│   └── workflows/
│       ├── daily-update.yml          # Scheduled fetch + GitHub Pages deploy
│       └── notebooklm-weekly.yml     # Weekly help.zscaler.com doc refresh
└── README.md
```

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
| State | `data/notebooklm_sync_state.json` — notebook id + per-file `sha256` and `source_id` |
| Source naming | The md filename (`zia_part1.md`) is the NotebookLM source title — that is how local files and remote sources are matched |

**Sync algorithm:** hash every `notebooklm_docs/*/*_part*.md`; skip files whose hash
matches the recorded one *and* whose source still exists; otherwise delete the old
source and re-add the file. Remote sources with no corresponding local file are
deleted (suppressed under `--only`, which sees just a subset).

Flags: `--dry-run` (report adds/updates/deletes without touching anything),
`--only <category…>`, `--wait-timeout`.

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

### Adding a category to the documentation set

1. Add an entry to `CATEGORIES` in `build_help_docs.py`: `stem: (display name, [url prefixes])`
2. Run `python scripts/build_help_docs.py --full --categories <stem>` to build it
3. Articles matching no prefix fall into `other` — check that bucket after
   Zscaler adds a new product area to the sitemap

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
  everything except `.git`, `_site` and `notebooklm_docs` into `_site/` and publishes
  that. Do not place sensitive files at the top level, and keep `notebooklm_docs`
  excluded: it is a full-text reproduction of Zscaler's copyrighted documentation and
  must not be served publicly.
- **`notebooklm_docs/*.md` are machine-managed** — the `<!-- ZS-ARTICLE {…} -->` markers
  are how `build_help_docs.py` locates and replaces individual articles on an
  incremental run. Hand-editing a part file will be silently overwritten, and removing
  a marker orphans that article.
- **NotebookLM cannot be automated** — it has no public API. The weekly workflow keeps
  the Markdown current; re-uploading the changed files into the notebook is a manual
  step. Read the job summary of `notebooklm-weekly.yml` to see which categories changed.
- **`view_type` matters** — `/zapi/fetch-data` returns `status: 301` with a valid body for
  relocated articles (accept it), and `swagger_article` with an empty `content` for API
  reference pages (follow `file_url` to the OpenAPI JSON). Listing pages such as
  `/bulletins` legitimately have no body and are skipped.
