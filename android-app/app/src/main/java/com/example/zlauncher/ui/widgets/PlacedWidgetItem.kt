package com.example.zlauncher.ui.widgets

import android.appwidget.AppWidgetHostView
import android.os.Build
import android.os.Bundle
import android.util.SizeF
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
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
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.data.widgets.WidgetHostController
import com.example.zlauncher.domain.model.WidgetPlacement
import kotlin.math.roundToInt

/**
 * 置いたウィジェット 1 件。
 *
 * 幅は呼び出し側（行）が weight で与える。ここは与えられた幅いっぱいに描き、高さだけ持つ。
 *
 * **操作の口はここに置かない。** 幅は 1 列（≒60dp）まで細くできるので、枠の中に
 * ラベルやボタンを並べると必ず溢れる。Layout 中に選ぶと、操作はペイン上部の
 * 共通バーに出る ― ここに出すのは高さのつまみだけ。
 */
@Composable
fun PlacedWidgetItem(
    placement: WidgetPlacement,
    controller: WidgetHostController,
    editing: Boolean,
    selected: Boolean,
    onSelect: () -> Unit,
    onHeightChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val info = remember(placement.appWidgetId) { controller.providerInfo(placement.appWidgetId) }
    val shape = RoundedCornerShape(14.dp)

    // ドラッグ中は保存せずここで持つ。1px ごとに書くと DataStore が悲鳴を上げる
    var draftHeight by remember(placement.appWidgetId) { mutableFloatStateOf(placement.heightDp.toFloat()) }
    var dragging by remember { mutableStateOf(false) }
    val heightDp = if (dragging) WidgetPlacement.clampHeight(draftHeight.roundToInt()) else placement.heightDp

    Column(
        modifier
            .clip(shape)
            .background(if (selected) ZColors.SurfaceHigh else ZColors.Surface.copy(alpha = 0.55f))
            .border(
                if (selected) 2.dp else 1.dp,
                if (selected) ZColors.Accent else ZColors.Outline,
                shape,
            )
            .then(if (editing) Modifier.springyClick(onClick = onSelect) else Modifier)
            .padding(3.dp),
    ) {
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

        // つまみは選択中だけ。常時出すと、縦にスクロールしたつもりが
        // ウィジェットの高さを変えてしまう
        if (editing && selected && info != null) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(22.dp)
                    .pointerInput(placement.appWidgetId) {
                        detectVerticalDragGestures(
                            onDragStart = {
                                dragging = true
                                draftHeight = placement.heightDp.toFloat()
                            },
                            onVerticalDrag = { change, delta ->
                                change.consume()
                                draftHeight = (draftHeight + delta / density.density).coerceIn(
                                    WidgetPlacement.MIN_HEIGHT_DP.toFloat(),
                                    WidgetPlacement.MAX_HEIGHT_DP.toFloat(),
                                )
                            },
                            // 保存は指を離してから 1 回だけ
                            onDragEnd = {
                                dragging = false
                                onHeightChange(WidgetPlacement.clampHeight(draftHeight.roundToInt()))
                            },
                            onDragCancel = { dragging = false },
                        )
                    },
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    Modifier
                        .width(32.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(if (dragging) ZColors.Accent else ZColors.OutlineStrong)
                )
            }
        }
    }
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
