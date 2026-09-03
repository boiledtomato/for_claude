package com.example.zlauncher.ui.widgets

import android.content.ComponentName
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zlauncher.core.ui.toImageBitmap
import com.example.zlauncher.data.widgets.WidgetHostController
import com.example.zlauncher.data.widgets.WidgetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class WidgetPickerViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val host: WidgetHostController,
    private val widgetRepository: WidgetRepository,
) : ViewModel() {

    data class ProviderItem(
        val provider: ComponentName,
        val label: String,
        /** 提供元アプリの名前。ウィジェット名だけでは何のものか分からないものが多い */
        val appLabel: String,
        val icon: ImageBitmap?,
        val minHeightDp: Int,
        val sizeLabel: String,
        /** 設定画面を持つウィジェットは、バインド後にこれを起動しないと空のまま表示される */
        val configure: ComponentName?,
    ) {
        val key: String get() = provider.flattenToShortString()

        /** 検索対象。ウィジェット名・アプリ名・パッケージ名のどれで引いても当たるように */
        fun matches(query: String): Boolean {
            val q = query.trim()
            if (q.isEmpty()) return true
            return label.contains(q, ignoreCase = true) ||
                appLabel.contains(q, ignoreCase = true) ||
                provider.packageName.contains(q, ignoreCase = true)
        }
    }

    private val _providers = MutableStateFlow<List<ProviderItem>>(emptyList())
    val providers: StateFlow<List<ProviderItem>> = _providers.asStateFlow()

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    init {
        viewModelScope.launch {
            _providers.value = withContext(Dispatchers.IO) { loadProviders() }
            _loading.value = false
        }
    }

    private fun loadProviders(): List<ProviderItem> {
        val pm = context.packageManager
        val metrics = context.resources.displayMetrics
        val iconSize = (40 * metrics.density).roundToInt().coerceAtLeast(1)
        return host.installedProviders().mapNotNull { info ->
            runCatching {
                // AppWidgetProviderInfo.providerInfo は SDK に無いので、パッケージから引く。
                // 提供元が見えない場合（パッケージ可視性）はパッケージ名で代用する
                val appLabel = runCatching {
                    pm.getApplicationLabel(pm.getApplicationInfo(info.provider.packageName, 0)).toString()
                }.getOrNull().orEmpty().ifBlank { info.provider.packageName }
                ProviderItem(
                    provider = info.provider,
                    label = info.loadLabel(pm).ifBlank { info.provider.packageName },
                    appLabel = appLabel,
                    icon = info.loadIcon(context, metrics.densityDpi)?.toImageBitmap(iconSize),
                    minHeightDp = (info.minHeight / metrics.density).roundToInt().coerceIn(80, 320),
                    sizeLabel = "%d × %d dp".format(
                        (info.minWidth / metrics.density).roundToInt(),
                        (info.minHeight / metrics.density).roundToInt(),
                    ),
                    configure = info.configure,
                )
            }.getOrNull()
        }
            // 提供元アプリでまとめてから名前順。同じアプリのウィジェットが散らばると探せない
            .sortedWith(compareBy({ it.appLabel.lowercase() }, { it.label.lowercase() }))
    }

    // ---- 検索と複数選択 -----------------------------------------------------

    var query by mutableStateOf("")
        private set

    fun updateQuery(value: String) {
        query = value
    }

    /**
     * 選択中のウィジェット。**追加は選んでからまとめて行う。**
     *
     * 1 件ごとに同意ダイアログと設定画面をくぐるので、以前の「行を押した瞬間に 1 件追加」
     * だと、5 個入れるのに画面を 5 往復する必要があった。
     */
    val selected = mutableStateListOf<ProviderItem>()

    fun toggle(item: ProviderItem) {
        val existing = selected.firstOrNull { it.key == item.key }
        if (existing != null) selected.remove(existing) else selected.add(item)
    }

    fun isSelected(item: ProviderItem): Boolean = selected.any { it.key == item.key }

    fun clearSelection() = selected.clear()

    fun allocateAppWidgetId(): Int = host.allocateAppWidgetId()

    fun bindIfAllowed(appWidgetId: Int, provider: ComponentName): Boolean =
        host.bindIfAllowed(appWidgetId, provider)

    /** 追加を取りやめたときは必ず ID を返す（放置すると ID がリークする） */
    fun cancel(appWidgetId: Int) = host.deleteAppWidgetId(appWidgetId)

    fun confirm(appWidgetId: Int, heightDp: Int) = viewModelScope.launch {
        widgetRepository.add(appWidgetId, heightDp)
    }
}
