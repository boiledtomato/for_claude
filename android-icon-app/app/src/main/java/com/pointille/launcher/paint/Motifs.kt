package com.pointille.launcher.paint

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader

private val LINEN = 0xFFE8DCC0.toInt()
private val SLATE = 0xFF2E3440.toInt()
private val WOOD = 0xFF6A4526.toInt()
private val WOOD_D = 0xFF3E2614.toInt()
private val BRASS = 0xFFC79733.toInt()

/**
 * A hand-drawn subject and the ground it sits on.
 *
 * These are not app icons. They are things from a Sunday afternoon on the
 * Grande Jatte — a parasol, a sail, the trumpeter, the dog. Bold silhouettes
 * only: at 78px a thin line, or a dark shape on a ground of the same value,
 * simply disappears. Three of these had to be redrawn for exactly that reason.
 */
class Motif(
    val label: String,
    val groundA: Int,
    val groundB: Int,
    val draw: (Pen) -> Unit,
)

object Motifs {

    /** Ground for a motif: the diagonal two-stop wash every panel starts from. */
    fun ground(a: Int, b: Int): Bitmap {
        val bmp = Bitmap.createBitmap(Pointillist.RES, Pointillist.RES, Bitmap.Config.ARGB_8888)
        val c = Canvas(bmp)
        val r = Pointillist.RES.toFloat()
        c.drawRect(0f, 0f, r, r, Paint().apply {
            shader = LinearGradient(0f, 0f, r, r, b, a, Shader.TileMode.CLAMP)
        })
        return bmp
    }

    /** The subject, drawn in a 100x100 space and scaled onto the panel. */
    fun subject(draw: (Pen) -> Unit): Bitmap {
        val bmp = Bitmap.createBitmap(Pointillist.RES, Pointillist.RES, Bitmap.Config.ARGB_8888)
        val c = Canvas(bmp)
        val r = Pointillist.RES.toFloat()
        c.save()
        c.translate(r * 0.13f, r * 0.13f)
        c.scale(r * 0.74f / 100f, r * 0.74f / 100f)
        draw(Pen(c))
        c.restore()
        return bmp
    }

