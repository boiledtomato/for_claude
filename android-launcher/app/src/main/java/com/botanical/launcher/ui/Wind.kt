package com.botanical.launcher.ui

import androidx.compose.ui.geometry.Offset
import com.botanical.launcher.flora.Flora
import com.botanical.launcher.flora.Organ
import com.botanical.launcher.flora.Stem
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sin

/**
 * 風。
 *
 * 突風は左から右へ抜け、株ごと・葉ごとに位相がずれる。速さの倍率はすべて
 * 整数にしてある。整数でないと位相が 1 周するたびに動きが飛ぶ。
 */
object Wind {

    /** 強弱。風は一定では吹かない。 */
    fun gust(phase: Float, x: Float, width: Float): Float {
        val travel = x / width.coerceAtLeast(1f) * 2.6f
        return 0.58f + 0.30f * sin(phase - travel) + 0.12f * sin(phase * 3f - travel)
    }

    /**
     * 撓んだ茎の制御点。根元は動かさず、上の制御点ほど大きくずらす。
     * 横へ流れたぶんだけ背を縮めないと、茎が伸び縮みして見える。
     */
    fun bend(stem: Stem, phase: Float, width: Float): List<Offset> {
        val g = gust(phase, stem.p[0].x, width)
        val s = sin(
            phase * stem.sway.speed + stem.sway.phase -
                stem.p[0].x / width.coerceAtLeast(1f) * 2.6f
        )
        val amp = stem.sway.amp * g * s
        val out = ArrayList<Offset>(4)
        out.add(stem.p[0])
        for (i in 1..3) {
            val v = when (i) { 1 -> 0.35f; 2 -> 0.75f; else -> 1.0f }
            val k = v.pow(1.7f)
            out.add(Offset(stem.p[i].x + amp * k, stem.p[i].y + abs(amp) * k * 0.12f))
        }
        return out
    }

    /** 葉や花が茎とは別に羽ばたく角度（度）。 */
    fun flutter(organSway: com.botanical.launcher.flora.Sway, phase: Float, gust: Float): Float =
        organSway.amp * sin(phase * organSway.speed + organSway.phase) * (0.4f + 0.6f * gust)
}
