package com.pointille.launcher.paint

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.drawable.AdaptiveIconDrawable
import android.graphics.drawable.Drawable
import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

/**
 * Where a panel's picture comes from, in three tiers.
 *
 *  1. **A hand-drawn motif**, for the apps worth the trouble. Fifteen of them.
 *  2. **The app's own icon, converted.** This is the workhorse, and it works
 *     because of a happy accident of the platform: an adaptive icon is already
 *     split into a background layer and a foreground layer — exactly the two
 *     images the painter wants. Background becomes the ground, foreground
 *     becomes the subject, and the identical rendering runs.
 *  3. **Colour alone.** When tier 2 would come out unreadable — a wordmark, a
 *     thin line on white — the drawing is thrown away and only the colours are
 *     kept, as divided fields.
 *
 * Everything is painted once and cached to disk, so this cost is paid on first
 * sight of an app and never again until it updates.
 */
object IconSource {

    private const val RES = Pointillist.RES

    /** Below this difference in value, a converted icon would read as a smudge. */
    private const val MIN_CONTRAST = 26f

    /** Tiers, as a person can force them from the long-press sheet. */
    const val AUTO = 0
    const val HAND = 1
    const val CONVERT = 2
    const val FIELDS = 3

    /**
     * @param key   stable cache key — "package@versionCode", so an app update
     *              repaints its panel and nothing else does.
     * @param force [AUTO] to pick a tier automatically, or a tier to insist on.
     *              Each choice caches separately, so switching back is instant.
     */
    fun painted(ctx: Context, key: String, pkg: String, icon: Drawable?, seed: Int, force: Int = AUTO): Bitmap {
        val cacheKey = if (force == AUTO) key else "$key#$force"
        cacheFile(ctx, cacheKey).let { f ->
            if (f.exists()) BitmapFactory.decodeFile(f.path)?.let { return it }
        }

        val (field, subject) = source(pkg, icon, seed, force)
        val painted = Pointillist.render(field, subject, seed)
        field.recycle(); subject?.recycle()

        runCatching {
            val f = cacheFile(ctx, cacheKey)
            f.parentFile?.mkdirs()
            f.outputStream().use { painted.compress(Bitmap.CompressFormat.PNG, 100, it) }
        }
        return painted
    }

    fun clearCache(ctx: Context) {
        cacheDir(ctx).listFiles()?.forEach { it.delete() }
    }

    private fun cacheDir(ctx: Context) = File(ctx.filesDir, "dots")
    private fun cacheFile(ctx: Context, key: String) =
        File(cacheDir(ctx), key.replace(Regex("[^A-Za-z0-9._@-]"), "_") + ".png")

    // ------------------------------------------------------------------ tiers

    private fun source(pkg: String, icon: Drawable?, seed: Int, force: Int): Pair<Bitmap, Bitmap?> {
        if (force == AUTO || force == HAND) {
            Motifs.forPackage(pkg)?.let { m ->                   // tier 1
                return Motifs.ground(m.groundA, m.groundB) to Motifs.subject(m.draw)
            }
            // asked for a motif that does not exist — fall through rather than
            // hand back an empty panel
        }
        if (icon == null) return colourFields(Color.rgb(110, 120, 130), Color.rgb(60, 70, 80), seed) to null

        if (force != FIELDS) {
            val split = split(icon)                              // tier 2
            // when it was asked for outright, the contrast check is skipped:
            // the person looking at the screen outranks the heuristic
            if (split != null && (force == CONVERT || readable(split.first, split.second))) return split
            split?.first?.recycle(); split?.second?.recycle()
        }

        val (a, b) = dominantPair(icon)                          // tier 3
        return colourFields(a, b, seed) to null
    }

    /**
     * Split an icon into ground and subject.
     *
     * Adaptive icons hand this over directly. Legacy ones do not, so the ground
     * is estimated from the border ring — whatever colour surrounds the mark —
     * and anything far enough from it is taken to be the mark.
     */
    private fun split(icon: Drawable): Pair<Bitmap, Bitmap>? {
        if (icon is AdaptiveIconDrawable) {
            // layers are authored on 108 units of which the middle 72 are safe,
            // so they are drawn oversized to put that safe zone on our square
            val over = (RES * 0.25f).toInt()
            val bg = icon.background ?: return null
            val fg = icon.foreground ?: return null
            return opaque(layer(bg, -over, RES + over)) to layer(fg, -over, RES + over)
        }

        val flat = layer(icon, 0, RES)
        val px = IntArray(RES * RES)
        flat.getPixels(px, 0, RES, 0, 0, RES, RES)
        flat.recycle()

        val ground = borderColour(px) ?: return null
        val field = Bitmap.createBitmap(RES, RES, Bitmap.Config.ARGB_8888)
        Canvas(field).drawColor(ground)

        val sub = IntArray(px.size)
        for (i in px.indices) {
            val p = px[i]
            val far = Color.alpha(p) > 128 && (
                abs(Color.red(p) - Color.red(ground)) +
                abs(Color.green(p) - Color.green(ground)) +
                abs(Color.blue(p) - Color.blue(ground))) > 60
            sub[i] = if (far) p else Color.TRANSPARENT
        }
        val subject = Bitmap.createBitmap(RES, RES, Bitmap.Config.ARGB_8888)
        subject.setPixels(sub, 0, RES, 0, 0, RES, RES)
        return field to subject
    }

