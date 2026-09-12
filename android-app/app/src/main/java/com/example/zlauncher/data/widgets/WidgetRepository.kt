package com.example.zlauncher.data.widgets

import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import com.example.zlauncher.domain.model.WidgetPlacement
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WidgetRepository @Inject constructor(
    private val preferences: LauncherPreferencesRepository,
    private val host: WidgetHostController,
) {
    val widgets: Flow<List<WidgetPlacement>> = preferences.state.map { it.widgets }

    suspend fun add(appWidgetId: Int, heightDp: Int, widthSpan: Int) = preferences.update { state ->
        if (state.widgets.any { it.appWidgetId == appWidgetId }) {
            state
        } else {
            val placement = WidgetPlacement(
                appWidgetId = appWidgetId,
                heightDp = WidgetPlacement.clampHeight(heightDp),
                widthSpan = WidgetPlacement.clampSpan(widthSpan),
            )
            state.copy(widgets = state.widgets + placement)
        }
    }

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
        val from = state.widgets.indexOfFirst { it.appWidgetId == appWidgetId }
        val to = from + delta
        if (from < 0 || to !in state.widgets.indices) {
            state
        } else {
            val reordered = state.widgets.toMutableList()
            reordered.add(to, reordered.removeAt(from))
            state.copy(widgets = reordered)
        }
    }

    /** ドラッグ並べ替え用。位置そのものを指定する */
    suspend fun moveTo(fromIndex: Int, toIndex: Int) = preferences.update { state ->
        if (fromIndex !in state.widgets.indices || toIndex !in state.widgets.indices) {
            state
        } else {
            val reordered = state.widgets.toMutableList()
            reordered.add(toIndex, reordered.removeAt(fromIndex))
            state.copy(widgets = reordered)
        }
    }

    suspend fun remove(appWidgetId: Int) {
        host.deleteAppWidgetId(appWidgetId)
        preferences.update { state ->
            state.copy(widgets = state.widgets.filterNot { it.appWidgetId == appWidgetId })
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
