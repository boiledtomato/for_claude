package com.example.zlauncher.ui.console.card

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zlauncher.core.designsystem.LocalStatusColors
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZMotion
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.designsystem.component.DashboardCardScaffold
import com.example.zlauncher.core.designsystem.component.MetricValue
import com.example.zlauncher.core.designsystem.component.Sparkline
import com.example.zlauncher.core.designsystem.component.StackedBar
import com.example.zlauncher.core.designsystem.component.StatusDot
import com.example.zlauncher.core.ui.formatBytes
import com.example.zlauncher.core.ui.formatDuration
import com.example.zlauncher.domain.model.CardStatus
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/** 実測のスループット。1 秒ごとの差分なので、通信すると即座に動く */
@Composable
fun TrafficCard(context: CardContext) {
    val metrics = context.snapshot.metrics
    val (down, downUnit) = formatBytes(metrics.rxBytesPerSec)
    val (up, upUnit) = formatBytes(metrics.txBytesPerSec)
    val active = metrics.rxBytesPerSec > 0 || metrics.txBytesPerSec > 0

    DashboardCardScaffold(
        title = "Traffic",
        status = if (active) CardStatus.GREEN else CardStatus.NEUTRAL,
        statusLabel = if (active) "Active" else "Idle",
        leadingDot = true,
    ) {
        Spacer(Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.Bottom) {
            Column(Modifier.weight(1f)) {
                Text("Down", style = ZType.Sub, color = ZColors.TextSecondary)
                MetricValue(value = down, unit = "$downUnit/s", fontSize = 26.sp, color = ZColors.AccentAlt)
            }
            Column(Modifier.weight(1f)) {
                Text("Up", style = ZType.Sub, color = ZColors.TextSecondary)
                MetricValue(value = up, unit = "$upUnit/s", fontSize = 26.sp, color = ZColors.Accent)
            }
        }
        Spacer(Modifier.height(10.dp))
        Sparkline(values = metrics.rxHistory, color = ZColors.AccentAlt, height = 28.dp)
        Spacer(Modifier.height(4.dp))
        Sparkline(values = metrics.txHistory, color = ZColors.Accent, height = 20.dp)
        Spacer(Modifier.height(8.dp))
        Text("Last ${metrics.rxHistory.size}s", style = ZType.Sub, color = ZColors.TextSecondary)
    }
}

/** 起動からの累計と、モバイル / Wi-Fi の内訳 */
@Composable
fun TrafficTotalCard(context: CardContext) {
    val metrics = context.snapshot.metrics
    val (rx, rxUnit) = formatBytes(metrics.rxBytesTotal)
    val (tx, txUnit) = formatBytes(metrics.txBytesTotal)
    val mobile = (metrics.mobileRxBytes + metrics.mobileTxBytes).toFloat()
    val wifi = (metrics.wifiRxBytes + metrics.wifiTxBytes).toFloat()

    DashboardCardScaffold(title = "Data since boot", status = CardStatus.NEUTRAL, leadingDot = true) {
        Spacer(Modifier.height(10.dp))
        Row {
            Column(Modifier.weight(1f)) {
                Text("Received", style = ZType.Sub, color = ZColors.TextSecondary)
                MetricValue(value = rx, unit = rxUnit, fontSize = 22.sp)
            }
            Column(Modifier.weight(1f)) {
                Text("Sent", style = ZType.Sub, color = ZColors.TextSecondary)
                MetricValue(value = tx, unit = txUnit, fontSize = 22.sp)
            }
        }
        Spacer(Modifier.height(12.dp))
        StackedBar(
            segments = listOf(
                wifi.coerceAtLeast(1f) to ZColors.AccentAlt,
                mobile.coerceAtLeast(1f) to ZColors.Accent,
            )
        )
        Spacer(Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            LegendItem(ZColors.AccentAlt, "Wi-Fi & other")
            LegendItem(ZColors.Accent, "Mobile")
        }
    }
}

private data class HealthCheck(val label: String, val value: String, val status: CardStatus)

/**
 * 状態を順に点検していく様子を見せるカード。
 * 判定そのものは実測値から出しているが、掃引アニメーションで 1 項目ずつ結果を出す。
 */
