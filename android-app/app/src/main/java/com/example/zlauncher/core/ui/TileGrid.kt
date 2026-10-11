package com.example.zlauncher.core.ui

/**
 * アイコンを並べる面の列数。**マルチウィンドウのために、数ではなく幅で決める。**
 *
 * 分割画面・フリーフォーム・DeX では、この面の幅が端末の画面幅と関係なく決まる。
 * 列数を 4 で固定すると、狭い窓では 1 列が 56dp のアイコンより細くなって重なる
 * （窓幅 240dp のカテゴリー面なら 1 列 32.5dp）。
 *
 * かといって `GridCells.Adaptive` に丸投げすると、全画面のほうが変わってしまう ―
 * 最小幅だけで数えるので、幅の広い端末では 5 列・6 列に増える。ここでやりたいのは
 * 「広いときは今までどおり、狭いときだけ減らす」なので、**上限付きで数える**。
 *
 * [MIN_CELL_DP] は 56dp のアイコン＋左右 2dp。この値だと短辺 360dp の電話機は
 * 1 列 62.5dp で 4 列を保ち、320dp 以下に初めて 3 列へ落ちる ― つまり実在する
 * 電話機の全画面では今までと 1 枚も変わらない。
 */
object TileGrid {

    /** 広いときの列数。これより増やさない（全画面の見た目を変えないため） */
    const val MAX_COLUMNS = 4

    /** 1 列の下限。アイコン 56dp が収まる最小 */
    const val MIN_CELL_DP = 60f

    /**
     * @param availableDp 内側の余白を引いたあとの、並べられる幅
     * @param gapDp 列のあいだの隙間
     * @return 1 以上 [maxColumns] 以下の列数
     */
    fun columns(
        availableDp: Float,
        gapDp: Float = 2f,
        minCellDp: Float = MIN_CELL_DP,
        maxColumns: Int = MAX_COLUMNS,
    ): Int {
        if (availableDp <= 0f || minCellDp <= 0f) return 1
        // 隙間は列数より 1 つ少ないので、両方に 1 つぶん足して割ると素直に数えられる
        val fits = ((availableDp + gapDp) / (minCellDp + gapDp)).toInt()
        return fits.coerceIn(1, maxColumns)
    }
}
