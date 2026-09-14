package com.example.zlauncher.ui.console

import com.example.zlauncher.data.device.DeviceMetrics
import com.example.zlauncher.domain.model.AppEntry

/** 左レールで選択中のペイン */
sealed interface ConsolePane {
    data object Overview : ConsolePane
    data class Category(val id: String) : ConsolePane

    /** Web Insights。カテゴリーから開いたときは、そのカテゴリーで絞った状態で入る */
    data class Insights(val categoryId: String? = null) : ConsolePane

    /** 置いたウィジェットの一覧と追加導線。ホームがコンソールになったのでここに置く */
    data object Widgets : ConsolePane

    /** 認証コード（TOTP）。開いている間だけ保管庫を復号する */
    data object Auth : ConsolePane
}

/**
 * 画面に出す認証コード 1 件。
 *
 * **共有鍵は含めない。** 鍵は ViewModel より内側に留め、Compose の状態には計算結果だけを渡す。
 * 状態の保存や開発ツールの覗き見に鍵が乗らないようにするため。
 */
data class OtpCodeUi(
    val id: String,
    val issuer: String,
    val account: String,
    /** 3 桁ずつ空けた表示用の文字列 */
    val grouped: String,
    /** コピー用（空白なし） */
    val raw: String,
    val secondsRemaining: Int,
    val fraction: Float,
    val colorIndex: Int,
)

/** カードが描画に使う値。実データはすべてここに集約する */
data class ConsoleSnapshot(
    val metrics: DeviceMetrics = DeviceMetrics.EMPTY,
    val appCount: Int = 0,
    val workAppCount: Int = 0,
    val recentApps: List<AppEntry> = emptyList(),
    val loaded: Boolean = false,
)
