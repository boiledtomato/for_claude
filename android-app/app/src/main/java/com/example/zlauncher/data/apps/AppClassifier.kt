package com.example.zlauncher.data.apps

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.example.zlauncher.domain.model.AppEntry
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

/** 1 アプリぶんの提案。なぜそう判断したかを必ず持たせる */
data class AppSuggestion(
    val entry: AppEntry,
    val confidence: SuggestionConfidence,
    /** 画面に出す短い根拠。「なぜこれが出てきたのか」が分からないと受け入れようがない */
    val reason: String,
)

enum class SuggestionConfidence { HIGH, MEDIUM }

/**
 * アプリがカテゴリーに属しそうかを推定する。
 *
 * **当たりを外す前提の仕組み**なので、ここは提案までしかしない。黙って振り分けると、
 * 間違えたぶんだけ Web Insights の集計が静かに狂う。採用するかどうかは必ず人が決める。
 *
 * 使う手掛かりは 3 つ。上のものほど強い:
 *
 * 1. **`ApplicationInfo.category`** — 開発者か Play が申告したアプリの区分（9 種）。
 *    設定されていれば信頼できるが、未設定（`CATEGORY_UNDEFINED`）のアプリも多い
 * 2. **語彙表** — パッケージ名とラベルに現れる語から引く。Zscaler のカテゴリーは 100 種以上あり、
 *    その多くは Web 専用（CDN、Anonymizer 等）でアプリには当たらない。当たりうるものだけ書いてある
 * 3. **語の重なり** — アプリ名とカテゴリー名/説明文の共通語。表に無いカテゴリーでも拾えるが弱い
 */
@Singleton
class AppClassifier @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    private val packageManager: PackageManager = context.packageManager
    private val androidCategoryCache = HashMap<String, Int>()

    /**
     * [categoryName] に合いそうなアプリを、確度の高い順に返す。
     *
     * @param description カタログの説明文。語の重なりを見るのに使う。自作カテゴリーでは空でよい
     * @param exclude 既に入っているパッケージ。二重に勧めない
     */
    fun suggest(
        categoryName: String,
        description: String,
        apps: List<AppEntry>,
        exclude: Set<String>,
    ): List<AppSuggestion> {
        val keywords = AppMatching.keywordsFor(categoryName)
        val androidCategories = AppMatching.androidCategoriesFor(categoryName)
        val nameTokens = AppMatching.meaningfulTokens(categoryName) +
            AppMatching.meaningfulTokens(description)

        return apps
            .asSequence()
            .filterNot { it.packageName in exclude }
            .mapNotNull { entry -> evaluate(entry, keywords, androidCategories, nameTokens) }
            .sortedWith(compareBy({ it.confidence.ordinal }, { it.entry.label.lowercase(Locale.getDefault()) }))
            .toList()
    }

    private fun evaluate(
        entry: AppEntry,
        keywords: List<String>,
        androidCategories: Set<Int>,
        nameTokens: Set<String>,
    ): AppSuggestion? {
        val haystack = AppMatching.haystack(entry.packageName, entry.label)

        // 1) 申告された区分。合っていれば根拠として一番強い
        if (androidCategories.isNotEmpty() && androidCategoryOf(entry.packageName) in androidCategories) {
            return AppSuggestion(entry, SuggestionConfidence.HIGH, "declared app category")
        }

        // 2) 語彙表
        keywords.firstOrNull { keyword -> AppMatching.matches(keyword, haystack) }?.let { hit ->
            return AppSuggestion(entry, SuggestionConfidence.HIGH, "matches “$hit”")
        }

        // 3) カテゴリー名・説明文との語の重なり
        val overlap = nameTokens.firstOrNull { token -> AppMatching.matches(token, haystack) }
        if (overlap != null) {
            return AppSuggestion(entry, SuggestionConfidence.MEDIUM, "name mentions “$overlap”")
        }
        return null
    }

    private fun androidCategoryOf(packageName: String): Int = androidCategoryCache.getOrPut(packageName) {
        runCatching {
            @Suppress("DEPRECATION")
            packageManager.getApplicationInfo(packageName, 0).category
        }.getOrDefault(ApplicationInfo.CATEGORY_UNDEFINED)
    }
}

/**
 * 判定の中身。Android の API に触れないので単体テストできる。
 *
 * [AppClassifier] 側に置いたままだと Context が要り、語彙表の誤爆
 * （"ad" が "android" に当たる等）を検証できない。壊れ方が静かなので、ここは必ず試験する。
 */
internal object AppMatching {
    const val MIN_SUBSTRING_LENGTH = 5

