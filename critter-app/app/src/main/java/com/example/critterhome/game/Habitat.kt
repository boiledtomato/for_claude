package com.example.critterhome.game

import android.content.pm.ApplicationInfo

/**
 * アプリの住みか。**住みかごとに相棒が 1 体いる。**
 *
 * 利用者にカテゴリーを作らせない ― インストール済みのアプリを自動で振り分け、それぞれの
 * 住みかに相棒を割り当てる。合わなければアプリ単位で住みかを移せる（[GameState.habitatOverrides]）。
 *
 * [key] は保存に使う。表示名を変えても記録が切れないよう、表示名とは分けてある。
 */
enum class Habitat(val key: String, val title: String, val type: String) {
    ARCADE("arcade", "Arcade", "PLAY"),
    MUSIC("music", "Music Hall", "SOUND"),
    THEATER("theater", "Theater", "SCREEN"),
    GALLERY("gallery", "Gallery", "LIGHT"),
    PLAZA("plaza", "Plaza", "CHAT"),
    NEWSROOM("newsroom", "Newsroom", "INFO"),
    WILDS("wilds", "Wilds", "SKY"),
    WORKSHOP("workshop", "Workshop", "TOOL"),
    LAB("lab", "Lab", "SPARK"),
    MEADOW("meadow", "Meadow", "LEAF"),
    ;

    companion object {
        fun ofKey(key: String): Habitat? = entries.firstOrNull { it.key == key }
    }
}

/**
 * 住みかの振り分け。**Android に触れない純粋な計算**（`HabitatsTest`）。
 *
 * 1. 利用者が移したアプリはその住みか
 * 2. アプリが自分で宣言した分類（`ApplicationInfo.category`）がはっきりしていればそれ。
 *    ただし「生産性」「アクセシビリティ」は範囲が広すぎるので、先に名前の言葉を見る
 * 3. パッケージ名とアプリ名の言葉
 * 4. どれにも当たらなければ [Habitat.MEADOW]
 */
object Habitats {

    private val KEYWORDS: List<Pair<Habitat, List<String>>> = listOf(
        Habitat.ARCADE to listOf("game", "games", "puzzle", "arcade", "chess", "quiz"),
        Habitat.MUSIC to listOf("music", "spotify", "audio", "radio", "podcast", "podcasts", "soundcloud", "recorder"),
        Habitat.THEATER to listOf("video", "youtube", "netflix", "tv", "tiktok", "movie", "movies", "twitch", "hulu", "abema", "tver", "anime"),
        Habitat.GALLERY to listOf("camera", "photo", "photos", "gallery", "image", "images", "snapseed", "lightroom", "album"),
        Habitat.PLAZA to listOf(
            "chat", "mail", "gmail", "messages", "messaging", "messenger", "message", "whatsapp", "telegram",
            "signal", "discord", "twitter", "instagram", "facebook", "threads", "social", "line", "sms",
            "contacts", "dialer", "phone", "talk",
        ),
        Habitat.NEWSROOM to listOf("news", "newspaper", "reader", "feed", "feedly", "magazine", "books", "kindle"),
        Habitat.WILDS to listOf("maps", "map", "navi", "navigation", "transit", "travel", "uber", "weather", "fitness", "health", "strava", "train"),
        Habitat.WORKSHOP to listOf(
            "docs", "sheets", "slides", "office", "word", "excel", "powerpoint", "outlook", "teams", "slack",
            "zoom", "calendar", "notes", "keep", "notion", "drive", "dropbox", "todo", "tasks", "onenote",
        ),
        Habitat.LAB to listOf(
            "settings", "calculator", "clock", "files", "filemanager", "browser", "chrome", "firefox", "edge",
            "vpn", "authenticator", "vending", "store", "security", "tools", "utility", "launcher", "keyboard",
        ),
    )

    fun classify(
        packageName: String,
        label: String,
        declaredCategory: Int,
        overrides: Map<String, String> = emptyMap(),
    ): Habitat {
        overrides[packageName]?.let { key -> Habitat.ofKey(key)?.let { return it } }
        declared(declaredCategory)?.let { return it }
        byWords(packageName, label)?.let { return it }
        return when (declaredCategory) {
            ApplicationInfo.CATEGORY_PRODUCTIVITY -> Habitat.WORKSHOP
            ApplicationInfo.CATEGORY_ACCESSIBILITY -> Habitat.LAB
            else -> Habitat.MEADOW
        }
    }

    private fun declared(category: Int): Habitat? = when (category) {
        ApplicationInfo.CATEGORY_GAME -> Habitat.ARCADE
        ApplicationInfo.CATEGORY_AUDIO -> Habitat.MUSIC
        ApplicationInfo.CATEGORY_VIDEO -> Habitat.THEATER
        ApplicationInfo.CATEGORY_IMAGE -> Habitat.GALLERY
        ApplicationInfo.CATEGORY_SOCIAL -> Habitat.PLAZA
        ApplicationInfo.CATEGORY_NEWS -> Habitat.NEWSROOM
        ApplicationInfo.CATEGORY_MAPS -> Habitat.WILDS
        else -> null
    }

    /**
     * 言葉で当てる。パッケージ名とアプリ名を英数字以外で区切った語を見る。
     *
     * **短い語（4 文字以下）は語として一致したときだけ**当てる。"line" が "online"、"tv" が
     * "tvk..." に、"map" が "bitmap" に当たってしまうので。長い語は語の中に含まれていればよい
     * （"gmailgo" の gmail、"filemanager" の files）。
     */
    internal fun byWords(packageName: String, label: String): Habitat? {
        val tokens = (packageName + " " + label).lowercase().split(Regex("[^a-z0-9]+")).filter { it.isNotEmpty() }
        return KEYWORDS.firstOrNull { (_, words) ->
            words.any { word -> tokens.any { token -> token == word || (word.length > 4 && token.contains(word)) } }
        }?.first
    }
}
