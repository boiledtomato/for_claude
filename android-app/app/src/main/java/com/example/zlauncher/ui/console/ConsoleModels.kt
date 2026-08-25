package com.example.zlauncher.ui.console

import com.example.zlauncher.data.device.DeviceMetrics
import com.example.zlauncher.domain.model.AppEntry

/** 左レールで選択中のペイン */
sealed interface ConsolePane {
    data object Overview : ConsolePane
    data class Category(val id: String) : ConsolePane
}

/** カードが描画に使う値。実データはすべてここに集約する */
data class ConsoleSnapshot(
    val metrics: DeviceMetrics = DeviceMetrics.EMPTY,
    val appCount: Int = 0,
    val workAppCount: Int = 0,
    val recentApps: List<AppEntry> = emptyList(),
    val loaded: Boolean = false,
)
