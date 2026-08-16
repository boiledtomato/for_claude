package com.impasto.launcher.paint

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.AdaptiveIconDrawable
import android.graphics.drawable.Drawable
import java.io.File
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sign
import kotlin.math.sin

/**
 * Turns an app's real launcher icon into an oil painting.
 *
 * The pipeline is the same four steps in both the prototype and here:
 *
 *   1. warp      — a smooth noise field nudges pixels around, so no edge in the
 *                  result is perfectly straight
 *   2. kuwahara  — an edge-preserving filter that collapses each neighbourhood
 *                  onto its flattest quadrant, which reads as brushed facets
 *   3. impasto   — the luminance gradient is lit from the upper left, so the
 *                  paint appears to stand off the canvas
 *   4. bristles + edge — visible strokes laid over the top, then the panel is
 *                  cut out along a hand-made superellipse instead of a rounded rect
 *
 * This runs once per app and the result is cached on disk, so it is deliberately
 * a plain CPU implementation — there is nothing to gain from a GPU shader for a
 * bitmap that is computed a single time and then read back from a PNG.
 */
object OilPainter {

    /** Working (and cached) resolution. Displayed well under this on any phone. */
    const val RES = 192

    private const val KUWAHARA_R = 3
    private const val WARP_AMP = 2.8f

    /** Panel outline: 4 = squircle, 2 = circle. */
    const val SHAPE_SQUIRCLE = 4f
    const val SHAPE_CIRCLE = 2f

    /**
     * @param key stable cache key — use "packageName@versionCode" so an app
     *            update repaints its icon and nothing else does.
     */
    fun paint(
        ctx: Context,
        key: String,
        icon: Drawable,
        seed: Int,
        shape: Float = SHAPE_SQUIRCLE,
    ): Bitmap {
        val cache = cacheFile(ctx, key)
        if (cache.exists()) {
            BitmapFactory.decodeFile(cache.path)?.let { return it }
        }
        val bmp = render(icon, seed, shape)
        runCatching {
            cache.parentFile?.mkdirs()
            cache.outputStream().use { bmp.compress(Bitmap.CompressFormat.PNG, 100, it) }
        }
        return bmp
    }

    fun clearCache(ctx: Context) {
        cacheDir(ctx).listFiles()?.forEach { it.delete() }
    }

    private fun cacheDir(ctx: Context) = File(ctx.filesDir, "oil")
    private fun cacheFile(ctx: Context, key: String) =
        File(cacheDir(ctx), key.replace(Regex("[^A-Za-z0-9._-]"), "_") + ".png")

    // ---------------------------------------------------------------- render

    private fun render(icon: Drawable, seed: Int, shape: Float): Bitmap {
        var px = rasterize(icon)
        px = warp(px, seed)
        px = kuwahara(px, KUWAHARA_R)
        px = impasto(px)

        val content = Bitmap.createBitmap(RES, RES, Bitmap.Config.ARGB_8888)
        content.setPixels(px, 0, RES, 0, 0, RES, RES)
        bristles(Canvas(content), px, seed)

        val out = cutPanel(content, seed, shape)
        content.recycle()
        return out
    }

    /**
     * Draw the icon onto an opaque ground.
     *
     * Two things matter here. Adaptive icons are authored on a 108-unit canvas
     * of which only the middle 72 are guaranteed visible, so the layers are
     * drawn oversized to put that safe zone on our square. And the ground is
     * filled with the icon's own average colour first — a transparent
     * background would bleed black through the filter, and a white one would
     * wash the panel out.
     */
    private fun rasterize(icon: Drawable): IntArray {
        val raw = Bitmap.createBitmap(RES, RES, Bitmap.Config.ARGB_8888)
        val rc = Canvas(raw)
        when (icon) {
            is AdaptiveIconDrawable -> {
                val over = (RES * 0.25f).toInt()      // 108/72 scale, centred
                val b = android.graphics.Rect(-over, -over, RES + over, RES + over)
                icon.background?.let { it.bounds = b; it.draw(rc) }
                icon.foreground?.let { it.bounds = b; it.draw(rc) }
            }
            else -> {
                icon.setBounds(0, 0, RES, RES)
                icon.draw(rc)
            }
        }

        val src = IntArray(RES * RES)
        raw.getPixels(src, 0, RES, 0, 0, RES, RES)
        raw.recycle()

        var r = 0L; var g = 0L; var b = 0L; var n = 0L
        for (p in src) {
            if ((p ushr 24) < 128) continue
            r += (p shr 16) and 0xFF; g += (p shr 8) and 0xFF; b += p and 0xFF; n++
        }
        val ground = if (n == 0L) Color.rgb(120, 120, 120)
                     else Color.rgb((r / n).toInt(), (g / n).toInt(), (b / n).toInt())

        // composite over the ground so every pixel is opaque from here on
        val outBmp = Bitmap.createBitmap(RES, RES, Bitmap.Config.ARGB_8888)
        val oc = Canvas(outBmp)
        oc.drawColor(ground)
        val tmp = Bitmap.createBitmap(RES, RES, Bitmap.Config.ARGB_8888)
        tmp.setPixels(src, 0, RES, 0, 0, RES, RES)
        oc.drawBitmap(tmp, 0f, 0f, null)
        tmp.recycle()

        val out = IntArray(RES * RES)
        outBmp.getPixels(out, 0, RES, 0, 0, RES, RES)
        outBmp.recycle()
        return out
    }

