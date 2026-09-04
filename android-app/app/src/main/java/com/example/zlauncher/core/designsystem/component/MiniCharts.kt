package com.example.zlauncher.core.designsystem.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.zlauncher.core.designsystem.ZColors

/**
 * ドーナツ型のリング。達成率・スコア 1 値の表現に使う。
 */
@Composable
fun ProgressRing(
    progress: Float,
    modifier: Modifier = Modifier,
    diameter: Dp = 60.dp,
    strokeWidth: Dp = 6.dp,
    color: Color? = null,
    content: @Composable BoxScope.() -> Unit = {},
) {
    val safeProgress = progress.coerceIn(0f, 1f)
    Box(modifier.size(diameter), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize()) {
            val stroke = strokeWidth.toPx()
            val inset = stroke / 2f
            val arcSize = Size(size.width - stroke, size.height - stroke)
            drawArc(
                color = ZColors.Outline,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(inset, inset),
                size = arcSize,
                style = Stroke(width = stroke),
            )
            val brush = if (color != null) {
                Brush.linearGradient(listOf(color, color))
            } else {
                Brush.linearGradient(listOf(ZColors.Accent, ZColors.AccentAlt))
            }
            drawArc(
                brush = brush,
                startAngle = -90f,
                sweepAngle = 360f * safeProgress,
                useCenter = false,
                topLeft = Offset(inset, inset),
                size = arcSize,
                style = Stroke(width = stroke, cap = androidx.compose.ui.graphics.StrokeCap.Round),
            )
        }
        content()
    }
}

/**
 * 推移のスパークライン。線の下は同色の 10% 塗りで面をつくる。
 */
@Composable
fun Sparkline(
    values: List<Float>,
    color: Color,
    modifier: Modifier = Modifier,
    height: Dp = 24.dp,
) {
    if (values.size < 2) {
        Box(modifier.fillMaxWidth().height(height))
        return
    }
    Canvas(modifier.fillMaxWidth().height(height)) {
        val min = values.min()
        val max = values.max()
        val range = (max - min).takeIf { it > 0.0001f } ?: 1f
        val stepX = size.width / (values.size - 1)
        val top = 2f
        val usable = size.height - top * 2

        val line = Path()
        values.forEachIndexed { index, value ->
            val x = stepX * index
            val y = top + usable - ((value - min) / range) * usable
            if (index == 0) line.moveTo(x, y) else line.lineTo(x, y)
        }
        val area = Path().apply {
            addPath(line)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        drawPath(area, color.copy(alpha = 0.10f))
        drawPath(line, color, style = Stroke(width = 1.5.dp.toPx()))
    }
}

/** 構成比の積み上げバー */
@Composable
fun StackedBar(
    segments: List<Pair<Float, Color>>,
    modifier: Modifier = Modifier,
    height: Dp = 8.dp,
) {
    Row(
        modifier.fillMaxWidth().height(height),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        segments.forEachIndexed { index, (weight, color) ->
            val shape = when {
                segments.size == 1 -> RoundedCornerShape(4.dp)
                index == 0 -> RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp)
                index == segments.lastIndex -> RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp)
                else -> RoundedCornerShape(0.dp)
            }
            Box(
                Modifier
                    .weight(weight.coerceAtLeast(0.001f))
                    .fillMaxHeight()
                    .background(color, shape)
            )
        }
    }
}

/** 直近 N 件の細い棒。最新だけ色を変えて強調する */
@Composable
fun MicroBars(
    values: List<Float>,
    modifier: Modifier = Modifier,
    height: Dp = 22.dp,
    barColor: Color = ZColors.StatusNeutral,
    lastBarColor: Color = ZColors.StatusRed,
) {
    val max = values.maxOrNull()?.takeIf { it > 0f } ?: 1f
    Row(
        modifier.fillMaxWidth().height(height),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        values.forEachIndexed { index, value ->
            BarCell(
                fraction = (value / max).coerceIn(0.08f, 1f),
                color = if (index == values.lastIndex) lastBarColor else barColor,
            )
        }
    }
}

@Composable
private fun RowScope.BarCell(fraction: Float, color: Color) {
    Box(
        Modifier
            .weight(1f)
            .fillMaxHeight(fraction)
            .background(color, RoundedCornerShape(2.dp))
    )
}
