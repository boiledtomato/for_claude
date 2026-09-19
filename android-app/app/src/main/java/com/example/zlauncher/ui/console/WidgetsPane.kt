package com.example.zlauncher.ui.console

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZMotion
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.ui.rememberListReorderState
import com.example.zlauncher.core.ui.reorderableHandle
import com.example.zlauncher.core.ui.reorderableSlot
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

    // 取り外しモード。Layout を開かずに、長押しから直接入れる導線
    var removing by remember { mutableStateOf(false) }
    val marked = remember { mutableStateListOf<Int>() }
    var confirming by remember { mutableStateOf(false) }

    fun exitRemoval() {
        removing = false
        marked.clear()
    }

    // 取り外しモード中の戻るは、まずモードを抜ける
    BackHandler(enabled = removing) { exitRemoval() }

    // 消えたウィジェットを選んだままにしない（別経路で外されたときのため）
    LaunchedEffect(widgets) {
        val alive = widgets.map { it.appWidgetId }.toSet()
        marked.retainAll { it in alive }
        if (removing && widgets.isEmpty()) exitRemoval()
    }

    val reorder = rememberListReorderState(onMove = viewModel::moveWidgetTo)
    reorder.count = widgets.size

    val rows = remember(widgets) { WidgetFlow.rows(widgets) }
    // 選択したウィジェットが消えた（削除した）ときは、バーを「未選択」に戻す
    val selected = widgets.firstOrNull { it.appWidgetId == selectedId }
    val selectedIndex = widgets.indexOfFirst { it.appWidgetId == selectedId }

    Box(modifier.fillMaxSize()) {
    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 12.dp,
            end = 16.dp,
            // ボタンが最後の 1 枚に重なると、そのウィジェットだけ選べなくなる
            bottom = if (removing && marked.isNotEmpty()) 96.dp else 24.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item(key = "actions") {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = when {
                        removing && marked.isEmpty() -> "Tap the widgets to remove"
                        removing && marked.size == 1 -> "1 selected"
                        removing -> "${marked.size} selected"
                        widgets.isEmpty() -> "No widgets yet"
                        widgets.size == 1 -> "1 widget"
                        else -> "${widgets.size} widgets"
                    },
                    style = ZType.Sub,
                    color = if (removing) ZColors.Danger else ZColors.TextSecondary,
                    modifier = Modifier.weight(1f),
                )
                if (removing) {
                    PillAction(label = "Cancel", accent = false, onClick = { exitRemoval() })
                } else {
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
                                "narrow ones share a row. “Layout” changes width, height and order, " +
                                "and a long press on a placed widget selects it for removal.",
                            style = ZType.Sub,
                            color = ZColors.TextSecondary,
                        )
                    }
                }
            }
        }

        itemsIndexed(rows, key = { _, row -> row.widgets.first().appWidgetId }) { rowIndex, row ->
            val firstIndex = rows.take(rowIndex).sumOf { it.widgets.size }
            BoxWithConstraints(Modifier.fillMaxWidth().animateItem(placementSpec = ZMotion.placement())) {
                // 幅つまみは「1 列ぶん」を知らないと動かせない。列の幅は行の実測から出す
                val columnWidth = (maxWidth - ROW_GAP * (WidgetPlacement.COLUMNS - 1)) / WidgetPlacement.COLUMNS
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(ROW_GAP),
                    verticalAlignment = Alignment.Top,
                ) {
                row.widgets.forEachIndexed { indexInRow, placement ->
                    val index = firstIndex + indexInRow
                    val dragging = reorder.draggingIndex == index
                    val active = reorder.isActive(index)
                    PlacedWidgetItem(
                        placement = placement,
                        controller = widgetHost,
                        editing = editing,
                        removing = removing,
                        marked = placement.appWidgetId in marked,
                        index = index,
                        onLongPress = {
                            removing = true
                            selectedId = null
                            if (placement.appWidgetId !in marked) marked.add(placement.appWidgetId)
                        },
                        onToggleMark = {
                            if (!marked.remove(placement.appWidgetId)) marked.add(placement.appWidgetId)
                        },
                        selected = editing && placement.appWidgetId == selectedId,
                        lifted = dragging,
                        columnWidth = columnWidth,
                        onSelect = {
                            selectedId = if (selectedId == placement.appWidgetId) null else placement.appWidgetId
                        },
                        onHeightChange = { viewModel.setWidgetHeight(placement.appWidgetId, it) },
                        onSpanChange = { viewModel.setWidgetSpan(placement.appWidgetId, it) },
                        dragHandle = Modifier.reorderableHandle(reorder, index, enabled = editing),
                        modifier = Modifier
                            .weight(WidgetPlacement.clampSpan(placement.widthSpan).toFloat())
                            // つまみ上げた 1 枚は必ず手前に。奥に潜ると指の下から消える
                            .zIndex(if (active) 1f else 0f)
                            .graphicsLayer {
                                translationX = if (active) reorder.dragOffset.x else 0f
                                translationY = if (active) reorder.dragOffset.y else 0f
                                val scale = if (dragging) ZMotion.LIFT_SCALE else 1f
                                scaleX = scale
                                scaleY = scale
                            }
                            .reorderableSlot(reorder, index),
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

        // 実行ボタンは**右下に浮かせる**。一覧は縦に長く、下のほうのウィジェットを
        // 外すのに上のバーまで戻らせない、というのがこのモードの存在理由
        AnimatedVisibility(
            visible = removing && marked.isNotEmpty(),
            modifier = Modifier.align(Alignment.BottomEnd).padding(end = 16.dp, bottom = 20.dp),
            enter = scaleIn(initialScale = 0.85f) + fadeIn(),
            exit = scaleOut(targetScale = 0.85f) + fadeOut(),
        ) {
            RemoveButton(count = marked.size, onClick = { confirming = true })
        }
    }

    if (confirming) {
        val count = marked.size
        ConfirmDialog(
            title = "Remove widgets",
            message = if (count == 1) {
                "Remove 1 widget from the console? The app it belongs to is not touched."
            } else {
                "Remove $count widgets from the console? The apps they belong to are not touched."
            },
            onConfirm = {
                viewModel.removeWidgets(marked.toList())
                confirming = false
                exitRemoval()
            },
            onDismiss = { confirming = false },
        )
    }
}

/**
 * 右下の実行ボタン。
 *
 * 押すと消えるものなので、面の中のチップとは色を分ける（暗い配色ではピンク地に白）。
 * 幅いっぱいの帯にしないのは、一覧をスクロールしながら選べるようにするため ―
 * 帯だと最下段のウィジェットが常に隠れる。
 */
@Composable
private fun RemoveButton(count: Int, onClick: () -> Unit) {
    Box(
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(ZColors.Danger)
            .springyClick(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = if (count == 1) "Remove 1" else "Remove $count",
            style = ZType.Body.copy(fontSize = 13.5.sp),
            color = ZColors.OnDanger,
        )
    }
}

/** 行の中の隙間。列の幅を実測から割り出すのに同じ値が要る */
private val ROW_GAP = 8.dp

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
                "Width moves in columns of ${WidgetPlacement.COLUMNS}, so narrowing one lets the next " +
                    "sit beside it. Long-press a widget to drag it somewhere else.",
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