    private fun warp(src: IntArray, seed: Int): IntArray {
        val s = seed * 0.913f
        val out = IntArray(src.size)
        for (y in 0 until RES) for (x in 0 until RES) {
            val dx = (WARP_AMP * (sin(y * 0.13f + s) + 0.6f * sin(x * 0.061f + y * 0.037f + s * 1.7f))).toInt()
            val dy = (WARP_AMP * (sin(x * 0.115f + s * 2.3f) + 0.6f * sin(y * 0.055f - x * 0.041f + s))).toInt()
            val sx = (x + dx).coerceIn(0, RES - 1)
            val sy = (y + dy).coerceIn(0, RES - 1)
            out[y * RES + x] = src[sy * RES + sx]
        }
        return out
    }

    /**
     * Kuwahara via summed-area tables: each of the four quadrant means and
     * variances is four table lookups, so cost is independent of the radius.
     */
    private fun kuwahara(src: IntArray, r: Int): IntArray {
        val w = RES; val h = RES; val stride = w + 1
        val n = stride * (h + 1)
        val sR = LongArray(n); val sG = LongArray(n); val sB = LongArray(n)
        val sL = LongArray(n); val sQ = LongArray(n)

        for (y in 0 until h) for (x in 0 until w) {
            val p = src[y * w + x]
            val red = (p shr 16) and 0xFF
            val grn = (p shr 8) and 0xFF
            val blu = p and 0xFF
            val lum = (299 * red + 587 * grn + 114 * blu) / 1000
            val i = (y + 1) * stride + x + 1
            val a = (y + 1) * stride + x
            val b = y * stride + x + 1
            val c = y * stride + x
            sR[i] = red + sR[a] + sR[b] - sR[c]
            sG[i] = grn + sG[a] + sG[b] - sG[c]
            sB[i] = blu + sB[a] + sB[b] - sB[c]
            sL[i] = lum + sL[a] + sL[b] - sL[c]
            sQ[i] = lum.toLong() * lum + sQ[a] + sQ[b] - sQ[c]
        }

        fun box(s: LongArray, x0: Int, y0: Int, x1: Int, y1: Int): Long =
            s[(y1 + 1) * stride + x1 + 1] - s[y0 * stride + x1 + 1] -
                s[(y1 + 1) * stride + x0] + s[y0 * stride + x0]

        val out = IntArray(w * h)
        for (y in 0 until h) for (x in 0 until w) {
            var best = Double.MAX_VALUE
            var br = 0; var bg = 0; var bb = 0
            for (q in 0 until 4) {
                val x0 = max(0, if (q and 1 == 1) x else x - r)
                val x1 = min(w - 1, if (q and 1 == 1) x + r else x)
                val y0 = max(0, if (q and 2 == 2) y else y - r)
                val y1 = min(h - 1, if (q and 2 == 2) y + r else y)
                val cnt = ((x1 - x0 + 1) * (y1 - y0 + 1)).toDouble()
                val mean = box(sL, x0, y0, x1, y1) / cnt
                val v = box(sQ, x0, y0, x1, y1) / cnt - mean * mean
                if (v < best) {
                    best = v
                    br = (box(sR, x0, y0, x1, y1) / cnt).toInt()
                    bg = (box(sG, x0, y0, x1, y1) / cnt).toInt()
                    bb = (box(sB, x0, y0, x1, y1) / cnt).toInt()
                }
            }
            out[y * w + x] = Color.rgb(br.coerceIn(0, 255), bg.coerceIn(0, 255), bb.coerceIn(0, 255))
        }
        return out
    }

    /** Light the luminance gradient from the upper left, with canvas tooth folded in. */
    private fun impasto(src: IntArray, strength: Float = 0.85f): IntArray {
        val lum = FloatArray(RES * RES)
        for (p in lum.indices) {
            val c = src[p]
            val x = p % RES; val y = p / RES
            val tooth = 2.2f * sin(x * 2.0f) * sin(y * 2.0f)
            lum[p] = 0.299f * ((c shr 16) and 0xFF) + 0.587f * ((c shr 8) and 0xFF) +
                0.114f * (c and 0xFF) + tooth
        }
        val out = src.copyOf()
        for (y in 1 until RES - 1) for (x in 1 until RES - 1) {
            val p = y * RES + x
            val g = (lum[p - RES - 1] - lum[p + RES + 1]) * 0.5f + (lum[p - 1] - lum[p + 1]) * 0.25f
            val k = (g * strength).coerceIn(-58f, 58f).toInt()
            val c = src[p]
            out[p] = Color.rgb(
                (((c shr 16) and 0xFF) + k).coerceIn(0, 255),
                (((c shr 8) and 0xFF) + k).coerceIn(0, 255),
                ((c and 0xFF) + k).coerceIn(0, 255),
            )
        }
        return out
    }

