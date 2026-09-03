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
import com.example.zlauncher.data.device.DeviceMetricsRepository
import com.example.zlauncher.data.device.NetworkKind
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
    metricsRepository: DeviceMetricsRepository,
    private val iconLoader: AppIconLoader,
    private val favoritesRepository: FavoritesRepository,
    private val widgetRepository: WidgetRepository,
    private val preferences: LauncherPreferencesRepository,
    private val launcherApps: LauncherAppsDataSource,
) : ViewModel() {

    private var themedIcons = false

    init {
        viewModelScope.launch {
            preferences.state.collect { themedIcons = it.themedIcons }
        }
    }

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    val widgets: StateFlow<List<WidgetPlacement>> = widgetRepository.widgets
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val uiState: StateFlow<HomeUiState> = combine(
        installedApps.apps,
        favoritesRepository.favorites,
        preferences.state,
        metricsRepository.metrics,
        _query,
    ) { apps, favorites, state, metrics, query ->
        val sorted = AppSorter.sort(apps, state.sortOrder)
        HomeUiState(
            loading = false,
            apps = if (query.isBlank()) sorted else sorted.filter { it.matches(query) },
            totalAppCount = sorted.size,
            query = query,
            favorites = favorites,
            sortOrder = state.sortOrder,
            favoriteSlots = favoritesRepository.maxSlots,
            // ダミーではなく実際の状態を出す
            statusChips = listOf(
                HomeStatusChip(
                    label = if (metrics.vpnActive) "VPN on" else "VPN off",
                    status = if (metrics.vpnActive) CardStatus.GREEN else CardStatus.NEUTRAL,
                ),
                HomeStatusChip(
                    label = when (metrics.network) {
                        NetworkKind.WIFI -> "Wi-Fi"
                        NetworkKind.CELLULAR -> "Mobile"
                        NetworkKind.ETHERNET -> "Ethernet"
                        NetworkKind.OTHER -> "Connected"
                        NetworkKind.NONE -> "Offline"
                    },
                    status = when {
                        metrics.network == NetworkKind.NONE -> CardStatus.RED
                        !metrics.networkValidated -> CardStatus.AMBER
                        else -> CardStatus.GREEN
                    },
                ),
                HomeStatusChip(
                    label = "${metrics.batteryPercent}%",
                    status = when {
                        metrics.batteryCharging || metrics.batteryPercent >= 50 -> CardStatus.GREEN
                        metrics.batteryPercent >= 20 -> CardStatus.AMBER
                        else -> CardStatus.RED
                    },
                ),
            ),
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), HomeUiState())

    /** アイコンは一覧描画のあとから解決する。キャッシュ済みなら即返る */
    suspend fun icon(entry: AppEntry): ImageBitmap? = iconLoader.load(entry, themedIcons)

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

    fun setWidgetHeight(appWidgetId: Int, heightDp: Int) = viewModelScope.launch {
        widgetRepository.setHeight(appWidgetId, heightDp)
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