@Composable
fun HealthCheckCard(context: CardContext) {
    val metrics = context.snapshot.metrics
    val checks = listOf(
        HealthCheck(
            label = "Network reachability",
            value = if (metrics.online) "OK" else "Not connected",
            status = if (metrics.online) CardStatus.GREEN else CardStatus.RED,
        ),
        HealthCheck(
            label = "VPN tunnel",
            value = if (metrics.vpnActive) "Connected" else "Not connected",
            status = if (metrics.vpnActive) CardStatus.GREEN else CardStatus.NEUTRAL,
        ),
        HealthCheck(
            label = "Storage headroom",
            value = if (metrics.storageUsedRatio < 0.9f) "Sufficient" else "Low",
            status = if (metrics.storageUsedRatio < 0.9f) CardStatus.GREEN else CardStatus.AMBER,
        ),
        HealthCheck(
            label = "Memory headroom",
            value = if (metrics.memoryUsedRatio < 0.9f) "Sufficient" else "Tight",
            status = if (metrics.memoryUsedRatio < 0.9f) CardStatus.GREEN else CardStatus.AMBER,
        ),
        HealthCheck(
            label = "Battery",
            value = if (metrics.batteryCharging || metrics.batteryPercent >= 20) "Healthy" else "Low battery",
            status = if (metrics.batteryCharging || metrics.batteryPercent >= 20) CardStatus.GREEN else CardStatus.RED,
        ),
    )
    val healthy = checks.count { it.status == CardStatus.GREEN }
    val worst = when {
        checks.any { it.status == CardStatus.RED } -> CardStatus.RED
        checks.any { it.status == CardStatus.AMBER } -> CardStatus.AMBER
        else -> CardStatus.GREEN
    }

    var runId by remember { mutableIntStateOf(0) }
    val sweep = remember { Animatable(0f) }
    LaunchedEffect(runId) {
        sweep.snapTo(0f)
        sweep.animateTo(1f, tween(1800))
        delay(RECHECK_INTERVAL_MS)
        runId++
    }
    val running = sweep.value < 1f

    DashboardCardScaffold(
        title = "Health check",
        status = if (running) CardStatus.NEUTRAL else worst,
        statusLabel = if (running) "Checking" else "$healthy/${checks.size} OK",
        onClick = { runId++ },
    ) {
        Spacer(Modifier.height(10.dp))
        // 掃引バー。走っている間だけ伸びる
        Box(
            Modifier
                .fillMaxWidth()
                .height(3.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(ZColors.Outline),
        ) {
            Box(
                Modifier
                    .fillMaxWidth(sweep.value)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(2.dp))
                    .background(ZColors.AccentAlt)
            )
        }
        Spacer(Modifier.height(12.dp))
        checks.forEachIndexed { index, check ->
            val revealAt = (index + 1f) / checks.size
            val visible by animateFloatAsState(
                targetValue = if (sweep.value >= revealAt) 1f else 0.15f,
                animationSpec = tween(220),
                label = "check$index",
            )
            Row(
                Modifier.fillMaxWidth().alpha(visible).padding(vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = check.label,
                    style = ZType.Body.copy(fontSize = 11.5.sp),
                    color = ZColors.TextSecondary,
                    modifier = Modifier.weight(1f),
                )
                StatusDot(check.status)
                Spacer(Modifier.width(6.dp))
                Text(check.value, style = ZType.Body.copy(fontSize = 11.5.sp), color = ZColors.TextPrimary)
            }
        }
        Spacer(Modifier.height(6.dp))
        Text(
            text = if (running) "Running checks…" else "Tap to re-run",
            style = ZType.Sub,
            color = ZColors.TextDim,
        )
    }
}

/** セッション中のバッテリー推移 */
@Composable
fun BatteryTrendCard(context: CardContext) {
    val metrics = context.snapshot.metrics
    val colors = LocalStatusColors.current
    val status = when {
        metrics.batteryCharging -> CardStatus.GREEN
        metrics.batteryPercent >= 20 -> CardStatus.NEUTRAL
        else -> CardStatus.RED
    }
    DashboardCardScaffold(title = "Battery trend", status = status, leadingDot = true) {
        Spacer(Modifier.height(10.dp))
        MetricValue(value = metrics.batteryPercent.toString(), unit = "%", fontSize = 26.sp)
        Spacer(Modifier.height(8.dp))
        Sparkline(
            values = metrics.batteryHistory,
            color = if (metrics.batteryCharging) colors.green else colors.amber,
            height = 26.dp,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = if (metrics.batteryCharging) "Charging" else "Discharging",
            style = ZType.Sub,
            color = ZColors.TextSecondary,
        )
    }
}

