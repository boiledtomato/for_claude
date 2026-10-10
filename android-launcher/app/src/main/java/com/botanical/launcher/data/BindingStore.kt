package com.botanical.launcher.data

import android.content.Context
import androidx.core.content.edit

/**
 * 「どの花・どの葉に、どのアプリを割り当てたか」の永続化。
 *
 * 部位 ID（例: main.flos1）→ アプリキー（package/class）の**並び**。
 * 1 つの部位に複数入れられる（束ねた花のように、まとめて開く）。
 *
 * 割り当ては**画面ごと**に持つ。折りたたみ端末では、閉じた表画面と開いた
 * 内側画面で持ちたいアプリが違う。表には電話とカメラ、内側には別のものを、
 * といった使い分けができないと、折りたたみに対応したとは言えない。
 * 保存キーは "<scope>/<部位 ID>"（scope は [Scope.COVER] / [Scope.MAIN]）。
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

    fun load(scope: String): Map<String, List<String>> {
        migrateUnscoped()
        val prefix = "$scope/"
        return prefs.all.entries
            .filter { it.key.startsWith(prefix) }
            .mapNotNull { (k, v) ->
                (v as? String)?.split(SEP)?.filter { it.isNotBlank() }
                    ?.takeIf { it.isNotEmpty() }
                    ?.let { k.removePrefix(prefix) to it }
            }
            .toMap()
    }

    fun put(scope: String, organId: String, appKeys: List<String>) {
        if (appKeys.isEmpty()) {
            remove(scope, organId)
        } else {
            prefs.edit { putString("$scope/$organId", appKeys.joinToString(SEP)) }
        }
    }

    fun remove(scope: String, organId: String) = prefs.edit { remove("$scope/$organId") }

    /**
     * 画面ごとに分ける前に保存した割り当てを、表画面のぶんとして引き継ぐ。
     * 一度だけ。消してしまうと、更新したとたんに設定が全部消えたように見える。
     */
    private fun migrateUnscoped() {
        if (prefs.getBoolean(KEY_MIGRATED, false)) return
        val old = prefs.all.entries.filter {
            !it.key.startsWith("__") && !it.key.contains('/') && it.value is String
        }
        prefs.edit {
            old.forEach { (k, v) ->
                putString("${Scope.COVER}/$k", v as String)
                remove(k)
            }
            putBoolean(KEY_MIGRATED, true)
        }
    }

    /** 初期配置は図版ごとに一度だけ。別の図版に切り替えたらそちらも一度置く。 */
    private fun seededKey(plateId: String) = "$KEY_SEEDED$plateId"

    /**
     * 初回表示時、空の版面を見せても操作が分からないので、
     * よく使われそうなアプリを目立つ部位から順に置いておく。
     */
    fun seedIfNeeded(
        scope: String,
        plateId: String,
        apps: List<AppEntry>,
        slots: List<String>,
    ): Map<String, List<String>> {
        migrateUnscoped()
        val key = seededKey("$scope/$plateId")
        if (prefs.getBoolean(key, false) || apps.isEmpty() || slots.isEmpty()) {
            return load(scope)
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
            assignment.forEach { (organId, appKey) -> putString("$scope/$organId", appKey) }
            putBoolean(key, true)
        }
        return load(scope)
    }

    /** 画面の区分。折りたたんだ表画面と開いた内側画面で割り当てを分ける。 */
    object Scope {
        const val COVER = "cover"
        const val MAIN = "main"

        /**
         * 画面幅から区分を決める。600dp は Android が「大きい画面」と
         * 呼ぶ境目で、折りたたみ端末の表画面（約 320dp）と内側画面
         * （約 700dp）はこれで分かれる。
         */
        fun of(screenWidthDp: Int): String = if (screenWidthDp >= 600) MAIN else COVER
    }

    private companion object {
        const val SEP = "|"
        const val KEY_SEEDED = "__seeded__"
        const val KEY_CAPTIONS = "__captions__"
        const val KEY_MIGRATED = "__scoped__"
        const val KEY_PLATE = "__plate__"
        val RESERVED = setOf(KEY_CAPTIONS, KEY_PLATE, KEY_MIGRATED)

        /** 「ホームに置いてあってほしい」順。部分一致で探す。 */
        val SEED_ORDER = listOf(
            "dialer", "contacts", "messaging", "messages", "chrome", "camera",
            "gm", "maps", "youtube", "calendar", "music", "gallery", "photos",
            "settings", "clock", "calculator",
        )
    }
}
