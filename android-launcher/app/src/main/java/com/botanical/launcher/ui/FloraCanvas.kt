package com.botanical.launcher.ui

import android.graphics.Matrix
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.sp
import com.botanical.launcher.data.AppEntry
import com.botanical.launcher.flora.Flora
import com.botanical.launcher.pencil.bloomPetals
import com.botanical.launcher.pencil.cubicAngle
import com.botanical.launcher.pencil.cubicAt
import com.botanical.launcher.pencil.pencilOutline
import com.botanical.launcher.pencil.pencilStroke
import com.botanical.launcher.pencil.polar
import com.botanical.launcher.pencil.smooth
import kotlin.math.roundToInt

private val GRAPHITE = Color(0xFF423F44)

/**
 * 版面の描画。
 *
 * 葉と花は焼いた画像を付け根で回して置く（ハッチングを毎フレーム引くのは重い）。
 * 茎と、アプリを開くときに伸びる蔓だけは形が毎フレーム変わるので線を直に引く。
 */
@Composable
fun FloraCanvas(
    flora: Flora,
    transform: SceneTransform,
    phase: () -> Float,
    boil: () -> Int,
    bloom: () -> Float,
    reach: () -> Reach?,
    reachGrow: () -> Float,
    reachBloom: () -> Float,
    bindings: Map<String, List<String>>,
    appsByKey: Map<String, AppEntry>,
    showLabels: Boolean,
    showHitAreas: Boolean,
    modifier: Modifier = Modifier,
) {
    val measurer = rememberTextMeasurer()
    val paint = remember { Paint().apply { isFilterBitmap = true; isAntiAlias = true } }
    val matrix = remember { Matrix() }

    Canvas(modifier) {
        drawPaper(flora)
        if (transform.scale <= 0f || flora.stems.isEmpty()) return@Canvas

        val ph = phase()
        val bl = boil()
        val spriteScale = transform.scale * flora.sample

        val bent = flora.stems.associate { it.id to Wind.bend(it, ph, flora.width) }

        // 茎。形が毎フレーム変わるので線を引く。
        for (stem in flora.stems) {
            val p = bent[stem.id] ?: continue
            val pts = (0..36).map { transform.toScreen(cubicAt(p, it / 36f)) }
            val w0 = stem.w0 * transform.scale
            val w1 = stem.w1 * transform.scale
            pencilStroke(
                pts = pts, sid = stem.id.hashCode(), boil = bl, color = GRAPHITE,
                widthAt = { t -> w0 - (w0 - w1) * t },
                tone = stem.tone, jitter = 0.9f, passes = 1,
                taperHead = 0.03f, taperTail = 0.12f, grain = 0.22f,
            )
            pencilStroke(
                pts = pts, sid = stem.id.hashCode() + 1, boil = bl, color = GRAPHITE,
                widthAt = { t -> (w0 - (w0 - w1) * t) * 0.52f },
                tone = stem.tone * 0.58f, jitter = 1.5f, passes = 1,
                taperHead = 0.03f, taperTail = 0.12f, grain = 0.3f,
            )
        }

        // 葉と花。焼いた画像を付け根を軸に回して置く。
        for (organ in flora.organs) {
            val bmp = organ.bitmap ?: continue
            val p = bent[organ.stem] ?: continue
            val at = cubicAt(p, organ.t)
            val ang = cubicAngle(p, organ.t)
            val g = Wind.gust(ph, at.x, flora.width)
            val rot = (ang - organ.restAngle) + Wind.flutter(organ.sway, ph, g)
            val screen = transform.toScreen(at)
            matrix.setTranslate(
                -(organ.pivot.x - organ.off.x) / flora.sample,
                -(organ.pivot.y - organ.off.y) / flora.sample,
            )
            matrix.postScale(spriteScale, spriteScale)
            matrix.postRotate(rot)
            matrix.postTranslate(screen.x, screen.y)
            drawContext.canvas.nativeCanvas.drawBitmap(bmp, matrix, paint)
        }

        // 蕾。開く途中は連続コマを差し替える。
        val gemma = flora.gemma
        if (gemma != null) {
            val p = bent[gemma.stem]
            if (p != null) {
                val at = cubicAt(p, gemma.t)
                val ang = cubicAngle(p, gemma.t)
                val g = Wind.gust(ph, at.x, flora.width)
                val rot = (ang - gemma.restAngle) + Wind.flutter(gemma.sway, ph, g)
                val idx = (bloom() * (gemma.frames.size - 1)).roundToInt()
                    .coerceIn(0, gemma.frames.size - 1)
                val frame = gemma.frames[idx]
                frame.bitmap?.let { bmp ->
                    val screen = transform.toScreen(at)
                    matrix.setTranslate(
                        -(gemma.pivot.x - frame.off.x) / flora.sample,
                        -(gemma.pivot.y - frame.off.y) / flora.sample,
                    )
                    matrix.postScale(spriteScale, spriteScale)
                    matrix.postRotate(rot)
                    matrix.postTranslate(screen.x, screen.y)
                    drawContext.canvas.nativeCanvas.drawBitmap(bmp, matrix, paint)
                }
            }
        }

        reach()?.let { drawReach(it, transform, bl, reachGrow(), reachBloom(), appsByKey) }

        if (showLabels) drawLabels(measurer, transform, flora, bindings, appsByKey)
        if (showHitAreas) {
            for (t in flora.tapTargets) {
                drawCircle(
                    color = Color(0x66A33B2E),
                    radius = t.radius * transform.scale,
                    center = transform.toScreen(t.at),
                    style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2f),
                )
            }
        }
    }
}

