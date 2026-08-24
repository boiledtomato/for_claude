package com.example.zlauncher.ui.home

import android.graphics.Rect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.ui.graphics.ImageBitmap
import com.example.zlauncher.data.apps.AppIconLoader
import com.example.zlauncher.data.apps.AppSorter
import com.example.zlauncher.data.apps.FavoritesRepository
import com.example.zlauncher.data.apps.InstalledAppRepository
import com.example.zlauncher.data.apps.LauncherAppsDataSource
import com.example.zlauncher.data.dashboard.DashboardDataSource
import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.domain.model.AppSortOrder
import com.example.zlauncher.domain.model.CardStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    installedApps: InstalledAppRepository,
    dashboardDataSource: DashboardDataSource,
    private val iconLoader: AppIconLoader,
    private val favoritesRepository: FavoritesRepository,
    private val preferences: LauncherPreferencesRepository,
    private val launcherApps: LauncherAppsDataSource,
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = combine(
        installedApps.apps,
        favoritesRepository.favorites,
        preferences.state,
        dashboardDataSource.snapshot(),
    ) { apps, favorites, state, snapshot ->
        HomeUiState(
            loading = false,
            apps = AppSorter.sort(apps, state.sortOrder),
            favorites = favorites,
            sortOrder = state.sortOrder,
            favoriteSlots = favoritesRepository.maxSlots,
            statusChips = listOf(
                HomeStatusChip("保護中", snapshot.securityStatus),
                HomeStatusChip("トンネル 接続済", CardStatus.GREEN),
                HomeStatusChip("${snapshot.latencyMs} ms", CardStatus.AMBER),
            ),
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), HomeUiState())

    /** アイコンは一覧描画のあとから解決する。キャッシュ済みなら即返る */
    suspend fun icon(entry: AppEntry): ImageBitmap? = iconLoader.load(entry.componentName)

    fun launch(entry: AppEntry, sourceBounds: Rect?) {
        launcherApps.launch(entry.componentName, entry.user, sourceBounds)
    }

    fun openAppInfo(entry: AppEntry, sourceBounds: Rect?) {
        launcherApps.openAppDetails(entry.componentName, entry.user, sourceBounds)
    }

    fun addFavorite(entry: AppEntry) = viewModelScope.launch {
        favoritesRepository.add(entry.packageName)
    }

    fun removeFavorite(entry: AppEntry) = viewModelScope.launch {
        favoritesRepository.remove(entry.packageName)
    }

    fun moveFavorite(from: Int, to: Int) = viewModelScope.launch {
        favoritesRepository.move(from, to)
    }

    fun setSortOrder(order: AppSortOrder) = viewModelScope.launch {
        preferences.update { it.copy(sortOrder = order) }
    }
}
