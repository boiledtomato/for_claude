package com.botanical.launcher.pencil

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.abs
import kotlin.math.hypot
import kotlin.math.sin

/**
 * 鉛筆の線。
 *
 * 手描きに見せるのに要るのは 3 つ。
 *   筆圧   — 両端が細く薄く、中ほどが太く濃い
 *   ぶれ   — 低周波のゆらぎ。白色ノイズだと震えになって汚い
 *   紙の目 — 濃度が細かく途切れる
 * さらに 1 本を少しずらして 2 回引くと「探り描き」になる。
 *
 * ゆらぎは (線の id, コマ番号) から決める。乱数を引くのではなく決定的に
 * 作ることで、同じコマは何度描いても同じ形になる。コマ番号を変えると
 * 線が描き直され、手描きアニメのように生きて見える。
 */

internal fun fnv(vararg vals: Int): Int {
    var h = -2128831035
    for (v in vals) {
        h = h xor v
        h *= 16777619
    }
    return h
}

internal fun rand01(seed: Int, i: Int): Float =
    ((fnv(seed, i) ushr 8) and 0xFFFF) / 65535f

private fun pressure(t: Float, head: Float, tail: Float): Float {
    val a = if (head > 0f) (t / head).coerceAtMost(1f) else 1f
    val b = if (tail > 0f) ((1f - t) / tail).coerceAtMost(1f) else 1f
    val env = Math.pow((a * b).toDouble(), 0.55).toFloat()
    return (env * (0.85f + 0.15f * sin(t * 9f))).coerceAtLeast(0.05f)
}

/** 折れ線を等間隔に打ち直す。筆圧を距離で扱えるようにするため。 */
private fun resample(pts: List<Offset>, step: Float): List<Offset> {
    if (pts.size < 2) return pts
    val out = ArrayList<Offset>(pts.size * 2)
    out.add(pts[0])
    var carry = 0f
    for (i in 0 until pts.size - 1) {
        val a = pts[i]
        val b = pts[i + 1]
        val seg = hypot(b.x - a.x, b.y - a.y)
        if (seg < 1e-4f) continue
        var t = carry
        while (t < seg) {
            val k = t / seg
            out.add(Offset(a.x + (b.x - a.x) * k, a.y + (b.y - a.y) * k))
            t += step
        }
        carry = t - seg
    }
    out.add(pts[pts.size - 1])
    return out
}

/** 低周波のゆらぎ。正弦を数本足すだけで手のぶれらしくなる。 */
private fun wobbleAt(seed: Int, i: Int, n: Int, amp: Float): Float {
    var sum = 0f
    for (o in 0 until 2) {
        val f = 0.6f + 1.4f * rand01(seed, 10 + o) * (o + 1)
        val p = rand01(seed, 20 + o) * 6.2832f
        sum += amp / (o + 1) * sin(f * 6.2832f * i / (n - 1).coerceAtLeast(1) + p)
    }
    return sum
}

/**
 * @param sid 線の識別子。[boil] と合わせてゆらぎが決まる
 * @param progress 0..1 で途中まで描く。蔓が伸びる表現に使う
 */
fun DrawScope.pencilStroke(
    pts: List<Offset>,
    sid: Int,
    boil: Int,
    color: Color,
    widthAt: (Float) -> Float,
    tone: Float = 0.8f,
    jitter: Float = 1.0f,
    passes: Int = 2,
    taperHead: Float = 0.3f,
    taperTail: Float = 0.3f,
    grain: Float = 0.3f,
    progress: Float = 1f,
    step: Float = 3.2f,
) {
    if (pts.size < 2 || tone <= 0.01f) return
    var base = resample(pts, step)
    if (progress < 1f) {
        val keep = (base.size * progress.coerceIn(0f, 1f)).toInt().coerceAtLeast(2)
        base = base.subList(0, keep)
    }
    val n = base.size
    if (n < 2) return

    for (pass in 0 until passes) {
        val seed = fnv(sid, boil, pass)
        val k = if (pass == 0) 1f else 0.35f + 0.25f * rand01(seed, 1)
        val amp = jitter * (if (pass == 0) 1f else 1.8f)
        var prev: Offset? = null
        for (i in 0 until n) {
            val p = base[i]
            val j = (i + 1).coerceAtMost(n - 1)
            val dx = base[j].x - p.x
            val dy = base[j].y - p.y
            val len = hypot(dx, dy).takeIf { it > 1e-4f } ?: 1f
            val w = wobbleAt(seed, i, n, amp)
            val cur = Offset(p.x - dy / len * w, p.y + dx / len * w)
            if (prev != null) {
                val t = (i - 1) / (n - 1f)
                val pr = pressure(t, taperHead, taperTail)
                if (rand01(seed, 100 + i) >= grain * (1f - pr) * 0.9f) {
                    val a = (tone * pr * k * (0.6f + 0.4f * rand01(seed, 200 + i)))
                        .coerceIn(0f, 1f)
                    if (a > 0.015f) {
                        drawLine(
                            color = color.copy(alpha = a),
                            start = prev,
                            end = cur,
                            strokeWidth = (widthAt(t) * (0.55f + 0.45f * pr))
                                .coerceAtLeast(0.8f),
                            cap = StrokeCap.Round,
                        )
                    }
                }
            }
            prev = cur
        }
    }
}

/** 閉じた輪郭を引く。 */
fun DrawScope.pencilOutline(
    poly: List<Offset>,
    sid: Int,
    boil: Int,
    color: Color,
    width: Float = 1.6f,
    tone: Float = 0.8f,
    jitter: Float = 0.7f,
) = pencilStroke(
    pts = poly + poly.first(),
    sid = sid, boil = boil, color = color,
    widthAt = { width }, tone = tone, jitter = jitter,
    passes = 2, taperHead = 0.12f, taperTail = 0.12f, grain = 0.22f,
)
