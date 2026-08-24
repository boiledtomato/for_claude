package com.example.zlauncher.ui.dashboard.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zlauncher.core.designsystem.LocalStatusColors
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.designsystem.component.DashboardCardScaffold
import com.example.zlauncher.core.designsystem.component.MetricValue
import com.example.zlauncher.core.designsystem.component.MicroBars
import com.example.zlauncher.core.designsystem.component.ProgressRing
import com.example.zlauncher.core.designsystem.component.Sparkline
import com.example.zlauncher.core.designsystem.component.StackedBar
import com.example.zlauncher.core.designsystem.component.StatusDot
import com.example.zlauncher.data.dashboard.DashboardSnapshot
import com.example.zlauncher.domain.model.CardStatus

@Composable
fun SecurityPostureCard(snapshot: DashboardSnapshot) {
    DashboardCardScaffold(
        title = "セキュリティ体制",
        status = snapshot.securityStatus,
        statusLabel = snapshot.securityStatusLabel,
    ) {
        Spacer(Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            ProgressRing(progress = snapshot.securityScore / 100f, diameter = 60.dp) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("${snapshot.securityScore}", style = ZType.Metric.copy(fontSize = 19.sp), color = ZColors.TextPrimary)
                    Text("スコア", style = ZType.Sub.copy(fontSize = 9.sp), color = ZColors.TextSecondary)
                }
            }
            Spacer(Modifier.width(16.dp))
            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(9.dp),
            ) {
                snapshot.securityChecks.forEach { check ->
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = check.label,
                            style = ZType.Body.copy(fontSize = 11.5.sp),
                            color = ZColors.TextSecondary,
                            modifier = Modifier.weight(1f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        StatusDot(check.status)
                        Spacer(Modifier.width(6.dp))
                        Text(check.value, style = ZType.Body.copy(fontSize = 11.5.sp), color = ZColors.TextPrimary)
                    }
                }
            }
        }
    }
}

@Composable
fun TunnelUptimeCard(snapshot: DashboardSnapshot) {
    DashboardCardScaffold(title = "トンネル稼働率", status = CardStatus.GREEN, leadingDot = true) {
        Spacer(Modifier.height(10.dp))
        MetricValue(value = "%.1f".format(snapshot.tunnelUptimePercent), unit = "%")
        Spacer(Modifier.height(8.dp))
        Sparkline(values = snapshot.tunnelTrend, color = LocalStatusColors.current.green)
        Spacer(Modifier.height(8.dp))
        Text("過去 24 時間", style = ZType.Sub, color = ZColors.TextSecondary)
    }
}

@Composable
fun LatencyCard(snapshot: DashboardSnapshot) {
    val overThreshold = snapshot.latencyMs > snapshot.latencyThresholdMs
    val status = if (overThreshold) CardStatus.AMBER else CardStatus.GREEN
    DashboardCardScaffold(title = "平均遅延", status = status, leadingDot = true) {
        Spacer(Modifier.height(10.dp))
        MetricValue(
            value = snapshot.latencyMs.toString(),
            unit = "ms",
            color = if (overThreshold) LocalStatusColors.current.amber else ZColors.TextPrimary,
        )
        Spacer(Modifier.height(8.dp))
        Sparkline(values = snapshot.latencyTrend, color = LocalStatusColors.current.amber)
        Spacer(Modifier.height(8.dp))
        Text("しきい値 ${snapshot.latencyThresholdMs} ms", style = ZType.Sub, color = ZColors.TextSecondary)
    }
}

@Composable
fun PolicyCard(snapshot: DashboardSnapshot) {
    val colors = LocalStatusColors.current
    DashboardCardScaffold(
        title = "ポリシー適用",
        trailing = { MetricValue(value = "%,d".format(snapshot.policyTotal), unit = "件", fontSize = 18.sp) },
    ) {
        Spacer(Modifier.height(12.dp))
        StackedBar(
            segments = listOf(
                snapshot.policyAllowPercent.toFloat() to ZColors.Accent,
                snapshot.policyRestrictPercent.toFloat() to colors.amber,
                snapshot.policyBlockPercent.toFloat() to colors.red,
            )
        )
        Spacer(Modifier.height(11.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Legend(ZColors.Accent, "許可 ${snapshot.policyAllowPercent}%")
            Legend(colors.amber, "制限 ${snapshot.policyRestrictPercent}%")
            Legend(colors.red, "ブロック ${snapshot.policyBlockPercent}%")
        }
    }
}

@Composable
private fun Legend(color: androidx.compose.ui.graphics.Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        Box(Modifier.size(6.dp).background(color, androidx.compose.foundation.shape.CircleShape))
        Text(label, style = ZType.Sub.copy(fontSize = 11.sp), color = ZColors.TextSecondary)
    }
}

