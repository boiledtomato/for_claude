package com.pointille.launcher.data

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

/**
 * Where each painting hangs.
 *
 * A grid places itself; a salon hang has to be composed. These are the slots
 * from the approved draft — x/y are fractions of the wall, size a fraction of
 * the wall's width. Three large canvases anchor it (top left, centre, right)
 * and the rest drift between them.
 */
data class Slot(val x: Float, val y: Float, val size: Float) {
    /** ±2°, from the slot index so it never changes between runs. */
    fun rotation(index: Int): Float = (((index * 37) % 9) - 4) * 0.55f
}

object Salon {
    val WALL = listOf(
        Slot(0.18f, 0.105f, 0.235f),
        Slot(0.52f, 0.070f, 0.159f),
        Slot(0.80f, 0.140f, 0.190f),
        Slot(0.12f, 0.333f, 0.169f),
        Slot(0.44f, 0.311f, 0.226f),
        Slot(0.78f, 0.401f, 0.149f),
        Slot(0.22f, 0.561f, 0.185f),
        Slot(0.55f, 0.583f, 0.144f),
        Slot(0.82f, 0.653f, 0.205f),
        Slot(0.16f, 0.789f, 0.149f),
        Slot(0.46f, 0.810f, 0.164f),
    )

    /** Paint dabs on the palette. */
    val DOCK = listOf(
        Slot(0.182f, 0.36f, 0.155f),
        Slot(0.388f, 0.36f, 0.155f),
        Slot(0.612f, 0.36f, 0.155f),
        Slot(0.818f, 0.36f, 0.155f),
    )
}

/**
 * Which app hangs in which slot, kept so the wall survives a reboot.
 *
 * Two short lists of package names, read once at startup — a database would be
 * all cost and no benefit.
 */
class LayoutStore(ctx: Context) {

    private val prefs = ctx.getSharedPreferences("pointille_layout", Context.MODE_PRIVATE)

    fun load(): Pair<List<String>, List<String>>? {
        val raw = prefs.getString(KEY, null) ?: return null
        return runCatching {
            val o = JSONObject(raw)
            o.getJSONArray("wall").strings() to o.getJSONArray("dock").strings()
        }.getOrNull()
    }

    fun save(wall: List<String>, dock: List<String>) {
        prefs.edit()
            .putString(KEY, JSONObject().put("wall", JSONArray(wall)).put("dock", JSONArray(dock)).toString())
            .apply()
    }

    /**
     * First run. The apps with a hand-drawn motif go on the wall first, since
     * those are the ones that look like paintings rather than conversions.
     */
    fun seed(apps: List<AppEntry>): Pair<List<String>, List<String>> {
        val byPackage = apps.associateBy { it.packageName }
        val dock = (DOCK_HINTS.mapNotNull { hint ->
            byPackage.keys.firstOrNull { it.contains(hint, ignoreCase = true) }
        } + apps.map { it.packageName }).distinct().take(Salon.DOCK.size)

        val rest = apps.map { it.packageName }.filterNot { it in dock }
        val (handDrawn, converted) = rest.partition {
            com.pointille.launcher.paint.Motifs.forPackage(it) != null
        }
        return (handDrawn + converted).take(Salon.WALL.size) to dock
    }

    private fun JSONArray.strings(): List<String> = (0 until length()).map { getString(it) }

    private companion object {
        const val KEY = "layout_v1"
        val DOCK_HINTS = listOf("dialer", "mail", "calculator", "search")
    }
}
