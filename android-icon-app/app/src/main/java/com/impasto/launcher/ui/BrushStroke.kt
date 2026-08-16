package com.impasto.launcher.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.pow
import kotlin.math.sin
import kotlin.random.Random

const val STROKE_MS = 430
const val STROKE_FADE_MS = 260

/** One bristle in the brush: its offset across the ferrule, colour and alpha. */
private data class Bristle(
    val offset: Float,
    val color: Color,
    val jitter: Float,
    val alpha: Float,
    val breaks: Boolean,
)

/**
 * The stroke that runs across a painting when it is touched.
 *
 * A single quadratic sweep, drawn by a bundle of bristles rather than one line:
 * pressure tapers at both ends, each hair carries a slightly different colour
 * lifted from the painting, and a few of them lift off mid-stroke so the paint
 * reads as running dry.
 *
 * [trigger] is a counter — incrementing it starts a new stroke, so repeated
 * taps each get their own angle and bristle set.
 */
@Composable
fun BrushStroke(
    colors: List<Color>,
    trigger: Int,
    modifier: Modifier = Modifier,
    onFinished: () -> Unit = {},
) {
    val progress = remember { Animatable(0f) }
    val fade = remember { Animatable(1f) }
    var shape by remember { mutableStateOf<StrokeShape?>(null) }

    LaunchedEffect(trigger) {
        if (trigger == 0) return@LaunchedEffect
        shape = StrokeShape.random(colors, Random(trigger * 7919))
        fade.snapTo(1f)
        progress.snapTo(0f)
        progress.animateTo(1f, tween(STROKE_MS, easing = LinearEasing))
        fade.animateTo(0f, tween(STROKE_FADE_MS))
        shape = null
        onFinished()
    }

    Canvas(modifier) {
        val s = shape ?: return@Canvas
        drawStroke(s, progress.value, fade.value)
    }
}

private class StrokeShape(
    val angle: Float,
    val bow: Float,
    val bristles: List<Bristle>,
) {
    companion object {
        fun random(colors: List<Color>, rnd: Random): StrokeShape {
            val palette = colors.ifEmpty { listOf(Color(0xFFF0EBDF)) }
            val hairs = (0 until COUNT).map { i ->
                Bristle(
                    offset = (i.toFloat() / (COUNT - 1) - 0.5f) * 2f,
                    color = palette[rnd.nextInt(palette.size)],
                    jitter = (rnd.nextFloat() - 0.5f) * 0.35f,
                    alpha = 0.30f + rnd.nextFloat() * 0.42f,
                    breaks = rnd.nextFloat() < 0.18f,
                )
            }
            return StrokeShape(
                angle = -0.9f + rnd.nextFloat() * 1.8f,
                bow = (rnd.nextFloat() - 0.5f) * 0.28f,
                bristles = hairs,
            )
        }

        const val COUNT = 11
    }
}

private fun DrawScope.drawStroke(s: StrokeShape, t: Float, fade: Float) {
    if (t <= 0f || fade <= 0f) return

    val w = size.width
    val cx = size.width / 2f
    val cy = size.height / 2f
    val len = w * 0.92f
    val ux = cos(s.angle)
    val uy = sin(s.angle)

    val p0 = Offset(cx - ux * len / 2f, cy - uy * len / 2f)
    val p1 = Offset(cx + ux * len / 2f, cy + uy * len / 2f)
    val bow = s.bow * w
    val cp = Offset((p0.x + p1.x) / 2f - uy * bow, (p0.y + p1.y) / 2f + ux * bow)

    fun at(u: Float): Offset {
        val m = 1f - u
        return Offset(
            m * m * p0.x + 2f * m * u * cp.x + u * u * p1.x,
            m * m * p0.y + 2f * m * u * cp.y + u * u * p1.y,
        )
    }

    val half = w * 0.115f
    val steps = (SEGMENTS * t).toInt().coerceAtLeast(1)
    var prev = at(0f)

    for (i in 1..steps) {
        val u = i.toFloat() / SEGMENTS
        val p = at(u)
        // pressure: thin where the brush lands and lifts, loaded in between
        val press = sin(Math.PI.toFloat() * u).pow(0.55f)
        val nx = -(p.y - prev.y)
        val ny = (p.x - prev.x)
        val nl = hypot(nx, ny).takeIf { it > 0f } ?: 1f

        for (b in s.bristles) {
            if (b.breaks && u > 0.45f && u < 0.72f) continue      // runs dry
            val off = (b.offset + b.jitter) * half * press
            val ox = nx / nl * off
            val oy = ny / nl * off
            drawLine(
                color = b.color.copy(alpha = b.alpha * press * fade),
                start = Offset(prev.x + ox, prev.y + oy),
                end = Offset(p.x + ox, p.y + oy),
                strokeWidth = 1.5f + press * 1.1f,
                cap = StrokeCap.Round,
            )
        }
        prev = p
    }
}

private const val SEGMENTS = 90