    val ALL: Map<String, Motif> = mapOf(
        "parasol" to Motif("日傘", 0xFF4E7C3A.toInt(), 0xFF98C06A.toInt()) { p ->
            p.color(LINEN)
            p.shape { moveTo(10f, 52f); cubicTo(16f, 14f, 84f, 14f, 90f, 52f) }
            p.ink(0x73786440)
            for (i in 1..4) p.ln(50f, 17f, 10f + i * 16f, 52f, 2.4f)
            p.color(LINEN); p.ci(50f, 14f, 5f)
            p.ink(WOOD); p.ln(50f, 52f, 50f, 92f, 5f)
        },

        "sail" to Motif("帆船", 0xFF2C5E7E.toInt(), 0xFF7FB3C9.toInt()) { p ->
            p.color(LINEN); p.tri(48f, 8f, 48f, 66f, 16f, 66f)
            p.color(0xFFDCCFAE.toInt()); p.tri(56f, 22f, 56f, 66f, 84f, 66f)
            p.ink(WOOD_D); p.ln(52f, 8f, 52f, 70f, 3.5f)
            p.color(SLATE)
            p.shape { moveTo(10f, 70f); lineTo(90f, 70f); lineTo(74f, 84f); lineTo(24f, 84f) }
        },

        "cornet" to Motif("ラッパ吹き", 0xFF7A4A1E.toInt(), 0xFFC79049.toInt()) { p ->
            p.color(SLATE); p.ci(34f, 22f, 12f)
            p.shape { moveTo(22f, 36f); lineTo(46f, 36f); lineTo(52f, 96f); lineTo(18f, 96f) }
            p.ink(SLATE); p.ln(44f, 48f, 62f, 33f, 10f)
            p.color(BRASS); p.tri(56f, 38f, 96f, 8f, 98f, 42f)
            p.color(0xFF8A6520.toInt()); p.oval(95f, 25f, 6f, 18f, -0.62f)
        },

        "pair" to Motif("二人の人影", 0xFF3E6E4C.toInt(), 0xFF8CBA83.toInt()) { p ->
            p.color(SLATE); p.ci(32f, 24f, 11f)
            p.shape { moveTo(21f, 36f); lineTo(43f, 36f); lineTo(48f, 96f); lineTo(16f, 96f) }
            p.color(0xFF43363F.toInt()); p.ci(68f, 30f, 10f)
            p.shape { moveTo(58f, 41f); lineTo(78f, 41f); lineTo(84f, 96f); lineTo(54f, 96f) }
            p.color(LINEN); p.ci(32f, 14f, 7f)
        },

        "tree" to Motif("木と影", 0xFF5D8A3C.toInt(), 0xFFB4CE79.toInt()) { p ->
            p.color(WOOD_D); p.rr(44f, 34f, 10f, 50f, 3f)
            p.color(0xFF3F6B2E.toInt()); p.ci(48f, 26f, 22f); p.ci(66f, 32f, 15f); p.ci(32f, 34f, 14f)
            p.color(0x8C263442.toInt()); p.oval(36f, 86f, 34f, 7f, -0.22f)
        },

        "dog" to Motif("犬", 0xFF6E8A34.toInt(), 0xFFC0D07A.toInt()) { p ->
            p.color(SLATE); p.rr(22f, 44f, 52f, 24f, 11f); p.ci(80f, 42f, 12f)
            p.tri(74f, 32f, 84f, 30f, 80f, 44f)
            p.rr(26f, 62f, 8f, 26f, 4f); p.rr(40f, 62f, 8f, 26f, 4f)
            p.rr(58f, 62f, 8f, 26f, 4f); p.rr(68f, 62f, 8f, 26f, 4f)
            p.ink(SLATE); p.ln(22f, 48f, 8f, 32f, 7f)
            p.color(LINEN); p.ci(86f, 40f, 2.6f)
        },

        // a wooden box on a green ground was one value and read as a blob; the
        // ground moved to river blue and the body to pale wood
        "tripod" to Motif("三脚の写真機", 0xFF2E4A5C.toInt(), 0xFF7FA0B6.toInt()) { p ->
            p.ink(WOOD_D)
            p.ln(46f, 60f, 18f, 96f, 7f); p.ln(46f, 60f, 72f, 96f, 7f); p.ln(46f, 60f, 48f, 94f, 7f)
            p.color(0xFFC99A58.toInt()); p.rr(8f, 20f, 52f, 40f, 5f)
            p.ink(0x803A2210.toInt())
            for (i in 0..2) p.ln(21f + i * 11f, 21f, 21f + i * 11f, 59f, 3.5f)
            p.color(BRASS); p.ci(70f, 40f, 19f)
            p.color(0xFF1E222A.toInt()); p.ci(70f, 40f, 11f)
            p.color(LINEN); p.ci(65f, 35f, 4.2f)
        },

        "opera" to Motif("オペラグラス", 0xFF33566E.toInt(), 0xFF7C9FB6.toInt()) { p ->
            p.color(SLATE); p.rr(4f, 28f, 42f, 44f, 13f); p.rr(54f, 28f, 42f, 44f, 13f)
            p.rr(42f, 42f, 16f, 12f, 4f)
            p.color(LINEN); p.ci(25f, 50f, 15f); p.ci(75f, 50f, 15f)
            p.color(0xFF5E8CAE.toInt()); p.ci(25f, 50f, 9f); p.ci(75f, 50f, 9f)
        },

        "bouquet" to Motif("花束", 0xFF8A5A2A.toInt(), 0xFFCFA163.toInt()) { p ->
            p.ink(0xFF4E6B32.toInt())
            p.ln(50f, 90f, 42f, 56f, 4.5f); p.ln(50f, 90f, 60f, 58f, 4.5f); p.ln(50f, 90f, 50f, 50f, 4.5f)
            p.color(0xFFEDE7CC.toInt()); p.ci(40f, 46f, 13f)
            p.color(0xFFD9829A.toInt()); p.ci(62f, 48f, 12f)
            p.color(0xFFEFC44E.toInt()); p.ci(50f, 32f, 13f)
            p.color(LINEN); p.rr(40f, 82f, 20f, 14f, 5f)
        },

        "carnet" to Motif("手帳", 0xFF5A5136.toInt(), 0xFF9C9161.toInt()) { p ->
            p.color(LINEN); p.rr(14f, 20f, 72f, 60f, 4f)
            p.color(WOOD_D); p.rr(46f, 20f, 8f, 60f, 2f)
            p.ink(0x805A462D.toInt())
            for (i in 0..2) {
                p.ln(22f, 36f + i * 13f, 42f, 36f + i * 13f, 3f)
                p.ln(58f, 36f + i * 13f, 78f, 36f + i * 13f, 3f)
            }
        },

        "bowler" to Motif("山高帽", 0xFF46607E.toInt(), 0xFF92AEC4.toInt()) { p ->
            p.color(SLATE)
            p.oval(50f, 66f, 40f, 10f)
            p.shape { moveTo(26f, 66f); cubicTo(26f, 26f, 74f, 26f, 74f, 66f) }
            p.color(0x73E8DCC0); p.rr(26f, 56f, 48f, 8f, 3f)
        },

        "dove" to Motif("鳩", 0xFF4E7C6A.toInt(), 0xFF9AC4AE.toInt()) { p ->
            p.color(LINEN)
            p.oval(46f, 56f, 28f, 16f, -0.18f)
            p.ci(76f, 42f, 12f)
            p.tri(22f, 54f, 4f, 44f, 20f, 68f)
            p.color(0xFFD2C8AA.toInt()); p.oval(42f, 52f, 19f, 9f, 0.48f)
            p.color(BRASS); p.tri(86f, 38f, 98f, 42f, 86f, 47f)
            p.color(SLATE); p.ci(80f, 38f, 2.8f)
        },

        "letter" to Motif("封書", 0xFF2C4A34.toInt(), 0xFF68936D.toInt()) { p ->
            p.color(LINEN); p.rr(10f, 26f, 80f, 50f, 4f)
            p.ink(0x8C5A462D.toInt())
            p.trace(5f) { moveTo(12f, 29f); lineTo(50f, 55f); lineTo(88f, 29f) }
            p.color(0xFFAE3323.toInt()); p.ci(50f, 66f, 12f)
            p.color(0x663C0E08); p.ci(50f, 66f, 6f)
        },

        "picnic" to Motif("市松の敷物", 0xFF7C5A3A.toInt(), 0xFFC1996C.toInt()) { p ->
            p.color(LINEN); p.rr(10f, 18f, 80f, 66f, 4f)
            p.color(0x9EB23A30.toInt())
            for (r in 0..4) for (k in 0..4)
                if ((r + k) % 2 == 0) p.rr(10f + k * 16f, 18f + r * 13.2f, 16f, 13.2f, 0f)
        },

        "glass" to Motif("虫眼鏡", 0xFF3E5C76.toInt(), 0xFF8FADC4.toInt()) { p ->
            p.color(0x8CE2EAF0.toInt()); p.ci(44f, 42f, 24f)
            p.ink(BRASS); p.arc(44f, 42f, 24f, 0f, 360f, 9f)
            p.ink(WOOD); p.ln(61f, 59f, 84f, 82f, 12f)
        },
    )

