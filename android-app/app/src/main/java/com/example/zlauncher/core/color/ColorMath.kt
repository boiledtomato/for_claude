package com.example.zlauncher.core.color

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

/**
 * 配色をいじるための計算。**Android に依存しない**ので、そのまま単体テストにかけられる。
 *
 * 色は 0xAARRGGBB の Int で受け渡しする。Compose の `Color` を跨がせないのは、
 * 描画側（Compose）とウィジェット側（RemoteViews）の両方から同じ計算を使うため。
 *
 * 彩度と明度は **HSL** でいじる。HSV だと「明度を上げる」が白に飛ばず色が濁る方向に動き、
 * 面の色として使いものにならない。
 */
object ColorMath {

    /** 0xAARRGGBB から HSL へ。h は 0..360、s と l は 0..1 */
    fun toHsl(argb: Int): FloatArray {
        val r = ((argb shr 16) and 0xFF) / 255f
        val g = ((argb shr 8) and 0xFF) / 255f
        val b = (argb and 0xFF) / 255f

        val maxC = max(r, max(g, b))
        val minC = min(r, min(g, b))
        val delta = maxC - minC
        val l = (maxC + minC) / 2f

        if (delta == 0f) return floatArrayOf(0f, 0f, l)

        val s = if (l > 0.5f) delta / (2f - maxC - minC) else delta / (maxC + minC)
        val h = when (maxC) {
            r -> ((g - b) / delta + if (g < b) 6f else 0f)
            g -> (b - r) / delta + 2f
            else -> (r - g) / delta + 4f
        } * 60f
        return floatArrayOf(h, s, l)
    }

    /** HSL から 0xAARRGGBB へ。[alpha] は 0..255 */
    fun fromHsl(h: Float, s: Float, l: Float, alpha: Int): Int {
        val hue = ((h % 360f) + 360f) % 360f
        val sat = s.coerceIn(0f, 1f)
        val light = l.coerceIn(0f, 1f)

        if (sat == 0f) {
            val v = (light * 255f + 0.5f).toInt()
            return pack(alpha, v, v, v)
        }

        val q = if (light < 0.5f) light * (1f + sat) else light + sat - light * sat
        val p = 2f * light - q
        val r = hueToChannel(p, q, hue / 360f + 1f / 3f)
        val g = hueToChannel(p, q, hue / 360f)
        val b = hueToChannel(p, q, hue / 360f - 1f / 3f)
        return pack(alpha, (r * 255f + 0.5f).toInt(), (g * 255f + 0.5f).toInt(), (b * 255f + 0.5f).toInt())
    }

    /**
     * 彩度と明度を掛ける。1.0 で素通し。
     *
     * **色相は動かさない。** 動かすと、カテゴリーの識別色とグラフの塗りが対応しなくなる。
     */
    fun adjust(argb: Int, saturation: Float, lightness: Float): Int {
        if (saturation == 1f && lightness == 1f) return argb
        val hsl = toHsl(argb)
        return fromHsl(
            h = hsl[0],
            s = (hsl[1] * saturation).coerceIn(0f, 1f),
            l = (hsl[2] * lightness).coerceIn(0f, 1f),
            alpha = (argb ushr 24) and 0xFF,
        )
    }

    /** WCAG の相対輝度 */
    fun luminance(argb: Int): Float {
        fun channel(value: Int): Float {
            val c = value / 255f
            return if (c <= 0.03928f) c / 12.92f else ((c + 0.055f) / 1.055f).pow(2.4f)
        }
        val r = channel((argb shr 16) and 0xFF)
        val g = channel((argb shr 8) and 0xFF)
        val b = channel(argb and 0xFF)
        return 0.2126f * r + 0.7152f * g + 0.0722f * b
    }

    /** WCAG のコントラスト比。1.0（同じ色）から 21.0（黒と白）まで */
    fun contrast(foreground: Int, background: Int): Float {
        val a = luminance(foreground)
        val b = luminance(background)
        val light = max(a, b)
        val dark = min(a, b)
        return (light + 0.05f) / (dark + 0.05f)
    }

    /**
     * 文字色を面から**引き離して**、読める比まで戻す。
     *
     * 利用者が明度を動かすと、面と文字が同じ側へ寄って字が消える。色相と彩度はそのままに、
     * 明るい面なら暗い方へ、暗い面なら明るい方へ明度だけを動かして比を満たす。
     * どこまで動かしても届かない場合は、行けるところまで（黒または白）で返す。
     */
    fun ensureContrast(foreground: Int, background: Int, minRatio: Float): Int {
        if (contrast(foreground, background) >= minRatio) return foreground

        val hsl = toHsl(foreground)
        val alpha = (foreground ushr 24) and 0xFF
        // 面が明るければ文字は暗く。境目では、いま離れている向きを選ぶ
        val towardsDark = luminance(background) > 0.18f ||
            (abs(luminance(background) - 0.18f) < 0.001f && hsl[2] < 0.5f)

        var best = foreground
        var bestRatio = contrast(foreground, background)
        var step = 0
        while (step <= STEPS) {
            val delta = step / STEPS.toFloat()
            val lightness = if (towardsDark) hsl[2] - delta else hsl[2] + delta
            val candidate = fromHsl(hsl[0], hsl[1], lightness.coerceIn(0f, 1f), alpha)
            val ratio = contrast(candidate, background)
            if (ratio > bestRatio) {
                bestRatio = ratio
                best = candidate
            }
            if (ratio >= minRatio) return candidate
            step++
        }
        return best
    }

    private fun hueToChannel(p: Float, q: Float, tRaw: Float): Float {
        var t = tRaw
        if (t < 0f) t += 1f
        if (t > 1f) t -= 1f
        return when {
            t < 1f / 6f -> p + (q - p) * 6f * t
            t < 1f / 2f -> q
            t < 2f / 3f -> p + (q - p) * (2f / 3f - t) * 6f
            else -> p
        }
    }

    private fun pack(a: Int, r: Int, g: Int, b: Int): Int =
        (a.coerceIn(0, 255) shl 24) or
            (r.coerceIn(0, 255) shl 16) or
            (g.coerceIn(0, 255) shl 8) or
            b.coerceIn(0, 255)

    /** 明度を動かす刻み。1/100 ずつ試して、最初に比を満たしたところで止める */
    private const val STEPS = 100
}
