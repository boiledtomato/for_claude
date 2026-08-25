package com.example.zlauncher.ui.console.card

import android.content.Intent
import android.provider.Settings
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zlauncher.core.designsystem.LocalStatusColors
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.designsystem.component.DashboardCardScaffold
import com.example.zlauncher.core.designsystem.component.MetricValue
import com.example.zlauncher.core.designsystem.component.ProgressRing
import com.example.zlauncher.core.designsystem.component.StackedBar
import com.example.zlauncher.core.designsystem.component.StatusDot
import com.example.zlauncher.core.ui.formatBytes
import com.example.zlauncher.core.ui.formatDuration
import com.example.zlauncher.data.device.NetworkKind
import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.domain.model.CardStatus
import com.example.zlauncher.ui.console.ConsoleSnapshot
import com.example.zlauncher.ui.home.component.AppIconTile
import com.example.zlauncher.ui.home.component.rememberAppIcon

/** カードが必要とするもの一式。実データと、押したときの動作 */
data class CardContext(
    val snapshot: ConsoleSnapshot,
    val iconProvider: suspend (AppEntry) -> ImageBitmap?,
    val onOpenSettings: (String) -> Unit,
    val onLaunchApp: (AppEntry) -> Unit,
)

private fun thresholdStatus(ratio: Float): CardStatus = when {
    ratio >= 0.90f -> CardStatus.RED
    ratio >= 0.75f -> CardStatus.AMBER
    else -> CardStatus.GREEN
}

@Composable
fun BatteryCard(context: CardContext) {
    val metrics = context.snapshot.metrics
    val status = when {
        metrics.batteryCharging -> CardStatus.GREEN
        metrics.batteryPercent >= 50 -> CardStatus.GREEN
        metrics.batteryPercent >= 20 -> CardStatus.AMBER
        else -> CardStatus.RED
    }
    val progress by animateFloatAsState(
        targetValue = metrics.batteryPercent / 100f,
        animationSpec = tween(700),
        label = "battery",
    )
    DashboardCardScaffold(
        title = "バッテリー",
        status = status,
        statusLabel = if (metrics.batteryCharging) "充電中" else null,
        onClick = { context.onOpenSettings(Intent.ACTION_POWER_USAGE_SUMMARY) },
    ) {
        Spacer(Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            ProgressRing(
                progress = progress,
                diameter = 60.dp,
                color = LocalStatusColors.current.colorFor(status),
            ) {
                Text(
                    "${metrics.batteryPercent}",
                    style = ZType.Metric.copy(fontSize = 19.sp),
                    color = ZColors.TextPrimary,
                )
            }
            Spacer(Modifier.width(16.dp))
            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                DetailRow("状態", if (metrics.batteryCharging) "充電中" else "放電中", status)
                DetailRow("残量", "${metrics.batteryPercent} %", status)
                DetailRow(
                    label = "温度",
                    value = String.format(java.util.Locale.US, "%.1f ℃", metrics.batteryTemperatureC),
                    status = if (metrics.batteryTemperatureC >= 45f) CardStatus.AMBER else CardStatus.GREEN,
                )
            }
        }
    }
}

@Composable
fun StorageCard(context: CardContext) {
    val metrics = context.snapshot.metrics
    val status = thresholdStatus(metrics.storageUsedRatio)
    val (used, usedUnit) = formatBytes(metrics.storageUsedBytes)
    val (free, freeUnit) = formatBytes(metrics.storageFreeBytes)
    val ratio by animateFloatAsState(metrics.storageUsedRatio, tween(700), label = "storage")

    DashboardCardScaffold(
        title = "ストレージ",
        status = status,
        leadingDot = true,
        onClick = { context.onOpenSettings(Settings.ACTION_INTERNAL_STORAGE_SETTINGS) },
    ) {
        Spacer(Modifier.height(10.dp))
        MetricValue(value = used, unit = usedUnit, color = LocalStatusColors.current.colorFor(status))
        Spacer(Modifier.height(10.dp))
        StackedBar(
            segments = listOf(
                ratio.coerceAtLeast(0.001f) to LocalStatusColors.current.colorFor(status),
                (1f - ratio).coerceAtLeast(0.001f) to ZColors.StatusNeutral,
            )
        )
        Spacer(Modifier.height(9.dp))
        Text("空き $free $freeUnit", style = ZType.Sub, color = ZColors.TextSecondary)
    }
}

