package com.example.zlauncher.domain.model.game

import kotlin.random.Random

/**
 * 相棒の種族。**すべてこのプロジェクトのオリジナルで、カテゴリーの id から導く。**
 *
 * 実在のゲームのキャラクター・名前・タイプ名は使わない（権利物なので）。名前は音節表から、
 * 姿は [MonsterSprite] がその場で生成する。同じ id なら何度呼んでも同じものが出るので、
 * 保存しなくてよい。
 *
 * 進化しても**最初の音節は変えない**。姿が大きく変わっても、名前の頭で同じ系統だと分かる。
 */
object MonsterSpecies {

    private val HEADS = listOf(
        "Bo", "Ki", "Ru", "Zo", "Mi", "Ta", "Ne", "Lu", "Po", "Ga",
        "Shi", "Ko", "Ra", "Vi", "No", "Pe", "Du", "Fa", "Jo", "Su",
    )
    private val MIDS = listOf(
        "bu", "ri", "mo", "ta", "lo", "ne", "su", "ka", "zu", "vo",
        "ni", "ra", "pu", "le", "do", "mi",
    )
    private val STAGE1 = listOf("rix", "dor", "lume", "bolt", "fang", "wing", "shell", "horn")
    private val STAGE2 = listOf("gon", "tron", "nova", "lord", "rex", "max", "crown", "titan")

    /**
     * タイプ。カテゴリー名の言葉から選び、どれにも当たらなければ id から決める。
     * 名前を変えればタイプも変わるが、それはカテゴリーの意味が変わったということ。
     */
    private val TYPE_KEYWORDS: List<Pair<String, List<String>>> = listOf(
        "CHAT" to listOf("social", "chat", "messag", "sns", "communit", "mail"),
        "COIN" to listOf("financ", "bank", "pay", "money", "crypto", "stock"),
        "PLAY" to listOf("game", "gaming", "play"),
        "INFO" to listOf("news", "info", "weather", "blog"),
        "TRADE" to listOf("shop", "commerce", "store", "auction"),
        "WAVE" to listOf("music", "video", "stream", "radio", "entertain", "media", "photo"),
        "SKY" to listOf("travel", "map", "navi", "transport"),
        "TOOL" to listOf("work", "business", "office", "product", "tool", "util"),
        "SHIELD" to listOf("secur", "vpn", "auth", "zscaler", "password"),
        "VITAL" to listOf("health", "fitness", "sport", "medic"),
        "LORE" to listOf("learn", "educat", "book", "reference", "ai", "search"),
    )

    val TYPES: List<String> = TYPE_KEYWORDS.map { it.first }

    /** 段階（0 / 1 / 2）ごとの名前 */
    fun name(seed: String, stage: Int): String {
        val rng = Random(seedOf(seed))
        val head = HEADS[rng.nextInt(HEADS.size)]
        val mid = MIDS[rng.nextInt(MIDS.size)]
        val s1 = STAGE1[rng.nextInt(STAGE1.size)]
        val s2 = STAGE2[rng.nextInt(STAGE2.size)]
        return when (stage.coerceIn(0, 2)) {
            0 -> head + mid
            1 -> head + mid.first() + s1
            else -> head + s2
        }
    }

    fun type(seed: String, categoryName: String): String {
        val lower = categoryName.lowercase()
        TYPE_KEYWORDS.firstOrNull { (_, words) -> words.any { word -> containsWord(lower, word) } }
            ?.let { return it.first }
        return TYPES[Math.floorMod(seedOf(seed), TYPES.size)]
    }

    /**
     * 長い語はどこに含まれていても当てる（"Webmail" の mail）。2 文字の語だけは語の頭に限る ―
     * "ai" が "mail" や "training" に当たってしまうので、英字が直前に無い位置だけを見る。
     */
    private fun containsWord(text: String, word: String): Boolean {
        if (word.length > 2) return word in text
        var from = 0
        while (true) {
            val at = text.indexOf(word, from)
            if (at < 0) return false
            if (at == 0 || !text[at - 1].isLetter()) return true
            from = at + 1
        }
    }

    /** String.hashCode は言語仕様で決まっていて端末や版で変わらないので、種として使える */
    internal fun seedOf(seed: String): Int = seed.hashCode()
}
