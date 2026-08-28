package com.example.zlauncher.ui.insights

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.example.zlauncher.domain.model.AppRollup
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
 *
 * 同じデータを 3 段階で見る:
 * Chart（カテゴリー別の積み上げ）→ Apps（アプリ別の順位）→ Log（生の行）。
 * どの段でもカテゴリー・時間帯・検索の絞り込みは共通で効く。
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
            canExport = viewModel.canExport,
            onRange = viewModel::selectRange,
            onRefresh = { viewModel.refresh(force = true) },
            onExport = viewModel::export,
        )

        Notice(
            message = viewModel.exportMessage,
            onDismiss = viewModel::clearExportMessage,
        )

        if (!viewModel.report.available) {
            UsageAccessCard(
                onGrant = { runCatching { context.startActivity(UsageAccess.settingsIntent()) } },
            )
            return@Column
        }

        ViewSwitcher(current = viewModel.view, onSelect = viewModel::selectView)

        AnimatedContent(
            targetState = viewModel.view,
            transitionSpec = {
                (fadeIn(tween(ZMotion.TRANSITION_MS)) + slideInVertically { it / 24 })
                    .togetherWith(fadeOut(tween(160)))
            },
            label = "insightsView",
        ) { view ->
            when (view) {
                InsightView.CHART -> ChartView(viewModel)
                InsightView.APPS -> AppsView(viewModel)
                InsightView.LOG -> LogView(viewModel)
            }
        }
    }

    viewModel.detail?.let { open ->
        DetailOverlay(viewModel = viewModel, detail = open, onClose = viewModel::closeDetail)
    }
}

// ---- Chart ------------------------------------------------------------------

@Composable
private fun ChartView(viewModel: InsightsViewModel) {
    val report = viewModel.report
    val series = remember(report) { report.chartSeries() }

    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 12.dp, end = 16.dp, top = 4.dp, bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item(key = "summary") { SummaryCard(report) }

        item(key = "timeline") {
            DashboardCardScaffold(
                title = "TRAFFIC BY CATEGORY",
                modifier = Modifier.fillMaxWidth(),
                trailing = { Text(report.range.bucketLabel, style = ZType.Sub, color = ZColors.TextDim) },
            ) {
                ChartSpacer(10.dp)
                if (series.isEmpty()) {
                    ChartEmpty("No traffic recorded in this window.")
                } else {
                    StackedTimeline(
                        bucketStarts = report.bucketStarts,
                        series = series,
                        selected = viewModel.bucketFilter,
                        onSelect = viewModel::toggleBucketFilter,
                    )
                    ChartSpacer(6.dp)
                    AxisRow(report)
                    ChartSpacer(12.dp)
                    // 2 系列以上あるので凡例は必ず出す。実数も並べて色だけに頼らせない
                    ChartLegend(series = series, onClick = { viewModel.openCategoryDetail(it.id) })
                }
            }
        }

        item(key = "selected-bucket") {
            SelectedBucketCard(
                report = report,
                bucketStart = viewModel.bucketFilter,
                onClear = { viewModel.toggleBucketFilter(viewModel.bucketFilter) },
            )
        }

        item(key = "transport") {
            DashboardCardScaffold(title = "BY CONNECTION", modifier = Modifier.fillMaxWidth()) {
                ChartSpacer(10.dp)
                SplitBar(
                    leftLabel = "Wi-Fi",
                    leftBytes = report.categories.sumOf { it.wifiBytes },
                    leftColor = ZColors.CategoryChartColors[1],
                    rightLabel = "Mobile",
                    rightBytes = report.categories.sumOf { it.mobileBytes },
                    rightColor = ZColors.CategoryChartColors[3],
                )
                ChartSpacer(12.dp)
                SplitBar(
                    leftLabel = "Down",
                    leftBytes = report.totalRx,
                    leftColor = ZColors.CategoryChartColors[0],
                    rightLabel = "Up",
                    rightBytes = report.totalTx,
                    rightColor = ZColors.CategoryChartColors[4],
                )
            }
        }

        item(key = "categories-header") {
            SectionHeader("All categories", "${report.categories.size}")
        }

        items(report.categories, key = { "cat-${it.id}" }) { rollup ->
            CategoryRow(
                rollup = rollup,
                shareOf = report.totalBytes,
                selected = viewModel.categoryFilter == rollup.id,
                modifier = Modifier.animateItem(placementSpec = ZMotion.placement()),
                onClick = { viewModel.toggleCategoryFilter(rollup.id) },
                onOpen = { viewModel.openCategoryDetail(rollup.id) },
            )
        }
    }
}