    val STOP_WORDS = setOf(
            "the", "and", "for", "that", "this", "sites", "site", "other", "such", "with",
            "are", "its", "also", "includes", "including", "related", "which", "from", "you",
            "your", "app", "apps", "com", "org", "net", "android", "google", "www", "http",
            "https", "inc", "ltd", "mobile", "free", "new", "provide", "provides", "promote",
            "information", "content", "web", "internet", "online", "user", "users",
        )

        /**
         * `ApplicationInfo.category` → 対応する Zscaler の小項目。
         * 申告値は 9 種しかないので、当てられる範囲は狭い。
         */
    val ANDROID_CATEGORIES: Map<Int, List<String>> = mapOf(
            ApplicationInfo.CATEGORY_GAME to listOf("online and other games"),
            ApplicationInfo.CATEGORY_AUDIO to listOf("music and audio streaming", "radio"),
            ApplicationInfo.CATEGORY_VIDEO to listOf("video streaming", "television/movies"),
            ApplicationInfo.CATEGORY_IMAGE to listOf("image host"),
            ApplicationInfo.CATEGORY_SOCIAL to listOf("social networking"),
            ApplicationInfo.CATEGORY_NEWS to listOf("news and media"),
            ApplicationInfo.CATEGORY_MAPS to listOf("travel"),
            ApplicationInfo.CATEGORY_PRODUCTIVITY to listOf("professional services"),
        )

