package com.example.zlauncher.data.apps

import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import com.example.zlauncher.data.prefs.LauncherState
import com.example.zlauncher.domain.model.AppCategory
import com.example.zlauncher.domain.model.CategoryRules
import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.domain.model.CATEGORY_COLOR_COUNT
import com.example.zlauncher.domain.model.CatalogPick
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
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
            val personal = apps.filterNot { it.isWorkProfile }
            val byPackage = personal.associateBy { it.packageName }
            // 未分類の枠だけは所属を保存せず、ここで計算する（理由は CategoryRules）
            val loose by lazy {
                CategoryRules.catchAllPackages(state.categories, personal.map { it.packageName })
            }
            state.categories.map { category ->
                val packages = if (category.isCatchAll) loose else category.packages
                CategoryWithApps(
                    category = category,
                    apps = packages.mapNotNull { byPackage[it] }
                        // 利用者が並べた順が無い枠なので、名前順で落ち着かせる
                        .let { if (category.isCatchAll) it.sortedBy { app -> app.label.lowercase() } else it },
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

    val pinnedExpanded: Flow<Boolean> = preferences.state.map { it.pinnedExpanded }

    suspend fun setPinnedExpanded(expanded: Boolean) = preferences.update {
        it.copy(pinnedExpanded = expanded)
    }

    val categoriesExpanded: Flow<Boolean> = preferences.state.map { it.categoriesExpanded }

    suspend fun setCategoriesExpanded(expanded: Boolean) = preferences.update {
        it.copy(categoriesExpanded = expanded)
    }

    /**
     * 作った id を返す。呼び出し側がそのままアプリ選択へ送れるようにするため。
     * **同じ名前が既にあるときは作らず null を返す**（画面側でも弾くが、最後の砦）。
     */
    suspend fun create(name: String, colorIndex: Int): String? {
        val wanted = name.trim().ifBlank { "New category" }
        val state = preferences.state.first()
        if (CategoryRules.nameTaken(state.categories, wanted)) return null
        val category = AppCategory(
            id = UUID.randomUUID().toString(),
            name = wanted,
            colorIndex = colorIndex,
        )
        preferences.update { it.copy(categories = it.categories + category) }
        return category.id
    }

    /**
     * 未分類を集める枠の作成 / 撤去。
     *
     * 撤去で失うものは無い ― 所属は保存しておらず、その都度計算しているため。
     */
    suspend fun setCatchAllEnabled(enabled: Boolean) = preferences.update { state ->
        val existing = CategoryRules.catchAll(state.categories)
        when {
            enabled && existing != null -> state
            enabled -> {
                // 同名の枠を利用者が先に作っていたら、それを昇格させる（2 つ並べない）
                val byName = state.categories.firstOrNull {
                    CategoryRules.normalize(it.name) == CategoryRules.normalize(CategoryRules.CATCH_ALL_NAME)
                }
                if (byName != null) {
                    state.copy(
                        categories = state.categories.map {
                            if (it.id == byName.id) it.copy(isCatchAll = true) else it
                        },
                    )
                } else {
                    state.copy(
                        categories = state.categories + AppCategory(
                            id = UUID.randomUUID().toString(),
                            name = CategoryRules.CATCH_ALL_NAME,
                            colorIndex = state.categories.size % CATEGORY_COLOR_COUNT,
                            isCatchAll = true,
                        ),
                    )
                }
            }
            existing != null -> state.copy(categories = state.categories.filterNot { it.isCatchAll })
            else -> state
        }
    }

    /**
     * カタログの小項目からまとめて作る。既にある鍵・名前は飛ばす。
     *
     * [AppCategory.catalogKey] を残すのが肝。カタログが改訂されて名前が変わったとき、
     * どのカテゴリーを差し替えればいいかをこれで辿る。
     */
    suspend fun createFromCatalog(picks: List<CatalogPick>): List<String> {
        // 追加分は update の外で組み立てる。中で作ると id を呼び出し側へ返せない
        val state = preferences.state.first()
        val existingKeys = state.categories.mapNotNull { it.catalogKey }.toSet()
        val existingNames = state.categories.mapTo(mutableSetOf()) { CategoryRules.normalize(it.name) }
        val added = picks
            .filterNot {
                it.entry.key in existingKeys ||
                    CategoryRules.normalize(it.entry.category) in existingNames
            }
            .map { pick ->
                AppCategory(
                    id = UUID.randomUUID().toString(),
                    name = pick.entry.category,
                    colorIndex = pick.colorIndex % CATEGORY_COLOR_COUNT,
                    catalogKey = pick.entry.key,
                )
            }
        if (added.isEmpty()) return emptyList()
        preferences.update { it.copy(categories = it.categories + added) }
        return added.map { it.id }
    }

    suspend fun rename(id: String, name: String) = preferences.update { state ->
        val trimmed = name.trim()
        if (trimmed.isEmpty()) return@update state
        // 自分以外に同じ名前が居たら何もしない（画面側でも弾くが、最後の砦）
        if (CategoryRules.nameTaken(state.categories, trimmed, exceptId = id)) return@update state
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

    /** まとめて外す。取り外しモードは複数選べるので、1 件ずつ書くと保存が何度も走る */
    suspend fun removeApps(id: String, packageNames: Collection<String>) = preferences.update { state ->
        if (packageNames.isEmpty()) return@update state
        val drop = packageNames.toSet()
        state.copy(
            categories = state.categories.map {
                if (it.id == id) it.copy(packages = it.packages.filterNot(drop::contains)) else it
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