/** 時間軸の目盛り。両端と中央だけ。全部の棒に時刻を振ると読めなくなる */
@Composable
private fun AxisRow(report: WebInsightsReport) {
    val first = report.bucketStarts.firstOrNull()
    val middle = report.bucketStarts.getOrNull(report.bucketStarts.size / 2)
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        listOfNotNull(first, middle, report.generatedAtMillis.takeIf { it > 0 }).forEach { millis ->
            Text(formatBucket(millis, report.range), style = ZType.Sub, color = ZColors.TextDim)
        }
    }
}

/** 選んだ時間帯の内訳。触って選ぶ画面なので、ホバーの代わりにこの読み取り欄を出す */
@Composable
private fun SelectedBucketCard(report: WebInsightsReport, bucketStart: Long?, onClear: () -> Unit) {
    AnimatedVisibility(visible = bucketStart != null, enter = fadeIn(), exit = fadeOut()) {
        val start = bucketStart ?: return@AnimatedVisibility
        val index = report.bucketStarts.indexOf(start)
        val rows = report.categories
            .map { it to it.series.getOrElse(index) { 0L } }
            .filter { it.second > 0 }
            .sortedByDescending { it.second }
        val total = rows.sumOf { it.second }
        val (value, unit) = formatBytes(total)

        DashboardCardScaffold(
            title = "SELECTED · ${formatBucket(start, report.range)} +${report.range.bucketLabel}",
            modifier = Modifier.fillMaxWidth(),
            highlighted = true,
            trailing = {
                Text(
                    "Clear",
                    style = ZType.Sub,
                    color = ZColors.AccentSoft,
                    modifier = Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .springyClick(onClick = onClear)
                        .padding(horizontal = 6.dp, vertical = 3.dp),
                )
            },
        ) {
            ChartSpacer(6.dp)
            MetricValue(value, unit, fontSize = 22.sp)
            ChartSpacer(8.dp)
            if (rows.isEmpty()) {
                Text("No traffic in this window.", style = ZType.Body, color = ZColors.TextSecondary)
            } else {
                rows.take(4).forEach { (rollup, bytes) ->
                    val (rowValue, rowUnit) = formatBytes(bytes)
                    Row(
                        Modifier.fillMaxWidth().padding(vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(7.dp),
                    ) {
                        Box(
                            Modifier.size(8.dp).clip(RoundedCornerShape(2.dp))
                                .background(chartColor(rollup.colorIndex))
                        )
                        Text(
                            rollup.name,
                            style = ZType.Body,
                            color = ZColors.TextSecondary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f),
                        )
                        Text("$rowValue $rowUnit", style = ZType.UnitText, color = ZColors.TextPrimary)
                    }
                }
            }
        }
    }
}

// ---- Apps -------------------------------------------------------------------

@Composable
private fun AppsView(viewModel: InsightsViewModel) {
    val apps = viewModel.filteredApps
    val peak = apps.firstOrNull()?.totalBytes ?: 0L

    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 12.dp, end = 16.dp, top = 4.dp, bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item(key = "search") { SearchRow(viewModel) }
        item(key = "filters") { FilterRow(viewModel) }
        item(key = "header") { SectionHeader("Apps by traffic", "${apps.size}") }

        if (apps.isEmpty()) {
            item(key = "empty") {
                Text(
                    "No apps match the current filter.",
                    style = ZType.Body,
                    color = ZColors.TextSecondary,
                    modifier = Modifier.padding(vertical = 12.dp),
                )
            }
        }

        items(apps, key = { "app-${it.key}" }) { app ->
            AppRow(
                app = app,
                peak = peak,
                modifier = Modifier.animateItem(placementSpec = ZMotion.placement()),
                onClick = { viewModel.openAppDetail(app.key) },
            )
        }
    }
}