@Composable
fun ThreatsCard(snapshot: DashboardSnapshot) {
    DashboardCardScaffold(title = "脅威ブロック", status = CardStatus.RED, leadingDot = true) {
        Spacer(Modifier.height(10.dp))
        MetricValue(value = snapshot.threatCount.toString(), unit = "件", color = LocalStatusColors.current.red)
        Spacer(Modifier.height(10.dp))
        MicroBars(values = snapshot.threatTrend)
        Spacer(Modifier.height(9.dp))
        Text("過去 7 日", style = ZType.Sub, color = ZColors.TextSecondary)
    }
}

@Composable
fun DevicesCard(snapshot: DashboardSnapshot) {
    val pending = snapshot.totalDevices - snapshot.compliantDevices
    val status = if (pending > 0) CardStatus.AMBER else CardStatus.GREEN
    DashboardCardScaffold(title = "準拠デバイス", status = status, leadingDot = true) {
        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            ProgressRing(
                progress = snapshot.compliantDevices.toFloat() / snapshot.totalDevices.coerceAtLeast(1),
                diameter = 50.dp,
                strokeWidth = 5.dp,
                color = LocalStatusColors.current.colorFor(status),
            ) {
                Text(
                    "${snapshot.compliantDevices}/${snapshot.totalDevices}",
                    style = ZType.Metric.copy(fontSize = 14.sp),
                    color = ZColors.TextPrimary,
                )
            }
            Spacer(Modifier.width(12.dp))
            Text(
                text = if (pending > 0) "$pending 台\n要対応" else "すべて\n準拠",
                style = ZType.Body.copy(fontSize = 11.5.sp),
                color = ZColors.TextSecondary,
            )
        }
        Spacer(Modifier.height(9.dp))
        Text("前回同期 ${snapshot.deviceSyncedAt}", style = ZType.Sub, color = ZColors.TextSecondary)
    }
}

@Composable
fun EventsCard(snapshot: DashboardSnapshot) {
    DashboardCardScaffold(title = "最近のイベント") {
        Spacer(Modifier.height(8.dp))
        snapshot.events.forEach { event ->
            Row(
                Modifier.fillMaxWidth().padding(vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(9.dp),
            ) {
                StatusDot(event.status)
                Text(
                    text = event.label,
                    style = ZType.Body,
                    color = ZColors.TextPrimary,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(event.time, style = ZType.UnitText.copy(fontSize = 10.5.sp), color = ZColors.TextSecondary)
            }
        }
    }
}

@Composable
fun DataUsageCard(snapshot: DashboardSnapshot) {
    UnavailableCard(title = "データ転送量", value = snapshot.dataUsageGb?.let { "%.1f".format(it) }, unit = "GB")
}

@Composable
fun UserActivityCard(snapshot: DashboardSnapshot) {
    UnavailableCard(title = "ユーザーアクティビティ", value = snapshot.userActivitySessions?.toString(), unit = "件")
}

/** 値がまだ無いカード。色ではなくラベルで「未取得」を伝える */
@Composable
private fun UnavailableCard(title: String, value: String?, unit: String) {
    DashboardCardScaffold(
        title = title,
        status = CardStatus.UNKNOWN,
        statusLabel = if (value == null) "未取得" else null,
        leadingDot = true,
    ) {
        Spacer(Modifier.height(10.dp))
        MetricValue(
            value = value ?: "—",
            unit = value?.let { unit },
            color = if (value == null) ZColors.TextDim else ZColors.TextPrimary,
        )
        Spacer(Modifier.height(8.dp))
        Text("取得前", style = ZType.Sub, color = ZColors.TextSecondary)
    }
}
