package com.example.zlauncher.ui.widgets

import android.appwidget.AppWidgetHostView
import android.os.Build
import android.os.Bundle
import android.util.SizeF
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.data.widgets.WidgetHostController
import com.example.zlauncher.domain.model.WidgetPlacement
import kotlin.math.roundToInt

/**
 * 置いたウィジェット 1 件。
 *
 * 幅は呼び出し側（行）が weight で与える。ここは与えられた幅いっぱいに描き、高さだけ持つ。
 *
 * **Layout 中はウィジェット本体にタッチを渡さない。** ウィジェットは自前のビューなので、
 * 何もしなければタップがそのまま提供元アプリに届き、選ぼうとしただけでアプリが開く。
 * 膜（[EditVeil]）を上にかぶせて、選択・移動・リサイズはすべてそちらで受ける。
 *
 * 操作の口のうち、幅と高さの**つまみは枠の縁に出す**。1 列ぶんの幅にボタンは並ばないので
 * 数値の増減はペイン上部のバーに置いてあるが、つまみが無いと「どこを掴めば伸びるのか」が
 * 分からない。
 */
@Composable
fun PlacedWidgetItem(
    placement: WidgetPlacement,
    controller: WidgetHostController,
    editing: Boolean,
    selected: Boolean,
    lifted: Boolean,
    columnWidth: Dp,
    onSelect: () -> Unit,
    onHeightChange: (Int) -> Unit,
    onSpanChange: (Int) -> Unit,
    dragHandle: Modifier,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val info = remember(placement.appWidgetId) { controller.providerInfo(placement.appWidgetId) }
    val shape = RoundedCornerShape(14.dp)

    // ドラッグ中は保存せずここで持つ。1px ごとに書くと DataStore が悲鳴を上げる
    var draftHeight by remember(placement.appWidgetId) { mutableFloatStateOf(placement.heightDp.toFloat()) }
    var resizing by remember { mutableStateOf(false) }
    val heightDp = if (resizing) WidgetPlacement.clampHeight(draftHeight.roundToInt()) else placement.heightDp

    Column(
        modifier
            .clip(shape)
            .background(if (selected || lifted) ZColors.SurfaceHigh else ZColors.Surface.copy(alpha = 0.55f))
            .border(
                if (selected || lifted) 2.dp else 1.dp,
                if (selected || lifted) ZColors.Accent else ZColors.Outline,
                shape,
            )
            .padding(3.dp),
    ) {
        Box(Modifier.fillMaxWidth()) {
            if (info != null) {
                // ウィジェットは与えられた寸法で描画を選ぶ。実測幅を渡さないと、
                // 高さだけ変えても中身が前のレイアウトのまま伸び縮みする
                BoxWithConstraints(Modifier.fillMaxWidth()) {
                    val widthDp = maxWidth
                    AndroidView(
                        factory = { ctx -> controller.createView(ctx, placement.appWidgetId, info) },
                        update = { view -> view.applySize(widthDp, heightDp.dp) },
                        modifier = Modifier.fillMaxWidth().height(heightDp.dp),
                    )
                }
            } else {
                Box(
                    Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 6.dp),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Text("Provider app not found", style = ZType.Sub, color = ZColors.TextDim)
                }
            }

            if (editing) {
                EditVeil(
                    selected = selected,
                    onSelect = onSelect,
                    dragHandle = dragHandle,
                    modifier = Modifier.matchParentSize(),
                )
            }

            if (editing && selected && info != null) {
                WidthHandle(
                    columnWidth = columnWidth,
                    span = placement.widthSpan,
                    onSpanChange = onSpanChange,
                    modifier = Modifier.align(Alignment.CenterEnd),
                )
                HeightHandle(
                    appWidgetId = placement.appWidgetId,
                    resizing = resizing,
                    onStart = {
                        resizing = true
                        draftHeight = placement.heightDp.toFloat()
                    },
                    onDrag = { delta ->
                        draftHeight = (draftHeight + delta / density.density).coerceIn(
                            WidgetPlacement.MIN_HEIGHT_DP.toFloat(),
                            WidgetPlacement.MAX_HEIGHT_DP.toFloat(),
                        )
                    },
                    // 保存は指を離してから 1 回だけ
                    onEnd = {
                        resizing = false
                        onHeightChange(WidgetPlacement.clampHeight(draftHeight.roundToInt()))
                    },
                    onCancel = { resizing = false },
                    modifier = Modifier.align(Alignment.BottomCenter),
                )
            }
        }
    }
}