@Composable
fun MemoryCard(context: CardContext) {
    val metrics = context.snapshot.metrics
    val status = thresholdStatus(metrics.memoryUsedRatio)
    val (used, usedUnit) = formatBytes(metrics.memoryUsedBytes)
    val (total, totalUnit) = formatBytes(metrics.memoryTotalBytes)
    val ratio by animateFloatAsState(metrics.memoryUsedRatio, tween(700), label = "memory")

    DashboardCardScaffold(title = "メモリ", status = status, leadingDot = true) {
        Spacer(Modifier.height(10.dp))
        MetricValue(value = used, unit = usedUnit, color = LocalStatusColors.current.colorFor(status))
        Spacer(Modifier.height(10.dp))
        StackedBar(
            segments = listOf(
                ratio.coerceAtLeast(0.001f) to LocalStatusColors.current.colorFor(status),
                (1f - ratio).coerceAtLeast(0.001f) to ZColors.StatusNeutral,
            )
        )
        Spacer(Modifier.height(9.dp))
        Text("全体 $total $totalUnit", style = ZType.Sub, color = ZColors.TextSecondary)
    }
}

@Composable
fun NetworkCard(context: CardContext) {
    val metrics = context.snapshot.metrics
    val status = when {
        metrics.network == NetworkKind.NONE -> CardStatus.RED
        !metrics.networkValidated -> CardStatus.AMBER
        else -> CardStatus.GREEN
    }
    val kindLabel = when (metrics.network) {
        NetworkKind.WIFI -> "Wi-Fi"
        NetworkKind.CELLULAR -> "モバイル通信"
        NetworkKind.ETHERNET -> "有線"
        NetworkKind.OTHER -> "その他"
        NetworkKind.NONE -> "未接続"
    }
    DashboardCardScaffold(
        title = "ネットワーク",
        status = status,
        statusLabel = if (metrics.online) "オンライン" else "要確認",
        onClick = { context.onOpenSettings(Settings.ACTION_WIRELESS_SETTINGS) },
    ) {
        Spacer(Modifier.height(12.dp))
        Column(verticalArrangement = Arrangement.spacedBy(9.dp)) {
            DetailRow("接続", kindLabel, status)
            DetailRow(
                label = "VPN",
                value = if (metrics.vpnActive) "接続済" else "未接続",
                status = if (metrics.vpnActive) CardStatus.GREEN else CardStatus.NEUTRAL,
            )
            DetailRow(
                label = "推定下り",
                value = if (metrics.downstreamKbps > 0) "${metrics.downstreamKbps / 1000} Mbps" else "—",
                status = CardStatus.NEUTRAL,
            )
        }
    }
}

@Composable
fun UptimeCard(context: CardContext) {
    DashboardCardScaffold(title = "連続稼働", status = CardStatus.NEUTRAL, leadingDot = true) {
        Spacer(Modifier.height(10.dp))
        Text(
            text = formatDuration(context.snapshot.metrics.uptimeMillis),
            style = ZType.Metric.copy(fontSize = 24.sp),
            color = ZColors.TextPrimary,
        )
        Spacer(Modifier.height(10.dp))
        Text("最終起動からの経過", style = ZType.Sub, color = ZColors.TextSecondary)
    }
}

@Composable
fun AppsCard(context: CardContext) {
    DashboardCardScaffold(
        title = "アプリ",
        status = CardStatus.NEUTRAL,
        leadingDot = true,
        onClick = { context.onOpenSettings(Settings.ACTION_APPLICATION_SETTINGS) },
    ) {
        Spacer(Modifier.height(10.dp))
        MetricValue(value = context.snapshot.appCount.toString(), unit = "個")
        Spacer(Modifier.height(10.dp))
        Text(
            text = if (context.snapshot.workAppCount > 0) {
                "仕事用 ${context.snapshot.workAppCount} 個を含む"
            } else {
                "起動可能なアプリ"
            },
            style = ZType.Sub,
            color = ZColors.TextSecondary,
        )
    }
}

@Composable
fun RecentAppsCard(context: CardContext) {
    DashboardCardScaffold(title = "最近追加したアプリ", status = CardStatus.NEUTRAL) {
        Spacer(Modifier.height(8.dp))
        if (context.snapshot.recentApps.isEmpty()) {
            Text("読み込み中", style = ZType.Sub, color = ZColors.TextSecondary)
            return@DashboardCardScaffold
        }
        context.snapshot.recentApps.forEach { entry ->
            val icon by rememberAppIcon(entry, context.iconProvider)
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable { context.onLaunchApp(entry) }
                    .padding(vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                AppIconTile(icon = icon, size = 34.dp, background = ZColors.SurfaceHigh)
                Text(
                    text = entry.label,
                    style = ZType.Body,
                    color = ZColors.TextPrimary,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text("起動", style = ZType.Sub, color = ZColors.AccentSoft)
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String, status: CardStatus) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = label,
            style = ZType.Body.copy(fontSize = 11.5.sp),
            color = ZColors.TextSecondary,
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        if (status != CardStatus.NEUTRAL) {
            StatusDot(status)
            Spacer(Modifier.width(6.dp))
        }
        Text(value, style = ZType.Body.copy(fontSize = 11.5.sp), color = ZColors.TextPrimary)
    }
}
