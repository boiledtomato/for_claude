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
import com.example.zlauncher.data.catalog.CatalogUpdateResult
import com.example.zlauncher.data.catalog.CatalogUpdater
import com.example.zlauncher.data.catalog.UrlCategoryRepository
import com.example.zlauncher.data.dashboard.DashboardLayoutRepository
import com.example.zlauncher.data.device.DeviceMetricsRepository
import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.domain.model.AppSortOrder
import com.example.zlauncher.domain.model.CardLayout
import com.example.zlauncher.domain.model.CardSpan
import com.example.zlauncher.domain.model.CatalogDiff
import com.example.zlauncher.domain.model.UrlCategoryEntry
import com.example.zlauncher.domain.model.UrlCategoryGroup
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
    private val catalogRepository: UrlCategoryRepository,
    private val catalogUpdater: CatalogUpdater,
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

    /**
     * 作ったあと、そのカテゴリーへ移動してアプリ選択を促す。
     *
     * 以前はここで作って終わりだった。レールに名前だけが増え、中身の無いカテゴリーが
     * そのまま残ってしまう（作った本人も気付かない）ので、続けて選ばせるところまでを 1 続きにする。
     */
    fun createCategory(name: String, colorIndex: Int) = viewModelScope.launch {
        val id = categoryRepository.create(name, colorIndex)
        promptForApps(id)
    }

    /** 作成直後にアプリ選択を開きたいカテゴリー。画面側が拾ったら [consumeAppPrompt] で戻す */
    var pendingAppPrompt by mutableStateOf<String?>(null)
        private set

    fun consumeAppPrompt() {
        pendingAppPrompt = null
    }

    fun promptForApps(id: String) {
        pane = ConsolePane.Category(id)
        isEditing = false
        pendingAppPrompt = id
    }

    /** 中身が空のカテゴリー。帯とレールの印に使う */
    val emptyCategories: StateFlow<List<CategoryWithApps>> = categoryRepository.categories
        .map { list -> list.filter { it.apps.isEmpty() } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

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
    private var themedIcons = false

    val themedIconsEnabled: StateFlow<Boolean> = preferences.state
        .map { it.themedIcons }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    init {
        viewModelScope.launch {
            layoutRepository.layout.collect { stored ->
                if (!dragging) _layout.value = stored
            }
        }
        viewModelScope.launch {
            preferences.state.collect { themedIcons = it.themedIcons }
        }
    }

    fun setThemedIcons(enabled: Boolean) = viewModelScope.launch {
        preferences.update { it.copy(themedIcons = enabled) }
    }

    fun createFromCatalog(entries: List<UrlCategoryEntry>) = viewModelScope.launch {
        val ids = categoryRepository.createFromCatalog(entries)
        // 複数まとめて作った場合は最初の 1 つだけ開く。人数分ダイアログを重ねても片付かない。
        // 残りは空カテゴリーの帯とレールの印から辿れる
        ids.firstOrNull()?.let { promptForApps(it) }
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

    // ---- URL カテゴリーカタログ ---------------------------------------------

    /** 大項目ごとにまとめた小項目。ダイアログの折りたたみがこれを描く */
    var catalogGroups by mutableStateOf<List<UrlCategoryGroup>>(emptyList())
        private set

    /** 採用中の CSV のファイル名。改訂日が入っているのでそのまま版として見せる */
    var catalogRevision by mutableStateOf("")
        private set

    var checkingCatalog by mutableStateOf(false)
        private set

    /** 手動更新の結果を 1 行で出す */
    var catalogMessage by mutableStateOf<String?>(null)
        private set

    val pendingCatalogDiff: StateFlow<CatalogDiff?> = preferences.state
        .map { it.pendingCatalogDiff }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    /** 差分を当てたときに名前が変わるカテゴリーの数。適用前に見せる */
    fun affectedByDiff(diff: CatalogDiff): Int {
        val renamedKeys = diff.renamed.map { it.from.key }.toSet()
        return categories.value.count { it.category.catalogKey in renamedKeys }
    }

    fun loadCatalog() = viewModelScope.launch {
        val catalog = catalogRepository.ensureLoaded()
        catalogGroups = catalog.groups()
        catalogRevision = catalog.revision
    }

    /** 3 か月を待たずに今すぐ確認する導線。設定から呼ぶ */
    fun checkCatalogNow() = viewModelScope.launch {
        if (checkingCatalog) return@launch
        checkingCatalog = true
        catalogMessage = when (val result = catalogUpdater.update()) {
            is CatalogUpdateResult.Updated ->
                "${result.diff.changeCount} change(s) found in ${result.diff.toRevision}."
            is CatalogUpdateResult.UpToDate -> "Already current (${result.revision})."
            is CatalogUpdateResult.Failed -> result.reason
        }
        loadCatalog().join()
        checkingCatalog = false
    }

    fun applyCatalogDiff() = viewModelScope.launch {
        catalogUpdater.applyPendingDiff()
        loadCatalog()
    }

    fun ignoreCatalogDiff() = viewModelScope.launch { catalogUpdater.dismissPendingDiff() }

    fun clearCatalogMessage() {
        catalogMessage = null
    }

    // ---- 共通 ---------------------------------------------------------------

    suspend fun icon(entry: AppEntry): ImageBitmap? = iconLoader.load(entry, themedIcons)

    fun launch(entry: AppEntry, sourceBounds: Rect? = null) {
        launcherApps.launch(entry.componentName, entry.user, sourceBounds)
    }

    private companion object {
        const val RECENT_APP_COUNT = 3
    }
}
