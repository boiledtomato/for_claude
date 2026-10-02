package com.example.zlauncher.ui.console

/**
 * レール上でどこへ落とすかの計算。
 *
 * 画面から切り離してあるのは、ここが**行の高さがばらつく**前提の計算だから。
 * カテゴリー名が 2 行に折り返す行は 1 行の行より高く、固定値で割ると長い名前の
 * 隣で 1 つぶんずれる。目で見て気付きにくいので、値で押さえておく。
 */
internal object RailReorder {

    /**
     * [start] から [dy] だけ動かしたときの落下先と、そこまでに詰めた距離。
     *
     * [Drop.consumed] を返すのは、指を離したあとの着地に使うため。並べ替えを確定すると
     * 行はその距離ぶん動くので、残り（dy - consumed）だけ戻せば、指を離した位置から
     * 滑らかに収まる。
     */
    data class Drop(val index: Int, val consumed: Float)

    fun drop(start: Int, dy: Float, heights: List<Float>): Drop {
        if (start !in heights.indices) return Drop(start, 0f)
        var index = start
        var consumed = 0f
        if (dy > 0f) {
            while (index + 1 <= heights.lastIndex) {
                val step = heights[index + 1]
                // 隣の行の半分を越えたら入れ替わり。半分未満なら手前に留まる
                if (dy - consumed > step / 2f) {
                    consumed += step
                    index++
                } else {
                    break
                }
            }
        } else {
            while (index - 1 >= 0) {
                val step = heights[index - 1]
                if (consumed - dy > step / 2f) {
                    consumed -= step
                    index--
                } else {
                    break
                }
            }
        }
        return Drop(index, consumed)
    }

    /**
     * 掴んでいない行のずれ。掴んだ行が抜けた穴を、通り道の行が順に埋める。
     *
     * 並べ替えは指を離すまで確定させない（途中で確定させると行と要素の対応が変わり、
     * 進行中のジェスチャーごと作り直されて指が外れる）。そのぶん、動いて見せるのは
     * ここで計算した見た目のずれだけになる。
     */
    fun shift(index: Int, start: Int, target: Int, heights: List<Float>): Float {
        if (index == start || start == target) return 0f
        val dragged = heights.getOrNull(start) ?: return 0f
        return when {
            target > start && index in (start + 1)..target -> -dragged
            target < start && index in target until start -> dragged
            else -> 0f
        }
    }
}
