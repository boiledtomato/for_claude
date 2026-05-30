import SwiftUI

// MARK: - Models

struct URLLookupRecord {
    let urlCategory: String
    let cloudApp: String
    let cloudAppCategory: String
    let riskLevel: LookupRisk
    let recommendedAction: LookupAction

    enum MatchType { case exact, parent, estimate, unknown }
}

enum LookupRisk: String, CaseIterable {
    case low      = "低 (Low)"
    case medium   = "中 (Medium)"
    case high     = "高 (High)"
    case critical = "重大 (Critical)"
    case unknown  = "不明"

    var color: Color {
        switch self {
        case .low:      return .green
        case .medium:   return .yellow
        case .high:     return .orange
        case .critical: return .red
        case .unknown:  return .gray
        }
    }
}

enum LookupAction: String {
    case allow   = "✅ 許可 (Allow)"
    case caution = "⚠ 注意 (Caution)"
    case block   = "🚫 ブロック (Block)"

    var color: Color {
        switch self {
        case .allow:   return .green
        case .caution: return .yellow
        case .block:   return .red
        }
    }
}

struct LookupResult {
    let fqdn: String
    let record: URLLookupRecord
    let matchType: URLLookupRecord.MatchType
    let matchedAs: String
}

// MARK: - Database

enum URLLookupData {

