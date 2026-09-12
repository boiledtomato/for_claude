package com.example.zlauncher.ui.console

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZMotion
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.data.widgets.WidgetHostController
import com.example.zlauncher.domain.model.WidgetPlacement
import com.example.zlauncher.ui.widgets.FreeWidgetSlot
import com.example.zlauncher.ui.widgets.PlacedWidgetItem
import com.example.zlauncher.ui.widgets.WidgetFlow
import kotlin.math.roundToInt

/**
 * 置いたウィジェットの面。
 *
 * **置く場所と足す場所を同じ画面にする。** 以前はホーム（全アプリのグリッド）に並び、
 * 追加はコンソールの設定行という分かれ方をしていて、どこに増えるのか読めなかった。
 *
 * 並びは [WidgetPlacement.COLUMNS] 列のグリッドに左から流し込む。全幅固定をやめたのは、
 * 2×1 の時計に 1 行を丸ごと使わせる理由が無いため ― 空いた列には次のウィジェットが入る。
 * 大きさを変えるのは Layout のときだけで、操作は選んだ 1 件に対して上のバーから行う。
 */
@Composable
fun WidgetsPane(
    viewModel: ConsoleViewModel,
    widgetHost: WidgetHostController,
    onAddWidget: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val widgets by viewModel.widgets.collectAsStateWithLifecycle()
    var editing by remember { mutableStateOf(false) }
    var selectedId by remember { mutableStateOf<Int?>(null) }

    val rows = remember(widgets) { WidgetFlow.rows(widgets) }
    // 選択したウィジェットが消えた（削除した）ときは、バーを「未選択」に戻す
    val selected = widgets.firstOrNull { it.appWidgetId == selectedId }
    val selectedIndex = widgets.indexOfFirst { it.appWidgetId == selectedId }

    LazyColumn(
        modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 12.dp, end = 16.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item(key = "actions") {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = when (widgets.size) {
                        0 -> "No widgets yet"
                        1 -> "1 widget"
                        else -> "${widgets.size} widgets"
                    },
                    style = ZType.Sub,
                    color = ZColors.TextSecondary,
                    modifier = Modifier.weight(1f),
                )
                if (widgets.isNotEmpty()) {
                    PillAction(
                        label = if (editing) "Done" else "Layout",
                        accent = editing,
                        onClick = {
                            editing = !editing
                            selectedId = null
                        },
                    )
                }
                PillAction(label = "Add widget", accent = !editing, onClick = onAddWidget)
            }
        }

        if (editing) {
            item(key = "controls") {
                val info = selected?.let { widgetHost.providerInfo(it.appWidgetId) }
                SizeBar(
                    name = when {
                        selected == null -> null
                        info != null -> info.loadLabel(context.packageManager).toString()
                        else -> "Unavailable widget"
                    },
                    placement = selected,
                    canMoveBack = selectedIndex > 0,
                    canMoveForward = selectedIndex >= 0 && selectedIndex < widgets.lastIndex,
                    onSpanStep = { delta ->
                        selected?.let {
                            viewModel.setWidgetSpan(it.appWidgetId, WidgetPlacement.clampSpan(it.widthSpan + delta))
                        }
                    },
                    onHeightStep = { delta ->
                        selected?.let {
                            viewModel.setWidgetHeight(
                                it.appWidgetId,
                                WidgetPlacement.clampHeight(it.heightDp + delta),
                            )
                        }
                    },
                    onMove = { delta -> selected?.let { viewModel.moveWidget(it.appWidgetId, delta) } },
                    onReset = {
                        val target = selected ?: return@SizeBar
                        val provider = info ?: return@SizeBar
                        val density = context.resources.displayMetrics.density
                        viewModel.setWidgetHeight(
                            target.appWidgetId,
                            WidgetPlacement.clampHeight((provider.minHeight / density).roundToInt()),
                        )
                        viewModel.setWidgetSpan(
                            target.appWidgetId,
                            WidgetPlacement.spanForWidthDp((provider.minWidth / density).roundToInt()),
                        )
                    },
                    onRemove = {
                        selected?.let { viewModel.removeWidget(it.appWidgetId) }
                        selectedId = null
                    },
                )
            }
        }

        if (widgets.isEmpty()) {
            item(key = "empty") {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(ZColors.Surface)
                        .border(1.dp, ZColors.Outline, RoundedCornerShape(14.dp))
                        .padding(horizontal = 14.dp, vertical = 22.dp),
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text("Nothing placed yet", style = ZType.Body, color = ZColors.TextPrimary)
                        Text(
                            "Widgets arrive at the size their own app asks for and keep it, so two " +
                                "narrow ones share a row. “Layout” changes width, height and order.",
                            style = ZType.Sub,
                            color = ZColors.TextSecondary,
                        )
                    }
                }
            }
        }

        itemsIndexed(rows, key = { _, row -> row.widgets.first().appWidgetId }) { _, row ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .animateItem(placementSpec = ZMotion.placement()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Top,
            ) {
                row.widgets.forEach { placement ->
                    PlacedWidgetItem(
                        placement = placement,
                        controller = widgetHost,
                        editing = editing,
                        selected = editing && placement.appWidgetId == selectedId,
                        onSelect = {
                            selectedId = if (selectedId == placement.appWidgetId) null else placement.appWidgetId
                        },
                        onHeightChange = { viewModel.setWidgetHeight(placement.appWidgetId, it) },
                        modifier = Modifier.weight(
                            WidgetPlacement.clampSpan(placement.widthSpan).toFloat()
                        ),
                    )
                }
                if (row.freeSpan > 0) {
                    if (editing) {
                        FreeWidgetSlot(span = row.freeSpan, modifier = Modifier.weight(row.freeSpan.toFloat()))
                    } else {
                        Box(Modifier.weight(row.freeSpan.toFloat()))
                    }
                }
            }
        }
    }
}

