package com.pointille.launcher.paint

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sign
import kotlin.math.sin
import kotlin.random.Random

/**
 * Seurat, in code.
 *
 * Pigment is never mixed: pure colours are set side by side and the eye does
 * the mixing. Every dab takes a base colour from the reference and then departs
 * from it — hue vibration, a warm dab for light, a cool one for shade, and
 * sparsely a complementary one, which is what makes the surface shimmer.
 *
 * Two rules were learned the hard way in the prototype and both are load-bearing:
 *
 *  - **The local colour has to stay clearly in charge.** Let every dab wander
 *    far and the field turns to confetti and the subject sinks into it.
 *  - **A dab is a tâche, not a dot.** Perfect circles on an even lattice read as
 *    a printed halftone screen, not as paint. Each one is a wobbled ellipse whose
 *    long axis leans on the panel's prevailing lay.
 *
 * The subject is a separate population of dabs so it keeps its value and stays
 * readable — at 78px that is the whole difference between an icon and a smudge.
 */
object Pointillist {

    /** Working (and cached) resolution. */
    const val RES = 192

    private const val SPACING = 5.0f
    private const val DAB_N = 11
    private const val TWO_PI = (Math.PI * 2).toFloat()

    private const val SUN_H = 45f      // sunlight leans the hue this way
    private const val SHADE_H = 238f   // shade leans this way — never toward black

    private val dabCos = FloatArray(DAB_N + 1) { cos(it.toFloat() / DAB_N * TWO_PI) }
    private val dabSin = FloatArray(DAB_N + 1) { sin(it.toFloat() / DAB_N * TWO_PI) }

    // ------------------------------------------------------------------ paint

    /**
     * @param field   the ground, opaque, [RES] square
     * @param subject the symbol over it, with alpha; null paints the ground alone
     */
    fun render(field: Bitmap, subject: Bitmap?, seed: Int): Bitmap {
        val rnd = Random(seed * 131 + 7)
        val lay = rnd.nextFloat() * Math.PI.toFloat()   // this panel's prevailing brush direction

        val fd = IntArray(RES * RES)
        field.getPixels(fd, 0, RES, 0, 0, RES, RES)

        val sd = subject?.let {
            val raw = IntArray(RES * RES)
            it.getPixels(raw, 0, RES, 0, 0, RES, RES)
            warp(raw, 1.4f, seed * 1.7f)                // drawn by hand, not plotted
        }

        val panel = coverage(seed, 5f)
        val inner = coverage(seed, 13f)

        val content = Bitmap.createBitmap(RES, RES, Bitmap.Config.ARGB_8888)
        val c = Canvas(content)

        // the priming, left showing between the dabs
        var ar = 0L; var ag = 0L; var ab = 0L
        for (p in fd) { ar += Color.red(p); ag += Color.green(p); ab += Color.blue(p) }
        val n = fd.size.toLong()
        c.drawColor(shade(Color.rgb((ar / n).toInt(), (ag / n).toInt(), (ab / n).toInt()), -0.08f))

        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val path = Path()
        val hsv = FloatArray(3)

        // two interleaved lattices, both jittered — an even grid reads as a screen
        val lattices = arrayOf(floatArrayOf(0f, 0f, 1f), floatArrayOf(SPACING / 2, SPACING / 2, 0.64f))
        for (l in lattices) {
            var gy = -SPACING
            while (gy < RES + SPACING) {
                var gx = -SPACING
                while (gx < RES + SPACING) {
                    if (rnd.nextFloat() > l[2]) { gx += SPACING; continue }
                    val x = gx + l[0] + (rnd.nextFloat() - 0.5f) * SPACING * 0.95f
                    val y = gy + l[1] + (rnd.nextFloat() - 0.5f) * SPACING * 0.95f
                    val xi = x.toInt().coerceIn(0, RES - 1)
                    val yi = y.toInt().coerceIn(0, RES - 1)
                    val mi = yi * RES + xi
                    if (panel[mi].toInt() == 0) { gx += SPACING; continue }

                    val onBorder = inner[mi].toInt() == 0
                    val isSubject = !onBorder && sd != null && Color.alpha(sd[mi]) > 90
                    val base = if (isSubject) sd!![mi] else fd[mi]

                    paint.color = dabColor(base, rnd, onBorder, isSubject, hsv)
                    val ang = (if (isSubject) lay + 1.0f else lay) + (rnd.nextFloat() - 0.5f) * 2.1f
                    dab(c, path, paint, x, y, 2.4f + rnd.nextFloat() * 1.3f, ang, rnd)

                    gx += SPACING
                }
                gy += SPACING
            }
        }

        weave(c, seed)
        val out = cutCanvas(content, seed)
        content.recycle()
        return out
    }

    // ------------------------------------------------------------------ colour

