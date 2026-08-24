package com.example.zlauncher.ui.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zlauncher.data.dashboard.DashboardDataSource
import com.example.zlauncher.data.dashboard.DashboardLayoutRepository
import com.example.zlauncher.data.dashboard.DashboardSnapshot
import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import com.example.zlauncher.domain.model.AppSortOrder
import com.example.zlauncher.domain.model.CardLayout
import com.example.zlauncher.domain.model.CardSpan
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val layoutRepository: DashboardLayoutRepository,
    private val preferences: LauncherPreferencesRepository,
    dataSource: DashboardDataSource,
) : ViewModel() {

    /**
     * データ取得の失敗はここで握って [Result] に落とす。カード 1 枚のためにコンソール全体を
     * 落とさないための保険（既定ホームアプリのクラッシュは利用者から見て端末の故障に等しい）。
     */
    val snapshot: StateFlow<Result<DashboardSnapshot>?> = dataSource.snapshot()
        .map { Result.success(it) }
        .catch { emit(Result.failure(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    /** ホームのアプリ並び順。日本語ラベルは五十音順にできないため、切替を用意している */
    val sortOrder: StateFlow<AppSortOrder> = preferences.state
        .map { it.sortOrder }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), AppSortOrder.LABEL)

    private val _layout = MutableStateFlow<List<CardLayout>>(emptyList())
    val layout: StateFlow<List<CardLayout>> = _layout.asStateFlow()

    var isEditing by mutableStateOf(false)
        private set

    private var dragging = false

    init {
        viewModelScope.launch {
            layoutRepository.layout.collect { stored ->
                // ドラッグ中はストア側の再入力で並びが飛ばないようにする
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

    fun toggleVisibility(id: String) = viewModelScope.launch {
        layoutRepository.toggleVisibility(id)
    }

    fun setSpan(id: String, span: CardSpan) = viewModelScope.launch {
        layoutRepository.setSpan(id, span)
    }

    fun setSortOrder(order: AppSortOrder) = viewModelScope.launch {
        preferences.update { it.copy(sortOrder = order) }
    }

    fun resetLayout() = viewModelScope.launch {
        layoutRepository.resetToDefault()
    }
}
