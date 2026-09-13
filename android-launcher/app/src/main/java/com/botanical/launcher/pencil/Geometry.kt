package com.botanical.launcher.pencil

import androidx.compose.ui.geometry.Offset
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

/** 画面座標（y は下向き）。角度は度で、-90 が真上。 */
internal const val DEG = (PI / 180.0).toFloat()

fun polar(o: Offset, deg: Float, d: Float): Offset =
    Offset(o.x + cos(deg * DEG) * d, o.y + sin(deg * DEG) * d)

fun lerp(a: Float, b: Float, t: Float): Float = a + (b - a) * t

fun smooth(t: Float): Float {
    val x = t.coerceIn(0f, 1f)
    return x * x * (3f - 2f * x)
}

fun cubicAt(p: List<Offset>, t: Float): Offset {
    val u = 1f - t
    val a = u * u * u
    val b = 3f * u * u * t
    val c = 3f * u * t * t
    val d = t * t * t
    return Offset(
        a * p[0].x + b * p[1].x + c * p[2].x + d * p[3].x,
        a * p[0].y + b * p[1].y + c * p[2].y + d * p[3].y,
    )
}

fun cubicAngle(p: List<Offset>, t: Float): Float {
    val u = 1f - t
    val dx = 3f * u * u * (p[1].x - p[0].x) + 6f * u * t * (p[2].x - p[1].x) +
        3f * t * t * (p[3].x - p[2].x)
    val dy = 3f * u * u * (p[1].y - p[0].y) + 6f * u * t * (p[2].y - p[1].y) +
        3f * t * t * (p[3].y - p[2].y)
    return atan2(dy, dx) / DEG
}

fun quadPoints(p0: Offset, c: Offset, p1: Offset, steps: Int): List<Offset> =
    (0..steps).map { i ->
        val t = i / steps.toFloat()
        val u = 1f - t
        Offset(
            u * u * p0.x + 2f * u * t * c.x + t * t * p1.x,
            u * u * p0.y + 2f * u * t * c.y + t * t * p1.y,
        )
    }

/**
 * 伸びていく蔓。先へ行くほど巻く。
 *
 * 素材として焼いてある器官と違い、蔓は押した場所から任意の向きへ伸びるので
 * その場で組み立てて線を引く。
 */
fun tendril(
    start: Offset,
    heading: Float,
    length: Float,
    curl: Float,
    seed: Int,
    steps: Int = 42,
): List<Offset> {
    val pts = ArrayList<Offset>(steps + 1)
    pts.add(start)
    var h = heading
    val step = length / steps
    for (i in 0 until steps) {
        val t = (i + 1f) / steps
        h += curl * (0.5f + 1.8f * t * t) + (rand01(seed, i) - 0.5f) * 5f
        pts.add(polar(pts[pts.size - 1], h, step))
    }
    return pts
}

/** 折れ線上の位置と接線方向。 */
fun along(pts: List<Offset>, t: Float): Pair<Offset, Float> {
    val n = pts.size - 1
    val f = t.coerceIn(0f, 0.9999f) * n
    val i = f.toInt()
    val u = f - i
    val p = Offset(lerp(pts[i].x, pts[i + 1].x, u), lerp(pts[i].y, pts[i + 1].y, u))
    val j = (i + 1).coerceAtMost(n)
    val k = (i - 1).coerceAtLeast(0)
    return p to atan2(pts[j].y - pts[k].y, pts[j].x - pts[k].x) / DEG
}

/** 葉・花弁の輪郭。二次ベジエ 2 本で囲む。 */
fun bladeOutline(
    attach: Offset,
    dirDeg: Float,
    length: Float,
    width: Float,
    bend: Float = 0f,
    steps: Int = 14,
): List<Offset> {
    val tip = polar(attach, dirDeg + bend * 16f, length)
    val waist = polar(attach, dirDeg + bend * 8f, length * 0.36f)
    val left = polar(waist, dirDeg - 90f, width)
    val right = polar(waist, dirDeg + 90f, width)
    return quadPoints(attach, left, tip, steps) + quadPoints(tip, right, attach, steps).drop(1)
}

/** 蕾から花への連続変化。閉じているときは深く重なって蕾の形になる。 */
fun bloomPetals(
    centre: Offset,
    axisDeg: Float,
    petals: Int,
    openness: Float,
    size: Float,
): List<List<Offset>> {
    val o = smooth(openness)
    val hub = size * 0.30f * o
    return (0 until petals).map { i ->
        val spread = lerp(6.5f, 360f / petals, o)
        val a = axisDeg + (i - (petals - 1) / 2f) * spread
        val ln = size * lerp(1.02f, 1.30f, o)
        val wd = size * lerp(0.17f, 0.46f, o)
        bladeOutline(polar(centre, a, hub), a, ln, wd)
    }
}