@Composable
private fun AppRow(app: AppRollup, peak: Long, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val color = chartColor(app.colorIndex)
    val (value, unit) = formatBytes(app.totalBytes)

    Column(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(ZColors.Surface)
            .border(1.dp, ZColors.Outline, RoundedCornerShape(10.dp))
            .springyClick(onClick = onClick)
            .padding(horizontal = 11.dp, vertical = 9.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(Modifier.size(8.dp).clip(RoundedCornerShape(2.dp)).background(color))
            Column(Modifier.weight(1f)) {
                Text(
                    app.label,
                    style = ZType.Body,
                    color = ZColors.TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    app.categoryName,
                    style = ZType.Sub,
                    color = ZColors.TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            MiniSeries(series = app.series, color = color)
            Text("$value $unit", style = ZType.UnitText, color = ZColors.TextPrimary)
        }
        ChartSpacer(7.dp)
        ShareBar(
            fraction = if (peak <= 0) 0f else app.totalBytes.toFloat() / peak.toFloat(),
            color = color,
        )
    }
}

// ---- Log --------------------------------------------------------------------

@Composable
private fun LogView(viewModel: InsightsViewModel) {
    val report = viewModel.report
    val entries = viewModel.visibleEntries

    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 12.dp, end = 16.dp, top = 4.dp, bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        item(key = "search") { SearchRow(viewModel) }
        item(key = "filters") { FilterRow(viewModel) }

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

@Composable
private fun LogRow(entry: InsightLogEntry, modifier: Modifier = Modifier) {
    val color = chartColor(entry.colorIndex)
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
        Box(Modifier.width(3.dp).height(28.dp).clip(RoundedCornerShape(2.dp)).background(color))
        Column(Modifier.weight(1f)) {
            Text(
                text = entry.label,
                style = ZType.Body,
                color = ZColors.TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "${formatLogTime(entry.startMillis)} · ${entry.categoryName} · ${entry.transport.label}",
                style = ZType.Sub,
                color = ZColors.TextSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text("↓ $rx $rxUnit", style = ZType.UnitText.copy(fontSize = 11.sp), color = ZColors.TextPrimary)
            Text("↑ $tx $txUnit", style = ZType.UnitText.copy(fontSize = 11.sp), color = ZColors.TextSecondary)
        }
    }
}

// ---- ドリルダウン ------------------------------------------------------------

/**
 * カテゴリー / アプリの詳細。一覧の上に重ねて出す。
 * 画面を切り替えてしまうと、どこから来たのか分からなくなるため。
 */
@Composable
private fun DetailOverlay(viewModel: InsightsViewModel, detail: InsightDetail, onClose: () -> Unit) {
    val report = viewModel.report
    val title: String
    val subtitle: String
    val colorIndex: Int
    val series: List<Long>
    val rx: Long
    val tx: Long
    val wifi: Long
    val mobile: Long
    val members: List<Pair<String, Long>>

    when (detail) {
        is InsightDetail.Category -> {
            val rollup = report.categories.firstOrNull { it.id == detail.id } ?: return
            title = rollup.name
            subtitle = "${rollup.appCount} apps · ${report.range.label}"
            colorIndex = rollup.colorIndex
            series = rollup.series
            rx = rollup.rxBytes
            tx = rollup.txBytes
            wifi = rollup.wifiBytes
            mobile = rollup.mobileBytes
            members = report.apps
                .filter { it.categoryId == rollup.id }
                .take(8)
                .map { it.label to it.totalBytes }
        }

        is InsightDetail.App -> {
            val app = report.apps.firstOrNull { it.key == detail.key } ?: return
            title = app.label
            subtitle = "${app.categoryName} · ${report.range.label}"
            colorIndex = app.colorIndex
            series = app.series
            rx = app.rxBytes
            tx = app.txBytes
            wifi = app.wifiBytes
            mobile = app.mobileBytes
            members = emptyList()
        }
    }

    val color = chartColor(colorIndex)
    val peak = members.maxOfOrNull { it.second } ?: 0L
    val (total, totalUnit) = formatBytes(rx + tx)

    Box(
        Modifier
            .fillMaxSize()
            .background(ZColors.Background.copy(alpha = 0.82f))
            .springyClick(onClick = onClose),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(ZColors.Surface)
                .border(1.dp, ZColors.OutlineStrong, RoundedCornerShape(16.dp))
                .padding(16.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(9.dp)) {
                Box(Modifier.size(10.dp).clip(CircleShape).background(identityColor(colorIndex)))
                Column(Modifier.weight(1f)) {
                    Text(title, style = ZType.Title, color = ZColors.TextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text(subtitle, style = ZType.Sub, color = ZColors.TextSecondary)
                }
                Text(
                    "✕",
                    style = ZType.Body,
                    color = ZColors.TextSecondary,
                    modifier = Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .springyClick(onClick = onClose)
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                )
            }

            ChartSpacer(14.dp)
            MetricValue(total, totalUnit, fontSize = 26.sp)

            ChartSpacer(14.dp)
            if (series.any { it > 0 }) {
                StackedTimeline(
                    bucketStarts = report.bucketStarts,
                    series = listOf(
                        CategoryRollup(
                            id = "detail",
                            name = title,
                            colorIndex = colorIndex,
                            rxBytes = rx,
                            txBytes = tx,
                            wifiBytes = wifi,
                            mobileBytes = mobile,
                            appCount = 1,
                            series = series,
                        )
                    ),
                    selected = null,
                    onSelect = {},
                    height = 64.dp,
                )
            } else {
                ChartEmpty("No traffic in this window.")
            }

            ChartSpacer(14.dp)
            SplitBar(
                leftLabel = "Down",
                leftBytes = rx,
                leftColor = ZColors.CategoryChartColors[0],
                rightLabel = "Up",
                rightBytes = tx,
                rightColor = ZColors.CategoryChartColors[4],
            )
            ChartSpacer(12.dp)
            SplitBar(
                leftLabel = "Wi-Fi",
                leftBytes = wifi,
                leftColor = ZColors.CategoryChartColors[1],
                rightLabel = "Mobile",
                rightBytes = mobile,
                rightColor = ZColors.CategoryChartColors[3],
            )

            if (members.isNotEmpty()) {
                ChartSpacer(16.dp)
                Text("Top apps", style = ZType.Eyebrow, color = ZColors.TextSecondary)
                ChartSpacer(8.dp)
                members.forEach { (label, bytes) ->
                    val (value, unit) = formatBytes(bytes)
                    Column(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                label,
                                style = ZType.Body,
                                color = ZColors.TextSecondary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f),
                            )
                            Text("$value $unit", style = ZType.UnitText, color = ZColors.TextPrimary)
                        }
                        ChartSpacer(5.dp)
                        ShareBar(
                            fraction = if (peak <= 0) 0f else bytes.toFloat() / peak.toFloat(),
                            color = color,
                            height = 3.dp,
                        )
                    }
                }
            }
        }
    }
}

