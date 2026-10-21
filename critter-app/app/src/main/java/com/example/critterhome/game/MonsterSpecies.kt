package com.example.critterhome.game

import kotlin.random.Random

/**
 * 相棒の名前。**すべてこのアプリのオリジナルで、種から導く。**
 *
 * 実在のゲームのキャラクター・名前・タイプ名は使わない（権利物なので）。名前は音節表から、
 * 姿は [MonsterSprite] がその場で生成する。同じ種なら何度呼んでも同じものが出るので、
 * 保存しなくてよい。種は「端末ごとの塩 + 住みか」（[GameState.seedOf]）なので、同じ住みかでも
 * 端末が違えば別の相棒になる。
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

    /** String.hashCode は言語仕様で決まっていて端末や版で変わらないので、種として使える */
    internal fun seedOf(seed: String): Int = seed.hashCode()
}