    // swiftlint:disable function_body_length
    static let db: [String: URLLookupRecord] = {
        typealias R = URLLookupRecord
        func r(_ cat: String, _ app: String, _ appCat: String, _ risk: LookupRisk, _ action: LookupAction) -> R {
            R(urlCategory: cat, cloudApp: app, cloudAppCategory: appCat, riskLevel: risk, recommendedAction: action)
        }
        return [
            // ── Web Search ────────────────────────────────────────────
            "google.com":           r("Web Search",                "Google Search",        "Web Search",              .low,    .allow),
            "google.co.jp":         r("Web Search",                "Google Search",        "Web Search",              .low,    .allow),
            "bing.com":             r("Web Search",                "Microsoft Bing",       "Web Search",              .low,    .allow),
            "yahoo.com":            r("Web Search",                "Yahoo Search",         "Web Search",              .low,    .allow),
            "yahoo.co.jp":          r("Web Search",                "Yahoo! JAPAN",         "Web Search",              .low,    .allow),
            "duckduckgo.com":       r("Web Search",                "DuckDuckGo",           "Web Search",              .low,    .allow),
            "baidu.com":            r("Web Search",                "Baidu",                "Web Search",              .medium, .caution),
            "yandex.com":           r("Web Search",                "Yandex",               "Web Search",              .medium, .caution),
            "perplexity.ai":        r("Web Search",                "Perplexity AI",        "AI/ML Services",          .low,    .allow),
            // ── Office Productivity ───────────────────────────────────
            "docs.google.com":      r("Business and Economy",      "Google Docs",          "Office Productivity",     .low,    .allow),
            "sheets.google.com":    r("Business and Economy",      "Google Sheets",        "Office Productivity",     .low,    .allow),
            "slides.google.com":    r("Business and Economy",      "Google Slides",        "Office Productivity",     .low,    .allow),
            "calendar.google.com":  r("Business and Economy",      "Google Calendar",      "Office Productivity",     .low,    .allow),
            "drive.google.com":     r("Online Storage and Backup", "Google Drive",         "File Storage/Sharing",    .low,    .allow),
            "mail.google.com":      r("Web-based Email",           "Gmail",                "Email",                   .low,    .allow),
            "gmail.com":            r("Web-based Email",           "Gmail",                "Email",                   .low,    .allow),
            "meet.google.com":      r("Web Conferencing",          "Google Meet",          "Video Conferencing",      .low,    .allow),
            "office.com":           r("Business and Economy",      "Microsoft 365",        "Office Productivity",     .low,    .allow),
            "microsoft.com":        r("Computer and Internet Info", "Microsoft",            "Technology",              .low,    .allow),
            "outlook.com":          r("Web-based Email",           "Microsoft Outlook",    "Email",                   .low,    .allow),
            "sharepoint.com":       r("Online Storage and Backup", "Microsoft SharePoint", "File Storage/Sharing",    .low,    .allow),
            "onedrive.live.com":    r("Online Storage and Backup", "Microsoft OneDrive",   "File Storage/Sharing",    .low,    .allow),
            "teams.microsoft.com":  r("Instant Messaging",         "Microsoft Teams",      "Collaboration",           .low,    .allow),
            "skype.com":            r("Instant Messaging",         "Skype",                "Communication",           .low,    .allow),
            "azure.microsoft.com":  r("Computer and Internet Info", "Microsoft Azure",     "Cloud Infrastructure",    .low,    .allow),
            // ── Collaboration & Messaging ─────────────────────────────
            "slack.com":            r("Instant Messaging",         "Slack",                "Collaboration",           .low,    .allow),
            "zoom.us":              r("Web Conferencing",          "Zoom",                 "Video Conferencing",      .low,    .allow),
            "webex.com":            r("Web Conferencing",          "Cisco Webex",          "Video Conferencing",      .low,    .allow),
            "gotomeeting.com":      r("Web Conferencing",          "GoTo Meeting",         "Video Conferencing",      .low,    .allow),
            "discord.com":          r("Instant Messaging",         "Discord",              "Collaboration",           .medium, .caution),
            "whatsapp.com":         r("Instant Messaging",         "WhatsApp",             "Communication",           .low,    .allow),
            "telegram.org":         r("Instant Messaging",         "Telegram",             "Communication",           .medium, .caution),
            "signal.org":           r("Instant Messaging",         "Signal",               "Communication",           .low,    .allow),
            "line.me":              r("Instant Messaging",         "LINE",                 "Communication",           .low,    .allow),
            "miro.com":             r("Business and Economy",      "Miro",                 "Collaboration",           .low,    .allow),
            // ── Social Networking ─────────────────────────────────────
            "facebook.com":         r("Social Networking",         "Facebook",             "Social Media",            .medium, .caution),
            "instagram.com":        r("Social Networking",         "Instagram",            "Social Media",            .medium, .caution),
            "twitter.com":          r("Social Networking",         "Twitter/X",            "Social Media",            .medium, .caution),
            "x.com":                r("Social Networking",         "Twitter/X",            "Social Media",            .medium, .caution),
            "linkedin.com":         r("Social Networking",         "LinkedIn",             "Social Media",            .low,    .allow),
            "tiktok.com":           r("Social Networking",         "TikTok",               "Social Media",            .high,   .caution),
            "snapchat.com":         r("Social Networking",         "Snapchat",             "Social Media",            .medium, .caution),
            "pinterest.com":        r("Social Networking",         "Pinterest",            "Social Media",            .low,    .caution),
            "reddit.com":           r("Social Networking",         "Reddit",               "Social Media",            .medium, .caution),
            "nicovideo.jp":         r("Streaming Media",           "Niconico",             "Video Streaming",         .low,    .allow),
            "ameblo.jp":            r("Personal Sites and Blogs",  "Ameba Blog",           "Social Media",            .low,    .allow),
            "note.com":             r("Personal Sites and Blogs",  "note",                 "Social Media",            .low,    .allow),
            // ── Streaming Media ───────────────────────────────────────
            "youtube.com":          r("Streaming Media",           "YouTube",              "Video Streaming",         .low,    .allow),
            "netflix.com":          r("Streaming Media",           "Netflix",              "Video Streaming",         .low,    .allow),
            "hulu.com":             r("Streaming Media",           "Hulu",                 "Video Streaming",         .low,    .allow),
            "disneyplus.com":       r("Streaming Media",           "Disney+",              "Video Streaming",         .low,    .allow),
            "primevideo.com":       r("Streaming Media",           "Amazon Prime Video",   "Video Streaming",         .low,    .allow),
            "twitch.tv":            r("Streaming Media",           "Twitch",               "Video Streaming",         .medium, .caution),
            "vimeo.com":            r("Streaming Media",           "Vimeo",                "Video Streaming",         .low,    .allow),
            "spotify.com":          r("Streaming Media",           "Spotify",              "Video Streaming",         .low,    .allow),
            "dazn.com":             r("Streaming Media",           "DAZN",                 "Video Streaming",         .low,    .allow),
            "abema.tv":             r("Streaming Media",           "ABEMA",                "Video Streaming",         .low,    .allow),
            // ── File Storage ──────────────────────────────────────────
            "dropbox.com":          r("Online Storage and Backup", "Dropbox",              "File Storage/Sharing",    .low,    .allow),
            "box.com":              r("Online Storage and Backup", "Box",                  "File Storage/Sharing",    .low,    .allow),
            "wetransfer.com":       r("Online Storage and Backup", "WeTransfer",           "File Storage/Sharing",    .medium, .caution),
            "mega.nz":              r("Online Storage and Backup", "MEGA",                 "File Storage/Sharing",    .high,   .caution),
            "icloud.com":           r("Online Storage and Backup", "iCloud",               "File Storage/Sharing",    .low,    .allow),
            // ── E-commerce ────────────────────────────────────────────
            "amazon.com":           r("Online Shopping",           "Amazon",               "E-commerce",              .low,    .allow),
            "amazon.co.jp":         r("Online Shopping",           "Amazon Japan",         "E-commerce",              .low,    .allow),
            "ebay.com":             r("Online Shopping",           "eBay",                 "E-commerce",              .low,    .allow),
            "shopify.com":          r("Business and Economy",      "Shopify",              "E-commerce",              .low,    .allow),
            "rakuten.co.jp":        r("Online Shopping",           "楽天市場",             "E-commerce",              .low,    .allow),
            "mercari.com":          r("Online Shopping",           "Mercari",              "E-commerce",              .low,    .allow),
            "alibaba.com":          r("Online Shopping",           "Alibaba",              "E-commerce",              .medium, .caution),
            "aliexpress.com":       r("Online Shopping",           "AliExpress",           "E-commerce",              .medium, .caution),
            // ── News & Media ──────────────────────────────────────────
            "cnn.com":              r("News and Media",            "CNN",                  "News",                    .low,    .allow),
            "bbc.com":              r("News and Media",            "BBC",                  "News",                    .low,    .allow),
            "reuters.com":          r("News and Media",            "Reuters",              "News",                    .low,    .allow),
            "nytimes.com":          r("News and Media",            "New York Times",       "News",                    .low,    .allow),
            "bloomberg.com":        r("News and Media",            "Bloomberg",            "News",                    .low,    .allow),
            "techcrunch.com":       r("News and Media",            "TechCrunch",           "News",                    .low,    .allow),
            "wired.com":            r("News and Media",            "WIRED",                "News",                    .low,    .allow),
            "nikkei.com":           r("News and Media",            "日本経済新聞",         "News",                    .low,    .allow),
            "asahi.com":            r("News and Media",            "朝日新聞",             "News",                    .low,    .allow),
            "yomiuri.co.jp":        r("News and Media",            "読売新聞",             "News",                    .low,    .allow),
            "mainichi.jp":          r("News and Media",            "毎日新聞",             "News",                    .low,    .allow),
            "nhk.or.jp":            r("News and Media",            "NHK",                  "News",                    .low,    .allow),
            "nhk.jp":               r("News and Media",            "NHK",                  "News",                    .low,    .allow),
            "itmedia.co.jp":        r("News and Media",            "ITmedia",              "News",                    .low,    .allow),
            "gigazine.net":         r("News and Media",            "GIGAZINE",             "News",                    .low,    .allow),
            // ── Finance ───────────────────────────────────────────────
            "paypal.com":           r("Finance",                   "PayPal",               "Finance",                 .low,    .allow),
            "stripe.com":           r("Business and Economy",      "Stripe",               "Finance",                 .low,    .allow),
            "coinbase.com":         r("Finance",                   "Coinbase",             "Finance",                 .medium, .caution),
            "binance.com":          r("Finance",                   "Binance",              "Finance",                 .medium, .caution),
            "moneyforward.com":     r("Finance",                   "Money Forward",        "Finance",                 .low,    .allow),
            "freee.co.jp":          r("Business and Economy",      "freee",                "Finance",                 .low,    .allow),
            // ── Developer Tools ───────────────────────────────────────
            "github.com":           r("Computer and Internet Info", "GitHub",              "Developer Tools",         .low,    .allow),
            "gitlab.com":           r("Computer and Internet Info", "GitLab",              "Developer Tools",         .low,    .allow),
            "stackoverflow.com":    r("Computer and Internet Info", "Stack Overflow",      "Developer Tools",         .low,    .allow),
            "aws.amazon.com":       r("Computer and Internet Info", "AWS",                 "Cloud Infrastructure",    .low,    .allow),
            "amazonaws.com":        r("Computer and Internet Info", "AWS",                 "Cloud Infrastructure",    .low,    .allow),
            "cloud.google.com":     r("Computer and Internet Info", "Google Cloud",        "Cloud Infrastructure",    .low,    .allow),
            "cloudflare.com":       r("Computer and Internet Info", "Cloudflare",          "Cloud Infrastructure",    .low,    .allow),
            "vercel.com":           r("Computer and Internet Info", "Vercel",              "Developer Tools",         .low,    .allow),
            "netlify.com":          r("Computer and Internet Info", "Netlify",             "Developer Tools",         .low,    .allow),
            "digitalocean.com":     r("Computer and Internet Info", "DigitalOcean",        "Cloud Infrastructure",    .low,    .allow),
            "docker.com":           r("Computer and Internet Info", "Docker Hub",          "Developer Tools",         .low,    .allow),
            "figma.com":            r("Computer and Internet Info", "Figma",               "Developer Tools",         .low,    .allow),
            "canva.com":            r("Business and Economy",      "Canva",                "Office Productivity",     .low,    .allow),
            // ── CRM / Enterprise ──────────────────────────────────────
            "salesforce.com":       r("Business and Economy",      "Salesforce",           "CRM",                     .low,    .allow),
            "hubspot.com":          r("Business and Economy",      "HubSpot",              "CRM",                     .low,    .allow),
            "zendesk.com":          r("Business and Economy",      "Zendesk",              "ITSM/Project Management", .low,    .allow),
            "servicenow.com":       r("Business and Economy",      "ServiceNow",           "ITSM/Project Management", .low,    .allow),
            "workday.com":          r("Business and Economy",      "Workday",              "HR/HCM",                  .low,    .allow),
            "sap.com":              r("Business and Economy",      "SAP",                  "ERP/Finance",             .low,    .allow),
            "oracle.com":           r("Business and Economy",      "Oracle",               "ERP/Finance",             .low,    .allow),
            // ── Project Management ────────────────────────────────────
            "atlassian.com":        r("Business and Economy",      "Atlassian",            "ITSM/Project Management", .low,    .allow),
            "jira.com":             r("Business and Economy",      "Jira",                 "ITSM/Project Management", .low,    .allow),
            "trello.com":           r("Business and Economy",      "Trello",               "ITSM/Project Management", .low,    .allow),
            "asana.com":            r("Business and Economy",      "Asana",                "ITSM/Project Management", .low,    .allow),
            "monday.com":           r("Business and Economy",      "Monday.com",           "ITSM/Project Management", .low,    .allow),
            "notion.so":            r("Business and Economy",      "Notion",               "Office Productivity",     .low,    .allow),
            // ── AI / LLM ──────────────────────────────────────────────
            "openai.com":           r("Computer and Internet Info", "OpenAI",              "AI/ML Services",          .low,    .allow),
            "chatgpt.com":          r("Computer and Internet Info", "ChatGPT",             "AI/ML Services",          .low,    .allow),
            "claude.ai":            r("Computer and Internet Info", "Claude (Anthropic)",  "AI/ML Services",          .low,    .allow),
            "anthropic.com":        r("Computer and Internet Info", "Anthropic",           "AI/ML Services",          .low,    .allow),
            "gemini.google.com":    r("Computer and Internet Info", "Google Gemini",       "AI/ML Services",          .low,    .allow),
            "huggingface.co":       r("Computer and Internet Info", "Hugging Face",        "AI/ML Services",          .low,    .allow),
            // ── Security ──────────────────────────────────────────────
            "zscaler.com":          r("Computer and Internet Info", "Zscaler",             "Security",                .low,    .allow),
            "zscalerone.net":       r("Computer and Internet Info", "Zscaler",             "Security",                .low,    .allow),
            "zscloud.net":          r("Computer and Internet Info", "Zscaler",             "Security",                .low,    .allow),
            "crowdstrike.com":      r("Computer and Internet Info", "CrowdStrike",         "Security",                .low,    .allow),
            "paloaltonetworks.com": r("Computer and Internet Info", "Palo Alto Networks",  "Security",                .low,    .allow),
            "okta.com":             r("Business and Economy",      "Okta",                 "Security",                .low,    .allow),
            // ── Gaming ────────────────────────────────────────────────
            "steampowered.com":     r("Games",                     "Steam",                "Gaming",                  .low,    .caution),
            "epicgames.com":        r("Games",                     "Epic Games",           "Gaming",                  .low,    .caution),
            "roblox.com":           r("Games",                     "Roblox",               "Gaming",                  .low,    .caution),
            "minecraft.net":        r("Games",                     "Minecraft",            "Gaming",                  .low,    .caution),
            "playstation.com":      r("Games",                     "PlayStation Network",  "Gaming",                  .low,    .caution),
            "xbox.com":             r("Games",                     "Xbox Network",         "Gaming",                  .low,    .caution),
            "nintendo.com":         r("Games",                     "Nintendo",             "Gaming",                  .low,    .allow),
            // ── Education ─────────────────────────────────────────────
            "coursera.org":         r("Education",                 "Coursera",             "Training",                .low,    .allow),
            "udemy.com":            r("Education",                 "Udemy",                "Training",                .low,    .allow),
            "edx.org":              r("Education",                 "edX",                  "Training",                .low,    .allow),
            "khanacademy.org":      r("Education",                 "Khan Academy",         "Training",                .low,    .allow),
            "wikipedia.org":        r("Education",                 "Wikipedia",            "Reference",               .low,    .allow),
            "pluralsight.com":      r("Education",                 "Pluralsight",          "Training",                .low,    .allow),
            // ── Health / Travel ───────────────────────────────────────
            "webmd.com":            r("Health",                    "WebMD",                "Health",                  .low,    .allow),
            "booking.com":          r("Travel",                    "Booking.com",          "Travel",                  .low,    .allow),
            "expedia.com":          r("Travel",                    "Expedia",              "Travel",                  .low,    .allow),
            "airbnb.com":           r("Travel",                    "Airbnb",               "Travel",                  .low,    .allow),
            "jal.co.jp":            r("Travel",                    "JAL",                  "Travel",                  .low,    .allow),
            "ana.co.jp":            r("Travel",                    "ANA",                  "Travel",                  .low,    .allow),
            // ── High-Risk / Proxy ─────────────────────────────────────
            "mega.nz":              r("Online Storage and Backup", "MEGA",                 "File Storage/Sharing",    .high,   .caution),
            "pastebin.com":         r("Anonymous Proxy/Anonymizer","Pastebin",             "File Storage/Sharing",    .high,   .caution),
            "proxyfree.com":        r("Anonymous Proxy/Anonymizer","ProxyFree",            "Proxy/Anonymizer",        .high,   .block),
            // ── Misc ──────────────────────────────────────────────────
            "medium.com":           r("Personal Sites and Blogs",  "Medium",               "Social Media",            .low,    .allow),
            "substack.com":         r("Personal Sites and Blogs",  "Substack",             "Social Media",            .low,    .allow),
            "wordpress.com":        r("Personal Sites and Blogs",  "WordPress.com",        "Web Hosting",             .low,    .allow),
            "apple.com":            r("Computer and Internet Info", "Apple",               "Technology",              .low,    .allow),
            "pixiv.net":            r("Arts",                      "pixiv",                "Entertainment",           .low,    .allow),
        ]
    }()

