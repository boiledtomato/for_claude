package com.example.zlauncher.domain.model

/**
 * カード ID。永続化される文字列なので、既存の値は変更しない（変更すると利用者の配置が消える）。
 * 消した ID は DashboardLayoutRepository.reconcile が保存済み配置から落とす。
 */
object CardIds {
    const val HEALTH = "health"
    const val TRAFFIC = "traffic"
    const val BATTERY = "battery"
    const val STORAGE = "storage"
    const val MEMORY = "memory"
    const val NETWORK = "network"
    const val BATTERY_TREND = "battery_trend"
    const val CLOCK = "clock"
    const val TRAFFIC_TOTAL = "traffic_total"
    const val RECENT_APPS = "recent_apps"
    const val UPTIME = "uptime"
    const val APPS = "apps"
    const val SOUND_LIGHT = "sound_light"
    const val DEVICE_INFO = "device_info"
}

/** 既定の並びと初期表示。使わないものは非表示で置いておき、編集画面から出せるようにする */
val DEFAULT_CARD_LAYOUT: List<CardLayout> = listOf(
    CardLayout(CardIds.HEALTH, CardSpan.FULL),
    CardLayout(CardIds.TRAFFIC, CardSpan.FULL),
    CardLayout(CardIds.BATTERY, CardSpan.FULL),
    CardLayout(CardIds.STORAGE, CardSpan.HALF),
    CardLayout(CardIds.MEMORY, CardSpan.HALF),
    CardLayout(CardIds.NETWORK, CardSpan.FULL),
    CardLayout(CardIds.BATTERY_TREND, CardSpan.HALF),
    CardLayout(CardIds.CLOCK, CardSpan.HALF),
    CardLayout(CardIds.TRAFFIC_TOTAL, CardSpan.FULL),
    CardLayout(CardIds.RECENT_APPS, CardSpan.FULL),
    CardLayout(CardIds.UPTIME, CardSpan.HALF, visible = false),
    CardLayout(CardIds.APPS, CardSpan.HALF, visible = false),
    CardLayout(CardIds.SOUND_LIGHT, CardSpan.FULL, visible = false),
    CardLayout(CardIds.DEVICE_INFO, CardSpan.FULL, visible = false),
)
