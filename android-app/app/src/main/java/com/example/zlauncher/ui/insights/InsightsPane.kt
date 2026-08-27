package com.example.zlauncher.ui.insights

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZMotion
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.designsystem.component.DashboardCardScaffold
import com.example.zlauncher.core.designsystem.component.MetricValue
import com.example.zlauncher.core.ui.formatBytes
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.data.insights.UsageAccess
import com.example.zlauncher.domain.model.CategoryRollup
import com.example.zlauncher.domain.model.InsightLogEntry
import com.example.zlauncher.domain.model.InsightRange
import com.example.zlauncher.domain.model.InsightTransport
import com.example.zlauncher.domain.model.WebInsightsReport
import com.example.zlauncher.ui.home.component.AppSearchBar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Web Insights — 自分で作ったカテゴリー別の通信ログ。
 *
 * 端末から取れるのは UID 単位のバイト数までで、URL やホスト名は取れない。
 * したがって「どのカテゴリーのアプリが、いつ、どれだけ流したか」までを見せる。
 */
@Composable
fun InsightsPane(
    initialCategoryId: String?,
    modifier: Modifier = Modifier,
    viewModel: InsightsViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    // 設定画面で許可を付けて戻ってきたときに拾えるよう、前面に戻るたびに読み直す
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            viewModel.refresh()
        }
    }

    LaunchedEffect(initialCategoryId) {
        if (initialCategoryId != null && viewModel.categoryFilter != initialCategoryId) {
            viewModel.toggleCategoryFilter(initialCategoryId)
        }
    }

    viewModel.pendingExport?.let { intent ->
        LaunchedEffect(intent) {
            runCatching { context.startActivity(intent) }
            viewModel.consumeExport()
        }
    }

    Column(modifier.fillMaxSize()) {
        InsightsToolbar(
            range = viewModel.range,
            loading = viewModel.loading,
            onRange = viewModel::selectRange,
            onRefresh = { viewModel.refresh(force = true) },
            onExport = viewModel::export,
        )

        ExportNotice(
            visible = viewModel.exportFailed,
            onDismiss = viewModel::clearExportError,
        )

        if (!viewModel.report.available) {
            UsageAccessCard(
                onGrant = {
                    runCatching { context.startActivity(UsageAccess.settingsIntent()) }
                },
            )
            return@Column
        }

        InsightsBody(viewModel = viewModel)
    }
}

@Composable
private fun InsightsBody(viewModel: InsightsViewModel) {
    val report = viewModel.report
    val entries = viewModel.visibleEntries
    val peak = remember(report) { report.series.maxOrNull() ?: 0L }

    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 12.dp, end = 16.dp, bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item(key = "summary") { SummaryCard(report) }

        item(key = "timeline") {
            TimelineCard(
                report = report,
                peak = peak,
                selected = viewModel.bucketFilter,
                onSelect = viewModel::toggleBucketFilter,
            )
        }

        item(key = "search") {
            AppSearchBar(
                query = viewModel.query,
                onQueryChange = viewModel::updateQuery,
                onClear = { viewModel.updateQuery("") },
                placeholder = "Search log by app or category",
            )
        }

        item(key = "filters") {
            ActiveFilters(
                categoryName = report.categories.firstOrNull { it.id == viewModel.categoryFilter }?.name,
                bucketStart = viewModel.bucketFilter,
                range = report.range,
                onClear = viewModel::clearFilters,
            )
        }

        item(key = "categories-header") {
            SectionHeader("Categories", "${report.categories.size}")
        }

        items(report.categories, key = { "cat-${it.id}" }) { rollup ->
            CategoryRow(
                rollup = rollup,
                shareOf = report.totalBytes,
                selected = viewModel.categoryFilter == rollup.id,
                modifier = Modifier.animateItem(placementSpec = ZMotion.placement()),
                onClick = { viewModel.toggleCategoryFilter(rollup.id) },
            )
        }

        item(key = "log-header") {
            SectionHeader(
                title = "Log",
                trailing = if (viewModel.truncated) {
                    "${entries.size} of ${viewModel.filteredEntries.size}"
                } else {
                    "${entries.size}"
                },
            )
        }

        if (entries.isEmpty()) {
            item(key = "log-empty") {
                Text(
                    text = if (report.entries.isEmpty()) {
                        "No traffic recorded in this window."
                    } else {
                        "No rows match the current filter."
                    },
                    style = ZType.Body,
                    color = ZColors.TextSecondary,
                    modifier = Modifier.padding(vertical = 12.dp),
                )
            }
        }

        items(entries, key = { "log-${it.startMillis}-${it.packageName}-${it.transport}-${it.label}" }) { entry ->
            LogRow(entry, modifier = Modifier.animateItem(placementSpec = ZMotion.placement()))
        }

        if (viewModel.truncated) {
            item(key = "log-truncated") {
                Text(
                    text = "Showing the newest ${entries.size} rows — narrow the range or search to see the rest.",
                    style = ZType.Sub,
                    color = ZColors.TextDim,
                    modifier = Modifier.padding(top = 6.dp),
                )
            }
        }
    }
}

