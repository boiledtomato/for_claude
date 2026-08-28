package com.example.zlauncher.core.designsystem

import androidx.compose.ui.graphics.Color

/**
 * 配色トークン。モックアップ（カード仕様シート）と 1:1 で対応する。
 * Composable の中に生の hex を書かず、必ずここ経由で参照する。
 */
object ZColors {
    val Background = Color(0xFF0A0E17)
    val Surface = Color(0xFF121826)
    val SurfaceHigh = Color(0xFF182031)
    val SurfaceLow = Color(0xFF0F1523)
    val Outline = Color(0xFF1F2A3C)
    val OutlineStrong = Color(0xFF24314A)
    val TextPrimary = Color(0xFFE6EDF7)
    val TextSecondary = Color(0xFF8A97AB)
    val TextDim = Color(0xFF4E6B9E)
    val Accent = Color(0xFF2E7DF6)
    val AccentAlt = Color(0xFF22D3EE)
    val AccentSoft = Color(0xFF7FB2FF)
    val StatusGreen = Color(0xFF34D399)
    val StatusAmber = Color(0xFFFBBF24)
    val StatusRed = Color(0xFFF87171)
    val StatusNeutral = Color(0xFF2A3550)
    val Violet = Color(0xFF8B93F8)

    /** カテゴリーの識別色。ステータス色とは役割が違うので別リストにする */
    val CategoryColors = listOf(Accent, AccentAlt, StatusGreen, StatusAmber, Violet, StatusRed)

    /**
     * 積み上げグラフの塗り用。[CategoryColors] とスロットが 1:1 で対応する。
     *
     * 識別色は小さな点やレールに置く前提で明るく取ってあり、面で並べると隣同士を
     * 見分けられない（緑と水色、青と紫が特に近い）。色相は各スロット 8 度以内しか
     * ずらしていないので「点と棒が同じ色」には見えるまま、暗い面の上で分離する。
     *
     * 数値で確認した結果（サーフェス #121826・全ペア）:
     * 通常視の最小 ΔE 18.2、CVD（protan/deutan）の最小 ΔE 10.1、コントラスト 3:1 以上。
     * 色を足す・変えるときは目視で決めず、必ず同じ検証をやり直すこと。
     */
    val CategoryChartColors = listOf(
        Color(0xFF2267A9), // 青
        Color(0xFF24A5AF), // 水色
        Color(0xFF067542), // 緑
        Color(0xFFBC8806), // 琥珀
        Color(0xFF807FFF), // 紫
        Color(0xFFD01604), // 赤
    )

    /** 系列が多すぎるときにまとめる「その他」。色相を持たせない */
    val ChartOther = Color(0xFF44506B)
}
