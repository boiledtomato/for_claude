package com.example.zlauncher.domain.model

/**
 * カード ID。永続化される文字列なので、既存の値は変更しない（変更すると利用者の配置が消える）。
 * 消した ID は DashboardLayoutRepository.reconcile が保存済み配置から落とす。
 */
object CardIds {
    const val BATTERY = "battery"
    const val STORAGE = "storage"
    const val MEMORY = "memory"
    const val NETWORK = "network"
    const val UPTIME = "uptime"
    const val APPS = "apps"
    const val RECENT_APPS = "recent_apps"
}

val DEFAULT_CARD_LAYOUT: List<CardLayout> = listOf(
    CardLayout(CardIds.BATTERY, CardSpan.FULL),
    CardLayout(CardIds.STORAGE, CardSpan.HALF),
    CardLayout(CardIds.MEMORY, CardSpan.HALF),
    CardLayout(CardIds.NETWORK, CardSpan.FULL),
    CardLayout(CardIds.UPTIME, CardSpan.HALF),
    CardLayout(CardIds.APPS, CardSpan.HALF),
    CardLayout(CardIds.RECENT_APPS, CardSpan.FULL),
)