    /** One dab of unmixed pigment, chosen so its neighbourhood averages to base. */
    private fun dabColor(base: Int, R: Random, onBorder: Boolean, isSubject: Boolean, hsv: FloatArray): Int {
        Color.colorToHSV(base, hsv)
        var h = hsv[0]; var s = hsv[1]; var v = hsv[2]

        if (onBorder) {
            // the dotted border Seurat painted onto La Grande Jatte itself
            hsv[0] = (h + 180f) % 360f
            hsv[1] = min(1f, max(s, 0.28f) + R.nextFloat() * 0.16f)
            hsv[2] = min(1f, v * (0.74f + R.nextFloat() * 0.18f))
            return Color.HSVToColor(hsv)
        }
        if (isSubject) {
            // the symbol reads by value, so it keeps its lightness and only takes a tint
            hsv[0] = mixHue(h, if (R.nextFloat() < 0.5f) SUN_H else SHADE_H, R.nextFloat() * 0.18f)
            hsv[1] = min(1f, max(s, 0.09f) + R.nextFloat() * 0.15f)
            hsv[2] = min(1f, v * (0.96f + R.nextFloat() * 0.10f))
            return Color.HSVToColor(hsv)
        }

        val m = R.nextFloat()
        when {
            m < 0.62f -> {                                   // the local colour, vibrating
                h = (h + (R.nextFloat() - 0.5f) * 14f + 360f) % 360f
                s = min(1f, s * (1.02f + R.nextFloat() * 0.24f))
                v = min(1f, v * (0.96f + R.nextFloat() * 0.11f))
            }
            m < 0.80f -> {                                   // sunlight
                h = mixHue(h, SUN_H, 0.18f + R.nextFloat() * 0.16f)
                s = min(1f, max(s, 0.18f) + R.nextFloat() * 0.14f)
                v = min(1f, v * 1.07f + 0.05f)
            }
            m < 0.94f -> {                                   // shade — cool, never black
                h = mixHue(h, SHADE_H, 0.22f + R.nextFloat() * 0.18f)
                s = min(1f, max(s, 0.22f) + R.nextFloat() * 0.14f)
                v *= 0.88f + R.nextFloat() * 0.07f
            }
            else -> {                                        // the complement, sparse
                h = (h + 180f) % 360f
                s = min(1f, max(s, 0.26f) + R.nextFloat() * 0.14f)
                v = min(1f, v * (0.97f + R.nextFloat() * 0.12f))
            }
        }
        hsv[0] = h; hsv[1] = s; hsv[2] = v
        return Color.HSVToColor(hsv)
    }

    private fun mixHue(a: Float, b: Float, t: Float): Float {
        val d = ((b - a + 540f) % 360f) - 180f
        return (a + d * t + 360f) % 360f
    }

    fun shade(argb: Int, k: Float): Int {
        fun f(v: Int) = (if (k >= 0) v + (255 - v) * k else v * (1 + k)).toInt().coerceIn(0, 255)
        return Color.rgb(f(Color.red(argb)), f(Color.green(argb)), f(Color.blue(argb)))
    }

    // ------------------------------------------------------------------- marks

    /**
     * A tâche: a short dab off a loaded brush, elongated and turned, never a
     * clean circle. The outline wobbles on two sines so no two are the same
     * silhouette.
     */
    private fun dab(c: Canvas, p: Path, paint: Paint, x: Float, y: Float, r: Float, ang: Float, R: Random) {
        val ry = r * (0.58f + R.nextFloat() * 0.34f)
        val rx = ry * (1.0f + R.nextFloat() * 1.45f)     // 1.0-2.45 : round-ish through to a slash
        val ca = cos(ang); val sa = sin(ang)
        val w1 = 0.10f + R.nextFloat() * 0.11f
        val w2 = 0.05f + R.nextFloat() * 0.09f
        val p1 = R.nextFloat() * TWO_PI
        val p2 = R.nextFloat() * TWO_PI

        p.reset()
        for (i in 0..DAB_N) {
            val t = i.toFloat() / DAB_N * TWO_PI
            val k = 1f + w1 * sin(2 * t + p1) + w2 * sin(3 * t + p2)
            val ex = dabCos[i] * rx * k
            val ey = dabSin[i] * ry * k
            val px = x + ex * ca - ey * sa
            val py = y + ex * sa + ey * ca
            if (i == 0) p.moveTo(px, py) else p.lineTo(px, py)
        }
        p.close()
        c.drawPath(p, paint)
    }

    /** Smooth noise nudges the pixels, so no edge in the subject is machine-straight. */
    private fun warp(src: IntArray, amp: Float, seed: Float): IntArray {
        val out = IntArray(src.size)
        for (y in 0 until RES) for (x in 0 until RES) {
            val dx = (amp * (sin(y * 0.13f + seed) + 0.6f * sin(x * 0.061f + y * 0.037f + seed * 1.7f))).toInt()
            val dy = (amp * (sin(x * 0.115f + seed * 2.3f) + 0.6f * sin(y * 0.055f - x * 0.041f + seed))).toInt()
            val sx = (x + dx).coerceIn(0, RES - 1)
            val sy = (y + dy).coerceIn(0, RES - 1)
            out[y * RES + x] = src[sy * RES + sx]
        }
        return out
    }