/**
 * Layout 中に中身へかぶせる膜。
 *
 * これが無いと、ウィジェットのビューがタップを先に取って提供元アプリが開いてしまう。
 * 選択のタップと長押しの並べ替えはここで受ける（本体には一切渡さない）。
 */
@Composable
private fun EditVeil(
    selected: Boolean,
    onSelect: () -> Unit,
    dragHandle: Modifier,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .background(
                if (selected) ZColors.Accent.copy(alpha = 0.10f) else ZColors.Background.copy(alpha = 0.28f)
            )
            .pointerInput(Unit) { detectTapGestures { onSelect() } }
            .then(dragHandle)
    )
}

/**
 * 右端の幅つまみ。
 *
 * 幅は列なので、指の移動が 1 列の半分を越えたところで 1 列ぶん動かす。dp をそのまま
 * 幅にすると列から外れ、行の詰め方と食い違う。
 */
@Composable
private fun WidthHandle(
    columnWidth: Dp,
    span: Int,
    onSpanChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    var dragged by remember { mutableStateOf(0f) }
    var active by remember { mutableStateOf(false) }
    val stepPx = with(density) { columnWidth.toPx() }.coerceAtLeast(1f)

    Box(
        modifier
            .width(26.dp)
            .fillMaxHeight()
            .pointerInput(span, stepPx) {
                detectHorizontalDragGestures(
                    onDragStart = {
                        active = true
                        dragged = 0f
                    },
                    onHorizontalDrag = { change, delta ->
                        change.consume()
                        dragged += delta
                        // 半列を越えた時点で 1 列動かし、越えたぶんを繰り越す
                        while (dragged >= stepPx / 2f) {
                            onSpanChange(WidgetPlacement.clampSpan(span + 1))
                            dragged -= stepPx
                        }
                        while (dragged <= -stepPx / 2f) {
                            onSpanChange(WidgetPlacement.clampSpan(span - 1))
                            dragged += stepPx
                        }
                    },
                    onDragEnd = { active = false },
                    onDragCancel = { active = false },
                )
            },
        contentAlignment = Alignment.Center,
    ) {
        Grip(vertical = true, active = active)
    }
}

@Composable
private fun HeightHandle(
    appWidgetId: Int,
    resizing: Boolean,
    onStart: () -> Unit,
    onDrag: (Float) -> Unit,
    onEnd: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .fillMaxWidth()
            .height(26.dp)
            .pointerInput(appWidgetId) {
                detectVerticalDragGestures(
                    onDragStart = { onStart() },
                    onVerticalDrag = { change, delta ->
                        change.consume()
                        onDrag(delta)
                    },
                    onDragEnd = { onEnd() },
                    onDragCancel = { onCancel() },
                )
            },
        contentAlignment = Alignment.Center,
    ) {
        Grip(vertical = false, active = resizing)
    }
}

/** つまみの見た目。掴める場所だと分かる大きさにする */
@Composable
private fun Grip(vertical: Boolean, active: Boolean) {
    val color = if (active) ZColors.Accent else ZColors.AccentSoft
    Box(
        Modifier
            .then(if (vertical) Modifier.width(5.dp).height(34.dp) else Modifier.width(34.dp).height(5.dp))
            .clip(RoundedCornerShape(3.dp))
            .background(color),
    )
}

/** 空いた列。Layout 中だけ枠で見せる ― 入ることが分からないと幅を縮める意味が伝わらない */
@Composable
fun FreeWidgetSlot(span: Int, modifier: Modifier = Modifier) {
    Box(
        modifier
            .height(56.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(ZColors.Surface.copy(alpha = 0.3f))
            .border(1.dp, ZColors.Outline, RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Text("$span free", style = ZType.Eyebrow, color = ZColors.TextDim, maxLines = 1)
    }
}

/**
 * ホストビューに寸法を伝える。
 *
 * API 31 以降は SizeF のリストを渡す形が正で、それ以前の 4 引数版は非推奨。
 * どちらも呼ばないと、ウィジェットは初回に選んだレイアウトのまま引き伸ばされる。
 */
private fun AppWidgetHostView.applySize(width: Dp, height: Dp) {
    val w = width.value
    val h = height.value
    if (w <= 0f || h <= 0f) return
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        updateAppWidgetSize(Bundle.EMPTY, listOf(SizeF(w, h)))
    } else {
        @Suppress("DEPRECATION")
        updateAppWidgetSize(Bundle.EMPTY, w.roundToInt(), h.roundToInt(), w.roundToInt(), h.roundToInt())
    }
}
