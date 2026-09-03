package com.example.zlauncher.domain.model

import kotlinx.serialization.Serializable

/**
 * ホームに置いたウィジェット 1 件。
 *
 * 座標は持たない。ウィジェットは全幅・縦一列に、このリストの順で並ぶ。
 * 自由座標配置にするとグリッド列数が変わったときに配置が破綻するため、MVP では採らない。
 */
@Serializable
data class WidgetPlacement(
    /** AppWidgetHost が払い出した ID。削除時は必ず deleteAppWidgetId すること */
    val appWidgetId: Int,
    val heightDp: Int,
) {
    companion object {
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

        fun clampHeight(value: Int): Int = value.coerceIn(MIN_HEIGHT_DP, MAX_HEIGHT_DP)
    }
}