    private fun layer(d: Drawable, from: Int, to: Int): Bitmap {
        val bmp = Bitmap.createBitmap(RES, RES, Bitmap.Config.ARGB_8888)
        d.setBounds(from, from, to, to)
        d.draw(Canvas(bmp))
        return bmp
    }

    /** Composite over the layer's own average, so no transparency reaches the painter. */
    private fun opaque(bmp: Bitmap): Bitmap {
        val px = IntArray(RES * RES)
        bmp.getPixels(px, 0, RES, 0, 0, RES, RES)
        var r = 0L; var g = 0L; var b = 0L; var n = 0L
        for (p in px) {
            if (Color.alpha(p) < 128) continue
            r += Color.red(p); g += Color.green(p); b += Color.blue(p); n++
        }
        val avg = if (n == 0L) Color.rgb(120, 120, 120)
                  else Color.rgb((r / n).toInt(), (g / n).toInt(), (b / n).toInt())
        val out = Bitmap.createBitmap(RES, RES, Bitmap.Config.ARGB_8888)
        val c = Canvas(out)
        c.drawColor(avg)
        c.drawBitmap(bmp, 0f, 0f, null)
        bmp.recycle()
        return out
    }

    /** The colour around the edge of a legacy icon — most likely its ground. */
    private fun borderColour(px: IntArray): Int? {
        val band = (RES * 0.10f).toInt()
        var r = 0L; var g = 0L; var b = 0L; var n = 0L
        for (y in 0 until RES) for (x in 0 until RES) {
            val edge = x < band || y < band || x >= RES - band || y >= RES - band
            if (!edge) continue
            val p = px[y * RES + x]
            if (Color.alpha(p) < 128) continue
            r += Color.red(p); g += Color.green(p); b += Color.blue(p); n++
        }
        if (n < 64) return null      // a mostly transparent border tells us nothing
        return Color.rgb((r / n).toInt(), (g / n).toInt(), (b / n).toInt())
    }

    /** Would the converted panel still read? Value separation is what carries it. */
    private fun readable(field: Bitmap, subject: Bitmap): Boolean {
        val fp = IntArray(RES * RES); field.getPixels(fp, 0, RES, 0, 0, RES, RES)
        val sp = IntArray(RES * RES); subject.getPixels(sp, 0, RES, 0, 0, RES, RES)

        var sLum = 0.0; var fLum = 0.0; var n = 0
        for (i in sp.indices) {
            if (Color.alpha(sp[i]) <= 90) continue
            sLum += lum(sp[i]); fLum += lum(fp[i]); n++
        }
        // nothing to draw, or the mark swallows the whole panel
        val share = n.toFloat() / sp.size
        if (share < 0.03f || share > 0.92f) return false
        return abs(sLum / n - fLum / n) >= MIN_CONTRAST
    }

    private fun lum(p: Int) = 0.299 * Color.red(p) + 0.587 * Color.green(p) + 0.114 * Color.blue(p)

    /** Tier 3 needs two colours worth looking at: the most saturated, and a foil. */
    private fun dominantPair(icon: Drawable): Pair<Int, Int> {
        val bmp = layer(icon, 0, RES)
        val px = IntArray(RES * RES)
        bmp.getPixels(px, 0, RES, 0, 0, RES, RES)
        bmp.recycle()

        var best = Color.rgb(90, 100, 120); var bestChroma = -1
        var dark = Color.rgb(40, 46, 58); var darkest = 999.0
        for (p in px) {
            if (Color.alpha(p) < 128) continue
            val r = Color.red(p); val g = Color.green(p); val b = Color.blue(p)
            val chroma = max(r, max(g, b)) - min(r, min(g, b))
            if (chroma > bestChroma) { bestChroma = chroma; best = p }
            val l = lum(p)
            if (l < darkest) { darkest = l; dark = p }
        }
        return best to Pointillist.shade(dark, -0.15f)
    }

    /** Tier 3: keep the colours, throw the drawing away. */
    private fun colourFields(a: Int, b: Int, seed: Int): Bitmap {
        val rnd = Random(seed * 911 + 5)
        val bmp = Bitmap.createBitmap(RES, RES, Bitmap.Config.ARGB_8888)
        val c = Canvas(bmp)
        val r = RES.toFloat()
        val cut = 0.30f + rnd.nextFloat() * 0.30f

        c.drawRect(0f, 0f, r, r, Paint().apply {
            shader = LinearGradient(0f, 0f, r, r, Pointillist.shade(a, 0.10f), a, Shader.TileMode.CLAMP)
        })
        val second = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = b }
        if (rnd.nextFloat() < 0.5f) {
            c.drawRect(0f, 0f, r, r * cut, second)
        } else {
            val p = android.graphics.Path()
            p.moveTo(0f, r); p.lineTo(r, r * cut); p.lineTo(r, r); p.close()
            c.drawPath(p, second)
        }
        return bmp
    }
}
