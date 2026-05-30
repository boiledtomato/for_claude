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
│   └── fetch_articles.py             # RSS aggregation script
├── data/
│   └── articles.json                 # Generated output — do not hand-edit
├── .github/
│   └── workflows/
│       └── daily-update.yml          # Scheduled fetch + GitHub Pages deploy
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
- **No `.gitignore`** — all files in the repo are tracked. Avoid creating ephemeral files (`.pyc`, `__pycache__`, etc.) without adding them to `.gitignore` first.
- **`data/articles.json` is auto-committed** by the Actions bot. Avoid manually editing it; changes will be overwritten on the next run.
- **Deduplication is URL-based** via SHA-256 ID. Changing a feed's URL for an existing article will cause it to appear as a new entry.
- **General feeds are noisy** — `ZSCALER_KEYWORDS` and `PRODUCT_TAGS` keywords must stay conservative to avoid unrelated articles.
- **GitHub Pages serves the entire repo root** — do not place sensitive files at the top level.