// ---- 共通の部品 --------------------------------------------------------------

@Composable
private fun ViewSwitcher(current: InsightView, onSelect: (InsightView) -> Unit) {
    Row(
        Modifier.fillMaxWidth().padding(start = 12.dp, end = 16.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        InsightView.entries.forEach { option ->
            Chip(label = option.label, selected = option == current, onClick = { onSelect(option) })
        }
    }
}

@Composable
private fun SearchRow(viewModel: InsightsViewModel) {
    AppSearchBar(
        query = viewModel.query,
        onQueryChange = viewModel::updateQuery,
        onClear = { viewModel.updateQuery("") },
        placeholder = "Search by app or category",
    )
}

@Composable
private fun FilterRow(viewModel: InsightsViewModel) {
    val report = viewModel.report
    val categoryName = report.categories.firstOrNull { it.id == viewModel.categoryFilter }?.name
    val bucketStart = viewModel.bucketFilter
    val active = categoryName != null || bucketStart != null

    AnimatedVisibility(visible = active, enter = fadeIn(), exit = fadeOut()) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            categoryName?.let { Chip(label = it, selected = true, onClick = viewModel::clearFilters) }
            bucketStart?.let {
                Chip(
                    label = "${formatBucket(it, report.range)} +${report.range.bucketLabel}",
                    selected = true,
                    onClick = viewModel::clearFilters,
                )
            }
            Spacer(Modifier.weight(1f))
            Text(
                "Clear",
                style = ZType.Sub,
                color = ZColors.AccentSoft,
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .springyClick(onClick = viewModel::clearFilters)
                    .padding(horizontal = 6.dp, vertical = 4.dp),
            )
        }
    }
}

