package com.example.zlauncher.core.ui

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * コンソール左レールの寸法。**窓の幅から決める。**
 *
 * レールは 84dp 固定だった。電話機（短辺 360-430dp）では画面の 2 割を占めて釣り合うが、
 * 折りたたみ端末を**開くと窓幅がほぼ倍になるのにレールだけ据え置き**になり、占める割合が
 * 半分以下に落ちる。実機（Galaxy Z Fold、開いて約 830dp）では、名前が 2 行に詰まって
 * 「Generative AI and ML Applic…」のように切れ、押す的も小さいままになっていた。
 *
 * **畳んだときの見た目は 1px も変えない。** [COMPACT] は今までの値そのままで、
 * 広い窓にだけ [EXPANDED] を当てる。利用者からは「畳んでいるときは違和感がない」と
 * 報告があったので、そこを動かす理由が無い。
 *
 * 分かれ目は 600dp ― Android 自身が大画面として扱う境目で、このアプリが向きの固定で
 * 無視される境目でもある。**窓の幅で測るのが肝**で、画面の幅ではない: 開いた状態でも
 * 分割画面なら片側は 415dp ほどになり、そこは畳んだときと同じ狭さだから。
 */
object RailMetrics {

    /** これ以上広い窓は「開いた」扱い。Android が大画面とみなす境目に合わせる */
    const val EXPANDED_FROM_DP = 600f

    /**
     * レールの寸法一式。
     *
     * 倍率ではなく**実寸で持つ**。倍率にすると、どの値がいくつになるのかが呼び出し側に
     * 散らばってテストで押さえられない ― 直したいのはまさにその「散らばり」だった。
     */
    data class Metrics(
        val widthDp: Float,
        /** 項目名。2 行で折り返す */
        val labelSp: Float,
        /** 見出し（Pinned / Categories） */
        val headerSp: Float,
        /** 見出しの開閉マーク */
        val chevronSp: Float,
        /** カテゴリー追加の ＋ */
        val addGlyphSp: Float,
        /** 配色切り替えの ◐ / ☀ / ☾ */
        val themeGlyphSp: Float,
        /** Apps・Overview などの目印の図形 */
        val indicatorDp: Float,
        /** ピン留めしたアプリのアイコン */
        val pinIconDp: Float,
        /** 項目の上下の余白。押す的の高さを決める */
        val itemPaddingDp: Float,
        /** 目印と名前のあいだ */
        val labelGapDp: Float,
        /** 区切り線の幅 */
        val dividerWidthDp: Float,
        /** 選択中を示す左端の縦棒 */
        val selectedBarHeightDp: Float,
        val selectedBarWidthDp: Float,
    )

    /** 電話機と、分割画面の片側。**今までの値そのまま** */
    val COMPACT = Metrics(
        widthDp = 84f,
        labelSp = 10f,
        headerSp = 9f,
        chevronSp = 11f,
        addGlyphSp = 18f,
        themeGlyphSp = 16f,
        indicatorDp = 14f,
        pinIconDp = 48f,
        itemPaddingDp = 10f,
        labelGapDp = 6f,
        dividerWidthDp = 36f,
        selectedBarHeightDp = 26f,
        selectedBarWidthDp = 3f,
    )

    /**
     * 開いた折りたたみ端末とタブレット。
     *
     * 幅はちょうど 2 倍。文字はそこまで上げず 1.35 倍前後に留めてある ― 幅と同じ倍率で
     * 伸ばすと、名前だけがペイン側の見出しより大きくなって主従が逆になる。
     * アイコンは 48 → 64dp（1.33 倍）。2 倍にすると、レールがアプリの一覧に見えてくる。
     */
    val EXPANDED = Metrics(
        widthDp = 168f,
        labelSp = 13.5f,
        headerSp = 12f,
        chevronSp = 14f,
        addGlyphSp = 24f,
        themeGlyphSp = 21f,
        indicatorDp = 20f,
        pinIconDp = 64f,
        itemPaddingDp = 14f,
        labelGapDp = 8f,
        dividerWidthDp = 72f,
        selectedBarHeightDp = 34f,
        selectedBarWidthDp = 4f,
    )

    fun forWindowWidth(windowWidthDp: Float): Metrics =
        if (windowWidthDp >= EXPANDED_FROM_DP) EXPANDED else COMPACT
}

/**
 * レールの寸法を配る。
 *
 * レールの部品は 10 個以上あり、どれも寸法を 1-2 個ずつ使う。引数で配ると呼び出し側が
 * 寸法の運び屋だらけになるので、配色（`LocalZColors`）やアイコンの色味
 * （`LocalIconAdjust`）と同じく CompositionLocal にしてある。
 *
 * `static` なのは、変わるのが端末を開閉したときだけだから ― 読んだ箇所を記録しておく
 * 必要が無いぶん軽い。
 */
val LocalRailMetrics = staticCompositionLocalOf { RailMetrics.COMPACT }