/**
 * 選んだ 1 件を操作するバー。
 *
 * ウィジェットの枠内ではなくここに置く理由は [PlacedWidgetItem] のとおり ―
 * 1 列ぶんの幅にボタンは入らない。ペイン幅いっぱいなら、どの大きさの相手でも同じ操作になる。
 */
@Composable
private fun SizeBar(
    name: String?,
    placement: WidgetPlacement?,
    canMoveBack: Boolean,
    canMoveForward: Boolean,
    onSpanStep: (Int) -> Unit,
    onHeightStep: (Int) -> Unit,
    onMove: (Int) -> Unit,
    onReset: () -> Unit,
    onRemove: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(ZColors.Surface)
            .border(1.dp, ZColors.Accent.copy(alpha = 0.35f), RoundedCornerShape(14.dp))
            .padding(horizontal = 10.dp, vertical = 9.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        if (placement == null) {
            Text("Tap a widget to resize it", style = ZType.Sub, color = ZColors.TextSecondary)
            Text(
                "Width moves in columns of ${WidgetPlacement.COLUMNS}. Narrow a widget and the one " +
                    "after it moves up beside it.",
                style = ZType.Sub,
                color = ZColors.TextDim,
            )
            return@Column
        }

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = name.orEmpty(),
                style = ZType.Body.copy(fontSize = 12.5.sp),
                color = ZColors.TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = "${placement.widthSpan}/${WidgetPlacement.COLUMNS} · ${placement.heightDp}dp",
                style = ZType.Eyebrow,
                color = ZColors.AccentSoft,
                maxLines = 1,
            )
        }

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text("Width", style = ZType.Eyebrow, color = ZColors.TextDim)
            SquareButton("−", enabled = placement.widthSpan > WidgetPlacement.MIN_SPAN) { onSpanStep(-1) }
            SquareButton("＋", enabled = placement.widthSpan < WidgetPlacement.COLUMNS) { onSpanStep(1) }
            Box(Modifier.size(6.dp))
            Text("Height", style = ZType.Eyebrow, color = ZColors.TextDim)
            SquareButton("−", enabled = placement.heightDp > WidgetPlacement.MIN_HEIGHT_DP) {
                onHeightStep(-WidgetPlacement.HEIGHT_STEP_DP)
            }
            SquareButton("＋", enabled = placement.heightDp < WidgetPlacement.MAX_HEIGHT_DP) {
                onHeightStep(WidgetPlacement.HEIGHT_STEP_DP)
            }
        }

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text("Order", style = ZType.Eyebrow, color = ZColors.TextDim)
            SquareButton("◀", enabled = canMoveBack) { onMove(-1) }
            SquareButton("▶", enabled = canMoveForward) { onMove(1) }
            Box(Modifier.weight(1f))
            TextAction("Reset size", onClick = onReset)
            TextAction("Remove", onClick = onRemove)
        }
    }
}

@Composable
private fun SquareButton(label: String, enabled: Boolean = true, onClick: () -> Unit) {
    Box(
        Modifier
            .size(28.dp)
            .alpha(if (enabled) 1f else 0.3f)
            .clip(RoundedCornerShape(8.dp))
            .background(ZColors.SurfaceHigh)
            .border(1.dp, ZColors.Outline, RoundedCornerShape(8.dp))
            .springyClick(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Text(label, style = ZType.Body.copy(fontSize = 13.sp), color = ZColors.TextSecondary)
    }
}

@Composable
private fun TextAction(label: String, onClick: () -> Unit) {
    Text(
        label,
        style = ZType.Sub,
        color = ZColors.TextSecondary,
        maxLines = 1,
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .springyClick(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp),
    )
}

@Composable
private fun PillAction(label: String, accent: Boolean, onClick: () -> Unit) {
    Text(
        label,
        style = ZType.Body.copy(fontSize = 12.5.sp),
        color = if (accent) ZColors.AccentSoft else ZColors.TextSecondary,
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (accent) ZColors.Accent.copy(alpha = 0.14f) else ZColors.SurfaceHigh)
            .border(
                1.dp,
                if (accent) ZColors.Accent.copy(alpha = 0.5f) else ZColors.Outline,
                RoundedCornerShape(999.dp),
            )
            .springyClick(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 9.dp),
    )
}
