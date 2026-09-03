package com.example.zlauncher.ui.home.component

import android.appwidget.AppWidgetHostView
import android.os.Build
import android.os.Bundle
import android.util.SizeF
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.data.widgets.WidgetHostController
import com.example.zlauncher.domain.model.WidgetPlacement
import kotlin.math.roundToInt

/**
 * ホームに置いたウィジェット 1 件。
 *
 * ウィジェット本体はタッチを消費するので、削除やサイズ変更はヘッダ行に出す
 * （ウィジェット上の長押しはウィジェット側の操作と競合する）。
 */
@Composable
fun HomeWidgetItem(
    placement: WidgetPlacement,
    controller: WidgetHostController,
    onRemove: () -> Unit,
    onHeightChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val info = remember(placement.appWidgetId) { controller.providerInfo(placement.appWidgetId) }
    val shape = RoundedCornerShape(14.dp)

    var resizing by remember { mutableStateOf(false) }
    // ドラッグ中は保存せずここで持つ。1px ごとに書くと DataStore が悲鳴を上げる
    var draftHeight by remember(placement.appWidgetId) { mutableFloatStateOf(placement.heightDp.toFloat()) }
    var dragging by remember { mutableStateOf(false) }
    val heightDp = if (dragging) WidgetPlacement.clampHeight(draftHeight.roundToInt()) else placement.heightDp

    Column(
        modifier
            .fillMaxWidth()
            .clip(shape)
            .background(ZColors.Surface)
            .border(
                1.dp,
                if (resizing) ZColors.Accent.copy(alpha = 0.5f) else ZColors.Outline,
                shape,
            )
            .padding(bottom = 6.dp),
    ) {
        Row(
            Modifier.fillMaxWidth().padding(start = 12.dp, end = 8.dp, top = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = info?.loadLabel(context.packageManager) ?: "Unavailable widget",
                style = ZType.Eyebrow,
                color = ZColors.TextSecondary,
                modifier = Modifier.weight(1f),
            )
            if (info != null) {
                HeaderAction(
                    label = if (resizing) "Done" else "Size",
                    accent = resizing,
                    onClick = {
                        draftHeight = placement.heightDp.toFloat()
                        resizing = !resizing
                    },
                )
            }
            HeaderAction(label = "Remove", accent = false, onClick = onRemove)
        }

        if (resizing && info != null) {
            ResizeRow(
                heightDp = heightDp,
                onStep = { delta -> onHeightChange(WidgetPlacement.clampHeight(placement.heightDp + delta)) },
                onReset = {
                    val fallback = (info.minHeight / density.density).roundToInt()
                    onHeightChange(WidgetPlacement.clampHeight(fallback))
                },
            )
        }

        if (info != null) {
            // ウィジェットは与えられた寸法で描画を選ぶ。実測幅を渡さないと、
            // 高さだけ変えても中身が前のレイアウトのまま伸び縮みする
            BoxWithConstraints(Modifier.fillMaxWidth().padding(horizontal = 6.dp)) {
                val widthDp = maxWidth
                AndroidView(
                    factory = { ctx -> controller.createView(ctx, placement.appWidgetId, info) },
                    update = { view -> view.applySize(widthDp, heightDp.dp) },
                    modifier = Modifier.fillMaxWidth().height(heightDp.dp),
                )
            }
        } else {
            Box(
                Modifier.fillMaxWidth().height(64.dp).padding(horizontal = 12.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                Text(
                    "Provider app not found",
                    style = ZType.Body,
                    color = ZColors.TextDim,
                )
            }
        }

        // つまみはサイズ変更中だけ出す。常時出すと、ホームを縦にスクロールしたつもりが
        // ウィジェットの高さを変えてしまう
        if (resizing && info != null) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(26.dp)
                    .pointerInput(placement.appWidgetId) {
                        detectVerticalDragGestures(
                            onDragStart = {
                                dragging = true
                                draftHeight = placement.heightDp.toFloat()
                            },
                            onVerticalDrag = { change, delta ->
                                change.consume()
                                draftHeight = (draftHeight + delta / density.density)
                                    .coerceIn(
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
                        .width(40.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(if (dragging) ZColors.Accent else ZColors.OutlineStrong)
                )
            }
        }
    }
}

@Composable
private fun ResizeRow(heightDp: Int, onStep: (Int) -> Unit, onReset: () -> Unit) {
    Row(
        Modifier.fillMaxWidth().padding(start = 12.dp, end = 8.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        StepButton("−") { onStep(-WidgetPlacement.HEIGHT_STEP_DP) }
        Text(
            "$heightDp dp",
            style = ZType.Sub,
            color = ZColors.TextPrimary,
            modifier = Modifier.width(56.dp),
        )
        StepButton("＋") { onStep(WidgetPlacement.HEIGHT_STEP_DP) }
        Text(
            "Drag the handle below to fine-tune",
            style = ZType.Sub,
            color = ZColors.TextDim,
            modifier = Modifier.weight(1f),
        )
        HeaderAction(label = "Reset", accent = false, onClick = onReset)
    }
}

@Composable
private fun StepButton(label: String, onClick: () -> Unit) {
    Box(
        Modifier
            .size(28.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(ZColors.SurfaceHigh)
            .border(1.dp, ZColors.Outline, RoundedCornerShape(8.dp))
            .springyClick(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Text(label, style = ZType.Body.copy(fontSize = 13.sp), color = ZColors.TextSecondary)
    }
}

@Composable
private fun HeaderAction(label: String, accent: Boolean, onClick: () -> Unit) {
    Text(
        text = label,
        style = ZType.Sub,
        color = if (accent) ZColors.AccentSoft else ZColors.TextSecondary,
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .springyClick(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp),
    )
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
