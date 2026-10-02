package com.example.zlauncher.core.color

import com.example.zlauncher.domain.model.ColorAdjust

/**
 * アプリのアイコンに掛ける 4x5 の色行列。**Android に依存しない**ので単体テストにかけられる。
 *
 * アイコンは配布物なので、[ColorMath] のように 1 色ずつ HSL でいじることはできない
 * （何百枚もの絵を毎回描き直すことになる）。代わりに描画時の色行列に載せる ―
 * GPU 側で済み、元の画像には触れない。
 *
 * 彩度は輝度への寄せ（標準の輝度係数）、明度は RGB の一様な拡大で表す。**一様な拡大は
 * どの行列とも交換できる**ので、掛ける順番を気にしなくてよい。
 */
object IconColorMatrix {

    /** sRGB の輝度係数。Android の `ColorMatrix.setSaturation` と同じ値にしてある */
    private const val LUMA_R = 0.213f
    private const val LUMA_G = 0.715f
    private const val LUMA_B = 0.072f

    /** 4x5 の行優先。素通し（調整なし）のときは null を返す ― 呼び出し側でフィルタを外せる */
    fun of(adjust: ColorAdjust): FloatArray? {
        val safe = adjust.normalized()
        if (safe.isNeutral) return null
        return build(safe.saturation, safe.lightness)
    }

    fun build(saturation: Float, brightness: Float): FloatArray {
        val s = saturation
        val b = brightness
        val invSat = 1f - s

        val r = LUMA_R * invSat
        val g = LUMA_G * invSat
        val bl = LUMA_B * invSat

        return floatArrayOf(
            b * (r + s), b * g, b * bl, 0f, 0f,
            b * r, b * (g + s), b * bl, 0f, 0f,
            b * r, b * g, b * (bl + s), 0f, 0f,
            // アルファには触らない。触ると角丸の縁やアダプティブアイコンの余白が濁る
            0f, 0f, 0f, 1f, 0f,
        )
    }
}