    /**
     * Visible strokes over the filtered image, mostly following one direction
     * the way a painter blocks in a passage. Colours are lifted from the pixels
     * underneath so the texture never fights the icon's own palette.
     */
    private fun bristles(c: Canvas, px: IntArray, seed: Int) {
        val rnd = kotlin.random.Random(seed * 977 + 13)
        val base = rnd.nextFloat() * Math.PI.toFloat()
        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
        val path = Path()
        repeat(86) {
            val x = rnd.nextFloat() * RES
            val y = rnd.nextFloat() * RES
            val src = px[(y.toInt().coerceIn(0, RES - 1)) * RES + x.toInt().coerceIn(0, RES - 1)]
            val k = 0.78f + rnd.nextFloat() * 0.5f
            paint.color = Color.argb(
                ((0.10f + rnd.nextFloat() * 0.17f) * 255).toInt(),
                (((src shr 16) and 0xFF) * k).toInt().coerceAtMost(255),
                (((src shr 8) and 0xFF) * k).toInt().coerceAtMost(255),
                ((src and 0xFF) * k).toInt().coerceAtMost(255),
            )
            paint.strokeWidth = 3f + rnd.nextFloat() * 7f
            val a = base + (rnd.nextFloat() - 0.5f) * 1.25f
            val half = (12f + rnd.nextFloat() * 30f) / 2f
            val dx = cos(a); val dy = sin(a)
            path.reset()
            path.moveTo(x - dx * half, y - dy * half)
            path.quadTo(
                x - dy * (rnd.nextFloat() - 0.5f) * 8f,
                y + dx * (rnd.nextFloat() - 0.5f) * 8f,
                x + dx * half, y + dy * half,
            )
            c.drawPath(path, paint)
        }
    }

    /**
     * Cut the panel out along a wobbling superellipse and raise its rim.
     *
     * Mask first, content second with SRC_IN — the reverse (drawing the mask
     * over the content with DST_IN) only blends inside the path's own coverage,
     * so everything outside it would survive.
     */
    private fun cutPanel(content: Bitmap, seed: Int, shape: Float): Bitmap {
        val outline = edgePath(seed, shape)
        val out = Bitmap.createBitmap(RES, RES, Bitmap.Config.ARGB_8888)
        val c = Canvas(out)

        c.drawPath(outline, Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.BLACK })
        c.drawBitmap(content, 0f, 0f, Paint(Paint.ANTI_ALIAS_FLAG).apply {
            xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        })

        val rim = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeWidth = 3f
            xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)
        }
        c.save(); c.translate(-1.2f, -1.2f)
        rim.color = Color.argb(77, 255, 255, 255); c.drawPath(outline, rim)
        c.restore()
        c.save(); c.translate(1.2f, 1.4f)
        rim.color = Color.argb(71, 0, 0, 0); c.drawPath(outline, rim)
        c.restore()
        return out
    }

    private fun edgePath(seed: Int, shape: Float): Path {
        val r = RES / 2f - 6f
        val mid = RES / 2f
        val s = seed * 1.37f
        val p = Path()
        val steps = 220
        for (i in 0..steps) {
            val t = i.toFloat() / steps * 2f * Math.PI.toFloat()
            val ct = cos(t); val st = sin(t)
            val j = 1f + 0.020f * sin(3 * t + s) + 0.015f * sin(7 * t + s * 2.1f) + 0.010f * sin(13 * t - s)
            val x = mid + sign(ct) * abs(ct).pow(2f / shape) * r * j
            val y = mid + sign(st) * abs(st).pow(2f / shape) * r * j
            if (i == 0) p.moveTo(x, y) else p.lineTo(x, y)
        }
        p.close()
        return p
    }

    /**
     * Colours to load the brush with, sampled evenly over the finished panel.
     * Near-neutrals are dropped unless the icon has nothing else to offer —
     * they make the launch wipe read as grey stripes rather than paint.
     */
    fun brushColors(bmp: Bitmap): List<Int> {
        val all = ArrayList<Int>(44)
        for (i in 1..44) {
            val gx = (i * 0.6180339887f) % 1f
            val gy = (i * 0.7548776662f) % 1f
            val x = (14 + gx * (bmp.width - 28)).toInt().coerceIn(0, bmp.width - 1)
            val y = (14 + gy * (bmp.height - 28)).toInt().coerceIn(0, bmp.height - 1)
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
