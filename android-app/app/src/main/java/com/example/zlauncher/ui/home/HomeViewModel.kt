package com.example.zlauncher.ui.home

import android.graphics.Rect
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zlauncher.data.apps.AppIconLoader
import com.example.zlauncher.data.apps.AppSorter
import com.example.zlauncher.data.apps.FavoritesRepository
import com.example.zlauncher.data.apps.InstalledAppRepository
import com.example.zlauncher.data.apps.LauncherAppsDataSource
import com.example.zlauncher.data.dashboard.DashboardDataSource
import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import com.example.zlauncher.data.widgets.WidgetRepository
import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.domain.model.AppSortOrder
import com.example.zlauncher.domain.model.CardStatus
import com.example.zlauncher.domain.model.WidgetPlacement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    private val widgetRepository: WidgetRepository,
    private val preferences: LauncherPreferencesRepository,
    private val launcherApps: LauncherAppsDataSource,
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    val widgets: StateFlow<List<WidgetPlacement>> = widgetRepository.widgets
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val uiState: StateFlow<HomeUiState> = combine(
        installedApps.apps,
        favoritesRepository.favorites,
        preferences.state,
        dashboardDataSource.snapshot(),
        _query,
    ) { apps, favorites, state, snapshot, query ->
        val sorted = AppSorter.sort(apps, state.sortOrder)
        HomeUiState(
            loading = false,
            apps = if (query.isBlank()) sorted else sorted.filter { it.matches(query) },
            totalAppCount = sorted.size,
            query = query,
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
    suspend fun icon(entry: AppEntry): ImageBitmap? = iconLoader.load(entry)

    fun setQuery(value: String) {
        _query.value = value
    }

    fun clearQuery() {
        _query.value = ""
    }

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

    fun removeWidget(appWidgetId: Int) = viewModelScope.launch {
        widgetRepository.remove(appWidgetId)
    }

    fun setSortOrder(order: AppSortOrder) = viewModelScope.launch {
        preferences.update { it.copy(sortOrder = order) }
    }
}

/** 検索はラベルの部分一致。日本語も英字も同じ扱いでよい */
private fun AppEntry.matches(query: String): Boolean =
    label.contains(query, ignoreCase = true) || packageName.contains(query, ignoreCase = true)
