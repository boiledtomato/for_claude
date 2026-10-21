package com.example.critterhome.game

import kotlinx.serialization.Serializable

/**
 * 住みか 1 つにいる相棒の記録。
 *
 * **名前も姿も保存しない。** どちらも種とレベルから [MonsterSpecies] / [MonsterSprite] が
 * 毎回同じものを導く ― 保存すると、進化の判定と保存された姿が食い違ったときにどちらを
 * 信じるかを決める必要が出る。
 */
@Serializable
data class MonsterRecord(
    val exp: Long = 0,
    /** 出会った日（epoch day） */
    val metOnDay: Long = 0,
    /** 最後にこの住みかのアプリを開いた日。HP はここからの経過日数で減る */
    val lastFedDay: Long = 0,
    val launches: Long = 0,
)

/** ゲームの進み具合のすべて */
@Serializable
data class GameState(
    /**
     * 端末ごとの塩。相棒の種は「塩 + 住みか」なので、これが無いと誰の端末でも
     * Arcade には同じ相棒が出る。最初の起動で 1 度だけ決める
     */
    val salt: String = "",
    val trainerExp: Long = 0,
    val launches: Long = 0,
    /** 住みかの key → 相棒 */
    val monsters: Map<String, MonsterRecord> = emptyMap(),
    /** 獲得済みバッジの id。獲得順 */
    val badges: List<String> = emptyList(),
    val streakDays: Int = 0,
    val bestStreak: Int = 0,
    /** 最後に遊んだ日（epoch day）。-1 はまだ一度も遊んでいない */
    val lastPlayDay: Long = -1,
    /** 同じアプリの連打で稼げないようにするための直前の起動 */
    val lastLaunchPackage: String = "",
    val lastLaunchMillis: Long = 0,
    val evolutions: Int = 0,
    /** メッセージウィンドウに順に出す文。古い順 */
    val messages: List<String> = emptyList(),
    /** 最近開いたアプリの key。新しい順、最大 [GameRules.RECENT_SIZE] */
    val recent: List<String> = emptyList(),
    /** 利用者が移したアプリ: packageName → 住みかの key */
    val habitatOverrides: Map<String, String> = emptyMap(),
) {
    fun seedOf(habitatKey: String): String = "$salt/$habitatKey"
}
