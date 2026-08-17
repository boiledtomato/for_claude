package com.pointille.launcher.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
private const val FADE_MS = 260
private const val SEGMENTS = 90
private const val BRISTLES = 11

/** One hair of the brush: where it sits across the ferrule, and what it carries. */
private class Bristle(
    val offset: Float,
    val color: Color,
    val jitter: Float,
    val alpha: Float,
    val breaks: Boolean,
)

private class Sweep(val angle: Float, val bow: Float, val bristles: List<Bristle>) {
    companion object {
        fun of(colors: List<Color>, rnd: Random): Sweep {
            val palette = colors.ifEmpty { listOf(Color(0xFFF0EBDF)) }
            return Sweep(
                angle = -0.9f + rnd.nextFloat() * 1.8f,
                bow = (rnd.nextFloat() - 0.5f) * 0.28f,
                bristles = (0 until BRISTLES).map { i ->
                    Bristle(
                        offset = (i.toFloat() / (BRISTLES - 1) - 0.5f) * 2f,
                        color = palette[rnd.nextInt(palette.size)],
                        jitter = (rnd.nextFloat() - 0.5f) * 0.35f,
                        alpha = 0.30f + rnd.nextFloat() * 0.42f,
                        breaks = rnd.nextFloat() < 0.18f,
                    )
                },
            )
        }
    }
}

/**
 * The stroke that runs across a painting when it is touched.
 *
 * A single sweep, drawn by a bundle of hairs rather than one line: pressure
 * tapers at both ends, each hair carries a slightly different colour lifted
 * from the painting itself, and a few lift off mid-stroke so the paint reads
 * as running dry.
 *
 * [trigger] is a counter — incrementing it starts a new stroke, so repeated
 * taps each get their own angle and their own bristles.
 */
@Composable
fun BrushStroke(
    colors: List<Color>,
    trigger: Int,
    modifier: Modifier = Modifier,
) {
    val progress = remember { Animatable(0f) }
    val fade = remember { Animatable(1f) }
    var sweep by remember { mutableStateOf<Sweep?>(null) }

    LaunchedEffect(trigger) {
        if (trigger == 0) return@LaunchedEffect
        sweep = Sweep.of(colors, Random(trigger * 7919))
        fade.snapTo(1f)
        progress.snapTo(0f)
        progress.animateTo(1f, tween(STROKE_MS, easing = LinearEasing))
        fade.animateTo(0f, tween(FADE_MS))
        sweep = null
    }

    Canvas(modifier) {
        val s = sweep ?: return@Canvas
        drawSweep(s, progress.value, fade.value)
    }
}

private fun DrawScope.drawSweep(s: Sweep, t: Float, fade: Float) {
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
            drawLine(
                color = b.color.copy(alpha = b.alpha * press * fade),
                start = Offset(prev.x + nx / nl * off, prev.y + ny / nl * off),
                end = Offset(p.x + nx / nl * off, p.y + ny / nl * off),
                strokeWidth = 1.5f + press * 1.1f,
                cap = StrokeCap.Round,
            )
        }
        prev = p
    }
}
