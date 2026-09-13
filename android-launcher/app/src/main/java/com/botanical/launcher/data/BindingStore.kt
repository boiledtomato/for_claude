package com.botanical.launcher.data

import android.content.Context
import androidx.core.content.edit

/**
 * 「どの葉・どの花にどのアプリを割り当てたか」の永続化。
 *
 * 器官 ID（例: salvia.leaf0）→ アプリキー（package/class）。
 * ランチャーは起動が速くないと体感が悪いので SharedPreferences を同期で読む。
 */
class BindingStore(context: Context) {

    private val prefs = context.applicationContext
        .getSharedPreferences("organ_bindings", Context.MODE_PRIVATE)

    /** 版面のキャプション（学名）を出すか。 */
    var captionsVisible: Boolean
        get() = prefs.getBoolean(KEY_CAPTIONS, true)
        set(value) = prefs.edit { putBoolean(KEY_CAPTIONS, value) }

    /** 選んでいる図版。未選択なら null。 */
    var selectedPlateId: String?
        get() = prefs.getString(KEY_PLATE, null)
        set(value) = prefs.edit { putString(KEY_PLATE, value) }

    fun load(): Map<String, String> =
        prefs.all.entries
            .filter { it.key !in RESERVED && !it.key.startsWith(KEY_SEEDED) }
            .mapNotNull { (k, v) -> (v as? String)?.let { k to it } }
            .toMap()

    fun put(organId: String, appKey: String) = prefs.edit { putString(organId, appKey) }

    fun remove(organId: String) = prefs.edit { remove(organId) }

    /** 初期配置は図版ごとに一度だけ。別の図版に切り替えたらそちらも一度置く。 */
    private fun seededKey(plateId: String) = "$KEY_SEEDED$plateId"

    /**
     * 初回表示時、空の版面を見せても操作が分からないので、
     * よく使われそうなアプリを花・大きい葉の順に置いておく。
     */
    fun seedIfNeeded(
        plateId: String,
        apps: List<AppEntry>,
        slots: List<String>,
    ): Map<String, String> {
        if (prefs.getBoolean(seededKey(plateId), false) || apps.isEmpty() || slots.isEmpty()) {
            return load()
        }

        val byKey = apps.associateBy { it.key }
        val picked = LinkedHashSet<String>()
        for (pattern in SEED_ORDER) {
            val hit = apps.firstOrNull { it.packageName.contains(pattern, ignoreCase = true) }
            if (hit != null) picked += hit.key
        }
        // 足りない分はアルファベット順で埋める
        for (app in apps) {
            if (picked.size >= slots.size) break
            picked += app.key
        }

        val assignment = slots.zip(picked.toList()).toMap()
        prefs.edit {
            assignment.forEach { (organId, appKey) -> putString(organId, appKey) }
            putBoolean(seededKey(plateId), true)
        }
        // 既に他の図版に置いた割り当ても残す
        return load() + assignment.filterValues { byKey.containsKey(it) }
    }

    private companion object {
        const val KEY_SEEDED = "__seeded__"
        const val KEY_CAPTIONS = "__captions__"
        const val KEY_PLATE = "__plate__"
        val RESERVED = setOf(KEY_CAPTIONS, KEY_PLATE)

        /** 「ホームに置いてあってほしい」順。部分一致で探す。 */
        val SEED_ORDER = listOf(
            "dialer", "contacts", "messaging", "messages", "chrome", "camera",
            "gm", "maps", "youtube", "calendar", "music", "gallery", "photos",
            "settings", "clock", "calculator",
        )
    }
}
