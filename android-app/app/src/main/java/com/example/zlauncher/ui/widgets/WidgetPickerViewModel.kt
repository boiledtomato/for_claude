package com.example.zlauncher.ui.widgets

import android.content.ComponentName
import android.content.Context
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
        val icon: ImageBitmap?,
        val minHeightDp: Int,
        val sizeLabel: String,
        /** 設定画面を持つウィジェットは、バインド後にこれを起動しないと空のまま表示される */
        val configure: ComponentName?,
    )

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
                ProviderItem(
                    provider = info.provider,
                    label = info.loadLabel(pm).ifBlank { info.provider.packageName },
                    icon = info.loadIcon(context, metrics.densityDpi)?.toImageBitmap(iconSize),
                    minHeightDp = (info.minHeight / metrics.density).roundToInt().coerceIn(80, 320),
                    sizeLabel = "%d × %d dp".format(
                        (info.minWidth / metrics.density).roundToInt(),
                        (info.minHeight / metrics.density).roundToInt(),
                    ),
                    configure = info.configure,
                )
            }.getOrNull()
        }.sortedBy { it.label }
    }

    fun allocateAppWidgetId(): Int = host.allocateAppWidgetId()

    fun bindIfAllowed(appWidgetId: Int, provider: ComponentName): Boolean =
        host.bindIfAllowed(appWidgetId, provider)

    /** 追加を取りやめたときは必ず ID を返す（放置すると ID がリークする） */
    fun cancel(appWidgetId: Int) = host.deleteAppWidgetId(appWidgetId)

    fun confirm(appWidgetId: Int, heightDp: Int) = viewModelScope.launch {
        widgetRepository.add(appWidgetId, heightDp)
    }
}
