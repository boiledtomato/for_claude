package com.example.zlauncher.ui.widgets

import android.content.ComponentName
import android.content.Context
import android.os.Process
import android.os.UserHandle
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
import com.example.zlauncher.domain.model.WidgetPlacement
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
        /** どのプロファイルのプロバイダか。結び付けるときに要る */
        val user: UserHandle,
        /** 仕事用プロファイルのものか。同名のウィジェットが 2 つ並ぶので、見分けが要る */
        val isWorkProfile: Boolean,
        val label: String,
        /** 提供元アプリの名前。ウィジェット名だけでは何のものか分からないものが多い */
        val appLabel: String,
        val icon: ImageBitmap?,
        val minHeightDp: Int,
        /** 申告された minWidth から決まる「本来の幅」（列数） */
        val widthSpan: Int,
        val sizeLabel: String,
        /** 設定画面を持つウィジェットは、バインド後にこれを起動しないと空のまま表示される */
        val configure: ComponentName?,
    ) {
        // 同じアプリが個人用と仕事用の両方に入っていると provider は同一になる。
        // ユーザーを混ぜないと、片方を選んだだけでもう片方まで選択済みに見える
        val key: String get() = "${provider.flattenToShortString()}#${user.hashCode()}"

        /** 検索対象。ウィジェット名・アプリ名・パッケージ名のどれで引いても当たるように */
        fun matches(query: String): Boolean {
            val q = query.trim()
            if (q.isEmpty()) return true
            return label.contains(q, ignoreCase = true) ||
                appLabel.contains(q, ignoreCase = true) ||
                provider.packageName.contains(q, ignoreCase = true) ||
                // 仕事用のものだけを見たいときに絞れるように
                (isWorkProfile && "work".startsWith(q, ignoreCase = true))
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
        val me = Process.myUserHandle()
        return host.installedProviders().mapNotNull { info ->
            runCatching {
                // getProfile() は API 21 から。プロバイダがどちらのユーザーのものかを持っている
                val user = info.profile ?: me
                val work = user != me
                // AppWidgetProviderInfo.providerInfo は SDK に無いので、パッケージから引く。
                // 提供元が見えない場合（パッケージ可視性）はパッケージ名で代用する
                val appLabel = runCatching {
                    pm.getApplicationLabel(pm.getApplicationInfo(info.provider.packageName, 0)).toString()
                }.getOrNull().orEmpty().ifBlank { info.provider.packageName }
                ProviderItem(
                    provider = info.provider,
                    user = user,
                    isWorkProfile = work,
                    label = info.loadLabel(pm).ifBlank { info.provider.packageName },
                    appLabel = appLabel,
                    // 仕事用のアイコンにはブリーフケースを重ねる。ドロワーのタイルと同じ扱い
                    icon = info.loadIcon(context, metrics.densityDpi)
                        ?.let { if (work) pm.getUserBadgedIcon(it, user) else it }
                        ?.toImageBitmap(iconSize),
                    minHeightDp = (info.minHeight / metrics.density).roundToInt().coerceIn(80, 320),
                    widthSpan = WidgetPlacement.spanForWidthDp((info.minWidth / metrics.density).roundToInt()),
                    sizeLabel = "%d × %d dp".format(
                        (info.minWidth / metrics.density).roundToInt(),
                        (info.minHeight / metrics.density).roundToInt(),
                    ),
                    configure = info.configure,
                )
            }.getOrNull()
        }
            // 個人用を先に、そのあと提供元アプリでまとめてから名前順。
            // 同じアプリのウィジェットが散らばると探せない
            .sortedWith(
                compareBy({ it.isWorkProfile }, { it.appLabel.lowercase() }, { it.label.lowercase() }),
            )
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

    fun bindIfAllowed(appWidgetId: Int, user: UserHandle, provider: ComponentName): Boolean =
        host.bindIfAllowed(appWidgetId, user, provider)

    /** 追加を取りやめたときは必ず ID を返す（放置すると ID がリークする） */
    fun cancel(appWidgetId: Int) = host.deleteAppWidgetId(appWidgetId)

    fun confirm(appWidgetId: Int, heightDp: Int, widthSpan: Int) = viewModelScope.launch {
        widgetRepository.add(appWidgetId, heightDp, widthSpan)
    }
}
