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

    /**
     * カテゴリーの識別色。レールの点や枠に置く、明るいほう。
     *
     * 15 スロットある。**この数は色だけで見分けられる上限を超えている**（下記）が、
     * 識別色が出る場所には必ず名前が隣にあるので、色は補助の手がかりでよい。
     *
     * 目視で足さないこと。実測値（サーフェス #121826・全ペア）:
     * 通常視の最小 ΔE 12.1 / CVD（protan・deutan）の最小 ΔE 6.5 / コントラスト 3:1 以上。
     */
    val CategoryColors = listOf(
        Color(0xFF0C7CF4), // 青
        Color(0xFF7878BA), // 藍
        Color(0xFFAD87F6), // 紫
        Color(0xFFFBC3FE), // 薄紫
        Color(0xFFBF5884), // 梅
        Color(0xFFFB6C9E), // 桃
        Color(0xFFE62F17), // 朱
        Color(0xFFC47F5A), // 茶
        Color(0xFFEFA011), // 橙
        Color(0xFFEBD815), // 黄
        Color(0xFFA1BF78), // 黄緑
        Color(0xFF36FB84), // 緑
        Color(0xFF31E3CB), // 青緑
        Color(0xFF38AAA8), // 浅葱
        Color(0xFF5CBEF4), // 空
    )

    /**
     * 積み上げグラフの塗り用。[CategoryColors] とスロットが 1:1 で対応する。
     *
     * 識別色は小さな点に置く前提で明るく取ってあり、面で並べると隣同士を見分けられない。
     * 色相は各スロット 18 度以内しかずらしていないので「点と棒が同じ色」には見えるまま、
     * 暗い面の上で分離する。
     *
     * **15 色は categorical palette の基準を満たせない。** これは努力不足ではなく計算上の限界で、
     * 15 色を色相で分けようとすると CVD では赤緑が潰れて必ず衝突する。実測値
     * （サーフェス #121826・全ペア）は 通常視の最小 ΔE 9.8 / CVD の最小 ΔE 5.3 で、
     * どちらも基準（15 / 8）を下回る。
     *
     * そのうえで成立させているのは、色だけに意味を負わせていないから:
     * - 同時に色が付く系列は最大 [CHART_SERIES_LIMIT] 件（残りは無彩色の Other）
     * - 凡例に必ず名前と実数を並べる
     * - 段の間に 2dp の隙間を空ける
     *
     * 色を足す・変えるときは目視で決めず、必ず同じ検証をやり直すこと。
     */
    val CategoryChartColors = listOf(
        Color(0xFF5392F4), // 青
        Color(0xFF6255CE), // 藍
        Color(0xFFA640FC), // 紫
        Color(0xFFA769BE), // 薄紫
        Color(0xFFAC3A75), // 梅
        Color(0xFFF55591), // 桃
        Color(0xFFD76A58), // 朱
        Color(0xFFA94D05), // 茶
        Color(0xFF9B7802), // 橙
        Color(0xFFA9A11F), // 黄
        Color(0xFF48753B), // 黄緑
        Color(0xFF0C9B4C), // 緑
        Color(0xFF1B958C), // 青緑
        Color(0xFF05B0C7), // 浅葱
        Color(0xFF136B94), // 空
    )

    /** 系列が多すぎるときにまとめる「その他」。色相を持たせない */
    val ChartOther = Color(0xFF44506B)
}
