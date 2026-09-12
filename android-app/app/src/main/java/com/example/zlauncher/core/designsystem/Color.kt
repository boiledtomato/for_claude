package com.example.zlauncher.core.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * 配色トークン一式。暗い面と明るい面で 1 つずつ持つ。
 *
 * Composable の中に生の hex を書かず、必ず [ZColors] 経由で参照する。参照の形を変えずに
 * 差し替えられるよう、[ZColors] は CompositionLocal を読むだけの入れ物にしてある。
 */
@Immutable
data class ZColorScheme(
    val background: Color,
    val surface: Color,
    val surfaceHigh: Color,
    val surfaceLow: Color,
    val outline: Color,
    val outlineStrong: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textDim: Color,
    val accent: Color,
    val accentAlt: Color,
    /** 強調した文字色。面の明暗と逆向きに振る（暗い面では明るく、明るい面では濃く） */
    val accentSoft: Color,
    val statusGreen: Color,
    val statusAmber: Color,
    val statusRed: Color,
    val statusNeutral: Color,
    val violet: Color,
    /** 取り消しのきかない操作の色。ステータスの赤とは別に持つ */
    val danger: Color,
    val onDanger: Color,
    val categoryColors: List<Color>,
    val categoryChartColors: List<Color>,
    val chartOther: Color,
    val isLight: Boolean,
)

/**
 * カテゴリーの識別色（暗い面）。レールの点や枠に置く、明るいほう。
 *
 * 15 スロットある。**この数は色だけで見分けられる上限を超えている**が、識別色が出る場所には
 * 必ず名前が隣にあるので、色は補助の手がかりでよい。目視で足さないこと ―
 * `tools/palette_check.py` を走らせて実測値を見る。
 */