@Composable
private fun InsightsToolbar(
    range: InsightRange,
    loading: Boolean,
    canExport: Boolean,
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
            Chip(label = option.label, selected = option == range, onClick = { onRange(option) })
        }
        Spacer(Modifier.weight(1f))
        AnimatedVisibility(visible = loading, enter = fadeIn(), exit = fadeOut()) { ScanPulse() }
        Chip(label = "⟳", selected = false, onClick = onRefresh)
        // 出せるものが無いときは置かない。押せるのに必ず失敗するボタンを残さない
        AnimatedVisibility(visible = canExport, enter = fadeIn(), exit = fadeOut()) {
            Chip(label = "Export", selected = false, onClick = onExport)
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
private fun Notice(message: String?, onDismiss: () -> Unit) {
    AnimatedVisibility(visible = message != null, enter = fadeIn(), exit = fadeOut()) {
        Row(
            Modifier.fillMaxWidth().padding(start = 12.dp, end = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Box(Modifier.size(6.dp).clip(CircleShape).background(ZColors.StatusAmber))
            Text(
                text = message.orEmpty(),
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
        ChartSpacer(6.dp)
        Row(horizontalArrangement = Arrangement.spacedBy(22.dp)) {
            Column {
                Text("↓ Down", style = ZType.Sub, color = ZColors.TextSecondary)
                MetricValue(downValue, downUnit, fontSize = 24.sp)
            }
            Column {
                Text("↑ Up", style = ZType.Sub, color = ZColors.TextSecondary)
                MetricValue(upValue, upUnit, fontSize = 24.sp)
            }
        }
        ChartSpacer(8.dp)
        Text(
            text = "${report.bucketStarts.size} buckets of ${report.range.bucketLabel} · " +
                "${report.apps.size} apps · ${report.entries.size} rows",
            style = ZType.Sub,
            color = ZColors.TextDim,
        )
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
    onOpen: () -> Unit,
) {
    val fill = chartColor(rollup.colorIndex)
    val dot = identityColor(rollup.colorIndex)
    val background by animateColorAsState(
        targetValue = if (selected) ZColors.SurfaceHigh else ZColors.Surface,
        animationSpec = ZMotion.value(),
        label = "categoryBackground",
    )
    val (value, unit) = formatBytes(rollup.totalBytes)
    val (rx, rxUnit) = formatBytes(rollup.rxBytes)
    val (tx, txUnit) = formatBytes(rollup.txBytes)

    Column(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(background)
            .border(1.dp, if (selected) dot.copy(alpha = 0.5f) else ZColors.Outline, RoundedCornerShape(10.dp))
            .springyClick(onClick = onClick)
            .padding(horizontal = 11.dp, vertical = 9.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(Modifier.size(8.dp).clip(CircleShape).background(dot))
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
        ChartSpacer(7.dp)
        ShareBar(fraction = if (shareOf <= 0) 0f else rollup.totalBytes.toFloat() / shareOf.toFloat(), color = fill)
        ChartSpacer(6.dp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${rollup.appCount} apps · ↓ $rx $rxUnit · ↑ $tx $txUnit",
                style = ZType.Sub,
                color = ZColors.TextSecondary,
                modifier = Modifier.weight(1f),
            )
            Text(
                "Details",
                style = ZType.Sub,
                color = ZColors.AccentSoft,
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .springyClick(onClick = onOpen)
                    .padding(horizontal = 7.dp, vertical = 3.dp),
            )
        }
    }
}

@Composable
private fun UsageAccessCard(onGrant: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(start = 12.dp, end = 16.dp)) {
        DashboardCardScaffold(title = "USAGE ACCESS REQUIRED", modifier = Modifier.fillMaxWidth()) {
            ChartSpacer(8.dp)
            Text(
                text = "Android only reports per-app data usage to apps that hold usage access. " +
                    "It cannot be requested with a normal permission dialog — grant it in Settings, " +
                    "then come back to this pane.",
                style = ZType.Body,
                color = ZColors.TextSecondary,
            )
            ChartSpacer(12.dp)
            Chip(label = "Open usage access settings", selected = true, onClick = onGrant)
            ChartSpacer(10.dp)
            Text(
                text = "Without it the console still shows device-wide totals on the Overview pane.",
                style = ZType.Sub,
                color = ZColors.TextDim,
            )
        }
    }
}

// ---- 表示ヘルパ -------------------------------------------------------------

private val InsightTransport.label: String
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
