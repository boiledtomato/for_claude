package com.example.zlauncher.domain.model

/**
 * カード ID。永続化される文字列なので、既存の値は変更しない（変更すると利用者の配置が消える）。
 */
object CardIds {
    const val SECURITY_POSTURE = "security_posture"
    const val TUNNEL_UPTIME = "tunnel_uptime"
    const val LATENCY = "latency"
    const val POLICY = "policy"
    const val THREATS = "threats"
    const val DEVICES = "devices"
    const val EVENTS = "events"
    const val DATA_USAGE = "data_usage"
    const val USER_ACTIVITY = "user_activity"
}

val DEFAULT_CARD_LAYOUT: List<CardLayout> = listOf(
    CardLayout(CardIds.SECURITY_POSTURE, CardSpan.FULL),
    CardLayout(CardIds.TUNNEL_UPTIME, CardSpan.HALF),
    CardLayout(CardIds.LATENCY, CardSpan.HALF),
    CardLayout(CardIds.POLICY, CardSpan.FULL),
    CardLayout(CardIds.THREATS, CardSpan.HALF),
    CardLayout(CardIds.DEVICES, CardSpan.HALF),
    CardLayout(CardIds.EVENTS, CardSpan.FULL),
    CardLayout(CardIds.DATA_USAGE, CardSpan.HALF, visible = false),
    CardLayout(CardIds.USER_ACTIVITY, CardSpan.HALF, visible = false),
)
