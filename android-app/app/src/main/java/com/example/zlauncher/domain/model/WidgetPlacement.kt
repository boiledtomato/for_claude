package com.example.zlauncher.domain.model

import kotlinx.serialization.Serializable
import kotlin.math.ceil

/**
 * 置いたウィジェット 1 件。
 *
 * 幅は [COLUMNS] 分割のうち何列を占めるか、高さは dp。自由座標は持たない
 * （画面幅が変わったときに配置が破綻する）が、列を跨がない幅を持つので
 * 1 行に複数のウィジェットが並ぶ。以前は全件が全幅で、2×1 の時計でも
 * 1 行を丸ごと潰していた。
 */
@Serializable
data class WidgetPlacement(
    /** AppWidgetHost が払い出した ID。削除時は必ず deleteAppWidgetId すること */
    val appWidgetId: Int,
    val heightDp: Int,
    /** 占める列数（1..[COLUMNS]）。既存データには無いので、既定は従来どおり全幅 */
    val widthSpan: Int = COLUMNS,
) {
    companion object {
        /**
         * グリッドの列数。
         *
         * 4 にしているのは、ウィジェットの標準的な寸法（2×1、4×1、4×2…）が
         * そのまま 1/2・全幅として載るため。5 以上にすると「幅の半分」が作れない。
         */
        const val COLUMNS = 4
        const val MIN_SPAN = 1

        /**
         * 高さの下限・上限。
         *
         * 下限は提供元が申告する minHeight より小さくてよい（多くのウィジェットは
         * resizeMode で縮小を許しており、時計や 1 行のものは 80dp でも読める）。
         * 上限はホームの縦スクロール 1 画面に収まる程度。
         */
        const val MIN_HEIGHT_DP = 72
        const val MAX_HEIGHT_DP = 560
        const val HEIGHT_STEP_DP = 20

        /**
         * ホーム画面 1 セルの慣習的な寸法。
         *
         * プロバイダは minWidth = 70dp × セル数 − 30dp（セル間の隙間ぶん）で申告する、
         * というのが AOSP から続く慣習。この 2 つはその 70 と 30。
         */
        private const val CELL_PITCH_DP = 70
        private const val CELL_GAP_DP = 30

        fun clampHeight(value: Int): Int = value.coerceIn(MIN_HEIGHT_DP, MAX_HEIGHT_DP)

        fun clampSpan(value: Int): Int = value.coerceIn(MIN_SPAN, COLUMNS)

        /**
         * 提供元が申告する minWidth から「本来の幅」を求める。
         *
         * ウィジェットは 70dp セル n 個ぶんの寸法で申告される慣習があり、セル間の
         * 隙間ぶん 30dp が引かれている。足し戻してから割らないと、2×1 のウィジェットが
         * 1 列と判定されて潰れる。
         */
        fun spanForWidthDp(minWidthDp: Int): Int {
            if (minWidthDp <= 0) return COLUMNS
            val cells = ceil((minWidthDp + CELL_GAP_DP).toDouble() / CELL_PITCH_DP).toInt()
            return clampSpan(cells)
        }
    }
}
