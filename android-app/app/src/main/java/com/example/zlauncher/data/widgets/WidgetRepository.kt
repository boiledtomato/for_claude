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

    suspend fun add(appWidgetId: Int, heightDp: Int) = preferences.update { state ->
        if (state.widgets.any { it.appWidgetId == appWidgetId }) {
            state
        } else {
            state.copy(widgets = state.widgets + WidgetPlacement(appWidgetId, heightDp))
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
