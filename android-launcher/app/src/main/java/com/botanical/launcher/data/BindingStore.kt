package com.botanical.launcher.data

import android.content.Context
import androidx.core.content.edit

/**
 * 「どの花・どの葉に、どのアプリを割り当てたか」の永続化。
 *
 * 部位 ID（例: pl134/magna）→ アプリキー（package/class）の**並び**。
 * 1 つの部位に複数入れられる（束ねた花のように、まとめて開く）。
 *
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

    fun load(): Map<String, List<String>> =
        prefs.all.entries
            .filter { it.key !in RESERVED && !it.key.startsWith(KEY_SEEDED) }
            .mapNotNull { (k, v) ->
                (v as? String)?.split(SEP)?.filter { it.isNotBlank() }
                    ?.takeIf { it.isNotEmpty() }
                    ?.let { k to it }
            }
            .toMap()

    fun put(organId: String, appKeys: List<String>) {
        if (appKeys.isEmpty()) {
            remove(organId)
        } else {
            prefs.edit { putString(organId, appKeys.joinToString(SEP)) }
        }
    }

    fun remove(organId: String) = prefs.edit { remove(organId) }

    /** 初期配置は図版ごとに一度だけ。別の図版に切り替えたらそちらも一度置く。 */
    private fun seededKey(plateId: String) = "$KEY_SEEDED$plateId"

    /**
     * 初回表示時、空の版面を見せても操作が分からないので、
     * よく使われそうなアプリを目立つ部位から順に置いておく。
     */
    fun seedIfNeeded(
        plateId: String,
        apps: List<AppEntry>,
        slots: List<String>,
    ): Map<String, List<String>> {
        if (prefs.getBoolean(seededKey(plateId), false) || apps.isEmpty() || slots.isEmpty()) {
            return load()
        }

        val picked = LinkedHashSet<String>()
        for (pattern in SEED_ORDER) {
            apps.firstOrNull { it.packageName.contains(pattern, ignoreCase = true) }
                ?.let { picked += it.key }
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
        // 既に他の図版へ置いた割り当ても残す
        return load()
    }

    private companion object {
        const val SEP = "|"
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
