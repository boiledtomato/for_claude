package com.impasto.launcher.data

import android.content.Context
import com.impasto.launcher.paint.OilPainter
import org.json.JSONArray
import org.json.JSONObject

/**
 * Where each painting hangs.
 *
 * A grid would place itself; a salon hang has to be composed. These are the
 * slots from the approved first draft — x/y are fractions of the wall, size is
 * a fraction of the wall's width. Three large canvases anchor the composition
 * (top left, centre, right) and the rest drift between them.
 */
data class Slot(
    val x: Float,
    val y: Float,
    val size: Float,
    val shape: Float = OilPainter.SHAPE_SQUIRCLE,
) {
    /** ±2°, derived from the slot index so it never changes between runs. */
    fun rotation(index: Int): Float = (((index * 37) % 9) - 4) * 0.55f
}

object Salon {
    private val SQ = OilPainter.SHAPE_SQUIRCLE
    private val CI = OilPainter.SHAPE_CIRCLE

    val WALL = listOf(
        Slot(0.18f, 0.105f, 0.235f, SQ),
        Slot(0.52f, 0.070f, 0.159f, CI),
        Slot(0.80f, 0.140f, 0.190f, CI),
        Slot(0.12f, 0.333f, 0.169f, SQ),
        Slot(0.44f, 0.311f, 0.226f, SQ),
        Slot(0.78f, 0.401f, 0.149f, CI),
        Slot(0.22f, 0.561f, 0.185f, SQ),
        Slot(0.55f, 0.583f, 0.144f, SQ),
        Slot(0.82f, 0.653f, 0.205f, SQ),
        Slot(0.16f, 0.789f, 0.149f, CI),
        Slot(0.46f, 0.810f, 0.164f, CI),
    )

    /** Paint dabs on the palette. */
    val DOCK = listOf(
        Slot(0.182f, 0.36f, 0.155f, CI),
        Slot(0.388f, 0.36f, 0.155f, CI),
        Slot(0.612f, 0.36f, 0.155f, CI),
        Slot(0.818f, 0.36f, 0.155f, CI),
    )
}

/**
 * Which app hangs in which slot, persisted so the wall survives a reboot.
 *
 * Stored as plain JSON in SharedPreferences: the payload is two short lists of
 * package names and it is read once at startup, so a database would be all
 * cost and no benefit.
 */
class LayoutStore(ctx: Context) {

    private val prefs = ctx.getSharedPreferences("impasto_layout", Context.MODE_PRIVATE)

    fun load(): Pair<List<String>, List<String>>? {
        val raw = prefs.getString(KEY, null) ?: return null
        return runCatching {
            val o = JSONObject(raw)
            o.getJSONArray("wall").strings() to o.getJSONArray("dock").strings()
        }.getOrNull()
    }

    fun save(wall: List<String>, dock: List<String>) {
        val o = JSONObject()
            .put("wall", JSONArray(wall))
            .put("dock", JSONArray(dock))
        prefs.edit().putString(KEY, o.toString()).apply()
    }

    /**
     * First run: fill the wall and palette from the installed apps, keeping the
     * ones a phone is actually used for near the thumb where we can identify
     * them, and letting everything else fall through to the drawer.
     */
    fun seed(apps: List<AppEntry>): Pair<List<String>, List<String>> {
        val byPackage = apps.associateBy { it.packageName }
        val preferredDock = DOCK_HINTS.mapNotNull { hint ->
            byPackage.keys.firstOrNull { it.contains(hint, ignoreCase = true) }
        }.distinct()

        val dock = (preferredDock + apps.map { it.packageName })
            .distinct()
            .take(Salon.DOCK.size)
        val wall = apps.map { it.packageName }
            .filterNot { it in dock }
            .take(Salon.WALL.size)
        return wall to dock
    }

    private fun JSONArray.strings(): List<String> = (0 until length()).map { getString(it) }

    private companion object {
        const val KEY = "layout_v1"
        val DOCK_HINTS = listOf("dialer", "contacts", "mail", "camera")
    }
}
