package com.example.zlauncher.domain.model

import kotlinx.serialization.Serializable

/**
 * 配色の微調整。彩度と明度を掛ける係数で持つ。
 *
 * 色そのものを持たせないのは、**配色の作りを壊さないため**。トークンは面と文字、識別色と
 * グラフ塗りが対応づいた一揃いで設計してあり、1 色だけ差し替えられると関係が崩れる。
 * 全体に同じ係数を掛ける形なら、その関係を保ったまま好みへ寄せられる。
 *
 * 色相は動かさない。動かすとカテゴリーの識別色とグラフの塗りが別の色に見える。
 */
@Serializable
data class ColorAdjust(
    /** 彩度。0 で無彩色、1 で既定、上げるほど鮮やかに */
    val saturation: Float = 1f,
    /** 明度。1 で既定、下げるほど沈み、上げるほど明るく */
    val lightness: Float = 1f,
) {
    val isNeutral: Boolean get() = saturation == 1f && lightness == 1f

    fun normalized(): ColorAdjust = ColorAdjust(
        saturation = saturation.coerceIn(SATURATION_RANGE),
        lightness = lightness.coerceIn(LIGHTNESS_RANGE),
    )

    companion object {
        val SATURATION_RANGE = 0f..1.6f

        /**
         * 明度の幅。**1.6 までは上げられない。**
         *
         * 暗い配色で 1.4 を超えると面が中間色まで浮いてきて、どちらのモードでもない
         * 濁った見た目になる。下も 0.6 で止める ― それ以下は面同士の差が潰れて、
         * カードの境目が見えなくなる。
         */
        val LIGHTNESS_RANGE = 0.6f..1.4f

        val NONE = ColorAdjust()
    }
}
