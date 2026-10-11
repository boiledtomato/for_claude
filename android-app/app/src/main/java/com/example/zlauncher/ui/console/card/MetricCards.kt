package com.example.zlauncher.ui.console.card

import android.content.Intent
import android.provider.Settings
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import com.example.zlauncher.core.designsystem.ZMotion
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.ui.springyClick
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
import com.example.zlauncher.ui.apps.component.AppIconTile
import com.example.zlauncher.ui.apps.component.rememberAppIcon

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
        animationSpec = ZMotion.value(),
        label = "battery",
    )
    DashboardCardScaffold(
        title = "Battery",
        status = status,
        statusLabel = if (metrics.batteryCharging) "Charging" else null,
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
                DetailRow("Status", if (metrics.batteryCharging) "Charging" else "Discharging", status)
                DetailRow("Level", "${metrics.batteryPercent} %", status)
                DetailRow(
                    label = "Temperature",
                    value = String.format(java.util.Locale.US, "%.1f °C", metrics.batteryTemperatureC),
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
    val ratio by animateFloatAsState(metrics.storageUsedRatio, ZMotion.value(), label = "storage")

    DashboardCardScaffold(
        title = "Storage",
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
        Text("Free $free $freeUnit", style = ZType.Sub, color = ZColors.TextSecondary)
    }
}

@Composable
fun MemoryCard(context: CardContext) {
    val metrics = context.snapshot.metrics
    val status = thresholdStatus(metrics.memoryUsedRatio)
    val (used, usedUnit) = formatBytes(metrics.memoryUsedBytes)
    val (total, totalUnit) = formatBytes(metrics.memoryTotalBytes)
    val ratio by animateFloatAsState(metrics.memoryUsedRatio, ZMotion.value(), label = "memory")

    DashboardCardScaffold(title = "Memory", status = status, leadingDot = true) {
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
        Text("Total $total $totalUnit", style = ZType.Sub, color = ZColors.TextSecondary)
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
        NetworkKind.CELLULAR -> "Mobile"
        NetworkKind.ETHERNET -> "Ethernet"
        NetworkKind.OTHER -> "Other"
        NetworkKind.NONE -> "Not connected"
    }
    DashboardCardScaffold(
        title = "Network",
        status = status,
        statusLabel = if (metrics.online) "Online" else "Check",
        onClick = { context.onOpenSettings(Settings.ACTION_WIRELESS_SETTINGS) },
    ) {
        Spacer(Modifier.height(12.dp))
        Column(verticalArrangement = Arrangement.spacedBy(9.dp)) {
            DetailRow("Connection", kindLabel, status)
            DetailRow(
                label = "VPN",
                value = if (metrics.vpnActive) "Connected" else "Not connected",
                status = if (metrics.vpnActive) CardStatus.GREEN else CardStatus.NEUTRAL,
            )
            DetailRow(
                label = "Est. downlink",
                value = if (metrics.downstreamKbps > 0) "${metrics.downstreamKbps / 1000} Mbps" else "—",
                status = CardStatus.NEUTRAL,
            )
        }
    }
}

@Composable
fun UptimeCard(context: CardContext) {
    DashboardCardScaffold(title = "Uptime", status = CardStatus.NEUTRAL, leadingDot = true) {
        Spacer(Modifier.height(10.dp))
        Text(
            text = formatDuration(context.snapshot.metrics.uptimeMillis),
            style = ZType.Metric.copy(fontSize = 24.sp),
            color = ZColors.TextPrimary,
        )
        Spacer(Modifier.height(10.dp))
        Text("Since last boot", style = ZType.Sub, color = ZColors.TextSecondary)
    }
}

@Composable
fun AppsCard(context: CardContext) {
    DashboardCardScaffold(
        title = "Apps",
        status = CardStatus.NEUTRAL,
        leadingDot = true,
        onClick = { context.onOpenSettings(Settings.ACTION_APPLICATION_SETTINGS) },
    ) {
        Spacer(Modifier.height(10.dp))
        MetricValue(value = context.snapshot.appCount.toString(), unit = "apps")
        Spacer(Modifier.height(10.dp))
        Text(
            text = if (context.snapshot.workAppCount > 0) {
                "Includes ${context.snapshot.workAppCount} work apps"
            } else {
                "Launchable apps"
            },
            style = ZType.Sub,
            color = ZColors.TextSecondary,
        )
    }
}

@Composable
fun RecentAppsCard(context: CardContext) {
    DashboardCardScaffold(title = "Recently added", status = CardStatus.NEUTRAL) {
        Spacer(Modifier.height(8.dp))
        if (context.snapshot.recentApps.isEmpty()) {
            Text("Loading", style = ZType.Sub, color = ZColors.TextSecondary)
            return@DashboardCardScaffold
        }
        context.snapshot.recentApps.forEach { entry ->
            val icon by rememberAppIcon(entry, context.iconProvider)
            Row(
                Modifier
                    .fillMaxWidth()
                    .springyClick { context.onLaunchApp(entry) }
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
                Text("Open", style = ZType.Sub, color = ZColors.AccentSoft)
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