/** 秒まで動く時計。画面に常に動きを持たせる */
@Composable
fun ClockCard(context: CardContext) {
    val time = remember(context.snapshot.metrics.sampledAtMillis) {
        SimpleDateFormat("H:mm:ss", Locale.getDefault()).format(Date())
    }
    val date = remember(context.snapshot.metrics.sampledAtMillis / 3_600_000) {
        SimpleDateFormat("EEE, MMM d", Locale.ENGLISH).format(Date())
    }
    DashboardCardScaffold(title = "Clock", status = CardStatus.NEUTRAL) {
        Spacer(Modifier.height(10.dp))
        Text(text = time, style = ZType.Metric.copy(fontSize = 30.sp), color = ZColors.TextPrimary)
        Spacer(Modifier.height(8.dp))
        Text(date, style = ZType.Sub, color = ZColors.TextSecondary)
        Spacer(Modifier.height(6.dp))
        Text("Up ${formatDuration(context.snapshot.metrics.uptimeMillis)}", style = ZType.Sub, color = ZColors.TextDim)
    }
}

/** 音量と明るさ。どちらも権限なしで読める */
@Composable
fun SoundLightCard(context: CardContext) {
    val metrics = context.snapshot.metrics
    DashboardCardScaffold(title = "Sound & brightness", status = CardStatus.NEUTRAL) {
        Spacer(Modifier.height(12.dp))
        LevelRow("Media", metrics.mediaVolumePercent, ZColors.AccentAlt)
        Spacer(Modifier.height(10.dp))
        LevelRow("Ring", metrics.ringVolumePercent, ZColors.Accent)
        Spacer(Modifier.height(10.dp))
        LevelRow("Brightness", metrics.brightnessPercent, ZColors.Violet)
    }
}

/** 端末の素性。動かないが、コンソールとしての情報量を補う */
@Composable
fun DeviceInfoCard(context: CardContext) {
    val info = context.snapshot.metrics.info
    DashboardCardScaffold(title = "Device", status = CardStatus.NEUTRAL) {
        Spacer(Modifier.height(12.dp))
        Column(verticalArrangement = Arrangement.spacedBy(9.dp)) {
            InfoRow("Model", info.model.ifBlank { "—" })
            InfoRow("Android", if (info.sdkInt > 0) "${info.androidVersion} (API ${info.sdkInt})" else "—")
            InfoRow("Display", if (info.screenWidthPx > 0) "${info.screenWidthPx} × ${info.screenHeightPx} / ${info.densityDpi} dpi" else "—")
            InfoRow("CPU cores", if (info.cpuCores > 0) "${info.cpuCores}" else "—")
        }
    }
}

@Composable
private fun LevelRow(label: String, percent: Int, color: Color) {
    val animated by animateFloatAsState(percent / 100f, ZMotion.value(), label = label)
    Column {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                label,
                style = ZType.Body.copy(fontSize = 11.5.sp),
                color = ZColors.TextSecondary,
                modifier = Modifier.weight(1f),
            )
            Text("$percent %", style = ZType.UnitText.copy(fontSize = 11.sp), color = ZColors.TextPrimary)
        }
        Spacer(Modifier.height(6.dp))
        StackedBar(
            segments = listOf(
                animated.coerceAtLeast(0.001f) to color,
                (1f - animated).coerceAtLeast(0.001f) to ZColors.StatusNeutral,
            ),
            height = 6.dp,
        )
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(
            label,
            style = ZType.Body.copy(fontSize = 11.5.sp),
            color = ZColors.TextSecondary,
            modifier = Modifier.weight(1f),
        )
        Text(value, style = ZType.Body.copy(fontSize = 11.5.sp), color = ZColors.TextPrimary)
    }
}

@Composable
private fun LegendItem(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        Box(Modifier.size(6.dp).clip(androidx.compose.foundation.shape.CircleShape).background(color))
        Text(label, style = ZType.Sub.copy(fontSize = 11.sp), color = ZColors.TextSecondary)
    }
}

private const val RECHECK_INTERVAL_MS = 9_000L
