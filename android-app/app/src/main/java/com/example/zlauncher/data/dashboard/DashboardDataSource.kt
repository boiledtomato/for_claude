package com.example.zlauncher.data.dashboard

import com.example.zlauncher.domain.model.CardStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

/**
 * MVP のダミーデータ。値はモックアップ（カード仕様シート）と一致させてある。
 * 実データを繋ぐときはこのクラスだけ差し替える。
 */
@Singleton
class DashboardDataSource @Inject constructor() {

    fun snapshot(): Flow<DashboardSnapshot> = flowOf(SAMPLE)

    private companion object {
        val SAMPLE = DashboardSnapshot(
            updatedAt = "14:32",
            securityScore = 92,
            securityStatus = CardStatus.GREEN,
            securityStatusLabel = "良好",
            securityChecks = listOf(
                CheckRow("URL フィルタリング", "適用済", CardStatus.GREEN),
                CheckRow("SSL 検査", "一部除外", CardStatus.AMBER),
                CheckRow("ファイアウォール", "適用済", CardStatus.GREEN),
            ),
            tunnelUptimePercent = 99.8,
            tunnelTrend = listOf(8f, 10f, 9f, 12f, 6f, 18f, 15f, 19f, 17f, 20f),
            latencyMs = 42,
            latencyThresholdMs = 40,
            latencyTrend = listOf(7f, 9f, 5f, 10f, 8f, 13f, 9f, 16f, 18f, 20f),
            policyTotal = 12_480,
            policyAllowPercent = 68,
            policyRestrictPercent = 24,
            policyBlockPercent = 8,
            threatCount = 137,
            threatTrend = listOf(38f, 52f, 44f, 68f, 56f, 74f, 100f),
            compliantDevices = 8,
            totalDevices = 9,
            deviceSyncedAt = "13:58",
            events = listOf(
                EventRow("マルウェア検出をブロック", "12:18", CardStatus.RED),
                EventRow("SSL 検査の除外を追加", "14:02", CardStatus.AMBER),
                EventRow("ポリシー同期完了", "13:40", CardStatus.GREEN),
            ),
            dataUsageGb = null,
            userActivitySessions = null,
        )
    }
}
