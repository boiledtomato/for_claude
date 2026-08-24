package com.example.zlauncher.data.dashboard

import com.example.zlauncher.domain.model.CardStatus

data class CheckRow(val label: String, val value: String, val status: CardStatus)

data class EventRow(val label: String, val time: String, val status: CardStatus)

/**
 * ダッシュボードに出す値のスナップショット。MVP では [DashboardDataSource] が固定値を返す。
 * 実データに差し替えるときも、この型より上（カード側）は変更しなくてよい。
 */
data class DashboardSnapshot(
    val updatedAt: String,
    val securityScore: Int,
    val securityStatus: CardStatus,
    val securityStatusLabel: String,
    val securityChecks: List<CheckRow>,
    val tunnelUptimePercent: Double,
    val tunnelTrend: List<Float>,
    val latencyMs: Int,
    val latencyThresholdMs: Int,
    val latencyTrend: List<Float>,
    val policyTotal: Int,
    val policyAllowPercent: Int,
    val policyRestrictPercent: Int,
    val policyBlockPercent: Int,
    val threatCount: Int,
    val threatTrend: List<Float>,
    val compliantDevices: Int,
    val totalDevices: Int,
    val deviceSyncedAt: String,
    val events: List<EventRow>,
    val dataUsageGb: Double?,
    val userActivitySessions: Int?,
)