        /**
         * Zscaler の小項目名（小文字）→ パッケージ名・ラベルに現れる語。
         *
         * Web 専用で端末アプリには当たらないカテゴリー（CDN、Anonymizer、DNS Over HTTPS 等）は
         * 書いていない。当たらないものを無理に書くと誤検出が増えるだけなので。
         */
    val KEYWORDS: Map<String, List<String>> = mapOf(
            "music and audio streaming" to listOf(
                "spotify", "music", "audio", "podcast", "soundcloud", "deezer", "tidal", "awa",
                "shazam", "audible",
            ),
            "radio" to listOf("radiko", "radio"),
            "video streaming" to listOf(
                "youtube", "netflix", "hulu", "primevideo", "disney", "abema", "tver", "niconico",
                "twitch", "vimeo", "video",
            ),
            "television/movies" to listOf("netflix", "hulu", "disney", "movie", "cinema", "tver"),
            "entertainment" to listOf("entertainment", "comic", "manga", "anime", "kindle", "book"),
            "social networking" to listOf(
                "facebook", "instagram", "twitter", "threads", "snapchat", "mastodon", "bluesky",
                "wechat", "kakao", "weibo", "tiktok", "pinterest",
            ),
            "online chat" to listOf(
                "slack", "telegram", "signal", "whatsapp", "messenger", "discord", "chat", "line",
            ),
            "web conferencing" to listOf("zoom", "teams", "webex", "meet", "bluejeans", "skype"),
            "webmail" to listOf("gmail", "outlook", "mail", "proton", "spark", "thunderbird"),
            "online shopping" to listOf(
                "amazon", "rakuten", "mercari", "shopping", "ebay", "aliexpress", "zozo", "temu",
                "shein", "shop", "qoo10",
            ),
            "online auctions" to listOf("auction", "ebay", "yahuoku"),
            "finance" to listOf(
                "bank", "paypay", "paypal", "wallet", "moneyforward", "zaim", "finance", "kakeibo",
                "revolut", "wise", "banking",
            ),
            "online trading and brokerage" to listOf(
                "securities", "trading", "invest", "monex", "matsui", "broker", "stock",
            ),
            "insurance" to listOf("insurance", "hoken"),
            "news and media" to listOf(
                "news", "smartnews", "newspicks", "nhk", "bbc", "cnn", "reuters", "nikkei",
            ),
            "travel" to listOf(
                "maps", "travel", "transit", "navitime", "booking", "airbnb", "expedia", "jorudan",
                "norikae", "trip", "flight", "airline", "hotel", "uber", "taxi", "rideshare",
            ),
            "sports" to listOf("sports", "dazn", "football", "soccer", "baseball", "running"),
            "health" to listOf(
                "health", "fitness", "calorie", "sleep", "workout", "strava", "medical", "clinic",
                "pedometer", "wellness",
            ),
            "developer tools" to listOf(
                "github", "gitlab", "termux", "developer", "compiler", "editor", "console", "ssh",
                "docker", "postman",
            ),
            "web search" to listOf("search", "duckduckgo", "bing", "chrome", "browser", "firefox"),
            "web host" to listOf("hosting", "wordpress", "server"),
            "file host" to listOf("drive", "dropbox", "onedrive", "storage", "cloud", "sync"),
            "image host" to listOf("photo", "gallery", "camera", "flickr", "imgur"),
            "remote access tools" to listOf("anydesk", "teamviewer", "remote", "rustdesk"),
            "operating system and software updates" to listOf("updater", "vending", "firmware"),
            "government" to listOf("mynumber", "gov", "etax", "myna", "juminhyo"),
            "military" to listOf("military", "defense"),
            "continuing education/colleges" to listOf("university", "college", "campus"),
            "k-12" to listOf("school", "kids", "study", "benesse"),
            "other education" to listOf("duolingo", "udemy", "coursera", "learning", "study"),
            "reference sites" to listOf("wikipedia", "dictionary", "jisho", "reference", "wiki"),
            "translators" to listOf("translate", "deepl", "honyaku", "papago"),
            "dining/restaurant" to listOf(
                "tabelog", "gurunavi", "ubereats", "demaecan", "restaurant", "recipe", "cookpad",
                "delivery", "hotpepper",
            ),
            "real estate" to listOf("suumo", "homes", "realestate", "chintai", "athome"),
            "job/employment search" to listOf(
                "indeed", "linkedin", "wantedly", "doda", "recruit", "career", "job", "green",
            ),
            "blogs" to listOf("blog", "hatena", "medium", "note", "tumblr"),
            "discussion forums" to listOf("reddit", "forum", "quora", "2ch", "5ch"),
            "peer-to-peer site" to listOf("torrent", "p2p"),
            "generative ai and ml applications" to listOf(
                "chatgpt", "claude", "gemini", "copilot", "perplexity", "openai", "anthropic",
                "midjourney",
            ),
            "general ai and ml applications" to listOf("assistant"),
            "advertising" to listOf("adsense", "admob", "advertising"),
            "vehicles" to listOf("toyota", "honda", "nissan", "mazda", "subaru", "carnavi", "drive"),
            "alcohol/tobacco" to listOf("beer", "sake", "wine", "whisky", "tobacco"),
            "art/culture" to listOf("museum", "gallery", "culture", "artist"),
            "hobbies/leisure" to listOf("hobby", "craft", "garden", "fishing", "camp"),
            "lifestyle" to listOf("lifestyle", "fashion", "beauty", "cosme"),
            "online and other games" to listOf("game", "puzzle", "rpg", "minecraft", "roblox"),
            "social networking games" to listOf("game"),
            "portals" to listOf("portal", "yahoo"),
            "internet services" to listOf("speedtest", "network"),
            "classifieds" to listOf("jimoty", "craigslist", "classified"),
            "corporate marketing" to listOf("crm", "salesforce", "hubspot"),
            "professional services" to listOf(
                "office", "docs", "sheets", "slides", "notion", "evernote", "todo", "calendar",
                "workspace", "acrobat",
            ),
            "traditional religion" to listOf("bible", "quran", "church", "temple", "jinja"),
        "gambling" to listOf("casino", "poker", "keiba", "pachinko", "lottery"),
    )

    /** アプリ側の手掛かり。パッケージの区切りとラベルの語をまとめて持つ */
    fun haystack(packageName: String, label: String): List<String> =
        packageName.lowercase(Locale.ROOT).split('.', '_', '-') + meaningfulTokens(label)

    /**
     * 短い語を部分一致で当てない。"ad" を含むだけで android のアプリが全部
     * 広告カテゴリーに吸い込まれる、という壊れ方をするため。
     */
    fun matches(needle: String, haystack: List<String>): Boolean =
        if (needle.length < MIN_SUBSTRING_LENGTH) {
            haystack.any { it == needle }
        } else {
            haystack.any { it == needle || it.contains(needle) }
        }

    /** 助詞や汎用語は根拠にならないので落とす */
    fun meaningfulTokens(text: String): Set<String> = text
        .lowercase(Locale.ROOT)
        .split(Regex("[^\\p{L}\\p{N}]+"))
        .filter { it.length >= 3 && it !in STOP_WORDS }
        .toSet()

    fun keywordsFor(categoryName: String): List<String> =
        KEYWORDS[categoryName.trim().lowercase(Locale.ROOT)].orEmpty()

    fun androidCategoriesFor(categoryName: String): Set<Int> {
        val canonical = categoryName.trim().lowercase(Locale.ROOT)
        return ANDROID_CATEGORIES.filterValues { names -> names.any { it == canonical } }.keys
    }
}