/** 伸びる蔓と、その先で開く花。中にアプリが現れる。 */
private fun DrawScope.drawReach(
    reach: Reach,
    t: SceneTransform,
    boil: Int,
    grow: Float,
    bloom: Float,
    appsByKey: Map<String, AppEntry>,
) {
    val w = 3.6f * t.scale
    pencilStroke(
        pts = reach.main.map { t.toScreen(it) },
        sid = reach.seed, boil = boil, color = GRAPHITE,
        widthAt = { s -> w * (1f - 0.45f * s) },
        tone = 0.95f, jitter = 1.0f, passes = 2,
        taperHead = 0.04f, taperTail = 0.2f, grain = 0.25f,
        progress = reach.mainGrow(grow),
    )
    val bg = reach.branchGrow(grow)
    for ((i, br) in reach.branches.withIndex()) {
        if (bg <= 0f) break
        pencilStroke(
            pts = br.map { t.toScreen(it) },
            sid = reach.seed + 31 * (i + 1), boil = boil, color = GRAPHITE,
            widthAt = { s -> w * 0.72f * (1f - 0.4f * s) },
            tone = 0.9f, jitter = 0.9f, passes = 2,
            taperHead = 0.04f, taperTail = 0.2f, grain = 0.25f,
            progress = bg,
        )
    }

    if (bloom <= 0.01f) return
    val size = 108f
    for (i in reach.tips.indices) {
        val (_, ang) = reach.tips[i]
        val centre = reach.flowerCentre(i, size)
        for ((j, petal) in bloomPetals(centre, ang, 5, bloom, size).withIndex()) {
            pencilOutline(
                poly = petal.map { t.toScreen(it) },
                sid = reach.seed + 101 * (i + 1) + j, boil = boil,
                color = GRAPHITE, width = 1.8f * t.scale, tone = 0.9f,
            )
        }
        // 花芯にアプリ
        val key = reach.appKeys.getOrNull(i)
        val app = key?.let { appsByKey[it] }
        val fade = smooth(((bloom - 0.45f) / 0.4f).coerceIn(0f, 1f))
        if (app != null && fade > 0.02f) {
            val r = size * 0.34f * t.scale * fade
            val c = t.toScreen(centre)
            drawImage(
                image = app.icon,
                dstOffset = androidx.compose.ui.unit.IntOffset(
                    (c.x - r).roundToInt(), (c.y - r).roundToInt()
                ),
                dstSize = androidx.compose.ui.unit.IntSize((r * 2).roundToInt(), (r * 2).roundToInt()),
                alpha = fade,
            )
        }
    }
}

private fun DrawScope.drawPaper(flora: Flora) {
    drawRect(flora.paper)
    drawRect(
        brush = Brush.radialGradient(
            colors = listOf(Color.Transparent, Color(0x14000000)),
            center = Offset(size.width / 2f, size.height / 2f),
            radius = maxOf(size.width, size.height) * 0.72f,
        ),
    )
}

private val labelStyle = TextStyle(
    fontFamily = FontFamily.Serif,
    fontSize = 10.sp,
    color = Color(0xCC3A362C),
)

private fun DrawScope.drawLabels(
    measurer: TextMeasurer,
    t: SceneTransform,
    flora: Flora,
    bindings: Map<String, List<String>>,
    appsByKey: Map<String, AppEntry>,
) {
    for ((organId, keys) in bindings) {
        val present = keys.mapNotNull { appsByKey[it] }
        if (present.isEmpty()) continue
        val target = flora.target(organId) ?: continue
        val text = if (present.size == 1) present[0].label
        else "${present[0].label} +${present.size - 1}"
        val layout = measurer.measure(AnnotatedString(text), labelStyle, maxLines = 1)
        val c = t.toScreen(target.at)
        val y = c.y + target.radius * t.scale + 4f
        drawText(layout, topLeft = Offset(c.x - layout.size.width / 2f, y))
    }
}
