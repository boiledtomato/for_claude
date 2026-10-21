package com.example.zlauncher.domain.model.game

import kotlinx.serialization.Serializable

/**
 * 1 カテゴリーに 1 体いる相棒の記録。
 *
 * **種族も名前も保存しない。** どちらもカテゴリーの id とレベルから
 * [MonsterSpecies] が毎回同じものを導く ― 保存すると、進化の判定と保存された姿が
 * 食い違ったときにどちらを信じるかを決める必要が出る。
 */
@Serializable
data class MonsterRecord(
    val exp: Long = 0,
    /** 出会った日（epoch day） */
    val metOnDay: Long = 0,
    /** 最後にこのカテゴリーのアプリを開いた日。HP はここからの経過日数で減る */
    val lastFedDay: Long = 0,
    val launches: Long = 0,
)

/**
 * ゲームの進み具合のすべて。
 *
 * **ランチャー本体の設定とは別の DataStore に置く**（[com.example.zlauncher.data.game.GameRepository]）。
 * アプリを開くたびに書くので、同じ所に入れると起動のたびに設定の JSON 全体を書き直すことになる。
 */
@Serializable
data class GameState(
    val trainerExp: Long = 0,
    val launches: Long = 0,
    /** カテゴリーの id → 相棒。カテゴリーを消しても記録は残す（作り直しても別の id になる） */
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
)