    /**
     * Which motif a package gets. Matching is on substrings of the package name
     * because vendors all name things differently — `com.google.android.deskclock`
     * and `com.sec.android.app.clockpackage` are both the clock.
     *
     * This is only the seed list. Anything not matched here falls through to
     * [IconSource], which converts the app's own icon instead.
     */
    private val HINTS: List<Pair<String, List<String>>> = listOf(
        "tripod" to listOf("camera", "gcam"),
        "pair" to listOf("messaging", ".mms", "message", "line", "chat"),
        "parasol" to listOf("weather", "wetter", "tenki"),
        "cornet" to listOf("music", "youtube.music", "spotify", "audio"),
        "sail" to listOf("maps", "navigation", "map"),
        "opera" to listOf("chrome", "browser", "firefox", "webview"),
        "bouquet" to listOf("calendar"),
        "carnet" to listOf("keep", "notes", "memo", "note"),
        "dog" to listOf("photos", "gallery", "album"),
        "tree" to listOf("deskclock", "clock", "alarm"),
        "bowler" to listOf("settings"),
        "dove" to listOf("dialer", "phone", "contacts"),
        "letter" to listOf("gm", "mail", "email"),
        "picnic" to listOf("calculator", "calc"),
        "glass" to listOf("search", "quicksearchbox", "googlequicksearchbox"),
    )

    fun forPackage(pkg: String): Motif? {
        val lower = pkg.lowercase()
        for ((key, hints) in HINTS) {
            if (hints.any { lower.contains(it) }) return ALL[key]
        }
        return null
    }
}
