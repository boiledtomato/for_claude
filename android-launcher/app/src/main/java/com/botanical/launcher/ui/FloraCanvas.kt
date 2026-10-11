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
import androidx.compose.ui.graphics.Path
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
import com.botanical.launcher.flora.Palette
import com.botanical.launcher.pencil.along
import com.botanical.launcher.pencil.cubicAngle
import com.botanical.launcher.pencil.cubicAt
import com.botanical.launcher.pencil.pencilStroke
import com.botanical.launcher.pencil.offsetPoly
import com.botanical.launcher.pencil.polar
import com.botanical.launcher.pencil.smooth
import kotlin.math.roundToInt

private val GRAPHITE = Palette.Ink

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

        // 根。土の中なので動かない。焼いた 1 枚をそのまま置く。
        flora.roots?.bitmap?.let { bmp ->
            val screen = transform.toScreen(flora.roots.off)
            matrix.setScale(spriteScale, spriteScale)
            matrix.postTranslate(screen.x, screen.y)
            drawContext.canvas.nativeCanvas.drawBitmap(bmp, matrix, paint)
        }

        // 茎。形が毎フレーム変わるので線を引く。
        for (stem in flora.stems) {
            val p = bent[stem.id] ?: continue
            val pts = (0..36).map { transform.toScreen(cubicAt(p, it / 36f)) }
            drawStem(pts, stem.w0 * transform.scale, stem.w1 * transform.scale,
                stem.tone, stem.id.hashCode(), bl, flora.paper, transform.scale)
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

        reach()?.let {
            drawReach(it, transform, bl, reachGrow(), reachBloom(), flora, appsByKey,
                matrix, paint)
        }

        drawCaption(measurer, transform, flora)
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

/**
 * 茎と蔓。輪郭 2 本のあいだを緑で埋めた筒として描く。
 *
 * 太い 1 本の線で引くと黒い棒になり、それだけで植物画に見えなくなる。
 * 左上からの光なので、影になる側だけ濃く落とすと丸みが出る。
 */
private fun DrawScope.drawStem(
    pts: List<Offset>, w0: Float, w1: Float, tone: Float,
    sid: Int, boil: Int, paper: Color, scale: Float,
) {
    if (pts.size < 2) return
    val half = { t: Float -> (w0 - (w0 - w1) * t) * 0.5f }
    val left = offsetPoly(pts, 90f, half)
    val right = offsetPoly(pts, -90f, half)

    val path = Path()
    path.moveTo(left[0].x, left[0].y)
    for (i in 1 until left.size) path.lineTo(left[i].x, left[i].y)
    for (i in right.indices.reversed()) path.lineTo(right[i].x, right[i].y)
    path.close()
    drawPath(path, Palette.GreenLight)
    drawPath(
        path,
        brush = Brush.linearGradient(
            colors = listOf(Palette.Stem.copy(alpha = 0.35f), Palette.StemDeep),
            start = Offset(0f, 0f),
            end = Offset(size.width, size.height),
        ),
        alpha = 0.72f,
    )

    pencilStroke(
        pts = right, sid = sid, boil = boil, color = Palette.GreenShade,
        widthAt = { 1.5f * scale }, tone = 1.02f * tone, jitter = 0.6f, passes = 1,
        taperHead = 0.03f, taperTail = 0.12f, grain = 0.18f,
    )
    pencilStroke(
        pts = left, sid = sid + 1, boil = boil, color = Palette.GreenDeep,
        widthAt = { 1.1f * scale }, tone = 0.6f * tone, jitter = 0.6f, passes = 1,
        taperHead = 0.03f, taperTail = 0.12f, grain = 0.18f,
    )
}

/**
 * 伸びる蔦と、その先でほどける蕾。中にアプリが現れる。
 *
 * 蔓そのものは形が毎フレーム変わるので線を引く。葉と蕾は焼いた素材を、
 * 蔓の上の位置と接線に合わせて置く。葉は蔓が通り過ぎてから少し遅れて
 * 開く。蔓と同時に開くと、生えたのではなく貼りついたように見える。
 */
private fun DrawScope.drawReach(
    reach: Reach,
    t: SceneTransform,
    boil: Int,
    grow: Float,
    bloom: Float,
    flora: Flora,
    appsByKey: Map<String, AppEntry>,
    matrix: Matrix,
    paint: Paint,
) {
    // 蔓の太さは版面の幅に対して決める。固定値にすると、版面の解像度を
    // 上げたときだけ蔓が細くなる。
    val w = flora.width * 0.0064f * t.scale
    val mg = reach.mainGrow(grow)
    val bg = reach.branchGrow(grow)

    fun vinePath(pts: List<Offset>, progress: Float): List<Offset> {
        if (progress >= 0.999f) return pts.map { t.toScreen(it) }
        val n = ((pts.size - 1) * progress).toInt().coerceAtLeast(1)
        return pts.take(n + 1).map { t.toScreen(it) }
    }

    // 主軸
    if (mg > 0.01f) {
        drawStem(vinePath(reach.main, mg), w, w * 0.5f, 0.95f,
            reach.seed, boil, flora.paper, t.scale)
    }
    for ((i, br) in reach.branches.withIndex()) {
        if (bg <= 0.01f) break
        drawStem(vinePath(br, bg), w * 0.78f, w * 0.42f, 0.9f,
            reach.seed + 31 * (i + 1), boil, flora.paper, t.scale)
    }

    val vine = flora.vine ?: return

    // 葉。蔓の先端が自分を追い越してから開きはじめる。
    for (lv in reach.leaves) {
        val path = if (lv.vine < 0) reach.main else reach.branches.getOrNull(lv.vine) ?: continue
        val prog = if (lv.vine < 0) mg else bg
        val open = ((prog - lv.t) / 0.26f).coerceIn(0f, 1f)
        if (open <= 0.01f) continue
        val sp = vine.leaves.getOrNull(lv.sprite) ?: continue
        val bmp = sp.bitmap ?: continue
        val (at, ang) = along(path, lv.t)
        // 焼いた葉は -90 度（上）を向いている。蔓の接線から左右へ開く。
        val rot = ang + 90f + lv.side * 62f
        val s = t.scale * flora.sample * lv.scale * smooth(open)
        matrix.setTranslate(sp.off.x / flora.sample, sp.off.y / flora.sample)
        matrix.postScale(s, s)
        matrix.postRotate(rot)
        matrix.postTranslate(t.toScreen(at).x, t.toScreen(at).y)
        drawContext.canvas.nativeCanvas.drawBitmap(bmp, matrix, paint)
    }

    // 先の蕾。蔓が伸びきる前から見えていて、そのあとほどける。
    for (i in reach.tips.indices) {
        val prog = reach.growOf(if (reach.branches.isEmpty()) -1 else i, grow)
        if (prog < 0.55f) continue
        val (tip, ang) = reach.tips[i]
        // 蔓が伸びきるまでは蕾のまま。swell で少しふくらませる。
        val swell = ((prog - 0.55f) / 0.45f).coerceIn(0f, 1f)
        val idx = (bloom * (vine.bloom.size - 1)).roundToInt()
            .coerceIn(0, vine.bloom.size - 1)
        val fr = vine.bloom[idx]
        val bmp = fr.bitmap ?: continue
        val size = vine.size * 1.22f
        val centre = reach.bloomCentre(i, size, swell)
        val s = t.scale * flora.sample * (size / vine.size) *
            (0.62f + 0.38f * smooth(swell))
        matrix.setTranslate(fr.off.x / flora.sample, fr.off.y / flora.sample)
        matrix.postScale(s, s)
        matrix.postRotate(ang + 90f)
        matrix.postTranslate(t.toScreen(centre).x, t.toScreen(centre).y)
        drawContext.canvas.nativeCanvas.drawBitmap(bmp, matrix, paint)

        // 花芯にアプリ。咲ききる手前から現れる。
        val key = reach.appKeys.getOrNull(i)
        val app = key?.let { appsByKey[it] }
        val fade = smooth(((bloom - 0.62f) / 0.38f).coerceIn(0f, 1f))
        if (app != null && fade > 0.02f) {
            val r = size * 0.27f * t.scale * fade
            val c = t.toScreen(centre)
            drawImage(
                image = app.icon,
                dstOffset = androidx.compose.ui.unit.IntOffset(
                    (c.x - r).roundToInt(), (c.y - r).roundToInt()
                ),
                dstSize = androidx.compose.ui.unit.IntSize(
                    (r * 2).roundToInt(), (r * 2).roundToInt()
                ),
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

private val captionStyle = TextStyle(
    fontFamily = FontFamily.Serif,
    fontSize = 19.sp,
    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
    color = Color(0xB33A362C),
)

private val plateNoStyle = TextStyle(
    fontFamily = FontFamily.Serif,
    fontSize = 11.sp,
    letterSpacing = 3.sp,
    color = Color(0x803A362C),
)

/** 図版名。参照した図版はどれも余白の下に学名が入る。ここが無いと標本画に見えない。 */
private fun DrawScope.drawCaption(measurer: TextMeasurer, t: SceneTransform, flora: Flora) {
    if (flora.caption.isEmpty()) return
    var y = t.toScreen(Offset(0f, flora.height * 0.926f)).y
    for ((text, style) in listOf(flora.caption to captionStyle, flora.plateNo to plateNoStyle)) {
        if (text.isEmpty()) continue
        val layout = measurer.measure(AnnotatedString(text), style, maxLines = 1)
        drawText(layout, topLeft = Offset(size.width / 2f - layout.size.width / 2f, y))
        y += layout.size.height * 1.15f
    }
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
