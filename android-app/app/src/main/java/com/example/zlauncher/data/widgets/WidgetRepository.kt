package com.example.zlauncher.data.widgets

import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import com.example.zlauncher.data.prefs.LauncherState
import com.example.zlauncher.domain.model.WidgetPlacement
import com.example.zlauncher.domain.model.WidgetSheet
import com.example.zlauncher.domain.model.WidgetSheets
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WidgetRepository @Inject constructor(
    private val preferences: LauncherPreferencesRepository,
    private val host: WidgetHostController,
) {
    val widgets: Flow<List<WidgetPlacement>> = preferences.state.map { it.widgets }

    /** 左右のフリックで行き来するシート */
    val sheets: Flow<List<WidgetSheet>> = preferences.state.map { it.widgetSheets }

    /** いま見ているシート。追加先でもある */
    val activeSheet: Flow<String> = preferences.state.map { it.activeWidgetSheet }

    /**
     * 追加は**いま見ているシート**へ。シートが 1 枚も無ければ作る
     * （ウィジェットだけ増えて置き場所が無い、という状態を作らない）。
     */
    suspend fun add(appWidgetId: Int, heightDp: Int, widthSpan: Int) = preferences.update { state ->
        if (state.widgets.any { it.appWidgetId == appWidgetId }) {
            state
        } else {
            val normalized = normalize(state)
            val target = normalized.activeWidgetSheet.ifBlank { normalized.widgetSheets.firstOrNull()?.id }
            val sheets = if (target == null) listOf(WidgetSheet(newId())) else normalized.widgetSheets
            val sheetId = target ?: sheets.first().id
            val placement = WidgetPlacement(
                appWidgetId = appWidgetId,
                heightDp = WidgetPlacement.clampHeight(heightDp),
                widthSpan = WidgetPlacement.clampSpan(widthSpan),
                sheetId = sheetId,
            )
            normalized.copy(
                widgetSheets = sheets,
                activeWidgetSheet = sheetId,
                widgets = normalized.widgets + placement,
            )
        }
    }

    /** 画面を開いたときに 1 度。旧データのウィジェットを 1 枚目へ寄せる */
    suspend fun ensureSheets() = preferences.update { normalize(it) }

    /** 新しいシートを足して、そこへ移る */
    suspend fun addSheet() = preferences.update { state ->
        val sheet = WidgetSheet(newId())
        state.copy(
            widgetSheets = state.widgetSheets + sheet,
            activeWidgetSheet = sheet.id,
        )
    }

    suspend fun setActiveSheet(sheetId: String) = preferences.update { state ->
        if (state.activeWidgetSheet == sheetId) state else state.copy(activeWidgetSheet = sheetId)
    }

    /**
     * 空のシートを片付ける。**いま居るシートだけは空でも残す** ―
     * 追加した直後のシートがその場で消えないように。
     */
    suspend fun pruneEmptySheets(keep: String?) = preferences.update { state ->
        val kept = WidgetSheets.pruneEmpty(state.widgetSheets, state.widgets, keep)
        if (kept.size == state.widgetSheets.size) {
            state
        } else {
            state.copy(
                widgetSheets = kept,
                activeWidgetSheet = kept.firstOrNull { it.id == state.activeWidgetSheet }?.id
                    ?: kept.firstOrNull()?.id.orEmpty(),
            )
        }
    }

    private fun normalize(state: LauncherState): LauncherState {
        val result = WidgetSheets.normalize(
            sheets = state.widgetSheets,
            widgets = state.widgets,
            active = state.activeWidgetSheet,
            newId = ::newId,
        )
        return if (
            result.sheets == state.widgetSheets &&
            result.widgets == state.widgets &&
            result.active == state.activeWidgetSheet
        ) {
            state
        } else {
            state.copy(
                widgetSheets = result.sheets,
                widgets = result.widgets,
                activeWidgetSheet = result.active,
            )
        }
    }

    private fun newId(): String = UUID.randomUUID().toString()

    /** 高さの変更。範囲外の値が入るとホームが描けなくなるので、必ず丸める */
    suspend fun setHeight(appWidgetId: Int, heightDp: Int) = preferences.update { state ->
        state.copy(
            widgets = state.widgets.map {
                if (it.appWidgetId == appWidgetId) {
                    it.copy(heightDp = WidgetPlacement.clampHeight(heightDp))
                } else {
                    it
                }
            }
        )
    }

    /** 幅（列数）の変更。丸めるのは高さと同じ理由 */
    suspend fun setSpan(appWidgetId: Int, widthSpan: Int) = preferences.update { state ->
        state.copy(
            widgets = state.widgets.map {
                if (it.appWidgetId == appWidgetId) {
                    it.copy(widthSpan = WidgetPlacement.clampSpan(widthSpan))
                } else {
                    it
                }
            }
        )
    }

    /**
     * 並び順を 1 つ動かす。
     *
     * 行の詰め方は順序だけで決まるので、隣に並べたい 2 つを寄せる手段がこれになる。
     */
    suspend fun move(appWidgetId: Int, delta: Int) = preferences.update { state ->
        val placement = state.widgets.firstOrNull { it.appWidgetId == appWidgetId } ?: return@update state
        val onSheet = WidgetSheets.widgetsOn(state.widgets, placement.sheetId)
        val from = onSheet.indexOfFirst { it.appWidgetId == appWidgetId }
        val to = from + delta
        if (to !in onSheet.indices) {
            state
        } else {
            state.copy(widgets = WidgetSheets.moveWithinSheet(state.widgets, placement.sheetId, from, to))
        }
    }

    /**
     * ドラッグ並べ替え用。位置そのものを指定する。
     *
     * **位置はシートの中での番号。** 保存しているのは 1 本の並びなので、
     * [WidgetSheets.moveWithinSheet] が全体の何番目かに引き直す。
     */
    suspend fun moveTo(sheetId: String, fromIndex: Int, toIndex: Int) = preferences.update { state ->
        state.copy(widgets = WidgetSheets.moveWithinSheet(state.widgets, sheetId, fromIndex, toIndex))
    }

    suspend fun remove(appWidgetId: Int) = remove(listOf(appWidgetId))

    /**
     * まとめて外す。
     *
     * **書き込みは 1 回。** 1 件ずつ [preferences] を更新すると、選んだ枚数だけ設定ファイルへ
     * 書き込みが走り、そのたびに一覧が再構成されて画面が段階的に崩れていく。
     * ホストへの id の返却は 1 件ずつでよい（こちらはファイルではない）。
     */
    suspend fun remove(appWidgetIds: Collection<Int>) {
        if (appWidgetIds.isEmpty()) return
        val drop = appWidgetIds.toSet()
        drop.forEach { host.deleteAppWidgetId(it) }
        preferences.update { state ->
            state.copy(widgets = state.widgets.filterNot { it.appWidgetId in drop })
        }
    }

    /**
     * プロバイダが消えた（アプリがアンインストールされた等）ウィジェットを掃除する。
     * 残しておくと描画できない枠が永遠にホームに残る。
     */
    suspend fun pruneMissing() {
        val current = preferences.state.first().widgets
        val stale = current.filter { host.providerInfo(it.appWidgetId) == null }
        if (stale.isEmpty()) return
        stale.forEach { host.deleteAppWidgetId(it.appWidgetId) }
        preferences.update { state ->
            state.copy(widgets = state.widgets.filterNot { placement -> stale.any { it.appWidgetId == placement.appWidgetId } })
        }
    }
}
