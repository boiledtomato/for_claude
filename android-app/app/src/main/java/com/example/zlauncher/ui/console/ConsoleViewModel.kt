package com.example.zlauncher.ui.console

import android.graphics.Rect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zlauncher.data.apps.AppIconLoader
import com.example.zlauncher.data.apps.AppSorter
import com.example.zlauncher.data.apps.CategoryRepository
import com.example.zlauncher.data.apps.CategoryWithApps
import com.example.zlauncher.data.apps.InstalledAppRepository
import com.example.zlauncher.data.apps.LauncherAppsDataSource
import com.example.zlauncher.data.dashboard.DashboardLayoutRepository
import com.example.zlauncher.data.device.DeviceMetricsRepository
import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.domain.model.AppSortOrder
import com.example.zlauncher.domain.model.CardLayout
import com.example.zlauncher.domain.model.CardSpan
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConsoleViewModel @Inject constructor(
    private val layoutRepository: DashboardLayoutRepository,
    private val categoryRepository: CategoryRepository,
    private val preferences: LauncherPreferencesRepository,
    private val iconLoader: AppIconLoader,
    private val launcherApps: LauncherAppsDataSource,
    installedApps: InstalledAppRepository,
    metricsRepository: DeviceMetricsRepository,
) : ViewModel() {

    // ---- 実測値 -------------------------------------------------------------

    val snapshot: StateFlow<ConsoleSnapshot> = combine(
        metricsRepository.metrics,
        installedApps.apps,
    ) { metrics, apps ->
        ConsoleSnapshot(
            metrics = metrics,
            appCount = apps.count { !it.isWorkProfile },
            workAppCount = apps.count { it.isWorkProfile },
            recentApps = apps.sortedByDescending { it.firstInstallTime }.take(RECENT_APP_COUNT),
            loaded = true,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(2_000), ConsoleSnapshot())

    // ---- 左レール -----------------------------------------------------------

    var pane by mutableStateOf<ConsolePane>(ConsolePane.Overview)
        private set

    val categories: StateFlow<List<CategoryWithApps>> = categoryRepository.categories
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val pinnedApps: StateFlow<List<AppEntry>> = categoryRepository.pinnedApps
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val pinnedSlots: Int get() = categoryRepository.pinnedSlots

    val allApps: StateFlow<List<AppEntry>> = combine(
        installedApps.apps,
        preferences.state,
    ) { apps, state -> AppSorter.sort(apps.filterNot { it.isWorkProfile }, state.sortOrder) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val sortOrder: StateFlow<AppSortOrder> = preferences.state
        .map { it.sortOrder }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), AppSortOrder.LABEL)

    fun select(target: ConsolePane) {
        pane = target
        if (target !is ConsolePane.Overview) isEditing = false
    }

    fun createCategory(name: String, colorIndex: Int) = viewModelScope.launch {
        categoryRepository.create(name, colorIndex)
    }

    fun renameCategory(id: String, name: String) = viewModelScope.launch {
        categoryRepository.rename(id, name)
    }

    fun setCategoryColor(id: String, colorIndex: Int) = viewModelScope.launch {
        categoryRepository.setColor(id, colorIndex)
    }

    fun deleteCategory(id: String) = viewModelScope.launch {
        categoryRepository.delete(id)
        if ((pane as? ConsolePane.Category)?.id == id) pane = ConsolePane.Overview
    }

    fun setCategoryApps(id: String, packages: List<String>) = viewModelScope.launch {
        categoryRepository.setApps(id, packages)
    }

    fun removeAppFromCategory(id: String, packageName: String) = viewModelScope.launch {
        categoryRepository.removeApp(id, packageName)
    }

    fun setPinned(slot: Int, packageName: String?) = viewModelScope.launch {
        categoryRepository.setPinned(slot, packageName)
    }

    // ---- カード配置 ---------------------------------------------------------

    private val _layout = MutableStateFlow<List<CardLayout>>(emptyList())
    val layout: StateFlow<List<CardLayout>> = _layout.asStateFlow()

    var isEditing by mutableStateOf(false)
        private set

    private var dragging = false

    init {
        viewModelScope.launch {
            layoutRepository.layout.collect { stored ->
                if (!dragging) _layout.value = stored
            }
        }
    }

    fun setEditMode(editing: Boolean) {
        isEditing = editing
    }

    fun onDragStateChange(isDragging: Boolean) {
        dragging = isDragging
    }

    /** 引数は「表示中カードの並び」でのインデックス */
    fun move(fromVisible: Int, toVisible: Int) {
        val current = _layout.value
        val visible = current.filter { it.visible }
        val fromId = visible.getOrNull(fromVisible)?.id ?: return
        val toId = visible.getOrNull(toVisible)?.id ?: return
        val fromIndex = current.indexOfFirst { it.id == fromId }
        val toIndex = current.indexOfFirst { it.id == toId }
        if (fromIndex < 0 || toIndex < 0) return

        val reordered = current.toMutableList().apply { add(toIndex, removeAt(fromIndex)) }
        _layout.value = reordered
        viewModelScope.launch { layoutRepository.setLayout(reordered) }
    }

    fun toggleVisibility(id: String) = viewModelScope.launch { layoutRepository.toggleVisibility(id) }

    fun setSpan(id: String, span: CardSpan) = viewModelScope.launch { layoutRepository.setSpan(id, span) }

    fun resetLayout() = viewModelScope.launch { layoutRepository.resetToDefault() }

    fun setSortOrder(order: AppSortOrder) = viewModelScope.launch {
        preferences.update { it.copy(sortOrder = order) }
    }

    // ---- 共通 ---------------------------------------------------------------

    suspend fun icon(entry: AppEntry): ImageBitmap? = iconLoader.load(entry)

    fun launch(entry: AppEntry, sourceBounds: Rect? = null) {
        launcherApps.launch(entry.componentName, entry.user, sourceBounds)
    }

    private companion object {
        const val RECENT_APP_COUNT = 3
    }
}