private val DarkCategoryColors = listOf(
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
 * 積み上げグラフの塗り用（暗い面）。[DarkCategoryColors] とスロットが 1:1 で対応する。
 *
 * 識別色は小さな点に置く前提で明るく取ってあり、面で並べると隣同士を見分けられない。
 * 色相は各スロット 18 度以内しかずらしていないので「点と棒が同じ色」には見えるまま、
 * 暗い面の上で分離する。
 *
 * **15 色は categorical palette の基準を満たせない。** これは努力不足ではなく計算上の限界で、
 * 15 色を色相で分けようとすると CVD では赤緑が潰れて必ず衝突する。そのうえで成立させて
 * いるのは、色だけに意味を負わせていないから:
 *
 * - 同時に色が付く系列は最大 4 件（残りは無彩色の [ZColorScheme.chartOther]）
 * - 凡例に必ず名前と実数を並べる
 * - 段の間に 2dp の隙間を空ける
 */
private val DarkCategoryChartColors = listOf(
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

/**
 * カテゴリーの識別色（明るい面）。
 *
 * 暗い面の色をそのまま白の上に置くと、明るいものは飛び、暗いものは沈む。色相は各スロット
 * 18 度以内しか動かしていない（テーマを切り替えても「同じカテゴリーは同じ色」に見せるため）
 * が、明度と彩度は白の上で分離するよう取り直してある。
 *
 * 白の上では暗い色まで使えるので、**明るい面の 15 色は基準（通常視 15 / CVD 8）を
 * 満たす** ― 実測 20.2 / 8.7、コントラスト 3.02:1。暗い面が満たせないのは面が暗いからで、
 * 手を抜いたからではない。`tools/palette_check.py` で測り直せる。
 */
private val LightCategoryColors = listOf(
    Color(0xFF3A5C8D), // 青
    Color(0xFF7B1BE6), // 藍
    Color(0xFF6C82FF), // 紫
    Color(0xFF866895), // 薄紫
    Color(0xFFAE1369), // 梅
    Color(0xFFFF00C7), // 桃
    Color(0xFFCB7A7A), // 朱
    Color(0xFF7C5047), // 茶
    Color(0xFFDD5B00), // 橙
    Color(0xFF7C7752), // 黄
    Color(0xFF1C6D00), // 黄緑
    Color(0xFF00AC44), // 緑
    Color(0xFF00A697), // 青緑
    Color(0xFF006D71), // 浅葱
    Color(0xFF009CC8), // 空
)

/**
 * 積み上げグラフの塗り用（明るい面）。[LightCategoryColors] と 1:1 で対応する。
 *
 * 識別色から明度を 4 落として彩度を 1 割下げただけ（色相のずれは最大 0.8 度）。点と棒が
 * 同じ色に見えたまま、面で並べたときに分離する。実測 19.0 / 8.0、コントラスト 3.21:1。
 */
private val LightCategoryChartColors = listOf(
    Color(0xFF35527E), // 青
    Color(0xFF6F1CCC), // 藍
    Color(0xFF6779E8), // 紫
    Color(0xFF7A5F87), // 薄紫
    Color(0xFF9B165E), // 梅
    Color(0xFFE81EB6), // 桃
    Color(0xFFBA7272), // 朱
    Color(0xFF6F4840), // 茶
    Color(0xFFCA560C), // 橙
    Color(0xFF716D4C), // 黄
    Color(0xFF1C6204), // 黄緑
    Color(0xFF1C9F43), // 緑
    Color(0xFF1C9A8C), // 青緑
    Color(0xFF0A6266), // 浅葱
    Color(0xFF1E90B7), // 空
)

val ZDarkColors = ZColorScheme(
    background = Color(0xFF0A0E17),
    surface = Color(0xFF121826),
    surfaceHigh = Color(0xFF182031),
    surfaceLow = Color(0xFF0F1523),
    outline = Color(0xFF1F2A3C),
    outlineStrong = Color(0xFF24314A),
    textPrimary = Color(0xFFE6EDF7),
    textSecondary = Color(0xFF8A97AB),
    textDim = Color(0xFF4E6B9E),
    accent = Color(0xFF2E7DF6),
    accentAlt = Color(0xFF22D3EE),
    accentSoft = Color(0xFF7FB2FF),
    statusGreen = Color(0xFF34D399),
    statusAmber = Color(0xFFFBBF24),
    statusRed = Color(0xFFF87171),
    statusNeutral = Color(0xFF2A3550),
    violet = Color(0xFF8B93F8),
    danger = Color(0xFFEC4899),
    onDanger = Color(0xFFFFFFFF),
    categoryColors = DarkCategoryColors,
    categoryChartColors = DarkCategoryChartColors,
    chartOther = Color(0xFF44506B),
    isLight = false,
)

/**
 * 明るい面。Zscaler Experience Center の画面から採った色を土台にしている
 * （ナビの青 #1D56CE、面は白、罫線 #E3E8EF）。
 *
 * 暗い面の色をそのまま薄い背景に置くことはできない。文字色は明暗を入れ替えるだけで足りるが、
 * カテゴリーの識別色は白の上で沈む・飛ぶので、明るい面用に取り直してある（下記）。
 */
val ZLightColors = ZColorScheme(
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFF4F7FB),
    surfaceHigh = Color(0xFFE8EEF6),
    surfaceLow = Color(0xFFFAFCFE),
    outline = Color(0xFFE3E8EF),
    outlineStrong = Color(0xFFC8D3E0),
    textPrimary = Color(0xFF0E1A2B),
    textSecondary = Color(0xFF4A5A73),
    textDim = Color(0xFF76849B),
    accent = Color(0xFF1D56CE),
    accentAlt = Color(0xFF0E7490),
    accentSoft = Color(0xFF17408F),
    statusGreen = Color(0xFF0F7B4F),
    statusAmber = Color(0xFFB45309),
    statusRed = Color(0xFFC2312C),
    statusNeutral = Color(0xFFC9D2DF),
    violet = Color(0xFF4F46E5),
    danger = Color(0xFFDB2777),
    onDanger = Color(0xFFFFFFFF),
    categoryColors = LightCategoryColors,
    categoryChartColors = LightCategoryChartColors,
    chartOther = Color(0xFFA9B4C4),
    isLight = true,
)

val LocalZColors = staticCompositionLocalOf { ZDarkColors }

/**
 * 現在のテーマの配色。
 *
 * 参照の形（`ZColors.TextPrimary`）を変えずに暗い面と明るい面を入れ替えられるよう、
 * 値ではなく CompositionLocal の読み出しにしてある。Composable の外（DrawScope の中など）
 * からは読めないので、その場合は呼び出し側で受け取ってから渡すこと。
 */
object ZColors {
    val Background: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.background
    val Surface: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.surface
    val SurfaceHigh: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.surfaceHigh
    val SurfaceLow: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.surfaceLow
    val Outline: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.outline
    val OutlineStrong: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.outlineStrong
    val TextPrimary: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.textPrimary
    val TextSecondary: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.textSecondary
    val TextDim: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.textDim
    val Accent: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.accent
    val AccentAlt: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.accentAlt
    val AccentSoft: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.accentSoft
    val StatusGreen: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.statusGreen
    val StatusAmber: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.statusAmber
    val StatusRed: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.statusRed
    val StatusNeutral: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.statusNeutral
    val Violet: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.violet
    val Danger: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.danger
    val OnDanger: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.onDanger
    val ChartOther: Color @Composable @ReadOnlyComposable get() = LocalZColors.current.chartOther

    val CategoryColors: List<Color> @Composable @ReadOnlyComposable get() = LocalZColors.current.categoryColors
    val CategoryChartColors: List<Color>
        @Composable @ReadOnlyComposable get() = LocalZColors.current.categoryChartColors
}
