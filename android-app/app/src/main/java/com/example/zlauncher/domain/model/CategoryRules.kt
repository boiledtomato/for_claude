package com.example.zlauncher.domain.model

/**
 * カテゴリーの決まりごと。**Android に依存しない**ので、そのままテストにかけられる。
 *
 * ここが崩れると「同じ名前のカテゴリーが 2 つ並ぶ」「同じアプリが 2 か所に出る」という
 * 形で出る。どちらも画面を見て気付くより、規則として書いて固定するほうが早い。
 */
object CategoryRules {

    /** 未分類を集める枠の名前 */
    const val CATCH_ALL_NAME = "Miscellaneous / Unknown"

    /**
     * 名前の突き合わせ方。前後の空白を落として大小を無視する。
     *
     * `Work` と `work ` を別物として通すと、レールには見分けのつかない行が 2 つ並ぶ。
     */
    fun normalize(name: String): String = name.trim().lowercase()

    /**
     * その名前が既に使われているか。[exceptId] は改名中の当人（自分自身とは衝突しない）。
     */
    fun nameTaken(categories: List<AppCategory>, name: String, exceptId: String? = null): Boolean {
        val target = normalize(name)
        if (target.isEmpty()) return false
        return categories.any { it.id != exceptId && normalize(it.name) == target }
    }

    /**
     * 未分類を集める枠に入るパッケージ。**保存せず、その都度ここで計算する。**
     *
     * 保存にすると、ほかのカテゴリーへ入れた瞬間にこちらからも消す書き込みが要る。
     * 取りこぼせば同じアプリが 2 か所に出るし、順番も食い違う。導出なら、ほかに
     * 入れた時点で自動的に外れ、外した時点で自動的に戻る。
     */
    fun catchAllPackages(categories: List<AppCategory>, installed: List<String>): List<String> {
        val claimed = categories.filterNot { it.isCatchAll }.flatMapTo(mutableSetOf()) { it.packages }
        return installed.filterNot { it in claimed }
    }

    /** 未分類の枠は 1 つだけ。2 つ作ると、どちらにも同じものが出る */
    fun catchAll(categories: List<AppCategory>): AppCategory? = categories.firstOrNull { it.isCatchAll }
}
