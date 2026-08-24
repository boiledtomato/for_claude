package com.example.zlauncher.data.apps

import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import com.example.zlauncher.data.prefs.LauncherState
import com.example.zlauncher.domain.model.AppEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import javax.inject.Singleton

/**
 * ドック（お気に入り）。保存するのは packageName の順序リスト 1 本だけ。
 *
 * インストール済みアプリの Flow と combine して解決しているので、アンインストールされた
 * アプリは自動的にドックから落ちる。削除処理を別に書く必要はない。
 */
@Singleton
class FavoritesRepository @Inject constructor(
    private val preferences: LauncherPreferencesRepository,
    installedApps: InstalledAppRepository,
) {
    val favorites: Flow<List<AppEntry>> =
        combine(preferences.state, installedApps.apps) { state, apps ->
            // 同じパッケージが個人用と仕事用の両方にありうる。ドックは個人用のみを解決する
            val byPackage = apps.filterNot { it.isWorkProfile }.associateBy { it.packageName }
            state.favorites.mapNotNull { byPackage[it] }
        }

    val maxSlots: Int get() = LauncherState.MAX_FAVORITES

    suspend fun add(packageName: String) = preferences.update { state ->
        if (packageName in state.favorites || state.favorites.size >= LauncherState.MAX_FAVORITES) {
            state
        } else {
            state.copy(favorites = state.favorites + packageName)
        }
    }

    suspend fun remove(packageName: String) = preferences.update { state ->
        state.copy(favorites = state.favorites - packageName)
    }

    suspend fun move(from: Int, to: Int) = preferences.update { state ->
        val list = state.favorites.toMutableList()
        if (from !in list.indices || to !in list.indices) return@update state
        list.add(to, list.removeAt(from))
        state.copy(favorites = list)
    }
}
