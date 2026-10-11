package com.example.zlauncher.ui.widgets

import com.example.zlauncher.domain.model.WidgetPlacement

/**
 * 置いたウィジェットを行に詰める。
 *
 * 左から順に詰め、残り列に入らないものだけ次の行へ送る。**空いた列は空いたまま**に
 * しておき、後ろのウィジェットを繰り上げて埋めることはしない ― 並び順は利用者が
 * 決めたものなので、勝手に入れ替えると「動かしたはずの位置に戻らない」ことになる。
 * 隣に並べたいものは順序を動かして寄せる。
 */
internal object WidgetFlow {

    data class Row(
        val widgets: List<WidgetPlacement>,
        /** 行に残った列数。0 なら埋まっている */
        val freeSpan: Int,
    )

    fun rows(widgets: List<WidgetPlacement>, columns: Int = WidgetPlacement.COLUMNS): List<Row> {
        val rows = mutableListOf<Row>()
        var current = mutableListOf<WidgetPlacement>()
        var used = 0

        fun flush() {
            if (current.isNotEmpty()) {
                rows += Row(current.toList(), columns - used)
                current = mutableListOf()
                used = 0
            }
        }

        widgets.forEach { placement ->
            val span = WidgetPlacement.clampSpan(placement.widthSpan).coerceAtMost(columns)
            if (used + span > columns) flush()
            current += placement
            used += span
        }
        flush()
        return rows
    }
}