// ---- ツールバー -------------------------------------------------------------

@Composable
private fun InsightsToolbar(
    range: InsightRange,
    loading: Boolean,
    onRange: (InsightRange) -> Unit,
    onRefresh: () -> Unit,
    onExport: () -> Unit,
) {
    Row(
        Modifier.fillMaxWidth().padding(start = 12.dp, end = 16.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        InsightRange.entries.forEach { option ->
            Chip(
                label = option.label,
                selected = option == range,
                onClick = { onRange(option) },
            )
        }
        Spacer(Modifier.weight(1f))
        AnimatedVisibility(visible = loading, enter = fadeIn(), exit = fadeOut()) {
            ScanPulse()
        }
        Chip(label = "⟳", selected = false, onClick = onRefresh)
        Chip(label = "Export", selected = false, onClick = onExport)
    }
}

/**
 * CSV の書き出しに失敗したときの通知。空の絞り込みで押した場合もここに出る。
 * Snackbar を出すために Scaffold を足すより、ペイン内に 1 行置くほうが素直。
 */
@Composable
private fun ExportNotice(visible: Boolean, onDismiss: () -> Unit) {
    AnimatedVisibility(visible = visible, enter = fadeIn(), exit = fadeOut()) {
        Row(
            Modifier.fillMaxWidth().padding(start = 12.dp, end = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Box(Modifier.size(6.dp).clip(CircleShape).background(ZColors.StatusAmber))
            Text(
                text = "Nothing to export for the current filter.",
                style = ZType.Sub,
                color = ZColors.TextSecondary,
                modifier = Modifier.weight(1f),
            )
            Text(
                "Dismiss",
                style = ZType.Sub,
                color = ZColors.AccentSoft,
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .springyClick(onClick = onDismiss)
                    .padding(horizontal = 6.dp, vertical = 4.dp),
            )
        }
    }
}

/** 読み込み中であることを示す明滅。数十回の照会があるので無反応に見せない */
@Composable
private fun ScanPulse() {
    val transition = rememberInfiniteTransition(label = "scan")
    val alpha by transition.animateFloat(
        initialValue = 0.25f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(700), RepeatMode.Reverse),
        label = "scanAlpha",
    )
    Row(
        Modifier.padding(end = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Box(Modifier.size(6.dp).alpha(alpha).clip(CircleShape).background(ZColors.AccentAlt))
        Text("Reading", style = ZType.Sub, color = ZColors.TextDim)
    }
}

@Composable
private fun Chip(label: String, selected: Boolean, onClick: () -> Unit) {
    val background by animateColorAsState(
        targetValue = if (selected) ZColors.Accent.copy(alpha = 0.14f) else ZColors.Surface,
        animationSpec = ZMotion.value(),
        label = "chipBackground",
    )
    val border by animateColorAsState(
        targetValue = if (selected) ZColors.Accent.copy(alpha = 0.5f) else ZColors.Outline,
        animationSpec = ZMotion.value(),
        label = "chipBorder",
    )
    Box(
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(background)
            .border(1.dp, border, RoundedCornerShape(999.dp))
            .springyClick(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp),
    ) {
        Text(
            text = label,
            style = ZType.Body.copy(fontSize = 12.sp),
            color = if (selected) ZColors.AccentSoft else ZColors.TextSecondary,
        )
    }
}

// ---- カード -----------------------------------------------------------------

@Composable
private fun SummaryCard(report: WebInsightsReport) {
    val (downValue, downUnit) = formatBytes(report.totalRx)
    val (upValue, upUnit) = formatBytes(report.totalTx)
    DashboardCardScaffold(
        title = "TOTAL · ${report.range.label}",
        modifier = Modifier.fillMaxWidth(),
        trailing = {
            Text(
                text = if (report.generatedAtMillis > 0) formatClock(report.generatedAtMillis) else "—",
                style = ZType.Sub,
                color = ZColors.TextDim,
            )
        },
    ) {
        Spacer(Modifier.height(6.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(22.dp)) {
            Column {
                Text("↓ Down", style = ZType.Sub, color = ZColors.TextSecondary)
                MetricValue(downValue, downUnit, fontSize = 24.sp, color = ZColors.AccentAlt)
            }
            Column {
                Text("↑ Up", style = ZType.Sub, color = ZColors.TextSecondary)
                MetricValue(upValue, upUnit, fontSize = 24.sp, color = ZColors.AccentSoft)
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = "${report.bucketStarts.size} buckets of ${report.range.bucketLabel} · " +
                "${report.apps.size} apps · ${report.entries.size} rows",
            style = ZType.Sub,
            color = ZColors.TextDim,
        )
    }
}

@Composable
private fun TimelineCard(
    report: WebInsightsReport,
    peak: Long,
    selected: Long?,
    onSelect: (Long) -> Unit,
) {
    val series = report.series
    DashboardCardScaffold(
        title = "TIMELINE",
        modifier = Modifier.fillMaxWidth(),
        trailing = {
            Text(
                text = report.bucketStarts.firstOrNull()?.let { start ->
                    "${formatBucket(start, report.range)} → ${formatBucket(report.generatedAtMillis, report.range)}"
                } ?: "—",
                style = ZType.Sub,
                color = ZColors.TextDim,
            )
        },
    ) {
        Spacer(Modifier.height(10.dp))
        if (series.isEmpty()) {
            Text("No data in this window.", style = ZType.Body, color = ZColors.TextSecondary)
            return@DashboardCardScaffold
        }
        Row(
            Modifier.fillMaxWidth().height(72.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.Bottom,
        ) {
            report.bucketStarts.forEachIndexed { index, start ->
                val value = series.getOrElse(index) { 0L }
                TimelineBar(
                    modifier = Modifier.weight(1f),
                    fraction = if (peak <= 0) 0f else value.toFloat() / peak.toFloat(),
                    selected = selected == start,
                    dimmed = selected != null && selected != start,
                    onClick = { onSelect(start) },
                )
            }
        }
        Spacer(Modifier.height(6.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = report.bucketStarts.firstOrNull()?.let { formatBucket(it, report.range) } ?: "",
                style = ZType.Sub,
                color = ZColors.TextDim,
            )
            val (peakValue, peakUnit) = formatBytes(peak)
            Text("peak $peakValue $peakUnit", style = ZType.Sub, color = ZColors.TextDim)
        }
    }
}

@Composable
private fun TimelineBar(
    modifier: Modifier,
    fraction: Float,
    selected: Boolean,
    dimmed: Boolean,
    onClick: () -> Unit,
) {
    // 値が 0 でも掴めるように最低高さを残す
    val target = (fraction.coerceIn(0f, 1f) * 0.94f + 0.06f)
    val height by animateFloatAsState(target, ZMotion.value(), label = "barHeight")
    val color by animateColorAsState(
        targetValue = when {
            selected -> ZColors.AccentAlt
            dimmed -> ZColors.Outline
            else -> ZColors.Accent
        },
        animationSpec = ZMotion.value(),
        label = "barColor",
    )
    Box(
        modifier
            .fillMaxHeight()
            .springyClick(onClick = onClick),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(height)
                .clip(RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp))
                .background(color)
        )
    }
}

@Composable
private fun ActiveFilters(
    categoryName: String?,
    bucketStart: Long?,
    range: InsightRange,
    onClear: () -> Unit,
) {
    val active = categoryName != null || bucketStart != null
    AnimatedVisibility(visible = active, enter = fadeIn(), exit = fadeOut()) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            categoryName?.let { Chip(label = it, selected = true, onClick = onClear) }
            bucketStart?.let {
                Chip(
                    label = "${formatBucket(it, range)} +${range.bucketLabel}",
                    selected = true,
                    onClick = onClear,
                )
            }
            Spacer(Modifier.weight(1f))
            Text(
                "Clear",
                style = ZType.Sub,
                color = ZColors.AccentSoft,
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .springyClick(onClick = onClear)
                    .padding(horizontal = 6.dp, vertical = 4.dp),
            )
        }
    }
}

@Composable
private fun SectionHeader(title: String, trailing: String) {
    Row(
        Modifier.fillMaxWidth().padding(top = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(title, style = ZType.Eyebrow, color = ZColors.TextSecondary, modifier = Modifier.weight(1f))
        Text(trailing, style = ZType.Sub, color = ZColors.TextDim)
    }
}

@Composable
private fun CategoryRow(
    rollup: CategoryRollup,
    shareOf: Long,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val color = categoryColor(rollup.colorIndex)
    val share = if (shareOf <= 0) 0f else rollup.totalBytes.toFloat() / shareOf.toFloat()
    val width by animateFloatAsState(share.coerceIn(0f, 1f), ZMotion.value(), label = "share")
    val background by animateColorAsState(
        targetValue = if (selected) ZColors.SurfaceHigh else ZColors.Surface,
        animationSpec = ZMotion.value(),
        label = "categoryBackground",
    )
    val (value, unit) = formatBytes(rollup.totalBytes)

    Column(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(background)
            .border(1.dp, if (selected) color.copy(alpha = 0.5f) else ZColors.Outline, RoundedCornerShape(10.dp))
            .springyClick(onClick = onClick)
            .padding(horizontal = 11.dp, vertical = 9.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(Modifier.size(8.dp).clip(CircleShape).background(color))
            Text(
                text = rollup.name,
                style = ZType.Body,
                color = ZColors.TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
            Text("$value $unit", style = ZType.UnitText, color = ZColors.TextPrimary)
        }
        Spacer(Modifier.height(7.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(RoundedCornerShape(999.dp))
                .background(ZColors.StatusNeutral),
        ) {
            Box(
                Modifier
                    .fillMaxWidth(width)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(999.dp))
                    .background(color)
            )
        }
        Spacer(Modifier.height(6.dp))
        val (rx, rxUnit) = formatBytes(rollup.rxBytes)
        val (tx, txUnit) = formatBytes(rollup.txBytes)
        Text(
            text = "${rollup.appCount} apps · ↓ $rx $rxUnit · ↑ $tx $txUnit",
            style = ZType.Sub,
            color = ZColors.TextSecondary,
        )
    }
}

@Composable
private fun LogRow(entry: InsightLogEntry, modifier: Modifier = Modifier) {
    val color = categoryColor(entry.colorIndex)
    val (rx, rxUnit) = formatBytes(entry.rxBytes)
    val (tx, txUnit) = formatBytes(entry.txBytes)

    Row(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(9.dp))
            .background(ZColors.SurfaceLow)
            .border(1.dp, ZColors.Outline, RoundedCornerShape(9.dp))
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(9.dp),
    ) {
        Box(Modifier.width(2.dp).height(28.dp).clip(RoundedCornerShape(2.dp)).background(color))
        Column(Modifier.weight(1f)) {
            Text(
                text = entry.label,
                style = ZType.Body,
                color = ZColors.TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "${formatLogTime(entry.startMillis)} · ${entry.categoryName} · ${entry.transport.badge}",
                style = ZType.Sub,
                color = ZColors.TextSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text("↓ $rx $rxUnit", style = ZType.UnitText.copy(fontSize = 11.sp), color = ZColors.AccentAlt)
            Text("↑ $tx $txUnit", style = ZType.UnitText.copy(fontSize = 11.sp), color = ZColors.TextSecondary)
        }
    }
}

@Composable
private fun UsageAccessCard(onGrant: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(start = 12.dp, end = 16.dp)) {
        DashboardCardScaffold(
            title = "USAGE ACCESS REQUIRED",
            modifier = Modifier.fillMaxWidth(),
        ) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Android only reports per-app data usage to apps that hold usage access. " +
                    "It cannot be requested with a normal permission dialog — grant it in Settings, " +
                    "then come back to this pane.",
                style = ZType.Body,
                color = ZColors.TextSecondary,
            )
            Spacer(Modifier.height(12.dp))
            Chip(label = "Open usage access settings", selected = true, onClick = onGrant)
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Without it the console still shows device-wide totals on the Overview pane.",
                style = ZType.Sub,
                color = ZColors.TextDim,
            )
        }
    }
}

// ---- 表示ヘルパ -------------------------------------------------------------

/** 未分類は色を持たないので、カテゴリー色の範囲外インデックスを無彩色に落とす */
private fun categoryColor(index: Int): Color =
    if (index < 0) ZColors.TextDim else ZColors.CategoryColors[index % ZColors.CategoryColors.size]

private val InsightTransport.badge: String
    get() = when (this) {
        InsightTransport.WIFI -> "Wi-Fi"
        InsightTransport.MOBILE -> "Mobile"
    }

private fun formatClock(millis: Long): String =
    SimpleDateFormat("H:mm:ss", Locale.getDefault()).format(Date(millis))

/** 1 時間の窓なら時刻だけ、週なら日付も出さないと同じ表示が並んでしまう */
private fun formatBucket(millis: Long, range: InsightRange): String {
    if (millis <= 0) return "—"
    val pattern = if (range == InsightRange.LAST_WEEK) "MMM d HH:mm" else "HH:mm"
    return SimpleDateFormat(pattern, Locale.ENGLISH).format(Date(millis))
}

private fun formatLogTime(millis: Long): String =
    SimpleDateFormat("MMM d HH:mm", Locale.ENGLISH).format(Date(millis))