    static let samples = [
        "google.com", "facebook.com", "github.com",
        "netflix.com", "slack.com", "zscaler.com", "tiktok.com"
    ]

    // MARK: - Lookup

    static func lookup(fqdn: String) -> LookupResult {
        let norm = normalize(fqdn)
        let noWww = norm.hasPrefix("www.") ? String(norm.dropFirst(4)) : norm

        // 1. Exact
        if let rec = db[norm]   { return LookupResult(fqdn: norm, record: rec, matchType: .exact,  matchedAs: norm)   }
        if let rec = db[noWww]  { return LookupResult(fqdn: norm, record: rec, matchType: .exact,  matchedAs: noWww)  }

        // 2. Parent domain walk
        let parts = noWww.split(separator: ".")
        if parts.count > 2 {
            for i in 1..<(parts.count - 1) {
                let parent = parts[i...].joined(separator: ".")
                if let rec = db[parent] {
                    return LookupResult(fqdn: norm, record: rec, matchType: .parent, matchedAs: parent)
                }
            }
        }

        // 3. TLD heuristic
        let tldHints: [(tld: String, cat: String, appCat: String, risk: LookupRisk)] = [
            ("gov.jp", "Government",                "Government",    .low),
            ("gov",    "Government",                "Government",    .low),
            ("ac.jp",  "Education",                 "Training",      .low),
            ("edu",    "Education",                 "Training",      .low),
            ("or.jp",  "Non-Profit/Advocacy Groups","Non-Profit",    .low),
            ("org",    "Non-Profit/Advocacy Groups","Non-Profit",    .low),
            ("co.jp",  "Business and Economy",      "Business Apps", .low),
        ]
        for h in tldHints where noWww.hasSuffix(".\(h.tld)") {
            let rec = URLLookupRecord(urlCategory: h.cat, cloudApp: "—", cloudAppCategory: h.appCat,
                                      riskLevel: h.risk, recommendedAction: .allow)
            return LookupResult(fqdn: norm, record: rec, matchType: .estimate, matchedAs: noWww)
        }

        // 4. Fallback
        let rec = URLLookupRecord(urlCategory: "Miscellaneous", cloudApp: "—", cloudAppCategory: "—",
                                   riskLevel: .unknown, recommendedAction: .caution)
        return LookupResult(fqdn: norm, record: rec, matchType: .unknown, matchedAs: noWww)
    }

    static func isValidFQDN(_ s: String) -> Bool {
        let n = normalize(s)
        let pattern = #"^[a-z0-9][a-z0-9\-\.]*\.[a-z]{2,}$"#
        return n.range(of: pattern, options: .regularExpression) != nil
    }

    private static func normalize(_ raw: String) -> String {
        var s = raw.trimmingCharacters(in: .whitespaces).lowercased()
        if s.hasPrefix("https://") { s = String(s.dropFirst(8)) }
        else if s.hasPrefix("http://") { s = String(s.dropFirst(7)) }
        s = s.components(separatedBy: "/").first ?? s
        s = s.components(separatedBy: ":").first ?? s
        s = s.hasPrefix(".") ? String(s.dropFirst()) : s
        return s
    }
}