    // ------------------------------------------------------------------ canvas

    /** The stretched canvas outline: near-square, softly cornered, hand-cut. */
    private fun canvasPath(seed: Int, inset: Float): Path {
        val r = RES / 2f - inset
        val mid = RES / 2f
        val s = seed * 1.37f
        val p = Path()
        for (i in 0..240) {
            val t = i / 240f * TWO_PI
            val ct = cos(t); val st = sin(t)
            val j = 1f + 0.013f * sin(3 * t + s) + 0.009f * sin(7 * t + s * 2.1f)
            val x = mid + sign(ct) * abs(ct).pow(0.25f) * r * j      // superellipse, n = 8
            val y = mid + sign(st) * abs(st).pow(0.25f) * r * j
            if (i == 0) p.moveTo(x, y) else p.lineTo(x, y)
        }
        p.close()
        return p
    }

    /** Which pixels the canvas covers, so dabs can be placed against it. */
    private fun coverage(seed: Int, inset: Float): ByteArray {
        val bmp = Bitmap.createBitmap(RES, RES, Bitmap.Config.ARGB_8888)
        Canvas(bmp).drawPath(canvasPath(seed, inset), Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.BLACK })
        val px = IntArray(RES * RES)
        bmp.getPixels(px, 0, RES, 0, 0, RES, RES)
        bmp.recycle()
        return ByteArray(px.size) { if (Color.alpha(px[it]) > 128) 1 else 0 }
    }

    /** Woven linen laid over the paint — the dabs sit down in its tooth. */
    private fun weave(c: Canvas, seed: Int) {
        val p = 4.2f
        val paint = Paint()
        var x = 0f
        while (x < RES) {
            val w = x + sin(x * 0.9f + seed) * 0.6f
            paint.color = Color.argb(9, 255, 250, 238); c.drawRect(w, 0f, w + p * 0.44f, RES.toFloat(), paint)
            paint.color = Color.argb(9, 58, 46, 34); c.drawRect(w + p * 0.44f, 0f, w + p, RES.toFloat(), paint)
            x += p
        }
        var y = 0f
        while (y < RES) {
            val h = y + cos(y * 0.8f + seed) * 0.6f
            paint.color = Color.argb(8, 255, 250, 238); c.drawRect(0f, h, RES.toFloat(), h + p * 0.44f, paint)
            paint.color = Color.argb(8, 58, 46, 34); c.drawRect(0f, h + p * 0.44f, RES.toFloat(), h + p, paint)
            y += p
        }
    }

    /**
     * Cut the canvas out and light its stretched edge.
     *
     * Mask first, content second with SRC_IN. Drawing the mask *over* the paint
     * with DST_IN only blends inside the path's own coverage, so everything
     * outside the canvas would survive — the panel would stay a full square.
     */
    private fun cutCanvas(content: Bitmap, seed: Int): Bitmap {
        val outline = canvasPath(seed, 5f)
        val out = Bitmap.createBitmap(RES, RES, Bitmap.Config.ARGB_8888)
        val c = Canvas(out)

        c.drawPath(outline, Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.BLACK })
        c.drawBitmap(content, 0f, 0f, Paint(Paint.ANTI_ALIAS_FLAG).apply {
            xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        })

        val rim = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeWidth = 3.4f
            xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)
        }
        c.save(); c.translate(-1.4f, -1.6f)
        rim.color = Color.argb(102, 255, 251, 240); c.drawPath(outline, rim); c.restore()
        c.save(); c.translate(1.4f, 1.8f)
        rim.color = Color.argb(87, 44, 34, 24); c.drawPath(outline, rim); c.restore()
        return out
    }

    // ------------------------------------------------------------------- brush

    /**
     * Colours to load the touch-stroke brush with. Near-neutrals are dropped
     * unless the panel has nothing else — they make the launch wipe read as
     * grey stripes rather than paint.
     */
    fun brushColors(bmp: Bitmap): List<Int> {
        val all = ArrayList<Int>(44)
        for (i in 1..44) {
            val gx = (i * 0.6180339887f) % 1f
            val gy = (i * 0.7548776662f) % 1f
            val x = (22 + gx * (bmp.width - 44)).toInt().coerceIn(0, bmp.width - 1)
            val y = (22 + gy * (bmp.height - 44)).toInt().coerceIn(0, bmp.height - 1)
            val p = bmp.getPixel(x, y)
            if (Color.alpha(p) > 200) all += p
        }
        if (all.isEmpty()) return listOf(Color.rgb(240, 235, 225))
        val rich = all.filter {
            val r = Color.red(it); val g = Color.green(it); val b = Color.blue(it)
            max(r, max(g, b)) - min(r, min(g, b)) > 18
        }
        return if (rich.size >= 3) rich else all
    }
}
