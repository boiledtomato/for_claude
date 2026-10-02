package com.example.zlauncher.domain.model

import kotlinx.serialization.Serializable

/**
 * ウィジェットを載せる 1 枚。左右のフリックで行き来する。
 *
 * 1 列に積み上げるだけだと、置くほど下に伸びて下端のものが遠くなる。枚数で分けられると、
 * 用途ごとに（仕事用・生活用のように）まとめて置ける。
 *
 * 名前は持たない。付けられるようにすると必ず「名前を決める」手間が要るが、枚数は普通
 * 数枚で、点の並び（ページインジケーター）だけで十分見分けられる。
 */
@Serializable
data class WidgetSheet(val id: String)

/**
 * シートと、その上のウィジェットの整合を取る計算。**Android に依存しない**ので
 * そのままテストにかけられる。
 *
 * ここが壊れると「置いたはずのウィジェットがどのシートにも出ない」「追加した直後の空の
 * シートが消える」といった形で出る。どちらも画面を見て気付くより、規則として書いて
 * 固定するほうが早い。
 */
object WidgetSheets {

    data class Normalized(
        val sheets: List<WidgetSheet>,
        val widgets: List<WidgetPlacement>,
        val active: String,
    )

    /**
     * どのシートにも属していないウィジェットを 1 枚目へ寄せ、シートの一覧と現在のシートを
     * 辻褄の合う状態にする。
     *
     * シートを導入する前に置いたウィジェットは [WidgetPlacement.sheetId] が空なので、
     * そのままだとどこにも表示されない。移行はここで吸収する（保存形式の版を上げずに済む）。
     */
    fun normalize(
        sheets: List<WidgetSheet>,
        widgets: List<WidgetPlacement>,
        active: String,
        newId: () -> String,
    ): Normalized {
        val known = sheets.map { it.id }.toSet()
        val orphans = widgets.filter { it.sheetId.isBlank() || it.sheetId !in known }

        var result = sheets
        if (orphans.isNotEmpty() && result.isEmpty()) {
            result = listOf(WidgetSheet(newId()))
        }

        val home = result.firstOrNull()?.id
        val placed = if (home == null) {
            widgets
        } else {
            widgets.map { if (it.sheetId.isBlank() || it.sheetId !in result.map { s -> s.id }.toSet()) it.copy(sheetId = home) else it }
        }

        val activeId = when {
            result.any { it.id == active } -> active
            else -> result.firstOrNull()?.id.orEmpty()
        }
        return Normalized(result, placed, activeId)
    }

    /**
     * 空のシートを片付ける。**[keep] だけは空でも残す。**
     *
     * 残さないと、追加した直後（まだ 1 つも置いていない）のシートがその場で消えてしまう。
     * 片付けるのは「そのシートから離れたとき」で、離れた先が [keep] になる。
     */
    fun pruneEmpty(
        sheets: List<WidgetSheet>,
        widgets: List<WidgetPlacement>,
        keep: String?,
    ): List<WidgetSheet> {
        val used = widgets.map { it.sheetId }.toSet()
        return sheets.filter { it.id in used || it.id == keep }
    }

    /** そのシートに載っているぶんだけ、並び順のまま取り出す */
    fun widgetsOn(widgets: List<WidgetPlacement>, sheetId: String): List<WidgetPlacement> =
        widgets.filter { it.sheetId == sheetId }

    /**
     * シートの中での並べ替えを、全体の並びの上での入れ替えに直す。
     *
     * 保存しているのは 1 本の並びなので、シート内の 3 番目と 5 番目を入れ替えるには、
     * その 2 件が全体で何番目なのかを引き直す必要がある。
     */
    fun moveWithinSheet(
        widgets: List<WidgetPlacement>,
        sheetId: String,
        fromIndex: Int,
        toIndex: Int,
    ): List<WidgetPlacement> {
        val onSheet = widgetsOn(widgets, sheetId)
        if (fromIndex !in onSheet.indices || toIndex !in onSheet.indices || fromIndex == toIndex) return widgets

        val reorderedSheet = onSheet.toMutableList().apply { add(toIndex, removeAt(fromIndex)) }

        // 全体の並びのうち、このシートが使っている位置だけを新しい順で埋め直す。
        // 他のシートのウィジェットは 1 件も動かさない
        val slots = widgets.withIndex().filter { it.value.sheetId == sheetId }.map { it.index }
        val result = widgets.toMutableList()
        slots.forEachIndexed { slot, position -> result[position] = reorderedSheet[slot] }
        return result
    }
}
