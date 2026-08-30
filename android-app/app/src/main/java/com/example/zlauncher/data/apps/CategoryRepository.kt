package com.example.zlauncher.data.apps

import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import com.example.zlauncher.data.prefs.LauncherState
import com.example.zlauncher.domain.model.AppCategory
import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.domain.model.CATEGORY_COLOR_COUNT
import com.example.zlauncher.domain.model.UrlCategoryEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/** カテゴリーと、そこに実在するアプリを解決した結果 */
data class CategoryWithApps(
    val category: AppCategory,
    val apps: List<AppEntry>,
) {
    val id: String get() = category.id
}

/**
 * コンソール左レールのカテゴリーと、レール上部にピン留めする 2 アプリ。
 *
 * ドックと同じく、インストール済みアプリの Flow と combine して解決するので、
 * アンインストールされたアプリはカテゴリーからもピンからも自動的に消える。
 */
@Singleton
class CategoryRepository @Inject constructor(
    private val preferences: LauncherPreferencesRepository,
    installedApps: InstalledAppRepository,
) {
    val categories: Flow<List<CategoryWithApps>> =
        combine(preferences.state, installedApps.apps) { state, apps ->
            val byPackage = apps.filterNot { it.isWorkProfile }.associateBy { it.packageName }
            state.categories.map { category ->
                CategoryWithApps(
                    category = category,
                    apps = category.packages.mapNotNull { byPackage[it] },
                )
            }
        }

    /** レール上部のクイック起動。最大 [LauncherState.MAX_PINNED] 件 */
    val pinnedApps: Flow<List<AppEntry>> =
        combine(preferences.state, installedApps.apps) { state, apps ->
            val byPackage = apps.filterNot { it.isWorkProfile }.associateBy { it.packageName }
            state.pinnedApps.mapNotNull { byPackage[it] }
        }

    val pinnedSlots: Int get() = LauncherState.MAX_PINNED

    /** 作った id を返す。呼び出し側がそのままアプリ選択へ送れるようにするため */
    suspend fun create(name: String, colorIndex: Int): String {
        val category = AppCategory(
            id = UUID.randomUUID().toString(),
            name = name.trim().ifBlank { "New category" },
            colorIndex = colorIndex,
        )
        preferences.update { it.copy(categories = it.categories + category) }
        return category.id
    }

    /**
     * カタログの小項目からまとめて作る。既にある鍵・名前は飛ばす。
     *
     * [AppCategory.catalogKey] を残すのが肝。カタログが改訂されて名前が変わったとき、
     * どのカテゴリーを差し替えればいいかをこれで辿る。
     */
    suspend fun createFromCatalog(entries: List<UrlCategoryEntry>): List<String> {
        // 追加分は update の外で組み立てる。中で作ると id を呼び出し側へ返せない
        val state = preferences.state.first()
        val existingKeys = state.categories.mapNotNull { it.catalogKey }.toSet()
        val existingNames = state.categories.map { it.name }.toSet()
        var color = state.categories.size
        val added = entries
            .filterNot { it.key in existingKeys || it.category in existingNames }
            .map { entry ->
                AppCategory(
                    id = UUID.randomUUID().toString(),
                    name = entry.category,
                    colorIndex = color++ % CATEGORY_COLOR_COUNT,
                    catalogKey = entry.key,
                )
            }
        if (added.isEmpty()) return emptyList()
        preferences.update { it.copy(categories = it.categories + added) }
        return added.map { it.id }
    }

    suspend fun rename(id: String, name: String) = preferences.update { state ->
        val trimmed = name.trim()
        if (trimmed.isEmpty()) return@update state
        state.copy(categories = state.categories.map { if (it.id == id) it.copy(name = trimmed) else it })
    }

    suspend fun setColor(id: String, colorIndex: Int) = preferences.update { state ->
        state.copy(categories = state.categories.map { if (it.id == id) it.copy(colorIndex = colorIndex) else it })
    }

    suspend fun delete(id: String) = preferences.update { state ->
        state.copy(categories = state.categories.filterNot { it.id == id })
    }

    /** アプリ選択シートからの一括反映 */
    suspend fun setApps(id: String, packages: List<String>) = preferences.update { state ->
        state.copy(
            categories = state.categories.map {
                if (it.id == id) it.copy(packages = packages.distinct()) else it
            }
        )
    }

    suspend fun removeApp(id: String, packageName: String) = preferences.update { state ->
        state.copy(
            categories = state.categories.map {
                if (it.id == id) it.copy(packages = it.packages - packageName) else it
            }
        )
    }

    suspend fun move(from: Int, to: Int) = preferences.update { state ->
        val list = state.categories.toMutableList()
        if (from !in list.indices || to !in list.indices) return@update state
        list.add(to, list.removeAt(from))
        state.copy(categories = list)
    }

    /** スロット指定でピンを差し替える。null で解除 */
    suspend fun setPinned(slot: Int, packageName: String?) = preferences.update { state ->
        val slots = MutableList(LauncherState.MAX_PINNED) { index -> state.pinnedApps.getOrNull(index) }
        if (slot !in slots.indices) return@update state
        slots[slot] = packageName
        state.copy(pinnedApps = slots.filterNotNull())
    }
}
